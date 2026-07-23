# -*- coding: utf-8 -*-
"""
Тема "Неоновая" - customtkinter, terminal/hacker-эстетика вместо шаблонного
"neon gradient" вида. Токены выбраны по гайду HANDOFF_3 п.1 (следуем
frontend-design skill'у из
Frontend-Design-SKILLS-for-AI, раздел "Technical / Mono" в aesthetics.md):
истинно чёрный фон, ОДИН акцент (кислотно-зелёный терминальный), моноширинный
шрифт везде (включая заголовки - "интерфейс это документация"), статус-точки
●/◯ вместо иконок, острые углы у большинства элементов (скругление только у
главной CTA-кнопки), никаких градиентов/glassmorphism.

Требует: customtkinter, Pillow (ставится вместе с customtkinter).
Если не установлены - диспетчер (gui.py) откатывается на другую тему сам,
этот файл предполагает, что зависимости уже проверены до вызова run_gui().
"""
import math
import os
import queue as _queue_mod
import sys
import threading
import tkinter as tk

from main import process_jar, NANO_DECOMPILER_VERSION, classify_line
import gui_common as common

# ---- токены (surface/ink/hairline/accent/state) - см. color.md скилла ----
SURFACE = "#050706"
SURFACE_ELEVATED = "#0a0d0b"
SURFACE_SUNKEN = "#000000"
INK = "#e9fff1"
INK_MUTED = "#5f8f72"
INK_SUBTLE = "#33473c"
HAIRLINE = "#163326"
HAIRLINE_STRONG = "#2ba15c"
ACCENT = "#39ff6a"
ACCENT_INK = "#03130a"
ACCENT_SOFT = "#0e2418"
STATE_OK = "#39ff6a"
STATE_WARN = "#ffb000"
STATE_ERROR = "#ff4d5e"
STATE_INFO = "#39d3ff"

MONO_FONT = "Consolas"  # fallback-цепочку задаём при создании CTkFont ниже


def _mono(size=12, weight="normal"):
    import customtkinter as ctk
    for family in ("JetBrains Mono", "Cascadia Mono", "Consolas", "DejaVu Sans Mono"):
        try:
            f = ctk.CTkFont(family=family, size=size, weight=weight)
            if f.cget("family") == family:
                return f
        except Exception:
            continue
    return ctk.CTkFont(size=size, weight=weight)


def _status_dot(state):
    return {"idle": "◯", "running": "●", "ok": "●", "error": "●"}.get(state, "◯")


def _open_settings_dialog(parent, on_theme_change):
    import customtkinter as ctk
    from tkinter import filedialog, messagebox

    settings = common.load_settings()
    win = ctk.CTkToplevel(parent)
    win.title("НАСТРОЙКИ")
    win.geometry("440x300")
    win.resizable(False, False)
    win.configure(fg_color=SURFACE)
    win.transient(parent)
    win.grab_set()

    ctk.CTkLabel(win, text="> ТЕМА ОФОРМЛЕНИЯ", text_color=ACCENT, font=_mono(11, "bold"),
                 anchor="w").pack(fill="x", padx=18, pady=(16, 4))
    theme_var = tk.StringVar(value=settings["theme"])
    for value, label in (("raw", "raw   -- classic ttk"),
                          ("neon", "neon  -- customtkinter (текущая)"),
                          ("md3", "md3   -- flet / Material Design 3")):
        ctk.CTkRadioButton(win, text=label, value=value, variable=theme_var,
                            fg_color=ACCENT, hover_color=ACCENT, text_color=INK,
                            font=_mono(11)).pack(anchor="w", padx=32, pady=2)

    ctk.CTkLabel(win, text="> ПАПКА РЕЗУЛЬТАТОВ ПО УМОЛЧАНИЮ", text_color=ACCENT,
                 font=_mono(11, "bold"), anchor="w").pack(fill="x", padx=18, pady=(16, 4))
    row = ctk.CTkFrame(win, fg_color="transparent")
    row.pack(fill="x", padx=18)
    out_var = tk.StringVar(value=settings.get("default_output_dir", ""))
    ctk.CTkEntry(row, textvariable=out_var, fg_color=SURFACE_SUNKEN, border_color=HAIRLINE,
                 text_color=INK, font=_mono(10), corner_radius=0).pack(
        side="left", fill="x", expand=True)

    def _pick_dir():
        path = filedialog.askdirectory(title="Папка для результатов по умолчанию")
        if path:
            out_var.set(path)

    ctk.CTkButton(row, text="...", width=36, command=_pick_dir, fg_color=SURFACE_ELEVATED,
                  hover_color=ACCENT_SOFT, text_color=ACCENT, border_width=1,
                  border_color=HAIRLINE, corner_radius=0, font=_mono(11)).pack(
        side="left", padx=(6, 0))

    def _save():
        new_theme = theme_var.get()
        changed = new_theme != settings["theme"]
        settings["theme"] = new_theme
        settings["default_output_dir"] = out_var.get().strip()
        if not common.save_settings(settings):
            messagebox.showerror(NANO_DECOMPILER_VERSION, "Не удалось сохранить настройки.")
            return
        win.destroy()
        if changed and on_theme_change:
            on_theme_change(new_theme)
            # Закрываем главное окно - движки тем не переключаются "на лету"
            # (разные toolkit'ы), диспетчер (gui.py) перезапустит нужный.
            parent.after(150, parent.destroy)

    btns = ctk.CTkFrame(win, fg_color="transparent")
    btns.pack(fill="x", padx=18, pady=(20, 12))
    ctk.CTkButton(btns, text="[ СОХРАНИТЬ ]", command=_save, fg_color=ACCENT,
                  hover_color="#5cff8c", text_color=ACCENT_INK, corner_radius=4,
                  font=_mono(11, "bold")).pack(side="left")
    ctk.CTkButton(btns, text="ОТМЕНА", command=win.destroy, fg_color="transparent",
                  hover_color=SURFACE_ELEVATED, text_color=INK_MUTED, border_width=1,
                  border_color=HAIRLINE, corner_radius=0, font=_mono(11)).pack(
        side="left", padx=8)


