## nature mental health

## Article

https://doi.org/10.1038/s44220-024-00322-1

## Unraveling the link between CNVs, cognition and individual neuroimaging deviation scores from a population-based reference cohort

Received: 28 February 2024

Accepted: 30 August 2024

Published online: 1 November 2024

![Image](./Fraza2023_artifacts/image_000000_daf95f95faa7e6c4e69a46e0bf952e43c742764b087bf621f028fc8631d97d87.png)

## A list of authors and their affiliations appears at the end of the paper

Copy number variations (CNVs) are genetic variants that can have a substantial influence o  n n  eu  ro  de  ve  lo  pment, n  eu  ropsychiatric traits and morphometric brain changes, yet their impact at the individual level remains unknown. Common case-control approaches for analyzing CNVs suffer from limitations; they are unable to inform on individual variation between carriers and preclude the study of rarer variants due to their limited sample size. Here we aim to map individualized brain deviation scores in individuals with pathogenic CNVs. We used normative modeling to map neuroimaging features from several large neuroimaging datasets and applied these models to understand the neurobiological profile of CNV carriers in the UK Biobank cohort. We highlight the 1q21.1 distal deletion and duplication, as an example of our individual-level normative modeling-CNV approach. Next, we count the number of extreme deviations for each participant from the mean and centiles of variation from population reference norms, giving us a combined risk score per participant per imaging modality. We show a high degree of heterogeneity between pathogenic CNV carriers in their implicated brain regions. For example, the cerebellum, brainstem and pallidum show large negative deviations for specific 1q21.1 duplication carriers. For certain 1q21.1 deletion CNV carriers, the caudate and accumbens show notable positive deviations. Finally, we show that negative deviations from these models are correlated to cognitive function. This study marks a starting point in understanding the impact of pathogenic CNVs on brain phenotypes, underscoring the intricacies of these genetic variations at the individual level and providing a means to study the effects of rare CNVs in carrier individuals.

Check for updates

Copy number variations (CNVs) are genetic variants that can have a large influence on neurodevelopment, neuropsychiatric traits and morphometric brain changes 1,2 . Some CNVs emerge as genetic risk factors for a variety of neurodevelopmental and other psychiatric disorders 3-7 . Specifically, previous studies have shown that certain rare recurrent CNVs increase the risk for schizophrenia 5,8-12 , attention deficit hyperactivity disorder (ADHD) 13 , autism spectrum disorder (ASD) 14,15 and links between a decreased intelligence quotient score and pathogenic CNVs have been established 16 . Despite their known

e-mail: charlotte.fraza@donders.ru.nl

importance, the effects of the majority of individual CNVs on the brain and behavior remain largely unknown. CNVs are typically studied at the group level, which may mask considerable interindividual variation in their brain phenotypic presentation. Furthermore, the group-based approach is only feasible for the more common CNVs, limiting our ability to map the effects of rarer CNVs with high penetrance or large effect sizes. Understanding the effects of specific CNVs on both brain structure and cognition at the individual level is crucial for mapping their impact on mental disorders.

CNVs often have pleiotropic effects, influencing multiple downstream processes simultaneously 17-19 . The diversity in genetic CNVmediated effects is heightened by interactions with both the rest of the genome and environmental factors 20 . This makes each CNV's impact unique to the individual, complicating the task of understanding their contributions to overall mental health. Conventional imaging and behavioral CNV studies often adopt a cases versus controls framework, which has led to tremendous insights. However, to grasp the full spectrum of CNV effects, which manifest in a quite heterogeneous manner, we need to move beyond group-level distinctions 21 .

Understanding the individual-level impact of CNVs on the brain and behavior has posed a challenge, yet the emergence of large-scale normative models may offer a solution. By employing this approach, z scores can be calculated for each individual across various neuroimaging modalities, quantifying individual variations against the mean and centiles of population reference norms. Normative models thereby shift focus from group-level to individual-level inferences 22-25 and allow us to quantify atypical developmental trajectories 26,27 . Normative modeling has proven its efficacy in correlating individual behavioral phenotypes with deviations from reference cohorts, spanning disorders such as schizophrenia 26,28 , neurodivergence for ASD and ADHD 29,30 and mapping disease progression in Alzheimer's disease 31 .

The normative modeling approach is designed to give insight into individual brain deviation scores and their relationship to behavioral factors and mental health. Mental health disorders such as major depression and schizophrenia, are often conceptualized as existing on a gradient of severity and can be seen as extreme values on continuous dimensions 32 . This idea aligns with normative modeling, as it places individuals with large brain deviation scores at the edges of the normative spectrum 33 . Using normative models, we can map brain deviation scores for individuals with a specific pathogenic CNV, allowing us to evaluate their position within the normative population. Afterward, we can use these pathogenic CNV-brain phenotypic deviation scores and correlate them with behavioral traits, uncovering hidden facets that group analyses could miss. Importantly, owing to its focus on individual differences, this approach is not limited to common CNVs; it extends to rarer variants, opening doors to tailored research for smaller populations of rare genetic variants.

In this study, our primary objective is to dive into the individualized impacts of pathogenic CNVs on brain structure and behavior. By using a normative modeling approach, we take a step toward making personalized risk profiles that allow us to do cross-individual comparisons. These profiles prove insightful for individuals with similar genetic mutations that manifest in comparable behavioral effects and share diverse neuroimaging fingerprints. We hypothesize that (1) individuals with a CNV related to cognitive deficits or neurodevelopmental disorders will have larger deviation scores compared with a reference model across several brain areas and (2) that the patterns of deviation across brain regions will be highly variable between these CNV carriers.

## Results

An overview of our analytic workflow is shown in Fig. 1, and a flowchart of the two data samples used in the subsequent analysis is visualized in Fig. 2. Further details on the demographics of the samples can be found in Tables 1 and 2. We focused on 92 CNVs proposed to be pathogenic (henceforth 'pathogenic') and their reciprocal CNVs 34-36 , including both duplications and deletions. All the details of the different analyses described can be found in Methods and Supplementary Information. In addition, the models used for all analyses are accessible at GitHub via https://github.com/amarquand/PCNtoolkit and https://github. com/CharFraza/CNV\_normative\_modeling/.

Initially, we fit a normative model to each of the image-derived phenotypes (IDPs) derived from the structural, functional and diffusion measures from the UK Biobank. The IDPs give global summary measures for each modality and have been previously publicly released 37 .

Specifically, we were interested in the differences between participants who had pathogenic CNVs in comparison with those without. The detailed results from this IDP normative model can be explored in Supplementary Figs. 1, 2 and 3. The resulting models explained as much

̂

<!-- formula-not-decoded -->

̂

representing the true values and yi the predicted values, var representing the variance, using the covariates age, sex and site. By considering these covariates, any residual variation in the model is largely uncorrelated with them and is probably attributable to other variables. A notable observation is that structural measures appeared most informative when we compared individual deviation scores across the different IDPs. In Supplementary Figs. 2 and 3, we can see that the structural measures gave a larger spread of the number of extreme deviations compared with other measures, which indicates a larger variation in individual differences.

