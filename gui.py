# -*- coding: utf-8 -*-
"""
GUI-обёртка NanoDecompiler (только Windows). Использует CustomTkinter
(современные скруглённые тёмные виджеты поверх Tkinter - чистый Tkinter
"плоский" и его нельзя красиво стилизовать) - если она не установлена,
автоматически откатывается на classic-стилизацию через ttk (тоже тёмная
синяя тема, просто без скруглений/анимаций CustomTkinter).

Установка (опционально, для красивого варианта): pip install customtkinter

Запускается автоматически из main.py, если main.py запущен на Windows
(см. main.py::main()). В Termux/Android/Linux/macOS этот файл вообще не
импортируется - там всё как раньше, через командную строку.
"""
import os
import platform
import queue
import shutil
import subprocess
import sys
import threading

from main import process_jar, NANO_DECOMPILER_VERSION, classify_line

# ---- палитра: тёмно-синяя, с голубыми акцентами (в тон консоли) ----
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

VSCODE_BLUE = "#0098ff"


class _QueueWriter:
    """Замена sys.stdout на время декомпиляции: вместо print() в консоль
    складывает строки в потокобезопасную очередь, которую GUI-поток
    вычитывает через периодический опрос (после() / after())."""
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


def _make_app_icon_image(size=256):
    """Рисует иконку приложения в памяти (без внешних файлов): "{" слева и
    "}" справа - каждая измеряется отдельно по bbox (одной строкой "{ }"
    кернинг непредсказуем и скобки может унести куда угодно - так и
    получился перекос, который заметил пользователь), банка по центру
    между ними, звёздочка в свободном углу сверху-справа. Требует Pillow
    (тянется customtkinter'ом как обязательная зависимость)."""
    from PIL import Image, ImageDraw, ImageFont
    import math
    img = Image.new("RGBA", (size, size), (0, 0, 0, 0))
    d = ImageDraw.Draw(img)
    navy = (11, 22, 34, 255)
    accent = (63, 169, 245, 255)
    accent_hover = (92, 193, 255, 255)
    border = (28, 58, 87, 255)

    pad = size // 42
    d.rounded_rectangle((pad, pad, size - pad, size - pad), radius=size // 4.6,
                         fill=navy, outline=border, width=max(2, size // 85))

    font = None
    for path in ("/usr/share/fonts/truetype/dejavu/DejaVuSans-Bold.ttf", "arialbd.ttf", "arial.ttf"):
        try:
            font = ImageFont.truetype(path, int(size * 0.30))
            break
        except Exception:
            continue
    if font is None:
        font = ImageFont.load_default()

    margin = size * 0.09
    lb_bbox = d.textbbox((0, 0), "{", font=font)
    lb_w, lb_h = lb_bbox[2] - lb_bbox[0], lb_bbox[3] - lb_bbox[1]
    lb_x = margin - lb_bbox[0]
    lb_y = size / 2 - lb_h / 2 - lb_bbox[1]
    d.text((lb_x, lb_y), "{", font=font, fill=accent)
    left_brace_right_edge = margin + lb_w

    rb_bbox = d.textbbox((0, 0), "}", font=font)
    rb_w = rb_bbox[2] - rb_bbox[0]
    rb_x = (size - margin) - rb_w - rb_bbox[0]
    rb_y = size / 2 - (rb_bbox[3] - rb_bbox[1]) / 2 - rb_bbox[1]
    d.text((rb_x, rb_y), "}", font=font, fill=accent)
    right_brace_left_edge = rb_x + rb_bbox[0]

    gap_left = left_brace_right_edge + size * 0.02
    gap_right = right_brace_left_edge - size * 0.02
    gap_w = gap_right - gap_left
    jar_w, jar_h = gap_w * 0.86, size * 0.40
    jar_x = gap_left + (gap_w - jar_w) / 2
    jar_y = size / 2 - jar_h / 2 + size * 0.02

    lid_pad, lid_h = jar_w * 0.09, size * 0.045
    d.rounded_rectangle((jar_x - lid_pad, jar_y - lid_h, jar_x + jar_w + lid_pad, jar_y + lid_h * 0.6),
                         radius=lid_h * 0.6, fill=(*accent[:3], 110))
    d.rounded_rectangle((jar_x, jar_y, jar_x + jar_w, jar_y + jar_h),
                         radius=jar_w * 0.16, fill=(*accent[:3], 70), outline=(*accent[:3], 160), width=4)
    for i in range(1, 4):
        ry = jar_y + jar_h * i / 4
        d.line((jar_x + jar_w * 0.12, ry, jar_x + jar_w * 0.88, ry), fill=(*navy[:3], 130), width=3)

    star_cx, star_cy, star_r = size * 0.80, size * 0.20, size * 0.045
    pts = []
    for i in range(8):
        ang = math.pi / 4 * i - math.pi / 2
        r = star_r if i % 2 == 0 else star_r * 0.42
        pts.append((star_cx + r * math.cos(ang), star_cy + r * math.sin(ang)))
    d.polygon(pts, fill=accent_hover)
    return img


def _make_vscode_glyph(size=28):
    """Маленькая иконка для кнопки "Открыть в VS Code" - НЕ логотип
    Microsoft (не воспроизводим их товарный знак), просто обобщённый
    значок "code" (</>) в характерном синем VS Code цвете."""
    from PIL import Image, ImageDraw, ImageFont
    img = Image.new("RGBA", (size, size), (0, 0, 0, 0))
    d = ImageDraw.Draw(img)
    font = None
    for path in ("/usr/share/fonts/truetype/dejavu/DejaVuSansMono-Bold.ttf", "consolab.ttf", "arialbd.ttf"):
        try:
            font = ImageFont.truetype(path, int(size * 0.62))
            break
        except Exception:
            continue
    if font is None:
        font = ImageFont.load_default()
    text = "</>"
    bbox = d.textbbox((0, 0), text, font=font)
    tw, th = bbox[2] - bbox[0], bbox[3] - bbox[1]
    d.text((size / 2 - tw / 2 - bbox[0], size / 2 - th / 2 - bbox[1]), text, font=font,
           fill=VSCODE_BLUE)
    return img


def _java_version_from_major(major):
    # major_version 45 = Java 1.1 ... 52 = Java 8 ... 61 = Java 17 ... 65 = Java 21
    if major is None:
        return "?"
    if major <= 48:
        return f"Java 1.{major - 44}"
    return f"Java {major - 44}"


def _jar_summary(jar_path):
    """Быстрая сводка по jar для инфо-карточки (без полного парсинга через
    classfile.py - тут нужно только несколько байт заголовка каждого класса
    и список путей, полный разбор при декомпиляции и так будет медленнее)."""
    import zipfile
    import struct
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
                        info["java"] = _java_version_from_major(major)
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


def _open_in_vscode(path, on_error):
    code_path = shutil.which("code") or shutil.which("code.cmd") or shutil.which("code.exe")
    if not code_path:
        on_error("VS Code не найден в PATH (команда 'code'). Установите VS Code и при "
                  "установке включите 'Add to PATH', либо откройте папку вручную.")
        return
    try:
        subprocess.Popen([code_path, path])
    except Exception as e:
        on_error(f"Не удалось запустить VS Code: {type(e).__name__}: {e}")


# ======================================================================
#  Вариант 1 (основной): CustomTkinter - современные скруглённые виджеты.
# ======================================================================
def _run_ctk_gui(initial_jar):
    import customtkinter as ctk
    from tkinter import filedialog, messagebox
    import tkinter as tk

    ctk.set_appearance_mode("dark")
    ctk.set_default_color_theme("blue")

    class App(ctk.CTk):
        def __init__(self):
            super().__init__(fg_color=BG_ROOT)
            self.title(NANO_DECOMPILER_VERSION)
            self.geometry("860x560")
            self.minsize(660, 420)
            try:
                self.attributes("-alpha", 0.96)
            except Exception:
                pass
            try:
                icon_img = _make_app_icon_image(256)
                self._icon_photo = ImageTkPhoto(icon_img)
                self.iconphoto(True, self._icon_photo)
            except Exception:
                pass

            self.queue = queue.Queue()
            self.running = False
            self.last_out_dir = None

            top = ctk.CTkFrame(self, fg_color=BG_PANEL, corner_radius=14)
            top.pack(fill="x", padx=14, pady=(14, 6))
            top.grid_columnconfigure(1, weight=1)

            ctk.CTkLabel(top, text=".jar плагина:", text_color=FG_DIM,
                         fg_color="transparent").grid(row=0, column=0, sticky="w", padx=(14, 8), pady=(14, 6))
            self.jar_var = tk.StringVar()
            ctk.CTkEntry(top, textvariable=self.jar_var, fg_color=BG_ENTRY, border_color=BORDER,
                         text_color=FG_TEXT, corner_radius=8).grid(row=0, column=1, sticky="ew", pady=(14, 6))
            ctk.CTkButton(top, text="Обзор...", command=self._pick_jar, fg_color=ACCENT_DARK,
                          hover_color=ACCENT, corner_radius=8, width=90).grid(
                row=0, column=2, padx=14, pady=(14, 6))

            ctk.CTkLabel(top, text="Папка для результата:", text_color=FG_DIM,
                         fg_color="transparent").grid(row=1, column=0, sticky="w", padx=(14, 8), pady=(0, 14))
            self.out_var = tk.StringVar()
            ctk.CTkEntry(top, textvariable=self.out_var, fg_color=BG_ENTRY, border_color=BORDER,
                         text_color=FG_TEXT, corner_radius=8).grid(row=1, column=1, sticky="ew", pady=(0, 14))
            ctk.CTkButton(top, text="Обзор...", command=self._pick_out_dir, fg_color=ACCENT_DARK,
                          hover_color=ACCENT, corner_radius=8, width=90).grid(
                row=1, column=2, padx=14, pady=(0, 14))

            self.summary_frame = ctk.CTkFrame(self, fg_color=BG_PANEL, corner_radius=14)
            self.summary_labels = {}
            self._build_summary_card()
            self.summary_frame.pack(fill="x", padx=14, pady=(0, 6))

            btn_row = ctk.CTkFrame(self, fg_color="transparent")
            btn_row.pack(fill="x", padx=14, pady=4)
            self.run_btn = ctk.CTkButton(btn_row, text="Декомпилировать", command=self._start,
                                          fg_color=ACCENT, hover_color=ACCENT_HOVER, text_color="#04121f",
                                          font=ctk.CTkFont(weight="bold"), corner_radius=10, height=36)
            self.run_btn.pack(side="left")
            self.open_btn = ctk.CTkButton(btn_row, text="Открыть папку с результатом",
                                           command=self._open_out_dir, fg_color=ACCENT_DARK,
                                           hover_color=ACCENT, corner_radius=10, height=36, state="disabled")
            self.open_btn.pack(side="left", padx=8)
            try:
                vsc_img = ctk.CTkImage(light_image=_make_vscode_glyph(28), size=(18, 18))
            except Exception:
                vsc_img = None
            self.vscode_btn = ctk.CTkButton(btn_row, text="Открыть проект в VS Code", image=vsc_img,
                                             compound="left", command=self._open_in_vscode,
                                             fg_color=ACCENT_DARK, hover_color=ACCENT, corner_radius=10,
                                             height=36, state="disabled")
            self.vscode_btn.pack(side="left", padx=8)

            log_frame = ctk.CTkFrame(self, fg_color=BG_LOG, corner_radius=14, border_width=1,
                                      border_color=BORDER)
            log_frame.pack(fill="both", expand=True, padx=14, pady=8)
            self.log = tk.Text(log_frame, state="disabled", wrap="none", font=("Consolas", 9),
                                bg=BG_LOG, fg=FG_TEXT, insertbackground=FG_TEXT,
                                selectbackground=ACCENT_DARK, selectforeground=FG_TEXT,
                                relief="flat", bd=0, highlightthickness=0)
            self.log.pack(fill="both", expand=True, padx=10, pady=10)
            for tag, color in (("banner", COLOR_BANNER), ("info", COLOR_INFO), ("ok", COLOR_OK),
                               ("warn", COLOR_WARN), ("error", COLOR_ERROR), ("dim", COLOR_DIM)):
                self.log.tag_configure(tag, foreground=color)

            self.status_var = tk.StringVar(value="Готов к работе.")
            ctk.CTkLabel(self, textvariable=self.status_var, text_color=FG_DIM,
                         fg_color="transparent", anchor="w").pack(fill="x", padx=20, pady=(0, 12))

            self.after(100, self._poll_queue)

            if initial_jar and os.path.isfile(initial_jar):
                self.jar_var.set(initial_jar)
                base = os.path.splitext(os.path.basename(initial_jar))[0]
                self.out_var.set(os.path.join(os.path.dirname(initial_jar), base + "_decompiled"))
                self._update_summary_card(initial_jar)
                self.after(300, self._start)

        def _build_summary_card(self):
            f = self.summary_frame
            cols = [("SIZE", "size"), ("JAVA", "java"), ("КЛАССОВ", "classes"), ("ПАКЕТОВ", "packages")]
            for i, (label, key) in enumerate(cols):
                ctk.CTkLabel(f, text=label, text_color=FG_DIM, font=ctk.CTkFont(size=10),
                             fg_color="transparent").grid(row=0, column=i, sticky="w",
                                                           padx=(14 if i == 0 else 10, 0), pady=(10, 0))
                val = ctk.CTkLabel(f, text="-", text_color=FG_TEXT, font=ctk.CTkFont(weight="bold"),
                                    fg_color="transparent")
                val.grid(row=1, column=i, sticky="w", padx=(14 if i == 0 else 10, 0), pady=(0, 10))
                self.summary_labels[key] = val
            self.summary_labels["name"] = ctk.CTkLabel(f, text="", text_color=ACCENT,
                                                         font=ctk.CTkFont(weight="bold"), fg_color="transparent")
            self.summary_labels["name"].grid(row=0, column=len(cols), rowspan=2, sticky="e", padx=14)
            f.grid_columnconfigure(len(cols), weight=1)

        def _update_summary_card(self, jar_path):
            info = _jar_summary(jar_path)
            self.summary_labels["size"].configure(text=info["size"])
            self.summary_labels["java"].configure(text=info["java"])
            self.summary_labels["classes"].configure(text=str(info["classes"]))
            self.summary_labels["packages"].configure(text=str(info["packages"]))
            self.summary_labels["name"].configure(text=info["plugin_name"] or info["name"])

        def _pick_jar(self):
            path = filedialog.askopenfilename(title="Выберите .jar плагина",
                                               filetypes=[("JAR-файлы", "*.jar"), ("Все файлы", "*.*")])
            if path:
                self.jar_var.set(path)
                self._update_summary_card(path)
                if not self.out_var.get():
                    base = os.path.splitext(os.path.basename(path))[0]
                    self.out_var.set(os.path.join(os.path.dirname(path), base + "_decompiled"))

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
            out_dir = self.out_var.get().strip() or (
                os.path.splitext(os.path.basename(jar_path))[0] + "_decompiled")

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
            sys.stdout = _QueueWriter(self.queue)
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
                        java_count = 0
                        for _root, _dirs, _files in os.walk(payload):
                            java_count += sum(1 for fn in _files if fn.endswith(".java"))
                        self._show_toast("Готово", f"{java_count} файлов декомпилировано")
                    elif kind == "error":
                        self._append_log(f"[!] ОШИБКА: {payload}")
                        self.status_var.set("Ошибка.")
                        self.run_btn.configure(state="normal")
                        self.running = False
                        messagebox.showerror(NANO_DECOMPILER_VERSION,
                                              f"Не удалось завершить декомпиляцию:\n\n{payload}")
            except queue.Empty:
                pass
            self.after(100, self._poll_queue)

        def _open_out_dir(self):
            if self.last_out_dir and os.path.isdir(self.last_out_dir):
                os.startfile(self.last_out_dir)

        def _open_in_vscode(self):
            if self.last_out_dir:
                _open_in_vscode(self.last_out_dir,
                                 lambda msg: messagebox.showwarning(NANO_DECOMPILER_VERSION, msg))

        def _show_toast(self, title, subtitle, ms=4500):
            """Небольшая всплывающая карточка снизу-справа поверх окна (в духе
            современных decompiler-тулов) - не блокирует, сама закрывается."""
            try:
                toast = ctk.CTkToplevel(self)
                toast.overrideredirect(True)
                toast.attributes("-topmost", True)
                try:
                    toast.attributes("-alpha", 0.97)
                except Exception:
                    pass
                card = ctk.CTkFrame(toast, fg_color=BG_PANEL, corner_radius=12,
                                     border_width=1, border_color=ACCENT)
                card.pack(fill="both", expand=True)
                row = ctk.CTkFrame(card, fg_color="transparent")
                row.pack(padx=14, pady=12)
                ctk.CTkLabel(row, text="✓", text_color=COLOR_OK,
                             font=ctk.CTkFont(size=18, weight="bold"),
                             fg_color="transparent").pack(side="left", padx=(0, 10))
                text_col = ctk.CTkFrame(row, fg_color="transparent")
                text_col.pack(side="left")
                ctk.CTkLabel(text_col, text=title, text_color=FG_TEXT,
                             font=ctk.CTkFont(weight="bold"), fg_color="transparent",
                             anchor="w").pack(fill="x")
                ctk.CTkLabel(text_col, text=subtitle, text_color=FG_DIM, fg_color="transparent",
                             anchor="w").pack(fill="x")

                self.update_idletasks()
                w, h = 260, 70
                x = self.winfo_x() + self.winfo_width() - w - 24
                y = self.winfo_y() + self.winfo_height() - h - 24
                toast.geometry(f"{w}x{h}+{x}+{y}")
                toast.bind("<Button-1>", lambda e: toast.destroy())
                toast.after(ms, lambda: toast.destroy() if toast.winfo_exists() else None)
            except Exception:
                pass  # тост - чисто косметика, не должен ронять приложение

    from PIL import ImageTk
    global ImageTkPhoto
    ImageTkPhoto = ImageTk.PhotoImage

    app = App()
    app.mainloop()


# ======================================================================
#  Вариант 2 (запасной): classic ttk - если customtkinter не установлена.
# ======================================================================
def _run_classic_gui(initial_jar):
    import tkinter as tk
    from tkinter import filedialog, messagebox, ttk

    class ClassicApp:
        def __init__(self, root, initial_jar=None):
            self.root = root
            self.root.title(NANO_DECOMPILER_VERSION)
            self.root.geometry("820x540")
            self.root.minsize(640, 400)
            self.root.configure(bg=BG_ROOT)
            try:
                self.root.attributes("-alpha", 0.94)
            except Exception:
                pass

            self._setup_style()
            self.queue = queue.Queue()
            self.running = False
            self.last_out_dir = None

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

            log_frame = tk.Frame(root, bg=BORDER, bd=0)
            log_frame.pack(fill="both", expand=True, padx=10, pady=6)
            from tkinter import scrolledtext
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
                base = os.path.splitext(os.path.basename(initial_jar))[0]
                self.out_var.set(os.path.join(os.path.dirname(initial_jar), base + "_decompiled"))
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

        def _pick_jar(self):
            path = filedialog.askopenfilename(title="Выберите .jar плагина",
                                               filetypes=[("JAR-файлы", "*.jar"), ("Все файлы", "*.*")])
            if path:
                self.jar_var.set(path)
                if not self.out_var.get():
                    base = os.path.splitext(os.path.basename(path))[0]
                    self.out_var.set(os.path.join(os.path.dirname(path), base + "_decompiled"))

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
            out_dir = self.out_var.get().strip() or (
                os.path.splitext(os.path.basename(jar_path))[0] + "_decompiled")
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
            sys.stdout = _QueueWriter(self.queue)
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
            except queue.Empty:
                pass
            self.root.after(100, self._poll_queue)

        def _open_out_dir(self):
            if self.last_out_dir and os.path.isdir(self.last_out_dir):
                os.startfile(self.last_out_dir)

        def _open_in_vscode(self):
            if self.last_out_dir:
                _open_in_vscode(self.last_out_dir,
                                 lambda msg: messagebox.showwarning(NANO_DECOMPILER_VERSION, msg))

    root = tk.Tk()
    ClassicApp(root, initial_jar)
    root.mainloop()


def run_gui(initial_jar=None):
    try:
        import customtkinter  # noqa: F401
        from PIL import Image  # noqa: F401
    except ImportError:
        print("[*] Для более красивого GUI можно поставить: pip install customtkinter")
        print("[*] Запускаю обычную (classic) версию интерфейса...")
        _run_classic_gui(initial_jar)
        return
    _run_ctk_gui(initial_jar)


if __name__ == "__main__":
    run_gui(sys.argv[1] if len(sys.argv) > 1 else None)
