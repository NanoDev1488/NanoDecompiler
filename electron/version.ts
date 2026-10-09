import { app } from "electron";
import * as path from "path";
import * as fs from "fs";

/**
 * Единый источник отображаемой версии GUI в Electron main/updater процессах.
 * Читается из app.getVersion() или package.json, исключая устаревший хардкод.
 */
function resolveGuiVersion(): string {
  try {
    if (app && typeof app.getVersion === "function") {
      const v = app.getVersion();
      if (v && v !== "0.0.0") return v;
    }
  } catch {}
  try {
    const pkgPath = path.resolve(__dirname, "../package.json");
    if (fs.existsSync(pkgPath)) {
      const pkg = JSON.parse(fs.readFileSync(pkgPath, "utf-8"));
      if (pkg.version) return pkg.version;
    }
  } catch {}
  return "1.9.162";
}

export const GUI_VERSION = resolveGuiVersion();

