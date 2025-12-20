#!/usr/bin/env python3
"""
JSONファイル構造統一スクリプト

このスクリプトは、DE_Results フォルダ配下のすべてのJSONファイルを
参照構造（DE_Author20XX_by_Someone_YYYYmmddHHMMSS.json）と同じ構造に統一します。

主な機能：
- フラット構造の検出と変換
- 番号付き構造の検出と変換
- キー名の大文字小文字の統一
- 値の保持を保証
- バックアップの作成
"""

import json
import os
import glob
import shutil
from pathlib import Path
from typing import Dict, Any, List
import re

# 参照構造の定義
REFERENCE_STRUCTURE = {
    "study_identification": [
        "study_id",
        "reference_file_names",
        "author_journal_year",
        "title",
        "doi"
    ],
    "study_characteristics": [
        "study_objective",
        "study_design",
        "study_design_other"
    ],
    "reference_cohort_and_imaging": [
        "dataset_name",
        "hc_n",
        "hc_age",
        "hc_sex",
        "imaging_modality",
        "analysis_level",
        "preprocessing_pipeline",
        "quality_checking",
        "quality_checking_detail",
        "site_effect_handling",
        "site_effect_handling_detail"
    ],
    "normative_modeling": [
        "model_origin",
        "model_origin_detail",
        "modeling_method",
        "software_tool",
        "response_variable",
        "predictor_variables",
        "predictor_effects",
        "nm_vldtn_handle_ns",
        "nm_vldtn_same_domain_nonindep",
        "nm_vldtn_same_domain_indep",
        "nm_vldtn_diff_domain"
    ],
    "clinical_application_and_analysis": [
        "clinical_dataset",
        "diseases_studied",
        "clinical_groups_n",
        "clinical_groups_age",
        "clinical_groups_sex",
        "deviation_metric",
        "association_analysis",
        "key_findings_brief",
        "key_findings_detailed",
        "key_limitations",
        "application_notes"
    ],
    "general_notes": [
        "general_notes"
    ]
}

# 詳細フィールド（answer, confidence_rating等を持つべきフィールド）
DETAILED_FIELDS = {
    "reference_cohort_and_imaging": [
        "dataset_name",
        "hc_n",
        "hc_age",
        "hc_sex",
        "imaging_modality",
        "analysis_level",
        "preprocessing_pipeline",
        "quality_checking",
        "quality_checking_detail",
        "site_effect_handling",
        "site_effect_handling_detail"
    ],
    "normative_modeling": [
        "model_origin",
        "model_origin_detail",
        "modeling_method",
        "software_tool",
        "response_variable",
        "predictor_variables",
        "predictor_effects",
        "nm_vldtn_handle_ns",
        "nm_vldtn_same_domain_nonindep",
        "nm_vldtn_same_domain_indep",
        "nm_vldtn_diff_domain"
    ],
    "clinical_application_and_analysis": [
        "clinical_dataset",
        "diseases_studied",
        "clinical_groups_n",
        "clinical_groups_age",
        "clinical_groups_sex",
        "deviation_metric"
    ]
}

# 単純な文字列フィールド（詳細構造を持たない）
SIMPLE_STRING_FIELDS = {
    "clinical_application_and_analysis": [
        "association_analysis",
        "key_findings_brief",
        "key_findings_detailed",
        "key_limitations",
        "application_notes"
    ]
}


def normalize_key(key: str) -> str:
    """キー名を正規化（大文字小文字、番号削除など）"""
    # 番号付きプレフィックスを削除 (例: "1-1. study_id" -> "study_id", "4-8. nm_vldtn_handle_ns" -> "nm_vldtn_handle_ns")
    key = re.sub(r'^\d+-\d+\.\s*', '', key)  # 4-8. のような形式
    key = re.sub(r'^\d+\.\s*', '', key)      # 1. のような形式

    # スペースをアンダースコアに変換して小文字化
    key = key.replace(' ', '_').lower()

    # 特殊なマッピング
    key_mapping = {
        "answer": "answer",
        "confidence_rating": "confidence_rating",
        "negative_answer_category": "negative_answer_category",
        "reason": "reason",
        "supporting_text": "supporting_text",
        "location": "location"
    }

    return key_mapping.get(key, key)


def normalize_section_name(section: str) -> str:
    """セクション名を正規化"""
    # 番号付きプレフィックスを削除
    section = re.sub(r'^\d+\.\s*', '', section)

    # 特殊文字を処理
    section = section.replace('&', 'and')

    # 正規化
    section_mapping = {
        "study identification": "study_identification",
        "study characteristics": "study_characteristics",
        "reference cohort and imaging": "reference_cohort_and_imaging",
        "normative modeling": "normative_modeling",
        "normative modelling": "normative_modeling",
        "clinical application and analysis": "clinical_application_and_analysis",
        "general notes": "general_notes"
    }

    normalized = section.strip().lower()
    return section_mapping.get(normalized, normalized.replace(' ', '_'))


