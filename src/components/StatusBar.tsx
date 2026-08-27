import { Check, TriangleAlert } from "lucide-react";
import { useEngine } from "../state/engine";
import { fmtNum, fmtSeconds } from "../lib/model";
import { cn } from "../utils/cn";

export function StatusBar() {
  const { runningJob, runningElapsed, selectedJob, envIssue, resolveEnvIssue, settings } = useEngine();

  const pct = runningJob ? Math.round(runningJob.progress * 100) : 0;

  return (
    <footer className="mono flex h-7 flex-none items-center gap-4 border-t border-line bg-surface px-3 text-[11px] text-faint select-none">
      {/* окружение */}
      <div className="flex items-center gap-3">
        {envIssue ? (
          <button
            onClick={resolveEnvIssue}
            className="flex items-center gap-1 text-err hover:text-ink"
            title="Проверить снова"
          >
            <TriangleAlert size={11} />
            java: не найдена
          </button>
        ) : (
          <span className="flex items-center gap-1">
            <Check size={11} className="text-acid" />
            java 21.0.3
          </span>
        )}
        <span className={cn("flex items-center gap-1", envIssue && "opacity-40")}>
          <Check size={11} className={envIssue ? "text-faint" : "text-acid"} />
          maven 3.9.6
        </span>
        <span className="hidden text-line-strong xl:inline">|</span>
        <span className="hidden max-w-[220px] truncate xl:inline">{settings.outputDir}</span>
      </div>

      <div className="flex-1" />

      {/* прогресс / итоги */}
      {runningJob ? (
        <span className="flex items-center gap-2 text-dim">
          <span className="dot bg-acid animate-pulse-dot" />
          <span className="max-w-[240px] truncate">{runningJob.fileName}</span>
          <span className="text-acid">{pct}%</span>
          {runningElapsed !== null && <span>{(runningElapsed / 1000).toFixed(1)} s</span>}
        </span>
      ) : selectedJob?.status === "done" ? (
        <span className="text-dim">
          {fmtNum(selectedJob.classCount ?? 0)} классов · {fmtSeconds(selectedJob.elapsedMs)} ·{" "}
          <span className="text-acid">0 ошибок</span>
        </span>
      ) : (
        <span className="flex items-center gap-1.5">
          <span className="dot dot-hollow size-[6px]" />
          движок свободен
        </span>
      )}

      <span className="hidden items-center gap-3 lg:flex">
        <span className="text-line-strong">|</span>
        <span>
          <span className="kbd mr-1">Ctrl K</span>команды
        </span>
      </span>

      <span className="hidden md:inline">GUI v2.1.0 · build a3f9c2</span>
    </footer>
  );
}
