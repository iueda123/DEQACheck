# Quality Assessment Form

## Study Identification

- **Study ID**:
- **Reference File Names**:
- **Author, Journal, Year**:
- **Title**:
- **DOI**:

---

## Assessment Items - Group A

### 1. Clarity of Research Objectives

- **Answer**:Partial
- **Confidence Rating**:High
- **Negative Answer Category**:Missing
- **Reason**:No hypotheses or specific application purposes are stated.
- **Supporting Text**:Our training dataset was composed of 3,032 T1-weighted (T1w) MRI from seven open access databases (Table 1). This dataset was com- posed of 2,655 CN subjects (CN) and 377 patients with AD.

In this study, we compared the proposed multimodel HAVAs with normative model-based strategy (i.e., using only CN model), state-of- the-art deep learning methods and classical machine learning methods.

To do that, we first estimated the mean and the SD for each structures using all the CN subjects over the entire lifespan. Then, for a given structures, we applied the same z-score normalization to all the subjects (i.e., CN, AD, and MCI). Therefore, by using z-score of normalized volumes in % of ICV, we compensated for both inter-subject and inter-structure variabilities. In the following, all the volumes are expressed as z-scores of normalized volumes.
- **Location**:p.3271 (last 3 lines), p.3274(l.30-35),p. 3273 (l.13-20)

### 2. Clear Definition of Target Population

- **Answer**:Partial
- **Confidence Rating**:High
- **Negative Answer Category**:Missing
- **Reason**:While demographic characteristics are described, diagnostic criteria and definition of healthy subjects are not described.
- **Supporting Text**:Table 1&2
- **Location**:p. 3272

### 3. Clarity of Inclusion and Exclusion Criteria

- **Answer**:Partial
- **Confidence Rating**:Medium
- **Negative Answer Category**:Missing
- **Reason**:Some information regarding subject numbers are described, but not specifically stated, and other information such as inclusion and exclusion criteria are missing. 
- **Supporting Text**:For CN trajectories, we used the N = 2,655 subjects from 9 months to 94y of the training dataset as done in Coupé et al. (2017). For the AD trajectories, we used N = 2,251 subjects. As done in Coupé et al. (2019), we mixed AD patients with young CN. More precisely, we used 377 AD patients (from 55y to 96y) and all the CN younger than 55y available in the training dataset (i.e., 1874 subjects) assuming that neurodegeneration is a slow and progressive process.
- **Location**:p.3273(l.25-32)

### 4. Validity of Normative Modeling Outcome Measures

- **Answer**:Yes
- **Confidence Rating**:High
- **Negative Answer Category**:Not negative
- **Reason**:They are all clearly stated.
- **Supporting Text**:Our training dataset was composed of 3,032 T1-weighted (T1w) MRI from seven open access databases (Table 1).

To compensate for the inter-subject variability, we normalized all the structure volumes using the intracranial cavity volume (ICV) (Manjon et al., 2014). Moreover, in order to be able to combine several struc- tures with different sizes, we performed z-score normalization of all the normalized volumes (in percentage of ICV). To do that, we first estimated the mean and the SD for each structures using all the CN subjects over the entire lifespan. Then, for a given structures, we applied the same z-score normalization to all the subjects (i.e., CN, AD, and MCI). Therefore, by using z-score of normalized volumes in % of ICV, we compensated for both inter-subject and inter-structure variabilities. In the following, all the volumes are expressed as z-scores of normalized volumes.

Consequently, the proposed HAVAs (i.e., the SAD score) reflects the probability for the subject under study to be a patient with AD (or a pMCI subject). 
- **Location**:p.3271(last 3 lines), p.3273 (l.9-20), p.3272 (Table 1), p.3274 (l.30-35)

### 5. Handling of Confounding Variables

- **Answer**:No
- **Confidence Rating**:High
- **Negative Answer Category**:Missing
- **Reason**:No information regarding confounding variables are described.
- **Supporting Text**:NA
- **Location**:NA

### 6. Clarity of Data Sources

- **Answer**: Partial
- **Confidence Rating**:High
- **Negative Answer Category**:Missing
- **Reason**:While dataset names are described, time and location of data acquisition are not described.
- **Supporting Text**:Table 1&2
- **Location**:p.3272

### 7. Description of Image Acquisition Protocol

