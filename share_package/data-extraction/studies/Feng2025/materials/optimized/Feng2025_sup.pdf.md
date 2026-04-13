## Supplementary Materials

## Microstructural Mapping of Neural Pathways in Alzheimer's Disease using Macrostructure-Informed Normative Tractometry

Yixue Feng¹, Bramsh Q. Chandio¹, Julio E. Villalon-Reina¹, Sophia I. Thomopoulos¹, Talia M. Nir¹, Sebastian Benavidez¹, Emily Laltoo¹, Tamoghna Chattopadhyay¹, Himanshu Joshi², Ganesan Venkatasubramanian³, John P. John², Neda Jahanshad¹, Robert I. Reid⁴, Clifford R. Jack⁵, Michael W. Weiner⁶, Paul M. Thompson¹, for the Alzheimer's Disease Neuroimaging Initiative¹

| ¹Imaging Genetics Center, Mark and Mary Stevens Neuroimaging and Informatics Institute, Keck School of Medicine, University of Southern California, Marina del Rey, CA, United States   |
|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ²Multimodal Brain Image Analysis Laboratory National Institute of Mental Health and Neuro Sciences (NIMHANS),                                                                           |
| Bengaluru, Karnataka, India                                                                                                                                                             |
| ³Translational Psychiatry Laboratory, National Institute of Mental Health and Neuro Sciences (NIMHANS), Bengaluru, Karnataka, India                                                     |
| ⁴Department of Information Technology, Mayo Clinic and Foundation, Rochester, MN, United States ⁵Department of Radiology, Mayo Clinic and Foundation, Rochester, MN, United States      |
| ⁶Department of Radiology and Biomedical Imaging, UCSF School of Medicine, San Francisco, CA, United States                                                                              |

## Contents

| 1 Related Methods for Diffusion MRI Analysis                     | 1 Related Methods for Diffusion MRI Analysis                     | 1 Related Methods for Diffusion MRI Analysis                                         |   2 |
|------------------------------------------------------------------|------------------------------------------------------------------|--------------------------------------------------------------------------------------|-----|
| 2 Atlas Bundles                                                  | 2 Atlas Bundles                                                  | 2 Atlas Bundles                                                                      |   4 |
| 3 Association of Diffusion Measures with Diagnostic Groups       | 3 Association of Diffusion Measures with Diagnostic Groups       | 3 Association of Diffusion Measures with Diagnostic Groups                           |   5 |
|                                                                  | 3.1                                                              | MCI and AD Effects on Radial Diffusivity . . . . . . . . . . . . . . . . . . . . . . |   5 |
|                                                                  | 3.2                                                              | MCI and AD Effects on Axial Diffusivity . . . . . . . . . . . . . . . . . . . . . .  |   6 |
| 4 Statistical Significance of Association with Diagnostic Groups | 4 Statistical Significance of Association with Diagnostic Groups | 4 Statistical Significance of Association with Diagnostic Groups                     |   7 |
|                                                                  | 4.1                                                              | Statistical Significance of MCI and AD Effects on Mean Diffusivity . . . . . . . .   |   7 |
|                                                                  | 4.2                                                              | Statistical Significance of MCI and AD Effects on Radial Diffusivity . . . . . . . . |   8 |
|                                                                  | 4.3                                                              | Statistical Significance of MCI and AD Effects on Axial Diffusivity . . . . . . . .  |   9 |
|                                                                  | 4.4                                                              | Statistical Significance of MCI and AD Effects on Fractional Anisotropy . . . . . .  |  10 |
|                                                                  | 4.5                                                              | Statistical Significance of MCI and AD Effects on Shape Abnormalties . . . . . . .   |  11 |
| 5 Harmonization Evaluation of DTI- and MINT-Derived Measures     | 5 Harmonization Evaluation of DTI- and MINT-Derived Measures     | 5 Harmonization Evaluation of DTI- and MINT-Derived Measures                         |  12 |
|                                                                  | 5.0.1                                                            | Mean Bundle Profiles of Diffusion Indices Before and After Harmonization             |  12 |
|                                                                  | 5.0.2                                                            | Fixed Effect Coefficients Before and After Harmonization . . . . . . . . .           |  17 |

## 1 Related Methods for Diffusion MRI Analysis

