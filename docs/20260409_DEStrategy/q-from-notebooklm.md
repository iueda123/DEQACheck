[INDEX](./INDEX.md)

*docs/20260409_DEStrategy/q-from-notebooklm.md*

**━━━━━━━━━━━━━━━━━━━━━━━━**

# Q from NotebookLM

`share_package/data/*/notes/WithNotebookLM.json` から `q` のみを抽出し、完全一致の重複を除外した一覧です。

## データセット構成・名称

1. この研究では何種のデータセットが用いられているか？
2. 各々のデータセットは何の目的（train, validation, test, transfer for clinical research, patient for clinical research など）に用いられているか？
3. この研究で用いられているデータセットはABDE I ですか？ABDE IIですか？それとも両方ですか？
4. この研究ではIXIデータセットは用いていますか？
5. IXIデータセットのフルスペルを教えてください。
6. Discoveryコホートとして HCP Psychosisを使っていますが、このデータセットは正常健常者の皮質圧の正規範囲をモデリングする目的では使われていないと考えてよいですか？
7. UTRECHTというデータセットについて説明してください。
8. UTRECHTデータセットの正式名称は？
9. この研究では CNP というデータセットが使われていますか？UCLAというデータセットは使われていますか？
10. KQJHというデータセットを用いていると思いますが、KQJHとは何の略ですか？
11. この研究ではHCP-Bのデータセットは用いられているか？
12. Baby Connectome Project のデータセットは使われているか？

## 規範モデル用サンプルの人数・年齢・性別

1. Normative model構築に用いた健常者データセットのN数について教えてください。Overall (trainだけでなくvalidationやtestも含めた) 段階と、train段階を区別して答えてください。
2. Normative model構築時の健常者データセットの年齢に関するmean, sd, median, iqr, min, maxを教えてください。Overall (trainだけでなくvalidationやtestも含めた) 段階と、train段階を区別して答えてください。もし本文に明記されていない場合は、meanとsdに関しては weighted mean of ages、pooled sd of ages の算出を試みてください。その他統計値は文脈から推定を試みてください。
3. Normative model構築時の健常者データセットの男女各々のN数および比率（%）について教えてください。Overall (trainだけでなくvalidationやtestも含めた) 段階と、train段階を区別して答えてください。
4. この研究ではNormative Modeling時に何人の被験者情報を用いていますか？
5. First, we estimated individualized gray matter morphological abnormalities using the normative modeling. とありますが、このときに用いだデータセットの名前と人数を教えてください。

## 疾患群・適用対象

1. この検討NMを使って解析対象となった疾患を列挙してください。
2. 自閉症とADHDの併存 (Co-occurring Autism and ADHD / Autism+ADHD) の年齢情報と性別情報をください。
3. この研究ではどのような疾患群に規範モデルを適用しているか？
4. それぞれの疾患群のN数はわかるか？
5. それぞれの疾患群の男女比（数）はわかるか？
6. それぞれの疾患群の年齢の平均、標準偏差、最大値、最小値はわかるか？
7. それぞれの男女比（数）はわかりますか？
8. アルツハイマー病スペクトラム（ADS）患者群のより詳細な内訳や男性、女性がそれぞれ何名であったかの情報は書かれていますか？

## Site・Scanner・Harmonization

1. この研究ではnormative model構築時、site effectはどのように取り扱っているか？
2. この研究ではsite effectsの扱いはどうなっていますか？
3. site effects は Normative Model構築段階ではなく、構築後の統計解析で考慮したという理解で良いですか？
4. この研究ではNormative Model構築時、site/scanner影響はどのように考慮されていますか？
5. この研究ではnormative model構築時、施設やスキャナ影響をどのように排除していますか？
6. この研究ではnormative modelingに先立ち、ComBatなどの site, scanner, cohort/study の違いに由来する値の変動を緩和する処理は施されていますか？
7. この研究ではnormative modeling時にsiteは説明変数として組み込まれていますか？
8. この研究ではnormative modeling時に説明変数としてsiteを組み込んでいますか？
9. 説明変数としてモデルにsite情報を組み込んでいないが、組み込んでいるという認識で良いですか？そのような組み込み方は数学的に何と呼ばれますか？
10. "To transfer the normative models to new sites, healthy controls from the new data set are used to learn site effects." とのことですが、どういうことですか？Normative modeling時の説明変数にsite情報を組み込んだということではないですよね？
11. この研究ではnormative modeling構築時に Site を説明変数として組み込んでいますか？
12. この研究でnormative modeling時にsite/scannerは説明変数として用いられていますか？
13. この研究ではnormative modelingの前または後に、ComBatなどの site, scanner, cohort/study の違いに由来する値の変動を緩和する処理は施されていますか？

