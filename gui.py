# -*- coding: utf-8 -*-
"""
Простой GUI-обёртка для NanoDecompiler (только Windows).

Запускается автоматически из main.py, если main.py запущен БЕЗ аргументов
на Windows (напр. двойной клик по run.bat). В Termux/Android/Linux/macOS
этот файл вообще не импортируется - там всё как раньше, через командную
строку (main.py plugin.jar).

Сама декомпиляция выполняется в фоновом потоке (process_jar может идти
долго на больших jar), чтобы окно не "зависало"; вывод print() из main.py
перехватывается и построчно добавляется в текстовое поле лога через
потокобезопасную очередь (напрямую трогать виджеты Tkinter из фонового
потока нельзя).
"""
import os
import queue
import sys
import threading

import tkinter as tk
from tkinter import filedialog, messagebox, scrolledtext, ttk

from main import process_jar, NANO_DECOMPILER_VERSION


class _QueueWriter:
    """Замена sys.stdout на время декомпиляции: вместо print() в консоль
    складывает строки в потокобезопасную очередь, которую GUI-поток
    вычитывает через root.after()."""
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


class NanoDecompilerGUI:
    def __init__(self, root, initial_jar=None):
        self.root = root
        self.root.title(NANO_DECOMPILER_VERSION)
        self.root.geometry("800x520")
        self.root.minsize(620, 380)

        self.queue = queue.Queue()
        self.running = False
        self.last_out_dir = None

        pad = {"padx": 8, "pady": 6}

        top = ttk.Frame(root)
        top.pack(fill="x", **pad)

        ttk.Label(top, text=".jar плагина:").grid(row=0, column=0, sticky="w")
        self.jar_var = tk.StringVar()
        ttk.Entry(top, textvariable=self.jar_var).grid(row=0, column=1, sticky="ew", padx=4)
        ttk.Button(top, text="Обзор...", command=self._pick_jar).grid(row=0, column=2)

        ttk.Label(top, text="Папка для результата:").grid(row=1, column=0, sticky="w", pady=(6, 0))
        self.out_var = tk.StringVar()
        ttk.Entry(top, textvariable=self.out_var).grid(row=1, column=1, sticky="ew", padx=4, pady=(6, 0))
        ttk.Button(top, text="Обзор...", command=self._pick_out_dir).grid(row=1, column=2, pady=(6, 0))

        top.columnconfigure(1, weight=1)

        btn_row = ttk.Frame(root)
        btn_row.pack(fill="x", **pad)
        self.run_btn = ttk.Button(btn_row, text="Декомпилировать", command=self._start)
        self.run_btn.pack(side="left")
        self.open_btn = ttk.Button(btn_row, text="Открыть папку с результатом",
                                    command=self._open_out_dir, state="disabled")
        self.open_btn.pack(side="left", padx=8)

        self.log = scrolledtext.ScrolledText(root, state="disabled", wrap="none",
                                              font=("Consolas", 9))
        self.log.pack(fill="both", expand=True, **pad)

        self.status_var = tk.StringVar(value="Готов к работе.")
        ttk.Label(root, textvariable=self.status_var, anchor="w").pack(fill="x", padx=8, pady=(0, 6))

        self.root.after(100, self._poll_queue)

        if initial_jar and os.path.isfile(initial_jar):
            self.jar_var.set(initial_jar)
            base = os.path.splitext(os.path.basename(initial_jar))[0]
            self.out_var.set(os.path.join(os.path.dirname(initial_jar), base + "_decompiled"))
            # небольшая задержка, чтобы окно успело отрисоваться до старта
            self.root.after(300, self._start)

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
        self.log.configure(state="normal")
        self.log.insert("end", line + "\n")
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
            os.path.splitext(os.path.basename(jar_path))[0] + "_decompiled"
        )

        self.log.configure(state="normal")
        self.log.delete("1.0", "end")
        self.log.configure(state="disabled")
        self.open_btn.configure(state="disabled")
        self.run_btn.configure(state="disabled")
        self.status_var.set("Идёт декомпиляция...")
        self.running = True

        t = threading.Thread(target=self._worker, args=(jar_path, out_dir), daemon=True)
        t.start()

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
                    self.run_btn.configure(state="normal")
                    self.running = False
                    messagebox.showinfo(NANO_DECOMPILER_VERSION, f"Декомпиляция завершена.\n\nРезультат: {payload}")
                elif kind == "error":
                    self._append_log(f"[!] ОШИБКА: {payload}")
                    self.status_var.set("Ошибка.")
                    self.run_btn.configure(state="normal")
                    self.running = False
                    messagebox.showerror(NANO_DECOMPILER_VERSION, f"Не удалось завершить декомпиляцию:\n\n{payload}")
        except queue.Empty:
            pass
        self.root.after(100, self._poll_queue)

    def _open_out_dir(self):
        if self.last_out_dir and os.path.isdir(self.last_out_dir):
            os.startfile(self.last_out_dir)


def run_gui(initial_jar=None):
    root = tk.Tk()
    NanoDecompilerGUI(root, initial_jar)
    root.mainloop()


if __name__ == "__main__":
    run_gui(sys.argv[1] if len(sys.argv) > 1 else None)
