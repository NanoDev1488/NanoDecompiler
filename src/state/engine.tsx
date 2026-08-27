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

/* Собирает плоское дерево .java-файлов из реального вывода движка через
   window.nano.listDir (рекурсивно), без чтения содержимого - код каждого
   файла подгружается лениво в selectFile() через readTextFile(). */
async function collectSourceFiles(outDir: string, relDir = ""): Promise<SourceFile[]> {
  const res = await window.nano.listDir(outDir, relDir);
  if (!res.ok || !res.items) return [];
  const out: SourceFile[] = [];
  for (const item of res.items) {
    const rel = relDir ? `${relDir}/${item.name}` : item.name;
    if (item.isDir) {
      out.push(...(await collectSourceFiles(outDir, rel)));
    } else if (/\.java$/i.test(item.name)) {
      const pkg = rel
        .replace(/^src\/main\/java\//, "")
        .replace(/\/[^/]+$/, "")
        .replace(/\//g, ".");
      out.push({ id: rid("f"), pkg: pkg || "(default)", name: item.name, relPath: rel, loc: 0 });
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
  settingsOpen: boolean;
  paletteOpen: boolean;
  envIssue: boolean;
  toasts: Toast[];
  queuedCount: number;

  addFiles(list: FileList | File[]): void;
  openFileDialog(): void;
  startQueue(): void;
  stopRunning(): void;
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
  saveSettings(next: Settings): void;
  setPaletteOpen(open: boolean): void;
  toggleEnvIssue(): void;
  resolveEnvIssue(): void;
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
};

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
  const [paletteOpen, setPaletteOpen] = useState(false);
  const [envIssue, setEnvIssue] = useState(false);
  const [toasts, setToasts] = useState<Toast[]>([]);
  const [runningElapsed, setRunningElapsed] = useState<number | null>(null);
  const [settings, setSettings] = useState<Settings>(DEFAULT_SETTINGS);

  const intervalRef = useRef<number | null>(null);
  const runningIdRef = useRef<string | null>(null);
  const startedAtRef = useRef(0);
  const logIdRef = useRef(0);
  const fileInputRef = useRef<HTMLInputElement>(null);
  const unsubscribeLogRef = useRef<(() => void) | null>(null);

  // Из main-процесса персистится ТОЛЬКО legitimacyCheck/autoUpdateCheck
  // (см. AppSettings в preload.ts) - остальные поля Settings живут только
  // в рендерере на время сессии.
  useEffect(() => {
    let cancelled = false;
    window.nano
      .getSettings()
      .then(s => {
        if (!cancelled) setSettings(prev => ({ ...prev, legitimacyCheck: s.legitimacyCheck }));
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
    if (stream === "stderr" || /error|ошибка|fail/i.test(line)) return { level: "err", tag: "stderr" };
    if (/warn|предупрежд/i.test(line)) return { level: "warn", tag: "warn" };
    if (/\bok\b|готово|done|success/i.test(line)) return { level: "ok", tag: "engine" };
    return { level: "info", tag: "engine" };
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
      // актуальных settings прямо перед запуском.
      const outDir = `${settings.outputDir}/${baseName(job.fileName)}`;
      if (outDir !== job.outDir) patchJob(jobId, { outDir });
      const runJob = { ...job, outDir };

      runningIdRef.current = jobId;
      startedAtRef.current = Date.now();
      setRunningElapsed(0);
      setSelectedJobId(jobId);
      setLog(prev => prev.filter(l => l.jobId !== jobId));

      pushLog(jobId, "info", "engine", `spawn NanoDecompilerCLI · in=${runJob.fileName} out=${runJob.outDir}`);
      patchJob(jobId, { status: "running", progress: 0.02, elapsedMs: 0 });

      intervalRef.current = window.setInterval(() => {
        const el = Date.now() - startedAtRef.current;
        setRunningElapsed(el);
        setJobs(prev => prev.map(j => (j.id === runningIdRef.current ? { ...j, elapsedMs: el } : j)));
      }, 97);

      unsubscribeLogRef.current = window.nano.onLog(e => {
        for (const l of e.lines) {
          const { level, tag } = classifyLine(l.line, l.stream);
          pushLog(jobId, level, tag, l.line);
        }
        // реального численного прогресса CLI не сообщает построчно -
        // плавно подводим индикатор к почти-концу, пока идёт вывод, чтобы
        // не показывать замёршую полоску; 1.0 выставляется в finalize().
        patchJob(jobId, { progress: Math.min(0.92, (jobsRef.current.find(j => j.id === jobId)?.progress ?? 0) + 0.015) });
      });

      try {
        const res = await window.nano.runDecompile(runJob.jarPath, runJob.outDir);
        if (res.ok) {
          await finalize(jobId, true);
        } else {
          pushLog(jobId, "err", "engine", res.error ?? `движок завершился с кодом ${res.code}`);
          await finalize(jobId, false, res.error);
        }
      } catch (e) {
        pushLog(jobId, "err", "engine", String(e));
        await finalize(jobId, false, String(e));
      }
    },
    [classifyLine, finalize, patchJob, pushLog],
  );

  const runRef = useRef(run);
  runRef.current = run;

  const startQueue = useCallback(() => {
    if (envIssue) {
      toast("Java Runtime не найдена — движку нужен JRE 17+. Проверьте окружение в настройках.", "err");
      return;
    }
    if (runningIdRef.current) return;
    const next = jobsRef.current.find(
      j => j.status === "queued" || j.status === "canceled" || j.status === "failed",
    );
    if (!next) {
      toast("Очередь пуста. Добавьте .jar слева.", "info");
      return;
    }
    runRef.current(next.id);
  }, [envIssue, toast]);

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

  // Добавление через реальный системный диалог (Electron) - единственный
  // надёжный способ получить настоящий абсолютный путь к .jar. Обычный
  // <input type=file>/drag-drop в песочнице рендерера пути не даёт (кроме
  // легаси File.path, который используем как резервный вариант ниже).
  const addJarPaths = useCallback(
    (paths: string[]) => {
      const current = jobsRef.current;
      const fresh: Job[] = [];
      for (const p of paths) {
        const fileName = p.split(/[/\\]/).pop() ?? p;
        if (
          current.some(j => j.jarPath === p && j.status !== "done") ||
          fresh.some(j => j.jarPath === p)
        ) {
          toast(`Уже в очереди: ${fileName}`, "warn");
          continue;
        }
        fresh.push({
          id: rid("job"),
          fileName,
          jarPath: p,
          outDir: `${settings.outputDir}/${baseName(fileName)}`,
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
              if (s.error) return;
              const bytes = Number(s.size.replace(/[^\d]/g, "")) || 0;
              patchJob(j.id, { classCount: s.classes, sizeBytes: bytes || j.sizeBytes });
            })
            .catch(() => {});
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
      .then(p => {
        if (p) addJarPaths([p]);
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

  const removeJob = useCallback((id: string) => {
    setJobs(prev => prev.filter(j => j.id !== id));
    setLog(prev => prev.filter(l => l.jobId !== id));
    setSelectedJobId(prev => (prev === id ? null : prev));
  }, []);

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
    window.nano
      .readTextFile(job.outDir, file.relPath)
      .then(res => {
        if (!res.ok || res.content === undefined) return;
        const loc = res.content.split("\n").length;
        setJobs(prev =>
          prev.map(j =>
            j.id !== jobId
              ? j
              : { ...j, files: j.files?.map(f => (f.id === fileId ? { ...f, code: res.content, loc } : f)) },
          ),
        );
      })
      .catch(() => {});
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
      window.nano.setSettings({ legitimacyCheck: next.legitimacyCheck }).catch(() => {});
      toast("Настройки сохранены", "ok");
    },
    [toast],
  );

  const toggleEnvIssue = useCallback(() => {
    setEnvIssue(v => !v);
  }, []);

  const resolveEnvIssue = useCallback(() => {
    setEnvIssue(false);
    toast("Окружение проверено", "ok");
  }, [toast]);

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
    terminalOpen, logFilter, settings, settingsOpen, paletteOpen, envIssue, toasts, queuedCount,
    addFiles, openFileDialog, startQueue, stopRunning, cancelJob, removeJob, clearQueue,
    selectJob, selectFile, setLogFilter, toggleTerminal, clearLog, copyLog, copyText,
    openOutput, setSettingsOpen, saveSettings, setPaletteOpen,
    toggleEnvIssue, resolveEnvIssue, toast, dismissToast,
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
