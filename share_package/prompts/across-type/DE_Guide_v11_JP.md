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

## ソース資料の場所

- 対象となる論文はカレントディレクトリにあります。以下の項目を、このフォルダ内のすべての情報に基づいて抽出してください。
- 必要に応じてサブフォルダも参照してください。

-------------------

## 一般的な抽出ルール

- スコープと優先順位
  - 本文と表を優先。なければ補足資料を参照。それでもなければ明示的に引用された外部ソースを使用し、Locationに出典を記載。
  - 複数のタイムポイント/セッションがある場合、通常はノルマティブモデルの学習/評価に実際に用いられた（典型的にはベースライン）ものを抽出し、異なる場合は明記。

- 記載の慣習
  - 複数値はセミコロンで区切る（例: 複数データセット、診断、指標）。
  - 年齢は原則「年」。論文が別の明示的単位を使う場合はその単位を使用し、不明なら単位を明記。
  - 標準トークン: NA=該当なし; NR=未報告; Unknown=精読しても不明。
  - 受容基準のみある場合はそれを報告し、欠損統計はNRと記載。

- 計算と丸め
  - 提示された数・統計から導出値（百分率、加重平均/プールドSDなど）を計算してよい。百分率は小数1桁、平均/SDは小数2桁を基本（論文が別精度ならそれに合わせる）。
  - 性別の一方のみ記載がある場合、総Nから差し引いて推定し、Reasonで推定を明記。
  - 範囲は「min–max」で表記（可能ならエンダッシュ）。

- 一貫性と書式
  - 項目名は指定どおり厳守。新しいフィールドを作らない。
  - ACRSL_Style では必ず5要素（answer, confidence_rating, reason, supporting_text, location）。
  - ADCSL_Style でも必ず5要素（answer, detail, confidence_rating, supporting_text, location）。
  - Supporting Text は簡潔な原文引用。言い換え禁止。
  - Location はファイル名と特定できる位置（セクション/行/ページ等）。

- あいまいさへの対応
  - 証拠が間接/分散している場合は Partial とし、Reasonで統合手順を説明。
  - 真に不在なら NR とし、confidence もそれに合わせる。推測しない。

-------------------

## データ抽出項目

**抽出結果のスタイル**

データ抽出ガイドでは4つのスタイルを使います:

  * **A_Style** = Answer のみ
    - 抽出した答えだけを記載
    - confidence_rating, reason, supporting_text, location は不要
    - 単純項目や選択肢のみの項目に使用

  * **ASL_Style** = Answer, Supporting text, Location
    - 推論なしにエビデンスを添える:
      - Answer: 抽出情報
      - Supporting text: 出典の簡潔な引用
      - Location: 出典の所在
    - 明示的な文言があるカテゴリ的項目などに使用

  * **ACRSL_Style** = Answer, Confidence rating, Reason, Supporting text, Location
    - 詳細な抽出:
      - Answer: 抽出情報
      - Confidence rating: High / Medium / Low
      - Reason: 判断のステップ
      - Supporting text: 直接引用
      - Location: 引用の所在
    - 複雑で根拠が必要な項目に使用

  * **ADCSL_Style** = Answer, Detail, Confidence rating, Supporting text, Location
    - カテゴリ回答＋簡潔な詳細が必要な項目に使用:
      - Answer: 抽出情報
      - Detail: 項目で求める構造化された詳細
      - Confidence rating: High / Medium / Low
      - Supporting text: 直接引用
      - Location: 引用の所在

詳細なフォーマット要件と例は後述「Basic Evaluation Principles」を参照。

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

#### SI-5. DOI
  * DOI (10.xxxx 形式、なければ NR)
  * 抽出スタイル: A_Style



### SC. Study Characteristics Part

#### SC-1. Study Objective
  * 主研究目的を1–2文で要約
  * 抽出スタイル: A_Style
  * 注: 主要な研究目的を記述

