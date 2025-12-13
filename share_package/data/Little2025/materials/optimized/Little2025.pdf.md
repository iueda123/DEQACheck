![Image](./Little2025_artifacts/image_000000_81bf13e7a182cfcc2a49cff2e6fd5648cddd95526918ebb7d91291dd4baf0826.png)

## Brain morphology normative modelling platform for abnormality and centile estimation: Brain MoNoCle

Bethany Little a,b , Nida Alyas a , Alexander Surtees c , Gavin P Winston d,e , John S Duncan d , David A Cousins b,f , John-  Paul Taylor b , Peter Taylor a,b,d , Karoline Leiberg a, *, Yujiang Wang a,b,d, *

a CNNP Lab (www  .  cnnp  -  lab  .  com), School of Computing, Newcastle University, Newcastle upon Tyne, United Kingdom b Faculty of Medical Sciences, Newcastle University, Newcastle upon Tyne, United Kingdom

c Research Software Engineers, Newcastle University, Newcastle-  upon-  Tyne, United Kingdom

d UCL Queen Square Institute of Neurology, Queen Square, London, United Kingdom

e Department of Medicine (Division of Neurology), Queen's University, Kingston, Canada

f Cumbria, Northumberland Tyne and Wear NHS Foundation Trust, Newcastle upon Tyne, United Kingdom

*Joint senior authors

Corresponding Author: Yujiang Wang (yujiang.wang@newcastle.ac.uk)

## ABSTRACT

Normative models of brain structure estimate the effects of covariates such as age and sex using large samples of healthy controls. These models can then be applied to, for example, smaller clinical cohorts to distinguish disease effects from other covariates. However, these advanced statistical modelling approaches can be difficult to access, and processing large healthy cohorts is computationally demanding. Thus, accessible platforms with pre-  trained normative models are needed. We present such a platform for brain morphology analysis as an open-  source web application https://cnnplab  .  shinyapps  .  io  /  BrainMoNoCle/, with six key features: (i) user-  friendly web interface, (ii) individual and group outputs, (iii) multi-  site analysis, (iv) regional and whole-  brain analysis, (v) integration with existing tools, and (vi) featuring multiple morphology metrics. Using a diverse sample of 3,276 healthy controls across 21 sites, we pre-  trained normative models on various metrics. We validated the models with a small sample of individuals with bipolar disorder, showing outputs that aligned closely with existing literature only after applying our normative modelling. Using a cohort of people with temporal lobe epilepsy, we showed that individual-  level abnormalities were in line with seizure lateralisation. Finally, with the ability to investigate multiple morphology measures in the same framework, we found that biological covariates are better explained in specific morphology measures, and for applications, only some measures are sensitive to the disease process. Our platform offers a comprehensive framework to analyse brain morphology in clinical and research settings. Validations confirm the superiority of normative models and the advantage of investigating a range of brain morphology metrics together.

Keywords: normative model, brain structure, structural abnormality, morphology, bipolar disorder, temporal lobe epilepsy

## 1. INTRODUCTION

Brain  morphology,  the  study  of  the  shape  and  size  of brain structures, can be used to track healthy brain development and detect abnormalities associated with underlying  disease  processes.  Normative  modelling  of  brain morphology uses large and diverse datasets to estimate healthy  variance  across  the  lifespan.  In  neuroscience research, normative models can reliably remove biological  and  technical  covariates  from  unseen  data  without removing, for example, disease effects (  Pomponio   et al.,

Received: 10 July 2024  Revision: 24 October 2024  Accepted: 25 November 2024  Available Online: 2 January 2025

![Image](./Little2025_artifacts/image_000001_fbc664191eafcd72ec5c0e10d43c86efa71e16077f2d080e829dbe181c8c5eea.png)

![Image](./Little2025_artifacts/image_000002_e5aa1cafeb3166be4af5f3d69798af266f3c2177e280815b4dc98dd89b47c61c.png)

2020), which is especially valuable for small samples with a limited number of control subjects. Further, the ability of normative modelling to estimate abnormalities in individuals is crucial for clinical applications, as it enables systematic biomarker discovery and supports translational uses  in  diagnosis,  stratification,  and  localisation  (  Little et al.,   2024;   Loreto   et al.,   2024;   P .   N.   T aylor   et al.,   2022). Therefore, normative modelling of brain morphology is an indispensable  framework  that  should  be  available  and accessible to all researchers.

To enable researchers without high levels of technical/ statistical know-  how, or resources for time and computationally  demanding tasks,  to  benefit  from  the  power  of the  normative  modelling  framework,  a  freely  available, pre-  trained modelling platform is needed. The normative models should be based on large, diverse, healthy population  data,  and  be  easily  applied  to  new  data.  Recent efforts  in  this  field  include  several  open-  source  tools, some allowing users to upload new, unseen brain morphology data to a web interface and generate individual abnormality scores (e.g. z-  scores or centiles) (  Bethlehem et al.,   2021;   Ge   et al.,   2024;   Rutherford   et al.,   2023). Each of  these  tools  has  some  advantages:  for  example,  (i) being accessible as an online tool that can be easily used without  any  need  for  software  download/installation  or writing/running scripts, (ii) providing individual and grouplevel outputs, (iii) multi-  site data-  as often seen in neuroscience research-  can be analysed, (iv) analysing brain shape  on  whole  hemispheres  and  smaller  regions,  (v) seamless integration with existing neuroimaging software such as FreeSurfer, and (vi) the option to explore a variety of metrics.

We present a normative modelling tool of brain morphology that combines all six key features in one open and web-  based application: Brain MoNoCle (Brain Morphology Normative modelling platform for  abnormality and Centile estimation). We included a large and diverse sample of 3,276 healthy controls across 21 sites to pretrain normative models in a variety of brain morphology measures that comprehensively quantify cortical shape, including  three  novel  metrics  that  were  only  recently proposed (  Wang   et al.,   2021).  As  a  first  validation,  we demonstrate how normative modelling improves reliability and reproducibility in a small clinical dataset of individuals  with  bipolar  disorder  (BD).  We  further  validate our outputs in a dataset of temporal lobe epilepsy (TLE) at the group level, and illustrate how individual patient abnormality  scores  agree  with  their  seizure  lateralisation. Finally, with the option to explore a variety of morphology  metrics  on  our  platform,  we  highlight  the importance of investigating multiple metrics at the same time both for normative modelling itself and for clinical applications.

## 2. MATERIALS AND METHODS

## 2.1. Normative data

We  collated  3T  T1-  weighted  MRI  scans  from  3,276 healthy individuals from several large public and in-  house datasets, detailed in Supplementary Materials S1 (  Greene et al.,   2018;   Himmelberg   et al.,   2023;   LaMontagne   et al., 2019;   Nigg   et al.,   2023;   Nooner   et al.,   2012;   Nugent   et al., 2022;   Shafto   et  al.,    2014;    J.    R.    T aylor    et  al.,    2017;    Van Essen   et al.,   2013;   Zareba   et al.,   2022). The age in the total dataset ranged from 5 to 95 years old; the age range and  sex  distribution  for  each  study  are  illustrated  in Figure 1. Scanning protocols differed across, and sometimes within datasets, which we corrected statistically in a later step. All studies had ethical approval from relevant institutional ethics boards and included written consent from  participants.  We  present  here  the  initial  dataset included in v1.0 of our app; however, we aim to continuously add to our normative reference dataset. Users of our web platform should, therefore, check the latest summary  of  the  dataset  shown  in  the  app  when  reporting their own results.

## 2.2. Pre-  processing

T1-  weighted  MRI  scans  were  pre-  processed  in  FreeSurfer (  Fischl,   2012) using the standard recon-  all pipeline, which includes removal of non-  brain  tissue,  segmentation of grey and white matter surfaces, and cortical parcellation. We also ran the localGI pipeline (  Schaer   et al., 2012) to yield smoothed outer pial surfaces. The aparcstats2table command was used to generate measures of cortical thickness, cortical volume, and pial surface area, for  68  brain  regions  according  to  the  Desikan-  Killiany parcellation  atlas  (  Desikan    et  al.,    2006).  The  version  of FreeSurfer  varied  across  datasets  (see  Supplementary Materials  S1),  which  was  corrected  during  site/batch harmonisation.

## 2.3. Cortical morphology measures

The traditional morphological measures of cortical thickness,  pial  surface  area,  and  exposed  surface  area  are known to covary (  Wang   et al.,   2016). Failure to account for  this  covariance  can  lose  and  confuse  information about the complex, folded shape of the brain. A recently developed framework proposed a universal scaling law of cortical folding that accounts for covariance between cortical thickness, pial surface area, and exposed surface area (  Mota   &amp;   Herculano-  Houzel,   2015). From this scaling law, three biologically interpretable independent components, K, I, and S, can be derived for hemispheres, lobes, and individual regions (  Leiberg   et al.,   2021;   Wang   et al.,

Fig. 1. Demographics of the data underlying the normative model. Age distributions and proportion of female participants are shown for each study. Some studies contained multi-site data (e.g. MEGUK), but these are not shown separately here.

![Image](./Little2025_artifacts/image_000003_b9388daceac17a89f6202a39657fb65157be0a246ff7d7c3e831ba8f43a3ef52.png)

2016,   2019,   2021). The dimensionless measure K reflects tension acting on the cortex and is relatively preserved across species, but appears to be sensitive to ageing and disease  processes  (  Leiberg    et  al.,    2021;    Wang    et  al., 2019,   2021). Isometric term I is orthogonal and statistically  independent to K and captures information about isometric size. S is a cross-  product of K and I that captures  all  remaining  information  about  shape,  reflecting complexity of cortical folding. For example, if a cortical structure  is  isometrically  rescaled  in  all  dimensions,  it changes I, but not K or S. K, I, and S are orthogonal and statistically independent to each other.

As  an  example,  in  TLE,  these  components  captured structural changes that were not detected with traditional metrics (  Wang   et al.,   2021). K, I, and S, therefore, offer a novel re-  conceptualisation of brain morphology measures that can detect nuanced morphological abnormalities. We used the toolbox developed by   Wang   et al.   (2021) (https:// github  .  com  /  cnnp  -  lab  /  CorticalFoldingAnalysisT ools) to calculate K, I, and S for each hemisphere.

## 2.4. Quality control

Some of the public datasets included quality control steps as part of the study design, which are reported in the original study publications. We detected outliers in the entire dataset statistically: we ran our gamlss model described below  for  each  structural  metric  for  each  region,  and flagged outliers defined by residuals more than five median absolute deviations. In addition, we also detected outliers based on visual inspections of plots: for each dataset, we plotted each brain metric at the hemisphere level against age to flag outliers within each dataset. These were then cross  checked  with  outliers  that  were  detected  statistically. We excluded participants who were flagged as an outlier in any of these models; we performed listwise deletion rather than pairwise deletion so that the same normative reference dataset was used for each normative model, allowing comparisons of model statistics across models.

## 2.5. Exemplar clinical datasets

To demonstrate the utility of our normative models in predicting abnormalities in patient groups and individuals, we included two exemplar clinical datasets. A sample of 133 adults  with  mesial  TLE  (mTLE;  n  =  74  right  hemisphere seizure onset; n = 59 left hemisphere seizure onset) and 99 healthy controls (HC-  mTLE) were acquired from the recent IDEAS dataset release (  P .   N.   T aylor   et al.,   2024), which was approved by a Research Ethics Committee (22/SC/0016). We also included a sample of 56 adults with bipolar disorder (BD) and 26 healthy controls (HC-  BD) from the Bipolar Lithium Imaging and Spectroscopy Study (BLISS) (  Little et al.,   2024;   Smith   et al.,   2018). The study was granted a favourable ethical opinion by a United Kingdom National Research Ethics Committee (14/NE/1135), and all participants provided written informed consent.

Descriptive  statistics  for  these  datasets  are  summarised in Table 1. See the respective publications for full details of each sample and neuroimaging pre-  processing steps.

## 2.6. Software structure

We  designed  our  software  as  an  R  Shiny  App,  and  it includes  three  aspects:  (i)  Pre-  training  normative  models

Table 1. Demographics of two clinical datasets.

|                    | BD            | HC-  BD       | mTLE left   | mTLE right   | HC-  mTLE   |
|--------------------|---------------|---------------|-------------|--------------|-------------|
| N                  | 56            | 26            | 74          | 59           | 99          |
| Age [mean (SD)]    | 45.36 (12.15) | 48.46 (11.80) | 36.0 (11.2) | 38.2 (10.8)  | 39.1 (12.1) |
| Sex [n female (%)] | 35 (62%)      | 12 (46%)      | 43 (58%)    | 39 (66%)     | 62 (63%)    |

SD = standard deviation; HC = healthy controls; BD = bipolar disorder; mTLE = mesial temporal lobe epilepsy.

for  each  morphological  measure,  which  can  be  readily used without re-  fitting statistical models to normative data. (ii) At the back-  end of the R Shiny App, computing z-  scores and centiles for new unseen data based on the pre-  trained models. (iii) At the front-  end of the R Shiny App, providing the  user  with  an  intuitive  interface  that  accepts  outputs directly from existing neuroimaging software, such as FreeSurfer. Each aspect is summarised below, and more technical details can be found in the Supplementary Materials.

## 2.6.1. Pre-  training normative models

All  brain  metrics  from  the  normative  data  were  logtransformed  before  being  used  to  train  the  normative models, so different metrics measuring different dimensionalities (e.g. thickness vs. surface area) can be treated in  the  same  way  for  the  normative  model.  Generalized additive models for location scale and shape (GAMLSS) using the gamlss package (https://cran  .  r  -  project  .  org  /  web /  packages  /  gamlss/) were used to simultaneously model the  parameters  (mean,  standard  deviation,  skew,  and kurtosis) of the distribution as response variables of the explanatory variables sex, age, and scanning site/batch. Specifically,  in  our  model,  the  mean  depends  on  sex (fixed effect), site (random effect), and a smooth function of  age;  the  standard  deviation  depends  on  sex  (fixed effect),  site  (random  effect),  and  a  smooth  function  of age; the skew depends on sex (fixed effect) and a smooth function of age; and the kurtosis depends on a smooth function of age. See the Supplementary Materials S2 for a more detailed description and justification of the statistical model. Normative models were fitted independently for  each  region  (from  the  Desikan-  Killiany  atlas)  and hemisphere, and each morphometric measure. Residuals were retained for later visualisations.

## 2.6.2. Predicting abnormalities in unseen data

The pre-  trained  normative  models  are  implemented  on the R Shiny App back-  end to score new, unseen individuals. A healthy control (HC) cohort from the new unseen site/batch is currently required.

First, we predicted the distribution parameters based on the HCs in each new site/batch and calculated resid- uals relative to one of the normative scanning sites. The mean of the residuals from the HCs in the unseen data was  then  used  to  calculate  the  site-  specific  offset needed to harmonise the unseen dataset with the normative data.

To obtain z-  score, we then calculated the residuals for each individual in the new unseen data relative to their site mean and divided by a standard deviation. The latter is calculated as follows: if there are less than 30 HCs, we used the average standard deviation seen across normative data sites/batches in the pre-  trained normative models. We estimated the standard deviation from the unseen HCs only if there were 30 or more HCs in the dataset. This approach ensures accurate estimations of new sites' standard deviations, as a sample size of 30 provides a 55%  probability  of  being  within  10%  accuracy  and  a 95% probability of being within 25% accuracy (  Schillaci &amp;   Schillaci,   2022).

The site-  specific mean, (site-  specific) standard deviation, skew, and kurtosis from the pre-  trained normative model were used to calculate centiles. See the Supplementary Materials S2 for a detailed description of the statistical pipeline.

## 2.6.3. Using the Brain MoNoCle web user interface

To  run  our  pipeline  to  predict  abnormalities  in  unseen data  as  described  above,  we  used  our  web  platform Brain MoNoCle. Users can follow the same steps to run the pipeline on their own data.

First,  we uploaded pre-  processed brain imaging data tables. For traditional brain imaging metrics, data should be  pre-  processed  using  FreeSurfer  (e.g.,  the  standard recon-  all command) and the structural  metrics  for  each hemisphere  should  be  exported  as  csv  files  using  the aparcstats2table command;  then  the  csv  file  for  each hemisphere can be directly uploaded to our web interface. For  morphology measures of independent components, the data tables from our cortical folding toolbox (https:// github  .  com  /  cnnp  -  lab  /  CorticalFoldingAnalysisT ools) can be directly  uploaded.  We  also  uploaded  meta-  data  in  a csv file containing subject IDs, age, sex, group, dataset, scanning site, and session. After selecting 'Run Model' to start the analysis, z-  scores, group summary statistics, and

centiles are available to view and download as csv files, using the tabs in the main panel. Users can export plots by selecting the 'Brain plot' and 'Scatter plot' tabs. A html report is available to download using the 'Report' tab.

## 2.7. Statistical analysis

All statistical analyses were performed in R Studio v4.3.2. Each test and associated sample size are stated in the results section.

## 3. RESULTS

## 3.1. Pre-  trained normative models on web platform

To pre-  train normative models, we used data from 3,276 healthy individuals from several large public and in-  house datasets, detailed in Supplementary Materials S1 (  Greene et al.,   2018;   Himmelberg   et al.,   2023;   LaMontagne   et al., 2019;   Nigg   et al.,   2023;   Nooner   et al.,   2012;   Nugent   et al., 2022;    Shafto    et  al.,    2014;    J.    R.    T aylor    et  al.,    2017;    Van Essen   et al.,    2013;    Zareba    et  al.,    2022).  We  focused  on including  a  variety  of  scanning  protocols/sites  (total  21 sites) to enable mixed-  effect modelling, and we achieved a larger overall sample size than recommended by previous normative models (  Ge   et al.,   2024). We performed a subsampling analysis that showed our models stabilised, see Supplementary Materials S3 for details. The age in the total normative dataset ranged from 5 to 95 years old and is shown by data source in Figure 1. With these data, we pre-  trained  our  normative  models  for  the  whole  hemisphere,  each  brain  region,  and  morphology  metric  (see Section  2  for  details  of  statistical  models).  We  incorporated these pre-  trained normative models on a web platform (Brain MoNoCle) to allow users to upload their own datasets to find morphological abnormalities in individuals and groups. In the following, we will validate our normative modelling framework and web platform outputs in a variety of ways, and demonstrate some biological insight.

## 3.2. Cortical thickness abnormalities in a sample of patients with bipolar disorder more closely match previous findings when using the normative model

To validate the normative modelling framework and outputs, we first investigated the group-  level differences in a small, well-  defined clinical cohort of bipolar disorder (BD, n = 56) and matched controls (BD-  HC, n = 26). We specifically wanted to see the difference in outputs between using  a  traditional  case-  control  comparison  approach only using the matched controls (Fig. 2A) vs. using our normative model instead (Fig. 2B). When using the small BD-  HC  group,  effect  sizes  (Cohen's  d)  suggested  that cortical  thinning  was  greatest  in  the  left  post-  central gyrus (d = -  0.85) and that the cortex was thicker in BD in the left pre-  central gyrus (d = 0.70). However, when using the normative model pipeline, the same sample showed similar thinning in the left post-  central gyrus (d = -  0.83), but cortical thinning in the left pre-  central gyrus (d = -  0.8). The latter of these findings, obtained through normative modelling, is more in line with previous findings from a large  sample  ENIGMA  study  showing  cortical  thinning across the cortex (  Hibar   et al.,   2018).

## 3.3. Group-  level cortical thickness abnormalities in mesial temporal lobe epilepsy agree with previous findings

We  validated  our  normative  model  outputs  in  a  large sample of individuals with mTLE (74 left mTLE, 59 right mTLE) and matched controls (n = 99) (  P .   N.   Taylor   et al., 2024).  Figure  3  shows  cortical  thickness  abnormality estimates for right mTLE and left mTLE groups. We found widespread cortical thinning, especially in the right mTLE group, in cortical regions including the precentral gyrus, supramarginal  gyrus,  and  inferior  parietal  gyrus.  This result reproduces both the findings from the IDEAS and ENIGMA-  epilepsy  studies.  We  quantitatively  assessed this by correlating the effect sizes for each brain region

Fig. 2. Alterations in cortical thickness associated with bipolar disorder derived from a case-  control study vs. normative modelling. Group-  level abnormalities in cortical thickness in n = 56 people with bipolar disorder for (A) a small, matched control group (n = 26) and (B) the normative reference population (n = 3,276).

![Image](./Little2025_artifacts/image_000004_78aab4e7443032c847588e188354156caeee36f8772b43e22a272e0e8441b510.png)

Fig. 3. Group-  level output for mesial temporal lobe epilepsy cohort after normative modelling. Group-  level summary of abnormalities in cortical thickness for left mTLE (n = 74, A) and right mTLE (n = 59, B), showing Cohen's d effect size for each cortical region.

![Image](./Little2025_artifacts/image_000005_b88df4ae37b2baa48bae749f4c0f9eceddb3d4095d3db9e6533890da4ada933e.png)

generated by Brain MoNoCle in our mTLE sample with the effect sizes reported in the ENIGMA study (  Whelan et  al.,    2018);  results  showed  agreement  between  both sets of effect sizes (see Supplementary Materials S4.1).

As a supplementary step, we compared the z-  scores for each brain region produced by Brain MoNoCle with z-  scores produced by a similar normative modelling platform, CentileBrain, using the IDEAS healthy control group (n  =  99).  Results  show  good  agreement  between  both apps (correlation larger than 0.75 in approx. 80% of brain regions, see Supplementary Materials S5).

## 3.4. Individual-  level abnormalities in certain measures agree with clinical lateralisation of seizure onset

To  validate  individual-  level  outputs  and  abnormalities, we  used  seizure  lateralisation  from  the  IDEAS  dataset (  P.   N.   T aylor   et al.,   2024). For each subject, we extracted the z-  score difference between left and right hemispheres in cortical thickness and other metrics (Fig. 4). Controls, as expected, had a distribution around zero in all measures after regressing out healthy biological covariates.

The  measure  most  frequently  used  in  cortical  morphometry, cortical thickness, did not lateralise individual patients well, and most patient z-  scores differences were within the same range and distribution as the controls.

Given that our normative modelling platform offers the ability  to  analyse  multiple  morphology  measures,  and given  that  cortical  thickness  is  known  to  covary  with other measures (such as surface area and volume), we investigated  all  metrics  implemented  on  the  platform. This included three statistically independent novel measures.  We  demonstrated that both cortical volume and I-  our novel morphometric for isometric size-  were best at lateralising at the hemisphere level (Fig. 4). Specifically, for cortical volume, most (78.2%) patients had a z-  score difference greater/smaller than zero, indicating lateralisation in agreement with clinical metadata. For I, 75.9% of

![Image](./Little2025_artifacts/image_000006_a84b935d452341fdb75b4827596c29595ecb98c3bd16320a90aecc20ee81920d.png)

Hemispheric z-score

difference

Fig. 4. Individual-  level z-  scores after normative modelling for mTLE cohort. Difference in hemisphere-  level z-  score between left and right hemisphere is shown in controls and right/left TLE subgroups for three example morphological measures. Individual subjects are shown as single data points, distributions of subjects are displayed as violin plots.

patients showed the correct laterality. Further, 36.1% of patients  were  outside  of  2  standard  deviations  of  the control for cortical volume, and 32.3% for I. Confusion matrices showing predictive performance for lateralisation using the sign of the z-  score difference between left and right hemisphere can be found in the Supplementary Materials S4.2. Overall performance accuracy was larger than 0.75 for both volume and I.

## 3.5. Covariates explain more variance in independent component K than in other structural MRI measures

Given the observed specificity in particular measures for seizure lateralisation, we explored the differences between morphological  measures  further  to  establish  a  baseline

Fig. 5. Variance explained by normative model in each morphometric. (A &amp; B) Harmonised normative data (grey dots) and predicted model centiles of mean cortical thickness and K across the lifespan (n = 3,276). (C) Model fit statistics R 2 for each metric and hemisphere. CT, CV, and SA are structural metrics estimated using FreeSurfer; T, At, Ae, K, I, and S are structural metrics estimated using the Cortical Folding toolbox. CT = cortical thickness, CV = cortical volume, SA = surface area, T = average thickness, At = total pial surface area, Ae = exposed surface area.

![Image](./Little2025_artifacts/image_000007_be8bbceaac8572935f39e1d723971fb7d80ba426b9b42d7b2e978cbee933f3c8.png)

for  future  applications.  To  this  end,  we  investigated  the normative models accounting for age, sex and scanning site for each measure.

Figure  5A  and  B  shows  the  fitted  normative  model over age for two example measures: cortical thickness and K-  a novel independent morphometric that is known to change with age (  Wang   et al.,   2021). Both thickness and K decrease over age with steeper declines in early and later life.

To compare morphology measures more directly, we obtained the R 2 of the normative model fit for each measure, Figure 5C. All measures used the same statistical model formulas of age, sex, and site. Out of all the measures implemented, K shows the best model fit ( R 2 = 0.8 for both left and right hemisphere), superior to all other metrics with R 2 around or below 0.6.

## 4. DISCUSSION

## 4.1. Summary

Brain MoNoCle is a user-  friendly online normative modelling platform for brain morphology analysis. The platform combines and unifies the most frequently requested and desired features of existing approaches and toolboxes in one, importantly including the option to analyse multiple morphology  measures  under  one  framework.  We  validated our normative models and platform outputs in clinical cohorts through a series of tests, including replicating previous findings from ENIGMA studies. We also provided an individual-  level output validation in a sample of mTLE by demonstrating agreement of our outputs with the clinical seizure lateralisation. Particularly, we highlighted that both biological covariates, as well as disease processes, are  uniquely  expressed  in  different  morphological  measures. This implies that brain normative modelling should be performed in a range of measures to be useful for brain morphology analysis in health and disease.

## 4.2. Validations

We  demonstrated  how  our  pipeline  can  be  applied  to clinical datasets and what outputs can be obtained in a sample of people with BD, and a sample of people with mTLE. Compared with the traditional case-  control pipeline, in which BD patients were compared with matched healthy  controls,  our  normative  pipeline  yielded  abnormality  estimates  that  were  more  in  line  with  previous research,  for  example,  a  large-  scale  ENIGMA  study  of cortical thickness in BD, despite a relatively small patient sample (  Hibar   et al.,   2018). In mTLE, we showed results similar to a recent report of the data release (  P .   N.   Taylor et al.,   2024). In particular, we did not find large abnormalities in mTLE patients; this may be because we only show cortical  data  and  mTLE  are  associated  with  structural changes in subcortical regions, such as the hippocampus (  Whelan   et al.,   2018). We also tested for lateralisation of the hemispheric abnormalities, and found a generally

good  agreement  with  clinical  metadata,  despite  only using cortical data. The reported effect sizes compared with controls are in line with previous reports of lateralisation using cortical information only (  Pustina   et al.,   2015; Whelan    et  al.,    2018).  We  conclude  that  our  normative models  provide  reasonable  outputs  in  small  and  large samples, and that individual-  level outputs are also in line with expectations. We hope Brain MoNoCle will be helpful in future analysis of cortical morphology.

## 4.3. Normative sample

We trained  our  models  using  healthy  control  data  from several large databases (Supplementary Materials S1 and S6), similar to other normative modelling approaches. Our model is stable with the current sample size of 3,276 subjects. However, in mixed effect modelling, the number of levels in the random effect (i.e., the number of sites) is also critical. It is recommended to use a fixed effect if there are fewer than 10 levels, and to apply caution with 20 levels as a random effect. We included 21 sites, and suggest that future  work  should  prioritize  increasing  the  number  of sites in normative datasets, with diverse global representation, rather than focusing solely on total subject count.

## 4.4. Methodological advance

Our normative models and web platform are an addition to  existing  free  toolboxes  for  modelling  cortical  morphometry (  Atty é et al.,   2024;   Bethlehem   et al.,   2021;   Ge et  al.,    2024;    Manj ó n    &amp;    Coup é ,    2016;    Rutherford    et  al., 2023). Brain MoNoCle, however, differs to all of these in some ways, including no requirement for any coding or running scripts from the user, no need to download software, outputs include group-  level analysis compared with controls as well as individual abnormalities as z-  scores and centiles, analysis is on full hemispheres and regions, and outputs are visualised in plots and available as tables of  z-  scores  and  centiles.  There  are  differences  in  the underlying models as well. For example, our model harmonises  scanning  site  effects  for  mean  and  variance, avoiding  separate  steps  as  found  in  Combat  (  Johnson et al.,   2007), which makes separate assumptions. Specifically,  Combat  assumes  that  after  removing  covariate effects  on  the  mean,  the  data  are  normally  distributed, and  site  effects  on  mean  and  variance  are  estimated based on this assumption. However, this is often not the case,  since  data  may  follow  a  non-  normal  distribution with higher distribution parameters of skew and kurtosis also depending on covariates. Further, in our model, information is pooled from the male and female populations to directly estimate sex covariate effects, and we use flexible  smooth  terms  for  age  effects  and  explicitly  model skew and kurtosis with GAMLSS. Lastly, our web platform  includes  normative  models  of  a  range  of  metrics, including traditional measures such as thickness, volume, and pial surface area, but also independent morphometrics which account for the covariance of those measures.

## 4.5. New biological insight

Through our exploration of multiple cortical morphometrics, we were able to compare normative models for traditional measures, such as cortical thickness and surface area,  but  also  novel  statistically  independent  morphometrics. We found that one of these novel morphometrics 'K'  (also  termed  'tension  component')  displayed  a  far superior performance as a normative model of age and sex  with R 2 = 0.8 compared  with  other  morphometrics that achieve R 2 between 0.4 and 0.6. This observation has two implications: firstly, there might be better morphometrics  to  use  to  model  age  and  sex  effects,  and extract disease-  specific effects, as alluded to in the first paper  proposing  'K'  as  a  novel  morphometric  (  Wang et al.,   2021). Secondly, traditional morphometrics clearly have residual unexplained variance due to their statistical interdependence. This implies that investigations of measures such as cortical thickness and surface area should consider their covariance, rather than interpreting them in isolation.  The  cortex,  as  a  biological  structure  obeying physical constraints, clearly does not have independent processes to develop its thickness vs .  surface area vs . overall size. Our platform, offering analysis streams for all traditional  morphometrics  and  novel  morphometrics, therefore, serves as a starting point for future statistically robust analyses of brain morphology.

## 4.6. Roadmap for future development

We have  three  concrete  developments  planned  for  our normative  modelling  platform.  First,  we  will  incorporate recently proposed multiscale morphometrics (  Chen   et al., 2022;    Leiberg    et  al.,    2023;    Wang    et  al.,    2023)  to  allow users to access the most recent cutting-  edge developments  in  morphological  analysis.  Second,  we  currently use  one  Freesurfer  parcellation  of  the  brain  to  analyse finer regions. We plan to incorporate more atlases, and in the same step, incorporate the possibility to jointly model related  regions  (e.g.  neighbouring  regions)  to  increase robustness of the model. We also note that vertex-  wise data and surface-  based statistics would be a useful addition. Third, with the increasing availability of longitudinal data, we plan to extend our normative model to accept multi-  session longitudinal clinical datasets and   statistically account  for  these  adequately  (see  e.g.    Bu č kov á et  al. (2023) for some suggestions). Further, we will add more

normative data from diverse geographical areas. We will also implement analysis capacity to compare morphology measures more directly on the web platform. Finally, we aim to add more structural metrics such as subcortical volumes, and integrate output from other neuroimaging tools,  such  as  CIVET  and  volBrain  (  Lee    et  al.,    2006; Manj ó n   &amp;   Coup é ,   2016).

## DATA AND CODE AVAILABILITY

Normative data may be available at the discretion of the data holders, please see the website of individual datasets for more information. The subset of the IDEAS mesial TLE dataset is freely available with the associated paper (  P.   N.   T aylor   et al.,   2024).

## AUTHOR CONTRIBUTIONS

Conceptualization:  B.L.,  K.L.,  and  Y.W.  Methodology: B.L., K.L., and Y.W. Investigation: B.L., N.A., A.S., K.L., and Y.W. Visualization: B.L., K.L., P .T., and Y .W. Funding acquisition: G.P .W., J.S.D., D.A.C., P .T., and Y .W. Project administration: B.L. and Y.W. Supervision: Y.W. Writingoriginal draft: B.L. Writing-  review &amp; editing: B.L., N.A., A.S., G.P .W., J.S.D., J.P .T., D.A.C., P .T., K.L., and Y .W.

## FUNDING

B.L., Y.W., and K.L. were supported by the EPSRC (EP/ Y016009/1). Y.W. and P .T. were supported by UKRI Future Leaders  Fellowships  (MR/V026569/1,  MR/T04294X/1). The  Bipolar  Lithium  Imaging  and  Spectroscopy  Study (BLISS)  project  was  funded  by  the  Medical  Research Council  (Clinician  Scientist  Fellowship  BH135495  to DAC). The normative MEG UK data collection was supported  by  an  MRC  UK  MEG  Partnership  Grant,  MR/ K005464/1. GPW and the collection of control data for the UCLH dataset were supported by the MRC (G0802012 and MR/M00841X/1) and the NIHR UCLH/UCL Biomedical Research Centre. J.P .T. and D.C. were supported by the Newcastle NIHR Biomedical Research Centre.

The funders did not have a role in study conception, design,  data  collection,  data  analysis,  interpretation  of the data, preparation of the article, or article submission.

## DECLARATION OF COMPETING INTEREST

There are no competing interests to disclose.

## ACKNOWLEDGEMENTS

We  thank  members  of  the  Computational  Neurology, Neuroscience &amp; Psychiatry Lab (www  .  cnnp  -  lab  .  com) for discussions on the analysis and manuscript. The full list of acknowledgements for the normative data is provided in the Supplementary Materials S6. We thank all participants who generously contributed their time and data.

## SUPPLEMENTARY MATERIALS

Supplementary material for this article is available with the  online  version  here:  https://doi  .  org  /  10  .  1162  /  imag  \_  a \_  00438.

## REFERENCES

- Atty é , A., Renard, F ., Anglade, V., Krainik, A., Kahane, P ., Mansencal, B., Coup é , P ., &amp; Calamante, F . (2024). Datadriven normative values based on generative manifold learning for quantitative MRI. Scientific Reports , 14 (1), 7563. https://doi  .  org  /  10  .  1038  /  s41598  -  024  -  58141  -  4
- Bethlehem, R. A. I., Seidlitz, J., White, S. R., Vogel, J. W., Anderson, K. M., Adamson, C., Adler, S., Alexopoulos, G. S., Anagnostou, E., Areces-  Gonzalez, A., Astle, D. E., Auyeung, B., Ayub, M., Bae, J., Ball, G., Baron-  Cohen, S., Beare, R., Bedford, S. A., Benegal, V., … AlexanderBloch, A. F . (2021). Brain charts for the human lifespan. Nature , 604 , 525-533. https://doi  .  org  /  10  .  1038  /  s41586 -  022  -  04554  -  y
- Bu č kov á , B. R., Fraza, C., Reh á k, R., Koleni č , M., Beckmann, C., Š paniel, F ., Marquand, A., &amp; Hlinka, J. (2023). Using normative models pre-  trained on crosssectional data to evaluate longitudinal changes in neuroimaging data. bioRxiv , 2023-  06. https://doi  .  org  /  10 .  1101  /  2023  .  06  .  09  .  544217
- Chen, Y.-  C., Arnatkevi č i ū t ė , A., McTavish, E., Pang, J. C., Chopra, S., Suo, C., Fornito, A., &amp; Aquino, K. M. (2022). The individuality of shape asymmetries of the human cerebral cortex. eLife , 11 , e75056. https://doi  .  org  /  10 .  7554  /  eLife  .  75056
- Desikan, R. S., S é gonne, F., Fischl, B., Quinn, B. T., Dickerson, B. C., Blacker, D., Buckner, R. L., Dale, A. M., Maguire, R. P ., Hyman, B. T., Albert, M. S., &amp; Killiany, R. J. (2006). An automated labeling system for subdividing the human cerebral cortex on MRI scans into gyral based regions of interest. NeuroImage , 31 , 968-980. https://doi  .  org  /  10  .  1016  /  J  .  NEUROIMAGE  .  2006 .  01  .  021
- Fischl, B. (2012). Freesurfer. NeuroImage , 62 , 774-781. https://doi  .  org  /  10  .  1016  /  J  .  NEUROIMAGE  .  2012  .  01  .  021
- Ge, R., Yu, Y., Qi, Y . X., Fan, Y .-  n., Chen, S., Gao, C., Haas, S. S., New, F ., Boomsma, D. I., Brodaty, H., Brouwer, R. M., Buckner, R., Caseras, X., Crivello, F ., Crone, E. A., Erk, S., Fisher, S. E., Franke, B., Glahn, D. C., … Group, E. L. W. (2024). Normative modelling of brain morphometry across the lifespan with centilebrain: Algorithm benchmarking and model optimisation. The Lancet Digital Health , 6 , e211-e221. https://doi  .  org  /  10 .  1016  /  S2589  -  7500(23)00250  -  9
- Greene, D. J., Koller, J. M., Hampton, J. M., Wesevich, V., Van, A. N., Nguyen, A. L., Hoyt, C. R., McIntyre, L., Earl, E. A., Klein, R. L., Shimony, J. S., Petersen, S. E., Schlaggar, B. L., Fair, D. A., &amp; Dosenbach, N. U. F . (2018). Behavioral interventions for reducing head motion during MRI scans in children. NeuroImage , 171 , 234-245. https://doi  .  org  /  10  .  1016  /  J  .  NEUROIMAGE  .  2018  .  01  .  023
- Hibar, D. P ., Westlye, L. T., Doan, N. T., Jahanshad, N., Cheung, J. W., Ching, C. R. K., Versace, A., Bilderbeck,

A. C., Uhlmann, A., Mwangi, B., Kr ä mer, B., Overs, B., Hartberg, C. B., Ab é , C., Dima, D., Grotegerd, D., Sprooten, E., B ø en, E., Jimenez, E., … Andreassen, O. A. (2018). Cortical abnormalities in bipolar disorder: An MRI analysis of 6503 individuals from the enigma bipolar disorder working group. Molecular Psychiatry , 23 , 932-942. https://doi  .  org  /  10  .  1038  /  mp  .  2017  .  73

Himmelberg, M. M., T ü n ç ok, E., Gomez, J., Grill-  Spector, K., Carrasco, M., &amp; Winawer, J. (2023). Comparing retinotopic maps of children and adults reveals a latestage change in how V1 samples the visual field. Nature Communications , 14 , 1-15. https://doi  .  org  /  10  .  1038 /  s41467  -  023  -  37280  -  8

Johnson, W. E., Li, C., &amp; Rabinovic, A. (2007). Adjusting batch effects in microarray expression data using empirical Bayes methods. Biostatistics , 8 (1), 118-127. https://doi  .  org  /  10  .  1093  /  biostatistics  /  kxj037

LaMontagne, P . J., Benzinger, T. L., Morris, J. C., Keefe, S., Hornbeck, R., Xiong, C., Grant, E., Hassenstab, J., Moulder, K., Vlassenko, A. G., Raichle, M. E., Cruchaga, C., &amp; Marcus, D. (2019). Oasis-  3: Longitudinal neuroimaging, clinical, and cognitive dataset for normal aging and Alzheimer disease. medRxiv , 2019.12.13.19014902. https://doi  .  org  /  10  .  1101  /  2019  .  12 .  13  .  19014902

Lee, J. K., Lee, J.-  M., Kim, J. S., Kim, I. Y ., Evans, A. C., &amp; Kim, S. I. (2006). A novel quantitative cross-  validation of different cortical surface reconstruction algorithms using MRI phantom. Neuroimage , 31 (2), 572-584. https://doi . org  /  10  .  1016  /  j  .  neuroimage  .  2005  .  12  .  044

Leiberg, K., Blattner, T., Little, B., Mello, V. B. B., de Moraes, F. H. P ., Rummel, C., Taylor, P . N., Mota, B., &amp; Wang, Y. (2023). Multiscale cortical morphometry reveals pronounced regional and scale-  dependent variations across the lifespan. arXiv . https://doi  .  org  /  10  .  48550  /  arXiv .  2311  .  13501

Leiberg, K., Papasavvas, C., &amp; Wang, Y. (2021). Local morphological measures confirm that folding within small partitions of the human cortex follows universal scaling law. Lecture Notes in Computer Science (including subseries Lecture Notes in Artificial Intelligence and Lecture Notes in Bioinformatics), 12907 LNCS , 691-700. https://doi  .  org  /  10  .  1007  /  978  3  -  030  -  87234  -  2  \_  65

Little, B., Flowers, C., Blamire, A., Thelwall, P ., Taylor, J.-  P ., Gallagher, P ., Cousins, D. A., &amp; Wang, Y . (2024). Multivariate brain-  cognition associations in euthymic bipolar disorder. Bipolar Disorders , 26 , 604-616. https:// doi  . org  /  10  .  1111  /  bdi  .  13484

Loreto, F ., Verdi, S., Kia, S. M., Duvnjak, A., Hakeem, H., Fitzgerald, A., Patel, N., Lilja, J., Win, Z., Perry, R., Marquand, A. F ., Cole, J. H., &amp; Malhotra, P . (2024). Alzheimer's disease heterogeneity revealed by neuroanatomical normative modeling. Alzheimer's &amp; Dementia: Diagnosis, Assessment &amp; Disease Monitoring , 16 , e12559. https://doi  .  org  /  10  .  1002  /  DAD2  .  12559

Manj ó n, J. V., &amp; Coup é , P . (2016). Volbrain: An online MRI brain volumetry system. Frontiers in Neuroinformatics , 10 , 30. https://doi  .  org  /  10  .  3389  /  fninf  .  2016  .  00030

Mota, B., &amp; Herculano-  Houzel, S. (2015). Cortical folding scales universally with surface area and thickness, not number of neurons. Science , 349 , 74-77. https://doi  .  org /  10  .  1126  /  science  .  aaa9101

Nigg, J. T., Karalunas, S. L., Mooney, M. A., Wilmot, B., Nikolas, M. A., Martel, M. M., Tipsord, J., Nousen, E. K., Schmitt, C., Ryabinin, P ., Musser, E. D., Nagel, B. J., &amp; Fair, D. A. (2023). The Oregon ADHD-  1000: A new longitudinal data resource enriched for clinical cases and multiple levels of analysis. Developmental Cognitive

Neuroscience , 60 , 101222. https://doi  .  org  /  10  .  1016  /  J .  DCN  .  2023  .  101222

Nooner, K. B., Colcombe, S. J., Tobe, R. H., Mennes, M., Benedict, M. M., Moreno, A. L., Panek, L. J., Brown, S., Zavitz, S. T., Li, Q., Sikka, S., Gutman, D., Bangaru, S., Schlachter, R. T., Kamiel, S. M., Anwar, A. R., Hinz, C. M., Kaplan, M. S., Rachlin, A. B., … Milham, M. P . (2012). The NKI-  Rockland sample: A model for accelerating the pace of discovery science in psychiatry. Frontiers in Neuroscience , 6 , 32787. https://doi  .  org  /  10  .  3389  /  FNINS .  2012  .  00152

Nugent, A. C., Thomas, A. G., Mahoney, M., Gibbons, A., Smith, J. T., Charles, A. J., Shaw, J. S., Stout, J. D., Namyst, A. M., Basavaraj, A., Earl, E., Riddle, T., Snow, J., Japee, S., Pavletic, A. J., Sinclair, S., Roopchansingh, V., Bandettini, P . A., &amp; Chung, J. (2022). The NIMH intramural healthy volunteer dataset: A comprehensive MEG, MRI, and behavioral resource. Scientific Data , 9 , 1-10. https://doi  .  org  /  10  .  1038  /  s41597  -  022  -  01623  -  9

Pomponio, R., Erus, G., Habes, M., Doshi, J., Srinivasan, D., Mamourian, E., Bashyam, V., Nasrallah, I. M., Satterthwaite, T. D., Fan, Y., Launer, L. J., Masters, C. L., Maruff, P ., Zhuo, C., V ö lzke, H., Johnson, S. C., Fripp, J., Koutsouleris, N., Wolf, D. H., … Davatzikos, C. (2020). Harmonization of large MRI datasets for the analysis of brain imaging patterns throughout the lifespan. NeuroImage , 208 , 116450. https://doi  .  org  /  10  .  1016  /  j .  neuroimage  .  2019  .  116450

Pustina, D., Avants, B., Sperling, M., Gorniak, R., He, X., Doucet, G., Barnett, P ., Mintzer, S., Sharan, A., &amp; Tracy, J. (2015). Predicting the laterality of temporal lobe epilepsy from PET, MRI, and DTI: A multimodal study. NeuroImage: Clinical , 9 , 20-31. https://doi  .  org  /  10  .  1016  /  j . nicl  .  2015  .  07  .  010

Rutherford, S., Barkema, P ., Tso, I. F ., Sripada, C., Beckmann, C. F., Ruhe, H. G., &amp; Marquand, A. F . (2023). Evidence for embracing normative modeling. eLife , 12 , e85082. https://doi  .  org  /  10  .  7554  /  eLife  .  85082

Schaer, M., Cuadra, M. B., Schmansky, N., Fischl, B., Thiran, J. P ., &amp; Eliez, S. (2012). How to measure cortical folding from MR images: A step-  by-  step tutorial to compute local gyrification index. Journal of Visualized Experiments , 59 , e3417. https://doi  .  org  /  10  .  3791  /  3417

Schillaci, M. A., &amp; Schillaci, M. E. (2022). Estimating the population variance, standard deviation, and coefficient of variation: Sample size and accuracy. Journal of Human Evolution , 171 , 103230. https://doi  .  org  /  10  .  1016  /  j .  jhevol  .  2022  .  103230

Shafto, M. A., Tyler, L. K., Dixon, M., Taylor, J. R., Rowe, J. B., Cusack, R., Calder, A. J., Marslen-  Wilson, W. D., Duncan, J., Dalgleish, T., Henson, R. N., Brayne, C., &amp; Matthews, F. E. (2014). The Cambridge centre for ageing and neuroscience (Cam-  CAN) study protocol: A cross-  sectional, lifespan, multidisciplinary examination of healthy cognitive ageing. BMC Neurology , 14 , 204. https://doi  .  org  /  10  .  1186  /  S12883  -  014  -  0204  -  1

Smith, F . E., Thelwall, P . E., Necus, J., Flowers, C. J., Blamire, A. M., &amp; Cousins, D. A. (2018). 3D  7 Li magnetic resonance imaging of brain lithium distribution in bipolar disorder. Molecular Psychiatry , 23 , 2184-2191. https:// doi  . org  /  10  .  1038  /  s41380  -  018  -  0016  -  6

Taylor, J. R., Williams, N., Cusack, R., Auer, T., Shafto, M. A., Dixon, M., Tyler, L. K., Cam-  CAN, &amp; Henson, R. N. (2017). The Cambridge centre for ageing and neuroscience (Cam-  CAN) data repository: Structural and functional MRI, MEG, and cognitive data from a cross-  sectional adult lifespan sample. NeuroImage , 144 , 262-269. https://doi  .  org  /  10  .  1016  /  J  .  NEUROIMAGE  .  2015  .  09  .  018

- Taylor, P . N., Papasavvas, C. A., Owen, T. W., Schroeder, G. M., Hutchings, F . E., Chowdhury, F . A., Diehl, B., Duncan, J. S., McEvoy, A. W., Miserocchi, A., de Tisi, J., Vos, S. B., Walker, M. C., &amp; Wang, Y. (2022). Normative brain mapping of interictal intracranial EEG to localize epileptogenic tissue. Brain , 145 , 939-949. https://doi  .  org /  10  .  1093  /  BRAIN  /  AWAB380
- Taylor, P . N., Wang, Y ., Simpson, C., Janiukstyte, V., Horsley, J., Leiberg, K., Little, B., Clifford, H., Adler, S., Vos, S., Winston, G., McEvoy, A., Miserocchi, A., de Tisi, J., &amp; Duncan, J. (2024). The imaging database for epilepsy and surgery (IDEAS). Epilepsia , 1-11. https://doi . org  /  10  .  1111  /  epi  .  18192
- Van Essen, D. C., Smith, S. M., Barch, D. M., Behrens, T. E., Yacoub, E., &amp; Ugurbil, K. (2013). The Wu-  Minn human connectome project: An overview. NeuroImage , 80 , 62-79. https://doi  .  org  /  10  .  1016  /  J  .  NEUROIMAGE  .  2013  .  05  .  041
- Wang, Y., Leiberg, K., Kindred, N., Madan, C. R., Poirier, C., Petkov, C. I., Taylor, P . N., &amp; Mota, B. C. (2023). Neuroevolutionary evidence for a universal fractal primate brain shape. eLife , 12 , RP92080. https://doi  .  org  /  10  .  7554  /  eLife .  92080  .  2
- Wang, Y., Leiberg, K., Ludwig, T., Little, B., Necus, J. H., Winston, G., Vos, S. B., de Tisi, J., Duncan, J. S., Taylor, P. N., &amp; Mota, B. (2021). Independent components of human brain morphology. NeuroImage , 226 , 117546. https://doi  .  org  /  10  .  1016  /  J  .  NEUROIMAGE  .  2020  .  117546
- Wang, Y., Necus, J., Kaiser, M., &amp; Mota, B. (2016). Universality in human cortical folding in health and disease. Proceedings of the National Academy of Sciences of the United States of America , 113 , 1282012825. https://doi  .  org  /  10  .  1073  /  PNAS  .  1610175113
- Wang, Y., Necus, J., Rodriguez, L. P ., Taylor, P . N., &amp; Mota, B. (2019). Human cortical folding across regions within individual brains follows universal scaling law. Communications Biology , 2 , 1-8. https://doi  .  org  /  10  .  1038 /  s42003  -  019  -  0421  -  7
- Whelan, C. D., Altmann, A., Bot í a, J. A., Jahanshad, N., Hibar, D. P ., Absil, J., Alhusaini, S., Alvim, M. K. M., Auvinen, P ., Bartolini, E., Bergo, F . P . G., Bernardes, T., Blackmon, K., Braga, B., Caligiuri, M. E., Calvo, A., Carr, S. J., Chen, J., Chen, S., … Sisodiya, S. M. (2018). Structural brain abnormalities in the common epilepsies assessed in a worldwide enigma study. Brain , 141 , 391-408. https://doi  .  org  /  10  .  1093  /  BRAIN /  AWX341
