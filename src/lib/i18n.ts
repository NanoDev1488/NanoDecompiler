// НОВОЕ v1.7.6 (реальный запрос - настраиваемый язык интерфейса en/ru).
// ЧЕСТНО: это ПЕРВЫЙ ШАГ - инфраструктура + перевод панели настроек.
// Перевод ВСЕХ экранов приложения (десятки компонентов, сотни строк на
// русском по всему коду) - отдельная большая задача, растягивать её на
// один заход означало бы либо сделать плохо везде понемногу, либо не
// сделать вообще ничего цельного. Здесь заложен МЕХАНИЗМ (словарь +
// хук useT()), которым дальше можно накрывать остальные экраны по одному,
// не меняя архитектуру.
export type Lang = "ru" | "en";

const DICT = {
  ru: {
    "settings.tab.general": "Основное",
    "settings.tab.about": "О сервисе",
    "settings.about.overview": "Обзор",
    "settings.about.features": "Возможности",
    "settings.about.team": "Команда",
    "settings.section.environment": "Окружение",
    "settings.section.language": "Язык интерфейса",
    "settings.language.ru": "Русский",
    "settings.language.en": "English",
    "settings.engine.label": "Движок",
    "settings.java.label": "Java",
    "settings.maven.label": "Maven",
    "settings.save": "Сохранить",
    "settings.cancel": "Отмена",
    "settings.install": "Установить",
    "settings.recheck": "Проверить снова",
  },
  en: {
    "settings.tab.general": "General",
    "settings.tab.about": "About",
    "settings.about.overview": "Overview",
    "settings.about.features": "Features",
    "settings.about.team": "Team",
    "settings.section.environment": "Environment",
    "settings.section.language": "Interface language",
    "settings.language.ru": "Русский",
    "settings.language.en": "English",
    "settings.engine.label": "Engine",
    "settings.java.label": "Java",
    "settings.maven.label": "Maven",
    "settings.save": "Save",
    "settings.cancel": "Cancel",
    "settings.install": "Install",
    "settings.recheck": "Check again",
  },
} satisfies Record<Lang, Record<string, string>>;

export type TranslationKey = keyof (typeof DICT)["ru"];

export function t(lang: Lang, key: TranslationKey): string {
  return DICT[lang]?.[key] ?? DICT.ru[key] ?? key;
}
