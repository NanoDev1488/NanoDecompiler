import { useState } from "react";
import { Lock, Globe, CheckCircle2, AlertCircle, Loader2, X } from "lucide-react";
import { useEngine } from "../state/engine";

function GithubIcon({ size = 16, className }: { size?: number; className?: string }) {
  return (
    <svg
      width={size}
      height={size}
      viewBox="0 0 24 24"
      fill="currentColor"
      className={className}
      aria-hidden="true"
    >
      <path d="M12 .5C5.73.5.5 5.73.5 12c0 5.1 3.29 9.43 7.86 10.96.58.1.79-.25.79-.56 0-.28-.01-1.02-.02-2-3.2.7-3.88-1.54-3.88-1.54-.52-1.34-1.28-1.7-1.28-1.7-1.05-.72.08-.7.08-.7 1.16.08 1.77 1.2 1.77 1.2 1.03 1.77 2.7 1.26 3.36.96.1-.75.4-1.26.73-1.55-2.55-.29-5.24-1.28-5.24-5.7 0-1.26.45-2.29 1.19-3.09-.12-.29-.52-1.46.11-3.05 0 0 .97-.31 3.18 1.18a10.98 10.98 0 0 1 5.79 0c2.2-1.49 3.17-1.18 3.17-1.18.63 1.59.23 2.76.11 3.05.74.8 1.19 1.83 1.19 3.09 0 4.43-2.7 5.4-5.27 5.69.42.36.78 1.07.78 2.17 0 1.57-.01 2.83-.01 3.22 0 .31.21.67.8.55A11.5 11.5 0 0 0 23.5 12C23.5 5.73 18.27.5 12 .5Z" />
    </svg>
  );
}

export function GitHubExportModal({
  open,
  onClose,
  outDir,
  defaultRepoName,
}: {
  open: boolean;
  onClose: () => void;
  outDir: string;
  defaultRepoName: string;
}) {
  const [token, setToken] = useState("");
  const [repoName, setRepoName] = useState(defaultRepoName.replace(/[^a-zA-Z0-9._-]/g, "-"));
  const [isPrivate, setIsPrivate] = useState(false);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [successUrl, setSuccessUrl] = useState<string | null>(null);

  if (!open) return null;

  async function handleExport() {
    if (!token.trim()) {
      setError("Укажите GitHub Personal Access Token (PAT)");
      return;
    }
    if (!repoName.trim()) {
      setError("Укажите имя репозитория");
      return;
    }

    setLoading(true);
    setError(null);
    setSuccessUrl(null);

    try {
      const res = await (window.nano as any).exportToGitHub({
        outDir,
        repoName: repoName.trim(),
        token: token.trim(),
        isPrivate,
      });

      if (res.ok && res.url) {
        setSuccessUrl(res.url);
      } else {
        setError(res.error || "Не удалось экспортировать проект в GitHub");
      }
    } catch (e: any) {
      setError(e.message || "Ошибка соединения с GitHub");
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="fixed inset-0 z-[100] flex items-center justify-center bg-black/60 backdrop-blur-sm p-4">
      <div className="relative w-full max-w-lg overflow-hidden rounded-xl border border-line bg-surface shadow-2xl animate-rise">
        {/* Заголовок */}
        <div className="flex items-center justify-between border-b border-line px-5 py-3.5 bg-raised/40">
          <div className="flex items-center gap-2.5">
            <GithubIcon size={18} className="text-acid" />
            <h3 className="font-semibold text-ink text-sm">Экспорт в GitHub Репозиторий</h3>
          </div>
          <button
            onClick={onClose}
            className="text-faint hover:text-ink transition-colors p-1 rounded-lg"
          >
            <X size={16} />
          </button>
        </div>

        {/* Тело */}
        <div className="p-5 space-y-4 text-xs">
          {successUrl ? (
            <div className="space-y-4 py-3 text-center">
              <CheckCircle2 size={40} className="text-acid mx-auto" />
              <div className="space-y-1">
                <div className="font-semibold text-ink text-sm">Репозиторий успешно создан!</div>
                <div className="text-faint text-[11px]">Декомпилированный проект запушен на GitHub:</div>
              </div>
              <a
                href={successUrl}
                target="_blank"
                rel="noreferrer"
                className="btn btn-acid mx-auto inline-flex items-center gap-2 text-xs"
              >
                <Globe size={14} /> Открыть на GitHub
              </a>
            </div>
          ) : (
            <>
              <div>
                <label className="block font-medium text-ink/80 mb-1">Имя нового репозитория</label>
                <input
                  type="text"
                  value={repoName}
                  onChange={(e) => setRepoName(e.target.value)}
                  placeholder="MyPlugin-Sources"
                  className="w-full rounded-lg border border-line bg-raised px-3 py-2 text-ink outline-none focus:border-acid transition-colors font-mono text-[11px]"
                />
              </div>

              <div>
                <div className="flex items-center justify-between mb-1">
                  <label className="font-medium text-ink/80">GitHub Personal Access Token (PAT)</label>
                  <a
                    href="https://github.com/settings/tokens/new?scopes=repo"
                    target="_blank"
                    rel="noreferrer"
                    className="text-[10px] text-acid hover:underline"
                  >
                    Создать токен (scope: repo) ↗
                  </a>
                </div>
                <input
                  type="password"
                  value={token}
                  onChange={(e) => setToken(e.target.value)}
                  placeholder="ghp_xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
                  className="w-full rounded-lg border border-line bg-raised px-3 py-2 text-ink outline-none focus:border-acid transition-colors font-mono text-[11px]"
                />
                <span className="text-[10px] text-faint mt-1 block">
                  Токен используется только для этой операции и нигде не сохраняется на диске.
                </span>
              </div>

              <div className="flex items-center gap-2 pt-1">
                <input
                  type="checkbox"
                  id="privateRepo"
                  checked={isPrivate}
                  onChange={(e) => setIsPrivate(e.target.checked)}
                  className="rounded border-line bg-raised text-acid focus:ring-acid"
                />
                <label htmlFor="privateRepo" className="cursor-pointer text-ink/80 flex items-center gap-1.5 select-none">
                  <Lock size={12} className="text-faint" /> Сделать репозиторий приватным
                </label>
              </div>

              {error && (
                <div className="rounded-lg border border-err/30 bg-err/10 p-2.5 text-err flex items-start gap-2 text-[11px]">
                  <AlertCircle size={14} className="flex-none mt-0.5" />
                  <span>{error}</span>
                </div>
              )}
            </>
          )}
        </div>

        {/* Подвал */}
        {!successUrl && (
          <div className="flex items-center justify-end gap-2 border-t border-line px-5 py-3 bg-raised/20">
            <button
              onClick={onClose}
              disabled={loading}
              className="btn btn-ghost text-xs"
            >
              Отмена
            </button>
            <button
              onClick={handleExport}
              disabled={loading}
              className="btn btn-acid text-xs inline-flex items-center gap-1.5"
            >
              {loading && <Loader2 size={13} className="animate-spin" />}
              {loading ? "Создание и push..." : "Экспортировать"}
            </button>
          </div>
        )}
      </div>
    </div>
  );
}
