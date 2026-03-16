# DE作業の戦略


## Data Extraction Flow

初回は広く、２回目以降は的を絞る。
１回目の情報を元に正規化キーワードや正規化形式を定める。

```
1) Extractionの観点を考える
2) Extraction Guide を作る
3) 1st Extraction using AI Agents
     目的: Normalization Keywords / Formats を定めるための情報収集
     方針: 広く捉える、自由記述を集める
     実施者: Codex / Claude / Copilot / Human
         |
         v
4) Extraction Guide を修正
     修正内容:
       - 抽出情報の前提を揃える（例：何というPhaseでの数値・手法なのか）
       - 言葉の範囲が重複しないよう、answer用整理キーワードを定義する
       - はみ出す場合の扱いを考えておく
       - 数値系は抽出フォーマットを定める
         |
         v
5) 2nd Extraction
     目的: Normalized Keywords / Formats に従って抽出
     実施者: Codex / Claude / Copilot / Human
         |
         v
6) Extraction Guide を修正
     - AI Agents間の不一致点は、とくに注意して情報抽出すべきとのアラート
     - AI Agentsと人間の不一致点は、そもそも抽出基準が的確でない可能性
         |
         v
  ↑ 抽出と修正を繰り返しながら、Humanによる結果に整えてゆく ↑
         |
         v
7) Final Answer
     実施者: Codex / Human
```


---


## 整理のための視点を定める

DE作業は、共通性が曖昧な情報群から視点を定めて共通点を整理してゆく作業とも言える。

視点の定め方の例:
- 各文献の質を評価する上で重要な観点
- 実用的な観点


## 共通点に言葉（キーワード）を当てはめてゆく作業（正規化作業）

初期段階から具体的なキーワードで拾い上げようとすると失敗する。

具体的キーワードの位置づけは、比較対象があって初めて定まってくるものであるため、初期段階においては往々にしてキーワードの守備範囲を誤解している。

またキーワードの守備範囲は情報に出会えば出会うほど変質するもの。
当初は比較的上位の概念だと思っていたものが、文献を読み進めてゆくうちにより上位の概念が出現することもある。

> **例**: 犬をみて「動物が１匹いるな」と思うときの "動物" の守備範囲は、そこに猫が現れて「動物が増えた」と思ったときの "動物" よりも狭い。

> **例**: 統計解析手法。当初は "群間比較" というキーワードで整理していても、後々「２群間比較」「３群間比較」等々が出てくれば、そのキーワードの守備範囲は変質してゆく。




### 自由記述形式のDE → カテゴリ形式のDE

そこで、１回目はまず自由記述による情報抽出を行って収集した文献群の言葉の全体像を把握し、そこからAIアシストを使いながら整理のためのキーワードを定義してゆく。


- まずは１つ１つ人力で文献を読みながら、共通点を見出し、条件・場合・段階・局面分けを理解し、抽出形式を定めてゆく
- ５篇ほどを目標に抽出を行い、templateとguideを定めてゆく（単純な構造の論文・複雑な構造の論文ともに）
- 
- 正規化のための抽出ルール再検討という作業も必要
    - 自由記載で情報を集めた上で、それを元に正規化キーワード・共通フォーマットを定めるのが現実的
    - つまり複数回の抽出と確認の繰り返しが必要
    - DE作業はQA作業とは本質的に異なり、どういうデータを論文紙面で提示したいかを意識しながらデータを正規化してゆく作業。
      一次調査 → 観点見直し → 二次調査 → 観点見直し → 三次調査…と繰り返す
- `Keywords-for-XXXXX.md` というものを作る


### ADCSL Style

1. **Answer**: 抽出情報（カテゴリ回答または短いテキスト）
2. **Detail**: 項目で求める構造化された詳細
3. **Confidence Rating**: High / Medium / Low
4. **Supporting Text**: 直接引用（簡潔に。言い換え禁止）
5. **Location**: 引用の所在（"FileName: Section / Location"）

### AIとの協業を利用したプロンプト・スクリプト・比較ツール生成

- AIと議論しながらDE_Guideを作るための計画書を作る
    - 「あなたが〇〇を実現するにあたり必要な情報を補完してください」
    - 「あなたが作業をするにあたり不足する情報があれば尋ねてください」
- DE_Guideを作らせる・修正させる
- DE実行に必要なスクリプトとテンプレートを用意させる
- 一覧するための道具を作らせる
- `DE_vXX` 間で道具を使い回せるように、出力JSONの形式を統一させられると良い


### 論文内に複数存在する要素を抽出する場合

- 論文内に一つ存在するであろう要素の調査と、論文内に複数存在しうる要素の調査の違いを意識する
    - 統一的に比較・まとめ上げるために必要な観点
- 実例は DE_v14
    - DE_v10〜12 は基本的に論文内に単一で存在する要素を抽出
    - DE_v13 もそのように作ればよかったと後で反省
        - DE_v13 を DE_v14 のように比較するならば、モデル名の正規化が必要
        - `NM-for-CT`、`NM-for-GMV` など、統一的なカテゴリを作ってから抽出に挑む必要がある
- 何個含まれているかわからないものを抽出する戦略を明確にする
- 抽出要素単位を決める
    - 何wise・何basedでデータを出力させるか（推定モデル毎なのか、疾患毎なのかなど）
- Agent毎に検出できる要素数が異なることも想定する
- 要素数が未確定な場合は配列（`["ADCSL", "ADCSL"]`）で表現
  （単一なら文字列 `"ADCSL"` で抽出すれば良いが、いくつ見つかるか定まらないものは配列 `[]` で）
- answerとして受け入れるものは正規化された表現（正規化キーワードやフォーマットされた数値）であるべき
    - 後工程で同一形式で比較できるように
- 期待するJSON構造を明確にする
    - 後工程でAgent抽出結果を比較する方法も定めながらJSON構造を定めてゆく


**DE-Result-by-Agent-X.json の例:**

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


---

## プロンプト例


[DE_Guide_v10_1.md](../../share_package/prompts/DE_Guide_v10_1.md)
[DE_Guide_v11.md](../../share_package/prompts/DE_Guide_v11.md)
[DE_Guide_v11_multi-study.md](../../share_package/prompts/DE_Guide_v11_multi-study.md)
[DE_Guide_v12.md](../../share_package/prompts/DE_Guide_v12.md)
[DE_Guide_v13.md](../../share_package/prompts/DE_Guide_v13.md)
[DE_Guide_v14.md](../../share_package/prompts/DE_Guide_v14.md)


## その他 効果的な工夫

- 最も構造的に複雑な論文を対象に抽出戦略を練る（上位3〜5個を先に見つけておくと良い）
- template 側で大枠を定め、guide 側の例で詳細を指示
    - 大枠・詳細ともにJSONで、並列な複数情報が抽出されうるときに場合分けしながら抽出が可能
- 依頼戦略
    - 予め決めた3〜4種のエージェントに網羅的に依頼
    - 2 Agents で実施し、不一致文献については3番目のエージェントに依頼


## その他参考情報

- 最終的には DE-Protocol文書（どういう基準で情報を抽出したか）も必要
- データ抽出の形式が揃うことを期待して複数スタディをまとめて一度にDE作業を行わせることも試したが、期待したほどの統一感は得られず。十分な効果検証は必要だが、一旦このアプローチは採用しない
