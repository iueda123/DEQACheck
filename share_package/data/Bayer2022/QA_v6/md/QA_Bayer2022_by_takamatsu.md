# Quality Assessment Form

## Study Identification

- **Study ID**:Bayer 2022
- **Reference File Names**:
- **Author, Journal, Year**:
- **Title**:Accommodating Site Variation In Neuroimaging Data Using Normative And Hierarchical Bayesian Models
- **DOI**:10.1101/2021.02.09.430363.

---

## Assessment Items - Group A

### 1. Clarity of Research Objectives

- **Answer**:YES
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:-
- **Supporting Text**:
1)In this study, we suggest accommodating for these site effects by including them as random effects in a hierarchical Bayesian model. We compared the performance of a linear and a non-linear hierarchical Bayesian model in modeling the effect of age on cortical thickness.
2)We show that the hierarchical Bayesian models including a site parameter perform better than existing methods for dealing with additive and multiplicative site effects, including ComBat and regressing out site. Subsequently, we validate the hierarchical Bayesian models in the autism sample of the ABIDE data set and test their ability to retain clinically useful variance while correcting for site effects. We discuss the normative hierarchical Bayesian methods with regard to their implications for neuroimaging data-sharing initiatives and their use as general technique to correct for site effects.
- **Location**:
1)Abstract, 
2)Introduction





### 2. Clear Definition of Target Population

- **Answer**:YES
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:-
- **Supporting Text**:
1)We used data of 570 healthy individuals from the ABIDE (autism brain imaging data exchange) data set in our experiments. In addition, we used data from individuals with autism to test whether our models are able to retain clinically useful information while removing site effects.
2)3.1.1 ABIDE data set—The ABIDE consortium (http://preprocessed-connectomes-project.org/abide/) was founded to facilitate research and collaboration on autism spectrum disorders by data aggregation and sharing. The consortium provides a publicly available structural magnetic resonance imaging (MRI) data set and corresponding phenotypic information of 539 individuals with autism spectrum disorder and 573 age-matched typical controls. For this study, we used 569 controls for development and performance testing of the models, out of which 470 were male
- **Location**:Figure 3, 1)Abstract, 2)Methods






### 3. Clarity of Inclusion and Exclusion Criteria

- **Answer**:YES
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:-
- **Supporting Text**:
To evaluate the performance of the models, we split the the healthy control data set into a training set (70% of data, n=389) and a test set (30% of data, n=166) using the R package caret and splitstackshape, while the distribution of age, sex and site was preserved between sets. Thus, training and test sets contained individuals from the same sites (“within-site-split”). For the clinical autism set, information from all individuals with autism that survived outlier correction (n=482) were used. Subsequently...

As a first step, the measures were scanned for outliers. The criterion applied to mark a value as an outlier was if it was above or beyond 2 inter quartile ranges from the mean of all values for that region and hemisphere. This quite liberal criterion was applied with the aim to detect not outliers in a mathematical sense (+/- 95 % confidence interval), but to detect impossible values. This leads to the removal of 1055 out of 162905 data points (0.006%) of all values.

- **Location**:3.1.1 ABIDE dataset, 3.3 splitting the ABIDE data set into..





### 4. Validity of Normative Modeling Outcome Measures
- **Answer**:YES
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:-
- **Supporting Text**:
For the current study, we focused on cortical thickness measures of the 34 bilateral regions (averaged between left and right hemisphere) of the Desikian Killiany atlas parcellation (Desikan et al., 2006) as a part of the FreeSurfer (Fischl et al., 2004) output and the average cortical thickness across all 34 regions. We chose to include only cortical thickness measures since they show a strong

As a first step, the measures were scanned for outliers. The criterion applied to mark a value as an outlier was if it was above or beyond 2 inter quartile ranges from the mean of all values for that region and hemisphere. This quite liberal criterion was applied with the aim to detect not outliers in a mathematical sense (+/- 95 % confidence interval), but to detect impossible values. This leads to the removal of 1055 out of 162905 data points (0.006%) of all values. After this step, the values of right and left hemisphere for each region were averaged (in case the value of one hemisphere was missing, the value of the remaining hemisphere was considered to be the average.) This procedure was preformed including all participants (control sample and autism sample, per region).
- **Location**:3.1.1 ABIDE data set, 3.2 Pre-processing of the ABIDE data set, 4.2 Predicting site from z-scores







