#!/bin/bash
if [[ -z "${BASH_VERSION:-}" ]]; then
    exec /bin/bash "$0" "$@"
fi

# ask-copilot-to-de-from-Kim2023-using-claude-sonnet-4.6.sh
#
# Kim2023 の材料前処理（アンカー付与）と情報抽出（copilot / claude-sonnet-4.6）を
# 1ステップで実行するスクリプト。
#
# 使用方法:
#   ./ask-copilot-to-de-from-Kim2023-using-claude-sonnet-4.6.sh           # ドライラン（デフォルト）
#   ./ask-copilot-to-de-from-Kim2023-using-claude-sonnet-4.6.sh --run     # 実行モード

this_script_path="$(readlink -f "${BASH_SOURCE[0]}")"
this_script_dir="$(dirname "${this_script_path}")"

#######################
# 設定（固定値）
#######################
AGENT="copilot"
MODEL="claude-sonnet-4.6"
AUTHOR_YEAR="Kim2023"
GUIDE_NUMBER="001"

GUIDE_FILE="${this_script_dir}/DE_Guide_001.md"
KEYWORDS_FILE="${this_script_dir}/dataset-name-keywords.md"
RAW_MATERIALS="${this_script_dir}/../../../data/${AUTHOR_YEAR}/materials/optimized"
PROCESSED_MATERIALS="${this_script_dir}/../../studies/${AUTHOR_YEAR}/materials/optimized"
OUTPUT_BASE="${this_script_dir}/../../studies/${AUTHOR_YEAR}/extracted-info/${GUIDE_NUMBER}"

#######################
# ログ出力補助関数
#######################
function techo() {
    local newline=true
    if [[ "$1" == "-n" ]]; then
        newline=false
        shift
    fi
    if [[ -n "${_log_file:-}" ]]; then
        if $newline; then printf '%s\n' "$*" | tee -a "${_log_file}"
        else         printf '%s'   "$*" | tee -a "${_log_file}"
        fi
    else
        if $newline; then printf '%s\n' "$*"
        else         printf '%s'   "$*"
        fi
    fi
}

#######################
# オプション解析
#######################
DryRun=true
for arg in "$@"; do
    case "${arg}" in
        -r|--run|--execute) DryRun=false ;;
        -d|--dry-run)       DryRun=true  ;;
        -h|--help)
            echo "使用方法: $(basename "${this_script_path}") [--run | --dry-run]"
            echo "  --run      実行モード（AI を実際に呼び出す）"
            echo "  --dry-run  ドライランモード（デフォルト）"
            exit 0
            ;;
        *)
            echo "エラー: 不明なオプション: ${arg}"
            exit 1
            ;;
    esac
done

#######################
# ファイル存在確認
#######################
if [[ ! -f "${GUIDE_FILE}" ]]; then
    echo "エラー: ガイドファイルが見つかりません: ${GUIDE_FILE}"
    exit 1
fi
if [[ ! -f "${KEYWORDS_FILE}" ]]; then
    echo "エラー: キーワードファイルが見つかりません: ${KEYWORDS_FILE}"
    exit 1
fi
if [[ ! -d "${RAW_MATERIALS}" ]]; then
    echo "エラー: 材料フォルダが見つかりません: ${RAW_MATERIALS}"
    exit 1
fi

#######################
# Step 1: 材料の前処理（アンカー付与）
#######################
echo ""
echo "============================================"
echo "  Step 1: 材料の前処理（アンカー付与）"
echo "  入力: ${RAW_MATERIALS}"
echo "  出力: ${PROCESSED_MATERIALS}"
echo "============================================"

mkdir -p "${PROCESSED_MATERIALS}"

