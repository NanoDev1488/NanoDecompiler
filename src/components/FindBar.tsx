import { ChevronDown, ChevronUp, X } from "lucide-react";
import { useEffect, useRef, useState, type RefObject } from "react";

// БАГ-ФИКС v1.7.6 (реальные жалобы - "Ctrl+F ищет по всему GUI, а не по
// коду", "очень медленно", "кнопки вверх/вниз не работают"): раньше это
// был webContents.findInPage() - НАТИВНЫЙ поиск Electron ПО ВСЕЙ СТРАНИЦЕ
// целиком (дерево файлов слева, терминал, все панели) - не существует
// способа ограничить findInPage() одним DOM-контейнером, поэтому он и
// "искал в самом GUI". На большой странице (сотни файлов в дереве + лог
// терминала) он же и медленный - обходит весь DOM, а не только код.
// Переписано на CSS Custom Highlight API (window.CSS.highlights) -
// ищет ТОЛЬКО внутри переданного containerRef (сам просмотрщик кода),
// подсвечивает совпадения БЕЗ изменения DOM (в отличие от оборачивания
// в <mark> вручную, что могло бы конфликтовать с реконсиляцией React).
function collectTextNodes(root: Node): Text[] {
  const walker = document.createTreeWalker(root, NodeFilter.SHOW_TEXT);
  const nodes: Text[] = [];
  let n: Node | null;
  while ((n = walker.nextNode())) nodes.push(n as Text);
  return nodes;
}

export function FindBar({ containerRef, onClose }: { containerRef: RefObject<HTMLElement | null>; onClose: () => void }) {
  const [query, setQuery] = useState("");
  const [matchCount, setMatchCount] = useState(0);
  const [activeIndex, setActiveIndex] = useState(0);
  const rangesRef = useRef<Range[]>([]);
  const inputRef = useRef<HTMLInputElement>(null);
  // Приведение к `any` намеренное: CSS Custom Highlight API довольно новый
  // (Chromium 105+, добавлен в TypeScript lib.dom не во всех версиях) -
  // не полагаемся на то, что ЭТА КОНКРЕТНАЯ версия TS в проекте уже знает
  // о window.Highlight/CSS.highlights, чтобы не словить ошибку компиляции
  // на ровном месте из-за версии тулчейна.
  const cssHighlights = (window as unknown as { CSS: { highlights: Map<string, unknown> } }).CSS?.highlights;
  const HighlightCtor = (window as unknown as { Highlight?: new (...ranges: Range[]) => unknown }).Highlight;
  const supported = typeof cssHighlights !== "undefined" && typeof HighlightCtor !== "undefined";

  useEffect(() => {
    inputRef.current?.focus();
    inputRef.current?.select();
    return () => {
      if (supported) cssHighlights.delete("nd-find");
      if (supported) cssHighlights.delete("nd-find-active");
    };
  }, [supported]);

  useEffect(() => {
    const container = containerRef.current;
    if (!supported || !container) return;
    const q = query.trim();
    if (q.length < 2) {
      cssHighlights.delete("nd-find");
      cssHighlights.delete("nd-find-active");
      rangesRef.current = [];
      setMatchCount(0);
      return;
    }
    const qLower = q.toLowerCase();
    const ranges: Range[] = [];
    for (const textNode of collectTextNodes(container)) {
      const text = textNode.data.toLowerCase();
      let from = 0;
      let idx: number;
      while ((idx = text.indexOf(qLower, from)) !== -1) {
        const r = new Range();
        r.setStart(textNode, idx);
        r.setEnd(textNode, idx + q.length);
        ranges.push(r);
        from = idx + q.length;
      }
    }
    rangesRef.current = ranges;
    setMatchCount(ranges.length);
    setActiveIndex(ranges.length > 0 ? 0 : -1);
    cssHighlights.set("nd-find", new HighlightCtor(...ranges));
  }, [query, containerRef, supported]);

  useEffect(() => {
    if (!supported) return;
    const ranges = rangesRef.current;
    if (activeIndex < 0 || activeIndex >= ranges.length) {
      cssHighlights.delete("nd-find-active");
      return;
    }
    const active = ranges[activeIndex];
    cssHighlights.set("nd-find-active", new HighlightCtor(active));
    active.startContainer.parentElement?.scrollIntoView({ block: "center" });
  }, [activeIndex, matchCount, supported]);

  const next = () => {
    if (rangesRef.current.length === 0) return;
    setActiveIndex(i => (i + 1) % rangesRef.current.length);
  };
  const prev = () => {
    if (rangesRef.current.length === 0) return;
    setActiveIndex(i => (i - 1 + rangesRef.current.length) % rangesRef.current.length);
  };

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
        {query.trim().length < 2 ? "" : matchCount > 0 ? `${activeIndex + 1}/${matchCount}` : "0/0"}
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
