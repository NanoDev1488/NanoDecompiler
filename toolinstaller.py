# -*- coding: utf-8 -*-
"""
Локальная авто-закачка JDK/Maven "по требованию" (HANDOFF_3, п.3).

ПРИНЦИП (вариант Б, как обсуждали с пользователем): НИЧЕГО не скачивается
молча/автоматически - только по явному запросу (`--install-tools` в CLI,
кнопка "Установить" в GUI-настройках). `check_java_maven()` (main.py)
как раньше ничего не ставит сама, только сообщает и предлагает.

Portable-установка (без прав администратора, без изменения системного PATH):
    JDK   -> <tools_dir>/jdk-<version>/...
    Maven -> <tools_dir>/apache-maven-<version>/...
где <tools_dir> = <app_data_dir>/tools (используем тот же app-data каталог,
что и настройки GUI - см. gui_common.get_app_data_dir(), но этот модуль
НЕ импортирует gui_common, чтобы не тянуть его сюда лишний раз - логика
пути продублирована в миниатюре, см. get_tools_dir()).

Источники:
    JDK   - Adoptium API v3 (https://api.adoptium.net) - официальный
            "convenience redirect" эндпоинт /v3/binary/latest/... отдаёт
            HTTP-редирект прямо на архив нужной ОС/архитектуры.
    Maven - dlcdn.apache.org (официальное CDN Apache) - номер последней
            версии берём из maven-metadata.xml на repo1.maven.org (это
            стабильный, предназначенный для машинного чтения источник;
            HTML-листинг dlcdn парсить не нужно и хрупко).

Никаких сторонних pip-пакетов - только stdlib (urllib, zipfile, tarfile).
"""
import io
import json
import os
import platform
import shutil
import stat
import tarfile
import urllib.error
import urllib.request
import zipfile

ADOPTIUM_FEATURE_VERSION = 17  # LTS, более чем достаточно для декомпилированных Bukkit-плагинов
MAVEN_METADATA_URL = "https://repo1.maven.org/maven2/org/apache/maven/apache-maven/maven-metadata.xml"
MAVEN_FALLBACK_VERSION = "3.9.9"  # если metadata.xml недоступен (сеть/зеркало легло) - известная рабочая версия
REQUEST_TIMEOUT = 30
DOWNLOAD_CHUNK = 1 << 16  # 64 КБ


# ---------------------------------------------------------------------
# Windows: почему "mvn/java найдены только что установленным пользователем,
# но всё равно пишет НЕ НАЙДЕН" - классическая причина: PATH обновляется в
# реестре установщиком, но уже ЗАПУЩЕННЫЕ процессы (включая explorer.exe -
# родитель для двойного клика по .exe/.bat) продолжают жить со СТАРЫМ
# окружением до перезахода/перезагрузки. shutil.which() смотрит только
# os.environ["PATH"] текущего процесса - если пользователь поставил Maven
# и сразу же (без релогина) запустил NanoDecompiler - PATH в этом процессе
# ещё старый, java/mvn "не находятся" хотя реально установлены.
# `resolve_tool_path()` ниже читает АКТУАЛЬНЫЙ PATH прямо из реестра
# (и HKCU, и HKLM - PATH пользователя может быть определён в любом из двух)
# и ищет ещё и по типичным путям установки, которые нередко НЕ попадают
# в PATH вообще (напр. Maven, распакованный вручную без добавления в PATH).
# ---------------------------------------------------------------------
def _registry_path_dirs():
    if platform.system() != "Windows":
        return []
    dirs = []
    try:
        import winreg
        for hive, subkey in (
            (winreg.HKEY_CURRENT_USER, r"Environment"),
            (winreg.HKEY_LOCAL_MACHINE, r"SYSTEM\CurrentControlSet\Control\Session Manager\Environment"),
        ):
            try:
                with winreg.OpenKey(hive, subkey) as key:
                    value, _ = winreg.QueryValueEx(key, "Path")
                    dirs.extend(p for p in value.split(os.pathsep) if p.strip())
            except OSError:
                continue  # ключа/значения нет - не критично, пробуем второй hive
    except ImportError:
        pass  # winreg есть только на Windows - на всякий случай, хотя сюда и
              # так попадаем только при platform.system() == "Windows"
    return dirs