Widely used methods to quantify WM microstructural differences between groups include voxel-based, fixel-based and tractometry approaches. Voxel-based methods first register voxel-based scalar maps such as those derived from DTI -to an atlas space, and then conduct univariate tests at the voxel or region-of-interest (ROI) level. Tract-based spatial statistics (TBSS) [1] was proposed to better align WM structures across subjects, by extracting the WM skeleton from fractional anisotropy (FA) maps, and summarizing white matter metrics in regions of the skeleton. When studying diffusion MRI, we also have to consider the special nature of WM structure: modeling how signals propagate along the long-range WM fibers is more faithful to the underlying anatomy than modeling signals in an isotropic neighborhood. Fixel-based analyses, such as connectivity-based fixel enhancement (CFE) [2], quantify microstructural properties in specific fiber populations within a voxel to better resolve crossing fibers, with special procedures for spatial normalization and statistics. Another family of methods tractometry [3, 4, 5] - creates bundle profiles or along-tract profiles [6, 7, 8] by projecting local measures of tissue microstructure, onto 3D white matter bundles reconstructed from tractography. Automatic Fiber Quantification (AFQ) [6] computes a mean bundle profile of microstructural measures -where the measure from each streamline (3D curve) in a bundle is weighted by distance to the bundle core. Bundle Analytics (BUAN) [7] uses the full structure of a bundle, yielding smoother along-tract metrics. BUAN has identified microstructural abnormalities in Parkinson's disease (PD), Alzheimer's disease (AD) [9] and bipolar disorder [10, 11] compared to matched controls. A more recent tractometry approach, Medial Tract Analysis (MeTA) [12] computes the core volume of a bundle around the medial surface for microstructural mapping to improve the reliability of bundle profiles. Many tractometry methods analyze segments/nodes along the length of bundles independently without taking into account neighboring information of points on a streamline. [13] proposed to model streamlines as functions to take into account neighboring information of points on the streamline.

Tractography, and by extension tractometry, demand substantial memory and computational resources, making it challenging to scale analyses to large cohorts, but the large volume of data can potentially be analyzed using deep learning methods. The unique format of tractography data is important to consider whenadapting deep learning model architectures used in other domains. Point-cloud based networks have been applied to tractography data for bundle segmentation [14] and predictive modeling [15, 16]. These models represent bundles as point clouds -as sets of 3D points, instead of a collection of streamlines -which are sets of ordered 3D point sequences. Without using a voxel grid, deep learning methods, such as PointNet [17], can encode point cloud data represented with 3D coordinates using operations that are invariant to permutations or arbitrary ordering of the data, such as fully connected layers and global pooling. These methods can extract bundle-level information, but they do not capture the dependency of neighboring points on a single streamline -an important source of macrostructural information derived from fiber tracking. Additionally, given the large number of streamlines per bundle, data reductions may be required to use a larger batch size during model training. In our prior work [18], we showed that a variational autoencoder (VAE) with 1D convolutional layers [19] can embed streamlines into a compact latent space and be used to detect structural anomalies in AD, and generate synthetic bundles via generative sampling.

Autoencoder-based architectures can also be used in normative models [20, 21, 22], to encode statistical distributions of features from a reference population. Deviations from the norm can be quantified and used in downstream analysis for group difference testing or mapping individual anomalies [23]. After training an autoencoder, data from the patient group is passed through the network at inference time, and the reconstruction error can be used for anomaly detection, or for group statistical compar-

isons. [24] first proposed normative tractometry to localize along-tract microstructural anomalies using autoencoders, revealing abnormalities in single subjects with genetic copy number variants (CNVs), epilepsy and schizophrenia. In our previous study using tractography data from the Alzheimer's Disease Neuroimaging Initiative (ADNI), our ConvVAE-based model identified 6 WM tracts with along-tract macrostructural anomalies in AD [25]. Using traditional machine learning methods, normative models have been used to quantify deviations from the normal range of variation in brain morphometry [26, 27] and functional network metrics [23] in large multi-site samples. Recent efforts have also studied age effects on brain microstructure over the human lifespan, producing normative charts for the primary microstructural metrics based on data from over 40,000 healthy individuals [28, 29, 30]. When merging multisite data to increase sample sizes, or to test the generalizability of the effects to different populations, the variability introduced by site (or scanning protocol) can strongly impact statistical analyses. Sources of multi-site variability include scanners from different vendors (e.g., GE, Siemens, or Philips), different acquisition protocols, and inclusion criteria which can affect sample characteristics [31]. Diffusion MRI is especially susceptible to protocol effects, although they can be carefully modeled using harmonization techniques [32, 33, 34]. A widely used method for data harmonization in neuroimaging, ComBat [35, 36] was recently adapted and extended to along-tract metrics, for use with the BUAN tractometry pipeline [37].