Recognizing the importance of structural measures, we mapped a voxel-based morphometric variation model to characterize these differences at a finer scale. To characterize morphometric differences, we used the Jacobian determinant of the deformation field which describes regional contractions or expansions necessary to align individuals to a common reference template (Methods), which is a well-validated approach for understanding neuroanatomical differences (Fig. 3). The resulting models accounted for as much as 52% of the variation in morphometric changes, using the covariates age, sex and site. To validate the models we used several model fit criteria. Among these were the kurtosis and skewness of the resulting z -score distribution, which measures the effectiveness of the warping function in capturing the nonlinearity and non-Gaussianity of the data 38 . In general, all voxels show relatively low skew (that is, |skew| &lt;1) and acceptable excess kurtosis (&lt;5).

## Individual risk profiles-combining pathogenic CNVs and brain imaging deviation scores

Next, we aim to characterize the individual variations in brain deviation scores associated with specific pathogenic CNVs. To accomplish this, we counted the number of extreme deviations (| z | &gt;2) for each individual. An overview of the average counts of negative and positive deviation scores across all pathogenic CNVs is shown in Supplementary Fig. 4.

Afterward, we constructed individualized risk profiles for 1q21.1 distal deletion (1q21.1del) and duplication (1q21.1dup). We chose this CNV to highlight our method, as it has shown moderate to strong effects on cognition 4,35 , a dose-response per copy number for head circumference 39 , with microcephaly in deletion carriers and macrocephaly in duplication carriers and has been associated with global cortical surface structure changes 40 . Furthermore, individuals with a 1q21.1 deletion and duplication show an increased risk for several neurodevelopmental disorders 3,4,39,41,42 . Figures 4 and 5 depict the number of positive and negative deviation scores for individuals with a deletion or duplication, respectively, in comparison with participants without pathogenic CNVs. In addition, these figures showcase deviation score brain maps for individuals with a pathogenic CNV, highlighting regions that have pronounced positive or negative deviations compared with a reference cohort, demonstrating a high degree of variability of volumetric alterations across carriers.

Figures 4b and 5b show areas with marked negative or positive Jacobian signals or localized volumetric alterations. The interpretation of positive and negative deviations can vary depending on the mean of the normative model. For example, when the deviation is positive compared to the mean, it indicates more expansion and a relatively small volume in that voxel compared with the norm. We can see from Figs. 4b and 5b that positive deviations, that is, more volume expansions than predicted, were more present for participants with a 1q21.1del, and negative deviations, that is, more volume contractions than predicted by the model, were more prevalent for participants

a

## Analytic workflow

![Image](./Fraza2023_artifacts/image_000001_b95ebe4c1dcf79618dcaa64f9d03f3b2cd41255ea17feaffe756ceeb8ee3093d.png)

b

(i) UKB-Image-derived phenotypes

Neuroimaging and genetic datasets

(ii) T1 Jacobians

Fig. 1 | Overview of study design and data resources. a , A schematic overview of the study workflow and hypothesis. First, we quantified the number of participants with pathogenic CNVs, previously linked to neurodevelopmental and psychiatric disorders. Then, we created normative models for the IDPs and, afterward, the voxel-wise Jacobians. We calculated the number of large deviation scores (| z | &gt; 2). We plotted the number of large deviation scores for individuals with pathogenic CNVs compared with the rest of the population. Finally, we correlated the extreme brain deviation scores of the Jacobian measures with a general cognitive ability score. BLR, Bayesian linear regression. b , An overview

![Image](./Fraza2023_artifacts/image_000002_7cf8b265acca0a66266d2b91614de9854c14dd6addfb06a8ebe41e7339775d78.png)

![Image](./Fraza2023_artifacts/image_000003_1b8fa83e1c6e36c707990e6afca54a57feedd5576aac7137fec3041effed4356.png)

of the neuroimaging datasets used in this study. (i) A distribution of the IDPs present in the UK Biobank dataset, derived from functional, structural and diffusion tensor imaging. (ii) Distributions of the data from seven sites used in the Jacobian normative model, split by sex. In total, for the IDP study, we used 43,893 participants, and for the Jacobian-voxel-based study, we used 19,620 visually quality-controlled participants. Magnetic resonance imaging (MRI); functional MRI (fMRI); white matter (WM); fractional anisotropy (FA); tensor mode (MO); ICVF, intra-cellular volume fraction; ISOVF, isotropic or free water volume fraction; orientation dispersion (OD).

with a 1q21.1dup. For the detailed brain maps of all the participants with a 1q21.1del or 1q21.1dup see Supplementary Figs. 5 and 6, the most implicated brain areas, calculated using the mean deviation score per ROI, are also summarized in word clouds.

While our study is primarily designed to unravel individualized pathogenic CNV effects, we also explored the potential for aggregating subjects with similar CNVs to uncover converging brain structural alterations across pathogenic CNVs. This will allow us to also make

Fig. 2 | Flowchart number of individuals used at each stage of the study. We report the number of participants for analysis 1, focusing on IDPs, and analysis 2, concerning whole brain T1 Jacobians. *Quality control for the IDPs was performed previously by the UK Biobank 37 . **Visual quality control for the reference Jacobian datasets has been performed previously and is detailed in the following study 24 .

![Image](./Fraza2023_artifacts/image_000004_46942fdfddb8db18ab927bbad7a9e68df4551783b9e68d99fb4910febce078a7.png)

comparisons with traditional case-control studies. We conducted a joint analysis for the 1q21.1del and 1q21.1dup groups, respectively, to examine the common distribution of deviation scores across all subjects. We expect that there is convergence in deviation scores amongst the individual subjects in certain brain regions 43 , especially those that can be found with traditional cases versus control paradigms. However, we also expect divergence amongst the subjects in deviation scores in other brain regions, as we know that not every participant with a pathogenic CNV is implicated in their behavioral phenotype or cognition. For example, a participant can have a pathogenic CNV and still have a standard cognitive performance. The analysis revealed prominent deviations in specific brain regions (Fig. 6). Notably, for 1q21.1dup, substantial negative deviations can be seen in the occipital cortex, while for 1q21.1del, pronounced positive deviations were observed in the cerebellum and thalamus.

The choice of focusing on one CNV gives us the ability to give a full overview and visualization of the implications specifically associated with this CNV. Nonetheless, we have also included results for three other CNVs-15q11.2 deletion and duplication, 16p11.2 deletion and duplication and 16p13.11 deletion and duplication-in Supplementary Information for interested readers. These CNVs were selected on the basis of prior research indicating their impact on neurodevelopment and cognition 21,43,44 .  Supplementary Figs. 9-14 show individualized risk profiles for the pathogenic CNVs 15q11.2 deletion and duplication, 16p11.2 deletion and duplication and 16p13.11 deletion and duplication. In addition, aggregated brain maps for these pathogenic CNVs are shown in Supplementary Figs. 15-17, demonstrating the versatility of our approach in analyzing a variety of rare pathogenic CNVs.

Table 1 | Demographics for analysis 1 (IDPs)

| Sample      | N      | Sex  (F%/M%)   | Mean age  (s.d.)   | Age  range  (years)   | Ethnicity (%)                                  |
|-------------|--------|----------------|--------------------|-----------------------|------------------------------------------------|
| UKB-IDP     | 43,893 | 52.4/47.6      | 60.97 (8.72)       | 44-82                 | 96.75 W/0.44 M/  1.05 A/0.65 B/  0.30 C/0.53 O |
| UKB-IDP-CNV | 263    | 54.4/45.6      | 62.97 (8.70)       | 46-80                 | 96.96 W/1.14 M/  1.14 A/0.00 B/  0.38 C/0.00 O |

