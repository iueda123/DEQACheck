# Data Extraction Guidance for Normative Modeling in Neuroimaging: A Systematic Review

-------------------

## Prompt Overview
You are a professional reviewer skilled at extracting the data of research articles on normative modeling in neuroimaging. This data extraction is conducted as part of a systematic review on methodological considerations and applications of neuroimaging for psychiatric and neurological disorders.

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

## General Extraction Rules

- Scope and priority
  - Prefer main text and tables; if absent, use supplementary materials; if still absent, use clearly cited external sources and cite them in Location.
  - When multiple timepoints/sessions exist, extract for the analysis actually used to fit/evaluate the normative model (typically baseline); specify if otherwise.

- Reporting conventions
  - Use semicolons to separate multiple values: e.g., multiple datasets, diagnoses, or metrics.
  - Use years for age unless the paper uses a different explicit unit; include unit when not obvious.
  - Use standard short tokens: NA = not applicable; NR = not reported; Unknown = unclear even after careful reading.
  - When only inclusion criteria are available (e.g., 50–80 years), report them and mark missing statistics as NR.

- Computation and rounding
  - You may compute derived values (percentages, weighted means/pooled SDs) from provided counts/statistics. Round percentages to one decimal place and means/SDs to two decimals unless the paper uses a different precision.
  - If only one sex count is given, infer the other from total N. Clearly state any inference in Reason.
  - For ranges, report as "min–max"; use en dash (–) when possible.

- Consistency and formatting
  - Keep key names exactly as specified; do not invent new fields.
  - For ACRSL_Style, always return all five fields (answer, confidence_rating, reason, supporting_text, location).
  - Use concise, literal quotes in Supporting Text; do not paraphrase there.
  - In Location, specify file and an identifiable place: file name + section/lines/pages.

- Ambiguity handling
  - If evidence is indirect or split across sources, answer as Partial and explain the synthesis in Reason.
  - When truly absent, answer NR and set confidence accordingly. Do not guess.

-------------------

## Data Extraction Items

**Extraction Result Style**

There are three result styles used in this data extraction guide:

  * **A_Style** = Answer only style
    - Provide only the extracted answer
    - No confidence rating, reason, supporting text, or location required
    - Used for simpler items or categorical choices

  * **ASL_Style** = Answer, Supporting text, and Location style
    - Provide succinct evidence without reasoning:
      - Answer: The extracted information
      - Supporting text: Direct quotes from source materials (concise)
      - Location: Where the quote was found
    - Used for items that benefit from citation but do not need full reasoning/confidence (e.g., categorical choices with explicit wording in the paper)

  * **ACRSL_Style** = Answer, Confidence rating, Reason, Supporting text, and Location style
    - Provide structured detailed extraction including:
      - Answer: The extracted information
      - Confidence rating: High, Medium, or Low
      - Reason: Step-by-step explanation of how you arrived at the answer
      - Supporting text: Direct quotes from source materials
      - Location: Document location of the supporting text
    - Used for complex items requiring evidence and justification

See "Basic Evaluation Principles" section below for detailed format requirements and examples.

### SI. Study Identification Part

#### SI-1. Study ID
  * Unique identifier for each paper. Author name + year.
  * Extraction Result Style: A_Style
  * Example: 
    * Rutherford2022

#### SI-2. Reference File Names
  * File names referenced in extracting this data
  * Extraction Result Style: A_Style
  * Example: 
    * Rutherford2022.pdf.md; Rutherford2022_sup.pdf.md
  
#### SI-3. Author, Journal, and Year
  * Extraction Result Style: A_Style
  * Data Type: string
  * Example: 
    * Rutherford et al., Communications Biology, 2022

#### SI-4. Title
  * Paper title (as original)
  * Extraction Result Style: A_Style

#### SI-5. DOI
  * DOI (10.xxxx format, NR if absent)
  * Extraction Result Style: A_Style



### SC. Study Characteristics Part

#### SC-1. Study Objective
  * Summarize primary research question/purpose in 1-2 sentences
  * Extraction Result Style: A_Style
  * Notes: 
    * Describe the primary research question or purpose.

