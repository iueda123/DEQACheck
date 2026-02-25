# 抽出基準文書（Data Extraction Protocol）

------------------

## Imaging Modality

### 概要

本項目では、当該研究でNormative Modelingの対象となった特徴量（Response Variable）が由来する主な画像モダリティを抽出した。
各研究で用いられたモダリティについて、論文のMethods（MRI Data Acquisition等）の記載に基づき、まず具体的なシーケンスや計測手法を自由記述形式で記録した（例: "T1w MRI; PET (Amyloid, 18F-Florbetapir)"）。
その上で、集計表（Table 1）においては **sMRI / fMRI / dMRI / Other** の4カテゴリへ分類した。1研究が複数のモダリティを用いている場合は、該当する全カテゴリにチェックを入れた。

### カテゴリ定義と分類基準

#### sMRI（構造的MRI）

T1強調画像（T1-weighted MRI）を用いた研究を本カテゴリに分類した。MPRAGE、FSPGR、BRAVO等の具体的パルスシーケンスの違いにかかわらず、T1強調画像であれば全てsMRIに含めた。T2強調画像（T2-weighted MRI）やT2-FLAIR画像を用いた研究も本カテゴリに分類した。sMRIから得られる代表的な特徴量には、皮質厚（cortical thickness）、灰白質体積（gray matter volume）、皮質面積（surface area）、皮質下体積（subcortical volume）等がある。

自由記述での記録例:
- "T1w MRI"
- "T1w MRI; T2w MRI"
- "sMRI (T2)"

#### fMRI（機能的MRI）

Blood-oxygen-level-dependent（BOLD）信号に基づく機能的MRIを用いた研究を本カテゴリに分類した。安静時fMRI（resting-state fMRI）および課題遂行時fMRI（task fMRI）のいずれも含む。fMRIから得られる代表的な特徴量には、機能的結合（functional connectivity: FC）、安静時FC（resting-state FC）、低周波BOLD変動指標（ALFF, fALFF等）、課題fMRIのコントラストマップ（GLMベースのz/t値）等がある。

#### dMRI（拡散MRI）

拡散強調画像（diffusion-weighted imaging）を用いた研究を本カテゴリに分類した。拡散テンソルイメージング（DTI）をはじめ、各種拡散モデルから得られる指標を対象とする。具体的な拡散指標として、fractional anisotropy（FA）、mean diffusivity（MD）、radial diffusivity（RD）、axial diffusivity（AD）、free water（FW）、tissue FA（FAt）等がある。自由記述ではこれらの具体的指標も併記した。

#### Other（その他）

上記3カテゴリ（sMRI, fMRI, dMRI）のいずれにも該当しないモダリティを用いた研究を本カテゴリに分類した。具体的には以下のモダリティが該当した:

- **PET（陽電子放射断層撮影）**: アミロイドPET（18F-Florbetapir等）、タウPET（18F-Flortaucipir等）、ドパミン系トレーサー（18F-FDOPA等）、その他の放射性リガンド（11C系トレーサー等）を用いた研究。代表的な特徴量にSUVR（standardized uptake value ratio）、BPND（binding potential）、Ki等がある。
- **EEG（脳波）**: 安静時または課題時の頭皮脳波計測を用いた研究。代表的な特徴量にpower spectral density（PSD）、amplitude envelope correlation（AEC）等がある。高密度EEG（HD-EEG; 128ch等）も含む。
- **MEG（脳磁図）**: 安静時または課題時のMEG計測を用いた研究。PSD、AEC等の周波数帯域別指標が該当する。
- **qMRI（定量的MRI）**: R1（1/T1）、R2*（1/T2*）、磁化率（QSM: χ）等の定量的MRIパラメータを用いた研究。
- **網膜画像（retinal imaging）**: 光干渉断層計（OCT）等による網膜厚の計測を用いた研究。

### 抽出時の留意事項

- 同一研究で複数のモダリティが使用されている場合、該当する全てを記録した（例: T1w MRIとfMRIの両方を使用していれば、sMRIとfMRIの両方にチェック）。

--------------