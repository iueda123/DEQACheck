#!/usr/bin/env bash

set -euo pipefail

#
# 概要:
# - tools/checkJsonStructureAndKey.py で JSON の構造・キーを検査
# - 生成されたサマリTSVの result 列が PASS 以外の行について、
#   ask_AiToNormalize_for_v10.sh を用いて修復を試みる
#
# 出力(本スクリプトの実行で生成・更新されるもの):
# - 構造チェックの成果物 (checkJsonStructureAndKey.py が出力)
#   - 概要TSV:  `notes/json_structure_check_<DE|QA>_<YYYYmmddHHMMSS>.tsv`
#   - 詳細ディレクトリ:  `notes/json_structure_check_<DE|QA>_<YYYYmmddHHMMSS>_details/`
#     ・FAIL/ERROR の各JSONに対する詳細内容を `<元ファイル名>.txt` で保存
# - 修復試行(--run 時; ask_AiToNormalize_for_v10.sh が出力)
#   - バックアップ: 対象JSONの同階層に `<元ファイル名>_backup.json` を作成
#   - 正規化: 対象JSONファイル本体を“上書き更新”する可能性あり（ドライランでは未変更）
#   - 一時ファイル: テンプレートJSONを同階層に一時コピーし、処理後に削除
#
# 使い方例:
#   ./03_checkStructureKeyOfJson_and_Normalization.sh --data-type DE --run --agent codex
#   ./03_checkStructureKeyOfJson_and_Normalization.sh --data-type QA --target-folder .. --output-folder ../notes
#

SCRIPT_PATH="$(readlink -f "${BASH_SOURCE[0]}")"
SCRIPT_DIR="$(dirname "${SCRIPT_PATH}")"

# 依存スクリプト:
# - checkJsonStructureAndKey.py: JSON構造とキーの妥当性を検証し、結果をTSVに出力
# - ask_AiToNormalize_for_v10.sh: AIエージェント（Gemini/Claude/Codex）を使用してJSONを正規化
CHECKER_PY="${SCRIPT_DIR}/SubFuncs_for_03/checkJsonStructureAndKey.py"
NORMALIZER_SH="${SCRIPT_DIR}/SubFuncs_for_03/ask_AiToNormalize_for_v10.sh"

if [[ ! -x "${NORMALIZER_SH}" ]]; then
  echo "エラー: 正規化スクリプトが見つかりません: ${NORMALIZER_SH}" >&2
  exit 1
fi

# 既定値
TARGET_FOLDER="${SCRIPT_DIR}/.."
OUTPUT_FOLDER="${SCRIPT_DIR}/../notes"
DATA_TYPE=""      # 必須: DE | QA
AI_AGENT="codex"  # gemini | claude | codex
DRY_RUN=true
VERBOSE=true      # 詳細出力は常に有効
LIMIT=""          # 任意: 何件まで処理するか（デバッグ用）

show_help() {
  cat <<EOF
使用方法: $(basename "$0") [OPTIONS]

JSON 構造チェックを実行し、result が PASS 以外のファイルを
ask_AiToNormalize_for_v10.sh で修復を試みます。

出力:
  - 概要TSVと詳細ディレクトリ (構造チェック結果):
      ${OUTPUT_FOLDER}/json_structure_check_<DE|QA>_<YYYYmmddHHMMSS>.tsv
      ${OUTPUT_FOLDER}/json_structure_check_<DE|QA>_<YYYYmmddHHMMSS>_details/
  - --run 時の修復出力:
      対象JSONのバックアップ: <元ファイル名>_backup.json (同階層)
      対象JSONの内容が正規化により更新される可能性

オプション:
  --data-type {DE|QA}     対象データ種別（必須）
  --target-folder PATH    走査の起点（既定: ${TARGET_FOLDER}）
  --output-folder PATH    結果出力フォルダ（既定: ${OUTPUT_FOLDER}）
  --agent {codex|gemini|claude}  使用AI（既定: ${AI_AGENT}）
  --run                   実行モード（既定はドライラン）
  --dry-run               ドライラン明示（出力ログのみ）
  --limit N               修復試行する件数を制限
  -h, --help              このヘルプ

例:
  $(basename "$0") --data-type DE --run --agent codex
  $(basename "$0") --data-type QA --target-folder .. --output-folder ../notes
EOF
}

# 引数処理
while [[ $# -gt 0 ]]; do
  case "$1" in
    --data-type)
      DATA_TYPE="$2"; shift 2;;
    --target-folder)
      TARGET_FOLDER="$(readlink -f "$2")"; shift 2;;
    --output-folder)
      OUTPUT_FOLDER="$(readlink -f "$2")"; shift 2;;
    --agent)
      AI_AGENT="$2"; shift 2;;
    --run|--execute)
      DRY_RUN=false; shift;;
    --dry-run)
      DRY_RUN=true; shift;;
    --limit)
      LIMIT="$2"; shift 2;;
    -h|--help)
      show_help; exit 0;;
    *)
      echo "不明なオプション: $1" >&2; show_help; exit 2;;
  esac
done

# 入力検証
if [[ -z "${DATA_TYPE}" ]]; then
  echo "エラー: --data-type は必須です (DE|QA)" >&2
  exit 2
fi
if [[ ! "${DATA_TYPE}" =~ ^(DE|QA)$ ]]; then
  echo "エラー: --data-type は DE または QA を指定してください" >&2
  exit 2
fi
if [[ ! -d "${TARGET_FOLDER}" ]]; then
  echo "エラー: --target-folder が存在しません: ${TARGET_FOLDER}" >&2
  exit 2
