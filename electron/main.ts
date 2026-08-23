// Главный процесс Electron.
//
// HANDOFF_46: движок декомпиляции ПЕРЕПИСАН на C++ (resources/engine_cpp) -
// это ЕДИНСТВЕННЫЙ способ его запустить, python-фолбэка больше нет (main.py
// и весь остальной python-код удалены из проекта). cli_main.cpp - полная
// совместимая замена main.py по флагам/выводу (см. cli_main.cpp шапку).
// Для истории архитектурного решения "почему GUI переписан отдельно от
// движка" - см. HANDOFF_1_ARCHITECTURE.md (было верно, когда движок ещё был
// на Python; сейчас GUI по-прежнему остаётся отдельным TypeScript-слоем
// поверх движка, спавнящим его как дочерний процесс - изменился только сам
// движок, не сама архитектура "GUI отдельно, движок отдельно").
import { app, BrowserWindow, ipcMain, dialog, shell } from "electron";
import { spawn, ChildProcessWithoutNullStreams } from "child_process";
import * as path from "path";
import * as fs from "fs";
import { registerUpdateHandlers } from "./updater";
import { readJarSummaryNative } from "./jarSummary";

let mainWindow: BrowserWindow | null = null;
let runningProc: ChildProcessWithoutNullStreams | null = null;

const isDev = !app.isPackaged;

// Настройки (см. HANDOFF_19 - экран настроек, раньше не было вообще).
// Простой JSON-файл в userData - для двух булевых тумблеров полноценная
// зависимость (electron-store и т.п.) избыточна, а свой формат/схему
// версионировать/мигрировать тут пока не нужно (если настроек станет
// много - тогда и стоит пересмотреть).
type Settings = {
  legitimacyCheck: boolean;
  autoUpdateCheck: boolean;
};
const DEFAULT_SETTINGS: Settings = { legitimacyCheck: true, autoUpdateCheck: true };

function settingsPath(): string {
  return path.join(app.getPath("userData"), "settings.json");
}

function loadSettings(): Settings {
  try {
    const raw = fs.readFileSync(settingsPath(), "utf-8");
    const parsed = JSON.parse(raw);
    // Разбираем поле за полем (не просто {...DEFAULT, ...parsed}) - на
    // случай если в файле окажется мусор/чужой тип по конкретному ключу
    // (напр. кто-то руками отредактировал файл) - тогда берём дефолт
    // именно для ЭТОГО ключа, а не роняем все настройки разом.
    return {
      legitimacyCheck: typeof parsed.legitimacyCheck === "boolean" ? parsed.legitimacyCheck : DEFAULT_SETTINGS.legitimacyCheck,
      autoUpdateCheck: typeof parsed.autoUpdateCheck === "boolean" ? parsed.autoUpdateCheck : DEFAULT_SETTINGS.autoUpdateCheck,
    };
  } catch {
    return { ...DEFAULT_SETTINGS };
  }
}

function saveSettings(s: Settings): void {
  fs.writeFileSync(settingsPath(), JSON.stringify(s, null, 2), "utf-8");
}

function engineDir(): string {
  return isDev
    ? path.join(__dirname, "..", "resources", "engine")
    : path.join(process.resourcesPath, "engine");
}

// HANDOFF_46: python-фолбэк убран полностью (main.py удалён из проекта -
// движок теперь ТОЛЬКО C++, resources/engine_cpp/src/cli_main.cpp).
// Раньше (HANDOFF_7/8/9/45) здесь был путь через python3/python как
// основной или запасной вариант - см. историю в этих хэндоффах, если
// понадобится контекст. Если бинарника рядом нет (например, CI ещё не
// пересобрал extraResources/engine после этого коммита) - функция бросает
// понятную ошибку, а НЕ падает молча/не пытается угадать питон.
function engineInvocation(scriptArgs: string[]): { cmd: string; args: string[] } {
  const binName = process.platform === "win32" ? "NanoDecompilerCLI.exe" : "NanoDecompilerCLI";
  const binPath = path.join(engineDir(), binName);
  if (!fs.existsSync(binPath)) {
    throw new Error(
      `Движок не найден: ${binPath}. Убедитесь, что resources/engine_cpp собран (см. ` +
        `resources/engine_cpp/CMakeLists.txt) и бинарник NanoDecompilerCLI${
          process.platform === "win32" ? ".exe" : ""
        } скопирован в resources/engine/ (см. .github/workflows/build-and-release.yml).`,
    );
  }
  // Бинарник уже "содержит в себе" весь движок - путь к скрипту не
  // передаём, только сами позиционные аргументы/флаги.
  return { cmd: binPath, args: scriptArgs };
}

