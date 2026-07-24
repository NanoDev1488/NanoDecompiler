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
import time
import zipfile

sys.setrecursionlimit(20000)

# На Windows с не-UTF8 консолью (напр. английская локаль - так настроены
# раннеры GitHub Actions windows-latest) sys.stdout по умолчанию кодируется
# в cp1252/cp437 и т.п., которые физически не умеют кириллицу - любой
# cprint() с русским текстом (а тут их сотни) падает с UnicodeEncodeError
# на первой же русской букве. Принудительно переключаем на UTF-8 здесь же,
# самым первым делом - до ЛЮБОГО print()/cprint() ниже по файлу. Та же
# правка стоит в api.py (для --api/--json-output пути).
for _stream in (sys.stdout, sys.stderr):
    try:
        _stream.reconfigure(encoding="utf-8", errors="replace")
    except AttributeError:
        pass  # Python <3.7 или поток без reconfigure() - маловероятно, но не падать из-за этого

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


# ---- цвета консоли (тёмно-голубая палитра, в тон GUI) ----
_ANSI = {
    "banner": "38;2;127;212;255",
    "info": "38;2;79;184;255",
    "ok": "38;2;79;227;139",
    "warn": "38;2;232;179;57",
    "error": "38;2;255;107;107",
    "dim": "38;2;111;147;179",
}
_ANSI_RESET = "\x1b[0m"


def classify_line(line):
    """Определяет смысловую категорию строки лога (для цвета - и в консоли
    через ANSI, и в GUI через теги Tkinter, см. gui.py::_classify_line)."""
    s = line.strip()
    if not s:
        return "dim"
    if s[:1] in "╭╰│─" or "NanoDecompiler" in s or "Java-декомпилятор" in s:
        return "banner"
    if s.startswith("[!]") or "ОШИБКА" in s or "ошибка" in s.lower():
        return "error"
    if s.startswith("[*] Не хватает") or "НЕ НАЙДЕН" in s or "НЕ НАЙДЕНА" in s or s.startswith("ВНИМАНИЕ"):
        return "warn"
    if s.startswith("[+]") or "Всё готово к работе" in s or "Готово" in s:
        return "ok"
    if s.startswith("[*]") or s.startswith("   "):
        return "info"
    return "dim"


def cprint(msg=""):
    """print(), но с цветом по смыслу строки - только если реально пишем в
    терминал (в GUI-логе sys.stdout подменён на очередь без isatty()==True,
    там цвет красится отдельно тегами Tkinter - см. gui.py)."""
    global _progress_active
    if _progress_active:
        sys.stdout.write("\n")
        _progress_active = False
    if not _supports_color():
        print(msg)
        return
    for line in str(msg).split("\n"):
        code = _ANSI.get(classify_line(line))
        print(f"\x1b[{code}m{line}{_ANSI_RESET}" if code else line)


def section(title):
    """Цветной заголовок-разделитель этапа (только в реальном терминале -
    в GUI-логе это просто обычная info-строка, см. classify_line)."""
    if _supports_color():
        code = _ANSI["banner"]
        print(f"\n\x1b[{code}m▸ {title}{_ANSI_RESET}")
    else:
        print(f"\n[*] {title}")


_progress_active = False


