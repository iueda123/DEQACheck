#!/usr/bin/env python3
"""Remove empty disorders entries from DE_v14 human JSON files."""

from __future__ import annotations

import json
from pathlib import Path


ROOT = Path(__file__).resolve().parents[2]
TARGET_GLOB = "share_package/data/*/DE_v14/json/DE_v14_*_by_human_*.json"


def should_remove(item: object) -> bool:
    if not isinstance(item, dict):
        return False
    disorder_name = item.get("disorder-name")
    if not isinstance(disorder_name, dict):
        return False
    return disorder_name.get("answer") == ""


def main() -> int:
    changed_files = 0
    removed_items = 0

    for path in sorted(ROOT.glob(TARGET_GLOB)):
        data = json.loads(path.read_text(encoding="utf-8"))
        disorders = data.get("disorders")
        if not isinstance(disorders, list):
            continue

        filtered = [item for item in disorders if not should_remove(item)]
        removed = len(disorders) - len(filtered)
        if removed == 0:
            continue

        data["disorders"] = filtered
        path.write_text(
            json.dumps(data, ensure_ascii=False, indent=2) + "\n",
            encoding="utf-8",
        )
        changed_files += 1
        removed_items += removed
        print(f"updated {path} removed={removed}")

    print(f"changed_files={changed_files}")
    print(f"removed_items={removed_items}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
