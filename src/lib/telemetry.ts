// НОВОЕ v1.8.4 (телеметрия по запросу пользователя - "сбор ошибок +
// отправка на сервер"). Собирает JSON-отчёт из уже загруженных данных
// job'а (details.stats - engine.tsx уже получил их через
// "##ND_RESULT:...##", см. parseEngineResult) + версий приложения/движка.
// Отправка (IPC -> main-процесс -> fetch на telemetryUrl из настроек) -
// отдельно, см. sendTelemetryReport в state/engine.tsx.
import type { Job } from "./model";

export interface TelemetryReport {
  app_version: string;
  app_commit: string;
  engine_version: string;
  os: string;
  target_plugin_name: string;
  target_platform: string;
  jar_file_name: string;
  classes_total: number;
  total_methods: number;
  decompiled_methods: number;
  fallback_methods: number;
  decompiled_pct: number;
  user_comment: string;
  fallback_contexts: {
    file: string;
    method_hint: string;
    java_before: string[];
    bytecode: string[];
    java_after: string[];
  }[];
}

export async function buildTelemetryReport(job: Job, userComment: string): Promise<TelemetryReport> {
  const [guiVersion, engineVersionRes] = await Promise.all([
    window.nano.getGuiVersion().catch(() => "unknown"),
    window.nano.getEngineVersion().catch(() => ({ ok: false as const })),
  ]);
  const stats = job.details?.stats;
  return {
    app_version: guiVersion,
    // НОВОЕ: коммит подставляется Vite на этапе сборки (см. define в
    // vite.config.mts - `git rev-parse --short HEAD` в момент билда) -
    // при локальном `npm start` вне git-чекаута честно "unknown".
    app_commit: BUILD_COMMIT,
    engine_version: engineVersionRes.ok && "version" in engineVersionRes ? (engineVersionRes.version ?? "unknown") : "unknown",
    os: navigator.userAgent,
    target_plugin_name: job.pluginName ?? "(unknown plugin)",
    target_platform: stats?.platform ?? "unknown",
    jar_file_name: job.fileName,
    classes_total: stats?.classes_total ?? 0,
    total_methods: stats?.total_methods ?? 0,
    decompiled_methods: stats?.decompiled_methods ?? 0,
    fallback_methods: stats?.fallback_methods ?? 0,
    decompiled_pct: stats?.decompiled_pct ?? 0,
    user_comment: userComment,
    fallback_contexts: stats?.fallback_contexts ?? [],
  };
}

// НОВОЕ: BUILD_COMMIT - глобальная константа, подставляется Vite на этапе
// сборки (см. define в vite.config.mts). Объявление типа - в
// src/global.d.ts (declare const BUILD_COMMIT: string).
