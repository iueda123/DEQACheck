# Quality Assessment Form

## Study Identification

- **Study ID**: Berthet2025
- **Reference File Names**: Berthet2025.pdf.md; media-1.pdf.md
- **Author, Journal, Year**: Berthet et al., Schizophrenia Bulletin, 2025
- **Title**: A 10-Year Longitudinal Study of Brain Cortical Thickness in People with First-Episode Psychosis Using Normative Models
- **DOI**: NR

---

## Assessment Items - Group A

### 1. Clarity of Research Objectives

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper clearly states its main goal to map associations between brain cortical abnormalities and clinical symptoms over the long term using normative modeling approach. The PICO elements are identifiable: population (first-episode psychosis patients and controls), intervention (normative modeling of MRI-based cortical thickness), comparator (healthy controls), and outcome (individual deviation scores and their associations with symptoms over 10 years). Research predictions are explicitly stated.
- **Supporting Text**: Our main goal in this study was to map the associations between brain cortical abnormalities and clinical symptoms over the longer term. To achieve this, we applied a normative modeling approach to magnetic resonance imaging (MRI)-based estimates of cerebral CT of people with schizophrenia spectrum first-episode psychosis and healthy controls (CTRL) in a long-term longitudinal study... Given prior evidence for the heterogeneity of cortical alterations in schizophrenia and that only a subset of individuals with schizophrenia show progressive brain changes, we predicted that: (i) we would observe a characteristic yet diffuse pattern of case-control differences in cortical normative deviations, consistent with prior studies and (ii) individual differences in cortical deviations would be coupled to clinical outcome over time.
- **Location**: Berthet2025.pdf.md:27

### 2. Clear Definition of Target Population

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The target population is clearly defined with specific diagnostic criteria. Patients had first-episode schizophrenia spectrum diagnoses established using SCID-I, including schizophrenia (72%), schizophreniform disorders (23%), and schizoaffective (5%). Healthy controls had clear exclusion criteria for psychiatric disorders and family history. Age ranges are provided for both groups.
- **Supporting Text**: Here, patients with a first-episode schizophrenia spectrum diagnosis (SCZ) were consecutively recruited from the catchment-area-based inpatient and outpatient services... Psychiatric diagnosis at baseline was established using the Structured Clinical Interview for DSM-IV Axis I Disorders (SCID-I), and we included a broad range of schizophrenia spectrum diagnoses: schizophrenia (n=57, 72% of the final, quality-checked longitudinal sample), schizophreniform disorders (n=18, 23%), and schizoaffective (n=4, 5%)... Exclusion criteria for healthy CTRL included a history of drug or alcohol abuse or dependency, psychosis, bipolar disorder, or major depressive disorder, or having a first-degree relative diagnosed with a psychotic or bipolar disorder.
- **Location**: Berthet2025.pdf.md:31-33

### 3. Clarity of Inclusion and Exclusion Criteria

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Inclusion and exclusion criteria are clearly described for both patients and controls. For patients, first-episode definition is specified (first adequate treatment within last 12 months, not previously treated with antipsychotics >12 weeks). General criteria include age ranges, language requirements, no severe head trauma, IQ >70. Quality control procedures using Euler characteristic (cutoff >5 removed) are detailed. Flow of participants is described in supplementary materials.
- **Supporting Text**: Patients (aged 18-53 years) and healthy controls (aged 13-56 years) understood and spoke a Scandinavian language, had no history of severe head trauma, and had an IQ above 70... Patients were not considered to be first-episode patients if they previously, on any occasion before the starting point of the index treatment, had been treated with antipsychotic medication for more than 12 weeks (or shorter if symptomatic remission was achieved before 12 weeks)... An automatic quality check procedure based on the Freesurfer Euler characteristic was run on all data and samples with a value higher than 5 were removed.
- **Location**: media-1.pdf.md:45-49; Berthet2025.pdf.md:43

