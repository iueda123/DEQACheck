# Keywords for Normalization: Dataset Name

Version: 20260116

## 高松案の確認

122のNormative Modeling (NM) 研究における
Reference Cohort の Source Dataset や
Clinical Cohort の Source Dataset について、
高松案（Table 1 のReference cohort - Source列、Clinical cohort - Source列）では、
以下の、119個のキーワードで表現することが提案された。

* ABCCT
* ABCD
* ABIDE
* ABIDE I
* ABIDE I/II
* ABIDE II
* ABIL
* ABRIM
* ADHD 200
* ADNI
* ADNI-ADS
* ADNI3
* AIBL
* ARWiBo
* ASRB
* Aggressotype
* Australian Schizophrenia Research Bank
* BHRCS
* BLISS
* Bethlehem 2022
* CHUV
* CIFASD
* CNP
* COBRE
* Cam-CAN
* CamCAN
* DEMGEN
* DIDA-MDD
* DIRECT
* Depression-EEG
* EMBARC
* ENIGMA
* ENIGMA CHR-P
* EU
* Early Stages of Schizophrenia
* FCON
* GAP
* GROUP
* GSP
* Ge 2024
* HBN
* HCP
* HCP Aging
* HCP Baby
* HCP Development
* HCP Lifespan
* HCP Psychosis
* HCP Young Adult
* HCP-A
* HCP-D
* HCP-EP
* HCP-YA
* HCPEP
* IBCDR
* IDEAS
* IMpACT
* INsIDER
* IXI
* Imperial APC
* KQJH
* Kia 2022
* Kim 2024
* Knight ADRC-ADS
* LEAP
* LEMON
* LausanneASD
* MATRICS
* MCAD
* MCIC
* MIND-Set
* MIPDB
* MIRIAD
* MSSEG2016
* Multi-dataset (100+ datasets)
* Multi-dataset (XX datasets)
* Multi-site (XX sites)
* NACC
* NAKO
* NIFD
* NIH MRI Study of Normal Brain Development
* NIHPD
* NIL
* NIMHANS
* NKI
* NUSDAST
* OASIS-3
* OASIS1
* OASIS3
* OPTIMSE
* OpenBHB
* OpenMSLong
* PNC
* PPP
* Potvin 2022
* REST-meta-MDD
* Rutherford 2022
* Rutherford 2022 (sMRI)
* SAED
* STROKEMRI
* SVIP
* Segal 2023
* Single-site
* StratiBip
* TOP
* TractoInferno
* Two single-site
* UCL
* UCLA
* UK Biobank
* UK Biobank (IDPs)
* UK Biobank (Jacobians)
* Umich
* Utrecht
* Wolfers 2018
* Zabihi 2019
* devCCNP
* femaleASD
* sTOP
* uTOP

## 統一的なキーワードの提案

上記119個のキーワードには表記のブレや付帯情報の記述ルールが曖昧なため、
summary tableを作るのに不都合がある。
まずAI抽出結果も参考に、表記のブレを省いた統一的なキーワード案を作成する。

参照すべきAI抽出結果は、

* `share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_codex_<ProcessedTime>.json`
* `share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_claude_<ProcessedTime>.json`
* `share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_gemini_<ProcessedTime>.json`

というファイルの

* "reference_cohort_and_imaging_part/rci1_dataset_name/answer"
* "reference_cohort_and_imaging_part/rci1_dataset_name/supporting_text"
* "clinical_application_and_analysis_part/caa1_clinical_dataset/answer"
* "clinical_application_and_analysis_part/caa1_clinical_dataset/supporting_text"

の部分である。
このJSONファイル情報に基づき、
summary table用のキーワードとして適切となるように表記にブレがなく、
付帯情報の記述ルールが明確なデータセット名一覧を作らせた結果が以下である。

## Keywords for Normalization

以下では、summary table で使うデータセット名の表記揺れをなくすための「表記ルール」と
「正規化キーワード一覧」を提示する。

### 表記ルール

1. **公式略称を基本形とする**（例: `ABCD`, `ADNI`, `UK Biobank`）。
2. **バージョン/サブコホートはハイフンで接続**（例: `ABIDE-I`, `ADNI-3`, `HCP-A`）。
3. **派生データ種別は括弧で明示**（例: `UK Biobank (IDPs)`）。
4. **スペース/ハイフンの揺れを統一**（例: `Cam-CAN`, `ENIGMA-CHR-P`）。
5. **正式名称が不詳なコホートは原著論文の著者年で表現 `Author YYYY` 形式**（例: `Ge 2024`）。
6. **複数サイト/複数データセットは固定キーワード**で記述（例: `MULTI-DATASET (N>=100)`）。

* MULTI-DATASET (N>=100): 100 以上のデータセット/研究が明示されているもの。
* MULTI-DATASET (N=XX): 具体的なデータセット数が明記されているもの。
* MULTI-SITE (N=XX): 「XX sites」「XX scan sites」「複数スキャナ/施設」を明示しており、サイト数ベースでの統合であることが主題であるもの。
* SINGLE-SITE: 単一施設・単一スキャナでの収集と読める場合。
* TWO-SINGLE-SITE: 2つの独立した単施設コホート（discovery/replication）を明記。

### 正規化キーワード一覧

| Major Category Keyword | Minor Category                | Minor Category Full Spelling                                                           | Alias / variant examples                  | 該当論文                                                                                                                                                                                                                                                                                                                                              | Notes             |
|------------------------|-------------------------------|----------------------------------------------------------------------------------------|-------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------|
| ABCCT                  | ABCCT                         | Autism Biomarker Consortium for Clinical Trials Dataset                                |                                           | Ebadi2024                                                                                                                                                                                                                                                                                                                                         |                   |
| ABCD                   | ABCD                          | Adolescent Brain Cognitive Development Study                                           |                                           | Bethlehem2021, GarciaSanMartin2025, Ge2024, Kia2022, Loreto2024, Mendes2024, Rutherford2022, Rutherford2023, Verdi2023, Verdi2024, VillalonReina2024, Worker2023                                                                                                                                                                                  |                   |
| ABIDE                  | ABIDE                         | Autism Brain Imaging Data Exchange                                                     | ABIDE                                     | Bayer2022, Bedford2025, Bethlehem2020, Bethlehem2021, Chan2025A, Coupe2022, Echave2024, Floris2021, Ge2024, Ilioska2024, Jiang2024, Kim2023, Kim2024, Lee2025, Meijer2024, Mendes2024, Pinaya2019, Rutherford2022, Segal2023, Segal2025, Shan2022, Wang2023, Yu2024, Zhang2023                                                                    | ABIDE の相当期が不明な場合  |
| ABIDE                  | ABIDE-I                       | Autism Brain Imaging Data Exchange I                                                   | ABIDE I                                   |                                                                                                                                                                                                                                                                                                                                                   |                   |
| ABIDE                  | ABIDE-II                      | Autism Brain Imaging Data Exchange II                                                  | ABIDE II                                  |                                                                                                                                                                                                                                                                                                                                                   |                   |
| ABIDE                  | ABIDE (I+II)                  | Autism Brain Imaging Data Exchange I and II                                            | ABIDE I/II                                |                                                                                                                                                                                                                                                                                                                                                   | I/II を明示した統合表記    |
| ABRIM                  | ABRIM                         | Advanced Brain Imaging on Ageing and Memory                                            |                                           | Chan2025B                                                                                                                                                                                                                                                                                                                                         |                   |
| ADHD-200               | ADHD-200                      | ADHD-200 Sample                                                                        | ADHD 200                                  | Bethlehem2021, Mendes2024                                                                                                                                                                                                                                                                                                                         | ハイフンで統一           |
| ADDI                   | ADNI                          | Alzheimer's Disease Neuroimaging Initiative                                            |                                           | Bethlehem2021, Coupe2022, Feng2025, Huo2024, Janahi2022, Kim2023, Kim2024, Kumar2024, Kumar2025, Leenings2024, Pinaya2021, Verdi2023, Verdi2024, VillalonReina2024, Young2024                                                                                                                                                                     |                   |
| ADNI                   | ADNI-ADS                      | Alzheimer's Disease Neuroimaging Initiative - Alzheimer's Disease Spectrum             |                                           |                                                                                                                                                                                                                                                                                                                                                   |                   |
| ADNI                   | ADNI-3                        | Alzheimer's Disease Neuroimaging Initiative 3                                          | ADNI3                                     |                                                                                                                                                                                                                                                                                                                                                   |                   |
| AIBL                   | AIBL                          | Australian Imaging, Biomarkers and Lifestyle Study of Ageing                           |                                           | Bethlehem2021, Coupe2022, Kim2023, Kim2024, Leenings2024, Pinaya2021, Vieira2025                                                                                                                                                                                                                                                                  |                   |
| ARWiBo                 | ARWiBo                        | Alzheimer's Disease Repository Without Borders                                         |                                           | Pinaya2021                                                                                                                                                                                                                                                                                                                                        |                   |
| ASRB                   | ASRB                          | Australian Schizophrenia Research Bank                                                 | Australian Schizophrenia Research Bank    | DiBiase2022, GarciaSanMartin2025, Lv2021, Segal2023, Segal2025                                                                                                                                                                                                                                                                                    |                   |
| Aggressotype           | Aggressotype                  | Aggressotype                                                                           |                                           | Holz2023                                                                                                                                                                                                                                                                                                                                          |                   |
| BHRCS                  | BHRCS                         | Brazilian High-Risk Cohort Study                                                       |                                           | Mendes2024                                                                                                                                                                                                                                                                                                                                        |                   |
| BLISS                  | BLISS                         | Bipolar Lithium Imaging and Spectroscopy Study                                         |                                           | Little2024, Little2025                                                                                                                                                                                                                                                                                                                            |                   |
| CHUV                   | CHUV                          | Centre Hospitalier Universitaire Vaudois                                               |                                           | Gordaliza2024                                                                                                                                                                                                                                                                                                                                     |                   |
| CIFASD                 | CIFASD                        | Collaborative Initiative on Fetal Alcohol Spectrum Disorders                           |                                           | Gimbel2025                                                                                                                                                                                                                                                                                                                                        |                   |
| CNP                    | CNP                           | Consortium for Neuropsychiatric Phenomics                                              |                                           | Fang2024, GarciaSanMartin2025, Jia2024, Jia2025, Kia2022, Loreto2024, Rutherford2022, Rutherford2023, Verdi2023, Worker2023, Yang2025, Yu2024                                                                                                                                                                                                     |                   |
| COBRE                  | COBRE                         | Center for Biomedical Research Excellence                                              |                                           | Echave2024, Fang2024, Fang2025, Joo2024, Lamsma2024, OliveiraSaraiva2023, Rutherford2023, Vieira2025                                                                                                                                                                                                                                              |                   |
| Cam                    | Cam-CAN                       | Cambridge Centre for Ageing and Neuroscience                                           | CamCAN                                    | Bedford2025, Bethlehem2021, Echave2024, Fraza2023, Ge2024, Huo2024, Italinna2023, Janssen2024, Kia2022, Kim2023, Lawn2024, Lin2024, Little2024, Little2025, Loreto2024, Rutherford2022, Rutherford2023, Verdi2023, VillalonReina2024, Worker2023, Zabihi2019                                                                                      |                   |
| DEMGEN                 | DEMGEN                        | Norwegian Dementia Genetics Network                                                    |                                           | Kim2023, Kim2024                                                                                                                                                                                                                                                                                                                                  |                   |
| DIDA                   | DIDA-MDD                      | Disease Imaging Data Archiving - Major Depressive Disorder                             |                                           | Han2024B, Sun2023                                                                                                                                                                                                                                                                                                                                 |                   |
| DIRECT                 | DIRECT                        | DIRECT consortium                                                                      |                                           | Fang2024, Sun2025                                                                                                                                                                                                                                                                                                                                 |                   |
| Depression             | Depression-EEG                | Depression-EEG                                                                         |                                           | Fang2024, Jing2023, Lin2023, Segal2023, Tong2024                                                                                                                                                                                                                                                                                                  |                   |
| EMBARC                 | EMBARC                        | Establishing Moderators and Biosignatures of Antidepressant Response for Clinical Care |                                           | Tong2024                                                                                                                                                                                                                                                                                                                                          |                   |
| ENIGMA                 | ENIGMA                        | Enhancing NeuroImaging Genetics through Meta-Analysis                                  |                                           | Baldwin2022, Bethlehem2021, Ge2024, Gimbel2025, Gordaliza2024, Haas2024, Lamsma2024                                                                                                                                                                                                                                                               | サブコホート不明時         |
| ENIGMA                 | ENIGMA-CHR-P                  | Enhancing NeuroImaging Genetics through Meta-Analysis - Clinical High Risk - Psychosis | ENIGMA CHR-P                              |                                                                                                                                                                                                                                                                                                                                                   |                   |
| EU                     | EU                            | European 16p11.2 consortium                                                            |                                           | CardenasDeLaParra2019, Floris2021, Floris2024, Ge2024, Holz2023, Ilioska2024, Laidi2022, Looden2022, Segal2025, Zabihi2019, Zabihi2020                                                                                                                                                                                                            |                   |
| Early                  | Early Stages of Schizophrenia | Early Stages of Schizophrenia                                                          |                                           | Cirstian2024, DiBiase2022, FukamiGartner2023, Ge2024, Geng2025, Hua2025, Kia2022, RehakBuckova2025, Rutherford2022, Rutherford2023, Verdi2023, Worker2023, Young2024                                                                                                                                                                              |                   |
| FCON                   | FCON                          | 1000 Functional Connectomes Project                                                    |                                           | Kia2022, Kim2023, Loreto2024, Verdi2023, Worker2023                                                                                                                                                                                                                                                                                               |                   |
| GAP                    | GAP                           | the Genetics and Psychosis                                                             |                                           | Lamsma2024, Worker2023                                                                                                                                                                                                                                                                                                                            | Full Spelling 要調査 |
| GROUP                  | GROUP                         | Genetic Risk and Outcome of Psychosis                                                  |                                           | Janssen2021, Janssen2024                                                                                                                                                                                                                                                                                                                          |                   |
| GSP                    | GSP                           | Brain Genomics Superstruct Project                                                     |                                           | Ge2024, Ma2024                                                                                                                                                                                                                                                                                                                                    |                   |
| HBN                    | HBN                           | Healthy Brain Network                                                                  |                                           | Bedford2025, Ebadi2024, Kim2023, Laidi2022, Rutherford2022, Rutherford2023, VillalonReina2024, Yu2024                                                                                                                                                                                                                                             |                   |
| HCP                    | HCP                           | Human Connectome Project                                                               |                                           | Bethlehem2021, Cirstian2024, DiBiase2022, Fraza2023, FukamiGartner2023, Ge2024, Hua2025, Huang2024, Kia2022, Kim2023, Lee2025, Little2024, Little2025, Loreto2024, Mansour2025, Pinaya2019, Rutherford2022, Rutherford2023, Sampaio2025, Savage2024, Segal2023, Shao2024, Verdi2023, Vieira2025, VillalonReina2024, Worker2023, Young2024, Yu2024 | サブコホート不明時         |
| HCP                    | HCP-A                         | Human Connectome Project Aging                                                         | HCP Aging, HCP-A                          |                                                                                                                                                                                                                                                                                                                                                   | Aging             |
| HCP                    | HCP-B                         | Human Connectome Project Baby                                                          | HCP Baby                                  |                                                                                                                                                                                                                                                                                                                                                   | Baby              |
| HCP                    | HCP-D                         | Human Connectome Project Development                                                   | HCP Development, HCP-D                    |                                                                                                                                                                                                                                                                                                                                                   | Development       |
| HCP                    | HCP-EP                        | Human Connectome Project Early Psychosis                                               | HCP Psychosis, HCPEP, HCP-EP              |                                                                                                                                                                                                                                                                                                                                                   | Early Psychosis   |
| HCP                    | HCP-LS                        | Human Connectome Project Lifespan                                                      | HCP Lifespan                              |                                                                                                                                                                                                                                                                                                                                                   | Lifespan          |
| HCP                    | HCP-YA                        | Human Connectome Project Young Adult                                                   | HCP Young Adult, HCP-YA                   |                                                                                                                                                                                                                                                                                                                                                   | Young Adult       |
| IBCDR                  | IBCDR                         | International Big-Data Center for Depression Research                                  |                                           | Jing2023, Lin2023                                                                                                                                                                                                                                                                                                                                 |                   |
| IDEAS                  | IDEAS                         | Imaging Database for Epilepsy and Surgery                                              |                                           | Little2025                                                                                                                                                                                                                                                                                                                                        |                   |
| IMpACT                 | IMpACT                        | International Multicenter persistent ADHD CollaboraTion                                |                                           | Segal2023, Segal2025, Wolfers2020                                                                                                                                                                                                                                                                                                                 |                   |
| INsIDER                | INsIDER                       | Imaging Axonal Damage & Repair in Multiple Sclerosis                                   |                                           | Gordaliza2024                                                                                                                                                                                                                                                                                                                                     |                   |
| IXI                    | IXI                           | IXI dataset                                                                            |                                           | Bethlehem2021, Coupe2022, Echave2024, Janssen2024, Kia2022, Rutherford2022, Rutherford2023, Verdi2023, Vieira2025, Worker2023                                                                                                                                                                                                                     | Full Spelling 要調査 |
| Imperial               | Imperial APC                  | Imperial APC Cohort                                                                    |                                           | Loreto2024                                                                                                                                                                                                                                                                                                                                        | Full Spelling 要調査 |
| KQJH                   | KQJH                          | KQJH dataset                                                                           |                                           | Wang2023                                                                                                                                                                                                                                                                                                                                          | Full Spelling 要調査 |
| Knight                 | Knight ADRC-ADS               | Knight Alzheimer's Disease Research Center                                             |                                           | Kumar2024, Kumar2025                                                                                                                                                                                                                                                                                                                              |                   |
| LEAP                   | LEAP                          | Longitudinal European Autism Project                                                   |                                           | Floris2021, Floris2024, Ilioska2024, Laidi2022, Looden2022, Zabihi2019, Zabihi2020                                                                                                                                                                                                                                                                |                   |
| LEMON                  | LEMON                         | Leipzig Study for Mind-Body-Emotion Interactions                                       |                                           | Tong2024                                                                                                                                                                                                                                                                                                                                          |                   |
| Lausanne               | Lausanne-ASD                  | Lausanne Autism Spectrum Disorder cohort                                               | LausanneASD                               | Ebadi2024, Gordaliza2024                                                                                                                                                                                                                                                                                                                          |                   |
| MATRICS                | MATRICS                       | Measurement and Treatment Research to Improve Cognition in Schizophrenia               |                                           | Holz2023                                                                                                                                                                                                                                                                                                                                          |                   |
| MCAD                   | MCAD                          | Multicenter Alzheimer Disease Imaging Consortium                                       |                                           | Huo2024                                                                                                                                                                                                                                                                                                                                           |                   |
| MCIC                   | MCIC                          | MIND Clinical Imaging Consortium                                                       |                                           | GarciaSanMartin2025, Joo2024, Lamsma2024, Vieira2025                                                                                                                                                                                                                                                                                              |                   |
| MIND                   | MIND-Set                      | MIND-Set study                                                                         |                                           | Coupe2022, GarciaSanMartin2025, Joo2024, Savage2024, Vieira2025                                                                                                                                                                                                                                                                                   |                   |
| MIPDB                  | MIPDB                         | Multimodal Resource for Studying Information Processing in the Developing Brain        |                                           | Ebadi2024                                                                                                                                                                                                                                                                                                                                         |                   |
| MIRIAD                 | MIRIAD                        | Minimal Interval Resonance Imaging in Alzheimer's Disease                              |                                           | Coupe2022, Pinaya2021                                                                                                                                                                                                                                                                                                                             |                   |
| MSSEG2016              | MSSEG2016                     | MICCAI MSSEG 2016 Challenge                                                            |                                           | Gordaliza2024                                                                                                                                                                                                                                                                                                                                     |                   |
| NACC                   | NACC                          | National Alzheimer's Coordinating Center                                               |                                           | Bhome2024                                                                                                                                                                                                                                                                                                                                         |                   |
| NAKO                   | NAKO                          | German National Cohort (NAKO Gesundheitsstudie)                                        |                                           | Leenings2024                                                                                                                                                                                                                                                                                                                                      |                   |
| NIFD                   | NIFD                          | Neuroimaging in Frontotemporal Dementia                                                |                                           | Leenings2024                                                                                                                                                                                                                                                                                                                                      |                   |
| NIHPD                  | NIHPD                         | NIH MRI Study of Normal Brain Development                                              | NIH MRI Study of Normal Brain Development | CardenasDeLaParra2019                                                                                                                                                                                                                                                                                                                             | 公式略称に統一           |
| NIL                    | NIL                           | Louvain Neuroinflammation Imaging Lab                                                  |                                           | Gordaliza2024                                                                                                                                                                                                                                                                                                                                     |                   |
| NIMHANS                | NIMHANS                       | National Institute of Mental Health and Neurosciences                                  |                                           | Feng2024, Feng2025, VillalonReina2024                                                                                                                                                                                                                                                                                                             |                   |
| NKI                    | NKI                           | Nathan Kline Institute                                                                 |                                           | Bethlehem2021, Janssen2024, Kia2022, Kim2023, Leiberg2023, Little2025, Rutherford2022, Rutherford2023, Verdi2023, Worker2023                                                                                                                                                                                                                      |                   |
| NUSDAST                | NUSDAST                       | Northwestern University Schizophrenia Data and Software Tool                           |                                           | Pinaya2019                                                                                                                                                                                                                                                                                                                                        |                   |
| OASIS                  | OASIS-1                       | Open Access Series of Imaging Studies-1                                                | OASIS1                                    | Bethlehem2021, Coupe2022, Echave2024, Fraza2023, Janssen2024, Kia2022, Kim2023, Leenings2024, Little2024, Little2025, Loreto2024, Pinaya2021, Romascano2024, Rutherford2022, Rutherford2023, Verdi2023, Verdi2024, VillalonReina2024, Worker2023                                                                                                  |                   |
| OASIS                  | OASIS-3                       | Open Access Series of Imaging Studies-3                                                | OASIS-3, OASIS3                           |                                                                                                                                                                                                                                                                                                                                                   |                   |
| OPTiMiSE               | OPTIMSE                       | the Optimization of Treatment and Management of Schizophrenia in Europe                |                                           | なし                                                                                                                                                                                                                                                                                                                                                | Full Spelling 要調査 |
| OpenBHB                | OpenBHB                       | Open Brain Health Benchmark                                                            |                                           | Little2024                                                                                                                                                                                                                                                                                                                                        |                   |
| OpenMSLong             | OpenMSLong                    | OpenMS Longitudinal dataset                                                            |                                           | Gordaliza2024                                                                                                                                                                                                                                                                                                                                     |                   |
| PNC                    | PNC                           | Philadelphia Neurodevelopmental Cohort                                                 |                                           | Bethlehem2021, Fraza2023, Jalbrzikowski2019, Kia2022, Kim2023, Loreto2024, Parkes2021, Rutherford2022, Rutherford2023, Verdi2023, VillalonReina2024, Worker2023                                                                                                                                                                                   |                   |
| PPP                    | PPP                           | Personalized Parkinson Project                                                         |                                           | Chan2025B                                                                                                                                                                                                                                                                                                                                         |                   |
| REST                   | REST-meta-MDD                 | REST-meta-MDD consortium                                                               |                                           | Fang2025, Han2024B, Shao2024, Sun2025, Wu2023, Wu2024                                                                                                                                                                                                                                                                                             |                   |
| SAED                   | SAED                          | Shanghai Autism Early Developmental Cohort                                             |                                           | Geng2025                                                                                                                                                                                                                                                                                                                                          |                   |
| STROKEMRI              | STROKEMRI                     | Stroke MRI study                                                                       |                                           | Haukvik2025                                                                                                                                                                                                                                                                                                                                       |                   |
| SVIP                   | SVIP                          | Simons VIP (Variation in Individuals Project)                                          |                                           | CardenasDeLaParra2019                                                                                                                                                                                                                                                                                                                             |                   |
| StratiBip              | StratiBip                     | Stratification of Bipolar Disorder                                                     |                                           | Sampaio2025                                                                                                                                                                                                                                                                                                                                       |                   |
| TractoInferno          | TractoInferno                 | TractoInferno challenge                                                                |                                           | Feng2024, Feng2025                                                                                                                                                                                                                                                                                                                                |                   |
| UCL                    | UCL                           | University College London                                                              |                                           | Bhome2024, Fang2024, GarciaSanMartin2025, Joo2024, Lawn2024, Little2025, OliveiraSaraiva2023, VillalonReina2024, Young2024                                                                                                                                                                                                                        |                   |
| UCLA                   | UCLA                          | University of California, Los Angeles                                                  |                                           | Fang2024, GarciaSanMartin2025, Joo2024, Lawn2024, OliveiraSaraiva2023, VillalonReina2024, Young2024                                                                                                                                                                                                                                               |                   |
| UK Biobank             | UK Biobank                    | UK Biobank                                                                             |                                           | Bethlehem2021, Cirstian2024, Fraza2023, GarciaSanMartin2025, Georgiadis2024, Janahi2022, Kasper2024, Kia2022, Kim2023, Kobbersmed2025, Loreto2024, Pinaya2021, Rutherford2022, Rutherford2023, Savage2024, Verdi2023, Verdi2024, Vieira2025, VillalonReina2024, Worker2023                                                                        |                   |
| UK Biobank             | UK Biobank (IDPs)             | UK Biobank (Imaging-Derived Phenotypes)                                                | UK Biobank (IDPs)                         |                                                                                                                                                                                                                                                                                                                                                   | 派生種別を括弧表記         |
| UK Biobank             | UK Biobank (Jacobians)        | UK Biobank (Jacobians)                                                                 | UK Biobank (Jacobians)                    |                                                                                                                                                                                                                                                                                                                                                   | 派生種別を括弧表記         |
| UMich                  | UMich                         | University of Michigan                                                                 | Umich                                     | Rutherford2022, Rutherford2023                                                                                                                                                                                                                                                                                                                    |                   |
| Utrecht                | Utrecht                       | Utrecht cohort                                                                         |                                           | Echave2024, Janssen2021, Janssen2024, Zabihi2019                                                                                                                                                                                                                                                                                                  | Full Spelling 要調査 |
| devCCNP                | devCCNP                       | developing Chinese Color Nest Project                                                  |                                           | Jia2025, Yang2025                                                                                                                                                                                                                                                                                                                                 |                   |
| TOP                    | TOP                           | Thematically Organized Psychosis                                                       |                                           | Berthet2025, Haukvik2025, Kia2022, Kim2023, Kim2024, Loreto2024, Rutherford2022, Rutherford2023, Segal2023, Segal2025, Verdi2023, Wolfers2018, Wolfers2021, Worker2023                                                                                                                                                                            |                   |
| TOP                    | sTOP                          | Forensic Psychiatry study (sTOP)                                                       |                                           | Haukvik2025                                                                                                                                                                                                                                                                                                                                       |                   |
| TOP                    | uTOP                          | Youth-TOP study (uTOP)                                                                 |                                           | Haukvik2025                                                                                                                                                                                                                                                                                                                                       |                   |
| femaleASD              | femaleASD                     | Multimodal Developmental Neurogenetics of Females with ASD                             |                                           | Ebadi2024                                                                                                                                                                                                                                                                                                                                         |                   |
| None (Pre-Trained NM)  | Rutherford 2022               | Rutherford 2022                                                                        |                                           | なし                                                                                                                                                                                                                                                                                                                                                | 著者年形式             |
| None (Pre-Trained NM)  | Wolfers 2018                  | Wolfers 2018                                                                           |                                           | なし                                                                                                                                                                                                                                                                                                                                                | 著者年形式             |
| None (Pre-Trained NM)  | Zabihi 2019                   | Zabihi 2019                                                                            |                                           | なし                                                                                                                                                                                                                                                                                                                                                | 著者年形式             |
| None (Pre-Trained NM)  | Bethlehem 2022                | Bethlehem 2022                                                                         |                                           | なし                                                                                                                                                                                                                                                                                                                                                | 著者年形式             |
| None (Pre-Trained NM)  | Kia 2022                      | Kia 2022                                                                               |                                           | なし                                                                                                                                                                                                                                                                                                                                                | 著者年形式             |
| None (Pre-Trained NM)  | Kim 2024                      | Kim 2024                                                                               |                                           | なし                                                                                                                                                                                                                                                                                                                                                | 著者年形式             |
| None (Pre-Trained NM)  | Potvin 2022                   | Potvin 2022                                                                            |                                           | なし                                                                                                                                                                                                                                                                                                                                                | 著者年形式             |
| None (Pre-Trained NM)  | Model of "Ge 2024"            | Ge 2024                                                                                |                                           | なし                                                                                                                                                                                                                                                                                                                                                | 著者年形式             |
| None (Pre-Trained NM)  | Segal 2023                    | Segal 2023                                                                             |                                           | なし                                                                                                                                                                                                                                                                                                                                                | 著者年形式             |
| DATESET/SITE           | MULTI-DATASET (N>=100)        | Multi-dataset (N>=100)                                                                 | Multi-dataset (100+ datasets)             |                                                                                                                                                                                                                                                                                                                                                   |                   |
| DATESET/SITE           | MULTI-DATASET (N=XX)          | Multi-dataset (N=XX)                                                                   | Multi-dataset (XX datasets)               |                                                                                                                                                                                                                                                                                                                                                   |                   |
| DATESET/SITE           | MULTI-SITE (N=XX)             | Multi-site (N=XX)                                                                      | Multi-site (XX sites)                     |                                                                                                                                                                                                                                                                                                                                                   |                   |
| DATESET/SITE           | SINGLE-SITE                   | Single-site                                                                            | Single-site                               |                                                                                                                                                                                                                                                                                                                                                   |                   |
| DATESET/SITE           | TWO-SINGLE-SITE               | Two single-site                                                                        | Two single-site                           |                                                                                                                                                                                                                                                                                                                                                   |                   |

