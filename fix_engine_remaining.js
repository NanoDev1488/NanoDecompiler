const fs = require('fs');
let e = fs.readFileSync('src/state/engine.tsx', 'utf8');

// Remaining hardcoded Russian toast strings
const reps = [
  ['toast("Вы на последней версии", "ok")', 'toast(t(lang(), "toast.no_updates"), "ok")'],
  ['toast(`Доступно обновление клиента: ${r.latestVersion}`, "info")', 'toast(`${t(lang(), "toast.update_client")}: ${r.latestVersion}`, "info")'],
  ['toast("Обновлено", "ok")', 'toast(t(lang(), "toast.updated"), "ok")'],
  ['toast(r.error ?? "Не удалось обновить", "err")', 'toast(r.error ?? t(lang(), "toast.update_failed"), "err")'],
  ['toast("Не удалось обновить", "err")', 'toast(t(lang(), "toast.update_failed"), "err")'],
  ['toast("Очередь полна. Добавьте .jar следом.", "info")', 'toast(t(lang(), "toast.queue_warning"), "info")'],
  ['toast("Нет окружения. Проверьте настройки.", "warn")', 'toast(t(lang(), "toast.env_not_ready"), "warn")'],
  ['toast("Очередь очищена", "warn")', 'toast(t(lang(), "toast.stopped"), "warn")'],
  ['toast(`Не является .jar файлом: ${fileName}`, "warn")', 'toast(`${t(lang(), "toast.jar_warning")}: ${fileName}`, "warn")'],
  ['toast("Ни один файл не выбран", "err")', 'toast(t(lang(), "toast.file_select_canceled"), "err")'],
  ['toast("Нет текста - копировать нечего", "warn")', 'toast(t(lang(), "toast.no_text"), "warn")'],
  ['toast("Нет записей с текущим фильтром", "warn")', 'toast(t(lang(), "toast.no_log_filter"), "warn")'],
  ['toast("Настройки сохранены", "ok")', 'toast(t(lang(), "toast.settings_saved"), "ok")'],
  ['toast("Клиент успешно обновлён", "ok")', 'toast(t(lang(), "toast.app_updated"), "ok")'],
  ['toast("Сначала завершите настройку, потом начинайте", "warn")', 'toast(t(lang(), "toast.setup_first"), "warn")'],
  // Bind failed
  ['toast(`Не удалось привязать ключ для ${j.fileName}: ${s.error}`, "err")', 'toast(`${t(lang(), "toast.bind_failed")} ${j.fileName}: ${s.error}`, "err")'],
  ['toast(`Не удалось привязать ключ для ${j.fileName}`, "err")', 'toast(`${t(lang(), "toast.bind_failed")} ${j.fileName}`, "err")'],
  // Phases  
  ['validating: "Проверка файла:"', 'validating: t(lang(), "phase.validating")'],
  ['scanning: "сканирование вредоносного содержимого:"', 'scanning: t(lang(), "phase.scanning")'],
  ['parsing: "разбор байткод-классов:"', 'parsing: t(lang(), "phase.parsing")'],
  ['legitimacy: "проверка легитимности (сеть):"', 'legitimacy: t(lang(), "phase.legitimacy")'],
  ['decompiling: "декомпиляция:"', 'decompiling: t(lang(), "phase.decompiling")'],
  // Root pkg
  ['pkg: pkg || "(корень)"', 'pkg: pkg || t(lang(), "toast.root_pkg")'],
  // Auto report
  ['let autoComment = "Автоматическая отправка (telemetryEnabled)"', 'let autoComment = t(lang(), "toast.auto_report")'],
  // Malware
  ['toast("Обнаружен подозрительный код", "warn")', 'toast(t(lang(), "toast.malware_warn"), "warn")'],
  // File too big
  ['Нет доступа или слишком большой файл - используйте "Открыть в ОС"', '${t(lang(), "toast.file_too_big")}'],
  // Not a jar
  ['не является .jar', t => t], // skip, needs context
  // Engine failed
  ['Не завершился с кодом', '${t(lang(), "engine.failed")}'],
];

let count = 0;
for (const [from, to] of reps) {
  if (typeof to === 'function') continue;
  if (e.includes(from)) {
    e = e.split(from).join(to);
    count++;
  }
}

// Handle remaining complex patterns manually
e = e.replace(
  /toast\(`Готово: \$\{job\.fileName\} - \$\{fmtSeconds\(elapsed\)\}`, "ok"\)/g,
  'toast(`${t(lang(), "toast.done")}: ${job.fileName} - ${fmtSeconds(elapsed)}`, "ok")'
);
e = e.replace(
  /toast\(`Ошибка: \$\{job\.fileName\}\$\{error \? ` - \$\{error\}` : ""\}`, "err"\)/g,
  'toast(`${t(lang(), "toast.error")}: ${job.fileName}${error ? ` - ${error}` : ""}`, "err")'
);
e = e.replace(
  /toast\(`В очередь: \$\{fresh\.length\} архив\(ов\)`, "ok"\)/g,
  'toast(`${t(lang(), "toast.queued")}: ${fresh.length} ${t(lang(), "toast.archives")}`, "ok")'
);
e = e.replace(
  /toast\(`Пропущено: \$\{f\.name\} - не является \.jar`, "err"\)/g,
  'toast(`${f.name}: ${t(lang(), "toast.jar_warning")}`, "err")'
);

fs.writeFileSync('src/state/engine.tsx', e, 'utf8');
console.log(`Replaced ${count} direct strings, plus regex patterns`);
