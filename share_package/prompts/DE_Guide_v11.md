# 神経画像におけるノルマティブモデリングのデータ抽出ガイダンス（システマティックレビュー）

-------------------

## プロンプト概要

あなたは神経画像のノルマティブモデリング研究からデータを抽出する熟練したレビューアです。このデータ抽出は、精神・神経疾患に対する神経画像の方法論的検討と応用に関するシステマティックレビューの一環として行われます。

-------------------

## 本レビューの目的

本システマティックレビューでは、神経画像および神経生理検査（例: MRI, PET, EEG,
MEG）を用いたノルマティブモデリング研究を評価します。以下の3点を扱います:

*
    1. **モダリティ**:
       ノルマティブモデリングで用いられている計測技術は何か、それぞれどの程度使われているか。MRIのような一般的モダリティでは、どのシーケンス（例:
       T-weighted）が最も頻用されるか。
*
    2. **方法論**: モダリティをまたいでノルマティブモデリング研究はどのように設計・検証されているか。サンプルサイズ、共変量、前処理、統計モデル、ハーモナイゼーション手法、検証戦略などを抽出する。
*
    3. **臨床的スコープ**: どの精神・神経疾患がノルマティブモデリングで検討されているか、個人レベルの逸脱パターンや臨床的ユーティリティは何が報告されているか。

-------------------

## ファイル構成

```
<timestamp>/                          # カレントディレクトリ（ここで作業）
├── DE_Guide_v11_a-study.md           # このファイル（ガイド）
├── DE_<AuthorYear>_by_<agent>_*.json # 結果ファイル（ここに出力）
└── study_1/                          # 研究の論文・資料
    ├── <論文>.pdf.md
    └── ...
```

## ソース資料の場所

- 対象となる論文はカレントディレクトリ下にある `./study_1/` フォルダ内にあります。
- 結果JSONファイルはカレントディレクトリ直下に出力してください（`study_1/` の中ではありません）。
- 次のセクションにある抽出依頼にある情報を抽出してください。必要に応じてサブフォルダも参照してください。

-------------------

## 一般的な抽出ルール

- スコープと優先順位
    - 本文と表を優先。なければ補足資料を参照。それでもなければ明示的に引用された外部ソースを使用し、Locationに出典を記載。

- 記載の慣習
    - 複数値はセミコロンで区切る

- 一貫性と書式
    - 項目名は指定どおり厳守。新しいフィールドを作らない。
    - ACRSL_Style では必ず5要素（answer, confidence_rating, reason, supporting_text, location）。
    - ADCSL_Style でも必ず5要素（answer, detail, confidence_rating, supporting_text, location）。
    - Supporting Text は簡潔な原文引用。言い換え禁止。
    - Location はファイル名と特定できる位置（セクション/行/ページ等）。

- 抽出結果のスタイル
    - データ抽出ガイドでは4つのスタイルを使います:
    - **A_Style** = Answer のみ
        - 抽出した答えだけを記載
        - confidence_rating, reason, supporting_text, location は不要
        - 単純項目や選択肢のみの項目に使用
    - **ASL_Style** = Answer, Supporting text, Location
        - 推論なしにエビデンスを添える:
            - Answer: 抽出情報
            - Supporting text: 出典の簡潔な引用
            - Location: 出典の所在
        - 明示的な文言があるカテゴリ的項目などに使用
    - **ACRSL_Style** = Answer, Confidence rating, Reason, Supporting text, Location
        - 詳細な抽出:
            - Answer: 抽出情報
            - Confidence rating: High / Medium / Low
            - Reason: 判断のステップ
            - Supporting text: 直接引用
            - Location: 引用の所在
        - 複雑で根拠が必要な項目に使用
    -
        * **ADCSL_Style** = Answer, Detail, Confidence rating, Supporting text, Location

        - カテゴリ回答＋簡潔な詳細が必要な項目に使用:
            - Answer: 抽出情報
            - Detail: 項目で求める構造化された詳細
            - Confidence rating: High / Medium / Low
            - Supporting text: 直接引用
            - Location: 引用の所在

詳細なフォーマット要件と例は後述「抽出結果のスタイル」を参照。

-------------------

## 抽出依頼内容

### SI. Study Identification Part

#### SI-1. Study ID

* 各論文の一意ID。著者名+年。
* 抽出スタイル: A_Style
* 例:
    * Rutherford2022

#### SI-2. Reference File Names

* データ抽出時に参照したファイル名
* 抽出スタイル: A_Style
* 例:
    * Rutherford2022.pdf.md; Rutherford2022_sup.pdf.md

#### SI-3. Author, Journal, and Year

* 抽出スタイル: A_Style
* データ型: string
* 例:
    * Rutherford et al., Communications Biology, 2022

#### SI-4. Title

* 論文タイトル（原文どおり）
* 抽出スタイル: A_Style

### DC. Dataset Characteristics Part

#### DC-1. Datasets Using in This Study

* 抽出基準: この研究で用いられているデータセットを列挙してください。
* データセット名は次の表のMinor Category列から選んでください。

| Major Category Keyword   | Minor Category                       | Minor Category Full Spelling                                                           |
|--------------------------|--------------------------------------|----------------------------------------------------------------------------------------|
| ABCCT                    | ABCCT                                | Autism Biomarker Consortium for Clinical Trials Dataset                                |
| ABCD                     | ABCD                                 | Adolescent Brain Cognitive Development Study                                           |
| ABIDE                    | ABIDE                                | Autism Brain Imaging Data Exchange                                                     |
| ABIDE                    | ABIDE-I                              | Autism Brain Imaging Data Exchange I                                                   |
| ABIDE                    | ABIDE-II                             | Autism Brain Imaging Data Exchange II                                                  |
| ABIDE                    | ABIDE (I+II)                         | Autism Brain Imaging Data Exchange I and II                                            |
| ABRIM                    | ABRIM                                | Advanced Brain Imaging on Ageing and Memory                                            |
| ADHD-200                 | ADHD-200                             | ADHD-200 Sample                                                                        |
| ADDI                     | ADNI                                 | Alzheimer's Disease Neuroimaging Initiative                                            |
| ADNI                     | ADNI-ADS                             | Alzheimer's Disease Neuroimaging Initiative - Alzheimer's Disease Spectrum             |
| ADNI                     | ADNI-3                               | Alzheimer's Disease Neuroimaging Initiative 3                                          |
| AIBL                     | AIBL                                 | Australian Imaging, Biomarkers and Lifestyle Study of Ageing                           |
| ARWiBo                   | ARWiBo                               | Alzheimer's Disease Repository Without Borders                                         |
| ASRB                     | ASRB                                 | Australian Schizophrenia Research Bank                                                 |
| Aggressotype and MATRICS | Aggressotype and MATRICS             | The dataset from EU-funded Aggressotype and MATRICS consortia                          |
| BHRCS                    | BHRCS                                | Brazilian High-Risk Cohort Study                                                       |
| BLISS                    | BLISS                                | Bipolar Lithium Imaging and Spectroscopy Study                                         |
| CHUV                     | CHUV                                 | Centre Hospitalier Universitaire Vaudois                                               |
| CIFASD                   | CIFASD                               | Collaborative Initiative on Fetal Alcohol Spectrum Disorders                           |
| CNP                      | CNP                                  | Consortium for Neuropsychiatric Phenomics                                              |
| COBRE                    | COBRE                                | Center for Biomedical Research Excellence                                              |
| Cam                      | Cam-CAN                              | Cambridge Centre for Ageing and Neuroscience                                           |
| DEMGEN                   | DEMGEN                               | Norwegian Dementia Genetics Network                                                    |
| DIDA                     | DIDA-MDD                             | Disease Imaging Data Archiving - Major Depressive Disorder                             |
| DIRECT                   | DIRECT                               | DIRECT consortium                                                                      |
| Depression               | Depression-EEG                       | Depression-EEG                                                                         |
| EMBARC                   | EMBARC                               | Establishing Moderators and Biosignatures of Antidepressant Response for Clinical Care |
| ENIGMA                   | ENIGMA                               | Enhancing NeuroImaging Genetics through Meta-Analysis                                  |
| ENIGMA                   | ENIGMA-CHR-P                         | Enhancing NeuroImaging Genetics through Meta-Analysis - Clinical High Risk - Psychosis |
| EU                       | EU                                   | European 16p11.2 consortium                                                            |
| Early                    | Early Stages of Schizophrenia        | Early Stages of Schizophrenia                                                          |
| FCON                     | FCON                                 | 1000 Functional Connectomes Project                                                    |
| GAP                      | GAP                                  | the Genetics and Psychosis                                                             |
| GROUP                    | GROUP                                | Genetic Risk and Outcome of Psychosis                                                  |
| GSP                      | GSP                                  | Brain Genomics Superstruct Project                                                     |
| HBN                      | HBN                                  | Healthy Brain Network                                                                  |
| HCP                      | HCP                                  | Human Connectome Project                                                               |
| HCP                      | HCP-A                                | Human Connectome Project Aging                                                         |
| HCP                      | HCP-B                                | Human Connectome Project Baby                                                          |
| HCP                      | HCP-D                                | Human Connectome Project Development                                                   |
| HCP                      | HCP-EP                               | Human Connectome Project Early Psychosis                                               |
| HCP                      | HCP-LS                               | Human Connectome Project Lifespan                                                      |
| HCP                      | HCP-YA                               | Human Connectome Project Young Adult                                                   |
| IBCDR                    | IBCDR                                | International Big-Data Center for Depression Research                                  |
| IDEAS                    | IDEAS                                | Imaging Database for Epilepsy and Surgery                                              |
| IMpACT                   | IMpACT                               | International Multicenter persistent ADHD CollaboraTion                                |
| INsIDER                  | INsIDER                              | Imaging Axonal Damage & Repair in Multiple Sclerosis                                   |
| IXI                      | IXI                                  | Information eXtraction from Images dataset                                             |
| Imperial APC             | Imperial APC                         | The Imperial Amyloid PET Cohort                                                        |
| KQJH                     | KQJH                                 | KQJH dataset                                                                           |
| Knight                   | Knight ADRC-ADS                      | Knight Alzheimer's Disease Research Center                                             |
| LEAP                     | LEAP                                 | Longitudinal European Autism Project                                                   |
| LEMON                    | LEMON                                | Leipzig Study for Mind-Body-Emotion Interactions                                       |
| Lausanne                 | Lausanne-ASD                         | Lausanne Autism Spectrum Disorder cohort                                               |
| MCAD                     | MCAD                                 | Multicenter Alzheimer Disease Imaging Consortium                                       |
| MCIC                     | MCIC                                 | MIND Clinical Imaging Consortium                                                       |
| MIND                     | MIND-Set                             | MIND-Set study                                                                         |
| MIPDB                    | MIPDB                                | Multimodal Resource for Studying Information Processing in the Developing Brain        |
| MIRIAD                   | MIRIAD                               | Minimal Interval Resonance Imaging in Alzheimer's Disease                              |
| MSSEG2016                | MSSEG2016                            | MICCAI MSSEG 2016 Challenge                                                            |
| NACC                     | NACC                                 | National Alzheimer's Coordinating Center                                               |
| NAKO                     | NAKO                                 | German National Cohort (NAKO Gesundheitsstudie)                                        |
| NIFD                     | NIFD                                 | Neuroimaging in Frontotemporal Dementia                                                |
| NIHPD                    | NIHPD                                | NIH MRI Study of Normal Brain Development                                              |
| NIL                      | NIL                                  | Louvain Neuroinflammation Imaging Lab                                                  |
| NIMHANS                  | NIMHANS                              | National Institute of Mental Health and Neurosciences                                  |
| NKI                      | NKI                                  | Nathan Kline Institute                                                                 |
| NUSDAST                  | NUSDAST                              | Northwestern University Schizophrenia Data and Software Tool                           |
| OASIS                    | OASIS-1                              | Open Access Series of Imaging Studies-1                                                |
| OASIS                    | OASIS-3                              | Open Access Series of Imaging Studies-3                                                |
| OPTiMiSE                 | OPTiMiSE                             | the Optimization of Treatment and Management of Schizophrenia in Europe                |
| OpenBHB                  | OpenBHB                              | Open Brain Health Benchmark                                                            |
| OpenMSLong               | OpenMSLong                           | OpenMS Longitudinal dataset                                                            |
| PNC                      | PNC                                  | Philadelphia Neurodevelopmental Cohort                                                 |
| PPP                      | PPP                                  | Personalized Parkinson Project                                                         |
| REST                     | REST-meta-MDD                        | REST-meta-MDD consortium                                                               |
| SAED                     | SAED                                 | Shanghai Autism Early Developmental Cohort                                             |
| STROKEMRI                | STROKEMRI                            | Stroke MRI study                                                                       |
| SVIP                     | SVIP                                 | Simons VIP (Variation in Individuals Project)                                          |
| StratiBip                | StratiBip                            | Stratification of Bipolar Disorder                                                     |
| TractoInferno            | TractoInferno                        | TractoInferno challenge                                                                |
| UCL                      | UCL                                  | University College London                                                              |
| UCLA                     | UCLA                                 | University of California, Los Angeles                                                  |
| UK Biobank               | UK Biobank                           | UK Biobank                                                                             |
| UMich                    | UMich                                | University of Michigan                                                                 |
| Utrecht                  | Utrecht                              | The dataset from Utrecht Schizophrenia project                                         |
| devCCNP                  | devCCNP                              | developing Chinese Color Nest Project                                                  |
| TOP                      | TOP                                  | Thematically Organized Psychosis                                                       |
| TOP                      | sTOP                                 | Forensic Psychiatry study (sTOP)                                                       |
| TOP                      | uTOP                                 | Youth-TOP study (uTOP)                                                                 |
| femaleASD                | femaleASD                            | Multimodal Developmental Neurogenetics of Females with ASD                             |
| MULTI-DATASET            | MULTI-DATASET (UNSPECIFIED OR N>=10) | Multiple Datasets (Unspecified or N>=10)                                               |
| No Special Dataset Name  | MULTI-SITE (N>=2)                    | Multiple Sites (N>=2)                                                                  |
| No Special Dataset Name  | SINGLE-SITE                          | Single Site                                                                            |
| None (Pre-Trained NM)    | None (Pre-Trained NM)                | None (Pre-Trained Normative Model)                                                     |
| Unknown                  | Unknown                              | Unknown Dataset                                                                        |                                                                                                                  |                                                                                                                                                                                                                                                                                                                                                   |                                                           |

* 抽出スタイル: ADCSL_Style
* "answer" 例:

```json
"answer": [ "HCP-A", "HCP-YA", "UK Biobank", "COBRE", "UMich", "TOP"]

```

#### DC-2. Purpose of Each Dataset

* 抽出基準: この研究で用いられている各データセットは各々何の目的（train, validation, test, transfer for clinical research,
  patient for clinical research など）に用いられているか列挙してください。
* 抽出スタイル: ADCSL_Style
* "answer" 例:

```json
"answer": [
{"HCP-A": "normative training for model-1"},
{"HCP-YA": "normative training/testing for model-1 and -2"},
{"UK Biobank": "regression benchmark (also partly in functional train/test)"},
{"COBRE": "transfer sets for classification/group difference"},
{"Umich": "transfer sets for classification/group difference"},
{"TOP": "additional transfer sets"}
]
```

#### DC-3. Healthy Control Number of Each Normative Model

* 抽出基準: この研究においてNormative model構築に用いられた健常者データセットのN数について教えてください。
* Overall (trainだけでなくvalidationやtestも含めた) 段階と、train段階を区別して答えてください。
* 複数のモデルが構築されている場合は配列で列挙してください。
* 回答形式は以下のようにしてください。
* 抽出スタイル: ADCSL_Style
* "answer" 例:

```json
"answer": {
  "model-1": {
    "model-name": "functional model",
    "overall-phase": 21594,
     "train-phase": "NR"
  },
"model-2": {
    "model-name": "structural model",
    "overall-phase": "NR",
    "train-phase": 14473
  }
}
```

#### DC-4. Age Info of Each Normative Model

* 抽出基準: Normative model構築時の健常者データセットの年齢に関するmean, sd, median, iqr, min,
  maxを教えてください。Overall (trainだけでなくvalidationやtestも含めた)
  段階と、train段階を区別して答えてください。もし本文に明記されていない場合は、meanとsdに関しては weighted mean of
  ages、pooled sd of ages の算出を試みてください。その他統計値は文脈から推定を試みてください。
* 抽出スタイル: ADCSL_Style
    - 年齢は原則「年」。論文が別の明示的単位を使う場合はその単位を使用し、不明なら単位を明記。
* 注:
    - 提示された数・統計から導出値（百分率、加重平均/プールドSDなど）を計算してよい。百分率は小数1桁、平均/SDは小数2桁を基本（論文が別精度ならそれに合わせる）。
    - 性別の一方のみ記載がある場合、総Nから差し引いて推定し、Reasonで推定を明記。
* "answer" 例:

```json
"answer": {
  "model-1": {
    "overall-phase": {
      "mean": "39.85 weighted",
      "sd": "7.94 pooled",
      "median": "NR",
      "iqr_l": "NR",
      "iqr_u": "NR",
      "min": "2 inferred",
      "max": "100 inferred"
    },
    "train-phase": {
      "mean": "NR",
      "sd": "NR",
      "median": "NR",
      "iqr_l": "NR",
      "iqr_u": "NR",
      "min": "2 inferred",
      "max": "100 inferred"
    }
  },
  "model-2": {
    "overall-phase": {
      "mean": "NR",
      "sd": "NR",
      "median": "NR",
      "iqr_l": "NR",
      "iqr_u": "NR",
      "min": "2 inferred",
      "max": "100 inferred"
    },
    "train-phase": {
      "mean": "NR",
      "sd": "NR",
      "median": "NR",
      "iqr_l": "NR",
      "iqr_u": "NR",
      "min": "2 inferred",
      "max": "100 inferred"
    }
  }
}
```

