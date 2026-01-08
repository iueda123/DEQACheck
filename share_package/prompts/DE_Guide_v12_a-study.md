# 神経画像におけるノルマティブモデリングのデータ抽出ガイダンス（システマティックレビュー）

-------------------

## プロンプト概要
あなたは神経画像のノルマティブモデリング研究からデータを抽出する熟練したレビューアです。このデータ抽出は、精神・神経疾患に対する神経画像の方法論的検討と応用に関するシステマティックレビューの一環として行われます。

-------------------

## 本レビューの目的

本システマティックレビューでは、神経画像および神経生理検査（例: MRI, PET, EEG, MEG）を用いたノルマティブモデリング研究を評価します。以下の3点を扱います:

  * 1. **モダリティ**: ノルマティブモデリングで用いられている計測技術は何か、それぞれどの程度使われているか。MRIのような一般的モダリティでは、どのシーケンス（例: T-weighted）が最も頻用されるか。
  * 2. **方法論**: モダリティをまたいでノルマティブモデリング研究はどのように設計・検証されているか。サンプルサイズ、共変量、前処理、統計モデル、ハーモナイゼーション手法、検証戦略などを抽出する。
  * 3. **臨床的スコープ**: どの精神・神経疾患がノルマティブモデリングで検討されているか、個人レベルの逸脱パターンや臨床的ユーティリティは何が報告されているか。

-------------------

## ファイル構成

```
<timestamp>/                          # カレントディレクトリ（ここで作業）
├── DE_Guide_v12_a-study.md           # このファイル（ガイド）
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
    - * **ADCSL_Style** = Answer, Detail, Confidence rating, Supporting text, Location
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

### NM2. Normative Modeling 2nd Part

#### NM2-1. Modeling Method
* Extraction Criteria:
Specify the statistical/machine learning algorithm(s) used for normative modeling.
以下の Keyword から該当するものを選んで回答してください（複数回答可）。

| Keyword | このキーワードに該当する条件や例 |
|---------|-------------------------------|
| GLM-family | GLM, OLSR |
| Additive models | GAM, GAMM, GAMLSS, LOESS, LMS |
| Polynomial | POLY (linear/quadratic/cubic; model selection) |
| Quantile | QUANTREG (percentile-based NM) |
| Fractional polynomial | MFPR |
| Gaussian process | GPR |
| Bayesian linear | BLR, WBLR |
| Hierarchical Bayesian | HBR, HBLM, HBGPM |
| Mixed-effects | MEM, LMM (voxel/ROI) |
| Nearest neighbor | N3, NNA (density-based), EXP-WEIBULL (NN→likelihood) |
| Kernel regression | KERNEL-NW (Nadaraya–Watson) |
| Moving average | MOV-AVG |
| Tolerance intervals | TOL-INT (nonparametric) |
| Z-score baseline | ZSCORE (mean/SD only) |
| Autoencoder family | AE (plain/denoising/semi-supervised), AAE, VAE, ConVAE, VQ-VAE, mmVAE, mmSIVAE |
| Deep generative | GPT (if used), FUNCOIN |
| Similarity-based | PBSI |
| Vector factorization | NMF (if present), PCA (if appears) |
| Unknown/unspecified | PCN-UNSPEC (PCNtoolkit/nispat/nomis unspecified), UNKNOWN/NR (Yes/NR/missing) |


* Extraction Result Style: ADCSL_Style
* "answer" example:

```json
"answer": [
  "Similarity-based", 
  "Autoencoder family"
]
```

#### NM2-2. Response Variable
* Extraction Criteria: Specify the imaging-derived variable(s) being modeled.
以下の Keyword から該当するものを選んで回答してください（複数回答可）。

| Keyword                 | このキーワードに該当する条件や例                                                                                                                                                                                      | 
|-------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| CT                      | cortical thickness                                                                                                                                                                                    |
| CV                      | cortical volume                                                                                                                                                                                       |
| SA                      | surface area                                                                                                                                                                                          |
| GI                      | gyrification index                                                                                                                                                                                    |
| Core morphometry        | CT, CV, SA, SV, GMV, WMV, GBV, TIV/ICV, WMH, CSF                                                                                                                                                      |
| ALFF family             | ALFF (raw low-frequency power), fALFF (low-frequency power / total power)                                                                                                                             |
| Diffusion scalars       | FA, MD, RD, AD, GFA, FAt, FW                                                                                                                                                                          |
| DTI tract profile       | MD, RD, AD, DTI-bundle-profile (along-tract shape + FA/MD/RD/AD)                                                                                                                                      |
| Connectivity            | FC, rs-FC, dyn-FC (aFCS/fFCS/FC-variability), FC-gradient, FCS, DC, GCOR, LCOR                                                                                                                        |
| Motion                  | FD                                                                                                                                                                                                    |
| PET/SPECT               | BPND, Ki_cer, SUVR-amyloid (e.g., AV45), SUVR-tau (e.g., FTP)                                                                                                                                         |
| Task fMRI               | TASK-GLM (contrast z/t-map)                                                                                                                                                                           |
| Deformation             | DBM/TBM (Jacobian determinants), VQ-typicality (VQ-VAE typicality score)                                                                                                                              |
| Folding/Similarity      | MSI (morphometric similarity index), PBSI-SW (sulcal width similarity), GI/CURV (gyrification/curvature metrics)                                                                                      |
| Laterality              | LI (laterality indices for CT/GMV/WMV/FC/others)                                                                                                                                                      |
| MEG/EEG power           | PSD (band power)                                                                                                                                                                                      |
| MEG/EEG connectivity    | AEC/PEC (frequency-specific FC)                                                                                                                                                                       |
| Ophthalmic              | RETINA (macular/RNFL/GC-IPL thickness)                                                                                                                                                                |
| Region-specific volumes | CER-Lobule (cerebellar lobules), THAL-Nuclei (thalamic nuclei), HIPPO (hippocampus), CC-morpho (corpus callosum volume/area/length/perimeter), SV-regional (generic subcortical volumes by atlas/ROI) |
| IDP sets                | IDP-set (bulk feature sets, e.g., 2000+ IDPs)                                                                                                                                                         |
| Model components        | NMF (component weights)                                                                                                                                                                               |
| Unknown                 | UNKNOWN/NR (missing or non-informative answers)                                                                                                                                                       |

* Extraction Result Style: ADCSL_Style
* "answer" 例:

```json
"answer": [
  "Core morphometry", Structural normative model repository (~58k subjects)",
  "Functional normative model multi-site dataset (~40 sites, overall 21,594 HC across train/test)",
  "HCP Young Adult",
  "COBRE",
  "UMich SchizGaze",
  "OpenNeuro ds000243/ds002843/ds003798"
]
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
   - **Medium**: Indirect or limited evidence (e.g., in supplementary materials, tables, or referenced papers); requires inference or computation from provided data.
   - **Low**: Ambiguous or insufficient description leading to uncertainty; conflicting information; or reliance on assumptions.

