# -*- coding: utf-8 -*-
"""
NanoDecompiler v1.1

Декомпилер + деобфускатор Java/Bukkit-плагинов (.jar) с ПОЛНЫМ восстановлением
управляющей структуры (if/else, while/do-while/for, switch, try/catch) и
выражений - а не просто дизассемблированный листинг байткода.

Работает БЕЗ внешних зависимостей (только Python 3 stdlib) - запускается
одинаково на Windows и в Termux (Android):

    python3 main.py plugin.jar [output_dir]

На Windows без аргументов (напр. двойной клик по run.bat) открывается
простой Tkinter-GUI (см. gui.py); в Termux/Android/Linux - как обычно, через
командную строку.

Конвейер:
  1. Распаковывает jar, вручную парсит байткод каждого .class (constant pool,
     поля, методы, атрибут Code - без javap/ASM/CFR/Fernflower).
  2. Строит эвристическую карту деобфускации имён классов/методов/полей/
     пакетов (мусорные -> читаемые: ClassA1, method3, field7, pkg2...).
  3. Для КАЖДОГО метода: строит граф потока управления (CFG) из инструкций,
     символически исполняет байткод как стек-машину (восстанавливая
     арифметику, вызовы, new, касты, конкатенацию строк, инкременты и т.д.
     в виде дерева выражений), затем СТРУКТУРИРУЕТ управляющий поток через
     дерево доминаторов/постдоминаторов в if/else, while/do-while/for,
     switch, try/catch - без единого goto в норме.
  4. Если для конкретного метода декомпилятор не уверен на 100% (редкий,
     непривычный паттерн байткода) - он НЕ гадает, а откатывается на честный
     дизассемблированный листинг байткода именно для этого метода, оставляя
     явный комментарий. Остальные методы это не затрагивает.
  5. Прогоняет результат через набор проверок (verify.py): баланс скобок,
     статистика "сколько реально восстановлено / сколько safe-fallback",
     коллизии коротких имён классов.
  6. Кладёт рядом MAPPING_RU.txt (было -> стало), README_RU.txt (отчёт +
     статистика точности + результаты проверок) и восстановленный/
     сгенерированный pom.xml.
"""
import io
import os
import platform
import re
import shutil
import subprocess
import sys
import zipfile

sys.setrecursionlimit(20000)

NANO_DECOMPILER_VERSION = "NanoDecompiler v1.1"


def _enable_windows_ansi():
    """Включает поддержку ANSI-кодов (цвет) в классической cmd.exe -
    современные Windows 10/11 это умеют, просто нужно явно попросить через
    kernel32 (без этого консоль печатает escape-последовательности как есть,
    мусором). Чистый ctypes из stdlib, без внешних зависимостей."""
    if platform.system() != "Windows":
        return
    try:
        import ctypes
        kernel32 = ctypes.windll.kernel32
        h = kernel32.GetStdHandle(-11)
        mode = ctypes.c_uint32()
        if kernel32.GetConsoleMode(h, ctypes.byref(mode)):
            kernel32.SetConsoleMode(h, mode.value | 0x0004)  # ENABLE_VIRTUAL_TERMINAL_PROCESSING
    except Exception:
        pass


def _supports_color():
    return bool(getattr(sys.stdout, "isatty", lambda: False)())


def banner_text():
    """Аккуратная рамка в духе Claude Code CLI (ширина считается автоматически
    по тексту - не хардкодим отступы вручную, чтобы не поехало при правках).
    Цвет только если реально пишем в терминал (в GUI-логе sys.stdout подменён
    на очередь без isatty()==True - там банер печатается обычным текстом,
    без escape-мусора)."""
    lines = [
        "✻  NanoDecompiler v1.1",
        "   Java-декомпилятор/деобфускатор для Bukkit-плагинов",
    ]
    width = max(len(l) for l in lines)
    top = "╭" + "─" * (width + 2) + "╮"
    bot = "╰" + "─" * (width + 2) + "╯"
    mid = [f"│ {l.ljust(width)} │" for l in lines]
    plain = "\n".join([top, *mid, bot])
    if _supports_color():
        orange, reset = "\x1b[38;2;217;119;87m", "\x1b[0m"
        return "\n".join(orange + ln + reset for ln in plain.splitlines())
    return plain


