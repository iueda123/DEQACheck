# Keywords for Normalization: Explanatory Variables

Version: 202601014

## 高松案の確認

122のNormative Modeling (NM) 研究における Explanatory Variables について、
高松案（Table 1 のExplanatory Variables列）として、以下の32個のキーワードで表現することが提案された。

| Major Category        | Minor Category               | Full Spelling                    | 
|-----------------------|------------------------------|----------------------------------|
| Age-related           | Age                          |                                  |
| Age-related           | Age²                         | Age squared                      |
| Age-related           | Age (polynomial)             |                                  |
| Age-related           | Age (fractional polynomials) |                                  |
| Age-related           | s(age)                       | Smooth function of age           |
| Demographics          | Sex                          |                                  |
| Demographics          | Race                         |                                  |
| Demographics          | Ethnic background            |                                  |
| Demographics          | Education                    |                                  |
| Interactions          | Age×sex / Sex×age            |                                  |
| Site/Scanner          | Site                         |                                  |
| Site/Scanner          | Scanner                      |                                  |
| Site/Scanner          | Scanner vendor               |                                  |
| Site/Scanner          | Magnetic field strength      |                                  |
| Site/Scanner          | FreeSurfer version           |                                  |
| Site/Scanner          | Scanning protocol            |                                  |
| Site/Scanner          | Acquisition/task parameters  |                                  |
| Global brain measures | ICV                          | Intracranial volume              |
| Global brain measures | TIV                          | Total intracranial volume        |
| Global brain measures | Total brain volume           |                                  |
| Global brain measures | Mean CT                      | Mean cortical thickness          |
| Global brain measures | Mean SA                      | Mean surface area                |
| Image/Data quality    | Euler number                 |                                  |
| Image/Data quality    | Image quality                |                                  |
| Image/Data quality    | Mean FD                      | Mean framewise displacement      |
| Image/Data quality    | Mean relative motion         |                                  |
| Image/Data quality    | Head motion                  |                                  |
| Other                 | Hemisphere                   |                                  |
| Other                 | BMI                          | Body mass index                  |
| Other                 | FIQ                          | Full-scale intelligence quotient |
| Other                 | Task performance             |                                  |
| Other                 | None                         |                                  |

## 不足キーワード調査

続いて、 上田が、これらに当てはまらないようなキーワードがないかを再考した。 方法としては、

`share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_codex_<ProcessedTime>.json`,
`share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_claude_<ProcessedTime>.json`,
`share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_gemini_<ProcessedTime>.json`:

というファイルの
"normative_modeling_part/nm5_predictor_variables/answer"
要素の値をキーワードに置き換えて正規化しようとしたときに、
上記32キーワードでは足りないものがないかを調べた。
その結果以下が挙げられた。

- 対象: `share_package/data/*/DE/json/DE_*_by_{codex,claude,gemini}_*.json` を走査。
- 正規化: Age 系の多項式・スプライン表記、Sex/Gender、Site/Scanner/Batch、ICV/TIV/Total brain volume、Mean CT/SA、QC 指標（Euler
  number、FD、Head motion 等）、Task performance、None などは32キーワードに吸収（大文字小文字差や説明文付きも含む）。
- 上記で吸収できなかった 28 件の研究についてそのExplanatory Variablesの内容で束ねたところ、以下のキーワードが現行32個には無い。

