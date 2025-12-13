# Quality Assessment Form

## Study Identification

- **Study ID**: Cirstian2024
- **Reference File Names**: Cirstian2024.pdf; media-1 to media-6 .docx
- **Author, Journal, Year**: Cirstian et al., bioRxiv [Preprint], 2024
- **Title**: Lifespan normative modelling of white-matter microstructure: fractional anisotropy trajectories and clinical deviations in early psychosis
- **DOI**: 10.1101/2024.12.11.627897.

---

## Assessment Items - Group A

### 1. Clarity of Research Objectives

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The study objectives are clearly defined and aim to quantify age-related changes in FA values across the lifespan using normative modeling and to assess deviance in patients with early psychosis (EP).
- **Supporting Text**: We aimed to establish lifespan normative models of fractional anisotropy (FA) across 48 white-matter tracts and evaluate individual-level deviations in early psychosis patients relative to these norms.
- **Location**: p. 1 (Introduction)

### 2. Clear Definition of Target Population

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The healthy group consists of HCP-A/B/D/YA and UK Biobank, with age, sex, and race ratios detailed in Supplementary Table S1. The patient group consists of 118 patients and 28 controls from the HCP Early Psychosis dataset (EP). Age, sex, race composition, and PANSS scores are clearly described.
- **Supporting Text**: Patients (N = 118) and controls (N = 28) from the HCP Early Psychosis dataset were included…
- **Location**: p. 2 (Participants section); Supplement Tables S1–S2

### 3. Clarity of Inclusion and Exclusion Criteria

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The conditions for use of the HCP EP dataset (early psychosis diagnosis, absence of neurological disorder) are indicated, but the exclusion procedure and screening process (number of people excluded, reasons) are not clear.
- **Supporting Text**: The HCP EP dataset included patients with early psychosis diagnoses and matched controls; participants with neurological conditions were excluded.
- **Location**: p. 2 (Methods)

### 4. Validity of Normative Modeling Outcome Measures

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: 48 Normalized models were constructed for FA values of white matter tracts with age, sex, and race (race) as covariates. z-scores were used to define deviance, and outlier rates were calculated with a threshold of ±2.6. These are visualized in Supplementary Fig. S4.
- **Supporting Text**: Normative models were fitted to FA values for 48 white-matter tracts using PCNToolkit; outliers were defined as Z > 2.6 or Z < –2.6
- **Location**: p. 3 (Normative modelling section); Supplement Fig. S4

### 5. Handling of Confounding Variables

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Age and sex are treated as covariates in all models, with and without race (race) in the comparison model (Fig. S2 vs. S3).
- **Supporting Text**: These models included race as a covariate accounting for its potential influence on FA (Fig. S2), and models without race were also examined (Fig. S3).
- **Location**: p. 3–4; Supplement Figs S2–S3

### 6. Clarity of Data Sources

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Training and testing data sets are explicit (HCP A/B/D/YA and UK Biobank); age ranges and composition ratios are presented in Supplement Table S1.
- **Supporting Text**: The lifespan dataset combined HCP (A/B/D/YA) and UK Biobank cohorts, spanning ages from infancy to old age (Table S1).
- **Location**: p. 2 (Datasets); Supplement Table S1

### 7. Description of Image Acquisition Protocol

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The DWI imaging conditions for each HCP and UKB are based on the source (see individual project specifications for b-values, number of directions, and TR/TE). The imaging conditions are outlined in this paper, but not all parameter details are provided.
- **Supporting Text**: Diffusion-weighted MRI data were acquired as part of the HCP and UK Biobank protocols using multi-shell designs (b = 1000–3000 s/mm²) and standardised acquisition pipelines.
- **Location**: p. 3 (Methods: MRI acquisition)

---

## Assessment Items - Group B

### 1. Details of Data Preprocessing

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Multi-shell diffusion MRI from HCP/UKB processed with unified FSL pipeline: B0 intensity normalization, EPI distortion correction, eddy current and motion correction (HCP pipeline for HCP, UKB standard processing for UKB) FA calculation with DTIfit → TBSS skeletonize (FMRIB58_FA, threshold 0.2) → extract 48 WM tracts with JHU atlas
- **Supporting Text**: B0 intensity normalization, … EPI distortions, eddy-current-induced and movement corrections… estimated the DTI model using DTIfit… ran TBSS…
- **Location**: p.10–11 (Data acquisition and processing)

