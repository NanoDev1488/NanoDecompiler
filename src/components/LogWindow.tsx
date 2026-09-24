import { Minus, Square, X } from "lucide-react";
import { useEffect, useState, type CSSProperties } from "react";

type AppLogEntry = { id: number; ts: number; kind: string; msg: string };

const KIND_COLOR: Record<string, string> = {
  err: "text-err",
  warn: "text-warn",
  ok: "text-acid",
};

function fmtTime(ts: number): string {
  const d = new Date(ts);
  const pad = (n: number) => String(n).padStart(2, "0");
  return `${pad(d.getHours())}:${pad(d.getMinutes())}:${pad(d.getSeconds())}`;
}

// НОВОЕ v1.9.8 (HANDOFF п.16 - окно логов разработчика): отдельное,
// самостоятельное React-дерево для ВТОРОГО BrowserWindow (см.
// electron/main.ts::openLogWindow + src/main.tsx). НЕ использует
// useEngine()/EngineProvider - у этого окна СВОЙ, полностью изолированный
// JS-контекст (другой процесс рендерера), общего состояния с главным
// окном напрямую нет - данные приходят ТОЛЬКО через IPC-буфер в
// main-процессе (window.nano.getAppLog/onAppLogUpdate).
//
// ЧЕСТНАЯ ОГОВОРКА ПО ОБЪЁМУ: источник записей - ТОЛЬКО toast() из
// engine.tsx (см. правку там), не каждая строка лога сборки/декомпиляции
// - это сознательное сужение объёма (полное дублирование движковых логов
// в отдельный буфер - отдельная, более крупная задача), но toast() и так
// покрывает почти все заметные пользователю события (ошибки, успехи,
// предупреждения).
export function LogWindow() {
  const [entries, setEntries] = useState<AppLogEntry[]>([]);
  const [isMax, setIsMax] = useState(false);

  useEffect(() => {
    window.nano.getAppLog().then(setEntries);
    const off = window.nano.onAppLogUpdate(entry => setEntries(prev => [...prev.slice(-999), entry]));
    window.nano.isWindowMaximized().then(setIsMax);
    return off;
  }, []);

  return (
    <div className="flex h-screen flex-col bg-bg text-ink">
      <header
        className="flex h-9 flex-none items-center gap-3 border-b border-line bg-bg px-3 select-none"
        style={{ WebkitAppRegion: "drag" } as CSSProperties}
      >
        <span className="mono text-[12px] font-semibold tracking-tight text-ink">Логи NanoDecompiler</span>
        <span className="chip h-[18px] px-1.5 text-[10px]">{entries.length}</span>
        <div className="flex-1" />
        <div className="flex items-center gap-0.5" style={{ WebkitAppRegion: "no-drag" } as CSSProperties}>
          <button className="icon-btn h-7 w-9 rounded-md" title="Свернуть" onClick={() => window.nano.minimizeWindow()}>
            <Minus size={13} />
          </button>
          <button
            className="icon-btn h-7 w-9 rounded-md"
            title="Развернуть"
            onClick={() => window.nano.toggleMaximizeWindow().then(() => setIsMax(v => !v))}
          >
            <Square size={11} />
          </button>
          <button
            className="icon-btn h-7 w-9 rounded-md hover:bg-err/15 hover:text-err"
            title="Закрыть"
            onClick={() => window.nano.closeWindow()}
          >
            <X size={14} />
          </button>
        </div>
      </header>
      <div className="min-h-0 flex-1 overflow-y-auto px-3 py-2">
        {entries.length === 0 ? (
          <p className="mono py-8 text-center text-[12px] text-faint">Пока пусто - записи появятся по мере работы</p>
        ) : (
          entries.map(e => (
            <div key={e.id} className="mono flex gap-2 border-b border-line/60 py-1 text-[11.5px] leading-snug">
              <span className="flex-none text-faint">{fmtTime(e.ts)}</span>
              <span className={"flex-1 " + (KIND_COLOR[e.kind] ?? "text-ink/90")}>{e.msg}</span>
            </div>
          ))
        )}
      </div>
    </div>
  );
}
