[INDEX](../../INDEX.md)

*share_package/data-extraction/SKILL.md*

**━━━━━━━━━━━━━━━━━━━━━━━━**

# SKILL.md — `/prep-guide-for-de-guide`・`/prep-de-guide`・`/gene-de-script` ドラフト

このファイルは3つのスラッシュコマンドのドラフト。
内容が固まったら各スキルを `.claude/skills/{skill-name}/SKILL.md` として配置する。

| ドラフト内容 | 配置先（スラッシュコマンド）                                                    |
|:-------------|:--------------------------------------------------------------------------------|
| SKILL 1      | `.claude/skills/prep-guide-for-de-guide/SKILL.md` → `/prep-guide-for-de-guide` |
| SKILL 2      | `.claude/skills/prep-de-guide/SKILL.md` → `/prep-de-guide`                     |
| SKILL 3      | `.claude/skills/gene-de-script/SKILL.md` → `/gene-de-script`                   |

---

## SKILL 1: `/prep-guide-for-de-guide`

> **配置先**: `.claude/skills/prep-guide-for-de-guide/SKILL.md`

```markdown
あなたは、システマティックレビューの情報抽出ガイド設計を支援する専門家です。
`Guide-for-Guide-vN.md` を共同で育てることが目的です。

## 起動時の手順

### Step 1: 既存ファイルの確認

`share_package/data-extraction/` 内の `Guide-for-Guide-v*.md` を列挙し、
最新バージョンを特定して読み込む。
`Guide-for-Guide-for-Guide.md` も読み込み、6セクション構成の要件を把握する。

### Step 2: 新バージョンの初稿作成

最新版を元に `Guide-for-Guide-v{N+1}.md` の初稿を作成する。
以下の6セクションをすべて含める:

1. レビューの背景・目的
2. 作業状況・データ配置
3. 収集したい情報の概要
4. 収集したい情報の単位
5. 収集したい情報の詳細（フィールドID・内容・備考の表 + Phase分類 + 算出ルール）
6. 出力形式（ADCSL_Styleフィールド定義・locationフォーマット・JSONスキーマ）

初稿にはファイルヘッダーとして以下を付ける:
```
> **derived_from**: `Guide-for-Guide-v{N}.md`
> **対応 DE_Guide**: 未定（`/prep-de-guide` 実行時に決定）
```

### Step 3: 能動的な質問フェーズ

初稿作成後、不足・曖昧な点を重要な順に1問ずつ質問し、
回答を受けたらその場でファイルを更新してから次の質問に進む。

質問のルール:
- 「どちらがよいか」「どれがよいか」は番号付き箇条書きで提示する
- 細かな点は質問せず、おまかせで判断する
- 重要な点のみ質問する

一通り質問が終わったら「注釈ドリブンモードに移行します」と告げる。

### Step 4: 注釈ドリブン改善フェーズ

「注釈に対応して」と依頼されたら、ファイル内の `[^N]` 脚注注釈を読み取り、
注釈を本文に組み込んだうえで脚注マーカー（`[^N]`）と脚注定義（`[^N]: ...`）を両方削除する。

注意:
- フォーマッターが脚注マーカーを再挿入することがある。再挿入されたマーカーも都度削除する
- 注釈の内容は本文に直接組み込む。脚注形式のまま残してはいけない

### Step 5: バージョン管理

抽出タスクが変わる場合（対象文献や収集情報が変わる場合）は、既存ファイルを上書きせず
`Guide-for-Guide-v{N+1}.md` として新ファイルを作成する。

## ADCSL_Style の要件

フィールド `location` は Markdown リンク形式の文字列で記述する:

```
[{FileName}: {Section} / {アンカー名}]({JSONからの相対パス}#{アンカーID})
```

JSON ファイルは `Studies/{AuthorYear}/extracted-info/{番号}/` に置かれるため、
materials ファイルへの相対パスは `../../materials/optimized/{FileName}` となる。

## hc_age の算出ルール

mean と sd の種別を `answer` に括弧付きで付記する:
- `(as reported)`: 論文に直接記載
- `(weighted)`: 加重平均として算出（Σ(Nᵢ×meanᵢ)/ΣNᵢ）
- `(pooled)`: プールドSDとして算出（√(Σ((Nᵢ-1)×sdᵢ²)/(ΣNᵢ-k))）

## dataset_name の正規化

5件超のデータセットは代表例 + 総数で記載。
正規化キーワードは `docs/20260116_keywords-for-dataset-name/20260116_Keywords-for-DatasetName.md` を参照。
```

---

## SKILL 2: `/prep-de-guide`

> **配置先**: `.claude/skills/prep-de-guide/SKILL.md`

```markdown
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
- アンカーIDは `prep-study-materials.sh` が見出しに付与したものを使用

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
```

---

## SKILL 3: `/gene-de-script`

> **配置先**: `.claude/skills/gene-de-script/SKILL.md`

```markdown
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
- AI を ワークスペース内から実行し、結果 JSON をワークスペース直下に書き出させる
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
```

**━━━━━━━━━━━━━━━━━━━━━━━━**

*share_package/data-extraction/SKILL.md*

[INDEX](../../INDEX.md)
