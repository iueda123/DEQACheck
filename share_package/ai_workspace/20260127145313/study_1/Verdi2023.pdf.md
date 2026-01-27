## Revealing Individual Neuroanatomical Heterogeneity in Alzheimer Disease Using Neuroanatomical Normative Modeling

Serena Verdi, MPhil, Seyed Mostafa Kia, PhD, Keir X.X. Yong, PhD, Duygu Tosun, PhD, Jonathan M. Schott, MD, Andre F. Marquand, PhD,* and James H. Cole, PhD*

Neurology ® 2023;100:e2442-e2453. doi:10.1212/WNL.0000000000207298

## Abstract

## Background and Objectives

Alzheimer disease (AD) is highly heterogeneous, with marked individual di /uniFB00 erences in clinical presentation and neurobiology. To explore this, we used neuroanatomical normative modeling to index regional patterns of variability in cortical thickness. We aimed to characterize individual di /uniFB00 erences and outliers in cortical thickness in patients with AD, people with mild cognitive impairment (MCI), and controls. Furthermore, we assessed the relationships between cortical thickness heterogeneity and cognitive function, β -amyloid, phosphorylated-tau, and ApoE genotype. Finally, we examined whether cortical thickness heterogeneity was predictive of conversion from MCI to AD.

## Methods

Cortical thickness measurements across 148 brain regions were obtained from T1-weighted MRI scans from 62 sites of the Alzheimer ' s Disease Neuroimaging Initiative. AD was determined by clinical and neuropsychological examination with no comorbidities present. Participants with MCI had reported memory complaints, and controls were cognitively normal. A neuroanatomical normative model indexed cortical thickness distributions using a separate healthy reference data set (n = 33,072), which used hierarchical Bayesian regression to predict cortical thickness per region using age and sex, while adjusting for site noise. Z -scores per region were calculated, resulting in a Z -score brain map per participant. Regions with Z -scores &lt; -1.96 were classi /uniFB01 ed as outliers.

## Results

Patients with AD (n = 206) had a median of 12 outlier regions (out of a possible 148), with the highest proportion of outliers (47%) in the parahippocampal gyrus. For 62 regions, over 90% of these patients had cortical thicknesses within the normal range. Patients with AD had more outlier regions than people with MCI (n = 662) or controls (n = 159) ( F (2, 1,022) = 95.39, p = 2.0 × 10 -16 ). They were also more dissimilar to each other than people with MCI or controls ( F (2, 1,024) = 209.42, p = 2.2 × 10 -16 ). A greater number of outlier regions were associated with worse cognitive function, CSF protein concentrations, and an increased risk of converting from MCI to AD within 3 years (hazard ratio 1.028, 95% CI 1.016 -1.039, p = 1.8 × 10 -16 ).

## Discussion

Individualized normative maps of cortical thickness highlight the heterogeneous e /uniFB00 ect of AD on the brain. Regional outlier estimates have the potential to be a marker of disease and could be used to track an individual ' s disease progression or treatment response in clinical trials.

*These authors contributed equally to this work.

From the Centre for Medical Image Computing (S.V., J.H.C.), Medical Physics and Biomedical Engineering, University College London; Dementia Research Centre (S.V., K.X.X.Y., J.M.S., J.H.C.), UCL Queen Square Institute of Neurology, London, United Kingdom; Donders Centre for Cognitive Neuroimaging (S.M.K., A.F.M.), Donders Institute for Brain, Cognition and Behaviour, Radboud University, Nijmegen; Department of Psychiatry (S.M.K.), University Medical Centre Utrecht, the Netherlands; Department of Radiology and Biomedical Imaging (D.T.), University of California, San Francisco; and Department of Cognitive Neuroscience (A.F.M.), Radboud University Medical Centre, Nijmegen, the Netherlands.

Go to Neurology.org/N for full disclosures. Funding information and disclosures deemed relevant by the authors, if any, are provided at the end of the article.

The Article Processing Charge was funded by METAFORA Biosystems.

This is an open access article distributed under the terms of the Creative Commons Attribution-NonCommercial-NoDerivatives License 4.0 (CC BY-NC-ND), which permits downloading and sharing the work provided it is properly cited. The work cannot be changed in any way or used commercially without permission from the journal.

## Correspondence

Prof. Cole james.cole@ucl.ac.uk

## RELATED ARTICLE

![Image](./Verdi2023_artifacts/image_000000_f230919731a1da9086082bdebf90c695888262e476c439df6328fa95c8c74275.png)

## Editorial

Toward a Patient-Speci /uniFB01 c Readout of Neurodegeneration: The Case for Spatial Normative Modeling in Alzheimer Disease

Page 1125

## MORE ONLINE

![Image](./Verdi2023_artifacts/image_000001_ed17423a69aeffd5a5bee829f79e504777bb7c08622f8ae9139910794dacb89a.png)

Video

## Glossary

A β = β -amyloid; AD = Alzheimer disease; ADNI = Alzheimer ' s Disease Neuroimaging Initiative; FDR = false discovery rate; IQR = interquartile range; MCI = mild cognitive impairment; MMSE = Mini-Mental State Examination; OASIS = Open Access Series of Imaging Studies; p-tau = phosphorylated-tau; tOC = total outlier count; UCSF = University of California, San Francisco; UKB = UK Biobank.

Alzheimer disease (AD) is the commonest cause of dementia, being characterized by a progressive deterioration in cognitive functioning and independence. 1 The AD spectrum comprises substantial clinical and biological di /uniFB00 erences between patients recognized in clinical and research criteria. 2 These di /uniFB00 erences include variations in genetic basis, 3 symptom pro /uniFB01 le, age at onset, trajectory and severity, 4,5 biomarker readouts (e.g., CSF β -amyloid [A β ] levels), 6 comorbidities, 7 and in atrophy patterns. 8 Despite this, conventional statistical analyses focus on group averages. This fundamental statistical assumption posits that AD will a /uniFB00 ect di /uniFB00 erent patients in similar ways, 9 characterizing the average patient. To reach the goal of precision medicine for AD, we need to look beyond the average and design statistical approaches that re /uniFB02 ect patient heterogeneity at the individual level.

Neuroimaging has revealed that di /uniFB00 erences in brain structure are very common in patients with AD. 10 Neuroimaging methods are the gold standard of understanding the in vivo brain 11 ; speci /uniFB01 cally, structural imaging has been described as the imaging workhorse of neurodegeneration, being commonly recommended in AD diagnostic guidelines. 12 With this in mind, large structural neuroimaging data sets are increasingly available for dementia, such as Alzheimer ' s Disease Neuroimaging Initiative (ADNI), Open Access Series of Imaging Studies (OASIS), and National Alzheimer ' s Coordinating Center and in the general population (e.g., UK Biobank [UKB] and the Human Connectome Project). These data sets provide the ability to chart variation across cohorts and facilitate individual prediction.

Furthermore, large neuroimaging data sets have supported the development and application of data-driven methods in AD research. This has revealed that di /uniFB00 erences in brain structure are very common in patients. 8,13 Moreover, they have enabled the estimation of disease subtypes from neuroimaging data, as a way to disentangle heterogeneity by grouping patients by distinctive neurobiological and cognitive characteristics 8,10,13,14 and disease progression. 15 Such subtypes have the potential to stratify patient groups for clinical decision making, such as regarding treatment strategy, services and therapies tailored to clinico-radiologic phenotype, and/or trial enrollment. 16,17