### 4. Validity of Normative Modeling Outcome Measures

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Brain measures are clearly defined as cortical thickness from Destrieux atlas (150 ROIs). The normative modeling approach using Hierarchical Bayesian Regression (HBR) is detailed, including how individual deviation scores are computed. The normative reference population is well-characterized (40,435 participants from 77 sites, 95% healthy individuals). Deviation thresholds (|z| > 2.0) and interpretation methods are clearly specified.
- **Supporting Text**: We estimated a normative model for each region of interest (ROI, n=150) in the Freesurfer Destrieux atlas, using HBR with age as a covariate, and sex and scanner id as batch effects, to predict CT. This accommodated multisite pooling using transfer learning and comparisons across scanners. The deviations from these models were then used as features in the linear mixed models... Using pooled data from a collection of mostly publicly available datasets from 77 sites, and 40,435 participants, the reference normative models were first trained on (95%) healthy individuals and validated on an independent set of 2548 CTRL and patients (5%, stratified by sites)... We defined the threshold for extreme deviation values as |z| > 2.0.
- **Location**: Berthet2025.pdf.md:46-47

### 5. Handling of Confounding Variables

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Key confounding variables are appropriately handled. Age and sex are included as covariates in the normative models and statistical analyses. Scanner/site effects are handled through Hierarchical Bayesian Regression with scanner id as batch effects and transfer learning. The HBR approach accommodates inter-site variation. Linear mixed effects models control for age at baseline, sex, and time since inclusion.
- **Supporting Text**: We estimated a normative model for each region of interest (ROI, n=150) in the Freesurfer Destrieux atlas, using HBR with age as a covariate, and sex and scanner id as batch effects, to predict CT. This accommodated multisite pooling using transfer learning and comparisons across scanners... To test for the potential of nonrandom attrition confounding our findings, we applied t-tests to check for differences between the patients followed for 10 years and the ones that dropped out... The model included time since inclusion, age at inclusion, and sex on the deviation scores... In all instances where multiple comparison correction was required, we applied the Benjamini-Hochberg procedure with α=0.05 to control the false discovery rate.
- **Location**: Berthet2025.pdf.md:46-47, 51, 53

### 6. Clarity of Data Sources

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Data sources are clearly described. The study sample comes from the Thematically Organized Psychosis (TOP) study at University of Oslo and Oslo University Hospital, with recruitment dates specified (October 27, 2004 to October 17, 2012). The normative reference models were built using pooled data from mostly publicly available datasets from 77 sites with 40,435 participants. Geographic catchment area is specified.
- **Supporting Text**: All participants were recruited to a specific first-episode sub-study of the TOP study at the University of Oslo and Oslo University Hospital from October 27, 2004, to October 17, 2012. Here, patients with a first-episode schizophrenia spectrum diagnosis (SCZ) were consecutively recruited from the catchment-area-based inpatient and outpatient services at Oslo University Hospital and 3 additional hospitals in the larger Oslo area... Using pooled data from a collection of mostly publicly available datasets from 77 sites, and 40,435 participants, the reference normative models were first trained on (95%) healthy individuals.
- **Location**: Berthet2025.pdf.md:31, 46

### 7. Description of Image Acquisition Protocol

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: MRI acquisition protocols are comprehensively described. Three scanners used sequentially without temporal overlap are detailed: (1) 1.5T Siemens MAGNETOM Sonata with 32-channel head coil, MPRAGE sequence (TR=2.730ms, TE=3.93ms, FA=7°); (2) 3T GE Signa HDxT with 8HRBRAIN coil, FSPGR sequence (TR=7.8ms, TE=3.18ms, FA=12°); (3) 3T GE 750 Discovery with 32-channel head coil, BRAVO sequence (TR=8.16ms, TE=3.18ms, FA=12°). Distribution of scanners across timepoints is provided in supplementary materials.
- **Supporting Text**: Three scanners at Oslo University Hospital were used in this longitudinal study without temporal overlap. The first scanner was a 1.5 Tesla Siemens MAGNETOM Sonata scanner with a 32-channel head coil. T1-weighted images were acquired using an MPRAGE sequence using these parameters: repetition time (TR)=2.730 ms, echo time (TE)=3.93 ms, flip angle (FA)=7°C. The second scanner was a 3 Tesla GE Signa HDxT with an 8HRBRAIN coil. T1-weighted images were acquired using an FSPGR sequence, with the following parameters: TR=7.8 ms, TE=3.18 ms, and FA=12°C. The third scanner was a 3 Tesla GE 750 Discovery scanner with a 32-channel head coil. The T1-weighted images were here acquired using a BRAVO sequence, with the following parameters: TR=8.16 ms, TE=3.18 ms, FA=12°C.
- **Location**: Berthet2025.pdf.md:39-41

