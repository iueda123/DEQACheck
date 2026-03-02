# 抽出基準文書（Data Extraction Protocol）

------------------

## Imaging Modality

### 概要

本項目では、当該研究でNormative Modelingの対象となった特徴量（Response Variable）が由来する主な画像モダリティを抽出した。
各研究で用いられたモダリティについて、論文のMethods（MRI Data
Acquisition等）の記載に基づき、まず具体的なシーケンスや計測手法を自由記述形式で記録した（例: "T1w MRI; PET (Amyloid,
18F-Florbetapir)"）。
その上で、集計表（Table 1）においては **sMRI / fMRI / dMRI / Other** の4カテゴリへ分類した。1研究が複数のモダリティを用いている場合は、該当する全カテゴリにチェックを入れた。

### カテゴリ定義と分類基準

#### sMRI（構造的MRI）

T1強調画像（T1-weighted
MRI）を用いた研究を本カテゴリに分類した。MPRAGE、FSPGR、BRAVO等の具体的パルスシーケンスの違いにかかわらず、T1強調画像であれば全てsMRIに含めた。T2強調画像（T2-weighted
MRI）やT2-FLAIR画像を用いた研究も本カテゴリに分類した。sMRIから得られる代表的な特徴量には、皮質厚（cortical
thickness）、灰白質体積（gray matter volume）、皮質面積（surface area）、皮質下体積（subcortical volume）等がある。

#### fMRI（機能的MRI）

Blood-oxygen-level-dependent（BOLD）信号に基づく機能的MRIを用いた研究を本カテゴリに分類した。安静時fMRI（resting-state
fMRI）および課題遂行時fMRI（task fMRI）のいずれも含む。fMRIから得られる代表的な特徴量には、機能的結合（functional
connectivity: FC）、安静時FC（resting-state FC）、低周波BOLD変動指標（ALFF, fALFF等）、課題fMRIのコントラストマップ（GLMベースのz/t値）等がある。

#### dMRI（拡散MRI）

拡散強調画像（diffusion-weighted
imaging）を用いた研究を本カテゴリに分類した。拡散テンソルイメージング（DTI）をはじめ、各種拡散モデルから得られる指標を対象とする。具体的な拡散指標として、fractional
anisotropy（FA）、mean diffusivity（MD）、radial diffusivity（RD）、axial diffusivity（AD）、free water（FW）、tissue
FA（FAt）等がある。自由記述ではこれらの具体的指標も併記した。

#### Other（その他）

上記3カテゴリ（sMRI, fMRI,
dMRI）のいずれにも該当しないモダリティを用いた研究を本カテゴリに分類した。具体的には以下のモダリティが該当した:

- **PET（陽電子放射断層撮影）**:
  アミロイドPET（18F-Florbetapir等）、タウPET（18F-Flortaucipir等）、ドパミン系トレーサー（18F-FDOPA等）、その他の放射性リガンド（11C系トレーサー等）を用いた研究。代表的な特徴量にSUVR（standardized
  uptake value ratio）、BPND（binding potential）、Ki等がある。
- **EEG（脳波）**: 安静時または課題時の頭皮脳波計測を用いた研究。代表的な特徴量にpower spectral density（PSD）、amplitude
  envelope correlation（AEC）等がある。高密度EEG（HD-EEG; 128ch等）も含む。
- **MEG（脳磁図）**: 安静時または課題時のMEG計測を用いた研究。PSD、AEC等の周波数帯域別指標が該当する。
- **qMRI（定量的MRI）**: R1（1/T1）、R2*（1/T2*）、磁化率（QSM: χ）等の定量的MRIパラメータを用いた研究。
- **網膜画像（retinal imaging）**: 光干渉断層計（OCT）等による網膜厚の計測を用いた研究。

### 抽出時の留意事項

- 同一研究で複数のモダリティが使用されている場合、該当する全てを記録した（例: T1w MRIとfMRIの両方を使用していれば、sMRIとfMRIの両方にチェック）。

------------------

## Normative Response Variable

### 概要

本項目では、各研究においてNormative Modelの目的変数（応答変数）として用いられた
画像由来の特徴量を抽出した。抽出ガイド（DE_Guide_v10_1）に基づき、
各研究で具体的にどの脳画像指標がモデリングされたかを自由記述形式で記録した後、
メンバー間で共有の集計表（Table 1）ではカテゴリキーワードを用いて正規化した。

