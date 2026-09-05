import { FileArchive, FolderOpen, Trash2, X } from "lucide-react";
import { useState } from "react";
import { useEngine } from "../state/engine";
import { useResizeDrag } from "../lib/useResize";
import { fmtBytes, fmtNum, fmtSeconds, type Job } from "../lib/model";
import { cn } from "../utils/cn";

function StatusDot({ job }: { job: Job }) {
  if (job.status === "running") return <span className="dot animate-pulse-dot bg-acid" />;
  if (job.status === "done") return <span className="dot bg-acid" />;
  if (job.status === "failed") return <span className="dot bg-err" />;
  return <span className="dot dot-hollow" />;
}

function statusLine(job: Job): string {
  switch (job.status) {
    case "queued":
      return job.classCount === null ? "в очереди · архив не сканирован" : "в очереди";
    case "running":
      return "декомпиляция…";
    case "done":
      return `${fmtNum(job.classCount ?? 0)} классов · ${fmtSeconds(job.elapsedMs)}`;
    case "canceled":
      return "остановлено — запустите снова";
    case "failed":
      return job.error ?? "ошибка движка";
  }
}

function JobCard({ job, selected }: { job: Job; selected: boolean }) {
  const { selectJob, cancelJob, removeJob, openOutput } = useEngine();

  return (
    <div
      role="button"
      tabIndex={0}
      onClick={() => selectJob(job.id)}
      onKeyDown={e => e.key === "Enter" && selectJob(job.id)}
      className={cn(
        "w-full cursor-pointer rounded-xl border bg-bg p-2.5 text-left transition-colors duration-150",
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

        {job.status === "done" && (
          <button
            className="icon-btn h-6 w-6 rounded-md"
            title="Открыть папку результата"
            onClick={e => {
              e.stopPropagation();
              openOutput(job);
            }}
          >
            <FolderOpen size={12} />
          </button>
        )}
        <button
          className="icon-btn h-6 w-6 rounded-md"
          title={
            job.status === "running"
              ? "Остановить"
              : job.status === "queued"
                ? "Убрать из очереди"
                : "Удалить из списка"
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

      <div className="mono mt-1.5 pl-[15px] text-[11px] text-faint">
        {fmtBytes(job.sizeBytes)}
        {job.classCount !== null && ` · ${fmtNum(job.classCount)} классов`}
      </div>
      <div
        className={cn(
          "mt-0.5 pl-[15px] text-[11.5px]",
          job.status === "failed" ? "text-err" : job.status === "done" ? "text-dim" : "text-faint",
        )}
      >
        {statusLine(job)}
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
  const { jobs, selectedJobId, addFiles, openFileDialog, clearQueue, sidebarWidth, setSidebarWidth } = useEngine();
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
        <span className="kicker">Входные архивы</span>
        <span className="chip h-[18px] px-1.5 text-[10px]">{jobs.length}</span>
        <div className="flex-1" />
        <button
          className="icon-btn h-6 w-6 rounded-md"
          title="Очистить список"
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
            {dragActive ? "Отпускайте — добавлю в очередь" : "Перетащите .jar сюда"}
          </p>
          <p className="mono text-[10.5px] text-faint">или нажмите, чтобы выбрать · только .jar</p>
        </div>
      </div>

      <div className="flex min-h-0 flex-1 flex-col gap-2 overflow-y-auto px-2.5 pb-2.5">
        {jobs.length === 0 && (
          <p className="mono px-1 pt-2 text-[11px] leading-relaxed text-faint">
            // очередь пуста.
            <br />
            // добавьте архив — движок разберёт его на .java
          </p>
        )}
        {jobs.map(j => (
          <JobCard key={j.id} job={j} selected={selectedJobId === j.id} />
        ))}
      </div>
    </aside>
  );
}
