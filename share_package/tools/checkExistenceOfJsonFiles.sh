#!/usr/bin/env bash
set -euo pipefail

# 概要:
#   ../<AuthorYear>/<TYPE>/json/ 配下に、指定した AI エージェント名
#   （gemini, claude, codex）によるファイルが存在するかを調べ、
#   結果を notes ディレクトリに出力します（デフォルト ../notes）。
#   TYPE は DE または QA。
#
# 使い方:
#   tools/checkExistenceOfJsonFiles.sh [--type DE|QA] [--agent NAME] [--output-dir DIR]
#     --type, -t: 対象タイプ（DE または QA）。省略時は DE。
#     --agent, -a: エージェント名（gemini | claude | codex）。未指定時は全て（gemini, claude, codex）。
#     --output-dir, -o: 出力先ディレクトリ（指定がなければ ../notes）。

usage() {
  cat <<'EOF'
使い方: tools/checkExistenceOfJsonFiles.sh [--type DE|QA] [--agent NAME] [--output-dir DIR]
  --type, -t: 対象タイプ（DE または QA。省略時は DE）
  --agent, -a: エージェント名（gemini | claude | codex）。未指定時は全て（gemini, claude, codex）
  --output-dir, -o: 出力先ディレクトリ（省略可。既定は ../notes）
EOF
}

if [[ ${1:-} == "-h" || ${1:-} == "--help" ]]; then
  usage
  exit 0
fi

type_raw="DE"
agent_raw=""
notes_dir_option=""

# スクリプト基準のプロジェクトルート（tools の 1 つ上）
script_dir="$(cd "$(dirname "$0")" && pwd)"
project_root="$(cd "${script_dir}/.." && pwd)"

# オプション解析
while [[ $# -gt 0 ]]; do
  case "$1" in
    -h|--help)
      usage; exit 0 ;;
    --type|-t)
      if [[ $# -lt 2 ]]; then
        echo "--type オプションに値が必要です（DE または QA）。" >&2
        exit 1
      fi
      type_raw="$2"; shift 2 ;;
    --type=*)
      type_raw="${1#*=}"; shift ;;
    -t=*)
      type_raw="${1#*=}"; shift ;;
    --agent|-a)
      if [[ $# -lt 2 ]]; then
        echo "--agent オプションに値が必要です（gemini|claude|codex）。" >&2
        exit 1
      fi
      agent_raw="$2"; shift 2 ;;
    --agent=*)
      agent_raw="${1#*=}"; shift ;;
    -a=*)
      agent_raw="${1#*=}"; shift ;;
    --output-dir|-o)
      if [[ $# -lt 2 ]]; then
        echo "--output-dir オプションにディレクトリを指定してください。" >&2
        exit 1
      fi
      notes_dir_option="$2"; shift 2 ;;
    --output-dir=*)
      notes_dir_option="${1#*=}"; shift ;;
    -o=*)
      notes_dir_option="${1#*=}"; shift ;;
    --)
      shift; break ;;
    -*)
      echo "不明なオプションです: $1" >&2; exit 1 ;;
    *)
      break ;;
  esac
done

notes_dir_default="${project_root}/notes"

# 位置引数は解釈しない（残っていればエラー）
if [[ $# -gt 0 ]]; then
  echo "未知の引数があります: $*" >&2
  usage
  exit 1
fi

# 出力先の決定（--output-dir 指定がなければ既定）
if [[ -n "$notes_dir_option" ]]; then
  notes_dir="$notes_dir_option"
else
  notes_dir="$notes_dir_default"
fi

mkdir -p "$notes_dir"

type_uc="$(printf '%s' "$type_raw" | tr '[:lower:]' '[:upper:]')"
case "$type_uc" in
  DE|QA) ;;
  *)
    echo "--type は DE または QA を指定してください（指定値: $type_raw）。" >&2
    exit 1
    ;;
esac

timestamp="$(date '+%F %T')"

# 対象ディレクトリ列挙（<project_root>/<AuthorYear>/<TYPE>/json）
mapfile -t json_dirs < <(find "${project_root}" -maxdepth 3 -type d -path "${project_root}/*/${type_uc}/json" | sort)

# 対象エージェントの決定
declare -a agents
if [[ -n "$agent_raw" ]]; then
  agent_single="$(printf '%s' "$agent_raw" | tr '[:upper:]' '[:lower:]')"
  case "$agent_single" in
    gemini|claude|codex) agents+=("$agent_single") ;;
    *)
      echo "エージェント名は gemini, claude, codex のいずれかを指定してください（指定値: $agent_raw）。" >&2
      exit 1
      ;;
  esac
else
  agents=(gemini claude codex)
fi

for agent in "${agents[@]}"; do
  out_file="$notes_dir/${type_uc}_json_existence_by_${agent}.tsv"
  reprocess_list_file="$notes_dir/StudiesWhichShouldBeReprocessed_by_${agent}.txt"

  # TSV ヘッダ
  printf "AuthorYear\tType\tAgent\tStatus\tFilePath\n" > "$out_file"
  # 再処理対象リスト（上書き初期化）
  : > "$reprocess_list_file"

  if [[ ${#json_dirs[@]} -eq 0 ]]; then
    {
      echo "対象ディレクトリ（<AuthorYear>/${type_uc}/json）が見つかりませんでした。"
    } >> "$out_file"
    echo "レポートを出力しました: $out_file"
    continue
  fi

  total=0
  found=0
  missing=0
  missing_list=()

  shopt -s nullglob
  for dir in "${json_dirs[@]}"; do
    # dir 例: <project_root>/Baldwin2022/${type_uc}/json
    total=$((total + 1))
    # project_root/ を除外し、最初のセグメントを AuthorYear として抽出
    rel_dir="${dir#${project_root}/}"
    author_year="${rel_dir%%/*}"

    # エージェント名の大文字/小文字の違い（例: Gemini, CLAUDE など）も存在として扱うため、
    # -iname を使って大文字小文字を無視して検索する。
    pattern_filename="${type_uc}_${author_year}_by_${agent}_*.json"
    mapfile -t matches < <(find "$dir" -maxdepth 1 -type f -iname "$pattern_filename" | sort)

    if (( ${#matches[@]} > 0 )); then
      found=$((found + 1))
      for f in "${matches[@]}"; do
        # project_root/ を外して相対パスをきれいに表示
        clean="${f#${project_root}/}"
        printf "%s\t%s\t%s\tFOUND\t%s\n" \
          "$author_year" "$type_uc" "$agent" "$clean" >> "$out_file"
      done
    else
      missing=$((missing + 1))
      printf "%s\t%s\t%s\tNOT_FOUND\t\n" \
        "$author_year" "$type_uc" "$agent" >> "$out_file"
      missing_list+=("$author_year")
    fi
  done
  shopt -u nullglob

  # 再処理対象スタディ一覧を出力（重複排除 + ソート）
  if (( ${#missing_list[@]} > 0 )); then
    printf "%s\n" "${missing_list[@]}" | sort -u > "$reprocess_list_file"
  else
    # 空の場合は空ファイルを維持
    : > "$reprocess_list_file"
  fi

  echo "レポートを出力しました: $out_file"
  echo "再処理リストを出力しました: $reprocess_list_file"
done