#### DC-5. Sex Info of Each Normative Model

* 抽出基準: Normative model構築時の健常者データセットの男女各々のN数および比率（%）について教えてください。Overall (
  trainだけでなくvalidationやtestも含めた) 段階と、train段階を区別して答えてください。
* 抽出スタイル: ADCSL_Style
* 注:
    - 性別の一方のみ記載がある場合、総Nから差し引いて推定し、Reasonで推定を明記。
* "answer" 例:

```json
"answer": {
  "model-1": {
    "overall-phase": {
      "female_n": 11085,
      "female_pct": "51.5%",
      "male_n": 10429,
      "male_pct": "48.5%"
    },
    "train-phase": {
      "female_n": 7517,
      "female_pct": "51.9%",
      "male_n": 6955,
      "male_pct": "48.1%"
    }
  },
  "model-2": {
    "overall-phase": {
      "female_n": "NR",
      "female_pct": "NR",
      "male_n": "NR",
      "male_pct": "NR"
    },
    "train-phase": {
      "female_n": 15033,
      "female_pct": "51.1%",
      "male_n": 14385,
      "male_pct": "48.9%"
    }
  }
}
```

-----------------------

## 抽出結果のスタイル

### ACRSL_Style Format

When an item requires ACRSL_Style, provide the following structured information:

1. **Answer**: The extracted information according to the extraction criteria.

- When information is not reported, state what is missing (e.g., "mean NR; sd NR" or "Unknown")
- When extraction criteria do not apply, answer "NA" (not applicable).

2. **Confidence Rating**: Rate your confidence as "High", "Medium", or "Low".
    - **High**: Clear and direct statements in the text; explicit numerical values or unambiguous descriptions.
    - **Medium**: Indirect or limited evidence (e.g., in supplementary materials, tables, or referenced papers);
      requires inference or computation from provided data.
    - **Low**: Ambiguous or insufficient description leading to uncertainty; conflicting information; or reliance on
      assumptions.

3. **Reason**: Provide a step-by-step explanation of how you arrived at the answer and confidence rating. Explain what
   information was available, how you interpreted it, and any computations or inferences made. If you computed values (
   e.g., percentages, pooled SD), state the formula briefly.
    - When extraction criteria do not apply, provide reason explaining why it does not apply.

