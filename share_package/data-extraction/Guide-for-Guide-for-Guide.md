[INDEX](../../INDEX.md)

*share_package/data-extraction/Guide-for-Guide-for-Guide.md*

**━━━━━━━━━━━━━━━━━━━━━━━━**

# Guide-for-Guide-for-Guide — SKILL `/prep-guide-for-de-guide`・`/prep-de-guide`・`/gene-de-script` 作成のための作業指針

この文書は、情報抽出（Data Extraction）ワークフローを3つのSKILLとして定式化するための作業指針です。
作業の区切りごとに `SKILL.md` に手順を記録し、最終的に `/prep-guide-for-de-guide`・`/prep-de-guide`・`/gene-de-script` として使えるようにします。

---

## 1. 最終目標

3つの SKILL を定義する。

| SKILL                      | 役割                                                                                                                                                        |
|:---------------------------|:------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `/prep-guide-for-de-guide` | `Guide-for-Guide.md` を育てる（抽出設計の共同作業）                                                                                                         |
| `/prep-de-guide`           | `Guide-for-Guide.md` を元に `DE_Guide_XXX.md` を生成し、注釈ベースで繰り返し改善する                                                                        |
| `/gene-de-script`          | 完成した `DE_Guide_XXX.md` を元に、実行するだけで抽出結果JSONが生成される bash スクリプト `ask-{agent}-to-de-from-{AuthorYear}-using-{model}.sh` を生成する |

情報抽出そのものを SKILL で直接行うのではなく、**情報抽出を実行するbashスクリプトを生成するSKILL**を作ることが目標。
最終フェーズのスクリプト実行は人間が行うものであり、SKILL は不要。

対象となる作業の軸は：

> **対象文献** × **収集したい情報** × **使用するAIエージェント／モデル**

---

## 2. ワークフロー（4フェーズ）

### Phase 1 — `Guide-for-Guide.md` の作成（`/prep-guide-for-de-guide` の作業）

`/prep-guide-for-de-guide` 呼び出し時の動作:

1. AIが既存の `Guide-for-Guide.md` を読み込む
2. AIが不足・曖昧な点を能動的に質問し（1問ずつ）、ユーザーの回答を元に文書を更新する
3. 一通り質問が終わったら注釈ドリブンモードへ移行 — ユーザーが注釈を入れて「注釈に対応して」と依頼するループに入る
4. これを繰り返して良質な `Guide-for-Guide.md` を目指す

### Phase 2 — `DE_Guide_XXX.md` の生成・改善（`/prep-de-guide` の作業）

- 対象の `Guide-for-Guide-vN.md` の内容**だけ**を元にゼロから `DE_Guide_XXX.md` を生成する（既存テンプレートは使わない）
- `Guide-for-Guide-vN.md` の番号と `Guides/{番号}/` の番号は**独立**（1つの `vN` から複数の `Guides/{番号}/` が生まれることがある）
- 生成された `DE_Guide_XXX.md` のヘッダーには派生元 `Guide-for-Guide-vN.md` のバージョンを記録し、トレーサビリティを確保する
- 初回生成で良質なものにならない可能性があるため、注釈ベースで繰り返し改善する
- 完成した `DE_Guide_XXX.md` を `Guides/{番号}/` に配置する
- 番号は**情報抽出目的ごと**に変える（同じ目的の改訂は同番号で上書き）

### Phase 3 — bashスクリプトの生成（`/gene-de-script` の作業）

**前準備（自動スクリプトによる materials 加工）:**
- `share_package/data/{AuthorYear}/materials/optimized/*.md` を `Studies/{AuthorYear}/materials/optimized/` へコピーし、アンカーを自動で埋め込む
- この処理は専用の前処理スクリプト（`prep-study-materials.sh`）で一括自動化する
- `/gene-de-script` 実行時にこのスクリプトも生成・実行する

**スクリプト生成:**
- `/gene-de-script` を呼び出すと、AIが対話形式で以下を順に確認する:
  1. 対象文献（`Studies/` 配下の `{AuthorYear}` を列挙して選ばせる。複数選択可）
  2. 使用エージェント（`llm-provider-settings.json` の `cli` キー: `claude` / `codex` / `copilot` / `gemini`）
  3. 使用モデル（選択したエージェントに対応するモデル一覧から選ばせる）
- 選択肢の定義元: `/media/iu/STORAGE/__GitHub__/wiki-concierge/main/src/main/resources/llm-provider-settings.json`（`cli` セクション）
- 確認後、**1文献 × 1エージェント × 1モデルの組み合わせごとに個別の**bashスクリプトを生成する
- 生成されたスクリプトを `DE_Guide_XXX.md` と同じ `Guides/{番号}/` に配置する
- 既存のスクリプト例として `share_package/tools/ask_AiToDe_v14_2.sh` を参考にする

### Phase 4 — bashスクリプトの実行による情報抽出（人間が行う）

- 生成された `ask-{agent}-to-de-from-{AuthorYear}-using-{model}.sh` を人間が実行するだけで JSON が生成される
- 出力ファイル名形式: `{AuthorYear}_by-{agent}-{model}_{yyyymmdd-HHmmss}.json`
- このフェーズは SKILL 不要

---

## 3. やり取りの進め方

| 場面               | 手段                                               |
|:-------------------|:---------------------------------------------------|
| 大まかな指示・確認 | ターミナル（Claude Codeとの対話）                  |
| 詳細な修正・注釈   | Markdownファイル（注釈を本文に書き込んでAIに渡す） |

