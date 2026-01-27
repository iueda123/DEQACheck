# Supplementary material

Little et al. (2023) Multivariate brain-cognition associations in euthymic bipolar disorder

## 1 Normative model of cortical thickness

We utilised four public datasets of T1-weighted scans from healthy controls:

- the WU-Minn Human Connectome Project Young Adult (HCP; available at https://www.humanconnectome.org/study/hcp-young-adult);1
- Open Big Healthy Brains (OpenBHB; available at https://ieee-dataport.org/open-access/openbhb-multi-site-brain-mri-dataset-age-prediction-and-debiasing);2
- Open Access Series of Imaging Studies (OASIS-3; available at https://sites.wustl.edu/oasisbrains/home/oasis-3/);3 and
- the Cambridge Centre for Ageing and Neuroscience (Cam-CAN; available at http://www.mrc-cbu.cam.ac.uk/datasets/camcan/).4

We also used a cohort of healthy adult participants scanned at Newcastle University as part of another study (site=NCL). Table S1 shows the demographic data for each site.

| Dataset   | N    | Age   | Age   | Age   | Sex      | Sex      |
|-----------|------|-------|-------|-------|----------|----------|
| Dataset   | N    | Mean  | SD    | Range | N female | % female |
| CamCAN    | 644  | 54.75 | 18.51 | 18-88 | 327      | 50.8%    |
| OASIS-3   | 542  | 66.18 | 8.89  | 42-95 | 335      | 61.8%    |
| OpenBHB   | 3984 | 24.92 | 14.29 | 5-88  | 1897     | 47.6%    |
| HCP       | 1113 | 28.80 | 3.70  | 22-37 | 606      | 54.4%    |
| NCL       | 61   | 75.51 | 6.13  | 61-89 | 29       | 47.5%    |
| Total     | 6344 | 32.64 | 19.39 | 5-95  | 3194     | 50.3%    |

Table S: Demographic data for each healthy control dataset included in the normative model.

## 2 Data pre-processing

### 2.1 Missing neuropsychological data

Five percent of neuropsychological data were missing in total (BD=4%, HC=8%). Missing data were spread across cognitive variables as follows: NART IQ 7%, d2 test of attention 2%, DSST 7%, TMT-A 7%, TMT-B 8%, and verbal fluency 6%. The pattern of missing data was not considered biased (i.e., missing cases were not influenced by cognitive ability and were ‘Missing at Random’) and therefore was suitable for imputation. Missing data were imputed using linear regression, where missing data for each variable was predicted using age, education, NART, and all other cognitive scores. This was done for bipolar disorder (BD) and healthy control (HC) groups separately since group differences in cognitive functions were expected. Before imputing missing data, outliers (defined as ±3 standard deviations around the mean) were Winsorized and skewed data were transformed to meet the assumptions of multiple regression, then the imputed values were back-transformed to align with the original data.

### 2.2 Checking assumptions of statistical models

Prior to statistical analysis, we checked that the assumptions of the statistical models were met. Normality of distribution of continuous variables was tested using Shapiro-Wilk tests and by examining Q-Q plots. Boxplots were visually inspected to identify outliers. Scatterplots were visually inspected to test whether continuous variables had linear relationships. Multivariate normality was tested using Mardia’s tests of skewness and kurtosis.

CCA assumes that the variables have linear relationships with one another and the two datasets have multivariate normality.5 The cortical thickness (CT) principal components (PCs) had multivariate normality for the BD group ( *p* s&gt;.05 for skewness and kurtosis), but the cognitive data did not have multivariate normality (skewness *p* &lt;.001; kurtosis *p* =.007) and some variables had outliers. Therefore, neuropsychological data were transformed using reflect and log or reflect and square root transformations where appropriate before running the CCA. d2 correct, d2 fluctuation rate and DSST were transformed using reflect and log; TMT-A and TMT-B were transformed using reflect and square root; and d2 continuous performance and category fluency did not require transformation. After transforming, neuropsychological data showed multivariate normality ( *p* &gt;.05 for skewness and kurtosis). The analysis was run using the transformed data; we also ran the analysis using the untransformed data for comparison.

## 3 

## 4 Neuropsychological group differences after pre-processing cognitive data

Table S2 shows the descriptive statistics and group differences for pre-processed neuropsychological data (after imputing missing data and controlling for age and premorbid IQ) for BD patients and matched healthy controls in the BLISS study. BD performed significantly worse on d2 concentration performance, d2 percent error, DSST, and category fluency; with all significant differences showing medium effect sizes ( *d* &gt;0.5).

| Neuropsychological test      | Healthy Controls   | Healthy Controls   | Healthy Controls   | Healthy Controls   | Bipolar Disorder   | Bipolar Disorder   | Bipolar Disorder   | Bipolar Disorder   | Group differences                                    | Group differences   | Group differences   | Group differences    |
|------------------------------|--------------------|--------------------|--------------------|--------------------|--------------------|--------------------|--------------------|--------------------|------------------------------------------------------|---------------------|---------------------|----------------------|
| Neuropsychological test      | N                  | Mean               | SD                 | Median             | N                  | Mean               | SD                 | Median             | **Statistic**  **(**  ***t***  **/**  ***W***  **)** | df                  | p-value             | **Cohen’s**  ***d*** |
| d2 Concentration Performance | 26                 | 0                  | 1                  | -0.18              | 56                 | -0.72              | 1.31               | -0.83              | -2.73*                                               | 62.47               | .008                | 0.60                 |
| d2 Percent Error             | 26                 | 0                  | 1                  | 0.19               | 56                 | -1.29              | 2.44               | -1.11              | 504*                                                 | -                   | .026                | 0.63                 |
| d2 Fluctuation Rate          | 26                 | 0                  | 1                  | -0.06              | 56                 | -0.44              | 1.88               | -0.24              | 626                                                  | -                   | .312                | 0.27                 |
| DSST Symbol minus Copy time  | 26                 | 0                  | 1                  | -0.02              | 56                 | -1.81              | 2.81               | -1.30              | 372*                                                 | -                   | <.001               | 0.77                 |
| TMT-A time                   | 26                 | 0                  | 1                  | 0.22               | 56                 | -0.49              | 1.36               | -0.11              | 590                                                  | -                   | .171                | 0.40                 |
| TMT-B minus TMT-A time       | 26                 | 0                  | 1                  | -0.02              | 56                 | -0.71              | 1.57               | -0.31              | 541                                                  | -                   | .063                | 0.51                 |
| Category Fluency total       | 26                 | 0                  | 1                  | 0.03               | 56                 | -0.60              | 1.39               | -0.64              | -2.22*                                               | 66.07               | .030                | 0.47                 |

Table S: Descriptive statistics and group differences on neuropsychological variables after missing data were imputed and age and premorbid IQ (NART score) were regressed out. *Significant at the 0.05 level. SD=standard deviation; df=degrees of freedom; DSST=Digit Symbol Substitution Test; TMT=Trail-Making Test.

## 5 Cortical thickness of each ROI and group differences for bipolar disorder patients and normative healthy controls

Table S3 shows the cortical thickness (CT) for each region of interest (ROI) and group differences for BD patients and healthy controls from the normative model (including HC from the BLISS study). Group differences were tested using independent samples *t* -tests. Significant group differences were found for: bilateral paracentral, postcentral, precentral, caudal middle frontal, precuneus, pericalcarine, medial orbitofrontal, and supramarginal areas; left cuneus, inferior parietal, middle temporal, pars triangularis, rostral middle frontal, superior parietal, superior temporal, and transverse temporal areas; and right lateral orbitofrontal, lingual, insula and superior frontal areas. After correcting for multiple comparisons, the following group differences remained significance: bilateral paracentral, precentral, and postcentral areas; left caudal middle frontal, precuneus and cuneus areas; and right insula and pericalcarine areas. In all regions showing significant group differences, BD showed reduced cortical thickness compared to controls.

| Brain region   | Brain region                      | Healthy Control   | Healthy Control   | Healthy Control   | Bipolar Disorder   | Bipolar Disorder   | Bipolar Disorder   | Group differences   | Group differences   | Group differences   | Group differences       | Group differences    |
|----------------|-----------------------------------|-------------------|-------------------|-------------------|--------------------|--------------------|--------------------|---------------------|---------------------|---------------------|-------------------------|----------------------|
| Hemisphere     | ROI                               | N                 | Mean              | SD                | N                  | Mean               | SD                 | t                   | df                  | p                   | ***p-***  **corrected** | **Cohen’s**  ***d*** |
| Left           | Banks of Superior Temporal Sulcus | 5977              | 0                 | 1                 | 56                 | -0.02              | 0.85               | -0.183              | 56.45               | .855                | .894                    | 0.021                |
| Left           | Caudal Anterior Cingulate         | 5977              | 0                 | 1                 | 56                 | 0.25               | 1.23               | 1.494               | 55.69               | .141                | .246                    | -0.245               |
| Left           | Caudal Middle Frontal             | 5977              | 0                 | 1                 | 56                 | -0.52              | 1.14               | -3.405              | 55.79               | .001                | .011                    | 0.520                |
| Left           | Cuneus                            | 5977              | 0                 | 1                 | 56                 | -0.44              | 0.92               | -3.525              | 56.22               | .001                | .011                    | 0.437                |
| Left           | Entorhinal                        | 5977              | 0                 | 1                 | 56                 | 0.00               | 1.11               | 0.025               | 55.84               | .980                | .980                    | -0.004               |
| Left           | Fusiform                          | 5977              | 0                 | 1                 | 56                 | -0.12              | 1.10               | -0.832              | 55.86               | .409                | .538                    | 0.122                |
| Left           | Inferior Parietal                 | 5977              | 0                 | 1                 | 56                 | -0.32              | 1.05               | -2.287              | 55.93               | .026                | .074                    | 0.323                |
| Left           | Inferior Temporal                 | 5977              | 0                 | 1                 | 56                 | -0.13              | 1.07               | -0.877              | 55.90               | .384                | .533                    | 0.126                |
| Left           | Isthmus Cingulate                 | 5977              | 0                 | 1                 | 56                 | -0.04              | 1.15               | -0.264              | 55.78               | .793                | .843                    | 0.041                |
| Left           | Lateral Occipital                 | 5977              | 0                 | 1                 | 56                 | -0.14              | 1.03               | -1.026              | 55.97               | .309                | .467                    | 0.142                |
| Left           | Lateral Orbitofrontal             | 5977              | 0                 | 1                 | 56                 | -0.05              | 1.03               | -0.387              | 55.98               | .700                | .807                    | 0.053                |
| Left           | Lingual                           | 5977              | 0                 | 1                 | 56                 | -0.27              | 1.38               | -1.462              | 55.54               | .149                | .253                    | 0.270                |
| Left           | Medial Orbitofrontal              | 5977              | 0                 | 1                 | 56                 | -0.35              | 1.14               | -2.299              | 55.79               | .025                | .074                    | 0.352                |
| Left           | Middle Temporal                   | 5977              | 0                 | 1                 | 56                 | -0.28              | 0.90               | -2.332              | 56.28               | .023                | .074                    | 0.282                |
| Left           | Parahippocampal                   | 5977              | 0                 | 1                 | 56                 | 0.22               | 1.06               | 1.519               | 55.92               | .135                | .245                    | -0.216               |
| Left           | Paracentral                       | 5977              | 0                 | 1                 | 56                 | -0.57              | 1.00               | -4.274              | 56.04               | <.001               | <.001                   | 0.574                |
| Left           | Pars Opercularis                  | 5977              | 0                 | 1                 | 56                 | -0.30              | 1.13               | -1.954              | 55.81               | .056                | .127                    | 0.296                |
| Left           | Pars Orbitalis                    | 5977              | 0                 | 1                 | 56                 | -0.19              | 1.10               | -1.274              | 55.86               | .208                | .329                    | 0.188                |
| Left           | Pars Triangularis                 | 5977              | 0                 | 1                 | 56                 | -0.28              | 1.02               | -2.038              | 56.00               | .046                | .112                    | 0.278                |
| Left           | Pericalcarine                     | 5977              | 0                 | 1                 | 56                 | -0.27              | 0.83               | -2.377              | 56.49               | .021                | .074                    | 0.267                |
| Left           | Postcentral                       | 5977              | 0                 | 1                 | 56                 | -0.64              | 0.84               | -5.671              | 56.46               | <.001               | <.001                   | 0.645                |
| Left           | Posterior Cingulate               | 5977              | 0                 | 1                 | 56                 | -0.30              | 1.20               | -1.888              | 55.72               | .064                | .140                    | 0.303                |
| Left           | Precentral                        | 5977              | 0                 | 1                 | 56                 | -0.65              | 1.07               | -4.488              | 55.90               | <.001               | <.001                   | 0.645                |
| Left           | Precuneus                         | 5977              | 0                 | 1                 | 56                 | -0.47              | 1.24               | -2.848              | 55.67               | .006                | .041                    | 0.473                |
| Left           | Rostral Anterior Cingulate        | 5977              | 0                 | 1                 | 56                 | -0.24              | 1.10               | -1.645              | 55.86               | .106                | .206                    | 0.242                |
| Left           | Rostral Middle Frontal            | 5977              | 0                 | 1                 | 56                 | -0.35              | 1.09               | -2.377              | 55.88               | .021                | .074                    | 0.346                |
| Left           | Superior Frontal                  | 5977              | 0                 | 1                 | 56                 | -0.20              | 1.07               | -1.395              | 55.90               | .168                | .279                    | 0.201                |
| Left           | Superior Parietal                 | 5977              | 0                 | 1                 | 56                 | -0.38              | 1.16               | -2.407              | 55.77               | .019                | .074                    | 0.374                |
| Left           | Superior Temporal                 | 5977              | 0                 | 1                 | 56                 | -0.38              | 1.19               | -2.366              | 55.73               | .021                | .074                    | 0.377                |
| Left           | Supramarginal                     | 5977              | 0                 | 1                 | 56                 | -0.31              | 1.05               | -2.174              | 55.93               | .034                | .086                    | 0.307                |
| Left           | Frontal pole                      | 5977              | 0                 | 1                 | 56                 | 0.10               | 0.92               | 0.822               | 56.22               | .415                | .538                    | -0.102               |
| Left           | Temporal pole                     | 5977              | 0                 | 1                 | 56                 | -0.16              | 1.22               | -0.967              | 55.70               | .338                | .489                    | 0.157                |
| Left           | Transverse Temporal               | 5977              | 0                 | 1                 | 56                 | -0.44              | 1.28               | -2.562              | 55.63               | .013                | .074                    | 0.437                |
| Left           | Insula                            | 5977              | 0                 | 1                 | 56                 | -0.12              | 1.04               | -0.883              | 55.95               | .381                | .533                    | 0.124                |
| Right          | Banks of Superior Temporal Sulcus | 5977              | 0                 | 1                 | 56                 | 0.02               | 1.05               | 0.117               | 55.93               | .907                | .924                    | -0.017               |
| Right          | Caudal Anterior Cingulate         | 5977              | 0                 | 1                 | 56                 | 0.15               | 1.10               | 0.991               | 55.86               | .326                | .482                    | -0.146               |
| Right          | Caudal Middle Frontal             | 5977              | 0                 | 1                 | 56                 | -0.33              | 1.02               | -2.442              | 56.00               | .018                | .074                    | 0.333                |
| Right          | Cuneus                            | 5977              | 0                 | 1                 | 56                 | -0.22              | 1.07               | -1.508              | 55.91               | .137                | .245                    | 0.216                |
| Right          | Entorhinal                        | 5977              | 0                 | 1                 | 56                 | -0.10              | 1.24               | -0.585              | 55.67               | .561                | .694                    | 0.097                |
| Right          | Fusiform                          | 5977              | 0                 | 1                 | 56                 | -0.14              | 1.29               | -0.815              | 55.62               | .419                | .538                    | 0.141                |
| Right          | Inferior Parietal                 | 5977              | 0                 | 1                 | 56                 | 0.02               | 1.15               | 0.114               | 55.78               | .910                | .924                    | -0.017               |
| Right          | Inferior Temporal                 | 5977              | 0                 | 1                 | 56                 | -0.04              | 1.00               | -0.333              | 56.04               | .740                | .812                    | 0.045                |
| Right          | Isthmus Cingulate                 | 5977              | 0                 | 1                 | 56                 | 0.07               | 1.18               | 0.453               | 55.74               | .652                | .778                    | -0.072               |
| Right          | Lateral Occipital                 | 5977              | 0                 | 1                 | 56                 | -0.04              | 0.94               | -0.354              | 56.18               | .724                | .812                    | 0.045                |
| Right          | Lateral Orbitofrontal             | 5977              | 0                 | 1                 | 56                 | -0.34              | 1.02               | -2.498              | 56.00               | .015                | .074                    | 0.342                |
| Right          | Lingual                           | 5977              | 0                 | 1                 | 56                 | -0.42              | 1.35               | -2.309              | 55.56               | .025                | .074                    | 0.417                |
| Right          | Medial Orbitofrontal              | 5977              | 0                 | 1                 | 56                 | -0.34              | 1.04               | -2.394              | 55.95               | .020                | .074                    | 0.335                |
| Right          | Middle Temporal                   | 5977              | 0                 | 1                 | 56                 | 0.06               | 1.08               | 0.421               | 55.88               | .676                | .793                    | -0.061               |
| Right          | Parahippocampal                   | 5977              | 0                 | 1                 | 56                 | -0.05              | 1.13               | -0.350              | 55.81               | .728                | .812                    | 0.053                |
| Right          | Paracentral                       | 5977              | 0                 | 1                 | 56                 | -0.36              | 0.87               | -3.129              | 56.38               | .003                | .026                    | 0.365                |
| Right          | Pars Opercularis                  | 5977              | 0                 | 1                 | 56                 | -0.17              | 1.15               | -1.117              | 55.78               | .269                | .416                    | 0.172                |
| Right          | Pars Orbitalis                    | 5977              | 0                 | 1                 | 56                 | -0.08              | 1.06               | -0.564              | 55.93               | .575                | .698                    | 0.080                |
| Right          | Pars Triangularis                 | 5977              | 0                 | 1                 | 56                 | -0.22              | 1.06               | -1.525              | 55.93               | .133                | .245                    | 0.216                |
| Right          | Pericalcarine                     | 5977              | 0                 | 1                 | 56                 | -0.35              | 0.86               | -2.985              | 56.40               | .004                | .030                    | 0.345                |
| Right          | Postcentral                       | 5977              | 0                 | 1                 | 56                 | -0.29              | 0.77               | -2.805              | 56.76               | .007                | .043                    | 0.291                |
| Right          | Posterior Cingulate               | 5977              | 0                 | 1                 | 56                 | -0.14              | 1.29               | -0.817              | 55.62               | .417                | .538                    | 0.141                |
| Right          | Precentral                        | 5977              | 0                 | 1                 | 56                 | -0.46              | 0.94               | -3.679              | 56.17               | .001                | .011                    | 0.465                |
| Right          | Precuneus                         | 5977              | 0                 | 1                 | 56                 | -0.39              | 1.25               | -2.349              | 55.66               | .022                | .074                    | 0.393                |
| Right          | Rostral Anterior Cingulate        | 5977              | 0                 | 1                 | 56                 | 0.25               | 1.11               | 1.655               | 55.84               | .103                | .206                    | -0.246               |
| Right          | Rostral Middle Frontal            | 5977              | 0                 | 1                 | 56                 | -0.05              | 1.18               | -0.311              | 55.74               | .757                | .817                    | 0.049                |
| Right          | Superior Frontal                  | 5977              | 0                 | 1                 | 56                 | -0.30              | 1.00               | -2.209              | 56.04               | .031                | .084                    | 0.296                |
| Right          | Superior Parietal                 | 5977              | 0                 | 1                 | 56                 | -0.19              | 1.09               | -1.328              | 55.87               | .190                | .308                    | 0.194                |
| Right          | Superior Temporal                 | 5977              | 0                 | 1                 | 56                 | -0.29              | 1.10               | -2.002              | 55.86               | .050                | .117                    | 0.294                |
| Right          | Supramarginal                     | 5977              | 0                 | 1                 | 56                 | -0.34              | 1.16               | -2.174              | 55.77               | .034                | .086                    | 0.336                |
| Right          | Frontal pole                      | 5977              | 0                 | 1                 | 56                 | -0.18              | 0.78               | -1.690              | 56.71               | .096                | .198                    | 0.178                |
| Right          | Temporal pole                     | 5977              | 0                 | 1                 | 56                 | -0.20              | 0.82               | -1.847              | 56.56               | .070                | .149                    | 0.203                |
| Right          | Transverse Temporal               | 5977              | 0                 | 1                 | 56                 | 0.10               | 1.24               | 0.599               | 55.67               | .551                | .694                    | -0.100               |
| Right          | Insula                            | 5977              | 0                 | 1                 | 56                 | -0.46              | 1.06               | -3.200              | 55.92               | .002                | .019                    | 0.456                |

Table S: Cortical thickness values for each region of interest (ROI) for the bipolar disorder (BD) and healthy controls from the normative model, and group difference statistics. df=degrees of freedom; CT=cortical thickness. p-corrected: p-values were corrected using the Benjamini-Hochberg method to control the false discovery rate (set at 5%).

## 6 Canonical correlation coefficients

Table S4 shows the results for each canonical correlation. None were significant according to Pillai’s trace (all *p* s&gt;.05).

|   Canonical correlation |   Coefficient (  *rho*  ) |   Pillai’s Trace (  *p*  ) |
|-------------------------|---------------------------|----------------------------|
|                       1 |                     0.86  |                      0.112 |
|                       2 |                     0.812 |                      0.505 |
|                       3 |                     0.732 |                      0.829 |
|                       4 |                     0.713 |                      0.931 |
|                       5 |                     0.594 |                      0.984 |
|                       6 |                     0.521 |                      0.986 |
|                       7 |                     0.404 |                      0.977 |

Table S: Coefficients and p-values for all seven canonical correlations between the cognitive dataset (U1) and the cortical thickness dataset (V1).

## 7 Cross-loadings for each ROI

Table S5 shows the cross-loading coefficients and their corresponding p-values for each ROI. Coefficients were considered meaningful if they were above .3 or below -.3.

|                                   | Left hemisphere   | Left hemisphere   | Right hemisphere   | Right hemisphere   |
|-----------------------------------|-------------------|-------------------|--------------------|--------------------|
| Region                            | rho               | p                 | rho                | p                  |
| Banks of Superior Temporal Sulcus | -.045             | .740              | -.183              | .176               |
| Caudal Anterior Cingulate         | .200              | .139              | .035               | .797               |
| Caudal Middle Frontal             | -.011             | .938              | .037               | .786               |
| Cuneus                            | -.070             | .609              | -.025              | .855               |
| Entorhinal                        | .262              | .051              | .380               | .004               |
| Fusiform                          | .057              | .676              | .066               | .631               |
| Inferior Parietal                 | .066              | .628              | .006               | .967               |
| Inferior Temporal                 | .406              | .002              | .286               | .033               |
| Isthmus Cingulate                 | -.105             | .442              | .143               | .292               |
| Lateral Occipital                 | .059              | .664              | .259               | .054               |
| Lateral Orbitofrontal             | -.111             | .416              | .145               | .286               |
| Lingual                           | -.188             | .165              | .020               | .882               |
| Medial Orbitofrontal              | .108              | .427              | .200               | .140               |
| Middle Temporal                   | .045              | .739              | .121               | .376               |
| Parahippocampal                   | .237              | .079              | .232               | .085               |
| Paracentral                       | .195              | .150              | .101               | .459               |
| Pars Opercularis                  | .159              | .243              | .042               | .759               |
| Pars Orbitalis                    | .016              | .904              | .268               | .046               |
| Pars Triangularis                 | -.071             | .605              | .035               | .797               |
| Pericalcarine                     | -.154             | .257              | .209               | .122               |
| Postcentral                       | .015              | .913              | -.061              | .656               |
| Posterior Cingulate               | .167              | .219              | .186               | .170               |
| Precentral                        | .195              | .150              | .194               | .151               |
| Precuneus                         | .039              | .776              | .068               | .621               |
| Rostral Anterior Cingulate        | .120              | .380              | -.039              | .775               |
| Rostral Middle Frontal            | .094              | .492              | .152               | .265               |
| Superior Frontal                  | .100              | .462              | .147               | .281               |
| Superior Parietal                 | .104              | .447              | .102               | .455               |
| Superior Temporal                 | .249              | .065              | .297               | .026               |
| Supramarginal                     | .120              | .376              | .032               | .814               |
| Frontal pole                      | .177              | .191              | -.018              | .893               |
| Temporal pole                     | .230              | .087              | .334               | .012               |
| Transverse Temporal               | .021              | .877              | .119               | .382               |
| Insula                            | .011              | .933              | .079               | .563               |

Table S: Cross-loadings for each region in dataset V1 with dataset U1.

## 8 CCA using untransformed neuropsychological data

CCA was also performed using the *untransformed* neuropsychological variables as dataset *U* and the 23 cortical thickness PCs as dataset *V* . Here, the untransformed data refers to data that was pre-processed (i.e., missing data imputed, age and NART IQ regressed out, and z-scored based on control group mean and SD), but not transformed to address skewed distributions.

The first canonical correlation coefficient had a strong linear correlation of .815 but this was not significant according to Pillai’s Trace ( *p* =.153) or the permutation test ( *p* =.136). Canonical cross-loadings suggested that that d2 continuous performance, DSST and TMT-A scores from dataset *U1* (Table S6) were most strongly associated with cortical thickness in the following areas: left insula, and right entorhinal, inferior temporal, and medial orbito-frontal areas (Table S7). Left inferior temporal and paracentral, and right lingual areas did not meet the .3 threshold but had significant *p* -values.

| Variables from  *U*       |   Loadings | Cross-loadings   |
|---------------------------|------------|------------------|
| d2 Continuous Performance |     -0.579 | -.472*           |
| d2 Percentage of Errors   |      0.055 | .044             |
| d2 Fluctuation Rate       |      0.328 | .267             |
| DSST                      |     -0.815 | -.664*           |
| TMT-A                     |     -0.551 | -.449*           |
| TMT-B                     |      0.169 | .138             |
| Category Fluency          |     -0.2   | -.163            |

Table S: Canonical loadings and cross-loadings for each variable in the untransformed cognitive dataset (U). *Associated with the first canonical variate V1 at the 0.3 threshold.

|                                   | Left hemisphere   | Left hemisphere   | Right hemisphere   | Right hemisphere   |
|-----------------------------------|-------------------|-------------------|--------------------|--------------------|
| Region                            | rho               | p                 | rho                | p                  |
| Banks of Superior Temporal Sulcus | .085              | .535              | .204               | .131               |
| Caudal Anterior Cingulate         | -.137             | .315              | .037               | .785               |
| Caudal Middle Frontal             | -.085             | .533              | -.165              | .224               |
| Cuneus                            | -.120             | .380              | .110               | .418               |
| Entorhinal                        | -.263             | .05               | -.349              | .008               |
| Fusiform                          | -.113             | .406              | -.205              | .129               |
| Inferior Parietal                 | .019              | .887              | .110               | .420               |
| Inferior Temporal                 | -.298             | .026              | -.307              | .021               |
| Isthmus Cingulate                 | -.096             | .48               | -.198              | .143               |
| Lateral Occipital                 | -.019             | .89               | -.223              | .099               |
| Lateral Orbitofrontal             | .137              | .313              | -.137              | .315               |
| Lingual                           | -.115             | .398              | -.287              | .032               |
| Medial Orbitofrontal              | -.080             | .558              | -.309              | .021               |
| Middle Temporal                   | .059              | .667              | -.056              | .684               |
| Parahippocampal                   | -.114             | .403              | -.148              | .277               |
| Paracentral                       | -.282             | .035              | -.203              | .134               |
| Pars Opercularis                  | -.249             | .064              | -.027              | .844               |
| Pars Orbitalis                    | -.078             | .568              | -.151              | .268               |
| Pars Triangularis                 | .073              | .595              | -.216              | .110               |
| Pericalcarine                     | -.064             | .639              | -.188              | .166               |
| Postcentral                       | -.022             | .872              | -.078              | .565               |
| Posterior Cingulate               | -.179             | .186              | -.045              | .741               |
| Precentral                        | -.186             | .169              | -.114              | .403               |
| Precuneus                         | -.138             | .309              | -.113              | .409               |
| Rostral Anterior Cingulate        | -.198             | .144              | -.036              | .793               |
| Rostral Middle Frontal            | -.127             | .351              | -.133              | .327               |
| Superior Frontal                  | -.216             | .110              | -.149              | .274               |
| Superior Parietal                 | -.153             | .260              | -.113              | .406               |
| Superior Temporal                 | -.077             | .575              | -.229              | .090               |
| Supramarginal                     | -.159             | .242              | -.009              | .950               |
| Frontal pole                      | -.222             | .101              | .032               | .816               |
| Temporal pole                     | -.091             | .505              | -.203              | .133               |
| Transverse Temporal               | -.026             | .849              | -.012              | .929               |
| Insula                            | -.314             | .018              | -.169              | .213               |

Table S: Cross-loadings for each region in dataset V1 with untransformed cognitive dataset U1.

## 9 References

1.	Van Essen DC, Smith SM, Barch DM, Behrens TEJ, Yacoub E, Ugurbil K. The WU-Minn Human Connectome Project: An overview. *Neuroimage* . 2013;80:62-79. doi:10.1016/j.neuroimage.2013.05.041

2.	Dufumier B, Grigis A, Victor J, Ambroise C, Frouin V, Duchesnay E. OpenBHB: a Large-Scale Multi-Site Brain MRI Data-set for Age Prediction and Debiasing. *Neuroimage* . 2022;263(September):119637. doi:10.1016/j.neuroimage.2022.119637

3.	LaMontagne PJ, Benzinger TL, Morris JC, et al. OASIS-3: Longitudinal Neuroimaging, Clinical, and Cognitive Dataset for Normal Aging and Alzheimer Disease. *medRxiv* . Published online 2019. doi:10.1101/2019.12.13.19014902

4.	Taylor JR, Williams N, Cusack R, et al. The Cambridge Centre for Ageing and Neuroscience (Cam-CAN) data repository: Structural and functional MRI, MEG, and cognitive data from a cross-sectional adult lifespan sample. *Neuroimage* . 2017;144:262-269. doi:10.1016/j.neuroimage.2015.09.018

