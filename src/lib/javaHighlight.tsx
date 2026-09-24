import { memo, useMemo, useState, type ReactNode } from "react";
import {
  CHAIN_CALL_AFTER_PLUS_RE,
  COLOR_CONCAT_RE,
  COLOR_METHOD_CALL_RE,
  GENERIC_COLOR_CALL_RE,
  lastColorHexInString,
  MC_CODE_RE,
  MC_COLOR_NAME_HEX,
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
  // НОВОЕ 1.9.6 (HANDOFF п.4-5/9 - сворачивание цепочек в чип): офсеты
  // символов ВНУТРИ строки - нужны, чтобы находить границы цепочек и
  // логгер-вызовов по СИМВОЛЬНЫМ диапазонам (см. findLogRegions/
  // findColorChainRegions ниже), а не гадать по индексу токена.
  start: number;
  end: number;
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
  // НОВОЕ v1.9.5 (доработка п.8 - "унаследованный цвет вместо нейтрального
  // серого для method1()-подобных цепочек"): hex цвета, действующего на
  // момент этого токена (от ChatColor.CONST/сырых §-кодов РАНЬШЕ на этой
  // же строке) - используется ТОЛЬКО для isGenericColorCall/
  // isChainedColorCall (там, где сам формат неизвестен, но контекст ясен).
  inheritedColor?: string | null;
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
  // НОВОЕ v1.9.5 - см. inheritedColor в Token выше. Обновляется в ТРЁХ
  // местах: (1) именованный цветовой метод/enum-константа (ChatColor.RED),
  // (2) сырой §/&-код ВНУТРИ строкового литерала, (3) НЕ обновляется для
  // isGenericColorCall/isChainedColorCall - там цвет неизвестен, только
  // читаем текущий, не перезаписываем.
  let currentColorHex: string | null = null;
  while ((m = TOKEN_RE.exec(line)) !== null) {
    if (m.index > last) out.push({ text: line.slice(last, m.index), cls: null, start: last, end: m.index });
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
    let inheritedColor: string | null = null;
    // НОВОЕ v1.9.5: `ChatColor.RED`/`NamedTextColor.RED` как ГОЛАЯ
    // enum-константа (не вызов метода, не конкатенация со строкой сразу
    // после) - update currentColorHex, чтобы дальнейшие method1()-подобные
    // цепочки уже знали актуальный цвет, даже если constant используется
    // отдельно (например, присвоен переменной чуть раньше по коду).
    if (word && word === word.toUpperCase() && MC_COLOR_NAME_HEX[word.toLowerCase()]) {
      const before = line.slice(0, m.index);
      if (/\.\s*$/.test(before) && /\b(?:ChatColor|NamedTextColor|TextColor)\.\s*$/.test(before)) {
        currentColorHex = MC_COLOR_NAME_HEX[word.toLowerCase()];
        sawColorSignal = true;
      }
    }
    if (str) {
      // НОВОЕ v1.7.5 (по прямой просьбе - "детект вызовов цветовых
      // методов, не только сырых &-кодов"): смотрим на текст ПЕРЕД этой
      // строкой на ТОЙ ЖЕ строке кода - `line.slice(0, m.index)` - ищем
      // паттерн вида `.GREEN(` прямо перед открывающей кавычкой аргумента.
      const before = line.slice(0, m.index);
      const namedMatch = COLOR_METHOD_CALL_RE.exec(before) ?? COLOR_CONCAT_RE.exec(before);
      if (namedMatch) {
        colorMethod = namedMatch[1];
        currentColorHex = MC_COLOR_NAME_HEX[colorMethod.toLowerCase()] ?? currentColorHex;
      } else if (GENERIC_COLOR_CALL_RE.test(before)) {
        isGenericColorCall = true;
      } else if (sawColorSignal && CHAIN_CALL_AFTER_PLUS_RE.test(before)) {
        isChainedColorCall = true;
      }
      if (isGenericColorCall || isChainedColorCall) {
        inheritedColor = currentColorHex;
      } else {
        // Обычная строка (в т.ч. с сырыми §/&-кодами) - обновляем текущий
        // цвет по её СОБСТВЕННОМУ содержимому для последующих токенов.
        currentColorHex = lastColorHexInString(str, currentColorHex);
      }
      if (colorMethod || isGenericColorCall || isChainedColorCall || MC_CODE_RE.test(str)) {
        sawColorSignal = true;
      }
    }
    out.push({
      text: m[0],
      cls,
      start: m.index,
      end: m.index + m[0].length,
      isStr: !!str,
      colorMethod,
      isGenericColorCall,
      isChainedColorCall,
      inheritedColor,
    });
    last = m.index + m[0].length;
  }
  if (last < line.length) out.push({ text: line.slice(last), cls: null, start: last, end: line.length });
  return out;
}

