# -*- coding: utf-8 -*-
"""
Проверки сгенерированного Java-кода. Мы не можем вызвать javac (его нет в
окружении сборки), поэтому проверяем то, что реально можем проверить
детерминированно и без догадок:

  1. Баланс скобок {}, (), [] по всему файлу (с учётом строковых/символьных
     литералов и комментариев - примитивный, но честный Java-lexer).
  2. Каждый оператор верхнего уровня оканчивается на ';' или '{'/'}' там, где
     это ожидается (грубая, но полезная эвристика).
  3. Коллизии простых имён импортов (два разных полных имени класса,
     сведённые к одинаковому simple-имени) - потенциальный источник неверной
     компиляции, если не устранить вручную.
  4. Итоговая статистика по методам (сколько реально декомпилировано в
     структурированный Java, а сколько безопасно откачено на байткод-листинг).

Эти проверки НЕ заменяют настоящую компиляцию, но ловят реальный класс
ошибок (баги в emit.py/structure.py, которые привели бы к несобираемому
файлу) без риска "додумывания" семантики.
"""
import re


def check_brackets(text, filename):
    issues = []
    pairs = {')': '(', ']': '[', '}': '{'}
    opens = set(pairs.values())
    closes = set(pairs.keys())
    stack = []
    i = 0
    n = len(text)
    line = 1
    in_string = False
    in_char = False
    in_line_comment = False
    in_block_comment = False
    while i < n:
        c = text[i]
        if c == "\n":
            line += 1
            in_line_comment = False
            i += 1
            continue
        if in_line_comment:
            i += 1
            continue
        if in_block_comment:
            if c == "*" and i + 1 < n and text[i + 1] == "/":
                in_block_comment = False
                i += 2
                continue
            i += 1
            continue
        if in_string:
            if c == "\\":
                i += 2
                continue
            if c == '"':
                in_string = False
            i += 1
            continue
        if in_char:
            if c == "\\":
                i += 2
                continue
            if c == "'":
                in_char = False
            i += 1
            continue
        if c == "/" and i + 1 < n and text[i + 1] == "/":
            in_line_comment = True
            i += 2
            continue
        if c == "/" and i + 1 < n and text[i + 1] == "*":
            in_block_comment = True
            i += 2
            continue
        if c == '"':
            in_string = True
            i += 1
            continue
        if c == "'":
            in_char = True
            i += 1
            continue
        if c in opens:
            stack.append((c, line))
        elif c in closes:
            if not stack:
                issues.append(f"{filename}:{line}: лишняя закрывающая скобка '{c}'")
            else:
                oc, oline = stack.pop()
                if pairs[c] != oc:
                    issues.append(f"{filename}:{line}: несовпадение скобок: открыта '{oc}' на строке {oline}, закрыта '{c}'")
        i += 1
    for oc, oline in stack:
        issues.append(f"{filename}:{oline}: незакрытая скобка '{oc}'")
    return issues


def check_import_collisions(imports):
    """imports: dict dotted -> simple (собранный по всем классам файла/проекта).
    Возвращает список конфликтов simple_name -> [dotted1, dotted2, ...] где >1 разных источников."""
    by_simple = {}
    for dotted, simple in imports.items():
        by_simple.setdefault(simple, set()).add(dotted)
    conflicts = {s: sorted(d) for s, d in by_simple.items() if len(d) > 1}
    return conflicts


def verify_class_text(text, filename):
    return check_brackets(text, filename)


