# Quality Assessment Form

## Study Identification

- **Study ID**: Berthet2025
- **Reference File Names**: Berthet2025.pdf.md
- **Author, Journal, Year**: Berthet et al., Schizophrenia Bulletin, 2025
- **Title**: A 10-Year Longitudinal Study of Brain Cortical Thickness in People with First-Episode Psychosis Using Normative Models
- **DOI**: NR

---

## Assessment Items - Group A

### 1. Clarity of Research Objectives

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The main goal of the study is clearly stated in the introduction. The authors aim to map the associations between brain cortical abnormalities and clinical symptoms over a long-term period using a normative modeling approach.
- **Supporting Text**: Our main goal in this study was to map the associations between brain cortical abnormalities and clinical symptoms over the longer term. To achieve this, we applied a normative modeling approach to magnetic resonance imaging (MRI)-based estimates of cerebral CT of people with schizophrenia spectrum first-episode psychosis and healthy controls (CTRL) in a long-term longitudinal study of participants with follow-up after approximately 12 months and 10 years.
- **Location**: Berthet2025.pdf.md, lines 63-68

### 2. Clear Definition of Target Population

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper clearly defines the target population as patients with a first-episode schizophrenia spectrum diagnosis and healthy controls. It also specifies the diagnostic criteria used (DSM-IV).
- **Supporting Text**: Here, patients with a first-episode schizophrenia spectrum diagnosis (SCZ) were consecutively recruited from the catchment-area-based inpatient and outpatient services at Oslo University Hospital and 3 additional hospitals in the larger Oslo area to the prospective study. Psychiatric diagnosis at baseline was established using the Structured Clinical Interview for DSM-IV Axis I Disorders (SCID-I 40 ), and we included a broad range of schizophrenia spectrum diagnoses: schizophrenia ( n = 57, 72% of the final, quality-checked longitudinal sample), schizophreniform disorders ( n = 18, 23%), and schizoaffective ( n = 4, 5%).
- **Location**: Berthet2025.pdf.md, lines 73-78

### 3. Clarity of Inclusion and Exclusion Criteria

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The paper describes exclusion criteria for the healthy control group, but not for the patient group. It also mentions an automatic quality check procedure for MRI data, but doesn't provide a detailed flowchart of participant selection.
- **Supporting Text**: Exclusion criteria for healthy CTRL included a history of drug or alcohol abuse or dependency, psychosis, bipolar disorder, or major depressive disorder, or having a first-degree relative diagnosed with a psychotic or bipolar disorder... An automatic quality check procedure based on the Freesurfer Euler characteristic was run on all data and samples with a value higher than 5 were removed.
- **Location**: Berthet2025.pdf.md, lines 81-83 and 98-100

### 4. Validity of Normative Modeling Outcome Measures

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The outcome measure (deviation scores from a normative model of cortical thickness) is clearly defined. The paper explains how these scores are calculated and what they represent.
- **Supporting Text**: Normative models for cortical thickness estimated on public MRI datasets ( n = 42 983) were applied to TOP data to obtain deviation scores for each region and timepoint.
- **Location**: Berthet2025.pdf.md, lines 26-28

### 5. Handling of Confounding Variables

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The study accounts for age, sex, and scanner effects by including them as covariates in the normative model.
- **Supporting Text**: We estimated a normative model for each region of interest (ROI, n = 150) in the Freesurfer Destrieux atlas, 52 using HBR with age as a covariate, and sex and scanner id as batch effects, to predict CT.
- **Location**: Berthet2025.pdf.md, lines 102-105

### 6. Clarity of Data Sources

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper clearly states that the data is from the TOP (Thematically Organized Psychosis) study. It also mentions the use of publicly available datasets for training the reference normative models.
- **Supporting Text**: All participants were recruited to a specific first-episode sub-study of the TOP study at the University of Oslo and Oslo University Hospital from October 27, 2004, to October 17, 2012.
- **Location**: Berthet2025.pdf.md, lines 72-74

### 7. Description of Image Acquisition Protocol

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper provides detailed information about the MRI scanners and the T1-weighted imaging sequences used.
- **Supporting Text**: Three scanners at Oslo University Hospital were used in this longitudinal study without temporal overlap. The first scanner was a 1.5 Tesla Siemens MANETOM Sonata scanner... The second scanner was a 3 Tesla GE Signa HDxT... The third scanner was a 3 Tesla GE 750 Discovery scanner...
- **Location**: Berthet2025.pdf.md, lines 89-96

---

## Assessment Items - Group B