--------

## 該当論文の調査方法について

Table "該当論文" 列は以下のようにして調べた。

### 例 "ABCD"

* `share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_codex_<ProcessedTime>.json`
* `share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_claude_<ProcessedTime>.json`
* `share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_gemini_<ProcessedTime>.json`

というファイルの

* "reference_cohort_and_imaging_part/rci1_dataset_name/answer"
* "reference_cohort_and_imaging_part/rci1_dataset_name/supporting_text"
* "clinical_application_and_analysis_part/caa1_clinical_dataset/answer"
* "clinical_application_and_analysis_part/caa1_clinical_dataset/supporting_text"

要素の値を参照し、"ABCD" というキーワードを付与すべき論文を特定してください。

調査結果: `share_package/data/**/DE/json` の rci1/caa1 の answer/supporting_text で "ABCD" が確認できた論文は
Bethlehem2021, GarciaSanMartin2025, Ge2024, Kia2022, Loreto2024, Mendes2024, Rutherford2022, Rutherford2023, Verdi2023,
Verdi2024, VillalonReina2024, Worker2023。

-------

## "DATASET/SITE"

Major Category Keyword として "DATASET/SITE" が付与されうる研究として、高松案では以下が挙げられている。

* MULTI-DATASET (N>=100): Bethlehem2022, Kim2024
* MULTI-DATASET (N=XX): Bedford2025, Coupe2022, Echave2024, Elad2021, GarciaSanMartin2024, Ge2024, Janssen2025, Joo2023,
  Kia2022, Little2025, Rutherford2022, Rutherford2023, Savage2024, Segal2023, Segal2025, Tabbal2025, VillalonReina2024,
  Young2025, Yu2024
