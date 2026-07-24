export {};

declare global {
  interface Window {
    nano: {
      selectJar: () => Promise<string | null>;
      selectOutDir: (defaultPath?: string) => Promise<string | null>;
      openPath: (target: string) => Promise<void>;
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
      onLog: (cb: (e: { line: string; stream: "stdout" | "stderr" }) => void) => () => void;
      onToolsProgress: (
        cb: (e: { type: "progress"; label: string; pct: number | null; downloaded_mb: number; total_mb: number | null }) => void
      ) => () => void;
    };
  }
}
