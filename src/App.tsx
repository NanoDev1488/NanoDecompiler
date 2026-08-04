import React, { useCallback, useEffect, useRef, useState } from "react";
import { classifyLine } from "./classifyLine";

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

export default function App() {
  const [jarPath, setJarPath] = useState<string | null>(null);
  const [outDir, setOutDir] = useState<string | null>(null);
  const [dragOver, setDragOver] = useState(false);
  const [status, setStatus] = useState<Status>("idle");
  const [lines, setLines] = useState<LogLine[]>([]);
  const [summary, setSummary] = useState<JarSummary | null>(null);
  const [installState, setInstallState] = useState<InstallState>("idle");
  const [installProgress, setInstallProgress] = useState<string | null>(null);
  const [dismissedInstallPrompt, setDismissedInstallPrompt] = useState(false);
  const [updateState, setUpdateState] = useState<UpdateState>("idle");
  const [updateKind, setUpdateKind] = useState<UpdateKind>("none");
  const [updateInfo, setUpdateInfo] = useState<{
    currentVersion?: string;
    latestVersion?: string;
    downloadUrl?: string | null;
    clientDownloadUrl?: string | null;
    error?: string;
  } | null>(null);
  const termRef = useRef<HTMLDivElement>(null);

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
    runUpdateCheck();
    const id = setInterval(runUpdateCheck, CHECK_INTERVAL_MS);
    return () => clearInterval(id);
  }, [runUpdateCheck, settings]);

  const applyUpdate = useCallback(async () => {
    if (!updateInfo?.downloadUrl || status === "running") return;
    setUpdateState("applying");
    const res = await window.nano.applyUpdate(updateInfo.downloadUrl, updateInfo.latestVersion);
    if (res.ok) {
      suppressChecksUntilRef.current = Date.now() + POST_UPDATE_SUPPRESS_MS;
      setUpdateState("applied");
    } else {
      setUpdateState("error");
      setUpdateInfo((prev) => ({ ...(prev ?? {}), error: res.error }));
    }
  }, [updateInfo, status]);

  const [installingClient, setInstallingClient] = useState(false);
  const [toast, setToast] = useState<string | null>(null);

  const installClientUpdate = useCallback(async () => {
    if (!updateInfo?.clientDownloadUrl || installingClient) return;
    setInstallingClient(true);
    const res = await window.nano.installClientAndRestart(updateInfo.clientDownloadUrl);
    if (!res.ok) {
      setInstallingClient(false);
      setUpdateState("error");
      setUpdateInfo((prev) => ({ ...(prev ?? {}), error: res.error }));
    }
    // при успехе приложение само закроется через electron/updater.ts -
    // никакого дальнейшего состояния тут показывать уже не успеем.
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

  useEffect(() => {
    const off = window.nano.onLog(({ line }) => {
      const missing = parseMissingTools(line);
      setLines((prev) => [...prev, { text: line, kind: classifyLine(line), missingTools: missing ?? undefined }]);
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
    setStatus("running");
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
    // main.py::_try_handle_install_tools_json принимает "jdk"/"java"/"maven" -
    // тут ровно те же токены, что печатает check_java_maven() в "Не хватает: ...",
    // конвертировать не нужно.
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
        <span className="update-badge">
          {updateState === "checking" && <span>проверка обновлений...</span>}
          {updateState === "up-to-date" && <span>всё актуально</span>}
          {updateState === "idle" && settings && !settings.autoUpdateCheck && (
            <button className="btn-mini btn-mini-no" onClick={runUpdateCheck}>
              Проверить обновления
            </button>
          )}
          {updateState === "available" && updateKind === "engine" && (
            <>
              <span>доступно обновление движка{updateInfo?.latestVersion ? ` ${updateInfo.latestVersion}` : ""}</span>
              <button className="btn-mini btn-mini-yes" onClick={applyUpdate} disabled={status === "running"}>
                Обновить
              </button>
            </>
          )}
          {updateState === "available" && updateKind === "client" && (
            <>
              <span className="update-badge-important">
                ⚠ важное обновление{updateInfo?.latestVersion ? ` ${updateInfo.latestVersion}` : ""} - нужен новый инсталлятор
              </span>
              <button className="btn-mini btn-mini-yes" onClick={installClientUpdate} disabled={installingClient}>
                {installingClient ? "Скачиваю..." : "Скачать и установить"}
              </button>
            </>
          )}
          {updateState === "applying" && (
            <span>
              <span className="dot running">●</span> устанавливаю обновление...
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
          ⚙
        </button>
      </div>

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

          <div className="status-row">
            <span className={"dot " + status}>{dotFor[status]}</span>
            <span>{statusLabel[status]}</span>
          </div>

          {status === "ok" && outDir && (
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
            </div>
          )}
        </div>

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
