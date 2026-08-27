import { ArrowDownToLine, ChevronDown, ChevronUp, Copy, Trash2 } from "lucide-react";
import { useEffect, useMemo, useRef, useState } from "react";
import { useEngine } from "../state/engine";
import { fmtClock, type LogFilter, type LogLevel } from "../lib/model";
import { cn } from "../utils/cn";

const TAG_COLOR: Record<LogLevel, string> = {
  info: "text-faint",
  ok: "text-acid",
  warn: "text-warn",
  err: "text-err",
};

const FILTERS: Array<{ id: LogFilter; label: string }> = [
  { id: "all", label: "все" },
  { id: "info", label: "info" },
  { id: "ok", label: "ok" },
  { id: "warn", label: "warn" },
  { id: "err", label: "err" },
];

export function Terminal() {
  const { log, logFilter, setLogFilter, terminalOpen, toggleTerminal, clearLog, copyLog, runningJob } =
    useEngine();
  const [stick, setStick] = useState(true);
  const scrollRef = useRef<HTMLDivElement>(null);

  const counts = useMemo(() => {
    const c: Record<LogFilter, number> = { all: log.length, info: 0, ok: 0, warn: 0, err: 0 };
    for (const l of log) c[l.level] += 1;
    return c;
  }, [log]);

  const visible = useMemo(
    () => (logFilter === "all" ? log : log.filter(l => l.level === logFilter)),
    [log, logFilter],
  );

  useEffect(() => {
    if (!stick || !terminalOpen) return;
    const el = scrollRef.current;
    if (el) el.scrollTop = el.scrollHeight;
  }, [visible.length, stick, terminalOpen]);

  const onScroll = () => {
    const el = scrollRef.current;
    if (!el) return;
    setStick(el.scrollHeight - el.scrollTop - el.clientHeight < 24);
  };

  const jumpToEnd = () => {
    const el = scrollRef.current;
    if (el) el.scrollTop = el.scrollHeight;
    setStick(true);
  };

  return (
    <div
      className={cn(
        "flex flex-none flex-col border-t border-line bg-bg transition-[height] duration-200",
        terminalOpen ? "h-[228px]" : "h-9",
      )}
    >
      <div className="flex h-9 flex-none items-center gap-2 px-3">
        <button
          onClick={toggleTerminal}
          className="flex items-center gap-1.5"
          title={terminalOpen ? "Свернуть терминал" : "Развернуть терминал"}
        >
          <span className="kicker hover:text-dim">Терминал</span>
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
            {FILTERS.map(f => (
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
          </div>
        )}

        <div className="flex-1" />

        {terminalOpen && (
          <>
            <button
              className="icon-btn h-7 w-7"
              data-active={stick}
              title="Автопрокрутка"
              onClick={() => {
                const next = !stick;
                setStick(next);
                if (next) jumpToEnd();
              }}
            >
              <ArrowDownToLine size={13} />
            </button>
            <button className="icon-btn h-7 w-7" title="Скопировать лог" onClick={copyLog}>
              <Copy size={13} />
            </button>
            <button className="icon-btn h-7 w-7" title="Очистить (Ctrl L)" onClick={clearLog}>
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
              <p className="text-faint">// движок молчит — запустите декомпиляцию</p>
            )}
            {visible.map(l => (
              <div
                key={l.id}
                className={cn("flex gap-3 leading-[1.75]", l.level === "err" && "-mx-3 bg-err/6 px-3")}
              >
                <span className="w-[64px] flex-none text-faint/70 tabular-nums">{fmtClock(l.at)}</span>
                <span className={cn("w-[58px] flex-none", TAG_COLOR[l.level])}>[{l.tag}]</span>
                <span
                  className={cn("flex-1 break-words", l.level === "err" ? "text-err/90" : "text-ink/85")}
                >
                  {l.msg}
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
              className="btn btn-tonal absolute right-3 bottom-2 h-7 px-2.5 text-[11px]"
            >
              <ArrowDownToLine size={12} />к концу
            </button>
          )}
        </div>
      )}
    </div>
  );
}