#### SC-2. Study Design
  * 抽出基準: Cross-sectional / Longitudinal / Other から選択
  * 抽出スタイル: A_Style
  * 注: 選択肢から選ぶ

#### SC-3. Study Design Other
  * 抽出基準: その他の研究デザインの説明
  * 抽出スタイル: A_Style
  * 注: 
    * "study_design" が "Other" の場合、その内容を記述。
    * それ以外は「-」。


### RCI. Reference Cohort & Imaging Part

#### RCI-1. Dataset Name
  * 抽出基準: 健常対照/ノルマティブ参照に用いたデータセット名
  * 抽出スタイル: ACRSL_Style
  * 注:
    * 使用したデータセット名を列挙。複数はセミコロンで区切る。
    * データセットが多い（5つ以上）場合は代表例と簡潔な補足（例: "Multi-site aggregated dataset; representative: ABIDE; HCP; UK Biobank"）。全リストは general_notes に記録。
    * データセットの版があれば含める（例: ABIDE I/II）。
    * 大規模DBの特定コホートやサイトのみ使用なら、それを記載（例: "UCL; NACC ADRC '8361'").
  * 例:
    * ABIDE I; ABIDE II
    * UK Biobank
  
#### RCI-2. HC_N
  * 抽出基準: 健常対照のサンプルサイズ
  * 抽出スタイル: ACRSL_Style
  * 注:
    * 実際に分析に使われた人数を記録（例: ABIDEが573でも解析対象が569なら569）。除外後の最終数を記載。
    * 別論文に記載とされる場合は、それを特定できるだけの情報を書く。
  * 例: 
    * 569
  
#### RCI-3. HC_Age
  * 抽出基準: 健常対照の平均・SD・範囲
  * 抽出スタイル: ACRSL_Style
  * 注:
    * 報告がない場合、与えられた情報から計算してよい（性別別の平均/SDと人数から加重平均とプールドSDなど）。
    * 胎児を含む場合、胎児は年齢0として扱い、general_notesに明記（例: "healthy controls include 115 days post-conception"）。
    * 別記事に記載とされる場合は、その記事を特定できる情報を書く。
    * 部分的情報のみの場合は、得られた情報を記載し、欠損は明示的に "NR"。
    * 受容基準のみ記されている場合はそれを記し、実際の統計がないことを注記。
  * 例:
    * mean 50.00; sd 5.40; min 39; max 64
  
#### RCI-4. HC_Sex
  * 抽出基準: 性別ごとの人数と割合
  * 抽出スタイル: ACRSL_Style
  * 注:
    * 性別の人数と割合を報告。割合は計算してよい。
    * 一方のみ人数がある場合、総Nから差し引きで推定し、Reasonで説明。
    * 性別分布が未報告なら "Unknown"。
  * 例:
    * F 99 (17.4%); M 470 (82.6%)

#### RCI-5. Imaging Modality
  * 抽出基準: 使用した画像モダリティ（セミコロン区切り）
  * 抽出スタイル: ACRSL_Style
  * 注:
    * すべてのモダリティを列挙。セミコロンで区切る。
    * MRIシーケンスは具体的に（T1-weighted, T2-weighted, T2-FLAIR など）。
    * dMRIなら指標（FA, FAt, FW等）があれば記載。
    * サブセットや特定目的のみで用いるモダリティは括弧で注記。
  * 例:
    * T1-weighted MRI; Diffusion MRI (FA)
    * T2-FLAIR (subset for pial refinement)
  
#### RCI-6. Analysis Level
  * 抽出基準: "Voxel-level", "ROI-level", "Vertex-level", "Network-level", "Other" から選択
  * 抽出スタイル: ASL_Style
  * 注: 選択肢から選ぶ

