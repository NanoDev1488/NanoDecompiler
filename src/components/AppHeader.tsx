import { Bell, ChevronDown, FileArchive, FolderOpen, MessageSquare, Play, Settings2, Square, Wrench, ListTree, GitCompare } from "lucide-react";
import { useState } from "react";
import { t } from "../lib/i18n";
import { fmtBytes } from "../lib/model";
import { useEngine } from "../state/engine";
import { cn } from "../utils/cn";

function GithubIcon({ size = 16, className }: { size?: number; className?: string }) {
  return (
    <svg
      width={size}
      height={size}
      viewBox="0 0 24 24"
      fill="currentColor"
      className={className}
      aria-hidden="true"
    >
      <path d="M12 .5C5.73.5.5 5.73.5 12c0 5.1 3.29 9.43 7.86 10.96.58.1.79-.25.79-.56 0-.28-.01-1.02-.02-2-3.2.7-3.88-1.54-3.88-1.54-.52-1.34-1.28-1.7-1.28-1.7-1.05-.72.08-.7.08-.7 1.16.08 1.77 1.2 1.77 1.2 1.03 1.77 2.7 1.26 3.36.96.1-.75.4-1.26.73-1.55-2.55-.29-5.24-1.28-5.24-5.7 0-1.26.45-2.29 1.19-3.09-.12-.29-.52-1.46.11-3.05 0 0 .97-.31 3.18 1.18a10.98 10.98 0 0 1 5.79 0c2.2-1.49 3.17-1.18 3.17-1.18.63 1.59.23 2.76.11 3.05.74.8 1.19 1.83 1.19 3.09 0 4.43-2.7 5.4-5.27 5.69.42.36.78 1.07.78 2.17 0 1.57-.01 2.83-.01 3.22 0 .31.21.67.8.55A11.5 11.5 0 0 0 23.5 12C23.5 5.73 18.27.5 12 .5Z" />
    </svg>
  );
}
import { PluginTreeModal, type PluginTreeData } from "./PluginTreeModal";
import { PluginDiffModal } from "./PluginDiffModal";
import { GitHubExportModal } from "./GitHubExportModal";

