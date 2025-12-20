#!/usr/bin/env python3
"""
JSON 構造・キー検査ツール

このスクリプトは、<大文字で始まるディレクトリ>/<DE|QA>/json 配下の JSON を
テンプレートと照合して構造・キーの妥当性を確認します。

出力ファイルについて:
- 概要 TSV: 検査結果の一覧 (PASS/FAIL/ERROR, 件数, 備考, ファイルパス)。
  デフォルトの出力先は "<プロジェクトルート>/notes" で、
  ファイル名は `json_structure_check_<DE|QA>_<YYYYmmddHHMMSS>.tsv` です。

- 詳細テキスト群: FAIL や ERROR の詳細を JSON ごとの .txt に出力します。
  概要 TSV と同じタイムスタンプを持つディレクトリ
  `json_structure_check_<DE|QA>_<YYYYmmddHHMMSS>_details/` に保存され、
  各 JSON ファイル名に拡張子 .txt を付けたファイルとして出力されます。

オプション `--output-folder` で出力先のディレクトリを変更できます。
"""
import argparse
import json
import re
import sys
from dataclasses import dataclass
from pathlib import Path
from typing import Any, List, Tuple, Union


JsonType = Union[dict, list, str, int, float, bool, None]

# Default template file paths (relative to ../templates/)
DEFAULT_DE_TEMPLATE = "DE_Author20XX_by_Someone_YYYYmmddHHMMSS_for_v10_1.json"
DEFAULT_QA_TEMPLATE = "QA_Author20XX_by_Someone_YYYYmmddHHMMSS_for_v7.json"


@dataclass
class Mismatch:
    path: str
    message: str

    def __str__(self) -> str:
        return f"{self.path}: {self.message}"


def is_uppercase_leading_dir(p: Path) -> bool:
    try:
        return p.is_dir() and p.name and p.name[0].isupper()
    except Exception:
        return False


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


def compare_structure(template: JsonType, data: JsonType, path: str = "$") -> List[Mismatch]:
    mismatches: List[Mismatch] = []

    # Type checks first
    if isinstance(template, dict):
        if not isinstance(data, dict):
            mismatches.append(Mismatch(path, f"expected object, got {json_type_name(data)}"))
            return mismatches

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
            mismatches.extend(
                compare_structure(template[k], data[k], path=f"{path}.{k}")
            )
        return mismatches

    if isinstance(template, list):
        if not isinstance(data, list):
            mismatches.append(Mismatch(path, f"expected array, got {json_type_name(data)}"))
            return mismatches

        # If template array has no example element, only check that data is an array
        if len(template) == 0:
            return mismatches

        # Use first element as schema example
        templ_elem = template[0]
        for idx, elem in enumerate(data):
            mismatches.extend(
                compare_structure(templ_elem, elem, path=f"{path}[{idx}]")
            )
        return mismatches

    # Primitive (string/number/boolean/null) type checks
    templ_type = json_type_name(template)
    data_type = json_type_name(data)
    if templ_type != data_type:
        # Allow numeric int/float interchangeability is already normalized to 'number'
        mismatches.append(Mismatch(path, f"expected {templ_type}, got {data_type}"))
    return mismatches


def discover_json_files(target_root: Path, data_type: str) -> List[Path]:
    results: List[Path] = []
    for child in sorted(target_root.iterdir()):
        if not is_uppercase_leading_dir(child):
            continue
        candidate = child / data_type / "json"
        if candidate.is_dir():
            results.extend(sorted(candidate.glob("*.json")))
    return results


def resolve_default_template(script_dir: Path, data_type: str) -> Path:
    # Default templates: ../../templates/<file> relative to the script directory
    # script_dir is tools/SubFuncs_for_03, so parent.parent goes to project root
    templates_dir = script_dir.parent.parent / "templates"

    # Check if templates directory exists
    if not templates_dir.exists():
        raise FileNotFoundError(
            f"\n{'='*60}\n"
            f"エラー: テンプレートディレクトリが見つかりません\n"
            f"{'='*60}\n"
            f"期待されるパス: {templates_dir}\n"
            f"スクリプト位置: {script_dir}\n"
            f"\n対処方法:\n"
            f"  1. テンプレートディレクトリが存在するか確認してください\n"
            f"  2. スクリプトが正しい場所に配置されているか確認してください\n"
            f"{'='*60}"
        )

    if data_type == "DE":
        default = templates_dir / DEFAULT_DE_TEMPLATE
        if default.exists():
            return default
        # Fallback to latest DE_*.json if exact not found
        candidates = sorted(templates_dir.glob("DE_*for_v*.json"))
        if candidates:
            print(f"警告: デフォルトテンプレート {DEFAULT_DE_TEMPLATE} が見つかりません", file=sys.stderr)
            print(f"代替として {candidates[-1].name} を使用します", file=sys.stderr)
            return candidates[-1]
    elif data_type == "QA":
        default = templates_dir / DEFAULT_QA_TEMPLATE
        if default.exists():
            return default
        candidates = sorted(templates_dir.glob("QA_*for_v*.json"))
        if candidates:
            print(f"警告: デフォルトテンプレート {DEFAULT_QA_TEMPLATE} が見つかりません", file=sys.stderr)
            print(f"代替として {candidates[-1].name} を使用します", file=sys.stderr)
            return candidates[-1]

    raise FileNotFoundError(
        f"\n{'='*60}\n"
        f"エラー: {data_type}用のテンプレートファイルが見つかりません\n"
        f"{'='*60}\n"
        f"検索ディレクトリ: {templates_dir}\n"
        f"期待されるファイル名パターン: {data_type}_*for_v*.json\n"
        f"\n対処方法:\n"
        f"  1. {templates_dir} に {data_type} テンプレートファイルが存在するか確認\n"
        f"  2. --template オプションでテンプレートファイルを明示的に指定\n"
        f"{'='*60}"
    )


def ensure_dir(p: Path) -> None:
    p.mkdir(parents=True, exist_ok=True)


def make_arg_parser(script_dir: Path) -> argparse.ArgumentParser:
    parser = argparse.ArgumentParser(
        description=(
            "Check JSON files under <UppercaseDir>/<DE|QA>/json against a template, "
            "verifying structure and keys."
        )
    )
    # Defaults relative to the script location, per request
    default_target = script_dir.parent
    # 出力先のデフォルトは <プロジェクトルート>/notes。
    # 以下の2種類の成果物が作成されます:
    #  - 概要 TSV:  json_structure_check_<DE|QA>_<YYYYmmddHHMMSS>.tsv
    #  - 詳細ディレクトリ: json_structure_check_<DE|QA>_<YYYYmmddHHMMSS>_details/
    default_output = script_dir.parent / "notes"

    parser.add_argument(
        "--target-folder",
        dest="target_folder",
        type=Path,
        default=default_target,
        help=f"Target root folder to scan (default: {default_target})",
    )
    # Also accept the misspelled alias to be safe
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
    parser.add_argument(
        "--template",
        dest="template",
        type=Path,
        default=None,
        help=(
            "Template JSON with expected structure/keys. "
            "Default: ../templates/DE_* or QA_* based on --data-type"
        ),
    )
    return parser


def write_summary_and_details(
    output_dir: Path,
    data_type: str,
    template_path: Path,
    results: List[Tuple[Path, List[Mismatch], Union[str, None]]],
) -> Tuple[Path, Path]:
    """
    results: list of (json_path, mismatches, error_string_if_any)

    生成される出力:
    - 概要 TSV (summary_csv_path):
        ファイル名は `json_structure_check_<DE|QA>_<YYYYmmddHHMMSS>.tsv`。
        1行に1 JSON ファイルの検査結果を記録 (result, issues, notes, file)。
        file 列はフルパスではなく「ファイル名のみ」を記載します。
    - 詳細ディレクトリ (details_dir_path):
        ディレクトリ名は `json_structure_check_<DE|QA>_<YYYYmmddHHMMSS>_details/`。
        FAIL/ERROR の場合に、対象 JSON ごとの詳細を <元ファイル名>.txt として出力。

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
        for json_path, mismatches, error in results:
            is_backup = json_path.name.endswith("_backup.json")

            # 常に詳細ファイルは作成（デバッグや診断のため）
            if error is not None:
                details_path = details_dir / (json_path.name + ".txt")
                with details_path.open("w", encoding="utf-8") as df:
                    df.write(f"ERROR parsing/reading: {error}\n")
                # 概要TSVには ERROR を記載しない（要求: FAIL のみを列挙）
                continue

            if mismatches:
                # 詳細ファイルは作成
                details_path = details_dir / (json_path.name + ".txt")
                with details_path.open("w", encoding="utf-8") as df:
                    df.write(f"Template: {template_path}\n")
                    df.write(f"Target:   {json_path}\n")
                    df.write("\nMismatches:\n")
                    for m in mismatches:
                        df.write(f"- {m}\n")

                # 概要TSVは FAIL かつ バックアップ以外のみを記載
                if not is_backup:
                    f.write(f"FAIL\t{len(mismatches)}\tSee details\t{json_path.name}\n")
            else:
                # PASS は概要TSVに記載しない（要求: FAIL のみを列挙）
                pass

    return summary_csv, details_dir


def main(argv: List[str]) -> int:
    script_dir = Path(__file__).resolve().parent
    parser = make_arg_parser(script_dir)
    args = parser.parse_args(argv)

    target_root: Path = args.target_folder.resolve()
    output_dir: Path = args.output_folder.resolve()
    data_type: str = args.data_type

    if args.template is None:
        try:
            template_path = resolve_default_template(script_dir, data_type)
        except FileNotFoundError as e:
            print(str(e), file=sys.stderr)
            return 2
    else:
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
            f"  2. --template オプションを省略してデフォルトを使用してください\n"
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
            f"No JSON files found under <UppercaseDir>/{data_type}/json in {target_root}",
            file=sys.stderr,
        )
        # Still exit 0, as there is nothing to check
        return 0

    results: List[Tuple[Path, List[Mismatch], Union[str, None]]] = []
    for jp in json_files:
        try:
            data = load_json(jp)
        except Exception as e:
            results.append((jp, [], f"JSON load error: {e}"))
            continue
        mismatches = compare_structure(template_json, data, path="$")
        results.append((jp, mismatches, None))

    summary_csv, details_dir = write_summary_and_details(output_dir, data_type, template_path, results)

    # Count results by status
    pass_count = sum(1 for _, m, e in results if e is None and not m)
    fail_count = sum(1 for _, m, e in results if e is None and m)
    error_count = sum(1 for _, m, e in results if e is not None)

    print(f"=== JSON Structure Check Complete ===")
    print(f"Checked files: {len(results)}")
    print(f"  PASS:  {pass_count}")
    print(f"  FAIL:  {fail_count}")
    print(f"  ERROR: {error_count}")
    print(f"")
    print(f"Output files:")
    print(f"  Summary TSV:   {summary_csv}")
    print(f"  Details folder: {details_dir}")
    return 0


if __name__ == "__main__":
    sys.exit(main(sys.argv[1:]))
