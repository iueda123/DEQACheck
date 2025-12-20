#!/usr/bin/env python3
import json
import sys
import argparse
from pathlib import Path
from copy import deepcopy


CANONICAL_FILE = Path("DE_Author20XX_by_Someone_YYYYmmddHHMMSS.json")


QUESTION_KEYS = [
    "answer",
    "confidence_rating",
    "negative_answer_category",
    "reason",
    "supporting_text",
    "location",
]

# Mapping of variant sub-keys to canonical sub-keys (case/space variations)
SUBKEY_MAP = {
    "answer": "answer",
    "ans": "answer",
    "confidence rating": "confidence_rating",
    "confidence": "confidence_rating",
    "negative answer category": "negative_answer_category",
    "negative": "negative_answer_category",
    "reason": "reason",
    "supporting text": "supporting_text",
    "supporting": "supporting_text",
    "location": "location",
    "page line": "location",
}


def load_json(path: Path):
    text = path.read_text(encoding="utf-8")
    # Trim potential BOM
    text = text.lstrip("\ufeff")
    return json.loads(text)


def is_question_obj(obj) -> bool:
    if not isinstance(obj, dict):
        return False
    keys = set(obj.keys())
    return set(QUESTION_KEYS).issubset(keys)


def build_schema_descriptor(canonical: dict):
    """
    Produce a descriptor dict mirroring canonical structure, marking nodes as
    - "question" (expects QUESTION_KEYS dict)
    - "string" (expects string)
    - "object" (nested dict of fields)
    We only descend into dicts present in canonical.
    """
    def describe(node):
        if isinstance(node, dict):
            # question object?
            if set(QUESTION_KEYS).issubset(node.keys()):
                return {"type": "question"}
            # nested object: recurse
            return {
                "type": "object",
                "fields": {k: describe(v) for k, v in node.items()}
            }
        else:
            # strings (canonical uses strings for some leaves)
            return {"type": "string"}

    return describe(canonical)


def normalize_subkey_name(name: str) -> str:
    key = name.strip().lower().replace("_", " ")
    return SUBKEY_MAP.get(key, None)


def coerce_to_question(value):
    """Return a question-shaped dict from various inputs, preserving info."""
    q = {k: "" for k in QUESTION_KEYS}
    extras = {}

    if isinstance(value, dict):
        for k, v in value.items():
            mapped = normalize_subkey_name(k)
            if mapped in QUESTION_KEYS:
                # Coerce non-strings to strings for consistency
                if isinstance(v, (dict, list)):
                    q[mapped] = json.dumps(v, ensure_ascii=False)
                elif v is None:
                    q[mapped] = ""
                else:
                    q[mapped] = str(v)
            else:
                # unexpected subkey — preserve in extras
                extras[k] = v
    elif isinstance(value, (str, int, float)) or value is None:
        q["answer"] = "" if value is None else str(value)
    else:
        # list or other — stringify
        q["answer"] = json.dumps(value, ensure_ascii=False)

    if extras:
        suffix = ("\nEXTRAS: " + json.dumps(extras, ensure_ascii=False))
        q["reason"] = (q["reason"] + suffix) if q["reason"] else suffix.lstrip("\n")

    return q


def coerce_to_string(value):
    """Return a string from various inputs, preserving info where possible."""
    if isinstance(value, str):
        return value
    if isinstance(value, dict):
        # If dict resembles a question object, format a readable multi-line string
        parts = []
        # Accept both canonical and variant subkeys
        items = {}
        for k, v in value.items():
            mapped = normalize_subkey_name(k)
            if mapped:
                items[mapped] = v
            else:
                items.setdefault("extras", {})[k] = v
        label_map = {
            "answer": "Answer",
            "confidence_rating": "Confidence Rating",
            "negative_answer_category": "Negative Answer Category",
            "reason": "Reason",
            "supporting_text": "Supporting Text",
            "location": "Location",
        }
        for k in [
            "answer",
            "confidence_rating",
            "negative_answer_category",
            "reason",
            "supporting_text",
            "location",
        ]:
            if k in items and items[k] not in (None, ""):
                v = items[k]
                if not isinstance(v, str):
                    v = json.dumps(v, ensure_ascii=False)
                parts.append(f"{label_map[k]}: {v}")
        if "extras" in items and items["extras"]:
            parts.append("EXTRAS: " + json.dumps(items["extras"], ensure_ascii=False))
        return "\n".join(parts)
    # list or other
    return json.dumps(value, ensure_ascii=False)


