#!/bin/bash

# スクリプト情報の取得
this_script_path="$(readlink -f "${BASH_SOURCE[0]}")"
this_script_name=$(basename "${this_script_path}")
this_script_parent=$(dirname ${this_script_path})

# AIに不必要にファイルを参照させないようにするために、
# カレントディレクトリを明確に移動させてから作業の依頼を出す。
# 作業に必要なファイルは上の階層から一時的にコピーして持ってくる。
# 書き込み状況を確認してからファイルを消す。

#######################
# ヘルプ表示関数
#######################
show_help() {
    cat << EOF
使用方法: ${this_script_name} [OPTIONS]

AIエージェント（Gemini, Claude, Codex）を使用して、
JSONファイルの構造とキーを正規化するスクリプトです。

オプション:
  -f, --file FILE        正規化対象のJSONファイルを指定（必須）
  -a, --agent AGENT      AIエージェント名を指定 (gemini|claude|codex)
                         デフォルト: codex
  -r, --run              実際に処理を実行（デフォルトはドライラン）
  -n, --dry-run          明示的にドライランに設定（デフォルト）
  -v, --verbose          詳細な出力を表示
  -h, --help             このヘルプメッセージを表示

使用例:
  # Codexを使用してJSONファイルを正規化
  ${this_script_name} --file ../Bhome2024/DE/json/DE_Bhome2024_by_gemini_*.json

  # Geminiを使用して正規化（詳細出力あり）
  ${this_script_name} -f QA_Bedford2025_by_claude.json -a gemini -v

  # ドライラン（実行内容の確認のみ・デフォルト）
  ${this_script_name} -f some_file.json

  # 実行モードで処理を走らせる
  ${this_script_name} -f some_file.json -r

注意事項:
  - --file は必須オプションです
  - 処理前にバックアップファイルが自動的に作成されます
  - JSONファイル名は "DE_" または "QA_" で始まる必要があります
  - 既定はドライランです（実コマンドは実行されません）
  - 実行モードにするには -r または --run を指定してください

EOF
    exit 0
}

#######################
# デフォルト値
#######################
TargetJsonFile=""
AiAgentName="codex"
DryRun=true
Verbose=false

#######################
# オプション解析
#######################
while [[ $# -gt 0 ]]; do
    case $1 in
        -f|--file)
            TargetJsonFile="$2"
            shift 2
            ;;
        -a|--agent)
            AiAgentName="$2"
            shift 2
            ;;
        -r|--run|--execute)
            DryRun=false
            shift
            ;;
        -n|--dry-run)
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
if [[ -z "${TargetJsonFile}" ]]; then
    echo "エラー: 対象JSONファイルが指定されていません（-f または --file を使用）"
    echo "ヘルプを表示するには -h または --help を使用してください"
    exit 1
fi

# AIエージェント名の検証
if [[ ! "${AiAgentName}" =~ ^(gemini|claude|codex)$ ]]; then
    echo "エラー: 無効なAIエージェント名: ${AiAgentName}"
    echo "有効な値: gemini, claude, codex"
    exit 1
fi

# ファイル存在確認（ドライラン以外の場合）
if [[ ${DryRun} == false && ! -f "${TargetJsonFile}" ]]; then
    echo "エラー: 指定されたファイルが見つかりません: ${TargetJsonFile}"
    exit 1
fi

# ファイル名の検証（DEまたはQAで始まる）
_target_file_basename=$(basename "${TargetJsonFile}")
_prefix="${_target_file_basename:0:2}"
if [[ ! "${_prefix}" =~ ^(DE|QA)$ ]]; then
    echo "エラー: JSONファイル名は 'DE_' または 'QA_' で始まる必要があります"
    echo "指定されたファイル: ${_target_file_basename}"
    exit 1
fi

#######################
# テンプレートファイルの設定
#######################

#
# QA用テンプレートファイル
# this_script_parent は tools/SubFuncs_for_03 なので、../../templates でプロジェクトルートのテンプレートを参照
#
#TemplateFileForQA=${this_script_parent}/../../templates/QA_Author20XX_by_Someone_YYYYmmddHHMMSS_for_v7.json

TemplateFileForQA=${this_script_parent}/../../templates/Author20XX_by_Someone_YYYYmmddHHMMSS_for_QA_v9.json

#
# DE用テンプレートファイル
#
#TemplateFileForDE=${this_script_parent}/../../templates/DE_Author20XX_by_Someone_YYYYmmddHHMMSS_for_v9.json
#TemplateFileForDE=${this_script_parent}/../../templates/DE_Author20XX_by_Someone_YYYYmmddHHMMSS_for_v10.json
#TemplateFileForDE=${this_script_parent}/../../templates/DE_Author20XX_by_Someone_YYYYmmddHHMMSS_for_v10_1.json
TemplateFileForDE=${this_script_parent}/../../templates/DE_v12_by_Someone_Author20XX_YYYYmmddHHMMSS.json


# テンプレートファイル存在確認
if [[ ! -f ${TemplateFileForDE} ]]; then
    echo "エラー: DEテンプレートファイルが見つかりません: ${TemplateFileForDE}"
    exit 1
fi

if [[ ! -f ${TemplateFileForQA} ]]; then
    echo "エラー: QAテンプレートファイルが見つかりません: ${TemplateFileForQA}"
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
    echo "対象JSONファイル: ${TargetJsonFile}"
    echo "AIエージェント: ${AiAgentName}"
    echo "DEテンプレート: ${TemplateFileForDE}"
    echo "QAテンプレート: ${TemplateFileForQA}"
    if [[ ${DryRun} == true ]]; then
        echo "モード: ドライラン（実行なし）"
    else
        echo "モード: 実行モード"
    fi
    echo "============================================"
    echo ""
fi


#######################
# テンプレートコピー関数
#######################
function copyTemplate(){

    _target_json_file=$1

    # テンプレートファイルを指定
    _target_json_file_name=$(basename "${_target_json_file}")
    _prefix="${_target_json_file_name:0:2}"
    if [[ "${_prefix}" == "DE" ]]; then
        _template_json_file=${TemplateFileForDE}
    elif [[ "${_prefix}" == "QA" ]]; then
        _template_json_file=${TemplateFileForQA}
    else
        echo "エラー: ファイル名が 'DE' または 'QA' で始まっていません"
        exit 1
    fi

    if [[ ${DryRun} == false ]]; then
        if [[ ${Verbose} == true ]]; then
            echo "テンプレートをコピー: $(basename ${_template_json_file}) -> $(dirname ${_target_json_file})"
        fi
        cp ${_template_json_file} $(dirname ${_target_json_file})
    else
        echo "[ドライラン] テンプレートをコピー: $(basename ${_template_json_file}) -> $(dirname ${_target_json_file})"
    fi

}

#######################
# JSONファイルバックアップ関数
#######################
function backupJsonFile(){

    _target_json_file=$1

    _target_json_file_name=$(basename ${_target_json_file%.json})
    _target_json_file_ext="${_target_json_file##*.}"
    _backup_file="$(dirname ${_target_json_file})/${_target_json_file_name}_backup.${_target_json_file_ext}"

    if [[ ${DryRun} == false ]]; then
        if [[ ${Verbose} == true ]]; then
            echo "バックアップを作成: ${_target_json_file_name}.${_target_json_file_ext} -> ${_target_json_file_name}_backup.${_target_json_file_ext}"
        fi
        cp ${_target_json_file} ${_backup_file}
    else
        echo "[ドライラン] バックアップを作成: ${_target_json_file_name}_backup.${_target_json_file_ext}"
    fi

}


#######################
# AIエージェント実行関数
#######################
function askAiAgent(){

    _target_json_file=$1
    _actual_run=$2


    _working_directory=$(dirname ${_target_json_file})

    # ディレクトリ変更（ドライラン以外の場合）
    _previous_directory=$(pwd)
    if [[ ${_actual_run} == true ]]; then
        cd ${_working_directory}
        if [[ ${Verbose} == true ]]; then
            echo "作業ディレクトリを変更: $(pwd)"
            echo ""
        fi
    fi


    # テンプレートファイルを指定
    _target_json_file_name=$(basename "${_target_json_file}")
    _prefix="${_target_json_file_name:0:2}"
    if [[ "${_prefix}" == "DE" ]]; then
        _template_json_file=${TemplateFileForDE}
    elif [[ "${_prefix}" == "QA" ]]; then
        _template_json_file=${TemplateFileForQA}
    else
        echo "エラー: ファイル名が 'DE' または 'QA' で始まっていません"
        exit 1
    fi

    _template_json_file_name=$(basename ${_template_json_file})

    # AIエージェントへのプロンプト
    _prompt="${_target_json_file_name} の構造、キーについて問題があれば修正をお願いします。このJSONファイルは ${_template_json_file_name} と 同じ構造、同じキーを持つことが期待されていますが、これら要件を満たしていない可能性があります。もし ${_target_json_file_name} の構造やキーが ${_template_json_file_name} と不一致であれば修正してください。"


    if [[ ${AiAgentName} == "gemini" ]]; then
        echo ""
        echo "========== Gemini コマンド =========="
        echo "gemini \\"
        echo "    \"${_prompt}\" \\"
        echo "    --approval-mode auto_edit \\"
        echo "    --allowed-tools \"ShellTool(git status,rm,mv,mkdir)\""
        echo "===================================="
        echo ""

        if [[ ${_actual_run} == true ]]; then
            sleep 3
            echo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"
            echo "Gemini を実行中..."
            echo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"

            gemini "${_prompt}" \
                --approval-mode auto_edit \
                --allowed-tools "ShellTool(git status,rm,mv,mkdir)"

            echo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
            echo "Gemini の実行が完了しました"
            echo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
        fi

    elif [[ ${AiAgentName} == "claude" ]]; then
        echo ""
        echo "========== Claude コマンド =========="
        echo "claude -p \\"
        echo "    \"${_prompt}\" \\"
        echo "    --allowedTools \"Bash,Read\" \\"
        echo "    --permission-mode acceptEdits"
        echo "===================================="
        echo ""

        if [[ ${_actual_run} == true ]]; then
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
        fi

    elif [[ ${AiAgentName} == "codex" ]]; then
        echo ""
        echo "========== Codex コマンド =========="
        echo "codex exec --full-auto --skip-git-repo-check -C . \\"
        echo "    \"${_prompt}\""
        echo "===================================="
        echo ""

        if [[ ${_actual_run} == true ]]; then
            sleep 3
            echo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"
            echo "Codex を実行中..."
            echo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"

            codex exec --full-auto --skip-git-repo-check -C . \
                "${_prompt}"

            echo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
            echo "Codex の実行が完了しました"
            echo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
        fi

    else
        echo "エラー: 不明なAIエージェント名が指定されました: ${AiAgentName}"
        exit 1
    fi


    # ディレクトリを元に戻す
    if [[ ${_actual_run} == true ]]; then
        cd ${_previous_directory}
        if [[ ${Verbose} == true ]]; then
            echo "作業ディレクトリを元に戻しました: $(pwd)"
            echo ""
        fi
    fi

}


#######################
# コピーしたテンプレート削除関数
#######################
function removeCopiedTemplate(){

    _target_json_file=$1

    # テンプレートファイルを指定
    _target_json_file_name=$(basename "${_target_json_file}")
    _prefix="${_target_json_file_name:0:2}"
    if [[ "${_prefix}" == "DE" ]]; then
        _template_json_file=${TemplateFileForDE}
    elif [[ "${_prefix}" == "QA" ]]; then
        _template_json_file=${TemplateFileForQA}
    else
        echo "エラー: ファイル名が 'DE' または 'QA' で始まっていません"
        exit 1
    fi

    _copied_template="$(dirname ${_target_json_file})/$(basename ${_template_json_file})"

    if [[ ${DryRun} == false ]]; then
        if [[ ${Verbose} == true ]]; then
            echo "コピーしたテンプレートを削除: $(basename ${_template_json_file})"
        fi
        rm "${_copied_template}"
    else
        echo "[ドライラン] コピーしたテンプレートを削除: $(basename ${_template_json_file})"
    fi

}


#######################
# メイン処理関数
#######################
function core(){

    _target_json_file=$1
    _actual_run=$2

    echo ""
    echo "============================================"
    echo "  ${AiAgentName} を使用したJSON正規化処理"
    echo "============================================"
    echo ""

    if [[ ${DryRun} == true ]]; then
        echo "【ドライランモード】実際の実行は行いません"
        echo ""
    fi

    echo "対象ファイル: $(basename ${_target_json_file})"
    echo ""

    copyTemplate ${_target_json_file}

    backupJsonFile ${_target_json_file}

    askAiAgent ${_target_json_file} ${_actual_run}

    removeCopiedTemplate ${_target_json_file}

    echo ""
    echo "============================================"
    echo "  処理が完了しました"
    echo "============================================"
    echo ""

}

#######################
# メイン処理
#######################
_run_agent=$( [[ ${DryRun} == false ]] && echo "true" || echo "false" )
core "${TargetJsonFile}" ${_run_agent}