#### SC-2. Study Design
  * Extraction Criteria: Cross-sectional / Longitudinal / Other
  * Extraction Result Style: A_Style
  * Notes: Choose from the options

#### SC-3. Study Design Other
  * Extraction Criteria: A description of the other study design.
  * Extraction Result Style: A_Style
  * Notes: 
    * If "study_design" is "Other", describe the study design.
    * If "study_design" is not "Other", write "-".


### RCI. Reference Cohort & Imaging Part

#### RCI-1. Dataset Name
  * Extraction Criteria: Dataset name
  * Extraction Result Style: ACRSL_Style
  * Notes:
    * List the name(s) of the dataset(s) used for healthy controls / normative reference.
    * Separate multiple datasets with semicolons.
    * If there are many datasets (five or more), list representative ones and add a succinct qualifier (e.g., "Multi-site aggregated dataset; representative: ABIDE; HCP; UK Biobank"). Record the full list in `general_notes`.
    * Include dataset versions if specified (e.g., ABIDE I and II).
    * If the dataset comprises specific cohorts or sites from a larger database, note that (e.g., "UCL; NACC ADRC '8361'").
  * Example:
    * ABIDE I; ABIDE II
    * UK Biobank
  

#### RCI-2. HC_N
  * Extraction Criteria: Sample size of healthy controls
  * Extraction Result Style: ACRSL_Style
  * Notes: 
    * Record the number of analyzed participants. For example: if ABIDE provides 573 but 569 were analyzed, record 569. Extract the final number used for modeling after applying exclusion criteria.
    * If the paper states that this information is provided elsewhere, include enough detail to identify that other paper.
  * Example: 
    * 569
  
#### RCI-3. HC_Age
  * Extraction Criteria: Mean, SD, and range age of healthy controls
  * Extraction Result Style: ACRSL_Style
  * Notes:
    * When values are not reported, compute them when possible. For example, if sex-specific means/SDs and counts (470/99) are provided (male 17.5±8.3, female 15.6±7.0), compute the weighted mean and pooled SD, e.g., "overall 17.17±8.12 years (n=569)".
    * If fetuses are included among participants, treat fetuses as age 0 in calculations. Also note this in `general_notes`, e.g., "healthy controls include 115 days post-conception".
    * If the paper states that this information is described in another article, include details to identify that article.
    * When only partial information is available, report what is available and explicitly mark missing values as "NR" (not reported).
    * If only inclusion criteria are stated, report those with a note that actual demographics are not reported.
  * Example:
    * mean 50.00; sd 5.40; min 39; max 64
  
#### RCI-4. HC_Sex
  * Extraction Criteria: N and percentage of each sex
  * Extraction Result Style: ACRSL_Style
  * Notes:
    * Report HC sex as N and percentage for each sex.
    * Calculate percentages if only counts are provided.
    * If only one sex count is provided, calculate the other by subtraction from total N.
    * If sex distribution is not reported, state "Unknown".
  * Example:
    * F 99 (17.4%); M 470 (82.6%)

#### RCI-5. Imaging Modality
  * Extraction Criteria: Imaging modalities (semicolon separated)
  * Extraction Result Style: ACRSL_Style
  * Notes:
    * List all imaging modalities used, separated by semicolons.
    * Be specific about MRI sequences (T1-weighted, T2-weighted, T2-FLAIR, etc.).
    * For diffusion MRI, note if specific metrics are reported (FA, FAt, FW, etc.).
    * If a modality is used only for a subset or specific purpose, note that in parentheses.
  * Example:
    * T1-weighted MRI; Diffusion MRI (FA)
    * T2-FLAIR (subset for pial refinement)
  
#### RCI-6. Analysis Level
  * Extraction Criteria: Choose from "Voxel-level", "ROI-level", "Vertex-level", "Network-level", "Other"
  * Extraction Result Style: ASL_Style
  * Notes: 
    * Choose from the options

