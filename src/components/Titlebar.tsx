import { Minus, Square, X } from "lucide-react";
import type { CSSProperties } from "react";
import { useEngine } from "../state/engine";

// БАГ-ФИКС/фича: раньше был статичный мотив "раскрытых скобок" - тот же
// стиль, что и в старой заглушке иконки приложения, которую заменили по
// прямой просьбе пользователя ("это ПОЛНЕЙШЕЕ ГОВНО"). Теперь мини-лого
// живо отражает выбранную в настройках иконку приложения (Settings.appIcon)
// вместо одного захардкоженного варианта.
function LogoMark({ variant }: { variant: "terminal" | "layers" }) {
  return (
    <span className="grid size-[22px] flex-none place-items-center rounded-[6px] border border-acid/35 bg-acid/10">
      {variant === "terminal" ? (
        <svg width="12" height="12" viewBox="0 0 24 24" fill="none" aria-hidden>
          <path d="M5 6.5 10.5 12 5 17.5" stroke="#9bf246" strokeWidth="2.4" strokeLinecap="round" strokeLinejoin="round" />
          <path d="M13 17.5h6" stroke="#eaf0ea" strokeWidth="2.4" strokeLinecap="round" />
        </svg>
      ) : (
        <svg width="13" height="13" viewBox="0 0 24 24" fill="none" aria-hidden>
          <path d="M4 8 12 4l8 4-8 4-8-4Z" stroke="#eaf0ea" strokeWidth="1.6" strokeLinejoin="round" opacity=".85" />
          <path d="M4 12l8 4 8-4" stroke="#9bf246" strokeWidth="1.6" strokeLinejoin="round" />
          <path d="M4 16l8 4 8-4" stroke="#eaf0ea" strokeWidth="1.6" strokeLinejoin="round" opacity=".5" />
        </svg>
      )}
    </span>
  );
}

export function Titlebar() {
  const { runningJob, selectedJob, guiVersion, settings } = useEngine();

  const center = runningJob
    ? `декомпиляция: ${runningJob.fileName}`
    : selectedJob
      ? selectedJob.fileName
      : "готов к работе";

  // БАГ-ФИКС (реальный, воспроизведён пользователем - "х2 кнопок от
  // электрона + кастомные не работают"): раньше эти три кнопки были
  // ФИКТИВНЫМИ (просто toast "работают в Electron-сборке"), а родная
  // рамка ОС рисовала СВОИ настоящие кнопки поверх - см. frame:false в
  // main.ts. Теперь это единственные кнопки окна, и они реально работают.
  return (
    <header
      className="flex h-9 flex-none items-center gap-3 border-b border-line bg-bg px-3 select-none"
      style={{ WebkitAppRegion: "drag" } as CSSProperties}
    >
      <div className="flex items-center gap-2">
        <LogoMark variant={settings.appIcon} />
        <span className="mono text-[12px] font-semibold tracking-tight text-ink">
          NanoDecompiler
        </span>
        <span className="chip h-[18px] px-1.5 text-[10px]">GUI v{guiVersion ?? "…"}</span>
      </div>

      <div className="mono flex-1 truncate text-center text-[11px] text-faint">{center}</div>

      <div className="flex items-center gap-0.5" style={{ WebkitAppRegion: "no-drag" } as CSSProperties}>
        <button
          className="icon-btn h-7 w-9 rounded-md"
          title="Свернуть"
          onClick={() => window.nano.minimizeWindow()}
        >
          <Minus size={13} />
        </button>
        <button
          className="icon-btn h-7 w-9 rounded-md"
          title="Развернуть"
          onClick={() => window.nano.toggleMaximizeWindow()}
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
  );
}
