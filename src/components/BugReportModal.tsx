import { Send, X } from "lucide-react";
import { useEffect, useRef, useState } from "react";
import { useEngine } from "../state/engine";
import { t } from "../lib/i18n";

export function BugReportModal({ onClose }: { onClose: () => void }) {
  const { sendBugReport, settings, setSettingsOpen } = useEngine();
  const lang = settings.language;
  const [text, setText] = useState("");
  const [sending, setSending] = useState(false);
  const textareaRef = useRef<HTMLTextAreaElement>(null);

  useEffect(() => {
    textareaRef.current?.focus();
  }, []);

  const canSend = settings.telemetryEnabled && text.trim().length > 0 && !sending;

  const send = async () => {
    if (!canSend) return;
    setSending(true);
    const res = await sendBugReport(text.trim());
    setSending(false);
    if (res.ok) onClose();
  };

  return (
    <div
      className="fixed inset-0 z-50 grid place-items-center bg-black/70"
      onMouseDown={e => e.target === e.currentTarget && onClose()}
    >
      <div className="animate-rise flex w-[520px] flex-col overflow-hidden rounded-2xl border border-line bg-surface shadow-2xl shadow-black/50">
        <div className="flex h-11 flex-none items-center gap-2 border-b border-line px-3.5">
          <span className="text-[13px] font-medium text-ink/90">{t(lang, "bug.title")}</span>
          <div className="flex-1" />
          <button className="icon-btn h-6 w-6" onClick={onClose}>
            <X size={13} />
          </button>
        </div>
        <div className="flex flex-col gap-3 p-4">
          {!settings.telemetryEnabled ? (
            <p className="text-[12px] text-dim">
              {lang === "ru" ? "Отправка отчётов выключена в настройках - " : "Error reporting is disabled in settings - "}
              <button
                className="text-ink underline decoration-dotted underline-offset-2 hover:text-accent"
                onClick={() => {
                  onClose();
                  setSettingsOpen(true);
                }}
              >
                {lang === "ru" ? "включить" : "enable"}
              </button>
              .
            </p>
          ) : (
            <p className="text-[12px] text-dim">
              {t(lang, "bug.desc")}
            </p>
          )}
          <textarea
            ref={textareaRef}
            value={text}
            onChange={e => setText(e.target.value)}
            onKeyDown={e => {
              if (e.key === "Escape") onClose();
              if (e.key === "Enter" && (e.metaKey || e.ctrlKey)) send();
            }}
            disabled={!settings.telemetryEnabled}
            placeholder={t(lang, "bug.placeholder")}
            rows={14}
            className="field mono min-h-[220px] resize-y text-[12.5px]"
            spellCheck
          />
          <div className="flex items-center justify-end gap-2">
            <button className="btn btn-ghost h-8 text-[12px]" onClick={onClose}>
              {t(lang, "bug.cancel")}
            </button>
            <button className="btn btn-tonal h-8 gap-1.5 text-[12px]" disabled={!canSend} onClick={send}>
              <Send size={13} />
              {sending ? t(lang, "bug.sending") : t(lang, "bug.submit")}
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