#### RCI-7. Preprocessing Pipeline
  * Extraction Criteria: Key software and processing steps
  * Extraction Result Style: ACRSL_Style
  * Notes:
    * List key software/libraries with version numbers when available, separated by semicolons.
    * Include important processing steps (parcellation scheme, registration, etc.).
    * Mention specific atlases or templates used.
    * If multiple software versions or pipelines are used for different datasets, note that.
  * Example:
    * FreeSurfer v6.0; Desikan–Killiany parcellation; hemispheric averaging
    * fMRIPrep (version NR); MNI152 registration
    * FSL DTIFIT; TBSS (skeleton threshold 0.2); JHU atlas
  
#### RCI-8. Quality Checking
  * Extraction Criteria: Yes / No
  * Extraction Result Style: A_Style
  * Notes: 
    * Choose from the options

#### RCI-9. Quality Checking Detail
  * Extraction Criteria: Quality control details (metrics, exclusion numbers, etc.)
  * Extraction Result Style: ACRSL_Style
  * Notes:
    * If "quality_checking" is yes, describe:
      - Key QC metrics used (e.g., Euler number, framewise displacement, SNR)
      - Thresholds applied
      - Number/percent of excluded subjects or data points
      - Whether QC was manual, automated, or both
      - Whether excluded subjects or QC metrics were included as covariates
      - Any sensitivity analyses related to QC
    * If "quality_checking" is no, write "-".
    * If QC is mentioned but details are minimal or referred to another paper, note that.
  * Example:
    * Euler number thresholding (median-centered |Euler| > 25) with 4% excluded; FD included as covariate; visual QC performed
    * Participants with >5% missing ROIs excluded; ±2 IQR outlier removal
  
#### RCI-10. Site Effect Handling
  * Extraction Criteria: None / Batch-removal / Model-based / Other
  * Extraction Result Style: A_Style
  * Notes: Choose from the options

#### RCI-11. Site Effect Handling Detail
  * Extraction Criteria: Site effect handling details (method names, batch variables, etc.)
  * Extraction Result Style: ACRSL_Style
  * Notes:
    * If "site_effect_handling" is not None, describe:
      - Method name (ComBat, ComBat-GAM, HBR, transfer learning, etc.)
      - Batch variables used (site, center, dataset, scanner, vendor, etc.)
      - Covariates preserved (age, sex, diagnosis)
      - Whether in-sample or out-of-sample approaches were used
      - Whether transfer learning or calibration with local controls was performed
      - Comparisons to alternative harmonization methods
      - Validation of harmonization effectiveness
    * If "site_effect_handling" is None, write "-".
  * Example:
    * ComBat by site (batch = site; covariates = age, sex); validated preservation of biological signal
    * Hierarchical Bayesian regression with site random effects; compared against ComBat
    * Transfer learning calibration using small local control set; site/scanner as batch variable
  
----


### NM. Normative Modeling Part

#### NM-1. Model Origin
  * Extraction Criteria: New / Pre-trained
  * Extraction Result Style: A_Style
  * Notes: Choose from the options

#### NM-2. Model Origin Detail
  * Extraction Criteria: Pre-training details for Pre-trained models
  * Extraction Result Style: ACRSL_Style
  * Notes:
    * If "model_origin" is pre-trained, describe:
      - Size and source of the pre-training dataset (N, number of sites)
      - Modeling method used for pre-training
      - Key covariates included in the pre-trained model
      - Reference to the original paper or resource
      - Whether and how the pre-trained model was adapted/calibrated for the current study
    * If "model_origin" is New, write "-".
  * Example:
    * Pre-trained BrainChart GAMLSS (N≈75k, multi-site); out-of-sample centiles; local calibration applied
    * Pre-trained wBLR (N≈59k, 82 sites); recalibrated with local controls

#### NM-3. Modeling Method
  * Extraction Criteria: Algorithm name (e.g., GPR, Deep Learning, GAMLSS)
  * Extraction Result Style: ACRSL_Style
  * Notes:
    * Specify the statistical/machine learning algorithm(s) used for normative modeling.
    * Include details about model formulation when relevant (e.g., warping function, basis expansion).
  * Example:
    * GAMLSS
    * Hierarchical Bayesian regression (wBLR/HBR)
    * Gaussian process regression (GPR)


