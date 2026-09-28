const fs = require('fs');

// Step 1: Add new keys to i18n.ts
let i18n = fs.readFileSync('src/lib/i18n.ts', 'utf8');

const ruKeys = {
  "toast.report_sent": "Отчёт отправлен",
  "toast.report_failed": "Не удалось отправить отчёт",
  "toast.unknown_error": "неизвестная ошибка",
  "toast.installed": "установлен(а)",
  "toast.install_failed": "Не удалось установить",
  "toast.check_update_failed": "Не удалось проверить обновления",
  "toast.no_updates": "Вы на последней версии",
  "toast.update_engine": "Доступно обновление движка",
  "toast.update_client": "Доступно обновление клиента",
  "toast.updated": "Обновлено",
  "toast.update_failed": "Не удалось обновить",
  "toast.link_failed": "Не удалось открыть ссылку",
  "toast.done": "Готово",
  "toast.error": "Ошибка",
  "toast.queue_warning": "Очередь полна. Добавьте .jar следом.",
  "toast.env_not_ready": "Нет окружения. Проверьте настройки.",
  "toast.stopped": "Очередь очищена",
  "toast.jar_warning": "Не является .jar файлом",
  "toast.already_queued": "Этот файл уже в очереди - результат будет в отдельной папке",
  "toast.queued": "В очередь",
  "toast.archives": "архив(ов)",
  "toast.bind_failed": "Не удалось привязать ключ для",
  "toast.no_text": "Нет текста - копировать нечего",
  "toast.no_log_filter": "Нет записей с текущим фильтром",
  "toast.copied": "скопировано",
  "toast.lines": "строк",
  "toast.log": "Лог",
  "toast.settings_saved": "Настройки сохранены",
  "toast.settings_failed": "Не удалось сохранить настройки",
  "toast.app_updated": "Клиент успешно обновлён",
  "toast.setup_first": "Сначала завершите настройку, потом начинайте",
  "toast.malware_warn": "Обнаружен подозрительный код",
  "toast.file_select_canceled": "Ни один файл не выбран",
  "toast.read_failed": "Не удалось прочитать файл",
  "toast.file_too_big": "Нет доступа или слишком большой файл - используйте \"Открыть в ОС\"",
  "toast.auto_report": "Автоматическая отправка (telemetryEnabled)",
  "phase.validating": "Проверка файла:",
  "phase.scanning": "сканирование вредоносного содержимого:",
  "phase.parsing": "разбор байткод-классов:",
  "phase.legitimacy": "проверка легитимности (сеть):",
  "phase.decompiling": "декомпиляция:",
  "engine.failed": "Не завершился с кодом",
  "toast.root_pkg": "(корень)",
};

const enKeys = {
  "toast.report_sent": "Report sent",
  "toast.report_failed": "Failed to send report",
  "toast.unknown_error": "unknown error",
  "toast.installed": "installed",
  "toast.install_failed": "Failed to install",
  "toast.check_update_failed": "Failed to check for updates",
  "toast.no_updates": "You are on the latest version",
  "toast.update_engine": "Engine update available",
  "toast.update_client": "Client update available",
  "toast.updated": "Updated",
  "toast.update_failed": "Failed to update",
  "toast.link_failed": "Failed to open link",
  "toast.done": "Done",
  "toast.error": "Error",
  "toast.queue_warning": "Queue full. Add .jar next.",
  "toast.env_not_ready": "Environment not ready. Check settings.",
  "toast.stopped": "Queue cleared",
  "toast.jar_warning": "Not a .jar file",
  "toast.already_queued": "This file is already queued - results will be in a separate folder",
  "toast.queued": "Queued",
  "toast.archives": "archive(s)",
  "toast.bind_failed": "Failed to bind key for",
  "toast.no_text": "No text - nothing to copy",
  "toast.no_log_filter": "No entries with current filter",
  "toast.copied": "copied",
  "toast.lines": "lines",
  "toast.log": "Log",
  "toast.settings_saved": "Settings saved",
  "toast.settings_failed": "Failed to save settings",
  "toast.app_updated": "Client updated successfully",
  "toast.setup_first": "Complete setup first, then begin",
  "toast.malware_warn": "Suspicious code detected",
  "toast.file_select_canceled": "No file selected",
  "toast.read_failed": "Failed to read file",
  "toast.file_too_big": "No access or file too big - use \"Open in OS\"",
  "toast.auto_report": "Automatic report (telemetryEnabled)",
  "phase.validating": "Validating file:",
  "phase.scanning": "scanning malicious content:",
  "phase.parsing": "parsing bytecode classes:",
  "phase.legitimacy": "checking legitimacy (network):",
  "phase.decompiling": "decompiling:",
  "engine.failed": "Failed with code",
  "toast.root_pkg": "(root)",
};

// Build ru block
let ruBlock = '';
for (const [k, v] of Object.entries(ruKeys)) {
  ruBlock += `    "${k}": "${v}",\n`;
}
// Build en block
let enBlock = '';
for (const [k, v] of Object.entries(enKeys)) {
  enBlock += `    "${k}": "${v}",\n`;
}

// Insert before the closing of each dict section
// Find first ru section marker
i18n = i18n.replace(
  /(\s*"app\.bug_report":)/,
  '\n' + ruBlock + '$1'
);

// For en section - find second app.bug_report
const firstIdx = i18n.indexOf('"app.bug_report":');
const secondIdx = i18n.indexOf('"app.bug_report":', firstIdx + 1);
if (secondIdx > 0) {
  i18n = i18n.slice(0, secondIdx) + '\n' + enBlock + '    ' + i18n.slice(secondIdx);
}

fs.writeFileSync('src/lib/i18n.ts', i18n, 'utf8');
console.log('i18n.ts patched with toast keys');

// Step 2: Replace hardcoded Russian strings in engine.tsx
let engine = fs.readFileSync('src/state/engine.tsx', 'utf8');

// Add import for t and lang at the top if not present
if (!engine.includes('import { t }')) {
  engine = engine.replace(
    'import { fmtNum',
    'import { t } from "../lib/i18n";\nimport { fmtNum'
  );
}