Nevertheless, there are challenges associated with the clinical translation of neuroimaging-derived subtypes. 10 These include the validity of subtypes, how distinct subtypes are from each other, and how stable subtypes are over the disease course. 13,18

Moreover, by design, clustering assumes homogeneity within each cluster, clouding the individual-level variation present, therefore limiting the representation of heterogeneity in the sample. 19 For instance, individual-level variation is seen in atypical, nonamnestic AD (who comprise up to a third of young-onset AD), which results in challenges to diagnosis and appropriate care. 17 Arguably, assessing the neurobiology of AD at the individual patient level will provide a precise understanding of their disease, likely outcomes and facilitate tailored treatment strategies. However, although this concept of patient-centered, individualized precision medicine for AD is well established, current research e /uniFB00 orts are limited.

Neuroanatomical normative modeling is an emerging technique that captures individual-level variability in the brain. This can provide individual statistical inferences with respect to an expected normative distribution or trajectory over time. Speci /uniFB01 cally, this was by modeling the relationship between neurobiological variables (e.g., neuroimaging features) and covariates (e.g., demographic variables such as age and sex) to map centiles of variation across a cohort (i.e., Z -scores). An individual can then be located within the normative distribution to establish to what extent they deviate from the expected pattern in each measure, and a map can be generated of where and to what extent an individual ' s brain di /uniFB00 ers from the norm. 20,21 This technique has shown to be suitable for precise mapping of individual patterns of variation in brain structure across multiple psychiatric and neurodevelopmental disorders. 20,22-24 Such /uniFB01 ndings motivate the /uniFB01 rst application of neuroanatomical normative modeling to AD. 2

Here, we examine individual patterns of variation in brain structure in patients with AD using neuroanatomical normative modeling. Using the well-characterized, multisite, ADNI data set, we applied a recent implementation of the normative modeling framework, hierarchical Bayesian regression. This technique has been shown to e /uniFB03 ciently accommodate intersite variation and provides computational scaling, which is useful when using large studies, or combining smaller studies together, that are acquired across multiple sites in a federated learning framework. 25-27 Our main objective was to quantify spatial patterns of neuroanatomical heterogeneity using cortical thickness measures in patients with AD, people with mild cognitive impairment (MCI), and cognitively normal controls by calculating deviations from normative ranges for each brain region and then identifying statistical outliers. Speci /uniFB01 cally, we aimed to (1) assess the extent of neuroanatomical variability between individual

![Image](./Verdi2023_artifacts/image_000002_062280d73ec02837e4f03338ae9658868e8d9247ff66c4703fd01eeec9a36d74.png)

patients based on overlapping or distinct patterns of outliers, (2) quantify group di /uniFB00 erences in between-participant dissimilarity, (3) relate the quantity of neuroanatomical outliers to cognitive performance and AD biomarkers, and (4) examine whether the number of outliers relate to subsequent disease progression from MCI to AD.

## Methods

## Participants

Participants were derived from 2 data sets: (1) a reference data set that comprised healthy people across the human lifespan and (2) a clinical target data set, which included people with AD or MCI in addition to age-matched cognitively normal controls. The reference data set was made by combining data on healthy people from multiple publicly available sources, 27 including OASIS, Adolescent Brain Cognitive Development study, and UKB, detailed in eTable 1 (links.lww.com/WNL/ C774). The clinical data used in the preparation of this article were obtained from the ADNI database. 28 Thecriteria for study inclusion was the availability of a baseline T1-weighted MRI, which passed quality control. Furthermore, AD participants had to meet the National Institute of Neurological and Communicative Disorders and Stroke-AD and Related Disorders Association criteria for probable AD and were screened to exclude genetic risk for familial AD. Participants with MCI reported a subjective memory concern either autonomously or via an informant or clinician, and participants had no signi /uniFB01 cant levels of impairment in other cognitive domains.

## Standard Protocol Approvals, Registrations, and Patient Consents

Written informed consent was obtained from all participants before experimental procedures were performed. Approval was received by an ethical standards committee for ADNI study data use.

## MRI Acquisition

For the clinical data set, T1-weighted images were acquired at multiple sites using 3T MRI scanners. Detailed MRI protocols for T1-weighted sequences are available online. 29 The quality of raw scans was evaluated by University of California, San Francisco (UCSF) before our exclusion criteria. Scans were excluded based on technical problems and signi /uniFB01 cant motion artifacts and clinical abnormalities. 30

## Estimation of Cortical Thickness

T1-weighted scans from both the reference and ADNI data sets were processed using a mix of both FreeSurfer versions 5 and 6. Cortical thickness values were generated using the recon-all cross-sectional approach. 31 This cortical thickness algorithm calculates the mean distance between vertices of a corrected, triangulated estimated gray/white matter surface and gray matter/CSF (pial) surface, 32 which generated the cortical thickness of each region of the Destrieux atlas regions. 33 This included the mean cortical thickness and 148 regions cortical thickness values for each participant.

Quality control of FreeSurfer processing for the reference data set relied on automated /uniFB01 ltering median-centered absolute Euler number higher than 25, as used in prior work. 26,27 The exclusion of outliers based on Euler numbers has shown to be a reliable quality control strategy in large neuroimaging cohorts. 34,35 For the ADNI, quality control was based on a visual review of each cortical region performed by UCSF. Only scans that passed this quality control were used.

## Neuroanatomical Normative Modeling

A hierarchical Bayesian regression model was trained on multisite data to generate normative models per region using the covariates age and sex. This was based on the population variation in the reference data set (training data), which adaptively pools parameter estimates across sites via a shared prior over regression parameters across sites. 27 This simultaneously accounts for intersite variation and allows sites to borrow strength from one another in a fully Bayesian framework. The advantage of training the models on the large independent data set, compared with just using the ADNI, is that the ADNI consists of many sites with small sample sizes. This would result in unstable estimates of normative distributions that could be strongly in /uniFB02 uenced by outliers or sampling bias. Here, by training on over n = 33,000 from only 9 data sets (with 60 sites), the model produces a stable distribution of estimates across the entire lifespan. Next, these estimates were conditioned to our speci /uniFB01 c context, using an adapted transfer learning approach. 27 The parameters of the reference normative model were recalibrated to the ADNI data set using 70% of healthy controls per ADNI site, where 70% was used to give stable estimates of the transferred model parameters, given that many of the scan sites in the ADNI have quite small sample sizes. The remaining 30% of healthy controls plus MCI and patients with AD were used to assess the heterogeneity in neuroanatomical presentation. This process generated regional and mean cortical thickness Z -scores for each participant in the clinical data set, relative to the normative range of the reference data set. All modeling steps are performed using PCNtoolkit (version 0.20).

## Statistical Analysis

## Group Cortical Thickness Differences

Cortical thickness group comparisons were conducted using t tests at each region and corrected for multiple comparisons using the false discovery rate (FDR). Signi /uniFB01 cant p values were mapped onto the Destrieux atlas using the R package ggseg. 36

## Outlier Definition and Statistics

Outliers in terms of low cortical thickness were identi /uniFB01 ed for each region, de /uniFB01 ned as Z &lt; -1.96 (corresponding to the bottom 2.5% of the normative distribution of cortical thickness). We only used the lower bound threshold for outliers as we were interested in cortical thinning associated with neurodegeneration. The number of outliers was summed across 148 regions for each participant to give a total outlier count (tOC) across regions. Linear regression

