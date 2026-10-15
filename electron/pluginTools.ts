import * as fs from "fs";
import * as path from "path";
import * as zlib from "zlib";
import * as https from "https";
import { execFile } from "child_process";
import { promisify } from "util";

const execFileAsync = promisify(execFile);

type CentralEntry = {
  name: string;
  method: number;
  compSize: number;
  uncompSize: number;
  localOffset: number;
  crc32: number;
};

async function readZipEntries(jarPath: string): Promise<CentralEntry[]> {
  const stat = await fs.promises.stat(jarPath);
  const fileSize = stat.size;
  const fh = await fs.promises.open(jarPath, "r");
  try {
    const maxScan = Math.min(fileSize, 65535 + 22);
    const buf = Buffer.alloc(maxScan);
    await fh.read(buf, 0, maxScan, fileSize - maxScan);

    let eocd = -1;
    for (let i = maxScan - 22; i >= 0; i--) {
      if (buf.readUInt32LE(i) === 0x06054b50) {
        eocd = i;
        break;
      }
    }
    if (eocd < 0) return [];

    const total = buf.readUInt16LE(eocd + 10);
    const cdSize = buf.readUInt32LE(eocd + 12);
    const cdOffset = buf.readUInt32LE(eocd + 16);

    const cdBuf = Buffer.alloc(cdSize);
    await fh.read(cdBuf, 0, cdSize, cdOffset);

    const entries: CentralEntry[] = [];
    let pos = 0;
    while (pos < cdSize && entries.length < total) {
      if (cdBuf.readUInt32LE(pos) !== 0x02014b50) break;
      const method = cdBuf.readUInt16LE(pos + 10);
      const crc32 = cdBuf.readUInt32LE(pos + 16);
      const compSize = cdBuf.readUInt32LE(pos + 20);
      const uncompSize = cdBuf.readUInt32LE(pos + 24);
      const nameLen = cdBuf.readUInt16LE(pos + 28);
      const extraLen = cdBuf.readUInt16LE(pos + 30);
      const commentLen = cdBuf.readUInt16LE(pos + 32);
      const localOffset = cdBuf.readUInt32LE(pos + 42);
      const name = cdBuf.toString("utf-8", pos + 46, pos + 46 + nameLen);

      entries.push({ name, method, compSize, uncompSize, localOffset, crc32 });
      pos += 46 + nameLen + extraLen + commentLen;
    }
    return entries;
  } finally {
    await fh.close();
  }
}

async function readZipEntryData(jarPath: string, entry: CentralEntry): Promise<Buffer> {
  const fh = await fs.promises.open(jarPath, "r");
  try {
    const head = Buffer.alloc(30);
    await fh.read(head, 0, 30, entry.localOffset);
    if (head.readUInt32LE(0) !== 0x04034b50) throw new Error("bad local header");
    const nameLen = head.readUInt16LE(26);
    const extraLen = head.readUInt16LE(28);
    const dataOffset = entry.localOffset + 30 + nameLen + extraLen;

    const compData = Buffer.alloc(entry.compSize);
    await fh.read(compData, 0, entry.compSize, dataOffset);

    if (entry.method === 0) return compData;
    if (entry.method === 8) {
      return await new Promise<Buffer>((resolve, reject) => {
        zlib.inflateRaw(compData, (err, res) => (err ? reject(err) : resolve(res)));
      });
    }
    throw new Error(`unsupported zip method: ${entry.method}`);
  } finally {
    await fh.close();
  }
}

export async function compareJars(jarA: string, jarB: string) {
  try {
    const [entriesA, entriesB] = await Promise.all([readZipEntries(jarA), readZipEntries(jarB)]);

    const mapA = new Map<string, CentralEntry>();
    for (const e of entriesA) {
      if (e.name.endsWith(".class")) mapA.set(e.name, e);
    }

    const mapB = new Map<string, CentralEntry>();
    for (const e of entriesB) {
      if (e.name.endsWith(".class")) mapB.set(e.name, e);
    }

    const added: string[] = [];
    const removed: string[] = [];
    const modified: Array<{ class: string; size_old: number; size_new: number; size_diff: number }> = [];
    let identicalCount = 0;

    for (const [name, entryB] of mapB.entries()) {
      const entryA = mapA.get(name);
      if (!entryA) {
        added.push(name);
      } else {
        if (entryA.crc32 === entryB.crc32 && entryA.uncompSize === entryB.uncompSize) {
          identicalCount++;
        } else {
          modified.push({
            class: name,
            size_old: entryA.uncompSize,
            size_new: entryB.uncompSize,
            size_diff: entryB.uncompSize - entryA.uncompSize,
          });
        }
      }
    }

    for (const name of mapA.keys()) {
      if (!mapB.has(name)) {
        removed.push(name);
      }
    }

    // Check manifest / plugin.yml
    const ymlA = entriesA.find((e) => e.name === "plugin.yml");
    const ymlB = entriesB.find((e) => e.name === "plugin.yml");
    const manifestChanged = (ymlA?.crc32 ?? 0) !== (ymlB?.crc32 ?? 0);

    return {
      ok: true,
      diff: {
        jar_a: path.basename(jarA),
        jar_b: path.basename(jarB),
        total_classes_a: mapA.size,
        total_classes_b: mapB.size,
        added_count: added.length,
        removed_count: removed.length,
        modified_count: modified.length,
        identical_count: identicalCount,
        added: added.sort(),
        removed: removed.sort(),
        modified: modified.sort((a, b) => Math.abs(b.size_diff) - Math.abs(a.size_diff)),
        manifest_changed: manifestChanged,
      },
    };
  } catch (e: any) {
    return { ok: false, error: e?.message || String(e) };
  }
}

export async function getPluginTree(jarPath: string) {
  try {
    const entries = await readZipEntries(jarPath);
    let pluginYmlText = "";
    const pluginYmlEntry = entries.find((e) => e.name === "plugin.yml");
    if (pluginYmlEntry) {
      try {
        const buf = await readZipEntryData(jarPath, pluginYmlEntry);
        pluginYmlText = buf.toString("utf-8");
      } catch {
        // ignore
      }
    }

    const manifest: { name?: string; version?: string; main?: string; api_version?: string; author?: string } = {};
    const commands: Array<{ command: string; description?: string; permission?: string; aliases?: string }> = [];
    const permissions: Array<{ permission: string; description?: string; default?: string }> = [];

    // Parse plugin.yml lines
    if (pluginYmlText) {
      const lines = pluginYmlText.split(/\r?\n/);
      let currentSection: "root" | "commands" | "permissions" = "root";
      let currentItem: any = null;

      for (let line of lines) {
        const raw = line;
        line = line.trimEnd();
        if (!line || line.trim().startsWith("#")) continue;

        const indent = raw.length - raw.trimStart().length;
        const trimmed = raw.trim();

        if (indent === 0) {
          if (trimmed.startsWith("name:")) manifest.name = trimmed.slice(5).trim().replace(/^['"]|['"]$/g, "");
          else if (trimmed.startsWith("version:")) manifest.version = trimmed.slice(8).trim().replace(/^['"]|['"]$/g, "");
          else if (trimmed.startsWith("main:")) manifest.main = trimmed.slice(5).trim().replace(/^['"]|['"]$/g, "");
          else if (trimmed.startsWith("api-version:")) manifest.api_version = trimmed.slice(12).trim().replace(/^['"]|['"]$/g, "");
          else if (trimmed.startsWith("author:")) manifest.author = trimmed.slice(7).trim().replace(/^['"]|['"]$/g, "");
          else if (trimmed.startsWith("commands:")) currentSection = "commands";
          else if (trimmed.startsWith("permissions:")) currentSection = "permissions";
          else currentSection = "root";
          continue;
        }

        if (currentSection === "commands") {
          if (indent === 2 && trimmed.endsWith(":")) {
            const cmdName = trimmed.slice(0, -1).trim();
            currentItem = { command: `/${cmdName}` };
            commands.push(currentItem);
          } else if (currentItem && indent >= 4) {
            if (trimmed.startsWith("description:")) currentItem.description = trimmed.slice(12).trim().replace(/^['"]|['"]$/g, "");
            else if (trimmed.startsWith("permission:")) currentItem.permission = trimmed.slice(11).trim().replace(/^['"]|['"]$/g, "");
            else if (trimmed.startsWith("aliases:")) currentItem.aliases = trimmed.slice(8).trim().replace(/^['"]|['"]$/g, "");
          }
        } else if (currentSection === "permissions") {
          if (indent === 2 && trimmed.endsWith(":")) {
            const permName = trimmed.slice(0, -1).trim();
            currentItem = { permission: permName };
            permissions.push(currentItem);
          } else if (currentItem && indent >= 4) {
            if (trimmed.startsWith("description:")) currentItem.description = trimmed.slice(12).trim().replace(/^['"]|['"]$/g, "");
            else if (trimmed.startsWith("default:")) currentItem.default = trimmed.slice(8).trim().replace(/^['"]|['"]$/g, "");
          }
        }
      }
    }

    // Find listener classes
    const listeners: string[] = [];
    for (const e of entries) {
      if (e.name.endsWith(".class")) {
        const simpleName = path.basename(e.name, ".class");
        if (
          simpleName.toLowerCase().includes("listener") ||
          simpleName.toLowerCase().includes("event") ||
          simpleName.toLowerCase().includes("handler")
        ) {
          listeners.push(e.name.replace(/\.class$/, "").replace(/\//g, "."));
        }
      }
    }

    return {
      ok: true,
      tree: {
        jar: path.basename(jarPath),
        manifest,
        commands,
        permissions,
        listeners: listeners.sort(),
      },
    };
  } catch (e: any) {
    return { ok: false, error: e?.message || String(e) };
  }
}

export async function exportToGitHub(options: {
  outDir: string;
  repoName: string;
  token: string;
  isPrivate: boolean;
}): Promise<{ ok: boolean; url?: string; error?: string }> {
  const { outDir, repoName, token, isPrivate } = options;

  if (!fs.existsSync(outDir)) {
    return { ok: false, error: `Директория ${outDir} не существует` };
  }

  // 1. Create repo on GitHub
  const postData = JSON.stringify({
    name: repoName,
    private: isPrivate,
    auto_init: false,
    description: "Decompiled with NanoDecompiler 2.0.0-alpha",
  });

  try {
    const createRes = await new Promise<{ ok: boolean; status: number; body: string }>((resolve, reject) => {
      const req = https.request(
        "https://api.github.com/user/repos",
        {
          method: "POST",
          headers: {
            "User-Agent": "NanoDecompiler",
            Authorization: `Bearer ${token}`,
            Accept: "application/vnd.github.v3+json",
            "Content-Type": "application/json",
            "Content-Length": Buffer.byteLength(postData),
          },
        },
        (res) => {
          let body = "";
          res.on("data", (chunk) => (body += chunk));
          res.on("end", () => resolve({ ok: (res.statusCode ?? 500) < 300, status: res.statusCode ?? 500, body }));
        }
      );
      req.on("error", reject);
      req.write(postData);
      req.end();
    });

    let repoJson: any = {};
    try {
      repoJson = JSON.parse(createRes.body);
    } catch {
      // ignore
    }

    if (!createRes.ok) {
      const msg = repoJson?.message || `Ошибка создания репозитория HTTP ${createRes.status}`;
      return { ok: false, error: msg };
    }

    const htmlUrl = repoJson.html_url;
    const cloneUrl = repoJson.clone_url;

    if (!cloneUrl) {
      return { ok: false, error: "GitHub не вернул URL репозитория" };
    }

    // Authenticated remote URL
    const authRemote = cloneUrl.replace("https://", `https://${token}@`);

    // 2. Initialize git and push
    await execFileAsync("git", ["init"], { cwd: outDir });
    await execFileAsync("git", ["config", "user.name", "NanoDecompiler"], { cwd: outDir });
    await execFileAsync("git", ["config", "user.email", "nanodecompiler@local"], { cwd: outDir });
    await execFileAsync("git", ["add", "."], { cwd: outDir });

    try {
      await execFileAsync("git", ["commit", "-m", "Decompiled with NanoDecompiler v2.0.0-alpha"], { cwd: outDir });
    } catch {
      // Nothing to commit or already committed
    }

    await execFileAsync("git", ["branch", "-M", "main"], { cwd: outDir });

    try {
      await execFileAsync("git", ["remote", "remove", "origin"], { cwd: outDir });
    } catch {
      // Ignore if origin doesn't exist
    }

    await execFileAsync("git", ["remote", "add", "origin", authRemote], { cwd: outDir });
    await execFileAsync("git", ["push", "-u", "origin", "main", "--force"], { cwd: outDir });

    // Clean remote URL to avoid storing token in .git/config
    try {
      await execFileAsync("git", ["remote", "set-url", "origin", cloneUrl], { cwd: outDir });
    } catch {
      // Ignore
    }

    return { ok: true, url: htmlUrl };
  } catch (err: any) {
    return { ok: false, error: err?.message || String(err) };
  }
}
