import json

with open(r"artifact_itemsadder\itemsadder_result.json", "r", encoding="utf-8") as f:
    data = json.load(f)

stats = data.get("stats", {})
print("=== FALLBACK REASONS ===")
for r, count in stats.get("fallback_reasons", {}).items():
    print(f"  {r}: {count}")

print("\n=== FALLBACK CONTEXTS ===")
for ctx in stats.get("fallback_contexts", []):
    print("--------------------------------------------------")
    for k, v in ctx.items():
        if k == "bytecode_snippet" or k == "disasm":
            print(f"  {k}:")
            for line in str(v).splitlines()[:15]:
                print(f"    {line}")
        else:
            print(f"  {k}: {v}")
