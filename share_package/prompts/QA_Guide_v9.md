# Quality Assessment Guidance for Normative Modeling in Neuroimaging: A Systematic Review

Also known as (AKA): The NORMA (Normative Modeling Assessment) Checklist: Reporting Standards for Normative Modeling in Neuroimaging

Version based on `Quality_Assessment_Checklist_20251222.pdf`.

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

#### CM-1. Research Objectives

Are the study objectives stated?

* Assessment Criteria:
    * [ ] Research hypotheses or questions are provided
    * [ ] The purpose of applying normative modeling is described (e.g., diagnostic support, personalized medicine, disease understanding)

* Assessment Result Style: ACRSL_Style


### NM. Normative Modeling Part

#### NM-1. Selection Criteria for the Reference Cohort

Are the eligibility criteria for the reference cohort (i.e., healthy individuals) and image quality standards described?

* Assessment Criteria:
    * For studies building a NEW normative model, check the following:
        * [ ] The eligibility criteria for the reference cohort are described  
            * For open datasets, the original publication is cited  
            * For newly collected data, inclusion and exclusion criteria are provided
        * [ ] Image quality and data completeness standards are specified (e.g., motion artifacts, missing data)
        * [ ] The final sample size and reasons for exclusion are reported

    * For studies using an EXISTING normative model, check the following:
        * [ ] The original publication describing the reference cohort is cited
        * [ ] If transfer learning or recalibration is performed, the eligibility criteria for the local reference sample are described
        * [ ] Image quality and data completeness standards are specified (e.g., motion artifacts, missing data)
        * [ ] The final sample size and exclusion reasons are reported

* Assessment Result Style: ACRSL_Style


#### NM-2. Handling of Covariates in the Reference Cohort

Are covariates appropriately considered in building the normative model?

* Assessment Criteria:
    * [ ] Covariates such as age and sex are considered
    * [ ] If multi-site data are used, methods for addressing site effects are described (e.g., site as covariate, ComBat harmonization, hierarchical modeling)

* Assessment Result Style: ACRSL_Style


#### NM-3. Data Sources of the Reference Cohort

Are the neuroimaging data sources of the reference cohort described?

* Assessment Criteria:
    * For studies building a NEW normative model, check the following:
        * [ ] The data source is specified  
            * For open datasets, the database/study name is indicated  
            * For newly collected data, collection sites and time period are described

    * For studies using an EXISTING normative model, check the following:
        * [ ] The data source is specified (e.g., dataset/study name, reference to the original publication)


* Assessment Result Style: ACRSL_Style


#### NM-4. Image Acquisition Protocol

Is the image acquisition protocol described?

* Assessment Criteria:
    * For PRIMARY data collection, check the following:
        * [ ] Imaging modality is specified, including subtype where applicable (e.g., MRI: T1-weighted, diffusion, functional)
        * [ ] Acquisition parameters are specified (e.g., TR, TE, resolution)
        * [ ] Equipment specifications are specified (e.g., manufacturer, field strength)

    * For SECONDARY data (existing datasets), **answer "NA"**.

* Assessment Result Style: ACRSL_Style


#### NM-5. Data Preprocessing

Are the preprocessing procedures described?

* Assessment Criteria:
    * If preprocessing is performed in this study, check the following:
        * [ ] Preprocessing software is specified (e.g., FreeSurfer, FSL, SPM)
        * [ ] Preprocessing steps or the standard pipeline used are mentioned (e.g., FreeSurfer recon-all, fMRIPrep)
        * [ ] Quality control procedures are described

    * If preprocessed data from existing projects are used with no additional preprocessing, **answer "NA"**.

* Assessment Result Style: ACRSL_Style


#### NM-6. Internal Data Validation of the Reference Cohort

Is a data partitioning strategy used for internal validation?

* Assessment Criteria:
    * For studies building a NEW normative model, check the following:
        * [ ] A data partitioning strategy is used where the model is tested on samples not used for fitting in that iteration (e.g., hold-out test set, K-fold cross-validation, leave-one-out cross-validation)

    * For studies using an EXISTING normative model, **answer "NA"**.

* Assessment Result Style: ACRSL_Style


#### NM-7. External Data Validation of the Reference Cohort

Is the normative model applied to an independent external dataset of healthy individuals?

* Assessment Criteria:
    * For studies building a NEW normative model, check the following:
        * [ ] The normative model is applied to an independent external dataset of healthy individuals that was not used for model development

    * For studies using an EXISTING normative model, **answer "NA"**.

* Assessment Result Style: ACRSL_Style


#### NM-8. Normative Modeling Approach

Is the statistical approach used for normative modeling described?

* Assessment Criteria:
    * For studies building a NEW normative model, check the following:
        * [ ] The type of statistical model is specified (e.g., linear regression, Gaussian process regression, hierarchical Bayesian model)
        * [ ] Key model settings are described (e.g., smoothing parameters, priors, kernel type)
        * [ ] Software and libraries used for implementation are specified

    * For studies using an EXISTING normative model, **answer "NA"**.
  
* Assessment Result Style: ACRSL_Style


#### NM-9. Model Performance

Is model performance quantitatively assessed for the reference cohort?

* Assessment Criteria:
    * For studies building a NEW normative model, check the following:
        * [ ] At least one quantitative metric assessing model fit or calibration is reported (e.g., explained variance, correlation between predicted and observed values, prediction error, coverage of prediction intervals)

    * For studies using an EXISTING normative model, **answer "NA"**.

* Assessment Result Style: ACRSL_Style


#### NM-10. Characteristics of the Reference Cohort in Each Partition

Are the characteristics of the reference cohort in each data partition described?

* Assessment Criteria:
    * For studies building a NEW normative model with data partitioning, check the following:
        * [ ] Sample sizes for each partition are reported (e.g., training, validation, test)
        * [ ] Demographic characteristics are described (e.g., age, sex)

    * For studies using an EXISTING normative model, or if no data partitioning is performed, **answer "NA"**.

* Assessment Result Style: ACRSL_Style

#### NM-11. Reproducibility

Is sufficient information provided to support reproducibility?

* Assessment Criteria:
    * For studies building a NEW normative model, check the following:
        * [ ] Software and library versions are specified (e.g., Python 3.8, PCNtoolkit v0.3, R4.2.0)
        * [ ] Availability of code, trained models, and data is stated, including access conditions (e.g., open, restricted, not available)

    * For studies using an EXISTING normative model, check the following:
        * [ ] The source of the normative model is provided (e.g., citation, repository URL, version)

* Assessment Result Style: ACRSL_Style



### CR. Clinical Research Part

#### CR-1. Selection Criteria for the Clinical Cohort

Are the eligibility criteria for the clinical cohort and image quality standards described?

* Assessment Criteria:
    * For PRIMARY data collection, check the following:
        * [ ] Patient eligibility and diagnostic criteria are described (e.g., DSM-5, ICD-10, clinical interviews)
        * [ ] Image quality and data completeness standards are specified (e.g., motion artifacts, missing data)
        * [ ] The final sample size and reasons for exclusion are reported

    * For SECONDARY data (existing datasets), check the following:
        * [ ] The original publication is cited, and any additional eligibility criteria applied in this study are described
        * [ ] Image quality and data completeness standards are specified (e.g., motion artifacts, missing data)
        * [ ] The final sample size and reasons for exclusion are reported

* Assessment Result Style: ACRSL_Style


#### CR-2. Handling of Clinical Covariates

Are clinical covariates considered in analyses involving the clinical cohort?

* Assessment Criteria:
    * [ ] Clinical covariates beyond basic demographics are considered in analyses of deviation scores (e.g., medication status, illness duration, symptom severity, comorbidities)
  
* Assessment Result Style: ACRSL_Style


#### CR-3. Data Sources of the Clinical Cohort

Are the neuroimaging data sources of the clinical cohort described?

* Assessment Criteria:
    * For PRIMARY data collection, check the following:
        * [ ] The data source is specified (e.g., collection sites, time period)

    * For SECONDARY data (existing datasets), check the following:
        * [ ] The data source is specified (e.g., database/study name, reference to the original publication)

* Assessment Result Style: ACRSL_Style


#### CR-4. Clinical Characteristics of the Clinical Cohort

Are the characteristics of the clinical cohort described?

* Assessment Criteria:
    * [ ] Sample sizes are reported
    * [ ] Demographic characteristics are described (e.g., age, sex)
    * [ ] Clinical characteristics are described (e.g., medication status, symptom severity, illness duration)

* Assessment Result Style: ACRSL_Style


#### CR-5. Clinical Assessment Measures

Are the clinical assessment measures described?

* Assessment Criteria:
    * [ ] Clinical measures are specified (e.g., symptom rating scales, diagnostic criteria with version)
    * [ ] Assessment procedures are described (e.g., structured interview, self-report, clinician-rated)

* Assessment Result Style: ACRSL_Style


#### CR-6. Interpretation of Deviation Scores

Is the interpretation of deviation scores and their clinical significance described?

* Assessment Criteria:
    * [ ] The meaning and direction of deviation scores are explained (i.e., what positive and negative values represent, how the magnitude should be interpreted)
    * [ ] If deviation scores are used to define categories (e.g., extreme, atypical), quantitative thresholds are reported (e.g., |Z| > 1.96, outside the 5th to 95th percentile range)
    * [ ] Clinical implications of deviation patterns are discussed (e.g., relationships with symptoms or diagnosis, comparison to group-level findings, limitations of interpretation)

* Assessment Result Style: ACRSL_Style


---------------


## Basic Evaluation Principles

Scope and style
* Apply these rules to items `CM-1`, `NM-1`–`NM-11`, and `CR-1`–`CR-6`; return results in `ACRSL_Style`.
* Use `A_Style` (answer-only) for `SI-1`–`SI-5` as indicated in the items list. All other items in this guide use `ACRSL_Style`.
* Some criteria are conditional. For items beginning with "If," respond only when the condition applies; otherwise, treat those criteria as not applicable. Follow the branching noted for NEW vs. EXISTING models and PRIMARY vs. SECONDARY data collection.

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
    * Location: Document location of the supporting text
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
