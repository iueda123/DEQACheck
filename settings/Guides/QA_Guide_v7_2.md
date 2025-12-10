# Quality Assessment Guidance for Normative Modeling in Neuroimaging: A Systematic Review

-------------------

## Prompt Overview
You are a professional reviewer skilled at assessing the quality of research articles on normative modeling in neuroimaging. This assessment is conducted as part of a systematic review on methodological considerations and applications of neuroimaging for psychiatric and neurological disorders.

-------------------

## Our Systematic Review Objectives

This systematic review will evaluate normative modeling studies that use neuroimaging and neurophysiological tests (e.g., MRI, PET, EEG, MEG). The review addresses three questions:

  * 1. **Modality**: Which measurement techniques have been applied in normative modeling, and how often is each modality used? For common modalities like MRI, what specific sequences (e.g., T-weighted) are most frequently utilized?
  * 2. **Methodology**: How are normative modeling studies designed and validated across modalities? We will extract information on sample size, covariates, image preprocessing, statistical models, harmonization procedures, and validation strategies.
  * 3. **Clinical scope**: Which psychiatric or neurological conditions have been investigated
with normative modeling, and what individual-level deviation patterns or clinical
utilities have been reported?

-------------------

## Location of Source Materials

- The papers to process are in the current directory. Extract the items below based on all information in this folder.
- Refer to subfolders as needed.

-------------------

## Quality Assessment Items

**Assessment Result Style**
  * A_Style = answer only style
  * ACRSL_Style = answer, confidence rating, reason, supporting text, and location style


### CM. Common Part

#### CM-1. Study ID (v6_1-1)
  * Unique identifier for each paper. Author name + year.
  * Example: 
    * Rutherford2022
  * Assessment Result Style: A_Style


#### CM-2. Reference File Names (v6_1-2)
  * File names referenced in extracting this data
  * Example: 
    * Rutherford2022.pdf.md; Rutherford2022_sup.pdf.md
  * Assessment Result Style: A_Style

  
#### CM-3. author_journal_year (v6_1-3)
  * "Author et al., Journal, Year" format
  * Example: 
    * Rutherford et al., Communications Biology, 2022
  * Assessment Result Style: A_Style


#### CM-4. Title (v6_1-4)
  * Paper title (as original)
  * Assessment Result Style: A_Style


#### CM-5. DOI (v6_1-5)
  * DOI (10.xxxx format, NR if absent)
  * Assessment Result Style: A_Style


#### CM-6. Clarity of Research Objectives (v6_2-1; N-1)

Were the research questions or objectives in this paper clearly stated?

  * Assessment Criteria:
    - [ ] Research hypotheses or research questions are specifically stated
    - [ ] Specific application purposes of normative modeling (diagnostic support, personalized medicine, disease understanding, etc.) are clearly stated

  * Assessment Result Style: ACRSL_Style

#### CM-7. Additional Comments (v6_4)

Important information not captured above. Describe any other important information not captured in the fields above.

  * Assessment Result Style: A_Style



### NM. Normative Modeling Part

#### NM-1. Clear Definition of Target Population (v6_2-2; N-2)

Were the target psychiatric or neurological patient populations clearly defined?

  * Assessment Criteria: 
    * When using a newly collected dataset
      - [ ] Demographic characteristics such as age and sex are described
      - [ ] Diagnostic criteria for psychiatric/neurological disorders (DSM-5, ICD-11, etc.) are specified
    *  When using an existing dataset
      - [ ] Demographic characteristics of the utilized dataset are specified
      - [ ] Reasons for selecting the utilized dataset are specified
  
  * Assessment Result Style: ACRSL_Style


#### NM-2. Clarity of Inclusion and Exclusion Criteria (v6_2-3; N-4, N-8)

Were the inclusion and exclusion criteria pre-specified and uniformly applied to all participants?

  * Assessment Criteria: 
    * When using a newly collected dataset
      - [ ] Clear inclusion criteria are described
      - [ ] Clear exclusion criteria are described
      - [ ] These criteria were uniformly applied to all participants
      - [ ] Normative modeling-specific requirements (image quality, data completeness, etc.) are considered
      - [ ] Number of subjects included in final analysis is specified    
    * When using an existing dataset
      - [ ] Normative modeling-specific requirements (image quality, data completeness, etc.) are considered
      - [ ] Exclusion reasons are specifically described
      - [ ] Number of subjects included in final analysis is specified

  * Assessment Result Style: ACRSL_Style


#### NM-3. Handling of Confounding Variables (v6_2-5; N-14)

