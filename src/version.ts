import packageJson from "../package.json";

/**
 * Единый источник отображаемой версии GUI на клиенте.
 * Читается напрямую из package.json для предотвращения рассинхронизации версий.
 */
export const GUI_VERSION: string = packageJson.version || "1.9.162";

