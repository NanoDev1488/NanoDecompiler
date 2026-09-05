import { memo, useMemo, type ReactNode } from "react";

/* БАГ-ФИКС: раньше ЛЮБОЙ файл в мини-просмотрщике проходил через
   JavaCode (java-специфичный токенизатор) - plugin.yml/pom.xml
   подсвечивались так, будто это Java (ключевые слова public/class и
   т.п. подсвечивались бы там, где их и близко нет, а реальный
   YAML/XML-синтаксис - нет). Здесь - отдельные лёгкие токенизаторы под
   YAML/properties и XML, тот же визуальный каркас (номера строк,
   моноширинный шрифт), что и у JavaCode, для единообразия. */

interface Token {
  text: string;
  cls: string | null;
}

function renderTokens(tokens: Token[], key: number): ReactNode {
  return (
    <span key={key}>
      {tokens.map((t, i) =>
        t.cls ? (
          <span key={i} className={t.cls}>
            {t.text}
          </span>
        ) : (
          <span key={i}>{t.text}</span>
        ),
      )}
    </span>
  );
}

// YAML/properties: "# комментарий", "ключ: значение", "ключ=значение",
// строки в кавычках, числа/true/false/null как отдельные слова.
const YAML_LINE_RE = /^(\s*)((?:#|!)[^\n]*)?$/;
const YAML_KEY_RE = /^(\s*(?:-\s*)?)([A-Za-z0-9_.\-]+)(\s*:)(\s.*|)$/;
const YAML_TOKEN_RE = new RegExp(
  [String.raw`("(?:[^"\\\n]|\\.)*")`, String.raw`('(?:[^'\\\n]|\\.)*')`, String.raw`\b(true|false|null|yes|no)\b`, String.raw`\b(-?\d[\d.]*)\b`].join("|"),
  "gi",
);

function tokenizeYamlLine(line: string): Token[] {
  const commentOnly = YAML_LINE_RE.exec(line);
  if (commentOnly && commentOnly[2]) {
    const indent = commentOnly[1];
    return indent ? [{ text: indent, cls: null }, { text: commentOnly[2], cls: "tok-c" }] : [{ text: commentOnly[2], cls: "tok-c" }];
  }
  const keyMatch = YAML_KEY_RE.exec(line);
  const out: Token[] = [];
  let rest = line;
  if (keyMatch) {
    out.push({ text: keyMatch[1], cls: null }, { text: keyMatch[2], cls: "tok-a" }, { text: keyMatch[3], cls: null });
    rest = keyMatch[4];
  }
  let last = 0;
  YAML_TOKEN_RE.lastIndex = 0;
  let m: RegExpExecArray | null;
  while ((m = YAML_TOKEN_RE.exec(rest)) !== null) {
    if (m.index > last) out.push({ text: rest.slice(last, m.index), cls: null });
    out.push({ text: m[0], cls: "tok-s" });
    last = m.index + m[0].length;
  }
  if (last < rest.length) out.push({ text: rest.slice(last), cls: null });
  return out;
}

// XML: <!-- комментарий -->, <tag>, </tag>, атрибуты, строки-значения.
const XML_TOKEN_RE = new RegExp(
  [
    String.raw`(<!--[\s\S]*?-->)`,
    String.raw`(<\/?[A-Za-z][\w:.\-]*)`,
    String.raw`(\/?>)`,
    String.raw`("(?:[^"\\\n]|\\.)*")`,
    String.raw`\b([A-Za-z_][\w:.\-]*)(?=\s*=)`,
  ].join("|"),
  "g",
);

function tokenizeXmlLine(line: string): Token[] {
  const out: Token[] = [];
  let last = 0;
  XML_TOKEN_RE.lastIndex = 0;
  let m: RegExpExecArray | null;
  while ((m = XML_TOKEN_RE.exec(line)) !== null) {
    if (m.index > last) out.push({ text: line.slice(last, m.index), cls: null });
    const [, comment, tag, close, str, attr] = m;
    let cls: string | null = null;
    if (comment) cls = "tok-c";
    else if (tag || close) cls = "tok-k";
    else if (str) cls = "tok-s";
    else if (attr) cls = "tok-a";
    out.push({ text: m[0], cls });
    last = m.index + m[0].length;
  }
  if (last < line.length) out.push({ text: line.slice(last), cls: null });
  return out;
}

function renderShell(lines: ReactNode[], wrap?: boolean): ReactNode {
  return (
    <>
      {lines.map((row, i) => (
        <div key={i} className="flex">
          <span
            aria-hidden
            className="mono w-12 flex-none pr-4 text-right text-[11px] leading-[1.75] text-faint select-none"
          >
            {i + 1}
          </span>
          <span
            className={
              "mono text-[12.5px] leading-[1.75] text-ink/90 " + (wrap ? "whitespace-pre-wrap break-words" : "whitespace-pre")
            }
          >
            {row}
          </span>
        </div>
      ))}
    </>
  );
}

export const YamlCode = memo(function YamlCode({ code, wrap }: { code: string; wrap?: boolean }) {
  const lines = useMemo(() => code.split("\n").map((l, i) => renderTokens(tokenizeYamlLine(l), i)), [code]);
  return renderShell(lines, wrap);
});

export const XmlCode = memo(function XmlCode({ code, wrap }: { code: string; wrap?: boolean }) {
  const lines = useMemo(() => code.split("\n").map((l, i) => renderTokens(tokenizeXmlLine(l), i)), [code]);
  return renderShell(lines, wrap);
});

// Форматы без своего токенизатора (.json/.md/.txt/.gitignore и т.п.) -
// честно показываем как обычный текст без подсветки, а не подделываем под
// java/yaml - неверная подсветка хуже, чем её отсутствие.
export const PlainCode = memo(function PlainCode({ code, wrap }: { code: string; wrap?: boolean }) {
  const lines = useMemo(() => code.split("\n").map((l, i) => <span key={i}>{l}</span>), [code]);
  return renderShell(lines, wrap);
});
