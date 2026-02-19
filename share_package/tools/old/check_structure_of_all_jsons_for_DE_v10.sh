#!/usr/bin/env bash
# Run the template checker on every JSON under <Project>/<UppercaseDir>/DE_v10/json/
set -uo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
CHECKER="$ROOT_DIR/tools/check_json_template_structure_for_DE_v10.py"
RESULT_FILE="$ROOT_DIR/json_structure_check_results.txt"

if [[ ! -x "$CHECKER" ]]; then
  echo "Template checker not found or not executable: $CHECKER" >&2
  exit 1
fi

: > "$RESULT_FILE"
shopt -s nullglob

while IFS= read -r -d '' dir; do
  base="$(basename "$dir")"
  [[ $base =~ ^[A-Z] ]] || continue
  json_dir="$dir/DE_v10/json"
  [[ -d "$json_dir" ]] || continue
  while IFS= read -r -d '' json_file; do
    output="$($CHECKER "$json_file" 2>&1)"
    cmd_status=$?
    status=$(printf '%s\n' "$output" | tail -n 1)
    if [[ $status != "true" && $status != "false" ]] || [[ $cmd_status -ne 0 ]]; then
      status="false"
    fi
    printf '%s\t%s\n' "${json_file#$ROOT_DIR/}" "$status" >> "$RESULT_FILE"
    if [[ $status == "false" ]]; then
      printf 'check failed for %s\n' "$json_file" >&2
      printf '%s\n' "$output" >&2
    fi
  done < <(find "$json_dir" -maxdepth 1 -type f -name '*.json' -print0 | sort -z)
done < <(find "$ROOT_DIR" -mindepth 1 -maxdepth 1 -type d -print0 | sort -z)

printf 'Wrote results to %s\n' "$RESULT_FILE"