### 2. Clarity of Data Partitioning Methods

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Lifespan data (N=24,915) split study/test=1:1 (stratified by gender, race, data set, and site). Refit to exclude outliers >±5 SD prior to modeling. In addition, clinical analysis validated generalization performance with 70/30 repeated splits x 1000 + replacement test for msCCA.
- **Supporting Text**: Total N=24,915 (N=12,457 training; N=12,457 test), stratified for sex, race, dataset and site…
- **Location**: p.4（Normative modeling)

### 3. Details of Normative Modeling Approach

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Warped Bayesian Linear Regression (BLR) to model age, sex, race, and site. Three-order B-spline basis (5 knots) to handle age nonlinearity and SinhArcsinh warping to handle non-Gaussianity, implemented with PCNToolkit.
- **Supporting Text**: fit lifespan normative models… using warped Bayesian linear regression (BLR)…
- **Location**: p.4（Normative modeling)

### 4. Details of Training Algorithm

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: BLR and warping, basis functions and covariates are detailed, but optimization procedures (iteration/convergence criteria, specifics of prior distribution, regularization hyper settings) are not specified.
- **Supporting Text**: All statistical analyses were conducted using Python… PCN toolkit
- **Location**: p.4（Normative modeling)

### 5. Model Performance Evaluation Metrics

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: External (test) evaluations of Explained Variance (EV), Skewness, and Kurtosis were reported (mean EV=0.37±0.10, Skew=-0.09±0.12, Kurtosis=0.42±0.27); distributions are also presented in Fig. S1.
- **Supporting Text**: We assessed the quality of the normative modeling fit using…
- **Location**: p.4（Normative modeling)

### 6. Implementation of Internal Validation

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: (i) Lifespan data verified external generalization with stratified learning/testing. (ii) Clinical data msCCA confirmed test correlation r=0.25, p=0.003 with 1000 70/30 splits and replacement test.
- **Supporting Text**: randomly split the data 1000 times into training (70%) and test (30%)…
- **Location**: p.7 (Results)

### 7. External Data Validation

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: Study/test was conducted in an internal framework integrating multiple HCP/UKB cohorts. Not presented until replication in independent separate study cohorts (e.g., separate EP cohorts).
- **Supporting Text**:
- **Location**: Fig.2-3; Supp. Figs S2–S4

### 8. Description of Dataset Characteristics

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Age range, sex, and race composition of each lifespan data subcohort is detailed in Table S1 (HCPA/HCPB/HCPD/HCPYA/UKB). The number, age, sex, race, and PANSS summary of the clinical cohort (HCP-EP) are organized in Table S2.
- **Supporting Text**: Table S1: demographic characteristics… age ranges, gender distribution, racial composition for HCPA, HCPB, HCPD, HCPYA and UKB.
- **Location**:Supp. Table S1; Supp. Table S2

### 9. Performance Metrics and Statistical Uncertainty

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: (i) Normalized fit presents EV/Skew/Kurtosis (good). (ii) Between clinical groups, mean deviance differences are evaluated by t-test + FDR, and extreme deviance ratios are compared by Mann-Whitney U + FDR (U and p-value explicit). (iii) msCCA is p=0.003 with replacement test; however, interval estimates such as effect size CIs are not systematically presented.
- **Supporting Text**: There were no significant differences… after FDR… schizophrenia had a greater proportion of extreme positive (U=1403.0, p=0.0036) and extreme negative (U=1517.0, p=0.0016) Z-scores… significant mean test canonical correlation r=0.25 (p=0.003).
- **Location**: p.5–7(Results)

### 10. Consideration for Reproducibility

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Tools and public resources used are specified (PCNToolkit, HCP-pipeline, GitHub repository). Mentioned as applicable in the Open Source/No Code Portal.
- **Supporting Text**: via our established no-code software platform and via open-source software tools (https://github.com/ramonacirstian/fa_normative_modeling)
- **Location**: p.10(Discussion)

### 11. Interpretation Specific to Normative Modeling

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Emphasizes that even without case-control differences, patients show increased rates of extreme deviations = highly individualized abnormalities. msCCA right hook bundle FA and bilateral thalamic volumes are associated with **symptoms (PANSS)**, indicating the usefulness of individual-level brain-symptom mapping.
- **Supporting Text**: 
- **Location**:

---

## Additional Comments

**Additional Comments**: FA normative reference for the largest size class, stratified testing + msCCA resampling to ensure robustness, and fairness considerations with comparisons with and without racial covariates (Fig. S2/S3).