#### RCI-7. Preprocessing Pipeline
  * 抽出基準: 主要ソフトウェアと処理ステップ
  * 抽出スタイル: ACRSL_Style
  * 注:
    * 主要ソフト/ライブラリをバージョン付きで（わかる範囲）セミコロン区切り。
    * 重要な処理工程（パーセレーション方式、レジストレーション等）を含める。
    * 使用したアトラスやテンプレートを記載。
    * データセットごとに異なるバージョン/パイプラインならそれも明記。
  * 例:
    * FreeSurfer v6.0; Desikan–Killiany parcellation; hemispheric averaging
    * fMRIPrep (version NR); MNI152 registration
    * FSL DTIFIT; TBSS (skeleton threshold 0.2); JHU atlas
  
#### RCI-8. Quality Checking
  * 抽出基準: QCの有無と主な内容
  * 抽出スタイル: ADCSL_Style
  * 注:
    * Answer: "Yes" または "No"。
    * Detail には該当する内容を簡潔に:
      - 使用した主なQC指標（Euler number, framewise displacement, SNRなど）
      - 適用した閾値
      - 除外数/割合
      - QCが手動/自動/両方か
      - 除外サンプルやQC指標を共変量に含めたか
      - QCに関する感度解析の有無
      - 記載が少なく他論文参照ならその旨（Supporting Text/Locationで引用）
    * Answer = "No" の場合、Detail = "-"。
  * 例:
    * Answer: Yes; Detail: Euler number thresholding (median-centered |Euler| > 25) with 4% excluded; FD included as covariate; visual QC performed
    * Answer: Yes; Detail: Participants with >5% missing ROIs excluded; ±2 IQR outlier removal
    * Answer: No; Detail: -
  
#### RCI-9. Site Effect Handling
  * 抽出基準: 取扱いカテゴリと主要内容
  * 抽出スタイル: ADCSL_Style
  * 注:
    * Answer は "None", "Batch-removal", "Model-based", "Other" から。
    * Detail は Answer ≠ "None" の場合に、以下を簡潔に: 方法名（ComBat, ComBat-GAM, HBR/wBLR, transfer learning, calibration など）、バッチ変数（サイト/センター/データセット/スキャナ/ベンダ/プロトコル等）、保持する共変量（年齢/性別/診断等）、インサンプルかアウトサンプルか、ローカルコントロールを用いた転移学習やキャリブレーションの有無、代替法との比較、ハーモナイゼーションの有効性評価など。
    * Answer = "None" の場合、Detail = "-"。
  * 例:
    * Answer: Batch-removal; Detail: ComBat by site (batch = site; covariates = age, sex); validated preservation of biological signal
    * Answer: Model-based; Detail: Hierarchical Bayesian regression with site random effects; compared against ComBat
    * Answer: Model-based; Detail: Transfer learning calibration using small local control set; site/scanner as batch variable
    * Answer: None; Detail: -

----


### NM. Normative Modeling Part

#### NM-1. Model Origin
  * 抽出基準: モデルの起源と事前学習の詳細
  * 抽出スタイル: ADCSL_Style
  * 注:
    * Answer: "New" または "Pre-trained"。
    * Detail: 
      - Pre-trained の場合: 事前学習データセットの規模/ソース（N, サイト数）、事前学習に用いた手法、主要共変量、元論文/リソースへの参照、当該研究での適応/キャリブレーションの有無。
      - New の場合: Detail = "-"。
    * 簡潔に。複数ソースはセミコロンで区切る。
  * 例:
    * Answer: Pre-trained; Detail: Pre-trained BrainChart GAMLSS (N≈75k, multi-site); out-of-sample centiles; local calibration applied
    * Answer: Pre-trained; Detail: Pre-trained wBLR (N≈59k, 82 sites); recalibrated with local controls
    * Answer: New; Detail: -