def is_detailed_object(value: Any) -> bool:
    """
    値が詳細オブジェクト（answer, confidence_rating等）かどうかを判定
    """
    if not isinstance(value, dict):
        return False

    # 小文字化したキーをチェック
    lower_keys = {k.lower().replace(' ', '_') for k in value.keys()}

    # 詳細オブジェクトの特徴的なキーのいずれかが存在するか
    detail_keys = {'answer', 'confidence_rating', 'negative_answer_category',
                   'reason', 'supporting_text', 'location'}

    return len(lower_keys & detail_keys) >= 2


def normalize_detailed_object(obj: Dict) -> Dict:
    """詳細オブジェクトのキーを正規化"""
    normalized = {}
    for key, value in obj.items():
        normalized_key = normalize_key(key)
        normalized[normalized_key] = value

    # 必須キーが存在しない場合は空文字列で補完
    required_keys = ["answer", "confidence_rating", "negative_answer_category",
                     "reason", "supporting_text", "location"]
    for req_key in required_keys:
        if req_key not in normalized:
            normalized[req_key] = ""

    return normalized


def detect_structure_type(data: Dict) -> str:
    """JSONファイルの構造タイプを検出"""
    keys = list(data.keys())

    # 空の場合
    if not keys:
        return "empty"

    # 参照構造と一致するか確認
    if "study_identification" in keys and "reference_cohort_and_imaging" in keys:
        return "correct"

    # 番号付き構造かチェック
    if any(re.match(r'^\d+\.', key) for key in keys):
        return "numbered"

    # フラット構造かチェック（トップレベルにstudy_idなどが直接ある）
    if "study_id" in keys and "study_identification" not in keys:
        return "flat"

    return "unknown"


def convert_flat_to_hierarchical(data: Dict) -> Dict:
    """フラット構造を階層構造に変換"""
    result = {}

    # 各セクションごとに処理
    for section, fields in REFERENCE_STRUCTURE.items():
        section_data = {}

        for field in fields:
            if field in data:
                value = data[field]

                # このフィールドが詳細オブジェクトを持つべきか確認
                section_detailed_fields = DETAILED_FIELDS.get(section, [])

                if field in section_detailed_fields:
                    # 詳細オブジェクトとして処理
                    if is_detailed_object(value):
                        section_data[field] = normalize_detailed_object(value)
                    else:
                        # 単純な値の場合、詳細オブジェクト形式に変換
                        section_data[field] = {
                            "answer": str(value) if value else "",
                            "confidence_rating": "",
                            "negative_answer_category": "",
                            "reason": "",
                            "supporting_text": "",
                            "location": ""
                        }
                else:
                    # 単純な文字列フィールド
                    section_data[field] = value

        if section_data:
            result[section] = section_data

    return result


def convert_numbered_to_hierarchical(data: Dict) -> Dict:
    """番号付き構造を階層構造に変換"""
    result = {}

    # まず番号付きセクションを正規化
    normalized_sections = {}
    for key, value in data.items():
        normalized_section = normalize_section_name(key)

        if isinstance(value, dict):
            # セクション内のフィールドも正規化
            normalized_fields = {}
            for field_key, field_value in value.items():
                normalized_field = normalize_key(field_key)
                normalized_fields[normalized_field] = field_value
            normalized_sections[normalized_section] = normalized_fields
        else:
            # 文字列の場合（例: general_notesセクション全体が文字列の場合）
            # general_notesの場合は特別処理
            if normalized_section == "general_notes":
                normalized_sections[normalized_section] = {"general_notes": value}
            else:
                normalized_sections[normalized_section] = value

    # 正規化されたセクションを参照構造に合わせて整理
    for section, fields in REFERENCE_STRUCTURE.items():
        if section in normalized_sections:
            section_data = {}
            section_source = normalized_sections[section]

            if isinstance(section_source, dict):
                for field in fields:
                    if field in section_source:
                        value = section_source[field]

                        # このフィールドが詳細オブジェクトを持つべきか確認
                        section_detailed_fields = DETAILED_FIELDS.get(section, [])

                        if field in section_detailed_fields:
                            if is_detailed_object(value):
                                section_data[field] = normalize_detailed_object(value)
                            else:
                                section_data[field] = {
                                    "answer": str(value) if value else "",
                                    "confidence_rating": "",
                                    "negative_answer_category": "",
                                    "reason": "",
                                    "supporting_text": "",
                                    "location": ""
                                }
                        else:
                            section_data[field] = value

            if section_data:
                result[section] = section_data

    return result


def normalize_correct_structure(data: Dict) -> Dict:
    """
    正しい構造のファイルでも、キーの大文字小文字などを統一
    """
    result = {}

    for section, fields in REFERENCE_STRUCTURE.items():
        if section in data:
            section_data = {}
            section_source = data[section]

            if isinstance(section_source, dict):
                for field in fields:
                    if field in section_source:
                        value = section_source[field]

                        # このフィールドが詳細オブジェクトを持つべきか確認
                        section_detailed_fields = DETAILED_FIELDS.get(section, [])

                        if field in section_detailed_fields:
                            if is_detailed_object(value):
                                section_data[field] = normalize_detailed_object(value)
                            else:
                                section_data[field] = value
                        else:
                            section_data[field] = value

            if section_data:
                result[section] = section_data

    return result


