#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
Fill one reference_cohort_and_imaging_part field in Human JSONs from sibling codex JSONs.

Targets:
  - data/*/DE/json/*Human*.json
  - data/*/DE/json/*human*.json

Rules:
  - For each target file, if the specified field is empty or missing,
    fill from a sibling JSON whose filename matches *codex*.json (case-insensitive).
  - Preserve any non-empty existing values in the target file.
  - If multiple codex files exist, use the most recently modified one.

Select field with required option --item:
  1: rci1_dataset_name
  2: rci2_hc_n
  3: rci3_hc_age
  4: rci4_hc_sex
  5: rci5_imaging_modality
  6: rci6_analysis_level
  7: rci7_preprocessing_pipeline
  8: rci8_quality_checking
  9: rci9_site_effect_handling

Usage:
  From repo root:
    python3 tools/fillJson_DERCI_Part.py --item 1 --dry-run
    python3 tools/fillJson_DERCI_Part.py --item 6
"""

from __future__ import annotations

import json
import sys
from pathlib import Path
from typing import Any, Dict, List, Tuple
import argparse


KEY_ROOT = "reference_cohort_and_imaging_part"

# Map numeric selector to sub-key name
ITEM_MAP: Dict[int, str] = {
    1: "rci1_dataset_name",
    2: "rci2_hc_n",
    3: "rci3_hc_age",
    4: "rci4_hc_sex",
    5: "rci5_imaging_modality",
    6: "rci6_analysis_level",
    7: "rci7_preprocessing_pipeline",
    8: "rci8_quality_checking",
    9: "rci9_site_effect_handling",
}


def is_empty(value: Any) -> bool:
    """Judge emptiness for strings, lists, and dicts with nested 'answer' fields.

    Rules:
      - None or empty string -> empty
      - list -> empty if len==0 or all items are empty
      - dict ->
          * if has 'answer' key: follow emptiness of its value
          * else: empty if all values are empty
    """
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


def summarize_value(value: Any, maxlen: int = 120) -> str:
    try:
        if value is None:
            return "<None>"
        if isinstance(value, str):
            s = value.strip().replace("\n", " ")
            return (s[: maxlen - 3] + "...") if len(s) > maxlen else s
        if isinstance(value, dict):
            if "answer" in value and isinstance(value["answer"], str):
                return f"dict(answer='{summarize_value(value['answer'], maxlen)}')"
            keys = ",".join(list(value.keys())[:6])
            if len(value.keys()) > 6:
                keys += ",..."
            return f"dict(keys=[{keys}])"
        if isinstance(value, list):
            return f"list(len={len(value)})"
        s = str(value)
        return (s[: maxlen - 3] + "...") if len(s) > maxlen else s
    except Exception:
        return "<unrepr>"


def fill_one_field_from_codex(
    target_path: Path, sub_key: str, *, dry_run: bool = False
) -> Tuple[bool, List[str], Path | None, Dict[str, Any]]:
    changed = False
    updated_keys: List[str] = []
    info: Dict[str, Any] = {}

    try:
        target = read_json(target_path)
    except Exception as e:
        info = {"status": "target_parse_error", "error": str(e)}
        print(f"[SKIP] Failed to parse JSON: {target_path} ({e})", file=sys.stderr)
        return False, [], None, info

    codex_path = find_codex_candidate(target_path.parent, exclude=target_path)
    if codex_path is None:
        tgt_top = target.get(KEY_ROOT)
        tgt_val = tgt_top.get(sub_key) if isinstance(tgt_top, dict) else None
        info = {
            "status": "no_codex",
            "target_empty": is_empty(tgt_val),
            "target_before": tgt_val,
        }
        print(f"[WARN] No codex JSON found for: {target_path}")
        return False, [], None, info

    try:
        codex = read_json(codex_path)
    except Exception as e:
        info = {"status": "codex_parse_error", "error": str(e)}
        print(f"[SKIP] Failed to parse codex JSON: {codex_path} ({e})", file=sys.stderr)
        return False, [], codex_path, info

    tgt_top = target.get(KEY_ROOT)
    tgt_val = tgt_top.get(sub_key) if isinstance(tgt_top, dict) else None

    src_top = codex.get(KEY_ROOT)
    src_val = src_top.get(sub_key) if isinstance(src_top, dict) else None

    if is_empty(tgt_val):
        if not is_empty(src_val):
            if not isinstance(target.get(KEY_ROOT), dict):
                target[KEY_ROOT] = {}
            target[KEY_ROOT][sub_key] = src_val
            changed = True
            updated_keys.append(f"{KEY_ROOT}/{sub_key}")
            info = {
                "status": "updated",
                "target_empty": True,
                "target_before": tgt_val,
                "source_empty": False,
                "source_value": src_val,
            }
        else:
            info = {
                "status": "skip_source_empty",
                "target_empty": True,
                "target_before": tgt_val,
                "source_empty": True,
                "source_value": src_val,
            }
    else:
        info = {
            "status": "skip_target_nonempty",
            "target_empty": False,
            "target_before": tgt_val,
            "source_empty": is_empty(src_val),
            "source_value": src_val,
        }

    if changed and not dry_run:
        try:
            write_json(target_path, target)
        except Exception as e:
            info["status"] = "write_error"
            info["error"] = str(e)
            print(f"[ERROR] Failed to write updated JSON: {target_path} ({e})", file=sys.stderr)
            return False, [], codex_path, info

    return changed, updated_keys, codex_path, info


def build_arg_parser() -> argparse.ArgumentParser:
    mapping_lines = [f"  {k}: {v}" for k, v in ITEM_MAP.items()]
    mapping_text = "\n" + "\n".join(mapping_lines)

    desc = (
        "Human JSON の reference_cohort_and_imaging_part の指定項目を、\n"
        "同階層の codex JSON から欠損時のみ補完します。\n"
        "対象: data/*/DE/json/*[Hh]uman*.json（各フォルダ内の最新の *codex*.json を参照）"
    )
    epilog = (
        "--item で補完対象の項目を指定します:" + mapping_text + "\n\n"
        "空とみなす条件: 未定義/null/空文字/空配列/空オブジェクト。\n"
        "既存の非空値は保持します。複数の codex がある場合は更新日時が最新のものを使用します。"
    )
    p = argparse.ArgumentParser(
        prog="fillJson_DERCI_Part",
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
        "data/*/DE/json/*Human*.json",
        "data/*/DE/json/*human*.json",
    ]

    targets: List[Path] = []
    for pat in patterns:
        targets.extend(sorted(root.glob(pat)))

    if not targets:
        print("[INFO] No target Human JSON files found under data/*/DE/json/.")
        return 0

    total = len(targets)
    changed_count = 0
    for idx, t in enumerate(targets, start=1):
        try:
            rel = t.relative_to(root)
        except Exception:
            rel = t
        changed, keys, codex_path, info = fill_one_field_from_codex(
            t, sub_key, dry_run=(not args.run)
        )
        codex_rel = None
        if codex_path is not None:
            try:
                codex_rel = codex_path.relative_to(root)
            except Exception:
                codex_rel = codex_path

        # Compose a detailed, single-line status with previews
        target_prev = summarize_value(info.get("target_before")) if "target_before" in info else "-"
        source_prev = summarize_value(info.get("source_value")) if "source_value" in info else "-"
        action = info.get("status", "unknown")
        write_flag = "yes" if (args.run and changed) else "no"
        print(
            f"[{idx}/{total}] file={rel} item={sub_key} codex={codex_rel} "
            f"target_empty={info.get('target_empty')} source_empty={info.get('source_empty')} "
            f"action={action} wrote={write_flag} "
            f"target_prev={target_prev} source={source_prev}"
        )

        if changed:
            changed_count += 1

    if not args.run:
        print(f"Dry-run complete. Would update {changed_count} / {total} files.")
    else:
        print(f"Done. Updated {changed_count} / {total} files.")
    return 0


if __name__ == "__main__":
    sys.exit(main(sys.argv[1:]))
