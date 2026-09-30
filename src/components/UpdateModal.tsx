import { Check, Download, FileText, Loader2, RefreshCw, Sparkles, Terminal, X } from "lucide-react";
import { useEngine } from "../state/engine";
import { Kbd } from "./ui";
import { cn } from "../utils/cn";
import { ChangelogView } from "./ChangelogView";
import { formatVersionsDisplay } from "../lib/model";

export function UpdateModal() {
  const {
    setUpdateModalOpen,
    updateInfo,
    downloadProgress,
    engineVersion,
    guiVersion,
    checkForUpdates,
    applyEngineUpdate,
    applyClientUpdate,
  } = useEngine();

  const isUpToDate = !updateInfo.checking && updateInfo.kind === "none" && !updateInfo.error;
  const hasUpdate = updateInfo.kind === "engine" || updateInfo.kind === "client";

  return (
    <div
      className="fixed inset-0 z-[110] grid place-items-center bg-black/75 p-4"
      onMouseDown={e => {
        if (e.target === e.currentTarget && !updateInfo.applying) setUpdateModalOpen(false);
      }}
    >
      <div
        role="dialog"
        aria-modal="true"
        aria-label="Обновления"
        className="animate-rise flex max-h-[85vh] w-[520px] flex-col overflow-hidden rounded-2xl border border-line bg-surface shadow-2xl shadow-black/60"
      >
        <div className="flex h-11 flex-none items-center gap-2 border-b border-line px-4">
          <h2 className="text-[13px] font-semibold text-ink">Центр обновлений</h2>
          <div className="flex-1" />
          <Kbd>Esc</Kbd>
          <button
            className="icon-btn h-7 w-7"
            onClick={() => setUpdateModalOpen(false)}
            aria-label="Закрыть"
            disabled={updateInfo.applying}
          >
            <X size={14} />
          </button>
        </div>

        <div className="flex flex-1 flex-col overflow-y-auto px-6 py-6 text-center">
          {/* Иконка-индикатор состояния */}
          <div className="mb-4 flex justify-center">
            <div
              className={cn(
                "grid h-16 w-16 place-items-center rounded-full border transition-all",
                updateInfo.checking
                  ? "border-line bg-bg"
                  : hasUpdate
                    ? "border-acid/50 bg-acid/10 shadow-lg shadow-acid/10"
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
          </div>

          {updateInfo.checking ? (
            <p className="text-[13.5px] text-dim">Проверяю обновления на сервере…</p>
          ) : updateInfo.kind === "engine" ? (
            <div className="space-y-4">
              <div>
                <p className="text-[14px] font-semibold text-ink">Доступно обновление движка</p>
                <p className="mono mt-1 text-[12px] text-faint">
                  {engineVersion?.replace(/^NanoDecompiler /, "") ?? "?"} → {updateInfo.latestVersion}
                </p>
              </div>

              {/* Прогресс-бар загрузки */}
              {updateInfo.applying && (
                <div className="space-y-2 rounded-xl border border-line bg-bg/60 p-3 text-left">
                  <div className="flex items-center justify-between text-[12px] font-mono text-ink">
                    <span className="flex items-center gap-1.5">
                      <Loader2 size={13} className="animate-spin text-acid" />
                      Загрузка движка...
                    </span>
                    <span className="font-semibold text-acid">
                      {downloadProgress?.percent !== null && downloadProgress?.percent !== undefined
                        ? `${downloadProgress.percent}%`
                        : "Загрузка..."}
                    </span>
                  </div>
                  <div className="h-2 w-full overflow-hidden rounded-full bg-line/60">
                    <div
                      className="h-full bg-acid transition-all duration-150 ease-out"
                      style={{
                        width: `${downloadProgress?.percent ?? (downloadProgress ? 100 : 0)}%`,
                      }}
                    />
                  </div>
                  {downloadProgress && (
                    <div className="flex justify-between text-[11px] font-mono text-faint">
                      <span>
                        {(downloadProgress.downloaded / (1024 * 1024)).toFixed(1)} MB
                        {downloadProgress.total ? ` / ${(downloadProgress.total / (1024 * 1024)).toFixed(1)} MB` : ""}
                      </span>
                      {downloadProgress.percent !== null && <span>{downloadProgress.percent}%</span>}
                    </div>
                  )}
                </div>
              )}

              <button
                className={cn(
                  "btn btn-acid h-10 w-full text-[13px] font-medium shadow-md shadow-acid/15",
                  updateInfo.applying && "pointer-events-none opacity-70",
                )}
                onClick={applyEngineUpdate}
                disabled={updateInfo.applying}
              >
                {updateInfo.applying ? <Loader2 size={14} className="animate-spin" /> : <Download size={14} />}
                {updateInfo.applying ? "Обновляю движок…" : "Обновить движок"}
              </button>
            </div>
          ) : updateInfo.kind === "client" ? (
            <div className="space-y-4">
              <div>
                <p className="text-[14px] font-semibold text-ink">Доступна новая версия приложения</p>
                <p className="mono mt-1 text-[12px] text-faint">
                  v{guiVersion ?? "?"} → v{updateInfo.latestVersion}
                </p>
              </div>

              {/* Прогресс-бар загрузки */}
              {updateInfo.applying && (
                <div className="space-y-2 rounded-xl border border-line bg-bg/60 p-3 text-left">
                  <div className="flex items-center justify-between text-[12px] font-mono text-ink">
                    <span className="flex items-center gap-1.5">
                      <Loader2 size={13} className="animate-spin text-acid" />
                      {updateInfo.applyingKind === "engine" ? "Загрузка движка..." : "Загрузка установщика..."}
                    </span>
                    <span className="font-semibold text-acid">
                      {downloadProgress?.percent !== null && downloadProgress?.percent !== undefined
                        ? `${downloadProgress.percent}%`
                        : "Загрузка..."}
                    </span>
                  </div>
                  <div className="h-2 w-full overflow-hidden rounded-full bg-line/60">
                    <div
                      className="h-full bg-acid transition-all duration-150 ease-out"
                      style={{
                        width: `${downloadProgress?.percent ?? (downloadProgress ? 100 : 0)}%`,
                      }}
                    />
                  </div>
                  {downloadProgress && (
                    <div className="flex justify-between text-[11px] font-mono text-faint">
                      <span>
                        {(downloadProgress.downloaded / (1024 * 1024)).toFixed(1)} MB
                        {downloadProgress.total ? ` / ${(downloadProgress.total / (1024 * 1024)).toFixed(1)} MB` : ""}
                      </span>
                      {downloadProgress.percent !== null && <span>{downloadProgress.percent}% завершено</span>}
                    </div>
                  )}
                </div>
              )}

              <div className="flex flex-col gap-2">
                <button
                  className={cn(
                    "btn btn-acid h-10 w-full text-[13px] font-medium shadow-md shadow-acid/15",
                    updateInfo.applying && "pointer-events-none opacity-70",
                  )}
                  onClick={applyClientUpdate}
                  disabled={updateInfo.applying}
                >
                  {updateInfo.applying && updateInfo.applyingKind === "client" ? (
                    <Loader2 size={14} className="animate-spin" />
                  ) : (
                    <Download size={14} />
                  )}
                  {updateInfo.applying && updateInfo.applyingKind === "client"
                    ? "Скачивание установщика…"
                    : "Скачать и обновить всё (приложение и движок)"}
                </button>

                {updateInfo.downloadUrl && (
                  <button
                    className={cn(
                      "btn btn-tonal h-9 w-full text-[12.5px] border border-line hover:border-acid/40",
                      updateInfo.applying && "pointer-events-none opacity-70",
                    )}
                    onClick={applyEngineUpdate}
                    disabled={updateInfo.applying}
                  >
                    {updateInfo.applying && updateInfo.applyingKind === "engine" ? (
                      <Loader2 size={13} className="animate-spin" />
                    ) : (
                      <Terminal size={13} />
                    )}
                    {updateInfo.applying && updateInfo.applyingKind === "engine"
                      ? "Скачивание движка…"
                      : "Обновить только движок (CLI)"}
                  </button>
                )}
              </div>
            </div>
          ) : updateInfo.kind === "closed_beta" ? (
            <div className="space-y-4">
              <div>
                <p className="text-[14px] font-semibold text-ink">У вас закрытая Бета Версия</p>
                <p className="mono mt-1 text-[11.5px] text-faint">
                  {engineVersion?.replace(/^NanoDecompiler /, "") ?? guiVersion ?? "?"} — новее последнего
                  опубликованного релиза ({updateInfo.latestVersion})
                </p>
              </div>

              {updateInfo.downloadUrl && (
                <button
                  className={cn(
                    "btn btn-tonal h-9 w-full text-[12.5px]",
                    updateInfo.applying && "pointer-events-none opacity-70",
                  )}
                  onClick={applyEngineUpdate}
                  disabled={updateInfo.applying}
                >
                  <Terminal size={13} />
                  Переустановить последний релиз движка (CLI)
                </button>
              )}

              <button className="btn btn-ghost h-9 w-full text-[12.5px]" onClick={() => checkForUpdates()}>
                <RefreshCw size={13} />
                Проверить ещё раз
              </button>
            </div>
          ) : updateInfo.error ? (
            <div className="space-y-3">
              <p className="text-[13px] text-err">{updateInfo.error}</p>
              <button className="btn btn-tonal h-9 w-full text-[12.5px]" onClick={() => checkForUpdates()}>
                <RefreshCw size={13} />
                Проверить снова
              </button>
            </div>
          ) : isUpToDate ? (
            <div className="space-y-3">
              <p className="text-[14px] font-medium text-ink">У вас установлена последняя версия</p>
              <p className="mono text-[11.5px] text-faint">
                {formatVersionsDisplay(engineVersion, guiVersion)}
              </p>
              <div className="flex gap-2">
                <button className="btn btn-ghost h-9 flex-1 text-[12.5px]" onClick={() => checkForUpdates()}>
                  <RefreshCw size={13} />
                  Проверить снова
                </button>
                {updateInfo.downloadUrl && (
                  <button
                    className={cn(
                      "btn btn-tonal h-9 flex-1 text-[12px]",
                      updateInfo.applying && "pointer-events-none opacity-70",
                    )}
                    onClick={applyEngineUpdate}
                    disabled={updateInfo.applying}
                  >
                    <Terminal size={13} />
                    Переустановить движок
                  </button>
                )}
              </div>
            </div>
          ) : (
            <button className="btn btn-acid h-9 w-full text-[12.5px]" onClick={() => checkForUpdates()}>
              <RefreshCw size={13} />
              Проверить обновления
            </button>
          )}

          {/* Блок списка изменений (Changelog) */}
          {updateInfo.changelog && (
            <div className="mt-5 w-full rounded-xl border border-line bg-bg/40 p-3.5 text-left shadow-inner">
              <div className="mb-2 flex items-center justify-between text-[12px] font-medium text-ink">
                <div className="flex items-center gap-1.5">
                  <FileText size={13} className="text-acid" />
                  <span>Список изменений {hasUpdate && updateInfo.latestVersion ? `(v${updateInfo.latestVersion})` : "релиза"}:</span>
                </div>
              </div>
              <div className="max-h-60 overflow-y-auto pr-1 scrollbar-thin">
                <ChangelogView
                  content={updateInfo.changelog}
                  latestVersion={updateInfo.latestVersion}
                  hasUpdate={hasUpdate}
                />
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}
