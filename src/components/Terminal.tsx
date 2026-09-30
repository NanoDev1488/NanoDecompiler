import { ArrowDownToLine, ChevronDown, ChevronUp, Copy, ShieldAlert, Trash2 } from "lucide-react";
import { useEffect, useMemo, useRef, useState, type ReactNode } from "react";
import { useEngine } from "../state/engine";
import { fmtClock, type LogFilter, type LogLevel } from "../lib/model";
import { t } from "../lib/i18n";
import { cn } from "../utils/cn";
import { useResizeDrag } from "../lib/useResize";

const TAG_COLOR: Record<LogLevel, string> = {
  info: "text-faint",
  ok: "text-acid",
  warn: "text-warn",
  err: "text-err",
};

/** Форматирует строку терминала, делая ссылки и Telegram-теги кликабельными */
function formatTerminalMessage(msg: string): ReactNode {
  if (!/(?:https?:\/\/|t\.me\/|@)[a-zA-Z0-9_]+/i.test(msg)) {
    return msg;
  }
  const parts: ReactNode[] = [];
  const regex = /(https?:\/\/[^\s]+|t\.me\/[a-zA-Z0-9_]+|@[a-zA-Z0-9_]+)/g;
  let lastIndex = 0;
  let m: RegExpExecArray | null;
  while ((m = regex.exec(msg)) !== null) {
    if (m.index > lastIndex) {
      parts.push(msg.slice(lastIndex, m.index));
    }
    const token = m[0];
    const url = token.startsWith("@")
      ? `https://t.me/${token.slice(1)}`
      : token.startsWith("t.me/")
      ? `https://${token}`
      : token;
    parts.push(
      <a
        key={m.index}
        href={url}
        target="_blank"
        rel="noopener noreferrer"
        onClick={(e) => {
          e.preventDefault();
          window.nano?.openExternal(url).catch(() => {});
        }}
        className="text-acid hover:underline cursor-pointer font-medium"
        title={`Открыть в браузере: ${url}`}
      >
        {token}
      </a>
    );
    lastIndex = regex.lastIndex;
  }
  if (lastIndex < msg.length) {
    parts.push(msg.slice(lastIndex));
  }
  return <>{parts}</>;
}

export function Terminal() {
  const { log, logFilter, setLogFilter, terminalOpen, toggleTerminal, clearLog, copyLog, copyText, runningJob, terminalHeight, setTerminalHeight, settings } =
    useEngine();
  const lang = settings.language;
  const [stick, setStick] = useState(true);
  const [searchQuery, setSearchQuery] = useState("");
  const scrollRef = useRef<HTMLDivElement>(null);
  // БАГ-ФИКС v1.7.3 (найдено сторонним ревью - реальная регрессия из
  // v1.7.2): scrollTo({behavior:"smooth"}) анимируется НЕСКОЛЬКО кадров, и
  // браузер шлёт ПРОМЕЖУТОЧНЫЕ события "scroll" по ходу анимации - на этих
  // кадрах позиция ЕЩЁ не у самого низа, onScroll ниже видел
  // "distance > 24" и сбрасывал stick=false ДО того, как анимация вообще
  // успевала доехать до конца. В следующий раз эффект видел stick=false и
  // просто не скроллил - автопрокрутка НАВСЕГДА глохла после первого же
  // плавного скролла. Флаг ниже помечает "это мы сами скроллим
  // программно, не пользователь" - onScroll игнорирует событие, пока флаг
  // взведён (снимается по таймауту, покрывающему длительность анимации).
  const isAutoScrollingRef = useRef(false);
  const autoScrollTimeoutRef = useRef<number | null>(null);
  // invert=true - тянем ЗА ВЕРХНИЙ край терминала, движение мыши ВВЕРХ
  // должно УВЕЛИЧИВАТЬ высоту (терминал растёт вверх, а не вниз).
  const onResizeDown = useResizeDrag("y", terminalHeight, setTerminalHeight, 120, 560, true);

  const counts = useMemo(() => {
    const c: Record<LogFilter, number> = { all: log.length, info: 0, ok: 0, warn: 0, err: 0 };
    for (const l of log) c[l.level] += 1;
    return c;
  }, [log]);

  const malwareCount = useMemo(() => log.filter(l => l.tag === "malware").length, [log]);

  const visible = useMemo(() => {
    let items = logFilter === "all" ? log : log.filter(l => l.level === logFilter);
    if (searchQuery.trim()) {
      const q = searchQuery.toLowerCase();
      items = items.filter(l => l.msg.toLowerCase().includes(q) || l.tag.toLowerCase().includes(q));
    }
    return items;
  }, [log, logFilter, searchQuery]);

  useEffect(() => {
    if (!stick || !terminalOpen) return;
    const el = scrollRef.current;
    if (!el) return;
    // БАГ-ФИКС v1.7.2 (HANDOFF_NEXT_AGENT_HANDOVER п.26): раньше прокрутка
    // к концу была мгновенным скачком (scrollTop = scrollHeight) - при
    // активной декомпиляции лог обновляется пачками (см. троттлинг в
    // electron/main.ts, HANDOFF_22) и терминал буквально "дёргался" на
    // каждую пачку. scrollTo({behavior:"smooth"}) даёт плавную анимацию;
    // если новых строк накопилось МНОГО за раз (пачка большая - например
    // после долгой паузы вкладки в фоне), анимация не должна тянуться
    // долго и заметно отставать от реального конца лога - в этом случае
    // прыгаем мгновенно, как раньше.
    const distance = el.scrollHeight - el.scrollTop - el.clientHeight;
    const behavior: ScrollBehavior = distance > 2000 ? "auto" : "smooth";
    if (behavior === "smooth") {
      isAutoScrollingRef.current = true;
      if (autoScrollTimeoutRef.current !== null) window.clearTimeout(autoScrollTimeoutRef.current);
      // 500мс с запасом покрывает типичную длительность smooth-скролла в
      // Chromium на дистанциях, которые тут вообще возможны (< 2000px,
      // см. ветку behavior==="auto" выше для больших прыжков).
      autoScrollTimeoutRef.current = window.setTimeout(() => {
        isAutoScrollingRef.current = false;
      }, 500);
    }
    el.scrollTo({ top: el.scrollHeight, behavior });
  }, [visible.length, stick, terminalOpen]);

  const onScroll = () => {
    const el = scrollRef.current;
    if (!el || isAutoScrollingRef.current) return;
    setStick(el.scrollHeight - el.scrollTop - el.clientHeight < 24);
  };

  const jumpToEnd = () => {
    const el = scrollRef.current;
    if (el) {
      isAutoScrollingRef.current = true;
      if (autoScrollTimeoutRef.current !== null) window.clearTimeout(autoScrollTimeoutRef.current);
      autoScrollTimeoutRef.current = window.setTimeout(() => {
        isAutoScrollingRef.current = false;
      }, 500);
      el.scrollTo({ top: el.scrollHeight, behavior: "smooth" });
    }
    setStick(true);
  };

  const filters: Array<{ id: LogFilter; label: string }> = useMemo(
    () => [
      { id: "all", label: t(lang, "term.filter_all") },
      { id: "info", label: "info" },
      { id: "ok", label: "ok" },
      { id: "warn", label: t(lang, "term.filter_warn") },
      { id: "err", label: t(lang, "term.filter_err") },
    ],
    [lang],
  );

  return (
    <div
      className={cn(
        "relative flex flex-none flex-col border-t border-line bg-bg transition-[height] duration-200",
        !terminalOpen && "h-9",
      )}
      style={terminalOpen ? { height: terminalHeight } : undefined}
    >
      {terminalOpen && (
        <div
          onPointerDown={onResizeDown}
          className="group absolute top-[-3px] left-0 z-10 h-[6px] w-full cursor-row-resize select-none"
        >
          <div className="absolute inset-x-0 top-1/2 h-px -translate-y-1/2 bg-line-strong opacity-0 transition-opacity group-hover:opacity-100 group-active:bg-acid group-active:opacity-100" />
        </div>
      )}
      <div className="flex h-9 flex-none items-center gap-2 px-3">
        <button
          onClick={toggleTerminal}
          className="flex items-center gap-1.5"
          title={terminalOpen ? (lang === "ru" ? "Свернуть терминал" : "Collapse terminal") : (lang === "ru" ? "Развернуть терминал" : "Expand terminal")}
        >
          <span className="kicker hover:text-dim">{lang === "ru" ? "Терминал" : "Terminal"}</span>
          {terminalOpen ? (
            <ChevronDown size={12} className="text-faint" />
          ) : (
            <ChevronUp size={12} className="text-faint" />
          )}
        </button>
        <span className="mono hidden text-[10.5px] text-faint md:inline">
          resources/engine/NanoDecompilerCLI
        </span>

        {terminalOpen && (
          <div className="ml-2 flex items-center gap-1">
            {filters.map(f => (
              <button
                key={f.id}
                onClick={() => setLogFilter(f.id)}
                className={cn(
                  "chip h-[22px] px-2 text-[10px] transition-colors",
                  logFilter === f.id
                    ? "border-acid/40 bg-acid/10 text-acid"
                    : "hover:border-line-strong hover:text-dim",
                )}
              >
                {f.label}
                <span className={logFilter === f.id ? "text-acid/70" : "text-faint"}>{counts[f.id]}</span>
              </button>
            ))}
            {malwareCount > 0 && (
              <button
                type="button"
                onClick={() => setSearchQuery(searchQuery === "malware" ? "" : "malware")}
                className={cn(
                  "chip h-[22px] px-2 text-[10px] transition-colors border-err/50 text-err flex items-center gap-1",
                  searchQuery.toLowerCase() === "malware" ? "bg-err/25 font-bold shadow-sm" : "bg-err/10 hover:bg-err/20",
                )}
                title={lang === "ru" ? "Показать только предупреждения безопасности и вредоносный код" : "Show security and malware findings"}
              >
                <ShieldAlert size={11} className="text-err" />
                <span>{lang === "ru" ? "Угрозы" : "Threats"}</span>
                <span className="font-mono text-err font-bold">{malwareCount}</span>
              </button>
            )}
          </div>
        )}

        <div className="flex-1" />

        {terminalOpen && (
          <>
            <div className="relative flex items-center mr-1">
              <input
                type="text"
                value={searchQuery}
                onChange={e => setSearchQuery(e.target.value)}
                placeholder={lang === "ru" ? "Поиск..." : "Search..."}
                className="h-[22px] w-24 rounded border border-line bg-surface/70 px-2 text-[10.5px] text-ink placeholder:text-faint focus:w-36 focus:border-line-strong focus:outline-none transition-all"
              />
              {searchQuery && (
                <button
                  onClick={() => setSearchQuery("")}
                  className="absolute right-1 text-faint hover:text-dim text-[10px]"
                  title={lang === "ru" ? "Очистить" : "Clear"}
                >
                  ✕
                </button>
              )}
            </div>
            <button
              className="icon-btn h-7 w-7"
              data-active={stick}
              title={
                stick
                  ? (lang === "ru" ? "Автопрокрутка включена" : "Autoscroll enabled")
                  : (lang === "ru" ? "Автопрокрутка приостановлена (нажмите для включения)" : "Autoscroll paused (click to resume)")
              }
              onClick={() => {
                const next = !stick;
                setStick(next);
                if (next) jumpToEnd();
              }}
            >
              <ArrowDownToLine size={13} className={stick ? "text-acid" : "text-warn"} />
            </button>
            <button
              className="icon-btn h-7 w-7"
              title={t(lang, "term.copy")}
              onClick={() => {
                if (searchQuery.trim()) {
                  if (visible.length === 0) return;
                  const text = visible.map(l => `[${fmtClock(l.at)}] [${l.tag}] ${l.msg}`).join("\n");
                  copyText(text, lang === "ru" ? `Лог (${visible.length} строк)` : `Log (${visible.length} lines)`);
                } else {
                  copyLog();
                }
              }}
            >
              <Copy size={13} />
            </button>
            <button className="icon-btn h-7 w-7" title={`${t(lang, "term.clear")} (Ctrl L)`} onClick={clearLog}>
              <Trash2 size={13} />
            </button>
          </>
        )}
      </div>

      {terminalOpen && (
        <div className="relative min-h-0 flex-1 border-t border-line/60">
          <div
            ref={scrollRef}
            onScroll={onScroll}
            className="mono h-full overflow-y-auto px-3 py-2 text-[11.5px]"
          >
            {visible.length === 0 && (
              <p className="text-faint">
                {lang === "ru" ? "// движок молчит — запустите декомпиляцию" : "// engine is idle — start decompilation"}
              </p>
            )}
            {visible.map(l => (
              <div
                key={l.id}
                className={cn("flex gap-3 leading-[1.75]", l.level === "err" && "-mx-3 bg-err/6 px-3")}
              >
                <span className="w-[64px] flex-none text-faint/70 tabular-nums">{fmtClock(l.at)}</span>
                <span className={cn("w-[58px] flex-none", TAG_COLOR[l.level])}>[{l.tag}]</span>
                <span
                  className={cn("flex-1 break-words whitespace-pre-wrap font-mono", l.level === "err" ? "text-err/90" : "text-ink/85")}
                >
                  {formatTerminalMessage(l.msg)}
                </span>
              </div>
            ))}
            {runningJob && (
              <div className="mt-0.5 flex gap-3 leading-[1.75]">
                <span className="w-[64px] flex-none" />
                <span className="w-[58px] flex-none" />
                <span className="animate-caret inline-block h-[13px] w-[7px] translate-y-[3px] bg-acid" />
              </div>
            )}
          </div>

          {!stick && (
            <button
              onClick={jumpToEnd}
              className="btn btn-tonal absolute right-4 bottom-3 h-7 gap-1.5 rounded-lg border border-warn/40 bg-surface px-2.5 text-[11px] text-warn shadow-lg hover:border-acid hover:text-acid"
            >
              <ArrowDownToLine size={12} className="animate-bounce" />
              {lang === "ru" ? "Вниз (пауза)" : "To bottom (paused)"}
            </button>
          )}
        </div>
      )}
    </div>
  );
}
