#!/bin/bash
# copy_codex_to_human_DE_v13.sh
#
# share_package/data/{AuthorYear}/DE_v13/json/ に by_human_*.json がない場合、
# 同階層の最新の by_codex_*.json を複製して by_human_{timestamp}.json を作成する。

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
DATA_DIR="$(cd "$SCRIPT_DIR/../data" && pwd)"

copied=0
skipped=0
no_codex=0

for json_dir in "$DATA_DIR"/*/DE_v13/json; do
    [[ -d "$json_dir" ]] || continue

    author_year=$(echo "$json_dir" | awk -F'/' '{print $(NF-2)}')

    # by_human_*.json が既に存在するか確認
    if ls "$json_dir"/DE_v13_"${author_year}"_by_human_*.json &>/dev/null 2>&1; then
        echo "[SKIP]   $author_year — human ファイルが既に存在します"
        ((skipped++))
        continue
    fi

    # 最新の by_codex_*.json を取得（タイムスタンプ降順の先頭）
    codex_file=$(ls -t "$json_dir"/DE_v13_"${author_year}"_by_codex_*.json 2>/dev/null | head -1)

    if [[ -z "$codex_file" ]]; then
        echo "[WARN]   $author_year — codex ファイルが見つかりません (スキップ)"
        ((no_codex++))
        continue
    fi

    timestamp=$(date +"%Y%m%d%H%M%S")
    dest="$json_dir/DE_v13_${author_year}_by_human_${timestamp}.json"

    cp "$codex_file" "$dest"
    echo "[COPIED] $author_year"
    echo "         src : $(basename "$codex_file")"
    echo "         dst : $(basename "$dest")"
    ((copied++))
done

echo ""
echo "=== 完了 ==="
echo "  コピー済み : $copied"
echo "  スキップ   : $skipped (human ファイルあり)"
echo "  codex なし : $no_codex"