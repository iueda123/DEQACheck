share_package/tools 配下のシェルスクリプト・Python スクリプト概要。

## Shell

### generateMdVersionOfPdfAcrossFolders.sh
trgts 配列で指定した各研究ディレクトリに入り pdf2md.sh を実行し、生成された .md とアーティファクトを上位の md/ に集約する。

### ask_AiToDeQa_v10_1.sh
ガイド/テンプレートを用いて DE または QA の自動生成を AI エージェント（gemini/claude/codex）に依頼するメインドライバ。リスト/単一研究指定、ロックファイルで多重実行防止、ドライラン対応。

### ask_AiToDe_a-study_v11.sh
DE/QA 名を任意に付けて AI 依頼を行う簡易版。研究リストまたは単一研究を処理し、結果ファイル名に --name を反映する。入力フォルダ構成は通常の AuthorYear ごとを想定。

### ask_AiToDe_studies_v11.sh
複数研究の materials/optimized を study_1, study_2... に並べ直し、AI が横断参照しながら DE を生成するモード。並列配置で「複数研究を横断的に見る」用途に特化（研究ごと AuthorYear ディレクトリがなくても動かせる）。

### ask_AiToQa_v2.sh
QA_v9 想定の AI 依頼スクリプト。リスト/単一研究指定、ドライラン既定、ログ付き。

### 03_checkStructureKeyOfJson_and_Normalization.sh
JSON 構造/キーを checkAndNormalizeJson/subfuncs/checkJsonStructureAndKey.py で検査し、FAIL/ERROR だけ checkAndNormalizeJson/subfuncs/ask_AiToNormalize_for_DE_v10.sh で正規化を試行するオーケストレーター（DE/QA 対応、ドライラン可、notes へ TSV/詳細ログを出力）。

### checkExistenceOfJsonFiles.sh
data/<AuthorYear>/<DE|QA>/json に各エージェント（gemini/claude/codex）の JSON が存在するか確認し、notes 配下に TSV を出力。

### deleteLowFillRateJsons.sh
notes/DE_JsonFillRate.tsv を参照し、指定エージェントのフィル率が閾値未満の研究について <AuthorYear>/DE_v10/json 配下の該当 JSON を削除する（既定はドライラン）。

### prepareHumanJsonFilesForQA.sh / prepareQaJsonFiles.sh
QA テンプレートを data/<AuthorYear>/QA/json にタイムスタンプ付きでコピー。既に JSON がある場合はスキップ。

### start_DEQACheck_simple.sh
Java 17 の DEQACheck-v20251109-all.jar を呼び出す簡易ランチャー（引数に AuthorYear を渡す）。

-------------------------

## Python

### checkJsonFillRate.py
data 配下の AuthorYear ごとに DE/QA JSON の葉ノード記入率を集計し、notes/DE_JsonFillRate.tsv または QA_JsonFillRate.tsv を出力。

### convert_DE_Json_to_Md_v10_1.py
DE v10_1 形式の JSON を Markdown に整形出力（ファイル指定で上書き/別ファイル書き出し対応）。

### fillJson_DECAA_Part.py
Human 用 DE JSON の clinical_application_and_analysis_part の指定フィールドを、同階層の codex JSON から補完（既存値は保持、フィールドを --item で選択、ドライラン可）。

### fillJson_DENM_Part.py
normative_modeling_part の不足フィールドを codex JSON から補完（--item で対象フィールド指定、ドライラン可）。

### fillJson_DERCI_Part.py
reference_cohort_and_imaging_part の不足フィールドを codex JSON から補完（--item で対象フィールド指定、ドライラン可）。

### fillJson_DESC_Part.py
study_characteristics_part の sc1–sc3 を codex JSON から欠損補完（ドライラン可）。

### fillJson_DESI_Part.py
study_identification_part の si1–si5 を codex JSON から欠損補完（ドライラン可）。 

### json_to_md_v7.py
QA v7 JSON（common/normative_modeling/clinical_research）を Markdown に変換。 

### md_to_json_v7.py
QA v7 の Markdown から JSON に再変換（上書きオプション付き）。
