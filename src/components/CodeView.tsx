import { Copy, WrapText } from "lucide-react";
import { memo, useState } from "react";
import { useEngine } from "../state/engine";
import { JavaCode } from "../lib/javaHighlight";
import { PlainCode, XmlCode, YamlCode } from "../lib/textHighlight";
import type { SourceFile } from "../lib/model";

// БАГ-ФИКС: раньше ЛЮБОЙ файл в просмотрщике рендерился через JavaCode
// независимо от расширения - .yml подсвечивался java-ключевыми словами.
// Выбираем токенизатор по расширению реального имени файла.
function codeComponentFor(name: string) {
  if (/\.java$/i.test(name)) return JavaCode;
  if (/\.ya?ml$/i.test(name)) return YamlCode;
  if (/\.properties$/i.test(name)) return YamlCode;  // ключ=значение - тот же токенизатор подходит
  if (/\.xml$/i.test(name)) return XmlCode;
  return PlainCode;
}

export const CodeView = memo(function CodeView({ file, jobId }: { file: SourceFile | null; jobId?: string }) {
  const { copyText, selectFile } = useEngine();
  const [wrap, setWrap] = useState(false);

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
    <section className="flex min-w-0 flex-1 flex-col bg-bg">
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
          disabled={file.code === undefined}
          onClick={() => file.code !== undefined && copyText(file.code, `Исходник ${file.name}`)}
        >
          <Copy size={14} />
        </button>
      </div>

      <div className="min-h-0 flex-1 overflow-auto py-3">
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
          ) : file.code === undefined ? (
            <p className="mono px-4 text-[11.5px] text-faint">// загрузка…</p>
          ) : (
            <CodeComponent code={file.code} wrap={wrap} />
          )}
        </div>
      </div>
    </section>
  );
});