function createWindow() {
  mainWindow = new BrowserWindow({
    width: 1180,
    height: 760,
    minWidth: 860,
    minHeight: 560,
    backgroundColor: "#0a0d0b", // --surface из styles.css (терминал+MD3 слияние)
    autoHideMenuBar: true,
    webPreferences: {
      preload: path.join(__dirname, "preload.js"),
      contextIsolation: true,
      nodeIntegration: false,
    },
  });

  if (isDev && process.env.VITE_DEV_SERVER_URL) {
    mainWindow.loadURL(process.env.VITE_DEV_SERVER_URL);
  } else {
    mainWindow.loadFile(path.join(__dirname, "..", "dist", "index.html"));
  }
}

app.whenReady().then(() => {
  registerUpdateHandlers(engineDir);
  createWindow();
});

app.on("window-all-closed", () => {
  if (process.platform !== "darwin") app.quit();
});

ipcMain.handle("settings:get", async (): Promise<Settings> => loadSettings());

ipcMain.handle("settings:set", async (_e, partial: Partial<Settings>): Promise<Settings> => {
  const merged = { ...loadSettings(), ...partial };
  saveSettings(merged);
  return merged;
});

ipcMain.handle("dialog:selectJar", async () => {
  const res = await dialog.showOpenDialog(mainWindow!, {
    title: "Выбери .jar плагина",
    properties: ["openFile"],
    filters: [{ name: "Java Archive", extensions: ["jar"] }],
  });
  if (res.canceled || res.filePaths.length === 0) return null;
  return res.filePaths[0];
});

ipcMain.handle("dialog:selectOutDir", async (_e, defaultPath?: string) => {
  const res = await dialog.showOpenDialog(mainWindow!, {
    title: "Папка для результата",
    // defaultPath может указывать на ЕЩЁ НЕ существующую папку (напр.
    // ".../MyPlugin_decompiled") - Windows-диалог всё равно откроется в
    // родительской директории с уже подставленным именем в поле ввода;
    // если пользователь просто нажмёт "Выбрать папку" не глядя - получит
    // осмысленное имя, а не "Новая папка" (баг, найденный на реальном тесте:
    // пустой диалог -> пользователь жмёт "Новая папка" в Проводнike -> имя
    // остаётся дефолтным, т.к. переименовать не догадался).
    defaultPath,
    properties: ["openDirectory", "createDirectory"],
  });
  if (res.canceled || res.filePaths.length === 0) return null;
  return res.filePaths[0];
});

ipcMain.handle("shell:openPath", async (_e, target: string) => {
  await shell.openPath(target);
});

ipcMain.handle("shell:openExternal", async (_e, url: string) => {
  // Только http(s) - см. HANDOFF_16, ссылка на баг-репорт/скачивание
  // нового инсталлятора открывается в системном браузере, а не внутри
  // окна приложения.
  if (/^https?:\/\//i.test(url)) {
    await shell.openExternal(url);
  }
});

ipcMain.handle("shell:openInVSCode", async (_e, target: string) => {
  // `code` - это shell-команда, которую сам VS Code добавляет в PATH при
  // установке (опция "Add to PATH" в инсталляторе, включена по умолчанию
  // на Windows) - если её нет, значит VS Code либо не установлен, либо
  // ставился без этой опции. shell:true нужен именно для .cmd-обёртки VS
  // Code на Windows (сам `code` там - это code.cmd, как и `mvn.cmd` в
  // toolinstaller.hpp - на POSIX это шло бы иначе, но здесь конкретно про code.cmd/mvn.cmd на Windows).
  return new Promise((resolve) => {
    const proc = spawn("code", [target], { shell: true, windowsHide: true });
    let errored = false;
    proc.on("error", () => {
      errored = true;
      resolve({ ok: false, error: "VS Code не найден в PATH (команда `code`) - убедись, что VS Code установлен и при установке была отмечена опция \"Add to PATH\"." });
    });
    proc.on("close", (code) => {
      if (errored) return;
      resolve(code === 0 ? { ok: true } : { ok: false, error: `code завершился с кодом ${code}` });
    });
  });
});

ipcMain.handle("run:decompile", async (event, jarPath: string, outDir: string) => {
  if (runningProc) {
    return { ok: false, error: "Декомпиляция уже запущена" };
  }
  if (!fs.existsSync(jarPath)) {
    return { ok: false, error: "Файл .jar не найден: " + jarPath };
  }

  // HANDOFF_47: --headless убран совсем (был no-op в cli_main.cpp - см.
  // HANDOFF_46, движок и так никогда не пытается открыть GUI, вся
  // GUI-логика тут, в Electron; пользователь счёл флаг бесполезным).
  const scriptArgs = [jarPath, outDir];
  // См. HANDOFF_19 - тумблер "проверка легитимности" в настройках. По
  // умолчанию включена (см. DEFAULT_SETTINGS) - флаг добавляется, ТОЛЬКО
  // когда пользователь явно выключил.
  if (!loadSettings().legitimacyCheck) scriptArgs.push("--no-legitimacy-check");
  let cmd: string, args: string[];
  try {
    ({ cmd, args } = engineInvocation(scriptArgs));
  } catch (e) {
    return { ok: false, error: (e as Error).message };
  }

  return new Promise((resolve) => {
    const proc = spawn(cmd, args, {
      cwd: engineDir(),
      env: { ...process.env },
    });
    runningProc = proc;

    const send = (channel: string, payload: unknown) => {
      if (mainWindow && !mainWindow.isDestroyed()) {
        mainWindow.webContents.send(channel, payload);
      }
    };

    let buf = "";
    // Батчим строки лога вместо отправки КАЖДОЙ отдельным IPC-сообщением -
    // на jar с тысячами методов движок может выдать сотни строк за долю
    // секунды, и раньше каждая строка = отдельный IPC round-trip +
    // отдельный React-рендер на стороне клиента (см. App.tsx) - реальная
    // причина ощутимых лагов на крупных jar, не сама анимация плашек.
    let pendingLines: { line: string; stream: "stdout" | "stderr" }[] = [];
    let flushTimer: NodeJS.Timeout | null = null;
    const scheduleFlush = () => {
      if (flushTimer) return;
      flushTimer = setTimeout(() => {
        flushTimer = null;
        if (pendingLines.length === 0) return;
        const batch = pendingLines;
        pendingLines = [];
        send("run:log", { lines: batch });
      }, 40);
    };
    const flushLines = (chunk: Buffer, stream: "stdout" | "stderr") => {
      buf += chunk.toString("utf-8");
      const lines = buf.split(/\r?\n/);
      buf = lines.pop() ?? "";
      for (const line of lines) pendingLines.push({ line, stream });
      if (pendingLines.length > 0) scheduleFlush();
    };

    proc.stdout.on("data", (d) => flushLines(d, "stdout"));
    proc.stderr.on("data", (d) => flushLines(d, "stderr"));

    proc.on("close", (code) => {
      if (buf) pendingLines.push({ line: buf, stream: "stdout" });
      if (flushTimer) clearTimeout(flushTimer);
      if (pendingLines.length > 0) send("run:log", { lines: pendingLines });
      runningProc = null;
      resolve({ ok: code === 0, code, outDir });
    });

    proc.on("error", (err) => {
      runningProc = null;
      resolve({ ok: false, error: String(err) });
    });
  });
});