#### NM-4. Software Tools
  * Extraction Criteria: Tool or library used
  * Extraction Result Style: ACRSL_Style
  * Notes:
    * Specify software packages/libraries with version numbers when available.
    * If custom implementation, note that.
    * If multiple tools are used for different steps, list them all.
  * Example:
    * PCNtoolkit v0.20
    * R (gamlss)
    * Stan / PyMC3 (custom HBR)


#### NM-5. Response Variable
  * Extraction Criteria: Response variable
  * Extraction Result Style: ACRSL_Style
  * Notes:
    * Describe the imaging-derived variable(s) being modeled.
    * Include anatomical scope (global, regional, voxelwise).
    * For regional analyses, note the parcellation scheme and number of regions.
    * For diffusion measures, specify the metrics (FA, MD, FW, etc.) and tracts/ROIs.
  * Example:
    * Cortical thickness (Desikan–Killiany, 68 ROIs)
    * Fractional anisotropy (JHU tracts via TBSS)

#### NM-6. Predictor Variables
  * Extraction Criteria: Predictor variables (semicolon separated)
  * Extraction Result Style: ACRSL_Style
  * Notes:
    * List all variables used as predictors in the normative model.
    * Separate by semicolons.
    * If sex-stratified models are used, note that instead of listing sex as a predictor.
  * Example:
    * Age; Sex; Site/Scanner; ICV; Motion (FD)

#### NM-7. Predictor Effects
  * Extraction Criteria: Fixed/random effects specification
  * Extraction Result Style: ACRSL_Style
  * Notes:
    * Describe whether predictors are modeled as fixed or random effects.
    * Note if hierarchical/multilevel structures are used.
    * Describe nonparametric modeling approaches (e.g., splines, kernels, GP).
    * If the paper does not specify fixed vs random, describe what is known about the modeling approach.
  * Example:
    * Age/Sex = fixed; Site = random (hierarchical)

#### NM-8. Normative Modeling Validation with Handling Nuisance Structure
  * Extraction Criteria: Normative Modeling Validation with Handling Nuisance Structure
  * Extraction Result Style: ACRSL_Style
  * Notes:
    * Assess whether nuisance structure is appropriately handled in the normative modeling approach.
    * Nuisance structure includes: site/scanner effects, batch effects, QC metrics (motion, Euler), age nonlinearity, heteroscedasticity, non-Gaussian distributions, etc.
    * Consider methods used: harmonization (ComBat, etc.), hierarchical/random effects, transfer learning, inclusion of covariates, sensitivity analyses.
    * Answer should be: "Yes" (adequately handled), "Partial" (some but not all addressed), or "No" (not addressed).
  * Example (answer field):
    * Yes
    * Partial
    * No

#### NM-9. Normative Modeling Validation Strategy using Same Domain Non-Independent Dataset
  * Extraction Criteria: Normative Modeling Validation Strategy using Same Domain Non-Independent Dataset
  * Extraction Result Style: ACRSL_Style
  * Notes:
    * This refers to validation on the training data itself or using non-independent resampling (e.g., bootstrap, cross-validation where normative parameters are estimated on full data).
    * Common examples: reporting model fit on training data, bootstrap assessments, sensitivity analyses on full cohort.
    * Answer should be: "Yes", "Partial", "No", or "NA" (if model is pre-trained and not re-estimated in this study).
  * Example (answer field):
    * Yes
    * Partial
    * No
    * NA

#### NM-10. Normative Modeling Validation Strategy using Same Domain Independent Dataset
  * Extraction Criteria: Normative Modeling Validation Strategy using Same Domain Independent Dataset
  * Extraction Result Style: ACRSL_Style
  * Notes:
    * This refers to validation on a held-out independent subset from the same dataset/domain (e.g., train/test split, k-fold cross-validation with proper parameter re-estimation per fold).
    * Key: normative parameters must be re-estimated on training folds only, not on full data.
    * Common examples: 70/30 split with normative model trained on 70% and validated on 30%; 10-fold CV with normative model re-fit per fold.
    * Answer should be: "Yes", "No", or "Partial".
  * Example (answer field):
    * Yes
    * No
    * Partial

