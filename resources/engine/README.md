# resources/engine/ - только документация

Движок декомпиляции переехал в `resources/engine_cpp/` (C++, HANDOFF_46).
Весь Python-код (`main.py`, `engine.py`, `api.py`, `toolinstaller.py` и
т.д.) удалён из проекта - здесь остались только хэндофы (`HANDOFF_*.md`),
фиксирующие историю порта сессия за сессией. Читать их по номеру по
порядку, если нужен контекст решений.

Актуальная документация по CLI/сборке - `resources/engine_cpp/README.md`.
