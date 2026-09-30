import { memo, type ReactNode } from "react";

function formatInlineMarkdown(text: string): ReactNode[] {
  const tokens: ReactNode[] = [];
  // Regex to match bold (**...** or __...__), inline code (`...`), markdown links [text](url), and bare URLs (https?://...)
  const regex = /(\*\*[^*]+\*\*|__[^_]+__|`[^`]+`|\[[^\]]+\]\([^)]+\)|https?:\/\/[^\s<)]+)/g;
  let lastIndex = 0;
  let match: RegExpExecArray | null;

  while ((match = regex.exec(text)) !== null) {
    if (match.index > lastIndex) {
      tokens.push(text.slice(lastIndex, match.index));
    }
    const token = match[0];
    if ((token.startsWith("**") && token.endsWith("**")) || (token.startsWith("__") && token.endsWith("__"))) {
      const inner = token.slice(2, -2);
      tokens.push(
        <strong key={match.index} className="font-semibold text-ink">
          {inner}
        </strong>
      );
    } else if (token.startsWith("`") && token.endsWith("`")) {
      tokens.push(
        <code key={match.index} className="mono rounded bg-raised px-1 py-0.5 text-[11px] text-acid font-medium">
          {token.slice(1, -1)}
        </code>
      );
    } else if (token.startsWith("[")) {
      const linkMatch = /\[([^\]]+)\]\(([^)]+)\)/.exec(token);
      if (linkMatch) {
        const [, label, url] = linkMatch;
        tokens.push(
          <a
            key={match.index}
            href={url}
            onClick={e => {
              e.preventDefault();
              window.nano.openExternal(url).catch(() => {});
            }}
            className="text-acid underline hover:text-acid/80"
          >
            {label}
          </a>
        );
      } else {
        tokens.push(token);
      }
    } else if (token.startsWith("http://") || token.startsWith("https://")) {
      tokens.push(
        <a
          key={match.index}
          href={token}
          onClick={e => {
            e.preventDefault();
            window.nano.openExternal(token).catch(() => {});
          }}
          className="text-acid underline hover:text-acid/80 break-all"
        >
          {token}
        </a>
      );
    }
    lastIndex = match.index + token.length;
  }
  if (lastIndex < text.length) {
    tokens.push(text.slice(lastIndex));
  }
  return tokens;
}

export const ChangelogView = memo(function ChangelogView({
  content,
  latestVersion,
  hasUpdate,
}: {
  content: string;
  latestVersion?: string;
  hasUpdate?: boolean;
}) {
  if (!content || !content.trim()) {
    return (
      <p className="mono py-4 text-center text-[11.5px] text-faint">
        // нет описания изменений для этой версии
      </p>
    );
  }

  const lines = content.split(/\r?\n/);
  const elements: ReactNode[] = [];

  for (let i = 0; i < lines.length; i++) {
    const raw = lines[i];
    const trimmed = raw.trim();

    if (!trimmed) {
      elements.push(<div key={`empty-${i}`} className="h-1.5" />);
      continue;
    }

    // Разделительные линии (---, ***, ===)
    if (/^[-*_]{3,}$/.test(trimmed)) {
      elements.push(<hr key={`hr-${i}`} className="my-2 border-line" />);
      continue;
    }

    // Заголовки: #, ##, ###, ####
    if (/^####\s+/.test(trimmed)) {
      elements.push(
        <h5 key={`h5-${i}`} className="mt-2 mb-0.5 text-[11.5px] font-semibold text-ink/90">
          {formatInlineMarkdown(trimmed.replace(/^####\s+/, ""))}
        </h5>
      );
    } else if (/^###\s+/.test(trimmed)) {
      elements.push(
        <h4 key={`h4-${i}`} className="mt-2.5 mb-1 text-[12px] font-semibold text-acid flex items-center gap-1.5">
          <span className="dot bg-acid" />
          <span>{formatInlineMarkdown(trimmed.replace(/^###\s+/, ""))}</span>
        </h4>
      );
    } else if (/^##\s+/.test(trimmed)) {
      elements.push(
        <h3 key={`h3-${i}`} className="mt-3.5 mb-1.5 border-b border-line pb-1 text-[13px] font-bold text-ink">
          {formatInlineMarkdown(trimmed.replace(/^##\s+/, ""))}
        </h3>
      );
    } else if (/^#\s+/.test(trimmed)) {
      elements.push(
        <h2 key={`h2-${i}`} className="mt-4 mb-2 text-[14px] font-bold text-ink">
          {formatInlineMarkdown(trimmed.replace(/^#\s+/, ""))}
        </h2>
      );
    } else if (trimmed.startsWith("- ") || trimmed.startsWith("* ") || trimmed.startsWith("+ ")) {
      const itemText = trimmed.slice(2);
      elements.push(
        <li key={`li-${i}`} className="ml-4 list-disc text-[12px] leading-relaxed text-dim pl-1">
          {formatInlineMarkdown(itemText)}
        </li>
      );
    } else if (/^\d+\.\s+/.test(trimmed)) {
      const match = /^(\d+\.)\s+(.*)/.exec(trimmed);
      elements.push(
        <li key={`oli-${i}`} className="ml-4 list-decimal text-[12px] leading-relaxed text-dim pl-1">
          {match ? formatInlineMarkdown(match[2]) : formatInlineMarkdown(trimmed)}
        </li>
      );
    } else if (trimmed.startsWith("> ")) {
      elements.push(
        <blockquote key={`quote-${i}`} className="my-1.5 border-l-2 border-acid/60 bg-acid/5 pl-3 py-1 text-[11.5px] italic text-dim rounded-r">
          {formatInlineMarkdown(trimmed.slice(2))}
        </blockquote>
      );
    } else {
      elements.push(
        <p key={`p-${i}`} className="text-[12px] leading-relaxed text-dim">
          {formatInlineMarkdown(trimmed)}
        </p>
      );
    }
  }

  return (
    <div className="changelog-markdown space-y-0.5 text-left font-sans select-text">
      {hasUpdate && latestVersion && (
        <div className="mb-2.5 flex items-center justify-between rounded-lg bg-acid/10 px-2.5 py-1.5 border border-acid/30 text-[11.5px] text-acid font-medium">
          <span>Новая версия доступна: {latestVersion}</span>
          <span className="mono rounded bg-acid/20 px-1.5 py-0.5 text-[10px] uppercase tracking-wider">Release</span>
        </div>
      )}
      {elements}
    </div>
  );
});
