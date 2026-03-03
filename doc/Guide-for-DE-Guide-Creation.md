# DE作業の工夫について

### 自由記述形式のDE→カテゴリ形式のDE

* １つ１つ作業しながら、共通点を見出し、条件、場合、段階、局面分けを理解し、抽出形式を定めてゆく。
* ５篇ほどを目標に抽出を繰り返し、templateとguideを定めてゆく。
    * 単純な構造の論文、複雑な構造の論文ともに
* 正規化のためのルール検討という作業も必要
    * 自由記載で情報を集めた上で、それを元に正規化キーワード、共通フォーマットを定めるのが現実的な手段であろう
    * つまり複数回の抽出と確認の繰り返しが必要。
    * data extraction作業を試みている中で、このDE作業というものは
      QA作業とは本質的に異なり、どういうデータを論文紙面で提示したいかというところを意識して、データを正規化してゆく作業であり、
      一次調査をし、一次調査結果を元に調査観点を見直し、二次調査。二次調査結果を元に調査観点を見直し三次調査…といったことになる。
* Keywords-for-XXXXX.md というものを作る

### AIとの協業を利用したプロンプト、スクリプト、比較ツール生成

* AIとの協業
    * AIと議論しながらDE_Guideを作るための計画書を作る。
        * あなたが〇〇を実現するにあたり必要な情報を補完してください。
        * あなたが作業をするにあたり不足する情報があれば尋ねてください。
    * DE_Guideを作らせる。修正させる。
    * DE実行に必要なスクリプトとテンプレートを用意させる。
    * 一覧するための道具を作らせる。
* DE_vXX 間で道具を使い回せるように、出力JSONの形式を統一させられると良い。

### 論文内に複数存在する要素を抽出する場合

* 論文内に一つ存在するであろう要素の調査と、論文内に複数存在しうる要素の調査の違いを意識したほうが良い。
    * 統一的に比較するためにも、統一的にまとめ上げるためにも必要な観点となる。
* 実例は DE_v14
    * DE_v10, 11, 12 は基本的に論文内に単一で存在する要素を抽出している。
    * DE_v13もそのように作ればよかったと後で反省。
        * もしDE_v13もDE_v14のように情報を抽出し比較するならば、モデル名を正規化する必要がある。
        * NM-for-CT, NM-for-GMV など、統一的なカテゴリを作ってから情報抽出に挑まねばならない。
* 何個含まれているかわからないものを抽出する戦略を明確にせねば
* 抽出要素単位を決める。
    * 何Wise、何basedでデータを出力させるか。
    * 推定モデル毎なのか、疾患毎なのかなど。
* Agent毎に検出できる要素数が異なることも想定せねばならない。
* いくつ見つけ出せるかAgent毎に異なりうる場合、その要素数が未確定な場合は配列（`["ADCSL", "ADCSL"]`）で表現
    * 単一なら文字列`"ADCSL"`で抽出すればよいが、いくつ見つかるか、定まらないものは配列[]で。
* answerとして受け入れるものは正規化された表現（正規化キーワードやフォーマットされた数値）であるべき。
    * 後工程で同一形式で比較できるように
* 期待するJSON構造を明確にすると良い。
    * 後工程でAgent抽出結果を比較する方法も定めながらJSON構造を定めてゆく
    * JSON構造定義はなかなかに難しいが。
* ADCSL Style, A Style など

**DE-Result-by-Agent-X.json**

```json
{
  "paper_id": {
    "answer": "Example2024"
  },
  "disorders": [
    {
      "disorder-name": {
        "answer": "SCZ",
        "detail": "DSM-IV schizophrenia; clinical cohort",
        "confidence_rating": "High",
        "supporting_text": "\"All patients met DSM-IV criteria for schizophrenia.\"",
        "location": "Example2024.pdf.md: Methods / Participants, L40-L44"
      },
      "dataset-of-origin": {
        "answer": [
          "COBRE",
          "MCIC"
        ],
        "detail": "Two public clinical datasets",
        "confidence_rating": "High",
        "supporting_text": "\"Participants were drawn from COBRE and MCIC.\"",
        "location": "Example2024.pdf.md: Methods / Datasets, L55-L58"
      },
      "age": {
        "answer": {
          "unit": "years",
          "min": 18,
          "max": 62,
          "mean": 35.40,
          "sd": 9.85
        },
        "detail": "Overall clinical cohort",
        "confidence_rating": "High",
        "supporting_text": "\"Mean age was 35.4 years (SD 9.85), range 18–62.\"",
        "location": "Example2024.pdf.md: Results / Demographics, L70-L72"
      },
      "sex": {
        "answer": {
          "male_n": 70,
          "male_pct": 58.3,
          "female_n": 50,
          "female_pct": 41.7
        },
        "detail": "Percentages calculated from counts",
        "confidence_rating": "Medium",
        "supporting_text": "\"70 males and 50 females.\"",
        "location": "Example2024.pdf.md: Results / Demographics, L73-L74"
      }
    }
  ]
}
```

## その他 効果的な工夫

* 最も構造的に複雑な論文を対象に抽出戦略を練る
    * 上位3個とか５個とか見つけておくと良い。
* template 側に加え、guide 側で構造を指示。
    * template 側で大枠を定め、 guide側の例で詳細を指示。
    * 大枠、詳細ともにjsonで 並列な複数情報が抽出されうるときに、場合分けをしながら抽出が可能。

## その他参考情報

* 最終的には DE-Protocol文書（どういう基準で情報を抽出したか）も必要。
* データ抽出の形式が揃うことを期待して複数スタディをまとめて一度にDE作業を行わせることも試したことがあるが、
  期待したほどには統一感は得られず。十分な効果検証は必要だが、一旦このアプローチは採用せず。