* MULTI-SITE (N=XX): Berthet2025, Giacomel2025, Huang2024, Jalbrzikowski2019, Jia2024, Jia2025, Shao2024
* SINGLE-SITE: Chien2022, DeMeo2019, FukamiGartner2023, Han2023, Hua2025, Italinna2023, Ji2023, Jia2024, Jia2025,
  Leiberg2023, Lin2024, Little2024, Liu2024, Mansour2025, Mao2025, Martin2025, Remiszewski2022, Thukral2024, Wen2025,
  Xiao2025, Yang2025, Yu2024, Zhang2022, Zheng2024
* TWO-SINGLE-SITE: Han2024A

これらに対して高松がどのような基準で MULTI-DATASET (N>=100), MULTI-DATASET (N=XX), MULITI-SITE (N=XX), SINGLE-SITE,
TWO-SINGLE-SITE という Minor Category Keywordを付与したかのルールを逆算した。

* `share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_codex_<ProcessedTime>.json`
* `share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_claude_<ProcessedTime>.json`
* `share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_gemini_<ProcessedTime>.json`

というファイルの

* "reference_cohort_and_imaging_part/rci1_dataset_name/answer"
* "reference_cohort_and_imaging_part/rci1_dataset_name/supporting_text"
* "clinical_application_and_analysis_part/caa1_clinical_dataset/answer"
* "clinical_application_and_analysis_part/caa1_clinical_dataset/supporting_text"