// Replace toast calls - be careful with template literals
const replacements = [
  // Report
  [/toast\("Отчёт отправлен", "ok"\)/g, 'toast(t(lang(), "toast.report_sent"), "ok")'],
  [/toast\(`Не удалось отправить отчёт: \$\{res\.error \?\? "неизвестная ошибка"\}`/g, 'toast(`${t(lang(), "toast.report_failed")}: ${res.error ?? t(lang(), "toast.unknown_error")}`'],
  [/toast\(`Не удалось отправить отчёт: \$\{String\(e\)\}`/g, 'toast(`${t(lang(), "toast.report_failed")}: ${String(e)}`'],
  [/toast\(`Не удалось отправить отчёт: \$\{error\}`/g, 'toast(`${t(lang(), "toast.report_failed")}: ${error}`'],
  // Install
  [/toast\(`\$\{which === "java" \? "Java" : "Maven"\} установлен\(а\)`/g, 'toast(`${which === "java" ? "Java" : "Maven"} ${t(lang(), "toast.installed")}`'],
  [/toast\(r\.errors\?\.\[0\] \?\? r\.error \?\? `Не удалось установить \$\{which === "java" \? "Java" : "Maven"\}`/g, 'toast(r.errors?.[0] ?? r.error ?? `${t(lang(), "toast.install_failed")} ${which === "java" ? "Java" : "Maven"}`'],
  // Updates
  [/toast\(r\.error \?\? "Не удалось проверить обновления"/g, 'toast(r.error ?? t(lang(), "toast.check_update_failed")'],
  [/toast\("Вы на последней версии"/g, 'toast(t(lang(), "toast.no_updates")'],
  [/toast\(`Доступно обновление движка: \$\{r\.latestVersion\}`/g, 'toast(`${t(lang(), "toast.update_engine")}: ${r.latestVersion}`'],
  [/toast\(`Доступно обновление клиента: \$\{r\.latestVersion\}`/g, 'toast(`${t(lang(), "toast.update_client")}: ${r.latestVersion}`'],
  [/toast\("Не удалось проверить обновления"/g, 'toast(t(lang(), "toast.check_update_failed")'],
  // Update
  [/toast\("Обновлено"/g, 'toast(t(lang(), "toast.updated")'],
  [/toast\(r\.error \?\? "Не удалось обновить"/g, 'toast(r.error ?? t(lang(), "toast.update_failed")'],
  [/toast\("Не удалось обновить"/g, 'toast(t(lang(), "toast.update_failed")'],
  // Link
  [/toast\("Не удалось открыть ссылку"/g, 'toast(t(lang(), "toast.link_failed")'],
  // Done/Error job
  [/toast\(`Готово: \$\{job\.fileName\}/g, 'toast(`${t(lang(), "toast.done")}: ${job.fileName}'],
  [/toast\(`Ошибка: \$\{job\.fileName\}/g, 'toast(`${t(lang(), "toast.error")}: ${job.fileName}'],
  // Queue
  [/toast\("Очередь полна\. Добавьте \.jar следом\."/g, 'toast(t(lang(), "toast.queue_warning")'],
  [/toast\("Нет окружения\. Проверьте настройки\."/g, 'toast(t(lang(), "toast.env_not_ready")'],
  [/toast\("Очередь очищена"/g, 'toast(t(lang(), "toast.stopped")'],
  // Jar warnings
  [/toast\(`Пропущено: \$\{f\.name\} - не является \.jar`/g, 'toast(`${f.name}: ${t(lang(), "toast.jar_warning")}`'],
  [/toast\(`Этот файл уже в очереди - результат будет в отдельной папке \(\$\{baseName\(fileName\)\}-\$\{n\}\)`/g, 'toast(`${t(lang(), "toast.already_queued")} (${baseName(fileName)}-${n})`'],
  // Queued
  [/toast\(`В очередь: \$\{fresh\.length\} архив\(ов\)`/g, 'toast(`${t(lang(), "toast.queued")}: ${fresh.length} ${t(lang(), "toast.archives")}`'],
  // File select
  [/toast\("Ни один файл не выбран"/g, 'toast(t(lang(), "toast.file_select_canceled")'],
  // Settings
  [/toast\("Настройки сохранены"/g, 'toast(t(lang(), "toast.settings_saved")'],
  [/toast\(`Не удалось сохранить настройки: \$\{res\.error \?\? "неизвестная ошибка"\}`/g, 'toast(`${t(lang(), "toast.settings_failed")}: ${res.error ?? t(lang(), "toast.unknown_error")}`'],
  [/toast\(`Не удалось сохранить настройки: \$\{String\(e\)\}`/g, 'toast(`${t(lang(), "toast.settings_failed")}: ${String(e)}`'],
  // App updated
  [/toast\("Клиент успешно обновлён"/g, 'toast(t(lang(), "toast.app_updated")'],
  // Setup
  [/toast\("Сначала завершите настройку, потом начинайте"/g, 'toast(t(lang(), "toast.setup_first")'],
  // Copy  
  [/toast\("Нет текста - копировать нечего"/g, 'toast(t(lang(), "toast.no_text")'],
  [/toast\("Нет записей с текущим фильтром"/g, 'toast(t(lang(), "toast.no_log_filter")'],
  // Phase labels
  [/validating: "Проверка файла:"/g, 'validating: t(lang(), "phase.validating")'],
  [/scanning: "сканирование вредоносного содержимого:"/g, 'scanning: t(lang(), "phase.scanning")'],
  [/parsing: "разбор байткод-классов:"/g, 'parsing: t(lang(), "phase.parsing")'],
  [/legitimacy: "проверка легитимности \(сеть\):"/g, 'legitimacy: t(lang(), "phase.legitimacy")'],
  [/decompiling: "декомпиляция:"/g, 'decompiling: t(lang(), "phase.decompiling")'],
  // Root pkg
  [/pkg: pkg \|\| "\(корень\)"/g, 'pkg: pkg || t(lang(), "toast.root_pkg")'],
  // Auto report
  [/let autoComment = "Автоматическая отправка \(telemetryEnabled\)"/g, 'let autoComment = t(lang(), "toast.auto_report")'],
];

for (const [re, repl] of replacements) {
  engine = engine.replace(re, repl);
}

// We need a lang() helper function since engine.tsx uses zustand store  
// Check if there's already a way to get lang
if (!engine.includes('const lang = ()')) {
  // Add a helper near the top of the store
  engine = engine.replace(
    'export const useEngine = create',
    'const lang = () => useEngine.getState().settings.language;\n\nexport const useEngine = create'
  );
}

fs.writeFileSync('src/state/engine.tsx', engine, 'utf8');
console.log('engine.tsx patched with i18n toast calls');
