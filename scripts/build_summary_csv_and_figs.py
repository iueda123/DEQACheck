#!/usr/bin/env python3
import json, glob, os, re, math, collections, statistics
from pathlib import Path

def load_json(path):
    try:
        with open(path, 'r', encoding='utf-8') as f:
            return json.load(f)
    except Exception:
        return None

def tokenize_semicolon(s):
    return [t.strip() for t in re.split(r";", s) if t and t.strip()]

def modality_category(text):
    low = text.lower()
    if ('t1' in low and 'mri' in low) or 'structural' in low or 'smri' in low:
        return 'T1w MRI'
    if 't2' in low or 'flair' in low:
        return 'T2w MRI'
    if 'fmri' in low or 'bold' in low:
        return 'fMRI'
    if 'diffusion' in low or 'dmri' in low or 'dti' in low or 'dwi' in low:
        return 'dMRI'
    if 'pet' in low:
        return 'PET'
    if 'eeg' in low:
        return 'EEG'
    if 'meg' in low:
        return 'MEG'
    return 'Others'

def parse_phase(line):
    m = re.search(r"Phase:\s*([^|]+)", line)
    return m.group(1).strip() if m else 'Uninvestigated'

def parse_n_values(text):
    # returns list of (phase, N)
    out = []
    for ln in [l for l in str(text).split('\n') if l.strip()]:
        phase = parse_phase(ln)
        m = re.search(r"\bN:\s*(\d+)", ln)
        if m:
            try:
                out.append((phase, int(m.group(1))))
                continue
            except Exception:
                pass
        # fallback: first integer with 2+ digits
        m2 = re.search(r"\b(\d{2,})\b", ln)
        if m2:
            try:
                out.append((phase, int(m2.group(1))))
            except Exception:
                pass
    return out

def parse_age_records(text):
    # returns list of dict per line: phase, mean, sd, min, max
    out = []
    for ln in [l for l in str(text).split('\n') if l.strip()]:
        rec = {'phase': parse_phase(ln), 'mean': None, 'sd': None, 'min': None, 'max': None}
        def pick(key):
            m = re.search(rf"{key}:\s*(NR|NA|\-?\d+(?:\.\d+)?)", ln, re.I)
            if m and m.group(1).upper() not in ('NR','NA'):
                try:
                    return float(m.group(1))
                except Exception:
                    return None
            return None
        rec['mean'] = pick('mean')
        rec['sd']   = pick('sd')
        rec['min']  = pick('min')
        rec['max']  = pick('max')
        out.append(rec)
    return out

def parse_sex_records(text):
    # returns list of (phase, female_pct)
    out = []
    for ln in [l for l in str(text).split('\n') if l.strip()]:
        phase = parse_phase(ln)
        fm = re.search(r"F\s*\d*\s*\((\d+(?:\.\d+)?)%\)", ln)
        if fm:
            try:
                out.append((phase, float(fm.group(1))))
                continue
            except Exception:
                pass
        mm = re.search(r"M\s*\d*\s*\((\d+(?:\.\d+)?)%\)", ln)
        if mm:
            try:
                out.append((phase, 100.0 - float(mm.group(1))))
            except Exception:
                pass
    return out

