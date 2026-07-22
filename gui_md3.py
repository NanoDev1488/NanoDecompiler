# -*- coding: utf-8 -*-
"""
Тема "MD3" - Flet (https://flet.dev), НЕ входит в stdlib: pip install flet.
Полностью отдельная архитектура виджетов (Flutter-виджеты через Flet, а не
tkinter) - см. HANDOFF_3, п.1. Бизнес-логика (process_jar, захват stdout,
classify_line, настройки) переиспользуется из main.py/gui_common.py - здесь
только сам UI-слой и Material Design 3 токены (Flet идёт со встроенной
поддержкой MD3 через ft.Theme(color_scheme_seed=...), поэтому токены не
собираются вручную, как в customtkinter-темах, а задаются через seed-цвет +
явные overrides только там, где нужно (лог - моноширинный, тёмная "sunken"
поверхность)).

Точка входа: run_gui(initial_jar=None, on_theme_change=None). on_theme_change
вызывается диспетчером после закрытия окна, если пользователь сменил тему на
экране настроек (Flet - однопроцессное веб/desktop-окно, поэтому реального
"мгновенного" переключения на другой тулкит нет - как и у customtkinter/
tkinter, требуется перезапуск, страница просто явно об этом предупреждает).
"""
import os
import sys
import threading

from main import process_jar, NANO_DECOMPILER_VERSION, classify_line
import gui_common as common

SEED_COLOR = "#39FF6A"  # тот же терминальный акцент, что и в neon-теме -
                          # единая айдентика продукта поверх трёх движков
LOG_BG = "#0b0f0d"

_LINE_COLOR = {
    "banner": "#7fd4ff",
    "info": "#4fb8ff",
    "ok": "#39ff6a",
    "warn": "#ffb000",
    "error": "#ff4d5e",
    "dim": "#6f93b3",
}


