import { Copy, Search, WrapText } from "lucide-react";
import { memo, useEffect, useMemo, useRef, useState } from "react";
import { useEngine } from "../state/engine";
import { JavaCode } from "../lib/javaHighlight";
import { PlainCode, PropertiesCode, JsonCode, XmlCode, YamlCode } from "../lib/textHighlight";
import { joinOutDir, type SourceFile } from "../lib/model";
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
  const { copyText, selectFile } = useEngine();
  const [wrap, setWrap] = useState(false);
  // НОВОЕ v1.7.6: ref именно на контейнер С КОДОМ (не на весь CodeView) -
  // FindBar ищет ТОЛЬКО внутри него, см. БАГ-ФИКС в FindBar.tsx.
  const codeContainerRef = useRef<HTMLDivElement>(null);
  // НОВОЕ v1.7.6 (реальная жалоба - "если в .txt много текста, тяжело
  // читать" - у ViaVersion и похожих есть настраиваемые .txt-файлы с
  // длинными абзацами): по умолчанию включаем перенос строк ИМЕННО для
  // прозы (.txt/.md), а не для кода - код без переноса читать привычнее
  // (сохраняет визуальную структуру отступов), а длинный абзац текста без
  // переноса требует горизонтальной прокрутки для каждой строки. Ручную
  // кнопку переноса это не отменяет - просто разумный дефолт на каждый
  // новый открытый файл.
  useEffect(() => {
    if (!file) return;
    setWrap(/\.(txt|md)$/i.test(file.name));
  }, [file?.id]);
  // НОВОЕ v1.8.0 (реальный запрос - "хороший поиск в файле"): Ctrl+F
  // локально в этом компоненте (не глобальный шорткат в engine.tsx) -
  // поиск в файле имеет смысл, только пока файл вообще открыт.
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
  // useMemo вызывается БЕЗУСЛОВНО (до раннего return ниже) - иначе при
  // переключении file между null/не-null менялось бы число вызванных хуков
  // между рендерами, что React запрещает (Rules of Hooks).
  const displayCode = useMemo(
    () => (file?.code === undefined ? undefined : prettyPrintIfJson(file.name, file.code)),
    [file?.name, file?.code],
  );

  if (!file) {
    return (
      <div className="flex min-w-0 flex-1 items-center justify-center bg-bg">
        <p className="mono text-[11.5px] text-faint">// файл не выбран</p>
      </div>
    );
  }

  const crumbs = [...file.pkg.split(".").filter(Boolean), file.name];
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

        {file.note && (
          <span className="chip hidden border-warn/35 text-warn lg:inline-flex" title={file.note}>
            замечание движка
          </span>
        )}

        <div className="flex-1" />

        <span className="mono hidden text-[10.5px] text-faint md:inline">{file.loc} строк · read-only</span>
        {outDir && (
          <OpenInMenu filePath={joinOutDir(outDir, file.relPath)} projectDir={outDir} />
        )}
        {/* НОВОЕ v1.7.5 (реальная жалоба - "поиск вообще не работает"):
            раньше поиск открывался ТОЛЬКО горячими клавишами (Ctrl+F/
            Ctrl+Shift+F) без единой видимой кнопки - пользователь просто
            не мог узнать, что фича существует. Видимая кнопка + подсказка
            с сочетанием клавиш в title. */}
        <button className="icon-btn h-7 w-7" title="Найти в файле (Ctrl+F)" onClick={() => setFindOpen(true)}>
          <Search size={14} />
        </button>
        <button
          className="icon-btn h-7 w-7"
          title={wrap ? "Отключить перенос строк" : "Переносить строки"}
          data-active={wrap}
          onClick={() => setWrap(v => !v)}
        >
          <WrapText size={14} />
        </button>
        <button
          className="icon-btn h-7 w-7"
          title="Скопировать исходник"
          disabled={displayCode === undefined}
          onClick={() => displayCode !== undefined && copyText(displayCode, `Исходник ${file.name}`)}
        >
          <Copy size={14} />
        </button>
      </div>

      <div ref={codeContainerRef} className="min-h-0 flex-1 overflow-auto py-3">
        {file.note && (
          <div className="mono mx-4 mb-3 rounded-lg border border-warn/25 bg-warn/5 px-3 py-2 text-[11px] leading-relaxed text-warn/90">
            {file.note}
          </div>
        )}
        <div className={wrap ? undefined : "min-w-max"}>
          {file.loadError !== undefined ? (
            <div className="mono flex flex-col items-start gap-2 px-4 text-[11.5px]">
              <p className="text-err">// не удалось загрузить файл: {file.loadError}</p>
              <button
                className="btn btn-tonal h-7 text-[11px]"
                onClick={() => jobId && selectFile(jobId, file.id)}
              >
                Повторить
              </button>
            </div>
          ) : displayCode === undefined ? (
            <p className="mono px-4 text-[11.5px] text-faint">// загрузка…</p>
          ) : (
            <CodeComponent code={displayCode} wrap={wrap} />
          )}
        </div>
      </div>
    </section>
  );
});
