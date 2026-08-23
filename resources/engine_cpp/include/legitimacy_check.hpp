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
};

struct LegitimacySourceResult {
    bool checked = false;
    bool found = false;
    std::vector<LegitimacyCandidate> candidates;
};

// Сетевые - см. оговорку выше про отсутствие живого тестирования.
LegitimacySourceResult check_github(const std::string& plugin_name, double timeout_sec = 4.0);
LegitimacySourceResult check_modrinth(const std::string& plugin_name, double timeout_sec = 4.0);
LegitimacySourceResult check_spigot(const std::string& plugin_name, double timeout_sec = 4.0);
LegitimacySourceResult check_ruspigot(const std::string& plugin_name, double timeout_sec = 4.0);

struct LegitimacyCheckResult {
    PluginYmlFields plugin_yml_fields;
    LegitimacySourceResult github;
    LegitimacySourceResult modrinth;
    LegitimacySourceResult spigot;
    LegitimacySourceResult ruspigot;
};

LegitimacyCheckResult run_legitimacy_check(const std::string& plugin_name, const std::string& plugin_yml_text);

std::optional<std::string> format_for_console(const LegitimacyCheckResult& result);

// ---- офлайн-тестируемые внутренности, вынесены в заголовок для тестов ----

// Извлекает до 5 (full_name/url/title, href) пар из HTML страницы поиска
// RuSpigot по тому же regex, что и оригинал - используется check_ruspigot,
// но тестируется отдельно на синтетическом HTML (без сети).
std::vector<std::pair<std::string, std::string>> ruspigot_parse_result_links(const std::string& html);

}  // namespace nd
