# Quality Assessment Guidance for Normative Modeling in Neuroimaging: A Systematic Review

Also known as (AKA): The NORMA (Normative Modeling Assessment) Checklist: Reporting Standards for Normative Modeling in Neuroimaging

-------------------

## Prompt Overview
You are a professional reviewer skilled at assessing the quality of research articles on normative modeling in neuroimaging. This assessment is conducted as part of a systematic review on methodological considerations and applications of neuroimaging for psychiatric and neurological disorders.

-------------------

## Our Systematic Review Objectives

This systematic review will evaluate normative modeling studies that use neuroimaging and neurophysiological tests (e.g., MRI, PET, EEG, MEG). The review addresses three questions:

  1. **Modality**: Which measurement techniques have been applied in normative modeling, and how often is each modality used? For common modalities like MRI, what specific sequences (e.g., T-weighted) are most frequently utilized?
  2. **Methodology**: How are normative modeling studies designed and validated across modalities? We will extract information on sample size, covariates, image preprocessing, statistical models, harmonization procedures, and validation strategies.
  3. **Clinical scope**: Which psychiatric or neurological conditions have been investigated with normative modeling, and what individual-level deviation patterns or clinical utilities have been reported?

-------------------

## Location of Source Materials

* The papers to process are in the current directory. Extract the items below based on all information in this folder.
* Refer to subfolders as needed.

## Quality Assessment Result Style 

There are two result styles used in this quality assessment guide:

  * **A_Style** = Answer only style
    * Provide only the extracted answer
    * No confidence rating, reason, supporting text, or location required
    * Used for simpler items or categorical choices

  * **ACRSL_Style** = Answer, Confidence rating, Reason, Supporting text, and Location style
    * Provide structured detailed extraction including:
        * Answer: The extracted information
        * Confidence rating: High, Medium, or Low
        * Reason: Step-by-step explanation of how you arrived at the answer
        * Supporting text: Direct quotes from source materials
        * Location: Document location of the supporting text
    * Used for complex items requiring evidence and justification


See "Basic Evaluation Principles" section below for detailed format requirements and examples.

----------------------

## Quality Assessment Items

### SI. Study Identification Part

#### SI-1. Study ID
  * Unique identifier for each paper. Author name + year.
  * Example: 
      * Rutherford2022
  * Assessment Result Style: A_Style


#### SI-2. Reference File Names
  * File names referenced in extracting this data
  * Example: 
      * Rutherford2022.pdf.md; Rutherford2022_sup.pdf.md
  * Assessment Result Style: A_Style

  
#### SI-3. author_journal_year
  * "Author et al., Journal, Year" format
  * Example: 
      * Rutherford et al., Communications Biology, 2022
  * Assessment Result Style: A_Style

#### SI-4. Title
  * Paper title (as original)
  * Assessment Result Style: A_Style


#### SI-5. DOI
  * DOI (10.xxxx format, NR if absent)
  * Assessment Result Style: A_Style



### CM. Common Part

#### CM-1. Clarity of Research Objectives

Were the research questions or objectives in this paper clearly stated?

* Assessment Criteria:
    * [ ] Research hypotheses and/or research questions are specifically stated
    * [ ] Specific application purposes of normative modeling are clearly stated (e.g. diagnostic support, personalized medicine, disease understanding)

* Assessment Result Style: ACRSL_Style


### NM. Normative Modeling Part

#### NM-1. Clarity of Inclusion and Exclusion Criteria for Healthy Controls

Were the inclusion and exclusion criteria clearly defined for healthy control status and image quality?

* Assessment Criteria:
    * For studies building a NEW normative model, check the following:
        * [ ] Healthy-control source and eligibility criteria are specified
            * For newly collected data, inclusion/exclusion criteria is described
            * For open datasets, references to the original publication are appropriately described.
        * [ ] Image quality and data completeness standards applied in this study are specified (e.g. motion artifacts, missing data)
        * [ ] Final sample size (subjects/scans) and exclusion reasons are reported

    * For studies using an EXISTING normative model, check the following:
        * [ ] The original publication is appropriately referenced
        * [ ] Inclusion/exclusion criteria of healthy controls for transfer learning/recalibration are described in this paper (if applicable)
        * [ ] Image quality and data completeness standards applied in this study are specified (e.g. motion artifacts, missing data)
        * [ ] Final sample size (subjects/scans) and exclusion reasons are reported

