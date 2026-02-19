# Data Extraction Form

## Study Identification

- **Study ID**: Bethlehem2020
- **Reference File Names**: Bethlehem2020.pdf.md; Bethlehem2020_sup1.pdf.md
- **Author, Journal, Year**: Bethlehem et al., Communications Biology, 2020
- **Title**: A normative modelling approach reveals age-atypical cortical thickness in a subgroup of males with autism spectrum disorder
- **DOI**: 10.1038/s42003-020-01212-9

---

## Study Characteristics

- **Study Objective**: Parameterize cortical thickness (CT) as individualized age-normed deviation (w-scores) to identify ASD subgroups with atypical CT and assess associations with behavioral symptomatology, contrasting against traditional case–control analyses.
- **Study Design**: Cross-sectional
- **Study Design Other**: -

---

## Reference Cohort & Imaging

#### Dataset Name
- **Answer**: ABIDE I; ABIDE II
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Participants were drawn from ABIDE I and II across multiple sites.
- **Supporting Text**: we utilized the ABIDE datasets (ABIDE I and II)... Across both ABIDE I and ABIDE II CT was extracted...
- **Location**: materials/Bethlehem2020.pdf.md L109; L139

#### HC N
- **Answer**: 870
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: After QC and matching, the TD (neurotypical) group size equals 870.
- **Supporting Text**: ...after re-running the sample consisted of 870 individuals with autism and 870 neurotypical individual, matched on Age, Euler and IQ.
- **Location**: materials/Bethlehem2020_sup1.pdf.md L40-L46

#### HC Age
- **Answer**: min 5; max 40; mean NR; sd NR
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: Age range is reported (5–40 years) but mean/SD are not explicitly provided in the text/tables available.
- **Supporting Text**: Across a large sample (n = 870 per group) and wide age range (5–40 years)
- **Location**: materials/Bethlehem2020.pdf.md L15

#### HC Sex
- **Answer**: F 210 (24.1%), M 660 (75.9%)
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: TD group counts by sex sum to 870 in Table 3 (IQ row): Male 660, Female 210.
- **Supporting Text**: TD  Male  N 660 ... TD  Female  N 210
- **Location**: materials/Bethlehem2020.pdf.md L124-L134

#### Imaging Modality
- **Answer**: T1-weighted MRI
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: CT was derived from MPRAGE (T1) images using FreeSurfer.
- **Supporting Text**: Cortical surface reconstruction was performed using the MPRAGE (T1) image of each participant with FreeSurfer...
- **Location**: materials/Bethlehem2020.pdf.md L111-L118

#### Analysis Level
- **Answer**: ROI-level
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: CT was averaged within 308 cortical regions; also analyzed a 360-region HCP parcellation for sensitivity.
- **Supporting Text**: All analyses were done on CT averaged within 308 cortical regions... a parcellation of 360 regions derived from multi-modal features...
- **Location**: materials/Bethlehem2020.pdf.md L31; L139-L140

#### Preprocessing Pipeline
- **Answer**: FreeSurfer v5.3.0; fsaverage mapping; Desikan–Killiany sub-parcellation (308 ROIs); HCP-MMP 360 parcellation
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Detailed FreeSurfer version and pipeline steps are described; parcellations specified (308 ROI sub-parcellation and HCP 360).
- **Supporting Text**: FreeSurfer ... version (v5.3.0, to ensure comparability with previous ABIDE publications)... The 308-region parcellation was constructed in the FreeSurfer fsaverage template... and a parcellation of 360 regions derived from... HCP dataset.
- **Location**: materials/Bethlehem2020.pdf.md L111-L118; L139-L141

#### Quality Checking
- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Euler index was used for structural MRI QC; top ~10% with extreme Euler (~300) excluded; Euler and motion were included as confounds; failed reconstructions excluded.
- **Supporting Text**: We chose to exclude the top 10% of subjects with an extreme Euler index (corresponding to a Euler index of ~300)... included the index itself as a confound variable in all models... We chose to not conduct manual segmentations and excluded failed subjects...
- **Location**: materials/Bethlehem2020.pdf.md L137-L139; L111-L118

#### Quality Checking Detail
- **Answer**: Euler number-based QC; excluded subjects with Euler ≥ ~300 (top 10%); re-matched samples; included Euler and in-scanner head motion (framewise displacement) as covariates in analyses; sensitivity analyses by iteratively excluding top motion/Euler subjects showed high spatial consistency.
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Supplement details thresholds and sensitivity; main text details Euler exclusion and covariates.
- **Supporting Text**: We excluded all subjects with a Euler score of 300 or higher... included both Euler index and framewise displacement as confound variables... cross-validation by systematically excluding the top 5% of motion and top 5% of Euler subjects... resulting maps were highly consistent.
- **Location**: materials/Bethlehem2020_sup1.pdf.md L40-L54; L64-L70

#### Site Effect Handling
- **Answer**: Other
- **Confidence Rating**: Medium
- **Negative Answer Category**: Not Negative
- **Reason**: Scanner/site was modeled as a covariate in linear mixed effects for case–control analyses; no ComBat or hierarchical site modeling for normative modeling.
- **Supporting Text**: ...linear mixed effect modelling including site, sex, age, in-scanner head motion and Euler index as covariates... we observed that site had a large effect on explaining variance in CT...
- **Location**: materials/Bethlehem2020.pdf.md L51-L54; L96-L108

#### Site Effect Handling Detail
- **Answer**: Included site as a fixed covariate in LME during case–control analyses; recognized large site effects on variance. Normative LOESS model trained on pooled TD males without explicit site harmonization.
- **Confidence Rating**: Medium
- **Negative Answer Category**: Not Negative
- **Reason**: Text specifies site as covariate and discusses site heterogeneity; no batch-removal (e.g., ComBat) or hierarchical model reported.
- **Supporting Text**: linear mixed effect modelling including site... A post-hoc collection of sites... scanners, acquisition sequences... highly heterogeneous. As a result, we observed that site had a large effect on explaining variance in CT.
- **Location**: materials/Bethlehem2020.pdf.md L51-L54; L96-L108

---

## Normative Modeling

#### Model Origin
- **Answer**: New
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Normative model is trained within this study on TD males from ABIDE using LOESS; not a pre-trained external model.
- **Supporting Text**: Age-related normative modelling... using a local polynomial regression fitting procedure (LOESS)... utilizing male-only data from the typically developing group (TD).
- **Location**: materials/Bethlehem2020.pdf.md L31-L34

#### Model Origin Detail
- **Answer**: -
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Not applicable because the model is new within-study.
- **Supporting Text**: N/A
- **Location**: -

#### Modeling Method
- **Answer**: LOESS (local polynomial regression) to model CT vs age in TD males; w-scores computed as standardized deviations
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Method explicitly stated including kernel width optimization.
- **Supporting Text**: We used a local polynomial regression fitting procedure (LOESS)... kernel width ... smallest sum of squared errors using hyperparameter optimization...
- **Location**: materials/Bethlehem2020.pdf.md L31-L34

#### Software Tool
- **Answer**: R (stats::loess; optim with Brent's method)
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: R's optim (Brent's method) used for smoothing parameter search; LOESS is an R stats function.
- **Supporting Text**: ...using Brent's method as implemented in the R optim function from the stats package.
- **Location**: materials/Bethlehem2020.pdf.md L31-L34

#### Response Variable
- **Answer**: Cortical thickness (CT) averaged within 308 ROIs; sensitivity analyses with 360-region HCP parcellation
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Explicitly states 308-ROI CT analysis and additional 360 parcellation.
- **Supporting Text**: All analyses were done on CT averaged within 308 cortical regions... a parcellation of 360 regions derived from multi-modal features...
- **Location**: materials/Bethlehem2020.pdf.md L31; L139-L141

#### Predictor Variables
- **Answer**: Age (TD males only)
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Normative model regresses CT on age using TD male data to reduce sex-related heterogeneity.
- **Supporting Text**: Normative modelling ... utilizing male-only data from the typically developing group (TD)... We chose to use LOESS estimation...
- **Location**: materials/Bethlehem2020.pdf.md L31-L34; L96-L104

#### Predictor Effects
- **Answer**: Age modeled nonparametrically via LOESS; sex controlled by restricting to males; site not modeled in the normative step
- **Confidence Rating**: Medium
- **Negative Answer Category**: Not Negative
- **Reason**: LOESS implies nonparametric smooth for age; sex handled by sample restriction; no mention of site effects in normative model.
- **Supporting Text**: ...utilizing male-only data... local polynomial regression (LOESS)...
- **Location**: materials/Bethlehem2020.pdf.md L31-L34

#### NM Vldtn Handle NS
- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: QC-related nuisance (Euler, motion) handled via covariates and sensitivity; site effects acknowledged and included in LME but not explicitly handled in the normative model; no batch harmonization.
- **Supporting Text**: included both Euler index and framewise displacement as confound variables... site had a large effect on explaining variance...
- **Location**: materials/Bethlehem2020_sup1.pdf.md L50-L70; materials/Bethlehem2020.pdf.md L96-L108

#### NM Vldtn Same Domain Nonindep
- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Bootstrap assessed robustness of w-scores; sensitivity analyses iteratively excluding top motion/Euler subjects showed high spatial consistency; additional centile-based approach correlated strongly with LOESS-based ratios.
- **Supporting Text**: ...bootstrapping procedure to identify robustness of outlier detection... resulting maps were highly consistent... Both approaches showed high significant correlation ...
- **Location**: materials/Bethlehem2020_sup1.pdf.md L72-L86; L52-L54

