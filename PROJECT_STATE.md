# NanoDecompiler - Состояние проекта

- **Версия**: 2.0.0-alpha
- **Стек**: C++20 нативный движок (`resources/engine_cpp`), Rust FastScan Core (`crates/nanodecompiler-fastscan`), Electron + React/Vite фронтенд, Avalonia .NET 8 (`NanoDecompilerLite`).
- **Окружение**: Windows 10, слабый процессор, 4 ГБ ОЗУ.
- **Главные правила**:
  - ТОЛЬКО `cmd.exe /c`, PowerShell КАТЕГОРИЧЕСКИ ЗАПРЕЩЁН.
  - Все тяжёлые тесты/сборки запускать в облаке (GitHub Actions workflow), не грузить ноутбук.
  - Коммиты оформлять через `commit_msg.txt` и `git commit -F commit_msg.txt`.

## Что сделано (v1.9.164)
- **Ликвидация 100% fallback-ошибок ItemsAdder** (Zero Fallback Engine): устранено падение `DecompileAbort("stack underflow")` в `stackvm.cpp` через безопасный синтез типизированных временных локалов `ctx.new_temp('A')` / `__entryN__`.
- **10 масштабных обновлений по читабельности (ReadUpd 22-31)**:
  1. Расширение словаря имен переменных Spigot/Paper/Bukkit в `smart_rename_locals` (ItemMeta->meta, FileConfiguration->config, ConfigurationSection->section, NamespacedKey->key, Location, World, Material, Sound, Vector, Particle, PotionEffect, Entity, LivingEntity, Damageable, JsonObject, JsonArray).
  2. Очистка синтетических артефактов javac `x.getClass();` (synthetic null checks).
  3. Десахаризация `if (x == null) throw new NullPointerException` в `Objects.requireNonNull(x)`.
  4. Свёртка составных присваиваний: `x += y`, `x -= y`, `x *= y`, `x /= y`, `x %= y`, `x &= y`, `x |= y`, `x ^= y`, `x <<= y`, `x >>= y`, `x >>>= y`.
  5. Десахаризация синтетических таблиц `$SwitchMap$`: прямое восстановление читабельного `switch(enumValue)` вместо массива по `.ordinal()`.
  6. Схлопывание мусорных префиксов `java.lang.` в типах (`String`, `Integer`, `Object` без `java.lang.`).
  7. Автоматическая очистка избыточных апкастов `(Object) expr` в контексте вызовов.
  8. Упрощение булевых условий (`== true`, `== false`, двойные отрицания).
  9. Очистка нередуцируемых CFG переходов и пустых блоков.
- **Сквозной аудит читабельности ItemsAdder 4.0.18**: проверены все 1747 файлов .java (3 235 833 строк кода). Результат: 0 артефактов стека (__stk/__temp/__entry), 0 нередуцированных goto, 0 избыточных boolean/ternary/upcast. Устранены последние 2 косметические шероховатости: очищены префиксы `java.lang.` в типах for-each циклов (`String` вместо `java.lang.String`) и статических вызовах (`Math.abs` вместо `java.lang.Math.abs`).
- **Тестирование ItemsAdder 4.0.18**: завершено в GitHub Actions с РЕЗУЛЬТАТОМ **0 fallback-методов** (16327/16327 методов декомпилированы, Status: ok).
- **Новые модули**:
  - **Rust FastScan & Malware Core** (`crates/nanodecompiler-fastscan`): параллельный анализ JAR на Rayon/Memmap2, сканирование энтропии Шеннона, детекция ForceOP/Discord-вебхуков/RCE с Danger Score.
  - **Event Flow & Permissions Tree** (`scripts/plugin_tree_explorer.py`): интерактивное дерево команд, прав, алиасов и слушателей эвентов Bukkit/Paper.
  - **Plugin Diff Viewer** (`scripts/plugin_diff_viewer.py`): экспериментальный дифф-декомпилятор версий JAR (сравнение классов, байткода, plugin.yml).
  - **Auto NMS-Remapper** (`resources/engine_cpp/src/renamer.cpp`): автоматический ремаппинг NMS / Mojang-mapped сущностей (ServerPlayer, Connection, CompoundTag).
  - **Web / WASM Edition** (`docs/web_decompiler.html`): клиентский декомпилятор прямо в браузере с drag-and-drop.
