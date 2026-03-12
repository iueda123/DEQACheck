こちらありがとうございます。ひとまずのお返事なのですが、

１）Preprocessing
思いのほか、いやな確認作業だったと記憶しています。他の変数と比較して確信度は高くありません。
preprocessの語で検索をかけて見つかっても、解釈が容易でないものが多かったような気がします。
AIをかけての確認ステップもしていただけるとよいと感じます。

２）Response variables
・解剖学的には、皮質 vs 皮質下 vs 全脳
・測定対象は、厚み vs 表面積 vs 体積
・粒度は、vertex/voxel-wise vs ROI-based regional vs global summary
sMRIが大半の結果となったレビューなのでsMRIでは緻密にだけどもまとまりある形を目指し、
sMRI以外のモダリティは大雑把なくくりのみ提示するのがTable 1としてはキレイでしょうか。
例えば、Response Variableの下に1.Outcome type、2.Spatial unitの中カテゴリを設置。
前者は、sMRI vs non-sMRIで回答方法を分けて、後者は全modalityで共通使用。
Table 1はシンプルにして、詳細(AI抽出結果)はsupplementaryに回す。

１．Outcome type
sMRIの場合
・Cortical thickness
・Cortical surface-based geometry
・Cortical volume
・Subcortical and cerebellar volumes
・Global brain tissue volumes
・Deformation-based morphometry
・Multivariate morphometric measures
sMRI以外の場合
・Functional connectivity（fMRI）
・Functional activation（fMRI）
・White-matter microstructure（dMRI）
・Electrophysiology（EEG/MEG）
・Molecular/metabolic imaging（PET）
・Multivariate/deviation-based measures（typicality, deviation score等）

2. Spatial unit of analysis
   ・Vertex-wise/voxel-wise
   ・ROI-based regional
   ・Network-level
   ・Along-tract
   ・Global summary
   ・Multiscale（global+regional+voxel）

３）Modeling methods
こちらも同様の、Table 1用のくくりが必要ということですよね。
論文で書かれていた、まんまの手法・表現はsupplementaryに回しつつ、
Table1のくくり方としてはどうなんでしょうか。10個だと多いでしょうか。
・Linear regression/GLM（Linear regression, OLSR/OLS, GLM, polynomial regression）
・Additive/mixed-effects regression（GAM, GAMM, Linear MEM）
・Distributional/quantile regression（GAMLSS, LMS, quantile regression, quantile linear/polynomial regression, tolerance
intervals）
・Nonparametric smoothing（LOESS, moving average curves, Nadaraya–Watson kernel regression）
・Gaussian process regression（GPR）
・Bayesian linear regression（BLR, B-spline BLR）
・Hierarchical Bayesian regression（HBR, Spectral Normative Modeling + HBR）
・Functional regression（MFPR）
・Deep generative/representation learning（Autoencoder, deep autoencoder, AAE, VAE/ConvVAE, mmVAE/mmSIVAE, VQ-VAE）
・Other/unclear/not reported（NR, PBSI, FUNCOIN, N³, 他）

高松
