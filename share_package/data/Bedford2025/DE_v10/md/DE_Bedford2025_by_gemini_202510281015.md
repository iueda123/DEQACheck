# Data Extraction Form

## Study Identification

- **Study ID**: Bedford2025
- **Reference File Names**: Bedford2025.pdf.md; 1-s2.0-S0006322324015130-mmc1.md; 1-s2.0-S0006322324015130-mmc2.pdf.md
- **Author, Journal, Year**: Bedford et al., Biological Psychiatry, 2024
- **Title**: Brain-Charting Autism and Attention-De /uniFB01 cit/ Hyperactivity Disorder Reveals Distinct and Overlapping Neurobiology
- **DOI**: 10.1016/j.biopsych.2024.07.024

---

## Study Characteristics

- **Study Objective**: To characterize cortical anatomy associated with autism and ADHD using population modeling on a large, multisite neuroimaging dataset, and to examine sex and age differences, the relationship with autistic traits, and the co-occurrence of autism and ADHD.
- **Study Design**: Cross-sectional
- **Study Design Other**: -

---

## Reference Cohort & Imaging

#### Dataset Name
- **Answer**: Multi-site aggregated dataset (ABIDE I and II; POND; CMI-HBN; ADHD200; NIMH NDA Females with ASD; UK MRC-AIMS; UCSD Biomarkers of Autism)
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### HC N
- **Answer**: 1869
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### HC Age
- **Answer**: mean 15.0, sd 7.9
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### HC Sex
- **Answer**: F 687 (36.8%), M 1182 (63.2%)
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Imaging Modality
- **Answer**: T1-weighted MRI
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Analysis Level
- **Answer**: Voxel-level
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Preprocessing Pipeline
- **Answer**: FreeSurfer v6.0.1
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
- **Answer**: Manual QC of raw image and FreeSurfer surface reconstructions using FSQC tool with a cutoff of 2.5. Euler number was included as a covariate in all analyses.
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Site Effect Handling
- **Answer**: Batch-removal
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Site Effect Handling Detail
- **Answer**: ComBat harmonization was applied to the entire dataset across all global and regional measures, with each site treated as a batch and with covariates of age, sex, and diagnosis to preserve related biological variation. GAMLSS was also used to account for site/scanner effects.
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

---

## Normative Modeling

#### Model Origin
- **Answer**: Pre-trained
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Model Origin Detail
- **Answer**: Reference normative model trained on 75,241 typically developing individuals from Bethlehem et al. (2022), Brain charts for the human lifespan, Nature, 604, 525–533.
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Modeling Method
- **Answer**: GAMLSS
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Software Tool
- **Answer**: R (gamlss package); FSQC tool
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Response Variable
- **Answer**: Cortical thickness (CT), Cortical volume (CV), and Surface area (SA)
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Predictor Variables
- **Answer**: Age; Sex; Site/Scanner
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Predictor Effects
- **Answer**: Age and sex were modeled as fixed effects. Site/scanner were modeled as random effects.
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### NM Vldtn Handle NS
- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The study used ComBat for harmonization and GAMLSS, which models site/scanner effects as random effects, to handle nuisance variables.
- **Supporting Text**: Generalized additive models of location scale and shape has been shown to adequately account for batch effects related to differences between siteand scanner-speci /uniFB01 c variables (98). However, we previously (98) noted the relatively lower stability of the out-of-sample models for n , 100. Due to the smaller sample sizes of some sites in our dataset and higher variability in the clinical samples, we /uniFB01 rst harmonized our data using ComBat (111), consistent with previous work (112). ComBat was applied to the entire dataset across all global and regional measures, with each site treated as a batch and with covariates of age, sex, and diagnosis to preserve related biological variation.
- **Location**: Bedford2025.pdf.md, page 7

