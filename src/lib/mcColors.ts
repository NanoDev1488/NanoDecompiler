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

export const MC_CODE_RE = /[&§]([0-9a-fA-Fk-oK-OrR])/;
const MC_CODE_RE_G = /[&§]([0-9a-fA-Fk-oK-OrR])/g;

/**
 * Разбирает текст на сегменты по цветовым кодам и возвращает JSX: перед
 * каждым куском текста, к которому применяется код, - маленький квадратик-
 * образец цвета (чтобы код был виден И читаем одновременно, а не просто
 * красил весь остаток строки без объяснения почему), сам текст красится
 * inline через style (не через CSS-класс - палитра Minecraft фиксная и не
 * зависит от темы редактора).
 */
export function renderMcColored(text: string): ReactNode[] {
  const out: ReactNode[] = [];
  let last = 0;
  let currentColor: string | null = null;
  let key = 0;
  MC_CODE_RE_G.lastIndex = 0;
  let m: RegExpExecArray | null;
  const pushText = (s: string, end: number) => {
    if (!s) return;
    out.push(
      createElement(
        "span",
        { key: key++, style: currentColor ? { color: currentColor } : undefined },
        s,
      ),
    );
    void end;
  };
  while ((m = MC_CODE_RE_G.exec(text)) !== null) {
    pushText(text.slice(last, m.index), m.index);
    const code = m[1].toLowerCase();
    if (MC_FORMAT_CODES.has(code)) {
      // формат-код (жирный/курсив/сброс и т.п.) - не меняет цвет, просто
      // помечаем маленькой серой меткой, чтобы код не выглядел "съеденным".
      out.push(
        createElement(
          "span",
          { key: key++, className: "mc-code-marker", title: `Minecraft формат-код: ${m[0]}` },
          m[0],
        ),
      );
      if (code === "r") currentColor = null;
    } else {
      const hex = MC_COLOR_HEX[code];
      out.push(
        createElement("span", {
          key: key++,
          className: "mc-code-swatch",
          title: `Minecraft цвет: ${m[0]}`,
          style: { background: hex },
        }),
      );
      currentColor = hex ?? currentColor;
    }
    last = m.index + m[0].length;
  }
  pushText(text.slice(last), text.length);
  return out;
}
