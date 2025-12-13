# Quality Assessment Form

## Study Identification

- **Study ID**: Cirstian2024
- **Reference File Names**: Cirstian2024.pdf.md; media-1.docx.md; media-2.docx.md; media-3.docx.md; media-4.docx.md; media-5.docx.md; media-6.docx.md
- **Author, Journal, Year**: Cirstian et al., Biological Psychiatry, 2025
- **Title**: Lifespan Normative Models of White Matter Fractional Anisotropy: Applications to Early Psychosis
- **DOI**: 10.1016/j.biopsych.2025.07.021

---

## Assessment Items - Group A

### 1. Clarity of Research Objectives

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper explicitly states four clear research objectives at the end of the introduction section. These objectives include: (i) developing normative models of FA using large-scale data, (ii) investigating white matter FA in early psychosis and mapping individual deviations, (iii) demonstrating utility of multimodal data fusion combining FA with cortical thickness and subcortical volumes, and (iv) releasing models freely to the community. The PICO elements are identifiable: Population (early psychosis patients and healthy individuals across lifespan), Intervention (normative modeling of diffusion MRI), Comparator (case-control comparison and normative reference), and Outcome (individual-level deviation patterns and symptom associations).
- **Supporting Text**: The aims of this study are to: (i) develop normative models of Fractional Anisotropy (FA), the most widely used diffusion metric in neuroimaging [17], across major white matter tracts using a large dataset of over 25,000 healthy individuals across a broad age range. By using high-quality diffusion MRI data from the UK Biobank and the Human Connectome Project, we seek to establish robust models that capture lifespan trajectories of white matter organization; (ii) investigate white matter FA in early psychosis, a prototypical psychiatric disorder that is known to be highly heterogeneous in disease severity and course, as well as clinical symptom expression and clinical outcomes. Using the HCP Early Psychosis (HCP-EP) dataset [18], we aim to map both group level differences and individual deviations from the normative model in order to better understand individual variability in white matter integrity; (iii) we aim to illustrate the value of normative models for multi-modal data fusion, by combining FA deviations with cortical thickness and subcortical brain volume deviations with the goal to identify multimodal biological signatures and specific white matter pathways in psychosis associated with different psychosis symptom domains. Finally, (iv) we release all models freely to the community via our existing open-source software platforms [19].
- **Location**: Cirstian2024.pdf.md, lines 123-138

### 2. Clear Definition of Target Population

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper clearly defines both the normative reference population and the clinical target population. For the normative model, five cohorts are specified: HCP Baby (0.04 to 3 years), HCP Development (8 to 22 years), HCP Young Adult (22 to 37 years), HCP Aging (36 to 100 years), and UK Biobank (46 to 82 years), totaling N=24,915 with detailed demographic information provided in supplementary Table S1. For the clinical population, the HCP Early Psychosis dataset is used with clear diagnostic criteria (SCID-5 for DSM-5), including N=118 patients and N=55 controls (N=28 with diffusion data) with detailed demographic characteristics in supplementary Table S2.
- **Supporting Text**: The construction of the lifespan dataset involved integrating data from five cohorts having high-quality multi-shell diffusion data, i.e.: the HCP Baby [20], HCP Development [21], HCP Young Adult [22], HCP Aging [23] datasets, and the UK Biobank [24]. The demographic information is available in supplementary table 1. ... Next, we applied the model to the Human Connectome Project Early Psychosis (HCP-EP) dataset [18] (Jacobs et al., 2024), which includes multi-shell diffusion data and T1-weighted structural MRI derived from participants diagnosed with early psychosis (n=118) and control participants (n=55). ... Participants with early psychosis were diagnosed using the Structured Clinical Interview for DSM-5 (SCID-5) (First et al., 2015)...
- **Location**: Cirstian2024.pdf.md, lines 355-358, 383-390; media-5.docx.md (Table S1); media-6.docx.md (Table S2)

