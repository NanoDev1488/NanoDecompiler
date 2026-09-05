import { Check, Download, Loader2, RefreshCw, Sparkles, X } from "lucide-react";
import { useEngine } from "../state/engine";
import { Kbd } from "./ui";
import { cn } from "../utils/cn";

// Отдельное модальное окно обновлений - раньше это была вкладка внутри
// SettingsModal ("О сервисе"), по прямой просьбе пользователя вынесено в
// собственное меню верхнего уровня ("отдельное меню как настройки"),
// оформлено в том же визуальном языке (тот же каркас модалки/оверлея),
// но открывается отдельно (см. AppHeader.tsx - кнопка рядом с настройками).
export function UpdateModal() {
  const {
    setUpdateModalOpen,
    updateInfo,
    engineVersion,
    guiVersion,
    checkForUpdates,
    applyEngineUpdate,
    openClientDownload,
  } = useEngine();

  const isUpToDate = !updateInfo.checking && updateInfo.kind === "none" && !updateInfo.error;
  const hasUpdate = updateInfo.kind === "engine" || updateInfo.kind === "client";

  return (
    <div
      className="fixed inset-0 z-50 grid place-items-center bg-black/60 p-4 backdrop-blur-[2px]"
      onMouseDown={e => {
        if (e.target === e.currentTarget) setUpdateModalOpen(false);
      }}
    >
      <div
        role="dialog"
        aria-modal="true"
        aria-label="Обновления"
        className="animate-rise flex w-[440px] flex-col overflow-hidden rounded-2xl border border-line bg-surface shadow-2xl shadow-black/50"
      >
        <div className="flex h-11 flex-none items-center gap-2 border-b border-line px-4">
          <h2 className="text-[13px] font-semibold text-ink">Обновления</h2>
          <div className="flex-1" />
          <Kbd>Esc</Kbd>
          <button className="icon-btn h-7 w-7" onClick={() => setUpdateModalOpen(false)} aria-label="Закрыть">
            <X size={14} />
          </button>
        </div>

        <div className="flex flex-col items-center gap-4 px-6 py-8 text-center">
          {/* иконка-индикатор состояния */}
          <div
            className={cn(
              "grid h-16 w-16 place-items-center rounded-full border",
              updateInfo.checking
                ? "border-line bg-bg"
                : hasUpdate
                  ? "border-acid/40 bg-acid/10"
                  : updateInfo.error
                    ? "border-err/40 bg-err/10"
                    : "border-acid/40 bg-acid/10",
            )}
          >
            {updateInfo.checking ? (
              <Loader2 size={26} className="animate-spin text-faint" />
            ) : hasUpdate ? (
              <Sparkles size={26} className="text-acid" />
            ) : updateInfo.error ? (
              <RefreshCw size={26} className="text-err" />
            ) : (
              <Check size={26} className="text-acid" />
            )}
          </div>

          {updateInfo.checking ? (
            <p className="text-[13px] text-dim">Проверяю обновления…</p>
          ) : updateInfo.kind === "engine" ? (
            <>
              <div>
                <p className="text-[13.5px] font-medium text-ink">Доступно обновление движка</p>
                <p className="mono mt-1 text-[11.5px] text-faint">
                  {engineVersion?.replace(/^NanoDecompiler /, "") ?? "?"} → {updateInfo.latestVersion}
                </p>
              </div>
              <button
                className={cn(
                  "btn btn-acid h-9 w-full text-[12.5px]",
                  updateInfo.applying && "pointer-events-none opacity-70",
                )}
                onClick={applyEngineUpdate}
              >
                {updateInfo.applying ? <Loader2 size={13} className="animate-spin" /> : <Download size={13} />}
                {updateInfo.applying ? "Обновляю…" : "Обновить движок"}
              </button>
            </>
          ) : updateInfo.kind === "client" ? (
            <>
              <div>
                <p className="text-[13.5px] font-medium text-ink">Доступна новая версия приложения</p>
                <p className="mono mt-1 text-[11.5px] text-faint">
                  {guiVersion ?? "?"} → {updateInfo.latestVersion}
                </p>
              </div>
              <button className="btn btn-acid h-9 w-full text-[12.5px]" onClick={openClientDownload}>
                <Download size={13} />
                Скачать
              </button>
            </>
          ) : updateInfo.kind === "closed_beta" ? (
            <>
              <p className="text-[13.5px] font-medium text-ink">У вас закрытая Бета Версия</p>
              <p className="mono mt-1 text-[11.5px] text-faint">
                {engineVersion?.replace(/^NanoDecompiler /, "") ?? guiVersion ?? "?"} — новее последнего
                опубликованного релиза ({updateInfo.latestVersion})
              </p>
              <button className="btn btn-ghost h-9 w-full text-[12.5px]" onClick={() => checkForUpdates()}>
                <RefreshCw size={13} />
                Проверить ещё раз
              </button>
            </>
          ) : updateInfo.error ? (
            <>
              <p className="text-[13px] text-err">{updateInfo.error}</p>
              <button className="btn btn-tonal h-9 w-full text-[12.5px]" onClick={() => checkForUpdates()}>
                <RefreshCw size={13} />
                Проверить снова
              </button>
            </>
          ) : isUpToDate ? (
            <>
              <p className="text-[13.5px] font-medium text-ink">У вас последняя версия</p>
              <p className="mono text-[11.5px] text-faint">
                движок {engineVersion?.replace(/^NanoDecompiler /, "") ?? "…"} · GUI v{guiVersion ?? "…"}
              </p>
              <button className="btn btn-ghost h-9 w-full text-[12.5px]" onClick={() => checkForUpdates()}>
                <RefreshCw size={13} />
                Проверить ещё раз
              </button>
            </>
          ) : (
            <button className="btn btn-acid h-9 w-full text-[12.5px]" onClick={() => checkForUpdates()}>
              <RefreshCw size={13} />
              Проверить обновления
            </button>
          )}
        </div>
      </div>
    </div>
  );
}
