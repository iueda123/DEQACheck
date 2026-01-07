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
- **Reason**: The abstract clearly states the goal to parameterize cortical thickness in ASD as individualized atypicality relative to TD norms, highlight subgroup with highly age-atypical CT, and contrast with traditional case-control analyses.
- **Supporting Text**: We examined how cortical thickness (CT) in ASD can be parameterized as an individualized metric of atypicality relative to typically-developing (TD) age-related norms. Across a large sample (n = 870 per group) and wide age range (5–40 years), we applied normative modelling ... and isolating a small subgroup with highly age-atypical CT.
- **Location**: materials/Bethlehem2020.pdf.md:14-15

### 2. Clear Definition of Target Population

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: Population (ASD vs TD) and sex restriction to males are clear, but diagnostic criteria (e.g., DSM) are not specified; control definition is implicit (TD).
- **Supporting Text**: Females were excluded from further analyses due to known sex differential effects in autism and the lack of available data to estimate population norms (see 'Methods' section for details). ... both were binned into one-year age bins. ... compute a w-score ... for every individual with autism ...
- **Location**: materials/Bethlehem2020.pdf.md:36-46

### 3. Clarity of Inclusion and Exclusion Criteria

- **Answer**: Partial
- **Confidence Rating**: High
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: Exclusions include FreeSurfer failures, age bins with <5 TD, subjects without rs-fMRI (for motion), and top ~10% by Euler index (~300). Final sample size is stated. However, comprehensive inclusion criteria and uniform application beyond these points are not fully detailed.
- **Supporting Text**: Age bins that contained fewer than five data-points in the TD group were excluded ... With the inclusion of motion we also excluded individuals for which no resting-state fMRI was available. ... We chose to not conduct manual segmentations and excluded failed subjects from any subsequent analysis ... we chose to exclude the top 10% of subjects with an extreme Euler index (corresponding to a Euler index of ~300) ... Across a large sample (n = 870 per group) ...
- **Location**: materials/Bethlehem2020.pdf.md:49;111-117;137;14-15

### 4. Validity of Normative Modeling Outcome Measures

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Brain measure (cortical thickness) and parcellations are defined; normative approach (LOESS, age- and sex-specific norms) and w-score computation are described; interpretation of w-scores as deviations from TD norms is explicit.
- **Supporting Text**: LOESS regression is used to estimate the developmental trajectory on CT for every individual brain region to obtain an age-specific mean and standard deviation. ... for each individual with autism and each brain region the normative mean and standard deviation are used to compute a w-score (analogous to a z-score) ... The w-score for an individual thus reflects how far away their CT is from TD norms in units of standard deviation.
- **Location**: materials/Bethlehem2020.pdf.md:36-49

### 5. Handling of Confounding Variables

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Models include site, sex, age, in-scanner head motion, and Euler index as covariates. Sensitivity analyses also examine motion and Euler effects.
- **Supporting Text**: ... conventional case-control differences using linear mixed effect modelling including site, sex, age, inscanner head motion and Euler index as covariates. ... Sensitivity analyses on the effects of reconstruction quality using Euler index as well as residual effects of in-scanner head motion ...
- **Location**: materials/Bethlehem2020.pdf.md:51;85

### 6. Clarity of Data Sources

- **Answer**: Partial
- **Confidence Rating**: High
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: ABIDE I and ABIDE II are named and site/participant info is in the supplement, but acquisition time/location details are not described in this paper.
- **Supporting Text**: Across both ABIDE I and ABIDE II CT was extracted for each subject ...; ## Site and participant information (Supplementary)
- **Location**: materials/Bethlehem2020.pdf.md:139; materials/Bethlehem2020_sup1.pdf.md:32-38

### 7. Description of Image Acquisition Protocol

- **Answer**: No
- **Confidence Rating**: Medium
- **Negative Answer Category**: Missing
- **Reason**: Imaging acquisition parameters (e.g., TR, TE, flip angle, scanner manufacturer/field strength) are not reported; only processing details are given.
- **Supporting Text**: Imaging processing and quantification ... Cortical surface reconstruction was performed ... with FreeSurfer ... version v5.3.0 ... (acquisition parameters not specified in the provided files).
- **Location**: materials/Bethlehem2020.pdf.md:111-117 (no TR/TE/flip/manufacturer reported)

---

## Assessment Items - Group B

### 1. Details of Data Preprocessing

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Processing with FreeSurfer v5.3.0 recon-all is described, including steps; QC via Euler index; parcellations (308, 360 HCP) and mapping are specified.
- **Supporting Text**: Cortical surface reconstruction was performed ... with FreeSurfer ... recon-all involved intensity normalization, registration to Talairach space, skull stripping, WM segmentation, tessellation ... correction of topological defects ... To assess the quality of Freesurfer reconstructions we computed the Euler index ... Across both ABIDE I and ABIDE II CT was extracted ... 308 regions ... 360 regions (HCP) ...
- **Location**: materials/Bethlehem2020.pdf.md:111-117;137;139-145

### 2. Clarity of Data Partitioning Methods

