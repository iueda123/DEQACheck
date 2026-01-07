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
- **Reason**: The research objectives are clearly stated in the abstract and introduction. The study aims to: (1) apply normative modeling to parameterize cortical thickness (CT) as an individualized metric of atypicality relative to typically-developing age-related norms; (2) isolate a subgroup with highly age-atypical CT; (3) compare normative modeling utility directly against traditional case-control models; (4) describe prevalence of ASD cases showing meaningful age-related deviance in CT; (5) explore age-atypical CT-behavior associations. The PICO elements are identifiable: Population (males with autism spectrum disorder, age 5-40 years), Intervention (normative modeling applied to cortical thickness from neuroimaging), Comparator (typically-developing controls, traditional case-control approaches), Outcome (individualized whole-brain maps of age-related CT atypicality, behavioral symptomatology associations).
- **Supporting Text**: Understanding heterogeneity is an important goal on the path to precision medicine for autism spectrum disorders (ASD). We examined how cortical thickness (CT) in ASD can be parameterized as an individualized metric of atypicality relative to typically-developing (TD) age-related norms... we first compare the utility of age-related normative modelling directly against more traditional case-control models. We then describe the prevalence of ASD cases that show meaningful age-related deviance in CT (i.e. >2 standard deviations from age-related norms or outside the 95% population confidence bounds) and show how a metric of continuous variability in age-related atypicality in CT is expressed across the cortex in autism. Finally, we explore age-atypical CT-behaviour associations and assess whether such dimensional analyses associated with behaviour identify similar or different regions than typical case-control analyses.
- **Location**: Bethlehem2020.pdf.md, lines 15, 27

### 2. Clear Definition of Target Population

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The study clearly describes the age range (5-40 years) and sex (predominantly males) of participants. The study utilized ABIDE I and II datasets with N=870 per group initially. Demographic characteristics including age distribution, IQ scores, ADOS and SRS scores are provided in Tables 2 and 3. However, specific diagnostic criteria for ASD (e.g., DSM-5, DSM-IV, ICD-11) are not explicitly stated in the main text. The paper mentions that informed consent was given at each ABIDE site but refers readers to the ABIDE website for details rather than specifying diagnostic procedures. The definition of healthy controls (TD group) is implicit but not explicitly detailed regarding screening criteria. After normative modeling selection, the final sample was n=699 ASD and n=624 TD (Table 1).
- **Supporting Text**: In this study, we first sought to leverage large neuroimaging datasets to yield greater statistical power for identifying subtle effects. To achieve this, we utilized the ABIDE datasets (ABIDE I and II; 15)... Groups were subsequently matched on age using the non-parametric nearest neighbour matching procedure... After matching case and control groups and excluding scans of poorer quality (see supplementary materials) we were left with a sample size N = 870 per group (Tables 2 and 3)... Given the reduced sample size in the female group and the known interaction between autism and biological sex, as well as the known sex differences in developmental trajectories, we conducted normative modelling on the male group only.
- **Location**: Bethlehem2020.pdf.md, lines 109, 155; Tables 1-3 at lines 57-136

### 3. Clarity of Inclusion and Exclusion Criteria

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The study describes several exclusion criteria: (1) exclusion of subjects with poorer quality scans based on Euler index (top 10% with Euler index ~300 or higher were excluded); (2) exclusion of subjects with IQ<70 (mentioned in discussion); (3) exclusion of age bins with fewer than 5 data-points in TD group; (4) exclusion of individuals without resting-state fMRI data (for motion assessment). Quality control procedures using Euler index are detailed in Supplementary Figure 2. However, the criteria are not comprehensively pre-specified as a formal list. A flow chart showing subject numbers at each exclusion stage is not provided. The specific reasons for individual exclusions and exact numbers at each stage are not clearly itemized. The matching procedure (MatchIt package) is mentioned but full details of inclusion criteria beyond age-matching and IQ-matching are not explicitly stated.
- **Supporting Text**: After matching case and control groups and excluding scans of poorer quality (see supplementary materials) we were left with a sample size N = 870 per group... Age bins that contained fewer than five data-points in the TD group were excluded from subsequent analysis as the standard deviations for these bins would essentially be zero (and thus the w-score could not be computed). With the inclusion of motion we also excluded individuals for which no resting-state fMRI was available... We excluded all subjects with a Euler score of 300 or higher in either hemisphere... While higher IQ does not automatically imply higher overall functioning it does limit the generalisability of our findings to individuals with normal to high IQ.
- **Location**: Bethlehem2020.pdf.md, lines 49, 109; Bethlehem2020_sup1.pdf.md, lines 40; Bethlehem2020.pdf.md, lines 101