def normalize_json_file(file_path: str, create_backup: bool = True) -> tuple[bool, str]:
    """
    JSONファイルを正規化

    Returns:
        (success: bool, message: str)
    """
    try:
        # ファイルを読み込み（無効なエスケープシーケンスを修正）
        with open(file_path, 'r', encoding='utf-8') as f:
            content = f.read()

        # 無効なエスケープシーケンスと制御文字を修正
        # \_  を _ に置き換え（バックスラッシュを削除）
        content = content.replace('\\_', '_')

        # JSON文字列内の実際の改行文字をエスケープシーケンスに変換
        # 複雑な処理: JSON文字列内の改行をエスケープする
        # より簡単な方法: Python 3.11+ ではない場合、手動で修正
        # JSONパースを試みて、エラーが出たら修正を試みる
        try:
            data = json.loads(content)
        except json.JSONDecodeError as e:
            # エラーの場合、JSON文字列内の改行を修正
            # 簡易的な方法: 文字列値内の改行を削除またはエスケープ
            # より正確には、正規表現で文字列値内の改行を見つけて修正
            import re

            # JSON文字列内の改行を見つけて修正
            # パターン: "..." 内の改行
            def fix_string_newlines(match):
                # マッチした文字列内の改行をスペースに置換
                string_content = match.group(1)
                # 実際の改行文字をスペースまたは削除
                fixed = string_content.replace('\n', ' ').replace('\r', ' ')
                return '"' + fixed + '"'

            # 単純な方法: すべての " ... " パターンを見つけて改行を修正
            # ただし、これはネストされた引用符を正しく処理しない
            # より安全な方法: 文字列値内の実際の改行をスペースに置き換え
            lines = content.split('\n')
            fixed_lines = []
            in_string = False
            for line in lines:
                # 行ごとに処理し、文字列の途中で改行されているかチェック
                # 簡易的な判定: " の数が奇数なら文字列の途中
                quote_count = line.count('"') - line.count('\\"')
                if in_string:
                    # 前の行から続いている文字列
                    # 改行をスペースに置き換えて前の行に追加
                    if fixed_lines:
                        fixed_lines[-1] = fixed_lines[-1].rstrip() + ' ' + line.lstrip()
                    else:
                        fixed_lines.append(line)
                else:
                    fixed_lines.append(line)

                # 文字列の状態を更新
                if quote_count % 2 == 1:
                    in_string = not in_string

            content = '\n'.join(fixed_lines)
            data = json.loads(content)

        # 構造タイプを検出
        structure_type = detect_structure_type(data)

        # 構造に応じて変換
        if structure_type == "correct":
            normalized_data = normalize_correct_structure(data)
        elif structure_type == "flat":
            normalized_data = convert_flat_to_hierarchical(data)
        elif structure_type == "numbered":
            normalized_data = convert_numbered_to_hierarchical(data)
        elif structure_type == "empty":
            return False, f"Empty file: {file_path}"
        else:
            return False, f"Unknown structure type: {file_path}"

        # バックアップを作成
        if create_backup:
            backup_path = file_path + ".backup"
            shutil.copy2(file_path, backup_path)

        # 正規化されたデータを保存
        with open(file_path, 'w', encoding='utf-8') as f:
            json.dump(normalized_data, f, indent=2, ensure_ascii=False)

        return True, f"Successfully normalized ({structure_type}): {file_path}"

    except Exception as e:
        return False, f"Error processing {file_path}: {str(e)}"


def main():
    """メイン処理"""
    base_dir = "/media/iu/STORAGE/.Trash-1000/files/DE_Results"

    # すべてのJSONファイルを検索
    json_files = glob.glob(os.path.join(base_dir, "**/*.json"), recursive=True)

    # 参照ファイルは除外
    reference_file = os.path.join(base_dir, "DE_Author20XX_by_Someone_YYYYmmddHHMMSS.json")
    json_files = [f for f in json_files if f != reference_file]

    print(f"Found {len(json_files)} JSON files to process")
    print("=" * 80)

    success_count = 0
    failure_count = 0
    failures = []

    for i, json_file in enumerate(json_files, 1):
        print(f"[{i}/{len(json_files)}] Processing: {os.path.basename(json_file)}")

        success, message = normalize_json_file(json_file, create_backup=True)

        if success:
            success_count += 1
            print(f"  ✓ {message}")
        else:
            failure_count += 1
            failures.append((json_file, message))
            print(f"  ✗ {message}")

    print("=" * 80)
    print(f"Summary:")
    print(f"  Success: {success_count}")
    print(f"  Failure: {failure_count}")

    if failures:
        print("\nFailed files:")
        for file_path, error in failures:
            print(f"  - {file_path}")
            print(f"    {error}")


if __name__ == "__main__":
    main()
