

DE結果を横断的に評価

元データを参照しながら

各AIの報告の妥当性をレーティング

総合的にみて適切な回答を再構築

合わせて報告形式も妥当なものにする

各種モデル、各種ツールの性能差というのも見れると思う
  * ツール名、バージョン
  * モデル名、バージョン





評価者として最適な組み合わせを決める
キャリブレーションフェーズというのもありなのかもしれない。

DetailやResonを読んだ場合、もっとも丁寧に、そしてネガティブな結果に対してはより丁寧に思考しているものを信じたくはなる。


理想的には複数のレビューで人間とのハイブリッドで使ってみて。
最適な組み合わせ、フローを模索するのが良いが。



### 全体的なフロー

1. Clarification of Review Study
  * PROSPERO
2. TiAb Screening
  * Title and Abstract 入手
  * 将来的に
3. FullText Screening
  * main PDF入手
  * Conversion
  * FullText まずはこちら
    * プロンプト作成支援
    * Ask AI
    * HumanCheck ★
      * CrossAgentExtractionResultEvaluation戦略を考える！
    * Chat with AI 
4. PreDataExtraction
  * Supple入手（できる限りの資料を集める）
  * Conversion
  * 情報粒度を考える
    * 表から
  * プロンプト作成支援
  * HumanCheck
  * 形式定義（answerに使うキーワード、文字列、数値精度）
5. DataExtraction
  * 情報粒度を考える
    * 最終的にanswerを横並びで比較することになることを意識
    * 表から
    * なるべくMajor Categoryで (※)
  * プロンプト作成支援
6. Comparation
  * Ask AI
  * Evaluation
  * HumanCheck
  * Chat with AI
7. Tabulation


###　File Conversion
  * さしあたってToMD
  * 将来的にToWiki

### Chat with AI　
  * ひとまずNLM
  * 将来的には自前 で Chat with AI refering DE-Guide
  
### Comparationについて

Comparatorを作って、眺めて、初めてJudgementに悩む。
どのように並べて（どのようにタブ階層をつくって）比較するかのイメージができて
初めてAIの力を借りたくなる。

なのでどのように一般化するかが悩ましい。
例えばNMレビューで言えば、論文別の情報に落とし込みたいのか、モデル別の情報に落とし込みたいのか、疾患別の情報に落とし込みたいのか、

表にすることを目標として出発すればよいのか？
そして各行に対応させる情報粒度（整理観点）を決め、
各列に定めた観点で比較する。
そこまで落とし込んでから、情報抽出プロンプト、抽出JSON形式を考え、
複数のAIが生成したJSONを別のAIに横断的に読んでもらい、AI側の最終抽出結果としてもらう
それに対して人間が情報抽出プロンプトを参考にChatで対話して確認し、
最終判断とする。


※Comparatorに落とし込むときにこんな問題も生じている。
各行を疾患毎にすると決めた。キーワードを定義した。DEさせてみる。
すると似たキーワード、しかし違うキーワードで情報をまとめているために、
横並びで比較できず。
やむなくAIが出力したJSON上のキーワードを書き換えた。
Minor Category KeywordでComparateしようとするとこうなるね。
Major Category Keywordならよいのだろうか？



情報抽出プロンプトが１個とかではないこともやっかいだ。
NMレビューではv14とかまで膨らんだ。
テーブルの数だけ



