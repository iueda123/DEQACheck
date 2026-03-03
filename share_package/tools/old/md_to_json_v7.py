#!/usr/bin/env python3
"""
Convert QA v7 Markdown (CM/NM/CR) to JSON.

Usage:
  python md_to_json_v7.py INPUT.md [output.json] [--overwrite]

Parses the v7 layout:
- Common Part (CM):
  - CM-1..5,7 are simple 'Answer' strings
  - CM-6 is a full item with fields (Answer, Confidence Rating, Reason, Supporting Text, Location)
- Normative Modeling Part (NM): NM-1..16 full items with fields
- Clinical Research Part (CR): CR-1..7 full items with fields
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


def parse_markdown_v7(md_text: str) -> Dict:
    lines = md_text.splitlines()

    common_part: Dict[str, object] = {}
    normative_modeling_part: Dict[str, Dict[str, str]] = {}
    clinical_research_part: Dict[str, Dict[str, str]] = {}

    # State
    current_section: Optional[str] = None  # 'cm' | 'nm' | 'cr'
    current_key: Optional[str] = None
    current_item: Optional[Dict[str, str]] = None
    current_field_key: Optional[str] = None
    field_buffer: list[str] = []

    # For simple CM items (string answers)
    current_simple_key: Optional[str] = None
    simple_buffer: list[str] = []

    # Patterns
    h2_re = re.compile(r"^##\s+(.+?)\s*$")
    # Accept headings like "### CM-2. Reference File Names" (optional CM/NM/CR prefix)
    h3_num_title_re = re.compile(r"^###\s+(?:[A-Za-z]{2}-)?(\d+)\.\s+(.*\S?)\s*$")
    bullet_field_re = re.compile(r"^-\s+\*\*(.+?)\*\*:\s*(.*)$")

    def commit_field():
        nonlocal current_item, current_field_key, field_buffer
        if current_item is not None and current_field_key is not None:
            current_item[current_field_key] = "\n".join(field_buffer).rstrip()
        current_field_key = None
        field_buffer = []

    def commit_simple():
        nonlocal current_simple_key, simple_buffer
        # Store only when we have a key; empty string acceptable
        if current_simple_key is not None:
            val = "\n".join(simple_buffer).rstrip()
            common_part[current_simple_key] = val
        current_simple_key = None
        simple_buffer = []

    def commit_item():
        nonlocal current_key, current_item
        if current_key and current_item is not None:
            if current_section == 'nm':
                normative_modeling_part[current_key] = current_item
            elif current_section == 'cr':
                clinical_research_part[current_key] = current_item
            elif current_section == 'cm':
                common_part[current_key] = current_item  # only CM-6 uses dict
        current_key = None
        current_item = None

    i = 0
    while i < len(lines):
        line = lines[i]

        # Section header
        m_h2 = h2_re.match(line)
        if m_h2:
            # finalize any pending structures
            commit_field()
            if current_item is not None:
                commit_item()
            if current_simple_key is not None:
                commit_simple()

            title = m_h2.group(1).strip().lower()
            if title.startswith("common part"):
                current_section = 'cm'
            elif title.startswith("normative modeling part"):
                current_section = 'nm'
            elif title.startswith("clinical research part"):
                current_section = 'cr'
            else:
                current_section = None

            i += 1
            continue

        # Horizontal rule: finalize within section
        if line.strip() == '---':
            commit_field()
            if current_item is not None:
                commit_item()
            if current_simple_key is not None:
                commit_simple()
            i += 1
            continue

        # Item header (### N. Title)
        m_item = h3_num_title_re.match(line)
        if m_item and current_section in ('cm', 'nm', 'cr'):
            # close previous
            commit_field()
            if current_item is not None:
                commit_item()
            if current_simple_key is not None:
                commit_simple()

            num = int(m_item.group(1))
            title = m_item.group(2).strip()
            tail = to_snake_case(title)
            key = f"{current_section}{num}_{tail}"

            # Determine simple vs dict for CM
            if current_section == 'cm' and num in {1, 2, 3, 4, 5, 7}:
                current_simple_key = key
                simple_buffer = []
                current_key = None
                current_item = None
                current_field_key = None
                field_buffer = []
            else:
                current_key = key
                current_item = {}
                current_field_key = None
                field_buffer = []
                current_simple_key = None
                simple_buffer = []

            i += 1
            continue

        # Bullet field
        m_b = bullet_field_re.match(line)
        if m_b and current_section in ('cm', 'nm', 'cr'):
            field_title, content = m_b.group(1).strip(), m_b.group(2)
            fkey = to_snake_case(field_title)
            if current_item is not None:
                commit_field()
                current_field_key = fkey
                field_buffer = [content]
            elif current_simple_key is not None:
                # Only capture 'Answer' line into simple buffer; ignore others
                if fkey == 'answer':
                    simple_buffer = [content]
            i += 1
            continue

        # Continuation lines
        if current_item is not None and current_field_key is not None:
            field_buffer.append(line)
            i += 1
            continue
        if current_simple_key is not None and simple_buffer:
            simple_buffer.append(line)
            i += 1
            continue

        i += 1

    # Finalize EOF
    commit_field()
    if current_item is not None:
        commit_item()
    if current_simple_key is not None:
        commit_simple()

    return {
        "common_part": common_part,
        "normative_modeling_part": normative_modeling_part,
        "clinical_research_part": clinical_research_part,
    }


def main() -> int:
    ap = argparse.ArgumentParser(description="Convert QA v7 Markdown to JSON.")
    ap.add_argument("input", help="Input Markdown file (v7)")
    ap.add_argument("output", nargs="?", help="Output JSON file (defaults to .json next to input)")
    ap.add_argument("--overwrite", action="store_true", help="Allow overwriting existing output file")
    args = ap.parse_args()

    in_path = Path(args.input)
    if not in_path.exists():
        print(f"Error: Input file '{in_path}' not found")
        return 2

    out_path = Path(args.output) if args.output else in_path.with_suffix(".json")
    if out_path.exists() and not args.overwrite:
        print(f"Error: Output file '{out_path}' exists. Use --overwrite to replace.")
        return 2

    try:
        md_text = in_path.read_text(encoding="utf-8")
    except Exception as e:
        print(f"Error reading '{in_path}': {e}")
        return 2

    data = parse_markdown_v7(md_text)

    try:
        out_path.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    except Exception as e:
        print(f"Error writing '{out_path}': {e}")
        return 2

    print(f"Converted '{in_path}' -> '{out_path}' (v7)")
    return 0


if __name__ == "__main__":
    sys.exit(main())

