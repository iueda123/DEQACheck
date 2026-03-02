#!/usr/bin/env bash
set -euo pipefail

root="share_package/data"
out="doc/NotebookLM_list.md"

python - <<'PY'
import json
from pathlib import Path

root = Path('share_package/data')
items = []
for path in root.glob('*/notes/WithNotebookLM.json'):
    author_year = path.parts[-3]
    try:
        data = json.loads(path.read_text(encoding='utf-8'))
    except Exception:
        data = None
    url = None
    if isinstance(data, dict):
        url = data.get('notebook-lm-url')
    if url:
        items.append((author_year, url))

items.sort(key=lambda x: x[0].lower())

out = Path('doc/NotebookLM_list.md')
lines = ['# NotebookLM URL list', '']
for author_year, url in items:
    lines.append(f'- {author_year}: {url}')
lines.append('')
out.write_text('\n'.join(lines), encoding='utf-8')

print(f'Wrote {out} with {len(items)} entries')
PY
