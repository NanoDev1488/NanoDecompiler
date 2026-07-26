import { app, ipcMain } from "electron";
import * as https from "https";
import * as fs from "fs";
import * as path from "path";

// Меняешь на реальный владелец/репозиторий, куда публикуются релизы (см.
// .github/workflows/build-and-release.yml - тот же тег v1.2/v1.0, что
// используется там).
const GITHUB_OWNER = "NanoDev1488";
const GITHUB_REPO = "NanoDecompiler";

// Версия ДВИЖКА (не клиента - см. HANDOFF, у них разный жизненный цикл).
// Клиент как целое обновляется через свой обычный установщик (NSIS) -
// апдейтер ниже только для движка (NanoDecompilerAPI.exe), т.к. именно
// он - тот самый "тяжёлый груз" (~10 МБ), который не хочется тащить
// заново переустановкой ~150 МБ клиента целиком при каждом мелком фиксе
// движка.
export const ENGINE_VERSION = "v1.0";

interface GhAsset {
  name: string;
  browser_download_url: string;
}
interface GhRelease {
  tag_name: string;
  assets: GhAsset[];
}

function httpsGetJson<T>(url: string): Promise<T> {
  return new Promise((resolve, reject) => {
    https.get(url, { headers: { "User-Agent": "NanoDecompiler-updater" } }, (res) => {
      if (res.statusCode && res.statusCode >= 300 && res.statusCode < 400 && res.headers.location) {
        resolve(httpsGetJson<T>(res.headers.location));
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
    }).on("error", reject);
  });
}

function httpsDownloadFile(url: string, destPath: string): Promise<void> {
  return new Promise((resolve, reject) => {
    https.get(url, { headers: { "User-Agent": "NanoDecompiler-updater" } }, (res) => {
      if (res.statusCode && res.statusCode >= 300 && res.statusCode < 400 && res.headers.location) {
        httpsDownloadFile(res.headers.location, destPath).then(resolve, reject);
        return;
      }
      if (res.statusCode !== 200) {
        reject(new Error(`HTTP ${res.statusCode} при скачивании ${url}`));
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
    }).on("error", reject);
  });
}

export function registerUpdateHandlers(engineDir: () => string) {
  ipcMain.handle("update:check", async () => {
    try {
      const release = await httpsGetJson<GhRelease>(
        `https://api.github.com/repos/${GITHUB_OWNER}/${GITHUB_REPO}/releases/latest`
      );
      const latestTag = release.tag_name; // ожидаем что-то вроде "v1.0" (движок) - если релиз объединённый (v1.2, см. HANDOFF), тут может понадобиться отдельный тег специально под движок
      const hasUpdate = latestTag !== ENGINE_VERSION;
      const asset = release.assets.find((a) => a.name === "NanoDecompilerAPI.exe");
      return {
        ok: true,
        currentVersion: ENGINE_VERSION,
        latestVersion: latestTag,
        hasUpdate,
        downloadUrl: asset ? asset.browser_download_url : null,
      };
    } catch (e) {
      return { ok: false, error: String(e) };
    }
  });

  ipcMain.handle("update:apply", async (_e, downloadUrl: string) => {
    try {
      const dest = path.join(engineDir(), "NanoDecompilerAPI.exe");
      // ВАЖНО: движок вызывается ТОЛЬКО как дочерний процесс (spawn) - см.
      // electron/main.ts::run:decompile/tools:install - между вызовами
      // файл никем не заблокирован (Windows не даёт перезаписать EXE,
      // который прямо сейчас исполняется, но между запросами пользователя
      // это не так). Если apply вызван ПОКА идёт декомпиляция - перезапись
      // может упасть с EBUSY/EPERM - вызывающая сторона (App.tsx) должна
      // дождаться завершения текущей операции перед вызовом apply.
      await httpsDownloadFile(downloadUrl, dest);
      return { ok: true };
    } catch (e) {
      return { ok: false, error: String(e) };
    }
  });
}
