#!/usr/bin/env python3
import json
import re
from pathlib import Path

BASE = Path('share_package/data')


def strip_trailing_period(s: str) -> str:
    if s is None:
        return ''
    t = s.strip()
    while t.endswith('.'):
        t = t[:-1]
    return t


def contains_word(s: str, w: str) -> bool:
    return re.search(rf"\b{re.escape(w)}\b", s) is not None


def classify_one(src: str) -> str:
    s = strip_trailing_period(src).replace('\u3000', ' ').strip()
    lower = s.lower()

    # T1w MRI
    if (re.search(r"t1\s*-?weighted.*mri", lower)
            or 'structural mri' in lower
            or re.search(r"\bsmri\b", lower)
            or re.search(r"\bt1w\b", lower)):
        return 'T1w MRI'

    # T2w MRI
    if 't2-weighted' in lower and 'mri' in lower:
        return 'T2w MRI'
    if 'flair' in lower:
        return 'T2w MRI'

    # fMRI
    if 'fmri' in lower:
        details = []
        if 'rest' in lower:
            details.append('rest')
        if 'task' in lower:
            details.append('task')
        if 'bold' in lower:
            details.append('BOLD')
        return 'fMRI' if not details else f"fMRI ({', '.join(details)})"

    # dMRI
    if any(k in lower for k in ['diffusion', 'dwi', 'dti', 'dmri']):
        details = []
        if 'dti' in lower:
            metrics = []
            if contains_word(lower, 'fa'):
                metrics.append('FA')
            if contains_word(lower, 'md'):
                metrics.append('MD')
            if contains_word(lower, 'rd'):
                metrics.append('RD')
            if contains_word(lower, 'axd') or contains_word(lower, 'ad'):
                metrics.append('AxD')
            details.append('DTI' if not metrics else f"DTI: {', '.join(metrics)}")
        return 'dMRI' if not details else f"dMRI ({', '.join(details)})"

    # PET
    if 'pet' in lower:
        details = []
        if any(k in lower for k in ['amyloid', 'av45', 'florbetapir']):
            details.append('Amyloid')
            if any(k in lower for k in ['florbetapir', 'av45']):
                details.append('18F-Florbetapir')
        if any(k in lower for k in ['tau', 'flortaucipir', 'ftp']):
            details.append('Tau')
            if any(k in lower for k in ['flortaucipir', 'ftp']):
                details.append('18F-Flortaucipir')
        if 'fdopa' in lower:
            details.append('18F-FDOPA')
        if '[11c]' in lower or '11c' in lower:
            details.append('11C tracer')
        return 'PET' if not details else f"PET ({', '.join(details)})"

    # EEG
    if 'eeg' in lower:
        details = []
        if 'rest' in lower:
            details.append('rest')
        if any(k in lower for k in ['hd', 'high-density', '128']):
            details.append('HD-128ch')
        return 'EEG' if not details else f"EEG ({', '.join(details)})"

    # MEG
    if 'meg' in lower:
        return 'MEG (rest)' if 'rest' in lower else 'MEG'

    return 'Others'


def normalize_answer(raw: str) -> str:
    if raw is None:
        return 'NR'
    s = strip_trailing_period(raw).replace('\u3000', ' ').strip()
    if not s:
        return 'NR'
    if s.lower() in ('nr', 'yes'):
        return 'NR'
    parts = re.split(r"\s*;\s*", s)
    out = [classify_one(p) for p in parts if p.strip()]
    return 'NR' if not out else '; '.join(out)


def process_file(path: Path) -> bool:
    try:
        with path.open('r', encoding='utf-8') as f:
            data = json.load(f)
    except Exception as e:
        print(f"ERROR reading {path}: {e}")
        return False

    ref = data.get('reference_cohort_and_imaging_part')
    if not isinstance(ref, dict):
        return False

    rci5 = ref.get('rci5_imaging_modality')
    if rci5 is None:
        return False

    raw = None
    if isinstance(rci5, dict):
        raw = rci5.get('answer')
    elif isinstance(rci5, str):
        raw = rci5
    else:
        return False

    new_val = normalize_answer(raw)

    # If no change, skip writing
    if isinstance(rci5, dict):
        if rci5.get('answer') == new_val:
            return False
        rci5['answer'] = new_val
    else:
        if rci5 == new_val:
            return False
        ref['rci5_imaging_modality'] = new_val

    try:
        with path.open('w', encoding='utf-8') as f:
            json.dump(data, f, ensure_ascii=False, indent=2)
            f.write('\n')
        print(f"UPDATED: {path}")
        return True
    except Exception as e:
        print(f"ERROR writing {path}: {e}")
        return False


def main():
    if not BASE.is_dir():
        print(f"No data dir: {BASE.resolve()}")
        return

    processed = updated = 0
    for author in BASE.iterdir():
        if not author.is_dir():
            continue
        de = author / 'DE'
        if not de.is_dir():
            continue
        for sub in de.iterdir():
            if not sub.is_dir() or not sub.name.startswith('json'):
                continue
            for f in sub.glob('DE_*.json'):
                name = f.name
                if re.search(r'(?i)human', name) is None:
                    continue  # only human/Human JSONs
                processed += 1
                if process_file(f):
                    updated += 1

    print(f"Done. processed={processed}, updated={updated}")


if __name__ == '__main__':
    main()
