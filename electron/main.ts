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
import * as os from "os";
import { registerUpdateHandlers } from "./updater";
import { readJarSummaryNative } from "./jarSummary";

// БАГ-ФИКС (реальный, воспроизведён пользователем на AntiX Linux -
// "Read-only file system"): DEFAULT_SETTINGS.outputDir = "~/NanoDecompiler/out"
// - тильда работает ТОЛЬКО в интерактивном shell, никогда при передаче
// пути напрямую в std::filesystem/fs - буквальная "~" создавалась как
// подпапка ОТНОСИТЕЛЬНО cwd движка (engineDir()), а в AppImage это
// смонтированный squashfs-образ - физически read-only. На Windows "~"
// вообще ничего не значит ни в каком контексте - та же причина там тоже
// правдоподобно объясняет "движок не запускается" с самого начала.
function expandHome(p: string): string {
  if (p === "~") return os.homedir();
  if (p.startsWith("~/") || p.startsWith("~\\")) return path.join(os.homedir(), p.slice(2));
  return p;
}

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
  // Живой лого-марк в шапке приложения + иконка окна/панели задач (см.
  // setAppIcon ниже) - выбор пользователя между двумя вариантами дизайна.
  // ВАЖНО: это НЕ иконка самого .exe/.app в проводнике/при первом запуске -
  // та зашивается в бинарник electron-builder'ом на этапе СБОРКИ и в
  // рантайме поменяться не может ни при каких обстоятельствах (ограничение
  // ОС, не наше) - честно объяснено в UI, не только здесь в комментарии.
  appIcon: "terminal" | "layers";
};
const DEFAULT_SETTINGS: Settings = { legitimacyCheck: true, autoUpdateCheck: true, appIcon: "terminal" };

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
      appIcon: parsed.appIcon === "terminal" || parsed.appIcon === "layers" ? parsed.appIcon : DEFAULT_SETTINGS.appIcon,
    };
  } catch {
    return { ...DEFAULT_SETTINGS };
  }
}

function saveSettings(s: Settings): void {
  fs.writeFileSync(settingsPath(), JSON.stringify(s, null, 2), "utf-8");
}

// БАГ-ФИКС/фича: выбор иконки приложения (см. Settings.appIcon выше).
// iconsDir() зеркалит engineDir() - dev режим читает прямо из resources/,
// упакованный - из resourcesPath (electron-builder кладёт extraResources
// туда же).
function iconsDir(): string {
  return isDev ? path.join(__dirname, "..", "resources", "icons") : path.join(process.resourcesPath, "icons");
}

function iconPathFor(choice: Settings["appIcon"]): string {
  const name = choice === "layers" ? "icon-e-layers.png" : "icon-b-terminal.png";
  return path.join(iconsDir(), name);
}

// Применяет выбранную иконку к окну/панели задач ЖИВЬЁМ, без перезапуска -
// работает на Windows/Linux (Electron setIcon() поддерживается там), на
// macOS дока-иконку так поменять нельзя (ограничение самой ОС - dock icon
// определяется Info.plist упакованного .app) - см. честную подпись в UI
// (SettingsModal), а не молчаливое "не сработало". Сам .exe/.app остаётся
// с той иконкой, что была на момент сборки - это отдельный,
// непреодолимый в рантайме случай, см. комментарий у Settings.appIcon.
function applyAppIcon(choice: Settings["appIcon"]): void {
  if (process.platform === "darwin") return;
  if (!mainWindow || mainWindow.isDestroyed()) return;
  try {
    mainWindow.setIcon(iconPathFor(choice));
  } catch {
    // молча игнорируем - косметика, не должна ронять приложение
  }
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
    // БАГ-ФИКС: было #0a0d0b с комментарием "из styles.css" - этот файл
    // заменён на index.css при интеграции нового GUI в этой сессии, и
    // реальный --color-surface там #0c100d, не #0a0d0b - несовпадение
    // давало едва заметную вспышку неверного фона на долю секунды перед
    // отрисовкой контента.
    backgroundColor: "#0c100d", // --color-surface из index.css
    autoHideMenuBar: true,
    // БАГ-ФИКС (реальный, воспроизведён пользователем - "х2 кнопок"):
    // frame не был задан вообще -> ОС рисовала СВОИ системные кнопки
    // (свернуть/развернуть/закрыть) НАД кастомным Titlebar.tsx, у которого
    // были СВОИ, но фиктивные React-кнопки (только показывали toast,
    // реально ничего не делали) - две пары кнопок разом, только нижняя
    // (кастомная) не работала. frame:false убирает системную рамку целиком
    // - кастомные кнопки внизу теперь единственные и реально подключены
    // (см. window:minimize/maximize/close ниже).
    frame: false,
    icon: iconPathFor(loadSettings().appIcon),
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
  registerUpdateHandlers(engineDir, engineInvocation);
  createWindow();
});

app.on("window-all-closed", () => {
  if (process.platform !== "darwin") app.quit();
});

ipcMain.handle("settings:get", async (): Promise<Settings> => loadSettings());

ipcMain.handle("settings:set", async (_e, partial: Partial<Settings>): Promise<Settings> => {
  const merged = { ...loadSettings(), ...partial };
  saveSettings(merged);
  if (partial.appIcon) applyAppIcon(merged.appIcon);
  return merged;
});

// Превью для пикера иконок в настройках - как base64 data URI, а не
// прямой file:// путь: рендерер работает с contextIsolation:true и
// webSecurity включён по умолчанию, произвольные file:// пути внутри
// него не гарантированно отрисуются в упакованной сборке (зависит от
// платформы/протокола) - base64 работает всегда, независимо от этого.
ipcMain.handle("appIcon:thumbnails", async () => {
  const read = (name: string) => {
    try {
      return `data:image/png;base64,${fs.readFileSync(path.join(iconsDir(), name)).toString("base64")}`;
    } catch {
      return null;
    }
  };
  return {
    terminal: read("icon-b-terminal-thumb.png"),
    layers: read("icon-e-layers-thumb.png"),
  };
});

ipcMain.handle("dialog:selectJar", async () => {
  // БАГ-ФИКС: раньше был только openFile (без multiSelections) и
  // возвращался res.filePaths[0] - первый выбранный файл, остальные
  // молча терялись. Очередь (state/engine.tsx: jobs: Job[],
  // addJarPaths(paths: string[])) уже умела принимать несколько jar'ов
  // разом - drag&drop это уже использовал, диалог выбора файла - нет.
  const res = await dialog.showOpenDialog(mainWindow!, {
    title: "Выбери .jar плагина (можно несколько)",
    properties: ["openFile", "multiSelections"],
    filters: [{ name: "Java Archive", extensions: ["jar"] }],
  });
  if (res.canceled || res.filePaths.length === 0) return [];
  return res.filePaths;
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
  await shell.openPath(expandHome(target));
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
  outDir = expandHome(outDir);

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
    // detached: true на POSIX делает proc лидером своей process group -
    // это позволяет позже убить всю группу (движок + его собственные
    // подпроцессы вроде curl из toolinstaller.cpp) разом через
    // process.kill(-pid), а не только сам NanoDecompilerCLI. На Windows
    // detached не создаёт process group так же, но там дерево убивается
    // через `taskkill /T` (см. run:cancel ниже).
    const proc = spawn(cmd, args, {
      cwd: engineDir(),
      env: { ...process.env },
      detached: process.platform !== "win32",
    });
    runningProc = proc;

    const send = (channel: string, payload: unknown) => {
      if (mainWindow && !mainWindow.isDestroyed()) {
        mainWindow.webContents.send(channel, payload);
      }
    };

    // БАГ-ФИКС: раньше был ОДИН общий buf для stdout и stderr - Node не
    // гарантирует порядок доставки данных между разными потоками, так что
    // кусок строки из stdout мог склеиться с куском из stderr в одну
    // строку с неверной меткой stream (и теоретически разорвать
    // многобайтовый UTF-8 символ на границе чанков разных потоков).
    // Раздельные буферы на каждый поток убирают проблему полностью.
    let bufOut = "";
    let bufErr = "";
    // БАГ-ФИКС: раньше при code !== 0 в resolve() не передавался error
    // вообще - GUI показывал обезличенное "движок завершился с кодом N",
    // хотя сам движок уже печатает понятную причину строкой "[!] <текст>"
    // перед выходом (run_decompile_console в cli_main.cpp - например,
    // "Обнаружен Fabric mod ... - временно моды не декомпилируются").
    // Запоминаем последнюю такую строку, чтобы отдать её как реальный
    // error вместо generic-сообщения.
    let lastReasonLine: string | null = null;
    // БАГ-ФИКС: если движок падает БЕЗ единой строки "[!] ..." (например,
    // чистый крэш на уровне ОС на Windows - antivirus quarantine, missing
    // dependency и т.п., без единого байта полезного вывода) -
    // lastReasonLine оставался null, и пользователь видел бесполезное
    // "движок завершился с кодом N" без единой зацепки для диагностики.
    // Держим последние несколько строк ЛЮБОГО вывода как запасной вариант.
    const recentLines: string[] = [];
    const rememberRecent = (line: string) => {
      if (!line.trim()) return;
      recentLines.push(line);
      if (recentLines.length > 5) recentLines.shift();
    };
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
      const isOut = stream === "stdout";
      const carried = (isOut ? bufOut : bufErr) + chunk.toString("utf-8");
      const lines = carried.split(/\r?\n/);
      const rest = lines.pop() ?? "";
      if (isOut) bufOut = rest;
      else bufErr = rest;
      for (const line of lines) {
        if (line.startsWith("[!] ")) lastReasonLine = line.slice(4);
        rememberRecent(line);
        pendingLines.push({ line, stream });
      }
      if (pendingLines.length > 0) scheduleFlush();
    };

    proc.stdout.on("data", (d) => flushLines(d, "stdout"));
    proc.stderr.on("data", (d) => flushLines(d, "stderr"));

    proc.on("close", (code) => {
      if (bufOut) pendingLines.push({ line: bufOut, stream: "stdout" });
      if (bufErr) pendingLines.push({ line: bufErr, stream: "stderr" });
      if (flushTimer) clearTimeout(flushTimer);
      if (pendingLines.length > 0) send("run:log", { lines: pendingLines });
      runningProc = null;
      const fallbackError =
        lastReasonLine ?? (recentLines.length > 0 ? recentLines.join(" | ") : `движок завершился с кодом ${code} без вывода - возможно, антивирус блокирует запуск, или файл повреждён при скачивании`);
      resolve({ ok: code === 0, code, outDir, error: code !== 0 ? fallbackError : undefined });
    });

    proc.on("error", (err) => {
      // spawn() провалился на уровне ОС (ENOENT - файл не найден,
      // EACCES - нет прав на выполнение) - раньше это тоже уходило в
      // generic "код N", хотя код здесь даже не появится (процесс не
      // стартовал вовсе).
      runningProc = null;
      resolve({ ok: false, code: null, outDir, error: `не удалось запустить движок: ${err.message}` });
    });
  });
});

