# Quality Assessment Form

## Study Identification

- **Study ID**:　Elad 2021
- **Reference File Names**:-
- **Author, Journal, Year**:-
- **Title**:-
- **DOI**:-

---



## Assessment Items - Group A

### 1. Clarity of Research Objectives
- **Answer**:Yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:-
- **Supporting Text**:
Here, we leveraged a large harmonized diffusion MRI dataset from 512 healthy controls and 601 individuals
diagnosed with schizophrenia, to study whether normative modeling can improve subject-level predictions from a binary classifier
Here, we take a step towards
subject-level inferences by investigating the application of the normative
modeling approach on this dataset. We first generate a normative
model by estimating age- and sex-adjusted z-scores from standard
(FA) and advanced (Free-water) dMRI measures in 18 white matter
regions of interest (ROIs). Then, for every subject, the predictive performance
of the following features is calculated and compared with
the predictive performance of the raw dMRI values: (1) z-scores
obtained by applying the normative modeling approach on FA values;
(2) summary measures for the z-score distributions (Pasternak
et al., 2014); (3) z-scores and summary measures obtained by applying
the normative modeling approach on free-water imaging derived measures
(Pasternak, Sochen, Gur, Intrator, & Assaf, 2009) rather
than on FA.
- **Location**:see above




### 2. Clear Definition of Target Population
- **Answer**:Yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:-
- **Supporting Text**:
The dataset used in this study coincides with the dataset utilized in
the published work by (Cetin-Karayumak et al., 2020), which includes
601 individuals diagnosed with schizophrenia-spectrum disorder
across multiple illness stages (mean [SD] age, 31.46 [12.31] years;
380 [63.23%] male), and 512 healthy controls (mean [SD] age, 30.15
[14.26] years; 279 [54.49%] male). dMRI data
- **Location**:see above




### 3. Clarity of Inclusion and Exclusion Criteria
- **Answer**:partial
- **Confidence Rating**:medium
- **Negative Answer Category**:incomplete
- **Reason**:-
- 精神神経疾患の診断基準（DSM-5、ICD-11等）が明記されている
　⇒okとする
- 健常対照群の定義が明確である（該当する場合）
　⇒N/A
- 年齢、性別等の人口統計学的特徴の記述がある
　⇒最低限はあるが、下記の通りフルの情報は別途の引用論文にゆだねている

- **Supporting Text**:
The dataset used in this study coincides with the dataset utilized in
the published work by (Cetin-Karayumak et al., 2020), which includes
601 individuals diagnosed with schizophrenia-spectrum disorder
across multiple illness stages (mean [SD] age, 31.46 [12.31] years;
380 [63.23%] male), and 512 healthy controls (mean [SD] age, 30.15
[14.26] years; 279 [54.49%] male). dMRI data were collated from
13 different sites across a number of separate studies. The single shell

A complete account of demographics,
inclusion and exclusion criteria, acquisition protocols across
the 13 sites, preprocessing and harmonization procedures can be
found in Cetin-Karayumak et al. (2020). Following harmonization, all
data had isotropic resolution of 1.5 mm  1.5 mm  1.5 mm, with a
b-value of 1,000 s/mm2.
- **Location**:see above






### 4. Validity of Normative Modeling Outcome Measures
- **Answer**:Yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:-
- **Supporting Text**:
2.3 | Construction of a normative model
The normative model represents the distribution of the normative
range within each ROI in the healthy controls using the sample mean
and standard deviation. To control for confounding factors resulting
from age and sex differences, we represented the normative range in
each ROI by an age specific weighted mean, cmh, and standard deviation,
bσ2h, for each sex separately. To do so, we used the Nadaraya-
Watson (NW) estimator (Nadaraya, 1964; Watson, 1964) with a
Gaussian kernel,

