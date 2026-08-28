// legitimacy_check.hpp - порт resources/engine/legitimacy_check.py (v2.0,
// HANDOFF_32). Проверка легитимности плагина по внешним источникам
// (GitHub/Modrinth/SpigotMC/RuSpigot) + разбор полей из plugin.yml.
//
// ВАЖНАЯ ОГОВОРКА ПРО ЭТУ ЧАСТЬ ПОРТА: сетевые функции (`http_get` и всё,
// что на нём построено - check_github/check_modrinth/check_spigot/
// check_ruspigot/run_legitimacy_check) написаны и синтаксически корректны,
// НО не могли быть протестированы вживую в этой офлайн-песочнице (исходящая
// сеть отключена в инструментах контейнера) - в отличие от всех предыдущих
// модулей, где паритет с Python подтверждён построчной регрессией. Перед
// боевым использованием эту часть стоит явно проверить на реальной машине
// с сетью. ЧИСТАЯ (без сети) логика - `fields_from_plugin_yml`, разбор
// JSON-ответов, regex RuSpigot, `format_for_console` - протестирована
// офлайн против оригинала (см. HANDOFF_32).
#pragma once

#include <cstdint>  // БАГ-ФИКС: MinGW/Windows не тянет int64_t транзитивно через другие заголовки, как это молча делает libstdc++ на Linux - см. ошибку сборки Windows-раннера в этой сессии.
#include <optional>
#include <string>
#include <vector>

namespace nd {

struct PluginYmlFields {
    std::optional<std::string> website;
    std::vector<std::string> authors;
};

PluginYmlFields fields_from_plugin_yml(const std::string& plugin_yml_text);

struct LegitimacyCandidate {
    std::string full_name;
    std::string url;
    int64_t stars = 0;
    // HANDOFF_53: заполняется только для источников, где хэш получить
    // дёшево (см. LEGITIMACY_SITES_MINI_LANGUAGE_SPEC.md) - nullopt,
    // если для этого источника сравнение хэшей не реализовано, или
    // конкретно этот кандидат не удалось проверить (сеть/формат ответа).
    std::optional<std::string> sha256_hex;
};

struct LegitimacySourceResult {
    bool checked = false;
    bool found = false;
    std::vector<LegitimacyCandidate> candidates;
};

// HANDOFF_53: мини-язык конфигурации источников - см.
// LEGITIMACY_SITES_MINI_LANGUAGE_SPEC.md за полным описанием формата и
// тем, почему он отличается от черновика пользователя (HANDOFF_39/23).
enum class SiteKind { GithubApi, ModrinthApi, SpigetApi, HtmlSearch };

struct SiteConfig {
    std::string label;       // для сообщений пользователю ("GitHub", "Modrinth", ...)
    SiteKind kind;
    std::string site;        // только для подписи - НЕ участвует в построении URL
    std::string query;       // полный URL-шаблон с {plugin_name}
    std::optional<std::string> release_query;  // полный URL-шаблон с {full_name}/{slug}, per-candidate
};

std::optional<SiteKind> parse_site_kind(const std::string& s);

// Отрезает версионный хвост ("MyPlugin-v1.2.3" -> "MyPlugin") - см. спеку.
// Возвращает "", если результат короче 2 символов (сигнал "не искать").
std::string clean_plugin_name_for_search(const std::string& raw_name);

// Разбирает JSON-текст конфига (массив объектов) в vector<SiteConfig> -
// невалидные записи (нет обязательных полей, неизвестный kind) - молча
// пропускаются (не валят весь конфиг из-за одной плохой записи).
std::vector<SiteConfig> parse_legitimacy_sites_config(const std::string& json_text);

// Встроенный дефолт (4 источника, эквивалент HANDOFF_32-версии) - см.
// спеку, пример JSON. Используется, если сеть недоступна И локального
// кэша ещё нет (самый первый запуск на новой машине).
std::vector<SiteConfig> default_legitimacy_sites_config();

// Скачивает свежий конфиг (см. LEGITIMACY_SITES_CONFIG_URL в .cpp),
// кэширует локально при успехе, иначе - локальный кэш, иначе - дефолт.
// НИКОГДА не возвращает пустой vector (см. default_legitimacy_sites_config).
std::vector<SiteConfig> load_legitimacy_sites_config();

// Универсальная проверка ОДНОГО источника (замена check_github/
// check_modrinth/check_spigot/check_ruspigot по отдельности - те функции
// сохранены ниже для обратной совместимости тестов HANDOFF_32, но
// run_legitimacy_check теперь использует ИМЕННО эту функцию с конфигом
// из load_legitimacy_sites_config()). jar_sha256_hex - для сравнения
// хэшей (см. спеку); пустая строка - сравнение хэшей пропускается.
LegitimacySourceResult check_site(const SiteConfig& cfg, const std::string& plugin_name, const std::string& jar_sha256_hex,
                                   double timeout_sec = 4.0);

// Сетевые - см. оговорку выше про отсутствие живого тестирования.
LegitimacySourceResult check_github(const std::string& plugin_name, double timeout_sec = 4.0);
LegitimacySourceResult check_modrinth(const std::string& plugin_name, double timeout_sec = 4.0);
LegitimacySourceResult check_spigot(const std::string& plugin_name, double timeout_sec = 4.0);
LegitimacySourceResult check_ruspigot(const std::string& plugin_name, double timeout_sec = 4.0);

// HANDOFF_53: итог сравнения SHA-256 проверяемого jar с хэшами, полученными
// от источников (только те, что их вообще дают - см. спеку). Формат
// сообщения пользователю ("не соответствует: сайт1, сайт2, НО
// соответствует: сайт3") - см. format_for_console.
struct HashComparisonResult {
    std::vector<std::string> matching;     // метки источников (label), где хэш совпал
    std::vector<std::string> mismatching;  // где хэш получен, но НЕ совпал
};

struct LegitimacyCheckResult {
    PluginYmlFields plugin_yml_fields;
    LegitimacySourceResult github;
    LegitimacySourceResult modrinth;
    LegitimacySourceResult spigot;
    LegitimacySourceResult ruspigot;
    // HANDOFF_53: nullopt, если jar_path не передан в run_legitimacy_check
    // (сравнение хэшей не запрашивалось) - см. jar_path параметр ниже.
    std::optional<HashComparisonResult> hash_comparison;
};

// jar_path - НОВЫЙ параметр (HANDOFF_53), нужен для сравнения хэшей (см.
// спеку) - опционален (по умолчанию "") для обратной совместимости
// старых вызовов; при пустом jar_path сравнение хэшей просто не делается
// (hash_comparison остаётся nullopt), остальное поведение не меняется.
LegitimacyCheckResult run_legitimacy_check(const std::string& plugin_name, const std::string& plugin_yml_text,
                                            const std::string& jar_path = "");

std::optional<std::string> format_for_console(const LegitimacyCheckResult& result);

// ---- офлайн-тестируемые внутренности, вынесены в заголовок для тестов ----

// Извлекает до 5 (full_name/url/title, href) пар из HTML страницы поиска
// RuSpigot по тому же regex, что и оригинал - используется check_ruspigot,
// но тестируется отдельно на синтетическом HTML (без сети).
std::vector<std::pair<std::string, std::string>> ruspigot_parse_result_links(const std::string& html);

}  // namespace nd
