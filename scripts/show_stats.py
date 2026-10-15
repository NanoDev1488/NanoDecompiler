import json

with open(r"artifact_itemsadder\itemsadder_result.json", "r", encoding="utf-8") as f:
    data = json.load(f)

stats = data.get("stats", {})
print("Stats keys:", list(stats.keys()))
for k in ["total_classes", "total_methods", "decompiled_methods", "fallback_methods", "fallback_locations"]:
    v = stats.get(k)
    if k == "fallback_locations":
        print(f"{k} count: {len(v) if isinstance(v, list) else v}")
        if isinstance(v, list):
            for item in v:
                print(" ->", item)
    else:
        print(f"{k}: {v}")