fi
mkdir -p "${OUTPUT_FOLDER}"

${VERBOSE} && echo "[INFO] checker: ${CHECKER_PY}" || true
${VERBOSE} && echo "[INFO] normalizer: ${NORMALIZER_SH}" || true
${VERBOSE} && echo "[INFO] target: ${TARGET_FOLDER}" || true
${VERBOSE} && echo "[INFO] output: ${OUTPUT_FOLDER}" || true
${VERBOSE} && echo "[INFO] data-type: ${DATA_TYPE}, agent: ${AI_AGENT}, dry-run: ${DRY_RUN}" || true

# 1) 構造チェック実行（dry-runでも常に実行）
#    実行後、以下が ${OUTPUT_FOLDER} に生成されます:
#      - 概要TSV: json_structure_check_${DATA_TYPE}_<timestamp>.tsv
#      - 詳細:   json_structure_check_${DATA_TYPE}_<timestamp>_details/*.txt
echo "実行: JSON 構造チェック (${DATA_TYPE})"
# set -e でもエラーをキャッチできるように || true を追加
CHECK_OUT=$(python3 "${CHECKER_PY}" \
  --target-folder "${TARGET_FOLDER}" \
  --output-folder "${OUTPUT_FOLDER}" \
  --data-type "${DATA_TYPE}" 2>&1) || RET=$?
# RETが設定されていない場合は0
RET=${RET:-0}

# エラー出力を常に表示
if [[ -n "${CHECK_OUT}" ]]; then
  echo "${CHECK_OUT}"
fi

if [[ ${RET} -ne 0 ]]; then
  echo "" >&2
  echo "========================================" >&2
  echo "エラー: JSON構造チェックが失敗しました" >&2
  echo "========================================" >&2
  echo "終了コード: ${RET}" >&2
  echo "" >&2
  echo "考えられる原因:" >&2
  echo "  - テンプレートファイルが見つからない" >&2
  echo "  - 対象ディレクトリが存在しない" >&2
  echo "  - JSONファイルの読み込みに失敗" >&2
  echo "" >&2
  echo "詳細なエラーメッセージは上記を確認してください。" >&2
  echo "========================================" >&2
  exit ${RET}
fi

# 2) サマリTSVのパス抽出
#    Python側の標準出力から "Summary TSV:" の行を拾う想定。
#    取得できない場合は、${OUTPUT_FOLDER} から最新の TSV をフォールバック検索。
SUMMARY_TSV=$(echo "${CHECK_OUT}" | awk -F'Summary: ' '/Summary:/ {print $2}' | tail -n1)
if [[ -z "${SUMMARY_TSV}" || ! -f "${SUMMARY_TSV}" ]]; then
  # フォールバック: 出力フォルダ内の最新のファイルを拾う
  SUMMARY_TSV=$(ls -1t "${OUTPUT_FOLDER}"/json_structure_check_${DATA_TYPE}_*.tsv 2>/dev/null | head -n1 || true)
fi
if [[ -z "${SUMMARY_TSV}" || ! -f "${SUMMARY_TSV}" ]]; then
  echo "エラー: サマリTSVが見つかりませんでした" >&2
  exit 3
fi
${VERBOSE} && echo "[INFO] summary: ${SUMMARY_TSV}" || true

# 3) PASS 以外の対象を抽出
# TSV列順: result issues notes file  (file はフルパスではなくファイル名のみ)
mapfile -t TARGET_FILES < <(awk -F"\t" 'NR>1 && $1!="PASS" {print $4}' "${SUMMARY_TSV}" | sed '/^\s*$/d')

TOTAL=${#TARGET_FILES[@]}
echo "対象ファイル数 (result!=PASS): ${TOTAL}"
if [[ ${TOTAL} -eq 0 ]]; then
  echo "全て PASS でした。処理を終了します。"
  exit 0
fi

# 4) 修復試行を実行（dry-runではログ出力のみ、--runで実際に修復）
#    --run の場合:
#      - 各対象JSONの隣に <元ファイル名>_backup.json を作成
#      - 対象JSONを正規化して上書き更新する可能性あり
#      - テンプレートJSONが一時的に同階層へコピーされ、処理後削除
COUNT=0
for f in "${TARGET_FILES[@]}"; do
  if [[ -n "${LIMIT}" && ${COUNT} -ge ${LIMIT} ]]; then
    echo "limit=${LIMIT} 到達のため中断"
    break
  fi
  COUNT=$((COUNT+1))

  # TSV の file 列はファイル名のみのため、実体のフルパスを検索
  resolved_path=$(find "${TARGET_FOLDER}" -type f -name "${f}" 2>/dev/null | head -n1 || true)
  if [[ -z "${resolved_path}" || ! -f "${resolved_path}" ]]; then
    echo "[SKIP] ファイルが見つかりません: ${f} (検索起点: ${TARGET_FOLDER})"
    continue
  fi

  echo "[${COUNT}/${TOTAL}] 修復試行: ${resolved_path} (agent=${AI_AGENT}, dry-run=${DRY_RUN})"
  if [[ "${DRY_RUN}" == true ]]; then
    "${NORMALIZER_SH}" -f "${resolved_path}" -a "${AI_AGENT}" -n ${VERBOSE:+-v}
  else
    "${NORMALIZER_SH}" -f "${resolved_path}" -a "${AI_AGENT}" -r ${VERBOSE:+-v}
  fi
done

echo "完了: 修復試行が終了しました。"