### 4. Validity of Normative Modeling Outcome Measures

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The outcome measures are clearly defined. The brain measure used is cortical thickness (CT), extracted using FreeSurfer v5.3.0 for 308 cortical regions. The method for quantifying individual-level deviations is detailed: w-scores (analogous to z-scores) are computed using LOESS regression to estimate age-specific mean and standard deviation from the TD normative sample, then calculating w = (CT_individual - mean_TD) / SD_TD for each region. The normative reference population is clearly specified as typically-developing males from ABIDE I and II, binned into one-year age bins. Interpretation of deviation scores is clear: w-scores >2 SD indicate significant atypicality, representing ~7.6% prevalence. Reliability of w-scores was assessed through bootstrapping (1000 iterations) showing robust measures. The study also compared LOESS with centile modeling approaches showing high correlation (r=0.87 for ABIDE I, r=0.66 for ABIDE II).
- **Supporting Text**: Normative modelling of age-related CT effects was done utilizing male-only data from the typically developing group (TD)... All analyses were done on CT averaged within 308 cortical regions. We used a local polynomial regression fitting procedure (LOESS), where the local width or smoothing kernel of the regression was determined by the model that provided the overall smallest sum of squared errors using hyperparameter optimization across 5-100% of the full age range using Brent's method... The w-score for an individual thus reflects how far away their CT is from TD norms in units of standard deviation... We operationalized 'significant' atypicality in statistical terms as w-scores >2 SD away from TD norms.
- **Location**: Bethlehem2020.pdf.md, lines 30-31, 45-49, 71

### 5. Handling of Confounding Variables

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The study comprehensively addressed confounding variables. Age and sex were primary considerations: age was handled through normative modeling approach itself (age-specific bins), and analyses were conducted separately by sex (males only due to sample size limitations in females). For the multi-site nature of ABIDE data, scanner site was included as a random effect in linear mixed effects models. The study also controlled for image quality using Euler index and in-scanner head motion using mean framewise displacement, both included as confound regressors. Variance partitioning analysis (Figure 5) showed that scanner site and age each explained ~15% of total variance. Groups were matched on age and IQ using MatchIt package. Sensitivity analyses systematically excluded subjects with high motion or poor Euler indices to assess their impact, showing robust results (Supplementary Figure 3).
- **Supporting Text**: Our first analysis examined conventional case-control differences using linear mixed effect modelling including site, sex, age, in-scanner head motion and Euler index as covariates... Perhaps unsurprisingly, scanner site and age proved to be the most dominant sources of variance (each explaining on average around 15% of the total variance)... All models also included Euler indices and mean framewise displacement as confound regressors... To further ensure adequate control for scan quality we included the index itself as a confound variable in all models... To address this issue in the present analysis we included mean framewise displacement in our models.
- **Location**: Bethlehem2020.pdf.md, lines 51, 143-149, 137, 101