* Assessment Result Style: ACRSL_Style


#### NM-2. Handling of Confounding Variables in Healthy Controls

Were key confounding variables (e.g. age, sex, imaging site) measured and statistically adjusted?

* Assessment Criteria:
    * [ ] Basic confounding variables/covariates such as age and sex are considered (Judge based on whether confounding was considered, not just whether variables were included in the final model)
    * For multi-site studies, check the following: 
      [ ] methods for handling site effects are described (e.g. site as covariate, harmonization like ComBat, hierarchical models)

* Assessment Result Style: ACRSL_Style


#### NM-3. Clarity of Data Sources of Healthy Controls

Were the neuroimaging data sources of healthy controls clearly described?

* Assessment Criteria:
    * For studies building a NEW normative model and open datasets, check the following:
        * [ ] Sepecific database/study name and version/release (if applicable) are indicated
    * For studies building a NEW normative model and newly collected data, check the following:
        * [ ] Collection sites/study name and time period are described
    * For studies using an EXISTING normative model, check the following:
        * [ ] Data source is clearly specified (e.g. dataset/study name or a reference to the publication describing the data)


* Assessment Result Style: ACRSL_Style


#### NM-4. Description of Image Acquisition Protocol

Were MRI and other image acquisition protocols clearly described?

* Assessment Criteria:
    * When using NEWLY collected data (reference or clinical cohort), check the following:
        * [ ] Details of MRI sequences used are described (e.g. T1-weighted, DTI, fMRI)
        * [ ] Imaging parameters are specified (e.g. TR, TE, flip angle, resolution)
        * [ ] Scanner specifications are described (e.g. manufacturer, field strength)

    * When using an EXISTING dataset, **answer "NA"**.

* Assessment Result Style: ACRSL_Style


#### NM-5. Details of Data Preprocessing

Were the neuroimaging data preprocessing procedures clearly described?

* Assessment Criteria:
    * When this study performs NEW preprocessing of raw data (for reference and/or clinical cohorts), check the following:
        * [ ] Preprocessing software is specified (e.g. FreeSurfer, FSL, SPM)
        * [ ] Preprocessing steps are detailed (e.g. skull stripping, normalization, segmentation)
        * [ ] Quality control procedures are described
    * When only preprocessed data from EXISTING pipelines/models are used and no preprocessing is performed in this study, **answer "NA"**.

* Assessment Result Style: ACRSL_Style


#### NM-6. Internal Data Validation in Healthy Controls

Was an internal validation strategy based on data partitioning used?

* Assessment Criteria:
    * When building a NEW model, check the following:
        * [ ] Internal validation based on data partitioning is used (e.g. hold-out with a train-test split, K-fold CV, LOOCV), using samples that were not used to fit the model

    * When using an EXISTING model, **answer "NA"**.

* Assessment Result Style: ACRSL_Style

#### NM-7. External Data Validation in Healthy Controls

Was the normative model evaluated on an independent external dataset?

* Assessment Criteria:
    * When building a NEW model, check the following:
        * [ ] The trained normative model is applied to and evaluated on an independent external dataset of healthy controls that was not used for model development

    * When using an EXISTING model, **answer "NA"**.

* Assessment Result Style: ACRSL_Style


#### NM-8. Details of Normative Modeling Approach

Were the statistical approaches used for normative modeling described in detail?

* Assessment Criteria:
    * When building a NEW model, check the following:
        * [ ] Types of statistical models are specified (e.g. linear regression, Gaussian process regression, Bayesian models)
        * [ ] Model settings such as software options and/or hyperparameters are described (e.g. choice of kernel, smoothing parameters, priors)
        * [ ] Tools used are specified (e.g. software programs, packages, libraries)
    * When using an EXISTING model, **answer "NA"**.
  
