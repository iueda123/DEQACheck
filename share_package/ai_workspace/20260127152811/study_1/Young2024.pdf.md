![Image](./Young2024_artifacts/image_000000_bb180ab065c4eb3bef25c5d55c1b936739f6b16a1be712338eb77f7a55da450c.png)

## Archival Report

## Normative Modeling of Thalamic Nuclear Volumes and Characterization of Lateralized Volume Alterations in Alzheimer ' s Disease Versus Schizophrenia

Taylor R. Young, Vinod Jangir Kumar, and Manojkumar Saranathan

## ABSTRACT

BACKGROUND: Thalamic nuclei facilitate a wide range of complex behaviors, emotions, and cognition and have been implicated in neuropsychiatric disorders including Alzheimer ' s disease (AD) and schizophrenia (SCZ). The aim of this work was to establish novel normative models of thalamic nuclear volumes and their laterality indices and investigate their changes in SCZ and AD.

METHODS: Volumes of bilateral whole thalami and 10 thalamic nuclei were generated from T1 magnetic resonance imaging data using a state-of-the-art novel segmentation method in healthy control participants ( n = 2374) and participants with early mild cognitive impairment ( n = 211), late mild cognitive impairment ( n = 113), AD ( n = 88), and SCZ ( n = 168). Normative models for each nucleus were generated from healthy control participants while controlling for sex, intracranial volume, and site. Extreme z -score deviations (| z | . 1.96) and zscore distributions were compared across phenotypes. z Scores were associated with clinical descriptors.

RESULTS: Increased infranormal and decreased supranormal z scores were observed in SCZ and AD. z Score shifts representing reduced volumes were observed in most nuclei in SCZ and AD, with strong overlap in the bilateral pulvinar, medial dorsal, and centromedian nuclei. Shifts were larger in AD, with evidence of a left-sided preference in early mild cognitive impairment while a predilection for right thalamic nuclei was observed in SCZ. The right medial dorsal nucleus was associated with disorganized thought and daily auditory verbal hallucinations.

CONCLUSIONS: In AD, thalamic nuclei are more severely and symmetrically affected, while in SCZ, the right thalamic nuclei are more affected. We highlight the right medial dorsal nucleus, which may mediate multiple symptoms of SCZ and is affected early in the disease course.

https://doi.org/10.1016/j.bpsc.2024.08.006

The thalamus is a bilateral subcortical structure with widespread connectivity to the cortex, basal ganglia and brainstem, and the cerebellum (1). It serves as a relay center for primary sensory input to the cortex, facilitates movement and motor functional processing, and participates in higher cortical functions such as arousal, executive function, learning, memory, emotion, motivation, language, and multisensory integration (2). The thalamus and, speci /uniFB01 cally, its component nuclei have been implicated across the spectrum of neurological and psychiatric disorders including essential tremor, multiple sclerosis, epilepsy, obsessive-compulsive disorder, schizophrenia (SCZ), depression, autism, bipolar disorder, chronic pain syndrome, Alzheimer ' s disease (AD), and frontotemporal dementia (3 -13). Until recently, the thalamus had been considered as a whole in most neuroimaging studies. This is primarily due to the lack of readily available tools for accurate segmentation and volumetry of the component thalamic nuclei from routine structural magnetic resonance imaging (MRI ) . Specialized methods using advanced diffusion MRI and resting-state functional MRI have been proposed to segment thalamic nuclei but are limited by poor spatial resolution, distortion, and more importantly, divergence from histological parcellation (14,15).

While age-related changes of the whole thalamus have been reported by many groups (16 -21), few studies have documented volumetric changes in individual thalamic nuclei with aging. One study (22) characterized changes in thalamic shape with aging in 86 healthy volunteers. Two recent studies examined thalamic nuclear volume changes as a function of age in healthy participants using a relatively small number of participants (198 and 237, respectively) from 1 or 2 sites (23,24). Normative modeling is increasingly being used in neuropsychiatric imaging analysis and has shown value in highlighting heterogeneity that would otherwise remain undetected in classic case-control volumetric comparisons (19,25 -27). By employing state-of-the-art harmonization techniques, normative modeling methods aggregate data from multiple databases, resulting in models generated from thousands of

ISSN: 2451-9022

participants and covering the human lifespan. To date, these normative models have chie /uniFB02 y used cortical thickness or subcortical volumes (16,19,28,29), where only the whole thalamus was considered as opposed to individual thalamic nuclei. Recently, several promising methods for thalamic nuclei segmentation from structural MRI at 3T have been proposed, such as Bayesian estimation from probabilistic atlases (30), multi-atlas segmentation from specialized white matter nulled (WMn) image contrast called THalamus Optimized Multi-Atlas Segmentation (THOMAS) (31), or THOMAS adapted to standard T1 (11,32). These methods have recently been used to delineate atrophy of speci /uniFB01 c thalamic nuclei in frontotemporal dementia (10,33), AD (11,12), alcohol use disorder (34), bipolar disorder (35), and SCZ (35 -38). A very recent study (39) compared these structural MRI-based methods to evaluate their accuracy and found that our histogram-based polynomial synthesis (HIPS) -THOMAS, a variant of THOMAS that was adapted for standard T1 MRI (32), to be more accurate and sensitive than other structural MRI-based methods.

Empowered by this new sensitive and accurate method for analysis of structural T1 MRI data from public databases, we proposed normative models using individual thalamic nuclear volumes as well as their laterality indices generated from 2374 healthy participants ages 5 to 100 years, who were drawn from multiple neuroimaging databases. Then, we applied these models to studying neuropsychiatric disorders, with a speci /uniFB01 c focus on AD and SCZ, which affect older and younger populations, respectively. AD is a progressive neurodegenerative disorder with lower heterogeneity of symptoms and welldescribed pathology (40). SCZ is a highly heterogeneous disorder with a relapsing-remitting course and potential neurodevelopmental origins (41). While both AD and SCZ can include cognitive de /uniFB01 cits, psychosis (e.g., delusions and hallucinations), and dysregulated behavior, cognitive de /uniFB01 cits in multiple domains leading to functional impairment are the principal symptoms of AD while psychotic symptoms in multiple domains leading to functional impairment are the principal symptoms of SCZ. Therefore, we hypothesized that normative modeling of thalamic nuclear volumes would facilitate the identi /uniFB01 cation of shared and distinct changes in thalamic nuclear regions between the 2 disorders.

## METHODS AND MATERIALS

## Imaging Datasets

Unprocessed T1-weighted images and clinical measures were accessed from 7 cohorts (HCP DEV, HCP EP, HCP YA, UCLA, SCZ AH, HCP AGE, and ADNI) described in Supplemental Methods. Consent for public and limited access to personal data were obtained from study participants and are currently maintained by the representative cohorts. The authors followed all regulations and guidelines in obtaining access to these data and disseminating results derived from them.

## Thalamic Nuclei Segmentation

THOMAS is a state-of-the-art method that has been proposed for accurate segmentation of thalamic nuclei by leveraging the superior intrathalamic contrast of WMn magnetization-prepared rapid acquisition gradient-echo data. To segment standard T1

magnetization-prepared rapid acquisition gradient-echo data, which is the structural MRI sequence used in most clinical protocols and in public databases, a variant was recently proposed that uses a histogram-based polynomial synthesis to /uniFB01 rst generate WMn-like images from T1 prior to segmentation. HIPS-THOMASwasshowntosigni /uniFB01 cantly improve accuracy as characterized by Dice and volume similarity indices (32) compared to FreeSurfer and other THOMAS variants (39). Brie /uniFB02 y, input T1 images are N4-bias corrected to remove shading artifacts and then automatically cropped to extract a 3dimensional volume encompassing both thalami and then converted to WMn-like contrast using a polynomial transformation. This synthesized image is then segmented using the multiatlas pipeline of THOMAS, leveraging the improved intrathalamic contrast. THOMAS uses 20 manually labeled highresolution WMn -magnetization-prepared rapid acquisition gradient-echo datasets (0.8 3 0.8 3 1 mm acquired at 7T) that are transferred to the input data space via a high-resolution template space using diffeomorphic nonlinear registration. A joint label fusion algorithm is used to fuse the 20 sets of labels to generate the /uniFB01 nal thalamic nuclei segmentation (Figure S1). Volumes of 10 thalamic nuclei and the whole thalamus were generated for each hemisphere in addition to 2 extrathalamic structures (the habenula and the mammillothalamic tract). The 10 thalamic nuclei considered in this work are anteroventral (AV), pulvinar (Pul), lateral geniculate nucleus (LGN), medial geniculate nucleus (MGN), medial dorsal -parafasicular (MD/Pf), centromedian (CM), ventral anterior, ventral lateral anterior (VLa), ventral lateral posterior (VLp), and ventral posterolateral. To assess left versus right hemisphere differences in structures, the laterality index (LI) de /uniFB01 ned as

<!-- formula-not-decoded -->

was computed for each nucleus, where V L and V R are the nuclei volumes corresponding to the left and right hemispheres.

## Data Quality Control

All segmentations were visually inspected using a custom Python script that generated a montage of axial, sagittal, and coronal slices of the thalamic nuclear segmentations overlaid on input images for rapid review. Participants with missing or failed segmentations were reprocessed or excluded.

## Data Preprocessing

Volumes for control samples 6 3 3 the IQR for each site were retained. This larger threshold was chosen to establish a more conservative estimate (compared with the conventional 1.5 3 IQR) of model performance and subsequent z scores. To generate data for univariate models (see Normative Modeling and Supplemental Methods), the following preprocessing was performed: volumes were /uniFB01 rst corrected for total intracranial volume (TIV); site effects were mitigated with ComBat in sex-strati /uniFB01 ed datasets as implemented in NeuroHarmonize Python library (20,42,43); volumes were then normalized by subtracting the mean (mean centering); and /uniFB01 nally sexstrati /uniFB01 ed datasets were created. For TIV correction, the estimated total intracranial volume (eTIV) was generated using the FreeSurfer mri\_segstats binary (44). Volumes for each region

were adjusted for eTIV separately within each site using the residual method (19,45) as follows:

<!-- formula-not-decoded -->

Here, V 0 and V are the adjusted and unadjusted volumes, and b is the slope of V versus eTIV estimated as the covariance(eTIV, V )/variance(eTIV). The above preprocessing steps were omitted for multivariate models in which sex, eTIV, and site were included as covariates (see Supplemental Methods).

## Normative Modeling

Ordinary least squares regression, multiple fractional polynomial regression, and generalized additive models of location, shape, and scale (GAMLSS) were compared as predictive models using both univariate and multivariate models that accounted for the effects of age, sex, TIV, and site. Univariate models included age as the independent variable and were trained on sex-strati /uniFB01 ed datasets, with volumes adjusted for TIV and harmonized for site using ComBat, while multivariate models used age, sex, TIV, and site as covariates (see Supplemental Methods).

Using the top-performing model, z scores were calculated for cases and controls from the residuals of /uniFB01 tted values and the standard deviation of residuals in the control samples. In the case of GAMLSS, /uniFB01 tted values were predicted for both m and s for each observation. z Scores were then calculated as follows:

<!-- formula-not-decoded -->

z-Score distributions in cases and controls were compared with the z test for each region. To control for multiple testing, p values were adjusted using the Benjamini-Hochberg method (46), and a false discovery rate threshold of false discovery rate -adjusted p , .05 was set. Supplemental analyses were performed comparing the z -score distributions of left and right thalamic nuclei within phenotypes and the LI of cases to controls (see the Supplement).

Extreme z -score deviations (infranormal and supranormal) were calculated as those outside the 95th percentile (| z | . 1.96). Regional overlap was calculated for each phenotype as the percentage of participants within each phenotype with an extreme z -score deviation in each region. Multigroup comparison between cases and controls was performed using the Kruskal-Wallis rank sum test on counts of extreme z -score deviations.

## Associations Between z Scores and Clinical Variables

Pearson correlation coef /uniFB01 cients were calculated between z scores and clinical variables for all regions. The p values were adjusted for multiple hypothesis testing using the BenjaminiHochberg (46) method. Clinical variables were analyzed as shown in Table 1.

## Comparison of Normative Modeling to Case-Control Studies

For comparisons with normative modeling, conventional casecontrol analyses were performed using jamovi software

Table 1. Cohorts, Clinical Tools, and Variables Analyzed

| Cohort   | Expansion or Description                                                                                      | Clinical Tools and Variables Analyzed                                                                                                                                                             |
|----------|---------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ADNI     | Alzheimer ' s Disease Neuroimaging Initiative                                                                 | Mini-Mental State Examination (70), Montreal Cognitive Assessment (71), and amyloid-tau index (72), which was calculated as A b /(240 1 (1.8 3 tau))                                              |
| SCZ AH   | Brain correlates of speech perception in patients with schizophrenia with and without auditory hallucinations | Psychotic Symptom Rating Scales Auditory Hallucination subscale (73,74)                                                                                                                           |
| UCLA     | UCLA Consortium for Neuropsychiatric Phenomics LA5c Study                                                     | Scale for the Assessment of Positive Symptoms (subscales and global ratings), Scale for the Assessment of Negative Symptoms (subscales and global ratings) (75)                                   |
| HCP EP   | Human Connectome Project for Early Psychosis                                                                  | The Positive and Negative Syndrome Scale (76). The total score, the Marder 5 factor scores for disorganization, the positive and negative subscales (77), and each individual item were analyzed. |

(version 2.3.28.0). Analyses of covariance were used for comparisons, with age, sex, and TIV as covariates on ComBat site-corrected data. Dunnett ' s test was used for post hoc analyses with corrections for multiple comparisons. Cohen ' s d (an output of analysis of covariance) was used to quantify the difference in effect sizes between cases and controls from the previously de /uniFB01 ned cohorts.

## RESULTS

A total of 2954 of 2978 participants ages 5.6 to 100 years passed initial quality control procedures (Table 2 and Figure 1). Of these 2954 participants, 2374 (1279 female, 1095 male) belonged to their respective control groups, and 580 carried one of the following phenotypic labels related to AD or SCZ: early mild cognitive impairment (EMCI) (100 female, 111 male), late MCI (LMCI) (60 female, 53 male), AD (41 female, 47 male), SCZ from the UCLA cohort (UCLA SCZ) (10 female, 31 male), nonaffective psychosis from the early psychosis cohort (24 female, 59 male), SCZ with auditory verbal hallucinations (SCZ 1 AH) (2 female, 19 male), and SCZ without auditory verbal hallucinations (SCZ 2 AH) (7 female, 16 male). One hundred sixty-eight participants (43 female, 125 male) from the 4 SCZ phenotypes were included in the aggregate SCZ group (all SCZ).

## Normative Model Comparison and Evaluation

The multivariate GAMLSS model with age, sex, eTIV, and site as covariates was the overall top-performing model. The performances of ordinary least squares and multiple fractional polynomial regression models were comparable, and the differences in model performance between univariate and multivariate models were minimal (Table S1 and Figures S8 -S10). Controlling for sex, eTIV, and site had a bene /uniFB01 cial effect on model performance, with eTIV having the largest individual

Table 2. Participant Characteristics of Each Cohort

|         |           |      | Sex, n   | Sex, n   |                       | Age Range, Years   | Age Range, Years   |
|---------|-----------|------|----------|----------|-----------------------|--------------------|--------------------|
| Cohort  | Phenotype | n    | Female   | Male     | Age, Years, Mean (SD) | Min                | Max                |
| HCP DEV | Control   | 628  | 337      | 291      | 14.4 (4.1)            | 5.6                | 21.9               |
| HCP EP  | Early SCZ | 83   | 24       | 59       | 22.2 (3.1)            | 16.7               | 30.8               |
|         | Control   | 56   | 19       | 37       | 24.9 (4.1)            | 16.8               | 35.7               |
| HCP YA  | Control   | 726  | 398      | 328      | 28.7 (3.7)            | 22.0               | 37.0               |
| UCLA    | Control   | 117  | 55       | 62       | 31.0 (8.5)            | 21.0               | 50.0               |
|         | UCLA SCZ  | 41   | 10       | 31       | 36.0 (9.0)            | 22.0               | 49.0               |
| SCZ AH  | SCZ 1 AH  | 21   | 2        | 19       | 39.7 (13.5)           | 19.0               | 66.0               |
|         | Control   | 22   | 7        | 15       | 41.0 (14.5)           | 20.0               | 64.0               |
|         | SCZ 2 AH  | 23   | 7        | 16       | 45.0 (7.4)            | 31.0               | 61.0               |
| HCP AGE | Control   | 701  | 395      | 306      | 60.4 (15.8)           | 36.0               | 100.0              |
| ADNI    | EMCI      | 211  | 100      | 111      | 70.5 (7.1)            | 55.5               | 88.6               |
|         | LMCI      | 113  | 60       | 53       | 71.8 (7.9)            | 55.0               | 91.4               |
|         | Control   | 124  | 68       | 56       | 73.4 (6.2)            | 56.2               | 85.6               |
|         | AD        | 88   | 41       | 47       | 74.0 (7.8)            | 55.9               | 88.3               |
| Total   | Control   | 2374 | 1279     | 1095     |                       |                    |                    |
|         | Case      | 580  | 244      | 336      |                       |                    |                    |

AD, Alzheimer ' s disease; ADNI, Alzheimer ' s Disease Neuroimaging Initiative; EMCI, early mild cognitive impairment; HCP AGE, Lifespan Human Connectome Project in Aging; HCP DEV, Lifespan Human Connectome Project in Development; HCP EP, Human Connectome Project for Early Psychosis; Early SCZ, participants with nonaffective psychosis (i.e., schizophrenia or schizoaffective disorder) within 5 years of disease onset; HCP YA, Human Connectome Project Young Adult Study; LMCI, late mild cognitive impairment; Max, maximum; Min, minimum; SCZ AH, brain correlates of speech perception in schizophrenia patients with and without auditory hallucinations; SCZ 1 AH, schizophrenia with daily auditory verbal hallucinations; SCZ 2 AH, schizophrenia without auditory verbal hallucinations; UCLA, UCLA Consortium for Neuropsychiatric Phenomics LA5c Study; UCLA SCZ, participants with schizophrenia or schizoaffective disorder.

effect on reducing mean absolute error (Figure S2). Centile plots demonstrated differential age associations for the thalamic regions. A linear trend was observed in the whole thalamus, Pul, AV, and MGN whereas the CM, VLa, ventral posterolateral, and LGN demonstrated a peak in volume around age 30 to 40 years followed by a linear decline in volume (Figure 2 and Figure S3). z -Score distributions for control participants approximated normal distributions centered around 0 for volumes and LIs of all nuclei (Figure 3). Leftward deviations from the center in the z -score distributions (as can

Figure 1. Age ranges for cases and controls within each cohort. ADNI, Alzheimer ' s Disease Neuroimaging Initiative; HCP AGE, Lifespan Human Connectome Project in Aging; HCP DEV, Lifespan Human Connectome Project in Development; HCP EP, Human Connectome Project for Early Psychosis; HCP YA, Human Connectome Project Young Adult Study; SCZ AH, brain correlates of speech perception in patients with schizophrenia with and without auditory hallucinations; UCLA, UCLA Consortium for Neuropsychiatric Phenomics LA5c Study.

![Image](./Young2024_artifacts/image_000001_647850051579d336906082096aa9b78b7cd2d15293e7b2a88c426976a8d2883a.png)

Figure 2. Sex-speci /uniFB01 c centile plots of representative regions for the generalized additive models of location, shape, and scale univariate model. Age is on the x-axis, and estimated total intracranial volume -adjusted volume is on the y-axis. Thalamus refers to the whole thalamus. CM, centromedian nucleus; VPL, ventral posterior lateral nucleus.

![Image](./Young2024_artifacts/image_000002_ada4a7d56fcbc54fe44ef6f36dd96f539057d80409317b331ec1413fd67ebd12.png)

be seen in the case cohorts) indicated reduced volumes on either the left or right and a greater reduction on the left compared with the right side for LI. Note the pattern of increasing reduction in volume from EMCI through LMCI to AD and a more right side -dominant pattern of reduced volumes in the SCZ cohorts. This is also re /uniFB02 ected in the LI distributions.

## MCI and AD

Statistically signi /uniFB01 cant increases in the number of infranormal z scores were observed in EMCI, LMCI, and AD, while statistically signi /uniFB01 cant decreases in supranormal z scores were observed in LMCI and AD (Figure 4). Regional overlap maps of extreme z -score deviations are shown in Figure S4. Statistically signi /uniFB01 cant differences in z -score distributions (compared with control participants) were colorized and are depicted in Figure 5 with the corresponding values tabulated on the right side. There was an increase in both the number of nuclei involved and the magnitude of the z score shifts from EMCI to LMCI and AD, where almost all nuclei showed volume reduction (i.e., atrophy). In each cohort, the left AV nucleus was the most affected nucleus, and the difference in means of z -score distributions was greater in the left than the right in the whole thalamus and MD/Pf nucleus (Figure 5). Statistically signi /uniFB01 cant differences in z -score distributions for LI were observed in the whole thalamus, VLp, and MGN in EMCI, suggesting smaller volumes on the left than the right in the whole thalamus and VLp nucleus and vice versa in the MGN (Figure 5). The right ventral nucleus, posterior nucleus, LGN, and MGN were affected in EMCI and LMCI, with the ventral posterior lateral nucleus remaining more affected on the right in AD while the MGN was more affected on the left in AD. No statistically signi /uniFB01 cant differences in z -score distributions were observed when the left and right sides were compared. Most regions showed weak to moderate, statistically signi /uniFB01 cant correlations with the Mini-Mental State Examination and Montreal Cognitive Assessment while fewer regions showed signi /uniFB01 cant correlations with the amyloid-tau index (Table S2). Regions that showed the greatest differences in means of the z -score distributions including the whole thalamus, AV, Pul, and MD/Pf bilaterally

Figure 3. z -Score distributions for left and right thalamic nuclei and the laterality index (LI), calculated as (L 2 R)/(L 1 R), where L is the volume of the left region and R is the volume of the right region. For LI, z scores . 1 indicate that the volume on the right is less than the volume on the left. Thalamus refers to the whole thalamus. AD, Alzheimer ' s disease; All SCZ, aggregate schizophrenia group; AV, anteroventral nucleus; CM, centromedian nucleus; Early SCZ, nonaffective psychosis (schizophrenia) within 5 years of onset; EMCI, early mild cognitive impairment; LGN, lateral geniculate nucleus; LMCI, late mild cognitive impairment; MD/ Pf, medial dorsal-parafascicular nucleus; MGN, medial geniculate nucleus; Pul, pulvinar nucleus; SCZ 2 AH, schizophrenia without auditory verbal hallucinations; SCZ 1 AH, schizophrenia with daily auditory verbal hallucinations; UCLA SCZ, participants with schizophrenia or schizoaffective disorder; VA, ventral anterior nucleus; VLa, ventral lateral anterior nucleus; VLp, ventral lateral posterior nucleus; VPL, ventral posterior lateral nucleus.

![Image](./Young2024_artifacts/image_000003_afbe0d2ca4624e3119021efbe860619b9852b5025b0a3de2a72b4083c89df13c.png)

also showed the strongest correlations with clinical variables.

## Schizophrenia

Statistically signi /uniFB01 cant increases in the number of infranormal z scores were observed in the SCZ 1 AH, SCZ 2 AH, and all SCZ cohorts while a statistically signi /uniFB01 cant decrease in supranormal z scores was observed in the all SCZ cohort (Figure 4). Regional overlap maps of extreme z -score deviations in SCZ can be seen in Figure S4. Statistically signi /uniFB01 cant differences in z -score distributions were observed in 13 of 20 thalamic regions (11 nuclei and the bilateral whole thalamus) in the all SCZ cohort (Figure 6). Three nuclei -the AV, ventral anterior, and VLa -were speci /uniFB01 c to the right thalamus (Figure 6). Statistically signi /uniFB01 cant shifts in LI

consistent with decreased volumes on the right compared with the left were observed in the whole thalamus and the ventral anterior, VLp, and MGN in the all SCZ cohort (Figure 6). Interestingly, statistically signi /uniFB01 cant differences in z -score distributions for the right AV and left CM nuclei were unique to the all SCZ cohort (Figure 6). The statistically signi /uniFB01 cant differences in z -score distributions in the early psychosis cohort are a subset of those observed in the all SCZ cohort (Figure 6). In the whole thalamus and the Pul, the left side was more affected than the right, and the LI in the Pul and ventral posterior lateral nucleus indicated that the volumes on the left were decreased compared with those on the right (Figure 6). In the SCZ 2 AH and SCZ 1 AH cohorts, most statistically signi /uniFB01 cant differences in z -score distributions observed are on the right, and all of the

Figure 4. Comparison of the number of extreme z -score deviations between control participants and each phenotype/cohort. AD, Alzheimer ' s disease; All SCZ, aggregate schizophrenia group; Early SCZ, nonaffective psychosis (schizophrenia) within 5 years of onset; EMCI, early mild cognitive impairment; LMCI, late mild cognitive impairment; SCZ 2 AH, schizophrenia without auditory verbal hallucinations; SCZ 1 AH, schizophrenia with daily auditory verbal hallucinations; UCLA SCZ, participants with schizophrenia or schizoaffective disorder.

![Image](./Young2024_artifacts/image_000004_126fdfc0e0fb7b3cda0a9c600da4a5961f8ce85d304497fa19ceb27e72e7f5d6.png)

observed differences in means in the LI indicate smaller volumes on the right than the left (Figure 6). Notably, the largest differences in means in the SCZ 1 AH cohort were observed in the right MGN, right Pul, bilateral MD/Pf nuclei, and the right thalamus (Figure 6). There were no statistically signi /uniFB01 cant changes in z -score distributions in the UCLA SCZ cohort, and there were no statistically signi /uniFB01 cant differences in z -score distributions when the left region was compared with the corresponding right region in any cohort. Interestingly, moderate, inverse, statistically signi /uniFB01 cant correlations were observed in the UCLA SCZ cohort, predominantly in the left thalamic regions (Table S3). No other statistically signi /uniFB01 cant correlations were observed in the SCZ cohorts. Direct pairwise comparisons between all phenotypes and control participants are shown in Figure S5 and described in the Supplement.

## Normative Modeling Versus Case-Control Analysis

Comparisons between normative modeling results and those obtained from a traditional case-control analysis (i.e., Cohen ' s d ) for the EMCI, AD, all SCZ, and SCZ 1 AH groups are shown in Figure 7. There was an increase in the number of statistically signi /uniFB01 cant thalamic regions identi /uniFB01 ed using normative modeling (Table S4). Not all statistically signi /uniFB01 cant nuclei found using a case-control approach remained statistically signi /uniFB01 -cant using the normative modeling approach (e.g., VLp nucleus in the all SCZ cohort).

## DISCUSSION

Normative modeling has shown promise in identifying regional brain abnormalities and delineating heterogeneity that is potentially missed in traditional case-control studies that compare mean volumes across groups (18,27). Here, we presented analyses using novel normative models of thalamic nuclei volumes and LIs, which leverage the /uniFB02 exibility of GAMLSS as a prediction method, the state-of-theart HIPS-THOMAS for accurate segmentation of thalamic nuclei from standard T1 MRI data, and a large, harmonized control dataset garnered from 2374 participants across 7 cohorts.

In addition to the optimization of our normative models (see Supplemental Results), our analysis of ADNI data supports the validity of our normative models. AD is relatively homogeneous, and an estimated 78% of patients have a typical amnestic syndrome (40), and up to 13% of patients with MCI progress to AD annually (47). We observed a clear progression of increasing z score shifts and increasing numbers of thalamic nuclei from EMCI through LMCI to AD. In each phenotype, the bilateral AV, MD/Pf, and Pul nuclei were the most severely affected, with the bilateral AV nuclei being the most affected in AD (Figure 5). These nuclei also showed the strongest correlations between z scores and clinical measures including cerebrospinal /uniFB02 uid biomarkers. (Table S2). These results are concordant with previous analysis of covariance -based analyses using the same ADNI data by Bernstein et al. (11). Based

A

Figure 5. Statistically signi /uniFB01 cant (false discovery rate -adjusted p , .05) changes in z -score distributions for left and right thalamic nuclei and the laterality index (LI) in (A) early mild cognitive impairment, (B) late mild cognitive impairment, and (C) Alzheimer ' s disease. Values represent the difference in means between the z -score distribution of the control cohort and that of the phenotypic cohort (see z -score distributions in Figure 3). The LI was calculated as (L 2 R)/(L 1 R), where L is the volume of the left region and R is the volume of the right region. A positive value indicates that volumes of the left-sided region are smaller than the volumes of the right-sided region compared with the control cohort. Thalamus refers to the whole thalamus. AV, anteroventral nucleus; CM, centromedian nucleus; L, left; LGN, lateral geniculate nucleus; MD/Pf, medial dorsal-parafascicular nucleus; MGN, medial geniculate nucleus; Pul, pulvinar nucleus; R, right; VA, ventral anterior nucleus; VLa, ventral lateral anterior nucleus; VLp, ventral lateral posterior nucleus; VPL, ventral posterior lateral nucleus.

![Image](./Young2024_artifacts/image_000005_3f093d1a8dc10bb91c7faf4e246c74b766a56aef235d59b86af3322c7fe3426f.png)

on the known connectivity of the AV, MD, and Pul nuclei, they also comport with the typical progression of amyloid and tau from the medial temporal lobes to the medial prefrontal and parietal lobes and with observations of both subcortical spread of amyloid and deposition of amyloid and tau in the anterior thalamic nuclei (11,48 -50). Taken together, this is compelling evidence that our normative models were well calibrated and captured volumetric changes associated with underlying pathology, disease state, and clinical measures.

Consistent with other normative modeling efforts in psychiatric disorders (25,51 -53), we observed heterogeneity across SCZ cohorts and phenotypes (Figure 6 and Figure S4). Recent volumetric analyses of thalamic nuclear regions comparing similar phenotypes (i.e., SCZ and SCZ with and without hallucinations) to healthy control participants identi /uniFB01 ed signi /uniFB01 cant associations with the medial dorsal nucleus, Pul, LGN, MGN, and ventrolateral nucleus (35,38,54). Results from the current analyses are consistent with these /uniFB01 ndings and

![Image](./Young2024_artifacts/image_000006_ad69487716944c374c87547aa7010670f9e4e04ceffefb9d475f0a13840f9955.png)

expand on them to include the right anteroventral nucleus, right ventral anterior nucleus, and bilateral CM nuclei. These results are also consistent with a recent review encompassing structural and functional imaging, electroencephalography, and neurochemistry that suggested that thalamic abnormalities -which were hypothesized to affect the AV, CM, MD, and Pul nuclei -are central to the pathology of SCZ (55).

We observed a predilection for right thalamic regions in all but one of the SCZ cohorts, where there were no statistically signi /uniFB01 cant associations observed. It is likely that lateralized /uniFB01 ndings represent differences in psychopathology (12,56 -59), and thalamic nuclei are known to carry out multiple functions (60). We found that the right MD/Pf region was associated with severity of formal thought disorder ratings and persistent auditory verbal hallucinations (Figure 6 and Table S3). The MD nucleus is a higher-order association nucleus with widespread connectivity including reciprocal connections to the prefrontal cortex, basal ganglia, and amygdala; input from the brainstem, midbrain, and cerebellum; and output to the temporal lobe and anterior cingulate cortex, which allows it to regulate and coordinate multifaceted networks relevant to language, motivated behavior, attention and horizontal gaze, emotion, and executive function and working memory (2,61 -63). We also observed that the right MD nucleus was affected early in the course of SCZ whereas the left MD nucleus was not affected (Figure 6). Taken together, this suggests a key role for the right MD nucleus in the development of multiple facets of SCZ through dysregulated network activity.

A recent study by Huang et al. focused on lifespan trajectories of thalamic regions and cognition in SCZ (64). The current work furthers the work of Huang et al. through systematic evaluation and optimization of the normative modeling methodology, inclusion of AD as a comparator to SCZ, association of z scores with psychopathology rather than cognition, and development of novel normative models of LI. The current work also uses HIPS-THOMAS, which has been shown recently to be more sensitive and accurate than the T1-THOMAS used by Williams et al. (39). Additional strengths of the current study include signi /uniFB01 cant results consistent with previous analyses in SCZ and volumetric changes in the thalamus that potentially explain psychopathology based on known functional neuroanatomy. Importantly, these insights could not be made based only on volumetric analysis of the whole thalamus, as has typically been done. Additionally, normative modeling has successfully addressed the effects of individual variation in variables such as age, sex, eTIV, and site, thereby simplifying the interpretation in terms of disease state (Figures S6 and S7). Because the role of the thalamus in neuropsychiatric disease remains an area of active research, the use of HIPS-THOMAS in conjunction with normative modeling can serve a critical role

=

Figure 6. Statistically signi /uniFB01 cant (false discovery rate -adjusted p , .05) changes in z -score distributions for left and right thalamic nuclei and the laterality index (LI) in schizophrenia (SCZ) cohorts, (A) aggregate SCZ, (B) early SCZ, (C) SCZ without auditory verbal hallucinations, and (D) SCZ with auditory verbal hallucinations. Values represent the difference in means between the z -score distribution of the control cohort and that of the phenotypic cohort (see z -score distributions in Figure 3). The laterality index, calculated as (L 2 R)/(L 1 R), where L is the volume of the left region and R is the volume of the right region. A positive value indicates that volumes of the left-sided region are smaller than the volumes of the right-sided region compared with the control cohort. Thalamus refers to the whole thalamus. AV, anteroventral nucleus; CM, centromedian nucleus; LGN, lateral geniculate nucleus; MD/Pf, medial dorsal-parafascicular nucleus; MGN, medial geniculate nucleus; Pul, pulvinar nucleus; VA, ventral anterior nucleus; VLa, ventral lateral anterior nucleus; VLp, ventral lateral posterior nucleus; VPL, ventral posterior lateral nucleus.

in future research, particularly if extended to include structural connectivity, functional connectivity, and brain age measures.

Weaknesses of the current study include a relatively small number of individuals in the SCZ cohorts, a moderate sample size for normative modeling, absence of some thalamic nuclei such as the centrolateral or intralaminar nuclei in the analyses, and using the same model for all regions. Additionally, our AD analyses suggest that our model performed well even in the older age ranges, and previous analyses by Ge et al. suggest that a sample size of 2000 to 3000 is adequate to optimize a normative model ' s performance (18). Our interpretation of volumetric changes is dependent on a change in volume being associated with a change in network function without assessing the network directly. While this is another area for future work, the concept of Hebbian learning, the diaschisis hypothesis, and emerging evidence that atrophy propagates along network lines support this approach (65 -69). Some regions such as the LGN and MGN are small and potentially more susceptible to subtle variability and imaging artifacts despite their accurate segmentation. Results concerning these nuclei should be interpreted with caution. Finally, relevant factors such as disease duration, substance abuse, medication burden, and head motion that may confound results were outside the scope of these analyses.

## Conclusions

We have developed novel normative models of thalamic nuclear volumes and performed analyses to support their validity and potential to generate novel insights into disease states. Notably, we present novel results that suggest dysfunction of the right thalamus -particularly the right MD/ Pf nucleus -and thalamic-mediated networks as a key feature in SCZ. To the best of our knowledge, we present the /uniFB01 rst normative models of LI. Finally, our centile curves provide important insight into volume changes to thalamic nuclear regions across the lifespan, about which there is a paucity of research.

## ACKNOWLEDGMENTS AND DISCLOSURES

MSacknowledges funding from the National Institute of Biomedical Imaging and Bioengineering (Grant No. R01EB032674). Neither the authors nor their institutions received payment or services from a third party for any aspect of the submitted work.

Data were provided (in part) by the HCP (Human Connectome Project), WU-Minn Consortium (principal investigators, David Van Essen and Kamil Ugurbil; Grant No. 1U54MH091657), funded by the 16 National Institutes of Health (NIH) Institutes and Centers that support the NIH Blueprint for Neuroscience Research and by the McDonnell Center for Systems Neuroscience at Washington University.

A

![Image](./Young2024_artifacts/image_000007_0569f61ff2096f8483f1dce3e20a02820dcc836aa9eca7a91d439dab543b181d.png)

## Normative Models of Thalamic Nuclei

HCP DEV: Research reported in this publication was supported by the National Institute of Mental Health (Grant No. U01MH109589) and by funds provided by the McDonnell Center for Systems Neuroscience at Washington University in St. Louis. The HCP-Development 2.0 Release data used in this report came from DOI: 10.15154/1520708.

HCP AGE: Research reported in this publication was supported by the National Institute on Aging (Award No. U01AG052564) and by funds provided by the McDonnell Center for Systems Neuroscience at Washington University in St. Louis. The HCP-Aging 2.0 Release data used in this report came from DOI: 10.15154/1520707.

HCP EP: Research using HCP-EP data reported in this publication was supported by the National Institute of Mental Health (Award No. U01MH109977). The HCP-EP 1.1 Release data used in this report came from DOI: 10.15154/1522899.

ADNI: Data used in preparation of this article were obtained from the ADNI database (adni.loni.usc.edu). As such, the investigators within the ADNI contributed to the design and implementation of ADNI and/or provided data but did not participate in analysis or writing of this report. A complete listing of ADNI investigators can be found at: http://adni.loni.usc.edu/ wpcontent/uploads/how\_to\_apply/ADNI\_Acknowledgement\_List.pdf. Data collection and sharing for this project was funded by the Alzheimer ' s Disease Neuroimaging Initiative (NIH Grant No. U01 AG024904) and DOD ADNI (Department of Defense Award No. W81XWH-12-2-0012). ADNI is funded by the National Institute on Aging, the National Institute of Biomedical Imaging and Bioengineering, and through generous contributions from the following: AbbVie, Alzheimer ' s Association; Alzheimer ' s Drug Discovery Foundation; Araclon Biotech; BioClinica, Inc.; Biogen; BristolMyers Squibb Company; CereSpir, Inc.; Cogstate; EISAI Inc.; Elan Pharmaceuticals, Inc.; Eli Lilly and Company; EuroImmun; F. Hoffmann-La Roche Ltd. and its af /uniFB01 liated company Genentech, Inc.; Fujirebio; GE Healthcare; IXICO Ltd.; Janssen Alzheimer Immunotherapy Research &amp; Development, LLC.; Johnson &amp; Johnson Pharmaceutical Research &amp; Development LLC.; Lumosity; Lundbeck; Merck &amp; Co., Inc.; Meso Scale Diagnostics, LLC.; NeuroRxResearch; Neurotrack Technologies; Novartis Pharmaceuticals Corporation; P /uniFB01 zer Inc.; Piramal Imaging; Servier; Takeda Pharmaceutical Company; and Transition Therapeutics. The Canadian Institutes of Health Research is providing funds to support ADNI clinical sites in Canada. Private sector contributions are facilitated by the Foundation for the NIH (http:// www.fnih.org). The grantee organization is the Northern California Institute for Research and Education, and the study is coordinated by the Alzheimer ' s Therapeutic Research Institute at the University of Southern California. ADNI data are disseminated by the Laboratory for Neuro Imaging at the University of Southern California.

TRY and MS conceived the study and analysis plan. TRY, MS, and VJK processed imaging data. TRY and MS performed normative modeling and statistical analysis. TRY and MS drafted the manuscript. VJK edited and contributed to the manuscript. All authors have reviewed and approved this manuscript.

Neuroimaging data used in these analyses were drawn from both open access and controlled access sources including OpenNeuro, the HCP, and ADNI. We will make data available to the extent possible as allowed by these resources. Normative models, HIPS-THOMAS, and R code are available at: https://github.com/thalamicseg/hipsthomasdocker.

A previous version of this article was published as a preprint on medRxiv: https://doi.org/10.1101/2024.03.06.24303871.

The authors report no biomedical /uniFB01 nancial interests or potential con /uniFB02 icts of interest.

## ARTICLE INFORMATION

From the Department of Psychiatry, University of Massachusetts Chan Medical School, Worcester, Massachusetts (TRY); Department of

=

Figure 7. Comparison of normative modeling on the left side of the /uniFB01 gure to traditional case-control study on the right side of the /uniFB01 gure in (A) early mild cognitive impairment, (B) Alzheimer ' s disease, (C) aggregate schizophrenia, and (D) schizophrenia with daily auditory verbal hallucinations. Normative modeling values represent the difference in means for statistically signi /uniFB01 cant changes in z -score distributions. Case-control values represent the effect size (Cohen ' s d ). AV, anteroventral nucleus; CM, centromedian nucleus; LGN, lateral geniculate nucleus; MD/Pf, medial dorsal-parafascicular nucleus; MGN, medial geniculate nucleus; Pul, pulvinar nucleus; VA, ventral anterior nucleus; VLa, ventral lateral anterior nucleus; VLp, ventral lateral posterior nucleus; VPL, ventral posterior lateral nucleus.

Neurology, University of Massachusetts Chan Medical School, Worcester, Massachusetts (TRY); Department of High/uniFB01 eld Magnetic Resonance, Max Planck Institute for Biological Cybernetics, Tübingen, Germany (VJK); and Department of Radiology, University of Massachusetts Chan Medical School, Worcester, Massachusetts (MS).

Address correspondence to Taylor R. Young, M.D., at taylor.young@ umassmemorial.org.

Received Jun 6, 2024; revised and accepted Aug 13, 2024.

Supplementary material cited in this article is available online at https:// doi.org/10.1016/j.bpsc.2024.08.006.

## REFERENCES

1. Biesbroek JM, Verhagen MG, van der Stigchel S, Biessels GJ (2024): When the central integrator disintegrates: A review of the role of the thalamus in cognition and dementia. Alzheimers Dement 20:2209 -2222.
2. Schmahmann JD (2003): Vascular syndromes of the thalamus. Stroke 34:2264 -2278.
3. Neudorfer C, Kultas-Ilinsky K, Ilinsky I, Paschen S, Helmers A-K, Cosgrove GR, et al. (2024): The role of the motor thalamus in deep brain stimulation for essential tremor. Neurotherapeutics 21:e00313.
4. Fujimori J, Nakashima I (2024): Early-stage volume losses in the corpus callosum and thalamus predict the progression of brain atrophy in patients with multiple sclerosis. J Neuroimmunol 387:578280.
5. Burdette D, Patra S, Johnson L (2024): Corticothalamic responsive neurostimulation for focal epilepsy: A single-center experience. J Clin Neurophysiol 47:630 -639.
6. Yang Z, Xiao S, Su T, Gong J, Qi Z, Chen G, et al. (2024): A multimodal meta-analysis of regional functional and structural brain abnormalities in obsessive -compulsive disorder. Eur Arch Psychiatry Clin Neurosci 274:165 -180.
7. Alemán-Gómez Y, Baumgartner T, Klauser P, Cleusix M, Jenni R, Hagmann P, et al. (2023): Multimodal magnetic resonance imaging depicts widespread and subregion speci /uniFB01 c anomalies in the thalamus of early-psychosis and chronic schizophrenia patients. Schizophr Bull 49:196 -207.
8. Wang X-Y, Xu X, Chen R, Jia W-B, Xu P-F, Liu X-Q, et al. (2023): The thalamic reticular nucleus-lateral habenula circuit regulates depressive-like behaviors in chronic stress and chronic pain. Cell Rep 42:113170.
9. Forno G, Saranathan M, Contador J, Guillen N, Falgàs N, TortMerino A, et al. (2023): Thalamic nuclei changes in early and late onset Alzheimer ' s disease. Curr Res Neurobiol 4:100084.
10. McKenna MC, Lope J, Bede P, Tan EL (2023): Thalamic pathology in frontotemporal dementia: Predilection for speci /uniFB01 c nuclei, phenotypespeci /uniFB01 c signatures, clinical correlates, and practical relevance. Brain Behav 13:e2881.
11. Bernstein AS, Rapcsak SZ, Hornberger M, Saranathan M, Alzheimer ' s Disease Neuroimaging Initiative (2021): Structural changes in thalamic nuclei across prodromal and clinical Alzheimer ' s disease. J Alzheimers Dis 82:361 -371.
12. Low A, Mak E, Malpetti M, Chouliaras L, Nicastro N, Su L, et al. (2019): Asymmetrical atrophy of thalamic subnuclei in Alzheimer ' s disease and amyloid-positive mild cognitive impairment is associated with key clinical features. Alzheimers Dement (Amst) 11:690 -699.
13. HwangWJ,KwakYB,ChoKIK,LeeTY,OhH,HaM, et al. (2022): Thalamic connectivity system across psychiatric disorders: Current status and clinical implications. Biol Psychiatry Glob Open Sci 2:332 -340.
14. Mastropasqua C, Bozzali M, Spanò B, Koch G, Cercignani M (2015): Functional anatomy of the thalamus as a model of integrated structural and functional connectivity of the human brain in vivo. Brain Topogr 28:548 -558.

15. Iglehart C, Monti M, Cain J, Tourdias T, Saranathan M (2020): A systematic comparison of structural-, structural connectivity-, and functional connectivity-based thalamus parcellation techniques. Brain Struct Funct 225:1631 -1642.
16. Bethlehem RAI, Seidlitz J, White SR, Vogel JW, Anderson KM, Adamson C, et al. (2022): Brain charts for the human lifespan. Nature 604:525 -533.
17. Rutherford S, Fraza C, Dinga R, Kia SM, Wolfers T, Zabihi M, et al. (2022): Charting brain growth and aging at high spatial precision. Elife 11:e72904.
18. Ge R, Yu Y, Qi YX, Fan Y-N, Chen S, Gao C, et al. (2024): Normative modelling of brain morphometry across the lifespan with CentileBrain: Algorithm benchmarking and model optimisation. Lancet Digit Health 6:e211 -e221.
19. Dima D, Modabbernia A, Papachristou E, Doucet GE, Agartz I, Aghajani M, et al. (2022): Subcortical volumes across the lifespan: Data from 18,605 healthy individuals aged 3-90 years. Hum Brain Mapp 43:452 -469.
20. Pomponio R, Erus G, Habes M, Doshi J, Srinivasan D, Mamourian E, et al. (2020): Harmonization of large MRI datasets for the analysis of brain imaging patterns throughout the lifespan. Neuroimage 208:116450.
21. Fama R, Sullivan EV (2015): Thalamic structures and associated cognitive functions: Relations with age and aging. Neurosci Biobehav Rev 54:29 -37.
22. Hughes EJ, Bond J, Svrckova P, Makropoulos A, Ball G, Sharp DJ, et al. (2012): Regional changes in thalamic shape and volume with increasing age. Neuroimage 63:1134 -1142.
23. Choi EY, Tian L, Su JH, Radovan MT, Tourdias T, Tran TT, et al. (2022): Thalamic nuclei atrophy at high and heterogenous rates during cognitively unimpaired human aging. Neuroimage 262:119584.
24. Pfefferbaum A, Sullivan EV, Zahr NM, Pohl KM, Saranathan M (2023): Multi-atlas thalamic nuclei segmentation on standard T1-weighed MRI with application to normal aging. Hum Brain Mapp 44:612 -628.
25. Segal A, Parkes L, Aquino K, Kia SM, Wolfers T, Franke B, et al. (2023): Regional, circuit and network heterogeneity of brain abnormalities in psychiatric disorders. Nat Neurosci 26:1613 -1629.
26. Tetreault AM, Phan T, Orlando D, Lyu I, Kang H, Landman B, et al. (2020): Network localization of clinical, cognitive, and neuropsychiatric symptoms in Alzheimer ' s disease. Brain 143:1249 -1260.
27. Rutherford S, Barkema P, Tso IF, Sripada C, Beckmann CF, Ruhe HG, Marquand AF (2023): Evidence for embracing normative modeling. Elife 12:e85082.
28. Potvin O, Mouiha A, Dieumegarde L, Duchesne S, Alzheimer ' s Disease Neuroimaging Initiative (2016): Normative data for subcortical regional volumes over the lifetime of the adult human brain. Neuroimage 137:9 -20.
29. Frangou S, Modabbernia A, Williams SCR, Papachristou E, Doucet GE, Agartz I, et al. (2022): Cortical thickness across the lifespan: Data from 17,075 healthy individuals aged 3-90 years. Hum Brain Mapp 43:431 -451.
30. Iglesias JE, Insausti R, Lerma-Usabiaga G, Bocchetta M, Van Leemput K, Greve DN, et al. (2018): A probabilistic atlas of the human thalamic nuclei combining ex vivo MRI and histology. Neuroimage 183:314 -326.
31. Su JH, Thomas FT, Kasoff WS, Tourdias T, Choi EY, Rutt BK, Saranathan M (2019): Thalamus Optimized Multi Atlas Segmentation (THOMAS): Fast, fully automated segmentation of thalamic nuclei from structural MRI. Neuroimage 194:272 -282.
32. Vidal JP, Danet L, Péran P, Pariente J, Bach Cuadra M, Zahr NM, et al. (2024): Robust thalamic nuclei segmentation from T1-weighted MRI using polynomial intensity transformation. Brain Struct Funct 229:1087 -1101.
33. Bocchetta M, Iglesias JE, Neason M, Cash DM, Warren JD, Rohrer JD (2020): Thalamic nuclei in frontotemporal dementia: Mediodorsal nucleus involvement is universal but pulvinar atrophy is unique to C9orf72. Hum Brain Mapp 41:1006 -1016.
34. Zahr NM, Sullivan EV, Pohl KM, Pfefferbaum A, Saranathan M (2020): Sensitivity of ventrolateral posterior thalamic nucleus to back pain in alcoholism and CD4 nadir in HIV. Hum Brain Mapp 41:1351 -1361.
35. Mørch-Johnsen L, Jørgensen KN, Barth C, Nerland S, Bringslid IK, Wortinger LA, et al. (2023): Thalamic nuclei volumes in schizophrenia
22. and bipolar spectrum disorders -Associations with diagnosis and clinical characteristics. Schizophr Res 256:26 -35.
36. Dönmezler S, Sönmez D, Y ı lbas ¸ B, Öztürk H \_ I, \_ Iskender G, Kurt \_ I (2024): Thalamic nuclei volume differences in schizophrenia patients and healthy controls using probabilistic mapping: A comparative analysis. Schizophr Res 264:266 -271.
37. Thalhammer M, Schulz J, Scheulen F, Oubaggi MEM, Kirschner M, Kaiser S, et al. (2024): Distinct volume alterations of thalamic nuclei across the schizophrenia spectrum. Schizophr Bull 50:1208 -1222.
38. Perez-Rando M, Elvira UKA, García-Martí G, Gadea M, Aguilar EJ, Escarti MJ, et al. (2022): Alterations in the volume of thalamic nuclei in patients with schizophrenia and persistent auditory hallucinations. Neuroimage Clin 35:103070.
39. Williams B, Nguyen D, Vidal JP, Saranathan M (2024): Thalamic nuclei segmentation from T1-weighted MRI: Unifying and benchmarking state-of-the-art methods. Imaging Neurosci 2:1 -16.
40. Dubois B, von Arnim CAF, Burnie N, Bozeat S, Cummings J (2023): Biomarkers in Alzheimer ' s disease: Role in early and differential diagnosis and recognition of atypical variants. Alzheimers Res Ther 15:175.
41. Birnbaum R, Weinberger DR (2024): The genesis of schizophrenia: An origin story. Am J Psychiatry 181:482 -492.
42. Johnson WE, Li C, Rabinovic A (2007): Adjusting batch effects in microarray expression data using empirical Bayes methods. Biostatistics 8:118 -127.
43. Fortin J-P, Cullen N, Sheline YI, Taylor WD, Aselcioglu I, Cook PA, et al. (2018): Harmonization of cortical thickness measurements across scanners and sites. Neuroimage 167:104 -120.
44. Buckner RL, Head D, Parker J, Fotenos AF, Marcus D, Morris JC, Snyder AZ (2004): A uni /uniFB01 ed approach for morphometric and functional data analysis in young, old, and demented adults using automated atlasbased head size normalization: Reliability and validation against manual measurement of total intracranial volume. Neuroimage 23:724 -738.
45. Mathalon DH, Sullivan EV, Rawles JM, Pfefferbaum A (1993): Correction for head size in brain-imaging measurements. Psychiatry Res 50:121 -139.
46. Benjamini Y, Hochberg Y (1995): Controlling the false discovery rate: A practical and powerful approach to multiple testing. J R Stat Soc B 57:289 -300.
47. McGirr A, Nathan S, Ghahremani M, Gill S, Smith EE, Ismail Z (2022): Progression to dementia or reversion to normal cognition in mild cognitive impairment as a function of late-onset neuropsychiatric symptoms. Neurology 98:e2132 -e2139.
48. Busche MA, Hyman BT (2020): Synergy between amyloidb and tau in Alzheimer ' s disease. Nat Neurosci 23:1183 -1193.
49. Cho SH, Shin J-H, Jang H, Park S, Kim HJ, Kim SE, et al. (2018): Amyloid involvement in subcortical regions predicts cognitive decline. Eur J Nucl Med Mol Imaging 45:2368 -2376.
50. Aggleton JP, Pralus A, Nelson AJD, Hornberger M (2016): Thalamic pathology and memory loss in early Alzheimer ' s disease: Moving the focus from the medial temporal lobe to Papez circuit. Brain 139:1877 -1890.
51. Worker A, Berthert P, Lawrence AJ, Kia SM, Arango C, Dinga R, et al. (2023): Extreme deviations from the normative model reveal cortical heterogeneity and associations with negative symptom severity in /uniFB01 rst-episode psychosis from the OPTiMiSE and GAP studies. Transl Psychiatry 13:373.
52. Antoniades M, Haas SS, Modabbernia A, Bykowsky O, Frangou S, Borgwardt S, Schmidt A (2021): Personalized estimates of brain structural variability in individuals with early psychosis. Schizophr Bull 47:1029 -1038.
53. Lv J, Di Biase M, Cash RFH, Cocchi L, Cropley VL, Klauser P, et al. (2021): Individual deviations from normative models of brain structure in a large cross-sectional schizophrenia cohort. Mol Psychiatry 26:3512 -3523.
54. Huang AS, Rogers BP, Shef /uniFB01 eld JM, Jalbrzikowski ME, Anticevic A, Blackford JU, et al. (2020): Thalamic nuclei volumes in psychotic disorders and in youths with psychosis spectrum symptoms. Am J Psychiatry 177:1159 -1167.
55. Steullet P (2020): Thalamus-related anomalies as candidate mechanismbased biomarkers for psychosis. Schizophr Res 226:147 -157.

