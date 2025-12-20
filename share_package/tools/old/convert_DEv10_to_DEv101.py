#!/usr/bin/env python3
"""
Convert DE v10 JSON files to DE v10_1 format.

Changes applied:
  - normative_modeling_part.nm1_model_origin: A_Style (string) + nm2 details -> ADCSL object
    * answer := old nm1_model_origin
    * detail := old nm2_model_origin_detail.answer (if Pre-trained), else "-"
    * confidence_rating, supporting_text, location := from nm2_model_origin_detail
    * remove nm2_model_origin_detail

  - reference_cohort_and_imaging_part.rci8_quality_checking: A_Style (string) + rci9 details -> ADCSL object
    * answer := old rci8_quality_checking
    * detail := old rci9_quality_checking_detail.answer (if Yes), else "-"
    * confidence_rating, supporting_text, location := from rci9_quality_checking_detail
    * remove rci9_quality_checking_detail

  - reference_cohort_and_imaging_part.rci10_site_effect_handling + rci11 details -> rci9_site_effect_handling (ADCSL)
    * answer := old rci10_site_effect_handling
    * detail := old rci11_site_effect_handling_detail.answer (if answer != "None"), else "-"
    * confidence_rating, supporting_text, location := from rci11_site_effect_handling_detail
    * output key renamed to rci9_site_effect_handling; rci10 and rci11 keys removed

  - normative_modeling_part renumbering after integrating nm2 into nm1:
    * nm3_modeling_method -> nm2_modeling_method
    * nm4_software_tool -> nm3_software_tool
    * nm5_response_variable -> nm4_response_variable
    * nm6_predictor_variables -> nm5_predictor_variables
    * nm7_predictor_effects -> nm6_predictor_effects
    * nm8_nm_vldtn_handle_ns -> nm7_nm_vldtn_handle_ns
    * nm9_nm_vldtn_same_domain_nonindep -> nm8_nm_vldtn_same_domain_nonindep
    * nm10_nm_vldtn_same_domain_indep -> nm9_nm_vldtn_same_domain_indep
    * nm11_nm_vldtn_diff_domain -> nm10_nm_vldtn_diff_domain

Other fields are carried over as-is.

Usage:
  python tools/convert_DEv10_to_DEv101.py INPUT.json

Behavior:
  - Backs up the original (v10) JSON to a sibling file named "<stem>_v10.json"
    (adds numeric suffix if needed).
  - Overwrites INPUT.json with the converted v10_1 content.
"""

from __future__ import annotations

import argparse
import json
from pathlib import Path
from typing import Any, Dict
import re


def _ensure_adcs_fields(obj: Dict[str, Any]) -> Dict[str, Any]:
    """Ensure ADCSL object has required keys with string defaults."""
    return {
        "answer": str(obj.get("answer", "")) if obj is not None else "",
        "detail": str(obj.get("detail", "")) if obj is not None else "",
        "confidence_rating": str(obj.get("confidence_rating", "")) if obj is not None else "",
        "supporting_text": str(obj.get("supporting_text", "")) if obj is not None else "",
        "location": str(obj.get("location", "")) if obj is not None else "",
    }


def _loads_lenient(text: str) -> Dict[str, Any]:
    # Strip BOM
    s = text.lstrip("\ufeff")
    # Remove // and /* */ comments
    s = re.sub(r"//.*", "", s)
    s = re.sub(r"/\*.*?\*/", "", s, flags=re.S)
    # Remove trailing commas before } or ]
    s = re.sub(r",\s*(\})", r"\1", s)
    s = re.sub(r",\s*(\])", r"\1", s)
    return json.loads(s)


