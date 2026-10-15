import { useState, useEffect } from "react";
import { ListTree, Terminal, Shield, Headphones, FileCode, X, Search, Check, Copy } from "lucide-react";

export interface PluginTreeData {
  jar: string;
  manifest: {
    name?: string;
    version?: string;
    main?: string;
    api_version?: string;
    author?: string;
  };
  commands: Array<{
    command: string;
    description?: string;
    permission?: string;
    aliases?: string;
  }>;
  permissions: Array<{
    permission: string;
    description?: string;
    default?: string;
  }>;
  listeners: string[];
}

export function PluginTreeModal({
  open,
  onClose,
  treeData,
}: {
  open: boolean;
  onClose: () => void;
  treeData: PluginTreeData | null;
}) {
  const [activeTab, setActiveTab] = useState<"commands" | "permissions" | "listeners" | "manifest">("commands");
  const [search, setSearch] = useState("");
  const [copied, setCopied] = useState(false);

  if (!open || !treeData) return null;

  const m = treeData.manifest;

  const filteredCommands = treeData.commands.filter(
    (c) =>
      c.command.toLowerCase().includes(search.toLowerCase()) ||
      (c.description && c.description.toLowerCase().includes(search.toLowerCase())) ||
      (c.permission && c.permission.toLowerCase().includes(search.toLowerCase()))
  );

  const filteredPermissions = treeData.permissions.filter(
    (p) =>
      p.permission.toLowerCase().includes(search.toLowerCase()) ||
      (p.description && p.description.toLowerCase().includes(search.toLowerCase()))
  );

  const filteredListeners = treeData.listeners.filter((l) =>
    l.toLowerCase().includes(search.toLowerCase())
  );

  function copyJson() {
    navigator.clipboard.writeText(JSON.stringify(treeData, null, 2));
    setCopied(true);
    setTimeout(() => setCopied(false), 2000);
  }

  return (
    <div className="fixed inset-0 z-[100] flex items-center justify-center bg-black/60 backdrop-blur-sm p-4">
      <div className="relative flex flex-col w-full max-w-3xl h-[80vh] overflow-hidden rounded-xl border border-line bg-surface shadow-2xl animate-rise">
        {/* Заголовок */}
        <div className="flex items-center justify-between border-b border-line px-5 py-3 bg-raised/40 flex-none">
          <div className="flex items-center gap-2.5">
            <ListTree size={18} className="text-acid" />
            <div>
              <h3 className="font-semibold text-ink text-sm">
                Дерево архитектуры: {m.name || treeData.jar} <span className="text-faint text-[11px] font-mono">v{m.version || "1.0"}</span>
              </h3>
              <div className="text-[10px] text-faint">
                Main: <span className="font-mono text-ink/80">{m.main || "Unknown"}</span> · API: <span className="font-mono text-ink/80">{m.api_version || "legacy"}</span>
              </div>
            </div>
          </div>
          <div className="flex items-center gap-2">
            <button
              onClick={copyJson}
              className="btn btn-ghost text-xs inline-flex items-center gap-1.5 py-1 px-2.5"
              title="Копировать JSON"
            >
              {copied ? <Check size={13} className="text-acid" /> : <Copy size={13} />}
              {copied ? "Скопировано!" : "Копировать JSON"}
            </button>
            <button onClick={onClose} className="text-faint hover:text-ink transition-colors p-1 rounded-lg">
              <X size={16} />
            </button>
          </div>
        </div>

        {/* Навигация по вкладкам и поиск */}
        <div className="flex items-center justify-between border-b border-line px-5 py-2 bg-raised/20 flex-none gap-4">
          <div className="flex gap-1">
            <button
              className={`px-3 py-1.5 rounded-lg text-xs font-medium flex items-center gap-1.5 transition-colors ${
                activeTab === "commands" ? "bg-acid/15 text-acid border border-acid/30" : "text-faint hover:text-ink"
              }`}
              onClick={() => setActiveTab("commands")}
            >
              <Terminal size={13} /> Команды ({treeData.commands.length})
            </button>
            <button
              className={`px-3 py-1.5 rounded-lg text-xs font-medium flex items-center gap-1.5 transition-colors ${
                activeTab === "permissions" ? "bg-acid/15 text-acid border border-acid/30" : "text-faint hover:text-ink"
              }`}
              onClick={() => setActiveTab("permissions")}
            >
              <Shield size={13} /> Permissions ({treeData.permissions.length})
            </button>
            <button
              className={`px-3 py-1.5 rounded-lg text-xs font-medium flex items-center gap-1.5 transition-colors ${
                activeTab === "listeners" ? "bg-acid/15 text-acid border border-acid/30" : "text-faint hover:text-ink"
              }`}
              onClick={() => setActiveTab("listeners")}
            >
              <Headphones size={13} /> Listeners ({treeData.listeners.length})
            </button>
            <button
              className={`px-3 py-1.5 rounded-lg text-xs font-medium flex items-center gap-1.5 transition-colors ${
                activeTab === "manifest" ? "bg-acid/15 text-acid border border-acid/30" : "text-faint hover:text-ink"
              }`}
              onClick={() => setActiveTab("manifest")}
            >
              <FileCode size={13} /> Манифест
            </button>
          </div>

          <div className="relative w-48">
            <Search size={13} className="absolute left-2.5 top-2.5 text-faint" />
            <input
              type="text"
              placeholder="Фильтр..."
              value={search}
              onChange={(e) => setSearch(e.target.value)}
              className="w-full rounded-lg border border-line bg-raised pl-8 pr-3 py-1 text-ink text-xs outline-none focus:border-acid"
            />
          </div>
        </div>

        {/* Контент вкладок */}
        <div className="flex-1 overflow-y-auto p-5 space-y-2 text-xs">
          {activeTab === "commands" && (
            <div className="space-y-2">
              {filteredCommands.length === 0 ? (
                <div className="text-center text-faint py-8">Команды не найдены</div>
              ) : (
                filteredCommands.map((c, i) => (
                  <div key={i} className="p-3 rounded-lg border border-line bg-raised/30 space-y-1">
                    <div className="flex items-center justify-between">
                      <span className="font-mono font-semibold text-acid text-sm">{c.command}</span>
                      {c.permission && (
                        <span className="px-2 py-0.5 rounded bg-raised border border-line text-[10px] font-mono text-faint">
                          {c.permission}
                        </span>
                      )}
                    </div>
                    {c.description && <p className="text-ink/80 text-[11px]">{c.description}</p>}
                    {c.aliases && (
                      <div className="text-[10px] text-faint font-mono">
                        Алиасы: {typeof c.aliases === "string" ? c.aliases : JSON.stringify(c.aliases)}
                      </div>
                    )}
                  </div>
                ))
              )}
            </div>
          )}

          {activeTab === "permissions" && (
            <div className="space-y-2">
              {filteredPermissions.length === 0 ? (
                <div className="text-center text-faint py-8">Права не найдены</div>
              ) : (
                filteredPermissions.map((p, i) => (
                  <div key={i} className="p-3 rounded-lg border border-line bg-raised/30 flex items-start justify-between">
                    <div>
                      <div className="font-mono font-semibold text-ink text-[11px]">{p.permission}</div>
                      {p.description && <p className="text-faint text-[10px] mt-0.5">{p.description}</p>}
                    </div>
                    <span className="px-2 py-0.5 rounded bg-raised border border-line text-[10px] text-acid font-mono">
                      default: {p.default || "op"}
                    </span>
                  </div>
                ))
              )}
            </div>
          )}

          {activeTab === "listeners" && (
            <div className="space-y-1.5 font-mono text-[11px]">
              {filteredListeners.length === 0 ? (
                <div className="text-center text-faint py-8">Слушатели эвентов не найдены</div>
              ) : (
                filteredListeners.map((l, i) => (
                  <div key={i} className="p-2.5 rounded-lg border border-line bg-raised/30 flex items-center gap-2">
                    <Headphones size={13} className="text-acid flex-none" />
                    <span className="text-ink/90 truncate">{l}</span>
                  </div>
                ))
              )}
            </div>
          )}

          {activeTab === "manifest" && (
            <div className="rounded-lg border border-line bg-raised/30 p-4 font-mono text-[11px] space-y-1.5 text-ink/90">
              <div><strong className="text-acid">name:</strong> {m.name || "N/A"}</div>
              <div><strong className="text-acid">version:</strong> {m.version || "N/A"}</div>
              <div><strong className="text-acid">main:</strong> {m.main || "N/A"}</div>
              <div><strong className="text-acid">api-version:</strong> {m.api_version || "N/A"}</div>
              <div><strong className="text-acid">author:</strong> {m.author || "N/A"}</div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}
