# Quality Assessment Form

## Study Identification

- **Study ID**: Cirstian2024
- **Reference File Names**: Cirstian2024.pdf.md
- **Author, Journal, Year**: Cirstian et al., Biological Psychiatry, 2025
- **Title**: Lifespan Normative Models of White Matter Fractional Anisotropy: Applications to Early Psychosis
- **DOI**: 10.1016/j.biopsych.2025.07.021

---

## Assessment Items - Group A

### 1. Clarity of Research Objectives

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper clearly states the four main aims of the study at the end of the introduction.
- **Supporting Text**: The aims of this study are to: (i) develop normative models of Fractional Anisotropy (FA), the most widely used diffusion metric in neuroimaging [17], across major white matter tracts using a large dataset of over 25,000 healthy individuals across a broad age range. By using high-quality diffusion MRI data from the UK Biobank and the Human Connectome Project, we seek to establish robust models that capture lifespan trajectories of white matter organization; (ii) investigate white matter FA in early psychosis, a prototypical psychiatric disorder that is known to be highly heterogeneous in disease severity and course, as well as clinical symptom expression and clinical outcomes. Using the HCP Early Psychosis (HCP-EP) dataset [18], we aim to map both group level differences and individual deviations from the normative model in order to better understand individual variability in white matter integrity; (iii) we aim to illustrate the value of normative models for multi-modal data fusion, by combining FA deviations with cortical thickness and subcortical brain volume deviations with the goal to identify multimodal biological signatures and specific white matter pathways in psychosis associated with different psychosis symptom domains. Finally, (iv) we release all models freely to the community via our existing open-source software platforms [19].
- **Location**: Cirstian2024.pdf.md, lines 123-138

### 2. Clear Definition of Target Population

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper clearly states that the clinical dataset is from the HCP Early Psychosis (HCP-EP) and that participants were diagnosed using the SCID-5.
- **Supporting Text**: Next, we applied the model to the Human Connectome Project Early Psychosis (HCP-EP) dataset [18] (Jacobs et al., 2024), which includes multi-shell diffusion data and T1-weighted structural MRI derived from participants diagnosed with early psychosis (n=118) and control participants (n=55). ... Participants with early psychosis were diagnosed using the Structured Clinical Interview for DSM-5 (SCID-5) (First et al., 2015) and symptoms assessed with the Positive and Negative Syndrome Scale (PANSS) [27], including negative symptoms (e.g., social withdrawal), positive symptoms (e.g., hallucinations), disorganisation, and general psychopathology.
- **Location**: Cirstian2024.pdf.md, lines 383-392

### 3. Clarity of Inclusion and Exclusion Criteria

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The paper mentions the diagnostic criteria for the clinical group (SCID-5) and an outlier exclusion criterion for the normative model. However, it does not provide a comprehensive list of inclusion and exclusion criteria for either the normative or the clinical sample.
- **Supporting Text**: In line with our prior work [46] we refit the models after excluding gross outliers having deviations larger than 5 standard deviations from the mean (Figure 1C). and Participants with early psychosis were diagnosed using the Structured Clinical Interview for DSM-5 (SCID-5) (First et al., 2015)
- **Location**: Cirstian2024.pdf.md, lines 378-380 and 389-390

### 4. Validity of Normative Modeling Outcome Measures

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper clearly defines the outcome measure as Fractional Anisotropy (FA), a well-established diffusion metric. It also clearly defines the deviation scores (z-scores) and the metrics used to evaluate the models (EV, skewness, kurtosis).
- **Supporting Text**: The aims of this study are to: (i) develop normative models of Fractional Anisotropy (FA), the most widely used diffusion metric in neuroimaging [17], across major white matter tracts... and We assessed the quality of the normative modeling fit using three key out-of-sample metrics, namely explained variance (EV), evaluating the fit of the median regression line, in addition to skewness and kurtosis, which evaluate the shape of the distribution used to model the centiles. and Next, we used these models to understand heterogeneity in white matter FA in psychosis. To achieve this, we applied these reference models to the HCP early psychosis (HCP-EP) dataset (N=173 with diffusion data -see supplementary table 2 for demographic information) in order to derive z-scores for each individual and tract.
- **Location**: Cirstian2024.pdf.md, lines 123-125, 155-159, 185-188

### 5. Handling of Confounding Variables

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper explicitly states that the normative model adjusted for age, sex, site, and race.
- **Supporting Text**: The model incorporated several covariates, including sex, age, and dummy coded race, and site.
- **Location**: Cirstian2024.pdf.md, lines 374-375

### 6. Clarity of Data Sources

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper clearly lists the datasets used for both the normative model and the clinical application, and provides references for each.
- **Supporting Text**: The construction of the lifespan dataset involved integrating data from five cohorts having high-quality multi-shell diffusion data, i.e.: the HCP Baby [20], HCP Development [21], HCP Young Adult [22], HCP Aging [23] datasets, and the UK Biobank [24]. and Next, we applied the model to the Human Connectome Project Early Psychosis (HCP-EP) dataset [18] (Jacobs et al., 2024)...
- **Location**: Cirstian2024.pdf.md, lines 355-358 and 383-384

### 7. Description of Image Acquisition Protocol

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The paper mentions that the data is multi-shell diffusion data and that harmonized pipelines were used. However, it does not provide the specific image acquisition parameters in the main text, instead referring to external documentation and other publications.
- **Supporting Text**: The construction of the lifespan dataset involved integrating data from five cohorts having high-quality multi-shell diffusion data... and These corrections were executed using the HCP-pipeline [42] for the HCP datasets while the UKB dataset was already processed according to the UKB documentation [43].
- **Location**: Cirstian2024.pdf.md, lines 355-356 and 363-365

---

## Assessment Items - Group B

### 1. Details of Data Preprocessing

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper describes the preprocessing pipeline in sufficient detail, mentioning the software used (FSL), the main steps (distortion and movement corrections, DTI modeling, TBSS), and the atlas used for segmentation (JHU).
- **Supporting Text**: The processing of these datasets followed harmonized FSL-based pipelines, summarized in Figure 1A. Initially, pre-processing was performed: B0 intensity normalization, correction for EPI distortions, eddy-current-induced and movement corrections. These corrections were executed using the HCP-pipeline [42] for the HCP datasets while the UKB dataset was already processed according to the UKB documentation [43]. Subsequently, we estimated the DTI model using DTIfit on the lowest shell value in order to extract the fractional anisotropy (FA) values. Following this, we ran Tract-Based Spatial Statistics (TBSS) [44] on the FA images which included registration to a standard space (FMRIB58_FA), projection of each individual’s FA image to the standard space skeletonized image (threshold at 0.2) to generate skeletonized FA images for each individual in the same space. Finally, segmentation was conducted using the Johns Hopkins University (JHU) atlas [45].
- **Location**: Cirstian2024.pdf.md, lines 359-371

### 2. Clarity of Data Partitioning Methods

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper clearly describes the data partitioning into training and test sets, including the size of each set and the stratification method.
- **Supporting Text**: To prepare for the modelling stage, we began by splitting the dataset of subjects (N=24,915) into two equal groups: a test set (N=12,457) and a training set (N=12,457), stratified to ensure an even distribution of sex, race, dataset and site.
- **Location**: Cirstian2024.pdf.md, lines 372-374

### 3. Details of Normative Modeling Approach

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper clearly describes the statistical approach used for normative modeling, including the model type (warped Bayesian linear regression), the basis expansion, the warping function, and the software used.
- **Supporting Text**: we employed a warped Bayesian linear regression (BLR) model and used in previous research [4], [25]. This approach involved applying a third-order polynomial B-spline basis expansion over age, with five evenly spaced knots, combined with a SinhArcsinh warping function. and All statistical analyses were conducted using Python version 3.8, with the Predictive Clinical Neuroscience PCN toolkit (GitHub, PCNtoolkit).
- **Location**: Cirstian2024.pdf.md, lines 375-378 and 381-382

### 4. Details of Training Algorithm

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The paper mentions that the models were refit after excluding outliers, but it does not provide details about the optimization algorithms, hyperparameter settings, or convergence criteria used for training the Bayesian linear regression models.
- **Supporting Text**: In line with our prior work [46] we refit the models after excluding gross outliers having deviations larger than 5 standard deviations from the mean (Figure 1C). Once the models were refit with the cleaned data, we calculated the fit statistics, including explained variance, skew, and kurtosis.
- **Location**: Cirstian2024.pdf.md, lines 378-381

### 5. Model Performance Evaluation Metrics

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

### 6. Implementation of Internal Validation

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

### 7. External Data Validation

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

### 8. Description of Dataset Characteristics

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

### 9. Performance Metrics and Statistical Uncertainty

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

### 10. Consideration for Reproducibility

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

### 11. Interpretation Specific to Normative Modeling

- **Answer**: 
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

---

## Additional Comments

**Additional Comments**: 
