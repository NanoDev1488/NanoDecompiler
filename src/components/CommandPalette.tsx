import {
  ClipboardCopy,
  FolderOpen,
  FolderOutput,
  ListX,
  PanelBottom,
  Play,
  Search,
  Settings2,
  Square,
  Trash2,
} from "lucide-react";
import { useEffect, useMemo, useRef, useState, type ComponentType } from "react";
import { useEngine } from "../state/engine";
import { cn } from "../utils/cn";

interface Action {
  id: string;
  label: string;
  hint?: string;
  icon: ComponentType<{ size?: number | string; className?: string }>;
  disabled?: boolean;
  run(): void;
}

export function CommandPalette() {
  const engine = useEngine();
  const [query, setQuery] = useState("");
  const [index, setIndex] = useState(0);
  const listRef = useRef<HTMLDivElement>(null);

  const { setPaletteOpen, runningJob, queuedCount, selectedJob, toast } = engine;

  const actions = useMemo<Action[]>(
    () => [
      {
        id: "start",
        label: queuedCount > 1 ? `Запустить очередь (${queuedCount})` : "Запустить декомпиляцию",
        icon: Play,
        disabled: runningJob !== null || queuedCount === 0,
        run: engine.startQueue,
      },
      {
        id: "stop",
        label: "Остановить текущую задачу",
        hint: runningJob?.fileName,
        icon: Square,
        disabled: runningJob === null,
        run: engine.stopRunning,
      },
      {
        id: "open-jar",
        label: "Открыть .jar…",
        hint: "Ctrl O",
        icon: FolderOpen,
        run: engine.openFileDialog,
      },
      {
        id: "open-out",
        label: "Открыть папку результата",
        icon: FolderOutput,
        disabled: selectedJob?.status !== "done",
        run: () => selectedJob && engine.openOutput(selectedJob),
      },
      {
        id: "copy-log",
        label: "Скопировать лог",
        icon: ClipboardCopy,
        run: engine.copyLog,
      },
      {
        id: "clear-log",
        label: "Очистить терминал",
        hint: "Ctrl L",
        icon: Trash2,
        run: engine.clearLog,
      },
      {
        id: "toggle-terminal",
        label: engine.terminalOpen ? "Свернуть терминал" : "Развернуть терминал",
        icon: PanelBottom,
        run: engine.toggleTerminal,
      },
      {
        id: "clear-queue",
        label: "Очистить список задач",
        icon: ListX,
        run: engine.clearQueue,
      },
      {
        id: "settings",
        label: "Открыть настройки",
        hint: "Ctrl ,",
        icon: Settings2,
        run: () => engine.setSettingsOpen(true),
      },
    ],
    [engine, queuedCount, runningJob, selectedJob],
  );

  const filtered = useMemo(() => {
    const q = query.trim().toLowerCase();
    if (!q) return actions;
    return actions.filter(a => a.label.toLowerCase().includes(q));
  }, [actions, query]);

  useEffect(() => setIndex(0), [query]);

  useEffect(() => {
    const el = listRef.current?.children[index] as HTMLElement | undefined;
    el?.scrollIntoView({ block: "nearest" });
  }, [index]);

  const runAction = (a: Action) => {
    if (a.disabled) {
      toast("Сейчас недоступно", "warn");
      return;
    }
    setPaletteOpen(false);
    a.run();
  };

  const onKey = (e: React.KeyboardEvent) => {
    if (e.key === "ArrowDown") {
      e.preventDefault();
      setIndex(i => Math.min(filtered.length - 1, i + 1));
    } else if (e.key === "ArrowUp") {
      e.preventDefault();
      setIndex(i => Math.max(0, i - 1));
    } else if (e.key === "Enter") {
      e.preventDefault();
      const a = filtered[index];
      if (a) runAction(a);
    }
  };

  return (
    <div
      className="fixed inset-0 z-50 flex justify-center bg-black/60 pt-[11vh] backdrop-blur-[2px]"
      onMouseDown={e => {
        if (e.target === e.currentTarget) setPaletteOpen(false);
      }}
    >
      <div
        role="dialog"
        aria-modal="true"
        aria-label="Палитра команд"
        className="animate-rise flex h-fit max-h-[420px] w-[480px] flex-col overflow-hidden rounded-2xl border border-line bg-surface shadow-2xl shadow-black/50"
      >
        <div className="flex h-11 flex-none items-center gap-2.5 border-b border-line px-3.5">
          <Search size={14} className="flex-none text-faint" />
          <input
            autoFocus
            value={query}
            onChange={e => setQuery(e.target.value)}
            onKeyDown={onKey}
            placeholder="Команда или действие…"
            className="w-full bg-transparent text-[13px] text-ink outline-none placeholder:text-faint"
            spellCheck={false}
          />
          <span className="kbd">Esc</span>
        </div>

        <div ref={listRef} className="min-h-0 flex-1 overflow-y-auto p-1.5">
          {filtered.length === 0 && (
            <p className="mono px-2 py-3 text-[11px] text-faint">// такой команды нет</p>
          )}
          {filtered.map((a, i) => {
            const Icon = a.icon;
            return (
              <button
                key={a.id}
                onMouseEnter={() => setIndex(i)}
                onClick={() => runAction(a)}
                disabled={false}
                className={cn(
                  "flex w-full items-center gap-2.5 rounded-lg px-2.5 py-2 text-left text-[12.5px]",
                  i === index ? "bg-raised text-ink" : "text-dim",
                  a.disabled && "opacity-40",
                )}
              >
                <Icon size={14} className={cn("flex-none", i === index ? "text-acid" : "text-faint")} />
                <span className="flex-1 truncate">{a.label}</span>
                {a.hint && <span className="kbd">{a.hint}</span>}
              </button>
            );
          })}
        </div>

        <div className="mono flex-none border-t border-line px-3.5 py-2 text-[10px] text-faint">
          ↑↓ выбор · Enter выполнить · Esc закрыть
        </div>
      </div>
    </div>
  );
}