### 1. Details of Data Preprocessing

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper specifies the software used for preprocessing (Freesurfer v5.3) and the parcellation atlas (Destrieux). It also mentions an automatic quality control procedure.
- **Supporting Text**: T1-weighted structural MRI scans were preprocessed through Freesurfer (version 5.3), and CT measures were parcellated using the Destrieux atlas. 52 An automatic quality check procedure based on the Freesurfer Euler characteristic was run on all data and samples with a value higher than 5 were removed.
- **Location**: Berthet2025.pdf.md, lines 97-100

### 2. Clarity of Data Partitioning Methods

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper describes the data partitioning for training and validation of the reference normative model (95% for training, 5% for validation). It also mentions that the model was then adapted to the 3 Oslo scanners using held-out cross-sectional data.
- **Supporting Text**: ...the reference normative models were first trained on (95%) healthy individuals and validated on an independent set of 2548 CTRL and patients (5%, stratified by sites). We then adapted the model to the 3 unseen Oslo scanners, by transferring the (hyper) parameters as informed priors for these new sites. 55 For this adaptation step, we used held-out cross- sectional data from CTRL from these 3 scanners...
- **Location**: Berthet2025.pdf.md, lines 108-112

### 3. Details of Normative Modeling Approach

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper clearly states the use of Hierarchical Bayesian Regression (HBR) for normative modeling.
- **Supporting Text**: To account for site and scanner effects, we used the Hierarchical Bayesian regression (HBR) approach for normative modeling, 54,55,59 which efficiently accommodates inter-site variation and provides computational scaling, which is useful for multi-cohort and longitudinal studies with data from different scanners.
- **Location**: Berthet2025.pdf.md, lines 100-102

### 4. Details of Training Algorithm

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The paper mentions the use of HBR, but does not go into the specifics of the training algorithm, optimization process, or hyperparameter settings. It refers to other papers for these details.
- **Supporting Text**: The paper refers to references 54, 55, and 59 for the details of the HBR approach.
- **Location**: Berthet2025.pdf.md, line 101

### 5. Model Performance Evaluation Metrics

- **Answer**: No
- **Confidence Rating**: High
- **Negative Answer Category**: Missing
- **Reason**: The paper does not report performance metrics (like MSLL, Pearson correlation, etc.) for the normative model itself. It focuses on the analysis of the deviation scores.
- **Supporting Text**: N/A (Information is missing)
- **Location**: N/A

### 6. Implementation of Internal Validation

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The reference normative model was validated on an independent set of 2548 participants (5% of the total dataset). The adaptation to the Oslo scanners was also done using held-out data.
- **Supporting Text**: ...the reference normative models were first trained on (95%) healthy individuals and validated on an independent set of 2548 CTRL and patients (5%, stratified by sites).
- **Location**: Berthet2025.pdf.md, lines 108-110

### 7. External Data Validation

- **Answer**: NA
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The study's main goal was not to build a new normative model, but to apply an existing one (after adaptation) to a new longitudinal dataset. Therefore, external validation in the traditional sense is not applicable. The application of the model to the TOP study data can be seen as a form of external validation of the model's utility.
- **Supporting Text**: N/A
- **Location**: N/A

### 8. Description of Dataset Characteristics

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper provides a table (Table 1) with demographic information (N, age, sex ratio) for both patients and controls at all three time points.
- **Supporting Text**: Table 1. Demographics of the Participants at the 3 Time Points
- **Location**: Berthet2025.pdf.md, line 148

### 9. Performance Metrics and Statistical Uncertainty

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper reports p-values (FDR corrected) and effect sizes (Cohen's d) for the statistical analyses performed on the deviation scores.
- **Supporting Text**: LMEs revealed significant ( P < .05, FDR corrected) conditional main effects...
Briefly, the Cohen's d for median thickness deviation score at baseline is -0.46, -0.43 at 12-month follow-up and -0.27 at 10-year follow-up.
- **Location**: Berthet2025.pdf.md, lines 155-156 and 164-166

### 10. Consideration for Reproducibility

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The paper mentions the software version used for preprocessing (Freesurfer 5.3). However, it does not mention the availability of the code, trained models, or the data itself.
- **Supporting Text**: T1-weighted structural MRI scans were preprocessed through Freesurfer (version 5.3)...
- **Location**: Berthet2025.pdf.md, line 97

### 11. Interpretation Specific to Normative Modeling

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The discussion section provides a thorough interpretation of the findings in the context of normative modeling, comparing the results to traditional case-control studies and discussing the clinical implications.
- **Supporting Text**: This study demonstrates that transfer learning from large-scale reference normative models can be used to make meaningful comparisons of MRI features between participants across different scanners and provides preliminary evidence for cortical associations with longitudinal clinical outcomes in people with schizophrenia.
- **Location**: Berthet2025.pdf.md, lines 268-271

---

## Additional Comments

**Additional Comments**: 