---

## Assessment Items - Group B

### 1. Details of Data Preprocessing

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: Preprocessing software and key steps are identified: FreeSurfer version 5.3 was used for processing T1-weighted scans, with parcellation using the Destrieux atlas. Quality control using Euler characteristic with threshold >5 is described. However, individual preprocessing steps (e.g., skull stripping, normalization, segmentation procedures) within FreeSurfer are not explicitly detailed beyond general mentions.
- **Supporting Text**: T1-weighted structural MRI scans were preprocessed through Freesurfer (version 5.3), and CT measures were parcellated using the Destrieux atlas. An automatic quality check procedure based on the Freesurfer Euler characteristic was run on all data and samples with a value higher than 5 were removed.
- **Location**: Berthet2025.pdf.md:43

### 2. Clarity of Data Partitioning Methods

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Data partitioning is clearly described. The normative models were trained on 95% of healthy individuals from the reference dataset and validated on an independent 5% set (2,548 CTRL and patients, stratified by sites). For adaptation to Oslo scanners, held-out cross-sectional data from CTRL from these 3 scanners were used. The longitudinal CTRL and SCZ samples were then used as the test set to obtain deviation scores. Measures to prevent data leakage are evident through this separation.
- **Supporting Text**: Using pooled data from a collection of mostly publicly available datasets from 77 sites, and 40,435 participants, the reference normative models were first trained on (95%) healthy individuals and validated on an independent set of 2548 CTRL and patients (5%, stratified by sites). We then adapted the model to the 3 unseen Oslo scanners, by transferring the (hyper) parameters as informed priors for these new sites. For this adaptation step, we used held-out cross-sectional data from CTRL from these 3 scanners, following methods described previously (supplementary table 1). After this transfer step, we tested the normative models on the remaining longitudinal CTRL and SCZ samples and obtained individual deviation scores.
- **Location**: Berthet2025.pdf.md:46-47

### 3. Details of Normative Modeling Approach

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The normative modeling approach is described in detail. Hierarchical Bayesian Regression (HBR) was used for each of 150 ROIs from the Destrieux atlas. Age was included as a covariate, with sex and scanner ID as batch effects. The approach accommodates inter-site variation and provides computational scaling useful for multi-cohort longitudinal studies. Transfer learning was employed to adapt models to new scanners. Relevant methodological papers are cited for implementation details.
- **Supporting Text**: To account for site and scanner effects, we used the Hierarchical Bayesian regression (HBR) approach for normative modeling, which efficiently accommodates inter-site variation and provides computational scaling, which is useful for multi-cohort and longitudinal studies with data from different scanners. We estimated a normative model for each region of interest (ROI, n=150) in the Freesurfer Destrieux atlas, using HBR with age as a covariate, and sex and scanner id as batch effects, to predict CT. This accommodated multisite pooling using transfer learning and comparisons across scanners.
- **Location**: Berthet2025.pdf.md:45-46

### 4. Details of Training Algorithm

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The general framework (HBR with transfer learning) is specified, and the paper cites methodological references for detailed procedures. However, specific optimization algorithms (e.g., gradient descent variants), convergence criteria, hyperparameter setting methods, and regularization techniques are not explicitly described in the main text or supplementary materials. The paper relies on cited methods for these implementation details.
- **Supporting Text**: We used the Hierarchical Bayesian regression (HBR) approach for normative modeling... We then adapted the model to the 3 unseen Oslo scanners, by transferring the (hyper) parameters as informed priors for these new sites. For this adaptation step, we used held-out cross-sectional data from CTRL from these 3 scanners, following methods described previously.
- **Location**: Berthet2025.pdf.md:45-47

