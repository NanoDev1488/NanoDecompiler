import * as fs from "fs";
import * as path from "path";
import * as os from "os";
import * as zlib from "zlib";
import { spawn } from "child_process";
import * as tar from "tar";
import { readJarSummaryNative, type JarSummary } from "./jarSummary";

// eslint-disable-next-line @typescript-eslint/no-var-requires
const unrar = require("node-unrar-js");
// eslint-disable-next-line @typescript-eslint/no-var-requires
const sevenBin = require("7zip-bin");

export interface ArchiveProgressEvent {
  archivePath: string;
  percent: number;
  currentFile: string;
  extractedFiles: number;
  totalFiles: number;
  etaSeconds: number;
  speedBytesPerSec: number;
  tempDir: string;
}

export interface DiscoveredPlugin {
  fileName: string;
  jarPath: string;
  relPath: string;
  sizeBytes: number;
  classCount: number | null;
  pluginName: string | null;
  pluginAuthor: string | null;
  platform: string;
  isPlugin: boolean;
  isServerCore: boolean;
  coreReason?: string;
}

export interface ArchiveExtractResult {
  ok: boolean;
  archivePath: string;
  tempDir: string;
  plugins: DiscoveredPlugin[];
  serverCores: DiscoveredPlugin[];
  skippedNonPlugins: number;
  totalJarsFound: number;
  error?: string;
}

/** Проверяет, является ли файл поддерживаемым архивом */
export function isSupportedArchive(filePath: string): boolean {
  const lower = filePath.toLowerCase();
  return (
    lower.endsWith(".zip") ||
    lower.endsWith(".tar.gz") ||
    lower.endsWith(".tgz") ||
    lower.endsWith(".tar") ||
    lower.endsWith(".7z") ||
    lower.endsWith(".7zip") ||
    lower.endsWith(".rar")
  );
}

/** Получает путь к бинарнику 7za */
function get7zaPath(): string | null {
  try {
    if (sevenBin && sevenBin.path7za && fs.existsSync(sevenBin.path7za)) {
      return sevenBin.path7za;
    }
  } catch {
    // fallback
  }

  // Проверяем bundled в extraResources
  const candidates = [
    path.join(process.resourcesPath || "", "engine", "bin", process.platform === "win32" ? "7za.exe" : "7za"),
    path.join(process.resourcesPath || "", "engine", process.platform === "win32" ? "7za.exe" : "7za"),
    path.join(__dirname, "..", "node_modules", "7zip-bin", process.platform, process.arch, process.platform === "win32" ? "7za.exe" : "7za"),
  ];

  for (const c of candidates) {
    if (fs.existsSync(c)) return c;
  }
  return null;
}

/**
 * Читает central directory JAR-файла и определяет, является ли он
 * плагином (Bukkit/Paper/Bungee/Velocity), серверным ядром или библиотекой.
 */
