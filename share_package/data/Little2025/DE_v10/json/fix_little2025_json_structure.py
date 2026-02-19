import json
from copy import deepcopy

TEMPLATE_FILE = "DE_Author20XX_by_Someone_YYYYmmddHHMMSS_for_v10_1.json"
TARGET_FILE = "DE_Little2025_by_gemini_20251123174927.json"


def load_json(path):
    with open(path, "r", encoding="utf-8") as f:
        return json.load(f)


def save_json(path, data):
    with open(path, "w", encoding="utf-8") as f:
        json.dump(data, f, ensure_ascii=False, indent=2)
        f.write("\n")


def nested_keys(d):
    """Return a nested representation of keys for comparison (structure only)."""
    if isinstance(d, dict):
        return {k: nested_keys(v) for k, v in d.items()}
    else:
        # For non-dicts, we only care that a value exists (string or object), not its content
        return None


def transform(source, template):
    out = deepcopy(template)

    # Simple helpers to set values safely
    def set_in(part, key, value):
        out[part][key] = value

    def copy_obj(part, key, src_key):
        tmpl_obj = out[part][key]
        new_obj = {}
        src_obj = source.get(src_key, {})
        if not isinstance(src_obj, dict):
            src_obj = {}
        for k in tmpl_obj.keys():
            new_obj[k] = deepcopy(src_obj.get(k, ""))
        out[part][key] = new_obj

    # study_identification_part (strings)
    set_in("study_identification_part", "si1_study_id", source.get("si_1_study_id", ""))
    set_in("study_identification_part", "si2_reference_file_names", source.get("si_2_reference_file_names", ""))
    set_in("study_identification_part", "si3_author_journal_year", source.get("si_3_author_journal_and_year", ""))
    set_in("study_identification_part", "si4_title", source.get("si_4_title", ""))
    set_in("study_identification_part", "si5_doi", source.get("si_5_doi", ""))

    # study_characteristics_part (strings)
    set_in("study_characteristics_part", "sc1_study_objective", source.get("sc_1_study_objective", ""))
    set_in("study_characteristics_part", "sc2_study_design", source.get("sc_2_study_design", ""))
    set_in("study_characteristics_part", "sc3_study_design_other", source.get("sc_3_study_design_other", ""))

    # reference_cohort_and_imaging_part (objects)
    mapping_rci = {
        "rci1_dataset_name": "rci_1_dataset_name",
        "rci2_hc_n": "rci_2_hc_n",
        "rci3_hc_age": "rci_3_hc_age",
        "rci4_hc_sex": "rci_4_hc_sex",
        "rci5_imaging_modality": "rci_5_imaging_modality",
        "rci6_analysis_level": "rci_6_analysis_level",
        "rci7_preprocessing_pipeline": "rci_7_preprocessing_pipeline",
        "rci8_quality_checking": "rci_8_quality_checking",
        "rci9_site_effect_handling": "rci_9_site_effect_handling",
    }
    for dest, src in mapping_rci.items():
        copy_obj("reference_cohort_and_imaging_part", dest, src)

    # normative_modeling_part (objects, with several renamed keys)
    mapping_nm = {
        "nm1_model_origin": "nm_1_model_origin",
        "nm2_modeling_method": "nm_2_modeling_method",
        "nm3_software_tool": "nm_3_software_tools",
        "nm4_response_variable": "nm_4_response_variable",
        "nm5_predictor_variables": "nm_5_predictor_variables",
        "nm6_predictor_effects": "nm_6_predictor_effects",
        "nm7_nm_vldtn_handle_ns": "nm_7_normative_modeling_validation_with_handling_nuisance_structure",
        "nm8_nm_vldtn_same_domain_nonindep": "nm_8_normative_modeling_validation_strategy_using_same_domain_non_independent_dataset",
        "nm9_nm_vldtn_same_domain_indep": "nm_9_normative_modeling_validation_strategy_using_same_domain_independent_dataset",
        "nm10_nm_vldtn_diff_domain": "nm_10_nm_validation_strategy_using_different_domain_dataset",
    }
    for dest, src in mapping_nm.items():
        copy_obj("normative_modeling_part", dest, src)

    # clinical_application_and_analysis_part (mix of objects and strings)
    mapping_caa_obj = {
        "caa1_clinical_dataset": "caa_1_clinical_dataset",
        "caa2_diseases_studied": "caa_2_diseases_studied",
        "caa3_clinical_groups_n": "caa_3_n_of_clinical_groups",
        "caa4_clinical_groups_age": "caa_4_age_of_clinical_groups",
        "caa5_clinical_groups_sex": "caa_5_sex_of_clinical_group_groups",
        "caa6_deviation_metric": "caa_6_deviation_metric",
        "caa10_key_limitations": "caa_10_key_limitations",
    }
    for dest, src in mapping_caa_obj.items():
        copy_obj("clinical_application_and_analysis_part", dest, src)

    # Strings within clinical_application_and_analysis_part
    out["clinical_application_and_analysis_part"]["caa7_association_analysis"] = source.get(
        "caa_7_association_analysis", ""
    )
    out["clinical_application_and_analysis_part"]["caa8_key_findings_brief"] = source.get(
        "caa_8_key_findings_brief", ""
    )
    out["clinical_application_and_analysis_part"]["caa9_key_findings_detailed"] = source.get(
        "caa_9_key_findings_detailed", ""
    )
    out["clinical_application_and_analysis_part"]["caa11_application_notes"] = source.get(
        "caa_11_application_notes", ""
    )

    # general_note_part (string)
    out["general_note_part"]["gn1_general_notes"] = source.get("gn_1_general_note", "")

    return out


def compare_structure(a, b):
    na = nested_keys(a)
    nb = nested_keys(b)
    return na == nb, na, nb


def main():
    template = load_json(TEMPLATE_FILE)
    source = load_json(TARGET_FILE)
    transformed = transform(source, template)

    # Validate structure matches template
    ok, na, nb = compare_structure(transformed, template)
    if not ok:
        print("[ERROR] Structure mismatch after transformation.")
        print(json.dumps({"transformed": na, "template": nb}, ensure_ascii=False, indent=2))
        raise SystemExit(1)

    # Save back
    save_json(TARGET_FILE, transformed)
    print("[OK] Updated", TARGET_FILE, "to match template structure and keys.")


if __name__ == "__main__":
    main()
