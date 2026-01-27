# 神経画像におけるノルマティブモデリングのデータ抽出ガイダンス（システマティックレビュー）

-------------------

## プロンプト概要
あなたは神経画像のノルマティブモデリング研究からデータを抽出する熟練したレビューアです。このデータ抽出は、精神・神経疾患に対する神経画像の方法論的検討と応用に関するシステマティックレビューの一環として行われます。

-------------------

## 本レビューの目的

本システマティックレビューでは、神経画像および神経生理検査（例: MRI, PET, EEG, MEG）を用いたノルマティブモデリング研究を評価します。以下の3点を扱います:

  * 1. **モダリティ**: ノルマティブモデリングで用いられている計測技術は何か、それぞれどの程度使われているか。MRIのような一般的モダリティでは、どのシーケンス（例: T-weighted）が最も頻用されるか。
  * 2. **方法論**: モダリティをまたいでノルマティブモデリング研究はどのように設計・検証されているか。サンプルサイズ、共変量、前処理、統計モデル、ハーモナイゼーション手法、検証戦略などを抽出する。
  * 3. **臨床的スコープ**: どの精神・神経疾患がノルマティブモデリングで検討されているか、個人レベルの逸脱パターンや臨床的ユーティリティは何が報告されているか。

-------------------

## ファイル構成

```
<timestamp>/                          # カレントディレクトリ（ここで作業）
├── DE_Guide_v12_a-study.md           # このファイル（ガイド）
├── DE_<AuthorYear>_by_<agent>_*.json # 結果ファイル（ここに出力）
└── study_1/                          # 研究の論文・資料
    ├── <論文>.pdf.md
    └── ...
```

## ソース資料の場所

- 対象となる論文はカレントディレクトリ下にある `./study_1/` フォルダ内にあります。
- 結果JSONファイルはカレントディレクトリ直下に出力してください（`study_1/` の中ではありません）。
- 次のセクションにある抽出依頼にある情報を抽出してください。必要に応じてサブフォルダも参照してください。

-------------------

## 一般的な抽出ルール

- スコープと優先順位
    - 本文と表を優先。なければ補足資料を参照。それでもなければ明示的に引用された外部ソースを使用し、Locationに出典を記載。

- 記載の慣習
    - 複数値はセミコロンで区切る

- 一貫性と書式
    - 項目名は指定どおり厳守。新しいフィールドを作らない。
    - ADCSL_Style でも必ず5要素（answer, detail, confidence_rating, supporting_text, location）。
    - Supporting Text は簡潔な原文引用。言い換え禁止。
    - Location はファイル名と特定できる位置（セクション/行/ページ等）。

------------

## 抽出結果のスタイル

### A_Style Format

1. **Answer**: The extracted information according to the extraction criteria.

### ADCSL_Style Format

When an item requires ADCSL_Style, provide the following structured information:

1. **Answer**: The extracted information according to the extraction criteria (typically a categorical or short textual answer).

2. **Detail**: Concise, structured details required by the item.

3. **Confidence Rating**: Rate your confidence as "High", "Medium", or "Low".

4. **Supporting Text**: Direct quotes from the source materials that support the answer (concise).

5. **Location**: Where the supporting text was found.
    - Format: "FileName: Section / Subsection / Location"

-------------------

## 抽出依頼内容

### SI. Study Identification Part

#### SI-1. Study ID
* 各論文の一意ID。著者名+年。
* 抽出スタイル: A_Style
* 例:
    * Rutherford2022

#### SI-2. Reference File Names
* データ抽出時に参照したファイル名
* 抽出スタイル: A_Style
* 例:
    * Rutherford2022.pdf.md; Rutherford2022_sup.pdf.md

#### SI-3. Author, Journal, and Year
* 抽出スタイル: A_Style
* データ型: string
* 例:
    * Rutherford et al., Communications Biology, 2022

#### SI-4. Title
* 論文タイトル（原文どおり）
* 抽出スタイル: A_Style