* Assessment Result Style: ACRSL_Style


#### NM-9. Model Performance and Calibration on Training Data

Were metrics reported to evaluate the fit of the normative model on healthy control data?

* Assessment Criteria:
    * When building a NEW model, check the following:
        * [ ] At least one quantitative metric assessing model fit or calibration for healthy controls is reported (e.g. explained variance (`R^2`), correlation between predicted and observed values, coverage of percentile/credibility intervals in controls)

    * When using an EXISTING model, **answer "NA"**.

* Assessment Result Style: ACRSL_Style


#### NM-10. Demographics and Characteristics of Healthy Controls in Each Partition

Were the demographic characteristics of healthy controls in each data partition (e.g. training, test) clearly described?

* Assessment Criteria:
    * When building a NEW model, check the following:
        * [ ] Sample sizes for each partition are specified
        * [ ] Demographic characteristics are described (e.g. age, sex, race/ethnicity)
    * When using an EXISTING model, **answer "NA"**.

* Assessment Result Style: ACRSL_Style

#### NM-11. Consideration for Reproducibility

Was sufficient information provided to ensure research reproducibility?

* Assessment Criteria:
    * When building a NEW model, check the following:
        * [ ] Software/library versions used are specified (e.g. Python 3.8, PCNtoolkit v0.3, R4.2.0)
        * [ ] Availability of code, trained models, and/or data is clearly stated (including access conditions: open, restricted, or not available)
    * When using an EXISTING model, check the following:
        * [ ] The manuscript provides the model's reference or source information (citation, repository/URL, version or commit)

* Assessment Result Style: ACRSL_Style



### CR. Clinical Research Part

#### CR-1. Clear Definition of Target Population

Were the inclusion and exclusion criteria clearly defined for the target patient population and image quality?

* Assessment Criteria:
    * For studies using a NEWLY collected dataset, check the following:
        * [ ] Patient inclusion/exclusion and diagnostic criteria are clearly described (e.g. DSM-5/ICD-10, clinical interviews, validated scales)
        * [ ] Image quality and data completeness standards applied in this study are specified (e.g. motion artifacts, missing data)
        * [ ] Final sample size (subjects/scans) and exclusion reasons are reported

    * For studies using an EXISTING dataset, check the following:
        * [ ] The original publication is appropriately referenced, and any additional inclusion/exclusion criteria applied in this study are described
        * [ ] Image quality and data completeness standards applied in this study are specified (e.g. motion artifacts, missing data)
        * [ ] Final sample size (subjects/scans) and exclusion reasons are reported

* Assessment Result Style: ACRSL_Style


#### CR-2. Handling of Confounding Variables in Clinical Analyses

Were potential confounding variables appropriately considered in clinical analyses?

* Assessment Criteria:
    * [ ] Clinical confounders are considered in analyses of deviation scores and group comparisons (e.g. medication status, illness duration, comorbidities)
  
* Assessment Result Style: ACRSL_Style


#### CR-3. Clarity of Data Sources of the Patient Population

Were the neuroimaging data sources of patients clearly described?

* Assessment Criteria:
    * For studies using a NEWLY collected dataset, check the following:
        * [ ] Data source is clearly specified: collection sites/study name and time period are described

    * For studies using an EXISTING dataset, check the following:
        * [ ] Data source is clearly specified: specific database/study name and version/release (if applicable) are indicated, or a reference to the publication describing the data is provided

* Assessment Result Style: ACRSL_Style


#### CR-4. Demographics and Clinical Characteristics of Cases in Patient Datasets

Were the demographic and clinical characteristics of patient datasets used in clinical analyses clearly described?

* Assessment Criteria:
    * [ ] Sample sizes of all patient datasets used in analyses are specified
    * [ ] Key demographic characteristics are described (e.g. age, sex)
    * [ ] Key clinical characteristics are described (e.g. medication status, symptom severity, illness duration)

