#!/bin/bash

# スクリプト情報の取得
this_script_path="$(readlink -f "${BASH_SOURCE[0]}")"
this_script_name=$(basename "${this_script_path}")
this_script_parent=$(dirname ${this_script_path})

# 複数の研究を横断的に処理するスクリプト
# 各研究の materials/optimized/ を study_1/, study_2/, ... に配置し、
# AIに横断的な参照をさせながら作業を依頼する

#######################
# ログ出力補助関数
#######################
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

AIエージェント（Gemini, Claude, Codex）を使用して、
複数の研究を横断的に参照しながらデータ抽出（DE）を自動実行するスクリプトです。

オプション:
  -a, --agent AGENT      AIエージェント名を指定 (gemini|claude|codex)
  -n, --name NAME        処理名を指定（結果ファイル名に使用、例: DE, QA）
  -s, --studies STUDIES  処理する研究名を複数指定（スペース区切り、引用符で囲む）
                         例: -s "Baldwin2022 Bayer2022 Bedford2025"
  -r, --run              実際に処理を実行（デフォルトはドライラン）
  -d, --dry-run          明示的にドライランに設定（デフォルト）
  -v, --verbose          詳細な出力を表示
  -h, --help             このヘルプメッセージを表示

使用例:
  # 複数の研究を横断的に処理（ドライラン）
  ${this_script_name} -a claude -n DE -s "Baldwin2022 Bayer2022 Bedford2025"

  # 実行モードで処理
  ${this_script_name} -a claude -n DE -s "Baldwin2022 Bayer2022" -r

注意事項:
  - --agent, --name, --studies は必須オプションです
  - 各研究は study_1/, study_2/, ... に配置されます
  - 結果JSONファイルは各研究ごとに作成されます

EOF
    exit 0
}

#######################
# デフォルト値
#######################
AiAgentName=""
DE_Name=""
StudiesString=""
DryRun=true
Verbose=false

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
        -s|--studies)
            StudiesString="$2"
            shift 2
            ;;
        -r|--run|--execute)
            DryRun=false
            shift
            ;;
        -d|--dry-run)
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
    exit 1
fi

if [[ -z "${DE_Name}" ]]; then
    echo "エラー: 処理名が指定されていません（-n または --name を使用）"
    exit 1
fi

if [[ -z "${StudiesString}" ]]; then
    echo "エラー: 研究が指定されていません（-s または --studies を使用）"
    echo "例: -s \"Baldwin2022 Bayer2022 Bedford2025\""
    exit 1
fi

# AIエージェント名の検証
if [[ ! "${AiAgentName}" =~ ^(gemini|claude|codex)$ ]]; then
    echo "エラー: 無効なAIエージェント名: ${AiAgentName}"
    echo "有効な値: gemini, claude, codex"
    exit 1
fi

