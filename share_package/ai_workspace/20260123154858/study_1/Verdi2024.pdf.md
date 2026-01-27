## RESEARCH ARTICLE

![Image](./Verdi2024_artifacts/image_000000_ba363a304214c4536054d20d711dc650f144b8f9d5657cc00c941889a1614c26.png)

![Image](./Verdi2024_artifacts/image_000001_aea9f37017a78ffe0b124246d968d8492cf27a04bf783aaf2deb5503a30f7bb7.png)

## Personalizing progressive changes to brain structure in Alzheimer's disease using normative modeling

Serena Verdi 1,2

Saige Rutherford 3,4

Charlotte Fraza 3,4

DuyguTosun 5

Andre Altmann 1

Lars Lau Raket 6

Jonathan M. Schott 2

Andre F. Marquand 3,4

James H. Cole 1,2

for the Alzheimer's Disease Neuroimaging Initiative

1 Centre for Medical Image Computing, University College London, London, UK

2 Dementia Research Centre, UCL Queen Square Institute of Neurology, London, UK

3 Donders Centre for Cognitive Neuroimaging, Donders Institute for Brain, Cognition and Behaviour, Radboud University, Nijmegen, the Netherlands

4 Department of Cognitive Neuroscience, Radboud University Medical Centre, Nijmegen, the Netherlands

5 Department of Radiology and Biomedical Imaging, University of California San Francisco, San Francisco, California, USA

6 Department of Clinical Sciences, Lund University, Malmö, Sweden

## Correspondence

James H. Cole, Centre for Medical Image Computing, Department of Computer Science, University College London, London, UK. Email: james.cole@ucl.ac.uk

Data used in preparation of this article were obtained from the Alzheimer's Disease Neuroimaging Initiative (ADNI) database (adni.loni.usc.edu). As such, the investigators within the ADNI contributed to the design and implementation of ADNI and/or provided data but did not participate in the analysis or writing of this report. A complete listing of ADNIinvestigators can be found at:

http://adni.loni.usc.edu/wpcontent/uploads/how\_to\_apply/ ADNI\_Acknowledgement\_List.pdf

## Funding information

National Institutes of Health, Grant/Award Number: U01 AG024904; DOD ADNI, Grant/Award Number: W81XWH-12-2-0012; National Institute on Aging; National Institute of Biomedical Imaging and Bioengineering; AbbVie; Alzheimer's Association; Alzheimer's Drug Discovery Foundation; Araclon Biotech; BioClinica, Inc.; Biogen; Bristol-Myers Squibb Company; CereSpir, Inc.; Cogstate; Eisai Incorporated; Elan Pharmaceuticals, Inc.; Eli

## Abstract

INTRODUCTION: Neuroanatomical normative modeling captures individual variability in Alzheimer's disease (AD). Here we used normative modeling to track individuals' disease progression in people with mild cognitive impairment (MCI) and patients with AD.

METHODS: Cortical and subcortical normative models were generated using healthy controls ( n ≈ 58k). These models were used to calculate regional z scores in 3233 T1-weighted magnetic resonance imaging time-series scans from 1181 participants. Regions with z scores &lt; -1.96 were classified as outliers mapped on the brain and summarized by total outlier count (tOC).

RESULTS: tOC increased in AD and in people with MCI who converted to AD and also correlated with multiple non-imaging markers. Moreover, a higher annual rate of change in tOC increased the risk of progression from MCI to AD. Brain outlier maps identified the hippocampus as having the highest rate of change.

DISCUSSION: Individual patients' atrophy rates can be tracked by using regional outlier maps and tOC.

## KEYWORDS

Alzheimer's disease, disease progression, longitudinal serial magnetic resonance imaging, mild cognitive impairment, neuroimaging, normative modeling

Jonathan M. Schott, Andre F. Marquand, James H. Cole, and the Alzheimer's Disease Neuroimaging Initiative contributed equally to this work.

This is an open access article under the terms of the Creative Commons Attribution License, which permits use, distribution and reproduction in any medium, provided the original work is properly cited.

©2024TheAuthor(s). Alzheimer's &amp; Dementia published by Wiley Periodicals LLC on behalf of Alzheimer's Association.

Lilly and Company; EuroImmun; F. Hoffmann-La Roche Ltd.; Genentech, Inc.; Fujirebio; GE Healthcare; IXICO Ltd.; Janssen Alzheimer Immunotherapy Research &amp; Development, LLC.; Johnson &amp; Johnson Pharmaceutical Research &amp; Development LL.; Lumosity; Lundbeck; Merck &amp; Co., Inc. Meso Scale Diagnostics, LLC; NeuroRx Research; Neurotrack Technologies; Novartis Pharmaceuticals Corporation; Pfizer Inc.; Piramal Imaging; Servier; Takeda Pharmaceutical Company; Transition Therapeutics; Canadian Institutes of Health Research; Northern California Institute for Research and Education; Foundation for the National Institutes of Health; Alzheimer's Neuro Imaging; University of Southern California; EPSRC-funded UCL Centre for Doctoral Training in Intelligent; Integrated Imaging in Healthcare, Grant/Award Number: EP/S021930/1; Department of Health's National Institute for Health Research; University College London Hospitals Biomedical Research Centre; Dutch Organization for Scientific Research; VIDI, Grant/Award Number: 016.156.415; Alzheimer's Research UK; Brain Research UK; Weston Brain Institute; Medical Research Council; British Heart Foundation; Early Detection of Alzheimer's Disease Subtypes; aegis of JPND; EU Joint

MR/T046422/1; Italian Ministry of Health; National Health &amp; Medical Research Council, Grant/Award Number: 1191535; National Grant/Award Number:

Therapeutic Research Institute; Laboratory for Programme-Neurodegenerative Disease Research; United Kingdom, Medical Research Council, Grant/Award Number: Research, Development and Innovation Office,

2019-2.1.7-ERA-NET-2020-00008; ADNI; Alzheimer's Association; Alzheimer's Drug Discovery Foundation; Department of Health's National Institute for Health Research funded University College London Hospitals Biomedical Research Centre; ZonMW, Grant/Award Number: 733051106; Agence Nationale de la Recherche, Grant/Award Number: ANR-19-JPW2-000; Italian Ministry of Health (MoH)

## 1 BACKGROUND

The pathologies underlying Alzheimer's disease (AD) interact with an individual's distinct genetics, environmental exposures, and comorbidities, leading to idiosyncratic patterns of brain atrophy that change dynamically as the disease progresses. 1-4 Heterogeneity in atrophy is likely to impact individual differences between patients with AD, including timing and focality of initial symptoms and the pattern and progression of symptoms. 3 This heterogeneity creates challenges in the clinic (e.g., when predicting prognosis and planning care), and complicates research recruitment and clinical trial design. 5-10 Therefore, there is a need to quantify disease heterogeneity at the individual level. 11

## Highlights

- NeuroanatomicalnormativemodelingwasappliedtoserialAlzheimer'sdisease(AD) magnetic resonance imaging (MRI) data for the first time.
- Deviation from the norm (outliers) of cortical thickness or brain volume was computed in 3233 scans.
- The number of brain-structure outliers increased over time in people with AD.
- Patterns of change in outliers varied markedly between individual patients with AD.
- People with mild cognitive impairment whose outliers increased over time had a higher risk of progression from AD.

Neuroimaging provides insights into brain structure in vivo, and has long been used to study AD; however, most studies focus on group-average or subtype effects and overlook the individual variability between patients. 12,13 Neuroanatomical normative modeling, an emerging technique that captures individual-level variability in the brain, has been developed to overcome reliance on group averages. 14,15 Based on the well-established normative modeling concept, for example, height and weight growth charts for children, 16 the neuroanatomical version builds separate normative models per brain region, based on a large independent reference dataset. An individual's brain scan can then be compared to this reference database to determine whether their brain volume or cortical thickness is lesser or greater than expected for someone of their age and sex. This deviation

![Image](./Verdi2024_artifacts/image_000002_e14af7d95f7340f7acdfeebacf3ba28ef567f8463c855883b7debf7f7bd001da.png)

![Image](./Verdi2024_artifacts/image_000003_486212221fdd90ac126c1e9c1ed63f3ece9c38d05f9c3bc9056fd43f73b299df.png)

from normality can be quantified using z scores, from which brain-wide z score maps can be generated, providing a unique fingerprint of an individual's brain health. 3 Neuroanatomical normative modeling has the potential to detect specific patterns of brain changes in individual patients with AD, paving the way for personalized health care and precision medicine approaches.

Using neuroimaging data from the Alzheimer's Disease Neuroimaging Initiative (ADNI) we have previously shown the heterogeneous nature of cortical thinning patterns between individuals with AD. 17 Here, the individualized brain-wide z score maps revealed heterogenous atrophy patterns in the AD group; the extent of cortical thinning (i.e., in terms of deviation from the norm) in mild cognitive impairment (MCI) was predictive of conversion to AD and was also related to cognitive function, amyloid beta, phosphorylated tau, and apolipoprotein E ( APOE ) genotype. In a further study, individualized atrophy patterns were shown to relate to disease severity, presenting phenotypes and comorbidities in amyloid-positive AD patients in a 'real-world' memory clinic setting. 18 Insights from these studies have so far been based on cross-sectional neuroimaging data. Understanding how patterns of atrophychangeovertimemayprovideinsightsintocausalmechanisms, and aid in prognostication on an individual patient basis and when monitoring disease progression for clinical trials.

Here, we apply neuroanatomical normative modeling to quantify regional changes in brain structure as AD progresses, using serial magnetic resonance imaging (MRI) data. The neuroanatomical normative modeling can also be optimized by including controls scanned at the same site of the research cohort (adaptive learning) to reduce the impact of scanner effects. 14 We assess whether markers of regional brain atrophy derived from normative modeling (1) can track disease trajectories in people with MCI and patients with AD; (2) can be used to predict progression from MCI to AD; and (3) are related to other commonimaging and non-imaging AD markers.

## 2 METHODS

## 2.1 Participants and research dataset

Participants were derived from two datasets (Figure 1): (1) a reference (training) dataset comprised of healthy people across the human lifespan, and (2) a research dataset that included people with AD or MCI in addition to age-matched cognitively unimpaired controls. The reference dataset was made by combining T1-weighted MRI scan data on healthy people from multiple publicly available sources, 19 including Open Access Series of Imaging Studies (OASIS), Adolescent Brain Cognitive Development (ABCD) study, and UK Biobank (UKB), totaling 58,836 individuals from 82 sites. Data collection, data processing, and participant demographics of the reference dataset were described previously. 14

The research data used in the preparation of this article were obtained from ADNI-http://adni.loni.usc.edu. Inclusion criteria were the availability of T1-weighted MRI scans (acquired using 1.5T and 3T MRI scanners) from ADNI-1, -GO, -2, and -3 ( n = 1849, a total of 4540

## RESEARCHINCONTEXT

1. Systematic review : The authors reviewed the literature using traditional (e.g., PubMed) sources. Alzheimer's disease (AD) atrophy is heterogeneous at the individual level throughout the disease course-neuroanatomical normative modeling is a quantitative technique to capture this. Relevant literature is cited.
2. Interpretation : Neuroanatomical normative modeling can generate personalized brain atrophy markers which can map changes over time in AD. We illustrate that neurodegeneration rates are heterogeneous over the disease course and reveal what cannot be seen using traditional group-average statistics. This is consistent with previous ADandnormative modeling research.
3. Future directions : Further validation of our personalized brain atrophy marker could be conducted in communitybased samples that comprise patients with both earlyand late-stage AD (to capture the full disease course). Future studies should assess if these individualized markers are sensitive to (1) capturing the deceleration of atrophy with disease-modifying therapies and (2) being implementedasadecision-makingtoolinclinical settings.

scans). Participants had either a diagnosis of MCI or AD or were a cognitively unimpaired control, of which their diagnosis record date was matched ± 12weeksafter the scan date (see Table 1 for diagnosis sample sizes). Here participants with AD fulfilled the National Institute of Neurological and Communicative Disorders and Stroke-Alzheimer's Disease and Related Disorders Association criteria for probable AD, 20 and patients were defined as having MCI or as cognitively unimpaired controls as described previously. 21 Patients with MCI who developed AD during longitudinal follow-up were classified as 'MCI progressive' ( n = 98) and the remainder as 'MCI stable.' 'MCI stable' excluded MCI regressors.

Furthermore, additional variables were obtained from the research dataset (ADNI) and linked to each MRI assessment, assuming these additional data were acquired within 12 weeks of the scan. This included cognitive data: memory using ADNI memory (MEM) or executive function using ADNI executive function (EF), 22 and the MiniMental State Examination (MMSE) total score 23 ; amyloid and tau markers: for amyloid, florbetapir, a summary positron emission tomography (PET) standardized uptake value ratio (SUVR), 24 and tau, a PET summary SUVR flortaucipir 25 ; and genetic markers: APOE ε 4 status (determined by either being APOE ε 4 homozygous, APOE ε 4 heterozygous, or APOE ε 4 non-carrier), and previously generated polygenic risk score (PRS). 26

In addition, we included a numerical index designed to reflect the AD stage, the so-called amyloid-cognition score (AC score). AC scores were calculated using latent time disease progression modeling of

![Image](./Verdi2024_artifacts/image_000004_e14af7d95f7340f7acdfeebacf3ba28ef567f8463c855883b7debf7f7bd001da.png)

FIGURE 1 Flow diagram of methods pipeline, study inclusion criteria, and sample sizes. Diagnostic groups include cognitively unimpaired controls, participants with MCI, and patients with dementia. *Study course is between baseline scan (0 months) and 115 months/9.5 years. ADNI, Alzheimer's Disease Neuroimaging Initiative; APOE , apolipoprotein E; MCI, mild cognitive impairment; MMSE, Mini-Mental State Examination; PET, positron emission tomography; SUVR, standardized uptake value ratio.

![Image](./Verdi2024_artifacts/image_000005_fee26b481b0e4b7f1f908dd80d267325e587c252f66a5206c21262db33db7b5d.png)

![Image](./Verdi2024_artifacts/image_000006_2626e123df805008b5631ba73d101a62f3738f2e2379e37ff17b60fe017fbe5a.png)

longitudinal amyloid PET SUVR and cognitive clinical scores, using methods previously detailed. 27,28 AC scores have been proposed as a natural timescale to compare biomarker trajectories and have previously been shown to stage patients along the AD continuum more precisely than conventional measures (e.g., early or late MCI). 27,28

## 2.2 MRI acquisition

For ADNI, T1-weighted images were acquired at 62 study sites using 1.5T or 3T MRI scanner visits across ADNI-1, -GO, -2, -3. Detailed MRI protocols for T1-weighted sequences are available online (http://adni. loni.usc.edu/methods/documents/mri-protocols/). The quality of raw scans was evaluated at the Mayo Clinic for technical problems and significant motion artifacts and clinical abnormalities. 29

## 2.3 Estimation of cortical thickness and subcortical volumes

T1-weighted scans from both the reference and the research dataset were processed using FreeSurfer recon-all cross-sectional, to extract the cortical thickness of 148 cortical regions and gray matter tissue volume of 20 subcortical volumes from the Destrieux parcellations. 30,31 For the reference dataset FreeSurfer version 6 was used; for ADNI, FreeSurfer versions 5 or 6 were used. Quality control of FreeSurfer processing for the reference dataset relied on both manual and automated filtering, as described previously. 14 For the research dataset, quality control was based on a visual review of each cortical region performed at University of California San Francisco (https://adni.bitbucket.io/reference/docs/UCSFFSX51/UCSF% 20FreeSurfer%20Methods%20and%20QC\_OFFICIAL.pdf).

## 2.4 Neuroanatomical normative modeling

A non-Gaussian Bayesian regression model was implemented, which accounts for the non-Gaussian distributions of the cortical thickness andsubcortical volume data and adjusts for unwanted noise from scanning acquisition across multiple sites. 32 This model was trained on 58,836scansfromdatasetsacross82sitestogeneratenormativemodels per region using the covariates age, sex, and site, as previously described. 14,32 Next, these estimates were calibrated to our specific test data (i.e., ADNI), using an adapted transfer learning approach. 19 The distribution parameters of the reference normative model were calibrated to our ADNI dataset using 70% of cognitively unimpaired controls per ADNI site, with the aim of reducing site effects and software version effects (i.e., FreeSurfer v5 or v6). The controls for calibration were randomly selected with stratification to ensure all sites and sexes were present in the adaptation set. The data from the remaining 30% of cognitively unimpaired controls, plus participants with MCI and patients with AD, were then compared to these normative models, generating z scores per region for each scan. A summary of this pipeline and a breakdown of the respective sample sizes included are detailed in Figure 1. The final sample used in z score analysis is n = 1181, which included 3233 scans (Figure 1 and Table 1). The modeling steps and models trained on the reference dataset are openly available: https://github.com/predictive-clinical-neuroscience/ braincharts.

## 2.4.1 Individualized brain markers

Extreme deviations from the norm, or 'outliers,' with lower cortical thickness and subcortical volumes were identified for each region, defined as Z &lt; -1.96. Here ventricular z scores were inverted to reflect volumetric expansion. The number of outliers was summed across all 168 regions to give a total outlier count (tOC) for each participant. Brain surface mapping was conducted using the Destrieux (148 cortical regions) and aseg (20 subcortical regions) atlas via the R package ggseg. All statistical analyses were implemented in R version 3.6.2.

## 2.5 Disease course analysis

## 2.5.1 Outliers at three cross-sectional snapshots over a 24-month period

Fortheseanalyses,longitudinal data were subset into three time points representing a cross-sectional snapshot measure of baseline, month 12 (in a range of ± 12 weeks), and month 24 (in a range of ± 12 weeks). Brain outlier maps for each diagnostic group were mapped across the three time points. This enabled visualization of the extent to which patterns of outlier regions overlap or are distinct in each of these three time points.

## 2.5.2 Rate of change in tOC and regional z scores

To assess longitudinal atrophy, we took two related approaches. First, we calculated the rate of change in tOC as the difference in baseline and final tOC (up to 115 months). A linear model was used to test for differences in the rate of change in tOC between diagnostic groups while adjusting for age, sex, and predicted AD stage (AC score, see Section 2.1). 28,33 Second, we calculated the rate of change in z score per region. Here, we calculated the difference between the baseline and final z score, and then we defined a new 'normative model' based on the distribution of rates of change in scans of cognitively unimpaired controls reserved for analysis (661 scans). 'Rate of z score change outliers' was then defined if a rate of change was more than two standard deviations away from the mean in the ADNI controls (which was Z = -0.0009). Then, the neuroanatomical patterns of the 'rate of z score change outliers' were mapped onto brain surfaces for visualization purposes. As a final step, to provide more detail, we focused on the region of the highest rate of change (in this case, the left hippocampus), and compared patients with AD who had a 'rate of z score change outliers' to those that were not, based on their total MMSE score and their ADstage (AC score), age, sex, and APOE ε 4 status.

![Image](./Verdi2024_artifacts/image_000007_23328ef30dd76ecd46a1ef2bb99e1804823a8a24594cca7d8331d30b959419c6.png)

TABLE 1 Demographics and AD-related characteristics of ADNI participants used in the study.

|                                                                 | Controls                 | MCI                   | AD                    | Total                  | Statistical differences               |
|-----------------------------------------------------------------|--------------------------|-----------------------|-----------------------|------------------------|---------------------------------------|
| n                                                               | 291                      | 682                   | 208                   | 1181                   | -                                     |
| Total of scans*                                                 | 661                      | 1999                  | 573                   | 3233                   | -                                     |
| Median (IQR) of scans per participant                           | 3 (2)                    | 4 (3)                 | 3 (2)                 | 4 (3)                  | -                                     |
| Follow-up time in time series data (months) mean ± SD           | 41.5 ± 28.2              | 32.2 ± 25.5           | 23.8 ± 21.4           | 32.1 ± 25.9            | F (2, 767) = 23.2, p = 1.5 × 10 -10   |
| Sex (M:F)                                                       | 121:170                  | 381:301               | 106:102               | 608:573                | X 2 = 16.68, p = 0.0002               |
| Baseline age mean ± SD&range                                    | 73.9 ± 6.2 (56-93)       | 72.2 ± 7.8 (54-97)    | 74.1 ± 8.1 (55-90)    | 72.9 ± 7.6 (54-97)     | F (2, 1172) = 8.42, p = 0.0002        |
| Baseline AC score (years) † mean ± SD&range                     | -5.17 ± 5.83 (-15.3-9.8) | 7.0 ± 4.0 (-7.0-14.6) | 12.9 ± 2.1 (2.5-16.5) | 4.3 ± 8.3 (-15.3-16.5) | F (2, 724) = 934.97, p = 2.2 × 10 -16 |
| Baseline MMSE score mean ± SD total & range                     | 29.07 ± 1.3 (22-30)      | 27.8 ± 1.9 (19-30)    | 22.6 ± 3.2 (7-30)     | 27.8 ± 1.9 (7-30)      | F (2, 1017) = 561.3, p = 2.2 × 10 -16 |
| Baseline ADNI memory mean ± SD total & range                    | 1.2 ± 0.6 (-0.7-3.3)     | 0.3 ± 0.6 (-1.5-2.4)  | -0.9 ± 0.6 (-2.9-0.4) | 0.3 ± 0.9 (-2.8-3.3)   | F (2, 1074) = 590.5, p = 2.2 × 10 -16 |
| Baseline ADNI executive function mean ± SDtotal & range         | 1.0 ± 0.8 (-1.2-2.9)     | 0.3 ± 0.8 (-2.3-2.9)  | -0.9 ± 1.0 (-3.0-2.6) | 0.3 ± 1.1 (-3.0-2.9)   | F (2, 1070) = 248.9, p = 2.2 × 10 -16 |
| Baseline amyloid PET SUVR mean ± SDtotal & range                | 1.1 ± 0.2 (0.9-2.7)      | 1.2 ± 0.2 (0.8-2.0)   | 1.4 ± 0.2 (0.9-1.8)   | 1.4 ± 0.2 (0.8-2.7)    | F (2, 630) = 54.6, p = 2.2 × 10 -16   |
| Baseline tau PET SUVR mean ± SD total & range                   | 1.5 ± 0.2 (1.2-2.0)      | 1.6 ± 0.3 (1.2-2.6)   | 1.8 ± 0.4 (1.5-2.8)   | 1.6 ± 0.2 (1.2-2.8)    | F (2, 138) = 7.9, p = 0.0005          |
| APOE ε 4 non-carrier (percentage in group sample & sample size) | 32.6% ( n = 194)         | 55.6% ( n = 332)      | 11.7% ( n = 70)       | 53%( n = 596)          | **X 2 = 107.5, p = 2.2 × 10 -16       |

Note : Key: ** X 2 of APOE ε 4 status.

Abbreviations: AD, Alzheimer's disease; ADNI, Alzheimer's Disease Neuroimaging Initiative; APOE , apolipoprotein E; IQR, interquartile range; MCI, mild cognitive impairment; PET, positron emission tomography; SD, standard deviation; SUVR, standardized uptake value ratio.

† Disease stage (AC) score represents predicted years since amyloid PET positivity.

## 2.5.3 Growth models

Growth models were used to understand individual trajectories of tOC over time using the lme4 package in R. Here, separate unconditional linear mixed models were generated for each of the three diagnostic groups; tOC is considered a dependent variable and time as the independent variable. The 'control to MCI' and 'MCI to AD' converters were removed from this model to ensure the diagnostic group data is distinct across each individual timeline (total n = 181 removed; Figure 1). Individual participants were considered variables acting as a random intercept (which allows subjects to vary randomly in terms of their intercept) and therefore models each participant's tOC linearly with time, taking into account how individual participants vary in the slopes and intercepts of this relationship. 34

## 2.5.4 MCI to AD progression analysis

Data were subset for participants who had MCI diagnosis at baseline andalso had follow-up data for 3 years (36 months) since their baseline visit ( n = 365). Using this subset, we ran a survival analysis using Cox proportional hazards regression to assess whether the tOC difference between baseline and 12 months was related to the risk of progression from MCI to AD in 36 months. The tOC change was thresholded according to the median tOC change between baseline and 12 months (median = 3) to signify a low or high rate of change in the first year and a Kaplan-Meier plot was used to illustrate the progression from MCI to AD. We also compared the first-year change in tOC between MCI stable and MCI progressive groups. Furthermore, we added baseline tOC to our Cox proportional hazards regression model (alongside tOC change in the first year) to explore the relationship of baseline tOC to risk of progression.

## 2.6 Relationship to other disease markers

## 2.6.1 AC score

The AC score, representing predicted years since amyloid PET positivity, was used as an approach for staging AD (see Section 2.1). This continuous measure was used as a covariate to adjust for disease stage

![Image](./Verdi2024_artifacts/image_000008_b4fa8398ef5f03b538ab1cc15ac7006ae0b3388fd4a93f7ec23398f9823304e8.png)

when exploring how tOC relates to cognitive, amyloid, and tau AD disease markers.

## 2.6.2 Cognitive markers

Linear regression adjusting for age, sex, and years of education examined the relationship between tOC rate of change and cognitive composite scores (memory using ADNI MEM or executive function using ADNI EF). 34 Wethenassessed the interaction between the diagnostic group and cognitive composite score. Total MMSE scores were only used when comparing regional rates of change (Figure 1).

## 2.6.3 Amyloid and tau markers

Florbetapir and flortaucipir SUVR were used to index cerebral amyloid and tau deposition, respectively. A linear regression adjusting for age and sex examined the relationship between the rate of change in tOC and amyloid and tau PET markers. We assessed the interaction between the diagnostic group and amyloid and tau markers in a subsequent regression.

## 2.6.4 Genetic markers

APOE ε 4 status was determined by either being APOE ε 4 homozygous, APOE ε 4 heterozygous, or APOE ε 4 non-carrier. Group differences in the rate of change in tOC were assessed as a linear regression, adjusting for age and sex. The relationship between PRS and the rate of change in tOC was examined using linear regression, adjusting for age, sex, and APOE ε 4 status. We assessed the interaction between the diagnostic group and PRS in a subsequent regression.

## 3 RESULTS

## 3.1 Participants

The final research dataset amounted to a total of 1181 participants with a total of 3233 scans at least 12 weeks apart between scanning visits and had a maximum interval between baseline and final visit of 9.5 years (115 months; Figure 1 and Table 1).

## 3.2 The change in neuroanatomical outliers between baseline and 24 months

Patterns of cortical thickness and subcortical volume outliers differed between AD, MCI, and control groups and varied over time (Figure 2). The proportion of outliers in each group was mapped cross-sectionally at baseline, 12 months, and 24 months. In patients with AD, the region with the highest proportion of the group having outliers was consis- tently the left hippocampus, with 47% at baseline, 60% at 12 months, and 72% at 24 months. Generally, the number of regions that contained at least one participant with an outlier remained stable; at baseline, there were 134 cortical regions and 13 subcortical regions with outliers, 128 and 12 at 12 months and 131 and 11 at 24 months, respectively.

## 3.3 Higher rate of atrophy in patients with AD

Overall, patients with AD showed a greater number of 'rate of z score change' outliers (see Section 2.5.2), compared to people with MCI or controls. The region with the highest proportion of rate of z score change outliers was the left hippocampus at 53%; therefore, 47% of patients with AD patients do not follow this trend (Figure 3). Comparing patients with AD that did versus did not have rate of z score change outliers in the left hippocampus, there were no statistical differences in age, total MMSE score, and AC score at baseline ( p &gt; 0.05), and no statistical differences in APOE ε 4 carrier status or sex ( X 2 P &gt; 0.05). In the 47% of patients who did not show rate of z score change outliers in the left hippocampus, the regions with the highest proportion of patients with outliers were the left amygdala (29%), the right amygdala (28%), and left and right lateral ventricles (both 11%).

## 3.4 tOC increases with time in AD and in individuals with MCI who progress to AD

Increased tOC over time (i.e., accumulation of outliers) was observed in the AD group ( β = 0.38, p = 9.8 × 10 -13 ) and in the MCI group ( β = 0.001, p = 0.004) but not in controls ( β = -0.001, p = 0.81; Figure 4A,B). Linear regression revealed that there was a significant increase in the rate of tOC change over time (Figure 4C). This differed between groups when adjusting for baseline AC score, baseline age, and sex ( F (5, 490) = 12.99, p = 6.9 × 10 -12 ). Interestingly, when including a quadratic term for time (i.e., time 2 ) to model the non-linear effects of time, this was significant ( β = -31.0, p = 0.0005). When assessing the interaction between group and time 2 we saw that this effect was stronger in AD patients ( β = -35.5, p = 0.06) than in people with MCI ( β = -84.07 p = 0.0005). Pairwise group comparisons (Tukey post hoc tests) of the different group trajectories over time were significant ( p ≤ 0.001) for AD versus controls and AD versus MCI, but not controls versus MCI ( p = 0.518). The rate of change in tOC was highest in the AD group (mean = 5.26, standard deviation [SD] = 9.85), intermediate in the MCI group (mean = 0.59, SD = 4.57), and lowest in controls (mean = -0.01, SD = 1.90). AC score was significantly associated with the rate of change in tOC across the whole sample when adjusting for age and sex ( β = 0.24, p = 7.5 × 10 -09 ).

Growth models showed that an increase in tOC over time was observed in both the MCI progressive group ( β = 0.10, p = 7.4 × 10 -05 , which equates to 1 additional tOC every 10 months) and the MCI stable group ( β = 0.017, p = 0.004; Figure 5A,B). Linear regression showed

![Image](./Verdi2024_artifacts/image_000009_ac1f1ad507d1f04bb8cb947f7023e8947ad09de0f88a561b8bcb125de1736d0c.png)

![Image](./Verdi2024_artifacts/image_000010_1b259f6e94de49f7ae9ffb5e1b559ea1904921b9b2667fcac64cc6857106d837.png)

![Image](./Verdi2024_artifacts/image_000011_cf121180dbe6884d66faa58c1f1f74cb1ecc348436008c35ed81817c628edd3e.png)

32233)

O22

@

FIGURE 2 Mappedarethe percentage of outliers present in (A) cognitively unimpaired controls, (B) mild cognitive impairment, and (C) Alzheimer's disease at baseline, 12 months, and 24 months, for cortical (left) and subcortical (right) areas. The color bar reflects the outlier proportion from 2.5% to 100% (thresholding of z scores). Zero percent (gray) represents that no participants have outliers in those respective regions.

![Image](./Verdi2024_artifacts/image_000012_3c46042c22ccc58bd267987474694221c0ab71c37a85aea2cb4cb7c5ed205653.png)

FIGURE 3 Mappedis the proportion of participants with a high rate of atrophy in cortical (left) and subcortical (right) areas. AD, Alzheimer's disease; MCI, mild cognitive impairment.

![Image](./Verdi2024_artifacts/image_000013_684058e38d1571a72523097dac9c512447c9944423c2e8d1ce454bf9433750c6.png)

![Image](./Verdi2024_artifacts/image_000014_9c240bc6eb5bf6a1baa5840c61c33ec64113f62450fa041c1e8814048249406b.png)

that the MCI progressive group had a significantly higher rate of tOC change over time (mean = 3.97, SD = 11.27) compared to the MCI stable group (mean = 0.53, SD = 4.61), when adjusting for age, sex, and AC score ( F (4, 214) = 2.622, p = 0.03; Figure 5C). There was a significant difference in the rate of change in the first 12 months between the MCI stable and progressive groups ( β = 4.47, p = 3.1 × 10 -15 ). Survival analysis indicated that for every three outliers increase in tOC in the first 12 months, the risk of progression from MCI to AD between 12 months and 36 months (i.e., in the following 2 years) increased by 30.2% (hazard ratio [HR] = 1.09, 95% confidence interval [CI]: [1.06, 1.12], p = 1.4 × 10 -14 ; Figure 5D). When adding baseline tOC as another predictor to our Cox proportional hazards regression model (alongside tOC change in the first year), survival analysis indicated that for every three outliers increase in baseline tOC, the risk of progression from MCI to AD between 12 months and 36 months increased by 9.7% (HR = 1.03, 95% CI: [1.01, 1.05], p = 0.003).

## 3.5 Rate of change in tOC correlates with cognitive, amyloid, and tau markers

The rate of change in tOC across the whole sample was significantly associated with poorer memory performance ( β = -1.99, p = 2.0 × 10 -16 ), and executive function ( β = -1.77, p = 2.0 × 10 -16 ) in separate linear regression models, controlling for age and sex (Figure 6A,B). Here interactions between the diagnostic group and memory ( F (2, 730 = 4.95, p = 0.007) and diagnostic group and executive function ( F (2, 722) = 4.44, p = 0.012) were both significant and were driven by patients with AD (memory [ β = -2.52, p = 0.011], executive function β = -1.73 p = 0.013]). The rate of change in tOC across the whole sample was significantly associated with an increase in amyloid PET summary SUVR ( β = 0.006, p = 0.001) and an increase in tau PET summary SUVR ( β = 0.03, p = 0.0001) when adjusting for age and sex (Figure 6C,6D). Interactions between group and SUVR were not significant ( p &gt; 0.05).

## 3.6 Rate of change in tOC is associated with APOE ε 4 status

APOE ε 4 status showed differences in the rate of change in tOC, in an analysis of variance adjusting for age and sex ( F (2, 755) = 10.06, p = 4.8 × 10 -05 ), which was not influenced by diagnostic group x APOE ε 4 status interaction ( p &gt; 0.05). This association was driven by higher accumulation of outliers in APOE ε 4 homozygotes ( β = 2.33, p = 0.003) and APOE ε 4 heterozygotes ( β = 5.77, p = 0.018) compared to APOE ε 4 negative participants ( β = -0.65, p = 0.185; Figure 7). Linear regression showed no significant association between PRS and rate of change of tOC when adjusting for age, sex, and APOE ε 4 status (F (1, 707) = 1.41, p = 0.23), which was not influenced by diagnostic group x PRS interaction ( p &gt; 0.05).

## 4 DISCUSSION

In this study we used neuroanatomical normative modeling to capture individual patient trajectories of brain structure changes during MCIandafter AD diagnosis. Our normative modeling approach generates patient-specific regional outlier maps, and summary outlier scores (tOC), which we analyzed using serial MRI data acquired up to 9.5 years (mean follow-up time 2.4 years) as part of ADNI. Our findings illustrate that AD affects patients in a non-uniform way as the disease progresses.

A key advantage of neuroanatomical normative modeling is that it provides region-level information, with separate normative models per brain region. We observed that patterns of outliers vary over time in

FIGURE 4 Change in tOC according to diagnostic group. A, Spaghetti plot of tOC according to diagnostic group. Each colored line represents an individual participant's trajectory of tOC scores over the scanning period. B, Linear growth model for each diagnostic group. C, The density spread of the rate of change in tOC for each diagnostic group. AD, Alzheimer's disease; MCI, mild cognitive impairment; tOC, total outlier count.

![Image](./Verdi2024_artifacts/image_000015_f4dcf53e5b049431c9b190cf8a8c4c91f105e8ca22103c133ff9446d8991a466.png)

theADgroup;forinstance,thepercentageofpatientswithADthathad outliers in the hippocampus increased from 47% to 72% in 24 months, suggesting the presence of atrophy in the hippocampus is more heterogenous in earlier stages of the disease and becomes more common as the disease progresses (Figure 2).

This is consistent with our finding that the rate of z score change was highest in the left hippocampus, with 53% of the AD group having outliers (i.e., greater than expected changes in z score) in this area (Figure 3). Hippocampal atrophy is seen as characteristic of AD and is included in AD diagnostic criteria, as well as being used in clinical trials. 35 However,ourresultsshowthatintheADNIADsample,47%of patients do not have greater-than-expected left hippocampal volume changes. These results highlight the individual differences between patients with AD and emphasize the limitation of group-average statistical designs, which would overlook this within-group variability. Moreover, we found that age, sex, total MMSE score and AD stage (AC score), and APOE ε 4 status were not associated with either having or not having a marked rate of z score change outliers (i.e., elevated atrophy), which may provide further evidence for a hippocampal-sparing subtype. 36-39

Our results are consistent with previous work on neuroanatomical variation in dementia. When using a related neuroanatomical normative modeling technique (hierarchical Bayesian regression 40 ), we previously found that patients with AD had a higher tOC and large interindividual differences in regional outliers at baseline, compared to people with MCI and cognitively unimpaired controls. 17,18 Here, our longitudinal data showed an increase in tOC in patients with AD. Interestingly, the rate of change in tOC in the AD group increased in a non-linear way, suggesting an accelerating accumulation of brain structural outliers over the study period. Therefore, tOC could offer some utility in tracking neurodegeneration across the disease course in individuals with AD (Figure 4).

Alongside capturing accumulating atrophy in AD, people with MCI who later progress to AD have increasing rates of tOC (Figure 5A-C).

FIGURE 5 Change in tOC according to disease conversion status. A, Spaghetti plot of tOC in either MCI stable or MCI progressive. Each colored line represents an individual change in tOC over the scanning period. B, Linear growth model for MCI stable or MCI progressive. C, The density spread of the rate of change in tOC in people with MCI. D, Kaplan-Meier plot of MCI progression to AD between 12 and 36 months: the two lines represent a median split of tOC, with &lt; 3 classed as low tOC (blue), and ≥ 3 classed as high tOC (red). Crosses indicate censoring points (i.e., time from baseline at last diagnosis assessment). Filled colors represent the 95% confidence intervals. AD, Alzheimer's disease; MCI, mild cognitive impairment; tOC, total outlier count.

![Image](./Verdi2024_artifacts/image_000016_64a1e474de4ce83a5f954b337d24a4b9e0e4b338c6d3b034eb2f306158eef794.png)

![Image](./Verdi2024_artifacts/image_000017_b1004075b5923e5ae5056ff4afb09f02db7a43c8e4c25b757c85a7b8bc7a6239.png)

This is consistent with our previous research that showed an increase of 10 outliers (i.e., + 10 tOC) confers a 31.4% chance of clinical progression within 3 years. 17 Here, we also found that the rate of change in tOC over 1 year (i.e., the difference in tOC between baseline and 12 months), was associated with progression to dementia in the subsequent 2 years, with an increase of three outliers in this first year giving 30.2% increased risk of clinical progression between 12 months and 36 months (Figure 5D). This raises the possibility that normative modeling approaches to serial scans in patients with MCI could have utility in detecting those that will progress to AD dementia and that annual scans for people with MCI to monitor brain health could benefit the clinical decision-making process (e.g., for early AD detection). Interestingly, some individuals showed negative rates of tOC, thus having fewer outliers over time. This includes 18% of cognitively unimpaired people, 12.7% of people with MCI, and 10.8% of AD patients, though for the vast majority of these participants the decrease was &lt; 5 regions, thus could be caused by noise in the MRI scans leading to minor changes to the FreeSurfer output.

We observed that the rate of change in tOC was associated with amyloid and tau PET SUVR (Figure 6C,6D), in line with previous associations of amyloid and tau with neuroanatomical changes in AD, 41-44 although it is important to note that the amyloid/tau/neurodegeneration (ATN) interplay is likely to differ from individual to individual. 45,46 We also assessed how genetic factors could relate to tOC change over time. We found that an increased tOC is associated with APOE ε 4 homozygosity and heterozygosity (Figure 7), consistent with other structural imaging markers findings, andislikely to reflect amyloid load. 26,47,48 Likewise, our results indicate that memory and executive function are associated with the accelerated accumulation of outliers (Figure 6A,B). 49,50 This highlights that participants with outliers are more likely to have (1) a clinical diagnosis of AD, (2) to be AD biomarker positive, and (3) to have cognitive features consistent with AD, reflecting alignment with current clinical frameworks. 51,52

Indeed, accelerated brain atrophy is a widely accepted marker of AD, 4,53 thus rate of change in brain structure outliers (i.e., tOC) may offer better clinical utility than using raw brain volumes/thicknesses.

FIGURE 6 The relationship between cognitive function and cerebrospinal fluid markers with the rate of change in tOC. Fitted lines are from a linear regression model per diagnostic group for (A) memory function, (B) executive function, (C) summary SUVR amyloid PET, (D) summary SUVR tau PET. AD, Alzheimer's disease; MCI, mild cognitive impairment; PET, positron emission tomography; SUVR, standardized uptake value ratio; tOC, total outlier count.

![Image](./Verdi2024_artifacts/image_000018_a29e9af85ef2fe2caa95babbeb1c0fc546af7d55ec374002cff83b1e67443372.png)

FIGURE 7 The density spread of the rate of change in tOC according to APOE ε 4 status. APOE , apolipoprotein E; tOC, total outlier count.

![Image](./Verdi2024_artifacts/image_000019_6736b38fead852d693337b6dac43d83d1b199ea65ee7f3c23e17adc53407640d.png)

Therefore, in a clinical setting, a patient's tOC could be derived from serial MRI scans to track brain changes over time. Similar brain structure measures derived from normative modeling have already been considered for clinical translation; the Quantitative Neuroradiology Initiative (QNI) provides a framework to contextualize a dementia patient's brain health and provide a personalized score to support clinical decision making. 54,55 Building on this idea, our application of neuroanatomical normative modeling offers regional information on brain health (mapped outlier scores), and improved neuroanatomical normative model estimates by using a large reference cohort. 56

One setback with translating computational statistical designs in clinical settings is the technical barriers to application and limits to data sharing. However, our neuroanatomical normative modeling approach does not require access to raw scans, as the end user only requires a pre-trained reference model, which contains no identifiable data. Scripts to generate individual z scores, tOC, and outlier maps are openly available. 57

Neuroanatomical normative modeling also has the potential to aid in trials of AD therapeutics. For example, it could be used to stratify people for trial enrolment based on the extent (tOC) and spatial distribution of their brain atrophy, to identify subgroups based on different atrophy patterns or as a personalized outcome measure, for which the impact of the treatment using unique brain 'fingerprints' can be quantified, increasing power and sensitivity to subtle changes over time. 9 Moreover, with further validation, it could warrant a run-in period of 12 months in clinical trials, during which an enrolled participant is stratified by their progression risk, based on the accumulation of neuroanatomical outliers over the previous year.

Yet, prior to clinical and drug trial implementation, additional diversification of datasets is needed. Although our reference dataset is large, it is over-representative of European ancestry due to the datasets predominantly from research studies (which do not match either regional or global population demographics). 58,59 Though ADNI participants are mostly of European ancestry, 60 caution should be made when transferring the model to diverse datasets, or participants from underrepresented demographics. 61 Moreover, ADNI participants are more likely to be in the early to intermediate stages of AD, and less likely to have comorbidities. 21,61-63 Future work will require the reference dataset and research/patient datasets to include participants from non-research studies (e.g., participants from memory clinics, 18 which are more likely to include later-stage patients with more

![Image](./Verdi2024_artifacts/image_000020_2626e123df805008b5631ba73d101a62f3738f2e2379e37ff17b60fe017fbe5a.png)

comorbidities), and different social-economic backgrounds and ethnicities to reduce bias and mitigate health-care inequalities. 64

Further optimization of neuroanatomical normative modeling is possible. Although scanner effects and the non-Gaussian distribution of the neuroimaging phenotypes were accounted for, some withinsubject noise remains in longitudinal data, which may be contributing to the range in tOC change (Figure 5C and Figure 6C). To assess this, it will be useful to understand test-retest reliability by calculating the difference in scans that have been acquired in close succession ( &lt; 1 week). 65 Also, the within-subject variability may have been better modeled by implementing the longitudinal FreeSurfer processing pipeline. 66 ADNI data were processed with a variety of FreeSurfer versions (5 &amp; 6). While impractical to unify the processing retrospectively, these inconsistencies may add noise to the normative models from potential differences in cortical thickness and subcortical volume estimates, 67,68 although there is evidence for consistencies of these estimates between some FreeSurfer versions. 69 Furthermore, our model treats brain regions independently, yet it is likely that regional z scores are intercorrelated, particularly between neighboring or bilateral regions. Solutions to this could consider the spatial extent of affected voxels and the magnitude in those voxels, 70 and apply normative models that use brain connectivity data, which have shown recent promise. 58

To conclude, we show that brain structural outliers across MCI and AD differ at the individual level and that this can be visualized over time by using outlier maps and quantified by the tOC generated by neuroanatomical normative modeling. Our study further supports the potential utility of tOC and brain outlier maps as personalized markers for patients with AD and to assess the risk of disease progression in people with MCI. The next steps are to diversify the training and research/patient data used, and further validate these markers for future translation into clinical settings and in clinical trial design.

## ACKNOWLEDGMENTS

Data collection and sharing for this project was funded by ADNI (National Institutes of Health Grant U01 AG024904) and DOD ADNI (Department of Defense award number W81XWH-12-2-0012). ADNI is funded by the National Institute on Aging, the National Institute of Biomedical Imaging and Bioengineering, and through generous contributions from the following: AbbVie; Alzheimer's Association; Alzheimer's Drug Discovery Foundation; Araclon Biotech; BioClinica, Inc.; Biogen; Bristol-Myers Squibb Company; CereSpir, Inc.; Cogstate; Eisai Inc.; Elan Pharmaceuticals, Inc.; Eli Lilly and Company; EuroImmun; F. Hoffmann-La Roche Ltd and its affiliated company Genentech, Inc.; Fujirebio; GE Healthcare; IXICO Ltd.; Janssen Alzheimer Immunotherapy Research &amp; Development, LLC.; Johnson &amp; Johnson Pharmaceutical Research &amp; Development LL.; Lumosity; Lundbeck; Merck &amp; Co., Inc. Meso Scale Diagnostics, LLC; NeuroRx Research; Neurotrack Technologies; Novartis Pharmaceuticals Corporation; Pfizer Inc.; Piramal Imaging; Servier; Takeda Pharmaceutical Company; and Transition Therapeutics. The Canadian Institutes of Health Research is providing funds to support ADNI clinical sites in

Canada. Private sector contributions are facilitated by the Foundation for the National Institutes of Health (www.fnih.org). The grantee organization is the Northern California Institute for Research and Education, and the study is coordinated by the Alzheimer's Therapeutic Research Institute at the University of Southern California. ADNI data are disseminated by the Laboratory for Neuro Imaging at the University of Southern California. This work was supported by the EPSRC-funded UCL Centre for Doctoral Training in Intelligent, Integrated Imaging in Healthcare (i4health) (EP/S021930/1), and the Department of Health's National Institute for Health Research funded University College London Hospitals Biomedical Research Centre. In addition, A.F.M. gratefully acknowledges funding from the Dutch Organization for Scientific Research via a VIDI fellowship (grant number 016.156.415); J.M.S. acknowledges the support of Alzheimer's Research UK, Brain Research UK, Weston Brain Institute, Medical Research Council, and the British Heart Foundation. A.A. was supported by the Early Detection of Alzheimer's Disease Subtypes (E-DADS) project, an EU Joint Programme-Neurodegenerative Disease Research (JPND) project (see www.jpnd.eu). The project is supported under the aegis of JPND through the following funding organizations: the United Kingdom, Medical Research Council (MR/T046422/1);Netherlands,ZonMW(733051106);France,Agence Nationale de la Recherche (ANR-19-JPW2-000); Italy, Italian Ministry of Health (MoH); Australia, National Health &amp; Medical Research Council (1191535); Hungary, National Research, Development and Innovation Office (2019-2.1.7-ERA-NET-2020-00008).

## CONFLICT OF INTEREST STATEMENT

L.L.R. is an employee of Eli Lilly and Company. All other authors report no disclosures relevant to the manuscript. Author disclosures are available in the supporting information.

## CONSENT STATEMENT

All data were approved by each cohort's respective institutional review boardandinformedwrittenconsentwasobtainedfromallparticipants.

## ORCID

JamesH.Cole https://orcid.org/0000-0003-1908-5588

![Image](./Verdi2024_artifacts/image_000021_dfc64bc22564edac17b84ad2f0ce20418f96d1f287d00fa0c384f8b4d5cebc68.png)

## REFERENCES

1. Aisen PS, Cummings J, Jack CR, et al. On the path to 2025: understanding the Alzheimer's disease continuum. Alzheimers Res Ther . 2017;9:1-10. doi:10.1186/s13195-017-0283-5
2. Eid A, Mhatre I, Richardson JR. Gene-environment interactions in Alzheimer's disease: a potential path to precision medicine. Pharmacol Ther . 2019;199:173-187. doi:10.1016/J.PHARMTHERA.2019.03.005
3. VerdiS,MarquandAF,SchottJM,ColeJH.Beyondtheaveragepatient: how neuroimaging models can address heterogeneity in dementia. Brain . 2021;144(10):2946-2953. doi:10.1093/BRAIN/AWAB165
4. Whitwell JL. Progression of atrophy in Alzheimer's disease and related disorders. Neurotox Res . 2010;18(3-4):339-346. doi:10.1007/S12640010-9175-1
5. Gauthier S, Albert M, Fox N, et al. Why has therapy development for dementia failed in the last two decades? Alzheimers Dement . 2016;12(1):60-64. doi:10.1016/j.jalz.2015.12.003

6. Cummings J, Feldman HH, Scheltens P. The 'rights' of precision drug development for Alzheimer's disease. Alzheimers Res Ther . 2019;11(1):76. doi:10.1186/s13195-019-0529-5
7. Aisen PS, Cummings J, Jack CR, et al. On the path to 2025: understanding the Alzheimer's disease continuum. Alzheimers Res Ther . 2017;9(1):60. doi:10.1186/s13195-017-0283-5
8. Ryan J, Fransquet P, Wrigglesworth J, Lacaze P. Phenotypic heterogeneity in dementia: a challenge for epidemiology and biomarker studies. Front Public Health . 2018;6:181. doi:10.3389/fpubh.2018. 00181
9. Duara R, Barker W. Heterogeneity in Alzheimer's disease diagnosis and progression rates: implications for therapeutic trials. Neurotherapeutics . 2022;19(1):8-25. doi:10.1007/S13311-022-01185-Z
10. Devi G, Scheltens P. Heterogeneity of Alzheimer's disease: consequence for drug trials? Alzheimers Res Ther . 2018;10(1):122. doi:10. 1186/S13195-018-0455-Y/METRICS
11. Marquand AF, Wolfers T, Mennes M, Buitelaar J, Beckmann CF. Beyond lumping and splitting: a review of computational approaches for stratifying psychiatric disorders. Biol Psychiatry Cogn Neurosci Neuroimaging . 2016;1(5):433-447. doi:10.1016/j.bpsc.2016.04.002
12. Ten Kate M, Dicks E, Visser PJ, et al. Atrophy subtypes in prodromal Alzheimer's disease are associated with cognitive decline. Brain . 2018;141(12):3443-3456. doi:10.1093/brain/awy264
13. National Institute for Health and Care Excellence (NICE). Dementia: Assessment, management and support for people living with dementia and their carers . National Institute for Health and Care Excellence (NICE);2022. https://www.ncbi.nlm.nih.gov/books/NBK513207/
14. Rutherford S, Fraza C, Dinga R, et al. Charting brain growth and aging at high spatial precision. eLife . 2022;11:e72904. doi:10.7554/ELIFE. 72904
15. Marquand AF, Kia SM, Zabihi M, Wolfers T, Buitelaar JK, Beckmann CF. Conceptualizing mental disorders as deviations from normative functioning. Mol Psychiatry . 2019;24(10):1415-1424. doi:10.1038/ s41380-019-0441-1
16. ColeTJ.Thedevelopmentofgrowthreferencesandgrowthcharts. Ann HumBiol . 2012;39(5):382-394. doi:10.3109/03014460.2012.694475
17. Verdi S, Kia SM, Yong KXX, et al. Revealing individual neuroanatomical heterogeneity in Alzheimer disease using neuroanatomical normative modeling. Neurology . 2023;100:e2442-e2453. doi:10.1212/WNL. 0000000000207298
18. Loreto F, Verdi S, Kia SM, et al. Alzheimer's disease heterogeneity revealed by neuroanatomical normative modeling. Alzheimers Dement (Amst) . 2024;16(1):e12559. doi:10.1002/dad2.12559
19. Kia S, Huijsdens H, Rutherford S, et al. Federated multi-site normative modeling using hierarchical Bayesian regression. PLoS One . 2022;17:e0278776. doi:10.1101/2021.05.28.446120
20. McKhann G, Drachman D, Folstein M, Katzman R, Price D, Stadlan EM. Clinical diagnosis of Alzheimer's disease: report of the NINCDSADRDA Work Group under the auspices of department of Health and Human Services Task Force on Alzheimer's Disease. Neurology . 1984;34(7):939-944. doi:10.1212/wnl.34.7.939
21. Petersen RC, Aisen PS, Beckett LA, et al. Alzheimer's disease Neuroimaging Initiative (ADNI) Clinical characterization. Neurology . 2010;74:201-209.
22. GibbonsLE,CarleAC,MackinRS,etal.Acompositescoreforexecutive functioning, validated in Alzheimer's Disease Neuroimaging Initiative (ADNI) participants with baseline mild cognitive impairment. Brain Imaging Behav . 2012;6:517-527.
23. Cockrell JR, Folstein MF. Mini-mental state examination (MMSE). Psychopharmacol Bull . 1988;24(4):689-692.
24. Landau S, Ward TJ, Murphy A, Jagust W. Flortaucipir (AV-1451) processing methods. 2016. https://adni.bitbucket.io/reference/docs/ UCBERKELEYAV1451/UCBERKELEY\_AV1451\_Methods\_2021-0114.pdf
25. Landau SM, Breault C, Joshi AD, et al. Amyloidβ imaging with Pittsburgh compound B and florbetapir: comparing radiotracers and quantification methods. J Nucl Med . 2013;54(1):70-77. doi:10.2967/ JNUMED.112.109009
26. Altmann A, Scelsi MA, Shoai M, et al. A comprehensive analysis of methods for assessing polygenic burden on Alzheimer's disease pathology and risk beyond APOE. Brain Commun . 2020;2(1):fcz047. doi:10.1093/BRAINCOMMS/FCZ047
27. Raket LL. Statistical disease progression modeling in Alzheimer disease. Front Big Data . 2020;3:24. doi:10.3389/FDATA.2020.00024
28. Raket LL, Palmqvist S, Mattsson-Carlgren N, Hansson O. An amyloidcognition composite score for estimating time-consistent biomarker trajectories across the Alzheimer's continuum. Alzheimers Dement . 2022;18(S6):e061629. doi:10.1002/ALZ.061629
29. Jack CR, Bernstein MA, Borowski BJ, et al. Update on the magnetic resonance imaging core of the Alzheimer's disease neuroimaging initiative. Alzheimers Dement . 2010;6(3):212-220. doi:10.1016/J.JALZ. 2010.03.004
30. FreeSurferVersion3. Free Surfer Wiki. 2021. Accessed May 27, 2023. https://surfer.nmr.mgh.harvard.edu/fswiki/ FreeSurferVersion3#AsegAtlas
31. Fischl B. FreeSurfer. Neuroimage . 2012;62:774-781. doi:10.1016/j. neuroimage.2012.01.021
32. Fraza CJ, Dinga R, Beckmann CF, Marquand AF. Warped Bayesian linear regression for normative modelling of big data. Neuroimage . 2021;245:118715. doi:10.1016/J.NEUROIMAGE.2021.118715
33. Kühnel L, Berger AK, Markussen B, Raket LL. Simultaneous modeling of Alzheimer's disease progression via multiple cognitive scales. Stat Med . 2021;40(14):3251-3266. doi:10.1002/SIM.8932
34. Bates D, Mächler M, Bolker B, Walker S. Fitting linear mixed-effects modelsusinglme4. J Stat Softw . 2015;67(1):51. doi:10.18637/jss.v067. i01
35. McKhann GM, Knopman DS, Chertkow H, et al. The diagnosis of dementia due to Alzheimer's disease: recommendations from the National Institute on Aging-Alzheimer's Association workgroups on diagnostic guidelines for Alzheimer's disease. Alzheimers Dement . 2011;7(3):263-269. doi:10.1016/J.JALZ.2011.03.005
36. Ferreira D, Mohanty R, Murray ME, Nordberg A, Kantarci K, Westman E. Does a truly hippocampal sparing subtype of Alzheimer's disease really exist? Acta Neuropathol Commun . 2022;10:166. doi:10.1002/ ALZ.056368
37. Ferreira D, Verhagen C, Hernández-Cabrera JA, et al. Distinct subtypes of Alzheimer's disease based on patterns of brain atrophy: longitudinal trajectories and clinical applications. Sci Rep . 2017;7:46263. doi:10.1038/srep46263
38. Poulakis K, Pereira JB, Mecocci P, et al. Heterogeneous patterns of brain atrophy in Alzheimer's disease. Neurobiol Aging . 2018;65:98-108. doi:10.1016/j.neurobiolaging.2018.01.009
39. Ferreira D, Nordberg A, Westman E. Biological subtypes of Alzheimer disease. Neurology . 2020;94(10):436-448. doi:10.1212/WNL. 0000000000009058
40. Kia SM, Huijsdens H, Rutherford S, et al. Closing the life-cycle of normative modeling using federated hierarchical Bayesian regression. PLoS One . 2022;17(12):e0278776. doi:10.1371/JOURNAL.PONE. 0278776
41. TosunD,SchuffN,ShawLM,TrojanowskiJQ,WeinerMW;Alzheimer's Disease NeuroImaging Initiative. Relationship between CSF biomarkers of Alzheimer's disease and rates of regional cortical thinning in ADNIdata. J Alzheimers Dis . 2011;26 Suppl 3(0 3):77-90. doi:10.3233/ JAD-2011-0006
42. Gordon BA, McCullough A, Mishra S, et al. Cross-sectional and longitudinal atrophy is preferentially associated with tau rather than amyloid β positron emission tomography pathology. Alzheimers Dement . 2018;10:245-252. doi:10.1016/J.DADM.2018.02.003

![Image](./Verdi2024_artifacts/image_000022_5624af7afa90839d716b5cd18b8910284b22c62caa2d9228e1925da06ba6818f.png)

43. MohantyR,FerreiraD,NordbergA,WestmanE.Associationsbetween different tau-PET patterns and longitudinal atrophy in the Alzheimer's disease continuum: biological and methodological perspectives from disease heterogeneity. Alzheimers Res Ther . 2023;15(1):37. doi:10. 1186/S13195-023-01173-1
44. La Joie R, Visani AV, Baker SL, et al. Prospective longitudinal atrophy in Alzheimer's disease correlates with the intensity and topography of baseline tau-PET. Sci Transl Med . 2020;12(524):eaau5732. doi:10. 1126/SCITRANSLMED.AAU5732
45. Grøntvedt GR, Lauridsen C, Berge G, et al. The amyloid, tau, and neurodegeneration (A/T/N) classification applied to a clinical research cohort with long-term follow-up. J Alzheimers Dis . 2020;74(3):829837. doi:10.3233/JAD-191227
46. Hadjichrysanthou C, Evans S, Bajaj S, et al. Alzheimer's Disease Neuroimaging Initiative. The dynamics of biomarkers across the clinical spectrum of Alzheimer's disease. Alzheimers Res Ther . 2020;12(1):74. doi:10.1186/S13195-020-00636-Z
47. Emrani S, Arain HA, DeMarshall C, Nuriel T. APOE4 is associated with cognitive and pathological heterogeneity in patients with Alzheimer's disease: a systematic review. Alzheimers Res Ther . 2020;12(1):141. doi:10.1186/S13195-020-00712-4
48. Mishra S, Blazey TM, Holtzman DM, et al. Longitudinal brain imaging in preclinical Alzheimer disease: impact of APOE ε 4 genotype. Brain . 2018;141(6):1828-1839. doi:10.1093/BRAIN/AWY103
49. Jutten RJ, Sikkes SAM, Van der Flier WM, et al. Alzheimer's Disease Neuroimaging Initiative. Finding treatment effects in Alzheimer trials in the face of disease progression heterogeneity. Neurology . 2021;96(22):e2673-e2684. doi:10.1212/WNL.0000000000012022
50. Sintini I, Graff-Radford J, Senjem ML, et al. Longitudinal neuroimaging biomarkers differ across Alzheimer's disease phenotypes. Brain . 2020;143(7):2281-2294. doi:10.1093/BRAIN/AWAA155
51. Jack CR, Bennett DA, Blennow K, et al. NIA-AA research framework: toward a biological definition of Alzheimer's disease. Alzheimers Dement . 2018;14(4):535-562. doi:10.1016/J.JALZ.2018.02.018
52. Boccardi M, Dodich A, Albanese E, et al. The strategic biomarker roadmap for the validation of Alzheimer's diagnostic biomarkers: methodological update. Eur J Nucl Med Mol Imaging . 2021;48(7):20702085. doi:10.1007/S00259-020-05120-2/
53. Leung KK, Bartlett JW, Barnes J, et al. Alzheimer's Disease Neuroimaging Initiative. Cerebral atrophy in mild cognitive impairment and Alzheimer disease. Neurology . 2013;80(7):648-654. doi:10.1212/ WNL.0B013E318281CCD3
54. Goodkin O, Pemberton H, Vos SB, et al. The quantitative neuroradiology initiative framework: application to dementia. Br J Radiol . 2019;92:20190365. doi:10.1259/bjr.20190365
55. Goodkin O. Translation of quantitative MRI analysis tools for clinical neuroradiology application. Doctoral thesis. UCL (University College London); 2022.
56. Bozek J, Griffanti L, Lau S, Jenkinson M. Normative models for neuroimaging markers: impact of model selection, sample size and evaluation criteria. Neuroimage . 2023;268:119864. doi:10.1016/J. NEUROIMAGE.2023.119864
57. Predictive-clinical-neuroscience/braincharts. GitHub. Accessed April 30, 2023. https://github.com/predictive-clinical-neuroscience/ braincharts
58. Rutherford S, Barkema P, Tso IF, et al. Evidence for embracing normative modeling. eLife . 2023;12:e85082. doi:10.7554/ELIFE.85082
59. Benkarim O, Paquola C, Park BY, et al. Population heterogeneity in clinical cohorts affects the predictive accuracy of brain imaging. PLoS Biol . 2022;20(4):e3001627. doi:10.1371/JOURNAL.PBIO.3001627
60. Ashford MT, Raman R, Miller G, et al. Screening and enrollment of underrepresented ethnocultural and educational populations in the Alzheimer's disease Neuroimaging Initiative (ADNI). Alzheimers Dement . 2022;18(12):2603-2613. doi:10.1002/ALZ.12640
61. Weiner MW, Veitch DP, Miller MJ, et al. Increasing participant diversity in AD research: plans for digital screening, blood testing, and a community-engaged approach in the Alzheimer's Disease Neuroimaging Initiative 4. Alzheimers Dement . 2023;19(1):307-317. doi:10.1002/ ALZ.12797
62. Burton A. Big science for a big problem: ADNI enters its second phase. Lancet Neurol . 2011;10:206-207. doi:10.1016/S1474-4422(11) 70031-X
63. Santiago JA, Potashkin JA. The impact of disease comorbidities in Alzheimer's disease. Front Aging Neurosci . 2021;13:631770. doi:10. 3389/FNAGI.2021.631770
64. Hampel H, Au R, Mattke S, et al. Designing the next-generation clinical care pathway for Alzheimer's disease. Nature Aging . 2022;2(8):692703. doi:10.1038/s43587-022-00269-x
65. Buˇ cková BR, Fraza C, Rehák R, et al. Using normative models pretrained on cross-sectional data to evaluate longitudinal changes in neuroimaging data. eLife . 13:RP95823. doi:10.1101/2023.06.09. 544217
66. Hedges EP, Dimitrov M, Zahid U, et al. Reliability of structural MRI measurements: the effects of scan session, head tilt, inter-scan interval, acquisition sequence, FreeSurfer version and processing stream. Neuroimage . 2022;246:118751. doi:10.1016/J.NEUROIMAGE.2021. 118751
67. Gronenschild EHBM, Habets P, Jacobs HIL, et al. The effects of freesurfer version, workstation type, and macintosh operating system version on anatomical volume and cortical thickness measurements. PLoS One . 2012;7(6):e38234. doi:10.1371/JOURNAL.PONE.0038234
68. Filip P, Bednarik P, Eberly LE, et al. Different FreeSurfer versions might generate different statistical outcomes in case-control comparison studies. Neuroradiology . 2022;64(4):765-773. doi:10.1007/S00234021-02862-0
69. Chepkoech JL, Walhovd KB, Grydeland H, Fjell AM; Alzheimer's Disease Neuroimaging Initiative. Effects of change in FreeSurfer version on classification accuracy of patients with Alzheimer's disease and mild cognitive impairment. Hum Brain Mapp . 2016;37(5):1831-1841. doi:10.1002/HBM.23139
70. Smith SM, Nichols TE. Threshold-free cluster enhancement: addressing problems of smoothing, threshold dependence and localisation in cluster inference. Neuroimage . 2009;44:83-98. doi:10.1016/j. neuroimage.2008.03.061

## SUPPORTING INFORMATION

Additional supporting information can be found online in the Supporting Information section at the end of this article.

Howtocite this article: Verdi S, Rutherford S, Fraza C, et al. Personalizing progressive changes to brain structure in

Alzheimer's disease using normative modeling. Alzheimer's Dement . 2024;20:6998-7012.