#### NM-2. Modeling Method
  * 抽出基準: アルゴリズム名（例: GPR, Deep Learning, GAMLSS）
  * 抽出スタイル: ACRSL_Style
  * 注:
    * ノルマティブモデリングに用いた統計/MLアルゴリズムを明記。
    * 必要に応じてモデル定式化の要点（ワーピング関数、基底展開など）を記載。
  * 例:
    * GAMLSS
    * Hierarchical Bayesian regression (wBLR/HBR)
    * Gaussian process regression (GPR)


#### NM-3. Software Tools
  * 抽出基準: 使用ソフト/ライブラリ
  * 抽出スタイル: ACRSL_Style
  * 注:
    * バージョンがわかれば併記。
    * カスタム実装ならその旨を記載。
    * 工程ごとに異なるツールを使う場合、すべて記載。
  * 例:
    * PCNtoolkit v0.20
    * R (gamlss)
    * Stan / PyMC3 (custom HBR)


#### NM-4. Response Variable
  * 抽出基準: 応答変数
  * 抽出スタイル: ACRSL_Style
  * 注:
    * モデリング対象の画像由来変数を記述。
    * 解剖学的スコープ（グローバル/領域/ボクセルなど）。
    * 領域解析ならパーセレーション方式と領域数。
    * dMRIなら指標（FA, MD, FW等）とトラクト/ROIを記載。
  * 例:
    * Cortical thickness (Desikan–Killiany, 68 ROIs)
    * Fractional anisotropy (JHU tracts via TBSS)

#### NM-5. Predictor Variables
  * 抽出基準: 予測変数（セミコロン区切り）
  * 抽出スタイル: ACRSL_Style
  * 注:
    * すべての予測変数を列挙。セミコロンで区切る。
    * 性別層別モデルなら性別を予測変数に列挙せず、その旨を記載。
  * 例:
    * Age; Sex; Site/Scanner; ICV; Motion (FD)

#### NM-6. Predictor Effects
  * 抽出基準: 固定/ランダム効果の指定
  * 抽出スタイル: ACRSL_Style
  * 注:
    * 予測変数を固定/ランダムのどちらで扱うかを記述。
    * 階層/マルチレベル構造を使うか。
    * 非パラメトリック（スプライン/カーネル/GP等）も記述。
    * 固定/ランダムの明記がない場合は、モデルの分かる情報を記述。
  * 例:
    * Age/Sex = fixed; Site = random (hierarchical)

#### NM-7. Normative Modeling Validation with Handling Nuisance Structure
  * 抽出基準: ノルマティブモデルのバリデーションにおける不要構造の取り扱い
  * 抽出スタイル: ACRSL_Style
  * 注:
    * 不要構造: サイト/スキャナ効果、バッチ効果、QC指標（モーション、Euler）、年齢の非線形性、ヘテロスケダスティシティ、非ガウス分布など。
    * 対応方法: ハーモナイゼーション（ComBat 等）、階層/ランダム効果、転移学習、共変量の組込み、感度解析など。
    * Answer は "Yes" / "Partial" / "No"。
  * 例（answer欄）:
    * Yes
    * Partial
    * No

#### NM-8. Normative Modeling Validation Strategy using Same Domain Non-Independent Dataset
  * 抽出基準: 同一ドメイン非独立データセットでのバリデーション戦略
  * 抽出スタイル: ACRSL_Style
  * 注:
    * 訓練データ自身や非独立リサンプリング（全データで推定したパラメータを評価に使うCV/ブートストラップ等）を指す。
    * 例: 訓練データでのモデル適合報告、全コホートでの感度解析。
    * Answer: "Yes" / "Partial" / "No" / "NA"（事前学習モデルで再推定なしの場合）。
  * 例（answer欄）:
    * Yes
    * Partial
    * No
    * NA

#### NM-9. Normative Modeling Validation Strategy using Same Domain Independent Dataset
  * 抽出基準: 同一ドメイン独立データセットでのバリデーション戦略
  * 抽出スタイル: ACRSL_Style
  * 注:
    * 同一データセット内の独立サブセットでの検証（適切な学習/検証分割やk-foldでの再推定）。
    * Answer: "Yes" / "No" / "Partial"。
  * 例（answer欄）:
    * Yes
    * No
    * Partial

