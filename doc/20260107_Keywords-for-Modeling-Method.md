# Keywords for Response Variable

## Question

`share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_codex_<ProcessedTime>.json`,
`share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_claude_<ProcessedTime>.json`,
`share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_gemini_<ProcessedTime>.json`bf:
というファイルを横断的に読み解いて教えてください。
"normative_modeling_part/nm2_modeling_method/answer" にある情報を
キーワード表現に置き換えて正規化したいと考えています。
現在、以下の24個のキーワードで表現することを考えていますが、
これらに当てはまらないようなキーワードがあれば教えてください。

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
- LMS: Lambda-Mu-Sigma method
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

## Answer

- 対象: codex/claude/gemini 372件の `nm2_modeling_method/answer` を確認（ユニーク219）。24キーワードでは未カバーが62種類あった。
- 未カバー主なカテゴリ:
    - Polynomial regression（linear/quadratic/cubic; モデル選択含む）、二次多項式.
    - Quantile regression（percentileモデリング; 5th/50th/95th など）.
    - Nonparametric tolerance intervals（両側許容区間）.
    - Simple Z-score基準（平均・分散でz化のみ、詳細手法なし）.
    - Moving average（移動平均で平均・分散推定）.
    - Kernel regression（Nadaraya–Watson）.
    - Autoencoder系（plain AE / denoising AE / semi-supervised AE）※既存VAE系とは別に素のAEが必要.
    - PCNtoolkit/nispat/nomis等でアルゴリズム未特定（Not reported/NR）を示すタグ.
    - Linear Mixed Models（LMM）、voxel-wise LME など mixed-effects 系の総称タグ.
    - Hierarchical Bayesian Linear/Gaussian Process Model（HBLM/HBGPM）など HBR 派生の明示.
    - Nearest Neighbor algorithm（density estimation）＋ Exponentiated Weibull likelihood変換.
- 補足: “Yes”“NR”“algorithm not specified” などは UNKNOWN/NR フラグが必要。欠損は3件（nm2_modeling_method/answer が空）。

## Keywords for Normalization

| Keyword | Fullspel | Description | Example |
| --- | --- | --- | --- |
| GLM | generalized linear model | 一般化線形モデルによる回帰 | GLM with Gaussian identity |
| OLSR | ordinary least squares regression | 線形回帰の最小二乗推定 | ROIごとの線形回帰 |
| GAM | generalized additive model | スムーズ項を含む非線形回帰 | thin-plate splineのGAM |
| GAMM | generalized additive mixed model | ランダム効果を含むGAM | サイトをランダム効果にしたGAMM |
| GAMLSS | generalized additive models for location, scale and shape | 分布の位置・尺度・形状を同時モデリング | μ/σ/ν/τをスプラインで推定 |
| LOESS | locally estimated scatterplot smoothing | ローカル回帰スムージング | span=0.75のLOESS |
| LMS | lambda-mu-sigma method | LMS法によるパーセンタイル曲線推定 | WHO成長曲線のLMS |
| POLY | polynomial regression | 線形/二次/三次など多項式回帰 | 二次項までの多項式モデル |
| QUANTREG | quantile regression | 分位点回帰でパーセンタイルを推定 | 5th/50th/95th quantile |
| MFPR | multivariate fractional polynomial regression | 多変量分数多項式回帰 | FP2で年齢効果をモデル |
| GPR | gaussian process regression | ガウス過程によるノンパラ回帰 | RBFカーネルのGPR |
| BLR | bayesian linear regression | ベイズ線形回帰 | 正規-逆ガンマ事前のBLR |
| WBLR | warped bayesian linear regression | 変数ワープを含むBLR | ワーピング付きBLR |
| HBR | hierarchical bayesian regression | 階層ベイズ回帰 | サイト階層を持つHBR |
| HBLM | hierarchical bayesian linear model | 階層ベイズ線形モデル | マルチレベル線形モデル |
| HBGPM | hierarchical bayesian gaussian process model | 階層ベイズGPR | 集団+サイトの階層GPR |
| MEM | mixed-effects model | 混合効果モデルの総称 | ランダム切片のみのLME |
| LMM | linear mixed model | 線形混合効果モデル | 体積を従属変数としたLMM |
| N3 | nearest neighbor normativity | 最近傍を用いたノーマティブ推定 | k=50のN3 |
| NNA | nearest neighbor (density-based) | 密度推定型の最近傍法 | kNNで局所密度を推定 |
| EXP-WEIBULL | exponentiated weibull likelihood | NN推定後のWeibull尤度変換 | kNN後にEW分布で外れ値評価 |
| KERNEL-NW | kernel regression (nadaraya-watson) | NW推定によるカーネル回帰 | ガウスカーネルのNW回帰 |
| MOV-AVG | moving average | 移動平均で平均・分散を推定 | 5歳幅の移動平均 |
| TOL-INT | tolerance interval (nonparametric) | 非パラ許容区間推定 | 両側95%許容区間 |
| ZSCORE | z-score baseline | 平均と分散のみでz化 | μ/σを使った単純zスコア |
| AE | autoencoder | 素の自己符号化器 | denoising AE |
| AAE | adversarial autoencoder | 逆学習を用いたAE | AAEで潜在分布整形 |
| VAE | variational autoencoder | 変分自己符号化器 | β-VAE |
| ConVAE | convolutional variational autoencoder | 畳み込みVAE | 3D-CNN VAE |
| VQ-VAE | vector-quantized variational autoencoder | ベクトル量子化VAE | VQ-VAE-2 |
| mmVAE | multimodal variational autoencoder | マルチモーダルVAE | 画像+臨床のmmVAE |
| mmSIVAE | multimodal introspective VAE | 自己内省型マルチモーダルVAE | MRI+遺伝のmmSIVAE |
| GPT | generative pre-trained transformer | 事前学習生成トランスフォーマ | GPTで合成被験者生成 |
| FUNCOIN | functional connectivity integrative normative modelling | FC統合ノーマティブモデル | FUNCOINでFC分布推定 |
| PBSI | person-based similarity index | 個人別類似性に基づくスコア | PBSIで個別偏差を計算 |
| NMF | nonnegative matrix factorization | 非負値行列因子分解 | コンポーネント荷重のNMF |
| PCA | principal component analysis | 主成分分析 | PCA得点を回帰 |
| PCN-UNSPEC | PCN toolkit unspecified | PCN/nispat/nomisで手法未特定 | “PCNtoolkit pipeline”のみ |
| UNKNOWN/NR | unknown / not reported | 情報欠損・非情報的回答 | “Yes”, 空欄, NR |
