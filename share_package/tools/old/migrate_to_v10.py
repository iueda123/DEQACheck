import json
from pathlib import Path


def normobj(value):
    """Normalize a detail object by removing obsolete keys and keeping expected fields.
    Accepts a dict or other; returns a dict with keys: answer, confidence_rating, reason, supporting_text, location.
    If value is a string, wrap it as answer and leave others empty.
    If value is None or missing, return empty fields.
    """
    fields = ["answer", "confidence_rating", "reason", "supporting_text", "location"]
    if isinstance(value, dict):
        # Drop obsolete key if present
        result = {k: value.get(k, "") for k in fields}
        return result
    elif isinstance(value, str):
        return {"answer": value, "confidence_rating": "", "reason": "", "supporting_text": "", "location": ""}
    else:
        return {k: "" for k in fields}


def pick_str(value):
    """Pick string value from either a string or a detail object with 'answer'."""
    if isinstance(value, dict):
        return value.get("answer", "")
    elif isinstance(value, str):
        return value
    else:
        return ""


def to_v10(src: dict) -> dict:
    # study_identification_part
    si = src.get("study_identification", {}) or {}
    study_identification_part = {
        "si1_study_id": si.get("study_id", ""),
        "si2_reference_file_names": si.get("reference_file_names", ""),
        "si3_author_journal_year": si.get("author_journal_year", ""),
        "si4_title": si.get("title", ""),
        "si5_doi": si.get("doi", ""),
    }

    # study_characteristics_part
    sc = src.get("study_characteristics", {}) or {}
    sc_obj = sc.get("study_objective", "")
    if isinstance(sc_obj, dict):
        sc1 = {"supporting_text": sc_obj.get("supporting_text", ""), "location": sc_obj.get("location", "")}
    else:
        sc1 = {"supporting_text": sc_obj or "", "location": ""}
    study_characteristics_part = {
        "sc1_study_objective": sc1,
        "sc2_study_design": sc.get("study_design", ""),
        "sc3_study_design_other": sc.get("study_design_other", ""),
    }

    # reference_cohort_and_imaging_part
    rci = src.get("reference_cohort_and_imaging", {}) or {}
    reference_cohort_and_imaging_part = {
        "rci1_dataset_name": normobj(rci.get("dataset_name")),
        "rci2_hc_n": normobj(rci.get("hc_n")),
        "rci3_hc_age": normobj(rci.get("hc_age")),
        "rci4_hc_sex": normobj(rci.get("hc_sex")),
        "rci5_imaging_modality": normobj(rci.get("imaging_modality")),
        "rci6_analysis_level": normobj(rci.get("analysis_level")),
        "rci7_preprocessing_pipeline": normobj(rci.get("preprocessing_pipeline")),
        "rci8_quality_checking": pick_str(rci.get("quality_checking")),
        "rci9_quality_checking_detail": normobj(rci.get("quality_checking_detail")),
        "rci10_site_effect_handling": pick_str(rci.get("site_effect_handling")),
        "rci11_site_effect_handling_detail": normobj(rci.get("site_effect_handling_detail")),
    }

    # normative_modeling_part
    nm = src.get("normative_modeling", {}) or {}
    normative_modeling_part = {
        "nm1_model_origin": pick_str(nm.get("model_origin")),
        "nm2_model_origin_detail": normobj(nm.get("model_origin_detail")),
        "nm3_modeling_method": normobj(nm.get("modeling_method")),
        "nm4_software_tool": normobj(nm.get("software_tool")),
        "nm5_response_variable": normobj(nm.get("response_variable")),
        "nm6_predictor_variables": normobj(nm.get("predictor_variables")),
        "nm7_predictor_effects": normobj(nm.get("predictor_effects")),
        "nm8_nm_vldtn_handle_ns": normobj(nm.get("nm_vldtn_handle_ns")),
        "nm9_nm_vldtn_same_domain_nonindep": normobj(nm.get("nm_vldtn_same_domain_nonindep")),
        "nm10_nm_vldtn_same_domain_indep": normobj(nm.get("nm_vldtn_same_domain_indep")),
        "nm11_nm_vldtn_diff_domain": normobj(nm.get("nm_vldtn_diff_domain")),
    }

    # clinical_application_and_analysis_part
    caa = src.get("clinical_application_and_analysis", {}) or {}
    clinical_application_and_analysis_part = {
        "caa1_clinical_dataset": normobj(caa.get("clinical_dataset")),
        "caa2_diseases_studied": normobj(caa.get("diseases_studied")),
        "caa3_clinical_groups_n": normobj(caa.get("clinical_groups_n")),
        "caa4_clinical_groups_age": normobj(caa.get("clinical_groups_age")),
        "caa5_clinical_groups_sex": normobj(caa.get("clinical_groups_sex")),
        "caa6_deviation_metric": normobj(caa.get("deviation_metric")),
        "caa7_association_analysis": caa.get("association_analysis", "") if not isinstance(caa.get("association_analysis"), dict) else pick_str(caa.get("association_analysis")),
        "caa8_key_findings_brief": caa.get("key_findings_brief", ""),
        "caa9_key_findings_detailed": caa.get("key_findings_detailed", ""),
        "caa10_key_limitations": caa.get("key_limitations", ""),
        "caa11_application_notes": caa.get("application_notes", ""),
    }

    # general_note_part
    gn = src.get("general_notes", {}) or src.get("general_note", {}) or {}
    if isinstance(gn, dict):
        note = gn.get("general_notes", "")
    else:
        note = str(gn)
    general_note_part = {"gn1_general_notes": note}

    dest = {
        "study_identification_part": study_identification_part,
        "study_characteristics_part": study_characteristics_part,
        "reference_cohort_and_imaging_part": reference_cohort_and_imaging_part,
        "normative_modeling_part": normative_modeling_part,
        "clinical_application_and_analysis_part": clinical_application_and_analysis_part,
        "general_note_part": general_note_part,
    }
    return dest


def main():
    for path in sorted(Path('.').glob('_DE_*_by_Human_.json')):
        with path.open('r', encoding='utf-8') as f:
            try:
                src = json.load(f)
            except json.JSONDecodeError as e:
                print(f"Skipping {path}: JSON decode error: {e}")
                continue
        dest = to_v10(src)
        with path.open('w', encoding='utf-8') as f:
            json.dump(dest, f, ensure_ascii=False, indent=2)
        print(f"Migrated {path}")


if __name__ == '__main__':
    main()

