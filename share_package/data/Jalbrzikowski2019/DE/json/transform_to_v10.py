import json
from pathlib import Path

SRC = Path('DE_Jalbrzikowski2019_by_gemini_20251122031337.json')
TEMPLATE = Path('DE_Author20XX_by_Someone_YYYYmmddHHMMSS_for_v10.json')

def load_json(p: Path):
    with p.open('r', encoding='utf-8') as f:
        return json.load(f)

def save_json(p: Path, data):
    with p.open('w', encoding='utf-8') as f:
        json.dump(data, f, ensure_ascii=False, indent=2)

def empty_answer_block():
    return {
        "answer": "",
        "confidence_rating": "",
        "reason": "",
        "supporting_text": "",
        "location": ""
    }

def main():
    src = load_json(SRC)
    template = load_json(TEMPLATE)

    # Build output structure matching template
    out = {
        "study_identification_part": {
            "si1_study_id": src.get("si1_study_id", ""),
            "si2_reference_file_names": src.get("si2_reference_file_names", ""),
            # Rename: si3_author_journal_and_year -> si3_author_journal_year
            "si3_author_journal_year": src.get("si3_author_journal_year", src.get("si3_author_journal_and_year", "")),
            "si4_title": src.get("si4_title", ""),
            "si5_doi": src.get("si5_doi", "")
        },
        "study_characteristics_part": {
            "sc1_study_objective": src.get("sc1_study_objective", ""),
            "sc2_study_design": src.get("sc2_study_design", ""),
            "sc3_study_design_other": src.get("sc3_study_design_other", "")
        },
        "reference_cohort_and_imaging_part": {
            "rci1_dataset_name": src.get("rci1_dataset_name", empty_answer_block()),
            "rci2_hc_n": src.get("rci2_hc_n", empty_answer_block()),
            "rci3_hc_age": src.get("rci3_hc_age", empty_answer_block()),
            "rci4_hc_sex": src.get("rci4_hc_sex", empty_answer_block()),
            "rci5_imaging_modality": src.get("rci5_imaging_modality", empty_answer_block()),
            # rci6 is a reduced block (no confidence/rationale by template)
            "rci6_analysis_level": {
                "answer": src.get("rci6_analysis_level", {}).get("answer", ""),
                "supporting_text": src.get("rci6_analysis_level", {}).get("supporting_text", ""),
                "location": src.get("rci6_analysis_level", {}).get("location", "")
            },
            "rci7_preprocessing_pipeline": src.get("rci7_preprocessing_pipeline", empty_answer_block()),
            # rci8 is a simple string in v10
            "rci8_quality_checking": src.get("rci8_quality_checking", ""),
            "rci9_quality_checking_detail": src.get("rci9_quality_checking_detail", empty_answer_block()),
            # rci10 is a simple string in v10
            "rci10_site_effect_handling": src.get("rci10_site_effect_handling", ""),
            "rci11_site_effect_handling_detail": src.get("rci11_site_effect_handling_detail", empty_answer_block()),
        },
        "normative_modeling_part": {
            "nm1_model_origin": src.get("nm1_model_origin", ""),
            "nm2_model_origin_detail": src.get("nm2_model_origin_detail", empty_answer_block()),
            "nm3_modeling_method": src.get("nm3_modeling_method", empty_answer_block()),
            # Rename: nm4_software_tools -> nm4_software_tool
            "nm4_software_tool": src.get("nm4_software_tool", src.get("nm4_software_tools", empty_answer_block())),
            "nm5_response_variable": src.get("nm5_response_variable", empty_answer_block()),
            "nm6_predictor_variables": src.get("nm6_predictor_variables", empty_answer_block()),
            "nm7_predictor_effects": src.get("nm7_predictor_effects", empty_answer_block()),
            # Rename and normalize nm8..nm11
            "nm8_nm_vldtn_handle_ns": src.get(
                "nm8_nm_vldtn_handle_ns",
                src.get("nm8_normative_modeling_validation_with_handling_nuisance_structure", empty_answer_block())
            ),
            "nm9_nm_vldtn_same_domain_nonindep": src.get(
                "nm9_nm_vldtn_same_domain_nonindep",
                src.get("nm9_normative_modeling_validation_strategy_using_same_domain_non_independent_dataset", empty_answer_block())
            ),
            "nm10_nm_vldtn_same_domain_indep": src.get(
                "nm10_nm_vldtn_same_domain_indep",
                src.get("nm10_normative_modeling_validation_strategy_using_same_domain_independent_dataset", empty_answer_block())
            ),
            "nm11_nm_vldtn_diff_domain": src.get(
                "nm11_nm_vldtn_diff_domain",
                src.get("nm11_nm_validation_strategy_using_different_domain_dataset", empty_answer_block())
            ),
        },
        "clinical_application_and_analysis_part": {
            # Not present in the Gemini file; initialize empty per v10
            "caa1_clinical_dataset": empty_answer_block(),
            "caa2_diseases_studied": empty_answer_block(),
            "caa3_clinical_groups_n": empty_answer_block(),
            "caa4_clinical_groups_age": empty_answer_block(),
            "caa5_clinical_groups_sex": empty_answer_block(),
            "caa6_deviation_metric": empty_answer_block(),
            "caa7_association_analysis": "",
            "caa8_key_findings_brief": "",
            "caa9_key_findings_detailed": "",
            "caa10_key_limitations": {"answer": "", "supporting_text": "", "location": ""},
            "caa11_application_notes": "",
        },
        "general_note_part": {
            "gn1_general_notes": ""
        }
    }

    # Validate structure against template: check keys and types recursively
    def compare_keys(a, b, path="$"):
        mismatches = []
        if isinstance(a, dict) and isinstance(b, dict):
            a_keys = set(a.keys())
            b_keys = set(b.keys())
            for k in sorted(a_keys - b_keys):
                mismatches.append(f"Extra key in output at {path}: {k}")
            for k in sorted(b_keys - a_keys):
                mismatches.append(f"Missing key in output at {path}: {k}")
            for k in sorted(a_keys & b_keys):
                mismatches.extend(compare_keys(a[k], b[k], f"{path}.{k}"))
        else:
            # Compare type category: string vs object
            type_a = 'dict' if isinstance(a, dict) else ('list' if isinstance(a, list) else type(a).__name__)
            type_b = 'dict' if isinstance(b, dict) else ('list' if isinstance(b, list) else type(b).__name__)
            if type_a != type_b:
                mismatches.append(f"Type mismatch at {path}: got {type_a}, expected {type_b}")
        return mismatches

    mismatches = compare_keys(out, template)
    if mismatches:
        # Write out anyway, but also drop a companion report for debugging
        report = Path('TRANSFORM_MISMATCH_REPORT.txt')
        report.write_text("\n".join(mismatches), encoding='utf-8')

    save_json(SRC, out)

if __name__ == "__main__":
    main()

