#!/usr/bin/env python3
import json
import sys
from pathlib import Path


def load_json(path: Path):
    with path.open('r', encoding='utf-8') as f:
        return json.load(f)


def save_json(path: Path, data):
    with path.open('w', encoding='utf-8') as f:
        json.dump(data, f, ensure_ascii=False, indent=2)
        f.write('\n')


def main():
    if len(sys.argv) != 3:
        print("Usage: fix_json_structure.py <template_json> <target_json>")
        sys.exit(1)

    template_path = Path(sys.argv[1])
    target_path = Path(sys.argv[2])

    template = load_json(template_path)
    target = load_json(target_path)

    # Start with a deep copy via serialization to preserve ordering/shape
    new_data = json.loads(json.dumps(template))

    # Helper to set nested value while filtering to template keys
    def set_to_path(parent_key, child_key, value):
        if parent_key not in new_data:
            return
        # If child_key is None, parent is a dict to be replaced with filtered value
        if child_key is None:
            if isinstance(value, dict) and isinstance(new_data[parent_key], dict):
                allowed = set(new_data[parent_key].keys())
                new_data[parent_key] = {k: value.get(k, new_data[parent_key][k]) for k in new_data[parent_key].keys()}
            else:
                new_data[parent_key] = value
            return

        if child_key not in new_data[parent_key]:
            return
        tmpl_sub = new_data[parent_key][child_key]
        if isinstance(tmpl_sub, dict) and isinstance(value, dict):
            # Only keep keys present in template sub-structure
            new_data[parent_key][child_key] = {k: value.get(k, tmpl_sub.get(k, "")) for k in tmpl_sub.keys()}
        else:
            new_data[parent_key][child_key] = value

    # Mapping from flat keys in target to nested (parent, child) in template
    mapping = {
        # Study identification
        "si_1_study_id": ("study_identification_part", "si1_study_id"),
        "si_2_reference_file_names": ("study_identification_part", "si2_reference_file_names"),
        "si_3_author_journal_and_year": ("study_identification_part", "si3_author_journal_year"),
        "si_4_title": ("study_identification_part", "si4_title"),
        "si_5_doi": ("study_identification_part", "si5_doi"),

        # Study characteristics
        "sc_1_study_objective": ("study_characteristics_part", "sc1_study_objective"),
        "sc_2_study_design": ("study_characteristics_part", "sc2_study_design"),
        "sc_3_study_design_other": ("study_characteristics_part", "sc3_study_design_other"),

        # Reference cohort and imaging
        "rci_1_dataset_name": ("reference_cohort_and_imaging_part", "rci1_dataset_name"),
        "rci_2_hc_n": ("reference_cohort_and_imaging_part", "rci2_hc_n"),
        "rci_3_hc_age": ("reference_cohort_and_imaging_part", "rci3_hc_age"),
        "rci_4_hc_sex": ("reference_cohort_and_imaging_part", "rci4_hc_sex"),
        "rci_5_imaging_modality": ("reference_cohort_and_imaging_part", "rci5_imaging_modality"),
        "rci_6_analysis_level": ("reference_cohort_and_imaging_part", "rci6_analysis_level"),
        "rci_7_preprocessing_pipeline": ("reference_cohort_and_imaging_part", "rci7_preprocessing_pipeline"),
        "rci_8_quality_checking": ("reference_cohort_and_imaging_part", "rci8_quality_checking"),
        "rci_9_site_effect_handling": ("reference_cohort_and_imaging_part", "rci9_site_effect_handling"),

        # Normative modeling
        "nm_1_model_origin": ("normative_modeling_part", "nm1_model_origin"),
        "nm_2_modeling_method": ("normative_modeling_part", "nm2_modeling_method"),
        "nm_3_software_tools": ("normative_modeling_part", "nm3_software_tool"),
        "nm_4_response_variable": ("normative_modeling_part", "nm4_response_variable"),
        "nm_5_predictor_variables": ("normative_modeling_part", "nm5_predictor_variables"),
        "nm_6_predictor_effects": ("normative_modeling_part", "nm6_predictor_effects"),
        "nm_7_normative_modeling_validation_with_handling_nuisance_structure": ("normative_modeling_part",
                                                                                "nm7_nm_vldtn_handle_ns"),
        "nm_8_normative_modeling_validation_strategy_using_same_domain_non_independent_dataset": (
            "normative_modeling_part", "nm8_nm_vldtn_same_domain_nonindep"),
        "nm_9_normative_modeling_validation_strategy_using_same_domain_independent_dataset": ("normative_modeling_part",
                                                                                              "nm9_nm_vldtn_same_domain_indep"),
        "nm_10_nm_validation_strategy_using_different_domain_dataset": ("normative_modeling_part",
                                                                        "nm10_nm_vldtn_diff_domain"),

        # Clinical application and analysis
        "caa_1_clinical_dataset": ("clinical_application_and_analysis_part", "caa1_clinical_dataset"),
        "caa_2_diseases_studied": ("clinical_application_and_analysis_part", "caa2_diseases_studied"),
        "caa_3_n_of_clinical_groups": ("clinical_application_and_analysis_part", "caa3_clinical_groups_n"),
        "caa_4_age_of_clinical_groups": ("clinical_application_and_analysis_part", "caa4_clinical_groups_age"),
        "caa_5_sex_of_clinical_group_groups": ("clinical_application_and_analysis_part", "caa5_clinical_groups_sex"),
        "caa_6_deviation_metric": ("clinical_application_and_analysis_part", "caa6_deviation_metric"),
        "caa_7_association_analysis": ("clinical_application_and_analysis_part", "caa7_association_analysis"),
        "caa_8_key_findings_brief": ("clinical_application_and_analysis_part", "caa8_key_findings_brief"),
        "caa_9_key_findings_detailed": ("clinical_application_and_analysis_part", "caa9_key_findings_detailed"),
        "caa_10_key_limitations": ("clinical_application_and_analysis_part", "caa10_key_limitations"),
        "caa_11_application_notes": ("clinical_application_and_analysis_part", "caa11_application_notes"),

        # General note
        "gn_1_general_note": ("general_note_part", "gn1_general_notes"),
    }

    # Apply mapping
    for src_key, (parent, child) in mapping.items():
        if src_key in target:
            set_to_path(parent, child, target[src_key])

    # Save back to target file
    save_json(target_path, new_data)


if __name__ == "__main__":
    main()
