export {};

declare global {
  interface Window {
    nano: {
      selectJar: () => Promise<string[]>;
      selectOutDir: (defaultPath?: string) => Promise<string | null>;
      openPath: (target: string) => Promise<void>;
      openExternal: (url: string) => Promise<void>;
      openInVSCode: (target: string) => Promise<{ ok: boolean; error?: string }>;
      detectApps: () => Promise<Record<string, boolean>>;
      openWith: (editorId: string, target: string) => Promise<{ ok: boolean; error?: string }>;
      jarSummary: (jarPath: string) => Promise<{
        name: string;
        size: string;
        sizeBytes?: number;
        classes: number;
        packages: number;
        java: string;
        plugin_name: string | null;
        error?: string;
      }>;
      getEngineVersion: () => Promise<{ ok: boolean; version?: string; error?: string }>;
      // БАГ-ФИКС v1.7.3: checkEnv() существовал в preload.ts, но не был
      // объявлен здесь - window.nano.checkEnv() формально был "any" для
      // TypeScript (рассинхронизация между реальным API и его типом).
      checkEnv: () => Promise<{
        java: { ok: boolean; text?: string };
        maven: { ok: boolean; text?: string };
      }>;
      getGuiVersion: () => Promise<string>;
      minimizeWindow: () => Promise<void>;
      toggleMaximizeWindow: () => Promise<void>;
      closeWindow: () => Promise<void>;
      isWindowMaximized: () => Promise<boolean>;
      runDecompile: (
        jarPath: string,
        outDir: string
      ) => Promise<{ ok: boolean; code?: number | null; outDir?: string; error?: string }>;
      cancel: () => Promise<boolean>;
      installTools: (only?: "jdk" | "java" | "maven") => Promise<{
        ok: boolean;
        java?: string | null;
        maven?: string | null;
        errors?: string[];
        error?: string;
      }>;
      checkUpdate: () => Promise<{
        ok: boolean;
        updateKind?: "none" | "engine" | "client" | "closed_beta";
        currentVersion?: string;
        latestVersion?: string;
        latestVersionKind?: "release" | "prerelease";
        downloadUrl?: string | null;
        clientDownloadUrl?: string | null;
        releaseUrl?: string;
        error?: string;
      }>;
      applyUpdate: (downloadUrl: string, latestApiVersion?: string) => Promise<{ ok: boolean; error?: string }>;
      installClientAndRestart: (downloadUrl: string) => Promise<{ ok: boolean; error?: string; manual?: boolean }>;
      consumeUpdateSuccessFlag: () => Promise<boolean>;
      onLog: (cb: (e: { lines: { line: string; stream: "stdout" | "stderr" }[] }) => void) => () => void;
      onToolsProgress: (
        cb: (e: { type: "progress"; label: string; pct: number | null; downloaded_mb: number; total_mb: number | null }) => void
      ) => () => void;
      onDownloadProgress: (
        cb: (e: { downloaded: number; total: number | null; kind: "client" | "engine" }) => void
      ) => () => void;
      getSettings: () => Promise<{ legitimacyCheck: boolean; autoUpdateCheck: boolean; appIcon: "terminal" | "layers"; setupCompleted: boolean }>;
      setSettings: (
        partial: Partial<{ legitimacyCheck: boolean; autoUpdateCheck: boolean; appIcon: "terminal" | "layers"; setupCompleted: boolean }>
      ) => Promise<{ legitimacyCheck: boolean; autoUpdateCheck: boolean; appIcon: "terminal" | "layers"; setupCompleted: boolean; ok: boolean; error?: string }>;
      getAppIconThumbnails: () => Promise<{ terminal: string | null; layers: string | null }>;
      listDir: (root: string, relDir: string) => Promise<{ ok: boolean; items?: { name: string; isDir: boolean }[]; error?: string }>;
      readTextFile: (root: string, relPath: string) => Promise<{ ok: boolean; content?: string; size?: number; error?: string }>;
      // НОВОЕ v1.8.0: см. БАГ-ФИКС checkEnv() выше про важность держать это
      // объявление синхронным с реальным preload.ts.
      searchInProject: (
        root: string,
        query: string,
      ) => Promise<{ ok: boolean; results: { relPath: string; line: number; snippet: string }[]; truncated: boolean }>;
      findInPage: (text: string, forward: boolean) => Promise<void>;
      stopFindInPage: () => Promise<void>;
      onFindResult: (cb: (r: { activeMatchOrdinal: number; matches: number }) => void) => () => void;
    };
  }
}
