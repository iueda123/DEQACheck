# AIを使ったQuality Assessment (QA) 戦略

## 前提・方針

- **PROSPERO文書の作成**（PICO等を明確化）
- **Assessmentツールの選定** 
- **段階的改善**: プロンプトを洗練（目的・例・判定区分・条件分岐などを的確に提示）

> **重要**: Data Extractionとは異なり、最初に作った評価基準を基本的に変更せず評価する。

---

## フロー

```
Quality Assessment Flow

1)  先行研究やToolを参考に評価基準を作る
            |
            v
2)  数編で評価を実施  （Codex / Claude / Gemini / Human）
            |
            v
3)  評価結果を吟味し評価基準を見直す
            |
            v
4)  5編で評価を実施   （Codex / Claude / Gemini / Human）
            |
            v
5)  評価結果を吟味し評価基準を見直す
            |
            v
6)  10編で評価を実施  （Codex / Claude / Gemini / Human）
            |
            v
7)  評価結果を吟味し評価基準を見直す
            |
            v
8)  25%編で評価を実施 （Codex / Claude / Gemini / Human）
            |
            v
9)  評価結果を吟味し評価基準を見直す
            |
            v
10) 全編で評価を実施  （Codex / Claude / Gemini / Human）
            |
            v
11) 最終評価結果
```

---

## 1. 全体像を設計する

- **AIは一次評価、最終判断は人**という役割分担を明確にする。
- **目的に合わせた粒度**を先に決める。Table化・ICC算出・再利用など用途を明確にする。
- **対象の異質性**（研究デザイン・データ源・新規/既存モデルなど）を最初から織り込む。

## 2. ルールを固定し、迷いどころを先に潰す

- **Yes/Partial/No/NA の判定基準**を明文化し、判断の揺れを抑える。
- **理由文の語彙を統一**し、missing / unclear / incomplete / not applicable などの語を必須化する。
- **Location の書き方を固定**して、第三者が辿れる形にする（文書構造や段落単位など）。

## 3. AI入力の品質を担保する

- **AIには本文と Supplement を両方渡す**。片方だけだと No が増える。
- **資料の不足はAIの失敗ではなく入力の失敗**と捉え、データ配備の基準を決める。
- **テンプレート固定**で出力を機械処理可能にし、後工程の負荷を下げる。

## 4. 評価の品質は "Reason" で決まる

- **Reason と Supporting Text の整合性**が最大の監査ポイント。
- **引用文献のみ記載の場合の扱い**は明示し、Yes/Partial の境界を共有する。
- **Partial/No/NA の理由が説明できるか**を品質の判定軸にする。

## 5. 検証ステップを組み込む

- **少数文献でAIと人間の二重評価**を行い、ズレの傾向を把握する。
- **ズレが出やすい項目**を重点レビュー対象として明示する。
- **基準擦り合わせの短い場**を設け、ルール解釈の分散を抑える。

## 6. 反復可能な運用にする

- **作業手順は短いチェックリスト化**し、誰がやっても同じ品質になるようにする。
- **AIプロンプト・テンプレ・例示**は最小限の差分で再利用可能にする。
- **レビューごとの固有事情**（特殊な研究領域など）は追加ルールとして上書きする。

## 7. 成果物の再利用性を意識する

- **機械処理前提の出力**にしておくと、集計・可視化・追跡が容易になる。
- **後で再評価できる形**（Location と Supporting Text）を必須にする。
- **説明可能性を優先**して、AIの判断をブラックボックス化しない。

---

## ACRSL Style

  * **ACRSL_Style** = Answer, Confidence rating, Reason, Supporting text, and Location style
    * Provide structured detailed extraction including:
        * Answer: The extracted information
        * Confidence rating: High, Medium, or Low
        * Reason: Step-by-step explanation of how you arrived at the answer
        * Supporting text: Direct quotes from source materials
        * Location: Document location of the supporting text
    * Used for complex items requiring evidence and justification


-----------

## PROSPERO文書とは

**PROSPERO**（International Prospective Register of Systematic Reviews）は、システマティックレビューを事前登録するための国際データベース（York大学運営）。
レビュー開始前に計画を登録することで、**後付け変更・出版バイアスの防止**と**透明性の確保**を図る。

### 主な登録項目

| 項目 | 内容 |
|------|------|
| **PICO(S)** | Population・Intervention/Exposure・Comparison・Outcome・Study design |
| **レビュー目的** | 具体的なリサーチクエスチョン |
| **適格基準** | 包含・除外基準（研究デザイン、対象、期間など） |
| **検索戦略** | 使用データベース、検索式、検索日 |
| **スクリーニング手順** | タイトル/抄録 → 全文の2段階スクリーニング |
| **Quality Assessment方法** | 使用するQAツール、評価者数、不一致時の解決方法 |
| **データ抽出方法** | 抽出する変数、使用するフォームや手順 |
| **解析計画** | 結果の統合方法（定性的統合／メタアナリシスなど） |

### 本レビューにおけるPICOS

| 要素 | 内容 |
|------|------|
| **P** (Population) | 精神・神経疾患の患者または健常対照を含む研究 |
| **I** (Exposure) | Normative modelingを用いたneuroimaging研究 |
| **C** (Comparison) | 比較対象は問わない（記述的なものも含む） |
| **O** (Outcome) | モダリティ・方法論・臨床的知見の記述 |
| **S** (Study design) | 原著論文（観察研究・RCTを問わない） |

### QAとの関係

- PROSPERO登録時に**QAツールの種類と評価者構成**を明記する
- 登録後にQA基準を大幅変更する場合は**Amendment（修正）**として記録が必要
- 評価者間一致度（例：ICC・Kappa）の算出方法も登録段階で決めておくことが望ましい

-----------


## Assessment Tool 例

  * [NIH Quality Assessment Tool for Observational Cohort and Cross-Sectional Studies 14項目](https://www.nhlbi.nih.gov/health-topics/study-quality-assessment-tools)
  * [CLAIM 2024 Update 44項目](https://pubs.rsna.org/doi/full/10.1148/ryai.240300)

-----------


## プロンプト例

[QA_Guide_v8.md](../../share_package/prompts/QA_Guide_v8.md)
[QA_Guide_v9.md](../../share_package/prompts/QA_Guide_v9.md)

-----------


## 参考資料

- [試行錯誤の記録](../old/2025.11.14_QAに関する状況整理と提案.md)
