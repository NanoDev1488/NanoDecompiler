import { FileArchive, Play, TriangleAlert } from "lucide-react";
import { useMemo, type ReactNode } from "react";
import { useEngine } from "../state/engine";
import { FileTree } from "./FileTree";
import { CodeView } from "./CodeView";
import { fmtBytes } from "../lib/model";

function Centered({ children }: { children: ReactNode }) {
  return (
    <div className="grid flex-1 place-items-center overflow-y-auto bg-bg p-6">
      <div className="w-full max-w-[540px] rounded-2xl border border-dashed border-line-strong bg-surface/60 px-8 py-9">
        {children}
      </div>
    </div>
  );
}

// БАГ-ФИКС: тот же ложный текст "движку нужен JRE 17+", что уже был
// исправлен в StatusBar/SettingsModal - здесь про него забыли. Движок на
// чистом C++, Java для декомпиляции не нужна вообще, это никогда не
// блокирует работу - баннер занижен по тревожности и текст честный.
function EnvBanner() {
  const { resolveEnvIssue, setSettingsOpen } = useEngine();
  return (
    <div className="flex flex-none items-center gap-3 border-b border-line bg-raised/40 px-4 py-2">
      <TriangleAlert size={14} className="flex-none text-warn/70" />
      <div className="min-w-0 flex-1">
        <p className="text-[12px] text-dim">
          Java не найдена — это не мешает декомпиляции, нужна только для ручной сборки (mvn compile) результата.
        </p>
      </div>
      <button className="btn btn-ghost h-6 text-[11px]" onClick={resolveEnvIssue}>
        Проверить снова
      </button>
      <button className="btn btn-ghost h-6 text-[11px]" onClick={() => setSettingsOpen(true)}>
        Настройки
      </button>
    </div>
  );
}

export function Workspace() {
  const { jobs, selectedJob, openFileByJob, selectFile, startQueue, openFileDialog, log, envIssue } =
    useEngine();

  const lastPhase = useMemo(() => {
    if (!selectedJob) return null;
    for (let i = log.length - 1; i >= 0; i--) {
      if (log[i].jobId === selectedJob.id && log[i].tag !== "engine") return log[i].msg;
    }
    return null;
  }, [log, selectedJob]);

  const pendingIds = useMemo(
    () => jobs.filter(j => j.status === "queued" || j.status === "canceled" || j.status === "failed").map(j => j.id),
    [jobs],
  );

  /* готово → дерево + код */
  if (selectedJob?.status === "done" && selectedJob.files) {
    const openId = openFileByJob[selectedJob.id];
    const file = selectedJob.files.find(f => f.id === openId) ?? selectedJob.files[0] ?? null;
    return (
      <div className="flex min-h-0 flex-1 flex-col">
        {envIssue && <EnvBanner />}
        <div className="flex min-h-0 flex-1">
          <FileTree files={selectedJob.files} openId={file?.id} onSelect={fid => selectFile(selectedJob.id, fid)} />
          <CodeView file={file} jobId={selectedJob.id} />
        </div>
      </div>
    );
  }

  /* идёт декомпиляция */
  if (selectedJob?.status === "running") {
    const pct = Math.round(selectedJob.progress * 100);
    return (
      <div className="flex min-h-0 flex-1 flex-col">
        {envIssue && <EnvBanner />}
        <Centered>
          <div className="flex items-baseline justify-between gap-4">
            <span className="kicker">Декомпиляция</span>
            <span className="mono text-[34px] leading-none font-semibold text-acid tabular-nums">
              {pct}
              <span className="text-[16px] text-dim">%</span>
            </span>
          </div>
          <p className="mono mt-3 truncate text-[13px] text-ink">{selectedJob.fileName}</p>
          <div className="bar mt-4">
            <i style={{ width: `${pct}%` }} />
          </div>
          <p className="mono mt-3 flex min-h-[18px] items-center gap-2 text-[11px] text-faint">
            <span className="dot bg-acid animate-pulse-dot" />
            <span className="truncate">{lastPhase ?? "подготовка…"}</span>
          </p>
          <p className="mono mt-1 text-[11px] text-faint">
            прошло {(selectedJob.elapsedMs / 1000).toFixed(1)} s · детали — в терминале ниже
          </p>
        </Centered>
      </div>
    );
  }

  /* в очереди / остановлено / ошибка */
  if (selectedJob) {
    const position = pendingIds.indexOf(selectedJob.id) + 1;
    const isNext = pendingIds[0] === selectedJob.id;
    return (
      <div className="flex min-h-0 flex-1 flex-col">
        {envIssue && <EnvBanner />}
        <Centered>
          <span className="kicker">
            {selectedJob.status === "failed"
              ? "Ошибка"
              : selectedJob.status === "canceled"
                ? "Остановлено"
                : "В очереди"}
          </span>
          <p className="mono mt-3 text-[15px] font-semibold text-ink">{selectedJob.fileName}</p>
          <p className="mono mt-1.5 text-[11.5px] text-faint">
            {fmtBytes(selectedJob.sizeBytes)}
            {selectedJob.status === "queued" && position > 0 && ` · позиция ${position}`}
            {selectedJob.status === "canceled" && " · прогресс не сохранён"}
            {selectedJob.status === "failed" && (selectedJob.error ? ` · ${selectedJob.error}` : "")}
          </p>
          <div className="mt-5 flex items-center gap-2">
            <button className="btn btn-acid" onClick={startQueue}>
              <Play size={14} />
              {isNext ? "Запустить" : pendingIds.length > 1 ? "Запустить очередь" : "Запустить"}
            </button>
            {selectedJob.classCount === null && (
              <span className="mono text-[10.5px] text-faint">архив будет просканирован при запуске</span>
            )}
          </div>
        </Centered>
      </div>
    );
  }

  /* пустое состояние */
  return (
    <div className="flex min-h-0 flex-1 flex-col">
      {envIssue && <EnvBanner />}
      <Centered>
        <div className="flex size-10 items-center justify-center rounded-xl border border-line bg-raised">
          <FileArchive size={18} className="text-dim" />
        </div>
        <p className="mt-4 text-[15px] font-semibold text-ink">
          {jobs.length === 0 ? "Пока нечего декомпилировать." : "Задача не выбрана."}
        </p>
        <p className="mt-2 max-w-[420px] text-[12.5px] leading-relaxed text-dim">
          Добавьте .jar слева. Движок распакует архив, восстановит .java и покажет дерево классов
          на этом месте.
        </p>
        <div className="mt-5">
          <button className="btn btn-tonal" onClick={openFileDialog}>
            <FileArchive size={14} />
            Выбрать .jar
          </button>
        </div>
      </Centered>
    </div>
  );
}
