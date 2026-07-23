# NanoDecompiler — Electron GUI (v2.0)

Новый GUI-слой поверх существующего Python-движка декомпиляции, на замену
трём Tkinter/CustomTkinter/Flet-темам (`gui_raw.py`, `gui_neon.py`,
`gui_md3.py`) из v1.2.

## Что переписано, а что — нет

- **Переписан**: весь GUI. Electron (main process на TS) + React 18 + TS
  (renderer, Vite). Визуальная идентичность (v2.1) — слияние двух языков в
  один: терминальный брендинг проекта (true-black фон, кислотно-зелёный
  акцент, моно-шрифт для лога, статус-точки ●/◯ — то же самое, что было в
  `gui_neon.py`) + структурные приёмы Material Design 3 (elevation,
  скруглённые surface-контейнеры, tonal-кнопки, chip-компонент). Не замена
  одного языка на другой — сигнатурный элемент (статус-индикатор) буквально
  MD3 assist-chip с той же зелёной точкой внутри, что и раньше. Токены и
  обоснование — в шапке `src/styles.css`.
- **НЕ переписан**: движок декомпиляции (`classfile.py`, `ir.py`, `cfg.py`,
  `stackvm.py`, `engine.py`, `structure.py`, `emit.py`, `javatypes.py`,
  `pom_builder.py`, `verify.py` и т.д.). Он запускается как дочерний процесс
  `python3 main.py <jar> <outdir>` и его вывод стримится в терминал-панель
  интерфейса. Причина — см. `resources/engine/HANDOFF_1_ARCHITECTURE.md`,
  раздел "ключевой принцип архитектуры": это откалиброванный на реальных
  .jar символический исполнитель байткода с 9 продиагностированными багами
  за плечами. Переписывать его на JS без тех же регрессионных .jar и без
  риска тихо потерять доверенное поведение (DecompileAbort-фоллбэк) —
  не имеет смысла и не было частью запроса на "переписать GUI".

## Запуск (разработка)

Требуется Node.js 18+ и Python 3 (движок использует только stdlib).

```bash
npm install
npm run dev            # поднимет Vite dev-server
# в отдельном терминале:
npm run build:electron && npx electron .
```

Проще для повседневной разработки — использовать `npm start` (собирает всё
и сразу открывает Electron-окно), пересобирая после правок.

## Сборка дистрибутива

```bash
npm run dist
```

`electron-builder` соберёт установщик и включит `resources/engine/*.py` как
`extraResources` (папка `engine/` рядом с исполняемым файлом) — движок
работает так же, просто путь к `main.py` резолвится по `process.resourcesPath`
в проде (см. `electron/main.ts::engineDir()`).

## Структура

```
electron/        # main process + preload (TypeScript, компилируется в dist-electron/)
src/             # React renderer (Vite)
resources/engine/  # Python-движок NanoDecompiler без изменений + HANDOFF_*.md
```

## Дальше по плану (не сделано в этом проходе)

- Дерево сгенерированных .java файлов в интерфейсе (сейчас — только сырой
  лог + кнопка "открыть папку"); данные для этого уже есть в
  `verify.py::ProjectStats`, нужно прокинуть их через IPC отдельным
  сообщением по завершении.
- Настройки (путь по умолчанию, недавние jar) — сейчас нет аналога
  `gui_common.py::load_settings/save_settings`, стоит перенести в
  `electron-store` или простой JSON рядом с `app.getPath("userData")`.
- Экран "нет python3 / нет java/maven" (в `main.py` уже есть эти проверки в
  консольном выводе — сейчас они просто попадут в терминал-лог как есть,
  можно поднять их в отдельный баннер).
