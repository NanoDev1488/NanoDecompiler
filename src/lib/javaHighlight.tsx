import { memo, useMemo, useState, type ReactNode } from "react";
import {
  applyFancyFont,
  CHAIN_CALL_AFTER_PLUS_RE,
  COLOR_CONCAT_RE,
  COLOR_METHOD_CALL_RE,
  GENERIC_COLOR_CALL_RE,
  isFancyFontMethod,
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
  // НОВОЕ v1.9.14: помечаем enum-константы цветов (ChatColor.RED и т.п.)
  // чтобы сворачивать их ВМЕСТЕ со следующей цветной строкой в один чип
  isColorEnum?: boolean;
  colorEnumStart?: number;
  // НОВОЕ: кастомный шрифт FancyFont / method1
  isFancyFontCall?: boolean;
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
    let isColorEnum = false;
    let colorEnumStart: number | undefined;
    // НОВОЕ v1.9.5 + v1.9.14: `ChatColor.RED`/`NamedTextColor.RED` как ГОЛАЯ
    // enum-константа (не вызов метода, не конкатенация со строкой сразу
    // после) - update currentColorHex, плюс запоминаем начало префикса для чипов.
    if (word && word === word.toUpperCase() && MC_COLOR_NAME_HEX[word.toLowerCase()]) {
      const before = line.slice(0, m.index);
      const prefixMatch = /\b(?:ChatColor|NamedTextColor|TextColor)\.\s*$/.exec(before);
      if (prefixMatch) {
        currentColorHex = MC_COLOR_NAME_HEX[word.toLowerCase()];
        sawColorSignal = true;
        isColorEnum = true;
        colorEnumStart = m.index - prefixMatch[0].length;
      }
    }
    let isFancyFontCall = false;
    if (str) {
      // НОВОЕ v1.7.5: смотрим на текст ПЕРЕД строкой на той же строке кода
      const before = line.slice(0, m.index);
      if (/\b(?:method1|stylize|fancyfont(?:\.stylize)?)\s*\(\s*$/i.test(before)) {
        isFancyFontCall = true;
      }
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
      isColorEnum,
      colorEnumStart,
      isFancyFontCall,
    });
    last = m.index + m[0].length;
  }
  if (last < line.length) out.push({ text: line.slice(last), cls: null, start: last, end: line.length });
  return out;
}

// НОВОЕ 1.9.6 + v1.9.17: распознаём уровни java.util.logging.Level, Bukkit Logger,
// SLF4J, Log4j, System.out/err и sendMessage/sendActionBar/sendTitle.
const MESSAGE_METHODS = new Set([
  "sendMessage",
  "sendActionBar",
  "sendTitle",
  "sendRawMessage",
  "broadcastMessage",
  "broadcast",
]);

const LOG_METHOD_RE = /(?:(\.)\s*)?\b(warning|severe|error|info|config|fine|finer|finest|debug|log|println|print|sendMessage|sendActionBar|sendTitle|sendRawMessage|broadcastMessage|broadcast)\s*\(/g;
const LOG_RECEIVER_RE = /(?:getLogger\(\)|\blog(?:ger)?|Bukkit\.getLogger\(\)|System\.(?:out|err)|\bsender|\bplayer|\btarget|\bp|\buser|\bcommandSender|\bcs|\bs|\bctx|\baudience|\brecipient)\s*$/i;

interface Region {
  start: number;
  end: number;
  kind: "color" | "log";
  logLevel?: string;
  openParenIndex?: number;
}


/** Ищет логгер-вызовы `logger.warning(...)`, `logger.log(Level.XXX, ...)`,
 * и Minecraft-сообщения `sender.sendMessage(...)` на строке и возвращает
 * символьные диапазоны от начала вызова (включая ресивер) до закрывающей
 * скобки включительно. */
function findLogRegions(line: string): Region[] {
  const regions: Region[] = [];
  LOG_METHOD_RE.lastIndex = 0;
  let m: RegExpExecArray | null;
  while ((m = LOG_METHOD_RE.exec(line)) !== null) {
    const hasDot = !!m[1];
    const methodName = m[2];
    const isMsg = MESSAGE_METHODS.has(methodName);
    const methodCallIndex = m.index + (hasDot ? m[0].indexOf(methodName) : 0);
    const before = line.slice(0, methodCallIndex - (hasDot ? 1 : 0));

    if (hasDot) {
      if (!isMsg && !LOG_RECEIVER_RE.test(before) && !/(?:log|logger)/i.test(before)) {
        continue;
      }
    } else {
      // Bare function call: разрешаем log(Level.XXX, ...) или прямое sendMessage
      if (methodName !== "log" && !isMsg) {
        continue;
      }
    }

    const openParenIndex = m.index + m[0].length - 1;
    let depth = 1;
    let i = openParenIndex + 1;
    let closeIndex = -1;
    while (i < line.length) {
      const ch = line[i];
      if (ch === '"') {
        i++;
        while (i < line.length && line[i] !== '"') {
          if (line[i] === "\\") i++;
          i++;
        }
        i++;
        continue;
      }
      if (ch === "'") {
        i++;
        while (i < line.length && line[i] !== "'") {
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
    if (closeIndex === -1) continue;

    // Распознаём уровень в logger.log(Level.XXX, ...)
    let logLevel = methodName;
    if (methodName === "log") {
      const insideCall = line.slice(openParenIndex + 1, closeIndex);
      const levelMatch = /^\s*(?:(?:[a-zA-Z0-9_.]+\.)?Level\.)?([A-Z_]+)\s*,/i.exec(insideCall);
      if (levelMatch) {
        const lvl = levelMatch[1].toUpperCase();
        if (lvl === "SEVERE" || lvl === "ERROR") logLevel = "severe";
        else if (lvl === "WARNING" || lvl === "WARN") logLevel = "warning";
        else if (lvl === "INFO") logLevel = "info";
        else if (lvl === "FINE" || lvl === "FINER" || lvl === "FINEST" || lvl === "DEBUG") logLevel = "debug";
        else if (lvl === "CONFIG") logLevel = "config";
      }
    }

    // Включаем ресивер (sender, logger и т.д.) в диапазон для чистого скрытия
    let start = methodCallIndex;
    if (hasDot) {
      const dotIndex = methodCallIndex - 1;
      const receiverMatch = /(?:[a-zA-Z_$][\w$]*(?:\(\))?\.)*[a-zA-Z_$][\w$]*(?:\(\))?$/.exec(line.slice(0, dotIndex));
      if (receiverMatch) {
        start = dotIndex - receiverMatch[0].length;
      }
    }

    regions.push({ start, end: closeIndex + 1, kind: "log", logLevel, openParenIndex });
  }
  return regions;

}

/** Ищет цепочки конкатенации цветного текста (2+ цветовых элементов подряд,
 * например `ChatColor.RED + "текст"` или `"§a" + method1("текст") + "§c!"`)
 * и возвращает их символьные диапазоны. */
function findColorChainRegions(tokens: Token[], excludeRanges: Region[]): Region[] {
  const isExcluded = (t: Token) => excludeRanges.some(r => t.start >= r.start && t.start < r.end);
  const isColorStr = (t: Token) =>
    !!t.isStr && !!(t.colorMethod || t.isGenericColorCall || t.isChainedColorCall || MC_CODE_RE.test(t.text));
  const isColorToken = (t: Token) => isColorStr(t) || !!t.isColorEnum;
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
      if (t.isColorEnum) {
        currentStart = t.colorEnumStart ?? t.start;
        prevEnd = t.end;
        colorCount = 1;
      } else if (isColorStr(t)) {
        currentStart = t.start;
        prevEnd = t.end;
        colorCount = 1;
      }
      continue;
    }
    prevEnd = t.end;
    if (isColorToken(t)) colorCount++;
  }
  if (currentStart !== null && colorCount >= 2) regions.push({ start: currentStart, end: prevEnd, kind: "color" });
  return regions;
}

/** Токены, попадающие в диапазон [start,end), с корректной обрезкой
 * ЧАСТИЧНО перекрывающего токена на границе end - та же проблема, что
 * чинилась в renderLineTokens (см. комментарий там про `));`): простой
 * `t.end <= end` фильтр молча ВЫБРАСЫВАЕТ токен целиком, если его конец
 * чуть-чуть вылезает за границу региона (например "))" закрытие
 * getVersion() слито с "))" закрытием внешнего info(...) в один сырой
 * токен, а дальше ещё и ";") - из-за чего видимый текст терял последние
 * символы. Обрезаем текст токена по границе вместо того, чтобы дропать
 * его целиком. */
function tokensInRange(tokens: Token[], start: number, end: number): Token[] {
  const out: Token[] = [];
  for (const t of tokens) {
    if (t.end <= start || t.start >= end) continue;
    if (t.start >= start && t.end <= end) {
      out.push(t);
    } else {
      const sliceStart = Math.max(start, t.start) - t.start;
      const sliceEnd = Math.min(end, t.end) - t.start;
      out.push({ ...t, text: t.text.slice(sliceStart, sliceEnd), start: Math.max(start, t.start), end: Math.min(end, t.end) });
    }
  }
  return out;
}

/** Убирает окружающие кавычки строкового литерала для показа "как будет
 * выглядеть в игре" внутри свёрнутого чипа - сырые кавычки там не нужны,
 * а переносы строк заменяются на символ ↵ если чип однострочный. */
function stripQuotes(text: string): string {
  let s = text;
  if (s.length >= 2 && s[0] === '"' && s[s.length - 1] === '"') s = s.slice(1, -1);
  return s.replace(/\r?\n|\\n/g, " ↵ ");
}

/** Рендерит строковое содержимое с полноценной поддержкой многострочных \n / \\n
 * переносов строк (например, ASCII-баннеры и стартовые сообщения плагинов):
 * визуализирует каждую строку аккуратно друг под другом с сохранением цвета Minecraft. */
function renderStringContent(
  rawText: string,
  activeColorHex: string | null,
  titleNote?: string,
  transform?: (s: string) => string,
): ReactNode {
  let s = rawText;
  if (s.length >= 2 && s[0] === '"' && s[s.length - 1] === '"') s = s.slice(1, -1);
  const unescaped = s.replace(/\\n/g, "\n").replace(/\\t/g, "    ").replace(/\\r/g, "");
  const lines = unescaped.split("\n");

  if (lines.length === 1) {
    const val = transform ? transform(lines[0]) : lines[0];
    return (
      <span style={activeColorHex ? { color: activeColorHex } : undefined} title={titleNote}>
        {val}
      </span>
    );
  }

  return (
    <span
      className="inline-flex flex-col items-start align-top font-mono"
      style={activeColorHex ? { color: activeColorHex } : undefined}
      title={titleNote}
    >
      {lines.map((ln, idx) => {
        const val = transform ? transform(ln) : ln;
        return (
          <span key={idx} className="whitespace-pre min-h-[1.25em]">
            {val || " "}
          </span>
        );
      })}
    </span>
  );
}

/** В сыром режиме отображения (без сворачивания в чипы) многострочные строки
 * с литеральным \n отображаются с визуальным переносом строки на следующую строку. */
function renderRawStringWithBreaks(text: string): ReactNode {
  if (!text.includes("\\n")) return text;
  const parts = text.split(/\\n/g);
  return (
    <>
      {parts.map((p, idx) => (
        <span key={idx}>
          {p}
          {idx < parts.length - 1 && (
            <>
              <span className="text-acid/80 font-bold select-none" title="Перенос строки (\n)">\n</span>
              <br />
            </>
          )}
        </span>
      ))}
    </>
  );
}

/** Рендер ОДНОГО токена - тот же выбор функции раскраски, что и раньше,
 * вынесенный в отдельную функцию: переиспользуется и для обычных строк
 * ВНЕ регионов, и для содержимого развёрнутого/свёрнутого чипа. */
function renderToken(t: Token, i: number | string, textOverride?: string, raw?: boolean): ReactNode {
  const text = textOverride ?? t.text;
  if (!raw) {
    if (t.isStr && t.isFancyFontCall) {
      const rawStr = stripQuotes(text);
      const stylized = applyFancyFont(rawStr);
      return (
        <span
          key={i}
          className="tok-s cursor-help font-normal"
          title={`Кастомный шрифт (method1 / FancyFont): исходный текст "${rawStr}"`}
          style={t.inheritedColor ? { color: t.inheritedColor } : undefined}
        >
          "{stylized}"
        </span>
      );
    }
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
  }
  if (t.cls) {
    return (
      <span key={i} className={t.cls}>
        {(!raw && t.isStr) ? renderRawStringWithBreaks(text) : text}
      </span>
    );
  }
  return <span key={i}>{text}</span>;
}

/** Интерактивный чип серверного runtime-выражения: предотвращает случайное сворачивание
 * родительского блока, копирует выражение при клике и отображает понятное объяснение. */
const RuntimeValueChip = memo(function RuntimeValueChip({ exprStr }: { exprStr: string }) {
  const [copied, setCopied] = useState(false);
  return (
    <span
      role="button"
      tabIndex={0}
      className="mono rounded bg-acid/20 px-1 py-0.2 text-[11px] text-acid font-semibold cursor-pointer hover:bg-acid/35 hover:scale-105 active:scale-95 transition-all mx-0.5 select-none inline-flex items-center gap-1 border border-acid/30"
      title={`Динамическое выражение сервера:\n${exprStr}\n(Клик: скопировать выражение и показать подробности)`}
      onClick={(e) => {
        e.stopPropagation();
        try {
          navigator.clipboard?.writeText(exprStr);
        } catch {}
        setCopied(true);
        setTimeout(() => setCopied(false), 2000);
      }}
    >
      <span>&#123;&#125;</span>
      {copied && (
        <span className="text-[9px] text-acid font-bold animate-pulse">
          ✓ {exprStr.length > 25 ? exprStr.slice(0, 22) + "..." : exprStr}
        </span>
      )}
    </span>
  );
});

const LOG_LEVEL_SEVERITY = new Set(["severe", "warning", "error"]);

/** Содержимое СВЁРНУТОГО чипа. Для color-региона - раскрашенные строковые
 * куски. Для log-региона - скрывает внешние скобки вызова и ресивер,
 * разворачивает обфусцированные дешифраторы (method1("...")), определяет
 * уровень (Level.WARNING -> WARN), удаляет Level.XXX из тела и красиво
 * оборачивает переменные в {} и исключения в ⚡. */
function renderChipPreview(tokens: Token[], region: Region): ReactNode {
  if (region.kind === "log") {
    const inRange = tokensInRange(tokens, region.start, region.end);
    const parts: ReactNode[] = [];
    let activeColorHex: string | null = null;
    let i = 0;

    // 1. Пропускаем имя метода/вызов и открывающую скобку '('
    // БАГ-ФИКС v1.9.153: если у ресивера есть свои скобки (например, Bukkit.getConsoleSender().sendMessage(...)),
    // то простой поиск '(' натыкался на getConsoleSender() и ломал разбор. Используем точный openParenIndex.
    if (region.openParenIndex !== undefined) {
      while (i < inRange.length && inRange[i].end <= region.openParenIndex) {
        i++;
      }
      if (i < inRange.length && inRange[i].start <= region.openParenIndex) {
        const sliceOffset = region.openParenIndex + 1 - inRange[i].start;
        const remainder = inRange[i].text.slice(sliceOffset);
        if (remainder.trim()) {
          inRange[i] = { ...inRange[i], text: remainder, start: region.openParenIndex + 1 };
        } else {
          i++;
        }
      }
    } else {
      while (i < inRange.length) {
        const t = inRange[i];
        if (t.text.includes("(")) {
          const parenIdx = t.text.indexOf("(");
          const remainder = t.text.slice(parenIdx + 1);
          if (remainder.trim()) {
            inRange[i] = { ...t, text: remainder };
          } else {
            i++;
          }
          break;
        }
        i++;
      }
    }

    // 2. Отсекаем ровно ОДНУ закрывающую скобку самого внешнего вызова логгера/сообщения.
    // БАГ-ФИКС v1.9.153: нельзя использовать replace(/\)+$/, "") - это удаляло закрывающие скобки
    // аргументов вроде getDescription().getVersion() и оставляло висящие '());' снаружи.
    let limit = inRange.length;
    let trimmedOuterParen = false;
    while (limit > i && !trimmedOuterParen) {
      const lastTok = inRange[limit - 1];
      const trimText = lastTok.text.trim();
      if (trimText === "" || trimText === ";") {
        limit--;
        continue;
      }
      const lastParenIdx = lastTok.text.lastIndexOf(")");
      if (lastParenIdx !== -1) {
        const beforeParen = lastTok.text.slice(0, lastParenIdx);
        const afterParen = lastTok.text.slice(lastParenIdx + 1);
        const newText = beforeParen + afterParen;
        if (newText.trim() === "") {
          limit--;
        } else {
          inRange[limit - 1] = { ...lastTok, text: newText };
        }
        trimmedOuterParen = true;
        break;
      }
      limit--;
    }

    // 3. Если был вызов logger.log(Level.XXX, ...), пропускаем аргумент Level.XXX,
    // чтобы он не превращался в ненужный {} в VS Code стиле
    while (i < limit) {
      const combined = inRange.slice(i, Math.min(i + 15, limit)).map(t => t.text).join("");
      const levelPrefixMatch = /^\s*(?:(?:[a-zA-Z0-9_.]+\.)?Level\.)?[A-Z_]+\s*,\s*/i.exec(combined);
      if (levelPrefixMatch) {
        let matchedLen = levelPrefixMatch[0].length;
        while (i < limit && matchedLen > 0) {
          if (inRange[i].text.length <= matchedLen) {
            matchedLen -= inRange[i].text.length;
            i++;
          } else {
            inRange[i] = { ...inRange[i], text: inRange[i].text.slice(matchedLen) };
            matchedLen = 0;
          }
        }
        break;
      }
      break;
    }

    // 4. Разбираем аргументы внутри
    while (i < limit) {
      const t = inRange[i];
      const textTrim = t.text.trim();
      if (!textTrim) {
        i++;
        continue;
      }

      // Проверка на ChatColor.COLOR или NamedTextColor.COLOR
      if (
        (textTrim === "ChatColor" || textTrim === "NamedTextColor" || textTrim === "TextColor") &&
        i + 2 < limit &&
        inRange[i + 1].text.trim() === "."
      ) {
        const colorName = inRange[i + 2].text.trim().toLowerCase();
        if (MC_COLOR_NAME_HEX[colorName]) {
          activeColorHex = MC_COLOR_NAME_HEX[colorName];
          i += 3;
          if (i < limit && inRange[i].text.trim() === "+") i++;
          continue;
        }
      }

      // Одиночный цветовой enum токен
      if (t.isColorEnum && MC_COLOR_NAME_HEX[textTrim.toLowerCase()]) {
        activeColorHex = MC_COLOR_NAME_HEX[textTrim.toLowerCase()];
        i++;
        if (i < limit && inRange[i].text.trim() === "+") i++;
        continue;
      }

      // Обфусцированный метод-дешифратор строк / кастомный шрифт (method1 / FancyFont)
      if (!t.isStr && /^[a-zA-Z_$][\w$]*(?:\.[a-zA-Z_$][\w$]*)?$/.test(textTrim)) {
        let nextIdx = i + 1;
        while (nextIdx < limit && inRange[nextIdx].text.trim() === "") nextIdx++;
        if (nextIdx < limit && inRange[nextIdx].text.trim() === "(") {
          let strIdx = nextIdx + 1;
          while (strIdx < limit && inRange[strIdx].text.trim() === "") strIdx++;
          if (strIdx < limit && inRange[strIdx].isStr) {
            let closeParenIdx = strIdx + 1;
            while (closeParenIdx < limit && inRange[closeParenIdx].text.trim() === "") closeParenIdx++;
            if (closeParenIdx < limit && inRange[closeParenIdx].text.trim().startsWith(")")) {
              const strToken = inRange[strIdx];
              const isFancy = isFancyFontMethod(textTrim);
              const titleNote = isFancy
                ? `Кастомный шрифт (${textTrim})`
                : undefined;
              parts.push(
                <span key={i}>
                  {renderStringContent(
                    strToken.text,
                    activeColorHex,
                    titleNote,
                    isFancy ? applyFancyFont : undefined,
                  )}
                </span>
              );
              const closeTok = inRange[closeParenIdx];
              const parenPos = closeTok.text.indexOf(")");
              const remainder = closeTok.text.slice(parenPos + 1);
              if (remainder.trim()) {
                inRange[closeParenIdx] = { ...closeTok, text: remainder };
                i = closeParenIdx;
              } else {
                i = closeParenIdx + 1;
              }
              continue;
            }
          }
        }
      }

      // Строковый литерал
      if (t.isStr) {
        parts.push(
          <span key={i}>
            {renderStringContent(t.text, activeColorHex)}
          </span>
        );
        i++;
        continue;
      }

      // Конкатенация '+'
      if (textTrim === "+") {
        i++;
        continue;
      }

      // Хвостовой аргумент исключения/Throwable, например ', e1' или ', ex'
      if (textTrim.startsWith(",") || (textTrim === "," && i + 1 < limit)) {
        const restTokens = inRange.slice(i, limit);
        const restText = restTokens.map(rt => rt.text).join("").trim();
        const exMatch = /^,\s*([a-zA-Z_$][\w$]*)$/.exec(restText);
        if (exMatch) {
          parts.push(
            <span
              key={`ex-${i}`}
              className="mono rounded bg-raised px-1.5 py-0.2 text-[10px] text-faint border border-line font-medium ml-1 select-none"
              title={`Исключение: ${exMatch[1]}`}
            >
              ⚡ {exMatch[1]}
            </span>
          );
          break;
        }
      }

      // Runtime server calls like manager.getAddons().size() or variable like permission
      const exprTokens: Token[] = [];
      const exprStart = i;
      while (i < limit) {
        const cur = inRange[i];
        const ct = cur.text.trim();
        if (ct === "+" || cur.isStr || ct.startsWith(",")) break;
        exprTokens.push(cur);
        i++;
      }

      if (exprTokens.length > 0) {
        let exprStr = exprTokens.map(et => et.text).join("").trim();
        let openCount = (exprStr.match(/\(/g) || []).length;
        let closeCount = (exprStr.match(/\)/g) || []).length;
        while (closeCount > openCount && exprStr.endsWith(")")) {
          exprStr = exprStr.slice(0, -1).trim();
          closeCount--;
        }
        if (exprStr && exprStr !== ")" && exprStr !== "(" && exprStr !== "))" && exprStr !== ")))") {
          parts.push(
            <RuntimeValueChip key={`rt-${exprStart}`} exprStr={exprStr} />
          );
        }
      }
    }

    let tagLabel = region.logLevel ? region.logLevel.toUpperCase() : "LOG";
    if (tagLabel === "SENDMESSAGE" || tagLabel === "SENDRAWMESSAGE" || tagLabel === "BROADCASTMESSAGE" || tagLabel === "BROADCAST") tagLabel = "MSG";
    else if (tagLabel === "SENDACTIONBAR") tagLabel = "ACTIONBAR";
    else if (tagLabel === "SENDTITLE") tagLabel = "TITLE";
    else if (tagLabel === "PRINTLN" || tagLabel === "PRINT") tagLabel = "OUT";
    else if (tagLabel === "SEVERE") tagLabel = "ERROR";
    else if (tagLabel === "WARNING") tagLabel = "WARN";

    const isSevere = region.logLevel === "severe" || region.logLevel === "error";
    const isWarn = region.logLevel === "warning" || region.logLevel === "warn";

    return (
      <>
        <span className="chain-chip-divider select-none text-[11px] text-faint/60 mr-1.5">│</span>
        <span
          className={
            "chain-chip-tag mr-2 " +
            (isSevere ? "tag-err text-err font-bold" : isWarn ? "tag-warn text-warn font-semibold" : "")
          }
        >
          {tagLabel}
        </span>
        {parts}
      </>
    );
  }

  // region.kind === "color"
  const inRange = tokensInRange(tokens, region.start, region.end);
  const parts: ReactNode[] = [];
  let activeColorHex: string | null = null;
  let i = 0;
  const limit = inRange.length;

  while (i < limit) {
    const t = inRange[i];
    const textTrim = t.text.trim();
    if (!textTrim) {
      i++;
      continue;
    }

    // ChatColor.COLOR или NamedTextColor.COLOR
    if (
      (textTrim === "ChatColor" || textTrim === "NamedTextColor" || textTrim === "TextColor") &&
      i + 2 < limit &&
      inRange[i + 1].text.trim() === "."
    ) {
      const colorName = inRange[i + 2].text.trim().toLowerCase();
      if (MC_COLOR_NAME_HEX[colorName]) {
        activeColorHex = MC_COLOR_NAME_HEX[colorName];
        i += 3;
        if (i < limit && inRange[i].text.trim() === "+") i++;
        continue;
      }
    }

    if (t.isColorEnum && MC_COLOR_NAME_HEX[textTrim.toLowerCase()]) {
      activeColorHex = MC_COLOR_NAME_HEX[textTrim.toLowerCase()];
      i++;
      if (i < limit && inRange[i].text.trim() === "+") i++;
      continue;
    }

    // Обфусцированный метод-дешифратор строк / кастомный шрифт (method1 / FancyFont)
    if (!t.isStr && /^[a-zA-Z_$][\w$]*(?:\.[a-zA-Z_$][\w$]*)?$/.test(textTrim)) {
      let nextIdx = i + 1;
      while (nextIdx < limit && inRange[nextIdx].text.trim() === "") nextIdx++;
      if (nextIdx < limit && inRange[nextIdx].text.trim() === "(") {
        let strIdx = nextIdx + 1;
        while (strIdx < limit && inRange[strIdx].text.trim() === "") strIdx++;
        if (strIdx < limit && inRange[strIdx].isStr) {
          let closeParenIdx = strIdx + 1;
          while (closeParenIdx < limit && inRange[closeParenIdx].text.trim() === "") closeParenIdx++;
          if (closeParenIdx < limit && inRange[closeParenIdx].text.trim().startsWith(")")) {
            const strToken = inRange[strIdx];
            const isFancy = isFancyFontMethod(textTrim);
            const titleNote = isFancy ? `Кастомный шрифт (${textTrim})` : undefined;
            parts.push(
              <span key={i}>
                {renderStringContent(
                  strToken.text,
                  activeColorHex,
                  titleNote,
                  isFancy ? applyFancyFont : undefined,
                )}
              </span>
            );
            const closeTok = inRange[closeParenIdx];
            const parenPos = closeTok.text.indexOf(")");
            const remainder = closeTok.text.slice(parenPos + 1);
            if (remainder.trim()) {
              inRange[closeParenIdx] = { ...closeTok, text: remainder };
              i = closeParenIdx;
            } else {
              i = closeParenIdx + 1;
            }
            continue;
          }
        }
      }
    }

    if (t.isStr) {
      parts.push(
        <span key={i}>
          {renderStringContent(t.text, activeColorHex ?? t.inheritedColor ?? null, undefined, t.isFancyFontCall ? applyFancyFont : undefined)}
        </span>
      );
      i++;
      continue;
    }

    if (textTrim === "+") {
      i++;
      continue;
    }

    // Runtime-выражения в цепочке цветов (например getDescription().getVersion() или Bukkit.getVersion())
    const exprTokens: Token[] = [];
    const exprStart = i;
    while (i < limit) {
      const cur = inRange[i];
      const ct = cur.text.trim();
      if (ct === "+" || cur.isStr || ct.startsWith(",")) break;
      exprTokens.push(cur);
      i++;
    }

    if (exprTokens.length > 0) {
      let exprStr = exprTokens.map(et => et.text).join("").trim();
      let openCount = (exprStr.match(/\(/g) || []).length;
      let closeCount = (exprStr.match(/\)/g) || []).length;
      while (closeCount > openCount && exprStr.endsWith(")")) {
        exprStr = exprStr.slice(0, -1).trim();
        closeCount--;
      }
      if (exprStr && exprStr !== ")" && exprStr !== "(" && exprStr !== "))" && exprStr !== ")))") {
        parts.push(<RuntimeValueChip key={`color-rt-${exprStart}`} exprStr={exprStr} />);
      }
    }
  }

  return <>{parts}</>;
}

// БАГ-ФИКС v1.9.13 (tsc TS2322 "'key' does not exist in type" - та же
// причина, что и в Sidebar.tsx/JobCard - см. тот комментарий): именованный
// тип вместо inline-объекта в параметрах.
type ChainChipProps = {
  tokens: Token[];
  region: Region;
  chipKey: string;
  expanded: boolean;
  onToggle: (key: string) => void;
};
function ChainChip({
  tokens,
  region,
  chipKey,
  expanded,
  onToggle,
}: ChainChipProps) {
  if (expanded) {
    const inRange = tokensInRange(tokens, region.start, region.end);
    return (
      <span className="chain-chip-expanded" title="Свернуть обратно" onClick={() => onToggle(chipKey)}>
        {inRange.map((t, i) => renderToken(t, i, undefined, true))}
      </span>
    );
  }
  return (
    <span
      className="chain-chip align-top"
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
  disableVsCodeLogs: boolean,
  tokens: Token[],
  lineKey: string,
  expanded: Set<string>,
  onToggle: (key: string) => void,
): ReactNode[] {
  const logRegions = disableVsCodeLogs ? [] : findLogRegions(tokens.map(t => t.text).join(""));
  const colorRegions = disableVsCodeLogs ? [] : findColorChainRegions(tokens, logRegions);
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

export const JavaCode = memo(function JavaCode({ code, wrap, disableVsCodeLogs }: { code: string; wrap?: boolean; disableVsCodeLogs?: boolean }) {
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
            className="mono w-12 flex-none pr-4 text-right text-[0.88em] leading-[1.75] text-faint select-none cursor-pointer hover:text-ink transition-colors"
            title="Кликните, чтобы подсветить строку"
            onClick={() => {
              const el = document.getElementById(`codeline-${li + 1}`);
              if (el) {
                el.classList.add("bg-acid/20");
                setTimeout(() => el.classList.remove("bg-acid/20"), 1500);
              }
            }}
          >
            {li + 1}
          </span>

          <span
            className={
              "mono text-[1em] leading-[1.75] text-ink/90 " +
              (wrap ? "whitespace-pre-wrap break-words" : "whitespace-pre")
            }
          >
            {row.map((seg, si) =>
              seg.comment ? (
                <span key={si} className="tok-c">
                  {seg.text}
                </span>
              ) : (
                <span key={si}>{renderLineTokens(!!disableVsCodeLogs, seg.tokens, `${li}:${si}`, expanded, onToggle)}</span>
              ),
            )}
          </span>
        </div>
      ))}
    </>
  );
});
