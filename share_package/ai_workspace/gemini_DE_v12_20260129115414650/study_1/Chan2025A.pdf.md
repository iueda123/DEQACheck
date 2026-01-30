![Image](./Chan2025A_artifacts/image_000000_6b1b8625b769f27703e2e60f691cf84d823d04dc257b3fbf0d5df7bb15a8e342.png)

Contents lists available at ScienceDirect

## Developmental Cognitive Neuroscience

journal homepage: www.elsevier.com/locate/dcn

## Social behavior in ASD males: The interplay between cognitive flexibility, working memory, and functional connectivity deviations ☆

![Image](./Chan2025A_artifacts/image_000001_35ea5ff483daecb25242fa06d5f02e8f92030bafa6131eca38ffa1858181297d.png)

Shi Yu Chan a , Jasmine Si Min Chuah a , Pei Huang a , Ai Peng Tan

a,b,c,*

- a Institute for Human Development and Potential (IHDP), Agency for Science, Technology and Research (A*STAR), 30 Medical Dr, Singapore 117609, Singapore
- b Yong Loo Lin School of Medicine, National University of Singapore (NUS), 10 Medical Dr, Singapore 117597, Singapore
- c Department of Diagnostic Imaging, National University Health System, 1E Kent Ridge Rd, Singapore 119228, Singapore

A R T I C L E I N F O

Keywords: Autism spectrum disorders Functional connectivity Generalized additive models Normative Trajectories Social behavior Executive functioning

## 1. Introduction

Autism spectrum disorder (ASD) is a highly heterogeneous neurodevelopmental disorder (ADDM and CDC, 2014) characterized by behaviorally defined impairments in social skills and the occurrence of restricted interests and repetitive behaviors (American Psychiatric Association, 2013). The extant literature on infant sibling studies of ASD indicates that these diagnostic symptoms gradually emerge over the first few years of life (Ozonoff et al., 2010) and are often not specific to ASD in the early stages (Ozonoff et al., 2008). The inclusion of four "open" specifiers (intelligence, language, severity, and comorbidity) and their possible combinations further complicates the heterogeneity of individuals within this diagnostic label. The heterogeneity of ASD is not only displayed at the phenotypic level, but also at the neuroimaging (Martinez-Murcia et al., 2017) and etiological (Jeste and Geschwind, 2014) levels. A better understanding of the neurobiological basis of behavioral difficulties associated with ASD will carve up the etiological

## A B S T R A C T

Autism spectrum disorder (ASD) is highly heterogeneous in presentation. While abnormalities in brain functional connectivity are consistently observed in autistic males, the neurobiological basis underlying the different domains of autism symptoms is unclear. In this study, we evaluated whether individual variations in functional connectivity deviations map to social behavior in ASD males. Using neuroimaging data from the Autism Brain Imaging Data Exchange (ABIDE), we modeled normative trajectories of between-network resting-state functional connectivity (rsFC) in non-ASD males across childhood (n = 321). These normative charts were then applied to ASD males (n = 418) to calculate individual deviation scores (z-scores) that reflect the degree of rsFC atypicality. Deviations in rsFC patterns among the default mode network (DMN), ventral attention network (VAN), frontoparietal network (FPN), and somatomotor network (SMN) were associated with distinct dimensions of social behavior. Cognitive flexibility and working memory mediated the association between VANxDMN z-scores and social behavioral problems. Our findings underscore the potential of normative models to identify atypical brain connectivity at an individual level, revealing the neurobiological patterns associated with social behavioral problems in ASD that are critical for precision diagnosis and intervention. Social outcomes in ASD males may be improved by targeting cognitive flexibility and working memory.

heterogeneity of ASD and open windows of opportunity for precision diagnosis, potentially forming a critical scaffold for the development of mechanistically targeted treatments.

While early behavioral markers lack the necessary sensitivity and specificity to accurately predict subsequent diagnoses of ASD, the existence of these early behavioral changes imply that aberrant brain development precedes the onset of readily diagnosable symptoms. Studies of neurodevelopmental disorders are now increasingly focused on understanding how disturbances in large-scale brain networks contribute to cognitive and affective dysfunctions (Menon, 2011). Of note, the ventral attention network (VAN), fronto-parietal network (FPN), default mode network (DMN), and the connectivity between them are associated with internally and externally-directed cognitive processing and social behavior, areas that are affected in ASD. For example, atypical interactions between the DMN and VAN might contribute to a lack of engagement with social stimuli in ASD (Menon, 2011). Neuroimaging research has uncovered a wealth of evidence that

☆ Location of work: Translational Neuroscience, Institute for Human Development and Potential, 30 Medical Drive, Singapore 117609

* Corresponding author at: Department of Diagnostic Imaging, National University Health System, 1E Kent Ridge Rd, Singapore 119228, Singapore. E-mail address: dnrtanap@nus.edu.sg (A.P. Tan).

![Image](./Chan2025A_artifacts/image_000002_621f2f4b78da3a7002888729029552a470a76e4958e06c7e5cbd75f580c41093.png)

![Image](./Chan2025A_artifacts/image_000003_0d2893016a2590b58a23ec695f85b5ab75f4d002a4ed4c4fe76067e2664401af.png)

has led to an emerging model of abnormal brain connectivity in ASD (Ecker et al., 2015; Hernandez et al., 2015; Rane et al., 2015). Evidence from electrophysiology and functional neuroimaging (Vissers et al., 2012), as well as molecular genetics (Hashem et al., 2020), also support the tenet that ASD is characterized by atypical neural connectivity. Between-network connectivity increases with age in neurotypical individuals (Power et al., 2010), but not in autistic individuals (Padmanabhan et al., 2017). Therefore, it is plausible that deviations from normative trajectories of network development may underlie social behavioral problems in autistic individuals. Functional connectivity-based measures are likely to be a more proximal measure of behavior relative to structural-based measures, and thus potentially a more reliable biomarker (Ooi et al., 2022). In addition, functional-based measures are more dynamic and can potentially act as targets for intervention. For example, both cognitive behavioral therapy and trans-cranial magnetic stimulation have shown efficacy in altering functional connectivity as interventions for psychiatric disorders (Corlier et al., 2019; Sandman et al., 2020). Thus, we focus on between-network resting-state functional connectivity (rsFC) in our current study. To our knowledge, the present study is the first to directly investigate whether between-network connectivity in autistic individuals deviates from normative developmental trajectories.