## 2 Atlas Bundles

Table 1: Thirty bundles from HCP842 atlas [38] used in bundle segmentation.

| Abbreviation    | Full Name                                  | Category    |
|-----------------|--------------------------------------------|-------------|
| AF_L            | Left Arcuate Fasciculus                    | Association |
| AF_R            | Right Arcuate Fasciculus                   | Association |
| EMC_L           | Left Extreme Capsule                       | Association |
| EMC_R           | Right Extrame Capsule                      | Association |
| IFOF_L          | Left Inferior Fronto-occipital Fasciculus  | Association |
| IFOF_R          | Right Inferior Fronto-occipital Fasciculus | Association |
| ILF_L           | Left Inferior Longitudinal Fasciculus      | Association |
| ILF_R           | Right Inferior Longitudinal Fasciculus     | Association |
| MdLF_L          | Left Middle Longitudinal Fasciculus        | Association |
| MdLF_R          | Right Middle Longitudinal Fasciculus       | Association |
| UF_L            | Left Uncinate Fasciculus                   | Association |
| UF_R            | Right Uncinate Fasciculus                  | Association |
| CST_L           | Left Corticospinal Tract                   | Projection  |
| CST_R           | Right Corticospinal Tract                  | Projection  |
| FPT_L           | Left Frontopontine Tract                   | Projection  |
| FPT_R           | Right Frontopontine Tract                  | Projection  |
| OPT_L           | Left Occipito Pontine Tract                | Projection  |
| OPT_R           | Right Occipito Pontine Tract               | Projection  |
| OR_L            | Left Optic Radiation                       | Projection  |
| OR_R            | Right Optic Radiation                      | Projection  |
| CC_ForcepsMajor | Corpus Callosum Major                      | Commissural |
| CC_ForcepsMinor | Corpus Callosum Minor                      | Commissural |
| CCMid           | Corpus Callosum Mid                        | Commissural |
| MLF_L           | Left Medial Longitudinal fasciculus        | Brainstem   |
| MLF_R           | Right Medial Longitudinal fasciculus       | Brainstem   |
| ML_L            | Left Medial Lemniscus                      | Brainstem   |
| ML_R            | Right Medial Lemniscus                     | Brainstem   |
| STT_L           | Left Spinothalamic Tract                   | Brainstem   |
| STT_R           | Right Spinothalamic Tract                  | Brainstem   |
| V               | Vermis                                     | Cerebellum  |

## 3 Association of Diffusion Measures with Diagnostic Groups

## 3.1 MCI and AD Effects on Radial Diffusivity

Figure 1: Along-tract 𝛽 ( DX ) for DTI-RD and MAE-RD, for AD vs. CN and MCI vs. CN in the ADNI and NIMHANS cohorts, categorized by WM pathways.

![Image](./Feng2025_sup_artifacts/image_000000_2ae96b9e9eafe8f0e4c90ebfc94d93d481288a1835c50d04358bb95a186f8507.png)

## 3.2 MCI and AD Effects on Axial Diffusivity

Figure 2: Along-tract 𝛽 ( DX ) for DTI-AxD and MAE-AxD, for AD vs. CN and MCI vs. CN in the ADNI and NIMHANS cohorts, categorized by WM pathways.

![Image](./Feng2025_sup_artifacts/image_000001_1c694a39e5b5378dccda1fa8a41cacb2e96bd1567e73811d1b05fafb8c9a6082.png)

## 4 Statistical Significance of Association with Diagnostic Groups

## 4.1 Statistical Significance of MCI and AD Effects on Mean Diffusivity

Figure 3: Along-tract -log 10 ( 𝑝 ) after FDR correction for MAE-MD and DTI-MD, for AD vs. CN and MCI vs. CN in the ADNI and NIMHANS cohorts, categorized by WM pathways.