### NM2. Normative Modeling 2nd Part

#### NM2-1. Modeling Methods

* Extraction Criteria: Specify the statistical/machine learning algorithm(s) used for normative modeling. 
* 以下の Major Category, Minor Category から該当するものを選んで回答してください（複数回答可）。

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

* Extraction Result Style: ADCSL_Style
* "answer" example:

```json
"answer": [
    {"major-category": "Bayesian Methods", "minor-category": "BLR"},
    {"major-category": "Mixed-Effects", "minor-category": "LMM"}
]
```

#### NM2-2. Response Variable

* Extraction Criteria: Specify the imaging-derived variable(s) being modeled.
* 以下の Major Category, Minor Category から該当するものを選んで回答してください（複数回答可）。

| Major Category | Minor Category   | Full Spelling                                             | Description                                | Example                   | 
|----------------|------------------|-----------------------------------------------------------|--------------------------------------------|---------------------------|
| sMRI           | CT               | cortical thickness                                        | sMRI, 皮質厚の平均や頂点値                           | 左上側頭回の皮質厚                 |
| sMRI           | CV               | cortical volume                                           | sMRI, 皮質領域の体積                              | 右前頭極の皮質体積                 |
| sMRI           | SA               | surface area                                              | sMRI, 皮質面積（頂点/領域）                          | 左外側後頭葉の面積                 |
| sMRI           | SV               | surface volume                                            | 表面ベースで算出した皮質体積                             | 全皮質の表面体積                  |
| sMRI           | GMV              | gray matter volume                                        | 灰白質体積（領域/全脳）                               | 両側海馬の灰白質体積                |
| sMRI           | WMV              | white matter volume                                       | 白質体積（領域/全脳）                                | 前頭葉白質体積                   |
| sMRI           | GBV              | global brain volume                                       | 全脳の総体積                                     | 全脳容積（灰白質+白質）              |
| sMRI           | TIV              | total intracranial volume                                 | 頭蓋内容積                                      | TIVでスケールした各IDP            |
| sMRI           | CSF              | cerebrospinal fluid volume                                | 脳脊髄液量                                      | 側脳室体積                     |
| sMRI           | SubV             | subcortical volume (regional)                             | 汎用ROIの皮質下体積                                | 扁桃体体積                     |
| sMRI           | CerLV            | cerebellar lobule volume                                  | 小脳葉の体積                                     | Crus I体積                  |
| sMRI           | CCMorph          | corpus callosum morphology                                | 脳梁の体積/面積/長さ/周長                             | 脳梁膨大部面積                   |
| sMRI           | GI/CURV          | gyrification/curvature                                    | 脳回形成や曲率の指標                                 | 全皮質平均gyrification         |
| sMRI           | WMH              | white matter hyperintensity                               | 白質高信号量                                     | Fazekasスコア、WMH総体積         |
| sMRI           | DDM              | deformation-derived morphometry                           | 変形場のJacobianによる形態指標                        | VBMのJacobian平均            |
| dMRI           | FA               | fractional anisotropy                                     | 拡散異方性のスカラー指標                               | 上縦束のFA                    |
| dMRI           | GFA              | generalized fractional anisotropy                         | Q-ball等での一般化FA                             | 半球平均のGFA                  |
| dMRI           | FAt              | tissue fractional anisotropy                              | 組織成分に限定したFA                                | CSF補正後FA                  |
| dMRI           | MD               | mean diffusivity                                          | 平均拡散係数                                     | 後部内包のMD                   |
| dMRI           | RD               | radial diffusivity                                        | 放射方向拡散係数                                   | 前放線冠のRD                   |
| dMRI           | AD               | axial diffusivity                                         | 軸方向拡散係数                                    | 鉤状束のAD                    |
| dMRI           | FW               | free water                                                | 自由水成分の割合                                   | 側頭葉白質のFW                  |
| fMRI           | LLF-BOLD-metrics | Local low-frequency BOLD fluctuation metrics              | 周波数領域・局所指標                                 | ALFF, fALFF, mALFF, zALFF |
| fMRI           | FC               | functional connectivity                                   | 時系列相関によるFC                                 | PCC–mPFCのFC               |
| fMRI           | rs-FC            | resting-state functional connectivity                     | 安静時fMRIのFC                                 | DMN内FC                    |
| fMRI           | dyn-FC           | dynamic functional connectivity                           | 時間変動するFC/変動度                               | スライディングウィンドウFC分散          |
| fMRI           | FC-gradient      | functional connectivity gradient                          | FC行列の勾配座標                                  | 主勾配(Gradient 1)スコア        |
| fMRI           | FC-strength      | functional connectivity strength                          | 接続強度の総和                                    | mPFCのFCS                  |
| fMRI           | TASK-GLM         | task fMRI general linear model                            | 課題fMRIコントラストのz/tマップ                        | faces>shapesのzマップ         |
| fMRI           | GCor             | global correlation                                        | 全ボクセル平均相関                                  | 全脳GCOR                    |
| fMRI           | LCor             | local correlation                                         | 近傍との局所相関                                   | 角回のLCOR                   |
| PET            | SUVR-amyloid     | standardized uptake value ratio (amyloid)                 | アミロイドPETのSUVR                              | AV45 SUVRCER              |
| PET            | SUVR-tau         | standardized uptake value ratio (tau)                     | タウPETのSUVR                                 | FTP SUVRCBL               |
| PET            | BPND             | binding potential (non-displaceable)                      | PET結合能指標                                   | \[11C\]DASBの線条体BPND       |
| PET            | Ki_cer           | influx rate constant (cerebellar ref.)                    | 小脳基準の取り込み率                                 | [18F]FDOPA Ki_cer         |
| MEG/EEG        | AEC/PEC          | amplitude/phase envelope correlation                      | MEG/EEGの周波数別FC                             | β帯域AEC                    |
| MEG/EEG        | PSD              | power spectral density                                    | MEG/EEG帯域パワー                               | α帯域PSD                    |
| Mathematical   | MSI              | morphometric similarity index                             | 形態類似度の指標                                   | MSI行列の平均                  |
| Mathematical   | PBSI             | person-based similarity index                             | 溝幅類似度の個人指標                                 | Sulcal width PBSI         |
| Mathematical   | IDP-set          | imaging derived phenotype set                             | 多数のIDPを束ねたセット                              | UKB 2,000+IDPセット          |
| Mathematical   | VQ-VAE-TS        | vector-quantized variational autoencoder typicality score | VQ-VAEでの典型度スコア                             | 典型度z-score                |
| Mathematical   | NMF              | non-negative matrix factorization                         | 成分負荷量/混合比                                  | NMFコンポーネント重み              |
| Mathematical   | LI               | laterality index                                          | 左右差の指数                                     | 海馬体積のLI                   |
| Mathematical   | NetMes           | network measures                                          | DC (degree centrality) などのネットワーク特徴量        | 視床のDC                     |
| Others         | RETINA           | retinal thickness metrics                                 | 網膜/黄斑/視神経線維層計測                             | RNFL厚                     |
| Unknown        | UNKNOWN          | unknown                                                   | 記述がなく不明。またはどのキーワードにも分類できない特徴量。             |                           | 
* 
* Extraction Result Style: ADCSL_Style
* "answer" 例:

```json
"answer": [
  {"major-category": "dMRI", "minor-category": "FA"},
  {"major-category": "dMRI", "minor-category": "MD"},
  {"major-category": "fMRI", "minor-category": "rs-FS"} 
]
```
-----

#### NM2-3. Explanatory Variables

* Extraction Criteria: List all variables used as explanatory variables (predictors) in the normative model. 
* 以下の Major Category, Minor Category から該当するものを選んで回答してください（複数回答可）。

| Major Category                | Minor Category            | Full Spelling / Description                                      |
|-------------------------------|---------------------------|------------------------------------------------------------------|
| Age-related                   | Age                       | linear age term                                                  |
| Age-related                   | Age^2                     | quadratic age term                                               |
| Age-related                   | Age higher-order          | higher-order age terms (degree ≥3)                               |
| Age-related                   | Age non-int               | non-integer age terms (fractional/negative powers)               |
| Age-related                   | PMA/PN weeks              | post-menstrual age / postnatal weeks at scan                     |
| Demographics                  | Sex                       |                                                                  |
| Demographics                  | Race                      |                                                                  |
| Demographics                  | Ethnic background         |                                                                  |
| Demographics                  | Education                 |                                                                  |
| Clinical / Group              | Dx/Clinical group         | clinical diagnosis or group indicator                            |
| Interactions                  | Age×sex / Sex×age         |                                                                  |
| Site/Scanner                  | Site                      |                                                                  |
| Site/Scanner                  | Scanner                   |                                                                  |
| Site/Scanner                  | Scanner vendor            |                                                                  |
| Site/Scanner                  | Magnetic field strength   |                                                                  |
| Acquisition / Protocol        | Scanning protocol         |                                                                  |
| Acquisition / Protocol        | Acq/task params           | e.g., TR/TE/task parameters                                      |
| Acquisition / Protocol        | Task/acq counts           | counts of blocks/stimuli/volumes                                 |
| Pipeline / Software           | FreeSurfer version        |                                                                  |
| Pipeline / Software           | Preproc pipeline/software | pipeline or software version as fixed effect                     |
| Study / Cohort structure      | Cohort/Study indicator    | cohort/study labels as effect                                    |
| Study / Cohort structure      | Family/Subject RE         | family ID or subject ID random effect                            |
| Global brain measures         | ICV                       | intracranial volume                                              |
| Global brain measures         | TIV                       | total intracranial volume                                        |
| Global brain measures         | Total brain volume        |                                                                  |
| Global brain measures         | Mean CT                   | mean cortical thickness                                          |
| Global brain measures         | Mean SA                   | mean surface area                                                |
| Image/Data quality            | Euler number              |                                                                  |
| Image/Data quality            | Image quality             |                                                                  |
| Image/Data quality            | Mean FD                   | mean framewise displacement                                      |
| Image/Data quality            | Mean relative motion      |                                                                  |
| Image/Data quality            | Head motion               |                                                                  |
| Task                          | Task performance          |                                                                  |
| Other                         | Hemisphere                |                                                                  |
| Other                         | BMI                       | body mass index                                                  |
| Other                         | FIQ                       | full-scale intelligence quotient                                 |
| Other                         | None                      |                                                                  |
| Other                         | Others (not specified)    | others not specified / 選択不可                                  |