### 3. Clarity of Inclusion and Exclusion Criteria

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The paper specifies some exclusion criteria, particularly the outlier exclusion criterion for the normative model (deviations larger than 5 standard deviations from the mean). Diagnostic criteria for the clinical group (SCID-5 for DSM-5 diagnosis) are mentioned. However, comprehensive inclusion and exclusion criteria for participant recruitment are not fully detailed in the main text. Specific criteria such as medical comorbidities, medication restrictions, image quality thresholds, or other clinical exclusion criteria are not explicitly described. A flow chart showing participant numbers at each stage of inclusion/exclusion is not provided.
- **Supporting Text**: In line with our prior work [46] we refit the models after excluding gross outliers having deviations larger than 5 standard deviations from the mean (Figure 1C). ... Participants with early psychosis were diagnosed using the Structured Clinical Interview for DSM-5 (SCID-5) (First et al., 2015) and symptoms assessed with the Positive and Negative Syndrome Scale (PANSS) [27]...
- **Location**: Cirstian2024.pdf.md, lines 378-380, 389-392

### 4. Validity of Normative Modeling Outcome Measures

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper clearly defines the brain measure used: Fractional Anisotropy (FA), which is described as the most widely used diffusion metric in neuroimaging. The method for quantifying individual-level deviations (z-scores) is explicitly described. The normative reference population characteristics are thoroughly specified across five large cohorts (N=24,915). Multiple model quality metrics are defined and reported: explained variance (EV), skewness, and kurtosis. The interpretation method for deviation scores is clear, with specific thresholds mentioned (z-score threshold of ±2.6 corresponding to p=0.01 for identifying extreme deviations).
- **Supporting Text**: The aims of this study are to: (i) develop normative models of Fractional Anisotropy (FA), the most widely used diffusion metric in neuroimaging [17], across major white matter tracts... We assessed the quality of the normative modeling fit using three key out-of-sample metrics, namely explained variance (EV), evaluating the fit of the median regression line, in addition to skewness and kurtosis, which evaluate the shape of the distribution used to model the centiles. ... Next, we used these models to understand heterogeneity in white matter FA in psychosis. To achieve this, we applied these reference models to the HCP early psychosis (HCP-EP) dataset (N=173 with diffusion data -see supplementary table 2 for demographic information) in order to derive z-scores for each individual and tract. ... To achieve this, we set a z-score threshold between -2.6 and 2.6, which correspond to a p-=value of 0.01...
- **Location**: Cirstian2024.pdf.md, lines 123-125, 155-159, 185-188, 424-425

### 5. Handling of Confounding Variables

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper explicitly describes adjustment for multiple key confounding variables. The normative model incorporated sex, age, self-reported race (as dummy coded fixed effects), and site as covariates. The handling of non-linear age effects is addressed through B-spline basis expansion. For the multi-site data integration, site effects are explicitly modeled as covariates in the normative model. The paper also discusses the use of harmonized preprocessing pipelines across datasets and mentions transfer learning to account for site-specific effects when applying models to new data. Additionally, the authors acknowledge race as an important factor and release models both with and without race adjustment to allow researchers to choose appropriate models.
- **Supporting Text**: The model incorporated several covariates, including sex, age, and dummy coded race, and site. To address potential nonlinear effects and non-Gaussian distributions, we employed a warped Bayesian linear regression (BLR) model and used in previous research [4], [25]. This approach involved applying a third-order polynomial B-spline basis expansion over age, with five evenly spaced knots, combined with a SinhArcsinh warping function. ... Using transfer learning, as in our previous work, we can efficiently adapt the models with only a small amount of calibration data to account for site-specific effect. ... A strength of our analysis is that we specifically account for ethnicity in our models, by including self-reported race using fixed effects in the analysis, following our prior work [40]. ... For these reasons we also release the models that do not include race so that each researcher using these models can decide for themselves which model is more appropriate for their needs.
- **Location**: Cirstian2024.pdf.md, lines 374-378, 406-407, 280-283