export async function inspectJarForPlugin(jarPath: string, rootDir: string): Promise<DiscoveredPlugin> {
  const fileName = path.basename(jarPath);
  const relPath = path.relative(rootDir, jarPath).replace(/\\/g, "/");
  let sizeBytes = 0;
  try {
    const stat = await fs.promises.stat(jarPath);
    sizeBytes = stat.size;
  } catch {
    // ignore
  }

  let summary: JarSummary | null = null;
  try {
    summary = await readJarSummaryNative(jarPath);
  } catch {
    // ignore
  }

  // Читаем список файлов внутри jar
  const entryNames: string[] = [];
  try {
    const fh = await fs.promises.open(jarPath, "r");
    try {
      const searchSize = Math.min(sizeBytes, 65557);
      const buf = Buffer.alloc(searchSize);
      await fh.read(buf, 0, searchSize, sizeBytes - searchSize);
      let eocd = -1;
      for (let i = buf.length - 22; i >= 0; i--) {
        if (buf.readUInt32LE(i) === 0x06054b50) {
          eocd = i;
          break;
        }
      }
      if (eocd >= 0) {
        const cdOffset = buf.readUInt32LE(eocd + 16);
        const cdSize = buf.readUInt32LE(eocd + 12);
        const total = buf.readUInt16LE(eocd + 10);
        if (total < 65535 && cdOffset !== 0xffffffff) {
          const cdBuf = Buffer.alloc(cdSize);
          await fh.read(cdBuf, 0, cdSize, cdOffset);
          let p = 0;
          for (let i = 0; i < total; i++) {
            if (cdBuf.readUInt32LE(p) !== 0x02014b50) break;
            const nameLen = cdBuf.readUInt16LE(p + 28);
            const extraLen = cdBuf.readUInt16LE(p + 30);
            const commentLen = cdBuf.readUInt16LE(p + 32);
            const name = cdBuf.toString("utf-8", p + 46, p + 46 + nameLen);
            entryNames.push(name);
            p += 46 + nameLen + extraLen + commentLen;
          }
        }
      }
    } finally {
      await fh.close();
    }
  } catch {
    // fallback
  }

  const has = (name: string) => entryNames.includes(name);

  // 1. Проверка на СЕРВЕРНОЕ ЯДРО (Server Core)
  const isPaperclip = entryNames.some(e => e.startsWith("io/papermc/paperclip") || e.startsWith("META-INF/versions/"));
  const isBundler = entryNames.some(e => e.startsWith("net/minecraft/bundler/"));
  const isMinecraftServer = entryNames.some(e => e.startsWith("net/minecraft/server/MinecraftServer.class") || e.startsWith("net/minecraft/server/Main.class"));
  const isCraftBukkitCore = entryNames.some(e => e.startsWith("org/bukkit/craftbukkit/Main.class")) && !has("plugin.yml");
  const isServerProperties = has("server.properties") || has("eula.txt");
  const lowerName = fileName.toLowerCase();
  const isServerName = /^(paper|spigot|purpur|folia|pufferfish|craftbukkit|server|minecraft_server|tuinity)-/i.test(lowerName) || lowerName === "server.jar";

  if (isPaperclip || isBundler || isMinecraftServer || isCraftBukkitCore || (isServerName && !has("plugin.yml"))) {
    let coreReason = "Обнаружены сигнатуры сервера Minecraft";
    if (isPaperclip) coreReason = "Paperclip загрузчик сервера (Paper/Purpur/Folia)";
    else if (isBundler) coreReason = "Vanilla / Bundler серверный загрузчик";
    else if (isMinecraftServer) coreReason = "Серверное ядро net.minecraft.server";
    else if (isCraftBukkitCore) coreReason = "Серверное ядро CraftBukkit / Spigot";
    else if (isServerName) coreReason = "Серверный архив ядра (не является плагином)";

    return {
      fileName,
      jarPath,
      relPath,
      sizeBytes,
      classCount: summary?.classes ?? null,
      pluginName: summary?.plugin_name ?? fileName,
      pluginAuthor: summary?.plugin_author ?? null,
      platform: "Серверное ядро",
      isPlugin: false,
      isServerCore: true,
      coreReason,
    };
  }

  // 2. Проверка на ПЛАГИН (Bukkit / Paper / Bungee / Velocity / Sponge)
  const hasPaperPlugin = has("paper-plugin.yml") || has("META-INF/paper-plugin.yml");
  const hasBukkitPlugin = has("plugin.yml");
  const hasBungeePlugin = has("bungee.yml") || has("waterfall.yml");
  const hasVelocityPlugin = has("velocity-plugin.json") || has("META-INF/velocity-plugin.json");
  const hasSpongePlugin = has("sponge_plugins.json") || has("META-INF/sponge_plugins.json");

  if (hasPaperPlugin || hasBukkitPlugin || hasBungeePlugin || hasVelocityPlugin || hasSpongePlugin) {
    let platform = "Bukkit/Spigot";
    if (hasPaperPlugin) platform = "Paper";
    else if (hasVelocityPlugin) platform = "Velocity";
    else if (hasBungeePlugin) platform = "BungeeCord";
    else if (hasSpongePlugin) platform = "Sponge";

    return {
      fileName,
      jarPath,
      relPath,
      sizeBytes,
      classCount: summary?.classes ?? null,
      pluginName: summary?.plugin_name ?? fileName.replace(/\.jar$/i, ""),
      pluginAuthor: summary?.plugin_author ?? null,
      platform,
      isPlugin: true,
      isServerCore: false,
    };
  }

  // 3. Сторонняя библиотека или неизвестный jar без манифеста плагина
  return {
    fileName,
    jarPath,
    relPath,
    sizeBytes,
    classCount: summary?.classes ?? null,
    pluginName: summary?.plugin_name ?? null,
    pluginAuthor: summary?.plugin_author ?? null,
    platform: "Сторонняя библиотека (не плагин)",
    isPlugin: false,
    isServerCore: false,
    coreReason: "Отсутствует манифест плагина (plugin.yml / paper-plugin.yml / bungee.yml / velocity-plugin.json)",
  };
}

