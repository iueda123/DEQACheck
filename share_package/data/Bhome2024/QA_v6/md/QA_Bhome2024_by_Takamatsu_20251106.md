# Quality Assessment Form

## Study Identification

- **Study ID**:Bhome2024
- **Reference File Names**:
- **Author, Journal, Year**:
- **Title**:
- **DOI**:
---


## Assessment Items - Group A


### 1. Clarity of Research Objectives
- **Answer**:yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:
- Specific application purposes of normative modeling (diagnostic support, personalized medicine, disease understanding, etc.) are clearly stated
- PICO elements (population, intervention/exposure, comparator, outcome) can be identified
- Research hypotheses or research questions are specifically stated

- **Supporting Text**:
We aimed to quantify patterns of
neurodegenerative dissimilarity in participants with PD and DLB and evaluate the potential clinical relevance of
total outlier count by testing its association with key clinical measures in PD and DLB.
- **Location**:




### 2. Clear Definition of Target Population
- **Answer**:yes (厳密に言えばpartial)
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:
- Diagnostic criteria for psychiatric/neurological disorders (DSM-5, ICD-11, etc.) are specified
 =>yes
- Definition of healthy control groups is clear (when applicable)
 =>N/A
- Demographic characteristics such as age and sex are described
=>yes
- **Supporting Text**:
participants with PD, 36
with DLB and 38 controls, from the Vision in Parkinson’s disease study
(PI: Dr Weil, Queen Square Ethics Committee reference 15/LO/00476).
The second site was the pseudoanonymised Alzheimer’s Disease
Research Center (ADRC) “8361” which contributes data to the National
Alzheimer’s Coordinating Center (NACC) database (Beekly et al., 2007),
and included 25 participants with DLB and 127 controls. Participants
from the UCL site were recruited from the National Hospital for
Neurology and Neurosurgery outpatient clinics and affiliated hospitals,
or from national patient support groups (Lewy Body Society and Rare
Dementia Support). They were diagnosed as having PD or probable DLB
if they satisfied Queen Square Brain Bank PD diagnostic criteria (Daniel
and Lees, 1993) and the Dementia with Lewy Bodies Consortium Criteria
(McKeith et al., 2017) respectively. Exclusions were a history of traumatic
brain injury, or major co-morbid psychiatric or confounding
neurological disorders; and for participants with PD, presence of dementia
was also an exclusion criterion, defined using Movement Disorder
Society criteria
- **Location**:本文＋SI



### 3. Clarity of Inclusion and Exclusion Criteria
- **Answer**:yes (厳しく言えばpartial)
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:
- Clear inclusion criteria are described
=>yes
- Clear exclusion criteria are described
=>yes (臨床診断としての除外基準あり)
- These criteria were uniformly applied to all participants
=>不明。UCLvs NACCでは違うかも
- Normative modeling-specific requirements (image quality, data completeness, etc.) are considered
=>ok
- Subject numbers at each stage are clearly described (flow chart recommended)
=>no flow chart, but acceptable
- Exclusion reasons are specifically described
=>臨床群としての除外か画像的な除外かにもよる。前者の基準は明示している。QCも目視では報告している。
- Number of subjects included in final analysis is specified
=>ok
- **Supporting Text**:
tient support groups (Lewy Body Society and Rare
Dementia Support). They were diagnosed as having PD or probable DLB
if they satisfied Queen Square Brain Bank PD diagnostic criteria (Daniel
and Lees, 1993) and the Dementia with Lewy Bodies Consortium Criteria
(McKeith et al., 2017) respectively. Exclusions were a history of traumatic
brain injury, or major co-morbid psychiatric or confounding
neurological disorders; and for participants with PD, presence of dementia
was also an exclusion criterion, defined using Movement Disorder
Society criteria
Controls were recruited from spouses of patients and UCL volunteer
databases. Inclusion criteria were being aged 50–80 and exclusions were
the presence of past neurological or psychiatric history, or cognitive
impairment on history or neuropsychological testing
Processed images were quality controlled by
visually inspecting grey and white matter boundaries, and subcortical
segmentation boundaries superimposed on the corresponding structural
T1-weighted image by a researcher blind to clinical status.
- **Location**:




### 4. Validity of Normative Modeling Outcome Measures
- **Answer**:yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:
- Brain measures used (cortical thickness, cortical area, subcortical volumes, connectivity measures, etc.) are clearly defined
=>ok
- Methods for quantifying individual-level deviations through normative modeling are detailed
=>ok
- Characteristics of the normative reference population are specified
=>ok
- Interpretation methods for deviation scores are clear
=>ok
- **Supporting Text**:
The reference normative model was recalibrated to the study datasets
with an adapted transfer learning approach (Kia et al., 2022). This
involved inputting control data from our two study sites into the reference
normative model to generate stable parameters for cortical thicknesses
and subcortical volumes, to account for residual differences in
data distributions, caused by factors such as scanner differences.
We generated z-scores from T1w-MRI scans for each participant relative to normative regional cortical
thickness and subcortical volumes, modelled in a reference cohort (n = 58,836). Outliers (z < 􀀀 1.96) were
aggregated across 169 brain regions per participant.
2.6.1. Total outlier count
From the z-scores for each cortical and subcortical region generated
from the normative modelling pipeline described above, outliers were
defined as z-scores < -1.96. This is a commonly used threshold representing
95 % confidence that points below it differ from the mean
(Fisher, 1925). This is equivalent to the p = 0.05 threshold for significance
in frequentist statistical models, and since we are interested in
atrophy, only consider lower values (i.e., the bottom 2.5 % of the population
distribution) for a given neuroimaging metric. However, to
ensure our findings were not driven by a particular threshold, we
repeated the analysis using a more liberal outlier threshold < -1.282, to
test whether this affected our findings
- **Location**:




### 5. Handling of Confounding Variables
- **Answer**:yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:
- Basic confounding variables such as age and sex are considered
=>yes
- For multi-site studies, methods for handling site effects are described
=>yes
- Treatment of covariates in normative modeling is clear
=>ok
- When harmonization methods are used, their details are described
=>ok

- **Supporting Text**:
2.6.3. Associations between total outlier count and clinical features
Linear regressions adjusting for age and sex were used to test associations
between total outlier count and composite cognitive score,
MoCA and visuo-perception, measured using the Hooper Visual Organisation
Test. In exploratory analyses, we tested associations with
disease-specific measures including global measure of severity (MDSUPDRS),
motor severity (MDS-UPDRS-III), hallucination severity (UMPDHQ)
and depression score (HADS). Associations were tested in PD and
DLB groups separately. For the DLB group we only included data from
UCL where clinical severity data had been comprehensively collected.
Statistical analyses were performed in R (v4.2.2).
Rutherford and colleagues (Rutherford et al., 2022) modelled
normative lifespan curves for cortical thicknesses across 148 regions
(Destrieux parcellation) and subcortical volumes derived from Freesurfer
using a warped Bayesian Linear Regression with age and sex as
covariates, and accounting for site differences (Bayer et al., 2022).
2.5. Applying neuroimaging normative modelling to study data
The reference normative model was recalibrated to the study datasets
with an adapted transfer learning approach (Kia et al., 2022). This
involved inputting control data from our two study sites into the reference
normative model to generate stable parameters for cortical thicknesses
and subcortical volumes, to account
- **Location**:




### 6. Clarity of Data Sources
- **Answer**:yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:
- Dataset names or study names used are specified
=>ok
- Time and location of data acquisition are described
=>yes
- For open datasets, specific database names (e.g., Human Connectome Project, ABCD Study, etc.) are specified
=>yes
- **Supporting Text**:
Structural T1w-MRI data from two sites were used. The first site at
University College London (UCL), included 108 participants with PD, 36
with DLB and 38 controls, from the Vision in Parkinson’s disease study
(PI: Dr Weil, Queen Square Ethics Committee reference 15/LO/00476).
The second site was the pseudoanonymised Alzheimer’s Disease
Research Center (ADRC) “8361” which contribut...
- **Location**:




### 7. Description of Image Acquisition Protocol
- **Answer**:yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:
- Details of MRI sequences used (T1-weighted, DTI, fMRI, etc.) are described
=>yes
- Imaging parameters (TR, TE, flip angle, resolution, etc.) are specified
=>yes
- Scanner specifications (manufacturer, field strength, etc.) are described
=>yes
- **Supporting Text**:
2.3. MRI acquisition and processing
Structural T1w-MRI scans at UCL were acquired on a 3 T Siemens
Magnetom Prisma scanner with a 64-channel head coil. Structural
magnetisation prepared rapid acquisition gradient echo (MPRAGE) data
were acquired using the following parameters: 1 × 1 × 1 mm voxel, TE
= 3.34 ms, TR = 2530 ms, flip angle = 7◦, acquisition time = 9 min.
Structural T1w-MRI scans from NACC ADRC “8361” were acquired on
1.5 T GE scanners (further information on scanning parameters are
available via the NACC database).
- **Location**:



---
## Assessment Items - Group B


### 1. Details of Data Preprocessing
- **Answer**:yes
- **Confidence Rating**:high
- **Negative Answer Category**:
- **Reason**:
- Preprocessing software used (FreeSurfer, FSL, SPM, etc.) is specified
=>yes
- Each preprocessing step (skull stripping, normalization, segmentation, etc.) is detailed
=>ok (not all step but partially detailed)
- Quality control procedures are described
=> ok
- **Supporting Text**:
2.3. MRI acquisition and processing
Structural T1w-MRI scans at UCL were acquired on a 3 T Siemens
Magnetom Prisma scanner with a 64-channel head coil. Structural
magnetisation prepared rapid acquisition gradient echo (MPRAGE) data
were acquired using the following parameters: 1 × 1 × 1 mm voxel, TE
= 3.34 ms, TR = 2530 ms, flip angle = 7◦, acquisition time = 9 min.
Structural T1w-MRI scans from NACC ADRC “8361” were acquired on
1.5 T GE scanners (further information on scanning parameters are
available via the NACC database).
The “recon-all” function in FreeSurfer v6.0.0 (http://www.frees
urfer.net) was used to process all UCL and NACC MRI data. Cortical
thickness values (Destrieux parcellation; lh.aparc.a2009s.stats, rh.
aparc.a2009s.stats) (Destrieux et al., 2010) and subcortical volumes
(aseg.stats) were extracted. Processed images were quality controlled by
visually inspecting grey and white matter boundaries, and subcortical
segmentation boundaries superimposed on the corresponding structural
T1-weighted image by a researcher blind to clinical status. Particular
attention was paid to atrophied scans which can sometimes affect robust
segmentation of brain structures.
- **Location**:



### 2. Clarity of Data Partitioning Methods
- **Answer**: partial
- **Confidence Rating**: medium
- **Negative Answer Category**: -
- **Reason**:
- Data partition ratios (e.g., 70:15:15) are specified
=>not reported but referes to Pre-trained model of Rutherford 2022 which uses spilt-half
- Partitioning methods (random, stratified sampling, etc.) are described
=>no
- When cross-validation is used, its details (K-fold, etc.) are described
=>no
- Measures to prevent data leakage are confirmed
=>not sure.
- **Supporting Text**:
- **Location**:



### 3. Details of Normative Modeling Approach
- **Answer**:partial
- **Confidence Rating**:medium
- **Negative Answer Category**:
- **Reason**:
- Types of statistical models (Gaussian process regression, Bayesian models, linear regression, etc.) are specified
=>yes
- Model hyperparameters and settings are described
=>no
- Software or tools used (PCNtoolkit, normative-modeling, etc.) are specified
=>yes
- **Supporting Text**:
Rutherford and colleagues (Rutherford et al., 2022) modelled
normative lifespan curves for cortical thicknesses across 148 regions
(Destrieux parcellation) and subcortical volumes derived from Freesurfer
using a warped Bayesian Linear Regression with age and sex as
covariates, and accounting for site differences (Bayer et al., 2022).
Bayesian linear regression with likelihood warping allows accurate
modelling of non-Gaussian effects and upscaling of normative models to
large cohorts (Fraza et al., 2021). Their reference cohort comprised
58,836 participants from 82 sites.
2.5. Applying neuroimaging normative modelling to study data
The reference normative model was recalibrated to the study datasets
with an adapted transfer learning approach (Kia et al., 2022). This
involved inputting control data from our two study sites into the reference
normative model to generate stable parameters for cortical thicknesses
and subcortical volumes, to account for residual differences in
data distributions, caused by factors such as scanner differences. Zscores
were then generated for each individual with DLB or PD, per
region, relative to the recalibrated reference values. All modelling steps
were performed using PCNToolkit (v0.20) (Rutherford et al., 2022).
- **Location**:





### 4. Details of Training Algorithm
- **Answer**: N/A 
=> pre-existing modelを活用している場合は、この項目はN/A評価になるだろうか。どうだろう。
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:
- Optimization algorithms (gradient descent, ADAM, L-BFGS, etc.) are specified
- Hyperparameter setting methods (grid search, Bayesian optimization, etc.) are described
- Convergence criteria and training termination conditions (number of epochs, loss function thresholds, etc.) are specified
- Use of regularization techniques (L1/L2 regularization, etc.) is described
- **Supporting Text**:
- **Location**:




### 5. Model Performance Evaluation Metrics
- **Answer**:yes 扱いでよさそう（基準・ルールの変更が必要だが）
=>そもそもperformance metricsが該当せず、z-scoreやoutlierの記述と取り扱いがあればよしとするか。
- **Confidence Rating**:medium
- **Negative Answer Category**:
- **Reason**:
- Performance metrics used (MSLL, Pearson correlation, Spearman correlation, R², etc.) are specified
=>N/A
- Meaning and interpretation methods of each metric are explained
=>yes
- Methods for evaluating statistical significance are described
=>yes
- **Supporting Text**:
2.6.1. Total outlier count
From the z-scores for each cortical and subcortical region generated
from the normative modelling pipeline described above, outliers were
defined as z-scores < -1.96. This is a commonly used threshold representing
95 % confidence that points below it differ from the mean
(Fisher, 1925). This is equivalent...
Fig. 1. Outlier Heterogeneity. Outlier Hamming distance matrices for PD-low visual performers (A) and PD-high visual performers (B). Kernel density estimates (Yaxis)
for a given Hamming distance score (X-axis) show that PD-low visual performers had more dissimilarity as evidenced by the flatter peak and longer tail
compared to PD-high visual performers (C)...
- **Location**:




### 6. Implementation of Internal Validation
- **Answer**: n/a (こちらpre-existingのときは該当なしか。あるいはcaliberationを適切に行ったかどうかが書かれていればよい、など、評価基準の明確化必要)
- **Confidence Rating**:
- **Negative Answer Category**:
- **Reason**:
- **Supporting Text**:
- **Location**:



### 7. External Data Validation
- **Answer**: no or N/A (pre-existing modelを使用する場合はN/Aがより適切だろうか？)
- **Confidence Rating**:
- **Negative Answer Category**:
- **Reason**:
- Evaluation on independent datasets not used for training was conducted
- Characteristics of external validation datasets are described
- Performance on external validation was compared and discussed with internal validation
- **Supporting Text**:
- **Location**:



### 8. Description of Dataset Characteristics
- **Answer**: yes
- **Confidence Rating**:
- **Negative Answer Category**:
- **Reason**:
- Sample sizes for each dataset are specified
=>ok
- Demographic characteristics (age distribution, sex ratio, patient/control group breakdown, etc.) are described
=>yes
- Clinical characteristics (symptom severity scores, illness duration, comorbidities, medication status, etc.) are described
=>yes (SI)
- Comparison of characteristics between datasets and assessment of balance are conducted
=>yes (SI)
- Handling of missing data is described
=>yes
- **Supporting Text**:
For the DLB group we only included data from
UCL where clinical severity data had been comprehensively collected.
- **Location**:SI+本文





### 9. Performance Metrics and Statistical Uncertainty
- **Answer**:yes (厳密にcriteriaを字義通りに捉えるとpartialなので、変更必要)
- **Confidence Rating**:
- **Negative Answer Category**:
- **Reason**:
- Point estimates of key performance metrics are reported
=>yes
- Confidence intervals or Bayesian credible intervals are reported
=>no (reports SE instead)
- P-values and statistical significance are appropriately reported
=>yes
- **Supporting Text**:
- **Location**:tabke 1, table2, 



### 10. Consideration for Reproducibility
- **Answer**:partial
- **Confidence Rating**:
- **Negative Answer Category**:
- **Reason**:
- Software versions used are specified
=>yes
- Availability of code and scripts is mentioned
=>no
- Sharing of trained models is mentioned
=>no, but yes for data availabity
- Data availability (including limitations if any) is described
=>yes, but made available on request
- **Supporting Text**:
- **Location**:


### 11. Interpretation Specific to Normative Modeling
- **Answer**:yes
- **Confidence Rating**:
- **Negative Answer Category**:
- **Reason**:
- Meaning of individual-level deviation scores is clearly explained
=>yes
- Clinical thresholds and decision criteria are discussed
=>yes
- Differences and advantages compared to traditional case-control studies are discussed
=>yes
- Prospects for clinical application are realistically discussed
=>yes
- **Supporting Text**:
2.6.1. Total outlier count
From the z-scores for each cortical and subcortical region generated
from the normative modelling pipeline described above, outliers were
defined as z-scores < -1.96. 
- **Location**:

