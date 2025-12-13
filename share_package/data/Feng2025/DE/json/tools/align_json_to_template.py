#!/usr/bin/env python3
import json
import sys
from copy import deepcopy
from pathlib import Path


def load_json(path: Path):
    with path.open('r', encoding='utf-8') as f:
        return json.load(f)


def save_json(path: Path, data):
    with path.open('w', encoding='utf-8') as f:
        json.dump(data, f, ensure_ascii=False, indent=2)
        f.write("\n")


def prune_to_template(value, template_node):
    """Recursively prune 'value' to keep only the structure/keys of 'template_node'.

    - If both are dicts: keep only template keys and recursively prune.
    - If both are lists: if template list has at least one element, prune each element
      of 'value' to match the first template element; otherwise keep as-is (empty schema).
    - Otherwise: return value as-is (but if types mismatch, replace with template_node).
    """
    if isinstance(template_node, dict):
        if not isinstance(value, dict):
            # Replace mismatched type with a deep copy of template_node
            return deepcopy(template_node)
        pruned = {}
        for k, tmpl_v in template_node.items():
            if k in value:
                pruned[k] = prune_to_template(value[k], tmpl_v)
            else:
                pruned[k] = deepcopy(tmpl_v)
        return pruned
    elif isinstance(template_node, list):
        if not isinstance(value, list):
            # Type mismatch -> use template default (likely empty list)
            return deepcopy(template_node)
        if not template_node:
            # No schema available; keep list as-is (structure only requires it's a list)
            return value
        # Use first element of template as schema for all elements
        tmpl_item = template_node[0]
        return [prune_to_template(v, tmpl_item) for v in value]
    else:
        # Primitive: coerce to template type when reasonable to preserve content
        if isinstance(template_node, str):
            # Convert any value to string
            return "" if value is None else str(value)
        if isinstance(template_node, (int, float)):
            # Try to coerce to number
            try:
                if isinstance(value, (int, float)):
                    return value
                if isinstance(value, str) and value.strip():
                    # Try int first, then float
                    iv = int(value)
                    return iv
            except Exception:
                try:
                    fv = float(value)
                    return fv
                except Exception:
                    return deepcopy(template_node)
            return deepcopy(template_node)
        # For None or other literals, return template default
        return deepcopy(template_node)


def main(template_path: str, source_path: str, dest_path: str):
    tpl_p = Path(template_path)
    src_p = Path(source_path)
    dst_p = Path(dest_path)

    template = load_json(tpl_p)
    src = load_json(src_p)

    # Start from a deep copy of the template so all required keys exist
    aligned = deepcopy(template)

    # Mapping from flat Gemini keys to (top-level section, template key)
    m = {
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
        "nm_7_normative_modeling_validation_with_handling_nuisance_structure": ("normative_modeling_part", "nm7_nm_vldtn_handle_ns"),
        "nm_8_normative_modeling_validation_strategy_using_same_domain_non_independent_dataset": ("normative_modeling_part", "nm8_nm_vldtn_same_domain_nonindep"),
        "nm_9_normative_modeling_validation_strategy_using_same_domain_independent_dataset": ("normative_modeling_part", "nm9_nm_vldtn_same_domain_indep"),
        "nm_10_nm_validation_strategy_using_different_domain_dataset": ("normative_modeling_part", "nm10_nm_vldtn_diff_domain"),

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

        # General notes
        "gn_1_general_note": ("general_note_part", "gn1_general_notes"),
    }

    # Copy values from src into aligned using the mapping and prune to template shape
    for src_key, (section, dst_key) in m.items():
        if src_key not in src:
            continue
        src_val = src[src_key]
        tmpl_node = template[section][dst_key]
        aligned[section][dst_key] = prune_to_template(src_val, tmpl_node)

    save_json(dst_p, aligned)


if __name__ == "__main__":
    if len(sys.argv) != 4:
        print("Usage: align_json_to_template.py TEMPLATE_JSON SOURCE_JSON DEST_JSON", file=sys.stderr)
        sys.exit(2)
    main(sys.argv[1], sys.argv[2], sys.argv[3])
