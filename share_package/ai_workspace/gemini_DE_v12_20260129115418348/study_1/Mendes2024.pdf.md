![Image](./Mendes2024_artifacts/image_000000_219af23b6dab78bb2610a0346afa29b57ef3d3d929b088c5698c2522e2dbbf37.png)

## GPT-  based normative models of brain sMRI correlate with dimensional psychopathology

Sergio Leonardo Mendes a , Walter Hugo Lopez Pinaya b , Pedro Mario Pan c , Ary Gadelha c , Sintia Belangero c , Andrea Parolin Jackowski c,d , Luis Augusto Rohde e,f,g , Euripedes Constantino Miguel h , João Ricardo Sato a,i a Center of Mathematics, Computing, and Cognition, Universidade Federal do ABC, S ã o Paulo - SP , Brazil

b Department of Biomedical Engineering, King's College London, London, United Kingdom

c Escola Paulista de Medicina, Universidade Federal de S ã o Paulo, S ã o Paulo - SP , Brazil

d Department of Education, ICT and Learning, Ø stfold University College, Halden, Norway

e ADHD Outpatient Program &amp; Developmental Psychiatry Program, Hospital de Clinicas de Porto Alegre, Federal University of Rio Grande do Sul, Brazil f Medical Council UNIFAJ and UNIMAX, Indaiatuba, Brazil g National Institute of Developmental Psychiatry &amp; National Center for Innovation and Research in Mental Health, S ã o Paulo - SP , Brazil

h Department and Institute of Psychiatry, Universidade de S ã o Paulo (USP), S ã o Paulo - SP , Brazil i Big Data Section, Hospital Israelita Albert Einstein, S ã o Paulo - SP , Brazil

Corresponding Author: Sergio Leonardo Mendes (sergioleonardomendes@gmail.com)

## ABSTRACT

Generative Pre-  trained Transformer (GPT) models have been widely used for language tasks with surprising results. Furthermore,  neuroimaging  studies  using  deep  generative  normative  modeling  show  promise  in  detecting  brain abnormalities  from  brain  structural  MRI  (sMRI).  Meanwhile,  psychiatric  disorders  are  typically  diagnosed  through clinical assessment, which is particularly challenging in children and adolescents who present early symptoms or are in the early stages of the disease. Brain biomarkers research may contribute to the complex task of disentangling typical neurodevelopment from emergent psychiatric disorders. Here, we investigate whether a GPT-  based normative architecture can detect psychiatric symptoms and disorders from brain sMRI of youths. The studied datasets contain measures of dimensional psychopathology: Brazilian High-  Risk Cohort Study (BHRCS, n = 737) and Adolescent Brain Cognitive Development (ABCD, n = 11,031), and scores and diagnostic of psychiatric disorders: Attention Deficit Hyperactivity Disorder (ADHD-  200, n = 922) and Autism Brain Imaging Data Exchange II (ABIDE-  II, n = 580). We examined the associations of all brain regions with: the Child Behavior Checklist (CBCL) symptom groups, ADHD scores, and Autism Spectrum Disorder (ASD) diagnosis. Results showed the whole-  brain typicality likelihood as correlated with social problems (ABCD test set) and ASD diagnosis (ABIDE-  II dataset). Analysis by brain regions linked different areas to several CBCL scales, ADHD scores, and ASD diagnostic. This is the first successful study assessing all dimensional groups of CBCL symptoms, from all brain regions, based exclusively on sMRI. The normative models based on GPT are promising to investigate the gap between the phenotypes of psychiatric conditions and their neurobiological substrates.

Keywords: children, brain structural MRI, GPT models, child behavior checklist, autism spectrum disorder, attention deficit hyperactivity disorder

![Image](./Mendes2024_artifacts/image_000001_f8aac42c67f1c4524863766343d06991b79b2b8a9db2374cb5007559a6a505f3.png)

![Image](./Mendes2024_artifacts/image_000002_e5aa1cafeb3166be4af5f3d69798af266f3c2177e280815b4dc98dd89b47c61c.png)

## 1. INTRODUCTION

One of the  big  challenges  psychiatrists  face  is  how  to incorporate  biological  measures  in  diagnosing  mental disorders (  Cuthbert   &amp;   Insel,   2010;   Scarpazza   et al.,   2020). Besides  some  advances  (  Drysdale    et  al.,    2017),  most psychiatric disorders are assessed only by clinical interviews  (  American    Psychiatric    Association,    2013;    Sato et  al.,    2017).  The  early  identification  of  mental  health issues is even more difficult. Investigations indicate that less  than  a  fifth  of  the  American  youths  experiencing symptoms that qualify them for a psychiatric diagnosis are typically identified (  Levitt   et al.,   2007). The time window of childhood and adolescence is of great importance in  the  development  of  psychiatric  disorders  (American Psychiatric   Association,   2013). In these age groups, the investigation of brain markers could provide information regarding  the  pathological  mechanisms  related  to  the nature of these diseases. Moreover, the investigation of objective brain markers may contribute to the complex task  of  disentangling  typical  neurodevelopment  from emergent psychiatric disorders.

Attempts to characterize brains' structural signatures of psychiatric disorders have shown that these conditions are highly heterogeneous (  Cannon   &amp;   Keller,   2006; Cicchetti   &amp;   Rogosch,   1996;   Mar í n,   2016). For instance, similar etiopathological mechanisms may converge to the same symptom in different  patients  (  Cannon    &amp;    Keller, 2006;   Cicchetti   &amp;   Rogosch,   1996). Conversely, several risk factors can result in different clinical phenotypes for distinct  individuals,  depending  on  the  environmental context,  genetic  predisposition,  and  critical  time  window of neurodevelopment (  Cicchetti   &amp;   Rogosch,   1996; Mar í n,   2016). Besides this variability, the overwhelming majority of neuroimaging studies still focus on average group  analysis  and  discard  the  individual  differences (  Marquand   et al.,   2019). To circumvent this constraint, the normative modeling methods offer individual-  specific statistical inferences based on a previously learned pattern of typicality (  Marquand   et al.,   2019). One notable aspect of these methods is their capacity to identify and delineate individual atypicality without relying on a uniform neurobiological pattern among all subjects (  Marquand et al.,   2019).

Recently, neuroimaging studies have used normative modeling to detect and segment brain lesions based on deep generative models (  Baur   et al.,   2021;   Chen   et al., 2020;    Pinaya    et  al.,    2022).  These  algorithms  use  data from  only  typically  developing  (TD)  subjects  to  learn  a probability density function that reflects the scenario of typicality  (  Pinaya    et  al.,    2022).  When  assessed  by  a model, the atypical subjects (with pathological features) are  detected  as  deviations  from  typicality.  As  these models  use  unsupervised  methods,  they  have  the advantage  of  not  depending  on  labeled  or  anomaly examples in the training phase (  Pinaya   et al.,   2022). Furthermore,  all  the  atypical  neuroanatomy  variability  is analyzed at the individual level. That is, the heterogeneity of the individual differences is captured, allowing the mapping of different neurological conditions.

In this context, a recent study by   Pinaya   et al.   (2022) used an innovative approach to achieve state-  of-  the-  art performance for detecting and segmenting brain lesions and tumors from T2-  Flair MRI. Shortly after, this approach was adapted to detect early schizophrenia from brain T1 structural MRI (sMRI) (  Da   Costa   et al.,   2022), exceeding the performance of previous methods. The architecture used in both studies is composed of two models. The first,  called  Vector  Quantized  Variational  Autoencoder (VQ-  VAE) (  Razavi   et al.,   2019;   Van   Den   Oord   et al.,   2017), is responsible for reducing the size of the input MRI from millions  of  voxels  to  a  representation  of  thousands  of latent  discrete  codes.  The  second,  a  Generative  Pretrained Transformer (GPT) (also known as autoregressive Transformer) (  Radford   et al.,   2018,   2019;   Vaswani   et al., 2017) uses the encoded representation generated by the VQ-  VAE to learn a probability density function of the typical  brain.  The  VQ-  VAE  skill  to  reduce  dimensions  and tokenize images and the GPTs' ability to map input data relationships regardless of distance makes them optimal candidates for neuroimaging tasks (  Graham   et al.,   2022; Pinaya   et al.,   2022).

The  results  achieved  by  the  GPT-  based  normative models to detect and segment brain anomalies (  Pinaya et al.,   2022) and to detect early schizophrenia (  Da   Costa et  al.,    2022)  made  us  question  whether  this  approach could be effective for the investigation of dimensional psychopathologies.  Datasets  such  as  the  Adolescent Brain  Cognitive  Development  (ABCD)  (  Casey    et  al., 2018) and the Brazilian High-  Risk Cohort Study (BHRCS) (  Salum   et al.,   2014) include T1 sMRI and scores of psychiatric symptoms from youths. These symptoms were measured  by  the  Child  Behavior  Checklist  (CBCL), which is composed of a list of questions answered by the  parents  (or  caregivers)  of  youths  (  Achenbach    &amp; Rescorla,   2001). Each answer is summed to a score of the symptom categories: aggressive behavior, anxious/ depressed, attention problems, rule-  breaking behavior, somatic  complaints,  social  problems,  thought  problems, and withdrawn (  Achenbach   &amp;   Rescorla,   2001). On a broader level, these categories are combined in scales of  internalizing  and  externalizing  problems,  and  all problems are totalized as the CBCL  total score (  Achenbach   &amp;   Rescorla,   2001). Besides assessing these behavioral  symptoms,  we  also  investigated  how  the trained models performed evaluating scores of Attention