ipcMain.handle("jar:summary", async (_e, jarPath: string) => {
  // См. HANDOFF_22 (история): раньше ВСЕГДА спавнили Python-подпроцесс - для
  // PyInstaller onefile-сборки это была самораспаковка exe на КАЖДЫЙ вызов,
  // ощущалось как "очень долго". Сейчас движок - компактный C++-бинарник
  // (HANDOFF_46), подпроцесс сам по себе больше не так дорог, но читать ZIP
  // central directory напрямую в Node всё равно быстрее (без spawn() вообще)
  // - оставлено как основной путь. Подпроцесс - только запасной вариант для
  // того, что быстрый путь сознательно не поддерживает (ZIP64 и т.п. edge-case).
  try {
    return readJarSummaryNative(jarPath);
  } catch {
    let cmd: string, args: string[];
    try {
      ({ cmd, args } = engineInvocation(["--jar-summary", jarPath]));
    } catch (e) {
      return { error: (e as Error).message };
    }
    return new Promise((resolve) => {
      let out = "";
      const proc = spawn(cmd, args, {
        cwd: engineDir(),
        env: { ...process.env },
      });
      proc.stdout.on("data", (d) => (out += d.toString("utf-8")));
      proc.on("close", () => {
        try {
          resolve(JSON.parse(out.trim().split(/\r?\n/).pop() ?? "{}"));
        } catch {
          resolve({ error: "не удалось получить сводку по jar" });
        }
      });
      proc.on("error", (err) => resolve({ error: String(err) }));
    });
  }
});

ipcMain.handle("run:cancel", async () => {
  if (runningProc) {
    runningProc.kill();
    runningProc = null;
    return true;
  }
  return false;
});

// HANDOFF_52: мини-IDE (файловый проводник + просмотр/редактирование
// декомпилированных файлов, см. HANDOFF_39 п.2 старой спецификации) -
// два хендлера: список директории (ленивая подгрузка по одному уровню -
// у крупных проектов, напр. BukkitOfUtils, 618+ .java файлов, разом
// строить весь рекурсивный список дорого и не нужно, пока узел дерева не
// раскрыли) и чтение файла (с лимитом размера - защита от случайной
// попытки открыть большой бинарный ресурс как текст).
//
// БЕЗОПАСНОСТЬ: renderer передаёт `root` (тот самый outDir, который САМ
// ЖЕ электрон вернул после успешной декомпиляции/пользователь выбрал
// через dialog:selectOutDir) и относительный путь ВНУТРИ него - сервер
// (этот процесс) проверяет, что итоговый абсолютный путь ДЕЙСТВИТЕЛЬНО
// лежит внутри root (через path.resolve + startsWith с проверкой границы
// разделителя, чтобы "/foo" не совпадал с "/foobar"), прежде чем читать
// что-либо с диска - иначе renderer (если когда-нибудь скомпрометирован
// какой-то будущей уязвимостью в самой странице) не смог бы читать
// произвольные файлы пользователя через эту функцию конкретно (остальные
// пути атаки типа openInVSCode/openPath - отдельный вопрос, не эта задача).
const MAX_TEXT_FILE_BYTES = 4 * 1024 * 1024; // 4 МБ - с запасом для любого разумного .java/.xml/.txt

function resolveWithinRoot(root: string, relPath: string): string | null {
  const resolvedRoot = path.resolve(root);
  const resolvedTarget = path.resolve(resolvedRoot, relPath || ".");
  if (resolvedTarget === resolvedRoot) return resolvedTarget;
  if (resolvedTarget.startsWith(resolvedRoot + path.sep)) return resolvedTarget;
  return null;
}

