# Data Extraction Guidance for Normative Modeling in Neuroimaging: A Systematic Review

-------------------

## プロンプト概要
あなたはプロのレビューアです。特にニューロイメージングにおける正規化モデリング（Normative Modeling）を扱った研究論文の質評価を行うことに長けています。この評価は、精神神経疾患とニューロイメージング研究の方法論的考察と応用に関するシステマティックレビューの一環として実施されます。

-------------------

## 評価対象のシステマティックレビュー概要（PICO）
* **P（Population）:** 精神神経疾患を有する参加者（統合失調症、うつ病、自閉症スペクトラム障害、認知症等）
* **I（Intervention/Exposure）:** ニューロイメージングデータ（MRI、PET、EEG、MEG等）への正規化モデリングの適用
* **C（Comparator）:** 従来の症例対照研究デザインや他の正規化モデリング手法（必要に応じて）
* **O（Outcome）:** 個人レベルの逸脱パターン、臨床的有用性、診断支援への応用等

-------------------

## 対象となる資料の所在

- 処理対象となる論文情報は `./materials` にあります。このフォルダにある情報すべてに基づいて次項の情報を抽出してください。
- 必要に応じてサブフォルダの内容も参照してください。

-------------------

## Assessment Items

### 1. Study Identification

#### 1-1. study_id
  * Unique identifier for each paper. Author name + year.
  * Data Type: string
  * Example: 
    * Rutherford2022

#### 1-2. reference_file_names
  * File names referenced in extracting this data
  * Data Type: string
  * Example: 
    * Rutherford2022.pdf.md; Rutherford2022_sup.pdf.md
  
#### 1-3. author_journal_year
  * "Author et al., Journal, Year" format
  * Data Type: string
  * Example: 
    * Rutherford et al., Communications Biology, 2022

#### 1-4. title
  * Paper title (as original)
  * Data Type: string

#### 1-5. doi
  * DOI (10.xxxx format, NR if absent)
  * Data Type: string



### 2. Assessment Items - Group A

#### 2-1. Clarity of Research Objectives

Were the research questions or objectives in this paper clearly stated?

**Assessment Criteria:**
- Specific application purposes of normative modeling (diagnostic support, personalized medicine, disease understanding, etc.) are clearly stated
- PICO elements (population, intervention/exposure, comparator, outcome) can be identified
- Research hypotheses or research questions are specifically stated


#### 2-2. Clear Definition of Target Population

Were the target psychiatric or neurological patient populations clearly defined?

**Assessment Criteria:**
- Diagnostic criteria for psychiatric/neurological disorders (DSM-5, ICD-11, etc.) are specified
- Definition of healthy control groups is clear (when applicable)
- Demographic characteristics such as age and sex are described


#### 2-3. Clarity of Inclusion and Exclusion Criteria

Were the inclusion and exclusion criteria pre-specified and uniformly applied to all participants?

**Assessment Criteria:**
- Clear inclusion criteria are described
- Clear exclusion criteria are described
- These criteria were uniformly applied to all participants
- Normative modeling-specific requirements (image quality, data completeness, etc.) are considered
- Subject numbers at each stage are clearly described (flow chart recommended)
- Exclusion reasons are specifically described
- Number of subjects included in final analysis is specified


#### 2-4. Validity of Normative Modeling Outcome Measures

Were the outcome measures from normative modeling (deviation scores, etc.) clearly defined, valid, and reliable?

**Assessment Criteria:**
- Brain measures used (cortical thickness, cortical area, subcortical volumes, connectivity measures, etc.) are clearly defined
- Methods for quantifying individual-level deviations through normative modeling are detailed
- Characteristics of the normative reference population are specified
- Interpretation methods for deviation scores are clear


#### 2-5. Handling of Confounding Variables

Were key confounding variables (age, sex, imaging site, etc.) measured and statistically adjusted?

**Assessment Criteria:**
- Basic confounding variables such as age and sex are considered
- For multi-site studies, methods for handling site effects are described
- Treatment of covariates in normative modeling is clear
- When harmonization methods are used, their details are described


#### 2-6. Clarity of Data Sources

Were the sources of neuroimaging data clearly described?

**Assessment Criteria:**
- Dataset names or study names used are specified
- Time and location of data acquisition are described
- For open datasets, specific database names (e.g., Human Connectome Project, ABCD Study, etc.) are specified


#### 2-7. Description of Image Acquisition Protocol

Were the MRI and other image acquisition protocols clearly described?

**Assessment Criteria:**
- Details of MRI sequences used (T1-weighted, DTI, fMRI, etc.) are described
- Imaging parameters (TR, TE, flip angle, resolution, etc.) are specified
- Scanner specifications (manufacturer, field strength, etc.) are described



