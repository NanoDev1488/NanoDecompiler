export {};

declare global {
  interface Window {
    nano: {
      selectJar: () => Promise<string | null>;
      selectOutDir: () => Promise<string | null>;
      openPath: (target: string) => Promise<void>;
      runDecompile: (
        jarPath: string,
        outDir: string
      ) => Promise<{ ok: boolean; code?: number | null; outDir?: string; error?: string }>;
      cancel: () => Promise<boolean>;
      onLog: (cb: (e: { line: string; stream: "stdout" | "stderr" }) => void) => () => void;
    };
  }
}
