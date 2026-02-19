# Data Extraction Form

## Study Identification

- **Study ID**: Cirstian2024
- **Reference File Names**: Cirstian2024.pdf.md
- **Author, Journal, Year**: Cirstian et al., 2024
- **Title**: Lifespan Normative Models of White Matter Fractional Anisotropy: Applications to Early Psychosis
- **DOI**: NR

---

## Study Characteristics

- **Study Objective**: This study aims to develop large-scale normative models of white matter fractional anisotropy (FA) across the lifespan, and to apply these models to investigate white matter alterations in early psychosis. The study also aims to demonstrate the utility of these models for multi-modal data fusion and to release the models publicly.
- **Study Design**: Cross-sectional
- **Study Design Other**: -

---

## Reference Cohort & Imaging

#### Dataset Name
- **Answer**: Multi-site aggregated dataset (HCP Baby; HCP Development; HCP Young Adult; HCP Aging; and UK Biobank)
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### HC N
- **Answer**: 24915
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### HC Age
- **Answer**: 0-100 years
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### HC Sex
- **Answer**: NR
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Imaging Modality
- **Answer**: Diffusion MRI
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Analysis Level
- **Answer**: ROI-level
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Preprocessing Pipeline
- **Answer**: FSL-based pipelines; DTIfit; Tract-Based Spatial Statistics (TBSS)
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Quality Checking
- **Answer**: Yes
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Quality Checking Detail
- **Answer**: Outlier exclusion based on Z-score thresholding (>5 standard deviations from the mean).
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Site Effect Handling
- **Answer**: Model-based
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Site Effect Handling Detail
- **Answer**: Site was included as a covariate in the warped Bayesian linear regression (BLR) model.
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

---

## Normative Modeling

#### Model Origin
- **Answer**: New
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Model Origin Detail
- **Answer**: -
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Modeling Method
- **Answer**: warped Bayesian linear regression (BLR)
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Software Tool
- **Answer**: Python version 3.8, with the Predictive Clinical Neuroscience PCN toolkit (GitHub, PCNtoolkit)
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Response Variable
- **Answer**: Fractional Anisotropy (FA)
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Predictor Variables
- **Answer**: Age; Sex; Site; Race
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Predictor Effects
- **Answer**: Age as a fixed effect with a non-linear basis expansion. Sex, site, and race as dummy coded variables.
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### NM Vldtn Handle NS
- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The model explicitly accounts for site effects by including site as a covariate. It also uses a warped Bayesian linear regression to handle non-Gaussian distributions and non-linear effects of age.
- **Supporting Text**: To address potential nonlinear effects and non-Gaussian distributions, we employed a warped Bayesian linear regression (BLR) model and used in previous research [4], [25]. This approach involved applying a third-order polynomial B-spline basis expansion over age, with five evenly spaced knots, combined with a SinhArcsinh warping function. The model incorporated several covariates, including sex, age, and dummy coded race, and site.
- **Location**: 388-393

#### NM Vldtn Same Domain Nonindep
- **Answer**: No
- **Confidence Rating**: High
- **Negative Answer Category**: Missing
- **Reason**: The paper describes splitting the data into training and test sets, and reports out-of-sample metrics. It does not mention validation on the training set.
- **Supporting Text**: To prepare for the modelling stage, we began by splitting the dataset of subjects (N=24,915) into two equal groups: a test set (N=12,457) and a training set (N=12,457), stratified to ensure an even distribution of sex, race, dataset and site.
- **Location**: 386-388

#### NM Vldtn Same Domain Indep
- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The authors split the data into a training and a test set and evaluated the model fit using out-of-sample metrics on the test set.
- **Supporting Text**: We assessed the quality of the normative modeling fit using three key out-ofsample metrics, namely explained variance (EV), evaluating the fit of the median regression line, in addition to skewness and kurtosis, which evaluate the shape of the distribution used to model the centiles.
- **Location**: 158-162

#### NM Vldtn Diff Domain
- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The normative models were applied to the HCP Early Psychosis (HCP-EP) dataset, which is a different domain from the reference cohort.
- **Supporting Text**: Next, we used these models to understand heterogeneity in white matter FA in psychosis. To achieve this, we applied these reference models to the HCP early psychosis (HCP-EP) dataset (N=173 with diffusion data -see supplementary table 2 for demographic information) in order to derive z-scores for each individual and tract.
- **Location**: 185-188

---

## Clinical Application & Analysis

#### Clinical Dataset
- **Answer**: HCP Early Psychosis (HCP-EP)
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Diseases Studied
- **Answer**: Early Psychosis
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Clinical Groups N
- **Answer**: 118
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Clinical Groups Age
- **Answer**: NR
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Clinical Groups Sex
- **Answer**: F37% M63%
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Deviation Metric
- **Answer**: Z-score
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

- **Association Analysis**: Multi-view sparse canonical correlation analysis (msCCA) was used to map symptoms onto deviations from multimodal normative models.
- **Key Findings Brief**: The study found extensive white matter heterogeneity in psychosis, not captured by group-level analyses, with the right uncinate fasciculus and thalami identified as key regions.
- **Key Findings Detailed**: The study developed lifespan normative models of FA and applied them to an early psychosis cohort. While no significant group differences in mean deviations were found, there was significantly more heterogeneity in individuals with psychosis. A multi-modal analysis combining FA and structural deviations showed a significant association with cognitive symptoms and total PANSS scores, driven by the right uncinate fasciculus and bilateral thalamus volume.
- **Key Limitations**: The age distribution of the reference cohort is skewed, with fewer data points at the extremes of the lifespan. The models do not fully explore variability due to demographic factors like socioeconomic background. The dataset is biased towards 'Western Educated, Industrialised, Rich and Democratic' (WEIRD) populations.
- **Application Notes**: The models are publicly available for community use. The study demonstrates the value of normative models for multi-modal data fusion.

---

## General Notes

**General Notes**: The paper also releases models that do not include race as a covariate.
