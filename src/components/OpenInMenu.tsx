import { ChevronDown, ExternalLink, File, Folder } from "lucide-react";
import { useEffect, useState } from "react";
import { useEngine } from "../state/engine";

// НОВОЕ v1.7.6 (реальный запрос - "открыть в..." мульти-кнопка со списком:
// VS Code / Antigravity / Блокнот и т.д, серая и недоступна для клика,
// если программа не установлена; при клике - спросить файл или проект").
const EDITOR_LABELS: Record<string, string> = {
  vscode: "VS Code",
  antigravity: "Antigravity",
  sublime: "Sublime Text",
  notepadpp: "Notepad++",
  notepad: "Блокнот",
};
// Порядок показа в меню - самые вероятные варианты сверху.
const EDITOR_ORDER = ["vscode", "antigravity", "sublime", "notepadpp", "notepad"];

export function OpenInMenu({ filePath, projectDir }: { filePath?: string; projectDir: string }) {
  const { toast } = useEngine();
  const [open, setOpen] = useState(false);
  // editorId, для которого сейчас показываем "файл/проект"-подвыбор.
  const [pendingEditor, setPendingEditor] = useState<string | null>(null);
  const [available, setAvailable] = useState<Record<string, boolean> | null>(null);

  useEffect(() => {
    if (open && available === null) {
      window.nano.detectApps().then(setAvailable);
    }
  }, [open, available]);

  const openWith = (editorId: string, target: string, label: string) => {
    window.nano.openWith(editorId, target).then(r => {
      if (!r.ok) toast(r.error ?? `Не удалось открыть в ${EDITOR_LABELS[editorId]}`, "err");
    });
    setOpen(false);
    setPendingEditor(null);
    void label;
  };

  return (
    <div className="relative">
      <button
        className="icon-btn h-7 gap-1 px-2 text-[11.5px]"
        title="Открыть в внешнем редакторе"
        onClick={() => {
          setOpen(v => !v);
          setPendingEditor(null);
        }}
      >
        <ExternalLink size={13} />
        Открыть в…
        <ChevronDown size={11} />
      </button>
      {open && (
        <>
          <div
            className="fixed inset-0 z-20"
            onClick={() => {
              setOpen(false);
              setPendingEditor(null);
            }}
          />
          <div className="animate-rise absolute top-9 right-0 z-30 w-52 overflow-hidden rounded-lg border border-line bg-surface py-1 shadow-xl shadow-black/40">
            {EDITOR_ORDER.map(id => {
              const isAvailable = available?.[id] ?? false;
              const label = EDITOR_LABELS[id];
              if (pendingEditor === id) {
                return (
                  <div key={id} className="border-t border-b border-line py-1 first:border-t-0">
                    <p className="mono px-3 py-1 text-[10.5px] text-faint">{label} - что открыть?</p>
                    {filePath && (
                      <button
                        className="flex w-full items-center gap-2 px-3 py-1.5 text-left text-[12px] text-ink/90 hover:bg-raised"
                        onClick={() => openWith(id, filePath, label)}
                      >
                        <File size={12} /> Текущий файл
                      </button>
                    )}
                    <button
                      className="flex w-full items-center gap-2 px-3 py-1.5 text-left text-[12px] text-ink/90 hover:bg-raised"
                      onClick={() => openWith(id, projectDir, label)}
                    >
                      <Folder size={12} /> Всю папку проекта
                    </button>
                  </div>
                );
              }
              return (
                <button
                  key={id}
                  disabled={!isAvailable}
                  title={isAvailable ? undefined : `${label} не найден в PATH`}
                  className="flex w-full items-center justify-between px-3 py-1.5 text-left text-[12px] text-ink/90 hover:bg-raised disabled:cursor-not-allowed disabled:text-faint disabled:opacity-50 disabled:hover:bg-transparent"
                  onClick={() => isAvailable && setPendingEditor(id)}
                >
                  {label}
                  {available === null && <span className="mono text-[10px] text-faint">…</span>}
                </button>
              );
            })}
          </div>
        </>
      )}
    </div>
  );
}