#### NM Vldtn Same Domain Indep
- **Answer**: No
- **Confidence Rating**: Low
- **Negative Answer Category**: Missing
- **Reason**: No explicit held-out independent subset within ABIDE reported for validating the normative model (beyond bootstrapping/sensitivity).
- **Supporting Text**: -
- **Location**: -

#### NM Vldtn Diff Domain
- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: Qualitative comparison to an independent EU-AIMS LEAP study using GPR suggests consistency, but no formal cross-domain validation metrics reported.
- **Supporting Text**: The current results can be contrasted with a recent study on the EU-AIMS LEAP cohort... Despite these differences, some important consistencies emerge... overall consistency suggests that many of the inferences ... generalize.
- **Location**: materials/Bethlehem2020.pdf.md L96-L106

---

## Clinical Application & Analysis

#### Clinical Dataset
- **Answer**: ABIDE I; ABIDE II
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Clinical application focused on ASD within ABIDE datasets.
- **Supporting Text**: utilized the ABIDE datasets (ABIDE I and II)
- **Location**: materials/Bethlehem2020.pdf.md L109

#### Diseases Studied
- **Answer**: Autism Spectrum Disorder (ASD)
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Study population is ASD vs typically developing controls.
- **Supporting Text**: ...autism spectrum disorder (ASD)...
- **Location**: materials/Bethlehem2020.pdf.md L15-L21

#### Clinical Groups N
- **Answer**: ASD: 870
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Matched ASD sample size after QC.
- **Supporting Text**: ...sample consisted of 870 individuals with autism and 870 neurotypical individuals...
- **Location**: materials/Bethlehem2020_sup1.pdf.md L40-L46

#### Clinical Groups Age
- **Answer**: ASD: min 5; max 40; mean NR; sd NR
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: Age range reported; detailed mean/SD not provided in accessible text.
- **Supporting Text**: wide age range (5–40 years)
- **Location**: materials/Bethlehem2020.pdf.md L15

#### Clinical Groups Sex
- **Answer**: ASD F 116 (13.3%), M 754 (86.7%)
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Counts by sex in Table 3 (IQ row) for autism sum to 870: Male 754, Female 116.
- **Supporting Text**: Autism  Male  N 754 ... Autism  Female  N 116
- **Location**: materials/Bethlehem2020.pdf.md L120-L128

#### Deviation Metric
- **Answer**: w-score (standardized deviation from age-norm LOESS); outlier threshold |w| ≥ 2; also global w-score ratio (threshold > 0.5)
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Supplement details w-score thresholds and global ratio definition.
- **Supporting Text**: we used a cut-off score of 2 standard deviations (i.e. w >= 2 or w <= -2)... compute an individual global w-score ratio... There is a total of 14 subjects for which the ratio score exceeds 0.5
- **Location**: materials/Bethlehem2020_sup1.pdf.md L88-L106

- **Association Analysis**: Linear mixed effects for case–control (covariates: site, sex, age, in-scanner motion, Euler); Spearman correlations between regional w-scores and ADOS/SRS with FDR correction; sensitivity analyses excluding high motion/Euler subjects.
- **Key Findings Brief**: Normative modelling reveals that only a small subset of ASD individuals show highly age-atypical CT; removing these outliers reduces small case–control differences, and w-scores associate with ADOS/SRS in distinct cortical regions.
- **Key Findings Detailed**: Matched samples (ASD n=870; TD n=870; age 5–40y). Conventional LME found 27/308 ROI differences (mostly |d|<0.2); excluding regional outlier patients (|w|>2) reduced significant regions to 14 (1.9× decrease). A small subset (n=14) showed global atypicality (global ratio>0.5), largely reflecting globally thinner cortex and likely QC-related effects. Regional w-scores correlated with SRS (lateral frontal/parietal) and ADOS (lateral/inferior temporal) after FDR correction. Sensitivity analyses indicated stability against motion/Euler exclusions; centile-based deviation correlated strongly with LOESS-based ratios.
- **Key Limitations**: Cross-sectional design limits trajectory inference; substantial site/scanner heterogeneity with large site effects; normative model restricted to males; LOESS may be sensitive to sparse age bins.
- **Application Notes**: Approach supports individualized deviation mapping and subgroup stratification; code and data available via GitHub; additional analyses extended to volume, surface area, and LGI in supplements.

---

## General Notes

**General Notes**: Two parcellations used (308 ROI Desikan–Killiany sub-parcellation; 360 HCP-MMP) with highly consistent patterns. Framewise displacement from resting-state fMRI computed to index motion and included as covariate. FreeSurfer recon-all steps detailed; failed reconstructions excluded prior to matching.
