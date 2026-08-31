# HANDOFF_45: СОВМЕСТИМОСТЬ С GUI + CI - проверено и починено

Продолжение HANDOFF_44. Запрос пользователя: "доделай весь C++ код и
проверь на совместимость во всех местах GUI".

## ЧТО ПРОВЕРЕНО

Нашёл ВСЕ места, где `electron/main.ts` вызывает движок подпроцессом
(единственные 3 точки, grep по `engineInvocation`/`spawn`):

1. **`run:decompile`** (основной путь): `jarPath outDir --headless
   [--no-legitimacy-check]`, ok = (exit code === 0), лог парсится
   построчно через `classifyLine.ts` (по префиксам `[*]`/`[+]`/`[!]` и
   т.д.) + `App.tsx` регуляркой достаёт статистику из строки
   `Методов декомпилировано: X/Y (Z%)`.
   - **Уже совместимо без изменений**: `cli_main.cpp` (HANDOFF_44) уже
     печатает ровно эту строку в ровно этом формате (проверил построчно
     против regex `App.tsx`), баннер начинается с `╭`/`│`/`╰` (banner-
     класс в classifyLine), `[*]`/`[+]` префиксы совпадают. `--headless`
     уже принимался (no-op).
2. **`jar:summary`** (запасной путь, когда быстрый нативный разбор ZIP в
   Node - `jarSummary.ts` - не справляется, напр. ZIP64): `--jar-summary
   plugin.jar`, ожидает ОДНУ строку JSON
   `{name,size,classes,packages,java,plugin_name}` на stdout.
   - **НЕ было реализовано вообще** - добавил `jar_summary.hpp/.cpp`,
     1:1 порт `gui_common.py::jar_summary()`, и флаг `--jar-summary` в
     `cli_main.cpp`. Схема JSON зеркалит `electron/jarSummary.ts::JarSummary`
     дословно (те же имена/типы полей). Проверено на 3 реальных jar -
     валидный JSON, значения выглядят разумно (сверял вручную с
     ожидаемым по факту содержимого архивов).
3. **`tools:install`** (JDK/Maven-установщик): `--install-tools-json[=jdk|maven]`,
   ожидает NDJSON (`{"type":"progress"|"done"|"error",...}`) на stdout.
   - **НЕ перенесено** (архитектурный вопрос из HANDOFF_40 всё ещё не
     решён - portable-скачивание JDK/Maven под Windows, нет сети в
     песочнице, чтобы вообще проверить логику скачивания). Кнопка
     "Установить инструменты" в GUI при использовании C++-движка сейчас
     работать НЕ будет (при отсутствии `--install-tools-json` любой набор
     флагов у `cli_main.cpp` просто трактуется как `jar_path`, реального
     jar не существует -> `process_jar_with_stats` бросит исключение при
     открытии как zip -> CLI напечатает `[!] ОШИБКА: ...` и вернёт код 1 -
     GUI покажет "Установщик завершился без ответа", не крашнется, но и
     не установит ничего).

## НАЙДЕННЫЙ РЕАЛЬНЫЙ РАЗРЫВ (не про флаги, про WIRING) И ФИКС

`electron/main.ts::engineInvocation()` искала скомпилированный
`NanoDecompilerCLI.exe` **только на Windows** (`process.platform ===
"win32"`) - на Linux/macOS ВСЕГДА уходила в `python3 main.py`, даже если
бы C++-бинарник лежал рядом. Так было исторически оправдано (раньше
PyInstaller-сборка была Windows-only, а сам движок на Python не требовал
компиляции на Linux/macOS вообще). Теперь движок - C++, требует
компиляции ВЕЗДЕ. **Пофиксил**: `engineInvocation()` теперь ищет
`NanoDecompilerCLI.exe` (Windows) или `NanoDecompilerCLI` (Linux/macOS,
без расширения) на всех платформах одинаково. Файл: `electron/main.ts`.

TypeScript-компилятор В ЭТОЙ СЕССИИ проверить правку НЕ смог -
`@types/node`/electron-типы не установлены, сети нет, чтобы поставить
(`npm install` не выполнялся ни разу с самого начала проекта - см. уже
существующий комментарий в `build-and-release.yml` про отсутствие
`package-lock.json`). Правка чисто механическая (убрал `if` по платформе,
имя файла зависит от платформы через тернарник) - синтаксис проверен
глазами, но реальная компиляция TS не подтверждена.