def main():
    files = glob.glob('share_package/data/*/DE/json/*human*.json') + glob.glob('share_package/data/*/DE/json/*Human*.json')
    records = []  # flattened per (file, line)
    datasets_counter = collections.Counter()
    modality_counter = collections.Counter()
    origin_counter = collections.Counter()
    disease_counter = collections.Counter()
    findings_terms = collections.Counter()

    stop = set('the a an and or of for to in on with without among versus vs from at as by be is are was were been being into over under across within using include includes included including not no low high higher lower have has had were it its their his her our your they them we us this that these those may might should can could will would do did done such which than per among across into'.split())
    word_re = re.compile(r"[A-Za-z][A-Za-z\-']+")

    for p in files:
        j = load_json(p)
        if not j: continue
        # Expected layout: share_package/data/<AuthorYear>/DE/json/filename.json
        try:
            authorYear = Path(p).parents[2].name
        except Exception:
            authorYear = Path(p).name
        ref = j.get('reference_cohort_and_imaging_part') or {}
        # Dataset
        dsnode = ref.get('rci1_dataset_name')
        dsval = dsnode.get('answer','') if isinstance(dsnode,dict) else (dsnode or '')
        if isinstance(dsval,str) and dsval.strip():
            ds_toks = tokenize_semicolon(dsval)
            for t in ds_toks:
                datasets_counter[t] += 1
        # N
        nn = ref.get('rci2_hc_n')
        nval = nn.get('answer','') if isinstance(nn,dict) else (nn or '')
        n_list = parse_n_values(nval)
        # Age
        an = ref.get('rci3_hc_age')
        aval = an.get('answer','') if isinstance(an,dict) else (an or '')
        age_list = parse_age_records(aval)
        # Sex
        sn = ref.get('rci4_hc_sex')
        sval = sn.get('answer','') if isinstance(sn,dict) else (sn or '')
        sex_list = parse_sex_records(sval)
        # Modality
        mn = ref.get('rci5_imaging_modality')
        mval = mn.get('answer','') if isinstance(mn,dict) else (mn or '')
        modality_list = []
        if isinstance(mval,str) and mval.strip():
            for t in [tt.strip() for tt in re.split(r";|,", mval) if tt.strip()]:
                cat = modality_category(t)
                modality_counter[cat] += 1
                modality_list.append(cat)
        # Origin
        nm = j.get('normative_modeling_part') or {}
        on = nm.get('nm1_model_origin')
        oval = on.get('answer','') if isinstance(on,dict) else (on or '')
        if isinstance(oval,str) and oval.strip():
            origin_counter[oval.strip()] += 1
        # Disease
        caa = j.get('clinical_application_and_analysis_part') or {}
        dn = caa.get('caa2_diseases_studied') or caa.get('diseases_studied')
        dval = dn.get('answer','') if isinstance(dn,dict) else (dn or '')
        diseases = tokenize_semicolon(dval) if isinstance(dval,str) else []
        for d in diseases:
            disease_counter[d] += 1
        # Findings keywords（簡易）
        fv = caa.get('caa8_key_findings_brief')
        fval = fv if isinstance(fv,str) else (fv.get('answer','') if isinstance(fv,dict) else '')
        if isinstance(fval,str) and fval.strip():
            for w in word_re.findall(fval.lower()):
                w = w.strip("-'")
                if not w or w in stop or len(w) <= 2:
                    continue
                findings_terms[w] += 1

        # Flatten records per available lines (align by phase best-effort)
        phases_seen = set([ph for ph,_ in n_list] + [r['phase'] for r in age_list] + [ph for ph,_ in sex_list])
        if not phases_seen:
            phases_seen = {'Uninvestigated'}
        for ph in phases_seen:
            rec = {
                'authorYear': authorYear,
                'phase': ph,
                'N': None,
                'age_mean': None,
                'age_sd': None,
                'age_min': None,
                'age_max': None,
                'female_pct': None,
                'modality': ';'.join(sorted(set(modality_list))) if modality_list else '',
                'origin': oval.strip() if isinstance(oval,str) else '',
                'dataset': ';'.join(tokenize_semicolon(dsval)) if isinstance(dsval,str) else '',
                'disease': ';'.join(diseases),
            }
            # pick phase-matching values
            for ph2, n in n_list:
                if n is not None and (ph2 == ph or rec['N'] is None):
                    rec['N'] = n
            best_age = None
            for a in age_list:
                if a['phase'] == ph:
                    best_age = a; break
            if not best_age and age_list:
                best_age = age_list[0]
            if best_age:
                rec['age_mean'] = best_age['mean']
                rec['age_sd']   = best_age['sd']
                rec['age_min']  = best_age['min']
                rec['age_max']  = best_age['max']
            best_sex = None
            for ph2, fp in sex_list:
                if ph2 == ph:
                    best_sex = fp; break
            if best_sex is None and sex_list:
                best_sex = sex_list[0][1]
            rec['female_pct'] = best_sex
            records.append(rec)

    # Write CSVs for analysis reuse
    outdir = Path('doc/figs'); outdir.mkdir(parents=True, exist_ok=True)
    csv_path = outdir / 'records.csv'
    cols = ['authorYear','phase','N','age_mean','age_sd','age_min','age_max','female_pct','modality','origin','dataset','disease']
    with open(csv_path,'w',encoding='utf-8') as f:
        f.write('\t'.join(cols)+'\n')
        for r in records:
            f.write('\t'.join(['' if r[c] is None else str(r[c]) for c in cols])+'\n')

    # Try to plot figures with matplotlib
    try:
        import matplotlib.pyplot as plt
        import numpy as np
    except Exception as e:
        # If plotting is unavailable, finish after CSV generation
        print('CSV written, but plotting libraries unavailable:', e)
        return

    # Helper: save fig
    def savefig(name):
        plt.tight_layout()
        plt.savefig(outdir / name, dpi=160)
        plt.close()

    # Figure 1: N histogram (log10)
    Ns = [r['N'] for r in records if isinstance(r.get('N'), int) and r['N']>0]
    if Ns:
        vals = np.log10(Ns)
        plt.figure(figsize=(6,4))
        plt.hist(vals, bins=30, color='#4e79a7', alpha=0.85)
        plt.xlabel('log10(N)')
        plt.ylabel('Count')
        plt.title('Distribution of Sample Size (N)')
        savefig('fig01_n_hist.png')

    # Figure 2: Age mean vs sd (color=modality, size ~ N)
    mean_sd = [(r['age_mean'], r['age_sd'], (r['modality'] or 'Others').split(';')[0], r['N'] or 100) for r in records if r['age_mean'] is not None and r['age_sd'] is not None]
    if mean_sd:
        cats = ['T1w MRI','fMRI','dMRI','T2w MRI','PET','EEG','MEG','Others']
        cmap = {c:col for c,col in zip(cats, ['#4e79a7','#f28e2b','#59a14f','#e15759','#76b7b2','#edc948','#b07aa1','#bab0ac'])}
        xs = [x for x,_,_,_ in mean_sd]
        ys = [y for _,y,_,_ in mean_sd]
        cs = [cmap.get(cat, '#bab0ac') for _,_,cat,_ in mean_sd]
        ss = [max(20, min(300, n**0.5)) for *_, n in mean_sd]
        plt.figure(figsize=(6,4))
        plt.scatter(xs, ys, s=ss, c=cs, alpha=0.7, edgecolor='none')
        plt.xlabel('Age Mean (Years)')
        plt.ylabel('Age SD (Years)')
        plt.title('Age Mean vs SD (Size ~ N)')
        savefig('fig02_age_scatter.png')

    # Figure 3: Female% histogram
    Fp = [r['female_pct'] for r in records if r['female_pct'] is not None]
    if Fp:
        plt.figure(figsize=(6,4))
        plt.hist(Fp, bins=20, color='#e15759', alpha=0.85)
        plt.axvline(50, color='k', linestyle='--', linewidth=1)
        plt.xlabel('Female Percentage (%)')
        plt.ylabel('Count')
        plt.title('Distribution of Female Percentage')
        savefig('fig03_female_hist.png')

    # Figure 4: Modality x Disease heatmap (top diseases)
    top_dis = [d for d,_ in disease_counter.most_common(10)]
    cats = ['T1w MRI','fMRI','dMRI','T2w MRI','PET','EEG','MEG','Others']
    mat = [[0 for _ in top_dis] for _ in cats]
    for r in records:
        ms = r['modality'].split(';') if r['modality'] else ['Others']
        ds = r['disease'].split(';') if r['disease'] else []
        for m in ms:
            if not m: m='Others'
            if m not in cats: m='Others'
            mi = cats.index(m)
            for d in ds:
                if d in top_dis:
                    dj = top_dis.index(d)
                    mat[mi][dj] += 1
    if sum(sum(row) for row in mat) > 0:
        import numpy as np
        plt.figure(figsize=(max(6, 0.7*len(top_dis)+2), 4))
        arr = np.array(mat)
        plt.imshow(arr, aspect='auto', cmap='Blues')
        plt.xticks(range(len(top_dis)), top_dis, rotation=45, ha='right')
        plt.yticks(range(len(cats)), cats)
        plt.colorbar(label='Count')
        plt.title('Modality × Disease (Top)')
        savefig('fig04_modality_disease_heatmap.png')

    # Figure 5: Top Datasets bar
    top_ds = datasets_counter.most_common(15)
    if top_ds:
        labels = [k for k,_ in top_ds]
        counts = [v for _,v in top_ds]
        plt.figure(figsize=(8, max(4, 0.4*len(labels))) )
        y = list(range(len(labels)))[::-1]
        plt.barh(y, counts, color='#59a14f')
        plt.yticks(y, labels)
        plt.xlabel('Count')
        plt.title('Top Datasets (Count)')
        savefig('fig05_top_datasets.png')

    # Figure 6: Origin composition
    if origin_counter:
        labels = list(origin_counter.keys())
        sizes = [origin_counter[k] for k in labels]
        plt.figure(figsize=(5,5))
        plt.pie(sizes, labels=labels, autopct='%1.0f%%', startangle=90, colors=['#4e79a7','#f28e2b','#bab0ac','#59a14f'])
        plt.title('Model Origin')
        savefig('fig06_origin_pie.png')

    # Figure 7: Findings keywords top bar
    top_kw = findings_terms.most_common(20)
    if top_kw:
        labels = [k for k,_ in top_kw]
        counts = [v for _,v in top_kw]
        plt.figure(figsize=(8, max(4, 0.4*len(labels))) )
        y = list(range(len(labels)))[::-1]
        plt.barh(y, counts, color='#b07aa1')
        plt.yticks(y, labels)
        plt.xlabel('Frequency')
        plt.title('Findings Keywords (Top)')
        savefig('fig07_findings_keywords.png')

    # Figure 8: Dataset x Modality heatmap (top datasets)
    top_labels = [k for k,_ in datasets_counter.most_common(12)]
    if top_labels:
        cats = ['T1w MRI','fMRI','dMRI','T2w MRI','PET','EEG','MEG','Others']
        mat = [[0 for _ in cats] for _ in top_labels]
        for r in records:
            ds = r['dataset'].split(';') if r['dataset'] else []
            ms = r['modality'].split(';') if r['modality'] else ['Others']
            for d in ds:
                if d in top_labels:
                    di = top_labels.index(d)
                    for m in ms:
                        if m not in cats: m='Others'
                        mj = cats.index(m)
                        mat[di][mj] += 1
        import numpy as np
        if sum(sum(row) for row in mat) > 0:
            plt.figure(figsize=(8, max(4, 0.5*len(top_labels))))
            arr = np.array(mat)
            plt.imshow(arr, aspect='auto', cmap='Greens')
            plt.yticks(range(len(top_labels)), top_labels)
            plt.xticks(range(len(cats)), cats, rotation=30, ha='right')
            plt.colorbar(label='Count')
            plt.title('Dataset × Modality (Top Datasets)')
            savefig('fig08_dataset_modality_heatmap.png')

    # Figure 9: Modality trend by publication year (line chart)
    # Extract year from authorYear and count unique (authorYear, modality) per year
    cats_order = ['T1w MRI','fMRI','dMRI','T2w MRI','PET','EEG','MEG','Others']
    year_mod_set = collections.defaultdict(set)  # year -> set of (authorYear, modality)
    years_all = set()
    for r in records:
        ay = r.get('authorYear') or ''
        m = re.search(r'(\d{4})', ay)
        if not m:
            continue
        year = int(m.group(1))
        years_all.add(year)
        mods = r.get('modality','').split(';') if r.get('modality') else []
        if not mods:
            mods = ['Others']
        for mod in mods:
            mod = mod or 'Others'
            if mod not in cats_order:
                mod = 'Others'
            year_mod_set[year].add((ay, mod))
    if years_all:
        years_sorted = sorted(years_all)
        plt.figure(figsize=(8,4))
        color_map = {c:col for c,col in zip(cats_order, ['#4e79a7','#f28e2b','#59a14f','#e15759','#76b7b2','#edc948','#b07aa1','#bab0ac'])}
        # cumulative totals per modality (unique studies across all years)
        mod_to_studies = collections.defaultdict(set)
        for y, pairs in year_mod_set.items():
            for ay, mcat in pairs:
                if mcat not in cats_order:
                    mcat = 'Others'
                mod_to_studies[mcat].add(ay)
        mod_totals = {m: len(s) for m, s in mod_to_studies.items()}
        for cat in cats_order:
            ys = []
            for y in years_sorted:
                cnt = sum(1 for (ay, mcat) in year_mod_set.get(y, set()) if mcat == cat)
                ys.append(cnt)
            if any(v>0 for v in ys):
                label = f"{cat} (n={mod_totals.get(cat, 0)})"
                plt.plot(years_sorted, ys, marker='o', label=label, color=color_map.get(cat, None))
        plt.xlabel('Publication year')
        plt.ylabel('Count (unique studies)')
        # Title Case + optional line break for readability
        title_main = 'Trends in the Number of Normative Modeling Research Reports'
        title_sub  = '(by Modality)'
        plt.title(f"{title_main}\n{title_sub}")
        # annotate study period inside the axes (top-right)
        period = 'Study period: Jan 2005–Mar 2025'
        ax = plt.gca()
        # place period at top-left inside the axes
        ax.text(0.01, 0.98, period, transform=ax.transAxes, ha='left', va='top', fontsize=9, color='#444')
        # legend: move to upper-left (slightly below the top to avoid overlap with the period text)
        plt.legend(fontsize='small', ncol=2, loc='upper left', bbox_to_anchor=(0.01, 0.88), frameon=False)
        savefig('fig09_modality_trend_by_year.png')

    print('Wrote:', csv_path)
    for fn in sorted(os.listdir(outdir)):
        if fn.endswith('.png'):
            print('Figure:', fn)

if __name__ == '__main__':
    main()