The ethnicity data are collected as self-reported data by the UK Biobank (UKB) with the labels 'M' for mixed, 'A' for Asian, 'B' for Black, 'C' for Chinese, 'W' for white and 'O' for other. F%, percentage females; M%, percentage males; s.d., standard deviation.

## Relationship brain deviation scores and cognitive deficits

Extended Data Fig. 1 outlines our hypothesis concerning the impact of pathogenic CNVs on cognition, proposing that certain CNVs might contribute to cognitive impairment in the absence of protective factors. We plotted the fluid intelligence scores among participants with pathogenic CNVs and participants without pathogenic CNVs in Extended Data Fig. 1a. To analyze the relationship between large deviation scores and cognition, we generated a general cognitive ability score for each participant by calculating the first principal component from the various cognitive tests within the UK Biobank dataset (see Supplementary Fig. 7 for an overview of the tests used). We examined the Spearman correlation between the total count of extreme positive deviation scores ( z &gt; 2) and the general cognitive ability score ( r = 0.03, P = 0.09), the extreme negative deviation scores ( z &lt; -2) and the general cognitive ability score ( r = -0.04, P = 0.04) (Extended Data Fig. 1). This correlation indicated that a higher number of extreme negative deviations, indicating more volume contractions compared to the mean of the population, were significantly associated with a lower general cognitive ability score. In Supplementary Figs. 18-21, we included the outcomes of Spearman correlation analyses between the total count of extreme positive and negative deviation scores and various other behavioral variables available in the UK Biobank, for the interested reader.

## Discussion

## Individualized risk profiles through normative models

We established normative reference models from IDPs to identify the neuroimaging modality most influenced by the pathogenic CNVs. Our findings highlight that structural measures appeared more informative. Following this, we utilized a whole-brain Jacobian model to map deviation scores with voxel-wise precision. For each participant with a pathogenic CNV, this allowed us to pinpoint the brain regions showcasing the most pronounced deviations. Expanding on this, we counted the total positive and negative deviation counts and juxtaposed them against the broader population's risk scores. As we expected, certain participants with a pathogenic CNV displayed an elevated number of positive or negative deviations compared with the general population, while others demonstrated deviation scores that aligned more with the 'norm'. Finally, we examined how the deviation scores are associated with general cognitive ability. We chose cognitive ability as our primary behavioral measurement because it has been consistently shown to be impaired within individuals with certain pathogenic CNVs 16,45 and, thus, allows us to establish a biologically meaningful connection between brain deviation scores and impaired behavior. Our analysis revealed a significant negative correlation between the count of negative deviations and general cognitive ability.

Using normative models to understand how pathogenic CNVs affect the brain has important benefits. It helps uncover hidden insights that group analyses might miss. Importantly, this approach focuses on individual differences, so it is not limited to common CNVs but can be applied to rarer variations, making it possible to do research

Table 2 | Demographics for analysis 2 (whole-brain T1 Jacobians)

| Sample   | N      | Sex  (F%/M%)   | Mean age  (s.d.)   | Age  range  (years)   | Ethnicity (%)                                  |
|----------|--------|----------------|--------------------|-----------------------|------------------------------------------------|
| Cam-CAN  | 656    | 50.6/49.4      | 54.93 (18.60)      | 18-89                 | 95.9 W/1.37 M/  1.37 A/0.30 B/  0.15 C/0.76 O  |
| HCP      | 1,112  | 54.5/45.5      | 28.80 (3.70)       | 22-37                 | 76.1 W/13.6 B/  2.3 M/6.2 A/1.8 O*             |
| OASIS 3  | 2,144  | 56.8/43.2      | 70.60 (9.52)       | 43-97                 | 84 W/15 B/  0.004 A*                           |
| PNC      | 1,296  | 51.9/48.1      | 14.37 (3.45)       | 8-21                  | 46.37 W/41.82 B/  2.16 M/9.65 O                |
| UKB      | 14,412 | 52.6/47.43     | 62.50 (7.48)       | 44-80                 | 97.05 W/0.42 M/  0.95 A/0.52 B/  0.31 C/0.43 O |
| UKB-CNV  | 375    | 55.4/44.5      | 63.58 (7.48)       | 46-80                 | 96.27 W/1.10 M/  1.60 A/0.00 B/  0.53 C/0.27 O |

The ethnicity data are summarized with the labels 'M' for mixed, 'A' for Asian, 'B' for Black, 'C' for Chinese, 'W' for white and 'O' for other. UKB, UK Biobank. *Ethnicity estimation was derived from previous work for the HCP sample in ref. 66 and for the OASIS 3 sample in ref. 67. F%, percentage of females in the sample; M%, percentage of males in the sample; s.d., standard deviation.

for smaller groups with uncommon genetic variants. For each person with a common or rare pathogenic CNV, we can identify the specific brain areas where they differ significantly from the norm. This allows us to see which brain regions are affected in each case, going beyond studies that need large sample sizes and can only look at more common CNVs.

## Individualized risk profiles for 1q21.1 distal CNV

We created individualized risk profiles for both carriers of the 1q21.1del and 1q21.1dup from the deviation scores. Participants with this CNV exhibit a diverse range of impaired traits 39,46 , which aligns with our proposal of an individualized approach. Notably, 1q21.1 CNV is associated with several, different neurodevelopmental disorders 2,4 . For participants with a 1q21.1 deletion or duplication, we counted instances of extreme positive and negative deviation scores, contrasting these frequencies with the broader population. Subsequently, we generated brain maps alongside these deviation scores to highlight the regions of the brain where deviations were most prominent. We aggregated the deviation scores across participants with duplications and separately for those with deletions. This allowed us to identify brain regions where the effects converged across individuals and we can then subsequently compare it with previous literature that uses case-control setups.

For the Jacobian normative model, we quantify individual deviations from the mean volumetric change for a specific voxel. In this context, negative deviations refer to instances where certain brain regions show more volume contractions relative to the mean value of the Jacobian normative model. Put simply, these deviations might indicate that a specific brain region had a larger volume originally than what the normative model predicts for a typical voxel. The Jacobian image subsequently corrects for these differences. Positive deviations refer to cases where certain brain regions exhibit more volume expansions relative to the mean value of the Jacobian normative model. These deviations could represent an original lower brain volume in certain voxels compared with the predicted or typical voxel. Interestingly, individuals with a 1q21.1 duplication showed more negative deviations; thus, these individuals on average have more volume contractions than expected by the model, reflecting larger intracranial volume and macrocephaly 40 in 1q21.1 distal duplication carriers. In contrast, 1q21.1 deletion carriers showed more positive deviations, which indicates that these participants have relatively more voxels with a lower brain volume compared with the mean of the population, which might reflect their smaller intracranial volume and microcephaly 40 . In other words, we identified more positive deviation scores in deletion carriers and more negative deviation scores in duplication carriers, reflecting previous findings of dosage effects on the brain of the 1q21.1 distal carriers.

