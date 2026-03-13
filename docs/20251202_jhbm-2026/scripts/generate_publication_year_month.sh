#!/usr/bin/env bash
# Generate publication year-month list from doi_list.txt using Crossref via
# getPublicationYearAndMonthFromDoi.py.
set -euo pipefail

INPUT_PATH="${1:-JHBM2026/data/across-study/doi_list.txt}"
OUTPUT_PATH="${2:-JHBM2026/data/across-study/publication_year-month_list.txt}"
PY_CLIENT="getPublicationYearAndMonthFromDoi.py"

if [[ ! -f "$INPUT_PATH" ]]; then
  echo "Input not found: $INPUT_PATH" >&2
  exit 1
fi

python3 - "$INPUT_PATH" "$OUTPUT_PATH" "$PY_CLIENT" <<'PY'
import sys
import time
from pathlib import Path

# argv: input_path, output_path, client_path
if len(sys.argv) < 4:
    sys.exit("Usage: script input_path output_path client_path")

input_path = Path(sys.argv[1])
output_path = Path(sys.argv[2])
client_path = Path(sys.argv[3])

# Make client importable
sys.path.insert(0, str(client_path.parent.parent))
from JHBM2026.scripts.getPublicationYearAndMonthFromDoi import CrossrefClient  # noqa: E402

lines = input_path.read_text().splitlines()
if not lines:
    sys.exit("Input DOI list is empty")

rows = [l.split("\t") for l in lines[1:] if l.strip()]

client = CrossrefClient(timeout=20, verbose=False)
results = []
for idx, parts in enumerate(rows, 1):
    doi = parts[2] if len(parts) > 2 else ""
    author = parts[1] if len(parts) > 1 else ""
    if not doi:
        results.append((parts[0] if parts else str(idx), author, doi, ""))
        continue
    meta, err = client.get_publication_year_month(doi)
    if err:
        ym = f"ERROR: {err}"
    else:
        ym = meta.get("formatted_date", "")
    results.append((parts[0] if parts else str(idx), author, doi, ym))
    time.sleep(0.1)  # polite pacing

def clean(s: str) -> str:
    return (s or "").replace("\t", " ").replace("\n", " ").replace("\r", " ").strip()

output_lines = ["No\tAuthorYear\tDOI\tPublicationYearMonth"]
for no, author, doi, ym in results:
    output_lines.append("\t".join([clean(no), clean(author), clean(doi), clean(ym)]))

output_path.write_text("\n".join(output_lines))
print(f"Wrote {len(results)} rows to {output_path}")
PY