- **Answer**:Partial
- **Confidence Rating**:High
- **Negative Answer Category**:Missing
- **Reason**:Details of MRI sequences are provided, but imaging parameters and scanner specifications are not.
- **Supporting Text**:Our training dataset was composed of 3,032 T1-weighted (T1w) MRI from seven open access databases (Table 1). This dataset was composed of 2,655 CN subjects (CN) and 377 patients with AD.
- **Location**:p.3271(last 3 lines)

---

## Assessment Items - Group B

### 1. Details of Data Preprocessing

- **Answer**:Yes
- **Confidence Rating**:High
- **Negative Answer Category**:Not negative
- **Reason**:Data preprocessing procedures are all clearly stated.
- **Supporting Text**:All the considered images were processed using AssemblyNet soft- ware (https://github.com/volBrain/AssemblyNet) (Coupé et al., 2020). Based on collective artificial intelligence, AssemblyNet is able to produce fine-grained segmentation of the whole brain in 15 min. The AssemblyNet preprocessing pipeline was based on several steps: image denoising (Manjon, Coupé, Martí-Bonmatí, Collins, & Robles, 2010), inhomogeneity correction (Tustison et al., 2010), affine registration to the MNI space, automatic quality control (QC) (Denis de Senneville, Manjon, & Coupé, 2020), a second inhomogeneity correction in the MNI space (Ashburner & Friston, 2005) and a final intensity standardization step (Manjon & Coupé, 2016).

Finally, we performed a QC procedure to carefully select subjects included in our training dataset. For all the training subjects detected as failure by the automatic QC RegQCNet (Denis de Senneville et al., 2020), a visual assessment was performed by individually check- ing the input images and the segmentations produced by AssemblyNet using a 3D viewer. If the failure was confirmed by our expert, the subject was removed from training dataset.
- **Location**:p.3272(l.23-33), p.3273(l.1-7)

### 2. Clarity of Data Partitioning Methods

- **Answer**:Partial
- **Confidence Rating**:High
- **Negative Answer Category**:Missing
- **Reason**:While the datasets used for training, testing, and validation are described in tables, the proportion is not clearly stated. In addition, other information (e.g., measures to prevent data leakage) is missing.
- **Supporting Text**:To validate our model, we built a testing dataset based on two open access databases (AIBL and MIRIAD) to perform AD versus CN diag- nosis task. Therefore, we validated the generalization capacity of our method and its robustness to domain shift. In addition, we used sub- jects with mild cognitive impairment (MCI) from ADNI to estimate the capability of our models on prognosis task (Table 2). Consequently, we validated the generalization of our models to unseen related tasks. As in Wen et al. (2020), the MCI group was split into stable MCI (sMCI) over 3 years and progressive MCI (pMCI) who will convert to AD within 36 months following the baseline visit. Finally, we used the ClinicaDL software (https://github.com/aramis-lab/clinicadl) (Wen et al., 2020) to define the groups of AD and CN groups in AIBL, and the pMCI and sMCI groups in ADNI. Therefore, we used the same selection criteria.
- **Location**:p.3272 (Table 1&2), p.3273(l.4-17)

### 3. Details of Normative Modeling Approach

- **Answer**:No
- **Confidence Rating**:High
- **Negative Answer Category**:Missing
- **Reason**:Types of statistical models, model hyperparameters, settings, software or tools used are not described.
- **Supporting Text**:NA
- **Location**:NA

### 4. Details of Training Algorithm

- **Answer**:No
- **Confidence Rating**:High
- **Negative Answer Category**:Missing
- **Reason**:Details of training algorithm are not described.
- **Supporting Text**:NA
- **Location**:NA

### 5. Model Performance Evaluation Metrics

- **Answer**:Yes
- **Confidence Rating**:High
- **Negative Answer Category**:Not negative
- **Reason**:Model evaluation evaluation metrics are described.
- **Supporting Text**:As in Coupé et al. (2017, 2019), a polynomial model was considered as a potential candidate only when simultaneously F-statistic based on ANOVA (i.e., model vs. constant model) was found significant (p < .05) and when all its coefficients were also significant using T-statistic (p < .05). Afterwards, to select the most relevant model between these potential candidates, we used the Bayesian Information Criterion (Schwarz, 1978). In addition, we estimated the distance between both AD and CN models as the Euclidean distance between trajectories. Finally, we estimated the confidence interval for each model at 95% and the lifetime period for which the two models diverged significantly (i.e., when confidence intervals do not overlap).
- **Location**:p.3276 (Table 4), p. 3273 (l.40-49)

### 6. Implementation of Internal Validation
- **Answer**:No
- **Confidence Rating**:High
- **Negative Answer Category**:Missing
- **Reason**:Information of validation is provided, but other information is not provided.
- **Supporting Text**:The classification performance of the proposed method was validated using several metrics: balanced accuracy (BACC), specificity (SPE), sensibility (SEN) and area under the curve (AUC) based on HAVAs.
- **Location**:p.3274(l.32-35)


### 7. External Data Validation

- **Answer**:Yes
- **Confidence Rating**:High
- **Negative Answer Category**:Not negative
- **Reason**:They are all clearly described.
- **Supporting Text**:Finally, as a sensitivity analysis, in order to evaluate the consistency and the robustness of HAVAs to training domain, we performed an additional experiment using AIBL, OASIS, and MIRIAD databases in the training dataset while removing the AD and CN subjects of the ADNI database from training and used them as testing dataset. First, Table 7 shows the results obtained by HAVAs, amygdala, hippocampus, and inferior lateral ventricles. The obtained results are similar to the results previously obtained on AIBL. This result highlights the robustness of the proposed HAVAs strategy to training domain selection and the good generalization capability of our method.

During the validation of HAVAs on three external datasets, we showed that our strategy enables accurate detection of subject having AD, or MCI who will convert to AD in the next 3 years (i.e., pMCI). 

- **Location**:p.3272 (Table 2), p.3277 (Figure 3), p.3279 (l.41-43), p.3279 (l.1-10)


### 8. Description of Dataset Characteristics

- **Answer**:Partial
- **Confidence Rating**:High
- **Negative Answer Category**:Missing
- **Reason**:Sample sizes and demographic characteristics are described, but others (e.g., clinical characteristics) are not described.
- **Supporting Text**:NA
- **Location**:p.3272 (Table 1&2)

### 9. Performance Metrics and Statistical Uncertainty

- **Answer**:Partial
- **Confidence Rating**:High
- **Negative Answer Category**:Missing
- **Reason**:No point estimates and confidence intervals or Bayesian credible intervals are described.
- **Supporting Text**:NA
- **Location**:p.3276 (Table 4)

### 10. Consideration for Reproducibility

- **Answer**:Partial
- **Confidence Rating**:High
- **Negative Answer Category**:Missing
- **Reason**:Information regarding some software versions and availability of code and scripts are not described.
- **Supporting Text**:

A software package including AssemblyNet pipeline and HAVAs estimation will be made freely available as a downloadable Docker (https://github.com/volBrain/ AssemblyNetAD) as well as an online pipeline on the volBrain platform (http://www.volbrain.net/).

All the used MRI were from open access database and thus can be downloaded from the database provider website. AssemblyNet is freely available at https://github.com/volBrain/AssemblyNet. HAVAs will be made available through our online platform www.volbrain.net and as part of AssemblyNet-AD at https://github.com/volBrain/ AssemblyNetAD.

- **Location**:p.3279 (l.75-79), p.3280 (last 6 lines)

### 11. Interpretation Specific to Normative Modeling

- **Answer**:Partial
- **Confidence Rating**:High
- **Negative Answer Category**:Missing
- **Reason**:Prospects for clinical application are not discussed.
- **Supporting Text**:irst, HAVAs is conceptually very simple to understand since based on the distance to AD or CN trajectories. This aspect enables an easy interpretability of the results in terms of hippocampal- amygdalo atrophy and concomitant ventricular enlargement. While current DL methods failed to produce relevant explanation on the used features for their decision making (Bron et al., 2021), HAVAs is fully interpretable and thus is well-suited for clinical practice or pharmaceutical trials. 

Moreover, in order to provide easily interpretable nonbinary scores to the user about the probability of the subject's status (and to be able to estimate area under curve), we proposed new scores of being an AD patient (respectively a CN subject) based on the distance to the models. This score was built to ensure that when AD score is higher than 50%, the closest model is the AD model. Moreover, we ensured that an AD score of 50% (i.e., CN score of 50%) is obtained for an equal distance between both models. To define these scores, we used the following approach.
- **Location**:p.3274 (l.3-12), p.3279 (l.67-75)

---

## Additional Comments

**Additional Comments**:
