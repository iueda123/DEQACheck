#!/bin/bash

# ${1}: DE or QA. 入出力フォルダ名の制御のため
# ${2}: json file name

# エラーハンドリング
set -e

# 引数チェック
if [ $# -lt 2 ]; then
    echo "Usage: $0 <DE|QA> <json_filename>"
    exit 1
fi

FOLDER_TYPE="$1"
JSON_FILENAME="$2"

# パス設定
INPUT_JSON="./json/${FOLDER_TYPE}/${JSON_FILENAME}"
OUTPUT_TSV="./tsv/${FOLDER_TYPE}/${JSON_FILENAME%.json}.tsv"

# 入力ファイルの存在確認
if [ ! -f "$INPUT_JSON" ]; then
    echo "Error: Input file not found: $INPUT_JSON"
    exit 1
fi

# 出力ディレクトリの作成
mkdir -p "$(dirname "$OUTPUT_TSV")"

# JSONからTSVへの変換
echo "Converting: $INPUT_JSON -> $OUTPUT_TSV"

# Pythonスクリプトを使って変換
python3 - "$INPUT_JSON" "$OUTPUT_TSV" << 'PYTHON_SCRIPT'
import json
import sys
import csv
from datetime import datetime

def get_value(data, *keys, default="N/A"):
    """ネストされた辞書から値を取得"""
    result = data
    for key in keys:
        if isinstance(result, dict):
            result = result.get(key, default)
        else:
            return default
    return result if result is not None else default

def escape_tsv_value(value):
    """TSV用に値をエスケープ（改行とタブを処理）"""
    if value is None:
        return "N/A"
    value_str = str(value)
    # 改行を空白に、タブを空白に変換
    value_str = value_str.replace('\n', ' ').replace('\r', ' ').replace('\t', ' ')
    # 連続する空白を1つにまとめる
    value_str = ' '.join(value_str.split())
    return value_str

def convert_json_to_tsv(json_file, output_file):
    """JSONファイルをTSVに変換"""

    # JSONファイルを読み込む
    with open(json_file, 'r', encoding='utf-8') as f:
        data = json.load(f)

    rows = []

    # Study Identification
    si = get_value(data, "study_identification", default={})
    rows.append(["Section", "Subsection", "Field", "Value", "Confidence Rating", "Negative Answer Category", "Reason", "Supporting Text", "Page/Line"])
    rows.append(["Study Identification", "", "Study ID", escape_tsv_value(get_value(si, 'study_id')), "", "", "", "", ""])
    rows.append(["Study Identification", "", "Reference Files", escape_tsv_value(get_value(si, 'reference_file_names')), "", "", "", "", ""])
    rows.append(["Study Identification", "", "Author/Journal/Year", escape_tsv_value(get_value(si, 'author_journal_year')), "", "", "", "", ""])
    rows.append(["Study Identification", "", "Title", escape_tsv_value(get_value(si, 'title')), "", "", "", "", ""])
    rows.append(["Study Identification", "", "DOI", escape_tsv_value(get_value(si, 'doi')), "", "", "", "", ""])

    # Study Characteristics
    sc = get_value(data, "study_characteristics", default={})
    rows.append(["Study Characteristics", "", "Study Objective", escape_tsv_value(get_value(sc, 'study_objective')), "", "", "", "", ""])
    rows.append(["Study Characteristics", "", "Study Design", escape_tsv_value(get_value(sc, 'study_design')), "", "", "", "", ""])
    rows.append(["Study Characteristics", "", "Study Design (Other)", escape_tsv_value(get_value(sc, 'study_design_other')), "", "", "", "", ""])

    # Reference Cohort and Imaging
    rci = get_value(data, "reference_cohort_and_imaging", default={})

    sections = [
        ("Dataset Name", "dataset_name"),
        ("Healthy Controls (N)", "hc_n"),
        ("Healthy Controls (Age)", "hc_age"),
        ("Healthy Controls (Sex)", "hc_sex"),
        ("Imaging Modality", "imaging_modality"),
        ("Analysis Level", "analysis_level"),
        ("Preprocessing Pipeline", "preprocessing_pipeline"),
        ("Quality Checking", "quality_checking"),
        ("Quality Checking (Detail)", "quality_checking_detail"),
        ("Site Effect Handling", "site_effect_handling"),
        ("Site Effect (Detail)", "site_effect_handling_detail")
    ]

    for section_title, section_key in sections:
        section_data = get_value(rci, section_key, default={})
        if isinstance(section_data, dict):
            rows.append([
                "Reference Cohort and Imaging",
                section_title,
                "Answer",
                escape_tsv_value(get_value(section_data, "Answer")),
                escape_tsv_value(get_value(section_data, "Confidence Rating")),
                escape_tsv_value(get_value(section_data, "Negative Answer Category")),
                escape_tsv_value(get_value(section_data, "Reason")),
                escape_tsv_value(get_value(section_data, "Supporting Text")),
                escape_tsv_value(get_value(section_data, "Page/Line"))
            ])

    # Normative Modeling
    nm = get_value(data, "normative_modeling", default={})

    nm_sections = [
        ("Model Origin", "model_origin"),
        ("Model Origin (Detail)", "model_origin_detail"),
        ("Modeling Method", "modeling_method"),
        ("Software Tool", "software_tool"),
        ("Response Variable", "response_variable"),
        ("Predictor Variables", "predictor_variables"),
        ("Predictor Effects", "predictor_effects"),
        ("Normative Model Validation - Nuisance Structures", "nm_vldtn_handle_ns"),
        ("Normative Model Validation - Same Domain (Non-Independent)", "nm_vldtn_same_domain_nonindep"),
        ("Normative Model Validation - Same Domain (Independent)", "nm_vldtn_same_domain_indep"),
        ("Normative Model Validation - Different Domain", "nm_vldtn_diff_domain")
    ]

    for section_title, section_key in nm_sections:
        section_data = get_value(nm, section_key, default={})
        if isinstance(section_data, dict):
            rows.append([
                "Normative Modeling",
                section_title,
                "Answer",
                escape_tsv_value(get_value(section_data, "Answer")),
                escape_tsv_value(get_value(section_data, "Confidence Rating")),
                escape_tsv_value(get_value(section_data, "Negative Answer Category")),
                escape_tsv_value(get_value(section_data, "Reason")),
                escape_tsv_value(get_value(section_data, "Supporting Text")),
                escape_tsv_value(get_value(section_data, "Page/Line"))
            ])
        elif isinstance(section_data, str):
            rows.append([
                "Normative Modeling",
                section_title,
                "",
                escape_tsv_value(section_data),
                "", "", "", "", ""
            ])

    # Clinical Application and Analysis
    caa = get_value(data, "clinical_application_and_analysis", default={})

    caa_sections = [
        ("Clinical Dataset", "clinical_dataset"),
        ("Diseases Studied", "diseases_studied"),
        ("Clinical Groups (N)", "clinical_groups_n"),
        ("Clinical Groups (Age)", "clinical_groups_age"),
        ("Clinical Groups (Sex)", "clinical_groups_sex"),
        ("Deviation Metric", "deviation_metric")
    ]

    for section_title, section_key in caa_sections:
        section_data = get_value(caa, section_key, default={})
        if isinstance(section_data, dict):
            rows.append([
                "Clinical Application and Analysis",
                section_title,
                "Answer",
                escape_tsv_value(get_value(section_data, "Answer")),
                escape_tsv_value(get_value(section_data, "Confidence Rating")),
                escape_tsv_value(get_value(section_data, "Negative Answer Category")),
                escape_tsv_value(get_value(section_data, "Reason")),
                escape_tsv_value(get_value(section_data, "Supporting Text")),
                escape_tsv_value(get_value(section_data, "Page/Line"))
            ])
        elif isinstance(section_data, str):
            rows.append([
                "Clinical Application and Analysis",
                section_title,
                "",
                escape_tsv_value(section_data),
                "", "", "", "", ""
            ])

    # テキストフィールド
    text_fields = [
        ("Association Analysis", "association_analysis"),
        ("Key Findings (Brief)", "key_findings_brief"),
        ("Key Findings (Detailed)", "key_findings_detailed"),
        ("Key Limitations", "key_limitations"),
        ("Application Notes", "application_notes")
    ]

    for section_title, section_key in text_fields:
        value = get_value(caa, section_key)
        rows.append([
            "Clinical Application and Analysis",
            section_title,
            "",
            escape_tsv_value(value),
            "", "", "", "", ""
        ])

    # General Notes
    gn = get_value(data, "general_notes", default={})
    rows.append([
        "General Notes",
        "",
        "",
        escape_tsv_value(get_value(gn, "general_notes")),
        "", "", "", "", ""
    ])

    # TSVファイルに書き込む
    with open(output_file, 'w', encoding='utf-8', newline='') as f:
        writer = csv.writer(f, delimiter='\t')
        writer.writerows(rows)

    print(f"Conversion completed successfully!")
    print(f"Output: {output_file}")

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python script.py <input_json> <output_tsv>")
        sys.exit(1)

    input_json = sys.argv[1]
    output_tsv = sys.argv[2]

    convert_json_to_tsv(input_json, output_tsv)

PYTHON_SCRIPT

echo "Conversion completed successfully!"
echo "Output: $OUTPUT_TSV"

# 成功通知（zenityがある場合）
if command -v zenity &> /dev/null; then
    zenity --info --text="Conversion completed!\n\nInput: $INPUT_JSON\nOutput: $OUTPUT_TSV" --width=400
fi
