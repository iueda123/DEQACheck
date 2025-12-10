#!/bin/bash

# このスクリプトは、渡されたすべての引数を番号付きで表示するGUIダイアログを表示します。
# 各引数は「  * ARG1: 値」の形式で改行区切りで表示されます。

message=""
counter=1
for arg in "$@"; do
    message="${message}  * ARG${counter}: ${arg}"
    counter=$((counter + 1))
done

zenity --info --text="${message}"