* Assessment Result Style: ACRSL_Style


#### CR-5. Validity of Clinical Assessment Measures

Were the clinical assessment measures clearly defined and appropriately used?

* Assessment Criteria:
    * [ ] Clinical measures are clearly specified (e.g. validated symptom scales with versions, diagnostic criteria)
    * [ ] Assessment procedures are described (e.g. structured interviews, self-report, clinician-rated)

* Assessment Result Style: ACRSL_Style


#### CR-6. Interpretation Specific to Normative Modeling

Was the interpretation of deviation patterns and their clinical significance appropriately described?

* Assessment Criteria:
    * [ ] Meaning and direction of deviation scores are clearly explained (e.g. what positive/negative values represent, how the magnitude of deviation should be interpreted)
    * [ ] When deviation scores are used to define categories (e.g. "atypical", "extreme", "abnormal"), explicit quantitative criteria are reported (e.g. `|Z| > 1.96`, outside the 5th-95th percentile range)
    * [ ] Clinical significance and implications of deviation patterns are discussed (e.g. relationships with symptoms or diagnosis, comparison to case-control findings, limitations of the clinical interpretation)

* Assessment Result Style: ACRSL_Style


---------------


## Basic Evaluation Principles

Scope and style
* Apply these rules to items `CM-1`, `NM-1`–`NM-11`, and `CR-1`–`CR-6`; return results in `ACRSL_Style`.
* Use `A_Style` (answer-only) for `SI-1`–`SI-5` as indicated in the items list. All other items in this guide use `ACRSL_Style`.

Answer category
* Select one: `Yes`, `Partial`, `No`, or `NA`.
* Yes: Clearly described and meets the criteria (≥100% of listed criteria satisfied).
* Partial: Mentioned but insufficient/incomplete (≥1 criterion satisfied but <100% overall).
* No: Information is absent or unavailable (0 criteria satisfied).
* NA: Not applicable to this study per the item’s embedded NA guidance (e.g., “When using an EXISTING model, answer NA.”).

Confidence rating
* Provide `High`, `Medium`, or `Low` for each answer.
* High: Clear, direct statements in main text or clearly labeled tables/figures.
* Medium: Indirect or limited evidence (e.g., only in supplement or ambiguous wording).
* Low: Ambiguous or insufficient description leading to uncertainty.

Evidence requirements
* For `Yes`, `Partial`, or `No`, include:
    * Reason: Step-by-step explanation of the judgment. Explicitly walk through the item’s criteria, indicate which were satisfied/unsatisfied/NA, and explain how this mapping leads to the final Answer Category.
    * Supporting Text: Verbatim excerpts (quote minimally; multiple snippets allowed).
    * Location: File and section context (e.g., `Paper.pdf.md: Methods / MRI / para 2`, `Supp.pdf.md: Table S1`).
* For `NA`, include:
    * Reason: State why the item is not applicable, explicitly citing the item’s NA condition.

Language and stance
* When selecting `Partial`, `No`, or `NA`, use terms like “missing”, “unclear”, “incomplete”, and “not applicable” where appropriate.
* When descriptions are unclear or absent, take a conservative stance; in many cases select `No`.

-------------------

## Output of Extracted Results
* File format
    * JSON. Match the structure of `./QA_Author20XX_by_Someone_YYYYmmddHHMMSS.json`.
* File name
    * If you are Claude Code: e.g., `QA_Bethlehem2022_by_claude_202509191115.json` (pattern: `QA_AuthorYear_by_claude_YYYYMMDDhhdd.ext`).
    * If you are Gemini CLI: e.g., `QA_Bethlehem2022_by_gemini_202509191115.json` (pattern: `QA_AuthorYear_by_gemini_YYYYMMDDhhdd.ext`).
    * If you are Codex-CLI: e.g., `QA_Bethlehem2022_by_codex_202509191115.json` (pattern: `QA_AuthorYear_by_codex_YYYYMMDDhhdd.ext`).
