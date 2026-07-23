import React, { useCallback, useEffect, useRef, useState } from "react";
import { classifyLine } from "./classifyLine";

type Status = "idle" | "running" | "ok" | "error";

interface LogLine {
  text: string;
  kind: ReturnType<typeof classifyLine>;
}

export default function App() {
  const [jarPath, setJarPath] = useState<string | null>(null);
  const [outDir, setOutDir] = useState<string | null>(null);
  const [dragOver, setDragOver] = useState(false);
  const [status, setStatus] = useState<Status>("idle");
  const [lines, setLines] = useState<LogLine[]>([]);
  const termRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const off = window.nano.onLog(({ line }) => {
      setLines((prev) => [...prev, { text: line, kind: classifyLine(line) }]);
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

  const pickJar = useCallback(async () => {
    const p = await window.nano.selectJar();
    if (p) setJarPath(p);
  }, []);

  const pickOutDir = useCallback(async () => {
    const p = await window.nano.selectOutDir();
    if (p) setOutDir(p);
  }, []);

  const onDrop = useCallback((e: React.DragEvent) => {
    e.preventDefault();
    setDragOver(false);
    const f = e.dataTransfer.files[0];
    if (f && f.name.toLowerCase().endsWith(".jar")) {
      setJarPath((f as any).path ?? f.name);
    }
  }, []);

  const run = useCallback(async () => {
    if (!jarPath) return;
    const resolvedOut =
      outDir ??
      // относительно самого jar — так же, как дефолт в main.py (см. HANDOFF_1)
      (jarPath.includes("/") || jarPath.includes("\\")
        ? jarPath.replace(/[\\/][^\\/]+$/, "/" + defaultOutFor(jarPath))
        : defaultOutFor(jarPath));

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
        <span className="brand-version">v2.0 · electron gui</span>
      </div>

      <div className="main">
        <div className="panel">
          <div>
            <span className="field-label">Плагин (.jar)</span>
            <div
              className={
                "dropzone" + (jarPath ? " has-file" : "") + (dragOver ? " drag-over" : "")
              }
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

          <div>
            <span className="field-label">Папка результата</span>
            <div className="path-row">
              <button className="btn" onClick={pickOutDir}>
                Выбрать...
              </button>
              <span style={{ color: "var(--on-surface-subtle)", fontSize: 11, wordBreak: "break-all" }}>
                {outDir ?? "(по умолчанию рядом с jar)"}
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
            <button className="btn" onClick={() => window.nano.openPath(outDir)}>
              Открыть папку с результатом
            </button>
          )}
        </div>

        <div className="terminal" ref={termRef}>
          {lines.length === 0 ? (
            <div className="empty-terminal">Лог появится здесь после запуска.</div>
          ) : (
            lines.map((l, i) => (
              <div key={i} className={"line-" + l.kind}>
                {l.text}
              </div>
            ))
          )}
        </div>
      </div>

      <div className="footer">
        <span>движок: Python (main.py, без изменений)</span>
        <span>{jarPath ? jarPath : "—"}</span>
      </div>
    </div>
  );
}