![Image](./Feng2025_sup_artifacts/image_000002_8f88d1cb4ee35c607a2e7162b5b0994230475410a967ea2f1d32741f80a45d41.png)

## 4.2 Statistical Significance of MCI and AD Effects on Radial Diffusivity

Figure 4: Along-tract -log 10 ( 𝑝 ) after FDR correction for MAE-RD and DTI-RD, for AD vs. CN and MCI vs. CN in the ADNI and NIMHANS cohorts, categorized by WM pathways.

![Image](./Feng2025_sup_artifacts/image_000003_2f5fefa2e3166fe8ed42e029bd9b07fad6ef73b8c2f6980d248922de9daa58c4.png)

## 4.3 Statistical Significance of MCI and AD Effects on Axial Diffusivity

Figure 5: Along-tract -log 10 ( 𝑝 ) after FDR correction for MAE-AxD and DTI-AxD, for AD vs. CN and MCI vs. CN in the ADNI and NIMHANS cohorts, categorized by WM pathways.

![Image](./Feng2025_sup_artifacts/image_000004_bd9a08bf23c2ab0081a0318d29433e5dc93e17a3e4b40f914a1bbdf1793f2a07.png)

## 4.4 Statistical Significance of MCI and AD Effects on Fractional Anisotropy

Figure 6: Along-tract -log 10 ( 𝑝 ) after FDR correction for MAE-FA and DTI-FA, for AD vs. CN and MCI vs. CN in the ADNI and NIMHANS cohorts, categorized by WM pathways.

![Image](./Feng2025_sup_artifacts/image_000005_48d3edf35ecbdb2820ca19ac697db85064e0595ad476cd890d95dd20f37f6952.png)

## 4.5 Statistical Significance of MCI and AD Effects on Shape Abnormalties

Figure 7: Along-tract -log 10 ( 𝑝 ) after FDR correction for MAE-Shape, for AD vs. CN and MCI vs. CN in the ADNI and NIMHANS cohorts, categorized by WM pathways.

![Image](./Feng2025_sup_artifacts/image_000006_95a6e36f99317483b35f15965e7a8f07b65879f9a96436037ef81e6d2ec26254.png)

## 5 Harmonization Evaluation of DTI- and MINT-Derived Measures

## 5.0.1 Mean Bundle Profiles of Diffusion Indices Before and After Harmonization

All 9 DTI and MAE measures show better alignment and the trends are well preserved after harmonization for most bundles. Protocol effects vary across bundles for all measures, but their effects on MAE measures are more consistent across bundles compared to the corresponding DTI measures. The overall trends of protocol effects also differ across metrics for the same bundle, except between 3 diffusivity measures (MD, RD and AxD). These results show that harmonization applied for each bundle and measure is appropriate. Interestingly, MAE-Shape is different across cohorts but similar between protocols within the same cohort. This is likely due to model fine-tuning applied separately for each cohort, or different preprocessing pipelines. The NIMHANS Siemens protocol shows the greatest protocol difference when compared to the Philips protocol from the same cohort or the ADNI protocols. Out of all bundles, the uncinate fasciculus (UF) and inferior fronto-occipital fasciculus (IFOF) bundles show the least alignment after ComBat harmonization. This may be due to inconsistent quality of tractography and segmentation of these bundles across all protocols.

Figure 8: MAE-Shape averaged per bundle, before and after ComBat harmonization, grouped by scanning protocols from both ADNI and NIMHAN.

![Image](./Feng2025_sup_artifacts/image_000007_f666aded083ae01cec82a7d2df291b09a07e38184d4de823950acd73ea9e3a1a.png)

Figure 9: DTI-FA (a) and MAE-FA (b) averaged per bundle, before and after ComBat harmonization, grouped by scanning protocols from both ADNI and NIMHAN.

![Image](./Feng2025_sup_artifacts/image_000008_40b772cfaf1b670c44445395ec6895cb770d9c2877aa161efe098fbbee5f1e2f.png)

Figure 10: DTI-MD (a) and MAE-MD (b) averaged per bundle, before and after ComBat harmonization, grouped by scanning protocols from both ADNI and NIMHAN.

