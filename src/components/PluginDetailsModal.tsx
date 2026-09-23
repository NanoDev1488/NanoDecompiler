import { ExternalLink, X, FolderOpen, Star, TriangleAlert } from "lucide-react";
import { useState, type ReactNode } from "react";
import { fmtBytes, fmtNum, fmtSeconds, joinOutDir, type Job } from "../lib/model";
import { useEngine } from "../state/engine";

// БАГ-ФИКС v1.8.0 «срочный CI-фикс» (реальный сбой сборки на macOS/Linux/
// Windows-раннерах - "Github" is not exported by lucide-react): брендовые
// иконки логотипов (в отличие от обычных UI-иконок вроде X/Star) - именно
// то, что чаще всего переименовывают или убирают между мажорными версиями
// icon-библиотек, а `lucide-react` запинен диапазоном "^1.34.0" (caret -
// любая 1.x), так что CI мог подтянуть версию, где `Github` уже нет.
// Чтобы больше не зависеть от того, есть ли конкретная брендовая иконка в
// конкретной версии lucide-react, рисуем её сами - маленький инлайн-SVG с
// тем же API (`size`/`className`), что и у lucide-иконок, так что менять
// остальной JSX ниже не пришлось.
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

// НОВОЕ v1.8.0 (реальный запрос - "мини-карточка к каждому плагину слева
// с троеточием, а по клику - открыть папку результата и детальнейшая
// информация о плагине"). Данные приходят из "##ND_RESULT:{...}##" -
// служебной строки, которую движок печатает в конце ОБЫЧНОГО (не только
// --json-output) прогона - см. cli_main.cpp/parseEngineResult в engine.tsx.
// Если job ещё не завершён (или завершился с ошибкой ДО печати этой
// строки) - details будет null, показываем честное "недоступно" вместо
// пустых нулей.
export function PluginDetailsModal({ job, onClose }: { job: Job; onClose: () => void }) {
  const { openOutput, toast, addJarPaths } = useEngine();
  const d = job.details;
  // НОВОЕ v1.7.6: поиск похожих репозиториев на GitHub по имени плагина
  // и автору из plugin.yml (см. jarSummary.ts/main.ts::github:searchSimilar).
  const [ghLoading, setGhLoading] = useState(false);
  // НОВОЕ v1.8.4 - было: локальный стейт для кнопки "Отправить отчёт".
  // Кнопка убрана в блоке 1.9.6 (см. HANDOFF, п.15) - с v1.9.5 отправка
  // fallback_contexts уходит АВТОМАТИЧЕСКИ при telemetryEnabled, ручная
  // кнопка стала избыточной и путала пользователя.
  const [ghResults, setGhResults] = useState<
    { name: string; fullName: string; url: string; description: string | null; stars: number }[] | null
  >(null);
  const searchGithub = () => {
    setGhLoading(true);
    window.nano
      .searchGithubSimilar(job.pluginName ?? null, job.pluginAuthor ?? null)
      .then(r => {
        if (!r.ok) {
          toast(r.error ?? "Не удалось выполнить поиск на GitHub", "err");
          setGhResults([]);
        } else {
          setGhResults(r.results ?? []);
        }
      })
      .finally(() => setGhLoading(false));
  };

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
                    ? (
                        <LibraryNamesValue
                          count={d.stats.library_classes_skipped}
                          names={d.stats.library_names_hit}
                        />
                      )
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
              {/* БАГ-ФИКС v1.8.2 - см. комментарий у полей в model.ts. */}
              {d.stats.synthetic_switchmap_classes_hidden > 0 && (
                <Row
                  label="Синтетических switchmap-классов скрыто"
                  value={`${fmtNum(d.stats.synthetic_switchmap_classes_hidden)} (компиляторные helper-классы для switch по enum)`}
                />
              )}
              {d.stats.junk_catches_removed > 0 && (
                <Row label="Пустых catch-блоков вычищено" value={fmtNum(d.stats.junk_catches_removed)} />
              )}
              {/* БАГ-ФИКС v1.8.4 (реальная жалоба - "время декомпиляции
                  врёт, всегда меньше секунды"): fmtSeconds(ms) САМА делит
                  на 1000 внутри (см. model.ts) - тут ЕЩЁ РАЗ делили ДО
                  вызова, двойное деление на 1000 схлопывало любое реальное
                  время (несколько тысяч мс) в тысячные доли секунды,
                  округлявшиеся до "0.00 s". Sidebar.tsx рядом вызывал
                  fmtSeconds(job.elapsedMs) без лишнего деления - и был прав. */}
              {job.status === "done" && <Row label="Время декомпиляции" value={fmtSeconds(job.elapsedMs)} />}
              {Object.keys(d.stats.import_conflicts).length > 0 && (
                <div className="flex flex-col gap-1.5 rounded-lg border border-line bg-bg px-3 py-2">
                  <p className="kicker">
                    Конфликты импортов ({Object.keys(d.stats.import_conflicts).length}) - используются полные имена в коде
                  </p>
                  {/* БАГ-ФИКС v1.8.2 (тот же принцип, что и с malware_findings
                      выше - движок УЖЕ присылает, КАКИЕ именно классы
                      конфликтуют и с чем, а не только их количество). */}
                  <ul className="mono flex flex-col gap-0.5 pl-1 text-[11px] text-dim">
                    {Object.entries(d.stats.import_conflicts).map(([simple, dotted]) => (
                      <li key={simple} className="truncate" title={dotted.join(", ")}>
                        <span className="text-ink/90">{simple}</span>: {dotted.join(", ")}
                      </li>
                    ))}
                  </ul>
                </div>
              )}
              {/* НОВОЕ v1.8.3 (HANDOFF_URGENT п.6 - "декомпиляция вложенных
                  jar"): полная РЕКУРСИЯ внутри движка не реализована (это
                  меняло бы C++ и требовало отдельной большой регрессии), но
                  движок УЖЕ извлекает найденные вложенные jar как обычный
                  ресурс на диск (см. process_jar.cpp) - так что вместо
                  рекурсии просто предлагаем поставить УЖЕ извлечённый файл
                  новым job'ом в ту же очередь, через тот же проверенный
                  addJarPaths(), которым пользуется обычное "Открыть .jar". */}
              {d.stats.embedded_jars.length > 0 && (
                <div className="flex flex-col gap-1.5 rounded-lg border border-line bg-bg px-3 py-2">
                  <p className="kicker">Вложенные jar внутри этого архива ({d.stats.embedded_jars.length})</p>
                  <ul className="mono flex flex-col gap-1 text-[11px]">
                    {d.stats.embedded_jars.map(relPath => {
                      // БАГ-ФИКС v1.8.4 (реальная жалоба - "путь в джарнике
                      // один, а для запуска декомпилера пишет другой"):
                      // embedded_jars хранит СЫРОЙ путь записи внутри
                      // исходного jar (напр. "bundled/servers/.../x.jar"),
                      // но на диск движок копирует ЛЮБОЙ обычный ресурс под
                      // src/main/resources/ (см. res_dir в process_jar.cpp) -
                      // забыл добавить этот префикс, из-за чего кнопка
                      // пыталась запустить декомпилятор на несуществующем
                      // пути (outDir/bundled/... вместо
                      // outDir/src/main/resources/bundled/...).
                      // БАГ-ФИКС v1.9.6 (реальная жалоба - "путь всё ещё
                      // неправильный, хотя обсолютно точно проверил, что
                      // он верный"): предыдущий фикс (v1.8.4) вручную
                      // определял разделитель и делал relPath.split("/").
                      // join(sep) - если zip-запись внутри jar содержит
                      // обратные слэши (бывает у jar, собранных кривыми
                      // Windows-тулзами), split("/") их вообще не находил,
                      // и в итоге получался путь со СМЕШАННЫМИ
                      // разделителями. Теперь строим путь ПОСЕГМЕНТНО через
                      // ту же самую joinOutDir(), которой уже пользуется
                      // весь остальной код (addJarPaths и т.д.) - один
                      // проверенный способ соединения путей везде, вместо
                      // отдельной самодельной логики только для этой кнопки.
                      const absPath = ["src", "main", "resources", ...relPath.split(/[/\\]+/).filter(Boolean)].reduce(
                        (acc, seg) => joinOutDir(acc, seg),
                        job.outDir,
                      );
                      return (
                        <li key={relPath} className="flex items-center justify-between gap-2">
                          <span className="truncate text-dim" title={relPath}>
                            {relPath}
                          </span>
                          <button
                            className="btn btn-tonal h-6 flex-none px-2 text-[10.5px]"
                            onClick={() => {
                              addJarPaths([absPath]);
                              toast(`Добавлено в очередь: ${relPath.split("/").pop()}`, "ok");
                            }}
                          >
                            Декомпилировать тоже
                          </button>
                        </li>
                      );
                    })}
                  </ul>
                </div>
              )}
              <div className="flex flex-col gap-1.5 rounded-lg border border-line bg-bg px-3 py-2">
                {d.stats.malware_findings.length > 0 ? (
                  <>
                    <div className="flex items-center gap-1.5">
                      <TriangleAlert size={13} className="flex-none text-err" />
                      <span className="text-err">
                        Найдено {d.stats.malware_findings.length} признак(ов) потенциально вредоносного кода:
                      </span>
                    </div>
                    {/* БАГ-ФИКС v1.8.2 (HANDOFF_URGENT п.7 - карточка плагина
                        показывала только счётчик с припиской "см. терминал",
                        хотя описание/серьёзность/расположение каждой
                        находки уже приезжают в том же JSON - не нужно было
                        заставлять пользователя листать терминал за тем, что
                        уже загружено). Цвет по severity - тем же принципом,
                        что и в терминале (см. classifyLine в engine.tsx):
                        high - err, остальное - warn. */}
                    <ul className="mono flex flex-col gap-1 pl-1 text-[11px]">
                      {d.stats.malware_findings.map((f, i) => (
                        <li key={i} className={f.severity === "high" ? "text-err" : "text-warn"}>
                          <span className="text-faint">[{f.severity}]</span> {f.description}{" "}
                          <span className="text-faint">({f.where})</span>
                        </li>
                      ))}
                    </ul>
                  </>
                ) : (
                  <span className="text-dim">Признаков вредоносного кода не обнаружено (эвристика, не гарантия)</span>
                )}
              </div>

              {/* НОВОЕ v1.7.6: похожие репозитории на GitHub - по имени
                  плагина и автору из plugin.yml. */}
              {ghResults !== null && (
                <div className="flex flex-col gap-1.5 rounded-lg border border-line bg-bg px-3 py-2">
                  <p className="kicker">Похожие репозитории на GitHub</p>
                  {ghResults.length === 0 ? (
                    <p className="text-[11.5px] text-faint">ничего не найдено</p>
                  ) : (
                    ghResults.map(r => (
                      <button
                        key={r.fullName}
                        className="flex items-center gap-2 rounded-md px-1.5 py-1 text-left hover:bg-raised"
                        onClick={() => window.nano.openExternal(r.url)}
                      >
                        <GithubIcon size={12} className="flex-none text-faint" />
                        <span className="mono flex-1 truncate text-[11.5px] text-ink/90">{r.fullName}</span>
                        <span className="flex flex-none items-center gap-0.5 text-[10px] text-faint">
                          <Star size={10} /> {r.stars}
                        </span>
                        <ExternalLink size={11} className="flex-none text-faint" />
                      </button>
                    ))
                  )}
                </div>
              )}
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
          {d && (
            <button className="btn btn-tonal h-7 flex-1 text-[11.5px]" disabled={ghLoading} onClick={searchGithub}>
              <GithubIcon size={12} />
              {ghLoading ? "Ищу…" : "Найти на GitHub"}
            </button>
          )}
        </div>
      </div>
    </div>
  );
}