// БАГ-ФИКС: раньше версия движка в GUI (SettingsModal "Проверить" /
// AppHeader / Titlebar / StatusBar) была захардкожена заглушкой "2.4.1" из
// демо-прототипа - реальная версия (version.hpp -> --version) никогда не
// спрашивалась. Кэшируется на время жизни процесса - версия не меняется
// без перезапуска приложения, спрашивать движок заново на каждый клик
// "Проверить" незачем.
let cachedEngineVersion: string | null = null;
// GUI-версия (Electron-обвязка) - реальная, из package.json через
// app.getVersion() (Electron делает это сам), не отдельный хардкод рядом
// с версией движка. sync-обработчик - значение уже в памяти при старте,
// незачем гонять Promise ради одной строки.
// БАГ-ФИКС: "java 21.0.3"/"maven 3.9.6" в StatusBar.tsx и "Окружение" в
// SettingsModal были захардкожены заглушками, вообще ничем не
// подкреплёнными - toggleEnvIssue() был просто ручным UI-тумблером без
// единого реального вызова java/mvn где-либо в проекте. Настоящая
// проверка через дочерний процесс - оба инструмента при --version часто
// пишут в stderr (особенно java), поэтому собираем оба потока.
// БАГ-ФИКС (реальный, воспроизведён на Windows): spawn(cmd, args) БЕЗ
// shell:true на Windows не находит .cmd/.bat-обёртки - а Maven на Windows
// ВСЕГДА ставится как mvn.cmd, никогда как mvn.exe! Java обычно java.exe
// (нашёлся бы и без shell), но некоторые менеджеры JDK (Scoop, sdkman для
// Windows и т.п.) тоже шимят через .cmd - отсюда "оба установлены, но
// пишет что их нету" именно на Windows. shell:true решает оба случая сразу.
function checkVersionCmd(cmd: string, args: string[]): Promise<{ ok: boolean; text?: string }> {
  return new Promise((resolve) => {
    let out = "";
    let settled = false;
    const proc = spawn(cmd, args, { env: { ...process.env }, shell: process.platform === "win32" });
    const finish = (ok: boolean) => {
      if (settled) return;
      settled = true;
      resolve({ ok, text: ok ? out.split(/\r?\n/)[0]?.trim() : undefined });
    };
    proc.stdout.on("data", (d: Buffer) => (out += d.toString("utf-8")));
    proc.stderr.on("data", (d: Buffer) => (out += d.toString("utf-8")));
    proc.on("close", (code: number) => finish(code === 0 && out.trim().length > 0));
    proc.on("error", () => finish(false));
  });
}

