# Modality パターン整理（RCI5: imaging modality）

この文書は `reference_cohort_and_imaging_part.rci5_imaging_modality.answer` に記載されたモダリティ表現（SummaryView
の「Modality」列）を実データから収集し、表記ゆれの傾向を整理・正規化方針を提案するものです。

- 対象データ: `share_package/data/**/DE/json*/DE_*.json`
- 解析方法: rci5_imaging_modality.answer を抽出しユニーク化・頻度集計

## サマリ

- 最頻出は「T1-weighted MRI」（構造MRI）。MP-RAGE/MPRAGE/BRAVO/3D などシーケンス詳細が括弧で付随。
- fMRI は `Resting-state fMRI`（rs-fMRI）と `Task-based fMRI`（BOLD, task 名）が混在。
- Diffusion は `Diffusion MRI`（dMRI, DWI）と `DTI (FA/MD/RD/AxD)`、`DSI` が混在。
- PET は `Amyloid PET (Florbetapir/AV45)`、`Tau PET (Flortaucipir/FTP)`、その他 `FDOPA`, `[11C]-(+)-PHNO` などトレーサー差異がある。
- EEG/MEG は `HD-EEG 128ch`, `resting-state` 指定など付帯情報が様々。
- その他: `T2-FLAIR`, `T2*-weighted (BOLD/GRE)`, `QSM`, `R1/R2*`, `OCT` 等。
- ノイズ値: `Yes`, `NR` はモダリティ値としては不適切（抽出器由来の混入）。

頻度上位（抜粋）:

- T1-weighted MRI … 272 件
- Yes … 57 件（ノイズ）
- Resting-state fMRI … 28 件
- T1-weighted MRI; Diffusion-weighted MRI … 10 件
- T1-weighted MRI (MPRAGE) … 9 件

## 正規化の基本方針（提案）

- 大分類 + 省略形 + 具体的条件は括弧で表記し、セミコロン区切りで複数モダリティを並列化。
    - 例: `T1w MRI; rs-fMRI; dMRI (DTI: FA, MD)`
- 表記ゆれは以下のカノニカル名に寄せる。
    - Structural MRI: `T1w MRI`（必要に応じて `(MPRAGE)` 等）
    - Functional MRI (rest): `rs-fMRI`
    - Functional MRI (task): `task-fMRI`（必要に応じて `(BOLD)` や課題名）
    - Diffusion MRI: `dMRI`（`DTI` 指標は括弧: `(DTI: FA, MD, RD, AxD)`）
    - Diffusion Spectrum Imaging: `DSI`
    - PET: `Amyloid PET (18F-Florbetapir)` / `Tau PET (18F-Flortaucipir)` などトレーサー明記。
    - EEG: `EEG`／`HD-EEG (128ch)`／`rs-EEG`
    - MEG: `MEG`
    - Others: `T2-FLAIR`, `T2* (GRE)`, `QSM`, `R1 (MP2RAGE)`, `R2* (multi-echo GRE)`, `OCT`
- 末尾のピリオドは除去。大文字小文字はカノニカル表記に統一。
- 派生量（例: `VBM`, `cortical thickness` など）は基本は括弧内で補助的に記すか、別列で扱う。
- `Yes`/`NR`/空は欠損として扱い、表示時は空欄（または `NR`）にする。

## 代表パターンと例（→ 正規化例）

### Structural MRI

- "T1-weighted MRI", "T1-weighted structural MRI", "Structural MRI (T1-weighted)" → `T1w MRI`
- "3D T1-weighted MRI", "T1-weighted MRI (3D)" → `T1w MRI (3D)`
- "T1-weighted MRI (MPRAGE)", "MP-RAGE" → `T1w MRI (MPRAGE)`
- "T1-weighted MRI (BRAVO)" → `T1w MRI (BRAVO)`
- "T1-weighted MRI (1.5T Philips Intera/Achieva)", "(3T)" → `T1w MRI (1.5T)`, `T1w MRI (3T)`
- "T2-weighted MRI", "T2-FLAIR" → `T2w MRI`, `T2-FLAIR`

派生表現（保持は任意）

- "(GMV via VBM)", "(cortical thickness)" → `T1w MRI (VBM)`, `T1w MRI (cortical thickness)`

### Functional MRI

- "Resting-state fMRI", "rs-fMRI", "resting-state fMRI" → `rs-fMRI`
- "Task-based fMRI (BOLD)", "Task fMRI (emotion …)" → `task-fMRI (BOLD)`／`task-fMRI (emotion)`
- "fMRI; T1-weighted MRI" → `fMRI; T1w MRI`（rest/task 不明時は `fMRI`）

