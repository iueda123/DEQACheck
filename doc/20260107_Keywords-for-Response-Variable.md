# Keywords for Normalization: Response Variable

Version: 20260128

## 再検討の記録

122のNormative Modeling (NM) 研究における Response variableについて、
高松案として、以下の16個のキーワードで表現することが提案された。

- ALFF: amplitude of low-frequency fluctuations（raw low-frequency power）
- CT: cortical thickness
- CV: cortical volume
- FA: fractional anisotropy
- fALFF: fractional amplitude of low-frequency fluctuations（low-frequency power / total power）
- FC: functional connectivity
- FD: framewise displacement
- GBV: global brain volume
- GCOR: global correlation
- GMV: gray matter volume
- LCOR: local correlation
- NMF: Nonnegative Matrix Factorization
- rs-FC: resting-state functional connectivity
- SA: surface area
- SV: surface volume
- WMV: white matter volume

続いて、 上田が、これらに当てはまらないようなキーワードがないかを再考した。
方法としては、
`share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_codex_<ProcessedTime>.json`,
`share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_claude_<ProcessedTime>.json`,
`share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_gemini_<ProcessedTime>.json`
というファイルの
"normative_modeling_part/nm4_response_variable/answer"
要素の値をキーワードに置き換えて正規化しようとしたときに、
上記16キーワードでは足りないものがないかを考えた。
その結果以下が挙げられた。

- 対象: codex/claude/gemini 375件の `nm4_response_variable/answer` を確認（回答ユニーク349種類）。
- 16キーワード外で頻出・未カバーのカテゴリ例:
    - DTI系: MD/RD/AD、GFA、FAt、FW、束プロファイル（shape+FA/MD/RD/AD）。
    - Laterality: GM/WM/CT/GMV の左右非対称・LI。
    - ネットワーク指標: degree centrality、Functional Connectivity Strength (FCS)、aFCS/fFCS、network fluctuation
      characteristic、FC gradient。
    - 変形ベース形態: Jacobian determinants (DBM/TBM)、VQ-VAE 典型性スコア。
    - Task fMRI 活性: GLM z-map（faces>shapes などコントラスト）。
    - PET/SPECT: BPND、Ki_cer（ドーパミン合成）、SUVR（アミロイド/タウ）。
    - 形態類似・折り畳み: morphometric similarity index、PBSI-SW（溝幅類似度）、gyrification/curvature 系。
    - MEG/EEG: PSD（帯域パワー）、AEC/PEC など周波数別 FC。
    - 眼科計測: 黄斑/網膜厚、RNFL、GC-IPL。
    - 特定ROI体積: 小脳葉・視床核、海馬、脳梁形態（面積/長さ/周長など）。
    - グローバル組織量: WMH、CSF、TIV/ICV など（GMV/WMV以外）。
    - 混合IDPセット: 2000+ IDPや多組織スコア（単一キーワードでは括りにくい）。
- 補足: `share_package/data/Feng2024/DE/json/DE_Feng2024_by_gemini_20251210071754.json` は answer 欄が空。`Yes`
  だけの回答も1件あり要確認。

## Keywords for Normalization

以上のような検討を経て、以下のようなCategoryキーワードを用いた整理方法を考えている。
テーブルの下には各Minor Categoryにどの文献が該当するかの簡易調査結果がまとめてある。