要素の値を参照しながら推測。

### 付与ルール

* MULTI-DATASET (N>=100)
    * 100 以上のデータセット/研究が明示されているもの。
    * 例：Bethlehem2022, Kim2024
* MULTI-DATASET (N=XX)
    * 具体的なデータセット数が明記されているもの。
    * 例: Bedford2025 (7 datasets), Ge2024 (87 datasets), Kia2022 (16 datasets), Tabbal2025 (14 studies), Yu2024 (11
      datasets), Segal2023/Segal2025 (14
      datasets)。
* MULTI-SITE (N=XX)
    * 「XX sites」「XX scan sites」「複数スキャナ/施設」を明示しており、サイト数ベースでの統合であることが主題であるもの。
    * 例: Berthet2025 (77 sites), Elad2021 (13 sites), Echave2024 (20 scan sites), Rutherford2022 (82 sites),
      Segal2023 (25 sites), Giacomel2025 (3 imaging
      sites + 複数スキャナ)。
* SINGLE-SITE
    * 単一施設・単一スキャナでの収集と読める場合。
    * 例: Chien2022 (National Taiwan University Hospital の単一スキャナ), Han2023/Liu2024/Wen2025 (First Affiliated
      Hospital of Zhengzhou Univ), Ji2023 (単一病院), Zhang2022 (
      Beijing Tiantan Hospital), Zheng2024 (First Affiliated Hospital of China Medical Univ)。