add_anchors_to_headings() {
    local file="$1"
    awk '
    /^#{1,6} / {
        if ($0 ~ /\{#[a-z0-9][a-z0-9-]*\}$/) { print; next }
        line = $0
        sub(/^#{1,6} /, "", line)
        anchor = tolower(line)
        gsub(/ /, "-", anchor)
        gsub(/[^a-z0-9-]/, "", anchor)
        gsub(/^-+/, "", anchor)
        gsub(/-+$/, "", anchor)
        gsub(/--+/, "-", anchor)
        if (anchor == "") { print; next }
        print $0 " {#" anchor "}"
        next
    }
    { print }
    ' "${file}"
}

_processed_count=0
for md_file in "${RAW_MATERIALS}"/*.md; do
    [[ -e "${md_file}" ]] || continue
    filename="$(basename "${md_file}")"
    add_anchors_to_headings "${md_file}" > "${PROCESSED_MATERIALS}/${filename}"
    echo "  -> ${PROCESSED_MATERIALS}/${filename}"
    (( _processed_count++ ))
done

if [[ ${_processed_count} -eq 0 ]]; then
    echo "エラー: .md ファイルが見つかりません: ${RAW_MATERIALS}"
    exit 1
fi
echo "  ${_processed_count} ファイルを前処理しました"

#######################
# Step 2: タイムスタンプと出力ファイル名
#######################
_timestamp=$(date +%Y%m%d-%H%M%S)
_result_filename="${AUTHOR_YEAR}_by-${AGENT}-${MODEL}_${_timestamp}.json"

#######################
# 既存結果の確認
#######################
mkdir -p "${OUTPUT_BASE}"
if ls "${OUTPUT_BASE}"/${AUTHOR_YEAR}_by-${AGENT}-${MODEL}_*.json 2>/dev/null | grep -q .; then
    echo ""
    echo "警告: 既存の結果ファイルが見つかりました:"
    ls "${OUTPUT_BASE}"/${AUTHOR_YEAR}_by-${AGENT}-${MODEL}_*.json
    echo ""
    read -r -p "処理を続行しますか？ [y/N]: " _ans
    if [[ ! "${_ans}" =~ ^[Yy]$ ]]; then
        echo "処理をスキップします"
        exit 0
    fi
fi

#######################
# Step 2: ワークスペースの準備
#######################
echo ""
echo "============================================"
echo "  Step 2: 情報抽出"
echo "  エージェント: ${AGENT} (${MODEL})"
echo "  対象文献    : ${AUTHOR_YEAR}"
echo "  出力先      : ${OUTPUT_BASE}/${_result_filename}"
if [[ ${DryRun} == true ]]; then
    echo "  モード      : ドライラン（実行なし）"
else
    echo "  モード      : 実行モード"
fi
echo "============================================"

AI_WORKSPACE="${this_script_dir}/../../../ai_workspace/${AGENT}_de${GUIDE_NUMBER}_${_timestamp}"
mkdir -p "${AI_WORKSPACE}/study_1"

# ガイドファイルをコピー
cp "${GUIDE_FILE}"    "${AI_WORKSPACE}/"
cp "${KEYWORDS_FILE}" "${AI_WORKSPACE}/"

# 前処理済み材料をコピー
cp "${PROCESSED_MATERIALS}"/*.md "${AI_WORKSPACE}/study_1/" 2>/dev/null || true
echo ""
echo "材料ファイルをコピーしました: ${AI_WORKSPACE}/study_1/"

# ログファイル
_log_file="${AI_WORKSPACE}/${_result_filename%.json}.log"

#######################
# AI コマンド
#######################
_guide_relative="./$(basename "${GUIDE_FILE}")"
_result_relative="./${_result_filename}"
_prompt="${_guide_relative} に従って作業をしてください。対象文献の資料は study_1/ フォルダ内にあります。抽出結果は ${_result_relative} へ書き込んでください。"

techo ""
techo "========== Copilot コマンド =========="
techo "copilot \\"
techo "    --prompt \"${_prompt}\" \\"
techo "    --model \"${MODEL}\" \\"
techo "    --allow-all-tools \\"
techo "    --add-dir . \\"
techo "    --deny-url \"*\""
techo "======================================"
techo ""

_previous_dir="$(pwd)"
cd "${AI_WORKSPACE}" || exit 1

if [[ ${DryRun} == false ]]; then
    sleep 3
    techo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"
    techo "Copilot が作業を実行中..."
    techo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"

    copilot \
        --prompt "${_prompt}" \
        --model "${MODEL}" \
        --allow-all-tools \
        --add-dir . \
        --deny-url "*" \
        2>&1 | tee -a "${_log_file}"

    techo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
    techo "Copilot による作業が完了しました"
    techo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
fi

cd "${_previous_dir}" || exit 1

#######################
# 結果処理
#######################
if [[ ${DryRun} == false ]]; then
    if [[ ! -f "${AI_WORKSPACE}/${_result_filename}" ]]; then
        echo ""
        echo "エラー: 結果ファイルが生成されませんでした: ${_result_filename}"
        echo "AIワークスペースを保持します: ${AI_WORKSPACE}"
        exit 1
    fi

    cp "${AI_WORKSPACE}/${_result_filename}" "${OUTPUT_BASE}/"
    echo ""
    echo "結果ファイル: ${OUTPUT_BASE}/${_result_filename}"

    if [[ -f "${AI_WORKSPACE}/${_result_filename%.json}.log" ]]; then
        cp "${AI_WORKSPACE}/${_result_filename%.json}.log" "${OUTPUT_BASE}/"
    fi

    rm -rf "${AI_WORKSPACE}"
    echo "AIワークスペースを削除しました"
else
    echo ""
    echo "【ドライラン】AIエージェントは実行されませんでした"
    echo "AIワークスペース: ${AI_WORKSPACE}"
    echo "予定出力ファイル: ${OUTPUT_BASE}/${_result_filename}"
    echo ""
    echo "※実行モードにするには --run オプションを指定してください"
    rm -rf "${AI_WORKSPACE}"
fi

echo ""
echo "============================================"
echo "  処理が完了しました"
echo "============================================"
echo ""
