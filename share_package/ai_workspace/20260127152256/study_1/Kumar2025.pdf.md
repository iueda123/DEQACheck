## RESEARCH ARTICLE

![Image](./Kumar2025_artifacts/image_000000_413a35e1f40eb65c4bbd208dd7364e54b13161714e6d45ae851dc6842470c6c5.png)

![Image](./Kumar2025_artifacts/image_000001_f47711d7feba01b541a674cd699dced9c194341362898cec2f6c8d3ad4d079e4.png)

## Analyzing heterogeneity in Alzheimer disease using multimodal normative modeling on imaging-based ATN biomarkers

Sayantan Kumar 1,2,3

TomEarnest 3

Braden Yang 3

Deydeep Kothapalli 3

AndrewJ. Aschenbrenner 4

- [x] Jason Hassenstab 4

Chengie Xiong 2

Beau Ances 4

John Morris 4

Tammie L. S. Benzinger 3

Brian A. Gordon 3

Philip Payne 1,2

Aristeidis Sotiras 2,3

for the Alzheimer's Disease Neuroimaging

Initiative

![Image](./Kumar2025_artifacts/image_000002_b11994b30ec20b2c54ed01fe16182a0c5da5f1f043f4a516025ad90003643ee9.png)

This is an open access article under the terms of the Creative Commons Attribution-NonCommercial License, which permits use, distribution and reproduction in any medium, provided the original work is properly cited and is not used for commercial purposes. ©2025TheAuthor(s). Alzheimer's &amp; Dementia published by Wiley Periodicals LLC on behalf of Alzheimer's Association.

## 1 BACKGROUND

Alzheimer disease (AD) is the leading cause of dementia, characterized by cognitive and functional impairments that disrupt daily activities. 1,2 AD is highly heterogeneous, exhibiting considerable variability in clinical manifestations, cognitive decline, disease progression, and neuropathological changes, even within specific diagnostic categories. 3 However, traditional statistical approaches in AD research often overlook this heterogeneity, relying on case-control designs and group averages, effectively treating AD patients as a homogenous group. To progress toward precision medicine in AD, it is essential to move beyond the 'average AD patient' approach and the assumption that AD affects all patients in the same way, and characterize disease abnormalities at the individual-level. 4

Data-driven clustering methods have been the predominant approach for exploring heterogeneity in AD. 5 Normative modeling is an emerging statistical technique that differs from clustering by focusing on subject-level variation instead of group averages. 6-8 Typically, normative analysis in AD research models the relationship between brain measures and covariates (e.g., age, sex) using univariate Bayesian regression models 8,9 or w-scores, 10,11 applied to a reference group of healthy controls. The trained normative models are subsequently used to estimate how every AD individual deviates from the norm, resulting in a map of individual-level variability. 8,9 However, normative modeling approaches typically construct separate regression models independently for each brain region, ignoring the multivariate nature of the data. To address this limitation, deep learning approaches based on autoencoders have been used as normative models. These models effectively capture the complex nonlinear interactions between brain regions. However, these interactions are typically restricted to same modality measurements. 12-14

Indeed, most previous studies employing normative models have primarily relied on single modality neuroimaging data to characterize heterogeneity in neuropsychiatric 6,7,15 and neurodegenerative disorders, including AD. 8,16 This is particularly limiting in the case of AD, which is a multi-factorial disease, involving multiple pathological processes that interact and contribute to disease progression. To accurately characterize AD, multi-modal imaging that can quantify biomarker pathology-including amyloid deposition, pathologic tau, and neurodegeneration-is essential. Together, these biomarkers compose the National Institute on Aging and Alzheimer's Association research framework that helps with defining AD as a biological construct and facilitates a more comprehensive understanding of individual differences in cognitive performance and clinical progression. 17-21

- Heterogeneity in spatial patterns of gray matter atrophy, amyloid, and tau burden.
- Higher within-group heterogeneity for AD patients at advanced dementia stages.
- Patient-specific metric summarized extent of neurodegeneration and neuropathology.
- Metric is a marker of poor brain health and can monitor risk of disease progression.

However, despite recent progress on developing deep learning models for normative modeling across multiple modalities, 22-27 these efforts have primarily been methodological and have not investigated AD heterogeneity by taking into account core AD biomarkers (i.e., amyloid and tau). 28

In this study, we aimed to identify individual patterns of neuroanatomical and neuropathological variation in the brains of individuals with AD using a deep learning-based normative modeling framework across amyloid-tau-neurodegeneration (ATN) 29 imaging biomarker data from Alzheimer's Disease Neuroimaging Initiative (ADNI). Accordingly, we trained our previously validated normative modeling framework, 22,24 which is based on multimodal variational autoencoders (mmVAE), on data from a reference control group [i.e., amyloid-negative cognitively unimpaired (CU) subjects]. We subsequently used the trained model to estimate the extent to which individuals spanning the AD spectrum (ADS) deviate from the normative distribution. Our main objectives can be summarized as follows: (i) assess the extent of neuroanatomical and neuropathological variability between individual patients based on overlapping or distinct patterns of abnormal deviations, (ii) quantify intra-group heterogeneity within ADS clinical groups based on differences in between-participant dissimilarity in abnormal deviations across ATN, (iii) estimate a disease severity index (DSI) for each ADS individual that can capture both the spatial extent of abnormality and the magnitude of regional abnormal deviations across ATN, and (iv) examine whether the DSI is related to severity of dementia, impaired cognition, and risk of disease progression. The results were replicated in an independent dataset, part of the Charles F. and Joanne Knight Alzheimer's Disease Research Center (ADRC) dataset at Washington University in St. Louis.

## 2 MATERIALS AND METHODS

## 2.1 Participants

We constructed a discovery dataset consisting of individuals from ADNI and a replication dataset consisting of individuals from the Charles F. and Joanne Knight ADRC dataset at Washington University in St. Louis. For both datasets, participants were required to have T1weighted magnetic resonance imaging (MRI), as well as amyloid and tau positron emission tomography (PET) imaging, completed within 1 year of one another. Note that these are cross-sectional cohorts, and we included only the first visit for each individual for which all modalities were available. For both datasets independently, we selected

two groups based on amyloid status (Section 2.2.3): (1) a reference control group of amyloid-negative CU [i.e., Clinical Dementia Rating (CDR) = 0] individuals, which was used to train the deep learning-based normative model; and (2) a target disease group of amyloid positive individuals across the ADS. A total of 434 amyloid-negative CU participants were included in the reference control group (ADNI-CU), and 231 amyloid-positive individuals across the ADS were included in the target disease group (ADNI-ADS) from ADNI (Figure 1A). For ADRC, the reference group (ADRC-CU) consisted of 301 amyloid-negative CUindividuals, while the disease group (ADRC-ADS) consisted of 129 amyloid-positive individuals on the ADS (Figure 1B).

ADNI-ADS individuals were assigned into three diagnostic groups based on CDR: CDR = 0orpreclinical AD ( n = 121), CDR = 0.5 ( n = 80), and CDR ≥ 1 ( n = 30) (Figure 1A). Similarly, the number of ADRC-ADS individuals in the corresponding groups were 98, 24, and 7, respectively (Figure 1B). The ADNI-CU group was further divided into a training set for model training (ADNI-CU-train; n = 326), a holdout validation set (ADNI-CU-holdout; n = 65), and a test set (ADNI-CU-test; n = 43) at a ratio of 75:15:10 (Figure 1A). The validation set was used to standardize deviations of ADNI-ADS and calculate Z -scores relative to ADNI-CU. The test sets served as a baseline control group to compare statistics of ADS individuals against amyloid-negative CU participants. Similarly, the ADRC-CU group was also divided into a training set for transfer learning on the replication dataset (ADRC-CU-tl; n = 225), a holdout validation set (ADRC-CU-holdout; n = 45), and a test set (ADRC-CU-test; n = 31) (Figure 1B).

## 2.2 T1-weighted MRI imaging

## 2.2.1 Image acquisition