* TWO-SINGLE-SITE
    * 2つの独立した単施設コホート（discovery/replication）を明記。
    * 例: Han2024A（Zhengzhou Univ と UESTC の 2 コホート）。

--------

## "SINGLE-SITE" と "Author Year" の違いは？

Author Yearはすべて Pre-trained Normative Model を用いている研究に付与されている。

高松案では以下は "SINGLE-SITE" というキーワードが付与されている。これらはNew Normative Model。

* SINGLE-SITE: Chien2022, DeMeo2019, FukamiGartner2023, Han2023, Hua2025, Italinna2023, Ji2023, Jia2024, Jia2025,
  Leiberg2023, Lin2024, Little2024, Liu2024, Mansour2025, Mao2025, Martin2025, Remiszewski2022, Thukral2024, Wen2025,
  Xiao2025, Yang2025, Yu2024, Zhang2022, Zheng2024

高松案では以下は "Author Year" キーワードが付与されている。これらは Pre-trained Normative Model。

* Rutherford 2022 <- Bhome et al., 2024, Haukvik et al., 2025, Rehák Bučková et al., 2025, Remiszewski et al., 2022,
  Rutherford et al., 2023, Thukral et al., 2024, Verdi et al., 2024
* Wolfers 2018 <- Wolfers et al., 2021
* Zabihi 2019 <- Zabihi et al., 2020
* Bethlehem 2022 <- Garcia-SanMartín et al., 2024, Bedford et al., 2025
* Kia 2022 <- Loreto et al., 2024, Verdi et al., 2023, Worker et al., 2023
* Kim 2024 <- Kim et al., 2025
* Potvin 2022 <- Martin et al., 2025
* Ge 2024 <- Gimbel et al., 2025, Gordaliza et al., 2024, Haas et al., 2024
* Segal 2023 <- Segal et al., 2025

