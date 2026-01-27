## ORIGINAL ARTICLE

![Image](./Zhang2022_artifacts/image_000000_5c0c55965235bd6e474f220c10f37538e83c4529dcbb003065d43bbea466350b.png)

## Probing individual-level structural atrophy in frontal glioma patients

Guobin Zhang 1  · Xiaokang Zhang 1 · Huawei Huang 2 · Yonggang Wang 1 · Haoyi Li 1 · Yunyun Duan 3 · Hongyan Chen 3 · Yaou Liu 3 · Bin Jing 4 · Yanmei Tie 5 · Song Lin 1

Received: 14 February 2022 / Revised: 15 April 2022 / Accepted: 25 April 2022 © The Author(s), under exclusive licence to Springer-Verlag GmbH Germany, part of Springer Nature 2022 / Published online: 4 May 2022

## Abstract

Although every glioma patient varies in tumor size, location, histological grade and molecular biomarkers, non-tumoral morphological abnormalities are commonly detected by a statistical comparison among patient groups, missing the information of individual morphological alterations. In this study, we introduced an individual-level structural abnormality detection method for glioma patients and proposed several abnormality indexes to depict individual atrophy patterns. Forty-five patients with a glioma in the frontal lobe and fifty-one age-matched healthy controls participated in the study. Individual structural abnormality maps (SAM) were generated using patients' preoperative T1 images, by calculating the degree of deviation of voxel volume in each patient with the normative model built from healthy controls. Based on SAM, a series of individual abnormality indexes were computed, and their relationship with glioma characteristics was explored. The results demonstrated that glioma patients showed unique non-tumoral atrophy patterns with overlapping atrophy regions mainly located at hippocampus, parahippocampus, amygdala, insula, middle temporal gyrus and inferior temporal gyrus, which are closely related to the human cognitive functions. The abnormality indexes were associated with several molecular biomarkers including isocitrate dehydrogenase (IDH) mutation, 1p/19q co-deletion and telomerase reverse transcriptase (TERT) promoter mutation. Our study provides an effective way to access the individual-level non-tumoral structural abnormalities in glioma patients, which has the potential to significantly improve individualized precision medicine.

Keywords Frontal glioma · Structural MRI · Individual structural atrophy · Abnormality index · Molecular biomarkers

Guobin Zhang, Xiaokang Zhang and Huawei Huang have contributed equally to this work and share first authorship.

* Bin Jing bjing@ccmu.edu.cn
* Yanmei Tie ytie@bwh.harvard.edu
* Song Lin linsong\_ttyy@sina.com
- 1 Department of Neurosurgery, China National Clinical Research Center for Neurological Diseases (NCRC-ND), Center of Brain Tumor, Beijing Institute for Brain Disorders, Beijing Key Laboratory of Brian Tumor, Beijing Tiantan Hospital, Capital Medical University, Beijing 100070, China
- 2 Department of Critical Care Medicine, Beijing Tiantan Hospital, Capital Medical University, Beijing 100070, China
- 3 Department of Radiology, Beijing Tiantan Hospital, Capital Medical University, Beijing 100070, China
- 4 School of Biomedical Engineering, Capital Medical University, Beijing 100069, China
- 5 Department of Neurosurgery, Brigham and Women's Hospital, Harvard Medical School, Boston, MA 02115, USA

## Introduction

Gliomas represent the majority of primary central nervous system (CNS) tumors. Comprehensive assessment of various diagnostic information of gliomas, including histological grades and molecular biomarkers, is important for its clinical management and prediction of outcome. However, it is infeasible to acquire these information before surgery; therefore, presurgical multi-modal imaging has been used as a key approach to assess the tumor status. Among many imaging modalities available for glioma patients, such as structural magnetic resonance imaging (sMRI), perfusion MRI, diffusion MRI (dMRI), functional MRI (fMRI) and positron emission tomography (PET), sMRI, e.g., T1 and T2 image, is the current standard of care scanning sequence, as it reveals the detailed morphological information about glioma with high spatial resolution and test-retest reliability. Commonly, limited information from sMRI, such as tumor location and size, is used for presurgical assessment. With the emergence of radiomics, the quantitative analysis of the

![Image](./Zhang2022_artifacts/image_000001_6d288351cfd118d4d9f276a4ec7eea4c2c96b4ff7292fd5b9a12f353117b71ce.png)

tumor region on sMRI images has been demonstrated to provide useful information about the histological/molecular biomarkers [27, 30, 37, 40].

Besides the tumor itself, glioma could induce alterations in the non-tumoral regions of the brain. A recent study reported that glioma in insular cortex resulted in a compensatory increase in the contralateral gray matter volume [1]. Another study confirmed that the gray matter volume of the contralateral temporal lobe was compensatively changed in patients with unilateral temporal lobe glioma and was related with patients' cognitive function [17]. Functional connectivity studies of the non-tumoral regions based on resting-state fMRI (rs-fMRI) indicated that alterations of functional connectivity and resting state networks in non-tumoral regions were related with patients' cognitive function [8, 31]. These studies imply that alterations in the non-tumoral regions may be important to the postsurgical prognosis of brain tumors. Structural and functional alterations in the non-tumoral regions are commonly studied by group-level statistical comparisons [1, 8, 17, 31], although every glioma patient may suffer unique brain alterations. Because detecting and quantifying individual-level alterations may further deepen our understanding of the influences of gliomas, it is necessary to probe the individual-level alterations of the nontumoral regions for glioma patients.

A previous study proposed an individual-level abnormality detection method called W-score map on multi-modal MRI [25]. W-score map can be used to detect personalized alterations in a single patient based on the patient's deviation from a normative model derived from healthy controls in a voxel-wise manner. This method has been applied to discover individual-level gray matter changes in Alzheimer's disease [18, 41] and provided an insightful assessment of the neuroanatomical variability and heterogeneity among patients. Based on these studies, we developed an extended W-score method for studying both gray matter and white matter volumes in brain tumor patients and further proposed several novel whole brain structural abnormality indexes to depict the individualized structural atrophy patterns. In this study, we aim to investigate whether individual frontal glioma patients display unique structural alterations, whether common patterns of structural alterations exist among glioma patients, and whether the individual-level atrophy patterns are correlated with patient-specific tumor characteristics.

