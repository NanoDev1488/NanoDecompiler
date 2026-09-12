import {
  createContext,
  useCallback,
  useContext,
  useEffect,
  useMemo,
  useRef,
  useState,
  type ReactNode,
} from "react";
import {
  baseName,
  joinOutDir,
  fmtClock,
  fmtNum,
  fmtSeconds,
  rid,
  type Job,
  type LogFilter,
  type LogLine,
  type LogLevel,
  type Settings,
  type SourceFile,
  type Toast,
  type ToastKind,
} from "../lib/model";

const MAX_LOG_LINES = 800;

/* Собирает плоское дерево файлов из реального вывода движка через
   window.nano.listDir (рекурсивно), без чтения содержимого - код каждого
   файла подгружается лениво в selectFile() через readTextFile().
   БАГ-ФИКС: раньше в список попадали ТОЛЬКО .java - plugin.yml, pom.xml,
   config.yml и прочие ресурсы декомпилированного Maven-проекта вообще не
   показывались в мини-просмотрщике (не то что не открывались - их не было
   в дереве файлов вовсе). Теперь читаемые текстовые форматы, которые
   реально встречаются в выводе движка, показываются тоже. */
const VIEWABLE_EXT = /\.(java|ya?ml|xml|properties|json|md|txt|gitignore|gitattributes)$/i;

async function collectSourceFiles(outDir: string, relDir = ""): Promise<SourceFile[]> {
  const res = await window.nano.listDir(outDir, relDir);
  if (!res.ok || !res.items) return [];
  const out: SourceFile[] = [];
  for (const item of res.items) {
    const rel = relDir ? `${relDir}/${item.name}` : item.name;
    if (item.isDir) {
      out.push(...(await collectSourceFiles(outDir, rel)));
    } else if (VIEWABLE_EXT.test(item.name)) {
      // БАГ-ФИКС (реальный, найден на настоящем EssentialsX - "группы
      // файлов называются именами самих файлов"): старый regex
      // `/\/[^/]+$/` требует СЛЭШ перед последним сегментом - для файла
      // ПРЯМО В КОРНЕ вывода (без подпапки, напр. просто "book.txt")
      // слэша нет вообще, .replace() не находит совпадение и возвращает
      // строку БЕЗ ИЗМЕНЕНИЙ - весь "book.txt" целиком становился
      // значением pkg вместо пустой строки (которая корректно
      // fallback'ится на "(корень)" ниже). Теперь явно вырезаем
      // последний сегмент через lastIndexOf, не полагаясь на то, что
      // слэш обязательно есть в строке.
      const stripped = rel.replace(/^src\/main\/(java|resources)\//, "");
      const lastSlash = stripped.lastIndexOf("/");
      const pkg = (lastSlash === -1 ? "" : stripped.slice(0, lastSlash)).replace(/\//g, ".");
      out.push({ id: rid("f"), pkg: pkg || "(корень)", name: item.name, relPath: rel, loc: 0 });
    }
  }
  return out;
}

interface EngineApi {
  jobs: Job[];
  log: LogLine[];
  runningJob: Job | null;
  runningElapsed: number | null;
  selectedJobId: string | null;
  selectedJob: Job | null;
  openFileByJob: Record<string, string>;
  terminalOpen: boolean;
  logFilter: LogFilter;
  settings: Settings;
  settingsLoaded: boolean;
  settingsOpen: boolean;
  updateModalOpen: boolean;
  paletteOpen: boolean;
  envIssue: boolean;
  engineVersion: string | null;
  guiVersion: string | null;
  javaEnv: { ok: boolean; text?: string } | null;
  mavenEnv: { ok: boolean; text?: string } | null;
  installingTool: "java" | "maven" | null;
  installProgress: { type: "progress"; label: string; pct: number | null; downloaded_mb: number; total_mb: number | null } | null;
  iconThumbnails: { terminal: string | null; layers: string | null };
  sidebarWidth: number;
  fileTreeWidth: number;
  terminalHeight: number;
  updateInfo: UpdateInfo;
  toasts: Toast[];
  queuedCount: number;

  addFiles(list: FileList | File[]): void;
  openFileDialog(): void;
  startQueue(): void;
  stopRunning(): void;
  stopAll(): void;
  cancelJob(id: string): void;
  removeJob(id: string): void;
  clearQueue(): void;
  selectJob(id: string): void;
  selectFile(jobId: string, fileId: string): void;
  setLogFilter(f: LogFilter): void;
  toggleTerminal(): void;
  clearLog(): void;
  copyLog(): void;
  copyText(text: string, what: string): void;
  openOutput(job: Job): void;
  setSettingsOpen(open: boolean): void;
  setUpdateModalOpen(open: boolean): void;
  setSidebarWidth(w: number): void;
  setFileTreeWidth(w: number): void;
  setTerminalHeight(h: number): void;
  saveSettings(next: Settings): void;
  completeSetup(): void;
  setPaletteOpen(open: boolean): void;
  resolveEnvIssue(): void;
  checkForUpdates(silent?: boolean): void;
  applyEngineUpdate(): void;
  openClientDownload(): void;
  checkEnv(): void;
  installTool(which: "java" | "maven"): void;
  toast(msg: string, kind?: ToastKind): void;
  dismissToast(id: number): void;
}

const Ctx = createContext<EngineApi | null>(null);

const DEFAULT_SETTINGS: Settings = {
  outputDir: "~/NanoDecompiler/out",
  threads: 8,
  renameObfuscated: true,
  keepLineNumbers: true,
  openFolderOnDone: false,
  legitimacyCheck: true,
  autoUpdateCheck: true,
  appIcon: "terminal",
  setupCompleted: false,
};

export interface UpdateInfo {
  checking: boolean;
  applying: boolean;
  kind: "none" | "engine" | "client" | "closed_beta" | null;
  currentVersion?: string;
  latestVersion?: string;
  downloadUrl?: string | null;
  clientDownloadUrl?: string | null;
  releaseUrl?: string;
  error?: string;
}

export function EngineProvider({ children }: { children: ReactNode }) {
  const [jobs, setJobs] = useState<Job[]>([]);
  const [log, setLog] = useState<LogLine[]>([]);
  const jobsRef = useRef<Job[]>(jobs);
  jobsRef.current = jobs;
  const [selectedJobId, setSelectedJobId] = useState<string | null>(null);
  const [openFileByJob, setOpenFileByJob] = useState<Record<string, string>>({});
  const [terminalOpen, setTerminalOpen] = useState(true);
  const [logFilter, setLogFilter] = useState<LogFilter>("all");
  const [settingsOpen, setSettingsOpen] = useState(false);
  const [updateModalOpen, setUpdateModalOpen] = useState(false);
  const [paletteOpen, setPaletteOpen] = useState(false);
  const [envIssue, setEnvIssue] = useState(false);
  // БАГ-ФИКС: engineVersion раньше была захардкожена заглушкой "2.4.1" в
  // каждом компоненте отдельно (SettingsModal/AppHeader/Titlebar/
  // StatusBar) - один запрос здесь на старте приложения, все читают из
  // контекста вместо своего собственного дублирующего IPC-вызова.
  const [engineVersion, setEngineVersion] = useState<string | null>(null);
  const [guiVersion, setGuiVersion] = useState<string | null>(null);
  const [updateInfo, setUpdateInfo] = useState<UpdateInfo>({ checking: false, applying: false, kind: null });
  // БАГ-ФИКС: раньше envIssue переключался вручную (toggleEnvIssue), без
  // единой реальной проверки java/mvn (см. env:check в main.ts). javaEnv/
  // mavenEnv - настоящий результат "java -version"/"mvn -version" через
  // дочерний процесс.
  const [javaEnv, setJavaEnv] = useState<{ ok: boolean; text?: string } | null>(null);
  const [mavenEnv, setMavenEnv] = useState<{ ok: boolean; text?: string } | null>(null);
  const [iconThumbnails, setIconThumbnails] = useState<{ terminal: string | null; layers: string | null }>({
    terminal: null,
    layers: null,
  });
  // Растягиваемые панели (по просьбе пользователя) - сессионное состояние,
  // сбрасывается при перезапуске приложения (осознанный компромисс - не
  // усложняем settings:get/set ради ширины панели в пикселях).
  const [sidebarWidth, setSidebarWidth] = useState(292);
  const [fileTreeWidth, setFileTreeWidth] = useState(248);
  const [terminalHeight, setTerminalHeight] = useState(228);
  const [toasts, setToasts] = useState<Toast[]>([]);
  const [runningElapsed, setRunningElapsed] = useState<number | null>(null);
  const [settings, setSettings] = useState<Settings>(DEFAULT_SETTINGS);
  const [settingsLoaded, setSettingsLoaded] = useState(false);

  const intervalRef = useRef<number | null>(null);
  const runningIdRef = useRef<string | null>(null);
  const startedAtRef = useRef(0);
  const logIdRef = useRef(0);
  const fileInputRef = useRef<HTMLInputElement>(null);
  const unsubscribeLogRef = useRef<(() => void) | null>(null);
  // НОВОЕ v1.7.3: как только пришёл ХОТЬ ОДИН настоящий прогресс-бар от
  // движка (см. parseEngineProgressBar) - время-экстраполяция в интервале
  // ниже должна ПЕРЕСТАТЬ подталкивать прогресс вверх самостоятельно,
  // иначе на медленном/сложном jar (движок реально ещё на 20%, но прошло
  // уже 15 секунд) время-экстраполяция перебила бы настоящее значение
  // выдуманным более высоким - хуже, чем не иметь реальных данных вообще.
  const hasRealProgressRef = useRef(false);

  const dismissToast = useCallback((id: number) => {
    setToasts(prev => prev.filter(t => t.id !== id));
  }, []);

  const toast = useCallback(
    (msg: string, kind: ToastKind = "info") => {
      const id = ++logIdRef.current;
      setToasts(prev => [...prev.slice(-3), { id, kind, msg }]);
      window.setTimeout(() => dismissToast(id), 3600);
    },
    [dismissToast],
  );

  const checkEnv = useCallback(() => {
    // БАГ-ФИКС: раньше не сбрасывал javaEnv/mavenEnv перед перепроверкой -
    // UI продолжал показывать старое значение, пока идёт новый запрос, из-за
    // чего клик по "Проверить снова" выглядел так, будто ничего не
    // происходит (нет "проверяю…" между кликом и ответом).
    setJavaEnv(null);
    setMavenEnv(null);
    window.nano
      .checkEnv()
      .then(r => {
        setJavaEnv(r.java);
        setMavenEnv(r.maven);
        setEnvIssue(!r.java.ok);
      })
      .catch(() => {
        setJavaEnv({ ok: false });
        setMavenEnv({ ok: false });
      });
  }, []);

  // НОВОЕ v1.7.3 (реальный запрос - установка Java/Maven прямо из настроек,
  // backend-плюмбинг tools:install/tools:progress уже существовал
  // (electron/main.ts, preload.ts) - не хватало только UI-триггера).
  const [installingTool, setInstallingTool] = useState<"java" | "maven" | null>(null);
  const [installProgress, setInstallProgress] = useState<{
    type: "progress";
    label: string;
    pct: number | null;
    downloaded_mb: number;
    total_mb: number | null;
  } | null>(null);

  const installTool = useCallback(
    (which: "java" | "maven") => {
      if (installingTool) return;  // уже что-то ставим - не даём запустить второй установщик поверх
      setInstallingTool(which);
      setInstallProgress(null);
      const unsubscribe = window.nano.onToolsProgress(e => setInstallProgress(e));
      window.nano
        .installTools(which)
        .then(r => {
          const ok = which === "java" ? !!r.java : !!r.maven;
          if (ok) toast(`${which === "java" ? "Java" : "Maven"} установлен(а)`, "ok");
          else toast(r.errors?.[0] ?? r.error ?? `Не удалось установить ${which === "java" ? "Java" : "Maven"}`, "err");
        })
        .catch(err => toast(String(err?.message ?? err), "err"))
        .finally(() => {
          unsubscribe();
          setInstallingTool(null);
          setInstallProgress(null);
          checkEnv();  // подтягиваем реальный статус после установки (успешной или нет)
        });
    },
    [installingTool, checkEnv, toast],
  );

  const checkForUpdates = useCallback((silent = false) => {
    setUpdateInfo(u => ({ ...u, checking: true, error: undefined }));
    window.nano
      .checkUpdate()
      .then(r => {
        if (!r.ok) {
          setUpdateInfo(u => ({ ...u, checking: false, error: r.error }));
          if (!silent) toast(r.error ?? "Не удалось проверить обновления", "err");
          return;
        }
        setUpdateInfo({
          checking: false,
          applying: false,
          kind: r.updateKind ?? "none",
          currentVersion: r.currentVersion,
          latestVersion: r.latestVersion,
          downloadUrl: r.downloadUrl,
          clientDownloadUrl: r.clientDownloadUrl,
          releaseUrl: r.releaseUrl,
        });
        if (!silent) {
          if (r.updateKind === "none") toast("У вас последняя версия", "ok");
          else if (r.updateKind === "engine") toast(`Доступно обновление движка: ${r.latestVersion}`, "info");
          else if (r.updateKind === "client") toast(`Доступно обновление приложения: ${r.latestVersion}`, "info");
        }
      })
      .catch(e => {
        setUpdateInfo(u => ({ ...u, checking: false, error: String(e) }));
        if (!silent) toast("Не удалось проверить обновления", "err");
      });
  }, [toast]);

  const applyEngineUpdate = useCallback(() => {
    if (!updateInfo.downloadUrl) return;
    setUpdateInfo(u => ({ ...u, applying: true }));
    window.nano
      .applyUpdate(updateInfo.downloadUrl, updateInfo.latestVersion)
      .then(r => {
        setUpdateInfo(u => ({ ...u, applying: false }));
        if (r.ok) {
          toast("Движок обновлён", "ok");
          setUpdateInfo(u => ({ ...u, kind: "none" }));
        } else {
          toast(r.error ?? "Не удалось обновить движок", "err");
        }
      })
      .catch(() => {
        setUpdateInfo(u => ({ ...u, applying: false }));
        toast("Не удалось обновить движок", "err");
      });
  }, [toast, updateInfo.downloadUrl, updateInfo.latestVersion]);

  const openClientDownload = useCallback(() => {
    const url = updateInfo.clientDownloadUrl ?? updateInfo.releaseUrl;
    if (url) window.nano.openExternal(url).catch(() => toast("Не удалось открыть ссылку", "err"));
  }, [toast, updateInfo.clientDownloadUrl, updateInfo.releaseUrl]);


  // Из main-процесса персистится ТОЛЬКО legitimacyCheck/autoUpdateCheck
  // (см. AppSettings в preload.ts) - остальные поля Settings живут только
  // в рендерере на время сессии.
  useEffect(() => {
    let cancelled = false;
    window.nano
      .getSettings()
      .then(s => {
        if (cancelled) return;
        setSettings(prev => ({ ...prev, legitimacyCheck: s.legitimacyCheck, autoUpdateCheck: s.autoUpdateCheck, appIcon: s.appIcon, setupCompleted: s.setupCompleted }));
        // Автопроверка обновлений при старте - только ПОСЛЕ того, как
        // узнали настоящее значение из настроек (не дефолт), иначе
        // выключенная пользователем автопроверка на миг игнорировалась бы.
        if (s.autoUpdateCheck) checkForUpdates(true);
      })
      .catch(() => {})
      .finally(() => {
        // БАГ-ФИКС/фича: settings.setupCompleted в начальном состоянии -
        // всегда false (дефолт), пока реальное значение не подгрузится с
        // диска - для ВОЗВРАЩАЮЩЕГОСЯ пользователя (уже видел мастер
        // первого запуска раньше) это дало бы едва заметное "мигание"
        // мастера на долю секунды при каждом старте. settingsLoaded не
        // даёт App.tsx решать, показывать ли мастер, пока не пришёл
        // настоящий ответ.
        if (!cancelled) setSettingsLoaded(true);
      });
    return () => {
      cancelled = true;
    };
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  // Настоящая версия движка вместо хардкода - см. engine:version в
  // main.ts / --version в cli_main.cpp. Молча остаётся null при ошибке
  // (движок не найден и т.п.) - UI показывает "—" вместо версии, это
  // видимо и достаточно, отдельный toast здесь не нужен.
  useEffect(() => {
    let cancelled = false;
    window.nano
      .getEngineVersion()
      .then(r => {
        if (!cancelled && r.ok && r.version) setEngineVersion(r.version);
      })
      .catch(() => {});
    return () => {
      cancelled = true;
    };
  }, []);

  useEffect(() => {
    checkEnv();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  useEffect(() => {
    let cancelled = false;
    window.nano
      .getGuiVersion()
      .then(v => {
        if (!cancelled) setGuiVersion(v);
      })
      .catch(() => {});
    return () => {
      cancelled = true;
    };
  }, []);

  useEffect(() => {
    let cancelled = false;
    window.nano
      .getAppIconThumbnails()
      .then(t => {
        if (!cancelled) setIconThumbnails(t);
      })
      .catch(() => {});
    return () => {
      cancelled = true;
    };
  }, []);

  useEffect(() => {
    return () => {
      if (intervalRef.current !== null) window.clearInterval(intervalRef.current);
      unsubscribeLogRef.current?.();
    };
  }, []);

  const pushLog = useCallback((jobId: string, level: LogLevel, tag: string, msg: string) => {
    setLog(prev => {
      const next = [
        ...prev,
        { id: ++logIdRef.current, jobId, at: Date.now() - startedAtRef.current, level, tag, msg },
      ];
      return next.length > MAX_LOG_LINES ? next.slice(next.length - MAX_LOG_LINES) : next;
    });
  }, []);

  const patchJob = useCallback((id: string, patch: Partial<Job>) => {
    setJobs(prev => prev.map(j => (j.id === id ? { ...j, ...patch } : j)));
  }, []);

  // Классифицирует сырую строку стдаута/стдерра движка по тегу/уровню для
  // подсветки в терминале - реальный NanoDecompilerCLI не шлёт structured
  // JSON построчно (только один JSON в самом конце), так что это
  // эвристика по префиксам, которые движок реально печатает.
  const classifyLine = useCallback((line: string, stream: "stdout" | "stderr"): { level: LogLevel; tag: string } => {
    // БАГ-ФИКС v1.7.3 (реальная жалоба - предупреждение о вредоносном коде
    // "терялось" в потоке лога): движок печатает находки малварь-сканера как
    // "ВНИМАНИЕ: обнаружены признаки потенциально вредоносного кода..."
    // (см. malware_scan.hpp/process_jar.cpp) - слово "ВНИМАНИЕ" не содержит
    // ни "warn", ни "предупрежд", так что строка проваливалась в default-
    // ветку ниже и красилась как ОБЫЧНАЯ info-строка, неотличимая от рядового
    // прогресса. Классифицируем как "err" (не просто "warn") - находка
    // малварь-сканера серьёзнее обычного предупреждения и заслуживает
    // самого заметного визуального оформления (красный фон строки в
    // Terminal.tsx), которое сейчас есть только у уровня "err".
    if (/вредоносн/i.test(line)) return { level: "err", tag: "malware" };
    if (stream === "stderr" || /error|ошибка|fail/i.test(line)) return { level: "err", tag: "stderr" };
    if (/warn|предупрежд|внимание/i.test(line)) return { level: "warn", tag: "warn" };
    if (/\bok\b|готово|done|success/i.test(line)) return { level: "ok", tag: "engine" };
    return { level: "info", tag: "engine" };
  }, []);

  // НОВОЕ v1.7.3 (реальный запрос - прогресс-бар "застревал" на 2%, время-
  // экстраполяция была лучше, чем ничего, но не настоящий прогресс): движок
  // теперь сам печатает построчный текстовый прогресс-бар вида
  // "[==========----------] 42%" (см. process_jar.cpp, kProgressBarWidth=20).
  // Извлекаем долю ЗАПОЛНЕНИЯ БАРА ПО КОЛИЧЕСТВУ символов '=' между
  // скобками (а не парсим отдельно напечатанное число процентов - оно там
  // для человека, который запустил CLI напрямую) - при желании ширину бара
  // можно поменять на стороне движка, GUI не привязан к конкретному числу.
  const PROGRESS_BAR_RE = /^\[([=\-]+)\]\s+\d+%$/;
  const parseEngineProgressBar = useCallback((line: string): number | null => {
    const m = PROGRESS_BAR_RE.exec(line.trim());
    if (!m) return null;
    const bar = m[1];
    const filled = (bar.match(/=/g) ?? []).length;
    return bar.length > 0 ? filled / bar.length : null;
  }, []);

  const finalize = useCallback(
    async (jobId: string, ok: boolean, error?: string) => {
      if (intervalRef.current !== null) {
        window.clearInterval(intervalRef.current);
        intervalRef.current = null;
      }
      unsubscribeLogRef.current?.();
      unsubscribeLogRef.current = null;

      const elapsed = Date.now() - startedAtRef.current;
      runningIdRef.current = null;
      setRunningElapsed(null);

      const job = jobsRef.current.find(j => j.id === jobId);
      let files: SourceFile[] | undefined;
      if (ok && job) {
        try {
          files = await collectSourceFiles(job.outDir);
        } catch {
          files = [];
        }
      }

      setJobs(list =>
        list.map(j =>
          j.id === jobId
            ? { ...j, status: ok ? "done" : "failed", progress: ok ? 1 : j.progress, elapsedMs: elapsed, files, error }
            : j,
        ),
      );

      if (ok && files?.length) {
        setSelectedJobId(jobId);
        setOpenFileByJob(m => (m[jobId] ? m : { ...m, [jobId]: files[0].id }));
      }

      if (job) {
        if (ok) {
          toast(`Готово: ${job.fileName} — ${fmtSeconds(elapsed)}`, "ok");
          if (settings.openFolderOnDone) window.nano.openPath(job.outDir).catch(() => {});
        } else {
          toast(`Ошибка: ${job.fileName}${error ? ` — ${error}` : ""}`, "err");
        }
      }

      window.setTimeout(() => {
        if (runningIdRef.current) return;
        const next = jobsRef.current.find(j => j.status === "queued");
        if (next) runRef.current(next.id);
      }, 400);
    },
    [settings.openFolderOnDone, toast],
  );

  const run = useCallback(
    async (jobId: string) => {
      if (runningIdRef.current) return;
      const job = jobsRef.current.find(j => j.id === jobId);
      if (!job) return;

      // БАГ-ФИКС: outDir раньше фиксировался один раз в addJarPaths() в
      // момент добавления в очередь. Если пользователь менял "Папку
      // результата" в настройках ПОСЛЕ добавления файла, но ДО запуска -
      // job всё равно уходил в старую папку. Пересчитываем outDir из
      // актуальных settings прямо перед запуском - но так же аккуратно,
      // как и в addJarPaths(), проверяем коллизию с ДРУГИМИ job'ами
      // (см. комментарий там же) - иначе этот пересчёт мог тихо откатить
      // разруливание одноимённых файлов обратно к общей папке.
      let outDir = joinOutDir(settings.outputDir, baseName(job.fileName));
      const others = jobsRef.current.filter(j => j.id !== jobId);
      if (others.some(j => j.outDir === outDir)) {
        let n = 2;
        let candidate = joinOutDir(settings.outputDir, `${baseName(job.fileName)}-${n}`);
        while (others.some(j => j.outDir === candidate)) {
          n += 1;
          candidate = joinOutDir(settings.outputDir, `${baseName(job.fileName)}-${n}`);
        }
        outDir = candidate;
      }
      if (outDir !== job.outDir) patchJob(jobId, { outDir });
      const runJob = { ...job, outDir };

      runningIdRef.current = jobId;
      startedAtRef.current = Date.now();
      hasRealProgressRef.current = false;
      setRunningElapsed(0);
      setSelectedJobId(jobId);
      setLog(prev => prev.filter(l => l.jobId !== jobId));

      pushLog(jobId, "info", "engine", `spawn NanoDecompilerCLI · in=${runJob.fileName} out=${runJob.outDir}`);
      patchJob(jobId, { status: "running", progress: 0.05, elapsedMs: 0 });

      intervalRef.current = window.setInterval(() => {
        const el = Date.now() - startedAtRef.current;
        setRunningElapsed(el);
        // БАГ-ФИКС v1.7.3 (пункт #27 бэклога - "2% за 16 секунд" на больших/
        // сложных jar вроде ViaVersion): прогресс раньше двигался ТОЛЬКО по
        // событиям `onLog` (+0.015 за пачку строк). Движок печатает в stdout
        // довольно редко на больших jar, а тяжёлые классы (структуризация
        // CFG) могут занимать секунды БЕЗ единой строки вывода - индикатор
        // выглядел замёршим, хотя реальная работа шла. Теперь на каждый тик
        // таймера (97мс, уже существовал для elapsedMs) считаем МОНОТОННУЮ
        // цель по прошедшему времени (асимптота к 0.92, темп подобран так,
        // чтобы за ~12 секунд дойти примерно до 2/3 пути) и берём max с уже
        // накопленным прогрессом от onLog - какой бы сигнал ни оказался
        // "быстрее" в моменте, индикатор никогда не идёт назад и никогда не
        // замирает надолго.
        const timeBasedTarget = 0.92 * (1 - Math.exp(-el / 8000));
        setJobs(prev =>
          prev.map(j =>
            j.id === runningIdRef.current && j.status === "running"
              ? hasRealProgressRef.current
                ? { ...j, elapsedMs: el }
                : { ...j, elapsedMs: el, progress: Math.max(j.progress, timeBasedTarget) }
              : j.id === runningIdRef.current
                ? { ...j, elapsedMs: el }
                : j,
          ),
        );
      }, 97);

      unsubscribeLogRef.current = window.nano.onLog(e => {
        let realProgress: number | null = null;
        for (const l of e.lines) {
          // НОВОЕ v1.7.3: строка прогресс-бара - служебный сигнал для GUI,
          // не полезная информация для человека в терминале (20 делений -
          // до 20 строк за job, не страшно, но текст вида "[===---] 42%"
          // ничего не даёт по сравнению с уже видимым визуальным баром) -
          // не пушим её в лог, только используем для реального прогресса.
          const barFraction = parseEngineProgressBar(l.line);
          if (barFraction !== null) {
            realProgress = barFraction;
            continue;
          }
          const { level, tag } = classifyLine(l.line, l.stream);
          pushLog(jobId, level, tag, l.line);
        }
        // реального численного прогресса раньше CLI не сообщал построчно -
        // теперь сообщает (см. parseEngineProgressBar выше); когда движок
        // ещё не долистал до первого класса (парсинг jar, легитимность,
        // построение имён) реальных данных ещё нет - в это время (и как
        // подстраховка, если по какой-то причине бар не распознался) плавно
        // подводим индикатор к почти-концу по времени by отдельному
        // таймеру выше, чтобы не показывать замёршую полоску.
        if (realProgress !== null) {
          hasRealProgressRef.current = true;
          patchJob(jobId, { progress: Math.max(0.02, Math.min(0.99, realProgress)) });
        } else if (!hasRealProgressRef.current) {
          patchJob(jobId, { progress: Math.min(0.92, (jobsRef.current.find(j => j.id === jobId)?.progress ?? 0) + 0.015) });
        }
      });

      try {
        const res = await window.nano.runDecompile(runJob.jarPath, runJob.outDir);
        // БАГ-ФИКС (реальный, по прямой жалобе пользователя): stopRunning()
        // уже мог пометить job как "canceled" и показать toast "Остановлено"
        // ДО того, как этот await вообще успел вернуться (убитый процесс
        // всё равно рано или поздно эмитит close с ненулевым кодом) - без
        // этой проверки res.ok=false после отмены СНОВА перезаписывал
        // статус на "failed" и показывал пугающее "движок завершился с
        // кодом N" прямо поверх уже показанного пользователю "Остановлено".
        if (jobsRef.current.find(j => j.id === jobId)?.status === "canceled") return;
        if (res.ok) {
          await finalize(jobId, true);
        } else {
          pushLog(jobId, "err", "engine", res.error ?? `движок завершился с кодом ${res.code}`);
          await finalize(jobId, false, res.error);
        }
      } catch (e) {
        if (jobsRef.current.find(j => j.id === jobId)?.status === "canceled") return;
        pushLog(jobId, "err", "engine", String(e));
        await finalize(jobId, false, String(e));
      }
    },
    [classifyLine, finalize, patchJob, pushLog],
  );

  const runRef = useRef(run);
  runRef.current = run;

  const startQueue = useCallback(() => {
    // БАГ-ФИКС: раньше здесь была жёсткая блокировка "if (envIssue) return"
    // с сообщением "движку нужен JRE 17+" - ЛОЖЬ: движок написан на чистом
    // C++17, для самой декомпиляции Java не требуется вообще. Java/Maven
    // нужны ТОЛЬКО если пользователь сам захочет вручную собрать
    // сгенерированный Maven-проект (mvn compile) уже ПОСЛЕ декомпиляции -
    // это никак не влияет на работу самого движка. Эта блокировка не
    // просто показывала неверный текст, а реально не давала запустить
    // декомпиляцию на машинах без Java - самый severe баг из всех.
    if (runningIdRef.current) return;
    const next = jobsRef.current.find(
      j => j.status === "queued" || j.status === "canceled" || j.status === "failed",
    );
    if (!next) {
      toast("Очередь пуста. Добавьте .jar слева.", "info");
      return;
    }
    runRef.current(next.id);
  }, [toast]);

  const stopRunning = useCallback(() => {
    const id = runningIdRef.current;
    if (!id) return;
    window.nano.cancel().catch(() => {});
    if (intervalRef.current !== null) {
      window.clearInterval(intervalRef.current);
      intervalRef.current = null;
    }
    unsubscribeLogRef.current?.();
    unsubscribeLogRef.current = null;
    runningIdRef.current = null;
    setRunningElapsed(null);
    patchJob(id, { status: "canceled" });
    pushLog(id, "err", "abort", "canceled by user");
    toast("Декомпиляция остановлена. Прогресс не сохранён.", "warn");
  }, [patchJob, pushLog, toast]);

  // По просьбе пользователя: если в очереди 2+ jar, "Остановить всё"
  // останавливает текущий И не даёт автопродолжению (см. finalize() -
  // через 400мс само подхватывает следующий status:"queued") забрать
  // следующий - помечаем ВСЕ ожидающие job'ы отменёнными тоже, не только
  // текущий.
  const stopAll = useCallback(() => {
    const wasRunning = runningIdRef.current !== null;
    stopRunning();
    setJobs(prev => prev.map(j => (j.status === "queued" ? { ...j, status: "canceled" } : j)));
    if (!wasRunning) toast("Очередь очищена", "warn");
  }, [stopRunning, toast]);

  // Добавление через реальный системный диалог (Electron) - единственный
  // надёжный способ получить настоящий абсолютный путь к .jar. Обычный
  // <input type=file>/drag-drop в песочнице рендерера пути не даёт (кроме
  // легаси File.path, который используем как резервный вариант ниже).
  const addJarPaths = useCallback(
    (paths: string[]) => {
      const current = jobsRef.current;
      const fresh: Job[] = [];
      // БАГ-ФИКС (реальный, найден при систематическом аудите): дедупликация
      // сравнивала только jarPath целиком - два файла с ОДИНАКОВЫМ именем
      // из РАЗНЫХ папок (~/Downloads/MyPlugin.jar и ~/Desktop/MyPlugin.jar)
      // не считались дублями, оба добавлялись в очередь, но outDir
      // вычислялся ТОЛЬКО из имени файла - оба получали ОДИН И ТОТ ЖЕ
      // выходной каталог, и вторая декомпиляция молча перезаписывала/
      // смешивала результат первой без единого предупреждения.
      const usedOutDirs = new Set(current.map(j => j.outDir));
      for (const p of paths) {
        const fileName = p.split(/[/\\]/).pop() ?? p;
        if (
          current.some(j => j.jarPath === p && j.status !== "done") ||
          fresh.some(j => j.jarPath === p)
        ) {
          toast(`Уже в очереди: ${fileName}`, "warn");
          continue;
        }
        let outDir = joinOutDir(settings.outputDir, baseName(fileName));
        if (usedOutDirs.has(outDir)) {
          let n = 2;
          let candidate = joinOutDir(settings.outputDir, `${baseName(fileName)}-${n}`);
          while (usedOutDirs.has(candidate)) {
            n += 1;
            candidate = joinOutDir(settings.outputDir, `${baseName(fileName)}-${n}`);
          }
          outDir = candidate;
          toast(`Одноимённый файл уже в очереди - результат пойдёт в отдельную папку (${baseName(fileName)}-${n})`, "warn");
        }
        usedOutDirs.add(outDir);
        fresh.push({
          id: rid("job"),
          fileName,
          jarPath: p,
          outDir,
          sizeBytes: 0,
          classCount: null,
          addedAt: Date.now(),
          status: "queued",
          progress: 0,
          elapsedMs: 0,
        });
      }
      if (fresh.length > 0) {
        setJobs(prev => [...prev, ...fresh]);
        toast(`В очереди: ${fresh.length} архив(а)`, "ok");
        // подтягиваем реальную сводку (размер/классы/пакеты) по каждому
        for (const j of fresh) {
          window.nano
            .jarSummary(j.jarPath)
            .then(s => {
              if (s.error) {
                toast(`Не удалось прочитать сводку по ${j.fileName}: ${s.error}`, "err");
                return;
              }
              // БАГ-ФИКС: раньше байты доставали обратным regex-разбором уже
              // ОТФОРМАТИРОВАННОЙ строки размера ("1.5 МБ" -> replace(/[^\d]/,"")
              // стирал точку вместе с буквами -> "15", а не ~1500000) - реальное
              // искажение на порядки. sizeBytes теперь отдельное числовое поле
              // напрямую из jarSummary.ts/jar_summary.cpp, без реконструкции.
              patchJob(j.id, { classCount: s.classes, sizeBytes: s.sizeBytes ?? j.sizeBytes });
            })
            .catch(e => {
              // БАГ-ФИКС: раньше молча глотал любую ошибку - если jarSummary()
              // падал (что угодно: битый jar, ошибка чтения и т.п.), карточка
              // плагина вечно показывала "0 Б · 0 классов" без единой зацепки,
              // что вообще пошло не так.
              toast(`Не удалось прочитать сводку по ${j.fileName}`, "err");
              console.error("jarSummary failed:", e);
            });
        }
      }
    },
    [patchJob, settings.outputDir, toast],
  );

  const addFiles = useCallback(
    (list: FileList | File[]) => {
      const paths: string[] = [];
      for (const f of Array.from(list)) {
        if (!/\.jar$/i.test(f.name)) {
          toast(`Пропущено: ${f.name} — нужен .jar`, "err");
          continue;
        }
        // legacy Electron File.path (доступно на Electron 31 при
        // перетаскивании файла из ОС в окно приложения)
        const withPath = f as File & { path?: string };
        if (withPath.path) paths.push(withPath.path);
        else toast(`${f.name}: нет доступа к пути файла — используйте "Открыть файл"`, "err");
      }
      if (paths.length) addJarPaths(paths);
    },
    [addJarPaths, toast],
  );

  const openFileDialog = useCallback(() => {
    window.nano
      .selectJar()
      .then(paths => {
        if (paths.length) addJarPaths(paths);
      })
      .catch(() => toast("Диалог выбора файла недоступен", "err"));
  }, [addJarPaths, toast]);

  const cancelJob = useCallback(
    (id: string) => {
      if (runningIdRef.current === id) {
        stopRunning();
        return;
      }
      setJobs(prev => prev.filter(j => j.id !== id));
      setLog(prev => prev.filter(l => l.jobId !== id));
      setSelectedJobId(prev => (prev === id ? null : prev));
    },
    [stopRunning],
  );

  const removeJob = useCallback(
    (id: string) => {
      // БАГ-ФИКС (реальный, найден при систематическом аудите):
      // clearQueue() уже защищал выполняющийся job (не удалял его), но
      // removeJob() - нет. Удаление карточки job'а ПРЯМО во время его
      // выполнения не останавливало реальный процесс движка - он продолжал
      // работать в фоне, а его лог-строки продолжали приходить в терминал
      // для job'а, у которого уже нет карточки в сайдбаре (видимая
      // путаница: активность в терминале без соответствующей карточки).
      if (runningIdRef.current === id) {
        toast("Сначала остановите декомпиляцию, потом удаляйте", "warn");
        return;
      }
      setJobs(prev => prev.filter(j => j.id !== id));
      setLog(prev => prev.filter(l => l.jobId !== id));
      setSelectedJobId(prev => (prev === id ? null : prev));
    },
    [toast],
  );

  const clearQueue = useCallback(() => {
    const removed = jobsRef.current.filter(j => j.status !== "running").length;
    setJobs(prev => prev.filter(j => j.status === "running"));
    setLog([]);
    setSelectedJobId(null);
    if (removed > 0) toast(`Список очищен: ${removed} задач`, "info");
  }, [toast]);

  const selectJob = useCallback((id: string) => {
    setSelectedJobId(id);
    const j = jobsRef.current.find(x => x.id === id);
    if (j?.status === "done" && j.files?.length) {
      setOpenFileByJob(m => (m[id] ? m : { ...m, [id]: j.files![0].id }));
    }
  }, []);

  // Содержимое файла грузится лениво по клику - move от eager-load из
  // старого демо-слоя, т.к. на крупных jar (тысячи .java) грузить всё
  // сразу через readTextFile было бы и медленно, и лишним IPC-трафиком.
  const selectFile = useCallback((jobId: string, fileId: string) => {
    setOpenFileByJob(m => ({ ...m, [jobId]: fileId }));
    const job = jobsRef.current.find(j => j.id === jobId);
    const file = job?.files?.find(f => f.id === fileId);
    if (!job || !file || file.code !== undefined) return;
    // Сбрасываем прошлую ошибку перед (пере)попыткой чтения - иначе кнопка
    // "Повторить" молча оставит старый текст ошибки, пока не придёт ответ.
    setJobs(prev =>
      prev.map(j =>
        j.id !== jobId ? j : { ...j, files: j.files?.map(f => (f.id === fileId ? { ...f, loadError: undefined } : f)) },
      ),
    );
    window.nano
      .readTextFile(job.outDir, file.relPath)
      .then(res => {
        if (!res.ok || res.content === undefined) {
          // БАГ-ФИКС: раньше здесь просто return - file.code оставался
          // undefined НАВСЕГДА, CodeView показывал "загрузка…" бесконечно,
          // неотличимо от того, что файл правда ещё грузится. Теперь явная
          // ошибка + возможность повторить.
          setJobs(prev =>
            prev.map(j =>
              j.id !== jobId
                ? j
                : { ...j, files: j.files?.map(f => (f.id === fileId ? { ...f, loadError: res.error ?? "не удалось прочитать файл" } : f)) },
            ),
          );
          return;
        }
        const loc = res.content.split("\n").length;
        setJobs(prev =>
          prev.map(j =>
            j.id !== jobId
              ? j
              : { ...j, files: j.files?.map(f => (f.id === fileId ? { ...f, code: res.content, loc } : f)) },
          ),
        );
      })
      .catch(e => {
        setJobs(prev =>
          prev.map(j =>
            j.id !== jobId
              ? j
              : { ...j, files: j.files?.map(f => (f.id === fileId ? { ...f, loadError: String(e) } : f)) },
          ),
        );
      });
  }, []);

  const clearLog = useCallback(() => setLog([]), []);

  const copyText = useCallback(
    (text: string, what: string) => {
      navigator.clipboard
        .writeText(text)
        .then(() => toast(`${what} скопировано`, "ok"))
        .catch(() => toast("Буфер обмена недоступен в этом окружении", "err"));
    },
    [toast],
  );

  const copyLog = useCallback(() => {
    if (log.length === 0) {
      toast("Лог пуст — копировать нечего", "warn");
      return;
    }
    const text = log.map(l => `[${fmtClock(l.at)}] [${l.tag}] ${l.msg}`).join("\n");
    copyText(text, `Лог (${log.length} строк)`);
  }, [copyText, log, toast]);

  const toggleTerminal = useCallback(() => setTerminalOpen(v => !v), []);

  const openOutput = useCallback((job: Job) => {
    window.nano.openPath(job.outDir).catch(() => {});
  }, []);

  const saveSettings = useCallback(
    (next: Settings) => {
      setSettings(next);
      setSettingsOpen(false);
      // БАГ-ФИКС: toast "Настройки сохранены" раньше показывался
      // БЕЗУСЛОВНО и НЕМЕДЛЕННО, даже не дожидаясь результата асинхронного
      // window.nano.setSettings() - при реальном отказе записи на диск
      // (fs.writeFileSync упал - диск полон, нет прав) пользователь видел
      // ложное "сохранено", хотя на деле настройка тихо не сохранялась и
      // откатилась бы при следующем запуске.
      window.nano
        .setSettings({ legitimacyCheck: next.legitimacyCheck, autoUpdateCheck: next.autoUpdateCheck, appIcon: next.appIcon })
        .then(r => {
          if (r.ok) toast("Настройки сохранены", "ok");
          else toast(`Не удалось сохранить настройки: ${r.error ?? "неизвестная ошибка"}`, "err");
        })
        .catch(e => toast(`Не удалось сохранить настройки: ${String(e)}`, "err"));
    },
    [toast],
  );

  // Мастер первого запуска - отдельная функция, не через saveSettings():
  // это разовое действие ("принял лицензию"), не связанное с обычным
  // диалогом настроек и его toast'ами - молча сохраняем и молча
  // проглатываем отказ (если ЭТО почему-то не сохранится, худшее, что
  // случится - мастер покажется ещё раз при следующем запуске, не
  // критично, в отличие от реальных настроек).
  const completeSetup = useCallback(() => {
    setSettings(prev => ({ ...prev, setupCompleted: true }));
    window.nano.setSettings({ setupCompleted: true }).catch(() => {});
  }, []);

  // Сообщение об успешном обновлении, если предыдущий запуск закончился
  // рестартом ради применения апдейта клиента (см.
  // update:installClientAndRestart в updater.ts - молча флагом на диске,
  // здесь просто "потребляем" его).
  useEffect(() => {
    window.nano
      .consumeUpdateSuccessFlag()
      .then(was => {
        if (was) toast("Приложение успешно обновлено", "ok");
      })
      .catch(() => {});
  }, [toast]);

  // БАГ-ФИКС: раньше молча ставил envIssue=false без единой реальной
  // проверки ("окружение проверено" было ложью) - теперь реально
  // перезапрашивает java/mvn через checkEnv().
  const resolveEnvIssue = useCallback(() => {
    checkEnv();
  }, [checkEnv]);

  // глобальные горячие клавиши
  useEffect(() => {
    const onKey = (e: KeyboardEvent) => {
      const mod = e.ctrlKey || e.metaKey;
      if (mod && e.key.toLowerCase() === "k") {
        e.preventDefault();
        setPaletteOpen(v => !v);
      } else if (mod && e.key.toLowerCase() === "o") {
        e.preventDefault();
        openFileDialog();
      } else if (mod && e.key === ",") {
        e.preventDefault();
        setSettingsOpen(true);
      } else if (mod && e.key.toLowerCase() === "l") {
        e.preventDefault();
        setLog([]);
      } else if (e.key === "Escape") {
        setPaletteOpen(false);
        setSettingsOpen(false);
        setUpdateModalOpen(false);
      }
    };
    window.addEventListener("keydown", onKey);
    return () => window.removeEventListener("keydown", onKey);
  }, [openFileDialog]);

  const runningJob = useMemo(() => jobs.find(j => j.status === "running") ?? null, [jobs]);
  const selectedJob = useMemo(() => jobs.find(j => j.id === selectedJobId) ?? null, [jobs, selectedJobId]);
  const queuedCount = useMemo(
    () => jobs.filter(j => j.status === "queued" || j.status === "canceled" || j.status === "failed").length,
    [jobs],
  );

  const api: EngineApi = {
    jobs, log, runningJob, runningElapsed, selectedJobId, selectedJob, openFileByJob,
    terminalOpen, logFilter, settings, settingsLoaded, settingsOpen, updateModalOpen, paletteOpen, envIssue, engineVersion, guiVersion, javaEnv, mavenEnv, installingTool, installProgress, iconThumbnails, updateInfo, toasts, queuedCount, sidebarWidth, fileTreeWidth, terminalHeight,
    addFiles, openFileDialog, startQueue, stopRunning, stopAll, cancelJob, removeJob, clearQueue,
    selectJob, selectFile, setLogFilter, toggleTerminal, clearLog, copyLog, copyText,
    openOutput, setSettingsOpen, setUpdateModalOpen, setSidebarWidth, setFileTreeWidth, setTerminalHeight, saveSettings, completeSetup, setPaletteOpen,
    resolveEnvIssue, checkForUpdates, applyEngineUpdate, openClientDownload, checkEnv, installTool, toast, dismissToast,
  };

  return (
    <Ctx.Provider value={api}>
      {children}
      <input
        ref={fileInputRef}
        type="file"
        accept=".jar,application/java-archive"
        multiple
        className="hidden"
        onChange={e => {
          if (e.target.files?.length) addFiles(e.target.files);
          e.target.value = "";
        }}
      />
    </Ctx.Provider>
  );
}

export function useEngine() {
  const ctx = useContext(Ctx);
  if (!ctx) throw new Error("useEngine вне EngineProvider");
  return ctx;
}