ADNI participants included in our analysis underwent T1-weighted MRIimagingusing3TMRIscanners(detailsareavailableonlinehttps:// adni.loni.usc.edu/methods/mri-tool/mri-analysis/). Knight ADRC participants underwent T1-weighted MRI imaging using the Siemens Biograph mMR 3T scanner. Detailed information about MRI image acquisition for the Knight ADRC dataset can be found in the Supplementary Methods (SM1.1).

## 2.2.2 Image pre-processing

The T1-weighted sequences from ADNI and Knight ADRC were preprocessed using FreeSurfer versions 6 and 5.3, respectively. The cortical surface of each hemisphere was parcellated according to the Desikan-Killiany atlas 30 and anatomical region of interest (ROI) measures were obtained via a whole-brain segmentation procedure (Aseg atlas). 31 The final data included in our analyses (both ADNI and Knight ADRC) included pre-processed regional grey matter volumes of 66 cortical ROIs (33 per hemisphere) and 24 subcortical ROIs for each participant. All ROI volumes were normalized by the intracranial volume (ICV). Detailed MRI pre-processing protocols for ADNI are available

## RESEARCHINCONTEXT

1. Systematic review : PubMed and Google Scholar were used to identify normative modeling works, which aimed to conceptualize Alzheimer disease (AD) as deviations from healthy controls and parse disease heterogeneity. Prior normative modeling approaches used single neuroimaging modality to investigate AD heterogeneity.
2. Interpretation : We used normative modeling to assess AD heterogeneity across multi-modal imaging modalities by estimating regional statistically significant neurodegenerative and neuropathological deviations at the individual level. We developed a patient-specific metric of brain health, summarizing the extent and severity of neurodegeneration and neuropathology. Together, the subject-specific metric and regional maps of abnormal deviations across multiple modalities have the potential to assist in clinical decision making and monitor patient response to anti-amyloid treatments.
3. Future directions : Future normative modeling studies should (i) incorporate longitudinal cohorts to better understand disease progression and heterogeneity and (ii) include a more diverse population from various geographical regions to enhance sample size and improve model training.

online (https://adni.loni.usc.edu/methods/mri-tool/mri-analysis/). Further information about MRI processing protocols for the Knight ADRC dataset can be found in the Supplementary Methods (SM1.2). As a summary metric of MRI atrophy, hippocampal volume was calculated independently for ADNI and Knight ADRC by averaging hippocampal volumes across the left and right hemispheres and subsequently normalized by the ICV.

## 2.3 Amyloid and tau PET imaging

## 2.3.1 Image acquisition

ADNI participants underwent amyloid-PET imaging with either [ 18 F]Florbetapir (FBP) or [ 18 F]-Florbetaben (FBB) tracers and tau-PET imaging with [ 18 F]-Flortaucipir (FTP). Details regarding PET acquisition for ADNI are available online (https://adni.loni.usc.edu/methods/ pet-analysis-method/pet-analysis/). Knight ADRC participants underwent amyloid-PET imaging with either FBP or [ 11 C]-Pittsburgh Compound B (PiB). Tau-PET imaging was performed using FTP. Further information about PET image acquisition for the Knight ADRC can be found in the Supplementary Methods (SM2.1). To avoid harmonization issues due to multiple tracers, we only included data collected using FBPin our analyses for both ADNI and Knight ADRC.

FIGURE 1 Flow chart of ADNI (A) and Knight ADRC (B) study participants. ADNI, Alzheimer's Disease Neuroimaging Initiative; ADRC, Alzheimer's Disease Research Center.

![Image](./Kumar2025_artifacts/image_000003_9f008c70125cb99d06513ef911d4517a9f83561448d693c10d7fd3583a2c2fb4.png)

![Image](./Kumar2025_artifacts/image_000004_dac8227bd295bb7835f1a60c2f815ac292a1116b08de1a2a9255840528039994.png)

## 2.3.2 Image pre-processing

ADNI PET images (FBP and FTP) were registered to the nearest T1-weighted image, which was subsequently processed with FreeSurfer version 6. Detailed PET pre-processing protocols for ADNI can be found available online (https://adni.loni.usc.edu/methods/petanalysis-method/pet-analysis/). All PET images from Knight ADRC (FBP and FTP) were processed using the PET Unified Pipeline (https:// github.com/ysu001/PUP). 32,33 Further information about PET processing protocols for Knight ADRC can be found in the Supplementary Methods (SM2.2). Similar to MRI ROI volumes, the final amyloidFBP and tau-FTP data included in our analyses from both ADNI and Knight ADRC consisted of regional standardized uptake value ratio (SUVR) values for 66 cortical ROIs (33 per hemisphere) and 24 subcortical ROIs for each participant. Regional SUVR values in ADNI for amyloid-FBP and tau-FTP were normalized relative to the whole cerebellum and inferior cerebellar gray matter reference region respectively. For Knight ADRC, all regional SUVRs for both amyloid-FBP and tau-FTP were calculated with cerebellum cortex as the reference region.

## 2.3.3 Amyloid burden and positivity

A summary estimate of global amyloid (FBP) burden in ADNI was calculated as the average SUVR within cortical meta-ROI (spanning frontal, anterior/posterior cingulate, lateral parietal, and lateral temporal regions), which was normalized with a whole cerebellum reference region. The summary estimate was converted to Centiloid using calibrated equations developed by ADNI. 34 ADNI individuals with metaROI SUVR uptake greater than 1.11 cutoff were labeled as amyloid positive, following established cutoff procedures recommended within ADNIdocumentation. 35-38

For Knight ADRC, an estimate of total cortical amyloid burden was derived by computing the SUVR of a meta-ROI consisting of lateral and medial orbitofrontal, middle and superior temporal, superior frontal, rostral middle frontal, and precuneus ROIs from both hemispheres. The meta-ROI SUVR was normalized using the cerebellar cortex reference region. The uptake in the cortical meta-ROI was converted to Centiloid using equations developed by previous literature. 39 KnightADRC individuals with meta-ROI SUVR greater than the 1.24 cutoff were considered amyloid positive, in line with the established literature. 18,40

## 2.3.4 Tau index

Following previous work, 10,18 we calculated a tau index (TI) as a measure of overall tau load, independently for both ADNI and Knight ADRC. Specifically, the TI was calculated as the mean regional SUVR for the four regions previously identified to characterize early tau accumulation (i.e., entorhinal cortex, amygdala, lateral occipital cortex, and inferior temporal cortex).

## 2.4 Clinical and cognitive assessments

Clinical and cognitive assessments for both ADNI and Knight ADRC were only included if they occurred within one year of MRI imaging. Participants were assessed for dementia using the CDR Scale. 41 Cognitive performance was quantified by computing neuropsychological composites for memory, executive functioning, and language, independently for ADNI and Knight ADRC (SM3).

## 2.5 Multimodal normative modeling

## 2.5.1 Model training

In this study, we used a previously validated deep learning-based normative modeling framework. 12,22,24 This framework is based on a multimodal variational autoencoder (mmVAE), which takes as input cross-sectional ATN biomarker data including regional gray matter volumes, as well as amyloid FBP and tau FTP SUVR values (Figure S1). mmVAE had separate encoders for each modality to learn a shared latent space, which is a joint distribution across the different modalities. The shared information in the aggregated latent space was fed through modality-specific decoders to reconstruct each modality (Figure S1). Details of the mmVAE architecture are provided in Supplementary Methods (SM4.1, SM4.2).

Initially, mmVAE was trained using the ADNI-CU-train set, where the input matrix had dimensions of 326 × 270 (subjects x ROIs, with 90 ROIs for each modality). During training, mmVAE learns to reconstruct the multimodal input data as closely as possible to the original. The joint latent distribution allows the model to learn the healthy brain patterns across all modalities. These include the variability in MRI estimates, regional noise, or off-target binding for amyloid FBP and age-associated tau FTP accumulation within healthy individuals. mmVAE was conditioned on the age and sex of participants to remove the effect of covariates (see Supplementary Methods SM4.3). For replication in the Knight ADRC dataset, mmVAE pretrained on ADNI-CU-train underwent fine-tuning on ADRC-CU-tl through transfer learning (Section 2.1). Further information about model training and hyperparameter details are available in Supplementary Methods (SM4.3).

## 2.5.2 Calculating regional deviations for each modality

The main idea of the normative approach is that mmVAE learns only to reconstruct the data of CU individuals. Since individuals on the ADS will differ from CU individuals due to the AD pathology, mmVAE will be less precise in reconstructing their data. As a result, the difference between the reconstructed and input data will be larger in the ADS cohort compared to CU individuals. For each participant in ADNI-ADS and ADRC-ADS, deviations for each region and for each modality can be calculated as the squared error between input and reconstructed data (Figure S1).

## 2.5.3 Normalizing deviations into Z -scores

Considering the complexity of the brain data, we expect that mmVAE might not fully capture normal variations in healthy subjects, leading to some reconstruction error. Hence, it is important to standardize ADNI-ADS deviations using ADNI-CU-holdout set (see Section 2.1) as a reference for the CU population. We utilized the mean and variance (calculated independently for each region) from ADNI-CU-holdout to normalize regional deviations in the ADNI-ADS cohort across each modality. Following fine-tuning of mmVAE on ADRC-CU-tl, a similar process was applied using ADRC-CU-holdout to normalize deviations in ADRC-ADS. This step generated regional, modality-specific Z -score deviations for each individual in the ADS cohort relative to the normative range of the respective reference control group (Figure S1).

## 2.6 Statistical analysis

## 2.6.1 Regional abnormal deviations across ATN biomarkers

For MRI gray matter volumes, regional Z -scores below -1.96 (bottom 2.5% of the normative distribution) were labeled as abnormal (statistically significant) deviations. As followed in previous normative modeling literature, 8,16 the lower bound was used because we were interested in gray matter loss (MRI atrophy) associated with neurodegeneration. Similarly regional deviations in amyloid-FBP and tau-FTP SUVR were identified as abnormal if their Z-scores were above 1.96 (top 2.5% of the normative distribution). This upper bound was chosen to focus on increased amyloid/tau SUVR uptake (high amyloid/tau burden) linked to pathological accumulation. 8,16 For each ADS individual, we created a binary thresholded abnormality map, marking regions with abnormal deviations as 1 and others as 0. These abnormality maps were calculated for each modality (MRI, amyloid, or tau) with 90 regions each and also aggregated across all modalities combined (270 regions).

![Image](./Kumar2025_artifacts/image_000005_833d42bbeadf51a751abe462b3dc99a7079e7dbe1561422b3e671dec8d0cb2d9.png)

## 2.6.2 Group differences of regional abnormal deviations across ATN imaging biomarkers

We examined the magnitude of abnormal deviations in each region between amyloid negative CU individuals (CU-test; Section 2.1) and clinical groups along the ADS: (i) CDR = 0(preclinical AD), (ii) CDR = 0.5 (very mild dementia), and (iii) CDR ≥ 1 (mild or more severe dementia). Our aim was to validate the derived regional abnormal deviations by examine examining whether these deviations showed increased group differences across progressive CDR stages. We quantified group differences using Cohen's d-statistic effect size, calculated separately for each modality. A higher effect size when comparing regional MRI volumes indicated lower gray matter volume (more atrophy). Similarly, a higher effect size when comparing amyloid or tau uptake indicated elevated SUVR uptake (higher amyloid and tau loads) compared to the amyloid-negative CU group. We repeated these group comparisons for both ADNI and Knight ADRC independently.

## 2.6.3 Analysis of spatial distribution of abnormal deviations across ATN imaging biomarkers

The group comparisons between each of the ADS groups and the amyloid-negative CU group (Section 2.6.2) effectively assumed that everyclinical group is homogenousintheregionalpatternsofabnormal deviations across all the modalities. To better understand disease heterogeneity, we also aimed to assess the variability in spatial patterns of neurodegeneration (MRI atrophy) and neuropathology (amyloid and tau deposition) across clinical groups. Toward this end, we computed the proportion of abnormal deviations (fraction of individuals with abnormal deviations; Section 2.6.1) separately for each region, modality and clinical group. The regional proportion of abnormal deviations was calculated for each of the ADS clinical groups and a group of amyloid-negative CU individuals (CU-test; Section 2.1) independently for both the ADNI and Knight ADRC datasets.

## 2.6.4 Intra-group heterogeneity within ADS groups

Next, we aimed to quantify the intra-group heterogeneity across ADS clinical groups and assess whether this increases across progressive dementia stages. We used hamming distance to measure the dissimilarity in binary-thresholded abnormality maps (Section 2.6.1) between every pair of ADS individuals, with higher distance indicating more dissimilarity. Hamming distances were estimated for both modality-specific abnormality maps (hamming\_mri, hamming\_amyloid, and hamming\_tau) and abnormality maps aggregated across all modalities (hamming\_all). Median hamming distances were compared across ADS groups and an amyloid-negative CU group (CU-test; Section 2.1). Distribution of hamming distances were visualized using kernel density estimation (KDE) plots across ADS groups, reflecting the extent of intra-group heterogeneity. The intra-group heterogeneity analysis was performed independently for both ADNI and Knight ADRC cohorts.

## 2.6.5 DSI across ADS groups

Our objective was to design a DSI for each ADS individual, which can capture both the spatial extent and magnitude of regional abnormal deviations across multiple modalities into a single, subject-specific metric. DSI was calculated separately for each modality (DSI\_mri, DSI\_amyloid, DSI\_tau) and also aggregated across all modalities (DSI\_all). Specifically, every individual's DSI was calculated by (i) first performing an inner product between the binary thresholded abnormality map (Section 2.6.1) and the regional deviation vector; and (ii) then normalizing the inner product by the total number of regions ( n R = 90 for DSI\_mri, DSI\_amyloid, DSI\_tau, and n R = 270 for DSI\_all).

DSI\_all represents a personalized measure of brain health that accounts for individual variability in gray matter volume, and amyloid and tau deposition, rather than relying on average group relationships. To demonstrate this, we first compared DSI values between the different ADS groups and a group of amyloid-negative CU individuals (CU-test; Section 2.1). This allowed us to examine the association between increasing DSI and progressive dementia stages (high CDR). False discovery rate (FDR) -corrected post hoc Tukey comparisons were used for pairwise group differences.

## 2.6.6 Association between DSI and cognitive performance

We then examined the associations between DSI values (DSI\_mri, DSI\_amyloid, DSI\_tau, and DSI\_all) and the three neuropsychological composites: memory, executive functioning, and language (Section 2.4, SM3) in both ADNI-ADS and ADRC-ADS cohorts. The associations were estimated using linear regression, adjusted for age and sex. Additionally, Pearson correlation coefficient was used to measure the pairwise correlation between each of the DSI categories and the composites.

To highlight the added value of DSI measures compared to traditional ATN imaging markers (e.g., ICV-adjusted hippocampal volume, amyloid Centiloid, and TI), we conducted additional analyses examining their associations with neuropsychological composites such as memory,executive functioning, and language. Furthermore, we applied Steiger's Z -test to determine whether the correlations between DSI measures and cognitive performance significantly differed from those between classical ATN imaging markers and cognitive measures.

## 2.6.7 Relationship between DSI and CDR progression

Lastly, we examined associations of DSI\_all and clinical progression in both ADNI-ADS and ADRC-ADS cohorts. For this analysis, we included subjects with follow-up CDR status data, who were CDR &lt; 1 at their baseline visit with all three modalities present. We analyzed the relationship between DSI\_all and CDR progression using survival analysis, adjusting for age and sex. The event of interest was progression to

TABLE 1 Descriptive statistics for the ADNI-ADS and ADRC-ADS datasets.

| Parameter        | ADNI-ADS   | ADRC-ADS     | p -value    |
|------------------|------------|--------------|-------------|
| N                | 231        | 129          | -           |
| Sex, male:female | 108:123    | 48:81        | 0.035*      |
| Age (mean ± SD)  | 73.6 ± 6.9 | 71.5 ± 8.3   | 0.006**     |
| CDR(0/0.5/ ≥ 1)  | 121/80/30  | 98/24/7      | < 0.001***  |
| MMSE(mean ± SD)  | 24.5 ± 3.2 | 26.5 + / 3.7 | < 0.001 *** |

Note : Statistical differences were assessed using two-sided ANOVA (continuous variables) and chi-squared tests (categorical. variables). Significant p -values are highlighted in bold with *0.01 &lt; p &lt; 0.05, **0.005 &lt; p &lt; 0.01, *** p &lt; 0.001.

Abbreviations: ADNI, Alzheimer's Disease Neuroimaging Initiative; ADRC, Alzheimer's Disease Research Center; ADS, Alzheimer's disease spectrum; ANOVA,analysisofvariance;CDR = Clinical Dementia Rating; MMSE, MiniMental State Examination; SD, standard deviation.

CDR ≥ 1. A Kaplan-Meier plot was used to illustrate the impact of the 4 DSI\_all quantiles on disease progression risk. Log-rank tests estimated pairwise differences in progression risk among the DSI\_all quantiles. Post-hoc comparisons were adjusted for multiple comparisons using FDR. 42

## 2.6.8 Code availability and visualizations

All analyses were performed using Python 3.7. All visualizations of the brain atlases for the effect size maps (Section 2.5.2) and proportion of abnormal deviation maps (Section 2.5.3) were visualized using the python package ggseg. 43 Hamming distance distributions at group level were visualized using KDE plots. Code for the project will be made publicly available upon acceptance.

## 3 RESULTS

## 3.1 Dataset characteristics

Sample characteristics for the ADNI-ADS and ADRC-ADS cohorts are showninTable1.TheADNI-ADScohortwasolder( p = 0.035), while the ADRC-ADS cohort had more females ( p = 0.006). ADRC-ADS showed less memory impairment, indicated by higher MMSE scores ( p &lt; 0.001) and a higher proportion of individuals with CDR = 0 or preclinical AD ( p &lt; 0.001).

Samplecharacteristics for the ADNI-CU and ADRC-CU datasets are provided in Table S1. ADNI-CU participants were older ( p &lt; 0.001), whereas there were more females in ADRC-CU ( p = 0.026). ADNICU participants had slightly lower MMSE scores than ADRC-CU, but this difference was not statistically significant ( p = 0.067). CU participants in ADNI and ADRC had lower age and higher MMSE scores compared to their ADS counterparts (i.e., ADNI-CU vs. ADNIADS; ADRC-CU vs. ADRC-ADS), with all differences being statistically significant ( p &lt; 0.001).

## 3.2 More severe dementia was associated with pronounced regional atrophy and elevated regional amyloid and tau burden in ADS patients

## 3.2.1 Discovery dataset-ADNI

Region-level (total of 90 regions-FDR corrected) pairwise group comparisons with amyloid-negative CU individuals (CU-test) provided evidence that gray matter volumes were lower in 56 regions in mild or more severe dementia, in 22 regions in very mild dementia, and no regions in preclinical AD (Table S2). Maximum group differences in atrophy were observed in the temporal, parietal, and hippocampal regions, and to a lesser extent in the frontal, occipital, and amygdala regions (Figure 2A). Expectedly, regional-level pairwise group comparisons with amyloid-negative CU individuals revealed higher amyloid burden in 84 regions in mild or severe dementia, in 75 regions in very mild dementia, and 85 regions in preclinical AD (Table S2). Higher effect sizes for amyloid burden were mostly observed in the medial orbitofrontal, precuneus, temporal, and frontal pole regions. Similarly, pairwise group comparisons with CU individuals revealed increased tau deposition in 80 regions in mild or severe dementia, in 62 regions in very mild dementia, and no regions in preclinical AD (Table S2). Regions with high effect sizes for tau burden included the temporal, frontal, precuneus, parietal and hippocampal regions (Figure 2A).

## 3.2.2 Replication dataset-Knight ADRC

Wefound a notable similarity between the effect size maps estimated in ADRC-ADS and in ADNI-ADS (Figure 2B). Similar to ADNI-ADS, statistically significant volumetric differences in ADRC-ADS were mainly seen in the temporal, parietal, and hippocampal regions. More regions showed pronounced atrophy in mild or severe dementia ( n r = 50) compared to very mild dementia ( n r = 25), with no abnormal regions for preclinical AD. Elevated amyloid burden was observed in more regions for mild or severe dementia ( n r = 81) compared to very mild dementia ( n r = 77), with 82 abnormal regions for preclinical AD (Table S2). Greater effect sizes were observed in the frontal and temporal regions (Figure 2B). Similarly, statistically significant group differences in regional tau deposition were found across various dementia severity levels (mild or severe dementia, n r = 74; very mild dementia, n r = 52; and preclinical AD, n r = 0), with greater effect sizes observed in the temporal and hippocampal regions (Table S2, Figure 2B).

## 3.3 ADS individuals with more severe dementia have a higher proportion of abnormal deviations in regional atrophy, amyloid, and tau burden

## 3.3.1 Discovery dataset-ADNI

The proportion of abnormal deviations defined within each clinical group differed in regional patterns between the mild or more severe

FIGURE 2 Brain atlas maps (Desikan-Killiany atlas for 66 cortical regions and Aseg atlas for 24 subcortical regions) showing the pairwise group differences in magnitude of deviations at each region between the amyloid-negative CU group and each of the CDR groups in ADNI (A) and Knight ADRC (B). The figures from left to right indicate the brain maps corresponding to MRI, amyloid, and tau, respectively. The color bar represents the effect size (Cohen's d statistic). Effect sizes of d = 0.2, d = 0.5, and d = 0.8 are typically categorized as small, medium, and large, respectively. Gray regions represent the regions with no statistically significant deviations after FDR correction. ADNI, Alzheimer's Disease Neuroimaging Initiative; ADRC, Alzheimer's Disease Research Center; CDR, Clinical Dementia Rating scale; CU, cognitively unimpaired; FDR, false discovery rate; MRI, magnetic resonance imaging.

![Image](./Kumar2025_artifacts/image_000006_6df37c27026cc0a82b606eca3871478f8552d8ac1eaecad63630df2bbe158363.png)

![Image](./Kumar2025_artifacts/image_000007_352ec5c538c162207117d7816f1ea5aa76631ad009c171e487276fd8b0bbdfaf.png)

dementia, the very mild dementia, the preclinical AD, and the controls group (Figure 3A). As far as regional gray matter volumes are concerned, the highest proportion of abnormal deviations was observed in hippocampal regions: 47% in the mild or more severe dementia group, 25% in the very mild dementia group, 6% in the preclinical AD group, and 3% in the amyloid-negative CU group. Regarding regional amyloid burden, the highest proportion of abnormal deviations was observed for precuneus and frontal pole cortices: 100% in the mild or more severe dementia group, 87% in the very mild dementia group, 71% in thepreclinical AD group, and 5% in the CU group. Lastly, the proportion of abnormal deviations in tau deposition was observed in hippocampal and entorhinal regions: 84% in the mild or more severe to severe dementia group, 65% in the very mild dementia group, 24% in the preclinical AD group, and 14% in the CU group. Overall, a higher pro- portion of abnormal deviations was observed for regional amyloid and tau burden than gray matter volume. This trend was consistent across the dementia stages (Figure 3A).

## 3.3.2 Replication dataset-Knight ADRC

In line with the ADNI-ADS results, we observed a consistent pattern of higher proportion of regional abnormal deviations with increased dementia severity (Figure 3B). Additionally, we observed similar regional patterns of abnormal deviations across all three modalities. Specifically, the highest proportion of abnormal deviations was observed for the same regions in both datasets, that is, hippocampal and temporal regions for MRI, frontal regions and precuneus for

![Image](./Kumar2025_artifacts/image_000008_9d4f1cf9548f6b0c6e41e6f54eebf31d27074ba3f888e38139be0a0f43529bec.png)

FIGURE 3 Brain atlas maps (Desikan-Killiany atlas for 66 cortical regions and Aseg atlas for 24 subcortical regions) showing the proportion of abnormal deviations for each region in ADNI (A) and Knight ADRC (B). The figures from left to right indicate the brain maps corresponding to MRI, amyloid, and tau, respectively. The color bar represents the proportion of abnormal deviations of each region from 0% to 100%. Gray represents that no participants have abnormal deviations for that region. ADNI, Alzheimer's Disease Neuroimaging Initiative; ADRC, Alzheimer's Disease Research Center; MRI, magnetic resonance imaging.

![Image](./Kumar2025_artifacts/image_000009_7d31efad3493d984d36928455ae2f0e0bae3bf7f4b6042dfbfe6735324de3765.png)

amyloid, and temporal and parietal regions for tau (Figure 3B) However, a lower proportion or regional abnormal deviations was observed for the mild or more severe dementia group in ADRC-ADS compared to the corresponding group in ADNI-ADS. This is likely due to the smaller sample size for this group ( n = 7) in ADRC-ADS (Figure 3B).

## 3.4 ADS individuals with more severe dementia are more heterogenous compared to individuals with less dementia

## 3.4.1 Discovery dataset-ADNI

The distribution of Hamming distance calculated for all modalities (hamming\_all) showed greater within-group heterogeneity (dissimi- larity) for ADNI-ADS individuals at progressive stages of dementia (Figure 4A). The median Hamming distance (hamming\_all) significantly differed between groups overall ( p &lt; 0.001). Pairwise comparisons in median hamming distance (Tukey post-hoc) were all significant ( p &lt; 0.001) except between the very mild dementia and the mild or more severe dementia group. Specifically, the Hamming distance was the highest in individuals with mild or more severe dementia [median 62, interquartile range (IQR) 39, 95% confidence interval (95% CI) 60.3-63.8], followed by the very mild dementia group (median 56, IQR 34, 95% CI 58.1-59.3) and the preclinical AD (CDR = 0) group (median 47, IQR 30, 95% CI 49.2-50.1) (Figure 4A). The lowest Hamming distance was observed in the CU group (ADNI-CU-test; median 5, IQR 6, 95% CI 8.4-10.7). For Hamming distance variants calculated using a single modality, we observed the same pattern of higher dissimilarity for ADS patients at progressive stages of dementia (Figure S2A).

![Image](./Kumar2025_artifacts/image_000010_42b046e3da082f57ca769fd0dc143ef78793220e7feb4e89bc9650b09c433496.png)

FIGURE 4 Hammingdistance density (KDE plot) which illustrates the spread of dissimilarity in abnormality patterns (calculated by the Hammingdistance for all modalities or hamming\_all; see Section 2.5.4) within each CDR group for ADNI (A) and Knight ADRC (B). Higher hamming distance values indicated intra-group more heterogeneity in abnormality patterns. ADNI, Alzheimer's Disease Neuroimaging Initiative; ADRC, Alzheimer's Disease Research Center; CDR, Clinical Dementia Rating scale; KDE, kernel density estimation.

![Image](./Kumar2025_artifacts/image_000011_3532acadaa8d1b8c867257147301c665d1b297179e939642c1028efa8eb159e6.png)

![Image](./Kumar2025_artifacts/image_000012_3525cd50a248b3a7bce704e090f98805ef9140a766b40f44524a0a480d22be28.png)

Within each clinical group, MRI showed the highest within-group heterogeneity in spatial patterns of abnormal deviations, followed by tau and amyloid (Figure S2A).

## 3.4.2 Replication dataset-Knight ADRC

Consistent with ADNI-ADS results, we observed greater withingroup dissimilarity for ADRC-ADS individuals at progressive stages of dementia (Figure 4B). Similar to ADNI, the median Hamming distance (hamming\_all) significantly differed between groups overall ( p &lt; 0.001). Pairwise comparisons in median hamming distance (Tukey post-hoc) were all significant ( p &lt; 0.001) except between the very mild dementia and the mild or more severe dementia group. Within-group dissimilarity was higher for the mild or more severe (median 39, IQR 40, 95% CI 28.8-43.5) and the very mild dementia (median 30, IQR 33, 95% CI

30.7-34.5)groupscomparedtopreclinicalAD(median14,IQR20,95% CI 21.6-22.9) and the CU groups (median 4, IQR 8, 95% CI 6.9-8.5). Weobserved the same trend when examining Hamming distances calculated for each modality separately (hamming\_mri, hamming\_amyloid, and hamming\_tau). Lastly, similarly to ADNI-ADS results, MRI exhibited the highest within-group dissimilarity in spatial patterns of abnormal deviations compared to amyloid and tau (Figure S2B).

## 3.5 Case studies to examine inter-patient variability in regional patterns of abnormal deviations

For a further exploration of inter-patient heterogeneity in regional patterns of abnormal deviations, we analyzed the origins of the different modes in Hamming distance distributions for tau pathology

(hamming\_tau) and amyloid pathology (hamming\_amyloid) in the Knight ADRC cohort, focusing on the CDR = 0.5 and CDR ≥ 1 ADS groups. KDE plots and heatmaps of Hamming distances were visualized side-by-side to explore variability patterns. Additionally, we compared binary-thresholded regional abnormality maps of two patients, sampled from different modes within each cohort (matched for age and sex), to examine distinct regional abnormality patterns (Figures S3 and S4).

The clustering of light and dark regions in the heatmaps corresponds to the modes in the KDE plots, indicating distinct subgroups within the cohorts. For instance, in the hamming\_tau distribution, most patients in the CDR = 0.5 and CDR ≥ 1 groups have median Hamming distances around 10, but a subset shows distances near 50, forming a separate mode (Figure S3). Similarly, the hamming\_amyloid distribution for CDR = 0.5 reveals modes with median distances of 15 and 65 (Figure S4). Binary abnormality maps for patients from different modes reveal further heterogeneity: despite sharing demographics and clinical group, these patients exhibit distinct patterns of abnormal deviations. This aligns with the clustering seen in the KDE plots and heatmaps, where patients in different modes show higher Hamming distances than those within the same mode. These findings underscore significant variability in regional pathological patterns within clinical groups, highlighting heterogeneity in AD progression among demographically similar patients.

## 3.6 Higher DSI was associated with progressive stages of dementia

## 3.6.1 Discovery dataset-ADNI

DSI calculated across all modalities (DSI\_all) exhibited minimal values for CU individuals (ADNI-CU-test; mean = 0.06, IQR = 0.03, 95% CI = [0.007-0.1]), with a consistently increasing trend across dementiastages(Figure 5A). Specifically, maximum DSI\_all values were observed for ADNI-ADS individuals with mild or more severe dementia (mean = 1.8, IQR = 1.5, 95% CI = [1.31-2.26]), followed by individuals with very mild dementia (mean = 1.1, IQR = 1.3, 95% CI = [0.871.25]) and preclinical AD individuals (mean = 0.45, IQR = 0.6, 95% CI = [0.39-0.52]). Pairwise group differences were statistically significant (FDR corrected p &lt; 0.05), except between very mild dementia and mild to more severe dementia groups (Figure 5A). When calculated across individual modalities, DSI values consistently increased at more advanced stages of dementia for all three modalities (i.e., DSI\_mri, DSI\_amyloid, DSI\_tau; Figure S5A). DSI values calculated for amyloidandtau(DSI\_amyloid,DSI\_tau)werehigheracrossCDRgroups compared to DSI values calculated for MRI (DSI\_mri). Pairwise group differences were statistically significant for DSI calculated for individual modalities except between very mild dementia and moderate to severe dementia groups for DSI\_mri, DSI\_amyloid, DSI\_tau, and between CU-test and preclinical AD for DSI\_mri and DSI\_tau (Figure S5A). The distribution of the different DSI measures (Figure S6), visu- alized using KDE plots, showed similar trends as the box plots in Figure 5A and Figure S5A.

## 3.6.2 Replication dataset-Knight ADRC

We found similar patterns of increasing DSI values at progressive dementiastagesintheADRC-ADScohort(Figure5B).DSI\_allwashigh-est for individuals with mild to more severe dementia (mean = 0.46, IQR = 0.28, 95% CI = [0.25-0.6]) and lowest for ADRC-CU-test individuals (mean = 0.006, IQR = 0.005, 95% CI = [0.002-0.008]) (Figure 5B). Individuals with preclinical AD (mean = 0.21, IQR = 0.22, 95% CI = [0.12-0.37]) and very mild dementia exhibited intermediate DSI\_all values (mean = 0.32, IQR = 0.32, 95% CI = [0.21-0.5]). Pairwise group differences were statistically significant ( p &lt; 0.05) except between the very mild dementia and moderate to severe dementia groups (Figure 5B). Notably, higher DSI\_all values were observed in ADNI-ADS compared to ADRC-ADS, likely due to more ADS individuals with advanced disease stages in ADNI compared to the ADRC dataset. Modality-specific DSI values (i.e., DSI\_mri, DSI\_amyloid, and DSI\_tau) for ADRC-ADS individuals showed similar trends as observed in ADNI (Figure S5B). The distribution of the different DSI measures (Figure S7), visualized using KDE plots, exhibited similar patterns as the box plots in Figure 5B and Figure S5B.

## 3.6.3 Difference in DSI scales between ADNI and Knight ADRC

The differences in DSI scales between ADNI and Knight ADRC likely reflect variations in underlying pathology within the same clinical groups, with ADNI showing higher overall pathology. Using the MannWhitney test, we compared ICV-adjusted hippocampal volume (Section 2.2.2), amyloid Centiloid (Section 2.3.3), and TI (Section 2.3.4) between the cohorts. Even within similar CDR groups (e.g., CDR = 0 andCDR = 0.5), ADNI participants exhibited greater hippocampal atrophy and tau burden, while amyloid Centiloid values were comparable (Figure S8). The higher pathology in ADNI ( p &lt; 0.05) is likely due to its focus on later disease stages, including MCI and AD dementia, whereas Knight ADRC primarily recruits cognitively unimpaired individuals with a family history of AD to study the preclinical phase. These differences in pathology likely contribute to the observed variations in DSI estimates across the two cohorts.

## 3.7 Higher DSI values were associated with impaired cognition

## 3.7.1 Discovery dataset-ADNI

Linear regression, adjusted for age and sex, revealed significant associations between higher DSI\_all values and decreased values in neuropsychological composites: memory ( β = -0.6; p &lt; 0.001; r = -0.62),

![Image](./Kumar2025_artifacts/image_000013_917c96ca1bccdccb87a3322b21cc894059267d57d73846f73cff182ee7add5c1.png)

![Image](./Kumar2025_artifacts/image_000014_d9faa22740262ccf59f42587062fddd70a0a4262bd18a2e37d402003e2ddd470.png)

(B)

\_

a

11.00

9

7)

0.75

0.50

0.25

0)

ADRC

ba

2.00

1.754

1.50

1.25)

‘

4

——

amyloid

CU

negative

—

‘

¢

$

CDR

=0

ADS

CDR

,

’

‘

CDR

=

0.5

ADS

groups

FIGURE 5 Box plot showing DSI\_all (DSI across all modalities; see Section 2.5.5) for both ADNI (A) and Knight ADRC (B). The x-axis shows the different CDR groups in the ADS and CU-test (Sections 2.2.1 and 2.3.1). FDR-corrected post hoc Tukey comparisons used to assess pairwise group differences. ADNI, Alzheimer's Disease Neuroimaging Initiative; ADRC, Alzheimer's Disease Research Center; ADS, Alzheimer's disease spectrum; CDR, Clinical Dementia Rating; CU, cognitively unimpaired; DSI, Disease Severity Index; FDR, false discovery rate. Statistical annotations: ns, not significant 0.05 &lt; p ≤ 1, * 0.01 &lt; p ≤ 0.05, ** 0.001 &lt; p &lt; 0.01, *** p &lt; 0.001.

executive functioning ( β = -0.46; p &lt; 0.001; r = -0.54), and language ( β =-0.39; p &lt; 0.001; r = -0.47) (Table 2). Similar statistically significant associations were observed for DSI values based on individual modalities (DSI\_mri, DSI\_amyloid, DSI\_tau). The correlations were higher for DSI\_tau compared to DSI\_mri and DSI\_amyloid (Table S3). For ADNI, DSI\_all exhibited a linear association with each of the composite scores: memory,executivefunctioningandlanguageasindicatedbythescatter plots (Figure S9).

In ADNI, both modality-specific DSI measures (DSI\_mri, DSI\_amyloid, and DSI\_tau) and standard ATN biomarkers (hippocampal volume, amyloid Centiloid value, and TI) showed statistically significant associations with cognition ( p &lt; 0.001). Modality-specific DSI values exhibited associations with composite scores comparable to their corresponding ATN imaging signatures (Tables S3 and S4), with a few key exceptions. DSI\_amyloid exhibited a relatively stronger association with language scores compared to amyloid Centiloid (Table S3), while hippocampal volume showed slightly higher correlations with memory and executive functioning than DSI\_mri (Table S4). In the ADNIdataset, the stronger association between classical hippocampal volume and memory can be attributed to the hippocampus's central role in memory function, making it a highly localized and specific biomarker. In comparison, DSI\_mri aggregates information from

ns

‘

.

CDR

&gt;=1

ADS

![Image](./Kumar2025_artifacts/image_000015_39c4bdd7b4bcab31c638be9976e26b57f3c33e72ae763ed0d167e5599d1c6a2d.png)

TABLE 2 Association between DSI\_all (across all modalities) and the composite cognitive scores (memory, executive functioning, and language).

|           |                  | ADNI-ADS   | ADNI-ADS   | ADNI-ADS   | ADRC-ADS   | ADRC-ADS   | ADRC-ADS   |
|-----------|------------------|------------|------------|------------|------------|------------|------------|
| Parameter | Cognitive domain | β          | p -value   | r          | β          | p -value   | r          |
| DSI_all   | Memory           | - 0.65     | < 0.001    | - 0.62*    | - 0.71     | < 0.001    | - 0.68*    |
|           | Executive        | - 0.46     | < 0.001    | - 0.54*    | - 0.52     | < 0.001    | - 0.55*    |
|           | Language         | - 0.39     | < 0.001    | - 0.47*    | - 0.36     | < 0.001    | - 0.41*    |

Note : β represents the slope and p represents the p -value for linear regression, adjusted for age and sex. r represents the Pearson correlation coefficient. *The correlation of DSI\_all versus cognition was significantly higher compared to the modality-specific DSI measures (DSI\_mri, DSI\_amyloid, and DSI\_tau; Table S3) by Steiger's test.

Abbreviations: ADNI, Alzheimer's Disease Neuroimaging Initiative; ADRC, Alzheimer's Disease Research Center; ADS, Alzheimer's disease spectrum; DSI, Disease Severity Index.

multiple brain regions, providing a broader but less targeted measure of neurodegeneration, which may weaken its direct correlation with memory. DSI\_all consistently showed stronger correlations with cognitive scores compared to modality-specific DSI measures (Table 2, Table S3).

## 3.7.2 Replication dataset-Knight ADRC

Weobserved similar trends of significant associations between DSI\_all and the neuropsychological composites in ADRC-ADS (Table 2). In ADRC-ADS, DSI\_all was significantly associated with memory ( β = -0.71; p &lt; 0.001; r = -0.68), executive functioning ( β = -0.52; p &lt; 0.001; r = -0.56), and language ( β =-0.36; p &lt; 0.001; r = -0.41). Similarly, DSI calculated for individual modalities exhibited significant associations with cognitive domains, with DSI\_amyloid and DSI\_tau generally showing higher correlations than DSI\_mri (Table S3). In Knight ADRC,DSI\_allshowedasimilarlinearrelationshipwitheachcomposite score-memory, executive functioning, and language (Figure S9).

Consistent with ADNI-ADS findings, both modality-specific DSI measures (DSI\_mri, DSI\_amyloid, and DSI\_tau) and standard ATN biomarkers (hippocampal volume, amyloid Centiloid value, and TI) showed statistically significant associations with cognition in the Knight ADRC cohort ( p &lt; 0.001). Similarly, in ADRC-ADS, modalityspecific DSI measures exhibited associations comparable to their corresponding ATN imaging signatures (Tables S3 and S4). Notably, DSI\_all, which integrates information across all modalities, outperformed modality-specific DSI measures in its correlation with composite cognitive scores in both ADNI and Knight ADRC cohorts.

## 3.8 Higher DSI is associated with increased risk for clinical progression

## 3.8.1 Discovery dataset-ADNI

Longitudinal clinical status data were available for 175 individuals in ADNI-ADS with either no or very mild dementia at baseline. DSI\_all wassignificantly associated with the risk of progressing to mild or more severe dementia ( p &lt; 0.001; Figure 6A). Notably, individuals in higher DSI quartiles, particularly q4 ( p &lt; 0.001) and q3 ( p &lt; 0.01), demonstrated a heightened risk of progression compared to those in lower quartiles, namely q1 and q2 (Table S5).

## 3.8.2 Replication dataset-Knight ADRC

Longitudinal clinical status data were available for 85 ADS individuals with either no or very mild dementia at baseline (CDR = 0 or CDR = 0.5). As in ADNI-ADS, the survival analysis in ADRCADS showed an association between DSI\_all and clinical progression (Figure 6B). In ADRC-ADS, individuals in q4 ( p &lt; 0.001) and q3 ( p &lt; 0.01) progressed more rapidly to severe dementia than those in q1 and q2 (Table S5).

## 4 DISCUSSION

In this study, we applied a deep learning-based normative modeling framework across multiple neuroimaging modalities to assess heterogeneity in neuroanatomical and neuropathological changes in the brain of individuals with AD. Results showed evidence of (i) heterogeneous patterns of abnormal deviations in regional volumetric measurements as well as amyloid and tau deposition between patients with AD; (ii) increased dissimilarity in spatial patterns of abnormal deviations for AD patients at more severe dementia stages; (iii) associations of DSI, which distils spatial patterns of abnormal deviations across multiple modalities in a single index for each subject, with cognitive performance; as well as (iv) associations of DSI with increased risk of disease progression. Our observations were reproducible in both the discovery and replication datasets, which demonstrated the generalizability of our scientific findings.

## 4.1 Deep learning-based normative modeling

Normative approaches to study heterogeneity in AD typically learn a regression model independently for each brain region, ignoring

![Image](./Kumar2025_artifacts/image_000016_99c6c9fd3028b4b088d24ad23d2db53f05754514d06c53dc20a32cd045bc75f4.png)

![Image](./Kumar2025_artifacts/image_000017_8e19596bee4d97007bb82f88c52f2708eccd65ca972492c528e45fd5fb046cb5.png)

(B)

FIGURE 6 Kaplan-Meier plot of conversion from CDR &lt; 1 to CDR ≥ 1 for ADNI-ADS (A) and ADRC-ADS (B) participants. The x-axis and the y-axis represent the follow-up period (in months) and the probability of progressing from CDR &lt; 1 to CDR ≥ 1, respectively. The four lines represent the four quantiles of DSI\_all (DSI across all modalities), shown by blue, red, green, and orange, respectively. The filled color span represents the 95% confidence intervals. ADNI, Alzheimer's Disease Neuroimaging Initiative; ADRC, Alzheimer's Disease Research Center; ADS, Alzheimer's disease spectrum; CDR, Clinical Dementia Rating scale; DSI, Disease Severity Scale.

the multivariate nature of the data. 4,8,16 Our work used a deep learning-based normative model, specifically a variational autoencoder, to capture the complex non-linear interactions within multivariate data, rather than modeling each variable independently. However, existing studies using deep normative models have been limited in studying interactions between single modality measurements (e.g., regional volumetric measurements extracted from T1-weighted MRI). 12,13 To accurately characterize a multi-factorial disease like AD, it is essential to use multiple neuroimaging modalities that can quantify biomarker pathology-including neurodegeneration, amyloid, and tau deposition. Despite recent progress on developing deep learning normative models for multiple modalities, these approaches have primarily focused on methodological advancements. 14,23 Further, these studies validated their approach using measurements extracted from

MRI and did not investigate AD heterogeneity using the core AD biomarkers (i.e., amyloid and tau). Our work used a multimodal deep learning-based normative modeling framework to investigate AD heterogeneity through the lens of multimodal imagingbased ATN biomarkers, that is, neurodegeneration, amyloid, and tau biomarkers.

## 4.2 Variation in spatial patterns of abnormal deviations across gray matter volume, amyloid, and tau burden

Our findings both complement and provide new insights to the established understanding of the neurobiology of AD. High proportion of

abnormal deviations in regional gray matter volumetric measurements was observed in hippocampal and medial-temporal regions, areas known to be associated with neurodegeneration. 44,45 Abnormal deviations in amyloid deposition were observed the most in the in the precuneus, frontal, and temporal regions, which are among the first areas to accumulate FBP amyloid pathology. 46-48 Similarly, maximum abnormal deviations in tau deposition were observed in the medial and lateral temporal regions. These regions are among the first to accumulate tau and are typically used to construct the meta-ROI to characterize early tau accumulation. 10,49

The regions with the highest proportion of abnormal deviations across all modalities (e.g., medial-temporal regions) represent the areas associated with onset of clinical symptoms related to 'typical AD.' The proportion of abnormal deviations for these 'typical' regions also increases with disease severity which complements the current literature on AD. 44,45 However, our work extends the literature by showing that there are other regions with lower proportion of abnormal deviations. This suggests partial overlap between ADS patients, which challenges the validity of a 'typical AD' patient. Further, this conflicts with the assumption of disease group homogeneity that is common among typical analytical tools, such as case-control studies. Further evidence for the partial overlap between patients were provided by the Hamming distance analyses, which quantified it and demonstrated increased dissimilarity in spatial patterns of abnormal deviations between AD patients at more severe dementia stages. Together, the results of the regional proportion of abnormal deviations across the three modalities (Figure 3) and the results of the Hamming distance analyses (Figure 4) provide evidence that ADS patients not only differ in the number of regions with statistically significant abnormal deviations but also in their respective patterns of abnormal deviations.

Further, our results indicated that for all ADS groups, the spatial patterns of abnormal deviations for MRI had the highest within-group dissimilarity, followed by tau and amyloid (Figure S2). These observations are also supported by our results in Figure 3 where the highest proportion of abnormal deviations across all ADS groups was the lowest for MRI (47%), higher for tau (84%), and the highest for amyloid (100%). This indicated greater variation in the spatial patterns of gray matter atrophy and tau pathology compared to amyloid deposition.

The observed variation in abnormal deviations across the three imaging modalities is in line with previous single-modality normative modeling and subtyping studies. 16,50,51 Specifically, a similar proportion of regional abnormal deviations was reported in a previous normative modeling study examining cortical thickness heterogeneity in AD. 16 Moreover, studies using T1-weighted MRI or tau PET to estimate subtypes within the ADS population identified more subtypesforMRI(foursubtypes 52-54 or three subtypes 55-58 ) and tau (four subtypes 50,59,60 ) compared to the ones using amyloid PET as the input modality (two subtypes 51,61 ). The fewer amyloid-driven subtypes indicate less heterogeneity compared to MRI and tau, consistent with our findings.

## 4.3 DSI\_all as a potential marker of brain health

Wedevelopedamultimodalmetric,DSI\_all,whichprovidesanindividualized measure of brain health by incorporating variability in gray matter volume, amyloid, and tau deposition, rather than relying on groupaverage relationships. As a unified metric, DSI\_all combines information from DSI\_mri, DSI\_amyloid, and DSI\_tau to represent their collective influence on overall brain health. Recent studies have relied on single modality to calculate the total count of regions with abnormal deviations in cortical thickness as a marker of disease progression. 8,16 Additionally, recent studies have also quantified tau spread (TSS) for predicting cognitive impairment and disease progression. 62,63 In contrast to these previous approaches, DSI\_all captures both the spatial spread and magnitude of regional abnormal deviations across all modalities. This allows DSI\_all to quantify neurodegenerative and neuropathological changes in the brain, providing a personalized metric of brain health, which can assist in clinical decision making. This is further supported by the demonstrated associations with cognitive performance, disease severity, and clinical progression. Lastly, DSI\_all can be potentially used to monitor the amyloid burden and track patient response to recently United States Food and Drug Administration (FDA) -approved ADtreatments, such as aducanumab and lecanemab medications. 64,65

## 4.4 Limitations and scope for future work

There are certain limitations that need to be considered regarding our analyses. First, we used cross-sectional imaging data for our normative modeling framework, providing a snapshot of the disease at a specific time. Due to the cross-sectional setting, it is challenging to distinguish between stages and subtypes. While the results of our analyses examining the regional proportions of abnormal deviations as well as patient dissimilarity across disease stages demonstrated that the observed variability of spatial patterns is due to both disease progression and spatial heterogeneity, future works should incorporate serial neuroimaging data collected across multiple time points to better characterize disease progression and heterogeneity.

Demographic differences between the ADNI and Knight ADRC cohorts, such as age and sex distributions, present a limitation in our study. These disparities due to differences in recruitment strategies and population characteristics may affect data the generalization of our normative model, introducing biases in cross-cohort model performance. Although fine-tuning helps address domain shifts to some extent, it cannot fully account for these demographic variations. Additionally, both ADNI and Knight ADRC datasets consisted of individuals from North America only, which are not representative of the general population. Future studies should utilize larger, more diverse datasets, sampled from different geographical regions or harmonize subpopulations within cohorts to minimize inter-cohort differences. This can allow studies to have a larger sample size for a more accurate representation of both the healthy and disease brain.

Third, the imaging scans in the discovery and replication cohorts were processed with different versions of FreeSurfer (FreeSurfer 6 for ADNI and FreeSurfer 5.3 for ADRC), and the imaging data were acquired using different MRI scanners. These differences may introduce variability, particularly in more granular MRI volumetric measures, which could add noise to the normative model and potentially affect the robustness of our results. Although we fine-tuned our pre-trained normative model on the replication cohort to address dataset-specific biases, 66-68 future multisite studies could further mitigate issues due to scanner and analysis differences using harmonization methods such as COMBAT. 69,70 Harmonization methods can account for site-specific and scanner-related effects, thus improving the consistency and reliability of normative modeling.

Finally, cerebrovascular disease burden is an important copathology in AD, contributing to cognitive decline independently or synergistically with amyloid and tau pathologies. 71,72 While our study focused on neurodegeneration as a key co-pathology to study deviations in brain structure, we acknowledge that vascular burden represents an essential dimension to understand AD. 73 Notably, our mmVAE framework is flexible and can incorporate additional input modalities, including imaging biomarkers related to vascular burden, such as white matter hyperintensities, arterial spin labeling measures of cerebral blood flow, or diffusion tensor imaging metrics of small vessel disease. Future studies should explore the integration of vascular burden to identify vascular-driven subtypes and provide a more comprehensive understanding of the heterogeneity in AD pathophysiology. 74,75

## 4.5 Conclusion

In this study, we assessed the heterogeneity in AD through the lens of multiple neuroimaging modalities by estimating regional statistically significant neurodegenerative and neuropathological deviations at the individual level. We studied these subject-specific maps of regional abnormal deviations across gray matter volume, amyloid burden, and tau deposition and observed higher variability in the spatial patterns of MRI atrophy compared to amyloid and tau burden. Additionally, we showed higher within-group heterogeneity for ADS patients at increased dementia stages. Lastly, we developed an individualized metric of brain health that summarizes the extent and severity of neurodegeneration and neuropathology. Together, the individualized DSI and the subject-specific maps of abnormal deviations have the potential to assist in clinical decision making and monitor patient response to anti-amyloid treatments. Our results were reproducible in both the discovery and replication datasets, demonstrating the generalizability of our findings.

## AUTHOR CONTRIBUTIONS

All authors contributed to the conceptualization and design of the study. Sayantan Kumar implemented all data analyses and experiments and wrote the first draft of the manuscript. Aristeidis Sotiras contributed to the interpretation of data. Tom Earnest, Brian A. Gordon, and Deydeep Kothapalli provided technical support. All authors were involved with manuscript revision, and all approved of the final draft.

## ACKNOWLEDGMENTS

The authors thank the staff for the Washington University Center for High Performance Computing who helped enable this work.

The preparation of this report was supported by the National Institutes of Health (NIH) (R01-AG067103). Computations were performed using the facilities of the Washington University Research Computing and Informatics Facility, which were partially funded by NIH grants S10OD025200, 1S10RR022984-01A1, and 1S10OD018091-01. Additional support is provided by the McDonnell Center for Systems Neuroscience.

Data collection and sharing for this project was funded by the Alzheimer's Disease Neuroimaging Initiative (ADNI) (National Institutes of Health Grant U01 AG024904) and DOD ADNI (Department of Defense award number W81XWH-12-2-0012). ADNI is funded by the National Institute on Aging, the National Institute of Biomedical Imaging and Bioengineering, and through generous contributions from the following: AbbVie, Alzheimer's Association; Alzheimer's Drug Discovery Foundation; Araclon Biotech; BioClinica, Inc.; Biogen; Bristol-MyersSquibbCompany;CereSpir,Inc.;Cogstate;EisaiInc.;Elan Pharmaceuticals, Inc.; Eli Lilly and Company; EuroImmun; F. HoffmannLa Roche Ltd and its affiliated company Genentech, Inc.; Fujirebio; GE Healthcare; IXICO Ltd.; Janssen Alzheimer Immunotherapy Research &amp; Development, LLC.; Johnson &amp; Johnson Pharmaceutical Research &amp; Development LLC.; Lumosity; Lundbeck; Merck &amp; Co., Inc.; Meso Scale Diagnostics, LLC.; NeuroRx Research; Neurotrack Technologies; Novartis Pharmaceuticals Corporation; Pfizer Inc.; Piramal Imaging; Servier; Takeda Pharmaceutical Company; and Transition Therapeutics. The Canadian Institutes of Health Research is providing funds to support ADNI clinical sites in Canada. Private sector contributions are facilitated by the Foundation for the National Institutes of Health (www.fnih.org). The grantee organization is the Northern California Institute for Research and Education, and the study is coordinated by the Alzheimer's Therapeutic Research Institute at the University of Southern California. ADNI data are disseminated by the Laboratory for Neuro Imaging at the University of Southern California.

Data were also provided (in part) by Knight Alzheimer Disease Research Center (ADRC), supported by the ADRC grant [P50AG05681], Healthy Aging and Senile Dementia [P01 AG03991], and Adult Children Study [P01 AG026276] and P30 NS048056 awarded to Dr Morris. AV-45 doses were provided by Avid Radiopharmaceuticals, a wholly owned subsidiary of Eli Lilly. Avid Radiopharmaceuticsls, Inc., a wholly owned subsidiary of Eli Lilly and Company, enabled use of the 18F-flortaucipir tracer by providing precursor, but did not provide direct funding and was not involved in data analysis or interpretation.

Data used in preparation of this article were obtained from the Alzheimer's Disease Neuroimaging Initiative (ADNI) database (adni.loni.usc.edu). As such, the investigators within the ADNI contributed to the design and implementation of ADNI and/or provided data but did not participate in analysis or writing of this report. A complete listing of ADNI investigators can be found

at: http://adni.loni.usc.edu/wp-content/uploads/how\_to\_apply/ADNI\_ Acknowledgement\_List.pdf

## CONFLICT OF INTEREST STATEMENT

AuthorAristeidisSotirashasequityinTheraPanaceaandhavereceived personal compensation for serving as grant reviewer for BrightFocus Foundation. The remaining authors have no conflicting interests to report.

## DATA AVAILABILITY STATEMENT

All ADNI participants provided written informed consent, and study protocols were approved by each local site's institutional review board. ADNI data used in this study are publicly available and can be requested following ADNI Data Sharing and Publications Committee guidelines: https://adni.loni.usc.edu/data-samples/accessdata/. All protocols for Knight ADRC were approved by the Institutional Review Board at Washington University in St. Louis, and all participants provided informed consent before all procedures. Knight ADRC data can be obtained by submitting a data request through https://knightadrc.wustl.edu/data-request-form/.

## ORCID

SayantanKumar https://orcid.org/0000-0001-7213-0734

TomEarnest https://orcid.org/0000-0001-8671-8424

BradenYang https://orcid.org/0000-0002-2558-4132

AndrewJ.Aschenbrenner https://orcid.org/0000-0002-4317-7282

JasonHassenstab https://orcid.org/0000-0002-7802-3371

BeauAnces https://orcid.org/0000-0003-3862-7397

JohnMorris https://orcid.org/0000-0001-9820-5618

TammieL.S.Benzinger https://orcid.org/0000-0002-8114-0552

Brian A. Gordon https://orcid.org/0000-0003-2109-2955

Philip Payne https://orcid.org/0000-0002-9532-2998

Aristeidis Sotiras https://orcid.org/0000-0003-0795-8820

![Image](./Kumar2025_artifacts/image_000018_7f6cea851f40e43e463fe18afcb4ad63aa325fbf6b6218fa06a08a44d87728da.png)

![Image](./Kumar2025_artifacts/image_000019_b12b8594fd234d3a799e0f3f8e1cc5fcfd9f842da1c0927ce04de66da20451da.png)

![Image](./Kumar2025_artifacts/image_000020_1df0d27fb0dac1ddb0f08a49974ed4a50877d96bf7622161d53a0dff1e73ad6b.png)

![Image](./Kumar2025_artifacts/image_000021_f56cb0e070bd73da6881d1a08c93de7693720f287ca9978b4bc4c9334e0463a7.png)

## REFERENCES

1. Kumar S, Oh I, Schindler S, Lai AM, Payne PR, Gupta A. Machine learning for modeling the progression of Alzheimer disease dementia using clinical data: a systematic literature review. JAMIA Open . 2021;4:ooab052.
2. Richards M, Brayne C. What do we mean by Alzheimer's disease?. BMJ . 2010:341.
3. JackCR,KnopmanDS,JagustWJ,etal.Hypotheticalmodelofdynamic biomarkers of the Alzheimer's pathological cascade. Lancet Neurol . 2010;9:119-128.
4. VerdiS,MarquandAF,SchottJM,ColeJH.Beyondtheaveragepatient: how neuroimaging models can address heterogeneity in dementia. Brain . 2021;144:2946-2953.
5. Habes M, Grothe MJ, Tunc B, McMillan C, Wolk DA, Davatzikos C. Disentangling heterogeneity in Alzheimer's disease and related dementias using data-driven methods. Biol Psychiatry . 2020;88:7082.
6. Kia SM, Marquand AF. Neural processes mixed-effect models for deep normativemodelingofclinical neuroimagingdata. Int Conf Med Imaging Deep Learn . 2019:297-314.
7. Marquand AF, Rezek I, Buitelaar J, Beckmann CF. Understanding heterogeneity in clinical cohorts using normative models: beyond case-control studies. Biol Psychiatry . 2016;80:552-561.
8. Verdi S, Kia SM, Yong KX, et al. Revealing individual neuroanatomical heterogeneity in Alzheimer disease using neuroanatomical normative modeling. Neurology . 2023;100:e2442-53.
9. Loreto F, Verdi S, Kia SM, et al. Examining real-world Alzheimer's disease heterogeneity using neuroanatomical normative modelling. medRxiv . 2022. 2022.11. 02.22281597.
10. Earnest T, Bani A, Ha SM, et al. Data-driven decomposition and staging of flortaucipir uptake in Alzheimer's disease. Alzheimers Dement 2024:4002-4019.
11. LeeWJ,BrownJA,KimHR,etal.RegionalA β -tau interactions promote onset and acceleration of Alzheimer's disease tau spreading. Neuron . 2022;110:1932-1943. e5.
12. Pinaya WH, Scarpazza C, Garcia-Dias R, et al. Using normative modelling to detect disease progression in mild cognitive impairment and Alzheimer's disease in a cross-sectional multi-cohort study. Sci Rep . 2021;11:1-13.
13. Pinaya WH, Mechelli A, Sato JR. Using deep autoencoders to identify abnormal brain structural patterns in neuropsychiatric disorders: a large-scale multi-sample study. HumBrain Mapp . 2019;40:944-954.
14. Lawry Aguila A, Chapman J, Janahi M, Altmann A. Conditional vaes for confound removal and normative modelling of neurodegenerative diseases. International Conference on Medical Image Computing and Computer-Assisted Intervention . Cham: Springer Nature Switzerland; 2022.
15. Fraza CJ, Dinga R, Beckmann CF, Marquand AF. Warped Bayesian linear regression for normative modelling of big data. Neuroimage . 2021;245:118715.
16. Loreto F, Verdi S, Kia SM, et al. Alzheimer's disease heterogeneity revealed by neuroanatomical normative modeling. Alzheimers Dement Diagn Assess Dis Monit . 2024;16:e12559.
17. Jack CR, Jr, HJ Wiste, Therneau TM, et al. Associations of amyloid, tau, and neurodegeneration biomarker profiles with rates of memory decline among individuals without dementia. JAMA . 2019;321:23162325. doi:10.1001/jama.2019.7437
18. Aschenbrenner AJ, Gordon BA, Benzinger TL, Morris JC, Hassenstab JJ. Influence of tau PET, amyloid PET, and hippocampal volume on cognition in Alzheimer disease. Neurology . 2018;91:e859-66.
19. Ebenau JL, Timmers T, Wesselman LMP, et al. ATN classification and clinical progression in subjective cognitive decline. Neurology . 2020;95:e46-58. doi:10.1212/WNL.0000000000009724
20. Ezzati A, Abdulkadir A, Jack Jr CR, et al. Predictive value of ATN biomarker profiles in estimating disease progression in Alzheimer's disease dementia. Alzheimers Dement . 2021;17:1855-1867. doi:10. 1002/alz.12491
21. Peretti DE, Ribaldi F, Scheffler M, Chicherio C, Frisoni GB, Garibotto V. Prognostic value of imaging-based ATN profiles in a memory clinic cohort. Eur J Nucl Med Mol Imaging . 2023;50:3313-3323. doi:10.1007/ s00259-023-06311-3
22. Kumar S, Payne P, Sotiras A. Improving normative modeling for multi-modal neuroimaging data using mixture-of-product-of-experts variational autoencoders. 2024 IEEE International Symposium on Biomedical Imaging (ISBI). IEEE 2024.
23. Lawry Aguila A, Chapman J, Altmann A. Multi-modal variational autoencoders for normative modelling across multiple imaging modalities. International Conference on Medical Image Computing and Computer-Assisted Intervention. Cham: Springer Nature Switzerland, 2023.
24. Kumar S, Payne PR, Sotiras A. Normative modeling using multimodal variational autoencoders to identify abnormal brain volume deviations in Alzheimer's disease. Med . Imaging . 2023; 12465, SPIE; 2023, p. 1246503.
25. Kumar S, Payne P, Sotiras A. Improving normative modeling for multi-modal neuroimaging data using mixture-of-product-of-experts variational autoencoders. 2024 IEEE Int. Symp. Biomed. Imaging ISBI . 2024:1-5. doi:10.1109/ISBI56570.2024.10635897

![Image](./Kumar2025_artifacts/image_000022_4431c69e01228fabafef4a58f2279ee9035ea42439ab95f5bb408692062c09e5.png)

![Image](./Kumar2025_artifacts/image_000023_0bd3a762eab47855d64e04daf43877815fb96dded744299534d32133c7264943.png)

26. Kumar S, Payne P, Sotiras A. mmNormVAE: Normative Modeling on Multimodal Neuroimaging Data using Variational Autoencoders. Deep Generative Models for Health Workshop NeurIPS 2023 .
27. Kumar S, Qiu P, Yang B, Bani A, Payne PRO, Sotiras A. Multimodal normative modeling in Alzheimer's Disease with introspective variational autoencoders 2024:2024.12.12.628273. doi:10.1101/2024.12. 12.628273
28. Revised Criteria for Diagnosis and Staging of Alzheimer's | AAIC. Revis Criteria Diagn Staging Alzheimers AAIC n.d. Accessed June 18, 2024. https://aaic.alz.org/diagnostic-criteria.asp
29. VanDerFlier WM,Scheltens P. The ATN framework-moving preclinical Alzheimer disease to clinical relevance. JAMANeurol . 2022;79:968970.
30. Desikan RS, Ségonne F, Fischl B, et al. An automated labeling system for subdividing the human cerebral cortex on MRI scans into gyral based regions of interest. Neuroimage . 2006;31:968-980.
31. Fischl B, Salat DH, Busa E, et al. Whole brain segmentation: automated labeling of neuroanatomical structures in the human brain. Neuron . 2002;33:341-355.
32. Su Y, Blazey TM, Snyder AZ, et al. Partial volume correction in quantitative amyloid imaging. Neuroimage . 2015;107:55-64.
33. Su Y, D'Angelo GM, Vlassenko AG, et al. Quantitative analysis of PiBPETwith freesurfer ROIs. PLoS One . 2013;8:e73377.
34. Klunk WE, Koeppe RA, Price JC, et al. The Centiloid Project: standardizing quantitative amyloid plaque estimation by PET. Alzheimers Dement . 2015;11:1-15. doi:10.1016/j.jalz.2014.07.003
35. Landau SM, Breault C, Joshi AD, et al. Amyloidβ imaging with Pittsburgh compound B and florbetapir: comparing radiotracers and quantification methods. J Nucl Med . 2013;54:70-77.
36. Landau SM, Thomas BA, Thurfjell L, et al. Amyloid PET imaging in Alzheimer's disease: a comparison of three radiotracers. Eur J Nucl Med Mol Imaging . 2014;41:1398-1407.
37. Clark CM, Schneider JA, Bedell BJ, et al. Use of florbetapir-PET for imaging β -amyloid pathology. JAMA . 2011;305:275-283.
38. Joshi AD, Pontecorvo MJ, Clark CM, et al. Performance characteristics of amyloid PET with florbetapir F 18 in patients with Alzheimer's disease and cognitively normal subjects. J Nucl Med . 2012;53:378-384.
39. Su Y, Flores S, Wang G, et al. Comparison of Pittsburgh compound B and florbetapir in cross-sectional and longitudinal studies. Alzheimers Dement Diagn Assess Dis Monit . 2019;11:180-190.
40. Su Y, Flores S, Hornbeck RC, et al. Utilizing the Centiloid scale in cross-sectional and longitudinal PiB PET studies. NeuroImage Clin . 2018;19:406-416.
41. Morris JC. The Clinical Dementia Rating (CDR): current version and scoring rules. Neurology . 1993.
42. Benjamini Y, Hochberg Y. Controlling the false discovery rate: a practical and powerful approach to multiple testing. J R Stat Soc Ser B Methodol . 1995;57:289-300.
43. Mowinckel AM, Vidal-Piñeiro D. Visualization of brain statistics with R packages ggseg and ggseg3d. Adv Methods Pract Psychol Sci . 2020;3:466-483.
44. Davatzikos C, Xu F, An Y, Fan Y, Resnick SM. Longitudinal progression of Alzheimer's-like patterns of atrophy in normal older adults: the SPARE-AD index. Brain . 2009;132:2026-2035. doi:10.1093/brain/ awp091
45. Davatzikos C, Fan Y, Wu X, Shen D, Resnick SM. Detection of prodromal Alzheimer's disease via pattern classification of magnetic resonance imaging. Neurobiol Aging . 2008;29:514-523. doi:10.1016/j. neurobiolaging.2006.11.010
46. Wong DF, Rosenberg PB, Zhou Y, et al. In vivo imaging of amyloid deposition in Alzheimer disease using the radioligand 18F-AV-45 (flobetapir F 18). J Nucl Med . 2010;51:913-920.
47. Levitis E, Vogel JW, Funck T, et al. Differentiating amyloid beta spread in autosomal dominant and sporadic Alzheimer's disease. Brain Commun . 2022;4:fcac085.
48. Palmqvist S, Schöll M, Strandberg O, et al. Earliest accumulation of β -amyloid occurs within the default-mode network and concurrently affects brain connectivity. Nat Commun . 2017;8:1214.
49. Mishra S, Gordon BA, Su Y, et al. AV-1451 PET imaging of tau pathology in preclinical Alzheimer disease: defining a summary measure. Neuroimage . 2017;161:171-178.
50. Vogel JW, Young AL, Oxtoby NP, et al. Four distinct trajectories of tau deposition identified in Alzheimer's disease. Nat Med . 2021;27:871881.
51. Aksman LM, Oxtoby NP, Scelsi MA, et al. A data-driven study of Alzheimer's disease related amyloid and tau pathology progression. Brain . 2023;146:4935-4948.
52. Dong A, Toledo JB, Honnorat N, et al. Heterogeneity of neuroanatomical patterns in prodromal Alzheimer's disease: links to cognition, progression and biomarkers. Brain . 2017;140:735-747.
53. Yang Z, Wen J, Davatzikos C. Smile-GANs: semi-supervised clustering via GANs for dissecting brain disease heterogeneity from medical images. ArXiv Prepr ArXiv200615255 . 2020.
54. Dong A, Honnorat N, Gaonkar B, Davatzikos C. CHIMERA: clustering of heterogeneous disease effects via distribution matching of imaging patterns. IEEE Trans Med Imaging . 2015;35:612-621.
55. Poulakis K, Ferreira D, Pereira JB, Smedby Ö, Vemuri P, Westman E. Fully Bayesian longitudinal unsupervised learning for the assessment and visualization of AD heterogeneity and progression. Aging . 2020;12:12622.
56. Young AL, Marinescu RV, Oxtoby NP, et al. Uncovering the heterogeneity and temporal complexity of neurodegenerative diseases with Subtype and Stage Inference. Nat Commun . 2018;9:4273.
57. Varol E, Sotiras A, Davatzikos C, Initiative ADN. HYDRA: revealing heterogeneity of imaging and genetic patterns through a multiple max-margin discriminative analysis framework. Neuroimage . 2017;145:346-364.
58. Zhang X, Mormino EC, Sun N, et al. Bayesian model reveals latent atrophy factors with dissociable cognitive trajectories in Alzheimer's disease. Proc Natl Acad Sci USA . 2016;113:E6535-44.
59. Lee HJ, Lee E-C, Seo S, et al. Identification of heterogeneous subtypes of mild cognitive impairment using cluster analyses based on PET imaging of tau and astrogliosis. Front Aging Neurosci . 2021;12: 615467.
60. Toledo JB, Liu H, Grothe MJ, et al. Disentangling tau and brain atrophy cluster heterogeneity across the Alzheimer's disease continuum. Alzheimers Dement Transl Res Clin Interv . 2022;8:e12305.
61. Sun Y, Zhao Y, Hu K, et al. Distinct spatiotemporal subtypes of amyloid deposition are associated with diverging disease profiles in cognitively normal and mild cognitive impairment individuals. Transl Psychiatry . 2023;13:35.
62. McCullough AA, Gordon BA, Christensen J, et al. P3-401: eXAMININGTHEABILITYOFATAUSPATIALSPREADMETRICTOINDICATE DISEASE PROGRESSION COMPARED TO AN INTENSITY-BASED APPROACH. Alzheimers Dement . 2018;14:P1255-6.
63. Doering S, McCullough AA, Gordon BA, et al. Evaluating regional importance for tau spatial spread in predicting cognitive impairment with machine learning. Alzheimers Dement . 2023;19:e082553.
64. Cummings J, Apostolova L, Rabinovici GD, et al. Lecanemab: appropriate use recommendations. J Prev Alzheimers Dis . 2023;10:362-377.
65. Tanzi RE. FDA approval of aduhelm paves a new path for Alzheimer's disease . vol. 12 ACS Publications; 2021
66. Pan SJ, Yang Q. A survey on transfer learning. IEEE Trans Knowl Data Eng . 2010;22:1345-1359. doi:10.1109/TKDE.2009.191
67. Cheplygina V, de Bruijne M, Pluim JPW. Not-so-supervised: a survey of semi-supervised, multi-instance, and transfer learning in medical image analysis. Med Image Anal . 2019;54:280-296. doi:10.1016/j. media.2019.03.009
68. Shin H-C, Roth HR, Gao M, et al. Deep convolutional neural networks for computer-aided detection: cNN architectures, dataset characteris-

- tics and transfer learning. IEEETransMedImaging . 2016;35:1285-1298. doi:10.1109/TMI.2016.2528162
69. Pomponio R, Erus G, Habes M, et al. Harmonization of large MRI datasets for the analysis of brain imaging patterns throughout the lifespan. Neuroimage . 2020;208:116450.
70. Yang B, Earnest T, Kumar S, et al. Evaluation of ComBat harmonization for reducing across-tracer differences in regional amyloid PET analyses. HumBrain Mapp . 2024;45:e70068. doi:10.1002/hbm.70068
71. Eisenmenger LB, Peret A, Famakin BM, et al. Vascular contributions to Alzheimer's disease. Transl Res . 2023;254:41-53. doi:10.1016/j.trsl. 2022.12.003
72. Jack Jr CR, Andrews JS, Beach TG, et al. Revised criteria for diagnosis and staging of Alzheimer's disease: Alzheimer's Association Workgroup. Alzheimers Dement . 2024;20:5143-5169. doi:10.1002/alz. 13859
73. Jack Jr CR, Bennett DA, Blennow K, et al. NIA-AA research framework: toward a biological definition of Alzheimer's disease. Alzheimers Dement . 2018;14:535-562.
74. Paradise MB, Shepherd CE, Wen W, Sachdev PS. Neuroimaging and neuropathology indices of cerebrovascular disease burden. Neurology . 2018;91:310-320. doi:10.1212/WNL.0000000000005997
75. Nation DA, Sweeney MD, Montagne A, et al. Blood-brain barrier breakdown is an early biomarker of human cognitive dysfunction. Nat Med . 2019;25:270-276. doi:10.1038/s41591-018-0297-y

![Image](./Kumar2025_artifacts/image_000024_3144e19af73bf18b15791140b49acdcd58aa1218328b739a62e9a3021d366f2f.png)

## SUPPORTING INFORMATION

Additional supporting information can be found online in the Supporting Information section at the end of this article.