#### NM-10. NM Validation Strategy using Different Domain Dataset
  * 抽出基準: 異なるドメインデータセットでのバリデーション戦略
  * 抽出スタイル: ACRSL_Style
  * 注:
    * 学習データに含まれない別コホート/サイト/スキャナ/集団での検証。
    * 例: Dataset Aで学習しDataset Bで適用、HCPで学習しADNIに適用など。転移学習/ローカルキャリブレーションもこれに含む。
    * Answer: "Yes" / "No" / "Partial"。
  * 例（answer欄）:
    * Yes
    * No
    * Partial


### CAA. Clinical Application & Analysis Part

#### CAA-1. Clinical Dataset
  * 抽出基準: 臨床応用に用いたデータセット名
  * 抽出スタイル: ACRSL_Style
  * 注:
    * 患者を含むデータセット名を列挙。
    * 同じデータセットでHCと患者を供給している場合はその旨を記載。
    * 複数ある場合はセミコロンで区切る。
    * グループごとに異なるデータセットを用いる場合は対応関係を明記。
  * 例:
    * ABIDE (ASD subset)
    * ADNI


#### CAA-2. Diseases Studied
  * 抽出基準: 対象疾患（セミコロン区切り）
  * 抽出スタイル: ACRSL_Style
  * 注:
    * 疾患名は正準名を用い、論文で略語を使う場合は最初に括弧で付記。
  * 例:
    * Alzheimer's disease (AD); Mild cognitive impairment (MCI)
    * Autism spectrum disorder (ASD)
  

#### CAA-3. N of Clinical Groups
  * 抽出基準: 臨床群のサンプルサイズ
  * 抽出スタイル: ACRSL_Style
  * 注:
    * 診断/臨床群ごとの人数を列挙。
    * 複数群は「Diagnosis:N」をセミコロン区切りで。
    * サブグループがあれば記載（例: 併存疾患、コンバーター/非コンバーター）。
  * 例:
    * ASD:482
    * MCI:89; Dementia:90



#### CAA-4. Age of Clinical Groups
  * 抽出基準: 臨床群の年齢（平均±SDおよび/または範囲）
  * 抽出スタイル: ACRSL_Style
  * 注:
    * 可能なら平均±SDを記載。
    * 範囲（min-max）があれば記載。
    * 複数群があれば群ごとに列挙。
    * サブグループ（性別など）があれば含める。
    * 未報告なら「NR」。
  * 例:
    * EP: mean 22.70; sd 3.70
    * PD: 64.1±7.8; DLB: 73.8±6.5



#### CAA-5. Sex of Clinical Group Groups
  * 抽出基準: 臨床群の性別内訳
  * 抽出スタイル: ACRSL_Style
  * 注:
    * 性別をNまたは割合で群ごとに報告。複数群はセミコロンで区切る。
    * 一方のみ記載の場合は総Nから推定してよい。
  * 例:
    * EP: M 62%; F 38%
    * SCZ: F 221 (36.8%); M 380 (63.2%)


#### CAA-6. Deviation Metric
  * 抽出基準: ノルムからの逸脱の定量化方法
  * 抽出スタイル: ACRSL_Style
  * 注:
    * 個人レベルの逸脱を測る指標を記述。
    * 異常/極値の閾値があれば記載。
    * 複数指標や複合スコアがあれば注記。
  * 例:
    * Z-score (|z| ≥ 2 as extreme)
    * Centile score (e.g., <1% or >99%)
    * Total outlier count


#### CAA-7. Association Analysis
  * 抽出基準: 逸脱と臨床変数を結び付ける統計解析（何をどの程度結び付けたかの要約）
  * 抽出スタイル: A_Style
  * 注:
    * 逸脱と臨床アウトカムを関連付ける統計手法を記述。
    * 解析種類（t検定、回帰、分類など）、調整共変量、多重比較補正を含める。
    * 症状重症度、診断、予後、その他臨床指標との関連を検討しているか記載。
  * 例:
    * Predictive performance metrics; group comparisons (ASD vs HC)
    * Welch's t-tests; multivariable regression (adjusted for age, sex)
    * Group comparisons of deviations (t-tests with FDR); Mann–Whitney U for extreme deviation proportions; multi-view sparse CCA (msCCA) linking symptoms to multimodal deviations
    * Linear mixed effects (site/sex/age/FD/Euler); Spearman correlations with ADOS, SRS, SCQ, AQ, FIQ
    * Multiple linear regressions on centile scores with covariates (age, Euler); FDR; spin permutation for spatial correlations; sex-stratified effects; age-by-diagnosis interaction
    * Bootstrapped non-parametric Welch's ANOVA + permutation pairwise tests
    * Logistic regression with ridge; AUC for prediction; Mann–Whitney U with FDR for regional outlier proportions
    * Multi-view CCA; diagnostic/prognostic classification (BACC/SPE/SEN/AUC); comparison to normative/deep/classical ML baselines
    * Region-wise AUC with permutation + FDR

#### CAA-8. Key Findings Brief
  * 抽出基準: 主結果の1文要約（著者の結論含む）
  * 抽出スタイル: A_Style

#### CAA-9. Key Findings Detailed
  * 抽出基準: 数値を含む詳細な結果要約（1パラグラフ程度）
  * 抽出スタイル: A_Style

#### CAA-10. Key Limitations
  * 抽出基準: 主要な限界の簡潔な要約
  * 抽出スタイル: ASL_Style

#### CAA-11. Application Notes
  * 抽出基準: 応用/解析段階でのその他の特筆事項や工夫
  * 抽出スタイル: A_Style
  * 注:
    * 他で扱っていない解析上の工夫や注意点（例: サブグループ層別、キャリブレーションの工夫、解釈可能性の手法など）を簡潔に記す。


### GN. General Note Part
  
#### GN-1. General Note  
  * 抽出基準: 上記でカバーされない重要事項
  * 抽出スタイル: A_Style
  

-------------------

## 基本的な評価原則

抽出項目 `RCI-1` ～ `RCI-5`, `RCI-7`, `NM-3` ～ `NM-11`, `CAA-1` ～ `CAA-6`（ただし `CAA-10` を除く）では、以下のルールに従って回答を構成し、"ACRSL_Style" を返してください。`NM-1`, `RCI-8`, `RCI-10` は "ADCSL_Style" で、専用フォーマットに従うこと。

### ACRSL_Style のフォーマット

項目が ACRSL_Style を要求する場合、以下を構造化して提供:

1. **Answer**: 抽出基準に沿った情報。

2. **Confidence Rating**: "High" / "Medium" / "Low" で信頼度を評価。
   - **High**: テキストに明示、数値が直接ある、曖昧さがない。
   - **Medium**: 補足や表、他論文参照など間接情報、計算や推論が必要。
   - **Low**: あいまい/不足/矛盾で不確実、仮定に依存。

3. **Reason**: その回答と信頼度に至った過程をステップで説明。利用した情報、解釈、計算（百分率やプールドSDなど）を簡潔に述べる。

4. **Supporting Text**: 回答を裏付ける原文引用（簡潔）。省略は引用符と "..." を用いる。

5. **Location**: 引用箇所。できるだけ具体的に。
   - 形式: "FileName: Section / Subsection / Location"
   - 例: "Bedford2025.pdf.md: Sample and Datasets, Paragraph 3"

### ACRSL_Style の特別ケース

- **適用外の場合**: Answer を "NA"。Reasonで理由を説明。Supporting Text と Location は "-"。

