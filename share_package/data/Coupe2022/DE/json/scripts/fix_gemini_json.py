#!/usr/bin/env python3
import json
from pathlib import Path

TEMPLATE_FILE = Path("DE_Author20XX_by_Someone_YYYYmmddHHMMSS_for_v10.json")
TARGET_FILE = Path("DE_Coupe2022_by_gemini_20251122023420.json")
BACKUP_FILE = Path("DE_Coupe2022_by_gemini_20251122023420_backup.json")

def load_json(path: Path):
    with path.open("r", encoding="utf-8") as f:
        return json.load(f)

def save_json(path: Path, data):
    with path.open("w", encoding="utf-8") as f:
        json.dump(data, f, ensure_ascii=False, indent=2)
        f.write("\n")

def main():
    if not TEMPLATE_FILE.exists():
        raise SystemExit(f"Template file not found: {TEMPLATE_FILE}")
    if not TARGET_FILE.exists():
        raise SystemExit(f"Target file not found: {TARGET_FILE}")

    template = load_json(TEMPLATE_FILE)
    src = load_json(TARGET_FILE)

    # Mapping for normative modeling keys that differ between files
    nm_map = {
        "nm1_model_origin": "nm1_model_origin",
        "nm2_model_origin_detail": "nm2_model_origin_detail",
        "nm3_modeling_method": "nm3_modeling_method",
        # plural -> singular
        "nm4_software_tools": "nm4_software_tool",
        "nm5_response_variable": "nm5_response_variable",
        "nm6_predictor_variables": "nm6_predictor_variables",
        "nm7_predictor_effects": "nm7_predictor_effects",
        # long descriptive -> abbreviated keys in v10
        "nm8_normative_modeling_validation_with_handling_nuisance_structure": "nm8_nm_vldtn_handle_ns",
        "nm9_normative_modeling_validation_strategy_using_same_domain_non_independent_dataset": "nm9_nm_vldtn_same_domain_nonindep",
        "nm10_normative_modeling_validation_strategy_using_same_domain_independent_dataset": "nm10_nm_vldtn_same_domain_indep",
        "nm11_nm_validation_strategy_using_different_domain_dataset": "nm11_nm_vldtn_diff_domain",
    }

    caa_map = {
        "caa1_clinical_dataset": "caa1_clinical_dataset",
        "caa2_diseases_studied": "caa2_diseases_studied",
        # name alignment
        "caa3_n_of_clinical_groups": "caa3_clinical_groups_n",
        "caa4_age_of_clinical_groups": "caa4_clinical_groups_age",
        # typo fix: _group_groups -> _groups
        "caa5_sex_of_clinical_group_groups": "caa5_clinical_groups_sex",
        "caa6_deviation_metric": "caa6_deviation_metric",
        "caa7_association_analysis": "caa7_association_analysis",
        "caa8_key_findings_brief": "caa8_key_findings_brief",
        "caa9_key_findings_detailed": "caa9_key_findings_detailed",
        "caa10_key_limitations": "caa10_key_limitations",
        "caa11_application_notes": "caa11_application_notes",
    }

    # Build output according to template structure, reusing values when available
    out = {
        "study_identification_part": {
            "si1_study_id": src.get("si1_study_id", ""),
            "si2_reference_file_names": src.get("si2_reference_file_names", ""),
            "si3_author_journal_year": src.get("si3_author_journal_year", ""),
            "si4_title": src.get("si4_title", ""),
            "si5_doi": src.get("si5_doi", ""),
        },
        "study_characteristics_part": {
            "sc1_study_objective": src.get("sc1_study_objective", ""),
            "sc2_study_design": src.get("sc2_study_design", ""),
            "sc3_study_design_other": src.get("sc3_study_design_other", ""),
        },
        "reference_cohort_and_imaging_part": {
            "rci1_dataset_name": src.get("rci1_dataset_name", {"answer":"","confidence_rating":"","reason":"","supporting_text":"","location":""}),
            "rci2_hc_n": src.get("rci2_hc_n", {"answer":"","confidence_rating":"","reason":"","supporting_text":"","location":""}),
            "rci3_hc_age": src.get("rci3_hc_age", {"answer":"","confidence_rating":"","reason":"","supporting_text":"","location":""}),
            "rci4_hc_sex": src.get("rci4_hc_sex", {"answer":"","confidence_rating":"","reason":"","supporting_text":"","location":""}),
            "rci5_imaging_modality": src.get("rci5_imaging_modality", {"answer":"","confidence_rating":"","reason":"","supporting_text":"","location":""}),
            "rci6_analysis_level": src.get("rci6_analysis_level", {"answer":"","supporting_text":"","location":""}),
            "rci7_preprocessing_pipeline": src.get("rci7_preprocessing_pipeline", {"answer":"","confidence_rating":"","reason":"","supporting_text":"","location":""}),
            "rci8_quality_checking": src.get("rci8_quality_checking", ""),
            "rci9_quality_checking_detail": src.get("rci9_quality_checking_detail", {"answer":"","confidence_rating":"","reason":"","supporting_text":"","location":""}),
            "rci10_site_effect_handling": src.get("rci10_site_effect_handling", ""),
            "rci11_site_effect_handling_detail": src.get("rci11_site_effect_handling_detail", {"answer":"","confidence_rating":"","reason":"","supporting_text":"","location":""}),
        },
        "normative_modeling_part": {},
        "clinical_application_and_analysis_part": {},
        "general_note_part": {
            "gn1_general_notes": src.get("gn1_general_notes", ""),
        },
    }

    # Fill normative modeling part using mapping
    nm_out = {}
    for src_key, dst_key in nm_map.items():
        if src_key in src:
            nm_out[dst_key] = src[src_key]
    # Ensure any missing expected nm keys from template exist with empty shape
    # Discover expected nm keys from template
    tmpl_nm = template.get("normative_modeling_part", {})
    for k, v in tmpl_nm.items():
        if k not in nm_out:
            # Preserve the expected shape: string or object
            if isinstance(v, dict):
                # create empty object with same subkeys if any
                if v:
                    nm_out[k] = {subk: "" for subk in v.keys()}
                else:
                    nm_out[k] = {}
            else:
                nm_out[k] = ""
    out["normative_modeling_part"] = nm_out

    # Fill clinical application and analysis part
    caa_out = {}
    for src_key, dst_key in caa_map.items():
        if src_key in src:
            caa_out[dst_key] = src[src_key]

    # Ensure missing caa keys exist with empty shape based on template
    tmpl_caa = template.get("clinical_application_and_analysis_part", {})
    for k, v in tmpl_caa.items():
        if k not in caa_out:
            if isinstance(v, dict):
                if v:
                    caa_out[k] = {subk: "" for subk in v.keys()}
                else:
                    caa_out[k] = {}
            else:
                caa_out[k] = ""
    out["clinical_application_and_analysis_part"] = caa_out

    # Sanity: ensure top-level keys match template keys
    expected_top = list(template.keys())
    out = {k: out[k] for k in expected_top if k in out}

    # Write backup only if not already present or differs
    try:
        if not BACKUP_FILE.exists():
            save_json(BACKUP_FILE, src)
    except Exception:
        # Non-fatal; continue
        pass

    save_json(TARGET_FILE, out)

if __name__ == "__main__":
    main()

