#!/usr/bin/env python3
import json, glob, re
from pathlib import Path

FULL2HALF = str.maketrans({'（': '(', '）': ')', '％': '%', '　': ' '})


def norm_text(s: str) -> str:
    if not isinstance(s, str):
        return s
    s = s.translate(FULL2HALF)
    s = s.replace('’', "'")
    s = s.replace('\u2013', '-')
    s = re.sub(r"\s+", " ", s)
    return s.strip()


def strip_commas(num_str: str) -> str:
    return re.sub(r",(?=\d{3}(\D|$))", "", num_str)


PHASE_MAP = {'whole': 'Overall', 'overall': 'Overall', 'train': 'Train', 'training': 'Train'}


def detect_phase(s: str):
    m = re.search(r"\b(Whole|Overall|Train|Training|Baseline|Follow-up)\b", s, re.I)
    if m:
        return PHASE_MAP.get(m.group(1).lower(), m.group(1).title())
    return None


def detect_model_tag(s: str):
    tag = None
    m = re.search(r"\bNM\s*(\d+)\b", s, re.I)
    if m:
        tag = f"NM{m.group(1)}"
    if re.search(r"func", s, re.I):
        tag = (tag + ' functional') if tag else 'functional'
    if re.search(r"structur(al|e)", s, re.I):
        tag = (tag + ' structural') if tag else 'structural'
    return tag


def detect_n_qualifier(s: str):
    if re.search(r"approx|approximately", s, re.I): return 'approx'
    if re.search(r"\bexact\b|stated", s, re.I): return 'exact'
    return ''


def detect_first_int_excl_nm(s: str):
    nums = [(m.group(0), m.start()) for m in re.finditer(r"(?<!NM)\b\d[\d,]*\b", s)]
    if not nums:
        return None
    cands = [n for n, _ in nums if len(re.sub(r",", "", n)) >= 3]
    n = cands[0] if cands else nums[0][0]
    try:
        return int(strip_commas(n))
    except:
        return None


def split_records_n(s: str):
    if '\n' in s:
        return [p.strip() for p in s.split('\n') if p.strip()]
    if ';' in s and re.search(r"\bNM\d+\b", s):
        return [p.strip() for p in s.split(';') if p.strip()]
    return [s.strip()]


def normalize_rci2(s: str) -> str:
    s0 = norm_text(s)
    recs = split_records_n(s0)
    out = []
    for r in recs:
        phase = detect_phase(r)
        model = detect_model_tag(r)
        qual = detect_n_qualifier(r)
        nval = detect_first_int_excl_nm(r)
        if nval is None or (nval == 1 and re.search(r"\bNM\s*1\b", r)):
            out.append(r);
            continue
        parts = []
        if model: parts.append(f"Model: {model}")
        parts.append(f"Phase: {phase if phase else 'Uninvestigated'}")
        parts.append(f"N: {nval}" + (f" {qual}" if qual else ''))
        out.append(" | ".join(parts))
    return "\n".join(out)


AGE_KEYS = ['mean', 'sd', 'median', 'iqr', 'min', 'max']


def extract_range(t):
    m = re.search(r"(\d+(?:\.\d+)?)\s*[\-–]\s*(\d+(?:\.\d+)?)", t)
    if m: return m.group(1), m.group(2)
    return None


