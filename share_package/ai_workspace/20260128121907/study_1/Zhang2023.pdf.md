![Image](./Zhang2023_artifacts/image_000000_fc61ed617711021f171e1f30e377488ba3370f2fb9ad4dc75174895ca86103af.png)

## Contents lists available at ScienceDirect

## Journal of Affective Disorders

journal homepage: www.elsevier.com/locate/jad

## Parsing the heterogeneity of brain-symptom associations in autism spectrum disorder via random forest with homogeneous canonical correlation

![Image](./Zhang2023_artifacts/image_000001_308393ae443ca275515b480436288786230dcb8bf6a260f6dc41068b89b06141.png)

Jiajun Zhang a,1 , Shuanfeng Fang b,1 , Yin Yao c , Fei Li d , Qiang Luo a,e,*

![Image](./Zhang2023_artifacts/image_000002_a778d778bd203742dd7444f764d8d3358287f3d804989a4733e0c513fa1587ee.png)

![Image](./Zhang2023_artifacts/image_000003_d2c92562ba518b2774200e283adc640df117132eabf42d722c8c8c04525e7e26.png)

- c Department of Computational Biology, School of Life Sciences, Fudan University, PR China

d Developmental and Behavioral Pediatric Department &amp; Child Primary Care Department, Ministry of Education Key Laboratory for Children ' s Environmental Health, Xinhua Hospital, Shanghai Jiao Tong University School of Medicine, Shanghai 200092, PR China

e Ministry of Education-Key Laboratory of Computational Neuroscience and Brain-Inspired Intelligence, Human Phenome Institute, Fudan University, Shanghai 200032, China

## A R T I C L E  I N F O

Keywords: Autism spectrum disorder Biosubtypes Homogeneous index Random forest Social symptoms

## 1. Introduction

Autism spectrum disorder (ASD) is a heterogeneous developmental disorder that can lead to lifelong disability (Thurm and Swedo, 2022). The etiology of ASD remains unclear, and there is no known cure for its core  symptoms at present (Elder et al.,  2017).  Individuals  with  ASD

## A B S T R A C T

Background: Autism spectrum disorder (ASD) is a highly heterogeneous developmental disorder, but the neuroimaging substrates of its heterogeneity remain unknown. The difficulty lies mainly on the significant individual variability in the brain-symptom association.

Methods: T1-weighted magnetic resonance imaging data from the Autism Brain Imaging Database Exchange (ABIDE) (NTDC = 1146) were used to generate a normative model to map brain structure deviations of cases (NASD = 571). Voxel-based morphometry (VBM) was used to compute gray matter volume (GMV). Singular Value Decomposition (SVD) was employed to perform dimensionality reduction. A tree-based algorithm was proposed to identify the ASD subtypes according to the pattern of brain-symptom association as assessed by a homogeneous canonical correlation.

Results: We identified 4 ASD subtypes with distinct association patterns between residual volumes and a social symptom score. More severe the social symptom was associated with greater GMVs in both the frontoparietal regions for the subtype1 ( r = 0.29 -0.44) and the ventral visual pathway for the subtype3 ( r = 0.19 -0.23), but lower GMVs in both the right anterior cingulate cortex for the subtype4 ( r =GLYPH&lt;0&gt; 0.25) and a few subcortical regions for the subtype2 ( r = GLYPH&lt;0&gt; 0.31 to GLYPH&lt;0&gt; 0.20). The subtyping significantly improved the classification accuracy between cases and controls from 0.64 to 0.75 ( p &lt; 0.05, permutation test), which was also better than the accuracy of 0.68

achieved by the k-means-based subtyping ( p &lt; 0.01).

Limitations: Sample size limited the study due to the missing data.

Conclusions: These findings suggest that the heterogeneity of ASD might reflect changes in different subsystems of the social brain, especially including social attention, motivation, perceiving and evaluation.

show heterogeneity in both the structure (Boedhoe et al., 2020) and function of the brain (Hahamy et al., 2015), as well as in clinical manifestations (Huerta and Lord, 2012). A key challenge is to characterize the heterogeneity not only by clinical symptoms, but also by biological factors such as neuroanatomical measures (Jeste and Geschwind, 2014; Volkmar et al., 2022).

* Corresponding author at: National Clinical Research Center for Aging and Medicine at Huashan Hospital, Institute of Science and Technology for Brain-Inspired Intelligence, Fudan University, Shanghai 200433, PR China.

E-mail address: qluo@fudan.edu.cn (Q. Luo).

1 Co-first author.

GLYPH&lt;0&gt;%GLYPH&lt;0&gt;JGLYPH&lt;0&gt;9GLYPH&lt;0&gt;J

GLYPH&lt;0&gt;$GLYPH&lt;0&gt;OGLYPH&lt;0&gt;O

GLYPH&lt;0&gt;UGLYPH&lt;0&gt;LGLYPH&lt;0&gt;JGLYPH&lt;0&gt;KGLYPH&lt;0&gt;WGLYPH&lt;0&gt;V

GLYPH&lt;0&gt;UGLYPH&lt;0&gt;HGLYPH&lt;0&gt;VGLYPH&lt;0&gt;HGLYPH&lt;0&gt;UGLYPH&lt;0&gt;YGLYPH&lt;0&gt;HGLYPH&lt;0&gt;GGLYPH&lt;0&gt;J

GLYPH&lt;0&gt;GLYPH&lt;22&gt;GLYPH&lt;0&gt;GLYPH&lt;22&gt;GLYPH&lt;0&gt;GLYPH&lt;24&gt;

GLYPH&lt;0&gt;RGLYPH&lt;0&gt;GLYPH&lt;21&gt;GLYPH&lt;0&gt;GLYPH&lt;19&gt;GLYPH&lt;0&gt;GLYPH&lt;21&gt;GLYPH&lt;0&gt;GLYPH&lt;22&gt;GLYPH&lt;0&gt;m

GLYPH&lt;0&gt;GLYPH&lt;22&gt;GLYPH&lt;0&gt;GLYPH&lt;25&gt;GLYPH&lt;0&gt;²GLYPH&lt;0&gt;GLYPH&lt;23&gt;GLYPH&lt;0&gt;GLYPH&lt;22&gt;

![Image](./Zhang2023_artifacts/image_000004_9f805e525236729c06f0c093beea075c7108c076596e6bbbb4e2f33162be9645.png)

![Image](./Zhang2023_artifacts/image_000005_e2461357d2bc3b5460075b08d1fe2ca2a0883b107ea416740c6fc6a962ccbf4d.png)

J. Zhang et al.

The heterogeneity in ASD may suggest the existence of biological and clinical subtypes (Hong et al., 2020). However, neuroanatomical subtypes of ASD are inconsistent in the neuroimaging literature. One study clustered ASD into 3 subtypes using gray matter volume (Shan et al., 2022). Another study found 2 neuroanatomical subtypes by clustering regional volumetric measures of gray matter, white matter, and cerebrospinal fluid (Liu et al., 2022). Another study revealed 4 ASD subtypes by focusing on cortical thickness of the frontal lobe, size of the corpus callosum, hippocampus, caudate, and amygdala (Hrdlicka et al., 2005). A 2022 study even argued that instead of neuroanatomical subtypes ASD might be better described as a spectrum using a brain-wide and voxelwise  volumetric analysis  (Aglinskas et  al.,  2022).  Most  of  these  subtyping  studies  were  based  on  neuroanatomical  data  alone,  however, these neuroanatomical measures are determined by complex gene and environment interactions (Gu and Kanai, 2014). It ' s possible that the findings exposed above could be tainted by the gene-environment interactions, thus are not pathogenetic nor specific for ASD. Therefore, we proposed a novel subtyping approach by focusing on neuroanatomic abnormalities that are associated with the core symptoms of ASD.

