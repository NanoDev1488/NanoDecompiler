import { memo, useMemo, type ReactNode } from "react";
import {
  CHAIN_CALL_AFTER_PLUS_RE,
  COLOR_CONCAT_RE,
  COLOR_METHOD_CALL_RE,
  GENERIC_COLOR_CALL_RE,
  MC_CODE_RE,
  renderMcColored,
  renderNamedColorText,
  renderUnknownColorMarked,
} from "./mcColors";

/* Однопроходный токенизатор: комментарии и строки не пересекаются,
   всё остальное — слова. Без полноценного парсера, для просмотра хватает. */

const KEYWORDS = new Set([
  "package", "import", "public", "private", "protected", "final", "static",
  "void", "class", "interface", "enum", "extends", "implements", "new",
  "return", "if", "else", "for", "while", "do", "switch", "case", "break",
  "continue", "try", "catch", "finally", "throw", "throws", "this", "super",
  "null", "true", "false", "boolean", "int", "long", "double", "float",
  "char", "byte", "short", "var", "instanceof", "synchronized", "volatile",
  "abstract", "default", "record", "sealed", "permits", "yield",
]);

const TOKEN_RE = new RegExp(
  [
    String.raw`(\/\*[\s\S]*?\*\/)`, // 1 блочный комментарий
    String.raw`(\/\/[^\n]*)`, // 2 строчный комментарий
    String.raw`("(?:[^"\\\n]|\\.)*")`, // 3 строка
    String.raw`('(?:[^'\\\n]|\\.)*')`, // 4 char-литерал
    String.raw`(@[A-Za-z_][\w$]*)`, // 5 аннотация
    String.raw`\b(\d[\d_]*(?:\.\d+)?[fFdDlL]?)\b`, // 6 число
    String.raw`\b([A-Za-z_$][\w$]*)\b`, // 7 слово
  ].join("|"),
  "g",
);

interface Token {
  text: string;
  cls: string | null;
  // НОВОЕ v1.8.0 (по прямой просьбе - цвета Minecraft в коде "везде во
  // всех файлах при вызове"): отдельно помечаем ИМЕННО строковый литерал
  // (не char/число - все три раньше делили один cls="tok-s") - цвета
  // майнкрафта имеет смысл разбирать только внутри настоящих строк.
  isStr?: boolean;
  // НОВОЕ v1.7.5: если это строковый аргумент вызова вида `.GREEN("...")`
  // или кастомного `.Color("...")` - см. tokenizeLine() ниже.
  colorMethod?: string | null;
  isGenericColorCall?: boolean;
  // НОВОЕ v1.8.0 (HANDOFF_URGENT п.8): строковый аргумент вызова
  // вида `Foo.bar(` сразу ПОСЛЕ конкатенации с частью строки, где уже был
  // цветовой сигнал на этой же строке (см. sawColorSignal в tokenizeLine).
  isChainedColorCall?: boolean;
}

function tokenizeLine(line: string): Token[] {
  const out: Token[] = [];
  let last = 0;
  TOKEN_RE.lastIndex = 0;
  let m: RegExpExecArray | null;
  // НОВОЕ v1.8.0: раз на этой строке уже был раскрашен §/&-код
  // (сырой, именованный метод или дженерик-Color-вызов) - следующий
  // `+ Вызов("...")` на ТОЙ ЖЕ строке с высокой вероятностью тоже часть
  // того же цветного сообщения (см. CHAIN_CALL_AFTER_PLUS_RE в mcColors.ts).
  let sawColorSignal = false;
  while ((m = TOKEN_RE.exec(line)) !== null) {
    if (m.index > last) out.push({ text: line.slice(last, m.index), cls: null });
    const [, block, line2, str, chr, ann, num, word] = m;
    let cls: string | null = null;
    if (block || line2) cls = "tok-c";
    else if (str || chr) cls = "tok-s";
    else if (ann) cls = "tok-a";
    else if (num) cls = "tok-s";
    else if (word && KEYWORDS.has(word)) cls = "tok-k";
    let colorMethod: string | null = null;
    let isGenericColorCall = false;
    let isChainedColorCall = false;
    if (str) {
      // НОВОЕ v1.7.5 (по прямой просьбе - "детект вызовов цветовых
      // методов, не только сырых &-кодов"): смотрим на текст ПЕРЕД этой
      // строкой на ТОЙ ЖЕ строке кода - `line.slice(0, m.index)` - ищем
      // паттерн вида `.GREEN(` прямо перед открывающей кавычкой аргумента.
      const before = line.slice(0, m.index);
      const namedMatch = COLOR_METHOD_CALL_RE.exec(before) ?? COLOR_CONCAT_RE.exec(before);
      if (namedMatch) {
        colorMethod = namedMatch[1];
      } else if (GENERIC_COLOR_CALL_RE.test(before)) {
        isGenericColorCall = true;
      } else if (sawColorSignal && CHAIN_CALL_AFTER_PLUS_RE.test(before)) {
        isChainedColorCall = true;
      }
      if (colorMethod || isGenericColorCall || isChainedColorCall || MC_CODE_RE.test(str)) {
        sawColorSignal = true;
      }
    }
    out.push({ text: m[0], cls, isStr: !!str, colorMethod, isGenericColorCall, isChainedColorCall });
    last = m.index + m[0].length;
  }
  if (last < line.length) out.push({ text: line.slice(last), cls: null });
  return out;
}

