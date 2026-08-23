import React, { useCallback, useEffect, useRef, useState } from "react";
import { classifyLine } from "./classifyLine";
import MiniIde from "./MiniIde";

type Status = "idle" | "running" | "ok" | "error";
type InstallState = "idle" | "installing" | "done";
type UpdateState = "idle" | "checking" | "up-to-date" | "available" | "applying" | "applied" | "error";
type UpdateKind = "none" | "engine" | "client";

interface LogLine {
  text: string;
  kind: ReturnType<typeof classifyLine>;
  /** Строка "[*] Не хватает: java, maven. ..." - к ней цепляем инлайн-кнопки
   * "Установить?" прямо на месте, а не отдельным попапом. */
  missingTools?: string[];
}

interface JarSummary {
  name: string;
  size: string;
  classes: number;
  packages: number;
  java: string;
  plugin_name: string | null;
  error?: string;
}

/** "[*] Не хватает: java, maven. Без этого..." -> ["java", "maven"] */
function parseMissingTools(line: string): string[] | null {
  const m = line.match(/^\[\*\]\s*Не хватает:\s*([a-z, ]+?)\.\s/i);
  if (!m) return null;
  return m[1].split(",").map((s) => s.trim()).filter(Boolean);
}

function formatBytes(n: number): string {
  if (n < 1024) return `${n} Б`;
  if (n < 1024 * 1024) return `${(n / 1024).toFixed(0)} КБ`;
  return `${(n / 1024 / 1024).toFixed(1)} МБ`;
}

function formatSpeed(bps: number): string {
  if (bps <= 0) return "…";
  return `${formatBytes(bps)}/с`;
}

function formatEta(sec: number | null): string {
  if (sec === null || !isFinite(sec) || sec < 0) return "…";
  if (sec < 60) return `${Math.ceil(sec)} с`;
  const m = Math.floor(sec / 60);
  const s = Math.ceil(sec % 60);
  return `${m} мин ${s} с`;
}

/** Material Symbols (Google, Apache License 2.0) - несколько самых
 * используемых иконок вшиты прямо как SVG-path, без внешнего шрифта/CDN
 * (в песочнице сборки нет сети на npm-реестр - см. HANDOFF_22). */
function Icon({ name, size = 16 }: { name: "folder_open" | "code" | "check_circle" | "error" | "refresh" | "cloud_download"; size?: number }) {
  const paths: Record<string, string> = {
    folder_open:
      "M20 6h-8l-2-2H4c-1.1 0-2 .9-2 2v12c0 1.1.9 2 2 2h16c1.1 0 2-.9 2-2V8c0-1.1-.9-2-2-2zm0 12H4V8h16v10z",
    code: "M9.4 16.6 4.8 12l4.6-4.6L8 6l-6 6 6 6zm5.2 0L19.2 12l-4.6-4.6L16 6l6 6-6 6z",
    check_circle:
      "M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-2 15-5-5 1.41-1.41L10 14.17l7.59-7.59L19 8z",
    error:
      "M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm1 15h-2v-2h2zm0-4h-2V7h2z",
    refresh:
      "M17.65 6.35A7.958 7.958 0 0 0 12 4c-4.42 0-7.99 3.58-7.99 8s3.57 8 7.99 8c3.73 0 6.84-2.55 7.73-6h-2.08a5.99 5.99 0 0 1-5.65 4c-3.31 0-6-2.69-6-6s2.69-6 6-6c1.66 0 3.14.69 4.22 1.78L14 11h7V4z",
    cloud_download:
      "M19.35 10.04A7.49 7.49 0 0 0 12 4a7.49 7.49 0 0 0-7.35 6.04A5.994 5.994 0 0 0 0 16c0 3.31 2.69 6 6 6h13c2.76 0 5-2.24 5-5 0-2.64-2.05-4.78-4.65-4.96zM17 13l-5 5-5-5h3V9h4v4z",
  };
  return (
    <svg viewBox="0 0 24 24" width={size} height={size} fill="currentColor" style={{ flexShrink: 0 }}>
      <path d={paths[name]} />
    </svg>
  );
}

