# 計画書：ポスター用原稿・図表スクリプト作成

ver. 2026-03-13

---

## 0. この計画書の位置づけ

本書は以下の 2 ファイルを作成するための計画書である。実際のコンテンツはまだ書かない。

| 作成対象ファイル | 内容 |
|---|---|
| `docs/20251202_jhbm-2026/manuscript.md` | ポスター用テキスト原稿（日本語）。Md2Poster に渡す MD ファイル。 |
| `docs/20251202_jhbm-2026/make-figures-and-tables.ipynb` | 図表生成ノートブック（**R カーネル**の Jupyter Notebook）。PNG を `figs/` に出力する。 |

前提資料:
- 調査まとめ: `docs/20251202_jhbm-2026/research_make-poster-for-jhbm2026.md`
- 提出版抄録: `docs/20251202_jhbm-2026/abstracts/abstract_ver5.md`
- データ: `docs/20251202_jhbm-2026/figs/record_ver20260313.csv`（140行、有効122件、Little2025の origin 修正済み）
- 出版年マッピング: `docs/20251202_jhbm-2026/figs/pubdate_by_authorYear.tsv`（authorYear / doi / pub_year / pub_month）
- 正規化ルール: `docs/20260302_data-extraction-protocol/Data-Extraction-Protocol.md`

---

## 1. データ前処理の仕様（ノートブック冒頭部に実装）

`make-figures-and-tables.ipynb`（R カーネル）の最初のチャンクとして実装する。

### 1.1 入力

```
input 1: docs/20251202_jhbm-2026/figs/record_ver20260313.csv
  タブ区切り、ヘッダ行あり
  カラム: authorYear / phase / N / age_mean / age_sd / age_min / age_max /
          female_pct / modality / origin / dataset / disease

input 2: docs/20251202_jhbm-2026/figs/pubdate_by_authorYear.tsv
  タブ区切り、ヘッダ行あり
  カラム: authorYear / doi / pub_year / pub_month
  件数: 100件（残 22件は pub_year が未取得）
```

### 1.2 ゴミ行・重複の除去

- ゴミ行除去: `authorYear %in% c("settings", "Someone20XX")` を除外（防衛的実装）
- phase 重複解消: 同一 `authorYear` が複数行ある場合、優先度 **Train > Overall > Uninvestigated > その他** の順で代表行を 1 行選択（根拠: `DE_Guide_v13.md`）
- 結果: **122件** の 1文献1行データフレーム `df`

### 1.3 モダリティ正規化

`Data-Extraction-Protocol.md` の 4カテゴリに変換（1研究が複数カテゴリを持つ場合は重複許容）。

| 記録値 | 正規化後 |
|--------|---------|
| T1w MRI, T2w MRI | sMRI |
| fMRI | fMRI |
| dMRI | dMRI |
| PET, EEG, MEG, Others, qMRI, retinal imaging 等 | Other |

実装: `modality` 列をセミコロン分割 → 各トークンをルール表で変換 → 長形式データフレーム `df_mod`（1行 = 1文献×1カテゴリ）

### 1.4 疾患名正規化

`Data-Extraction-Protocol.md` の Clinical Cohort Info カテゴリ体系に従い、`disease` 列をセミコロン分割して長形式 `df_dis`（1行 = 1文献×1疾患）を生成。
- 括弧内の略語（例: `Schizophrenia (SCZ)`）の表示は略語のみに短縮する。

### 1.5 出版年の取得

`pubdate_by_authorYear.tsv` を `df` に left_join して `pub_year` / `pub_month` を付与する。

- **一次ソース**: `pubdate_by_authorYear.tsv`（DOI 経由で取得した正確な出版年。100件をカバー）。
- **フォールバック**: TSV に存在しない 22件については `authorYear` 末尾の 4桁数字から `pub_year` を補完（`A`/`B` サフィックスは除去して変換）。
- 未取得 22件: Berthet2025, Cirstian2024, Coupe2022, Feng2024, Feng2025, FukamiGartner2023, Ge2024, Han2023, Janssen2021, Ji2023, Jia2025, Jing2023, Kumar2024, Lin2024, Loreto2024, Pinaya2019, RehakBuckova2025, Rutherford2022, Verdi2024, Yang2025, Yu2024, Zhang2022

---

## 2. 図表一覧と仕様

出力解像度: **300 dpi**、フォーマット: PNG。

