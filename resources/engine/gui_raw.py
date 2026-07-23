# -*- coding: utf-8 -*-
"""
Тема "Сырая" - classic ttk, чистый stdlib (никаких customtkinter/flet).
Раньше это был единственный fallback-вариант gui.py, если customtkinter не
установлена. Теперь это отдельная равноправная тема (см. HANDOFF_3, п.1):
пользователь может выбрать её осознанно, не только как fallback.

Точка входа: run_gui(initial_jar=None, on_theme_change=None).
on_theme_change(new_theme) вызывается, если пользователь меняет тему на
экране настроек - диспетчер (gui.py) должен перезапустить нужный движок.
"""
import os
import queue as _queue_mod
import sys
import threading
import tkinter as tk
from tkinter import filedialog, messagebox, ttk, scrolledtext

from main import process_jar, NANO_DECOMPILER_VERSION, classify_line
import gui_common as common

BG_ROOT = "#0b1622"
BG_PANEL = "#0f2036"
BG_ENTRY = "#0c1b2c"
BG_LOG = "#081120"
FG_TEXT = "#dbe9fa"
FG_DIM = "#6f93b3"
ACCENT = "#3fa9f5"
ACCENT_HOVER = "#5cc1ff"
ACCENT_DARK = "#153450"
BORDER = "#1c3a57"

COLOR_INFO = "#4fb8ff"
COLOR_OK = "#4fe38b"
COLOR_WARN = "#e8b339"
COLOR_ERROR = "#ff6b6b"
COLOR_BANNER = "#7fd4ff"
COLOR_DIM = FG_DIM


def _open_settings_dialog(parent, on_theme_change):
    settings = common.load_settings()
    win = tk.Toplevel(parent)
    win.title("Настройки")
    win.configure(bg=BG_PANEL)
    win.geometry("420x260")
    win.resizable(False, False)
    win.transient(parent)
    win.grab_set()

    pad = {"padx": 16, "pady": 6}
    ttk.Label(win, text="Тема оформления:", style="Dim.TLabel").pack(anchor="w", **pad)
    theme_var = tk.StringVar(value=settings["theme"])
    for value, label in (("raw", "Сырая (classic ttk)"),
                          ("neon", "Неоновая (customtkinter)"),
                          ("md3", "MD3 (Flet, Material Design 3)")):
        ttk.Radiobutton(win, text=label, value=value, variable=theme_var).pack(anchor="w", padx=28)

    ttk.Label(win, text="Папка для результатов по умолчанию:", style="Dim.TLabel").pack(
        anchor="w", **pad)
    out_row = ttk.Frame(win)
    out_row.pack(fill="x", padx=16)
    out_var = tk.StringVar(value=settings.get("default_output_dir", ""))
    ttk.Entry(out_row, textvariable=out_var, style="Dark.TEntry").pack(
        side="left", fill="x", expand=True)

    def _pick_dir():
        path = filedialog.askdirectory(title="Папка для результатов по умолчанию")
        if path:
            out_var.set(path)

    ttk.Button(out_row, text="Обзор...", style="Accent.TButton", command=_pick_dir).pack(
        side="left", padx=(6, 0))

    def _save():
        new_theme = theme_var.get()
        changed = new_theme != settings["theme"]
        settings["theme"] = new_theme
        settings["default_output_dir"] = out_var.get().strip()
        ok = common.save_settings(settings)
        if not ok:
            messagebox.showerror(NANO_DECOMPILER_VERSION, "Не удалось сохранить настройки.")
            return
        win.destroy()
        if changed and on_theme_change:
            on_theme_change(new_theme)

    btns = ttk.Frame(win)
    btns.pack(fill="x", padx=16, pady=(14, 10))
    ttk.Button(btns, text="Сохранить", style="Primary.TButton", command=_save).pack(side="left")
    ttk.Button(btns, text="Отмена", style="Accent.TButton", command=win.destroy).pack(
        side="left", padx=8)


class RawApp:
    def __init__(self, root, initial_jar=None, on_theme_change=None):
        self.root = root
        self.on_theme_change = on_theme_change
        self.root.title(NANO_DECOMPILER_VERSION)
        self.root.geometry("820x540")
        self.root.minsize(640, 400)
        self.root.configure(bg=BG_ROOT)
        try:
            self.root.attributes("-alpha", 0.94)
        except Exception:
            pass

        self._setup_style()
        self.queue = common.new_queue()
        self.running = False
        self.last_out_dir = None
        self.settings = common.load_settings()

        top = ttk.Frame(root, style="Panel.TFrame", padding=10)
        top.pack(fill="x", padx=10, pady=(10, 4))
        ttk.Label(top, text=".jar плагина:", style="Dim.TLabel").grid(row=0, column=0, sticky="w")
        self.jar_var = tk.StringVar()
        ttk.Entry(top, textvariable=self.jar_var, style="Dark.TEntry").grid(
            row=0, column=1, sticky="ew", padx=6)
        ttk.Button(top, text="Обзор...", style="Accent.TButton",
                   command=self._pick_jar).grid(row=0, column=2)
        ttk.Label(top, text="Папка для результата:", style="Dim.TLabel").grid(
            row=1, column=0, sticky="w", pady=(8, 0))
        self.out_var = tk.StringVar()
        ttk.Entry(top, textvariable=self.out_var, style="Dark.TEntry").grid(
            row=1, column=1, sticky="ew", padx=6, pady=(8, 0))
        ttk.Button(top, text="Обзор...", style="Accent.TButton",
                   command=self._pick_out_dir).grid(row=1, column=2, pady=(8, 0))
        top.columnconfigure(1, weight=1)

        btn_row = ttk.Frame(root, style="Root.TFrame")
        btn_row.pack(fill="x", padx=10, pady=4)
        self.run_btn = ttk.Button(btn_row, text="Декомпилировать", style="Primary.TButton",
                                   command=self._start)
        self.run_btn.pack(side="left")
        self.open_btn = ttk.Button(btn_row, text="Открыть папку с результатом", style="Accent.TButton",
                                    command=self._open_out_dir, state="disabled")
        self.open_btn.pack(side="left", padx=8)
        self.vscode_btn = ttk.Button(btn_row, text="</> Открыть в VS Code", style="Accent.TButton",
                                      command=self._open_in_vscode, state="disabled")
        self.vscode_btn.pack(side="left", padx=8)
        ttk.Button(btn_row, text="⚙ Настройки", style="Accent.TButton",
                   command=self._open_settings).pack(side="right")

        log_frame = tk.Frame(root, bg=BORDER, bd=0)
        log_frame.pack(fill="both", expand=True, padx=10, pady=6)
        self.log = scrolledtext.ScrolledText(
            log_frame, state="disabled", wrap="none", font=("Consolas", 9),
            bg=BG_LOG, fg=FG_TEXT, insertbackground=FG_TEXT,
            selectbackground=ACCENT_DARK, selectforeground=FG_TEXT,
            relief="flat", bd=0, highlightthickness=1,
            highlightbackground=BORDER, highlightcolor=ACCENT,
        )
        self.log.pack(fill="both", expand=True, padx=1, pady=1)
        for tag, color in (("banner", COLOR_BANNER), ("info", COLOR_INFO), ("ok", COLOR_OK),
                           ("warn", COLOR_WARN), ("error", COLOR_ERROR), ("dim", COLOR_DIM)):
            self.log.tag_configure(tag, foreground=color)

        self.status_var = tk.StringVar(value="Готов к работе.")
        ttk.Label(root, textvariable=self.status_var, style="Dim.TLabel", anchor="w").pack(
            fill="x", padx=12, pady=(0, 8))

        self.root.after(100, self._poll_queue)
        if initial_jar and os.path.isfile(initial_jar):
            self.jar_var.set(initial_jar)
            self.out_var.set(common.default_out_dir_for(initial_jar, self.settings))
            self.root.after(300, self._start)

    def _setup_style(self):
        style = ttk.Style(self.root)
        try:
            style.theme_use("clam")
        except Exception:
            pass
        style.configure("Root.TFrame", background=BG_ROOT)
        style.configure("Panel.TFrame", background=BG_PANEL)
        style.configure("Dim.TLabel", background=BG_ROOT, foreground=FG_DIM, font=("Segoe UI", 9))
        style.configure("TLabel", background=BG_PANEL, foreground=FG_TEXT)
        style.configure("Dark.TEntry", fieldbackground=BG_ENTRY, background=BG_ENTRY,
                         foreground=FG_TEXT, insertcolor=FG_TEXT, borderwidth=1, relief="flat")
        style.map("Dark.TEntry", fieldbackground=[("focus", BG_ENTRY)])
        style.configure("Accent.TButton", background=ACCENT_DARK, foreground=FG_TEXT,
                         borderwidth=0, focusthickness=0, padding=(10, 6))
        style.map("Accent.TButton", background=[("active", ACCENT), ("disabled", BG_PANEL)],
                  foreground=[("disabled", FG_DIM)])
        style.configure("Primary.TButton", background=ACCENT, foreground="#04121f",
                         borderwidth=0, focusthickness=0, padding=(14, 8), font=("Segoe UI", 9, "bold"))
        style.map("Primary.TButton", background=[("active", ACCENT_HOVER), ("disabled", BG_PANEL)],
                  foreground=[("disabled", FG_DIM)])

    def _open_settings(self):
        _open_settings_dialog(self.root, self._handle_theme_change)

    def _handle_theme_change(self, new_theme):
        if self.on_theme_change:
            self.on_theme_change(new_theme)
        # Закрываем окно, чтобы диспетчер (gui.py) перезапустил нужный
        # движок - каждая тема живёт в своём toolkit'е (tkinter/customtkinter/
        # flet), "горячего" переключения без перезапуска процесса нет.
        self.root.after(150, self.root.destroy)

    def _pick_jar(self):
        path = filedialog.askopenfilename(title="Выберите .jar плагина",
                                           filetypes=[("JAR-файлы", "*.jar"), ("Все файлы", "*.*")])
        if path:
            self.jar_var.set(path)
            if not self.out_var.get():
                self.out_var.set(common.default_out_dir_for(path, self.settings))

    def _pick_out_dir(self):
        path = filedialog.askdirectory(title="Папка для результата")
        if path:
            self.out_var.set(path)

    def _append_log(self, line):
        tag = classify_line(line)
        self.log.configure(state="normal")
        self.log.insert("end", line + "\n", tag)
        self.log.see("end")
        self.log.configure(state="disabled")

    def _start(self):
        if self.running:
            return
        jar_path = self.jar_var.get().strip()
        if not jar_path:
            messagebox.showwarning(NANO_DECOMPILER_VERSION, "Сначала выберите .jar файл плагина.")
            return
        if not os.path.isfile(jar_path):
            messagebox.showerror(NANO_DECOMPILER_VERSION, f"Файл не найден:\n{jar_path}")
            return
        out_dir = self.out_var.get().strip() or common.default_out_dir_for(jar_path, self.settings)
        self.log.configure(state="normal")
        self.log.delete("1.0", "end")
        self.log.configure(state="disabled")
        self.open_btn.configure(state="disabled")
        self.vscode_btn.configure(state="disabled")
        self.run_btn.configure(state="disabled")
        self.status_var.set("Идёт декомпиляция...")
        self.running = True
        threading.Thread(target=self._worker, args=(jar_path, out_dir), daemon=True).start()

    def _worker(self, jar_path, out_dir):
        old_stdout = sys.stdout
        sys.stdout = common.QueueWriter(self.queue)
        try:
            process_jar(jar_path, out_dir)
            self.queue.put(("done", out_dir))
        except Exception as e:
            self.queue.put(("error", f"{type(e).__name__}: {e}"))
        finally:
            sys.stdout = old_stdout

    def _poll_queue(self):
        try:
            while True:
                kind, payload = self.queue.get_nowait()
                if kind == "log":
                    self._append_log(payload)
                elif kind == "done":
                    self.last_out_dir = payload
                    self._append_log(f"[+] Готово. Результат в: {payload}")
                    self.status_var.set("Готово.")
                    self.open_btn.configure(state="normal")
                    self.vscode_btn.configure(state="normal")
                    self.run_btn.configure(state="normal")
                    self.running = False
                    messagebox.showinfo(NANO_DECOMPILER_VERSION,
                                         f"Декомпиляция завершена.\n\nРезультат: {payload}")
                elif kind == "error":
                    self._append_log(f"[!] ОШИБКА: {payload}")
                    self.status_var.set("Ошибка.")
                    self.run_btn.configure(state="normal")
                    self.running = False
                    messagebox.showerror(NANO_DECOMPILER_VERSION,
                                          f"Не удалось завершить декомпиляцию:\n\n{payload}")
        except _queue_mod.Empty:
            pass
        self.root.after(100, self._poll_queue)

    def _open_out_dir(self):
        if self.last_out_dir and os.path.isdir(self.last_out_dir):
            common.open_in_file_manager(self.last_out_dir)

    def _open_in_vscode(self):
        if self.last_out_dir:
            common.open_in_vscode(self.last_out_dir,
                                   lambda msg: messagebox.showwarning(NANO_DECOMPILER_VERSION, msg))


def run_gui(initial_jar=None, on_theme_change=None):
    root = tk.Tk()
    RawApp(root, initial_jar, on_theme_change=on_theme_change)
    root.mainloop()


if __name__ == "__main__":
    run_gui(sys.argv[1] if len(sys.argv) > 1 else None)