---------------------

## Author Year形式のキーワードが振られているものに、本当に正式名称はないのか？

### "Rutherford 2022" というキーワードで本当に集約してよいのか？

• Rutherford2022 で「公的データセット」として列挙されている正式名称は、share_package/data/Rutherford2022/materials/optimized/Rutherford2022.pdf.md
の記載（Materials and methods）から以下です。

- Adolescent Brain Cognitive Development Study (ABCD)
- Autism Brain Imaging Data Exchange (ABIDE)
- ADHD200
- Cambridge Centre for Ageing and Neuroscience (CamCAN / CAMCAN)
- Healthy Brain Network / Child Mind Institute Healthy Brain Network (CMI-HBN)
- Human Connectome Project – Aging (HCP-Aging)
- Human Connectome Project – Development (HCP-Development)
- Human Connectome Project – Early Psychosis (HCP-Early Psychosis)
- Human Connectome Project – Young Adult (HCP-Young Adult)
- IXI dataset (IXI)
- Nathan Kline Institute Rockland Sample (NKI-RS)
- Open Access Series of Imaging Studies (OASIS)
- OpenNeuro
- Philadelphia Neurodevelopmental Cohort (PNC)
- SRPBS (site acronymのため正式展開は本文からは不明)
- UK Biobank

出典箇所: share_package/data/Rutherford2022/materials/optimized/Rutherford2022.pdf.md（Materials and methods、Data from 82
sites…の段落）

