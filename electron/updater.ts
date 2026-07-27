import { ipcMain } from "electron";
import * as https from "https";
import * as fs from "fs";
import * as path from "path";

// Подтверждено пользователем: реальный владелец/репозиторий (не заглушка).
const GITHUB_OWNER = "NanoDev1488";
const GITHUB_REPO = "NanoDecompiler";

interface GhAsset {
  name: string;
  browser_download_url: string;
}
interface GhRelease {
  tag_name: string;
  assets: GhAsset[];
}

const REQUEST_TIMEOUT_MS = 8000;

function httpsGetJson<T>(url: string): Promise<T> {
  return new Promise((resolve, reject) => {
    const req = https.get(url, { headers: { "User-Agent": "NanoDecompiler-updater" } }, (res) => {
      if (res.statusCode && res.statusCode >= 300 && res.statusCode < 400 && res.headers.location) {
        resolve(httpsGetJson<T>(res.headers.location));
        return;
      }
      if (res.statusCode !== 200) {
        reject(new Error(`HTTP ${res.statusCode} при запросе ${url}`));
        res.resume();
        return;
      }
      let data = "";
      res.on("data", (chunk) => (data += chunk));
      res.on("end", () => {
        try {
          resolve(JSON.parse(data));
        } catch (e) {
          reject(e);
        }
      });
    });
    req.on("error", reject);
    req.setTimeout(REQUEST_TIMEOUT_MS, () => req.destroy(new Error("Таймаут запроса к GitHub")));
  });
}

function httpsDownloadFile(url: string, destPath: string): Promise<void> {
  return new Promise((resolve, reject) => {
    const req = https.get(url, { headers: { "User-Agent": "NanoDecompiler-updater" } }, (res) => {
      if (res.statusCode && res.statusCode >= 300 && res.statusCode < 400 && res.headers.location) {
        httpsDownloadFile(res.headers.location, destPath).then(resolve, reject);
        return;
      }
      if (res.statusCode !== 200) {
        reject(new Error(`HTTP ${res.statusCode} при скачивании ${url}`));
        res.resume();
        return;
      }
      const tmpPath = destPath + ".download";
      const file = fs.createWriteStream(tmpPath);
      res.pipe(file);
      file.on("finish", () => {
        file.close(() => {
          // Атомарная замена - переименование внутри одной файловой системы
          // почти мгновенное, не оставляет "битого" файла на середине,
          // если что-то пойдёт не так ДО этого момента.
          fs.renameSync(tmpPath, destPath);
          resolve();
        });
      });
      file.on("error", (err) => {
        fs.unlink(tmpPath, () => {});
        reject(err);
      });
    });
    req.on("error", reject);
    req.setTimeout(REQUEST_TIMEOUT_MS, () => req.destroy(new Error("Таймаут скачивания")));
  });
}

// См. HANDOFF_7/8 - раньше версия движка была ЗАШИТА константой в код
// (ENGINE_VERSION = "v1.0") и сравнивалась с тегом релиза из GitHub API
// (release.tag_name). ЭТО БЫЛО СЛОМАНО: workflow публикует ОДИН общий тег
// на клиент+движок (RELEASE_TAG, сейчас "v1.2"), а движок сам по себе
// версионируется отдельно (API_VERSION, сейчас "v1.0") - эти два числа
// заведомо разные по конструкции и никогда бы не совпали, поэтому
// "обновление доступно" показывалось ВСЕГДА, даже сразу после успешного
// обновления (константа в коде не менялась от одного apply к другому).
//
// Фикс: сравниваем тег с тегом (яблоки с яблоками) - какой релиз СЕЙЧАС
// установлен, храним в маленьком текстовом файле рядом с самим exe
// движка (обновляется после каждого успешного apply), а не в константе
// в коде. Если файла нет (первый запуск после установки клиента, который
// эту версию апдейтера ещё не знает) - считаем версию "неизвестной", это
// покажет "доступно обновление" один-единственный раз до первого apply,
// дальше ведёт себя корректно.
function versionMarkerPath(engineDir: string): string {
  return path.join(engineDir, ".engine-release-tag");
}

function readInstalledTag(engineDir: string): string | null {
  try {
    return fs.readFileSync(versionMarkerPath(engineDir), "utf-8").trim() || null;
  } catch {
    return null;
  }
}

function writeInstalledTag(engineDir: string, tag: string): void {
  fs.writeFileSync(versionMarkerPath(engineDir), tag, "utf-8");
}

export function registerUpdateHandlers(engineDir: () => string) {
  ipcMain.handle("update:check", async () => {
    try {
      const release = await httpsGetJson<GhRelease>(
        `https://api.github.com/repos/${GITHUB_OWNER}/${GITHUB_REPO}/releases/latest`
      );
      const latestTag = release.tag_name;
      const installedTag = readInstalledTag(engineDir());
      const hasUpdate = installedTag !== latestTag;
      const asset = release.assets.find((a) => a.name === "NanoDecompilerCLI.exe");
      return {
        ok: true,
        currentVersion: installedTag ?? "неизвестна (движок ещё не обновлялся этой версией клиента)",
        latestVersion: latestTag,
        hasUpdate,
        downloadUrl: asset ? asset.browser_download_url : null,
      };
    } catch (e) {
      return { ok: false, error: String(e) };
    }
  });

  ipcMain.handle("update:apply", async (_e, downloadUrl: string, latestTag?: string) => {
    try {
      const dir = engineDir();
      const dest = path.join(dir, "NanoDecompilerCLI.exe");
      // ВАЖНО: движок вызывается ТОЛЬКО как дочерний процесс (spawn) - см.
      // electron/main.ts::run:decompile/tools:install - между вызовами
      // файл никем не заблокирован (Windows не даёт перезаписать EXE,
      // который прямо сейчас исполняется, но между запросами пользователя
      // это не так). Если apply вызван ПОКА идёт декомпиляция - перезапись
      // может упасть с EBUSY/EPERM - вызывающая сторона (App.tsx) должна
      // дождаться завершения текущей операции перед вызовом apply (уже
      // сделано - кнопка "Обновить" задизейблена, пока status === "running").
      await httpsDownloadFile(downloadUrl, dest);
      if (latestTag) {
        writeInstalledTag(dir, latestTag);
      }
      return { ok: true };
    } catch (e) {
      return { ok: false, error: String(e) };
    }
  });
}
