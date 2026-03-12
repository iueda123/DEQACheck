
目的:
Gemini等でJSON形式が乱れることがあるため、
Codex等のJSON出力に強いモデルで後処理し、
出力ファイルを期待されるJSON形式に整形できるようにする。

前提:
- ベースは `share_package/tools/ask_AiToDe_v14.sh`

方針:
- `--ensure-json-structure-by` オプション付きの
  `share_package/tools/ask_AiToDe_v14_2.sh` を作成する。
- オプション指定時に、JSON整形の後処理を走らせる。

後処理:
- 例: `share_package/tools/checkAndNormalizeJson/subfuncs/ask_AiToNormalize_for_DE_v12.sh`
- デフォルトの後処理モデルは codex
- 目的: 期待されるJSON形式へ正規化
