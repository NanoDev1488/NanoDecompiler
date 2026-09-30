"""
fetch_live_web_docs.py - скрипт для загрузки актуальной документации, спецификаций и статей
из интернета (Oracle JVMS, OpenJDK JEPs, cppreference, исследовательские материалы по декомпиляторам).

Сохраняет скачанные материалы в docs/downloaded_research/ с манифестом WEB_SOURCES_MANIFEST.json,
содержащим URL, HTTP-заголовки, SHA-256 и время загрузки.
"""

import os
import sys
import json
import hashlib
import datetime
import urllib.request
import urllib.error
import re

TARGET_DIR = os.path.join(os.path.dirname(__file__), "..", "docs", "downloaded_research")

SOURCES = [
    # --- OpenJDK JEPs (Современная спецификация и desugaring паттернов) ---
    {
        "id": "jep-280-indify-string-concat",
        "url": "https://openjdk.org/jeps/280",
        "title": "JEP 280: Indify String Concatenation",
        "category": "jvm_modern_specs"
    },
    {
        "id": "jep-394-pattern-matching-instanceof",
        "url": "https://openjdk.org/jeps/394",
        "title": "JEP 394: Pattern Matching for instanceof",
        "category": "jvm_modern_specs"
    },
    {
        "id": "jep-395-records",
        "url": "https://openjdk.org/jeps/395",
        "title": "JEP 395: Records",
        "category": "jvm_modern_specs"
    },
    {
        "id": "jep-409-sealed-classes",
        "url": "https://openjdk.org/jeps/409",
        "title": "JEP 409: Sealed Classes",
        "category": "jvm_modern_specs"
    },
    {
        "id": "jep-440-record-patterns",
        "url": "https://openjdk.org/jeps/440",
        "title": "JEP 440: Record Patterns",
        "category": "jvm_modern_specs"
    },
    {
        "id": "jep-441-pattern-matching-switch",
        "url": "https://openjdk.org/jeps/441",
        "title": "JEP 441: Pattern Matching for switch",
        "category": "jvm_modern_specs"
    },
    {
        "id": "jep-456-unnamed-variables-patterns",
        "url": "https://openjdk.org/jeps/456",
        "title": "JEP 456: Unnamed Variables & Patterns",
        "category": "jvm_modern_specs"
    },

    # --- Oracle Java Virtual Machine Specification (Java SE 21) ---
    {
        "id": "jvms-se21-ch2-structure",
        "url": "https://docs.oracle.com/javase/specs/jvms/se21/html/jvms-2.html",
        "title": "JVMS SE 21: Chapter 2 - The Structure of the Java Virtual Machine",
        "category": "jvms_specification"
    },
    {
        "id": "jvms-se21-ch3-compiling",
        "url": "https://docs.oracle.com/javase/specs/jvms/se21/html/jvms-3.html",
        "title": "JVMS SE 21: Chapter 3 - Compiling for the Java Virtual Machine",
        "category": "jvms_specification"
    },
    {
        "id": "jvms-se21-ch4-class-file-format",
        "url": "https://docs.oracle.com/javase/specs/jvms/se21/html/jvms-4.html",
        "title": "JVMS SE 21: Chapter 4 - The class File Format",
        "category": "jvms_specification"
    },
    {
        "id": "jvms-se21-ch5-loading-linking-initializing",
        "url": "https://docs.oracle.com/javase/specs/jvms/se21/html/jvms-5.html",
        "title": "JVMS SE 21: Chapter 5 - Loading, Linking, and Initializing",
        "category": "jvms_specification"
    },
    {
        "id": "jvms-se21-ch6-instruction-set",
        "url": "https://docs.oracle.com/javase/specs/jvms/se21/html/jvms-6.html",
        "title": "JVMS SE 21: Chapter 6 - The Java Virtual Machine Instruction Set",
        "category": "jvms_specification"
    },

    # --- C++ Engine Architecture & Modern C++ Guidelines (cppreference) ---
    {
        "id": "cpp-raii-resource-management",
        "url": "https://en.cppreference.com/w/cpp/language/raii",
        "title": "cppreference: RAII (Resource Acquisition Is Initialization)",
        "category": "cpp_architecture"
    },
    {
        "id": "cpp-memory-model",
        "url": "https://en.cppreference.com/w/cpp/language/memory_model",
        "title": "cppreference: C++ Memory Model and Data Races",
        "category": "cpp_architecture"
    },
    {
        "id": "cpp-move-semantics",
        "url": "https://en.cppreference.com/w/cpp/language/move_constructor",
        "title": "cppreference: Move Constructors and Rvalue References",
        "category": "cpp_architecture"
    },
    {
        "id": "cpp-smart-pointers",
        "url": "https://en.cppreference.com/w/cpp/memory/unique_ptr",
        "title": "cppreference: std::unique_ptr and AST Node Ownership",
        "category": "cpp_architecture"
    },
    {
        "id": "cpp-variant-sum-types",
        "url": "https://en.cppreference.com/w/cpp/utility/variant",
        "title": "cppreference: std::variant and Type-Safe Sum Types for IR",
        "category": "cpp_architecture"
    }
]

def clean_html(html_text: str) -> str:
    """Удаляет лишние теги и форматирует чистый текст статьи."""
    # Удаляем script и style
    text = re.sub(r'<script.*?</script>', '', html_text, flags=re.DOTALL | re.IGNORECASE)
    text = re.sub(r'<style.*?</style>', '', text, flags=re.DOTALL | re.IGNORECASE)
    # Превращаем h1-h6 в markdown заголовки
    text = re.sub(r'<h1[^>]*>(.*?)</h1>', r'\n# \1\n', text, flags=re.DOTALL | re.IGNORECASE)
    text = re.sub(r'<h2[^>]*>(.*?)</h2>', r'\n## \1\n', text, flags=re.DOTALL | re.IGNORECASE)
    text = re.sub(r'<h3[^>]*>(.*?)</h3>', r'\n### \1\n', text, flags=re.DOTALL | re.IGNORECASE)
    text = re.sub(r'<h4[^>]*>(.*?)</h4>', r'\n#### \1\n', text, flags=re.DOTALL | re.IGNORECASE)
    # Заменяем <pre> и <code>
    text = re.sub(r'<pre[^>]*>(.*?)</pre>', r'\n```\n\1\n```\n', text, flags=re.DOTALL | re.IGNORECASE)
    text = re.sub(r'<code[^>]*>(.*?)</code>', r'`\1`', text, flags=re.DOTALL | re.IGNORECASE)
    text = re.sub(r'<p[^>]*>(.*?)</p>', r'\n\1\n', text, flags=re.DOTALL | re.IGNORECASE)
    text = re.sub(r'<br\s*/?>', r'\n', text, flags=re.IGNORECASE)
    # Удаляем остальные теги
    text = re.sub(r'<[^>]+>', ' ', text)
    # Декодируем базовые сущности
    text = text.replace('&nbsp;', ' ').replace('&lt;', '<').replace('&gt;', '>').replace('&amp;', '&').replace('&quot;', '"')
    # Сжимаем лишние пробелы и пустые строки
    lines = [line.strip() for line in text.split('\n')]
    cleaned = []
    prev_blank = False
    for line in lines:
        if not line:
            if not prev_blank:
                cleaned.append('')
                prev_blank = True
        else:
            cleaned.append(line)
            prev_blank = False
    return '\n'.join(cleaned)

def main():
    os.makedirs(TARGET_DIR, exist_ok=True)
    manifest = {
        "fetched_at": datetime.datetime.now(datetime.timezone.utc).isoformat(),
        "total_sources": len(SOURCES),
        "files": []
    }

    headers = {
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
    }

    print(f"[*] Начало загрузки {len(SOURCES)} официальных веб-источников из интернета...")

    for i, item in enumerate(SOURCES, start=1):
        print(f"[{i}/{len(SOURCES)}] Загрузка {item['id']} ({item['url']})...")
        cat_dir = os.path.join(TARGET_DIR, item["category"])
        os.makedirs(cat_dir, exist_ok=True)
        
        md_filename = f"{item['id']}.md"
        out_path = os.path.join(cat_dir, md_filename)

        try:
            req = urllib.request.Request(item["url"], headers=headers)
            with urllib.request.urlopen(req, timeout=30) as resp:
                status = resp.status
                raw_bytes = resp.read()
                content_type = resp.headers.get("Content-Type", "")
                encoding = "utf-8"
                if "charset=" in content_type.lower():
                    encoding = content_type.lower().split("charset=")[-1].split(";")[0].strip()

                try:
                    text_content = raw_bytes.decode(encoding, errors="replace")
                except Exception:
                    text_content = raw_bytes.decode("utf-8", errors="replace")

                sha256 = hashlib.sha256(raw_bytes).hexdigest()
                cleaned_markdown = clean_html(text_content)

                file_header = f"""---
source_url: {item['url']}
title: {item['title']}
downloaded_at_utc: {datetime.datetime.now(datetime.timezone.utc).isoformat()}
http_status: {status}
content_sha256: {sha256}
category: {item['category']}
---

# {item['title']}

**Официальный первоисточник:** [{item['url']}]({item['url']})  
**Статус загрузки:** HTTP {status} OK  
**Хэш содержимого (SHA-256):** `{sha256}`  

---

"""
                with open(out_path, "w", encoding="utf-8") as f:
                    f.write(file_header + cleaned_markdown)

                manifest["files"].append({
                    "id": item["id"],
                    "title": item["title"],
                    "url": item["url"],
                    "category": item["category"],
                    "relative_path": os.path.join(item["category"], md_filename).replace("\\", "/"),
                    "bytes": len(raw_bytes),
                    "sha256": sha256,
                    "http_status": status
                })
                print(f"    -> Сохранено: {len(raw_bytes)} байт, SHA-256: {sha256[:12]}...")

        except Exception as e:
            print(f"    [!] Ошибка при загрузке {item['url']}: {e}")
            manifest["files"].append({
                "id": item["id"],
                "url": item["url"],
                "error": str(e)
            })

    manifest_path = os.path.join(TARGET_DIR, "WEB_SOURCES_MANIFEST.json")
    with open(manifest_path, "w", encoding="utf-8") as f:
        json.dump(manifest, f, indent=2, ensure_ascii=False)

    print(f"\n[+] Загрузка завершена! Манифест записан в {manifest_path}")

if __name__ == "__main__":
    main()
