import { ChevronDown, ChevronRight, ChevronsDownUp, ChevronsUpDown, FileCode2, Search, TriangleAlert } from "lucide-react";
import { memo, useMemo, useState } from "react";
import type { SourceFile } from "../lib/model";
import { useEngine } from "../state/engine";
import { useResizeDrag } from "../lib/useResize";

interface Props {
  files: SourceFile[];
  openId: string | undefined;
  onSelect(fileId: string): void;
}

export const FileTree = memo(function FileTree({ files, openId, onSelect }: Props) {
  const { fileTreeWidth, setFileTreeWidth } = useEngine();
  const onResizeDown = useResizeDrag("x", fileTreeWidth, setFileTreeWidth, 180, 420);
  const [query, setQuery] = useState("");
  const [collapsed, setCollapsed] = useState<Set<string>>(new Set());

  const groups = useMemo(() => {
    const q = query.trim().toLowerCase();
    const visible = q ? files.filter(f => f.name.toLowerCase().includes(q)) : files;
    const map = new Map<string, SourceFile[]>();
    for (const f of visible) {
      // НОВОЕ v1.7.2 (HANDOFF_NEXT_AGENT_HANDOVER п.21): "(корень)" раньше
      // был ОДНОЙ группой на все файлы без вложенной папки - для реального
      // плагина это одновременно default-package .java классы, pom.xml И
      // config.yml/другие ресурсы прямо в src/main/resources - вперемешку
      // по алфавиту их неудобно искать. Разделяем ТОЛЬКО отображение (не
      // сами данные) на ".java" и "ресурсы" внутри корня по расширению.
      const key = f.pkg === "(корень)" ? (/\.java$/i.test(f.name) ? "(корень)" : "(корень: ресурсы)") : f.pkg;
      const arr = map.get(key) ?? [];
      arr.push(f);
      map.set(key, arr);
    }
    return [...map.entries()];
  }, [files, query]);

  const toggle = (pkg: string) =>
    setCollapsed(prev => {
      const next = new Set(prev);
      if (next.has(pkg)) next.delete(pkg);
      else next.add(pkg);
      return next;
    });

  const filtering = query.trim().length > 0;
  const totalLoc = useMemo(() => files.reduce((sum, f) => sum + f.loc, 0), [files]);
  // НОВОЕ v1.7.2 (HANDOFF_NEXT_AGENT_HANDOVER п.23): "свернуть/развернуть
  // всё" - на jar с сотней+ пакетов прокручивать дерево, сворачивая пакеты
  // по одному, неудобно. allCollapsed - для иконки/тултипа кнопки (когда
  // ВСЕ видимые группы свёрнуты - показываем "развернуть", иначе "свернуть").
  const allPkgs = useMemo(() => groups.map(([pkg]) => pkg), [groups]);
  const allCollapsed = allPkgs.length > 0 && allPkgs.every(pkg => collapsed.has(pkg));
  const toggleAll = () => setCollapsed(allCollapsed ? new Set() : new Set(allPkgs));

  return (
    <div className="relative flex flex-none flex-col border-r border-line bg-surface" style={{ width: fileTreeWidth }}>
      <div
        onPointerDown={onResizeDown}
        className="group absolute top-0 right-[-3px] z-10 h-full w-[6px] cursor-col-resize select-none"
      >
        <div className="absolute inset-y-0 left-1/2 w-px -translate-x-1/2 bg-line-strong opacity-0 transition-opacity group-hover:opacity-100 group-active:bg-acid group-active:opacity-100" />
      </div>
      <div className="flex h-9 flex-none items-center gap-2 border-b border-line px-3">
        <span className="kicker">Исходники</span>
        <span className="chip h-[18px] px-1.5 text-[10px]">{files.length} файл(ов)</span>
        <div className="flex-1" />
        {!filtering && allPkgs.length > 1 && (
          <button
            className="icon-btn h-6 w-6"
            title={allCollapsed ? "Развернуть всё" : "Свернуть всё"}
            onClick={toggleAll}
          >
            {allCollapsed ? <ChevronsUpDown size={13} /> : <ChevronsDownUp size={13} />}
          </button>
        )}
      </div>

      <div className="flex-none p-2.5 pb-1.5">
        <div className="relative">
          <Search size={12} className="pointer-events-none absolute top-1/2 left-2.5 -translate-y-1/2 text-faint" />
          <input
            value={query}
            onChange={e => setQuery(e.target.value)}
            placeholder="Фильтр по имени…"
            className="field mono h-[30px] pl-7 text-[11.5px]"
            spellCheck={false}
          />
        </div>
      </div>

      <div className="min-h-0 flex-1 overflow-y-auto px-2 pb-2">
        {groups.length === 0 && (
          <p className="mono px-1 pt-2 text-[11px] text-faint">// ничего не найдено</p>
        )}
        {groups.map(([pkg, pkgFiles]) => {
          const isCollapsed = !filtering && collapsed.has(pkg);
          return (
            <div key={pkg} className="mb-0.5">
              <button
                onClick={() => toggle(pkg)}
                className="tree-row mono flex w-full items-center gap-1 rounded-md px-1.5 py-[5px] text-left text-[11px]"
              >
                {isCollapsed ? <ChevronRight size={12} /> : <ChevronDown size={12} />}
                <span className="flex-1 truncate">{pkg}</span>
                <span className="text-faint">{pkgFiles.length}</span>
              </button>
              {!isCollapsed &&
                pkgFiles.map(f => (
                  <button
                    key={f.id}
                    onClick={() => onSelect(f.id)}
                    aria-selected={openId === f.id}
                    className="tree-row mono flex w-full items-center gap-1.5 rounded-md py-[5px] pr-2 pl-[22px] text-left text-[12px]"
                  >
                    <FileCode2 size={13} className="flex-none opacity-60" />
                    <span className="flex-1 truncate">{f.name}</span>
                    {f.note && <TriangleAlert size={11} className="flex-none text-warn" />}
                  </button>
                ))}
            </div>
          );
        })}
      </div>

      <div className="mono flex-none border-t border-line px-3 py-2 text-[10.5px] text-faint">
        {files.length} файл(ов) · {totalLoc} строк
      </div>
    </div>
  );
});
