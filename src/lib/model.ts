export type JobStatus = "queued" | "running" | "done" | "failed" | "canceled";

export interface SourceFile {
  id: string;
  /** пакет, например net.novacraft.kits.kit */
  pkg: string;
  name: string;
  /** относительный путь от корня job.outDir - нужен для readTextFile */
  relPath: string;
  loc: number;
  /** предупреждение движка по этому файлу (обфускация, частичный вывод) */
  note?: string;
  // БАГ-ФИКС v1.8.4: раньше бинарные файлы вообще не попадали в список
  // (см. collectSourceFiles в state/engine.tsx) - теперь попадают, и это
  // поле позволяет FileTree показать им отдельную иконку, не дожидаясь
  // неудачной попытки readTextFile по клику.
  isBinary?: boolean;
  /** undefined, пока содержимое не подгружено через window.nano.readTextFile */
  code?: string;
  /** БАГ-ФИКС: раньше при отказе readTextFile file.code просто оставался
   * undefined НАВСЕГДА без единой ошибки - CodeView показывал "загрузка…"
   * бесконечно, неотличимо от "правда ещё грузится". loadError отличает
   * два состояния и даёт кнопку "Повторить" вместо вечного спиннера. */
  loadError?: string;
}

export interface Job {
  id: string;
  fileName: string;
  /** абсолютный путь к .jar на диске - нужен движку (window.nano.runDecompile) */
  jarPath: string;
  /** абсолютный путь к папке результата этого job'а (settings.outputDir + baseName) */
  outDir: string;
  sizeBytes: number;
  classCount: number | null;
  addedAt: number;
  status: JobStatus;
  progress: number;
  elapsedMs: number;
  error?: string;
  files?: SourceFile[];
  /** НОВОЕ v1.8.0: текущая стадия ("проверяю легитимность…" и т.п.),
   * распознаётся из "##ND_STAGE:xxx##"-маркеров движка. Только для
   * отображения под процентом - не влияет на статус job'а. */
  phase?: string | null;
  /** НОВОЕ v1.8.0: подробная статистика по jar - парсится из "##ND_RESULT:{json}##"
   * в конце обычного вывода движка (см. onLog в engine.tsx). Для карточки
   * плагина -> "Подробная информация". */
  details?: JobDetails | null;
  // НОВОЕ v1.7.6: для поиска похожих репозиториев на GitHub - берётся из
  // jarSummary() при добавлении файла (см. engine.tsx).
  pluginName?: string | null;
  pluginAuthor?: string | null;
}

/** Одна находка малварь-сканера (см. malware_findings_json() в
 * stats_json.cpp - severity/description/where 1-в-1 с тем, что печатает
 * движок). */
export interface MalwareFinding {
  severity: "high" | "medium" | "low";
  description: string;
  where: string;
}

/** Подмножество JSON, который печатает jar_process_result_to_json() в движке -
 * только то, что реально показываем в карточке (не весь blob 1-в-1). */
export interface JobDetails {
  status: string;
  stats: {
    classes_total: number;
    classes_parsed: number;
    library_classes_skipped: number;
    library_names_hit: string[];
    total_methods: number;
    decompiled_methods: number;
    fallback_methods: number;
    decompiled_pct: number;
    // БАГ-ФИКС v1.8.2: было unknown[] - карточка плагина показывала только
    // СЧЁТЧИК находок с припиской "см. терминал", хотя описание/severity/
    // расположение каждой находки уже приезжают в этом же JSON. Типизируем
    // как MalwareFinding[], чтобы можно было отрендерить список прямо тут.
    malware_findings: MalwareFinding[];
    import_conflicts: Record<string, string[]>;
    platform: string | null;
    // БАГ-ФИКС v1.8.2: движок считает эти два поля (см. stats_json.cpp) -
    // сколько синтетических switchmap-классов (компиляторные helper-классы
    // для switch по enum) скрыто из вывода и сколько пустых/бессмысленных
    // catch-блоков вычищено - но раньше они никак не доходили до UI, хотя
    // это ровно та прозрачность "что движок сделал с твоим кодом", которая
    // уже есть для fallback_methods/library_classes_skipped.
    synthetic_switchmap_classes_hidden: number;
    junk_catches_removed: number;
    // НОВОЕ v1.8.3 (HANDOFF_URGENT п.6) - см. process_jar.hpp::embedded_jars.
    // Пути ОТНОСИТЕЛЬНО outDir (движок уже извлёк их как обычный ресурс -
    // см. комментарий у addJarPaths в state/engine.tsx).
    embedded_jars: string[];
    // НОВОЕ v1.8.4 - см. verify.hpp::ProjectStats::total_source_lines.
    total_source_lines: number;
    // НОВОЕ v1.8.4 - см. verify.hpp::ProjectStats::file_notes. relPath ->
    // текст предупреждения (используется для SourceFile.note в engine.tsx).
    file_notes: Record<string, string>;
    // НОВОЕ v1.8.4 (телеметрия) - см. verify.hpp::FallbackContext.
    fallback_contexts: {
      file: string;
      method_hint: string;
      java_before: string[];
      bytecode: string[];
      java_after: string[];
    }[];
    // НОВОЕ v1.9.12 (волна 7, п.7 из HANDOFF_NEXT_AGENT_HANDOVER_WAVE7.md -
    // "hash_comparison/legitimacy вообще ещё не подключены к GUI"): движок
    // считает и отдаёт это ВСЕГДА (см. legitimacy_check.hpp/stats_json.cpp,
    // поле "legitimacy" внутри "stats") с версии, в которой появился сам
    // модуль легитимности - GUI просто никогда не объявлял тип для этого
    // поля, поэтому TS его молча отбрасывал, и PluginDetailsModal.tsx его
    // не рендерил. null - если проверка легитимности была явно отключена
    // (skip_legitimacy) для этого прогона.
    legitimacy: LegitimacyCheckResult | null;
  };
}

