#!/usr/bin/env python3
"""
Convert QA/DE Markdown files to JSON, mapping headings/labels to snake_case.

Usage:
  python md_to_json_v3.py INPUT.md [output.json] [--overwrite]

Improvements over v2:
- Auto-detects QA vs DE Markdown layouts.
- Parses DE sections: Study Characteristics, Reference Cohort & Imaging,
  Normative Modeling, Clinical Application & Analysis, and General Notes.
- Supports both H3 (###) and H4 (####) item headings in DE sections.
- Preserves v2 QA parsing behavior and structure for compatibility.
"""

from __future__ import annotations

import argparse
import json
import re
import sys
from pathlib import Path
from typing import Dict, Optional


def to_snake_case(text: str) -> str:
    if not isinstance(text, str):
        return text
    s = text.strip()
    s = re.sub(r"[\s\-./]+", " ", s)
    s = re.sub(r"(?<=[a-z0-9])([A-Z])", r" \1", s)
    s = re.sub(r"([A-Z]+)([A-Z][a-z])", r"\1 \2", s)
    s = re.sub(r"[^A-Za-z0-9 _]", "", s)
    s = s.lower().replace(" ", "_")
    s = re.sub(r"_+", "_", s).strip("_")
    return s


def parse_markdown(md_text: str) -> Dict:
    lines = md_text.splitlines()

    # Buckets we may populate depending on detected doc type
    study_identification: Dict[str, str] = {
        "study_id": "",
        "reference_file_names": "",
        "author_journal_year": "",
        "title": "",
        "doi": "",
    }

    qa_group_a: Dict[str, Dict[str, str]] = {}
    qa_group_b: Dict[str, Dict[str, str]] = {}
    qa_additional_comments: Dict[str, str] = {"additional_comments": ""}

    de_study_char: Dict[str, str] = {}
    de_ref: Dict[str, Dict[str, str]] = {}
    de_nm: Dict[str, Dict[str, str]] = {}
    de_clin: Dict[str, object] = {}
    de_general_notes: Dict[str, str] = {"general_notes": ""}

    # Parser state
    section_key: Optional[str] = None
    current_group: Optional[Dict[str, object]] = None  # for QA group A/B

    current_item_key: Optional[str] = None
    current_item: Optional[Dict[str, str]] = None
    current_field_key: Optional[str] = None
    field_buffer: list[str] = []

    # For DE simple key-values within complex sections when not inside an item
    current_simple_field_key: Optional[str] = None
    simple_field_buffer: list[str] = []

    # Detection hint
    doc_hint: Optional[str] = None  # "QA" or "DE"

    # Patterns
    h2_re = re.compile(r"^##\s+(.+?)\s*$")
    h3_re = re.compile(r"^###\s+(?:\d+\.\s+)?(.+?)\s*$")
    h4_re = re.compile(r"^####\s+(.+?)\s*$")
    bullet_field_re = re.compile(r"^-\s+\*\*(.+?)\*\*:\s*(.*)$")
    addl_line_re = re.compile(r"^\*\*(Additional Comments)\*\*:\s*(.*)$")
    gnote_line_re = re.compile(r"^\*\*(General Notes)\*\*:\s*(.*)$")

    def commit_field():
        nonlocal current_item, current_field_key, field_buffer
        if current_item is not None and current_field_key is not None:
            val = "\n".join(field_buffer).rstrip()
            current_item[current_field_key] = val
        current_field_key = None
        field_buffer = []

    def commit_item_to(target: Dict[str, Dict[str, str]]):
        nonlocal current_item_key, current_item
        if current_item_key and current_item is not None:
            target[current_item_key] = current_item
        current_item_key = None
        current_item = None

    def commit_simple_field(target: Dict[str, object]):
        nonlocal current_simple_field_key, simple_field_buffer
        if current_simple_field_key:
            target[current_simple_field_key] = "\n".join(simple_field_buffer).rstrip()
        current_simple_field_key = None
        simple_field_buffer = []

    i = 0
    while i < len(lines):
        line = lines[i]

        # Section header (## ...)
        m_h2 = h2_re.match(line)
        if m_h2:
            # Finalize any pending stuff
            commit_field()
            if section_key == "assessment_items_group_a":
                commit_item_to(qa_group_a)
            elif section_key == "assessment_items_group_b":
                commit_item_to(qa_group_b)
            elif section_key == "reference_cohort_and_imaging":
                commit_item_to(de_ref)
                commit_simple_field(de_ref)  # no-op; de_ref is of dicts only
            elif section_key == "normative_modeling":
                commit_item_to(de_nm)
                commit_simple_field(de_nm)  # no-op; de_nm is of dicts only
            elif section_key == "clinical_application_and_analysis":
                commit_item_to(de_clin)  # type: ignore[arg-type]
                commit_simple_field(de_clin)

            heading = m_h2.group(1).strip()
            norm = to_snake_case(heading)

            # Map to known sections
            if norm.startswith("study_identification"):
                section_key = "study_identification"
            elif norm.startswith("assessment_items_group_a"):
                section_key = "assessment_items_group_a"
                doc_hint = doc_hint or "QA"
            elif norm.startswith("assessment_items_group_b"):
                section_key = "assessment_items_group_b"
                doc_hint = doc_hint or "QA"
            elif norm.startswith("additional_comments"):
                section_key = "additional_comments"
                doc_hint = doc_hint or "QA"
            elif norm.startswith("study_characteristics"):
                section_key = "study_characteristics"
                doc_hint = doc_hint or "DE"
            elif norm.startswith("reference_cohort"):
                section_key = "reference_cohort_and_imaging"
                doc_hint = doc_hint or "DE"
            elif norm.startswith("normative_modeling"):
                section_key = "normative_modeling"
                doc_hint = doc_hint or "DE"
            elif norm.startswith("clinical_application"):
                section_key = "clinical_application_and_analysis"
                doc_hint = doc_hint or "DE"
            elif norm.startswith("general_notes"):
                section_key = "general_notes"
                doc_hint = doc_hint or "DE"
            else:
                # Unknown H2; treat as 'other'
                section_key = to_snake_case(heading)

            # Reset item/simple-field state
            current_item_key = None
            current_item = None
            current_field_key = None
            field_buffer = []
            current_simple_field_key = None
            simple_field_buffer = []

            i += 1
            continue

        # Horizontal rule: commit and reset within section
        if line.strip() == "---":
            commit_field()
            if section_key == "assessment_items_group_a":
                commit_item_to(qa_group_a)
            elif section_key == "assessment_items_group_b":
                commit_item_to(qa_group_b)
            elif section_key == "reference_cohort_and_imaging":
                commit_item_to(de_ref)
                commit_simple_field(de_ref)
            elif section_key == "normative_modeling":
                commit_item_to(de_nm)
                commit_simple_field(de_nm)
            elif section_key == "clinical_application_and_analysis":
                commit_item_to(de_clin)  # type: ignore[arg-type]
                commit_simple_field(de_clin)
            i += 1
            continue

        # Study Identification bullets
        if section_key == "study_identification":
            m_b = bullet_field_re.match(line)
            if m_b:
                field_title, content = m_b.group(1).strip(), m_b.group(2)
                key = to_snake_case(field_title)
                study_identification[key] = content.strip()
            i += 1
            continue

        # QA Assessment Groups (A/B)
        if section_key in ("assessment_items_group_a", "assessment_items_group_b"):
            # Item header
            m_h3 = h3_re.match(line)
            if m_h3:
                commit_field()
                if section_key == "assessment_items_group_a":
                    commit_item_to(qa_group_a)
                else:
                    commit_item_to(qa_group_b)
                item_title = m_h3.group(1).strip()
                current_item_key = to_snake_case(item_title)
                current_item = {}
                i += 1
                continue

            # Field bullets within an item
            m_b = bullet_field_re.match(line)
            if m_b and current_item is not None:
                commit_field()
                field_title, content = m_b.group(1).strip(), m_b.group(2)
                current_field_key = to_snake_case(field_title)
                field_buffer = [content] if content is not None else [""]
                i += 1
                continue

            # Continuation lines for current field
            if current_item is not None and current_field_key is not None:
                field_buffer.append(line)
                i += 1
                continue

            i += 1
            continue

        # QA Additional Comments
        if section_key == "additional_comments":
            m_add = addl_line_re.match(line)
            if m_add:
                buf = [m_add.group(2)]
                j = i + 1
                while j < len(lines):
                    nxt = lines[j]
                    if nxt.startswith("## ") or nxt.startswith("### ") or nxt.strip() == "---":
                        break
                    buf.append(nxt)
                    j += 1
                qa_additional_comments["additional_comments"] = "\n".join(buf).rstrip()
                i = j
                continue
            i += 1
            continue

        # DE Study Characteristics: simple KV bullets
        if section_key == "study_characteristics":
            m_b = bullet_field_re.match(line)
            if m_b:
                field_title, content = m_b.group(1).strip(), m_b.group(2)
                key = to_snake_case(field_title)
                de_study_char[key] = content.strip()
            i += 1
            continue

        # DE Reference Cohort & Imaging and Normative Modeling and Clinical Application
        if section_key in ("reference_cohort_and_imaging", "normative_modeling", "clinical_application_and_analysis"):
            # Item header: accept ### or ####
            m_h3 = h3_re.match(line)
            m_h4 = h4_re.match(line) if not m_h3 else None
            if m_h3 or m_h4:
                commit_field()
                # commit any pending simple field if we were in simple mode
                if section_key == "clinical_application_and_analysis":
                    commit_simple_field(de_clin)
                # finalize previous item into appropriate target
                if section_key == "reference_cohort_and_imaging":
                    commit_item_to(de_ref)
                elif section_key == "normative_modeling":
                    commit_item_to(de_nm)
                elif section_key == "clinical_application_and_analysis":
                    commit_item_to(de_clin)  # type: ignore[arg-type]

                item_title = (m_h3 or m_h4).group(1).strip()
                current_item_key = to_snake_case(item_title)
                current_item = {}
                i += 1
                continue

            # Field bullet
            m_b = bullet_field_re.match(line)
            if m_b:
                field_title, content = m_b.group(1).strip(), m_b.group(2)
                fkey = to_snake_case(field_title)
                item_fields = {"answer", "confidence_rating", "negative_answer_category", "reason", "supporting_text", "location"}

                if current_item is not None and fkey in item_fields:
                    # inside an item: standard field
                    commit_field()
                    current_field_key = fkey
                    field_buffer = [content] if content is not None else [""]
                elif section_key == "clinical_application_and_analysis":
                    # simple kv under clinical section; close any open item first
                    commit_field()
                    commit_item_to(de_clin)  # type: ignore[arg-type]
                    commit_simple_field(de_clin)
                    current_simple_field_key = fkey
                    simple_field_buffer = [content]
                else:
                    # Unexpected simple kv in other sections: ignore or treat as continuation
                    # Here we choose to ignore to avoid corrupting structure.
                    pass
                i += 1
                continue

            # Continuation of current field or simple field
            if current_item is not None and current_field_key is not None:
                field_buffer.append(line)
                i += 1
                continue
            if current_simple_field_key is not None:
                simple_field_buffer.append(line)
                i += 1
                continue

            i += 1
            continue

        # DE General Notes
        if section_key == "general_notes":
            m_gn = gnote_line_re.match(line)
            if m_gn:
                buf = [m_gn.group(2)]
                j = i + 1
                while j < len(lines):
                    nxt = lines[j]
                    if nxt.startswith("## ") or nxt.startswith("### ") or nxt.startswith("#### ") or nxt.strip() == "---":
                        break
                    buf.append(nxt)
                    j += 1
                de_general_notes["general_notes"] = "\n".join(buf).rstrip()
                i = j
                continue
            i += 1
            continue

        # default
        i += 1

    # Finalize at EOF
    commit_field()
    if section_key == "assessment_items_group_a":
        commit_item_to(qa_group_a)
    elif section_key == "assessment_items_group_b":
        commit_item_to(qa_group_b)
    elif section_key == "reference_cohort_and_imaging":
        commit_item_to(de_ref)
        commit_simple_field(de_ref)
    elif section_key == "normative_modeling":
        commit_item_to(de_nm)
        commit_simple_field(de_nm)
    elif section_key == "clinical_application_and_analysis":
        commit_item_to(de_clin)  # type: ignore[arg-type]
        commit_simple_field(de_clin)

    # Determine document type
    doc_type = doc_hint
    if not doc_type:
        # Heuristics based on content presence
        if qa_group_a or qa_group_b or any("additional_comments" in ln for ln in lines):
            doc_type = "QA"
        elif de_study_char or de_ref or de_nm or de_clin or any("General Notes" in ln for ln in lines):
            doc_type = "DE"
        else:
            doc_type = "QA"  # fallback

    if doc_type == "QA":
        return {
            "study_identification": study_identification,
            "assessment_items_group_a": qa_group_a,
            "assessment_items_group_b": qa_group_b,
            "additional_comments": qa_additional_comments,
        }
    else:
        return {
            "study_identification": study_identification,
            "study_characteristics": de_study_char,
            "reference_cohort_and_imaging": de_ref,
            "normative_modeling": de_nm,
            "clinical_application_and_analysis": de_clin,
            "general_notes": de_general_notes,
        }


