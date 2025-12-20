#!/usr/bin/env bash

# Sync Markdown and JSON files under a target directory using:
# - 06_QualityAssessment/tools/md_to_json_v4.py
# - 06_QualityAssessment/tools/json_to_md_v4.py
#
# Usage:
#   bash 06_QualityAssessment/tools/sync_md_json.sh <target_dir> [--overwrite]
#
# Behavior:
# - Looks for "md" and "json" subdirectories under <target_dir>.
# - Converts every *.md in ./md to ./json/<basename>.json.
# - Converts every *.json in ./json to ./md/<basename>.md.
# - Skips conversions where the destination already exists unless --overwrite is provided.

set -u

if [[ $# -lt 1 ]]; then
  echo "Usage: $0 <target_dir> [--overwrite]" >&2
  exit 2
fi

TARGET_DIR="$1"
OVERWRITE="false"
if [[ ${2:-} == "--overwrite" ]]; then
  OVERWRITE="true"
fi

# Resolve script directory to locate the Python tools reliably
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
MD2JSON_PY="$SCRIPT_DIR/md_to_json_v4.py"
JSON2MD_PY="$SCRIPT_DIR/json_to_md_v4.py"

if [[ ! -f "$MD2JSON_PY" ]] || [[ ! -f "$JSON2MD_PY" ]]; then
  echo "Error: Required tools not found in $SCRIPT_DIR" >&2
  exit 2
fi

# Set up directories
MD_DIR="$TARGET_DIR/md"
JSON_DIR="$TARGET_DIR/json"

mkdir -p "$MD_DIR" "$JSON_DIR"

echo "[INFO] Target: $TARGET_DIR"
echo "[INFO] MD dir: $MD_DIR"
echo "[INFO] JSON dir: $JSON_DIR"
echo "[INFO] Overwrite: $OVERWRITE"

# Convert MD -> JSON
if compgen -G "$MD_DIR/*.md" > /dev/null; then
  while IFS= read -r -d '' MDFILE; do
    base="$(basename "$MDFILE" .md)"
    out_json="$JSON_DIR/$base.json"
    if [[ -f "$out_json" && "$OVERWRITE" != "true" ]]; then
      echo "[SKIP] $MDFILE -> $out_json (exists)"
      continue
    fi
    echo "[RUN ] MD->JSON: $MDFILE -> $out_json"
    if [[ "$OVERWRITE" == "true" ]]; then
      python3 "$MD2JSON_PY" "$MDFILE" "$out_json" --overwrite || echo "[WARN] Conversion failed: $MDFILE" >&2
    else
      python3 "$MD2JSON_PY" "$MDFILE" "$out_json" || echo "[WARN] Conversion failed: $MDFILE" >&2
    fi
  done < <(find "$MD_DIR" -maxdepth 1 -type f -name "*.md" -print0)
else
  echo "[INFO] No .md files found in $MD_DIR"
fi

# Convert JSON -> MD
if compgen -G "$JSON_DIR/*.json" > /dev/null; then
  while IFS= read -r -d '' JSONFILE; do
    base="$(basename "$JSONFILE" .json)"
    out_md="$MD_DIR/$base.md"
    if [[ -f "$out_md" && "$OVERWRITE" != "true" ]]; then
      echo "[SKIP] $JSONFILE -> $out_md (exists)"
      continue
    fi
    echo "[RUN ] JSON->MD: $JSONFILE -> $out_md"
    if [[ "$OVERWRITE" == "true" ]]; then
      python3 "$JSON2MD_PY" "$JSONFILE" "$out_md" --overwrite || echo "[WARN] Conversion failed: $JSONFILE" >&2
    else
      python3 "$JSON2MD_PY" "$JSONFILE" "$out_md" || echo "[WARN] Conversion failed: $JSONFILE" >&2
    fi
  done < <(find "$JSON_DIR" -maxdepth 1 -type f -name "*.json" -print0)
else
  echo "[INFO] No .json files found in $JSON_DIR"
fi

echo "[DONE] Sync complete."