### Fig.1　研究数の年次推移（優先図）

| 項目 | 仕様 |
|------|------|
| 変数 | `pub_year`（2005–2025） |
| グラフ種 | 累積積み上げ棒グラフ（x: 年、y: 研究件数、fill: モダリティ 4カテゴリ） |
| 参考既存図 | `figs/fig10_modality_trend_by_quarter_cumulative_stacked.png` |
| 出力ファイル | `figs/fig_trend_by_year.png` |
| 注意 | 旧図は旧データに基づく。新ノートブックで `record_ver20260313.csv` から再生成する。 |

### Fig.2　モダリティ内訳

| 項目 | 仕様 |
|------|------|
| 変数 | `df_mod` の正規化モダリティカテゴリ（sMRI / fMRI / dMRI / Other） |
| グラフ種 | **横棒グラフ**（x: 件数、y: カテゴリ、降順ソート） |
| 棒 vs 円の選択根拠 | カテゴリが 4種、かつ「複数回答あり（重複カウント）」のため円グラフ不適（全体の割合として解釈できない）。棒グラフが正確。 |
| キャプション注記 | 「複数モダリティを持つ研究は重複カウント」 |
| 出力ファイル | `figs/fig_modality_bar.png` |

### Fig.3　疾患別研究件数

| 項目 | 仕様 |
|------|------|
| 変数 | `df_dis` の正規化疾患名（上位 10件を表示） |
| グラフ種 | **横棒グラフ**（降順ソート） |
| 棒 vs 円の選択根拠 | カテゴリが 10種。円グラフは 5種超で可読性が著しく低下し、値の大小比較も困難。棒グラフ一択。 |
| キャプション注記 | 「疾患別集計・1研究が複数疾患を対象とする場合は重複カウント」 |
| 出力ファイル | `figs/fig_disease_bar.png` |

### Fig.4　N の分布

| 項目 | 仕様 |
|------|------|
| 変数 | `df$N`（122件全件） |
| グラフ種 | 箱ひげ図 + jitter（対数スケール推奨）。中央値・IQR をアノテーション。 |
| 要約統計 | 中央値 770、IQR 321–6909 |
| 出力ファイル | `figs/fig_N_boxplot.png` |

### Fig.5　年齢平均の分布

| 項目 | 仕様 |
|------|------|
| 変数 | `df$age_mean`（89件、欠損 33件は除外し除外数を図中に注記） |
| グラフ種 | 箱ひげ図 + jitter。中央値・IQR をアノテーション。 |
| 要約統計 | 中央値 33.90歳、IQR 17.14–46.90 |
| 出力ファイル | `figs/fig_age_boxplot.png` |

### Fig.6　女性比率の分布

| 項目 | 仕様 |
|------|------|
| 変数 | `df$female_pct`（102件、欠損 20件は除外し除外数を図中に注記） |
| グラフ種 | 箱ひげ図 + jitter。50% ラインを reference line として追加。 |
| 要約統計 | 中央値 51.0%、IQR 44.7–54.0% |
| 出力ファイル | `figs/fig_female_boxplot.png` |

### Fig.7　Model Origin

| 項目 | 仕様 |
|------|------|
| 変数 | `df$origin`（New: 100件 / Pre-trained: 22件） |
| グラフ種 | **円グラフ**（カテゴリ 2種、全体に対する割合を示す目的に適合） |
| 棒 vs 円の選択根拠 | 2カテゴリで「全体の中での比率」を直感的に伝えたい場合は円グラフが有効。ただし件数ラベルも付記する。 |
| 出力ファイル | `figs/fig_origin_pie.png` |

### Table 1　研究サマリー統計表

| 項目 | 仕様 |
|------|------|
| 内容 | 文献数・N 代表値・年齢代表値・女性比率・モダリティ件数・Origin 件数をまとめた 1枚の統計表 |
| 実装 | R の `gt` パッケージで生成し PNG 出力（または `knitr::kable` で Markdown 出力） |
| 出力ファイル | `figs/table1_summary.png` |

---

## 3. manuscript.md の構成仕様

### 3.1 ファイルの目的

Md2Poster（入力形式: MD ファイル）に渡すポスター用テキスト原稿。日本語で書く。ポスター形式は**A0 縦**。
数値は `record_ver20260313.csv` から集計した最新値を使用し、ver5 抄録の旧値は使わない。

