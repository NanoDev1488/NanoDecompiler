// Портировано 1:1 из main.py::classify_line (см. HANDOFF_1_ARCHITECTURE.md).
// Держим логику в одном месте и в Python, и здесь по смыслу совпадающей —
// движок остаётся источником истины, это просто раскраска его же вывода.
export type LineKind = "banner" | "error" | "warn" | "ok" | "info" | "dim";

export function classifyLine(raw: string): LineKind {
  const s = raw.trim();
  if (!s) return "dim";
  if ("╭╰│─".includes(s[0]) || s.includes("NanoDecompiler") || s.includes("Java-декомпилятор")) {
    return "banner";
  }
  if (s.startsWith("[!]") || s.includes("ОШИБКА") || s.toLowerCase().includes("ошибка")) {
    return "error";
  }
  if (
    s.startsWith("[*] Не хватает") ||
    s.includes("НЕ НАЙДЕН") ||
    s.includes("НЕ НАЙДЕНА") ||
    s.startsWith("ВНИМАНИЕ")
  ) {
    return "warn";
  }
  if (s.startsWith("[+]") || s.includes("Всё готово к работе") || s.includes("Готово")) {
    return "ok";
  }
  if (s.startsWith("[*]") || raw.startsWith("   ")) {
    return "info";
  }
  return "dim";
}
