#!/bin/bash

# スクリプト情報の取得
this_script_path="$(readlink -f "${BASH_SOURCE[0]}")"
this_script_name=$(basename "${this_script_path}")
this_script_parent=$(dirname ${this_script_path})

# AIに不必要にファイルを参照させないようにするために、
# カレントディレクトリを明確に移動させてから作業の依頼を出す。
# 作業に必要なファイルは上の階層から一時的にコピーして持ってくる。
# 書き込み状況を確認してからファイルを消す。

# 変更履歴:
#   * ロックファイル機構を追加: 複数のAIエージェントが同じ作業フォルダで同時に作業するのを防止

#######################
# ログ出力補助関数
#######################
# echo の代わりに使用し、必要に応じてログへも追記します。
# 使い方: techo [-n] TEXT...
#  -n: 末尾の改行を抑制（echo -n 相当）
# _log_file_name がスコープ内で設定されていれば、そのファイルに追記します。
# 未設定の場合は通常の echo と同等の挙動になります。
function techo() {
    local newline=true
    if [[ "$1" == "-n" ]]; then
        newline=false
        shift
    fi

    if [[ -n "${_log_file_name:-}" ]]; then
        if $newline; then
            printf '%s\n' "$*" | tee -a "${_log_file_name}"
        else
            printf '%s' "$*" | tee -a "${_log_file_name}"
        fi
    else
        if $newline; then
            printf '%s\n' "$*"
        else
            printf '%s' "$*"
        fi
    fi
}

#######################
# ヘルプ表示関数
#######################
show_help() {
    cat << EOF
使用方法: ${this_script_name} [OPTIONS]

AIエージェント（Gemini, Claude, Codex, Copilot等）を使用して、
データ抽出（DE）または品質評価（QA）を自動実行するスクリプトです。

オプション:
  -a, --agent AGENT      AIエージェント名を指定 (gemini|claude|codex)
  -n, --name NAME        処理名を指定（結果ファイル名に使用、例: DE, QA）
  -l, --list FILE        処理すべき研究名が1行ずつ記載されたファイルのパス
  -s, --study STUDY      単一の研究のみ処理 (互換目的)
  --ensure-json-structure-by AGENT
                         JSON整形の後処理を実行 (gemini|claude|codex)
  -r, --run              実際に処理を実行（デフォルトはドライラン）
  -d, --dry-run          明示的にドライランに設定（デフォルト）
  -v, --verbose          詳細な出力を表示
  -h, --help             このヘルプメッセージを表示

使用例:
  # 研究名の一覧を記したファイルを指定して実行（例: list.txt）
  ${this_script_name} --agent gemini --name DE --list tools/TaegetStudies.txt

  # Claudeを使用してQA処理を実行（複数の研究を対象）
  ${this_script_name} -a claude -n QA -l tools/TaegetStudies.txt

  # ドライラン（実行内容の確認のみ・デフォルト）
  ${this_script_name} -a codex -n DE -l tools/TaegetStudies.txt

  # 実行モードで処理を走らせる
  ${this_script_name} -a codex -n DE -l tools/TaegetStudies.txt -r

  # JSON整形の後処理を実行（モデル指定あり）
  ${this_script_name} -a gemini -n DE -l tools/TaegetStudies.txt -r --ensure-json-structure-by codex

注意事項:
  - --agent と --name は必須オプションです
  - -l/--list または -s/--study のいずれかが必須です。
  - 既定はドライランです（実コマンドは実行されません）
  - 実行モードにするには -r または --run を指定してください

EOF
    exit 0
}

#######################
# デフォルト値
#######################
AiAgentName=""
DE_Name=""
ListFilePath=""
SingleStudy=""  # backward-compat for -s/--study (deprecated)
DryRun=true
Verbose=false
EnsureJsonStructureBy=""

