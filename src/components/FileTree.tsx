import {
  ArrowDownAZ,
  ArrowDownWideNarrow,
  ChevronDown,
  ChevronRight,
  ChevronsDownUp,
  ChevronsUpDown,
  File,
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
  // НОВОЕ v1.8.4 (реальная жалоба - "общее число строк растёт только по
  // мере открытия файлов, не сразу после декомпиляции"): движок уже
  // считает суммарную длину ВСЕХ сгенерированных .java при записи (см.
  // ProjectStats::total_source_lines в verify.hpp) - если это число уже
  // доступно, используем его напрямую вместо суммы f.loc по лениво
  // подгруженным файлам. Undefined (job ещё бежит / старый job без этого
  // поля в кэше) - падаем обратно на прежний лениво накапливаемый счёт.
  totalSourceLines?: number;
}

// НОВОЕ v1.7.3 (HANDOFF-бэклог п.22): реальные вложенные папки ресурсов
// (например `lang/messages/en.yml` -> pkg "lang.messages") раньше не
// показывались как раскрываемое дерево - только ОДНОЙ плоской строкой на
// каждую ТОЧНУЮ комбинацию пути ("lang.messages" целиком, без узла "lang"
// сверху, который можно было бы свернуть вместе со всем содержимым).
interface ResTreeNode {
  key: string; // полный путь узла, включая префикс ("res:"/"java:") - уникален глобально, для collapse-state и React key
  label: string; // отображаемое имя ТОЛЬКО этого сегмента (не всего пути)
  children: ResTreeNode[];
  files: SourceFile[];
}