![Image](./Feng2025_sup_artifacts/image_000009_af52de00f1304ff5b9ec41feca30e0cbe7753c6ab11e44bccbbfcfc7dc080718.png)

Figure 11: DTI-RD (a) and MAE-RD (b) averaged per bundle, before and after ComBat harmonization, grouped by scanning protocols from both ADNI and NIMHAN.

![Image](./Feng2025_sup_artifacts/image_000010_de61b41ac309ab395e1f66c005a013a7aa0899e93d12055ca107d4211eba6ba3.png)

Figure 12: DTI-AxD (a) and MAE-AxD (b) averaged per bundle, before and after ComBat harmonization, grouped by scanning protocols from both ADNI and NIMHAN.

![Image](./Feng2025_sup_artifacts/image_000011_6a98322bf91c6d2763caf9a414b1ba2e9c767d479a068b76e5838d610f24ffbc.png)

## 5.0.2 Fixed Effect Coefficients Before and After Harmonization

To evaluate how ComBat influences statistical analysis, we compared the fixed effect coefficient for diagnosis 𝛽 ( DX ) from the linear regression models before and after using ComBat to harmonize the data. If ComBat was successful in removing site effects while preserving biological variability, 𝛽 should remain the same before and after harmonization [39]. In Figure 13 and 14, we plot the 𝛽 ( DX ) from the AD vs. CN comparison per bundle, for MAE features for both ADNI and NIMHANS, with and without ComBat. Overall, 𝛽 ( DX ) are largely the same for most bundles across all MAE metrics in both cohorts, and the trends are preserved before and after ComBat. 𝛽 ( DX ) is more consistent in ADNI than NIMHANS, except we see a large AD effect on all MAE microstructural metrics in FPT\_R, AF\_R, and a decreased effect on MAE-Shape in the brainstem bundles after ComBat. In the NIMHANS cohort, we see larger differences of 𝛽 ( DX ) before or after harmonization compared to the ADNI cohort, primarily in the brainstem bundles, where the AD effect on all MAE metrics except MAE-FA is larger after ComBat. Considering that ComBat did not align the UF mean bundle profiles very well, as noted in Section 5.0.1, we also see that 𝛽 ( DX ) in these bundles exhibits larger MAE -for all metrics -after ComBat.

Figure 13: Fixed effect coefficient of diagnosis 𝛽 ( DX ) before and after ComBat harmonization for MAE measures calculated from subjects in the ADNI cohort.

![Image](./Feng2025_sup_artifacts/image_000012_ff232a8757770c3baa3c887d3a98b5d321fd71ec9c93425495f182c1c351b2ca.png)

Figure 14: Fixed effect coefficient of diagnosis 𝛽 ( DX ) before and after ComBat harmonization for MAE measures calculated from subjects in the NIMHANS cohort.

![Image](./Feng2025_sup_artifacts/image_000013_fde20f94b4440cf0f3dc45299505b1e23471323afb6e03862aa8a08fdafc2080.png)

## References

