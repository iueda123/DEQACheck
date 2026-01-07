# Quality Assessment Form

## Study Identification

- **Study ID**: Bhome2024
- **Reference File Names**: Bhome2024.pdf; 1-s2.0-S2213158224000354-mmc1.docx
- **Author, Journal, Year**: Bhome et al., NeuroImage: Clinical, 2024
- **Title**: A neuroimaging measure to capture heterogeneous patterns of atrophy in Parkinson’s disease and dementia with Lewy bodies
- **DOI**: 10.1016/j.nicl.2024.103596

---

## Assessment Items - Group A

### 1. Clarity of Research Objectives

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Study objectives (quantification of heterogeneity of individual atrophy patterns in PD and DLB, verification of clinical relevance of total outlier count, stratification by group comparison and visual performance, assessment of dissimilarity by Hamming distance, and verification of relevance to clinical index) clearly stated.
- **Supporting Text**: Here, we employed neuroanatomical normative modelling to investigate heterogeneity in Lewy body diseases and evaluate the potential of this technique to provide useful measures of disease severity...
We hypothesised that there would be a) significant differences in total number of regional outliers between high and poor visual performance PD groups, and in PD compared with DLB; b) greater dissimilarity in individual patients for low versus high visual performers in PD; and for DLB compared to PD. Finally, we predicted c) that greater total outlier count would be associated with poorer cognitive performance in PD and DLB.
- **Location**: p.2, Section 1–2

### 2. Clear Definition of Target Population

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The diagnostic criteria for PD and DLB (Queen Square Brain Bank and DLB Consortium Criteria) are clearly stated and the age groups of the target and control groups are clearly defined.
- **Supporting Text**: We included 108 participants with PD, 36 with DLB and 38 controls, from the Vision in Parkinson’s disease study… diagnosed as having PD or probable DLB if they satisfied Queen Square Brain Bank PD diagnostic criteria and the Dementia with Lewy Bodies Consortium Criteria respectively. Inclusion criteria were being aged 50–80...
- **Location**: p.2, Section 2.1 Participants

### 3. Clarity of Inclusion and Exclusion Criteria

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The UCL site presents clear inclusion/exclusion based on age band and medical history; NACC describes inclusion rules based on descriptive items in the database. However, the flow chart of the number of people in the screening stage, the breakdown of reasons for exclusion at each stage, and the process of reaching the final number of analyses are unclear.
- **Supporting Text**: Exclusions were a history of traumatic brain injury, or major co-morbid psychiatric or confounding neurological disorders; and for participants with PD, presence of dementia was also an exclusion criterion, defined using Movement Disorder Society criteria.
- **Location**: p.2, Section 2.1 Participants

### 4. Validity of Normative Modeling Outcome Measures

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Site-specific z-scoring for the reference cohort (n=58,836), outlier definition at threshold (z<-1.96), derivation of total outlier count, and assessment of dissimilarity in Hamming distance are detailed.
- **Supporting Text**:Outliers (z < −1.96) were aggregated across 169 brain regions per participant… Hamming distance scores were calculated…
- **Location**: p.3, Section 2.6.1/2.6.2

### 5. Handling of Confounding Variables

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Regression analysis adjusted for age and gender. Site differences were corrected by recalibrating the reference model (input control data) using PCNToolkit and transfer learning. The reference model itself was constructed using warped Bayesian linear regression with age and sex as covariates and site differences taken into account.
- **Supporting Text**: Linear regressions, correcting for age and sex, were used to test for group differences in total outlier count between high and low visual performers with PD; and between DLB and PD...
- **Location**: p.3, Section 2.6.1/2.5/2.4

### 6. Clarity of Data Sources

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Data sources (UCL research facilities and NACC database) are clearly indicated and their origin and characteristics are detailed; NACC cohort conditions are also clearly stated.
- **Supporting Text**: The first site at University College London (UCL)…
- **Location**: p.2, Section 2.1 Participants

### 7. Description of Image Acquisition Protocol

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: In UCL, MPRAGE details (voxel, TE/TR, flip angle, and imaging time) of 3T Prisma, FreeSurfer v6.0 processing, and even QC method (visual confirmation of boundaries) are clear. On the other hand, NACC states "1.5T GE" and "details can be referenced in NACC" and imaging parameters are not covered in the text.
- **Supporting Text**: Structural T1w-MRI scans at UCL were acquired on a 3 T Siemens Magnetom Prisma scanner with a 64-channel head coil. MPRAGE data were acquired using the following parameters…
- **Location**: p.3, Section 2.3 MRI acquisition and processing