// Резервный поиск по стандартным путям установки, если PATH почему-то не
// содержит java/mvn в окружении, унаследованном Electron-процессом (по
// просьбе пользователя - "искать разные части"). НЕ отдельный C++/Rust-
// бинарник: это чисто ОС-специфичный поиск файлов на диске, Node делает
// это надёжно сам через fs/glob - заводить ради этого ещё один
// компилируемый на 3 платформах артефакт добавило бы риска и веса сборки
// без реальной пользы (это не байткод-логика, где нужен именно C++).
function findFallbackBinary(names: string[], searchRoots: string[]): string | null {
  for (const root of searchRoots) {
    try {
      if (!fs.existsSync(root)) continue;
      const entries = fs.readdirSync(root, { withFileTypes: true });
      for (const entry of entries) {
        if (!entry.isDirectory()) continue;
        for (const name of names) {
          const candidate = path.join(root, entry.name, "bin", name);
          if (fs.existsSync(candidate)) return candidate;
        }
      }
    } catch {
      // недоступная директория - молча пропускаем, это лишь fallback
    }
  }
  return null;
}

function javaSearchRoots(): string[] {
  const roots: string[] = [];
  if (process.env.JAVA_HOME) roots.push(path.join(process.env.JAVA_HOME, ".."));
  if (process.platform === "win32") {
    roots.push("C:\\Program Files\\Java", "C:\\Program Files\\Eclipse Adoptium", "C:\\Program Files\\Zulu");
  } else if (process.platform === "darwin") {
    roots.push("/Library/Java/JavaVirtualMachines", "/opt/homebrew/opt");
  } else {
    roots.push("/usr/lib/jvm");
  }
  return roots;
}