Deficit Hyperactivity Disorder (ADHD) and diagnosis of Autism Spectrum Disorder (ASD). Whereas the behavioral symptoms captured by the CBCL and ADHD scores may capture mild or early-  stage conditions, the positive diagnosis  of  ASD  can  capture  an  already  established disorder. BHRCS, ABCD, Attention Deficit Hyperactivity Disorder  (ADHD-  200),  and  Autism  Brain  Imaging  Data Exchange II (ABIDE-  II) consortiums provide datasets for these  investigations  (  Di    Martino    et  al.,    2017;    Milham et al.,   2012).

This  study  investigates  if  a  normative  architecture composed  of  the  VQ-  VAE  and  GPT  models  is  able  to detect  psychiatric  symptoms  or  disorders  from  brain sMRI of youths. We assess each brain Region of Interest (ROI), from the studied datasets, for associations with the CBCL groups of symptoms, ADHD scores, and diagnostics of ASD.

## 2. MATERIALS AND METHODS

The  adopted  methods  followed  a  logical  sequence  of steps. First, the data were downloaded, filtered, preprocessed, and split into training, validation, and test sets. Then, models were configured and trained from the training and validation sets. Finally, the trained models evaluated  the  unexplored  test  sets,  producing  metrics  to interpret the results. Figure 1 presents an overview of the processing steps.

## 2.1. Data description

The studied data include two datasets containing scores and  diagnoses  of  psychiatric  disorders,  respectively: Attention Deficit Hyperactivity Disorder (ADHD-  200) and Autism Brain Imaging Data Exchange II (ABIDE-  II), and two neurodevelopmental studies containing measures of dimensional  psychopathology:  the  Brazilian  High-  Risk Cohort Study (BHRCS) and Adolescent Brain Cognitive

Development  (ABCD)  (  Casey    et  al.,    2018;    Di    Martino et al.,   2017;   Milham   et al.,   2012;   Salum   et al.,   2014). See Supplemental Information (section 5.1) for further details. The  data  were  collected  and  made  publicly  available according to the guidelines, and approval was provided by the local ethics committee for each project.

## 2.2. Participants

Our  study  targeted  neurodevelopmental  processes  in youth. Thus, we selected subjects younger than 20 years of  age and used only the last collected sMRI image of each subject who participated in the baseline scanning session. A demographic overview of the data is shown in Figure 2 and Table 1. See Supplemental Information (section 5.2) for further details.

## 2.3. MRI preprocessing

The collected sMRI were preprocessed using the VoxelBased Morphometry (VBM) (  Ashburner   &amp;   Friston,   2000). In brief, the VBM spatially normalizes MRI images to the same stereotactic space, allowing the extraction of different brain tissues from images partitioned with correction for nonuniform intensity variations (  Ashburner   &amp;   Friston, 2000). See Supplemental Information (section 5.3) for further details.

## 2.4. Model architecture and training

This  study  follows  and  adapts  a  network  architecture recently proposed by   Pinaya   et al.   (2022) (open access) to detect pathological lesions from brain images. Their approach  became  state-  of-  the-  art  in  brain  anomaly detection using CT and FLAIR imaging data. In brief, the architecture  is  composed  of  two  models,  a  VQ-  VAE (  Pinaya   et al.,   2022;   Razavi   et al.,   2019;   Van   Den   Oord et al.,   2017) and a GPT (also known as an   autoregressive

Fig. 1. Processing pipeline. First, the datasets were downloaded, filtered, and preprocessed using Voxel-  based morphometry. Then, the ABCD dataset was split into test and training/validation sets, whereas out-  of-  sample datasets were fully reserved for the test phase. After the models' training, the results were collected, analyzed, and interpreted from the test sets only.

![Image](./Mendes2024_artifacts/image_000003_2f8c09854d5101f093b3c13a91cd9c5df2eae1b3df2855d515ab3d3913773457.png)

Fig. 2. Datasets demographic distribution. Note the different distributions of age, sex, and psychiatric diagnostics among the datasets. The PD acronym represents different psychiatric diagnostics in each dataset (i.e., ASD for ABIDE-  II, ADHD for ADHD-  200, and all the DSM-  IV or DSM-  V diagnostics, respectively, for BHRCS and ABCD). Note that the distributions of CBCL scores present lower values in ABCD than in BHRCS. This is because the BHRCS screening protocol prioritized participants with higher risks of developing psychiatric disorders. Dotted lines mark the quartiles. Acronyms: CBCL = Child Behavior Checklist, TD = Typically Developing, PD = Psychiatric Diagnostic.

![Image](./Mendes2024_artifacts/image_000004_31281d597f925557760c1280b927fe17d5f1ec3884c08da573aa3d190edfb317.png)

Table 1. Demographic information.

| Data set   | N      | Age, y  ±  SD   | Male, %   | PD, %   | CBCL  ±  SD   |
|------------|--------|-----------------|-----------|---------|---------------|
| ABCD       | 11,031 | 9.9  ±  0.6     | 52.0%     | 15.0%   | 18.1  ±  17.9 |
| ABIDE-  II | 580    | 12.1  ±  3.2    | 73.8%     | 43.3%   | -             |
| ADHD-  200 | 922    | 11.7  ±  3.0    | 63.1%     | 38.7%   | -             |
| BHRCS      | 737    | 9.9  ±  1.9     | 57.1%     | 30.5%   | 27.1  ±  25.2 |

The sample size is denoted by N. Age is presented in years ± standard deviations. The CBCL total score is on a raw scale ± standard deviations. Subjects with any psychiatric disorder are grouped in PD. For ADHD-  200, PD contains the subtypes of ADHD, and for ABIDEII PD includes different levels of the autism spectrum. For ABCD and BHRCS, PD contains subjects with at least one mental disorder according to DSM-  V (ABCD) and DSM-  IV (BHRCS). Note the datasets differences for sample size, age range, psychiatric diagnostic, and CBCL. The CBCL scores present higher values in BHRCA than in ABCD. This is because the BHRCS screening protocol prioritized participants with higher risks of developing psychiatric disorders. Acronyms: CBCL = Child Behavior Checklist, PD = Psychiatric Diagnostic.

Transformer) (  Pinaya   et al.,   2022;   Radford   et al.,   2019; Vaswani   et al.,   2017). The VQ-  VAE learns a latent discrete representation of the brain, while the GPT models the likelihood of occurrence of each discrete element. Like  in  the  referenced  work  (  Pinaya    et  al.,    2022),  our approach is normative as we use only typical subjects (i.e., normal) to train the models. In the test phase, both atypical  and  typical  subjects  are  evaluated.  As  the trained  models  learn  exclusively  from  the  typical,  the atypicality is detected as a deviation from the learned pattern of typicality. Therefore, it is expected that during the test phase, the atypical brain regions present typicality scores that differ from the ones of typical patterns. Similar to other normative models, our approach has the advantage  of  identifying  and  outlining  individual  atypicality without  relying  on  a  uniform  neurobiological  pattern

among all subjects (  Marquand   et al.,   2019). Moreover, the atypical neuroanatomy variability is analyzed at the individual level, and the heterogeneity of the individual differences is captured, allowing the mapping of different neurological conditions. The architecture is depicted in Figure 3. See Supplemental Information (section 5.4) for further details.

## 2.5. Evaluation procedure

The trained network predicts the likelihood of typicality of the  downsized  and  quantized  brain  segments,  where each VQ-  VAE quantized brain segment (from the dimension  of  24x28x24)  corresponds  to  a  specific  serialized GPT vocabulary token (in the sentence of 16,128 tokens) (see Fig. 3). Then, the likelihoods of these brain segments

Fig. 3. Network Architecture. The VQ-  VAE encoder uses brain images x to map the observations to a latent representation z e ( x ) of the brain. Then, the embedding space is used as a codebook by the VQ-  VAE to transform z e ( x ) in discrete representations of q ( z | x ) . This is done by selecting the e x vectors that are more similar (closer) to each z e ( x ) element. This encoding process reduces the input dimension of x from 14.2 million voxels to 16.1 thousand latent discrete codes in q ( z | x ) . Next, the q ( z | x ) codes are serialized to train a GPT that outputs the probabilities (likelihoods) of the discrete elements. Therefore, the likelihood of each discrete element outputted by the GPT corresponds to a specific brain segment of the input observation. The decoder part of the VQ-  VAE that reconstructs the codes q ( z | x ) in a reconstructed image x ! is necessary for the learning (optimization) process of the VQ-  VAE. For didactic purposes, the scheme depicts an architecture for 2 d input images. The accurate dimensions of the architecture are shown right up the illustrations of x , z e (x) , q(z|x) , z q (x) and x ! . This figure was adapted from the originals (  Van   Den   Oord   et al.,   2017;   Vaswani   et al.,   2017).

![Image](./Mendes2024_artifacts/image_000005_505abe7833e62ef9de75a763e4d55b1252df65310b8d0832b92995d36edb1794.png)

| Table 2.  Dataset   | Evaluation plan. Target   | Target   type   | Metrics          | α     |
|---------------------|---------------------------|-----------------|------------------|-------|
| ABCD   test set     | CBCL symptom   groups     | Numeric         | r, p-  value     | <0.05 |
| BHRCS               | CBCL symptom   groups     | Numeric         | r, p-  value     | <0.05 |
| ADHD-  200          | ADHD symptom   groups     | Numeric         | r, p-  value     | <0.05 |
| ABIDE-  II          | ASD diagnosis             | Binary          | AUC,   p-  value | <0.05 |
| All above           | Chronological age         | Numeric         | r, p-  value     | <0.05 |