2.4 | Calculation of deviation from the
normative model
The deviation of every individual diagnosed with schizophrenia from
the normative atlas, in each ROI, was captured by a z-score, calculated
using the NW estimators cmh, b σh
2 (see Equations (1a) and (1b)),
z x ð Þ¼
ymchðxÞ
bσhðxÞ
where x is the subject's age and y is the subject's dMRI value (e.g., the
mean FA value over the ROI). The z-scores were truncated to the
range ½10,10. The same procedure was also used to evaluate deviation
of each healthy control subject, but with a leave-one-out approach,
that is, we compared a given healthy control subject with a normative
model composed of all healthy control subjects, excluding the one being
evaluated. As a result, for each subject, and for each dMRI value (FA,
FAt, or FW), we obtained a vector with 18 z-scores (for 17 tracts +
white matter skeleton) representing deviation from the normative model.
Our approach is summarized in Figure 1, as well as in Algorithm 1
- **Location**:see above




### 5. Handling of Confounding Variables
- **Answer**:Yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:-
- **Supporting Text**:2.3 | Construction of a normative model
The normative model represents the distribution of the normative
range within each ROI in the healthy controls using the sample mean
and standard deviation. To control for confounding factors resulting
from age and sex differences, we represented the normative range in
each ROI by an age specific weighted mean, cmh, and standard deviation,
bσ2h
, for each sex separately. To do so, we used the Nadaraya-
Watson (NW) estimator (Nadaraya, 1964; Watson, 1964) with a
Gaussian kernel,
- **Location**:see above






### 6. Clarity of Data Sources
- **Answer**:Yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:-
- **Supporting Text**:
preprocessing and harmonization procedures
The dataset used in this study coincides with the dataset utilized in
the published work by (Cetin-Karayumak et al., 2020), which includes
601 individuals diagnosed with schizophrenia-spectrum disorder
across multiple illness stages (mean [SD] age, 31.46 [12.31] years;
380 [63.23%] male), and 512 healthy controls (mean [SD] age, 30.15
[14.26] years; 279 [54.49%] male). dMRI data were collated from
13 different sites across a number of separate studies. The single shell

dMRI data followed a standardized preprocessing protocol and were
harmonized across sites to remove site-related differences using retrospective
harmonization (
- **Location**:see above




### 7. Description of Image Acquisition Protocol
- **Answer**:Partial
- **Confidence Rating**:high
- **Negative Answer Category**:incomplete
- **Reason**:-
- 使用したMRIシーケンス（T1-weighted、DTI、fMRI等）の詳細が記載されている
　⇒ok
- 撮像パラメータ（TR、TE、フリップ角、解像度等）が明記されている
　⇒なし
- スキャナーの仕様（メーカー、磁場強度等）が記載されている
　⇒なし
- **Supporting Text**:
- **Location**:-
---







## Assessment Items - Group B

### 1. Details of Data Preprocessing
- **Answer**:Partial
- **Confidence Rating**:high
- **Negative Answer Category**:incomplete
- **Reason**:
- 使用した前処理ソフトウェア（FreeSurfer、FSL、SPM等）が明記されている
　⇒ok
- 前処理の各ステップ（頭蓋除去、正規化、セグメンテーション等）が詳述されている
　⇒なし；先行研究参照と
- 品質管理（Quality Control）の手順が記載されている
　⇒同上
- **Supporting Text**:
- **Location**:
A complete account of demographics,
inclusion and exclusion criteria, acquisition protocols across
the 13 sites, preprocessing and harmonization procedures can be
found in Cetin-Karayumak et al. (2020)

The harmonized data were fitted using FSL's DTIFIT (Behrens
et al., 2003) to the DTI model, from which FA was derived. The data
were also fitted to the two-compartments Free-water imaging model
(including a free-water compartment and a tissue compartment) using
a regularized nonlinear fit (Pasternak et al., 2009). In this process, the
fractional volume of the free-water compartment (FW) as well as
the FA of the tissue compartment (FAt) were estimated, as previous
work suggests that these may increase sensitivity to underlying
pathologies (Lyall et al., 2018; Pasternak et al., 2012; Pasternak,
Westin, Dahlben, Bouix, & Kubicki, 2015).






### 2. Clarity of Data Partitioning Methods

