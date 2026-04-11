---
name: gene-de-script
description: このスキルは、ユーザーが「情報抽出スクリプトを作りたい」「DEスクリプトを生成したい」「ask スクリプトを作りたい」「/gene-de-script」と依頼したときに使用する。完成した DE_Guide_{番号}.md を元に、実行するだけでJSONが生成されるbashスクリプトを1文献×1エージェント×1モデルの組み合わせごとに生成する。
version: 1.0.0
allowed-tools: [Read, Write, Edit, Glob, Grep, Bash]
---

あなたは、情報抽出用bashスクリプトを生成する専門家です。
完成した `DE_Guide_{番号}.md` を元に、実行するだけでJSONが生成されるbashスクリプトを
1文献 × 1エージェント × 1モデルの組み合わせごとに生成することが目的です。

## 起動時の対話手順

以下を順番に確認し、すべての回答を得てからスクリプト生成に進む。

### Step 1: 使用する DE_Guide の選択

`share_package/data-extraction/Guides/` 配下のフォルダを番号付きで提示する。
フォルダが1つだけの場合はそのまま使用する。

### Step 2: 対象文献の選択

`share_package/data-extraction/Studies/` 配下の `{AuthorYear}` フォルダを番号付きで提示する。
`Studies/` が存在しない、またはフォルダが空の場合は `share_package/data/` 配下のフォルダ一覧を代わりに使用する
（`Someone` と `settings` は除外）。
複数選択可（例: "1,3,5" または "all"）。文献名の直接入力も受け付ける。

### Step 3: 使用エージェントの選択

`/media/iu/STORAGE/__GitHub__/wiki-concierge/main/src/main/resources/llm-provider-settings.json`
の `cli` セクションを実際に読み込み、キー名をエージェント選択肢として番号付きで提示する。

### Step 4: 使用モデルの選択

Step 3 で選択したエージェントの `models` 配列を同ファイルから読み込み、番号付きで提示する。

### Step 5: 確認

選択内容（DE_Guide番号・対象文献・エージェント・モデル）と生成されるファイル名を一覧表示し、
ユーザーの確認を得てからスクリプト生成に進む。

## スクリプト生成

確認後、1文献 × 1エージェント × 1モデルの組み合わせごとに **1本の統合スクリプト** を生成し
`share_package/data-extraction/Guides/{番号}/` に配置する。

### `ask-{agent}-to-de-from-{AuthorYear}-using-{model}.sh`（統合スクリプト）

前処理（アンカー付与）と情報抽出（AI 呼び出し）を1本にまとめる。
デフォルトはドライランとし、`--run` オプション指定時のみ AI を実際に呼び出す。

**内包する処理 1: 材料の前処理**
- `share_package/data/{AuthorYear}/materials/optimized/*.md` を
  `share_package/data-extraction/Studies/{AuthorYear}/materials/optimized/` にコピー
- 各 Markdown 見出しに `{#anchor-id}` 形式のアンカーを自動付与（awk で処理）
  （例: `## Methods` → `## Methods {#methods}`、`## Table 1` → `## Table 1 {#table-1}`）
- アンカーID: 見出しテキストを lowercase・空白→ハイフン・英数字とハイフン以外を除去

**内包する処理 2: 情報抽出**
- `share_package/tools/ask_AiToDe_v14_2.sh` の構造を参考にする
- ワークスペース: `share_package/ai_workspace/{agent}_de{番号}_{timestamp}/`
  - `DE_Guide_{番号}.md` および同フォルダ内の関連 `.md` ファイル（キーワードファイル等）をコピー
  - 前処理済み材料を `study_1/` サブフォルダにコピー
- AI をワークスペース内から実行し、結果 JSON をワークスペース直下に書き出させる
- 実行後、結果ファイルを出力先にコピーしてワークスペースを削除する

**出力先**:
- JSON: `Studies/{AuthorYear}/extracted-info/{番号}/{AuthorYear}_by-{agent}-{model}_{timestamp}.json`
- ログ: 同フォルダ（`.log` ファイル）
- タイムスタンプ形式: `%Y%m%d-%H%M%S`（例: `20260411-224510`）
- 出力ディレクトリが存在しない場合は自動作成

**その他の挙動**:
- 既存の結果ファイルが存在する場合は続行確認を求める
- 材料フォルダが存在しない場合はエラーを表示して終了

## 参照ファイル

- `share_package/data-extraction/Guides/{番号}/DE_Guide_{番号}.md`
- `share_package/tools/ask_AiToDe_v14_2.sh`（スクリプト構造の参考）
- `/media/iu/STORAGE/__GitHub__/wiki-concierge/main/src/main/resources/llm-provider-settings.json`（`cli` セクション）
