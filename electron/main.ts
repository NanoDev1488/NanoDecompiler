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

let mainWindow: BrowserWindow | null = null;
let runningProc: ChildProcessWithoutNullStreams | null = null;

const isDev = !app.isPackaged;

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

app.whenReady().then(createWindow);

app.on("window-all-closed", () => {
  if (process.platform !== "darwin") app.quit();
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

ipcMain.handle("dialog:selectOutDir", async () => {
  const res = await dialog.showOpenDialog(mainWindow!, {
    title: "Папка для результата",
    properties: ["openDirectory", "createDirectory"],
  });
  if (res.canceled || res.filePaths.length === 0) return null;
  return res.filePaths[0];
});

ipcMain.handle("shell:openPath", async (_e, target: string) => {
  await shell.openPath(target);
});

ipcMain.handle("run:decompile", async (event, jarPath: string, outDir: string) => {
  if (runningProc) {
    return { ok: false, error: "Декомпиляция уже запущена" };
  }
  if (!fs.existsSync(jarPath)) {
    return { ok: false, error: "Файл .jar не найден: " + jarPath };
  }

  const mainPy = path.join(engineDir(), "main.py");
  // --headless критически важен на Windows: без него main.py() бы всегда
  // пытался открыть tkinter GUI (см. main.py::main(), ветка
  // "platform.system() == Windows") ВМЕСТО обычной консольной декомпиляции -
  // именно эта ветка и была целью всей замены на Electron. На Linux/Termux
  // флаг безвреден (там и так нет форсированного GUI-пути), но передаём его
  // всегда, для единообразия между платформами.
  const args = [mainPy, jarPath, outDir, "--headless"];

  return new Promise((resolve) => {
    const proc = spawn(pythonBin(), args, {
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

ipcMain.handle("run:cancel", async () => {
  if (runningProc) {
    runningProc.kill();
    runningProc = null;
    return true;
  }
  return false;
});
