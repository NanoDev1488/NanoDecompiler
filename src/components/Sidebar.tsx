import { FileArchive, FolderOpen, MoreVertical, Trash2, X } from "lucide-react";
import { useState } from "react";
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
function JobCard({ job, selected, lang }: JobCardProps) {
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
}

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
              : (lang === "ru" ? "Перетащите .jar сюда" : "Drag .jar here")}
          </p>
          <p className="mono text-[10.5px] text-faint">
            {lang === "ru" ? "или нажмите, чтобы выбрать · только .jar" : "or click to select · .jar only"}
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
                // добавьте архив — движок разберёт его на .java
              </>
            ) : (
              <>
                // queue is empty.
                <br />
                // add an archive — engine will decompile to .java
              </>
            )}
          </p>
        )}
        {jobs.map(j => (
          <JobCard key={j.id} job={j} selected={selectedJobId === j.id} lang={lang} />
        ))}
      </div>
    </aside>
  );
}
