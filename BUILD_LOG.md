# NanoDecompiler - Журнал сборок и релизов

### [1.9.164] - 2026-10-15
- **Изменения**:
  - Полное устранение fallback-методов в `ItemsAdder_4.0.18_.jar` (16327 методов, 0 fallback) за счёт синтетических локалов при stack underflow.
  - Внедрено 10 масштабных обновлений по читабельности (ReadUpd 22-31): Bukkit/Paper локалы, удаление `x.getClass()`, `Objects.requireNonNull`, составные присваивания `+=`/`-=`, `$SwitchMap$` enum switch desugaring, очистка префиксов `java.lang.`.
- **Результат**: Синхронизированы версии в `package.json`, `version.hpp`, `NanoDecompilerLite.csproj`. Подготовлен облачный прогон в GitHub Actions.
