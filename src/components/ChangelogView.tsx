import { memo, type ReactNode } from "react";
import { Check, CheckSquare, Square } from "lucide-react";

/**
 * Парсер встроенного Markdown (bold, italic, code, links, badges, strikethrough).
 */
function formatInlineMarkdown(text: string): ReactNode[] {
  const tokens: ReactNode[] = [];
  // Токены:
  // 1. Bold-italic: ***...*** или ___...___
  // 2. Bold: **...** или __...__
  // 3. Italic: *...* или _..._
  // 4. Strikethrough: ~~...~~
  // 5. Code: `...`
  // 6. Badges: [tag]
  // 7. Links: [label](url)
  // 8. URLs: https?://...
  const regex =
    /(\*\*\*[^*]+\*\*\*|___[^_]+___|\*\*[^*]+\*\*|__[^_]+__|~~[^~]+~~|`[^`]+`|''[^']+''|\[[^\]]+\]\([^)]+\)|\[[A-Za-z0-9_./ -]+\]|https?:\/\/[^\s<)]+|\*[^*\s][^*]*\*|_[^_\s][^_]*_)/g;

  let lastIndex = 0;
  let match: RegExpExecArray | null;

  while ((match = regex.exec(text)) !== null) {
    if (match.index > lastIndex) {
      tokens.push(text.slice(lastIndex, match.index));
    }
    const token = match[0];
    const key = `tok-${match.index}`;

    if ((token.startsWith("***") && token.endsWith("***")) || (token.startsWith("___") && token.endsWith("___"))) {
      tokens.push(
        <strong key={key} className="font-bold italic text-ink break-words">
          {formatInlineMarkdown(token.slice(3, -3))}
        </strong>
      );
    } else if ((token.startsWith("**") && token.endsWith("**")) || (token.startsWith("__") && token.endsWith("__"))) {
      tokens.push(
        <strong key={key} className="font-semibold text-ink break-words">
          {formatInlineMarkdown(token.slice(2, -2))}
        </strong>
      );
    } else if (token.startsWith("~~") && token.endsWith("~~")) {
      tokens.push(
        <del key={key} className="line-through text-faint opacity-80 break-words">
          {formatInlineMarkdown(token.slice(2, -2))}
        </del>
      );
    } else if ((token.startsWith("`") && token.endsWith("`")) || (token.startsWith("''") && token.endsWith("''"))) {
      const codeText = token.startsWith("`") ? token.slice(1, -1) : token.slice(2, -2);
      tokens.push(
        <code key={key} className="mono rounded bg-raised px-1 py-0.5 text-[11px] text-acid font-medium break-all whitespace-pre-wrap">
          {codeText}
        </code>
      );
    } else if (token.startsWith("[") && token.includes("](") && token.endsWith(")")) {
      const linkMatch = /\[([^\]]+)\]\(([^)]+)\)/.exec(token);
      if (linkMatch) {
        const [, label, url] = linkMatch;
        tokens.push(
          <a
            key={key}
            href={url}
            onClick={e => {
              e.preventDefault();
              window.nano.openExternal(url).catch(() => {});
            }}
            className="text-acid underline hover:text-acid/80 font-medium transition-colors break-all"
          >
            {label}
          </a>
        );
      } else {
        tokens.push(token);
      }
    } else if (token.startsWith("[") && token.endsWith("]")) {
      // Бейдж/чип релиза вроде [ObfUpd 15/15] или [Engine]
      const badgeContent = token.slice(1, -1);
      tokens.push(
        <span
          key={key}
          className="mono rounded bg-acid/15 border border-acid/30 px-1.5 py-0.5 text-[10.5px] text-acid font-semibold mx-0.5 select-all inline-block"
        >
          {badgeContent}
        </span>
      );
    } else if (token.startsWith("http://") || token.startsWith("https://")) {
      tokens.push(
        <a
          key={key}
          href={token}
          onClick={e => {
            e.preventDefault();
            window.nano.openExternal(token).catch(() => {});
          }}
          className="text-acid underline hover:text-acid/80 break-all transition-colors"
        >
          {token}
        </a>
      );
    } else if ((token.startsWith("*") && token.endsWith("*")) || (token.startsWith("_") && token.endsWith("_"))) {
      tokens.push(
        <em key={key} className="italic text-dim break-words">
          {formatInlineMarkdown(token.slice(1, -1))}
        </em>
      );
    } else {
      tokens.push(token);
    }
    lastIndex = match.index + token.length;
  }

  if (lastIndex < text.length) {
    tokens.push(text.slice(lastIndex));
  }
  return tokens;
}

