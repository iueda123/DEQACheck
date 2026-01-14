# Keywords for Normalization: Modeling Method

Version: 202601014

## 再検討の記録

122のNormative Modeling (NM) 研究における Modeling Methods について、
高松案として、以下の24個のキーワードで表現することが提案された。

- AAE: Adversarial Autoencoder
- AAE: Adversarial autoencoder
- BLR: Bayesian Linear Regression
- ConVAE: Convolutional Variational Autoencoder
- FUNCOIN: Functional Connectivity Integrative Normative Modelling
- GAM: Generalized Additive Model
- GAMLSS: Generalized Additive Models for Location, Scale and Shape
- GAMM: Generalized Additive Mixed Model
- GLM: Generalized Linear Model
- GPR: Gaussian Process Regression
- GPT: Generative Pre-trained Transformer
- HBR: Hierarchical Bayesian Regression
- LOESS: Locally Estimated Scatterplot Smoothing
- MEM: Mixed-Effects Model
- MFPR: Multivariate Fractional Polynomial Regression
- mmSIVAE: Multimodal Introspective Variational Autoencoder
- mmVAE: Multimodal Variational Autoencoder
- N3: Nearest Neighbor Normativity
- OLSR: Ordinary Least Squares Regression
- PBSI: Person-Based Similarity Index
- VAE: Variational Autoencoder
- VQ-VAE: Vector-Quantized Variational Autoencoder
- WBLR: Warped Bayesian Linear Regression

続いて、 上田が、これらに当てはまらないようなキーワードがないかを再考した。
方法としては、

`share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_codex_<ProcessedTime>.json`,
`share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_claude_<ProcessedTime>.json`,
`share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_gemini_<ProcessedTime>.json`bf:

というファイルの
"normative_modeling_part/nm2_modeling_method/answer"
要素の値をキーワードに置き換えて正規化しようとしたときに、
上記24キーワードでは足りないものがないかを考えた。
その結果以下が挙げられた。

- 対象: codex/claude/gemini 372件の `nm2_modeling_method/answer` を確認（ユニーク219）。24キーワードでは未カバーが62種類あった。
- 未カバー主なカテゴリ:
    - POLY: Polynomial regression（linear/quadratic/cubic; モデル選択含む）、二次多項式.
    - QUANTREG: Quantile regression（percentileモデリング; 5th/50th/95th など）.
    - AE: Autoencoder系（plain AE / denoising AE / semi-supervised AE）※既存VAE系とは別に素のAEが必要.
    - PCN-UNSPEC: PCNtoolkit/nispat/nomis等でアルゴリズム未特定（Not reported/NR）を示すタグ.
    - LMM: Linear Mixed Models（LMM）、voxel-wise LME など mixed-effects 系の総称タグ.
    - HBLM/HBGPM: Hierarchical Bayesian Linear/Gaussian Process Model（HBLM/HBGPM）など HBR 派生の明示.
- 補足: “Yes”“NR”“algorithm not specified” などは UNKNOWN/NR フラグが必要。欠損は3件（nm2_modeling_method/answer が空）。


また高松先生から追加提案で以下の10個のカテゴリの提案をもらった。

- Functional regression（MFPR）
- Linear regression/GLM（Linear regression, OLSR/OLS, GLM, polynomial regression）
- Additive/mixed-effects regression（GAM, GAMM, Linear MEM）
- Distributional/quantile regression（GAMLSS, quantile regression, quantile linear/polynomial regression）
- Nonparametric smoothing（LOESS, moving average curves, Nadaraya–Watson kernel regression）
- Gaussian process regression（GPR）
- Bayesian linear regression（BLR, B-spline BLR）
- Hierarchical Bayesian regression（HBR, Spectral Normative Modeling + HBR）
- Deep generative/representation learning（Autoencoder, deep autoencoder, AAE, VAE/ConvVAE, mmVAE/mmSIVAE, VQ-VAE）
- Other/unclear/not reported（NR, PBSI, FUNCOIN, N³, 他）

## Keywords for Normalization

以上のような検討を経て、以下のようなCategoryキーワードを用いた整理方法を考えている。
テーブルの下には各Major Categoryにどの文献が該当するかの簡易調査結果がまとめてある。

| Major Category      | Minor Category | Full Spelling                                             | Description            | Example                    |
|---------------------|----------------|-----------------------------------------------------------|------------------------|----------------------------|
| Bayesian Methods    | BLR            | bayesian linear regression                                | ベイズ線形回帰                | 正規-逆ガンマ事前のBLR              |
| Bayesian Methods    | GPR            | gaussian process regression                               | ガウス過程によるノンパラ回帰         | RBFカーネルのGPR                |
| Bayesian Methods    | HBR            | hierarchical bayesian regression                          | 階層ベイズ回帰                | サイト階層を持つHBR                |
| Bayesian Methods    | HBGPM          | hierarchical bayesian gaussian process model              | 階層ベイズGPR               | 集団+サイトの階層GPR               |
| Bayesian Methods    | HBLM           | hierarchical bayesian linear model                        | 階層ベイズ線形モデル             | マルチレベル線形モデル                |
| Bayesian Methods    | WBLR           | warped bayesian linear regression                         | 変数ワープを含むBLR            | ワーピング付きBLR                 |
| Deep Learning       | AAE            | adversarial autoencoder                                   | 逆学習を用いたAE              | AAEで潜在分布整形                 |
| Deep Learning       | AE             | autoencoder                                               | 素の自己符号化器               | denoising AE               |
| Deep Learning       | ConVAE         | convolutional variational autoencoder                     | 畳み込みVAE                | 3D-CNN VAE                 |
| Deep Learning       | GPT            | generative pre-trained transformer                        | 事前学習生成トランスフォーマ         | GPTで合成被験者生成                |
| Deep Learning       | mmSIVAE        | multimodal introspective VAE                              | 自己内省型マルチモーダルVAE        | MRI+遺伝のmmSIVAE             |
| Deep Learning       | mmVAE          | multimodal variational autoencoder                        | マルチモーダルVAE             | 画像+臨床のmmVAE                |
| Deep Learning       | VAE            | variational autoencoder                                   | 変分自己符号化器               | β-VAE                      |
| Deep Learning       | VQ-VAE         | vector-quantized variational autoencoder                  | ベクトル量子化VAE             | VQ-VAE-2                   |
| Domain-Specific     | FUNCOIN        | functional connectivity integrative normative modelling   | FC統合ノーマティブモデル          | FUNCOINでFC分布推定             |
| Domain-Specific     | PBSI           | person-based similarity index                             | 個人別類似性に基づくスコア          | PBSIで個別偏差を計算               |
| Mixed-Effects       | LMM            | linear mixed model                                        | 線形混合効果モデル              | 体積を従属変数としたLMM              |
| Mixed-Effects       | MEM            | mixed-effects model                                       | 混合効果モデルの総称             | ランダム切片のみのLME               |
| Nearest Neighbor    | N3             | nearest neighbor normativity                              | 最近傍を用いたノーマティブ推定        | k=50のN3                    |
| Nonparametric Reg   | GAM            | generalized additive model                                | スムーズ項を含む非線形回帰          | thin-plate splineのGAM      |
| Nonparametric Reg   | GAMLSS         | generalized additive models for location, scale and shape | 分布の位置・尺度・形状を同時モデリング    | μ/σ/ν/τをスプラインで推定           |
| Nonparametric Reg   | GAMM           | generalized additive mixed model                          | ランダム効果を含むGAM           | サイトをランダム効果にしたGAMM          |
| Nonparametric Reg   | LOESS          | locally estimated scatterplot smoothing                   | ローカル回帰スムージング           | span=0.75のLOESS            |
| Nonparametric Reg   | QUANTREG       | quantile regression                                       | 分位点回帰でパーセンタイルを推定       | 5th/50th/95th quantile     |
| Parametric Reg      | GLM            | generalized linear model                                  | 一般化線形モデルによる回帰          | GLM with Gaussian identity |
| Parametric Reg      | MFPR           | multivariate fractional polynomial regression             | 多変量分数多項式回帰             | FP2で年齢効果をモデル               |
| Parametric Reg      | OLSR           | ordinary least squares regression                         | 線形回帰の最小二乗推定            | ROIごとの線形回帰                 |
| Parametric Reg      | POLY           | polynomial regression                                     | 線形/二次/三次など多項式回帰        | 二次項までの多項式モデル               |
| Unspecified         | PCN-UNSPEC     | PCN toolkit unspecified                                   | PCN/nispat/nomisで手法未特定 | "PCNtoolkit pipeline"のみ    |
| Unspecified         | UNKNOWN/NR     | unknown / not reported                                    | 情報欠損・非情報的回答            | "Yes", 空欄, NR              |

