# Keywords for Response Variable

## Ask AI

`share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_codex_<ProcessedTime>.json`,
`share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_claude_<ProcessedTime>.json`,
`share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_gemini_<ProcessedTime>.json`
というファイルを横断的に読み解いて教えてください。
"normative_modeling_part/nm4_response_variable/answer"
にある情報をキーワード表現に置き換えて正規化したいと考えています。現在、以下の16個のキーワードで表現することを考えていますが、これらに当てはまらないようなキーワードがあれば教えてください。

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

## Answer

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

| Group  | Keyword            | Full Spelling                                | Description                            | Example                   |
|--------|--------------------|----------------------------------------------|----------------------------------------|---------------------------|
| sMRI   | CT                 | cortical thickness                           | 皮質厚の平均や頂点値                             | 左上側頭回の皮質厚                 |
| sMRI   | CV                 | cortical volume                              | 皮質領域の体積                                | 右前頭極の皮質体積                 |
| sMRI   | SA                 | surface area                                 | 皮質面積（頂点/領域）                            | 左外側後頭葉の面積                 |
| sMRI   | SV                 | surface volume                               | 表面ベースで算出した皮質体積                         | 全皮質の表面体積                  |
| sMRI   | GMV                | gray matter volume                           | 灰白質体積（領域/全脳）                           | 両側海馬の灰白質体積                |
| sMRI   | WMV                | white matter volume                          | 白質体積（領域/全脳）                            | 前頭葉白質体積                   |
| sMRI   | GBV                | global brain volume                          | 全脳の総体積                                 | 全脳容積（灰白質+白質）              |
| sMRI   | TIV/ICV            | total intracranial volume                    | 頭蓋内容積                                  | TIVでスケールした各IDP            |
| sMRI   | WMH                | white matter hyperintensity                  | 白質高信号量                                 | Fazekasスコア、WMH総体積         |
| sMRI   | CSF                | cerebrospinal fluid volume                   | 脳脊髄液量                                  | 側脳室体積                     |
| sMRI   | SV-regional        | subcortical volume (regional)                | 汎用ROIの皮質下体積                            | 扁桃体体積                     |
| dMRI   | DTI-bundle-profile | diffusion MRI bundle profile                 | 束に沿った形状とFA/MD/RD/ADプロファイル              | 海馬傍回路のalong-tract FA      |
| dMRI   | FA                 | fractional anisotropy                        | 拡散異方性のスカラー指標                           | 上縦束のFA                    |
| dMRI   | GFA                | generalized fractional anisotropy            | Q-ball等での一般化FA                         | 半球平均のGFA                  |
| dMRI   | FAt                | tissue fractional anisotropy                 | 組織成分に限定したFA                            | CSF補正後FA                  |
| dMRI   | MD                 | mean diffusivity                             | 平均拡散係数                                 | 後部内包のMD                   |
| dMRI   | RD                 | radial diffusivity                           | 放射方向拡散係数                               | 前放線冠のRD                   |
| dMRI   | AD                 | axial diffusivity                            | 軸方向拡散係数                                | 鉤状束のAD                    |
| dMRI   | FW                 | free water                                   | 自由水成分の割合                               | 側頭葉白質のFW                  |
| fMRI   | LLF-BOLD metrics   | Local low-frequency BOLD fluctuation metrics | 周波数領域・局所指標                             | ALFF, fALFF, mALFF, zALFF |
| fMRI   | FC                 | functional connectivity                      | 時系列相関によるFC                             | PCC–mPFCのFC               |
| fMRI   | rs-FC              | resting-state functional connectivity        | 安静時fMRIのFC                             | DMN内FC                    |
| fMRI   | dyn-FC             | dynamic functional connectivity              | 時間変動するFC/変動度                           | スライディングウィンドウFC分散          |
| fMRI   | FC-gradient        | functional connectivity gradient             | FC行列の勾配座標                              | 主勾配(Gradient 1)スコア        |
| fMRI   | FCS                | functional connectivity strength             | 接続強度の総和                                | mPFCのFCS                  |
| fMRI   | TASK-GLM           | task fMRI general linear model               | 課題fMRIコントラストのz/tマップ                    | faces>shapesのzマップ         |
| NetMes | DC                 | degree centrality                            | 頂点の次数中心性                               | 視床のDC                     |
|        | GCOR               | global correlation                           | 全ボクセル平均相関                              | 全脳GCOR                    |
|        | LCOR               | local correlation                            | 近傍との局所相関                               | 角回のLCOR                   |
|        | FD                 | framewise displacement                       | 頭動のフレーム間変位                             | 平均FD=0.18mm               |
|        | BPND               | binding potential (non-displaceable)         | PET結合能指標                               | \[11C\]DASBの線条体BPND       |
|        | Ki_cer             | influx rate constant (cerebellar ref.)       | 小脳基準の取り込み率                             | [18F]FDOPA Ki_cer         |
| PET    | SUVR-amyloid       | standardized uptake value ratio (amyloid)    | アミロイドPETのSUVR                          | AV45 SUVRCER              |
| PET    | SUVR-tau           | standardized uptake value ratio (tau)        | タウPETのSUVR                             | FTP SUVRCBL               |
|        | MSI                | morphometric similarity index                | 形態類似度の指標                               | MSI行列の平均                  |
|        | DBM/TBM            | deformation/voxel-based morphometry          | 変形場のJacobianによる形態指標                    | VBMのJacobian平均            |
|        | VQ-typicality      | vector-quantized VAE typicality score        | VQ-VAEでの典型度スコア                         | 典型度z-score                |
|        | PBSI-SW            | person-based similarity index (sulcal width) | 溝幅類似度の個人指標                             | Sulcal width PBSI         |
|        | GI/CURV            | gyrification/curvature                       | 脳回形成や曲率の指標                             | 全皮質平均gyrification         |
|        | LI                 | laterality index                             | 左右差の指数                                 | 海馬体積のLI                   |
|        | PSD                | power spectral density                       | MEG/EEG帯域パワー                           | α帯域PSD                    |
|        | AEC/PEC            | amplitude/phase envelope correlation         | MEG/EEGの周波数別FC                         | β帯域AEC                    |
|        | RETINA             | retinal thickness metrics                    | 網膜/黄斑/視神経線維層計測                         | RNFL厚                     |
|        | CER-Lobule         | cerebellar lobule volume                     | 小脳葉の体積                                 | Crus I体積                  |
|        | THAL-Nuclei        | thalamic nuclei volume                       | 視床核の体積                                 | 左LGN体積                    |
|        | HIPPO              | hippocampal volume                           | 海馬体積                                   | CA1体積                     |
|        | CC-morpho          | corpus callosum morphology                   | 脳梁の体積/面積/長さ/周長                         | 脳梁膨大部面積                   |
|        | IDP-set            | imaging derived phenotype set                | 多数のIDPを束ねたセット                          | UKB 2,000+IDPセット          |
|        | NMF                | nonnegative matrix factorization             | 成分負荷量/混合比                              | NMFコンポーネント重み              |
|        | UNKNOWN/NR         | unknown / not reported                       | 情報欠損・不明。Response variableが特定できないときに選択。 |                           |


## Appendix: SV-regional に該当する文献

SV-regional (subcortical volume - regional, 皮質下領域の体積) 皮質下領域の体積測定を正規化するために作成されたカテゴリキーワード。
42の研究で SV-regional が、Response Variableとして使用されている：

完全なリスト（42研究）：

Baldwin2022, Bhome2024, CardenasDeLaParra2019, Chan2025B, Cirstian2024, Coupe2022, DeMeo2019, FukamiGartner2023, GarciaSanMartin2025, Ge2024, Gimbel2025, Gordaliza2024, Haas2024, Han2023, Han2024B, Haukvik2025, Holz2023, Jalbrzikowski2019, Lamsma2024, Little2025, Martin2025, Meijer2024, Mendes2024, Pinaya2019, Pinaya2021, RehakBuckova2025, Remiszewski2022, Romascano2024, Rutherford2022, Rutherford2023, Sampaio2025, Savage2024, Segal2025, Verdi2023, Verdi2024, Vieira2025, Wen2025, Xiao2024, Yang2025, Young2024, Zhang2022, Floris2024

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
