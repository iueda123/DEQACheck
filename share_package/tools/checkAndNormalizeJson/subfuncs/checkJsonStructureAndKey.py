#!/usr/bin/env python3
# Usage:
#   python3 subfuncs/checkJsonStructureAndKey.py --data-type DE --target-folder PATH --template PATH [--result-folder PATH]
#   python3 subfuncs/checkJsonStructureAndKey.py --data-type QA --target-folder PATH --template PATH [--result-folder PATH]
#
# What it does:
# - Scans <data_type>/json/<data_type>*.json under the target folder,
#   where <data_type> can be nested at any depth under the target folder.
# - Compares each JSON's structure/keys to the template specified by --template.
# - Writes a summary TSV and per-file detail text outputs under the output folder (default: <script_dir>/str_and_key_check_results).
"""
JSON 構造・キー検査ツール

このスクリプトは、target folder 配下の任意階層にある
<data_type>/json/<data_type>*.json をテンプレートと照合して
構造・キーの妥当性を確認します。
--data-type には DE_v11 や QA_v9 といった文字列を指定してください。

出力ファイルについて:
- 概要 TSV: 検査結果の一覧 (FAIL/WARN, 件数, 備考, ファイルパス)。
  デフォルトの出力先は "<スクリプトディレクトリ>/str_and_key_check_results" で、
  ファイル名は `json_structure_check_<data_type>_<YYYYmmddHHMMSS>.tsv` です。

- 詳細テキスト群: FAIL/WARN/ERROR の詳細を JSON ごとの .txt に出力します。
  概要 TSV と同じタイムスタンプを持つディレクトリ
  `json_structure_check_<data_type>_<YYYYmmddHHMMSS>_details/` に保存され、
  各 JSON ファイル名に拡張子 .txt を付けたファイルとして出力されます。

オプション `--result-folder` で出力先のディレクトリを変更できます。
"""
import argparse
import json
import sys
from dataclasses import dataclass
from pathlib import Path
from typing import Any, List, Tuple, Union


JsonType = Union[dict, list, str, int, float, bool, None]

@dataclass
class Mismatch:
    path: str
    message: str

    def __str__(self) -> str:
        return f"{self.path}: {self.message}"


@dataclass
class Warning:
    path: str
    message: str

    def __str__(self) -> str:
        return f"{self.path}: {self.message}"


def load_json(path: Path) -> JsonType:
    with path.open("r", encoding="utf-8") as f:
        return json.load(f)


def json_type_name(x: Any) -> str:
    if isinstance(x, bool):
        return "boolean"
    if x is None:
        return "null"
    if isinstance(x, (int, float)) and not isinstance(x, bool):
        return "number"
    if isinstance(x, str):
        return "string"
    if isinstance(x, list):
        return "array"
    if isinstance(x, dict):
        return "object"
    return type(x).__name__


def is_empty_container(x: JsonType) -> bool:
    return (isinstance(x, dict) and len(x) == 0) or (isinstance(x, list) and len(x) == 0)


def compare_structure(
    template: JsonType, data: JsonType, path: str = "$"
) -> Tuple[List[Mismatch], List[Warning]]:
    mismatches: List[Mismatch] = []
    warnings: List[Warning] = []

    # Type checks first
    if isinstance(template, dict):
        if not isinstance(data, dict):
            if is_empty_container(template):
                warnings.append(
                    Warning(path, f"expected object, got {json_type_name(data)} (allowed at leaf)")
                )
            else:
                mismatches.append(Mismatch(path, f"expected object, got {json_type_name(data)}"))
            return mismatches, warnings

        if is_empty_container(template):
            if len(data) > 0:
                warnings.append(
                    Warning(
                        path,
                        f"template object has no keys; allowing keys {sorted(data.keys())}",
                    )
                )
            return mismatches, warnings

        templ_keys = set(template.keys())
        data_keys = set(data.keys())

        missing = templ_keys - data_keys
        extra = data_keys - templ_keys
        if missing:
            mismatches.append(Mismatch(path, f"missing keys: {sorted(missing)}"))
        if extra:
            mismatches.append(Mismatch(path, f"unexpected keys: {sorted(extra)}"))

        # Recurse for common keys
        for k in sorted(templ_keys & data_keys):
            child_mismatches, child_warnings = compare_structure(
                template[k], data[k], path=f"{path}.{k}"
            )
            mismatches.extend(child_mismatches)
            warnings.extend(child_warnings)
        return mismatches, warnings

    if isinstance(template, list):
        if not isinstance(data, list):
            if is_empty_container(template):
                warnings.append(
                    Warning(path, f"expected array, got {json_type_name(data)} (allowed at leaf)")
                )
            else:
                mismatches.append(Mismatch(path, f"expected array, got {json_type_name(data)}"))
            return mismatches, warnings

        # If template array has no example element, only check that data is an array
        if len(template) == 0:
            if len(data) > 0:
                warnings.append(
                    Warning(path, "template array has no example; allowing any array elements")
                )
            return mismatches, warnings

        # Use first element as schema example
        templ_elem = template[0]
        for idx, elem in enumerate(data):
            child_mismatches, child_warnings = compare_structure(
                templ_elem, elem, path=f"{path}[{idx}]"
            )
            mismatches.extend(child_mismatches)
            warnings.extend(child_warnings)
        return mismatches, warnings

    # Primitive (string/number/boolean/null) type checks
    templ_type = json_type_name(template)
    data_type = json_type_name(data)
    if templ_type != data_type:
        warnings.append(Warning(path, f"expected {templ_type}, got {data_type} (allowed at leaf)"))
    return mismatches, warnings