def progress(current, total, label):
    """Живой прогресс-бар в реальном терминале (перерисовывается на месте
    через \\r, как в Claude Code CLI) - в GUI/не-tty вместо этого печатает
    редкие дискретные вехи (каждые ~10%), чтобы не заспамить лог, но и не
    молчать всю дорогу на больших jar."""
    global _progress_active
    width = 28
    frac = (current / total) if total else 1.0
    filled = int(width * frac)
    bar = "█" * filled + "░" * (width - filled)
    pct = int(frac * 100)
    msg = f"[*] {label}: [{bar}] {current}/{total} ({pct}%)"
    if _supports_color():
        code = _ANSI["info"]
        sys.stdout.write(f"\r\x1b[K\x1b[{code}m{msg}{_ANSI_RESET}")
        sys.stdout.flush()
        _progress_active = True
        if current >= total:
            sys.stdout.write("\n")
            sys.stdout.flush()
            _progress_active = False
    else:
        step = max(1, total // 10)
        if current >= total or current % step == 0:
            print(msg)


def banner_text():
    """Аккуратная рамка в духе Claude Code CLI (ширина считается автоматически
    по тексту - не хардкодим отступы вручную, чтобы не поехало при правках).
    Цвет только если реально пишем в терминал (в GUI-логе sys.stdout подменён
    на очередь без isatty()==True - там банер печатается обычным текстом,
    без escape-мусора)."""
    lines = [
        "✻ NanoDecompiler v1.1",
        "   Java-декомпилятор/деобфускатор для Bukkit-плагинов",
    ]
    width = max(len(l) for l in lines)
    top = "╭" + "─" * (width + 2) + "╮"
    bot = "╰" + "─" * (width + 2) + "╯"
    mid = [f"│ {l.ljust(width)} │" for l in lines]
    plain = "\n".join([top, *mid, bot])
    if _supports_color():
        blue = f"\x1b[{_ANSI['banner']}m"
        return "\n".join(blue + ln + _ANSI_RESET for ln in plain.splitlines())
    return plain


def check_java_maven():
    """Проверяет, установлены ли java и mvn (нужны для следующего шага -
    mvn clean package) и печатает понятный статус + ссылки на скачивание,
    если чего-то не хватает. Сама НИЧЕГО не устанавливает - только смотрит
    PATH (shutil.which) И локальную portable-папку toolinstaller.get_tools_dir()
    (куда мог поставить `--install-tools` в прошлый раз, см. HANDOFF_3 п.3) -
    никаких внешних зависимостей для самой проверки не требуется."""
    section("Проверка окружения (java / maven)")
    missing = []
    _subprocess_kwargs = {}
    if platform.system() == "Windows":
        # Иначе при сборке в --windowed .exe (PyInstaller) на секунду
        # мелькало бы консольное окно дочернего процесса java/mvn.
        _subprocess_kwargs["creationflags"] = subprocess.CREATE_NO_WINDOW

    try:
        import toolinstaller
        local_java = toolinstaller.find_local_java()
        local_mvn = toolinstaller.find_local_maven()
        java_path = toolinstaller.resolve_tool_path(["java", "java.exe"], "java") or local_java
        mvn_path = toolinstaller.resolve_tool_path(["mvn", "mvn.cmd"], "maven") or local_mvn
    except Exception:
        # toolinstaller недоступен по какой-то причине - откатываемся на
        # голый shutil.which(), как было раньше, чем совсем ничего не найти.
        local_java = local_mvn = None
        java_path = shutil.which("java")
        mvn_path = shutil.which("mvn") or shutil.which("mvn.cmd")

    on_path_java = bool(shutil.which("java"))
    if java_path:
        try:
            r = subprocess.run([java_path, "-version"], capture_output=True, text=True, timeout=10,
                                **_subprocess_kwargs)
            ver_line = (r.stderr or r.stdout or "").splitlines()[0] if (r.stderr or r.stdout) else "версия неизвестна"
        except Exception:
            ver_line = "версия неизвестна"
        if on_path_java:
            source = "PATH"
        elif java_path == local_java:
            source = "локальная portable-установка"
        else:
            source = "найдена вне PATH (реестр/типичный путь установки - см. ниже)"
        cprint(f"    java:  найдена, {source} ({ver_line})")
        if source.startswith("найдена вне PATH"):
            cprint(f"           путь: {java_path}")
            cprint("           PATH не обновился в этом процессе - обычно достаточно перезайти "
                   "в систему/перезагрузиться, чтобы это заработало и без такой подсказки.")
    else:
        missing.append("java")
        cprint("    java:  НЕ НАЙДЕНА (ни в PATH, ни в реестре/типичных путях, ни в portable-папке).")
        cprint("           Скачать (Eclipse Temurin JDK, бесплатно): https://adoptium.net/")
        cprint("           Либо запустить: python3 main.py --install-tools")

    on_path_mvn = bool(shutil.which("mvn") or shutil.which("mvn.cmd"))
    if mvn_path:
        try:
            r = subprocess.run([mvn_path, "-version"], capture_output=True, text=True, timeout=15,
                                **_subprocess_kwargs)
            ver_line = (r.stdout or r.stderr or "").splitlines()[0] if (r.stdout or r.stderr) else "версия неизвестна"
        except Exception:
            ver_line = "версия неизвестна"
        if on_path_mvn:
            source = "PATH"
        elif mvn_path == local_mvn:
            source = "локальная portable-установка"
        else:
            source = "найден вне PATH (реестр/типичный путь установки - см. ниже)"
        cprint(f"    maven: найден, {source} ({ver_line})")
        if source.startswith("найден вне PATH"):
            cprint(f"           путь: {mvn_path}")
            cprint("           PATH не обновился в этом процессе - обычно достаточно перезайти "
                   "в систему/перезагрузиться, чтобы это заработало и без такой подсказки.")
    else:
        missing.append("maven")
        cprint("    maven: НЕ НАЙДЕН (ни в PATH, ни в реестре/типичных путях, ни в portable-папке).")
        cprint("           Скачать: https://maven.apache.org/download.cgi")
        cprint("           (после распаковки папку bin/ нужно добавить в PATH)")
        cprint("           Либо запустить: python3 main.py --install-tools")

    if not missing:
        cprint("[*] Всё готово к работе - можете делать с восстановленным плагином всё, что хотите.")
    else:
        cprint(f"[*] Не хватает: {', '.join(missing)}. Без этого не получится собрать проект "
              f"(mvn clean package) после декомпиляции - сама декомпиляция от этого не зависит "
              f"и пройдёт нормально.")
    return missing


def _try_handle_install_tools_json(argv):
    """`--install-tools-json[=jdk|maven]` - то же самое, что --install-tools,
    но вывод - NDJSON (по одной JSON-строке на событие) вместо текста с
    прогресс-баром через \\r. Используется ТОЛЬКО Electron-клиентом (см.
    electron/main.ts) - там нужно показать реальный процент в UI, а не
    парсить текстовый \\r-прогресс. CLI-версия (--install-tools) остаётся
    для людей, работающих в терминале - там текст читается глазами удобнее.

    Формат событий (каждая строка - отдельный valid JSON):
        {"type": "progress", "label": "JDK", "pct": 42, "downloaded_mb": 12, "total_mb": 28}
        {"type": "done", "java": "путь/или null", "maven": "путь/или null", "errors": ["..."]}
        {"type": "error", "message": "..."}  - если что-то пошло не так до начала установки
    """
    if not any(a == "--install-tools-json" or a.startswith("--install-tools-json=") for a in argv):
        return False
    import json as _json
    import toolinstaller

    only = None
    for a in argv:
        if a.startswith("--install-tools-json="):
            only = a.split("=", 1)[1].strip().lower()

    # ВАЖНО: тут НЕ вызываем check_java_maven() - она печатает читаемый текст
    # с баннером, это не нужно вызывающей стороне (Electron уже решил, что
    # нужно ставить, по своей собственной проверке лога) - только тихо
    # смотрим, чего не хватает.
    java_path = toolinstaller.resolve_tool_path(["java", "java.exe"], "java") or toolinstaller.find_local_java()
    mvn_path = toolinstaller.resolve_tool_path(["mvn", "mvn.cmd"], "maven") or toolinstaller.find_local_maven()
    missing = ([] if java_path else ["java"]) + ([] if mvn_path else ["maven"])

    if only in ("jdk", "java"):
        need_java, need_maven = "java" in missing, False
    elif only == "maven":
        need_java, need_maven = False, "maven" in missing
    else:
        need_java, need_maven = "java" in missing, "maven" in missing

    if not need_java and not need_maven:
        print(_json.dumps({"type": "done", "java": java_path, "maven": mvn_path, "errors": []}, ensure_ascii=False))
        return True

    def progress_cb(label, downloaded, total):
        if total:
            pct = int(downloaded * 100 / total)
            evt = {"type": "progress", "label": label, "pct": pct,
                   "downloaded_mb": downloaded // 1024 // 1024, "total_mb": total // 1024 // 1024}
        else:
            evt = {"type": "progress", "label": label, "pct": None,
                   "downloaded_mb": downloaded // 1024 // 1024, "total_mb": None}
        print(_json.dumps(evt, ensure_ascii=False))
        sys.stdout.flush()

    result = toolinstaller.install_missing(need_java, need_maven, progress_cb=progress_cb)
    print(_json.dumps({"type": "done", "java": result["java"], "maven": result["maven"],
                        "errors": result["errors"]}, ensure_ascii=False))
    return True


def _try_handle_install_tools(argv):
    """`--install-tools` (CLI, HANDOFF_3 п.3) - portable-закачка недостающих
    java/mvn БЕЗ прав администратора, в локальную папку
    toolinstaller.get_tools_dir(). Только по явному запросу (вариант Б -
    ничего не ставится молча при обычном запуске). Возвращает True, если
    аргументы относились к этому режиму (main() должен выйти)."""
    if not any(a == "--install-tools" or a.startswith("--install-tools=") for a in argv):
        return False
    import toolinstaller

    only = None
    for a in argv:
        if a.startswith("--install-tools="):
            only = a.split("=", 1)[1].strip().lower()

    missing = check_java_maven()
    if only in ("jdk", "java"):
        need_java, need_maven = "java" in missing, False
    elif only == "maven":
        need_java, need_maven = False, "maven" in missing
    else:
        need_java, need_maven = "java" in missing, "maven" in missing

    if not need_java and not need_maven:
        cprint("[*] Устанавливать нечего - всё уже найдено (см. проверку выше).")
        return True

    cprint("")
    section("Установка недостающих инструментов (portable, без прав администратора, "
            f"в {toolinstaller.get_tools_dir()})")

    last_pct = {}

    def progress_cb(label, downloaded, total):
        if total:
            pct = int(downloaded * 100 / total)
            if last_pct.get(label) == pct:
                return
            last_pct[label] = pct
            sys.stdout.write(f"\r    {label}: {pct:3d}% ({downloaded // 1024 // 1024} МБ / {total // 1024 // 1024} МБ)")
            sys.stdout.flush()
        else:
            sys.stdout.write(f"\r    {label}: {downloaded // 1024 // 1024} МБ")
            sys.stdout.flush()

    result = toolinstaller.install_missing(need_java, need_maven, progress_cb=progress_cb)
    print()  # завершить строку прогресса
    if result["java"]:
        cprint(f"[+] JDK установлена: {result['java']}")
    if result["maven"]:
        cprint(f"[+] Maven установлен: {result['maven']}")
    for err in result["errors"]:
        cprint(f"[!] Ошибка установки - {err}")
    if result["errors"]:
        cprint("[*] Что-то не встало - см. ошибки выше, можно поставить вручную по ссылкам из проверки окружения.")
    return True


from classfile import ClassFile, access_str
from disassembler import disassemble
from javatypes import (
    field_descriptor_to_java, method_descriptor_to_java, looks_obfuscated, dotted_from_internal,
    mark_type, resolve_type_markers,
)
from pom_builder import build_pom, KNOWN_LIBS, find_pom_properties_and_xml, parse_shade_relocations
from engine import decompile_method_body, fallback_bytecode_listing
from verify import ProjectStats, check_brackets, check_import_collisions
from switchmap import detect_switchmaps
from stackvm import java_string_literal, java_float_literal
from ast_nodes import ExprStmt, Assign, FieldAccess, NewObject, ReturnStmt
from emit import emit_expr, emit_stmts, set_shadow_context, set_current_class

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


def _known_library_coords(internal, extra_prefixes=None):
    """Если internal-имя класса подпадает под известную библиотеку - вернуть
    (dotted_prefix, groupId, artifactId), иначе None.
    extra_prefixes: доп. список (dotted_prefix, (groupId, artifactId)) -
    релоцированные (shaded) префиксы, обнаруженные в <relocations> самого
    pom.xml плагина (см. _relocated_library_prefixes ниже) - проверяются
    ПЕРЕД основной таблицей KNOWN_LIBS, т.к. они точнее (взяты из
    официального конфига сборки именно ЭТОГО jar'а, а не общего списка)."""
    dotted = internal_to_dotted(internal)
    for prefix, coords in (extra_prefixes or []):
        if dotted == prefix or dotted.startswith(prefix + "."):
            return prefix, coords[0], coords[1]
    for prefix, coords in KNOWN_LIBS:
        if dotted == prefix or dotted.startswith(prefix + "."):
            return prefix, coords[0], coords[1]
    return None


def _relocated_library_prefixes(original_pom_xml):
    """По <relocations> из pom.xml плагина (maven-shade-plugin) строит список
    (shaded_dotted_prefix, (groupId, artifactId)) для тех relocation-записей,
    чей ОРИГИНАЛЬНЫЙ pattern совпадает с известной нам библиотекой из
    KNOWN_LIBS - т.е. "эта известная библиотека здесь лежит под таким-то
    неожиданным именем пакета, декомпилировать её не нужно, она уже есть в
    pom.xml как <dependency>". Пример из реального плагина пользователя
    (DeathUtils): pattern org.sqlite -> shadedPattern com.agent1k.libs.sqlite."""
    out = []
    for pattern, shaded in parse_shade_relocations(original_pom_xml):
        for prefix, coords in KNOWN_LIBS:
            if pattern == prefix or pattern.startswith(prefix + ".") or prefix.startswith(pattern + "."):
                out.append((shaded, coords))
                break
    return out


# Сигнатуры для случаев, когда pom.xml САМОГО плагина не забандлен внутри jar'а
# вообще (частый реальный кейс - см. MLSAC-1.0, где _relocated_library_prefixes
# выше не срабатывает, т.к. парсить нечего: <relocations> просто негде взять) -
# тогда релокацию ловим по характерным путям РЕСУРСОВ, которые известная
# библиотека всегда кладёт по одному и тому же относительному шаблону
# независимо от того, куда её пакет релоцировали (maven-shade-plugin
# переименовывает и путь ресурса вместе с пакетом классов, если ресурс лежит
# внутри релоцированного пакета - значит "хвост" пути после релоцированного
# префикса остаётся ПОСТОЯННЫМ, и по нему можно восстановить префикс).
_SIGNATURE_PATTERNS = [
    # sqlite-jdbc: org/sqlite/native/{OS}/{arch}/(lib)?sqlitejdbc.{so,dll,dylib}
    (re.compile(r"^(.*)/native/[^/]+/[^/]+/(?:lib)?sqlitejdbc\.(?:so|dll|dylib)$"),
     ("org.xerial", "sqlite-jdbc")),
]


def _signature_relocated_prefixes(all_names):
    """Fallback-детект релокации по сигнатурным путям ресурсов - см. комментарий
    к _SIGNATURE_PATTERNS. Не заменяет _relocated_library_prefixes (тот точнее,
    т.к. берёт данные из официального конфига сборки), а дополняет его для
    случаев, когда pom.xml плагина не бандлится вообще."""
    out = []
    seen_coords = set()
    for name in all_names:
        for pattern, coords in _SIGNATURE_PATTERNS:
            if coords in seen_coords:
                continue
            m = pattern.match(name)
            if m:
                dotted_prefix = m.group(1).replace("/", ".")
                out.append((dotted_prefix, coords))
                seen_coords.add(coords)
    return out


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


def _format_annotation_value(v):
    """Простое (безопасное для инлайна) значение аргумента аннотации, или
    None если значение сложное (enum-константа/вложенная аннотация/массив) -
    в этом случае аргументы аннотации лучше не печатать вообще, чем
    напечатать что-то невалидное (см. _format_annotation)."""
    if isinstance(v, bool):
        return "true" if v else "false"
    if isinstance(v, (int, float)):
        return str(v)
    if isinstance(v, str):
        return java_string_literal(v)
    return None


def _format_annotation(ann, renamer, known_internal_by_dotted, all_imports):
    """@NotNull / @Foo(key = "val") - см. classfile.py::_parse_annotation.
    Тип печатается через mark_type() (тот же отложенный резолв simple-имя/
    FQN, что и везде - см. javatypes.mark_type), поэтому коллизии импортов
    между аннотациями и обычными типами обрабатываются автоматически."""
    type_desc = ann.get("type", "")
    try:
        java_type = field_descriptor_to_java(type_desc)
    except Exception:
        java_type = type_desc.strip("L;").replace("/", ".")
    dotted = format_type_dotted(java_type, renamer, known_internal_by_dotted, all_imports)
    name_marker = mark_type(dotted)
    args = ann.get("args") or {}
    if not args:
        return f"@{name_marker}"
    parts = []
    for k, v in args.items():
        rendered = _format_annotation_value(v)
        if rendered is None:
            return f"@{name_marker}"  # сложное значение - печатаем маркер без аргументов, не гадаем
        parts.append(rendered if (k == "value" and len(args) == 1) else f"{k} = {rendered}")
    return f"@{name_marker}({', '.join(parts)})"


def process_jar(jar_path, out_dir):
    """Обратно-совместимая обёртка (см. gui_raw.py/gui_neon.py/gui_md3.py и
    CLI-ветку main() ниже) - возвращает только out_dir, как и раньше.
    Для программного доступа к статистике (режим API - см. api.py) см.
    process_jar_with_stats()."""
    out_dir, _stats = process_jar_with_stats(jar_path, out_dir)
    return out_dir


def process_jar_with_stats(jar_path, out_dir):
    """То же самое, что process_jar(), но возвращает (out_dir, ProjectStats) -
    нужно режиму API (api.py), чтобы отдать статистику как JSON без
    перепарсивания README_RU.txt."""
    _t0 = time.time()
    _enable_windows_ansi()
    print()
    print(banner_text())
    print()
    check_java_maven()

    section("Проверка на подозрительное содержимое")
    import malware_scan
    malware_findings = malware_scan.scan_jar(jar_path)
    stats_findings_holder = malware_findings  # прокидываем в stats чуть ниже, после создания ProjectStats
    warning_text = malware_scan.format_findings_for_console(malware_findings)
    if warning_text:
        for line in warning_text.split("\n"):
            cprint(f"[!] {line}" if not line.startswith("  [") else line)
    else:
        cprint("[*] Признаков вредоносного кода не обнаружено (эвристика, не гарантия - см. README_RU.txt).")

    os.makedirs(out_dir, exist_ok=True)
    src_dir = os.path.join(out_dir, "src", "main", "java")
    os.makedirs(src_dir, exist_ok=True)

    stats = ProjectStats()
    stats.malware_findings = stats_findings_holder
    class_files = {}
    parse_errors = []
    plugin_yml_text = None

    section("Разбор .class файлов")
    with zipfile.ZipFile(jar_path) as z:
        all_names = z.namelist()
        names = [n for n in all_names if n.endswith(".class") and "module-info" not in n]
        stats.classes_total = len(names)
        cprint(f"[*] Найдено {len(names)} .class файлов")
        for n in names:
            try:
                data = z.read(n)
                cf = ClassFile(data=data)
                class_files[cf.this_class_name] = cf
            except Exception as e:
                parse_errors.append((n, str(e)))
        stats.classes_parsed = len(class_files)
        stats.parse_errors = parse_errors

        # Смотрим pom.xml плагина (если он реально внутри jar'а) ЗАРАНЕЕ, до
        # решения какие классы пропускать - нужно для _relocated_library_prefixes
        # ниже (maven-shade-plugin мог переименовать пакет бандленной
        # библиотеки во что угодно, см. DeathUtils: org.sqlite ->
        # com.agent1k.libs.sqlite - без этого такую библиотеку не узнать по
        # одному только префиксу пакета). build_pom() ниже сама ещё раз найдёт
        # этот же pom.xml - здесь читаем его только для relocations, дублирующий
        # проход по just-in-memory списку имён недорогой.
        _pom_props_early, _pom_xml_early = find_pom_properties_and_xml(all_names, z)
        relocated_prefixes = _relocated_library_prefixes(_pom_xml_early)
        relocated_prefixes += _signature_relocated_prefixes(all_names)
        if relocated_prefixes:
            cprint(f"[*] В pom.xml плагина найден релоцированный (shaded) пакет известной "
                   f"библиотеки: {', '.join(f'{p} -> {c[0]}:{c[1]}' for p, c in relocated_prefixes)}")

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
            hit = _known_library_coords(k, relocated_prefixes)
            if hit:
                _prefix, g, a = hit
                library_internal_names.append(k)
                library_hit_labels.add(f"{g}:{a}")
                del class_files[k]
        if library_internal_names:
            stats.library_classes_skipped = len(library_internal_names)
            stats.library_names_hit = library_hit_labels
            cprint(f"[*] Известных библиотечных классов пропущено (не декомпилируются, "
                  f"будут подтянуты maven'ом как зависимость): "
                  f"{len(library_internal_names)} ({', '.join(sorted(library_hit_labels))})")

        # Префиксы путей ресурсов, которые нужно пропустить вместе с классами
        # известных/релоцированных библиотек (см. выше) - иначе в проект всё
        # равно попадали бы нативные .so/.dll/.dylib и .properties библиотеки
        # (напр. sqlite-jdbc бандлит нативные бинарники под тем же путём, что
        # и свои .class - libraryInternalNames их не ловит, т.к. это не .class).
        _skip_res_prefixes = tuple(
            p.replace(".", "/") + "/" for p, _c in KNOWN_LIBS
        ) + tuple(
            p.replace(".", "/") + "/" for p, _c in relocated_prefixes
        )
        _skip_res_ga = {label for label in library_hit_labels}  # "groupId:artifactId"

        res_dir = os.path.join(out_dir, "src", "main", "resources")
        for n in all_names:
            if n.endswith(".class") or n.endswith("/"):
                continue
            if n.startswith(_skip_res_prefixes):
                continue
            m_maven = re.match(r"META-INF/maven/([^/]+)/([^/]+)/", n)
            if m_maven and f"{m_maven.group(1)}:{m_maven.group(2)}" in _skip_res_ga:
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

        cprint(f"[*] Успешно распарсено классов: {len(class_files)}; ошибок парсинга: {len(parse_errors)}")

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
        cprint(f"[*] pom.xml ({'найден оригинал' if pom_kind == 'original' else 'сгенерирован по эвристике'}): {pom_dest}")

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
        cprint(f"[*] Найдено и свёрнуто synthetic switch-map классов (switch-on-enum): {len(synthetic_switchmap_classes)}")

    all_imports = {}
    section("Декомпиляция классов")
    to_render = [(i, cf) for i, cf in class_files.items() if i not in synthetic_switchmap_classes]
    total_to_render = len(to_render)
    for done_count, (internal, cf) in enumerate(to_render, 1):
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
        progress(done_count, total_to_render, "Декомпиляция классов")

    stats.import_conflicts = check_import_collisions(all_imports)
    stats.synthetic_switchmap_classes_hidden = len(synthetic_switchmap_classes)

    write_mapping_report(out_dir, renamer)
    write_readme(out_dir, jar_path, len(class_files), parse_errors, class_files, renamer, stats)
    cprint(f"[*] Заняло: {time.time() - _t0:.1f} сек.")
    return out_dir, stats


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
        # package-level аннотаций + `package x.y.z;`.
        all_imports = {}
        ann_lines = [_format_annotation(a, renamer, known_internal_by_dotted, all_imports)
                     for a in cf.annotations]
        lines = [f"// исходный (обфусцированный) внутренний класс: {internal_to_dotted(internal)}"]
        lines.extend(ann_lines)
        if pkg:
            lines.append(f"package {pkg.replace('/', '.')};")
        text = resolve_type_markers("\n".join(lines), set())
        return text + "\n", all_imports

    lines = []
    lines.append(f"// исходный (обфусцированный) внутренний класс: {internal_to_dotted(internal)}")
    if pkg:
        lines.append(f"package {pkg.replace('/', '.')};")

    set_current_class(internal_to_dotted(new_internal))

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
    class_annotation_lines = [_format_annotation(a, renamer, known_internal_by_dotted, all_imports)
                               for a in cf.annotations]

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
                set_shadow_context(cres.ctx)
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
                set_shadow_context(_cres.ctx)
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
        for _ann in f.annotations:
            body_lines.append(f"    {_format_annotation(_ann, renamer, known_internal_by_dotted, all_imports)}")
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
                    _static_ctx = cres.ctx if (is_enum and enum_const_fields) else cres2.ctx
                    _static_pre = (cres.pre_lines if (is_enum and enum_const_fields) else cres2.pre_lines) or []
                    set_shadow_context(_static_ctx)
                    body_lines.append("    static {")
                    body_lines.extend(_static_pre)
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

        result = None
        if m.code is not None:
            stats.total_methods += 1
            result = decompile_method_body(cf, m, renamer, known_internal_by_dotted, internal, indent=2, enum_ordinals=enum_ordinals, switchmap_tables=switchmap_tables)

        # Имена параметров в сигнатуре ДОЛЖНЫ совпадать с именами, которые тело
        # метода реально использует (ctx.locals - argN по умолчанию, либо
        # настоящее имя из LocalVariableTable, если jar собран с отладочной
        # информацией - см. stackvm.py::MethodCtx._build_lvt_names). Раньше
        # сигнатура строилась ДО декомпиляции тела и всегда печатала "argN" -
        # с появлением LVT-имён это стало реальным багом рассинхронизации
        # (сигнатура "arg0", а тело внутри уже ссылалось на "player" - код не
        # компилировался). Поэтому сигнатура строится ПОСЛЕ декомпиляции тела,
        # из тех же result.ctx.locals, а не заново с нуля.
        param_names = None
        if result is not None and result.ok and result.ctx is not None:
            param_entries = sorted(
                ((slot, info) for slot, info in result.ctx.locals.items() if info.get("is_param")),
                key=lambda si: si[0],
            )
            names = [info["name"] for _, info in param_entries]
            if is_enum_ctor:
                names = names[2:]
            if len(names) == len(params_disp):
                param_names = names
        if param_names is None:
            param_names = [f"arg{i + arg_offset}" for i in range(len(params_disp))]

        param_anns = list(m.param_annotations) if m.param_annotations else []
        if is_enum_ctor and param_anns:
            param_anns = param_anns[2:]
        param_parts = []
        for i, (p, n) in enumerate(zip(params_disp, param_names)):
            prefix = ""
            if i < len(param_anns) and param_anns[i]:
                prefix = " ".join(_format_annotation(a, renamer, known_internal_by_dotted, all_imports)
                                   for a in param_anns[i]) + " "
            param_parts.append(f"{prefix}{_simple_type(p)} {n}")
        param_str = ", ".join(param_parts)
        has_body = result is not None
        sig_end = " {" if has_body else ";"
        for _ann in m.annotations:
            body_lines.append(f"    {_format_annotation(_ann, renamer, known_internal_by_dotted, all_imports)}")
        sig = f"    {mmods} {_simple_type(ret_disp)} {mname}({param_str}){sig_end}{renamed_note}".replace("  ", " ")
        body_lines.append(sig)

        if result is not None:
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
    for _ann_line in class_annotation_lines:
        lines.append(_ann_line)
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
    cprint(f"[*] Отчёт деобфускации: {path}")


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
            "    вместо этого метод честно откатывается на дизассемблированный листинг\n"
            "    байткода (см. `synchronized-блок не свёрнут` в причинах ниже, если есть) -\n"
            "    компилировать такой листинг всё равно нельзя, зато семантика не теряется\n"
            "    молча.\n"
            "  - try/finally, скомпилированный через дублирование кода finally-блока\n"
            "    (стандартно для javac 7+), восстанавливается как несколько отдельных\n"
            "    catch(Throwable)-блоков с повторяющимся кодом, а не как единый\n"
            "    красивый `finally {}` - семантика верна, но не свёрнута.\n"
        )
    cprint(f"[*] README: {path}")


def _try_handle_api_mode():
    """ПРОВЕРЯЕТСЯ ПЕРВЫМ ДЕЛОМ в main() - до ветки 'Windows -> GUI'. Это
    ключевое требование из HANDOFF_3, п.2: режим API должен ЯВНО отличаться
    от обычного запуска и НИКОГДА не импортировать gui.py / не открывать
    окно, даже на Windows без аргументов. Возвращает True, если аргументы
    относились к API-режиму - в этом случае main() должен просто выйти,
    вся работа уже сделана здесь (см. api.py)."""
    argv = list(sys.argv[1:])

    if "--api-server" in argv:
        import api
        host = "127.0.0.1"
        port = 8791
        if "--host" in argv:
            host = argv[argv.index("--host") + 1]
        if "--port" in argv:
            port = int(argv[argv.index("--port") + 1])
        api.run_api_server(host=host, port=port)
        return True

    if "--json-output" in argv or "--api" in argv:
        import api
        import json
        # Убираем флаги (и их значения для --host/--port, на случай если их
        # тоже передали здесь) - остаётся только позиционные jar/out_dir.
        skip_next = False
        positional = []
        for a in argv:
            if skip_next:
                skip_next = False
                continue
            if a in ("--json-output", "--api", "--api-server"):
                continue
            if a in ("--host", "--port"):
                skip_next = True
                continue
            positional.append(a)
        jar_path = positional[0] if positional else None
        out_dir = positional[1] if len(positional) > 1 else None
        if not jar_path:
            print(json.dumps({
                "status": "error",
                "error": "использование: main.py plugin.jar [out_dir] --json-output",
            }, ensure_ascii=False))
            sys.exit(1)
        if not out_dir:
            out_dir = os.path.splitext(os.path.basename(jar_path))[0] + "_decompiled"
        api.run_json_output(jar_path, out_dir)  # печатает JSON и делает sys.exit сама
        return True

    return False


def _try_handle_jar_summary(argv):
    """`--jar-summary plugin.jar` - печатает JSON со сводкой по jar'у (имя,
    размер, версия Java, число классов/пакетов, имя плагина из plugin.yml) -
    для карточки "выбрал файл -> вот что внутри" в Electron-клиенте (см.
    src/App.tsx). Переиспользует gui_common.jar_summary() - та же функция,
    что рисовала карточку в старом GUI (см. gui_common.py - там же явно
    сказано, что этот модуль безопасно импортировать из CLI, никаких
    tkinter/customtkinter/flet зависимостей не тянет)."""
    if "--jar-summary" not in argv:
        return False
    import json as _json
    import gui_common

    idx = argv.index("--jar-summary")
    jar_path = argv[idx + 1] if idx + 1 < len(argv) else None
    if not jar_path:
        print(_json.dumps({"error": "использование: main.py --jar-summary plugin.jar"}, ensure_ascii=False))
        return True
    if not os.path.isfile(jar_path):
        print(_json.dumps({"error": f"файл не найден: {jar_path}"}, ensure_ascii=False))
        return True
    print(_json.dumps(gui_common.jar_summary(jar_path), ensure_ascii=False))
    return True


def main():
    if _try_handle_api_mode():
        return

    if _try_handle_jar_summary(sys.argv[1:]):
        return

    if _try_handle_install_tools_json(sys.argv[1:]):
        return

    if _try_handle_install_tools(sys.argv[1:]):
        return

    if platform.system() == "Windows" and "--headless" not in sys.argv[1:] and len(sys.argv) < 2:
        # Раньше здесь запускался старый tkinter/customtkinter/flet GUI
        # (gui.py + gui_raw.py/gui_neon.py/gui_md3.py) при запуске БЕЗ
        # аргументов на Windows. Эти файлы физически удалены из движка -
        # GUI теперь только Electron-клиент (NanoDecompiler-Client-Setup.exe,
        # отдельный продукт, см. README.md) - этот exe (CLI/API) для него
        # не более чем дочерний процесс, вызываемый с аргументами. Если
        # запустили без аргументов (напр. случайным двойным кликом) - просто
        # печатаем usage вместо попытки открыть несуществующий GUI.
        cprint("Это консольный движок NanoDecompiler - для графического интерфейса используйте")
        cprint("клиентское приложение (NanoDecompiler-Client-Setup.exe).")
        cprint("")
        cprint("Использование: NanoDecompilerCLI.exe plugin.jar [output_dir]")
        cprint("       NanoDecompilerCLI.exe plugin.jar [out_dir] --api   (разовый вызов, JSON в stdout)")
        cprint("       NanoDecompilerCLI.exe --api-server [--host H] [--port 8791]   (HTTP-сервер)")
        cprint("       NanoDecompilerCLI.exe --install-tools[=jdk|maven]   (portable JDK/Maven по требованию)")
        sys.exit(1)

    if len(sys.argv) < 2:
        cprint("Использование: python3 main.py plugin.jar [output_dir]")
        cprint("       python3 main.py plugin.jar [out_dir] --json-output   (разовый вызов, JSON в stdout)")
        cprint("       python3 main.py --api-server [--host H] [--port 8791]   (HTTP-сервер)")
        cprint("       python3 main.py --install-tools[=jdk|maven]   (portable JDK/Maven по требованию)")
        sys.exit(1)
    jar_path = sys.argv[1]
    out_dir = sys.argv[2] if len(sys.argv) > 2 else os.path.splitext(os.path.basename(jar_path))[0] + "_decompiled"
    process_jar(jar_path, out_dir)
    cprint(f"[+] Готово. Результат в: {out_dir}")


if __name__ == "__main__":
    main()
