import { Bell, ChevronDown, FolderOpen, MessageSquare, Play, Settings2, Square } from "lucide-react";
import { useState } from "react";
import { useEngine } from "../state/engine";
import { cn } from "../utils/cn";

export function AppHeader() {
  const {
    runningJob,
    runningElapsed,
    queuedCount,
    envIssue,
    engineVersion,
    updateInfo,
    startQueue,
    stopRunning,
    stopAll,
    openFileDialog,
    setSettingsOpen,
    setBugReportOpen,
    setUpdateModalOpen,
    setPaletteOpen,
  } = useEngine();

  const running = runningJob !== null;
  const engineLabel = engineVersion?.replace(/^NanoDecompiler /, "") ?? "engine";
  const hasUpdate = updateInfo.kind === "engine" || updateInfo.kind === "client";

  return (
    <div className="flex h-12 flex-none items-center gap-3 overflow-x-auto border-b border-line bg-surface px-3">
      {/* статус движка — ассист-чип с точкой, наследие gui_neon.py */}
      <div
        className={cn(
          "chip max-w-[32vw] flex-none",
          envIssue && "border-err/40 text-err",
          !envIssue && running && "border-acid/40 text-acid",
        )}
        role="status"
      >
        <span
          className={cn(
            "dot",
            envIssue ? "bg-err" : running ? "bg-acid animate-pulse-dot" : "bg-acid",
          )}
        />
        <span className="truncate">
          {envIssue
            ? "окружение: нет Java"
            : running
              ? `${engineLabel} — занят${runningElapsed !== null ? ` · ${(runningElapsed / 1000).toFixed(1)} s` : ""}`
              : `${engineLabel} — готов`}
        </span>
        {envIssue && (
          <button
            className="mono -mr-1 rounded px-1 text-[10px] underline decoration-dotted underline-offset-2 hover:text-ink"
            onClick={() => setSettingsOpen(true)}
          >
            починить
          </button>
        )}
      </div>

      <div className="flex-1" />

      <button className="btn btn-tonal flex-none" onClick={openFileDialog}>
        <FolderOpen size={14} />
        Открыть .jar
        <span className="kbd ml-1 hidden lg:inline">Ctrl O</span>
      </button>

      {running ? (
        queuedCount > 0 ? (
          // По просьбе пользователя: 2+ плагина в очереди (текущий + ещё
          // хотя бы один ожидающий) - основная кнопка "Остановить всё"
          // (текущий + вся очередь), маленькая рядом - остановить только
          // текущий, не трогая очередь. Один плагин - как было раньше.
          <div className="flex flex-none items-center gap-1">
            <button className="btn btn-err flex-none" onClick={stopAll}>
              <Square size={13} />
              Остановить всё
            </button>
            <button
              className="icon-btn h-8 w-8 flex-none border border-line"
              onClick={stopRunning}
              title="Остановить только текущий"
            >
              <Square size={11} />
            </button>
          </div>
        ) : (
          <button className="btn btn-err flex-none" onClick={stopRunning}>
            <Square size={13} />
            Остановить
          </button>
        )
      ) : (
        <button className="btn btn-acid flex-none" onClick={startQueue} disabled={queuedCount === 0}>
          <Play size={14} />
          Запустить
          {queuedCount > 0 && (
            <span className="mono grid h-[18px] min-w-[18px] place-items-center rounded-full bg-black/25 px-1 text-[10px] font-semibold">
              {queuedCount}
            </span>
          )}
        </button>
      )}

      {/* БАГ-ФИКС v1.8.3 (HANDOFF_URGENT п.7 - "слишком много кнопок
          тулбара"): была ЕДИНСТВЕННОЙ кнопкой в хедере, которая пропадала
          ЦЕЛИКОМ на узких экранах (`hidden md:inline-flex`) - мышью
          вызвать палитру команд было НЕЛЬЗЯ вообще, только Ctrl+K
          (и то если человек о нём знает). Остальные кнопки того же ряда
          (см. "Обновления" ниже) скрывают только ТЕКСТОВУЮ подпись,
          сама кнопка+иконка/kbd-хинт остаётся кликабельной всегда -
          привели к тому же паттерну вместо полного исчезновения. */}
      <button className="btn btn-ghost flex-none" onClick={() => setPaletteOpen(true)} title="Палитра команд">
        <span className="kbd">Ctrl K</span>
        <span className="hidden text-faint md:inline">команды</span>
      </button>

      {/* НОВОЕ 1.9.6 (HANDOFF п.10, "группировка кнопок тулбара в
          подпапки: лидер + выпадающий список"). ВАЖНО - выбор группы и
          лидера сделан БЕЗ подтверждения пользователем (визуально
          рискованно без скриншотов, как и предупреждал прошлый хэндофф) -
          решение: "Обновления" (Bell) - лидер, т.к. у неё уже есть
          собственное активное состояние (красная точка при наличии
          обновления - самая "заметная" кнопка группы), "Сообщить о
          проблеме" ушла под стрелку. "Настройки" НЕ сгруппирована -
          используется слишком часто, чтобы прятать за клик. Паттерн
          dropdown скопирован с OpenInMenu.tsx (open-state + оверлей +
          absolute-меню), лидер повторяется первым пунктом списка. */}
      <ToolbarMenu hasUpdate={hasUpdate} setUpdateModalOpen={setUpdateModalOpen} setBugReportOpen={setBugReportOpen} />

      <button
        className="icon-btn flex-none"
        onClick={() => setSettingsOpen(true)}
        aria-label="Настройки"
        title="Настройки (Ctrl ,)"
      >
        <Settings2 size={16} />
      </button>
    </div>
  );
}

