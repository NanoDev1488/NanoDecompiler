// Портировано 1:1 из main.py::classify_line - main.py удалён (HANDOFF_46),
// движок сейчас на C++ (cli_main.cpp::banner_text() и вывод "[*]"/"[+]"/"[!]"
// префиксами) - формат вывода сохранён специально, чтобы эта раскраска не
// сломалась. Держим логику классификации в одном месте (здесь) - движок
// остаётся источником истины по СОДЕРЖАНИЮ строк, это просто раскраска его
// же вывода.
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