export function AppHeader() {
  const {
    runningJob,
    runningElapsed,
    queuedCount,
    selectedJob,
    envIssue,
    engineVersion,
    updateInfo,
    settings,
    startQueue,
    stopRunning,
    stopAll,
    openFileDialog,
    setSettingsOpen,
    setBugReportOpen,
    setUpdateModalOpen,
    setPaletteOpen,
  } = useEngine();

  const lang = settings.language;
  const running = runningJob !== null;
  const engineLabel = engineVersion?.replace(/^NanoDecompiler /, "") ?? "engine";
  const hasUpdate = updateInfo.kind === "engine" || updateInfo.kind === "client";

  const [treeModalOpen, setTreeModalOpen] = useState(false);
  const [treeData, setTreeData] = useState<PluginTreeData | null>(null);
  const [diffModalOpen, setDiffModalOpen] = useState(false);
  const [githubExportOpen, setGithubExportOpen] = useState(false);

  async function handleOpenTree() {
    let jarPath = selectedJob?.jarPath;
    if (!jarPath) {
      const picked = await window.nano.selectJar();
      if (picked && picked.length > 0) jarPath = picked[0];
    }
    if (!jarPath) return;
    try {
      const res = await (window.nano as any).getPluginTree(jarPath);
      if (res.ok && res.tree) {
        setTreeData(res.tree);
        setTreeModalOpen(true);
      }
    } catch (e) {
      console.error(e);
    }
  }

  function handleOpenDiff() {
    setDiffModalOpen(true);
  }

  function handleOpenGitHubExport() {
    setGithubExportOpen(true);
  }

  return (
    <div className="flex h-12 flex-none items-center gap-3 z-20 border-b border-line bg-surface px-3">
      {/* статус движка — ассист-чип с точкой, наследие gui_neon.py */}
      <div
        className={cn(
          "chip max-w-[32vw] flex-none",
          envIssue && "border-err/40 text-err",
          !envIssue && running && "border-acid/40 text-acid",
        )}
        role="status"
      >
        <span
          className={cn(
            "dot",
            envIssue ? "bg-err" : running ? "bg-acid animate-pulse-dot" : "bg-acid",
          )}
        />
        <span className="truncate">
          {envIssue
            ? t(lang, "app.env_error")
            : running
              ? `${engineLabel} — ${t(lang, "app.busy")}${runningElapsed !== null ? ` · ${(runningElapsed / 1000).toFixed(1)} s` : ""}`
              : `${engineLabel} — ${t(lang, "app.ready")}`}
        </span>
        {envIssue && (
          <button
            className="mono -mr-1 rounded px-1 text-[10px] underline decoration-dotted underline-offset-2 hover:text-ink"
            onClick={() => setSettingsOpen(true)}
          >
            {t(lang, "app.fix")}
          </button>
        )}
      </div>

      {selectedJob && (
        <div
          className="chip max-w-[28vw] flex-none truncate text-[11px] text-ink/75 border-line bg-raised/40 hover:text-ink transition-colors cursor-default hidden sm:flex items-center gap-1.5"
          title={`${selectedJob.jarPath}\n${fmtBytes(selectedJob.sizeBytes)}${selectedJob.classCount ? ` · ${selectedJob.classCount} классов` : ""}`}
        >
          <FileArchive size={12} className="text-acid flex-none opacity-80" />
          <span className="font-mono truncate">{selectedJob.fileName}</span>
          <span className="text-faint text-[10px] flex-none">({fmtBytes(selectedJob.sizeBytes)})</span>
        </div>
      )}

      <div className="flex-1" />

      <button className="btn btn-tonal flex-none" onClick={openFileDialog}>
        <FolderOpen size={14} />
        {t(lang, "app.open_jar")}
        <span className="kbd ml-1 hidden lg:inline">Ctrl O</span>
      </button>

      {running ? (
        queuedCount > 0 ? (
          <div className="flex flex-none items-center gap-1">
            <button className="btn btn-err flex-none" onClick={stopAll}>
              <Square size={13} />
              {t(lang, "app.stop_all")}
            </button>
            <button
              className="icon-btn h-8 w-8 flex-none border border-line"
              onClick={stopRunning}
              title={t(lang, "app.stop_current")}
            >
              <Square size={11} />
            </button>
          </div>
        ) : (
          <button className="btn btn-err flex-none" onClick={stopRunning}>
            <Square size={13} />
            {t(lang, "app.stop")}
          </button>
        )
      ) : (
        <button className="btn btn-acid flex-none" onClick={startQueue} disabled={queuedCount === 0}>
          <Play size={14} />
          {t(lang, "app.start")}
          {queuedCount > 0 && (
            <span className="mono grid h-[18px] min-w-[18px] place-items-center rounded-full bg-black/25 px-1 text-[10px] font-semibold">
              {queuedCount}
            </span>
          )}
        </button>
      )}

      <button className="btn btn-ghost flex-none" onClick={() => setPaletteOpen(true)} title="Палитра команд">
        <span className="kbd">Ctrl K</span>
        <span className="hidden text-faint md:inline">команды</span>
      </button>

      {/* НОВОЕ v2.0.0-alpha: Меню инструментов (Дерево, Diff, GitHub) */}
      <ToolsMenu
        onOpenTree={handleOpenTree}
        onOpenDiff={handleOpenDiff}
        onOpenGitHub={handleOpenGitHubExport}
      />

      <ToolbarMenu lang={lang} hasUpdate={hasUpdate} setUpdateModalOpen={setUpdateModalOpen} setBugReportOpen={setBugReportOpen} />

      <button
        className="icon-btn flex-none"
        onClick={() => setSettingsOpen(true)}
        aria-label={t(lang, "app.settings")}
        title={`${t(lang, "app.settings")} (Ctrl ,)`}
      >
        <Settings2 size={16} />
      </button>

      {/* Модалки инструментов v2.0 */}
      {treeModalOpen && <PluginTreeModal open={treeModalOpen} onClose={() => setTreeModalOpen(false)} treeData={treeData} />}
      {diffModalOpen && <PluginDiffModal open={diffModalOpen} onClose={() => setDiffModalOpen(false)} initialJarA={selectedJob?.jarPath} />}
      {githubExportOpen && (
        <GitHubExportModal
          open={githubExportOpen}
          onClose={() => setGithubExportOpen(false)}
          outDir={selectedJob?.outDir || ""}
          defaultRepoName={selectedJob?.fileName ? selectedJob.fileName.replace(/\.jar$/i, "") : "decompiled-plugin"}
        />
      )}
    </div>
  );
}

