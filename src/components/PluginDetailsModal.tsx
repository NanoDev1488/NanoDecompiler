import { X, FolderOpen, TriangleAlert } from "lucide-react";
import { fmtBytes, fmtNum, fmtSeconds, type Job } from "../lib/model";
import { useEngine } from "../state/engine";

// НОВОЕ v1.8.0 (реальный запрос - "мини-карточка к каждому плагину слева
// с троеточием, а по клику - открыть папку результата и детальнейшая
// информация о плагине"). Данные приходят из "##ND_RESULT:{...}##" -
// служебной строки, которую движок печатает в конце ОБЫЧНОГО (не только
// --json-output) прогона - см. cli_main.cpp/parseEngineResult в engine.tsx.
// Если job ещё не завершён (или завершился с ошибкой ДО печати этой
// строки) - details будет null, показываем честное "недоступно" вместо
// пустых нулей.
export function PluginDetailsModal({ job, onClose }: { job: Job; onClose: () => void }) {
  const { openOutput } = useEngine();
  const d = job.details;

  return (
    <div
      className="fixed inset-0 z-50 grid place-items-center bg-black/60 p-4 backdrop-blur-[2px]"
      onMouseDown={e => {
        if (e.target === e.currentTarget) onClose();
      }}
    >
      <div
        role="dialog"
        aria-modal="true"
        aria-label="Информация о плагине"
        className="animate-rise flex max-h-[86vh] w-[520px] flex-col overflow-hidden rounded-2xl border border-line bg-surface shadow-2xl shadow-black/50"
      >
        <div className="flex h-11 flex-none items-center gap-2 border-b border-line px-4">
          <h2 className="mono truncate text-[13px] font-semibold text-ink">{job.fileName}</h2>
          <div className="flex-1" />
          <button className="icon-btn h-7 w-7" onClick={onClose} aria-label="Закрыть">
            <X size={14} />
          </button>
        </div>

        <div className="min-h-0 flex-1 overflow-y-auto p-4">
          {!d ? (
            <p className="mono text-[12px] text-faint">
              // подробная информация появляется после завершения декомпиляции.
              {job.status === "failed" && " этот запуск завершился с ошибкой до сбора статистики."}
            </p>
          ) : (
            <div className="flex flex-col gap-3 text-[12.5px]">
              <Row label="Платформа" value={d.stats.platform ?? "не определена"} />
              <Row label="Размер архива" value={fmtBytes(job.sizeBytes)} />
              <Row label="Классов в архиве" value={fmtNum(d.stats.classes_total)} />
              <Row
                label="Классов библиотек пропущено"
                value={
                  d.stats.library_classes_skipped > 0
                    ? `${fmtNum(d.stats.library_classes_skipped)} (${d.stats.library_names_hit.join(", ")})`
                    : "0"
                }
              />
              <Row
                label="Методов декомпилировано"
                value={`${fmtNum(d.stats.decompiled_methods)} / ${fmtNum(d.stats.total_methods)} (${d.stats.decompiled_pct.toFixed(1)}%)`}
              />
              {d.stats.fallback_methods > 0 && (
                <Row label="Откат на байткод" value={`${fmtNum(d.stats.fallback_methods)} метод(ов) - см. .java с комментарием`} />
              )}
              {job.status === "done" && <Row label="Время декомпиляции" value={fmtSeconds(job.elapsedMs / 1000)} />}
              {Object.keys(d.stats.import_conflicts).length > 0 && (
                <Row
                  label="Конфликты импортов"
                  value={`${Object.keys(d.stats.import_conflicts).length} - используются полные имена в коде`}
                />
              )}
              <div className="flex items-center gap-1.5 rounded-lg border border-line bg-bg px-3 py-2">
                {d.stats.malware_findings.length > 0 ? (
                  <>
                    <TriangleAlert size={13} className="flex-none text-err" />
                    <span className="text-err">
                      Найдено {d.stats.malware_findings.length} признак(ов) потенциально вредоносного кода - см. терминал
                    </span>
                  </>
                ) : (
                  <span className="text-dim">Признаков вредоносного кода не обнаружено (эвристика, не гарантия)</span>
                )}
              </div>
            </div>
          )}
        </div>

        <div className="flex h-11 flex-none items-center gap-2 border-t border-line px-3">
          <button
            className="btn btn-tonal h-7 flex-1 text-[11.5px]"
            disabled={job.status !== "done"}
            onClick={() => openOutput(job)}
          >
            <FolderOpen size={12} />
            Открыть папку результата
          </button>
        </div>
      </div>
    </div>
  );
}

function Row({ label, value }: { label: string; value: string }) {
  return (
    <div className="flex items-start justify-between gap-3">
      <span className="text-faint">{label}</span>
      <span className="mono text-right text-ink/90">{value}</span>
    </div>
  );
}