- **未報告の場合**:
  - Answer で欠損を明示（例: "mean NR; sd NR" や "Unknown"）または "Partial: [得られた部分]"。
  - 適切な confidence を付与。Supporting Text がなければ "-" とし、Reason で探索経路を説明。

### ADCSL_Style のフォーマット

項目が ADCSL_Style を要求する場合、以下を構造化:

1. **Answer**: 抽出基準に沿った（多くはカテゴリ的）回答。

2. **Detail**: 項目で求められる簡潔な構造化詳細。
   - `NM-1`: Pre-trained なら事前学習データセット規模/出所（N, サイト数）、事前学習手法、主要共変量、元論文/リソース、当該研究での適応/キャリブレーション。Answer = New なら "-"。
   - `RCI-8`: QC指標/閾値/除外数・割合、手動/自動、QCを共変量に使ったか、感度解析の有無。Answer = No なら "-"。
   - `RCI-10`: 方法名、バッチ変数、保持共変量、インサンプル/アウトサンプル、転移学習/キャリブレーション、代替法との比較、ハーモナイゼーション有効性の検証。Answer = None なら "-"。

3. **Confidence Rating**: "High" / "Medium" / "Low"。

4. **Supporting Text**: 回答を裏付ける直接引用（簡潔）。

5. **Location**: 出典の所在。
   - 形式: "FileName: Section / Subsection / Location"

### ASL_Style のフォーマット

ASL_Style を要求する項目では:

1. **Answer**: 抽出基準に沿った回答。
2. **Supporting Text**: 簡潔な直接引用。
3. **Location**: 引用箇所（形式: "FileName: Section / Subsection / Location"）。

`RCI-6` と `CAA-10` に使用。

### A_Style のフォーマット

`SI-1` ～ `SI-5`, `SC-1` ～ `SC-3`, `CAA-7`, `CAA-8`, `CAA-9`, `CAA-11`, `GN-1` は Answer のみ。confidence, reason, supporting_text, location は不要。

### 例: ACRSL_Style
```json
{
  "rci2_hc_n": {
    "answer": "569",
    "confidence_rating": "High",
    "reason": "The manuscript states they used 569 controls for development and performance testing of the models.",
    "supporting_text": "For this study, we used 569 controls for development and performance testing of the models, out of which 470 were male.",
    "location": "materials/Bayer2022.pdf.md:L125-L129"
  }
}
```

### 例: A_Style
```json
{
  "rci8_quality_checking": "Yes"
}
``` 

### 例: ASL_Style
```json
{
  "rci6_analysis_level": {
    "answer": "ROI-level",
    "supporting_text": "\"ROI-wise cortical thickness was computed...\"",
    "location": "Paper2022.pdf.md: Methods / Imaging analysis, L120-L125"
}
}
```

### 例: ADCSL_Style 
```json
{
  "rci8_quality_checking": {
    "answer": "Yes",
    "detail": "Euler number thresholding (median-centered |Euler| > 25) with 4% excluded; FD included as covariate; visual QC performed",
    "confidence_rating": "Medium",
    "supporting_text": "\"Images failing Euler number quality thresholds were excluded (4%) ... Framewise displacement was included as a covariate ... Visual inspection ...\"",
    "location": "materials/Example2023.pdf.md: Methods / Quality control, L145-L168"
}
}
```

### 例: ADCSL_Style 
```json
{
  "rci10_site_effect_handling": {
    "answer": "Model-based",
    "detail": "Hierarchical Bayesian regression with site random effects; compared against ComBat; site and scanner as batch variables; preserved age and sex; out-of-sample evaluation of harmonization effectiveness",
    "confidence_rating": "High",
    "supporting_text": "\"We accounted for site/scanner using a hierarchical Bayesian model with random effects ... preserving age and sex ... we compared to ComBat and evaluated out-of-sample ...\"",
    "location": "materials/Example2024.pdf.md: Methods / Harmonization, L180-L215"
  }
}
```

### 例: ADCSL_Style 
```json
{
  "nm1_model_origin": {
    "answer": "Pre-trained",
    "detail": "Pre-trained BrainChart GAMLSS (N≈75k, multi-site); out-of-sample centiles; local calibration applied",
    "confidence_rating": "High",
    "supporting_text": "\"We applied the pre-trained BrainChart normative model ... and calibrated centiles to the local cohort ...\"",
    "location": "materials/Bethlehem2022.pdf.md: Methods / Normative modeling, L210-L224"
  }
}
```

-------------------

## 重要なキーワード定義
- **Explained Variance (EV)**: モデルがデータの中心傾向をどれだけ捉えるか。1に近いほど良好。
- **Mean Standardized Log-Loss (MSLL)**: 中心傾向と分散の適合度を評価。より負の値が良好。
- **Skewness**: モデルが算出した逸脱スコア（zスコア）の分布形状を評価。0に近いのが望ましい。
- **Kurtosis**: 逸脱スコア分布の形状を評価。0が望ましい。0より大きいと裾が重い。
- **Pearson Correlation Coefficient (RHO)**: 観測値と予測値の線形関係の強さ。1に近いほど良好。
- **dMRI**: Diffusion-weighted MRI（拡散強調MRI）。
- **sMRI**: Structural MRI（構造MRI）。例: T1 weighted MRI, T2 weighted MRI。
- **fMRI**: Functional MRI。
- **GAMLSS**: Generalized Additive Models for Location, Scale and Shape。平均だけでなく分散・形状パラメータも共変量の関数としてモデリングできる枠組み。例: log σ ~ s(age) で年齢に伴う分散変化をスプラインで推定。
- **HBR**: Hierarchical Bayesian Regression。階層構造とランダム効果を含むベイズ回帰。例: サイト別スケール（ランダム効果）でサイト差を推定。
- **GPR**: Gaussian Process Regression。
- **Nuisance Structure (NS)**: 研究の主目的ではないが系統的に観測に影響する要因。平均シフト、分散差、相関構造、非線形性などの入り方を含む。NM/神経画像での例: サイト/スキャナ/プロトコル差（加算/乗算、階層分散）、画像品質/モーション/SNR/QC指標（測定誤差や外れ値）、年齢の非線形性、性別・ICV（主目的でなければ不要構造）、縦断/家族/クラスター構造（被験者/サイト内相関）、時期/バッチ/オペレータ差（ドリフト、バッチ効果）、ヘテロスケダスティシティ（年齢/サイトで分散が変化）、時空間の自己相関、欠測バイアス。
- **Locally Estimated Scatterplot Smoothing (LOESS)**: 局所加重回帰/局所多項式回帰とも呼ばれる近似法。
-------------------

## 抽出結果の出力
- ファイル形式
  - JSON。`./DE_Author20XX_by_Someone_YYYYmmddHHMMSS.json` の構造に合わせる。
- ファイル名
  - Claude Code の場合: `DE_Bethlehem2022_by_claude_202509191115.json`（パターン: `DE_AuthorYear_by_claude_YYYYMMDDhhdd.ext`）。
  - Gemini CLI の場合: `DE_Bethlehem2022_by_gemini_202509191115.json`（パターン: `DE_AuthorYear_by_gemini_YYYYMMDDhhdd.ext`）。
  - Codex-CLI の場合: `DE_Bethlehem2022_by_codex_202509191115.json`（パターン: `DE_AuthorYear_by_codex_YYYYMMDDhhdd.ext`）。
  - ファイル名はASCII、空白なし。

- JSONフォーマット
  - キーは指定通り、item IDは snake_case。
  - ACRSL_Style の Answer は混在内容なら文字列。純粋な数値は数値/文字列いずれでもよいが、ファイル内で一貫させる。
  - 末尾カンマ禁止、有効なJSONにする。