## Normative Models of Thalamic Nuclei

56. Lubben N, Ensink E, Coetzee GA, Labrie V (2021): The enigma and implications of brain hemispheric asymmetry in neurodegenerative diseases. Brain Commun 3:fcab211.
57. Tomer R, Slagter HA, Christian BT, Fox AS, King CR, Murali D, Davidson RJ (2013): Dopamine asymmetries predict orienting bias in healthy individuals. Cereb Cortex 23:2899 -2904.
58. Tiihonen J, Katila H, Pekkonen E, Jääskeläinen IP, Huotilainen M, Aronen HJ, et al. (1998): Reversal of cerebral asymmetry in schizophrenia measured with magnetoencephalography. Schizophr Res 30:209 -219.
59. Kuo F, Massoud TF (2022): Structural asymmetries in normal brain anatomy: A brief overview. Ann Anat 241:151894.
60. Kumar VJ, Beckmann CF, Schef /uniFB02 er K, Grodd W (2022): Relay and higher-order thalamic nuclei show an intertwined functional association with cortical-networks. Commun Biol 5:1187.
61. Georgescu IA, Popa D, Zagrean L (2020): The anatomical and functional heterogeneity of the mediodorsal thalamus. Brain Sci 10:624.
62. Barbas H, García-Cabezas MÁ., Zikopoulos B (2013): Frontal-thalamic circuits associated with language. Brain Lang 126:49 -61.
63. Pergola G, Danet L, Pitel A-L, Carlesimo GA, Segobin S, Pariente J, et al. (2018): The regulatory role of the human mediodorsal thalamus. Trends Cogn Sci 22:1011 -1025.
64. Huang AS, Kang K, Vandekar S, Rogers BP, Heckers S, Woodward ND (2024): Lifespan development of thalamic nuclei and characterizing thalamic nuclei abnormalities in schizophrenia using normative modeling. Neuropsychopharmacology 49:1518 -1527.
65. Munakata Y, Pfaf /uniFB02 y J (2004): Hebbian learning and development. Dev Sci 7:141 -148.
66. Carrera E, Tononi G (2014): Diaschisis: Past, present, future. Brain 137:2408 -2422.
67. Han S, Fang K, Zheng R, Li S, Zhou B, Sheng W, et al. (2023): Gray matter atrophy is constrained by normal structural brain network architecture in depression. Psychol Med 54:1318 -1328.
68. Petersen M, Nägele FL, Mayer C, Schell M, Rimmele DL, Petersen E, et al. (2022): Brain network architecture constrains age-related cortical thinning. Neuroimage 264:119721.
69. Chopra S, Segal A, Oldham S, Holmes A, Sabaroedin K, Orchard ER, et al. (2023): Network-based spreading of gray matter changes across different stages of psychosis. JAMA Psychiatry 80:1246 -1257.
70. Folstein MF, Folstein SE, McHugh PR (1975): ' Mini-mental state ' . A practical method for grading the cognitive state of patients for the clinician. J Psychiatr Res 12:189 -198.
71. Nasreddine ZS, Phillips NA, Bédirian V, Charbonneau S, Whitehead V, Collin I, et al. (2005): The Montreal Cognitive Assessment, MoCA: A brief screening tool for mild cognitive impairment. J Am Geriatr Soc 53:695 -699.
72. Tariciotti L, Casadei M, Honig LS, Teich AF, McKhann GM, Tosto G, Mayeux R (2018): Clinical experience with cerebrospinal /uniFB02 uid A b 42, total and phosphorylated tau in the evaluation of 1,016 individuals for suspected dementia. J Alzheimers Dis 65:1417 -1425.
73. Haddock G, McCarron J, Tarrier N, Faragher EB (1999): Scales to measure dimensions of hallucinations and delusions: The psychotic symptom rating scales (PSYRATS). Psychol Med 29:879 -889.
74. Andreasen NC (1989): The Scale for the Assessment of Negative Symptoms (SANS): Conceptual and theoretical foundations. Br J Psychiatry Suppl(7):49 -58.
75. Andreasen NC, Arndt S, Miller D, Flaum M, Nopoulos P (1995): Correlational studies of the Scale for the Assessment of Negative Symptoms and the Scale for the Assessment of Positive Symptoms: An overview and update. Psychopathology 28:7 -17.
76. Kay SR, Fiszbein A, Opler LA (1987): The positive and negative syndrome scale (PANSS) for schizophrenia. Schizophr Bull 13:261 -276.
