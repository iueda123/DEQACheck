# Reference Cohort Info 抽出プロンプト

-------------------

## プロンプト概要

あなたは神経画像のノルマティブモデリング研究からデータを抽出する熟練したレビューアです。本プロンプトでは、指定された1つの文献について、NM構築時に参照しているリファレンスコホート（健常者コホート）のデータセット特性および人口統計情報を抽出してください。

-------------------

## 抽出対象

- **Model Build = New の研究のみ**が本項目の抽出対象。
- Model Build = Pre-trained の研究は本項目の抽出対象外（リファレンスコホートの構築が当該論文内で行われていないため）。
- 指定された文献が Pre-trained に該当する場合は、その旨を回答し、RCI-1〜RCI-7 の抽出は不要。

-------------------

## 一般的な抽出ルール

- ソース優先順位: 本文・表 > 補足資料 > 明示的に引用された外部ソース（外部ソース参照時は Location に出典を記載）
- 複数値はセミコロンで区切る
- 情報が見つからない場合: `NR`（Not Reported）を使用
- 複数モデル構築の場合: 配列/オブジェクトでモデル別に記録
- 導出値（百分率、加重平均、プールドSD等）を計算してよい。百分率は小数1桁、平均/SDは小数2桁を基本（論文が別精度ならそれに合わせる）

-------------------

## 抽出結果のスタイル

本プロンプトでは以下の2スタイルを使用する。

### ADCSL_Style

1. **Answer**: 抽出情報（カテゴリ回答または短いテキスト）
2. **Detail**: 項目で求める構造化された詳細
3. **Confidence Rating**: High / Medium / Low
    - **High**: 本文中に明確かつ直接的な記述がある場合
    - **Medium**: 間接的・限定的なエビデンス、推論や計算を要する場合
    - **Low**: 曖昧または不十分な記述、矛盾する情報、仮定に依存する場合
4. **Supporting Text**: 直接引用（簡潔に。言い換え禁止）
5. **Location**: 引用の所在（"FileName: Section / Location"）

### A_Style

1. **Answer**: 抽出情報のみ（confidence rating, supporting text, location は不要）

-------------------

## 抽出依頼内容

### RCI-1. Using MSAD or Not

* 定義: NM構築のリファレンスデータが、複数施設から集積されたデータセット（Multi-Site Aggregated Dataset; MSAD）であるか否か。
* 回答: `Yes` / `No` / `NR`
* 判定基準:
    * **Yes**: 明示的にマルチサイトと記載されている、または複数施設名やサイト数の記載がある場合。
               2施設以上、2スキャナ以上、同一施設で複数スキャナのいずれかを満たせば multi site 由来と判断する
               1つのデータセットが複数施設から集積されたもの（例: ENIGMA）も Yes とする。
    * **No**: 単一施設のデータ、単一撮像装置と明記されている場合。
    * **NR**: 施設数や装置数に関する情報が見当たらない場合。
* 抽出スタイル: ADCSL_Style
* "answer" 例:

```json
{
  "rci1_using_msad": {
    "answer": "Yes",
    "detail": "Multi-site; 82 sites from 40+ studies",
    "confidence_rating": "High",
    "supporting_text": "\"We used data from 82 scanning sites across 40+ studies...\"",
    "location": "Rutherford2022.pdf.md: Methods / Data, L45-L50"
  }
}
```

### RCI-2. Using EPD or Not

* 定義: NM構築のリファレンスデータに、確立された公開データセット（Established Public Dataset; EPD）が含まれているか否か。
* 回答: `Yes` / `No` / `NR`
* EPDの定義基準（2026.02.19 ミーティングにて合意）:
    * 本レビュー対象論文内で **5件以上の研究** で使用されていること
    * データセットへの **公的アクセス**（公式URL、データペーパー、申請手続き等）が確認済みであること
    * 上記いずれも満たすものを EPD とし、それ以外を In-house dataset とする
* 判定基準:
    * **Yes**: 下記 RCI-3 の EPD リストに含まれるデータセットが、NM構築のリファレンスデータに使用されている場合。
    * **No**: リファレンスデータが全て in-house dataset（独自収集・非公開データ）のみで構成されている場合。
    * **NR**: データセットの種類に関する情報が見当たらない場合。
* 抽出スタイル: ADCSL_Style
* "answer" 例:

```json
{
  "rci2_using_epd": {
    "answer": "Yes",
    "detail": "HCP-YA and UK Biobank used for normative training",
    "confidence_rating": "High",
    "supporting_text": "\"Healthy control data were drawn from the Human Connectome Project and UK Biobank...\"",
    "location": "Example2024.pdf.md: Methods / Participants, L60-L65"
  }
}
```

