import { contextBridge, ipcRenderer } from "electron";

export type LogEvent = { line: string; stream: "stdout" | "stderr" };
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
  error?: string;
};

contextBridge.exposeInMainWorld("nano", {
  selectJar: (): Promise<string | null> => ipcRenderer.invoke("dialog:selectJar"),
  selectOutDir: (defaultPath?: string): Promise<string | null> =>
    ipcRenderer.invoke("dialog:selectOutDir", defaultPath),
  openPath: (target: string): Promise<void> => ipcRenderer.invoke("shell:openPath", target),
  openInVSCode: (target: string): Promise<ShellResult> => ipcRenderer.invoke("shell:openInVSCode", target),
  jarSummary: (jarPath: string): Promise<JarSummary> => ipcRenderer.invoke("jar:summary", jarPath),
  runDecompile: (jarPath: string, outDir: string): Promise<RunResult> =>
    ipcRenderer.invoke("run:decompile", jarPath, outDir),
  cancel: (): Promise<boolean> => ipcRenderer.invoke("run:cancel"),
  installTools: (only?: "jdk" | "java" | "maven"): Promise<ToolsInstallResult> =>
    ipcRenderer.invoke("tools:install", only),
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
});
