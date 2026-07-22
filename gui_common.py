# -*- coding: utf-8 -*-
"""
Общий код для всех трёх GUI-движков (gui_raw.py / gui_neon.py / gui_md3.py):
настройки (тема + путь по умолчанию), захват stdout в очередь, сводка по jar,
открытие в VS Code. Ничего специфичного для конкретного тулкита (tkinter/
customtkinter/flet) здесь быть не должно - см. gui.py для диспетчера.

Ничего из этого не тянет тяжёлые GUI-зависимости - безопасно импортировать
из main.py/CLI без риска утащить tkinter/flet туда, где их нет.
"""
import json
import os
import platform
import queue
import shutil
import struct
import subprocess
import zipfile

NANO_DECOMPILER_VERSION_FALLBACK = "NanoDecompiler"

VALID_THEMES = ("raw", "neon", "md3")
DEFAULT_THEME = "raw"


# ---------------------------------------------------------------------
# Настройки: %LOCALAPPDATA%\NanoDecompiler\settings.json (Windows), либо
# ~/.nanodecompiler/settings.json на других ОС (на случай запуска GUI не
# на Windows - сам движок кросс-платформенный, только gui.py исторически
# Windows-only, но незачем жёстко это требовать для файла настроек).
# ---------------------------------------------------------------------
def get_app_data_dir():
    if platform.system() == "Windows":
        base = os.environ.get("LOCALAPPDATA") or os.path.expanduser("~")
        d = os.path.join(base, "NanoDecompiler")
    else:
        d = os.path.expanduser("~/.nanodecompiler")
    os.makedirs(d, exist_ok=True)
    return d


def get_settings_path():
    return os.path.join(get_app_data_dir(), "settings.json")


DEFAULT_SETTINGS = {
    "theme": DEFAULT_THEME,          # "raw" | "neon" | "md3"
    "default_output_dir": "",        # пусто = рядом с jar, "<jar>_decompiled"
    "confirm_overwrite": True,
}


def load_settings():
    path = get_settings_path()
    data = dict(DEFAULT_SETTINGS)
    try:
        with open(path, "r", encoding="utf-8") as f:
            loaded = json.load(f)
        if isinstance(loaded, dict):
            data.update({k: v for k, v in loaded.items() if k in DEFAULT_SETTINGS})
    except (FileNotFoundError, json.JSONDecodeError, OSError):
        pass
    if data.get("theme") not in VALID_THEMES:
        data["theme"] = DEFAULT_THEME
    return data


def save_settings(settings):
    path = get_settings_path()
    data = dict(DEFAULT_SETTINGS)
    data.update({k: v for k, v in settings.items() if k in DEFAULT_SETTINGS})
    try:
        with open(path, "w", encoding="utf-8") as f:
            json.dump(data, f, ensure_ascii=False, indent=2)
        return True
    except OSError:
        return False


# ---------------------------------------------------------------------
# stdout -> очередь (все три GUI перехватывают консольный вывод process_jar
# одинаково, поток пишет в очередь, GUI-поток вычитывает через таймер/poll).
# ---------------------------------------------------------------------
class QueueWriter:
    def __init__(self, q):
        self.q = q
        self._buf = ""

    def write(self, text):
        self._buf += text
        while "\n" in self._buf:
            line, self._buf = self._buf.split("\n", 1)
            self.q.put(("log", line))

    def flush(self):
        pass

    def isatty(self):
        return False


def new_queue():
    return queue.Queue()


# ---------------------------------------------------------------------
# Сводка по jar для карточки на экране декомпиляции.
# ---------------------------------------------------------------------
def java_version_from_major(major):
    if major is None:
        return "?"
    if major <= 48:
        return f"Java 1.{major - 44}"
    return f"Java {major - 44}"


def jar_summary(jar_path):
    info = {
        "name": os.path.basename(jar_path),
        "size": "?", "classes": 0, "packages": 0, "java": "?", "plugin_name": None,
    }
    try:
        size_bytes = os.path.getsize(jar_path)
        info["size"] = (f"{size_bytes / 1024 / 1024:.1f} МБ" if size_bytes >= 1024 * 1024
                         else f"{size_bytes / 1024:.1f} КБ")
    except OSError:
        pass
    try:
        with zipfile.ZipFile(jar_path) as z:
            names = z.namelist()
            class_names = [n for n in names if n.endswith(".class") and "module-info" not in n]
            info["classes"] = len(class_names)
            pkgs = {n.rsplit("/", 1)[0] for n in class_names if "/" in n}
            info["packages"] = len(pkgs)
            if class_names:
                try:
                    head = z.read(class_names[0])[:8]
                    if len(head) == 8:
                        major = struct.unpack(">H", head[6:8])[0]
                        info["java"] = java_version_from_major(major)
                except Exception:
                    pass
            if "plugin.yml" in names:
                try:
                    text = z.read("plugin.yml").decode("utf-8", errors="replace")
                    for line in text.splitlines():
                        if line.strip().startswith("name:"):
                            info["plugin_name"] = line.split(":", 1)[1].strip().strip("'\"")
                            break
                except Exception:
                    pass
    except Exception:
        pass
    return info


def default_out_dir_for(jar_path, settings=None):
    base = os.path.splitext(os.path.basename(jar_path))[0]
    root = None
    if settings and settings.get("default_output_dir"):
        root = settings["default_output_dir"]
    if root:
        return os.path.join(root, base + "_decompiled")
    return os.path.join(os.path.dirname(jar_path), base + "_decompiled")


def open_in_vscode(path, on_error):
    code_path = shutil.which("code") or shutil.which("code.cmd") or shutil.which("code.exe")
    if not code_path:
        on_error("VS Code не найден в PATH (команда 'code'). Установите VS Code и при "
                  "установке включите 'Add to PATH', либо откройте папку вручную.")
        return
    try:
        subprocess.Popen([code_path, path])
    except Exception as e:
        on_error(f"Не удалось запустить VS Code: {type(e).__name__}: {e}")


def open_in_file_manager(path):
    """Кросс-платформенное 'открыть папку в проводнике' - старый код делал
    только os.startfile (Windows-only), но настройки/движок теперь не
    жёстко Windows-only, так что добавляем fallback."""
    try:
        if platform.system() == "Windows":
            os.startfile(path)  # noqa
        elif platform.system() == "Darwin":
            subprocess.Popen(["open", path])
        else:
            subprocess.Popen(["xdg-open", path])
    except Exception:
        pass