function Row({ label, value }: { label: string; value: ReactNode }) {
  return (
    <div className="flex items-start justify-between gap-3">
      <span className="text-faint">{label}</span>
      <span className="mono text-right text-ink/90">{value}</span>
    </div>
  );
}

// НОВОЕ (HANDOFF_URGENT п.2): при 10+ библиотеках голый join(", ") превращался
// в нечитаемую простыню на всю ширину модалки. Показываем первые 3 + счётчик
// остальных, разворачиваем по клику. Модалка пересоздаётся при смене job -
// лишнего стейта между открытиями не копится, локального useState достаточно.
function LibraryNamesValue({ count, names }: { count: number; names: string[] }) {
  const [expanded, setExpanded] = useState(false);
  const VISIBLE = 3;
  if (names.length <= VISIBLE) {
    return <>{`${fmtNum(count)} (${names.join(", ")})`}</>;
  }
  const shown = expanded ? names.join(", ") : names.slice(0, VISIBLE).join(", ");
  return (
    <span className="inline-flex flex-col items-end gap-0.5">
      <span>{fmtNum(count)}</span>
      <span className="text-right">
        ({shown}
        {!expanded && ", "}
        <button
          type="button"
          className="mono underline decoration-dotted underline-offset-2 hover:text-ink"
          onClick={() => setExpanded(v => !v)}
        >
          {expanded ? "свернуть" : `ещё ${names.length - VISIBLE}`}
        </button>
        )
      </span>
    </span>
  );
}
