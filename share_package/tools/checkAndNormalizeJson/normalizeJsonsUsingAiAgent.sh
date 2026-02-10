#!/usr/bin/env bash
#
# このスクリプトはサマリTSVに列挙された対象ファイルを正規化します。
# 対象結果 (FAIL/WARN/ALL) や正規化スクリプトを選択できます。

set -euo pipefail

# 概要:
# - サマリTSVの result 列から対象(Fail/Warn/All)を抽出し、
#   指定された ask_AiToNormalize_for_*.sh で修復を試みる
#
# 出力(本スクリプトの実行で生成・更新されるもの):
# - 構造チェックの成果物 (checkJsonStructureAndKey.py が出力)
#   - 概要TSV:  `check_rslt/json_structure_check_<DATA_TYPE>_<YYYYmmddHHMMSS>.tsv`
#   - 詳細ディレクトリ:  `check_rslt/json_structure_check_<DATA_TYPE>_<YYYYmmddHHMMSS>_details/`
#     ・FAIL/WARN/ERROR の各JSONに対する詳細内容を `<元ファイル名>.txt` で保存
# - 修復試行(--run 時; ask_AiToNormalize_for_*.sh が出力)
#   - バックアップ: 対象JSONの同階層に `<元ファイル名>_backup.json` を作成
#   - 正規化: 対象JSONファイル本体を“上書き更新”する可能性あり（ドライランでは未変更）
#   - 一時ファイル: テンプレートJSONを同階層に一時コピーし、処理後に削除
#
# 使い方例:
#   ./checkStructureKeyOfJson_and_Normalization.sh --summary-tsv ../check_rslt/json_structure_check_DE_*.tsv --ask-script ./subfuncs/ask_AiToNormalize_for_DE_v12.sh --run
#

SCRIPT_PATH="$(readlink -f "${BASH_SOURCE[0]}")"
SCRIPT_DIR="$(dirname "${SCRIPT_PATH}")"

# 依存スクリプト:
# - ask_AiToNormalize_for_*.sh: AIエージェント（Gemini/Claude/Codex）を使用してJSONを正規化
DATA_TYPE_NAME=""

DEFAULT_TSV_DIR="${SCRIPT_DIR}/str_and_key_check_results"
AI_AGENT="codex"  # gemini | claude | codex
DRY_RUN=true
VERBOSE=true      # 詳細出力は常に有効
LIMIT=""          # 任意: 何件まで処理するか（デバッグ用）
SUMMARY_TSV_OVERRIDE=""
RESULT_FILTER="FAIL"  # FAIL | WARN | ALL

show_help() {
  cat <<EOF
使用方法: $(basename "$0") [OPTIONS]

サマリTSVに列挙された指定 result のファイルを
ask_AiToNormalize_for_*.sh で修復を試みます。

出力:
  - --run 時の修復出力:
      対象JSONのバックアップ: <元ファイル名>_backup.json (同階層)
      対象JSONの内容が正規化により更新される可能性

オプション:
  --str-and-key-check-result-tsv PATH      既存のサマリTSVを指定
                                           （省略時: ${DEFAULT_TSV_DIR}/ 下の最新TSVを自動選択）
  --data-type, -d {DE_v10|DE_v11|DE_v12}  対象データ型を指定（必須）
  --result {FAIL|WARN|ALL} 対象とする result を指定（既定: FAIL）
  --agent {codex|gemini|claude}  使用AI（既定: ${AI_AGENT}）
  --run, -r               実行モード（既定はドライラン）
  --limit N               修復試行する件数を制限
  -h, --help              このヘルプ

例:
  $(basename "$0") -d DE_v12 --run
  $(basename "$0") --data-type DE_v11 --str-and-key-check-result-tsv ./str_and_key_check_results/json_structure_check_DE_v11_*.tsv --run
EOF
}

# 引数処理
while [[ $# -gt 0 ]]; do
  case "$1" in
    --str-and-key-check-result-tsv)
      SUMMARY_TSV_OVERRIDE="$(readlink -f "$2")"; shift 2;;
    --data-type|-d)
      DATA_TYPE_NAME="$2"; shift 2;;
    --result)
      RESULT_FILTER="$2"; shift 2;;
    --agent)
      AI_AGENT="$2"; shift 2;;
    --run|-r|--execute)
      DRY_RUN=false; shift;;
    --limit)
      LIMIT="$2"; shift 2;;
    -h|--help)
      show_help; exit 0;;
    *)
      echo "不明なオプション: $1" >&2; show_help; exit 2;;
  esac
done

# 入力検証
# --data-type を先に検証（TSV自動選択でフィルタに使うため）
if [[ -z "${DATA_TYPE_NAME}" ]]; then
  echo "エラー: --data-type (-d) は必須です（DE_v10, DE_v11, DE_v12 のいずれか）" >&2
  exit 2
fi
if [[ ! "${DATA_TYPE_NAME}" =~ ^(DE_v10|DE_v11|DE_v12)$ ]]; then
  echo "エラー: --data-type は DE_v10, DE_v11, DE_v12 のいずれかを指定してください" >&2
  exit 2