tested for group di /uniFB00 erences in mean cortical thickness Z -score and tOC. In addition, group comparisons at each region were conducted using χ 2 (FDR corrected). The Hamming distance, a quantitative measure of similarity between binary thresholded cortical thickness outlier vectors, was used to measure dissimilarity between individuals. Median Hamming distances were compared between groups. To explore spatial patterns of cortical thickness outliers per group, the proportion of participants within each group whose cortical thickness was an outlier (i.e., Z &lt; -1.96) was calculated for each region. This enabled visualization of the extent to which patterns of outlier regions overlap or are distinct. This was mapped using the Destrieux atlas via the R package ggseg. All statistical analyses were implemented in R version 3.6.2.

## Outlier Associations With Cognitive Function and CSF Markers

Linear regression adjusting for age, sex, years of education, and Clinical Dementia Rating (sum of boxes) examined the relationship between tOC and cognitive composite scores (memory using ADNI MEM or executive function using ADNI EF). 37 We assessed the interactional e /uniFB00 ects of the diagnostic group within a subsequent regression. Furthermore, linear regression adjusting for age and sex only examined the relationship between tOC and CSF markers (A β and phosphoylated-tau [p-tau]). Here, we also assessed the interactional e /uniFB00 ects of the diagnostic group within a subsequent regression. To stratify outlier maps in both MCI and patients with AD groups, we used total scores from the Mini-Mental State Examination (MMSE).

## MCI to AD Conversion Analysis

Follow-up diagnosis status data, up to 3 years from the baseline scan, were obtained from 454 people with MCI. In total, 76 people with MCI at baseline had converted to AD within 3 years. We then ran a survival analysis using Cox

Table 1 Demographics of the ADNI Sample

|                                                        | Controls        | MCI             | AD              | Total           | Statistical differences                  |
|--------------------------------------------------------|-----------------|-----------------|-----------------|-----------------|------------------------------------------|
| n                                                      | 621             | 664             | 207             | 1,492           | -                                        |
| Sex, male:female                                       | 252:369         | 370:294         | 106:101         | 728:764         | χ 2 = 30.01, p = 3.04 × 10 - 7           |
| Age, y, mean ± SD                                      | 72.2 ± 6.8      | 71.9 ± 7.7      | 74.0 ± 8.0      | 72.3 ± 7.4      | F (2, 1,489) = 6.72, p = 0.001           |
| Age, y, range                                          | 62.8 - 88.7     | 56.1 - 92.5     | 57.0 - 88.5     | 56.1 - 92.5     | -                                        |
| Total MMSE score, mean ± SD a                          | 29.06 ± 1.18    | 27.82 ± 1.91    | 22.56 ± 3.19    | 26.94 ± 3.11    | F (2, 1,400) = 867.97, p < 2.2 × 10 - 16 |
| CSF A β , pg/mL, mean ± SD                             | 246.55 ± 305.02 | 231.98 ± 275.41 | 178.07 ± 182.37 | 223.00 ± 263.87 | F (2, 765) = 3.39, p = 0.03              |
| CSF p-tau, pg/mL, mean ± SD                            | 34.06 ± 17.96   | 40.82 ± 24.67   | 57.84 ± 32.22   | 43.42 ± 26.73   | F (2, 765) = 37.11, p < 4.12 × 10 - 16   |
| ApoE « 4 negative (% = proportion in the group sample) | 385 (69)        | 322 (53)        | 58 (31)         | 765 (56)        | χ 2 = 85.92, p < 2.2 × 10 - 16           |

Abbreviations: A β = β -amyloid; AD = Alzheimer disease; ADNI = Alzheimer ' s Disease Neuroimaging Initiative; ANOVA = analysis of variance; MCI = mild cognitive impairment; MMSE = Mini-Mental State Examination; p-tau = phosphorylated-tau.

Statistical differences were assessed using ANOVA and χ 2 tests.

a MMSE had a maximum score of 30.

proportional hazards regression to assess whether tOC related to the risk of converting from MCI to AD, controlling for age and sex. We use a Kaplan-Meier plot to illustrate how either a low or high tOC (split via median) can contribute to the risk of converting.

## Data Availability

Statistical analysis scripts are available on GitHub (github.com/ serenaverdi/ADNI\_normative-modelling). The neuroanatomical normative model was generated using the PCNtoolkit software package (github.com/amarquand/PCNtoolkit). ADNI data used in this study are publicly available and can be requested following ADNI Data Sharing and Publications Committee guidelines: adni.loni.usc.edu/data-samples/access-data/

## Results

## Participants

In the reference data set, a total of n = 33,072 T1-weighted MRI scans were collated across 60 sites (this sample is described in detail in Kia et al. 27 and summarized in eTable 1, links.lww.com/WNL/C774). The clinical ADNI data set amounted to 1,492 participants which were scanned across 62 sites (Table 1). Here 70% of controls were removed from the clinical data set and were used as a calibration data set to adapt the normative model to the new sites. These controls were randomly selected and strati /uniFB01 ed across sites and gender to make sure all sites and genders are present in the adaptation set. This left a total of 1,027 participants in the /uniFB01 nal clinical data set.

## Patients With AD Have Smaller Cortical Thicknesses Than People With MCI or With Normal Cognition

Mean cortical thicknesses were compared across participant groups. Ageand sex-adjusted mean cortical thickness

![Image](./Verdi2023_artifacts/image_000003_970d5a4460fb3f5e508faa4246b2a878a9c1f73b231942f2539c535c6da24dec.png)

![Image](./Verdi2023_artifacts/image_000004_f2dfdbafbec7241241abdae549f3fe5654723245c306c8677afc2a046d037d12.png)

signi /uniFB01 cantly di /uniFB00 ered between groups overall ( F (2, 1,487) = 137.9, p =2.0 × 10 -16 ). Pairwise comparisons (Tukey post hoc) were all signi /uniFB01 cant ( p &lt; 0.001), with mean cortical thickness being lowest in AD (mean 2.28, SD 0.13, 95% CI 0.161 to -0.124) and highest in controls (mean 2.42, SD 0.11, 95% CI 2.415 -2.433), with MCI being intermediate (mean 2.38, SD 0.12, 95% CI -0.054 to -0.029) (eFigure 1, links.lww.com/ WNL/C774). Region-level pairwise group comparisons (total of 148 regions -FDR corrected) provided evidence cortical thickness measures were on average lower in 133 regions in AD vs controls, in 111 regions in AD vs MCI and in 78 regions in MCI vs controls (eFigure 1, links.lww.com/WNL/C774).

Next, cortical thickness Z -scores, derived from comparison to the normative model, were then compared across participant groups. In this way, we could compare the degree to which each group di /uniFB00 ered from the separate reference cohort, used to de /uniFB01 ne the normative model. Consistent

Figure 1 Regional Maps of Heterogeneity

![Image](./Verdi2023_artifacts/image_000005_d5631ca84c3f341eccca12a0f050941496c231f3502a38950842b17b42304647.png)

(A) Mapped are significant group differences of outliers. The color bar indicates effect size as Phi φ (0.1 is considered to be a small effect, 0.3 a medium effect, and 0.5 a large effect). (B) Mapped is the percentage of outliers present within each participant group. The color bar reflects outlier proportion from 2.5% to 100% (thresholding of z -scores). Gray represents that no participants have outliers in those respective regions. AD = Alzheimer disease; MCI = mild cognitive impairment.

