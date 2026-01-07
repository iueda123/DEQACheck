# Quality Assessment Form

## Study Identification

- **Study ID**:Bethlehem2020
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
  =>ok
- **Supporting Text**:
We examined how cortical thickness (CT) in ASD can be
parameterized as an individualized metric of atypicality relative to typically-developing (TD)
age-related norms. Across a large sample (n = 870 per group) and wide age range (5–40
years), we applied normative modelling resulting in individualized whole-brain maps of agerelated
CT atypicality in ASD and isolating a small subgroup with highly age-atypical CT
first compare the utility of age-related normative modelling
directly against more traditional case-control models. We then
describe the prevalence of ASD cases that show meaningful agerelated
deviance in CT (i.e. >2 standard deviations from agerelated
norms or outside the 95% population confidence bounds)
and show how a metric of continuous variability in age-related
atypicality in CT is expressed across the cortex in autism. Finally,
we explore age–atypical CT–behaviour associations and assess
whether such dimensional analyses associated with behaviour
identify similar or different regions than typical case-control
analyses. 
- **Location**:abstract, intro



### 2. Clear Definition of Target Population
- **Answer**:yes (厳しく言えばpartial)
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:
- Diagnostic criteria for psychiatric/neurological disorders (DSM-5, ICD-11, etc.) are specified
 =>not reported (AS)
- Definition of healthy control groups is clear (when applicable)
 =>N/A
- Demographic characteristics such as age and sex are described
  =>ok
- **Supporting Text**:
Participants. In this study, we first sought to leverage large neuroimaging datasets
to yield greater statistical power for identifying subtle effects. To achieve this, we
utilized the ABIDE datasets (ABIDE I and II; 15) (see Supplementary Fig. 1).
Informed consent was given at each site included in the ABIDE studies, see the
website for more details: http://fcon_1000.projects.nitrc.org/indi/abide/.
- **Location**:above




### 3. Clarity of Inclusion and Exclusion Criteria
- **Answer**:yes (厳しく言えばpartial)
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:
- Clear inclusion criteria are described
  =>dataset名、男性、ASD、matchedは記載あるも、具体的な基準の報告なし
- Clear exclusion criteria are described
  =>画像的な基準の報告はあり
- These criteria were uniformly applied to all participants
  =>not reported
- Normative modeling-specific requirements (image quality, data completeness, etc.) are considered
  =>yes
- Subject numbers at each stage are clearly described (flow chart recommended)
  =>yes; only number, no flow chart
- Exclusion reasons are specifically described
  =>yes
- Number of subjects included in final analysis is specified
  =>yes

- **Supporting Text**:
After matching case and control groups and excluding
scans of poorer quality (see supplementary materials) we were left with a sample
size N = 870 per group (Tables 2 and 3).
Imaging processing and quantification:
We chose to not
conduct manual segmentations and excluded failed subjects from any subsequent
analysis (and these subjects were removed prior to the matching and QC procedures).
To assess the quality of Freesurfer reconstructions we computed the Euler
index38.
- **Location**:above




### 4. Validity of Normative Modeling Outcome Measures
- **Answer**:yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:
- Brain measures used (cortical thickness, cortical area, subcortical volumes, connectivity measures, etc.) are clearly defined
- Methods for quantifying individual-level deviations through normative modeling are detailed
- Characteristics of the normative reference population are specified
- Interpretation methods for deviation scores are clear
=>ok
- **Supporting Text**:
first compare the utility of age-related normative modelling
directly against more traditional case-control models. We then
describe the prevalence of ASD cases that show meaningful agerelated
deviance in CT (i.e. >2 standard deviations from agerelated
norms or outside the 95% population confidence bounds)
and show how a metric of continuous variability in age-related
atypicality in CT is expressed across the cortex in autism. Finally,
we explore age–atypical CT–behaviour associations and assess
whether such dimensional analyses associated with behaviour
identify similar or different regions than typical case-control
analyses. To show applicability of this approach we also applied
the same method to other measures of neuroanatomy; gyrification,
volume and surface area. Results and analyses of these
metrics can be found in the supplementary materials and all code
and data used are available on GitHub32

