#!/usr/bin/env python3
"""
Convert QA/DE JSON files to Markdown.

Usage:
  python json_to_md_v4.py input.json [output.md] [--overwrite]

Features:
- Auto-detects input type: QA (Quality Assessment) or DE (Data Extraction).
- For QA, preserves v3 layout and ordering to ensure compatibility.
- For DE, outputs a clear, ordered layout covering all sections.
- Snake_case → Title Case conversion with acronym support.
"""

from __future__ import annotations

import argparse
import json
import sys
from pathlib import Path
from typing import Dict, Iterable, List


# Common formatting helpers
LOWERCASE_WORDS = {"of", "and", "or", "the", "a", "an", "in", "on", "at", "to", "for", "with", "by"}
ACRONYMS = {
    # generic
    "id": "ID", "ids": "IDs", "doi": "DOI", "ai": "AI", "ml": "ML",
    # imaging
    "mri": "MRI", "fmri": "fMRI", "eeg": "EEG", "meg": "MEG", "pet": "PET",
    # modalities/metrics
    "dmri": "dMRI", "smri": "sMRI", "dti": "DTI", "auc": "AUC", "fd": "FD",
    # datasets/fields
    "hcp": "HCP", "adni": "ADNI", "abide": "ABIDE", "icv": "ICV",
    # analysis level
    "roi": "ROI", "rois": "ROIs", "idp": "IDP", "idps": "IDPs",
    # task-specific
    "hc": "HC", "nm": "NM", "ns": "NS",
}


def snake_to_title(snake_str: str) -> str:
    parts = str(snake_str).split("_")
    out: List[str] = []
    for i, w in enumerate(parts):
        lw = w.lower()
        if lw in ACRONYMS:
            out.append(ACRONYMS[lw])
            continue
        if i == 0 or lw not in LOWERCASE_WORDS:
            out.append(lw.capitalize())
        else:
            out.append(lw)
    return " ".join(out)


# === QA (v3-compatible) ===
GROUP_A_ORDER = [
    "clarity_of_research_objectives",
    "clear_definition_of_target_population",
    "clarity_of_inclusion_and_exclusion_criteria",
    "validity_of_normative_modeling_outcome_measures",
    "handling_of_confounding_variables",
    "clarity_of_data_sources",
    "description_of_image_acquisition_protocol",
]

GROUP_B_ORDER = [
    "details_of_data_preprocessing",
    "clarity_of_data_partitioning_methods",
    "details_of_normative_modeling_approach",
    "details_of_training_algorithm",
    "model_performance_evaluation_metrics",
    "implementation_of_internal_validation",
    "external_data_validation",
    "description_of_dataset_characteristics",
    "performance_metrics_and_statistical_uncertainty",
    "consideration_for_reproducibility",
    "interpretation_specific_to_normative_modeling",
]

FIELDS_ORDER = [
    "Answer",
    "Confidence Rating",
    "Negative Answer Category",
    "Reason",
    "Supporting Text",
    "Location",
]


def render_study_identification(study: Dict[str, str]) -> List[str]:
    out: List[str] = []
    out.append("## Study Identification\n")
    out.append(f"- **Study ID**: {study.get('study_id', '')}")
    out.append(f"- **Reference File Names**: {study.get('reference_file_names', '')}")
    out.append(f"- **Author, Journal, Year**: {study.get('author_journal_year', '')}")
    out.append(f"- **Title**: {study.get('title', '')}")
    out.append(f"- **DOI**: {study.get('doi', '')}")
    out.append("\n---\n")
    return out


def render_fields_block(item: Dict[str, str]) -> List[str]:
    out: List[str] = []
    for field in FIELDS_ORDER:
        jkey = field.lower().replace(" ", "_")
        out.append(f"- **{field}**: {item.get(jkey, '')}")
    return out


def json_to_markdown_qa(data: dict) -> str:
    out: List[str] = []
    out.append("# Quality Assessment Form\n")

    # Study Identification
    out.extend(render_study_identification(data.get("study_identification", {}) or {}))

    # Group A
    out.append("## Assessment Items - Group A\n")
    g_a = data.get("assessment_items_group_a", {}) or {}
    for i, key in enumerate(GROUP_A_ORDER, 1):
        if key not in g_a:
            continue
        out.append(f"### {i}. {snake_to_title(key)}\n")
        out.extend(render_fields_block(g_a.get(key, {}) or {}))
        out.append("")

    out.append("---\n")

    # Group B
    out.append("## Assessment Items - Group B\n")
    g_b = data.get("assessment_items_group_b", {}) or {}
    for i, key in enumerate(GROUP_B_ORDER, 1):
        if key not in g_b:
            continue
        out.append(f"### {i}. {snake_to_title(key)}\n")
        out.extend(render_fields_block(g_b.get(key, {}) or {}))
        out.append("")

    out.append("---\n")

    # Additional Comments
    out.append("## Additional Comments\n")
    addl = data.get("additional_comments", {}) or {}
    out.append(f"**Additional Comments**: {addl.get('additional_comments', '')}")
    out.append("")

    return "\n".join(out)


# === DE layout ===
DE_STUDY_CHAR_ORDER = [
    "study_objective",
    "study_design",
    "study_design_other",
]

DE_REF_COHORT_ORDER = [
    "dataset_name",
    "hc_n",
    "hc_age",
    "hc_sex",
    "imaging_modality",
    "analysis_level",
    "preprocessing_pipeline",
    "quality_checking",
    "quality_checking_detail",
    "site_effect_handling",
    "site_effect_handling_detail",
]

DE_NM_ORDER = [
    "model_origin",
    "model_origin_detail",
    "modeling_method",
    "software_tool",
    "response_variable",
    "predictor_variables",
    "predictor_effects",
    "nm_vldtn_handle_ns",
    "nm_vldtn_same_domain_nonindep",
    "nm_vldtn_same_domain_indep",
    "nm_vldtn_diff_domain",
]

DE_CLINICAL_OBJ_ORDER = [
    "clinical_dataset",
    "diseases_studied",
    "clinical_groups_n",
    "clinical_groups_age",
    "clinical_groups_sex",
    "deviation_metric",
]

DE_CLINICAL_TEXT_ORDER = [
    "association_analysis",
    "key_findings_brief",
    "key_findings_detailed",
    "key_limitations",
    "application_notes",
]


def render_simple_kv_section(title: str, kv: Dict[str, str], order: Iterable[str]) -> List[str]:
    out: List[str] = []
    out.append(f"## {title}\n")
    for key in order:
        out.append(f"- **{snake_to_title(key)}**: {kv.get(key, '')}")
    out.append("\n---\n")
    return out


def maybe_render_item_block(parent: Dict[str, dict], key: str) -> List[str]:
    out: List[str] = []
    if key not in parent:
        return out
    out.append(f"#### {snake_to_title(key)}")
    out.extend(render_fields_block(parent.get(key, {}) or {}))
    out.append("")
    return out


def json_to_markdown_de(data: dict) -> str:
    out: List[str] = []
    out.append("# Data Extraction Form\n")

    # Study Identification
    out.extend(render_study_identification(data.get("study_identification", {}) or {}))

    # Study Characteristics (simple key-value)
    out.extend(render_simple_kv_section(
        "Study Characteristics", data.get("study_characteristics", {}) or {}, DE_STUDY_CHAR_ORDER
    ))

    # Reference Cohort & Imaging (item blocks)
    out.append("## Reference Cohort & Imaging\n")
    ref = data.get("reference_cohort_and_imaging", {}) or {}
    for key in DE_REF_COHORT_ORDER:
        out.extend(maybe_render_item_block(ref, key))
    out.append("---\n")

    # Normative Modeling (item blocks)
    out.append("## Normative Modeling\n")
    nm = data.get("normative_modeling", {}) or {}
    for key in DE_NM_ORDER:
        out.extend(maybe_render_item_block(nm, key))
    out.append("---\n")

    # Clinical Application & Analysis (mix of item blocks and text)
    out.append("## Clinical Application & Analysis\n")
    clin = data.get("clinical_application_and_analysis", {}) or {}
    # object-style items
    for key in DE_CLINICAL_OBJ_ORDER:
        if key in clin and isinstance(clin.get(key), dict):
            out.extend(maybe_render_item_block(clin, key))
    # simple text fields
    for key in DE_CLINICAL_TEXT_ORDER:
        if key in clin and not isinstance(clin.get(key), dict):
            out.append(f"- **{snake_to_title(key)}**: {clin.get(key, '')}")
    out.append("\n---\n")

    # General Notes
    out.append("## General Notes\n")
    g = data.get("general_notes", {}) or {}
    out.append(f"**General Notes**: {g.get('general_notes', '')}")
    out.append("")

    return "\n".join(out)


def detect_type(data: dict) -> str:
    """Return 'QA' or 'DE' depending on keys present."""
    if any(k in data for k in ("assessment_items_group_a", "assessment_items_group_b")):
        return "QA"
    if any(k in data for k in (
        "study_characteristics", "reference_cohort_and_imaging", "normative_modeling", "clinical_application_and_analysis", "general_notes"
    )):
        return "DE"
    # Fallback: if structure resembles QA template fields
    if "additional_comments" in data:
        return "QA"
    return "DE"


def main() -> int:
    ap = argparse.ArgumentParser(description="Convert QA/DE JSON to Markdown (snake_case → Title Case).")
    ap.add_argument("input", help="Input JSON file")
    ap.add_argument("output", nargs="?", help="Output Markdown file (defaults to .md next to input)")
    ap.add_argument("--overwrite", action="store_true", help="Allow overwriting existing output file")
    args = ap.parse_args()

    in_path = Path(args.input)
    if not in_path.exists():
        print(f"Error: Input file '{in_path}' not found")
        return 2

    out_path = Path(args.output) if args.output else in_path.with_suffix(".md")
    if out_path.exists() and not args.overwrite:
        print(f"Error: Output file '{out_path}' already exists. Use --overwrite to replace it.")
        return 2

    try:
        data = json.loads(in_path.read_text(encoding="utf-8"))
    except Exception as e:
        print(f"Error reading JSON '{in_path}': {e}")
        return 2

    doc_type = detect_type(data)
    if doc_type == "QA":
        md = json_to_markdown_qa(data)
    else:
        md = json_to_markdown_de(data)

    try:
        out_path.write_text(md, encoding="utf-8")
    except Exception as e:
        print(f"Error writing Markdown '{out_path}': {e}")
        return 2

    print(f"Successfully converted '{in_path}' to '{out_path}' as {doc_type}")
    return 0


if __name__ == "__main__":
    sys.exit(main())

