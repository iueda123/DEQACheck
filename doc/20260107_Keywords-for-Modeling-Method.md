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

- GLM-family: GLM, OLSR
- Additive models: GAM, GAMM, GAMLSS, LOESS, LMS
- Polynomial: POLY (linear/quadratic/cubic; model selection)
- Quantile: QUANTREG (percentile-based NM)
- Fractional polynomial: MFPR
- Gaussian process: GPR
- Bayesian linear: BLR, WBLR
- Hierarchical Bayesian: HBR, HBLM, HBGPM
- Mixed-effects: MEM, LMM (voxel/ROI)
- Nearest neighbor: N3, NNA (density-based), EXP-WEIBULL (NN→likelihood)
- Kernel regression: KERNEL-NW (Nadaraya–Watson)
- Moving average: MOV-AVG
- Tolerance intervals: TOL-INT (nonparametric)
- Z-score baseline: ZSCORE (mean/SD only)
- Autoencoder family: AE (plain/denoising/semi-supervised), AAE, VAE, ConVAE, VQ-VAE, mmVAE, mmSIVAE
- Deep generative: GPT (if used), FUNCOIN
- Similarity-based: PBSI
- Vector factorization: NMF (if present), PCA (if appears)
- Unknown/unspecified: PCN-UNSPEC (PCNtoolkit/nispat/nomis unspecified), UNKNOWN/NR (Yes/NR/missing)
