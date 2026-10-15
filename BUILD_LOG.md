# NanoDecompiler - Журнал сборок и релизов

### [2.0.0-alpha] - 2026-10-15
- **Изменения**:
  - **Zero Fallback Engine**: 100% методов декомпилируются без фолбэков (верифицировано на `ItemsAdder_4.0.18_.jar` — 16327/16327 методов, 0 fallback).
  - **Rust FastScan & Malware Core**: высокопроизводительный многопоточный анализатор безопасности JAR (энтропия Шеннона, детекция ForceOP, Discord-вебхуков, RCE) на Rayon.
  - **Event Flow & Permissions Tree в GUI**: интерактивное дерево событий, команд, прав Bukkit/Paper с мгновенным поиском и фильтрацией.
  - **Plugin Diff Viewer в GUI**: графическое сравнение двух версий JAR (модифицированные, добавленные, удаленные классы, разница размеров).
  - **Auto NMS-Remapper**: автоматический транслятор обфусцированных пакетов Mojang/Spigot NMS в читаемые имена.
  - **Экспорт в GitHub в GUI**: интеграция с GitHub REST API с безопасным вводом токена PAT и прямым пушем исходников.
  - **Web / WASM Edition**: drag-and-drop декомпиляция прямо в веб-интерфейсе браузера (`docs/web_decompiler.html`).
- **Результат**: Успешная сборка фронтенда Vite и Electron TypeScript. Создан тег и предрелиз `v2.0.0-alpha`.

### [1.9.164] - 2026-10-15
- **Изменения**:
  - Полное устранение fallback-методов в `ItemsAdder_4.0.18_.jar` (16327 методов, 0 fallback) за счёт синтетических локалов при stack underflow.
  - Внедрено 10 масштабных обновлений по читабельности (ReadUpd 22-31): Bukkit/Paper локалы, удаление `x.getClass()`, `Objects.requireNonNull`, составные присваивания `+=`/`-=`, `$SwitchMap$` enum switch desugaring, очистка префиксов `java.lang.`.
- **Результат**: Синхронизированы версии в `package.json`, `version.hpp`, `NanoDecompilerLite.csproj`. Подготовлен облачный прогон в GitHub Actions.