One of the primary obstacles impeding the clinical application of neurobiological markers is our inability to utilize these indicators for individual-level assessment. Normative modeling is an established technique for providing inferences at the level of the individual and has proved useful in dissecting the heterogeneity across clinical cohorts (Bethlehem et al., 2020; Wolfers et al., 2020; Zabihi et al., 2019). It is an appealing approach considering that many brain disorders stem from atypical brain development (Insel, 2014). Thus, deviations from a normative trajectory may reflect atypical brain development at a specific time-point. Understanding individual variations in such deviations allow us to potentially identify subgroups that share similar neurobiological patterns, that may in turn explain the heterogeneity observed in clinical symptoms. Prior work on the construction of normative models has focused on structural features such as cortical thickness and regional volume (Bethlehem et al., 2020; Rutherford et al., 2022). However, changes in brain functional connectivity, which are central to the underlying mechanisms of ASD, are not well known. Establishing the link between functional connectivity alterations and ASD symptoms at an individual level will form the critical first step for precision diagnosis and treatment in ASD.

Executive function is a complex multifactorial construct (Sylvester et al., 2003) that involves various cognitive processes, including planning, cognitive flexibility, working memory, inhibition, initiation, and monitoring of action (Miyake and Friedman, 2012; Miyake et al., 2000). Executive dysfunction is evident in various fundamental aspects of ASD, encompassing both social and non-social domains. Autistic individuals have reported difficulties with planning (Hill, 2004), cognitive flexibility (Ozonoff and Jensen, 1999), working memory (Barendse et al., 2013), and monitoring of action (Russell and Jarrold, 1998). Theory of mind, which is believed to be core to socio-communication difficulties in ASD, is also strongly interconnected with executive function. It has been suggested that the development of executive functions facilitates the emergence of theory of mind (Prior and Hoffmann, 1990). Hence, atypical executive function development could also contribute to social behavioral difficulties observed in autistic children. Another perspective argues that strong executive function protects children who are at increased likelihood of developing neurodevelopmental disorders such as ASD by compensating for deficits in other brain systems (Johnson, 2012). Given that alterations in rsFC have also shown associations with executive dysfunction (Reineberg et al., 2018), inter-individual differences in rsFC may have an impact on both executive function and social behavior. Despite extensive evidence linking both executive dysfunction and alterations in brain connectivity with social difficulties in ASD, a unifying model which links these three factors is still not fully established. There is also a lack of consensus as to which executive function domains are implicated in social behavioral problems observed in children with ASD, in relation to the alterations in brain connectivity.

We posit that social behavioral problems observed in children with ASD are attributed to deviations in between-network rsFC from the established norm. To test this hypothesis, we used data from the ABIDE consortium (Di Martino et al., 2014). We focused exclusively on males due to the increased prevalence of ASD in males (Werling and Geschwind, 2013) providing us with a greater study sample size and the sex differences observed in neurodevelopmental trajectories (Bethlehem et al., 2022) that would make it inadvisable to study males and females as one cohort. We first generated normative trajectories of between-network rsFC from 321 typically developing males between ages 5 and 14 years using generalized additive models. We then aligned these normative charts to rsFC measures of 418 males with ASD to quantify between-network rsFC deviations at the level of individual participants. Next, we examined the associations between rsFC deviations and distinct domains of social behaviors using sparse canonical correlation analysis. Executive function predicts performance on theory of mind tests but not the reverse (Hughes, 1998). This suggests that social behavioral problems, which are closely associated with deficiencies in theory of mind, can also be partially attributed to difficulties with executive functioning. Hence, we also examined whether executive function mediated the association between atypical rsFC and social behaviors in 151 ASD males. We were interested in exploring this pathway given the potential of executive function as a modifiable target for intervention. We hypothesized that executive dysfunction would significantly mediate the association between rsFC deviations and social difficulties. Finally, a follow-up analysis to examine the mediating role of eight executive function domains was performed.

## 2. Materials and methods

The study design and analysis numbers are depicted in Fig. 1.

## 2.1. Cohort

