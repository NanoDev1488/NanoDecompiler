import { Check, FolderOpen, Loader2, Minus, Plus, Send, X } from "lucide-react";
import { useState, type ReactNode } from "react";
import { useEngine } from "../state/engine";
import { Toggle, Kbd } from "./ui";
import { cn } from "../utils/cn";

function Row({ label, hint, control }: { label: string; hint?: string; control: ReactNode }) {
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

type Tab = "general" | "about";

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
    toast,
  } = useEngine();
  const [draft, setDraft] = useState(settings);
  const [checking, setChecking] = useState<"idle" | "busy" | "ok">("idle");
  const [tab, setTab] = useState<Tab>("general");

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
      className="fixed inset-0 z-50 grid place-items-center bg-black/60 p-4 backdrop-blur-[2px]"
      onMouseDown={e => {
        if (e.target === e.currentTarget) setSettingsOpen(false);
      }}
    >
      <div
        role="dialog"
        aria-modal="true"
        aria-label="Настройки"
        className="animate-rise flex max-h-[86vh] w-[560px] flex-col overflow-hidden rounded-2xl border border-line bg-surface shadow-2xl shadow-black/50"
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
          <button
            className={cn(
              "rounded-md px-2.5 py-1 text-[11.5px] transition-colors",
              tab === "general" ? "bg-acid/10 text-acid" : "text-faint hover:text-ink",
            )}
            onClick={() => setTab("general")}
          >
            Основные
          </button>
          <button
            className={cn(
              "rounded-md px-2.5 py-1 text-[11.5px] transition-colors",
              tab === "about" ? "bg-acid/10 text-acid" : "text-faint hover:text-ink",
            )}
            onClick={() => setTab("about")}
          >
            О сервисе
          </button>
        </div>

        {tab === "general" ? (
          <div className="min-h-0 flex-1 overflow-y-auto px-4 py-3">
            {/* окружение */}
            <p className="kicker pt-1 pb-2">Окружение</p>
            <div className="rounded-xl border border-line bg-bg px-3">
              <Row
                label="Движок"
                hint="resources/engine/NanoDecompilerCLI · дочерний процесс, вывод идёт в терминал"
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
                label="Java"
                hint={
                  javaEnv === null
                    ? "проверяю…"
                    : envIssue
                      ? "JRE не найдена в PATH — движок не запустится"
                      : (javaEnv.text ?? "найдена")
                }
                control={
                  envIssue ? (
                    <button className="btn btn-err h-7 text-[11.5px]" onClick={resolveEnvIssue}>
                      Проверить снова
                    </button>
                  ) : (
                    <span className="chip border-acid/35 text-acid">
                      <span className="dot bg-acid" />
                      найдена
                    </span>
                  )
                }
              />
              <div className="h-px bg-line" />
              <Row
                label="Maven"
                hint={
                  mavenEnv === null
                    ? "проверяю…"
                    : mavenEnv.ok
                      ? (mavenEnv.text ?? "найден") + " · нужен для верификации через recompile"
                      : "не найден — верификация через recompile недоступна"
                }
                control={
                  <span className={cn("chip", !mavenEnv?.ok ? "opacity-40" : "border-acid/35 text-acid")}>
                    {mavenEnv?.ok && <span className="dot bg-acid" />}
                    {mavenEnv?.ok ? "найден" : "не найден"}
                  </span>
                }
              />
            </div>

            {/* пути */}
            <p className="kicker pt-4 pb-2">Пути</p>
            <div className="rounded-xl border border-line bg-bg px-3 py-2.5">
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

            {/* декомпиляция */}
            <p className="kicker pt-4 pb-2">Декомпиляция</p>
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
                hint="параллельный разбор классов; 8 — разумный дефолт"
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
                hint="эвристика по сигнатурам; ниже порога 0.60 имена остаются как есть"
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
            </div>
          </div>
        ) : (
          <div className="min-h-0 flex-1 overflow-y-auto px-4 py-3">
            <p className="kicker pt-1 pb-2">Что это</p>
            <div className="rounded-xl border border-line bg-bg px-3.5 py-3 text-[12.5px] leading-relaxed text-ink/85">
              <p>
                <span className="font-semibold text-ink">NanoDecompiler</span> — декомпилятор Java-байткода (.class/.jar)
                в читаемый исходный код .java. Движок написан на C++17: разбирает constant pool и байткод JVM,
                восстанавливает control-flow (if/while/for/switch/try-catch) из низкоуровневых инструкций и
                stack-based VM в структурированные Java-выражения, затем генерирует компилируемые .java-файлы и
                Maven-проект (pom.xml) вокруг них.
              </p>
              <p className="mt-2">
                Изначально заточен под декомпиляцию Bukkit/Spigot-плагинов для Minecraft (учитывает <code className="mono text-[11px]">plugin.yml</code>,
                фильтрует библиотечные классы, распознаёт паттерны байткода, которые генерирует именно javac для
                типичных плагинных конструкций), но одинаково разбирает любой обычный .jar.
              </p>
            </div>

            <p className="kicker pt-4 pb-2">Возможности</p>
            <div className="rounded-xl border border-line bg-bg px-3.5 py-3">
              <ul className="space-y-1.5 text-[12px] leading-relaxed text-ink/80">
                <li>— восстановление структурного control-flow (if/while/for/switch/try-catch, а не плоский байткод с goto)</li>
                <li>— переименование обфусцированных членов по эвристике сигнатур</li>
                <li>— проверка легитимности .jar перед декомпиляцией (база известных вредоносных сигнатур)</li>
                <li>— автогенерация pom.xml с восстановленными Maven-зависимостями (в т.ч. из META-INF/maven/*)</li>
                <li>— мини-IDE прямо в приложении: дерево пакетов, подсветка синтаксиса, просмотр без внешнего редактора</li>
                <li>— работает офлайн — движок не отправляет ваш .jar никуда за пределы вашей машины</li>
              </ul>
            </div>

            <p className="kicker pt-4 pb-2">Команда</p>
            <div className="space-y-2">
              <TelegramCredit handle="radoqi" role="Кодер, основатель проекта" />
              <TelegramCredit handle="dyrachuna" role="GUI-разработчик" />
            </div>
          </div>
        )}

        <div className="flex h-12 flex-none items-center gap-2 border-t border-line px-4">
          <span className="mono text-[10.5px] text-faint">
            engine {engineVersion?.replace(/^NanoDecompiler /, "") ?? "…"} · GUI v{guiVersion ?? "…"}
          </span>
          <div className="flex-1" />
          <button className="btn btn-ghost" onClick={() => setSettingsOpen(false)}>
            Отмена
          </button>
          <button className="btn btn-acid" onClick={() => saveSettings(draft)}>
            Сохранить
          </button>
        </div>
      </div>
    </div>
  );
}
