# Disease列の表現バラエティ調査

本メモは、SummaryView.java が参照している Human 系 JSON
の「Disease」列（CAA2）に記入されている値の表現バリエーションを俯瞰し、今後の正規化・入力指針の検討材料をまとめたものです。

## 相談事項

SummaryView.javaが参照しているJSONファイルのDisease列に対応する要素の値について
表現のバラエティを調査してしてください。調査結果は ./spec/pattern_of_disease.md にお願いします。

SummaryView.javaが参照しているJSONファイル自体に修正を施したいです。
Disease列に対応する要素の値を正規化して欲しいです。
pattern_of_disease.md を参考に書き換えてください。
share_package/data/**/DE_v10/json*/DE_*human*.json や share_package/data/**/DE_v10/json*/DE_*Human*.json についてのみ修正をお願いします。名前に
human や Human のつかないJSONファイルについては一切触らないでください。

## 対象と方法

- 対象ファイル: `share_package/data/*/DE_v10/json/*human*.json`
- 読み取り方法:
    - `SummaryView` は `RowObject` を通じて JSON を読み、`clinical_application_and_analysis_part` セクションの `caaN_*`
      キーを数値順で収集。
    - 本調査では「Disease」列に対応する CAA2（`caa2_*`）の値（answer があればそれを優先）を抽出。
- スキャン件数: 123 JSON（Human 系）
- 一意表現数: 95

## 概要（頻出例）

- Autism Spectrum Disorder (ASD): 合計 16 件（表記ゆれ含む、例: 大文字/小文字差, 追記「males」など）
- Major Depressive Disorder (MDD): 5 件（「first-episode」「drug-naive」等の注記つきが一部）
- Schizophrenia（SCZ/SZ 含む）: 3 件（単独表記）+ コンボ表記多数
- Alzheimer’s disease / Mild cognitive impairment (AD/MCI): 複合表記 3 件、他にも AD のみ、MCI とセット、拡張（EMCI/LMCI）などあり
- Parkinson’s disease (PD): 2 件（DLB/LBD と組み合わせのケースもあり）
- OCD/BD/ADHD 等: それぞれ単独もしくは複数疾患の並列表記として出現

## 代表的な表記パターン

- 疾患名 + 略語: 例「Autism Spectrum Disorder (ASD)」「Major Depressive Disorder (MDD)」「Schizophrenia (SCZ/SZ)」
- 大文字/小文字の差: 例「Autism Spectrum Disorder」vs「Autism spectrum disorder」
- 同義/略語の揺れ:
    - Schizophrenia: SCZ, SZ, SCHZ の混在
    - Bipolar: BD, BP の混在
    - DLB/LBD（Lewy body dementia）の表記ゆれ
    - Alzheimer’s: `’`（スマートクオート）と `'`（ASCII）が混在
    - MCI の細分類（EMCI/LMCI、MCI-AD など）の併記
- 複数疾患の並列表記: セミコロン区切り（`;`）、疾病カテゴリの混在（精神・神経変性・遺伝性・腫瘍・嗜癖など）
- 集団/状態注記の埋め込み: 例「males」「first-episode」「drug-naïve」「pediatric」「euthymic」「vs healthy controls」
- スペクトル/リスク段階: 例「Clinical high risk for psychosis (CHR-P)」「First-episode psychosis (FEP)」「Psychosis spectrum
  disorders (PSD)」
- 遺伝学的表現: 例「16p11.2 BP4-5 deletion/duplication」「22q11.2 Deletion Syndrome (22qDel)」「CNV carrier」
- その他の表記揺れ: ハイフン/エンダッシュ、丸括弧内の注釈、文末の句点、和文括弧やカンマ/セミコロンの混在
- 明らかに不適切/欠損: 例「Yes」（疾病名でない）、空欄 1 件

## ケース別に見た揺れの例

- ASD（自閉スペクトラム症）
    - 「Autism Spectrum Disorder (ASD)」「Autism spectrum disorder (ASD)」「Autism spectrum disorder (ASD) males」
    - 他疾患との併記多数（ADHD, BD, SZ 等）
