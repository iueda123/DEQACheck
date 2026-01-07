# Quality Assessment Form

## Study Identification

- **Study ID**: Cirstian2024
- **Reference File Names**: Cirstian2024.pdf.md; media-5.docx.md; media-6.docx.md
- **Author, Journal, Year**: Cirstian et al., NR, 2024
- **Title**: Lifespan Normative Models of White Matter Fractional Anisotropy: Applications to Early Psychosis
- **DOI**: NR

---

## Assessment Items - Group A

### 1. Clarity of Research Objectives

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper explicitly lists four aims: building lifespan FA normative models, applying them to early psychosis, demonstrating multimodal fusion with cortical/subcortical deviations, and releasing models openly.
- **Supporting Text**: The aims of this study are to: (i) develop normative models of Fractional Anisotropy (FA)... (ii) investigate white matter FA in early psychosis... (iii) ... multi-modal data fusion ... (iv) we release all models freely to the community via our existing open-source software platforms.
- **Location**: materials/Cirstian2024.pdf.md, line 55

### 2. Clear Definition of Target Population

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: Populations are described at a high level: healthy individuals (0–100 years) for reference models and an early psychosis cohort (HCP-EP) with group sizes and demographics. However, diagnostic criteria for patients and detailed control definitions are not specified.
- **Supporting Text**: ... over 25,000 healthy individuals aged 0-100 years ... We applied these FA models to the HCP Early Psychosis cohort ...; Table S2 reports N, age, sex, and PANSS for patients and controls.
- **Location**: materials/Cirstian2024.pdf.md, lines 23–23; materials/media-6.docx.md, table

### 3. Clarity of Inclusion and Exclusion Criteria

- **Answer**: No
- **Confidence Rating**: Medium
- **Negative Answer Category**: Missing
- **Reason**: No explicit inclusion/exclusion criteria or flow of participants are described for either the reference cohorts or HCP-EP. Only a QC outlier exclusion via Z-score thresholding is mentioned in a figure caption.
- **Supporting Text**: ... quality control process using normative modeling and outlier exclusion based on Z-score thresholding ... (no inclusion/exclusion criteria reported elsewhere).
- **Location**: materials/Cirstian2024.pdf.md, line 61 (QC/outlier mention); keyword search for 'inclusion'/'exclusion' returns no additional hits

### 4. Validity of Normative Modeling Outcome Measures

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Outcome measures are clearly defined: FA extracted via DTI, TBSS skeletonization, JHU atlas tract means across 48 tracts. Deviation scores are z-scores with explicit extreme thresholds; reference population characteristics and covariates are described.
- **Supporting Text**: ... DTI modelling to extract fractional anisotropy (FA) values, Tract-Based Spatial Statistics (TBSS) ... segmentation with the Johns Hopkins University (JHU) atlas ... across 48 white matter tracts ... thresholds set at ±2.6 which correspond to a p-value of 0.01.
- **Location**: materials/Cirstian2024.pdf.md, lines 121–127; 155–157

### 5. Handling of Confounding Variables

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Models include age, sex, site, and race as covariates; datasets are stratified by sex, race, dataset, and site; site effects are acknowledged and addressed.
- **Supporting Text**: ... stratified for sex, self-reported race, dataset and site ... fit lifespan normative models ... on the basis of age, sex, site and race ... addressing ... race, and site effects ...
- **Location**: materials/Cirstian2024.pdf.md, lines 121–121 and 23–23

### 6. Clarity of Data Sources

- **Answer**: Partial
- **Confidence Rating**: High
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: Data sources are clearly named (HCP Baby/Development/Young Adult/Aging and UK Biobank), and patient cohort is HCP-EP. However, acquisition time and locations are not detailed here.
- **Supporting Text**: ... five cohorts ... HCP Baby, HCP Development, HCP Young Adult, HCP Aging, and UK Biobank ... applied to the HCP early psychosis (HCP-EP) dataset ...
- **Location**: materials/Cirstian2024.pdf.md, lines 121–121; 131–131

### 7. Description of Image Acquisition Protocol

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: Mentions multi-shell diffusion data and harmonised FSL-based pipelines, but does not provide scanner manufacturer, field strength, or sequence parameters (TR/TE, b-values) in this document.
- **Supporting Text**: First, we assembled high-quality multi-shell diffusion data ... harmonised FSL-based pipelines ...
- **Location**: materials/Cirstian2024.pdf.md, line 121

---

## Assessment Items - Group B

### 1. Details of Data Preprocessing

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Preprocessing steps and tools are specified: intensity normalisation, distortion/motion corrections (FSL-based), DTI modelling, TBSS skeletonization, and JHU atlas tract extraction; QC via z-score thresholding is illustrated.
- **Supporting Text**: ... processed using harmonised FSL-based pipelines, involving preprocessing (intensity normalisation, distortion and movement corrections), DTI modelling ... TBSS ... JHU atlas ...; quality control process ... outlier exclusion based on Z-score thresholding.
- **Location**: materials/Cirstian2024.pdf.md, lines 121–127; 61

