#!/usr/bin/env bash
set -euo pipefail

table_path="doc/2026.02.19_Keywords-for-Disorder-Name.md"

extract_cols() {
  awk -F'\\|' '
    $1 ~ /^\\|/ && $2 ~ /AuthorYear/ {in_table=1; next}
    in_table && $0 ~ /^\\|[- ]+\\|/ {next}
    in_table && $1 ~ /^\\|/ {
      gsub(/^[ \t]+|[ \t]+$/, "", $3)
      gsub(/^[ \t]+|[ \t]+$/, "", $4)
      print $3 \"\\t\" $4
    }
  ' \"$table_path\"
}

if command -v pbcopy >/dev/null 2>&1; then
  extract_cols | pbcopy
elif command -v xclip >/dev/null 2>&1; then
  extract_cols | xclip -selection clipboard
elif command -v xsel >/dev/null 2>&1; then
  extract_cols | xsel --clipboard --input
else
  echo "No clipboard tool found (pbcopy/xclip/xsel)." >&2
  exit 1
fi