def convert_record_v10_to_v101(data: Dict[str, Any]) -> Dict[str, Any]:
    out = json.loads(json.dumps(data))  # deep copy

    # 1) NM-1 + NM-2 -> NM-1 (ADCSL)
    nm = out.setdefault("normative_modeling_part", {})
    nm1_answer = nm.get("nm1_model_origin", "")
    nm2 = nm.pop("nm2_model_origin_detail", None) or {}
    nm1_adcs: Dict[str, Any] = {
        "answer": nm1_answer if isinstance(nm1_answer, str) else str(nm1_answer),
        "detail": "-",
        "confidence_rating": str(nm2.get("confidence_rating", "")),
        "supporting_text": str(nm2.get("supporting_text", "")),
        "location": str(nm2.get("location", "")),
    }
    # Prefer nm2.answer as the detail when pre-trained; otherwise "-"
    if isinstance(nm1_adcs["answer"], str) and nm1_adcs["answer"].strip().lower() == "pre-trained":
        nm1_adcs["detail"] = str(nm2.get("answer", "")).strip() or ""
    else:
        nm1_adcs["detail"] = "-"
    nm["nm1_model_origin"] = _ensure_adcs_fields(nm1_adcs)

    # 2) RCI-8 + RCI-9 -> RCI-8 (ADCSL)
    rci = out.setdefault("reference_cohort_and_imaging_part", {})
    rci8_answer = rci.get("rci8_quality_checking", "")
    rci9 = rci.pop("rci9_quality_checking_detail", None) or {}
    rci8_adcs: Dict[str, Any] = {
        "answer": rci8_answer if isinstance(rci8_answer, str) else str(rci8_answer),
        "detail": "",
        "confidence_rating": str(rci9.get("confidence_rating", "")),
        "supporting_text": str(rci9.get("supporting_text", "")),
        "location": str(rci9.get("location", "")),
    }
    ans8 = (rci8_adcs["answer"] or "").strip().lower()
    if ans8 == "yes":
        rci8_adcs["detail"] = str(rci9.get("answer", "")).strip() or ""
    elif ans8 == "no":
        rci8_adcs["detail"] = "-"
    else:
        # Unknown/empty; keep whatever detail existed
        rci8_adcs["detail"] = str(rci9.get("answer", "")).strip()
    rci["rci8_quality_checking"] = _ensure_adcs_fields(rci8_adcs)

    # 3) RCI-10 + RCI-11 -> RCI-9 (ADCSL)
    rci10_answer = rci.get("rci10_site_effect_handling", "")
    rci11 = rci.pop("rci11_site_effect_handling_detail", None) or {}
    rci10_adcs: Dict[str, Any] = {
        "answer": rci10_answer if isinstance(rci10_answer, str) else str(rci10_answer),
        "detail": "",
        "confidence_rating": str(rci11.get("confidence_rating", "")),
        "supporting_text": str(rci11.get("supporting_text", "")),
        "location": str(rci11.get("location", "")),
    }
    ans10 = (rci10_adcs["answer"] or "").strip().lower()
    if ans10 == "none":
        rci10_adcs["detail"] = "-"
    else:
        rci10_adcs["detail"] = str(rci11.get("answer", "")).strip() or ""
    rci["rci9_site_effect_handling"] = _ensure_adcs_fields(rci10_adcs)
    rci.pop("rci10_site_effect_handling", None)

    # 4) Renumber NM-3..11 -> NM-2..10
    nm_renames = [
        ("nm3_modeling_method", "nm2_modeling_method"),
        ("nm4_software_tool", "nm3_software_tool"),
        ("nm5_response_variable", "nm4_response_variable"),
        ("nm6_predictor_variables", "nm5_predictor_variables"),
        ("nm7_predictor_effects", "nm6_predictor_effects"),
        ("nm8_nm_vldtn_handle_ns", "nm7_nm_vldtn_handle_ns"),
        ("nm9_nm_vldtn_same_domain_nonindep", "nm8_nm_vldtn_same_domain_nonindep"),
        ("nm10_nm_vldtn_same_domain_indep", "nm9_nm_vldtn_same_domain_indep"),
        ("nm11_nm_vldtn_diff_domain", "nm10_nm_vldtn_diff_domain"),
    ]
    for old_key, new_key in nm_renames:
        if old_key in nm:
            nm[new_key] = nm.pop(old_key)

    return out


def derive_backup_path(input_path: Path) -> Path:
    base = input_path.with_name(f"{input_path.stem}_v10{input_path.suffix}")
    if not base.exists():
        return base
    i = 1
    while True:
        candidate = input_path.with_name(f"{input_path.stem}_v10_{i}{input_path.suffix}")
        if not candidate.exists():
            return candidate
        i += 1


def main() -> None:
    parser = argparse.ArgumentParser(description="Convert DE v10 JSON to v10_1 format in-place, backing up original to *_v10.json.")
    parser.add_argument("input", type=Path, help="Path to v10 JSON file (will be overwritten with v10_1)")
    args = parser.parse_args()

    in_path = args.input
    backup_path = derive_backup_path(in_path)

    with in_path.open("r", encoding="utf-8") as f:
        raw = f.read()
    try:
        data = json.loads(raw)
    except json.JSONDecodeError:
        # Fallback to lenient parser (comments/trailing commas)
        data = _loads_lenient(raw)

    converted = convert_record_v10_to_v101(data)

    # Write backup of original
    backup_path.parent.mkdir(parents=True, exist_ok=True)
    with backup_path.open("w", encoding="utf-8") as f:
        json.dump(data, f, ensure_ascii=False, indent=2)

    # Overwrite input with converted content
    with in_path.open("w", encoding="utf-8") as f:
        json.dump(converted, f, ensure_ascii=False, indent=2)

    print(f"Backed up v10 to: {backup_path}")
    print(f"Wrote v10_1 to:  {in_path}")


if __name__ == "__main__":
    main()
