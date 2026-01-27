## Supplementary Online Content

Wolfers T, Doan NT, Kaufmann T, et al. Mapping the Heterogeneous Phenotype of Schizophrenia and Bipolar Disorder Using Normative Models. JAMA Psychiatry . Published online October 10, 2018. doi:10.1001/jamapsychiatry.2018.2467

## eMethods

eResults eTable 1. Demographic and Clinical Characteristics of Participants eTable 2. Association With Age, Days Since Diagnosis and Cognition in Schizophrenia and Bipolar Disorder eTable 3. Association With Weighted Image Quality Rating (IQR) eTable 4. Association With Total Medication Load eTable 5. Association With Substance Abuse eFigure 1. Characterization of Extreme Deviations From the Normative Model in the Schizophrenia Spectrum eFigure 2. Histograms Showing the Percentage of Deviating Voxels Across Subjects for Each Diagnostic Classification eFigure 3. Characterization of Individual Deviations From the Normative Model in Gray Matter eFigure 4. Characterization of Individual Deviations from the Normative Model in White Matter eFigure 5. Characterization of Extreme Deviations From the Normative Model Using FDR eFigure 6. Normalized Mean and Median Deviations From the Normative Model for Each Diagnosis in Gray- and White Matter eFigure 7. Overview of the Normative Model eFigure 8. Characterization of Extreme Deviations From the Normative Model eReferences This supplementary material has been provided by the authors to give readers additional information about their work.

## eMaterials

These  online  materials  contain  information  on  eMethods,  eResults,  eTables,  and eFigures referred to in the main text of the manuscript.

## eMethods

## MRI acquisition

Structural  scans  were  obtained  on  a  1.5  Tesla  Siemens  MAGNETOM  Sonata scanner (Siemens Medical Solutions, Erlangen, Germany) using a standard head coil. Two T1-weighted images were acquired by a repeated 3D T1-weighted magnetization prepared  rapid  acquisition  gradient  echo  (MPRAGE)  sequence  with  the  following parameters: repetition time (TR) = 2730 ms, echo time (TR) = 3.93 ms, inversion time (TI) = 1000 ms, Þ eld of view (FOV) = 240 mm, ß ip angle (FA) = 7 °, matrix = 192 × 256, voxel size = 1.33 × 0.94 × 1 mm, 160 sagittal slices. The two T1-weighted scans obtained for each participant  were  averaged after  rigid  registration  to  improve  signal-to-noise  ratio (SNR).

## Estimation of gray and white matter volume

We chose voxel-based morphometry (VBM) as the primary processing framework for the T1-weigted images over alternatives such as Freesurfer due to the benefit of the VBM pipeline that it treats subcortical, cerebellar and cortical structures consistently. This is important for our work as earlier research has shown that those structures are important

in schizophrenia and bipolar disorder  1-4  and we wanted to rule out methodological bias between  these  regions  in  our  normative  modeling  approach.  However,  to  increase confidence in our findings and to better characterize volumetric differences in the cortex we additionally report the latter analyses in the supplement. In future work, we will also add alternative measures and data from different modalities to map the heterogeneity, which may provide more specific inferences about the biology underlying abnormalities in these disorders in the cortex.