// БАГ-ФИКС v1.8.2 (HANDOFF_URGENT п.7 - "настоящее дерево для Java-
// пакетов (не только ресурсов)"): раньше было ДВЕ разных структуры -
// настоящее дерево для ресурсов (эта функция) и отдельный плоский
// Map<string, SourceFile[]> для java (каждый ПОЛНЫЙ dotted-пакет одной
// строкой, без вложенности по сегментам). Причина исторически была
// осознанной (см. git-историю комментария) - "пользователь никогда не
// жаловался на браузинг java-пакетов" - но теперь пожаловался явно (см.
// HANDOFF_URGENT_16_ITEMS.md п.7), так что выделяем общую логику
// построения дерева по dotted-пути в keyPrefix-параметризованную функцию
// и используем её для ОБОИХ случаев, вместо двух параллельных реализаций.
function buildPkgTree(files: SourceFile[], keyPrefix: string): ResTreeNode[] {
  const rootChildren = new Map<string, ResTreeNode>();
  for (const f of files) {
    // "(корень)" - специальная метка без точек (см. state/engine.tsx) - один
    // сегмент, отображаем как "(корень: ...)" для узнаваемости (то же
    // имя, что использовалось до v1.7.3 для этой плоской группы).
    const segments = f.pkg === "(корень)" ? ["(корень)"] : f.pkg.split(".").filter(Boolean);
    let siblings = rootChildren;
    let path = keyPrefix;
    let node: ResTreeNode | undefined;
    for (const seg of segments) {
      path += ":" + seg;
      node = siblings.get(path);
      if (!node) {
        const label = seg === "(корень)" ? (keyPrefix === "java" ? "(корень: default package)" : "(корень: ресурсы)") : seg;
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

function buildResourceTree(resourceFiles: SourceFile[]): ResTreeNode[] {
  return buildPkgTree(resourceFiles, "res");
}

function buildJavaPackageTree(javaFiles: SourceFile[]): ResTreeNode[] {
  return buildPkgTree(javaFiles, "java");
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

// БАГ-ФИКС v1.8.2 (заодно с переносом java на дерево - см. buildPkgTree):
// раньше сортировка ВНУТРИ узла дерева (файлы) уважала sortMode, но
// порядок СЕСТРИНСКИХ узлов (папок/пакетов) был ЖЁСТКО алфавитным всегда,
// даже при sortMode="size"/"warnings" - для ресурсов это никогда не
// исправляли, потому что до этой версии только ПЛОСКИЙ java-список умел
// переупорядочивать сами группы по агрегату (см. историю javaGroups ниже).
// Теперь один рекурсивный проход делает и то, и другое, для ОБОИХ деревьев
// одинаково: сортирует файлы внутри узла, спускается в детей, затем
// переупорядочивает самих детей по агрегату (сумма LOC / есть ли
// предупреждение где-то в поддереве).
function nodeAggregateLoc(n: ResTreeNode): number {
  return n.files.reduce((s, f) => s + f.loc, 0) + n.children.reduce((s, c) => s + nodeAggregateLoc(c), 0);
}
function nodeHasWarning(n: ResTreeNode): boolean {
  return n.files.some(f => f.note) || n.children.some(nodeHasWarning);
}
function sortTreeNodes(nodes: ResTreeNode[], mode: SortMode): void {
  for (const n of nodes) {
    n.files.sort((a, b) => compareFiles(a, b, mode));
    sortTreeNodes(n.children, mode);
  }
  if (mode === "size") {
    nodes.sort((a, b) => nodeAggregateLoc(b) - nodeAggregateLoc(a) || a.label.localeCompare(b.label));
  } else if (mode === "warnings") {
    nodes.sort((a, b) => (nodeHasWarning(b) ? 1 : 0) - (nodeHasWarning(a) ? 1 : 0) || a.label.localeCompare(b.label));
  } else {
    nodes.sort((a, b) => a.label.localeCompare(b.label));
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

export const FileTree = memo(function FileTree({ files, openId, onSelect, totalSourceLines }: Props) {
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

  const javaTree = useMemo(() => {
    const javaFiles = visible.filter(f => /\.java$/i.test(f.name));
    const tree = buildJavaPackageTree(javaFiles);
    sortTreeNodes(tree, sortMode);
    return tree;
  }, [visible, sortMode]);

  const resourceTree = useMemo(() => {
    const resourceFiles = visible.filter(f => !/\.java$/i.test(f.name));
    const tree = buildResourceTree(resourceFiles);
    sortTreeNodes(tree, sortMode);
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
  // БАГ-ФИКС v1.8.4 - см. комментарий у totalSourceLines в Props выше.
  const lazyTotalLoc = useMemo(() => files.reduce((sum, f) => sum + f.loc, 0), [files]);
  const totalLoc = totalSourceLines ?? lazyTotalLoc;

  // НОВОЕ v1.7.2 (HANDOFF-бэклог п.23), расширено в v1.7.3 на все уровни
  // вложенности дерева ресурсов, а в v1.8.2 - и на java (теперь тоже
  // настоящее дерево, см. buildJavaPackageTree).
  const allKeys = useMemo(() => {
    return [...collectAllResKeys(javaTree), ...collectAllResKeys(resourceTree)];
  }, [javaTree, resourceTree]);
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
      {/* БАГ-ФИКС v1.8.4 (та же сессия, что и включение бинарников в дерево
          - см. collectSourceFiles): отдельная иконка для бинарных файлов,
          чтобы было видно ДО клика, что это не текст/код. */}
      {f.isBinary ? (
        <File size={13} className="flex-none opacity-40" />
      ) : (
        <FileCode2 size={13} className="flex-none opacity-60" />
      )}
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
        {javaTree.length === 0 && resourceTree.length === 0 && (
          <p className="mono px-1 pt-2 text-[11px] text-faint">// ничего не найдено</p>
        )}
        {/* НОВОЕ v1.7.3 (реальный запрос - "сверху все .java пакеты, снизу
            всё остальное, но в одном месте, красиво и понятно"): раньше
            java-пакеты и дерево ресурсов просто шли друг за другом без
            какого-либо визуального разделения - на глаз не всегда очевидно,
            где кончается код и начинаются ресурсы. Добавлены отдельные
            подписи-разделители секций (без своей кнопки сворачивания -
            это просто заголовок, не узел дерева). */}
        {javaTree.length > 0 && <div className="tree-section-label mono px-1.5 pt-1 pb-1 text-[9.5px] tracking-wide text-faint uppercase">Java</div>}
        {/* БАГ-ФИКС v1.8.2 (HANDOFF_URGENT п.7 - "настоящее дерево для
            Java-пакетов (не только ресурсов)"): раньше тут был отдельный
            плоский .map по javaGroups с полным dotted-путём одной строкой -
            теперь javaTree - такое же ResTreeNode[], как и resourceTree
            ниже, так что рендерится ТЕМ ЖЕ renderResNode без дублирования
            вёрстки. */}
        {javaTree.map(n => renderResNode(n, 0))}
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