### 6. Clarity of Data Sources

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper clearly identifies all data sources used for both normative modeling and clinical application. Five specific datasets are named for the normative model: HCP Baby [20], HCP Development [21], HCP Young Adult [22], HCP Aging [23], and UK Biobank [24], all with appropriate citations. For the clinical application, the HCP Early Psychosis (HCP-EP) dataset [18] is specified with citation. The demographic composition and age ranges for each dataset are provided in supplementary materials. The data availability statement mentions that UK Biobank data is available through access application, and code/models are available on GitHub.
- **Supporting Text**: The construction of the lifespan dataset involved integrating data from five cohorts having high-quality multi-shell diffusion data, i.e.: the HCP Baby [20], HCP Development [21], HCP Young Adult [22], HCP Aging [23] datasets, and the UK Biobank [24]. The demographic information is available in supplementary table 1. ... Next, we applied the model to the Human Connectome Project Early Psychosis (HCP-EP) dataset [18] (Jacobs et al., 2024), which includes multi-shell diffusion data and T1-weighted structural MRI derived from participants diagnosed with early psychosis (n=118) and control participants (n=55). ... The data used in the present study is part of the UK Biobank dataset which is available to be downloaded upon completing an access application.
- **Location**: Cirstian2024.pdf.md, lines 355-358, 383-386, 589-591

### 7. Description of Image Acquisition Protocol

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The paper indicates that high-quality multi-shell diffusion data were used from all five cohorts, and mentions that HCP datasets used the HCP-pipeline and UK Biobank used their documented processing protocol. However, specific imaging parameters (TR, TE, flip angle, voxel resolution, number of diffusion directions, b-values, etc.) are not provided in the main text. Scanner specifications (manufacturer, field strength) are not explicitly stated in the main manuscript. The paper refers readers to external documentation and other publications for these details rather than providing them directly.
- **Supporting Text**: The construction of the lifespan dataset involved integrating data from five cohorts having high-quality multi-shell diffusion data, i.e.: the HCP Baby [20], HCP Development [21], HCP Young Adult [22], HCP Aging [23] datasets, and the UK Biobank [24]. ... The processing of these datasets followed harmonized FSL-based pipelines, summarized in Figure 1A. Initially, pre-processing was performed: B0 intensity normalization, correction for EPI distortions, eddy-current-induced and movement corrections. These corrections were executed using the HCP-pipeline [42] for the HCP datasets while the UKB dataset was already processed according to the UKB documentation [43].
- **Location**: Cirstian2024.pdf.md, lines 355-365

---

## Assessment Items - Group B

### 1. Details of Data Preprocessing

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper provides comprehensive details of the preprocessing pipeline. The software used (FSL-based pipelines, HCP-pipeline, DTIfit, TBSS) is clearly specified with appropriate citations. Each major preprocessing step is described: B0 intensity normalization, correction for EPI distortions, eddy-current-induced and movement corrections, DTI model estimation to extract FA values, TBSS processing including registration to standard space (FMRIB58_FA), projection to skeletonized image (threshold at 0.2), and segmentation using the Johns Hopkins University (JHU) atlas to delineate 48 white matter tracts. Quality control procedures are mentioned, including outlier exclusion based on normative modeling approach (deviations >5 SD).
- **Supporting Text**: The processing of these datasets followed harmonized FSL-based pipelines, summarized in Figure 1A. Initially, pre-processing was performed: B0 intensity normalization, correction for EPI distortions, eddy-current-induced and movement corrections. These corrections were executed using the HCP-pipeline [42] for the HCP datasets while the UKB dataset was already processed according to the UKB documentation [43]. Subsequently, we estimated the DTI model using DTIfit on the lowest shell value in order to extract the fractional anisotropy (FA) values. Following this, we ran Tract-Based Spatial Statistics (TBSS) [44] on the FA images which included registration to a standard space (FMRIB58_FA), projection of each individual's FA image to the standard space skeletonized image (threshold at 0.2) to generate skeletonized FA images for each individual in the same space. Finally, segmentation was conducted using the Johns Hopkins University (JHU) atlas [45].
- **Location**: Cirstian2024.pdf.md, lines 359-371