## 説明変数・共変量

1. この研究ではnormative modeling時に何を説明変数に用いていますか？
2. この研究ではnormative modeling時にhigher-order age terms (degree ≥3)を説明変数に用いていますか？
3. この研究ではnormative modeling時にCohort/Study indicatorを説明変数に用いていますか？
4. この研究では normative modeling 時に何を説明変数として組み込んでいますか？
5. この研究では normative modeling 時に family ID or subject ID random effectを説明変数として組み込んでいますか？
6. この研究でnormative modeling時に説明変数として用いたものは何ですか？
7. この研究ではnormative modeling時に何を説明変数に組み込んでいますか？
8. この研究でnormative modeling時に説明変数として組み込まれているものを教えてください。
9. この研究ではnormative modeling時に何を説明変数に設定していますか？
10. この研究でnormative modeling時に説明変数として用いられたものを教えてください。
11. この研究でnormative modeling時に説明変数として設定されたものを教えてください。
12. この研究でnormative modeling時に説明変数としてnon-integer age terms (fractional/negative powers)や higher-order age terms (degree ≥3) は組み入れられましたか？
13. この研究ではnormative moeling時に何を説明変数に用いていますか？
14. 男女別にモデルを構築したのではないですか？
15. だとすれば、この研究ではnormative moeling時にsexは説明変数に用いていないと言えませんか？
16. この研究ではnormative modeling時に site, scanner, Cohort/Study indicator のような説明変数をモデルに組み込んでいますか？
17. この研究ではnormative modeling時にsexを説明変数として組み込んでいますか？
18. この研究で、normative modeling時の説明変数としてICVは組み込まれていますか？
19. この研究ではnormative modeling時にAge^2を説明変数に設定していますか？
20. この研究において normative modeling 時に説明変数として設定されたものは何ですか？
21. この研究ではnormative modeling時に何を共変量に設定していますか？
22. この研究ではnormative modeling 時に説明変数としてどのようなものを組み込んでいますか？
23. この研究ではnormative modeling 時に説明変数としてsex^2 や sex^3 は組み込んでいますか？
24. この研究ではnormative modeling 時に説明変数としてTIVは組み込んでいますか？
25. この研究ではnormative modeling構築時に higher-order age terms (degree ≥3) を説明変数として組み込んでいますか？
26. この研究でnormative modeling時に説明変数として用いられているものは何ですか？
27. この研究ではnormative modeling構築時に TIV を説明変数として組み込んでいますか？

## 目的変数・特徴量

1. この研究でnormative modeling時に laterality indexは目的変数として用いられていますか？
2. この研究でnormative modeling時に目的変数に設定されているものは何か？
3. この研究でnormative modeling時に目的変数として用いられたものを教えてください。
4. resting state fMRI 由来のものですか？
5. この研究ではnormative modeling時に何を目的変数に設定していますか？
6. この研究ではnormative modeling時にネットワーク特徴量を目的変数に設定していると言えますか？
7. この研究ではnormative modeling時の目的変数としてwhite matter volumeを設定していますか？
8. この研究ではnormative modeling時の目的変数として何が設定されていますか？
9. この研究でnormative modeling時に目的変数に設定された変数は何ですか？
10. この研究ではnormative modeling時に何を目的変数として設定していますか？
11. この研究ではnormative modeling時に SUVR-amyloid と  SUVR-tau を目的変数として設定していますか？
12. この研究でnormative modeling時に目的変数として設定されたものは何ですか？
13. この研究において normative modeling 時にTotal GM Volume は目的変数として設定されましたか？
14. この研究でnormative modeling時に目的変数に設定されたものは何ですか？
15. この研究でnormative modeling時に目的変数に設定されたものはrs-fMRI 由来の FCではないですよね？
16. この文献中に「the volume of 36 neuroanatomical structures」とありますが、具体的にはこの36構造は何ですか？
17. この研究ではlaterality indexについてnormative modelingをしていますか？
18. この研究ではnormative modeling構築時に何を目的変数としていますか？

## モデリング手法・アルゴリズム分類

