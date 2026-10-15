import json

with open(r"artifact_itemsadder\itemsadder_result.json", "r", encoding="utf-8") as f:
    data = json.load(f)

contexts = data.get("stats", {}).get("fallback_contexts", [])
for i, c in enumerate(contexts):
    print(f"\n==================== CTX {i} ====================")
    print("File:", c.get("file"))
    print("Method hint:", c.get("method_hint"))
    print("Bytecode lines:")
    for b in c.get("bytecode", []):
        print(" ", b)