#######################
# オプション解析
#######################
while [[ $# -gt 0 ]]; do
    case $1 in
        -a|--agent)
            AiAgentName="$2"
            shift 2
            ;;
        -n|--name)
            DE_Name="$2"
            shift 2
            ;;
        -l|--list)
            ListFilePath="$2"
            shift 2
            ;;
        -s|--study)
            # backward compatibility (deprecated)
            SingleStudy="$2"
            shift 2
            ;;
        --ensure-json-structure-by)
            if [[ -z "${2:-}" || "$2" =~ ^- ]]; then
                echo "エラー: --ensure-json-structure-by には AGENT が必要です (gemini|claude|codex)"
                exit 1
            fi
            EnsureJsonStructureBy="$2"
            shift 2
            ;;
        -r|--run|--execute)
            DryRun=false
            shift
            ;;
        -d|--dry-run)
            # デフォルトがドライランのため、明示指定時も true に設定
            DryRun=true
            shift
            ;;
        -v|--verbose)
            Verbose=true
            shift
            ;;
        -h|--help)
            show_help
            ;;
        *)
            echo "エラー: 不明なオプション: $1"
            echo "ヘルプを表示するには -h または --help を使用してください"
            exit 1
            ;;
    esac
done

#######################
# 入力検証
#######################
if [[ -z "${AiAgentName}" ]]; then
    echo "エラー: AIエージェント名が指定されていません（-a または --agent を使用）"
    echo "ヘルプを表示するには -h または --help を使用してください"
    exit 1
fi

if [[ -z "${DE_Name}" ]]; then
    echo "エラー: 処理名称が指定されていません（-n または --name を使用）"
    echo "ヘルプを表示するには -h または --help を使用してください"
    exit 1
fi

# JSON整形オプションの検証
if [[ -n "${EnsureJsonStructureBy}" ]]; then
    if [[ ! "${EnsureJsonStructureBy}" =~ ^(gemini|claude|codex)$ ]]; then
        echo "エラー: 無効なAIエージェント名(ensure-json): ${EnsureJsonStructureBy}"
        echo "有効な値: gemini, claude, codex"
        exit 1
    fi
fi

# AIエージェント名の検証
if [[ ! "${AiAgentName}" =~ ^(gemini|claude|codex|cgemini|opus)$ ]]; then
    echo "エラー: 無効なAIエージェント名: ${AiAgentName}"
    echo "有効な値: gemini, claude, codex"
    exit 1
fi

# 処理名の検証（ファイル名として有効な文字列かチェック）
if [[ "${DE_Name}" =~ [/\\:\*\?\"\<\>\|[:space:][:cntrl:]] ]] || [[ -z "${DE_Name}" ]]; then
    echo "エラー: 処理名にファイル名として無効な文字が含まれています: ${DE_Name}"
    echo "使用できない文字: / \\ : * ? \" < > | 空白"
    exit 1
fi

if [[ "${DE_Name}" == "." ]] || [[ "${DE_Name}" == ".." ]]; then
    echo "エラー: 処理名に '.' または '..' は使用できません"
    exit 1
fi

# 研究リスト指定の必須チェック
if [[ -z "${ListFilePath}" && -z "${SingleStudy}" ]]; then
    echo "エラー: 対象研究が指定されていません。-l/--list または -s/--study のいずれかを指定してください。"
    echo "ヘルプを表示するには -h または --help を使用してください"
    exit 1
fi

#######################
# 設定ファイルの決定
#######################
    #
    # DE系ドキュメント
    #
    #GuideFile=${this_script_parent}/../prompts/DE_Guide_v9_1.md
    #GuideFile=${this_script_parent}/../prompts/DE_Guide_v10.md
    #GuideFile=${this_script_parent}/../prompts/DE_Guide_v10_1.md
    #GuideFile=${this_script_parent}/../prompts/DE_Guide_v12.md
    GuideFile=${this_script_parent}/../prompts/DE_Guide_v14.md

    #TemplateFile=${this_script_parent}/../templates/DE_Author20XX_by_Someone_YYYYmmddHHMMSS_for_v9.json
    #TemplateFile=${this_script_parent}/../templates/DE_Author20XX_by_Someone_YYYYmmddHHMMSS_for_v10.json
    #TemplateFile=${this_script_parent}/../templates/DE_Author20XX_by_Someone_YYYYmmddHHMMSS_for_v10_1.json
    #TemplateFile=${this_script_parent}/../templates/DE_v12_Author20XX_by_Someone_YYYYmmddHHMMSS.json
    TemplateFile=${this_script_parent}/../templates/DE_v14_Author20XX_by_Someone_YYYYmmddHHMMSS.json
    NormalizeTemplateFile=${this_script_parent}/../templates/DE_v12_Author20XX_by_Someone_YYYYmmddHHMMSS.json


# ファイル存在確認
if [[ ! -f ${GuideFile} ]]; then
    echo "エラー: ガイドファイルが見つかりません: ${GuideFile}"
    exit 1
fi

if [[ ! -f ${TemplateFile} ]]; then
    echo "エラー: テンプレートファイルが見つかりません: ${TemplateFile}"
    exit 1
fi
if [[ -n "${EnsureJsonStructureBy}" && ! -f ${NormalizeTemplateFile} ]]; then
    echo "エラー: JSON整形テンプレートが見つかりません: ${NormalizeTemplateFile}"
    exit 1
fi

#######################
# スクリプト情報表示
#######################
if [[ ${Verbose} == true ]]; then
    echo "============================================"
    echo "スクリプト情報"
    echo "============================================"
    echo "スクリプトパス: ${this_script_path}"
    echo "AIエージェント: ${AiAgentName}"
    echo "ガイドファイル: ${GuideFile}"
    if [[ -n "${ListFilePath}" ]]; then
        echo "リストファイル: ${ListFilePath}"
    fi
    if [[ -n "${SingleStudy}" ]]; then
        echo "対象研究(単一・deprecated -s): ${SingleStudy}"
    fi
    if [[ ${DryRun} == true ]]; then
        echo "モード: ドライラン（実行なし）"
    else
        echo "モード: 実行モード"
    fi
    if [[ -n "${EnsureJsonStructureBy}" ]]; then
        echo "JSON整形: 有効 (${EnsureJsonStructureBy})"
    fi
    echo "============================================"
    echo ""
fi



#######################
# 研究リストの設定
#######################
AuthorYearArray=()

if [[ -n "${ListFilePath}" ]]; then
    if [[ ! -f "${ListFilePath}" ]]; then
        echo "エラー: 指定されたリストファイルが見つかりません: ${ListFilePath}"
        exit 1
    fi

    # リストファイルを読み込み（空行・空白・#以降のコメント・CRLF対応）
    while IFS= read -r _line || [[ -n "$_line" ]]; do
        # コメント除去
        _line="${_line%%#*}"
        # CR除去（Windows改行対策）
        _line="${_line%$'\r'}"
        # 前後空白除去
        _line="$(echo -e "${_line}" | sed -e 's/^[[:space:]]*//' -e 's/[[:space:]]*$//')"
        [[ -z "${_line}" ]] && continue
        AuthorYearArray+=("${_line}")
    done < "${ListFilePath}"
elif [[ -n "${SingleStudy}" ]]; then
    # deprecated -s/--study で指定された単一研究のみを処理
    AuthorYearArray+=("${SingleStudy}")
else
    echo ""
fi

# Note: 
#
# notes/DE_JsonFillRate.tsv を参照して、claude かつ rate が 0.90 未満のものについて tools/ask_AiToDeQa_for_v8.sh 内の line 198-325 の範囲でコメントを無効化し処理対象となるようにしてください。
#
# notes/DE_JsonFillRate.tsv を参照して、gemini かつ rate が 0.90 未満のものについて <AuthorYear>/DE_v10/json下にある gemini 関連ファイルを削除するスクリプトを ./tools/ の下に作ってください。
# 
# notes/DE_json_existence_by_gemini.tsv を参照して、Status が NOT_FOUND であるものついて 、tools/ask_AiToDeQa_for_v10.sh 内の line 239-366 の範囲でコメントを無効化し処理対象となるようにしてください。
# 

#######################
# ロックファイル削除関数
#######################
function cleanup_lockfile(){
    if [[ -n "${LOCK_FILE}" ]] && [[ -f "${LOCK_FILE}" ]]; then
        # ロックファイルが自分のプロセスIDと一致する場合のみ削除
        if grep -q "^PID: $$" "${LOCK_FILE}" 2>/dev/null; then
            rm -f "${LOCK_FILE}"
            if [[ ${Verbose} == true ]]; then
                echo "ロックファイルを削除しました: ${LOCK_FILE}"
            fi
        fi
    fi
}

#######################
# AIエージェント実行関数
#######################
function askAiAgent(){

    _ai_agent_name=$1
    _guide_file=$2
    _result_file_name=$3
    _actual_run=$4
    _log_file_name=$5

if [[ ${_ai_agent_name} == "opus" ]]; then
        techo ""
        techo "========== Opus via Copilot コマンド =========="
        techo "copilot  --prompt \\"
        techo "    \"${_guide_file} に従って作業をしてください。作業結果は ${_result_file_name} へ書き込んでください。\" \\"
        techo "    --model \"claude-opus-4.6\" \\"
        techo "    --allow-all-tools \\"
        techo "    --add-dir . \\"
        techo "    --deny-url \"*\" "
        techo "===================================="
        techo ""

        if [[ ${_actual_run} == true ]]; then
            sleep 3
            techo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"
            techo "Opus via Copilot が作業を実行中..."
            techo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"

            copilot --prompt "${_guide_file} に従って作業をしてください。作業結果は ${_result_file_name} へ書き込んでください。" \
                --model "claude-opus-4.6" \
                --allow-all-tools \
                --add-dir . \
                --deny-url "*" \
                2>&1 | tee -a ${_log_file_name}

            techo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
            techo "Opus via Copilot による作業が完了しました"
            techo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
        fi

   elif [[ ${_ai_agent_name} == "cgemini" ]]; then
        techo ""
        techo "========== Gemini via Copilot コマンド =========="
        techo "copilot  --prompt \\"
        techo "    \"${_guide_file} に従って作業をしてください。作業結果は ${_result_file_name} へ書き込んでください。\" \\"
        techo "    --model \"gemini-3-pro-preview\" \\"
        techo "    --allow-all-tools \\"
        techo "    --add-dir . \\"
        techo "    --deny-url \"*\" "
        techo "===================================="
        techo ""

        if [[ ${_actual_run} == true ]]; then
            sleep 3
            techo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"
            techo "Gemini via Copilot が作業を実行中..."
            techo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"

            copilot --prompt "${_guide_file} に従って作業をしてください。作業結果は ${_result_file_name} へ書き込んでください。" \
                --model "gemini-3-pro-preview" \
                --allow-all-tools \
                --add-dir . \
                --deny-url "*" \
                2>&1 | tee -a ${_log_file_name}

            techo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
            techo "Gemini via Copilot による作業が完了しました"
            techo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
        fi

    elif [[ ${_ai_agent_name} == "gemini" ]]; then
        techo ""
        techo "========== Gemini コマンド =========="
        techo "gemini -p \\"
        techo "    \"${_guide_file} に従って作業をしてください。作業結果は ${_result_file_name} へ書き込んでください。\" \\"
        techo "    --approval-mode auto_edit \\"
        techo "    --allowed-tools \"run_shell_command\""
        techo "===================================="
        techo ""

        if [[ ${_actual_run} == true ]]; then
            sleep 3
            techo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"
            techo "Gemini が作業を実行中..."
            techo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"

            gemini -p "${_guide_file} に従って作業をしてください。作業結果は ${_result_file_name} へ書き込んでください。" \
                --approval-mode auto_edit \
                --allowed-tools "run_shell_command" \
                2>&1 | tee -a ${_log_file_name}

            techo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
            techo "Gemini による作業が完了しました"
            techo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"

        fi
    elif [[ ${_ai_agent_name} == "claude" ]]; then
        techo ""
        techo "========== Claude コマンド =========="
        techo "claude -p \\"
        techo "    \"${_guide_file} に従って作業をしてください。作業結果は ${_result_file_name} へ書き込んでください。\" \\"
        techo "    --allowedTools \"Bash,Read\" \\"
        techo "    --permission-mode acceptEdits"
        techo "===================================="
        techo ""

        if [[ ${_actual_run} == true ]]; then
            sleep 3
            techo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"
            techo "Claude が作業を実行中..."
            techo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"

            claude -p \
                "${_guide_file} に従って作業をしてください。作業結果は ${_result_file_name} へ書き込んでください。" \
                --allowedTools "Bash,Read" \
                --permission-mode acceptEdits \
                2>&1 | tee -a ${_log_file_name}

            techo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
            techo "Claude による作業が完了しました"
            techo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"

        fi
    elif [[ ${_ai_agent_name} == "codex" ]]; then
        techo ""
        techo "========== Codex コマンド =========="
        techo "codex exec --full-auto --skip-git-repo-check -C . \\"
        techo "    \"${_guide_file} に従って作業をしてください。作業結果は ${_result_file_name} へ書き込んでください。\""
        techo "===================================="
        techo ""

        if [[ ${_actual_run} == true ]]; then
            sleep 3
            techo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"
            techo "Codex が作業を実行中..."
            techo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"

            codex exec --full-auto --skip-git-repo-check -C . \
                "${_guide_file} に従って作業をしてください。作業結果は ${_result_file_name} へ書き込んでください。" \
                2>&1 | tee -a ${_log_file_name}

            techo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
            techo "Codex による作業が完了しました"
            techo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"

        fi
    else
        echo "エラー: 不明なAIエージェント名が指定されました: ${_ai_agent_name}"
        exit 1
    fi

}

#######################
# JSON整形補助関数
#######################
function normalize_copy_template(){
    _target_json_file=$1
    cp "${NormalizeTemplateFile}" "$(dirname "${_target_json_file}")"
}

function normalize_backup_json(){
    _target_json_file=$1
    _target_json_file_name=$(basename "${_target_json_file%.json}")
    _target_json_file_ext="${_target_json_file##*.}"
    _backup_file="$(dirname "${_target_json_file}")/${_target_json_file_name}_backup.${_target_json_file_ext}"
    cp "${_target_json_file}" "${_backup_file}"
}

function normalize_remove_template(){
    _target_json_file=$1
    _copied_template="$(dirname "${_target_json_file}")/$(basename "${NormalizeTemplateFile}")"
    rm -f "${_copied_template}"
}

function normalize_ask_agent(){
    _target_json_file=$1
    _normalize_agent=$2

    _working_directory=$(dirname "${_target_json_file}")
    _previous_directory=$(pwd)
    cd "${_working_directory}" || exit 1

    _target_json_file_name=$(basename "${_target_json_file}")
    _template_json_file_name=$(basename "${NormalizeTemplateFile}")
    _prompt="${_target_json_file_name} の構造、キーについて問題があれば修正をお願いします。このJSONファイルは ${_template_json_file_name} と 同じ構造、同じキーを持つことが期待されていますが、これら要件を満たしていない可能性があります。もし ${_target_json_file_name} の構造やキーが ${_template_json_file_name} と不一致であれば修正してください。"

    if [[ ${_normalize_agent} == "gemini" ]]; then
        echo ""
        echo "========== Gemini コマンド =========="
        echo "gemini -p \\"
        echo "    \"${_prompt}\" \\"
        echo "    --approval-mode auto_edit \\"
        echo "    --allowed-tools \"ShellTool(git status,rm,mv,mkdir)\""
        echo "===================================="
        echo ""

        sleep 3
        echo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"
        echo "Gemini を実行中..."
        echo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"

        gemini -p "${_prompt}" \
            --approval-mode auto_edit \
            --allowed-tools "ShellTool(git status,rm,mv,mkdir)"

        echo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
        echo "Gemini の実行が完了しました"
        echo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"

    elif [[ ${_normalize_agent} == "claude" ]]; then
        echo ""
        echo "========== Claude コマンド =========="
        echo "claude -p \\"
        echo "    \"${_prompt}\" \\"
        echo "    --allowedTools \"Bash,Read\" \\"
        echo "    --permission-mode acceptEdits"
        echo "===================================="
        echo ""

        sleep 3
        echo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"
        echo "Claude を実行中..."
        echo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"

        claude -p \
            "${_prompt}" \
            --allowedTools "Bash,Read" \
            --permission-mode acceptEdits

        echo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
        echo "Claude の実行が完了しました"
        echo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"

    elif [[ ${_normalize_agent} == "codex" ]]; then
        echo ""
        echo "========== Codex コマンド =========="
        echo "codex exec --full-auto --skip-git-repo-check -C . \\"
        echo "    \"${_prompt}\""
        echo "===================================="
        echo ""

        sleep 3
        echo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"
        echo "Codex を実行中..."
        echo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"

        codex exec --full-auto --skip-git-repo-check -C . \
            "${_prompt}"

        echo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
        echo "Codex の実行が完了しました"
        echo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"

    else
        echo "エラー: 不明なAIエージェント名が指定されました: ${_normalize_agent}"
        exit 1
    fi

    cd "${_previous_directory}" || exit 1
}

function normalize_json_file(){
    _target_json_file=$1
    _normalize_agent=$2

    echo ""
    echo "============================================"
    echo "  ${_normalize_agent} を使用したJSON正規化処理"
    echo "============================================"
    echo ""
    echo "対象ファイル: $(basename "${_target_json_file}")"
    echo ""

    normalize_copy_template "${_target_json_file}"
    normalize_backup_json "${_target_json_file}"
    normalize_ask_agent "${_target_json_file}" "${_normalize_agent}"
    normalize_remove_template "${_target_json_file}"
}

#######################
# メイン処理開始
#######################
echo ""
echo "============================================"
echo "  ${AiAgentName} を使用した ${DE_Name} 処理を開始"
echo "============================================"
echo ""

if [[ ${DryRun} == true ]]; then
    echo "【ドライランモード】実際の実行は行いません"
    echo ""
fi

for _author_year in "${AuthorYearArray[@]}"; do

  echo ""
  echo "============================================"
  echo "  対象研究: ${_author_year}"
  echo "============================================"
  
  # Ask AI agent if the result json was not found.
  _folder="${this_script_parent}/../data/${_author_year}/${DE_Name}/json/"
  if [[ ! -d ${_folder} ]]; then mkdir -p ${_folder}; fi
  _expected_result_file="${DE_Name}_${_author_year}_by_${AiAgentName}_*.json"

  _proceed=false
  if find ${_folder} -maxdepth 1 -type f -iname ${_expected_result_file} | grep -q .; then
      echo "結果JSONファイルは既に存在します"
      echo "フォルダを確認してください: ${_folder}"

      read -r -p "既存のJSONがあります。処理を続行しますか？ [y/N]: " _ans
      if [[ "${_ans}" =~ ^[Yy]$ ]]; then
          echo "ユーザー確認により処理を続行します"
          _proceed=true
      else
          echo "処理をスキップします"
          echo ""
      fi
  else
      echo "結果JSONファイルはまだ作成されていません"
      echo ""
      _proceed=true
  fi

  if [[ ${_proceed} == true ]]; then

      # Make a flag file
      echo "The result json file is not created yet." \
          > ${this_script_parent}/${DE_Name}_${_author_year}_by_${AiAgentName}_is_not_yet.txt

      # Set Working Directory
      # ai_workspace/<timestamp>/ にGuideファイルとTemplateファイルを配置
      # ai_workspace/<timestamp>/study_1/ に研究ファイルを配置し、AIはそこで作業する
      ai_workspace_base=${this_script_parent}/../ai_workspace
      _timestamp=$(date +%Y%m%d%H%M%S%3N)
      ai_workspace=${ai_workspace_base}/${AiAgentName}_${DE_Name}_${_timestamp}
      study_1_directory=${ai_workspace}/study_1
      source_directory=${this_script_parent}/../data/${_author_year}/materials/optimized

      if [[ ${Verbose} == true ]]; then
          echo "AIワークスペースベース: ${ai_workspace_base}"
          echo "タイムスタンプフォルダ: ${ai_workspace}"
          echo "研究ディレクトリ: ${study_1_directory}"
          echo "ソースディレクトリ: ${source_directory}"
      fi

      # ロックファイルのチェックと作成
      LOCK_FILE="${ai_workspace}/.lock_${DE_Name}_processing"

      # ai_workspaceディレクトリが存在しない場合は作成
      if [[ ! -d "${ai_workspace}" ]]; then
          mkdir -p "${ai_workspace}"
      fi

      # ロックファイルが存在するかチェック
      if [[ -f "${LOCK_FILE}" ]]; then
          echo ""
          echo "警告: 他のプロセスが作業中です"
          echo "ロックファイル: ${LOCK_FILE}"
          echo "内容:"
          cat "${LOCK_FILE}"
          echo ""
          echo "この研究(${_author_year})の処理をスキップします"
          echo ""
          continue
      fi

      # ロックファイルを作成
      cat > "${LOCK_FILE}" << EOF
AIエージェント: ${AiAgentName}
処理タイプ: ${DE_Name}
研究: ${_author_year}
PID: $$
開始時刻: $(date '+%Y-%m-%d %H:%M:%S')
DryRun: ${DryRun}
EOF

      if [[ ${Verbose} == true ]]; then
          echo "ロックファイルを作成しました: ${LOCK_FILE}"
      fi

      # 異常終了時にロックファイルを削除するためのtrap設定
      trap cleanup_lockfile EXIT INT TERM

      # study_1ディレクトリを作成（既存の場合はクリア）
      if [[ -d "${study_1_directory}" ]]; then
          echo "既存の研究ディレクトリをクリアします: ${study_1_directory}"
          rm -rf "${study_1_directory}"
      fi
      mkdir -p "${study_1_directory}"

      # ソースディレクトリからstudy_1へファイルをコピー
      if [[ ! -d "${source_directory}" ]]; then
          echo "エラー: ソースディレクトリが見つかりません: ${source_directory}"
          cleanup_lockfile
          continue
      fi
      echo "ファイルをコピー中: ${source_directory} -> ${study_1_directory}"
      cp -r "${source_directory}"/* "${study_1_directory}"/

      # GuideファイルをAIワークスペースにコピー
      if [[ ! -f "${GuideFile}" ]]; then
          echo "エラー: ガイドファイルが見つかりません: ${GuideFile}"
          exit 1
      fi
      cp "${GuideFile}" "${ai_workspace}/"
      echo "Guideファイルをコピー: ${ai_workspace}/$(basename "${GuideFile}")"

      # TemplateファイルをAIワークスペースにコピーしてリネーム
      if [[ ! -f "${TemplateFile}" ]]; then
          echo "エラー: テンプレートファイルが見つかりません: ${TemplateFile}"
          exit 1
      fi
      _result_file_name="${DE_Name}_${_author_year}_by_${AiAgentName}_${_timestamp}.json"
      cp "${TemplateFile}" "${ai_workspace}/${_result_file_name}"
      echo "Templateファイルをコピー: ${ai_workspace}/${_result_file_name}"

      # study_1内の既存の結果JSONを削除
      find "${study_1_directory}" -type f -name "${DE_Name}*.json" -print -delete

      # AIの作業ディレクトリをai_workspace/<timestamp>に変更
      # （Codexのサンドボックスが親ディレクトリへの書き込みを禁止するため、
      #   study_1ではなくtimestampフォルダを作業ディレクトリにする）
      _previous_directory=$(pwd)
      cd "${ai_workspace}" || exit 1
      echo ""
      echo "AIの作業ディレクトリを変更しました: $(pwd)"
      echo ""



      ################
      # Ask AI Agent #
      ################
      _run_agent=$( [[ ${DryRun} == false ]] && echo "true" || echo "false" )

      _log_file_name=${_result_file_name%.json}.log

      # AIの作業ディレクトリは ai_workspace/<timestamp>/ なので
      # - ガイドファイル: ./DE_Guide_v11_a-study.md
      # - 結果ファイル: ./${_result_file_name}
      # - ログファイル: ./${_log_file_name}
      # - 研究ファイル: ./study_1/ 内
      _guide_file_relative="./$(basename "${GuideFile}")"
      _result_file_relative="./${_result_file_name}"
      _log_file_relative="./${_log_file_name}"

      askAiAgent "${AiAgentName}" "${_guide_file_relative}" "${_result_file_relative}" "${_run_agent}" "${_log_file_relative}"



      # 結果処理
      if [[ ${DryRun} == false ]]; then
          # 実行モード: AIが実行された後の結果を処理
          # 結果ファイルは ai_workspace/ に配置されている
          if [ "$(md5sum "${TemplateFile}" | awk '{print $1}')" = "$(md5sum "${ai_workspace}/${_result_file_name}" | awk '{print $1}')" ]; then
              # 書き込まれていなければjsonファイルを削除しフラッグファイルは残す
              echo ""
              echo "エラー: 結果ファイルが空です"
              rm "${ai_workspace}/${_result_file_name}" #結果jsonファイルを削除
              rm "${ai_workspace}/${_log_file_name}" #logファイルを削除

          else
              # ちゃんと書き込まれていたらjsonファイルをコピーしフラッグファイルも消す
              echo ""
              echo "JSONファイルが作成されました"

              if [[ -n "${EnsureJsonStructureBy}" ]]; then
                  echo ""
                  echo "JSON整形の後処理を実行します (agent: ${EnsureJsonStructureBy})"
                  normalize_json_file "${ai_workspace}/${_result_file_name}" "${EnsureJsonStructureBy}"
              fi

              _dst=${this_script_parent}/../data/${_author_year}/${DE_Name}/json
              if [[ ! -d "${_dst}" ]]; then mkdir -p "${_dst}"; fi
              cp "${ai_workspace}/${_result_file_name}" "${_dst}" #結果jsonファイルをコピー

              _dst=${this_script_parent}/../data/${_author_year}/${DE_Name}/log
              if [[ ! -d "${_dst}" ]]; then mkdir -p "${_dst}"; fi
              cp "${ai_workspace}/${_log_file_name}" "${_dst}" #logファイルをコピー

              rm "${this_script_parent}/${DE_Name}_${_author_year}_by_${AiAgentName}_is_not_yet.txt"

              echo "結果ファイル: ${_dst}/${_result_file_name}"
          fi

      else
          # ドライランモード: AIは実行しないが、準備されたファイル構成を確認可能
          echo ""
          echo "【ドライラン】AIエージェントは実行されませんでした"
          echo "AIワークスペース: ${ai_workspace}"
          echo "研究ディレクトリ: ${study_1_directory}"
          echo "結果テンプレート: ${_result_file_name}"
      fi

      # 共通のクリーンアップ処理
      cleanup_lockfile # ロックファイルを削除

      cd "${_previous_directory}" || exit 1
      echo "作業ディレクトリを元に戻しました: $(pwd)"
      echo ""

      if [[ ${DryRun} == false ]]; then
          if [[ -d "${ai_workspace}" ]]; then
              rm -rf "${ai_workspace}"
              echo "AIワークスペースを削除しました: ${ai_workspace}"
          fi
      fi
  
  fi


done

if [[ ${DryRun} == true ]]; then
    echo "※本作業モードで実行するには -r (--run) オプションを付けて実行してください"
    echo ""
fi

echo ""
echo "============================================"
echo "  処理が完了しました"
echo "============================================"
echo ""