| Minor Category                            | Full Spelling                                | Description                            | Example                                                                   |
|-------------------------------------------|----------------------------------------------|----------------------------------------|---------------------------------------------------------------------------|
| Diagnostic/Clinical group                 | Clinical diagnosis / group indicator         | 群比較やハーモナイズ時に診断や群ラベルを共変量に含めるケース         | `Diagnosis`, `Group (CHR-P/HC)`, `Diagnosis (DX)`                         |
| Preprocessing pipeline / software version | Preprocessing pipeline or software version   | 前処理パイプラインやソフトウェア版を固定効果として扱う記述          | `Preprocessing pipeline/software version (fixed effect, five categories)` |
| PMA / Postnatal weeks                     | post-menstrual age / postnatal weeks at scan | 新生児研究で出生後週齢を年齢表現として用いるケース              | `PMA at scan (weeks)`                                                     |
| Family / subject-level random effect      | Family ID or Subject ID random effect        | 家族ID・被験者IDをランダム効果に入れて階層構造をモデル化する記述     | `Family (random intercept)`, `Subject ID (random effect in GAMM)`         |
| Task / acquisition counts                 | Counts of blocks, stimuli, or volumes        | タスクや取得回数（ブロック数・刺激数・ボリューム数など）を共変量に含める記述 | `Number of target blocks`, `Target stimuli`, `Number of volumes`          |
| Cohort / study indicator                  | Cohort or study indicator                    | サイト以外のコホート名や Study を効果として扱う記述          | `Cohort (as random effect)`, `Study`                                      |
| Others                                    | others not specified                         | 該当キーワード選択不能                            | `Yes`, `-`, `others not specified.`                                       |

## Age-related Variablesの見直し

Age-related variables用category wordsの使い分けを明確にする。

**高松案**

注：Descriptionについて明確な記載はなかったが、使われ方から推測して記述した。

| Minor Category               | Full Spelling          | Description                                                                                                                          |
|------------------------------|------------------------|--------------------------------------------------------------------------------------------------------------------------------------|
| Age                          |                        | age の1乗項が使われている場合は `age` を付与する。                                                                                    |
| Age²                         | Age squared            | age の2乗項が使われている場合は `age²` を付与する。線形の age とセットで投入されている論文も多いが、その場合もキーワードとしては `Age` と `Age²` の2つを記録する。                                   |
| Age (polynomial)             |                        | 3次以上の多項式（例: age, age², age³）を含む場合は `Age (polynomial)` にまとめる。明示的に次数が書かれていなくても「polynomial expansion of age」といった記述があればこのキーワードを採用する。     |
| Age (fractional polynomials) |                        | 分数指数を含む多項式（例: age^0.5, age^-1 など）は `Age (fractional polynomials)` に統一する。FP2, FP3 のような表記がある研究にもこのキーワードを付与する。                          |
| s(age)                       | Smooth function of age | GAM/GAMLSS などで spline として age を平滑化する場合は `s(age)` にマッピングする。単に「nonlinear age effect」と記載されている場合でも spline/smooth と明記されていれば `s(age)` を選ぶ。 |

### Age (polynominal) について

Romascano2024に対して、高松案では「Age (polynomial)」というキーワードが付与されている。

`share_package/data/Romascano2024/materials/optimized/Romascano2024.pdf.md` 
では explanatory variables について以下のように記載されている（原文そのまま）:

- 「In brief, low order polynomials were fitted to the 100 resamples of the SBA metrics of our HCs as a function of age.」
- 「Covariates other than age (i.e. sex, scanner and scanning protocol) were accounted for by selecting matched subgroups before computing statistics.」

ここからわかるのは、説明変数は年齢だけで、年齢に対して「低次数多項式」を当てている。モデル形はシンプルに
- 各指標 y(age) に対して y = β0 + β1·age + β2·age^2 + … + βk·age^k をフィットする多項式回帰。
- k を 0 から順に上げ、残差分散の減少が有意でなくなるまで（nested F test）次数を増やす。
- 過学習防止のため上限次数を odd number 2*floor(ln(n/10)+1)-1 に設定（n はサンプル数）。OASIS3 の例では max degree 11。
- 1,927 HC の 597 指標について、年齢ヒストグラムを 10 bin にして均等サンプリングした 100 resample ごとに上の手順を実施し、fit を平均して age トレンドと予測区間を算出。
- 各 resample のフィット前後に IQR ベースの外れ値除去を実施。

明示的な多項式の次数や係数は書かれていないが、上記のとおり「年齢のみを説明変数とした低次数多項式回帰」で、次数選択は統計的検定＋次数上限で決める、という仕様。

### Age (fractional polynomials)