## Major Category Descriptions

### Bayesian Methods

ベイズ推定の枠組みを用いたノーマティブモデリング手法群。事前分布と尤度から事後分布を導出し、パラメータの不確実性を確率分布として表現する。

**特徴:**
- パラメータ推定に不確実性を組み込むことが可能
- 少数サンプルでも過学習を抑制しやすい
- 事前知識（事前分布）を明示的にモデルに反映できる
- 予測区間の算出が理論的に自然

**カテゴリ内の手法:**
- **BLR (Bayesian Linear Regression)**: 線形回帰のベイズ版。パラメータに正規分布などの事前分布を設定し、事後分布を解析的またはサンプリングで推定。
- **WBLR (Warped Bayesian Linear Regression)**: BLRに変数変換（ワーピング）を追加し、非線形関係や非正規分布への対応を強化。
- **GPR (Gaussian Process Regression)**: 関数そのものに事前分布（ガウス過程）を置くノンパラメトリックベイズ手法。カーネル関数で柔軟な非線形性を表現。
- **HBR (Hierarchical Bayesian Regression)**: 階層構造を持つベイズモデル。複数サイトや集団間の変動を階層的にモデル化。
- **HBLM (Hierarchical Bayesian Linear Model)**: 線形モデルに階層構造を導入。サイト間変動と個人間変動を分離して推定可能。
- **HBGPM (Hierarchical Bayesian Gaussian Process Model)**: ガウス過程に階層構造を組み込んだモデル。複数集団やサイト間の共通性と個別性を同時にモデル化。

**Normative Modelingにおける利点:**
- 不確実性の定量化により、信頼区間や予測区間が自然に得られる
- 多施設研究において、サイト間の系統的差異を階層構造で吸収できる（HBR, HBLM, HBGPM）
- 小サンプルサイズでも事前分布により安定した推定が可能
- 外れ値検出において、予測分布の幅を考慮した偏差評価ができる

**代表的な研究:**
- **BLR**: Echave2024, Floris2024, Giacomel2025, Kasper2024, Savage2024, Shao2024（6研究）
- **GPR**: Bayer2022, Chan2025B, Fang2024, Floris2021, Geng2025, Han2023, Han2024A, Han2024B, Holz2023, Huo2024, Ilioska2024, Jia2024, Jia2025, Laidi2022, Liu2024, Parkes2021, Wolfers2018, Wolfers2020, Wolfers2021, Zabihi2019, Zabihi2020 など（31研究）
- **WBLR**: Bhome2024, Cirstian2024, Echave2024, Fraza2023, Haukvik2025, Janssen2024, Kasper2024, Meijer2024, RehakBuckova2025, Rutherford2022, Rutherford2023, Segal2025, Verdi2024 など（16研究）
- **HBR (HBLM/HBGPM含む)**: Berthet2025, Kia2022, Lawn2024, Loreto2024, Mansour2025, Segal2023, Sun2025, Verdi2023, VillalonReina2024, Worker2023, Wu2023, Wu2024（12研究）

### Deep Learning

ニューラルネットワークを用いた深層学習ベースのノーマティブモデリング手法群。高次元データの潜在表現を学習し、複雑な非線形関係をモデル化する。

**特徴:**
- 高次元データ（画像、マルチモーダルデータ）を直接扱える
- 特徴抽出と予測を同時に学習（end-to-end学習）
- 大規模データでの表現力が高い
- 潜在空間での分布モデリングが可能

**カテゴリ内の手法:**
- **AE (Autoencoder)**: エンコーダとデコーダで構成される基本的な自己符号化器。入力を潜在表現に圧縮・再構成。
- **AAE (Adversarial Autoencoder)**: AEに敵対的学習を導入。潜在空間の分布を事前に指定した分布（例：正規分布）に整形。
- **VAE (Variational Autoencoder)**: 変分推論の枠組みで潜在変数を確率分布としてモデル化。生成モデルとしても機能。
- **ConVAE (Convolutional VAE)**: VAEに畳み込み層を導入。3D脳画像などの空間構造を持つデータに適用。
- **VQ-VAE (Vector-Quantized VAE)**: 潜在空間をベクトル量子化し、離散的な潜在表現を学習。
- **mmVAE (Multimodal VAE)**: 複数モダリティ（MRI、臨床データなど）を統合的にモデル化するVAE。
- **mmSIVAE (Multimodal Introspective VAE)**: マルチモーダルVAEに自己内省機構を追加。モダリティ間の関係性をより詳細に学習。
- **GPT (Generative Pre-trained Transformer)**: 事前学習された大規模言語モデル。合成データ生成やデータ拡張に利用。

**Normative Modelingにおける利点:**
- 脳画像などの高次元データを直接モデリング可能
- 潜在空間での偏差評価により、解釈可能な異常検出が可能
- マルチモーダルデータの統合により、多面的な評価が可能（mmVAE, mmSIVAE）
- 大規模データから学習した汎用的な表現を活用できる

**代表的な研究:**
- Feng2024, Feng2025, Kumar2024, Kumar2025, Mendes2024, OliveiraSaraiva2023, Pinaya2019, Pinaya2021, Sampaio2025, Tong2024, Vieira2025（11研究）

### Domain-Specific

特定のドメインや応用領域に特化して設計されたノーマティブモデリング手法。

**特徴:**
- 領域固有の知識や構造を明示的にモデルに組み込む
- 汎用手法では捉えにくい特性を考慮
- 特定の研究目的に最適化された指標を提供
- 解釈可能性と実用性を重視

**カテゴリ内の手法:**
- **FUNCOIN (Functional Connectivity Integrative Normative Modelling)**: 機能的結合（FC）データに特化。脳領域間の結合パターンの分布を統合的にモデル化。
- **PBSI (Person-Based Similarity Index)**: 個人ベースの類似性指標。個々の被験者が集団内のどの部分集団に近いかを評価し、個別化された偏差を算出。

**Normative Modelingにおける利点:**
- 領域固有の複雑な構造（FCネットワークなど）を適切にモデル化
- 一般的な手法では見逃される特異的なパターンの検出
- 臨床的解釈や個別化医療への直接的な応用が容易
- ドメイン知識を活用した信頼性の高い評価

**代表的な研究と具体例:**
- **PBSI (Similarity-Based Methods)**: Baldwin2022, Joo2024（2研究）  
  例: Baldwin2022 はSA/CT/SV/ICVの153 ROIプロファイルをHC平均と相関させるPBSIを算出し、個人の逸脱度をZ化。Joo2024 では308 ROI（SA/GMV/CT/LGI）のPBSIで逸脱者を特定。
- **FUNCOIN**: FCデータを対象に、各被験者の結合パターン分布をモデリングし、ノード/ネットワークごとの逸脱を推定する手法。 
  特定研究（具体的にはKobbersmed2025）で補助的に用いられるが、本データ抽出では主要カテゴリとしての明示報告は限定的。

### Mixed-Effects

固定効果とランダム効果を組み合わせた混合効果モデル。個人内の繰り返し測定や階層的データ構造に対応。

**特徴:**
- 被験者間変動（ランダム効果）と集団レベルの効果（固定効果）を分離
- 縦断データや多施設データの階層構造に対応
- 個人差を考慮した予測が可能
- 欠測データに対してロバスト