// НОВОЕ 1.9.6 (HANDOFF п.9 - "сворачивать логгер-вызовы Bukkit так же, как
// цветовые цепочки"): распознаём ТОЛЬКО .warning(/.severe(/.info(/.config(/
// .fine(/.finer(/.finest( - это реальные уровни java.util.logging.Level,
// которым пользуется Bukkit Logger (plugin.getLogger()...). Чтобы не ловить
// случайный несвязанный метод с тем же именем на произвольном объекте (у
// плагина может быть свой класс со методом .info(), не имеющим отношения к
// логгеру) - требуем, чтобы ПЕРЕД точкой стояло что-то похожее на логгер:
// вызов ...getLogger() ИЛИ идентификатор, оканчивающийся на log/logger
// (любым регистром - LOGGER, logger, pluginLog и т.п.).
const LOG_METHOD_RE = /\.(warning|severe|info|config|fine|finer|finest)\s*\(/g;
const LOG_RECEIVER_RE = /(?:getLogger\(\)|\blog(?:ger)?)\s*$/i;

interface Region {
  start: number;
  end: number;
  kind: "color" | "log";
  logLevel?: string;
}

/** Ищет логгер-вызовы `logger.warning(...)` (включая plugin.getLogger().x(...))
 * на строке и возвращает символьные диапазоны ОТ имени метода ДО закрывающей
 * скобки включительно. Скобки/кавычки внутри строковых литералов-аргументов
 * не считаются структурными - простой посимвольный сканер, учитывающий
 * границы `"..."` (с учётом экранирования `\"`). */
function findLogRegions(line: string): Region[] {
  const regions: Region[] = [];
  LOG_METHOD_RE.lastIndex = 0;
  let m: RegExpExecArray | null;
  while ((m = LOG_METHOD_RE.exec(line)) !== null) {
    const dotIndex = m.index;
    const before = line.slice(0, dotIndex);
    if (!LOG_RECEIVER_RE.test(before)) continue;
    const methodStart = dotIndex + 1; // сразу после точки, с имени метода
    const openParenIndex = m.index + m[0].length - 1; // позиция самой "("
    let depth = 1;
    let i = openParenIndex + 1;
    let closeIndex = -1;
    while (i < line.length) {
      const ch = line[i];
      if (ch === '"') {
        // пропускаем строковый литерал целиком - его содержимое не влияет
        // на баланс скобок вызова.
        i++;
        while (i < line.length && line[i] !== '"') {
          if (line[i] === "\\") i++;
          i++;
        }
        i++;
        continue;
      }
      if (ch === "(") depth++;
      else if (ch === ")") {
        depth--;
        if (depth === 0) {
          closeIndex = i;
          break;
        }
      }
      i++;
    }
    if (closeIndex === -1) continue; // не нашли пару на этой строке - не трогаем
    regions.push({ start: methodStart, end: closeIndex + 1, kind: "log", logLevel: m[1] });
  }
  return regions;
}

/** Ищет цепочки конкатенации цветного текста (2+ цветовых строк подряд,
 * например `"§a" + method1("текст") + "§c!"`) и возвращает их символьные
 * диапазоны. Токены, уже накрытые логгер-регионом (excludeRanges), в расчёт
 * не берутся - логгер-вызов сворачивается ЦЕЛИКОМ отдельным регионом, а не
 * пересекается с цветовым. */
function findColorChainRegions(tokens: Token[], excludeRanges: Region[]): Region[] {
  const isExcluded = (t: Token) => excludeRanges.some(r => t.start >= r.start && t.start < r.end);
  const isColorStr = (t: Token) =>
    !!t.isStr && !!(t.colorMethod || t.isGenericColorCall || t.isChainedColorCall || MC_CODE_RE.test(t.text));
  const isBreaker = (t: Token) => (t.isStr && !isColorStr(t)) || t.text.includes(";") || t.cls === "tok-k";

  const regions: Region[] = [];
  let currentStart: number | null = null;
  let prevEnd = 0;
  let colorCount = 0;
  for (const t of tokens) {
    if (isExcluded(t)) continue;
    if (isBreaker(t)) {
      if (currentStart !== null && colorCount >= 2) regions.push({ start: currentStart, end: prevEnd, kind: "color" });
      currentStart = null;
      colorCount = 0;
      continue;
    }
    if (currentStart === null) {
      if (isColorStr(t)) {
        currentStart = t.start;
        prevEnd = t.end;
        colorCount = 1;
      }
      continue;
    }
    prevEnd = t.end;
    if (isColorStr(t)) colorCount++;
  }
  if (currentStart !== null && colorCount >= 2) regions.push({ start: currentStart, end: prevEnd, kind: "color" });
  return regions;
}

/** Убирает окружающие кавычки строкового литерала для показа "как будет
 * выглядеть в игре" внутри свёрнутого чипа - сырые кавычки там не нужны. */
function stripQuotes(text: string): string {
  if (text.length >= 2 && text[0] === '"' && text[text.length - 1] === '"') return text.slice(1, -1);
  return text;
}

/** Рендер ОДНОГО токена - тот же выбор функции раскраски, что и раньше,
 * вынесенный в отдельную функцию: переиспользуется и для обычных строк
 * ВНЕ регионов, и для содержимого развёрнутого/свёрнутого чипа. */
function renderToken(t: Token, i: number | string, textOverride?: string): ReactNode {
  const text = textOverride ?? t.text;
  if (t.isStr && t.colorMethod) {
    return (
      <span key={i} className="tok-s">
        {renderNamedColorText(text, t.colorMethod)}
      </span>
    );
  }
  if (t.isStr && (t.isGenericColorCall || t.isChainedColorCall)) {
    return (
      <span key={i} className="tok-s">
        {renderUnknownColorMarked(text, t.inheritedColor)}
      </span>
    );
  }
  if (t.isStr && MC_CODE_RE.test(text)) {
    return (
      <span key={i} className="tok-s">
        {renderMcColored(text)}
      </span>
    );
  }
  if (t.cls) {
    return (
      <span key={i} className={t.cls}>
        {text}
      </span>
    );
  }
  return <span key={i}>{text}</span>;
}

const LOG_LEVEL_SEVERITY = new Set(["severe", "warning"]);

/** Содержимое СВЁРНУТОГО чипа - только "результат" (цветной текст без
 * кавычек/скобок/имени метода). Для color-региона - просто раскрашенные
 * строковые куски. Для log-региона - тег уровня + то же самое. */
function renderChipPreview(tokens: Token[], region: Region): ReactNode {
  const inRange = tokens.filter(t => t.start >= region.start && t.end <= region.end && t.isStr);
  const parts = inRange.map((t, i) => renderToken(t, i, stripQuotes(t.text)));
  if (region.kind === "log") {
    return (
      <>
        <span className={"chain-chip-tag" + (LOG_LEVEL_SEVERITY.has(region.logLevel ?? "") ? " tag-warn" : "")}>
          {region.logLevel}
        </span>
        {parts}
      </>
    );
  }
  return <>{parts}</>;
}

function ChainChip({
  tokens,
  region,
  chipKey,
  expanded,
  onToggle,
}: {
  tokens: Token[];
  region: Region;
  chipKey: string;
  expanded: boolean;
  onToggle: (key: string) => void;
}) {
  if (expanded) {
    const inRange = tokens.filter(t => t.start >= region.start && t.end <= region.end);
    return (
      <span className="chain-chip-expanded" title="Свернуть обратно" onClick={() => onToggle(chipKey)}>
        {inRange.map((t, i) => renderToken(t, i))}
      </span>
    );
  }
  return (
    <span
      className="chain-chip"
      title={
        region.kind === "log"
          ? "Свёрнутый вызов логгера - клик, чтобы посмотреть исходный код"
          : "Свёрнутая цветовая цепочка - клик, чтобы посмотреть исходный код"
      }
      onClick={() => onToggle(chipKey)}
    >
      {renderChipPreview(tokens, region)}
    </span>
  );
}

/** Строит итоговый JSX для одной строки кода из уже готовых токенов -
 * НЕ токенизирует заново (это делает tokenizeCode() один раз через
 * useMemo), только собирает регионы (зависят лишь от токенов, дёшево) и
 * расставляет обычные токены / чипы, учитывая текущее expanded-состояние. */
function renderLineTokens(
  tokens: Token[],
  lineKey: string,
  expanded: Set<string>,
  onToggle: (key: string) => void,
): ReactNode[] {
  const logRegions = findLogRegions(tokens.map(t => t.text).join(""));
  const colorRegions = findColorChainRegions(tokens, logRegions);
  const regions = [...logRegions, ...colorRegions].sort((a, b) => a.start - b.start);

  const out: ReactNode[] = [];
  let i = 0;
  let regionIdx = 0;
  while (i < tokens.length) {
    const t = tokens[i];
    const region = regions.find(r => t.start >= r.start && t.start < r.end);
    if (region) {
      const chipKey = `${lineKey}:${regionIdx++}`;
      out.push(
        <ChainChip
          key={chipKey}
          tokens={tokens}
          region={region}
          chipKey={chipKey}
          expanded={expanded.has(chipKey)}
          onToggle={onToggle}
        />,
      );
      // пропускаем все токены, попавшие в этот регион. БАГ-ФИКС (найден
      // реальным тестом, не на глаз): "сырые" токены-связки (пунктуация
      // между распознанными словами/строками) могут захватывать несколько
      // символов сразу, например `));` одним куском - если такой токен
      // НАЧИНАЕТСЯ внутри региона, но ЗАКАНЧИВАЕТСЯ уже ПОСЛЕ его границы
      // (region.end), обычный пропуск токена целиком молча стирал бы
      // символы ПОСЛЕ границы (например, точку с запятой сразу после
      // закрывающей скобки лог-вызова) - расщепляем такой токен и
      // дорисовываем "хвост" уже как обычный текст.
      while (i < tokens.length && tokens[i].start < region.end) {
        const overlapping = tokens[i];
        if (overlapping.end > region.end) {
          const tailText = overlapping.text.slice(region.end - overlapping.start);
          out.push(
            renderToken({ ...overlapping, text: tailText, start: region.end, end: overlapping.end }, `${i}-tail`),
          );
        }
        i++;
      }
      continue;
    }
    out.push(renderToken(t, i));
    i++;
  }
  return out;
}

type LineSeg = { comment: true; text: string } | { comment: false; tokens: Token[] };

/** Блочные комментарии могут занимать несколько строк — обрабатываем их
    до разбивки на строки, помечая диапазоны. */
function tokenizeCode(code: string): LineSeg[][] {
  const blockRe = /\/\*[\s\S]*?\*\//g;
  const lines: LineSeg[][] = [];

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
        row.push(seg.comment ? { comment: true, text: part } : { comment: false, tokens: tokenizeLine(part) });
        return;
      }
      lines.push([seg.comment ? { comment: true, text: part } : { comment: false, tokens: tokenizeLine(part) }]);
    });
  }
  return lines;
}

