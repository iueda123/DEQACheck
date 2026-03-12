# DE_Guide_v14.md 作成のための作業計画書（Clinical Cohort 抽出）

## 目的

Normative Model (NM) 構築後に Clinical Cohort に適用した研究について、 [^1]
Clinical Cohort の人口統計情報を疾患 Minor カテゴリ単位で体系的に抽出できる
データ抽出プロンプト（DE_Guide_v14.md）を作成する。

[^1]: もしClinical CohortにNMを適用していない場合はその旨を回答JSON内のどこかで示して欲しい。

## 出力

- 出力先: `share_package/prompts/DE_Guide_v14.md`
- 参照ガイド: `share_package/prompts/DE_Guide_v10_1.md`, `DE_Guide_v11.md`,
  `DE_Guide_v12.md`, `DE_Guide_v13.md`

## 参照資料

- 疾患カテゴリ定義:
  `doc/2026.02.19_Keywords-for-Disorder-Name.md`
- 文献本文:
  `share_package/data/<AuthorYear>/materials/optimized/`
- 一次調査結果:
  `share_package/data/<AuthorYear>/DE_v10/json/DE_<AuthorYear>_by_<Agent>_yyyymmddHHMMSS.json`
  - `clinical_application_and_analysis_part`（特に caa1〜5）を参照

## 抽出スコープ

疾患 Minor カテゴリごとに以下を抽出する。

- 疾患カテゴリ: Major / Minor
- 由来データセット名（複数可）
  - 各データセットに EPD / Non-EPD / In-House ラベルを付与
- N
- Sex: male_n, male_pct, female_n, female_pct
- Age: unit, min, max, mean, sd（必要に応じて weighted / pooled 注記）

## 抽出ルール

- ソース優先順位: 本文・表 > 補足資料 > 明示的に引用された外部ソース（Location に出典を記載）
- 複数モデル／複数フェーズがある場合は phase を明記し、
  必要に応じて `model-1`, `model-2` のモデル別構造を採用する（DE_v13 に準拠）　[^1]
- 年齢単位は原則 years。週齢などの場合は unit を明記する

[^1]: Clinical Cohortに関してはモデル別に情報を集める必要は無いと考えるが、集めたほうが良い理由があれば提示して欲しい。

## 値の計算と表記

- 百分率は小数 1 桁、平均 / SD は小数 2 桁を基本（論文が別精度ならそれに合わせる）
- 片方の性別のみ記載されている場合は N から差し引いて推定し、Detail に推定である旨を明記
- 複数データセット統合時は加重平均（weighted mean）とプールド SD（pooled SD）を算出し、
  `answer` 内に "weighted" / "pooled" と注記

## 抽出結果スタイル

- DE_v13 と同様に **ADCSL_Style / A_Style** を使用
- Clinical Cohort の主要項目は原則 **ADCSL_Style**
- `supporting_text` は必須（原文引用・言い換え禁止）
- `location` はファイル名と特定可能な位置（セクション・行・ページ等）を明記
- `source_text` は使用しない
- `clinical_cohorts` は常に配列とし、単一 Minor カテゴリの研究でも 1 要素配列に格納する
- 各要素は `disorder_major` / `disorder_minor` 単位で記録し、欠損情報は `NR` で統一する

## JSON 例（支援テキスト付き）

```json
{
  "paper_id": "AuthorYear",
  "clinical_cohorts": [
    {
      "disorder_major": "MajorCategory",
      "disorder_minor": "MinorCategory",
      "datasets": [
        {
          "name": "DatasetA",
          "label": "EPD"
        }
      ],
      "phase": "Overall",
      "N": {
        "answer": 123,
        "detail": "Total clinical cohort N",
        "confidence_rating": "High",
        "supporting_text": "\"We included 123 patients...\"",
        "location": "Example2024.pdf.md: Methods / Participants, L40-L45"
      },
      "sex": {
        "answer": {
          "male_n": 60,
          "male_pct": 48.8,
          "female_n": 63,
          "female_pct": 51.2
        },
        "detail": "Percentages calculated from reported counts",
        "confidence_rating": "Medium",
        "supporting_text": "\"60 males and 63 females\"",
        "location": "Example2024.pdf.md: Methods / Participants, L46-L48"
      },
      "age": {
        "answer": {
          "unit": "years",
          "min": 12,
          "max": 68,
          "mean": 34.56,
          "sd": 10.12,
          "note": "weighted mean, pooled SD"
        },
        "detail": "Weighted mean/pooled SD across datasets",
        "confidence_rating": "Low",
        "supporting_text": "\"Mean age 35.2 (SD 9.8) in Dataset A; 33.7 (SD 10.5) in Dataset B\"",
        "location": "Example2024.pdf.md: Results / Participants, L55-L60"
      }
    }
  ]
}
```

## 作業手順

1) 参照ガイド（v10_1〜v13）の構成と記述トーンを確認
2) 本計画書の抽出スコープとルールを DE_Guide_v14.md に反映
3) Clinical Cohort 抽出項目に ADCSL_Style / A_Style を割り当て
4) JSON 例と記述の整合性を確認し、最終化
