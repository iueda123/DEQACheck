#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
Fill missing study_characteristics_part fields in Human JSONs from sibling codex JSONs.

Targets:
  - data/*/DE_v10/json/*Human*.json
  - data/*/DE_v10/json/*human*.json

Rules:
  - For each target file, if any of these fields are empty or missing,
    fill from a sibling JSON whose filename matches *codex*.json (case-insensitive):
      * study_characteristics_part/sc1_study_objective
      * study_characteristics_part/sc2_study_design
      * study_characteristics_part/sc3_study_design_other
  - Preserve any non-empty existing values in the target file.
  - If multiple codex files exist, use the most recently modified one.

Usage:
  From repo root:
    python3 tools/fillJson_DESC_Part.py
"""

from __future__ import annotations

import json
import sys
from pathlib import Path
from typing import Any, Dict, List, Tuple
import argparse


# Key paths to fill (top-level key, nested key)
KEY_PATHS: List[Tuple[str, str]] = [
    ("study_characteristics_part", "sc1_study_objective"),
    ("study_characteristics_part", "sc2_study_design"),
    ("study_characteristics_part", "sc3_study_design_other"),
]


def is_empty(value: Any) -> bool:
    """Judge emptiness with awareness of nested dict fields like 'answer'."""
    if value is None:
        return True
    if isinstance(value, str):
        return value.strip() == ""
    if isinstance(value, list):
        return len(value) == 0 or all(is_empty(v) for v in value)
    if isinstance(value, dict):
        if "answer" in value:
            return is_empty(value.get("answer"))
        return all(is_empty(v) for v in value.values()) if value else True
    return False


def read_json(path: Path) -> Dict[str, Any]:
    with path.open("r", encoding="utf-8") as f:
        return json.load(f)


def write_json(path: Path, data: Dict[str, Any]) -> None:
    with path.open("w", encoding="utf-8") as f:
        json.dump(data, f, ensure_ascii=False, indent=2)
        f.write("\n")


def find_codex_candidate(sibling_dir: Path, exclude: Path | None = None) -> Path | None:
    cand: List[Path] = []
    for p in sibling_dir.glob("*.json"):
        if exclude is not None and p.resolve() == exclude.resolve():
            continue
        if "codex" in p.name.lower():
            cand.append(p)
    if not cand:
        return None
    cand.sort(key=lambda p: p.stat().st_mtime, reverse=True)
    return cand[0]


def fill_from_codex(target_path: Path, *, dry_run: bool = False) -> Tuple[bool, List[str], Path | None]:
    changed = False
    updated_keys: List[str] = []

    try:
        target = read_json(target_path)
    except Exception as e:
        print(f"[SKIP] Failed to parse JSON: {target_path} ({e})", file=sys.stderr)
        return False, [], None

    codex_path = find_codex_candidate(target_path.parent, exclude=target_path)
    if codex_path is None:
        print(f"[WARN] No codex JSON found for: {target_path}")
        return False, [], None

    try:
        codex = read_json(codex_path)
    except Exception as e:
        print(f"[SKIP] Failed to parse codex JSON: {codex_path} ({e})", file=sys.stderr)
        return False, [], codex_path

    for top_key, sub_key in KEY_PATHS:
        tgt_top = target.get(top_key)
        tgt_val = None
        if isinstance(tgt_top, dict):
            tgt_val = tgt_top.get(sub_key)

        src_val = None
        src_top = codex.get(top_key)
        if isinstance(src_top, dict):
            src_val = src_top.get(sub_key)

        if is_empty(tgt_val) and not is_empty(src_val):
            if not isinstance(target.get(top_key), dict):
                target[top_key] = {}
            target[top_key][sub_key] = src_val
            changed = True
            updated_keys.append(f"{top_key}/{sub_key}")

    if changed and not dry_run:
        try:
            write_json(target_path, target)
        except Exception as e:
            print(f"[ERROR] Failed to write updated JSON: {target_path} ({e})", file=sys.stderr)
            return False, [], codex_path

    return changed, updated_keys, codex_path


def build_arg_parser() -> argparse.ArgumentParser:
    desc = (
        "Human JSON の study_characteristics_part 欠損項目を、同階層の codex JSON から補完します。\n"
        "対象: data/*/DE_v10/json/*[Hh]uman*.json（各フォルダ内の最新の *codex*.json を参照）"
    )
    epilog = (
        "空とみなす条件: 未定義/null/空文字/空配列/空オブジェクト。\n"
        "既存の非空値は保持します。複数の codex がある場合は更新日時が最新のものを使用します。"
    )
    p = argparse.ArgumentParser(
        prog="fillJson_DESC_Part",
        description=desc,
        epilog=epilog,
        formatter_class=argparse.ArgumentDefaultsHelpFormatter,
    )
    p.add_argument(
        "--run",
        action="store_true",
        help="指定時に書き込みを実行します（未指定ならドライラン）",
    )
    p.add_argument(
        "--root",
        type=Path,
        default=Path.cwd(),
        help="検索の起点となるリポジトリのルートディレクトリ",
    )
    return p


def main(argv: List[str]) -> int:
    parser = build_arg_parser()
    args = parser.parse_args(argv)

    root: Path = args.root.resolve()
    patterns = [
        "data/*/DE_v10/json/*Human*.json",
        "data/*/DE_v10/json/*human*.json",
    ]

    targets: List[Path] = []
    for pat in patterns:
        targets.extend(sorted(root.glob(pat)))

    if not targets:
        print("[INFO] No target Human JSON files found under data/*/DE_v10/json/.")
        return 0

    total = len(targets)
    changed_count = 0
    for idx, t in enumerate(targets, start=1):
        try:
            rel = t.relative_to(root)
        except Exception:
            rel = t
        changed, keys, codex_path = fill_from_codex(t, dry_run=(not args.run))
        if changed:
            changed_count += 1
            codex_rel = None
            if codex_path is not None:
                try:
                    codex_rel = codex_path.relative_to(root)
                except Exception:
                    codex_rel = codex_path
            keys_str = ", ".join(keys)
            if not args.run:
                print(f"[{idx}/{total}] Would update {rel} from {codex_rel}: {keys_str}")
            else:
                print(f"[{idx}/{total}] Updated {rel} from {codex_rel}: {keys_str}")
        else:
            print(f"[{idx}/{total}] No changes for {rel}")

    if not args.run:
        print(f"Dry-run complete. Would update {changed_count} / {total} files.")
    else:
        print(f"Done. Updated {changed_count} / {total} files.")
    return 0


if __name__ == "__main__":
    sys.exit(main(sys.argv[1:]))