with comparisons of mean cortical thickness, age- and sexadjusted Z -scores di /uniFB00 ered between groups overall ( F (2, 1,022) = 69.49, p = 2.0 × 10 -16 ). Pairwise comparisons (Tukey post hoc) were all signi /uniFB01 cant ( p ≤ 0.003), with Z -scores being lowest in AD (mean -1.27, SD 1.41, 95% CI -1.630 to -1.130), highest in controls (mean 0.07, SD 1.04, 95% CI -1.053 to 0.374), and intermediate in MCI (mean -0.28, SD 1.17, 95% CI -0.600 to -0.180) (eFigure 2A, links.lww.com/WNL/C774).

Furthermore, age- and sex-adjusted tOCs di /uniFB00 ered between groups overall ( F (2, 1,022) = 95.39, p = 2.0 × 10 -16 ). Pairwise comparisons (Tukey post hoc) were all signi /uniFB01 cant ( p ≤ 0.003), with tOCs being highest in AD (median 12, interquartile range [IQR] 28, 95% CI 14.38 -19.88), lowest in controls (median 2, IQR 6, 95% CI 2.780 -18.494), and intermediate in MCI (median 4, IQR 9, 95% CI 1.56 -6.18) (eFigure 2B, links.lww.com/WNL/C774).

Region-level pairwise group comparisons (total of 148 regions -FDR corrected) showed higher numbers of outliers in cortical thickness in 79 regions in AD vs controls, in 63 regions in AD vs MCI, and 1 region in MCI vs controls. Region-level group di /uniFB00 erences in outlier count were most evident within temporoparietal and to a lesser extent frontal and occipital regions (Figure 1A).

## Patients With AD Are Less Similar to Each Other Than People With MCI or With Normal Cognition

Hamming distance matrices indicated greater within-group dissimilarity in patients with AD, relative to MCI or control participants, who were most similar to each other in spatial patterns of outliers (Figure 2). The median hamming distance signi /uniFB01 cantly di /uniFB00 ered between groups overall ( F (2, 1,024) = 209.42, p = 2.2 × 10 -16 ). Pairwise comparisons (Tukey post hoc) were all signi /uniFB01 cant ( p &lt; 0.001), with being highest in AD (median 32, IQR 32, 95% CI 26.29 -29.43) and lowest in

Figure 2 Outlier Dissimilarity

![Image](./Verdi2023_artifacts/image_000006_2d157e1056414d3ebe6652ee41d76888684869a68acc9ca85a62914bb5662ab9.png)

(A) Outlier distance heatmaps: both x and y axes represent participants within each group. Yellow indicates higher hamming distance (greater dissimilarity between participants in this brain region), as opposed to if participants are identical in this brain region, the Hamming distance would be 0, represented by white in the color bar. (B) Outlier distance density: illustrates the spread of outlier dissimilarity (calculated by the Hamming distance) within each group. AD = Alzheimer disease; MCI = mild cognitive impairment.

controls (median 6, IQR 8, 95% CI -24.37 to -19.61), with MCI being intermediate (median 10, IQR 14, 95% CI -18.52 to -14.92).

## Patients With AD Have Spatially Higher Proportions of Cortical Thickness Outliers

The proportion of outliers de /uniFB01 ned within each group di /uniFB00 ered in regional patterns between AD, MCI, and control groups. This is illustrated in Figure 1B and in eFigure 3 (links.lww. com/WNL/C774). For a breakdown of proportions, see eTable 2; for individual maps of outliers, see Video 1. A greater number of regions and a higher proportion of the group were outliers in patients with AD, as expected. In fact, 145 regions in the AD group had over the expected 2.5% of patients with an outlier (based on the Z &lt; -1.96 threshold). The left parahippocampal gyrus was the region with the highest outlier percentage (47% of the AD group). For the MCI group, 138 regions in the MCI group had outliers (over the expected 2.5% of the group). The left parahippocampal

![Image](./Verdi2023_artifacts/image_000007_816f5c69dcfd75babec66587b62e021ada91d71578932ec37a06ae12085a9fde.png)

![Image](./Verdi2023_artifacts/image_000008_a28f447a6028959fc96e54ed2ff5ee3b0fe206f02a9c95af3e372a3a3e5af6e8.png)

gyrus was the region with the highest outlier percentage (14% of the MCI group). For the control group, only 66 regions had outliers above the expected 2.5%. The left occipital temporal lateral sulcus was the region with the highest outlier percentage (6% of controls).

## Outliers Are Associated With Cognitive Function and CSF A β and p-Tau

tOC across the whole sample was signi /uniFB01 cantly associated with memory performance ( β = -0.01, p = 2.2 × 10 -16 ) and executive function ( β = -0.02, p = 2.2 × 10 -16 ) in a linear regression model. To check for the association between 2 variables within a sample, we also model a group by tOC interaction term, which was not signi /uniFB01 cant for memory performance ( F (2, 849) = 2.28, p = 0.103) and executive function ( F (2, 849) = 2.534, p = 0.07) (Figure 3, A and B). Lower MMSE scores showed di /uniFB00 erent spatial patterns of outliers in both MCI and patients with AD (Figure 4A) groups. However, total MMSE score and age did explain some of the variance in tOC (adjusted R 2 = 0.1793, p = 2.2 × 10 -16 ). In addition, tOC was signi /uniFB01 cantly associated with A β ( β = 0.002, p = 0.022) and p-tau ( β = 0.1301, p = 1.04 × 10 -8 ), which was not in /uniFB02 uenced by either group A β ( F (2, 576) = 0.96, p = 0.38) or p-tau interaction ( F (2, 576) = 1.362, p = 0.257) (Figure 3, C and D).

## Case Studies Suggest That Variability in Cortical Thickness Is Not Solely Due to Disease Stage or Other Clinical Factors

To explore whether individual di /uniFB00 erences in outlier maps were driven by disease-related characteristics (such as ApoE genotype and demographics) or by disease stage, we examined sets of participants closely matched for ApoE genotype status, age, sex, and MMSE score. Figure 4B presents 4 individual female patients with AD all aged 71 -72 years, heterozygous for ApoE e 4, with similar MMSE scores, all of whom were CSF amyloid positive, with no underlying comorbidities. Furthermore, clinical impressions con /uniFB01 rm that these individuals all have mild dementia, with further con /uniFB01 rmation of no depressive symptoms. These individual patients might be considered similar from biological or clinical perspectives, yet their patterns of outliers in cortical thickness are markedly variable; for example, variously suggesting lateralized (patient 3) and occipital atrophy (patient 1).

## Greater Numbers of Outliers Are Associated With Risk of Conversion From Mild Cognitive Impairment to AD

A survival analysis indicated that for every 10 points of tOC, the risk of converting from MCI to AD within 3 years increased by 31.4% (hazard ratio 1.028, 95% CI 1.016 -1.039, p = 1.8 × 10 -16 )

Figure 3 Cognitive Function and CSF Marker Association With tOC

![Image](./Verdi2023_artifacts/image_000009_0a9171cd5dc7f2255a86750421b2ba7bdcd7c944322e0d750bd6d7330a2a07b9.png)

Fitted lines are from a linear regression model per diagnostics group for (A) memory function, (B) executive function, (C) CSF β -amyloid, and (D) phosphoylated-tau. AD = Alzheimer disease; MCI = mild cognitive impairment; tOC = total outlier count.

![Image](./Verdi2023_artifacts/image_000010_3de77aff5b881db2205b987e7636221413a69dee80aa787d77badb749ab16cce.png)

Mapped is the percentage of region outliers proportional to the MMSE scoring subgroup in (A) participants with MCI and (B) patients with AD. The color bar reflects outlier proportion from 2.5% to 100% (thresholding of z -scores). Gray represents that no participants have outliers in those respective regions. AD = Alzheimer disease; MCI = mild cognitive impairment; MMSE = Mini-Mental State Examination.

![Image](./Verdi2023_artifacts/image_000011_63bc965c3191d1fff7f5a8624adaf60a7dd7d0c5fea491890f0bc3ceaf433cde.png)

(A) Kaplan-Meier plot of MCI to AD conversion: the 2 lines represent a median split of tOC, with &lt;4 classed as low tOC (blue) and ≥ 4 classed as high tOC (red). Crosses indicate censoring points (i.e., age at last diagnosis assessment). The filled color represents the 95% confidence intervals. (B) Mapped is the proportion of regional outliers among people with MCI who converted to patients with AD. AD = Alzheimer disease; MCI = mild cognitive impairment; tOC = total outlier count.

![Image](./Verdi2023_artifacts/image_000012_9d5f243b91807494244f426ffe21ff826182c722f6d3e43f082c28466e7032b4.png)

![Image](./Verdi2023_artifacts/image_000013_e6cc41d6fcfd4b996db17d6e265aa2c58da2caf8600fedaf362f0e9b084638d4.png)

(Figure 5A). This is illustrated within a Kaplan-Meier plot, which shows how a high tOC can contribute to the risk of converting in comparison to a low tOC (Figure 5B).

## Discussion

In this study, we de /uniFB01 ned individual spatial patterns of cortical thickness outliers and illustrated that AD does not a /uniFB00 ect different people in a uniform way. Moreover, our analysis quanti /uniFB01 ed and visualized these individual di /uniFB00 erences in patterns of cortical atrophy. Overall, the results of the present study provide evidence of (1) heterogeneous patterns of cortical thickness between patients with AD, (2) associations of cortical thickness heterogeneity with cognitive performance and CSF A β and p-tau, and (3) the potential of individualized markers of cortical thickness heterogeneity to predict survival time before conversion from the MCI stage to diagnosed AD.

Our /uniFB01 ndings both complement and o /uniFB00 er additional information to the established understanding of AD. We observed a high tOC in patients with AD, consistent with the evidence of cortical thinning as a consequence of AD neuropathology. 38 Moreover, we also observe signi /uniFB01 cant associations with cortical thinning and poor cognitive performance, a decrease in CSF A β , and an increase in CSF p-tau (Figure 3), which is also consistent with previous /uniFB01 ndings. 39,40 Atrophy has also been associated with the risk of progression from MCI to AD 41 (Figure 5), alongside a combination of other biomarkers. 42 Importantly, these previous studies examined the correlates of common patterns of cortical atrophy -yet conversely, we considered individual variability in patterns of cortical thickness, as opposed to assessing group average relationships. This highlights that individualized measures of neuroanatomy are sensitive to both nonimaging disease markers and disease progression.

The tOC has the potential to be used as an individual patient metric of poor brain health to help inform clinical decisions. Indeed, similar measures have recently been adopted as a clinical measure, that is, brain volume/thickness patient Z -scores. However, these have been calculated using di /uniFB00 erent normative modeling techniques, 43,44 which base their normative population on smaller reference samples; limit modeling to just whole brain, or within speci /uniFB01 c regions; and do not account for site-related variation (i.e., site e /uniFB00 ects). These studies also did not fully relate these to clinical outcomes and cognitive scores. Our tOC can provide an optimized measure here and will translate within clinical applications for precision medicine.

When assessing regional heterogeneity of the ADNI sample, we observed more outliers in patients with AD in temporal

regions such as the hippocampus and the cingulate cortex. These are areas known to be sensitive to neurodegeneration in AD 45 and are responsible for clinical symptoms in AD. 46 However, looking beyond these group-average regional differences, we observe that the highest proportion of outliers in a single region was less than 50% in the AD group (Figure 1). This suggests that the individual spatial patterns of outliers in AD only partially overlap between patients; if atrophy were homogenous (as assumed within group averages), we might expect 100% of participants to have outliers here.

The observed variation in atrophy in the temporal lobe is consistent with subtyping studies. 8,47 Also, a recent study used normative modeling to estimate neuroanatomical heterogeneity within the ADNI cohort, which shows similarities of variation in atrophy within the temporal regions. 48 However, in comparison to these studies, our speci /uniFB01 c application of neuroanatomical normative modeling has enabled the creation of an individual metric of neuroanatomical heterogeneity, characterized the spatially distributed nature of alterations in MCI and AD, and assessed how neuroanatomical variability relates to cognitive performance, CSF biomarkers, disease progression, or genetic factors. Furthermore, our study employs a normative modeling technique (hierarchical Bayesian regression), which crucially accounts for the confounding e /uniFB00 ects of multiple scanning sites when evaluating neuroanatomical heterogeneity in AD.

Going further, our study reveals that each patient not only di /uniFB00 ers in the number of outliers they have, but the regional patterns of outliers markedly di /uniFB00 er (Video 1). The latter is re /uniFB02 ected in large levels of dissimilarity between individuals with AD (Figure 2). Potentially, one reason for the variable patterns of atrophy is simply disease stage, whereby more atrophy appears with greater disease progression. However, our results indicate that this is not the case, as when closely examining patients of very similar demographics and clinical characteristics, being at a comparable disease stage (e.g., based on MMSE score), heterogeneous patterns of cortical atrophy were still present (Figure 4).

It is surprising to observe that cognitively normal controls also showed some outliers, suggesting a degree of within-group heterogeneity (Figures 1 and 2). Therefore, the assumption of homogeneity in case-control studies should be made with caution, even in control groups. Statistical designs for basic research and clinical trials should better re /uniFB02 ect this heterogeneity in brain structure.

Afew considerations can be made regarding the data sets used within the study. Although the reference data set includes over 30,000 individuals, we should be cautious to assume that it is representative of a healthy population. Also, patients who volunteer for research studies (i.e., ADNI) do not necessarily re /uniFB02 ect the clinical population. Future neuroanatomical normative modeling studies could supplement the reference data set with MRI scans acquired from routine clinical visits, community cohorts, or other less selective sources. Finally, the reference data were processed with a variety of FreeSurfer versions. While impractical to unify the image processing retrospectively, the di /uniFB00 erent versions of FreeSurfer may potentially add noise to the normative models. This represents an important caveat to consider and further investigate.

As the ADNI comprises more participants with early-stage dementia, examining late-stage patients with AD may o /uniFB00 er insights into the heterogeneity in spatial patterns of atrophy across the disease course. Clinical observations have suggested that late-stage patients with AD have widespread atrophy across the brain; therefore, we may hypothesize such patients will have less heterogeneous patterns of atrophy. However, regardless of the heterogeneous patterns of atrophy, the tOC can still provide information about the extent of cortical atrophy in a given individual.

