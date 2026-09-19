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
    malware_findings: unknown[];
    import_conflicts: Record<string, string[]>;
    platform: string | null;
  };
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
