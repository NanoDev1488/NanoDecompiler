import json
import os

with open(r"artifact_itemsadder\itemsadder_result.json", "r", encoding="utf-8") as f:
    raw = f.read()

print(f"File size: {len(raw)} bytes")
for line in raw.splitlines():
    line = line.strip()
    if line.startswith('{') and line.endswith('}'):
        try:
            d = json.loads(line)
            print("Keys:", list(d.keys()))
            for k in ["status", "platform", "platform_name", "total_classes", "total_methods", "decompiled_methods", "fallback_methods"]:
                if k in d:
                    print(f"  {k}: {d[k]}")
            if "fallback_locations" in d:
                print(f"fallback_locations count: {len(d['fallback_locations'])}")
                for loc in d['fallback_locations']:
                    print("   *", loc)
            if "fallbacks" in d:
                print(f"fallbacks count: {len(d['fallbacks'])}")
                for loc in d['fallbacks']:
                    print("   #", loc)
            if "errors" in d:
                print(f"errors: {d['errors']}")
            if "decompilation_stats" in d:
                print("decompilation_stats:", d["decompilation_stats"])
        except Exception as e:
            pass

err_path = r"artifact_itemsadder\itemsadder_err.log"
if os.path.exists(err_path) and os.path.getsize(err_path) > 0:
    print(f"\n--- ERR LOG ({os.path.getsize(err_path)} bytes) ---")
    with open(err_path, "r", encoding="utf-8", errors="ignore") as f:
        print(f.read()[:2000])
