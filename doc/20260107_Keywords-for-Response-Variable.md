# Keywords for Response Variable 

## Ask AI

`share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_codex_<ProcessedTime>.json`,
`share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_claude_<ProcessedTime>.json`,
`share_package/data/<AuthorYear>/DE/json/DE_AuthorYear>_by_gemini_<ProcessedTime>.json`
というファイルを横断的に読み解いて教えてください。
"normative_modeling_part/nm4_response_variable/answer" にある情報をキーワード表現に置き換えて正規化したいと考えています。現在、以下の16個のキーワードで表現することを考えていますが、これらに当てはまらないようなキーワードがあれば教えてください。

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
  - ネットワーク指標: degree centrality、Functional Connectivity Strength (FCS)、aFCS/fFCS、network fluctuation characteristic、FC gradient。
  - 変形ベース形態: Jacobian determinants (DBM/TBM)、VQ-VAE 典型性スコア。
  - Task fMRI 活性: GLM z-map（faces>shapes などコントラスト）。
  - PET/SPECT: BPND、Ki_cer（ドーパミン合成）、SUVR（アミロイド/タウ）。
  - 形態類似・折り畳み: morphometric similarity index、PBSI-SW（溝幅類似度）、gyrification/curvature 系。
  - MEG/EEG: PSD（帯域パワー）、AEC/PEC など周波数別 FC。
  - 眼科計測: 黄斑/網膜厚、RNFL、GC-IPL。
  - 特定ROI体積: 小脳葉・視床核、海馬、脳梁形態（面積/長さ/周長など）。
  - グローバル組織量: WMH、CSF、TIV/ICV など（GMV/WMV以外）。
  - 混合IDPセット: 2000+ IDPや多組織スコア（単一キーワードでは括りにくい）。
- 補足: `share_package/data/Feng2024/DE/json/DE_Feng2024_by_gemini_20251210071754.json` は answer 欄が空。`Yes` だけの回答も1件あり要確認。

## Keywords for Normalization

- Core morphometry: CT, CV, SA, SV, GMV, WMV, GBV, TIV/ICV, WMH, CSF.
- ALFF family: ALFF (raw low-frequency power), fALFF (low-frequency power / total power).
- Diffusion scalars: FA, MD, RD, AD, GFA, FAt, FW.
- DTI tract profile: DTI-bundle-profile (along-tract shape + FA/MD/RD/AD).
- Connectivity: FC, rs-FC, dyn-FC (aFCS/fFCS/FC-variability), FC-gradient, FCS, DC, GCOR, LCOR.
- Motion: FD.
- PET/SPECT: BPND, Ki_cer, SUVR-amyloid (e.g., AV45), SUVR-tau (e.g., FTP).
- Task fMRI: TASK-GLM (contrast z/t-map).
- Deformation: DBM/TBM (Jacobian determinants), VQ-typicality (VQ-VAE typicality score).
- Folding/Similarity: MSI (morphometric similarity index), PBSI-SW (sulcal width similarity), GI/CURV (gyrification/curvature metrics).
- Laterality: LI (laterality indices for CT/GMV/WMV/FC/others).
- MEG/EEG power: PSD (band power).
- MEG/EEG connectivity: AEC/PEC (frequency-specific FC).
- Ophthalmic: RETINA (macular/RNFL/GC-IPL thickness).
- Region-specific volumes: CER-Lobule (cerebellar lobules), THAL-Nuclei (thalamic nuclei), HIPPO (hippocampus), CC-morpho (corpus callosum volume/area/length/perimeter), SV-regional (generic subcortical volumes by atlas/ROI).
- IDP sets: IDP-set (bulk feature sets, e.g., 2000+ IDPs).
- Model components: NMF (component weights).
- Unknown: UNKNOWN/NR (missing or non-informative answers such as “Yes”).
