import { Minus, Square, X } from "lucide-react";
import { useEngine } from "../state/engine";

function LogoMark() {
  return (
    <span className="grid size-[22px] flex-none place-items-center rounded-[6px] border border-acid/35 bg-acid/10">
      {/* раскрытые скобки: jar раскрывается в исходник */}
      <svg width="11" height="11" viewBox="0 0 12 12" fill="none" aria-hidden>
        <path d="M4.5 2 2 6l2.5 4" stroke="#9bf246" strokeWidth="1.4" strokeLinecap="round" strokeLinejoin="round" />
        <path d="M7.5 2 10 6l-2.5 4" stroke="#9bf246" strokeWidth="1.4" strokeLinecap="round" strokeLinejoin="round" opacity=".45" />
      </svg>
    </span>
  );
}

export function Titlebar() {
  const { runningJob, selectedJob, toast, guiVersion } = useEngine();

  const center = runningJob
    ? `декомпиляция: ${runningJob.fileName}`
    : selectedJob
      ? selectedJob.fileName
      : "готов к работе";

  const windowHint = () => toast("Кнопки окна работают в Electron-сборке", "info");

  return (
    <header className="flex h-9 flex-none items-center gap-3 border-b border-line bg-bg px-3 select-none">
      <div className="flex items-center gap-2">
        <LogoMark />
        <span className="mono text-[12px] font-semibold tracking-tight text-ink">
          NanoDecompiler
        </span>
        <span className="chip h-[18px] px-1.5 text-[10px]">GUI v{guiVersion ?? "…"}</span>
      </div>

      <div className="mono flex-1 truncate text-center text-[11px] text-faint">{center}</div>

      <div className="flex items-center gap-0.5">
        <button className="icon-btn h-7 w-9 rounded-md" title="Свернуть" onClick={windowHint}>
          <Minus size={13} />
        </button>
        <button className="icon-btn h-7 w-9 rounded-md" title="Развернуть" onClick={windowHint}>
          <Square size={11} />
        </button>
        <button
          className="icon-btn h-7 w-9 rounded-md hover:bg-err/15 hover:text-err"
          title="Закрыть"
          onClick={windowHint}
        >
          <X size={14} />
        </button>
      </div>
    </header>
  );
}
