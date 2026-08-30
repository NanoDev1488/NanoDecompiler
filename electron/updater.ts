import { app, ipcMain, shell } from "electron";
import * as https from "https";
import * as fs from "fs";
import * as path from "path";
import * as crypto from "crypto";
import { spawn } from "child_process";

// Подтверждено пользователем: реальный владелец/репозиторий (не заглушка).
const GITHUB_OWNER = "NanoDev1488";
const GITHUB_REPO = "NanoDecompiler";

interface GhAsset {
  name: string;
  browser_download_url: string;
}
interface GhRelease {
  tag_name: string;
  html_url: string;
  assets: GhAsset[];
}
interface VersionsJson {
  client: string;
  api: string;
}
// checksums.json (см. build-and-release.yml) - плоская мапа
// "имя-файла-в-релизе" -> "sha256 hex". Ключи - буквально то же имя, что
// у release-ассета (напр. "NanoDecompilerClApi-windows.exe").
type ChecksumsJson = Record<string, string>;

// SHA256 локального файла - для сверки с checksums.json ДО скачивания
// (не качать заново, если содержимое не изменилось) и ПОСЛЕ (защита от
// оборванного/битого скачивания - см. update:apply ниже).
function sha256File(filePath: string): Promise<string | null> {
  return new Promise((resolve) => {
    try {
      const hash = crypto.createHash("sha256");
      const stream = fs.createReadStream(filePath);
      stream.on("data", (chunk) => hash.update(chunk));
      stream.on("end", () => resolve(hash.digest("hex")));
      stream.on("error", () => resolve(null));
    } catch {
      resolve(null);
    }
  });
}

// БАГ-ФИКС: раньше здесь искался ассет "NanoDecompilerCLI.exe" - имя из
// СТАРОЙ схемы релиза (единственный build-api job, только Windows). После
// объединения CLI+API в "ClApi" и разбивки на 3 платформенных job'а (см.
// .github/workflows/build-and-release.yml) ассеты релиза теперь называются
// NanoDecompilerClApi-windows.exe / -linux / -macos - апдейтер тихо
// переставал находить движок в новых релизах, откатываясь на "обновлять
// нечего". Заодно тихий патч движка на лету (updateKind "engine") теперь
// доступен на всех трёх ОС, а не только Windows - раньше это было
// осознанным ограничением ИЗ-ЗА того, что build-api был Windows-only,
// но эта причина больше не существует.
const ENGINE_ASSET_NAME: string =
  process.platform === "win32"
    ? "NanoDecompilerClApi-windows.exe"
    : process.platform === "darwin"
      ? "NanoDecompilerClApi-macos"
      : "NanoDecompilerClApi-linux";

// Регэксп имени клиентского инсталлятора - см. package.json build.*.artifactName
// по умолчанию (electron-builder) и .github/workflows/build-and-release.yml
// (там же явно копируется под фиксированное имя перед публикацией).
function clientAssetPattern(): RegExp {
  if (process.platform === "win32") return /Setup\.exe$/i;
  if (process.platform === "darwin") return /\.dmg$/i;
  return /\.AppImage$/i;
}

// См. HANDOFF_16/19 - пользователь просил именно 5 сек для ПРОВЕРКИ
// (лёгкие JSON-запросы к api.github.com) - для СКАЧИВАНИЯ файлов (exe/
// инсталлятор, могут быть десятки МБ) отдельный, более щедрый таймаут -
// 5 сек для реальной закачки было бы слишком мало и рвало бы её на
// медленном интернете.
const CHECK_TIMEOUT_MS = 5000;
const DOWNLOAD_TIMEOUT_MS = 30000;

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
    req.setTimeout(CHECK_TIMEOUT_MS, () => req.destroy(new Error("Таймаут запроса к GitHub")));
  });
}

