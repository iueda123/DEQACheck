## A 10-Year Longitudinal Study of Brain Cortical Thickness in People with FirstEpisode Psychosis Using Normative Models

Pierre Berthet 1,2,13 , Beathe C. Haatveit 1,2,13 , Rikka Kjelkenes 1,2,13 , Amanda Worker 3 , Seyed Mostafa Kia 4-6 , Thomas Wolfers 1,2,7, , Saige Rutherford 4,8,9 , Dag Alnaes 1,2, , Richard Dinga 6 , Mads L. Pedersen 1,2 , Andreas Dahl 1,2 , Sara Fernandez-Cabello 1,2 , Paola Dazzan 3 , Ingrid Agartz 2,10,11 , Ragnar Nesvåg 12 , Torill Ueland 1,2 , Ole A. Andreassen 2 , Carmen Simonsen 2,10 , Lars T. Westlye 1,2,14, , Ingrid Melle 2,14 , Andre Marquand 4,8,*,14

1 Department of Psychology, University of Oslo, Oslo, Norway;  2 Norwegian Center for Mental Disorders Research (NORMENT), University of Oslo, and Oslo University Hospital, Oslo, Norway;  3 Department of Psychosis Studies, Institute of Psychiatry, King's College, London, UK;  4 Donders Institute for Brain, Cognition, and Behaviour, Radboud University, Nijmegen, the Netherlands; 5 Department of Psychiatry, Utrecht University Medical Center, Utrecht, the Netherlands;  6 Department Cognitive Science and Artificial Intelligence, Tilburg University, the Netherlands;  7 Department of Psychiatry and Psychotherapy, T ü bingen Center for Mental Health, University of T ü bingen, T ü bingen, Germany;  8 Department of Cognitive Neuroscience, Radboud University Medical Center, Nijmegen, the Netherlands;  9 Department of Psychiatry, University of Michigan, Ann Arbor, MI, USA;  10 Department of Psychiatric Research, Diakonhjemmet Hospital, Oslo, Norway;  11 Centre for Psychiatry Research, Department of Clinical Neuroscience, Karolinska Institutet, Stockholm, Sweden;  12 Department of Mental Disorders, Norwegian Institute of Public Health, Oslo, Norway

13 These authors shared first authorship.

14 These authors shared last authorship.

* To whom correspondence should be addressed: Department of Cognitive Neuroscience, Radboud University Medical Centre, Kapittelweg 29, Nijmegen 6525EN, the Netherlands; tel: (+31) 024-3668492, e-mail: andre.marquand@donders.ru.nl

Background : Clinical  forecasting  models  have  potential to optimize treatment and improve outcomes in psychosis, but predicting long-term outcomes is challenging and longterm follow-up data are scarce. In this 10-year longitudinal study, we aimed to characterize the temporal evolution of cortical correlates of psychosis and their associations with symptoms. Design : Structural  magnetic  resonance  imaging (MRI) from people with first-episode psychosis and controls  ( n = 79  and  218)  were  obtained  at  enrollment, after  12  months  ( n = 67  and  197),  and  10  years  ( n = 23 and  77),  within  the  Thematically  Organized  Psychosis (TOP) study. Normative models for cortical thickness estimated on public MRI datasets ( n = 42 983) were applied to TOP data to obtain deviation scores for each region and timepoint. Positive and Negative Syndrome Scale (PANSS) scores were acquired at each timepoint along with registry data. Linear mixed effects models assessed effects of diagnosis, time, and their interactions on cortical deviations plus associations with symptoms. Results : LMEs revealed conditional main effects of diagnosis and time × diagnosis interactions in a distributed cortical network, where negative deviations in patients attenuate over time. In patients, symptoms also attenuate over time. LMEs revealed effects of anterior cingulate on PANSS total, and insular and orbitofrontal regions on PANSS negative scores. Conclusions :

This  long-term  longitudinal  study  revealed  a  distributed pattern of cortical differences which attenuated over time together  with  a  reduction  in  symptoms.  These  findings are not in line with a simple neurodegenerative account of schizophrenia, and deviations from normative models offer a promising avenue to develop biomarkers to track clinical trajectories over time.

Key words: schizophrenia/cortical thickness/long-term follow up/normative modeling

## Introduction

Psychotic  disorders  are  severe  and  complex  conditions characterized by substantial clinical and biological heterogeneity 1-3 and  significant  negative  effects  on  quality of  life  and  societies. 4-7   Predicting  long-term  outcomes and improving treatment and prognosis are a priority in schizophrenia research, and models that can predict the clinical course are highly needed, as this will help us to optimize treatment planning. Longitudinal clinical studies have revealed substantial heterogeneity in the clinical and functional  trajectories, 8-12 underlying  neurobiology, 13-16 and  its  interaction  with  medication. 17-21 Likewise,  both cross-sectional  and  longitudinal  brain  imaging  studies

have revealed significant yet typically diffuse brain cortical alterations in groups of individuals with psychotic disorders. 22-24   Both  neurodevelopmental  and  neurodegenerative models have been proposed for the evolution of these changes, 25-27  and there is increasing evidence and awareness of substantial individual differences and heterogeneity in such trajectories. 28-30  However, the timeframe for most longitudinal studies is relatively short (e.g., 1-2 years) and there is a need for better characterization of the dynamics of the brain cortical correlates of the illness and their  long-term  temporal  associations  with  clinical symptoms at an individual level. 31-35  Two previous longterm  prospective  studies  of  first-episode  psychosis  patients were conducted more than 20 years ago. 36,37  These studies comprised patients receiving first-generation antipsychotics, a group of medications associated with findings of reduced cortical thickness (CT). 18,38  A more recent study reports an association between increasing expressive negative symptoms and changes in CT, reporting on the dose but not the type of medication. 39

More recently still, the availability  of  large  neuroimaging datasets has led to the advent of normative development charts 40,41 that allow for individual-level statistical inference and for mapping clinical traits to extreme deviations  from  the  normative  range. 28,42-49 Such  techniques may be particularly valuable in longitudinal studies because they provide the ability to detect deviations from an  expected  trajectory  over  time,  which  might  provide early indicators of worsening or improvement in the disease course and can accommodate heterogeneity in the pattern of atypicalities across individuals and timepoints.

