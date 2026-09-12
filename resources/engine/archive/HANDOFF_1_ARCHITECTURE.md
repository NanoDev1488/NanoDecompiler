# HANDOFF 1/3: АРХИТЕКТУРА - NanoDecompiler v1.1

Читай все 3 файла (HANDOFF_1_ARCHITECTURE.md, HANDOFF_2_FIXES_HISTORY.md,
HANDOFF_3_TODO.md) ПЕРЕД тем как трогать код. Разбито на 3 файла по просьбе
пользователя (было в одном большом).

## ЧТО ЭТО

Полноценный декомпилятор байткода Java для Bukkit/Spigot-плагинов (не просто
дизассемблер): парсит `.class` вручную (свой парсер constant pool/Code/
BootstrapMethods/InnerClasses/аннотаций), строит CFG, символически исполняет
байткод как стек-машину, СТРУКТУРИРУЕТ управляющий поток (if/else, while/
do-while/for, switch, try/catch - без goto) через дерево доминаторов/
постдоминаторов, деобфусцирует имена. Чистый Python 3 stdlib, без
зависимостей (кроме GUI). Работает и на Windows, и в Termux.

Запуск (Termux/Linux/macOS - всегда так): `python3 main.py plugin.jar [output_dir]`
Запуск (Windows): `run.bat` (двойной клик = GUI; с .jar аргументом или
перетаскиванием файла - GUI открывается с уже подставленным путём и сразу
стартует).

## КЛЮЧЕВОЙ ПРИНЦИП АРХИТЕКТУРЫ (НЕ НАРУШАТЬ)

Если декомпилятор НЕ уверен на 100% в конкретном методе - он ОБЯЗАН
откатиться на честный дизассемблированный листинг байткода вместо того,
чтобы печатать код, который МОЖЕТ быть неправильным. Лучше "не смог" чем
"соврал". Делается через `DecompileAbort` (engine.py) - ловится в
`decompile_method_body()`. НИКОГДА не убирай эту защиту ради процента
"успеха" - и никогда не добавляй новую проверку (DecompileAbort) без ОЧЕНЬ
тщательной проверки на ложные срабатывания (см. HANDOFF_2 - историю
escape-check - на этом дважды обожглись).

## ФАЙЛЫ И ИХ РОЛИ

Все плоские .py в одной папке `build/`, без пакетов:
- `main.py` - оркестрация всего (process_jar, render_class), CLI/GUI entry
  point, консольный вывод (баннер, цвет, прогресс-бар), проверка java/maven,
  KNOWN_LIBRARY-detection и relocation-detection.
- `classfile.py` - парсер .class (constant pool, поля, методы, Code,
  LocalVariableTable, аннотации, BootstrapMethods, InnerClasses).
- `ir.py` - декодирует raw bytecode в список инструкций.
- `cfg.py` - строит CFG из инструкций + exception table.
- `stackvm.py` - символическое исполнение блока: байткод -> Expr-дерево.
  MethodCtx - контекст метода. `_coerce_arg`/`_build_lambda` - коррекция
  типов на границах вызовов (см. HANDOFF_2, раздел "Дженерики").
- `engine.py` - `decompile_method_body()` - весь пайплайн одного метода.
  Все DecompileAbort-проверки здесь.
- `structure.py` - CFG -> структурированное дерево. Catch-переменные
  (`e1`, `e2`...) - счётчик ОБЩИЙ на весь метод, не per-try.
- `emit.py` - AST -> текст Java. `set_current_class`/`set_shadow_context` -
  контекст для решения когда убирать `this.`/самоквалификацию.
- `ast_nodes.py` - узлы AST.
- `javatypes.py` - дескрипторы JVM -> Java-типы, `looks_obfuscated`
  (НАМЕРЕННО консервативная), `mark_type`/`resolve_type_markers`.
- `pom_builder.py` - pom.xml, `KNOWN_LIBS`, `parse_shade_relocations`.
- `verify.py` - ProjectStats, баланс скобок, README-текст, `_quality_rating`.
- `switchmap.py`, `disassembler.py`, `opcodes.py` - вспомогательные.
- `gui.py` - Windows-only GUI (CustomTkinter, fallback на classic ttk).
- `run.bat`/`run.sh` - точки входа.
- `icon.ico`/`icon.png` - иконка (сгенерирована PIL, не внешний файл).

## РЕГРЕССИЯ - ОБЯЗАТЕЛЬНО ПОСЛЕ ЛЮБЫХ ИЗМЕНЕНИЙ

В архиве `test_jars/` (рядом с этими файлами) лежат:
- `EryBuyer-v1.jar` - шейдит sqlite-jdbc, интерфейсы, enum, synchronized.
- `DeathUtils-1_0.jar` - шейдит sqlite-jdbc РЕЛОЦИРОВАННЫЙ под
  `com.agent1k.libs.sqlite` (проверяет relocation-detection).
- `GlowClans-0_1-fork.jar` - САМЫЙ ценный тестовый jar: реальный лог `mvn
  clean package` (`glowclans_log.txt` в архиве) нашёл 6+ реальных багов
  движка (см. HANDOFF_2). Есть @NotNull-аннотации (JetBrains).
- pdfbox/libreoffice НЕ входят в архив (размер) - если под рукой нет,
  возьми любые похожие большие сторонние библиотеки для стресс-теста.

Команда:
```
for j in test_jars/*.jar; do
    python3 build/main.py "$j" "/tmp/out_$(basename "$j" .jar)"
    grep "Полностью восстановлены\|балансом скобок" "/tmp/out_$(basename "$j" .jar)/README_RU.txt"
done
```
Плюс проверка на 0 дублирующихся `import` (одинаковое simple-имя дважды в
одном файле) и 0 "протёкших" `\x01`/`\x02` маркеров (javatypes.mark_type):
```python
import os, re
for root, _, files in os.walk(outdir):
    for fn in files:
        if not fn.endswith(".java"): continue
        t = open(os.path.join(root, fn), encoding="utf-8", errors="replace").read()
        assert "\x01" not in t and "\x02" not in t
        imports = re.findall(r'^import ([\w.]+);', t, re.M)
        by_simple = {}
        for i in imports: by_simple.setdefault(i.rsplit(".",1)[-1], []).append(i)
        assert all(len(v) == 1 for v in by_simple.values())
```
БАЗОВЫЕ ЧИСЛА ("Полностью восстановлены" после всех фиксов этой сессии):
EryBuyer-v1 ~93%, pdfbox ~95%, libreoffice ~93%, DeathUtils ~94%,
GlowClans ~97%. Если после правки число заметно просело на НЕСКОЛЬКИХ jar -
велика вероятность, что новая проверка/фикс ложно срабатывает.