### RCI-3. EPD Name

* 抽出基準: RCI-2 = Yes の場合、NMの Training phase（またはリファレンスコホート構築）で使用された EPD の名前を記録する。
* 以下の確定済み18 EPD リストから選択する（括弧内の数字はレビュー対象論文内での使用論文数）:

| EPD Name | 使用論文数 |
|----------|----------|
| HCP | 28 |
| ABIDE | 24 |
| Cam-CAN | 21 |
| UK Biobank | 20 |
| OASIS | 19 |
| ADNI | 15 |
| TOP | 14 |
| PNC | 12 |
| ABCD | 12 |
| IXI | 10 |
| NKI | 10 |
| HBN | 8 |
| COBRE | 8 |
| AIBL | 7 |
| ENIGMA | 7 |
| LEAP | 7 |
| REST-meta-MDD | 6 |
| FCON | 5 |

* 回答: EPD名をセミコロン区切り / `NA`（RCI-2 = No の場合） / `NR`（情報なし）
* 抽出スタイル: ADCSL_Style
* "answer" 例:

```json
{
  "rci3_epd_name": {
    "answer": "HCP; UK Biobank; Cam-CAN",
    "detail": "HCP-YA subset used; UK Biobank imaging cohort; Cam-CAN full sample",
    "confidence_rating": "High",
    "supporting_text": "\"Data were obtained from the Human Connectome Project (Young Adult), UK Biobank, and Cambridge Centre for Ageing and Neuroscience...\"",
    "location": "Example2024.pdf.md: Methods / Datasets, L70-L80"
  }
}
```

### RCI-4. Phase

* 定義: 以降の RCI-5（N）、RCI-6（Sex）、RCI-7（Age）の情報が、どの Phase に対応するかを記録する。
* 優先順位: **Training phase** > **Overall phase** > 文献内で明示されている他の Phase
    * Training phase の情報があればそれを優先的に記録する。
    * Training phase の情報がなく Overall（train + validation + test を含む全体）の情報しかなければ Overall とする。
    * いずれもなく、Test や Validation 等の他の Phase の情報のみ見つかった場合はその Phase を記録する。
* 回答: `Training` / `Overall` / `Test` / `Validation` / `NR`
* 抽出スタイル: A_Style
* "answer" 例:

```json
{
  "rci4_phase": "Training"
}
```

### RCI-5. N

* 抽出基準: リファレンスコホート（健常者）のN数を記録する。RCI-4 で記録した Phase に対応するN数を抽出する。
* 複数のモデルが構築されている場合は、モデル別に記録する（DC-3 形式を参考）。
* 単一モデルの場合も `model-1` キーを用いた統一構造とする。
* 各モデルには `model-name`（そのモデルが何を対象としているかの端的な説明）を必ず付与する。
* 抽出スタイル: ADCSL_Style
* "answer" 例（単一モデル）:

```json
{
  "rci5_n": {
    "answer": {
      "model-1": {
        "model-name": "cortical thickness model",
        "n": 58836
      }
    },
    "detail": "Training phase HC; pooled from HCP, UK Biobank, Cam-CAN",
    "confidence_rating": "High",
    "supporting_text": "\"A total of 58,836 healthy participants were used for model training...\"",
    "location": "Rutherford2022.pdf.md: Methods / Sample, L55-L60"
  }
}
```

* "answer" 例（複数モデル）:

```json
{
  "rci5_n": {
    "answer": {
      "model-1": {
        "model-name": "functional model",
        "n": 21594
      },
      "model-2": {
        "model-name": "structural model",
        "n": 14473
      }
    },
    "detail": "Training phase HC for each model",
    "confidence_rating": "High",
    "supporting_text": "\"For the functional model, 21,594 healthy controls were included... The structural model used 14,473 participants...\"",
    "location": "Example2024.pdf.md: Methods / Participants, L80-L95"
  }
}
```

### RCI-6. Sex

* 抽出基準: リファレンスコホート（健常者）の Female / Male 各々のN数および比率（%）を記録する。RCI-4 で記録した Phase に対応する情報を抽出する。
* 複数モデルがある場合は、DC-5 形式を参考にモデル別構造で記録する。
* 各モデルには `model-name`（そのモデルが何を対象としているかの端的な説明）を必ず付与する。
* 抽出スタイル: ADCSL_Style
* 注意:
    * 一方の性別のみ記載されている場合、総N（RCI-5）から差し引いて他方を推定する。推定した場合は Detail に推定である旨を明記する。
    * 比率（%）は小数1桁（例: "51.5%"）。
* "answer" 例（単一モデル）:

```json
{
  "rci6_sex": {
    "answer": {
      "model-1": {
        "model-name": "cortical thickness model",
        "female_n": 30245,
        "female_pct": "51.4%",
        "male_n": 28591,
        "male_pct": "48.6%"
      }
    },
    "detail": "Training phase; female_n estimated from total N minus reported male_n",
    "confidence_rating": "Medium",
    "supporting_text": "\"Of the 58,836 training participants, 28,591 were male...\"",
    "location": "Rutherford2022.pdf.md: Methods / Sample, L58-L62"
  }
}
```

* "answer" 例（複数モデル）:

```json
{
  "rci6_sex": {
    "answer": {
      "model-1": {
        "model-name": "functional model",
        "female_n": 11085,
        "female_pct": "51.3%",
        "male_n": 10509,
        "male_pct": "48.7%"
      },
      "model-2": {
        "model-name": "structural model",
        "female_n": "NR",
        "female_pct": "NR",
        "male_n": "NR",
        "male_pct": "NR"
      }
    },
    "detail": "Training phase; model-2 sex breakdown not reported",
    "confidence_rating": "Medium",
    "supporting_text": "\"The functional model sample comprised 11,085 females and 10,509 males...\"",
    "location": "Example2024.pdf.md: Methods / Participants, Table 2"
  }
}
```

### RCI-7. Age

* 抽出基準: リファレンスコホート（健常者）の年齢統計量を記録する。RCI-4 で記録した Phase に対応する情報を抽出する。基本の統計量は mean, SD, range (min–max)。
* 複数モデルがある場合はモデル別構造で記録する（DC-4 形式を参考に簡略化）。
* 各モデルには `model-name`（そのモデルが何を対象としているかの端的な説明）を必ず付与する。
* 抽出スタイル: ADCSL_Style
* 注意:
    * 複数データセット統合時は、加重平均（weighted mean）およびプールドSD（pooled SD）の算出を試みる。算出した場合は answer 内の値に "weighted" / "pooled" と注記する。
    * 年齢の単位は原則「年（years）」。週齢（gestational weeks 等）の場合はその旨を明記する。
    * 平均 / SD は小数2桁を基本（論文が別精度ならそれに合わせる）。
* "answer" 例（単一モデル）:

```json
{
  "rci7_age": {
    "answer": {
      "model-1": {
        "model-name": "cortical thickness model",
        "mean": "39.85 weighted",
        "sd": "17.42 pooled",
        "min": "3",
        "max": "95"
      }
    },
    "detail": "Training phase; weighted mean and pooled SD computed from 3 datasets (HCP, UK Biobank, Cam-CAN); age in years",
    "confidence_rating": "Medium",
    "supporting_text": "\"Ages ranged from 3 to 95 years... HCP (mean 29.2, SD 3.6, N=1113), UK Biobank (mean 55.4, SD 7.5, N=40000), Cam-CAN (mean 54.3, SD 18.6, N=652)...\"",
    "location": "Example2024.pdf.md: Methods / Participants, Table 1"
  }
}
```

* "answer" 例（複数モデル）:

```json
{
  "rci7_age": {
    "answer": {
      "model-1": {
        "model-name": "functional model",
        "mean": "35.20",
        "sd": "12.50",
        "min": "18",
        "max": "65"
      },
      "model-2": {
        "model-name": "structural model",
        "mean": "NR",
        "sd": "NR",
        "min": "2 inferred",
        "max": "100 inferred"
      }
    },
    "detail": "Training phase; model-1 from paper Table 1; model-2 age range inferred from dataset descriptions",
    "confidence_rating": "Medium",
    "supporting_text": "\"Model 1 participants had a mean age of 35.20 (SD=12.50, range 18-65)... Model 2 included developmental to aging cohorts...\"",
    "location": "Example2024.pdf.md: Methods / Participants, L90-L110"
  }
}
```

-------------------

## 抽出時の一般的な留意事項

1. **Pre-trained model の研究は対象外**: Model Build = Pre-trained の研究は本項目の抽出対象外。指定された文献が該当する場合はその旨を回答し、RCI-1〜RCI-7 の抽出は不要。
2. **複数モデル構築の場合**: RCI-5, RCI-6, RCI-7 では配列/オブジェクトでモデル別に記録する。
3. **情報が見つからない場合**: `NR`（Not Reported）を使用する。
4. **ソース優先順位**: 本文・表 > 補足資料 > 明示的に引用された外部ソース。外部ソースを参照した場合は Location に出典を記載する。
5. **数値の精度**: 百分率は小数1桁、平均/SDは小数2桁を基本とする。論文が異なる精度を使用している場合はそれに合わせる。
6. **推定値の扱い**: 総Nからの差し引き、加重平均、プールドSDなど計算で導出した値は、Detail または answer 内に推定・算出であることを明記する。
