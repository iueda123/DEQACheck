# Quality Assessment Form

## Study Identification

- **Study ID**: Bedford2025
- **Reference File Names**: Bedford2025.pdf, 1-s2.0-S0006322324015130-mmc2.pdf, 1-s2.0-S0006322324015130-mmc1.xlsx.md
- **Author, Journal, Year**: Bedford et al., Biological Psychiatry, 2025
- **Title**: Brain-Charting Autism and Attention-Deficit/Hyperactivity Disorder Reveals Distinct and Overlapping Neurobiology
- **DOI**: 10.1016/j.biopsych.2024.07.024

---

## Assessment Items - Group A

### 1. Clarity of Research Objectives

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The research objectives are clearly stated: to use normative modeling (BrainCharts) on a large multisite dataset to characterize cortical anatomy in autism and ADHD, and to investigate the effects of sex, age, and co-occurrence.
- **Supporting Text**: "Here, we used population modeling and a large, multisite neuroimaging dataset (N = 4255 after quality control) to characterize cortical anatomy associated with autism and ADHD, benchmarked against models of average brain development..."
- **Location**: Bedford2025.pdf, Page 1 (Abstract)

### 2. Clear Definition of Target Population

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The target clinical populations (Autism, ADHD, and co-occurring) and the control group are clearly defined with sample sizes.
- **Supporting Text**: "...1869 typically developing control participants [687 female, 1182 male], 987 individuals with ADHD [270 female, 717 male], and 1399 autistic individuals [288 female, 1111 male]..."
- **Location**: Bedford2025.pdf, Page 2 (Methods and Materials)

### 3. Clarity of Inclusion and Exclusion Criteria

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: General inclusion based on primary diagnosis is mentioned, and a specific quality control cutoff (FSQC > 2.5) is defined. However, detailed inclusion/exclusion criteria for the 7 constituent datasets are not provided in the main text.
- **Supporting Text**: "Individuals with magnetic resonance imaging data and a primary diagnosis of autism or ADHD or no diagnosis were included."; "A cutoff of 2.5 was used for FSQC (107)."
- **Location**: Bedford2025.pdf, Page 2 (Methods and Materials)

### 4. Validity of Normative Modeling Outcome Measures

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The outcome measure is explicitly defined as "centile scores" derived from BrainChart reference models, with a clear interpretation of their range.
- **Supporting Text**: "Centile scores quantify variation in brain development and range from 0 to 1, with 0.5 representing the average of the reference sample."
- **Location**: Bedford2025.pdf, Page 3 (Methods and Materials)

### 5. Handling of Confounding Variables

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Key confounders (age, sex, site) are handled via the reference model (GAMLSS) and ComBat harmonization. Image quality (Euler number) is included as a covariate.
- **Supporting Text**: "...while accounting for effects of age, sex, and site/scanner."; "ComBat was applied... with covariates of age, sex, and diagnosis..."; "All analyses included Euler number as a covariate, as well as age..."
- **Location**: Bedford2025.pdf, Page 2-3 (Methods and Materials)

### 6. Clarity of Data Sources

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The specific open datasets and consortia used are clearly listed.
- **Supporting Text**: "...including the ABIDE..., the POND... Network, the HBN..., the ADHD200 Consortium, the Multimodal Developmental Neurogenetics of Females with ASD dataset..., the UK MRC-AIMS..., and the University of California San Diego Biomarkers of Autism study."
- **Location**: Bedford2025.pdf, Page 2 (Sample and Datasets)

### 7. Description of Image Acquisition Protocol

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The supplement provides general scanner types for some datasets and points to external websites for details.
- **Supporting Text**: "For more information about individual sites and imaging protocols, please see: http://fcon_1000.projects.nitrc.org/indi/abide/."
- **Location**: 1-s2.0-S0006322324015130-mmc2.pdf, Page 4 (Supplementary Methods)

---

## Assessment Items - Group B