Data for the current study were from the Autism Brain Imaging Data Exchange (ABIDE) I and ABIDE II collections of the ABIDE initiative (https://fcon\_1000.projects.nitrc.org/indi/abide), a publicly available dataset of participants diagnosed with ASD and age-matched non-ASD participants. Data collected include resting-state fMRI (rs-fMRI), demographic, and clinical assessment data. Supplementary Table 1 summarizes the site-specific inclusion and exclusion criteria. Given the prevalence of ASD in males (Werling and Geschwind, 2013) and the focus on neurodevelopment, only males between ages 5.1 -14.0 years were included in this study (n = 816, Supplementary Fig 1). The study dataset was then split into training and testing datasets. The training dataset was composed of 80 % of non-ASD males, while the testing dataset was composed of the remaining 20 % of non-ASD males and ASD males. Age range was similar between training and testing datasets (Table 1; Supplementary Fig 2). Specific data on socioeconomic status and educational attainment levels were not recorded. As expected, ASD males had greater social behavioral problems and lower executive functioning performance relative to non-ASD males.

## 2.2. Resting-state functional MRI acquisition and pre-processing

Supplementary Table 2 lists the site-specific MRI scanners and acquisition parameters.

Resting-state fMRI (rs-fMRI) data was processed as previously described (Chan et al., 2024), with the default pre-processing and de-noising pipelines using the CONN Toolbox v20b (Whitfield-Gabrieli and Nieto-Castanon, 2012). Scans underwent realignment with SPM12 realign &amp; unwarp procedure (Andersson et al., 2001). Temporal misalignment between different slices of the functional data was

Fig. 1. Study Design. 21 Between-network functional connectivity matrices were derived for males in the ABIDE cohort (age range: 5.1 -14 years, n = 816). 80 % of non-ASD males were assigned to the training dataset (n = 321) to model normative trajectories of between-network FC. Normative models were then applied to the testing dataset (n = 77 non-ASD males; n = 418 ASD males) to derive deviation scores (z). Sparse canonical correlation analysis (CCA) was performed to identify the FC deviation most correlated with social behavioral problems in ASD males. Finally, a mediation analysis was performed to assess whether executive functioning mediated the association between FC deviation and social behavioral problems.

![Image](./Chan2025A_artifacts/image_000004_86ae29d9d65105db587682674aaca1cbd2a7e0dd086420ec0018fb39082898f6.png)

Table 1 Phenotypic characteristics of the study cohort stratified into training/testing dataset and diagnosis.

|                                    |                                    | Dataset                        |                            |                            |
|------------------------------------|------------------------------------|--------------------------------|----------------------------|----------------------------|
|                                    |                                    | Training N = 321               | Testing (Control) N = 77   | Testing (ASD) N = 418      |
| Age at scan (years)                |                                    |                                |                            |                            |
| Mean ( ± SD)                       |                                    | 10.6 ( ± 1.8)                  | 10.8 ( ± 1.6)              | 10.4 ( ± 2.1)              |
| Range                              |                                    | 5.9 - 13.9                     | 6.5 - 13.8                 | 5.1 - 14.0                 |
| Full IQ                            |                                    |                                |                            |                            |
| Mean ( ± SD)                       |                                    | 113.2 ( ± 12.6)                | 112.1 ( ± 13.7)            | 105.1 ( ± 17.5)            |
| Range                              |                                    | 79 - 148                       | 78 - 142                   | 61 - 149                   |
| Social Responsiveness Scale (SRS)  |                                    |                                |                            |                            |
| Total                              | Mean ( ± SD)                       | 43.8 ( ± 5.8)                  | 42.8 ( ± 6.6)              | 76.3 ( ± 13.2)             |
|                                    | Range Mean                         | 34 - 61                        | 34 - 66 42.8 ( ± 8.9)      | 42 - 116                   |
| Awareness                          | ( ± SD)                            | 46.3 ( ± 9.2)                  |                            | 69.5 ( ± 12.1)             |
| subscale                           | Range                              | 30 - 70                        | 30 - 72                    | 39 - 97                    |
| Communication                      | Mean ( ± SD)                       | 44.0 ( ± 5.9)                  | 43.3 ( ± 6.9)              | 74.1 ( ± 13.3)             |
| subscale                           | Range                              | 36 - 64                        | 36 - 69                    | 39 - 104                   |
| Cognition                          | Mean ( ± SD)                       | 43.4 ( ± 6.2)                  | 42.8 ( ± 5.7)              | 72.4 ( ± 13.1)             |
| subscale                           | Range                              | 36 - 63                        | 36 - 68                    | 39 - 108                   |
| Motivation                         | Mean ( ± SD)                       | 45.5 ( ± 6.2)                  | 46.1 ( ± 7.4)              | 70.3 ( ± 14.8)             |
| subscale                           | Range                              | 37 - 63                        | 37 - 70                    | 38 - 113                   |
| Mannerisms                         | Mean ( ± SD)                       | 44.9 ( ± 5.7)                  | 43.5 ( ± 4.5)              | 77.5 ( ± 15.7)             |
| subscale                           | Range                              | 40 - 74                        | 40 - 62                    | 40 - 121                   |
| BRIEF - Global Executive Composite | BRIEF - Global Executive Composite |                                |                            |                            |
| Mean ( ± SD)                       |                                    | 44.2 ( ± 8.4)                  | 41.7 ( ± 8.2)              | 66.4 ( ± 10.5)             |
| Range                              |                                    | 30 - 72                        | 30 - 64                    | 35 - 90                    |
| fMRI Mean Relative Motion (mm)     | fMRI Mean Relative Motion (mm)     | fMRI Mean Relative Motion (mm) |                            |                            |
| Mean ( ± SD) Range                 |                                    | 0.2 ( ± 0.1) 0.046 - 0.414     | 0.2 ( ± 0.1) 0.032 - 0.406 | 0.2 ( ± 0.1) 0.048 - 0.502 |

corrected using SPM12 slice-timing correction (STC) procedure (Henson et al., 1999). Scan volumes with framewise displacement above 0.9 mm or global BOLD signal changes above 5 standard deviations were identified as potential outliers. Functional and anatomical data were normalized into standard MNI space and segmented into grey matter, white matter, and CSF tissue classes using SPM12 unified segmentation and normalization procedure (Ashburner and Friston, 2005). Functional data was smoothed using spatial convolution with a Gaussian kernel of 6 mm full width half maximum (FWHM), and the first four scans excluded to allow for magnetic field saturation. For denoising, BOLD signal variance over time explained by nuisance variables, including scan volumes identified as potential outliers, was removed from the data using Ordinary Least Squares regression, and the BOLD time series were band-pass filtered to preserve only frequencies between 0.008 and 0.09 Hz (Biswal et al., 1995; Fox and Raichle, 2007). Mean relative motion over all volumes was extracted and included as a co-variate in normative models.

## 2.3. ROIs

Regions of interest (ROIs) were seven large scale cortical restingstate networks [ Visual Network (VN), Somatomotor Network (SMN), Dorsal Attention Network (DAN), Ventral Attention Network/Salience Network (VAN), Limbic Network (LN), Frontoparietal Network (FPN) and Default Mode Network (DMN )], identified by Yeo et al. (2011). For each scan, functional connectivity matrices were computed by measuring the bivariate correlation coefficients of the BOLD time series between each seed and target ROIs through a hemodynamic response factor (hrf)-weighted general linear model. To account for site effects, we employed the ComBat harmonization method (Supplementary

Tables 3a-b; Supplementary Fig 3) with the neuroCombat package v1.0.13 (Fortin et al., 2018). With seven functional networks as ROIs, we have a total of 21 unique between-network rsFCs.

## 2.4. Behavioral outcomes

## 2.4.1. Social responsiveness scale (SRS)

The SRS (Constantino and Gruber, 2005) was used to measure parent-rated deficits in social behavior. The questionnaire consists of 65 items that are grouped into five subscales; (1) social awareness, (2) social cognition, (3) social motivation, (4) social communication, and (5) mannerisms (also known as restricted interests and repetitive behavior). Items are scored on a 4-point Likert scale (1 = Not true, 4 = Almost always true), with a higher score indicating greater problems with social behavior associated with ASD. The T-scores for SRS total and all 5 subscales were used in our analyses, as they are normalized for age and sex.

## 2.4.2. Behavior rating inventory of executive function (BRIEF)

The BRIEF (Gioia et al., 2000) is an 86-item questionnaire used to assess executive function performance in participants aged between 5 and 18 years. Each item is scored on a 3-point Likert scale. Higher scores indicate poorer executive function performance. BRIEF consists of eight clinical subscales, (1) Inhibit, (2) Shift (Cognitive Flexibility), (3) Emotional Control, (4) Initiate, (5) Working Memory, (6) Plan/Organize, (7) Organization of Materials and (8) Monitor. The General Executive Composite (GEC) score is the sum of these 8 clinical subscale scores. Raw scores were linearly transformed to obtain T-scores, which were used in our analyses.

## 2.5. Statistical analysis

All statistical analyses were conducted in R v4.3.0 (R Core Team, 2023). Alpha level was set at p &lt; 0.05 (two-tailed unless stated otherwise).

## 2.5.1. Normative modeling and deviation score calculation

For each between-network rsFC ( FCi ), normative trajectories were modeled with Generalized Additive Models (GAMs) for the training dataset using the mgcv package v1.8 -42 (Wood, 2011). To model possible non-linear trajectories, a smoothing function was applied to age (predictor), where the effective degrees of freedom (edf) statistic reflects the degree of non-linearity of a curve. Mean relative motion was included as a co-variate of no interest. The suitability of model parameters was assessed with the GAM.check function. Details of GAM models are reported in Supplementary Table 4.

<!-- formula-not-decoded -->

The normative model was then applied to the testing dataset. Between-network FC was predicted in the testing dataset ( ̂ FCi ). Deviation scores (z) were then calculated by subtracting predicted betweennetwork FC from actual between-network FC, and dividing the difference by the training dataset standard deviation (population standard deviation). These z scores reflect the individual degree of betweennetwork FC atypicality from a typically developing population mean.

Deviation Equation

$$: zi = ( FCi GLYPH<0> FCi ) /$$

̂ SD

## 2.5.2. Sparse canonical correlation analysis

Sparse canonical correlation analysis (CCA) was performed with the PMA package v1.2.1 (Witten et al., 2009). Only ASD patients were included in this analysis. CCA is an analysis technique used to identify linear combinations of two sets of variables (X, Y) that are maximally correlated with each other (Witten and Tibshirani, 2009). In sparse CCA, a penalty is applied such that only the most relevant variables are included, with the contribution of other variables reduced to 0. The best

penalty for each dataset (Xp, Yp) was determined with the CCA.permute function using penalized matrix decomposition (number of permutations = 1000). Sparse CCA was then performed with the best penalties applied. The number of canonical components was set at 5 to match the 5 SRS subscales that constitute dataset Y.

## 2.5.3. Mediation analysis

The mediation package v4.5.0 (Tingley et al., 2014) was used to assess whether executive function mediated the association between between-network deviation scores and social behavioral problems. Bootstrap simulations were set at n = 5000. For the follow-up analysis, bonferroni correction was performed to correct for multiple comparisons over the 8 executive function domains for an adjusted alpha level of

Fig. 2. Normative models and deviation score calculation. For each between-network FC, the left panel shows the normative trajectory from age 5.1 -14 years trained on non-ASD males (n = 321). Trajectories are displayed as mean predicted FC (dark blue line) + /- 95 % confidence interval (grey). Individual FC is plotted as points (dark blue). The right panel is a bar chart showing the spread of deviation scores in the testing dataset. Each bar is an individual colored by diagnosis (yellow - ASD males, n = 418; dark blue - non-ASD males, n = 77).

![Image](./Chan2025A_artifacts/image_000005_db00d73465e4425ecdc2c9d184eb0cad0cd0acf73b3fd5939397b82c08067ccf.png)

Fig. 2. ( continued ).

![Image](./Chan2025A_artifacts/image_000006_b1f71f557b8ba0d0c683e077e34dcbc5d57c73a5d53f9e84590b1cbc3750ec51.png)

network deviation scores and SRS subscales (Table 2). Thus, a more positive deviation was associated with greater problems with social behavior. The deviation scores for three between-network FC (VANxDMN, SMNxFPN, and VANxFPN) were identified to be associated with social behavioral problems. Of note, the deviation score for VANxDMN (VANxDMN\_z) was highly correlated with three SRS subscales - Social Awareness (Component 1, r = 0.272), Social Communication (Component 2, r = 0.215), and Social Cognition (Component 5, r = 0.200). Score plots between VANxDMN\_z and SRS subscales are shown in Supplementary Fig 4.

We assessed whether the three deviation scores were significantly different between ASD and non-ASD males in the testing dataset using the Mann Whitney Wilcoxon test. We observed a trending difference for VANxDMN (W = 18109, p = 0.081), but not for SMNxFPN or VANxFPN

Table 2 Summary of the 5 canonical components showing the strongest associations between rsFC deviation scores and subscales of social behavioral problems.

|   Component |   Correlation | Deviation   | Weight     | Behavior      | Weight     |
|-------------|---------------|-------------|------------|---------------|------------|
|           1 |         0.272 | VANxDMN     | 1          | SRS Awareness | 1          |
|           2 |         0.215 | VANxDMN     | 1          | SRS           | 1          |
|           3 |         0.214 | SMNxFPN     | 1          | SRS Cognition | 1          |
|           4 |         0.22  | VANxFPN     | GLYPH<0> 1 | SRS Awareness | GLYPH<0> 1 |
|           5 |         0.2   | VANxDMN     | 1          | SRS Cognition | 1          |

p-corrected &lt; 0.00625.

## 3. Results

## 3.1. Normative modeling of between-network rsFC

Normative trajectories were derived for each between-network rsFC based on data from the training dataset (n = 321, Fig. 2 1st Panel). Our findings showed that the trajectories were largely linear across the study age range. These models were then applied to the testing dataset (n = 495), and the deviation between the predicted FC and actual FC in the testing dataset was calculated (Fig. 2 2nd Panel). We observe both positive and negative deviations for the ASD cohort, showing the heterogeneity of FC values among the ASD cohort.

## 3.2. FC Deviation scores were significantly correlated with SRS subscales

We used sparse CCA to identify which of our between-network deviation scores were most strongly correlated with social behavioral problems, assessed by five SRS subscale scores. A value of 0.1 was identified as the best penalty (Supplementary Table 5) and applied to both sets of data. Thus, the output of the analysis was 5 components/ pairs of variables, each consisting of a single between-network deviation score that was most highly correlated with a SRS subscale.

All five components showed a positive correlation between between-

(SMNxFPN: W = 15051, p = 0.367; VANxFPN: W = 14867, p = 0.288; Supplementary Fig 5), showing the limitations in case-control comparisons in a heterogeneous population.

## 3.3. Mediating role of executive function

We then examined if executive function significantly mediates the association between between-network deviation scores and social behavioral problems, assessed by the SRS total score. We focused on VANxDMN\_z given its relevance to multiple dimensions of social behavioral problems. We found that a measure of general executive function (Global Executive Composite [GEC] assessed by the BRIEF) partially mediated the association between VANxDMN\_z and SRS total

Table 3 Summary of mediation analysis results assessing whether BRIEF scores mediated the association between VANxDMN deviation scores and social behavioral problems.

| Mediator    | Output             | Estimate    | 95 % CI Lower   | 95 % CI Upper   | p-value                 |
|-------------|--------------------|-------------|-----------------|-----------------|-------------------------|
| GEC         | ACME               | 2.956       | 1.059           | 5.4             | 0.0028                  |
|             | ADE                | 3.297       | 0.974           | 6.05            | 0.0068                  |
|             | Total              | 6.253       | 3.309           | 10.1            | < 2e GLYPH<0> 16        |
|             | Effect Prop.       | 0.473       | 0.236           | 0.81            | 0.0028                  |
| Domain 1:   | ACME               | 1.77        | 0.488           | 3.54            | 0.0152                  |
| Inhibition  | ADE                | 4.483       | 1.796           | 8.06            | 0.0024                  |
|             | Total              | 6.253       | 3.309           | 10.1            | < 2e GLYPH<0> 16        |
|             | Effect Prop. Med   | 0.283       | 0.071           | 0.56            | 0.0152                  |
| Domain 2:   | ACME               | 2.839       | 0.980           | 5.13            | 0.0048                  |
| Cognitive   | ADE                | 3.414       | 1.136           | 6.32            | 0.0032                  |
| Flexibility | Total Effect       | 6.253       | 3.309           | 10.1            | < 2e GLYPH<0> 16        |
| Flexibility | Prop. Med          | 0.454       | 0.100           | 0.69            | 0.0048                  |
| Domain 3:   | ACME               | 0.926       | GLYPH<0> 0.815  | 2.98            | 0.3116                  |
| Emotional   | ADE                | 5.327       | 3.014           | 8.15            | 0.0004                  |
| Control     | Total Effect       | 6.253       | 3.309           | 10.1            | < 2e GLYPH<0> 16        |
| Control     | Prop. Med          | 0.148       | GLYPH<0> 0.204  | 0.4             | 0.3116                  |
| Domain 4:   | ACME               | 1.475       | 0.135           | 3.370           | 0.042                   |
| Initiate    | ADE                | 4.778       | 2.041           | 8.280           | 0.0012                  |
| Initiate    | Total Effect Prop. | 6.253 0.236 | 3.309 0.044     | 10.1 0.620      | < 2e GLYPH<0> 16 0.042  |
| Domain 5:   | ACME               | 2.258       | 0.698           | 4.460           | 0.0044                  |
| Working     | ADE                | 3.995       | 1.163           | 7.570           | 0.009                   |
| Memory      | Total              | 6.253       | 3.309           | 10.1            | < 2e GLYPH<0> 16        |
| Memory      | Effect Prop. Med   | 0.361       | 0.131           | 0.790           | 0.0044                  |
| Domain 6:   | ACME               | 1.224       | 0.040           | 3.020           | 0.058                   |
| Plan        | ADE                | 5.030       | 2.278           | 8.390           | 0.001                   |
|             | Total              | 6.253       | 3.309           | 10.1            | < 2e GLYPH<0> 16        |
|             | Effect Prop.       | 0.196       | 0.020           | 0.520           | 0.058                   |
| Domain 7:   | Med ACME           | 0.397       | GLYPH<0> 0.153  | 1.460           | 0.230                   |
| Organize    | ADE                | 5.857       | 3.055           | 9.58            | < 2e GLYPH<0> 16        |
| Organize    | Total Effect       | 6.253       | 3.309           | 10.1            | < 2e GLYPH<0> 16        |
| Domain 8:   | Prop. Med          | 0.063       | GLYPH<0> 0.016  | 0.290           | 0.230                   |
|             | ACME               | 2.186       | 0.716           | 4.22            | 0.0068                  |
| Monitor     | ADE                | 4.067       | 1.487           | 7.15            | 0.0056                  |
|             | Total Effect Prop. | 6.253 0.35  | 3.309 0.098     | 10.1 0.62       | < 2e GLYPH<0> 16 0.0068 |

score (Table 3, ACME = 2.956, p = 0.0028). As follow-up analysis, we then explored whether this mediation effect was observed for specific executive function domains that make up the GEC. Only the cognitive flexibility (Table 3, ACME = 2.839, p = 0.0048) and working memory domains (Table 3, ACME = 2.258, p = 0.0044) passed the adjusted alpha level of p &lt; 0.00625. The remaining 6 domains had p-values between 0.0068 and 0.3116 (Table 3).

We performed sensitivity analyses correcting for IQ and observed similar mediation estimates (Supplementary Table 6).

## 4. Discussion

Our study used normative modeling to explore between-network rsFC heterogeneity in ASD males at an individual level, presenting a potential route toward precision medicine in neurodevelopmental disorders. Deviations in rsFC patterns among the default mode network (DMN), ventral attention network (VAN), frontoparietal network (FPN), and somatomotor network (SMN) were found to be significantly associated with distinct dimensions of social behavior, supporting the notion that impairments in integrative and multisensory processing are linked to social dysfunctions in ASD. We posit that atypical between-network rsFC represents altered information integration across distributed brain networks in autistic individuals. Notably, VAN-DMN rsFC deviation scores were found to be associated with multiple domains of social behavior, including social awareness, social communication, and social cognition. Our study also revealed that cognitive flexibility and working memory significantly mediated the association between VAN-DMN rsFC deviation and social difficulties in ASD males, highlighting these executive function domains as potential targets to mitigate the downstream adverse effects of rsFC deviations to improve outcomes in ASD males.

Our normative modeling method forges an important connection between rsFC deviations and their role in social behavior in ASD, mapping the heterogeneous ASD phenotype at the level of the individual. We showed that when individuals show greater rsFC deviations from normative trajectories, they exhibit a higher level of aberrant social behavior. Normative modeling of between-network rsFC is a well-suited approach for conditions with a high degree of heterogeneity, such as ASD. Heterogeneity of ASD is the most significant obstacle towards the implementation of precision medicine and can be observed on at least three levels: (1) heterogeneity as a consequence of different symptom profiles that are classified under the same disorder (clinical heterogeneity), (2) heterogeneity induced by different biological predispositions converging on the same symptoms (biological heterogeneity), and (3) different environmental events that cause (or prevent) the same symptoms (environmental heterogeneity). Normative modeling explicitly characterizes and quantifies the heterogeneity underlying ASD at a higher resolution than is afforded by case-control analyses, which focus on group averages, thereby seeking a consistent pattern of atypicality (i. e., the 'average patient ' ). In case-control analyses, heterogeneity becomes apparent via inflation of the model residuals and ultimately decreases sensitivity for detecting condition-related effects.

The degree of deviation of VAN-DMN rsFC was significantly associated with three closely related domains of social behavior: social cognition, social awareness, and social communication. The substantial impact of VAN-DMN rsFC deviation is to be expected, considering the significant involvement of the DMN and VAN in ASD behavior. The DMN is one of the most extensively researched functional connectivity networks in ASD and has emerged as a key system underlying social dysfunction in ASD (Buckner et al., 2008; Uddin et al., 2013a,2013b). Altered connectivity between DMN nodes likely underlies difficulties in self-referential cognitive processing and inferring the mental states of others (Assaf et al., 2010; Monk et al., 2009; Padmanabhan et al., 2017). The VAN modulates the switch between the DMN which is involved in internally directed cognition and the FPN which governs externally directed cognition (Stevens et al., 2009). Atypical interactions between the DMN and VAN might contribute to a lack of engagement with

socially relevant stimuli, as posited by the triple network model of psychopathology (Menon, 2011). Interestingly, we also observed that FPN-VAN rsFC deviation scores were significantly associated with impairments in social awareness, further reinforcing the notion that altered connectivity among the triple networks significantly impacts one ' s ability to assign appropriate saliency and direct attention to social stimuli. A previous study reported the presence of intrinsic functional hyper-connectivity within the VAN, FPN, and DMN in ASD individuals, resulting in a phenomenon of ' network isolation, ' which limits dynamic between-network interactions that are necessary for complex social behaviors (Uddin et al., 2013a,2013b). FPN-SMN rsFC deviation scores were significantly associated with social cognition, a diverse group of cognitive mechanisms that facilitate the interpretation of social stimuli and the subsequent enactment of acceptable social behaviors. These mechanisms require the interplay between sensory processing in which the SMN plays a crucial role and cognitive control of behavior which is supported by the FPN. Atypical functional connectivity of sensorimotor regions has been reported in ASD (Anderson et al., 2011; Mostofsky et al., 2009). Atypical between-network rsFC was not able to account for the social motivation domain. Future studies should consider examining deviations of subcortical-cortical connectivity, with an emphasis on brain structures related to reward processing such as the nucleus accumbens and the orbitofrontal cortex (Chan et al., 2022).

In this study, we found that cognitive flexibility and working memory mediate the association between VAN-DMN rsFC deviation scores and social behavior in ASD males. Specifically, a greater degree of VANDMN rsFC atypicality, along with poorer cognitive flexibility and working memory, are associated with more pronounced difficulties in social function. Cognitive flexibility is essential for social interaction as it enables an individual to accurately assess the situation and tailor their responses in an appropriate manner. Working memory on the other hand plays a crucial role in the processing of social information, as information received needs to be stored temporarily for it to be comprehended and subsequently used to plan appropriate responses during interactions with others. Also, the ability to hold two conflicting perspectives on the same stimulus is a prerequisite for promoting the development of social cognition (Gordon and Olson, 1998). Impairments of the phonological loop, a component of the working memory model, will impact one ' s ability to understand and carry out conversations (Baddeley, 1992). Additionally, since working memory is responsible for the temporary storage of information before it can be utilized, it is a crucial component to enable cognitive flexibility. More importantly, our findings highlight cognitive flexibility and working memory as potential targets for intervention to improve social outcomes in ASD males, although these findings may be provisional and have to be validated in independent ASD cohorts.

In the present study, we modeled normative trajectories of betweennetwork rsFC from cross-sectional data using age as a proxy for time. While this approach offers a high level of statistical power and a broad age range, a recent study reported that developmental trajectories inferred from cross-sectional data significantly underestimate actual changes measured longitudinally (Di Biase et al., 2023). Therefore, there is a need for future prospective longitudinal studies to facilitate the calibration of our cross-sectional models and enhance the precision of individual and future inferences. Genetic and environmental factors that may contribute to the prediction errors of normative models were not considered in the current study, as these information were not collected in the ABIDE cohort. These factors should be more fully addressed in future studies. Multi-site MRI acquisition increases sample sizes at the cost of uncontrolled heterogeneity. This issue is partially addressed through the use of ComBat (Fortin et al., 2018), a data harmonization approach employed in multi-site studies to eliminate sources of variance that are not related to biological factors. It is also worth noting that we did not test for clinical specificity of our findings and we cannot exclude the possibility that the same neuromechanism may be present in other neurodevelopmental disorders with social behavioral problems.

Interpretation of the findings from mediation models are based on the assumption that effects are not driven by unmeasured variables that are strongly correlated with both executive functioning and social behavior deficits (Bullock and Green, 2021). Thus, it is possible that potential confounders may reduce the mediation effect. Our study consists of an exclusively male sample and study findings are only applicable to ASD males. Given the distinct neuroimaging patterns observed in ASD males and females (Walsh et al., 2021), it is possible that the neurodevelopmental pathways to ASD symptoms are sex-specific and that functional connectivity deviations associated with social behavioral deficits differ in males and females. Future studies should focus on identifying functional connectivity deviations in ASD females. Finally, our sample includes individuals from ages 5 -14 years, which covers several phases of childhood. However, the age distribution is imbalanced, and the majority of participants are aged 9 -12 years (Supplementary Fig 2). Thus, although normative models take age into account, our study findings largely apply to late childhood. Moreover, determining whether the observed brain connectivity atypicality in our study is the causal factor or the consequence of ASD behavioral pathology is a challenge. As experience shapes brain connectivity, the brain connectivity atypicality observed beyond the first few years of life may simply represent a consequence of social deprivation from reduced social attention. Future studies with a more balanced age distribution should explore whether associations between FC deviations and social behavioral deficits differ across different childhood phases, and focus on the change in brain connectivity in the first few years of life to establish the existence of these identified neuroimaging biomarkers. Another future area of focus is applying clustering algorithms to deviations from normative models to find potential subtypes.

## 5. Conclusion

This study contributes to the existing body of research that seeks to systematically map the heterogeneity of ASD across biological readouts. Our findings underscore the potential of between-network rsFC normative models to identify patterns of atypical brain connectivity at an individual level, thereby providing a window into neurobiological heterogeneity among ASD males in terms of their social difficulties. Last but not least, our study highlights cognitive flexibility and working memory as two key executive function domains that may play important roles in the social functioning of ASD males, suggesting areas of focus for future research to improve social behavioral problems in ASD males.

## CRediT authorship contribution statement

Ai Peng Tan: Writing -review &amp; editing, Writing -original draft, Supervision, Methodology, Formal analysis, Conceptualization. Pei Huang: Writing -original draft, Formal analysis, Data curation. Jasmine Si Min Chuah: Writing -original draft, Formal analysis, Data curation. Shi Yu Chan: Writing -original draft, Methodology, Formal analysis, Visualization.

## Data statement

Data for the current study were from the Autism Brain Imaging Data Exchange (ABIDE) I and ABIDE II collections of the ABIDE initiative, publicly available at http://fcon\_1000.projects.nitrc.org/indi/abide/.

## Declaration of Competing Interest

The authors declare that they have no known competing financial interests or personal relationships that could have appeared to influence the work reported in this paper.

## Acknowledgements

The authors would like to thank the project directors and team behind the Autism Brain Imaging Data Exchange.

SYC is supported by funding from the National Medical Research Council, Singapore (NMRC) Open Fund -Young Individual Research Grant (MOH-001149 -00). APT is supported by funding from the NMRC Transition Award (MOH-001273 -00).

## Appendix A. Supporting information

Supplementary data associated with this article can be found in the online version at doi:10.1016/j.dcn.2024.101483.

## Data availability

Data for the current study is publicly available.

## References

- ADDM, CDC. (2014). Prevalence of autism spectrum disorder among children aged 8 years - autism and developmental disabilities monitoring network, 11 sites, United States, 2010. MMWR. Surveillance summaries: Morbidity and mortality weekly report. Surveillance summaries / CDC, 63(2), 1 -21.
- American Psychiatric Association. (2013). Diagnostic and Statistical Manual of Mental Disorders, 5th ed. (DSM-5).
- Anderson, J.S., Druzgal, T.J., Froehlich, A., DuBray, M.B., Lange, N., Alexander, A.L., Abildskov, T., et al., 2011. Decreased interhemispheric functional connectivity in autism. Cereb. Cortex 21 (5), 1134 -1146.
- Andersson, J.L., Hutton, C., Ashburner, J., Turner, R., Friston, K., 2001. Modeling geometric deformations in EPI time series. Neuroimage 13 (5), 903 -919.
- Ashburner, J., Friston, K.J., 2005. Unified segmentation. Neuroimage 26 (3), 839 -851.
- Assaf, M., Jagannathan, K., Calhoun, V.D., Miller, L., Stevens, M.C., Sahl, R., O ' Boyle, J. G., et al., 2010. Abnormal functional connectivity of default mode sub-networks in autism spectrum disorder patients. Neuroimage 53 (1), 247 -256.
- Baddeley, A.D., 1992. Working memory. Science 255 (5044), 556 -559.
- Barendse, E.M., Hendriks, M.P., Jansen, J.F., Backes, W.H., Hofman, P.A., Thoonen, G., Kessels, R.P., et al., 2013. Working memory deficits in high-functioning adolescents with autism spectrum disorders: neuropsychological and neuroimaging correlates. J. Neurodev. Disord. 5 (1), 14.
- Bethlehem, R.A.I., Seidlitz, J., Romero-Garcia, R., Trakoshis, S., Dumas, G., Lombardo, M.V., 2020. A normative modelling approach reveals age-atypical cortical thickness in a subgroup of males with autism spectrum disorder. Commun. Biol. 3 (1), 486.
- Bethlehem, R.A.I., Seidlitz, J., White, S.R., Vogel, J.W., Anderson, K.M., Adamson, C., Adler, S., et al., 2022. Brain charts for the human lifespan. Nature 604 (7906)), 525 -533.
- Biswal, B., Yetkin, F.Z., Haughton, V.M., Hyde, J.S., 1995. Functional connectivity in the motor cortex of resting human brain using echo-planar MRI. Magn. Reson. Med. 34 (4), 537 -541.
- Buckner, R.L., Andrews-Hanna, J.R., Schacter, D.L., 2008. The brain ' s default network: anatomy, function, and relevance to disease. Ann. N. Y. Acad. Sci. 1124, 1 -38.
- Bullock, J.G., Green, D.P., 2021. The failings of conventional mediation analysis and a design-based alternative. Adv. Methods Pract. Psychol. Sci. 4 (4), 251524592110472.
- Chan, S.Y., Ong, Z.Y., Ngoh, Z.M., Chong, Y.S., Zhou, J.H., Fortier, M.V., Daniel, L.M., et al., 2022. Structure-function coupling within the reward network in preschool children predicts executive functioning in later childhood. Dev. Cogn. Neurosci. 55, 101107.
- Chan, S.Y., Ngoh, Z.M., Ong, Z.Y., Teh, A.L., Kee, M.Z.L., Zhou, J.H., Fortier, M.V., et al., 2024. The influence of early-life adversity on the coupling of structural and functional brain connectivity across childhood. Nat. Ment. Health.
- Constantino, J.N., Gruber, C.P., 2005. Social Responsiveness Scale (SRS) Manual (J., Ed.). Western Psychological Services, Los Angeles.
- Corlier, J., Wilson, A., Hunter, A.M., Vince-Cruz, N., Krantz, D., Levitt, J., Minzenberg, M.J., et al., 2019. Changes in functional connectivity predict outcome of repetitive transcranial magnetic stimulation treatment of major depressive disorder. Cereb. Cortex 29 (12), 4958 -4967.
- Di Biase, M.A., Tian, Y.E., Bethlehem, R.A.I., Seidlitz, J., Alexander-Bloch, A.F., Yeo, B.T. T., Zalesky, A., 2023. Mapping human brain charts cross-sectionally and longitudinally. Proc. Natl. Acad. Sci. USA 120 (20), e2216798120.
- Di Martino, A., Yan, C.G., Li, Q., Denio, E., Castellanos, F.X., Alaerts, K., Anderson, J.S., et al., 2014. The autism brain imaging data exchange: towards a large-scale evaluation of the intrinsic brain architecture in autism. Mol. Psychiatry 19 (6), 659 -667.
- Ecker, C., Bookheimer, S.Y., Murphy, D.G.M., 2015. Neuroimaging in autism spectrum disorder: brain structure and function across the lifespan. Lancet Neurol. 14 (11), 1121 -1134.
- Fortin, J.-P., Cullen, N., Sheline, Y.I., Taylor, W.D., Aselcioglu, I., Cook, P.A., Adams, P., et al., 2018. Harmonization of cortical thickness measurements across scanners and sites. Neuroimage 167, 104 -120.
- Fox, M.D., Raichle, M.E., 2007. Spontaneous fluctuations in brain activity observed with functional magnetic resonance imaging. Nat. Rev. Neurosci. 8 (9), 700 -711.
- Gioia, G.A., lsquith, P.K., Guy, S.C., &amp; Kenworthy, L. (2000). Behavior Rating Inventory of Executive Function (BRIEF) [Database record]. APA PsycTests.
- Gordon, A.C., Olson, D.R., 1998. The relation between acquisition of a theory of mind and the capacity to hold in mind. J. Exp. Child Psychol. 68 (1), 70 -83.
- Hashem, S., Nisar, S., Bhat, A.A., Yadav, S.K., Azeem, M.W., Bagga, P., Fakhro, K., et al., 2020. Genetics of structural and functional brain changes in autism spectrum disorder. Transl. Psychiatry 10 (1), 229.
- Henson, R.N.A., Buechel, C., Josephs, O., Friston, K.J., 1999. The slice-timing problem in event-related fMRI. Neuroimage 9, 125.
- Hernandez, L.M., Rudie, J.D., Green, S.A., Bookheimer, S., Dapretto, M., 2015. Neural signatures of autism spectrum disorders: insights into brain network dynamics. Neuropsychopharmacology 40 (1), 171 -189.
- Hill, E.L., 2004. Evaluating the theory of executive dysfunction in autism. Dev. Rev. 24 (2), 189 -233.
- Hughes, C., 1998. Finding your marbles: does preschoolers ' strategic behavior predict later understanding of mind? Dev. Psychol. 34 (6), 1326 -1339.
- Insel, T.R., 2014. Mental disorders in childhood: shifting the focus from behavioral symptoms to neurodevelopmental trajectories. J. Am. Med. Assoc. 311 (17), 1727 -1728.
- Jeste, S.S., Geschwind, D.H., 2014. Disentangling the heterogeneity of autism spectrum disorder through genetic findings. Nat. Rev. Neurol. 10 (2), 74 -81.
- Johnson, M.H., 2012. Executive function and developmental disorders: the flip side of the coin. Trends Cogn. Sci. 16 (9), 454 -457.
- Martinez-Murcia, F.J., Lai, M.-C., G ´ orriz, J.M., Ramírez, J., Young, A.M.H., Deoni, S.C.L., Ecker, C., et al., 2017. On the brain structure heterogeneity of autism: parsing out acquisition site effects with significance-weighted principal component analysis. Hum. Brain Mapp. 38 (3), 1208 -1223.
- Menon, V., 2011. Large-scale brain networks and psychopathology: a unifying triple network model. Trends Cogn. Sci. 15 (10), 483 -506.
- Miyake, A., Friedman, N.P., 2012. The nature and organization of individual differences in executive functions: four general conclusions. Curr. Dir. Psychol. Sci. 21 (1), 8 -14.
- Miyake, A., Friedman, N.P., Emerson, M.J., Witzki, A.H., Howerter, A., Wager, T.D., 2000. The unity and diversity of executive functions and their contributions to complex ' Frontal Lobe ' tasks: a latent variable analysis. Cogn. Psychol. 41 (1), 49 -100.
- Monk, C.S., Peltier, S.J., Wiggins, J.L., Weng, S.-J., Carrasco, M., Risi, S., Lord, C., 2009. Abnormalities of intrinsic functional connectivity in autism spectrum disorders. Neuroimage 47 (2), 764 -772.
- Mostofsky, S.H., Powell, S.K., Simmonds, D.J., Goldberg, M.C., Caffo, B., Pekar, J.J., 2009. Decreased connectivity and cerebellar activity in autism during motor task performance. Brain A J. Neurol. 132 (Pt 9), 2413 -2425.
- Ooi, L.Q.R., Chen, J., Zhang, S., Kong, R., Tam, A., Li, J., Dhamala, E., et al., 2022. Comparison of individualized behavioral predictions across anatomical, diffusion and functional connectivity MRI. Neuroimage 263, 119636.
- Ozonoff, S., Jensen, J., 1999. Brief report: specific executive function profiles in three neurodevelopmental disorders. J. Autism Dev. Disord. 29 (2), 171 -177.
- Ozonoff, S., Heung, K., Byrd, R., Hansen, R., Hertz-Picciotto, I., 2008. The onset of autism: patterns of symptom emergence in the first years of life. Autism Res. Off. J. Int. Soc. Autism Res. 1 (6), 320 -328.
- Ozonoff, S., Iosif, A.-M., Baguio, F., Cook, I.C., Hill, M.M., Hutman, T., Rogers, S.J., et al., 2010. A prospective study of the emergence of early behavioral signs of autism. J. Am. Acad. Child Adolesc. Psychiatry 49 (3), 256 -266 e1.
- Padmanabhan, A., Lynch, C.J., Schaer, M., Menon, V., 2017. The default mode network in autism. Biol. Psychiatry. Cogn. Neurosci. Neuroimag. 2 (6), 476 -486.
- Power, J.D., Fair, D.A., Schlaggar, B.L., Petersen, S.E., 2010. The development of human functional brain networks. Neuron 67 (5), 735 -748.
- Prior, M., Hoffmann, W., 1990. Brief report: neuropsychological testing of autistic children through an exploration with frontal lobe tests. J. Autism Dev. Disord. 20 (4), 581 -590.
- R: A language and environment for statistical computing. (2021). The R Project for Statistical Computing. Retrieved November 8, 2023, from http://www.r-project.org/

