# Data Extraction Form

## Study Identification

- **Study ID**: Cirstian2024
- **Reference File Names**: Cirstian2024.pdf.md; media-6.docx.md
- **Author, Journal, Year**: Cirstian et al., NR, 2024
- **Title**: Lifespan Normative Models of White Matter Fractional Anisotropy: Applications to Early Psychosis
- **DOI**: NR

---

## Study Characteristics

- **Study Objective**: Construct large-scale lifespan normative models of white matter fractional anisotropy (FA) from diffusion MRI in healthy individuals and apply them to an early psychosis cohort to quantify individual-level deviations and relate them to symptoms.
- **Study Design**: Cross-sectional
- **Study Design Other**: -

---

## Reference Cohort & Imaging

#### Dataset Name
- **Answer**: HCP Baby; HCP Development; HCP Young Adult; HCP Aging; UK Biobank
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Materials and Methods explicitly lists the five cohorts used to construct the lifespan dataset.
- **Supporting Text**: The construction of the lifespan dataset involved integrating data from five cohorts ... the HCP Baby, HCP Development, HCP Young Adult, HCP Aging datasets, and the UK Biobank.
- **Location**: materials/Cirstian2024.pdf.md: Materials and Methods > Data acquisition and processing

#### HC N
- **Answer**: 24,915
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: They report the combined subject count used for modeling and the exact split size for train/test.
- **Supporting Text**: we began by splitting the dataset of subjects (N=24,915) into two equal groups: a test set (N=12,457) and a training set (N=12,457)
- **Location**: materials/Cirstian2024.pdf.md: Normative modeling

#### HC Age
- **Answer**: Range: 0–100 years; mean/SD: NR
- **Confidence Rating**: High
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: Age range is stated in the abstract but no pooled mean/SD is reported for the healthy reference sample.
- **Supporting Text**: using diffusion MRI data from over 25,000 healthy individuals aged 0–100 years.
- **Location**: materials/Cirstian2024.pdf.md: Abstract

#### HC Sex
- **Answer**: No
- **Confidence Rating**: Medium
- **Negative Answer Category**: Missing
- **Reason**: They describe stratification by sex in the split but do not report overall HC sex counts/percentages.
- **Supporting Text**: stratified to ensure an even distribution of sex, race, dataset and site.
- **Location**: materials/Cirstian2024.pdf.md: Normative modeling

#### Imaging Modality
- **Answer**: Diffusion MRI
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The study constructs normative models of FA derived from diffusion MRI.
- **Supporting Text**: This study presents large-scale normative models of white matter ... using diffusion MRI data ... fractional anisotropy (FA)
- **Location**: materials/Cirstian2024.pdf.md: Abstract

#### Analysis Level
- **Answer**: ROI-level
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: They compute mean FA along the skeleton for 48 JHU white matter tracts (region/tract-wise).
- **Supporting Text**: This process delineated 48 white matter (WM) tracts ... for which we computed the mean FA values along the skeleton of each tract.
- **Location**: materials/Cirstian2024.pdf.md: Data acquisition and processing

#### Preprocessing Pipeline
- **Answer**: HCP pipeline (B0 normalization; EPI distortion; eddy/motion corrections); UK Biobank standard processing; FSL DTIfit; TBSS (FMRIB58_FA; skeleton threshold 0.2); JHU atlas segmentation
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The preprocessing steps, tools, and atlases are explicitly listed.
- **Supporting Text**: pre-processing was performed: B0 intensity normalization, correction for EPI distortions, eddy-current-induced and movement corrections ... using the HCP-pipeline ... UKB dataset was already processed ... estimated the DTI model using DTIfit ... ran Tract-Based Spatial Statistics (TBSS) ... registration to a standard space (FMRIB58_FA) ... skeletonized ... threshold at 0.2 ... segmentation using the Johns Hopkins University (JHU) atlas.
- **Location**: materials/Cirstian2024.pdf.md: Data acquisition and processing

#### Quality Checking
- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: They describe harmonized preprocessing, TBSS skeleton thresholding, and exclusion of gross outliers (>5 SD) but do not report QC exclusion counts or specific automated QC metrics.
- **Supporting Text**: we refit the models after excluding gross outliers having deviations larger than 5 standard deviations from the mean ... TBSS ... skeletonized ... threshold at 0.2
- **Location**: materials/Cirstian2024.pdf.md: Normative modeling; Data acquisition and processing

#### Quality Checking Detail
- **Answer**: Gross outlier removal (|z| > 5) prior to final fit; TBSS skeleton threshold 0.2; harmonized preprocessing across cohorts.
- **Confidence Rating**: Medium
- **Negative Answer Category**: Not Negative
- **Reason**: Procedures are described, but no explicit exclusion counts/percentages are provided.
- **Supporting Text**: we refit the models after excluding gross outliers having deviations larger than 5 standard deviations from the mean ... TBSS ... threshold at 0.2
- **Location**: materials/Cirstian2024.pdf.md: Normative modeling; Data acquisition and processing

