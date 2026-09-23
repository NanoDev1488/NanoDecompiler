import { Check, Loader2, TriangleAlert } from "lucide-react";
import { useEngine } from "../state/engine";
import { fmtNum, fmtSeconds } from "../lib/model";
import { cn } from "../utils/cn";

export function StatusBar() {
  const { runningJob, runningElapsed, selectedJob, envIssue, resolveEnvIssue, javaEnv, mavenEnv, guiVersion, settings } =
    useEngine();

  const pct = runningJob ? Math.round(runningJob.progress * 100) : 0;

  return (
    <footer className="mono flex h-7 flex-none items-center gap-4 border-t border-line bg-surface px-3 text-[11px] text-faint select-none">
      {/* окружение */}
      <div className="flex items-center gap-3">
        {javaEnv === null ? (
          <span className="flex items-center gap-1 opacity-60">
            <Loader2 size={11} className="animate-spin" />
            java: проверяю…
          </span>
        ) : envIssue ? (
          <button
            onClick={resolveEnvIssue}
            className="flex items-center gap-1 text-faint hover:text-ink"
            title="Java не нужна для декомпиляции - только для ручной сборки (mvn compile). Проверить снова"
          >
            <TriangleAlert size={11} className="text-warn/70" />
            java: не найдена
          </button>
        ) : (
          <span
            className="flex items-center gap-1"
            title={javaEnv.inPath === false ? `${javaEnv.text ?? ""} - найдена по внутреннему пути, НЕ в системном PATH` : javaEnv.text}
          >
            <Check size={11} className="text-acid" />
            {javaEnv.text ?? "java"}
            {/* БАГ-ФИКС v1.9.6 (реальная жалоба - "Maven не добавляется в
                PATH, но приложение всё равно пишет, что установлен"):
                приложение реально ЗАПУСКАЕТ бинарник по найденному пути
                (не просто проверяет наличие файла) - для СВОИХ целей это
                честная правда, но раньше выглядело идентично системной
                установке, хотя `mvn`/`java` в обычном терминале пользователя
                работать не будут. Отдельная явная пометка вместо тихого
                "работает точно так же". */}
            {javaEnv.inPath === false && <span className="text-warn/70">(не в PATH)</span>}
          </span>
        )}
        <span
          className={cn("flex items-center gap-1", !mavenEnv?.ok && "opacity-40")}
          title={
            mavenEnv?.ok && mavenEnv.inPath === false
              ? `${mavenEnv.text ?? ""} - найден по внутреннему пути, НЕ в системном PATH`
              : mavenEnv?.text
          }
        >
          <Check size={11} className={mavenEnv?.ok ? "text-acid" : "text-faint"} />
          {mavenEnv?.ok ? (mavenEnv.text ?? "maven") : "maven: не найден"}
          {mavenEnv?.ok && mavenEnv.inPath === false && <span className="text-warn/70">(не в PATH)</span>}
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
          {fmtNum(selectedJob.classCount ?? 0)} классов в архиве · {fmtSeconds(selectedJob.elapsedMs)} ·{" "}
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

      {/* БАГ-ФИКС: "GUI v2.1.0 · build a3f9c2" было захардкожено демо-
          заглушкой, включая полностью выдуманный git-хэш, который взять
          неоткуда без доступа к реальному репозиторию - честнее показать
          только реальную версию (app.getVersion(), см. gui:version в
          main.ts), без придуманного build-хэша. */}
      <span className="hidden md:inline">GUI v{guiVersion ?? "…"}</span>
    </footer>
  );
}