* Extraction Result Style: ADCSL_Style
* "answer" 例:

```json
"answer": [
  {"major-category": "Age-related", "minor-category": "Age"},
  {"major-category": "Age-related", "minor-category": "Age^2"},
  {"major-category": "Demographics", "minor-category": "Sex"},
  {"major-category": "Site/Scanner", "minor-category": "Scanner vendor"}
]
```

-------------------------

## 抽出結果の出力
- ファイル形式
    - JSON。`./DE_v12_Author20XX_by_Someone_YYYYmmddHHMMSS.json` の構造に合わせる。
- ファイル名は `<DE version>_by_<Agent Name>_<AuthorYear>_<Processing Date>.json`
    - Claude Code の場合: `DE_v12_Bethlehem2022_by_claude_202509191115.json`
    - Gemini CLI の場合: `DE_v12_Bethlehem2022_by_gemini_202509191115.json`
    - Codex-CLI の場合: `DE_v12_Bethlehem2022_by_codex_202509191115.json`
    - ファイル名はASCII、空白なし。
- JSONフォーマット
    - キーは指定通り、item IDは snake_case。
    - Answer は混在内容なら文字列。純粋な数値は数値/文字列いずれでもよいが、ファイル内で一貫させる。
    - 末尾カンマ禁止、有効なJSONにする。