Georgiadis2025に対して、高松案では「Age（fractional polynomials）」というキーワードが付与されている。

リポジトリ上では Georgiadis2025（`share_package/data/Georgiadis2024/materials/optimized/Georgiadis2024.pdf.md`)には
explanatory variables について以下のように記載されている（原文そのまま）:

- 「To model the effects of age on retinal thickness, which are known to be non-linear 23,38 , 
  we used fractional polynomials which have been shown to provide an accurate method to model nonlinear effects 22,30. 
  Image quality and reported ethnic background were also included as covariates, 
  since they have been shown  to influence retinal parameters 38.」
- 「Although cardiovascular risk factors are also well known to have an effect on retinal thickness, 
  most previous studies on retinal thickness deviations in patients with mental disorders 
  did not have access to the phenotyping depth of the UKBB, and did not take them into account 10-19 
  Thus, we include cardiovascular risk factors and other covariates in our robustness analysis (see Supplementary Results), 
  and not in our main analysis in order to be able to compare our main findings with the existing literature.」

上記は GAMLSS で age を fractional polynomials として扱う設定で、典型的には
`y = β0 + β1·age^p1 + β2·age^p2 + γ·covariates` の形を採用し、
指数 p1, p2 を {-2, -1, -0.5, 0, 0.5, 1, 2, 3} の候補から探索して非線形性を吸収する。
共変量には image quality と ethnic background を固定効果として入れ、
BMI・喫煙・飲酒・糖尿病・SES などの心血管リスク要因はメイン解析から外し、
ロバストネス解析で追加する設計になっている。

### 項の次数に特化した表現にする

現行の高松案カテゴリキーワードには「項数」という軸と「項の次数」という評価軸が混在し、
的確にexplanatory variablesを正規化できていない印象を与えかねない。
「項数」という軸は付与したキーワード数で示すとして、「項の次数」という軸についてキーワードを割り振るのが良いのではないか。

以下のような場合を的確に表現すべく、Age, Age^2, Age higher-order, Age non-integer というキーワードはどうだろうか。

* `y ~ age + other_terms`
* `y ~ age^2 + other_terms`
* `y ~ age + age^2 + other_terms` :
* `y ~ age + age^2 + age^3 + other_terms`: liner term, quadratic term, higher-order term(s)
* `y ~ age^{-1} + other_terms`
* `y ~ age + age^{-1} + other_terms`

| Minor Category   | Full Spelling          | Description                                     |
|------------------|------------------------|-------------------------------------------------|
| Age              | linear age term        | 一次項。例：age                                 |
| Age²             | quadratic age term     | 二次項。 例：age^2                              |
| Age higher-order | higher-order age terms | 三次以上の整数冪。例：age^3, age^4              |
| Age non-integer  | non-integer age terms  | 分数冪・負の冪。例：age^{-1}、age^{0.5}        |

**留意事項**
「y ~ x + x^2 + x^{-1}」 は数学的に多項式と呼ばないとのこと（負の冪項をもつため）。 
「y ~ x^2」に対して「y ~ x + x^2 + x^{-1}」を何と呼ぶかについて正しく表現するならば、 
non-polynomial model, nonlinear regression model, a regression model including linear, quadratic, and inverse terms など。

### "s(age)" というキーワードは廃止する

Age　と s(age) を同一列内に表現することに関して見直しを提案する。
現状だと、Explanatory-Variables に関する情報列に、 spline方法に関する情報が混在している印象を受ける。
Ageは単なる説明変数名であるが、 s(age)はGAMなどでスプライン平滑化をかけた「項」の表記で、
変数自体というよりモデリング手法・関数形の情報である。
そのため「Explanatory-Variables」欄に両者を並べると、変数リストと手法の情報が混ざって見える。
age以外の変数でスプライン平滑化を行っている研究がないのであれば、`s(age)` は廃止し、スプライン平滑化情報は、
モデリング手法としてGAM系を使っている場合の補足情報として別途記載が良いのではないかと考える。

------

## Category Keywords for Explanatory Variables 改定案

