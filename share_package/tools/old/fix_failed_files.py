#!/usr/bin/env python3
"""
失敗したJSONファイルを修正するスクリプト
"""

import json
import re
import os

failed_files = [
    "/media/iu/STORAGE/.Trash-1000/files/DE_Results/Little2024/DE/json/DE_Little2024_by_claude_202511011727.json",
    "/media/iu/STORAGE/.Trash-1000/files/DE_Results/Tabbal2025/DE/json/DE_Tabbal2025_by_gemini_202510311600.json",
    "/media/iu/STORAGE/.Trash-1000/files/DE_Results/Verdi2023/DE/json/DE_Verdi2023_by_gemini_202510311100.json",
    "/media/iu/STORAGE/.Trash-1000/files/DE_Results/Jiang2024/DE/json/DE_Jiang2024_by_gemini_202510310000.json",
    "/media/iu/STORAGE/.Trash-1000/files/DE_Results/Holz2023/DE/json/DE_Holz2023_by_gemini_202510300100.json",
    "/media/iu/STORAGE/.Trash-1000/files/DE_Results/Wolfers2021/DE/json/DE_Wolfers2021_by_claude_202511061242.json",
    "/media/iu/STORAGE/.Trash-1000/files/DE_Results/RehakBuckova2025/DE/json/DE_RehakBuckova2025_by_gemini_202510301200.json",
    "/media/iu/STORAGE/.Trash-1000/files/DE_Results/Liu2024/DE/json/DE_Liu2024_by_gemini_202511051500.json",
    "/media/iu/STORAGE/.Trash-1000/files/DE_Results/OliveiraSaraiva2023/DE/json/DE_OliveiraSaraiva2023_by_gemini_202510301200.json"
]

def fix_json_file(file_path):
    """JSONファイルの構文エラーを修正"""
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            content = f.read()

        # バックアップ
        backup_path = file_path + ".broken_backup"
        with open(backup_path, 'w', encoding='utf-8') as f:
            f.write(content)

        # 一般的な修正
        # 1. 無効なエスケープシーケンスを修正
        content = content.replace('\\_', '_')

        # 2. 文字列内の改行を修正
        lines = content.split('\n')
        fixed_lines = []
        in_string = False
        for line in lines:
            quote_count = line.count('"') - line.count('\\"')
            if in_string:
                if fixed_lines:
                    fixed_lines[-1] = fixed_lines[-1].rstrip() + ' ' + line.lstrip()
                else:
                    fixed_lines.append(line)
            else:
                fixed_lines.append(line)

            if quote_count % 2 == 1:
                in_string = not in_string

        content = '\n'.join(fixed_lines)

        # 3. trailing comma を削除（JSON末尾の余分なカンマ）
        content = re.sub(r',\s*}', '}', content)
        content = re.sub(r',\s*]', ']', content)

        # JSONをパース
        try:
            data = json.loads(content)
            print(f"✓ Fixed: {os.path.basename(file_path)}")

            # 修正されたファイルを保存
            with open(file_path, 'w', encoding='utf-8') as f:
                json.dump(data, f, indent=2, ensure_ascii=False)

            return True, "Successfully fixed"

        except json.JSONDecodeError as e:
            # より詳細な修正が必要
            print(f"✗ Still broken: {os.path.basename(file_path)}")
            print(f"  Error: {e}")
            print(f"  Position: line {e.lineno}, column {e.colno}")

            return False, str(e)

    except Exception as e:
        print(f"✗ Error: {os.path.basename(file_path)}")
        print(f"  {str(e)}")
        return False, str(e)


def main():
    print("Fixing failed JSON files...")
    print("=" * 80)

    success_count = 0
    still_failed = []

    for file_path in failed_files:
        if os.path.exists(file_path):
            success, message = fix_json_file(file_path)
            if success:
                success_count += 1
            else:
                still_failed.append((file_path, message))
        else:
            print(f"✗ File not found: {file_path}")
            still_failed.append((file_path, "File not found"))

    print("=" * 80)
    print(f"Summary:")
    print(f"  Fixed: {success_count}/{len(failed_files)}")
    print(f"  Still broken: {len(still_failed)}")

    if still_failed:
        print("\nFiles that still need manual fixing:")
        for file_path, error in still_failed:
            print(f"  - {os.path.basename(file_path)}")
            print(f"    {error}")


if __name__ == "__main__":
    main()
