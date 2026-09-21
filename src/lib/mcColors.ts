import { createElement, type ReactNode } from "react";

// НОВОЕ v1.8.0 (по прямой просьбе - "первым делом" сделать цвета
// Minecraft в просмотрщике, "везде во всех файлах"): общий модуль,
// переиспользуемый и в подсветке .properties/.yml/.json (реальные
// конфиги сообщений), и в подсветке .java (строковые литералы -
// "любая функция, которая выводит текст в игре" на практике просто
// означает "любая строка с цветовыми кодами", отслеживать САМ вызов
// конкретной Bukkit-функции типа sendMessage() не нужно - код цвета в
// строковом литерале сам по себе достаточный и надёжный сигнал).
//
// Поддержаны ОБА формата, которые реально встречаются в декомпилированном
// коде и конфигах: "&a" (наиболее частый - плагины сами транслируют его
// в "§a" через ChatColor.translateAlternateColorCodes перед отправкой) и
// сырой "§a" (реже - когда код уже "как есть" в константе/конфиге).
const MC_COLOR_HEX: Record<string, string> = {
  "0": "#000000",
  "1": "#0000AA",
  "2": "#00AA00",
  "3": "#00AAAA",
  "4": "#AA0000",
  "5": "#AA00AA",
  "6": "#FFAA00",
  "7": "#AAAAAA",
  "8": "#555555",
  "9": "#5555FF",
  a: "#55FF55",
  b: "#55FFFF",
  c: "#FF5555",
  d: "#FF55FF",
  e: "#FFFF55",
  f: "#FFFFFF",
};
// k=obfuscated, l=bold, m=strikethrough, n=underline, o=italic, r=reset -
// эти не меняют ЦВЕТ, но всё равно распознаём (иначе "&l" осталось бы
// НЕ подсвечено вообще, что выглядело бы как пропуск/баг). Показываем
// нейтральным серым маркером (не белым/не текущим цветом - чтобы не
// путать с обычным текстом).
const MC_FORMAT_CODES = new Set(["k", "l", "m", "n", "o", "r"]);

// НОВОЕ v1.7.5 (по прямой просьбе - детект НЕ только сырых &-кодов, но и
// ВЫЗОВОВ методов, которые семантически красят текст: `foo.GREEN("text")`
// (именованная цветовая константа - Bukkit ChatColor или собственный
// аналог плагина) или `foo.Color("&AAAA")` (кастомный метод-транслятор с
// СВОИМ форматом кодов, который движок не может знать заранее - в таком
// случае честно помечаем "это будет цветной текст в игре", не угадывая
// точный итоговый цвет из неизвестного формата).
export const MC_COLOR_NAME_HEX: Record<string, string> = {
  black: "#000000",
  dark_blue: "#0000AA",
  dark_green: "#00AA00",
  dark_aqua: "#00AAAA",
  dark_red: "#AA0000",
  dark_purple: "#AA00AA",
  gold: "#FFAA00",
  gray: "#AAAAAA",
  grey: "#AAAAAA",
  dark_gray: "#555555",
  dark_grey: "#555555",
  blue: "#5555FF",
  green: "#55FF55",
  aqua: "#55FFFF",
  red: "#FF5555",
  light_purple: "#FF55FF",
  purple: "#FF55FF",
  yellow: "#FFFF55",
  white: "#FFFFFF",
};