function mavenSearchRoots(): string[] {
  const roots: string[] = [];
  if (process.env.MAVEN_HOME) roots.push(path.join(process.env.MAVEN_HOME, ".."));
  if (process.platform === "win32") {
    roots.push("C:\\Program Files\\Apache\\maven", "C:\\apache-maven", "C:\\maven");
  } else {
    roots.push("/opt", "/usr/local");
  }
  return roots;
}

ipcMain.handle("env:check", async () => {
  let java = await checkVersionCmd("java", ["--version"]);
  let maven = await checkVersionCmd("mvn", ["--version"]);
  // PATH-поиск не нашёл - пробуем стандартные пути установки напрямую по
  // полному пути к бинарнику (спавн по абсолютному пути не зависит от
  // PATH вообще, так что shell:true здесь не нужен для .exe, но нужен
  // для .cmd на Windows - оставляем ту же логику через checkVersionCmd).
  if (!java.ok) {
    const found = findFallbackBinary(["java.exe", "java"], javaSearchRoots());
    if (found) java = await checkVersionCmd(found, ["--version"]);
  }
  if (!maven.ok) {
    const found = findFallbackBinary(["mvn.cmd", "mvn"], mavenSearchRoots());
    if (found) maven = await checkVersionCmd(found, ["--version"]);
  }
  return { java, maven };
});

ipcMain.handle("gui:version", async () => app.getVersion());

// Реальное управление окном для кастомного Titlebar.tsx - см. frame:false
// выше (без родной рамки ОС нужно самим сворачивать/разворачивать/
// закрывать через IPC, раньше эти кнопки были фиктивными заглушками).
ipcMain.handle("window:minimize", async () => {
  mainWindow?.minimize();
});
ipcMain.handle("window:toggleMaximize", async () => {
  if (!mainWindow) return;
  if (mainWindow.isMaximized()) mainWindow.unmaximize();
  else mainWindow.maximize();
});
ipcMain.handle("window:close", async () => {
  mainWindow?.close();
});
ipcMain.handle("window:isMaximized", async () => mainWindow?.isMaximized() ?? false);

ipcMain.handle("engine:version", async () => {
  if (cachedEngineVersion) return { ok: true, version: cachedEngineVersion };
  let cmd: string, args: string[];
  try {
    ({ cmd, args } = engineInvocation(["--version"]));
  } catch (e) {
    return { ok: false, error: (e as Error).message };
  }
  return new Promise((resolve) => {
    let out = "";
    const proc = spawn(cmd, args, { cwd: engineDir(), env: { ...process.env } });
    proc.stdout.on("data", (d) => (out += d.toString("utf-8")));
    proc.on("close", () => {
      try {
        const parsed = JSON.parse(out.trim());
        cachedEngineVersion = parsed.version ?? null;
        if (cachedEngineVersion) resolve({ ok: true, version: cachedEngineVersion });
        else resolve({ ok: false, error: "движок не вернул версию" });
      } catch {
        resolve({ ok: false, error: "не удалось разобрать ответ движка" });
      }
    });
    proc.on("error", (e) => resolve({ ok: false, error: e.message }));
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
  // БАГ-ФИКС: раньше runningProc.kill() слал SIGTERM только самому
  // NanoDecompilerCLI. Движок сам вызывает std::system("curl ...") в
  // toolinstaller.cpp при первом скачивании JDK/Maven - если отмена
  // происходит именно в этот момент, curl оставался висеть орфаном.
  // Теперь убиваем всё дерево: на POSIX - всю process group (proc был
  // заспавнен с detached:true, так что pid == pgid, убиваем -pid); на
  // Windows - через taskkill /T (рекурсивно по дереву процессов).
  if (runningProc && runningProc.pid) {
    const pid = runningProc.pid;
    if (process.platform === "win32") {
      spawn("taskkill", ["/PID", String(pid), "/T", "/F"]);
    } else {
      try {
        process.kill(-pid, "SIGTERM");
      } catch {
        runningProc.kill();
      }
    }
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
  const resolvedRoot = path.resolve(expandHome(root));
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