Our main goal in this study was to map the associations between brain cortical abnormalities and clinical symptoms over the longer term. To achieve this, we applied a  normative modeling approach to magnetic resonance imaging (MRI)-based estimates of cerebral CT of people with schizophrenia spectrum first-episode psychosis and healthy  controls  (CTRL)  in  a  long-term  longitudinal study of participants with follow-up after approximately 12 months and 10 years. We used normative models to compute individual deviation scores for the 2 groups at different timepoints, which allows meaningful comparisons even when follow-up data are acquired on different scanners from the baseline scans. 50  Then, we assessed the association between CT deviations and symptom scales at clinical follow-up and between deviations and Norwegian patient registry data to address the possibility of selective retention bias influencing our findings. Given prior evidence for the heterogeneity of cortical alterations in schizophrenia 28-30 and  that  only  a  subset  of  individuals with schizophrenia show progressive brain changes, 36  we predicted that: (i) we would observe a characteristic yet diffuse pattern of case-control differences in cortical normative  deviations,  consistent  with  prior  studies 22,24 and (ii) individual differences in cortical deviations would be coupled  to  clinical  outcome  over  time.  We  tested  these associations using linear mixed models (LMEs) with subsequent corrections for multiple comparisons.

## Methods

Participants

All participants were recruited to a specific first-episode sub-study  of  the  TOP  study  at  the  University  of  Oslo and  Oslo  University  Hospital  from  October  27,  2004, to October 17, 2012. Here, patients with a first-episode schizophrenia  spectrum  diagnosis  (SCZ)  were  consecutively  recruited  from  the  catchment-area-based  inpatient and outpatient services at Oslo University Hospital and 3 additional hospitals in the larger Oslo area to the prospective  study.  Psychiatric  diagnosis  at  baseline  was established  using  the  Structured  Clinical  Interview  for DSM-IV Axis I Disorders (SCID-I 40 ), and we included a broad range of schizophrenia spectrum diagnoses: schizophrenia ( n = 57, 72% of the final, quality-checked longitudinal  sample),  schizophreniform  disorders  ( n = 18, 23%), and schizoaffective ( n = 4, 5%). Information about patients' current antipsychotic medication was gathered at each timepoint. Positive and negative symptoms were assessed using the Positive and Negative Syndrome Scale (PANSS 51 ).  Healthy  CTRL  from  the  same  geographic catchment area were invited based on national records. Exclusion criteria for healthy CTRL included a history of drug or alcohol abuse or dependency, psychosis, bipolar disorder, or major depressive disorder, or having a first-degree relative diagnosed with a psychotic or bipolar disorder. The participants were invited to a follow-up approximately  10  years  after  their  baseline  scan  (patients mean [SD] 9.7 years [0.9], CTRL 8.2 years [1.1]). A subsample also participated in a follow-up scan after approximately 1 year. 16 See  supplementary figure 2 for details. We also augmented our sample by including additional CTRL from additional Thematically Organized Psychosis (TOP) sub-studies acquired on the same scanners at the same time,  to  improve  the  fit  of  the  normative  models that we employ.

We  also  accessed  the  Norwegian  National  Registry for healthcare information about all enrolled patients at baseline. This allowed us to access dates and durations of  contacts  with  the  Norwegian  healthcare  system  for ICD-10 F-01-09 labeled events (Mental, Behavioral and Neurodevelopmental disorders) in the follow-up period (e.g.,  from  the  start  of  treatment  to  10-year  follow-up) for all participants, serving as a proxy for illness severity.

## MRI Data Acquisition and Analysis

Three  scanners  at  Oslo  University  Hospital  were  used in  this  longitudinal  study  without  temporal  overlap. The first scanner was a 1.5 Tesla Siemens MANETOM Sonata scanner with a 32-channel head coil. T1-weighted images  were  acquired  using  an  MPRAGE  sequence

using these parameters: repetition time (TR) = 2.730 ms, echo  time  (TE) = 3.93 ms,  flip  angle  (FA) = 7°C.  The second scanner was a 3 Tesla GE Signa HDxT with an 8HRBRAIN  coil.  T1-weighted  images  were  acquired using  an  FSPGR  sequence,  with  the  following  parameters:  TR = 7.8 ms,  TE = 3.18 ms,  and  FA = 12°C.  The third  scanner was a 3 Tesla GE 750 Discovery scanner with  a  32-channel  head  coil.  The  T1-weighted  images were here acquired using a BRAVO sequence, with the following parameters: TR = 8.16 ms, TE = 3.18 ms, FA = 12°C. See supplementary figure 9 for the distribution of scanners across different timepoints.

T1-weighted structural MRI scans were preprocessed through Freesurfer (version 5.3), and CT measures were parcellated  using  the  Destrieux  atlas. 52   An  automatic quality  check  procedure  based  on  the  Freesurfer  Euler characteristic  was  run  on  all  data  and  samples  with  a value higher than 5 were removed. 53-58

## Normative Modeling

To  account  for  site  and  scanner  effects,  we  used  the Hierarchical  Bayesian  regression  (HBR)  approach  for normative  modeling, 54,55,59   which  efficiently  accommodates  inter-site  variation  and  provides  computational scaling, which is useful for multi-cohort and longitudinal studies with data from different scanners. We estimated a  normative  model  for  each  region  of  interest  (ROI, n = 150) in the Freesurfer Destrieux atlas, 52   using HBR with age as a covariate, and sex and scanner id as batch effects, to predict CT. 45,54,55,58  This accommodated multisite  pooling  using  transfer  learning  and  comparisons across scanners. 54,55,59   The deviations from these models were  then  used  as  features  in  the  linear  mixed  models outlined  below.  Importantly,  as  HBR  fits  site-specific intercepts  and  slopes,  the  resulting  normative  trajectory might not be linear across the lifespan, but rather piecewise  linear.  Using  pooled  data  from  a  collection of mostly publicly available datasets from 77 sites, and 40 435  participants, 29   the  reference  normative  models were first trained on (95%) healthy individuals and validated on an independent set of 2548 CTRL and patients (5%, stratified by sites). We then adapted the model to the 3 unseen Oslo scanners, by transferring the (hyper) parameters as informed priors for these new sites. 55  For this  adaptation  step,  we  used  held-out  cross-  sectional data  from  CTRL  from  these  3  scanners,  following methods described previously 55  (supplementary table 1). After this transfer step, we tested the normative models on the remaining longitudinal CTRL and SCZ samples and obtained individual deviation scores for these participants  at  each  timepoint  and  ROI.  We  defined  the threshold for extreme deviation values as | z | &gt; 2.0. While this threshold is arbitrary, we consider 2 standard deviations from the mean a potentially clinically significant effect.

## Statistical Analysis

To  test  for  the  potential  of  nonrandom  attrition  confounding our findings, we applied t -tests to check for differences between the patients followed for 10 years and the ones that dropped out in several factors, i.e., number of  hospitalizations,  Positive  and  Negative  Syndrome Scale (PANSS) domain scores, and median CT deviation scores.

Next, we employed an LME model to investigate the impact  of  diagnosis,  time  since  inclusion,  age  at  inclusion, and sex on the deviation scores derived from the 150 cortical  ROIs  in  a  longitudinal  setting.  The  interaction between group and time since inclusion (delay) was also included. The model was formulated as follows:

<!-- formula-not-decoded -->

where i indexes subjects, y i is the deviation score at a given ROI, and β 0 a global intercept. The variables X 1 i , . . ., X 4 i represent, respectively, time since inclusion, age at baseline, sex and diagnosis with associated coefficients β 1 , . . ., β 4 . In addition, we model an interaction between diagnosis and time since inclusion, i.e., X 1 i X 4 i with coefficient β 5 . Finally, u i is a subject-specific random intercept and /epsilon1 i are normally distributed errors.

In all instances where multiple comparison correction was required, we applied the Benjamini-Hochberg procedure with α = 0.05 to control the false discovery rate 60 corrected  across  ROIs.  For  the  ROIs  with  a  significant interaction effect, we also calculated the predicted values for a combination of time since inclusion (delay) and diagnosis  levels  to  visualize  the  nature  of  the  interaction effects in the model.

We also aimed to understand the regional distribution of  extreme  deviations  at  the  level  at  the  individual.  As these are count data (i.e., having highly skewed discrete distributions),  we  applied  a  non-parametric  Wilcoxon test to the proportion of individual deviations differing between diagnostic groups at each timepoint, in line with prior work. 28,29,61

To examine the development of PANSS scores during the  longitudinal  period,  we  visualized  the  distributions and used an LME model to assess the change in PANSS subscales over time, while controlling for age at baseline and sex. The model was formulated as follows:

<!-- formula-not-decoded -->

Here, y i is the PANSS score (or subscale) and X 1 i , . . . , X 3 i are  defined  as  above,  respectively,  time  since  inclusion, age at baseline, and sex with coefficients β 1 , . . . , β 3 . Again, β 0 is  the global intercept, u i is  a  subject-specific random intercept and /epsilon1 i are normally distributed errors.

Next, we employed another LME model to investigate the  associations  between the deviation score in the different  ROIs,  time  since  inclusion,  age  at  inclusion  and sex on the general and domain-specific PANSS scores in the schizophrenia patients. The model was formulated as follows:

<!-- formula-not-decoded -->

Here, y i , X 1 i , . . . , X 3 i , u i , and /epsilon1 i are defined as in equations  (1)  and  (2),  but  here X 4 i is  the  deviation  score  at a given ROI, with coefficient β 4 ,  and we also model an interaction  between  time  since  inclusion  and  deviation score, X 1 i X 4 i , with coefficient β 5 .

## Results

## Participants

A total of 218 healthy CTRL and 79 patients were included  in  the  longitudinal  analysis  (table  1  and  supplementary  figures  1  and  8).  There  was  no  significant difference  either  in  the  number  of  contacts  with  the healthcare system for ICD-10 classified 'Mental, Behavioral  and  Neurodevelopmental  disorders,'  nor  in duration of contacts with the healthcare system (e.g., the duration of treatment) between the longitudinal sample and the drop-outs. There was no significant association between  attrition  groups  and  PANSS  scores,  or  median  cortical  deviation  scores.  Data  on  medication  use for  each  timepoint  showed that patients primarily used second-generation antipsychotics (62% at baseline, 48% at 1 year, and 17% at 10 years) or did not use any antipsychotics (38%, 50%, and 83%, respectively; for details, see supplementary material).

## Normative Modeling Allows to Compare Across Sites

Figure 1 displays the joint distributions of the median CT and their associated deviation scores for all healthy CTRL from the validation set of the adaptation (transfer) dataset. Of interest are the marginal densities: normative modeling

Table 1. Demographics of the Participants at the 3 Time Points

|                                     | Baseline      | Baseline            | 12 months      | 12 months              | 10 years   | 10 years         |
|-------------------------------------|---------------|---------------------|----------------|------------------------|------------|------------------|
| Diagnosis                           | CTRL          | SCZ                 | CTRL           | SCZ                    | CTRL       | SCZ              |
| N                                   | 218           | 79                  | 197            | 67                     | 77         | 23               |
| Age, mean [SD]                      | 33.9 [10.1] a | 27.8 [7.6]  a 41.7% | 35.1 [10.2]  a | 29.3 [7.9]  a 41.8% 56 | 40.7 [7.8] | 37.9 [7.3] 34.7% |
| Sex ratio (female) PANSS, mean [SD] | 42.6%         | 64                  | 40.6%          |                        | 37.6%      | 50               |
|                                     |               | [14]                |                | [15]                   |            | [16]             |

Note : a There was a significant effect of group on age at baseline ( F = 24.0 1,295 , P &lt; .01) and 12 months ( F = 18.1 1,295 , P &lt; .01). There were no other significant differences for other demographic variables.

of the median CT accounting for site and sex aligns the distributions and allows meaningful comparisons between samples from different sites in the deviation scores space. In contrast, estimates of CT appear highly impacted by the site effect (figure 1). We also show an example of the deviation scores following the adaptation process in supplementary figure 8, which illustrates that the normative model does a good job in accounting for age-related variation. We have shown in prior work that normative modeling also allows meaningful comparisons across sex. 40,50,55

## Deviation Score Difference by ROIs

LMEs revealed significant ( P &lt; .05, FDR corrected) conditional main effects of diagnosis and time × diagnosis interaction effects in a diffuse network of lateral temporal, parietal, and frontal brain regions, and along the medial frontal and parietal lobes, bilaterally (figure 2A and B). It should be noted that regression plots for each region (supplementary figure 4) showed a cross-over interaction in  many (but not all) regions also having a conditional main effect  on  diagnosis.  In  such  cases,  the  interaction effect should be considered the primary finding. Post hoc analyses (supplementary figures 3 and 4) showed that the interaction in most regions was principally due to more negative deviation scores in patients with SCZ at baseline, which attenuate over time such that a fewer number of significant regions were detected at the first follow-up timepoint, and there were no significant differences observed at the final 10-year follow-up (supplementary figures 3-5). The effect sizes at the different timepoints are shown in supplementary figure 3D. Briefly, the Cohen's d for  median  thickness  deviation  score  at  baseline  is -0.46, -0.43 at 12-month follow-up and -0.27 at 10-year follow-up. Additionally, there were no significant differences in the deviation scores at baseline between patients who completed the 10-year follow-up scan ( n = 23) and those  who  did  not  but  were  enrolled  at  baseline  in  the 10-year  study  ( n = 40).  In  addition  to  these  effects,  we also detected conditional main effects for age, time since inclusion, and sex (supplementary figure 6). However, as these are nuisance effects and all have very small effect sizes, we do not consider them further.

Fig. 1. Joint-plot and marginal distributions of the median CT measures ( y -axis) and associated deviation scores ( x -axis), color-coded by a scanner, from the test samples of the adaptation set (cross-sectional TOP samples).

![Image](./Berthet2025_artifacts/image_000000_b7094c4e871ce299f012fd6c61f686851055cbb98143f9bf7ad91d8db99c3ff6.png)

For  visualization  purposes,  figure  3  shows  both  the raw  CT  estimates  and  deviations  from  the  normative model  for  mean  CT  in  individuals  with  schizophrenia and healthy CTRL. As expected, the raw CT estimates (figure  3A)  are  confounded  by  both  aging  and  scanner effects,  showing the general reduction in CT that is expected over this lifespan stage. 40  In contrast, the normative deviations are cleared from these effects (figure 3B). In both cases, the gradual attenuation of baseline reductions in CT over time is apparent.

Number and Distribution of Regions with Extreme Deviations

Figure 4 and supplementary table 3 summarize the distribution  of  ROIs  showing  a  significantly  higher  proportion of extreme negative deviations among patients with  schizophrenia  compared  with  CTRL,  at  each timepoint. Here we find ROIs in both the left and right hemispheres with significantly different overlap statistics  of  extreme  negative  deviation  scores  between  patients and CTRL. The 3 ROIs with the strongest effects are  the  lateral  aspect  of  the  superior  temporal  gyrus

(CV = 0.19, P &lt; .01),  and  the  opercular  part  of  the interferer  frontal  gyrus  (CV = 0.18, P &lt; .01)  both  in the LH, and the superior temporal sulcus (CV = 0.18, P &lt; .01)  in  the  right  hemisphere.  At  the  second  and third  time  point,  none  of  the  ROIs  remained  significant. A Mann-Whitney U test  revealed  no  significant case-control differences in the number of positive extreme deviations, at any time points ( Z &gt; 2). The analysis revealed significant case-control differences in the number  of  extreme  negative  deviations  ( Z &lt; -2)  at baseline ( P = 2.56 × 10 -5 , common language (CL) effect size = 66%)  and  at  the  second  assessment  ( P = .0006, CL effect size = 63%). There was no significant casecontrol difference at the third time point ( P = 1, CL effect size = 50%) (see supplementary tables 4 and 5 for further details).

Symptom Scores from Inclusion to 10-Year Follow-up

PANSS scores at each timepoint are shown in figure 5.

First, linear mixed models were fitted to examine the association  between  the  different  PANSS  sub-scores and  the  predictor  variables,  including  time  since  the

Fig. 2. (A) Conditional main effect of diagnosis from the linear mixed model, showing that patients have reduced CT deviations overall (i.e., across all timepoints), color-coded by effect size; (B) time × diagnosis interaction effect, showing ROIs where the effect of diagnosis changes over time; (C) regression plot for mean CT deviation across all ROIs, showing that the differences evident in the deviations at the first timepoint attenuate at later timepoints. The color bar shows the effect size for each effect multiplied by the sign of the coefficient.

![Image](./Berthet2025_artifacts/image_000001_947039c80c9624eed0061e1660b7a1756717b36b887eec99f1dc74b2ad04dcb8.png)

first scan, age at the first scan, and sex. For all PANSS scores, there was a significant effect of time ( P &lt; .001), indicating that PANSS scores decreased over time. None of the other covariates were significant (supplementary figure 10). To further clarify the nature of these effects, we also tested differences between individual timepoints, significant  differences  were  observed  only  when  comparing  baseline  values  to  the  second  follow-up  scores, but not between the 2 follow-up time points, indicating that  the  main  improvement  took  place  during  the  first year  (baseline-12-month  follow-up, P = .002  Cohen's d = -0.53; baseline-10-year follow-up P = .001, Cohen's d = -0.87). For PANSS subscales, we notice significantly lower  scores  for  the  general  psychopathology  scale  at follow-ups compared with baseline (baseline-12-month follow-up P = .0002 Cohen's d = -.60; baseline-10-year follow-up P = .0004  Cohen's d = -0.93).  Symptom  domains were also  significantly  reduced  at  12-month  and 10-year  follow-ups  compared  with  baseline  for  positive  symptoms  (baseline-12-month follow-up P = .012 Cohen's d = -0.42; baseline-10-year follow-up P = .026

Cohen's d = -.56)  and  at  10  years  for  negative  symptoms (baseline-10-year follow-up P = .019 Cohen's d = -0.58).

## Association of CT Deviation and PANSS Scores

Figure 6 shows the results from the LME testing for associations between cortical deviations and PANSS. Several ROIs in the LH had a significant association with PANSS domain scores across time. Anterior cingulate gyrus was associated with PANSS total and PANSS general (coefficient = -4.0 and -1.79, respectively, P = .003 and .01). The left anterior segment of the circular sulcus of the insula (coefficient = -1.3, P = .04), the posterior ramus of the lateral sulcus (coefficient = 1.52, P = .03), and the medial orbital sulcus (coefficient = -1.42, P = .02) were also significantly  associated  with  PANSS  negative.  Negative associations indicate more negative deviation scores with higher symptom severity.

There was a significant interaction between time since inclusion  and  the  LH  posterior  ramus  (or  segment)  of

Fig. 3. (A) Raw mean CT scores for individuals with schizophrenia and healthy CTRL, where the line segments connect the successive time points of each individual. (B) Deviations from the normative model for mean CT.

![Image](./Berthet2025_artifacts/image_000002_b8632b7024662e8e7b9ea5e911c4dcb72e1b179813766f69dfa7e2ecb232ac53.png)

the lateral sulcus on PANSS negative scores (figure 5D, coefficient = 0.28, P = .047), indicating that an initial association  between  larger  negative  deviations  and  more symptoms at baseline attenuated with time (see supplementary figure 6).

## Discussion

In this study, we analyzed CT data from a 10-year longitudinal study of people with schizophrenia and healthy CTRL using structural brain MRI. The dataset included 2 or 3 time points for each participant, covering a period of approximately 10 years. We used normative modeling to investigate the deviations from an expected pattern of CT and how this pattern changed over time, both at the group level and at the individual level. We also examined the relationship between these deviation scores and the severity of psychotic symptoms as measured by PANSS. We report 3 main findings: (i) we show a diffuse pattern of  CT atypicalities  in  schizophrenia  early  in  the  illness course,  both  in  terms  of  the  mean  deviations  across groups and in the number of extreme deviations at the individual level; and that these deviations (ii) attenuate over time; and (iii) associations with clinical symptoms across a distributed set of brain regions.

The  pattern  of  negative  deviation  scores  at  baseline in a diffuse network of in patients compared with CTRL  encompassed  bilateral  temporal,  parietal  and frontal regions. These significant effects are consistent with  findings  from  large  meta-analytic  studies 22-24 and more generally, 14,28,62  present both at baseline and at the first  follow-up  and  are  evident  at  the  group  and  individual levels, both in terms of the mean and the number of  extreme  deviations  (figures  2  and  4,  respectively). Significant  interactions  between  diagnosis  and  time demonstrate that these differences attenuate over time. More  specifically,  between  the  first  two  time  points, the number of ROIs with significant effects decreased, but also the amplitude of the remaining effects. These findings are in line with previous reports that the gray matter  differences  were  most  severe  in  the  early  years after  schizophrenia  onset, 36 and  are  discordant  with  a general  notion  of  schizophrenia  as  a  neurodegenerative  disorder  with  progressive  brain  aberrations  over time. 25,36  The 3 previous long-term prospective brain imaging studies of first-episode psychosis also found that only a smaller subset of individuals with schizophrenia showed significant  progressive  brain  changes  and  also had a high proportion of subjects using first-generation antipsychotics. 36,37,39 In  contrast,  in  our  study,  individuals with psychosis were almost exclusively treated with second-generation antipsychotics. In view of the heterogeneity within the illness 28-30  and since the proportion of patients with stable, poor clinical trajectories is relatively low, and the proportion with increasingly severe trajectories is even lower, we consider that the profile we detect likely reflects that very few participants with these trajectories are part of our study sample. Our findings thus primarily reflect the more common favorable trajectories.  Since  good-outcome  first-episode  patients  leave the  treatment  services,  more  extensive  cross-sectional studies based on clinical recruitment will include more multi-episode patients. We consider that cross-sectional studies will thus be enriched with patients with more severe trajectories, explaining the findings of considerable heterogeneity found in these types of studies. 28,29,63

Fig. 4. (A-C) ROIs showing a significantly higher proportion of negative extreme deviations among patients with schizophrenia (SCZ) compared with healthy CTRL at each timepoint. (d) χ 2 test significant differences in negative extreme deviation distributions between people with SCZ and CTRL at each time point (see supplementary table 4 for a detailed summary of each ROI).

![Image](./Berthet2025_artifacts/image_000003_31fe64f34fce8069de83843889919bca2fa6e80abb6312045bfcdb3ff039d48a.png)

The pattern of brain regions showing case-control differences particularly implicated frontal and temporal regions including the paracentral lobule and sulcus, which have  been  associated  with  poor  1-year  functional  outcomes 64  and the superior temporal gyrus, associated with positive  symptoms. 62 Several  insula  ROIs  showed  more negative  deviations  in  participants  with  schizophrenia than in CTRL at both baseline and at the first follow-up after on average 24 months. This region, especially on the left, is associated with inner speech and verbal hallucinations and reduced insula gray matter has been reported in hallucinators. 65

We  assessed  the  possibility  of  nonrandom  attrition biasing our findings, which is often a concern in longitudinal studies and may influence the validity of regression models. 66 Logistic  regressions  and  LME  models  did  not indicate any significant differences between attrition and CT deviation scores, PANSS scores, or in the frequency or  duration  of  contacts  with  the  healthcare  system  for mental,  behavioral,  and  neurodevelopmental  disorderrelated events.

Linear  mixed  models  revealed  a  few  brain  regions showing associations with symptoms. Most notably in the  anterior  cingulate  gyrus  where  patients  with  more negative  deviations  exhibited  higher  symptom  scores on multiple PANSS domains (total, positive, and negative  scales)  and  several  other  regions  including  the insula  and  parahippocampal  gyrus  showed  an  association  with  negative  symptoms.  Notably,  alterations  in the insula and the cingulate gyrus have been associated with negative symptoms, hallucinations, and psychotic disorders. 22,67,68

Fig. 5. PANSS domain scores at the 3 timepoints. Most follow-up scores are significantly lower than baseline scores indicating a decrease in symptom severity over time (* P &lt; .05, ** P &lt; .01, *** P &lt; .001).

![Image](./Berthet2025_artifacts/image_000004_ee0004372d10cc4489c84285a3bac1ef51d07b68982f29d4da6452e921abd9e0.png)

The  observed  case-control  differences  in  CT  deviation scores at baseline, reflecting a relatively early clinical phase, suggest that brain differences might be observable before the onset of the first episode. Indeed, large metaof mega-analytic studies have shown that cortical alterations are present in the at-risk phase, 69 although it has also  been  shown  that  such  changes  explain  only  a  tiny proportion of the variance in regional deviations from a normative model for CT and do not predict conversion to psychosis. 70 In individuals at clinical high risk for psychosis,  multimodal  (including  brain  MRI  data)  prediction  of  the  negative  symptom  severity  appears  to  yield promising results. 71  The notion of a neurodevelopmental component in the etiology of severe mental disorders is in line with previously reported correlations between deviation in CT and a general psychopathology score in a population-based sample of children and adolescents. 72 However,  we  emphasize  that  our  data  cannot  inform directly  about  the  neurodevelopmental  antecedents  of schizophrenia because we lack information from important neurodevelopmental phases. 41

## Limitations

Our study is subject to several limitations. First, even with access to the national registry data, ensuring representative recruitment in clinical and population-based cohorts is  nontrivial,  e.g.,  inclusion  and  exclusion  criteria  of  patients 73,74 or bias on the selection of healthy subjects 75 or the retention of individuals with psychosis and CTRL. We are currently working on an extensive evaluation of these potential biases in normative models in separate work.

Second, whilst our findings are suggestive that secondgeneration antipsychotics may have different chronic effects on brain structure to first-generation antipsychotics, we were unable to test this directly because only a very small number of participants were taking first-generation antipsychotics in our sample. Further work is therefore necessary to test this more directly.

Third,  extreme  cortical  deviations  may  not  only  relate to schizophrenia-related pathologies but could also be markers of other effects, e.g., noise, artifacts, medications, co-morbidities, co-existing conditions, and various lifestyle and health-related behaviors or traits. 76  While we cannot rule out confounding effects, our quality control and  validation  procedures  against  clinical  and  registry data speak against this interpretation.

Finally,  we  acknowledge  that  our  sample  size  at  the third  follow-up  session  is  moderate,  and  this  reduction in  sample  size  could  have  biased  our  findings  at  later timepoints.  However,  we  would  like  to  emphasize  that: (i) the proportion of subjects retained in our study compares favorably to the retention rates reported in the literature, particularly in view of the 10-year follow-up period of this study and (ii) the effect size estimates we present (supplementary figures  3  and  4)  also  speak  against  the possibility  that  the  attenuation  of  effects  we  report  is only  attributable  to  a  reduction  in  sample  size.  Also, despite  best  efforts,  the  inclusion  of  different  scanners across different waves of the study may have influenced

Fig. 6. Results from the LME model testing for associations between symptom scores and cortical deviations (equation 2). We report man effects for the PANSS total domain (A), the PANSS general domain (B), PANSS negative symptoms (C), and a significant interaction in a single region between the PANSS negative scores and follow-up time (D). The color bar shows the effects size of each effect.

![Image](./Berthet2025_artifacts/image_000005_629fce9b0d20b47504ff9bec3cc4561aa4c70b40b7fbe359d3c1ebb8bdeb8bc3.png)

our findings. However, we have extensively validated the normative modeling framework that we employ in such settings  elsewhere  and  it  shows  good  performance. 50 Nevertheless, our findings should be considered preliminary at this stage and await replication in other cohorts.

## Conclusion

Using  a  unique  dataset  comprising  clinical  and  MRI data from a 10-year longitudinal study with patients with schizophrenia,  we  have  shown  an  apparent  gradual  reduction in case-control CT deviations from the first psychotic episode to the 10-year follow-up assessment, with some evidence of regionally distributed associations with clinical  symptoms  over  time.  This  study  demonstrates that transfer learning from large-scale reference normative models can be used to make meaningful comparisons of  MRI  features  between  participants  across  different scanners and provides preliminary evidence for cortical associations with longitudinal clinical outcomes in people with schizophrenia.

## Supplementary Material

Supplementary material is available at https://academic. oup.com/schizophreniabulletin/.

## Acknowledgments

O.A.  Andreassenb:  consultant  to  cortechs.ai,  speaker's honorarium from Sunvion, Janssen, Lundbeck. Regional PI for clinical trials funded by BI, MAPS, Janssen. Other authors do not report any conflict of interest.

## Funding

This study was supported by grant number 'BRAINCHART,'  215698/Z/19/Z  from  the  Wellcome Trust Innovator Award, the Research Council of Norway (223273, 287714), the KG Jebsen Stiftelsen, the European Research Council under the European Union's Horizon 2020 Research and Innovation program (ERC StG, grant 802998),  and  South-Eastern  Norway  Regional  Health

Authority (grants 2006233, 2006258, 2009037, 2011085, 2011096, 2012100, 2014102, 2015088, 2018093, 2019107, 2020086).

## References

1.  Insel  TR,  Cuthbert  BN.  Endophenotypes:  bridging  genomic complexity and disorder heterogeneity. Biol Psychiatry. 2009;66:988-989.
2.  Alnæs D, Westlye LT. Brain heterogeneity in schizophrenia and  its  association  with  polygenic  risk. JAMA  Psychiatry. 2019;76(739):1211-1212.
3.  Kaufmann T, van der Meer D, Doan NT, et al; Karolinska Schizophrenia Project (KaSP). Common brain disorders are associated with heritable patterns of apparent aging of the brain. Nat Neurosci. 2019;22:1617-1623.
4.  Barr  PB,  Bigdeli  TB,  Meyers  JL.  Prevalence,  comorbidity, and sociodemographic correlates of psychiatric diagnoses reported in the all of us research program. JAMA Psychiatry. 2022;79(622):622.
5.  Christensen MK, Lim CCW, Saha S, et al. The cost of mental disorders: a systematic review. Epidemiol  Psychiatr Sci. 2020;29:e161.
6.  Plana -Ripoll O, Musliner KL, Dalsgaard S, et al. Nature and prevalence of combinations of mental disorders and their association with excess mortality in a population -based cohort study. World Psychiatry. 2020;19:339-349.
7.  Solmi M, Radua J, Olivola M, et al. Age at onset of mental disorders  worldwide:  large-scale  meta-analysis  of  192  epidemiological studies. Mol Psychiatry. 2022;27:281-295.
8.  Lally J, Ajnakina O, Stubbs B, et al. Remission and recovery from first-episode psychosis in adults: systematic review and meta-analysis of long-term outcome studies. Br J Psychiatry. 2017;211:350-358.
9.  Friis  S,  Melle  I,  Johannessen  JO,  et  al.  Early  predictors  of ten-year  course  in  first-episode  psychosis. Psychiatr  Serv. (Washington, D.C.) 2016;67:438-443.
10.  Austin SF, Mors O, Budtz-Jørgensen E, et al. Long-term trajectories of positive and negative symptoms in first episode psychosis:  a  10  year  follow-up  study  in  the  OPUS  cohort. Schizophr Res. 2015;168:84-91.
11.  O'Keeffe  D,  Hannigan  A,  Doyle  R,  et  al.  The  iHOPE-20 study: relationships between and prospective predictors of remission, clinical recovery, personal recovery and resilience 20 years on from a first episode psychosis. Aust N Z J Psychiatry. 2019;53:1080-1092.
12.  Morgan C, Dazzan P, Lappin J, et al. Rethinking the course of psychotic disorders: modelling long-term symptom trajectories. Psychol Med. 2022;52:2641-2650.
13.  Patel PK, Leathem LD, Currin DL, Karlsgodt KH. Adolescent neurodevelopment and vulnerability to psychosis. Biol Psychiatry. 2021;89:184-193.
14.  Van  Haren  NEM.  Changes  in  cortical  thickness  during the course of illness in schizophrenia. Arch Gen Psychiatry. 2011;68(871):871.
15.  Barth C, Jørgensen KN, Wortinger LA, Nerland S, Jönsson EG, Agartz I. Trajectories of brain volume change over 13 years in  chronic  schizophrenia. Schizophr  Res. 2020;222:525-527.
16.  Haukvik UK, Hartberg CB, Nerland S, et al. No progressive brain changes during a 1-year follow-up of patients with firstepisode psychosis. Psychol Med. 2016;46:589-598.
17.  Jørgensen KN,  Nesvåg  R,  Gunleiksrud  S, Raballo A, Jönsson  EG,  Agartz  I.  First-  and  second-generation  antipsychotic  drug  treatment  and  subcortical  brain  morphology  in  schizophrenia. Eur Arch Psychiatry Clin Neurosci. 2016;266:451-460.
18.  Ansell  BRE,  Dwyer  DB,  Wood  SJ,  et  al.  Divergent  effects of first-generation and second-generation antipsychotics on cortical  thickness  in  first-episode  psychosis. Psychol  Med. 2015;45:515-527.
19.  Wiegand LC, Warfield SK, Levitt JJ, et al. Prefrontal cortical thickness in first-episode psychosis: a magnetic resonance imaging study. Biol Psychiatry. 2004;55:131-140.
20.  Nelson EA, Kraguljac NV, White DM, Jindal RD, Shin AL, Lahti AC. A prospective longitudinal investigation of cortical thickness and gyrification in schizophrenia. Can J Psychiatry. 2020;65:381-391.
21.  Zhang  W,  Xiao  Y ,  Sun  H,  et  al.  Discrete  patterns  of  cortical  thickness  in  youth  with  bipolar  disorder  differentially predict  treatment  response  to  quetiapine  but  not  lithium. Neuropsychopharmacology. 2018;43:2256-2263.
22.  Van  Erp  TGM,  Walton  E,  Hibar  DP,  et  al;  Karolinska Schizophrenia Project. Cortical brain abnormalities in 4474 individuals with schizophrenia and 5098 control subjects via the enhancing neuro imaging genetics through meta analysis (ENIGMA) consortium. Biol Psychiatry. 2018;84:644-654.
23.  Cannon TD, Chung Y , He G, et al; North American Prodrome Longitudinal  Study  Consortium.  Progressive  reduction  in cortical thickness as psychosis develops: a multisite longitudinal neuroimaging study of youth at elevated clinical risk. Biol Psychiatry. 2015;77:147-157.
24.  van  Erp  TG,  Hibar  DP,  Rasmussen  JM,  et  al.;  for  the ENIGMA Schizophrenia Working Group. Subcortical brain volume abnormalities in 2028 individuals with schizophrenia and 2540 healthy controls via the ENIGMA consortium. Mol Psychiatry. 2016;21:547-553.
25.  Kochunov P, Hong LE. Neurodevelopmental and neurodegenerative models of schizophrenia: white matter at the center stage. Schizophr Bull. 2014;40:721-728.
26.  Andreasen NC. The lifetime trajectory of schizophrenia and the concept of neurodevelopment. Dialogues Clin Neurosci. 2010;12:409-415.
27.  Insel TR. Mental disorders in childhood: shifting the focus from  behavioral  symptoms  to  neurodevelopmental  trajectories. JAMA. 2014;311:1727-1728.
28.  Wolfers  T,  Doan  NT,  Kaufmann  T,  et  al.  Mapping  the heterogeneous phenotype of schizophrenia and bipolar disorder using normative models. JAMA Psychiatry. 2018;75:1146-1155.
29.  Wolfers  T,  Rokicki  J,  Alnaes  D,  et  al.  Replicating  extensive brain structural heterogeneity in individuals with schizophrenia and bipolar disorder. Hum  Brain  Mapp. 2021;42:2546-2555.
30.  Clementz BA, Sweeney JA, Hamm JP, et al. Identification of distinct psychosis biotypes using brain-based biomarkers. Am J Psychiatry. 2016;173:373-384.
31.  Cuthbert  BN,  Insel  TR.  Toward  the  future  of  psychiatric  diagnosis:  the  seven  pillars  of  RDoC. BMC  Med. 2013;11(126):126.
32.  Farley  JD.  Phylogenetic  adaptations  and  the  genetics  of psychosis. Acta Psychiatr Scand. 1976;53:173-192.
33.  Insel TR. Rethinking schizophrenia. Nature. 2010;468:187-193.
34.  Insel TR, Cuthbert BN. Brain disorders? Precisely. Science. 2015;348:499-500.

35.  Rubio JM, Malhotra AK, Kane JM. Towards a framework to  develop  neuroimaging  biomarkers  of  relapse  in  schizophrenia. Behav Brain Res. 2021;402:113099.
36.  Andreasen NC, Nopoulos P, Magnotta V, Pierson R, Ziebell S, Ho B-C. Progressive brain change in schizophrenia: a prospective  longitudinal  study  of  first-episode  schizophrenia. Biol Psychiatry. 2011;70:672-679.
37.  Huhtaniska  S,  Jääskeläinen  E,  Heikka  T,  et  al.  Long-term antipsychotic  and  benzodiazepine  use  and  brain  volume changes in schizophrenia: the Northern Finland Birth Cohort 1966 study. Psychiatry Res Neuroimaging. 2017;266:73-82.
38.  Vita A, De Peri L, Deste G, Barlati S, Sacchetti E. The effect of antipsychotic treatment on cortical gray matter changes in schizophrenia:  Does  the  class  matter?  A  meta-analysis  and meta-regression of longitudinal magnetic resonance imaging studies. Biol Psychiatry. 2015;78:403-412.
39.  Canal-Rivero  M,  Ruiz-Veguilla  M,  Ortiz-García  de  la  Foz V, et al. Longitudinal trajectories in negative symptoms and changes in brain cortical thickness: 10-year follow-up study. Br J Psychiatry. 2023;223:309-318.
40.  Rutherford S, Fraza C, Dinga R, et al. Charting brain growth and aging at high spatial precision. eLife. 2022;11:e72904.
41.  Bethlehem RI, Seidlitz J, White SR, et al; 3R-BRAIN. Brain charts for the human lifespan. Nature. 2022;604:525-533.
42.  Antoniades M, Haas SS, Modabbernia A, et al. Personalized estimates  of  brain  structural  variability  in  individuals  with early psychosis. Schizophr Bull. 2021;47:1029-1038.
43.  Cole TJ. The development of growth references and growth charts. Ann Hum Biol. 2012;39:382-394.
44.  Marquand  AF,  Kia  SM,  Zabihi  M,  Wolfers  T,  Buitelaar JK,  Beckmann  CF.  Conceptualizing  mental  disorders  as deviations from  normative  functioning. Mol  Psychiatry. 2019;24:1415-1424.
45.  Marquand AF, Rezek I, Buitelaar J, Beckmann CF. Understanding  heterogeneity  in  clinical  cohorts  using  normative models: beyond case-control studies. Biol Psychiatry. 2016;80:552-561.
46.  Rutherford  S, Barkema  P,  Tso  IF,  et  al.  Evidence  for embracing normative modeling. Elife. 2023;12:e85082.
47.  Fraza CJ, Dinga R, Beckmann CF, Marquand AF. Warped Bayesian  linear  regression  for  normative  modelling  of  big data. Neuroimage. 2021;245(118715):118715.
48.  Marquand AF, Wolfers T, Mennes M, Buitelaar J, Beckmann CF.  Beyond  lumping  and  splitting:  a  review  of  computational  approaches  for  stratifying  psychiatric  disorders. Biol Psychiatry: Cognit Neurosci Neuroimag. 2016;1:433-447.
49.  Zabihi M, Oldehinkel M, Wolfers T, et al. Dissecting the heterogeneous  cortical  anatomy  of  autism  spectrum  disorder using  normative  models. Biol  Psychiatry  Cogn  Neurosci Neuroimaging. 2019;4:567-578.
50.  Gaiser C, Berthet P, Kia SM, et al. Estimating cortical thickness  trajectories  in  children  across  different  scanners  using transfer learning from normative models. Hum Brain Mapp. 2024;45:e26565.
51.  Kay SR, Fiszbein A, Opler LA. The Positive and Negative Syndrome Scale (PANSS) for schizophrenia. Schizophr Bull. 1987;13:261-276.
52.  Destrieux  C,  Fischl  B,  Dale  A,  Halgren  E.  Automatic parcellation of human cortical gyri and sulci using standard anatomical nomenclature. Neuroimage. 2010;53:1-15.
53.  Rosen  AFG,  Roalf  DR,  Ruparel  K,  et  al.  Quantitative assessment of structural image quality. Neuroimage. 2018;169:407-418.
54.  Kia, S. M. et al. Hierarchical Bayesian regression for multi-site normative modeling of neuroimaging data. In: Proceedings of the 23rd international conference of Medical Image Computing and Computer Assisted Intervention (MICCAI) , Lima, Peru, 2020.  Published  in  Lecture  Notes  in  Computer  Science, Springer 2020.
55.  Kia SM, Huijsdens H, Rutherford S, et al. Closing the lifecycle  of  normative  modeling  using  federated  hierarchical Bayesian regression. PLoS ONE. 2022;17:e0278776.
56.  Ducharme S, Albaugh MD,  Nguyen  T-V, et al; Brain Development Cooperative Group. Trajectories of cortical thickness maturation in normal brain development-the importance of quality control procedures. Neuroimage. 2016;125:267-279.
57.  Monereo-Sánchez J, de Jong JJA, Drenthen GS, et al. Quality control strategies for brain MRI segmentation and parcellation: practical approaches and recommendations-insights from the Maastricht study. Neuroimage. 2021;237(118174):118174.
58.  Rutherford  S,  Kia  SM,  Wolfers  T,  et  al.  The  normative modeling  framework  for computational psychiatry. Nat Protoc. 2022;17:1711-1734.
59.  Bayer JMM, Dinga R, Kia SM, et al. Accommodating site variation  in  neuroimaging  data  using  normative  and  hierarchical Bayesian models. Neuroimage. 2022;264:119699.
60.  Benjamini  Y ,  Hochberg  Y .  Controlling  the  false  discovery rate: a practical and powerful approach to multiple testing. J R Stat Soc B: Stat Methodol. 1995;57:289-300.
61.  Wolfers T, Beckmann CF, Hoogman M, Buitelaar JK, Franke B,  Marquand AF. Individual differences  v.  the  average  patient: mapping the heterogeneity in ADHD using normative models. Psychol Med. 2020;50:314-323.
62.  Walton  E,  Hibar  DP,  van  Erp  TGM,  et  al;  Karolinska Schizophrenia  Project  Consortium  (KaSP).  Positive  symptoms associate with cortical thinning in the superior temporal gyrus  via  the  ENIGMA  Schizophrenia  consortium. Acta Psychiatr Scand. 2017;135:439-447.
63.  Lv J, Di Biase M, Cash RFH, et al. Individual deviations from normative models of brain structure in a large cross-sectional schizophrenia cohort. Mol Psychiatry. 2021;26:3512-3523.
64.  Sasabayashi D, Takayanagi Y , Takahashi T, et al. Reduced cortical thickness of the paracentral lobule in at-risk mental state  individuals  with  poor  1-year  functional  outcomes. Transl Psychiatry. 2021;11(396):396.
65.  Barber L, Reniers R, Upthegrove R. A review of functional and structural neuroimaging studies to investigate the inner speech  model  of  auditory  verbal  hallucinations  in  schizophrenia. Transl Psychiatry. 2021;11(582):582.
66.  Wolke D, Waylen A, Samara M, et al. Selective drop-out in longitudinal studies and non-biased prediction of behaviour disorders. Br J Psychiatry. 2009;195:249-256.
67.  Pantelis C, Velakoulis D, McGorry PD, et al. Neuroanatomical abnormalities before and after onset of psychosis: a cross-sectional  and  longitudinal  MRI  comparison. Lancet. 2003;361:281-288.
68.  Wylie  KP,  Tregellas  JR.  The  role  of  the  insula  in  schizophrenia. Schizophr Res. 2010;123:93-104.
69.  Jalbrzikowski  M,  Hayes  RA,  Wood  SJ,  et  al;  ENIGMA Clinical High Risk for Psychosis Working Group. Association of structural magnetic resonance imaging measures with psychosis onset in individuals at clinical high risk for developing psychosis: an ENIGMA working group megaanalysis. JAMA Psychiatry. 2021;78:753.
70.  Haas,  SS,  Ge  R,  Agartz  I,  et  al.  Normative  modeling  of brain  morphometry  in  clinical  high-risk  for  psychosis.

