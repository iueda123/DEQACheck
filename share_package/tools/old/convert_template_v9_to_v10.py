#!/usr/bin/env python3
"""
Convert a DE template JSON from v9 structure/keys to v10.

Usage:
  python tools/convert_template_v9_to_v10.py -i <input.json> -o <output.json>

Notes:
- Drops fields removed in v10 (e.g., negative_answer_category; some confidence_rating/reason in specific items).
- When an object becomes a plain string in v10, we take the original "answer" field.
- When a plain string (v9) becomes an object in v10 (caa10_key_limitations), we map to {"answer": <string>, "supporting_text": "", "location": ""}.
- Emits warnings to stderr for any keys dropped or missing.

Auto-detects two v9 variants:
- Numbered-part v9 (e.g., study_identification_part, rci1_..., nm1_...)
- Human v9 (e.g., study_identification, dataset_name, model_origin, ...)
"""

from __future__ import annotations

import argparse
import json
import sys
from typing import Any, Dict, Tuple


def warn(msg: str) -> None:
    print(f"[warn] {msg}", file=sys.stderr)


def load_json(path: str) -> Dict[str, Any]:
    with open(path, "r", encoding="utf-8") as f:
        return json.load(f)


def save_json(path: str, data: Dict[str, Any]) -> None:
    with open(path, "w", encoding="utf-8") as f:
        json.dump(data, f, ensure_ascii=False, indent=2)


def drop_keys(d: Dict[str, Any], keys_to_drop: Tuple[str, ...]) -> Dict[str, Any]:
    out = {}
    for k, v in d.items():
        if k in keys_to_drop:
            warn(f"Dropping key '{k}' per v10 schema change")
            continue
        out[k] = v
    return out


def pick_keys(d: Dict[str, Any], keys_to_keep: Tuple[str, ...], *, context: str = "") -> Dict[str, Any]:
    out = {}
    for k in keys_to_keep:
        if k in d:
            out[k] = d[k]
        else:
            warn(f"Missing key '{k}' under {context}; defaulting to empty")
            out[k] = ""
    # Warn for dropped extras
    for k in d.keys() - set(keys_to_keep):
        warn(f"Dropping extra key '{k}' under {context} (not in v10)")
    return out


def from_obj_to_str(v9_obj: Any, *, context: str) -> str:
    """Map v9 object with answer/confidence/... to v10 string (use 'answer')."""
    if isinstance(v9_obj, dict):
        ans = v9_obj.get("answer", "")
        # Warn about dropped keys beyond 'answer'
        for k in v9_obj.keys() - {"answer"}:
            warn(f"Dropping '{context}.{k}' when converting to string")
        return ans if isinstance(ans, str) else ""
    elif isinstance(v9_obj, str):
        # Already a string; pass through
        return v9_obj
    else:
        warn(f"Unexpected type for {context}: {type(v9_obj).__name__}; coercing to empty string")
        return ""


def from_str_to_obj(v9_str: Any, *, keys: Tuple[str, ...], context: str) -> Dict[str, Any]:
    """Map v9 string to v10 object by populating 'answer' and empty for the rest."""
    if not isinstance(v9_str, str):
        warn(f"Expected string at {context}; got {type(v9_str).__name__}. Converting via str().")
        v9_str = "" if v9_str is None else str(v9_str)
    out = {k: "" for k in keys}
    if "answer" in out:
        out["answer"] = v9_str
    return out


