import { X } from "lucide-react";
import { useEngine } from "../state/engine";
import { cn } from "../utils/cn";

const DOT: Record<string, string> = {
  info: "bg-dim",
  ok: "bg-acid",
  warn: "bg-warn",
  err: "bg-err",
};

export function Toasts() {
  const { toasts, dismissToast } = useEngine();

  return (
    <div className="pointer-events-none fixed right-3 bottom-9 z-[70] flex w-[340px] flex-col gap-2" aria-live="polite">
      {toasts.map(t => (
        <div
          key={t.id}
          className="animate-rise pointer-events-auto flex items-start gap-2.5 rounded-xl border border-line bg-raised px-3 py-2.5 shadow-lg shadow-black/40"
        >
          <span className={cn("dot mt-[5px]", DOT[t.kind])} />
          <p className="flex-1 text-[12.5px] leading-snug text-ink/90">{t.msg}</p>
          <button
            className="icon-btn h-5 w-5 rounded"
            onClick={() => dismissToast(t.id)}
            aria-label="Скрыть"
          >
            <X size={12} />
          </button>
        </div>
      ))}
    </div>
  );
}