- biorxiv 2023.  doi:10.1101/2023.01.17.523348,  preprint:  not peer reviewed.
71.  Hauke DJ, Schmidt A, Studerus E, et al; PRONIA Group. Multimodal  prognosis  of  negative  symptom  severity  in  individuals  at  increased  risk  of  developing  psychosis. Transl Psychiatry. 2021;11:312.
72.  Kjelkenes  R,  Wolfers  T,  Alnæs  D,  et  al.  Deviations  from normative brain white and gray matter structure are associated with psychopathology in youth. Deve Cognit Neurosci. 2022;58:101173.
73.  Murray GK, Lin T, Austin J, McGrath JJ, Hickie IB, Wray NR. Could polygenic risk scores be useful in psychiatry? A review. JAMA Psychiatry. 2021;78:210-219.
74.  Taipale  H,  Schneider-Thoma J,  Pinzón-Espinosa J, et  al. Representation and outcomes of individuals with schizophrenia  seen  in  everyday  practice  who  are  ineligible  for randomized clinical trials. JAMA Psychiatry. 2022;79:210-218.
75.  Fry  A,  Littlejohns  TJ,  Sudlow  C,  et  al.  Comparison  of sociodemographic and health-related characteristics of UK biobank participants with those of the general population. Am J Epidemiol. 2017;186:1026-1034.