## Methods

## Subjects

This study was approved by the Institutional Review Board of Beijing Tiantan Hospital, Capital Medical University,

![Image](./Zhang2022_artifacts/image_000002_691faa8fa051e164f8ab4e6b81054ae0372beb45c55929007ad1716fb425b282.png)

Beijing, China (KY2020-048-01), and registered at Chinese  ClinicalTrial  Registry  (ChiCTR2000031805). All study procedures were in accordance with the Declaration of Helsinki. Written informed consent was obtained from all subjects. All patients were prospectively enrolled between May 2019 and July 2020, and all healthy controls were recruited from the local community and universities. An initial cohort of 52 glioma patients and 117 healthy controls participated this study. All gliomas were diagnosed according to the criteria of the World Health Organization (WHO) classification system [28, 29]. Inclusion criteria were adult (&gt; 18 years old) patients with a newly diagnosed glioma. Exclusion criteria were patients with previous cranial surgery, neuropsychiatric comorbidities and any contraindications to MR scanning such as metal implants. A final cohort of 45 patients (29 men, 16 women, age (mean ± standard deviation (SD)) = 43.2 ± 9.7 years, all right-handed) and 51  age-matched  healthy  controls  (24  men,  27  women, age = 42.6 ± 9.7 years, all right-handed) were included in the study. The demographic and clinical information of all subjects is listed in Table 1.

Neuropsychological testing was conducted in patients prior to their first MRI scan using the Montreal Cognitive Assessment (MOCA) test. The MOCA score was obtained by summing up seven sub-domain scores, including visual-spatial abilities, name objects, attention, language, abstraction, delayed recall and orientation. The total score of MOCA is 30, and patients scoring below 26 were identified as showing cognitive impairment. Age-adjusted Charlson Comorbidity Index (CCI) was collected to ascertain the comorbidity level for each patient. Histological confirmation of the diagnosis was obtained by surgical resection, and there were 33/10/2 glioma patients with grade II/III/IV. Molecular markers, including 1p/19q co-deletion, isocitrate dehydrogenase (IDH)1/2 mutation, telomerase reverse transcriptase (TERT) promoter mutation and   O 6 -methylguanine-DNA methyltransferase (MGMT) promotor methylation, were all collected. Chromosomes 1 and 19 were analyzed by the fluorescence in situ hybridization method, and the IDH1/2 mutation and TERT promoter mutation were detected by sequence analysis, both following a previously described protocol [39]. MGMT promoter methylation was assessed by methylation-specific polymerase chain reaction (PCR) as described previously by our team [47]. Patients were followed with routine clinical visits after initiation of treatment.

## Structural MRI acquisition

All subjects were scanned with a Philips Ingenia 3.0 T MRI scanner at Beijing Tiantan Hospital. For both glioma patients and healthy controls, T1 sequence was collected with the following parameters: TR: 6.5 ms, TE: 3.0 ms, flip angle: 8°, voxel size: 1  ×  1  ×  1   mm 3 , image dimension: 256 × 256 × 196.

Table 1 Demographic and clinical information of all subjects

|                                            | Glioma patients   | Healthy controls   |
|--------------------------------------------|-------------------|--------------------|
| Number                                     | 45                | 51                 |
| Gender (M/F)                               | 29/16             | 24/27              |
| Age                                        | 43.2 ± 9.7        | 42.6 ± 9.7         |
| TIV                                        | 1475.1 ± 114.0    | 1425.9 ± 144.9     |
| Tumor volume                               | 37.6 ± 38.0       | -                  |
| Histological grade (II/III/IV)             | 33/10/2           | -                  |
| IDH1 mutation (mutated/wild type)          | 39/6              | -                  |
| TERT promoter mutation (mutated/wild type) | 30/15             | -                  |
| 1p/19q co-deletion (yes/no)                | 31/14             | -                  |
| MGMT promoter methylation (yes/no)         | 37/8              | -                  |
| MOCA                                       | 22.6 ± 4.6        | -                  |
| Charlson Comorbidity Index                 | 0.911 ± 1.164     |                    |

IDH,  isocitrate  dehydrogenase;  MGMT,    O 6 -methylguanine-DNA  methyltransferase;  MOCA,  Montreal Cognitive Assessment; TERT, telomerase reverse transcriptase; TIV, total intracranial volume. Age, TIV, tumor volume and MOCA are displayed with mean ± std

For patients, T2-FLAIR (fluid attenuated inversion recovery) was scanned with the following parameters: TR: 4.8 s, TE: 0.34 s, flip angle: 90°, voxel size: 0.625  ×  0.625  ×  0.55   mm 3 , image dimension: 400 × 400 × 300.

## Tumor segmentation

The tumor region of every patient was extracted from T2-FLAIR images using the ITK-SNAP software (www. itksn  ap.  org,version 3.8.0) [46]. First, an automatic segmentation procedure was performed on each patient's T2-FLAIR image to segment the tumor region, followed by a manual correction of any errors by an experienced neurosurgeon (Zhang, G.). Notably, the tumor segmentation region contained the peri-tumoral edema in order to minimize possible influences of edema on non-tumoral structural alterations. Using the SPM12 software (https://  www.  fil.  ion.  ucl.  ac.  uk/ spm/, Wellcome Trust Center for Neuroimaging, University College London, UK), individual patient's T2-FLAIR image was co-registered to the patient's T1 image using a rigid registration procedure, and the resulting transformation matrix was applied to the segmented tumor to obtain the extracted tumor region in T1 image space. To obtain an overall distribution of the tumors, the segmented tumors were normalized into Montreal Neurological Institute (MNI) standard space (Fig. 1).

## Brain volume extraction

Individual T1 images were processed using the Computational Anatomy Toolbox 12 (CAT12, http://  dbm.  neuro.  unijena.  de/  cat/, Structural Brain Imaging Group, University of Jena, GER) to calculate the brain tissue volume. First, skull stripping and correction for bias-field inhomogeneities were performed. Then, the whole brain image was segmented into different tissue types, e.g., gray matter (GM), white matter (WM) and cerebrospinal fluid (CSF), followed by normalization of the segmented GM and WM images into the MNI space using optimized shooting algorithm [16]. Finally, the normalized GM and WM images were smoothed with a 4-mm full width at half maxima (FWHM) Gaussian kernel.

## Generation of individual structural abnormality map

Our idea of generating an individual structural abnormality map (SAM) is to quantify the deviation of a patient's brain volume measurement from a normative brain volume model using a W score. Figure 2 illustrates the flowchart of SAM generation. First, a normative brain volume model was constructed using the healthy controls' data. General linear model (GLM) was adopted to discover the relationship between brain volume and variables including age, sex and total intracranial volume (TIV) using the following equation:

<!-- formula-not-decoded -->

where β 1 , β 2 , β 3 are weights of the regressors age, sex and TIV, respectively. Using this approach, the normative GM and WM volume models were separately constructed. Then, for each glioma patient, an individual SAM was generated by calculating a W score [25] for each voxel using the following equation:

<!-- formula-not-decoded -->

Here, the W score essentially reflects the degree of deviation of each patient from the healthy normal controls, and

![Image](./Zhang2022_artifacts/image_000003_6d288351cfd118d4d9f276a4ec7eea4c2c96b4ff7292fd5b9a12f353117b71ce.png)

Fig. 1 Overall tumor distribution in all frontal glioma patients. The color bar indicates the number of patients who display tumor in that location. L: left; R: right

![Image](./Zhang2022_artifacts/image_000004_b8de01d2150adb6ab40a78d1de2b8d785debf88a3fd8c1818899d1ec60366c45.png)

the larger the W score in one voxel, the higher the atrophy probability in that location. A threshold of |W|&gt; 6 was selected to generate the SAM for each patient to avoid the possible false positives in the whole brain SAM. Additionally, individual SAM was masked with corresponding nontumoral regions, which was generated by the subtraction of individual tumor ROI from GM/WM prior probability template (provided in SPM, thresholded at 0.2). Based on the whole brain SAM, we defined 8 structural abnormality indexes (4 indexes for GM and 4 for WM) to reflect the characteristics of individual patients' structural alterations: ipsilateral atrophy ratio, contralateral atrophy ratio, non-cancer atrophy ratio and relative atrophy ratio (definitions of these indexes are listed in Table 2).

## Relationship between abnormality indexes and glioma characteristics

To investigate the relationship between the abnormality indexes and various glioma characteristics, Spearman correlation was used to correlate the indexes with continuous variables, e.g., tumor volume, CCI and MOCA score, and a two-sample t-test was used for categorical variables, e.g., TERT mutation, 1p/19q co-deletion, MGMT promoter methylation, histological grade and IDH1. A significance level

![Image](./Zhang2022_artifacts/image_000005_1c135f20677aa9cf15a57d1cd74b01fbfaaa0f4112e7f4e714545afba9be4767.png)

of p &lt;  0.05 with false discovery rate (FDR) correction for multiple comparisons was used for both analyses.

## Results

All  subjects'  demographic  information  is  reported  in Table 1. A summary of the patients' clinical information, including WHO histological grade, IDH1, TERT mutation, 1p/19q co-deletion, MGMT promoter methylation and MOCA score, is reported in Table 1 as well. Specially, the MOCA score was negatively correlated with WHO grading (r  =  -0.356, p =  0.0096) in all patients.

## Every patient displayed unique atrophy pattern

The results showed that every patient displayed unique atrophy pattern, and the tumor-induced structural atrophies were located not only at regions close to tumors but also regions far away from tumors. Figure 3 illustrates three example patients' results, showing T1 images (with varying tumor size and histological grades) and corresponding individualized GM/WM SAMs that were mapped back to the patients' T1 space using reverse normalization matrix.

Fig. 2 Flowchart of the derivation of structural abnormality indexes. ( A ) construction of normative brain volume model; ( B ) calculation of the proposed individual abnormality indexes. GLM: general linear model; GM: gray matter; WM: white matter

![Image](./Zhang2022_artifacts/image_000006_554ebe6b8775a354927fe93038a61d7889ddf7a087d9b3cbae2833882535508f.png)

Table 2 Proposed 8 whole-brain structural abnormality indexes

| Abnormality index                                                                                     | Description                                                                                                                                                                                                                                                                                                                                                                                                          |
|-------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Ipsilateral atrophy ratio Contralateral atrophy ratio Non-cancer atrophy ratio Relative atrophy ratio | = number of GM ∕ WMatrophy voxels in hemisphere with tumor number of all voxels in hemisphere with tumor = number of GM ∕ WMatrophy voxels in contralateral hemisphere number of all voxels in contralateralhemisphere = number of abnormal GM ∕ WMvoxels in non - cancer region number of all voxels in whole brain = number of abnormal GM ∕ WMvoxels in non - cancer region number of all voxels in cancer region |

GM, gray matter; WM, white matter

## Shared atrophic regions across patients

Figure 4 shows the overlapping GM regions of all patients' SAMs. There were widespread GM atrophies in the temporal lobe, especially in the mesial temporal lobe (MTL, mainly including hippocampus, amygdala and parahippocampus), middle temporal gyrus and inferior temporal gyrus. In addition, precuneus, lingual gyrus, fusiform and insula also showed atrophy in these patients with frontal gliomas. Specifically, atrophies in the right temporal lobe were more extensive than the left side regardless of tumor hemisphere. Figure 5 shows the overlapping WM regions of all patients' SAMs. The atrophy regions were mainly located at bilateral thalamus and pallidum, with the contralateral side having larger atrophy areas than the ipsilateral side.

## Individual atrophy patterns were associated with glioma characteristics

Figure 6 illustrates the relationship between the individual abnormality indexes and glioma characteristics ( p &lt;  0.05, FDR corrected). GM/WM relative atrophy ratio was found to significantly correlate with the tumor size, i.e., the larger the tumor volume, the smaller the relative non-tumoral atrophy (power law distribution); no other individual structural indexes were correlated with tumor size. In particular, although grade III and IV gliomas are known to represent

![Image](./Zhang2022_artifacts/image_000007_929efd798ab7dbc83b8f1b2488d7e793735df4b2b5fa0841056002f36b900012.png)

Fig. 3 Three example patients' results of individualized GM/WM atrophy maps. GM: gray matter; WM: white matter

![Image](./Zhang2022_artifacts/image_000008_074f322c82aab8f43b50987a543c1d7578995f6e770338d230e9211f3a8ce553.png)

a whole brain disease, the above-mentioned correlations still existed after excluding these patients (r = -0.671 and -0.684, respectively, both p &lt;  0.0001). WM contralateral atrophy ratio indicated significant differences between IDH wild type and mutation type, while WM relative atrophy ratio was significantly different between 1p/19q co-deletion and nonco-deletion type. Moreover, both GM/WM relative atrophy ratios indicated between-group differences in TERT wild type and mutation type. No significant relationships were detected between atrophy indexes and MOCA, MGMT or CCI.

## Discussion

Through an individualized structural abnormality map detection method, this study aimed to address the questions that whether frontal glioma patients display unique

![Image](./Zhang2022_artifacts/image_000009_426adb1e324b76809f2b77c3e55f7f9a646353e405a63013e90789d74904914f.png)

and overlapping structural abnormalities in the non-tumoral regions and whether the individual atrophy patterns were associated with glioma characteristics. We developed a modified W-score map and proposed several whole-brain GM/WM atrophy indexes to depict individual structural atrophy characteristics in the non-tumoral regions, and further investigated the relationship between individual atrophy patterns and tumor characteristics. The results demonstrated that every glioma patient displayed unique atrophy pattern, and there were overlapping atrophy regions among patients, mainly at the temporal lobe including MTL (e.g., hippocampus, amygdala, and parahippocampus), middle temporal gyrus and inferior temporal gyrus. In addition, the atrophy indexes were correlated with tumor size and molecular indicators such as IDH mutation, 1p/19q co-deletion and TERT mutation.

In recent years, individualized neuroimaging has shown great potential in precision diagnosis and treatment [26, 43].

![Image](./Zhang2022_artifacts/image_000010_35b287bd6b8c99aeb833492a8aa5367511602338c509394bab26d8769c8f0b37.png)

Fig. 4 Overlapping GM atrophy in non-tumor regions for all patients. ( A ) tumor on left hemisphere; ( B ) tumor on right hemisphere. GM: gray matter; L: left; R: right

![Image](./Zhang2022_artifacts/image_000011_968953b8b5ee52e9a9f097485c6de66d9bcc6e6579ef033d36386b64a8fb28ba.png)

Fig. 5 Overlapping WM atrophy in non-tumor regions for all patients. ( A ) tumor on left hemisphere; ( B ) tumor on right hemisphere. WM: white matter; L: left, R: right

![Image](./Zhang2022_artifacts/image_000012_6d288351cfd118d4d9f276a4ec7eea4c2c96b4ff7292fd5b9a12f353117b71ce.png)

Fig. 6 Relationship between abnormality indexes and clinical/molecular  indicators  in  frontal  glioma  patients.  GM  relative  atrophy  ratio ( A )  and  WM  relative  atrophy  ratio  ( B )  were  correlated  with  tumor volume.  WM  contralateral  atrophy  ratio  was  significantly  different between IDH mutation and IDH wild-type patients ( C ) and between 1p/19q  co-deletion  and  nonco-deletion  patients  ( D ).  There  are  sig-

![Image](./Zhang2022_artifacts/image_000013_5370f6a9e95f262dc4b39b3806e42bf310cbb98adf048c57e669573e93a09c4c.png)

Using rs-fMRI, Stoecklein et al. reported that individual functional connectivity characteristics of glioma patients were significantly correlated with tumor grade and IDH mutation status [38], demonstrating that individual-level fMRI alterations could reflect the intrinsic characteristics of tumors. Conversely, individual-level whole-brain structural alterations are not adequately studied for glioma patients although sMRI is the main diagnostic tool in presurgical assessment of gliomas. Currently, sMRI is either directly used through visual inspection by experienced neurosurgeons, or at a group level, brain tumor patients' sMRIs are quantitatively compared with that of the healthy controls to identify changes in the non-tumoral regions, missing the unique information of individual-level structural alterations. In addition, brain structure may be more relevant to the molecular biomarkers than brain function [48, 49]; therefore, individual-level structural alterations may be more sensitive to reflect the differences among diverse molecular types. Our study revealed that every glioma patient displayed unique structural atrophy in the non-tumoral regions, and individual atrophy patterns were associated with several molecular biomarkers, demonstrating the great potential of sMRI for individual-level assessment of glioma patient.

Although glioma patients displayed distinct individuallevel structural atrophy, it was interesting to find that they also shared overlapping GM atrophies in MTL, middle

nificant  differences  in  GM  relative  atrophy  ratio  ( E )  and  WM  relative  atrophy ratio ( F )  between TERT mutation and TERT wild-type patients. GM: gray matter; WM: white matter. IDH: isocitrate dehydrogenase mutation; TERT: telomerase reverse transcriptase; MGMT: O6-methylguanine-DNA methyltransferase

![Image](./Zhang2022_artifacts/image_000014_dcd03172cb7fb019bcf2a4efff34a34f3f4a4ac54d7dd209d84bac49f197d756.png)

temporal gyrus, inferior temporal gyrus, precuneus, lingual gyrus, fusiform and insula. Importantly, these atrophies are not dependent on the hemisphere of tumor, tumor size, histological grade or molecular biomarkers, indicating that frontal gliomas may introduce consistent atrophies in the remote brain structure. In addition, GM atrophy was more severe in the right hemisphere than the left hemisphere regardless of the tumor location, suggesting that the right hemisphere is more vulnerable to frontal glioma. This lateralization of the atrophy pattern needs further investigation.

Among the shared atrophy regions, MTL is a key region related to memory function, and MTL atrophy (MTA) is an important predictor for Alzheimer's disease (AD)[5, 32]. Middle temporal gyrus and inferior temporal gyrus have been found to correlate with verbal cognition and semantic memory in AD [7, 36]. Precuneus is related to episodic memory, and transcranial magnetic stimulation of the precuneus could enhance memory in prodromal AD [24]. Lingual gyrus and fusiform are key regions for visual processing, and have been found to link to visual memory in patients with mild cognitive impairment [21]. Insula is involved in the human cognition [4], and its dysfunction is also reported in AD [3]. Taken together, the shared atrophy regions in these frontal glioma patients indicate a high risk of cognitive impairment, which may explain the previous surveys that as many as 90% of frontal glioma patients showed

tumor-related cognitive deficits [11, 12]. Future longitudinal studies are needed to investigate the impact of these atrophy patterns in patients' cognitive impairments.

The overlapping WM atrophies were mainly located at bilateral thalamus and pallidum, and the contralateral hemisphere showed larger atrophy areas than the ipsilateral hemisphere. These regions are involved with two fiber bundles linking frontal and temporal regions: superior longitudinal fasciculus (SLF) and uncinate fasciculus (UF). SLF has been reported to correlate with the spatial working memory deficit [22], visuospatial dysfunction [33] and transmission of speech and language [15] in glioma patients. UF has been demonstrated to correlate with language [9] and cognitive deficits [19] in glioma patients. These WM atrophies may indicate the dysfunctions in the structural connectivity between frontal and temporal lobe, leading to the cognitive impairments in frontal glioma patients.

GM/WM relative atrophy ratio was negatively related (power law distribution) with tumor volume, i.e., the larger the tumor size, the smaller the relative non-tumoral atrophy in GM/WM. This relationship indicated that the size of individual non-tumoral atrophy is not directly driven by the tumor size; instead, the size of individual non-tumoral atrophy may be affected by other contributors, such as the genetic and environmental factors [13]. Moreover, WM contralateral and relative atrophy ratios were significantly different between patients with IDH mutation and 1p/19q co-deletion. IDH and 1p/19q co-deletion are both valid biomarkers for the prognosis of glioma. For example, lowergrade gliomas with wild-type IDH have similar prognosis as glioblastomas, and IDH mutated glioblastomas have better prognosis than IDH wild-type glioblastomas [6, 14]. Anaplastic gliomas with IDH wild-type have worse prognosis than glioblastomas with IDH mutation [14]. 1p/19q co-deletion is found with better prognosis for patients with oligodendroglioma after radiotherapy or alkylating chemotherapy [20, 42]. Additionally, GM/WM relative atrophy ratios were both found with significant differences between TERT mutated and wild type, which is also a promising indicator for the treatment response of radiotherapy and temozolomide in primary glioblastoma, IDH-wildtype [10, 35, 44]. In summary, our findings indicated that individual WM/ GM alterations in the non-tumoral regions are influenced by several molecular biomarkers in glioma patients. Although these genetic alterations occurred in glioma cells could not directly influence the neuronal plasticity in healthy (e.g., contralesional hemisphere) brain, they in fact decide the lesion momentum (rate and effect of lesion) of glioma [23]. As shown in previous studies, lesion momentum affected the brain remodeling and differed in different genotypes of glioma [23, 34]. The proposed individual structural abnormality maps may indicate the extent of structural reorganization such as compression of neural unit, neuron loss and increasing efficiency by synaptic pruning [2, 45] in the brain. Therefore, they could serve as a potential indicator of lesion momentum of the glioma and thus reflect the tumor genotypes; especially, molecular biomarkers are not available before surgery; these structural atrophy patterns can assist the inference about the glioma genetics and thus improve presurgical assessment of glioma patients.

There are several limitations of this study. First, the optimal threshold for W is not clear; we chose |W|&gt; 6 in order to reduce the whole brain false positives in the detection of atrophy patterns. Second, the sex and TIV are not well matched between glioma patients and healthy controls. Third, in order to include a complete tumor-infiltrated area, the perifocal edema (high signal in T2-FLAIR) was included during the segmentation of tumor region. However, the perifocal edema may affect the WM related structural abnormality indexes, especially in high grade lesions. Future large-scale studies are needed to address these limitations and further validate the findings of this study.

## Conclusion

Our study investigated the individual-level non-tumoral structural atrophies in frontal glioma patients and found the structural atrophies were distinctive in every patient but also shared common atrophies among patients. Additionally, several individual structural abnormality indexes were potential indicators of the molecular biomarkers of glioma. Our findings can provide valuable information for the individualized presurgical evaluation and postsurgical prognosis in frontal glioma patients.

Authors' contributions Bin Jing, Song Lin and Yonggang Wang took part in conceptualization; Bin Jing and Yaou Liu involved in methodology; Guobin Zhang, Bin Jing, Yunyun Duan and Huawei Huang took part in formal analysis and investigation; Guo-Bin Zhang and Xiaokang Zhang took part in writing-original draft preparation; Yanmei Tie and Song Lin involved in writing-review and editing; Yonggang Wang, Hongyan Chen, Yaou Liu and Song Lin involved in resources; Xiaokang Zhang, Haoyi Li, Yunyun Duan and Hongyan Chen involved in data curation; Song Lin, Bin Jing and Yanmei Tie took part in supervision; Guobin Zhang, Huawei Huang and Bin Jing took part in funding acquisition.

Funding This work was supported by the National Natural Science Foundation of China (81801042), Beijing Hospital Authority Youth Program (20190504), Beijing Outstanding Talent Training Foundation, Youth Backbone Individual Project (2018000021469G230) and Beijing Municipal Commission of Education (KM202010025025). The funding agencies have no role in the study design, data collection and analysis, decision to publish or manuscript preparation.

Data availability The datasets analyzed during the current study are available from the corresponding author on reasonable request.

![Image](./Zhang2022_artifacts/image_000015_6d288351cfd118d4d9f276a4ec7eea4c2c96b4ff7292fd5b9a12f353117b71ce.png)

Code availability The code of the individual structural abnormality map is available from the corresponding author on reasonable request.

## Declarations

Conflict of interest The authors have no relevant financial or non-financial interests to disclose.

Ethics approval The study was approved by the Institutional Review Board of Beijing Tiantan Hospital, Capital Medical University, Beijing, China (KY2020-048-01).

Consent to participate Informed consent was obtained from all individual participants included in the study.

Consent for publication Patients signed informed consent regarding publishing their data and photographs.

## References

1. Almairac F, Duffau H, Herbet G (2018) Contralesional macrostructural plasticity of the insular cortex in patients with glioma: A VBM study. Neurology 91:e1902-e1908. https://  doi.  org/  10.  1212/ wnl.  00000  00000  006517
2. Asan L, Falfán-Melgoza C, Beretta CA, Sack M, Zheng L, WeberFahr W, Kuner T, Knabbe J (2021) Cellular correlates of gray matter volume changes in magnetic resonance morphometry identified by two-photon microscopy. Sci Rep 11:4234. https://  doi.  org/  10. 1038/  s41598-  021-  83491-8
3. Bonthius DJ, Solodkin A, Van Hoesen GW (2005) Pathology of the insular cortex in Alzheimer disease depends on cortical architecture. J Neuropathol Exp Neurol 64:910-922. https://  doi.  org/  10. 1097/  01.  jnen.  00001  82983.  87106.  d1
4. Chang LJ, Yarkoni T, Khaw MW, Sanfey AG (2013) Decoding the role of the insula in human cognition: functional parcellation and large-scale reverse inference. Cereb Cortex 23:739-749. https:// doi.  org/  10.  1093/  cercor/  bhs065
5. Chincarini A, Bosco P, Calvini P, Gemme G, Esposito M, Olivieri C, Rei L, Squarcia S, Rodriguez G, Bellotti R et al (2011) Local MRI analysis approach in the diagnosis of early and prodromal Alzheimer's disease. Neuroimage 58:469-480. https://  doi.  org/  10. 1016/j.  neuro  image.  2011.  05.  083
6. Darvishi P, Batchala PP, Patrie JT, Poisson LM, Lopes MB, Jain R, Fadul CE, Schiff D, Patel SH (2020) Prognostic Value of Preoperative MRI Metrics for Diffuse Lower-Grade Glioma Molecular Subtypes. AJNR Am J Neuroradiol 41:815-821. https://  doi. org/  10.  3174/  ajnr.  A6511
7. Davey J, Thompson HE, Hallam G, Karapanagiotidis T, Murphy C, De Caso I, Krieger-Redwood K, Bernhardt BC, Smallwood J, Jefferies E (2016) Exploring the role of the posterior middle temporal gyrus in semantic cognition: Integration of anterior temporal lobe with executive processes. Neuroimage 137:165-177. https:// doi.  org/  10.  1016/j.  neuro  image.  2016.  05.  051
8. De Baene W, Rutten GM, Sitskoorn MM (2019) Cognitive functioning in glioma patients is related to functional connectivity measures of the non-tumoural hemisphere. Eur J Neurosci 50:3921-3933. https://  doi.  org/  10.  1111/  ejn.  14535
9. Duffau H, Gatignol P, Moritz-Gasser S, Mandonnet E (2009) Is the left uncinate fasciculus essential for language? A cerebral stimulation study. J Neurol 256:382-389. https://  doi.  org/  10.  1007/ s00415-  009-  0053-9
10.  Eckel-Passow JE, Lachance DH, Molinaro AM, Walsh KM, Decker PA, Sicotte H, Pekmezci M, Rice T, Kosel ML, Smirnov IV et al (2015) Glioma Groups Based on 1p/19q, IDH, and TERT Promoter Mutations in Tumors. N Engl J Med 372:2499-2508. https://  doi.  org/  10.  1056/  NEJMo  a1407  279
11. Gehring K, Roukema JA, Sitskoorn MM (2012) Review of recent studies on interventions for cognitive deficits in patients with cancer. Expert Rev Anticancer Ther 12:255-269. https://  doi.  org/  10. 1586/  era.  11.  202
12. Gehring K, Sitskoorn MM, Aaronson NK, Taphoorn MJ (2008) Interventions for cognitive deficits in adults with brain tumours. Lancet  Neurol  7:548-560.  https://  doi.  org/  10.  1016/  S14744422(08)  70111-X
13. Gu J, Kanai R (2014) What contributes to individual differences in brain structure? Front Hum Neurosci 8:262. https://  doi.  org/  10. 3389/  fnhum.  2014.  00262
14. Hartmann C, Hentschel B, Wick W, Capper D, Felsberg J, Simon M, Westphal M, Schackert G, Meyermann R, Pietsch T et al (2010) Patients with IDH1 wild type anaplastic astrocytomas exhibit worse prognosis than IDH1-mutated glioblastomas, and IDH1 mutation status accounts for the unfavorable prognostic effect of higher age: implications for classification of gliomas. Acta Neuropathol 120:707-718. https://  doi.  org/  10.  1007/ s00401-  010-  0781-z
15. Henderson F, Abdullah KG, Verma R, Brem S (2020) Tractography and the connectome in neurosurgical treatment of gliomas: the premise, the progress, and the potential. Neurosurg Focus 48:E6. https://  doi.  org/  10.  3171/  2019.  11.  Focus  19785
16. Hernandez  M  (2019)  PDE-constrained  LDDMM  via  geodesic shooting and inexact Gauss-Newton-Krylov optimization using the incremental adjoint Jacobi equations. Phys Med Biol 64:025002. https://  doi.  org/  10.  1088/  1361-  6560/  aaf598
17. Hu G, Hu X, Yang K, Liu D, Xue C, Liu Y, Xiao C, Zou Y, Liu H, Chen J (2020) Restructuring of contralateral gray matter volume associated with cognition in patients with unilateral temporal lobe glioma before and after surgery. Hum Brain Mapp 41:1786-1796. https://  doi.  org/  10.  1002/  hbm.  24911
18. Iaccarino L, La Joie R, Edwards L, Strom A, Schonhaut DR, Ossenkoppele R, Pham J, Mellinger T, Janabi M, Baker SL et al (2021) Spatial Relationships between Molecular Pathology and Neurodegeneration in the Alzheimer's Disease Continuum. Cereb Cortex 31:1-14. https://  doi.  org/  10.  1093/  cercor/  bhaa1  84
19. Incekara F, Satoer D, Visch-Brink E, Vincent A, Smits M (2018) Changes in language white matter tract microarchitecture associated with cognitive deficits in patients with presumed low-grade glioma. J Neurosurg:1-9.https://  doi.  org/  10.  3171/  2017.  12.  Jns17 1681
20. Jenkins RB, Blair H, Ballman KV, Giannini C, Arusell RM, Law M, Flynn H, Passe S, Felten S, Brown PD et al (2006) A t(1;19) (q10;p10) mediates the combined deletions of 1p and 19q and predicts a better prognosis of patients with oligodendroglioma. Cancer Res 66:9852-9861. https://  doi.  org/  10.  1158/  0008-  5472. CAN-  06-  1796
21. Kim HJ, Cheong EN, Jo S, Lee S, Shim WH, Kang DW, Kwon M, Kim JS, Lee JH (2021) Early Impairment in the Ventral Visual Pathway Can Predict Conversion to Dementia in Patients With Amyloid-negative Amnestic Mild Cognitive Impairment. Alzheimer Dis Assoc Disord. https://  doi.  org/  10.  1097/  wad.  00000  00000 000457
22. Kinoshita M, Nakajima R, Shinohara H, Miyashita K, Tanaka S, Okita H, Nakada M, Hayashi Y (2016) Chronic spatial working memory deficit associated with the superior longitudinal fasciculus: a study using voxel-based lesion-symptom mapping and intraoperative direct stimulation in right prefrontal glioma surgery. J Neurosurg 125:1024-1032. https://  doi.  org/  10.  3171/  2015.  10.  Jns15 91

![Image](./Zhang2022_artifacts/image_000016_25490d8bc5baf5489904f88be00da9f021f713dc49e9c086e9ba6846cbf94c85.png)

23. Klein M (2016) Lesion momentum as explanation for preoperative neurocognitive function in patients with malignant glioma. Neuro Oncol 18:1595-1596. https://  doi.  org/  10.  1093/  neuonc/  now266
24. Koch G, Bonnì S, Pellicciari MC, Casula EP, Mancini M, Esposito R, Ponzo V, Picazio S, Di Lorenzo F, Serra L et al (2018) Transcranial magnetic stimulation of the precuneus enhances memory and neural activity in prodromal Alzheimer's disease. Neuroimage 169:302-311. https://  doi.  org/  10.  1016/j.  neuro  image.  2017.  12.  048
25. La Joie R, Perrotin A, Barré L, Hommet C, Mézenge F, Ibazizene M, Camus V, Abbas A, Landeau B, Guilloteau D et al (2012) Regionspecific hierarchy between atrophy, hypometabolism, and β-amyloid (Aβ) load in Alzheimer's disease dementia. J Neurosci 32:1626516273. https://  doi.  org/  10.  1523/  jneur  osci.  2170-  12.  2012
26. Liu H, Liu WJ, Wang D, Dahmani L (2020) Individual-Specific Analysis for Psychoradiology. Neuroimaging Clin N Am 30:45-51. https://  doi.  org/  10.  1016/j.  nic.  2019.  09.  003
27. Lohmann P, Lerche C, Bauer EK, Steger J, Stoffels G, Blau T, Dunkl V, Kocher M, Viswanathan S, Filss CP et al (2018) Predicting IDH genotype in gliomas using FET PET radiomics. Sci Rep 8:13328. https://  doi.  org/  10.  1038/  s41598-  018-  31806-7
28. Louis DN, Perry A, Reifenberger G, von Deimling A, FigarellaBranger D, Cavenee WK, Ohgaki H, Wiestler OD, Kleihues P, Ellison DW (2016) The 2016 World Health Organization Classification of Tumors of the Central Nervous System: a summary. Acta Neuropathol 131:803-820. https://  doi.  org/  10.  1007/  s00401-  016-  1545-1
29. Louis DN, Perry A, Wesseling P, Brat DJ, Cree IA, FigarellaBranger D, Hawkins C, Ng HK, Pfister SM, Reifenberger G et al (2021) The 2021 WHO Classification of Tumors of the Central Nervous System: a summary. Neuro Oncol 23:1231-1251. https:// doi.  org/  10.  1093/  neuonc/  noab1  06
30. Lu CF, Hsu FT, Hsieh KL, Kao YJ, Cheng SJ, Hsu JB, Tsai PH, Chen RJ, Huang CC, Yen Y et al (2018) Machine Learning-Based Radiomics for Molecular Subtyping of Gliomas. Clin Cancer Res 24:4429-4436. https://  doi.  org/  10.  1158/  1078-  0432.  Ccr-  17-  3445
31. Maesawa S, Bagarinao E, Fujii M, Futamura M, Motomura K, Watanabe H, Mori D, Sobue G, Wakabayashi T (2015) Evaluation of resting state networks in patients with gliomas: connectivity changes in the unaffected side and its relation to cognitive function. PLoS ONE 10:e0118072. https://  doi.  org/  10.  1371/  journ  al.  pone.  01180  72
32. Montandon ML, Haller S, Scheffler M, Giannakopoulos P, Herrmann FR, Gold G, Kövari E (2020) Medial temporal lobe volume is associated with neuronal loss but not with hippocampal microinfarcts despite their high frequency in aging brains. Neurobiol Aging 95:9-14. https://  doi.  org/  10.  1016/j.  neuro  biola  ging.  2020.  06.  008
33. Nakajima R, Kinoshita M, Miyashita K, Okita H, Genda R, Yahata T, Hayashi Y, Nakada M (2017) Damage of the right dorsal superior longitudinal fascicle by awake surgery for glioma causes persistent visuospatial dysfunction. Sci Rep 7:17158. https://  doi.  org/  10.  1038/ s41598-  017-  17461-4
34. Noll KR, Sullaway C, Ziu M, Weinberg JS, Wefel JS (2015) Relationships between tumor grade and neurocognitive functioning in patients with glioma of the left temporal lobe prior to surgical resection. Neuro Oncol 17:580-587. https://  doi.  org/  10.  1093/  neuonc/ nou233
35. Peng H, Huo J, Li B, Cui Y, Zhang H, Zhang L, Ma L (2020) Predicting Isocitrate Dehydrogenase (IDH) Mutation Status in Gliomas Using Multiparameter MRI Radiomics Features. J Magn Reson Imaging. https://  doi.  org/  10.  1002/  jmri.  27434
36. Scheff SW, Price DA, Schmitt FA, Scheff MA, Mufson EJ (2011) Synaptic loss in the inferior temporal gyrus in mild cognitive impairment and Alzheimer's disease. J Alzheimers Dis 24:547-557. https://  doi.  org/  10.  3233/  jad-  2011-  101782
37. Soike MH, McTyre ER, Shah N, Puchalski RB, Holmes JA, Paulsson AK, Miller LD, Cramer CK, Lesser GJ, Strowd RE et al (2018) Glioblastoma radiomics: can genomic and molecular characteristics
16. correlate with imaging response patterns? Neuroradiology 60:10431051. https://  doi.  org/  10.  1007/  s00234-  018-  2060-y
38. Stoecklein VM, Stoecklein S, Galie F, Ren J, Schmutzer M, Unterrainer M, Albert NL, Kreth FW, Thon N, Liebig T et al (2020) Resting-state fMRI detects alterations in whole brain connectivity related to tumor biology in glioma patients. Neuro Oncol 22:1388-1398. https://  doi.  org/  10.  1093/  neuonc/  noaa0  44
39. Suh CH, Kim HS, Jung SC, Choi CG, Kim SJ (2019) Imaging prediction of isocitrate dehydrogenase (IDH) mutation in patients with glioma: a systemic review and meta-analysis. Eur Radiol 29:745758. https://  doi.  org/  10.  1007/  s00330-  018-  5608-7
40. Takahashi S, Takahashi W, Tanaka S, Haga A, Nakamoto T, Suzuki Y, Mukasa A, Takayanagi S, Kitagawa Y, Hana T et al (2019) Radiomics Analysis for Glioma Malignancy Evaluation Using Diffusion Kurtosis and Tensor Imaging. Int J Radiat Oncol Biol Phys 105:784-791. https://  doi.  org/  10.  1016/j.  ijrobp.  2019.  07.  011
41. Tetreault AM, Phan T, Orlando D, Lyu I, Kang H, Landman B, Darby RR (2020) Network localization of clinical, cognitive, and neuropsychiatric symptoms in Alzheimer's disease. Brain 143:1249-1260. https://  doi.  org/  10.  1093/  brain/  awaa0  58
42. van der Voort SR, Incekara F, Wijnenga MMJ, Kapas G, Gardeniers M, Schouten JW, Starmans MPA, Nandoe Tewarie R, Lycklama GJ, French PJ et al (2019) Predicting the 1p/19q Codeletion Status of Presumed Low-Grade Glioma with an Externally Validated Machine Learning Algorithm. Clin Cancer Res 25:7455-7462. https://  doi. org/  10.  1158/  1078-  0432.  CCR-  19-  1127
43. Wang D, Buckner RL, Fox MD, Holt DJ, Holmes AJ, Stoecklein S, Langs G, Pan R, Qian T, Li K et al (2015) Parcellating cortical functional networks in individuals. Nat Neurosci 18:1853-1860. https://  doi.  org/  10.  1038/  nn.  4164
44. Yang P, Cai J, Yan W, Zhang W, Wang Y, Chen B, Li G, Li S, Wu C, Yao K et al (2016) Classification based on mutations of TERT promoter and IDH characterizes subtypes in grade II/III gliomas. Neuro Oncol 18:1099-1108. https://  doi.  org/  10.  1093/  neuonc/  now021
45. Yuan Y, Liu J, Zhao P, Xing F, Huo H, Fang T (2019) Structural Insights Into the Dynamic Evolution of Neuronal Networks as Synaptic Density Decreases. Front Neurosci 13:892. https://  doi.  org/  10. 3389/  fnins.  2019.  00892
46. Yushkevich PA, Piven J, Hazlett HC, Smith RG, Ho S, Gee JC, Gerig G (2006) User-guided 3D active contour segmentation of anatomical structures: significantly improved efficiency and reliability. Neuroimage 31:1116-1128. https://  doi.  org/  10.  1016/j.  neuro  image. 2006.  01.  015
47. Zhang GB, Cui XL, Sui DL, Ren XH, Zhang Z, Wang ZC, Lin S (2013) Differential molecular genetic analysis in glioblastoma multiforme of long- and short-term survivors: a clinical study in Chinese patients. J Neurooncol 113:251-258. https://  doi.  org/  10.  1007/ s11060-  013-  1102-x
48. Zhao B, Ibrahim JG, Li Y, Li T, Wang Y, Shan Y, Zhu Z, Zhou F, Zhang J, Huang C et al (2019) Heritability of Regional Brain Volumes in Large-Scale Neuroimaging and Genetic Studies. Cereb Cortex 29:2904-2914. https://  doi.  org/  10.  1093/  cercor/  bhy157
49. Zhao B, Luo T, Li T, Li Y, Zhang J, Shan Y, Wang X, Yang L, Zhou F, Zhu Z et al (2019) Genome-wide association analysis of 19,629 individuals identifies variants influencing regional brain volumes and refines their genetic co-architecture with cognitive and mental health traits. Nat Genet 51:1637-1644. https://  doi.  org/  10.  1038/ s41588-  019-  0516-6

Publisher's  note Springer Nature remains neutral with regard to jurisdictional claims in published maps and institutional affiliations.

