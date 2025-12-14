# N / RC Age / Sex の表現パターン調査と正規化案

## 依頼事項

SummaryView.javaが参照しているJSONファイルの調査をお願いします。
N列、RC Age列、Sex列が参照している要素の値を正規化する方法を考えています。
以下の方針や懸念事項を考慮した場合、現在の記述がどのように変更されるかを教えてください。

* N列要素
  * 数字にコンマが混じっていることがありますがこれは取り除いてください。
  * Whole は Overall と同義。Overallに統一
  * 複数情報が併記されていることがあります。これは論文中に記載されているnormative modelが複数ある場合、異なる解析Phaseでの値をメモしているためです。「何番目のモデルか」「モデルの名前（わかる場合）」「解析Phese（Overall or Train）」などの場合があります。どのように統一的に表現するか決めかねています。
  * stated は exact と同義。exactに統一
* RC Age列について
  * mean sd median iqr min max の情報を集めたいです。不明場合は数値ではなく NR としたい。例えば  「functioinal models; Whole; 22000 (approx); 39.85 (weighted); 7.94 (pooled); 2 (inffered); 100 (inffered)
  * 概算値の場合があり、その場合は approx. を添えたい
  * 本文中の記述から推定された場合があり、その場合は innfered を添えたい
  * weited meanの場合があり、その場合は weighted を添えたい
  * pooled sd の場合があり、その場合は pooled を添えたい
  * 数字にコンマが混じっていることがありますがこれは取り除いてください。
    *　Whole は Overall と同義。Overallに統一
  * 複数情報が併記されていることがある。これは論文中に記載されているnormative modelが複数ある場合、異なる解析Phaseでの値をメモしているためです。「何番目のモデルか」「モデルの名前（わかる場合）」「解析Phese（Overall or Train）」などの場合があります。どのように統一的に表現するか決めかねています。
  * stated は exact と同義。exactに統一
* Sex列について
  * 「F 7458 （50.7%), M 7250 (49.3%)」のような表記で統一したい。
  * ただし、複数のNormative Modelを取り扱っている研究、異なる解析Phaseでの値を報告している研究があり、その場合の表記をどうするか。
* 特にRutherford2023での状況が複雑。Rutherford2023に対応する表記方法を確立して、同じ表記方法を他の研究にも適応する。

調査結果は ./spec/n_rc_age_sex_pattern.md へお願いします。

## 以下AI回答

本メモは、SummaryView.java が参照している Human 系 JSON（`share_package/data/*/DE/json*/DE_*human*.json` など）における以下 3 列の現在の記述と、それを踏まえた正規化方針・表記ルール案をまとめたものです。

- N 列: `reference_cohort_and_imaging_part.rci2_hc_n`
- RC Age 列: `reference_cohort_and_imaging_part.rci3_hc_age`
- Sex 列: `reference_cohort_and_imaging_part.rci4_hc_sex`

調査は 2025-12-14 時点の Human 系 108 JSON を対象に実施しました。





**サマリ（出現傾向）**
- N: 複数行（複数 NM/Phase 記述）あり 10 件、カンマ入り数値あり、`Whole/Overall` 混在、`stated/exact` 混在、`approx` あり。
- RC Age: 複数行あり 13 件、`Whole/Overall` 混在、`weighted/pooled`、`inferred` 記述あり、`stated/exact` 混在、カンマ入り数値あり。
- Sex: 複数行あり 12 件、`F/M` と百分率の併記が大半、`NR` 併記あり、全角括弧混在。


## 正規化の基本ポリシー（共通）
- 数値のカンマ区切りは除去（例: `14,708` → `14708`）。
- `Whole` は `Overall` に統一。
- `stated` は `exact` に統一。
- モデルや Phase（Overall/Train）など複数情報が併記される場合は「モデルタグ」と「Phase タグ」を明示して並記する（下記の表記規則）。
- セル内の複数レコードは改行で区切る（`\n`）。テーブル幅の都合で 1 行に圧縮する場合は ` | ` 区切り。


## 表記規則（提案）

