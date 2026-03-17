# JHBM2026 ポスター作成前の調査結果まとめ

## 1. 調査の目的

JHBM2026 ポスターでは、Normative Modeling（NM）研究の横断的傾向と、報告標準化の課題を示す。
そのため、抄録（`abstract_ver5.md`）で定義した項目を中心に、既存データを再点検した。

## 2. 調査対象と情報源

- 抄録提出版（ver5）: `./docs/20251202_jhbm-2026/abstracts/abstract_ver5.md`
  - ポスター用に ver6 を作成予定（ver6 の作成はまだ不要）。
- 横断集計の基礎データ（旧版）: `./docs/20251202_jhbm-2026/figs/record_ver20251215.csv`
- 横断集計の基礎データ（最新版）: `./docs/20251202_jhbm-2026/figs/record_ver20260313.csv`
  - 行数: **140行**（ゴミ行 2件除去済み）、カラム: authorYear / phase / N / age_mean / age_sd / age_min / age_max / female_pct / modality / origin / dataset / disease
  - 140行の内訳は以下の通りであり、有効文献数は **122件**。
    - 有効文献: 122件
    - phase 別複数行（同一文献が Train / Overall 等で重複）: 18行
  - データソース: **human タグ付き JSON のみを使用**（codex / claude / gemini 由来は除外）。
  - アプリの localhost エンドポイントとの照合は未実施。ポスター用の最終集計前に `/summary-view` 系画面での目視確認を要する。
  - git 未コミットのため、バージョン管理への追加を推奨。
- 解析メモ・標準化検討: `./docs/20251202_jhbm-2026/research_make-abstract-for-jhbm2026.md`
- 実装上の確認画面:
  - `http://localhost:8080/de-result-overview`
  - `http://localhost:8080/summary-view`
  - `http://localhost:8080/summary-view-2`
  - `http://localhost:8080/summary-view-3`
  - `http://localhost:8080/summary-view-4`
  - `http://localhost:8080/summary-view-5`
  - `http://localhost:8080/summary-view-6`

## 3. 主要な調査結果（ポスター掲載候補）

以下の集計はすべて `record_ver20260313.csv` から、phase 重複解消（**Train 優先 > Overall**; `DE_Guide_v13.md` の優先度ルールに準拠）した **122件** を対象とする。
モダリティ・疾患名の正規化は `Data-Extraction-Protocol.md` のキーワード体系に従い原稿づくりの段階で適用する。

### 3.1 文献規模と対象

- 対象文献は **122 件**。
- 精神疾患・神経疾患を横断し、SCZ、ASD、MDD、AD、BD の比重が高い。
  - 下表は **疾患別研究件数**（1研究が複数疾患を対象とする場合は各疾患に1件ずつ計上）。文献数とは異なる。

  | 疾患 | 研究件数（疾患別集計） |
  |------|------|
  | Schizophrenia (SCZ) | 36 |
  | Autism Spectrum Disorder (ASD) | 27 |
  | Major Depressive Disorder (MDD) | 19 |
  | Alzheimer's Disease (AD) | 19 |
  | Bipolar Disorder (BD) | 18 |
  | Attention-Deficit/Hyperactivity Disorder (ADHD) | 13 |
  | Mild Cognitive Impairment (MCI) | 13 |
  | First-Episode Psychosis (FEP) | 5 |
  | Obsessive-Compulsive Disorder (OCD) | 5 |
  | Early Psychosis (EP) | 3 |

### 3.2 サンプル構成（代表値）

- N は広い分布を示し、中央値 **770**（IQR 321-6909）。
  - 根拠: 122件すべてで N 値が記載されていた。
- 年齢平均の中央値は **33.90 歳**（IQR 17.14-46.90）。
  - 根拠: 122件中 89件に age_mean の記載あり（欠損 33件、27%）。
  - 欠損補完は学会後の作業とし、現段階では実施しない。
- 女性比率の中央値は **51.0%**（IQR 44.7-54.0%）。
  - 根拠: 122件中 102件に female_pct の記載あり（欠損 20件、16%）。

#### age_mean 欠損の AuthorYear（35件）

以下の文献は `record_ver20260313.csv` の `age_mean` が未記入。

Bethlehem2021, Cirstian2024, Echave2024, Fang2024, Feng2024, Feng2025, Floris2021, FukamiGartner2023, GarciaSanMartin2025, Italinna2023, Janahi2022, Janssen2021, Ji2023, Jia2025, Kia2022, Kobbersmed2025, Kumar2024, Lamsma2024, Leiberg2023, Little2025, Mao2025, Mendes2024, Rutherford2023, Savage2024, Segal2023, Segal2025, Shao2024, Tabbal2025, Tong2024, Verdi2023, Vieira2025, VillalonReina2024, Wolfers2021, Worker2023, Yu2024