## CI (`.github/workflows/build-and-release.yml`)

`build-client` job раньше собирал `NanoDecompilerCLI.exe` через
PyInstaller из `main.py` (старый движок). Переписал на сборку C++ через
CMake+MSYS2/MinGW (добавлен `resources/engine_cpp/CMakeLists.txt` -
раньше системы сборки не было вообще, только ручные `g++ ...` команды в
разных тестовых харнессах этой и прошлых сессий).

**НЕ ПРОВЕРЕНО живым запуском** - в песочнице сессии порта нет доступа ни
к Windows, ни к GitHub Actions вообще (только Linux-контейнер). Прямая
`g++ -std=c++17 -O2 -Iinclude src/*.cpp -lz -o NanoDecompilerCLI` команда
- 100% рабочая (ею собирался и тестировался весь движок все последние
сессии, включая полный прогон на 13 jar в HANDOFF_44), `CMakeLists.txt` -
обёртка над ТЕМИ ЖЕ файлами/флагами, но саму CMake-конфигурацию и тем
более MSYS2-сборку под Windows никто не запускал. Если упадёт в CI -
смотри лог, вероятные точки: доступность zlib через MSYS2-пакет
(`mingw-w64-x86_64-zlib`), путь `build/NanoDecompilerCLI.exe` после
`cmake --build` (MinGW Makefiles генератор кладёт бинарник в корень
`build/`, не в `build/Release/` как MSVC-генератор - если CMake на раннере
почему-то возьмёт другой генератор, путь придётся поправить).

`build-api` job (собирает отдельно скачиваемые `NanoDecompilerCLI.exe`/
`NanoDecompilerAPI.exe` с флагами `--api`/`--json-output`) **НЕ тронут** -
всё ещё PyInstaller/Python, т.к. эти флаги не портированы (см. HANDOFF_44).
Из-за этого теперь ДВЕ РАЗНЫЕ сборки называются одинаково
(`NanoDecompilerCLI.exe` внутри клиента - новая, C++; `NanoDecompilerCLI.exe`
как отдельный скачиваемый файл из `build-api` - старая, Python) - поправил
текст релиза (`publish-release` job, тело релиза), чтобы это было явно
написано, а не тихо разъезжалось с реальностью.

`package.json` (`extraResources`) трогать не пришлось - копирует всю папку
`resources/engine` целиком, а компилированный exe теперь кладётся туда же
самим CI-шагом (`cp build/NanoDecompilerCLI.exe ../engine/NanoDecompilerCLI.exe`) -
механизм упаковки не менялся.

`electron/updater.ts` (автообновление exe после релиза) - Windows-only,
качает `NanoDecompilerCLI.exe` как ассет релиза. НЕ расширял на Linux/macOS
(там сейчас обновления бинарника вообще нет - только через переустановку
всего клиента) - отдельная задача, требует также публикации
Linux/macOS-бинарников как ассетов релиза (CI сейчас собирает клиент
ТОЛЬКО под `windows-latest`).

## ЧТО ДАЛЬШЕ

1. Порт `--api`/`--json-output` (`api.py`) в C++, если нужен голый
   API-продукт на новом движке - тогда `build-api` job тоже можно
   переписать на CMake, а расхождение "два разных движка под одним именем
   exe" исчезнет само.
2. Проверка CI живым push'ем в ветку (недоступно из этой сессии).
3. Кросс-платформенная сборка клиента (сейчас `build-client` -
   `windows-latest` онли, GUI для Linux/macOS не собирается вообще - это
   не связано с C++-портом, так было и раньше).
4. `--install-tools-json` (JDK/Maven-установщик) - архитектурный вопрос
   из HANDOFF_40 всё ещё открыт.

## ФАЙЛЫ ЭТОЙ СЕССИИ

- `resources/engine_cpp/include/jar_summary.hpp` - новый.
- `resources/engine_cpp/src/jar_summary.cpp` - новый.
- `resources/engine_cpp/src/cli_main.cpp` - добавлен `--jar-summary`.
- `resources/engine_cpp/CMakeLists.txt` - новый.
- `electron/main.ts` - `engineInvocation()` кросс-платформенно.
- `.github/workflows/build-and-release.yml` - `build-client` через CMake/
  MSYS2 вместо PyInstaller, текст релиза поправлен под расхождение.
- Этот файл.
