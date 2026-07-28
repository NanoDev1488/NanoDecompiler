export {};

declare global {
  interface Window {
    nano: {
      selectJar: () => Promise<string | null>;
      selectOutDir: (defaultPath?: string) => Promise<string | null>;
      openPath: (target: string) => Promise<void>;
      openExternal: (url: string) => Promise<void>;
      openInVSCode: (target: string) => Promise<{ ok: boolean; error?: string }>;
      jarSummary: (jarPath: string) => Promise<{
        name: string;
        size: string;
        classes: number;
        packages: number;
        java: string;
        plugin_name: string | null;
        error?: string;
      }>;
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
        updateKind?: "none" | "engine" | "client";
        currentVersion?: string;
        latestVersion?: string;
        downloadUrl?: string | null;
        clientDownloadUrl?: string | null;
        releaseUrl?: string;
        error?: string;
      }>;
      applyUpdate: (downloadUrl: string, latestApiVersion?: string) => Promise<{ ok: boolean; error?: string }>;
      installClientAndRestart: (downloadUrl: string) => Promise<{ ok: boolean; error?: string }>;
      consumeUpdateSuccessFlag: () => Promise<boolean>;
      onLog: (cb: (e: { line: string; stream: "stdout" | "stderr" }) => void) => () => void;
      onToolsProgress: (
        cb: (e: { type: "progress"; label: string; pct: number | null; downloaded_mb: number; total_mb: number | null }) => void
      ) => () => void;
    };
  }
}
