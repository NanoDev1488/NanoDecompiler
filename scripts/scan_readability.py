#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
scan_readability.py - Сквозной сканер читабельности декомпилированного Java-кода.
Проходит по всем сгенерированным .java файлам в out_dir, находит любые шероховатости,
артефакты стека, нередуцированные конструкции, и сравнивает их с байткодом javap.
"""

import os
import sys
import re
import json
import zipfile
import subprocess
from collections import defaultdict

def scan_file(java_path, rel_path):
    issues = []
    try:
        with open(java_path, "r", encoding="utf-8", errors="replace") as f:
            lines = f.readlines()
    except Exception as e:
        return [{"type": "read_error", "file": rel_path, "line": 0, "code": str(e)}]

    in_comment = False
    for i, line in enumerate(lines, 1):
        s = line.strip()
        
        # Пропуск блочных комментариев
        if "/*" in s and "*/" in s:
            pass
        elif "/*" in s:
            in_comment = True
            continue
        elif "*/" in s:
            in_comment = False
            continue
        if in_comment or s.startswith("//"):
            continue

        # 1. Артефакты стека / временных переменных
        if re.search(r'\b(__stk\w*|__temp\w*|__entry\w*)\b', s):
            issues.append({
                "type": "stack_temp_artifact",
                "file": rel_path,
                "line": i,
                "code": s,
                "detail": "Остаточная временная переменная стека или синтетический локал"
            })

        # 2. Несвернутые goto
        if re.search(r'\bgoto\s+\w+;', s):
            issues.append({
                "type": "goto_stmt",
                "file": rel_path,
                "line": i,
                "code": s,
                "detail": "Нередуцированный переход goto вместо цикла/условия"
            })

        # 3. Странные булевы сравнения (== true, == false, ! == true)
        if re.search(r'(==\s*true\b|==\s*false\b|!=\s*true\b|!=\s*false\b)', s):
            issues.append({
                "type": "redundant_boolean_cmp",
                "file": rel_path,
                "line": i,
                "code": s,
                "detail": "Избыточное сравнение с true/false"
            })

        # 4. Тривиальные тернарники x ? true : false
        if re.search(r'\?\s*true\s*:\s*false\b|\?\s*false\s*:\s*true\b', s):
            issues.append({
                "type": "trivial_ternary",
                "file": rel_path,
                "line": i,
                "code": s,
                "detail": "Тривиальный тернарный оператор вместо прямого условия"
            })

        # 5. Синтетические null-checks (x.getClass();)
        if re.search(r'\b\w+\.getClass\(\)\s*;', s) and not any(k in s for k in ["Class<?>", "Class ", "println", "print", "log", "return"]):
            issues.append({
                "type": "synthetic_null_check",
                "file": rel_path,
                "line": i,
                "code": s,
                "detail": "Синтетический javac null-check через x.getClass()"
            })

        # 6. Избыточные апкасты ((Object) x)
        if re.search(r'\(\(Object\)\s*[a-zA-Z0-9_.]+\)', s):
            issues.append({
                "type": "redundant_upcast_object",
                "file": rel_path,
                "line": i,
                "code": s,
                "detail": "Избыточный каст к (Object)"
            })

        # 7. Неопрятные циклы while (true) со странными хвостами
        if re.search(r'while\s*\(\s*true\s*\)\s*\{', s):
            # Проверим следующие пару строк на простой if-break
            next_lines = [lines[j].strip() for j in range(i, min(len(lines), i + 3))]
            for nl in next_lines:
                if re.search(r'if\s*\(.*?\)\s*break;', nl):
                    issues.append({
                        "type": "while_true_break",
                        "file": rel_path,
                        "line": i,
                        "code": s + " -> " + nl,
                        "detail": "while(true) с немедленным if-break вместо прямого while(cond)"
                    })
                    break

        # 8. Нераспознанные SwitchMap структуры
        if "$SwitchMap$" in s:
            issues.append({
                "type": "switch_map_artifact",
                "file": rel_path,
                "line": i,
                "code": s,
                "detail": "Синтетический массив $SwitchMap$ не свернут в чистый enum switch"
            })

        # 9. Префиксы java.lang.
        if re.search(r'\bjava\.lang\.(String|Integer|Boolean|Double|Float|Long|Byte|Short|Character|Object|Exception|RuntimeException|Throwable|StringBuilder|StringBuffer|Thread|Math)\b', s):
            issues.append({
                "type": "java_lang_prefix",
                "file": rel_path,
                "line": i,
                "code": s,
                "detail": "Избыточный префикс пакета java.lang.*"
            })

        # 10. Повторяющиеся пустые блоки {}
        if re.search(r'\{\s*\}', s) and not any(k in s for k in ["class ", "interface ", "enum ", "new "]):
            issues.append({
                "type": "empty_block",
                "file": rel_path,
                "line": i,
                "code": s,
                "detail": "Пустой операторный блок {}"
            })

    return issues

def get_bytecode_for_class(jar_path, class_rel_path):
    """Извлекает .class файл из JAR и запускает javap -c -p для сравнения."""
    try:
        with zipfile.ZipFile(jar_path, 'r') as z:
            base_name = os.path.basename(class_rel_path).replace(".java", ".class")
            match = None
            for name in z.namelist():
                if os.path.basename(name) == base_name:
                    match = name
                    break
            if not match:
                return f"Class {base_name} not found in {jar_path}"

            data = z.read(match)
            tmp_class = "tmp_inspect.class"
            with open(tmp_class, "wb") as f:
                f.write(data)

            res = subprocess.run(["javap", "-c", "-p", tmp_class], stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True, timeout=10)
            if os.path.exists(tmp_class):
                os.remove(tmp_class)
            return res.stdout[:2500]
    except Exception as e:
        return f"Bytecode extraction error: {e}"

def main():
    if len(sys.argv) < 3:
        print("Usage: python scan_readability.py <out_dir> <jar_path>")
        sys.exit(1)

    out_dir = sys.argv[1]
    jar_path = sys.argv[2]

    print(f"Scanning directory: {out_dir}")
    print(f"Reference JAR: {jar_path}")

    all_issues = []
    total_files = 0
    total_lines = 0

    for root, dirs, files in os.walk(out_dir):
        for f in files:
            if f.endswith(".java"):
                total_files += 1
                full_path = os.path.join(root, f)
                rel_path = os.path.relpath(full_path, out_dir).replace("\\", "/")
                
                with open(full_path, "r", encoding="utf-8", errors="ignore") as jf:
                    total_lines += sum(1 for _ in jf)

                issues = scan_file(full_path, rel_path)
                if issues:
                    all_issues.extend(issues)

    print(f"Total scanned Java files: {total_files}")
    print(f"Total lines of code: {total_lines}")
    print(f"Total readability issues found: {len(all_issues)}")

    # Группировка по типам
    by_type = defaultdict(list)
    for iss in all_issues:
        by_type[iss["type"]].append(iss)

    print("\n--- Summary by Issue Type ---")
    for t, items in sorted(by_type.items(), key=lambda x: -len(x[1])):
        print(f"  {t}: {len(items)} occurrences")

    # Формируем отчет
    report = {
        "total_files": total_files,
        "total_lines": total_lines,
        "total_issues": len(all_issues),
        "issues_by_type": {t: len(items) for t, items in by_type.items()},
        "top_samples": {}
    }

    # Для каждого типа берем до 5 примеров с извлечением байткода
    for t, items in by_type.items():
        samples = []
        for iss in items[:5]:
            bc = get_bytecode_for_class(jar_path, iss["file"])
            samples.append({
                "file": iss["file"],
                "line": iss["line"],
                "code": iss["code"],
                "detail": iss["detail"],
                "bytecode_snippet": bc[:1000] if bc else "N/A"
            })
        report["top_samples"][t] = samples

    with open("readability_report.json", "w", encoding="utf-8") as f:
        json.dump(report, f, indent=2, ensure_ascii=False)

    # Генерация Markdown отчета
    md_lines = [
        "# NanoDecompiler - Отчет анализа читабельности кода (ItemsAdder)",
        f"",
        f"- **Всего проверено файлов**: {total_files}",
        f"- **Суммарно строк кода (LOC)**: {total_lines}",
        f"- **Найдено замечаний**: {len(all_issues)}",
        f"",
        f"## Статистика по категориям",
        f"",
        f"| Категория | Количество | Описание |",
        f"|---|---|---|",
    ]
    for t, items in sorted(by_type.items(), key=lambda x: -len(x[1])):
        detail = items[0]["detail"] if items else ""
        md_lines.append(f"| `{t}` | **{len(items)}** | {detail} |")

    md_lines.append("\n## Детальные примеры и сопоставление с байткодом\n")
    for t, samples in report["top_samples"].items():
        md_lines.append(f"### Категория: `{t}` ({len(by_type[t])} мест)")
        for s in samples:
            md_lines.append(f"- **Файл**: `{s['file']}:{s['line']}`")
            md_lines.append(f"  ```java\n  {s['code']}\n  ```")
            md_lines.append(f"  *Байткод (javap)*:\n  ```jvm\n{s['bytecode_snippet'][:400]}\n  ```\n")

    with open("readability_report.md", "w", encoding="utf-8") as f:
        f.write("\n".join(md_lines))

    print("\nReports generated: readability_report.json, readability_report.md")

if __name__ == "__main__":
    main()