def run_gui(initial_jar=None, on_theme_change=None):
    import flet as ft

    def main(page: ft.Page):
        page.title = NANO_DECOMPILER_VERSION
        page.theme_mode = ft.ThemeMode.DARK
        page.theme = ft.Theme(color_scheme_seed=SEED_COLOR, use_material3=True)
        page.window.width = 960
        page.window.height = 640
        page.window.min_width = 720
        page.window.min_height = 480
        page.padding = 0

        state = {
            "settings": common.load_settings(),
            "running": False,
            "last_out_dir": None,
        }

        jar_field = ft.TextField(label=".jar плагина", expand=True, dense=True)
        out_field = ft.TextField(label="Папка для результата", expand=True, dense=True)

        summary_values = {
            "size": ft.Text("--", weight=ft.FontWeight.BOLD),
            "java": ft.Text("--", weight=ft.FontWeight.BOLD),
            "classes": ft.Text("--", weight=ft.FontWeight.BOLD),
            "packages": ft.Text("--", weight=ft.FontWeight.BOLD),
        }

        def _summary_card(label, value_text):
            return ft.Container(
                content=ft.Column([
                    ft.Text(label, size=11, color=ft.Colors.ON_SURFACE_VARIANT),
                    value_text,
                ], spacing=2),
                padding=12,
                border_radius=12,
                bgcolor=ft.Colors.SURFACE_CONTAINER_HIGHEST,
                expand=True,
            )

        summary_row = ft.Row([
            _summary_card("РАЗМЕР", summary_values["size"]),
            _summary_card("JAVA", summary_values["java"]),
            _summary_card("КЛАССОВ", summary_values["classes"]),
            _summary_card("ПАКЕТОВ", summary_values["packages"]),
        ], spacing=10)

        log_view = ft.ListView(expand=True, spacing=0, auto_scroll=True)
        log_container = ft.Container(
            content=log_view, bgcolor=LOG_BG, border_radius=12, padding=10, expand=True,
            border=ft.border.all(1, ft.Colors.OUTLINE_VARIANT),
        )

        status_text = ft.Text("Готов к работе.", color=ft.Colors.ON_SURFACE_VARIANT, size=12)
        progress = ft.ProgressBar(visible=False, width=None)

        def append_log(line):
            tag = classify_line(line)
            log_view.controls.append(
                ft.Text(line, font_family="Consolas", size=12,
                        color=_LINE_COLOR.get(tag, ft.Colors.ON_SURFACE), selectable=True))
            if len(log_view.controls) > 4000:
                del log_view.controls[:1000]
            page.update()

        def update_summary(jar_path):
            info = common.jar_summary(jar_path)
            summary_values["size"].value = info["size"]
            summary_values["java"].value = info["java"]
            summary_values["classes"].value = str(info["classes"])
            summary_values["packages"].value = str(info["packages"])
            page.update()

        def pick_jar_result(e: "ft.FilePickerResultEvent"):
            if e.files:
                path = e.files[0].path
                jar_field.value = path
                update_summary(path)
                if not out_field.value:
                    out_field.value = common.default_out_dir_for(path, state["settings"])
                page.update()

        def pick_out_result(e: "ft.FilePickerResultEvent"):
            if e.path:
                out_field.value = e.path
                page.update()

        jar_picker = ft.FilePicker(on_result=pick_jar_result)
        out_picker = ft.FilePicker(on_result=pick_out_result)
        page.overlay.extend([jar_picker, out_picker])

        run_btn = ft.FilledButton("Декомпилировать", icon=ft.Icons.PLAY_ARROW,
                                   on_click=lambda e: start())
        open_btn = ft.OutlinedButton("Открыть папку с результатом", icon=ft.Icons.FOLDER_OPEN,
                                      disabled=True, on_click=lambda e: open_out_dir())
        vscode_btn = ft.OutlinedButton("Открыть в VS Code", icon=ft.Icons.CODE,
                                        disabled=True, on_click=lambda e: open_vscode())

        class _DirectWriter:
            """В отличие от tkinter-тем, Flet безопасно принимает
            page.update() из фонового потока (сообщение просто уходит по
            веб-сокету) - поэтому здесь можно писать в лог напрямую, без
            очереди/поллинга через after()."""
            def __init__(self):
                self._buf = ""

            def write(self, text):
                self._buf += text
                while "\n" in self._buf:
                    line, self._buf = self._buf.split("\n", 1)
                    append_log(line)

            def flush(self):
                pass

            def isatty(self):
                return False

        def finish_ok(out_dir):
            state["last_out_dir"] = out_dir
            append_log(f"[+] Готово. Результат в: {out_dir}")
            status_text.value = "Готово."
            progress.visible = False
            open_btn.disabled = False
            vscode_btn.disabled = False
            run_btn.disabled = False
            state["running"] = False
            page.update()

        def finish_error(message):
            append_log(f"[!] ОШИБКА: {message}")
            status_text.value = "Ошибка."
            progress.visible = False
            run_btn.disabled = False
            state["running"] = False
            page.update()
            page.open(ft.SnackBar(ft.Text(f"Сбой декомпиляции: {message}"),
                                   bgcolor=ft.Colors.ERROR_CONTAINER))

        def worker(jar_path, out_dir):
            old_stdout = sys.stdout
            sys.stdout = _DirectWriter()
            try:
                process_jar(jar_path, out_dir)
                finish_ok(out_dir)
            except Exception as e:
                finish_error(f"{type(e).__name__}: {e}")
            finally:
                sys.stdout = old_stdout

        def start():
            if state["running"]:
                return
            jar_path = (jar_field.value or "").strip()
            if not jar_path:
                page.open(ft.SnackBar(ft.Text("Сначала выберите .jar файл плагина.")))
                return
            if not os.path.isfile(jar_path):
                page.open(ft.SnackBar(ft.Text(f"Файл не найден: {jar_path}")))
                return
            out_dir = (out_field.value or "").strip() or common.default_out_dir_for(
                jar_path, state["settings"])
            log_view.controls.clear()
            open_btn.disabled = True
            vscode_btn.disabled = True
            run_btn.disabled = True
            progress.visible = True
            status_text.value = "Идёт декомпиляция..."
            state["running"] = True
            page.update()
            threading.Thread(target=worker, args=(jar_path, out_dir), daemon=True).start()

        def open_out_dir():
            if state["last_out_dir"] and os.path.isdir(state["last_out_dir"]):
                common.open_in_file_manager(state["last_out_dir"])

        def open_vscode():
            if state["last_out_dir"]:
                common.open_in_vscode(
                    state["last_out_dir"],
                    lambda msg: page.open(ft.SnackBar(ft.Text(msg))))

        # ---- экран настроек (вторая "страница" через NavigationRail) ----
        theme_group = ft.RadioGroup(
            value=state["settings"]["theme"],
            content=ft.Column([
                ft.Radio(value="raw", label="Сырая (classic ttk)"),
                ft.Radio(value="neon", label="Неоновая (customtkinter)"),
                ft.Radio(value="md3", label="MD3 (Flet, текущая)"),
            ]))
        settings_out_field = ft.TextField(
            label="Папка для результатов по умолчанию",
            value=state["settings"].get("default_output_dir", ""), expand=True, dense=True)

        def pick_settings_dir_result(e: "ft.FilePickerResultEvent"):
            if e.path:
                settings_out_field.value = e.path
                page.update()

        settings_dir_picker = ft.FilePicker(on_result=pick_settings_dir_result)
        page.overlay.append(settings_dir_picker)

        def save_settings(e):
            new_theme = theme_group.value
            changed = new_theme != state["settings"]["theme"]
            state["settings"]["theme"] = new_theme
            state["settings"]["default_output_dir"] = (settings_out_field.value or "").strip()
            ok = common.save_settings(state["settings"])
            if not ok:
                page.open(ft.SnackBar(ft.Text("Не удалось сохранить настройки.")))
                return
            if changed:
                page.open(ft.SnackBar(ft.Text(
                    "Тема сохранена, перезапускаю интерфейс...")))
                if on_theme_change:
                    on_theme_change(new_theme)
                # Flet-окно не может "на лету" стать tkinter/customtkinter -
                # закрываем, диспетчер (gui.py) перезапустит нужный движок.
                page.window.close()
            else:
                page.open(ft.SnackBar(ft.Text("Настройки сохранены.")))

        settings_view = ft.Column([
            ft.Text("Тема оформления", weight=ft.FontWeight.BOLD),
            theme_group,
            ft.Divider(),
            ft.Text("Результаты", weight=ft.FontWeight.BOLD),
            ft.Row([settings_out_field,
                    ft.IconButton(ft.Icons.FOLDER_OPEN,
                                  on_click=lambda e: settings_dir_picker.get_directory_path())]),
            ft.FilledButton("Сохранить", icon=ft.Icons.SAVE, on_click=save_settings),
        ], spacing=14, visible=False)

        decompile_view = ft.Column([
            ft.Row([jar_field, ft.IconButton(ft.Icons.FILE_OPEN,
                                              on_click=lambda e: jar_picker.pick_files(
                                                  allowed_extensions=["jar"]))]),
            ft.Row([out_field, ft.IconButton(ft.Icons.FOLDER_OPEN,
                                              on_click=lambda e: out_picker.get_directory_path())]),
            summary_row,
            ft.Row([run_btn, open_btn, vscode_btn]),
            progress,
            log_container,
            status_text,
        ], spacing=10, expand=True)

        def nav_change(e):
            idx = e.control.selected_index
            decompile_view.visible = idx == 0
            settings_view.visible = idx == 1
            page.update()

        rail = ft.NavigationRail(
            selected_index=0,
            label_type=ft.NavigationRailLabelType.ALL,
            destinations=[
                ft.NavigationRailDestination(icon=ft.Icons.TERMINAL, label="Декомпиляция"),
                ft.NavigationRailDestination(icon=ft.Icons.SETTINGS, label="Настройки"),
            ],
            on_change=nav_change,
        )

        page.add(
            ft.Row([
                rail,
                ft.VerticalDivider(width=1),
                ft.Container(content=ft.Stack([decompile_view, settings_view]),
                              expand=True, padding=18),
            ], expand=True)
        )

        if initial_jar and os.path.isfile(initial_jar):
            jar_field.value = initial_jar
            out_field.value = common.default_out_dir_for(initial_jar, state["settings"])
            update_summary(initial_jar)
            page.update()
            start()

    ft.app(target=main)


if __name__ == "__main__":
    run_gui(sys.argv[1] if len(sys.argv) > 1 else None)