「注釈に対応して」と依頼された場合、AIは注釈を使わず回答を本文に組み込む（CLAUDE.md の規則に従う）。

---

## 4. ファイル・フォルダ構造

```
share_package/
 +-- data/
 |    +-- {AuthorYear}/
 |         +-- materials/
 |              +-- optimized/
 |                   +-- main.pdf.md   ← コピー元（アンカー加工前の原本）
 |                   +-- sup.pdf.md
 :
 +-- data-extraction/
 |    +-- Guide-for-Guide-for-Guide.md   ← この文書（作業指針）
 |    +-- Guide-for-Guide-v1.md          ← 抽出ガイドの設計方針（Phase 1 成果物、タスクごとに新バージョン）
 |    +-- Guide-for-Guide-v2.md
 |    :
 |    +-- SKILL.md                       ← 3つのSKILLのドラフト（随時更新）
 |    +-- Guides/
 |    |    +-- 001/
 |    |    |    +-- DE_Guide_001.md                                    ← Phase 2 成果物
 |    |    |    +-- prep-study-materials.sh                            ← materials 前処理スクリプト（Phase 3 成果物）
 |    |    |    +-- ask-claude-to-de-from-Ge2024-using-opus46.sh       ← Phase 3 成果物（文献×エージェント×モデル個別）
 |    |    |    +-- ask-claude-to-de-from-Geng2025-using-opus46.sh
 |    |    |    +-- ask-codex-to-de-from-Ge2024-using-gpt51.sh
 |    |    |    :
 |    |    +-- 002/
 |    |    |    +-- DE_Guide_002.md
 |    |    |    +-- prep-study-materials.sh
 |    |    |    +-- ask-{agent}-to-de-from-{AuthorYear}-using-{model}.sh
 |    |    :
 |    +-- Studies/
 |         +-- {AuthorYear}/
 |         |    +-- extracted-info/
 |         |    |    +-- 001/            ← DE_Guide_001 使用時の抽出結果
 |         |    |    |    +-- {AuthorYear}_by-claude-opus46_20260411-224510.json
 |         |    |    |    +-- {AuthorYear}_by-codex-gpt51_20260411-225010.json
 |         |    |    +-- 002/
 |         |    |    :
 |         |    +-- materials/
 |         |         +-- optimized/      ← share_package/data/{AuthorYear}/materials/optimized/
 |         |              |               からコピーし、アンカーを埋め込む加工を行ったもの
 |         |              +-- main.pdf.md
 |         |              +-- sup.pdf.md
 |         +-- {AuthorYear}/
 |         :

.claude/
 +-- commands/
      +-- prep-guide-for-de-guide.md   ← SKILL /prep-guide-for-de-guide の実体
      +-- prep-de-guide.md             ← SKILL /prep-de-guide の実体
      +-- gene-de-script.md         ← SKILL /gene-de-script の実体
```

> **補足**: `SKILL.md`（`data-extraction/` 内）はドラフトとして育てる。
> Claude Code がスラッシュコマンドとして認識するには、完成した内容を
> `.claude/commands/` 以下の各ファイルに配置する必要がある。

---

## 5. `Guide-for-Guide.md` で押さえるべきポイント

`Guide-for-Guide.md` は以下の6項目を網羅する設計とする。

1. **レビューの背景・目的** — なぜこの情報を集めるのか
2. **作業状況・データ配置** — 対象文献の数・場所・現在の進捗
3. **収集したい情報の概要** — どの概念・変数群か
4. **収集したい情報の単位** — 1論文に対して何件の記録が生じうるか
5. **収集したい情報の詳細** — 各変数の定義・分類・不明時の扱い
6. **出力形式** — ADCSL_Style の JSON 形式で出力する（下記参照）。`location` フィールドには
   `materials/optimized/*.md` 内の該当箇所へのMarkdownリンクを埋め込む。
   アンカー付与は**事前加工**（JSON生成前に `materials/optimized/*.md` を一括処理）で行う。
   これにより、複数エージェントが同じアンカーを参照でき、`location` の一貫性が保たれる。

### ADCSL_Style とは

各抽出項目を以下の5フィールドで記録するフォーマット（`share_package/prompts/DE_Guide_v14.md` で定義）。

| フィールド            | 内容                                                |
|:----------------------|:----------------------------------------------------|
| **Answer**            | 抽出情報（カテゴリ回答または短いテキスト）          |
| **Detail**            | 項目が求める構造化された詳細情報                    |
| **Confidence Rating** | High / Medium / Low                                 |
| **Supporting Text**   | 論文からの直接引用（簡潔に。言い換え禁止）          |
| **Location**          | 引用の所在（`"FileName: Section / Location"` 形式） |

---

## 6. `SKILL.md` への記録方針

- 作業の区切り（Phase 1 完成、Phase 2 完成、Phase 3 完成など）ごとに `SKILL.md` を更新する
- `/prep-guide-for-de-guide`・`/prep-de-guide`・`/gene-de-script` それぞれについて、AIが迷わず再現できる粒度で記述する
- 具体的には: 使用ファイル・実行コマンド・出力の確認方法・典型的なつまずきと対処
- 内容が固まったら `.claude/commands/` 以下の各ファイルに配置してスラッシュコマンドとして登録する

**━━━━━━━━━━━━━━━━━━━━━━━━**

*share_package/data-extraction/Guide-for-Guide-for-Guide.md*

[INDEX](../../INDEX.md)