def run_gui(initial_jar=None, on_theme_change=None):
    import customtkinter as ctk
    from tkinter import filedialog, messagebox
    from PIL import ImageTk

    ctk.set_appearance_mode("dark")
    ctk.set_default_color_theme("green")

    class NeonApp(ctk.CTk):
        def __init__(self):
            super().__init__(fg_color=SURFACE)
            self.title(NANO_DECOMPILER_VERSION)
            self.geometry("900x580")
            self.minsize(680, 440)
            try:
                icon_img = _make_neon_icon(256)
                self._icon_photo = ImageTk.PhotoImage(icon_img)
                self.iconphoto(True, self._icon_photo)
            except Exception:
                pass

            self.queue = common.new_queue()
            self.running = False
            self.last_out_dir = None
            self.settings = common.load_settings()

            # ---- signature element: терминальный "заголовок-приглашение" ----
            header = ctk.CTkFrame(self, fg_color=SURFACE_ELEVATED, corner_radius=0,
                                   border_width=1, border_color=HAIRLINE_STRONG, height=44)
            header.pack(fill="x")
            header.pack_propagate(False)
            self.status_dot_var = tk.StringVar(value="◯")
            self.status_dot_lbl = ctk.CTkLabel(header, textvariable=self.status_dot_var,
                                                text_color=INK_SUBTLE, font=_mono(14),
                                                fg_color="transparent")
            self.status_dot_lbl.pack(side="left", padx=(16, 6))
            ctk.CTkLabel(header, text="nano://decompiler", text_color=ACCENT,
                         font=_mono(13, "bold"), fg_color="transparent").pack(side="left")
            ctk.CTkLabel(header, text=f" v{NANO_DECOMPILER_VERSION.split()[-1]}"
                         if " " in NANO_DECOMPILER_VERSION else "",
                         text_color=INK_SUBTLE, font=_mono(11), fg_color="transparent").pack(side="left")
            ctk.CTkButton(header, text="⚙ settings", command=self._open_settings, width=100,
                          fg_color="transparent", hover_color=ACCENT_SOFT, text_color=INK_MUTED,
                          border_width=1, border_color=HAIRLINE, corner_radius=0,
                          font=_mono(10)).pack(side="right", padx=12, pady=6)

            body = ctk.CTkFrame(self, fg_color="transparent")
            body.pack(fill="both", expand=True, padx=16, pady=12)

            form = ctk.CTkFrame(body, fg_color=SURFACE_ELEVATED, corner_radius=0,
                                 border_width=1, border_color=HAIRLINE)
            form.pack(fill="x")
            form.grid_columnconfigure(1, weight=1)

            ctk.CTkLabel(form, text="jar$", text_color=ACCENT, font=_mono(12, "bold"),
                         fg_color="transparent").grid(row=0, column=0, sticky="w", padx=(14, 8), pady=(14, 6))
            self.jar_var = tk.StringVar()
            ctk.CTkEntry(form, textvariable=self.jar_var, fg_color=SURFACE_SUNKEN,
                         border_color=HAIRLINE, text_color=INK, font=_mono(11),
                         corner_radius=0).grid(row=0, column=1, sticky="ew", pady=(14, 6))
            ctk.CTkButton(form, text="выбрать", command=self._pick_jar, fg_color=SURFACE,
                          hover_color=ACCENT_SOFT, text_color=ACCENT, border_width=1,
                          border_color=HAIRLINE_STRONG, corner_radius=0, width=90,
                          font=_mono(10)).grid(row=0, column=2, padx=14, pady=(14, 6))

            ctk.CTkLabel(form, text="out$", text_color=ACCENT, font=_mono(12, "bold"),
                         fg_color="transparent").grid(row=1, column=0, sticky="w", padx=(14, 8), pady=(0, 14))
            self.out_var = tk.StringVar()
            ctk.CTkEntry(form, textvariable=self.out_var, fg_color=SURFACE_SUNKEN,
                         border_color=HAIRLINE, text_color=INK, font=_mono(11),
                         corner_radius=0).grid(row=1, column=1, sticky="ew", pady=(0, 14))
            ctk.CTkButton(form, text="выбрать", command=self._pick_out_dir, fg_color=SURFACE,
                          hover_color=ACCENT_SOFT, text_color=ACCENT, border_width=1,
                          border_color=HAIRLINE_STRONG, corner_radius=0, width=90,
                          font=_mono(10)).grid(row=1, column=2, padx=14, pady=(0, 14))

            self.summary = ctk.CTkFrame(body, fg_color="transparent")
            self.summary.pack(fill="x", pady=(10, 0))
            self.summary_labels = {}
            for i, (label, key) in enumerate((("SIZE", "size"), ("JAVA", "java"),
                                               ("CLASSES", "classes"), ("PACKAGES", "packages"))):
                col = ctk.CTkFrame(self.summary, fg_color=SURFACE_ELEVATED, corner_radius=0,
                                    border_width=1, border_color=HAIRLINE)
                col.pack(side="left", fill="x", expand=True, padx=(0 if i == 0 else 8, 0))
                ctk.CTkLabel(col, text=label, text_color=INK_SUBTLE, font=_mono(9),
                             fg_color="transparent").pack(anchor="w", padx=10, pady=(8, 0))
                val = ctk.CTkLabel(col, text="--", text_color=ACCENT, font=_mono(15, "bold"),
                                    fg_color="transparent")
                val.pack(anchor="w", padx=10, pady=(0, 8))
                self.summary_labels[key] = val

            btn_row = ctk.CTkFrame(body, fg_color="transparent")
            btn_row.pack(fill="x", pady=(12, 8))
            self.run_btn = ctk.CTkButton(btn_row, text="[ RUN ]", command=self._start,
                                          fg_color=ACCENT, hover_color="#5cff8c", text_color=ACCENT_INK,
                                          font=_mono(12, "bold"), corner_radius=4, height=36, width=120)
            self.run_btn.pack(side="left")
            self.open_btn = ctk.CTkButton(btn_row, text="open dir", command=self._open_out_dir,
                                           fg_color="transparent", hover_color=ACCENT_SOFT,
                                           text_color=INK_MUTED, border_width=1, border_color=HAIRLINE,
                                           corner_radius=0, height=36, state="disabled", font=_mono(11))
            self.open_btn.pack(side="left", padx=8)
            self.vscode_btn = ctk.CTkButton(btn_row, text="</> vscode", command=self._open_in_vscode,
                                             fg_color="transparent", hover_color=ACCENT_SOFT,
                                             text_color=INK_MUTED, border_width=1, border_color=HAIRLINE,
                                             corner_radius=0, height=36, state="disabled", font=_mono(11))
            self.vscode_btn.pack(side="left", padx=8)

            log_frame = ctk.CTkFrame(body, fg_color=SURFACE_SUNKEN, corner_radius=0,
                                      border_width=1, border_color=HAIRLINE)
            log_frame.pack(fill="both", expand=True)
            self.log = tk.Text(log_frame, state="disabled", wrap="none", font=(MONO_FONT, 9),
                                bg=SURFACE_SUNKEN, fg=INK, insertbackground=ACCENT,
                                selectbackground=ACCENT_SOFT, selectforeground=INK,
                                relief="flat", bd=0, highlightthickness=0)
            self.log.pack(fill="both", expand=True, padx=8, pady=8)
            for tag, color in (("banner", ACCENT), ("info", STATE_INFO), ("ok", STATE_OK),
                               ("warn", STATE_WARN), ("error", STATE_ERROR), ("dim", INK_MUTED)):
                self.log.tag_configure(tag, foreground=color)

            self.status_var = tk.StringVar(value="idle -- готов к работе")
            ctk.CTkLabel(body, textvariable=self.status_var, text_color=INK_MUTED,
                         font=_mono(10), fg_color="transparent", anchor="w").pack(
                fill="x", pady=(6, 0))

            self.after(100, self._poll_queue)
            if initial_jar and os.path.isfile(initial_jar):
                self.jar_var.set(initial_jar)
                self.out_var.set(common.default_out_dir_for(initial_jar, self.settings))
                self._update_summary(initial_jar)
                self.after(300, self._start)

        def _update_summary(self, jar_path):
            info = common.jar_summary(jar_path)
            self.summary_labels["size"].configure(text=info["size"])
            self.summary_labels["java"].configure(text=info["java"])
            self.summary_labels["classes"].configure(text=str(info["classes"]))
            self.summary_labels["packages"].configure(text=str(info["packages"]))

        def _open_settings(self):
            _open_settings_dialog(self, on_theme_change)

        def _pick_jar(self):
            path = filedialog.askopenfilename(title="Выберите .jar плагина",
                                               filetypes=[("JAR-файлы", "*.jar"), ("Все файлы", "*.*")])
            if path:
                self.jar_var.set(path)
                self._update_summary(path)
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

        def _set_status_dot(self, state):
            colors = {"idle": INK_SUBTLE, "running": ACCENT, "ok": STATE_OK, "error": STATE_ERROR}
            self.status_dot_var.set(_status_dot(state))
            self.status_dot_lbl.configure(text_color=colors.get(state, INK_SUBTLE))

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
            self.status_var.set("running -- идёт декомпиляция...")
            self._set_status_dot("running")
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
                        self._append_log(f"[+] готово. результат в: {payload}")
                        self.status_var.set("ok -- готово")
                        self._set_status_dot("ok")
                        self.open_btn.configure(state="normal")
                        self.vscode_btn.configure(state="normal")
                        self.run_btn.configure(state="normal")
                        self.running = False
                    elif kind == "error":
                        self._append_log(f"[!] ошибка: {payload}")
                        self.status_var.set("error -- сбой декомпиляции")
                        self._set_status_dot("error")
                        self.run_btn.configure(state="normal")
                        self.running = False
                        messagebox.showerror(NANO_DECOMPILER_VERSION,
                                              f"Не удалось завершить декомпиляцию:\n\n{payload}")
            except _queue_mod.Empty:
                pass
            self.after(100, self._poll_queue)

        def _open_out_dir(self):
            if self.last_out_dir and os.path.isdir(self.last_out_dir):
                common.open_in_file_manager(self.last_out_dir)

        def _open_in_vscode(self):
            if self.last_out_dir:
                common.open_in_vscode(self.last_out_dir,
                                       lambda msg: messagebox.showwarning(NANO_DECOMPILER_VERSION, msg))

    app = NeonApp()
    app.mainloop()


def _make_neon_icon(size=256):
    """Иконка в том же terminal-духе: скобки `{}` акцентным зелёным на
    истинно чёрном, курсор-подчёркивание снизу (мигающий курсор терминала,
    статично как последний штрих) - переиспользует то же начертание, что и
    основная иконка (см. HANDOFF_2), но с новой палитрой темы."""
    from PIL import Image, ImageDraw, ImageFont
    img = Image.new("RGBA", (size, size), (0, 0, 0, 0))
    d = ImageDraw.Draw(img)
    black = (5, 7, 6, 255)
    accent = (57, 255, 106, 255)
    border = (43, 161, 92, 255)

    pad = size // 42
    d.rectangle((pad, pad, size - pad, size - pad), fill=black, outline=border,
                width=max(2, size // 85))

    font = None
    for path in ("/usr/share/fonts/truetype/dejavu/DejaVuSansMono-Bold.ttf", "consolab.ttf"):
        try:
            font = ImageFont.truetype(path, int(size * 0.30))
            break
        except Exception:
            continue
    if font is None:
        font = ImageFont.load_default()

    text = "{}"
    bbox = d.textbbox((0, 0), text, font=font)
    tw, th = bbox[2] - bbox[0], bbox[3] - bbox[1]
    d.text((size / 2 - tw / 2 - bbox[0], size / 2 - th / 2 - bbox[1] - size * 0.03),
           text, font=font, fill=accent)

    cursor_w, cursor_h = size * 0.22, size * 0.045
    d.rectangle((size / 2 - cursor_w / 2, size * 0.72, size / 2 + cursor_w / 2,
                 size * 0.72 + cursor_h), fill=accent)
    return img


if __name__ == "__main__":
    run_gui(sys.argv[1] if len(sys.argv) > 1 else None)