補助語

- "(functional connectivity)", "ALFF" などは括弧に集約: `rs-fMRI (FC)`, `rs-fMRI (ALFF)`

### Diffusion MRI

- "Diffusion MRI", "dMRI", "DWI" → `dMRI`
- "DTI: FA, MD, RD, AxD", "FA/MD/RD/AxD" → `dMRI (DTI: FA, MD, RD, AxD)`
- "Diffusion Spectrum Imaging (DSI)" → `DSI`

### PET

- "Amyloid PET (AV45/Florbetapir)" → `Amyloid PET (18F-Florbetapir)`
- "Tau PET (Flortaucipir/FTP)" → `Tau PET (18F-Flortaucipir)`
- その他: `[11C]-(+)-PHNO PET`, `[18F]FDOPA PET` はトレーサー名称をそのまま括弧内に保持。

### EEG/MEG

- "EEG", "Resting-state EEG" → `EEG`／`rs-EEG`
- "HD-EEG (128 channels)" → `HD-EEG (128ch)`
- "MEG (resting-state, eyes-closed)" → `MEG (rs, eyes-closed)`

### その他

- 定量 MRI: `R1 (MP2RAGE)`, `R2* (ME-GRE)`, `QSM`
- 眼科: `OCT`

## ノイズ/欠損

- `Yes` → 無効値（該当するモダリティ名に置換できない場合は空欄）
- `NR`/空文字 → 欠損

## 正規化ルール（実装指針）

- セパレータはセミコロン `;` を採用。
- 前処理
    - 末尾の `.` を削除
    - 余分な空白を正規化
    - 全角記号は半角へ
- マッピング（例）
    - `/\bT1[- ]?weighted\b.*MRI/i` → `T1w MRI`（括弧内容は残す）
    - `/\bstructural MRI\b/i` → `T1w MRI`
    - `/\brs[- ]?fMRI\b|resting[- ]state fMRI/i` → `rs-fMRI`
    - `/\btask[- ]?based fMRI\b|\btask fMRI\b/i` → `task-fMRI`
    - `/\bdiffusion( |-)?weighted( |-)?imaging\b|\bdwi\b/i` → `dMRI`
    - `/\bdiffusion MRI\b|\bdMRI\b/i` → `dMRI`
    - `/\bDTI\b.*(FA|MD|RD|AxD)/i` → 付帯情報として括弧に保持
    - `/\bDSI\b|diffusion spectrum imaging/i` → `DSI`
    - `/(AV45|Florbetapir)/i` → `18F-Florbetapir`
    - `/(Flortaucipir|FTP)/i` → `18F-Flortaucipir`
    - `/\bEEG\b/i` → `EEG`（`HD`, `128` を検出したら `HD-EEG (128ch)`）
    - `/\bMEG\b/i` → `MEG`
    - `/\bFLAIR\b/i` → `T2-FLAIR`
    - `/\bQSM\b/i` → `QSM`
- 例外
    - `Yes`/`NR` は空欄へ

## よくある並列表現の正規化例

- `T1-weighted MRI; Resting-state fMRI` → `T1w MRI; rs-fMRI`
- `T1-weighted MRI; Amyloid PET (AV45/Florbetapir); Tau PET (Flortaucipir)` →
  `T1w MRI; Amyloid PET (18F-Florbetapir); Tau PET (18F-Flortaucipir)`
- `fMRI (resting-state and task-fMRI); Structural MRI (T1)` → `rs-fMRI; task-fMRI; T1w MRI`
- `Diffusion MRI (DTI: FA, MD, RD, AxD)` → `dMRI (DTI: FA, MD, RD, AxD)`

## データ由来の代表例（抜粋）

- Structural: `T1-weighted MRI`, `T1-weighted MRI (MPRAGE)`, `Structural MRI (T1-weighted)`
- fMRI: `Resting-state fMRI`, `Task-based fMRI (BOLD)`
- Diffusion: `Diffusion MRI (FA, MD, RD, AxD from DTI)`, `Diffusion Spectrum Imaging (DSI)`
- PET: `T1-weighted MRI; Amyloid PET (Florbetapir); Tau PET (Flortaucipir)`, `[11C]-(+)-PHNO PET; [18F]FDOPA PET`
- EEG/MEG: `HD-EEG (128 channels)`, `MEG (resting-state, eyes-closed)`

---

必要に応じて、このガイドラインに沿って正規化関数を実装し、SummaryView の Modality 列に正規化結果を表示することも可能です。