def check_java_maven():
    """Проверяет, установлены ли java и mvn (нужны для следующего шага -
    mvn clean package) и печатает понятный статус + ссылки на скачивание,
    если чего-то не хватает. Ничего не устанавливает сама, только смотрит
    PATH через shutil.which - никаких внешних зависимостей не требуется."""
    print("[*] Проверка окружения (java / maven)...")
    missing = []
    _subprocess_kwargs = {}
    if platform.system() == "Windows":
        # Иначе при сборке в --windowed .exe (PyInstaller) на секунду
        # мелькало бы консольное окно дочернего процесса java/mvn.
        _subprocess_kwargs["creationflags"] = subprocess.CREATE_NO_WINDOW

    java_path = shutil.which("java")
    if java_path:
        try:
            r = subprocess.run(["java", "-version"], capture_output=True, text=True, timeout=10,
                                **_subprocess_kwargs)
            ver_line = (r.stderr or r.stdout or "").splitlines()[0] if (r.stderr or r.stdout) else "версия неизвестна"
        except Exception:
            ver_line = "версия неизвестна"
        print(f"    java:  найдена ({ver_line})")
    else:
        missing.append("java")
        print("    java:  НЕ НАЙДЕНА в PATH.")
        print("           Скачать (Eclipse Temurin JDK, бесплатно): https://adoptium.net/")

    mvn_path = shutil.which("mvn") or shutil.which("mvn.cmd")
    if mvn_path:
        try:
            r = subprocess.run(["mvn", "-version"], capture_output=True, text=True, timeout=15,
                                **_subprocess_kwargs)
            ver_line = (r.stdout or r.stderr or "").splitlines()[0] if (r.stdout or r.stderr) else "версия неизвестна"
        except Exception:
            ver_line = "версия неизвестна"
        print(f"    maven: найден ({ver_line})")
    else:
        missing.append("maven")
        print("    maven: НЕ НАЙДЕН в PATH.")
        print("           Скачать: https://maven.apache.org/download.cgi")
        print("           (после распаковки папку bin/ нужно добавить в PATH)")

    if not missing:
        print("[*] Всё готово к работе - можете делать с восстановленным плагином всё, что хотите.")
    else:
        print(f"[*] Не хватает: {', '.join(missing)}. Без этого не получится собрать проект "
              f"(mvn clean package) после декомпиляции - сама декомпиляция от этого не зависит "
              f"и пройдёт нормально.")
    return missing


from classfile import ClassFile, access_str
from disassembler import disassemble
from javatypes import (
    field_descriptor_to_java, method_descriptor_to_java, looks_obfuscated, dotted_from_internal,
    mark_type, resolve_type_markers,
)
from pom_builder import build_pom, KNOWN_LIBS
from engine import decompile_method_body, fallback_bytecode_listing
from verify import ProjectStats, check_brackets, check_import_collisions
from switchmap import detect_switchmaps
from stackvm import java_string_literal, java_float_literal
from ast_nodes import ExprStmt, Assign, FieldAccess, NewObject, ReturnStmt
from emit import emit_expr, emit_stmts

# Известные крупные сторонние библиотеки (таблица - pom_builder.KNOWN_LIBS),
# которые Java/Bukkit-плагины часто тащат внутри jar'а целиком
# (shaded/relocated зависимости - JDBC-драйверы, JSON/YAML, логирование,
# крипто, HTTP-клиенты и т.п.). Декомпилировать их в структурированный Java -
# трата времени/памяти И источник мусора в выдаче (имена там уже осмысленные -
# реальный опубликованный опенсорс, а не обфускация автора плагина), а маven
# и так способен закачать их сам как обычную зависимость по координатам -
# поэтому такие классы просто НЕ декомпилируются и не копируются в проект
# вообще (см. process_jar); ссылки на них из своего кода плагина попадают в
# pom.xml как <dependency> через ту же таблицу KNOWN_LIBS (build_pom).


def _known_library_coords(internal):
    """Если internal-имя класса подпадает под известную библиотеку - вернуть
    (dotted_prefix, groupId, artifactId), иначе None."""
    dotted = internal_to_dotted(internal)
    for prefix, coords in KNOWN_LIBS:
        if dotted == prefix or dotted.startswith(prefix + "."):
            return prefix, coords[0], coords[1]
    return None


class Renamer:
    def __init__(self):
        self.class_map = {}
        self.method_map = {}
        self.field_map = {}
        self.package_map = {}
        self._class_ctr = 0
        self._method_ctr = 0
        self._field_ctr = 0
        self._pkg_ctr = 0

    def friendly_class(self, internal_name):
        if internal_name in self.class_map:
            return self.class_map[internal_name]
        pkg, _, simple = internal_name.rpartition("/")
        new_pkg = self.friendly_package(pkg) if pkg else ""
        # Outer$Inner$1 (вложенные/анонимные классы) - разбираем по сегментам
        # и обрабатываем каждый отдельно: анонимным (числовым) сегментам даём
        # осмысленное имя, остальные - через обычную эвристику деобфускации.
        # "$" заменяем на "_", т.к. каждый .class у нас - отдельный
        # top-level файл, и "Outer.Inner" физически не резолвился бы.
        parts = simple.split("$")
        new_parts = []
        for p in parts:
            if p.isdigit():
                new_parts.append(f"Anon{p}")
            elif looks_obfuscated(p, "class"):
                self._class_ctr += 1
                new_parts.append(f"ClassA{self._class_ctr}")
            else:
                new_parts.append(p)
        new_simple = "_".join(new_parts)
        new_internal = f"{new_pkg}/{new_simple}" if new_pkg else new_simple
        self.class_map[internal_name] = new_internal
        return new_internal

    def friendly_package(self, pkg_internal):
        if pkg_internal in self.package_map:
            return self.package_map[pkg_internal]
        parts = pkg_internal.split("/")
        new_parts = []
        for p in parts:
            if looks_obfuscated(p, "package"):
                self._pkg_ctr += 1
                new_parts.append(f"pkg{self._pkg_ctr}")
            else:
                new_parts.append(p)
        new_pkg = "/".join(new_parts)
        self.package_map[pkg_internal] = new_pkg
        return new_pkg

    def friendly_method(self, owner_internal, name, desc):
        key = (owner_internal, name, desc)
        if key in self.method_map:
            return self.method_map[key]
        if looks_obfuscated(name, "method"):
            self._method_ctr += 1
            new_name = f"method{self._method_ctr}"
        else:
            new_name = name
        self.method_map[key] = new_name
        return new_name

    def friendly_field(self, owner_internal, name, desc):
        key = (owner_internal, name, desc)
        if key in self.field_map:
            return self.field_map[key]
        if looks_obfuscated(name, "field"):
            self._field_ctr += 1
            new_name = f"field{self._field_ctr}"
        else:
            new_name = name
        self.field_map[key] = new_name
        return new_name