def main() -> int:
    ap = argparse.ArgumentParser(description="Convert QA/DE Markdown to JSON (v3).")
    ap.add_argument("input", nargs="?", help="Input Markdown file (default: unique QA_*.md or DE_*.md in CWD)")
    ap.add_argument("output", nargs="?", help="Output JSON file (default: input with .json suffix)")
    ap.add_argument("--overwrite", action="store_true", help="Allow overwriting existing output file")
    args = ap.parse_args()

    # Determine input file
    input_path: Optional[Path]
    if args.input:
        input_path = Path(args.input)
    else:
        candidates = sorted(list(Path.cwd().glob("QA_*.md")) + list(Path.cwd().glob("DE_*.md")))
        input_path = candidates[0] if len(candidates) == 1 else None

    if not input_path or not input_path.exists():
        print("Error: specify an existing Markdown input file.")
        return 2

    # Determine output path
    if args.output:
        output_path = Path(args.output)
    else:
        output_path = input_path.with_suffix(".json")

    if output_path.exists() and not args.overwrite:
        print(f"Error: Output file '{output_path}' exists. Use --overwrite to replace.")
        return 2

    try:
        md_text = input_path.read_text(encoding="utf-8")
    except Exception as e:
        print(f"Error reading '{input_path}': {e}")
        return 2

    data = parse_markdown(md_text)

    try:
        with output_path.open("w", encoding="utf-8") as f:
            json.dump(data, f, ensure_ascii=False, indent=2)
            f.write("\n")
    except Exception as e:
        print(f"Error writing '{output_path}': {e}")
        return 2

    print(f"Converted '{input_path}' -> '{output_path}'")
    return 0


if __name__ == "__main__":
    sys.exit(main())
