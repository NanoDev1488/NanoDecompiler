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


# ---------------- Signature-атрибут (дженерики), см. HANDOFF_3 п.4 -------
#
# Грамматика (упрощённо, JVMS §4.7.9.1 FieldTypeSignature) - разбирает
# ТОЛЬКО сигнатуру ПОЛЯ (`ClassSignature`/`MethodSignature` - с параметрами
# типа <T extends X> и сигнатурами параметров метода - сознательно не
# трогаем в этой версии, см. докстринг ниже) и текстовые аргументы
# generic-параметров, чтобы напечатать `List<String>` вместо голого
# `List`, если у поля/локальной переменной есть непустой атрибут
# Signature. Дженерики стираются JVM-ом на уровне байткода (type erasure) -
# это ЧИСТО косметическое обогащение отображаемого типа, ни на что другое
# не влияет и не может сломать саму декомпиляцию (если разобрать сигнатуру
# не удалось - тихо возвращаем None, вызывающий код просто использует
# обычный, не-generic тип дескриптора, как и раньше).
class _SignatureParseError(Exception):
    pass


class _FieldSigParser:
    def __init__(self, s):
        self.s = s
        self.i = 0
        self.n = len(s)

    def _peek(self):
        if self.i >= self.n:
            raise _SignatureParseError("неожиданный конец сигнатуры")
        return self.s[self.i]

    def parse_type(self):
        c = self._peek()
        if c == "L":
            return self._parse_class_type()
        if c == "T":
            return self._parse_type_var()
        if c == "[":
            self.i += 1
            return self.parse_type() + "[]"
        if c in _PRIMS:
            self.i += 1
            return _PRIMS[c]
        if c == "*":
            self.i += 1
            return "?"
        if c in "+-":
            wc = c
            self.i += 1
            inner = self.parse_type()
            return ("? extends " if wc == "+" else "? super ") + inner
        raise _SignatureParseError(f"неожиданный символ {c!r} в позиции {self.i}")

    def _parse_type_var(self):
        self.i += 1  # 'T'
        start = self.i
        while self._peek() != ";":
            self.i += 1
        name = self.s[start:self.i]
        self.i += 1  # ';'
        return name

    def _parse_class_type(self):
        self.i += 1  # 'L'
        start = self.i
        while self._peek() not in "<;.":
            self.i += 1
        internal = self.s[start:self.i]
        dotted = dotted_from_internal(internal)
        # См. докстринг parse_field_signature() ниже про то, почему тут
        # простое имя, а не полноценная интеграция с mark_type()/импортами.
        result = dotted.rsplit(".", 1)[-1] if "." in dotted else dotted
        if self._peek() == "<":
            result += self._parse_type_args()
        # вложенные классы вида Outer<T>.Inner<U> - редко, но встречается
        while self.i < self.n and self._peek() == ".":
            self.i += 1
            istart = self.i
            while self._peek() not in "<;.":
                self.i += 1
            iname = self.s[istart:self.i]
            result += "." + iname
            if self._peek() == "<":
                result += self._parse_type_args()
        if self._peek() != ";":
            raise _SignatureParseError("ожидался ';' в конце ClassTypeSignature")
        self.i += 1
        return result

    def _parse_type_args(self):
        self.i += 1  # '<'
        args = []
        while self._peek() != ">":
            args.append(self.parse_type())
        self.i += 1  # '>'
        return f"<{', '.join(args)}>"


def parse_field_signature(sig):
    """Разбирает Signature-атрибут ПОЛЯ (или локальной переменной из
    LocalVariableTypeTable - грамматика та же, FieldTypeSignature) в
    Java-подобную строку generic-типа, напр. `List<String>`.

    ВАЖНО - известное упрощение: типы generic-аргументов печатаются
    ПРОСТЫМ (неквалифицированным) именем напрямую, БЕЗ прогона через
    mark_type()/resolve_type_markers() (см. выше) и без регистрации
    в `all_imports` вызывающей стороны (main.py::render_class) - полная
    интеграция потребовала бы протащить renamer/known_internal_by_dotted/
    all_imports внутрь javatypes.py (сейчас renamer-агностичный модуль
    более низкого уровня) - отдельная, более крупная переделка. Практическое
    следствие: в РЕДКОМ случае, когда тип встречается в файле ТОЛЬКО внутри
    generic-аргумента (нигде больше не упоминается как обычный тип) - явный
    `import` для него может не сгенерироваться, и такой код придётся
    доимпортировать руками. Для базового/внешнего типа (напр. `List` в
    `List<String>`) обычный import-механизм по-прежнему работает как раньше -
    это чисто ограничение для типов ВНУТРИ уголковых скобок.

    None, если разобрать не удалось (пустой/непонятный сигнатуры не должны
    ронять декомпиляцию метода - вызывающий код в этом случае просто
    использует обычный дескриптор без дженериков, как было до этой фичи)."""
    if not sig:
        return None
    try:
        p = _FieldSigParser(sig)
        result = p.parse_type()
        if p.i != p.n:
            return None  # в конце остался "хвост" - разобрали не всё, не доверяем
        return result
    except _SignatureParseError:
        return None
    except Exception:
        return None