#### NM Vldtn Same Domain Nonindep
- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The study performed sensitivity analyses, including comparing in-sample and out-of-sample centiles.
- **Supporting Text**: We also conducted sensitivity analyses on non -ComBat-harmonized centiles and compared in-sample and out-of-sample centiles (Supplemental Methods Sections 2 and 3).
- **Location**: Bedford2025.pdf.md, page 8

#### NM Vldtn Same Domain Indep
- **Answer**: No
- **Confidence Rating**: High
- **Negative Answer Category**: Missing
- **Reason**: The paper does not mention validation on an independent dataset from the same domain.
- **Supporting Text**: 
- **Location**: 

#### NM Vldtn Diff Domain
- **Answer**: No
- **Confidence Rating**: High
- **Negative Answer Category**: Missing
- **Reason**: The paper does not mention validation on a dataset from a different domain.
- **Supporting Text**: 
- **Location**: 

---

## Clinical Application & Analysis

#### Clinical Dataset
- **Answer**: ABIDE I and II; POND; CMI-HBN; ADHD200; NIMH NDA Females with ASD; UK MRC-AIMS; UCSD Biomarkers of Autism
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Diseases Studied
- **Answer**: Autism spectrum disorder (ASD); Attention Deficit Hyperactivity Disorder (ADHD)
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Clinical Groups N
- **Answer**: Autism: 1399; ADHD: 987; Autism+ADHD: 203
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Clinical Groups Age
- **Answer**: Autism: mean 14.8, sd 8.9; ADHD: mean 11.1, sd 3.1; Autism+ADHD: mean 11.37, sd 4.51
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Clinical Groups Sex
- **Answer**: Autism: F 288 (20.6%), M 1111 (79.4%); ADHD: F 270 (27.4%), M 717 (72.6%); Autism+ADHD: F 28 (13.8%), M 175 (86.2%)
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

#### Deviation Metric
- **Answer**: Centile score
- **Confidence Rating**: 
- **Negative Answer Category**: 
- **Reason**: 
- **Supporting Text**: 
- **Location**: 

- **Association Analysis**: Multiple linear regressions were used to examine diagnostic group differences in centile scores. Sex-by-diagnosis interactions were examined, and sex-stratified analyses were used. Spearman correlations and spin permutation testing were used to assess similarity of sex-specific effect size maps. An age-by-diagnosis interaction was conducted to assess age-dependent diagnostic differences.
- **Key Findings Brief**: The study identified distinct cortical differences in autism and ADHD, which are differentially affected by age and sex, and also found unique patterns related to their co-occurrence.
- **Key Findings Detailed**: Autistic individuals showed greater cortical thickness and volume in the superior temporal cortex. Individuals with ADHD showed more global increases in cortical thickness but lower cortical volume and surface area. The co-occurring autism+ADHD group showed widespread increases in cortical thickness and decreases in surface area. Sex modulated the neuroanatomy of autism but not ADHD, and there was an age-by-diagnosis interaction for ADHD only.
- **Key Limitations**: The data come from multiple sources with different scanners and protocols. Effect sizes were small. Cortical measures were averaged across hemispheres. Lack of consistent phenotypic and diagnostic information. The sample is not fully representative, with imbalances in sex, IQ, and racial-ethnic diversity. The study is cross-sectional.
- **Application Notes**: The findings offer insights into developmental processes and could potentially serve as biomarkers. The study also identified potential differential impacts of co-occurring diagnoses of autism and ADHD.

---

## General Notes

**General Notes**: The full list of datasets is: ABIDE (Autism Brain Imaging Data Exchange) (102,103), the POND (Province of Ontario Neurodevelopmental) Network, the HBN (Healthy Brain Network) at the Child Mind Institute (104), the ADHD200 Consortium, the Multimodal Developmental Neurogenetics of Females with ASD dataset from the National Institute of Mental Health Data Archive, the UK MRC-AIMS (Medical Research Council Autism Imaging Multi-centre Study), and the University of California San Diego Biomarkers of Autism study.