def parse_age_record(r: str) -> str:
    r0 = norm_text(r)
    r0 = re.sub(r"\bWhole\b", "Overall", r0, flags=re.I)
    r0 = re.sub(r"inffe?red", "inferred", r0, flags=re.I)
    r0 = re.sub(r"stated", "exact", r0, flags=re.I)
    tokens = [t.strip() for t in r0.split(';') if t.strip()]
    model = None;
    phase = None
    vals = {'mean': 'NR', 'sd': 'NR', 'median': 'NR', 'iqr': 'NR', 'min': 'NR', 'max': 'NR'}
    tags = {'mean': [], 'sd': [], 'min': [], 'max': []}

    rem = []
    for t in tokens:
        low = t.lower()
        if model is None and (
                re.search(r"\bnm\d+\b", low) or 'func' in low or 'structur' in low or 'smri' in low or 'dmri' in low):
            m = re.search(r"\bnm\s*(\d+)\b", t, re.I)
            tag = []
            if m: tag.append(f"NM{m.group(1)}")
            if 'func' in low: tag.append('functional')
            if 'structur' in low: tag.append('structural')
            if re.search(r"\bsmri\b", low): tag.append('sMRI')
            if re.search(r"\bdmri\b", low): tag.append('dMRI')
            model = ' '.join(tag) if tag else t
            continue
        if phase is None and re.search(r"\b(Overall|Whole|Train|Training|Baseline|Follow-up)\b", t, re.I):
            ph = re.search(r"\b(Overall|Whole|Train|Training|Baseline|Follow-up)\b", t, re.I).group(1)
            phase = PHASE_MAP.get(ph.lower(), ph)
            continue
        rem.append(t)

    # Remove N-looking tokens
    rem2 = []
    for t in rem:
        tl = t.lower()
        if (('approx' in tl or 'exact' in tl) and re.search(r"\d", t)
                and not re.search(r"\b(mean|sd|median|iqr|min|max)\b", tl)):
            continue
        rem2.append(t)

    # key-based extraction
    for t in rem2:
        t0 = strip_commas(t)
        tl = t0.lower()
        matched = False
        for key in AGE_KEYS:
            if re.search(rf"\b{key}\b", tl):
                matched = True
                m = re.search(r"(-?\d+(?:\.\d+)?)", t0)
                if m:
                    vals[key] = m.group(1)
                else:
                    if 'nr' in tl or 'na' in tl: vals[key] = 'NR'
                if 'weighted' in tl and key == 'mean': tags['mean'].append('weighted')
                if 'pooled' in tl and key == 'sd': tags['sd'].append('pooled')
                if 'inferred' in tl and key in ('min', 'max'): tags[key].append('inferred')
                if 'exact' in tl and key in ('mean', 'sd'): tags[key].append('exact')
                break
        if not matched:
            rng = extract_range(t0)
            if rng:
                vals['min'], vals['max'] = rng

    # fallback: infer from unlabeled numbers conservatively
    if rem2:
        nums = []
        for t in rem2:
            m = re.search(r"(-?\d+(?:\.\d+)?)", t)
            if m:
                num = m.group(1)
                tl = t.lower()
                nums.append((num, tl))
        # If exactly two numbers and mean/sd are unknown, treat them as min,max
        if len(nums) == 2 and vals['min'] == 'NR' and vals['max'] == 'NR' and vals['mean'] == 'NR' and vals[
            'sd'] == 'NR':
            vals['min'], vals['max'] = nums[0][0], nums[1][0]
            if 'inferred' in nums[0][1]: tags['min'].append('inferred')
            if 'inferred' in nums[1][1]: tags['max'].append('inferred')
        # If four numbers and nothing labeled, map as mean, sd, min, max
        elif len(nums) >= 4 and all(vals[k] == 'NR' for k in ('mean', 'sd', 'min', 'max')):
            order = ['mean', 'sd', 'min', 'max']
            for (num, tl), key in zip(nums, order):
                vals[key] = num
                if key == 'mean' and 'weighted' in tl: tags['mean'].append('weighted')
                if key == 'sd' and 'pooled' in tl: tags['sd'].append('pooled')
                if key in ('min', 'max') and 'inferred' in tl: tags[key].append('inferred')
                if key in ('mean', 'sd') and 'exact' in tl: tags[key].append('exact')

    parts = []
    if model: parts.append(f"Model: {model}")
    parts.append(f"Phase: {phase if phase else 'Uninvestigated'}")
    parts.append("mean: " + vals['mean'] + (f" {' '.join(tags['mean'])}" if tags['mean'] else ''))
    parts.append("sd: " + vals['sd'] + (f" {' '.join(tags['sd'])}" if tags['sd'] else ''))
    parts.append("median: " + vals['median'])
    parts.append("iqr: " + vals['iqr'])
    parts.append("min: " + vals['min'] + (f" {' '.join(tags['min'])}" if tags['min'] else ''))
    parts.append("max: " + vals['max'] + (f" {' '.join(tags['max'])}" if tags['max'] else ''))
    return " | ".join(parts)


def normalize_rci3(s: str) -> str:
    # Age は1レコード=1行。先に改行で分割し、各レコードごとに正規化する。
    recs = s.split('\n') if '\n' in s else [s]
    out = [parse_age_record(norm_text(r)) for r in recs if str(r).strip()]
    return "\n".join(out)


def parse_sex_record(r: str) -> str:
    r0 = norm_text(r)
    model = None;
    phase = None
    body = r0
    m = re.match(r"^([^:]+):\s*(.*)$", r0)
    if m:
        label = m.group(1).strip()
        body = m.group(2).strip()
        low = label.lower()
        if low == 'model':
            if '|' in body:
                model = body.split('|', 1)[0].strip();
                body = body.split('|', 1)[1].strip()
            else:
                model = body;
                body = ''
        elif 'func' in low or 'structur' in low:
            model = 'functional' if 'func' in low else 'structural'
        elif re.search(r"baseline|follow-up", low):
            phase = 'Baseline' if 'baseline' in low else 'Follow-up'
        else:
            model = label

    def find_one(letter):
        pat = rf"{letter}\s*(\d+)\s*\((\d+(?:\.\d+)?)%\)"
        m = re.search(pat, body, re.I)
        if m:
            return int(strip_commas(m.group(1))), float(m.group(2))
        return None

    F = find_one('F');
    M = find_one('M');
    NR = find_one('NR')
    segs = []
    if model: segs.append(f"Model: {model}")
    segs.append(f"Phase: {phase if phase else 'Uninvestigated'}")
    if F is None and re.search(r"\bF\s*\d+\b", body):
        c = int(strip_commas(re.search(r"\bF\s*(\d+)\b", body).group(1)));
        F = (c, None)
    if M is None and re.search(r"\bM\s*\d+\b", body):
        c = int(strip_commas(re.search(r"\bM\s*(\d+)\b", body).group(1)));
        M = (c, None)
    if NR is None and re.search(r"\bNR\s*\d+\b", body):
        c = int(strip_commas(re.search(r"\bNR\s*(\d+)\b", body).group(1)));
        NR = (c, None)

    def fmt(pair, label):
        if not pair: return None
        c, p = pair
        if p is None: return f"{label} {c}"
        pstr = f"{p:.1f}" if (p % 1) != 0 else f"{int(p)}"
        return f"{label} {c} ({pstr}%)"

    sex_parts = []
    if F: sex_parts.append(fmt(F, 'F'))
    if M: sex_parts.append(fmt(M, 'M'))
    if NR: sex_parts.append(fmt(NR, 'NR'))
    if sex_parts:
        segs.append(", ".join(sex_parts))
    else:
        segs.append(body)
    return " | ".join(segs)


def normalize_rci4(s: str) -> str:
    s0 = s
    recs = s0.split('\n') if '\n' in s0 else [s0]
    return "\n".join(parse_sex_record(r) for r in recs if r.strip())


def process_file(path: str):
    changed = False
    with open(path, 'r', encoding='utf-8') as f:
        try:
            j = json.load(f)
        except Exception:
            return False
    ref = j.get('reference_cohort_and_imaging_part') or {}
    for key, func in (
            ('rci2_hc_n', normalize_rci2),
            ('rci3_hc_age', normalize_rci3),
            ('rci4_hc_sex', normalize_rci4),
    ):
        node = ref.get(key)
        if isinstance(node, dict):
            ans = node.get('answer', '')
            if isinstance(ans, str) and ans.strip():
                new = func(ans)
                if new != ans:
                    node['answer'] = new;
                    changed = True
        elif isinstance(node, str) and node.strip():
            new = func(node)
            if new != node:
                ref[key] = new;
                changed = True
    if changed:
        with open(path, 'w', encoding='utf-8') as f:
            json.dump(j, f, ensure_ascii=False, indent=2)
            f.write('\n')
    return changed


def main():
    patterns = [
        'share_package/data/*/DE_v10/json*/DE_*human*.json',
        'share_package/data/*/DE_v10/json*/DE_*Human*.json',
    ]
    files = []
    for pat in patterns:
        files.extend(glob.glob(pat))
    updated = 0
    for p in files:
        if process_file(p):
            updated += 1
    print(f'FILES_MATCHED {len(files)}')
    print(f'FILES_UPDATED {updated}')


if __name__ == '__main__':
    main()
