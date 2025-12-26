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

AIエージェント（Gemini, Claude, Codex）を使用して、
データ抽出（DE）または品質評価（QA）を自動実行するスクリプトです。

オプション:
  -a, --agent AGENT      AIエージェント名を指定 (gemini|claude|codex)
  -n, --name NAME        処理名を指定（結果ファイル名に使用、例: DE, QA）
  -l, --list FILE        処理すべき研究名が1行ずつ記載されたファイルのパス
  -s, --study STUDY      単一の研究のみ処理 (互換目的)
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
QA_Name=""
ListFilePath=""
SingleStudy=""  # backward-compat for -s/--study (deprecated)
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
            QA_Name="$2"
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

if [[ -z "${QA_Name}" ]]; then
    echo "エラー: 処理名が指定されていません（-n または --name を使用）"
    echo "ヘルプを表示するには -h または --help を使用してください"
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
if [[ "${QA_Name}" =~ [/\\:\*\?\"\<\>\|[:space:][:cntrl:]] ]] || [[ -z "${QA_Name}" ]]; then
    echo "エラー: 処理名にファイル名として無効な文字が含まれています: ${QA_Name}"
    echo "使用できない文字: / \\ : * ? \" < > | 空白"
    exit 1
fi

# 処理名が . または .. の場合も無効
if [[ "${QA_Name}" == "." ]] || [[ "${QA_Name}" == ".." ]]; then
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
# QA系ドキュメント
#
#GuideFile=${this_script_parent}/../prompts/QA_Guide_v6_1.md
#GuideFile=${this_script_parent}/../prompts/QA_Guide_v7_2.md
#GuideFile=${this_script_parent}/../prompts/QA_Guide_v8.md
GuideFile=${this_script_parent}/../prompts/QA_Guide_v9.md

#TemplateFile=${this_script_parent}/../templates/QA_Author20XX_by_Someone_YYYYmmddHHMMSS.json
#TemplateFile=${this_script_parent}/../templates/QA_Author20XX_by_Someone_YYYYmmddHHMMSS_for_v7.json
#TemplateFile=${this_script_parent}/../templates/QA_Author20XX_by_Someone_YYYYmmddHHMMSS_for_v8.json
TemplateFile=${this_script_parent}/../templates/Author20XX_by_Someone_YYYYmmddHHMMSS_for_QA_v9.json


# ファイル存在確認
if [[ ! -f ${GuideFile} ]]; then
    echo "エラー: ガイドファイルが見つかりません: ${GuideFile}"
    exit 1
fi

if [[ ! -f ${TemplateFile} ]]; then
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
    echo "処理タイプ: ${QA_Name}"
    echo "ガイドファイル: ${GuideFile}"
    echo "テンプレートファイル: ${TemplateFile}"
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
    #AuthorYearArray+=("Baldwin2022")
    #AuthorYearArray+=("Bayer2022")
    #AuthorYearArray+=("Bedford2025")
    #AuthorYearArray+=("Berthet2025")
    #AuthorYearArray+=("Bethlehem2020")
    #AuthorYearArray+=("Bethlehem2021")
    #AuthorYearArray+=("Bhome2024")
    
    #AuthorYearArray+=("CardenasDeLaParra2019") 
    #AuthorYearArray+=("Chan2025A")
    #AuthorYearArray+=("Chan2025B")
    #AuthorYearArray+=("Chien2022")
    #AuthorYearArray+=("Cirstian2024")
    #AuthorYearArray+=("Coupe2022")
    #AuthorYearArray+=("DeMeo2019")
    #AuthorYearArray+=("DiBiase2022")
    #AuthorYearArray+=("Ebadi2024")
    #AuthorYearArray+=("Echave2024")
    #AuthorYearArray+=("Elad2021")
    #AuthorYearArray+=("Fang2024")
    #AuthorYearArray+=("Fang2025")
    #AuthorYearArray+=("Feng2024")
    #AuthorYearArray+=("Feng2025")
    #AuthorYearArray+=("Floris2021")
    #AuthorYearArray+=("Floris2024")
    #AuthorYearArray+=("Fraza2023")
    #AuthorYearArray+=("FukamiGartner2023")
    #AuthorYearArray+=("GarciaSanMartin2025")
    #AuthorYearArray+=("Ge2024")
    #AuthorYearArray+=("Geng2025")
    #AuthorYearArray+=("Georgiadis2024")
    #AuthorYearArray+=("Giacomel2025")
    #AuthorYearArray+=("Gimbel2025")
    #AuthorYearArray+=("Gordaliza2024")
    #AuthorYearArray+=("Haas2024")
    #AuthorYearArray+=("Han2023")
    #AuthorYearArray+=("Han2024A")
    #AuthorYearArray+=("Han2024B")
    #AuthorYearArray+=("Haukvik2025")
    #AuthorYearArray+=("Holz2023")
    #AuthorYearArray+=("Hua2025")
    #AuthorYearArray+=("Huang2024")
    #AuthorYearArray+=("Huo2024")
    #AuthorYearArray+=("Ilioska2024")

    #AuthorYearArray+=("DeMeo2019")
    #AuthorYearArray+=("Feng2024")
    #AuthorYearArray+=("Italinna2023")
    #AuthorYearArray+=("Jalbrzikowski2019")
    #AuthorYearArray+=("Janahi2022")
    #AuthorYearArray+=("Janssen2021")
    #AuthorYearArray+=("Janssen2024")
    #AuthorYearArray+=("Ji2023")
    #AuthorYearArray+=("Jia2024")
    #AuthorYearArray+=("Jia2025")
    #AuthorYearArray+=("Jiang2024")
    #AuthorYearArray+=("Jing2023")
    #AuthorYearArray+=("Joo2024")
    #AuthorYearArray+=("Kasper2024")
    #AuthorYearArray+=("Kia2022")
    #AuthorYearArray+=("Kim2023")
    #AuthorYearArray+=("Kim2024")
    #AuthorYearArray+=("Kobbersmed2025")
    #AuthorYearArray+=("Kumar2024")
    #AuthorYearArray+=("Kumar2025")

    #AuthorYearArray+=("Laidi2022")
    #AuthorYearArray+=("Lamsma2024")
    #AuthorYearArray+=("Lawn2024")
    #AuthorYearArray+=("Lee2025")
    #AuthorYearArray+=("Leenings2024")
    #AuthorYearArray+=("Leiberg2023")
    #AuthorYearArray+=("Lin2023")
    #AuthorYearArray+=("Lin2024")
    #AuthorYearArray+=("Little2024")
    #AuthorYearArray+=("Little2025")
    #AuthorYearArray+=("Liu2024")
    #AuthorYearArray+=("Looden2022")
    #AuthorYearArray+=("Loreto2024")
    #AuthorYearArray+=("Lv2021")
    #AuthorYearArray+=("Ma2024")
    #AuthorYearArray+=("Mansour2025")
    #AuthorYearArray+=("Mao2025")
    #AuthorYearArray+=("Martin2025")
    #AuthorYearArray+=("Meijer2024")
    #AuthorYearArray+=("Mendes2024")
    #AuthorYearArray+=("Narai2024")
    #AuthorYearArray+=("OliveiraSaraiva2023")
    #AuthorYearArray+=("Parkes2021")
    #AuthorYearArray+=("Pinaya2019")
    #AuthorYearArray+=("Pinaya2021")
    #AuthorYearArray+=("RehakBuckova2025")
    #AuthorYearArray+=("Remiszewski2022")
    #AuthorYearArray+=("Romascano2024")
    #AuthorYearArray+=("Rutherford2022")
    #AuthorYearArray+=("Rutherford2023")
    #AuthorYearArray+=("Sampaio2025")
    #AuthorYearArray+=("Savage2024")
    #AuthorYearArray+=("Segal2023")
    #AuthorYearArray+=("Segal2025")
    #AuthorYearArray+=("Shan2022")
    #AuthorYearArray+=("Shao2024")
    #AuthorYearArray+=("Sun2023")
    #AuthorYearArray+=("Sun2025")
    #AuthorYearArray+=("Tabbal2025")
    #AuthorYearArray+=("Thukral2024")
    #AuthorYearArray+=("Tong2024")
    #AuthorYearArray+=("Verdi2023")
    #AuthorYearArray+=("Verdi2024")
    #AuthorYearArray+=("Vieira2025")
    #AuthorYearArray+=("VillalonReina2024")
    #AuthorYearArray+=("Wang2023")
    #AuthorYearArray+=("Wen2025")
    #AuthorYearArray+=("Wolfers2018")
    #AuthorYearArray+=("Wolfers2020")
    #AuthorYearArray+=("Wolfers2021")
    #AuthorYearArray+=("Worker2023")
    #AuthorYearArray+=("Wu2023")
    #AuthorYearArray+=("Wu2024")
    #AuthorYearArray+=("Xiao2024")
    #AuthorYearArray+=("Yang2025")
    #AuthorYearArray+=("Young2024")
    #AuthorYearArray+=("Yu2024")
    #AuthorYearArray+=("Zabihi2019")
    #AuthorYearArray+=("Zabihi2020")
    #AuthorYearArray+=("Zhang2022")
    #AuthorYearArray+=("Zhang2023")
    #AuthorYearArray+=("Zheng2024")
fi

# Note: 
#
# notes/DE_JsonFillRate.tsv を参照して、claude かつ rate が 0.90 未満のものについて tools/ask_AiToDeQa_for_v8.sh 内の line 198-325 の範囲でコメントを無効化し処理対象となるようにしてください。
#
# notes/DE_JsonFillRate.tsv を参照して、gemini かつ rate が 0.90 未満のものについて <AuthorYear>/DE/json下にある gemini 関連ファイルを削除するスクリプトを ./tools/ の下に作ってください。
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


    if [[ ${_ai_agent_name} == "gemini" ]]; then
        techo ""
        techo "========== Gemini コマンド =========="
        techo "gemini \\"
        techo "    \"$(basename ${_guide_file}) に従って作業をしてください。作業結果は ${_result_file_name} へ書き込んでください。\" \\"
        techo "    --approval-mode auto_edit \\"
        techo "    --allowed-tools \"ShellTool(git status,rm,mv,mkdir,cat)\""
        techo "===================================="
        techo ""

        if [[ ${_actual_run} == true ]]; then
            sleep 3
            techo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"
            techo "Gemini が作業を実行中..."
            techo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"

            gemini "$(basename ${_guide_file}) に従って作業をしてください。作業結果は ${_result_file_name} へ書き込んでください。" \
                --approval-mode auto_edit \
                --allowed-tools "ShellTool(git status,rm,mv,mkdir,cat)" \
                2>&1 | tee -a ${_log_file_name}

            techo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
            techo "Gemini による作業が完了しました"
            techo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"

        fi
    elif [[ ${_ai_agent_name} == "claude" ]]; then
        techo ""
        techo "========== Claude コマンド =========="
        techo "claude -p \\"
        techo "    \"$(basename ${_guide_file}) に従って作業をしてください。作業結果は ${_result_file_name} へ書き込んでください。\" \\"
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
                "$(basename ${_guide_file}) に従って作業をしてください。作業結果は ${_result_file_name} へ書き込んでください。" \
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
        techo "    \"$(basename ${_guide_file}) に従って作業をしてください。作業結果は ${_result_file_name} へ書き込んでください。\""
        techo "===================================="
        techo ""

        if [[ ${_actual_run} == true ]]; then
            sleep 3
            techo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"
            techo "Codex が作業を実行中..."
            techo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"

            codex exec --full-auto --skip-git-repo-check -C . \
                "$(basename ${_guide_file}) に従って作業をしてください。作業結果は ${_result_file_name} へ書き込んでください。" \
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
# メイン処理開始
#######################
echo ""
echo "============================================"
echo "  ${AiAgentName} を使用した ${QA_Name} 処理を開始"
echo "============================================"
echo ""

if [[ ${DryRun} == true ]]; then
    echo "【ドライランモード】実際の実行は行いません"
    echo ""
fi

for _author_year in ${AuthorYearArray[@]}; do

  echo ""
  echo "============================================"
  echo "  対象研究: ${_author_year}"
  echo "============================================"
  
  # Ask AI agent if the result json was not found.
  _folder="${this_script_parent}/../data/${_author_year}/${QA_Name}/json/"
  if [[ ! -d ${_folder} ]]; then mkdir -p ${_folder}; fi
  _expected_result_file="${_author_year}_by_${AiAgentName}_*_${QA_Name}.json"

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
      if [[ ${DryRun} == false ]]; then
          echo "The result json file is not created yet." \
              > ${this_script_parent}/${QA_Name}_${_author_year}_by_${AiAgentName}_is_not_yet.txt
      fi

      # Set Working Directory
      working_directory=${this_script_parent}/../data/${_author_year}/materials/optimized

      if [[ ${Verbose} == true ]]; then
          echo "作業ディレクトリ: ${working_directory}"
      fi

      # ロックファイルのチェックと作成
      LOCK_FILE="${working_directory}/.lock_${QA_Name}_processing"

      # ドライランでない場合のみロックファイルをチェック
      if [[ ${DryRun} == false ]]; then
          # 作業ディレクトリが存在しない場合は作成
          if [[ ! -d "${working_directory}" ]]; then
              mkdir -p "${working_directory}"
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
処理タイプ: ${QA_Name}
研究: ${_author_year}
PID: $$
開始時刻: $(date '+%Y-%m-%d %H:%M:%S')
EOF

          if [[ ${Verbose} == true ]]; then
              echo "ロックファイルを作成しました: ${LOCK_FILE}"
          fi

          # 異常終了時にロックファイルを削除するためのtrap設定
          trap cleanup_lockfile EXIT INT TERM
      fi

      # ドライランでない場合のみファイル操作を実行
      if [[ ${DryRun} == false ]]; then
          # Copy the guide file
          if [[ ! -f ${GuideFile} ]]; then
              echo "エラー: ガイドファイルが見つかりません: ${GuideFile}"
              exit 1
          fi
          cp ${GuideFile} ${working_directory}

          # Remove exist result jsons
          find "$working_directory" -type f -name "*_for_${QA_Name}.json" -print -delete

          # Prepare an empty result file
          if [[ ! -f ${TemplateFile} ]]; then
              echo "エラー: テンプレートファイルが見つかりません: ${TemplateFile}"
              exit 1
          fi
          cp ${TemplateFile} ${working_directory}
          _result_file_name="${_author_year}_by_${AiAgentName}_$(date +%Y%m%d%H%M%S)_for_${QA_Name}.json"
          mv ${working_directory}/$(basename ${TemplateFile}) \
             ${working_directory}/${_result_file_name}

        else
          _result_file_name="${_author_year}_by_${AiAgentName}_$(date +%Y%m%d%H%M%S)_for_${QA_Name}.json"
      fi
      
      # Change the current directory
      _previous_directory=$(pwd)
      cd ${working_directory}
      echo "作業ディレクトリを変更しました: $(pwd)"
      echo ""



      ################
      # Ask AI Agent #
      ################
      _run_agent=$( [[ ${DryRun} == false ]] && echo "true" || echo "false" )
      
      _log_file_name=${_result_file_name%.json}.log
      
      askAiAgent ${AiAgentName} ${GuideFile} ${_result_file_name} ${_run_agent} ${_log_file_name}
      


      # ドライランでない場合のみ結果を処理
      if [[ ${DryRun} == false ]]; then
          if [ "$(md5sum ${TemplateFile} | awk '{print $1}')" = "$(md5sum ${working_directory}/${_result_file_name} | awk '{print $1}')" ]; then
              # 書き込まれていなければjsonファイルを削除しフラッグファイルは残す
              echo ""
              echo "エラー: 結果ファイルが空です"
              rm ${working_directory}/${_result_file_name} #結果jsonファイルを削除
              rm ${working_directory}/${_log_file_name} #logファイルを削除
              
          else
              # ちゃんと書き込まれていたらjsonファイルを移動させフラッグファイルも消す
              echo ""
              echo "JSONファイルが作成されました"

              _dst=${this_script_parent}/../data/${_author_year}/${QA_Name}/json
              if [[ ! -d ${_dst} ]]; then mkdir -p ${_dst}; fi
              mv ${working_directory}/${_result_file_name} ${_dst} #結果jsonファイルを移動

              _dst=${this_script_parent}/../data/${_author_year}/${QA_Name}/log
              if [[ ! -d ${_dst} ]]; then mkdir -p ${_dst}; fi              
              mv ${working_directory}/${_log_file_name} ${_dst} #logファイルを移動

              rm ${this_script_parent}/${_author_year}_by_${AiAgentName}_for_${QA_Name}_is_not_yet.txt

              echo "結果ファイル: ${_dst}/${_result_file_name}"
          fi


          rm "${working_directory}/$(basename ${GuideFile})" # Remove Guide File
          cleanup_lockfile # ロックファイルを削除

      else
          _dst=${this_script_parent}/../data/${_author_year}/${QA_Name}/log
          if [[ ! -d ${_dst} ]]; then mkdir -p ${_dst}; fi              
          mv ${working_directory}/${_log_file_name} ${_dst} #logファイルを移動
      fi
  
      cd ${_previous_directory}
      echo "作業ディレクトリを元に戻しました: $(pwd)"
      echo ""
  
  fi


done

echo ""
echo "============================================"
echo "  処理が完了しました"
echo "============================================"
echo ""

if [[ ${DryRun} == true ]]; then
    echo "【ヒント】実際に処理を実行するには --run (-r) オプションを追加してください"
    echo "例: ${this_script_name} -a ${AiAgentName} -n ${QA_Name} -l <リストファイル> -r"
    echo ""
fi
