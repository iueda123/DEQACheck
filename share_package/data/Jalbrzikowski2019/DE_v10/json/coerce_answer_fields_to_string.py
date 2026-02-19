import json
from pathlib import Path

TARGET = Path('DE_Jalbrzikowski2019_by_gemini_20251209161423.json')

FIELDS = {"answer", "confidence_rating", "reason", "supporting_text", "location", "detail"}

def coerce(obj):
    if isinstance(obj, dict):
        for k, v in list(obj.items()):
            if k in FIELDS and not isinstance(v, (str, type(None))):
                obj[k] = "" if v is None else str(v)
            else:
                obj[k] = coerce(v)
    elif isinstance(obj, list):
        return [coerce(v) for v in obj]
    return obj

def main():
    data = json.loads(TARGET.read_text(encoding='utf-8'))
    data = coerce(data)
    TARGET.write_text(json.dumps(data, ensure_ascii=False, indent=2), encoding='utf-8')

if __name__ == '__main__':
    main()

