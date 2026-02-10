#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PY_SCRIPT="$SCRIPT_DIR/subfuncs/checkJsonStructureAndKey.py"

print_usage() {
  cat <<'EOF'
Usage:
  ./checkJsonStructureAndKey.sh --data-type <DATA_TYPE> --target-folder <PATH> --template <PATH> [--result-folder <PATH>]

Required:
  --data-type      Data type (e.g., DE_v12, QA_v9)
  --target-folder  Root folder to scan
  --template       Template JSON path

Optional:
  --result-folder  Output directory for results (default: ./str_and_key_check_results)
  -h, --help       Show this help

Example:
  ./checkJsonStructureAndKey.sh \
    --data-type DE_v12 \
    --target-folder ../../data \
    --template ../../templates/DE_v12_Author20XX_by_Someone_YYYYmmddHHMMSS.json
EOF
}

case "${1:-}" in
  -h|--help)
    print_usage
    exit 0
    ;;
esac

if [[ $# -eq 0 ]]; then
  echo "Missing required argument: --data-type" >&2
  echo "Missing required argument: --target-folder" >&2
  echo "Missing required argument: --template" >&2
  echo "" >&2
  print_usage >&2
  exit 2
fi

missing=()
has_flag() {
  local flag="$1"
  shift
  for arg in "$@"; do
    if [[ "$arg" == "$flag" ]]; then
      return 0
    fi
  done
  return 1
}

if ! has_flag --data-type "$@"; then
  missing+=("--data-type")
fi
if ! has_flag --target-folder "$@"; then
  missing+=("--target-folder")
fi
if ! has_flag --template "$@"; then
  missing+=("--template")
fi

if [[ ${#missing[@]} -gt 0 ]]; then
  for flag in "${missing[@]}"; do
    echo "Missing required argument: $flag" >&2
  done
  echo "" >&2
  print_usage >&2
  exit 2
fi

default_result_dir="$SCRIPT_DIR/str_and_key_check_results"
if ! has_flag --result-folder "$@"; then
  set -- "$@" --result-folder "$default_result_dir"
fi

if [[ ! -f "$PY_SCRIPT" ]]; then
  echo "ERROR: not found: $PY_SCRIPT" >&2
  exit 1
fi

exec python3 "$PY_SCRIPT" "$@"