def discover_json_files(target_root: Path, data_type: str) -> List[Path]:
    results: List[Path] = []
    # Search for <data_type>/json directories under target_root, not limited to direct children.
    for data_dir in sorted(target_root.rglob(data_type)):
        if not data_dir.is_dir():
            continue
        candidate = data_dir / "json"
        if candidate.is_dir():
            results.extend(sorted(candidate.glob(f"{data_type}*.json")))
    return results


def ensure_dir(p: Path) -> None:
    p.mkdir(parents=True, exist_ok=True)


def make_arg_parser(script_dir: Path) -> argparse.ArgumentParser:
    parser = argparse.ArgumentParser(
        description=(
            "Check JSON files under <data_type>/json (nested under the target) against a template, "
            "verifying structure and keys."
        )
    )
    # 出力先のデフォルトは <スクリプトディレクトリ>/str_and_key_check_results。
    # 以下の2種類の成果物が作成されます:
    #  - 概要 TSV:  json_structure_check_<data_type>_<YYYYmmddHHMMSS>.tsv
    #  - 詳細ディレクトリ: json_structure_check_<data_type>_<YYYYmmddHHMMSS>_details/
    default_output = script_dir / "str_and_key_check_results"

    parser.add_argument(
        "--target-folder",
        dest="target_folder",
        type=Path,
        required=True,
        help="Target root folder to scan (e.g., ./share_package/data)",
    )
    parser.add_argument(
        "--result-folder",
        dest="output_folder",
        type=Path,
        default=default_output,
        help=f"Folder to write results (default: {default_output})",
    )
    parser.add_argument(
        "--data-type",
        dest="data_type",
        # Accept any data_type string because target folders can be DE_v11, QA_v7, etc.
        type=str,
        required=True,
        help="Data type folder name to search for (e.g., DE_v11, QA_v9)",
    )
    parser.add_argument(
        "--template",
        dest="template",
        type=Path,
        required=True,
        help=(
            "Template JSON with expected structure/keys. "
            "Specify an explicit template file path"
        ),
    )
    return parser


