import { Check, FolderOpen, Loader2, Minus, Plus, Send, X } from "lucide-react";
import { useEffect, useState, type ReactNode } from "react";
import { useEngine } from "../state/engine";
import { Toggle, Kbd } from "./ui";
import { cn } from "../utils/cn";
import { t } from "../lib/i18n";
import { DEFAULT_ICON_THUMBNAILS } from "../lib/iconThumbs";
import { formatVersionsDisplay } from "../lib/model";

function Row({ label, hint, control }: { label: string; hint?: ReactNode; control: ReactNode }) {
  return (
    <div className="flex items-center gap-4 py-2.5">
      <div className="min-w-0 flex-1">
        <p className="text-[12.5px] text-ink/90">{label}</p>
        {hint && <p className="mt-0.5 text-[11px] leading-snug text-faint">{hint}</p>}
      </div>
      {control}
    </div>
  );
}

function TelegramCredit({ handle, role }: { handle: string; role: string }) {
  const { toast } = useEngine();
  const url = `https://t.me/${handle}`;
  return (
    <a
      href={url}
      onClick={e => {
        e.preventDefault();
        window.nano.openExternal(url).catch(() => toast("Не удалось открыть ссылку", "err"));
      }}
      className="group flex items-center gap-3 rounded-xl border border-line bg-bg px-3 py-2.5 no-underline transition-colors hover:border-acid/40 hover:bg-acid/5"
    >
      <span className="grid h-8 w-8 flex-none place-items-center rounded-full border border-line bg-surface text-faint group-hover:border-acid/40 group-hover:text-acid">
        <Send size={13} />
      </span>
      <span className="min-w-0 flex-1">
        <span className="block text-[12.5px] text-ink/90">{role}</span>
        <span className="mono block text-[11px] text-faint group-hover:text-acid">t.me/{handle}</span>
      </span>
    </a>
  );
}

type Tab = "general" | "decompiler" | "editor" | "about";

const EDITOR_FONTS = [
  {
    id: "jetbrains",
    label: "JetBrains Mono",
    fontFamily: '"JetBrains Mono", ui-monospace, "SF Mono", Menlo, monospace',
    desc: "Шрифт для разработчиков с четкими символами и лигатурами",
  },
  {
    id: "fira",
    label: "Fira Code",
    fontFamily: '"Fira Code", ui-monospace, "SF Mono", Menlo, monospace',
    desc: "Популярный моноширинный шрифт Mozilla с лигатурами для кода",
  },
  {
    id: "consolas",
    label: "Consolas",
    fontFamily: 'Consolas, Monaco, "Courier New", monospace',
    desc: "Классический моноширинный шрифт Windows и Visual Studio",
  },
] as const;