def map_ref_cohort_imaging(v9: Dict[str, Any]) -> Dict[str, Any]:
    out: Dict[str, Any] = {}

    # Helpers
    def clean_obj(name: str) -> Dict[str, Any]:
        obj = v9.get(name, {})
        if not isinstance(obj, dict):
            warn(f"{name} is not an object; coercing to empty object")
            obj = {}
        # v10 drops 'negative_answer_category'
        obj = drop_keys(obj, ("negative_answer_category",))
        # Keep known keys if present; otherwise leave as-is to preserve confidence/reason where applicable
        return obj

    # rci1..rci5: drop negative_answer_category only
    for key in [
        "rci1_dataset_name",
        "rci2_hc_n",
        "rci3_hc_age",
        "rci4_hc_sex",
        "rci5_imaging_modality",
    ]:
        out[key] = clean_obj(key)

    # rci6_analysis_level: keep only answer, supporting_text, location
    key = "rci6_analysis_level"
    if key in v9 and isinstance(v9[key], dict):
        out[key] = pick_keys(
            v9[key], ("answer", "supporting_text", "location"), context=key
        )
    else:
        out[key] = {"answer": "", "supporting_text": "", "location": ""}

    # rci7_preprocessing_pipeline: drop negative_answer_category only
    out["rci7_preprocessing_pipeline"] = clean_obj("rci7_preprocessing_pipeline")

    # rci8_quality_checking: object -> string (use answer)
    out["rci8_quality_checking"] = from_obj_to_str(
        v9.get("rci8_quality_checking", {}), context="rci8_quality_checking"
    )

    # rci9_quality_checking_detail: drop negative_answer_category only
    out["rci9_quality_checking_detail"] = clean_obj("rci9_quality_checking_detail")

    # rci10_site_effect_handling: object -> string (use answer)
    out["rci10_site_effect_handling"] = from_obj_to_str(
        v9.get("rci10_site_effect_handling", {}), context="rci10_site_effect_handling"
    )

    # rci11_site_effect_handling_detail: drop negative_answer_category only
    out["rci11_site_effect_handling_detail"] = clean_obj("rci11_site_effect_handling_detail")

    return out


def map_normative_modeling(v9: Dict[str, Any]) -> Dict[str, Any]:
    out: Dict[str, Any] = {}

    # nm1_model_origin: object -> string
    out["nm1_model_origin"] = from_obj_to_str(
        v9.get("nm1_model_origin", {}), context="nm1_model_origin"
    )

    # For the rest, drop 'negative_answer_category'
    for key in [
        "nm2_model_origin_detail",
        "nm3_modeling_method",
        "nm4_software_tool",
        "nm5_response_variable",
        "nm6_predictor_variables",
        "nm7_predictor_effects",
        "nm8_nm_vldtn_handle_ns",
        "nm9_nm_vldtn_same_domain_nonindep",
        "nm10_nm_vldtn_same_domain_indep",
        "nm11_nm_vldtn_diff_domain",
    ]:
        obj = v9.get(key, {})
        if not isinstance(obj, dict):
            warn(f"{key} is not an object; coercing to empty object")
            obj = {}
        out[key] = drop_keys(obj, ("negative_answer_category",))

    return out


def map_caa(v9: Dict[str, Any]) -> Dict[str, Any]:
    out: Dict[str, Any] = {}

    # caa1..caa6: drop negative_answer_category
    for key in [
        "caa1_clinical_dataset",
        "caa2_diseases_studied",
        "caa3_clinical_groups_n",
        "caa4_clinical_groups_age",
        "caa5_clinical_groups_sex",
        "caa6_deviation_metric",
    ]:
        obj = v9.get(key, {})
        if not isinstance(obj, dict):
            warn(f"{key} is not an object; coercing to empty object")
            obj = {}
        out[key] = drop_keys(obj, ("negative_answer_category",))

    # caa7..caa9 stay strings
    for key in [
        "caa7_association_analysis",
        "caa8_key_findings_brief",
        "caa9_key_findings_detailed",
    ]:
        v = v9.get(key, "")
        if not isinstance(v, str):
            warn(f"{key} expected string; coercing via str()")
            v = "" if v is None else str(v)
        out[key] = v

    # caa10_key_limitations: string -> object with answer/supporting_text/location
    out["caa10_key_limitations"] = from_str_to_obj(
        v9.get("caa10_key_limitations", ""),
        keys=("answer", "supporting_text", "location"),
        context="caa10_key_limitations",
    )

    # caa11 stays string
    key = "caa11_application_notes"
    v = v9.get(key, "")
    if not isinstance(v, str):
        warn(f"{key} expected string; coercing via str()")
        v = "" if v is None else str(v)
    out[key] = v

    return out


