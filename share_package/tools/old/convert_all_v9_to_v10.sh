#!/usr/bin/env bash
set -Eeuo pipefail

# Batch-convert DE template JSONs from v9 to v10.
# - Input root: first argument (base directory)
# - Searches: For each direct child folder whose name starts with an uppercase letter (A-Z),
#             looks for DE/json/*.json and converts them.
# - Backup:   Copies pre-conversion files to the sibling DE/json_v9/ (same filename), no overwrite.
# - Convert:  Runs tools/convert_template_v9_to_v10.py in-place (output overwrites original).

usage() {
  cat >&2 <<'USAGE'
Batch-convert DE template JSONs from v9 to v10.

Usage:
  tools/convert_all_v9_to_v10.sh <base_dir>
  tools/convert_all_v9_to_v10.sh -h | --help

Arguments:
  <base_dir>   Directory that contains subfolders like Baldwin2022, Bayer2022, ...

Behavior:
  - Scans only direct subfolders of <base_dir> whose names start with an uppercase letter (A-Z).
  - For each, looks for DE/json/*.json and converts them to v10 in place.
  - Backs up originals to DE/json_v9/ (same filename) if not already backed up.
  - If conversion fails, attempts to restore the original from backup.

Examples:
  bash tools/convert_all_v9_to_v10.sh .
  bash tools/convert_all_v9_to_v10.sh /path/to/parent
USAGE
}

if [[ ${1:-} == "-h" || ${1:-} == "--help" ]]; then
  usage
  exit 0
fi

if [[ ${1:-} == "" ]]; then
  usage
  exit 2
fi

BASE_DIR=$(cd -- "$1" 2>/dev/null && pwd || true)
if [[ -z "${BASE_DIR}" || ! -d "${BASE_DIR}" ]]; then
  echo "[error] Base directory not found: $1" >&2
  exit 2
fi

SCRIPT_DIR=$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)
REPO_ROOT=$(cd -- "${SCRIPT_DIR}/.." && pwd)
CONVERTER="${REPO_ROOT}/tools/convert_template_v9_to_v10.py"

if [[ ! -f "${CONVERTER}" ]]; then
  echo "[error] Converter not found: ${CONVERTER}" >&2
  exit 1
fi

shopt -s nullglob

converted=0
failed=0
found_any=false

echo "[info] Base: ${BASE_DIR}"
echo "[info] Scanning uppercase-leading subfolders for DE/json/*.json"

for dir in "${BASE_DIR}"/*/; do
  [[ -e "$dir" ]] || continue
  name=$(basename -- "$dir")
  # Only process names starting with an uppercase letter
  if [[ ! "$name" =~ ^[A-Z].* ]]; then
    continue
  fi

  json_dir="${dir%/}/DE/json"
  for json in "$json_dir"/*.json; do
    [[ -e "$json" ]] || continue
    found_any=true

    relpath="${json#${BASE_DIR%/}/}"
    de_dir=$(dirname -- "$json_dir")          # .../<Name>/DE
    backup_dir="${de_dir}/json_v9"             # .../<Name>/DE/json_v9
    filename=$(basename -- "$json")
    backup_file="${backup_dir}/${filename}"

    mkdir -p -- "$backup_dir"

    # Copy original to backup if not already present
    if [[ -e "$backup_file" ]]; then
      echo "[skip] Backup exists, not overwriting: ${relpath} -> ${backup_file#${BASE_DIR%/}/}"
    else
      cp -a -- "$json" "$backup_file"
      echo "[bak ] ${relpath} -> ${backup_file#${BASE_DIR%/}/}"
    fi

    # Run converter in-place (output overwrites original path)
    if python3 "$CONVERTER" -i "$json" -o "$json"; then
      echo "[ok  ] Converted: ${relpath}"
      ((converted++)) || true
    else
      echo "[fail] Conversion failed: ${relpath}" >&2
      ((failed++)) || true
      # Attempt to restore from backup if available
      if [[ -e "$backup_file" ]]; then
        cp -a -- "$backup_file" "$json"
        echo "[rest] Restored original from backup: ${relpath}"
      fi
    fi
  done
done

if [[ "$found_any" = false ]]; then
  echo "[warn] No DE/json/*.json found under uppercase-leading subfolders of: ${BASE_DIR}" >&2
  exit 3
fi

echo "[done] Converted: ${converted}, Failed: ${failed}"

exit 0
