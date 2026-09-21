import { execSync } from "child_process";
import path from "path";
import tailwindcss from "@tailwindcss/vite";
import react from "@vitejs/plugin-react";
import { defineConfig } from "vite";

// НОВОЕ v1.8.4 (телеметрия - "коммит с гитхаба берётся"): короткий hash
// текущего коммита, зашивается в сборку через define (см. ниже) - honest
// fallback, если сборка идёт НЕ из git-чекаута (например, распакованный
// исходник без .git) - тогда просто "unknown", а не падение всей сборки.
function getBuildCommit(): string {
  try {
    return execSync("git rev-parse --short HEAD", { cwd: __dirname, stdio: ["ignore", "pipe", "ignore"] })
      .toString()
      .trim();
  } catch {
    return "unknown (not built from git checkout)";
  }
}

export default defineConfig({
  plugins: [react(), tailwindcss()],
  base: "./",
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "src"),
    },
  },
  define: {
    // Глобальная константа-строка (JSON.stringify - иначе Vite вставит как
    // голый идентификатор, а не строковый литерал) - тип объявлен в
    // src/global.d.ts (declare const BUILD_COMMIT: string).
    BUILD_COMMIT: JSON.stringify(getBuildCommit()),
  },
  build: {
    outDir: "dist",
  },
});
