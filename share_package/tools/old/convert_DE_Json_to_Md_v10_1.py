#!/usr/bin/env python3
"""
Convert DE v10_1 JSON to Markdown.

Usage:
  python tools/convert_DE_Json_to_Md_v10_1.py -i templates/DE_Author20XX_by_Someone_YYYYmmddHHMMSS_for_v10_1.json \
      -o out.md

If -o is omitted, writes alongside input with .md extension.
"""
from __future__ import annotations

import argparse
import json
from pathlib import Path
from typing import Any, Dict, List


def read_json(path: Path) -> Dict[str, Any]:
    with path.open("r", encoding="utf-8") as f:
        return json.load(f)


def get(d: Dict[str, Any], *keys: str, default: str = "") -> str:
    cur: Any = d
    for k in keys:
        if not isinstance(cur, dict) or k not in cur:
            return default
        cur = cur[k]
    if cur is None:
        return default
    return str(cur)


def line(label: str, value: str) -> str:
    return f"- **{label}**: {value}".rstrip()


def render(json_data: Dict[str, Any]) -> str:
    out: List[str] = []
    o = out.append

    # Top title
    o("# Data Extraction Form")
    o("")

    # Study Identification (SI)
    si = json_data.get("study_identification_part", {})
    o("## Study Identification (SI)")
    o("")
    o(line("Study ID", get(si, "si1_study_id")))
    o(line("Reference File Names", get(si, "si2_reference_file_names")))
    o(line("Author, Journal, Year", get(si, "si3_author_journal_year")))
    o(line("Title", get(si, "si4_title")))
    o(line("DOI", get(si, "si5_doi")))
    o("")
    o("---")
    o("")

    # Study Characteristics (SC)
    sc = json_data.get("study_characteristics_part", {})
    o("## Study Characteristics (SC)")
    o("")
    o(line("Study Objective", get(sc, "sc1_study_objective")))
    o(line("Study Design", get(sc, "sc2_study_design")))
    o(line("Study Design Other", get(sc, "sc3_study_design_other")))
    o("")
    o("---")
    o("")

    # Reference Cohort & Imaging (RCI)
    rci = json_data.get("reference_cohort_and_imaging_part", {})
    o("## Reference Cohort & Imaging (RCI)")
    o("")

    def object_block(title: str, node_key: str, fields: List[str]):
        node = rci.get(node_key, {})
        o(f"#### {title}")
        for fld in fields:
            o(line(fld, get(node, fld.lower().replace(" ", "_"))))
        o("")

    object_block("RCI-1. Dataset Name", "rci1_dataset_name",
                 ["Answer", "Confidence Rating", "Reason", "Supporting Text", "Location"])
    object_block("RCI-2. HC N", "rci2_hc_n",
                 ["Answer", "Confidence Rating", "Reason", "Supporting Text", "Location"])
    object_block("RCI-3. HC Age", "rci3_hc_age",
                 ["Answer", "Confidence Rating", "Reason", "Supporting Text", "Location"])
    object_block("RCI-4. HC Sex", "rci4_hc_sex",
                 ["Answer", "Confidence Rating", "Reason", "Supporting Text", "Location"])
    object_block("RCI-5. Imaging Modality", "rci5_imaging_modality",
                 ["Answer", "Confidence Rating", "Reason", "Supporting Text", "Location"])

    # rci6 has reduced fields
    node = rci.get("rci6_analysis_level", {})
    o("#### RCI-6. Analysis Level")
    o(line("Answer", get(node, "answer")))
    o(line("Supporting Text", get(node, "supporting_text")))
    o(line("Location", get(node, "location")))
    o("")

    object_block("RCI-7. Preprocessing Pipeline", "rci7_preprocessing_pipeline",
                 ["Answer", "Confidence Rating", "Reason", "Supporting Text", "Location"])

    # rci8 & rci9 include Detail
    for code, key, title in (
        ("RCI-8", "rci8_quality_checking", "Quality Checking"),
        ("RCI-9", "rci9_site_effect_handling", "Site Effect Handling"),
    ):
        node = rci.get(key, {})
        o(f"#### {code}. {title}")
        o(line("Answer", get(node, "answer")))
        o(line("Detail", get(node, "detail")))
        o(line("Confidence Rating", get(node, "confidence_rating")))
        o(line("Supporting Text", get(node, "supporting_text")))
        o(line("Location", get(node, "location")))
        o("")

    o("---")
    o("")

    # Normative Modeling (NM)
    nm = json_data.get("normative_modeling_part", {})
    o("## Normative Modeling (NM)")
    o("")

    # nm1 with Detail
    node = nm.get("nm1_model_origin", {})
    o("#### NM-1. Model Origin")
    o(line("Answer", get(node, "answer")))
    o(line("Detail", get(node, "detail")))
    o(line("Confidence Rating", get(node, "confidence_rating")))
    o(line("Supporting Text", get(node, "supporting_text")))
    o(line("Location", get(node, "location")))
    o("")

    def nm_block(code: int, key: str, title: str):
        node = nm.get(key, {})
        o(f"#### NM-{code}. {title}")
        o(line("Answer", get(node, "answer")))
        o(line("Confidence Rating", get(node, "confidence_rating")))
        o(line("Reason", get(node, "reason")))
        o(line("Supporting Text", get(node, "supporting_text")))
        o(line("Location", get(node, "location")))
        o("")

    nm_block(2, "nm2_modeling_method", "Modeling Method")
    nm_block(3, "nm3_software_tool", "Software Tool")
    nm_block(4, "nm4_response_variable", "Response Variable")
    nm_block(5, "nm5_predictor_variables", "Predictor Variables")
    nm_block(6, "nm6_predictor_effects", "Predictor Effects")
    nm_block(7, "nm7_nm_vldtn_handle_ns", "NM Vldtn Handle NS")
    nm_block(8, "nm8_nm_vldtn_same_domain_nonindep", "NM Vldtn Same Domain Nonindep")
    nm_block(9, "nm9_nm_vldtn_same_domain_indep", "NM Vldtn Same Domain Indep")
    nm_block(10, "nm10_nm_vldtn_diff_domain", "NM Vldtn Diff Domain")

    o("---")
    o("")

    # Clinical Application & Analysis (CAA)
    caa = json_data.get("clinical_application_and_analysis_part", {})
    o("## Clinical Application & Analysis (CAA)")
    o("")

    def caa_block(code: int, key: str, title: str, fields: List[str] | None = None):
        node = caa.get(key, {})
        o(f"#### CAA-{code}. {title}")
        if fields is None:
            fields = ["Answer", "Confidence Rating", "Reason", "Supporting Text", "Location"]
        for fld in fields:
            # map label -> json key
            json_key = fld.lower().replace(" ", "_")
            o(line(fld, get(node, json_key)))
        o("")

    caa_block(1, "caa1_clinical_dataset", "Clinical Dataset")
    caa_block(2, "caa2_diseases_studied", "Diseases Studied")
    caa_block(3, "caa3_clinical_groups_n", "Clinical Groups N")
    caa_block(4, "caa4_clinical_groups_age", "Clinical Groups Age")
    caa_block(5, "caa5_clinical_groups_sex", "Clinical Groups Sex")
    caa_block(6, "caa6_deviation_metric", "Deviation Metric")

    # String fields with dedicated headings
    o("#### CAA-7. Association Analysis")
    o(line("Association Analysis", get(caa, "caa7_association_analysis")))
    o("")

    o("#### CAA-8. Key Findings Brief")
    o(line("Key Findings Brief", get(caa, "caa8_key_findings_brief")))
    o("")

    o("#### CAA-9. Key Findings Detailed")
    o(line("Key Findings Detailed", get(caa, "caa9_key_findings_detailed")))
    o("")

    # caa10 limited fields
    node = caa.get("caa10_key_limitations", {})
    o("#### CAA-10. Key Limitations")
    o(line("Answer", get(node, "answer")))
    o(line("Supporting Text", get(node, "supporting_text")))
    o(line("Location", get(node, "location")))
    o("")

    o("#### CAA-11. Application Notes")
    o(line("Application Notes", get(caa, "caa11_application_notes")))
    o("")

    o("---")
    o("")

    # General Notes (GN)
    gn = json_data.get("general_note_part", {})
    o("## General Notes (GN)")
    o("")
    o(f"**General Notes**: {get(gn, 'gn1_general_notes')}")

    return "\n".join(out).rstrip() + "\n"


def main():
    ap = argparse.ArgumentParser(description="Convert DE v10_1 JSON to Markdown")
    ap.add_argument("-i", "--input", required=True, help="Path to v10_1 JSON file")
    ap.add_argument("-o", "--output", help="Output Markdown path; defaults to input with .md")
    args = ap.parse_args()

    in_path = Path(args.input)
    if not in_path.exists():
        raise SystemExit(f"Input file not found: {in_path}")

    out_path = Path(args.output) if args.output else in_path.with_suffix(".md")

    data = read_json(in_path)
    md = render(data)

    out_path.parent.mkdir(parents=True, exist_ok=True)
    with out_path.open("w", encoding="utf-8", newline="\n") as f:
        f.write(md)

    print(f"Wrote: {out_path}")


if __name__ == "__main__":
    main()