- **Answer**: NA
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: No training/validation/test partitioning applies; normative LOESS-based reference is computed on TD data to derive w-scores, not a supervised predictive model.
- **Supporting Text**: Schematic overview of normative modelling ... LOESS regression ... compute a w-score ... for every individual ... (no mention of train/validation/test splits).
- **Location**: materials/Bethlehem2020.pdf.md:36-46

### 3. Details of Normative Modeling Approach

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Norms estimated per region via LOESS; one-year age bins; sex-specific norms; w-score analogous to z-score; exclusions for sparse bins.
- **Supporting Text**: LOESS regression is used to estimate the developmental trajectory ... age-specific mean and standard deviation ... both were binned into one-year age bins ... compute a w-score (analogous to a z-score) ... Age bins ... fewer than five data-points ... were excluded ...
- **Location**: materials/Bethlehem2020.pdf.md:36-49

### 4. Details of Training Algorithm

- **Answer**: NA
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: No iterative training/optimization algorithm (e.g., gradient descent) is used; LOESS smoothing and bin-based statistics are applied.
- **Supporting Text**: Methods describe LOESS/binned statistics for normative estimation rather than a trained predictive model.
- **Location**: materials/Bethlehem2020.pdf.md:36-49

### 5. Model Performance Evaluation Metrics

- **Answer**: No
- **Confidence Rating**: High
- **Negative Answer Category**: Missing
- **Reason**: Standard NM performance metrics (e.g., EV, MSLL, R²) for the normative model are not reported.
- **Supporting Text**: Not reported in the provided files; analyses focus on case-control LME results, outlier prevalence, and phenotype correlations.
- **Location**: materials/Bethlehem2020.pdf.md (no EV/MSLL/R² reported)

### 6. Implementation of Internal Validation

- **Answer**: Partial
- **Confidence Rating**: High
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: Internal sensitivity checks include bootstrapping and systematic exclusion (top motion/Euler) with spatial correlation assessments; however, no k-fold cross-validation of normative fits is described.
- **Supporting Text**: BOOTSTRAPPING ... Supplemental Figure S4: bootstrap validation ... we conducted a cross-validation analysis by systematically excluding the top 5% of motion subject and top 5% of Euler subjects and assessed the spatial correlation ... Resulting maps were highly consistent ...
- **Location**: materials/Bethlehem2020_sup1.pdf.md:12;50-54

### 7. External Data Validation

- **Answer**: No
- **Confidence Rating**: High
- **Negative Answer Category**: Missing
- **Reason**: No validation on an independent external dataset is performed in this study (discussion references another cohort but no external testing here).
- **Supporting Text**: The current results can be contrasted with a recent study on the EU-AIMS LEAP cohort ... studies differ ... (comparative discussion only).
- **Location**: materials/Bethlehem2020.pdf.md:96-108

### 8. Description of Dataset Characteristics

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Sample sizes, sex breakdowns, and phenotypic measures (IQ, ADOS, SRS) are presented; age range and group sizes are reported; exclusions described.
- **Supporting Text**: Table 3 Sample characteristics. ... IQ ... ADOS ... SRS ... Across a large sample (n = 870 per group) and wide age range (5–40 years) ...
- **Location**: materials/Bethlehem2020.pdf.md:120-134;14-15

### 9. Performance Metrics and Statistical Uncertainty

- **Answer**: Partial
- **Confidence Rating**: High
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: Reports p-values, effect sizes, and FDR correction; confidence intervals/credible intervals for key metrics are not provided.
- **Supporting Text**: ... regions passing FDR correction ... effect sizes ... (Fig. 2a) ... Panel A shows ... p < .001 ... correlations not passing FDR ... marked ...
- **Location**: materials/Bethlehem2020.pdf.md:51-53; materials/Bethlehem2020_sup1.pdf.md:64

### 10. Consideration for Reproducibility

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: Software/version (FreeSurfer v5.3.0) is specified and a link to code for an ancillary analysis (Cohen's d) is provided; full analysis code/models are not shared.
- **Supporting Text**: ... FreeSurfer ... version (v5.3.0) ... Cohen's d was computed using custom R code https://github.com/mvlombardo/utils/blob/master/cohens_d.R.
- **Location**: materials/Bethlehem2020.pdf.md:111; materials/Bethlehem2020_sup1.pdf.md:46

### 11. Interpretation Specific to Normative Modeling

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Meaning of w-scores as individual deviations from TD norms is explained; results identify a small subgroup with globally atypical CT; contrasts with case-control analysis and discuss clinical relevance and heterogeneity.
- **Supporting Text**: The w-score for an individual thus reflects how far away their CT is from TD norms in units of standard deviation. ... only a small subset of ASD individuals are actually highly atypical relative to age-norms, driving small on-average case-control differences.
- **Location**: materials/Bethlehem2020.pdf.md:49;14-15;81

---

## Additional Comments

**Additional Comments**: Study uses ABIDE I/II multi-site data; females excluded due to sex differences and limited normative data. Normative estimation via LOESS with age/sex stratification; QC via Euler index with top ~10% excluded; sensitivity checks for motion/Euler and bootstrap outlier robustness in supplement. No scanner acquisition parameters or standard NM performance metrics reported.