- **Answer**:yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:
- データ分割の比率（例：70:15:15）が明記されている
　⇒N/A
- 分割方法（ランダム、層化サンプリング等）が記述されている
　⇒N/A
- クロスバリデーションが使用された場合、その詳細（K-fold等）が記載されている
  ⇒ok
- データリーケージを防ぐ措置が講じられていることが確認できる
　⇒ok

- **Supporting Text**:
Prediction performance of the estimated models was validated
using a 10-fold cross-validation procedure. The data were partitioned
into 10 subsets—seven subsets comprised of 51 subjects
from the control group and 60 subjects from the schizophrenia
group, two subsets comprised of 52 subjects from the control group
and 60 subjects from the schizophrenia group, and one subset comprised
of 51 subjects from the control group and 61 subjects from
the schizophrenia group. In each cross-validation round, one of the
10 subsets served as the test set, while the other 9 subsets served
as the training set for the binary classifier. The average of the area
under the receiver operator curve (AUC), across the 10 test sets,
was the evaluation metric. We note that in each cross-validation, the
normative range, as well as the choice of a bandwidth, were estimated
using only the healthy control subjects that belonged to the
corresponding training set.This guaranteed that the classification
performance on the test sets was not biased by the estimated normative
model.

- **Location**:see above






### 3. Details of Normative Modeling Approach

- **Answer**: yesでよいか（ソフトがあるのかどうか不明のため）
- **Confidence Rating**:medium
- **Negative Answer Category**:-
- **Reason**:
- 統計モデルの種類（ガウシアンプロセス回帰、ベイジアンモデル、線形回帰等）が明記されている
　⇒ok
- モデルのハイパーパラメータや設定が記載されている
　⇒ok
- 使用したソフトウェアまたはツール（PCNtoolkit、normative-modeling等）が明記されている
　⇒なし
- **Supporting Text**:
2.3 | Construction of a normative model
The normative model represents the distribution of the normative
range within each ROI in the healthy controls using the sample mean
and standard deviation. To control for confounding factors resulting
from age and sex differences, we represented the normative range in
each ROI by an age specific weighted mean, cmh, and standard deviation,
bσ2h
, for each sex separately. To do so, we used the Nadaraya-
Watson (NW) estimator (Nadaraya, 1964; Watson, 1964) with a
Gaussian kernel,
- **Location**: see above




### 4. Details of Training Algorithm
- **Answer**:yes (厳しく言えばpartial)
- **Confidence Rating**:
- **Negative Answer Category**:
- **Reason**:
- 最適化アルゴリズム（勾配降下法、ADAM、L-BFGS等）が明記されている
- ハイパーパラメータの設定方法（グリッドサーチ、ベイジアン最適化等）が記述されている
- 収束判定基準や訓練終了条件（エポック数、損失関数の閾値等）が明記されている
- 正則化手法（L1/L2正則化等）の使用について記載されている
　⇒全ては記載ないが、基本は報告されている
- **Supporting Text**:
2.8 | Prediction models
We examined the diagnostic potential of the normative modeling
approach by using the z-score maps, as well as the z-score derived
measures, as inputs to a binary classifier, with the aim of classifying
individual subjects as either healthy controls or as diagnosed with
schizophrenia. In comparison, we also built binary classifiers with raw
dMRI values as the input. We chose logistic regression with ridge regularization
(McIlhagga, 2016) as the binary classifier of choice, thus
enforcing sparse and stable classification solutions. Explicitly, we
examined the following measures as inputs to the classifier:
(1) FA/FAt/FW raw values in each ROI separately, (2) FA/FAt/FW zscore
values in each ROI separately, (3) FA/FAt/FW z-scores in all
ROIs simultaneously (concatenated into one vector of length 18 for
each dMRI measure), (4) FAt and FW z-scores in all ROIs simultaneously
(concatenated into one vector of length 36 for each subject),
and (5) combination of summary measures and the aforementioned
inputs.
- **Location**: see above





