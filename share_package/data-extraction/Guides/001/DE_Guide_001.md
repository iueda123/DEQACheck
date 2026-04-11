# NM Training Dataset 情報抽出プロンプト — DE_Guide_001

> **derived_from**: `Guide-for-Guide-v1.md`
> **作成日**: 2026-04-11
> **番号**: 001

---

## プロンプト概要

あなたは神経画像のノルマティブモデリング（Normative Modeling; NM）研究からデータを抽出する熟練したレビューアです。
本プロンプトでは、指定された1つの文献について、**NM 構築に用いられた training dataset の人口統計情報**を抽出してください。

---

## 抽出対象

各研究が NM を構築するために使用した健常者（reference cohort）のデータセットに関する情報を、
**Normative Model（NM）ごとに**抽出します。

- 1論文に複数の NM が含まれる場合は、NM の数だけレコードを作成してください
- NM が1つも確認できない場合は `normative_models` を空配列 `[]` としてください

---

## 一般的な抽出ルール

- **ソース優先順位**: 本文・表 > 補足資料 > 明示的に引用された外部ソース
- **複数値**: セミコロン（`;`）で区切る
- **情報が見つからない場合**: `NR`（Not Reported）を使用
- **導出値**: 片方の性別のみ記載の場合は総 N から差し引いて推定し、Detail に「推定値」と明記
- **複数 NM で共通データセット**: 各 NM に同じ情報を記録し、Detail に「NM X と同一データセット」と注記
- **年齢単位**: 原則「years」。週齢等の場合は Detail に unit を明記

---

## 抽出結果のスタイル

### ADCSL_Style

すべての抽出フィールドは以下の4フィールドで記録してください。

| フィールド          | 型     | 内容                                                     |
|:--------------------|:-------|:---------------------------------------------------------|
| `answer`            | string | 抽出情報（後述のフォーマットに従う）                     |
| `confidence_rating` | string | `High` / `Medium` / `Low`                                |
| `supporting_text`   | string | 論文からの直接引用（言い換え禁止。簡潔に）               |
| `location`          | string | 引用箇所への Markdown リンク（後述のフォーマットに従う） |

### `location` フィールドのフォーマット

`location` は引用箇所へのリンクを **Markdown リンク形式**の文字列で記述してください。

```
[{FileName}: {Section} / {アンカー名}](../../materials/optimized/{FileName}#{アンカーID})
```

- `{FileName}`: `materials/optimized/` 内のファイル名（`main.pdf.md` または `sup.pdf.md`）
- `{アンカーID}`: 見出しテキストを lowercase・空白をハイフンに変換した ID（例: `methods` → `#methods`、`table-1` → `#table-1`）
- 相対パスは出力 JSON ファイルの位置（`Studies/{AuthorYear}/extracted-info/001/`）から

記載例:
```
"[main.pdf.md: Methods / methods](../../materials/optimized/main.pdf.md#methods)"
"[main.pdf.md: Table 1 / table-1](../../materials/optimized/main.pdf.md#table-1)"
"[sup.pdf.md: Supplementary Table 1 / supplementary-table-1](../../materials/optimized/sup.pdf.md#supplementary-table-1)"
```

---

## 抽出依頼内容

### P. Paper Part

#### P-1. paper_id

- 定義: 論文の一意ID（著者名 + 出版年）
- フォーマット: `{ "paper_id": "AuthorYear" }`（文字列のみ。ADCSL_Style 不要）

---

### NM. Normative Model Part

各 NM について以下のフィールドを抽出してください。

#### NM-0. nm_id / nm_label

- `nm_id`: `"nm1"`, `"nm2"`, ... と連番で付与
- `nm_label`: NM を識別するための任意ラベル（例: `"whole-brain cortical thickness model"`）。論文内に識別可能な名称があればそれを使用。なければ簡潔に記述

---

#### NM-1. dataset_name

- 定義: NM 構築に使用したデータセット名
- 抽出スタイル: ADCSL_Style
- `answer` フォーマット:
  - 9件以下: `"DatasetA; DatasetB; DatasetC"` — 正規化キーワードを使用
  - 10件以上: `"DatasetA; DatasetB; DatasetC; and XX others (Total YY)"` — 代表例に正規化キーワードを使用
- 正規化キーワードの参照先: `./dataset-name-keywords.md`