def internal_to_dotted(internal):
    return dotted_from_internal(internal) if internal else internal


def format_type_dotted(java_type, renamer, known_internal_by_dotted, all_imports=None):
    base = java_type
    arr = ""
    while base.endswith("[]"):
        arr += "[]"
        base = base[:-2]
    if base in known_internal_by_dotted:
        internal = known_internal_by_dotted[base]
        base = internal_to_dotted(renamer.friendly_class(internal))
        if all_imports is not None:
            all_imports.setdefault(base, base.rsplit(".", 1)[-1])
    elif all_imports is not None and "." in base:
        if not (base.startswith("java.lang.") and "." not in base[len("java.lang."):]):
            all_imports.setdefault(base, base.rsplit(".", 1)[-1])
    return base + arr


def process_jar(jar_path, out_dir):
    _enable_windows_ansi()
    print()
    print(banner_text())
    print()
    check_java_maven()
    os.makedirs(out_dir, exist_ok=True)
    src_dir = os.path.join(out_dir, "src", "main", "java")
    os.makedirs(src_dir, exist_ok=True)

    stats = ProjectStats()
    class_files = {}
    parse_errors = []
    plugin_yml_text = None

    with zipfile.ZipFile(jar_path) as z:
        all_names = z.namelist()
        names = [n for n in all_names if n.endswith(".class") and "module-info" not in n]
        stats.classes_total = len(names)
        print(f"[*] Найдено {len(names)} .class файлов")
        for n in names:
            try:
                data = z.read(n)
                cf = ClassFile(data=data)
                class_files[cf.this_class_name] = cf
            except Exception as e:
                parse_errors.append((n, str(e)))
        stats.classes_parsed = len(class_files)
        stats.parse_errors = parse_errors

        # Известные крупные сторонние библиотеки (см. pom_builder.KNOWN_LIBS) -
        # НЕ декомпилируем и НЕ копируем байткод внутрь проекта: maven и так
        # закачает их сам как обычную зависимость, если добавить в pom.xml -
        # то есть просто убираем их из class_files. Раз они удалены отсюда ДО
        # сканирования external_dotted ниже, любая ссылка на них из СВОЕГО кода
        # плагина (import/использование) автоматически попадёт в
        # external_dotted -> build_pom() сам добавит <dependency> по таблице
        # KNOWN_LIBS (см. pom_builder.py).
        library_internal_names = []
        library_hit_labels = set()
        for k in list(class_files):
            hit = _known_library_coords(k)
            if hit:
                _prefix, g, a = hit
                library_internal_names.append(k)
                library_hit_labels.add(f"{g}:{a}")
                del class_files[k]
        if library_internal_names:
            stats.library_classes_skipped = len(library_internal_names)
            stats.library_names_hit = library_hit_labels
            print(f"[*] Известных библиотечных классов пропущено (не декомпилируются, "
                  f"будут подтянуты maven'ом как зависимость): "
                  f"{len(library_internal_names)} ({', '.join(sorted(library_hit_labels))})")

        res_dir = os.path.join(out_dir, "src", "main", "resources")
        for n in all_names:
            if n.endswith(".class") or n.endswith("/"):
                continue
            try:
                data = z.read(n)
            except Exception:
                continue
            dest = os.path.join(res_dir, n)
            os.makedirs(os.path.dirname(dest), exist_ok=True)
            with open(dest, "wb") as f:
                f.write(data)
            if n == "plugin.yml":
                try:
                    plugin_yml_text = data.decode("utf-8", errors="replace")
                except Exception:
                    plugin_yml_text = None

        print(f"[*] Успешно распарсено классов: {len(class_files)}; ошибок парсинга: {len(parse_errors)}")

        known_internal_by_dotted = {internal_to_dotted(k): k for k in class_files}

        external_dotted = set()
        for internal, cf in class_files.items():
            for entry in cf.pool.values():
                if entry[0] == "Class":
                    name_idx = entry[1]
                    cname = cf.utf8(name_idx)
                    if cname and cname not in class_files and not cname.startswith("["):
                        external_dotted.add(internal_to_dotted(cname))

        pom_text, pom_kind = build_pom(jar_path, plugin_yml_text, external_dotted, all_names, z)
        pom_dest = os.path.join(out_dir, "pom.xml")
        with open(pom_dest, "w", encoding="utf-8") as f:
            f.write(pom_text)
        print(f"[*] pom.xml ({'найден оригинал' if pom_kind == 'original' else 'сгенерирован по эвристике'}): {pom_dest}")

    renamer = Renamer()

    for internal, cf in class_files.items():
        renamer.friendly_class(internal)
        for f in cf.fields:
            renamer.friendly_field(internal, f.name, f.descriptor)
        for m in cf.methods:
            renamer.friendly_method(internal, m.name, m.descriptor)

    enum_ordinals = {}
    for internal, cf in class_files.items():
        if not (cf.access & 0x4000):
            continue
        own_desc = f"L{internal};"
        names = []
        for f in cf.fields:
            if (f.access & 0x4000) and f.descriptor == own_desc:
                names.append(renamer.field_map.get((internal, f.name, f.descriptor), f.name))
        if names:
            enum_ordinals[internal] = names

    switchmap_fields, synthetic_switchmap_classes = detect_switchmaps(class_files)
    switchmap_tables = {}
    for (owner_internal, field_name), info in switchmap_fields.items():
        owner_cf = class_files.get(owner_internal)
        new_owner_dotted = internal_to_dotted(renamer.friendly_class(owner_internal))
        new_field_name = renamer.field_map.get((owner_internal, field_name, "[I"), field_name)
        enum_owner_internal = info["enum_owner"]
        table = {}
        for val, const_orig_name in info["table"].items():
            const_desc = f"L{enum_owner_internal};"
            table[val] = renamer.field_map.get((enum_owner_internal, const_orig_name, const_desc), const_orig_name)
        switchmap_tables[(new_owner_dotted, new_field_name)] = table
    if synthetic_switchmap_classes:
        print(f"[*] Найдено и свёрнуто synthetic switch-map классов (switch-on-enum): {len(synthetic_switchmap_classes)}")

    all_imports = {}
    for internal, cf in class_files.items():
        if internal in synthetic_switchmap_classes:
            # чисто компиляторский артефакт switch-on-enum - в исходнике его
            # никогда не было, use-места уже восстановлены в нормальный switch
            continue
        try:
            text, cls_imports = render_class(cf, renamer, known_internal_by_dotted, stats,
                                              enum_ordinals, switchmap_tables)
        except Exception as e:
            text = f"// ОШИБКА рендеринга класса {internal}: {type(e).__name__}: {e}\n"
            cls_imports = {}
        all_imports.update(cls_imports)
        new_internal = renamer.friendly_class(internal)
        rel_path = new_internal + ".java"
        dest = os.path.join(src_dir, rel_path)
        os.makedirs(os.path.dirname(dest), exist_ok=True)
        with open(dest, "w", encoding="utf-8") as f:
            f.write(text)
        stats.bracket_issues.extend(check_brackets(text, rel_path))

    stats.import_conflicts = check_import_collisions(all_imports)
    stats.synthetic_switchmap_classes_hidden = len(synthetic_switchmap_classes)

    write_mapping_report(out_dir, renamer)
    write_readme(out_dir, jar_path, len(class_files), parse_errors, class_files, renamer, stats)
    return out_dir