4. **Supporting Text**: Provide direct excerpts from the source materials that support your answer. Use quotation marks
   and ellipses (...) for omitted text.
    - When extraction criteria do not apply, provide "-".

5. **Location**: Specify where the supporting text was found using the document structure. Prefer the most specific
   locator available.
    - Format: "FileName: Section / Subsection / Location"
    - Example: "Bedford2025.pdf.md: Sample and Datasets, Paragraph 3"
    - When extraction criteria do not apply, provide "-".

#### Example: ACRSL_Style

```json
{
  "rci2_hc_n": {
    "answer": "569",
    "confidence_rating": "High",
    "reason": "The manuscript states they used 569 controls for development and performance testing of the models.",
    "supporting_text": "For this study, we used 569 controls for development and performance testing of the models, out of which 470 were male.",
    "location": "materials/Bayer2022.pdf.md:L125-L129"
  }
}
```

### ADCSL_Style Format

When an item requires ADCSL_Style, provide the following structured information:

1. **Answer**: The extracted information according to the extraction criteria (typically a categorical or short textual
   answer).

2. **Detail**: Concise, structured details required by the item.

3. **Confidence Rating**: Rate your confidence as "High", "Medium", or "Low".

4. **Supporting Text**: Direct quotes from the source materials that support the answer (concise).

5. **Location**: Where the supporting text was found.
    - Format: "FileName: Section / Subsection / Location"

#### Example: ADCSL_Style

```json
{
  "rci10_site_effect_handling": {
    "answer": "Model-based",
    "detail": "Hierarchical Bayesian regression with site random effects; compared against ComBat; site and scanner as batch variables; preserved age and sex; out-of-sample evaluation of harmonization effectiveness",
    "confidence_rating": "High",
    "supporting_text": "\"We accounted for site/scanner using a hierarchical Bayesian model with random effects ... preserving age and sex ... we compared to ComBat and evaluated out-of-sample ...\"",
    "location": "materials/Example2024.pdf.md: Methods / Harmonization, L180-L215"
  }
}
```

### ASL_Style Format

When an item requires ASL_Style, provide the following structured information:

1. **Answer**: The extracted information according to the extraction criteria.
2. **Supporting Text**: Direct quotes from source materials that support the answer (keep concise).
3. **Location**: Where the quote was found.
    - Format: "FileName: Section / Subsection / Location"

#### Example: ASL_Style

```json
{
  "rci6_analysis_level": {
    "answer": "ROI-level",
    "supporting_text": "\"ROI-wise cortical thickness was computed...\"",
    "location": "Paper2022.pdf.md: Methods / Imaging analysis, L120-L125"
  }
}
```

### A_Style Format

1. **Answer**: The extracted information according to the extraction criteria.

For Assessment Items `SI-1` 〜 `SI-5`, `SC-1` 〜 `SC-3`, `CAA-7`, `CAA-8`, `CAA-9`, `CAA-11`, and `GN-1`, provide only the
answer without confidence rating, reason, supporting text, or location.

#### Example: A_Style

```json
{
  "rci8_quality_checking": "Yes"
}
```

-------------------------

## 抽出結果の出力

- ファイル形式
    - JSON。`./DE_Author20XX_by_Someone_YYYYmmddHHMMSS.json` の構造に合わせる。
- ファイル名
    - Claude Code の場合: `DE_Bethlehem2022_by_claude_202509191115.json`（パターン:
      `DE_AuthorYear_by_claude_YYYYMMDDhhdd.ext`）。
    - Gemini CLI の場合: `DE_Bethlehem2022_by_gemini_202509191115.json`（パターン:
      `DE_AuthorYear_by_gemini_YYYYMMDDhhdd.ext`）。
    - Codex-CLI の場合: `DE_Bethlehem2022_by_codex_202509191115.json`（パターン:
      `DE_AuthorYear_by_codex_YYYYMMDDhhdd.ext`）。
    - ファイル名はASCII、空白なし。

- JSONフォーマット
    - キーは指定通り、item IDは snake_case。
    - Answer は混在内容なら文字列。純粋な数値は数値/文字列いずれでもよいが、ファイル内で一貫させる。
    - 末尾カンマ禁止、有効なJSONにする。