def convert_v9_to_v10(v9_root: Dict[str, Any]) -> Dict[str, Any]:
    out: Dict[str, Any] = {}

    # study_identification_part: copy as-is
    sip_key = "study_identification_part"
    if sip_key in v9_root and isinstance(v9_root[sip_key], dict):
        out[sip_key] = dict(v9_root[sip_key])
    else:
        warn("Missing or invalid study_identification_part; creating default keys")
        out[sip_key] = {
            "si1_study_id": "",
            "si2_reference_file_names": "",
            "si3_author_journal_year": "",
            "si4_title": "",
            "si5_doi": "",
        }

    # study_characteristics_part: copy as-is
    scp_key = "study_characteristics_part"
    if scp_key in v9_root and isinstance(v9_root[scp_key], dict):
        out[scp_key] = dict(v9_root[scp_key])
    else:
        warn("Missing or invalid study_characteristics_part; creating default keys")
        out[scp_key] = {
            "sc1_study_objective": "",
            "sc2_study_design": "",
            "sc3_study_design_other": "",
        }

    # reference_cohort_and_imaging_part: remap per v10
    rci_key = "reference_cohort_and_imaging_part"
    if rci_key in v9_root and isinstance(v9_root[rci_key], dict):
        out[rci_key] = map_ref_cohort_imaging(v9_root[rci_key])
    else:
        warn("Missing or invalid reference_cohort_and_imaging_part; creating empty section")
        out[rci_key] = map_ref_cohort_imaging({})

    # normative_modeling_part: remap per v10
    nm_key = "normative_modeling_part"
    if nm_key in v9_root and isinstance(v9_root[nm_key], dict):
        out[nm_key] = map_normative_modeling(v9_root[nm_key])
    else:
        warn("Missing or invalid normative_modeling_part; creating empty section")
        out[nm_key] = map_normative_modeling({})

    # clinical_application_and_analysis_part: remap per v10
    caa_key = "clinical_application_and_analysis_part"
    if caa_key in v9_root and isinstance(v9_root[caa_key], dict):
        out[caa_key] = map_caa(v9_root[caa_key])
    else:
        warn("Missing or invalid clinical_application_and_analysis_part; creating empty section")
        out[caa_key] = map_caa({})

    # general_note_part: copy as-is
    gnp_key = "general_note_part"
    if gnp_key in v9_root and isinstance(v9_root[gnp_key], dict):
        out[gnp_key] = dict(v9_root[gnp_key])
    else:
        warn("Missing or invalid general_note_part; creating default keys")
        out[gnp_key] = {"gn1_general_notes": ""}

    return out


