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
OUTPUT_MD="./md/${FOLDER_TYPE}/${JSON_FILENAME%.json}.md"

# 入力ファイルの存在確認
if [ ! -f "$INPUT_JSON" ]; then
    echo "Error: Input file not found: $INPUT_JSON"
    exit 1
fi

# 出力ディレクトリの作成
mkdir -p "$(dirname "$OUTPUT_MD")"

# JSONからMarkdownへの変換
echo "Converting: $INPUT_JSON -> $OUTPUT_MD"

# Pythonスクリプトを使って変換
python3 - "$INPUT_JSON" "$OUTPUT_MD" << 'PYTHON_SCRIPT'
import json
import sys
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

def convert_json_to_markdown(json_file, output_file):
    """JSONファイルをMarkdownに変換"""

    # JSONファイルを読み込む
    with open(json_file, 'r', encoding='utf-8') as f:
        data = json.load(f)

    md_lines = []

    # タイトル
    md_lines.append("# Data Extraction Report")
    md_lines.append("")

    # Study Identification
    md_lines.append("## Study Identification")
    md_lines.append("")
    si = get_value(data, "study_identification", default={})
    md_lines.append(f"- **Study ID**: {get_value(si, 'study_id')}")
    md_lines.append(f"- **Reference Files**: {get_value(si, 'reference_file_names')}")
    md_lines.append(f"- **Author/Journal/Year**: {get_value(si, 'author_journal_year')}")
    md_lines.append(f"- **Title**: {get_value(si, 'title')}")
    md_lines.append(f"- **DOI**: {get_value(si, 'doi')}")
    md_lines.append("")

    # Study Characteristics
    md_lines.append("## Study Characteristics")
    md_lines.append("")
    sc = get_value(data, "study_characteristics", default={})
    md_lines.append(f"- **Study Objective**: {get_value(sc, 'study_objective')}")
    md_lines.append(f"- **Study Design**: {get_value(sc, 'study_design')}")
    md_lines.append(f"- **Study Design (Other)**: {get_value(sc, 'study_design_other')}")
    md_lines.append("")

    # Reference Cohort and Imaging
    md_lines.append("## Reference Cohort and Imaging")
    md_lines.append("")
    rci = get_value(data, "reference_cohort_and_imaging", default={})

    # 各サブセクション
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
        md_lines.append(f"### {section_title}")
        md_lines.append("")
        section_data = get_value(rci, section_key, default={})
        if isinstance(section_data, dict):
            for key in ["Answer", "Confidence Rating", "Negative Answer Category", "Reason", "Supporting Text", "Page/Line"]:
                value = get_value(section_data, key)
                if value != "N/A":
                    md_lines.append(f"- **{key}**: {value}")
        md_lines.append("")

    # Normative Modeling
    md_lines.append("## Normative Modeling")
    md_lines.append("")
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
        md_lines.append(f"### {section_title}")
        md_lines.append("")
        section_data = get_value(nm, section_key, default={})
        if isinstance(section_data, dict):
            for key in ["Answer", "Confidence Rating", "Negative Answer Category", "Reason", "Supporting Text", "Page/Line"]:
                value = get_value(section_data, key)
                if value != "N/A":
                    md_lines.append(f"- **{key}**: {value}")
        elif isinstance(section_data, str):
            md_lines.append(section_data)
        md_lines.append("")

    # Clinical Application and Analysis
    md_lines.append("## Clinical Application and Analysis")
    md_lines.append("")
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
        md_lines.append(f"### {section_title}")
        md_lines.append("")
        section_data = get_value(caa, section_key, default={})
        if isinstance(section_data, dict):
            for key in ["Answer", "Confidence Rating", "Negative Answer Category", "Reason", "Supporting Text", "Page/Line"]:
                value = get_value(section_data, key)
                if value != "N/A":
                    md_lines.append(f"- **{key}**: {value}")
        elif isinstance(section_data, str):
            md_lines.append(section_data)
        md_lines.append("")

    # テキストフィールド
    text_fields = [
        ("Association Analysis", "association_analysis"),
        ("Key Findings (Brief)", "key_findings_brief"),
        ("Key Findings (Detailed)", "key_findings_detailed"),
        ("Key Limitations", "key_limitations"),
        ("Application Notes", "application_notes")
    ]

    for section_title, section_key in text_fields:
        md_lines.append(f"### {section_title}")
        md_lines.append("")
        value = get_value(caa, section_key)
        md_lines.append(value)
        md_lines.append("")

    # General Notes
    md_lines.append("## General Notes")
    md_lines.append("")
    gn = get_value(data, "general_notes", default={})
    md_lines.append(get_value(gn, "general_notes"))
    md_lines.append("")

    # フッター
    md_lines.append("---")
    md_lines.append(f"*Generated on {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}*")
    md_lines.append("")

    # ファイルに書き込む
    with open(output_file, 'w', encoding='utf-8') as f:
        f.write("\n".join(md_lines))

    print(f"Conversion completed successfully!")
    print(f"Output: {output_file}")

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python script.py <input_json> <output_md>")
        sys.exit(1)

    input_json = sys.argv[1]
    output_md = sys.argv[2]

    convert_json_to_markdown(input_json, output_md)

PYTHON_SCRIPT

# 成功通知（zenityがある場合）
if command -v zenity &> /dev/null; then
    zenity --info --text="Conversion completed!\n\nInput: $INPUT_JSON\nOutput: $OUTPUT_MD" --width=400
fi
