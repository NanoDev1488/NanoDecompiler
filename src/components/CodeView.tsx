import { Copy, Search, WrapText } from "lucide-react";
import { memo, useEffect, useMemo, useRef, useState } from "react";
import { useEngine } from "../state/engine";
import { JavaCode } from "../lib/javaHighlight";
import { PlainCode, PropertiesCode, JsonCode, XmlCode, YamlCode } from "../lib/textHighlight";
import { joinOutDir, type SourceFile } from "../lib/model";
import { t } from "../lib/i18n";
import { FindBar } from "./FindBar";
import { OpenInMenu } from "./OpenInMenu";

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
  const { copyText, selectFile, updateFileCode, toast, settings } = useEngine();
  const lang = settings.language;
  const [wrap, setWrap] = useState(false);
  const [draft, setDraft] = useState("");
  const lastSavedRef = useRef<string>("");
  const [saveState, setSaveState] = useState<"saving" | "err" | null>(null);
  const codeContainerRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    if (!file) return;
    setWrap(/\.(txt|md)$/i.test(file.name));
    setDraft(file.code ?? "");
    lastSavedRef.current = file.code ?? "";
    setSaveState(null);
  }, [file?.id]);

  const saveNowRef = useRef<() => void>(() => {});
  useEffect(() => {
    saveNowRef.current = () => {
      if (!outDir || !jobId || !file) return;
      if (draft === lastSavedRef.current) return;
      setSaveState("saving");
      window.nano
        .writeTextFile(outDir, file.relPath, draft)
        .then(res => {
          if (res.ok) {
            lastSavedRef.current = draft;
            updateFileCode(jobId, file.id, draft);
            setSaveState(null);
          } else {
            setSaveState("err");
            toast(`Не удалось сохранить ${file.name}: ${res.error ?? "неизвестная ошибка"}`, "err");
          }
        })
        .catch(e => {
          setSaveState("err");
          toast(`Не удалось сохранить ${file.name}: ${String(e)}`, "err");
        });
    };
  });

  // Автоматический flush несохранённых данных при смене файла или закрытии
  useEffect(() => {
    return () => {
      saveNowRef.current();
    };
  }, [file?.id]);

  useEffect(() => {
    const onKey = (e: KeyboardEvent) => {
      if ((e.ctrlKey || e.metaKey) && e.key.toLowerCase() === "s") {
        e.preventDefault();
        saveNowRef.current();
      }
    };
    window.addEventListener("keydown", onKey);
    const timer = window.setInterval(() => saveNowRef.current(), 5000);
    return () => {
      window.removeEventListener("keydown", onKey);
      window.clearInterval(timer);
    };
  }, []);

  const [findOpen, setFindOpen] = useState(false);
  useEffect(() => {
    const onKey = (e: KeyboardEvent) => {
      if ((e.ctrlKey || e.metaKey) && !e.shiftKey && e.key.toLowerCase() === "f") {
        e.preventDefault();
        setFindOpen(true);
      } else if (e.key === "Escape") {
        setFindOpen(false);
      }
    };
    window.addEventListener("keydown", onKey);
    return () => window.removeEventListener("keydown", onKey);
  }, []);

  const canEdit = !!outDir && !!jobId && file?.code !== undefined && file?.loadError === undefined;
  const displayCode = useMemo(() => {
    if (file?.code === undefined) return undefined;
    return canEdit ? draft : prettyPrintIfJson(file.name, file.code);
  }, [file?.name, file?.code, canEdit, draft]);

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
      <div className="flex h-9 flex-none items-center gap-2.5 border-b border-line px-3">
        <nav className="mono flex min-w-0 items-center gap-1 text-[11.5px] text-faint" aria-label="Путь к файлу">
          {crumbs.map((c, i) => (
            <span key={i} className="flex min-w-0 items-center gap-1">
              {i > 0 && <span className="text-line-strong">/</span>}
              <span className={i === crumbs.length - 1 ? "truncate text-ink" : "truncate"}>{c}</span>
            </span>
          ))}
        </nav>

        {canEdit && (draft !== lastSavedRef.current || saveState !== null) && (
          <span className="mono flex items-center gap-1.5 text-[10.5px]">
            {saveState === "saving" ? (
              <span className="text-acid">{lang === "ru" ? "сохранение…" : "saving…"}</span>
            ) : saveState === "err" ? (
              <span className="text-err">{lang === "ru" ? "ошибка сохранения" : "save error"}</span>
            ) : (
              <span className="flex items-center gap-1 text-warn">
                <span className="h-1.5 w-1.5 rounded-full bg-warn" />
                {lang === "ru" ? "не сохранено" : "unsaved"}
              </span>
            )}
          </span>
        )}

        {file.note && (
          <span className="chip hidden border-warn/35 text-warn lg:inline-flex" title={file.note}>
            {lang === "ru" ? "замечание движка" : "engine note"}
          </span>
        )}

        <div className="flex-1" />

        <span className="mono hidden text-[10.5px] text-faint md:inline">
          {file.loc} {t(lang, "code.lines")}{canEdit ? "" : ` · ${t(lang, "code.readonly")}`}
        </span>
        {outDir && (
          <OpenInMenu filePath={joinOutDir(outDir, file.relPath)} projectDir={outDir} />
        )}
        <button
          className="icon-btn h-7 w-7"
          title={lang === "ru" ? "Найти в файле (Ctrl+F)" : "Find in file (Ctrl+F)"}
          onClick={() => setFindOpen(true)}
        >
          <Search size={14} />
        </button>
        <button
          className="icon-btn h-7 w-7"
          title={wrap ? (lang === "ru" ? "Отключить перенос строк" : "Disable word wrap") : (lang === "ru" ? "Переносить строки" : "Enable word wrap")}
          data-active={wrap}
          disabled={canEdit}
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
        {canEdit && (
          <span
            className="mono flex items-center gap-1 text-[10.5px] text-faint"
            title={lang === "ru" ? "Ctrl+S сохраняет сразу; иначе автосохранение раз в 5с" : "Ctrl+S saves immediately; otherwise autosaves every 5s"}
          >
            {saveState === "saving" ? (
              t(lang, "code.saving")
            ) : saveState === "err" ? (
              <span className="text-err">{t(lang, "code.save_error")}</span>
            ) : draft === lastSavedRef.current ? (
              t(lang, "code.saved")
            ) : (
              t(lang, "code.unsaved")
            )}
          </span>
        )}
      </div>

      <div ref={codeContainerRef} className="min-h-0 flex-1 overflow-auto py-3">
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
            {/(?:похоже на )?бинарн/i.test(file.loadError) ? (
              <p className="text-dim">
                {lang === "ru"
                  ? "Просмотр бинарных файлов внутри вьюера пока не поддерживается - воспользуйтесь кнопкой «Открыть в…» справа сверху (системное приложение или папка с файлом)."
                  : "Viewing binary files inside the viewer is not supported yet - use the 'Open in...' button at top right."}
              </p>
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
        ) : canEdit ? (
          <div className="grid min-w-max" style={{ gridTemplateAreas: '"stack"' }}>
            <div style={{ gridArea: "stack" }} aria-hidden className="pointer-events-none">
              <CodeComponent code={displayCode} wrap={false} />
            </div>
            <textarea
              style={{ gridArea: "stack" }}
              className="mono h-full w-full resize-none border-0 bg-transparent px-4 pl-[64px] text-[12.5px] leading-[1.75] whitespace-pre text-transparent caret-ink outline-none"
              value={draft}
              spellCheck={false}
              onChange={e => setDraft(e.target.value)}
              onKeyDown={e => {
                if (e.key === "Tab") {
                  e.preventDefault();
                  const target = e.currentTarget;
                  const start = target.selectionStart;
                  const end = target.selectionEnd;
                  const val = target.value;
                  const next = val.substring(0, start) + "    " + val.substring(end);
                  setDraft(next);
                  requestAnimationFrame(() => {
                    target.selectionStart = target.selectionEnd = start + 4;
                  });
                }
              }}
            />
          </div>
        ) : (
          <div className={wrap ? undefined : "min-w-max"}>
            <CodeComponent code={displayCode} wrap={wrap} />
          </div>
        )}
      </div>
    </section>
  );
});