CBCL symptom groups include: aggression, anxiety, depression, rule break, somatic, attention, social, thought, opposite, conduct, others, internalizing, externalizing, and total scores. ADHD symptom groups include: inattention, hyperactivity/impulsivity, and ADHDindex scores. p-  Values are Bonferroni-  corrected before checking the accepted statistical significance level ( α : alpha). The AUC p-  value is calculated using permutation tests (  Combrisson   &amp;   Jerbi,   2015) (with 1,000 permutations). Acronyms: r = Pearson´s correlation, AUC = area under the receiver operating characteristic curve.

(i.e., vocabulary tokens) are grouped and averaged within the regions of the AAL3 3D brain atlas (  Rolls   et al.,   2020) to provide ROI identification and allow literature comparability.  The  metrics  are  extracted  from  unbiased  and unexplored  data,  according  to  the  plan  presented  in Table 2. See Supplemental Information (section 5.5) for further details.

## 2.6. Models' interpretability

One of the benefits of the adopted approach is that the GPTs output likelihoods of typicality that were indirectly obtained  from  brain  segments.  That  is,  the  brain  sMRI input  is  downscaled  to  latent  discrete  codes  that  are serialized to train GPT models. Therefore, each likelihood of  the  vector  outputted  by  the  GPT,  during  prediction, represents  a  specific  brain  segment.  Thereby,  we  can reshape  and  upscale  the  GPTs'  outputs  to  obtain  the map of likelihoods of typicality (per voxel) in the original 3  d  input  space  of  the  brain  sMRI.  See  Supplemental Information (section 5.6) for further details.

## 2.7. Software and hardware specification

All  source  codes  are  publicly  available  (https://github .  com  /  SergioLeonardoMendes  /  normative  \_  psychiatry). See  Supplemental  Information  (section  5.7)  for  further details.

## 3. RESULTS

After successfully executing the data preprocessing and model  training  steps,  the  evaluation  and  interpretation procedures were conducted as planned (see section 2).

During the evaluation of the ABCD test set ( n = 3,524), the  whole-  brain  predicted  likelihood  of  typicality  presented a statistically significant correlation with CBCL´s social problems scale (corrected p-  value = 0.006). When assessing brains' parcellations, significant correlations were  found  for  the  CBCL  symptom  groups  of:  total, externalizing,  rule-  breaking,  aggressive,  conduct,  and social problems subscales. The detected associations between brain ROIs and psychiatric symptoms are presented  in  Table  3  and  Figure  4a.  See  supplementary Table S1 for statistical metrics.

For the BHRCS dataset (n = 737), the predicted likelihood  of  typicality  of  whole  brains  has  not  presented significant  correlations  to  psychiatric  symptoms  after statistical  corrections.  When  assessing  the  symptoms per brain region, significant correlations were found for the CBCL symptom groups of: social, thought, somatic, depression, anxiety, internalizing, and total scores. The brain ROIs and their association to psychiatric symptoms are presented in Table 4 and Figure 4b. See supplementary Table S2 for statistical metrics.

Evaluating  the  ADHD-  200  dataset  (n  =  922),  the whole-  brain  predicted  likelihood  was  not  found  to  be correlated to the scores of inattentive, hyper-  impulsive, or  ADHD index. However, significant  correlations  were found when assessing the ADHD score groups by brain ROIs. The brain regions and their detected associations are presented in Table 5 and Figure 4c. See Table S3 for statistical metrics.

We  also  used  the  model's  likelihood  of  typicality  to assess  ASD  diagnoses.  From  the  ABIDE-  II dataset ( n = 580), the whole-  brain predicted likelihood of typicality  was  found  to  be  discriminant  of  ASD  (AUC  =  0.60, p-  value  &lt;0.001).  The  evaluation  of  significant  AUC  by brain regions also found several ROIs as being correlated with ASD diagnosis. These ROIs are shown in Table 6 and Figure 4d. See Table S4 for the AUC metrics of ASD diagnosis.

The likelihood of typicality was also used to assess the brain aging as a potential confounder to the other measurements. This analysis showed the whole brain as correlated  with  aging  for  all  but  the  ABCD  dataset. Some brain ROIs were also correlated with brain aging as shown in Table 7. See supplementary Table S5 for the brain aging statistical metrics.

## 4. DISCUSSION

This study investigated whether a normative architecture composed of the VQ-  VAE and GPT models could predict typicality  scores  that  are  correlated  with  psychiatric symptoms  and  disorders,  from  brain  sMRI  of  youths. Models were trained from typical development subjects

Table 3. Brain regions versus CBCL symptom groups in the ABCD test set.

| Brain region                             | Social   | Conduct   | Aggressive   | Rule break   | Externaliz.   | Total   |
|------------------------------------------|----------|-----------|--------------|--------------|---------------|---------|
| Whole brain                              | 0.060    |           |              |              |               |         |
| Precentral gyrus (right)                 | 0.074    |           |              |              |               |         |
| Sup. frontal gyrus, dorsolat. (left)     | 0.084    | 0.073     |              | 0.076        | 0.075         | 0.075   |
| Sup. frontal gyrus, dorsolat. (right)    | 0.080    |           |              |              |               |         |
| Sup. frontal gyrus, medial (left)        | 0.074    | 0.078     |              | 0.080        | 0.075         |         |
| Sup. frontal gyrus, medial (right)       | 0.081    |           |              |              |               |         |
| Middle frontal gyrus (right)             |          |           |              | 0.078        |               |         |
| Inf. frontal gyrus, triang. part (right) |          |           |              | 0.074        |               |         |
| Calcarine fissure surr. cortex (right)   | 0.076    |           |              |              |               |         |
| Cuneus (left)                            | 0.081    |           |              |              |               |         |
| Cuneus (right)                           | 0.098    |           |              |              |               |         |
| Superior occipital gyrus (left)          | 0.091    |           |              |              |               | 0.075   |
| Superior occipital gyrus (right)         | 0.101    |           |              |              |               |         |
| Middle occipital gyrus (left)            | 0.073    |           |              |              |               |         |
| Middle occipital gyrus (right)           | 0.081    |           |              |              |               |         |
| Postcentral gyrus (right)                | 0.085    |           |              |              |               |         |
| Superior parietal gyrus (left)           | 0.123    | 0.080     | 0.082        | 0.087        | 0.089         | 0.098   |
| Superior parietal gyrus (right)          | 0.102    |           |              | 0.080        | 0.079         | 0.081   |
| Inferior parietal gyrus (left)           | 0.090    |           |              |              |               |         |
| Angular gyrus (left)                     | 0.077    |           |              |              |               |         |
| Angular gyrus (right)                    | 0.085    |           |              |              |               |         |
| Precuneus (left)                         | 0.096    |           |              | 0.082        |               |         |
| Precuneus (right)                        | 0.095    |           |              |              |               | 0.077   |

Numbers indicate the statistically significant Pearson's correlation between regions of interest and groups of CBCL symptoms, after correcting p-  values by Bonferroni's method. Note that all brain areas associated with the CBCL groups of symptoms are cortical regions. Some brain regions are correlated with more than one group of symptoms, including the groups of externalizing and total scores. The superior parietal gyrus (left) and superior frontal gyrus (dorsolateral left) are the regions that correlated to the highest number of symptom groups.

(only), using the lower quartiles of CBCL total score from the  ABCD  dataset.  Next,  the  trained  models  predicted the likelihoods (of typicality) for the whole brain and each brain segment of the tested subjects. During the evaluation, the likelihoods of typicality were assessed for associations with (i) the scores of CBCL symptom groups (for the ABCD test set and BHRCS dataset), (ii) the scores of ADHD  (for  ADHD-  200  dataset),  (iii)  the  diagnostics  of ASD (for the ABIDE-  II dataset), and (iv) the chronological age  (for  all  datasets).  The  resulting  p-  values  were  corrected by Bonferroni's method, and the statistically significant associations were identified and charted.

This approach identified that the whole brain's likelihood of typicality  was  correlated  with  social  problems (for the ABCD test set), ASD diagnosis (for the ABIDE-  II dataset),  and  age  (for  the  BHRCS,  ADHD-  200,  and ABIDE-  II  datasets). The analysis by brain region linked different brain ROIs to several CBCL scales, ADHD scores, and ASD diagnostic.

To the best of our knowledge, there are no successful studies assessing all dimensional groups of CBCL symptoms, from all brain regions, based exclusively on sMRI. A  recent  study  tried  to  estimate  the  CBCL  total  score from  the  ABCD  and  BHRCS  datasets  using  structural MRI;  however,  the  authors'  attempt  was  unsuccessful

(  Mendes   et al.,   2023). The same applies to ADHD, where most MRI studies explore approaches based on classification (instead of dimensional score estimation). Therefore, this study will discuss not only based on structural MRI studies, instead, sometimes we will need to resort to the  classical  neuroanatomy  literature  as  well  as  other modalities of MRI (e.g., resting state and functional MRI).

The  assessment  of  the  CBCL  symptom  groups  for ABCD exhibited ROIs that correlated  with  problems  of socializing, conduct, aggressiveness, rule-  breaking, externalizing,  and  total  symptoms.  In  contrast,  the  BHRCS subjects presented ROIs that correlated with problems of socializing, thought, somatic, depression, anxiety, internalizing, and total symptoms (see Tables 3 and 4). Interestingly, all ROIs that emerged from the ABCD are cortical, whereas all ROIs shown for the BHRCS are subcortical regions. While all ABCD symptom groups are externalizing, the BHRCS symptom groups are above all internalizing. In other words, the ABCD exhibited cortical regions correlated  with  externalizing  symptoms,  whereas  the BHRCS presented subcortical regions mostly correlated with internalizing symptoms.

Classical  neuroanatomy  literature  indicates  that  the ROIs  highlighted  in  ABCD,  which  are  associated  with more than one group of symptoms (see Table 3), process

Fig. 4. Brain regions associated with psychiatric symptoms or disorders: (a) CBCL symptoms in ABCD test set, (b) CBCL symptoms in BHRCS dataset, (c) ADHD symptoms in ADHD-  200 dataset, (d) ASD diagnostic in ABIDE-  II. The mapped regions are statistically significant after correcting the p-  values by Bonferroni's method.

![Image](./Mendes2024_artifacts/image_000006_8c4c763dbed4422b854cc9a6ba89df7fe42e48cf194c8ff4140247b6818075e8.png)

Table 4. Brain regions versus CBCL symptom groups in the BHRCS dataset.

| Brain region                                                   | Social   | Thought   | Somatic     | Depression   | Anxiety   | Internaliz.   | Total    |
|----------------------------------------------------------------|----------|-----------|-------------|--------------|-----------|---------------|----------|
| Cerebellar lobules IV and V (left)                             | -  0.188 |           | 0.158       |              |           |               |          |
| Cerebellar vermis lobule III                                   |          |           |             | -  0.160     | -  0.160  | -  0.182      | -  0.173 |
| Cerebellar vermis lobule VI Thalamus ventral posterolat (left) | 0.163    | 0.179     | 0.171 0.169 |              |           | 0.171         | 0.180    |
| Ventral tegmental area (left)                                  |          |           |             |              |           | 0.164         |          |
| Red nucleus (left)                                             |          |           | 0.161       |              |           | 0.169         | 0.162    |

Numbers indicate the statistically significant Pearson's correlation between regions of interest and groups of CBCL symptoms, after correcting p-  values by Bonferroni's method. Note that all brain areas associated with the CBCL groups of symptoms are subcortical regions. Some brain regions are correlated with more than one symptom group, including the internalizing and total scores groups. The cerebellar vermis (lobule III) and thalamus ventral posterolateral (left) are the regions that correlated to the highest number of symptom groups.

Table 5. Brain regions versus ADHD scores in the ADHD-  200 dataset.

| Brain region                                     | Hyperactive / impulsive   | Inattentive   | ADHD index   |
|--------------------------------------------------|---------------------------|---------------|--------------|
| Superior frontal gyrus, dorsolateral (left)      | -  0.203                  | -  0.182      |              |
| Superior frontal gyrus, dorsolateral (right)     | -  0.266                  | -  0.226      |              |
| Superior frontal gyrus, medial (left)            | -  0.229                  | -  0.192      |              |
| Superior frontal gyrus, medial (right)           | -  0.266                  | -  0.231      |              |
| Superior frontal gyrus, medial orbital (left)    | -  0.178                  |               |              |
| Superior frontal gyrus, medial orbital (right)   | -  0.200                  |               |              |
| Gyrus rectus (left)                              | -  0.185                  |               |              |
| Anterior orbital gyrus (right)                   | -  0.204                  | -  0.176      |              |
| Amygdala (right)                                 | -  0.170                  |               |              |
| Cerebellar crus I (left)                         | -  0.177                  |               |              |
| Cerebellar crus II (left)                        | -  0.263                  | -  0.235      |              |
| Cerebellar crus II (right)                       | -  0.187                  |               |              |
| Cerebellar lobule III (left)                     | -  0.174                  |               |              |
| Cerebellar lobule VI (left)                      | -  0.177                  |               |              |
| Cerebellar lobule VII (left)                     | -  0.248                  | -  0.219      |              |
| Cerebellar lobule VIII (right)                   | -  0.191                  |               |              |
| Thalamus ventral anterior (right)                | 0.181                     |               |              |
| Thalamus pulvinar lateral (left)                 | 0.190                     |               |              |
| Anterior cingulate cortex, pregenual (right)     | -  0.182                  |               |              |
| Anterior cingulate cortex, supracallosal (right) | -  0.176                  | -  0.171      |              |
| Substantia nigra, pars compacta (right)          | -  0.248                  | -  0.228      | -  0.170     |

Numbers indicate the statistically significant Pearson's correlation between regions of interest and groups of CBCL symptoms, after correcting p-  values by Bonferroni's method. Note that the brain areas associated with ADHD scores are part of the cortical and subcortical regions. Some regions are correlated with both inattentive and hyperactive/impulsive scores. The substantia nigra pars compacta (right) is the only region correlated with the ADHD index score.

mainly multimodal associative information (  Martin,   2014). The superior and inferior (angular) parietal gyri are sites of  the  superior  order  somatosensory  posterior-  parietal associative cortex, and parieto-  temporo-  occipital associative  cortex,  respectively  (  Martin,    2014).  These  areas integrate functions for the visuomotor spatial consciousness,  perception,  vision,  reading,  and  speech  (  Martin, 2014). The superior, medial, and inferior frontal gyri host the frontal eye fields and the prefrontal associative cortices, which are responsible for thinking, cognition, and behavioral and movement planning (  Martin, 2014). Together, these associative regions are also part of the oculomotor  and  associative  brain  loops  (  Martin,    2014). While the oculomotor loop searches and finds relevant information in a scene through the saccadic eye movements, the associative loop works for the cognition and executive  functional  behaviors  as  well  as  in  planning behavioral strategies (  Martin,   2014).

Other regions highlighted in ABCD, such as the precentral,  postcentral,  precuneus,  cuneus,  occipital  gyri, and calcarine fissure cortex, which are primary and unimodal  association  cortices  (  Kandel    et  al.,    2000),  also participate  in  the  information  processing.  That  is,  the primary  sensory  cortices  (e.g.,  postcentral  gyrus  and calcarine fissure cortex) send information (e.g., somatosensory  and  visual)  to  unimodal  association  cortices (e.g.,  superior  parietal  and  unimodal  occipital  gyri)  to finally arrive at the multimodal associative cortices (e.g., parieto-  temporo-  occipital and prefrontal associative cortices) (  Kandel   et al.,   2000). After processing sensory information,  the  multimodal  associative  cortices  can transmit  information  to  unimodal  cortices  and  finally send instructions to the primary motor cortex (i.e., precentral  gyrus)  to  produce  body  movements  (  Kandel et al.,   2000). The ROIs highlighted in ABCD appear to be coherent  with  regions  expected  to  participate  in  the information processing of the related psychiatric symptoms (i.e.,  social,  conduct,  aggressive,  rule  break,  and externalizing).

In the BHRCS dataset, the associations came exclusively from subcortical structures, including the cerebellar lobules, thalamus, red nucleus, and ventral tegmental area (see Table 4). Despite the well-  recognized role of the cerebellum in sensorimotor functions, studies also indicate  that  it  plays  an  important  role  as  a  modulator  of emotional  processing,  producing  both  excitatory  and inhibitory tones via its connections to the ventral tegmental  area  (  Sacchetti    et  al.,    2009;    Shakiba,    2014).  Among other neuropsychiatric symptoms,  cerebellar lesions were  found  to  elicit  problems  of  depression,  anxiety, socializing skills, and somatic manifestations, therefore, consistent with our findings (  Schmahmann   et al.,   2007; Shakiba,   2014). In addition, the thalamus ventral posterolateral has a relay role, projecting peripheral information (e.g., tact, members position, and temperature sensation) to the somatosensory cortex (  Martin,   2014). Interestingly,

Table 6. Brain regions correlated with ASD in the ABIDE-  II dataset.

| Brain region                                             | ASD diagnosis   |
|----------------------------------------------------------|-----------------|
| Whole brain                                              | 0.600           |
| Precentral gyrus (right)                                 | 0.594           |
| Inferior frontal gyrus, opercular (right)                | 0.582           |
| Cingulate gyrus, middle (left / right)                   | 0.614 / 0.584   |
| Cingulate gyrus, posterior (left)                        | 0.572           |
| Insula (left)                                            | 0.578           |
| Hippocampus (left)                                       | 0.597           |
| Parahippocampal gyrus (left / right)                     | 0.627 / 0.576   |
| Calcarine fissure and surrounding   cortex (left)        | 0.606           |
| Cuneus (left / right)                                    | 0.580 / 0.575   |
| Precuneus (left)                                         | 0.627           |
| Lingual gyrus (left)                                     | 0.588           |
| Postcentral gyrus (left / right)                         | 0.599 / 0.626   |
| Paracentral lobule (left / right)                        | 0.582 / 0.604   |
| Putamen (left)                                           | 0.591           |
| Heschl's gyrus (right)                                   | 0.620           |
| Temporal gyrus, superior (left / right)                  | 0.616 / 0.609   |
| Temporal pole (left / right)                             | 0.605 / 0.594   |
| Cerebellar crus I (right)                                | 0.585           |
| Cerebellar lobules IV/V and IX (right)                   | 0.606           |
| Cerebellar vermis (lobule VIII)                          | 0.599           |
| Thalamus anterior, ventral (left)                        | 0.586           |
| Thalamus pulvinar, medial (left)                         | 0.603           |
| Anterior cingulate cortex, supracallosal  (left / right) | 0.587 / 0.593   |
| Ventral tegmental area (right)                           | 0.591           |
| Substantia nigra, pars compacta (right)                  | 0.569           |

Numbers indicate the statistically significant AUC between brain ROIs and ASD diagnosis, after correcting p-  values by Bonferroni's method.

our  study  found  this  ROI  is  associated  with  somatic, thought,  and  social  symptoms.  Analyzing  the  somatic group of symptoms, the highlighted regions (i.e., cerebellum, thalamus lateral, and red nucleus) were previously found to be associated with somatic pain processing in functional  MRI  studies  (  Bingel    et  al.,    2003;    Dunckley et al.,   2005). The role of the thalamus lateral is suggested as  relaying  spatial  information  of  selective  nociceptive stimuli to the somatosensory cortex to provide pain localization (  Bingel   et al.,   2003). Overall, the ROI associations found in BHRCS are consistent with the related literature.

A major issue was that the findings of BHRCS did not replicate  the  ones  of  ABCD.  We  conjecture  that  this occurred due to the differences in the demographic data distribution of these datasets, especially the age ranges (see  Fig.  2).  Neural  development  involves  highly  coordinated and sequenced events of both progressive (myelination) and regressive (synaptic pruning) processes (  Silk &amp;   Wood,   2011). These brain transformations affect GM and WM densities at different rates, in a regionally and temporally  specific  way  (  Gogtay    et  al.,    2004;    Silk    &amp; Wood,   2011). Therefore, the different age ranges of the

ABCD  and  BHRCS  may  have  influenced  the  nonreplicability of the results in these datasets. Another limitation is due to the multiple distinct scanner models used to collect the sMRI in each dataset (see Supplemental Information, section 5.1). A recent study indicates that differences  in  the  acquisition  parameters  of  scanners represent  a  major  limitation  for  the  generalizability  of brain models (  Jirsaraie   et al.,   2023). The set of scanner models used to train our artificial neural network (from ABCD dataset) is different from the set of scanners used by each of the testing datasets (i.e., BHRCS, ABIDE-  II and  ADHD-  200).  Therefore,  the  statistical  effect  size obtained from the testing datasets may have been reduced since our method does not control for the effect of scanners models. For instance, the scanners used to collect the  ABCD  data  were  3T  scanners  whereas  BHRCS employed 1.5T (exclusively). The higher magnetic field strength  of  the  3T  models  provides  higher  signal-  tonoise  and  contrast-  to-  noise  ratios  (in  comparison  with 1.5T)  (  Duyn,    2012).  This  translates  in  less  noise  with increased image contrast and resolution (  Duyn,   2012) for ABCD. That is, the 3T images (when compared to 1.5T) potentially capture more information on subtle contrast differences and small structural variations of the brain. Therefore, the differences in scanners' acquisition parameters  may  also  have  influenced  the  non-  replication  of ABCD results on BHRCS data.

Analyzing  the  ADHD-  200  dataset,  the  brain  regions found  to  be  associated  with  ADHD  symptoms  (see Table 5) have already been reported as atypical in ADHD subjects (  Krain   &amp;   Castellanos,   2006;   Posner   et al.,   2011, 2014;   Tomasi   &amp;   Volkow,   2012;   Zang   et al.,   2007). Studies with  ADHD  subjects  show  that  the  prefrontal  cortex, basal ganglia, and cerebellum are known for presenting atypical  volume  when  measured  from  structural  MRI (  Krain   &amp;   Castellanos,   2006;   Zang   et al.,   2007). In addition, the regions of the anterior cingulate cortex, and amygdala have been found to present abnormal activation in functional  MRI  studies  of  ADHD  (  Posner    et  al.,    2011; Zang   et  al.,    2007).  Another  functional  MRI  study  found that the substantia nigra and its dopaminergic nigrostriatal  pathways  mature  abnormally  during  childhood  to adulthood  in  ADHD  subjects  (  Tomasi    &amp;    Volkow,    2012). Including the thalamus, most of these structures are part of the cortico-  striato-  cortical loops, which are neural circuits  that  are  believed  to  show  abnormal  function  in ADHD subjects (  Posner   et al.,   2014). Taken together, the literature  corroborates  the  regions  highlighted  in  this study as being associated with ADHD.

When  assessing  ASD  diagnosis  from  the  ABIDE-  II dataset,  the  whole-  brain  predicted  likelihood  was  discriminant of both ASD (AUC = 0.60, p-  value &lt;0.001) and brain aging (r = -  0.33, p-  value &lt;0.001) (see supplementary

Table 7. Brain regions associated to chronological aging in each dataset.

| Brain region                                                  | ABCD   | BHRCS    | ADHD-  200   | ABIDE-  II          |
|---------------------------------------------------------------|--------|----------|--------------|---------------------|
| Whole brain                                                   |        | -  0.07  | -  0.19      | -  0.33             |
| Thalamus ventral lateral (right)                              | 0.075  |          |              | 0.261               |
| Thalamus ventral anterior (right)                             | 0.104  |          |              | 0.279               |
| Red nucleus (right)                                           | 0.076  |          |              |                     |
| Globus pallidus (right)                                       |        | -  0.195 |              |                     |
| Cerebellar lobules IV / V (right)                             |        | -  0.184 |              | -  0.300            |
| Cerebellar lobule VI (right)                                  |        | -  0.171 |              | -  0.257            |
| Cerebellar vermis (lobules I and II)                          |        | -  0.211 |              | -  0.218            |
| Thalamus intralaminar (left)                                  |        | -  0.194 |              | -  0.194            |
| Thalamus reuniens (left)                                      |        | -  0.171 |              |                     |
| Ventral tegmental area (right)                                |        | -  0.163 |              | -  0.357            |
| Thalamus pulvinar anterior (left)                             |        |          | -  0.170     | -  0.270            |
| Precentral gyrus (left / right)                               |        |          |              | -  0.194 / -  0.270 |
| Frontal gyrus superior (right)                                |        |          |              | -  0.238            |
| Frontal gyrus middle (right)                                  |        |          |              | -  0.150            |
| Frontal gyrus inferior opercular (right)                      |        |          |              | -  0.224            |
| Rolandic operculum (left / right)                             |        |          |              | -  0.217 / -  0.234 |
| Supplementary motor area (left / right)                       |        |          |              | -  0.185 / 0.212    |
| Superior frontal gyrus (left)                                 |        |          |              | -  0.171            |
| Orbital gyrus, anterior (left / right)                        |        |          |              | -  0.243 / -  0.309 |
| Orbital gyrus, lateral (left / right)                         |        |          |              | -  0.199 / -  0166  |
| Hippocampus (left)                                            |        |          |              | -  0.226            |
| Cuneus (left / right)                                         |        |          |              | -  0.158 / -  0.151 |
| Angular gyrus (left)                                          |        |          |              | 0.213               |
| Precuneus (left)                                              |        |          |              | -  0.177            |
| Paracentral lobule (left / right)                             |        |          |              | -  0.245 / -  0.153 |
| Caudate nucleus (left / right)                                |        |          |              | -  0.293 / -  0.206 |
| Globus pallidus (left)                                        |        |          |              | 0.225               |
| Heschl's gyrus (right)                                        |        |          |              | -  0.212            |
| Temporal pole (superior, middle - left and right)             |        |          |              | -  0.186 ~ -  0.337 |
| Cerebellar lobules (all - left and right)                     |        |          |              | -  0.156 ~ -  0.369 |
| Cerebellar vermis (III, VIII, IX, X)                          |        |          |              | -  0.186 ~ -  0.364 |
| Thalamus (anterov., reuniens, lgn, pulvinar, left and right)  |        |          |              | -  0.166 ~ -  0.352 |
| Substantia nigra (p. compacta, p. reticulate, left and right) |        |          |              | -  0.213 ~ -  0.337 |
| Locus coeruleus (left and right)                              |        |          |              | -  0.200            |

Numbers indicate statistically significant Pearson's correlations between regions of interest and chronological aging, after correcting p-  values by Bonferroni's method.

Tables  S4-  S5).  Therefore,  the  discrimination  of  ASD through the whole brain may have been confounded by brain aging. The same applies to several brain parcellations that were correlated with both ASD and brain aging (see supplementary Tables S4-  S5). However, some ROIs were  exclusively  discriminatory  of  ASD.  These  regions included  the  left  insula,  cingulate  gyrus  (left  posterior left,  middle  left  and  right),  parahippocampal gyrus (left and right), calcarine fissure and surrounding cortex (left), left  lingual  gyrus,  postcentral  gyrus  (left  and  right),  left putamen,  and  anterior  cingulate  cortex  (supra  callosal, left and right). Previous functional MRI studies found that these  regions  present  lower  activation  in  ASD  patients when compared to TD subjects (  Greene   et al.,   2011;   Kana et al.,   2007). More specifically, these regions are part of networks responsible for inhibitory control (  Kana   et  al., 2007),  and  social  orienting  for  spatial  cueing  (  Greene et  al.,    2011).  Therefore,  the  literature  corroborates  the ROIs found in our study as being discriminant of ASD.

The analysis of the correlation metrics (see supplementary  T ables  S1-  S3)  shows  that  the  correlations'  dir  ection changes when assessing different datasets. The correlations of the ABCD test set are positive (Table S1), whereas they are mixed (positive and negative) for BHRCS (Table S2), and  almost  entirely  negative  for  the  ADHD-  200  dataset (Table S3). We conjecture that this occurs due to the differences in the data distribution (especially age) of these datasets (see Fig. 2). In other words, the assessment of subjects whose age is outside the ABCD age range may lead the correlations to be negative. As the typical neurodevelopment is coupled with aging (  Gogtay   et al.,   2004; Silk    &amp;    Wood,   2011),  subjects  presenting  age  deviations (from the training set) can have their normal neurodevelopment accounted as atypical by the models' estimation.

To investigate this scenario, we measured the association between the chronological age and the models' likelihood of  typicality  (see  supplementary  Table  S5).  The  results indicated that differently of the ABCD test set (r = -  0.01, p-  value  =  0.48),  both  the  datasets  BHRCS  (r  =  -  0.07, p-  value = 0.046), ADHD-  200 (r = -  0.19, p-  value &lt; 0.001), and ABIDE-  II (r = -  0.33, p-  value &lt; 0.001) presented statistical  significant  association  between  age  and  the  wholebrain  likelihood  of  typicality.  However,  none  of  the  brain ROIs associated with the CBCL groups of symptoms or ADHD (except the left thalamus pulvinar) showed a statistically significant association with age. We hypothesize that, although brain ROIs associated with psychiatric symptoms or  ADHD  are  not  significantly  confounded  by  age,  the influence of age across the brain may influence the direction of the correlations. Presumably, this scenario would not occur if the demographic data distribution of the training set and the evaluation datasets had the same shape.

Brain ROIs found to be correlated with chronological aging did not overlap across datasets. Although counterintuitive, this behavior was expected, since the studied datasets  have  distinct  demographic  distributions.  Our model learned a typicality pattern from a training set with a  specific  distribution  of  age,  sex,  ethnicity,  unknown comorbidities,  and  other  demographic  characteristics. During the evaluation, the trained model predicted typicality scores for datasets with data distributions distinct (i.e., out of the range) of that used for training. As previously  mentioned,  neural  development  involves  highly coordinated and sequenced events that affect GM and WM densities at different rates, in a regionally and temporally specific manner (  Gogtay   et al.,   2004;   Silk   &amp;   Wood, 2011). Therefore, given the unique characteristics of each dataset tested, it was expected that each would present its own set of ROIs correlated with chronological aging.

The approach adopted by this study presents several advantages  over  other  methods.  First,  as  the  models' learning is based only on TD subjects, the same trained model is capable of estimating different psychiatric conditions, from distinct datasets. This is because atypical subjects are detected as deviations from the learned pattern of typicality. Second, the models assess each region of the whole brain estimating its correspondent likelihood of  typicality  without  bias  to  any  previous  hypothesis. Third, as psychiatric conditions are highly heterogeneous (  Cannon    &amp;    Keller,    2006;    Cicchetti    &amp;    Rogosch,    1996; Mar í n,    2016),  our  approach  allows  the  detection  and mapping  of  anomalies  without  requiring  a  consistent neurobiological signature among the evaluated subjects (  Marquand   et al.,   2019). Fourth, the robustness of GPT (i.e., Transformer-  based) normative models to map input data  relationships  regardless  of  their  distance  makes them great for neuroimaging tasks (  Graham   et al.,   2022;

Pinaya   et al.,   2021). This was evidenced in a recent study, where Transformer-  based normative models outperformed other methods in the classification of early-  stage schizophrenia from brain sMRI (  Da   Costa    et  al.,    2022). Together, these characteristics made  the proposed approach  capable  of  estimating  and  mapping  brain regions associated with psychiatric symptoms (i.e., CBCL symptom groups) from brain sMRI, for the first time.

