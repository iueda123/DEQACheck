#!/usr/bin/env python3
"""Check whether a JSON file matches the template structure."""
import json
import sys
from pathlib import Path

TEMPLATE_NAME = "DE_Author20XX_by_Someone_YYYYmmddHHMMSS_for_v10.json"
TEMPLATE_PATH = Path(__file__).resolve().parents[1] / "templates" / TEMPLATE_NAME


def compare_structure(template, target):
    """Recursively compare dictionaries/lists to ensure same structure and keys."""
    if isinstance(template, dict):
        if not isinstance(target, dict):
            return False
        template_keys = set(template.keys())
        target_keys = set(target.keys())
        if template_keys != target_keys:
            return False
        return all(compare_structure(template[key], target[key]) for key in template)

    if isinstance(template, list):
        if not isinstance(target, list) or len(template) != len(target):
            return False
        return all(compare_structure(t_item, target_item) for t_item, target_item in zip(template, target))

    return type(template) is type(target)


def load_json(path):
    with path.open("r", encoding="utf-8") as handle:
        return json.load(handle)


def main(argv):
    if len(argv) != 2:
        print("false")
        print("Usage: check_json_template_structure.py <json_file>", file=sys.stderr)
        return 1

    json_path = Path(argv[1]).expanduser().resolve()

    try:
        template_data = load_json(TEMPLATE_PATH)
        target_data = load_json(json_path)
    except (OSError, json.JSONDecodeError) as exc:
        print("false")
        print(f"Failed to load JSON: {exc}", file=sys.stderr)
        return 1

    matches = compare_structure(template_data, target_data)
    print("true" if matches else "false")
    return 0 if matches else 2


if __name__ == "__main__":
    sys.exit(main(sys.argv))