### 5. Model Performance Evaluation Metrics
- **Answer**:yes
- **Confidence Rating**:high
- **Negative Answer Category**:-
- **Reason**:
- Evaluation on validation sets independent of training data was conducted
- Appropriate validation methods such as cross-validation were used
- Measures to detect and prevent overfitting were implemented
=>ok
- **Supporting Text**:

Prediction performance of the estimated models was validated
using a 10-fold cross-validation procedure. The data were partitioned
into 10 subsets—seven subsets comprised of 51 subjects
from the control group and 60 subjects from the schizophrenia
and 60 subjects from the schizophrenia group, and one subset comprised
of 51 subjects from the control group and 61 subjects from
the schizophrenia group. In each cross-validation round, one of the
10 subsets served as the test set, while the other 9 subsets served
as the training set for the binary classifier. The average of the area
under the receiver operator curve (AUC), across the 10 test sets,
was the evaluation metric. We note that in each cross-validation, the
normative range, as well as the choice of a bandwidth, were estimated
using only the healthy control subjects that belonged to the
corresponding training set. This guaranteed that the classification
performance on the test sets was not biased by the estimated normative
model.
- **Location**:see above








### 6. Implementation of Internal Validation
- **Answer**:yes
- **Confidence Rating**:high
- **Negative Answer Category**:
- **Reason**:
- Evaluation on validation sets independent of training data was conducted
- Appropriate validation methods such as cross-validation were used
- Measures to detect and prevent overfitting were implemented
=>ok
- **Supporting Text**:
Prediction performance of the estimated models was validated
using a 10-fold cross-validation procedure. The data were partitioned
into 10 subsets—seven subsets comprised of 51 subjects
from the control group and 60 subjects from the schizophrenia
group, two subsets comprised of 52 subjects from the control group
and 60 subjects from the schizophrenia group, and one subset comprised
of 51 subjects from the control group and 61 subjects from
the schizophrenia group. In each cross-validation round, one of the
10 subsets served as the test set, while the other 9 subsets served
as the training set for the binary classifier. The average of the area
under the receiver operator curve (AUC), across the 10 test sets,
was the evaluation metric. We note that in each cross-validation, the
normative range, as well as the choice of a bandwidth, were estimated
using only the healthy control subjects that belonged to the
corresponding training set. This guaranteed that the classification
performance on the test sets was not biased by the estimated normative
model.
- **Location**:above




### 7. External Data Validation
- **Answer**:no
- **Confidence Rating**:high
- **Negative Answer Category**:missing
- **Reason**:-
- **Supporting Text**:-
- **Location**:-






### 8. Description of Dataset Characteristics
- **Answer**:partial
- **Confidence Rating**:high
- **Negative Answer Category**:incomplete
- **Reason**:
- Sample sizes for each dataset are specified
- Demographic characteristics (age distribution, sex ratio, patient/control group breakdown, etc.) are described
- Clinical characteristics (symptom severity scores, illness duration, comorbidities, medication status, etc.) are described
- Comparison of characteristics between datasets and assessment of balance are conducted
- Handling of missing data is described
=>age, sexはあるが、詳細は先行研究参照とのこと。

