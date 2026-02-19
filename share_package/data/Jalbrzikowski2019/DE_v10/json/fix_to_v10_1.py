import json
from pathlib import Path

SRC = Path('DE_Jalbrzikowski2019_by_gemini_20251209161423.json')
TEMPLATE = Path('DE_Author20XX_by_Someone_YYYYmmddHHMMSS_for_v10_1.json')


def load_json(p: Path):
    with p.open('r', encoding='utf-8') as f:
        return json.load(f)


def save_json(p: Path, data):
    with p.open('w', encoding='utf-8') as f:
        json.dump(data, f, ensure_ascii=False, indent=2)


def pick(obj, keys):
    return {k: obj.get(k, "") for k in keys}


def main():
    src = load_json(SRC)
    template = load_json(TEMPLATE)

    # Helper getters from source (flat, gemini-style keys)
    g = lambda k: src.get(k)

    # Build each section to match the template's keys exactly
    study_identification_part = {
        'si1_study_id': g('si_1_study_id') or "",
        'si2_reference_file_names': g('si_2_reference_file_names') or "",
        'si3_author_journal_year': g('si_3_author_journal_and_year') or g('si_3_author_journal_year') or "",
        'si4_title': g('si_4_title') or "",
        'si5_doi': g('si_5_doi') or "",
    }

    study_characteristics_part = {
        'sc1_study_objective': g('sc_1_study_objective') or "",
        'sc2_study_design': g('sc_2_study_design') or "",
        'sc3_study_design_other': g('sc_3_study_design_other') or "",
    }

    # For answer blocks, only keep keys that exist in the template block
def ans_block(src_block, tmpl_block):
    if not isinstance(tmpl_block, dict):
        # Template expects a string; coerce to string if needed
        if isinstance(src_block, (dict, list)):
            return ""
        return ("" if src_block is None else str(src_block))
    # Template expects an object; keep only its keys and coerce to string values
    if not isinstance(src_block, dict):
        src_block = {}
    out = {}
    for k in tmpl_block.keys():
        val = src_block.get(k, "")
        # In template, nested fields are strings; coerce accordingly
        out[k] = "" if val is None else str(val)
    return out

    rci_tmpl = template['reference_cohort_and_imaging_part']
    nm_tmpl = template['normative_modeling_part']
    caa_tmpl = template['clinical_application_and_analysis_part']

    reference_cohort_and_imaging_part = {
        'rci1_dataset_name': ans_block(g('rci_1_dataset_name'), rci_tmpl['rci1_dataset_name']),
        'rci2_hc_n': ans_block(g('rci_2_hc_n'), rci_tmpl['rci2_hc_n']),
        'rci3_hc_age': ans_block(g('rci_3_hc_age'), rci_tmpl['rci3_hc_age']),
        'rci4_hc_sex': ans_block(g('rci_4_hc_sex'), rci_tmpl['rci4_hc_sex']),
        'rci5_imaging_modality': ans_block(g('rci_5_imaging_modality'), rci_tmpl['rci5_imaging_modality']),
        'rci6_analysis_level': ans_block(g('rci_6_analysis_level'), rci_tmpl['rci6_analysis_level']),
        'rci7_preprocessing_pipeline': ans_block(g('rci_7_preprocessing_pipeline'), rci_tmpl['rci7_preprocessing_pipeline']),
        'rci8_quality_checking': ans_block(g('rci_8_quality_checking'), rci_tmpl['rci8_quality_checking']),
        'rci9_site_effect_handling': ans_block(g('rci_9_site_effect_handling'), rci_tmpl['rci9_site_effect_handling']),
    }

    normative_modeling_part = {
        'nm1_model_origin': ans_block(g('nm_1_model_origin'), nm_tmpl['nm1_model_origin']),
        'nm2_modeling_method': ans_block(g('nm_2_modeling_method'), nm_tmpl['nm2_modeling_method']),
        'nm3_software_tool': ans_block(g('nm_3_software_tool'), nm_tmpl['nm3_software_tool']),
        'nm4_response_variable': ans_block(g('nm_4_response_variable'), nm_tmpl['nm4_response_variable']),
        'nm5_predictor_variables': ans_block(g('nm_5_predictor_variables'), nm_tmpl['nm5_predictor_variables']),
        'nm6_predictor_effects': ans_block(g('nm_6_predictor_effects'), nm_tmpl['nm6_predictor_effects']),
        'nm7_nm_vldtn_handle_ns': ans_block(g('nm_7_normative_modeling_validation_with_handling_nuisance_structure'), nm_tmpl['nm7_nm_vldtn_handle_ns']),
        'nm8_nm_vldtn_same_domain_nonindep': ans_block(g('nm_8_normative_modeling_validation_strategy_using_same_domain_non_independent_dataset'), nm_tmpl['nm8_nm_vldtn_same_domain_nonindep']),
        'nm9_nm_vldtn_same_domain_indep': ans_block(g('nm_9_normative_modeling_validation_strategy_using_same_domain_independent_dataset'), nm_tmpl['nm9_nm_vldtn_same_domain_indep']),
        'nm10_nm_vldtn_diff_domain': ans_block(g('nm_10_nm_validation_strategy_using_different_domain_dataset'), nm_tmpl['nm10_nm_vldtn_diff_domain']),
    }

    clinical_application_and_analysis_part = {
        'caa1_clinical_dataset': ans_block(g('caa_1_clinical_dataset'), caa_tmpl['caa1_clinical_dataset']),
        'caa2_diseases_studied': ans_block(g('caa_2_diseases_studied'), caa_tmpl['caa2_diseases_studied']),
        'caa3_clinical_groups_n': ans_block(g('caa_3_n_of_clinical_groups'), caa_tmpl['caa3_clinical_groups_n']),
        'caa4_clinical_groups_age': ans_block(g('caa_4_age_of_clinical_groups'), caa_tmpl['caa4_clinical_groups_age']),
        'caa5_clinical_groups_sex': ans_block(g('caa_5_sex_of_clinical_groups'), caa_tmpl['caa5_clinical_groups_sex']),
        'caa6_deviation_metric': ans_block(g('caa_6_deviation_metric'), caa_tmpl['caa6_deviation_metric']),
        'caa7_association_analysis': ans_block(g('caa_7_association_analysis'), caa_tmpl['caa7_association_analysis']),
        'caa8_key_findings_brief': ans_block(g('caa_8_key_findings_brief'), caa_tmpl['caa8_key_findings_brief']),
        'caa9_key_findings_detailed': ans_block(g('caa_9_key_findings_detailed'), caa_tmpl['caa9_key_findings_detailed']),
        'caa10_key_limitations': ans_block(g('caa_10_key_limitations'), caa_tmpl['caa10_key_limitations']),
        'caa11_application_notes': ans_block(g('caa_11_application_notes'), caa_tmpl['caa11_application_notes']),
    }

    general_note_part = {
        'gn1_general_notes': g('gn_1_general_note') or "",
    }

    out = {
        'study_identification_part': study_identification_part,
        'study_characteristics_part': study_characteristics_part,
        'reference_cohort_and_imaging_part': reference_cohort_and_imaging_part,
        'normative_modeling_part': normative_modeling_part,
        'clinical_application_and_analysis_part': clinical_application_and_analysis_part,
        'general_note_part': general_note_part,
    }

    # Optional: verify that out has same structure (keys and types) as template
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
            type_a = 'dict' if isinstance(a, dict) else ('list' if isinstance(a, list) else ('str' if isinstance(a, str) else type(a).__name__))
            type_b = 'dict' if isinstance(b, dict) else ('list' if isinstance(b, list) else ('str' if isinstance(b, str) else type(b).__name__))
            if type_a != type_b:
                mismatches.append(f"Type mismatch at {path}: got {type_a}, expected {type_b}")
        return mismatches

    mismatches = compare_keys(out, template)
    if mismatches:
        Path('FIX_V10_1_MISMATCH_REPORT.txt').write_text("\n".join(mismatches), encoding='utf-8')

    # Write back to source file
    save_json(SRC, out)


if __name__ == '__main__':
    main()