Another limitation of the ADNI data set is the underrepresentation of cognitive domains beyond memory, executive function, and language. Between a quarter to a third of the AD group exhibit parieto-occipital outliers, comparable to separate parieto-occipital predominant subtypes associated with prominent visuospatial dysfunction, 10 Further characterization of how outlier distribution relates to nonmemory/executive symptoms may be of particular clinical relevance, for example, given the implications of visuospatial dysfunction for diminished autonomy, falls risk, and appropriate services. 17,49,50

Future e /uniFB00 orts when applying neuroanatomical normative modeling to AD data should incorporate serial neuroimaging across multiple time points. This will help de /uniFB01 ne patient-level longitudinal trajectories. Mapping neuroanatomical variability using neuroanatomical normative modeling at di /uniFB00 erent time points has the potential to improve predictions of disease progression or treatment response at the level of the individual patient. Apart from our MCI to AD analysis, the sample taken from this study is cross-sectional, re /uniFB02 ecting a snapshot in time, yet heterogeneity has been shown to di /uniFB00 er temporally. 51 Potentially, data-driven staging methods here (e.g., SusStain 15 ) may also provide clinically useful information of longitudinal trends of individual heterogeneity while taking account of an individual ' s disease stage.

Furthermore, it will also be valuable to map variation using other neuroanatomical metrics, such as subcortical volumes. Our methodology can be extended to include subcortical volumes by using a reference data set that has such data available. 23 Future e /uniFB00 orts that adopt this could enrich our understanding of regional anatomic heterogeneity between patients.

We provide a quantitative approach to estimate variability in brain atrophy at the regional level for individual patients. Individualized maps of neuroanatomical outliers were related to cognitive performance and CSF biomarkers. Furthermore, the number of outliers, based on individual patterns, helped

predict conversion from MCI to AD. These individual neuroanatomical maps, derived from normative models, have the potential to be a marker of AD state. These could index disease progression or even evaluate the e /uniFB00 ectiveness of potential disease-modifying treatments tailored to the individual patient.

## Acknowledgment

Data collection and sharing for this project was funded by the ADNI (NIH grant U01 AG024904) and DOD ADNI (Department of Defence award number W81XWH-12-20012). The ADNI is funded by the National Institute on Aging, the National Institute of Biomedical Imaging and Bioengineering, and through generous contributions from the following: AbbVie; Alzheimer ' s Association; Alzheimer ' s Drug Discovery Foundation; Araclon Biotech; BioClinica, Inc.; Biogen; Bristol-Myers Squibb Company; CereSpir, Inc.; Cogstate; Eisai Inc.; Elan Pharmaceuticals, Inc.; Eli Lilly and Company; EuroImmun; F. Ho /uniFB00 mann-La Roche Ltd and its a /uniFB03 liated company Genentech, Inc.; Fujirebio; GE Healthcare; IXICO Ltd.; Janssen Alzheimer Immunotherapy Research &amp; Development, LLC; Johnson &amp; Johnson Pharmaceutical Research &amp; Development LLC; Lumosity; Lundbeck; Merck &amp; Co., Inc.; Meso Scale Diagnostics, LLC; NeuroRx Research; Neurotrack Technologies; Novartis Pharmaceuticals Corporation; P /uniFB01 zer Inc.; Piramal Imaging; Servier; Takeda Pharmaceutical Company; and Transition Therapeutics. The Canadian Institutes of Health Research is providing funds to support ADNI clinical sites in Canada. Private sector contributions are facilitated by the Foundation for the National Institutes of Health (fnih.org). The grantee organization is the Northern California Institute for Research and Education, and the study is coordinated by the Alzheimer ' s Therapeutic Research Institute at the University of Southern California. ADNI data are disseminated by the Laboratory for Neuro Imaging at the University of Southern California.

## Study Funding

This work was supported by the EPSRC-funded UCL Centre for Doctoral Training in Intelligent, Integrated Imaging in Healthcare (i4health) (EP/S021930/1) and the Department of Health ' s National Institute for Health Research funded University College London Hospitals Biomedical Research Centre. In addition, A.F. Marquand gratefully acknowledges funding from the Dutch Organization for Scienti /uniFB01 c Research via a VIDI fellowship (grant number 016.156.415). J.M. Schott acknowledges the support of Alzheimer ' s Research UK, Brain Research UK, Weston Brain Institute, Medical Research Council, and the British Heart Foundation. K.X.X. Yong is an Etherington PCA Senior Research Fellow and is funded by the Alzheimer ' s Society (grant number 453 AS-JF-18-003).

## Disclosure

The authors report no relevant disclosures. Go to Neurology. org/N for full disclosures.

## Publication History

Previously published at medRxiv doi: 10.1101/2022.06.30.22277053. Received by Neurology September 1, 2022. Accepted in /uniFB01 nal form March 2, 2023. Submitted and externally peer reviewed. The handling editors were Associate Editors Bradford Worrall, MD, MSc, FAAN, and Andrea Schneider, MD, PhD.

## Appendix Authors

| Name                   | Location                                                                                                                                                                                                                | Contribution                                                                                                                                                                                   |
|------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Serena Verdi, MPhil    | Centre for Medical Image Computing, Medical Physics and Biomedical Engineering, University College London; Dementia Research Centre, UCL Queen Square Institute of Neurology, London, United Kingdom                    | Drafting/revision of the manuscript for content, including medical writing for content; study concept or design; and analysis or interpretation of data                                        |
| Seyed Mostafa Kia, PhD | Donders Centre for Cognitive Neuroimaging, Donders Institute for Brain, Cognition and Behaviour, Radboud University, Nijmegen; Department of Psychiatry, University Medical Centre Utrecht, the Netherlands             | Drafting/revision of the manuscript for content, including medical writing for content; major role in the acquisition of data; and analysis or interpretation of data                          |
| Keir X.X. Yong, PhD    | Dementia Research Centre, UCL Queen Square Institute of Neurology, London, United Kingdom                                                                                                                               | Drafting/revision of the manuscript for content, including medical writing for content, and analysis or interpretation of data                                                                 |
| Duygu Tosun, PhD       | Department of Radiology and Biomedical Imaging, University of California, San Francisco                                                                                                                                 | Drafting/revision of the manuscript for content, including medical writing for content, and major role in the acquisition of data                                                              |
| Jonathan M. Schott, MD | Dementia Research Centre, UCL Queen Square Institute of Neurology, London, United Kingdom                                                                                                                               | Drafting/revision of the manuscript for content, including medical writing for content, and analysis or interpretation of data                                                                 |
| Andre F. Marquand, PhD | Donders Centre for Cognitive Neuroimaging, Donders Institute for Brain, Cognition and Behaviour, Radboud University; Department of Cognitive Neuroscience, Radboud University Medical Centre, Nijmegen, the Netherlands | Drafting/revision of the manuscript for content, including medical writing for content; major role in the acquisition of data; study concept or design; and analysis or interpretation of data |
| James H. Cole, PhD     | Centre for Medical Image Computing, Medical Physics and Biomedical Engineering, University College London; Dementia Research Centre, UCL Queen Square Institute of Neurology, London, United Kingdom                    | Drafting/revision of the manuscript for content, including medical writing for content; study concept or design; and analysis or interpretation of data                                        |

## References

1. Richards M, Brayne C. What do we mean by Alzheimer ' s disease? BMJ . 2010; 341(7778):865-867. doi:10.1136/BMJ.C4670
2. Verdi S, Marquand AF, Schott JM, Cole JH. Beyond the average patient: how neuroimaging models can address heterogeneity in dementia. Brain . 2021;144(10): 2946-2953. doi:10.1093/brain/awab165
3. Ringman JM, Goate A, Masters CL, et al. Genetic heterogeneity in Alzheimer disease and implications for treatment strategies. Curr Neurol Neurosci Rep . 2014;14(11):499. doi:10.1007/s11910-014-0499-8

4. Ryan J, Fransquet P, Wrigglesworth J, Lacaze P. Phenotypic heterogeneity in dementia: a challenge for epidemiology and biomarker studies. Front Public Health . 2018;6:181. doi:10.3389/fpubh.2018.00181
5. Goyal D, Tjandra D, Migrino RQ, Giordani B, Syed Z, Wiens J. Characterizing heterogeneity in the progression of Alzheimer ' s disease using longitudinal clinical and neuroimaging biomarkers. Alzheimers Dement (Amst) . 2018;10(1):629-637. doi: 10.1016/j.dadm.2018.06.007
6. Pais M, Loureiro J, Do Vale V, et al. Heterogeneity of cerebrospinal /uniFB02 uid biomarkers pro /uniFB01 les in individuals with distinct levels of cognitive decline: a cross-sectional study. J Alzheimers Dis . 2021;81(3):949-962. doi:10.3233/JAD-210144
7. Bunn F, Burn AM, Goodman C, et al. Comorbidity and dementia: a scoping review of the literature. BMC Med . 2014;12(1):192. doi:10.1186/s12916-014-0192-4
8. Dong A, Toledo JB, Honnorat N, et al. Heterogeneity of neuroanatomical patterns in prodromal Alzheimer ' s disease: links to cognition, progression and biomarkers. Brain . 2017;140(3):735-747. doi:10.1093/brain/aww319
9. Mehta RI, Schneider JA. What is ' Alzheimer ' s disease ' ? The neuropathological heterogeneity of clinically de /uniFB01 ned Alzheimer ' s dementia. Curr Opin Neurol . 2021;34(2): 237-245. doi:10.1097/WCO.0000000000000912
10. Ten Kate M, Dicks E, Visser PJ, et al. Atrophy subtypes in prodromal Alzheimer ' s disease are associated with cognitive decline. Brain . 2018;141(12):3443-3456. doi: 10.1093/brain/awy264
11. Young PNE, Estarellas M, Coomans E, et al. Imaging biomarkers in neurodegeneration: current and future practices. Alzheimers Res Ther . 2020;12(1):49. doi: 10.1186/S13195-020-00612-7/FIGURES/4
12. Dementia: Assessment, Management and Support for People Living with Dementia and Their Carers . National Institute for Health and Care Excellence (NICE); 2018.
13. Habes M, Grothe MJ, Tunc B, McMillan C, Wolk DA, Davatzikos C. Disentangling heterogeneity in Alzheimer ' s disease and related dementias using data-driven methods. Biol Psychiatry . 2020;88(1):70-82. doi:10.1016/j.biopsych.2020.01.016
14. Wen J, Varol E, Sotiras A, et al. Multi-scale semi-supervised clustering of brain images: deriving disease subtypes. Med Image Anal . 2022;75:102304. doi:10.1016/ J.MEDIA.2021.102304
15. Young AL, Marinescu RV, Oxtoby NP, et al. Uncovering the heterogeneity and temporal complexity of neurodegenerative diseases with subtype and stage inference. Nat Commun . 2018;9(1):4273. doi:10.1038/s41467-018-05892-0
16. Lam B, Masellis M, Freedman M, Stuss DT, Black SE. Clinical, imaging, and pathological heterogeneity of the Alzheimer ' s disease syndrome. Alzheimers Res Ther . 2013;5(1):1. doi:10.1186/alzrt155
17. Gra /uniFB00 -Radford J, Yong KXX, Apostolova LG, et al. New insights into atypical Alzheimer ' s disease in the era of biomarkers. Lancet Neurol . 2021;20(3):222-234. doi: 10.1016/S1474-4422(20)30440-3
18. Mohanty R, Mårtensson G, Poulakis K, et al. Comparison of subtyping methods for neuroimaging studies in Alzheimer ' s disease: a call for harmonization. Brain Commun . 2020;2(2):fcaa192. doi:10.1093/BRAINCOMMS/FCAA192
19. Marquand AF, Wolfers T, Mennes M, Buitelaar J, Beckmann CF. Beyond lumping and splitting: a review of computational approaches for stratifying psychiatric disorders. Biol Psychiatry Cogn Neurosci Neuroimaging . 2016;1(5):433-447. doi:10.1016/ j.bpsc.2016.04.002
20. Marquand AF, Kia SM, Zabihi M, Wolfers T, Buitelaar JK, Beckmann CF. Conceptualizing mental disorders as deviations from normative functioning. Mol Psychiatry . 2019;24(10):1415-1424. doi:10.1038/s41380-019-0441-1
21. Marquand AF, Rezek I, Buitelaar J, Beckmann CF. Understanding heterogeneity in clinical cohorts using normative models: beyond case-control studies. Biol Psychiatry . 2016;80(7):552-561. doi:10.1016/j.biopsych.2015.12.023
22. Rutherford S, Kia SM, Wolfers T, et al. The normative modeling framework for computational psychiatry. Nat Protoc . 2021;17(7):1711-1734. doi:10.1101/ 2021.08.08.455583
23. Rutherford S, Fraza C, Dinga R, et al. Charting brain growth and aging at high spatial precision. Elife . 2022;11:e72904. doi:10.7554/ELIFE.72904
24. Bethlehem RAI, Seidlitz J, Romero-Garcia R, Trakoshis S, Dumas G, Lombardo MV. A normative modelling approach reveals age-atypical cortical thickness in a subgroup of males with autism spectrum disorder. Commun Biol . 2020;3(1):486. doi:10.1038/ s42003-020-01212-9
25. Bayer JMM, Dinga R, Mostafa Kia S, et al. Accommodating site variation in neuroimaging data using hierarchical and Bayesian models a preprint. bioRxiv . 2021: 2021.02.09.430363.
26. Kia SM, Huijsdens H, Dinga R, et al. Hierarchical Bayesian regression for multi-site normative modeling of neuroimaging data. Lecture Notes in Computer Science (including subseries Lecture Notes in Arti /uniFB01 cial Intelligence and Lecture Notes in Bioinformatics) . 2020; 12267 LNCS:699-709. doi:10.1007/978-3-030-59728-3\_68/FIGURES/4
27. Kia S, Huijsdens H, Rutherford S, et al. Federated multi-site normative modeling using hierarchical Bayesian regression. bioRxiv . 2021. doi:10.1101/2021.05.28.446120
28. The Alzheimer ' s Disease Neuroimaging Initiative (ADNI). Accessed January 2023. adni.loni.usc.edu.
29. The Alzheimer ' s Disease Neuroimaging Initiative (ADNI). MRI SCANNER PROTOCOLS. Accessed January 2023. adni.loni.usc.edu/methods/documents/mri-protocols/.
30. Hartig M, Truran-Sacrey D, Raptentsetsang D, Simonson A, Mezher A, Schu /uniFB00 N, et al. UCSF FreeSurfer Methods. Accessed January 2023. adni.bitbucket.io/ reference/docs/UCSFFSX51/UCSF%20FreeSurfer%20Methods%20and%20QC\_ OFFICIAL.pdf.
31. Fischl B. FreeSurfer. Neuroimage . 2012;62(2):774-781. doi:10.1016/j.neuroimage.2012.01.021
32. Fischl B, Dale AM. Measuring the thickness of the human cerebral cortex from magnetic resonance images. Proc Natl Acad Sci U S A . 2000;97(20):11050-11055. doi: 10.1073/pnas.200033797
33. Destrieux C, Fischl B, Dale A, Halgren E. Automatic parcellation of human cortical gyri and sulci using standard anatomical nomenclature. Neuroimage . 2010;53(1):1-15. doi:10.1016/J.NEUROIMAGE.2010.06.010
34. Dale AM, Fischl B, Sereno MI. Cortical surface-based analysis: I. Segmentation and surface reconstruction. Neuroimage . 1999;9(2):170-194. doi:10.1006/nimg.1998.0395
35. Monereo S´ anchez JA, de Jong JJA, Drenthen AGS, et al. Quality control strategies for brain MRI segmentation and parcellation: practical approaches and recommendations: insights from the Maastricht Study. bioRxiv . 2021.
36. Mowinckel AM, Vidal-Piñeiro D. Visualisation of brain statistics with R-packages ggseg and ggseg3d. arXiv . 2019. doi:10.1177/2515245920928009
37. Gibbons LE, Carle AC, Mackin RS, et al. A composite score for executive functioning, validated in Alzheimer ' s Disease Neuroimaging Initiative (ADNI) participants with baseline mild cognitive impairment. Brain Imaging Behav . 2012;6(4):517-527. doi: 10.1007/S11682-012-9176-1
38. Julkunen V, Niskanen E, Koikkalainen J, et al. Di /uniFB00 erences in cortical thickness in healthy controls, subjects with mild cognitive impairment, and Alzheimer ' s disease patients: a longitudinal study. J Alzheimers Dis . 2010;21(4):1141-1151. doi:10.3233/ JAD-2010-100114
39. Bakkour A, Morris JC, Wolk DA, Dickerson BC. The e /uniFB00 ects of aging and Alzheimer ' s disease on cerebral cortical anatomy: Speci /uniFB01 city and di /uniFB00 erential relationships with cognition. Neuroimage . 2013;76:332-344. doi:10.1016/ J.NEUROIMAGE.2013.02.059
40. Fortea J, Vilaplana E, Alcolea D, et al. Cerebrospinal /uniFB02 uid β -amyloid and phospho-tau biomarker interactions a /uniFB00 ecting brain structure in preclinical Alzheimer disease. Ann Neurol . 2014;76(2):223-230. doi:10.1002/ANA.24186
41. Querbes O, Aubry F, Pariente J, et al. Early diagnosis of Alzheimer ' s disease using cortical thickness: impact of cognitive reserve. Brain . 2009;132(8):2036-2047. doi: 10.1093/BRAIN/AWP105
42. Popescu SG, Whittington A, Gunn RN, et al. Nonlinear biomarker interactions in conversion from mild cognitive impairment to Alzheimer ' s disease. Hum Brain Mapp . 2020;41(15):4406-4418. doi:10.1002/HBM.25133
43. Goodkin O, Pemberton H, Vos SB, et al. The quantitative neuroradiology initiative framework: application to dementia. Br J Radiol . 2019;92(1101):20190365. doi: 10.1259/bjr.20190365
44. Stelmokas J, Yassay L, Giordani B, et al. Translational MRI volumetry with NeuroQuant: e /uniFB00 ects of version and normative data on relationships with memory performance in healthy older adults and patients with mild cognitive impairment. J Alzheimer Dis . 2017;60(4):1499-1510. doi:10.3233/JAD-170306
45. Popuri K, Ma D, Wang L, Beg MF. Using machine learning to quantify structural MRI neurodegeneration patterns of Alzheimer ' s disease into dementia score: independent validation on 8,834 images from ADNI, AIBL, OASIS, and MIRIAD databases. Hum Brain Mapp . 2020;41(14):4127-4147. doi:10.1002/hbm.25115
46. Leech R, Sharp DJ. The role of the posterior cingulate cortex in cognition and disease. Brain . 2014;137(1):12-32. doi:10.1093/brain/awt162
47. Noh Y, Jeon S, Lee JM, et al. Anatomical heterogeneity of Alzheimer disease Based on cortical thickness on MRIs. Neurology . 2014;83(21):1936-1944. doi:10.1212/ WNL.0000000000001003
48. Pinaya WHL, Scarpazza C, Garcia-Dias R, et al. Using normative modelling to detect disease progression in mild cognitive impairment and Alzheimer ' s disease in a cross-sectional multicohort study. Sci Rep . 2021;11(1):15746. doi:10.1038/s41598-021-95098-0
49. Martin K, Thomson R, Blizzard L, Wood A, Garry M, Srikanth V. Visuospatial ability and memory are associated with falls risk in older people: a population-based study. Dement Geriatr Cogn Disord . 2009;27(5):451-457. doi:10.1159/000216840
50. Perry RJ, Hodges JR. Relationship between functional and neuropsychological performance in early Alzheimer disease. Alzheimer Dis Assoc Disord . 2000;14(1):1-10. doi:10.1097/00002093-200001000-00001
51. Ferreira D, Verhagen C, Hern´ andez-Cabrera JA, et al. Distinct subtypes of Alzheimer ' s disease based on patterns of brain atrophy: longitudinal trajectories and clinical applications. Sci Rep . 2017;7(1):46263. doi:10.1038/srep46263