---

#### NM-2. hc_n

- 定義: NM 構築に用いた健常者の総被験者数 N
- 抽出スタイル: ADCSL_Style
- `answer` フォーマット: `"Phase: {Phase値} | N: {数値}"`

**Phase 値の定義**:

| Phase 値   | 意味                                                          |
|:-----------|:--------------------------------------------------------------|
| `Training` | NM の training set に限定した統計                             |
| `Overall`  | 研究全体の健常者コホート（training + その他の分割を含む総数） |
| `Unknown`  | どちらか特定できない                                          |

記載例:
- `"Phase: Training | N: 27117"`
- `"Phase: Overall | N: 50000"`
- `"Phase: Unknown | N: 1200"`

---

#### NM-3. hc_sex

- 定義: 健常者コホートの性別内訳（女性数・男性数・各比率）
- 抽出スタイル: ADCSL_Style
- `answer` フォーマット: `"Phase: {Phase値} | F: {数値} ({比率}%) | M: {数値} ({比率}%)"`
- 比率は小数1桁
- 片方のみ記載の場合: 総 N から差し引いて推定し、Detail に「推定値」と明記

記載例:
- `"Phase: Training | F: 14372 (53.0%) | M: 12745 (47.0%)"`
- `"Phase: Unknown | F: NR | M: NR"`

---

#### NM-4. hc_age

- 定義: 健常者コホートの年齢統計（平均・SD・最小・最大）
- 抽出スタイル: ADCSL_Style
- `answer` フォーマット: `"Phase: {Phase値} | mean: {値} ({種別}) | sd: {値} ({種別}) | min: {値} | max: {値}"`

**mean・sd の種別**（括弧内に付記）:

| 種別          | 意味                                                                                           |
|:--------------|:-----------------------------------------------------------------------------------------------|
| `as reported` | 論文に直接記載された値                                                                         |
| `weighted`    | 構成データセットの N で重み付けした加重平均（Σ(Nᵢ×meanᵢ)/ΣNᵢ）                           |
| `pooled`      | 構成データセットから算出したプールド SD（√(Σ((Nᵢ-1)×sdᵢ²)/(ΣNᵢ-k))、k=データセット数） |

統合データセットで mean/sd が直接記載されていない場合は上記の式で算出し、種別を `weighted`/`pooled` と明記してください。
算出した旨を Detail にも明記してください。

記載例:
- `"Phase: Training | mean: 35.20 (as reported) | sd: 12.10 (as reported) | min: 18 | max: 85"`
- `"Phase: Overall | mean: 42.31 (weighted) | sd: 15.08 (pooled) | min: NR | max: NR"`
- `"Phase: Unknown | mean: NR | sd: NR | min: 6 | max: 89"`

---

## 出力 JSON スキーマ

```json
{
  "paper_id": "AuthorYear",
  "normative_models": [
    {
      "nm_id": "nm1",
      "nm_label": "識別ラベル",
      "dataset_name": {
        "answer": "DatasetA; DatasetB; DatasetC",
        "confidence_rating": "High",
        "supporting_text": "直接引用",
        "location": "[main.pdf.md: Methods / methods](../../materials/optimized/main.pdf.md#methods)"
      },
      "hc_n": {
        "answer": "Phase: Training | N: 500",
        "confidence_rating": "High",
        "supporting_text": "直接引用",
        "location": "[main.pdf.md: Table 1 / table-1](../../materials/optimized/main.pdf.md#table-1)"
      },
      "hc_sex": {
        "answer": "Phase: Training | F: 250 (50.0%) | M: 250 (50.0%)",
        "confidence_rating": "High",
        "supporting_text": "直接引用",
        "location": "[main.pdf.md: Table 1 / table-1](../../materials/optimized/main.pdf.md#table-1)"
      },
      "hc_age": {
        "answer": "Phase: Training | mean: 35.20 (as reported) | sd: 12.10 (as reported) | min: 18 | max: 85",
        "confidence_rating": "High",
        "supporting_text": "直接引用",
        "location": "[main.pdf.md: Table 1 / table-1](../../materials/optimized/main.pdf.md#table-1)"
      }
    }
  ]
}
```

NM が存在しない場合:
```json
{
  "paper_id": "AuthorYear",
  "normative_models": []
}
```
