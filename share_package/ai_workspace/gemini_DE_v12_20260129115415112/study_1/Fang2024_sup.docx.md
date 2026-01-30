**Supplementary results of individualized gray matter morphological abnormalities uncover two robust transdiagnostic biotypes**

**Supplementary methods**

**Datasets**

The discovery dataset was sourced from the University of California, Los Angeles (UCLA) Consortium for Neuropsychiatric Phenomics (CNP), accessible via the public database OpenfMRI  [1]. This dataset comprises 142 individuals diagnosed with psychiatric disorders (schizophrenia, n = 50; bipolar disorder (BD), n = 49; attention-deficit/hyperactivity disorder (ADHD), n = 43), alongside 130 healthy controls (HCs). Ethical approval for this study was obtained from the Institutional Review Boards at UCLA and the Los Angeles County Department of Mental Health, and all participants provided informed consent before participation. Diagnoses were established using the Structured Clinical Interview for DSM-IV-Text Revision [1]. Inclusion criteria for all participants mandated a minimum of eight years of formal education, absence of significant medical illness, and negative urinalysis results for drugs of abuse (e.g., cocaine and morphine). HCs had no lifetime diagnoses of psychotic/mental disorders or substance abuse. Participants with missing behavioral scores/images or signal dropouts in the cerebellum were excluded, resulting in a final cohort of 224 participants [2]. Further details about the dataset can be found elsewhere [1, 2].

Validation dataset 1 was obtained from the Center for Biomedical Research Excellence (COBRE), accessible at http://fcon\_1000.projects.nitrc.org/indi/retro/cobre.html [3]. This dataset comprises 61 patients diagnosed with schizophrenia and 73 matched healthy controls (HCs) after excluding participants with poor image quality or missing data. Patients met diagnostic criteria for schizophrenia according to the Diagnostic and Statistical Manual of Mental Disorders (DSM-IV, Fourth Edition) and were on stable antipsychotic medication for at least one month. Exclusion criteria included active substance dependence or abuse (except for nicotine) within the past year and a history of neurological disorders. HCs were evaluated using the DSM-IV Axis I disorders-non-patient edition. None of the HCs had a history of depression or antidepressant use within the last six months, a history of lifetime antidepressant use exceeding one year, recent substance abuse or dependence, head trauma with a loss of consciousness exceeding five minutes, or a family history of psychotic disorder in a first-degree relative.

Validation dataset 2 was obtained from site 20 of the Depression Imaging Research Consortium (DIRECT) [4, 5], consisting of 282 patients diagnosed with depression and 251 HCs. Patients were recruited from the First Affiliated Hospital of Chongqing Medical School, Chongqing, China. Two psychiatrists independently assessed diagnoses using the Structured Clinical Interview for DSM-IV. Patients had no comorbidities with other psychiatric disorders, and symptom severity was assessed using the Hamilton Rating Scale for Depression (HAMD) [6]. HCs did not meet DSM-IV criteria for any psychiatric disorders and had not used any drugs affecting brain structure or function. Exclusion criteria for all participants included current neurological disorders, history of substance abuse, and serious encephalopathy. The study procedures were approved by the Research Ethics Committee of the Brain Imaging Center of Southwest University and the First Affiliated Hospital of Chongqing Medical School, and all participants provided informed consent.

**Data acquisition**

**Discovery dataset**

T1-weighted anatomical images were acquired using the following parameters: TR = 1.9 s, TE = 2.26 ms, FOV = 250 mm, matrix = 256 × 256, sagittal plane, slice thickness = 1 mm, 176 slices.

**Validation dataset 1**

T1-weighted anatomical images were acquired using Siemens Trio scanner. Scanning parameters were as follow: repetition time = 2530 ms, echo time = 1.64 ms, flip angle = 7 degrees, voxel size = 1 × 1 × 1 mm3, inversion time = 1200ms, field of view = 256 mm, resolution matrix = 256 × 256, thickness = 1.0 mm, 256 slices.

**Validation dataset 2**

T1-weighted anatomical images were scanned on a 3.0-T Siemens Trio MRI scanner using a 16-channel whole-brain coil (Siemens Medical, Erlangen, Germany). Scanning parameters were as follow: repetition time = 1900 ms, echo time = 2.52 ms, flip angle = 9 degrees, inversion time = 900 ms, resolution matrix = 256 × 256, slice thickness = 1.0 mm, 176 slices.

**Data preprocessing**

