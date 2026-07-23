# -*- coding: utf-8 -*-
"""
Точка входа ВТОРОГО exe - "только API", встраиваемого в другой проект
("4 в 1" декомпилер, см. переписку). Специально ОТДЕЛЬНЫЙ файл от main.py -
не флаг на общем entry point, а физически другой скрипт, который нигде
не упоминает gui.py/gui_raw.py/gui_neon.py/gui_md3.py/toolinstaller.py
даже в закомментированном виде. Из-за этого PyInstaller, анализируя ИМЕННО
этот файл как точку входа, вообще не увидит tkinter/customtkinter/flet
и не включит их в сборку - собранный exe физически не может открыть
никакое окно, что бы ему ни передали и как бы ни запустили.

Отличия от main.py:
- main.py: полноценное клиентское приложение "на каждый день" - с GUI по
  умолчанию на Windows, с флагами --api/--json-output/--api-server/
  --install-tools для остальных сценариев. Это первое приложение из
  переписки ("клиентское повседневное").
- api_cli.py (этот файл): НЕ сервер (ничего не слушает, не висит в фоне) -
  запускается, декомпилирует один .jar, печатает JSON, завершается. Ничего
  не делает, пока его явно не вызвали. Это второе приложение из переписки.

Использование (одинаково с обеих сторон - не нужен даже флаг --api, тут
и так больше ничего не бывает):
    NanoDecompilerAPI.exe plugin.jar [out_dir]

Печатает в stdout РОВНО ОДНУ строку JSON и завершается с кодом 0 (успех)
или 1 (ошибка):
    {"status": "ok", "out_dir": "...", "elapsed_sec": 0.7, "stats": {...}}
    {"status": "error", "error": "FileNotFoundError: ...", "console_log": "..."}

Как вызывать из родительского процесса, чтобы НИКОГДА не мелькало окно
консоли (даже на долю секунды) - это настраивается на СТОРОНЕ ВЫЗЫВАЮЩЕГО
кода, не здесь:
    Node.js:  spawn(exePath, args, { windowsHide: true })
    C#:       new ProcessStartInfo { CreateNoWindow = true, UseShellExecute = false }
    Python:   subprocess.run([...], creationflags=subprocess.CREATE_NO_WINDOW)  # только Windows
"""
import os
import sys

# Намеренно НЕ `import main` целиком (main.py на верхнем уровне модуля не
# импортирует gui.py - тот импорт спрятан внутри функции main() - так что
# формально `import main` тут был бы безопасен и сегодня, PyInstaller не
# потянул бы gui.py транзитивно. Но НЕ полагаемся на то, что это навсегда
# так и останется при будущих правках main.py - берём только то, что
# реально нужно, напрямую из api.py, чтобы гарантия "этот exe не может
# показать окно" не зависела от чужого файла и не могла тихо сломаться).
from api import run_json_output


def _usage_error(msg):
    import json
    print(json.dumps({"status": "error", "error": msg}, ensure_ascii=False))
    sys.exit(1)


def main():
    argv = sys.argv[1:]
    if not argv:
        _usage_error("использование: NanoDecompilerAPI.exe plugin.jar [out_dir]")
        return
    jar_path = argv[0]
    out_dir = argv[1] if len(argv) > 1 else (
        os.path.splitext(os.path.basename(jar_path))[0] + "_decompiled"
    )
    run_json_output(jar_path, out_dir)  # печатает JSON и делает sys.exit сама


if __name__ == "__main__":
    main()