### 1. Details of Data Preprocessing

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Standard preprocessing using FreeSurfer and a specific atlas is documented.
- **Supporting Text**: "T1 images from each dataset were processed using FreeSurfer, version 6.0.1 (105). Regional estimates of each cortical measure were extracted based on the Desikan-Killiany atlas (106)."
- **Location**: Bedford2025.pdf, Page 2 (Data Processing)

### 2. Clarity of Data Partitioning Methods

- **Answer**: NA
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The study uses pre-existing reference models (BrainCharts) for out-of-sample estimation on the new clinical dataset, so standard training/test partitioning is not applicable to this specific study's primary analysis.
- **Supporting Text**: "Out-of-sample centile scores for our study sample were generated based on these reference models..."
- **Location**: Bedford2025.pdf, Page 3 (Methods and Materials)

### 3. Details of Normative Modeling Approach

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The modeling approach (GAMLSS for BrainCharts) is specified, with details referenced to prior work and summarized in the supplement.
- **Supporting Text**: "Our previous work (98) generated reference models using generalized additive models of location scale and shape..."; Supplementary text details the GAMLSS model specification.
- **Location**: Bedford2025.pdf, Page 2; 1-s2.0-S0006322324015130-mmc2.pdf, Page 17

### 4. Details of Training Algorithm

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The procedure for applying the pre-trained model to new data (out-of-sample estimation via Maximum Likelihood Estimation) is detailed in the supplement.
- **Supporting Text**: "Using the likelihood as defined by the main model fit, maximum likelihood estimates (MLEs) are obtained from the random-effects conditional on the fitted fixed-effects."
- **Location**: 1-s2.0-S0006322324015130-mmc2.pdf, Page 17 (Supplementary methods 2)

### 5. Model Performance Evaluation Metrics

- **Answer**: NA
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The study focuses on applying an established model to find group differences, not on benchmarking the model's predictive performance itself.
- **Supporting Text**: N/A
- **Location**: N/A

### 6. Implementation of Internal Validation

- **Answer**: NA
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Standard internal validation (like cross-validation for model selection) is not applicable as no new model was trained. Sensitivity analyses (e.g., comparing in-sample vs out-of-sample centiles for overlapping sites) were conducted.
- **Supporting Text**: "We also conducted sensitivity analyses... and compared in-sample and out-of-sample centiles..."
- **Location**: Bedford2025.pdf, Page 3 (Methods and Materials)

### 7. External Data Validation

- **Answer**: NA
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The entire study serves as an application of the BrainCharts model to a large external clinical dataset.
- **Supporting Text**: N/A
- **Location**: N/A

### 8. Description of Dataset Characteristics

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Detailed demographics (N, age, sex) for all diagnostic groups and individual datasets are provided in the text, figures, and supplementary tables.
- **Supporting Text**: Figure 1 displays "Age distribution by study and diagnostic group". Supplementary Tables S1.2.1 to S1.2.5 provide detailed numbers.
- **Location**: Bedford2025.pdf, Figure 1; 1-s2.0-S0006322324015130-mmc2.pdf, Page 6-16

### 9. Performance Metrics and Statistical Uncertainty

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Statistical outputs including Cohen's d effect sizes and FDR-corrected p-values are reported.
- **Supporting Text**: "Multiple comparisons were controlled for using the false discovery rate correction... Cohen’s d effect sizes were calculated..."
- **Location**: Bedford2025.pdf, Page 3 (Statistical Analysis)

### 10. Consideration for Reproducibility

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The Key Resources Table provides the algorithm URL, R version, and specific packages used.
- **Supporting Text**: Key Resources Table lists "Brain Charts algorithm" URL and "R version 4.3.1".
- **Location**: 1-s2.0-S0006322324015130-mmc1.xlsx.md

### 11. Interpretation Specific to Normative Modeling

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The study utilizes centile scores to analyze individual-level overlaps of extreme deviations, directly leveraging the normative modeling framework to discuss heterogeneity.
- **Supporting Text**: "We also examined the amount of regional overlap in participants in each group with the greatest divergences from the average centile score..."; "Autistic individuals showed the highest degree of both negative and positive extreme centiles..."
- **Location**: Bedford2025.pdf, Page 3, 5
