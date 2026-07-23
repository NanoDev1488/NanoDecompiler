import { contextBridge, ipcRenderer } from "electron";

export type LogEvent = { line: string; stream: "stdout" | "stderr" };
export type RunResult = { ok: boolean; code?: number | null; outDir?: string; error?: string };

contextBridge.exposeInMainWorld("nano", {
  selectJar: (): Promise<string | null> => ipcRenderer.invoke("dialog:selectJar"),
  selectOutDir: (): Promise<string | null> => ipcRenderer.invoke("dialog:selectOutDir"),
  openPath: (target: string): Promise<void> => ipcRenderer.invoke("shell:openPath", target),
  runDecompile: (jarPath: string, outDir: string): Promise<RunResult> =>
    ipcRenderer.invoke("run:decompile", jarPath, outDir),
  cancel: (): Promise<boolean> => ipcRenderer.invoke("run:cancel"),
  onLog: (cb: (e: LogEvent) => void) => {
    const handler = (_e: unknown, payload: LogEvent) => cb(payload);
    ipcRenderer.on("run:log", handler);
    return () => ipcRenderer.removeListener("run:log", handler);
  },
});
