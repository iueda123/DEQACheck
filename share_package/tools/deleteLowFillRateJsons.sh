#!/usr/bin/env bash

set -euo pipefail

# Delete <agent>-related JSON files for studies where <agent> fill rate < 0.90

this_script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
repo_root="$(cd "${this_script_dir}/.." && pwd)"
tsv_file="${repo_root}/notes/DE_JsonFillRate.tsv"

dry_run=true
quiet=false
agent=""
threshold="0.90"

usage() {
  cat <<EOF
Usage: $(basename "$0") --agent {gemini|claude|codex} [--threshold VAL] [--run] [--quiet]

Reads notes/DE_JsonFillRate.tsv, selects rows where the file path
contains 'by_<agent>' and the rate < threshold, and deletes <agent>-related
JSONs under <AuthorYear>/DE/json for those studies.

Options:
  --agent VAL       One of: gemini, claude, codex (required)
  --threshold VAL   Rate threshold (default: 0.90)
  --run        Actually delete files (default: dry-run)
  --quiet      Suppress non-essential output
  -h, --help   Show this help

Dry-run prints what would be deleted without removing anything.
EOF
}

while [[ $# -gt 0 ]]; do
  case "$1" in
    --agent)
      if [[ $# -lt 2 ]]; then
        echo "Error: --agent requires a value" >&2
        usage
        exit 1
      fi
      agent="$2"
      shift 2
      ;;
    --threshold)
      if [[ $# -lt 2 ]]; then
        echo "Error: --threshold requires a value" >&2
        usage
        exit 1
      fi
      threshold="$2"
      shift 2
      ;;
    --run)
      dry_run=false
      shift
      ;;
    --quiet)
      quiet=true
      shift
      ;;
    -h|--help)
      usage
      exit 0
      ;;
    *)
      echo "Unknown option: $1" >&2
      usage
      exit 1
      ;;
  esac
done

if [[ ! -f "${tsv_file}" ]]; then
  echo "Error: TSV not found: ${tsv_file}" >&2
  exit 1
fi

# Validate agent option
case "${agent}" in
  gemini|claude|codex)
    ;;
  "")
    echo "Error: --agent is required (gemini|claude|codex)" >&2
    usage
    exit 1
    ;;
  *)
    echo "Error: invalid --agent: ${agent}. Expected gemini|claude|codex" >&2
    usage
    exit 1
    ;;
esac

# Collect unique study IDs (AuthorYear) where selected agent rate < 0.90
# Validate threshold
if [[ ! ${threshold} =~ ^(0(\.[0-9]+)?|1(\.0+)?)$ ]]; then
  echo "Error: --threshold must be a number between 0 and 1 (e.g., 0.90)" >&2
  exit 1
fi

# Collect unique study IDs (AuthorYear) where selected agent rate < threshold
mapfile -t studies < <(awk -F '\t' -v agent="${agent}" -v thr="${threshold}" 'NR>1 { r=$6+0; if ($3 ~ ("by_" agent) && r < thr+0) print $1 }' "${tsv_file}" | sort -u)

if [[ ${#studies[@]} -eq 0 ]]; then
  ${quiet} || echo "No ${agent} targets with rate < ${threshold} found."
  exit 0
fi

total=0
deleted=0

${quiet} || {
  echo "Targets (rate<${threshold}, ${agent}): ${#studies[@]}"
  printf ' - %s\n' "${studies[@]}"
  if [[ "${dry_run}" == true ]]; then
    echo "Mode: DRY-RUN"
  else
    echo "Mode: RUN"
  fi
}

for s in "${studies[@]}"; do
  dir="${repo_root}/${s}/DE/json"
  if [[ ! -d "${dir}" ]]; then
    ${quiet} || echo "[skip] Missing dir: ${dir}"
    continue
  fi

  # <agent>-related JSONs
  mapfile -t files < <(find "${dir}" -maxdepth 1 -type f -iname "*${agent}*.json" -print | sort)
  if [[ ${#files[@]} -eq 0 ]]; then
    ${quiet} || echo "[none] No ${agent} JSONs in ${dir}"
    continue
  fi

  for f in "${files[@]}"; do
    (( total += 1 ))
    if [[ "${dry_run}" == true ]]; then
      ${quiet} || echo "[dry-run] rm \"${f}\""
    else
      rm -f -- "${f}"
      (( deleted += 1 ))
      ${quiet} || echo "[deleted] ${f}"
    fi
  done
done

${quiet} || echo "Done. Candidates: ${total}, Deleted: ${deleted}"

exit 0
