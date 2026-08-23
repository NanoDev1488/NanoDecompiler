# NanoDecompiler — Electron GUI (dev-заметки)

> Заголовок раньше был помечен "(v2.0)" - это была внутренняя метка ДО
> того, как у "v2.0" появился новый, более крупный смысл (полное
> переписывание движка на C-семейный язык - см.
> `resources/engine/HANDOFF_23_V2_PLANNING.md`). Убрано во избежание
> путаницы - этот файл просто про то, как устроен GUI-слой, не про
> будущую v2.0.

Электрон-слой поверх C++-движка декомпиляции (`resources/engine_cpp/`), на
замену трём Tkinter/CustomTkinter/Flet-темам (`gui_raw.py`, `gui_neon.py`,
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
- **Движок ТОЖЕ переписан** (HANDOFF_46, изначально не планировалось — см.
  историческую заметку ниже): `resources/engine_cpp/` — C++17, компактный
  бинарник `NanoDecompilerCLI`, без Python вообще. GUI спавнит его как
  дочерний процесс (`electron/main.ts::engineInvocation()`), вывод стримится
  в терминал-панель интерфейса так же, как раньше с Python. Подробности,
  сознательные упрощения относительно Python-прототипа и honest caveats —
  `resources/engine_cpp/README.md` + `resources/engine/HANDOFF_40..46*.md`.

  *(Историческая заметка: изначально это НЕ планировалось — см.
  `resources/engine/HANDOFF_1_ARCHITECTURE.md`, где объяснялось, почему
  движок оставили на Python. Решение пересмотрено в HANDOFF_39-46: движок
  портирован на C++ полностью, модуль за модулем, с проверкой на реальных
  .jar на каждом шаге — тот риск "тихо потерять доверенное поведение",
  которого тогда избегали, снят регрессионным тестированием каждого куска.)*

## Запуск (разработка)

Требуется Node.js 18+ и C++17-компилятор (g++/clang++) + CMake для сборки
движка (`resources/engine_cpp/`, см. его README) — Python для запуска
проекта больше НЕ нужен вообще.

```bash
cd resources/engine_cpp && cmake -S . -B build && cmake --build build --parallel
cp build/NanoDecompilerCLI ../engine/NanoDecompilerCLI   # или .exe на Windows
cd ../..
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

`electron-builder` соберёт установщик и включит `resources/engine/` целиком
как `extraResources` (папка `engine/` рядом с исполняемым файлом) — туда
нужно заранее положить собранный бинарник `NanoDecompilerCLI`/`.exe` (CI
делает это автоматически, см. `.github/workflows/build-and-release.yml`) —
движок резолвится по `process.resourcesPath` в проде (см.
`electron/main.ts::engineDir()`/`engineInvocation()`).

## Структура

```
electron/            # main process + preload (TypeScript, компилируется в dist-electron/)
src/                 # React renderer (Vite)
resources/engine_cpp/  # C++-движок NanoDecompiler (см. его README.md)
resources/engine/    # только HANDOFF_*.md - история порта Python -> C++
```

## Дальше по плану (не сделано в этом проходе)

- Дерево сгенерированных .java файлов в интерфейсе (сейчас — только сырой
  лог + кнопка "открыть папку"); данные для этого уже есть в
  `verify.hpp::ProjectStats`, нужно прокинуть их через IPC отдельным
  сообщением по завершении.
- Экран "нет java/maven" (в `cli_main.cpp`/`toolinstaller.hpp` уже есть эти
  проверки в консольном выводе — сейчас они просто попадут в терминал-лог
  как есть, можно поднять их в отдельный баннер). Проверки на "нет
  python3" больше не нужны — движок не требует Python вообще (HANDOFF_46).

(Настройки — уже сделаны в v1.4.2, см. `⚙` в шапке клиента и
`electron/main.ts::loadSettings/saveSettings`.)
