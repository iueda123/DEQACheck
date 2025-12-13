# Quality Assessment Form

## Study Identification

- **Study ID**:
- **Reference File Names**:
- **Author, Journal, Year**:
- **Title**:
- **DOI**:

---

## Assessment Items - Group A

### 1. Clarity of Research Objectives

- **Answer**:Partial 
- **Confidence Rating**:Medium
- **Negative Answer Category**:Missing
- **Reason**:PICO are generally stated but specific application purposes or hypotheses are not stated. 
- **Supporting Text**The construction of the lifespan dataset involved integrating data from five cohorts having high-quality multi-shell diffusion data, i.e.: the HCP Baby [20], HCP Development [21], HCP Young Adult [22], HCP Aging [23] datasets, and the UK Biobank [24]. The demographic information is available in supplementary table 1.
- **Location**:p.10 (l.338-341), Table S1

### 2. Clear Definition of Target Population

- **Answer**:Yes
- **Confidence Rating**:Medium
- **Negative Answer Category**:Not Negative
- **Reason**:Diagnostic tools (DSM-5 or SCID-5) used and demographic data are described.
- **Supporting Text**: Participants with early psychosis were diagnosed using the Structured Clinical Interview for DSM-5 (SCID-5) (First et al., 2015) and symptoms assessed with the Positive and Negative Syndrome Scale (PANSS).
- **Location**:p.11 (l.380-382), Table S2

### 3. Clarity of Inclusion and Exclusion Criteria

- **Answer**:Partial
- **Confidence Rating**:High
- **Negative Answer Category**:Missing
- **Reason**:While some information is provided (e.g., number of subjects included in the final analysis), other information such as clear inclusion and exclusion criteria are missing.- **Supporting Text**:Next, we applied the model to the Human Connectome Project Early Psychosis (HCP-EP) dataset [18] (Jacobs et al., 2024), which includes multi-shell diffusion data and T1-weighted structural MRI derived from participants diagnosed with early psychosis (n=118) and control participants (n=55). 
- **Location**:p.11 (l.375-378)

### 4. Validity of Normative Modeling Outcome Measures

- **Answer**:Yes
- **Confidence Rating**:Medium
- **Negative Answer Category**:Not Negative
- **Reason**:All criteria are stated in the text or in the supplementary information.
- **Supporting Text**:A normative model was then fit to the training set for each white matter tract.

We then computed z-scores for the patients and remaining controls for the FA data and computed the deviations for cortical thickness and subcortical volumes derived from models we have previously brought online.

To achieve this, we set a z-score threshold between -2.6 and 2.6, which correspond to a p-=value of 0.01 as in prior work to identify extreme deviations then employed a non-parametric Mann-Whitney U test.
- **Location**:p.11 (l.359-360), p.11-12 (l.396-399), p.12 (line 406-408), Table S2

### 5. Handling of Confounding Variables

- **Answer**:Partial
- **Confidence Rating**:High
- **Negative Answer Category**:Missing
- **Reason**:The detail of harmonization is not stated.
- **Supporting Text**: The model incorporated several covariates, including sex, age, and dummy coded race, and site.

The processing of these datasets followed harmonized FSL-based pipelines, summarized in Figure 1A. 
- **Location**:p.11 (l.359-360), p.10 (l.342-343)

### 6. Clarity of Data Sources

- **Answer**:Partial
- **Confidence Rating**:High
- **Negative Answer Category**:Missing
- **Reason**:While the resource of the data are described, the time and location of the data acquisition are not described. 
- **Supporting Text**:First, we assembled high-quality multi-shell diffusion data from five cohorts having closely matched acquisition and processing pipelines, namely Human Connectome Project (HCP) Baby [20], HCP Development [21], HCP Young Adult [22], HCP Aging [23] datasets, and UK Biobank [24]. Total N=24,915, (N=12,457 for training and N=12,457 for test, stratified for sex, self-reported race, dataset and site).

Next, we applied the model to the Human Connectome Project Early Psychosis (HCP-EP) dataset [18] (Jacobs et al., 2024), which includes multi-shell diffusion data and T1-weighted structural MRI derived from participants diagnosed with early psychosis (n=118) and control participants (n=55). 
- **Location**:p.4 (l.125-129), p.11 (l.375-378)

### 7. Description of Image Acquisition Protocol

- **Answer**:Partial
- **Confidence Rating**:High
- **Negative Answer Category**:Missing
- **Reason**:While details of MRI sequences are described, imaging parameters and scanner specifications are not described.
- **Supporting Text**:In short, the datasets were processed using harmonised FSL-based pipelines, involving pre-processing (intensity normalisation, distortion and movement corrections), DTI modellingto extract fractional anisotropy (FA) values, Tract-Based Spatial Statistics (TBSS) for skeletonised FA images, and segmentation with the Johns Hopkins University (JHU) atlas to compute mean FA values across 48 white matter tracts.

Next, we applied the model to the Human Connectome Project Early Psychosis (HCP-EP) dataset [18] (Jacobs et al., 2024), which includes multi-shell diffusion data and T1-weighted structural MRI derived from participants diagnosed with early psychosis (n=118) and control participants (n=55). 
- **Location**:p.4 (l.130-135), p.11 (375-378)

---

## Assessment Items - Group B

### 1. Details of Data Preprocessing

- **Answer**:Partial
- **Confidence Rating**:High
- **Negative Answer Category**:Missing
- **Reason**:While preprocessing softwares are described, details about each preprocessing step (for structural data) and about quality control are not described.
- **Supporting Text**:The processing of these datasets followed harmonized FSL-based pipelines, summarized in Figure 1A. Initially, pre-processing was performed: B0 intensity normalization, correction for EPI distortions, eddy-current-induced and movement corrections. These corrections were executed using the HCP-pipeline [42] for the HCP datasets while the UKB dataset was already processed according to the UKB documentation [43].

The diffusion data were processed with the same pipeline as described above (Figure 1A),and structural data were processed using Freesurfer version 6.0 following similar procedures as we have described previously [4].
- **Location**:p.10 (l.342-346), p.11 (l.390-392)

### 2. Clarity of Data Partitioning Methods

- **Answer**:Partial
- **Confidence Rating**:High
- **Negative Answer Category**:Missing
- **Reason**:Information regarding measures to prevent leakage is missing.
- **Supporting Text**:To prepare for the modelling stage, we began by splitting the dataset of subjects (N=24,915) into two equal groups: a test set (N=12,457) and a training set (N=12,457), stratified to ensure an even distribution of sex, race, dataset and site. 
- **Location**:p.11 (l.357-359)

### 3. Details of Normative Modeling Approach

- **Answer**:Partial
- **Confidence Rating**:High
- **Negative Answer Category**:Unclear/Incomplete
- **Reason**:Details about model hyperparameters and settings are missing.
- **Supporting Text**:A normative model was then fit to the training set for each white matter tract. The model incorporated several covariates, including sex, age, and dummy coded race, and site. To address potential non-linear effects and non-Gaussian distributions, we employed a warped Bayesian linear regression (BLR) model and used in previous research [4], [25]. This approach involved applying a third-order polynomial B-spline basis expansion over age, with five evenly spaced knots, combined with a SinhArcsinh warping function...All statistical analyses were conducted using Python version 3.8, with the Predictive Clinical Neuroscience PCN toolkit (GitHub, PCNtoolkit).
- **Location**:p.11 (l.359-373)

### 4. Details of Training Algorithm

- **Answer**:No
- **Confidence Rating**:High
- **Negative Answer Category**:Missing
- **Reason**:Information is not provided.
- **Supporting Text**:NA
- **Location**:NA

### 5. Model Performance Evaluation Metrics

- **Answer**:No
- **Confidence Rating**:High
- **Negative Answer Category**:Missing
- **Reason**:Information is not provided.
- **Supporting Text**:NA
- **Location**:NA

### 6. Implementation of Internal Validation

- **Answer**:Partial
- **Confidence Rating**:High
- **Negative Answer Category**:Unclear/Incomplete
- **Reason**:Validated but not sufficient.
- **Supporting Text**:Note that we deliberately choose fixed parameters rather than optimizing them via nested cross-validation given the moderate sample size for the clinical data. Instead, we employed stability selection to assess the generalizability of the coefficients, which is theoretically guaranteed to provide tight type-I family-wise error control [29].In more detail, we performed 1000 random splits of the dataset into a training (70%) and test set (30%) and selected the most stable features, i.e. features that were selected in more than 80% of the splits. This threshold is justified as it is sufficiently high that the theoretical guarantees on controlling the type 1 error rate become operative. In order to assess generalizability, we then ran an additional 1000 permutations, where within each permutation, we computed the test canonical correlation averaged across 10 random splits of the data, both before and after randomly permuting the order of the PANSS data view to destroy the relationship between the symptom scores and imaging data.
- **Location**:p.12 (l.433-446)

### 7. External Data Validation

- **Answer**:No
- **Confidence Rating**:High
- **Negative Answer Category**:Missing
- **Reason**:Not described.
- **Supporting Text**:NA
- **Location**:NA

### 8. Description of Dataset Characteristics

- **Answer**:Partial
- **Confidence Rating**:High
- **Negative Answer Category**:Missing
- **Reason**:Sample sizes for each data set are described, but other information is not stated.
- **Supporting Text**:To prepare for the modelling stage, we began by splitting the dataset of subjects (N=24,915) into two equal groups: a test set (N=12,457) and a training set (N=12,457),stratified to ensure an even distribution of sex, race, dataset and site. 
- **Location**:p.11 (l.357-361)

### 9. Performance Metrics and Statistical Uncertainty

- **Answer**:Partial
- **Confidence Rating**:High
- **Negative Answer Category**:Missing
- **Reason**:Some performance metrics are reported (e.g., explained variance) but others (e.g., confidence intervals) are not reported.
- **Supporting Text**:We assessed the quality of the normative modeling fit using three key out-of-sample metrics, namely explained variance (EV), evaluating the fit of the median regression line, in addition to skewness and kurtosis, which evaluate the shape of the distribution used to model the centiles. These metrics offer insight into how well the models capture the underlying distribution of the data across 48 white matter tracts. The mean (standard deviation) EV was 0.37 (0.10), indicating good fit across different models. Skewness, and kurtosis were respectively -0.09 ( 0.12) and 0.42 (0.27), which together indicate that the shape was also appropriate for the data. Supplementary figure 1 shows a histogram of the EV, skew and kurtosis of the models.
- **Location**:p.4 (l.138-146)

### 10. Consideration for Reproducibility

- **Answer**:Yes
- **Confidence Rating**:High
- **Negative Answer Category**:Not Negative
- **Reason**:All details are described.
- **Supporting Text**:All statistical analyses were conducted using Python version 3.8, with the Predictive Clinical Neuroscience PCN toolkit (GitHub, PCNtoolkit).

Finally, we provide these models to the field via our established no-code software platform [19] and via open-source software tools (https://github.com/ramonacirstian/fa_normative_modeling), so that others in the field can easily apply these models to their own data.

The data used in the present study is part of the UK Biobank dataset which is available to be downloaded upon completing an access application. More information can be found on the dedicated webpage (UK Biobank, n.d.). The code used to process the data and train the normative models is also available online on GitHub (https://github.com/ramonacirstian/fa_normative_modeling n.d.)
- **Location**:p.11 (l.372-373), p.9-10 (299-302), p.16 (l.506-511)

### 11. Interpretation Specific to Normative Modeling

- **Answer**:Partial
- **Confidence Rating**:High
- **Negative Answer Category**:Missing
- **Reason**:While prospects for clinical application are partially discussed, other information is missing.
- **Supporting Text**:Our application of these models to a clinical early psychosis cohort underscores their potential utility in identifying atypical white matter patterns in psychiatric conditions. These models not only serve as a benchmark for individual-level assessments but also offer valuable insights for precision medicine, facilitating more personalized interventions. This study highlights the relevance of normative modeling in neuroimaging, paving the way for its integration into clinical and research settings focused on individual variability in brain structure and pathology.
- **Location**:p.10 (l.328-334)

---

## Additional Comments

**Additional Comments**:
