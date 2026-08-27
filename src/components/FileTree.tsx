import { ChevronDown, ChevronRight, FileCode2, Search, TriangleAlert } from "lucide-react";
import { memo, useMemo, useState } from "react";
import type { SourceFile } from "../lib/model";

interface Props {
  files: SourceFile[];
  openId: string | undefined;
  onSelect(fileId: string): void;
}

export const FileTree = memo(function FileTree({ files, openId, onSelect }: Props) {
  const [query, setQuery] = useState("");
  const [collapsed, setCollapsed] = useState<Set<string>>(new Set());

  const groups = useMemo(() => {
    const q = query.trim().toLowerCase();
    const visible = q ? files.filter(f => f.name.toLowerCase().includes(q)) : files;
    const map = new Map<string, SourceFile[]>();
    for (const f of visible) {
      const arr = map.get(f.pkg) ?? [];
      arr.push(f);
      map.set(f.pkg, arr);
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

  return (
    <div className="flex w-[248px] flex-none flex-col border-r border-line bg-surface">
      <div className="flex h-9 flex-none items-center gap-2 border-b border-line px-3">
        <span className="kicker">Исходники</span>
        <span className="chip h-[18px] px-1.5 text-[10px]">{files.length} файл(ов)</span>
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