カテゴリキーワードの策定にあたっては、
まずチームリーダーが設定した初期の16キーワード案を基に、
AI抽出結果（codex/claude/gemini計375件）のnm4_response_variable/answer欄を走査し、
未カバーのカテゴリを同定した。
その結果、DTI系拡散指標、ネットワーク指標、変形ベース形態指標、Task
fMRI活性、PET/SPECT指標、MEG/EEG指標、網膜画像指標等が追加され、
最終的にMajor Category 10種、Minor Category 50種の分類体系を確定した。

### カテゴリ体系

Major Categoryは画像モダリティ・計測手法に基づく大分類であり、Minor Categoryは具体的な特徴量の種類を表す。

| Major Category | 代表的なMinor Category                                                                  | 説明                                   |
|----------------|-------------------------------------------------------------------------------------|--------------------------------------|
| sMRI           | CT, CV, SA, SV, GMV, WMV, GBV, TIV, CSF, SubV, CerLV, CCMorph, CGeom, WMH, DDM      | 構造的MRI由来の形態指標。皮質厚、体積、面積等の領域別・全脳指標を含む |
| dMRI           | FA, GFA, FAt, MD, RD, AD, FW                                                        | 拡散MRI由来の白質微細構造指標                     |
| fMRI           | LLF-BOLD-metrics, FC, rs-FC, dyn-FC, FC-gradient, FC-strength, TASK-GLM, GCor, LCor | 機能的MRI由来の結合・活動指標。安静時・課題時の両方を含む       |
| qMRI           | R1, R2*, χ                                                                          | 定量的MRIパラメータ                          |
| PET            | SUVR-amyloid, SUVR-tau, BPND, Ki_cer                                                | PETトレーサー別の取り込み・結合指標                  |
| MEG/EEG        | AEC/PEC, PSD, MEG/EEG-Other                                                         | MEG/EEGの周波数帯域別パワーおよび結合指標             |
| Mathematical   | MSI, PBSI, IDP-set, VQ-VAE-TS, NMF, LI, NetMes                                      | 複数の画像指標から派生した数理的指標                   |
| Others         | RETINA                                                                              | 上記に該当しない計測指標（網膜厚等）                   |
| Unknown        | UNKNOWN                                                                             | 記述がなく分類不能な特徴量                        |

### 分類上の主な判断基準

- **SubV と GMV の使い分け**: 対象が明確に皮質下核の体積（海馬、扁桃体、視床等）であればSubV、それ以外の灰白質体積（全脳GMV、VBM由来のGM体積等）はGMVに分類した。
- **CV と GMV の使い分け**: FreeSurferのcortical volumeのように皮質に限定された体積はCV、VBM由来やROIの一般的なgray
  matter volumeはGMVとした。記述が曖昧な場合はGMVに分類した。
- **CGeom（cortical geometry）**: 皮質表面の幾何学的特性（gyrification index, curvature, K/I/S独立成分等）を包括するカテゴリとして設けた。
- **DDM（deformation-derived morphometry）**: 非線形正規化の変形場（Jacobian行列の決定量）そのものをresponse
  variableとしたDBM/TBM研究に付与した。JacobianでモジュレーションしたVBMボクセル値を用いた研究はGMV/WMVに分類し、DDMとは区別した。
- **1研究で複数の特徴量**: 該当する全てのMinor Categoryキーワードを付与した。

### 参考

- 抽出ガイド: DE_Guide_v10_1.md（NM-4. Response Variable）、DE_Guide_v12.md（NM2-2. Response Variable）
- キーワード文書: 20260107_Keywords-for-Response-Variable.md

------------------

## Explanatory Variables

### 概要