#### NM-11. NM Validation Strategy using Different Domain Dataset
  * Extraction Criteria: Normative Modeling Validation Strategy using Different Domain Dataset
  * Extraction Result Style: ACRSL_Style
  * Notes:
    * This refers to validation on an external dataset from a different domain (different cohorts, sites, scanners, or populations not included in training).
    * Common examples: training on dataset A, applying to dataset B; training on HCP, applying to ADNI.
    * Transfer learning or calibration with local controls is a form of different-domain application.
    * Answer should be: "Yes", "No", or "Partial" (e.g., qualitative comparison without formal metrics).
  * Example (answer field):
    * Yes
    * No
    * Partial


### CAA. Clinical Application & Analysis Part

#### CAA-1. Clinical Dataset
  * Extraction Criteria: Name of the dataset used for clinical application
  * Extraction Result Style: ACRSL_Style
  * Notes:
    * List the name(s) of the dataset(s) containing clinical/patient participants.
    * If the same dataset provided both controls and patients, note that it is the same dataset.
    * Separate multiple datasets with semicolons.
    * If different datasets are used for different clinical groups, specify which dataset for which group.
  * Example:
    * ABIDE (ASD subset)
    * ADNI


#### CAA-2. Diseases Studied
  * Extraction Criteria: Target diseases (semicolon separated)
  * Extraction Result Style: ACRSL_Style
  * Notes:
    * Use canonical disease names; include abbreviations in parentheses on first mention if the paper uses them.
  * Example:
    * Alzheimer's disease (AD); Mild cognitive impairment (MCI)
    * Autism spectrum disorder (ASD)
  

#### CAA-3. N of Clinical Groups
  * Extraction Criteria: Sample size of clinical groups
  * Extraction Result Style: ACRSL_Style
  * Notes:
    * List sample sizes for each clinical group/diagnosis.
    * Use format "Diagnosis:N" separated by semicolons for multiple groups.
    * Include subgroups if relevant (e.g., co-occurring diagnoses, converters vs non-converters).
  * Example:
    * ASD:482
    * MCI:89; Dementia:90



#### CAA-4. Age of Clinical Groups
  * Extraction Criteria: Age of clinical groups (mean±SD and/or min-max)
  * Extraction Result Style: ACRSL_Style
  * Notes:
    * Report mean±SD when available.
    * Report range (min-max) when available.
    * For multiple clinical groups, list each separately.
    * If subgroup data (e.g., by sex) are provided, include those.
    * If data are not reported, note "NR".
  * Example:
    * EP: mean 22.70; sd 3.70
    * PD: 64.1±7.8; DLB: 73.8±6.5




#### CAA-5. Sex of Clinical Group Groups
  * Extraction Criteria: Sex breakdown of clinical groups
  * Extraction Result Style: ACRSL_Style
  * Notes:
    * Report sex as N and/or percentage for each clinical group.
    * For multiple groups, separate with semicolons.
    * Calculate from total if only one sex is reported.
  * Example:
    * EP: M 62%; F 38%
    * SCZ: F 221 (36.8%); M 380 (63.2%)


#### CAA-6. Deviation Metric
  * Extraction Criteria: How deviation from the norm was quantified
  * Extraction Result Style: ACRSL_Style
  * Notes:
    * Describe the metric(s) used to quantify individual-level deviations from the normative model.
    * Include thresholds for abnormality/extremes if specified.
    * Note if multiple metrics or composite scores are used.
  * Example:
    * Z-score (|z| ≥ 2 as extreme)
    * Centile score (e.g., <1% or >99%)
    * Total outlier count


#### CAA-7. Association Analysis
  * Extraction Criteria: Statistical analysis used to link deviation to clinical variables. Statistical linking of deviation metrics to clinical indicators, including "what" and "to what extent" (effect summary)
  * Extraction Result Style: A_Style
  * Notes:
    * Describe the statistical methods used to relate deviations to clinical outcomes.
    * Include: type of analysis (t-test, regression, classification, etc.), covariates adjusted for, multiple comparison corrections.
    * Note if associations are tested with symptom severity, diagnosis, prognosis, or other clinical variables.
  * Example:
    * Predictive performance metrics; group comparisons (ASD vs HC)
    * Welch's t-tests; multivariable regression (adjusted for age, sex)
    * The study used Welch's t-tests and Wilcoxon rank-sum tests for group comparisons, and logistic regression with ridge regularization for prediction modeling. The performance of the prediction models was evaluated using the area under the receiver operator curve (AUC).
    * Group comparisons of deviations (t-tests with FDR correction); Mann–Whitney U for extreme deviation proportions; multi-view sparse CCA (msCCA) mapping symptom domains to multimodal deviation profiles.
    * Linear mixed effects including site/sex/age/FD/Euler; Spearman correlations with ADOS, SRS, SCQ, AQ, FIQ
    * Multiple linear regressions on centile scores with covariates (age, Euler number); FDR correction for multiple comparisons; spin permutation tests for spatial correlations of effect maps; additional analyses include sex-stratified effects, age-by-diagnosis interactions, and correlations/overlap in regions with extreme deviations.
    * Case-control differences in centile scores were analysed with a bootstrapped non-parametric generalization of Welch's one-way ANOVA. Pairwise comparisons were conducted using non-parametric Monte Carlo permutation tests.
    * Linear regressions adjusting for age and sex were used to test associations between total outlier count and composite cognitive score, MoCA and visuo-perception, measured using the Hooper Visual Organisation Test. In exploratory analyses, we tested associations with disease-specific measures including global measure of severity (MDS-UPDRS), motor severity (MDS-UPDRS-III), hallucination severity (UM-PDHQ) and depression score (HADS).
    * Linear regressions adjusting for age and sex tested associations between total outlier count and composite cognitive score, MoCA, and visuoperception (HVOT); exploratory analyses with MDS-UPDRS, UMPDHQ, HADS; region-wise outlier proportions via Mann-Whitney U with FDR.
    * Multi-view CCA mapping symptoms to deviations
    * Diagnostic classification; prognostic evaluation
    * Diagnostic and prognostic classification evaluated via BACC, SPE, SEN, and AUC; HAVAs compared against single-structure normative models, deep learning baselines (ClinicaDL), and classical ML (SVM RBF, LASSO logistic regression).
    * Region-wise Area Under the ROC Curve (AUC) to evaluate the predictive power of deviations for each diagnosis, with permutation tests and FDR correction for significance.


#### CAA-8. Key Findings Brief
  * Extraction Criteria: One-sentence summary of main results. Very brief summary of the main results and author's conclusions.
  * Extraction Result Style: A_Style


#### CAA-9. Key Findings Detailed
  * Extraction Criteria: Detailed results summary (including numerical values). One-paragraph Summary: Brief summary of the main results and author's conclusions, including specific numerical values where appropriate
  * Extraction Result Style: A_Style

#### CAA-10. Key Limitations
  * Extraction Criteria: Brief summary of the limitations
  * Extraction Result Style: ASL_Style

#### CAA-11. Application Notes
  * Extraction Criteria: Any other notable techniques or innovations in the application/analysis phase
  * Extraction Result Style: A_Style
  * Notes:
    * Briefly note analysis innovations or practical caveats not captured elsewhere (e.g., subgroup stratification strategy, calibration nuances, interpretability tools).


### GN. General Note Part
  
#### GN-1. General Note  
  * Extraction Criteria: Important information not captured above. Describe any other important information not captured in the fields above.
  * Extraction Result Style: A_Style
  

-------------------

## Basic Evaluation Principles

For Extraction Items `RCI-1` 〜 `RCI-5`, `RCI-7`, `RCI-9`, `RCI-11`, `NM-2` 〜 `NM-11`, and `CAA-1` 〜 `CAA-6` (except `CAA-10`), follow the rules below when constructing answers, and return "ACRSL_Style" results.

