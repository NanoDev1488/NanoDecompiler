import * as fs from "fs";
import * as path from "path";
import * as zlib from "zlib";

/**
 * Читает сводку по .jar (размер/классы/пакеты/версия Java/имя плагина)
 * напрямую через central directory ZIP-формата, БЕЗ спавна подпроцесса
 * движка. См. HANDOFF_22/46: раньше каждый выбор jar в UI спавнил
 * `python3 main.py --jar-summary ...` (или собранный .exe) - для
 * PyInstaller onefile-сборки это ещё и самораспаковка exe во временную
 * папку НА КАЖДЫЙ вызов (известная особенность --onefile) - десятки-
 * сотни миллисекунд, ощущавшиеся как "очень долго определяется размер".
 * Движок теперь на C++ (cli_main.cpp --jar-summary, HANDOFF_46) - этой
 * проблемы с самораспаковкой больше нет, но читать central directory
 * напрямую в Node всё равно быстрее (без spawn() вообще) - оставлено как
 * основной путь. Здесь читаются только МЕТАДАННЫЕ central directory (имена
 * файлов, размеры, смещения) - без распаковки содержимого, КРОМЕ двух
 * маленьких файлов (первый .class - 8 байт заголовка для версии Java,
 * plugin.yml целиком - обычно первые сотни байт) - на реальных jar это
 * исполняется за единицы миллисекунд, а не сотни.
 *
 * ZIP64 (jar'ы больше 4ГБ или с 65535+ записей - на практике НЕ
 * встречается у Bukkit-плагинов) сознательно не поддержан - при
 * малейшем подозрении (сигнатуры 0xffffffff/0xffff в EOCD) бросаем
 * исключение, вызывающая сторона (main.ts) откатывается на старый путь
 * через подпроцесс движка (jar_summary.hpp/.cpp, см. HANDOFF_46) -
 * корректность важнее скорости на редком edge-case.
 */

export type JarSummary = {
  name: string;
  size: string;
  sizeBytes: number;
  classes: number;
  packages: number;
  java: string;
  plugin_name: string | null;
};

/** Та же формула, что и java_version_from_major() в jar_summary.cpp -
 * держим в одном месте только концептуально (два языка, увы, дублировать
 * неизбежно), но проверено на точное совпадение вывода для major 45-68. */
function javaVersionFromMajor(major: number): string {
  return major <= 48 ? `Java 1.${major - 44}` : `Java ${major - 44}`;
}

function formatSize(bytes: number): string {
  return bytes >= 1024 * 1024 ? `${(bytes / 1024 / 1024).toFixed(1)} МБ` : `${(bytes / 1024).toFixed(1)} КБ`;
}

type CentralEntry = { name: string; method: number; compSize: number; localOffset: number };

function findEndOfCentralDirectory(fd: number, fileSize: number): { cdOffset: number; cdSize: number; total: number } {
  // EOCD - минимум 22 байта, максимум 22+65535 (если есть comment) - на
  // практике comment у jar почти всегда пуст, но ищем честно с конца.
  const searchSize = Math.min(fileSize, 65557);
  const buf = Buffer.alloc(searchSize);
  fs.readSync(fd, buf, 0, searchSize, fileSize - searchSize);
  for (let i = buf.length - 22; i >= 0; i--) {
    if (buf.readUInt32LE(i) === 0x06054b50) {
      const total = buf.readUInt16LE(i + 10);
      const cdSize = buf.readUInt32LE(i + 12);
      const cdOffset = buf.readUInt32LE(i + 16);
      if (total === 0xffff || cdSize === 0xffffffff || cdOffset === 0xffffffff) {
        throw new Error("ZIP64 - не поддержано в быстром пути");
      }
      return { cdOffset, cdSize, total };
    }
  }
  throw new Error("не найден EOCD (не ZIP/jar файл?)");
}

function parseCentralDirectory(fd: number, cdOffset: number, cdSize: number, total: number): CentralEntry[] {
  const buf = Buffer.alloc(cdSize);
  fs.readSync(fd, buf, 0, cdSize, cdOffset);
  const entries: CentralEntry[] = [];
  let p = 0;
  for (let i = 0; i < total; i++) {
    if (buf.readUInt32LE(p) !== 0x02014b50) break; // рассинхрон - лучше остановиться, чем читать мусор
    const method = buf.readUInt16LE(p + 10);
    const compSize = buf.readUInt32LE(p + 20);
    const nameLen = buf.readUInt16LE(p + 28);
    const extraLen = buf.readUInt16LE(p + 30);
    const commentLen = buf.readUInt16LE(p + 32);
    const localOffset = buf.readUInt32LE(p + 42);
    const name = buf.toString("utf-8", p + 46, p + 46 + nameLen);
    entries.push({ name, method, compSize, localOffset });
    p += 46 + nameLen + extraLen + commentLen;
  }
  return entries;
}

/** Читает и распаковывает содержимое одной записи по её local-header offset
 * (нельзя использовать смещения/длины из central directory для ДАННЫХ -
 * только local header даёт точное начало сжатых данных, extra-поля там
 * часто отличаются по длине от central directory). */
function readEntryData(fd: number, entry: CentralEntry): Buffer {
  const localHeader = Buffer.alloc(30);
  fs.readSync(fd, localHeader, 0, 30, entry.localOffset);
  if (localHeader.readUInt32LE(0) !== 0x04034b50) throw new Error("битый local header");
  const nameLen = localHeader.readUInt16LE(26);
  const extraLen = localHeader.readUInt16LE(28);
  const dataOffset = entry.localOffset + 30 + nameLen + extraLen;
  const raw = Buffer.alloc(entry.compSize);
  fs.readSync(fd, raw, 0, entry.compSize, dataOffset);
  if (entry.method === 0) return raw; // stored, без сжатия
  if (entry.method === 8) return zlib.inflateRawSync(raw); // deflate - обычный случай
  throw new Error(`неподдерживаемый метод сжатия ${entry.method}`);
}

export function readJarSummaryNative(jarPath: string): JarSummary {
  const fileSize = fs.statSync(jarPath).size;
  const fd = fs.openSync(jarPath, "r");
  try {
    const { cdOffset, cdSize, total } = findEndOfCentralDirectory(fd, fileSize);
    const entries = parseCentralDirectory(fd, cdOffset, cdSize, total);

    const classEntries = entries.filter((e) => e.name.endsWith(".class") && !e.name.includes("module-info"));
    const packages = new Set<string>();
    for (const e of classEntries) {
      const i = e.name.lastIndexOf("/");
      if (i >= 0) packages.add(e.name.slice(0, i));
    }

    let java = "?";
    if (classEntries.length > 0) {
      try {
        const data = readEntryData(fd, classEntries[0]);
        if (data.length >= 8) java = javaVersionFromMajor(data.readUInt16BE(6));
      } catch {
        // не критично - просто не покажем версию Java
      }
    }

    let pluginName: string | null = null;
    const pluginYml = entries.find((e) => e.name === "plugin.yml");
    if (pluginYml) {
      try {
        const text = readEntryData(fd, pluginYml).toString("utf-8");
        for (const line of text.split(/\r?\n/)) {
          const t = line.trim();
          if (t.startsWith("name:")) {
            pluginName = t.slice(5).trim().replace(/^['"]|['"]$/g, "");
            break;
          }
        }
      } catch {
        // не критично
      }
    }

    return {
      name: path.basename(jarPath),
      size: formatSize(fileSize),
      sizeBytes: fileSize,
      classes: classEntries.length,
      packages: packages.size,
      java,
      plugin_name: pluginName,
    };
  } finally {
    fs.closeSync(fd);
  }
}