### 3.3 モダリティ・モデル起源

`Data-Extraction-Protocol.md` の分類基準（sMRI = T1w+T2w / fMRI / dMRI / Other）に従って正規化した（複数モダリティを持つ研究は重複カウント）。

**Modality（122件、複数回答あり）**:

| カテゴリ | 件数 | 含まれる記録値 |
|---------|------|--------------|
| sMRI | 95 | T1w MRI, T2w MRI |
| fMRI | 27 | fMRI |
| dMRI | 9 | dMRI |
| Other | 27 | PET, EEG, MEG, Others（qMRI・網膜画像等を含む） |

**Origin（122件）**:

| 起源 | 件数 |
|------|------|
| New（新規構築） | 100 |
| Pre-trained（既存モデル利用） | 22 |

- Little2025 の origin は "Yes" と記録されていたが、**New** であることが確認された。CSV の該当セルを修正すること。

### 3.4 研究報告の傾向

DE_v10 human JSON の `caa8_key_findings_brief` フィールドから、**107/122件**（88%）について findings を抽出した。キーワード頻度は以下の通り。

| キーワード | 該当件数（107件中） |
|-----------|------|
| deviation（偏位） | 66（62%） |
| normative | 34（32%） |
| individual（個人差） | 29（27%） |
| cortical（皮質） | 21（20%） |
| age | 19（18%） |
| network（ネットワーク） | 13（12%） |
| site | 11（10%） |
| heterogeneity（不均一性） | 9（8%） |
| spatial（空間的） | 6（6%） |
| transdiagnostic（疾患横断） | 5（5%） |

**ver5 記述との照合**: ver5 の「個人偏位の空間的不均一性が主要所見」（deviation 62%、individual 27%、heterogeneity 8%、spatial 6%）および「皮質領域・ネットワーク単位に集中」（cortical 20%、network 12%）は最新データでも支持される。**ver5 の傾向記述は維持して良い。**

補足: `/summary-view-6` は disorder（臨床コホート）情報の表示画面であり findings は含まない。findings のソースは DE_v10 human JSON の `caa8_key_findings_brief`（`summary-view` ベース画面の CAA8 列に対応）。

#### findings 欠損の AuthorYear（15件）

以下の文献は DE_v10 human JSON が未整備のため findings を抽出できていない（学会後対応でも可）。

Baldwin2022, Bayer2022, Bedford2025, Bethlehem2020, Bethlehem2021, Bhome2024, CardenasDeLaParra2019, Chan2025A, Chan2025B, Chien2022, Cirstian2024, Coupe2022, Elad2021, Gimbel2025, Haas2024


## 4. 追加資料: Takamatsu2026 草稿（NM_draft46）が示すポスターのストーリー

### 4.1 資料の位置づけ

`docs/20251202_jhbm-2026/PDFs/Importants/optimized/Takamatsu2026/NM_draft46.docx.md`

NM に関する系統的レビューおよび **NORMA 報告チェックリスト**を提案する論文草稿（高松ら、投稿準備中）。本ポスターの背景・考察のストーリーラインと直接対応する。

### 4.2 Case-control パラダイムの限界（定量的根拠）

| 知見 | 出典 |
|------|------|
| SCZ の海馬体積縮小: Cohen's d ≈ −0.46（患者・対照群の分布は大きく重複） | van Erp 2016（ENIGMA） |
| SCZ・BD・MDD の皮質下体積 effect-size profile の相関: r = 0.95–0.98（診断非特異的） | Cheon 2022 |
| 脳構造と行動表現型の再現可能な関連には数万人規模が必要 | Marek 2022 |
| 8大精神疾患のリスク遺伝子座の約 3/4 が複数疾患に共有（pleiotropic） | Cross-Disorder Group PGC 2019 |

### 4.3 個人偏位の不均一性（NM の必要性を支持するエビデンス）

| 知見 | 出典 |
|------|------|
| 6疾患横断解析: 同一脳領域に偏位が共存する患者割合 **< 7%** | Segal 2023 |
| SCZ 322例: 単一領域の infra-normal 偏位 ≤ 17% / 脳内のどこかに偏位あり: **79%** | Lv 2021 |
| MDD 機能的結合: 同一領域に extreme deviation を示す患者 **< 3%** | Sun 2023 |
| ASD: 極端な偏位者を除外すると群間有意差の **半数が消失** | Bethlehem 2020 |
| ASD: 多数派と逆符号の構造的偏位を示す患者 **≈ 28%** | Shan 2022 |
| SCZ 5,600例以上のクラスタリング: SCZ の **51%** が正常範囲内の biotype に属する | Okada 2023 |
| うつ・不安の fMRI biotype 6種は診断カテゴリに対応せず; 1種はHCと差なし | Tozzi 2024 |