本項目では、Normative Model構築時にモデルに組み込まれた、
あるいは条件付けに用いられた主要な変数を抽出した。抽出ガイド(DE_Guide_v10_1）に基づき、
各研究でどの変数がモデルに投入されたかを記録した。

本レビューではこれらの変数を「Explanatory Variables（説明変数）」と総称した。
年齢・性別のようにNormative Modelの正常軌跡を定義する主たる予測子から、
サイト・スキャナのように影響を統制するための共変量（covariates）まで、
モデルに投入される変数の役割は多様であるが、いずれも応答変数の変動を「説明」するという点で共通しており、
上位概念であるexplanatory variablesが総称として適切である。
なお、Normative Modeling文献ではこれらを一括して "covariates" と呼ぶ慣習もあるが
（Rutherford et al., 2022; PCNtoolkit; Franke2025等）、
年齢のような主予測子を含む以上、伝統的な統計用語法に照らすとexplanatory variablesの方が正確と考える。

これらの変数のモデルへの組み込み方も多様であり、固定効果としての投入（例:
GLMでの年齢・性別）、ランダム効果としての組み込み（例: HBRでのサイト）、スプライン平滑化（例:
GAMLSSでの年齢）、あるいは前処理段階でのハーモナイゼーション（例: ComBatによるサイト効果除去）など、手法によって異なる。

集計表（Table 1）では、初期のExplanatory Variables列（自由記述）に加え、カテゴリキーワードで正規化したCovariates列を設けた。

### カテゴリ体系

Major Category 11種、Minor Category 37種の分類体系を用いた。

| Major Category         | 代表的なMinor Category                                                      | 説明                  |
|------------------------|-------------------------------------------------------------------------|---------------------|
| Age-related            | Age, Age^2, Age higher-order, Age non-int, PMA/PN weeks                 | 年齢関連の項。次数の軸で分類      |
| Demographics           | Sex, Race, Ethnic background, Education                                 | 人口統計学的変数            |
| Clinical/Group         | Dx/Clinical group                                                       | 臨床診断・群ラベル           |
| Interactions           | Age×Sex                                                                 | 交互作用項               |
| Site/Scanner           | Site, Scanner, Scanner vendor, Magnetic field strength                  | サイト・スキャナ関連変数        |
| Acquisition/Protocol   | Scanning protocol, Acq/task params, Task/acq counts                     | 撮像プロトコル関連           |
| Pipeline/Software      | FreeSurfer version, Preproc pipeline/software                           | 前処理パイプライン情報         |
| Study/Cohort structure | Cohort/Study indicator, Family/Subject RE                               | コホート構造・家族/被験者ランダム効果 |
| Global brain measures  | ICV/TIV, TBV, TCV, TGV, Mean CT, Mean SA, Total SA                      | 全脳指標                |
| Image/Data quality     | Euler number, Image quality, Mean FD, Mean relative motion, Head motion | 画像品質指標              |
| Other                  | Hemisphere, BMI, IQ, Task performance, Others, Not specified, None      | その他・未特定             |

### 年齢項の分類基準

年齢に関連する項は、「項の次数」を軸に分類した。当初の案にあった「s(age)
」（スプライン平滑化された年齢）は、変数名というよりモデリング手法・関数形に関する情報であるため廃止し、スプライン使用の情報はModeling
Methodsの記載に委ねた。

- **Age**: 年齢の一次項（線形項）。
- **Age^2**: 年齢の二次項（二乗項）。線形項とセットで投入されている場合も、AgeとAge^2の両キーワードを記録した。
- **Age higher-order**: 三次以上の整数冪項（例: age^3, age^4）。B-spline基底展開もこのカテゴリに含めた。
- **Age non-int**: 分数冪・負の冪項（例: age^0.5, age^{-1}）。fractional polynomialモデルが該当する。

### 抽出時の留意事項

- Normative Model構築段階で投入された変数と、下流の解析（群間比較等）で共変量として含めた変数が論文中で混在している場合がある。本項目ではNormative
  Model構築段階の変数を優先して記録した。
- HBRにおけるサイトのランダム効果のように、固定効果・ランダム効果の区別がある場合は、DE_Guide_v10_1のNM-6（Predictor
  Effects）に基づき、効果の種類も併せて記録した。

### 参考

- 抽出ガイド: DE_Guide_v10_1.md（NM-5. Predictor Variables、NM-6. Predictor Effects）、DE_Guide_v12.md（NM2-3. Explanatory
  Variables）
- キーワード文書: 20261014_Keywords-for-Explanatory-Variables.md

------------------

## Modeling Methods

### 概要

本項目では、各研究でNormative Modelの構築に用いられた統計的・機械学習的アルゴリズムを抽出した。
抽出ガイド(DE_Guide_v10_1）に基づき、具体的な手法名を記録した後、
集計表（Table 1）ではカテゴリキーワードで正規化した。

キーワードの策定にあたっては、初期の24キーワード案を基に、
AI抽出結果（codex/claude/gemini計372件）のnm2_modeling_method/answer欄を走査し、
未カバーの手法を同定した。その結果、多項式回帰（POLY）、分位点回帰（QUANTREG）、素のAutoencoder（AE）、線形混合モデル（LMM）等が追加され、最終的にMajor
Category 9種、Minor Category 32種の分類体系を確定した。

### カテゴリ体系

| Major Category    | 代表的なMinor Category                                | 説明                                                                              |
|-------------------|---------------------------------------------------|---------------------------------------------------------------------------------|
| Bayesian Methods  | BLR, WBLR, GPR, HBR, HBLM, HBGPM                  | ベイズ推定の枠組みを用いた手法群。事前分布からパラメータの不確実性を確率分布として推定する。HBR系は多施設研究でのサイト間変動を階層構造で吸収できる点が特徴 |
| Deep Learning     | AE, AAE, VAE, ConVAE, VQ-VAE, mmVAE, mmSIVAE, GPT | ニューラルネットワークベースの手法群。高次元画像データの潜在表現学習や生成モデルを利用する                                   |
| Nonparametric Reg | GAM, GAMLSS, GAMM, LOESS, NWK, QUANTREG, MAA      | パラメトリックな関数形を仮定せず、スプラインやカーネル等でデータから柔軟に回帰関数を推定する手法群。GAMLSSは分布の位置・尺度・形状を同時にモデル化できる |
| Parametric Reg    | GLM, OLSR, POLY, MFPR                             | 明確なパラメトリック関数形（線形、多項式等）を仮定した回帰手法群                                                |
| Mixed-Effects     | LMM, MEM                                          | 固定効果とランダム効果を組み合わせた混合効果モデル。縦断データや階層的データ構造に対応する                                   |
| Domain-Specific   | FUNCOIN, PBSI                                     | 特定のドメインに特化した手法。FUNCOINは機能的結合データ専用、PBSIは個人ベースの類似性指標                              |
| Nearest Neighbor  | N3                                                | 最近傍法を用いた局所的ノーマティブ分布の推定                                                          |
| Others            | NIE, PCN-UNSPEC, UNKNOWN/NR                       | ノンパラメトリック許容区間推定（NIE）、ツールキット名のみで手法未特定（PCN-UNSPEC）、および情報欠損                        |

### 使用頻度の高い手法

Table 1 の集計（上田案、122研究）では以下の手法が多く用いられていた:

- **GPR**（ガウス過程回帰）: 28研究 — ノンパラメトリックベイズの代表的手法
- **GAMLSS**: 12研究 — 分布の全パラメータをモデル化可能
- **WBLR**（ワープ付きベイズ線形回帰）: 12研究 — 非正規性に対応
- **HBR**（階層ベイズ回帰）: 11研究 — 多施設研究での階層構造に対応
- **BLR**（ベイズ線形回帰）: 6研究

### 抽出時の留意事項

- 1研究で複数の手法が用いられている場合（例: 比較研究、モダリティ別の手法使い分け）、該当する全てのMinor
  Categoryキーワードをセミコロン区切りで記録した。
- 「PCNtoolkit」等のツールキット名のみが記載され具体的アルゴリズムが特定できない場合はPCN-UNSPECとした。

### 参考

- 抽出ガイド: DE_Guide_v10_1.md（NM-2. Modeling Method）、DE_Guide_v12.md（NM2-1. Modeling Methods）
- キーワード文書: 20260107_Keywords-for-Modeling-Method.md

------------------

## Site/Scanner Effect Handling

### 概要

本項目では、各研究において多施設・多スキャナに起因する系統的差異（サイト効果・スキャナ効果）がどのように対処されたかを抽出した。抽出ガイドDE_Guide_v10_1のRCI-9（Site
Effect Handling）に基づき、ADCSL_Style（Answer, Detail, Confidence rating, Supporting text, Location）で記録した。

### カテゴリ定義と分類基準

集計表（Table 1）では、各研究のサイト効果対処法を以下のカテゴリに分類した。

#### None（対処なし）: 30研究

サイト効果への対処が行われていない研究。単施設研究でサイト間変動がそもそも存在しない場合や、多施設であってもサイト効果への対処が報告されていない場合を含む。

#### Batch-removal（バッチ除去）: 24研究

Normative Model構築の前段階（前処理）として、サイト効果を統計的に除去する手法。代表的にはComBat（Empirical Bayes
harmonization）やComBat-GAMが該当する。バッチ変数（サイト、スキャナ等）を指定し、保存すべき共変量（年齢、性別等）を指定した上で、バッチ由来の系統的差異を除去する。

#### Model-based（モデルベース）: 50研究

Normative Model自体にサイト効果を組み込んで対処する手法。以下のアプローチが含まれる:

- **階層ベイズ回帰（HBR）のサイトランダム効果**: サイトをランダム効果として階層的にモデル化し、サイト間の変動と個人間の変動を分離する。
- **GAMLSS等のサイト効果**: サイトを固定効果またはランダム効果としてモデルに含める。
- **転移学習（transfer learning）**: 大規模データセットで事前学習したモデルを、少数のローカルデータで再キャリブレーションする。新しいサイトのデータに対してモデルを適応させる手法。

#### Other（その他）: 7研究

上記カテゴリに該当しない対処法。

#### 複合的対処

一部の研究ではBatch-removalとModel-basedの両方を併用、あるいは比較検討していた（例:
ComBatで前処理した上でHBRのサイトランダム効果も組み込む等）。このような場合は該当する全てのカテゴリを記録した。

### Table 1 のチェック方式との関係

Table 1 では、上記カテゴリ分類（C19列）に加え、4つのチェック列（C15-C18）も設けた:

- **N/A**: サイト効果対処が該当しない研究（16研究）
- **None**: 対処なし（27研究）
- **Modeled**: モデル内でサイト効果を処理（48研究）
- **Harmonized**: 前処理段階でハーモナイゼーション済み（33研究）

チェック方式はカテゴリ分類を補完する簡易的な表現であり、ModeledはModel-basedに、HarmonizedはBatch-removalにおおむね対応するが、1対1の対応ではない。

### 参考

- 抽出ガイド: DE_Guide_v10_1.md（RCI-9. Site Effect Handling）

------------------

## Model Build

### 概要

本項目では、各研究で用いられたNormative
Modelが当該研究で新たに構築されたものか（New）、それとも既存の事前学習済みモデルを利用したものか（Pre-trained）を抽出した。抽出ガイドDE_Guide_v10_1のNM-1（Model
Origin）に基づき、ADCSL_Style（Answer, Detail, Confidence rating, Supporting text, Location）で記録した。

### カテゴリ定義と分類基準

#### New（新規構築）: 102研究

当該研究のデータを用いてNormative
Modelをゼロから構築した研究。リファレンスコホート（健常者データ）を用いてモデルのパラメータを推定し、臨床群に適用する、という一連のプロセスが当該論文内で完結している場合にNewと分類した。

#### Pre-trained（事前学習済み）: 19研究

他の研究やリソースで構築・公開された既存のNormative
Modelを利用した研究。Pre-trainedと分類した場合、Detailに以下の情報を可能な限り記録した:

- **事前学習データセットの規模と出典**: データセットのN数、サイト数（例: "N≈75k, multi-site"）
- **事前学習に用いたモデリング手法**: 例: GAMLSS, wBLR等
- **主要な共変量**: 事前学習時に使用された共変量
- **元論文・リソースへの参照**: 例: Bethlehem2022, Rutherford2022等
- **適応・キャリブレーションの有無と方法**: ローカルデータによる転移学習やキャリブレーションが行われたかどうか

代表的な事前学習済みモデル:

- **BrainChart（Bethlehem et al., 2022）**: GAMLSSベース。約75,000人の多施設データで構築された脳形態のnormative
  model。セントタイルスコアとして出力される。
- **PCNtoolkit pre-trained models（Rutherford et al., 2022等）**:
  wBLR/HBRベース。約59,000人・82サイトのデータで構築されたモデル。ローカルデータによる再キャリブレーション機能を持つ。

### 抽出時の留意事項

- 一部の研究では、モダリティや目的に応じてNewとPre-trainedを併用していた（例:
  構造MRIはPre-trained、fMRIはNew）。このような場合はその旨を記録した。
- Pre-trained modelをそのまま（out-of-sample centiles等として）適用した場合と、ローカルデータで再キャリブレーション（transfer
  learning等）した場合を区別してDetailに記録した。

### 参考

- 抽出ガイド: DE_Guide_v10_1.md（NM-1. Model Origin）


---------------


## Reference Cohort Info

### Using MSAD or Not



### Using EPD or Not



### EPD Name 



### Phase



### N



### Sex



### Age


--------------------

## Clinical Cohort Info

### 