Previous literature has also revealed various effects associated with the 1q21.1 distal CNV on the brain, including positive dosage effects on ICV and total cortical surface area, particularly in the frontal and cingulate cortices, and negative dosage effects on caudate and hippocampal volumes 40 . Another study found higher intraindividual variability in brain structure in 1q21.1 distal CNV carriers, with distinct regional effects on cortical surface area and thickness. In addition, 1q21.1del carriers exhibited reduced global cortical surface area, impacting primarily frontal and association cortices 21 . Moreover, this CNV is linked to a high prevalence of micro- and macrocephaly in deletion and duplication carriers, respectively 39,46 . From our results, we can see that the dosage effects of the 1q21.1 distal carriers remain the same. This means that a duplication of the copy number of the 1q21.1 region is associated with more negative deviations in the Jacobian, which indicates an increase in volume of certain brain structural features, and the 1q21.1 deletion is associated with more positive deviations in the Jacobian, indicating a relative decrease in volume of certain brain regions.

In general, our study shows different regions that are implicated for different participants, for example, the cerebellum, brainstem and pallidum show large negative deviations for certain 1q21.1dup carriers. For certain 1q21.1del CNV carriers, the caudate and accumbens show notable positive deviations. A recent multivariate analysis of eight CNVs revealed that the cingulate gyrus, insula, supplementary motor cortex and cerebellum were the top regions contributing to shared alterations across the CNVs 43 . This overlaps with our findings that highlight the cerebellum in several 1q21.1 distal carriers. Likewise, in our study, for certain 1q21.1del CNV carriers, the caudate and accumbens show notable positive deviations, also overlapping with previous findings 40 . The reason the implicated regions we identify might differ slightly from previous studies could be that we focused on individual variations from the norm of the population, rather than comparing the average differences between cases and controls. Moreover, although there is some overlap between studies, our study group represents a set of CNV carriers with a somewhat different profile from those studied previously.

## Toward personalized psychiatry-individualized risk profiles and beyond

When interpreting the normative model outputs, a common pitfall is to default to a case-control thinking paradigm. This interpretation often categorizes individuals into groups, emphasizing group patterns or seeking group effects instead of individual-level results. While brain deviation score maps can be superimposed to uncover commonalities among subjects with identical pathogenic CNVs, it is not a necessity. In our pursuit of understanding pathogenic CNVs and their effects on cognitive functioning and mental health, it is essential to recognize that we cannot solely rely on aggregated group-level data. While grouping subjects that exhibit similar behavioral phenotypes or possess the same CNV can provide insights into convergence points, this approach overlooks the diversity in the effects of pathogenic CNVs on brain structures and behaviors. For instance, those with a CNV linked to cognitive deficits might range from typical cognitive functioning to severe impairment 34,35,47 . Arguably, the starting point should be individual patient risk profiles, including all the known risk factors, environment, genetics and lifestyle, among others. Once we curate these profiles against a reference population, we can start to understand the implications of pathogenic CNVs at an individual level.

Normative modeling results

Fig. 3 | Overview of normative modeling results. a , Performance metrics for the test set; showing the explained variance, mean standardized log loss (MSLL), kurtosis and skewness. Both skew and kurtosis serve as indicators of the model's accuracy in estimating shape via warped Bayesian linear regression.

![Image](./Fraza2023_artifacts/image_000005_23b7648729379c09e1a6369fc566d5fd39e3e2a3ef1d3d6f77bee49a694ea146.png)

b , A depiction of varied normative trajectories across distinct voxels, showing in the corner the histogram of z scores, with the accompanying whole brain explained variance ( R Ţ ) map, split based on sex.

Fig. 4 | Individual risk profiles 1q21.1 deletion. On the left-hand side, an overview of the extreme value calculations from a normative model is presented. First, a normative model with a specific individual is shown in red, demonstrating positive deviation scores. Afterwards, the voxels with an extreme value are calculated when they surpass a threshold of | z | &gt; 2 as indicated by the red dotted line. a , The prevalence of pathogenic CNV carriers including 1q21.1del in the UK Biobank neuroimaging dataset used in Jacobian analysis. b , Counts of extreme positive and negative deviation scores (| z | &gt; 2) among participants with a 1q21.1 deletion ( n = 5) in contrast to participants without a pathogenic CNV ( n = 9617). Left: the box plots show the distribution of counts of extreme positive scores for no-CNV (0) (minimum 3.0, maximum 58,249.0 and median 3,568.0; Q1 1,783.0 and Q3 6,774.0; whisker low 3.0 and whisker high 14,260.5) and with a 1q21.1 deletion (minimum 773.0, maximum 16,248.0 and median 3,554.0; Q1 3,304.0

![Image](./Fraza2023_artifacts/image_000006_b65b9c132649e1abe953f2398c5a44d9cc4127d17c8de096e6df6cc1ce429200.png)

and Q3 12,415.0; whisker low 773.0 and whisker high 16,248.0) and of counts of extreme negative scores for no-CNV (0) (minimum 0, maximum 187,415.0 and median 3,133.0; Q1 1,407.0 and Q3 6,266.0; whisker low 0 and whisker high 13,554.5) and with a 1q21.1 deletion (minimum 236.0, maximum 5,378.0 and median 3,080.0; Q1 1,655.0 and Q3 5,153.0; whisker low 236.0 and whisker high 5,378.0). The box represents the central 50% of the data, with a line indicating the median value. The whiskers indicate the data outside the central 50%, extending from the quartiles to 1.5 times the interquartile range, showing the spread of the data. The frequency plots show the number of extreme positive deviations for the reference cohort and the dots show each 1q21.1 deletion carrier's specific position in the distribution. Right: the profile of three selected 1q21.1del CNV carriers. No-CNV, participants without CNVs; Q1, first quantile; Q3, third quantile.

score

z

Frequency

Individual risk profile

Ss

@

A. Creating individual deviation scores per voxel

z

score

¥

B. Count voxels with large

z

score

Voxels

C. Compare individuals with

CNV with population

Number large deviations

20

30

40

50

Number participants with CNV

Fig. 5 | Individual risk profiles 1q21.1 duplication. On the left-hand side, an overview of the extreme value calculations from a normative model is presented. First, a normative model with a specific individual is shown, showing negative deviation scores. Afterwards, the voxels with an extreme value are calculated when they surpass a threshold of | z | &gt; 2 as indicated by the red dotted line. a , The prevalence of pathogenic CNV carriers including 1q21.1dup in the UK Biobank neuroimaging dataset used in Jacobian analysis as indicated by the large blue bar. b , Counts of extreme positive and negative deviation scores (| z | &gt; 2) among participants with a 1q21.1 duplication ( n = 5) in contrast to participants without a pathogenic CNV ( n = 9,617). Left: The box plots show the distribution of counts of extreme positive scores for no-CNV (0) (minimum 3.0, maximum 58,249.0 and median 3,568.0; Q1 1783.0 and Q3 6,774.0; whisker low 3.0 and whisker high 14,260.5) and with a 1q21.1 duplication (minimum 2,068.0, maximum 11,464.0 and median 2,845.0; Q1 2,618.0 and Q3 3,999.0; whisker low 2,068.0 and whisker

![Image](./Fraza2023_artifacts/image_000007_d3847308ec30b55e4d1c523419047a606b66ca985360c6880c1abee80cbc7d3b.png)

high 6,070.5) and counts of extreme negative scores for no-CNV (0) (minimum 0, maximum 187,415.0 and median 3,133.0; Q1 1,407.0 and Q3 6,266.0; whisker low 0 and whisker high 13,554.5) and with a 1q21.1 duplication (minimum 785.0, maximum 8,974.0 and median 4,210.0; Q1 2,479.0 and Q3 7,270.0; whisker low 785 and whisker high 8,974). The box represents the central 50% of the data, with a line indicating the median value. The whiskers indicate the data outside the central 50%, extending from the quartiles to 1.5 times the interquartile range, showing the spread of the data. The frequency plots show the number of extreme negative deviations for the reference cohort and the dots show each 1q21.1 duplication carrier's specific position in the distribution. Right: the profile of three selected 1q21.1dup CNV carriers. The prevalence of pathogenic CNV carriers including 1q21.1dup in the UK Biobank neuroimaging dataset used in Jacobian analysis. No-CNV, participants without CNVs; Q1, first quantile; Q3, third quantile.