export default function App() {
  const [jarPath, setJarPath] = useState<string | null>(null);
  const [outDir, setOutDir] = useState<string | null>(null);
  // Разобранная строка "Методов декомпилировано: X/Y (Z%)" из лога движка
  // (см. cli_main.cpp::run_decompile_console) - показывается крупной плашкой
  // результата, а не только в самом логе (раньше результат было легко
  // пропустить - см. HANDOFF_22).
  const [resultStats, setResultStats] = useState<{ done: number; total: number; pct: number } | null>(null);
  const [dragOver, setDragOver] = useState(false);
  const [status, setStatus] = useState<Status>("idle");
  const [lines, setLines] = useState<LogLine[]>([]);
  const [summary, setSummary] = useState<JarSummary | null>(null);
  const [installState, setInstallState] = useState<InstallState>("idle");
  const [installProgress, setInstallProgress] = useState<string | null>(null);
  const [dismissedInstallPrompt, setDismissedInstallPrompt] = useState(false);
  // HANDOFF_52: мини-IDE (файловый проводник + просмотр кода, см.
  // src/MiniIde.tsx) - переключатель "Лог / Мини-IDE" рядом с терминалом,
  // доступен только после успешной декомпиляции (нужен outDir).
  const [showIde, setShowIde] = useState(false);
  const [updateState, setUpdateState] = useState<UpdateState>("idle");
  const [updateKind, setUpdateKind] = useState<UpdateKind>("none");
  const [updateInfo, setUpdateInfo] = useState<{
    currentVersion?: string;
    latestVersion?: string;
    latestVersionKind?: "release" | "prerelease";
    downloadUrl?: string | null;
    clientDownloadUrl?: string | null;
    error?: string;
  } | null>(null);
  const termRef = useRef<HTMLDivElement>(null);
  const [installingClient, setInstallingClient] = useState(false);

  // Настройки (см. HANDOFF_19 - экран настроек, раньше не было вообще).
  // null пока не загружены (первый рендер, до ответа main-процесса) -
  // отличаем от "уже загружены и оба тумблера включены" (дефолт).
  const [settings, setSettingsState] = useState<{
    legitimacyCheck: boolean;
    autoUpdateCheck: boolean;
  } | null>(null);
  const [settingsOpen, setSettingsOpen] = useState(false);

  useEffect(() => {
    let cancelled = false;
    (async () => {
      const s = await window.nano.getSettings();
      if (!cancelled) setSettingsState(s);
    })();
    return () => {
      cancelled = true;
    };
  }, []);

  const updateSetting = useCallback(async (partial: Partial<{ legitimacyCheck: boolean; autoUpdateCheck: boolean }>) => {
    const merged = await window.nano.setSettings(partial);
    setSettingsState(merged);
  }, []);

  // Проверка обновлений - см. HANDOFF_16/19. Два РАЗНЫХ типа обновления
  // (см. electron/updater.ts):
  //  - "engine" - поменялся только движок (.exe) - можно тихо подменить
  //    файл на лету, обычное "Обновить".
  //  - "client" - поменялся сам клиент (GUI/Electron-обвязка) - его
  //    ТАК просто одним файлом не заменить (Windows не даёт процессу
  //    перезаписать свой же запущенный .exe) - нужен новый инсталлятор,
  //    показываем это как ВАЖНОЕ обновление со ссылкой на скачивание.
  //
  // ПОКА ПРИЛОЖЕНИЕ ОТКРЫТО - проверяем раз в 60 сек (не один раз при
  // старте), таймаут на саму сетевую проверку - 5 сек (см.
  // electron/updater.ts::CHECK_TIMEOUT_MS). После УСПЕШНОГО применения
  // апдейта движка - следующие 5 минут проверки ПРОПУСКАЕМ (см.
  // suppressChecksUntilRef ниже) - иначе пользователь видел
  // "не удалось проверить обновления" сразу после обновления (см. отчёт
  // пользователя) - похоже на гонку/временную недоступность сразу после
  // сетевой активности апдейтера, пауза даёт этому "отлежаться".
  const CHECK_INTERVAL_MS = 60_000;
  const POST_UPDATE_SUPPRESS_MS = 5 * 60_000;
  const suppressChecksUntilRef = useRef(0);

  const runUpdateCheck = useCallback(async () => {
    if (Date.now() < suppressChecksUntilRef.current) return;
    setUpdateState("checking");
    const res = await window.nano.checkUpdate();
    if (Date.now() < suppressChecksUntilRef.current) return; // могли применить апдейт, пока ждали ответ
    if (!res.ok) {
      setUpdateState("error");
      setUpdateInfo({ error: res.error });
      return;
    }
    setUpdateInfo({
      currentVersion: res.currentVersion,
      latestVersion: res.latestVersion,
      latestVersionKind: res.latestVersionKind,
      downloadUrl: res.downloadUrl,
      clientDownloadUrl: res.clientDownloadUrl,
    });
    setUpdateKind(res.updateKind ?? "none");
    setUpdateState(res.updateKind && res.updateKind !== "none" ? "available" : "up-to-date");
  }, []);

  useEffect(() => {
    // См. HANDOFF_19 - тумблер "автопроверка обновлений". Ждём, пока
    // настройки реально загрузятся (settings === null на первом рендере) -
    // не проверяем "на всякий случай" до этого, чтобы не мигнуть проверкой
    // один раз, если пользователь успел выключить тумблер раньше.
    if (settings === null) return;
    if (!settings.autoUpdateCheck) return;
    // См. HANDOFF_22 - раньше проверка обновлений продолжала идти по
    // таймеру ДАЖЕ пока сам апдейт уже качается/применяется - бессмысленно
    // (мы и так знаем, что обновление есть, мы его уже качаем) и лишняя
    // сетевая/UI-активность прямо во время скачивания.
    if (updateState === "applying" || installingClient) return;
    runUpdateCheck();
    const id = setInterval(runUpdateCheck, CHECK_INTERVAL_MS);
    return () => clearInterval(id);
  }, [runUpdateCheck, settings, updateState, installingClient]);

  // Детальный прогресс скачивания (размер/скорость/осталось) - см.
  // HANDOFF_22: раньше во время скачивания клиент/движка ничего этого не
  // показывал вообще, просто "Скачиваю...". Скорость считаем сами по
  // дельте между последовательными событиями прогресса (electron/updater.ts
  // шлёт их не чаще раза в ~120мс - см. его комментарий про троттлинг).
  const [downloadProgress, setDownloadProgress] = useState<{
    downloaded: number;
    total: number | null;
    kind: "client" | "engine";
    speedBps: number;
    etaSec: number | null;
  } | null>(null);
  const downloadSampleRef = useRef<{ t: number; bytes: number } | null>(null);
  const [downloadDetailOpen, setDownloadDetailOpen] = useState(false);

  useEffect(() => {
    const off = window.nano.onDownloadProgress(({ downloaded, total, kind }) => {
      const now = Date.now();
      const prev = downloadSampleRef.current;
      let speedBps = 0;
      if (prev && now > prev.t) {
        speedBps = ((downloaded - prev.bytes) / (now - prev.t)) * 1000;
      }
      downloadSampleRef.current = { t: now, bytes: downloaded };
      const etaSec = total && speedBps > 1024 ? (total - downloaded) / speedBps : null;
      setDownloadProgress({ downloaded, total, kind, speedBps, etaSec });
    });
    return off;
  }, []);

  const applyUpdate = useCallback(async () => {
    if (!updateInfo?.downloadUrl || status === "running") return;
    setUpdateState("applying");
    downloadSampleRef.current = null;
    setDownloadProgress(null);
    const res = await window.nano.applyUpdate(updateInfo.downloadUrl, updateInfo.latestVersion);
    setDownloadProgress(null);
    if (res.ok) {
      suppressChecksUntilRef.current = Date.now() + POST_UPDATE_SUPPRESS_MS;
      setUpdateState("applied");
    } else {
      setUpdateState("error");
      setUpdateInfo((prev) => ({ ...(prev ?? {}), error: res.error }));
    }
  }, [updateInfo, status]);

  const [toast, setToast] = useState<string | null>(null);

  const installClientUpdate = useCallback(async () => {
    if (!updateInfo?.clientDownloadUrl || installingClient) return;
    setInstallingClient(true);
    downloadSampleRef.current = null;
    setDownloadProgress(null);
    const res = await window.nano.installClientAndRestart(updateInfo.clientDownloadUrl);
    if (!res.ok) {
      setInstallingClient(false);
      setDownloadProgress(null);
      setUpdateState("error");
      setUpdateInfo((prev) => ({ ...(prev ?? {}), error: res.error }));
      return;
    }
    if (res.manual) {
      // HANDOFF_51: Linux/macOS - приложение НЕ закрывается само (см.
      // electron/updater.ts::installClientAndRestart) - открыли страницу
      // скачивания в браузере, дальше пользователь обновляется вручную.
      setInstallingClient(false);
      setDownloadProgress(null);
      setToast("Страница скачивания открыта в браузере - установите новую версию вручную и перезапустите приложение.");
      return;
    }
    // иначе (Windows) - при успехе приложение само закроется через
    // electron/updater.ts - никакого дальнейшего состояния тут показывать
    // уже не успеем.
  }, [updateInfo, installingClient]);

  // Плашка "Обновление успешно установлено" - см. HANDOFF_16. Показывается
  // один раз при первом запуске ПОСЛЕ того, как предыдущая версия сама
  // запустила новый инсталлятор и закрылась (маркер-файл/--add-update-success,
  // см. electron/updater.ts::consumeUpdateSuccessFlag) - само временное
  // исчезновение плашки через TOAST_MS реализовано ниже.
  const TOAST_MS = 6000;
  useEffect(() => {
    let cancelled = false;
    (async () => {
      const shouldShow = await window.nano.consumeUpdateSuccessFlag();
      if (!cancelled && shouldShow) {
        setToast("Обновление успешно установлено");
      }
    })();
    return () => {
      cancelled = true;
    };
  }, []);
  useEffect(() => {
    if (!toast) return;
    const t = setTimeout(() => setToast(null), TOAST_MS);
    return () => clearTimeout(t);
  }, [toast]);

  // Верхний предел строк лога в памяти/DOM - на jar в тысячи методов лог
  // мог разрастись до многих тысяч строк без предела, что само по себе
  // тормозило рендер (терминал без виртуализации). Обрезаем самые старые,
  // отчёт (README_RU.txt/JSON) всё равно содержит полную статистику -
  // в живом логе важен ХВОСТ (последние события), не самое начало.
  const MAX_LOG_LINES = 4000;

  useEffect(() => {
    const off = window.nano.onLog(({ lines: batch }) => {
      const parsedNew: LogLine[] = [];
      let newStats: { done: number; total: number; pct: number } | null = null;
      for (const { line } of batch) {
        const missing = parseMissingTools(line);
        parsedNew.push({ text: line, kind: classifyLine(line), missingTools: missing ?? undefined });
        const statsMatch = line.match(/Методов декомпилировано:\s*(\d+)\/(\d+)\s*\(([\d.]+)%\)/);
        if (statsMatch) {
          newStats = { done: Number(statsMatch[1]), total: Number(statsMatch[2]), pct: Number(statsMatch[3]) };
        }
      }
      // Один setLines на весь батч (не по одному на строку) - см. выше про
      // причину лагов на крупных jar.
      setLines((prev) => {
        const merged = prev.length + parsedNew.length > MAX_LOG_LINES ? prev.slice(-(MAX_LOG_LINES - parsedNew.length)) : prev;
        return [...merged, ...parsedNew];
      });
      if (newStats) setResultStats(newStats);
    });
    return off;
  }, []);

  useEffect(() => {
    const off = window.nano.onToolsProgress((e) => {
      setInstallProgress(
        e.pct != null ? `${e.label}: ${e.pct}% (${e.downloaded_mb} МБ / ${e.total_mb} МБ)` : `${e.label}: ${e.downloaded_mb} МБ`
      );
    });
    return off;
  }, []);

  useEffect(() => {
    termRef.current?.scrollTo({ top: termRef.current.scrollHeight });
  }, [lines]);

  const defaultOutFor = (jar: string) => {
    const base = jar.replace(/\\/g, "/").split("/").pop() ?? "output";
    const stem = base.replace(/\.jar$/i, "");
    return stem + "_decompiled";
  };

  const defaultOutPathFor = (jar: string) =>
    jar.includes("/") || jar.includes("\\")
      ? jar.replace(/[\\/][^\\/]+$/, "/" + defaultOutFor(jar))
      : defaultOutFor(jar);

  const selectJarFile = useCallback(async (jar: string) => {
    setJarPath(jar);
    setOutDir(null);
    setSummary(null);
    setDismissedInstallPrompt(false);
    const s = await window.nano.jarSummary(jar);
    setSummary(s);
  }, []);

  const pickJar = useCallback(async () => {
    const p = await window.nano.selectJar();
    if (p) await selectJarFile(p);
  }, [selectJarFile]);

  const pickOutDir = useCallback(async () => {
    // Подставляем предполагаемое имя папки (на основе jar'а) в диалог -
    // иначе пустой диалог + случайный клик "Новая папка" в Проводнике даёт
    // папку буквально с таким названием (реальный баг, найденный на тесте).
    const suggested = jarPath ? defaultOutPathFor(jarPath) : undefined;
    const p = await window.nano.selectOutDir(suggested);
    if (p) setOutDir(p);
  }, [jarPath]);

  const onDrop = useCallback(
    (e: React.DragEvent) => {
      e.preventDefault();
      setDragOver(false);
      const f = e.dataTransfer.files[0];
      if (f && f.name.toLowerCase().endsWith(".jar")) {
        void selectJarFile((f as any).path ?? f.name);
      }
    },
    [selectJarFile]
  );

  const run = useCallback(async () => {
    if (!jarPath) return;
    const resolvedOut = outDir ?? defaultOutPathFor(jarPath);

    setLines([]);
    setResultStats(null);
    setStatus("running");
    setShowIde(false);
    const res = await window.nano.runDecompile(jarPath, resolvedOut);
    setStatus(res.ok ? "ok" : "error");
    if (!res.ok && res.error) {
      setLines((prev) => [...prev, { text: "[!] " + res.error, kind: "error" }]);
    }
    if (res.outDir) setOutDir(res.outDir);
  }, [jarPath, outDir]);

  const cancel = useCallback(async () => {
    await window.nano.cancel();
    setStatus("idle");
  }, []);

  const installTools = useCallback(async (tools: string[]) => {
    setInstallState("installing");
    setInstallProgress(null);
    // cli_main.cpp::run_install_tools_json принимает "jdk"/"java"/"maven" -
    // тут ровно те же токены (см. toolinstaller.hpp), конвертировать не нужно.
    const only = tools.length === 1 ? (tools[0] as "java" | "maven") : undefined;
    const res = await window.nano.installTools(only as any);
    setInstallState("done");
    setInstallProgress(null);
    const okLine = res.ok
      ? `[+] Установка завершена. java: ${res.java ?? "не ставилась/не нужна"}, maven: ${res.maven ?? "не ставился/не нужен"}`
      : `[!] Установка не удалась: ${res.error ?? (res.errors ?? []).join("; ")}`;
    setLines((prev) => [...prev, { text: okLine, kind: res.ok ? "ok" : "error" }]);
  }, []);

  const dotFor: Record<Status, string> = { idle: "◯", running: "●", ok: "●", error: "●" };
  const statusLabel: Record<Status, string> = {
    idle: "ожидание",
    running: "декомпиляция...",
    ok: "готово",
    error: "ошибка",
  };

  return (
    <div className="app">
      <div className="topbar">
        <span className="brand">NanoDecompiler</span>
        <span className="brand-version">{updateInfo?.currentVersion ?? ""}</span>
        <span
          className={"update-badge" + (downloadProgress ? " is-clickable" : "")}
          onClick={() => {
            if (downloadProgress) setDownloadDetailOpen((v) => !v);
          }}
        >
          {updateState === "checking" && <span>проверка обновлений...</span>}
          {updateState === "up-to-date" && <span>всё актуально</span>}
          {updateState === "idle" && settings && !settings.autoUpdateCheck && (
            <button className="btn-mini btn-mini-no" onClick={runUpdateCheck}>
              Проверить обновления
            </button>
          )}
          {updateState === "available" && updateKind === "engine" && (
            <>
              <span>
                доступно обновление движка{updateInfo?.latestVersion ? ` ${updateInfo.latestVersion}` : ""}
                {updateInfo?.latestVersionKind && (
                  <span
                    className={"version-kind version-kind-" + updateInfo.latestVersionKind}
                    title={
                      updateInfo.latestVersionKind === "prerelease"
                        ? "Тестовая (бета) версия — возможны баги, обновляйтесь осторожно"
                        : "Финальная релизная версия — можно спокойно пользоваться"
                    }
                  >
                    {updateInfo.latestVersionKind === "prerelease" ? "бета" : "релиз"}
                  </span>
                )}
              </span>
              <button className="btn-mini btn-mini-yes" onClick={applyUpdate} disabled={status === "running"}>
                Обновить
              </button>
            </>
          )}
          {updateState === "available" && updateKind === "client" && (
            <>
              <span className="update-badge-important">
                ⚠ важное обновление{updateInfo?.latestVersion ? ` ${updateInfo.latestVersion}` : ""} - нужен новый инсталлятор
                {updateInfo?.latestVersionKind && (
                  <span
                    className={"version-kind version-kind-" + updateInfo.latestVersionKind}
                    title={
                      updateInfo.latestVersionKind === "prerelease"
                        ? "Тестовая (бета) версия — возможны баги, обновляйтесь осторожно"
                        : "Финальная релизная версия — можно спокойно пользоваться"
                    }
                  >
                    {updateInfo.latestVersionKind === "prerelease" ? "бета" : "релиз"}
                  </span>
                )}
              </span>
              <button className="btn-mini btn-mini-yes" onClick={installClientUpdate} disabled={installingClient}>
                {installingClient ? "Скачиваю..." : "Скачать и установить"}
              </button>
            </>
          )}
          {(updateState === "applying" || installingClient) && (
            <span className="download-progress-inline">
              <span className="dot running">●</span>
              {downloadProgress ? (
                <>
                  <span>
                    {formatBytes(downloadProgress.downloaded)}
                    {downloadProgress.total ? ` / ${formatBytes(downloadProgress.total)}` : ""}
                  </span>
                  <span className="download-progress-sep">·</span>
                  <span>{formatSpeed(downloadProgress.speedBps)}</span>
                  {downloadProgress.etaSec !== null && (
                    <>
                      <span className="download-progress-sep">·</span>
                      <span>осталось {formatEta(downloadProgress.etaSec)}</span>
                    </>
                  )}
                  <span className="download-progress-hint">клик — подробнее</span>
                </>
              ) : (
                <span>скачиваю...</span>
              )}
            </span>
          )}
          {updateState === "applied" && <span>обновлено — перезапустите приложение</span>}
          {updateState === "error" && <span title={updateInfo?.error}>проверка обновлений не удалась</span>}
        </span>
        <button
          className="settings-gear"
          title="Настройки"
          aria-label="Настройки"
          onClick={() => setSettingsOpen(true)}
        >
          <svg viewBox="0 0 24 24" width="16" height="16" fill="none" stroke="currentColor" strokeWidth="2">
            <circle cx="12" cy="12" r="3" />
            <path d="M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 1 1-2.83 2.83l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-4 0v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 1 1-2.83-2.83l.06-.06a1.65 1.65 0 0 0 .33-1.82 1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1 0-4h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 1 1 2.83-2.83l.06.06a1.65 1.65 0 0 0 1.82.33H9a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 4 0v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 1 1 2.83 2.83l-.06.06a1.65 1.65 0 0 0-.33 1.82V9a1.65 1.65 0 0 0 1.51 1H21a2 2 0 0 1 0 4h-.09a1.65 1.65 0 0 0-1.51 1z" />
          </svg>
        </button>
      </div>

      {downloadDetailOpen && downloadProgress && (
        <div className="modal-overlay" onClick={() => setDownloadDetailOpen(false)}>
          <div className="modal-panel" onClick={(e) => e.stopPropagation()}>
            <div className="modal-header">
              <span>{downloadProgress.kind === "client" ? "Скачивание клиента" : "Скачивание движка"}</span>
              <button className="modal-close" onClick={() => setDownloadDetailOpen(false)} aria-label="Закрыть">
                ✕
              </button>
            </div>
            <div className="download-detail-bar">
              <div
                className="download-detail-bar-fill"
                style={{
                  width: downloadProgress.total
                    ? `${Math.min(100, (downloadProgress.downloaded / downloadProgress.total) * 100)}%`
                    : "100%",
                }}
              />
            </div>
            <div className="download-detail-grid">
              <div>
                <div className="settings-row-title">Скачано</div>
                <div className="settings-row-hint">
                  {formatBytes(downloadProgress.downloaded)}
                  {downloadProgress.total ? ` из ${formatBytes(downloadProgress.total)}` : " (размер неизвестен)"}
                  {downloadProgress.total
                    ? ` (${Math.min(100, (downloadProgress.downloaded / downloadProgress.total) * 100).toFixed(0)}%)`
                    : ""}
                </div>
              </div>
              <div>
                <div className="settings-row-title">Скорость</div>
                <div className="settings-row-hint">{formatSpeed(downloadProgress.speedBps)}</div>
              </div>
              <div>
                <div className="settings-row-title">Осталось времени</div>
                <div className="settings-row-hint">{formatEta(downloadProgress.etaSec)}</div>
              </div>
            </div>
          </div>
        </div>
      )}

      {settingsOpen && settings && (
        <div className="modal-overlay" onClick={() => setSettingsOpen(false)}>
          <div className="modal-panel" onClick={(e) => e.stopPropagation()}>
            <div className="modal-header">
              <span>Настройки</span>
              <button className="modal-close" onClick={() => setSettingsOpen(false)} aria-label="Закрыть">
                ✕
              </button>
            </div>
            <label className="settings-row">
              <div>
                <div className="settings-row-title">Проверка легитимности</div>
                <div className="settings-row-hint">
                  Сверять плагин с GitHub/Modrinth/SpigotMC/RuSpigot при декомпиляции
                </div>
              </div>
              <input
                type="checkbox"
                checked={settings.legitimacyCheck}
                onChange={(e) => updateSetting({ legitimacyCheck: e.target.checked })}
              />
            </label>
            <label className="settings-row">
              <div>
                <div className="settings-row-title">Автопроверка обновлений</div>
                <div className="settings-row-hint">
                  Проверять новую версию движка/клиента в фоне, пока приложение открыто
                </div>
              </div>
              <input
                type="checkbox"
                checked={settings.autoUpdateCheck}
                onChange={(e) => updateSetting({ autoUpdateCheck: e.target.checked })}
              />
            </label>
          </div>
        </div>
      )}

      <div className="main">
        <div className="panel">
          <div>
            <span className="field-label">Плагин (.jar)</span>
            <div
              className={"dropzone" + (jarPath ? " has-file" : "") + (dragOver ? " drag-over" : "")}
              onClick={pickJar}
              onDragOver={(e) => {
                e.preventDefault();
                setDragOver(true);
              }}
              onDragLeave={() => setDragOver(false)}
              onDrop={onDrop}
            >
              {jarPath ?? "нажми или перетащи .jar сюда"}
            </div>
          </div>

          {summary && !summary.error && (
            <div className="jar-card">
              <div className="jar-card-title">{summary.plugin_name ?? summary.name}</div>
              <div className="jar-card-grid">
                <div>
                  <span className="jar-card-label">Размер</span>
                  <span className="jar-card-value">{summary.size}</span>
                </div>
                <div>
                  <span className="jar-card-label">Java</span>
                  <span className="jar-card-value">{summary.java}</span>
                </div>
                <div>
                  <span className="jar-card-label">Классы</span>
                  <span className="jar-card-value">{summary.classes}</span>
                </div>
                <div>
                  <span className="jar-card-label">Пакеты</span>
                  <span className="jar-card-value">{summary.packages}</span>
                </div>
              </div>
            </div>
          )}

          <div>
            <span className="field-label">Папка результата</span>
            <div className="path-row">
              <button className="btn" onClick={pickOutDir}>
                Выбрать...
              </button>
              <span style={{ color: "var(--on-surface-subtle)", fontSize: 11, wordBreak: "break-all" }}>
                {outDir ?? (jarPath ? defaultOutPathFor(jarPath) : "(по умолчанию рядом с jar)")}
              </span>
            </div>
          </div>

          {status === "running" ? (
            <button className="btn-cta" onClick={cancel}>
              ОСТАНОВИТЬ
            </button>
          ) : (
            <button className="btn-cta" onClick={run} disabled={!jarPath}>
              ДЕКОМПИЛИРОВАТЬ
            </button>
          )}

          {(status === "idle" || status === "running") && (
            <div className="status-row">
              <span className={"dot " + status}>{dotFor[status]}</span>
              <span>{statusLabel[status]}</span>
            </div>
          )}

          {status === "ok" && (
            <div className="result-card result-card-ok">
              <div className="result-card-headline">
                <span className="result-card-icon">✓</span>
                <span>Готово{resultStats ? ` — ${resultStats.pct.toFixed(1)}%` : ""}</span>
              </div>
              {resultStats && (
                <div className="result-card-detail">
                  {resultStats.done} из {resultStats.total} методов полностью восстановлено в читаемый Java
                </div>
              )}
              {outDir && (
                <div className="path-row">
                  <button className="btn" onClick={() => window.nano.openPath(outDir)}>
                    Открыть папку
                  </button>
                  <button
                    className="btn"
                    onClick={async () => {
                      const r = await window.nano.openInVSCode(outDir);
                      if (!r.ok && r.error) {
                        setLines((prev) => [...prev, { text: "[!] " + r.error, kind: "error" }]);
                      }
                    }}
                  >
                    Открыть в VS Code
                  </button>
                  <button className="btn" onClick={() => setShowIde((v) => !v)}>
                    {showIde ? "Показать лог" : "Открыть мини-IDE"}
                  </button>
                </div>
              )}
            </div>
          )}

          {status === "error" && (
            <div className="result-card result-card-error">
              <div className="result-card-headline">
                <span className="result-card-icon">✕</span>
                <span>Не получилось</span>
              </div>
              <div className="result-card-detail">Подробности — в логе ниже</div>
            </div>
          )}
        </div>

        {showIde && outDir ? (
          <MiniIde
            root={outDir}
            onOpenExternal={async (relPath) => {
              const full = outDir.replace(/[\\/]+$/, "") + "/" + relPath;
              const r = await window.nano.openInVSCode(full);
              if (!r.ok && r.error) await window.nano.openPath(full);
            }}
          />
        ) : (
        <div className="terminal" ref={termRef}>
          {lines.length === 0 ? (
            <div className="empty-terminal">Лог появится здесь после запуска.</div>
          ) : (
            lines.map((l, i) => (
              <div key={i} className={"line-" + l.kind}>
                {l.text}
                {l.missingTools && !dismissedInstallPrompt && installState === "idle" && (
                  <span className="install-prompt">
                    <span>Установить недостающее?</span>
                    <button
                      className="btn-mini btn-mini-yes"
                      onClick={() => installTools(l.missingTools!)}
                    >
                      Да
                    </button>
                    <button
                      className="btn-mini btn-mini-no"
                      onClick={() => setDismissedInstallPrompt(true)}
                    >
                      Нет
                    </button>
                  </span>
                )}
                {l.missingTools && installState === "installing" && (
                  <span className="install-prompt">
                    <span className="dot running">●</span>
                    <span>{installProgress ?? "Устанавливаю..."}</span>
                  </span>
                )}
              </div>
            ))
          )}
        </div>
        )}
      </div>

      <div className="footer">
        <a
          className="bug-report-link"
          href="#"
          onClick={(e) => {
            e.preventDefault();
            window.nano.openExternal("https://t.me/ERROR_92");
          }}
        >
          Нашёл баг? - t.me/ERROR_92
        </a>
        <span>{jarPath ? jarPath : "—"}</span>
      </div>
      {toast && (
        <div className="toast">
          <span>{toast}</span>
          <div className="toast-expiry">
            <div className="toast-expiry-bar" style={{ animationDuration: `${TOAST_MS}ms` }} />
          </div>
        </div>
      )}
    </div>
  );
}