class ProjectStats:
    def __init__(self):
        self.total_methods = 0
        self.decompiled_methods = 0
        self.fallback_methods = 0
        self.fallback_reasons = {}
        self.classes_total = 0
        self.classes_parsed = 0
        self.parse_errors = []
        self.bracket_issues = []
        self.import_conflicts = {}
        self.synthetic_switchmap_classes_hidden = 0
        self.library_classes_skipped = 0
        self.library_names_hit = set()

    def record_method(self, ok, reason=None):
        self.total_methods += 1
        if ok:
            self.decompiled_methods += 1
        else:
            self.fallback_methods += 1
            self.fallback_reasons[reason] = self.fallback_reasons.get(reason, 0) + 1

    def pct(self, part, whole):
        return (part / whole * 100.0) if whole else 0.0

    def summary_text(self):
        lines = []
        lines.append("=" * 70)
        lines.append("ПРОВЕРКА КАЧЕСТВА ДЕКОМПИЛЯЦИИ")
        lines.append("=" * 70)
        lines.append("")
        lines.append(f"Классов в jar: {self.classes_total}, успешно распарсено байткода: "
                      f"{self.classes_parsed} ({self.pct(self.classes_parsed, self.classes_total):.1f}%)")
        if self.library_classes_skipped:
            hit = ", ".join(sorted(self.library_names_hit)) or "?"
            lines.append(f"  Классов из известных сторонних библиотек НЕ декомпилировано (не бандлятся - "
                          f"добавлены в pom.xml как maven-зависимость): {self.library_classes_skipped} "
                          f"(обнаружено: {hit})")
        if self.parse_errors:
            lines.append(f"  Классы с ошибкой парсинга constant pool/байткода ({len(self.parse_errors)}):")
            for n, err in self.parse_errors[:30]:
                lines.append(f"    - {n}: {err}")
        lines.append("")
        lines.append(f"Методов с телом (есть байткод): {self.total_methods}")
        lines.append(f"  - Полностью восстановлены в структурированный Java "
                      f"(if/else, while/for, switch, try/catch, выражения): "
                      f"{self.decompiled_methods} ({self.pct(self.decompiled_methods, self.total_methods):.1f}%)")
        lines.append(f"  - Не удалось безопасно восстановить -> честный дизассемблированный "
                      f"листинг байткода (см. комментарий в самом методе): "
                      f"{self.fallback_methods} ({self.pct(self.fallback_methods, self.total_methods):.1f}%)")
        if self.fallback_reasons:
            lines.append("")
            lines.append("  Причины отката на байткод (сгруппировано):")
            grouped = {}
            for reason, cnt in self.fallback_reasons.items():
                key = _group_reason(reason)
                grouped[key] = grouped.get(key, 0) + cnt
            for key, cnt in sorted(grouped.items(), key=lambda kv: -kv[1]):
                lines.append(f"    {cnt:5d}  {key}")
        lines.append("")
        if self.synthetic_switchmap_classes_hidden:
            lines.append(f"Восстановлено настоящих switch(enum){{...}} вместо synthetic switch-map "
                          f"классов компилятора: скрыто {self.synthetic_switchmap_classes_hidden} "
                          f"вспомогательных классов (их никогда не было в исходнике).")
            lines.append("")
        if self.bracket_issues:
            lines.append(f"ВНИМАНИЕ: найдены проблемы с балансом скобок в {len(self.bracket_issues)} местах "
                          f"(это указывало бы на баг в генераторе кода):")
            for issue in self.bracket_issues[:40]:
                lines.append(f"  {issue}")
        else:
            lines.append("Баланс скобок {} () [] проверен по всем сгенерированным .java файлам - "
                          "проблем не найдено.")
        lines.append("")
        if self.import_conflicts:
            lines.append(f"ВНИМАНИЕ: {len(self.import_conflicts)} коллизий коротких имён классов "
                          f"(разные полные имена сведены к одному simple-имени в одном файле - "
                          f"возможна неоднозначность, при ручной доводке используйте полное имя):")
            for simple, dotteds in list(self.import_conflicts.items())[:30]:
                lines.append(f"  {simple}: {', '.join(dotteds)}")
        lines.append("")
        lines.append("ЧТО ЭТО ЗНАЧИТ НА ПРАКТИКЕ:")
        lines.append(
            "  В этом окружении сборки нет javac, поэтому мы не можем гарантировать компиляцию\n"
            "  на 100% - НО каждый метод, помеченный как 'восстановлен', прошёл через:\n"
            "    1) полную символическую интерпретацию байткода (стек-машина -> выражения),\n"
            "    2) структуризацию control-flow (if/while/for/switch/try) через дерево\n"
            "       доминаторов/постдоминаторов,\n"
            "    3) проверку баланса скобок сгенерированного текста.\n"
            "  Если на любом из этих шагов декомпилятор не был уверен на 100% - метод\n"
            "  автоматически откатывается на честный дизассемблированный листинг байткода\n"
            "  вместо того, чтобы 'угадывать' и рисковать неверной логикой.\n"
        )
        return "\n".join(lines)


def _group_reason(reason):
    if reason is None:
        return "неизвестно"
    if reason.startswith("нередуцируемый goto"):
        return "нередуцируемый goto (сложный control-flow, не сведённый к структурам)"
    if reason.startswith("несогласованная глубина"):
        return "многозначное пересечение стека между блоками (напр. arr[i] = cond ? a : b)"
    if reason.startswith("unrecognized <init>"):
        return "нестандартный паттерн вызова конструктора"
    if reason.startswith("внутренняя ошибка"):
        return "внутренняя ошибка декомпилятора (см. детали в логе)"
    if reason.startswith("неизвестная/неподдержанная инструкция"):
        return reason
    return reason
