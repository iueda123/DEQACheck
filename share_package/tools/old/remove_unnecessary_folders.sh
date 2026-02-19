#!/usr/bin/env bash
set -euo pipefail

# Remove json_v9/json_v10 DE folders and QA folders under share_package/data/*.

script_dir="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"
package_root="$(cd -- "${script_dir}/.." && pwd)"

shopt -s nullglob

targets=(
  "${package_root}/data"/*/DE_v10/json_v9
  "${package_root}/data"/*/DE_v10/json_v10
  "${package_root}/data"/*/QA
)

if [ ${#targets[@]} -eq 0 ]; then
  echo "No matching directories found."
  exit 0
fi

for dir in "${targets[@]}"; do
  if [ -d "$dir" ]; then
    echo "Removing: ${dir}"
    rm -rf -- "$dir"
  else
    echo "Not found (skipped): ${dir}"
  fi
done

echo "Done."