def render_class(cf, renamer, known_internal_by_dotted, stats, enum_ordinals, switchmap_tables=None):
    internal = cf.this_class_name
    new_internal = renamer.friendly_class(internal)
    pkg, _, simple = new_internal.rpartition("/")

    if internal.rsplit("/", 1)[-1] == "package-info":
        # package-info.java - служебный синтетический класс, который javac
        # генерирует под package-level Javadoc/аннотации. Это НЕ настоящий
        # класс/интерфейс - `interface package-info {}` невалиден как Java-код
        # (найдено на реальном плагине пользователя: "<identifier> expected").
        # В исходнике package-info.java состоит ТОЛЬКО из (опционально)
        # package-level аннотаций + `package x.y.z;`. RuntimeVisibleAnnotations
        # этот парсер не читает (см. HANDOFF - Signature/аннотации вне scope),
        # поэтому аннотации теряются, но это безопаснее компиляционной ошибки.
        lines = [f"// исходный (обфусцированный) внутренний класс: {internal_to_dotted(internal)}"]
        if pkg:
            lines.append(f"package {pkg.replace('/', '.')};")
        return "\n".join(lines) + "\n", {}

    lines = []
    lines.append(f"// исходный (обфусцированный) внутренний класс: {internal_to_dotted(internal)}")
    if pkg:
        lines.append(f"package {pkg.replace('/', '.')};")

    is_interface = bool(cf.access & 0x0200)
    is_enum = bool(cf.access & 0x4000)
    is_annotation = bool(cf.access & 0x2000)
    kind = "@interface" if is_annotation else ("interface" if is_interface else ("enum" if is_enum else "class"))

    mod_bits = []
    if cf.access & 0x0001: mod_bits.append("public")
    if cf.access & 0x0010 and not is_enum: mod_bits.append("final")
    if cf.access & 0x0400 and not is_interface and not is_enum: mod_bits.append("abstract")
    mods = " ".join(mod_bits)

    mod_bits = []
    if cf.access & 0x0001: mod_bits.append("public")
    if cf.access & 0x0010 and not is_enum: mod_bits.append("final")
    if cf.access & 0x0400 and not is_interface and not is_enum: mod_bits.append("abstract")
    mods = " ".join(mod_bits)

    all_imports = {}

    header = f"{mods} {kind} {simple}".replace("  ", " ").strip()
    if cf.super_class_name and cf.super_class_name != "java/lang/Object" and \
            not is_interface and not is_enum and cf.super_class_name != "java/lang/Enum":
        super_disp = format_type_dotted(internal_to_dotted(cf.super_class_name), renamer,
                                         known_internal_by_dotted, all_imports)
        header += f" extends {_simple_type(super_disp)}"
    if cf.interfaces:
        iface_strs = []
        for iface in cf.interfaces:
            d = format_type_dotted(internal_to_dotted(iface), renamer, known_internal_by_dotted, all_imports)
            iface_strs.append(_simple_type(d))
        kw = "extends" if is_interface else "implements"
        header += f" {kw} " + ", ".join(iface_strs)
    header += " {"

    body_lines = []

    own_field_type_desc = f"L{internal};"
    enum_const_fields = [f for f in cf.fields if is_enum and (f.access & 0x4000) and f.descriptor == own_field_type_desc]
    other_fields = [f for f in cf.fields if f not in enum_const_fields and
                     not (is_enum and f.name == "$VALUES" and (f.access & 0x1000))]

    if is_enum and enum_const_fields:
        const_args = {}
        clinit = next((m for m in cf.methods if m.name == "<clinit>"), None)
        leftover_clinit_stmts = None
        synthetic_field_names = {renamer.field_map.get((internal, f.name, f.descriptor), f.name)
                                  for f in cf.fields if (f.access & 0x1000)}
        if clinit is not None and clinit.code is not None:
            cres = decompile_method_body(cf, clinit, renamer, known_internal_by_dotted, internal, indent=2, enum_ordinals=enum_ordinals, switchmap_tables=switchmap_tables)
            if cres.ok and cres.stmts is not None:
                const_names = {renamer.field_map.get((internal, f.name, f.descriptor), f.name): f
                                for f in enum_const_fields}
                remaining = []
                ok_extract = True
                for st in cres.stmts:
                    matched = False
                    if isinstance(st, ExprStmt) and isinstance(st.expr, Assign):
                        tgt = st.expr.target
                        val = st.expr.value
                        if isinstance(tgt, FieldAccess) and tgt.static and tgt.target is None:
                            if tgt.name in const_names and isinstance(val, NewObject) and \
                                    val.type.rsplit(".", 1)[-1] == simple:
                                args_txt = ", ".join(emit_expr(a) for a in val.args[2:])
                                const_args[tgt.name] = args_txt
                                matched = True
                            elif tgt.name in synthetic_field_names:
                                matched = True  # $VALUES = ...; - неявно генерируется Java
                    if not matched:
                        remaining.append(st)
                leftover_clinit_stmts = remaining
        names = []
        for f in enum_const_fields:
            fname = renamer.field_map.get((internal, f.name, f.descriptor), f.name)
            args_txt = const_args.get(fname, "")
            names.append(f"{fname}({args_txt})" if args_txt else fname)
        body_lines.append("    " + ",\n    ".join(names) + ";")
        body_lines.append("")
    else:
        leftover_clinit_stmts = None

    # Интерфейсы НЕ МОГУТ иметь static{} блок (JLS: "initializers not allowed
    # in interfaces" - реальная ошибка javac, найденная на плагине пользователя,
    # Actions.java). Единственный валидный способ проинициализировать
    # static-константу интерфейса - inline-инициализатор в самом поле. Поэтому
    # для интерфейсов декомпилируем <clinit> ЗАРАНЕЕ (до печати полей ниже) и
    # пытаемся разложить его на простые прямые присваивания `Class.FIELD = expr;`
    # -> инициализатор соответствующего поля. Если <clinit> не сводится к такому
    # простому виду (циклы, временные переменные и т.п.) - честно не гадаем,
    # оставляем комментарий-предупреждение вместо невалидного/неверного кода.
    interface_field_inits = {}
    interface_clinit_complex = False
    if is_interface and not is_enum:
        _iface_clinit = next((m for m in cf.methods if m.name == "<clinit>"), None)
        if _iface_clinit is not None and _iface_clinit.code is not None:
            _cres = decompile_method_body(cf, _iface_clinit, renamer, known_internal_by_dotted, internal,
                                           indent=2, enum_ordinals=enum_ordinals, switchmap_tables=switchmap_tables)
            if _cres.ok and _cres.stmts is not None:
                _stmts = _cres.stmts
                if _stmts and isinstance(_stmts[-1], ReturnStmt) and _stmts[-1].expr is None:
                    _stmts = _stmts[:-1]
                _own_field_names = {renamer.field_map.get((internal, f.name, f.descriptor), f.name)
                                     for f in other_fields}
                _all_simple = True
                for st in _stmts:
                    matched = False
                    if isinstance(st, ExprStmt) and isinstance(st.expr, Assign):
                        tgt = st.expr.target
                        if isinstance(tgt, FieldAccess) and tgt.static and tgt.target is None and \
                                tgt.name in _own_field_names and tgt.name not in interface_field_inits:
                            interface_field_inits[tgt.name] = emit_expr(st.expr.value)
                            matched = True
                    if not matched:
                        _all_simple = False
                        break
                if _all_simple and interface_field_inits:
                    all_imports.update(_cres.ctx.imports if _cres.ctx else {})
                else:
                    interface_field_inits = {}
                    interface_clinit_complex = True
            else:
                interface_clinit_complex = True

    if other_fields:
        body_lines.append("    // ---- поля ----")
    for f in other_fields:
        fmods = access_str(f.access, "field")
        try:
            jtype = field_descriptor_to_java(f.descriptor)
            jtype = format_type_dotted(jtype, renamer, known_internal_by_dotted, all_imports)
        except Exception:
            jtype = f.descriptor
        fname = renamer.field_map.get((internal, f.name, f.descriptor), f.name)
        renamed_note = "" if fname == f.name else f"  // было: {f.name}"
        if fname in interface_field_inits:
            cv = f" = {interface_field_inits[fname]}"
        else:
            literal = format_field_constant(cf, f.constant_value, f.descriptor)
            cv = f" = {literal}" if literal is not None else ""
        body_lines.append(f"    {fmods} {_simple_type(jtype)} {fname}{cv};{renamed_note}".replace("  ", " "))
        if "." in jtype:
            all_imports.setdefault(jtype.rstrip("[]"), jtype.rstrip("[]").rsplit(".", 1)[-1])
    if other_fields:
        body_lines.append("")

    skip_methods = set()
    clinit_m = next((m for m in cf.methods if m.name == "<clinit>"), None)
    if clinit_m is not None:
        skip_methods.add(id(clinit_m))
        if clinit_m.code is not None and is_interface and not is_enum:
            # Уже обработано выше (interface_field_inits) - static{} для
            # интерфейса не печатаем ни в каком виде (невалидно всегда).
            stats.total_methods += 1
            if interface_field_inits and not interface_clinit_complex:
                stats.decompiled_methods += 1
            else:
                stats.fallback_methods += 1
                stats.fallback_reasons["<clinit> интерфейса требует ручной правки (static{} невозможен в interface)"] = \
                    stats.fallback_reasons.get("<clinit> интерфейса требует ручной правки (static{} невозможен в interface)", 0) + 1
                body_lines.append("    // ВНИМАНИЕ: static-инициализатор этого интерфейса не удалось безопасно")
                body_lines.append("    // разложить по полям (interface не может иметь блок static{} - JLS).")
                body_lines.append("    // Нужна РУЧНАЯ доводка полей выше по дизассемблированному листингу метода <clinit>.")
                body_lines.append("")
        elif clinit_m.code is not None:
            if is_enum and enum_const_fields:
                static_stmts = leftover_clinit_stmts
                static_ctx_imports = {}
            else:
                cres2 = decompile_method_body(cf, clinit_m, renamer, known_internal_by_dotted, internal, indent=2, enum_ordinals=enum_ordinals, switchmap_tables=switchmap_tables)
                if cres2.ok:
                    static_stmts = cres2.stmts
                    static_ctx_imports = cres2.ctx.imports if cres2.ctx else {}
                else:
                    static_stmts = None
                    static_ctx_imports = {}
            if static_stmts is not None:
                if static_stmts and isinstance(static_stmts[-1], ReturnStmt) and static_stmts[-1].expr is None:
                    static_stmts = static_stmts[:-1]
                all_imports.update(static_ctx_imports)
                if static_stmts:
                    body_lines.append("    static {")
                    body_lines.extend(emit_stmts(static_stmts, 2))
                    body_lines.append("    }")
                    body_lines.append("")
                stats.total_methods += 1
                stats.decompiled_methods += 1
            else:
                stats.total_methods += 1
                stats.fallback_methods += 1
                stats.fallback_reasons["<clinit> (static-инициализатор)"] = \
                    stats.fallback_reasons.get("<clinit> (static-инициализатор)", 0) + 1
                body_lines.append("    static {")
                body_lines.extend(fallback_bytecode_listing(cf, clinit_m, indent=2))
                body_lines.append("    }")
                body_lines.append("")

    if is_enum:
        for m in cf.methods:
            if m.name == "values" and m.descriptor == f"()[{own_field_type_desc}":
                skip_methods.add(id(m))
            elif m.name == "valueOf" and m.descriptor == f"(Ljava/lang/String;){own_field_type_desc}":
                skip_methods.add(id(m))
            elif m.name == "$values" and (m.access & 0x1000):
                skip_methods.add(id(m))

    for m in cf.methods:
        if id(m) in skip_methods:
            continue
        mmods = access_str(m.access, "method")
        try:
            ret, params = method_descriptor_to_java(m.descriptor)
            ret_disp = format_type_dotted(ret, renamer, known_internal_by_dotted, all_imports)
            params_disp = [format_type_dotted(p, renamer, known_internal_by_dotted, all_imports) for p in params]
        except Exception:
            ret_disp, params_disp = m.descriptor, []
        mname = renamer.method_map.get((internal, m.name, m.descriptor), m.name)
        is_enum_ctor = is_enum and m.name == "<init>"
        arg_offset = 0
        if m.name == "<init>":
            mname = simple
            ret_disp = ""
            if is_enum_ctor:
                params_disp = params_disp[2:]
                arg_offset = 2
        renamed_note = "" if mname == m.name else f"  // было: {m.name}"
        param_str = ", ".join(f"{_simple_type(p)} arg{i + arg_offset}" for i, p in enumerate(params_disp))
        sig = f"    {mmods} {_simple_type(ret_disp)} {mname}({param_str}) {{{renamed_note}".replace("  ", " ")
        body_lines.append(sig)

        if m.code is not None:
            stats.total_methods += 1
            result = decompile_method_body(cf, m, renamer, known_internal_by_dotted, internal, indent=2, enum_ordinals=enum_ordinals, switchmap_tables=switchmap_tables)
            if result.ok:
                stats.decompiled_methods += 1
                out_stmts = result.stmts
                if is_enum_ctor and out_stmts and isinstance(out_stmts[0], ExprStmt) and \
                        isinstance(out_stmts[0].expr, __import__("ast_nodes").MethodCall) and \
                        out_stmts[0].expr.is_ctor:
                    # неявный super(name, ordinal) enum-конструктора - в исходнике
                    # недоступен и не пишется явно, компилятор вставляет его сам
                    out_stmts = out_stmts[1:]
                    rendered = result.pre_lines + emit_stmts(out_stmts, 2)
                    body_lines.extend(rendered if rendered else ["        // (пустое тело)"])
                else:
                    body_lines.extend(result.java_lines if result.java_lines else ["        // (пустое тело)"])
                if result.ctx is not None:
                    all_imports.update(result.ctx.imports)
            else:
                stats.fallback_methods += 1
                stats.fallback_reasons[result.reason] = stats.fallback_reasons.get(result.reason, 0) + 1
                body_lines.extend(fallback_bytecode_listing(cf, m, indent=2))
        else:
            body_lines.append("        // abstract / native - тела нет")
        body_lines.append("    }")
        body_lines.append("")

    own_dotted = internal_to_dotted(new_internal)

    # Коллизии simple-имён (напр. org.bukkit.ChatColor и net.md_5.bungee.api.ChatColor
    # в одном файле): первый ВСТРЕЧЕННЫЙ (порядок вставки в all_imports, т.е. порядок
    # появления в исходном коде класса) тип сохраняет "import X;" + короткое имя,
    # остальные типы с тем же простым именем - "проигравшие" (losers): им НЕ
    # печатается import, а везде в теле класса они разворачиваются в FQN (полное
    # dotted-имя) на финальном проходе resolve_type_markers(). Это устраняет
    # javac-ошибки "reference to X is ambiguous" / "already defined by
    # single-type-import" (см. HANDOFF_STATUS_RU.md, пункт B.1).
    by_simple = {}
    for dotted, simple_name in all_imports.items():
        by_simple.setdefault(simple_name, []).append(dotted)
    losers = set()
    for simple_name, dotted_list in by_simple.items():
        if len(dotted_list) > 1:
            for d in dotted_list[1:]:
                losers.add(d)

    import_lines = []
    for dotted, simple_name in sorted(all_imports.items()):
        if dotted.startswith("java.lang.") and "." not in dotted[len("java.lang."):]:
            continue
        if dotted == own_dotted:
            continue
        if "." not in dotted:
            continue
        if dotted in losers:
            continue
        import_lines.append(f"import {dotted};")

    lines.append("")
    if import_lines:
        lines.extend(import_lines)
        lines.append("")
    lines.append(header)
    lines.append("")
    lines.extend(body_lines)
    lines.append("}")
    text = resolve_type_markers("\n".join(lines), losers)
    return text, all_imports


