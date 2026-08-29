import { Bell, FolderOpen, Play, Settings2, Square } from "lucide-react";
import { useEngine } from "../state/engine";
import { cn } from "../utils/cn";

export function AppHeader() {
  const {
    runningJob,
    runningElapsed,
    queuedCount,
    envIssue,
    engineVersion,
    updateInfo,
    startQueue,
    stopRunning,
    openFileDialog,
    setSettingsOpen,
    setUpdateModalOpen,
    setPaletteOpen,
  } = useEngine();

  const running = runningJob !== null;
  const engineLabel = engineVersion?.replace(/^NanoDecompiler /, "") ?? "engine";
  const hasUpdate = updateInfo.kind === "engine" || updateInfo.kind === "client";

  return (
    <div className="flex h-12 flex-none items-center gap-3 border-b border-line bg-surface px-3">
      {/* статус движка — ассист-чип с точкой, наследие gui_neon.py */}
      <div
        className={cn(
          "chip max-w-[46vw]",
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
            ? "окружение: нет Java"
            : running
              ? `${engineLabel} — занят${runningElapsed !== null ? ` · ${(runningElapsed / 1000).toFixed(1)} s` : ""}`
              : `${engineLabel} — готов`}
        </span>
        {envIssue && (
          <button
            className="mono -mr-1 rounded px-1 text-[10px] underline decoration-dotted underline-offset-2 hover:text-ink"
            onClick={() => setSettingsOpen(true)}
          >
            починить
          </button>
        )}
      </div>

      <div className="flex-1" />

      <button className="btn btn-tonal" onClick={openFileDialog}>
        <FolderOpen size={14} />
        Открыть .jar
        <span className="kbd ml-1 hidden lg:inline">Ctrl O</span>
      </button>

      {running ? (
        <button className="btn btn-err" onClick={stopRunning}>
          <Square size={13} />
          Остановить
        </button>
      ) : (
        <button className="btn btn-acid" onClick={startQueue} disabled={queuedCount === 0}>
          <Play size={14} />
          Запустить
          {queuedCount > 0 && (
            <span className="mono grid h-[18px] min-w-[18px] place-items-center rounded-full bg-black/25 px-1 text-[10px] font-semibold">
              {queuedCount}
            </span>
          )}
        </button>
      )}

      <button
        className="btn btn-ghost hidden md:inline-flex"
        onClick={() => setPaletteOpen(true)}
        title="Палитра команд"
      >
        <span className="kbd">Ctrl K</span>
        <span className="text-faint">команды</span>
      </button>

      <button
        className="icon-btn relative"
        onClick={() => setUpdateModalOpen(true)}
        aria-label="Обновления"
        title="Обновления"
      >
        <Bell size={16} />
        {hasUpdate && <span className="absolute top-1 right-1 size-[7px] rounded-full bg-acid" />}
      </button>

      <button
        className="icon-btn"
        onClick={() => setSettingsOpen(true)}
        aria-label="Настройки"
        title="Настройки (Ctrl ,)"
      >
        <Settings2 size={16} />
      </button>
    </div>
  );
}