Participants. In this study, we first sought to leverage large neuroimaging datasets
to yield greater statistical power for identifying subtle effects. To achieve this, we
utilized the ABIDE datasets (ABIDE I and II; 15) (see Supplementary Fig. 1).
Informed consent was given at each site included in the ABIDE studies, see the
website for more details: http://fcon_1000.projects.nitrc.org/indi/abide/. Given that
the normalized modelling approach gives us individual level measures we chose to
also include sites with limited numbers of subjects. Groups were subsequently
matched on age using the non-parametric nearest neighbour matching procedure
implemented in the Matchit package in R (https://cran.r-project.org/web/packages/
MatchIt/index.html)52. After matching case and control groups and excluding
scans of poorer quality (see supplementary materials) we were left with a sample
size N = 870 per group (Tables 2 and 3).
- **Location**:above




### 5. Handling of Confounding Variables
- **Answer**:yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:
- Basic confounding variables such as age and sex are considered
- For multi-site studies, methods for handling site effects are described
- Treatment of covariates in normative modeling is clear
- When harmonization methods are used, their details are described

- **Supporting Text**:
There are likely many variables that contribute to variability in CT between
individuals and across the brain. In order to visually assess the contribution of some
prominent sources of variance we adopted a visualization framework derived from
gene expression analysis (http://bioconductor.org/packages/variancePartition)61 and
included the most commonly available covariates in the ABIDE dataset: age, sex,
diagnosis, scanner site, full-scale IQ, verbal IQ, handedness and SRS. Given that
ABIDE was not designed as an integrated dataset from the outset, it seems plausible
that the scanner site might be related to autism or autism-related variables (e.g., some
sites might have different case-control ratios or only recruited specific subgroups).
Figure 5 shows the ranked contribution of those covariates. Perhaps unsurprisingly,
scanner site and age proved to be the most dominant sources of variance (each
explaining on average around 15% of the total variance). Our initial conventional
analysis was aimed to delineate potential broad case-control differences, as has been
done in previous studies14,27. We used a linear mixed effects model with scanner site
as a random effect
- **Location**:above




### 6. Clarity of Data Sources
- **Answer**:yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:
- Dataset names or study names used are specified
- Time and location of data acquisition are described
- For open datasets, specific database names (e.g., Human Connectome Project, ABCD Study, etc.) are specified
  =>厳密に言えばtime of acquisitionは未報告か
- **Supporting Text**:
To achieve this, we
utilized the ABIDE datasets (ABIDE I and II; 15) (see Supplementary Fig. 1).
Informed consent was given at each site included in the ABIDE studies, see the
website for more details: http://fcon_1000.projects.nitrc.org/indi/abide/.
- **Location**:above



### 7. Description of Image Acquisition Protocol
- **Answer**:partial
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:
- Details of MRI sequences used (T1-weighted, DTI, fMRI, etc.) are described
  =>ok
- Imaging parameters (TR, TE, flip angle, resolution, etc.) are specified
　=>not reported (各サイトの詳細まではない)
- Scanner specifications (manufacturer, field strength, etc.) are described
  =not reported (同上)
- **Supporting Text**:
Imaging processing and quantification. Cortical surface reconstruction was
performed using the MPRAGE (T1) image of each participant with FreeSurfer
(http://surfer.nmr.mgh.harvard.edu/) version (v5.3.0, to ensure comparability with
previous ABIDE publications). The reconstruction pipeline performed by Free-
Surfer “recon-all” involved intensity normalization, registration to Talairach space,
skull stripping, WM segmentation, tessellation of the WM boundary, and automatic
correction of topological defects. Briefly, non-uniformity intensity correction
algorithms were applied before skull stripping53, resulting in resampled isotropic
images of 1 mm. An initial segmentation of the white matter tissue was performed
to generate a tessellated representation of the WM/GM boundary. The resulting
surface
- **Location**:above







---
## Assessment Items - Group B


### 1. Details of Data Preprocessing
- **Answer**:yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:-
- Preprocessing software used (FreeSurfer, FSL, SPM, etc.) is specified
=>ok
- Each preprocessing step (skull stripping, normalization, segmentation, etc.) is detailed
=>ok
- Quality control procedures are described
=>ok
- **Supporting Text**:
Imaging processing and quantification. Cortical surface reconstruction was
performed using the MPRAGE (T1) image of each participant with FreeSurfer
(http://surfer.nmr.mgh.harvard.edu/) version (v5.3.0, to ensure comparability with
previous ABIDE publications). The reconstruction pipeline performed by Free-
Surfer “recon-all” involved intensity normalization, registration to Talairach space,
skull stripping, WM segmentation, tessellation of the WM boundary, and automatic
correction of topological defects.
CT of each vertex was defined as the shortest distance
between vertices of the GM/WM boundary and the pial surface55. We chose to not
conduct manual segmentations and excluded failed subjects from any subsequent
analysis (and these subjects were removed prior to the matching and QC procedures).
- **Location**:above



### 2. Clarity of Data Partitioning Methods
- **Answer**:yes 
- **Confidence Rating**:
- **Negative Answer Category**:
- **Reason**:
- Data partition ratios (e.g., 70:15:15) are specified
  =>N/A, binned into one-year age bins
- Partitioning methods (random, stratified sampling, etc.) are described
  =>N/A
- When cross-validation is used, its details (K-fold, etc.) are described
  =>N/A, bootstrap used
- Measures to prevent data leakage are confirmed
  =>ok
- **Supporting Text**:
we permuted the normative sample (1000 bootstraps, with replacement) and
computed 1000 permuted w-scores for each individual and each brain region. To
subsequently quantify the reliability of the w-score we computed an FDR corrected
analogous p-value for each subject by computing the absolute position of the real
w-score in the distribution of permuted w-scores. The rationale being that if a real
w-score would be in the top 5% of the bootstrapped distribution it would likely not
be a reliable score (e.g. the score would be influenced by only a small subset of the
normative data).
- **Location**:above





### 3. Details of Normative Modeling Approach
- **Answer**:yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:
- Types of statistical models (Gaussian process regression, Bayesian models, linear regression, etc.) are specified
 =>ok
- Model hyperparameters and settings are described
 =>ok
- Software or tools used (PCNtoolkit, normative-modeling, etc.) are specified
 =>ok
- **Supporting Text**:
In the first instance LOESS regression is used to estimate the developmental trajectory on CT for every individual brain region to obtain an agespecific
mean and standard deviation. Then we computed median for each one-year age-bin for these mean and median neurotypical estimates to align them
with the ASD group. Next, for each individual with autism and each brain region the normative mean and standard deviation are used to compute a w-score
relative to their neurotypical age-bin
Imaging processing and quantification. Cortical surface reconstruction was
performed using the MPRAGE (T1) image of each participant with FreeSurfer
(http://surfer.nmr.mgh.harvard.edu/) version (v5.3.0, to ensure comparability with
previous ABIDE publications).
Groups were subsequently
matched on age using the non-parametric nearest neighbour matching procedure
implemented in the Matchit package in R (https://cran.r-project.org/web/packages/
MatchIt/index.html)52.
- **Location**:above







### 4. Details of Training Algorithm
- **Answer**:yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:
- Optimization algorithms (gradient descent, ADAM, L-BFGS, etc.) are specified
  =>ok
- Hyperparameter setting methods (grid search, Bayesian optimization, etc.) are described
  =>ok
- Convergence criteria and training termination conditions (number of epochs, loss function thresholds, etc.) are specified
  =>N/A
- Use of regularization techniques (L1/L2 regularization, etc.) is described
  =>N/A

- **Supporting Text**:
We used a local polynomial regression fitting procedure
(LOESS)34,35, where the local width or smoothing kernel of the
regression was determined by the model that provided the overall
smallest sum of squared errors using hyperparameter optimization
across 5–100% of the full age range using Brent’s method36 as
implemented in the R optim function from the stats package.
We also assessed consistency of our output using centiles scoring
and consistency of the normative model using extensive bootstrapping
and sensitivity analyses, both showed high outcome
consistency (see “Methods” section and Supplementary Materials;
Supplementary Figs. 3–5).
- **Location**:above





### 5. Model Performance Evaluation Metrics
- **Answer**:yes扱いでよいのではないだろうか。
- **Confidence Rating**:medium
- **Negative Answer Category**:incomplete
- **Reason**:
- Performance metrics used (MSLL, Pearson correlation, Spearman correlation, R², etc.) are specified
  =>NR, N/A.  正常分布を作成しているものは予測性能の評価はそもそもN/A。
- Meaning and interpretation methods of each metric are explained
  =>ok
- Methods for evaluating statistical significance are described
  =>ok
- **Supporting Text**: w-scoreについてもろもろ
- **Location**:above





### 6. Implementation of Internal Validation
- **Answer**:yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:
- Evaluation on validation sets independent of training data was conducted
=>no.…だけどもBLRやautoencoder系でなく、LOESS/GAMLSS/HBRなど全数を使って参照モデルを構築する場合、N/A扱いか。
　下記の、bootstrapで対応している点では、実質yes扱い。
- Appropriate validation methods such as cross-validation were used
=>bootsrap, sensitivity analysis
- Measures to detect and prevent overfitting were implemented
=>ok
- **Supporting Text**:
We also assessed consistency of our output using centiles scoring
and consistency of the normative model using extensive bootstrapping
and sensitivity analyses, both showed high outcome
consistency (see “Methods” section and Supplementary Materials;
- **Location**:-




### 7. External Data Validation
- **Answer**:no
- **Confidence Rating**:high
- **Negative Answer Category**:missing
- **Reason**:
- Evaluation on independent datasets not used for training was conducted
  =>no
- Characteristics of external validation datasets are described
  =>no
- Performance on external validation was compared and discussed with internal validation
  =>no
- **Supporting Text**:-
- **Location**:-





### 8. Description of Dataset Characteristics
- **Answer**:partial
- **Confidence Rating**:high
- **Negative Answer Category**:incomplete
- **Reason**:
- Sample sizes for each dataset are specified
=>ok
- Demographic characteristics (age distribution, sex ratio, patient/control group breakdown, etc.) are described
=>ok
- Clinical characteristics (symptom severity scores, illness duration, comorbidities, medication status, etc.) are described
=>not reported
- Comparison of characteristics between datasets and assessment of balance are conducted
=>no (matchedとは書いてあるが詳細なし)
- Handling of missing data is described
=>ok 
- **Supporting Text**:
Groups were subsequently
matched on age using the non-parametric nearest neighbour matching procedure
implemented in the Matchit package in R (https://cran.r-project.org/web/packages/
MatchIt/index.html)52. After matching case and control groups and excluding
scans of poorer quality (see supplementary materials) we were left with a sample
size N = 870 per group (Tables 2 and 3).
We chose to not
conduct manual segmentations and excluded failed subjects from any subsequent
analysis (and these subjects were removed prior to the matching and QC procedures).
- **Location**:-





### 9. Performance Metrics and Statistical Uncertainty
- **Answer**:partial~yes
- **Confidence Rating**:medium
- **Negative Answer Category**:-
- **Reason**:
- Point estimates of key performance metrics are reported
=>ok (w-score、cohen's Dでの報告はあり)
- Confidence intervals or Bayesian credible intervals are reported
=>no　（CIはないが、bootstrapやsensitivity analysisの実施あり）
- P-values and statistical significance are appropriately reported
=>ok (FDR, q<0.05>)

- **Supporting Text**:
Multiple comparison correction
was implemented with Benjamini–Hochberg FDR at q < 0.0562. All models also
included Euler indices38 and mean framewise displacement37 as confound regressors
(see also Supplementary Fig. 3 for sensitivity analyses on these confound regressors
Normative modelling reliability. To assess the reliability of the normative w-score
we permuted the normative sample (1000 bootstraps, with replacement) and
computed 1000 permuted w-scores for each individual and each brain region. To
subsequently quantify the reliability of the w-score we computed an FDR corrected
analogous p-value for each subject by computing the absolute position of the real
w-score in the distribution of permuted w-scores.

Panel a shows effect sizes for regions passing FDR correction for linear mixed
effect modelling of conventional case control difference analysis. Cohen’s d values represent ASD−control, thus blue denotes ASD<control and red
denotes ASD>control. P

- **Location**:above





### 10. Consideration for Reproducibility
- **Answer**:yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:
- Software versions used are specified
=>ok
- Availability of code and scripts is mentioned
=>ok
- Sharing of trained models is mentioned
=>no, but yes through reported data availability 
- Data availability (including limitations if any) is described
=>ok
- **Supporting Text**:
Data availability
All data is openly available on GitHub32, this includes all measures extracted from the
raw imaging data alongside the relevant phenotypic and quality control measures.
Original unprocessed neuroimaging data is openly available through the ABIDE
consortium: http://fcon_1000.projects.nitrc.org/indi/abide/abide_I.html.
Code availability
All code is openly available on GitHub32, Cohen’s d were computed using: https://github.
com/mvlombardo/utils/blob/master/cohens_d.R and the centiles cross-validation code
can be found in https://github.com/deep-introspection/PyNM.
- **Location**: -



### 11. Interpretation Specific to Normative Modeling
- **Answer**:yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:
- Meaning of individual-level deviation scores is clearly explained
=>ok
- Clinical thresholds and decision criteria are discussed
=>ok
- Differences and advantages compared to traditional case-control studies are discussed
=>ok
- Prospects for clinical application are realistically discussed
=>ok
- **Supporting Text**:
- **Location**:-





---
## Additional Comments
**Additional Comments**:
