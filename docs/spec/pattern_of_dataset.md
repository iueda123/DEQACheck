# Dataset (RCI1) 表現パターン調査

本調査は SummaryView が参照する Human JSON（`share_package/data/*/DE_v10/json/*human*.json`）中、
`reference_cohort_and_imaging_part.rci1_dataset_name.answer`（＝表の「Dataset」列）に入力されている文字列表現のバラエティを収集・要約したものです。

- 解析対象ファイル数: 108
- 非空の Dataset 値があるファイル数: 91
- 空（未入力）: 17

## 相談事項

SummaryView.javaが参照しているJSONファイルのDataset列に対応する要素の値について
表現のバラエティを調査してしてください。調査結果は ./spec/pattern_of_dataset.md にお願いします。

SummaryView.javaが参照しているJSONファイル自体に修正を施したいです。
Dataset列に対応する要素の値を正規化して欲しいです。
pattern_of_dataset.md を参考に書き換えてください。
share_package/data/**/DE_v10/json*/DE_*human*.json や share_package/data/**/DE_v10/json*/DE_*Human*.json についてのみ修正をお願いします。名前に
human や Human のつかないJSONファイルについては一切触らないでください。

## 主な傾向（要約）

- 単一データセット名の表記（例: `UK Biobank`, `EU-AIMS LEAP`, `OASIS-3`）。
- セミコロン区切りの複数列挙（例: `Cam-CAN; HCP; OASIS; PNC; UK Biobank`）。
- 同一データセットの略称・別表記・派生（例: `UKBB`/`UK Biobank`/`UKBiobank`、`CamCAN`/`Cam-CAN`、`OASIS3`/`OASIS-3`）。
- HCP 系の下位バリアント（`HCP Young Adult`/`HCP Aging`/`HCP Development`/`HCP-D` など）。
- ローカル/院内コホート記述（例: `Single-site cohort (First Affiliated Hospital ...)`、`In-house cohort ...`）。
- 「マルチサイト集約」を表す説明文＋代表例の長い列挙（例: `Multi-site aggregated dataset (...)`）。
- 他研究への参照（例: `See Rutherford2022`, `Same as Kia2023`）。
- 括弧による注記（`(controls)`, `(HC)`, `(S1200 release)` など）。

## 正規化に向けた指針（提案）

- セパレータ: `;` で分割し、各トークンを `trim()`。
- 同義語マッピング（例）:
    - `UKBB`, `UKBiobank` → `UK Biobank`
    - `CamCAN` → `Cam-CAN`
    - `OASIS3` → `OASIS-3`
    - `HCP-D` → `HCP Development`
    - `HCP-A`, `HCP Aging Lifespan` → `HCP Aging`
    - `HCP-YA`, `HCP Young Adults`, `HCP S1200` → `HCP Young Adult`
    - `NKI` → `NKI-Rockland`（必要なら `NKI-RS` に統一）
    - `LEAP` 系 → `EU-AIMS LEAP`（`AIMS-2-TRIALS` は注記へ）
- 括弧注記: `(HC)`, `(controls)`, `(S1200 release)` などは別フィールド（メタ情報）へ退避、またはサブタグ化。
- 「マルチサイト集約」: `^Multi-site aggregated dataset` で始まる記述は `Aggregated (multi-site)`
  とし、括弧内列挙は個別データセットに分解・正規化。
- 参照表現: `See X`, `Same as Y` は「参照: X/Y」とタグ化し、可能であれば当該研究の Dataset 正規化結果へリンク。

上記により `;` 区切りの各要素を「カノニカル名＋（任意注記）」に整理できます。

## ユニーク表現一覧（頻度順）

以下は非空値 91 件のユニーク表現と件数です（`count\tvalue`）。

```
5	UK Biobank
3	ABCD; CamCAN; CMI; CNP; FCON; HCP; HCPAG; HCPDV; HCPEP; IXI; NKI; OASIS3; OPN; PNC; TOP; UKBB
3	EU-AIMS LEAP
2	ABIDE I; ABIDE II
2	Multi-site aggregated dataset (87 datasets; ENIGMA Lifespan WG and collaborators)
2	See Rutherford2022
2	TractoInferno
1	A combination of 40 sites including ABCD, CAMCAN, CMI-HBN, HCP-Aging, HCP-Development, HCP-Early Psychosis, HCP-Young Adult, NKI-RS, OpenNeuro, PNC; UKBiobank, and etc.
1	A study-specific cohort from China Medical University.
1	ABCD; AOMIC; CAMCAN; CHBMP; CHCP; HCP-A; HCP-D; HCP-YA; PedsDTI; PING; PNC; QTAB; QTIM; SLIM; UKBB; ADNI3 (HC); OASIS3 (HC); PPMI (HC); HBN (HC)
1	ABIDE (Autism Brain Imaging Data Exchange)
1	ABIDE II (reference normative cohort); ABIDE I (application)
1	ABIDE-I; ABIDE-II; KQJH
1	ADNI
1	Adolescent Brain Cognitive Development (ABCD)
1	AMC; JNUH; COBRE; NMorphCH; MCIC; fBIRN; UCLA
1	Australian Schizophrenia Research Bank (ASRB)
1	Cam-CAN
1	Cam-CAN (Cambridge Centre for Ageing and Neuroscience)
1	Cam-CAN; HCP; OASIS; PNC; UK Biobank
1	CamCAN; UCLA Phenomics
1	CoRR; UCLA LA5c; COBRE
1	developing Chinese Color Nest Project (devCCNP)
1	DIDA-MDD
1	EMBARC; Depression-EEG; LEMON
1	EU-AIMS LEAP; ABIDE I; ABIDE II
1	EU-AIMS Longitudinal European Autism Project (LEAP)
1	EU-AIMS/AIMS-2-TRIALS Longitudinal European Autism Project (LEAP)
1	EU-funded Aggressotype and MATRICS consortia
1	FDOPA_HC01, FDOPA_HC02, FDOPA_01, FDOPA_02, FDOPA_03, FDOPA_04
1	HCP Development; HCP Young Adult; HCP Aging; HCP Early Psychosis (controls); UCLA LA5c (controls); ADNI (controls); OpenNeuro ds004302 (SCZ AH, controls)
1	HCP S1200; HCP Development Lifespan; HCP Aging Lifespan
1	HCP Young Adult; HCP Aging; HCP Development
1	HCP Young Adult; OpenBHB; OASIS-3; Cam-CAN; Newcastle University healthy adults (NCL); BLISS local healthy controls included for harmonization
1	HCP Young Adults; HCP Aging; UK Biobank; IXI
1	HCP-D
1	HCP-YA (Human Connectome Project Young Adults)
1	Healthy dataset (Peking University Center for MRI Research, HCtrain)
1	Human Connectome Project (HCP) S1200 release
1	Human Connectome Project (HCP); REST-meta-MDD Project
1	Human Connectome Project-Young Adult Sample; Human Connectome Project for Early Psychosis (HCP-psychosis); Australian Schizophrenia Research Bank (ASRB)
1	IMpACT (Dutch cohort)
1	In-house cohort from First Affiliated Hospital of Zhengzhou University
1	Local Community Cohort; Southwest University Adult Lifespan Dataset
1	Local single-site cohort (Beijing Tiantan Hospital)
1	Locally recruited children (Beijing); fcon_1000.projects dataset (IPCAS7); Child Connectome Project (CCNP); Children's Hospital Guangzhou
1	Locally recruited in Beijing; fcon_1000 projects dataset; Child Connectome Project (devCCNP); Guangzhou Women and Children's Medical Center
1	Longitudinal European Autism Project (LEAP)
1	Luna 1; Luna 2; PNC; Pitt
1	Multi-site aggregated dataset (82 sites); representative datasets include: ABCD; ABIDE; ADHD200; CAMCAN; CMI-HBN; HCP-Aging; HCP-Development; HCP-Early Psychosis; HCP-Young Adult; IXI; NKI-RS; Oasis; OpenNeuro; PNC; SRPBS; UKBiobank; University of Michigan studies; UC Davis; University of Oslo; King's College London; Amsterdam UMC
1	Multi-site aggregated dataset (ABCD; Cam-CAN; CNP; FCON-1000; HCP; OASIS3; PNC; TOP; UK Biobank). Site-matched adaptation dataset (n=20) from ICHT.
1	Multi-site aggregated dataset (ABIDE I; ABIDE II; ASRB; FEMS; MON; IMpACT-NL; KANMDD; MITASD; OCDPG; RUSMDD; SPAINOCD; TOP15; WASHASD; YoDA)
1	Multi-site aggregated dataset (ABIDE; ADHD200; AOMIC ID1000; Beijing Enhanced; CAMCAN; CoRR; DLBS; DS000119; DS000202; DS000222; Fcon1000; HBN; HCP; MPI Lemon; NKI-Rockland; OASIS-3; PING; SALD; SLIM; UK Biobank; and 100+ other studies)(Total 132)
1	Multi-site aggregated dataset (ENIGMA Schizophrenia Working Group: 20 sites from 13 countries including COBRE, ESO, FIDMAG, FOR2107, GAP, IGP, IMH, JBNU, MCIC, Montreal, MPRC, NU, Olin, RomeSL, RSCZ, SNUH, UCISZ, UMCU, UNINA, Zürich)
1	Multi-site aggregated dataset from 20 scan sites; representative sites include ABIDE-I/II, BGS, COBRE, Utrecht, AOMIC, CAMCAN, DLBS, IXI, NARRATIVES, SALD, OASIS, ROCKLAND, MADRID-ASD, MITASD, WASHASD.
1	Multi-site aggregated dataset: ABIDE I; ABIDE II; MITASD; WASHASD; IMpACT-NL; FEMS; TOP15; KANMDD; RUSMDD; YoDa; OCDPG; SPAINOCD; ASRB; MON
1	Multi-site aggregated dataset; 14 datasets including: Dataset 1 (OpenNeuro AHEPA AD), Dataset 2 (OSF TUM Chronic Pain), Dataset 3 (BASEL), Dataset 4 (OpenNeuro UNM PD), Dataset 5 (CREAPARK), Dataset 6 (turkeyAD), Dataset 7 (turkeyPDAD), Dataset 8 (lebAD2017), Dataset 9 (lebAD2019), Dataset 10 (University of Marseille HC), Dataset 11 (OSF University Hospital of Turku PD), Dataset 12 (NEMAR Wadsworth Center HC), Dataset 13 (OpenNeuro SRM), Dataset 14 (Zenodo Denmark Impaired Hearing)
1	Multi-site aggregated dataset; representative: ABIDE I; ABIDE II; BCP; CPMD; CBD; CCNP; HBN; HCPD; IBIS; PeriCBD; PING; Pixar
1	Multi-site aggregated dataset; representative: ADHD1000; BLISS; CamCAN; Chronotype; Greene-HM; HCP; MEGUK (multiple sites); NCL-dementia; NIMH-IHV; NKI; OASIS3; Stanford-CR; UCLH
1	NIH-funded MRI Study of Normal Brain Development; Hospital San Raffaele (HSR)
1	OASIS3
1	Philadelphia Neurodevelopmental Cohort (PNC)
1	REST-meta-MDD (DIRECT consortium)
1	REST-meta-MDD consortium
1	REST-meta-MDD Consortium
1	REST-meta-MDD database
1	Rutherford2022
1	SALD (Southwest University adult lifespan dataset)
1	Same as Kia2023
1	Single-center dataset from First Affiliated Hospital of Zhengzhou University
1	Single-center hospital dataset (Hefei, China; University of Science and Technology of China / Anhui Medical University)
1	Single-site cohort (First Affiliated Hospital of Zhengzhou University)
1	Thematically Organized Psychosis study (TOP)
1	TOP (Thematically Organized Psychosis) study
1	TOP study; Multi-site aggregated dataset for normative modeling
1	UCSF early psychosis clinic cohort; Human Connectome Project-Early Psychosis (HCP-EP)
1	UK Biobank; HCP Young Adult; HCP Development; AOMIC PIOP2; Duke Neurogenetics Study (DNS); MIND-Set (controls)
1	Utrecht Schizophrenia Project; Genetic Risk and Outcome of Psychosis (GROUP) consortium
1	Zhejiang Children's Hospital pediatric cohort (single-site)
```

## 代表的な正規化マッピング（案）

- UK Biobank 系: `UKBB`, `UKBiobank` → `UK Biobank`
- HCP 系: `HCP-YA`, `HCP Young Adults`, `HCP S1200` → `HCP Young Adult`; `HCP-D` → `HCP Development`; `HCP-A`/
  `HCP Aging Lifespan` → `HCP Aging`; `HCPEP`/`HCP-psychosis` → `HCP Early Psychosis`
- ABIDE 系: `ABIDE (Autism Brain Imaging Data Exchange)` → `ABIDE`; `ABIDE I/II` の混在は `ABIDE I` と `ABIDE II` に分割
- Cam-CAN 系: `CamCAN` → `Cam-CAN`; 補足 `(Cambridge Centre for Ageing and Neuroscience)` は注記へ
- OASIS 系: `OASIS3` → `OASIS-3`
- NKI 系: `NKI` → `NKI-Rockland (NKI-RS)`
- REST-meta-MDD 系: `REST-meta-MDD [database|consortium|DIRECT consortium]` → `REST-meta-MDD`＋注記
- TOP 系: `Thematically Organized Psychosis study (TOP)` → `TOP`
- ローカル/院内: `Local ...`, `Single-site ...`, `In-house ...` → `Local single-site cohort`＋施設名を注記へ
- 参照表現: `See X`, `Same as Y` → `Ref: X/Y`（別フィールドへ）

## 実装メモ（サマリ生成方法）

- `glob('share_package/data/*/DE_v10/json/*human*.json')` を走査。
- JSON から `reference_cohort_and_imaging_part.rci1_dataset_name.answer` を取得。
- 文字列が非空のものを集計し、ユニーク値・頻度を算出。
  -（本ファイルの一覧は 2025-12-14 時点の結果に基づく）

