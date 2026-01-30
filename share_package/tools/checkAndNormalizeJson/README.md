# checkAndNormalizeJson

JSONファイルの構造・キーのチェックと、AIエージェントを使用した正規化を行うツール群です。

## 概要

このディレクトリには以下のスクリプトが含まれています：

| ファイル | 説明 |
|----------|------|
| `checkJsonStructureAndKey.py` | JSONの構造・キーをテンプレートと照合してチェック |
| `ask_AiToNormalize_for_DE_v10.sh` | DE_v10用 AIエージェント正規化スクリプト |
| `ask_AiToNormalize_for_DE_v11.sh` | DE_v11用 AIエージェント正規化スクリプト |
| `ask_AiToNormalize_for_DE_v12.sh` | DE_v12用 AIエージェント正規化スクリプト |
| `normalizeJsonsUsingAiAgent.sh` | 複数ファイルの一括正規化ラッパー |
| `check_rslt/` | チェック結果の出力ディレクトリ |

## 1. checkJsonStructureAndKey.py

JSONファイルの構造とキーをテンプレートと照合し、不一致を検出します。

### 使用方法

```bash
python3 checkJsonStructureAndKey.py \
    --data-type <データタイプ> \
    --target-folder <対象フォルダ> \
    --template <テンプレートJSONパス> \
    [--output-folder <出力先>]
```

### オプション

| オプション | 必須 | 説明                                                                                               |
|------------|------|--------------------------------------------------------------------------------------------------|
| `--data-type` | 必須 | データタイプ（例: `DE_v11`, `DE_v12`, `QA_v9`）                                                           |
| `--target-folder` | 必須 | スキャン対象のルートフォルダ (例： `share_package/data`)                                                         |
| `--template` | 必須 | 期待される構造を定義したテンプレートJSON (例: `share_package/templates/*.json`) |
| `--output-folder` | 任意 | 結果の出力先（デフォルト: `./check_rslt`）                                                                    |

### 使用例

```bash
# DE_v12 のJSONファイルをチェック
python3 checkJsonStructureAndKey.py \
    --data-type DE_v12 \
    --target-folder ../../data \
    --template ../../templates/DE_v12_Author20XX_by_Someone_YYYYmmddHHMMSS.json
```

### 出力

- **概要TSV**: `check_rslt/json_structure_check_<data_type>_<タイムスタンプ>.tsv`
  - 各JSONファイルの検査結果（FAIL/WARN）を一覧表示
- **詳細ディレクトリ**: `check_rslt/json_structure_check_<data_type>_<タイムスタンプ>_details/`
  - 各JSONファイルごとの詳細な不一致情報を `.txt` ファイルとして保存

### 結果の種類

| 結果 | 説明 |
|------|------|
| PASS | テンプレートと完全に一致 |
| WARN | 軽微な警告あり（型の違いなど） |
| FAIL | 構造またはキーの不一致あり |
| ERROR | JSONの読み込みエラー |

---

## 2. ask_AiToNormalize_for_DE_v*.sh

AIエージェント（Gemini, Claude, Codex）を使用して、単一のJSONファイルをテンプレートに合わせて正規化します。

### 使用方法

```bash
./ask_AiToNormalize_for_DE_v12.sh [OPTIONS]
```

### オプション

| オプション | 必須 | 説明 |
|------------|------|------|
| `-f, --file FILE` | 必須 | 正規化対象のJSONファイル |
| `-a, --agent AGENT` | 任意 | AIエージェント名（`gemini`/`claude`/`codex`）デフォルト: `codex` |
| `-r, --run` | 任意 | 実行モード（指定しないとドライラン） |
| `-n, --dry-run` | 任意 | ドライランモード（デフォルト） |
| `-v, --verbose` | 任意 | 詳細な出力を表示 |
| `-h, --help` | 任意 | ヘルプメッセージを表示 |

### 使用例

```bash
# ドライラン（実行内容の確認のみ）
./ask_AiToNormalize_for_DE_v12.sh -f ../data/Author2025/DE_v12/json/DE_v12_Author2025_by_gemini.json

# Codexを使用して実行
./ask_AiToNormalize_for_DE_v12.sh -f path/to/DE_v12_file.json -a codex -r

# Geminiを使用（詳細出力あり）
./ask_AiToNormalize_for_DE_v12.sh -f path/to/DE_v12_file.json -a gemini -r -v

# Claudeを使用して実行
./ask_AiToNormalize_for_DE_v12.sh -f path/to/DE_v12_file.json -a claude -r
```

### バージョン別対応テンプレート

| スクリプト | 対応ファイル | テンプレート |
|------------|-------------|--------------|
| `ask_AiToNormalize_for_DE_v10.sh` | `DE_*`, `QA_*` | DE_v10用, QA_v9用 |
| `ask_AiToNormalize_for_DE_v11.sh` | `DE_*` | DE_v11用 |
| `ask_AiToNormalize_for_DE_v12.sh` | `DE_*` | DE_v12用 |

### 注意事項

- JSONファイル名は `DE_` で始まる必要があります（v10では `QA_` も対応）
- 処理前にバックアップファイル（`*_backup.json`）が自動的に作成されます
- デフォルトはドライランモードで、実際の変更は行われません

---

## 3. normalizeJsonsUsingAiAgent.sh

`checkJsonStructureAndKey.py` が出力したサマリTSVをもとに、複数のJSONファイルを一括で正規化します。

### 使用方法

```bash
./normalizeJsonsUsingAiAgent.sh [OPTIONS]
```

### オプション

| オプション | 必須 | 説明 |
|------------|------|------|
| `--summary-tsv PATH` | 必須 | チェック結果のサマリTSVファイル |
| `--ask-script PATH` | 必須 | 使用する正規化スクリプト（`ask_AiToNormalize_for_*.sh`） |
| `--result {FAIL\|WARN\|ALL}` | 任意 | 対象とする結果タイプ（デフォルト: `FAIL`） |
| `--agent {codex\|gemini\|claude}` | 任意 | 使用するAIエージェント（デフォルト: `codex`） |
| `--run, -r` | 任意 | 実行モード（デフォルトはドライラン） |
| `--limit N` | 任意 | 処理するファイル数を制限 |
| `-h, --help` | 任意 | ヘルプを表示 |

### 使用例

```bash
# ドライラン（FAILのファイルを確認）
./normalizeJsonsUsingAiAgent.sh \
    --summary-tsv ./check_rslt/json_structure_check_DE_v12_20260127171907.tsv \
    --ask-script ./ask_AiToNormalize_for_DE_v12.sh

# FAILのファイルをCodexで正規化
./normalizeJsonsUsingAiAgent.sh \
    --summary-tsv ./check_rslt/json_structure_check_DE_v12_20260127171907.tsv \
    --ask-script ./ask_AiToNormalize_for_DE_v12.sh \
    --agent codex \
    --run

# WARN含む全ての問題ファイルを処理（最大5件）
./normalizeJsonsUsingAiAgent.sh \
    --summary-tsv ./check_rslt/json_structure_check_DE_v12_20260127171907.tsv \
    --ask-script ./ask_AiToNormalize_for_DE_v12.sh \
    --result ALL \
    --limit 5 \
    --run
```

---

## 典型的なワークフロー

### 1. 構造チェックの実行

```bash
python3 checkJsonStructureAndKey.py \
    --data-type DE_v12 \
    --target-folder ../../data \
    --template ../../templates/DE_v12_Author20XX_by_Someone_YYYYmmddHHMMSS.json
```

### 2. チェック結果の確認

```bash
# サマリTSVを確認
cat check_rslt/json_structure_check_DE_v12_*.tsv

# 詳細を確認
cat check_rslt/json_structure_check_DE_v12_*_details/*.txt
```

### 3. 問題のあるファイルを正規化

```bash
# まずドライランで確認
./normalizeJsonsUsingAiAgent.sh \
    --summary-tsv ./check_rslt/json_structure_check_DE_v12_*.tsv \
    --ask-script ./ask_AiToNormalize_for_DE_v12.sh

# 問題なければ実行
./normalizeJsonsUsingAiAgent.sh \
    --summary-tsv ./check_rslt/json_structure_check_DE_v12_*.tsv \
    --ask-script ./ask_AiToNormalize_for_DE_v12.sh \
    --run
```

### 4. 再度チェックして確認

```bash
python3 checkJsonStructureAndKey.py \
    --data-type DE_v12 \
    --target-folder ../../data \
    --template ../../templates/DE_v12_Author20XX_by_Someone_YYYYmmddHHMMSS.json
```

---

## 出力ディレクトリ構成

```
check_rslt/
├── json_structure_check_DE_v12_20260127171907.tsv      # 概要TSV
└── json_structure_check_DE_v12_20260127171907_details/ # 詳細ディレクトリ
    ├── DE_v12_Author2025_by_gemini_20260101120000.json.txt
    ├── DE_v12_Author2024_by_claude_20251231235959.json.txt
    └── ...
```

---

## 依存関係

- Python 3.x
- AIエージェントCLI（使用するエージェントに応じて）
  - `gemini` コマンド
  - `claude` コマンド
  - `codex` コマンド