### ACRSL_Style Format

When an item requires ACRSL_Style, provide the following structured information:

1. **Answer**: The extracted information according to the extraction criteria.

2. **Confidence Rating**: Rate your confidence as "High", "Medium", or "Low".
   - **High**: Clear and direct statements in the text; explicit numerical values or unambiguous descriptions.
   - **Medium**: Indirect or limited evidence (e.g., in supplementary materials, tables, or referenced papers); requires inference or computation from provided data.
   - **Low**: Ambiguous or insufficient description leading to uncertainty; conflicting information; or reliance on assumptions.

3. **Reason**: Provide a step-by-step explanation of how you arrived at the answer and confidence rating. Explain what information was available, how you interpreted it, and any computations or inferences made. If you computed values (e.g., percentages, pooled SD), state the formula briefly.

4. **Supporting Text**: Provide direct excerpts from the source materials that support your answer. Use quotation marks and ellipses (...) for omitted text.

5. **Location**: Specify where the supporting text was found using the document structure. Prefer the most specific locator available.
   - Format: "FileName: Section / Subsection / Location"
   - Example: "Bedford2025.pdf.md: Sample and Datasets, Paragraph 3"

### Special Cases for ACRSL_Style

- **When extraction criteria do not apply**: Answer "NA" (not applicable).
  - Provide reason explaining why it does not apply.
  - Supporting Text: "-"
  - Location: "-"

- **When information is not reported**:
  - Answer: State what is missing (e.g., "mean NR; sd NR" or "Unknown")
  - OR Answer: "Partial: [describe what IS available]"
  - Provide appropriate confidence rating, reason, supporting text (if any contextual info exists), and location. If no supporting text exists (truly absent), set Supporting Text and Location to "-" and explain in Reason how you searched.

### ASL_Style Format

When an item requires ASL_Style, provide the following structured information:

1. **Answer**: The extracted information according to the extraction criteria.
2. **Supporting Text**: Direct quotes from source materials that support the answer (keep concise).
3. **Location**: Where the quote was found.
   - Format: "FileName: Section / Subsection / Location"

Use ASL_Style for `RCI-6` and `CAA-10`.

### A_Style Format

For Assessment Items `RCI-8`, `RCI-10`, `NM-1`, `SI-1` 〜 `SI-5`, `SC-1` 〜 `SC-3`, `CAA-7`, `CAA-8`, `CAA-9`, `CAA-11`, and `GN-1`, provide only the answer without confidence rating, reason, supporting text, or location.

### Example: ACRSL_Style

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

### Example: A_Style

```json
{
  "rci8_quality_checking": "Yes"
}
``` 

### Example: ASL_Style

```json
{
  "rci6_analysis_level": {
    "answer": "ROI-level",
    "supporting_text": "\"ROI-wise cortical thickness was computed...\"",
    "location": "Paper2022.pdf.md: Methods / Imaging analysis, L120-L125"
  }
}
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
  - JSON. Match the structure of `./DE_Author20XX_by_Someone_YYYYmmddHHMMSS.json`.
- File name
  - If you are Claude Code: e.g., `DE_Bethlehem2022_by_claude_202509191115.json` (pattern: `DE_AuthorYear_by_claude_YYYYMMDDhhdd.ext`).
  - If you are Gemini CLI: e.g., `DE_Bethlehem2022_by_gemini_202509191115.json` (pattern: `DE_AuthorYear_by_gemini_YYYYMMDDhhdd.ext`).
  - If you are Codex-CLI: e.g., `DE_Bethlehem2022_by_codex_202509191115.json` (pattern: `DE_AuthorYear_by_codex_YYYYMMDDhhdd.ext`).
  - Keep filenames ASCII; avoid spaces.

- JSON formatting
  - Keep keys as specified; snake_case for item IDs.
  - For ACRSL_Style items, return strings for Answer when it contains mixed content; pure counts may be numeric or string, but be consistent within the file.
  - Do not include trailing commas; ensure valid JSON.