### 1) N 列（rci2_hc_n）
- 形式: `Model: <model_tag> | Phase: <Overall|Train> | N: <int> <exact|approx>`
- 任意: モデル名が明確なときは `<model_tag>` を `NM1 functional` のように「NM番号 + 短い識別名」で表記。
- 複数 NM/Phase の場合は、上記 1 レコードを改行で複数並べる。
- 例（Rutherford2023 の元記載 → 正規化）
  - 元: `NM1 (functioinal models) 22000 approx Overall`
  - 新: `Model: NM1 functional | Phase: Overall | N: 22000 approx`

補足（抽出ルール）:
- `approx`/`exact` を含む語を検出して付記（`stated` は `exact` に置換）。
- `Overall/Train/Whole` を検出し Phase にマップ（`Whole`→`Overall`）。
- 先頭の `NM1/NM2`、括弧や語から機能名（functional/structural など）を簡易抽出して `<model_tag>` に反映。


### 2) RC Age 列（rci3_hc_age）
- 目的: `mean, sd, median, iqr, min, max` を抽出・整形。未知は `NR`。
- 形式: `Model: <model_tag> | Phase: <Overall|Train> | mean: <val>[ weighted] | sd: <val>[ pooled] | median: <val|NR> | iqr: <val|NR> | min: <val|NR>[ inferred] | max: <val|NR>[ inferred]`
- 任意: 入力に N が含まれる場合は N 列に移す。Age 列では年齢統計のみを保持。
- 例（Rutherford2023 の元記載 → 正規化）
  - 元: `functioinal models; Whole; 22000 (approx); 39.85 (weighted); 7.94 (pooled); 2 (inffered); 100 (inffered)`
  - 新: `Model: functional | Phase: Overall | mean: 39.85 weighted | sd: 7.94 pooled | median: NR | iqr: NR | min: 2 inferred | max: 100 inferred`
- 例（`stated` の扱い）
  - 元: `... 46.9 (stated); 24.4 (stated) ...`
  - 新: `... mean: 46.9 exact | sd: 24.4 exact ...`（内部的には `exact` 扱いだが、最終表示では `exact` の明記は省略可）

補足（抽出ルール）:
- セミコロン `;` 区切りでトークン化し、Phase/モデル/N/統計量を分類。
- `weighted`/`pooled`/`inferred(typo: inffered)`/`approx` のラベルを検出して該当統計にタグ付け。
- 数値のカンマ除去と単位/余計な括弧の削除（`(weighted)` などはタグに変換）。
- `median/iqr` が見当たらない場合は `NR` とする。


### 3) Sex 列（rci4_hc_sex）
- 形式: `F <count> (<pct>%), M <count> (<pct>%), NR <count> (<pct>%)`
- 括弧は半角、百分率は `%`、小数点表現は入力に合わせ 1 桁（または 1–2 桁）に丸め。
- 複数 NM/Phase の場合は `Model/Phase` の見出しを付けて改行で並べる。
- 例（Rutherford2023 の元記載 → 正規化）
  - 元: `functional models: F 7458 （50.7%), M 7250 (49.3%)`
  - 新: `Model: functional | F 7458 (50.7%), M 7250 (49.3%)`
  - 元: `structure models: F 15033 (51.1%), M 14385 (48.9%)`
  - 新: `Model: structural | F 15033 (51.1%), M 14385 (48.9%)`

補足（抽出ルール）:
- 全角括弧 `（` `）` は半角に置換。
- `NR` の人数・比率がある場合は第三要素として併記（ない場合は省略）。
- `Phase` 情報が Sex に含まれる場合は先頭に `Phase: ...` を付与しても良い（省略可）。


## Rutherford2023 への適用例（3 列すべて）

元データ（抜粋）:
- N: `NM1 (functioinal models) 22000 approx Overall` / `NM1 (functioinal models) 11889 exact Train` / `NM2 (structural models) 58000 approx Overall` / `NM2 (structural models) 29418 exact Train`
- RC Age: `functioinal models; Whole; 22000 (approx); 39.85 (weighted); 7.94 (pooled); 2 (inffered); 100 (inffered)` など 4 レコード
- Sex: `functional models: F 7458 （50.7%), M 7250 (49.3%)` / `structure models: F 15033 (51.1%), M 14385 (48.9%)`