The T1-weighted anatomical images underwent preprocessing using the CAT12 toolbox (http://dbm.neuro.uni-jena.de/cat12/) to derive voxel-level gray matter voxel-based morphometry [7]. We followed the recommended pipeline provided by CAT12, with detailed procedures outlined elsewhere [8]. Subsequently, the obtained gray matter maps underwent smoothing using a 6-mm full width at half maximum Gaussian kernel. Total intracranial volume (TIV) was also recorded for subsequent analyses [9, 10].

**Table S1.** Clinical and demographic information for participants

|                             | Discovery dataset   | Discovery dataset   | Discovery dataset   | Discovery dataset   | Discovery dataset   | Validation dataset 1   | Validation dataset 1   | Validation dataset 1   | Validation dataset 2   | Validation dataset 2   | Validation dataset 2   |
|-----------------------------|---------------------|---------------------|---------------------|---------------------|---------------------|------------------------|------------------------|------------------------|------------------------|------------------------|------------------------|
|                             | HC (n = 110)        | Sch (n = 37)        | BD (n = 40)         | ADHD (n = 37)       | p                   | Sch (n = 61)           | HC (n = 73)            | p                      | Depression (N=282)     | HC (N=251)             | p                      |
| Age, years, mean (SD)       | 31.26 (8.66)        | 35.54 (9.08)        | 34.78 (9.24)        | 31.22 (10.11)       | 0.026a              | 36.57(12.97)           | 36.07(13.08)           | 0.882c                 | 38.74(13.65)           | 39.64(15.87)           | 0.480c                 |
| Sex (male %)                | 78.38%              | 57.50%              | 54.05%              | 51.82%              | 0.041b              | 78.69%                 | 68.49%                 | 0.321b                 | 35.11%                 | 34.66%                 | 0.914b                 |
| Education, years, mean (SD) | 15.19 (1.59)        | 12.78 (1.55)        | 14.43 (1.91)        | 14.57 (1.85)        | <0.001a             | -                      | -                      | -                      | 10.94(3.39)            | 13.07(3.78)            | <0.001c                |
| Lifetime SUB, No. (%)       | 41 (37)             | 26 (70)             | 34 (85)             | 22 (59)             |                     |                        |                        |                        |                        |                        |                        |

Note, HC, healthy control; Sch, schizophrenia; BD, bipolar disorder; ADHD, attention-deficit/hyperactivity disorder; SUB, Substance Use including substance abuse and/or dependence for nicotine, alcohol, cannabis, cocaine, amphetamine, sedatives/hypnotics/anxiolytics, inhalants, opioids and hallucinogens; a one-way ANOVA; b Chi-square test; ctwo sample t test.

**Figure S1** . Gray matter morphological abnormalities for each subtype ( *p* FDR&lt;0.05) in the discovery dataset.

![Image](./Fang2024_sup_artifacts/image_000000_82a696c1492b44960a8cc63535d4519c1db81b6e74cf89b454586c2a4b2f065d.png)

**Figure S2** . Reproducibility analysis results. (A). ARI values between subtyping results obtained using all patients and those using randomly selected subsets. (B). ARI values of HYDRA for each number of subtypes. This result suggests the optimal number of subtypes is 2 when ARI values reach the maximum.

![Image](./Fang2024_sup_artifacts/image_000001_bd908c9701b5bdf11c8880e8ab76a4767f6742ebf2555a82ab4e9eb275feb230.png)

**Figure S3** . Gray matter morphological abnormalities for each subtype ( *p* FDR&lt;0.05) in the validation datasets.

![Image](./Fang2024_sup_artifacts/image_000002_121a8f2661942df964c41a74b6a4f3b8a9475aaae480670b3e10a369c89bd82d.png)

1.	Poldrack, R.A., et al., *A phenome-wide examination of neural and cognitive function.* Sci Data, 2016. **3** : p. 160110.

2.	Kebets, V., et al., *Somatosensory-Motor Dysconnectivity Spans Multiple Transdiagnostic Dimensions of Psychopathology.* Biol Psychiatry, 2019. **86** (10): p. 779-791.

3.	Collin, G., et al., *Impaired rich club connectivity in unaffected siblings of schizophrenia patients.* Schizophr Bull, 2014. **40** (2): p. 438-48.

4.	Yan, C.G., et al., *Reduced default mode network functional connectivity in patients with recurrent major depressive disorder.* 2019. **116** (18): p. 9078-9083.

5.	Chen, X., et al., *The DIRECT consortium and the REST-meta-MDD project: towards neuroimaging biomarkers of major depressive disorder.* Psychoradiology, 2022. **2** (1): p. 32-42.

6.	Hamilton, M., *A rating scale for depression.* J Neurol Neurosurg Psychiatry, 1960. **23** (1): p. 56-62.

7.	Ashburner, J. and K.J. Friston, *Voxel-based morphometry--the methods.* Neuroimage, 2000. **11** (6 Pt 1): p. 805-21.

8.	Ashburner, J., *Computational anatomy with the SPM software.* Magn Reson Imaging, 2009. **27** (8): p. 1163-74.

9.	Han, S., et al., *Resolving heterogeneity in obsessive-compulsive disorder through individualized differential structural covariance network analysis.* Cereb Cortex, 2022.

