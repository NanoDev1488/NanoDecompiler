# -*- coding: utf-8 -*-
"""
Подсказки для переименования обфусцированных классов - НЕ угадывание, а
чтение уже существующих в байткоде данных (строк/аннотаций), которые
осмысленно называют класс. Идея и часть логики - с Rust-инструмента
пользователя (names.rs: moduleNames/commandNames), переписано на Python
под наши структуры данных (classfile.py).

1. by_annotation_name(): если МНОГО разных классов в jar'е несут одну и ту
   же (возможно, тоже обфусцированную по имени) аннотацию с текстовым
   аргументом "name" - это, скорее всего, самодельный DI/модульный
   фреймворк ("@Module(name=\"lobby\")" и т.п.) - берём это объявленное имя
   вместо генерического ClassA7.

2. by_brigadier_super_call(): специфично для Minecraft-плагинов на
   Brigadier (командный API Mojang, 1.13+) - командные классы обычно
   вызывают `super("имякоманды")` в конструкторе. Если проект использует
   Brigadier (есть упоминание в constant pool) и класс с обфусцированным
   именем расширяет ТОЖЕ обфусцированный класс, а его <init> начинается
   ровно с `aload_0; ldc "строка"; invokespecial <super>.<init>` - берём
   эту строку как имя команды.

Оба метода дают {internal_class_name: предложенное_имя} - используется
Renamer'ом (main.py) как ПОДСКАЗКА при переименовании, а не заменяет
обычную эвристику - если подсказки нет, поведение не меняется.
"""
import re

_VALID_IDENT_CHARS = re.compile(r"[^A-Za-z0-9_]")


def _sanitize_identifier(raw, prefix=""):
    """Превращает произвольную строку в валидный Java-идентификатор в
    PascalCase - 'teleport-player' -> 'TeleportPlayer', 'lobby' -> 'Lobby'."""
    parts = re.split(r"[^A-Za-z0-9]+", raw)
    parts = [p for p in parts if p]
    if not parts:
        return None
    name = "".join(p[:1].upper() + p[1:] for p in parts)
    name = _VALID_IDENT_CHARS.sub("", name)
    if not name or name[0].isdigit():
        name = "_" + name
    return prefix + name


def by_annotation_name(class_files, looks_obfuscated_fn):
    """cf.annotations - список {"type": descriptor, "args": {name: value}}
    (см. classfile.py). Считаем, сколько РАЗНЫХ классов несут каждый тип
    аннотации с аргументом "name" - если >= 3 разных классов (не случайное
    совпадение на одном классе), считаем это надёжным сигналом фреймворка."""
    by_annotation_type = {}  # descriptor -> list[(internal, name_value)]
    for internal, cf in class_files.items():
        for ann in getattr(cf, "annotations", []):
            args = ann.get("args", {})
            name_val = args.get("name")
            if isinstance(name_val, str) and name_val.strip():
                by_annotation_type.setdefault(ann.get("type"), []).append((internal, name_val))

    hints = {}
    for ann_type, entries in by_annotation_type.items():
        if len(entries) < 3:
            continue  # мало примеров - может быть случайность, не фреймворк
        for internal, name_val in entries:
            simple = internal.rsplit("/", 1)[-1]
            if not looks_obfuscated_fn(simple, "class"):
                continue  # и так нормальное имя, подсказка не нужна
            sanitized = _sanitize_identifier(name_val)
            if sanitized:
                hints[internal] = sanitized
    return hints


_BRIGADIER_MARKER = b"brigadier"

# aload_0; ldc|ldc_w <idx>; invokespecial <idx2>
_ALOAD_0 = 0x2a
_LDC = 0x12
_LDC_W = 0x13
_INVOKESPECIAL = 0xb7


def _project_uses_brigadier(class_files):
    for cf in class_files.values():
        for entry in cf.pool.values():
            if entry and entry[0] == "Utf8" and _BRIGADIER_MARKER in entry[1].lower().encode("utf-8", "ignore"):
                return True
    return False


def by_brigadier_super_call(class_files, looks_obfuscated_fn):
    if not _project_uses_brigadier(class_files):
        return {}

    hints = {}
    for internal, cf in class_files.items():
        simple = internal.rsplit("/", 1)[-1]
        if not looks_obfuscated_fn(simple, "class"):
            continue
        super_internal = cf.super_class_name
        if not super_internal:
            continue
        super_simple = super_internal.rsplit("/", 1)[-1]
        if not looks_obfuscated_fn(super_simple, "class"):
            continue  # суперкласс не выглядит обфусцированным - не похоже на командный базовый класс
        for m in cf.methods:
            if m.name != "<init>" or not m.code or len(m.code) < 6:
                continue
            code = m.code
            if code[0] != _ALOAD_0:
                continue
            if code[1] == _LDC:
                str_idx = code[2]
                pos = 3
            elif code[1] == _LDC_W:
                str_idx = (code[2] << 8) | code[3]
                pos = 4
            else:
                continue
            if pos + 3 > len(code) or code[pos] != _INVOKESPECIAL:
                continue
            invoke_idx = (code[pos + 1] << 8) | code[pos + 2]
            ref = cf.ref_string(invoke_idx)
            if ref is None or ref[0] != super_internal or ref[1] != "<init>":
                continue
            entry = cf.pool.get(str_idx)
            if not entry or entry[0] != "String":
                continue
            cmd_name = cf.utf8(entry[1])
            if not cmd_name:
                continue
            sanitized = _sanitize_identifier(cmd_name, prefix="Command")
            if sanitized:
                hints[internal] = sanitized
            break  # нашли <init>, дальше методы этого класса не смотрим
    return hints