def normalize_to_schema(source: dict, schema_desc: dict):
    """
    Create a new dict that exactly follows the canonical schema, pulling/mapping
    values from source and preserving any non-canonical data into general_notes.general_notes.
    """
    preserved_extras = []  # collect (path, data) for anything we can't place

    def key_norm_basic(s: str) -> str:
        # Basic normalization for mapping: lowercase, remove non-alnum, collapse variations
        s = s.lower()
        s = s.replace("-", "_").replace(" ", "_")
        # strip leading numeric section prefixes like '3_', '1_1_'
        i = 0
        n = len(s)
        while i < n and (s[i].isdigit() or s[i] in ("_", "-")):
            i += 1
        s = s[i:]
        # British/American
        s = s.replace("modelling", "modeling")
        # common shorthands
        s = s.replace("validation", "vldtn").replace("validate", "vldtn")
        # strip non-alnum/underscore
        return "".join(ch for ch in s if ch.isalnum() or ch == "_")

    # Alias maps per scope (dot-path) for parent field names
    PARENT_ALIAS = {
        "": {
            "studyidentification": "study_identification",
            "study_characteristics": "study_characteristics",
            "studycharacteristics": "study_characteristics",
            "referencecohortandimaging": "reference_cohort_and_imaging",
            "reference_cohort_imaging": "reference_cohort_and_imaging",
            "normativemodeling": "normative_modeling",
            "normative_modelling": "normative_modeling",
            "clinicalapplicationandanalysis": "clinical_application_and_analysis",
            "clinicalapplicationanalysis": "clinical_application_and_analysis",
            "clinical_application_analysis": "clinical_application_and_analysis",
            "generalnotes": "general_notes",
        },
        "study_identification": {
            "studyid": "study_id",
            "referencefilenames": "reference_file_names",
            "authorjournalyear": "author_journal_year",
            "paper_title": "title",
            "paper_title_full": "title",
        },
        "study_characteristics": {
            "studyobjective": "study_objective",
            "studydesign": "study_design",
            "studydesignother": "study_design_other",
        },
        "reference_cohort_and_imaging": {
            "dataset": "dataset_name",
            "datasetname": "dataset_name",
            "hcp_n": "hc_n",
            "hcn": "hc_n",
            "hc_n": "hc_n",
            "hcage": "hc_age",
            "hc_age": "hc_age",
            "hcsex": "hc_sex",
            "hc_sex": "hc_sex",
            "imagingmodality": "imaging_modality",
            "analysislevel": "analysis_level",
            "preprocessing": "preprocessing_pipeline",
            "preprocessingpipeline": "preprocessing_pipeline",
            "qualitychecking": "quality_checking",
            "qc": "quality_checking",
            "qualitycheckingdetail": "quality_checking_detail",
            "qcdetail": "quality_checking_detail",
            "siteeffecthandling": "site_effect_handling",
            "siteeffecthandlingdetail": "site_effect_handling_detail",
        },
        "normative_modeling": {
            "modelorigin": "model_origin",
            "modelorigindetail": "model_origin_detail",
            "modelingmethod": "modeling_method",
            "softwaretool": "software_tool",
            "responsevariable": "response_variable",
            "responsevariables": "response_variable",
            "predictorvariables": "predictor_variables",
            "predictoreffects": "predictor_effects",
            "nmvldtnhandlens": "nm_vldtn_handle_ns",
            "nm_vldtn_handle_nuisance": "nm_vldtn_handle_ns",
            "nmvldtnsamedomainnonindep": "nm_vldtn_same_domain_nonindep",
            "nmvldtnsamedomainindep": "nm_vldtn_same_domain_indep",
            "nmvldtndiffdomain": "nm_vldtn_diff_domain",
        },
        "clinical_application_and_analysis": {
            "clinicaldataset": "clinical_dataset",
            "diseasesstudied": "diseases_studied",
            "clinicalgroupsn": "clinical_groups_n",
            "clinicalgroupsage": "clinical_groups_age",
            "clinicalgroupssex": "clinical_groups_sex",
            "deviationmetric": "deviation_metric",
            "associationanalysis": "association_analysis",
            "keyfindingsbrief": "key_findings_brief",
            "keyfindingsshort": "key_findings_brief",
            "keyfindingsdetailed": "key_findings_detailed",
            "keyfindingsdetail": "key_findings_detailed",
            "keylimitations": "key_limitations",
            "applicationnotes": "application_notes",
        },
        "general_notes": {
            "notes": "general_notes",
            "generalnotes": "general_notes",
        },
    }

    def remap_parent_keys(src_node, path, expected_fields=None):
        if not isinstance(src_node, dict):
            return src_node
        scope = "".join(path[0:1]) if len(path) == 1 else (path[0] if path else "")
        # Determine scope key for alias table
        scope_key = "" if not path else (path[-1] if len(path) == 1 else path[0])
        # Build alias map for this level
        alias_table = PARENT_ALIAS.get(scope_key, {})
        expected_norm = set()
        if expected_fields:
            expected_norm = {key_norm_basic(k): k for k in expected_fields}
        out = {}
        used = set()
        for k, v in src_node.items():
            kn = key_norm_basic(k)
            target = alias_table.get(kn)
            if not target:
                # try exact canonical match on normalized form
                for cand in alias_table.values():
                    if key_norm_basic(cand) == kn:
                        target = cand
                        break
            if not target and expected_norm:
                # If key matches an expected canonical child at this level, use it
                if kn in expected_norm:
                    target = expected_norm[kn]
            if target:
                # If collision, keep first occurrence and preserve extra in list
                if target in out:
                    # preserve duplicate under extras
                    used.add(k)
                    continue
                out[target] = v
                used.add(k)
            else:
                out[k] = v
        return out

    def transform(node_desc, src_node, path):
        t = node_desc["type"]
        if t == "object":
            out = {}
            fields = node_desc["fields"]
            # Attempt to remap parent-level keys to canonical ones before traversing
            if isinstance(src_node, dict):
                src_node = remap_parent_keys(src_node, path, expected_fields=set(fields.keys()))
            else:
                # If this object has exactly one child, and src_node is a scalar, map directly
                if len(fields) == 1 and src_node is not None:
                    only_key = next(iter(fields.keys()))
                    child_desc = fields[only_key]
                    out[only_key] = transform(child_desc, src_node, path + [only_key])
                    # Fill other expected keys (none) and return
                    # Identify non-dict source as preserved extra? Not necessary since we mapped it.
                    return out
            # Build child nodes in canonical order
            for k in fields.keys():
                child_desc = fields[k]
                child_src = None
                if isinstance(src_node, dict) and k in src_node:
                    child_src = src_node[k]
                out[k] = transform(child_desc, child_src, path + [k])
            # Identify any unexpected keys at this level to preserve
            if isinstance(src_node, dict):
                for k in src_node.keys():
                    if k not in fields:
                        preserved_extras.append((".".join(path + [k]), src_node[k]))
            return out
        elif t == "question":
            return coerce_to_question(src_node)
        elif t == "string":
            return coerce_to_string(src_node)
        else:
            # Fallback: stringify
            return coerce_to_string(src_node)

    # Start transform
    normalized = transform(schema_desc, source, [])

    # Attempt to lift preserved extras back into canonical slots when possible
    remaining_extras = []
    for p, v in preserved_extras:
        pn = key_norm_basic(p)
        try:
            # Handle top-level clinical_application alias extras
            if pn == key_norm_basic("clinical_application_and_analysis") and isinstance(v, dict):
                sect = normalized.get("clinical_application_and_analysis")
                if isinstance(sect, dict):
                    # Map each child
                    for ck, cv in v.items():
                        ckn = key_norm_basic(ck)
                        # expected child keys of this section
                        mapping = {
                            key_norm_basic("clinical_dataset"): "clinical_dataset",
                            key_norm_basic("diseases_studied"): "diseases_studied",
                            key_norm_basic("clinical_groups_n"): "clinical_groups_n",
                            key_norm_basic("clinical_groups_age"): "clinical_groups_age",
                            key_norm_basic("clinical_groups_sex"): "clinical_groups_sex",
                            key_norm_basic("deviation_metric"): "deviation_metric",
                            key_norm_basic("association_analysis"): "association_analysis",
                            key_norm_basic("key_findings_brief"): "key_findings_brief",
                            key_norm_basic("key_findings_detailed"): "key_findings_detailed",
                            key_norm_basic("key_limitations"): "key_limitations",
                            key_norm_basic("application_notes"): "application_notes",
                        }
                        if ckn in mapping:
                            dest = mapping[ckn]
                            # Question-type vs string fields
                            if isinstance(sect.get(dest), dict):
                                sect[dest] = coerce_to_question(cv)
                            else:
                                sect[dest] = coerce_to_string(cv)
                    # merged successfully; skip adding to extras
                    continue

            # Handle top-level general_notes extras
            if pn == key_norm_basic("general_notes") and isinstance(v, (str, dict, list)):
                gn = normalized.setdefault("general_notes", {}).setdefault("general_notes", "")
                merged = coerce_to_string(v)
                normalized["general_notes"]["general_notes"] = (gn + "\n\n" + merged).strip() if gn else merged
                continue

            # Handle study_identification child extras such as DOI with numbered prefixes
            if pn.startswith(key_norm_basic("study_identification") + "."):
                child = pn.split(".", 1)[1]
                ckn = key_norm_basic(child)
                dest_map = {key_norm_basic(k): k for k in [
                    "study_id", "reference_file_names", "author_journal_year", "title", "doi"
                ]}
                if ckn in dest_map:
                    normalized["study_identification"][dest_map[ckn]] = coerce_to_string(v)
                    continue

            # Handle reference_cohort_and_imaging child extras (numbered variants)
            if pn.startswith(key_norm_basic("reference_cohort_and_imaging") + "."):
                child = pn.split(".", 1)[1]
                ckn = key_norm_basic(child)
                sect = normalized.get("reference_cohort_and_imaging", {})
                child_map = {
                    key_norm_basic("dataset_name"): "dataset_name",
                    key_norm_basic("hc_n"): "hc_n",
                    key_norm_basic("hc_age"): "hc_age",
                    key_norm_basic("hc_sex"): "hc_sex",
                    key_norm_basic("imaging_modality"): "imaging_modality",
                    key_norm_basic("analysis_level"): "analysis_level",
                    key_norm_basic("preprocessing_pipeline"): "preprocessing_pipeline",
                    key_norm_basic("quality_checking"): "quality_checking",
                    key_norm_basic("quality_checking_detail"): "quality_checking_detail",
                    key_norm_basic("site_effect_handling"): "site_effect_handling",
                    key_norm_basic("site_effect_handling_detail"): "site_effect_handling_detail",
                }
                if ckn in child_map:
                    key = child_map[ckn]
                    sect[key] = coerce_to_question(v)
                    normalized["reference_cohort_and_imaging"] = sect
                    continue

            # Handle normative_modeling nm_vldtn* extras (numbered variants)
            if pn.startswith(key_norm_basic("normative_modeling") + "."):
                child = pn.split(".", 1)[1]
                ckn = key_norm_basic(child)
                sect = normalized.get("normative_modeling", {})
                nm_map = {
                    key_norm_basic("model_origin"): "model_origin",
                    key_norm_basic("model_origin_detail"): "model_origin_detail",
                    key_norm_basic("modeling_method"): "modeling_method",
                    key_norm_basic("software_tool"): "software_tool",
                    key_norm_basic("response_variable"): "response_variable",
                    key_norm_basic("predictor_variables"): "predictor_variables",
                    key_norm_basic("predictor_effects"): "predictor_effects",
                    key_norm_basic("nm_vldtn_handle_ns"): "nm_vldtn_handle_ns",
                    key_norm_basic("nm_vldtn_same_domain_nonindep"): "nm_vldtn_same_domain_nonindep",
                    key_norm_basic("nm_vldtn_same_domain_indep"): "nm_vldtn_same_domain_indep",
                    key_norm_basic("nm_vldtn_diff_domain"): "nm_vldtn_diff_domain",
                }
                if ckn in nm_map:
                    key = nm_map[ckn]
                    # Determine if question or string
                    if isinstance(sect.get(key), dict):
                        sect[key] = coerce_to_question(v)
                    else:
                        sect[key] = coerce_to_string(v)
                    normalized["normative_modeling"] = sect
                    continue

            # If not lifted, keep for NONCANONICAL_DATA
            remaining_extras.append((p, v))
        except Exception:
            # If something goes wrong, keep the extra as-is
            remaining_extras.append((p, v))

    # Inject remaining extras into general_notes.general_notes
    if remaining_extras:
        gn = normalized.setdefault("general_notes", {}).setdefault("general_notes", "")
        lines = []
        lines.append("NONCANONICAL_DATA:")
        for p, v in remaining_extras:
            try:
                v_str = json.dumps(v, ensure_ascii=False)
            except Exception:
                v_str = str(v)
            lines.append(f"- {p}: {v_str}")
        extra_block = "\n" + "\n".join(lines)
        if gn:
            normalized["general_notes"]["general_notes"] = gn + "\n\n" + extra_block
        else:
            normalized["general_notes"]["general_notes"] = extra_block.lstrip("\n")

    # Final safeguard: if general_notes.general_notes is empty or 'null',
    # try to source it directly from top-level original keys matching general_notes
    try:
        gn_val = normalized.get("general_notes", {}).get("general_notes")
        if (gn_val is None) or (isinstance(gn_val, str) and gn_val.strip().lower() == "null") or (isinstance(gn_val, str) and not gn_val.strip()):
            for k, v in (source.items() if isinstance(source, dict) else []):
                if key_norm_basic(k) == key_norm_basic("general_notes"):
                    normalized.setdefault("general_notes", {})["general_notes"] = coerce_to_string(v)
                    break
    except Exception:
        pass

    return normalized


