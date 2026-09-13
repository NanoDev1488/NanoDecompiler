import { Search, X } from "lucide-react";
import { useEffect, useRef, useState } from "react";
import { useEngine } from "../state/engine";

// НОВОЕ v1.8.0 (реальный запрос - "поиск по проекту, минимум 3 буквы,
// сейчас ищется ужасно" - до этого искать по СОДЕРЖИМОМУ файлов вообще
// было нельзя, только по имени в дереве). Ищет через файловую систему
// результата (search:inProject, электрон main.ts) - не требует, чтобы
// файлы были уже открыты/загружены в память рендерера.
export function ProjectSearchModal({ onClose }: { onClose: () => void }) {
  const { selectedJob, selectFile } = useEngine();
  const [query, setQuery] = useState("");
  const [loading, setLoading] = useState(false);
  const [results, setResults] = useState<{ relPath: string; line: number; snippet: string }[]>([]);
  const [truncated, setTruncated] = useState(false);
  const inputRef = useRef<HTMLInputElement>(null);
  const debounceRef = useRef<number | null>(null);

  useEffect(() => {
    inputRef.current?.focus();
  }, []);

  useEffect(() => {
    if (debounceRef.current !== null) window.clearTimeout(debounceRef.current);
    const q = query.trim();
    if (q.length < 3 || !selectedJob) {
      setResults([]);
      setTruncated(false);
      return;
    }
    setLoading(true);
    debounceRef.current = window.setTimeout(async () => {
      const r = await window.nano.searchInProject(selectedJob.outDir, q);
      setLoading(false);
      if (r.ok) {
        setResults(r.results);
        setTruncated(r.truncated);
      }
    }, 220);
    return () => {
      if (debounceRef.current !== null) window.clearTimeout(debounceRef.current);
    };
  }, [query, selectedJob]);

  const openResult = (relPath: string, line: number) => {
    if (!selectedJob) return;
    const file = selectedJob.files?.find(f => f.relPath === relPath);
    if (!file) return;
    selectFile(selectedJob.id, file.id);
    onClose();
    // НОВОЕ v1.8.0: скроллим к нужной строке - id проставлен во ВСЕХ
    // токенизаторах (см. renderShell в textHighlight.tsx/javaHighlight.tsx).
    // Небольшая задержка - файл должен успеть отрендериться после смены
    // выбранного файла (readTextFile асинхронный).
    window.setTimeout(() => {
      document.getElementById(`codeline-${line}`)?.scrollIntoView({ block: "center" });
    }, 260);
  };

  return (
    <div
      className="fixed inset-0 z-50 grid place-items-start justify-center bg-black/60 pt-24 backdrop-blur-[2px]"
      onMouseDown={e => e.target === e.currentTarget && onClose()}
    >
      <div className="animate-rise flex max-h-[70vh] w-[560px] flex-col overflow-hidden rounded-2xl border border-line bg-surface shadow-2xl shadow-black/50">
        <div className="flex h-11 flex-none items-center gap-2 border-b border-line px-3">
          <Search size={14} className="flex-none text-faint" />
          <input
            ref={inputRef}
            value={query}
            onChange={e => setQuery(e.target.value)}
            onKeyDown={e => e.key === "Escape" && onClose()}
            placeholder={selectedJob ? "Искать по всем файлам проекта… (мин. 3 буквы)" : "Сначала выберите плагин слева"}
            disabled={!selectedJob}
            className="mono h-7 flex-1 bg-transparent text-[12.5px] text-ink outline-none placeholder:text-faint"
            spellCheck={false}
          />
          <button className="icon-btn h-6 w-6" onClick={onClose}>
            <X size={13} />
          </button>
        </div>
        <div className="min-h-0 flex-1 overflow-y-auto p-1.5">
          {!selectedJob ? null : query.trim().length < 3 ? (
            <p className="mono px-3 py-3 text-[11.5px] text-faint">// введите минимум 3 буквы</p>
          ) : loading ? (
            <p className="mono px-3 py-3 text-[11.5px] text-faint">// ищу…</p>
          ) : results.length === 0 ? (
            <p className="mono px-3 py-3 text-[11.5px] text-faint">// ничего не найдено</p>
          ) : (
            <>
              {truncated && (
                <p className="mono px-3 py-1.5 text-[10.5px] text-warn">
                  показаны первые 300 совпадений - уточните запрос для полного списка
                </p>
              )}
              {results.map((r, i) => (
                <button
                  key={i}
                  onClick={() => openResult(r.relPath, r.line)}
                  className="tree-row mono flex w-full flex-col gap-0.5 rounded-lg px-3 py-1.5 text-left"
                >
                  <span className="text-[10.5px] text-faint">
                    {r.relPath}:{r.line}
                  </span>
                  <span className="truncate text-[12px] text-ink/90">{r.snippet}</span>
                </button>
              ))}
            </>
          )}
        </div>
      </div>
    </div>
  );
}