function ToolsMenu({
  onOpenTree,
  onOpenDiff,
  onOpenGitHub,
}: {
  onOpenTree: () => void;
  onOpenDiff: () => void;
  onOpenGitHub: () => void;
}) {
  const [open, setOpen] = useState(false);
  return (
    <div className="relative flex-none">
      <button
        className="btn btn-ghost relative flex-none text-acid/90 hover:text-acid"
        onClick={() => setOpen(v => !v)}
        title="Инструменты v2.0"
      >
        <Wrench size={14} className="text-acid" />
        <span className="hidden md:inline font-mono text-[11px]">v2.0</span>
        <ChevronDown size={11} />
      </button>
      {open && (
        <>
          <div className="fixed inset-0 z-[79]" onClick={() => setOpen(false)} />
          <div className="animate-rise absolute top-9 right-0 z-[80] w-64 overflow-hidden rounded-lg border border-line bg-surface py-1 shadow-xl shadow-black/40">
            <div className="px-3 py-1 text-[10px] font-semibold text-faint uppercase tracking-wider border-b border-line/40">
              Инструменты Nano v2.0
            </div>
            <button
              className="flex w-full items-center gap-2.5 px-3 py-2 text-left text-[12px] text-ink/90 hover:bg-raised transition-colors"
              onClick={() => {
                setOpen(false);
                onOpenTree();
              }}
            >
              <ListTree size={14} className="text-acid" />
              <div>
                <div className="font-medium text-ink">Дерево плагина</div>
                <div className="text-[10px] text-faint">События, команды и права</div>
              </div>
            </button>
            <button
              className="flex w-full items-center gap-2.5 px-3 py-2 text-left text-[12px] text-ink/90 hover:bg-raised transition-colors"
              onClick={() => {
                setOpen(false);
                onOpenDiff();
              }}
            >
              <GitCompare size={14} className="text-acid" />
              <div>
                <div className="font-medium text-ink">Plugin Diff Viewer</div>
                <div className="text-[10px] text-faint">Сравнение двух JAR плагинов</div>
              </div>
            </button>
            <button
              className="flex w-full items-center gap-2.5 px-3 py-2 text-left text-[12px] text-ink/90 hover:bg-raised transition-colors"
              onClick={() => {
                setOpen(false);
                onOpenGitHub();
              }}
            >
              <GithubIcon size={14} className="text-acid" />
              <div>
                <div className="font-medium text-ink">Экспорт в GitHub</div>
                <div className="text-[10px] text-faint">Пуш проекта с PAT токеном</div>
              </div>
            </button>
          </div>
        </>
      )}
    </div>
  );
}

function ToolbarMenu({
  lang,
  hasUpdate,
  setUpdateModalOpen,
  setBugReportOpen,
}: {
  lang: "ru" | "en";
  hasUpdate: boolean;
  setUpdateModalOpen: (v: boolean) => void;
  setBugReportOpen: (v: boolean) => void;
}) {
  const [open, setOpen] = useState(false);
  return (
    <div className="relative flex-none">
      <button
        className={cn("btn btn-ghost relative flex-none", hasUpdate && "border-acid/40 text-acid")}
        onClick={() => setOpen(v => !v)}
        aria-label={t(lang, "app.updates")}
        title={t(lang, "app.updates")}
      >
        <Bell size={14} />
        <span className="hidden lg:inline">{t(lang, "app.updates")}</span>
        <ChevronDown size={11} />
        {hasUpdate && <span className="absolute top-1 right-1.5 size-[7px] rounded-full bg-acid" />}
      </button>
      {open && (
        <>
          {/* БАГ-ФИКС 1.9.10 (реальный репорт - "плашка уходит под сам GUI"):
              z-30 оказался ниже других слоёв приложения (например,
              Toasts.tsx - z-[70]) - выпадающее меню тулбара ДОЛЖНО быть
              выше буквально всего, кроме ничего (это не модалка, а
              overlay-меню, которое просят "самый верх"). */}
          <div className="fixed inset-0 z-[79]" onClick={() => setOpen(false)} />
          <div className="animate-rise absolute top-9 right-0 z-[80] w-52 overflow-hidden rounded-lg border border-line bg-surface py-1 shadow-xl shadow-black/40">
            <button
              className="flex w-full items-center justify-between px-3 py-1.5 text-left text-[12px] text-ink/90 hover:bg-raised"
              onClick={() => {
                setOpen(false);
                setUpdateModalOpen(true);
              }}
            >
              <span className="flex items-center gap-2">
                <Bell size={13} /> {t(lang, "app.updates")}
              </span>
              {hasUpdate && <span className="size-[7px] rounded-full bg-acid" />}
            </button>
            <button
              className="flex w-full items-center gap-2 px-3 py-1.5 text-left text-[12px] text-ink/90 hover:bg-raised"
              onClick={() => {
                setOpen(false);
                setBugReportOpen(true);
              }}
            >
              <MessageSquare size={13} /> {t(lang, "app.bug_report")}
            </button>
          </div>
        </>
      )}
    </div>
  );
}
