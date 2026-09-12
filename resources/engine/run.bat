@echo off
REM Двойной клик БЕЗ аргументов теперь просто печатает usage (см. main.py -
REM старый tkinter/customtkinter/flet GUI, который раньше открывался тут,
REM физически удалён - GUI теперь отдельный продукт, Electron-клиент
REM NanoDecompiler-Client-Setup.exe, см. README.md).
REM Перетащить .jar-файл на этот .bat (или на собранный .exe) - запустит
REM декомпиляцию в консоли с этим jar как аргументом.
REM Требуется Python 3 (python.org/downloads) - при установке отметьте "Add to PATH".
python main.py %*
