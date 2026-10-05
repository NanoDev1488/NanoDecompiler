import {
  Archive,
  ArrowDown,
  FileArchive,
  FolderOpen,
  Loader2,
  MoreVertical,
  PackageCheck,
  PackageOpen,
  Play,
  ShieldAlert,
  Trash2,
  X,
} from "lucide-react";
import { memo, useState } from "react";
import { useEngine } from "../state/engine";
import { useResizeDrag } from "../lib/useResize";
import { fmtBytes, fmtNum, fmtSeconds, type Job } from "../lib/model";
import { t, type Lang } from "../lib/i18n";
import { cn } from "../utils/cn";
import { PluginDetailsModal } from "./PluginDetailsModal";

function StatusDot({ job }: { job: Job }) {
  if (job.status === "running") return <span className="dot animate-pulse-dot bg-acid" />;
  if (job.status === "done") return <span className="dot bg-acid" />;
  if (job.status === "failed") return <span className="dot bg-err" />;
  return <span className="dot dot-hollow" />;
}

function fmtEta(seconds: number, lang: Lang): string {
  if (seconds <= 0) return lang === "ru" ? "завершается..." : "finishing...";
  if (seconds < 60) return `~${Math.ceil(seconds)} ${lang === "ru" ? "сек" : "s"}`;
  const m = Math.floor(seconds / 60);
  const s = Math.ceil(seconds % 60);
  return `~${m} ${lang === "ru" ? "мин" : "m"} ${s} ${lang === "ru" ? "сек" : "s"}`;
}

function statusLine(job: Job, lang: Lang): string {
  switch (job.status) {
    case "queued":
      return job.classCount === null
        ? (lang === "ru" ? "в очереди · архив не сканирован" : "in queue · archive not scanned")
        : t(lang, "sidebar.status.queued");
    case "running":
      return t(lang, "sidebar.status.running");
    case "done":
      return `${fmtNum(job.classCount ?? 0)} ${lang === "ru" ? "классов в архиве" : "classes in archive"} · ${fmtSeconds(job.elapsedMs)}`;
    case "canceled":
      return lang === "ru" ? "остановлено — запустите снова" : "stopped — run again";
    case "failed":
      return job.error ?? (lang === "ru" ? "ошибка движка" : "engine error");
  }
}

type JobCardProps = { job: Job; selected: boolean; lang: Lang };
const JobCard = memo(function JobCard({ job, selected, lang }: JobCardProps) {
  const { selectJob, cancelJob, removeJob, openOutput } = useEngine();
  const [menuOpen, setMenuOpen] = useState(false);
  const [detailsOpen, setDetailsOpen] = useState(false);

  return (
    <div
      role="button"
      tabIndex={0}
      onClick={() => selectJob(job.id)}
      onKeyDown={e => e.key === "Enter" && selectJob(job.id)}
      className={cn(
        "relative w-full cursor-pointer rounded-xl border bg-bg p-2.5 text-left transition-colors duration-150",
        selected && job.status !== "running"
          ? "border-line-strong bg-raised"
          : job.status === "running"
            ? "border-acid/35 bg-raised"
            : "border-line hover:border-line-strong",
      )}
    >
      <div className="flex items-center gap-2">
        <StatusDot job={job} />
        <span className="mono flex-1 truncate text-[12px] font-medium text-ink">{job.fileName}</span>

        <button
          className="icon-btn h-6 w-6 rounded-md"
          title={lang === "ru" ? "Ещё" : "More"}
          onClick={e => {
            e.stopPropagation();
            setMenuOpen(v => !v);
          }}
        >
          <MoreVertical size={12} />
        </button>
        <button
          className="icon-btn h-6 w-6 rounded-md"
          title={
            job.status === "running"
              ? t(lang, "app.stop")
              : job.status === "queued"
                ? (lang === "ru" ? "Убрать из очереди" : "Remove from queue")
                : t(lang, "sidebar.remove")
          }
          onClick={e => {
            e.stopPropagation();
            if (job.status === "done") removeJob(job.id);
            else cancelJob(job.id);
          }}
        >
          <X size={12} />
        </button>
      </div>

      {menuOpen && (
        <>
          {/* клик по фону закрывает меню, не выделяя карточку под ним */}
          <div className="fixed inset-0 z-[79]" onClick={e => (e.stopPropagation(), setMenuOpen(false))} />
          <div
            className="animate-rise absolute top-9 right-2.5 z-[80] w-52 overflow-hidden rounded-lg border border-line bg-surface py-1 shadow-xl shadow-black/40"
            onClick={e => e.stopPropagation()}
          >
            <button
              className="flex w-full items-center gap-2 px-3 py-1.5 text-left text-[12px] text-ink/90 hover:bg-raised disabled:opacity-40"
              disabled={job.status !== "done"}
              onClick={() => {
                setMenuOpen(false);
                openOutput(job);
              }}
            >
              <FolderOpen size={12} />
              {t(lang, "sidebar.open_output")}
            </button>
            <button
              className="flex w-full items-center gap-2 px-3 py-1.5 text-left text-[12px] text-ink/90 hover:bg-raised"
              onClick={() => {
                setMenuOpen(false);
                setDetailsOpen(true);
              }}
            >
              <MoreVertical size={12} className="rotate-90" />
              {t(lang, "sidebar.details")}
            </button>
          </div>
        </>
      )}
      {detailsOpen && <PluginDetailsModal job={job} onClose={() => setDetailsOpen(false)} />}

      <div className="mono mt-1.5 pl-[15px] text-[11px] text-faint">
        {fmtBytes(job.sizeBytes)}
        {job.classCount !== null && ` · ${fmtNum(job.classCount)} ${lang === "ru" ? "классов" : "classes"}`}
      </div>
      <div
        className={cn(
          "mt-0.5 pl-[15px] text-[11.5px]",
          job.status === "failed" ? "text-err" : job.status === "done" ? "text-dim" : "text-faint",
        )}
      >
        {statusLine(job, lang)}
      </div>

      {job.status === "running" && (
        <div className="mt-2 pl-[15px]">
          <div className="bar">
            <i style={{ width: `${Math.round(job.progress * 100)}%` }} />
          </div>
          <div className="mono mt-1 flex justify-between text-[10.5px] text-faint">
            <span>{Math.round(job.progress * 100)}%</span>
            <span>{(job.elapsedMs / 1000).toFixed(1)} s</span>
          </div>
        </div>
      )}
    </div>
  );
});

