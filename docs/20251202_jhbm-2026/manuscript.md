[INDEX](./INDEX.md)

*docs/20251202_jhbm-2026/manuscript.md*

**━━━━━━━━━━━━━━━━━━━━━━━━**

# 精神神経画像の規範モデリング：横断分析と標準化課題

Normative Modeling in Neuropsychiatric Neuroimaging: Cross-sectional Analysis and Standardization Challenges

---

## 著者・所属

上田 一生<sup>1,7</sup>，高松 直岐<sup>2</sup>，齋藤 慶人<sup>3</sup>、高橋 優輔<sup>2</sup>，、惠谷 隆英<sup>4</sup>，、田村 俊介<sup>5</sup>，、渋川 周平<sup>1,6</sup>，、塚崎 天輝<sup>4</sup>、高橋 航来<sup>4</sup>、平野 羊嗣<sup>5</sup>、小池 進介<sup>8,1</sup>、中島 振一郎<sup>4</sup>

1. 東京大学大学院 総合文化研究科附属進化認知科学研究センター
2. 東京大学 医学部 精神医学教室
3. メルボルン大学メルボルン神経精神医学センター
4. 慶應義塾大学 医学部 精神・神経科学教室
5. 宮崎大学 医学部 臨床神経科学講座精神医学分野
6. 順天堂大学 保健医療学研究科
7. 信州大学医学部医療データサイエンス講座
8. 東京大学大学院医学系研究科 こころの発達医学分野

---

## Abstract

【背景】精神疾患の神経画像研究は診断カテゴリーに基づく群間比較に依存してきたが、脳計測値には診断横断的な異質性が大きい。Normative Modeling（NM）は健常参照集団に対する個人偏位を推定し、診断非依存の評価を可能にする。しかし方法・報告の標準化は不十分。【目的】NM文献から抽出した情報を集積し横断調査を行い、NM研究の方法論的傾向と標準化課題を明らかにする。【方法】PRISMA 2020に準拠して2005-2025年のNM文献を抽出し、研究のメタ情報（Dataset／N／RC Age／Sex／Modality／Model Origin／Disease／Findings）を収集・正規化して分析。【結果】文献122件を同定した。データセットは The data repository for the Cambridge Centre for Ageing and Neuroscience、The Human Connectome Project、Open Access Series of Imaging Studies 3 の利用が多かった。Nは中央値770（IQR 322–6871）と広く、年齢の平均値は中央値32.64歳（IQR 17.10–44.75）、女性比率中央値51.0%。モダリティはsMRIが最多（95件）、次いでfMRI（27件）、dMRI（9件）。モデル起源は新規モデル構築が多数（100件）、既存モデル利用は22件。対象疾患は統合失調症、自閉症スペクトラム障害、大うつ病、アルツハイマー病、双極性障害が中心。各研究の報告内容の傾向としては、個人の偏位パターンが空間的に不均一である点が強調されるものが多く、その局在は皮質領域を中心とした部位・ネットワーク単位で論じられる傾向があり。【考察】参照コホートの特徴（Dataset・年齢・性別構成）、分割戦略、性能指標、外部検証、サイト効果やハーモナイズ、再現性担保のための資料公開など、Reporting Minimum Setの明確化が必要。特に（1）Phase（Overall/Train等）の系統的記録、（2）年齢統計（mean/sd/median/iqr/min/max）の完全性、（3）モダリティ表記・カテゴリの統一、（4）疾患名の正規化は、横断比較の基盤整備に直結する。

---

## Background

精神疾患の神経画像研究は診断カテゴリーに基づく群間比較に依存してきたが、脳計測値の効果量は小さく（例：SCZ の海馬縮小 d ≈ −0.46）、分布は大きく重複する。疾患横断的な類似性（SCZ・BD・MDD の皮質下体積 effect-size profile の相関 r = 0.95–0.98）は、診断特異性よりも方法論的制約を反映している可能性がある。

Normative Modeling（NM）は健常参照集団に対する個人偏位を推定する手法であり、「脳の成長曲線」として直感的に理解できる。個人偏位パターンは同一診断内でも空間的に異質であり（同一領域に共存する患者 < 7%）、群平均は生物学的多様性を隠蔽している。

NM の急速な普及に伴い、方法論・報告の標準化が不十分であることが課題となっている。

---

## Methods

- PRISMA 2020 準拠、対象期間：2005–2025 年
- 抽出項目：Dataset / N / RC Age / Sex / Modality / Model Origin / Disease / Findings
- ツール：COVIDENCE (for TiAb & full-text screening), AI Agents（Claude Code, Codex CLI、Gemini CLI）、独自開発アプリケーション（evaluation for AI-extracetd data, data normalization）
- Phase（Train / Overall / Uninvestigated）を記録し、代表行を優先度順（Train > Overall > Uninvestigated）に選択

---

## Results

### 文献概要


![PRISMA flow diagram](figs/fig_prisma_flow.png)
**Fig. 1: 文献選定の流れを（PRISMA flow chart）**. 主要５大データベースから17.836件をスクリーニング、122 件の NM 文献を同定（2005–2025 年）。

使用データセットは The data repository for the Cambridge Centre for Ageing and Neuroscience（CamCAN）、The Human Connectome Project（HCP）、Open Access Series of Imaging Studies 3（OASIS-3）の利用が多かった。


![Fig.1: 研究数の四半期別推移（累積積み上げ棒グラフ）](figs/fig_trend_by_quarter.png)
**Fig. 1: Quaterly trend of NM pulications (cumulative)**. 研究数は 2019 年以降に急増し、近年は四半期あたり 10 件超のペースで増加している。

---

### 参照コホートの特性

参照コホートの規模（N）は中央値 770（IQR 322–6871）と広い分布を示した。年齢の平均値は中央値 32.64 歳（IQR 17.10–44.75；87 件、欠損 35 件）、女性比率の中央値は 51.0%（IQR 44.9–54.0%；102 件、欠損 20 件）であった。

<!--
![Fig.4: N の分布（箱ひげ図・対数スケール）](figs/fig_N_boxplot.png)

![Fig.5: 年齢平均の分布（箱ひげ図）](figs/fig_age_boxplot.png)

![Fig.6: 女性比率の分布（箱ひげ図）](figs/fig_female_boxplot.png)
-->

参照コホートの特性をまとめた統計表を以下に示す。

![Table 1: 研究サマリー統計表](figs/table1_summary.png)

---

### モダリティ・モデル起源

使用モダリティは sMRI が最多（95 件）、次いで fMRI（27 件）、Other（27 件）、dMRI（9 件）であった。複数モダリティを用いる研究は各カテゴリに重複カウントされる。モデル起源は新規モデル構築（New）が 100 件と多数を占め、既存モデルの転用（Pre-trained）は 22 件であった。

![Fig.2: モダリティ内訳（横棒グラフ）](figs/fig_modality_bar.png)

![Fig.7: Model Origin（円グラフ）](figs/fig_origin_pie.png)

---

### 対象疾患

<!--
疾患別集計（1 研究が複数疾患を対象とする場合は重複カウント）では、SCZ 37 件、ASD 32 件、AD 20 件、MDD 20 件、BD 19 件が上位を占めた。

![Fig.3: 疾患別研究件数・上位 10（横棒グラフ）](figs/fig_disease_bar.png)
-->

疾患別集計（1 研究が複数疾患、複数モダリティを対象とする場合は重複カウント）では、SCZ 48 件、ASD 43 件、AD 29 件、MDD 25 件、BD 25 件が上位を占めた。

![Fig.3b: 疾患別研究件数・モダリティ色分け積み上げ（横棒グラフ）](figs/fig_disease_modality_stacked.png)

**各疾患のモダリティ構成を色分けした内訳**

疾患略語一覧：SCZ = Schizophrenia（統合失調症）、ASD = Autism Spectrum Disorder（自閉症スペクトラム障害）、AD = Alzheimer's Disease（アルツハイマー病）、MDD = Major Depressive Disorder（大うつ病性障害）、BD = Bipolar Disorder（双極性障害）、ADHD = Attention-Deficit/Hyperactivity Disorder（注意欠如多動症）、FEP = First Episode Psychosis（初回エピソード精神病）、CHR-P = Clinical High Risk for Psychosis（精神病リスク状態）、MCI = Mild Cognitive Impairment（軽度認知障害）、PD = Parkinson's Disease（パーキンソン病）。

<!--
モダリティ × 疾患グループのクロス集計を Table 2 に示す。
![Table 2: Modality × Disease クロス集計表](figs/table_modality_disease.png)
-->

---

## Discussion

本横断集計が示す報告欠損・表記揺れは、NM 研究の横断比較を阻む実際的な障壁である。年齢統計の欠損（35 件 / 122 件）、女性比率の欠損（20 件）、モダリティ表記の非統一は、研究間比較のメタ解析的統合を困難にしている。

Reporting Minimum Set として以下の 4 点を提案する：

1. **Phase（Train / Overall）の系統的記録**：参照コホートの分割戦略を再現可能な形で報告する
2. **年齢統計の完全性**：mean / sd / median / IQR / min / max を揃えて報告する
3. **モダリティ表記・カテゴリの統一**：sMRI / fMRI / dMRI / Other の 4 カテゴリへの明示的な対応づけ
4. **疾患名の正規化**：略語と正式名称の対応を明示する

NM 研究の方法論的透明性と比較可能性を高めるため、報告標準化チェックリスト（NORMA チェックリストのようなツール）の整備が今後必要である。これらの整備が NM 研究の横断比較・再現性担保・バイオタイプ研究推進の基盤となる。

---

## Conclusion


- 精神神経画像 NM 文献 122 件の横断集計を行い、方法論的傾向を把握した
- 参照コホートの記述・モダリティ・疾患名の報告標準化（Reporting Minimum Set）の整備を提案する

---

## References

- Page MJ, et al. PRISMA 2020 statement. *BMJ* 2021.
- Marquand AF, et al. *Biol Psychiatry* 2016; *Trends Cogn Sci* 2019.
- Rutherford S, et al. *NeuroImage* 2022.
- Bethlehem RAI, et al. *Nature* 2022.
- Segal A, et al. *Nat Med* 2023.
- van Erp TGM, et al. (ENIGMA) *Mol Psychiatry* 2016.（SCZ 海馬縮小 d ≈ −0.46）

**━━━━━━━━━━━━━━━━━━━━━━━━**

*docs/20251202_jhbm-2026/manuscript.md*

[INDEX](./INDEX.md)