def _well_known_dirs(kind):
    """Типичные места portable/ручной установки, которые пользователи часто
    НЕ добавляют в PATH вообще (особенно Maven - его "установка" часто
    сводится к простой распаковке zip куда попало)."""
    if platform.system() != "Windows":
        return []
    dirs = []
    program_files = [os.environ.get("ProgramFiles", r"C:\Program Files"),
                      os.environ.get("ProgramFiles(x86)", r"C:\Program Files (x86)"),
                      os.environ.get("ProgramW6432", "")]
    user_profile = os.environ.get("USERPROFILE", "")
    if kind == "java":
        env_home = os.environ.get("JAVA_HOME", "")
        if env_home:
            dirs.append(os.path.join(env_home, "bin"))
        for pf in program_files:
            if not pf:
                continue
            for vendor_dir in ("Java", "Eclipse Adoptium", "Eclipse Foundation", "Microsoft", "Zulu"):
                base = os.path.join(pf, vendor_dir)
                if os.path.isdir(base):
                    for name in os.listdir(base):
                        dirs.append(os.path.join(base, name, "bin"))
    elif kind == "maven":
        for var in ("MAVEN_HOME", "M2_HOME"):
            env_home = os.environ.get(var, "")
            if env_home:
                dirs.append(os.path.join(env_home, "bin"))
        for pf in program_files:
            if not pf:
                continue
            base = os.path.join(pf, "Apache", "maven")
            if os.path.isdir(base):
                dirs.append(os.path.join(base, "bin"))
            apache_base = os.path.join(pf, "Apache")
            if os.path.isdir(apache_base):
                for name in os.listdir(apache_base):
                    if name.lower().startswith("maven"):
                        dirs.append(os.path.join(apache_base, name, "bin"))
        if user_profile:
            # scoop (частый способ установки Maven на Windows без админ-прав)
            scoop_current = os.path.join(user_profile, "scoop", "apps", "maven", "current", "bin")
            dirs.append(scoop_current)
    return [d for d in dirs if os.path.isdir(d)]


def resolve_tool_path(exe_names, kind):
    """Ищет исполняемый файл по имени (`exe_names` - список вариантов, напр.
    `["mvn", "mvn.cmd"]`) сначала через shutil.which() с ТЕКУЩИМ PATH, затем
    (только на Windows) с PATH, дополненным СВЕЖИМ значением из реестра и
    типичными путями установки (`kind` - "java" | "maven", см.
    `_well_known_dirs()`). Возвращает путь или None. Ничего не меняет в
    реальном os.environ текущего процесса - расширенный PATH используется
    только локально, для одного вызова shutil.which()."""
    for name in exe_names:
        found = shutil.which(name)
        if found:
            return found

    if platform.system() != "Windows":
        return None

    current = os.environ.get("PATH", "")
    extra_dirs = _registry_path_dirs() + _well_known_dirs(kind)
    if not extra_dirs:
        return None
    merged = os.pathsep.join([current] + extra_dirs)
    for name in exe_names:
        found = shutil.which(name, path=merged)
        if found:
            return found
    return None


class ToolInstallError(Exception):
    """Любая ошибка установки - сеть, распаковка, неподдерживаемая платформа."""


# ---------------------------------------------------------------------
# Каталог установки
# ---------------------------------------------------------------------
def get_app_data_dir():
    # Продублировано из gui_common.get_app_data_dir() НАМЕРЕННО - этот модуль
    # должен оставаться независимым и импортируемым из CLI/main.py без
    # затягивания gui_common (тот, в свою очередь, не тянет тяжёлые GUI-
    # тулкиты, но незачем плодить лишние взаимные импорты между "лёгкими"
    # модулями - см. HANDOFF_1 про плоскую структуру файлов).
    if platform.system() == "Windows":
        base = os.environ.get("LOCALAPPDATA") or os.path.expanduser("~")
        d = os.path.join(base, "NanoDecompiler")
    else:
        d = os.path.expanduser("~/.nanodecompiler")
    os.makedirs(d, exist_ok=True)
    return d


def get_tools_dir():
    d = os.path.join(get_app_data_dir(), "tools")
    os.makedirs(d, exist_ok=True)
    return d


# ---------------------------------------------------------------------
# Определение ОС/архитектуры для Adoptium API
# ---------------------------------------------------------------------
def _adoptium_os():
    system = platform.system()
    if system == "Windows":
        return "windows"
    if system == "Darwin":
        return "mac"
    return "linux"  # включая Termux (Android) - Adoptium публикует linux-сборки,
    # aarch64-Termux на телефоне попадает сюда же через архитектуру ниже


def _adoptium_arch():
    machine = platform.machine().lower()
    if machine in ("x86_64", "amd64"):
        return "x64"
    if machine in ("aarch64", "arm64"):
        return "aarch64"
    if machine.startswith("arm"):
        return "arm"
    if machine in ("i386", "i686", "x86"):
        return "x86-32"
    raise ToolInstallError(f"Неизвестная архитектура '{machine}' - автозакачка JDK не поддерживается, "
                            f"скачайте вручную: https://adoptium.net/")


# ---------------------------------------------------------------------
# Поиск УЖЕ установленного (портативно) java/mvn - PATH проверяется
# отдельно в main.py::check_java_maven(), здесь только tools_dir.
# ---------------------------------------------------------------------
def _find_one(tools_dir, dir_prefix, rel_bin):
    if not os.path.isdir(tools_dir):
        return None
    candidates = []
    for name in os.listdir(tools_dir):
        if name.lower().startswith(dir_prefix):
            full = os.path.join(tools_dir, name, *rel_bin)
            if os.path.isfile(full):
                candidates.append(full)
    if not candidates:
        return None
    # Если версий несколько - берём лексикографически последнюю (обычно
    # соответствует самой новой версии в формате jdk-17.0.9+9/apache-maven-3.9.9).
    candidates.sort()
    return candidates[-1]


def find_local_java(tools_dir=None):
    tools_dir = tools_dir or get_tools_dir()
    exe = "java.exe" if platform.system() == "Windows" else "java"
    return _find_one(tools_dir, "jdk", ("bin", exe))


def find_local_maven(tools_dir=None):
    tools_dir = tools_dir or get_tools_dir()
    exe = "mvn.cmd" if platform.system() == "Windows" else "mvn"
    return _find_one(tools_dir, "apache-maven", ("bin", exe))


# ---------------------------------------------------------------------
# Скачивание с прогрессом
# ---------------------------------------------------------------------
def _download(url, progress_cb=None, label=""):
    """Скачивает URL целиком в память (архивы JDK/Maven - десятки-сотни МБ,
    но не гигабайты - для portable-установки это приемлемо и сильно проще,
    чем стримить распаковку по ходу скачивания). Следует редиректам
    автоматически (urllib делает это сам). Возвращает bytes."""
    req = urllib.request.Request(url, headers={"User-Agent": "NanoDecompiler-toolinstaller"})
    try:
        with urllib.request.urlopen(req, timeout=REQUEST_TIMEOUT) as resp:
            total = resp.length or resp.getheader("Content-Length")
            total = int(total) if total else None
            buf = io.BytesIO()
            downloaded = 0
            while True:
                chunk = resp.read(DOWNLOAD_CHUNK)
                if not chunk:
                    break
                buf.write(chunk)
                downloaded += len(chunk)
                if progress_cb:
                    progress_cb(label, downloaded, total)
            return buf.getvalue()
    except urllib.error.URLError as e:
        raise ToolInstallError(f"Не удалось скачать {url}: {e}") from e


def _extract_archive(data, dest_dir, is_zip):
    """Распаковывает архив (zip или tar.gz) в dest_dir. Возвращает имя
    единственной корневой папки внутри архива (и JDK-, и Maven-архивы
    всегда содержат ровно одну корневую папку типа jdk-17.0.9+9/ или
    apache-maven-3.9.9/), либо None, если структура неожиданная (архив
    всё равно распакован - вызывающий код тогда сам просканирует dest_dir
    через find_local_java/find_local_maven)."""
    if is_zip:
        with zipfile.ZipFile(io.BytesIO(data)) as z:
            names = z.namelist()
            z.extractall(dest_dir)
    else:
        with tarfile.open(fileobj=io.BytesIO(data), mode="r:gz") as t:
            names = t.getnames()
            # Python 3.12+ по умолчанию строже фильтрует tar-члены
            # (data filter) - явно просим совместимый режим, если доступен.
            try:
                t.extractall(dest_dir, filter="data")
            except TypeError:
                t.extractall(dest_dir)

    roots = {n.split("/")[0] for n in names if n.strip("/")}
    if len(roots) == 1:
        root = roots.pop()
        extracted_path = os.path.join(dest_dir, root)
        if os.path.isdir(extracted_path):
            return root
    return None


def _ensure_executable(path):
    if platform.system() != "Windows" and os.path.exists(path):
        st = os.stat(path)
        os.chmod(path, st.st_mode | stat.S_IXUSR | stat.S_IXGRP | stat.S_IXOTH)


# ---------------------------------------------------------------------
# JDK (Adoptium / Eclipse Temurin)
# ---------------------------------------------------------------------
def _adoptium_binary_url(feature_version=ADOPTIUM_FEATURE_VERSION):
    os_name = _adoptium_os()
    arch = _adoptium_arch()
    # Convenience-эндпоинт Adoptium API v3 - редиректит прямо на бинарник,
    # без отдельного JSON-запроса за ссылкой. image_type=jdk (не jre) -
    # нужен javac для mvn clean package, не только java.
    return (f"https://api.adoptium.net/v3/binary/latest/{feature_version}/ga/"
            f"{os_name}/{arch}/jdk/hotspot/normal/eclipse")