### 2. Clarity of Data Partitioning Methods

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper clearly describes the data partitioning strategy. For the normative model, the dataset (N=24,915) was split into two equal groups: training set (N=12,457) and test set (N=12,457). The partitioning method is explicitly stated as stratified sampling to ensure even distribution of sex, race, dataset, and site. For the clinical analysis, transfer learning was used with half of the control participants for training and the remaining controls plus patients for testing. For the multimodal msCCA analysis, 1000 random splits into training (70%) and test (30%) sets were performed for stability selection. Measures to prevent data leakage are mentioned, including ensuring matched splits for diffusion and structural measures.
- **Supporting Text**: To prepare for the modelling stage, we began by splitting the dataset of subjects (N=24,915) into two equal groups: a test set (N=12,457) and a training set (N=12,457), stratified to ensure an even distribution of sex, race, dataset and site. ... Next, we divided this dataset into a training set, consisting of half of the control participants, and combined it with the larger training set described above to retrain the normative models for each white matter tract. Using transfer learning, as in our previous work, we can efficiently adapt the models with only a small amount of calibration data to account for site-specific effect. ... Note that the splits for this analysis were matched so that the same participants were in the training and test sets for diffusion and structural measures at each iteration. ... In more detail, we performed 1000 random splits of the dataset into a training (70%) and test set (30%)...
- **Location**: Cirstian2024.pdf.md, lines 372-374, 403-410, 443-444

### 3. Details of Normative Modeling Approach

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The statistical approach for normative modeling is described in detail. The model type is specified as warped Bayesian linear regression (BLR) model, which was used in previous research with citations [4], [25]. The basis expansion is detailed: third-order polynomial B-spline basis expansion over age with five evenly spaced knots. The warping function is specified as SinhArcsinh to address non-Gaussian distributions. The covariates included in the model are clearly stated: sex, age, dummy-coded race, and site. The software and version used are specified: Python version 3.8 with the Predictive Clinical Neuroscience PCN toolkit (PCNtoolkit on GitHub).
- **Supporting Text**: The model incorporated several covariates, including sex, age, and dummy coded race, and site. To address potential nonlinear effects and non-Gaussian distributions, we employed a warped Bayesian linear regression (BLR) model and used in previous research [4], [25]. This approach involved applying a third-order polynomial B-spline basis expansion over age, with five evenly spaced knots, combined with a SinhArcsinh warping function. ... All statistical analyses were conducted using Python version 3.8, with the Predictive Clinical Neuroscience PCN toolkit (GitHub, PCNtoolkit).
- **Location**: Cirstian2024.pdf.md, lines 374-382

### 4. Details of Training Algorithm

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The paper describes the overall training strategy including the iterative refitting process after outlier exclusion. The outlier threshold is specified (deviations larger than 5 standard deviations). However, specific details about optimization algorithms (e.g., gradient descent variants, L-BFGS), hyperparameter optimization methods, convergence criteria, number of iterations/epochs, or specific regularization techniques are not explicitly provided in the text. While the use of Bayesian linear regression is mentioned, the specific implementation details of the Bayesian inference procedure (e.g., variational inference, MCMC) are not described.
- **Supporting Text**: Next, we estimated deviation scores for each subject and white matter tract. In line with our prior work [46] we refit the models after excluding gross outliers having deviations larger than 5 standard deviations from the mean (Figure 1C). Once the models were refit with the cleaned data, we calculated the fit statistics, including explained variance, skew, and kurtosis. The extent of deviation for each subject was visualized by plotting individual z-scores against the mean and centiles of variation predicted by the model.
- **Location**: Cirstian2024.pdf.md, lines 378-382