1. Specify the statistical/machine learning algorithm(s) used for normative modeling.
2. はい、この研究では**ベイズ線形回帰（Bayesian Linear Regression, BLR）**が用いられています,。  ただし、BLRは本研究で提案されている新しい手法（$N^3$）そのものではなく、**比較対象（ベンチマーク）としての「従来型のノーマティブ・モデリング」**を構築するために使用されています,。  詳細は以下の通りです。  ### 1. BLRの使用目的 この研究の主な目的は、新しく提案された「Nearest Neighbor Normativity（$N^3$）」というフレームワークの有効性を検証することです,。その性能を評価するために、既存の標準的な手法である**従来型のノーマティブ・モデリング**と比較を行っています。この従来型モデルの構築において、BLRが採用されました。  ### 2. BLRを用いた解析の詳細 *   **ツールキット:** Predictive Clinical Neuroscience toolkit（Rutherfordらによって記述されたもの）を使用して計算されています。 *   **モデルの設定:** デフォルトのパラメータを用いて、**ベイズ線形回帰（BLR）モデル**が適合（フィット）されました,。 *   **入力データ:** 灰白質（GM）、白質（WM）、白質高信号域（WMH）、脳脊髄液（CSF）の各ボリュームを全脳容積（TIV）で正規化したデータが使用されました。 *   **算出された指標:** BLRモデルから導出された各変数の**zスコア**を基に、以下の2つの指標が作成されました。     *   **NM-S:** zスコアの絶対値の合計。     *   **NM-C:** マグニチュードが±1.96を超えるzスコアの数。  ### 3. 解析における役割 研究の結果、提案手法である$N^3$マーカーは、この**BLRベースの従来型モデル（NM-SおよびNM-C）**や、脳年齢（Brain Age Gap）モデルよりも、神経変性疾患の検出において高い統計的説明力と予測精度を示すことが確認されています,,。  --- **補足：** この論文で最も強調されている「$N^3$」手法自体は、BLRではなく、**Nearest-Neighbor（最近傍）アルゴリズム**を用いた**局所密度推定（Local Density Estimation）**に基づいています,。
3. この研究ではnormative modeling時に何という手法が用いられていますか？
4. この研究でnormative modelingに用いられている手法は何ですか？
5. この研究でnormative modelingに用いられている手法は quantile regression (分位点回帰でパーセンタイルを推定) する手法と言えますか？
6. この研究ではnormative modeling時に何という手法を用いていますか？
7. この研究ではnormative modeling時に何と呼ばれる手法を用いていますか？
8. CentailBrainの出処は Ge et al., 2024 であり、その中では MFPR; OLSR; BLR; GAMLSS; GPR; WBLR; HBR の手法が試されていると思います。Gimbel2025ではその中でもとくに multivariate fractional polynomial regression (MFPR) に基づく normative modeling をしているという認識で合っていますか？
9. CentailBrainの出処は Ge et al., 2024 であり、その中では MFPR; OLSR; BLR; GAMLSS; GPR; WBLR; HBR の手法が試されていると思います。Haas2024ではその中でも特に multivariate fractional polynomial regression (MFPR) に基づく normative modeling をしているという認識で合っていますか？
10. この研究でnormative modelingの軌跡を描くのに用いた手法を教えてください。
11. この研究で用いられた normative modeling 手法は Parametric Regの一種の ordinary least squares regression だと言えますか？
12. この研究で用いられた normative modeling 手法は Parametric Regの一種だと言えますか？
13. この研究で用いられた normative modeling 手法は Nonparametric Interval Estimation の一種だと言えますか？
14. この研究でNormative modelingに用いられた手法を教えてください。
15. この研究ではnormative modeling時に何という手法を用いているか？
16. この研究ではnormative modeling時に polynomial regression を用いていますか？
17. この研究でnormative modeling時に用いられている手法は何ですか？
18. この研究ではnormative modeling時にpolynomial regressionを用いていますか？
19. この研究ではnormative modeling手法としてBayesian Linear Regression (BLR)は用いられていますか？
20. この研究でnormative modelingに用いられている手法を教えてください。
21. この研究でnormative modeling時に用いられている手法はpolynomial regression ですか？

## モデルの由来・検証・前処理

1. 著者たちは自前で画像を処理していますか？
2. この研究について「 Internal validation based on data partitioning is used (e.g. hold-out with a train-test split, K-fold CV, LOOCV), using samples that were not used to fit the model」は当てはまりますか？
3. この研究で用いられているnormative modelはこの研究内で構築されたものですか？それとも先行研究で既に構築されたものを流用していますか？

**━━━━━━━━━━━━━━━━━━━━━━━━**

*docs/20260409_DEStrategy/q-from-notebooklm.md*

[INDEX](./INDEX.md)