a

CNV type

CNV 1q21.1dup prevalence UK Biobank

Number participants CNV and neuroimaging data

3q29del

15q11.2del,16p12.1dup

2q13del(NPHP1),13q12.12dup

22q11.2dup

8p23.1dup

13q12.12del

15q11q13del\_BP3-BP4(APBA2,TJP)

17p13.3(YWHAE)del

16p11.2dup

WBS\_dup

2q13dup(NPHP1),15q13.3dup(CHRNA7)

2q11.2del

15q11.2dup,15q13.3dup(CHRNA7)

16p11.2del

15q11.2del,16p13.11dup

2q13dup

15q13.3dup

7q11.23dup\_distal

15q11q13dup\_BP3-BP4(APBA2,TJP)

2q11.2dup

17p12dup(CMT1A)

16p12.1del

NRXN1del

16p13.11del

1q21.1dup

1q21.1del

13q12del(CRYL1)

16p12.1dup

TAR\_dup

16p13.11dup

13q12.12dup

17p12del(HNPP)

15q11.2del

15q11.2dup

2q13dup(NPHP1)

15q13.3dup(CHRNA7)

2q13del(NPHP1)

Duplication

60

70

0

10

Mean deviations normative model

Fig. 6 | Convergence of positive or negative deviation scores for 1q21.1del or 1q21.1dup. The mean brain deviation score maps for participants with the 1q21.1del or 1q21.1dup CNV. On the x axis, we show different sagittal slices with steps of ten.

![Image](./Fraza2023_artifacts/image_000008_61d7e4a898f649d85d52576d3e2c36c699496d545d6921103c4c28ce6d20d8a4.png)

Adopting an 'individual patient first' approach reshapes our perspective on psychiatry, emphasizing that no two mental disorders are truly identical. Traditional psychiatric diagnoses have been classified into separate mental disorders, each presumed to have distinct origins and symptomologies. However, we have come to realize that such boundaries are far from clear cut. Most patients with one diagnosis often have one or more comorbid conditions 22 . This wide-ranging clinical manifestation, coupled with multifactorial etiological risk factors and comorbidities, underscores that most psychiatric disorders do not correspond to single disease entities. Initiatives such as the Research Domain Criteria 48 have aimed to transition to more dimensional approaches, and current categorizations such as the Diagnostic and Statistical Manual of Mental Disorders, Fifth Edition (DSM-V) persist, even amidst challenges such as coexisting conditions and the complexity of categorizing patients. In this study, we make a starting point toward understanding the individual implications of pathogenic CNVs on several brain phenotypes, cognition and mental health.

## Addressing the limitations of genetic studies

One limitation of our study is the lack of longitudinal imaging data for the pathogenic CNV carriers, particularly within the younger age range. While our model covers the entire age range, our conclusions are constrained to participants within the older age range due to the limited availability of CNV data to only the UK Biobank. As we know that CNVs play a large role in neurodevelopment 10,40 , it is important to track participants with these CNVs longitudinally during the age range where their influence is the largest. Furthermore, we acknowledge that, in the study, the individual deviation scores might be partly driven by effects outside of the CNVs, for instance, the remaining genome. Research has shown that environmental factors can lead to molecular 'scars', impacting brain function over time and contributing to disorders such as schizophrenia 20 . For instance, individuals with a 1q21.1del or 1q21.1dup have a higher risk for cognitive impairment, schizophrenia and micro/macrocephaly 49 . Nevertheless, this CNV may also be present in individuals who are otherwise healthy. Therefore, it is probable that the expression of mental disorders associated with this CNV is influenced by a combination of environmental factors and an individual's lifestyle. Epigenetic pathways can offer a valuable perspective for studying these factors. While initial efforts to understand brain-environment-genetic interactions have started 50 , an important question remains: what the role of CNVs is in this complex picture. Currently, we are developing longitudinal normative models 26,51 , which will help us understand critical time frames and can be used to model the interplay between genetics, environment and brain function. In future investigations, we aim to integrate the normative modeling framework with these longitudinal samples to examine the dynamics of genetic markers and environmental influences over time.

Another limitation of current normative or reference-based studies is the disproportionate representation of individuals of European ancestry in our reference samples 25,52 . We attempt to make this bias explicit in our study by including this information within the demographic tables of our reference samples. However, it remains evident that our reference models are likely not representative of individuals with diverse ancestral backgrounds beyond those in the reference sample. This limitation reduces the generalizability of our models until larger more diverse datasets become available.

Addressing these complexities is crucial for advancing our understanding of genetic influences on mental health, requiring a multidimensional approach that integrates genetics, environment and developmental aspects. The present study marks an initial step toward unraveling the impacts of pathogenic CNVs on the brain and cognition at an individualized level.

## Methods

Ethical approval for all the data used in this study was attained previously by the studies contributing their datasets. All participants gave written informed consent and received compensation for their contributions. The details of all the studies and data can be found in the publicly available repositories' main publications: the Cambridge Centre for Ageing and Neuroscience (Cam-CAN) 53 , Human Connectome Project (HCP) 54 , Oasis 55 , Philadelphia Neurodevelopmental Cohort (PNC) 56 and UK Biobank 37 (under application number 23668).

## Study workflow and data summary

Figure 1a visually outlines our study workflow, of which the details can be found below. We first mapped participants with pathogenic CNVs in the UK Biobank and placed them in our test set of normative models. We created normative models for each individual IDP or voxel, taking into account covariates age, site and sex. We counted the number of extreme deviations for each participant (| z | &gt; 2) and mapped where participants with a pathogenic CNV lay on this distribution, giving us a combined risk score per participant per imaging modality. We correlated the total number of extreme Jacobian z scores with a measure of general cognitive ability. In Fig. 1b, we show an overview of

the datasets used, encompassing IDPs from the UK Biobank and Jacobian data from seven sites, details can be found in Supplementary Information. In total, we used 44,456 participants in the IDP study, and in the Jacobian-voxel-based study, 19,620 participants passed visual quality control. There were 375 individuals with pathogenic CNVs in the qualitycontrolled Jacobian neuroimaging dataset. In Figs. 4a and 5a, the final pathogenic CNV sample used for the Jacobian normative model study is shown.

## Normative modeling of IDPs and voxel-based morphometry

To discern whether certain neuroimaging modalities exhibited more atypicalities than others, we first employed normative modeling to analyze the preestimated IDPs from the UK Biobank study 37 , covering diverse functional, structural and diffusion tensor imaging measures. In total, we used data from 44,456 participants from the UK Biobank and 2,084 IDPs, which were processed using FUNPACK (v.2.8) 57 , developed by the Wellcome Centre for Integrative Neuroimaging and used for automatic normalization, cleaning and parsing. From this initial study, we found that the IDPs derived from the structural measures contained the largest variation between deviation scores among participants (Results).

