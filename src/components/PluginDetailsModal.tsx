import { ExternalLink, X, FolderOpen, Star, TriangleAlert } from "lucide-react";
import { useState, type ReactNode } from "react";
import { fmtBytes, fmtNum, fmtSeconds, joinOutDir, type Job } from "../lib/model";
import { t } from "../lib/i18n";
import { useEngine } from "../state/engine";

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

export function PluginDetailsModal({ job, onClose }: { job: Job; onClose: () => void }) {
  const { openOutput, toast, addJarPaths, settings } = useEngine();
  const lang = settings.language;
  const d = job.details;
  const [ghLoading, setGhLoading] = useState(false);
  const [ghResults, setGhResults] = useState<
    { name: string; fullName: string; url: string; description: string | null; stars: number }[] | null
  >(null);

  useEffect(() => {
    const onKey = (e: KeyboardEvent) => {
      if (e.key === "Escape") onClose();
    };
    window.addEventListener("keydown", onKey);
    return () => window.removeEventListener("keydown", onKey);
  }, [onClose]);

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
      className="fixed inset-0 z-50 grid place-items-center bg-black/70 p-4"
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
              {lang === "ru"
                ? "// подробная информация появляется после завершения декомпиляции."
                : "// detailed information appears after decompilation completes."}
              {job.status === "failed" && (lang === "ru" ? " этот запуск завершился с ошибкой до сбора статистики." : " this run failed before stats collection.")}
            </p>
          ) : (
            <div className="flex flex-col gap-3 text-[12.5px]">
              <Row label={lang === "ru" ? "Платформа" : "Platform"} value={d.stats.platform ?? (lang === "ru" ? "не определена" : "unknown")} />
              <Row label={lang === "ru" ? "Размер архива" : "Archive size"} value={fmtBytes(job.sizeBytes)} />
              <Row label={lang === "ru" ? "Классов в архиве" : "Classes in archive"} value={fmtNum(d.stats.classes_total)} />
              <Row
                label={lang === "ru" ? "Классов библиотек пропущено" : "Library classes skipped"}
                value={
                  d.stats.library_classes_skipped > 0
                    ? (
                        <LibraryNamesValue
                          count={d.stats.library_classes_skipped}
                          names={d.stats.library_names_hit}
                          lang={lang}
                        />
                      )
                    : "0"
                }
              />
              <Row
                label={lang === "ru" ? "Методов декомпилировано" : "Methods decompiled"}
                value={`${fmtNum(d.stats.decompiled_methods)} / ${fmtNum(d.stats.total_methods)} (${d.stats.decompiled_pct.toFixed(1)}%)`}
              />
              {d.stats.fallback_methods > 0 && (
                <Row
                  label={lang === "ru" ? "Откат на байткод" : "Bytecode fallback"}
                  value={`${fmtNum(d.stats.fallback_methods)} ${lang === "ru" ? "метод(ов) - см. .java с комментарием" : "method(s) - see commented .java"}`}
                />
              )}
              {d.stats.synthetic_switchmap_classes_hidden > 0 && (
                <Row
                  label={lang === "ru" ? "Синтетических switchmap-классов скрыто" : "Synthetic switchmap classes hidden"}
                  value={`${fmtNum(d.stats.synthetic_switchmap_classes_hidden)} (${lang === "ru" ? "компиляторные helper-классы для switch по enum" : "compiler helper classes for enum switches"})`}
                />
              )}
              {d.stats.junk_catches_removed > 0 && (
                <Row label={lang === "ru" ? "Пустых catch-блоков вычищено" : "Empty catch blocks cleaned"} value={fmtNum(d.stats.junk_catches_removed)} />
              )}
              {job.status === "done" && <Row label={lang === "ru" ? "Время декомпиляции" : "Decompilation time"} value={fmtSeconds(job.elapsedMs)} />}
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

              {/* НОВОЕ v1.9.12: блок легитимности + НОВОЕ v1.9.13 host_reachable */}
              {d.stats.legitimacy && (
                <div className="flex flex-col gap-1.5 rounded-lg border border-line bg-bg px-3 py-2">
                  <p className="kicker">Проверка легитимности</p>
                  {/* НОВОЕ v1.9.13: источники с host_reachable===false показываем явно
                      как "недоступен из вашей сети" - отдельно от "не найдено".
                      Показываем только те, у которых probe выполнялся (not null) и провалился. */}
                  {(["github","modrinth","spigot","hangar"] as const)
                    .filter(k => d.stats.legitimacy![k].host_reachable === false)
                    .map(k => (
                      <span key={k} className="mono text-[11px] text-warn">
                        ⚠ {k.charAt(0).toUpperCase() + k.slice(1)} — недоступен из вашей сети (возможно, гео-блок)
                      </span>
                    ))
                  }
                  {/* SHA-256 блок (из v1.9.12) */}
                  {d.stats.legitimacy.hash_comparison && (
                    <>
                      {d.stats.legitimacy.hash_comparison.matching.length === 0 &&
                       d.stats.legitimacy.hash_comparison.mismatching.length === 0 ? (
                        <span className="text-dim">
                          Совпадений по хэшу не найдено (либо источники не отдают хэш для этого плагина)
                        </span>
                      ) : (
                        <ul className="mono flex flex-col gap-1 pl-1 text-[11px]">
                          {d.stats.legitimacy.hash_comparison.matching.map(src => (
                            <li key={`m-${src}`} className="text-acid">
                              ✓ хэш совпадает с релизом на {src}
                            </li>
                          ))}
                          {d.stats.legitimacy.hash_comparison.mismatching.map(src => (
                            <li key={`x-${src}`} className="italic text-warn">
                              хэш НЕ совпадает ни с одним релизом на {src} — возможно, изменённая копия
                            </li>
                          ))}
                        </ul>
                      )}
                    </>
                  )}
                </div>
              )}

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
            {t(lang, "sidebar.open_output")}
          </button>
          {d && (
            <button className="btn btn-tonal h-7 flex-1 text-[11.5px]" disabled={ghLoading} onClick={searchGithub}>
              <GithubIcon size={12} />
              {ghLoading ? (lang === "ru" ? "Ищу…" : "Searching…") : (lang === "ru" ? "Найти на GitHub" : "Find on GitHub")}
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

function LibraryNamesValue({ count, names, lang }: { count: number; names: string[]; lang: "ru" | "en" }) {
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
          {expanded ? (lang === "ru" ? "свернуть" : "collapse") : (lang === "ru" ? `ещё ${names.length - VISIBLE}` : `${names.length - VISIBLE} more`)}
        </button>
        )
      </span>
    </span>
  );
}