もし SRPBS など略称の正式名まで特定したい場合は、Supplementary files 1–2 を確認する必要があります（本文で「詳細はSupplementary
files 1–2」と明記）。

###  

### "Ge 2024" というキーワードで本当に集約してよいのか？

「Gimbel2025, Gordaliza2024, Haas2024で用いられているNormative Modelは "Ge 2024" 由来である。」 という調査結果が正しいか確認を行ってください。

• 結論: その調査結果は正しくありません。「Ge 2024」はデータセット名ではなく、CentileBrain
の論文（規範モデル）の引用です。各研究で使っている“データセット名”は以下のとおりです。

- Gimbel2025: 参加者は CIFASD（Collaborative Initiative on FASD）由来で、規範モデルは CentileBrain（Ge et al.,
  2024）を使用しています。
    - share_package/data/Gimbel2025/materials/optimized/Gimbel2025.pdf.md:66 と
      share_package/data/Gimbel2025/materials/optimized/Gimbel2025.pdf.md:84
- Gordaliza2024: MS患者データは CHUV / NIL / INsIDER / MSSEG2016 / OpenMSLong から構成され、規範モデルは CentileBrain
  を使用しています。
    - share_package/data/Gordaliza2024/materials/optimized/Gordaliza2024.pdf.md:41
    - share_package/data/Gordaliza2024/materials/optimized/Gordaliza2024.pdf.md:49
    - share_package/data/Gordaliza2024/materials/optimized/Gordaliza2024.pdf.md:63
    - share_package/data/Gordaliza2024/materials/optimized/Gordaliza2024.pdf.md:75
- Haas2024: ENIGMA CHR-P Working Group の多施設データで、規範モデルは CentileBrain を使用しています。
    - share_package/data/Haas2024/materials/optimized/Haas2024.pdf.md:11
    - share_package/data/Haas2024/materials/optimized/Haas2024.pdf.md:69

--------

## "ENIGMA" という キーワードに集約されうるキーワード

例えば Lamsma2024 は ENIGMA のキーワードが付与されうるが、ENIGMAデータセットは Multi-site aggregated dataset であるため、
複数のキーワードがこのENIGMAのに集約されうる。

> Data came from the Schizophrenia Working Group of the Enhancing Neuroimaging Genetics through Meta-Analysis (ENIGMA)
> consortium. Twenty member sites contributed data to the current study. These sites were located in 13 different
> countries [[Lamsma2024]]

Claudeによる調査では、「COBRE, ESO, FIDMAG, FOR2107, GAP, IGP, IMH, JBNU, MCIC, Montreal, MPRC, NU, Olin, RomeSL, RSCZ,
SNUH, UCISZ, UMCU, UNINA, Zürich」の２０のデータセットが含まれる。

---------

##  "ABIDE-I" と "ABIDE-II" の違い

ABIDE-I は最初の公開データリリースで、ABIDE-II はその拡張版（参加サイト数・被験者数の増加、より幅広い年齢層や追加の臨床情報を含む）として位置付けられる。
一般に ABIDE-II は ABIDE-I の後続・補完データであり、別リリースとして区別される。



--------------------

## 補足（判定が割れうる例）

* Jia2024/Jia2025/Shao2024 は rci1 が複数データソース（4 つの DB など）で multi-dataset 相当だが、caa1 は単施設の患者コホート。Minor
  keyword を reference 側基準にするなら MULTI-DATASET/MULTI-SITE、clinical 側基準にするなら SINGLE-SITE になり得る。
* DeMeo2019 は rci1 が NIH+HSR の 2 ソース、caa1 は複数病院。rci1/caa1 のどちらを基準にするかで判定が変わる。
* GarciaSanMartin2024, Janssen2025 は該当 JSON 未検出、Joo2023 は rci1/caa1 が None で推測材料なし。
