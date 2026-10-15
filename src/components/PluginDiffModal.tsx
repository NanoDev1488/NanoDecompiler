import { useState } from "react";
import { GitCompare, FolderOpen, ArrowRight, CheckCircle2, AlertTriangle, FileCode, Plus, Minus, RefreshCw, X, Loader2 } from "lucide-react";

export interface PluginDiffReport {
  jar_a: string;
  jar_b: string;
  total_classes_a: number;
  total_classes_b: number;
  added_count: number;
  removed_count: number;
  modified_count: number;
  identical_count: number;
  added: string[];
  removed: string[];
  modified: Array<{
    class: string;
    size_old: number;
    size_new: number;
    size_diff: number;
  }>;
  manifest_changed: boolean;
}

export function PluginDiffModal({
  open,
  onClose,
  initialJarA,
}: {
  open: boolean;
  onClose: () => void;
  initialJarA?: string;
}) {
  const [jarA, setJarA] = useState(initialJarA || "");
  const [jarB, setJarB] = useState("");
  const [loading, setLoading] = useState(false);
  const [diff, setDiff] = useState<PluginDiffReport | null>(null);
  const [error, setError] = useState<string | null>(null);
  const [activeTab, setActiveTab] = useState<"modified" | "added" | "removed">("modified");

  if (!open) return null;

  async function pickJarA() {
    const res = await window.nano.selectJar();
    if (res && res.length > 0) setJarA(res[0]);
  }

  async function pickJarB() {
    const res = await window.nano.selectJar();
    if (res && res.length > 0) setJarB(res[0]);
  }

  async function runCompare() {
    if (!jarA || !jarB) {
      setError("Выберите оба JAR-файла для сравнения");
      return;
    }
    setLoading(true);
    setError(null);
    try {
      const res = await (window.nano as any).compareJars(jarA, jarB);
      if (res.ok && res.diff) {
        setDiff(res.diff);
      } else {
        setError(res.error || "Не удалось сравнить плагины");
      }
    } catch (e: any) {
      setError(e.message || "Ошибка сравнения");
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="fixed inset-0 z-[100] flex items-center justify-center bg-black/60 backdrop-blur-sm p-4">
      <div className="relative flex flex-col w-full max-w-4xl h-[85vh] overflow-hidden rounded-xl border border-line bg-surface shadow-2xl animate-rise">
        {/* Заголовок */}
        <div className="flex items-center justify-between border-b border-line px-5 py-3 bg-raised/40 flex-none">
          <div className="flex items-center gap-2.5">
            <GitCompare size={18} className="text-acid" />
            <div>
              <h3 className="font-semibold text-ink text-sm">Plugin Diff Viewer (Экспериментальный)</h3>
              <p className="text-[10px] text-faint">Сравнение версий плагинов, кряков и обновлений байткода</p>
            </div>
          </div>
          <button onClick={onClose} className="text-faint hover:text-ink transition-colors p-1 rounded-lg">
            <X size={16} />
          </button>
        </div>

        {/* Панель выбора файлов */}
        <div className="p-4 border-b border-line bg-raised/20 flex-none space-y-3">
          <div className="grid grid-cols-1 md:grid-cols-2 gap-3 text-xs">
            <div className="p-3 rounded-lg border border-line bg-raised/40 space-y-1.5">
              <span className="font-medium text-ink/80 flex items-center gap-1.5">
                <FileCode size={13} className="text-acid" /> Исходный JAR (Оригинал / Старая версия)
              </span>
              <div className="flex gap-2">
                <input
                  type="text"
                  readOnly
                  value={jarA}
                  placeholder="Выберите файл А..."
                  className="w-full rounded border border-line bg-surface px-2.5 py-1 text-[11px] font-mono text-ink/80 truncate"
                />
                <button onClick={pickJarA} className="btn btn-tonal text-xs flex-none px-2.5 py-1">
                  <FolderOpen size={13} />
                </button>
              </div>
            </div>

            <div className="p-3 rounded-lg border border-line bg-raised/40 space-y-1.5">
              <span className="font-medium text-ink/80 flex items-center gap-1.5">
                <FileCode size={13} className="text-acid" /> Целевой JAR (Новая версия / Модификация)
              </span>
              <div className="flex gap-2">
                <input
                  type="text"
                  readOnly
                  value={jarB}
                  placeholder="Выберите файл B..."
                  className="w-full rounded border border-line bg-surface px-2.5 py-1 text-[11px] font-mono text-ink/80 truncate"
                />
                <button onClick={pickJarB} className="btn btn-tonal text-xs flex-none px-2.5 py-1">
                  <FolderOpen size={13} />
                </button>
              </div>
            </div>
          </div>

          <div className="flex items-center justify-between pt-1">
            {error && <span className="text-err text-[11px]">{error}</span>}
            {!error && <span className="text-faint text-[10px]">Сравнение классов по SHA-256 хэшам байткода и манифесту</span>}
            <button
              onClick={runCompare}
              disabled={loading || !jarA || !jarB}
              className="btn btn-acid text-xs inline-flex items-center gap-1.5 ml-auto"
            >
              {loading ? <Loader2 size={13} className="animate-spin" /> : <RefreshCw size={13} />}
              {loading ? "Сравнение..." : "Сравнить плагины"}
            </button>
          </div>
        </div>

        {/* Результаты */}
        <div className="flex-1 overflow-y-auto p-5 space-y-4 text-xs">
          {diff ? (
            <>
              {/* Метрики */}
              <div className="grid grid-cols-2 sm:grid-cols-4 gap-3">
                <div className="p-3 rounded-lg border border-line bg-raised/30 text-center">
                  <div className="text-faint text-[10px]">Идентичные</div>
                  <div className="font-bold text-ink text-base">{diff.identical_count}</div>
                </div>
                <div className="p-3 rounded-lg border border-line bg-raised/30 text-center">
                  <div className="text-faint text-[10px]">Модифицированные</div>
                  <div className="font-bold text-yellow-400 text-base">{diff.modified_count}</div>
                </div>
                <div className="p-3 rounded-lg border border-line bg-raised/30 text-center">
                  <div className="text-faint text-[10px]">Добавленные</div>
                  <div className="font-bold text-acid text-base">{diff.added_count}</div>
                </div>
                <div className="p-3 rounded-lg border border-line bg-raised/30 text-center">
                  <div className="text-faint text-[10px]">Удалённые</div>
                  <div className="font-bold text-err text-base">{diff.removed_count}</div>
                </div>
              </div>

              {diff.manifest_changed && (
                <div className="p-2.5 rounded-lg border border-yellow-500/30 bg-yellow-500/10 text-yellow-300 text-[11px] flex items-center gap-2">
                  <AlertTriangle size={14} className="flex-none" />
                  <span>Манифест <strong>plugin.yml</strong> был изменен между версиями!</span>
                </div>
              )}

              {/* Навигация по вкладкам */}
              <div className="flex gap-2 border-b border-line pb-2">
                <button
                  className={`px-3 py-1 rounded-lg text-xs font-medium flex items-center gap-1.5 ${
                    activeTab === "modified" ? "bg-yellow-500/15 text-yellow-300 border border-yellow-500/30" : "text-faint"
                  }`}
                  onClick={() => setActiveTab("modified")}
                >
                  <RefreshCw size={12} /> Модифицированные ({diff.modified.length})
                </button>
                <button
                  className={`px-3 py-1 rounded-lg text-xs font-medium flex items-center gap-1.5 ${
                    activeTab === "added" ? "bg-acid/15 text-acid border border-acid/30" : "text-faint"
                  }`}
                  onClick={() => setActiveTab("added")}
                >
                  <Plus size={12} /> Добавленные ({diff.added.length})
                </button>
                <button
                  className={`px-3 py-1 rounded-lg text-xs font-medium flex items-center gap-1.5 ${
                    activeTab === "removed" ? "bg-err/15 text-err border border-err/30" : "text-faint"
                  }`}
                  onClick={() => setActiveTab("removed")}
                >
                  <Minus size={12} /> Удалённые ({diff.removed.length})
                </button>
              </div>

              {/* Списки */}
              <div className="space-y-1.5 font-mono text-[11px]">
                {activeTab === "modified" && (
                  diff.modified.length === 0 ? (
                    <div className="text-center text-faint py-8">Нет модифицированных классов</div>
                  ) : (
                    diff.modified.map((m, i) => (
                      <div key={i} className="p-2.5 rounded-lg border border-line bg-raised/30 flex items-center justify-between">
                        <span className="text-ink/90">{m.class}</span>
                        <span className={`text-[10px] px-1.5 py-0.5 rounded ${m.size_diff >= 0 ? "text-acid bg-acid/10" : "text-err bg-err/10"}`}>
                          {m.size_diff >= 0 ? `+${m.size_diff}` : m.size_diff} Б
                        </span>
                      </div>
                    ))
                  )
                )}

                {activeTab === "added" && (
                  diff.added.length === 0 ? (
                    <div className="text-center text-faint py-8">Нет добавленных классов</div>
                  ) : (
                    diff.added.map((a, i) => (
                      <div key={i} className="p-2.5 rounded-lg border border-acid/20 bg-acid/5 text-acid flex items-center gap-2">
                        <Plus size={12} /> <span>{a}</span>
                      </div>
                    ))
                  )
                )}

                {activeTab === "removed" && (
                  diff.removed.length === 0 ? (
                    <div className="text-center text-faint py-8">Нет удалённых классов</div>
                  ) : (
                    diff.removed.map((r, i) => (
                      <div key={i} className="p-2.5 rounded-lg border border-err/20 bg-err/5 text-err flex items-center gap-2">
                        <Minus size={12} /> <span>{r}</span>
                      </div>
                    ))
                  )
                )}
              </div>
            </>
          ) : (
            <div className="text-center text-faint py-16 space-y-2">
              <GitCompare size={36} className="mx-auto text-faint/60" />
              <div className="text-sm text-ink/70">Выберите два JAR файла выше и нажмите «Сравнить плагины»</div>
              <div className="text-[11px] max-w-sm mx-auto">Дифф мгновенно покажет все изменения в байткоде, внедренные или удаленные классы и патчи.</div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}