export const JavaCode = memo(function JavaCode({ code, wrap }: { code: string; wrap?: boolean }) {
  const lines = useMemo(() => tokenizeCode(code), [code]);
  // НОВОЕ 1.9.6: ключ чипа = "номер строки:номер сегмента:номер региона на
  // строке" - стабилен, пока не меняется сам код (что и так пересоздаёт
  // lines/expanded целиком через смену code). Per-line, а не файл целиком,
  // как и предполагал HANDOFF - разворачивать одну цепочку не трогает
  // состояние остальных.
  const [expanded, setExpanded] = useState<Set<string>>(new Set());
  const onToggle = (key: string) =>
    setExpanded(prev => {
      const next = new Set(prev);
      if (next.has(key)) next.delete(key);
      else next.add(key);
      return next;
    });

  return (
    <>
      {lines.map((row, li) => (
        <div key={li} id={`codeline-${li + 1}`} className="flex">
          <span
            aria-hidden
            className="mono w-12 flex-none pr-4 text-right text-[11px] leading-[1.75] text-faint select-none"
          >
            {li + 1}
          </span>
          <span
            className={
              "mono text-[12.5px] leading-[1.75] text-ink/90 " +
              (wrap ? "whitespace-pre-wrap break-words" : "whitespace-pre")
            }
          >
            {row.map((seg, si) =>
              seg.comment ? (
                <span key={si} className="tok-c">
                  {seg.text}
                </span>
              ) : (
                <span key={si}>{renderLineTokens(seg.tokens, `${li}:${si}`, expanded, onToggle)}</span>
              ),
            )}
          </span>
        </div>
      ))}
    </>
  );
});