def _simple_type(dotted):
    # Отложенное разрешение - см. javatypes.mark_type()/resolve_type_markers()
    # и комментарий в render_class про коллизии imports (пункт B.1).
    return mark_type(dotted)


def format_field_constant(cf, entry, descriptor):
    """entry - сырая constant-pool запись (tag, ...), как её отдаёт
    classfile.py. Форматирует в ВАЛИДНЫЙ Java-литерал по типу дескриптора
    поля (в частности: String -> двойные кавычки, char -> одинарные,
    boolean -> true/false, а не голый repr() значения, который для строк
    в Python по умолчанию даёт ОДИНАРНЫЕ кавычки - невалидно для Java)."""
    if entry is None:
        return None
    tag = entry[0]
    if tag == "String":
        s = cf.utf8(entry[1])
        return java_string_literal(s if s is not None else "")
    if tag == "Integer":
        v = entry[1]
        if descriptor == "Z":
            return "true" if v else "false"
        if descriptor == "C":
            ch = chr(v & 0xFFFF)
            if ch == "'":
                return "'\\''"
            if ch == "\\":
                return "'\\\\'"
            if ch == "\n":
                return "'\\n'"
            if ch == "\t":
                return "'\\t'"
            if ch == "\r":
                return "'\\r'"
            if ord(ch) < 0x20 or ord(ch) == 0x7f:
                return f"'\\u{ord(ch):04x}'"
            return f"'{ch}'"
        return str(v)
    if tag == "Float":
        return java_float_literal(float(entry[1]), "f")
    if tag == "Long":
        return f"{entry[1]}L"
    if tag == "Double":
        return java_float_literal(float(entry[1]), "")
    return None