fi
# --str-and-key-check-result-tsv が未指定の場合、DATA_TYPE_NAME に一致する最新TSVを自動選択
if [[ -z "${SUMMARY_TSV_OVERRIDE}" ]]; then
  if [[ ! -d "${DEFAULT_TSV_DIR}" ]]; then
    echo "エラー: デフォルトTSVディレクトリが見つかりません: ${DEFAULT_TSV_DIR}" >&2
    echo "  --str-and-key-check-result-tsv でTSVファイルを指定してください" >&2
    exit 2
  fi
  SUMMARY_TSV_OVERRIDE="$(find "${DEFAULT_TSV_DIR}" -maxdepth 1 -name "json_structure_check_${DATA_TYPE_NAME}_*.tsv" -printf '%f\n' | sort | tail -n1)"
  if [[ -z "${SUMMARY_TSV_OVERRIDE}" ]]; then
    echo "エラー: ${DEFAULT_TSV_DIR} に ${DATA_TYPE_NAME} のTSVファイルが見つかりません" >&2
    exit 2
  fi
  SUMMARY_TSV_OVERRIDE="${DEFAULT_TSV_DIR}/${SUMMARY_TSV_OVERRIDE}"
  ${VERBOSE} && echo "[INFO] 自動選択されたサマリTSV: ${SUMMARY_TSV_OVERRIDE}" || true
fi
NORMALIZER_SH="${SCRIPT_DIR}/subfuncs/ask_AiToNormalize_for_${DATA_TYPE_NAME}.sh"
if [[ ! -x "${NORMALIZER_SH}" ]]; then
  echo "エラー: 正規化スクリプトが見つかりません: ${NORMALIZER_SH}" >&2
  exit 1
fi
if [[ ! "${RESULT_FILTER}" =~ ^(FAIL|WARN|ALL)$ ]]; then
  echo "エラー: --result は FAIL, WARN, ALL のいずれかを指定してください" >&2
  exit 2
fi
${VERBOSE} && echo "[INFO] normalizer: ${NORMALIZER_SH}" || true
${VERBOSE} && echo "[INFO] agent: ${AI_AGENT}, dry-run: ${DRY_RUN}, result: ${RESULT_FILTER}" || true
if [[ -n "${SUMMARY_TSV_OVERRIDE}" ]]; then
  ${VERBOSE} && echo "[INFO] summary-tsv: ${SUMMARY_TSV_OVERRIDE}" || true
fi

# 1) サマリTSVのパスを使用
SUMMARY_TSV="${SUMMARY_TSV_OVERRIDE}"
if [[ -z "${SUMMARY_TSV}" || ! -f "${SUMMARY_TSV}" ]]; then
  echo "エラー: サマリTSVが見つかりませんでした" >&2
  exit 3
fi
${VERBOSE} && echo "[INFO] summary: ${SUMMARY_TSV}" || true

# DATA_TYPE をサマリTSVのファイル名から推定
DATA_TYPE=$(basename "${SUMMARY_TSV}" | sed -n 's/^json_structure_check_\([^_]\+\)_.*/\1/p')
if [[ -z "${DATA_TYPE}" ]]; then
  echo "エラー: サマリTSVのファイル名から DATA_TYPE を推定できませんでした" >&2
  exit 3
fi
${VERBOSE} && echo "[INFO] data-type: ${DATA_TYPE}" || true

# 3) 対象を抽出
# TSV列順: result issues notes file
case "${RESULT_FILTER}" in
  FAIL)
    FILTER_AWK='NR>1 && $1=="FAIL" {print $4}';;
  WARN)
    FILTER_AWK='NR>1 && $1=="WARN" {print $4}';;
  ALL)
    FILTER_AWK='NR>1 && $1!="PASS" {print $4}';;
esac
mapfile -t TARGET_FILES < <(awk -F"\t" "${FILTER_AWK}" "${SUMMARY_TSV}" | sed '/^\s*$/d')

TOTAL=${#TARGET_FILES[@]}
echo "対象ファイル数 (result=${RESULT_FILTER}): ${TOTAL}"
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
DETAILS_DIR="${SUMMARY_TSV%.tsv}_details"
for f in "${TARGET_FILES[@]}"; do
  if [[ -n "${LIMIT}" && ${COUNT} -ge ${LIMIT} ]]; then
    echo "limit=${LIMIT} 到達のため中断"
    break
  fi
  COUNT=$((COUNT+1))

  file_name="${f}"
  details_path="${DETAILS_DIR}/${file_name}.txt"
  if [[ ! -f "${details_path}" ]]; then
    echo "[SKIP] 詳細ファイルが見つかりません: ${details_path}"
    continue
  fi
  resolved_path=$(sed -n 's/^Target:[[:space:]]*//p' "${details_path}" | head -n1 || true)
  if [[ -z "${resolved_path}" || ! -f "${resolved_path}" ]]; then
    echo "[SKIP] 対象パスが解決できません: ${details_path}"
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
if [[ "${DRY_RUN}" == true ]]; then
  echo "実行モードにするには --run または -r を指定してください。"
fi