#### Site Effect Handling
- **Answer**: Model-based
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Site is included as a covariate in the BLR model; later adapted via transfer learning using local controls to account for site-specific effects.
- **Supporting Text**: The model incorporated ... dummy coded race, and site. ... Using transfer learning ... adapt the models with only a small amount of calibration data to account for site-specific effect.
- **Location**: materials/Cirstian2024.pdf.md: Normative modeling; Application to a clinical dataset

#### Site Effect Handling Detail
- **Answer**: Site/scanner included as fixed covariate in warped Bayesian linear regression; transfer learning with small local control subset for calibration to account for site-specific effects.
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: They explicitly list site as a covariate and describe transfer learning for calibration.
- **Supporting Text**: The model incorporated ... sex, age, and dummy coded race, and site. ... Using transfer learning ... adapt the models ... account for site-specific effect.
- **Location**: materials/Cirstian2024.pdf.md: Normative modeling; Application to a clinical dataset

---

## Normative Modeling

#### Model Origin
- **Answer**: New
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: They construct new FA normative models across the lifespan rather than using pre-trained models.
- **Supporting Text**: This study presents large-scale normative models of white matter ... We provide these models to the field via our established no-code software platform ... and via open-source software tools.
- **Location**: materials/Cirstian2024.pdf.md: Abstract; Conclusion

#### Model Origin Detail
- **Answer**: -
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Not applicable for newly trained models.
- **Supporting Text**: -
- **Location**: -

#### Modeling Method
- **Answer**: Warped Bayesian Linear Regression (BLR) with B-spline basis for age and SinhArcsinh warping
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: They specify BLR with age modeled via third-order B-spline basis and warping to address non-Gaussian distributions.
- **Supporting Text**: we employed a warped Bayesian linear regression (BLR) ... applying a third-order polynomial B-spline basis expansion over age ... combined with a SinhArcsinh warping function.
- **Location**: materials/Cirstian2024.pdf.md: Normative modeling

#### Software Tool
- **Answer**: PCNtoolkit (Python 3.8)
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: They state analyses were conducted using Python with the Predictive Clinical Neuroscience toolkit.
- **Supporting Text**: All statistical analyses were conducted using Python version 3.8, with the Predictive Clinical Neuroscience PCN toolkit (GitHub, PCNtoolkit).
- **Location**: materials/Cirstian2024.pdf.md: Normative modeling

#### Response Variable
- **Answer**: Fractional Anisotropy (FA) across 48 JHU white matter tracts (TBSS skeleton means)
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: They compute mean FA per tract along the TBSS skeleton for 48 tracts.
- **Supporting Text**: DTI model using DTIfit ... extract the fractional anisotropy (FA) values ... delineated 48 white matter (WM) tracts ... computed the mean FA values along the skeleton of each tract.
- **Location**: materials/Cirstian2024.pdf.md: Data acquisition and processing

#### Predictor Variables
- **Answer**: Age; Sex; Race; Site
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: They list sex, age, dummy coded race, and site as covariates.
- **Supporting Text**: The model incorporated several covariates, including sex, age, and dummy coded race, and site.
- **Location**: materials/Cirstian2024.pdf.md: Normative modeling

#### Predictor Effects
- **Answer**: Age (fixed, B-spline basis); Sex (fixed); Race (fixed); Site (fixed covariate)
- **Confidence Rating**: Medium
- **Negative Answer Category**: Not Negative
- **Reason**: They do not explicitly use hierarchical random effects; site is included as a covariate; age uses spline basis.
- **Supporting Text**: The model incorporated ... sex, age, and dummy coded race, and site. ... applying a third-order polynomial B-spline basis expansion over age.
- **Location**: materials/Cirstian2024.pdf.md: Normative modeling

#### NM Vldtn Handle NS
- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: They address nonlinearity (age B-splines), non-Gaussian distributions (SinhArcsinh warping), site effects (covariate; transfer learning), and outliers (|z|>5 exclusion).
- **Supporting Text**: ... non-Gaussian distributions, we employed a warped Bayesian linear regression ... third-order ... B-spline ... combined with a SinhArcsinh warping function. ... site ... transfer learning ... excluding gross outliers having deviations larger than 5 standard deviations ...
- **Location**: materials/Cirstian2024.pdf.md: Normative modeling; Application to a clinical dataset

#### NM Vldtn Same Domain Nonindep
- **Answer**: No
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: They use an explicit held-out test set rather than validating on non-independent training data.
- **Supporting Text**: splitting the dataset of subjects (N=24,915) into two equal groups: a test set ... and a training set ...
- **Location**: materials/Cirstian2024.pdf.md: Normative modeling

