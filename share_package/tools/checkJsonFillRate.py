#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
指定したルート配下にある AuthorYear 形式（例: Smith2024, Han2024A など）の
子フォルダごとに、配下の <DE|QA>/json にある各 JSON ファイルの
「書き込み率（フィル率）」を集計し、ファイル単位で結果を出力します。

オプションとデフォルトの挙動は tools/checkJsonStructureAndKey.py に合わせています。
- --target-folder: 走査の起点（既定: このスクリプトの親ディレクトリ）
- --output-folder: 出力先（既定: このスクリプトの親ディレクトリ/notes）
- --data-type: DE または QA（必須）

定義（デフォルト）
- 再帰的に JSON を走査し，葉（プリミティブ）ノードの総数と，そのうち「記入済み」と
  判定できる個数を数えます。
- 記入済みの判定:
  - None: 記入扱い（既存仕様を踏襲）
  - 文字列: 空白を除去して空でなければ記入済み
  - 数値/真偽値: 値があれば記入済みとみなす（0 や False も記入済み）
  - 配列/オブジェクト: 自身は葉と数えず，中身を再帰的に評価（空配列/空オブジェクトは 0/0）

出力
- --output-folder 配下に TSV を出力（レガシー名を維持）
  - DE の場合: DE_JsonFillRate.tsv、QA の場合: QA_JsonFillRate.tsv
  - 列: child, source, file, filled, total, rate（昇順ソート）
"""

from __future__ import annotations

import argparse
import json
import sys
from dataclasses import dataclass
from pathlib import Path
import re
from typing import Iterable, List, Tuple

# 近傍ツールと同様のユーティリティを用意
def is_uppercase_leading_dir(p: Path) -> bool:
    try:
        return p.is_dir() and p.name and p.name[0].isupper()
    except Exception:
        return False


@dataclass
class FileStat:
    path: Path
    filled: int
    total: int

    @property
    def rate(self) -> float | None:
        return (self.filled / self.total) if self.total > 0 else None


def count_filled_and_total(node) -> Tuple[int, int]:
    """JSONノードの記入済み葉と総葉を数える。

    - dict/list は自身を葉とせず，中身を合算
    - プリミティブは 1 葉として扱い，filled 判定を行う
    """
    # オブジェクト（辞書）
    if isinstance(node, dict):
        filled, total = 0, 0
        for v in node.values():
            f, t = count_filled_and_total(v)
            filled += f
            total += t
        return filled, total

    # 配列（リスト）
    if isinstance(node, list):
        filled, total = 0, 0
        for v in node:
            f, t = count_filled_and_total(v)
            filled += f
            total += t
        return filled, total

    # プリミティブ
    total = 1
    if node is None:
        # ユーザー要望: None も記入扱い
        return 1, total
    if isinstance(node, str):
        return (1 if node.strip() != "" else 0), total
    # 数値・真偽値・その他は値があれば記入済み扱い
    return 1, total


def collect_json_files(root: Path) -> List[Path]:
    return sorted(p for p in root.rglob("*.json") if p.is_file())


def analyze_dir(json_dir: Path) -> Tuple[List[FileStat], int, int]:
    files = collect_json_files(json_dir)
    stats: List[FileStat] = []
    total_filled = 0
    total_total = 0
    for fp in files:
        try:
            data = json.loads(fp.read_text(encoding="utf-8"))
        except Exception as e:
            print(f"[WARN] JSON 読み込み失敗: {fp} ({e})", file=sys.stderr)
            continue
        filled, tot = count_filled_and_total(data)
        stats.append(FileStat(fp, filled, tot))
        total_filled += filled
        total_total += tot
    return stats, total_filled, total_total


def format_rate(filled: int, total: int) -> str:
    if total == 0:
        return "N/A"
    return f"{filled}/{total} ({(filled/total)*100:.2f}%)"


def rate_fields(filled: int, total: int) -> Tuple[str, str]:
    """TSV 用のレート列（rate, rate_percent）を返す。total=0 の場合は空欄。"""
    if total == 0:
        return "", ""
    r = filled / total
    return f"{r:.6f}", f"{r*100:.2f}"


def make_arg_parser(script_dir: Path) -> argparse.ArgumentParser:
    parser = argparse.ArgumentParser(
        description=(
            "Check JSON fill rate under <UppercaseDir>/<DE|QA>/json and export TSV."
        )
    )
    default_target = script_dir.parent
    default_output = script_dir.parent / "notes"

    parser.add_argument(
        "--target-folder",
        dest="target_folder",
        type=Path,
        default=default_target,
        help=f"Target root folder to scan (default: {default_target})",
    )
    # Keep misspelled alias for compatibility, but hide it
    parser.add_argument(
        "--ouput-folder",
        dest="output_folder",
        type=Path,
        default=default_output,
        help=argparse.SUPPRESS,
    )
    parser.add_argument(
        "--output-folder",
        dest="output_folder",
        type=Path,
        default=default_output,
        help=f"Folder to write results (default: {default_output})",
    )
    parser.add_argument(
        "--data-type",
        dest="data_type",
        choices=["DE", "QA"],
        required=True,
        help="Choose which data type (DE or QA)",
    )
    return parser


def discover_json_dirs(target_root: Path, data_type: str) -> List[Tuple[str, Path]]:
    """Return list of (child_name, json_dir) for given data_type under target_root."""
    entries: List[Tuple[str, Path]] = []
    for child in sorted(target_root.iterdir()):
        if not is_uppercase_leading_dir(child):
            continue
        json_dir = child / data_type / "json"
        if json_dir.is_dir():
            entries.append((child.name, json_dir))
    return entries


def main(argv: Iterable[str] | None = None) -> int:
    script_dir = Path(__file__).resolve().parent
    parser = make_arg_parser(script_dir)
    args = parser.parse_args(list(argv) if argv is not None else None)

    target_root: Path = args.target_folder.resolve()
    output_dir: Path = args.output_folder.resolve()
    data_type: str = args.data_type

    if not target_root.exists() or not target_root.is_dir():
        print(
            f"Target folder does not exist or is not a directory: {target_root}",
            file=sys.stderr,
        )
        return 2

    targets = discover_json_dirs(target_root, data_type)
    if not targets:
        print(
            f"No JSON files found under <UppercaseDir>/{data_type}/json in {target_root}",
            file=sys.stderr,
        )
        return 0

    # Collect per-file entries
    entries: List[Tuple[str, str, Path, int, int, str, float]] = []
    for child_name, json_dir in targets:
        stats, _filled_sum, _total_sum = analyze_dir(json_dir)
        for st in stats:
            r, _rp = rate_fields(st.filled, st.total)
            rnum = (st.filled / st.total) if st.total > 0 else -1.0
            rel = st.path
            entries.append((child_name, data_type, rel, st.filled, st.total, r, rnum))

    if not entries:
        print(
            f"No JSON files found under <UppercaseDir>/{data_type}/json in {target_root}",
            file=sys.stderr,
        )
        return 0

    # Sort by rate ascending
    entries.sort(key=lambda x: x[6])

    # Prepare output (keep legacy filenames to avoid breaking scripts)
    output_dir.mkdir(parents=True, exist_ok=True)
    if data_type == "DE":
        out_path = output_dir / "DE_JsonFillRate.tsv"
    else:
        out_path = output_dir / "QA_JsonFillRate.tsv"

    rows: List[str] = ["child\tsource\tfile\tfilled\ttotal\trate"]
    for child_name, source_tag, rel, filled, total, r, _ in entries:
        rows.append(f"{child_name}\t{source_tag}\t{rel}\t{filled}\t{total}\t{r}")

    out_path.write_text("\n".join(rows) + "\n", encoding="utf-8")
    print(f"Checked {len(entries)} files. Summary: {out_path}")

    return 0


if __name__ == "__main__":
    raise SystemExit(main())