正規化後（提案表記）:
- N（4 行）
  - `Model: NM1 functional | Phase: Overall | N: 22000 approx`
  - `Model: NM1 functional | Phase: Train | N: 11889 exact`
  - `Model: NM2 structural | Phase: Overall | N: 58000 approx`
  - `Model: NM2 structural | Phase: Train | N: 29418 exact`
- RC Age（4 行）
  - `Model: functional | Phase: Overall | mean: 39.85 weighted | sd: 7.94 pooled | median: NR | iqr: NR | min: 2 inferred | max: 100 inferred`
  - `Model: functional | Phase: Train   | mean: NR | sd: NR | median: NR | iqr: NR | min: 2 inferred | max: 100 inferred`
  - `Model: structural | Phase: Overall | mean: NR | sd: NR | median: NR | iqr: NR | min: 2 inferred | max: 100 inferred`
  - `Model: structural | Phase: Train   | mean: 46.9 exact | sd: 24.4 exact | median: NR | iqr: NR | min: 2 inferred | max: 100 inferred`
- Sex（2 行）
  - `Model: functional | F 7458 (50.7%), M 7250 (49.3%)`
  - `Model: structural | F 15033 (51.1%), M 14385 (48.9%)`


## 実データで観測された代表パターン例

- N の例
  - `11034`
  - `NM1: 499; NM2: 400`（→ 改行分解して 2 レコードへ）
  - `Overall 22000 approx`（→ `Phase: Overall | N: 22000 approx`）
- RC Age の例
  - `Overall: mean 61.5; sd NR; median: NR; iqr: NR; min 4; max 91`（→ キー=値の抽出でそのまま成形）
  - `NM1; Train; 49.63 (weighted); 20.76 (pooled); 8 (stated); 82 (stated)`（→ `weighted/pooled/exact` タグ付け）
- Sex の例
  - `F 13201 (44.18%), M 16682 (55.82%)`
  - `Baseline: M 126 (77%); F 38 (23%)`（→ `Model/Phase: Baseline` として見出しに昇格）
  - `F 14000 (54.0%), M 11917 (46.0%), NR 0 (0.0%)`


## 実装ガイド（正規化アルゴリズム草案）

共通プリプロセス:
- 全角英数・括弧の半角化、スマートクオートの ASCII 化。
- 余分な括弧はタグに変換（例: `(weighted)` → `weighted`）。
- カンマ入り数値からカンマ除去。

N 列:
- 行分割: `\n` または `;` をレコード境界として使用（ただし Age の `;` とは衝突しないよう rci2 のみで適用）。
- `NM\d+`、`functional/structural`、`Overall/Whole/Train`、`approx/exact/stated`、整数 N を抽出。
- 正規化文字列へ再構成。

RC Age 列:
- 行分割: `\n`。
- トークン化: `;` 区切りで `[model_tag, phase, n?, mean?, sd?, median?, iqr?, min?, max?]` を抽出。
- キー語彙: `mean|sd|median|iqr|min|max`、修飾語: `weighted|pooled|approx|inferred|stated|exact`。
- 欠損は `NR` を補完。`stated` は `exact` に置換。
- 正規化文字列へ再構成（N は N 列へ移すのが望ましい）。

Sex 列:
- 行分割: `\n` または `;` の前置き見出し（`Baseline:`, `Follow-up:`, `Model:` 等）は `Model:`/`Phase:` タグに格上げ。
- `F/M/NR` の人数・百分率を抽出し、半角括弧に統一し順序 `F, M, NR` で再構成。


## 影響範囲と UI 表示上の注意
- SummaryView のセルは文字列ベースのため、複数レコードは改行区切りが視認性・実装容易性のバランスが良い（CSS で `white-space: pre-wrap` を検討）。
- 将来的に厳密化する場合は JSON 側を構造化（配列化）し、View 側でテンプレート整形する方が保守的。
- 本正規化は非破壊的（情報を極力保全）で、曖昧な注記はタグ化（`approx/exact/weighted/pooled/inferred`）して保持します。