.

- Rane, P., Cochran, D., Hodge, S.M., Haselgrove, C., Kennedy, D.N., Frazier, J.A., 2015. Connectivity in autism: a review of MRI connectivity studies. Harv. Rev. Psychiatry 23 (4), 223 -244.
- Reineberg, A.E., Gustavson, D.E., Benca, C., Banich, M.T., Friedman, N.P., 2018. The relationship between resting state network connectivity and individual differences in executive functions. Front. Psychol. 9, 1600.
- Russell, J., Jarrold, C., 1998. Error-correction problems in autism: evidence for a monitoring impairment? J. Autism Dev. Disord. 28 (3), 177 -188.
- Rutherford, S., Fraza, C., Dinga, R., Kia, S.M., Wolfers, T., Zabihi, M., Berthet, P., et al., 2022. Charting brain growth and aging at high spatial precision. eLife 11.
- Sandman, C.F., Young, K.S., Burklund, L.J., Saxbe, D.E., Lieberman, M.D., Craske, M.G., 2020. Changes in functional connectivity with cognitive behavioral therapy for social anxiety disorder predict outcomes at follow-up. Behav. Res. Ther. 129, 103612.
- Stevens, M.C., Pearlson, G.D., Calhoun, V.D., 2009. Changes in the interaction of restingstate neural networks from adolescence to adulthood. Hum. Brain Mapp. 30 (8), 2356 -2366.

