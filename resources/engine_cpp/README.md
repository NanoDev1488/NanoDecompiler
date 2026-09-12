# NanoDecompiler engine (C++)

Декомпилятор/деобфускатор байткода Java для Bukkit/Spigot-плагинов. Не
дизассемблер — парсит `.class`-файлы вручную (свой парсер по спецификации
JVM), строит граф потока управления, символически исполняет байткод как
стек-машину и **структурирует** его обратно в читаемый Java (if/else,
while/do-while/for, switch, try/catch) — без единого goto.

C++17, только `zlib` как внешняя зависимость (нет TLS/HTTP-библиотек — см.
`toolinstaller.hpp` про то, почему HTTPS-скачивание идёт через системный
`curl`, а не встроенную TLS-библиотеку).

Портирован с Python-прототипа (`resources/engine/HANDOFF_*.md` — история
порта, HANDOFF_1…HANDOFF_46). На тестовом корпусе из 13 реальных плагинов
восстанавливает 88–100% методов в структурированный Java (медиана ~93%).

## Сборка

```bash
cmake -S . -B build -DCMAKE_BUILD_TYPE=Release
cmake --build build --parallel
# бинарник: build/NanoDecompilerCLI (Linux/macOS) или build/NanoDecompilerCLI.exe (Windows/MinGW)
```

Либо напрямую, без CMake:

```bash
g++ -std=c++17 -O2 -Iinclude src/*.cpp -lz -o NanoDecompilerCLI
```

## Режимы CLI

```
NanoDecompilerCLI plugin.jar [out_dir]                       # обычный запуск, консольный вывод
NanoDecompilerCLI plugin.jar [out_dir] --no-legitimacy-check # без проверки на легитимность (сеть)
NanoDecompilerCLI plugin.jar [out_dir] --json-output         # разовый вызов, JSON в stdout
NanoDecompilerCLI --api-server [--host H] [--port 8791]      # HTTP API (POST /decompile, GET /health)
NanoDecompilerCLI --jar-summary plugin.jar                   # JSON-сводка (для GUI)
NanoDecompilerCLI --install-tools[=jdk|maven]                 # portable JDK/Maven по требованию
NanoDecompilerCLI --install-tools-json[=jdk|maven]             # то же, NDJSON (для GUI)
```

## Что на выходе

Maven-проект: `pom.xml` + `src/main/java/**/*.java` + `src/main/resources/**`
+ `MAPPING_RU.txt` (что переименовано) + `README_RU.txt` (статистика +
честные оговорки про границы восстановления).

## Структура

- `include/`, `src/` — сам движок (см. заголовок каждого файла — там же
  указано, какому куску Python-прототипа он соответствует, и какие
  сознательные упрощения относительно прототипа сделаны, если есть).
- `tools/` — регрессионные `dump_*`/`batch_*` утилиты (сверка отдельных
  модулей с прежним поведением при рефакторинге) — не часть движка.
- `CMakeLists.txt` — сборка.

## Честно о границах

- В окружении разработки нет `javac` под рукой на каждой сборке — вывод
  проверяется на баланс скобок/самосогласованность, а не компилируется
  реальным компилятором на каждый прогон. Перед боевым использованием
  результата рекомендуется прогнать через `javac`/IDE.
- `synchronized`-блоки не сворачиваются — метод целиком откатывается на
  дизассемблированный листинг (семантика не теряется молча, просто не
  компилируется как есть).
- Полный список сознательных упрощений — см. заголовки `render_class.hpp`,
  `renamer.hpp`, `process_jar.hpp`, `toolinstaller.hpp`.
