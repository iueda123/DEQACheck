# DE_v14 human JSON から空の disorders 要素を除去する計画

## 背景
- 対象は `share_package/data/{AuthorYear}/DE_v14/json/DE_v14_{AuthorYear}_by_human_*.json`。
- `disorders` 配列の中に、`disorder-name.answer == ""` の空要素が混入しているファイルがある。
- 事前確認では対象 87 ファイル中 54 ファイルが dict 形式の `disorders` 要素を持ち、そのうち 50 ファイルで空要素が 1 件ずつ見つかった。

## 目的
- `disorders` 配列から、実質的に未入力の要素である `disorder-name.answer == ""` の要素だけを除去する。
- それ以外の要素、ファイル名、JSON 構造、既存データは変更しない。

## 対象と非対象
### 対象
- `share_package/data/*/DE_v14/json/DE_v14_*_by_human_*.json`
- `disorders` 配列内の dict 要素
- 条件: `item["disorder-name"]["answer"] == ""`

### 非対象
- `by_codex`、`by_sonnet` など human 以外の JSON
- `DE_v14` 以外の版
- `disorders` 以外のフィールド
- `disorder-name` が文字列で格納されている既存フォーマット

## 現状観察
- フォーマットは混在している。
- 例1: `share_package/data/Chan2025A/DE_v14/json/DE_v14_Chan2025A_by_human_20260312150942.json` では `disorder-name` が文字列 `"ASD"`。
- 例2: `share_package/data/Geng2025/DE_v14/json/DE_v14_Geng2025_by_human_20260312160929.json` では `disorder-name` が dict で、空要素は以下の形。

```json
{
  "disorder-name": {
    "answer": "",
    "detail": "",
    "confidence_rating": "",
    "supporting_text": "",
    "location": ""
  },
  "dataset-of-origin": {
    "answer": [],
    "detail": "",
    "confidence_rating": "",
    "supporting_text": "",
    "location": ""
  },
  "age": {
    "answer": "",
    "detail": "",
    "confidence_rating": "",
    "supporting_text": "",
    "location": ""
  },
  "sex": {
    "answer": "",
    "detail": "",
    "confidence_rating": "",
    "supporting_text": "",
    "location": ""
  }
}
```

## 実施方針
1. 対象ファイル一覧を列挙する。
2. 各 JSON を読み込み、`disorders` 配列のみを走査する。
3. 各要素について次の条件で保持/除去を判定する。
   - `dict` でない要素は保持
   - `disorder-name` が `dict` でない要素は保持
   - `disorder-name.answer == ""` の要素だけ除去
4. 除去が発生したファイルだけを上書き保存する。
5. 実行後に件数確認を行い、空要素が 0 件になったことを確認する。

## 実装案
- 小さなワンオフスクリプト、または既存メンテナンス用スクリプトを 1 本用意して処理する。
- 文字コードは UTF-8、JSON 整形は既存ファイルの読みやすさを大きく崩さない設定にする。
- 書き戻し対象は内容が変化したファイルだけに限定する。

## 確認項目
- 実行前後で `disorder-name.answer == ""` の要素数が `50 -> 0` になること。
- 除去対象がないファイルは未変更であること。
- `disorder-name` が文字列のファイルに副作用がないこと。
- JSON として再読込できること。

## 想定コマンド
実装時は概ね次の確認を行う。

```bash
python <script>
python - <<'PY'
import json
from pathlib import Path
count = 0
for p in Path("share_package/data").glob("*/DE_v14/json/DE_v14_*_by_human_*.json"):
    data = json.loads(p.read_text())
    for item in data.get("disorders", []):
        if isinstance(item, dict):
            dn = item.get("disorder-name")
            if isinstance(dn, dict) and dn.get("answer") == "":
                count += 1
print(count)
PY
```

## リスクと注意点
- `DE_v14` human JSON は同一スキーマではないため、`disorder-name` を常に dict と決め打ちすると既存データを壊す。
- 空文字判定を広げすぎると、本来残すべき partially filled 要素まで消す可能性がある。
- ユーザー作業中の差分が多いリポジトリなので、変更対象は今回のファイルに限定して扱う。

## 完了条件
- 計画に沿った実装が行われ、対象全ファイルで `disorder-name.answer == ""` の要素が除去されている。
- 検証結果を残し、変更ファイルが意図した範囲に収まっていることを確認できる。
