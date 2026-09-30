import os
import re
import sys

# Script to find hardcoded Cyrillic strings in tsx/ts files that are not wrapped in comments or already localized
CYRILLIC_PATTERN = re.compile(r'[\u0400-\u04FF]')

def check_file(filepath):
    untranslated = []
    with open(filepath, 'r', encoding='utf-8', errors='ignore') as f:
        lines = f.readlines()
    
    in_block_comment = False
    for idx, line in enumerate(lines, 1):
        stripped = line.strip()
        # check block comments
        if "/*" in stripped and "*/" in stripped:
            # single line block comment
            continue
        if "/*" in stripped:
            in_block_comment = True
            continue
        if in_block_comment:
            if "*/" in stripped:
                in_block_comment = False
            continue
        # line comment
        if stripped.startswith("//"):
            continue
        
        # Check if contains Cyrillic
        if CYRILLIC_PATTERN.search(line):
            # Check if this line is just a trailing comment
            code_part = line.split("//")[0]
            if CYRILLIC_PATTERN.search(code_part):
                untranslated.append((idx, line.rstrip()))
    return untranslated

def main():
    root_dirs = ["src"]
    results = {}
    for rdir in root_dirs:
        for root, dirs, files in os.walk(rdir):
            for file in files:
                if file.endswith((".tsx", ".ts")) and not file.endswith(".d.ts"):
                    path = os.path.join(root, file)
                    # Skip i18n dictionary file itself
                    if "i18n.ts" in file:
                        continue
                    un = check_file(path)
                    if un:
                        results[path] = un

    print(f"=== Found Cyrillic in {len(results)} files ===")
    total_lines = 0
    for path, lines in results.items():
        print(f"\nFile: {path} ({len(lines)} lines)")
        total_lines += len(lines)
        for num, line in lines[:5]:
            print(f"  L{num}: {line}")
        if len(lines) > 5:
            print(f"  ... and {len(lines) - 5} more")
    print(f"\nTotal untranslated/Cyrillic lines: {total_lines}")

if __name__ == "__main__":
    main()