To take this result forward, we aim to explore a whole-brain voxelbased model based on structural T1 data and to map in a spatially precise manner the individual-level effects of pathogenic CNVs within the brain. More specifically, we fitted a voxel-based morphometric variation model using Jacobian determinant images derived from the nonlinear image registration to the MNI152 space. Jacobians, in their essence, provide a spatially precise measure of voxel-based morphometric differences, capturing the extent of volumetric adjustments-either expansion or contraction-needed to align each sample with the registration template for each voxel. These determinants are more informative compared with other derived measures 58 and describe aggregate differences, avoiding the partly arbitrary distinction between gray and white matter. Moreover, it is well established that specific CNVs influence intracranial volume (ICV) 40,59 . Consequently, we anticipated that this influence would manifest as either an increase or decrease in the necessary volumetric adjustments for individuals with these CNVs, which would be reflected in their Jacobians. Typically, a normative model can be constructed solely on the basis of the age range of interest. If we apply this method, we would focus on the 40-80 year age bracket where CNV carriers are located. However, for normative curves, it is advisable to develop a normative model covering the entire age spectrum. This ensures that we make an accurate estimation even at the edges of the age range of interest, by aligning the estimated centiles with a wider age range and, thereby, reducing uncertainty in the centile estimations.

## Participant demographics and data sources

To create the voxel-wise Jacobian normative model that spans the entire age range, we pooled a large dataset from five publicly available repositories: Cam-CAN 53 , HCP 54 , Oasis 55 , PNC 56 and UK Biobank 37 , leveraging Jacobian determinant images from nonlinear image registration, specifically via the anatomical processing tools found in the FMRIB Software Library. The details of the quality control, preprocessing steps and data resources can be found in Supplementary Information. In total, we used visually quality-controlled T1 Jacobian data from 19,620 participants from seven sites. To indicate how representative the samples are, in Tables 1 and 2 we present an overview of the demographics of the samples employed in this study, including age, sex and number of participants per dataset. The ethnicity data presented in the studies originate from self-reported data from the publicly available datasets from which the samples were derived. We decided to use the same ethnicity categories as the UK Biobank, according to https:// biobank.ctsu.ox.ac.uk/crystal/field.cgi?id=21000, which included the categories 'W' for white, 'B' for Black, 'A' for Asian, 'M' for mixed and 'O'

for other. These categories are quite general and, therefore, heterogeneous in the populations they represent; for example, the label Asian can indicate both South and East Asian backgrounds.

For the genetic CNV data, we focused on the 92 CNVs proposed to be pathogenic, which can be requested from https://biobank.ndph. ox.ac.uk/ukb/app.cgi?id=14421, and highlighted the 1q21.1del and 1q21.1dup, as an example of our normative modeling-CNV approach.

## Normative modeling with Bayesian linear regression

We employed the Bayesian linear regression function from the PCNtoolkit to create the normative models for each voxel, and the mathematical details are described in the supplement. The analysis used a Gaussian prior with mean zero and a precision matrix over the model parameters; this allowed for the posterior distribution to be calculated in closed form. To ensure accurate modeling of nonlinear and non-Gaussian effects, we employed a B-spline basis function and likelihood warping, as described in ref. 38. Every normative model constructed factored in the covariates age and sex. To reduce site effects, we have added it as a fixed effect in the model. In general, incorporating site as a confounding variable during the fitting procedure is the most effective approach for handling site effects 60 . Harmonization procedures can also be considered, if the site data are unavailable or for adapting larger models afterward. However, they come with extensive drawbacks, including the potential removal of meaningful variance correlated with site effects 61,62 .

## Cognitive and behavioral correlations with Jacobian brain deviation scores

