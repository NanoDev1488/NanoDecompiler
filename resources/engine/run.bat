@echo off
REM Двойной клик - откроется GUI (можно выбрать .jar в диалоге).
REM Можно и перетащить .jar-файл на этот .bat (или на собранный .exe) -
REM GUI откроется с уже подставленным путём и сразу начнёт декомпиляцию.
REM Требуется Python 3 (python.org/downloads) - при установке отметьте "Add to PATH".
python main.py %*