![Image](./Verdi2023_artifacts/image_000014_f3b756bafc09856bf69cb4c5f57b00a69d64304325ced8ae8ba13d7b766cec26.png)

## Neurology:

## Neuroanatomical Normative Modeling Revealing Individual Neuroanatomical Heterogeneity in Alzheimer Disease Using

DOI 10.1212/WNL.0000000000207298 2023;100;e2442-e2453 Published Online before print May 1, 2023 Neurology Serena Verdi, Seyed Mostafa Kia, Keir X.X. Yong, et al.

## This information is current as of May 1, 2023

Services Updated Information &amp;

http://n.neurology.org/content/100/24/e2442.full including high resolution figures, can be found at:

References

http://n.neurology.org/content/100/24/e2442.full#ref-list-1 This article cites 45 articles, 4 of which you can access for free at:

Citations

http://n.neurology.org/content/100/24/e2442.full##otherarticles This article has been cited by 1 HighWire-hosted articles:

Subspecialty Collections

http://n.neurology.org/cgi/collection/mri

MRI

http://n.neurology.org/cgi/collection/mci\_mild\_cognitive\_impairment

MCI (mild cognitive impairment)

http://n.neurology.org/cgi/collection/alzheimers\_disease

Alzheimer's disease

following collection(s): This article, along with others on similar topics, appears in the

Permissions &amp; Licensing

http://www.neurology.org/about/about\_the\_journal#permissions

its entirety can be found online at: Information about reproducing this article in parts (figures,tables) or in

Reprints

http://n.neurology.org/subscribers/advertise Information about ordering reprints can be found online:

ISSN: 0028-3878. Online ISSN: 1526-632X. Wolters Kluwer Health, Inc. on behalf of the American Academy of Neurology.. All rights reserved. Print 1951, it is now a weekly with 48 issues per year. Copyright Copyright ' 2023 The Author(s). Published by fi is the official journal of the American Academy of Neurology. Published continuously since Neurology