function renderLine(line: string, key: number): ReactNode {
  const tokens = tokenizeLine(line);
  return (
    <span key={key}>
      {tokens.map((t, i) =>
        t.isStr && t.colorMethod ? (
          <span key={i} className="tok-s">
            {renderNamedColorText(t.text, t.colorMethod)}
          </span>
        ) : t.isStr && t.isGenericColorCall ? (
          <span key={i} className="tok-s">
            {renderUnknownColorMarked(t.text)}
          </span>
        ) : t.isStr && t.isChainedColorCall ? (
          <span key={i} className="tok-s">
            {renderUnknownColorMarked(t.text)}
          </span>
        ) : t.isStr && MC_CODE_RE.test(t.text) ? (
          <span key={i} className="tok-s">
            {renderMcColored(t.text)}
          </span>
        ) : t.cls ? (
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

/** Блочные комментарии могут занимать несколько строк — обрабатываем их
    до разбивки на строки, помечая диапазоны. */
function highlight(code: string): ReactNode[][] {
  const blockRe = /\/\*[\s\S]*?\*\//g;
  const lines: ReactNode[][] = [];

  let cursor = 0;
  let m: RegExpExecArray | null;
  const segments: Array<{ text: string; comment: boolean }> = [];
  while ((m = blockRe.exec(code)) !== null) {
    if (m.index > cursor) segments.push({ text: code.slice(cursor, m.index), comment: false });
    segments.push({ text: m[0], comment: true });
    cursor = m.index + m[0].length;
  }
  if (cursor < code.length) segments.push({ text: code.slice(cursor), comment: false });

  for (const seg of segments) {
    const parts = seg.text.split("\n");
    parts.forEach((part, i) => {
      if (i === 0 && lines.length > 0) {
        // продолжение сегмента на той же строке — добавляем к последней строке
        const row = lines[lines.length - 1];
        if (seg.comment) {
          row.push(
            <span key={row.length} className="tok-c">
              {part}
            </span>,
          );
        } else {
          row.push(renderLine(part, row.length));
        }
        return;
      }
      if (seg.comment) {
        lines.push([
          <span key={0} className="tok-c">
            {part}
          </span>,
        ]);
      } else {
        lines.push([renderLine(part, 0)]);
      }
    });
  }
  return lines;
}

export const JavaCode = memo(function JavaCode({ code, wrap }: { code: string; wrap?: boolean }) {
  const lines = useMemo(() => highlight(code), [code]);
  return (
    <>
      {lines.map((row, i) => (
        <div key={i} id={`codeline-${i + 1}`} className="flex">
          <span
            aria-hidden
            className="mono w-12 flex-none pr-4 text-right text-[11px] leading-[1.75] text-faint select-none"
          >
            {i + 1}
          </span>
          <span
            className={
              "mono text-[12.5px] leading-[1.75] text-ink/90 " +
              (wrap ? "whitespace-pre-wrap break-words" : "whitespace-pre")
            }
          >
            {row}
          </span>
        </div>
      ))}
    </>
  );
});
