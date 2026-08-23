// MiniIde.tsx - файловый проводник + просмотр кода с подсветкой синтаксиса
// для распакованного/декомпилированного проекта плагина (HANDOFF_52,
// спецификация из HANDOFF_39 п.2 "Мини-IDE в GUI").
//
// CodeMirror 6 выбран вместо Monaco Editor - легче интегрируется с Vite
// (Monaco требует отдельной настройки web worker'ов под бандлер, это
// реальный риск сломать сборку без возможности проверить живьём в этой
// сессии - см. HANDOFF_45/51 про то же ограничение с TS-компиляцией).
// Тема редактора - самодельная, использует ТЕ ЖЕ CSS-переменные, что и
// остальной интерфейс (см. src/styles.css), а не готовую тему из npm -
// чтобы редактор не выглядел вставленным чужеродным куском.
//
// НЕ ПРОВЕРЕНО ЖИВОЙ СБОРКОЙ (нет сети на npm-реестр в песочнице сессии
// порта, см. HANDOFF_22/45) - синтаксис/типы проверены глазами и
// структурной сверкой с документацией @uiw/react-codemirror (стандартный,
// широко используемый пакет), но `npm install` + `vite build` ни разу не
// запускались за всю историю этого проекта в песочнице - см. те же
// оговорки, что и в HANDOFF_45/51 про остальной TypeScript-код.
import React, { useCallback, useEffect, useMemo, useState } from "react";
import CodeMirror from "@uiw/react-codemirror";
import { EditorView } from "@codemirror/view";
import { java } from "@codemirror/lang-java";
import { xml } from "@codemirror/lang-xml";
import { yaml } from "@codemirror/lang-yaml";

type TreeItem = { name: string; isDir: boolean };
type TreeNode = {
  path: string; // относительный путь от root, "" для корня
  name: string;
  isDir: boolean;
  expanded: boolean;
  loading: boolean;
  children: TreeNode[] | null; // null = ещё не загружено
};

function extensionOf(name: string): string {
  const i = name.lastIndexOf(".");
  return i === -1 ? "" : name.slice(i + 1).toLowerCase();
}

// Язык по расширению - только то, что реально встречается в выводе
// движка (.java, pom.xml, plugin.yml/config.yml из ресурсов) - остальное
// (MAPPING_RU.txt/README_RU.txt/произвольные ресурсы) показываем как
// обычный текст без подсветки, а не пытаемся угадывать.
function languageExtensionFor(name: string) {
  const ext = extensionOf(name);
  if (ext === "java") return java();
  if (ext === "xml") return xml();
  if (ext === "yml" || ext === "yaml") return yaml();
  return null;
}

// Иконка файла/папки - используем ТУ ЖЕ идею инлайн-SVG Material Symbols,
// что уже принята в App.tsx::Icon (см. комментарий там про отсутствие
// сети на CDN/шрифты) - здесь только новые пути, не нужные в App.tsx.
function TreeIcon({ kind }: { kind: "folder" | "folder_open" | "java" | "xml" | "text" | "file" }) {
  const paths: Record<string, string> = {
    folder: "M20 6h-8l-2-2H4c-1.1 0-2 .9-2 2v12c0 1.1.9 2 2 2h16c1.1 0 2-.9 2-2V8c0-1.1-.9-2-2-2z",
    folder_open: "M20 6h-8l-2-2H4c-1.1 0-2 .9-2 2v12c0 1.1.9 2 2 2h16c1.1 0 2-.9 2-2V8c0-1.1-.9-2-2-2zm0 12H4V8h16v10z",
    java: "M9 3v2h1v10a3 3 0 0 0 3 3h0a3 3 0 0 0 3-3V5h1V3h-4v2h1v10a1 1 0 0 1-2 0V5h1V3H9z",
    xml: "M4 4h16v2H4zm0 14h16v2H4zM7 9l3 3-3 3-1.4-1.4L7.2 12l-1.6-1.6zm10 0l1.4 1.4L16.8 12l1.6 1.6L17 15l-3-3z",
    text: "M6 2h9l5 5v13a2 2 0 0 1-2 2H6a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2zm8 1.5V8h4.5z",
    file: "M6 2h9l5 5v13a2 2 0 0 1-2 2H6a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2zm8 1.5V8h4.5z",
  };
  return (
    <svg viewBox="0 0 24 24" width={15} height={15} fill="currentColor" style={{ flexShrink: 0, opacity: 0.85 }}>
      <path d={paths[kind]} />
    </svg>
  );
}

function iconKindFor(item: { name: string; isDir: boolean }, expanded: boolean): "folder" | "folder_open" | "java" | "xml" | "text" | "file" {
  if (item.isDir) return expanded ? "folder_open" : "folder";
  const ext = extensionOf(item.name);
  if (ext === "java") return "java";
  if (ext === "xml" || ext === "yml" || ext === "yaml") return "xml";
  if (ext === "txt" || ext === "md") return "text";
  return "file";
}

const editorTheme = EditorView.theme(
  {
    "&": {
      backgroundColor: "var(--surface-sunken)",
      color: "var(--on-surface)",
      height: "100%",
      fontSize: "12.5px",
    },
    ".cm-content": { fontFamily: "var(--font-mono)", caretColor: "var(--primary)" },
    ".cm-gutters": {
      backgroundColor: "var(--surface-sunken)",
      color: "var(--on-surface-subtle)",
      border: "none",
    },
    ".cm-activeLine": { backgroundColor: "var(--surface-container)" },
    ".cm-activeLineGutter": { backgroundColor: "var(--surface-container)" },
    ".cm-selectionBackground, &.cm-focused .cm-selectionBackground": {
      backgroundColor: "var(--primary-container) !important",
    },
    ".cm-cursor": { borderLeftColor: "var(--primary)" },
    "&.cm-focused": { outline: "none" },
  },
  { dark: true }
);

function TreeRow({
  node,
  depth,
  selectedPath,
  onToggle,
  onSelect,
}: {
  node: TreeNode;
  depth: number;
  selectedPath: string | null;
  onToggle: (node: TreeNode) => void;
  onSelect: (node: TreeNode) => void;
}) {
  const isSelected = !node.isDir && node.path === selectedPath;
  return (
    <>
      <div
        className={`ide-tree-row${isSelected ? " ide-tree-row-selected" : ""}`}
        style={{ paddingLeft: 8 + depth * 14 }}
        onClick={() => (node.isDir ? onToggle(node) : onSelect(node))}
        title={node.path}
      >
        <TreeIcon kind={iconKindFor(node, node.expanded)} />
        <span className="ide-tree-name">{node.name}</span>
        {node.loading && <span className="ide-tree-spinner" />}
      </div>
      {node.isDir && node.expanded && node.children && (
        <>
          {node.children.map((child) => (
            <TreeRow key={child.path} node={child} depth={depth + 1} selectedPath={selectedPath} onToggle={onToggle} onSelect={onSelect} />
          ))}
          {node.children.length === 0 && (
            <div className="ide-tree-empty" style={{ paddingLeft: 8 + (depth + 1) * 14 }}>
              (пусто)
            </div>
          )}
        </>
      )}
    </>
  );
}

