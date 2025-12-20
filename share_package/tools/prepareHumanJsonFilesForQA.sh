#!/usr/bin/env bash
set -euo pipefail

# This script copies the QA template JSON to each author folder under
# share_package/data/<AuthorXXXX>/QA/json/
# The output filename format is:
#   QA_<AuthorXXXX>_by_human_2025mmddHHMMSS.json
# If any existing .json file is already present in the target QA/json directory,
# it skips that author without making changes.

# Resolve important paths relative to this script
this_script="${BASH_SOURCE[0]}"
this_script_parent="$(cd "$(dirname "$this_script")" && pwd)"
repo_root="$(cd "$this_script_parent/../.." && pwd)"

template_file="$repo_root/share_package/templates/QA_Author20XX_by_Someone_YYYYmmddHHMMSS_for_v7.json"
data_root="$repo_root/share_package/data"

if [[ ! -f "$template_file" ]]; then
  echo "[ERROR] Template not found: $template_file" >&2
  exit 1
fi

if [[ ! -d "$data_root" ]]; then
  echo "[ERROR] Data root not found: $data_root" >&2
  exit 1
fi

shopt -s nullglob

created_count=0
skipped_count=0

for author_dir in "$data_root"/*/; do
  [[ -d "$author_dir" ]] || continue

  author_name="$(basename "$author_dir")"
  target_dir="$author_dir/QA/json"

  # Ensure target directory exists (for consistent checks and future use)
  mkdir -p "$target_dir"

  # If any existing JSON is present, skip this author
  existing_json=("$target_dir"/*.json)
  if (( ${#existing_json[@]} > 0 )); then
    echo "[SKIP] $author_name: existing JSON found in $target_dir"
    skipped_count=$((skipped_count + 1))
    continue
  fi

  # Build filename with fixed-year prefix '2025' + current mmddHHMMSS
  ts="2025$(date +%m%d%H%M%S)"
  out_file="$target_dir/QA_${author_name}_by_human_${ts}.json"

  cp "$template_file" "$out_file"
  echo "[OK] Created: $out_file"
  created_count=$((created_count + 1))
done

echo "---"
echo "Done. Created: $created_count, Skipped: $skipped_count"
