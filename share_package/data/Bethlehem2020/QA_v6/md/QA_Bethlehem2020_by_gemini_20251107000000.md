# Quality Assessment Form

## Study Identification

- **Study ID**: Bethlehem2020
- **Reference File Names**: Bethlehem2020.pdf.md; Bethlehem2020_sup1.pdf.md
- **Author, Journal, Year**: Bethlehem et al., Communications Biology, 2020
- **Title**: A normative modelling approach reveals age-atypical cortical thickness in a subgroup of males with autism spectrum disorder
- **DOI**: 10.1038/s42003-020-01212-9

---

## Assessment Items - Group A

### 1. Clarity of Research Objectives

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper clearly states the research objectives, including the application of normative modeling to understand heterogeneity in ASD and to compare this approach with traditional case-control methods. The PICO elements are identifiable.
- **Supporting Text**: Understanding heterogeneity is an important goal on the path to precision medicine for autism spectrum disorders (ASD). We examined how cortical thickness (CT) in ASD can be parameterized as an individualized metric of atypicality relative to typically-developing (TD) age-related norms. and In the present study, we employ normative modelling on age-related variability as a means to individualize our approach to isolate speci /uniFB01 c subsets of patients with very different neural features.
- **Location**: Bethlehem2020.pdf.md, lines 13-16 and 61-63.

### 2. Clear Definition of Target Population

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The paper identifies the use of the ABIDE dataset, which defines the target population. However, it does not explicitly state the diagnostic criteria (e.g., DSM-IV, DSM-5) used for the diagnosis of Autism Spectrum Disorder within the paper itself. It relies on the reader to know the details of the ABIDE dataset. Demographic characteristics are described.
- **Supporting Text**: In this study, we /uniFB01 rst sought to leverage large neuroimaging datasets to yield greater statistical power for identifying subtle effects. To achieve this, we utilized the ABIDE datasets (ABIDE I and II; 15) (see Supplementary Fig. 1).
- **Location**: Bethlehem2020.pdf.md, lines 221-223.

### 3. Clarity of Inclusion and Exclusion Criteria

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The paper describes exclusion criteria based on image quality (Euler index) and data availability. However, a clear flowchart of participant selection is missing, and the initial number of participants before exclusion is not explicitly stated in the main text.
- **Supporting Text**: After matching case and control groups and excluding scans of poorer quality (see supplementary materials) we were left with a-sample size N = 870 per group (Tables 2 and 3). and We excluded all subjects with a Euler score of 300 or higher in either hemisphere. and Age bins that contained fewer than /uniFB01 ve data-points in the TD group were excluded from subsequent analysis...
- **Location**: Bethlehem2020.pdf.md, lines 223-225 and 105-107, and Bethlehem2020_sup1.pdf.md, lines 30-32.

### 4. Validity of Normative Modeling Outcome Measures

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper clearly defines the outcome measure (cortical thickness), the deviation score (w-score), the normative reference population (TD group from ABIDE), and the interpretation of the deviation scores.
- **Supporting Text**: CT of each vertex was de /uniFB01 ned as the shortest distance between vertices of the GM/WM boundary and the pial surface and The w-score for an individual thus re /uniFB02 ects how far away their CT is from TD norms in units of standard deviation. and Because w-scores are computed for every brain region, we get a w-score map for each ASD participant showing how each brain region for that individual is atypical relative to TD norms.
- **Location**: Bethlehem2020.pdf.md, lines 231-232, 103-104, and 104-106.

### 5. Handling of Confounding Variables

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper clearly describes the methods used to handle confounding variables, including age, sex, imaging site, and head motion. They used a linear mixed-effects model, included covariates, and performed matching.
- **Supporting Text**: We used a linear mixed effects model with scanner site as a random effect. Given the potentially strong contribution of age we chose to include this as /uniFB01 xed effects covariates in the model. ... All models also included Euler indices 38 and mean framewise displacement 37 as confound regressors
- **Location**: Bethlehem2020.pdf.md, lines 245-249.

### 6. Clarity of Data Sources

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper clearly states that the data was sourced from the ABIDE I and II datasets and provides a link to the dataset's website. The supplementary materials also provide information about the specific sites included.
- **Supporting Text**: To achieve this, we utilized the ABIDE datasets (ABIDE I and II; 15) (see Supplementary Fig. 1). and Original unprocessed neuroimaging data is openly available through the ABIDE consortium: http://fcon_1000.projects.nitrc.org/indi/abide/abide_I.html.
- **Location**: Bethlehem2020.pdf.md, lines 222-223 and 265-266.

### 7. Description of Image Acquisition Protocol

- **Answer**: No
- **Confidence Rating**: High
- **Negative Answer Category**: Missing
- **Reason**: The paper explicitly states that the imaging acquisition parameters are highly heterogeneous across the different sites in the ABIDE dataset. However, it does not provide a detailed description of these parameters. It relies on the reader to find this information from the ABIDE consortium.
- **Supporting Text**: The dataset also presents a post-hoc collection of sites accumulated through the ABIDE initiative, whereby scanners, imaging acquisition sequences and parameters, sample ascertainment, etc., are highly heterogeneous.
- **Location**: Bethlehem2020.pdf.md, lines 201-204.

---

## Assessment Items - Group B

### 1. Details of Data Preprocessing

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper clearly describes the data preprocessing pipeline, including the software used (FreeSurfer v5.3.0), the specific steps involved in the `recon-all` pipeline, and the quality control procedure using the Euler index.
- **Supporting Text**: Cortical surface reconstruction was performed using the MPRAGE (T1) image of each participant with FreeSurfer (http://surfer.nmr.mgh.harvard.edu/) version (v5.3.0, to ensure comparability with previous ABIDE publications). The reconstruction pipeline performed by FreeSurfer ' recon-all ' involved intensity normalization, registration to Talairach space, skull stripping, WM segmentation, tessellation of the WM boundary, and automatic correction of topological defects. and To assess the quality of Freesurfer reconstructions we computed the Euler index 38 . The Euler number is a quantitative proxy index of segmentation quality and has shown high overlap with manual quality control labelling 38 .
- **Location**: Bethlehem2020.pdf.md, lines 226-231 and 233-235.

### 2. Clarity of Data Partitioning Methods

- **Answer**: No
- **Confidence Rating**: High
- **Negative Answer Category**: Missing
- **Reason**: The paper does not describe any data partitioning into training, validation, and test sets. The normative model was built using the entire typically developing (TD) cohort and then applied to the autism spectrum disorder (ASD) cohort.
- **Supporting Text**: Normative modelling of age-related CT effects was done utilizing male-only data from the typically developing group (TD) (see ' Methods ' section for full sample description, Fig. 1 for a schematic overview and Supplementary Figs. 1 and 2 for more demographics information).
- **Location**: Bethlehem2020.pdf.md, lines 81-84.

### 3. Details of Normative Modeling Approach

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper clearly describes the normative modeling approach used, which is a local polynomial regression fitting procedure (LOESS). It also details the hyperparameter optimization method and the software function used.
- **Supporting Text**: We used a local polynomial regression /uniFB01 tting procedure (LOESS) 34,35 , where the local width or smoothing kernel of the regression was determined by the model that provided the overall smallest sum of squared errors using hyperparameter optimization across 5 -100% of the full age range using Brent ' s method 36 as implemented in the R optim function from the stats package.
- **Location**: Bethlehem2020.pdf.md, lines 84-88.

### 4. Details of Training Algorithm

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper uses LOESS, a non-parametric method. The "training" involves fitting the local models. The paper clearly describes the optimization process for the smoothing kernel, which is the main hyperparameter of the LOESS model.
- **Supporting Text**: ...where the local width or smoothing kernel of the regression was determined by the model that provided the overall smallest sum of squared errors using hyperparameter optimization across 5 -100% of the full age range using Brent ' s method 36 as implemented in the R optim function from the stats package.
- **Location**: Bethlehem2020.pdf.md, lines 85-88.

### 5. Model Performance Evaluation Metrics

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The paper does not report traditional model performance metrics like MSLL or correlation between predicted and actual values. Instead, it focuses on the consistency of the normative model through bootstrapping and comparison with a centile-based approach. The performance is evaluated more in terms of its utility for outlier detection and its impact on case-control analyses.
- **Supporting Text**: We also assessed consistency of our output using centiles scoring and consistency of the normative model using extensive bootstrapping and sensitivity analyses, both showed high outcome consistency (see ' Methods ' section and Supplementary Materials; Supplementary Figs. 3 -5).
- **Location**: Bethlehem2020.pdf.md, lines 88-91.

### 6. Implementation of Internal Validation

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The paper uses bootstrapping to assess the reliability of the w-scores, which can be considered a form of internal validation. However, it does not use a separate validation set to evaluate the model's performance, which is a more standard approach to internal validation.
- **Supporting Text**: To assess the reliability of the normative w-score we permuted the normative sample (1000 bootstraps, with replacement) and computed 1000 permuted w-scores for each individual and each brain region.
- **Location**: Bethlehem2020.pdf.md, lines 250-252.

### 7. External Data Validation

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The paper does not apply the trained model to an independent external dataset. However, it does compare its findings with a study that used a different dataset (EU-AIMS LEAP), noting some consistencies in the results. This is an indirect form of external validation.
- **Supporting Text**: The current results can be contrasted with a recent study on the EU-AIMS LEAP cohort 44 . This study differs from the current work in being based on a completely independent dataset (EUAIMS LEAP vs. ABIDE).
- **Location**: Bethlehem2020.pdf.md, lines 191-193.

### 8. Description of Dataset Characteristics

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper provides tables with demographic and clinical characteristics of the participants, including sample sizes, age, sex, IQ, and clinical scores (ADOS, SRS). It also mentions the matching procedure used to balance the groups.
- **Supporting Text**: The characteristics of the /uniFB01 nal autism sample are listed in Table 1. and Groups were subsequently matched on age using the non-parametric nearest neighbour matching procedure implemented in the Matchit package in R and Tables 1, 2, and 3.
- **Location**: Bethlehem2020.pdf.md, lines 107-108, 223-225, and Tables 1, 2, 3.

### 9. Performance Metrics and Statistical Uncertainty

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The paper reports effect sizes and p-values for the case-control analyses and brain-behavior correlations. However, for the normative model itself, it does not report traditional performance metrics with statistical uncertainty (e.g., confidence intervals). The bootstrapping analysis provides some information about the reliability of the w-scores, but not in a standard performance metric format.
- **Supporting Text**: Of these regions, most are of small effect size, with 26 of the detected 27 regions showing an effect <0.2 standard deviations of difference (Fig. 2a). and This difference from an expected proportion of 5% in the present sample corresponds to a X 2 of 3.85 (with Yates continuity correction 40 ) that is signi /uniFB01 cant at p = 0.049
- **Location**: Bethlehem2020.pdf.md, lines 111-113 and 131-133.

### 10. Consideration for Reproducibility

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper provides links to the code and data used in the study, which are available on GitHub. It also specifies the version of the software used for preprocessing. This information is sufficient to ensure the reproducibility of the study.
- **Supporting Text**: All data is openly available on GitHub 32 , this includes all measures extracted from the raw imaging data alongside the relevant phenotypic and quality control measures. and All code is openly available on GitHub 32 , and the centiles cross-validation code can be found in https://github.com/deep-introspection/PyNM.
- **Location**: Bethlehem2020.pdf.md, lines 263-265 and 267-269.

### 11. Interpretation Specific to Normative Modeling

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper provides a thorough discussion of the interpretation of the normative modeling results, including the implications for understanding heterogeneity in ASD, the advantages over traditional case-control approaches, and the potential for personalized medicine.
- **Supporting Text**: Utilizing normative modelling as a way of identifying and removing CT-atypical outlier patients, we /uniFB01 nd here that most small case-control differences are driven by a small subgroup of patients with high CTatypicality for their age, which indeed begs the question of the existence of on-average atypical cortical morphology in autism 14 . and Normative models may provide an alternative to case-control models that test hypotheses at a group-level, by allowing additional insight to be made at more individualized levels, and thus help further progress towards personalized medicine for ASD.
- **Location**: Bethlehem2020.pdf.md, lines 171-175 and 213-216.

---

## Additional Comments

**Additional Comments**: The normative model was built using only male participants to reduce sex-related heterogeneity. The authors acknowledge this limitation and suggest that future studies should include females.
