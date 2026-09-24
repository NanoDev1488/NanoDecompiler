import { contextBridge, ipcRenderer } from "electron";

export type LogLineEntry = { line: string; stream: "stdout" | "stderr" };
export type LogEvent = { lines: LogLineEntry[] };
export type RunResult = { ok: boolean; code?: number | null; outDir?: string; error?: string };
export type ShellResult = { ok: boolean; error?: string };
export type ToolsProgressEvent = { type: "progress"; label: string; pct: number | null; downloaded_mb: number; total_mb: number | null };
export type ToolsInstallResult = { ok: boolean; java?: string | null; maven?: string | null; errors?: string[]; error?: string };

export type JarSummary = {
  name: string;
  size: string;
  classes: number;
  packages: number;
  java: string;
  plugin_name: string | null;
  plugin_author?: string | null;
  error?: string;
};

export type AppSettings = {
  legitimacyCheck: boolean;
  autoUpdateCheck: boolean;
  appIcon: "terminal" | "layers";
  setupCompleted: boolean;
  // НОВОЕ v1.7.6 (реальный запрос - настраиваемый язык интерфейса). Пока
  // переведена только сама панель настроек (см. src/lib/i18n.ts) -
  // полный перевод всех экранов приложения - отдельная большая задача,
  // это ПЕРВЫЙ ШАГ (инфраструктура + один экран), не полное покрытие.
  language: "ru" | "en";
  // НОВОЕ v1.8.4 - см. комментарий у Settings.telemetryEnabled в main.ts.
  telemetryEnabled: boolean;
  telemetryUrl: string;
};