export function SettingsModal() {
  const {
    settings,
    saveSettings,
    setSettingsOpen,
    envIssue,
    resolveEnvIssue,
    engineVersion,
    guiVersion,
    javaEnv,
    mavenEnv,
    installingTool,
    installProgress,
    installTool,
    addToSystemPath,
    iconThumbnails,
    toast,
  } = useEngine();
  const [draft, setDraft] = useState(settings);
  const [checking, setChecking] = useState<"idle" | "busy" | "ok">("idle");
  const [tab, setTab] = useState<Tab>("general");
  const [aboutSubTab, setAboutSubTab] = useState<"overview" | "features" | "team">("overview");

  const [editorFont, setEditorFont] = useState<string>(() => {
    try {
      return localStorage.getItem("nano:editor_font") || "jetbrains";
    } catch {
      return "jetbrains";
    }
  });

  const [editorZoom, setEditorZoom] = useState(() => {
    try {
      const s = localStorage.getItem("nano:editor_zoom");
      return s ? Number(s) || 100 : 100;
    } catch {
      return 100;
    }
  });

  const selectFont = (fontId: string) => {
    setEditorFont(fontId);
    try {
      localStorage.setItem("nano:editor_font", fontId);
      const found = EDITOR_FONTS.find(f => f.id === fontId);
      if (found) {
        document.documentElement.style.setProperty("--font-mono", found.fontFamily);
      }
    } catch {}
  };

  const updateEditorZoom = (z: number) => {
    const val = Math.max(70, Math.min(160, z));
    setEditorZoom(val);
    try {
      localStorage.setItem("nano:editor_zoom", String(val));
    } catch {}
  };

  useEffect(() => {
    const onKey = (e: KeyboardEvent) => {
      if (e.key === "Escape") setSettingsOpen(false);
    };
    window.addEventListener("keydown", onKey);
    return () => window.removeEventListener("keydown", onKey);
  }, [setSettingsOpen]);

  const checkEngine = () => {
    setChecking("busy");
    window.nano
      .getEngineVersion()
      .then(r => {
        if (r.ok && r.version) {
          setChecking("ok");
          toast(`Движок отвечает: ${r.version}`, "ok");
        } else {
          setChecking("idle");
          toast(r.error ?? "Движок не отвечает", "err");
        }
      })
      .catch(() => {
        setChecking("idle");
        toast("Движок не отвечает", "err");
      });
  };

  return (
    <div
      className="fixed inset-0 z-[110] grid place-items-center bg-black/70 p-4"
      onMouseDown={e => {
        if (e.target === e.currentTarget) setSettingsOpen(false);
      }}
    >
      <div
        role="dialog"
        aria-modal="true"
        aria-label="Настройки"
        className="animate-rise flex max-h-[86vh] w-[620px] flex-col overflow-hidden rounded-2xl border border-line bg-surface shadow-2xl shadow-black/50"
      >
        <div className="flex h-11 flex-none items-center gap-2 border-b border-line px-4">
          <h2 className="text-[13px] font-semibold text-ink">Настройки</h2>
          <div className="flex-1" />
          <Kbd>Esc</Kbd>
          <button className="icon-btn h-7 w-7" onClick={() => setSettingsOpen(false)} aria-label="Закрыть">
            <X size={14} />
          </button>
        </div>

        <div className="flex h-9 flex-none items-center gap-1 border-b border-line px-3">
          {(["general", "decompiler", "editor", "about"] as const).map(tKey => (
            <button
              key={tKey}
              className={cn(
                "rounded-md px-2.5 py-1 text-[11.5px] transition-colors",
                tab === tKey ? "bg-acid/10 text-acid font-medium" : "text-faint hover:text-ink",
              )}
              onClick={() => setTab(tKey)}
            >
              {t(draft.language, `settings.tab.${tKey}` as any)}
            </button>
          ))}
        </div>

        {/* ВКЛАДКА 1: ОБЩИЕ */}
        {tab === "general" && (
          <div className="min-h-0 flex-1 overflow-y-auto px-4 py-3">
            <p className="kicker pt-1 pb-2">{t(draft.language, "settings.section.language")}</p>
            <div className="mb-4 rounded-xl border border-line bg-bg px-3">
              <Row
                label="RU / EN"
                hint="переключает язык интерфейса и подсказок"
                control={
                  <div className="flex items-center gap-1 rounded-lg border border-line p-0.5">
                    {(["ru", "en"] as const).map(lang => (
                      <button
                        key={lang}
                        className={cn(
                          "rounded-md px-2.5 py-1 text-[11.5px] transition-colors",
                          draft.language === lang ? "bg-acid/10 text-acid" : "text-faint hover:text-ink",
                        )}
                        onClick={() => setDraft(d => ({ ...d, language: lang }))}
                      >
                        {t(draft.language, lang === "ru" ? "settings.language.ru" : "settings.language.en")}
                      </button>
                    ))}
                  </div>
                }
              />
            </div>

            <p className="kicker pt-2 pb-2">Пути</p>
            <div className="mb-4 rounded-xl border border-line bg-bg px-3 py-2.5">
              <p className="mb-1.5 text-[12.5px] text-ink/90">Папка результата</p>
              <div className="flex gap-2">
                <input
                  className="field mono text-[12px]"
                  value={draft.outputDir}
                  onChange={e => setDraft(d => ({ ...d, outputDir: e.target.value }))}
                  spellCheck={false}
                />
                <button
                  className="icon-btn h-8 w-8 flex-none border border-line"
                  title="Выбрать папку"
                  onClick={() =>
                    window.nano
                      .selectOutDir(draft.outputDir)
                      .then(p => {
                        if (p) setDraft(d => ({ ...d, outputDir: p }));
                      })
                      .catch(() => toast("Диалог выбора папки недоступен", "err"))
                  }
                >
                  <FolderOpen size={14} />
                </button>
              </div>
            </div>

            <p className="kicker pt-2 pb-2">Иконка приложения</p>
            <div className="rounded-xl border border-line bg-bg px-3.5 py-3">
              <div className="flex gap-3">
                {(
                  [
                    { key: "terminal" as const, label: "Консоль", thumb: iconThumbnails.terminal || DEFAULT_ICON_THUMBNAILS.terminal },
                    { key: "layers" as const, label: "Слои", thumb: iconThumbnails.layers || DEFAULT_ICON_THUMBNAILS.layers },
                  ]
                ).map(opt => (
                  <button
                    key={opt.key}
                    onClick={() => setDraft(d => ({ ...d, appIcon: opt.key }))}
                    className={cn(
                      "flex flex-1 flex-col items-center gap-2 rounded-lg border px-3 py-3 transition-colors",
                      draft.appIcon === opt.key
                        ? "border-acid/50 bg-acid/5"
                        : "border-line hover:border-line-strong",
                    )}
                  >
                    {opt.thumb ? (
                      <img src={opt.thumb} alt={opt.label} className="size-12 rounded-[10px]" />
                    ) : (
                      <div className="size-12 rounded-[10px] bg-raised" />
                    )}
                    <span className={cn("text-[11.5px]", draft.appIcon === opt.key ? "text-acid" : "text-dim")}>
                      {opt.label}
                    </span>
                  </button>
                ))}
              </div>
              <p className="mt-3 text-[10.5px] leading-relaxed text-faint">
                Меняет логотип в шапке приложения и иконку окна сразу. Иконку .exe/.app в проводнике при сборке это не затрагивает.
              </p>
            </div>
          </div>
        )}

        {/* ВКЛАДКА 2: ДЕКОМПИЛЯТОР И ОКРУЖЕНИЕ */}
        {tab === "decompiler" && (
          <div className="min-h-0 flex-1 overflow-y-auto px-4 py-3">
            <p className="kicker pt-1 pb-2">{t(draft.language, "settings.section.environment")}</p>
            <div className="mb-4 rounded-xl border border-line bg-bg px-3">
              <Row
                label={t(draft.language, "settings.engine.label")}
                hint="resources/engine/NanoDecompilerCLI · C++17 движок декомпиляции"
                control={
                  <button
                    className={cn("btn btn-tonal h-7 text-[11.5px]", checking === "ok" && "pointer-events-none")}
                    onClick={checkEngine}
                  >
                    {checking === "busy" ? (
                      <Loader2 size={12} className="animate-spin" />
                    ) : checking === "ok" ? (
                      <Check size={12} />
                    ) : null}
                    {checking === "busy"
                      ? "проверяю…"
                      : checking === "ok"
                        ? `ok · ${engineVersion?.replace(/^NanoDecompiler /, "") ?? "?"}`
                        : "Проверить"}
                  </button>
                }
              />
              <div className="h-px bg-line" />
              <Row
                label={t(draft.language, "settings.java.label")}
                hint={
                  installingTool === "java"
                    ? (installProgress?.label ?? "устанавливаю…") +
                      (installProgress?.pct != null ? ` · ${installProgress.pct}%` : "")
                    : javaEnv === null
                      ? "проверяю…"
                      : envIssue
                        ? "не найдена в PATH — нужна для ручной сборки (mvn compile), движок C++ работает без неё"
                        : (
                          <>
                            {javaEnv.text ?? "найдена"}
                            {javaEnv.inPath === false && (
                              <span className="text-warn/90"> (не в системном PATH)</span>
                            )}
                            {javaEnv.olderThanBundled && (
                              <span className="text-warn block mt-0.5">· Установлена старая версия; рекомендуется JDK 17 LTS</span>
                            )}
                          </>
                        )
                }
                control={
                  installingTool === "java" ? (
                    <span className="chip border-warn/35 text-warn">
                      <Loader2 size={12} className="animate-spin" />
                      установка…
                    </span>
                  ) : !javaEnv?.ok ? (
                    <div className="flex items-center gap-1.5">
                      <button
                        className="btn btn-tonal h-7 text-[11.5px]"
                        disabled={installingTool !== null}
                        onClick={() => installTool("java")}
                      >
                        {t(draft.language, "settings.install")}
                      </button>
                      <button
                        className="btn btn-tonal h-7 text-[11.5px]"
                        disabled={installingTool !== null}
                        onClick={resolveEnvIssue}
                      >
                        {t(draft.language, "settings.recheck")}
                      </button>
                    </div>
                  ) : javaEnv.inPath === false ? (
                    <button
                      className="btn btn-primary h-7 text-[11px]"
                      onClick={() => addToSystemPath("java")}
                    >
                      В системный PATH
                    </button>
                  ) : javaEnv.olderThanBundled ? (
                    <button
                      className="btn btn-tonal h-7 text-[11px]"
                      onClick={() => installTool("java")}
                    >
                      Обновить до JDK 17
                    </button>
                  ) : (
                    <span className="chip border-acid/35 text-acid">
                      <span className="dot bg-acid" />
                      найдена (в PATH)
                    </span>
                  )
                }
              />
              <div className="h-px bg-line" />
              <Row
                label={t(draft.language, "settings.maven.label")}
                hint={
                  installingTool === "maven"
                    ? (installProgress?.label ?? "устанавливаю…") +
                      (installProgress?.pct != null ? ` · ${installProgress.pct}%` : "")
                    : mavenEnv === null
                      ? "проверяю…"
                      : mavenEnv.ok
                        ? (
                          <>
                            {(mavenEnv.text ?? "найден") + " · нужен для сборки проектов (mvn compile)"}
                            {mavenEnv.inPath === false && (
                              <span className="text-warn/90"> (не в системном PATH)</span>
                            )}
                            {mavenEnv.olderThanBundled && (
                              <span className="text-warn block mt-0.5">· Установлена старая версия; рекомендуется Maven 3.9.9</span>
                            )}
                          </>
                        )
                        : "не найден — нужен только для сборки сгенерированных pom.xml"
                }
                control={
                  installingTool === "maven" ? (
                    <span className="chip border-warn/35 text-warn">
                      <Loader2 size={12} className="animate-spin" />
                      установка…
                    </span>
                  ) : !mavenEnv?.ok ? (
                    <button
                      className="btn btn-tonal h-7 text-[11.5px]"
                      disabled={installingTool !== null}
                      onClick={() => installTool("maven")}
                    >
                      {t(draft.language, "settings.install")}
                    </button>
                  ) : mavenEnv.inPath === false ? (
                    <button
                      className="btn btn-primary h-7 text-[11px]"
                      onClick={() => addToSystemPath("maven")}
                    >
                      В системный PATH
                    </button>
                  ) : mavenEnv.olderThanBundled ? (
                    <button
                      className="btn btn-tonal h-7 text-[11px]"
                      onClick={() => installTool("maven")}
                    >
                      Обновить до 3.9.9
                    </button>
                  ) : (
                    <span className="chip border-acid/35 text-acid">
                      <span className="dot bg-acid" />
                      найден (в PATH)
                    </span>
                  )
                }
              />
            </div>

            <p className="kicker pt-2 pb-2">Параметры декомпиляции</p>
            <div className="rounded-xl border border-line bg-bg px-3">
              <Row
                label="Проверка легитимности"
                hint="сверка с базой известных вредоносных сигнатур перед декомпиляцией"
                control={
                  <Toggle
                    label="Проверка легитимности"
                    checked={draft.legitimacyCheck}
                    onChange={v => setDraft(d => ({ ...d, legitimacyCheck: v }))}
                  />
                }
              />
              <div className="h-px bg-line" />
              <Row
                label="Потоки движка"
                hint="параллельный разбор классов в C++ ядре"
                control={
                  <div className="flex items-center gap-1.5">
                    <button
                      className="icon-btn h-7 w-7 border border-line"
                      onClick={() => setDraft(d => ({ ...d, threads: Math.max(1, d.threads - 1) }))}
                      aria-label="Меньше"
                    >
                      <Minus size={12} />
                    </button>
                    <span className="mono w-8 text-center text-[13px] text-ink tabular-nums">{draft.threads}</span>
                    <button
                      className="icon-btn h-7 w-7 border border-line"
                      onClick={() => setDraft(d => ({ ...d, threads: Math.min(16, d.threads + 1) }))}
                      aria-label="Больше"
                    >
                      <Plus size={12} />
                    </button>
                  </div>
                }
              />
              <div className="h-px bg-line" />
              <Row
                label="Переименовывать обфусцированные члены"
                hint="эвристика по сигнатурам и омоглифам; ниже порога имена остаются как есть"
                control={
                  <Toggle
                    label="Переименовывать обфусцированные члены"
                    checked={draft.renameObfuscated}
                    onChange={v => setDraft(d => ({ ...d, renameObfuscated: v }))}
                  />
                }
              />
              <div className="h-px bg-line" />
              <Row
                label="Сохранять номера строк байткода"
                hint="комментарии /* line: n */ — помогает сверять со стектрейсами"
                control={
                  <Toggle
                    label="Сохранять номера строк байткода"
                    checked={draft.keepLineNumbers}
                    onChange={v => setDraft(d => ({ ...d, keepLineNumbers: v }))}
                  />
                }
              />
              <div className="h-px bg-line" />
              <Row
                label="Открывать папку по завершении"
                control={
                  <Toggle
                    label="Открывать папку по завершении"
                    checked={draft.openFolderOnDone}
                    onChange={v => setDraft(d => ({ ...d, openFolderOnDone: v }))}
                  />
                }
              />
              <div className="h-px bg-line" />
              <Row
                label="Отправка отчётов разработчику"
                hint="отправлять статистику и нераспознанный байткод для улучшения движка"
                control={
                  <Toggle
                    label="Отправка отчётов разработчику"
                    checked={draft.telemetryEnabled}
                    onChange={v => setDraft(d => ({ ...d, telemetryEnabled: v }))}
                  />
                }
              />
            </div>
          </div>
        )}

        {/* ВКЛАДКА 3: РЕДАКТОР И ШРИФТЫ */}
        {tab === "editor" && (
          <div className="min-h-0 flex-1 overflow-y-auto px-4 py-3">
            <p className="kicker pt-1 pb-2">Масштаб просмотра кода</p>
            <div className="mb-4 rounded-xl border border-line bg-bg px-3">
              <Row
                label={draft.language === "ru" ? "Масштаб шрифта" : "Font zoom"}
                hint={draft.language === "ru" ? "Базовый размер текста в окне просмотра кода (Ctrl + / Ctrl -)" : "Base text size in code viewer (Ctrl + / Ctrl -)"}
                control={
                  <div className="flex items-center gap-1.5">
                    <button
                      className="icon-btn h-7 w-7 border border-line"
                      title={draft.language === "ru" ? "Уменьшить" : "Decrease"}
                      onClick={() => updateEditorZoom(editorZoom - 10)}
                    >
                      <Minus size={13} />
                    </button>
                    <span className="mono w-12 text-center text-[12px] text-ink">{editorZoom}%</span>
                    <button
                      className="icon-btn h-7 w-7 border border-line"
                      title={draft.language === "ru" ? "Увеличить" : "Increase"}
                      onClick={() => updateEditorZoom(editorZoom + 10)}
                    >
                      <Plus size={13} />
                    </button>
                    {editorZoom !== 100 && (
                      <button
                        className="btn btn-tonal h-7 px-2 text-[10.5px]"
                        onClick={() => updateEditorZoom(100)}
                      >
                        {draft.language === "ru" ? "Сброс" : "Reset"}
                      </button>
                    )}
                  </div>
                }
              />
            </div>

            <p className="kicker pt-2 pb-2">Шрифт кода (Моноширинный)</p>
            <div className="mb-4 space-y-2">
              {EDITOR_FONTS.map(f => {
                const active = editorFont === f.id;
                return (
                  <button
                    key={f.id}
                    onClick={() => selectFont(f.id)}
                    className={cn(
                      "w-full text-left rounded-xl border p-3 transition-colors",
                      active ? "border-acid/50 bg-acid/5" : "border-line bg-bg hover:border-line-strong",
                    )}
                  >
                    <div className="flex items-center justify-between">
                      <span className="text-[13px] font-medium text-ink" style={{ fontFamily: f.fontFamily }}>
                        {f.label}
                      </span>
                      {active && (
                        <span className="chip border-acid/35 text-acid text-[10.5px]">
                          <span className="dot bg-acid" /> активен
                        </span>
                      )}
                    </div>
                    <p className="mt-1 text-[11px] text-faint leading-snug">{f.desc}</p>
                  </button>
                );
              })}
            </div>

            <p className="kicker pt-2 pb-2">Предпросмотр кода</p>
            <div className="mb-4 rounded-xl border border-line bg-bg p-3">
              <pre className="mono text-[11.5px] leading-relaxed text-ink/90 overflow-x-auto">
{`// Пример декомпиляции с текущим шрифтом
public class NanoForgePlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("✔ Плагин успешно инициализирован");
    }
}`}
              </pre>
            </div>

            <p className="kicker pt-2 pb-2">Стиль отображения</p>
            <div className="rounded-xl border border-line bg-bg px-3">
              <Row
                label={t(draft.language, "settings.disable_vscode_logs")}
                hint="скрывает визуальное сворачивание логов и декорации вызовов"
                control={
                  <Toggle
                    label=""
                    checked={draft.disableVsCodeLogs}
                    onChange={v => setDraft(d => ({ ...d, disableVsCodeLogs: v }))}
                  />
                }
              />
            </div>
          </div>
        )}

        {/* ВКЛАДКА 4: О ПРОГРАММЕ */}
        {tab === "about" && (
          <div className="flex min-h-0 flex-1 flex-col">
            <div className="flex flex-none items-center gap-1 border-b border-line px-4 pt-2 pb-1">
              {(
                [
                  ["overview", t(draft.language, "settings.about.overview")],
                  ["features", t(draft.language, "settings.about.features")],
                  ["team", t(draft.language, "settings.about.team")],
                ] as const
              ).map(([id, label]) => (
                <button
                  key={id}
                  className={cn(
                    "rounded-md px-2.5 py-1 text-[11px] transition-colors",
                    aboutSubTab === id ? "bg-acid/10 text-acid font-medium" : "text-faint hover:text-ink",
                  )}
                  onClick={() => setAboutSubTab(id)}
                >
                  {label}
                </button>
              ))}
            </div>
            <div className="min-h-0 flex-1 overflow-y-auto px-4 py-3">
              {aboutSubTab === "overview" && (
                <>
                  <p className="kicker pt-1 pb-2">Архитектура NanoDecompiler</p>
                  <div className="rounded-xl border border-line bg-bg px-3.5 py-3 text-[12px] leading-relaxed text-ink/85 space-y-2.5">
                    <p>
                      <span className="font-semibold text-ink">NanoDecompiler</span> — профессиональный декомпилятор Java-байткода
                      со встроенным 15-ступенчатым конвейером глубокой деобфускации (-ObfUpd.1..15).
                    </p>
                    <p>
                      Ядро приложения написано на чистом <span className="text-acid">C++17</span>: осуществляет потоковый разбор
                      Constant Pool и JVM байткода, моделирует абстрактный стек исполнения, восстанавливает структурные
                      конструкции control-flow (if/while/for/switch/try-catch) и генерирует чистый, компилируемый Java 8–21 код.
                    </p>
                    <p>
                      Графическая оболочка построена на <span className="text-acid">Electron + React + TypeScript + Tailwind</span>.
                      Она обеспечивает мгновенную навигацию по дереву пакетов, умную подсветку с распознаванием Bukkit/BungeeCord
                      цветов и локальную безопасность без отправки исходников на сторонние серверы.
                    </p>
                  </div>
                </>
              )}
              {aboutSubTab === "features" && (
                <>
                  <p className="kicker pt-1 pb-2">15 этапов конвейера деобфускации (-ObfUpd)</p>
                  <div className="rounded-xl border border-line bg-bg px-3.5 py-3 text-[11.5px] leading-relaxed text-ink/80">
                    <ul className="space-y-1.5 list-disc pl-4">
                      <li><b>Allatori XOR:</b> расшифровка двух- и трехключевого XOR строк и инлайнинг методов.</li>
                      <li><b>Opaque Predicates:</b> устранение непрозрачных условий и удаление ложных веток.</li>
                      <li><b>Control Flow Unflattening:</b> расплющивание фиктивных switch-диспетчеров обфускаторов.</li>
                      <li><b>Exception Trampolines:</b> нейтрализация фиктивных батутов try-catch исключений.</li>
                      <li><b>Constant Folding:</b> свертка числовых, логических и строковых констант.</li>
                      <li><b>String Decryptors:</b> инлайнинг intern/substring/charAt/length вызовов.</li>
                      <li><b>Synthetic Accessors:</b> свертка synthetic access$000 методов в прямые вызовы.</li>
                      <li><b>Dead Code Elimination:</b> отсечение недостижимого кода после terminal-инструкций.</li>
                      <li><b>Identifier Heuristics:</b> детекция обфусцированных имен, омоглифов и ZKM/ProGuard паттернов.</li>
                      <li><b>Stack Inlining:</b> устранение паразитных объявлений tempN / __stk / __sb в заголовках.</li>
                      <li><b>Assert & Unbox:</b> восстановление $assertionsDisabled и распаковка примитивов.</li>
                      <li><b>Reflection Desugaring:</b> свертка Class.forName/getMethod/invoke в прямой вызов.</li>
                      <li><b>StringBuilder Normalizer:</b> объединение цепочек .append(...) в оператор +.</li>
                      <li><b>Static Table Unpacker:</b> распаковка массивов и таблиц в static-блоках &lt;clinit&gt;.</li>
                      <li><b>Pipeline Verification:</b> сводный аудит качества и генерация чистого Maven проекта.</li>
                    </ul>
                  </div>
                </>
              )}
              {aboutSubTab === "team" && (
                <>
                  <p className="kicker pt-1 pb-2">Разработчики</p>
                  <div className="space-y-2">
                    <TelegramCredit handle="radoqi" role="Кодер, основатель проекта" />
                    <TelegramCredit handle="dyrachuna" role="GUI-разработчик" />
                  </div>
                </>
              )}
            </div>
          </div>
        )}

        <div className="flex h-12 flex-none items-center gap-2 border-t border-line px-4">
          <span className="mono text-[10.5px] text-faint">
            {formatVersionsDisplay(engineVersion, guiVersion, draft.language)}
          </span>
          <div className="flex-1" />
          <button className="btn btn-ghost" onClick={() => setSettingsOpen(false)}>
            {t(draft.language, "settings.cancel")}
          </button>
          <button className="btn btn-acid" onClick={() => saveSettings(draft)}>
            {t(draft.language, "settings.save")}
          </button>
        </div>
      </div>
    </div>
  );
}