def install_jdk(feature_version=ADOPTIUM_FEATURE_VERSION, progress_cb=None):
    """Скачивает и распаковывает Eclipse Temurin JDK в <tools_dir>/jdk-.../
    Возвращает путь к исполняемому java. progress_cb(label, downloaded, total)."""
    tools_dir = get_tools_dir()
    url = _adoptium_binary_url(feature_version)
    is_zip = _adoptium_os() == "windows"
    data = _download(url, progress_cb, label="JDK")
    root = _extract_archive(data, tools_dir, is_zip)
    java_path = find_local_java(tools_dir)
    if not java_path:
        raise ToolInstallError(
            "JDK скачан и распакован, но java не найдена внутри - возможно, "
            "Adoptium изменил структуру архива. Папка распаковки: "
            f"{os.path.join(tools_dir, root) if root else tools_dir}")
    _ensure_executable(java_path)
    return java_path


# ---------------------------------------------------------------------
# Maven (Apache dlcdn)
# ---------------------------------------------------------------------
def _latest_maven_version():
    try:
        req = urllib.request.Request(MAVEN_METADATA_URL,
                                      headers={"User-Agent": "NanoDecompiler-toolinstaller"})
        with urllib.request.urlopen(req, timeout=REQUEST_TIMEOUT) as resp:
            xml_text = resp.read().decode("utf-8", errors="replace")
        # Простой парсинг без внешних XML-либ сверх stdlib - metadata.xml
        # маленький и формат стабилен, полноценный XML-парсер тут избыточен;
        # но используем xml.etree (stdlib), а не regex, чтобы не сломаться
        # на форматировании.
        import xml.etree.ElementTree as ET
        root = ET.fromstring(xml_text)
        release = root.findtext("./versioning/release")
        if release:
            return release.strip()
    except Exception:
        pass
    return MAVEN_FALLBACK_VERSION


def install_maven(progress_cb=None):
    """Скачивает и распаковывает Apache Maven в <tools_dir>/apache-maven-.../
    Возвращает путь к исполняемому mvn/mvn.cmd."""
    tools_dir = get_tools_dir()
    version = _latest_maven_version()
    url = f"https://dlcdn.apache.org/maven/maven-3/{version}/binaries/apache-maven-{version}-bin.zip"
    try:
        data = _download(url, progress_cb, label="Maven")
    except ToolInstallError:
        if version != MAVEN_FALLBACK_VERSION:
            # dlcdn иногда убирает старые версии сразу после релиза новой -
            # если "последняя по metadata.xml" ещё не разъехалась по CDN,
            # пробуем последний известный рабочий номер как запасной вариант.
            url = (f"https://dlcdn.apache.org/maven/maven-3/{MAVEN_FALLBACK_VERSION}"
                   f"/binaries/apache-maven-{MAVEN_FALLBACK_VERSION}-bin.zip")
            data = _download(url, progress_cb, label="Maven")
        else:
            raise
    root = _extract_archive(data, tools_dir, is_zip=True)
    mvn_path = find_local_maven(tools_dir)
    if not mvn_path:
        raise ToolInstallError(
            "Maven скачан и распакован, но mvn не найден внутри - возможно, "
            "изменилась структура архива. Папка распаковки: "
            f"{os.path.join(tools_dir, root) if root else tools_dir}")
    _ensure_executable(mvn_path)
    return mvn_path


# ---------------------------------------------------------------------
# Единая точка входа для CLI (--install-tools) и GUI (кнопка "Установить")
# ---------------------------------------------------------------------
def install_missing(need_java, need_maven, progress_cb=None):
    """Ставит то, что запрошено (обычно - то, чего не хватает по
    check_java_maven()). Возвращает dict {"java": path_or_None, "maven": path_or_None,
    "errors": [...]}. НЕ бросает исключение наружу - собирает ошибки в список,
    чтобы одна неудача (напр. Maven) не мешала попытке поставить другое (JDK)."""
    result = {"java": None, "maven": None, "errors": []}
    if need_java:
        try:
            result["java"] = install_jdk(progress_cb=progress_cb)
        except ToolInstallError as e:
            result["errors"].append(f"JDK: {e}")
    if need_maven:
        try:
            result["maven"] = install_maven(progress_cb=progress_cb)
        except ToolInstallError as e:
            result["errors"].append(f"Maven: {e}")
    return result