contextBridge.exposeInMainWorld("nano", {
  selectJar: (): Promise<string[]> => ipcRenderer.invoke("dialog:selectJar"),
  selectOutDir: (defaultPath?: string): Promise<string | null> =>
    ipcRenderer.invoke("dialog:selectOutDir", defaultPath),
  openPath: (target: string): Promise<void> => ipcRenderer.invoke("shell:openPath", target),
  openExternal: (url: string): Promise<void> => ipcRenderer.invoke("shell:openExternal", url),
  openInVSCode: (target: string): Promise<ShellResult> => ipcRenderer.invoke("shell:openInVSCode", target),
  // НОВОЕ v1.7.6: "Открыть в..." - список редакторов + проверка доступности.
  detectApps: (): Promise<Record<string, boolean>> => ipcRenderer.invoke("apps:detect"),
  // НОВОЕ v1.9.8 (окно логов разработчика, HANDOFF п.16)
  pushAppLog: (kind: string, msg: string): void => ipcRenderer.send("applog:push", kind, msg),
  getAppLog: (): Promise<Array<{ id: number; ts: number; kind: string; msg: string }>> => ipcRenderer.invoke("applog:getAll"),
  openLogWindow: (): Promise<void> => ipcRenderer.invoke("applog:open"),
  onAppLogUpdate: (cb: (entry: { id: number; ts: number; kind: string; msg: string }) => void) => {
    const handler = (_e: unknown, entry: { id: number; ts: number; kind: string; msg: string }) => cb(entry);
    ipcRenderer.on("applog:update", handler);
    return () => ipcRenderer.removeListener("applog:update", handler);
  },
  openWith: (editorId: string, target: string): Promise<ShellResult> => ipcRenderer.invoke("apps:openWith", editorId, target),
  // НОВОЕ v1.7.6: поиск похожих репозиториев на GitHub.
  searchGithubSimilar: (
    pluginName: string | null,
    author: string | null,
  ): Promise<{
    ok: boolean;
    error?: string;
    results?: { name: string; fullName: string; url: string; description: string | null; stars: number }[];
  }> => ipcRenderer.invoke("github:searchSimilar", pluginName, author),
  jarSummary: (jarPath: string): Promise<JarSummary> => ipcRenderer.invoke("jar:summary", jarPath),
  getEngineVersion: (): Promise<{ ok: boolean; version?: string; error?: string }> =>
    ipcRenderer.invoke("engine:version"),
  getGuiVersion: (): Promise<string> => ipcRenderer.invoke("gui:version"),
  minimizeWindow: (): Promise<void> => ipcRenderer.invoke("window:minimize"),
  toggleMaximizeWindow: (): Promise<void> => ipcRenderer.invoke("window:toggleMaximize"),
  closeWindow: (): Promise<void> => ipcRenderer.invoke("window:close"),
  isWindowMaximized: (): Promise<boolean> => ipcRenderer.invoke("window:isMaximized"),
  getAppIconThumbnails: (): Promise<{ terminal: string | null; layers: string | null }> =>
    ipcRenderer.invoke("appIcon:thumbnails"),
  checkEnv: (): Promise<{
    java: { ok: boolean; text?: string; inPath?: boolean };
    maven: { ok: boolean; text?: string; inPath?: boolean };
  }> => ipcRenderer.invoke("env:check"),
  runDecompile: (jarPath: string, outDir: string): Promise<RunResult> =>
    ipcRenderer.invoke("run:decompile", jarPath, outDir),
  cancel: (): Promise<boolean> => ipcRenderer.invoke("run:cancel"),
  installTools: (only?: "jdk" | "java" | "maven"): Promise<ToolsInstallResult> =>
    ipcRenderer.invoke("tools:install", only),
  checkUpdate: (): Promise<{
    ok: boolean;
    updateKind?: "none" | "engine" | "client" | "closed_beta";
    currentVersion?: string;
    latestVersion?: string;
    downloadUrl?: string | null;
    clientDownloadUrl?: string | null;
    releaseUrl?: string;
    error?: string;
  }> => ipcRenderer.invoke("update:check"),
  applyUpdate: (downloadUrl: string, latestApiVersion?: string): Promise<{ ok: boolean; error?: string }> =>
    ipcRenderer.invoke("update:apply", downloadUrl, latestApiVersion),
  installClientAndRestart: (downloadUrl: string): Promise<{ ok: boolean; error?: string; manual?: boolean }> =>
    ipcRenderer.invoke("update:installClientAndRestart", downloadUrl),
  consumeUpdateSuccessFlag: (): Promise<boolean> => ipcRenderer.invoke("update:consumeSuccessFlag"),
  onLog: (cb: (e: LogEvent) => void) => {
    const handler = (_e: unknown, payload: LogEvent) => cb(payload);
    ipcRenderer.on("run:log", handler);
    return () => ipcRenderer.removeListener("run:log", handler);
  },
  onToolsProgress: (cb: (e: ToolsProgressEvent) => void) => {
    const handler = (_e: unknown, payload: ToolsProgressEvent) => cb(payload);
    ipcRenderer.on("tools:progress", handler);
    return () => ipcRenderer.removeListener("tools:progress", handler);
  },
  onDownloadProgress: (cb: (e: { downloaded: number; total: number | null; kind: "client" | "engine" }) => void) => {
    const handler = (_e: unknown, payload: { downloaded: number; total: number | null; kind: "client" | "engine" }) =>
      cb(payload);
    ipcRenderer.on("update:downloadProgress", handler);
    return () => ipcRenderer.removeListener("update:downloadProgress", handler);
  },
  getSettings: (): Promise<AppSettings> => ipcRenderer.invoke("settings:get"),
  setSettings: (partial: Partial<AppSettings>): Promise<AppSettings & { ok: boolean; error?: string }> =>
    ipcRenderer.invoke("settings:set", partial),
  // НОВОЕ v1.8.4 (телеметрия): report собирается в рендерере (см.
  // buildTelemetryReport в state/engine.tsx), тут только транспорт.
  sendTelemetryReport: (report: unknown): Promise<{ ok: boolean; error?: string }> =>
    ipcRenderer.invoke("telemetry:sendReport", report),
  // HANDOFF_52: мини-IDE - см. main.ts::fs:listDir/fs:readTextFile.
  listDir: (root: string, relDir: string): Promise<{ ok: boolean; items?: { name: string; isDir: boolean }[]; error?: string }> =>
    ipcRenderer.invoke("fs:listDir", root, relDir),
  readTextFile: (root: string, relPath: string): Promise<{ ok: boolean; content?: string; size?: number; error?: string }> =>
    ipcRenderer.invoke("fs:readTextFile", root, relPath),
  // НОВОЕ v1.9.9 (read-write вьюер кода, HANDOFF п.6)
  writeTextFile: (root: string, relPath: string, content: string): Promise<{ ok: boolean; error?: string }> =>
    ipcRenderer.invoke("fs:writeTextFile", root, relPath, content),
  // НОВОЕ v1.8.0: поиск по содержимому всех файлов результата (не по
  // имени - см. FileTree.tsx, тот фильтр остаётся отдельно).
  searchInProject: (
    root: string,
    query: string,
  ): Promise<{ ok: boolean; results: { relPath: string; line: number; snippet: string }[]; truncated: boolean }> =>
    ipcRenderer.invoke("search:inProject", root, query),
  // НОВОЕ v1.8.0: поиск в открытом файле - см. main.ts::page:find.
  findInPage: (text: string, forward: boolean): Promise<void> => ipcRenderer.invoke("page:find", text, forward),
  stopFindInPage: (): Promise<void> => ipcRenderer.invoke("page:stopFind"),
  onFindResult: (cb: (r: { activeMatchOrdinal: number; matches: number }) => void) => {
    const handler = (_e: unknown, payload: { activeMatchOrdinal: number; matches: number }) => cb(payload);
    ipcRenderer.on("page:findResult", handler);
    return () => ipcRenderer.removeListener("page:findResult", handler);
  },
});