export default function MiniIde({ root, onOpenExternal }: { root: string; onOpenExternal: (path: string) => void }) {
  const [rootNode, setRootNode] = useState<TreeNode>({
    path: "",
    name: root.split(/[\\/]/).pop() || root,
    isDir: true,
    expanded: true,
    loading: false,
    children: null,
  });
  const [selectedPath, setSelectedPath] = useState<string | null>(null);
  const [fileContent, setFileContent] = useState<string | null>(null);
  const [fileError, setFileError] = useState<string | null>(null);
  const [fileLoading, setFileLoading] = useState(false);

  const loadChildren = useCallback(
    async (node: TreeNode): Promise<TreeNode[]> => {
      const res = await window.nano.listDir(root, node.path);
      if (!res.ok || !res.items) return [];
      return (res.items as TreeItem[]).map((it) => ({
        path: node.path ? `${node.path}/${it.name}` : it.name,
        name: it.name,
        isDir: it.isDir,
        expanded: false,
        loading: false,
        children: null,
      }));
    },
    [root]
  );

  // Обновление узла ГДЕ-ТО в дереве по пути - дерево иммутабельно
  // (React-friendly), поэтому рекурсивно копируем путь от корня до узла.
  const updateNode = useCallback((path: string, patch: Partial<TreeNode>) => {
    setRootNode((prev) => {
      function walk(node: TreeNode): TreeNode {
        if (node.path === path) return { ...node, ...patch };
        if (!node.children) return node;
        return { ...node, children: node.children.map(walk) };
      }
      return walk(prev);
    });
  }, []);

  const handleToggle = useCallback(
    async (node: TreeNode) => {
      if (!node.expanded && node.children === null) {
        updateNode(node.path, { loading: true });
        const children = await loadChildren(node);
        updateNode(node.path, { loading: false, expanded: true, children });
      } else {
        updateNode(node.path, { expanded: !node.expanded });
      }
    },
    [loadChildren, updateNode]
  );

  const handleSelect = useCallback(
    async (node: TreeNode) => {
      setSelectedPath(node.path);
      setFileContent(null);
      setFileError(null);
      setFileLoading(true);
      const res = await window.nano.readTextFile(root, node.path);
      setFileLoading(false);
      if (res.ok) {
        setFileContent(res.content ?? "");
      } else {
        setFileError(res.error ?? "не удалось открыть файл");
      }
    },
    [root]
  );

  // Раскрываем корень при первом монтировании.
  useEffect(() => {
    let cancelled = false;
    (async () => {
      const children = await loadChildren(rootNode);
      if (!cancelled) setRootNode((prev) => ({ ...prev, children }));
    })();
    return () => {
      cancelled = true;
    };
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [root]);

  const langExt = useMemo(() => {
    if (!selectedPath) return [editorTheme];
    const name = selectedPath.split("/").pop() || selectedPath;
    const lang = languageExtensionFor(name);
    return lang ? [lang, editorTheme] : [editorTheme];
  }, [selectedPath]);

  return (
    <div className="ide-panel">
      <div className="ide-tree-pane">
        <div className="ide-tree-header">Файлы проекта</div>
        <div className="ide-tree-scroll">
          {rootNode.children === null ? (
            <div className="ide-tree-empty">Загрузка…</div>
          ) : (
            rootNode.children.map((child) => (
              <TreeRow key={child.path} node={child} depth={0} selectedPath={selectedPath} onToggle={handleToggle} onSelect={handleSelect} />
            ))
          )}
        </div>
      </div>
      <div className="ide-editor-pane">
        {!selectedPath && <div className="ide-editor-placeholder">Выберите файл слева, чтобы посмотреть содержимое</div>}
        {selectedPath && (
          <>
            <div className="ide-editor-header">
              <span className="ide-editor-path" title={selectedPath}>
                {selectedPath}
              </span>
              <button className="btn btn-text" onClick={() => onOpenExternal(selectedPath)}>
                Открыть в системном редакторе
              </button>
            </div>
            <div className="ide-editor-body">
              {fileLoading && <div className="ide-editor-placeholder">Загрузка…</div>}
              {fileError && <div className="ide-editor-placeholder ide-editor-error">{fileError}</div>}
              {!fileLoading && !fileError && fileContent !== null && (
                <CodeMirror
                  value={fileContent}
                  height="100%"
                  theme="dark"
                  extensions={langExt}
                  editable={true}
                  basicSetup={{ lineNumbers: true, foldGutter: true, highlightActiveLine: true }}
                />
              )}
            </div>
          </>
        )}
      </div>
    </div>
  );
}