def convert_v9human_to_v10(v9_root: Dict[str, Any]) -> Dict[str, Any]:
    """Convert alternate v9 (Human) schema to v10 schema."""
    out: Dict[str, Any] = {}

    # study_identification -> study_identification_part
    si_src = v9_root.get("study_identification", {})
    if not isinstance(si_src, dict):
        warn("study_identification is not an object; creating defaults")
        si_src = {}
    out["study_identification_part"] = {
        "si1_study_id": si_src.get("study_id", ""),
        "si2_reference_file_names": si_src.get("reference_file_names", ""),
        "si3_author_journal_year": si_src.get("author_journal_year", ""),
        "si4_title": si_src.get("title", ""),
        "si5_doi": si_src.get("doi", ""),
    }

    # study_characteristics -> study_characteristics_part
    sc_src = v9_root.get("study_characteristics", {})
    if not isinstance(sc_src, dict):
        warn("study_characteristics is not an object; creating defaults")
        sc_src = {}
    out["study_characteristics_part"] = {
        "sc1_study_objective": sc_src.get("study_objective", ""),
        "sc2_study_design": sc_src.get("study_design", ""),
        "sc3_study_design_other": sc_src.get("study_design_other", ""),
    }

    # reference_cohort_and_imaging -> reference_cohort_and_imaging_part
    rci_src = v9_root.get("reference_cohort_and_imaging", {})
    if not isinstance(rci_src, dict):
        warn("reference_cohort_and_imaging is not an object; creating empty section")
        rci_src = {}

    def get_obj(name: str) -> Dict[str, Any]:
        v = rci_src.get(name, {})
        if not isinstance(v, dict):
            warn(f"{name} under reference_cohort_and_imaging is not an object; coercing")
            v = {}
        return v

    def drop_neg(d: Dict[str, Any]) -> Dict[str, Any]:
        return drop_keys(d, ("negative_answer_category",))

    rci_out: Dict[str, Any] = {}
    # rci1..rci5
    rci_out["rci1_dataset_name"] = drop_neg(get_obj("dataset_name"))
    rci_out["rci2_hc_n"] = drop_neg(get_obj("hc_n"))
    rci_out["rci3_hc_age"] = drop_neg(get_obj("hc_age"))
    rci_out["rci4_hc_sex"] = drop_neg(get_obj("hc_sex"))
    rci_out["rci5_imaging_modality"] = drop_neg(get_obj("imaging_modality"))

    # rci6 keep only answer/supporting_text/location
    rci6 = get_obj("analysis_level")
    rci_out["rci6_analysis_level"] = pick_keys(
        rci6, ("answer", "supporting_text", "location"), context="rci6_analysis_level"
    )

    # rci7 drop negative
    rci_out["rci7_preprocessing_pipeline"] = drop_neg(get_obj("preprocessing_pipeline"))

    # rci8 object -> string via answer
    rci_out["rci8_quality_checking"] = from_obj_to_str(
        get_obj("quality_checking"), context="rci8_quality_checking"
    )

    # rci9 drop negative
    rci_out["rci9_quality_checking_detail"] = drop_neg(get_obj("quality_checking_detail"))

    # rci10 object -> string via answer
    rci_out["rci10_site_effect_handling"] = from_obj_to_str(
        get_obj("site_effect_handling"), context="rci10_site_effect_handling"
    )

    # rci11 drop negative
    rci_out["rci11_site_effect_handling_detail"] = drop_neg(get_obj("site_effect_handling_detail"))
    out["reference_cohort_and_imaging_part"] = rci_out

    # normative_modeling -> normative_modeling_part
    nm_src = v9_root.get("normative_modeling", {})
    if not isinstance(nm_src, dict):
        warn("normative_modeling is not an object; creating empty section")
        nm_src = {}
    nm_out: Dict[str, Any] = {}

    nm_out["nm1_model_origin"] = from_obj_to_str(
        nm_src.get("model_origin", {}), context="nm1_model_origin"
    )

    def nm_obj(name: str) -> Dict[str, Any]:
        v = nm_src.get(name, {})
        if not isinstance(v, dict):
            warn(f"{name} under normative_modeling is not an object; coercing")
            v = {}
        return drop_neg(v)

    nm_out["nm2_model_origin_detail"] = nm_obj("model_origin_detail")
    nm_out["nm3_modeling_method"] = nm_obj("modeling_method")
    nm_out["nm4_software_tool"] = nm_obj("software_tool")
    nm_out["nm5_response_variable"] = nm_obj("response_variable")
    nm_out["nm6_predictor_variables"] = nm_obj("predictor_variables")
    nm_out["nm7_predictor_effects"] = nm_obj("predictor_effects")
    nm_out["nm8_nm_vldtn_handle_ns"] = nm_obj("nm_vldtn_handle_ns")
    nm_out["nm9_nm_vldtn_same_domain_nonindep"] = nm_obj("nm_vldtn_same_domain_nonindep")
    nm_out["nm10_nm_vldtn_same_domain_indep"] = nm_obj("nm_vldtn_same_domain_indep")
    nm_out["nm11_nm_vldtn_diff_domain"] = nm_obj("nm_vldtn_diff_domain")
    out["normative_modeling_part"] = nm_out

    # clinical_application_and_analysis -> clinical_application_and_analysis_part
    caa_src = v9_root.get("clinical_application_and_analysis", {})
    if not isinstance(caa_src, dict):
        warn("clinical_application_and_analysis is not an object; creating empty section")
        caa_src = {}

    def caa_obj(name: str) -> Dict[str, Any]:
        v = caa_src.get(name, {})
        if not isinstance(v, dict):
            warn(f"{name} under clinical_application_and_analysis is not an object; coercing")
            v = {}
        return drop_neg(v)

    caa_out: Dict[str, Any] = {}
    caa_out["caa1_clinical_dataset"] = caa_obj("clinical_dataset")
    caa_out["caa2_diseases_studied"] = caa_obj("diseases_studied")
    caa_out["caa3_clinical_groups_n"] = caa_obj("clinical_groups_n")
    caa_out["caa4_clinical_groups_age"] = caa_obj("clinical_groups_age")
    caa_out["caa5_clinical_groups_sex"] = caa_obj("clinical_groups_sex")
    caa_out["caa6_deviation_metric"] = caa_obj("deviation_metric")

    # strings
    for src_name, tgt_name in [
        ("association_analysis", "caa7_association_analysis"),
        ("key_findings_brief", "caa8_key_findings_brief"),
        ("key_findings_detailed", "caa9_key_findings_detailed"),
        ("application_notes", "caa11_application_notes"),
    ]:
        v = caa_src.get(src_name, "")
        if not isinstance(v, str):
            warn(f"{src_name} expected string; coercing via str()")
            v = "" if v is None else str(v)
        caa_out[tgt_name] = v

    # caa10 string -> object
    caa_out["caa10_key_limitations"] = from_str_to_obj(
        caa_src.get("key_limitations", ""),
        keys=("answer", "supporting_text", "location"),
        context="caa10_key_limitations",
    )
    out["clinical_application_and_analysis_part"] = caa_out

    # general_notes -> general_note_part
    gn_src = v9_root.get("general_notes", {})
    gn_value = ""
    if isinstance(gn_src, dict):
        v = gn_src.get("general_notes", "")
        gn_value = v if isinstance(v, str) else ("" if v is None else str(v))
    elif isinstance(gn_src, str):
        gn_value = gn_src
    else:
        warn("general_notes has unexpected type; defaulting to empty")
    out["general_note_part"] = {"gn1_general_notes": gn_value}

    return out


def main() -> None:
    p = argparse.ArgumentParser(description="Convert DE template JSON from v9 to v10 structure")
    p.add_argument("-i", "--input", required=True, help="Path to v9 JSON file")
    p.add_argument("-o", "--output", required=True, help="Path to write v10 JSON file")
    args = p.parse_args()

    v9 = load_json(args.input)

    # Detect variant by top-level keys
    keys = set(v9.keys()) if isinstance(v9, dict) else set()
    if {"study_identification_part", "study_characteristics_part"} & keys:
        v10 = convert_v9_to_v10(v9)
    elif {"study_identification", "study_characteristics"} & keys:
        v10 = convert_v9human_to_v10(v9)
    else:
        warn("Unknown schema; attempting numbered-part v9 conversion as fallback")
        v10 = convert_v9_to_v10(v9)
    save_json(args.output, v10)
    print(f"Converted '{args.input}' -> '{args.output}'")


if __name__ == "__main__":
    main()