- Sylvester, C.-Y.C., Wager, T.D., Lacey, S.C., Hernandez, L., Nichols, T.E., Smith, E.E., Jonides, J., 2003. Switching attention and resolving interference: fMRI measures of executive functions. Neuropsychologia 41 (3), 357 -370.
- Tingley, D., Yamamoto, T., Hirose, K., Keele, L., Imai, K., 2014. mediation: R package for causal mediation analysis. J. Stat. Softw. 59 (5).
- Uddin, L.Q., Supekar, K., Menon, V., 2013b. Reconceptualizing functional brain connectivity in autism from a developmental perspective. Front. Hum. Neurosci. 7, 458.
- Uddin, L.Q., Supekar, K., Lynch, C.J., Khouzam, A., Phillips, J., Feinstein, C., Ryali, S., et al., 2013a. Salience network-based classification and prediction of symptom severity in children with autism. JAMA Psychiatry 70 (8), 869 -879.
- Vissers, M.E., Cohen, M.X., Geurts, H.M., 2012. Brain connectivity and high functioning autism: a promising path of research that needs refined models, methodological convergence, and stronger behavioral links. Neurosci. Biobehav. Rev. 36 (1), 604 -625.
- Walsh, M.J.M., Wallace, G.L., Gallegos, S.M., Braden, B.B., 2021. Brain-based sex differences in autism spectrum disorder across the lifespan: a systematic review of structural MRI, fMRI, and DTI findings. NeuroImage. Clin. 31, 102719.
- Werling, D.M., Geschwind, D.H., 2013. Sex differences in autism spectrum disorders. Curr. Opin. Neurol. 26 (2), 146 -153.
- Whitfield-Gabrieli, S., Nieto-Castanon, A., 2012. Conn: a functional connectivity toolbox for correlated and anticorrelated brain networks. Brain Connect. 2 (3), 125 -141.
- Witten, D.M., Tibshirani, R.J., 2009. Extensions of sparse canonical correlation analysis with applications to genomic data. Stat. Appl. Genet. Mol. Biol. 8, Article28.
- Witten, D.M., Tibshirani, R., Hastie, T., 2009. A penalized matrix decomposition, with applications to sparse principal components and canonical correlation analysis. Biostatistics 10 (3), 515 -534.
- Wolfers, T., Beckmann, C.F., Hoogman, M., Buitelaar, J.K., Franke, B., Marquand, A.F., 2020. Individual differences v. the average patient: mapping the heterogeneity in ADHD using normative models. Psychol. Med. 50 (2), 314 -323.
- Wood, S.N., 2011. Fast stable restricted maximum likelihood and marginal likelihood estimation of semiparametric generalized linear models. J. R. Stat. Soc. Ser. B (Stat. Methodol. 73 (1), 3 -36.
- Yeo, B.T.T., Krienen, F.M., Sepulcre, J., Sabuncu, M.R., Lashkari, D., Hollinshead, M., Roffman, J.L., et al., 2011. The organization of the human cerebral cortex estimated by intrinsic functional connectivity. J. Neurophysiol. 106 (3), 1125 -1165.