### 3.2 セクション構成

```
# [タイトル]
精神神経画像の規範モデリング：横断分析と標準化課題

## 著者・所属
上田一生¹⁷, 高松直岐², 齋藤慶人³, ... （ver5 の著者情報に準ずる）

## 背景
- 診断群比較の限界（脳計測値の診断横断的異質性）
- NM の意義（個人偏位の推定、診断非依存評価）
- 方法・報告の標準化が不十分
→ 2–3 文の簡潔な記述

## 方法
- PRISMA 2020 準拠、2005–2025 年
- 抽出項目: Dataset / N / RC Age / Sex / Modality / Model Origin / Disease
- データ収集ツール: DEQACheck
→ 箇条書き 3–4 項目

## 結果

### 文献概要
- 122件同定
- [Fig.1 挿入: 研究数の年次推移]

### 参照コホートの特性
- N: 中央値 770（IQR 321–6909）
- 年齢平均: 中央値 33.9歳（IQR 17.1–46.9）
- 女性比率: 中央値 51.0%（IQR 44.7–54.0%）
- [Fig.4–6 挿入または Table 1 挿入]

### モダリティ・モデル起源
- sMRI 最多（95件）、fMRI 27件、dMRI 9件、Other 27件
- New 100件 vs Pre-trained 22件
- [Fig.2, Fig.7 挿入]

### 対象疾患
- SCZ 36件、ASD 27件、MDD 19件、AD 19件、BD 18件（疾患別集計）
- [Fig.3 挿入]

## 考察
- 報告情報の欠損・表記揺れが横断比較を困難にする
- Reporting Minimum Set の明確化が必要
  1. Phase（Train/Overall）の系統的記録
  2. 年齢統計（mean/sd/median/iqr/min/max）の完全性
  3. モダリティ表記の統一
  4. 疾患名の正規化

## 結論
- NM 文献 122件の横断集計を行い方法論的傾向を把握した
- 報告標準化課題（Reporting Minimum Set）を提示

## 参考文献（簡略）
- PRISMA 2020（Page et al.）
- Marquand et al. 2016, 2019
- Rutherford et al. 2022
- Bethlehem et al. 2022
```

### 3.3 数値の対照表（ver5 旧値 → 原稿使用値）

| 項目 | ver5 旧値 | 原稿使用値（最新） |
|------|---------|---------|
| 文献数 | 122件 | 122件（変わらず） |
| N 中央値 | 822（IQR 326–7728） | 770（IQR 321–6909） |
| 年齢平均 中央値 | 33.9歳（IQR 17.06–46.9） | 33.90歳（IQR 17.14–46.90） |
| 女性比率 中央値 | 51% | 51.0%（IQR 44.7–54.0%） |
| sMRI | 96件（T1w MRI） | 95件（sMRI = T1w+T2w） |
| fMRI | 29件 | 27件 |
| dMRI | 9件 | 9件（変わらず） |
| New | 95件 | 100件 |
| Pre-trained | 25件 | 22件 |

---

## 4. 作業順序

```
Step 1  make-figures-and-tables.ipynb の作成（R カーネル）
        └─ データ前処理チャンク（§1）
        └─ 各図のチャンク（§2）
        └─ Table 1 生成チャンク（§2）
        └─ ノートブック実行 → figs/*.png を確認

Step 2  manuscript.md の作成
        └─ §3 の構成に従いテキストを記述
        └─ 図の挿入位置に ![fig](figs/fig_xxx.png) を記述
        └─ §3.3 の数値対照表に従い旧値を更新

Step 3  Md2Poster への入力（A0 縦）
        └─ manuscript.md を Md2Poster に入力
        └─ 図サイズ・フォントを調整
```

---

## 5. 未解決事項

| 事項 | 状況 |
|------|------|
| `record_ver20260313.csv` の git コミット（Little2025 修正含む） | 要実施 |
| `pubdate_by_authorYear.tsv` の git コミット | 要実施 |
| pubdate 未取得 22件の年次推移への影響確認 | ノートブック実行時に目視確認 |
| ポスターサイズ | A0 縦 |
| Md2Poster の入力フォーマット | MD ファイル |
| 図の解像度 | 300 dpi |
| 年齢統計の欠損補完（35件） | 学会後に対応 |
| findings 欠損 15件の補完 | 学会後に対応 |