### 5. Handling of Confounding Variables
- **Answer**:Yes
- **Confidence Rating**:High
- **Negative Answer Category**:-
- **Reason**:-
- **Supporting Text**:
3.8 Model specification
In this section, we show how normative models describing the association between age, sex, and cortical thickness measures can be modeled on data comprising site effects using a hierarchical Bayesian linear mixed model with a Gaussian Process term, which allows to model non-linear association between age and cortical thickness measures

3.8.2 Comparison models—We compare the hierarchical Bayesian attempt to normative modeling to commonly used harmonization techniques in which site is controlled
for...
- **Location**:see above





### 6. Clarity of Data Sources

- **Answer**:Yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:-
- **Supporting Text**:
3.1.1 ABIDE data set—The ABIDE consortium (http://preprocessed-connectomes-project.org/abide/) was founded to facilitate research and collaboration on autism spectrum disorders by data aggregation and sharing.
3.1.2 Site effects in the ABIDE data set—The ABIDE data set has been obtained by aggregating data from 20 independent samples collected at 17 different scanning locations (Di Martino et al., 2014).
- **Location**:see above






### 7. Description of Image Acquisition Protocol
- **Answer**:Yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:-
- **Supporting Text**:
3.1.2 Site effects in the ABIDE data set—The ABIDE data set has been obtained by aggregating data from 20 independent samples collected at 17 different scanning locations (Di Martino et al., 2014). Although all data has been collected with 3 Tesla scanners and preprocessed in a harmonized way (Craddock et al., 2013), sequence parameters for anatomical and functional data, as well as type of scanner varied across sites (Di Martino et al., 2014). In addition, sites differ in distribution of age and sex and in sample size. An overview of site-specific data is provided in Table 1 and in (Di Martino et al., 2014).
- **Location**:see above







## Assessment Items - Group B

### 1. Details of Data Preprocessing
- **Answer**:Yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:-
- **Supporting Text**:
3.2 Pre-processing of the ABIDE data set
Measures of cortical thickness were extracted from the arpac.stats files as part of the Freesurfer output of 1051 individuals in the ABIDE data set, separately for left and right hemisphere. As a first step, the measures were scanned for outliers. The criterion applied to mark a value as an outlier was if it was above or beyond 2 inter quartile ranges from the mean of all values for that region and hemisphere. This quite liberal criterion was applied with the aim to detect not outliers in a mathematical sense (+/- 95 % confidence interval), but to detect impossible values. This leads to the removal of 1055 out of 162905 data points (0.006%) of all values. After this step, the values of right and left hemisphere for each region were averaged (in case the value of one hemisphere was missing, the value of the remaining hemisphere was considered to be the average.) This procedure was preformed including all participants (control sample and autism sample, per region).
- **Location**:see above




### 2. Clarity of Data Partitioning Methods
- **Answer**:Yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:-
- **Supporting Text**:
3.3 Splitting the ABIDE data set into training and test sets
To evaluate the performance of the models, we split the the healthy control data set into a training set (70% of data, n=389) and a test set (30% of data, n=166) using the R package caret and splitstackshape, while the distribution of age, sex and site was preserved between sets. Thus, training and test sets contained individuals from the same sites (“within-site-split”).

5.Discussion The normative models were trained on a training set consisting of healthy individuals from the ABIDE data set (70% of the data from 20 different sites, within-site split, preserving the distribution of age and sex across training and test set) and we presented results from generalization to a test set (the remaining 30% of the data from the same sites).
- **Location**:see above




### 3. Details of Normative Modeling Approach
- **Answer**:Yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:-
- **Supporting Text**:
For these reasons, we suggest an alternative approach to deal with site effects in neuroimaging data. While we focus in particular on normative modeling, our approach can be also applied to other neuroimaging data analyses scenarios. We propose a hierarchical Bayesian approach in which we include site as a random effect in the model, avoiding the exclusion of meaningful variance correlated with site by predicting site effects as part of the model instead of removing them from the data.
This approach is similar to the approach by Kia et al. (2020), who used hierarchical Bayesian regression (HBR) in a similar way for multi-site normative modeling in a pooled neuroimaging data set, which contained 7499 participants who were scanned with 33 different scanners. Kia et al. (2020)’s estimate of site variation is based on a partial pooling approach, in which the variation between site-specific parameters is bound by a shared prior. The approach showed better performance when evaluated with respect to metrics accounting for the quality of the predictive mean and variance compared to a complete pooling of site parameters and to ComBat harmonization, and similar performance to a no-pooling approach, with the benefit of reduced risk of over-fitting due to the shared site variance. Moreover, Kia et al. (2020) also showed that the posterior distribution of site parameters from the training set can also be used as an informed prior to make predictions in an unseen, new test set, outperforming predictions from complete pooling and uninformed priors. The method was also able to preserve and parse heterogeneity between individuals with varying clinical diagnoses in associated brain regions of 1017 clinical patients of the study.
The present paper is a replication and extension of the approach by Kia et al. (2020).
+それに次ぐセクション
- **Location**:see above, 3.4~3.8






### 4. Details of Training Algorithm
- **Answer**:Yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:-
- **Supporting Text**:
3.7 Performance measures
3.7.1 Measures of model performance—Model performance is assessed using several common performance metrics. The Pearson’s correlation coefficient ρ indicates the linear association between true and predicted value of cortical thickness measures. However, correlations are not a sensitive error measure and cannot capture the mismatch between true and predicted value. Hence, we also calculate the standardized version of the root mean squared error (SRMSE) and the point-wise log-likelihood at each data point in the test set as a metric indicating deviance from the true value. However, these measures only take into account the estimate of the mean, and do not account for variations in the estimate of the variance. Thus, we also compute the proportion of variance explained (EV) by the predicted values and a standardized version of the log-loss (mean standardized log-loss, MSLL (Rasmussen and Williams, 2006)). The latter does not only take into account the variance of the test set, but also standardizes it by the variance of the training set, making a comparison between the models possible. This step is necessary as various methods of correcting for site might also have an impact on the variance remaining in the data.
3.7.2 Measures of goodness of the simulation in Stan—Parameters indicating the goodness of the model simulation process in Stan itself, like convergence, effective sample size, and trace plots can be found in the supplementary material.
- **Location**:see above, supplementary 0.2, 0.3 and beyond









### 5. Model Performance Evaluation Metrics
- **Answer**:Yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:-
- **Supporting Text**:
3.7 Performance measures
3.7.1 Measures of model performance—Model performance is assessed using several common performance metrics. The Pearson’s correlation coefficient ρ indicates the linear association between true and predicted value of cortical thickness measures. However, correlations are not a sensitive error measure and cannot capture the mismatch between true and predicted value. Hence, we also calculate the standardized version of the root mean squared error (SRMSE) and the point-wise log-likelihood at each data point in the test set as a metric indicating deviance from the true value. However, these measures only take into account the estimate of the mean, and do not account for variations in the estimate of the variance. Thus, we also compute the proportion of variance explained (EV) by the predicted values and a standardized version of the log-loss (mean standardized log-loss, MSLL (Rasmussen and Williams, 2006)). The latter does not only take into account the variance of the test set, but also standardizes it by the variance of the training set, making a comparison between the models possible. This step is necessary as various methods of correcting for site might also have an impact on the variance remaining in the data.
3.7.2 Measures of goodness of the simulation in Stan—Parameters indicating the goodness of the model simulation process in Stan itself, like convergence, effective sample size, and trace plots can be found in the supplementary material.
4.1.1 Mean standardized log-loss—To also account for the second order statistics of the posterior distributions created by each model, we calculated the mean standardized log-loss (MSLL).
+alpha
- **Location**:see above, results 4.1.1 and 4.1.2






### 6. Implementation of Internal Validation
- **Answer**:Yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:-
- **Supporting Text**:
3.3 Splitting the ABIDE data set into training and test sets
To evaluate the performance of the models, we split the the healthy control data set into a training set (70% of data, n=389) and a test set (30% of data, n=166) using the R package caret and splitstackshape, while the distribution of age, sex and site was preserved between sets. Thus, training and test sets contained individuals from the same sites (“within-site-split”). For the clinical autism set, information from all individuals with autism that survived outlier correction (n=482) were used. Subsequently, the control training and both the control and clinical autism test sets were standardized region-wise based on location and

0.4 Statistical comparison of measures of model perfor38
mance
39 All comparisons regarding measures of model performance were performed using
40 two-way ANOVAs including the factors model (HBLM, HBGPM, Combat Gam,
41 ComBat, ComBat without covariates, residuals, raw data) and set (train, test).
42 Post hoc tests were performed using Tukey tests and corrected for multiple
43 comparisons. Parametric tests such as ANOVA were deliberately chosen over
44 their non-parametric equivalents, since deviations from gaussianity were negligible
45 in the present data set and in the authors’ opinion, the substantial loss of power
46 with the choice of non-parametric tests does not scale with the potential threat
47 of violated modeling assumptions such as homoscedasticity and gaussianity.
- **Location**:see above, 3.3 splitting the ABIDE dataset...




### 7. External Data Validation
- **Answer**:No
- **Confidence Rating**:high
- **Negative Answer Category**:missing
- **Reason**:どれもないですかね。
- 訓練に使用されていない独立したデータセットでの評価が実施されている
- 外部検証データセットの特徴が記載されている
- 外部検証での性能が内部検証と比較・検討されている
- **Supporting Text**:
- **Location**:-



### 8. Description of Dataset Characteristics
- **Answer**:Partial
- **Confidence Rating**:high
- **Negative Answer Category**:incomplete
- **Reason**:
- 各データセットのサンプルサイズが明記されている
　⇒ok
- 人口統計学的特徴（年齢分布、性別比、疾患群・健常群の内訳等）が記載されている
　⇒部分的にはあるも、split後の詳細はない
- 臨床的特徴（症状重症度スコア、罹病期間、併存疾患、服薬状況等）が記載されている
　⇒ほぼなし
- データセット間の特徴比較と均等性の検討が実施されている
　⇒なし
- 欠損データの扱いについて記載されている
　⇒ok

- **Supporting Text**:
3.1.1 ABIDE data set—The ABIDE consortium (http://preprocessed-connectomes-project.org/abide/) was founded to facilitate research and collaboration on autism spectrum disorders by data aggregation and sharing. The consortium provides a publicly available structural magnetic resonance imaging (MRI) data set and corresponding phenotypic information of 539 individuals with autism spectrum disorder and 573 age-matched typical controls. For this study, we used 569 controls for development and performance testing of the models, out of which 470 were male. 

3.1.2 Site effects in the ABIDE data set—The ABIDE data set has been obtained by aggregating data from 20 independent samples collected at 17 different scanning locations (Di Martino et al., 2014).

3.3 Splitting the ABIDE data set into training and test sets
To evaluate the performance of the models, we split the the healthy control data set into a training set (70% of data, n=389) and a test set (30% of data, n=166) using the R package caret and splitstackshape, while the distribution of age, sex and site was preserved between sets. Thus, training and test sets contained individuals from the same sites (“within-site-split”). For the clinical autism set, information from all individuals with autism that survived outlier correction (n=482) were used

0.1 Missing values and sample sizes per model
3 Despite the aim of the study to run all seven models in this study on the
4 same data set, we were faced with the challenge that differing requirements
5 for each model with respect to missing values made an adjustment of sample
6 sizes per model necessary. Combat [Fortin2017, Fortin2018] accepts missing
7 values, and could be thus run on the full data set. In contrast, ComBat Gam
8 [pomponio2020] does not. Thus, for ComBat Gam all subjects with a missing
9 value in any of the 35 regions had to be excluded, which lead to a sample size
10 reduction from 391 to 370 individuals for the training set, and from 168 to 156
11 individuals for the healthy test set. The normative modeling process is performed
12 region wise and independently, thus only the subjects that contained missing
13 subjects for that particular region were deleted.

- **Location**:see above






### 9. Performance Metrics and Statistical Uncertainty
- **Answer**:Partial
- **Confidence Rating**:medium
- **Negative Answer Category**:incomplete
- **Reason**:-
- 主要な性能指標の点推定値が報告されている
　⇒ok
- 信頼区間またはベイジアン信頼区間が報告されている
　⇒なし…rangeだの様々に報告はあるが区間ではない
- p値や統計的有意性が適切に報告されている
  ⇒ok

- **Supporting Text**:
4.1 Comparing hierarchical Bayesian models and comparison models
Both the HBLM and the HBGPM outperformed all other comparison models with respect to all performance measures considered in this study. In detail, the HBLM and the HBGPM showed higher average values of the Pearson’s correlation coefficient ρ (Table 2), lower average SRMSEs (Table 3), smaller average LL (Table 4) and higher average proportions of EV (Table 5) than all comparison models (p < 0.001 for all comparisons). For none of these comparisons did the non-linear HBGPM outperform the linear HBLM. In addition to the mean comparisons reported in Table 2 - 5, the distribution of all performance measures across all 34 regions and for average cortical thickness across the entire cortex per model can be found in Fig. 4. A detailed comparison of all models with respect to to ρ, SRMSE, EV and LL can be found in the supplementary material.

4.1.1 Mean standardized log-loss
4.1.2 Predictive Variance

- **Location**:see above




### 10. Consideration for Reproducibility

- **Answer**:Partial
- **Confidence Rating**:medium
- **Negative Answer Category**: incomplete
- **Reason**:
- 使用したソフトウェアのバージョンが明記されている
　⇒Stan, Freesurfer, Rが使用されたとはあるが、バージョン情報の記載がない。
- コードやスクリプトの利用可能性について言及されている
　⇒ok
- 訓練済みモデルの共有について言及されている
  ⇒なし
- データの利用可能性（制限がある場合はその旨）について記述されている
　⇒ok

- **Supporting Text**:
3.2 Pre-processing of the ABIDE data set
Measures of cortical thickness were extracted from the arpac.stats files as part of the Freesurfer output of 1051 individuals in the ABIDE data set, separately for left and right hemisphere.

are first harmonized by three different common models of site harmonization, and then a simple Bayesian linear algorithm, with an additive term for age and sex, but without site as a predictor is used to make predictions in Stan (Stan Development Team, 2020b).

R (R Core Team, 2020) was used for preprocessing of all data and to create the data set
where site was regressed out, and for preprocessing the data with ComBat (Johnson et al.,
2007; Fortin et al., 2017).

The Stan code for the HBLM, the HBGPM and the simple Bayesian linear model without site as predictor can be found at https://github.com/likeajumprope/Bayesian_normative_models.

3.1.2 Site effects in the ABIDE data set—The ABIDE data set has been obtained by aggregating data from 20 independent samples collected at 17 different scanning locations (Di Martino et al., 2014).

- **Location**:see above





### 11. Interpretation Specific to Normative Modeling

- **Answer**:yes
- **Confidence Rating**:medium
- **Negative Answer Category**:-
- **Reason**:-
- 個人レベルの逸脱スコアの意味が明確に説明されている
  ⇒ok
- 臨床的閾値や判定基準について議論されている
  ⇒ok
- 従来の症例対照研究との違いや利点が議論されている
  ⇒ok
- 臨床応用への展望が現実的に議論されている
  ⇒ok
- **Supporting Text**:
While the approach we propose may have broad utility, our approach is particularly well suited to normative modelling where the primary interest is in accurate modelling of inter-subject variation and statistical quantification of deviations from a reference model.

Case-control inferences can be clinically meaningful under some circumstances when the group mean is a good representation of each individual in the group

The average percentage of atypical z-scores in our study for the autism sample was 7.8% for the HBGPM and 7.2% for the HBLM, compared to 5.7% for the HBGPM and 5.5% for the HBLM for the control test set. These findings illustrate, on the one hand side, that the models were both able to produce the per-definition expected amount of 5% of atypical z-scores in a healthy control test set, thus validating the model.

- **Location**:see above

---






## Additional Comments

**Additional Comments**:
none