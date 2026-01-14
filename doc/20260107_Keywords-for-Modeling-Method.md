# Keywords for Response Variable

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
    - TOL-INT: Tolerance interval (nonparametric)（両側許容区間）.
    - ZSCORE: Simple Z-score基準（平均・分散でz化のみ、詳細手法なし）.
    - MOV-AVG: Moving average（移動平均で平均・分散推定）.
    - KERNEL-NW: Kernel regression（Nadaraya–Watson）.
    - AE: Autoencoder系（plain AE / denoising AE / semi-supervised AE）※既存VAE系とは別に素のAEが必要.
    - PCN-UNSPEC: PCNtoolkit/nispat/nomis等でアルゴリズム未特定（Not reported/NR）を示すタグ.
    - LMM: Linear Mixed Models（LMM）、voxel-wise LME など mixed-effects 系の総称タグ.
    - HBLM/HBGPM: Hierarchical Bayesian Linear/Gaussian Process Model（HBLM/HBGPM）など HBR 派生の明示.
    - NNA/EXP-WEIBULL: Nearest Neighbor algorithm（density estimation）＋ Exponentiated Weibull likelihood変換.
- 補足: “Yes”“NR”“algorithm not specified” などは UNKNOWN/NR フラグが必要。欠損は3件（nm2_modeling_method/answer が空）。

## Keywords for Normalization

| Minor Category | Full Spelling                                             | Description            | Example                    |
|----------------|-----------------------------------------------------------|------------------------|----------------------------|
| AAE            | adversarial autoencoder                                   | 逆学習を用いたAE              | AAEで潜在分布整形                 |
| AE             | autoencoder                                               | 素の自己符号化器               | denoising AE               |
| BLR            | bayesian linear regression                                | ベイズ線形回帰                | 正規-逆ガンマ事前のBLR              |
| ConVAE         | convolutional variational autoencoder                     | 畳み込みVAE                | 3D-CNN VAE                 |
| EXP-WEIBULL    | exponentiated weibull likelihood                          | NN推定後のWeibull尤度変換      | kNN後にEW分布で外れ値評価            |
| FUNCOIN        | functional connectivity integrative normative modelling   | FC統合ノーマティブモデル          | FUNCOINでFC分布推定             |
| GAM            | generalized additive model                                | スムーズ項を含む非線形回帰          | thin-plate splineのGAM      |
| GAMLSS         | generalized additive models for location, scale and shape | 分布の位置・尺度・形状を同時モデリング    | μ/σ/ν/τをスプラインで推定           |
| GAMM           | generalized additive mixed model                          | ランダム効果を含むGAM           | サイトをランダム効果にしたGAMM          |
| GLM            | generalized linear model                                  | 一般化線形モデルによる回帰          | GLM with Gaussian identity |
| GPR            | gaussian process regression                               | ガウス過程によるノンパラ回帰         | RBFカーネルのGPR                |
| GPT            | generative pre-trained transformer                        | 事前学習生成トランスフォーマ         | GPTで合成被験者生成                |
| HBR            | hierarchical bayesian regression                          | 階層ベイズ回帰                | サイト階層を持つHBR                |
| HBGPM          | hierarchical bayesian gaussian process model              | 階層ベイズGPR               | 集団+サイトの階層GPR               |
| HBLM           | hierarchical bayesian linear model                        | 階層ベイズ線形モデル             | マルチレベル線形モデル                |
| KERNEL-NW      | kernel regression (nadaraya-watson)                       | NW推定によるカーネル回帰          | ガウスカーネルのNW回帰               |
| LMM            | linear mixed model                                        | 線形混合効果モデル              | 体積を従属変数としたLMM              |
| LMS            | lambda-mu-sigma method                                    | LMS法によるパーセンタイル曲線推定     | WHO成長曲線のLMS                |
| LOESS          | locally estimated scatterplot smoothing                   | ローカル回帰スムージング           | span=0.75のLOESS            |
| MEM            | mixed-effects model                                       | 混合効果モデルの総称             | ランダム切片のみのLME               |
| MFPR           | multivariate fractional polynomial regression             | 多変量分数多項式回帰             | FP2で年齢効果をモデル               |
| mmSIVAE        | multimodal introspective VAE                              | 自己内省型マルチモーダルVAE        | MRI+遺伝のmmSIVAE             |
| mmVAE          | multimodal variational autoencoder                        | マルチモーダルVAE             | 画像+臨床のmmVAE                |
| MOV-AVG        | moving average                                            | 移動平均で平均・分散を推定          | 5歳幅の移動平均                   |
| N3             | nearest neighbor normativity                              | 最近傍を用いたノーマティブ推定        | k=50のN3                    |
| NMF            | nonnegative matrix factorization                          | 非負値行列因子分解              | コンポーネント荷重のNMF              |
| NNA            | nearest neighbor (density-based)                          | 密度推定型の最近傍法             | kNNで局所密度を推定                |
| NTI            | nonparametric tolerance intervals                         | 両側許容区間                 |                            | 
| OLSR           | ordinary least squares regression                         | 線形回帰の最小二乗推定            | ROIごとの線形回帰                 |
| PBSI           | person-based similarity index                             | 個人別類似性に基づくスコア          | PBSIで個別偏差を計算               |
| PCA            | principal component analysis                              | 主成分分析                  | PCA得点を回帰                   |
| PCN-UNSPEC     | PCN toolkit unspecified                                   | PCN/nispat/nomisで手法未特定 | "PCNtoolkit pipeline"のみ    |
| POLY           | polynomial regression                                     | 線形/二次/三次など多項式回帰        | 二次項までの多項式モデル               |
| QUANTREG       | quantile regression                                       | 分位点回帰でパーセンタイルを推定       | 5th/50th/95th quantile     |
| TOL-INT        | tolerance interval (nonparametric)                        | 非パラ許容区間推定              | 両側95%許容区間                  |
| UNKNOWN/NR     | unknown / not reported                                    | 情報欠損・非情報的回答            | "Yes", 空欄, NR              |
| VAE            | variational autoencoder                                   | 変分自己符号化器               | β-VAE                      |
| VQ-VAE         | vector-quantized variational autoencoder                  | ベクトル量子化VAE             | VQ-VAE-2                   |
| WBLR           | warped bayesian linear regression                         | 変数ワープを含むBLR            | ワーピング付きBLR                 |
| ZSCORE         | z-score baseline                                          | 平均と分散のみでz化             | μ/σを使った単純zスコア              |