#### NM Vldtn Same Domain Indep
- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: They validate model fit and deviation distributions on a held-out test set within the same combined cohorts.
- **Supporting Text**: splitting the dataset ... into ... test ... and ... training ... calculated the fit statistics, including explained variance, skew, and kurtosis.
- **Location**: materials/Cirstian2024.pdf.md: Normative modeling

#### NM Vldtn Diff Domain
- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: They apply models to an external clinical dataset (HCP-EP) with transfer learning calibration.
- **Supporting Text**: Next, we applied the model to the Human Connectome Project Early Psychosis (HCP-EP) dataset ... Using transfer learning ... adapt the models ... account for site-specific effect.
- **Location**: materials/Cirstian2024.pdf.md: Application to a clinical dataset

---

## Clinical Application & Analysis

#### Clinical Dataset
- **Answer**: HCP Early Psychosis (HCP-EP)
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: They explicitly state application to HCP-EP and describe its composition.
- **Supporting Text**: we applied the model to the Human Connectome Project Early Psychosis (HCP-EP) dataset ... participants diagnosed with early psychosis (n=118) and control participants (n=55).
- **Location**: materials/Cirstian2024.pdf.md: Application to a clinical dataset

#### Diseases Studied
- **Answer**: Early Psychosis (EP)
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Clinical target described as early psychosis.
- **Supporting Text**: participants diagnosed with early psychosis (n=118)
- **Location**: materials/Cirstian2024.pdf.md: Application to a clinical dataset

#### Clinical Groups N
- **Answer**: EP:118
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Patient count is reported for HCP-EP.
- **Supporting Text**: participants diagnosed with early psychosis (n=118)
- **Location**: materials/Cirstian2024.pdf.md: Application to a clinical dataset

#### Clinical Groups Age
- **Answer**: EP: mean 22.7; sd 3.7
- **Confidence Rating**: Medium
- **Negative Answer Category**: Not Negative
- **Reason**: Age summary for patients is provided in supplementary Table S2.
- **Supporting Text**: Age (μ, σ) | 22.7, 3.7
- **Location**: materials/media-6.docx.md: Table S2

#### Clinical Groups Sex
- **Answer**: EP: M 62%, F 38%
- **Confidence Rating**: Medium
- **Negative Answer Category**: Not Negative
- **Reason**: Sex proportions for patients are shown in Table S2.
- **Supporting Text**: Sex (M%, F%) | 62%, 38%
- **Location**: materials/media-6.docx.md: Table S2

#### Deviation Metric
- **Answer**: Z-scores per tract; extreme deviation threshold |Z| > 2.6; group mean deviation comparisons; total outlier proportion per tract
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: They computed z-scores and used a |Z|>2.6 threshold to identify extreme deviations, with group comparisons.
- **Supporting Text**: computed z-scores ... set a z-score threshold between -2.6 and 2.6 ... identify extreme deviations
- **Location**: materials/Cirstian2024.pdf.md: Application to a clinical dataset

- **Association Analysis**: Group comparisons of deviations (t-tests with FDR correction); Mann–Whitney U for extreme deviation proportions; multi-view sparse CCA (msCCA) mapping symptom domains to multimodal deviation profiles.
- **Key Findings Brief**: Normative FA models reveal substantial white matter heterogeneity in early psychosis, highlighting regions such as the right uncinate fasciculus and thalami not captured by group-level analyses.
- **Key Findings Detailed**: Applying the lifespan FA normative models to HCP-EP, the authors computed tract-wise deviation z-scores, compared patient vs control mean deviations using t-tests with FDR correction, and quantified extreme deviations using a |Z| > 2.6 threshold with Mann–Whitney U tests. Multivariate msCCA related symptom domains to multimodal deviations. Results indicate extensive heterogeneity in white matter alterations across patients, with key regions including the right uncinate fasciculus and thalami, consistent with individualized deviation profiles rather than uniform group differences.
- **Key Limitations**: Lifespan dataset has sparse sampling at ages 5–8 and >85, which may limit generalizability at extremes; demographic factors such as socioeconomic background not fully explored; potential WEIRD sampling bias; race included as fixed effect but acknowledged as an imperfect proxy for ethnicity; models also released without race covariate.
- **Application Notes**: Models released via PCNportal and open-source GitHub; transfer learning enables calibration with small local control sets; deviation analysis combined across diffusion and structural modalities in msCCA.

---

## General Notes

**General Notes**: Reference cohorts: HCP Baby/Development/Young Adult/Aging; UK Biobank. TBSS pipeline (FMRIB58_FA) with JHU tract means (48 tracts). Held-out same-domain test set used to compute EV/skew/kurtosis; external validation on HCP-EP.
