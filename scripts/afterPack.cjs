// afterPack-хук electron-builder - урезает размер собранного клиента (был
// 169-175 МБ по жалобе пользователя). Реальная причина размера - сам
// Electron-рантайм (Chromium+Node.js, ~130-180 МБ у ЛЮБОГО Electron-
// приложения вне зависимости от кода) - это не убрать без отказа от
// Electron целиком (Tauri и т.п. - совсем другой, намного более
// рискованный проект, не делается в рамках одной правки).
//
// Но один реальный и безопасный кусок веса МОЖНО срезать: Chromium
// зашивает ~50+ файлов локализации (.pak) под КАЖДЫЙ язык интерфейса
// браузерных диалогов (не самого нашего GUI - React-часть не трогается
// вообще) - приложению не нужны японская/тайская/иврит и т.п. локали,
// раз весь GUI и так только на русском.
const fs = require("fs");
const path = require("path");

const KEEP_LOCALES = new Set(["en-US.pak", "ru.pak"]);

exports.default = async function afterPack(context) {
  const localesDir = path.join(context.appOutDir, "locales");
  if (!fs.existsSync(localesDir)) return;

  let removedCount = 0;
  let removedBytes = 0;
  for (const file of fs.readdirSync(localesDir)) {
    if (KEEP_LOCALES.has(file)) continue;
    const full = path.join(localesDir, file);
    try {
      const size = fs.statSync(full).size;
      fs.unlinkSync(full);
      removedCount += 1;
      removedBytes += size;
    } catch {
      // не критично - пропускаем файл, если почему-то не удалось удалить
    }
  }
  console.log(
    `[afterPack] удалено ${removedCount} неиспользуемых локалей Chromium, освобождено ~${Math.round(removedBytes / 1024 / 1024)} МБ`
  );
};