### 3. Assessment Items - Group B


#### 3-1. Details of Data Preprocessing

Were the neuroimaging data preprocessing procedures clearly described?

**Assessment Criteria:**
- Preprocessing software used (FreeSurfer, FSL, SPM, etc.) is specified
- Each preprocessing step (skull stripping, normalization, segmentation, etc.) is detailed
- Quality control procedures are described


#### 3-2. Clarity of Data Partitioning Methods

Was it clearly described how data were partitioned into training, validation, and test sets?

**Assessment Criteria:**
- Data partition ratios (e.g., 70:15:15) are specified
- Partitioning methods (random, stratified sampling, etc.) are described
- When cross-validation is used, its details (K-fold, etc.) are described
- Measures to prevent data leakage are confirmed


#### 3-3. Details of Normative Modeling Approach

Were the statistical approaches used for normative modeling described in detail?

**Assessment Criteria:**
- Types of statistical models (Gaussian process regression, Bayesian models, linear regression, etc.) are specified
- Model hyperparameters and settings are described
- Software or tools used (PCNtoolkit, normative-modeling, etc.) are specified


#### 3-4. Details of Training Algorithm

Were the model training algorithms and optimization processes described in detail?

**Assessment Criteria:**
- Optimization algorithms (gradient descent, ADAM, L-BFGS, etc.) are specified
- Hyperparameter setting methods (grid search, Bayesian optimization, etc.) are described
- Convergence criteria and training termination conditions (number of epochs, loss function thresholds, etc.) are specified
- Use of regularization techniques (L1/L2 regularization, etc.) is described


#### 3-5. Model Performance Evaluation Metrics

Were the metrics for evaluating model performance clearly defined?

**Assessment Criteria:**
- Performance metrics used (MSLL, Pearson correlation, Spearman correlation, R², etc.) are specified
- Meaning and interpretation methods of each metric are explained
- Methods for evaluating statistical significance are described


#### 3-6. Implementation of Internal Validation

Was internal validation (performance evaluation on internal data) appropriately implemented?

**Assessment Criteria:**
- Evaluation on validation sets independent of training data was conducted
- Appropriate validation methods such as cross-validation were used
- Measures to detect and prevent overfitting were implemented


#### 3-7. External Data Validation

Was model validation on independent external datasets conducted?

**Assessment Criteria:**
- Evaluation on independent datasets not used for training was conducted
- Characteristics of external validation datasets are described
- Performance on external validation was compared and discussed with internal validation



#### 3-8. Description of Dataset Characteristics

Were the composition and characteristics of each dataset (training, validation, test) clearly described?

**Assessment Criteria:**
- Sample sizes for each dataset are specified
- Demographic characteristics (age distribution, sex ratio, patient/control group breakdown, etc.) are described
- Clinical characteristics (symptom severity scores, illness duration, comorbidities, medication status, etc.) are described
- Comparison of characteristics between datasets and assessment of balance are conducted
- Handling of missing data is described


#### 3-9. Performance Metrics and Statistical Uncertainty

Were model performance metrics and measures of statistical uncertainty clearly reported?

**Assessment Criteria:**
- Point estimates of key performance metrics are reported
- Confidence intervals or Bayesian credible intervals are reported
- P-values and statistical significance are appropriately reported


#### 3-10. Consideration for Reproducibility

Was there description of the availability of software, models, and data to ensure research reproducibility?

**Assessment Criteria:**
- Software versions used are specified
- Availability of code and scripts is mentioned
- Sharing of trained models is mentioned
- Data availability (including limitations if any) is described


#### 3-11. Interpretation Specific to Normative Modeling

Were the interpretation of individual-level deviation patterns and their clinical significance appropriately discussed?

**Assessment Criteria:**
- Meaning of individual-level deviation scores is clearly explained
- Clinical thresholds and decision criteria are discussed
- Differences and advantages compared to traditional case-control studies are discussed
- Prospects for clinical application are realistically discussed



### 4. Additional Comments
  * Important information not captured above. Describe any other important information not captured in the fields above.
  

-------------------

## 評価の基本原則

`2. Assessment Items - Group A`, `3. Assessment Items - Group B` については以下のルールに従って回答を作ってください。

1. 各質問には **回答カテゴリー**（「Yes」「Partial」「No」「NA」）を選んでください。
   - **Yes**: 明確に記載され、基準を満たしている。
   - **Partial**: 記載はあるが不十分または不完全。
   - **No**: 情報が存在しない、または利用できない。
   - **NA**: この研究には適用されない。
2. 各回答には **信頼度（Confidence Rating）** を付してください（「High」「Medium」「Low」）。
   - **High**: 文章中に明確で直接的な記載がある。
   - **Medium**: 間接的または限定的な証拠がある（例：補足資料に記載）。
   - **Low**: 記載が曖昧または不足しており判断が不確実。
3. 回答が「Partial」または「No」の場合は、標準化された **否定的根拠カテゴリー（Negative Answer Category）** を記入してください。
   - **Missing**: 必要な情報がどこにも記載されていない。
   - **Unclear/Incomplete**: 記載はあるが不明瞭または詳細が不足。
   - **Not Negative**: 主となる回答が「Yes」または「NA」のとき。
4. 「Yes」「Partial」「No」を選んだ場合は必ず以下を記載してください：
   - **Reason:** 判断の理由を段階的に説明
   - **Supporting Text:** 判断根拠とした原文の記述を抜粋
   - **Location:** 該当箇所のページ番号と行番号
5. 記述が不明確または記載がない場合は、保守的に評価し、多くの場合「No」を選択してください。

### 例

```
#### nm_vldtn_handle_ns
- Answer:
- Confidence Rating:
- Negative Answer Category: 
- Reason: 
- Supporting Text:
- Location:
```

-------------------

## Important Keyword Definitions
- **Explained Variance (EV)**: Indicates how well the model captures the central tendency of the data. Values closer to 1 indicate better model performance
- **Mean Standardized Log-Loss (MSLL)**: Evaluates both central tendency and variance fit of the model. More negative values indicate better model performance
- **Skewness**: Evaluates the shape of the distribution of deviation scores (z-scores) calculated by the model. Ideally close to 0
- **Kurtosis**: Also evaluates the shape of deviation score distribution. Ideally close to 0; values greater than 0 indicate "heavy tails"
- **Pearson Correlation Coefficient (RHO)**: Indicates the strength of linear relationship between observed and predicted values. Values closer to 1 indicate better model performance
- **dMRI**: Diffusion-weighted magnetic resonance imaging
- **sMRI**: Structural magnetic resonance imaging. For example, T1 weighted MRI, T2 weighted MRI
- **fMRI**: Functional magnetic resonance imaging
- **GAMLSS**: Generalized Additive Models for Location, Scale and Shape. A statistical modeling framework that allows modeling not only the mean (location) but also variance (scale) and shape parameters as functions of covariates. Example: log σ ~ s(age) estimates σ with splines assuming variance changes with age
- **HBR**: Hierarchical Bayesian Regression. A Bayesian modeling approach that incorporates hierarchical structure with random effects. Example: site-specific scale (random effects) estimates site-specific scale differences with hierarchical Bayesian random effects
- **GPR**: Gaussian Process Regression.
- **Nuisance Structure (NS)**: 研究の主目的ではないが観測値に系統的な影響を与える要因と、その影響の入り方（平均ずれ、分散の違い、相関構造、非線形性など）の総称。NM/脳画像での具体例: サイト・スキャナ・撮像プロトコル差（加法/乗法効果、階層分散）; 画質・モーション・SNR・QC指標（測定誤差や外れ値生成）; 年齢の非線形性、性別やICV（主目的でなければナイサンス）; 縦断・家族/同胞・クラスター（被験者内/サイト内相関）; 期間/バッチ/担当者差（ドリフト、バッチ効果）; ヘテロスケダスティシティ（分散が年齢/サイトで変わる）; 空間・時間自己相関、欠測機構の偏り
- **Locally Estimated Scatterplot Smoothing (LOESS)**: Locally Weighted Scatterplot Smoothing や  Local Polynomial Regression fitting procedure とも呼ばれる。

-------------------

## 抽出結果の出力
- ファイル形式
  - json形式。jsonファイルの構造は `./QA_Author20XX_by_Someone_2025XXXX.json`を真似てください。
- ファイル名
  - あなたがClaude Codeなら `QA_Bethlehem2022_by_claude_202509191115.json`のように「QA_AuthorYear_by_claude_YYYYMMDDhhdd.拡張子」としてください。
  - あなたがGemini CLIなら `QA_Bethlehem2022_by_gemini_202509191115.json`のように「QA_AuthorYear_by_gemini_YYYYMMDDhhdd.拡張子」としてください。
  - あなたがCodex-CLIなら `QA_Bethlehem2022_by_codex_202509191115.json`のように「QA_AuthorYear_by_claude_YYYYMMDDhhdd.拡張子」としてください。
- 出力先
  - `./QACheck/json/`

