// legitimacy_check.hpp - порт resources/engine/legitimacy_check.py (v2.0,
// HANDOFF_32). Проверка легитимности плагина по внешним источникам
// (GitHub/Modrinth/SpigetApi/Hangar) + разбор полей из plugin.yml.
//
// БАГ-ФИКС/ПРАВКА БЕЗОПАСНОСТИ 1.9.6: 4-й источник ("RuSpigot",
// spigotmc.ru) ЗАМЕНЁН на Hangar (hangar.papermc.io, официальный
// плагин-репозиторий PaperMC, https://hangar.papermc.io/api-docs).
// Причина: при проверке источников в этой сессии нашлось прямое
// предупреждение сообщества (Habr) - искать плагины СТРОГО на
// spigotmc.org, а НЕ на .ru и подобных "копиях" (в т.ч. rubukkit.org),
// т.к. это известный вектор бэкдоров. Инструмент легитимности не должен
// сам держать в списке доверенных источников домен, который сообщество
// прямо называет небезопасным - живого подтверждения, что spigotmc.ru
// сейчас вообще работает и что это официальный русский мираж, а не
// фишинговая копия, тоже не нашлось. `check_ruspigot()`/
// `ruspigot_parse_result_links()` НЕ удалены (безвредный мёртвый код,
// вдруг понадобятся для другого html_search-источника) - просто
// исключён ИМЕННО spigotmc.ru из конфига по умолчанию, а поле
// результата `ruspigot` переименовано в `hangar` (frontend НЕ хранит
// это имя поля жёстко - проверено, безопасно для переименования).
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
// офлайн против оригинала (см. HANDOFF_32). Новый парсинг Hangar (1.9.6)
// компилируется чисто (`g++ -c` реально прогнан в этой сессии), но, как и
// остальные сетевые куски, вживую против настоящего hangar.papermc.io НЕ
// проверялся - структура ответа подтверждена только через веб-поиск.
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
    // НОВОЕ v1.9.13 (задача из волны 7 - различать "источник недоступен
    // из вашей сети/гео-блок" от "источник доступен, но плагин не найден"):
    // true  = прощупывающий GET на корневой эндпоинт API вернул валидный
    //         JSON-ответ ДО основного поиска (источник точно доступен).
    // false = curl вернул ошибку ИЛИ ответ не распарсился как JSON
    //         (источник недоступен: нет сети, гео-блок, таймаут).
    // nullopt = зондирование не выполнялось (источник типа HtmlSearch не
    //           имеет простого JSON-эндпоинта для probe, либо probe был
    //           намеренно пропущен).
    // Важно: probe - это ОТДЕЛЬНЫЙ лёгкий запрос к стабильному эндпоинту
    // (не к поисковому запросу). Даже если probe прошёл успешно (true), это
    // не гарантирует, что именно поисковый запрос не вернёт гео-блок -
    // но на практике если API корень отвечает валидным JSON, то и поиск
    // работает. false = практически точно означает "источник заблокирован
    // для вас" - это нужно показать пользователю как "недоступен" а не
    // "не найдено".
    std::optional<bool> host_reachable;
    std::vector<LegitimacyCandidate> candidates;
};

// HANDOFF_53: мини-язык конфигурации источников - см.
// LEGITIMACY_SITES_MINI_LANGUAGE_SPEC.md за полным описанием формата и
// тем, почему он отличается от черновика пользователя (HANDOFF_39/23).
enum class SiteKind { GithubApi, ModrinthApi, SpigetApi, HtmlSearch, HangarApi };

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
    LegitimacySourceResult hangar;
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
