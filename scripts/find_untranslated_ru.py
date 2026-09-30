#!/usr/bin/env python3
import os
import re

CYRILLIC_RE = re.compile(r'[а-яА-ЯёЁ]')
IGNORE_FILES = {
    'i18n.ts',
    'mcColors.ts',      # contains color names / game color descriptions
}
IGNORE_PATTERNS = [
    r'^\s*//',          # comments
    r'^\s*/\*',         # block comment start
    r'^\s*\* ',         # block comment line
]

def scan_file(filepath):
    found = []
    with open(filepath, 'r', encoding='utf-8', errors='ignore') as f:
        for idx, line in enumerate(f, 1):
            # Check if line is comment
            stripped = line.strip()
            if any(re.match(p, stripped) for p in IGNORE_PATTERNS):
                continue
            if CYRILLIC_RE.search(line):
                found.append((idx, stripped))
    return found

def main():
    src_dir = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), 'src')
    total_findings = 0
    results = {}
    for root, _, files in os.walk(src_dir):
        for file in files:
            if not file.endswith(('.ts', '.tsx')):
                continue
            if file in IGNORE_FILES:
                continue
            full_path = os.path.join(root, file)
            rel_path = os.path.relpath(full_path, src_dir)
            hits = scan_file(full_path)
            if hits:
                results[rel_path] = hits
                total_findings += len(hits)

    print(f"=== Найдено строк с русским текстом: {total_findings} в {len(results)} файлах ===\n")
    for file, hits in sorted(results.items()):
        print(f"--- {file} ({len(hits)} совпадений) ---")
        for line_num, line in hits[:8]:  # Show first 8 per file
            print(f"  [{line_num}] {line[:100]}")
        if len(hits) > 8:
            print(f"  ... и ещё {len(hits) - 8} строк")
        print()

if __name__ == '__main__':
    main()