function httpsDownloadFile(
  url: string,
  destPath: string,
  onProgress?: (downloaded: number, total: number | null) => void
): Promise<void> {
  return new Promise((resolve, reject) => {
    const req = https.get(url, { headers: { "User-Agent": "NanoDecompiler-updater" } }, (res) => {
      if (res.statusCode && res.statusCode >= 300 && res.statusCode < 400 && res.headers.location) {
        httpsDownloadFile(res.headers.location, destPath, onProgress).then(resolve, reject);
        return;
      }
      if (res.statusCode !== 200) {
        reject(new Error(`HTTP ${res.statusCode} при скачивании ${url}`));
        res.resume();
        return;
      }
      const totalHeader = res.headers["content-length"];
      const total = totalHeader ? parseInt(Array.isArray(totalHeader) ? totalHeader[0] : totalHeader, 10) : null;
      let downloaded = 0;
      // Троттлинг - см. HANDOFF_22: если слать прогресс на КАЖДЫЙ chunk
      // (могут прилетать десятки раз в секунду на быстром интернете),
      // получаем ту же болезнь, что и с логом декомпиляции - шквал IPC +
      // рендеров вместо плавного счётчика. Раз в ~120мс более чем
      // достаточно для человеческого глаза (это уже ~8 обновлений/сек).
      let lastEmit = 0;
      if (onProgress) onProgress(0, total);
      res.on("data", (chunk: Buffer) => {
        downloaded += chunk.length;
        const now = Date.now();
        if (onProgress && now - lastEmit >= 120) {
          lastEmit = now;
          onProgress(downloaded, total);
        }
      });
      const tmpPath = destPath + ".download";
      const file = fs.createWriteStream(tmpPath);
      res.pipe(file);
      file.on("finish", () => {
        file.close(() => {
          if (onProgress) onProgress(downloaded, total);
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
    req.setTimeout(DOWNLOAD_TIMEOUT_MS, () => req.destroy(new Error("Таймаут скачивания")));
  });
}

// См. HANDOFF_7/8/16 - версия ДВИЖКА (api) хранится в маленьком текстовом
// файле рядом с exe (обновляется после каждого успешного apply), а не в
// константе кода - см. предыдущие аддендумы про то, почему сравнение тегов
// "в лоб" было сломано. Версия КЛИЕНТА никакого отдельного файла не
// требует - её всегда точно знает сам Electron через app.getVersion()
// (читает package.json "version", который electron-builder зашивает в
// собранный .exe/инсталлятор на этапе сборки) - именно она "живёт" в
// файловой системе как факт того, какой инсталлятор реально стоит.
function apiVersionMarkerPath(engineDir: string): string {
  return path.join(engineDir, ".engine-api-version");
}

function writeInstalledApiVersion(engineDir: string, version: string): void {
  fs.writeFileSync(apiVersionMarkerPath(engineDir), version, "utf-8");
}

// БАГ-ФИКС: раньше версия движка бралась ТОЛЬКО из файла-маркера
// (.engine-api-version), который писался ИСКЛЮЧИТЕЛЬНО после успешного
// update:apply - на свежей установке (маркер никогда не создавался)
// installedApiVersion был всегда null -> apiNeedsUpdate всегда true ->
// апдейтер ВЕЧНО показывал "доступно обновление движка", даже когда
// встроенный в свежий инсталлятор движок уже последней версии. Теперь
// реально спрашиваем сам бинарник через --version (см. cli_main.cpp) - и
// только на случай, если сам бинарник почему-то не смог ответить (не
// найден и т.п.), откатываемся на маркер-файл как на кэш последнего
// известного значения.
function extractVersionNumber(raw: string): string | null {
  // "NanoDecompiler v1.6.2 BETA" -> "1.6.2" (версия внутри произвольного
  // текста - число из максимум 4 точечных сегментов, первое совпадение).
  const m = raw.match(/(\d+(?:\.\d+){1,3})/);
  return m ? m[1] : null;
}

function resolveInstalledApiVersion(
  engineDir: string,
  engineInvocation: (scriptArgs: string[]) => { cmd: string; args: string[] },
): Promise<string | null> {
  return new Promise((resolve) => {
    let cmd: string, args: string[];
    try {
      ({ cmd, args } = engineInvocation(["--version"]));
    } catch {
      resolve(readMarkerFallback(engineDir));
      return;
    }
    let out = "";
    let settled = false;
    const finish = (v: string | null) => {
      if (settled) return;
      settled = true;
      resolve(v ?? readMarkerFallback(engineDir));
    };
    try {
      const proc = spawn(cmd, args, { env: { ...process.env } });
      proc.stdout.on("data", (d) => (out += d.toString("utf-8")));
      proc.on("close", () => {
        try {
          const parsed = JSON.parse(out.trim());
          const num = parsed.version ? extractVersionNumber(String(parsed.version)) : null;
          if (num) writeInstalledApiVersion(engineDir, num);
          finish(num);
        } catch {
          finish(null);
        }
      });
      proc.on("error", () => finish(null));
    } catch {
      finish(null);
    }
  });
}

function readMarkerFallback(engineDir: string): string | null {
  try {
    return fs.readFileSync(apiVersionMarkerPath(engineDir), "utf-8").trim() || null;
  } catch {
    return null;
  }
}

// "v1.2" / "1.2" / "1.2.0" - в разных местах версия приходит в разном
// формате (package.json без "v", релизный тег с "v", API_VERSION как
// договорятся) - сравниваем по числовым компонентам, а не строкой 1:1.
function versionParts(v: string): number[] {
  return v
    .replace(/^v/i, "")
    .split(".")
    .map((p) => parseInt(p, 10) || 0);
}

function versionsEqual(a: string, b: string): boolean {
  const pa = versionParts(a);
  const pb = versionParts(b);
  const len = Math.max(pa.length, pb.length);
  for (let i = 0; i < len; i++) {
    if ((pa[i] ?? 0) !== (pb[i] ?? 0)) return false;
  }
  return true;
}

// Соглашение о номерах версий проекта (см. HANDOFF_22): релиз - РОВНО два
// числа через точку (1.4, 1.5, 1.6...), без хвостовых нулей на конце.
// Пререлиз/бета - три и больше чисел (1.4.1, 1.4.2, ...) - это
// промежуточные тестовые сборки на пути к следующему релизу. Используется
// при проверке обновлений, чтобы явно сказать пользователю "это бета,
// осторожнее" или "это релиз, можно спокойно пользоваться".
function classifyVersion(v: string): "release" | "prerelease" {
  return versionParts(v).length >= 3 ? "prerelease" : "release";
}

// См. HANDOFF_16 - после "важного" (client) обновления новый инсталлятор
// запускается ОТДЕЛЬНЫМ процессом (detached, переживает закрытие текущего
// приложения), а ТЕКУЩИЙ клиент сам закрывается - Windows не даёт
// перезаписать свой же запущенный .exe. NSIS-инсталлятор electron-builder
// по умолчанию сам предлагает "запустить приложение" по завершении
// (чекбокс включён по умолчанию) - но на случай, если пользователь его
// снимет или что-то пойдёт не так, любой следующий запуск приложения
// (хоть вручную, хоть через сам инсталлятор) проверяет этот файл-маркер
// и показывает плашку "Обновление успешно установлено" - маркер надёжнее
// пробрасывания CLI-флага через инсталлятор (который его сам не передаёт).
function updateSuccessMarkerPath(): string {
  return path.join(app.getPath("userData"), ".pending-update-success");
}

export function writeUpdateSuccessMarker(): void {
  try {
    fs.writeFileSync(updateSuccessMarkerPath(), "1", "utf-8");
  } catch {
    // не критично - в худшем случае просто не покажем плашку один раз
  }
}

// Тот же результат достижим и явным флагом `--add-update-success` (см.
// запрос пользователя) - на случай ручного/скриптового вызова, не только
// через маркер-файл.
export function consumeUpdateSuccessFlag(): boolean {
  const viaFlag = process.argv.includes("--add-update-success");
  let viaMarker = false;
  try {
    viaMarker = fs.existsSync(updateSuccessMarkerPath());
    if (viaMarker) fs.unlinkSync(updateSuccessMarkerPath());
  } catch {
    // игнорируем - не критично
  }
  return viaFlag || viaMarker;
}

export function registerUpdateHandlers(
  engineDir: () => string,
  engineInvocation: (scriptArgs: string[]) => { cmd: string; args: string[] },
) {
  ipcMain.handle("update:consumeSuccessFlag", async () => consumeUpdateSuccessFlag());

  ipcMain.handle("update:installClientAndRestart", async (event, downloadUrl: string) => {
    // HANDOFF_51: тихая "скачать инсталлятор -> запустить его -> выйти,
    // инсталлятор сам перезапустит приложение" схема - специфична для
    // Windows NSIS (silent-install + автозапуск по завершении, см. комментарий
    // ниже). Для macOS (.dmg - нужно смонтировать образ и вручную
    // перетащить .app, автоматизировать без доп. библиотек - отдельная
    // большая задача) и Linux (.AppImage - НЕТ единого "инсталлятора" в
    // принципе, самообновление AppImage обычно означает подмену файла на
    // диске и требует знать, ГДЕ пользователь его хранит - не гарантировано)
    // - вместо притворства, что тут работает то же самое, ЧЕСТНО открываем
    // страницу скачивания в браузере и просим пользователя обновиться
    // вручную. НЕ проверялось живьём ни на одной из трёх платформ.
    if (process.platform !== "win32") {
      try {
        await shell.openExternal(downloadUrl);
        return { ok: true, manual: true };
      } catch (e) {
        return { ok: false, error: String(e) };
      }
    }
    try {
      const tmpDir = app.getPath("temp");
      const installerPath = path.join(tmpDir, "NanoDecompiler-Client-Setup.exe");
      await httpsDownloadFile(downloadUrl, installerPath, (downloaded, total) => {
        event.sender.send("update:downloadProgress", { downloaded, total, kind: "client" });
      });
      writeUpdateSuccessMarker();
      // detached + unref - инсталлятор должен пережить закрытие текущего
      // процесса (не быть его child'ом с точки зрения жизненного цикла).
      // spawn уже статически импортирован в шапке файла - раньше здесь был
      // отдельный динамический import(), излишний и лишь маскировавший
      // обычный статический импорт тем же именем.
      const child = spawn(installerPath, [], { detached: true, stdio: "ignore" });
      child.unref();
      // Небольшая задержка перед выходом - даём инсталлятору реально
      // стартовать (открыть свой файл) ДО того, как текущий .exe
      // попытается завершиться (иначе на медленной машине можно словить
      // гонку, где Windows ещё не успела дать installerPath файловый
      // хэндл на чтение).
      setTimeout(() => app.quit(), 700);
      return { ok: true };
    } catch (e) {
      return { ok: false, error: String(e) };
    }
  });

  ipcMain.handle("update:check", async () => {
    try {
      const release = await httpsGetJson<GhRelease>(
        `https://api.github.com/repos/${GITHUB_OWNER}/${GITHUB_REPO}/releases/latest`
      );
      const versionsAsset = release.assets.find((a) => a.name === "versions.json");
      const checksumsAsset = release.assets.find((a) => a.name === "checksums.json");
      const cliAsset = ENGINE_ASSET_NAME ? release.assets.find((a) => a.name === ENGINE_ASSET_NAME) : undefined;
      const setupAsset = release.assets.find((a) => clientAssetPattern().test(a.name));

      const currentClientVersion = app.getVersion();
      const installedApiVersion = await resolveInstalledApiVersion(engineDir(), engineInvocation);

      // По просьбе пользователя: сверяем SHA256 движка с checksums.json
      // релиза ДО того, как вообще предлагать обновление - если бинарник
      // на диске побайтово совпадает с тем, что в релизе (даже если
      // версионная строка почему-то разошлась - редкий, но возможный
      // случай), апдейт движка не предлагаем вообще, нечего качать.
      let engineHashMatches = false;
      if (checksumsAsset && cliAsset) {
        try {
          const checksums = await httpsGetJson<ChecksumsJson>(checksumsAsset.browser_download_url);
          const expectedHash = checksums[cliAsset.name];
          const localBin = path.join(engineDir(), ENGINE_ASSET_NAME ?? "NanoDecompilerCLI");
          const localHash = expectedHash ? await sha256File(localBin) : null;
          engineHashMatches = !!expectedHash && !!localHash && expectedHash === localHash;
        } catch {
          // checksums.json недоступен/битый - не блокируем обновление
          // из-за этого, просто не получаем доп. проверку.
        }
      }

      if (!versionsAsset) {
        // Старый релиз без versions.json (см. HANDOFF_16) - не можем
        // различить тип обновления, откатываемся на грубое "обычное"
        // поведение по тегу целиком, лишь бы не соврать про "всё ок".
        return {
          ok: true,
          updateKind: release.tag_name === installedApiVersion ? "none" : "engine",
          currentVersion: installedApiVersion ?? "неизвестна",
          latestVersion: release.tag_name,
          latestVersionKind: classifyVersion(release.tag_name),
          downloadUrl: cliAsset ? cliAsset.browser_download_url : null,
          clientDownloadUrl: null,
          releaseUrl: release.html_url,
        };
      }

      const versions = await httpsGetJson<VersionsJson>(versionsAsset.browser_download_url);
      const clientNeedsUpdate = !versionsEqual(currentClientVersion, versions.client);
      const apiNeedsUpdate = installedApiVersion === null || !versionsEqual(installedApiVersion, versions.api);

      // Клиент важнее движка: если поменялось само GUI-приложение, апдейт
      // движка (даже если он ТОЖЕ поменялся) неважен сам по себе - новый
      // инсталлятор клиента и так принесёт свежий движок внутри себя (см.
      // build-client в workflow - он теперь сам собирает и встраивает
      // движок).
      //
      // "engine"-обновление (тихий патч без переустановки) теперь доступно
      // на всех трёх ОС (см. ENGINE_ASSET_NAME выше - раньше было только
      // на Windows, пока build-api был Windows-only job'ом).
      let updateKind: "none" | "engine" | "client" = "none";
      if (clientNeedsUpdate) updateKind = "client";
      else if (apiNeedsUpdate && cliAsset && !engineHashMatches) updateKind = "engine";

      return {
        ok: true,
        updateKind,
        currentVersion: currentClientVersion,
        latestVersion: updateKind === "client" ? versions.client : versions.api,
        latestVersionKind: classifyVersion(updateKind === "client" ? versions.client : versions.api),
        downloadUrl: cliAsset ? cliAsset.browser_download_url : null,
        clientDownloadUrl: setupAsset ? setupAsset.browser_download_url : release.html_url,
        releaseUrl: release.html_url,
      };
    } catch (e) {
      return { ok: false, error: String(e) };
    }
  });

  ipcMain.handle("update:apply", async (event, downloadUrl: string, latestApiVersion?: string) => {
    // ВАЖНО: apply умеет подменять ТОЛЬКО движок (exe) - см. updateKind
    // выше. Обновление самого клиента "апдейтом" в этом же смысле в
    // принципе невозможно - Windows не даёт процессу перезаписать
    // собственный запущенный .exe/удалить свою же папку в Program Files;
    // для клиента showUpdateKind "client" в App.tsx ведёт на скачивание
    // НОВОГО инсталлятора через shell.openExternal, а не сюда.
    try {
      const dir = engineDir();
      const dest = path.join(dir, ENGINE_ASSET_NAME ?? "NanoDecompilerCLI");
      // БАГ-ФИКС/фича: скачиваем во ВРЕМЕННЫЙ файл, а не сразу поверх
      // рабочего движка - если скачивание оборвётся/повредится (см.
      // "движок завершился с кодом N без вывода - возможно, файл повреждён
      // при скачивании" в main.ts), рабочая копия остаётся нетронутой.
      // После скачивания сверяем SHA256 с checksums.json релиза - только
      // при совпадении делаем rename поверх рабочего файла (атомарно на
      // одной файловой системе).
      const tmpDest = dest + ".download";
      await httpsDownloadFile(downloadUrl, tmpDest, (downloaded, total) => {
        event.sender.send("update:downloadProgress", { downloaded, total, kind: "engine" });
      });

      try {
        const release = await httpsGetJson<GhRelease>(
          `https://api.github.com/repos/${GITHUB_OWNER}/${GITHUB_REPO}/releases/latest`
        );
        const checksumsAsset = release.assets.find((a) => a.name === "checksums.json");
        const assetName = ENGINE_ASSET_NAME ?? path.basename(dest);
        if (checksumsAsset) {
          const checksums = await httpsGetJson<ChecksumsJson>(checksumsAsset.browser_download_url);
          const expected = checksums[assetName];
          const actual = await sha256File(tmpDest);
          if (expected && actual && expected !== actual) {
            fs.unlinkSync(tmpDest);
            return { ok: false, error: "скачанный файл повреждён (SHA256 не совпадает) - попробуйте ещё раз" };
          }
        }
      } catch {
        // checksums.json недоступен - не блокируем обновление из-за этого
        // (лучше применить непроверенное обновление, чем не обновиться
        // вовсе, раз основной httpsDownloadFile уже завершился без ошибки).
      }

      fs.renameSync(tmpDest, dest);
      if (process.platform !== "win32") {
        try {
          fs.chmodSync(dest, 0o755);
        } catch {
          // не критично - если бинарник уже был +x, rename сохраняет права
        }
      }
      if (latestApiVersion) {
        writeInstalledApiVersion(dir, latestApiVersion);
      }
      return { ok: true };
    } catch (e) {
      return { ok: false, error: String(e) };
    }
  });
}
