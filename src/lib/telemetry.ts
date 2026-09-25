// НОВОЕ v1.8.4 (телеметрия по запросу пользователя - "сбор ошибок +
// отправка на сервер"). Собирает JSON-отчёт из уже загруженных данных
// job'а (details.stats - engine.tsx уже получил их через
// "##ND_RESULT:...##", см. parseEngineResult) + версий приложения/движка.
// Отправка (IPC -> main-процесс -> fetch на telemetryUrl из настроек) -
// отдельно, см. sendTelemetryReport в state/engine.tsx.
import type { Job, JobDetails } from "./model";

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

// НОВОЕ v1.9.5: версии приложения/движка нужны и job-отчёту, и
// произвольному багрепорту (см. buildFreeformBugReport ниже) - вынесено,
// чтобы не дублировать.
async function getAppVersions(): Promise<{ app_version: string; engine_version: string }> {
  const [guiVersion, engineVersionRes] = await Promise.all([
    window.nano.getGuiVersion().catch(() => "unknown"),
    window.nano.getEngineVersion().catch(() => ({ ok: false as const })),
  ]);
  return {
    app_version: guiVersion,
    engine_version: engineVersionRes.ok && "version" in engineVersionRes ? (engineVersionRes.version ?? "unknown") : "unknown",
  };
}

// БАГ-ФИКС v1.9.11: detailsOverride - см. jobDetailsRef в state/engine.tsx.
// job.details (из React-состояния) может быть на пару рендеров позади
// самой свежей "##ND_RESULT:...##" из лога - при автоотправке сразу по
// завершении job'а (finalize()) это раньше могло привести к отправке
// отчёта с пустой статистикой (или к пропуску отправки вовсе, если
// решение "отправлять или нет" тоже принималось по этому же устаревшему
// job.details). Если override передан - используем его вместо job.details.
export async function buildTelemetryReport(job: Job, userComment: string, detailsOverride?: JobDetails): Promise<TelemetryReport> {
  const versions = await getAppVersions();
  const stats = (detailsOverride ?? job.details)?.stats;
  return {
    ...versions,
    // НОВОЕ: коммит подставляется Vite на этапе сборки (см. define в
    // vite.config.mts - `git rev-parse --short HEAD` в момент билда) -
    // при локальном `npm start` вне git-чекаута честно "unknown".
    app_commit: BUILD_COMMIT,
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

// НОВОЕ v1.9.5 (прямая просьба пользователя - "отдельная кнопка для
// багрепорта, не от декомпиляции, а просто лично от пользователя"): та же
// форма отчёта, что и у fallback-отчёта (единый формат на стороне
// сервера/Telegram), но НЕ привязана ни к какому job'у - все
// декомпиляционные поля честно нулевые/заглушки, а не выдуманные.
// jar_file_name специально НЕ "(unknown file)" - используется как имя
// файла-вложения на сервере (см. server.js), "(общий отчёт)" читается
// понятнее в имени файла, чем "unknown file".
export async function buildFreeformBugReport(userComment: string): Promise<TelemetryReport> {
  const versions = await getAppVersions();
  return {
    ...versions,
    app_commit: BUILD_COMMIT,
    os: navigator.userAgent,
    target_plugin_name: "(общий отчёт, не привязан к плагину)",
    // БАГ-ФИКС v1.9.11 (жалоба "'платформа' пишет n/a после отправки"):
    // ПРОВЕРЕНО - это НЕ баг, поле честно пустое по design, т.к. у
    // произвольного багрепорта нет привязанного job'а/плагина (см.
    // комментарий класса выше). Голое "n/a" в отчёте выглядело как
    // недостающее значение/баг сбора данных - заменено на явную фразу,
    // чтобы человек, читающий отчёт (в т.ч. сам пользователь на
    // сервере/в Telegram), сразу понимал ПОЧЕМУ платформы нет, а не
    // подозревал сломанный сбор телеметрии.
    target_platform: "(нет — общий отчёт без привязки к плагину)",
    jar_file_name: "(общий отчёт)",
    classes_total: 0,
    total_methods: 0,
    decompiled_methods: 0,
    fallback_methods: 0,
    decompiled_pct: 0,
    user_comment: userComment,
    fallback_contexts: [],
  };
}

// НОВОЕ: BUILD_COMMIT - глобальная константа, подставляется Vite на этапе
// сборки (см. define в vite.config.mts). Объявление типа - в
// src/global.d.ts (declare const BUILD_COMMIT: string).