**カテゴリ内の手法:**
- **MEM (Mixed-Effects Model)**: 混合効果モデルの総称。線形・非線形を問わず、ランダム効果を含むモデル全般。
- **LMM (Linear Mixed Model)**: 線形混合効果モデル。固定効果とランダム効果の両方を線形式でモデル化。

**Normative Modelingにおける利点:**
- 多施設研究において、サイト間変動をランダム効果として吸収
- 縦断データにおいて、個人内の時系列変化と集団レベルのトレンドを分離
- 被験者ごとの個別予測が可能（個別化ノーマティブモデル）
- 不均衡データや欠測に対して頑健な推定

**代表的な研究:**
- CardenasDeLaParra2019, DeMeo2019, Jalbrzikowski2019（3研究）

### Nearest Neighbor

近傍のデータポイントを利用して局所的にノーマティブ分布を推定する手法群。

**特徴:**
- ノンパラメトリックで分布の仮定が不要
- 局所的なデータ構造を柔軟に捉える
- 実装がシンプルで直感的
- 計算コストは高い場合があるが、局所適応性が高い

**カテゴリ内の手法:**
- **N3 (Nearest Neighbor Normativity)**: 最近傍法を用いたノーマティブ推定。各点に対してk個の近傍を用い、局所的な平均・分散を推定。

**Normative Modelingにおける利点:**
- 複雑な非線形分布や多峰性分布に対応可能
- 局所的な集団構造を考慮した偏差評価
- 事前の分布仮定が不要で、データ駆動的
- 稀な亜集団や特異なパターンにも柔軟に対応

**代表的な研究と具体例:**
- **Leenings2024**: Structural MRIの脳形態（加齢・疾患）に対し、Nearest Neighbor Normativity (N³) を主要手法として導入し、個別の正常性参照を近傍サンプルで構築。
- N3を主要手法として用いた研究は限定的で、他手法との併用が多い  
  例: ADNI系解析で、非線形正規化後のJacobianマップやVBM特徴に対し、同年齢近傍の被験者k人から局所平均・分散を推定し、個人のZスコアを計算するN3的な最近傍ノーマティブが報告されている。

### Nonparametric Reg

パラメトリックな関数形を仮定せず、データから柔軟に回帰関数を推定する手法群。

**特徴:**
- 事前の関数形の仮定が不要または最小限
- 非線形関係を柔軟にモデル化
- スムージングやカーネル、スプラインなどの技法を利用
- 過学習を避けるための正則化が重要

**カテゴリ内の手法:**
- **GAM (Generalized Additive Model)**: 一般化加法モデル。各説明変数の効果をスムーズ関数の和として表現。
- **GAMLSS (Generalized Additive Models for Location, Scale and Shape)**: GAMを拡張し、分布の位置・尺度・形状パラメータを同時にモデル化。
- **GAMM (Generalized Additive Mixed Model)**: GAMにランダム効果を追加。階層データやサイト効果を考慮。
- **LOESS (Locally Estimated Scatterplot Smoothing)**: 局所加重回帰。各点周辺のデータに重みをつけて局所的に回帰。
- **QUANTREG (Quantile Regression)**: 分位点回帰。条件付き中央値や任意のパーセンタイルを直接推定。

**Normative Modelingにおける利点:**
- 年齢効果など複雑な非線形トレンドを柔軟にモデル化
- 分布の歪みや裾の重さを考慮した偏差評価（GAMLSS, QUANTREG）
- 多様な分布形状に対応し、正規性の仮定に依存しない
- パーセンタイル曲線や成長曲線の推定に適している

**代表的な研究と具体例:**
- **GAMLSS**: Bedford2025, Bethlehem2021, Ebadi2024, GarciaSanMartin2025, Ge2024, Georgiadis2024, Huang2024, Lee2025, Leiberg2023, Little2025, Mao2025, Remiszewski2022, Tabbal2025, Wang2023, Young2024（15研究）  
  例: Bethlehem2021 は年齢を説明変数にし、Box-Cox t分布を仮定したGAMLSSで皮質厚のpercentile曲線を推定し、個人のzスコアを算出。
- **GAM/GAMM**: Chan2025A, Janssen2021, Leiberg2023, Little2024（4研究）  
  例: Janssen2021 は平滑スプラインを用いたGAMで皮質厚の年齢曲線を推定し、残差をHC平均・SDで標準化。
- **LOESS**: Jiang2024, Parkes2021（2研究）  
  例: Jiang2024 は局所多項式平滑（LOESS）で年齢効果を推定し、局所的に標準化した逸脱スコアを計算。
- **Quantile Regression**: DiBiase2022, Lin2024, Lv2021, Zheng2024（4研究）  
  例: Lin2024 はfMRI接続強度の年齢依存パーセンタイル（5,50,95%）をquantile regressionで推定し、被験者の位置づけを評価。

### Parametric Reg

特定のパラメトリックな関数形を仮定して回帰モデルを構築する手法群。

**特徴:**
- 明確な関数形（線形、多項式など）を仮定
- パラメータ推定が解析的または効率的に可能
- 解釈が容易でモデルが単純
- データ量が少なくても安定した推定が可能

**カテゴリ内の手法:**
- **OLSR (Ordinary Least Squares Regression)**: 最小二乗法による線形回帰。最も基本的な回帰手法。
- **GLM (Generalized Linear Model)**: 一般化線形モデル。指数型分布族とリンク関数により、多様な応答変数に対応。
- **POLY (Polynomial Regression)**: 多項式回帰。線形、二次、三次など多項式で非線形関係を近似。
- **MFPR (Multivariate Fractional Polynomial Regression)**: 分数多項式回帰。整数次数に限らない柔軟な多項式モデル。

**Normative Modelingにおける利点:**
- シンプルで計算効率が高い
- 少数のパラメータで解釈が容易
- 統計的推測（信頼区間、検定など）の理論的基盤が確立
- 小規模データでも安定した推定が可能

**代表的な研究と具体例:**
- **Polynomial Regression**: Bethlehem2020, Coupe2022, Ge2024, Gordaliza2024, Haas2024, Jiang2024, Jing2023, Lin2023, Romascano2024, Yang2025, Zhang2023（11研究）  
  例: Bethlehem2020 は年齢を多項式（2～4次）で展開した線形回帰を用いて、各皮質厚・面積のnormative曲線をフィッティングし、個人のZスコアを算出。
- **GLM, OLSR, MFPR**: 多くの研究で基本手法として使用されているが、主要手法としての明示的な記載は限定的
  - **GLMの１例**: Jing2023 ではタスクfMRIコントラストのZマップを GLM で生成した後、行列を分解しスコアに変換して normative モデルに投入。
  - **OLSRの１例**: Jiang2024 では単純なOLS回帰で年齢・性別を共変量にした脳形態指標の予測式を作り、残差をZ化して逸脱度を計算。
  - **MFPRの１例**: Romascano2024 では分数次数の多項式（例: 年齢^0.5 や 年齢^-1 を含む）を使って複数の形態指標の非線形年齢効果を柔軟に近似し、normative 分布を学習。

### Unspecified

手法が明示されていない、または情報が不足しているケース。

**特徴:**
- アルゴリズムの詳細が論文に記載されていない
- ツールキット名のみが記載され、具体的な手法が不明
- 回答が非情報的（"Yes", "NR"など）
- データ欠損

**カテゴリ内の手法:**
- **PCN-UNSPEC (PCN toolkit unspecified)**: PCNtoolkit/nispat/nomisなどのツールキットが使用されているが、具体的なアルゴリズムが特定できない。
- **UNKNOWN/NR (Unknown / Not Reported)**: 手法が全く記載されていない、または非情報的な回答（"Yes", 空欄, "NR"など）。

**Normative Modelingにおける注意点:**
- メタ分析や系統的レビューにおいて、手法の詳細を確認できない論文が存在
- 再現性の観点から問題がある
- データ抽出時には元論文の詳細確認が必要
- 今後のデータ整理では、可能な限り具体的な手法を特定する必要がある

**該当研究:**
- 122研究中、複数の研究において手法の詳細が不明確または記載不足
- 特に「PCNtoolkit」などのツールキット名のみが記載され、具体的なアルゴリズムが特定できないケースが散見される
