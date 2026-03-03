#!/usr/bin/env python3
"""
Convert QA v7 JSON (CM/NM/CR) to Markdown.

Usage:
  python json_to_md_v7.py input.json [output.md] [--overwrite]

Notes:
- Expects the v7 structure with top-level keys: common_part, normative_modeling_part, clinical_research_part.
- Renders section headers and numbered items (CM-#, NM-#, CR-#) with field lists.
"""

from __future__ import annotations

import argparse
import json
import re
import sys
from pathlib import Path
from typing import Dict, Iterable, List, Tuple


# Common formatting helpers (aligned with v6 for consistency)
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
    "hc": "HC", "nm": "NM", "ns": "NS", "cr": "CR",
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


FIELD_ORDER_V7 = [
    ("answer", "Answer"),
    ("confidence_rating", "Confidence Rating"),
    ("reason", "Reason"),
    ("supporting_text", "Supporting Text"),
    ("location", "Location"),
]


def extract_num_and_label(raw_key: str) -> Tuple[int, str]:
    """Extract numeric index and the remaining label from keys like 'cm6_clarity_of_research_objectives'."""
    m = re.match(r"^[a-zA-Z]+(\d+)_?(.*)$", raw_key)
    if not m:
        return (0, snake_to_title(raw_key))
    num = int(m.group(1)) if m.group(1) else 0
    label = m.group(2) or ""
    # In case of empty label (e.g., cm1), return as-is
    return (num, snake_to_title(label) if label else snake_to_title(raw_key))


SPECIAL_LABELS = {
    # Match desired punctuation for specific keys
    "cm3_author_journal_year": "Author, Journal, Year",
}


def render_item_block(prefix: str, key: str, value) -> List[str]:
    """Render an item under a section with a numbered heading and fields.

    - prefix: 'CM' | 'NM' | 'CR'
    - key: raw key like 'cm6_clarity_of_research_objectives'
    - value: either a string (simple answer) or a dict with detailed fields
    """
    num, label = extract_num_and_label(key)
    label = SPECIAL_LABELS.get(key, label)
    heading = f"### {prefix}-{num}. {label}"
    out: List[str] = [heading, ""]

    if isinstance(value, dict):
        for jkey, title in FIELD_ORDER_V7:
            out.append(f"- **{title}**: {value.get(jkey, '')}")
    else:
        out.append(f"- **Answer**: {'' if value is None else value}")

    out.append("")
    return out


def render_section(title: str, prefix: str, section: Dict[str, object]) -> List[str]:
    out: List[str] = []
    out.append(f"## {title}")
    out.append("")

    # Sort keys by their embedded number to ensure stable order
    def sort_key(k: str) -> int:
        m = re.match(r"^[a-zA-Z]+(\d+)", k)
        return int(m.group(1)) if m else 0

    for k in sorted(section.keys(), key=sort_key):
        out.extend(render_item_block(prefix, k, section[k]))

    return out


def json_to_markdown_v7(data: dict) -> str:
    out: List[str] = []
    out.append("# Quality Assessment Form (v7)")
    out.append("")

    # Common Part (CM)
    cm = data.get("common_part", {}) or {}
    out.extend(render_section("Common Part (CM)", "CM", cm))
    out.append("---")
    out.append("")

    # Normative Modeling Part (NM)
    nm = data.get("normative_modeling_part", {}) or {}
    out.extend(render_section("Normative Modeling Part (NM)", "NM", nm))
    out.append("---")
    out.append("")

    # Clinical Research Part (CR)
    cr = data.get("clinical_research_part", {}) or {}
    out.extend(render_section("Clinical Research Part (CR)", "CR", cr))

    return "\n".join(out) + "\n"


def main() -> int:
    ap = argparse.ArgumentParser(description="Convert QA v7 JSON to Markdown.")
    ap.add_argument("input", help="Input JSON file (v7)")
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

    try:
        md = json_to_markdown_v7(data)
    except Exception as e:
        print(f"Error converting to Markdown: {e}")
        return 2

    try:
        out_path.write_text(md, encoding="utf-8")
    except Exception as e:
        print(f"Error writing Markdown '{out_path}': {e}")
        return 2

    print(f"Successfully converted '{in_path}' to '{out_path}' (v7)")
    return 0


if __name__ == "__main__":
    sys.exit(main())
