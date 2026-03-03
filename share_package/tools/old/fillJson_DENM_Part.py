#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
Fill one normative_modeling_part field in Human JSONs from sibling codex JSONs.

Targets:
  - data/*/DE_v10/json/*Human*.json
  - data/*/DE_v10/json/*human*.json

Rules:
  - For each target file, if the specified field is empty or missing,
    fill from a sibling JSON whose filename matches *codex*.json (case-insensitive).
  - Preserve any non-empty existing values in the target file.
  - If multiple codex files exist, use the most recently modified one.

Select field with required option --item:
  1: nm1_model_origin
  2: nm2_modeling_method
  3: nm3_software_tool
  4: nm4_response_variable
  5: nm5_predictor_variables
  6: nm6_predictor_effects
  7: nm7_nm_vldtn_handle_ns
  8: nm8_nm_vldtn_same_domain_nonindep
  9: nm9_nm_vldtn_same_domain_indep
  10: nm10_nm_vldtn_diff_domain

Usage:
  From repo root:
    python3 tools/fillJson_DENM_Part.py --item 1 --dry-run
    python3 tools/fillJson_DENM_Part.py --item 6
"""

from __future__ import annotations

import json
import sys
from pathlib import Path
from typing import Any, Dict, List, Tuple
import argparse


KEY_ROOT = "normative_modeling_part"

# Map numeric selector to sub-key name
ITEM_MAP: Dict[int, str] = {
    1: "nm1_model_origin",
    2: "nm2_modeling_method",
    3: "nm3_software_tool",
    4: "nm4_response_variable",
    5: "nm5_predictor_variables",
    6: "nm6_predictor_effects",
    7: "nm7_nm_vldtn_handle_ns",
    8: "nm8_nm_vldtn_same_domain_nonindep",
    9: "nm9_nm_vldtn_same_domain_indep",
    10: "nm10_nm_vldtn_diff_domain",
}


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


def fill_one_field_from_codex(target_path: Path, sub_key: str, *, dry_run: bool = False) -> Tuple[bool, List[str], Path | None]:
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

    tgt_top = target.get(KEY_ROOT)
    tgt_val = tgt_top.get(sub_key) if isinstance(tgt_top, dict) else None

    src_top = codex.get(KEY_ROOT)
    src_val = src_top.get(sub_key) if isinstance(src_top, dict) else None

    if is_empty(tgt_val) and not is_empty(src_val):
        if not isinstance(target.get(KEY_ROOT), dict):
            target[KEY_ROOT] = {}
        target[KEY_ROOT][sub_key] = src_val
        changed = True
        updated_keys.append(f"{KEY_ROOT}/{sub_key}")

    if changed and not dry_run:
        try:
            write_json(target_path, target)
        except Exception as e:
            print(f"[ERROR] Failed to write updated JSON: {target_path} ({e})", file=sys.stderr)
            return False, [], codex_path

    return changed, updated_keys, codex_path


def build_arg_parser() -> argparse.ArgumentParser:
    mapping_lines = [f"  {k}: {v}" for k, v in ITEM_MAP.items()]
    mapping_text = "\n" + "\n".join(mapping_lines)

    desc = (
        "Human JSON の normative_modeling_part の指定項目を、\n"
        "同階層の codex JSON から欠損時のみ補完します。\n"
        "対象: data/*/DE_v10/json/*[Hh]uman*.json（各フォルダ内の最新の *codex*.json を参照）"
    )
    epilog = (
        "--item で補完対象の項目を指定します:" + mapping_text + "\n\n"
        "空とみなす条件: 未定義/null/空文字/空配列/空オブジェクト。\n"
        "既存の非空値は保持します。複数の codex がある場合は更新日時が最新のものを使用します。"
    )
    p = argparse.ArgumentParser(
        prog="fillJson_DENM_Part",
        description=desc,
        epilog=epilog,
        formatter_class=argparse.ArgumentDefaultsHelpFormatter,
    )
    p.add_argument(
        "--item",
        type=int,
        choices=sorted(ITEM_MAP.keys()),
        required=True,
        help="補完対象項目の番号（必須）",
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

    sub_key = ITEM_MAP[args.item]

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
        changed, keys, codex_path = fill_one_field_from_codex(t, sub_key, dry_run=(not args.run))
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