| Major Category | Minor Category   | Full Spelling                                             | Description                         | Example                         | 
|----------------|------------------|-----------------------------------------------------------|-------------------------------------|---------------------------------|
| sMRI           | CT               | cortical thickness                                        | sMRI, 皮質厚の平均や頂点値                    | 左上側頭回の皮質厚                       |
| sMRI           | CV               | cortical volume                                           | sMRI, 皮質領域の体積                       | 右前頭極の皮質体積                       |
| sMRI           | SA               | surface area                                              | sMRI, 皮質面積（頂点/領域）                   | 左外側後頭葉の面積                       |
| sMRI           | SV               | surface volume                                            | 表面ベースで算出した皮質体積                      | 全皮質の表面体積                        |
| sMRI           | GMV              | gray matter volume                                        | 灰白質体積（領域/全脳）                        | 両側海馬の灰白質体積                      |
| sMRI           | WMV              | white matter volume                                       | 白質体積（領域/全脳）                         | 前頭葉白質体積                         |
| sMRI           | GBV              | global brain volume                                       | 全脳の総体積                              | 全脳容積（灰白質+白質）                    |
| sMRI           | TIV              | total intracranial volume                                 | 頭蓋内容積                               | TIVでスケールした各IDP                  |
| sMRI           | CSF              | cerebrospinal fluid volume                                | 脳脊髄液量                               | 側脳室体積                           |
| sMRI           | SubV             | subcortical volume (regional)                             | 汎用ROIの皮質下体積                         | 扁桃体体積                           |
| sMRI           | CerLV            | cerebellar lobule volume                                  | 小脳葉の体積                              | Crus I体積                        |
| sMRI           | CCMorph          | corpus callosum morphology                                | 脳梁の体積/面積/長さ/周長                      | 脳梁膨大部面積                         |
| sMRI           | CGeom            | cortical geometry                                         | 皮質表面の幾何学的特性（GI, curvature, K/I/S等）  | LGI, K, I, S                    |
| sMRI           | WMH              | white matter hyperintensity                               | 白質高信号量                              | Fazekasスコア、WMH総体積               |
| sMRI           | DDM              | deformation-derived morphometry                           | 変形場のJacobianによる形態指標                 | VBMのJacobian平均                  |
| dMRI           | FA               | fractional anisotropy                                     | 拡散異方性のスカラー指標                        | 上縦束のFA                          |
| dMRI           | GFA              | generalized fractional anisotropy                         | Q-ball等での一般化FA                      | 半球平均のGFA                        |
| dMRI           | FAt              | tissue fractional anisotropy                              | 組織成分に限定したFA                         | CSF補正後FA                        |
| dMRI           | MD               | mean diffusivity                                          | 平均拡散係数                              | 後部内包のMD                         |
| dMRI           | RD               | radial diffusivity                                        | 放射方向拡散係数                            | 前放線冠のRD                         |
| dMRI           | AD               | axial diffusivity                                         | 軸方向拡散係数                             | 鉤状束のAD                          |
| dMRI           | FW               | free water                                                | 自由水成分の割合                            | 側頭葉白質のFW                        |
| fMRI           | LLF-BOLD-metrics | Local low-frequency BOLD fluctuation metrics              | 周波数領域・局所指標                          | ALFF, fALFF, mALFF, zALFF       |
| fMRI           | FC               | functional connectivity                                   | 時系列相関によるFC                          | PCC–mPFCのFC                     |
| fMRI           | rs-FC            | resting-state functional connectivity                     | 安静時fMRIのFC                          | DMN内FC                          |
| fMRI           | dyn-FC           | dynamic functional connectivity                           | 時間変動するFC/変動度                        | スライディングウィンドウFC分散                |
| fMRI           | FC-gradient      | functional connectivity gradient                          | FC行列の勾配座標                           | 主勾配(Gradient 1)スコア              |
| fMRI           | FC-strength      | functional connectivity strength                          | 接続強度の総和                             | mPFCのFCS                        |
| fMRI           | TASK-GLM         | task fMRI general linear model                            | 課題fMRIコントラストのz/tマップ                 | faces>shapesのzマップ               |
| fMRI           | GCor             | global correlation                                        | 全ボクセル平均相関                           | 全脳GCOR                          |
| fMRI           | LCor             | local correlation                                         | 近傍との局所相関                            | 角回のLCOR                         |
| qMRI           | R1               | Quantitative MRI Parameter - R1                           | 1/T1（縦緩和率）の定量指標                     | R1平均（皮質）                        |
| qMRI           | R2*              | Quantitative MRI Parameter - R2 star                      | 1/T2*（有効横緩和率）の定量指標                  | R2*平均（被殻）                       |
| qMRI           | χ                | Quantitative MRI Parameter - chi                          | 磁化率（QSM）指標                          | QSM χ平均                         |
| PET            | SUVR-amyloid     | standardized uptake value ratio (amyloid)                 | アミロイドPETのSUVR                       | AV45 SUVRCER                    |
| PET            | SUVR-tau         | standardized uptake value ratio (tau)                     | タウPETのSUVR                          | FTP SUVRCBL                     |
| PET            | BPND             | binding potential (non-displaceable)                      | PET結合能指標                            | \[11C\]DASBの線条体BPND             |
| PET            | Ki_cer           | influx rate constant (cerebellar ref.)                    | 小脳基準の取り込み率                          | [18F]FDOPA Ki_cer               |
| MEG/EEG        | AEC/PEC          | amplitude/phase envelope correlation                      | MEG/EEGの周波数別FC                      | β帯域AEC                          |
| MEG/EEG        | PSD              | power spectral density                                    | MEG/EEG帯域パワー                        | α帯域PSD                          |
| MEG/EEG        | MEG/EEG-Other    | other MEG/EEG metrics                                     | MEG/EEGのその他指標                       | FFG source-level activation PC1 |
| Mathematical   | MSI              | morphometric similarity index                             | 形態類似度の指標                            | MSI行列の平均                        |
| Mathematical   | PBSI             | person-based similarity index                             | 溝幅類似度の個人指標                          | Sulcal width PBSI               |
| Mathematical   | IDP-set          | imaging derived phenotype set                             | 多数のIDPを束ねたセット                       | UKB 2,000+IDPセット                |
| Mathematical   | VQ-VAE-TS        | vector-quantized variational autoencoder typicality score | VQ-VAEでの典型度スコア                      | 典型度z-score                      |
| Mathematical   | NMF              | non-negative matrix factorization                         | 成分負荷量/混合比                           | NMFコンポーネント重み                    |
| Mathematical   | LI               | laterality index                                          | 左右差の指数                              | 海馬体積のLI                         |
| Mathematical   | NetMes           | network measures                                          | DC (degree centrality) などのネットワーク特徴量 | 視床のDC                           |
| Others         | RETINA           | retinal thickness metrics                                 | 網膜/黄斑/視神経線維層計測                      | RNFL厚                           |
| Unknown        | UNKNOWN          | unknown                                                   | 記述がなく不明。またはどのキーワードにも分類できない特徴量。      |                                 | 