Raw structural MRI images were preprocessed using the computational analysis version 12 (CAT12; http://www.neuro.uni-jena.de/software/)  5 . This toolbox is based on statistical parametric mapping version 12 (SPM-12). Images were segmented, normalized, and bias-field-corrected using VBM-SPM12 (http://www.fil.ion.ucl.ac.uk/spm, London, UK)  6,7 , yielding images containing gray and white matter segments. Prior to the estimation of the normative models, all gray and white matter volumes were smoothed with an 8 mm FWHM Gaussian smoothing kernel.

## Estimation of cortical thickness and surface area

Structural  MRI  images  were  preprocessed  using  Freesurfer  v5.3  software  to extract measures for cortical thickness and areal expansion (http://surfer.nmr.mgh.harvard.edu/;  8,9 ). The standard FreeSurfer preprocessing pipeline (recon-all) was applied to these images, in which a reconstruction of the cortical sheet was  estimated  using  intensity  and  continuity  information.  Cortical  thickness  was determined as the closest distance from the gray/white boundary to the gray/cerebrospinal fluid (CSF) boundary at each vertex  10 . Surface area in Freesurfer is

estimated  as  relative  amount  of  expansion  or  compression  at  each  vertex  when registering each participant's surface to a common atlas. Surface maps were resampled and mapped to a common coordinate system  11 . During preprocessing, the data were registered onto the high-resolution average participant surface space (fsaverage), and a 10 mm FWHM surface-based smoothing kernel was applied.

## Cognitive measures

Verbal learning and memory, was derived from the logical memory subtest of the Wechsler memory scale  12  and the California verbal learning test  13 , processing speed from the digit symbol test from the Wechsler adult intelligence scale (WAIS-III)  14 , working memory from the digit span test of the WAIS-III  14 , and executive function from the verbal fluency test (Delis-Kaplan Executive Function System (D-KEFS)  15  and the color word interference test (D-KEFS)  15 . These same measures were used in an earlier study  16 . Prior to association of the cognitive measures with the percentage of extreme negative and  extreme  positive  deviations,  missing  values  were  imputed  by  the  mean.  The imputation rate for verbal learning and memory was 6.2%, for processing speed 6.6%, for working memory 6.2%, and for executive functioning 6.6%.

## Potential confounding variables

We performed additional checks on our models to eliminate the possibility of image quality  (e.g.  due  to  head  motion),  medication  and  substance  abuse  confounding  our results.  Regarding  image  quality,  we  carefully  checked  the  quality  of  all  MRI  scans

included in our model using the 'weighted overall image quality' metric provided by the CAT12 toolbox. Whilst this does not assess the effect of head motion directly, it provides a reasonable estimate of overall image quality which is influenced by head motion. On this metric, all participants included in our cohort scored &lt; 2.8, where lower scores denote higher  image  quality.  On  the  rating  scale  provided  by  the  authors  of  this  tool  (see www.neuro.uni-jena.de/cat/index.html#QA), this corresponds to a rating of 'good' for 99.1% of our scans and a small number (0.9%) as 'satisfactory'.

To  check  whether  medication  effects  could  have  confounded  our  results,  we regressed the percentage of extreme positive and negative deviations with the normalized total medication load separately for the group of individuals with schizophrenia and bipolar disorder.  This  was  computed  by  dividing  the  raw  dosage  of  each  medication  by  the directed  daily  dosage  for  that  medication  (see  https://www.whocc.no/atc\_ddd\_index/), then summing across all medications for each individual. We emphasize strongly that these  associations  should  be  considered  illustrative  only  because  our  study  was  not designed to detect medication effects and has multiple limitations for that purpose:  it follows  a  cross-sectional  design  with  no  random  assignment  to  medication  type  and dosage and almost all patients (and no controls) have been medicated. We also did not acquire plasma concentrations of drugs or metabolites, nor accurate life-time measures of medication. We then correlated image quality measures and medication load with the percentage of positive and negative deviations for each subject (see main text). Further we checked if substance abuse could have influenced our analyses. We tested this by comparing the difference between individuals that abused drugs and those that do not on the  percentage  of  extreme  positive  and  extreme  negative  deviations,  separately  for

patients  with  schizophrenia  and  bipolar  disorder.  For  this  we  combined  a  measure  of lifetime drug abuse from the SCID  17  with a measure from a custom survey (assessing ongoing drug abuse). Since this results in a large number of comparisons, we correct these using the same Bonferroni-Holm method we use for the main analyses (separately for each potential confounding variable).

## Normative modelling on cortical thickness and surface area

We repeated an identical normative modelling procedure but instead of VBM whiteand gray matter maps we utilized measures of cortical thickness and surface area. We used  age  and  gender  as  a  predictor  for  these  brain  features,  using  the  normative modelling approach described in the main text.

## eResults

## Deviations across the full schizophrenia spectrum

In eFigure 1, we show the group level deviations including all individuals in the schizophrenia  spectrum  (i.e.  including  schizoaffective  and  schizophreniform  disorder). See main text for details.

## Distribution of deviations across all subjects

In  eFigure  2,  we  show  histograms  characterizing  the  distribution  of  the  total percentage of deviating voxels across participants. For all disorders this distribution is sharply peaked, slightly asymmetric and with heavy tails. Taken together with Figure 3 in the  main  text,  these  show  that  for  both  schizophrenia  and  bipolar  disorder,  most participants have relatively few deviations that are widespread across brain regions. The participants with the most extensive deviations are shown in eFigure 3 for gray matter and eFigure 4 for white matter.

## False discovery rate corrected deviations

In  eFigure  5,  we  show  the  group-level  deviations  after  correcting  for  the  false discovery rate (FDR). These are highly similar to the maps of deviation from the models including only pure schizophrenia (Figure 4 in the main text).

## Characteristics of the sample

In eTable 1, we report the demographic and clinical characteristics of participants in this study.

Individual extreme deviations linked to cognitive performance and disorder duration, but not age

In  eTable  2,  all  significant  associations  that  survived  multiple  comparisons  are displayed.  In  schizophrenia,  the  number  of  extreme  positive  white  matter  deviations associated positively with disease duration. The number of extreme deviations were not significantly associated with age in any of the groups. In bipolar disorder, the number of extreme negative deviations associated significantly with processing speed and executive functioning; the more negative deviations a patient exhibited in gray matter, the lower their processing speed and executive functioning.

## Potential confounding variables

The results  from  the  analyses  of  potential  confounding  variables  is  reported  in eTable  3  (image  quality),  eTable  4  (medication)  and  eTable  5  (substance  abuse). Regarding image quality, the only significant association we detected was a moderate positive correlation with the percentage of extreme negative deviations in gray matter with overall image quality (i.e. more substantial negative deviations were associated with lower image quality). The image quality metric is quite general and is sensitive to many factors (e.g. head motion or pathology). Indeed, patients often move more in the scanner than controls. To better understand the nature of this variation, we performed two additional regression analyses to test if the explained variance between image quality measures and the reported diagnostic effect on the percentage of extreme negative deviations was shared. We could show that these effects are not shared, as the effect without correction

for image quality measures of the diagnosis ( β = 0.203; p &lt; 0.001) was virtually the same as  with  correction  ( β =  0.196;  p  &lt;  0.001).  This  gives  us  confidence  that  this  has  no significant impact on the main conclusions of this manuscript. Nevertheless, this does highlight that for future studies, it is desirable to acquire different measures and develop methods that allow these potential effects to be better identified. Regarding medication, we report a nominally significant association between positive deviations in white matter and total medication load, but this did not survive correction. Regarding substance abuse we find a significant association with the percentage of negative deviations in gray matter in  the  schizophrenia  group  (eTable  5),  indicating  more  negatively  deviating  voxels  in patients  reporting  drug  abuse  in  patients  with  schizophrenia. This  is  in  line  with  our expectations as patients that abuse drugs may show a more severe phenotype. However, we are cautious about the interpretation of this finding because the drug abuse measure we  employed  is  relatively  crude  and  it  is  impossible  given  our  design  to  infer  any cause/effect relationship (see also the considerations above).

## Mean and median images across subjects

Finally,  to  better  understand  the  effect  of  potential  outliers  on  our  group  level deviation statistic images (i.e. to understand whether group level deviations in a given voxel  are  driven  by  a  small  number  of  outlying  subjects)  we  show  a  graphical representation of the normalized median deviations across subjects in eFigure 6. In all cases the mean is very similar to the median, suggesting that the role of outliers was minor in each voxel.

## Normative modeling cortical thickness and surface area

eFigure 7 shows a visual summary of the analysis procedure; in Figure 1B we depict  a  spatial  representation  of  the  vertex  wise  normative  model,  which  was characterized by a global cortical thickness decrease and surface area decrease from age 20 to 70 in line with the reported decrease in gray matter reported in the main text, with the largest decrease primarily in frontal areas for both females and males.

## Spatial extent of extreme deviations across patients and healthy individuals

eFigure 8 shows that the overlap maps based on cortical thickness resembles the maps based on VBM measures across the three groups. Schizophrenia shows stronger overlap  in  negative  deviations  particularly  in  frontal  brain  regions  than  the  other  two groups. Further, these analyses show that positive deviations from the normative model on surface area overlap more strongly on positive deviations than negative deviations. Generally, these analyses support our conclusions and reinforce the results reported on VBM in the main text.

## eTables

eTable 1: Demographic and Clinical Characteristics of Participants

| Characteristic                      | Healthy  Individuals  (n = 256)   | Patients With  Bipolar Disorder  (n = 190)   | Patients With  Schizophrenia (n  = 163)   | Patients With  Schizophrenia  Spectrum (n = 218)a   |
|-------------------------------------|-----------------------------------|----------------------------------------------|-------------------------------------------|-----------------------------------------------------|
| Male, No. (%)                       | 140 (54.7)                        | 79 (41.6)                                    | 105 (64.4)                                | 126 (57.8)                                          |
| Age, mean (SD)  [range], y          | 34 (9.5)  [18-59]                 | 34 (11.3) [18-64]                            | 31 (8.7) [19-60]                          | 30 (9.3) [19-62]                                    |
| Educational  level, mean  (SD), y   | 14 (2.3)                          | 13.57 (2.3)                                  | 12.88 (2.6)                               | 12.82 (2.6)                                         |
| Duration of  illness, mean  (SD), d | NA                                | 285.5 (352.2)                                | 232.2 (330.6)                             | 241.1 (320.8)                                       |
| PANSS score,  mean (SD)             |                                   |                                              |                                           |                                                     |
| Negative                            | NA                                | 10.1 (3.5)                                   | 15.9 (6.4)                                | 14.7 (5.3)                                          |
| Positive                            | NA                                | 10.0 (3.5)                                   | 15.1 (5.5)                                | 15.4 (6.5)                                          |
| Total                               | NA                                | 45.5 (10.1)                                  | 63.1 (16.9)                               | 61.9 (17.2)                                         |
| Medication use,  No. (%)            |                                   |                                              |                                           |                                                     |
| Antipsychotic                       | NA                                | 74 (38.9)                                    | 129 (79.1)                                | 174 (79.8)                                          |
| Lithium                             | NA                                | 35 (18.4)                                    | 2 (1.2)                                   | 2 (-0.9)                                            |
| Antiepileptic                       | NA                                | 82 (43.2)                                    | 47 (28.8)                                 | 63 (28.9)                                           |
| Antidepressant                      | NA                                | 52 (27.4)                                    | 86 (52.7)                                 | 124 (56.8)                                          |

Abbreviations: NA, not applicable; PANSS, Positive and Negative Syndrome Scale. a Schizophrenia spectrum includes patients with schizophrenia (n = 163), schizoaffective disorder (n = 33), and schizophreniform (n = 22).

| SCZ                                 | Age                                 | Days since  diagnosis                 | Verbal  learning and  memory a    | Processing  speed b                     | Working  memory c                    | Executive  functioning d                |
|-------------------------------------|-------------------------------------|---------------------------------------|-----------------------------------|-----------------------------------------|--------------------------------------|-----------------------------------------|
| Percentage  GM negative  deviations | β  = -0.188  p = 0.016  pcorr =.192 | β  = 0.015  p = 0.850  pcorr =1       | β  = -0.008  p = 0.917  pcorr =1  | β  = -0.125  p = 0.113  pcorr =1        | β  = -0.048  p = 0.542  pcorr =1     | β  = -0.146  p = 0.063  pcorr =1        |
| Percentage  GM positive  deviations | β  = 0.009  p = 0.912  pcorr = 1    | β  = 0.099  p = 0.208  pcorr = 1      | β  = 0.036  p = 0.649  pcorr = 1  | β  = 0.076  p = 0.332  pcorr = 1        | β  = 0.138  p = 0.079  pcorr = 1     | β  = 0.057  p = 0.466  pcorr =1         |
| Percentage  WM negative  deviations | β  = -0.131  p = 0.095  pcorr = 1   | β  = 0.099  p = 0.209  pcorr = 1      | β  = 0.078  p = 0.325  pcorr = 1  | β  = -0.142  p = 0.070  pcorr =1        | β  = -0.013  p = 0.864  pcorr = 1    | β  = -0.080  p = 0.309  pcorr = 1       |
| Percentage  WM positive  deviations | β  = 0.121  p = 0.123  pcorr =1     | β  = 0.233**  p= 0.003  pcorr = 0.036 | β  = -0.103  p = 0.190  pcorr = 1 | β  = -0.039  p = 0.625  pcorr = 1       | β  = 0.116  p = 0.140  pcorr =1      | β  = -0.043  p = 0.582  pcorr = 1       |
| BPD                                 | Age                                 | Days since  diagnosis                 | Verbal  learning and  memory a    | Processing  speed b                     | Working  memory c                    | Executive  functioning d                |
| Percentage  GM negative  deviations | β  = -0.042  p = 0.570  pcorr = 1   | β  = -0.090  p = 0.216  pcorr = 1     | β  = -0.153  p = 0.035  pcorr = 1 | β  = -0.232**  p = 0.001  pcorr = 0.012 | β  = -0.129  p = 0.076  pcorr = 1    | β  = -0.232**  p = 0.001  pcorr = 0.012 |
| Percentage  GM positive  deviations | β  = -0.123  p = 0.090  pcorr = 1   | β  = 0.203  p = 0.005  pcorr = .050   | β  = -0.027  p = 0.716  pcorr = 1 | β  = 0.179  p = 0.140  pcorr = 1        | β  = 0.100  p = 0.168  pcorr = 1     | β  = 0.040  p = 0.584  pcorr = 1        |
| Percentage  WM negative  deviations | β  = 0.178  p = 0.014  pcorr = 1    | β  = -0.083  p = 0.255  pcorr = 1     | β  = -0.103  p = 0.157  pcorr = 1 | β  = -0.119  p = 0.102  pcorr = 1       | β  = -0.087  p = 0.232  pcorr = 1    | β  = -0.074  p = 0.311  pcorr = 1       |
| Percentage  WM positive  deviations | β  = -0.025  p = 0.733  pcorr = 1   | β  = 0.085  p = 0.243  pcorr = 1      | β  = -0.071  p = 0.333  pcorr = 1 | β  = 0.129  p = 0.075  pcorr = 1        | β  = 0.187  p = 0.010  pcorr = 0.120 | β  = 0.079  p = 0.279  pcorr = 1        |

Note: SCZ = schizophrenia; BPD = bipolar disorder; ** = significant after multiple comparison correction

a  derived from logical memory subtest of the Wechsler memory scale (Wechsler et al., 2007) and the California verbal learning test (Delis et al., 2004)

b  derived from the digit symbol test from the Wechsler adult intelligence scale (WAIS-III; (Wechsler, 2003)

c  derived from the digit span test of the WAIS-III (Wechsler, 2003)

d  derived from the executive function from the verbal fluency test (Delis-Kaplan Executive Function System (D-KEFS) (Delis et al., 2005) and the color word interference test (D-KEFS) (Delis et al., 2005).

pcorr = We use the Bonferroni-Holm method to correct for multiple comparisons. We correct for four comparisons within each patient group. We take the smallest p-value and multiply it by the number of comparisons. If this number remains significant p&lt;0.05 after correction we take the second smallest p-value and multiply by the number of comparisons subtracted by one. We continue this process until the first p-value is not significant, subsequent non-significant p-values are given the number one.;

| eTable 3: Association with weighted image quality rating (IQR)   | eTable 3: Association with weighted image quality rating (IQR)   |
|------------------------------------------------------------------|------------------------------------------------------------------|
|                                                                  | Weighted image quality rating (IQR)                              |
| Percentage GM negative deviations                                | β  = 0.277; p < 0.001; pcorr < 0.001                             |
| Percentage GM positive deviations                                | β  = -0.074; p = 0.07; pcorr = 1                                 |
| Percentage WM negative deviations                                | β  = 0.080; p = 0.06; pcorr = 0.180                              |
| Percentage WM positive deviations                                | β  = 0.003; p = 0.93; p corr = 1                                 |

Note: Weighted image quality rating = A measure of general image quality provided by the computational analysis toolbox. pcorr = We use the Bonferroni-Holm method to correct for multiple comparisons. We correct for four comparisons within each patient group. We take the smallest p-value and multiply it by the number of comparisons. If this number remains significant p&lt;0.05 after correction, we take the second smallest p-value and multiply by the number of comparisons subtracted by one. We continue this process until the first p-value is not significant, subsequent non-significant p-values are given the number one.

| eTable 4: Association with total medication load   | eTable 4: Association with total medication load   |
|----------------------------------------------------|----------------------------------------------------|
| SCZ                                                | Total medication load                              |
| Percentage GM negative deviations                  | β  = -0.047; p = 0.568; pcorr = 1                  |
| Percentage GM positive deviations                  | β  = -0.072; p = 0.384; pcorr = 1                  |
| Percentage WM negative deviations                  | β  = -0.144; p = 0.081; pcorr = 1                  |
| Percentage WM positive deviations                  | β  = -0.176; p = 0.033; pcorr = 0.132              |
| BPD                                                | Total medication load                              |
| Percentage GM negative deviations                  | β  = -0.013 p = 0.873; pcorr = 1                   |
| Percentage GM positive deviations                  | β  = -0.016; p = 0.846; pcorr = 1                  |
| Percentage WM negative deviations                  | β  = 0.062; p = 0.439; pcorr = 1                   |
| Percentage WM positive deviations                  | β  = -0.041; p = 0.608; pcorr = 1                  |

Note: SCZ = All pure Schizophrenia patients and not including those with Schizoaffective and Schizophreniform disorder; BPD = Bipolar disorder I and II; Total medication load is defined in units of directed daily dosage (to standardize across different medications). This was computed by dividing the raw dosage of each medication by the directed daily dosage for that medication and summing across all medications each subject was taking; pcorr = We use the Bonferroni-Holm method to correct for multiple comparisons. We correct for four comparisons within each patient group. We take the smallest p-value and multiply it by the number of comparisons. If this number remains significant p&lt;0.05 after correction, we take the second smallest p-value and multiply by the number of comparisons subtracted by one. We continue this process until the first pvalue is not significant, subsequent non-significant p-values are given the number one.

| eTable 5: Association with substance abuse   | eTable 5: Association with substance abuse   |
|----------------------------------------------|----------------------------------------------|
| SCZ                                          | Substance abuse                              |
| Percentage GM negative deviations            | β  = 0.234; p = 0.003; pcorr = 0.012         |
| Percentage GM positive deviations            | β  = -0.052; p = 0.513; pcorr = 1            |
| Percentage WM negative deviations            | β  = 0.008; p = 0.923; pcorr = 1             |
| Percentage WM positive deviations            | β  = 0.008; p = 0.923; pcorr = 1             |
| BPD                                          | Substance abuse                              |
| Percentage GM negative deviations            | β  = 0.096; p = 0.188; pcorr = 1             |
| Percentage GM positive deviations            | β  = 0.124; p = 0.089; pcorr = 0.356         |
| Percentage WM negative deviations            | β  = 0.004; p = 0.956; pcorr = 1             |
| Percentage WM positive deviations            | β  = 0.103; p = 0.157; pcorr = 1             |

Note: SCZ = All pure Schizophrenia patients, not including those with Schizoaffective and Schizophreniform disorder; BPD = Bipolar disorder; Substance abuse= We combined a measure of lifetime drug abuse from the SCID, combined with a measure from a custom survey (assessing ongoing drug abuse). pcorr = We use the Bonferroni-Holm method to correct for multiple comparisons. We correct for four comparisons within each patient group. We take the smallest p-value and multiply it by the number of comparisons. If this number remains significant p&lt;0.05 after correction, we take the second smallest p-value and multiply by the number of comparisons subtracted by one. We continue this process until the first p-value is not significant, subsequent nonsignificant p-values are given the number one.;

## eFigures

eFigure 1: Characterization of extreme deviations from the normative model in the schizophrenia spectrum. Similar to Figure 4, we report the group-level mean deviations. Further, the overlap of the most extreme deviations is depicted, representing the percentage of individuals showing an extreme value (|Z| &gt; 2.6) at a specific brain locus. The sub-plot is organized as follows: each one starts with a map of group-level mean deviations (|z| &gt; 2.6), the second plot shows the overlap maps for extreme negative deviations in percentage, the third shows the same for the extreme positive deviations. This figure shows the results for all patients having a diagnosis in the schizophrenia spectrum, thus including patients with schizophrenia, schizoaffective disorder, and schizophreniform disorder. The take home message from this plot is that the results between patients with pure schizophrenia (Figure 4), and patients belonging to the schizophrenia spectrum do not differ.

eFigure 2:  Histograms showing the percentage of deviating voxels across participants for each diagnostic classification. In each case the distribution of the percentage of deviating voxels for healthy individuals is overlaid as a solid black line.

![Image](./yoi180064supp1_prod_artifacts/image_000000_7e2f015691f019ad3455d073d6cf59c72ea4bec381767f9e6d3dffbd27eba9e5.png)

## Characterization of individual deviations from normative model in gray matter

eFigure 3: Characterization of individual deviations from the normative model in gray matter. The individual deviations from the normative model in gray matter are depicted: on the left, extreme negative deviations and extreme positive deviations are depicted for schizophrenia, on the right the same is shown for bipolar disorder. As can be seen, strong inter-individual differences exist between patients.

![Image](./yoi180064supp1_prod_artifacts/image_000001_142dd4e5ac5396e1f8c23d1c3cf13cdcac56f09fdba77068119b2b2ba0cb7c33.png)

## Characterization of individual normative deviations from normative model in white matter

eFigure 4:  Characterization of individual deviations from the normative model in white matter. The individual deviations from the normative model in white matter are depicted: on the left, extreme negative deviations and extreme positive deviations are depicted for schizophrenia, on the right the same is shown for bipolar disorder. As can be seen, strong inter-individual differences exist between patients.

![Image](./yoi180064supp1_prod_artifacts/image_000002_4373c39cbb98afd52bd451c49abd1cb5ac64a5dbc1e9e8f6cb89d685978e01e1.png)

## Characterization of extreme deviations from the normative model -FDR -

Figure 5: Characterization of extreme deviations from the normative model using FDR. The group-level mean deviations of healthy individuals and patients with schizophrenia and bipolar disorder are depicted. Further, the overlap of the most extreme deviations is depicted, representing the percentage of individuals showing an extreme value (false discovery rate (FDR)-corrected at the 5% level) at a specific brain locus. Each subplot is organized as follows: it starts with a map of group-level mean deviations, the second plot shows the overlap maps for extreme negative deviations in percentage, the third plot shows the same for the extreme positive deviations. From this figure, we can deduce that the conclusions based on Figure 3,4,5 remain, when we use the false discovery rate as an initial thresholding criterion.

![Image](./yoi180064supp1_prod_artifacts/image_000003_d40f32aeaafff66c4495a8f5635614f5ab612c846da814a5063ae5900d60243a.png)

## Characterization of mean and median deviations from normative model

eFigure 6: Normalized mean and median deviations from the normative model for each diagnosis in grayand white matter. Note that no statistical threshold has been applied.

![Image](./yoi180064supp1_prod_artifacts/image_000004_76a477163c5a9cafe0f67443e1b94e3f5ec803644ccb851389812e8c9f8b84ea.png)

## Overview of the normative model -freesurfer -

![Image](./yoi180064supp1_prod_artifacts/image_000005_755a5257dd56e48b305c5e4195878ff36e0108c907c3d96a8b1d56af9a803cb4.png)

eFigure 7: Overview of the normative model. In A, the procedure of estimating the normative model in healthy individuals is depicted, with age and gender included as covariates and performing 10-fold crossvalidation. In B, the characterization of the normative model is shown. We see that the normative model changes with age and that from age 20 to 70 years, cortical thickness and surface area is predominantly decreasing. This is true for both females and males and most strongly present in frontal brain regions. In C, we depict the application of the normative model to the different patient groups, including patients with schizophrenia and patients with bipolar disorder. In D, we present the steps that are used to characterize the deviations from the normative model.

## Characterization of extreme deviations from the normative model -freesurfer -

eFigure  8:  Characterization  of  extreme  deviations  from  the  normative  model.  The  overlap  of  the  most extreme deviations is depicted, representing the percentage of individuals showing an extreme value (|z| &gt; 2.6)  at  a  specific  vertex.  Each  subplot  is  organized  as  follows:  the  overlap  maps  for  extreme  negative deviations in percentage, and the extreme positive deviations are depicted. These plots show in essence the same results as the previously reported VBM derived normative models.

![Image](./yoi180064supp1_prod_artifacts/image_000006_3f0b2db7f6e8cd5a63d855ded364b49036af2b3895218b68c88514816f9a1ea8.png)

## References

1. Van Erp TGM, Hibar DP, Rasmussen JM, et al. Subcortical brain volume abnormalities in 2028 individuals with schizophrenia and 2540 healthy controls via the ENIGMA consortium. Mol Psychiatry . 2016;21(4):547-553. doi:10.1038/mp.2015.63.
2. Hibar DP, Westlye LT, Van Erp TGM, et al. Subcortical volumetric abnormalities in bipolar disorder. Mol Psychiatry . 2016;21(12):1710-1716. doi:10.1038/mp.2015.227.
3. Hibar DP, Westlye LT, Doan NT, et al. Cortical abnormalities in bipolar disorder: an MRI analysis of 6503 individuals from the ENIGMA Bipolar Disorder Working Group. Mol Psychiatry . 2017;23(4):1-11. doi:10.1038/mp.2017.73.
4. Moberget T, Doan NT, Alnæs D, et al. Cerebellar volume and cerebellocerebral structural covariance in schizophrenia: a multisite mega-analysis of 983 patients and 1349 healthy controls. Mol Psychiatry . 2017;(April):1-9. doi:10.1038/mp.2017.106.
5. Gaser C, Nenadic I, Buchsbaum BR, Hazlett EA, Buchsbaum MS. Deformationbased morphometry and its relation to conventional volumetry of brain lateral ventricles in MRI. Neuroimage . 2001;13(6):1140-1145. doi:10.1006/nimg.2001.0771.
6. Ashburner J, Friston KJ. Voxel-based morphometry - The methods. Neuroimage . 2000;11(6 I):805-821. doi:10.1006/nimg.2000.0582.
7. Ashburner J, Friston KJ. Unified segmentation. Neuroimage . 2005;26(3):839-851. doi:10.1016/j.neuroimage.2005.02.018.
8. Dale AM, Fischl B, Sereno MI. Cortical surface-based analysis: I. Segmentation and surface reconstruction. Neuroimage . 1999;9(2):179-194. doi:10.1006/nimg.1998.0395.
9. Fischl B, Sereno MI, Dale AM. Cortical surface-based analysis: II. Inflation, flattening, and a surface-based coordinate system. Neuroimage . 1999;9(2):195207. doi:10.1006/nimg.1998.0396.
10. Fischl B, Dale AM. Measuring the thickness of the human cerebral cortex from magnetic resonance images. Proc Natl Acad Sci . 2000;97(20):11050-11055. doi:10.1073/pnas.200033797.
11. Fischl B, Rajendran N, Busa E, et al. Cortical folding patterns and predicting cytoarchitecture. Cereb Cortex . 2008;18(8):1973-1980. doi:10.1093/cercor/bhm225.
12. Wechsler D. Wechsler Memory Scale- (Fourth Ed.). Psychol Corp . 2010.
13. Delis DC, Kramer JH, Kaplan E, Ober BA. California Verbal Learning Test -

- second edition. Adult version. Manual. Test . 2000.
14. Wechsler D. WAISGLYPH&lt;31&gt;III Administration and Scoring Manual .; 1997. doi:10.1177/1073191102009001003.
15. Delis D, Kaplan E, Kramer J. Delis-Kaplan executive function system (D-KEFS). Can J Sch Psychol . 2001;20(1-2):117-128. doi:10.1177/0829573506295469.
16. Doan NT, Kaufmann T, Bettella F, et al. Distinct multivariate brain morphological patterns and their added predictive value with cognitive and polygenic risk scores in mental disorders. NeuroImage Clin . 2017;15(April):719-731. doi:10.1016/j.nicl.2017.06.014.