// `.RED(`, `.someClass.DARK_AQUA(` и т.п. - завершается ИМЕННО одним из
// известных цветовых имён прямо перед открывающей скобкой вызова.
export const COLOR_METHOD_CALL_RE = new RegExp(`\\.(${Object.keys(MC_COLOR_NAME_HEX).join("|")})\\s*\\(\\s*$`, "i");
// БАГ-ФИКС v1.7.6 (реальная жалоба - "ChatColor.RED(...) + текст не
// подсвечивается"): настоящий Bukkit ChatColor.RED - это ENUM-КОНСТАНТА,
// а НЕ метод! Реальный код выглядит как `ChatColor.RED + "текст"`
// (конкатенация), а НЕ `ChatColor.RED("текст")` (вызов) - паттерн выше
// ловит только вызовы, но самый частый РЕАЛЬНЫЙ случай - именно
// конкатенация. Тот же список цветовых имён, но перед строкой стоит `+`,
// а не `(`.
export const COLOR_CONCAT_RE = new RegExp(`\\.(${Object.keys(MC_COLOR_NAME_HEX).join("|")})\\s*\\+\\s*$`, "i");
// Обобщённый случай - имя метода САМО содержит "color"/"colour"/"paint" -
// формат внутри неизвестен (может быть что угодно, включая "AAAA" из
// примера), поэтому просто помечаем строку как "будет обработана как
// цвет", не пытаясь угадать итоговый оттенок.
export const GENERIC_COLOR_CALL_RE = /\.\w*(colou?r|paint)\w*\s*\(\s*$/i;

// НОВОЕ (HANDOFF_URGENT п.8, реальный пример пользователя -
// `"§c✖ " + FancyFont.stylize("текст")`): метод НЕ содержит "color" в
// имени (GENERIC_COLOR_CALL_RE выше его не поймает), но раз он вызывается
// сразу после конкатенации с частью, где УЖЕ есть §/&-код - на практике
// это почти всегда тоже часть того же самого цветного сообщения (свой
// метод форматирования текста типа FancyFont/small-caps/транслитерация
// и т.п.). Ловим сам факт "+ Вызов(" перед открывающей кавычкой - решение,
// красить ли эту строку тоже, принимает вызывающий код (javaHighlight.tsx)
// на основе того, встречался ли цветовой сигнал РАНЕЕ на этой же строке.
export const CHAIN_CALL_AFTER_PLUS_RE = /\+\s*[A-Za-z_$][\w$]*(?:\.[A-Za-z_$][\w$]*)*\(\s*$/;

// НОВОЕ v1.9.5 (доработка п.8 по прямой просьбе - "неизвестный формат
// method1() должен красится унаследованным цветом, а не нейтральным
// серым"): возвращает hex ПОСЛЕДНЕГО цветового §/&-кода внутри строки (не
// форматирующего - k/l/m/n/o/r цвет не меняют, только r его СБРАСЫВАЕТ) -
// или null, если кодов не было вовсе. currentHex - цвет, унаследованный
// СНАРУЖИ (от предыдущей части конкатенации) - нужен, чтобы неоконченная
// строка типа "текст без кодов" не сбрасывала уже установленный цвет.
export function lastColorHexInString(text: string, currentHex: string | null): string | null {
  let hex = currentHex;
  MC_CODE_RE_G.lastIndex = 0;
  let m: RegExpExecArray | null;
  while ((m = MC_CODE_RE_G.exec(text)) !== null) {
    const code = m[1].toLowerCase();
    if (code === "r") hex = null;
    else if (!MC_FORMAT_CODES.has(code) && MC_COLOR_HEX[code]) hex = MC_COLOR_HEX[code];
  }
  return hex;
}

export const MC_CODE_RE = /[&§]([0-9a-fA-Fk-oK-OrR])/;
const MC_CODE_RE_G = /[&§]([0-9a-fA-Fk-oK-OrR])/g;

/** Строка передаётся в `.GREEN("текст")`-подобный вызов - красим ЦЕЛИКОМ
 * жирным известным цветом (само имя метода уже однозначно говорит, какой
 * цвет применится в игре - в отличие от кастомного Color(), тут гадать
 * не нужно). НОВОЕ v1.7.6 (по просьбе - "цвета чуть ярче для новых"):
 * лёгкое text-shadow свечение тем же цветом - визуально выделяет ИМЕННО
 * эти (выведенные из имени метода, не из сырого &-кода) совпадения на
 * фоне остального текста, не трогая исходную палитру MC_COLOR_HEX
 * (используется и здесь, и в renderMcColored для сырых кодов - если
 * бы просто "осветлил" сам цвет, старые &-коды тоже стали бы ярче). */
export function renderNamedColorText(text: string, colorName: string): ReactNode {
  const hex = MC_COLOR_NAME_HEX[colorName.toLowerCase()];
  return createElement(
    "span",
    { style: hex ? { color: hex, fontWeight: 700, textShadow: `0 0 6px ${hex}66` } : undefined },
    text,
  );
}

/** Строка передаётся в СВОЙ метод плагина типа `.Color("&AAAA")` -
 * формат кодов внутри неизвестен движку (может быть что угодно), поэтому
 * НЕ угадываем итоговый цвет - честно подчёркиваем волнистой линией
 * "это будет обработано как цвет в игре", реальный оттенок пользователь
 * увидит только запустив код.
 *
 * НОВОЕ v1.9.5 (доработка по прямой просьбе - "унаследованный цвет вместо
 * нейтрального серого"): если ИЗВЕСТЕН цвет, действовавший на этом месте
 * строки РАНЬШЕ (см. inheritedColor в javaHighlight.tsx - пришёл от
 * ChatColor.RED/сырого §-кода до этого вызова), красим текст ИМ - это НЕ
 * гарантия, что метод сохраняет цвет (он мог бы его сбросить или заменить
 * другим), но на практике такие кастомные method1()-подобные обёртки
 * почти всегда просто МЕНЯЮТ ШРИФТ/РЕГИСТР, не трогая цвет - унаследованный
 * цвет куда полезнее нейтрального серого. Волнистое подчёркивание +
 * title остаются - это по-прежнему явно помеченная догадка, не факт. */
export function renderUnknownColorMarked(text: string, inheritedHex?: string | null): ReactNode {
  const style: { color?: string } = {};
  if (inheritedHex) style.color = inheritedHex;
  return createElement(
    "span",
    {
      className: "mc-code-unknown",
      style: inheritedHex ? style : undefined,
      title: inheritedHex
        ? `Похоже на вызов цветовой функции - формат кодов свой, но цвет ДО этого места был ${inheritedHex} - вероятно, сохраняется`
        : "Похоже на вызов цветовой функции - формат кодов свой, конкретный цвет неизвестен",
    },
    text,
  );
}

/**
 * Разбирает текст на сегменты по цветовым кодам и возвращает JSX: сам код
 * (&a/§a) остаётся виден, но приглушённым/мелким (не пропадает - иначе
 * невозможно понять, ГДЕ именно он стоит и что дальше правится вручную),
 * а весь ТЕКСТ ПОСЛЕ кода красится + становится жирным - так нагляднее
 * видно результат (как реально будет выглядеть сообщение в игре), чем
 * маленький квадратик-образец перед обычным текстом.
 */
export function renderMcColored(text: string): ReactNode[] {
  const out: ReactNode[] = [];
  let last = 0;
  let currentColor: string | null = null;
  let currentColorCode: string | null = null;
  let currentBold = false;
  let key = 0;
  MC_CODE_RE_G.lastIndex = 0;
  let m: RegExpExecArray | null;
  // БАГ-ФИКС (HANDOFF_URGENT п.11): раньше title= был ТОЛЬКО на самом
  // §-коде (крошечный маркер перед текстом) - наведение на сам цветной
  // текст (то, что реально видно в игре, а не служебный код) не
  // показывало вообще ничего. Собираем то же описание, что уже есть на
  // маркере, и вешаем на span с текстом тоже.
  const pushText = (s: string) => {
    if (!s) return;
    const style: { color?: string; fontWeight?: number } = {};
    const titleParts: string[] = [];
    if (currentColor) {
      style.color = currentColor;
      titleParts.push(`цвет: ${currentColorCode ?? ""} (${currentColor})`);
    }
    if (currentBold) {
      style.fontWeight = 700;
      titleParts.push("жирный (&l)");
    }
    out.push(
      createElement(
        "span",
        {
          key: key++,
          style: Object.keys(style).length ? style : undefined,
          title: titleParts.length ? titleParts.join(", ") : undefined,
        },
        s,
      ),
    );
  };
  while ((m = MC_CODE_RE_G.exec(text)) !== null) {
    pushText(text.slice(last, m.index));
    const code = m[1].toLowerCase();
    // Сам код печатаем мелким приглушённым текстом, а НЕ отдельным
    // квадратиком - так видно ТОЧНОЕ место, где стоит код, не разрывая
    // визуально строку декоративным элементом.
    out.push(
      createElement(
        "span",
        { key: key++, className: "mc-code-marker", title: MC_FORMAT_CODES.has(code) ? `формат: ${m[0]}` : `цвет: ${m[0]}` },
        m[0],
      ),
    );
    if (code === "r") {
      currentColor = null;
      currentColorCode = null;
      currentBold = false;
    } else if (code === "l") {
      currentBold = true;
    } else if (MC_FORMAT_CODES.has(code)) {
      // k/m/n/o (обфускация/зачёркнутый/подчёркнутый/курсив) - цвет не
      // меняют, жирность тоже не трогаем, только сам код уже показан выше.
    } else if (MC_COLOR_HEX[code]) {
      currentColor = MC_COLOR_HEX[code];
      currentColorCode = m[0];
    }
    last = m.index + m[0].length;
  }
  pushText(text.slice(last));
  return out;
}
