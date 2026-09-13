import { ChevronDown, ChevronUp, X } from "lucide-react";
import { useEffect, useRef, useState } from "react";

// НОВОЕ v1.8.0 (реальный запрос - "хороший поиск в файле, минимум 2 буквы").
// Использует нативный webContents.findInPage() (см. electron/main.ts) -
// подсветка совпадений и "N из M" приходят от самого Electron, здесь
// только UI-обвязка (открыть/закрыть/next/prev по Enter и стрелкам).
export function FindBar({ onClose }: { onClose: () => void }) {
  const [query, setQuery] = useState("");
  const [result, setResult] = useState<{ activeMatchOrdinal: number; matches: number } | null>(null);
  const inputRef = useRef<HTMLInputElement>(null);

  useEffect(() => {
    inputRef.current?.focus();
    inputRef.current?.select();
    const unsubscribe = window.nano.onFindResult(r => setResult(r));
    return () => {
      unsubscribe();
      window.nano.stopFindInPage();
    };
  }, []);

  useEffect(() => {
    // Минимум 2 буквы - короче почти всегда даёт бесполезный "шум" из
    // сотен совпадений одной-двух букв в декомпилированном коде.
    if (query.trim().length < 2) {
      window.nano.stopFindInPage();
      setResult(null);
      return;
    }
    window.nano.findInPage(query, true);
  }, [query]);

  const next = () => query.trim().length >= 2 && window.nano.findInPage(query, true);
  const prev = () => query.trim().length >= 2 && window.nano.findInPage(query, false);

  return (
    <div className="animate-rise absolute top-2 right-3 z-30 flex items-center gap-1 rounded-lg border border-line-strong bg-surface px-2 py-1.5 shadow-xl shadow-black/40">
      <input
        ref={inputRef}
        value={query}
        onChange={e => setQuery(e.target.value)}
        onKeyDown={e => {
          if (e.key === "Enter") (e.shiftKey ? prev : next)();
          else if (e.key === "Escape") onClose();
        }}
        placeholder="Найти в файле… (мин. 2 буквы)"
        className="field mono h-7 w-52 text-[11.5px]"
        spellCheck={false}
      />
      <span className="mono w-14 flex-none text-center text-[10.5px] text-faint">
        {query.trim().length < 2 ? "" : result && result.matches > 0 ? `${result.activeMatchOrdinal}/${result.matches}` : "0/0"}
      </span>
      <button className="icon-btn h-6 w-6" title="Предыдущее (Shift+Enter)" onClick={prev}>
        <ChevronUp size={13} />
      </button>
      <button className="icon-btn h-6 w-6" title="Следующее (Enter)" onClick={next}>
        <ChevronDown size={13} />
      </button>
      <button className="icon-btn h-6 w-6" title="Закрыть (Esc)" onClick={onClose}>
        <X size={13} />
      </button>
    </div>
  );
}
