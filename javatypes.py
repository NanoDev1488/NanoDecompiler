# -*- coding: utf-8 -*-
import re

_PRIMS = {
    "V": "void", "Z": "boolean", "B": "byte", "C": "char",
    "S": "short", "I": "int", "J": "long", "F": "float", "D": "double",
}


def dotted_from_internal(internal):
    """pkg/Outer$Inner -> pkg.Outer.Inner (валидная Java-ссылка на класс).
    Числовые сегменты (анонимные классы, Outer$1) оставляем приклеенными
    через $ - в исходном Java-коде сослаться на анонимный класс по имени
    синтаксически невозможно, точечная форма тут была бы ещё более неверной."""
    pkg, _, simple = internal.rpartition("/")
    segs = simple.split("$")
    pieces = [segs[0]]
    for s in segs[1:]:
        if s.isdigit():
            pieces[-1] = pieces[-1] + "$" + s
        else:
            pieces.append(s)
    result = ".".join(pieces)
    dotted_pkg = pkg.replace("/", ".")
    return f"{dotted_pkg}.{result}" if dotted_pkg else result


def _parse_one_type(desc, i):
    c = desc[i]
    if c in _PRIMS:
        return _PRIMS[c], i + 1
    if c == "L":
        j = desc.index(";", i)
        internal = desc[i + 1:j]
        return dotted_from_internal(internal), j + 1
    if c == "[":
        inner, ni = _parse_one_type(desc, i + 1)
        return inner + "[]", ni
    raise ValueError(f"Плохой дескриптор типа: {desc!r} at {i}")


TYPE_MARK_OPEN = "\x01"
TYPE_MARK_CLOSE = "\x02"


def mark_type(dotted):
    """Оборачивает dotted-имя типа в маркер-плейсхолдер вместо немедленного
    сведения к simple-имени. Нужно для отложенного (второй проход) решения
    об imports/FQN - см. resolve_type_markers() ниже и HANDOFF_STATUS_RU.md,
    пункт B.1 (коллизии simple-имён классов из разных пакетов, напр.
    org.bukkit.ChatColor vs net.md_5.bungee.api.ChatColor).
    Возвращает текст с маркером вокруг БАЗОВОГО типа (без "[]" - суффикс
    массива дописывается снаружи маркера как есть)."""
    if dotted is None or dotted == "":
        return "Object" if dotted is None else dotted
    arr = ""
    base = dotted
    while base.endswith("[]"):
        arr += "[]"
        base = base[:-2]
    return f"{TYPE_MARK_OPEN}{base}{TYPE_MARK_CLOSE}{arr}"


def resolve_type_markers(text, losers):
    """Финальный проход по уже собранному тексту класса: заменяет каждый
    mark_type()-маркер на:
      - simple-имя (обычный случай, тип импортирован под коротким именем)
      - полное dotted-имя (FQN), если тип входит в `losers` - т.е. проиграл
        коллизию simple-имён другому типу, который встретился в файле раньше
        (первый встреченный тип коллизии сохраняет `import X;` + short name,
        остальные печатаются как FQN и НЕ импортируются - см. render_class)."""
    out = []
    i = 0
    n = len(text)
    while i < n:
        ch = text[i]
        if ch == TYPE_MARK_OPEN:
            j = text.index(TYPE_MARK_CLOSE, i + 1)
            dotted = text[i + 1:j]
            if dotted in losers:
                out.append(dotted)
            else:
                out.append(dotted.rsplit(".", 1)[-1] if "." in dotted else dotted)
            i = j + 1
        else:
            out.append(ch)
            i += 1
    return "".join(out)


def field_descriptor_to_java(desc):
    t, _ = _parse_one_type(desc, 0)
    return t


def method_descriptor_to_java(desc):
    """returns (return_type_str, [param_type_str, ...])"""
    assert desc.startswith("(")
    i = 1
    params = []
    while desc[i] != ")":
        t, i = _parse_one_type(desc, i)
        params.append(t)
    ret, _ = _parse_one_type(desc, i + 1)
    return ret, params


# ---------------- obfuscation heuristics ----------------

VOWELS = set("aeiou")

JAVA_KEYWORDS = {
    "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
    "class", "const", "continue", "default", "do", "double", "else", "enum",
    "extends", "final", "finally", "float", "for", "goto", "if", "implements",
    "import", "instanceof", "int", "interface", "long", "native", "new",
    "package", "private", "protected", "public", "return", "short", "static",
    "strictfp", "super", "switch", "synchronized", "this", "throw", "throws",
    "transient", "try", "void", "volatile", "while", "true", "false", "null",
    "var", "record", "yield", "sealed", "permits",
}

_IDENT_RE = re.compile(r"^[A-Za-z_$][A-Za-z0-9_$]*$")


def is_safe_local_name(name):
    """Годится ли имя локальной переменной/параметра из LocalVariableTable для
    прямой подстановки в Java-текст: валидный идентификатор и не
    зарезервированное слово (обфускатор иногда генерирует отладочную таблицу
    с мусорными/невалидными именами - подстраховываемся)."""
    return bool(name) and bool(_IDENT_RE.match(name)) and name not in JAVA_KEYWORDS


def _consonant_run(name_lower):
    run = 0
    best = 0
    for ch in name_lower:
        if ch.isalpha() and ch not in VOWELS:
            run += 1
            best = max(best, run)
        else:
            run = 0
    return best


def looks_obfuscated(name, kind="class"):
    """
    Эвристика: похоже ли имя на сгенерированное обфускатором (случайный набор букв),
    а не на осмысленный человеческий идентификатор.
    kind: 'class' | 'method' | 'field' | 'package'

    ВАЖНО: намеренно консервативная эвристика (см. фидбек пользователя -
    реальные разработчики часто используют короткие/"дурацкие"/сокращённые
    имена, и старые пороги слишком часто путали их с обфускацией, портя
    вполне читаемый код). По умолчанию переименовываем только то, что
    выглядит как почти гарантированная обфускация (одна буква, либо длинный
    плоский нижнерегистровый набор букв без единой гласной закономерности) -
    остальное лучше оставить как есть, даже если оно кажется "странным".
    """
    if name is None:
        return False
    if name in ("<init>", "<clinit>"):
        return False

    base = name.rsplit("$", 1)[-1]  # для внутренних классов Foo$1 смотрим на последнюю часть

    # Однобуквенные top-level имена - классический сигнатурный признак
    # обфускатора (a.class, b()); всё остальное (в т.ч. 2-буквенные - могут
    # быть осмысленной аббревиатурой вроде "Io"/"Db") не флагаем по длине.
    if kind in ("class", "method", "field") and len(base) == 1:
        return True

    lower = base.lower()
    letters_only = re.sub(r"[^a-z]", "", lower)
    if len(letters_only) < 12:
        return False  # короткое/среднее имя - не флагаем, слишком велик риск ложного срабатывания

    has_upper = any(c.isupper() for c in base)
    has_digit = any(c.isdigit() for c in base)
    has_underscore = "_" in base

    vowel_ratio = sum(1 for c in letters_only if c in VOWELS) / len(letters_only)
    max_run = _consonant_run(letters_only)

    # Сигналы "случайности" - оставлены только КРАЙНИЕ случаи:
    flat_lower_long = (not has_upper) and (not has_digit) and (not has_underscore) and len(base) >= 12
    very_low_vowels = vowel_ratio < 0.20
    very_long_consonant_run = max_run >= 6

    if kind in ("class", "method", "field"):
        if flat_lower_long and (very_low_vowels or very_long_consonant_run):
            return True
        if flat_lower_long and len(base) >= 18:
            # очень длинные плоские нижнерегистровые идентификаторы без единой
            # заглавной буквы/цифры - почти наверняка обфускация, а не реальное
            # (пусть и длинное) человеческое имя
            return True

    if kind == "package":
        if flat_lower_long and len(base) >= 16:
            return True

    return False
