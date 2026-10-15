import json

with open(r"artifact_itemsadder\itemsadder_result.json", "r", encoding="utf-8") as f:
    data = json.load(f)

stats = data.get("stats", {})
contexts = stats.get("fallback_contexts", [])

print(f"Total fallback contexts: {len(contexts)}\n")
for idx, ctx in enumerate(contexts, 1):
    owner = ctx.get("owner_class") or ctx.get("class_name")
    method = ctx.get("method_name")
    desc = ctx.get("method_desc")
    reason = ctx.get("reason")
    exc = ctx.get("exception") or ctx.get("error")
    print(f"[{idx}] {owner} -> {method}{desc}")
    print(f"    Reason: {reason}")
    if exc:
        print(f"    Exception: {exc}")
    snippet = ctx.get("bytecode_snippet") or ctx.get("disasm")
    if snippet:
        lines = snippet if isinstance(snippet, list) else snippet.splitlines()
        print("    Disassembly:")
        for l in lines[:10]:
            print(f"      {l}")
    print()