Were key confounding variables (age, sex, imaging site, etc.) measured and statistically adjusted?

  * Assessment Criteria: 
    - [ ] Basic confounding variables such as age and sex are considered
    - [ ] For multi-site studies, methods for handling site effects are described
    - [ ] Treatment of covariates in normative modeling is clear
    - [ ] When harmonization methods are used, their details are described

  * Assessment Result Style: ACRSL_Style


#### NM-4. Clarity of Data Sources (v6_2-6; C-7)

Were the sources of neuroimaging data clearly described?

  * Assessment Criteria: 
    * When using a newly collected dataset
      - [ ] Dataset names or study names used are specified
      - [ ] Time and location of data acquisition are described
    * When using an existing dataset
      - [ ] Dataset names or study names used are specified
      - [ ] For open datasets, specific database names (e.g., Human Connectome Project, ABCD Study, etc.) are specified

  * Assessment Result Style: ACRSL_Style


#### NM-5. Description of Image Acquisition Protocol (v6_2-7; C-13)

Were the MRI and other image acquisition protocols clearly described?

  * Assessment Criteria: 
    * When using a newly collected dataset
      - [ ] Details of MRI sequences used (T1-weighted, DTI, fMRI, etc.) are described
      - [ ] Imaging parameters (TR, TE, flip angle, resolution, etc.) are specified
      - [ ] Scanner specifications (manufacturer, field strength, etc.) are described
    * When using an existing dataset
      - Answer "NA".

  * Assessment Result Style: ACRSL_Style


#### NM-6. Details of Data Preprocessing (v6_3-1; C-9)

Were the neuroimaging data preprocessing procedures clearly described?

  * Assessment Criteria: 
    * When building a new model
      - [ ] Preprocessing software used (FreeSurfer, FSL, SPM, etc.) is specified
      - [ ] Each preprocessing step (skull stripping, normalization, segmentation, etc.) is detailed
      - [ ] Quality control procedures are described
    * When using an existing model
      - Answer "NA".
  
  * Assessment Result Style: ACRSL_Style

  
#### NM-7. Clarity of Data Partitioning Methods (v6_3-2; C-19)

Was it clearly described how data were partitioned into training, validation, and test sets?

  * Assessment Criteria: 
    * When building a new model
      - [ ] Data partition ratios (e.g., 70:15:15) are specified
      - [ ] Partitioning methods (random, stratified sampling, etc.) are described
      - [ ] When cross-validation is used, its details (K-fold, etc.) are described
      - [ ] Measures to prevent data leakage are confirmed
    * When using an existing model
      - Answer "NA".

  * Assessment Result Style: ACRSL_Style


#### NM-8. Details of Normative Modeling Approach (v6_3-3; C-22)

Were the statistical approaches used for normative modeling described in detail?

  * Assessment Criteria: 
    * When building a new model
      - [ ] Types of statistical models (Gaussian process regression, Bayesian models, linear regression, etc.) are specified
      - [ ] Model hyperparameters and settings are described
      - [ ] Software or tools used (PCNtoolkit, normative-modeling, etc.) are specified
    * When using an existing model
      - Answer "NA".

  * Assessment Result Style: ACRSL_Style


#### NM-9. Details of Training Algorithm (v6_3-4; C-25)
  
Were the model training algorithms and optimization processes described in detail?

  * Assessment Criteria: 
    * When building a new model
      - [ ] Optimization algorithms (gradient descent, ADAM, L-BFGS, etc.) are specified
      - [ ] Hyperparameter setting methods (grid search, Bayesian optimization, etc.) are described
      - [ ] Convergence criteria and training termination conditions (number of epochs, loss function thresholds, etc.) are specified
      - [ ] Use of regularization techniques (L1/L2 regularization, etc.) is described
    * When using an existing model
      - Answer "NA".

  * Assessment Result Style: ACRSL_Style


#### NM-10. Model Performance Evaluation Metrics (v6_3-5, v_3-9; C-28, C-37)

Were the metrics for evaluating model performance clearly defined?

  * Assessment Criteria: 
    * When building a new model
      - [ ] Performance metrics used (MSLL, Pearson correlation, Spearman correlation, R², etc.) are specified
      - [ ] Meaning and interpretation methods of each metric are explained
      - [ ] Methods for evaluating statistical significance are described    
      - [ ] Point estimates of key performance metrics are reported
      - [ ] Confidence intervals or Bayesian credible intervals are reported
      - [ ] P-values and statistical significance are appropriately reported
    * When using an existing model
      - Answer "NA".

  * Assessment Result Style: ACRSL_Style



#### NM-11. Implementation of Internal Validation (v6_3-6; C-32)

Was internal validation (performance evaluation on internal data) appropriately implemented?

  * Assessment Criteria: 
    * When building a new model
      - [ ] Evaluation on validation sets independent of training data was conducted
      - [ ] Appropriate validation methods such as cross-validation were used
      - [ ] Measures to detect and prevent overfitting were implemented
    * When using an existing model
      - Answer "NA".

  * Assessment Result Style: ACRSL_Style


#### NM-12. External Data Validation (v6_3-7; C-33)

Was model validation on independent external datasets conducted?

  * Assessment Criteria: 
    * When building a new model
      - [ ] Evaluation on independent datasets not used for training was conducted
      - [ ] Characteristics of external validation datasets are described
      - [ ] Performance on external validation was compared and discussed with internal validation
    * When using an existing model
      - Answer "NA".

  * Assessment Result Style: ACRSL_Style


#### NM-13. Demographics and Clinical Characteristics of Cases in Each Partition (v6_3-8; C-36)

Were the composition and characteristics of each dataset (training, validation, test) clearly described?

  * Assessment Criteria: 
    * When building a new model
      - [ ] Sample sizes for each dataset are specified
      - [ ] Demographic characteristics (age distribution, sex ratio, patient/control group breakdown, etc.) are described
      - [ ] Clinical characteristics (symptom severity scores, illness duration, comorbidities, medication status, etc.) are described
      - [ ] Comparison of characteristics between datasets and assessment of balance are conducted
      - [ ] Handling of missing data is described
    * When using an existing model
      - Answer "NA".

  * Assessment Result Style: ACRSL_Style



#### NM-14. Consideration for Reproducibility (v6_3-10; C-43)

Was there description of the availability of software, models, and data to ensure research reproducibility?

  * Assessment Criteria: 
    * When building a new model
      - [ ] Software versions used are specified
      - [ ] Availability of code and scripts is mentioned
      - [ ] Sharing of trained models is mentioned
      - [ ] Data availability (including limitations if any) is described  
    * When using an existing model
      - Answer "NA".

  * Assessment Result Style: ACRSL_Style


#### NM-15. Interpretation Specific to Normative Modeling (v6_3-11)

Were the interpretation of individual-level deviation patterns and their clinical significance appropriately described?

  * Assessment Criteria: 
    - [ ] Meaning of individual-level deviation scores is clearly explained
    - [ ] Clinical thresholds and decision criteria are described
    - [ ] Differences and advantages compared to traditional case-control studies are described
    - [ ] Prospects for clinical application are realistically described
  
  * Assessment Result Style: ACRSL_Style



### CR. Clinical Research Part

#### CR-1. Clear Definition of Target Population (v6_2-2; N-2)

Were the target psychiatric or neurological patient populations clearly defined?

  * Assessment Criteria: 
    * When using a newly collected dataset
      - [ ] Demographic characteristics such as age and sex are described
      - [ ] Diagnostic criteria for psychiatric/neurological disorders (DSM-5, ICD-11, etc.) are specified
    * When using an existing dataset
      - [ ] Demographic characteristics of the utilized dataset are specified
      - [ ] Reasons for selecting the utilized dataset are specified

  * Assessment Result Style: ACRSL_Style


#### CR-2. Clarity of Inclusion and Exclusion Criteria (v6_2-3; N-4, C-8)

Were the inclusion and exclusion criteria pre-specified and uniformly applied to all participants?

  * Assessment Criteria:
    * When using a newly collected dataset
      - [ ] Clear inclusion criteria are described
      - [ ] Clear exclusion criteria are described
      - [ ] These criteria were uniformly applied to all participants
      - [ ] Image quality is considered
      - [ ] Number of subjects included in final analysis is specified
    * When using an existing dataset
      - [ ] Image quality is considered
      - [ ] Exclusion reasons are specifically described
      - [ ] Number of subjects included in final analysis is specified

  * Assessment Result Style: ACRSL_Style


#### CR-3. Clarity of Data Sources (v6_2-6; C-7)

Were the sources of neuroimaging data clearly described?

  * Assessment Criteria: 
    * When using a newly collected dataset
      - [ ] Dataset names or study names used are specified
      - [ ] Time and location of data acquisition are described
    * When using an existing dataset
      - [ ] Dataset names or study names used are specified
      - [ ] For open datasets, specific database names (e.g., Human Connectome Project, ABCD Study, etc.) are specified

  * Assessment Result Style: ACRSL_Style


#### CR-4. Description of Image Acquisition Protocol (v6_2-7; C-13)

Were the MRI and other image acquisition protocols clearly described?

  * Assessment Criteria: 
    * When using a newly collected dataset
      - [ ] Details of MRI sequences used (T1-weighted, DTI, fMRI, etc.) are described
      - [ ] Imaging parameters (TR, TE, flip angle, resolution, etc.) are specified
      - [ ] Scanner specifications (manufacturer, field strength, etc.) are described

    * When using an existing dataset
      - Answer "NA".

  * Assessment Result Style: ACRSL_Style


#### CR-5. Details of Data Preprocessing (v6_3-1; C-9)

Were the neuroimaging data preprocessing procedures clearly described?

  * Assessment Criteria: 
    * When building a new model 
      - [ ] Preprocessing software used (FreeSurfer, FSL, SPM, etc.) is specified
      - [ ] Each preprocessing step (skull stripping, normalization, segmentation, etc.) is detailed
      - [ ] Quality control procedures are described
    * When using an existing model
      - Answer "NA".

  * Assessment Result Style: ACRSL_Style


#### CR-6. Validity of Outcome Measures (v6_2-4; N-11)　
　
　Were the outcome measures (dependent variables) clearly defined, valid, reliable, and implemented consistently across all study participants?

  * Assessment Criteria:
    - [ ] Outcomes are pre-specified and operationally defined (definitions, thresholds, timing)
    - [ ] Measurement methods are validated or standard; instruments/versions are specified (incl. questionnaires, biomarkers/imaging, EHR/claims codes)
    - [ ] Reliability and quality control are reported (e.g., ICC/κ/α ≥ 0.70, training/calibration, duplicate or central reads); data-handling rules are pre-specified
    - [ ] Implementation is consistent across participants/sites (same protocol/mode, harmonized timing); assessors are blinded when feasible; deviations are reported/justified
    
  * Assessment Result Style: ACRSL_Style


#### CR-7. Handling of Confounding Variables (v6_2-5; N-14)

Were key confounding variables (age, sex, imaging site, etc.) measured and statistically adjusted?

  * Assessment Criteria: 
    - [ ] Basic confounding variables such as age and sex are considered
    - [ ] For multi-site studies, methods for handling site effects are described
    - [ ] When harmonization methods are used, their details are described
    - [ ] Appropriate adjustment for confounders not covered in NM is described

  * Assessment Result Style: ACRSL_Style


-------------------

## Basic Evaluation Principles

For Assessment Items `CM-6`, `NM-1` 〜 `NM-15`, and `CR-1` 〜 `CR-7`, follow the rules below when constructing answers, and return "ACRSL_Style" results.

1. For each question, select an Answer Category: "Yes", "Partial", "No", or "NA".
   - Yes: Clearly described and meets the criteria.
   - Partial: Mentioned but insufficient or incomplete.
   - No: Information is absent or unavailable.
   - NA: Not applicable to this study.

Judge "Yes" if at least 80% of the Assessment Criteria are met, "Partial" if at least one is met, and "No" if none are met.

2. Provide a Confidence Rating for each answer: "High", "Medium", or "Low".
   - High: Clear and direct statements in the text.
   - Medium: Indirect or limited evidence (e.g., in supplementary materials).
   - Low: Ambiguous or insufficient description leading to uncertainty.

3. When selecting "Yes", "Partial", or "No", always include:
   - Reason: Step-by-step explanation of the judgment. 
   - Supporting Text: Excerpts of the text used as evidence.
   - Location: Answer by tracing the document structure to find the location of the Supporting Text.
     - Example: "Someone2025.pdf: Main Manuscript / Methods / Paragraph 3", "Someone2025_sup.pdf.md: Supporting Information / Paragraph 4"

4. When selecting "NA" , always include:
   - Reason: Step-by-step explanation of the judgment.

5. When selecting "Partial", "No", or "NA", explain using the terms "missing", "unclear", "incomplete", and "not applicable" whenever possible in the Reason statement.

6. When descriptions are unclear or absent, take a conservative stance and, in many cases, select "No".

For Assessment Items `CM-1` 〜 `CM-5`, `CM-7`, just return the answer, i.e., in "A_Style". 

-------------------

## Output of Extracted Results
- File format
  - JSON. Match the structure of `./QA_Author20XX_by_Someone_YYYYmmddHHMMSS.json`.
- File name
  - If you are Claude Code: e.g., `QA_Bethlehem2022_by_claude_202509191115.json` (pattern: `QA_AuthorYear_by_claude_YYYYMMDDhhdd.ext`).
  - If you are Gemini CLI: e.g., `QA_Bethlehem2022_by_gemini_202509191115.json` (pattern: `QA_AuthorYear_by_gemini_YYYYMMDDhhdd.ext`).
  - If you are Codex-CLI: e.g., `QA_Bethlehem2022_by_codex_202509191115.json` (pattern: `QA_AuthorYear_by_claude_YYYYMMDDhhdd.ext`).