# 処理名の検証（ファイル名として有効な文字列かチェック）
# 無効な文字: / \ : * ? " < > | 空白 制御文字
if [[ "${DE_Name}" =~ [/\\:\*\?\"\<\>\|[:space:][:cntrl:]] ]] || [[ -z "${DE_Name}" ]]; then
    echo "エラー: 処理名にファイル名として無効な文字が含まれています: ${DE_Name}"
    echo "使用できない文字: / \\ : * ? \" < > | 空白"
    exit 1
fi

# 処理名が . または .. の場合も無効
if [[ "${DE_Name}" == "." ]] || [[ "${DE_Name}" == ".." ]]; then
    echo "エラー: 処理名に '.' または '..' は使用できません"
    exit 1
fi

#######################
# 研究リストを配列に変換
#######################
read -ra StudiesArray <<< "${StudiesString}"
StudyCount=${#StudiesArray[@]}

if [[ ${StudyCount} -lt 1 ]]; then
    echo "エラー: 少なくとも1つの研究を指定してください"
    exit 1
fi

echo ""
echo "============================================"
echo "  複数研究の横断的処理"
echo "============================================"
echo "対象研究数: ${StudyCount}"
for i in "${!StudiesArray[@]}"; do
    echo "  study_$((i+1)): ${StudiesArray[$i]}"
done
echo "============================================"
echo ""

#######################
# 設定ファイルの決定
#######################
GuideFile=${this_script_parent}/../prompts/DE_Guide_v11_studies.md
TemplateFile=${this_script_parent}/../templates/DE_Author20XX_by_Someone_YYYYmmddHHMMSS_for_v11.json

# ファイル存在確認
if [[ ! -f "${GuideFile}" ]]; then
    echo "エラー: ガイドファイルが見つかりません: ${GuideFile}"
    exit 1
fi

if [[ ! -f "${TemplateFile}" ]]; then
    echo "エラー: テンプレートファイルが見つかりません: ${TemplateFile}"
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
    echo "研究数: ${StudyCount}"
    if [[ ${DryRun} == true ]]; then
        echo "モード: ドライラン（実行なし）"
    else
        echo "モード: 実行モード"
    fi
    echo "============================================"
    echo ""
fi

#######################
# ロックファイル削除関数
#######################
function cleanup_lockfile(){
    if [[ -n "${LOCK_FILE:-}" ]] && [[ -f "${LOCK_FILE}" ]]; then
        if grep -q "^PID: $$" "${LOCK_FILE}" 2>/dev/null; then
            rm -f "${LOCK_FILE}"
            if [[ ${Verbose} == true ]]; then
                echo "ロックファイルを削除しました: ${LOCK_FILE}"
            fi
        fi
    fi
}

#######################
# AIエージェント実行関数（複数研究用）
#######################
function askAiAgentForStudies(){

    _ai_agent_name=$1
    _guide_file=$2
    _result_files_info=$3  # "study_1:file1.json study_2:file2.json ..."
    _actual_run=$4
    _log_file_name=$5

    # プロンプトはガイドファイル参照のみ（詳細はMDファイルに集約）
    local _prompt="${_guide_file} を読んで、記載された手順に従ってデータ抽出を行ってください。"

    if [[ ${_ai_agent_name} == "gemini" ]]; then
        techo ""
        techo "========== Gemini コマンド =========="
        techo "gemini \\"
        techo "    \"${_prompt}\" \\"
        techo "    --approval-mode auto_edit \\"
        techo "    --allowed-tools \"run_shell_command\""
        techo "===================================="
        techo ""

        if [[ ${_actual_run} == true ]]; then
            sleep 3
            techo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"
            techo "Gemini が作業を実行中..."
            techo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"

            gemini "${_prompt}" \
                --approval-mode auto_edit \
                --allowed-tools "run_shell_command" \
                2>&1 | tee -a "${_log_file_name}"

            techo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
            techo "Gemini による作業が完了しました"
            techo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
        fi

    elif [[ ${_ai_agent_name} == "claude" ]]; then
        techo ""
        techo "========== Claude コマンド =========="
        techo "claude -p \\"
        techo "    \"${_prompt}\" \\"
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
                "${_prompt}" \
                --allowedTools "Bash,Read" \
                --permission-mode acceptEdits \
                2>&1 | tee -a "${_log_file_name}"

            techo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
            techo "Claude による作業が完了しました"
            techo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
        fi

    elif [[ ${_ai_agent_name} == "codex" ]]; then
        techo ""
        techo "========== Codex コマンド =========="
        techo "codex exec --full-auto --skip-git-repo-check -C . \\"
        techo "    \"${_prompt}\""
        techo "===================================="
        techo ""

        if [[ ${_actual_run} == true ]]; then
            sleep 3
            techo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"
            techo "Codex が作業を実行中..."
            techo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"

            codex exec --full-auto --skip-git-repo-check -C . \
                "${_prompt}" \
                2>&1 | tee -a "${_log_file_name}"

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
# メイン処理開始
#######################
echo ""
echo "============================================"
echo "  ${AiAgentName} を使用した ${DE_Name} 処理を開始"
echo "  （複数研究横断モード）"
echo "============================================"
echo ""

if [[ ${DryRun} == true ]]; then
    echo "【ドライランモード】実際の実行は行いません"
    echo ""
fi

# 作業ディレクトリの設定
# ai_workspace/<timestamp>/ にGuide、Template、ログを配置
# ai_workspace/<timestamp>/study_N/ に各研究のファイルを配置
ai_workspace_base=${this_script_parent}/../ai_workspace
_timestamp=$(date +%Y%m%d%H%M%S)
ai_workspace=${ai_workspace_base}/${_timestamp}

# ロックファイルの設定
LOCK_FILE="${ai_workspace}/.lock_${DE_Name}_processing"

# 作業ディレクトリを作成
if [[ ! -d "${ai_workspace_base}" ]]; then
    mkdir -p "${ai_workspace_base}"
fi
mkdir -p "${ai_workspace}"

# ロックファイルが存在するかチェック
if [[ -f "${LOCK_FILE}" ]]; then
    echo ""
    echo "警告: 他のプロセスが作業中です"
    echo "ロックファイル: ${LOCK_FILE}"
    cat "${LOCK_FILE}"
    echo ""
    exit 1
fi

# ロックファイルを作成
cat > "${LOCK_FILE}" << EOF
AIエージェント: ${AiAgentName}
処理タイプ: ${DE_Name}
研究数: ${StudyCount}
研究リスト: ${StudiesString}
PID: $$
開始時刻: $(date '+%Y-%m-%d %H:%M:%S')
DryRun: ${DryRun}
EOF

trap cleanup_lockfile EXIT INT TERM

if [[ ${Verbose} == true ]]; then
    echo "タイムスタンプフォルダ: ${ai_workspace}"
    echo "ロックファイルを作成しました: ${LOCK_FILE}"
fi

#######################
# Phase 1: 各研究のファイルをコピー（すべて準備してからAIを呼ぶ）
#######################
echo ""
echo "============================================"
echo "  Phase 1: ファイル準備"
echo "============================================"

declare -A ResultFileNames  # 研究名 -> 結果ファイル名のマッピング
declare -A StudyDirs        # 研究名 -> study_Nディレクトリのマッピング

for i in "${!StudiesArray[@]}"; do
    _author_year="${StudiesArray[$i]}"
    _study_num=$((i+1))
    _study_dir="${ai_workspace}/study_${_study_num}"
    _source_dir="${this_script_parent}/../data/${_author_year}/materials/optimized"

    echo "--------------------------------------------"
    echo "study_${_study_num}: ${_author_year}"
    echo "--------------------------------------------"

    # study_N ディレクトリを作成
    mkdir -p "${_study_dir}"

    # ソースディレクトリからファイルをコピー
    if [[ ! -d "${_source_dir}" ]]; then
        echo "警告: ソースディレクトリが見つかりません: ${_source_dir}"
        echo "この研究はスキップされます"
        continue
    fi
    echo "ファイルをコピー中: ${_source_dir} -> ${_study_dir}"
    cp -r "${_source_dir}"/* "${_study_dir}"/

    # study_N内の既存の結果JSONを削除
    find "${_study_dir}" -type f -name "${DE_Name}*.json" -print -delete

    # テンプレートファイルをtimestamp直下にコピーしてリネーム
    _result_file_name="${DE_Name}_${_author_year}_by_${AiAgentName}_${_timestamp}.json"
    cp "${TemplateFile}" "${ai_workspace}/${_result_file_name}"
    ResultFileNames["${_author_year}"]="${_result_file_name}"
    StudyDirs["${_author_year}"]="${_study_dir}"

    echo "結果ファイル: ${ai_workspace}/${_result_file_name}"
    echo ""
done

# ガイドファイルをtimestamp直下にコピー
cp "${GuideFile}" "${ai_workspace}/"
echo "ガイドファイルをコピー: ${ai_workspace}/$(basename "${GuideFile}")"
echo ""

echo "============================================"
echo "  Phase 1 完了: すべてのファイル準備完了"
echo "============================================"
echo ""

#######################
# Phase 2: AIエージェントに依頼
#######################
echo ""
echo "============================================"
echo "  Phase 2: AI実行"
echo "============================================"

_previous_directory=$(pwd)
cd "${ai_workspace}" || exit 1
echo "作業ディレクトリを変更しました: $(pwd)"
echo ""

_run_agent=$( [[ ${DryRun} == false ]] && echo "true" || echo "false" )
_log_file_name="${DE_Name}_studies_by_${AiAgentName}_${_timestamp}.log"
_guide_file_relative="$(basename "${GuideFile}")"

# 結果ファイル情報を作成（AIへの指示用）
_result_files_info=""
for i in "${!StudiesArray[@]}"; do
    _author_year="${StudiesArray[$i]}"
    _study_num=$((i+1))
    if [[ -n "${ResultFileNames[${_author_year}]:-}" ]]; then
        _result_files_info+="study_${_study_num}:${ResultFileNames[${_author_year}]} "
    fi
done

echo "準備されたファイル構成:"
echo "  ガイド: ${_guide_file_relative}"
echo "  ログ: ${_log_file_name}"
for i in "${!StudiesArray[@]}"; do
    _author_year="${StudiesArray[$i]}"
    _study_num=$((i+1))
    if [[ -n "${ResultFileNames[${_author_year}]:-}" ]]; then
        echo "  study_${_study_num}/ (${_author_year}) -> ${ResultFileNames[${_author_year}]}"
    fi
done
echo ""

askAiAgentForStudies "${AiAgentName}" "${_guide_file_relative}" "${_result_files_info}" "${_run_agent}" "${_log_file_name}"

#######################
# Phase 3: 結果を処理
#######################
echo ""
echo "============================================"
echo "  Phase 3: 結果処理"
echo "============================================"

if [[ ${DryRun} == false ]]; then

    for i in "${!StudiesArray[@]}"; do
        _author_year="${StudiesArray[$i]}"
        _study_num=$((i+1))
        _result_file_name="${ResultFileNames[${_author_year}]:-}"

        if [[ -z "${_result_file_name}" ]]; then
            echo "study_${_study_num} (${_author_year}): スキップされました"
            continue
        fi

        echo "--------------------------------------------"
        echo "study_${_study_num}: ${_author_year}"
        echo "--------------------------------------------"

        # 結果ファイルはtimestamp直下にある
        if [[ ! -f "${ai_workspace}/${_result_file_name}" ]]; then
            echo "結果ファイルが見つかりません: ${ai_workspace}/${_result_file_name}"
            continue
        fi

        # ファイルが空かどうかチェック
        if [ "$(md5sum "${TemplateFile}" | awk '{print $1}')" = "$(md5sum "${ai_workspace}/${_result_file_name}" | awk '{print $1}')" ]; then
            echo "警告: 結果ファイルが空です（テンプレートのまま）"
        else
            echo "結果ファイルが作成されました"

            # 結果ファイルをコピー
            _dst=${this_script_parent}/../data/${_author_year}/${DE_Name}/json
            if [[ ! -d "${_dst}" ]]; then mkdir -p "${_dst}"; fi
            cp "${ai_workspace}/${_result_file_name}" "${_dst}"
            echo "コピー先: ${_dst}/${_result_file_name}"
        fi
        echo ""
    done

    # ログファイルを各研究のDE/logにコピー
    if [[ -f "${ai_workspace}/${_log_file_name}" ]]; then
        for i in "${!StudiesArray[@]}"; do
            _author_year="${StudiesArray[$i]}"
            _log_dst=${this_script_parent}/../data/${_author_year}/${DE_Name}/log
            if [[ ! -d "${_log_dst}" ]]; then mkdir -p "${_log_dst}"; fi
            cp "${ai_workspace}/${_log_file_name}" "${_log_dst}"
            echo "ログファイルをコピーしました: ${_log_dst}/${_log_file_name}"
        done
    fi

else
    echo ""
    echo "【ドライラン】AIエージェントは実行されませんでした"
    echo "タイムスタンプフォルダ: ${ai_workspace}"
    echo "結果ファイル・ログファイルはコピーされません"
fi

#######################
# 後処理
#######################
cleanup_lockfile

cd "${_previous_directory}" || exit 1
echo ""
echo "作業ディレクトリを元に戻しました: $(pwd)"
echo ""

if [[ ${DryRun} == true ]]; then
    echo "※本作業モードで実行するには -r (--run) オプションを付けて実行してください"
    echo ""
fi

echo "============================================"
echo "  処理が完了しました"
echo "============================================"
echo "タイムスタンプフォルダ: ${ai_workspace}"
echo ""