function ToolbarMenu({
  hasUpdate,
  setUpdateModalOpen,
  setBugReportOpen,
}: {
  hasUpdate: boolean;
  setUpdateModalOpen: (v: boolean) => void;
  setBugReportOpen: (v: boolean) => void;
}) {
  const [open, setOpen] = useState(false);
  return (
    <div className="relative flex-none">
      <button
        className={cn("btn btn-ghost relative flex-none", hasUpdate && "border-acid/40 text-acid")}
        onClick={() => setOpen(v => !v)}
        aria-label="Обновления и обратная связь"
        title="Обновления и обратная связь"
      >
        <Bell size={14} />
        <span className="hidden lg:inline">Обновления</span>
        <ChevronDown size={11} />
        {hasUpdate && <span className="absolute top-1 right-1.5 size-[7px] rounded-full bg-acid" />}
      </button>
      {open && (
        <>
          {/* БАГ-ФИКС 1.9.10 (реальный репорт - "плашка уходит под сам GUI"):
              z-30 оказался ниже других слоёв приложения (например,
              Toasts.tsx - z-[70]) - выпадающее меню тулбара ДОЛЖНО быть
              выше буквально всего, кроме ничего (это не модалка, а
              overlay-меню, которое просят "самый верх"). */}
          <div className="fixed inset-0 z-[79]" onClick={() => setOpen(false)} />
          <div className="animate-rise absolute top-9 right-0 z-[80] w-52 overflow-hidden rounded-lg border border-line bg-surface py-1 shadow-xl shadow-black/40">
            <button
              className="flex w-full items-center justify-between px-3 py-1.5 text-left text-[12px] text-ink/90 hover:bg-raised"
              onClick={() => {
                setOpen(false);
                setUpdateModalOpen(true);
              }}
            >
              <span className="flex items-center gap-2">
                <Bell size={13} /> Обновления
              </span>
              {hasUpdate && <span className="size-[7px] rounded-full bg-acid" />}
            </button>
            <button
              className="flex w-full items-center gap-2 px-3 py-1.5 text-left text-[12px] text-ink/90 hover:bg-raised"
              onClick={() => {
                setOpen(false);
                setBugReportOpen(true);
              }}
            >
              <MessageSquare size={13} /> Сообщить о проблеме
            </button>
          </div>
        </>
      )}
    </div>
  );
}