const ArchiveCard = memo(function ArchiveCard({ job, selected, lang }: JobCardProps) {
  const { selectJob, removeArchive, decompileArchivePlugins, addJarPaths } = useEngine();
  const prog = job.archiveProgress;
  const isExtracting = job.status === "running";
  const isDone = job.status === "done";
  const isFailed = job.status === "failed";
  const plugins = job.extractedPlugins ?? [];
  const skipped = job.skippedServerCores ?? [];

  return (
    <div
      className={cn(
        "relative w-full rounded-xl border bg-bg p-3 transition-colors duration-150",
        selected ? "border-line-strong bg-raised" : "border-line hover:border-line-strong",
        isExtracting && "border-amber-500/40 bg-amber-500/5",
      )}
      onClick={() => selectJob(job.id)}
    >
      {/* Шапка архива */}
      <div className="flex items-center gap-2">
        <div className="flex h-7 w-7 flex-none items-center justify-center rounded-lg bg-surface border border-line">
          {isExtracting ? (
            <PackageOpen size={15} className="animate-bounce text-amber-400" />
          ) : isDone ? (
            <PackageCheck size={15} className="text-acid" />
          ) : (
            <Archive size={15} className="text-faint" />
          )}
        </div>
        <div className="min-w-0 flex-1">
          <div className="mono truncate text-[12px] font-semibold text-ink" title={job.fileName}>
            {job.fileName}
          </div>
          <div className="mono text-[10.5px] text-faint">
            {fmtBytes(job.sizeBytes)} · {lang === "ru" ? "Архив" : "Archive"}
          </div>
        </div>
        <button
          className="icon-btn h-6 w-6 rounded-md hover:bg-err/10 hover:text-err"
          title={lang === "ru" ? "Удалить архив и временные файлы" : "Delete archive & temp files"}
          onClick={e => {
            e.stopPropagation();
            removeArchive(job);
          }}
        >
          <X size={12} />
        </button>
      </div>

      {/* Анимация и прогресс распаковки во временную папку */}
      {isExtracting && (
        <div className="mt-3 rounded-lg border border-amber-500/20 bg-amber-500/10 p-2.5">
          <div className="flex items-center gap-2 text-[11.5px] font-medium text-amber-300">
            <Loader2 size={13} className="animate-spin text-amber-400" />
            <span>{t(lang, "sidebar.archive_extracting")}</span>
          </div>

          {/* Индикатор прогресса */}
          <div className="bar mt-2 bg-black/40">
            <i
              className="bg-amber-400 transition-all duration-200"
              style={{ width: `${Math.max(5, Math.round(prog?.percent ?? job.progress * 100))}%` }}
            />
          </div>

          <div className="mono mt-1.5 flex items-center justify-between text-[10.5px] text-amber-300/80">
            <span>{Math.round(prog?.percent ?? job.progress * 100)}%</span>
            {prog?.etaSeconds !== undefined && prog.etaSeconds > 0 && (
              <span className="font-semibold text-amber-200">
                {lang === "ru" ? "Осталось" : "ETA"}: {fmtEta(prog.etaSeconds, lang)}
              </span>
            )}
            {prog?.speedBytesPerSec !== undefined && prog.speedBytesPerSec > 0 && (
              <span>{fmtBytes(prog.speedBytesPerSec)}/s</span>
            )}
          </div>

          {prog?.currentFile && (
            <div className="mono mt-1.5 truncate text-[10px] text-faint" title={prog.currentFile}>
              {lang === "ru" ? "Извлечение" : "Extracting"}: {prog.currentFile}
            </div>
          )}

          {job.tempDir && (
            <div className="mono mt-1 truncate text-[9.5px] text-faint/70" title={job.tempDir}>
              📁 {job.tempDir}
            </div>
          )}
        </div>
      )}

      {/* Ошибка распаковки */}
      {isFailed && (
        <div className="mt-2 rounded-lg border border-err/30 bg-err/10 p-2 text-[11px] text-err">
          {job.error || (lang === "ru" ? "Ошибка распаковки архива" : "Archive extraction error")}
        </div>
      )}

      {/* Завершено: список найденных плагинов со стрелочкой вниз */}
      {isDone && (
        <div className="mt-2.5">
          {/* Верхняя плашка с кнопкой "Декомпилировать все" */}
          <div className="flex items-center justify-between gap-1 pb-1.5 border-b border-line/60">
            <span className="text-[11px] font-medium text-dim">
              {lang === "ru" ? `Найдено плагинов: ${plugins.length}` : `Plugins found: ${plugins.length}`}
            </span>
            {plugins.length > 0 && (
              <button
                className="btn-primary flex items-center gap-1 rounded px-1.5 py-0.5 text-[10px] font-medium shadow-none h-6 whitespace-nowrap"
                onClick={e => {
                  e.stopPropagation();
                  decompileArchivePlugins(job);
                }}
              >
                <Play size={9} className="fill-current" />
                {t(lang, "sidebar.decompile_all")}
              </button>
            )}
          </div>

          {/* Стрелочка от архива вниз к плагинам */}
          {plugins.length > 0 && (
            <div className="flex items-center gap-1.5 py-1.5 text-acid">
              <div className="flex h-5 w-5 items-center justify-center rounded-full bg-acid/15 border border-acid/30">
                <ArrowDown size={11} />
              </div>
              <span className="mono text-[10px] font-semibold uppercase tracking-wider text-acid">
                {t(lang, "sidebar.archive_plugins_found")} ({plugins.length})
              </span>
            </div>
          )}

          {/* Список плагинов */}
          <div className="flex flex-col gap-1.5 pl-2 border-l-2 border-acid/30">
            {plugins.map(p => (
              <div
                key={p.jarPath}
                className="group flex flex-col gap-1 rounded-lg border border-line bg-surface/80 p-2 hover:border-acid/40 hover:bg-raised transition-colors"
              >
                <div className="flex items-start justify-between gap-1">
                  <div className="min-w-0 flex-1">
                    <div className="mono truncate text-[11.5px] font-medium text-ink" title={p.fileName}>
                      {p.pluginName ? `${p.pluginName}` : p.fileName}
                    </div>
                    {p.pluginName && p.pluginName !== p.fileName && (
                      <div className="mono truncate text-[10px] text-faint" title={p.fileName}>
                        {p.fileName}
                      </div>
                    )}
                  </div>
                  <button
                    className="btn flex flex-none items-center gap-1 rounded bg-acid/15 px-2 py-0.5 text-[10.5px] font-medium text-acid hover:bg-acid/25 border border-acid/30 transition-colors"
                    title={lang === "ru" ? "Декомпилировать этот плагин" : "Decompile this plugin"}
                    onClick={e => {
                      e.stopPropagation();
                      addJarPaths([p.jarPath]);
                    }}
                  >
                    <Play size={9} className="fill-current" />
                    {t(lang, "sidebar.decompile")}
                  </button>
                </div>

                <div className="flex flex-wrap items-center gap-1 text-[10px]">
                  <span className="rounded bg-acid/10 px-1 py-0.2 text-[9.5px] font-medium text-acid border border-acid/20">
                    {p.platform}
                  </span>
                  <span className="mono text-faint">{fmtBytes(p.sizeBytes)}</span>
                  {p.classCount !== null && (
                    <span className="mono text-faint">· {p.classCount} {lang === "ru" ? "кл." : "cls"}</span>
                  )}
                  {p.pluginAuthor && (
                    <span className="mono text-faint/80 truncate max-w-[100px]" title={p.pluginAuthor}>
                      · {p.pluginAuthor}
                    </span>
                  )}
                </div>
              </div>
            ))}

            {plugins.length === 0 && (
              <div className="py-2 text-[11px] text-faint italic">
                {lang === "ru" ? "В архиве не найдено подходящих .jar плагинов" : "No valid .jar plugins found in archive"}
              </div>
            )}
          </div>

          {/* Пропущенные ядра сервера */}
          {skipped.length > 0 && (
            <div className="mt-2.5 rounded-lg border border-amber-500/25 bg-amber-500/5 p-2">
              <div className="flex items-center gap-1.5 text-[10.5px] font-semibold text-amber-400">
                <ShieldAlert size={12} />
                <span>{t(lang, "sidebar.core_skipped")} ({skipped.length})</span>
              </div>
              <div className="mt-1 flex flex-col gap-1 text-[10px] text-faint">
                {skipped.map(c => (
                  <div
                    key={c.jarPath}
                    className="mono truncate"
                    title={`${c.fileName} (${fmtBytes(c.sizeBytes)})${c.coreReason ? ` — ${c.coreReason}` : ""}`}
                  >
                    <span className="text-ink/80 font-medium">{c.fileName}</span>
                    <span className="text-faint/70"> ({fmtBytes(c.sizeBytes)})</span>
                    {c.coreReason && <span className="text-amber-400/80"> — {c.coreReason}</span>}
                  </div>
                ))}
              </div>
            </div>
          )}
        </div>
      )}
    </div>
  );
});

export function Sidebar() {
  const { jobs, selectedJobId, settings, addFiles, openFileDialog, clearQueue, sidebarWidth, setSidebarWidth } = useEngine();
  const lang = settings.language;
  const [dragActive, setDragActive] = useState(false);
  const onResizeDown = useResizeDrag("x", sidebarWidth, setSidebarWidth, 220, 480);

  return (
    <aside className="relative flex flex-none flex-col border-r border-line bg-surface" style={{ width: sidebarWidth }}>
      <div
        onPointerDown={onResizeDown}
        className="group absolute top-0 right-[-3px] z-10 h-full w-[6px] cursor-col-resize select-none"
      >
        <div className="absolute inset-y-0 left-1/2 w-px -translate-x-1/2 bg-line-strong opacity-0 transition-opacity group-hover:opacity-100 group-active:bg-acid group-active:opacity-100" />
      </div>
      <div className="flex h-9 flex-none items-center gap-2 border-b border-line px-3">
        <span className="kicker">{lang === "ru" ? "Входные архивы" : "Input archives"}</span>
        <span className="chip h-[18px] px-1.5 text-[10px]">{jobs.length}</span>
        <div className="flex-1" />
        <button
          className="icon-btn h-6 w-6 rounded-md"
          title={lang === "ru" ? "Очистить список" : "Clear list"}
          onClick={clearQueue}
          disabled={jobs.length === 0}
        >
          <Trash2 size={12} />
        </button>
      </div>

      <div className="p-2.5">
        <div
          role="button"
          tabIndex={0}
          onClick={openFileDialog}
          onKeyDown={e => e.key === "Enter" && openFileDialog()}
          onDragOver={e => {
            e.preventDefault();
            setDragActive(true);
          }}
          onDragLeave={() => setDragActive(false)}
          onDrop={e => {
            e.preventDefault();
            setDragActive(false);
            if (e.dataTransfer.files.length) addFiles(e.dataTransfer.files);
          }}
          className={cn(
            "flex cursor-pointer flex-col items-center gap-1.5 rounded-xl border border-dashed px-4 py-5 text-center transition-colors duration-150",
            dragActive
              ? "border-acid/60 bg-acid/8"
              : "border-line hover:border-line-strong hover:bg-raised/50",
          )}
        >
          <FileArchive size={18} className={dragActive ? "text-acid" : "text-faint"} />
          <p className="text-[12.5px] font-medium text-ink/90">
            {dragActive
              ? (lang === "ru" ? "Отпускайте — добавлю в очередь" : "Drop to add to queue")
              : t(lang, "sidebar.drag_drop_archive")}
          </p>
          <p className="mono text-[10.5px] text-faint">
            {t(lang, "sidebar.drag_drop_archive_desc")}
          </p>
        </div>
      </div>

      <div className="flex min-h-0 flex-1 flex-col gap-2 overflow-y-auto px-2.5 pb-2.5">
        {jobs.length === 0 && (
          <p className="mono px-1 pt-2 text-[11px] leading-relaxed text-faint">
            {lang === "ru" ? (
              <>
                // очередь пуста.
                <br />
                // добавьте архив (.jar, .zip, .tar.gz, .7z, .rar)
              </>
            ) : (
              <>
                // queue is empty.
                <br />
                // add archive (.jar, .zip, .tar.gz, .7z, .rar)
              </>
            )}
          </p>
        )}
        {jobs.map(j => (
          j.isArchive ? (
            <ArchiveCard key={j.id} job={j} selected={selectedJobId === j.id} lang={lang} />
          ) : (
            <JobCard key={j.id} job={j} selected={selectedJobId === j.id} lang={lang} />
          )
        ))}
      </div>
    </aside>
  );
}