- **Supporting Text**:
2.1 | Participants, imaging acquisition, image
preprocessing and harmonization procedures
The dataset used in this study coincides with the dataset utilized in
the published work by (Cetin-Karayumak et al., 2020), which includes
601 individuals diagnosed with schizophrenia-spectrum disorder
across multiple illness stages (mean [SD] age, 31.46 [12.31] years;
380 [63.23%] male), and 512 healthy controls (mean [SD] age, 30.15
[14.26] years; 279 [54.49%] male). dMRI data were collated from
13 different sites across a number of separate studies. The single shell
dMRI data followed a standardized preprocessing protocol and were
harmonized across sites to remove site-related differences using retrospective
harmonization (Karayumak et al., 2019; Ning et al., 2020).
In particular, Cetin-Karayumak et al. (2020) evaluated the performance
of the harmonization procedure by using unpaired t tests to
assess between-site differences and showed that statistical differences
between matched controls across sites were removed after harmonization
(see Figure S2 in Cetin-Karayumak et al., 2020). We note
that following the harmonization, site differences between subjects
diagnosed with schizophrenia are likely to occur, because of different
2.1 | Participants, imaging acquisition, image
preprocessing and harmonization procedures
The dataset used in this study coincides with the dataset utilized in
the published work by (Cetin-Karayumak et al., 2020), which includes
601 individuals diagnosed with schizophrenia-spectrum disorder
across multiple illness stages (mean [SD] age, 31.46 [12.31] years;
380 [63.23%] male), and 512 healthy controls (mean [SD] age, 30.15
[14.26] years; 279 [54.49%] male). dMRI data were collated from
13 different sites across a number of separate studies. The single shell
dMRI data followed a standardized preprocessing protocol and were
harmonized across sites to remove site-related differences using retrospective
harmonization (Karayumak et al., 2019; Ning et al., 2020).
In particular, Cetin-Karayumak et al. (2020) evaluated the performance
of the harmonization procedure by using unpaired t tests to
assess between-site differences and showed that statistical differences
between matched controls across sites were removed after harmonization
(see Figure S2 in Cetin-Karayumak et al., 2020). We note
that following the harmonization, site differences between subjects
diagnosed with schizophrenia are likely to occur, because of different
- **Location**:above






### 9. Performance Metrics and Statistical Uncertainty
- **Answer**: partial
- **Confidence Rating**:medium
- **Negative Answer Category**:incomplete
- **Reason**:
- Point estimates of key performance metrics are reported
  => AUC reported
- Confidence intervals or Bayesian credible intervals are reported  
  => NR
- P-values and statistical significance are appropriately reported
  => ok
- **Supporting Text**:
Prediction power for dMRI modalities. Area under the
receiver–operator curves (AUC), averaged over the cross-validations,
obtained when inputting the values in all ROIs simultaneously into the
classifier, for FA (green bars), FAt (red bars), FW (blue bars) and FAt
+FW (orange bars)
etc.
- **Location**:above





### 10. Consideration for Reproducibility

- **Answer**:partial
- **Confidence Rating**:high
- **Negative Answer Category**:incomplete
- **Reason**:
- Software versions used are specified
  => FSL reported, version not reported
- Availability of code and scripts is mentioned
  =>NR
- Sharing of trained models is mentioned
  =>NR
- Data availability (including limitations if any) is described
  =>ok (reported that data not shared)
- **Supporting Text**:
The harmonized data were fitted using FSL's DTIFIT (Behrens
et al., 2003) to the DTI model, from which FA was derived. The data
were also fitted to the two-compartments Free-water imaging model

DATA AVAILABILITY STATEMENT
Research data are not shared.
- **Location**:above





### 11. Interpretation Specific to Normative Modeling
- **Answer**:yes (厳しく言えばpartial)
- **Confidence Rating**:medium
- **Negative Answer Category**:fair enough~incomplete
- **Reason**:
- Meaning of individual-level deviation scores is clearly explained
  =>ok
- Clinical thresholds and decision criteria are discussed
  =>NR
- Differences and advantages compared to traditional case-control studies are discussed
  =>ok
- Prospects for clinical application are realistically discussed
  =>ok
- **Supporting Text**:
Our key finding is that the use of the complete
distribution of deviations from the normative range of each
individual as an input to a binary classifier improves the predictive
performance for all tested measures (FA, FAt, FW). Even though we
only reached a performance level indicative of an “acceptable discrimination”
(c.f., p. 162 in Hosmer Jr, Lemeshow, & Sturdivant, 2013), our
findings can serve as an early step in the development of a classification
scheme that involves schizophrenia and therefore aid in subjectlevel
classification.
We also find that extreme deviations from the normative model
are not found in a sufficient number of individuals diagnosed with
schizophrenia, and, accordingly, summary measures based on extreme
deviations are less efficient diagnostic measures. Indeed, the zdistribution
analysis identified that the range of z-scores that best...
- **Location**:above





---
## Additional Comments
**Additional Comments**:
