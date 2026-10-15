# NanoDecompiler - Состояние проекта

- **Версия**: 1.9.164
- **Стек**: C++20 нативный движок (`resources/engine_cpp`), Electron + React/Vite фронтенд, Avalonia .NET 8 (`NanoDecompilerLite`).
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
  10. Тестирование ItemsAdder_4.0.18_.jar в облачном GitHub Actions без локальной нагрузки.