/** Рекурсивный поиск файлов .jar в папке */
async function scanJars(dir: string): Promise<string[]> {
  const result: string[] = [];
  const entries = await fs.promises.readdir(dir, { withFileTypes: true });
  for (const entry of entries) {
    const fullPath = path.join(dir, entry.name);
    if (entry.isDirectory()) {
      const nested = await scanJars(fullPath);
      result.push(...nested);
    } else if (entry.isFile() && /\.jar$/i.test(entry.name)) {
      result.push(fullPath);
    }
  }
  return result;
}

/** Создает чистую временную папку для разархивации */
function createTempArchiveDir(archivePath: string): string {
  const base = path.basename(archivePath).replace(/[^a-zA-Z0-9_\-\.]/g, "_");
  const tempDir = path.join(os.tmpdir(), "NanoDecompiler", "archives", `${Date.now()}_${base}`);
  fs.mkdirSync(tempDir, { recursive: true });
  return tempDir;
}

/** Распаковка RAR архивов через WebAssembly node-unrar-js */
async function extractRar(
  archivePath: string,
  targetDir: string,
  onProgress: (ev: ArchiveProgressEvent) => void,
): Promise<void> {
  const extractor = await unrar.createExtractorFromFile({
    filepath: archivePath,
    targetPath: targetDir,
  });

  const fileList = extractor.getFileList();
  const fileHeaders = [...fileList.fileHeaders];
  const totalFiles = fileHeaders.length;
  let totalBytes = 0;
  for (const h of fileHeaders) {
    totalBytes += h.unpSize || 0;
  }

  const startTime = Date.now();
  let extractedFiles = 0;
  let extractedBytes = 0;

  const extracted = extractor.extract();
  for (const file of extracted.files) {
    extractedFiles++;
    const unpSize = file.fileHeader?.unpSize || 0;
    extractedBytes += unpSize;
    const fileName = file.fileHeader?.name || `файл ${extractedFiles}`;

    const percent = totalFiles > 0 ? Math.min(99, Math.round((extractedFiles / totalFiles) * 100)) : 50;
    const elapsedSec = (Date.now() - startTime) / 1000;
    const speed = elapsedSec > 0 ? extractedBytes / elapsedSec : 0;
    const remainingRatio = totalFiles > 0 ? (totalFiles - extractedFiles) / extractedFiles : 0;
    const etaSeconds = Math.max(0, Math.ceil(elapsedSec * remainingRatio));

    onProgress({
      archivePath,
      percent,
      currentFile: fileName,
      extractedFiles,
      totalFiles,
      etaSeconds,
      speedBytesPerSec: speed,
      tempDir: targetDir,
    });
  }
}

/** Распаковка .tar и .tar.gz через node tar */
async function extractTar(
  archivePath: string,
  targetDir: string,
  onProgress: (ev: ArchiveProgressEvent) => void,
): Promise<void> {
  const stat = await fs.promises.stat(archivePath);
  const totalBytes = stat.size;
  const startTime = Date.now();
  let extractedFiles = 0;
  let extractedBytes = 0;

  await tar.x({
    file: archivePath,
    cwd: targetDir,
    onentry: (entry) => {
      extractedFiles++;
      extractedBytes += entry.size || 0;
      const elapsedSec = (Date.now() - startTime) / 1000;
      const speed = elapsedSec > 0 ? extractedBytes / elapsedSec : 0;
      const percent = totalBytes > 0 ? Math.min(99, Math.round((extractedBytes / totalBytes) * 100)) : 50;
      const remainingBytes = Math.max(0, totalBytes - extractedBytes);
      const etaSeconds = speed > 0 ? Math.ceil(remainingBytes / speed) : 2;

      onProgress({
        archivePath,
        percent,
        currentFile: entry.path,
        extractedFiles,
        totalFiles: Math.max(extractedFiles + 5, 20),
        etaSeconds,
        speedBytesPerSec: speed,
        tempDir: targetDir,
      });
    },
  });
}

