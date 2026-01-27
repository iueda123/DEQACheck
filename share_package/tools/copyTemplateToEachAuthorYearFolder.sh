#!/usr/bin/env bash
set -euo pipefail

# This script copies a template JSON file to each AuthorYear folder.
# Target path: {data-folder}/{AuthorYear}/{name}/json/{name}_{AuthorYear}_by_human_{timestamp}.json
#
# Usage:
#   ./copyTemplateToEachAuthorYearFolder.sh --template <template.json> --data-folder <folder> --name <name>
#
# Options:
#   --template      Path to the template JSON file
#   --data-folder   Path to the data folder containing AuthorYear folders
#   --name, -n      Name of the subfolder (e.g., DE_v11, DE_v12)
#   --overwrite, -o Allow adding files even if JSON files already exist (default: skip)
#   --run, -r       Actually run the copy (default: dry-run mode)
#   --help, -h      Show this help message
#
# Note: Relative paths are resolved from the current working directory.

# Function to show usage
show_usage() {
    cat <<EOF
Usage: $(basename "$0") --template <template.json> --data-folder <folder> --name <name> [--run]

Options:
  --template        Path to the template JSON file (required)
  --data-folder     Path to the data folder containing AuthorYear folders (required)
  --name, -n        Name of the subfolder, e.g., DE_v11, DE_v12 (required)
  --overwrite, -o   Allow adding files even if JSON files already exist (default: skip existing)
  --run, -r         Actually run the copy (default: dry-run mode)
  --help, -h        Show this help message

Note: Relative paths are resolved from the current working directory.
      By default, this script runs in dry-run mode and skips folders with existing JSON files.
      Use --run to actually copy files, and --overwrite to add files even if JSON already exists.

Example:
  # Dry-run (default) - shows what would be done, skips existing
  $(basename "$0") --template templates/DE_v12_template.json --data-folder data -n DE_v12

  # Actually copy files (skips folders with existing JSON)
  $(basename "$0") --template templates/DE_v12_template.json --data-folder data -n DE_v12 --run

  # Actually copy files (including folders with existing JSON)
  $(basename "$0") --template templates/DE_v12_template.json --data-folder data -n DE_v12 --run --overwrite
EOF
}

# Function to resolve path (relative to current working directory)
resolve_path() {
    local path="$1"
    if [[ "$path" = /* ]]; then
        # Absolute path
        echo "$path"
    else
        # Relative path - resolve from current working directory
        echo "$(cd "$(dirname "$path")" && pwd)/$(basename "$path")"
    fi
}

# Parse arguments
template_file=""
data_folder=""
name=""
skip_existing=true   # Default: skip folders with existing JSON
dry_run=true         # Default: dry-run mode

while [[ $# -gt 0 ]]; do
    case "$1" in
        --template)
            template_file="$2"
            shift 2
            ;;
        --data-folder)
            data_folder="$2"
            shift 2
            ;;
        --name|-n)
            name="$2"
            shift 2
            ;;
        --overwrite|-o)
            skip_existing=false
            shift
            ;;
        --run|-r)
            dry_run=false
            shift
            ;;
        --help|-h)
            show_usage
            exit 0
            ;;
        *)
            echo "[ERROR] Unknown option: $1" >&2
            show_usage
            exit 1
            ;;
    esac
done

# Validate required arguments
if [[ -z "$template_file" ]]; then
    echo "[ERROR] --template is required" >&2
    show_usage
    exit 1
fi

if [[ -z "$data_folder" ]]; then
    echo "[ERROR] --data-folder is required" >&2
    show_usage
    exit 1
fi

if [[ -z "$name" ]]; then
    echo "[ERROR] --name (-n) is required" >&2
    show_usage
    exit 1
fi

# Resolve paths
template_file="$(resolve_path "$template_file")"
data_folder="$(resolve_path "$data_folder")"

# Validate template file exists
if [[ ! -f "$template_file" ]]; then
    echo "[ERROR] Template file not found: $template_file" >&2
    exit 1
fi

# Validate data folder exists
if [[ ! -d "$data_folder" ]]; then
    echo "[ERROR] Data folder not found: $data_folder" >&2
    exit 1
fi

echo "Template: $template_file"
echo "Data folder: $data_folder"
echo "Name: $name"
echo "Skip existing: $skip_existing"
echo "Dry run: $dry_run"
if [[ "$dry_run" == true ]]; then
    echo "[MODE] DRY-RUN - no files will be copied"
fi
echo "---"

shopt -s nullglob

created_count=0
skipped_count=0
error_count=0

# Iterate over AuthorYear folders (folders starting with uppercase letter)
for author_dir in "$data_folder"/[A-Z]*/; do
    [[ -d "$author_dir" ]] || continue

    author_name="$(basename "$author_dir")"
    target_dir="$author_dir/$name/json"

    # Check if target directory should be created
    if [[ ! -d "$target_dir" ]]; then
        if [[ "$dry_run" == true ]]; then
            echo "[DRY-RUN] Would create directory: $target_dir"
        else
            mkdir -p "$target_dir"
        fi
    fi

    # If skip_existing is true and by_human JSON files exist, skip this author
    if [[ "$skip_existing" == true ]]; then
        existing_json=("$target_dir"/${name}_${author_name}_by_human_*.json)
        if (( ${#existing_json[@]} > 0 )); then
            echo "[SKIP] $author_name: existing by_human JSON found in $target_dir"
            skipped_count=$((skipped_count + 1))
            continue
        fi
    fi

    # Build filename with current timestamp
    ts="$(date +%Y%m%d%H%M%S)"
    out_file="$target_dir/${name}_${author_name}_by_human_${ts}.json"

    if [[ "$dry_run" == true ]]; then
        echo "[DRY-RUN] Would create: $out_file"
    else
        cp "$template_file" "$out_file"
        echo "[OK] Created: $out_file"
    fi
    created_count=$((created_count + 1))
done

echo "---"
if [[ "$dry_run" == true ]]; then
    echo "Dry run completed. Would create: $created_count, Skipped: $skipped_count"
else
    echo "Done. Created: $created_count, Skipped: $skipped_count"
fi
