# Clinical Cohort Info 抽出プロンプト

-------------------

## プロンプト概要

あなたは神経画像のノルマティブモデリング（NM）研究からデータを抽出する熟練したレビューアです。
本プロンプトでは、指定された 1 つの文献について、NM 構築後に適用される Clinical Cohort のデータセット特性および人口統計情報を抽出してください。

-------------------

## 抽出対象

- 各研究が NM 構築後に Clinical Cohort に NM を適用している前提で、Clinical Cohort 情報を抽出する。
- 疾患 Minor カテゴリごとに抽出し、`disorders` 配列に記録する。
- もし Clinical Cohort への NM 適用が確認できない場合は、`disorders` を空配列 `[]` とする。

-------------------

## 一般的な抽出ルール

- ソース優先順位: 本文・表 > 補足資料 > 明示的に引用された外部ソース（外部ソース参照時は Location に出典を記載）
- 複数値はセミコロンで区切る
- 情報が見つからない場合: `NR`（Not Reported）を使用
- モデル別の扱い:
  - Clinical Cohort は本抽出ではモデル別に分けない
  - モデル別の値のみが提示されている場合は、`answer` にセミコロンで併記し、
    Detail に「モデル別の値である」旨を明記する
- 導出値（百分率、加重平均、プールド SD 等）を計算してよい
  - 百分率は小数 1 桁、平均 / SD は小数 2 桁を基本（論文が別精度ならそれに合わせる）
  - 片方の性別のみが記載されている場合は N から差し引いて推定し、Detail に推定である旨を明記する
- 年齢単位は原則「年（years）」。週齢（gestational weeks 等）の場合は unit を明記する
- 疾患カテゴリの定義は本書内の「疾患カテゴリ定義」に従う


-------------------

## 抽出結果のスタイル

本プロンプトでは以下の 2 スタイルを使用する。

### ADCSL_Style

1. **Answer**: 抽出情報（カテゴリ回答または短いテキスト）
2. **Detail**: 項目で求める構造化された詳細
3. **Confidence Rating**: High / Medium / Low
4. **Supporting Text**: 直接引用（簡潔に。言い換え禁止）
5. **Location**: 引用の所在（"FileName: Section / Location"）

### A_Style

1. **Answer**: 抽出情報のみ（confidence rating, supporting text, location は不要）

-------------------

## 抽出依頼内容

### P. Paper Part

#### P-1. Paper ID

* 定義: 各論文の一意ID（著者名+年）を記録する。
* 抽出スタイル: A_Style
* 備考: 出力形式は `{ "paper_id": { "answer": "AuthorYear" } }` とする。

### D. Disorders Part

#### D-1. Disorder Name

* 定義: Clinical Cohort の疾患名（Minor カテゴリ）を記録する。
* カテゴリ定義は本書内の「疾患カテゴリ定義」に従う。
* `answer` には正規化キーワード（Minor Category の略語/正式表記）を用いる。
* 抽出スタイル: ADCSL_Style

#### D-2. Dataset of Origin

* 定義: Clinical Cohort の由来データセット名を記録する。
* `answer` には本書内「データセット名 正規化キーワード一覧」の正規化キーワードを配列で記載する。
* 抽出スタイル: ADCSL_Style

#### D-3. Age

* 抽出基準: Clinical Cohort の年齢情報（min, max, mean, sd）を記録する。
* 抽出スタイル: ADCSL_Style
* 注意:
  - 複数データセット統合時は加重平均（weighted mean）とプールド SD（pooled SD）を算出し、`answer` 内に "weighted" / "pooled" と注記する。

#### D-4. Sex

* 抽出基準: Clinical Cohort の Female / Male 各々の N 数および比率（%）を記録する。
* 抽出スタイル: ADCSL_Style
* 注意:
  - 一方の性別のみ記載されている場合、総 N から差し引いて推定する。推定した場合は Detail に明記する。
  - 比率（%）は小数 1 桁。

-------------------

## 疾患カテゴリ定義（Major / Minor）

Major / Minor の組み合わせは以下の表に従う。

| Major Category                 | Minor Category               | Full spelling                                        |
|--------------------------------|------------------------------|------------------------------------------------------|
| Psychotic disorders            | SCZ                          | Schizophrenia                                        |
| Psychotic disorders            | SZA                          | Schizoaffective disorder                             |
| Psychotic disorders            | SZF                          | Schizophreniform disorder                            |
| Psychotic disorders            | BrPD                         | Brief psychotic disorder                             |
| Psychotic disorders            | ATPD                         | Acute/transient psychotic disorder                   |
| Psychotic disorders            | NOS                          | Unspecified psychosis (Psychosis NOS)                |
| Psychotic disorders            | SSD                          | Schizophrenia spectrum disorder                      |
| Psychotic disorders            | PSD                          | Psychosis spectrum disorders                         |
| Psychotic disorders            | AP                           | Affective psychosis                                  |
| Psychotic disorders            | NAP                          | Non-affective psychosis                              |
| Psychotic disorders            | ESZ                          | Early-illness schizophrenia                          |
| Psychotic disorders            | CHR-P                        | Clinical high risk for psychosis                     |
| Psychotic disorders            | FEP                          | First episode psychosis                              |
| Psychotic disorders            | EP                           | Early psychosis                                      |
| Psychotic disorders            | PE                           | Psychotic experiences                                |
| Psychotic disorders            | Psychosis                    | Psychosis                                            |
| Mood disorders                 | BD                           | Bipolar disorder                                     |
| Mood disorders                 | MDD                          | Major depressive disorder                            |
| Mood disorders                 | DEP                          | Depression                                           |
| Mood disorders                 | MD                           | Mood disorders                                       |
| Neurocognitive disorders       | AD                           | Alzheimer's disease                                  |
| Neurocognitive disorders       | DLB                          | Lewy body dementia                                   |
| Neurocognitive disorders       | Dementia                     | Dementia                                             |
| Neurocognitive disorders       | PD                           | Parkinson's disease                                  |
| Neurocognitive disorders       | FTD                          | Frontotemporal dementia                              |
| Neurocognitive disorders       | MCI                          | Mild cognitive impairment                            |
| Neurodevelopmental disorders   | ASD                          | Autism spectrum disorder                             |
| Neurodevelopmental disorders   | ADHD                         | Attention-deficit/hyperactivity disorder             |
| Neurodevelopmental disorders   | DD                           | Developmental delay                                  |
| Neurodevelopmental disorders   | ID                           | Intellectual disability                              |
| Neurodevelopmental disorders   | NDD                          | Neurodevelopmental disorder                          |
| Neurodevelopmental disorders   | DCD                          | Developmental coordination disorder                  |
| Neurodevelopmental disorders   | LCD                          | Language/communication disorder                      |
| Neurodevelopmental disorders   | DBD                          | Disruptive behavior disorders                        |
| Neurodevelopmental disorders   | LD                           | Learning disorder                                    |
| Neurodevelopmental disorders   | FASD                         | Fetal alcohol spectrum disorder                      |
| Anxiety disorders              | ANX                          | Anxiety disorder                                     |
| Anxiety disorders              | GAD                          | Generalized anxiety disorder                         |
| Anxiety disorders              | Panic                        | Panic disorder                                       |
| Anxiety disorders              | PTSD                         | Post-traumatic stress disorder                       |
| Anxiety disorders              | Social                       | Social phobia                                        |
| Anxiety disorders              | Specific                     | Specific phobia                                      |
| Anxiety disorders              | Agoraphobia                  | Agoraphobia                                          |
| Anxiety disorders              | OCD                          | Obsessive-compulsive disorder                        |
| Epilepsy                       | TLE                          | Temporal lobe epilepsy                               |
| Epilepsy                       | DR-TLE                       | Drug-resistant temporal lobe epilepsy                |
| Epilepsy                       | RE                           | Refractory epilepsy                                  |
| Epilepsy                       | SeLECTS                      | SeLECTS                                              |
| Epilepsy                       | Epilepsy post-hemispherotomy | Epilepsy post-hemispherotomy                         |
| Epilepsy                       | TLE post-ATLR                | TLE post-ATLR                                        |
| Epilepsy                       | mTLE                         | Mesial temporal lobe epilepsy                        |
| Demyelinating disorders        | RRMS                         | Relapsing-remitting multiple sclerosis               |
| Demyelinating disorders        | MS                           | Multiple sclerosis                                   |
| Chromosomal/CNV                | T21                          | Trisomy 21                                           |
| Chromosomal/CNV                | 1q21.1 del                   | 1q21.1 deletion                                      |
| Chromosomal/CNV                | 1q21.1 dup                   | 1q21.1 duplication                                   |
| Chromosomal/CNV                | 22q11.2 del                  | 22q11.2 deletion syndrome                            |
| Chromosomal/CNV                | 16p11.2 del                  | 16p11.2 deletion                                     |
| Chromosomal/CNV                | 16p11.2 dup                  | 16p11.2 duplication                                  |
| Chromosomal/CNV                | pathogenic CNV               | Pathogenic CNV carriers                              |
| Brain tumors                   | DIPG                         | Diffuse intrinsic pontine glioma                     |
| Brain tumors                   | DMG                          | Diffuse midline glioma                               |
| Brain tumors                   | PBT                          | Pediatric brainstem tumors                           |
| Brain tumors                   | Brainstem tumor              | Brainstem tumor                                      |
| Brain tumors                   | Frontal glioma               | Frontal glioma                                       |
| Alzheimer spectrum             | Pre-AD                       | Preclinical AD                                       |
| Alzheimer spectrum             | EMCI                         | Early mild cognitive impairment                      |
| Alzheimer spectrum             | LMCI                         | Late mild cognitive impairment                       |
| Alzheimer spectrum             | sMCI                         | Stable mild cognitive impairment                     |
| Alzheimer spectrum             | pMCI                         | Progressive mild cognitive impairment                |
| Alzheimer spectrum             | MCI-AD                       | Mild cognitive impairment due to Alzheimer's disease |
| Substance/behavioral disorders | TUD                          | Tobacco Use Disorder                                 |
| Substance/behavioral disorders | CUD                          | Cannabis Use Disorder                                |
| Substance/behavioral disorders | IGD                          | Internet Gaming Disorder                             |
| Neurotrauma                    | mTBI                         | Mild traumatic brain injury                          |
| Psychiatric disorders (other)  | psychopathology              | Psychopathology                                      |