interface TableRow {
  isHeader: boolean;
  cells: string[];
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

  const rawLines = content.split(/\r?\n/);
  const lines: string[] = [];

  for (const line of rawLines) {
    const trimmed = line.trim();
    // Отсекаем раздел "Загрузки" и всё, что ниже
    if (/^(?:#+\s*)?(?:📦\s*Загрузки|📦\s*Downloads|Загрузки)\b/i.test(trimmed) || /📦\s*Загрузки/i.test(trimmed)) {
      break;
    }
    lines.push(line);
  }

  if (lines.every(l => !l.trim())) {
    return (
      <p className="mono py-4 text-center text-[11.5px] text-faint">
        // нет описания изменений для этой версии
      </p>
    );
  }

  const elements: ReactNode[] = [];
  let i = 0;

  while (i < lines.length) {
    const raw = lines[i];
    const trimmed = raw.trim();

    if (!trimmed) {
      elements.push(<div key={`empty-${i}`} className="h-1.5" />);
      i++;
      continue;
    }

    // Разделительные линии (---, ***, ===)
    if (/^[-*_]{3,}$/.test(trimmed)) {
      elements.push(<hr key={`hr-${i}`} className="my-2.5 border-line" />);
      i++;
      continue;
    }

    // Таблицы Markdown (| col1 | col2 |)
    if (trimmed.startsWith("|") && trimmed.endsWith("|")) {
      const tableRows: TableRow[] = [];
      let isFirst = true;

      while (i < lines.length) {
        const cur = lines[i].trim();
        if (!cur.startsWith("|") || !cur.endsWith("|")) break;

        // Строка разделителя (|---|---|)
        if (/^\|(?:\s*:?-+:?\s*\|)+$/.test(cur)) {
          i++;
          continue;
        }

        const cells = cur
          .slice(1, -1)
          .split("|")
          .map(c => c.trim());

        tableRows.push({ isHeader: isFirst, cells });
        isFirst = false;
        i++;
      }

      if (tableRows.length > 0) {
        elements.push(
          <div key={`table-${i}`} className="my-2.5 overflow-x-auto rounded-lg border border-line bg-surface/50 shadow-sm">
            <table className="w-full border-collapse text-left text-[11.5px]">
              {tableRows.some(r => r.isHeader) && (
                <thead>
                  <tr className="border-b border-line bg-raised/60 text-ink font-semibold">
                    {tableRows[0].cells.map((cell, cIdx) => (
                      <th key={`th-${cIdx}`} className="px-2.5 py-1.5 font-semibold">
                        {formatInlineMarkdown(cell)}
                      </th>
                    ))}
                  </tr>
                </thead>
              )}
              <tbody className="divide-y divide-line/40">
                {tableRows
                  .filter(r => !r.isHeader)
                  .map((row, rIdx) => (
                    <tr key={`tr-${rIdx}`} className="hover:bg-raised/30 transition-colors">
                      {row.cells.map((cell, cIdx) => (
                        <td key={`td-${rIdx}-${cIdx}`} className="px-2.5 py-1.5 text-dim">
                          {formatInlineMarkdown(cell)}
                        </td>
                      ))}
                    </tr>
                  ))}
              </tbody>
            </table>
          </div>
        );
      }
      continue;
    }

    // Заголовки: #, ##, ###, #### (с пробелом или без него, с ** или без)
    const headerMatch = /^(#{1,6})\s*(.+)$/.exec(trimmed);
    if (headerMatch) {
      const level = headerMatch[1].length;
      const titleText = headerMatch[2];

      if (level === 1) {
        elements.push(
          <h2 key={`h1-${i}`} className="mt-3.5 mb-2 text-[14.5px] font-bold text-ink flex items-center gap-1.5">
            {formatInlineMarkdown(titleText)}
          </h2>
        );
      } else if (level === 2) {
        elements.push(
          <h3 key={`h2-${i}`} className="mt-3 mb-1.5 border-b border-line pb-1 text-[13px] font-bold text-ink flex items-center gap-1.5">
            {formatInlineMarkdown(titleText)}
          </h3>
        );
      } else if (level === 3) {
        elements.push(
          <h4 key={`h3-${i}`} className="mt-2.5 mb-1 text-[12px] font-semibold text-acid flex items-center gap-1.5">
            <span className="dot bg-acid shrink-0" />
            <span>{formatInlineMarkdown(titleText)}</span>
          </h4>
        );
      } else {
        elements.push(
          <h5 key={`h4-${i}`} className="mt-2 mb-0.5 text-[11.5px] font-semibold text-ink/90">
            {formatInlineMarkdown(titleText)}
          </h5>
        );
      }
      i++;
      continue;
    }

    // Списки с отступами: - [x], - [ ], - item, * item, + item
    const indentMatch = /^(\s*)/.exec(raw);
    const indentLevel = indentMatch ? Math.min(4, Math.floor(indentMatch[1].length / 2)) : 0;
    const paddingLeftClass = indentLevel === 0 ? "ml-3.5" : indentLevel === 1 ? "ml-7" : indentLevel === 2 ? "ml-10" : "ml-12";

    // Чекбоксы: - [x] или - [ ]
    const taskMatch = /^[*\-+]\s+\[([ xX])\]\s+(.*)$/.exec(trimmed);
    if (taskMatch) {
      const isChecked = taskMatch[1].toLowerCase() === "x";
      const itemText = taskMatch[2];
      elements.push(
        <div key={`task-${i}`} className={`${paddingLeftClass} my-0.5 flex items-start gap-1.5 text-[12px] leading-relaxed text-dim break-words`}>
          <span className="mt-0.5 shrink-0 text-acid">
            {isChecked ? <CheckSquare size={13} className="text-acid" /> : <Square size={13} className="text-faint" />}
          </span>
          <span className={`${isChecked ? "text-ink/80" : "text-dim"} break-words`}>
            {formatInlineMarkdown(itemText)}
          </span>
        </div>
      );
      i++;
      continue;
    }

    // Маркированные списки
    if (trimmed.startsWith("- ") || trimmed.startsWith("* ") || trimmed.startsWith("+ ")) {
      const itemText = trimmed.slice(2);
      elements.push(
        <li key={`li-${i}`} className={`${paddingLeftClass} list-disc text-[12px] leading-relaxed text-dim pl-1 break-words`}>
          {formatInlineMarkdown(itemText)}
        </li>
      );
      i++;
      continue;
    }

    // Нумерованные списки
    const numMatch = /^(\d+\.)\s+(.*)/.exec(trimmed);
    if (numMatch) {
      elements.push(
        <li key={`oli-${i}`} className={`${paddingLeftClass} list-decimal text-[12px] leading-relaxed text-dim pl-1 break-words`}>
          {formatInlineMarkdown(numMatch[2])}
        </li>
      );
      i++;
      continue;
    }

    // Цитаты (> quote)
    if (trimmed.startsWith("> ")) {
      elements.push(
        <blockquote key={`quote-${i}`} className="my-1.5 border-l-2 border-acid/60 bg-acid/5 pl-3 py-1 text-[11.5px] italic text-dim rounded-r break-words">
          {formatInlineMarkdown(trimmed.slice(2))}
        </blockquote>
      );
      i++;
      continue;
    }

    // Обычный параграф
    elements.push(
      <p key={`p-${i}`} className="text-[12px] leading-relaxed text-dim break-words">
        {formatInlineMarkdown(trimmed)}
      </p>
    );
    i++;
  }

  return (
    <div className="changelog-markdown space-y-0.5 text-left font-sans select-text break-words">
      {hasUpdate && latestVersion && (
        <div className="mb-2.5 flex items-center justify-between rounded-lg bg-acid/10 px-2.5 py-1.5 border border-acid/30 text-[11.5px] text-acid font-medium">
          <span className="flex items-center gap-1.5">
            <Check size={12} className="text-acid" />
            <span>Новая версия доступна: <strong>{latestVersion}</strong></span>
          </span>
          <span className="mono rounded bg-acid/20 px-1.5 py-0.2 text-[10px] uppercase tracking-wider font-semibold">
            Release
          </span>
        </div>
      )}
      {elements}
    </div>
  );
});
