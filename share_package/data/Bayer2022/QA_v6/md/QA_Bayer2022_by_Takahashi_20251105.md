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

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: 研究の目的は、マルチサイト・ニューロイメージングデータにおけるサイト効果に対応するため、サイトをランダム効果として含む階層ベイズモデル（HBLMとHBGPM）を提案し、従来のハーモナイゼーション手法（ComBat等）と比較検証することであり、明確に記述されている。
- **Supporting Text**: "In this study, we suggest accommodating for these site effects by including them as random effects in a hierarchical Bayesian model. We compared the performance of a linear and a non-linear hierarchical Bayesian model..."
- **Location**: Bayer2022.pdf, Page 1, Abstract

### 2. Clear Definition of Target Population

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: PICOのPopulation（精神・神経疾患）として、自閉症（Autism Spectrum Disorder）患者と健常対照者（Healthy Individuals）が明確に対象とされている。
- **Supporting Text**: "We used data of 570 healthy individuals from the ABIDE (autism brain imaging data exchange) data set... In addition, we used data from individuals with autism to test whether our models are able to retain clinically useful information..." (Page 1, Abstract). "For this study, we used 569 controls... we applied the hierarchical Bayesian models to 482 individuals with autism..." (Page 6, Methods 3.1.1).
- **Location**: Bayer2022.pdf, Page 1 (Abstract), Page 6 (Methods 3.1.1)

### 3. Clarity of Inclusion and Exclusion Criteria

- **Answer**: Partial
- **Confidence Rating**: Medium
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: この研究独自の平均から2IQR以上離れた値の除去は記載されているが、参加者レベルでの詳細な適格基準・除外基準（例：併存疾患、IQ、画像品質の最低基準など）は、使用したABIDEデータセットの原典（Di Martino et al., 2014）に依存している。フローチャートは提供されていない。
- **Supporting Text**: "The criterion applied to mark a value as an outlier was if it was above or beyond 2 inter quartile ranges from the mean... This leads to the removal of 1055 out of 162905 data points..."

- **Location**: Bayer2022.pdf, Page 7 (Methods 3.2)

### 4. Validity of Normative Modeling Outcome Measures

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: 使用した脳計測値（皮質厚、Desikan-Killianyアトラスの34領域平均）と、ノルマティブモデリングのアウトカムである逸脱スコア（z-score）の計算方法（Equation 10）が明確に定義されている。
- **Supporting Text**: "For the current study, we focused on cortical thickness measures of the 34 bilateral regions (averaged between left and right hemisphere) of the Desikian Killiany atlas..." (Page 6, Methods 3.1.1). "Finally, each individual’s z-score of deviation can be calculated via: [Equation 10]" (Page 11, Methods 3.8.1).
- **Location**: Bayer2022.pdf, Page 6 (Methods 3.1.1), Page 11 (Methods 3.8.1)

### 5. Handling of Confounding Variables

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: この研究の主題は交絡変数（特にサイト効果）の扱いで、年齢（age）、性別（sex）、サイト（site）をモデルに組み込む方法（HBLM, HBGPM）と、除去する方法（ComBat, regressing out）が詳細に比較検討されている。
- **Supporting Text**: "The ability of the model to deal with site effects is obtained by introducing a random effect for site s = 1, 2, . . . ,q..." (Page 10, Methods 3.8).
- **Location**: Bayer2022.pdf, Page 10 (Methods 3.8)

### 6. Clarity of Data Sources

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: 使用したデータソースがオープンデータセット「ABIDE (autism brain imaging data exchange)」であることが明確に記載されている。
- **Supporting Text**: "We used data of 570 healthy individuals from the ABIDE (autism brain imaging data exchange) data set..." (Page 1, Abstract). "The ABIDE consortium (http://preprocessed-connectomes-project.org/abide/)..." (Page 6, Methods 3.1.1).
- **Location**: Bayer2022.pdf, Page 1 (Abstract), Page 6 (Methods 3.1.1)

### 7. Description of Image Acquisition Protocol

- **Answer**: Partial
- **Confidence Rating**: High
- **Negative Answer Category**: Unclear/Incomplete
- **Reason**: ABIDEがマルチサイト研究であり、サイト毎にスキャナや撮像パラメータが異なることが明記されている。詳細はTable 1および原典（Di Martino et al., 2014）を参照するよう誘導されており、本論文単体では全サイトの詳細なプロトコルは網羅されていないが、Table 1に概要が提供されている。
- **Supporting Text**: "Although all data has been collected with 3 Tesla scanners... sequence parameters for anatomical and functional data, as well as type of scanner varied across sites (Di Martino et al., 2014)... An overview of site-specific data is provided in Table 1..."
- **Location**: Bayer2022.pdf, Page 7 (Methods 3.1.2), Page 34-35 (Table 1)

---

## Assessment Items - Group B

### 1. Details of Data Preprocessing

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: ABIDEのPreprocessed Connectomes Projectによって標準化されたプロトコルで処理済みのデータを使用したことが明記されている。使用ソフトウェア（FreeSurfer）、パイプライン、アトラス（Desikan-Kiliany）が特定されている。
- **Supporting Text**: "The data were processed using a standardized protocol (Craddock et al., 2013) of the FreeSurfer standard pipeline (Desikan-Kiliany Atlas) as part of the Preprocessed Connectomes Project (Craddock et al., 2013)..."
- **Location**: Bayer2022.pdf, Page 6 (Methods 3.1.1)

### 2. Clarity of Data Partitioning Methods

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: 健常者データセットを訓練用（70%）とテスト用（30%）に分割したことが明記されている。分割方法は、年齢、性別、サイトの分布を保持する層化サンプリング（Rパッケージ使用）であり、明確である。
- **Supporting Text**: "To evaluate the performance of the models, we split the the healthy control data set into a training set (70% of data, n=389) and a test set (30% of data, n=166) using the R package caret and splitstackshape, while the distribution of age, sex and site was preserved between sets."
- **Location**: Bayer2022.pdf, Page 7 (Methods 3.3)