### 5. Model Performance Evaluation Metrics

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper clearly defines the metrics used for evaluating normative model performance. Three key out-of-sample metrics are specified: (1) explained variance (EV) for evaluating the fit of the median regression line, (2) skewness for evaluating the shape of the distribution used to model the centiles, and (3) kurtosis for evaluating the shape of the distribution. The meaning of each metric is explained. The paper reports specific values: mean EV = 0.37 (SD = 0.10), skewness = -0.09 (SD = 0.12), and kurtosis = 0.42 (SD = 0.27). For the clinical analysis, additional statistical methods are described including t-tests with FDR correction and Mann-Whitney U tests. For the multimodal analysis, canonical correlation and permutation testing (1000 permutations) are used.
- **Supporting Text**: We assessed the quality of the normative modeling fit using three key out-of-sample metrics, namely explained variance (EV), evaluating the fit of the median regression line, in addition to skewness and kurtosis, which evaluate the shape of the distribution used to model the centiles. These metrics offer insight into how well the models capture the underlying distribution of the data across 48 white matter tracts. The mean (standard deviation) EV was 0.37 (0.10), indicating good fit across different models. Skewness, and kurtosis were respectively -0.09 (0.12) and 0.42 (0.27), which together indicate that the shape was also appropriate for the data. ... We next assessed the mean difference of the deviations between patients and controls for each tract using a t-test with false discovery rate (FDR) correction for multiple testing [26]. ... In order to assess generalizability, we then ran an additional 1000 permutations...
- **Location**: Cirstian2024.pdf.md, lines 155-162, 418-420, 447-450

### 6. Implementation of Internal Validation

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper implements thorough internal validation procedures. The normative models were evaluated on an independent test set (N=12,457) that was completely separate from the training set (N=12,457). Out-of-sample performance metrics (EV, skewness, kurtosis) were calculated specifically on the test set, not the training set. For the multimodal msCCA analysis, a robust cross-validation approach was employed with 1000 random splits into training (70%) and test (30%) sets, with the mean test canonical correlation reported. Measures to detect overfitting are evident, including the outlier exclusion and refitting procedure, and the use of regularization (sparsity parameters) in the msCCA analysis.
- **Supporting Text**: To prepare for the modelling stage, we began by splitting the dataset of subjects (N=24,915) into two equal groups: a test set (N=12,457) and a training set (N=12,457), stratified to ensure an even distribution of sex, race, dataset and site. ... We assessed the quality of the normative modeling fit using three key out-of-sample metrics, namely explained variance (EV), evaluating the fit of the median regression line, in addition to skewness and kurtosis, which evaluate the shape of the distribution used to model the centiles. ... In more detail, we performed 1000 random splits of the dataset into a training (70%) and test set (30%) and selected the most stable features, i.e. features that were selected in more than 80% of the splits. ... In order to assess generalizability, we then ran an additional 1000 permutations, where within each permutation, we computed the test canonical correlation averaged across 10 random splits of the data...
- **Location**: Cirstian2024.pdf.md, lines 372-374, 155-159, 443-450

### 7. External Data Validation

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper demonstrates external validation by applying the normative models trained on the large lifespan dataset to an independent external dataset: the HCP Early Psychosis (HCP-EP) dataset. This external dataset (N=173 with diffusion data, including 118 patients and 55 controls) was not used in the initial model training. The characteristics of the external validation dataset are described in detail, including demographic composition and clinical characteristics (supplementary Table S2). The transfer learning approach was used to account for site-specific effects while maintaining the core model structure. Performance and findings on the external dataset are extensively discussed, including the detection of heterogeneity in patient deviations and the multimodal brain-behavior associations.
- **Supporting Text**: Next, we applied the model to the Human Connectome Project Early Psychosis (HCP-EP) dataset [18] (Jacobs et al., 2024), which includes multi-shell diffusion data and T1-weighted structural MRI derived from participants diagnosed with early psychosis (n=118) and control participants (n=55). The dataset's demographic distribution comprises 37% females and 63% males, with a racial composition of 58% White, 28% Black, 9% Asian, 1% Mixed, and 3% Other. ... Next, we divided this dataset into a training set, consisting of half of the control participants, and combined it with the larger training set described above to retrain the normative models for each white matter tract. Using transfer learning, as in our previous work, we can efficiently adapt the models with only a small amount of calibration data to account for site-specific effect.
- **Location**: Cirstian2024.pdf.md, lines 383-408

### 8. Description of Dataset Characteristics

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper provides comprehensive descriptions of dataset characteristics. Sample sizes are clearly reported for all datasets: total N=24,915 for normative modeling (split equally into training and test), and N=173 (118 patients, 55 controls) for HCP-EP. Detailed demographic characteristics are provided in supplementary tables including age ranges, sex distribution, and racial composition for each of the five normative datasets (Table S1) and for the clinical dataset (Table S2). Clinical characteristics for the patient group are thoroughly described including PANSS scores (total, positive, negative, general), Marder factor scores (positive, negative, cognitive), and medication status (antipsychotic type and chlorpromazine equivalents). Missing data handling is mentioned through the outlier exclusion procedure. Balance between datasets is addressed through stratified sampling.
- **Supporting Text**: Total N=24,915, (N=12,457 for training and N=12,457 for test, stratified for sex, self-reported race, dataset and site). ... The dataset's demographic distribution comprises 37% females and 63% males, with a racial composition of 58% White, 28% Black, 9% Asian, 1% Mixed, and 3% Other. Participants with early psychosis were diagnosed using the Structured Clinical Interview for DSM-5 (SCID-5) (First et al., 2015) and symptoms assessed with the Positive and Negative Syndrome Scale (PANSS) [27], including negative symptoms (e.g., social withdrawal), positive symptoms (e.g., hallucinations), disorganisation, and general psychopathology. ... Medication status was also documented, including antipsychotic type and dosage converted to chlorpromazine equivalents.
- **Location**: Cirstian2024.pdf.md, lines 150-153, 386-395; media-5.docx.md (Table S1); media-6.docx.md (Table S2)

### 9. Performance Metrics and Statistical Uncertainty

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: The paper reports point estimates for key performance metrics clearly: mean EV = 0.37 (SD = 0.10), skewness = -0.09 (SD = 0.12), kurtosis = 0.42 (SD = 0.27), and test canonical correlation r = 0.25. P-values are reported for several analyses (e.g., p=0.003 for main msCCA component, p=0.0036 and p=0.0016 for Mann-Whitney U tests). Standard deviations are provided for the model performance metrics. However, confidence intervals or Bayesian credible intervals are not explicitly reported for the main performance metrics. For the multimodal analysis, statistical significance is assessed through permutation testing, which is appropriate, but formal confidence intervals are not provided. The use of FDR correction for multiple comparisons is mentioned and appropriate.
- **Supporting Text**: The mean (standard deviation) EV was 0.37 (0.10), indicating good fit across different models. Skewness, and kurtosis were respectively -0.09 (0.12) and 0.42 (0.27), which together indicate that the shape was also appropriate for the data. ... This analysis yielded a significant mean test canonical correlation of r=0.25 for the leading component (p=0.003 under permutation testing, see Methods for details). ... More specifically, individuals with schizophrenia had a greater proportion of extreme positive (Mann-Whitney U=1403.0, p=0.0036) and extreme negative (U=1517.0, p=0.0016) Z-scores relative to controls...
- **Location**: Cirstian2024.pdf.md, lines 159-162, 213-215, 199-202