In the literature, the core symptoms of ASD have been associated with various neuroimaging measures, but the findings have significant inconsistency  (Hull  et  al.,  2016).  Such  inconsistency  exits  in  both structural measures of brain regions (Eilam-Stock et al., 2016; Hegarty et al., 2020; Supekar and Menon, 2015) and the functional connectivity between them (Assaf et al., 2010; Duan et al., 2017). Instead of a pairwise association between a symptom score and a neuroimaging measure, the symptoms of ASD have been associated with combinations of various  neuroimaging  measures  (Mei  et  al.,  2020).  These  previous findings suggest that the multivariate patterns of brain-symptom associations  might  provide  us  a  new  opportunity  to  identify  the  neuroimaging measures that are associated with the core symptoms instead of the gene-environmental factors in the general population. To address these problems, we propose here to first use a normative model of gray matter volume to estimate the individual deviations of each ASD case from the standard developmental trajectory, and then to group the cases into subtypes according to their multivariate patterns of brain-symptom associations between individual deviations and the core symptoms.

The main aim of the current study was to test the hypothesis that there are ASD subtypes with distinct patterns of brain-symptom associations. First, we built a normative model of gray matter volume using typically developing controls (TDC; N = 1146), and then for each ASD case ( N = 571) we evaluated the deviations of 140 brain regions from this  normative  model  by  the  model  residuals.  Second,  we  employed singular value decomposition for dimensionality reduction of these deviations. Using the features in the low-dimensional space, we proposed an algorithm called random forest with homogeneous canonical correlation  (RFHCC)  to  identify  the  ASD  subtypes,  each  of  which  had  a distinct pattern of brain-symptom associations as assessed by a canonical  correlation  analysis  (CCA).  The  performance  of  the  algorithm  on recovering the patterns of association between two multivariate datasets was demonstrated by numerical simulations. Finally, we tested whether the  subtyping  could  significantly  improve  the  classification  accuracy between cases and controls in our sample by 1000 random permutations.  We  also  compared  the  case-control  classification  performance with a classical subtyping approach (i.e., k-means).

## 2. Methods

## 2.1. Participants

The study analyzed both structural neuroimaging data and phenotypic  data  provided  by  the  Autism  Brain  Imaging  Data  Exchange (ABIDE) (Di Martino et al., 2017; Di Martino et al., 2014). The current study includes the data of 571 cases with ASD who had both symptom assessments and T1 images, and 1146 controls with T1 images. The cases were diagnosed according to DSM-IV-TR at each contributing site. The symptom severity was assessed by the Autism Diagnostic Observation Schedule (ADOS), consisting of 3 subscales (i.e., the communication, social,  stereotyped  behaviors  and  restricted  interests  scores).  Demographic  and  clinical  information  of  these  subjects  were  listed  in Table 1. The original studies included in ABIDE received approval from each site ' s Institutional Review Board (IRB), and all adults or the legal guardians of children and adolescents provided written informed consent after information on the research procedures at each data collection site.

## 2.2. Neuroimaging preprocessing

Structural neuroimaging data of participants were preprocessed by using the computational anatomy toolbox 12 (CAT12; http://www.neu ro.uni-jena.de/cat/). The main preprocessing steps in CAT12 included denoising, resampling, bias-correction, affine-registration, the standard SPM  unified  segmentation  (Ashburner  and  Friston,  2005),  adaptive maximum a posteriori (AMAP) segmentation (Rajapakse et al., 1997), a partial volume estimation (Tohka et al., 2004), and finally normalization by Geodesic Shooting (Ashburner and Friston, 2011) registrations. Before the statistically analysis, the gray matter volume images were smoothed with an 8-mm FWHM (full width at half maximum) Gaussian kernel  (Ashburner  and  Friston,  2000).  We  had  1094  participants  in ABIDE I and 1100 participants in ABIDE II who passed the preprocessing, while 18 participants in ABIDE I and 14 participants in ABIDE II

Table 1 Demographic and clinical information for the subjects.

|                     |                         | TC                                 | ASD                                |
|---------------------|-------------------------|------------------------------------|------------------------------------|
| Gender(M/F)  Age(Y) | Gender(M/F)  Age(Y)     | 868/278  15.98(8.620)  [5.89 - 64] | 503/68  17.27(9.34)  [5.22 - 62]   |
| TIV ( cm 3 )        | TIV ( cm 3 )            | 1524.9(134.1)  [1171.4 - 1886.4]   | 1553.3(144.9)  [1163.6 - 1913.8]   |
| IQ                  | FIQ                     | 113.1(12.7)  [71.0 - 151.0]{1052}  | 106.7(17.0)  [49.0 - 149.0]{559}   |
|                     | VIQ                     | 113.8(13.7)  [67.0 - 156.0]{855}   | 106.7(17.3)  [45.0 - 155.0]{474}   |
|                     | PIQ                     | 109.7(13.6)  [62.0 - 155.0]{917}   | 106.2(16.9)  [61.0 - 149.0]{470}   |
| SRS                 | Awareness               | 4.6(2.8)[0.0 - 13.0]  {436}        | 12.0(3.9)[1.0 - 21.0]  {177}       |
|                     | Cognition               | 3.4(3.4)[0.0 - 22.0]  {436}        | 17.0(6.2)[1.0 - 32.0]  {177}       |
|                     | Communication           | 6.5(5.9)[0.0 - 35.0]  {436}        | 31.0(10.9)[3.0 - 55.0]  {205}      |
|                     | Motivation              | 4.4(3.8)[0.0 - 22.0]  {436}        | 15.4(6.5)[1.0 - 32.0]  {205}       |
|                     | Mannerisms              | 2.6(3.2)[0.0 - 21.0]  {436}        | 17.1(7.1)[0.0 - 34.0]  {205}       |
|                     | Total                   | 21.0(15.7)  [0.0 - 103.0]{575}     | 91.4(31.1)  [6.0 - 199.0]{333}     |
| VINELAND            | Communication  standard | /                                  | 80.7(14.7)  [32.0 - 120.0]{105}    |
|                     | Social standard         | /                                  | 77.8.4(15.1)  [20.0 - 130.0]{105}  |
|                     | ABC standard            | /                                  | 79.1(12.4)  [44.0 - 101.0]{105}    |
|                     | Daily living  standard  | /                                  | 85.6(12.9)  [52.0 - 113.0]{105}    |
|                     | Sum scores              | /                                  | 249.4 (43.1)  [136.0 - 400.0]{105} |
| ADOS                | Social                  | /                                  | 7.60(2.66)[1.0 - 14.0]  {571}      |
|                     | Communication           | /                                  | 3.52(1.60)[0.0 - 10.0]  {571}      |
|                     | Stereotyped  behaviors  | /                                  | 1.85(1.54)[0.0 - 8.0]  {571}       |

The  mean  (standard  deviation)  [range]{number  of  non-missing  values}  was listed  for  each  variable.  ADOS stands for the Autism Diagnostic Observation Schedule, SRS stands for the Social Responsiveness Scale, VINELAND stands for the Vineland Adaptive Behavior Scales.

J. Zhang et al.

failed to complete the segmentation, resulting in errors and no output. The total intracranial volume (TIV) was estimated for each participant, 31 samples with abnormal TIV as outliers (i.e., outside the 1.5 interquartile  range)  were  removed.  The  requirement  for  ADOS  subscale scores for the cases left 1146 controls and 573 cases. However, another 2 cases were excluded due to the inconsistency between their ages provided on the ABIDE official website and in the file of the phenotypic data. Finally, the data from 1146 controls and 571 cases entered the following analyses. The automated anatomical labelling 3 (AAL3) atlas (Rolls et al., 2020) was used to extract gray matter volumes of 140 brain regions excluding the cerebellum and the vermis, because there were no significant correlation between the cerebellum/vermis and symptoms of ADOS  (Laidi  et  al.,  2017),  and  no  significant  group  difference  was observed  for  the  volume  of  cerebellum  between  autism  and  control (Laidi et al., 2022).

## 2.3. Normative model

To  account  for  the  typical  development  of  gray  matter  volume (GMV), we fitted a multivariate linear regression model for the regional GMV by age, gender and TIV using the controls. There were four options for  the  highest  order  of  age  in  the  regression  model:  1,  2,  3,  and  4. Through BIC (Bayesian Information Criterion), we found that the model was the best when the highest order of age was 2, and the residual data did not have any significant site effect (eMethods 1). The model was then applied to the corresponding brain regions of the cases, and the model residuals were used to estimate volumetric deviations for each case from the typical developing trajectory. Mathematically,

<!-- formula-not-decoded -->

## 2.4. Identifying ASD subtypes each with a homogenous pattern of brainsymptom association

Among the ASD cases, the multivariate pattern of brain-symptom association  was  evaluated  between  volumetric  deviations  of  the  140 brain regions and symptom severities of the 3 ADOS subscales by CCA. The CCA is a powerful tool for identifying multivariate associations, but it is often prone to over-fitting. Therefore, we defined an index of homogeneity for the identified CCA mode. Given a group of patients, we randomly split the group into two halves for training and test, respectively.  For  a  CCA  mode  identified  using  the  training  sample,  the weightings for both the canonical component of volumes (CC-volume) and the canonical component of symptoms (CC-symptom) were applied to the test sample. If the association between the CC-volume and CCsymptom remained significant (assessed by 1000 permutations) in the test sample, we added 1 to the index of homogeneity for the current CCA mode.  The  randomly  split-half  process  was  repeated  for  10  times following the literature (Smith et al., 2015), and the final index of homogeneity was scored between 0 and 10. Mathematically, for the s th CCA mode, let pt be its significance in the t th test sample, then its index of homogeneity ( Hs ) was calculated by

<!-- formula-not-decoded -->

If the Hs scored 10 for a CCA mode in a given group of cases, these cases were considered to have a homogenous brain-symptom association.

To identify these groups each with a homogenous brain-symptom association,  we  proposed  an  unsupervised  algorithm,  namely  the RFHCC (random forest with homogeneous canonical correlation, Fig. 1). The patients were split into subgroups according to a set of grouping features, In order to obtain the grouping features, we applied the singular value decomposition to the regional volumetric deviations given by the normative model to reduce its dimensionality, and the grouping features were the first 5 principal components with the accumulative variance explained &gt; 80 %. The goal of each split was to increase the Hs in the resulting subgroups.

At each step of the tree growing, a subset of the grouping features was randomly selected. For the i th cut-off ( cij ) of the j th covariate ( Zj ), the cases in the parent node were split into the left and the right child nodes if they had this grouping feature valued lower or higher than the cut-off, respectively. For each cut-off cij , the index of homogeneity was assessed for each of the child nodes as

<!-- formula-not-decoded -->

where SL / R was the number of CCA modes identified in the left/right child node. Among all cut-offs for the selected grouping feature, the one ( c opt ) that gave the highest quality was used to execute this split:

<!-- formula-not-decoded -->

Fig. 1. Pseudo code of the proposed algorithm.

J. Zhang et al.

where I was  the  number  of  cut-offs, J was  the  number  of  selected covariates,  and n cij L and n cij R were  sizes  of  the  left  and  right  nodes, respectively.  The  tree  grew  until  the  stopping  criteria  were  met, including 1) the number of cases contained in the current node was less than a pre-defined threshold L = 40, and in this case, the tree rolled back to the parent node; and 2) the current tree depth was equal to 10. This tree-growing procedure was repeated for 500 times, and the homogeneous modes with Hs = 10 were collected from all trees. Each homogeneous mode corresponded to a leaf node of a tree, and the cases in this node shared a homogeneous brain-symptom association as defined by this mode.