- [1] Smith SM, Jenkinson M, Johansen-Berg H, et al. Tract-based spatial statistics: Voxelwise analysis of multi-subject diffusion data. NeuroImage. 2006;31(4):1487-1505. doi: 10.1016/j.neuroimage.2006.02.024
- [2] Raffelt DA, Smith RE, Ridgway GR, et al. Connectivity-based fixel enhancement: Whole-brain statistical analysis of diffusion MRI measures in the presence of crossing fibres. NeuroImage. 2015;117:40-55. doi: 10.1016/j.neuroimage.2015.05.039
- [3] Gong G, Jiang T, Zhu C, et al. Asymmetry analysis of cingulum based on scale-invariant parameterization by diffusion tensor imaging. Human Brain Mapping. 2005;24(2):92-98. doi: 10.1002/hbm.20072
- [4] Jones DK, Travis AR, Eden G, Pierpaoli C, Basser PJ. PASTA: Pointwise assessment of streamline tractography attributes: PASTA. Magnetic Resonance in Medicine. 2005;53(6):1462-1467. doi: 10.1002/mrm.20484
- [5] Bells S, Cercignani M, Deoni S, et al. Tractometry Comprehensive Multi-modal Quantitative Assessment of White Matter Along Specific Tracts. In: 2011.
- [6] Yeatman JD, Dougherty RF, Myall NJ, Wandell BA, Feldman HM. Tract Profiles of White Matter Properties: Automating Fiber-Tract Quantification. PLoS ONE. 2012;7(11):e49790. doi: 10.1371/journal.pone.0049790
- [7] Chandio BQ, Risacher SL, Pestilli F, et al. Bundle analytics, a computational framework for investigating the shapes and profiles of brain pathways across populations. Scientific Reports. 2020;10(1):17149. doi: 10.1038/s41598-020-74054-4
- [8] Kruper J, Yeatman JD, Richie-Halford A, et al. Evaluating the reliability of human brain white matter tractometry. preprint, Neuroscience; 2021
- [9] Chandio BQ, Owens-Walton C, Villalon-Reina JE, et al. Microstructural changes in the white matter tracts of the brain due to mild cognitive impairment. Alzheimer's &amp; Dementia. 2022;18(S5):e065339. doi: 10.1002/alz.065339
- [10] Nabulsi L, Chandio BQ, Dhinagar N, et al. Along-Tract Statistical Mapping of Microstructural Abnormalities in Bipolar Disorder: A Pilot Study. preprint, Neuroscience; 2023
- [11] Nabulsi L, Chandio BQ, McPhilemy G, et al. Multi-Site Statistical Mapping of Along-Tract Microstructural Abnormalities in Bipolar Disorder with Diffusion MRI Tractometry. In: IEEE 2023; Mexico City, Mexico:1-5
- [12] Gari IB, Ramesh A, Javid S, et al. Medial Tractography Analysis (MeTA) for White Matter Population Analyses Across Datasets. In: IEEE 2023; Baltimore, MD, USA:1-5
- [13] Chandio BQ. Advancing White Matter Tractometry of the Brain Using Diffusion MRI and Machine Learning . Indiana University, 2022.
- [14] Xue T, Zhang F, Zhang C, et al. Supwma: Consistent and Efficient Tractography Parcellation of Superficial White Matter with Deep Learning. In: IEEE 2022; Kolkata, India:1-5

- [15] Chen Y, Zhang F, Zhang C, et al. White Matter Tracts are Point Clouds: Neuropsychological Score Prediction and Critical Region Localization via Geometric Deep Learning. 2022. arXiv:2207.02402 [cs].
- [16] Chen Y, Zhang F, Zekelman LR, et al. TractGraphCNN: anatomically informed graph CNN for classification using diffusion MRI tractography. 2023. Publisher: arXiv Version Number: 1doi: 10.48550/ARXIV.2301.01911
- [17] Qi CR, Su H, Mo K, Guibas LJ. PointNet: Deep Learning on Point Sets for 3D Classification and Segmentation. 2017. Number: arXiv:1612.00593 arXiv:1612.00593 [cs].
- [18] Feng Y, Chandio BQ, Thomopoulos SI, Thompson PM. Variational Autoencoders for Generating Synthetic Tractography-Based Bundle Templates in a Low-Data Setting. preprint, Neuroscience; 2023
- [19] Legarreta JH, Petit L, Rheault F, et al. Filtering in tractography using autoencoders (FINTA). Medical Image Analysis. 2021;72:102126. arXiv: 2010.04007doi: 10.1016/j.media.2021.102126
- [20] Ruff L, Kauffmann JR, Vandermeulen RA, et al. A Unifying Review of Deep and Shallow Anomaly Detection. Proceedings of the IEEE. 2021;109(5):756-795. doi: 10.1109/JPROC.2021.3052449
- [21] Pinaya WHL, Scarpazza C, Garcia-Dias R, et al. Using normative modelling to detect disease progression in mild cognitive impairment and Alzheimer's disease in a cross-sectional multi-cohort study. Scientific Reports. 2021;11(1):15746. doi: 10.1038/s41598-021-95098-0
- [22] Lawry Aguila A, Chapman J, Janahi M, Altmann A. Conditional VAEs for Confound Removal and Normative Modelling of Neurodegenerative Diseases. In: Wang L, Dou Q, Fletcher PT, Speidel S, Li S. , eds. Medical Image Computing and Computer Assisted Intervention - MICCAI 2022 , , . 13431. Cham: Springer Nature Switzerland, 2022:430-440. Series Title: Lecture Notes in Computer Science
- [23] Rutherford S, Barkema P, Tso IF, et al. Evidence for embracing normative modeling. eLife. 2023;12:e85082. doi: 10.7554/eLife.85082
- [24] Chamberland M, Genc S, Tax CMW, et al. Detecting microstructural deviations in individuals with deep diffusion MRI tractometry. Nature Computational Science. 2021;1(9):598-606. doi: 10.1038/s43588-021-00126-8
- [25] Feng Y, Chandio BQ, Chattopadhyay T, et al. Learning optimal white matter tract representations from tractography using a deep generative model for population analyses. In: SPIE 2023; Valparaíso, Chile:48
- [26] Bethlehem RaI, Seidlitz J, White SR, et al. Brain charts for the human lifespan. Nature. 2022;604(7906):525-533. doi: 10.1038/s41586-022-04554-y
- [27] Ge R, Yu Y, Qi YX, et al. Normative modelling of brain morphometry across the lifespan with CentileBrain: algorithm benchmarking and model optimisation. The Lancet Digital Health. 2024;6(3):e211-e221. doi: 10.1016/S2589-7500(23)00250-9
- [28] Villalón-Reina JE, Moreau CA, Nir TM, et al. Multi-site Normative Modeling of Diffusion Tensor Imaging Metrics Using Hierarchical Bayesian Regression. In: Wang L, Dou Q, Fletcher PT, Speidel S, Li S. , eds. Medical Image Computing and Computer Assisted Intervention - MICCAI 2022 , , . 13431. Cham: Springer Nature Switzerland, 2022:207-217. Series Title: Lecture Notes in Computer Science

- [29] Villalón-Reina JE, Zhu AH, Nir TM, et al. Large-scale Normative Modeling of Brain Microstructure. In: IEEE 2023; Mexico City, Mexico:1-5
- [30] Zhu AH, Nir TM, Javid S, et al. Reference curves for harmonizing multi-site regional diffusion MRI metrics across the lifespan. 2024. Pages: 2024.02.22.581646 Section: New Results
- [31] Bayer JMM, Thompson PM, Ching CRK, et al. Site effects how-to and when: An overview of retrospective techniques to accommodate site effects in multi-site neuroimaging analyses. Frontiers in Neurology. 2022;13:923988. doi: 10.3389/fneur.2022.923988
- [32] Zhu AH, Moyer DC, Nir TM, Thompson PM, Jahanshad N. Challenges and Opportunities in dMRI Data Harmonization. In: Bonet-Carne E, Grussu F, Ning L, Sepehrband F, Tax CMW., eds. Computational Diffusion MRI , , Cham: Springer International Publishing, 2019:157-172. Series Title: Mathematics and Visualization
- [33] Thomopoulos SI, Nir TM, Villalon-Reina JE, et al. Diffusion MRI metrics and their relation to dementia severity: effects of harmonization approaches. In: Walker A, Rittner L, Romero Castro E, Lepore N, Brieva J, Linguraru MG., eds. 17th International Symposium on Medical Information Processing and Analysis SPIE 2021; Campinas, Brazil:79
- [34] Schilling KG, Tax CM, Rheault F, et al. Fiber tractography bundle segmentation depends on scanner effects, vendor effects, acquisition resolution, diffusion sampling scheme, diffusion sensitization, and bundle segmentation workflow. NeuroImage. 2021;242:118451. doi: 10.1016/j.neuroimage.2021.118451
- [35] Johnson WE, Li C, Rabinovic A. Adjusting batch effects in microarray expression data using empirical Bayes methods. Biostatistics. 2007;8(1):118-127. doi: 10.1093/biostatistics/kxj037
- [36] Fortin JP, Parker D, Tunç B, et al. Harmonization of multi-site diffusion tensor imaging data. NeuroImage. 2017;161:149-170. doi: 10.1016/j.neuroimage.2017.08.047
- [37] Chandio BQ, Villalon-Reina JE, Nir TM, et al. Bundle Analytics based Data Harmonization for Multi-Site Diffusion MRI Tractometry. preprint, Neuroscience; 2024
- [38] Yeh FC, Panesar S, Fernandes D, et al. Population-averaged atlas of the macroscale human structural connectome and its network topology. NeuroImage. 2018;178:57-68. doi: 10.1016/j.neuroimage.2018.05.027
