# The guide for a data extraction guide

## レビューの背景、目的

システマティックレビューをしています。
概要は /media/iu/STORAGE/__GitHub__/Prjct_2025_DEQACheck/docs/POSPERO/PROSPERO_final.pdf に書いてあります。
プロトコル文書と呼びましょう[^1]。

[^1]: 一般的にこう読んでまちがいないだろうか？

## 作業状況、データ配置

いまTiAb Screening、Full Text Screening を経て 122の文献が選び出されました。
`share_package/data/` 下にの子フォルダとして配置されています（Someone20XXとsettingsは除く）。
そして情報抽出（data extraction）を行う段階にあります。

## 収集したい情報の概要

今回抽出したい情報はnormative model (NM) を構築するにあたり用いたデータセットに関する情報です。
対象としている122文献にはそれぞれ normative modelingという、
脳MRI画像から得た特徴量から年齢や性別などの交絡因子の影響を取り除いて
着目する因子による集団間の差異を評価しやすくする統計学的な処理が用いられています。
このNM手法は一般的に疾患のない健康な者のデータを用いてmodelingすることから始まります。
このようなmodelingの際に用いられているデータセットに関する情報を収集したいです。


## 収集したい情報の単位

情報収集は Normative Model 毎です。１つの論文に複数のNormative Modelが含まれている可能性があります（スクリーニングの失敗によりNMが一つも含まれていない可能性があります）。

## 収集したい情報の詳細

収集したい情報は以下です。
  * 人数
  * 男性人数、女性人数
  * 年齢の平均、標準偏差、最小、最大
    * 平均
  * これら情報が Training Phaseのものか、Overall Phase のものか （特定できない場合はUnknown）

## 収集したい情報の出力形式

JSONで出力したい。

```
{


}
```