----

## CT に該当する文献

CT (cortical thickness) を Response Variable として用いた研究（43件）：

完全なリスト（43研究）：

Baldwin2022, Bayer2022, Bedford2025, Berthet2025, Bethlehem2020, Bethlehem2021, Bhome2024, DiBiase2022, Echave2024,
Ge2024, Haas2024, Haukvik2025, Hua2025, Janssen2024, Ji2023, Jia2025, Joo2024, Kia2022, Lamsma2024, Leiberg2023,
Little2024,
Little2025, Loreto2024, Lv2021, Ma2024, Mansour2025, Martin2025, Meijer2024, Pinaya2019, RehakBuckova2025,
Romascano2024,
Rutherford2022, Rutherford2023, Sampaio2025, Verdi2023, Verdi2024, Wang2023, Wolfers2018, Worker2023, Wu2024, Yang2025,
Zabihi2019, Zabihi2020

----

## CV に該当する文献

CV (cortical volume) を Response Variable として用いた研究（23件）：

完全なリスト（23研究）：

Baldwin2022, Bedford2025, Bethlehem2021, Bhome2024, CardenasDeLaParra2019, Ge2024, Gordaliza2024, Haas2024, Haukvik2025,
Janssen2024, Lamsma2024, Little2025, Meijer2024, Parkes2021, Pinaya2019, Pinaya2021, RehakBuckova2025, Remiszewski2022,
Romascano2024, Rutherford2022, Rutherford2023, Verdi2024, Yang2025

----

## SA に該当する文献

SA (surface area) を Response Variable として用いた研究（16件）：

完全なリスト（16研究）：

Baldwin2022, Bedford2025, Bethlehem2021, Ge2024, Haas2024, Haukvik2025, Janssen2024, Joo2024, Lamsma2024, Leiberg2023,
Little2025, Rutherford2023, Wang2023, Wolfers2018, Yang2025, Zabihi2019

----

## SV に該当する文献

SV (surface volume) を Response Variable として用いた研究（20件）：

完全なリスト（20研究）：

Baldwin2022, Bhome2024, CardenasDeLaParra2019, Ge2024, Gordaliza2024, Haas2024, Haukvik2025, Kumar2025, Lamsma2024,
Meijer2024,
Pinaya2019, Pinaya2021, RehakBuckova2025, Remiszewski2022, Romascano2024, Rutherford2022, Rutherford2023, Verdi2024,
Vieira2025,
Yang2025

----

## WMV に該当する文献

WMV (white matter volume) を Response Variable として用いた研究（9件）：

完全なリスト（9研究）：

Bedford2025, Bethlehem2021, CardenasDeLaParra2019, Sampaio2025, Segal2025, Wolfers2018, Wolfers2020, Wu2023, Zhang2022

----

## GBV に該当する文献

GBV (global brain volume) を Response Variable として用いた研究（2件）：

完全なリスト（2研究）：

CardenasDeLaParra2019, Leenings2024

----

## TIV に該当する文献

TIV (total intracranial volume) / ICV を Response Variable として用いた研究（40件）：

完全なリスト（40研究）：

Baldwin2022, Bethlehem2020, Chan2025A, Coupe2022, Ebadi2024, Ebadi2025, Fang2025, Floris2021, Floris2024,
FukamiGartner2023,
Ge2024, Han2023, Holz2023, Huo2024, Ilioska2024, Jalbrzikowski2019, Jiang2024, Jing2023, Kobbersmed2025, Kumar2025,
Lawn2024,
Lee2025, Leenings2024, Lin2023, Lin2024, Liu2024, Looden2022, OliveiraSaraiva2023, Pinaya2021, Romascano2024,
Rutherford2023,
Sampaio2025, Savage2024, Shan2022, Sun2023, Tabbal2025, Thukral2024, Tong2024, Vieira2025, Xiao2024

----

## CSF に該当する文献

CSF (cerebrospinal fluid volume) を Response Variable として用いた研究（4件）：

完全なリスト（4研究）：

Bedford2025, Bethlehem2021, Leenings2024, Remiszewski2022

----

## SubV に該当する文献

SubV (subcortical volume - regional, 皮質下領域の体積) は
皮質下領域の体積測定を正規化するために作成されたカテゴリキーワード。
42の研究で SubV が、Response Variableとして使用されている：

完全なリスト（42研究）：

Baldwin2022, Bhome2024, CardenasDeLaParra2019, Chan2025B, Cirstian2024, Coupe2022, DeMeo2019, FukamiGartner2023,
GarciaSanMartin2025, Ge2024, Gimbel2025, Gordaliza2024, Haas2024, Han2023, Han2024B, Haukvik2025, Holz2023,
Jalbrzikowski2019, Lamsma2024, Little2025, Martin2025, Meijer2024, Mendes2024, Pinaya2019, Pinaya2021, RehakBuckova2025,
Remiszewski2022, Romascano2024, Rutherford2022, Rutherford2023, Sampaio2025, Savage2024, Segal2025, Verdi2023,
Verdi2024, Vieira2025, Wen2025, Xiao2024, Yang2025, Young2024, Zhang2022, Floris2024

代表的な例：

1. Gimbel2025 - 扁桃体、尾状核、海馬、淡蒼球、被殻、視床、側坐核（14 ROIs）
2. Rutherford2022 - 40の皮質下領域
3. Vieira2025 - 33の皮質下構造（Desikan-Killiany atlas）
4. Yang2025 - 14の皮質下領域
5. Gordaliza2024 - 視床、尾状核、被殻、淡蒼球、海馬、扁桃体、側坐核（両側）

よく使われる皮質下構造：

- 海馬（Hippocampus）
- 扁桃体（Amygdala）
- 被殻（Putamen）
- 尾状核（Caudate）
- 視床（Thalamus）
- 淡蒼球（Pallidum）
- 側坐核（Nucleus Accumbens）

----

## GMV に該当する文献

GMV (gray matter volume) を Response Variable として用いた研究（31件）：

完全なリスト（31研究）：

Bedford2025, Bethlehem2021, CardenasDeLaParra2019, DeMeo2019, Fang2024, Floris2024, GarciaSanMartin2025, Geng2025,
Gimbel2025,
Han2023, Han2024A, Huo2024, Joo2024, Kumar2025, Laidi2022, Lamsma2024, Martin2025, Romascano2024, Sampaio2025,
Segal2023,
Shan2022, Shao2024, Verdi2024, Wang2023, Wen2025, Wolfers2018, Wolfers2020, Wolfers2021, Yu2024, Zhang2023, Zheng2024

-----

## SubV と GMV、CV と GMV　の使い分けは？

SubV は「皮質下の特定ROI体積」を指す場合に用いる（例: 海馬、扁桃体、視床核、線条体、側坐核など）。
GMV は「灰白質体積の一般的指標」を指す場合に用いる（例: 全脳GMV、広域/皮質ROIのGMV、VBM由来のGM体積）。
迷った場合は、対象が明確に皮質下核の体積なら SubV、それ以外の灰白質体積は GMV に分類する。

CV と GMV の使い分けについては、対象が「皮質（cortex）に限定された体積」であることが明確な場合は CV とする
（例: FreeSurfer の cortical volume、表面ベースで thickness×area から算出された体積、皮質ROIのcortical GM volume）。
一方、VBM 由来のボクセル/領域の gray matter volume や、皮質下も含む灰白質体積、全脳GMVは GMV とする。
記述が曖昧で「gray matter volume」とだけある場合は GMV に寄せる。


----

## CerLV に該当する文献

CerLV (cerebellar lobule volume) を Response Variable として用いた研究（2件）：

完全なリスト（2研究）：

Jia2024, Kim2024

----

## CCMorph に該当する文献

CCMorph (corpus callosum morphology) を Response Variable として用いた研究（3件）： Leiberg2023, Mao2025, Wu2023

----

## CGeom に該当する文献

CGeom (Cortical Geometry ) を Response Variable として用いた研究（3件）： Janssen2024, Joo2024, Little2025

### CGeom という minor category keyword の設定経緯について

CGeom (Cortical Geometry) は、皮質表面の幾何学的特性を反映する指標を包括するために設けられたminor categoryである。
当初は GI/CURV (gyrification/curvature) というキーワードで gyrification index や curvature をまとめようとしていたが、
Little2025で用いられた K, I, S という独立成分指標を含める必要が生じたため、より包括的な名称に変更した。

#### 包含する指標

| 指標                      | 説明                                                                 | 出典                            |
|-------------------------|--------------------------------------------------------------------|-------------------------------|
| Gyrification Index (GI) | 皮質の折り畳み度合いを測定する指標（局所的または全脳的）。FreeSurferのlocalGI等で算出。               | Schaer et al., 2012           |
| Curvature               | 皮質表面の局所的な曲率（mean curvature, Gaussian curvatureなど）。                 | FreeSurfer標準出力                |
| K (tension component)   | 皮質にかかる張力を反映する無次元量。種間で保存されるが加齢・疾患に敏感。universal scaling lawに基づく独立成分。 | Wang et al., 2016, 2019, 2021 |
| I (isometric size)      | Kと直交・統計的に独立で、等方的なサイズ情報を捉える指標。                                      | Wang et al., 2016, 2019, 2021 |
| S (shape/complexity)    | KとIのクロス積で、皮質折り畳みの複雑さを反映する指標。                                       | Wang et al., 2016, 2019, 2021 |

#### K, I, S の導出背景

Wang et al.が提案した「universal scaling law of cortical folding」に基づき、
cortical thickness (T)、pial surface area (At)、exposed surface area (Ae) の共分散を考慮して導出された
統計的に独立な3成分である。従来のCT/SA/CVなどが共変動する問題を解決し、
皮質形態の独立した側面を捉えることができる。
Little2025では、Kが他の形態指標（R²=0.4-0.6）と比較して最も高いmodel fit（R²=0.8）を示すことが報告されている。

#### 該当文献の詳細

1. **Janssen2024**（統合失調症・縦断）: 62 DKT ROIのMorphometric Similarity（CT/SA/CV/曲率から算出）を
   Warped BLRでnormative zスコア化。
2. **Joo2024**（多施設SCZ・横断）: 308 ROIのSA/GMV/CT/LGI（Local Gyrification Index）でPBSIを算出し、
   HC平均・SDに基づくZスコアで逸脱を評価。
3. **Little2025**（Brain MoNoCle）: 3,276人の健常者データからK, I, Sを含む複数の皮質形態指標の
   normative modelを構築。GAMLSSを用いて年齢・性別・サイトを共変量としてモデル化。
   TLE患者でIとcortical volumeが側性化の検出に有効であることを示した。

----

## WMH に該当する文献

WMH (white matter hyperintensity) を Response Variable として用いた研究（1件）： Leenings2024

----

## FA に該当する文献

FA (fractional anisotropy) を Response Variable として用いた研究（8件）： Chien2022, Cirstian2024, Elad2021, Feng2024,
Feng2025, Lamsma2024, Lv2021, VillalonReina2024

----

## GFA に該当する文献

GFA (generalized fractional anisotropy) を Response Variable として用いた研究（1件）：

完全なリスト（1研究）：

Chien2022

----

## FAt に該当する文献

FAt (tissue fractional anisotropy) を Response Variable として用いた研究（1件）：

完全なリスト（1研究）：

Elad2021

----

## MD に該当する文献

MD (mean diffusivity) を Response Variable として用いた研究（6件）：

完全なリスト（6研究）：

Feng2024, Feng2025, Huang2024, Lamsma2024, VillalonReina2024, Young2024

----

## RD に該当する文献

RD (radial diffusivity) を Response Variable として用いた研究（2件）：

完全なリスト（2研究）：

Feng2025, VillalonReina2024

----

## AD に該当する文献

AD (axial diffusivity) を Response Variable として用いた研究（1件）：

完全なリスト（1研究）：

VillalonReina2024

----

## FW に該当する文献

FW (free water) を Response Variable として用いた研究（1件）：

完全なリスト（1研究）：

Elad2021

----

## LLF-BOLD-metrics に該当する文献

LLF-BOLD-metrics (ALFF/fALFF などの局所低周波BOLD指標) を Response Variable として用いた研究（2件）：

完全なリスト（2研究）：

Han2024B, Kasper2024

----

## FC に該当する文献

FC (functional connectivity) を Response Variable として用いた研究（23件）：

完全なリスト（23研究）：

Chan2025A, Ebadi2024, Ebadi2025, Fang2025, Floris2024, Huo2024, Ilioska2024, Jalbrzikowski2019, Jiang2024, Jing2023,
Kobbersmed2025,
Lawn2024, Lee2025, Lin2023, Lin2024, Liu2024, Looden2022, OliveiraSaraiva2023, Rutherford2023, Sun2023, Tabbal2025,
Thukral2024,
Tong2024

----

## rs-FC に該当する文献

rs-FC (resting-state functional connectivity) を Response Variable として用いた研究（5件）：

完全なリスト（5研究）：

Chan2025A, Jalbrzikowski2019, Kobbersmed2025, Rutherford2023, Thukral2024

----

## dyn-FC に該当する文献

dyn-FC (dynamic functional connectivity) を Response Variable として用いた研究（3件）：

完全なリスト（3研究）：

Huo2024, Jing2023, Lin2023

----

## FC-gradient に該当する文献

FC-gradient (functional connectivity gradient) を Response Variable として用いた研究（1件）：

完全なリスト（1研究）：

Lee2025

----

## FC-strength に該当する文献

FC-strength (functional connectivity strength) を Response Variable として用いた研究（5件）：

完全なリスト（5研究）：

Huo2024, Lin2023, Lin2024, Liu2024, Sun2023

----

## TASK-GLM に該当する文献

TASK-GLM (task fMRI GLMコントラスト) を Response Variable として用いた研究（4件）：

完全なリスト（4研究）：

Floris2024, Holz2023, Savage2024, Xiao2024

----

## GCOR に該当する文献

GCOR (global correlation) を Response Variable として用いた研究（1件）：

完全なリスト（1研究）：

Kasper2024

----

## LCOR に該当する文献

LCOR (local correlation) を Response Variable として用いた研究（1件）：

完全なリスト（1研究）：

Kasper2024

----

## SUVR-amyloid に該当する文献

SUVR-amyloid を Response Variable として用いた研究（2件）：

