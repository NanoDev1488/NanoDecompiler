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

// БАГ-ФИКС: раньше был один путь "appOutDir/locales" - верно для Windows/
// Linux (там appOutDir - это плоская распакованная папка приложения), но
// НЕВЕРНО для macOS - там путь до Resources лежит ВНУТРИ .app-бандла
// (appOutDir/ProductName.app/Contents/Resources/locales), а не рядом с
// appOutDir напрямую - на macOS хук раньше молча не находил папку и не
// удалял ничего (fs.existsSync -> false -> ранний return), пользователь
// не видел уменьшения размера именно на этой платформе.
function localesDirFor(context) {
  if (context.electronPlatformName === "darwin") {
    const productFilename = context.packager.appInfo.productFilename;
    return path.join(context.appOutDir, `${productFilename}.app`, "Contents", "Resources", "locales");
  }
  return path.join(context.appOutDir, "locales");
}

exports.default = async function afterPack(context) {
  const localesDir = localesDirFor(context);
  if (!fs.existsSync(localesDir)) {
    console.log(`[afterPack] папка локалей не найдена по пути ${localesDir} - пропускаю (платформа: ${context.electronPlatformName})`);
    return;
  }

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
    } catch (e) {
      console.log(`[afterPack] не удалось удалить ${full}: ${e.message}`);
    }
  }
  console.log(
    `[afterPack] (${context.electronPlatformName}) удалено ${removedCount} неиспользуемых локалей Chromium из ${localesDir}, освобождено ~${Math.round(removedBytes / 1024 / 1024)} МБ`
  );
};