3. **Reason**: Provide a step-by-step explanation of how you arrived at the answer and confidence rating. Explain what information was available, how you interpreted it, and any computations or inferences made. If you computed values (e.g., percentages, pooled SD), state the formula briefly.
   - When extraction criteria do not apply, provide reason explaining why it does not apply.

4. **Supporting Text**: Provide direct excerpts from the source materials that support your answer. Use quotation marks and ellipses (...) for omitted text.
    - When extraction criteria do not apply, provide "-".

5. **Location**: Specify where the supporting text was found using the document structure. Prefer the most specific locator available.
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

1. **Answer**: The extracted information according to the extraction criteria (typically a categorical or short textual answer).

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

#### Example: A_Style

```json
{
  "rci8_quality_checking": "Yes"
}
```

-------------------------

## 抽出結果の出力
- ファイル形式
    - JSON。`./DE_v12_by_Someone_Author20XX_YYYYmmddHHMMSS.json` の構造に合わせる。
- ファイル名は `<DE version>_by_<Agent Name>_<AuthorYear>_<Processing Date>.json`
    - Claude Code の場合: `DE_v12_by_claude_Bethlehem2022_202509191115.json`
    - Gemini CLI の場合: `DE_v12_by_gemini_Bethlehem2022_202509191115.json`
    - Codex-CLI の場合: `DE_v12_by_codex_Bethlehem2022_202509191115.json`
    - ファイル名はASCII、空白なし。
- JSONフォーマット
    - キーは指定通り、item IDは snake_case。
    - Answer は混在内容なら文字列。純粋な数値は数値/文字列いずれでもよいが、ファイル内で一貫させる。
    - 末尾カンマ禁止、有効なJSONにする。
