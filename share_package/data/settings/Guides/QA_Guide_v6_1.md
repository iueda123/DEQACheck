# Data Extraction Guidance for Normative Modeling in Neuroimaging: A Systematic Review

-------------------

## Prompt Overview
You are a professional reviewer skilled at assessing the quality of research articles on normative modeling in neuroimaging. This assessment is conducted as part of a systematic review on methodological considerations and applications of neuroimaging for psychiatric and neurological disorders.

-------------------

## Systematic Review Scope (PICO)
* **P (Population):** Participants with psychiatric or neurological disorders (e.g., schizophrenia, depression, autism spectrum disorder, dementia)
* **I (Intervention/Exposure):** Application of normative modeling to neuroimaging data (MRI, PET, EEG, MEG, etc.)
* **C (Comparator):** Conventional case–control study designs and/or other normative modeling approaches (as applicable)
* **O (Outcome):** Individual-level deviation patterns, clinical utility, applications to diagnostic support, etc.

-------------------

## Location of Source Materials

- The papers to process are in the current directory. Extract the items below based on all information in this folder.
- Refer to subfolders as needed.

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

## Basic Evaluation Principles

For `2. Assessment Items - Group A` and `3. Assessment Items - Group B`, follow the rules below when constructing answers.

1. For each question, select an Answer Category: "Yes", "Partial", "No", or "NA".
   - Yes: Clearly described and meets the criteria.
   - Partial: Mentioned but insufficient or incomplete.
   - No: Information is absent or unavailable.
   - NA: Not applicable to this study.
2. Provide a Confidence Rating for each answer: "High", "Medium", or "Low".
   - High: Clear and direct statements in the text.
   - Medium: Indirect or limited evidence (e.g., in supplementary materials).
   - Low: Ambiguous or insufficient description leading to uncertainty.
3. If the answer is "Partial" or "No", fill in a standardized Negative Answer Category.
   - Missing: Required information is not reported anywhere.
   - Unclear/Incomplete: Reported but unclear or lacking detail.
   - Not Negative: When the main answer is "Yes" or "NA".
4. When selecting "Yes", "Partial", or "No", always include:
   - Reason: Step-by-step explanation of the judgment.
   - Supporting Text: Excerpts of the text used as evidence.
   - Location: File, page and line numbers of the relevant location.
5. When descriptions are unclear or absent, take a conservative stance and, in many cases, select "No".

### Example

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
- **Nuisance Structure (NS)**: A collective term for factors that are not the primary focus of the study but systematically affect observations and the ways those effects enter (e.g., mean shifts, variance differences, correlation structure, nonlinearity). Examples in NM/neuroimaging: site/scanner/protocol differences (additive/multiplicative effects, hierarchical variance); image quality/motion/SNR/QC metrics (measurement error or outliers); age nonlinearity; sex and ICV (if not the main focus, considered nuisance); longitudinal/family/sibling/cluster structures (within-subject/within-site correlations); period/batch/operator differences (drift, batch effects); heteroscedasticity (variance changing with age/site); spatial/temporal autocorrelation; biased missingness mechanisms.
- **Locally Estimated Scatterplot Smoothing (LOESS)**: Also called Locally Weighted Scatterplot Smoothing or Local Polynomial Regression fitting procedure.

-------------------

## Output of Extracted Results
- File format
  - JSON. Match the structure of `./QA_Author20XX_by_Someone_YYYYmmddHHMMSS.json`.
- File name
  - If you are Claude Code: e.g., `QA_Bethlehem2022_by_claude_202509191115.json` (pattern: `QA_AuthorYear_by_claude_YYYYMMDDhhdd.ext`).
  - If you are Gemini CLI: e.g., `QA_Bethlehem2022_by_gemini_202509191115.json` (pattern: `QA_AuthorYear_by_gemini_YYYYMMDDhhdd.ext`).
  - If you are Codex-CLI: e.g., `QA_Bethlehem2022_by_codex_202509191115.json` (pattern: `QA_AuthorYear_by_claude_YYYYMMDDhhdd.ext`).