Despite the advantages, some limitations need to be considered. The modest statistical effect sizes presented in  the  evaluation  metrics  (see  results'  tables)  make  our strategy not feasible to classify (i.e., diagnose) subjects between  typical  and  atypical  (at  least  for  the  CBCL symptom groups, based on brain sMRI). Conversely, our approach  is best suitable for mapping  associations between brain ROIs and psychiatric conditions. Another constraint  is  related  to  the  demographic  distributions  of data used for training and evaluation (see Fig. 2). Ideally, the shape of the distributions (e.g., age and sex) should be similar between training and evaluation data. Furthermore, this study did not analyze the subjects' comorbidities as a potential confounder since this information is not available in the studied data. A possible way to circumvent the limitations  related  to  differences  in  data  distributions  is  to condition the models' estimation based on context to have a demographic-  dependent likelihood estimation (  Da   Costa et al.,   2022). Presumably, the conditioning of the models by  demographic  information  and  other  potential  confounders (e.g., comorbidities and scanners' model) should lead models to show more robust metrics (i.e., larger effect sizes). Another technique that promises to increase effect sizes  is  the  likelihood  ratio,  which  has  the  potential  to emphasize  in-  distribution  semantic  components  while demonstrating reduced sensitivity to high-  frequency features  shared  across  the  population  (  Ren    et  al.,    2019). Moreover,  applying  our  approach  to  other  modalities  of data (e.g., functional MRI) is expected to produce better estimates due to the extra information (e.g., timedependent brain activations) supplied to the models. Collectively, the study of neuroimaging from normative models based on GPT is a promising approach to investigate the gap  between  the  phenotypes  of  psychiatric  conditions and their neurobiological substrates.

## DATA AND CODE AVAILABILITY

The  datasets  used  in  this  study  were  obtained  from two  public  datasets:  the  Autism  Brain  Imaging  Data Exchange II (ABIDE-  II) and Attention Deficit Hyperactivity Disorder (ADHD-  200); and from two datasets that required authorization: Adolescent Brain Cognitive Development (ABCD) and Brazilian High-  Risk Cohort Study (BHRCS). ADHD-  200  and  ABIDE-  II  can  be  directly  downloaded

from the NeuroImaging Tools &amp; Resource Collaboratory Image Repository (NITRC-  IR: https://www  .  nitrc  .  org  /  ir/). For ABCD and BHRCS data sets, application and consortium approval of an NDA form are required. The data were collected and made publicly available according to the guidelines, and approval was provided by the local ethics committee for each project. Detailed information on these datasets and their acquisition parameters can be retrieved from ABIDE-  II (http://fcon  \_  1000  .  projects  .  nitrc .  org  / indi  /  abide  /  abide  \_  II  .  html), ADHD-  200 (http://fcon \_  1000  .  projects  .  nitrc  .  org  /  indi  /  adhd200/),  ABCD  (https:// nda  .  nih  .  gov  /  abcd), and BHRCS  (https://osf  .  io  /  ktz5h /  wiki  /  home/).

All source codes are publicly available (https://github .  com  /  SergioLeonardoMendes  /  normative  \_  psychiatry).

## AUTHOR CONTRIBUTIONS

Designing  of  the  BHRCS  data  collection:  A.G.,  A.P .J., E.C.M., L.A.R., and S.B.; BHRCS data collection: P .M.P .; Conceptualization:  S.L.M.,  W.H.L.P .,  P .M.P .,  and  J.R.S.; Methodology: S.L.M., W.H.L.P ., and J.R.S.; Experiments: S.L.M.; Data analysis and interpretation: S.L.M., W.H.L.P ., and  J.R.S.; Original  manuscript  preparation:  S.L.M.; Manuscript editing: W.H.L.P ., P .M.P ., A.G., A.P .J., E.C.M., L.A.R.,  and  S.B.;  Critical  review  and  final  approval:  all authors; and Supervision: J.R.S.

## FUNDING

W.H.L.P .  is  supported  by  the  Wellcome  Flagship  Programme [WT213038/Z/18/Z].

## DECLARATION OF COMPETING INTEREST

L.A.R has received grant or research support from, served as a consultant to, and served on the speakers' bureau of Abdi Ibrahim, Abbott, Ach é ,  Adium, Apsen, Bial, Knight Therapeutics,  Medice,  Novartis/Sandoz,  Pfizer/Upjohn/ Viatris, and Shire/Takeda in the last 3 years. The ADHD and Juvenile Bipolar Disorder Outpatient  Programs chaired by Dr. Rohde have received unrestricted educational and research support from the following pharmaceutical companies in the last 3 years: Novartis/Sandoz and  Shire/Takeda.  Dr.  Rohde  has  received  authorship royalties from Oxford Press and ArtMed.

All  authors  declare  no  conflicts  of  interest  regarding the publication of this paper.

## ACKNOWLEDGMENTS

This study is supported by the S ã o Paulo Research Foundation (FAPESP) Grants #2018/21934-  5, #2018/04654-  9,

2022/07782-  3, and 2021/05332-  8. This study  was financed in part by the Coordena çã o de Aperfei ç oamento de Pessoal de N í vel Superior - Brasil (CAPES) - Finance Code 001. WHLP is supported by the Wellcome Flagship Programme  [WT213038/Z/18/Z].  We  acknowledge  the Autism Brain Imaging Data Exchange II (ABIDE-  II) consortium and  their funding sources  (http://fcon  \_  1000 . projects  .  nitrc  .  org  / indi  / abide  /  abide  \_  II  .  html). We would like to thank the Attention Deficit Hyperactivity Disorder (ADHD-  200) consortium and their funding sources (http:// fcon  \_  1000  .  projects  .  nitrc  .  org  /  indi  /  adhd200/).  Data  used in the preparation of this article were obtained from the Adolescent Brain Cognitive Development (ABCD) Study (https://abcdstudy  .  org),  held  in  the  NIMH  Data  Archive (NDA). This was a multisite, longitudinal study designed to recruit more than 10,000 children aged 9-  10 and follow them over 10 years into early adulthood. The ABCD Study® is supported by the National Institutes of Health and  additional  federal  partners  under  award  numbers U01DA041048,  U01DA050989,  U01DA051016,  U01DA 041022, U01DA051018, U01DA051037, U01DA050987, U01DA041174,  U01DA041106,  U01DA041117,  U01DA 041028, U01DA041134, U01DA050988, U01DA051039, U01DA041156,  U01DA041025,  U01DA041120,  U01DA 051038, U01DA041148, U01DA041093, U01DA041089, U24DA041123, U24DA041147. A full list of supporters is available  at  https://abcdstudy  .  org  /  federal  -  partners  .  html. A listing of participating sites and a complete listing of the  study  investigators  can  be  found  at  https://abcdstudy  .  org  /  consortium  \_  members/.  The  ABCD  consortium inves  tigators designed and implemented the study and/or   provided data but did not necessarily participate in the analysis or writing of this report. This manuscript reflects  the  views  of  the  authors  and  may  not  reflect the opinions or views of the NIH or ABCD consortium investigators.

## SUPPLEMENTARY MATERIALS

Supplementary material for this article is available with the online version here: https://doi  .  org  /  10  .  1162  /  imag  \_  a  \_  00204.

## REFERENCES

Achenbach, T. M., &amp; Rescorla, L. A. (2001). Manual for the ASEBA school-  age forms &amp; profiles . Burlington: University of Vermont, Research Center for Children, Youth, and Families. ISBN: 0938565737.

American Psychiatric Association. (2013). Diagnostic and statistical manual of mental disorders: DSM-  5 (Vol. 17, Issue 7). American Psychiatric Association.

Ashburner, J., &amp; Friston, K. J. (2000). Voxel-  based morphometry - The methods. NeuroImage , 11 (6), 805-821. https://doi  .  org  /  10  .  1006  /  nimg  .  2000  .  0582

Baur, C., Denner, S., Wiestler, B., Navab, N., &amp; Albarqouni, S. (2021). Autoencoders for unsupervised anomaly

segmentation in brain MR images: A comparative study. Medical Image Analysis , 69 (8), 1-16. https://doi  .  org  /  10 .  1016  /  j  .  media  .  2020  .  101952

Bingel, U., Quante, M., Knab, R., Bromm, B., Weiller, C., &amp; B ü chel, C. (2003). Single trial fMRI reveals significant contralateral bias in responses to laser pain within thalamus and somatosensory cortices. NeuroImage , 18 (3), 740-748. https://doi  .  org  /  10  .  1016  /  S1053 -  8119(02)00033  -  2

Cannon, T. D., &amp; Keller, M. C. (2006). Endophenotypes in the genetic analyses of mental disorders. Annual Review of Clinical Psychology , 2 , 267-290. https://doi  .  org  /  10 .  1146  /  annurev  .  clinpsy  .  2  .  022305  .  095232

Casey, B. J., Cannonier, T., Conley, M. I., Cohen, A. O., Barch, D. M., Heitzeg, M. M., Soules, M. E., Teslovich, T., Dellarco, D. V., Garavan, H., Orr, C. A., Wager, T. D., Banich, M. T., Speer, N. K., Sutherland, M. T., Riedel, M. C., Dick, A. S., Bjork, J. M., Thomas, K. M., … Dale, A. M. (2018). The Adolescent Brain Cognitive Development (ABCD) study: Imaging acquisition across 21 sites. Developmental Cognitive Neuroscience , 32 , 43-54. https://doi  .  org  /  10  .  1016  /  j  .  dcn  .  2018  .  03  .  001

Chen, X., You, S., Tezcan, K. C., &amp; Konukoglu, E. (2020). Unsupervised lesion detection via image restoration with a normative prior. Medical Image Analysis , 64 , 101713. https://doi  .  org  /  10  .  1016  /  j  .  media  .  2020  .  101713

Cicchetti, D., &amp; Rogosch, F . A. (1996). Equifinality and multifinality in developmental psychopathology. Development and Psychopathology , 8 (4), 597-600. https://doi  .  org  /  10  .  1017  /  S0954579400007318

Combrisson, E., &amp; Jerbi, K. (2015). Exceeding chance level by chance: The caveat of theoretical chance levels in brain signal classification and statistical assessment of decoding accuracy. Journal of Neuroscience Methods , 250 , 126-136. https://doi  .  org  /  10  .  1016  /  j  .  jneumeth  .  2015 .  01  .  010

Cuthbert, B. N., &amp; Insel, T. R. (2010). Toward new approaches to psychotic disorders: The NIMH research domain criteria project. Schizophrenia Bulletin , 36 (6), 1061-1062. https://doi  .  org  /  10  .  1093  /  schbul  /  sbq108

Da Costa, P . F ., Dafflon, J., Mendes, S. L., Sato, J. R., Cardoso, M. J., Leech, R., Jones, E. J., &amp; Pinaya, W. H. L. (2022). Transformer-  based normative modelling for anomaly detection of early schizophrenia. 1-10. http://arxiv  .  org  /  abs  /  2212  .  04984

Di Martino, A., O'Connor, D., Chen, B., Alaerts, K., Anderson, J. S., Assaf, M., Balsters, J. H., Baxter, L., Beggiato, A., Bernaerts, S., Blanken, L. M. E., Bookheimer, S. Y., Braden, B. B., Byrge, L., Castellanos, F. X., Dapretto, M., Delorme, R., Fair, D. A., Fishman, I., … Milham, M. P . (2017). Enhancing studies of the connectome in autism using the autism brain imaging data exchange II. Scientific Data , 4 , 1-15. https://doi  .  org /  10  .  1038  /  sdata  .  2017  .  10

Drysdale, A. T., Grosenick, L., Downar, J., Dunlop, K., Mansouri, F., Meng, Y., Fetcho, R. N., Zebley, B., Oathes, D. J., Etkin, A., Schatzberg, A. F ., Sudheimer, K., Keller, J., Mayberg, H. S., Gunning, F . M., Alexopoulos, G. S., Fox, M. D., Pascual-  Leone, A., Voss, H. U., … Liston, C. (2017). Resting-  state connectivity biomarkers define neurophysiological subtypes of depression. Nature Medicine , 23 (1), 28-38. https://doi  .  org  /  10  .  1038  /  nm  .  4246

- Dunckley, P ., Wise, R. G., Fairhurst, M., Hobden, P ., Aziz, Q., Chang, L., &amp; Tracey, I. (2005). A comparison of visceral and somatic pain processing in the human brainstem using functional magnetic resonance imaging. Journal of Neuroscience , 25 (32), 7333-7341. https://doi .  org  /  10  .  1523  /  JNEUROSCI  .  1100  -  05  .  2005
- Duyn, J. H. (2012). The future of ultra-  high field MRI and fMRI for study of the human brain. NeuroImage , 62 (2), 1241-1248. https://doi  .  org  /  10  .  1016  /  j  .  neuroimage  .  2011 .  10  .  065

Gogtay, N., Giedd, J. N., Lusk, L., Hayashi, K. M., Greenstein, D., Vaituzis, A. C., Nugent, T. F ., Herman, D. H., Clasen, L. S., Toga, A. W., Rapoport, J. L., &amp; Thompson, P. M. (2004). Dynamic mapping of human cortical development during childhood through early adulthood. Proceedings of the National Academy of Sciences of the United States of America , 101 (21), 8174-8179. https://doi  .  org  /  10  .  1073  /  pnas  .  0402680101

Graham, M. S., Tudosiu, P .-  D., Wright, P ., Hugo Lopez Pinaya, W., U-  King-  Im, J.-  M., Mah, Y . H., Teo, J. T., Jager, R., Werring, D., Nachev, P ., Ourselin, S., &amp; Jorge Cardoso, M. (2022). Transformer-  based out-  ofdistribution detection for clinically safe segmentation. Proceedings of Machine Learning Research-  Under Review , 1-16. https://proceedings.mlr.press/v172 /graham22a.html

Greene, D. J., Colich, N., Iacoboni, M., Zaidel, E., Bookheimer, S. Y., &amp; Dapretto, M. (2011). Atypical neural networks for social orienting in autism spectrum disorders. NeuroImage , 56 (1), 354-362. https://doi  .  org /  10  .  1016  /  j  .  neuroimage  .  2011  .  02  .  031

Jirsaraie, R. J., Kaufmann, T., Bashyam, V., Erus, G., Luby, J. L., Westlye, L. T., Davatzikos, C., Barch, D. M., &amp; Sotiras, A. (2023). Benchmarking the generalizability of brain age models: Challenges posed by scanner variance and prediction bias. Human Brain Mapping , 44 (3), 1118-1128. https://doi  .  org  /  10  .  1002  /  hbm  .  26144

Kana, R. K., Keller, T. A., Minshew, N. J., &amp; Just, M. A. (2007). Inhibitory control in high-  functioning autism: Decreased activation and underconnectivity in inhibition networks. Biological Psychiatry , 62 (3), 198-206. https:// doi  . org  /  10  . 1016  /  j  .  biopsych  .  2006  .  08  .  004

Kandel, E., Schwartz, J., &amp; Jessell, T. (2000). Principles of neural science (4th ed.), pp. 345, 350. ISBN: 9780838577011 / 0838577016; McGraw-  Hill Medical. Krain, A. L., &amp; Castellanos, F . X. (2006). Brain development and ADHD. Clinical Psychology Review , 26 (4), 433-444. https://doi  .  org  /  10  .  1016  /  j . cpr  .  2006  .  01  .  005

Levitt, J. M., Saka, N., Hunter Romanelli, L., &amp; Hoagwood, K. (2007). Early identification of mental health problems in schools: The status of instrumentation. Journal of School Psychology , 45 (2), 163-191. https://doi  .  org  /  10 . 1016  /  j . jsp  .  2006  .  11  .  005

Mar í n, O. (2016). Developmental timing and critical windows for the treatment of psychiatric disorders. Nature Medicine , 22 (11), 1229-1238. https://doi  .  org  /  10 .  1038  /  nm  .  4225

Marquand, A. F., Kia, S. M., Zabihi, M., Wolfers, T., Buitelaar, J. K., &amp; Beckmann, C. F . (2019). Conceptualizing mental disorders as deviations from normative functioning. Molecular Psychiatry , 24 (10), 1415-1424. https://doi  .  org  /  10  .  1038  /  s41380  -  019  -  0441  -  1

Martin, J. H. (2014). Neuroanatomia Texto E Atlas (4th ed.), pp. 47, 53, 337-338. ISBN: 9788580552645 / 0071603964 / 9780071603966.

Mendes, S. L., Pinaya, W. H. L., Pan, P . M., Jackowski, A. P ., Bressan, R. A., &amp; Sato, J. R. (2023). Generalizability of 3D CNN models for age estimation in diverse youth populations using structural MRI. Scientific Reports , 13 (1), 1-12. https://doi  .  org  /  10  .  1038  /  s41598  -  023  -  33920  -  7

Milham, P . M., Damien, F ., Maarten, M., &amp; Stewart, H. M. (2012). The ADHD-  200 Consortium: A model to advance the translational potential of neuroimaging in clinical neuroscience. Frontiers in Systems Neuroscience ,

- 6 (SEPTEMBER), 1-5. https://doi  .  org  /  10  .  3389  /  fnsys  .  2012 .  00062
- Pinaya, W. H. L., Tudosiu, P .-  D., Gray, R., Rees, G., Nachev, P., Ourselin, S., &amp; Cardoso, M. J. (2021). Unsupervised Brain Anomaly Detection and Segmentation with Transformers . 1-22. https://doi  .  org  /  10  .  1016  /  j  .  media  .  2022 .  102475
- Pinaya, W. H. L., Tudosiu, P . D., Gray, R., Rees, G., Nachev, P., Ourselin, S., &amp; Cardoso, M. J. (2022). Unsupervised brain imaging 3D anomaly detection and segmentation with transformers. Medical Image Analysis , 79 , 102475. https://doi  .  org  /  10  .  1016  /  j  .  media  .  2022  .  102475
- Posner, J., Nagel, B. J., Maia, T. V., Mechling, A., Oh, M., Wang, Z., &amp; Peterson, B. S. (2011). Abnormal amygdalar activation and connectivity in adolescents with attentiondeficit/hyperactivity disorder. Journal of the American Academy of Child and Adolescent Psychiatry , 50 (8), 828-837.e3. https://doi  .  org  /  10  .  1016  /  j  .  jaac  .  2011  .  05  .  010
- Posner, J., Park, C., &amp; Wang, Z. (2014). Connecting the dots: A review of resting connectivity MRI studies in attention-  deficit/hyperactivity disorder. Neuropsychology Review , 24 (1), 3-15. https://doi  .  org  /  10  .  1007  /  s11065  -  014 -  9251  -  z
- Radford, A., Narasimhan, K., Salimans, T., &amp; Sutskever, I. (2018). Improving language understanding by generative pre-  training. OpenAIBlog . https://cdn.openai.com /research-covers/language-unsupervised/language \_understanding\_paper.pdf
- Radford, A., Wu, J., Child, R., Luan, D., Amodei, D., &amp; Sutskever, I. (2019). Language models are unsupervised multitask learners. OpenAIBlog , 1 . https://cdn.openai .com/better-language-models/language\_models\_are \_unsupervised\_multitask\_learners.pdf
- Razavi, A., van den Oord, A., &amp; Vinyals, O. (2019). Generating diverse high-  fidelity images with VQ-  VAE-  2. Advances in Neural Information Processing Systems , 32 . https://doi  .  org  /  10  .  1109  /  icassp  .  2019  .  8683277
- Ren, J., Liu, P . J., Fertig, E., Snoek, J., Poplin, R., DePristo, M. A., Dillon, J. V., &amp; Lakshminarayanan, B. (2019). Likelihood ratios for out-  of-  distribution detection. Advances in Neural Information Processing Systems , 32 (NeurIPS). https://doi  .  org  /  10  .  48550  /  arXiv  .  1906  .  02845
- Rolls, E. T., Huang, C. C., Lin, C. P ., Feng, J., &amp; Joliot, M. (2020). Automated anatomical labelling atlas 3. NeuroImage , 206 , 116189. https://doi  .  org  /  10  .  1016  /  j .  neuroimage  .  2019  .  116189
- Sacchetti, B., Scelfo, B., &amp; Strata, P . (2009). Cerebellum and emotional behavior. Neuroscience , 162 (3), 756-762. https://doi  .  org  /  10  .  1016  /  j  .  neuroscience  .  2009  .  01  .  064
- Salum, G., Gadelha, A., Pan, P ., Moriyama, T., GraeffMartins, A., Tamanaha, A., Alvarenga, P ., Krieger, F ., Fleitlich-  Bilyk, B., Jackowski, A., Sato, J., Brietzke, E., Polanczyk, G., Brentani, H., Mari, J., Ros á rio-  Campos, M., Manfro, G., Bressan, R., Mercadante, M., &amp; Rohde, L. (2014). High risk cohort study for psychiatric disorders
- in childhood: Rationale, design, methods and preliminary results. International Journal of Methods in Psychiatric Research , 24 (1), 58-73. https://doi  .  org  /  10  .  1002  /  mpr  .  1459
- Sato, J. R., Biazoli, C. E., Salum, G. A., Gadelha, A., Crossley, N., Vieira, G., Zugman, A., Picon, F . A., Pan, P. M., Hoexter, M. Q., Amaro, E., An é s, M., Moura, L. M., Del'Aquilla, M. A. G., Mcguire, P ., Rohde, L. A., Miguel, E. C., Jackowski, A. P ., &amp; Bressan, R. A. (2017). Association between abnormal brain functional connectivity in children and psychopathology: A study based on graph theory and machine learning. World Journal of Biological Psychiatry , 19 (2), 119-129. https:// doi  .  org  /  10  .  1080  /  15622975  .  2016  .  1274050
- Scarpazza, C., Ha, M., Baecker, L., Garcia-  Dias, R., Pinaya, W. H. L., Vieira, S., &amp; Mechelli, A. (2020). Translating research findings into clinical practice: A systematic and critical review of neuroimaging-  based clinical tools for brain disorders. Translational Psychiatry , 10 (1), 107. https://doi  .  org  /  10  .  1038  /  s41398  -  020  -  0798  -  6
- Schmahmann, J. D., Weilburg, J. B., &amp; Sherman, J. C. (2007). The neuropsychiatry of the cerebellum - Insights from the clinic. Cerebellum , 6 (3), 254-267. https://doi  .  org /  10  .  1080  /  14734220701490995
- Shakiba, A. (2014). The role of the cerebellum in neurobiology of psychiatric disorders. Neurologic Clinics , 32 (4), 1105-1115. https://doi  .  org  /  10  .  1016  /  j  .  ncl  .  2014  .  07 .  008
- Silk, T. J., &amp; Wood, A. G. (2011). Lessons about neurodevelopment from anatomical magnetic resonance imaging. Journal of Developmental and Behavioral Pediatrics , 32 (2), 158-168. https://doi  .  org  /  10  .  1097  /  DBP .  0b013e318206d58f
- Tomasi, D., &amp; Volkow, N. D. (2012). Functional connectivity of substantia nigra and ventral tegmental area: Maturation during adolescence and effects of ADHD. Cerebral Cortex , 24 (4), 935-944. https://doi  .  org  /  10  .  1093 /  cercor  /  bhs382
- Van Den Oord, A., Vinyals, O., &amp; Kavukcuoglu, K. (2017). Neural discrete representation learning. Advances in Neural Information Processing Systems , 2017 -  Decem(Nips), 6307-6316. https://papers.nips.cc /paper\_files/paper/2017/file/7a98af17e63a0ac09ce2e96 d03992fbc-Paper.pdf
- Vaswani, A., Shazeer, N., Parmar, N., Uszkoreit, J., Jones, L., Gomez, A. N., Kaiser, Ł ., &amp; Polosukhin, I. (2017). Attention is all you need. Advances in Neural Information Processing Systems , 2017 -  Decem(Nips), 5999-6009. https://proceedings.neurips.cc/paper\_files/paper/2017 /file/3f5ee243547dee91fbd053c1c4a845aa-Paper.pdf