def main():
    parser = argparse.ArgumentParser(description="Normalize DE JSON files to canonical schema")
    parser.add_argument("--repair-from-bak", action="store_true", help="Read from .bak originals if present to restore any lost values")
    args = parser.parse_args()

    if not CANONICAL_FILE.exists():
        print(f"Canonical file not found: {CANONICAL_FILE}", file=sys.stderr)
        sys.exit(1)

    canonical = load_json(CANONICAL_FILE)
    schema_desc = build_schema_descriptor(canonical)

    # Gather targets (all json files recursively except the canonical template)
    root = Path('.')
    targets = [
        p for p in root.rglob('*.json')
        if p.resolve() != CANONICAL_FILE.resolve()
    ]

    changed = 0
    for path in targets:
        # Choose source: optionally prefer .bak for repair mode
        src_path = path
        if args.repair_from_bak:
            bak = path.with_suffix(path.suffix + '.bak')
            if bak.exists():
                src_path = bak
        try:
            src = load_json(src_path)
        except Exception as e:
            print(f"SKIP (invalid JSON): {src_path} :: {e}")
            continue

        normalized = normalize_to_schema(src, schema_desc)

        # If normalized equals source, skip writing to avoid touching
        try:
            if normalized == src:
                print(f"OK (already canonical): {path}")
                continue
        except Exception:
            # Non-comparable (e.g., default dict) — proceed to write
            pass

        # Backup original next to file
        backup = path.with_suffix(path.suffix + '.bak')
        if not backup.exists():
            # Always back up the current on-disk JSON, not the src we loaded (which may be .bak)
            try:
                current = load_json(path)
            except Exception:
                current = src
            backup.write_text(json.dumps(current, ensure_ascii=False, indent=2) + "\n", encoding='utf-8')

        # Write normalized
        path.write_text(json.dumps(normalized, ensure_ascii=False, indent=2) + "\n", encoding='utf-8')
        print(f"UPDATED: {path}")
        changed += 1

    print(f"Done. Updated {changed} file(s).")


if __name__ == '__main__':
    main()