### 6. Clarity of Data Sources

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Data sources are clearly specified. The study utilized the Autism Brain Imaging Data Exchange (ABIDE) datasets, specifically ABIDE I and ABIDE II, which are well-known publicly available multi-site neuroimaging databases. The study references the specific ABIDE website (http://fcon_1000.projects.nitrc.org/indi/abide/) where original unprocessed data is available. Supplementary Figure S1 shows site distribution across the different scanning sites included in ABIDE. The paper notes that ABIDE represents a post-hoc collection of sites with heterogeneous scanners, imaging acquisition sequences and parameters, and sample ascertainment. The study also mentions that informed consent was given at each site included in ABIDE studies. All extracted data and relevant phenotypic measures are made openly available on GitHub.
- **Supporting Text**: To achieve this, we utilized the ABIDE datasets (ABIDE I and II; 15) (see Supplementary Fig. 1). Informed consent was given at each site included in the ABIDE studies, see the website for more details: http://fcon_1000.projects.nitrc.org/indi/abide/... Original unprocessed neuroimaging data is openly available through the ABIDE consortium: http://fcon_1000.projects.nitrc.org/indi/abide/abide_I.html... the dataset also presents a post-hoc collection of sites accumulated through the ABIDE initiative, whereby scanners, imaging acquisition sequences and parameters, sample ascertainment, etc., are highly heterogeneous.
- **Location**: Bethlehem2020.pdf.md, lines 109, 171, 100

### 7. Description of Image Acquisition Protocol

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The study specifies that MPRAGE (T1-weighted) images were used for each participant. However, detailed imaging parameters (TR, TE, flip angle, resolution) are not provided in the main text or supplementary materials. The paper acknowledges that ABIDE is a multi-site dataset with heterogeneous scanners, imaging acquisition sequences and parameters, but does not provide site-specific protocol details. Scanner specifications (manufacturer, field strength) are not detailed. The study notes that variability due to scanner site was a major source of variance (~15%) and was controlled for statistically, but the actual acquisition protocols are not enumerated. This is somewhat expected for a large multi-site retrospective study using ABIDE data, where protocols would vary by site, but specific parameters could have been provided in supplementary tables or referenced to ABIDE documentation.
- **Supporting Text**: Cortical surface reconstruction was performed using the MPRAGE (T1) image of each participant with FreeSurfer... the dataset also presents a post-hoc collection of sites accumulated through the ABIDE initiative, whereby scanners, imaging acquisition sequences and parameters, sample ascertainment, etc., are highly heterogeneous. As a result, we observed that site had a large effect on explaining variance in CT and this is compatible with observations made by other studies.
- **Location**: Bethlehem2020.pdf.md, lines 111, 100

---

## Assessment Items - Group B

### 1. Details of Data Preprocessing

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Preprocessing procedures are clearly described. The study used FreeSurfer version 5.3.0 for cortical surface reconstruction to ensure comparability with previous ABIDE publications. The 'recon-all' pipeline steps are detailed: (1) intensity normalization; (2) registration to Talairach space; (3) skull stripping using non-uniformity intensity correction algorithms; (4) white matter segmentation; (5) tessellation of the WM boundary; (6) automatic correction of topological defects; (7) deformation to generate pial surface; (8) correction for geometrical and topological abnormalities. CT was defined as shortest distance between GM/WM boundary and pial surface vertices. Quality control using Euler index is described, with subjects having Euler index ≥300 excluded. The study chose not to conduct manual segmentations, excluding failed subjects instead. Two parcellation schemes were used: 308-region (~500mm² each) and 360-region HCP-based parcellation.
- **Supporting Text**: Cortical surface reconstruction was performed using the MPRAGE (T1) image of each participant with FreeSurfer (http://surfer.nmr.mgh.harvard.edu/) version (v5.3.0, to ensure comparability with previous ABIDE publications). The reconstruction pipeline performed by FreeSurfer 'recon-all' involved intensity normalization, registration to Talairach space, skull stripping, WM segmentation, tessellation of the WM boundary, and automatic correction of topological defects. Briefly, non-uniformity intensity correction algorithms were applied before skull stripping, resulting in resampled isotropic images of 1 mm. An initial segmentation of the white matter tissue was performed to generate a tessellated representation of the WM/GM boundary. The resulting surface was deformed outwards to the volume that maximizes the intensity contrast between GM and cerebrospinal fluid, generating the pial surface. Resulting surfaces were constrained to a spherical topology and corrected for geometrical and topological abnormalities. CT of each vertex was defined as the shortest distance between vertices of the GM/WM boundary and the pial surface.
- **Location**: Bethlehem2020.pdf.md, lines 111-112

### 2. Clarity of Data Partitioning Methods

- **Answer**: No
- **Confidence Rating**: High
- **Negative Answer Category**: Missing
- **Reason**: The study does not describe data partitioning into training, validation, and test sets. The normative model was built using all typically-developing (TD) males and then applied to all ASD males without apparent hold-out validation sets. While the study did implement bootstrapping for reliability assessment (1000 bootstraps with replacement) and compared LOESS with centile modeling approaches, there is no mention of: (1) splitting data into train/validation/test sets; (2) cross-validation procedures such as k-fold CV; (3) measures to prevent data leakage; (4) separate cohorts for model development versus evaluation. The normative modeling approach used all available TD data to establish norms and then applied these to the ASD group, which is appropriate for the study design but does not constitute formal train/test partitioning. This may be acceptable for normative modeling where the goal is to maximize the normative reference sample, but it limits assessment of generalization.
- **Supporting Text**: No explicit statements about data partitioning were found. The methods describe using TD males to build normative models and applying these to ASD males, but without formal train/test splits.
- **Location**: Not applicable - information is missing

### 3. Details of Normative Modeling Approach

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The normative modeling approach is described in detail. The statistical model type is clearly specified: LOESS (Locally Estimated Scatterplot Smoothing, also called Local Polynomial Regression). Hyperparameter optimization is described: the local width/smoothing kernel was determined by finding the model with smallest sum of squared errors across 5-100% of the full age range using Brent's method as implemented in R's optim function. The study computed age-specific mean and standard deviation for each brain region from TD males, binned into one-year age bins. W-scores were calculated as (individual CT - normative mean) / normative SD. The approach was validated through: (1) extensive bootstrapping (1000 iterations); (2) comparison with centile scoring showing high consistency; (3) sensitivity analyses. The study also notes that LOESS was chosen for computational efficiency and interpretability. Alternative approaches are discussed in context of prior literature (Marquand et al. referenced).
- **Supporting Text**: We used a local polynomial regression fitting procedure (LOESS), where the local width or smoothing kernel of the regression was determined by the model that provided the overall smallest sum of squared errors using hyperparameter optimization across 5-100% of the full age range using Brent's method as implemented in the R optim function from the stats package. We also assessed consistency of our output using centiles scoring and consistency of the normative model using extensive bootstrapping and sensitivity analyses... there are a number of different approaches to normative modelling that all have pros and cons (see ref. 45 for an excellent review). We chose to use LOESS estimation as it is computationally efficient and the resulting w-scores are easily interpretable.
- **Location**: Bethlehem2020.pdf.md, lines 30-31, 100

### 4. Details of Training Algorithm

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: Some details of the model fitting are provided but not comprehensive. The study specifies that hyperparameter optimization used Brent's method for finding the optimal smoothing kernel width by minimizing sum of squared errors across 5-100% of the age range. However, typical machine learning training algorithm details are not fully applicable here since LOESS is a non-parametric local regression method rather than a parametric model requiring iterative optimization. The study does not explicitly describe: (1) specific optimization algorithms (e.g., gradient descent is not applicable for LOESS); (2) convergence criteria; (3) number of iterations/epochs; (4) regularization techniques. For LOESS, the 'training' is essentially local weighted regression at each point, and the main hyperparameter is the bandwidth/smoothing parameter, which was optimized using Brent's method. The bootstrapping procedure (1000 iterations) is described for validation but this is separate from the model fitting itself.
- **Supporting Text**: We used a local polynomial regression fitting procedure (LOESS), where the local width or smoothing kernel of the regression was determined by the model that provided the overall smallest sum of squared errors using hyperparameter optimization across 5-100% of the full age range using Brent's method as implemented in the R optim function from the stats package.
- **Location**: Bethlehem2020.pdf.md, lines 30-31

### 5. Model Performance Evaluation Metrics

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The study does not explicitly report traditional normative modeling performance metrics such as MSLL (Mean Standardized Log Loss), explained variance, or Pearson/Spearman correlations between predicted and observed values for the normative model itself. Instead, the study focuses on: (1) reliability of w-scores through bootstrapping, showing median of 1 unreliable region per subject out of 308; (2) consistency between LOESS and centile approaches (r=0.87 for ABIDE I, r=0.66 for ABIDE II); (3) variance partitioning showing age and site each explain ~15% of variance; (4) spatial consistency of effect maps under systematic exclusions (correlations >0.7). For brain-behavior analyses, Spearman correlations with FDR correction (q<0.05) are reported for ADOS, SRS and other phenotypic measures. While these provide evidence of model validity, conventional normative modeling performance metrics (MSLL, explained variance, skewness, kurtosis of z-scores) are not explicitly reported.
- **Supporting Text**: To assess the reliability of the normative w-score we permuted the normative sample (1000 bootstraps, with replacement) and computed 1000 permuted w-scores for each individual and each brain region... The median number of brain regions per subject with a significant p-value in the normative sample was 1 (out of 308), indicating that the normative sample is topologically robust and that the w-score is a robust reflection of atypicality... Both approaches showed high significant correlation in determining whole-brain w-score ratios (r=0.87, p=4e-119 and r=0.66, p=5.7e-39 for ABIDE I and ABIDE II respectively).
- **Location**: Bethlehem2020.pdf.md, lines 151; Bethlehem2020_sup1.pdf.md, lines 78

### 6. Implementation of Internal Validation

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The study implemented some forms of internal validation but not in the traditional sense of hold-out validation sets. Internal validation approaches include: (1) extensive bootstrapping with 1000 iterations with replacement to assess w-score reliability; (2) comparison of LOESS with alternative centile modeling approach showing consistency; (3) sensitivity analyses systematically excluding subjects with high motion (top 5-25%) or poor Euler indices (top 5-25%) and assessing spatial correlation of resulting effect maps (r>0.7); (4) one-sample tests in the normative TD group showing no regions with w-scores significantly different from zero. However, the study did not use: (1) cross-validation procedures (e.g., k-fold CV); (2) separate validation cohorts independent of training data; (3) formal assessment of overfitting through learning curves or similar. The bootstrapping approach provides evidence of stability but is not the same as validation on held-out data.
- **Supporting Text**: To assess the reliability of the normative w-score we permuted the normative sample (1000 bootstraps, with replacement) and computed 1000 permuted w-scores for each individual and each brain region... To further assess the distribution in the normative group we also conducted one-sample linear mixed effects modelling in the normative group only to determine if any of all brain regions would show outlier consistency. There were no brain regions for which the w-score showed a deviation significant from zero in the normative group... we conducted a cross-validation analysis by systematically excluding the top 5% of motion subject and top 5% of Euler subjects and assessed the spatial correlation in resulting Cohen's D maps. Resulting maps were highly consistent, with the lowest correlation (r = 0.7).
- **Location**: Bethlehem2020.pdf.md, lines 151; Bethlehem2020_sup1.pdf.md, lines 52

### 7. External Data Validation

- **Answer**: No
- **Confidence Rating**: High
- **Negative Answer Category**: Missing
- **Reason**: The study did not conduct validation on independent external datasets. All analyses were conducted using ABIDE I and ABIDE II data. The normative model was built using TD males from ABIDE and applied to ASD males from the same ABIDE datasets. The discussion section mentions comparison with a study by Zabihi et al. using the independent EU-AIMS LEAP cohort, noting some consistency in spatial topology and brain-behavior relationships despite methodological differences (LOESS vs. Gaussian process regression, sample sizes, sex inclusion). However, the authors did not themselves apply their normative model to the EU-AIMS LEAP or any other external dataset for validation. The lack of external validation limits assessment of generalizability to other populations, scanners, or acquisition protocols beyond ABIDE.
- **Supporting Text**: The current results can be contrasted with a recent study on the EU-AIMS LEAP cohort. This study differs from the current work in being based on a completely independent dataset (EU-AIMS LEAP vs. ABIDE)... Despite these differences, some important consistencies emerge. In particular, our map of prevalence of the CT outlier group (Fig. 3) is somewhat consistent with the spatial topology Zabihi and colleagues report for negative deviations from the normative model.
- **Location**: Bethlehem2020.pdf.md, lines 99 (discussion section mentioning external study but not conducting external validation)

### 8. Description of Dataset Characteristics

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: Dataset characteristics are comprehensively described. Sample sizes are clearly specified: initially N=870 per group after matching, final sample after normative modeling selection was n=699 ASD and n=624 TD males (Table 1). Demographic characteristics are detailed in Tables 2 and 3: age distribution (mean, SD, median, min, max) separated by diagnosis and sex; full sample age range 5.13-64 years. Clinical characteristics include: IQ (mean ~106 for ASD, ~111 for TD); ADOS total scores (available for n=505 ASD males, mean 11.15±3.86); SRS scores (available for n=421 ASD males, mean 80.42±21.41); other measures (SCQ, AQ, FIQ) mentioned. Sex distribution is provided (754 male/116 female ASD; 660 male/210 female TD before sex-specific analysis). Site distribution shown in Supplementary Figure S1. Quality metrics (Euler index, framewise displacement) are compared between groups. Missing data handling is implicit through exclusion criteria (e.g., excluding subjects without resting-state fMRI for motion assessment).
- **Supporting Text**: Tables shown in lines 57-136 provide comprehensive demographic and clinical characteristics. Table 1 shows final sample (ASD n=699, TD n=624, age mean 14.93±5.97 vs 15.35±6.37). Table 2 shows sex-stratified age characteristics. Table 3 shows IQ, ADOS, SRS characteristics with sample sizes for each measure.
- **Location**: Bethlehem2020.pdf.md, Tables 1-3 at lines 49, 113-136; Supplementary Figure S1

### 9. Performance Metrics and Statistical Uncertainty

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The study reports several statistical metrics but with variable completeness regarding uncertainty measures. Point estimates reported include: (1) Cohen's d for case-control comparisons (mostly <0.2); (2) Spearman correlations for brain-behavior associations; (3) prevalence of outliers (median 7.6% across regions); (4) chi-square test for outlier prevalence (χ²=3.85, p=0.049 with Yates correction). P-values are reported for case-control analyses (FDR q<0.05) and brain-behavior correlations (FDR q<0.05 across 1848 tests). Statistical power analysis is described: minimum detectable effect d=0.1752 with 80% power at α=0.005 for N=870 per group. However, confidence intervals or Bayesian credible intervals are generally not reported for most estimates. The bootstrapping approach provides distribution-based reliability assessment but not formal CIs. Bayes Factors are reported in Supplementary Figure 3 for some sensitivity analyses but not systematically throughout.
- **Supporting Text**: Of these regions, most are of small effect size, with 26 of the detected 27 regions showing an effect <0.2 standard deviations of difference... Over all brain regions the median prevalence of these patients is around 7.6%. Meaning that in each brain region there are ~7.6% of individuals that would be considered an outlier. This difference from an expected proportion of 5% in the present sample corresponds to a X2 of 3.85 (with Yates continuity correction) that is significant at p = 0.049... we conducted an a priori statistical power analysis indicating that a minimum case-control effect size of d = 0.1752 could be detected at this sample size with 80% power at a conservative alpha set to 0.005.
- **Location**: Bethlehem2020.pdf.md, lines 51, 71, 141

### 10. Consideration for Reproducibility

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The study demonstrates strong commitment to reproducibility. Software versions are specified: FreeSurfer v5.3.0, R packages (MatchIt, stats, variancePartition). Code availability is clearly stated: all code is openly available on GitHub with specific repository reference (Zenodo DOI: 10.5281/ZENODO.1325171), including Cohen's d computation code (https://github.com/mvlombardo/utils/blob/master/cohens_d.R) and centiles cross-validation code (https://github.com/deep-introspection/PyNM). Data availability is described: all extracted measures, phenotypic data and quality control measures are openly available on GitHub; original unprocessed neuroimaging data is available through ABIDE consortium (http://fcon_1000.projects.nitrc.org/indi/abide/abide_I.html). The study provides detailed methodological descriptions enabling replication. Specific R functions and methods are referenced (e.g., Brent's method via optim function). The paper does not mention sharing of trained models themselves, which is a limitation.
- **Supporting Text**: All data is openly available on GitHub, this includes all measures extracted from the raw imaging data alongside the relevant phenotypic and quality control measures. Original unprocessed neuroimaging data is openly available through the ABIDE consortium: http://fcon_1000.projects.nitrc.org/indi/abide/abide_I.html. All code is openly available on GitHub, Cohen's d were computed using: https://github.com/mvlombardo/utils/blob/master/cohens_d.R and the centiles cross-validation code can be found in https://github.com/deep-introspection/PyNM. Cortical surface reconstruction was performed using the MPRAGE (T1) image of each participant with FreeSurfer (http://surfer.nmr.mgh.harvard.edu/) version (v5.3.0, to ensure comparability with previous ABIDE publications).
- **Location**: Bethlehem2020.pdf.md, lines 170-176, 111

### 11. Interpretation Specific to Normative Modeling

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The study provides excellent interpretation specific to normative modeling. Individual-level deviation scores (w-scores) are clearly explained as units of standard deviation from age-specific TD norms, allowing individualized assessment of atypicality. Clinical thresholds are discussed: w-scores >2 SD (outside 95% confidence bounds) defined as significant atypicality, identifying ~7.6% of ASD individuals per region. The study extensively compares normative modeling with traditional case-control approaches, showing that: (1) small case-control effects are driven by outlier subgroups identified by normative modeling; (2) removing outliers reduces significant regions from 27 to 14, demonstrating case-control susceptibility to outliers; (3) normative w-score analyses reveal different spatial patterns than case-control. Advantages are discussed: enables individualized precision medicine approaches, identifies heterogeneity, moves beyond diagnostic categories toward dimensional approaches. Clinical application prospects are realistically discussed including limitations (cross-sectional data, limited age range, need for longitudinal validation, phenotypic characterization of outlier subgroup).
- **Supporting Text**: The w-score for an individual thus reflects how far away their CT is from TD norms in units of standard deviation... In contrast to a canonical case-control model, we computed normative models of age which resulted in individualized w-scores that indicate how atypical CT is for an individual compared to typical norms for that age... removal of outlier patients now revealed only 14 significant regions instead of 27 regions with small case-control differences - a 1.9-fold decrease in the number of regions detected... Normative models may provide an alternative to case-control models that test hypotheses at a group-level, by allowing additional insight to be made at more individualized levels, and thus help further progress towards personalized medicine for ASD... it moves us conceptually closer to making precise dimensional inferences rather than purely relying on diagnostic categories.
- **Location**: Bethlehem2020.pdf.md, lines 45, 53-54, 51, 105

---

## Additional Comments

**Additional Comments**: This is a well-designed and rigorously conducted study applying normative modeling to autism neuroimaging data. Key strengths include: (1) large sample size (n=699 ASD, n=624 TD after QC) from multi-site ABIDE datasets providing statistical power; (2) comprehensive quality control using Euler index and motion assessment; (3) robust validation through bootstrapping (1000 iterations) and comparison with alternative centile approaches; (4) extensive sensitivity analyses addressing confounds; (5) excellent reproducibility through open data and code sharing; (6) clear demonstration of normative modeling advantages over traditional case-control approaches; (7) identification of a small subgroup (~7.6% per region) with age-atypical cortical thickness potentially relevant for precision medicine. Limitations include: (1) lack of external validation on independent datasets (though consistency with EU-AIMS LEAP study is discussed); (2) cross-sectional design limiting trajectory inferences; (3) absence of formal train/test data partitioning; (4) incomplete reporting of traditional normative modeling performance metrics (MSLL, explained variance); (5) limited imaging acquisition protocol details (expected for multi-site retrospective study but could reference ABIDE documentation); (6) restriction to males and higher IQ (IQ≥70) limiting generalizability; (7) phenotypic characterization of outlier subgroup limited by data availability. The study represents an important methodological contribution demonstrating how normative modeling can reveal heterogeneity masked in traditional case-control analyses, identifying clinically meaningful subgroups for targeted investigation. The focus on LOESS for computational efficiency is pragmatic though comparison with more sophisticated approaches (Gaussian process regression as in Marquand et al.) could strengthen future work. Overall, this paper meets most quality criteria for normative modeling studies and provides a strong foundation for precision medicine approaches in autism research.