export interface LegitimacyCandidate {
  full_name: string;
  url: string;
  stars: number;
  // null - источник не даёт хэш дёшево (см. LEGITIMACY_SITES_MINI_LANGUAGE_
  // SPEC.md в архиве движка), либо конкретно этот кандидат не проверился.
  sha256_hex: string | null;
}

export interface LegitimacySourceResult {
  checked: boolean;
  found: boolean;
  // НОВОЕ v1.9.13: null = probe не выполнялся (HtmlSearch-источник),
  // false = недоступен/гео-блок, true = источник отвечает.
  host_reachable: boolean | null;
  candidates: LegitimacyCandidate[];
}

export interface HashComparisonResult {
  matching: string[];
  mismatching: string[];
}

export interface LegitimacyCheckResult {
  plugin_yml_fields: { website: string | null; authors: string[] };
  github: LegitimacySourceResult;
  modrinth: LegitimacySourceResult;
  spigot: LegitimacySourceResult;
  hangar: LegitimacySourceResult;
  hash_comparison: HashComparisonResult | null;
}

export type LogLevel = "info" | "ok" | "warn" | "err";

export interface LogLine {
  id: number;
  jobId: string;
  at: number;
  level: LogLevel;
  tag: string;
  msg: string;
}

export type LogFilter = "all" | "info" | "ok" | "warn" | "err";

export interface Settings {
  outputDir: string;
  threads: number;
  renameObfuscated: boolean;
  keepLineNumbers: boolean;
  openFolderOnDone: boolean;
  /** единственное поле здесь, которое реально доходит до движка -
   * остальные (threads/renameObfuscated/keepLineNumbers) сейчас чисто
   * визуальные: CLI принимает только [jarPath, outDir, --no-legitimacy-check?].
   * persisted в main-процессе через settings:get/settings:set. */
  legitimacyCheck: boolean;
  /** второе персистящееся поле - включает автопроверку обновлений при
   * старте (см. saveSettings/settings:set). */
  autoUpdateCheck: boolean;
  /** живой лого-марк в шапке + иконка окна/панели задач (Windows/Linux) -
   * НЕ сама иконка .exe/.app в проводнике, ту в рантайме не поменять
   * (ограничение ОС - зашивается при сборке). */
  appIcon: "terminal" | "layers";
  /** мастер первого запуска (EULA) показан и подтверждён - см.
   * electron/main.ts::Settings.setupCompleted. */
  setupCompleted: boolean;
  /** НОВОЕ v1.7.6: язык интерфейса - пока переведена только сама панель
   * настроек (см. src/lib/i18n.ts), это первый шаг инфраструктуры. */
  language: "ru" | "en";
}

export type ToastKind = LogLevel;

export interface Toast {
  id: number;
  kind: ToastKind;
  msg: string;
}

let idCounter = 0;
export function rid(prefix: string) {
  idCounter += 1;
  return `${prefix}-${idCounter.toString(36)}`;
}

export function fmtBytes(bytes: number) {
  if (bytes < 1024) return `${bytes} Б`;
  if (bytes < 1024 * 1024) return `${(bytes / 1024).toFixed(1)} КБ`;
  return `${(bytes / (1024 * 1024)).toFixed(2)} МБ`;
}

export function fmtNum(n: number) {
  return new Intl.NumberFormat("ru-RU").format(n);
}

export function fmtSeconds(ms: number) {
  return `${(ms / 1000).toFixed(2)} s`;
}

/** 00:03.412 — как в консольном выводе движка */
export function fmtClock(ms: number) {
  const total = Math.max(0, Math.floor(ms));
  const m = Math.floor(total / 60000);
  const s = Math.floor((total % 60000) / 1000);
  const rest = total % 1000;
  const pad = (v: number, w: number) => String(v).padStart(w, "0");
  return `${pad(m, 2)}:${pad(s, 2)}.${pad(rest, 3)}`;
}

export function baseName(fileName: string) {
  return fileName.replace(/\.jar$/i, "");
}

// БАГ-ФИКС: раньше пути для outDir склеивались через литеральный `/`
// прямо в JS-шаблонных строках (`${settings.outputDir}/${...}`) - на
// Windows, где outputDir обычно уже содержит обратные слеши
// (C:\Users\x\out), это давало смешанные разделители в путях
// (C:\Users\x\out/MyPlugin) - не крашит (Node на Windows принимает "/"
// в путях), но выглядит небрежно в логах/UI. Определяем разделитель по
// уже используемому в самом outputDir, а не жёстко "/".
export function joinOutDir(outputDir: string, name: string): string {
  const sep = outputDir.includes("\\") && !outputDir.includes("/") ? "\\" : "/";
  const trimmed = outputDir.endsWith(sep) ? outputDir.slice(0, -1) : outputDir;
  return `${trimmed}${sep}${name}`;
}