/** Распаковка .7z, .zip или fallback через 7za */
async function extractWith7za(
  sevenZipExe: string,
  archivePath: string,
  targetDir: string,
  onProgress: (ev: ArchiveProgressEvent) => void,
): Promise<void> {
  return new Promise((resolve, reject) => {
    const startTime = Date.now();
    const args = ["x", "-y", "-bsp1", "-o" + targetDir, archivePath];
    const proc = spawn(sevenZipExe, args);

    let lastPercent = 0;
    let currentFile = "Распаковка...";

    const parseLine = (line: string) => {
      // Ищем процент вида: " 45% 12/80 plugins/WorldEdit.jar"
      const m = /(\d{1,3})%\s*(?:(\d+)\/(\d+))?\s*(.*)/.exec(line);
      if (m) {
        const pct = parseInt(m[1], 10);
        if (!isNaN(pct)) lastPercent = pct;
        if (m[4] && m[4].trim()) currentFile = m[4].trim();

        const elapsedSec = (Date.now() - startTime) / 1000;
        let etaSeconds = 3;
        if (lastPercent > 0 && lastPercent < 100) {
          const totalEstimated = elapsedSec / (lastPercent / 100);
          etaSeconds = Math.max(0, Math.ceil(totalEstimated - elapsedSec));
        }

        onProgress({
          archivePath,
          percent: lastPercent,
          currentFile,
          extractedFiles: m[2] ? parseInt(m[2], 10) : 0,
          totalFiles: m[3] ? parseInt(m[3], 10) : 100,
          etaSeconds,
          speedBytesPerSec: 1024 * 1024,
          tempDir: targetDir,
        });
      }
    };

    proc.stdout.on("data", (d) => {
      const text = d.toString("utf-8");
      for (const line of text.split(/\r?\n/)) {
        if (line.trim()) parseLine(line);
      }
    });

    proc.stderr.on("data", (d) => {
      const text = d.toString("utf-8");
      for (const line of text.split(/\r?\n/)) {
        if (line.trim()) parseLine(line);
      }
    });

    proc.on("close", (code) => {
      if (code === 0) resolve();
      else reject(new Error(`7-Zip завершился с кодом ошибки ${code}`));
    });

    proc.on("error", (err) => reject(err));
  });
}

/** Распаковывает архив и сканирует найденные jar-файлы */
export async function extractArchiveAndScanPlugins(
  archivePath: string,
  onProgress: (ev: ArchiveProgressEvent) => void,
): Promise<ArchiveExtractResult> {
  const tempDir = createTempArchiveDir(archivePath);
  const lower = archivePath.toLowerCase();

  try {
    onProgress({
      archivePath,
      percent: 5,
      currentFile: "Инициализация распаковки...",
      extractedFiles: 0,
      totalFiles: 100,
      etaSeconds: 5,
      speedBytesPerSec: 0,
      tempDir,
    });

    if (lower.endsWith(".rar")) {
      await extractRar(archivePath, tempDir, onProgress);
    } else if (lower.endsWith(".tar.gz") || lower.endsWith(".tgz") || lower.endsWith(".tar")) {
      await extractTar(archivePath, tempDir, onProgress);
    } else {
      // .zip, .7z, .7zip
      const sevenPath = get7zaPath();
      if (sevenPath) {
        await extractWith7za(sevenPath, archivePath, tempDir, onProgress);
      } else if (lower.endsWith(".zip")) {
        // Fallback на tar.x или распаковку
        await extractTar(archivePath, tempDir, onProgress);
      } else {
        throw new Error("Не найден модуль 7za для распаковки формата .7z");
      }
    }

    onProgress({
      archivePath,
      percent: 100,
      currentFile: "Поиск плагинов...",
      extractedFiles: 100,
      totalFiles: 100,
      etaSeconds: 0,
      speedBytesPerSec: 0,
      tempDir,
    });

    // Сканируем все найденные .jar файлы
    const jarPaths = await scanJars(tempDir);
    const plugins: DiscoveredPlugin[] = [];
    const serverCores: DiscoveredPlugin[] = [];
    let skippedNonPlugins = 0;

    for (const jp of jarPaths) {
      const info = await inspectJarForPlugin(jp, tempDir);
      if (info.isPlugin) {
        plugins.push(info);
      } else if (info.isServerCore) {
        serverCores.push(info);
      } else {
        skippedNonPlugins++;
      }
    }

    return {
      ok: true,
      archivePath,
      tempDir,
      plugins,
      serverCores,
      skippedNonPlugins,
      totalJarsFound: jarPaths.length,
    };
  } catch (err) {
    return {
      ok: false,
      archivePath,
      tempDir,
      plugins: [],
      serverCores: [],
      skippedNonPlugins: 0,
      totalJarsFound: 0,
      error: (err as Error).message || String(err),
    };
  }
}