# ---------------- MethodTypeSignature / ClassSignature (JVMS §4.7.9.1) ---
#
# Расширяет _FieldSigParser парсингом параметров типа (`<T extends X>`),
# списка параметров метода, возвращаемого типа и (класс) списка
# суперкласса/интерфейсов - то, чего нет у голого FieldTypeSignature.
# throws-сигнатуры (`^TE;`) разбираются (чтобы не сбить курсор парсера),
# но НЕ используются при рендере - см. parse_method_signature().
class _FullSigParser(_FieldSigParser):
    def _read_ident(self):
        start = self.i
        while self.i < self.n and self.s[self.i] not in ":<;.[/()^":
            self.i += 1
        return self.s[start:self.i]

    def parse_type_params(self):
        """<T extends X & Y, U:Ljava/lang/Object;> - возвращает
        list[(name, [bound_str, ...])]. Пустой список, если параметров
        типа нет (нет '<' в текущей позиции)."""
        if self.i >= self.n or self._peek() != "<":
            return []
        self.i += 1
        params = []
        while self._peek() != ">":
            name = self._read_ident()
            bounds = []
            if self._peek() != ":":
                raise _SignatureParseError("ожидался ':' после имени параметра типа")
            self.i += 1  # первое ':' (ClassBound, может быть пустым)
            if self._peek() not in ":>":
                bounds.append(self.parse_type())
            while self._peek() == ":":  # InterfaceBound*
                self.i += 1
                bounds.append(self.parse_type())
            # Неявная граница Object (javac всегда её печатает explicitно,
            # но не всем интересно видеть "<T extends Object>" - опускаем
            # в отображении, если это ЕДИНСТВЕННАЯ граница).
            if bounds == ["Object"]:
                bounds = []
            params.append((name, bounds))
        self.i += 1  # '>'
        return params


def _format_type_params(type_params):
    if not type_params:
        return ""
    parts = []
    for name, bounds in type_params:
        parts.append(f"{name} extends {' & '.join(bounds)}" if bounds else name)
    return f"<{', '.join(parts)}>"


def parse_method_signature(sig):
    """Разбирает Signature-атрибут МЕТОДА (MethodTypeSignature) в dict:
    {"type_params": "<T>"|"", "param_types": [str, ...], "return_type": str}.
    None, если разобрать не удалось - вызывающий код (main.py) в этом
    случае просто использует обычные типы из дескриптора метода, как было
    до этой фичи (см. тот же принцип, что и у parse_field_signature() -
    сознательно не рискуем ронять рендер метода из-за кривой сигнатуры).

    throws-сигнатуры разбираются, чтобы не потерять позицию курсора при
    проверке "разобрали ли всё", но не возвращаются - typed throws
    (`<E extends Exception> void foo() throws E`) встречается крайне редко
    в декомпилируемых Bukkit-плагинах, а сам throws-список движок и так
    берёт из отдельного атрибута Exceptions (см. main.py), не из Signature."""
    if not sig:
        return None
    try:
        p = _FullSigParser(sig)
        type_params = p.parse_type_params()
        if p._peek() != "(":
            return None
        p.i += 1
        param_types = []
        while p._peek() != ")":
            param_types.append(p.parse_type())
        p.i += 1  # ')'
        if p._peek() == "V":
            p.i += 1
            ret = "void"
        else:
            ret = p.parse_type()
        while p.i < p.n and p._peek() == "^":
            p.i += 1
            p.parse_type()
        if p.i != p.n:
            return None
        return {
            "type_params": _format_type_params(type_params),
            "param_types": param_types,
            "return_type": ret,
        }
    except _SignatureParseError:
        return None
    except Exception:
        return None


def parse_class_signature(sig):
    """Разбирает Signature-атрибут КЛАССА (ClassSignature) в dict:
    {"type_params": "<T>"|"", "superclass": str, "interfaces": [str, ...]}.
    None при неудаче разбора (см. parse_method_signature())."""
    if not sig:
        return None
    try:
        p = _FullSigParser(sig)
        type_params = p.parse_type_params()
        superclass = p.parse_type()
        interfaces = []
        while p.i < p.n:
            interfaces.append(p.parse_type())
        return {
            "type_params": _format_type_params(type_params),
            "superclass": superclass,
            "interfaces": interfaces,
        }
    except _SignatureParseError:
        return None
    except Exception:
        return None


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

    if kind == "class":
        base = name.rsplit("$", 1)[-1]  # для внутренних классов Foo$1 смотрим на последнюю часть
    else:
        # "$" в имени МЕТОДА/ПОЛЯ - всегда синтетика компилятора (lambda$foo$0,
        # access$100, this$0 и т.п.), реальный разработчик "$" в имени не пишет.
        # Раньше здесь тоже резалось по rsplit("$",1) как для классов - для
        # "lambda$reload$0" это давало base="0" (длина 1) -> ложно считалось
        # обфускацией -> переименовывалось в бессмысленный "method7". Такие
        # синтетические имена и так уже валидные (и информативные) java-
        # идентификаторы - просто не трогаем их вообще.
        if "$" in name:
            return False
        base = name

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