完全なリスト（2研究）：

Kumar2024, Kumar2025

----

## SUVR-tau に該当する文献

SUVR-tau を Response Variable として用いた研究（2件）：

完全なリスト（2研究）：

Kumar2024, Kumar2025

----

## BPND に該当する文献

BPND を Response Variable として用いた研究（1件）：

完全なリスト（1研究）：

Giacomel2025

----

## Ki_cer に該当する文献

Ki_cer を Response Variable として用いた研究（1件）：

完全なリスト（1研究）：

Giacomel2025

----

## MEG/EEG

**AEC/PEC に該当する文献**

AEC/PEC (amplitude/phase envelope correlation) を Response Variable として用いた研究（10件）：

完全なリスト（11研究）：

Ebadi2024, Ebadi2025, FukamiGartner2023, Italinna2023, Kobbersmed2025, Lawn2024, Lin2023, Mansour2025, Tabbal2025,
Tong2024

**PSD に該当する文献**

PSD (power spectral density) を Response Variable として用いた研究（1件）：

完全なリスト（1研究）：

Italinna2023

**MEG/EEG-Other に該当する文献**

MEG/EEG-Other (other MEG/EEG metrics) を Response Variable として用いた研究（1件）：

完全なリスト（1研究）：

Floris2024



-------

## MSI に該当する文献

MSI (Morphometric Similarity Index) は皮質形態の類似度行列を指標化したもので、現状1研究で Response Variable として利用されている：

完全なリスト（1研究）：

- Janssen2024（統合失調症・縦断）: 62 DKT ROIのMorphometric Similarity（CT/SA/CV/曲率から算出）をWarped
  BLRで年齢・性別・Euler数・スキャナを共変量としたnormative
  zスコア化し、Yeo-7ネットワーク平均zも評価。share_package/data/Janssen2024/DE/json/DE_Janssen2024_by_codex_202510300210.json

-------

## PBSI に該当する文献

- Janssen2021（統合失調症・縦断）:
  PBSI-SW（11両側溝幅）を計算し、年齢/スキャナ/TBVで残差化後にHC平均・SDからZ化して逸脱者（|Z|>
  2）を特定。比較としてPBSI-CTも算出。share_package/data/Janssen2021/DE/json_v9/
  DE_Janssen2021_by_codex_202510291208.json
- Baldwin2022（ENIGMA CHR-Pメガ解析）: SA・CT・SV（+ICV）の153 ROIベクトルをHCプロファイルと相関させるPBSIでnormative
  Zスコアを作成し、>1.5 SDで顕著逸脱をカウント。share_package/data/Baldwin2022/DE_v12/json/
  DE_v12_Baldwin2022_by_human_20260107172123.json
- Joo2024（多施設SCZ・横断）: 308
  ROIのSA/GMV/CT/LGI（および全統合PBSI-All）でPBSIを算出し、HC平均・SDに基づくZスコアで患者を「deviant」（< -1.5
  SD）と分類し臨床指標と比較。share_package/data/Joo2024/materials/optimized/
  Joo2024.pdf.md

-------

## IDP-set に該当する文献

- Fraza2023（UKB CNV解析）: UK Biobank
  44,456人から得た2,084のIDP（Imaging-Derived Phenotype 構造・機能・拡散のまとめ指標）をFUNPACKで前処理し、
  PCNtoolkitのBLR（年齢・性別・サイト共変量）で各IDPのnormative modelを作成。
  CNVキャリアの逸脱z（|z|>2）総数をモダリティ別に比較するスクリーニングとしてIDPセットを用いた後、
  Jacobian voxelwise解析へ展開。share_package/data/Fraza2023/materials/optimized/Fraza2023.pdf.md

-------

## VQ-VAE-TS に該当する文献

- Mendes2024 - VQ-VAE(Vector-Quantized Variational Autoencoder)でVBM前処理したsMRIを離散コード（~
  16k）に量子化し、GPT（トランスフォーマー）が典型脳の確率分布を学習。各コードのlog-likelihoodを合算したwhole-brain典型度（likelihood）スコアと、AAL3で平均化したROI別likelihoodをresponse
  variableとしてCBCL症状、ADHDスコア、ASD診断と関連付け。
- Pinaya2022（T2-FLAIR病変検出）- VQ-VAE+GPTで正常脳の確率分布を学習し、各コードの（log）likelihoodを「典型度」として算出。典型度マップの低下を病変検出の異常度指標（response
  variable）に用いる。
- DaCosta2022（早期SCZ検出）- 同じVQ-VAE+GPT典型度モデルをT1 sMRIに適用。被験者のコード列log-likelihood（whole-brain/ROI典型度）をresponse
  variableとして用い、早期SCZの判別に利用。

-------

## NMF に該当する文献

NMF (non-negative matrix factorization) を Response Variable として用いた研究（1件）：

完全なリスト（1研究）：

Shan2022

-------

## LI に該当する文献

LI (laterality index) を Response Variable として用いた研究（3件）：

完全なリスト（3研究）：

Floris2021, Geng2025, Young2024

-------

## NetMes に該当する文献

NetMes (Network Measures, ネットワーク指標) 脳機能ネットワークの特性を測定する指標を正規化するために作成されたカテゴリキーワード。
6の研究で NetMes が、Response Variableとして使用されている：

完全なリスト（6研究）：

Sun2023, Liu2024, Sun2025, Lin2024, Huo2024, Jing2023

代表的な例：

1. Sun2023 - Functional Connectivity Strength (FCS)
2. Liu2024 - Functional Connectivity Strength (FCS)、OCD研究
3. Sun2025 - Degree Centrality (DC)、MDD研究
4. Lin2024 - Average FCS (aFCS)、Dynamic FCS (fFCS)、統合失調症縦断研究
5. Huo2024 - Average Dynamic FC (a-FCS)、Dynamic FC Fluctuation (f-FCS)、AD/MCI研究
6. Jing2023 - Dynamic Functional Connectivity、初発うつ病研究

よく使われるネットワーク指標：

- FCS (Functional Connectivity Strength): 機能的結合強度の総和
- aFCS (Average FCS): 平均機能的結合強度
- fFCS (Dynamic FCS): 動的機能的結合強度の変動
- DC (Degree Centrality): 次数中心性
- Dynamic FC: 時間変動する機能的結合

----

## RETINA に該当する文献

RETINA (retinal thickness metrics) を Response Variable として用いた研究（1件）：

完全なリスト（1研究）：

Georgiadis2024

----

## UNKNOWN に該当する文献

- 該当なし（0件）。

----

## Ancillary Consideration: Deformation-Derived Morphometry (DDM) に該当する文献

「deformation由来の形態指標をResponse variableとしてNormative Modelingした研究」というニュアンスが伝わる名前で括を設ける。
「Deformation-Derived Morphometry」とするのはどうだろうか。

DDMというというキーワードで、
Normative Modeling時に
非線形正規化の変形場（Jacobian行列）を用いたボクセル単位の局所体積変化を定量化する形態計測値をresponse variableをとしている研究
およびその類似研究をを整理したいと考えている。
類似研究をどこまで含めるかが悩ましいが、以下のような研究にDDMというキーワードを紐付けたい。

* 変形場Jacobian決定量そのもの（DBM/TBM）をresponse variableにしたNormative Modeling研究
* そこから得た低次元因子（NMFなど）をresponse variableにしたNormative Modeling研究

一方で以下のような研究はこの括りの外としたい。

* JacobianでモジュレーションしたVBMボクセル値(Jacobian modulated VBM（GM/WM体積）)をresponse variableにしたNormative
  Modeling研究
* そこから得た低次元因子（NMFなど）をresponse variableにしたNormative Modeling研究

例えば以下の研究がDDMに該当する（Normative Modeling Studies with Deformation-Derived Morphometry）。

### Jacobianそのものを予測するDBM/TBM

- CardenasDeLaParra2019 - 16p11.2重複/欠失を対象に、全脳のlog-Jacobian TBM(Tensor-Based Morphometry)
  マップ（voxel-wise）とグローバル体積をモデル化。非線形正規化の変形場（Jacobian行列）から体積変化をボクセル単位で評価する形態計測手法です。
  変形テンソル（Jacobian）を直接使う点が特徴。
  VBMのようなセグメンテーションを経ずに変形量から局所体積差を定量化。
  NIHPD基準でlog-Jacobian determinantsのvoxel-wise TBMマップをGPRでnormative Zスコア化。
  share_package/data/CardenasDeLaParra2019/materials/optimized/CardenasDeLaParra2019.pdf.md
- Fraza2023（UKB Jacobianモデル）:
  Cam-CAN/HCP/OASIS/PNC/UKBの19,620人から得たT1→MNIのJacobian determinants（voxel-wise）をWarped
  BLRでnormativeモデル化し、CNVキャリアの逸脱Zを解析。
  share_package/data/Fraza2023/materials/optimized/Fraza2023.pdf.md
  BrainCharts拡張データでT1→MNIのvoxel-wise Jacobian determinantsを直接モデル化（DBM/TBM的にJacobian決定量そのものを予測）。
- Holz2023（DBD・multi-modal）: 構造画像のvoxel-wise非線形Jacobian determinantsをGPRベースのnormativeモデルに投入し、
  感情課題fMRIの逸脱とともにLICAで統合。share_package/data/Holz2023/materials/optimized/Holz2023.pdf.md
  share_package/data/Holz2023/materials/optimized/Holz2023.pdf.md

### JacobianでモジュレーションしたVBM（組織体積）

- Wolfers2018 / Wolfers2021 - SCZ/BP/ASDなどでVBM由来のグレー/ホワイトマターのvoxel-wise量をNormative Modeling。
  具体的には、T1を共通テンプレートへ非線形正規化し、その変形Jacobianで体積補正（モジュレーション）したGM/WM確率マップを
  ボクセル単位で扱い、
  Warped Bayesian Linear Regressionで年齢・性別などを共変量にした予測分布を学習し、
  個人ごとの逸脱Zを算出する、という形で変形場由来の局所体積指標を利用。
  変形場のJacobianは体積補正（modulation）にのみ使い、特徴量はGM/WMのモジュレーテッドVBMボクセル値（組織体積）。
  Jacobian決定量そのものを予測するDBM(Deformation-Based Morphometry)/TBM(Tensor-Based Morphometry)ではない。
  非線形正規化後にJacobianで体積補正したGM/WM確率マップのvoxel-wise値をWarped BLRでnormative
  modelingし、SCZ/BP/ASDで逸脱Zを評価。
- Shan2022（ASD・構造MRI）:
  ASDを対象に、VBM灰白質ボリュームをNMF(Non-negative Matrix Factorization)で低次元因子に分解し、
  各因子重みをNormative Modeling（因子はVBM変形指標由来の空間パターン）。
  非線形正規化の変形場（Jacobian決定量）を直接モデル化・予測するDBM/TBMではなく、JacobianはVBMの体積補正にのみ間接利用され、学習自体はその補正済み体積を要約したNMF因子重みに対して行っている。
  VBM体積をNMF因子化した派生特徴をnorumative modeling。Jacobian行列はVBMの体積補正に間接利用されるのみ）。
  VBM灰白質ボリュームをNMFで6因子に分解し、因子重みをAge/Gender共変量のGPRでnormative modeling。
  ABIDE II TDのVBM灰白質ボリュームをNMFで6因子に分解し、因子重みをAge/Gender共変量のGPRでnormative モデル化。
  ABIDE I ASDの因子重みWスコアの逸脱と臨床サブタイプを解析。
  share_package/data/Shan2022/materials/optimized/Shan2022.pdf.md
- Kim2023/2024
  小脳のJacobianモジュレーテッドVBMのvoxel-wise値（約14万voxel）と葉別体積を年齢・性別でnormative modelingし、臨床群の逸脱Zを評価。
- Segal2025 - 白質VBM（JacobianモジュレーテッドWMVのvoxel-wiseマップ）をWarped BLMでNormative Modelingし、白質全域の逸脱Zマップを作成。
  白質JacobianモジュレーテッドWMVのvoxel-wiseマップをWarped BLMでnormative modelingし、全白質域の逸脱Zマップを作成。
- Han2024A - CAT12で得た灰白質のモジュレーテッドVBMボクセル値と246領域ZスコアをNormative
  Modelingし、個人の灰白質逸脱をボクセル/領域の両粒度で評価。
- Han2023（OCD・構造MRI）:
  VBM GMVのnormative Zスコアを正負に分けてNMFし、4因子重みを症状・発症年齢と関連付け。
  CAT12 VBMで得たvoxel-wise GMVをage/sex共変量GPRでNormative Zスコア化し、正負に分けてNMFで4潜在因子（positive/negative各2）に分解。
  因子重み（変形由来GMV逸脱パターンの潜在要約）を症状・発症年齢・病期・サブタイプ同定に関連付け。
  CAT12のモジュレーテッドVBMのGMVをGPRでZ化し、その正負をNMF。
  モジュレーション自体は正規化のJacobianで体積を補正しているが、Jacobianマップそのものを解析したりDBM/TBMとして扱ってはいない。主題はGMV
  Zの因子化。
  share_package/data/Han2023/materials/optimized/Han2023.pdf.md

### 補足説明

* Jacobian行列: 変形場（座標写像）の各点での微分係数をまとめた行列で、非線形正規化で生じる局所の伸縮を表す。体積変化の尺度としてはJacobian行列の決定量（determinant）が用いられ、
  VBM/TBM/DBMでモジュレーションや形態比較に使われる。
* modulation： VBMの「モジュレーション」は、非線形正規化の変形場で得たJacobian決定量を各ボクセルに掛ける体積補正のことである。
  テンプレートに合わせて引き伸ばし・縮みが生じた分を補正し、正規化前の局所体積を保ったボクセル値（modulated GMV/WMV）として扱う。
* NMF: Non-negative Matrix Factorization（非負値行列因子分解）の略で、非負のデータ行列Vを基底行列Wと係数行列Hに分解し、
  各因子（列）で空間パターンと被験者ごとの重みを同時に推定する次元圧縮手法。
  VBMやfMRIでボクセル（特徴）×被験者の行列を因子化し、低次元の解釈しやすい脳パターンと個人の寄与度を得る際に用いられる。
  NMFとJacobian行列と独立の概念である。Jacobian行列は非線形正規化の変形場から得る局所体積スケールの指標で、形態計測（VBM/DBM/TBM）で使う。
  NMFは非負値行列因子分解という次元圧縮・パターン抽出の数理手法で、入力データに依存する。
  関係するとすれば、Jacobian由来のボクセルマップやモジュレーテッドVBMを行列にしてNMFにかけるといった分析パイプライン上での利用に限られ、概念的には別である。