---

## Assessment Items - Group B

### 1. Details of Data Preprocessing

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Pre-processing procedures are very clear. The software used (FreeSurfer v6.0.0), the processing function (recon-all), and the QC method (whole brain visual check by an expert) are clearly stated.
- **Supporting Text**: The ‘recon-all’ function in FreeSurfer v6.0.0 was used to generate cortical and subcortical segmentations …
- **Location**: p. 3 (2.3 Image acquisition and processing)

### 2. Clarity of Data Partitioning Methods

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The PCNToolkit's transfer learning is used to recalibrate the reference model, but specific split ratios to train/validate/test are not provided.
- **Supporting Text**: The reference normative model was recalibrated with a subset of site-specific healthy controls using an adapted transfer learning approach …
- **Location**: p. 3 (2.5 Normative modelling)

### 3. Details of Normative Modeling Approach

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Specifies the method as "Warped Bayesian Linear Regression" and explains that it treats age and sex as covariates and site differences as random effects. The software (PCNToolkit v0.20) is also specified. The purpose of the model (calculation of site-specific z-scores) and the units of treatment (169 ROI) are described.
- **Supporting Text**: The reference model was trained using a warped Bayesian Linear Regression with age and sex as covariates, and accounting for site differences.
- **Location**: p. 3 (2.4 Reference model)

### 4. Details of Training Algorithm

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: It specifies "Bayesian Linear Regression" but does not provide specific values for the optimization algorithm, iteration conditions, convergence criteria, and regularization coefficients, which are presumed to be in accordance with the default implementation of PCNToolkit.
- **Supporting Text**: All modelling steps were performed using PCNToolkit (v0.20), implementing a warped Bayesian Linear Regression framework.
- **Location**: p. 3 (2.4 Reference model)

### 5. Model Performance Evaluation Metrics

- **Answer**: No
- **Confidence Rating**: High
- **Negative Answer Category**: Missing
- **Reason**: No model fit indices are reported. Analysis results are limited to z-scores and Hamming distance distributions for descriptive and group comparisons.
- **Supporting Text**:
- **Location**:

### 6. Implementation of Internal Validation

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: There is no explanation of cross-validation or quantitative evaluation using retained-out samples, although there is a statement that performance was partially confirmed using HC at the time of transfer learning.
- **Supporting Text**:
- **Location**:

### 7. External Data Validation

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: External data sets (NACC vs. UCL) were compared to each other to reproduce patterns of outlier counts and Hamming distances. Results for both sites are presented in Figure 2 to confirm reproducibility.
- **Supporting Text**: Patterns of outlier distribution and Hamming distance scores were comparable across the UCL and NACC datasets.
- **Location**: p. 5 (Results / Fig. 2)

### 8. Description of Dataset Characteristics

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Statistics on sample size (UCL: PD 54, DLB 31, HC 40, etc./NACC: DLB 63, HC 108, etc.), age, sex, and cognitive score are detailed in Table 1 and 2.
- **Supporting Text**:
- **Location**: p. 4 (Tables 1–2)

### 9. Performance Metrics and Statistical Uncertainty

- **Answer**: Yes
- **Confidence Rating**: Medium
- **Negative Answer Category**: Not Negative
- **Reason**: t-values and p-values are clearly indicated. 95 % CI is not available, but is sufficient as uncertainty information.
- **Supporting Text**:
- **Location**: p. 5 (Results)

### 10. Consideration for Reproducibility

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The software used (.20) and the reference model (Rutherford et al., 2022) are explicitly stated and reproducible, but there is no URL in the text for code sharing or data publication.
- **Supporting Text**: All modelling steps were performed using PCNToolkit (v0.20)… Data will be made available on request.
- **Location**: p. 3 (2.4)

### 11. Interpretation Specific to Normative Modeling

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The clinical significance of outlier count and Hamming distance (in relation to cognition, visual function, and disease type discrimination) is clearly discussed, as well as the usefulness of individual difference analysis based on the limitations of case-control.
- **Supporting Text**: Our results demonstrate that total outlier count captures clinically relevant heterogeneity in neurodegeneration across PD and DLB …
- **Location**: p. 6–7 (Discussion)

---

## Additional Comments

**Additional Comments**: This study is a representative example of applying a warped Bayesian normative model by PCNToolkit to a clinical cohort.
Strengths: Transfer learning of multicenter data, clear covariate settings, replication in UCL/NACC.
