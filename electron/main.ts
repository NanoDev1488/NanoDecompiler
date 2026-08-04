// Главный процесс Electron.
//
// ВАЖНО (архитектурное решение): движок декомпиляции (classfile.py, ir.py,
// cfg.py, stackvm.py, engine.py, structure.py, emit.py и т.д.) НЕ переписан
// на TypeScript и запускается как есть, через python3, дочерним процессом.
// Причина - см. HANDOFF_1_ARCHITECTURE.md, раздел "ключевой принцип
// архитектуры": движок - это ~9 диагностированных и исправленных багов
// компиляции, откалиброванный на реальных .jar (EryBuyer/DeathUtils/
// GlowClans, ~93-97% "полностью восстановлено"). Переписывать несколько
// тысяч строк символического исполнения байткода на JS без тех же
// регрессионных .jar под рукой - гарантированный откат качества и повторный
// проход по всем 9 багам заново. Здесь переписан ТОЛЬКО GUI-слой (три
// Tkinter/CustomTkinter/Flet темы -> один Electron+React интерфейс),
// что и было целью запроса.
import { app, BrowserWindow, ipcMain, dialog, shell } from "electron";
import { spawn, ChildProcessWithoutNullStreams } from "child_process";
import * as path from "path";
import * as fs from "fs";
import { registerUpdateHandlers } from "./updater";

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

function pythonBin(): string {
  // Termux/Linux/macOS - всегда python3 (см. HANDOFF_1). На Windows тоже
  // пробуем python3 первым, с фолбэком на python в рендерере при ошибке.
  return process.platform === "win32" ? "python" : "python3";
}

// См. HANDOFF_7/8/9 - раньше клиент ВСЕГДА запускал python3/python main.py
// напрямую, а апдейтер (electron/updater.ts) при этом качал и подменял
// NanoDecompilerCLI.exe, который клиент вообще никогда не вызывал - кнопка
// "Обновить" в топбаре ничего реально не меняла в поведении. Теперь: если
// рядом лежит NanoDecompilerCLI.exe (Windows-only, собирается в
// .github/workflows/build-and-release.yml как `pyinstaller --onefile
// --name NanoDecompilerCLI main.py` - ТОТ ЖЕ движок, те же флаги 1:1, т.к.
// это просто main.py, упакованный в один exe) - используем его, иначе
// как и раньше падаем на python3/python + main.py. На свежей установке
// exe'а рядом нет (в extraResources пакуются только .py-файлы, см.
// package.json) - клиент работает как и раньше, python; exe появляется
// ТОЛЬКО после того, как пользователь один раз нажмёт "Обновить".
//
// НЕ включаем это на Linux/macOS/Termux - exe собирается только под
// Windows (windows-latest раннер в workflow), на других платформах его
// в принципе быть не может (а если бы вдруг оказался - попытка spawn()
// его напрямую там просто упадёт с ENOEXEC, а не тихо сработает).
function engineInvocation(scriptArgs: string[]): { cmd: string; args: string[] } {
  if (process.platform === "win32") {
    const exePath = path.join(engineDir(), "NanoDecompilerCLI.exe");
    if (fs.existsSync(exePath)) {
      // exe уже "содержит в себе" main.py - путь к скрипту не передаём,
      // только сами позиционные аргументы/флаги.
      return { cmd: exePath, args: scriptArgs };
    }
  }
  const mainPy = path.join(engineDir(), "main.py");
  return { cmd: pythonBin(), args: [mainPy, ...scriptArgs] };
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
  // toolinstaller.py - subprocess без shell не умеет их запускать напрямую).
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

  // --headless критически важен на Windows: без него main.py() бы всегда
  // пытался открыть tkinter GUI (см. main.py::main(), ветка
  // "platform.system() == Windows") ВМЕСТО обычной консольной декомпиляции -
  // именно эта ветка и была целью всей замены на Electron. На Linux/Termux
  // флаг безвреден (там и так нет форсированного GUI-пути), но передаём его
  // всегда, для единообразия между платформами.
  const scriptArgs = [jarPath, outDir, "--headless"];
  // См. HANDOFF_19 - тумблер "проверка легитимности" в настройках. По
  // умолчанию включена (см. DEFAULT_SETTINGS) - флаг добавляется, ТОЛЬКО
  // когда пользователь явно выключил.
  if (!loadSettings().legitimacyCheck) scriptArgs.push("--no-legitimacy-check");
  const { cmd, args } = engineInvocation(scriptArgs);

  return new Promise((resolve) => {
    const proc = spawn(cmd, args, {
      cwd: engineDir(),
      env: { ...process.env, PYTHONIOENCODING: "utf-8", PYTHONUNBUFFERED: "1" },
    });
    runningProc = proc;

    const send = (channel: string, payload: unknown) => {
      if (mainWindow && !mainWindow.isDestroyed()) {
        mainWindow.webContents.send(channel, payload);
      }
    };

    let buf = "";
    const flushLines = (chunk: Buffer, stream: "stdout" | "stderr") => {
      buf += chunk.toString("utf-8");
      const lines = buf.split(/\r?\n/);
      buf = lines.pop() ?? "";
      for (const line of lines) send("run:log", { line, stream });
    };

    proc.stdout.on("data", (d) => flushLines(d, "stdout"));
    proc.stderr.on("data", (d) => flushLines(d, "stderr"));

    proc.on("close", (code) => {
      if (buf) send("run:log", { line: buf, stream: "stdout" });
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
  const { cmd, args } = engineInvocation(["--jar-summary", jarPath]);
  return new Promise((resolve) => {
    let out = "";
    const proc = spawn(cmd, args, {
      cwd: engineDir(),
      env: { ...process.env, PYTHONIOENCODING: "utf-8" },
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
});

ipcMain.handle("run:cancel", async () => {
  if (runningProc) {
    runningProc.kill();
    runningProc = null;
    return true;
  }
  return false;
});

let installingProc: ChildProcessWithoutNullStreams | null = null;

ipcMain.handle("tools:install", async (_event, only?: "jdk" | "java" | "maven") => {
  if (installingProc) {
    return { ok: false, error: "Установка уже идёт" };
  }
  const flag = only ? `--install-tools-json=${only}` : "--install-tools-json";
  const { cmd, args } = engineInvocation([flag]);

  return new Promise((resolve) => {
    const proc = spawn(cmd, args, {
      cwd: engineDir(),
      env: { ...process.env, PYTHONIOENCODING: "utf-8", PYTHONUNBUFFERED: "1" },
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