### 4.4 NORMA チェックリストの位置づけと活用方針

Takamatsu2026 草稿は NM 研究の方法論的透明性と比較可能性を高めるための **NORMA チェックリスト**を提案する内容である。ただし**本論文は未発表・投稿準備中**であり、ポスターで直接引用することはしない。

活用方針:
- 背景・考察の内容を組み立てる際の**参考資料**として使用する
- ポスター本文では「NORMA チェックリスト」という語は**将来的な標準化の方向性**として言及するに留める（「このようなチェックリストの整備が必要」という文脈）
- Takamatsu2026 本人は共著者であるため、議論の文脈を共有した上でポスターのストーリーに反映する

本ポスターの Reporting Minimum Set と NORMA の概念的対応（内部参照用）:

| 本ポスターの Reporting Minimum Set | NORMA が扱う領域 |
|------------------------------------|----------------|
| Phase（Train/Overall）の系統的記録 | モデル構築・検証手順の透明性 |
| 年齢統計の完全性（mean/sd/median/iqr/min/max） | 参照コホートの記述統計の標準化 |
| モダリティ表記の統一 | 計測手法の標準報告 |
| 疾患名の正規化 | 対象集団の標準記述 |

### 4.5 ポスターストーリーへの反映方針

**背景セクション**（強化ポイント）:
- Case-control 限界を定量値で示す（d ≈ −0.46、r = 0.95–0.98）
- NM を「脳の成長曲線」として直感的に説明（成長曲線アナロジー）
- 個人偏位の不均一性エビデンスを 1–2 文で提示（<7%、79%）

**考察セクション**（強化ポイント）:
- 「本横断集計が示す報告欠損・表記揺れは NM 研究の横断比較を阻む実際的な障壁である」と記述
- Reporting Minimum Set の 4 点を提案し、「NORMA チェックリストのような標準化ツールの整備が今後必要」という方向性に言及する（Takamatsu2026 の直接引用は行わない）

---

## 5. ポスター作成に向けた実務上の示唆

### 5.1 ストーリーライン

- 背景: 診断群比較の限界（定量的根拠）と NM の必要性・成長曲線アナロジー
- 方法: PRISMA 準拠の文献抽出とメタ情報正規化
- 結果: 文献分布・デモグラフィ・モダリティ・疾患分布
- 議論: Reporting Minimum Set の提案（NORMA チェックリストのような標準化ツールの必要性に言及）

### 5.2 図表で優先すべき項目

図表は JupyterNotebook or R 上で確認しながら生成する予定。

- **研究数の四半期別推移グラフ**（優先的に掲載）
  - 既存参考ファイル: `figs/fig10_modality_trend_by_quarter_cumulative_stacked.png`
- N、年齢、女性比率の分布図（箱ひげ + Table 1）
- モダリティ内訳（sMRI / fMRI / dMRI / Other の 4 分類）
- 疾患カテゴリ頻度（上位疾患）
- Model origin（New vs Pre-trained）

## 6. 残課題（ポスター反映前に要対応）

- Little2025 の origin を CSV で "Yes" → "New" に修正し git コミット（修正済み、コミット未実施）。
- モダリティ表記の正規化（`Data-Extraction-Protocol.md` の sMRI/fMRI/dMRI/Other 4カテゴリ、原稿づくりの段階で適用）。
- 疾患名の正規化（`Data-Extraction-Protocol.md` に従い、原稿づくりの段階で適用）。
- localhost エンドポイントとの目視照合（human JSON 由来データのみ使用）。
- findings 未収録の 15件の補完（学会後対応でも可、対象 AuthorYear は 3.4 節に記載）。
- どの集計を「本文図」、どれを「補足表」に回すかの確定。
- 年齢統計の欠損補完は学会後の作業とする（対象 AuthorYear は 3.2 節に記載）。

## 7. 直近の作業方針

- `make-figures-and-tables.ipynb`（R カーネル）と `manuscript.md` を作成する（計画書: `plan_make-manuscript-for-poster.md`）。
  - 参照元データ: `record_ver20260313.csv`（ポスターの集計表はこの CSV から生成する）。
  - 背景・考察は §4（Takamatsu2026 草稿）の知見・参考文献を反映する。
- 最終的なポスター化は `Md2Poster` を利用する（ユーザーが実施）。