-------------------

## データセット名 正規化キーワード

正規化キーワード一覧を使用する。

### 正規化キーワード一覧

| Major Category Keyword | Minor Category | Minor Category Full Spelling | Alias / variant examples |
| -------------------------- | -------------------------------------- | ------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------ |
| ABCCT | ABCCT | Autism Biomarker Consortium for Clinical Trials Dataset |  |
| ABCD | ABCD | Adolescent Brain Cognitive Development Study |  |
| ABIDE | ABIDE | Autism Brain Imaging Data Exchange | ABIDE |
| ABIDE | ABIDE-I | Autism Brain Imaging Data Exchange I | ABIDE I |
| ABIDE | ABIDE-II | Autism Brain Imaging Data Exchange II | ABIDE II |
| ABIDE | ABIDE (I+II) | Autism Brain Imaging Data Exchange I and II | ABIDE I/II |
| ABRIM | ABRIM | Advanced Brain Imaging on Ageing and Memory |  |
| ADHD-200 | ADHD-200 | ADHD-200 Sample | ADHD 200 |
| ADDI | ADNI | Alzheimer's Disease Neuroimaging Initiative |  |
| ADNI | ADNI-ADS | Alzheimer's Disease Neuroimaging Initiative - Alzheimer's Disease Spectrum |  |
| ADNI | ADNI-3 | Alzheimer's Disease Neuroimaging Initiative 3 | ADNI3 |
| AIBL | AIBL | Australian Imaging, Biomarkers and Lifestyle Study of Ageing |  |
| ARWiBo | ARWiBo | Alzheimer's Disease Repository Without Borders |  |
| ASRB | ASRB | Australian Schizophrenia Research Bank | Australian Schizophrenia Research Bank |
| Aggressotype and MATRICS | Aggressotype and MATRICS | The dataset from EU-funded Aggressotype and MATRICS consortia |  |
| BHRCS | BHRCS | Brazilian High-Risk Cohort Study |  |
| BLISS | BLISS | Bipolar Lithium Imaging and Spectroscopy Study |  |
| CHUV | CHUV | Centre Hospitalier Universitaire Vaudois |  |
| CIFASD | CIFASD | Collaborative Initiative on Fetal Alcohol Spectrum Disorders |  |
| COBRE | COBRE | Center for Biomedical Research Excellence |  |
| Cam | Cam-CAN | Cambridge Centre for Ageing and Neuroscience | CamCAN |
| DEMGEN | DEMGEN | Norwegian Dementia Genetics Network |  |
| DIDA | DIDA-MDD | Disease Imaging Data Archiving - Major Depressive Disorder |  |
| DIRECT | DIRECT | DIRECT consortium |  |
| Depression | Depression-EEG | Depression-EEG |  |
| EMBARC | EMBARC | Establishing Moderators and Biosignatures of Antidepressant Response for Clinical Care |  |
| ENIGMA | ENIGMA | Enhancing NeuroImaging Genetics through Meta-Analysis |  |
| ENIGMA | ENIGMA-CHR-P | Enhancing NeuroImaging Genetics through Meta-Analysis - Clinical High Risk - Psychosis | ENIGMA CHR-P |
| EU | EU | European 16p11.2 consortium |  |
| Early | Early Stages of Schizophrenia | Early Stages of Schizophrenia |  |
| FCON | FCON | 1000 Functional Connectomes Project |  |
| GAP | GAP | the Genetics and Psychosis |  |
| GROUP | GROUP | Genetic Risk and Outcome of Psychosis |  |
| GSP | GSP | Brain Genomics Superstruct Project |  |
| HBN | HBN | Healthy Brain Network |  |
| HCP | HCP | Human Connectome Project |  |
| HCP | HCP-A | Human Connectome Project Aging | HCP Aging, HCP-A |
| HCP | HCP-B | Human Connectome Project Baby | HCP Baby |
| HCP | HCP-D | Human Connectome Project Development | HCP Development, HCP-D |
| HCP | HCP-EP | Human Connectome Project Early Psychosis | HCP Psychosis, HCPEP, HCP-EP |
| HCP | HCP-LS | Human Connectome Project Lifespan | HCP Lifespan |
| HCP | HCP-YA | Human Connectome Project Young Adult | HCP Young Adult, HCP-YA |
| IBCDR | IBCDR | International Big-Data Center for Depression Research |  |
| IDEAS | IDEAS | Imaging Database for Epilepsy and Surgery |  |
| IMpACT | IMpACT | International Multicenter persistent ADHD CollaboraTion |  |
| INsIDER | INsIDER | Imaging Axonal Damage & Repair in Multiple Sclerosis |  |
| IXI | IXI | Information eXtraction from Images dataset |  |
| Imperial APC | Imperial APC | The Imperial Amyloid PET Cohort |  |
| KQJH | KQJH | KQJH dataset |  |
| Knight | Knight ADRC-ADS | Knight Alzheimer's Disease Research Center |  |
| LEAP | LEAP | Longitudinal European Autism Project |  |
| LEMON | LEMON | Leipzig Study for Mind-Body-Emotion Interactions |  |
| Lausanne | Lausanne-ASD | Lausanne Autism Spectrum Disorder cohort | LausanneASD |
| MCAD | MCAD | Multicenter Alzheimer Disease Imaging Consortium |  |
| MCIC | MCIC | MIND Clinical Imaging Consortium |  |
| MIND | MIND-Set | MIND-Set study |  |
| MIPDB | MIPDB | Multimodal Resource for Studying Information Processing in the Developing Brain |  |
| MIRIAD | MIRIAD | Minimal Interval Resonance Imaging in Alzheimer's Disease |  |
| MSSEG2016 | MSSEG2016 | MICCAI MSSEG 2016 Challenge |  |
| NACC | NACC | National Alzheimer's Coordinating Center |  |
| NAKO | NAKO | German National Cohort (NAKO Gesundheitsstudie) |  |
| NIFD | NIFD | Neuroimaging in Frontotemporal Dementia |  |
| NIHPD | NIHPD | NIH MRI Study of Normal Brain Development | NIH MRI Study of Normal Brain Development |
| NIL | NIL | Louvain Neuroinflammation Imaging Lab |  |
| NIMHANS | NIMHANS | National Institute of Mental Health and Neurosciences |  |
| NKI | NKI | Nathan Kline Institute |  |
| NUSDAST | NUSDAST | Northwestern University Schizophrenia Data and Software Tool |  |
| OASIS | OASIS-1 | Open Access Series of Imaging Studies-1 | OASIS1 |
| OASIS | OASIS-3 | Open Access Series of Imaging Studies-3 | OASIS-3, OASIS3 |
| OPTiMiSE | OPTiMiSE | the Optimization of Treatment and Management of Schizophrenia in Europe |  |
| OpenBHB | OpenBHB | Open Brain Health Benchmark |  |
| OpenMSLong | OpenMSLong | OpenMS Longitudinal dataset |  |
| PNC | PNC | Philadelphia Neurodevelopmental Cohort |  |
| PPP | PPP | Personalized Parkinson Project |  |
| REST | REST-meta-MDD | REST-meta-MDD consortium |  |
| SAED | SAED | Shanghai Autism Early Developmental Cohort |  |
| STROKEMRI | STROKEMRI | Stroke MRI study |  |
| SVIP | SVIP | Simons VIP (Variation in Individuals Project) |  |
| StratiBip | StratiBip | Stratification of Bipolar Disorder |  |
| TractoInferno | TractoInferno | TractoInferno challenge |  |
| UCL | UCL | University College London |  |
| UCLA | UCLA-LA5c | University of California Los Angeles Consortium for Neuropsychiatric Phenomics LA5c Study | UCLA,  Consortium for Neuropsychiatric Phenomics (CNP) |
| UCLA | UCLA-NGD | UCLA 22Q11.2 CNV/NEUROGENETIC DATASET | UCLA |
| UK BIOBANK | UK BIOBANK | UK BIOBANK |  |
| UMICH | UMICH | UNIVERSITY OF MICHIGAN | UMICH |
| Utrecht | Utrecht | The dataset from Utrecht Schizophrenia project |  |
| devCCNP | devCCNP | developing Chinese Color Nest Project |  |
| TOP | TOP | Thematically Organized Psychosis |  |
| TOP | sTOP | Forensic Psychiatry study (sTOP) |  |
| TOP | uTOP | Youth-TOP study (uTOP) |  |
| femaleASD | femaleASD | Multimodal Developmental Neurogenetics of Females with ASD |  |
| MULTI-DATASET | MULTI-DATASET (UNSPECIFIED OR N>=10) | Multiple Datasets (Unspecified or N>=10) | Multi-dataset (N>=100), Multi-dataset (N=XX), Multi-dataset (100+ datasets), Multi-dataset (XX datasets) |
| No Special Dataset Name | MULTI-SITE (N>=2) | Multiple Sites (N>=2) | Multi-site (N=XX), Multi-site (XX sites), Two single-site |
| No Special Dataset Name | SINGLE-SITE | Single Site | Single-site |
| None (Pre-Trained NM) | None (Pre-Trained NM) | None (Pre-Trained Normative Model) | Rutherford 2022, Wolfers 2018, Zabihi 2019, Bethlehem 2022, Kia 2022, Kim 2024, Potvin 2022, Ge 2024, Segal 2023 |
| Unknown | Unknown | Unknown Dataset |  |

## 抽出結果 JSON の全体構造（例）

```json
{
  "paper_id": {
    "answer": "Example2024"
  },
  "disorders": [
    {
      "disorder-name": {
        "answer": "SCZ",
        "detail": "DSM-IV schizophrenia; clinical cohort",
        "confidence_rating": "High",
        "supporting_text": "\"All patients met DSM-IV criteria for schizophrenia.\"",
        "location": "Example2024.pdf.md: Methods / Participants, L40-L44"
      },
      "dataset-of-origin": {
        "answer": ["COBRE", "MCIC"],
        "detail": "Two public clinical datasets",
        "confidence_rating": "High",
        "supporting_text": "\"Participants were drawn from COBRE and MCIC.\"",
        "location": "Example2024.pdf.md: Methods / Datasets, L55-L58"
      },
      "age": {
        "answer": { "unit": "years", "min": 18, "max": 62, "mean": 35.40, "sd": 9.85 },
        "detail": "Overall clinical cohort",
        "confidence_rating": "High",
        "supporting_text": "\"Mean age was 35.4 years (SD 9.85), range 18–62.\"",
        "location": "Example2024.pdf.md: Results / Demographics, L70-L72"
      },
      "sex": {
        "answer": { "male_n": 70, "male_pct": 58.3, "female_n": 50, "female_pct": 41.7 },
        "detail": "Percentages calculated from counts",
        "confidence_rating": "Medium",
        "supporting_text": "\"70 males and 50 females.\"",
        "location": "Example2024.pdf.md: Results / Demographics, L73-L74"
      }
    }
  ]
}
```

Clinical Cohort への NM 適用が確認できない場合は、`disorders` を空配列 `[]` にする。