The similarity between two homogeneous modes was assessed by the correlation coefficient between two weighting vectors of the canonical components. When the correlation was significant, the corresponding subgroups were merged into a larger subgroup. In this new subgroup, a new  mode  was  established  and  its  index  of  homogeneity  was  reevaluated.  These  two  modes  were  replaced  by  the  new  mode  only when the index of homogeneity of the new mode scored 10.

## 2.5. Simulation

To test whether the RFHCC can detect the distinct patterns of association between two multivariate datasets, we performed a numerical simulation  experiment.  We  randomly  generated  500  samples  from  a pattern of association between x and y , and another 500 samples from a bivariate standard normal distribution. We applied the RFHCC to the simulated data to see if the algorithm could separate these two groups of samples into two leaf nodes. We repeated the simulation for 100 times and found that the algorithm successfully recovered the pattern in all simulations. More details of the simulation are provided in eMethods 2.

## 2.6. Clinical characterization for the subtypes

A Chi-squared test was used to test the difference in the gender ratio among different subtypes. Among different subtypes, the Kruskal-Wallis H test was used to test the differences in the age, TIV, symptom scores, and the IQ scores, including the Full IQ (FIQ), the Performance IQ (PIQ) and the Verbal IQ (VIQ) standard scores. Results were reported after false discovery rate correction. Kruskal-Wallis H test was also used to compare the case-control classification accuracies between those given by the classic clustering approach (i.e., k-means) and the proposed algorithm (i.e., RFHCC).

The  sign  of  the  canonical  correlation  coefficient  cannot  indicate whether a larger brain volume is associated with a higher or a lower symptom score. To determine the direction of the association, we further examined the associations (i.e., the cross-loading) (Modabbernia et al., 2021) of the symptom score with each brain region. For each subtype, we reported brain regions with both small effect (|cross-loading| &gt; 0.1) and significant correlation ( p &lt; 0.05, uncorrected) following the literature (Cohen, 1988; Modabbernia et al., 2021).

## 2.7. Case-control classification

For each subtype, an equal number of controls selected from the 1146 controls were matched by using 4 different matching algorithms. The first algorithm called EGMAD (Equal Gender and Minimum Age Difference), as for each case in the subtype, a control with the same gender and the minimum age difference from the 1146 controls was matched. Another 3 algorithms were provided by the R package matchit with 3 different parameters: ' optimal ' , ' knn ' , and ' cardinality ' .

For each of the 4 matched case-control datasets, we used the volumetric deviations of 140 brain regions and 4 CC-volumes as input features for the classifier. The CC-volume was obtained by applying the weighting vector of CC-volume to the volumetric deviations. The dataset was split into 60% for training and 40% for test, and this process was repeated for 100 times. The classification accuracy was evaluated by the mean area under curve (AUC) for the test datasets.

To test whether the accuracy improvement introduced by our brainsymptom subtyping was significantly better than random grouping, we randomly shuffled the subtyping labels among the 571 cases and then repeated the previous matching and classification steps for 1000 times. The permutation p -value was assessed by comparing the actual AUC with the 1000 AUCs from the shuffled data. As a baseline, the classification was also performed between the 1094 cases without subtyping and 1094 controls that were matched by the same matching algorithms as described above.

