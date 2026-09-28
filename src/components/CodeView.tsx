import { Copy, Hash, Search, WrapText, ZoomIn, ZoomOut } from "lucide-react";
import { memo, useCallback, useEffect, useMemo, useRef, useState } from "react";
import { useEngine } from "../state/engine";
import { JavaCode } from "../lib/javaHighlight";
import { PlainCode, PropertiesCode, JsonCode, XmlCode, YamlCode } from "../lib/textHighlight";
import { fmtNum, joinOutDir, type SourceFile } from "../lib/model";
import { t } from "../lib/i18n";
import { FindBar } from "./FindBar";
import { OpenInMenu } from "./OpenInMenu";

function GotoLineBar({
  maxLines,
  onJump,
  onClose,
  lang,
}: {
  maxLines: number;
  onJump: (line: number) => void;
  onClose: () => void;
  lang: string;
}) {
  const [val, setVal] = useState("");
  const inputRef = useRef<HTMLInputElement>(null);
  useEffect(() => {
    inputRef.current?.focus();
  }, []);
  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    const n = parseInt(val, 10);
    if (!isNaN(n) && n >= 1) {
      onJump(Math.min(n, Math.max(1, maxLines)));
      onClose();
    }
  };
  return (
    <form
      onSubmit={handleSubmit}
      className="absolute top-10 right-4 z-20 flex items-center gap-1.5 rounded border border-line-strong bg-surface p-1.5 shadow-lg"
    >
      <span className="mono text-[11px] text-faint">{lang === "ru" ? "Строка:" : "Line:"}</span>
      <input
        ref={inputRef}
        type="number"
        min={1}
        max={Math.max(1, maxLines)}
        value={val}
        onChange={e => setVal(e.target.value)}
        placeholder={`1..${Math.max(1, maxLines)}`}
        className="h-6 w-20 rounded border border-line bg-bg px-2 text-[11px] text-ink outline-none focus:border-acid"
        onKeyDown={e => {
          if (e.key === "Escape") onClose();
        }}
      />
      <button type="submit" className="btn btn-tonal h-6 px-2 text-[10.5px]">
        {lang === "ru" ? "Перейти" : "Go"}
      </button>
      <button
        type="button"
        onClick={onClose}
        className="icon-btn h-6 w-6 text-faint hover:text-ink"
        title={lang === "ru" ? "Закрыть (Esc)" : "Close (Esc)"}
      >
        ✕
      </button>
    </form>
  );
}

// БАГ-ФИКС: раньше ЛЮБОЙ файл в просмотрщике рендерился через JavaCode
// независимо от расширения - .yml подсвечивался java-ключевыми словами.
// Выбираем токенизатор по расширению реального имени файла.
function codeComponentFor(name: string) {
  if (/\.java$/i.test(name)) return JavaCode;
  if (/\.ya?ml$/i.test(name)) return YamlCode;
  // БАГ-ФИКС v1.7.2: .properties использует "=" как разделитель, а
  // YAML_KEY_RE в YamlCode понимает только ":" - подсветка ключей никогда
  // не срабатывала. Отдельный токенизатор PropertiesCode понимает оба.
  if (/\.properties$/i.test(name)) return PropertiesCode;
  // НОВОЕ v1.7.3 (реальная жалоба - "нет подсветки для .json, например
  // fabric.mod.json") - раньше .json не имел своего токенизатора вообще,
  // шёл через PlainCode (только pretty-print без цвета).
  if (/\.json$/i.test(name)) return JsonCode;
  if (/\.xml$/i.test(name)) return XmlCode;
  return PlainCode;
}

// НОВОЕ v1.7.2 (HANDOFF_NEXT_AGENT_HANDOVER п.17): .json-файлы (например
// mapping/stats-отчёты движка) раньше показывались ОДНОЙ строкой как есть -
// движок пишет компактный JSON без переносов, читать такое в просмотрщике
// невозможно. Красиво печатаем ТОЛЬКО для отображения (сам файл на диске не
// трогаем) - если JSON невалиден (обрезан/не JSON вовсе, несмотря на
// расширение), тихо показываем исходный текст как есть, не роняем вьюер.
function prettyPrintIfJson(name: string, code: string): string {
  if (!/\.json$/i.test(name)) return code;
  try {
    return JSON.stringify(JSON.parse(code), null, 2);
  } catch {
    return code;
  }
}

export const CodeView = memo(function CodeView({
  file,
  jobId,
  outDir,
}: {
  file: SourceFile | null;
  jobId?: string;
  outDir?: string;
}) {
  const { copyText, selectFile, toast, settings, addJarPaths } = useEngine();
  const lang = settings.language;
  const [wrap, setWrap] = useState(false);
  const codeContainerRef = useRef<HTMLDivElement>(null);
  const [zoom, setZoom] = useState(() => {
    try {
      const saved = localStorage.getItem("nano:editor_zoom");
      return saved ? Number(saved) || 100 : 100;
    } catch {
      return 100;
    }
  });

  const changeZoom = (delta: number) => {
    setZoom(z => {
      const next = Math.max(70, Math.min(160, z + delta));
      try { localStorage.setItem("nano:editor_zoom", String(next)); } catch {}
      return next;
    });
  };

  const resetZoom = () => {
    setZoom(100);
    try { localStorage.setItem("nano:editor_zoom", "100"); } catch {}
  };

  useEffect(() => {
    if (!file) return;
    setWrap(/\.(txt|md)$/i.test(file.name));
  }, [file?.id]);

  useEffect(() => {
    const onKey = (e: KeyboardEvent) => {
      if (e.altKey && e.key.toLowerCase() === "z") {
        e.preventDefault();
        setWrap(v => !v);
      } else if ((e.ctrlKey || e.metaKey) && (e.key === "=" || e.key === "+")) {
        e.preventDefault();
        changeZoom(10);
      } else if ((e.ctrlKey || e.metaKey) && (e.key === "-" || e.key === "_")) {
        e.preventDefault();
        changeZoom(-10);
      } else if ((e.ctrlKey || e.metaKey) && e.key === "0") {
        e.preventDefault();
        resetZoom();
      } else if (e.altKey && e.key.toLowerCase() === "w") {
        e.preventDefault();
        if (jobId) selectFile(jobId, "");
      }
    };
    window.addEventListener("keydown", onKey);
    return () => {
      window.removeEventListener("keydown", onKey);
    };
  }, [jobId, selectFile]);

  const [findOpen, setFindOpen] = useState(false);
  const [gotoOpen, setGotoOpen] = useState(false);

  const jumpToLine = useCallback((lineNum: number) => {
    const el = codeContainerRef.current?.querySelector(`#codeline-${lineNum}`) as HTMLElement | null;
    if (el) {
      el.scrollIntoView({ behavior: "smooth", block: "center" });
      el.classList.add("bg-acid/20");
      setTimeout(() => el.classList.remove("bg-acid/20"), 1500);
    } else if (codeContainerRef.current) {
      codeContainerRef.current.scrollTop = Math.max(0, (lineNum - 1) * 22);
    }
  }, []);

  useEffect(() => {
    const onKey = (e: KeyboardEvent) => {
      if ((e.ctrlKey || e.metaKey) && !e.shiftKey && e.key.toLowerCase() === "f") {
        e.preventDefault();
        setFindOpen(true);
      } else if ((e.ctrlKey || e.metaKey) && !e.shiftKey && e.key.toLowerCase() === "g") {
        e.preventDefault();
        setGotoOpen(true);
      } else if (e.key === "Escape") {
        setFindOpen(false);
        setGotoOpen(false);
      }
    };
    window.addEventListener("keydown", onKey);
    return () => window.removeEventListener("keydown", onKey);
  }, []);

  const job = jobId ? useEngine().jobs.find(j => j.id === jobId) : undefined;
  const platformVersion = undefined;

  const displayCode = useMemo(() => {
    if (file?.code === undefined) return undefined;
    let code = prettyPrintIfJson(file.name, file.code);
    if (platformVersion && /\.java$/i.test(file.name)) {
      const v = `"${platformVersion}"`;
      code = code.replace(/this\.getDescription\(\)\.getVersion\(\)/g, v);
      code = code.replace(/plugin\.getDescription\(\)\.getVersion\(\)/g, v);
      code = code.replace(/getDescription\(\)\.getVersion\(\)/g, v);
    }
    return code;
  }, [file?.name, file?.code, platformVersion]);

  if (!file) {
    return (
      <div className="flex min-w-0 flex-1 items-center justify-center bg-bg">
        <p className="mono text-[11.5px] text-faint">
          {lang === "ru" ? "// файл не выбран" : "// no file selected"}
        </p>
      </div>
    );
  }

  const crumbs = [...file.pkg.split("/").filter(Boolean), file.name];
  const CodeComponent = codeComponentFor(file.name);

  return (
    <section className="relative flex min-w-0 flex-1 flex-col bg-bg">
      {findOpen && <FindBar containerRef={codeContainerRef} onClose={() => setFindOpen(false)} />}
      {gotoOpen && (
        <GotoLineBar
          maxLines={file.loc || (displayCode?.split("\n").length ?? 1)}
          onJump={jumpToLine}
          onClose={() => setGotoOpen(false)}
          lang={lang}
        />
      )}
      <div className="flex h-9 flex-none items-center gap-2.5 border-b border-line px-3">
        <nav className="mono flex min-w-0 items-center gap-1 text-[11.5px] text-faint" aria-label="Путь к файлу">
          {crumbs.map((c, i) => (
            <span key={i} className="flex min-w-0 items-center gap-1">
              {i > 0 && <span className="text-line-strong">/</span>}
              <span className={i === crumbs.length - 1 ? "truncate text-ink" : "truncate"}>{c}</span>
            </span>
          ))}
        </nav>

        {file.note && (
          <span className="chip hidden border-warn/35 text-warn lg:inline-flex" title={file.note}>
            {lang === "ru" ? "замечание движка" : "engine note"}
          </span>
        )}

        <div className="flex-1" />

        <span className="mono hidden text-[10.5px] text-faint md:inline" title={lang === "ru" ? "Строк и символов в файле" : "Lines and characters in file"}>
          {displayCode ? displayCode.split("\n").length : file.loc} {t(lang, "code.lines")}
          {displayCode ? ` · ${fmtNum(displayCode.length)} ${lang === "ru" ? "симв." : "chars"}` : ""}
          {` · ${t(lang, "code.readonly")}`}
        </span>
        {outDir && (
          <OpenInMenu filePath={joinOutDir(outDir, file.relPath)} projectDir={outDir} />
        )}
        <button
          className="icon-btn h-7 w-7"
          title={lang === "ru" ? "Перейти к строке (Ctrl+G)" : "Go to line (Ctrl+G)"}
          onClick={() => setGotoOpen(true)}
        >
          <Hash size={14} />
        </button>
        <button
          className="icon-btn h-7 w-7"
          title={lang === "ru" ? "Найти в файле (Ctrl+F)" : "Find in file (Ctrl+F)"}
          onClick={() => setFindOpen(true)}
        >
          <Search size={14} />
        </button>
        <button
          className="icon-btn h-7 w-7"
          title={`${wrap ? (lang === "ru" ? "Отключить перенос строк" : "Disable word wrap") : (lang === "ru" ? "Переносить строки" : "Enable word wrap")} (Alt+Z)`}
          data-active={wrap}
          onClick={() => setWrap(v => !v)}
        >
          <WrapText size={14} />
        </button>
        <button
          className="icon-btn h-7 w-7"
          title={lang === "ru" ? "Скопировать исходник" : "Copy source"}
          disabled={displayCode === undefined}
          onClick={() => displayCode !== undefined && copyText(displayCode, `Исходник ${file.name}`)}
        >
          <Copy size={14} />
        </button>
        <div className="flex items-center gap-0.5 border-l border-line pl-1.5 ml-0.5">
          <button
            className="icon-btn h-7 w-7"
            title={lang === "ru" ? "Уменьшить масштаб (Ctrl+-)" : "Zoom out (Ctrl+-)"}
            onClick={() => changeZoom(-10)}
          >
            <ZoomOut size={13} />
          </button>
          <button
            className="mono px-1.5 h-6 rounded text-[10.5px] hover:bg-surface-elevated text-dim hover:text-ink transition-colors cursor-pointer"
            title={lang === "ru" ? "Сбросить масштаб (Ctrl+0)" : "Reset zoom (Ctrl+0)"}
            onClick={resetZoom}
          >
            {zoom}%
          </button>
          <button
            className="icon-btn h-7 w-7"
            title={lang === "ru" ? "Увеличить масштаб (Ctrl++)" : "Zoom in (Ctrl++)"}
            onClick={() => changeZoom(10)}
          >
            <ZoomIn size={13} />
          </button>
        </div>
      </div>

      <div
        ref={codeContainerRef}
        className="min-h-0 flex-1 overflow-auto py-3 outline-none"
        style={zoom !== 100 ? { fontSize: `${zoom}%` } : undefined}
      >
        {file.note && (
          <div className="mono mx-4 mb-3 rounded-lg border border-warn/25 bg-warn/5 px-3 py-2 text-[11px] leading-relaxed text-warn/90">
            {file.note}
          </div>
        )}
        {file.loadError !== undefined ? (
          <div className="mono flex flex-col items-start gap-2 px-4 text-[11.5px]">
            <p className="text-err">
              {lang === "ru" ? `// не удалось загрузить файл: ${file.loadError}` : `// failed to load file: ${file.loadError}`}
            </p>
              {/(?:бинарм|binary)/i.test(file.loadError) ? (
                <div className="flex flex-col items-start gap-3">
                  <p className="text-dim">
                    {lang === "ru"
                      ? "Просмотр бинарных файлов внутри просмотровщика пока не поддерживается - используйте кнопку «Открыть в...» (справа сверху)."
                      : "Viewing binary files inside the viewer is not supported yet - use the 'Open in...' button at top right."}
                  </p>
                  {(file.name.toLowerCase().endsWith('.jar') || file.name.toLowerCase().endsWith('.jar.patch')) && outDir && (
                    <button 
                      className="btn btn-primary h-8 px-4 text-[12px]" 
                      onClick={() => addJarPaths([joinOutDir(outDir, file.id)])}
                    >
                      {lang === "ru" ? "Декомпилировать этот .jar" : "Decompile this .jar"}
                    </button>
                  )}
                </div>
              ) : /слишком больш/i.test(file.loadError) ? (
              <p className="text-dim">
                {lang === "ru"
                  ? "Файл слишком большой для просмотра в редакторе - воспользуйтесь кнопкой «Открыть в…» справа сверху (системное приложение или папка с файлом)."
                  : "File is too large to view in the editor - use the 'Open in...' button at top right."}
              </p>
            ) : (
              <button className="btn btn-tonal h-7 text-[11px]" onClick={() => jobId && selectFile(jobId, file.id)}>
                {lang === "ru" ? "Повторить" : "Retry"}
              </button>
            )}
          </div>
        ) : displayCode === undefined ? (
          <p className="mono px-4 text-[11.5px] text-faint">{lang === "ru" ? "// загрузка…" : "// loading…"}</p>
        ) : (
          <div className={wrap ? undefined : "min-w-max"}>
            <CodeComponent code={displayCode} wrap={wrap} disableVsCodeLogs={settings.disableVsCodeLogs} />
          </div>
        )}
      </div>
    </section>
  );
});