- Schizophrenia / Psychosis
    - 「Schizophrenia」「Schizophrenia (SCZ)」「Schizophrenia (SZ)」「Schizophrenia-spectrum disorder (SCZ)」
    - 近縁概念の併記「First-episode psychosis (FEP)」「Clinical high risk for psychosis (CHR-P)」「Psychosis spectrum
      disorders (PSD)」
- MDD（うつ病）
    - 「Major Depressive Disorder (MDD)」「major depressive disorder (MDD)」
    - 状態注記の併記「first-episode」「drug-naïve」「FEDN-MDD」
- AD/MCI（アルツハイマー/軽度認知障害）
    - 「Alzheimer's disease (AD); Mild cognitive impairment (MCI)」「Alzheimer's Disease (AD); Mild Cognitive Impairment (
      MCI; EMCI; LMCI)」「MCI-AD」
    - DLB/LBD/PD とセットの並列表現も見られる

## 正規化に向けた提案

- トークナイズ/分割
    - セミコロン `;` を主分割子として複数疾患を配列化（`,` はサブ注記想定）。
    - 前後空白・末尾句読点の除去、全角空白の半角化。
- 標準名称と略語の辞書化
    - 例: ASD, ADHD, MDD, BD, SCZ（SZ を集約）, AD, MCI, PD, OCD, FEP, CHR-P, DLB, MS, TLE/mTLE, SeLECTS, IGD/TUD/CUD,
      FASD, DIPG, 22qDel, 16p11.2-CNV 等。
    - 「BP」は「BD」に統一、「LBD」は「DLB」に統一など、片方に寄せるルールを明示。
- 大文字小文字の規約
    - 疾患フルネームは Title Case、略語は全て大文字に統一。
- 集団/状態修飾の分離
    - 「males」「pediatric」「first-episode」「drug-naïve」「euthymic」「vs healthy controls」等は別カラム（Population/State）へ分離。
- 無効値の扱い
    - 「Yes」等の非疾病表現は欠損/要レビューとしてフラグ。
- 最低限の正規化手順（擬似コード）
    1) 文字種正規化（全角→半角，スマートクオート→ASCII）
    2) `;` で split → 各要素を trim
    3) 末尾の句読点・括弧閉じの整形
    4) 略語展開/統合（辞書で置換）
    5) 集団/状態語句を抽出し別フィールドへ退避
    6) 残った疾病名を Title Case 化

## ユニーク表現（件数つき、頻度順）

- 9x: Autism Spectrum Disorder (ASD)
- 7x: Autism spectrum disorder (ASD)
- 5x: Major Depressive Disorder (MDD)
- 3x: Alzheimer's disease (AD); Mild cognitive impairment (MCI)
- 3x: Schizophrenia
- 2x: Alzheimer's Disease (AD)
- 2x: Alzheimer's Disease (AD); Mild Cognitive Impairment (MCI)
- 2x: Obsessive-Compulsive Disorder (OCD)
- 2x: Parkinson's disease (PD)
- 2x: Schizophrenia (SCZ)
- 2x: Schizophrenia (SZ)
- 1x:
- 1x: 16p11.2 BP4-5 deletion; 16p11.2 BP4-5 duplication. Associated conditions include neurodevelopmental disorders such
  as Autism Spectrum Disorder (ASD) and intellectual disability.
- 1x: ADHD; Schizophrenia (SZ); Bipolar Disorder (BD); Major Depressive Disorder (MDD); Early Psychosis (EP); Mild
  Cognitive Impairment (MCI); Dementia (DM)
- 1x: Alzheimer's disease (AD) spectrum
- 1x: Alzheimer's disease (AD); Mild cognitive impairment (MCI); Parkinson's disease (PD); Lewy body dementia (LBD);
  Schizophrenia (SCZ); Major depressive disorder (MDD); Bipolar disorder (BD); Autism spectrum disorder (ASD); Anxiety
  disorders (ANX); Obsessive–compulsive disorder (OCD)
- 1x: Alzheimer's Disease (AD); Mild Cognitive Impairment (MCI; EMCI; LMCI)
- 1x: Alzheimer's disease (AD); Mild Cognitive Impairment due to AD (MCI-AD)
- 1x: Alzheimer's Disease (AD); Schizophrenia (SZ)
- 1x: Alzheimer's disease; Mild cognitive impairment
- 1x: Alzheimer’s disease; Schizophrenia (including subgroups by stage and hallucination status)
- 1x: ASD with DD/ID; ASD without DD/ID; DD/ID only
- 1x: Attention Deficit Hyperactivity Disorder (ADHD); Autism Spectrum Disorder (ASD); Bipolar Disorder (BD); Early
  Psychosis (EP); Major Depressive Disorder (MDD); Schizophrenia (SZ)
- 1x: Attention-Deficit/Hyperactivity Disorder (ADHD)
- 1x: Attention-deficit/hyperactivity disorder (ADHD); Autism spectrum disorder (ASD); Anxiety disorder (ANX); Learning
  disorder (LD)
- 1x: Attention-Deficit/Hyperactivity Disorder (ADHD); Autism Spectrum Disorder (ASD); Bipolar Disorder (BP); Major
  Depressive Disorder (MDD); Obsessive-Compulsive Disorder (OCD); Schizophrenia (SCZ)
- 1x: Attention-deficit/hyperactivity disorder (ADHD); Autism spectrum disorder (ASD); Bipolar disorder (BP); Major
  depressive disorder (MDD); Obsessive-compulsive disorder (OCD); Schizophrenia (SCZ)
- 1x: Autism spectrum disorder (ASD) males
- 1x: Autism Spectrum Disorder (ASD); Attention Deficit Hyperactivity Disorder (ADHD)
- 1x: Autism Spectrum Disorder (ASD); Attention-Deficit/Hyperactivity Disorder (ADHD); Co-occurring autism+ADHD (subset)
- 1x: Autism Spectrum Disorder (ASD); Attention-Deficit/Hyperactivity Disorder (ADHD); Mood disorders; Anxiety
  disorders; Neurodivergence (co-occurring possible)
- 1x: Autism Spectrum Disorder (ASD); Bipolar Disorder (BD); Schizophrenia (SZ); Mild Cognitive Impairment (MCI);
  Alzheimer's Disease (AD)
- 1x: Autism spectrum disorder (ASD); Mild cognitive impairment (MCI); Alzheimer disease (AD); Bipolar disorder (BD);
  Schizophrenia (SZ)
- 1x: Autism Spectrum Disorder (ASD); Schizophrenia (SZ)
- 1x: Bipolar Disorder (BD)
- 1x: Bipolar disorder (BD); Parkinson's disease (PD)
- 1x: Bipolar disorder (euthymic)
- 1x: Cannabis Use Disorder (CUD)
- 1x: Clinical high risk for psychosis (CHR-P); conversion to psychotic disorder
- 1x: Clinical high risk for psychosis (CHR-P); Early illness schizophrenia (ESZ); Non-affective psychosis (NAff-P);
  Affective psychosis (Aff-P)
- 1x: Clinical high risk for psychosis (CHR-P); transition to first episode psychosis
- 1x: Depression; Attention-deficit/hyperactivity disorder (ADHD)
- 1x: Disruptive Behavior Disorders (DBD: ODD and/or CD)
- 1x: Down syndrome (Trisomy 21)
- 1x: Early Psychosis (EP)
- 1x: Early psychosis (psychosis)
- 1x: Fetal Alcohol Spectrum Disorder (FASD)
- 1x: First-episode psychosis (FEP)
- 1x: First-Episode Psychosis (FEP)
- 1x: First-episode psychosis (schizophrenia spectrum and affective psychoses)
- 1x: Frontal lobe glioma
- 1x: Internet Gaming Disorder (IGD); Tobacco Use Disorder (TUD)
- 1x: Major depressive disorder (first-episode and recurrent)
- 1x: Major Depressive Disorder (first-episode drug-naive; FEDN-MDD)
- 1x: Major Depressive Disorder (first-episode MDD)
- 1x: Major depressive disorder (MDD)
- 1x: Major depressive disorder (MDD) — first-episode drug-naïve (FEDN-MDD)
- 1x: Major depressive disorder (MDD); Bipolar disorder (BD)
- 1x: Major depressive disorder (MDD); Bipolar disorder (BD); Schizophrenia (SZ)
- 1x: Major Depressive Disorder (MDD); Schizophrenia (SCZ)
- 1x: Mesial Temporal Lobe Epilepsy (mTLE); Bipolar Disorder (BD)
- 1x: Mild Cognitive Impairment (MCI); Alzheimer's Disease (AD); Frontotemporal Dementia (FTD)
- 1x: Mild Cognitive Impairment (MCI); Dementia (Alzheimer's disease); 22q11.2 Deletion Syndrome (22qDel)
- 1x: Mild Cognitive Impairment (MCI); Dementia; Alzheimer's Disease (AD)
- 1x: Mild Traumatic Brain Injury (mTBI)
- 1x: Multiple Sclerosis
- 1x: Multiple Sclerosis (MS) (pediatric)
- 1x: Obsessive-compulsive disorder (OCD)
- 1x: Parkinson's disease (PD); Dementia with Lewy bodies (DLB)
- 1x: Parkinson's disease; Alzheimer's disease
- 1x: Pathogenic copy number variant (CNV) carrier status (e.g., 1q21.1 deletion; 1q21.1 duplication; others)
- 1x: Pediatric brainstem tumors (including focal and diffuse intrinsic pontine glioma [DIPG])
- 1x: Pediatric brainstem tumors; Diffuse Intrinsic Pontine Glioma (DIPG)
- 1x: Psychosis spectrum disorders (PSD); First-episode psychosis (FEP); Other psychopathology (non-psychotic)
- 1x: Refractory epilepsy (pediatric).
- 1x: Schizophrenia (SCHZ); Bipolar Disorder (BPD); Attention-Deficit/Hyperactivity Disorder (ADHD)
- 1x: Schizophrenia (SCZ); Autism spectrum disorder (ASD)
- 1x: Schizophrenia (SCZ); Bipolar Disorder (BD); Attention-Deficit/Hyperactivity Disorder (ADHD)
- 1x: Schizophrenia (SCZ); Bipolar Disorder (BD); Attention-Deficit/Hyperactivity Disorder (ADHD); Major Depressive
  Disorder (MDD)
- 1x: Schizophrenia (SCZ); First-Episode Psychosis (FEP)
- 1x: Schizophrenia (SCZ); Schizoaffective disorder
- 1x: Schizophrenia (SCZ); Schizoaffective Disorder (SAD); First Episode Psychosis (FEP); Psychotic Experiences (PE;
  suspected/definite/clinical); First-degree relatives of SCZ/SAD
- 1x: Schizophrenia (SZ); Bipolar Disorder (BD); Major Depressive Disorder (MDD)
- 1x: Schizophrenia (SZ); Bipolar Disorder (BP)
- 1x: Schizophrenia (vs healthy controls)
- 1x: Schizophrenia spectrum
- 1x: Schizophrenia spectrum disorder (SSD); Violent offenders without schizophrenia (behavioral phenotype)
- 1x: Schizophrenia spectrum first-episode psychosis (SCZ)
- 1x: Schizophrenia-spectrum disorder (SCZ)
- 1x: Schizophrenia; Autism spectrum disorder (ASD); Attention-deficit/hyperactivity disorder (ADHD)
- 1x: Schizophrenia; Bipolar Disorder
- 1x: Schizophrenia; Brief psychotic disorder (acute and transient psychotic disorders)
- 1x: Self-limited epilepsy with centrotemporal spikes (SeLECTS)
- 1x: Temporal Lobe Epilepsy (TLE)
- 1x: Yes

---
備考: 「settings」の JSON 由来で空欄が 1 件含まれます。表中の値には、文末の句点や補足文章が含まれるケースがあります（上記「正規化に向けた提案」を参照）。