def write_summary_and_details(
    output_dir: Path,
    data_type: str,
    template_path: Path,
    results: List[Tuple[Path, List[Mismatch], List[Warning], Union[str, None]]],
) -> Tuple[Path, Path]:
    """
    results: list of (json_path, mismatches, warnings, error_string_if_any)

    生成される出力:
    - 概要 TSV (summary_csv_path):
        ファイル名は `json_structure_check_<data_type>_<YYYYmmddHHMMSS>.tsv`。
        1行に1 JSON ファイルの検査結果を記録 (result, issues, notes, file)。
        file 列はフルパスではなく「ファイル名のみ」を記載します。
    - 詳細ディレクトリ (details_dir_path):
        ディレクトリ名は `json_structure_check_<data_type>_<YYYYmmddHHMMSS>_details/`。
        FAIL/WARN/ERROR の場合に、対象 JSON ごとの詳細を <元ファイル名>.txt として出力。

    戻り値は (summary_csv_path, details_dir_path) のタプル。
    """
    ensure_dir(output_dir)

    # Timestamped filenames for clarity
    from datetime import datetime

    ts = datetime.now().strftime("%Y%m%d%H%M%S")
    summary_csv = output_dir / f"json_structure_check_{data_type}_{ts}.tsv"
    details_dir = output_dir / f"json_structure_check_{data_type}_{ts}_details"
    ensure_dir(details_dir)

    def sanitize_cell(text: str) -> str:
        return text.replace("\t", " ").replace("\n", " ").replace("\r", " ")

    # Write summary TSV
    with summary_csv.open("w", encoding="utf-8") as f:
        f.write("result\tissues\tnotes\tfile\n")
        for json_path, mismatches, warnings, error in results:
            is_backup = json_path.name.endswith("_backup.json")

            # 常に詳細ファイルは作成（デバッグや診断のため）
            if error is not None:
                details_path = details_dir / (json_path.name + ".txt")
                with details_path.open("w", encoding="utf-8") as df:
                    df.write(f"ERROR parsing/reading: {error}\n")
                # 概要TSVには ERROR を記載しない（要求: FAIL のみを列挙）
                continue

            if mismatches or warnings:
                # 詳細ファイルは作成
                details_path = details_dir / (json_path.name + ".txt")
                with details_path.open("w", encoding="utf-8") as df:
                    df.write(f"Template: {template_path}\n")
                    df.write(f"Target:   {json_path}\n")
                    if mismatches:
                        df.write("\nMismatches:\n")
                        for m in mismatches:
                            df.write(f"- {m}\n")
                    if warnings:
                        df.write("\nWarnings:\n")
                        for w in warnings:
                            df.write(f"- {w}\n")

                # 概要TSVは FAIL/WARN かつ バックアップ以外のみを記載
                if mismatches and not is_backup:
                    f.write(
                        f"FAIL\t{len(mismatches)}\tSee details\t{json_path.name}\n"
                    )
                elif warnings and not is_backup:
                    f.write(
                        f"WARN\t{len(warnings)}\tSee details\t{json_path.name}\n"
                    )
            # PASS は概要TSVに記載しない

    return summary_csv, details_dir


def main(argv: List[str]) -> int:
    script_dir = Path(__file__).resolve().parent
    parser = make_arg_parser(script_dir)
    args = parser.parse_args(argv)

    target_root: Path = args.target_folder.resolve()
    output_dir: Path = args.output_folder.resolve()
    data_type: str = args.data_type

    template_path = args.template.resolve()

    if not target_root.exists() or not target_root.is_dir():
        print(
            f"\n{'='*60}\n"
            f"エラー: 対象フォルダが存在しないか、ディレクトリではありません\n"
            f"{'='*60}\n"
            f"指定されたパス: {target_root}\n"
            f"{'='*60}",
            file=sys.stderr
        )
        return 2

    if not template_path.exists():
        print(
            f"\n{'='*60}\n"
            f"エラー: テンプレートファイルが見つかりません\n"
            f"{'='*60}\n"
            f"指定されたパス: {template_path}\n"
            f"\n対処方法:\n"
            f"  1. ファイルパスが正しいか確認してください\n"
            f"{'='*60}",
            file=sys.stderr
        )
        return 2

    # Load template
    try:
        template_json = load_json(template_path)
    except Exception as e:
        print(f"Failed to load template {template_path}: {e}", file=sys.stderr)
        return 2

    # Discover json files
    json_files = discover_json_files(target_root, data_type)
    if not json_files:
        print(
            f"No JSON files found under any <{data_type}>/json in {target_root}",
            file=sys.stderr,
        )
        # Still exit 0, as there is nothing to check
        return 0

    results: List[Tuple[Path, List[Mismatch], List[Warning], Union[str, None]]] = []
    for jp in json_files:
        try:
            data = load_json(jp)
        except Exception as e:
            results.append((jp, [], [], f"JSON load error: {e}"))
            continue
        mismatches, warnings = compare_structure(template_json, data, path="$")
        results.append((jp, mismatches, warnings, None))

    summary_csv, details_dir = write_summary_and_details(
        output_dir, data_type, template_path, results
    )

    # Count results by status
    pass_count = sum(1 for _, m, w, e in results if e is None and not m and not w)
    warn_count = sum(1 for _, m, w, e in results if e is None and not m and w)
    fail_count = sum(1 for _, m, w, e in results if e is None and m)
    error_count = sum(1 for _, m, w, e in results if e is not None)

    print(f"=== JSON Structure Check Complete ===")
    print(f"Checked files: {len(results)}")
    print(f"  PASS:  {pass_count}")
    print(f"  WARN:  {warn_count}")
    print(f"  FAIL:  {fail_count}")
    print(f"  ERROR: {error_count}")
    print(f"")
    print(f"Output files:")
    print(f"  Summary TSV:   {summary_csv}")
    print(f"  Details folder: {details_dir}")
    return 0


if __name__ == "__main__":
    sys.exit(main(sys.argv[1:]))
