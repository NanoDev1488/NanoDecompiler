import json

with open(r"artifact_itemsadder\itemsadder_result.json", "r", encoding="utf-8") as f:
    data = json.load(f)

contexts = data.get("stats", {}).get("fallback_contexts", [])
for i, c in enumerate(contexts):
    print(f"=== CTX {i} ===")
    print(json.dumps(c, indent=2, ensure_ascii=False)[:1000])
