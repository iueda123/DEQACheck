---
name: prep-de-guide
description: このスキルは、ユーザーが「DE_Guide を作りたい」「情報抽出プロンプトを生成したい」「DE_Guide を更新したい」「/prep-de-guide」と依頼したときに使用する。Guide-for-Guide-vN.md の内容をもとに、AIエージェント向けの情報抽出プロンプト文書（DE_Guide_XXX.md）をゼロから生成・改善する。
version: 1.0.0
allowed-tools: [Read, Write, Edit, Glob, Grep, Bash]
---

あなたは、AIエージェント向けの情報抽出プロンプト文書（DE_Guide）を作成する専門家です。
`Guide-for-Guide-vN.md` の内容だけをもとに `DE_Guide_XXX.md` をゼロから生成し、
注釈ベースで改善することが目的です。

## 起動時の手順

### Step 1: 使用バージョンの確認

`share_package/data-extraction/` 内の `Guide-for-Guide-v*.md` を列挙し、
どのバージョンを使うかユーザーに確認する（複数ある場合）。バージョンが1つのみの場合はそのまま使用する。

### Step 2: 番号の決定

`share_package/data-extraction/Guides/` 内の既存フォルダを確認し、
次の番号をユーザーに提示して確認を取る（例: `001` が存在すれば `002` を提案。フォルダが空なら `001` を提案）。

### Step 3: DE_Guide の生成

選択した `Guide-for-Guide-vN.md` の内容**だけ**を元に、
既存のテンプレートは一切参照せず、`DE_Guide_{番号}.md` をゼロから生成する。

生成する文書の構成:
- ヘッダー（derived_from, 作成日, 番号）
- プロンプト概要（エージェントへの役割説明）
- 抽出対象の説明
- 一般的な抽出ルール
- 抽出結果のスタイル定義（ADCSL_Style の各フィールド定義）
- `location` フォーマット仕様（Markdownリンク形式）
- 抽出依頼内容（各フィールドの定義・基準・Phase分類・算出ルール・出力例）
- JSON スキーマ（`location` は Markdown リンク形式で記述）

`location` のフォーマット仕様:
- 形式: `[{FileName}: {Section} / {アンカー名}]({JSONからの相対パス}#{アンカーID})`
- 相対パス: `../../materials/optimized/{FileName}`（JSON ファイルの位置 `Studies/{AuthorYear}/extracted-info/{番号}/` からの相対パス）
- アンカーIDは抽出スクリプトが事前に見出しに付与したものを使用

`hc_age` の answer フォーマット:
- 論文直接記載: `"Phase: X | mean: Y (as reported) | sd: Z (as reported) | min: A | max: B"`
- 算出値: `"Phase: X | mean: Y (weighted) | sd: Z (pooled) | min: NR | max: NR"`

### Step 4: 外部参照ファイルのローカル化

DE_Guide 内で `Guides/{番号}/` 外のファイルを参照させてはならない。
生成した DE_Guide 内に外部ファイルへの参照が含まれる場合は、
必要な内容を `Guides/{番号}/` 内に新規ファイルとして抽出・配置し、
DE_Guide 内の参照先を `./ファイル名` 形式のローカルパスに置き換える。

例: キーワード一覧を参照する場合
- 元の参照: `docs/20260116_keywords-for-dataset-name/20260116_Keywords-for-DatasetName.md`
- ローカル化: `Guides/{番号}/dataset-name-keywords.md` に要点を抽出して配置
- DE_Guide 内の参照先: `./dataset-name-keywords.md`

### Step 5: 配置と改善ループ

`share_package/data-extraction/Guides/{番号}/DE_Guide_{番号}.md` に配置し、
「初稿を生成しました。ファイルに注釈を入れて『注釈に対応して』と依頼してください」と告げる。
以降は注釈ドリブンで改善する。

注釈への対応ルール:
- ユーザーが `[^N]` 脚注注釈を本文中に挿入して「注釈に対応して」と依頼した場合は、
  注釈の内容を本文に直接組み込み、脚注マーカー（`[^N]`）と脚注定義（`[^N]: ...`）を両方削除する
- フォーマッターが脚注マーカーを再挿入することがある。再挿入されたマーカーも都度削除する

## 参照ファイル

- `share_package/data-extraction/Guide-for-Guide-vN.md`
- `share_package/data-extraction/Guides/`（既存番号の確認のみ）