We linked the found Jacobian brain deviation scores to cognition by testing a Spearman correlation between the number of extreme deviation scores (| z | &gt; 2) per participant and a general measure of cognitive ability, derived from seven cognition tests available in the UK Biobank (https://biobank.ndph.ox.ac.uk/ukb/label.cgi?id=100026) 63 . Our choice to focus on cognition scores as the primary behavioral variable stemmed from prior research indicating that the selected CNVs may detrimentally affect cognition 39,46 . In addition, for those interested, we included a supplementary behavioral correlation analysis linking all IDP deviation scores and Jacobian deviation scores to several other behavioral variables sourced from the UK Biobank. Furthermore, to test the correspondence between the IDP and Jacobian deviation scores with the polygenic scores (PGS) for major neuropsychiatric disorders, we included a correlation analysis with the PGS for seven disorders: ADHD, ASD, major depressive disorder, anxiety disorders, schizophrenia, bipolar disorder and cannabis use disorder. The PGS scores were calculated with the PRS-CS-auto method 64 . Details of the PGS derivation and correlation analysis can be found in Supplementary Information and the related paper 65 .

## Reporting summary

Further information on research design is available in the Nature Portfolio Reporting Summary linked to this article.

## Data availability

The data used in this study are all attained from publicly available  resources  (Cam-CAN,  https://www.cam-can.org/index. php?content=dataset; PNC, https://www.nitrc.org/projects/pnc; UK Biobank, https://www.ukbiobank.ac.uk; OASIS, https://www.oasisbrains.org; and HCP, https://www.humanconnectome.org/study/ hcp-young-adult) and can be requested by researchers.

## Code availability

The code to create the reference models is available on GitHub at https://github.com/amarquand/PCNtoolkit. Scripts for creating the visualizations are available on GitHub at https://github.com/CharFraza/ CNV\_normative\_modeling/.

## References

1. Malhotra, D. &amp; Sebat, J. CNVs: harbingers of a rare variant revolution in psychiatric genetics. Cell 148 , 1223-1241 (2012).
2. Sanders, S. J. et al. A framework for the investigation of rare genetic disorders in neuropsychiatry. Nat. Med. 25 , 1477-1487 (2019).
3. Gudmundsson, O. O. et al. Attention-deficit hyperactivity disorder shares copy number variant risk with schizophrenia and autism spectrum disorder. Transl. Psychiatry 9 , 1-9 (2019).
4. Marshall, C. R. et al. Contribution of copy number variants to schizophrenia from a genome-wide study of 41,321 subjects. Nat. Genet. 49 , 27-35 (2017).
5. Kirov, G. et al. The penetrance of copy number variations for schizophrenia and developmental delay. Biol. Psychiatry 75 , 378-385 (2014).
6. Cooper, G. M. et al. A copy number variation morbidity map of developmental delay. Nat. Genet. 43 , 838-846 (2011).
7. Sanders, S. J. et al. Insights into autism spectrum disorder genomic architecture and biology from 71 risk loci. Neuron 87 , 1215-1233 (2015).
8. Fromer, M. et al. De novo mutations in schizophrenia implicate synaptic networks. Nature 506 , 179-184 (2014).
9. International Schizophrenia Consortium. Rare chromosomal deletions and duplications increase risk of schizophrenia. Nature 455 , 237-241 (2008).
10. Walsh, T. et al. Rare structural variants disrupt multiple genes in neurodevelopmental pathways in schizophrenia. Science 320 , 539-543 (2008).
11. Bearden, C. E. &amp; Forsyth, J. K. The many roads to psychosis: recent advances in understanding risk and mechanisms. F1000Res 7 , F1000 Faculty Rev-1883 (2018).
12. Singh, T. et al. Rare coding variants in ten genes confer substantial risk for schizophrenia. Nature 604 , 509-516 (2022).
13. Lionel, A. C. et al. Rare copy number variation discovery and cross-disorder comparisons identify risk genes for ADHD. Sci. Transl. Med. 3 , 95ra75 (2011).
14. Moreau, C. A. et al. Mutations associated with neuropsychiatric conditions delineate functional brain connectivity dimensions contributing to autism and schizophrenia. Nat. Commun. 11 , 5272 (2020).
15. Douard, E. et al. Effect sizes of deletions and duplications on autism risk across the genome. Am. J. Psychiatry 178 , 87-98 (2021).
16. Stefansson, H. et al. CNVs conferring risk of autism or schizophrenia affect cognition in controls. Nature 505 , 361-366 (2014).
17. Lee, P. H. et al. Genomic relationships, novel loci, and pleiotropic mechanisms across eight psychiatric disorders. Cell 179 , 1469-1482.e11 (2019).
18. Andrews, T. et al. Gene networks underlying convergent and pleiotropic phenotypes in a large and systematically-phenotyped cohort with heterogeneous developmental disorders. PLoS Genet. 11 , e1005012 (2015).
19. Moreau, C. et al. The general impact of haploinsuff.shorticiency on brain connectivity underlies the pleiotropic effect of neuropsychiatric CNVs. Biol. Psychiatry 89 (Suppl.), S40 (2021).
20.  Richetto, J. &amp; Meyer, U. Epigenetic modifications in schizophrenia and related disorders: molecular scars of environmental exposures and source of phenotypic variability. Biol. Psychiatry 89 , 215-226 (2021).
21. Boen, R. et al. Beyond the global brain differences: intra-individual variability differences in 1q21.1 distal and 15q11.2 BP1-BP2 deletion carriers. Biol. Psychiatry 95 , 147-160 (2024).
22. Marquand, A. F., Rezek, I., Buitelaar, J. &amp; Beckmann, C. F. Understanding heterogeneity in clinical cohorts using normative models: beyond case-control studies. Biol. Psychiatry 80 , 552-561 (2016).
23. Marquand, A. F. et al. Conceptualizing mental disorders as deviations from normative functioning. Mol. Psychiatry 24 , 1415-1424 (2019).
24. Rutherford, S. et al. Charting brain growth and aging at high spatial precision. eLife 11 , e72904 (2022).
25. Rutherford, S. et al. Evidence for embracing normative modeling. eLife 12 , e85082 (2023).
26. Bučková, B. R. et al. Using normative models pre-trained on cross-sectional data to evaluate longitudinal changes in neuroimaging data. eLife 13 , RP95823 (2024).
27. Bethlehem, Ra. I. et al. Brain charts for the human lifespan. Nature 604 , 525-533 (2022).
28. Wolfers, T. et al. Mapping the heterogeneous phenotype of schizophrenia and bipolar disorder using normative models. JAMA Psychiatry 75 , 1146-1155 (2018).
29. Wolfers, T. et al. Individual differences v. the average patient: mapping the heterogeneity in ADHD using normative models. Psychol. Med. 50 , 314-323 (2020).
30.  Zabihi, M. et al. Fractionating autism based on neuroanatomical normative modeling. Transl. Psychiatry 10 , 1-10 (2020).
31. Pinaya, W. H. L. et al. Using normative modelling to detect disease progression in mild cognitive impairment and Alzheimer's disease in a cross-sectional multi-cohort study. Sci. Rep. 11 , 15746 (2021).
32. Owen, M. J. &amp; O'Donovan, M. C. Schizophrenia and the neurodevelopmental continuum:evidence from genomics. World Psychiatry 16 , 227-235 (2017).
33.  Fraza, C., Zabihi, M., Beckmann, C. F. &amp; Marquand, A. F. The extremes of normative modelling. Preprint at https://doi.org/ 10.1101/2022.08.23.505049 (2022).
34.  Kendall, K. M. et al. Cognitive performance among carriers of pathogenic copy number variants: analysis of 152,000 UK Biobank subjects. Biol. Psychiatry 82 , 103-110 (2017).
35.  Kendall, K. M. et al. Cognitive performance and functional outcomes of carriers of pathogenic copy number variants: analysis of the UK Biobank. Br. J. Psychiatry 214 , 297-304 (2019).
36.  Crawford, K. et al. Medical consequences of pathogenic CNVs in adults: analysis of the UK Biobank. J. Med. Genet. 56 , 131-138 (2019).
37. Alfaro-Almagro, F. et al. Image processing and quality control for the first 10,000 brain imaging datasets from UK Biobank. NeuroImage 166 , 400-424 (2018).
38.  Fraza, C. J., Dinga, R., Beckmann, C. F. &amp; Marquand, A. F. Warped Bayesian linear regression for normative modelling of big data. NeuroImage 245 , 118715 (2021).
39. Bernier, R. et al. Clinical phenotype of the recurrent 1q21.1 copynumber variant. Genet. Med. 18 , 341-349 (2016).
40.  Sønderby, I. E. et al. 1q21.1 distal copy number variants are associated with cerebral and cognitive alterations in humans. Transl. Psychiatry 11 , 1-16 (2021).
41. Stefansson, H. et al. Large recurrent microdeletions associated with schizophrenia. Nature 455 , 232-236 (2008).
42. Green, E. K. et al. Copy number variation in bipolar disorder. Mol. Psychiatry 21 , 89-93 (2016).
43.  Modenato, C. et al. Effects of eight neuropsychiatric copy number variants on human brain structure. Transl. Psychiatry 11 , 399 (2021).
44.  Sønderby, I. E. et al. Dose response of the 16p11.2 distal copy number variant on intracranial volume and basal ganglia. Mol. Psychiatry 25 , 584-602 (2020).

45.  Writing Committee for the ENIGMA-CNV Working Groupet al. Association of copy number variation of the 15q11.2 BP1-BP2 region with cortical and subcortical morphology and cognition. JAMA Psychiatry 77 , 420-430 (2020).
46.  Brunetti-Pierri, N. et al. Recurrent reciprocal 1q21.1 deletions and duplications associated with microcephaly or macrocephaly and developmental and behavioral abnormalities. Nat. Genet. 40 , 1466-1471 (2008).
47. Huguet, G. et al. Measuring and estimating the effect sizes of copy number variants on general intelligence in community-based samples. JAMA Psychiatry 75 , 447-457 (2018).
48.  Insel, T. et al. Research Domain Criteria (RDoC): toward a new classification framework for research on mental disorders. Am. J. Psychiatry https://doi.org/10.1176/appi.ajp.2010.09091379 (2010).
49. Szecówka, K., Misiak, B., Łaczmańska, I., Frydecka, D. &amp; Moustafa, A. A. Copy number variations and schizophrenia. Mol. Neurobiol. 60 , 1854-1864 (2023).
50.  Xu, J. et al. Effects of urban living environments on mental health in adults. Nat. Med. 29 , 1456-1467 (2023).
51. Di Biase, M. A. et al. Mapping human brain charts crosssectionally and longitudinally. Proc. Natl Acad. Sci. USA 120 , e2216798120 (2023).
52. Henrich, J., Heine, S. J. &amp; Norenzayan, A. The weirdest people in the world? Behav. Brain Sci. 33 , 61-83 (2010).
53. Taylor, J. R. et al. The Cambridge Centre for Ageing and Neuroscience (Cam-CAN) data repository: structural and functional MRI, MEG, and cognitive data from a cross-sectional adult lifespan sample. NeuroImage 144 , 262-269 (2017).
54.  Van Essen, D. C. et al. The WU-Minn Human Connectome Project: an overview. NeuroImage 80 , 62-79 (2013).
55. Marcus, D. S., Fotenos, A. F., Csernansky, J. G., Morris, J. C. &amp; Buckner, R. L. Open access series of imaging studies (OASIS): longitudinal MRI data in nondemented and demented older adults. J. Cogn. Neurosci. 22 , 2677-2684 (2010).
56. Satterthwaite, T. D. et al. Neuroimaging of the Philadelphia Neurodevelopmental Cohort. NeuroImage 86 , 544-553 (2014).
57. McCarthy, P. Funpack. Zenodo https://doi.org/10.5281/ zenodo.3761702 (2020).
58. Monté-Rubio, G. C., Falcón, C., Pomarol-Clotet, E. &amp; Ashburner, J. A comparison of various MRI feature types for characterizing whole brain anatomical differences using linear pattern recognition methods. NeuroImage 178 , 753-768 (2018).
59. Modenato, C. et al. Lessons learned from neuroimaging studies of copy number variants: a systematic review. Biol. Psychiatry 90 , 596-610 (2021).
60.  Nygaard, V., Rødland, E. A. &amp; Hovig, E. Methods that remove batch effects while retaining group differences may lead to exaggerated confidence in downstream analyses. Biostatistics 17 , 29-39 (2016).
61. Bayer, J. M. M. et al. Site effects how-to and when: an overview of retrospective techniques to accommodate site effects in multisite neuroimaging analyses. Front. Neurol. 13 , 923988 (2022).
62. Bayer, J. M. M. et al. Accommodating site variation in neuroimaging data using normative and hierarchical Bayesian models. NeuroImage 264 , 119699 (2022).
63.  Sripada, C., Angstadt, M., Rutherford, S., Taxali, A. &amp; Shedden, K. Toward a 'treadmill test' for cognition: improved prediction of general cognitive ability from the task activated brain. Human Brain Mapping 41 , 3186-3197 (2020).
64.  Ge, T., Chen, C.-Y., Ni, Y ., Feng, Y .-C. A. &amp; Smoller, J. W. Polygenic prediction via Bayesian regression and continuous shrinkage priors. Nat. Commun. 10 , 1776 (2019).
65. Genetic liability to major psychiatric disorders contributes to multi-faceted quality of life outcomes in children and adults. Preprint at https://www.medrxiv.org/content/10.1101/2023.01.17.2 3284645v1.full.
66.  Li, J. et al. Cross-ethnicity/race generalization failure of behavioral prediction from resting-state functional connectivity. Sci. Adv. 8 , eabj1812 (2022).
67. LaMontagne, P. J. et al. OASIS-3: longitudinal neuroimaging, clinical, and cognitive dataset for normal aging and Alzheimer disease. Preprint at https://doi.org/10.1101/2019.12.13.19014902 (2019).

## Acknowledgements

This research was supported by grants from the European Research Council (grant 'MENTALPRECISION' 101001118) and the Dutch Organisation for Scientific Research (VIDI grant 016.156.415) received by A. Marquand. This research has been conducted using the UK Biobank resource under application number 23668. We thank the researchers and participants from the UK Biobank for their contributions. R.B. and I.E.S. are supported by the Research Council of Norway (no. 223273) and the South-Eastern Norway Regional Health Authority (no. 2020060). In addition, I.E.S. is supported by the European Union's Horizon2020 Research and Innovation Programme (CoMorMent project, grant no. 847776) and Kristian Gerhard Jebsen Stiftelsen (SKGJ-MED-021).

## Author contributions

C.J.F. conceptualized the initial idea, performed the analysis and wrote the original draft of the manuscript. I.E.S. and R.B. assisted with critical review of the article and the interpretation of the results. Y.S. provided and analyzed part of the genetic data for the revision and aided in the writing of the rebuttal. C.F.B. provided supervision and review of the final article. A.F.M. aided in the original conceptualization, supervision, methodology and final writing, review and editing of the paper.

## Competing interests

C.F.B. is a director of and shareholder in SBGneuro Ltd.

## Additional information

Extended data is available for this paper at https://doi.org/10.1038/s44220-024-00322-1.

Supplementary information The online version contains supplementary material available at https://doi.org/10.1038/s44220-024-00322-1.

Correspondence and requests for materials should be addressed to Charlotte Fraza.

Peer review information Nature Mental Health thanks Sven Cichon, Fabrizio Pizzagalli and the other, anonymous, reviewers for their contribution to the peer review of this work.

Reprints and permissions information is available at

www.nature.com/reprints.

Publisher's note Springer Nature remains neutral with regard to jurisdictional claims in published maps and institutional aff.shortiliations.

Springer Nature or its licensor (e.g. a society or other partner) holds exclusive rights to this article under a publishing agreement with the author(s) or other rightsholder(s); author self-archiving of the accepted manuscript version of this article is solely governed by the terms of such publishing agreement and applicable law.

- © The Author(s), under exclusive licence to Springer Nature America, Inc. 2024

## Charlotte Fraza 1,2 , Ida E. Sønderby 3,4,5 , Rune Boen 3,4 , Yingjie Shi 1,6 , Christian F. Beckmann 1,2,7 &amp; Andre F. Marquand 1,2

1 Donders Institute for Brain, Cognition, and Behavior, Radboud University, Nijmegen, the Netherlands. 2 Department of Cognitive Neuroscience, Radboud University Medical Center, Nijmegen, the Netherlands. 3 Department of Medical Genetics, Oslo University Hospital, Oslo, Norway. 4 Norwegian Centre for Mental Disorders Research, Division of Mental Health and Addiction, Oslo University Hospital, University of Oslo, Oslo, Norway. 5 KG Jebsen Centre for Neurodevelopmental Disorders, University of Oslo, Oslo, Norway. 6 Department of Human Genetics, Radboud University Medical Center, Nijmegen, the Netherlands. 7 Centre for Functional MRI of the Brain, Nuffield Department of Clinical Neurosciences, Wellcome Centre for Integrative Neuroimaging, University of Oxford, Oxford, UK. e-mail: charlotte.fraza@donders.ru.nl

![Image](./Fraza2023_artifacts/image_000009_1ca5b6f4478e6414b00ff81a94f166c03b56a7aaf33ba9f5dd921d6061ed8165.png)

4

Extended Data Fig. 1 | Pathogenic CNVs impact on cognitive function. Illustration of how protective and disruptive factors, which may include (pathogenic) CNVs, might lead to cognitive impairment. A . Variations in fluid intelligence scores among participants with pathogenic CNVs (P-CNVs) and

![Image](./Fraza2023_artifacts/image_000010_e35331ed720d3a2e1cac8c6ba75552f4b65d503ed3f6644fb8e5b5b36afc649c.png)

