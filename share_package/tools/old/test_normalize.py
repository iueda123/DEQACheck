#!/usr/bin/env python3
"""
テスト用の簡易スクリプト
"""

import json
import sys
sys.path.insert(0, '/media/iu/STORAGE/.Trash-1000/files/DE_Results')

from normalize_json_structure import normalize_json_file

# テストファイル
test_files = [
    "/media/iu/STORAGE/.Trash-1000/files/DE_Results/test_conversion/test_flat.json",
    "/media/iu/STORAGE/.Trash-1000/files/DE_Results/test_conversion/test_numbered.json",
    "/media/iu/STORAGE/.Trash-1000/files/DE_Results/test_conversion/test_correct.json"
]

print("Testing normalization on sample files...")
print("=" * 80)

for test_file in test_files:
    print(f"\nTesting: {test_file}")

    # 元のファイルを読み込んで表示
    with open(test_file, 'r', encoding='utf-8') as f:
        content = f.read()
    content = content.replace('\\_', '_')
    # 制御文字を削除
    content = ''.join(char if ord(char) >= 32 or char in '\t\n\r' else ' ' for char in content)
    original_data = json.loads(content)
    print(f"  Original keys: {list(original_data.keys())[:5]}...")

    # 正規化を実行
    success, message = normalize_json_file(test_file, create_backup=True)

    if success:
        print(f"  ✓ {message}")

        # 変換後のファイルを読み込んで表示
        with open(test_file, 'r', encoding='utf-8') as f:
            normalized_data = json.load(f)
        print(f"  Normalized keys: {list(normalized_data.keys())}")

        # いくつかのキーの値を確認
        if "study_identification" in normalized_data:
            print(f"  study_identification keys: {list(normalized_data['study_identification'].keys())}")
    else:
        print(f"  ✗ {message}")

print("\n" + "=" * 80)
print("Test completed!")