### 2. Clarity of Data Partitioning Methods

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: A clear 50/50 train-test split (N=12,457 each) is reported and stratified by sex, race, dataset, and site. Additional 70/30 splits with repetition are described for the msCCA analysis.
- **Supporting Text**: Total N=24,915, (N=12,457 for training and N=12,457 for test, stratified for sex, self-reported race, dataset and site) ... randomly split the data 1000 times into training (70%) and test (30%).
- **Location**: materials/Cirstian2024.pdf.md, lines 121–121; 161–167

### 3. Details of Normative Modeling Approach

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Normative models are specified as warped Bayesian linear regression with non-linear basis expansion over age, including covariates age, sex, site, and race; prior related work is cited.
- **Supporting Text**: We then fit lifespan normative models ... using warped Bayesian linear regression (BLR) and a non-linear basis expansion over age ... on the basis of age, sex, site and race ... in line with our prior work [4], [25].
- **Location**: materials/Cirstian2024.pdf.md, line 121

### 4. Details of Training Algorithm

- **Answer**: No
- **Confidence Rating**: Medium
- **Negative Answer Category**: Missing
- **Reason**: Specific optimization algorithms, hyperparameter search strategies, and stopping criteria for fitting the normative models are not described in this document.
- **Supporting Text**: Not reported beyond model class (BLR) and covariates; no optimizer or hyperparameter details found.
- **Location**: materials/Cirstian2024.pdf.md, global review; no matching lines for optimization/hyperparameter terms

### 5. Model Performance Evaluation Metrics

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Performance metrics for normative fits are defined and interpreted: Explained Variance (EV) for median fit and skewness/kurtosis for distribution shape; out-of-sample evaluation is reported with summary statistics.
- **Supporting Text**: We assessed the quality ... using ... explained variance (EV) ... in addition to skewness and kurtosis ... The mean (standard deviation) EV was 0.37 (0.10) ... Skewness and kurtosis were respectively -0.09 (0.12) and 0.42 (0.27).
- **Location**: materials/Cirstian2024.pdf.md, line 121

### 6. Implementation of Internal Validation

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Internal validation via an out-of-sample test set is described and used to compute EV, skewness, and kurtosis.
- **Supporting Text**: ... out-of-sample metrics, namely explained variance (EV) ... skewness and kurtosis ...
- **Location**: materials/Cirstian2024.pdf.md, line 121

### 7. External Data Validation

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The models are applied to an independent HCP-EP clinical dataset to assess deviations and group differences.
- **Supporting Text**: ... applied these reference models to the HCP early psychosis (HCP-EP) dataset ... derive z-scores for each individual and tract ...
- **Location**: materials/Cirstian2024.pdf.md, lines 129–131

### 8. Description of Dataset Characteristics

- **Answer**: Partial
- **Confidence Rating**: High
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: Sample sizes and demographics (age, sex, and race distributions) are provided for training/reference datasets (Table S1) and the HCP-EP clinical cohort (Table S2). Handling of missing data is not described.
- **Supporting Text**: Table S1 shows age ranges and sex/race composition per dataset (HCPA/HCPB/HCPD/HCPYA/UKB). Table S2 reports N, age, sex, race, and PANSS for patients and controls.
- **Location**: materials/media-5.docx.md, table; materials/media-6.docx.md, table

### 9. Performance Metrics and Statistical Uncertainty

- **Answer**: Partial
- **Confidence Rating**: High
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: Point estimates and standard deviations are reported for EV, skewness, and kurtosis. For clinical comparisons, p-values are reported. Confidence intervals or credible intervals are not provided.
- **Supporting Text**: The mean (standard deviation) EV was 0.37 (0.10) ... Skewness ... -0.09 (0.12) and 0.42 (0.27) ... Mann-Whitney U=1403.0, p=0.0036; U=1517.0, p=0.0016.
- **Location**: materials/Cirstian2024.pdf.md, lines 121–131

### 10. Consideration for Reproducibility

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The paper states that all models are publicly available via open-source platforms, but specific software versions, code repositories, or model download links are not detailed here.
- **Supporting Text**: All models are publicly available for community use. ... we release all models freely to the community via our existing open-source software platforms [19].
- **Location**: materials/Cirstian2024.pdf.md, lines 23–23; 55–55

### 11. Interpretation Specific to Normative Modeling

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The study interprets individual-level deviation patterns (extreme z-scores) in psychosis, discusses their prevalence across tracts, and connects multimodal deviations to symptom domains via msCCA.
- **Supporting Text**: ... thresholds set at ±2.6 ... percentage overlap of extreme deviations ...; combined FA deviations with cortical thickness and subcortical volume ... msCCA ... significant mean test canonical correlation of r=0.25 ...
- **Location**: materials/Cirstian2024.pdf.md, lines 155–157; 161–167

---

## Additional Comments

**Additional Comments**: Journal name and DOI are not reported in the provided materials. Detailed acquisition parameters (scanner vendor, field strength, TR/TE, b-values) are also not included; readers are referred to HCP/UKB protocols by citation.