The feature importance scores for these 4 case-control classifications were evaluated by a python package eli5 by shuffling each feature in the test dataset to evaluate the change in performance (https://eli5.readthe docs.io/en/latest/). Five-fold cross-validation and 1000 random shuffles were used here. The mean reduction of accuracy was calculated as the feature importance. A feature with a positive feature importance score means this feature  contributes  to  the  classification,  while  a  negative score means that this feature damages the classification.

## 3. Results

## 3.1. Brain-symptom association-based subtypes

Using the normative model, we estimated the individual deviations from the standard developing trajectory of gray matter volume (GMV). We then applied the CCA to the data of all 571 cases, but we could not identify  any  CCA  mode  with  an  index  of  homogeneity  exceeding  5, indicating significant heterogeneity in the associations between volumetric  deviations  of  140  brain  regions  and  symptom  severities  of  3 ADOS subscales.

As detailed in the Methods, we selected the first 5 principal components given by the singular value decomposition of the 140 regional volumetric deviations to be the grouping feature for the RFHCC algorithm. Indeed, we found 4 subtypes according to their distinct patterns of brain-symptom association with all indexes of homogeneity scored 10 (Fig.  2).  The  subtype3  had  the  largest  number  of  cases  namely  110, while subtype1 had the fewest number of cases, 47 (eFigures 1 -3). None of the correlation coefficients between each pair of the weight vectors of the CC-volume was significant. The canonical weight of the social subscale in the CC-symptom was 1, while the canonical weights of the other 2 subscales (i.e., the communication and the stereotyped behaviors and restricted interest subscales) were both 0 in all 4 subtypes, This may be due to the high correlation among these three subscales ( p &lt; 1 * 10 GLYPH&lt;0&gt; 15 ). Therefore, we used the CC-symptom interchangeable with the ADOSsocial subscale score in the following report.

We found significant differences in age, gender ratio and TIV among the 4 subtypes (eTable 1; eFigure 4). The cases in subtype 3 were older than the cases in both subtype 2 and subtype 4, while the TIVs in subtype 1 were smaller than that in the other 3 subtypes. The proportion of males in subtype 1 was lower than that in both subtype 2 and subtype 4. These 4  subtypes  had  no  significant  difference  in  symptoms  including  the ADOS,  the  Social  Responsiveness  Scale  and  the  Vineland  Adaptive Behavior Scales scores. No significant difference was observed in the FIQ, VIQ and PIQ standard scores.

## 3.2. Sensitivity analysis

Since  there  were  group  differences  in  age,  gender  ratio,  and  TIV among the 4 subtypes, we regressed out these covariates from the CCvolume and the CC-symptom scores. In all subtypes, we found that the correlations  remained  significant  between  the  residual  component scores (eTable 2).

GLYPH&lt;0&gt;φGLYPH&lt;0&gt;LGLYPH&lt;0&gt;VGLYPH&lt;0&gt;RGLYPH&lt;0&gt;

J. Zhang et al.

Fig. 2. Differential patterns of brain-symptom associations in four subtypes.

![Image](./Zhang2023_artifacts/image_000006_9c116109f7153c8b38f5c3bff8bfd8dcbb850b7013f1bb94d84167bd23758769.png)

A -D are the scatter plots of the CC-symptom (i.e., the social subscale of ADOS) and the CC-volume respectively for these 4 subtypes with each point representing an ASD case. E -H represent the significant associations between the social subscale of ADOS and the volumetric deviations in the regional gray matter volumes in these 4 subtypes, respectively. Subtype 1: A, E. Subtype 2: B, F. Subtype 3: C, G. Subtype 4: D, H. CC is short for canonical component.

## 3.3. Differential brain-symptom associations among the four subtypes

In subtype 1, the correlation coefficient between the CC-volume and the CC-symptom (i.e., the ADOS-social subscale score) was 0.859 ( p &lt; 0.001; index of homogeneity, Hs = 10; Fig. 2A; eTable 3). In total, 39 regional  volumetric  deviations  showed  positive  correlations  with  the ADOS-social score ( r = 0.29 -0.44; p &lt; 0.05, uncorrected). Among these regions, 33 regions had positive residuals in all ASD cases, indicating that the overgrowth of these brain regions as assessed by the normative model were associated with more severe ADOS-social scores. The top 5 brain regions which had the largest positive correlations with the ADOSsocial  score  were  the  right  superior  parietal  gyrus  (SPG),  the  right inferior parietal gyrus (IPG), the right medial orbital gyrus (OFGmed), the opercular part of the left inferior frontal gyrus (IFG) and the IFG pars orbitalis (eTable 7).

The  correlation  coefficient  between  the  CC-volume  and  the  CCsymptom  (i.e.,  the  ADOS-social  subscale  score)  in  subtype2  was GLYPH&lt;0&gt; 0.648 ( p &lt; 0.001; Hs = 10; Fig. 2B; eTable 4). In total, 14 regional volumetric deviations showed correlations with the ADOS-social score ( p &lt; 0.05,  uncorrected;  eTable  8).  Among  these  regions,  5  regions showed positive correlations ranging from 0.21 to 0.22 and the other 9 brain  regions  showed  negative  correlations  ranging  from GLYPH&lt;0&gt; 0.20  to GLYPH&lt;0&gt; 0.31. The top 5 brain regions with the largest correlations were the dorsal raphe nucleus (DRN), the left ventral tegmental area (VTA), the left pars compacta, the left red nucleus and the left pars reticulata.

The  correlation  coefficient  between  the  CC-volume  and  the  CCsymptom  (i.e.,  the  ADOS-social  subscale  score)  in  subtype3  was GLYPH&lt;0&gt; 0.757  ( p &lt; 0.001; Hs = 10;  Fig.  2C;  eTable  5).  We  found  that  10 regional  volumetric  deviations  showed  correlations  with  the  ADOSsocial score ( p &lt; 0.05, uncorrected; eTable 9). The top 5 brain regions with the largest positive correlations included the left inferior occipital gyrus (IOG), inferior temporal gyrus (ITG), the right middle temporal gyrus (MTG) and the right fusiform gyrus.

The correlation between the CC-volume and the CC-symptom (i.e., the ADOS-social subscale score) in subtype 4 was GLYPH&lt;0&gt; 0.697 ( p &lt; 0.001; Hs = 10; Fig. 2D; eTable 6). We found that 6 regional volumetric deviations showed negative correlations with the ADOS-social score ( r = GLYPH&lt;0&gt; 0.21 to GLYPH&lt;0&gt; 0.25; p &lt; 0.05, uncorrected; eTable 10). Among them, 5 regions had positive residuals in all ASD cases, indicating that the overgrowth of these brain regions might be a compensation for reducing the ADOSsocial symptom. The top 5 brain regions which had the highest negative  correlations  were  the  right  supracallosal  parts  of  the  anterior cingulate cortex (ACCsup), the right pallidum, the left pars reticulata part of the substantia nigra, the triangular and the opercular parts of right IFG (eTable 10).

## 3.4. Subtyping significantly improved case-control classification accuracy

The mean AUC of the case-control classification for those 4 subtypes was 0.75 (ranging from 0.64 to 0.91), which was higher than the classification AUC of 0.64 before the subtyping (Table 2). The permutation tests demonstrated that the subtyping significantly improved the classification accuracy as compared with the accuracies given by randomly

Table 2

Comparison of performances for the classifications between ASD subtypes and matched controls.

| Test AUC/ p  value   | EGMAD               | MatchIt             | MatchIt             | MatchIt             |
|----------------------|---------------------|---------------------|---------------------|---------------------|
|                      |                     | Optimal             | knn                 | Cardinality         |
| Subtype 1            | 0.82(0.051)/  0.010 | 0.91(0.043)/  0.023 | 0.82(0.050)/  0.012 | 0.82(0.052)/  0.025 |
| Subtype 2            | 0.69(0.046)/  0.012 | 0.65(0.054)/  0.002 | 0.64(0.058)/  0.035 | 0.71(0.050)/  0.007 |
| Subtype 3            | 0.70(0.051)/  0.008 | 0.70(0.052)/  0.057 | 0.69(0.059)/  0.038 | 0.86(0.032)/  0.043 |
| Subtype 4            | 0.74(0.048)/  0.017 | 0.71(0.059)/  0.035 | 0.70(0.058)/  0.055 | 0.85(0.038)/  0.025 |
| Without  subtype     | 0.63                | 0.63                | 0.64                | 0.65                |

The mean (standard deviation)/ p value was listed for each combination. AUCarea under curve. p -Value was given by 1000 permutations. MatchIt is a function provided by an R package MatchIt for matching the participants according the selected variables, such as age, gender, and total intracranial volume. This function provided 3 different algorithms for the matching, including optimal, knn and cardinality (https://cran.r-project.org/web/packages/MatchIt/vignett es/MatchIt.html). EGMAD stands for the equal gender and minimum age difference algorithm for matching.

GLYPH&lt;0&gt;φGLYPH&lt;0&gt;LGLYPH&lt;0&gt;VGLYPH&lt;0&gt;RGLYPH&lt;0&gt;

J. Zhang et al.

shuffling the subtyping labels among the cases in most of the experimental  settings  ( p &lt; 0.05;  Table  2).  Such  improvement  was  at  the marginal significance levels for both subtype4 with the knn matching algorithm and subtype3 with the optimal matching algorithm ( p &lt; 0.06; Table 2).

As  a  comparison,  we  also  applied  a  clustering  approach  (i.e.,  kmeans)  to  the  volumetric  deviation  data  to  identify  ASD  subtypes (eMethods 3). Among the 4 ASD clusters identified by k-means, the indexes of homogeneity were all smaller than 3. Following the same casecontrol  classification  procedure  described  above,  we  found  that  the mean  classification  accuracy  given  by  four  different  matching  algorithms for four clusters was 0.68 (eMethods 3), which was lower than the mean accuracy of 0.75 given by our RFHCC algorithm ( p &lt; 0.01). At the same time, we also used the raw gray matter volume of the 140 brain regions to perform cluster analysis and found that neither RFHCC nor kmeans could identify any significant cluster in the data (eMethods 4).

Comparing the feature importance scores among the classifiers for 4 subtypes, we found that the correlations of the feature importance scores between each pair of classifiers could not reach any significance level, suggesting that different subtypes had different patterns of deviations in gray matter volume from the typical controls (eFigure 5; eTables 11 -14). We also found that 100 % ( N = 39), 93 % ( N = 13), 40 % ( N = 4) and 17 % (N = 1) of the brain regions, which were associated with the ADOSsocial score, contributed to the case-control classification (i.e., positive feature importance scores) in these four subtypes, respectively. Specifically, the SPG, IPG and IFG in subtype1, the DRN and VTA in subtype2, the IOG and ITG in subtype3 which had the largest correlations with the ADOS-social score all had positive feature importance scores in the casecontrol classification.

## 4. Discussion

In this study, we proposed a novel RFHCC algorithm to identify the subtypes of ASD in each of which the cases shared the same multivariate pattern of brain-symptom association. Using a big dataset for ASD ( N = 1717), we found 4 subtypes each with a distinct combination of brain regions that was associated with the same core symptom of ASD (i.e., the social subscale of ADOS). The association patterns in these 4 subtypes highlighted the frontoparietal cortex, the midbrain, the ventral visual pathway  and  the  social  reward  evaluation  regions  in  the  brain.  The subtyping outperformed the classifications both without subtyping and with a subtyping given by k-means. The established subtyping can help with  identifying  the  key  brain  regions  that  are  associated  with  core symptoms  specifically  within  each  subtype,  which  provides  a  novel neuroimaging model for precision psychiatry (Saggar and Uddin, 2019).

The identified patterns of brain-symptom association provided a new neuroimaging model to parse the heterogeneity of ASD. In the literature, the  same  symptom  score  of  ASD  (i.e.,  stereotyped  behaviors  and restricted interests) has been associated with different brain regions in different studies (e.g., right superior frontal gyrus (Eilam-Stock et al., 2016),  motor  cortex  (Supekar  and  Menon,  2015),  and  orbitofrontal cortex (Hegarty et al., 2020)). It has long been hypothesized that ASD subtypes  might  be  underlying  the  observed  heterogeneity  (Amaral, 2011; Kim, 2020; Masi et al., 2017). However, it is difficult to identify the subtypes owing to complex dependencies of brain-symptom associations  on  individualized  deviations  from  typical  brain  development. This difficulty had also been demonstrated in our sample, as no significant brain-symptom association could be identified among all patients using the CCA. Therefore, we first quantified the individualized neuroanatomical changes by estimating the volumetric deviations of regional gray matter volume from the typical brain developmental trajectory in the controls. Using these individualized brain changes, we proposed a tree-based algorithm to split the sample into subgroups by maximizing the consistency of brain-symptom associations at each split. After the tree-splitting, the brain-symptom association was consistent within each subtype  as  demonstrated  by  high  homogeneity  (i.e., Hs = 10).  In contrast, 54 % of the brain regions had inconsistent associations with the same symptom score (i.e., the ADOS-social subscale) among 4 subtypes, i.e., the association was positive in one subtype but negative in another.

The  identified  4  subtypes  presented  distinct  patterns  of  brainsymptom association with the social subscale of ADOS. In subtype 1, the ADOS-social score was mainly positively associated with brain regions in the frontoparietal network for social attention, including the right parietal gyri (SPG, IPG) and the left IFG, which have been associated with social functions in ASD (Alcala-Lopez et al., 2018; Plitt et al., 2015)  possibly  through  affecting  social  attention  (Chita-Tegmark, 2016). In subtype 2, the midbrain regions (e.g., the DRN and the left VTA), that have been implicated in social motivation (Borland et al., 2018; Luo et al., 2017; Neuhaus et al., 2019) had the greatest negative brain-symptom associations with the ADOS-social subscale score. Animal  experiments  have  already  demonstrated  that  stimulating  DRN neurons can improve the social performance in autistic-like mice (Luo et al., 2017) and the oxytocin receptors in the VTA are critical for the reinforcing  properties  of  social  interactions  (Borland  et  al.,  2018). Interestingly, two distinct GABAergic pathways from VTA to DRN have been  discovered  to  be ' anti-reward ' and ' pro-reward ' ,  which  might contribute to the decreased social motivation in ASD (Neuhaus et al., 2019). In subtype 3, the ADOS-social score had the greatest positive associations with both the left IOG and ITG. In literatures, the hyperconnectivity  between  the  ITG  and  the  Heschl ' s  has  been  associated with more severe impairment in the ASD children ' s social communication (Kim et al., 2021), while hyper-connectivity between the temporal gyri  (MTG,  ITG,  STG)  was  correlated  with  more  severe  ADOS-social symptoms (Cheng et al., 2015). Together with the IOG, these brain regions are within the ventral visual pathway, which has been implicated in perceiving social signals during social interactions (Di Martino et al., 2009). In subtype 4, the ACC and the right pallidum had the greatest negative brain-symptom associations with the ADOS-social score. The ACC  has  been  implicated  in  social  deficits  in  a  ASD  mouse  model, namely the Shank3 mutant mice (Guo et al., 2019; Wang et al., 2019), while social avoidance has been associated with the neuroanatomical features of the striatum including the pallidum (Evans et al., 2015). The involvement of both areas may highlight the coupling of both social evaluation (Rigney et al., 2018) and reward processing (Haber, 2017) underlying the social impairment in this subtype.

These brain regions differentially associated with the ADOS-social score also contributed to the case-control classification. The SPG, IPG and IFG in subtype 1, the DRN and VTA in subtype 2, the IOG and ITG in subtype 3 all had positive feature importance scores, which indicated that significant changes in these regional gray matter volume in ASD differentiated them from the controls. As demonstrated by the permutation  tests,  these  subtypes  that  were  defined  by  the  brain-symptom associations  significantly  improved  the  case-control  classification  accuracy. These results might provide new evidence that the differential brain-symptom associations reflected distinct neuroanatomical abnormalities in these ASD subtypes. Subtyping of ASD might be an important way for the stratification of cases into groups each with a same pattern of underlying neurobiological bases.

## 4.1. Limitations

Our study has several limitations. First, the current approach based on  the  brain-symptom  association  required  both  neuroimaging  measurements  and  symptom  assessments,  which  limited  the  sample  size used in this study owing to the missing data, especially the ADOS scores, in  the  ABIDE  database.  Future  studies  with  larger  sample  sizes  are needed for further validation of the subtyping. Second, the midbrain nuclei (e.g., VTA, DRN, etc.) might be too small to be mapped accurately onto individual brains by the automated alignment algorithm, so our findings for subtype 2 need to be interpreted with caution.

GLYPH&lt;0&gt;φGLYPH&lt;0&gt;LGLYPH&lt;0&gt;VGLYPH&lt;0&gt;RGLYPH&lt;0&gt;

J. Zhang et al.

## 5. Conclusion

The  proposed  RFHCC  algorithm  enabled  the  discovery  of  4  ASD subtypes according to their distinct brain-symptom association patterns. These  findings  suggest  that  the  heterogeneity  of  ASD  might  reflect changes in different subsystems of the social brain, especially including social attention, motivation, perceiving and evaluation.

Supplementary data to this article can be found online at https://doi. org/10.1016/j.jad.2023.04.102.

## Funding

This  study  was  partially  supported  by  grants  from  the  National Natural Science Foundation of China (No.s 82272079 and 71834002), the Science and Technology Commission of Shanghai Municipality (No. 20ZR1404900), the Shanghai Municipal Science and Technology Major Project (No.s: 2018SHZDZX01 and 2021SHZDZX0103).

## CRediT authorship contribution statement

QL, JJZ and FL conceived of the original idea, JJZ, SFF, and YY analyzed the data, QL, SFF, FL, and YY interpreted the findings, JJZ and QL wrote the first draft of the manuscript, QL, SFF, FL, and YY made critical revisions of the manuscript.

## Code availability

The python code of this study is available at https://github.com/xdrzjj/ABIDE.

## Role of the funding source

The funding agencies only provided research funding, they did not contribute to the analysis or results, and the views presented in this manuscript are those of the authors and may not reflect those of the funding agencies.

## Declaration of competing interest

All  authors  report  no  financial  relationships  with  commercial interests.

## Data availability

The data used in this study are openly accessable through the Autism Brain Imaging Data Exchange at the following website: https://fcon\_1000.projects.nitrc.org/indi/abide/.

## Acknowledgements

The authors acknowledge the efforts of Edmund T. Rolls in proofreading the article.

## References

- Aglinskas, A., Hartshorne, J.K., Anzellotti, S., 2022. Contrastive machine learning reveals the structure of neuroanatomical variation within autism. Science 376, 1070 -1074.
- Alcala-Lopez, D., Smallwood, J., Jefferies, E., Van Overwalle, F., Vogeley, K., Mars, R.B., Turetsky, B.I., Laird, A.R., Fox, P.T., Eickhoff, S.B., Bzdok, D., 2018. Computing the social brain connectome across systems and states. Cereb. Cortex 28 (7), 2207 -2232. https://doi.org/10.1093/cercor/bhx121.
- Amaral, D.G., 2011. The promise and the pitfalls of autism research: an introductory note for new autism researchers. Brain Res. 1380, 3 -9. https://doi.org/10.1016/j. brainres.2010.11.077.
- Ashburner, J., Friston, K.J., 2000. Voxel-based morphometry -the methods. NeuroImage 11 (6 Pt 1), 805 -821. https://doi.org/10.1006/nimg.2000.0582.
- Ashburner, J., Friston, K.J., 2005. Unified segmentation. NeuroImage 26 (3), 839 -851. https://doi.org/10.1016/j.neuroimage.2005.02.018.
- Ashburner, J., Friston, K.J., 2011. Diffeomorphic registration using geodesic shooting and Gauss-Newton optimisation. NeuroImage 55 (3), 954 -967. https://doi.org/ 10.1016/j.neuroimage.2010.12.049.
- Assaf, M., Jagannathan, K., Calhoun, V.D., Miller, L., Stevens, M.C., Sahl, R., O ' Boyle, J. G., Schultz, R.T., Pearlson, G.D., 2010. Abnormal functional connectivity of default mode sub-networks in autism spectrum disorder patients. NeuroImage 53 (1), 247 -256. https://doi.org/10.1016/j.neuroimage.2010.05.067.

Boedhoe, P.S.W., van Rooij, D., Hoogman, M., Twisk, J.W.R., Schmaal, L., Abe, Y., Alonso, P., Ameis, S.H., Anikin, A., Anticevic, A., Arango, C., Arnold, P.D., Asherson, P., Assogna, F., Auzias, G., Banaschewski, T., Baranov, A., Batistuzzo, M. C., Baumeister, S., Baur-Streubel, R., Behrmann, M., Bellgrove, M.A., Benedetti, F., Beucke, J.C., Biederman, J., Bollettini, I., Bose, A., Bralten, J., Bramati, I.E., Brandeis, D., Brem, S., Brennan, B.P., Busatto, G.F., Calderoni, S., Calvo, A., Calvo, R., Castellanos, F.X., Cercignani, M., Chaim-Avancini, T.M., Chantiluke, K.C., Cheng, Y., Cho, K.I.K., Christakou, A., Coghill, D., Conzelmann, A., Cubillo, A.I., Dale, A.M., Dallaspezia, S., Daly, E., Denys, D., Deruelle, C., Di Martino, A., Dinstein, I., Doyle, A.E., Durston, S., Earl, E.A., Ecker, C., Ehrlich, S., Ely, B.A., Epstein, J.N., Ethofer, T., Fair, D.A., Fallgatter, A.J., Faraone, S.V., Fedor, J., Feng, X., Feusner, J.D., Fitzgerald, J., Fitzgerald, K.D., Fouche, J.P., Freitag, C.M., Fridgeirsson, E.A., Frodl, T., Gabel, M.C., Gallagher, L., Gogberashvili, T., Gori, I., Gruner, P., Gursel, D.A., Haar, S., Haavik, J., Hall, G.B., Harrison, N.A., Hartman, C. A., Heslenfeld, D.J., Hirano, Y., Hoekstra, P.J., Hoexter, M.Q., Hohmann, S., Hovik, M.F., Hu, H., Huyser, C., Jahanshad, N., Jalbrzikowski, M., James, A., Janssen, J., Jaspers-Fayer, F., Jernigan, T.L., Kapilushniy, D., Kardatzki, B., Karkashadze, G., Kathmann, N., Kaufmann, C., Kelly, C., Khadka, S., King, J.A., Koch, K., Kohls, G., Konrad, K., Kuno, M., Kuntsi, J., Kvale, G., Kwon, J.S., Lazaro, L., Lera-Miguel, S., Lesch, K.P., Hoekstra, L., Liu, Y., Lochner, C., Louza, M.R., Luna, B., Lundervold, A.J., Malpas, C.B., Marques, P., Marsh, R., Martinez-Zalacain, I., MataixCols, D., Mattos, P., McCarthy, H., McGrath, J., Mehta, M.A., Menchon, J.M., Mennes, M., Martinho, M.M., Moreira, P.S., Morer, A., Morgado, P., Muratori, F., Murphy, C.M., Murphy, D.G.M., Nakagawa, A., Nakamae, T., Nakao, T., NamazovaBaranova, L., Narayanaswamy, J.C., Nicolau, R., Nigg, J.T., Novotny, S.E., Nurmi, E. L., Weiss, E.O., O ' Gorman Tuura, R.L., O ' Hearn, K., O ' Neill, J., Oosterlaan, J., Oranje, B., Paloyelis, Y., Parellada, M., Pauli, P., Perriello, C., Piacentini, J., Piras, F., Piras, F., Plessen, K.J., Puig, O., Ramos-Quiroga, J.A., Reddy, Y.C.J., Reif, A., Reneman, L., Retico, A., Rosa, P.G.P., Rubia, K., Rus, O.G., Sakai, Y., Schrantee, A., Schwarz, L., Schweren, L.J.S., Seitz, J., Shaw, P., Shook, D., Silk, T.J., Simpson, H.B., Skokauskas, N., Soliva Vila, J.C., Solovieva, A., Soreni, N., Soriano-Mas, C., Spalletta, G., Stern, E.R., Stevens, M.C., Stewart, S.E., Sudre, G., Szeszko, P.R., Tamm, L., Taylor, M.J., Tolin, D.F., Tosetti, M., Tovar-Moll, F., Tsuchiyagaito, A., van Erp, T.G.M., van Wingen, G.A., Vance, A., Venkatasubramanian, G., Vilarroya, O., Vives-Gilabert, Y., von Polier, G.G., Walitza, S., Wallace, G.L., Wang, Z., Wolfers, T., Yoncheva, Y.N., Yun, J.Y., Zanetti, M.V., Zhou, F., Ziegler, G. C., Zierhut, K.C., Zwiers, M.P., Group, E.A.W., Group, E.O.W., Thompson, P.M., Stein, D.J., Buitelaar, J., Franke, B., van den Heuvel, O.A., 2020. Subcortical brain volume, regional cortical thickness, and cortical surface area across disorders: findings from the ENIGMA ADHD, ASD, and OCD working groups. Am J Psychiatry 177 (9), 834 -843. https://doi.org/10.1176/appi.ajp.2020.19030331.

- Borland, J.M., Grantham, K.N., Aiani, L.M., Frantz, K.J., Albers, H.E., 2018. Role of oxytocin in the ventral tegmental area in social reinforcement. Psychoneuroendocrinology 95, 128 -137. https://doi.org/10.1016/j.

psyneuen.2018.05.028.

- Cheng, W., Rolls, E.T., Gu, H., Zhang, J., Feng, J., 2015. Autism: reduced connectivity between cortical areas involved in face expression, theory of mind, and the sense of self. Brain 138 (Pt 5), 1382 -1393. https://doi.org/10.1093/brain/awv051.
- Chita-Tegmark, M., 2016. Social attention in ASD: a review and meta-analysis of eyetracking studies. Res. Dev. Disabil. 48, 79 -93. https://doi.org/10.1016/j. ridd.2015.10.011.
- Cohen, J., 1988. Statistical Power Analysis for the Behavioral Sciences, (2nd ed ed.). Lawrence Erlbaum Associates.
- Di Martino, A., O ' Connor, D., Chen, B., Alaerts, K., Anderson, J.S., Assaf, M., Balsters, J. H., Baxter, L., Beggiato, A., Bernaerts, S., Blanken, L.M., Bookheimer, S.Y., Braden, B.B., Byrge, L., Castellanos, F.X., Dapretto, M., Delorme, R., Fair, D.A., Fishman, I., Fitzgerald, J., Gallagher, L., Keehn, R.J., Kennedy, D.P., Lainhart, J.E., Luna, B., Mostofsky, S.H., Muller, R.A., Nebel, M.B., Nigg, J.T., O ' Hearn, K., Solomon, M., Toro, R., Vaidya, C.J., Wenderoth, N., White, T., Craddock, R.C., Lord, C., Leventhal, B., Milham, M.P., 2017. Enhancing studies of the connectome in autism using the autism brain imaging data exchange II. Sci. Data 4, 170010. https://doi.org/10.1038/sdata.2017.10.
- Di Martino, A., Ross, K., Uddin, L.Q., Sklar, A.B., Castellanos, F.X., Milham, M.P., 2009. Functional brain correlates of social and nonsocial processes in autism spectrum disorders: an activation likelihood estimation meta-analysis. Biol. Psychiatry 65 (1), 63 -74. https://doi.org/10.1016/j.biopsych.2008.09.022.
- Di Martino, A., Yan, C.G., Li, Q., Denio, E., Castellanos, F.X., Alaerts, K., Anderson, J.S., Assaf, M., Bookheimer, S.Y., Dapretto, M., Deen, B., Delmonte, S., Dinstein, I., ErtlWagner, B., Fair, D.A., Gallagher, L., Kennedy, D.P., Keown, C.L., Keysers, C., Lainhart, J.E., Lord, C., Luna, B., Menon, V., Minshew, N.J., Monk, C.S., Mueller, S., Muller, R.A., Nebel, M.B., Nigg, J.T., O ' Hearn, K., Pelphrey, K.A., Peltier, S.J., Rudie, J.D., Sunaert, S., Thioux, M., Tyszka, J.M., Uddin, L.Q., Verhoeven, J.S., Wenderoth, N., Wiggins, J.L., Mostofsky, S.H., Milham, M.P., 2014. The autism brain imaging data exchange: towards a large-scale evaluation of the intrinsic brain architecture in autism. Mol. Psychiatry 19 (6), 659 -667. https://doi.org/10.1038/ mp.2013.78.
- Duan, X., Chen, H., He, C., Long, Z., Guo, X., Zhou, Y., Uddin, L.Q., Chen, H., 2017. Resting-state functional under-connectivity within and between large-scale cortical networks across three low-frequency bands in adolescents with autism. Prog. Neuro-

J. Zhang et al.

- Psychopharmacol. Biol. Psychiatry 79 (Pt B), 434 -441. https://doi.org/10.1016/j. pnpbp.2017.07.027.
- Eilam-Stock, T., Wu, T., Spagna, A., Egan, L.J., Fan, J., 2016. Neuroanatomical alterations in high-functioning adults with autism Spectrum disorder. Front. Neurosci. 10, 237. https://doi.org/10.3389/fnins.2016.00237.
- Elder, J.H., Kreider, C.M., Brasher, S.N., Ansell, M., 2017. Clinical impact of early diagnosis of autism on the prognosis and parent-child relationships. Psychol. Res. Behav. Manag. 10, 283 -292. https://doi.org/10.2147/PRBM.S117499.
- Evans, D.W., Lazar, S.M., Boomer, K.B., Mitchel, A.D., Michael, A.M., Moore, G.J., 2015. Social cognition and brain morphology: implications for developmental brain dysfunction. Brain Imaging Behav. 9 (2), 264 -274. https://doi.org/10.1007/s11682014-9304-1.
- Gu, J., Kanai, R., 2014. What contributes to individual differences in brain structure? Front. Hum. Neurosci. 8, 262. https://doi.org/10.3389/fnhum.2014.00262.
- Guo, B., Chen, J., Chen, Q., Ren, K., Feng, D., Mao, H., Yao, H., Yang, J., Liu, H., Liu, Y., Jia, F., Qi, C., Lynn-Jones, T., Hu, H., Fu, Z., Feng, G., Wang, W., Wu, S., 2019. Anterior cingulate cortex dysfunction underlies social deficits in Shank3 mutant mice. Nat. Neurosci. 22 (8), 1223 -1234. https://doi.org/10.1038/s41593-019-04459.
- Haber, S.N., 2017. Anatomy and connectivity of the reward circuit. In: Decision Neuroscience, pp. 3 -19. https://doi.org/10.1016/b978-0-12-805308-9.00001-4.
- Hahamy, A., Behrmann, M., Malach, R., 2015. The idiosyncratic brain: distortion of spontaneous connectivity patterns in autism spectrum disorder. Nat. Neurosci. 18 (2), 302 -309. https://doi.org/10.1038/nn.3919.
- Hegarty, I.J., Lazzeroni, L.C., Raman, M.M., Hallmayer, J.F., Cleveland, S.C., Wolke, O. N., Phillips, J.M., Reiss, A.L., Hardan, A.Y., 2020. Genetic and environmental influences on corticostriatal circuits in twins with autism. J. Psychiatry Neurosci. 45 (3), 188 -197. https://doi.org/10.1503/jpn.190030.
- Hong, S.J., Vogelstein, J.T., Gozzi, A., Bernhardt, B.C., Yeo, B.T.T., Milham, M.P., Di Martino, A., 2020. Toward neurosubtypes in autism. Biol. Psychiatry 88 (1), 111 -128. https://doi.org/10.1016/j.biopsych.2020.03.022.
- Hrdlicka, M., Dudova, I., Beranova, I., Lisy, J., Belsan, T., Neuwirth, J., Komarek, V., Faladova, L., Havlovicova, M., Sedlacek, Z., Blatny, M., Urbanek, T., 2005. Subtypes of autism by cluster analysis based on structural MRI data. Eur. Child. Adolesc. Psychiatry 14 (3), 138 -144. https://doi.org/10.1007/s00787-005-0453-z.
- Huerta, M., Lord, C., 2012. Diagnostic evaluation of autism spectrum disorders. Pediatr Clin North Am 59 (1), 103 -111. https://doi.org/10.1016/j.pcl.2011.10.018 xi.
- Hull, J.V., Dokovna, L.B., Jacokes, Z.J., Torgerson, C.M., Irimia, A., Van Horn, J.D., 2016. Resting-state functional connectivity in autism Spectrum disorders: a review. Front. Psychiatry 7, 205. https://doi.org/10.3389/fpsyt.2016.00205.
- Jeste, S.S., Geschwind, D.H., 2014. Disentangling the heterogeneity of autism spectrum disorder through genetic findings. Nat. Rev. Neurol. 10 (2), 74 -81. https://doi.org/ 10.1038/nrneurol.2013.278.
- Kim, D., Lee, J.Y., Jeong, B.C., Ahn, J.H., Kim, J.I., Lee, E.S., Kim, H., Lee, H.J., Han, C.E., 2021. Overconnectivity of the right Heschl ' s and inferior temporal gyrus correlates with symptom severity in preschoolers with autism spectrum disorder. Autism Res. 14 (11), 2314 -2329. https://doi.org/10.1002/aur.2609.
- Kim, S.H., 2020. Decomposing heterogeneity in autism Spectrum disorder through neurosubtyping. Biol. Psychiatry 87 (12), e37 -e38. https://doi.org/10.1016/j. biopsych.2020.04.019.
- Laidi, C., Boisgontier, J., Chakravarty, M.M., Hotier, S., d ' Albis, M.A., Mangin, J.F., Devenyi, G.A., Delorme, R., Bolognani, F., Czech, C., Bouquet, C., Toledano, E., Bouvard, M., Gras, D., Petit, J., Mishchenko, M., Gaman, A., Scheid, I., Leboyer, M., Zalla, T., Houenou, J., 2017. Cerebellar anatomical alterations and attention to eyes in autism. Sci. Rep. 7 (1), 12008. https://doi.org/10.1038/s41598-017-11883-w.
- Laidi, C., Floris, D.L., Tillmann, J., Elandaloussi, Y., Zabihi, M., Charman, T., Wolfers, T., Durston, S., Moessnang, C., Dell ' Acqua, F., Ecker, C., Loth, E., Murphy, D., BaronCohen, S., Buitelaar, J.K., Marquand, A.F., Beckmann, C.F., Frouin, V., Leboyer, M., Duchesnay, E., Coupe, P., Houenou, J., Group, E.-A.L., 2022. Cerebellar atypicalities in autism? Biol. Psychiatry 92 (8), 674 -682. https://doi.org/10.1016/j. biopsych.2022.05.020.
- Liu, G., Shi, L., Qiu, J., Lu, W., 2022. Two neuroanatomical subtypes of males with autism spectrum disorder revealed using semi-supervised machine learning. Mol. Autism 13 (1), 9. https://doi.org/10.1186/s13229-022-00489-3.
- Luo, J., Feng, Q., Wei, L., Luo, M., 2017. Optogenetic activation of dorsal raphe neurons rescues the autistic-like social deficits in Shank3 knockout mice. Cell Res. 27 (7), 950 -953. https://doi.org/10.1038/cr.2017.52.
- Masi, A., DeMayo, M.M., Glozier, N., Guastella, A.J., 2017. An overview of autism Spectrum disorder, heterogeneity and treatment options. Neurosci. Bull. 33 (2), 183 -193. https://doi.org/10.1007/s12264-017-0100-y.
- Mei, T., Llera, A., Floris, D.L., Forde, N.J., Tillmann, J., Durston, S., Moessnang, C., Banaschewski, T., Holt, R.J., Baron-Cohen, S., Rausch, A., Loth, E., Dell ' Acqua, F., Charman, T., Murphy, D.G.M., Ecker, C., Beckmann, C.F., Buitelaar, J.K., Group, E.A.L., 2020. Gray matter covariations and core symptoms of autism: the EU-AIMS Longitudinal European Autism Project. Mol Autism 11 (1), 86. https://doi.org/ 10.1186/s13229-020-00389-4.
- Modabbernia, A., Reichenberg, A., Ing, A., Moser, D.A., Doucet, G.E., Artiges, E., Banaschewski, T., Barker, G.J., Becker, A., Bokde, A.L.W., Quinlan, E.B., Desrivieres, S., Flor, H., Frohner, J.H., Garavan, H., Gowland, P., Grigis, A., Grimmer, Y., Heinz, A., Insensee, C., Ittermann, B., Martinot, J.L., Martinot, M.P., Millenet, S., Nees, F., Orfanos, D.P., Paus, T., Penttila, J., Poustka, L., Smolka, M.N., Stringaris, A., van Noort, B.M., Walter, H., Whelan, R., Schumann, G., Frangou, S., Consortium, I., 2021. Linked patterns of biological and environmental covariation with brain structure in adolescence: a population-based longitudinal study. Mol Psychiatry 26 (9), 4905 -4918. https://doi.org/10.1038/s41380-020-0757-x.
- Neuhaus, E., Webb, S.J., Bernier, R.A., 2019. Linking social motivation with social skill: the role of emotion dysregulation in autism spectrum disorder. Dev. Psychopathol. 31 (3), 931 -943. https://doi.org/10.1017/S0954579419000361.
- Plitt, M., Barnes, K.A., Wallace, G.L., Kenworthy, L., Martin, A., 2015. Resting-state functional connectivity predicts longitudinal change in autistic traits and adaptive functioning in autism. Proc. Natl. Acad. Sci. U. S. A. 112 (48), E6699 -E6706. https://doi.org/10.1073/pnas.1510098112.
- Rajapakse, J.C., Giedd, J.N., Rapoport, J.L., 1997. Statistical approach to segmentation of single-channel cerebral MR images. IEEE Trans. Med. Imaging 16, 176 -186.
- Rigney, A.E., Koski, J.E., Beer, J.S., 2018. The functional role of ventral anterior cingulate cortex in social evaluation: disentangling valence from subjectively rewarding opportunities. Soc. Cogn. Affect. Neurosci. 13 (1), 14 -21. https://doi.org/ 10.1093/scan/nsx132.
- Rolls, E.T., Huang, C.C., Lin, C.P., Feng, J., Joliot, M., 2020. Automated anatomical labelling atlas 3. NeuroImage 206, 116189. https://doi.org/10.1016/j. neuroimage.2019.116189.
- Saggar, M., Uddin, L.Q., 2019. Pushing the boundaries of psychiatric neuroimaging to ground diagnosis in biology. eNeuro 6 (6). https://doi.org/10.1523/ENEURO.038419.2019.
- Shan, X., Uddin, L.Q., Xiao, J., He, C., Ling, Z., Li, L., Huang, X., Chen, H., Duan, X., 2022. Mapping the heterogeneous brain structural phenotype of autism spectrum disorder using the normative model. Biol. Psychiatry 91 (11), 967 -976. https://doi. org/10.1016/j.biopsych.2022.01.011.
- Smith, S.M., Nichols, T.E., Vidaurre, D., Winkler, A.M., Behrens, T.E., Glasser, M.F., Ugurbil, K., Barch, D.M., Van Essen, D.C., Miller, K.L., 2015. A positive-negative mode of population covariation links brain connectivity, demographics and behavior. Nat. Neurosci. 18 (11), 1565 -1567. https://doi.org/10.1038/nn.4125.
- Supekar, K., Menon, V., 2015. Sex differences in structural organization of motor systems and their dissociable links with repetitive/restricted behaviors in children with autism. Mol. Autism 6, 50. https://doi.org/10.1186/s13229-015-0042-z.
- Thurm, A., Swedo, S.E., 2022. The importance of autism research. Dialogues Clin. Neurosci. 14 (3), 219 -222. https://doi.org/10.31887/DCNS.2012.14.3/athurm.
- Tohka, J., Zijdenbos, A., Evans, A., 2004. Fast and robust parameter estimation for statistical partial volume models in brain MRI. NeuroImage 23 (1), 84 -97. https:// doi.org/10.1016/j.neuroimage.2004.05.007.
- Volkmar, F.R., Reichow, B., McPartland, J., 2022. Classification of autism and related conditions: progress, challenges, and opportunities. Dialogues Clin. Neurosci. 14 (3), 229 -237. https://doi.org/10.31887/DCNS.2012.14.3/fvolkmar.