def write_mapping_report(out_dir, renamer):
    path = os.path.join(out_dir, "MAPPING_RU.txt")
    with io.open(path, "w", encoding="utf-8") as f:
        f.write("Отчёт деобфускации: что было переименовано\n")
        f.write("=" * 60 + "\n\n")
        f.write("--- Пакеты ---\n")
        for old, new in renamer.package_map.items():
            if old != new:
                f.write(f"  {old or '(default)'}  ->  {new or '(default)'}\n")
        f.write("\n--- Классы ---\n")
        for old, new in renamer.class_map.items():
            if old != new:
                f.write(f"  {internal_to_dotted(old)}  ->  {internal_to_dotted(new)}\n")
        f.write("\n--- Методы ---\n")
        for (owner, name, desc), new_name in renamer.method_map.items():
            if new_name != name:
                f.write(f"  {internal_to_dotted(owner)}.{name}{desc}  ->  {new_name}\n")
        f.write("\n--- Поля ---\n")
        for (owner, name, desc), new_name in renamer.field_map.items():
            if new_name != name:
                f.write(f"  {internal_to_dotted(owner)}.{name}:{desc}  ->  {new_name}\n")
    print(f"[*] Отчёт деобфускации: {path}")


def write_readme(out_dir, jar_path, n_classes, parse_errors, class_files, renamer, stats):
    path = os.path.join(out_dir, "README_RU.txt")
    with io.open(path, "w", encoding="utf-8") as f:
        f.write(f"Результат разбора: {os.path.basename(jar_path)}\n")
        f.write(f"Классов успешно разобрано: {n_classes}\n")
        f.write(f"Ошибок парсинга: {len(parse_errors)}\n")
        if parse_errors:
            f.write("\nКлассы, которые не удалось разобрать:\n")
            for n, err in parse_errors:
                f.write(f"  {n}: {err}\n")

        total_methods = sum(len(cf.methods) for cf in class_files.values())
        total_fields = sum(len(cf.fields) for cf in class_files.values())
        renamed_classes = sum(1 for old, new in renamer.class_map.items() if old != new)
        renamed_methods = sum(1 for k, v in renamer.method_map.items() if v != k[1])
        renamed_fields = sum(1 for k, v in renamer.field_map.items() if v != k[1])
        f.write("\n" + "=" * 60 + "\n")
        f.write("СТАТИСТИКА ДЕОБФУСКАЦИИ ИМЁН\n")
        f.write("(эвристика, см. javatypes.py: looks_obfuscated - может как пропустить,\n"
                "так и переименовать нормальное имя по ошибке; проверяйте MAPPING_RU.txt)\n\n")
        f.write(f"  Классов всего: {n_classes}, переименовано: {renamed_classes} "
                f"({renamed_classes/max(n_classes,1)*100:.1f}%)\n")
        f.write(f"  Методов всего: {total_methods}, переименовано: {renamed_methods} "
                f"({renamed_methods/max(total_methods,1)*100:.1f}%)\n")
        f.write(f"  Полей всего: {total_fields}, переименовано: {renamed_fields} "
                f"({renamed_fields/max(total_fields,1)*100:.1f}%)\n")

        f.write("\n" + stats.summary_text() + "\n")

        f.write("\n" + "=" * 60 + "\n")
        f.write(
            "ЧТО РЕАЛЬНО ДЕЛАЕТ ЭТОТ ИНСТРУМЕНТ:\n\n"
            "  - Парсит constant pool, поля, методы, атрибут Code, BootstrapMethods,\n"
            "    InnerClasses - вручную, по спецификации JVM (свой парсер).\n"
            "  - Для каждого метода: строит CFG, символически исполняет байткод как\n"
            "    стек-машину (арифметика, вызовы, new/anewarray, касты, инкременты,\n"
            "    конкатенация строк через StringBuilder/invokedynamic, лямбды через\n"
            "    LambdaMetafactory) и СТРУКТУРИРУЕТ управляющий поток через дерево\n"
            "    доминаторов/постдоминаторов в if/else, while/do-while/for, switch,\n"
            "    try/catch - настоящий Java-код, а не листинг байткода.\n"
            "  - Если конкретный метод не удаётся восстановить с полной уверенностью -\n"
            "    он НЕ гадает: откатывается на честный дизассемблированный листинг\n"
            "    именно для этого метода (см. статистику выше), остальные методы это\n"
            "    не затрагивает.\n"
            "  - Эвристически деобфусцирует имена классов/методов/полей/пакетов,\n"
            "    переименовывая согласованно по всему проекту.\n\n"
            "ЧЕСТНО О ГРАНИЦАХ:\n"
            "  - В окружении сборки нет javac, поэтому финальная компиляция не была\n"
            "    проверена настоящим компилятором - только баланс скобок и структурная\n"
            "    самосогласованность (см. статистику проверок выше). Перед боевым\n"
            "    использованием рекомендуется прогнать через javac/IDE и поправить то,\n"
            "    что покажет реальный компилятор (в первую очередь - конфликты коротких\n"
            "    имён импортов, если они указаны выше).\n"
            "  - synchronized-блоки не сворачиваются в `synchronized (x) { ... }` -\n"
            "    monitorenter/monitorexit оставлены как явные комментарии с полным\n"
            "    сохранением семантики (просто без Java-сахара).\n"
            "  - try/finally, скомпилированный через дублирование кода finally-блока\n"
            "    (стандартно для javac 7+), восстанавливается как несколько отдельных\n"
            "    catch(Throwable)-блоков с повторяющимся кодом, а не как единый\n"
            "    красивый `finally {}` - семантика верна, но не свёрнута.\n"
        )
    print(f"[*] README: {path}")


def main():
    if platform.system() == "Windows":
        # На Windows GUI - ЕДИНСТВЕННЫЙ путь (проще для сборки в один .exe
        # через PyInstaller - не нужно поддерживать отдельно консольный
        # сценарий без окна). Если jar подсунут аргументом (напр. перетащили
        # файл на .exe) - подставляем его в GUI и сразу стартуем декомпиляцию.
        try:
            from gui import run_gui
        except Exception as e:
            try:
                print(f"Не удалось загрузить GUI (gui.py): {type(e).__name__}: {e}")
            except Exception:
                pass
            sys.exit(1)
        initial_jar = sys.argv[1] if len(sys.argv) > 1 else None
        run_gui(initial_jar)
        return

    if len(sys.argv) < 2:
        print("Использование: python3 main.py plugin.jar [output_dir]")
        sys.exit(1)
    jar_path = sys.argv[1]
    out_dir = sys.argv[2] if len(sys.argv) > 2 else os.path.splitext(os.path.basename(jar_path))[0] + "_decompiled"
    process_jar(jar_path, out_dir)
    print(f"[+] Готово. Результат в: {out_dir}")


if __name__ == "__main__":
    main()
