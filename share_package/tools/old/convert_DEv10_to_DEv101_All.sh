#!/usr/bin/env bash
set -euo pipefail

# Batch-convert DE v10 JSON files to v10_1 across author folders.
# - Uses 09_DataExtractionPrep3/tools/convert_DEv10_to_DEv101.py
# - Keeps the converted file at the same path (overwrites in place)
# - Moves the pre-conversion backup (*_v10.json) into ../json_v10/
#
# Usage:
#   bash 09_DataExtractionPrep3/tools/convert_DEv10_to_DEv101_All.sh [--run|-r]
#
# Options:
#   -r, --run       Perform actual conversion and moves (default is dry-run)
#   -n, --dry-run   Show what would be done without making changes (default)

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
DRY_RUN=1

while [[ $# -gt 0 ]]; do
  case "$1" in
    -r|--run)
      DRY_RUN=0
      shift
      ;;
    -n|--dry-run)
      DRY_RUN=1
      shift
      ;;
    -h|--help)
      sed -n '1,200p' "$0" | sed -n '/^# Usage:/,/^$/p' | sed 's/^# \{0,1\}//'
      exit 0
      ;;
    *)
      echo "Unknown option: $1" >&2
      exit 2
      ;;
  esac
done
CONVERTER_REL="09_DataExtractionPrep3/tools/convert_DEv10_to_DEv101.py"

# Resolve converter path relative to repo root
REPO_ROOT="$(cd "$SCRIPT_DIR/../.." && pwd)"
CONVERTER="$REPO_ROOT/$CONVERTER_REL"

if ! command -v python3 >/dev/null 2>&1; then
  echo "Error: python3 not found in PATH" >&2
  exit 1
fi

if [ ! -f "$CONVERTER" ]; then
  echo "Error: converter not found at $CONVERTER" >&2
  exit 1
fi

shopt -s nullglob

# Find all JSONs under 09_DataExtractionPrep3/<AuthorYear>/DE/json/*.json
mapfile -t JSON_FILES < <(find "$REPO_ROOT/09_DataExtractionPrep3" -type f -path '*/DE/json/*.json' | sort)

if [ ${#JSON_FILES[@]} -eq 0 ]; then
  echo "No JSON files found under 09_DataExtractionPrep3/*/DE/json/. Nothing to do."
  exit 0
fi

echo "Found ${#JSON_FILES[@]} JSON files to convert."

for json in "${JSON_FILES[@]}"; do
  echo "Converting: $json"

  # Predict backup path (must match converter logic)
  base_no_ext="${json%.json}"
  backup_candidate="${base_no_ext}_v10.json"
  if [ -e "$backup_candidate" ]; then
    i=1
    while :; do
      backup_candidate="${base_no_ext}_v10_${i}.json"
      if [ ! -e "$backup_candidate" ]; then
        break
      fi
      i=$((i+1))
    done
  fi

  # Run conversion (in-place overwrite; creates backup at backup_candidate)
  if [[ $DRY_RUN -eq 1 ]]; then
    echo "  DRY-RUN: python3 $CONVERTER $json"
  else
    if ! python3 "$CONVERTER" "$json"; then
      echo "  Error: conversion failed for $json; continuing to next file." >&2
      continue
    fi
  fi

  # Move backup to ../json_v10/ and rename to drop _v10 suffix
  json_dir="$(dirname "$json")"           # .../DE/json
  dest_dir="$(cd "$json_dir/.." && pwd)/json_v10"
  orig_name="$(basename "$json")"         # e.g., foo.json (desired name in json_v10)
  target_path="$dest_dir/$orig_name"

  if [[ $DRY_RUN -eq 1 ]]; then
    echo "  DRY-RUN: mkdir -p $dest_dir"
    echo "  DRY-RUN: mv $backup_candidate $dest_dir/"
    echo "  DRY-RUN: rename $(basename "$backup_candidate") -> $(basename "$target_path")"
  else
    mkdir -p "$dest_dir"
    if [ -f "$backup_candidate" ]; then
      # Move
      mv "$backup_candidate" "$dest_dir/"
      moved_path="$dest_dir/$(basename "$backup_candidate")"
      # Determine non-conflicting target name based on original json name
      rename_to="$target_path"
      if [ -e "$rename_to" ]; then
        i=1
        while :; do
          candidate="$dest_dir/${orig_name%.json}_$i.json"
          if [ ! -e "$candidate" ]; then
            rename_to="$candidate"
            break
          fi
          i=$((i+1))
        done
      fi
      mv "$moved_path" "$rename_to"
      echo "  Moved backup -> $rename_to"
    else
      echo "  Warning: Expected backup not found: $backup_candidate" >&2
    fi
  fi
done

echo "All done. Converted ${#JSON_FILES[@]} files."