### 3. Details of Normative Modeling Approach

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: 提案手法である2つの統計モデル（HBLM: 階層ベイズ線形モデル、HBGPM: 階層ベイズガウス過程モデル）が、数式（Eq. 3-6）と共に詳細に説明されている。使用ソフトウェア（Stan）も明記されている。
- **Supporting Text**: "Hierarchical Bayesian Linear Model, HBLM" and "Hierarchical Bayesian Gaussian Process Model, HBGPM" (Page 8, Methods 3.4). "We use Stan (Carpenter et al., 2017; Stan Development Team, 2020b) to estimate all free parameters..." (Page 11, Methods 3.8).
- **Location**: Bayer2022.pdf, Page 8-11 (Methods 3.4, 3.8)

### 4. Details of Training Algorithm

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: ベイズ推定のためのサンプリングアルゴリズム（NUTS: No-U-Turn Sampler）、イテレーション総数（4000）、ウォームアップ期間（2000）が明記されている。
- **Supporting Text**: "It uses the No-U-Turn Sampler (NUTS)... to generate representative samples..." (Page 12, Methods 3.8.3). "For the present project, each model run entailed a Monte-Carlo sampling process of 4000 iterations in Stan, of which 2000 were disregarded as warm up." (Page 1, Supplementary 0.2).

- **Location**: Bayer2022.pdf (Page 12), Bayer2022_sup.pdf (Page 1)

### 5. Model Performance Evaluation Metrics

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: モデル性能を評価するために使用した複数の指標（Pearson’s correlation coefficient ρ, SRMSE, EV, MSLL）が明確に定義され、その選択理由も説明されている。
- **Supporting Text**: "Model performance is assessed using several common performance metrics. The Pearson’s correlation coefficient ρ... standardized version of the root mean squared error (SRMSE)... proportion of variance explained (EV)... and a standardized version of the log-loss (mean standardized log-loss, MSLL)..."
- **Location**: Bayer2022.pdf, Page 9 (Methods 3.7.1)

### 6. Implementation of Internal Validation

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: データを訓練セット（70%）とテストセット（30%）に分割し、訓練セットでモデルを構築し、テストセットで性能を評価しており、適切な内部検証（Internal Validation）が実施されている。
- **Supporting Text**: (Methods 3.3, Page 7) および (Results 4.1, Page 13).
- **Location**: Bayer2022.pdf, Page 7 (Methods 3.3), Page 13 (Results 4.1)

### 7. External Data Validation

- **Answer**: No
- **Confidence Rating**: High
- **Negative Answer Category**: Missing
- **Reason**: 検証はABIDEデータセットを分割（within-site split）して行われたものであり、ABIDEとは異なる独立した外部データセット（External datasets）を用いた検証は実施されていない。この限界はDiscussionでも述べられている。
- **Supporting Text**: "Secondly, the between-site split and the model at its current state only allow generalizations to a test set which includes individuals from the same sites as the training set..."

- **Location**: Bayer2022.pdf, Page 19 (Discussion)

### 8. Description of Dataset Characteristics

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: 訓練/テスト/自閉症サンプルのサンプルサイズ、年齢、性別の分布がFigure 2に示されている。また、サイトごとの詳細な情報（スキャナ、参加人数、年齢範囲など）がTable 1にまとめられている。

- **Supporting Text**: "An overview of site-specific data is provided in Table 1..." (Page 7, Methods 3.1.2). "An overview of the distribution of age and sex for the training and test sets... can be found in Fig. 2" (Page 7, Methods 3.3).
- **Location**: Bayer2022.pdf, Page 7 (Methods 3.1.2, 3.3), Page 26 (Fig 2), Page 34-35 (Table 1)

### 9. Performance Metrics and Statistical Uncertainty

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: 性能指標の点推定値（Mean）と統計的不確実性（STD: 標準偏差）が、Table 2（Correlation）, Table 3（SRMSE）, Table 5（EV）に明確に報告されている。
- **Supporting Text**: "Table 2... Mean Correlation (STD)..." (Page 36). "Table 3... Mean SRMSE (STD)..." (Page 37).
- **Location**: Bayer2022.pdf, Page 36-39 (Tables 2-5)

### 10. Consideration for Reproducibility

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: 再現性の確保に関して配慮されている。使用データ（ABIDE）、使用ソフトウェア（Stan, R）、およびモデルのStanコードがGitHubリポジトリで公開されている。
- **Supporting Text**: "We declare that all software, data and code used for this paper is publicly available." (Page 20, Data Availability Statement). "The Stan code for the HBLM, HBGPM and simple Bayesian linear model are available at: https://github.com/likeajumprope/ Bayesian_normative_models." (Page 20).
- **Location**: Bayer2022.pdf, Page 20 (Data Availability Statement)

### 11. Interpretation Specific to Normative Modeling

- **Answer**: Yes
- **Confidence Rating**: High
- **Negative Answer Category**: Not Negative
- **Reason**: 提案手法（HBLM, HBGPM）が、サイト効果を除去しつつも、臨床的に意味のある逸脱（自閉症サンプルの非典型的なz-score）を保持できるかどうかが議論されており、ノルマティブモデリング特有の解釈が適切に行われている。

- **Supporting Text**: "...the difference in atypical z-scores between test healthy controls and the autism sample showed that... the models are yet able to preserve clinically significant variation while removing site related variation."
- **Location**: Bayer2022.pdf, Page 19 (Discussion)

---

## Additional Comments

**Additional Comments**:
