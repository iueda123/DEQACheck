#!/bin/bash

this_script_path="$(readlink -f "${BASH_SOURCE[0]}")"
echo "\${this_script_path}: ${this_script_path}"
this_script_name=$(basename "${this_script_path}")
this_script_parent=$(dirname ${this_script_path})

#AuthorYearArray=()

# 上田担当分
#AuthorYearArray+=("Baldwin2022")
#AuthorYearArray+=("Bayer2022")
#AuthorYearArray+=("Bedford2025")
#AuthorYearArray+=("Berthet2025")
#AuthorYearArray+=("Bethlehem2020" "Bethlehem2021")
#AuthorYearArray+=("Bhome2024")
#AuthorYearArray+=("CardenasDeLaParra2019" "Chan2025A" "Chan2025B" "Chien2022")
#AuthorYearArray+=("Cirstian2024")
#AuthorYearArray+=("Coupe2022")
#AuthorYearArray+=("DeMeo2019")
#AuthorYearArray+=("DiBiase2022" "Ebadi2024")
#AuthorYearArray+=("Echave2024" "Elad2021" "Fang2024" "Fang2025")
#AuthorYearArray+=("Feng2024")
#AuthorYearArray+=("Feng2025" "Floris2021" "Floris2024" "Fraza2023" "FukamiGartner2023")
#AuthorYearArray+=("GarciaSanMartin2025" "Ge2024" "Geng2025")
#AuthorYearArray+=("Georgiadis2024" "Giacomel2025")
#AuthorYearArray+=("Gimbel2025" "Gordaliza2024" "Haas2024" "Han2023" "Han2024A")
#AuthorYearArray+=("Han2024B" "Haukvik2025")
#AuthorYearArray+=("Holz2023")
#AuthorYearArray+=("Hua2025" "Huang2024")
#AuthorYearArray+=("Huo2024" "Ilioska2024")


#AuthorYearArray+=("DeMeo2019")
#AuthorYearArray+=("Feng2024")
#AuthorYearArray+=("Italinna2023")
#AuthorYearArray+=("Jalbrzikowski2019")
#AuthorYearArray+=("Janahi2022")
#AuthorYearArray+=("Janssen2021" "Janssen2024" "Ji2023")
#AuthorYearArray+=("Jia2024" "Jia2025")
#AuthorYearArray+=("Jiang2024")
#AuthorYearArray+=("Jing2023")
#AuthorYearArray+=("Joo2024" "Kasper2024" "Kia2022")
#AuthorYearArray+=("Kim2023" "Kim2024" "Kobbersmed2025")
#AuthorYearArray+=("Kumar2024" "Kumar2025")


# 斎藤先生にお願いしたい分
#AuthorYearArray+=("Laidi2022" "Lamsma2024" "Lawn2024" "Lee2025" "Leenings2024")
#AuthorYearArray+=("Leiberg2023" "Lin2023" "Lin2024")
#AuthorYearArray+=("Little2024" "Little2025")
#AuthorYearArray+=("Liu2024")
#AuthorYearArray+=("Looden2022" "Loreto2024" "Lv2021" "Ma2024")
#AuthorYearArray+=("Mansour2025" "Mao2025" "Martin2025" "Meijer2024" "Mendes2024")
#AuthorYearArray+=("Narai2024")
#AuthorYearArray+=("OliveiraSaraiva2023")
#AuthorYearArray+=("Parkes2021" "Pinaya2019" "Pinaya2021")
#AuthorYearArray+=("RehakBuckova2025")
#AuthorYearArray+=("Remiszewski2022" "Romascano2024" "Rutherford2022" "Rutherford2023")
#AuthorYearArray+=("Sampaio2025" "Savage2024" "Segal2023" "Segal2025" "Shan2022")
#AuthorYearArray+=("Shao2024" "Sun2023" "Sun2025")
#AuthorYearArray+=("Tabbal2025")
#AuthorYearArray+=("Thukral2024")
#AuthorYearArray+=("Tong2024")
#AuthorYearArray+=("Verdi2023")
#AuthorYearArray+=("Verdi2024" "Vieira2025" "VillalonReina2024")
#AuthorYearArray+=("Wang2023" "Wen2025" "Wolfers2018" "Wolfers2020" "Wolfers2021")
#AuthorYearArray+=("Worker2023" "Wu2023" "Wu2024" "Xiao2024" "Yang2025")
#AuthorYearArray+=("Young2024" "Yu2024" "Zabihi2019" "Zabihi2020" "Zhang2022")
#AuthorYearArray+=("Zhang2023" "Zheng2024")

# AIに不必要にファイルを参照させないようにするために、
# カレントディレクトリを明確に移動させてから作業の依頼を出す。
# 作業に必要なファイルは上の階層から一時的にコピーして持ってくる。
# 書き込み状況を確認してからファイルを消す。



#GuideFile=${this_script_parent}/../prompts/DE_Guide_v9_1.md
#GuideFile=${this_script_parent}/../prompts/QA_Guide_v6_1.md

TemplateFileForDE=${this_script_parent}/../templates/DE_Author20XX_by_Someone_YYYYmmddHHMMSS.json
TemplateFileForQA=${this_script_parent}/../templates/QA_Author20XX_by_Someone_YYYYmmddHHMMSS.json

#AiAgentName="gemini"
#AiAgentName="claude"
AiAgentName="codex"


# copy template
function copyTemplate(){
    
    _target_json_file=$1
    
    # Specify Template File)
    _target_fson_file_name=$(basename "${_target_json_file}")
    _prefix="${_target_fson_file_name:0:2}"
    if [[ "${_prefix}" == "DE" ]]; then
      _template_json_file=${TemplateFileForDE}
    elif [[ "${_prefix}" == "QA" ]]; then
        _template_json_file=${TemplateFileForQA}     
    else
      echo "The file does not start with 'DE' or 'QA'."
      exit 1
    fi
        
    cp ${_template_json_file} $(dirname ${_target_json_file})    
    
}

# backup json file
function backupJsonFile(){

    _target_json_file=$1
    
    _target_json_file_name=$(basename ${_target_json_file%.json})
    _target_json_file_ext="${_target_json_file##*.}"

    
    cp ${_target_json_file} $(dirname ${_target_json_file})/${_target_json_file_name}_backup.${_target_json_file_ext}

}


function askAiAgent(){
      
    _target_json_file=$1
    _actual_run=$2
    
    
    _working_directory=$(dirname ${_target_json_file})
    
    # Change the current directory
    _previous_directory=$(pwd)
    cd ${_working_directory}
    echo "The workind directory was changed to $(pwd)."
    echo ""
  
    
    # Specify Template File
    _target_fson_file_name=$(basename "${_target_json_file}")
    _prefix="${_target_fson_file_name:0:2}"
    if [[ "${_prefix}" == "DE" ]]; then
      _template_json_file=${TemplateFileForDE}
    elif [[ "${_prefix}" == "QA" ]]; then
        _template_json_file=${TemplateFileForQA}     
    else
      echo "The file does not start with 'DE' or 'QA'."
      exit 1
    fi
    
    _template_json_file_name=$(basename ${_template_json_file})
    
      
    if [[ ${AiAgentName} == "gemini" ]]; then
        echo ""
        echo "gemini \\"
        echo "    \"${_target_fson_file_name} の構造、キーについて問題があれば修正をお願いします。このJSONファイルは ${_template_json_file_name} と 同じ構造、同じキーを持つことが期待されていますが、これら要件を満たしていない可能性があります。もし ${_target_fson_file_name} の構造やキーが ${_template_json_file_name} と不一致であれば修正してください。\" \\"
        echo "    --approval-mode auto_edit \\"
        echo "    --allowed-tools \"ShellTool(git status,rm,mv,mkdir)\""
        echo ""
        sleep 5
        if [[ ${_actual_run} == true ]]; then
        
            echo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"
            
            gemini "${_target_fson_file_name} の構造、キーについて問題があれば修正をお願いします。このJSONファイルは ${_template_json_file_name} と 同じ構造、同じキーを持つことが期待されていますが、これら要件を満たしていない可能性があります。もし ${_target_fson_file_name} の構造やキーが ${_template_json_file_name} と不一致であれば修正してください。" \
                --approval-mode auto_edit \
                --allowed-tools "ShellTool(git status,rm,mv,mkdir)"
                
            echo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" 
                 
        fi
    elif [[ ${AiAgentName} == "claude" ]]; then
        echo ""
        echo "claude -p \\"
        echo "    \"${_target_fson_file_name} の構造、キーについて問題があれば修正をお願いします。このJSONファイルは ${_template_json_file_name} と 同じ構造、同じキーを持つことが期待されていますが、これら要件を満たしていない可能性があります。もし ${_target_fson_file_name} の構造やキーが ${_template_json_file_name} と不一致であれば修正してください。\" \\"
        echo "    --allowedTools "Bash,Read" \\"
        echo "    --permission-mode acceptEdits"
        echo ""
        sleep 5
        if [[ ${_actual_run} == true ]]; then
        
            echo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"
            
            claude -p \
                "${_target_fson_file_name} の構造、キーについて問題があれば修正をお願いします。このJSONファイルは ${_template_json_file_name} と 同じ構造、同じキーを持つことが期待されていますが、これら要件を満たしていない可能性があります。もし ${_target_fson_file_name} の構造やキーが ${_template_json_file_name} と不一致であれば修正してください。" \
                --allowedTools "Bash,Read" \
                --permission-mode acceptEdits
                
            echo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"    
              
        fi
    elif [[ ${AiAgentName} == "codex" ]]; then
        echo ""
        echo "codex exec --full-auto --skip-git-repo-check -C . \\"
        echo "    \"${_target_fson_file_name} の構造、キーについて問題があれば修正をお願いします。このJSONファイルは ${_template_json_file_name} と 同じ構造、同じキーを持つことが期待されていますが、これら要件を満たしていない可能性があります。もし ${_target_fson_file_name} の構造やキーが ${_template_json_file_name} と不一致であれば修正してください。\""
        echo ""
        sleep 5
        if [[ ${_actual_run} == true ]]; then
        
            echo "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv"
            
            codex exec --full-auto --skip-git-repo-check -C . \
                "${_target_fson_file_name} の構造、キーについて問題があれば修正をお願いします。このJSONファイルは ${_template_json_file_name} と 同じ構造、同じキーを持つことが期待されていますが、これら要件を満たしていない可能性があります。もし ${_target_fson_file_name} の構造やキーが ${_template_json_file_name} と不一致であれば修正してください。"
                
            echo "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"      
            
        fi
    else 
        echo "Error. Unknown agent name was specified."
        exit 1
    fi
    
    
    cd ${_previous_directory}
    echo "The workind directory was changed to $(pwd)."
    echo ""
    echo ""
      
}


function removeCopiedTemplate(){
        
    _target_json_file=$1
    
    # Specify Template File)
    _target_fson_file_name=$(basename "${_target_json_file}")
    _prefix="${_target_fson_file_name:0:2}"
    if [[ "${_prefix}" == "DE" ]]; then
      _template_json_file=${TemplateFileForDE}
    elif [[ "${_prefix}" == "QA" ]]; then
        _template_json_file=${TemplateFileForQA}     
    else
      echo "The file does not start with 'DE' or 'QA'."
      exit 1
    fi

    
    rm $(dirname ${_target_json_file})/$(basename ${_template_json_file})
    
}


# ask ai agent
    echo ""
function core(){
    
    echo ""
    echo "====== Let's ask ${AiAgentName} the normalization of a json file ====="
    echo ""
    
    _target_json_file=$1
    _actual_run=$2
    
    if [[ -z ${_actual_run} ]]; then
         _actural_run=false
    fi
    
    copyTemplate ${_target_json_file}
    
    backupJsonFile ${_target_json_file}
    
    askAiAgent ${_target_json_file} ${_actual_run}
    
    removeCopiedTemplate ${_target_json_file}
    
}

# $1 for _target_json_file
# $2 for _actual_run
core $1 $2