### 5. Model Performance Evaluation Metrics

- **Answer**: No
- **Confidence Rating**: Medium
- **Negative Answer Category**: Missing
- **Reason**: While the paper describes validation on an independent 5% set of the reference data, specific quantitative performance metrics (e.g., MSLL, Explained Variance, Pearson correlation, R²) for the normative models are not reported. The focus is on the statistical analysis of deviation scores rather than on reporting normative model performance metrics themselves.
- **Supporting Text**: Using pooled data from a collection of mostly publicly available datasets from 77 sites, and 40,435 participants, the reference normative models were first trained on (95%) healthy individuals and validated on an independent set of 2548 CTRL and patients (5%, stratified by sites).
- **Location**: Berthet2025.pdf.md:46

### 6. Implementation of Internal Validation

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Internal validation was implemented appropriately. The normative models were validated on an independent 5% of the reference dataset (2,548 participants) stratified by sites, separate from the 95% training set. Additionally, for the Oslo scanners, held-out cross-sectional CTRL data were used for the adaptation/transfer step, keeping the longitudinal samples as an independent test set. This demonstrates proper separation of training and validation data.
- **Supporting Text**: Using pooled data from a collection of mostly publicly available datasets from 77 sites, and 40,435 participants, the reference normative models were first trained on (95%) healthy individuals and validated on an independent set of 2548 CTRL and patients (5%, stratified by sites). We then adapted the model to the 3 unseen Oslo scanners, by transferring the (hyper) parameters as informed priors for these new sites. For this adaptation step, we used held-out cross-sectional data from CTRL from these 3 scanners... After this transfer step, we tested the normative models on the remaining longitudinal CTRL and SCZ samples.
- **Location**: Berthet2025.pdf.md:46-47

### 7. External Data Validation

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: External validation was conducted. The normative models were trained on publicly available datasets from 77 sites (40,435 participants), then adapted and tested on completely independent TOP study data from Oslo scanners that were not part of the original training datasets. This represents true external validation on an independent cohort. The characteristics of this external validation dataset (TOP study) are well described.
- **Supporting Text**: Using pooled data from a collection of mostly publicly available datasets from 77 sites, and 40,435 participants, the reference normative models were first trained on (95%) healthy individuals... We then adapted the model to the 3 unseen Oslo scanners... After this transfer step, we tested the normative models on the remaining longitudinal CTRL and SCZ samples and obtained individual deviation scores for these participants at each timepoint and ROI.
- **Location**: Berthet2025.pdf.md:46-47

### 8. Description of Dataset Characteristics

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Dataset characteristics are comprehensively described. Sample sizes at each timepoint are provided (baseline: 218 CTRL, 79 SCZ; 12 months: 197 CTRL, 67 SCZ; 10 years: 77 CTRL, 23 SCZ). Demographic characteristics including age means, sex ratios, and PANSS scores are reported in Table 1. Medication status at each timepoint is detailed. Attrition analysis comparing retained vs. dropout patients is included. Supplementary tables provide additional details on adaptation samples.
- **Supporting Text**: A total of 218 healthy CTRL and 79 patients were included in the longitudinal analysis (table 1 and supplementary figures 1 and 8). There was no significant difference either in the number of contacts with the healthcare system for ICD-10 classified 'Mental, Behavioral and Neurodevelopmental disorders,' nor in duration of contacts with the healthcare system between the longitudinal sample and the drop-outs. There was no significant association between attrition groups and PANSS scores, or median cortical deviation scores. Data on medication use for each timepoint showed that patients primarily used second-generation antipsychotics (62% at baseline, 48% at 1 year, and 17% at 10 years) or did not use any antipsychotics (38%, 50%, and 83%, respectively).
- **Location**: Berthet2025.pdf.md:79; Table 1 at line 85-95

### 9. Performance Metrics and Statistical Uncertainty

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Statistical performance metrics and uncertainty are clearly reported. Cohen's d effect sizes are provided for group comparisons at different timepoints (baseline d=-0.46, 12-month d=-0.43, 10-year d=-0.27). P-values with FDR correction are reported throughout. Specific effect sizes for individual ROIs are provided in supplementary tables. Linear mixed effects models include coefficient estimates. Common language effect sizes are reported for extreme deviation analyses (66% at baseline, 63% at 12 months, 50% at 10 years).
- **Supporting Text**: LMEs revealed significant (P<.05, FDR corrected) conditional main effects of diagnosis and time × diagnosis interaction effects in a diffuse network... Briefly, the Cohen's d for median thickness deviation score at baseline is -0.46, -0.43 at 12-month follow-up and -0.27 at 10-year follow-up... The analysis revealed significant case-control differences in the number of extreme negative deviations (Z<-2) at baseline (P=2.56×10-5, common language (CL) effect size=66%) and at the second assessment (P=.0006, CL effect size=63%).
- **Location**: Berthet2025.pdf.md:100-101, 113

### 10. Consideration for Reproducibility

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: Software versions are specified (FreeSurfer version 5.3). The study cites published methodological papers for HBR implementation details. The reference normative models were built using mostly publicly available datasets. However, there is no explicit statement about code/script availability, sharing of trained models, or data availability beyond general mentions of public datasets used for training the reference models. The TOP study data availability is not explicitly discussed.
- **Supporting Text**: T1-weighted structural MRI scans were preprocessed through Freesurfer (version 5.3), and CT measures were parcellated using the Destrieux atlas... To account for site and scanner effects, we used the Hierarchical Bayesian regression (HBR) approach for normative modeling... Using pooled data from a collection of mostly publicly available datasets from 77 sites, and 40,435 participants, the reference normative models were first trained on (95%) healthy individuals.
- **Location**: Berthet2025.pdf.md:43, 45-46

### 11. Interpretation Specific to Normative Modeling

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The interpretation is strongly focused on normative modeling. Individual-level deviation scores are the primary outcome measure. The paper discusses how deviations from normative trajectories attenuate over time and their clinical significance. Advantages over traditional approaches are highlighted (e.g., ability to compare across scanners, individual-level inference, accommodating heterogeneity). Clinical implications regarding the attenuation of deviations challenging neurodegenerative models are discussed. The threshold for extreme deviations (|z|>2.0) is defined and its clinical relevance is considered.
- **Supporting Text**: More recently still, the availability of large neuroimaging datasets has led to the advent of normative development charts that allow for individual-level statistical inference and for mapping clinical traits to extreme deviations from the normative range. Such techniques may be particularly valuable in longitudinal studies because they provide the ability to detect deviations from an expected trajectory over time, which might provide early indicators of worsening or improvement in the disease course and can accommodate heterogeneity in the pattern of atypicalities across individuals and timepoints... These findings are in line with previous reports that the gray matter differences were most severe in the early years after schizophrenia onset, and are discordant with a general notion of schizophrenia as a neurodegenerative disorder with progressive brain aberrations over time.
- **Location**: Berthet2025.pdf.md:25, 145

---

## Additional Comments

**Additional Comments**: This is a well-conducted 10-year longitudinal study employing transfer learning from large-scale normative models to enable meaningful comparisons across different scanners. The study demonstrates appropriate handling of multi-scanner longitudinal data through HBR. Attrition is addressed through registry data validation. The medication data showing predominant second-generation antipsychotic use distinguishes this from earlier long-term studies. While specific normative model performance metrics are not reported, the comprehensive statistical analysis of deviation scores and their clinical correlates is thorough. The study provides important evidence against simple neurodegenerative accounts of schizophrenia by showing attenuation of cortical deviations over time.
