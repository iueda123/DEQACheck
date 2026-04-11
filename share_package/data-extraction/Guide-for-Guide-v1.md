[INDEX](../../INDEX.md)

*share_package/data-extraction/Guide-for-Guide-v1.md*

**━━━━━━━━━━━━━━━━━━━━━━━━**

# Guide-for-Guide v1 — NM Training Dataset 情報抽出ガイドの設計方針

> **derived_from**: `Guide-for-Guide-v0.md`
> **対応 DE_Guide**: 未定（`/prep-de-guide` 実行時に決定）

---

## 1. レビューの背景・目的

神経画像のノルマティブモデリング（Normative Modeling; NM）に関するシステマティックレビューを実施しています。
研究プロトコルは PROSPERO に登録済みです（`docs/POSPERO/PROSPERO_final.pdf` 参照）。

NM 手法は、健康な者のデータを用いてモデルを構築（training）し、そこから逸脱した個体を統計的に特定することで、疾患群の脳構造・機能上の個人差を評価します。
本レビューでは、**NM 構築に使われた training dataset の人口統計情報**を系統的に収集することで、先行研究間のデータセット選択の実態と多様性を明らかにすることを目的とします。

---

## 2. 作業状況・データ配置

- TiAb Screening および Full Text Screening を経て **122 文献**が選出済み
- 各文献の資料は `share_package/data/{AuthorYear}/materials/optimized/` に Markdown 変換済みで格納
  - `main.pdf.md`: 本文
  - `sup.pdf.md`: 補足資料（存在する場合）
- 現在、情報抽出（Data Extraction）フェーズに入る段階

---

## 3. 収集したい情報の概要

**Normative Model の構築に用いられた training dataset の人口統計情報**を収集する。

具体的には以下を対象とする:
- 使用データセット名
- 総被験者数（N）
- 性別内訳（男性数・女性数）
- 年齢統計（平均・SD・最小・最大）
- 上記が Training Phase のものか Overall Phase のものか

---

## 4. 収集したい情報の単位

**Normative Model（NM）ごと**に1レコードを作成する。

- 1論文に複数の NM が含まれる場合は、NM の数だけレコードを作成する
- スクリーニング通過後も NM が1つも確認できない場合（スクリーニングエラー）は `normative_models` を空配列 `[]` とする

---

## 5. 収集したい情報の詳細

各 NM について以下のフィールドを収集する。すべて **ADCSL_Style** で記録する。

| フィールドID   | 内容                                         | 備考                                                                                                                                                                                                |
|:---------------|:---------------------------------------------|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `dataset_name` | 使用データセット名（複数はセミコロン区切り） | 5件超の場合は代表例 + 総数（例: `"ABIDE; HCP; UK Biobank; and 120+ others (Total 132)"`）。正規化キーワードは `docs/20260116_keywords-for-dataset-name/20260116_Keywords-for-DatasetName.md` を参照 |
| `hc_n`         | 健常者の総被験者数 N                         | Phase を `answer` に含める（後述）                                                                                                                                                                  |
| `hc_sex`       | 女性数・男性数・各比率（%）                  | 片方のみ記載の場合は差し引いて推定し Detail に明記                                                                                                                                                  |
| `hc_age`       | 平均・SD・最小・最大（単位: years）          | 週齢等の場合は unit を明記。mean/sd の種別を括弧付きで付記（後述）                                                                                                                                  |


### Phase の分類

各フィールドの `answer` の先頭に以下のいずれかを付記する:

| Phase 値   | 意味                                                          |
|:-----------|:--------------------------------------------------------------|
| `Training` | NM の training set に限定した統計                             |
| `Overall`  | 研究全体の健常者コホート（training + その他の分割を含む総数） |
| `Unknown`  | どちらか特定できない                                          |

`answer` 記載例:
- `"Phase: Training | N: 27117"`
- `"Phase: Overall | F: 250 (50.0%) | M: 250 (50.0%)"`
- `"Phase: Unknown | mean: 35.2 | sd: 12.1 | min: 18 | max: 85"`

### `hc_age` の算出ルールと表記

mean と sd の種別を `answer` に括弧付きで付記する。

| 括弧表記        | 意味                                                |
|:----------------|:----------------------------------------------------|
| `(as reported)` | 論文に直接記載された値                              |
| `(weighted)`    | 構成データセットの N で重み付けして算出した加重平均 |
| `(pooled)`      | 構成データセットから算出したプールド SD             |

統合データセットで mean/sd が直接記載されていない場合:
- weighted mean = Σ(Nᵢ × meanᵢ) / ΣNᵢ
- pooled SD = √( Σ((Nᵢ−1) × sdᵢ²) / (ΣNᵢ − k) )　（k = データセット数）
- 算出した旨を Detail に明記する

`answer` 記載例:
- `"Phase: Training | mean: 35.20 (as reported) | sd: 12.10 (as reported) | min: 18 | max: 85"`
- `"Phase: Overall | mean: 42.31 (weighted) | sd: 15.08 (pooled) | min: NR | max: NR"`

### 値が不明・未記載の場合

- 論文に記載なし → `NR`（Not Reported）
- 導出可能な場合（片方の性別から計算等）→ 計算した値を記載し、Detail に「推定値」と明記
- 複数 NM で共通の training dataset を使う場合 → 各 NM に同じ情報を記録し、Detail に「NM X と同一データセット」と注記

---

## 6. 出力形式

**ADCSL_Style** の JSON 形式で出力する。

### ADCSL_Style フィールド定義

| フィールド          | 型     | 内容                                               |
|:--------------------|:-------|:---------------------------------------------------|
| `answer`            | string | 抽出情報（Phase プレフィックスを含む短いテキスト） |
| `confidence_rating` | string | `High` / `Medium` / `Low`                          |
| `supporting_text`   | string | 論文からの直接引用（言い換え禁止）                 |
| `location`          | string | 引用箇所への Markdown リンク（後述のフォーマット） |

### `location` フィールドのフォーマット

`location` の値は、`supporting_text` の引用箇所へのリンクを **Markdown リンク形式**の文字列として記述する。

```
[{FileName}: {Section} / {アンカー名}]({JSONファイルからの相対パス}#{アンカーID})
```

- `{FileName}`: `materials/optimized/` 内のファイル名（`main.pdf.md` など）
- `{アンカーID}`: `prep-study-materials.sh` が自動付与したアンカーの ID
- 相対パスは JSON ファイルの位置（`Studies/{AuthorYear}/extracted-info/{番号}/`）からの相対パス

例:
```
"[main.pdf.md: Methods / methods](../../materials/optimized/main.pdf.md#methods)"
"[main.pdf.md: Table 1 / table-1](../../materials/optimized/main.pdf.md#table-1)"
"[sup.pdf.md: Supplementary Table 1 / supplementary-table-1](../../materials/optimized/sup.pdf.md#supplementary-table-1)"
```

`prep-study-materials.sh` は各 Markdown 見出しに `{#anchor-id}` 形式のアンカーを付与する。
エージェントはアンカー一覧をファイルから読み取り、最も近い箇所のアンカー ID を選んで `location` に埋め込む。

### JSON スキーマ

```json
{
  "paper_id": "AuthorYear",
  "normative_models": [
    {
      "nm_id": "nm1",
      "nm_label": "任意の識別ラベル（例: 'whole-brain cortical thickness model'）",
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

**━━━━━━━━━━━━━━━━━━━━━━━━**

*share_package/data-extraction/Guide-for-Guide-v1.md*

[INDEX](../../INDEX.md)
