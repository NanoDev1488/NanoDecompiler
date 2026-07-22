# -*- coding: utf-8 -*-
"""
Диспетчер GUI (только Windows - см. main.py::main()). Читает выбранную тему
из настроек (%LOCALAPPDATA%\\NanoDecompiler\\settings.json - см.
gui_common.py), запускает соответствующий движок:

    raw  -> gui_raw.py   (classic ttk, всегда доступна - stdlib)
    neon -> gui_neon.py  (customtkinter + Pillow)
    md3  -> gui_md3.py   (flet)

Если для выбранной темы не хватает зависимостей - откатывается по цепочке
md3 -> neon -> raw (первая, для которой зависимости на месте), печатает
почему, НЕ падает молча. raw не требует внешних зависимостей вообще, так что
цепочка гарантированно на чём-то остановится.

Если пользователь меняет тему на экране настроек - выбранный движок
закрывает своё окно (см. gui_raw.py/gui_neon.py/gui_md3.py::on_theme_change)
и этот диспетчер перезапускает уже новый движок (initial_jar НЕ повторяется
на перезапуске - только на самом первом запуске процесса).

История версий: до появления трёх тем это был один файл gui.py со связкой
customtkinter/classic напрямую (см. HANDOFF_1/2) - разбит на модули под
задачу из HANDOFF_3, п.1.
"""
import sys

import gui_common as common


def _deps_ok(theme):
    if theme == "raw":
        return True, None
    if theme == "neon":
        try:
            import customtkinter  # noqa: F401
            from PIL import Image  # noqa: F401
            return True, None
        except ImportError as e:
            return False, f"customtkinter/Pillow не установлены ({e})"
    if theme == "md3":
        try:
            import flet  # noqa: F401
            return True, None
        except ImportError as e:
            return False, f"flet не установлен ({e})"
    return False, f"неизвестная тема: {theme!r}"


_FALLBACK_CHAIN = {
    "md3": ["md3", "neon", "raw"],
    "neon": ["neon", "raw"],
    "raw": ["raw"],
}

_INSTALL_HINT = {
    "neon": "pip install customtkinter pillow",
    "md3": "pip install flet",
}


def _resolve_theme(requested):
    chain = _FALLBACK_CHAIN.get(requested, ["raw"])
    for theme in chain:
        ok, reason = _deps_ok(theme)
        if ok:
            if theme != requested:
                print(f"[*] Тема '{requested}' недоступна: {reason}")
                if requested in _INSTALL_HINT:
                    print(f"[*] Чтобы включить её: {_INSTALL_HINT[requested]}")
                print(f"[*] Запускаю тему '{theme}' вместо неё...")
            return theme
        print(f"[*] Тема '{theme}' недоступна: {reason}")
    return "raw"  # недостижимо на практике (raw всегда ok), но на всякий случай


def _run_theme(theme, initial_jar, on_theme_change):
    if theme == "raw":
        import gui_raw
        gui_raw.run_gui(initial_jar, on_theme_change=on_theme_change)
    elif theme == "neon":
        import gui_neon
        gui_neon.run_gui(initial_jar, on_theme_change=on_theme_change)
    elif theme == "md3":
        import gui_md3
        gui_md3.run_gui(initial_jar, on_theme_change=on_theme_change)
    else:
        import gui_raw
        gui_raw.run_gui(initial_jar, on_theme_change=on_theme_change)


def run_gui(initial_jar=None):
    """Единственная публичная точка входа - main.py вызывает только эту
    функцию (никогда напрямую gui_raw/gui_neon/gui_md3), чтобы фолбэк и
    перезапуск при смене темы работали независимо от того, что вызывает
    GUI."""
    jar_for_this_launch = initial_jar
    while True:
        pending_theme = {}

        def on_theme_change(new_theme, _pending=pending_theme):
            _pending["theme"] = new_theme

        settings = common.load_settings()
        theme = _resolve_theme(settings.get("theme", common.DEFAULT_THEME))
        _run_theme(theme, jar_for_this_launch, on_theme_change)

        jar_for_this_launch = None  # не переоткрывать jar при перезапуске
        if "theme" not in pending_theme:
            break  # окно закрыто пользователем как обычно - выходим


if __name__ == "__main__":
    run_gui(sys.argv[1] if len(sys.argv) > 1 else None)