ipcMain.handle("fs:listDir", async (_e, root: string, relDir: string) => {
  const dirPath = resolveWithinRoot(root, relDir);
  if (!dirPath) return { ok: false, error: "путь вне корневой директории проекта" };
  try {
    const entries = fs.readdirSync(dirPath, { withFileTypes: true });
    const items = entries
      .map((ent) => ({ name: ent.name, isDir: ent.isDirectory() }))
      .sort((a, b) => (a.isDir === b.isDir ? a.name.localeCompare(b.name) : a.isDir ? -1 : 1));
    return { ok: true, items };
  } catch (e) {
    return { ok: false, error: String(e) };
  }
});

ipcMain.handle("fs:readTextFile", async (_e, root: string, relPath: string) => {
  const filePath = resolveWithinRoot(root, relPath);
  if (!filePath) return { ok: false, error: "путь вне корневой директории проекта" };
  try {
    const stat = fs.statSync(filePath);
    if (!stat.isFile()) return { ok: false, error: "не файл" };
    if (stat.size > MAX_TEXT_FILE_BYTES) {
      return { ok: false, error: `файл слишком большой для просмотра в редакторе (${(stat.size / 1024 / 1024).toFixed(1)} МБ)` };
    }
    const buf = fs.readFileSync(filePath);
    // Грубая эвристика "это бинарный файл" - нулевой байт в первых 8000
    // байтах практически никогда не встречается в валидном UTF-8/ASCII
    // тексте (тот же трюк использует git для своего "binary file" détection).
    const probeLen = Math.min(buf.length, 8000);
    for (let i = 0; i < probeLen; i++) {
      if (buf[i] === 0) return { ok: false, error: "похоже на бинарный файл - предпросмотр недоступен" };
    }
    return { ok: true, content: buf.toString("utf-8"), size: stat.size };
  } catch (e) {
    return { ok: false, error: String(e) };
  }
});

let installingProc: ChildProcessWithoutNullStreams | null = null;

ipcMain.handle("tools:install", async (_event, only?: "jdk" | "java" | "maven") => {
  if (installingProc) {
    return { ok: false, error: "Установка уже идёт" };
  }
  const flag = only ? `--install-tools-json=${only}` : "--install-tools-json";
  let cmd: string, args: string[];
  try {
    ({ cmd, args } = engineInvocation([flag]));
  } catch (e) {
    return { java: null, maven: null, errors: [(e as Error).message] };
  }

  return new Promise((resolve) => {
    const proc = spawn(cmd, args, {
      cwd: engineDir(),
      env: { ...process.env },
    });
    installingProc = proc;

    const send = (channel: string, payload: unknown) => {
      if (mainWindow && !mainWindow.isDestroyed()) mainWindow.webContents.send(channel, payload);
    };

    let buf = "";
    let finalResult: { java: string | null; maven: string | null; errors: string[] } | null = null;

    const handleChunk = (chunk: Buffer) => {
      buf += chunk.toString("utf-8");
      const lines = buf.split(/\r?\n/);
      buf = lines.pop() ?? "";
      for (const line of lines) {
        if (!line.trim()) continue;
        // --install-tools-json печатает ТОЛЬКО валидный NDJSON (см.
        // main.py::_try_handle_install_tools_json) - но на случай, если
        // что-то постороннее (напр. предупреждение интерпретатора) попадёт
        // в тот же stdout, не даём одной кривой строке уронить весь парсинг.
        try {
          const evt = JSON.parse(line);
          if (evt.type === "progress") send("tools:progress", evt);
          else if (evt.type === "done") finalResult = evt;
          else if (evt.type === "error") finalResult = { java: null, maven: null, errors: [evt.message] };
        } catch {
          /* игнорируем нераспарсенные строки */
        }
      }
    };

    proc.stdout.on("data", handleChunk);
    proc.stderr.on("data", handleChunk);

    proc.on("close", () => {
      installingProc = null;
      if (finalResult) resolve({ ok: finalResult.errors.length === 0, ...finalResult });
      else resolve({ ok: false, error: "Установщик завершился без ответа - см. вывод декомпиляции для деталей" });
    });

    proc.on("error", (err) => {
      installingProc = null;
      resolve({ ok: false, error: String(err) });
    });
  });
});