### 10. Consideration for Reproducibility

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper demonstrates strong consideration for reproducibility. Software versions are specified: Python version 3.8 and PCN toolkit (PCNtoolkit on GitHub). The code availability is explicitly mentioned with a GitHub repository link (https://github.com/ramonacirstian/fa_normative_modeling). The paper states that all trained models are released freely to the community via open-source software platforms and the PCNportal [19]. Data availability is clearly described: UK Biobank data is available through access application, and the other datasets (HCP cohorts) are referenced with appropriate citations. The methods section provides sufficient detail to replicate the preprocessing and modeling approach. The paper explicitly states as one of its aims to 'release all models freely to the community.'
- **Supporting Text**: All statistical analyses were conducted using Python version 3.8, with the Predictive Clinical Neuroscience PCN toolkit (GitHub, PCNtoolkit). ... Finally, we provide these models to the field via our established no-code software platform [19] and via open-source software tools (https://github.com/ramonacirstian/fa_normative_modeling), so that others in the field can easily apply these models to their own data. ... The data used in the present study is part of the UK Biobank dataset which is available to be downloaded upon completing an access application. More information can be found on the dedicated webpage (UK Biobank, n.d.). The code used to process the data and train the normative models is also available online on GitHub. ... Finally, (iv) we release all models freely to the community via our existing open-source software platforms [19].
- **Location**: Cirstian2024.pdf.md, lines 381-382, 272-275, 589-591, 136-138

### 11. Interpretation Specific to Normative Modeling

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: The paper provides comprehensive interpretation specific to normative modeling. Individual-level deviation scores (z-scores) are clearly explained with specific thresholds for clinical significance (±2.6 corresponding to p=0.01). The paper explicitly discusses the advantages of normative modeling over traditional case-control approaches, noting that 'inter-individual variability masks group level effects' and that 'normative deviations frequently outperform raw measures in group difference testing, disease classification and treatment response prediction.' The clinical utility is demonstrated through the identification of heterogeneity in early psychosis patients and multimodal brain-symptom associations. Prospects for clinical application are realistically discussed, including potential for stratifying cohorts and developing personalized interventions, while acknowledging limitations such as age distribution gaps and dataset representativeness (WEIRD populations).
- **Supporting Text**: Next, we used these models to understand heterogeneity in white matter FA in psychosis. To achieve this, we applied these reference models to the HCP early psychosis (HCP-EP) dataset (N=173 with diffusion data -see supplementary table 2 for demographic information) in order to derive z-scores for each individual and tract. ... Note that this variability was evident in an absence of case-control effects, which indicates that inter-individual variability masks group level effects, which we also have observed in gray matter in autism [7]. ... Moreover, we have demonstrated that normative measures frequently outperform raw measures (e.g. cortical thickness in mm) in group difference testing, disease classification [11] and treatment response prediction [12]. ... These models not only serve as a benchmark for individual-level assessments but also offer valuable insights for precision medicine, facilitating more personalized interventions. ... Additionally, while our models effectively account for site-specific differences, variability due to demographic factors like socioeconomic background was not fully explored and should be considered in future normative modeling efforts.
- **Location**: Cirstian2024.pdf.md, lines 185-188, 249-252, 95-98, 330-333, 284-287

---

## Additional Comments

**Additional Comments**: This is a high-quality study that develops large-scale normative models for white matter fractional anisotropy across the lifespan using over 25,000 healthy individuals from five well-characterized cohorts. The study demonstrates several methodological strengths: (1) use of harmonized multi-shell diffusion data and preprocessing pipelines across datasets, (2) robust statistical modeling using warped Bayesian linear regression to handle non-Gaussian distributions and non-linear age effects, (3) explicit consideration of confounding variables including age, sex, race, and site effects, (4) thorough internal and external validation procedures, and (5) strong commitment to reproducibility through open sharing of code and trained models. The application to early psychosis effectively demonstrates the clinical utility of normative modeling for capturing individual heterogeneity that is missed by traditional case-control analyses. The multimodal analysis integrating diffusion, cortical thickness, and subcortical volumes with symptom data using msCCA represents an innovative approach. However, some limitations should be noted: (1) specific imaging acquisition parameters are not provided in the main text, (2) comprehensive inclusion/exclusion criteria for participant recruitment are not fully detailed, (3) training algorithm implementation details (optimization procedures, convergence criteria) are limited, and (4) confidence intervals for performance metrics are not explicitly reported. The paper acknowledges important limitations including age distribution gaps (5-8 years and >85 years), potential racial bias in WEIRD populations, and the imperfect use of self-reported race as a proxy for ethnicity. Overall, this study makes a significant contribution to the field of psychiatric neuroimaging by providing freely available normative reference models that can advance precision psychiatry approaches.
