import React, { useCallback, useEffect, useRef, useState } from "react";
import { classifyLine } from "./classifyLine";

type Status = "idle" | "running" | "ok" | "error";
type InstallState = "idle" | "installing" | "done";

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
  const termRef = useRef<HTMLDivElement>(null);

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
        <span className="brand-version">v2.1 · electron gui</span>
      </div>

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
        <span>движок: Python (main.py, без изменений)</span>
        <span>{jarPath ? jarPath : "—"}</span>
      </div>
    </div>
  );
}
