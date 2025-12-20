# 神経画像におけるノルマティブモデリングのデータ抽出ガイダンス（システマティックレビュー）

-------------------

## プロンプト概要
あなたは神経画像のノルマティブモデリング研究からデータを抽出する熟練したレビューアです。このデータ抽出は、精神・神経疾患に対する神経画像の方法論的検討と応用に関するシステマティックレビューの一環として行われます。

-------------------

## 本レビューの目的

本システマティックレビューでは、神経画像および神経生理検査（例: MRI, PET, EEG, MEG）を用いたノルマティブモデリング研究を評価します。以下の3点を扱います:

  * 1. **モダリティ**: ノルマティブモデリングで用いられている計測技術は何か、それぞれどの程度使われているか。MRIのような一般的モダリティでは、どのシーケンス（例: T-weighted）が最も頻用されるか。
  * 2. **方法論**: モダリティをまたいでノルマティブモデリング研究はどのように設計・検証されているか。サンプルサイズ、共変量、前処理、統計モデル、ハーモナイゼーション手法、検証戦略などを抽出する。
  * 3. **臨床的スコープ**: どの精神・神経疾患がノルマティブモデリングで検討されているか、個人レベルの逸脱パターンや臨床的ユーティリティは何が報告されているか。

-------------------

## ソース資料の場所

- 対象となる論文はカレントディレクトリ下にある「study_001」「study_002」「study_003」「study_004」「study_005」下にあります。これらstudiesを横断的に参照しながら、次のセクションにある抽出依頼にある情報を抽出してください。必要に応じてサブフォルダも参照してください。

-------------------

## 抽出依頼内容


  * 各研究で用いられているデータセットを列挙してください。

{
  "Study A": ["dataset_a", "dataset_b", "dataset_c"], 
  "Study B": ["dataset_a", "dataset_d", "dataset_e"], 
  "Study C": ["dataset_a", "dataset_d", "dataset_e"], 
  "Study D": ["dataset_a", "dataset_d", "dataset_e"], 
  "Study E": ["dataset_a", "dataset_d", "dataset_e"] 
}

  * 各研究の各データセット が何の目的（train, validation, test, transfer for clinical research, patient for clinical research など）に用いられているか？

{
  "Study A": {
      "dataset_a": ,
      "dataset_b": ,
      "dataset_c": 
  }, 
  "Study B": {
      "dataset_a": , 
      "dataset_d": , 
      "dataset_e": 
  },  
  "Study C": ["dataset_a", "dataset_d", "dataset_e"], 
  "Study D": ["dataset_a", "dataset_d", "dataset_e"], 
  "Study E": ["dataset_a", "dataset_d", "dataset_e"] 
}



  * 各研究においてNormative model構築に用いられた健常者データセットのN数について教えてください。Overall (trainだけでなくvalidationやtestも含めた) 段階と、train段階を区別して答えてください。回答形式は以下のようにしてください。

```json
{

}
```  

  * Normative model構築時の健常者データセットの年齢に関するmean, sd, median, iqr, min, maxを教えてください。Overall (trainだけでなくvalidationやtestも含めた) 段階と、train段階を区別して答えてください。もし本文に明記されていない場合は、meanとsdに関しては weighted mean of ages、pooled sd of ages の算出を試みてください。その他統計値は文脈から推定を試みてください。
  
```json
{

}
```  


どういうフォーマットで返してほしいか。




