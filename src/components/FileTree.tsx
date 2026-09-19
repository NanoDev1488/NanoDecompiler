import {
  ArrowDownAZ,
  ArrowDownWideNarrow,
  ChevronDown,
  ChevronRight,
  ChevronsDownUp,
  ChevronsUpDown,
  FileCode2,
  Folder,
  FolderOpen,
  Search,
  TriangleAlert,
} from "lucide-react";
import { memo, useMemo, useState } from "react";
import type { SourceFile } from "../lib/model";
import { useEngine } from "../state/engine";
import { useResizeDrag } from "../lib/useResize";

interface Props {
  files: SourceFile[];
  openId: string | undefined;
  onSelect(fileId: string): void;
}

// НОВОЕ v1.7.3 (HANDOFF-бэклог п.22): реальные вложенные папки ресурсов
// (например `lang/messages/en.yml` -> pkg "lang.messages") раньше не
// показывались как раскрываемое дерево - только ОДНОЙ плоской строкой на
// каждую ТОЧНУЮ комбинацию пути ("lang.messages" целиком, без узла "lang"
// сверху, который можно было бы свернуть вместе со всем содержимым).
//
// Намеренно ограничено ТОЛЬКО ресурсными файлами (не .java) - Java-пакеты
// продолжают показываться как раньше, одной строкой на полный dotted-путь
// (`com.example.foo`), без разбивки по точкам на уровни. Пользователь ни
// разу не жаловался на браузинг Java-пакетов, только на ресурсные папки -
// трогать более привычное поведение без явного запроса означало бы
// увеличивать площадь риска (untested GUI) без нужды.
interface ResTreeNode {
  key: string; // полный путь узла, включая префикс "res:" - уникален глобально, для collapse-state и React key
  label: string; // отображаемое имя ТОЛЬКО этого сегмента (не всего пути)
  children: ResTreeNode[];
  files: SourceFile[];
}

function buildResourceTree(resourceFiles: SourceFile[]): ResTreeNode[] {
  const rootChildren = new Map<string, ResTreeNode>();
  for (const f of resourceFiles) {
    // "(корень)" - специальная метка без точек (см. state/engine.tsx) - один
    // сегмент, отображаем как "(корень: ресурсы)" для узнаваемости (то же
    // имя, что использовалось до v1.7.3 для этой плоской группы).
    const segments = f.pkg === "(корень)" ? ["(корень)"] : f.pkg.split(".").filter(Boolean);
    let siblings = rootChildren;
    let path = "res";
    let node: ResTreeNode | undefined;
    for (const seg of segments) {
      path += ":" + seg;
      node = siblings.get(path);
      if (!node) {
        const label = seg === "(корень)" ? "(корень: ресурсы)" : seg;
        node = { key: path, label, children: [], files: [] };
        siblings.set(path, node);
      }
      if (!(node as any)._childMap) (node as any)._childMap = new Map<string, ResTreeNode>();
      siblings = (node as any)._childMap;
    }
    if (node) node.files.push(f);
  }
  const finalize = (map: Map<string, ResTreeNode>): ResTreeNode[] => {
    const arr = [...map.values()];
    for (const n of arr) {
      const childMap: Map<string, ResTreeNode> | undefined = (n as any)._childMap;
      n.children = childMap ? finalize(childMap) : [];
      delete (n as any)._childMap;
    }
    arr.sort((a, b) => a.label.localeCompare(b.label));
    return arr;
  };
  return finalize(rootChildren);
}

function countFilesIn(node: ResTreeNode): number {
  return node.files.length + node.children.reduce((s, c) => s + countFilesIn(c), 0);
}

// НОВОЕ v1.7.3 (реальный запрос - настраиваемая сортировка списка файлов):
// "name" - алфавит (было единственным поведением раньше); "size" - по
// размеру файла в строках (LOC), больше сверху - помогает быстро найти
// "главный" класс плагина среди сотен мелких; "warnings" - файлы с
// предупреждением движка (обфускация/частичный вывод - см. TriangleAlert)
// сначала - помогает быстро добраться до того, что скорее всего требует
// ручной проверки, не листая всё подряд.
type SortMode = "name" | "size" | "warnings";

function compareFiles(a: SourceFile, b: SourceFile, mode: SortMode): number {
  if (mode === "size") return b.loc - a.loc || a.name.localeCompare(b.name);
  if (mode === "warnings") return (b.note ? 1 : 0) - (a.note ? 1 : 0) || a.name.localeCompare(b.name);
  return a.name.localeCompare(b.name);
}

function sortResourceTreeFiles(nodes: ResTreeNode[], mode: SortMode): void {
  for (const n of nodes) {
    n.files.sort((a, b) => compareFiles(a, b, mode));
    sortResourceTreeFiles(n.children, mode);
  }
}

function collectAllResKeys(nodes: ResTreeNode[]): string[] {
  const out: string[] = [];
  for (const n of nodes) {
    out.push(n.key);
    out.push(...collectAllResKeys(n.children));
  }
  return out;
}

export const FileTree = memo(function FileTree({ files, openId, onSelect }: Props) {
  const { fileTreeWidth, setFileTreeWidth, setProjectSearchOpen } = useEngine();
  const onResizeDown = useResizeDrag("x", fileTreeWidth, setFileTreeWidth, 180, 420);
  const [query, setQuery] = useState("");
  // НОВОЕ v1.8.1 (HANDOFF-бэклог п.7 - "VS-Code-style персистентность
  // collapse-state дерева файлов, сделана только для sortMode"): ключ -
  // ПУТЬ узла ("java:some.pkg", ключи ресурсного дерева), а не job/jar id -
  // так же, как VS Code помнит свёрнутые папки по пути, а не привязывает
  // это к конкретному открытому файлу. Разные jar'ы часто делят одинаковые
  // имена пакетов/папок (util, commands, listeners...) - если пользователь
  // всегда сворачивает "com.google.gson", разумно сворачивать её везде, а
  // не переспрашивать при каждом новом jar'е.
  const [collapsed, setCollapsedState] = useState<Set<string>>(() => {
    try {
      const saved = localStorage.getItem("nd:fileTreeCollapsed");
      if (saved) {
        const arr = JSON.parse(saved);
        if (Array.isArray(arr)) return new Set(arr.filter((x): x is string => typeof x === "string"));
      }
    } catch {
      // localStorage недоступен или битый JSON - просто дефолт, не роняем компонент.
    }
    return new Set();
  });
  const setCollapsed = (updater: Set<string> | ((s: Set<string>) => Set<string>)) => {
    setCollapsedState(prev => {
      const next = typeof updater === "function" ? (updater as (s: Set<string>) => Set<string>)(prev) : updater;
      try {
        localStorage.setItem("nd:fileTreeCollapsed", JSON.stringify([...next]));
      } catch {
        // не критично - просто не запомнится до следующего запуска
      }
      return next;
    });
  };
  // НОВОЕ v1.7.3 (реальный запрос - настраиваемая сортировка списка файлов):
  // сортировка применяется ТОЛЬКО к порядку ФАЙЛОВ внутри пакета/папки -
  // сами пакеты/папки остаются в алфавитном порядке (предсказуемая
  // навигация, как в большинстве IDE - "сортировка" это про файлы, не про
  // перестановку структуры дерева).
  const [sortMode, setSortModeState] = useState<SortMode>(() => {
    // НОВОЕ v1.7.6 (реальный запрос - "запоминать состояние между
    // сессиями"): режим сортировки переживает перезапуск приложения.
    // localStorage тут безопасен - это настоящее Electron-приложение с
    // постоянным профилем пользователя, а не sandboxed-артефакт.
    try {
      const saved = localStorage.getItem("nd:fileTreeSortMode");
      if (saved === "name" || saved === "size" || saved === "warnings") return saved;
    } catch {
      // localStorage недоступен (крайне маловероятно в Electron) - просто
      // используем дефолт, не роняем компонент.
    }
    return "name";
  });
  const setSortMode = (updater: SortMode | ((m: SortMode) => SortMode)) => {
    setSortModeState(prev => {
      const next = typeof updater === "function" ? (updater as (m: SortMode) => SortMode)(prev) : updater;
      try {
        localStorage.setItem("nd:fileTreeSortMode", next);
      } catch {
        // не критично - просто не запомнится до следующего запуска
      }
      return next;
    });
  };

  const visible = useMemo(() => {
    const q = query.trim().toLowerCase();
    // БАГ-ФИКС v1.8.1 (реальный пробел - искать пакет было НЕЛЬЗЯ вообще):
    // раньше матчилось только f.name (имя файла) - поиск "listener" находил
    // файл ТОЛЬКО если он сам назывался PlayerListener.java, но НЕ находил
    // Handler.java, лежащий в пакете com.example.listener. В декомпиляторе,
    // где пакетная структура - основной способ ориентироваться в сотнях
    // файлов, это едва ли не более естественный запрос, чем поиск по имени
    // файла. Матчим f.name ИЛИ f.pkg.
    return q ? files.filter(f => f.name.toLowerCase().includes(q) || f.pkg.toLowerCase().includes(q)) : files;
  }, [files, query]);

  const javaGroups = useMemo(() => {
    const map = new Map<string, SourceFile[]>();
    for (const f of visible) {
      if (!/\.java$/i.test(f.name)) continue;
      const arr = map.get(f.pkg) ?? [];
      arr.push(f);
      map.set(f.pkg, arr);
    }
    for (const arr of map.values()) arr.sort((a, b) => compareFiles(a, b, sortMode));
    // НОВОЕ v1.7.5 (реальный запрос - "сортировка ПАКЕТОВ нужна, а не
    // только файлов внутри них"): раньше сортировка ВСЕГДА применялась
    // только к файлам ВНУТРИ пакета, сам порядок пакетов был ЖЁСТКО
    // алфавитным независимо от sortMode. Теперь режим влияет и на порядок
    // самих пакетов: "size" - по суммарному LOC пакета (больше сверху),
    // "warnings" - пакеты с хотя бы одним предупреждением сначала, "name" -
    // как раньше, алфавит.
    const entries = [...map.entries()];
    if (sortMode === "size") {
      entries.sort((a, b) => {
        const sizeA = a[1].reduce((s, f) => s + f.loc, 0);
        const sizeB = b[1].reduce((s, f) => s + f.loc, 0);
        return sizeB - sizeA || a[0].localeCompare(b[0]);
      });
    } else if (sortMode === "warnings") {
      entries.sort((a, b) => {
        const warnA = a[1].some(f => f.note) ? 1 : 0;
        const warnB = b[1].some(f => f.note) ? 1 : 0;
        return warnB - warnA || a[0].localeCompare(b[0]);
      });
    } else {
      entries.sort((a, b) => a[0].localeCompare(b[0]));
    }
    return entries;
  }, [visible, sortMode]);

  const resourceTree = useMemo(() => {
    const resourceFiles = visible.filter(f => !/\.java$/i.test(f.name));
    const tree = buildResourceTree(resourceFiles);
    sortResourceTreeFiles(tree, sortMode);
    return tree;
  }, [visible, sortMode]);

  const toggle = (key: string) =>
    setCollapsed(prev => {
      const next = new Set(prev);
      if (next.has(key)) next.delete(key);
      else next.add(key);
      return next;
    });

  const filtering = query.trim().length > 0;
  const totalLoc = useMemo(() => files.reduce((sum, f) => sum + f.loc, 0), [files]);

  // НОВОЕ v1.7.2 (HANDOFF-бэклог п.23), расширено в v1.7.3 на все уровни
  // вложенности дерева ресурсов (не только плоские java-группы).
  const allKeys = useMemo(() => {
    const javaKeys = javaGroups.map(([pkg]) => "java:" + pkg);
    return [...javaKeys, ...collectAllResKeys(resourceTree)];
  }, [javaGroups, resourceTree]);
  const allCollapsed = allKeys.length > 0 && allKeys.every(k => collapsed.has(k));
  const toggleAll = () => setCollapsed(allCollapsed ? new Set() : new Set(allKeys));

  const fileRow = (f: SourceFile, depth: number) => (
    <button
      key={f.id}
      onClick={() => onSelect(f.id)}
      aria-selected={openId === f.id}
      style={{ paddingLeft: 22 + depth * 14 }}
      className="tree-row mono flex w-full items-center gap-1.5 rounded-md py-[5px] pr-2 text-left text-[12px]"
    >
      <FileCode2 size={13} className="flex-none opacity-60" />
      <span className="flex-1 truncate">{f.name}</span>
      {/* БАГ-ФИКС v1.8.1 (в духе п.11 - "подсказки при наведении для ВСЕХ
          индикаторов", тот же пробел нашёлся и тут): f.note - готовый
          человекочитаемый текст (например "частичный вывод - см.
          байткод"), но раньше просто ЛЕЖАЛ в данных и никак не
          показывался - нужно было открывать файл, чтобы узнать, ЧТО
          именно движок предупреждает. */}
      {f.note && <TriangleAlert size={11} className="flex-none text-warn" title={f.note} />}
    </button>
  );

  const renderResNode = (node: ResTreeNode, depth: number) => {
    const isCollapsed = !filtering && collapsed.has(node.key);
    const totalCount = countFilesIn(node);
    return (
      <div key={node.key} className="mb-0.5">
        <button
          onClick={() => toggle(node.key)}
          style={{ paddingLeft: 6 + depth * 14 }}
          className="tree-row mono flex w-full items-center gap-1 rounded-md py-[5px] pr-1.5 text-left text-[11px]"
        >
          {isCollapsed ? <ChevronRight size={12} /> : <ChevronDown size={12} />}
          {isCollapsed ? (
            <Folder size={12} className="flex-none opacity-70" />
          ) : (
            <FolderOpen size={12} className="flex-none opacity-70" />
          )}
          <span className="flex-1 truncate">{node.label}</span>
          <span className="text-faint">{totalCount}</span>
        </button>
        {!isCollapsed && (
          <>
            {node.children.map(c => renderResNode(c, depth + 1))}
            {node.files.map(f => fileRow(f, depth + 1))}
          </>
        )}
      </div>
    );
  };

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
        {/* НОВОЕ v1.7.5 (реальная жалоба - "поиск вообще не работает",
            причина - не было видимой кнопки, только Ctrl+Shift+F). */}
        <button
          className="icon-btn h-6 w-6"
          title="Поиск по всему проекту (Ctrl+Shift+F)"
          onClick={() => setProjectSearchOpen(true)}
        >
          <Search size={13} />
        </button>
        {/* НОВОЕ v1.7.3: настраиваемая сортировка файлов - по клику
            переключает "имя -> размер -> предупреждения -> имя…" (без
            выпадающего меню - в проекте нигде нет кастомного select/popover
            компонента, добавлять его ради одной кнопки - лишний риск
            непроверенной визуально GUI-фичи; цикл-кнопка с тултипом решает
            задачу без этого риска). */}
        <button
          className="icon-btn h-6 w-6"
          title={
            sortMode === "name"
              ? "Сортировка: по имени (нажмите для смены)"
              : sortMode === "size"
                ? "Сортировка: по размеру (нажмите для смены)"
                : "Сортировка: сначала с предупреждениями (нажмите для смены)"
          }
          onClick={() => setSortMode(m => (m === "name" ? "size" : m === "size" ? "warnings" : "name"))}
        >
          {sortMode === "name" ? (
            <ArrowDownAZ size={13} />
          ) : sortMode === "size" ? (
            <ArrowDownWideNarrow size={13} />
          ) : (
            <TriangleAlert size={13} />
          )}
        </button>
        {!filtering && allKeys.length > 1 && (
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
            placeholder="Фильтр по имени или пакету…"
            className="field mono h-[30px] pl-7 text-[11.5px]"
            spellCheck={false}
          />
        </div>
      </div>

      <div className="min-h-0 flex-1 overflow-y-auto px-2 pb-2">
        {javaGroups.length === 0 && resourceTree.length === 0 && (
          <p className="mono px-1 pt-2 text-[11px] text-faint">// ничего не найдено</p>
        )}
        {/* НОВОЕ v1.7.3 (реальный запрос - "сверху все .java пакеты, снизу
            всё остальное, но в одном месте, красиво и понятно"): раньше
            java-пакеты и дерево ресурсов просто шли друг за другом без
            какого-либо визуального разделения - на глаз не всегда очевидно,
            где кончается код и начинаются ресурсы. Добавлены отдельные
            подписи-разделители секций (без своей кнопки сворачивания -
            это просто заголовок, не узел дерева). */}
        {javaGroups.length > 0 && <div className="tree-section-label mono px-1.5 pt-1 pb-1 text-[9.5px] tracking-wide text-faint uppercase">Java</div>}
        {javaGroups.map(([pkg, pkgFiles]) => {
          const key = "java:" + pkg;
          const isCollapsed = !filtering && collapsed.has(key);
          return (
            <div key={key} className="mb-0.5">
              <button
                onClick={() => toggle(key)}
                className="tree-row mono flex w-full items-center gap-1 rounded-md px-1.5 py-[5px] text-left text-[11px]"
              >
                {isCollapsed ? <ChevronRight size={12} /> : <ChevronDown size={12} />}
                <span className="flex-1 truncate">{pkg}</span>
                <span className="text-faint">{pkgFiles.length}</span>
              </button>
              {!isCollapsed && pkgFiles.map(f => fileRow(f, 0))}
            </div>
          );
        })}
        {resourceTree.length > 0 && (
          <div className="tree-section-label mono mt-1 border-t border-line px-1.5 pt-2 pb-1 text-[9.5px] tracking-wide text-faint uppercase">
            Ресурсы
          </div>
        )}
        {resourceTree.map(n => renderResNode(n, 0))}
      </div>

      <div className="mono flex-none border-t border-line px-3 py-2 text-[10.5px] text-faint">
        {files.length} файл(ов) · {totalLoc} строк
      </div>
    </div>
  );
});
