// legitimacy_check.cpp - см. legitimacy_check.hpp (и ОБЯЗАТЕЛЬНО оговорку
// там же про отсутствие живого сетевого тестирования в этой сессии).
#include "legitimacy_check.hpp"

#include <array>
#include <cstdio>
#include <future>
#include <memory>
#include <regex>
#include <sstream>

#include "json_value.hpp"

namespace nd {

// ---------------- fields_from_plugin_yml (офлайн, полностью тестируемо) ----------------

PluginYmlFields fields_from_plugin_yml(const std::string& plugin_yml_text) {
    PluginYmlFields result;
    if (plugin_yml_text.empty()) return result;

    // re.search(r"^website:\s*['\"]?([^'\"\n]+)['\"]?\s*$", text, re.M)
    {
        static const std::regex re(R"(^website:\s*['"]?([^'"\n]+)['"]?\s*$)", std::regex::multiline);
        std::smatch m;
        if (std::regex_search(plugin_yml_text, m, re)) {
            std::string v = m[1].str();
            // .strip() - убираем ведущие/хвостовые пробелы
            size_t a = v.find_first_not_of(" \t");
            size_t b = v.find_last_not_of(" \t");
            result.website = (a == std::string::npos) ? "" : v.substr(a, b - a + 1);
        }
    }
    // re.search(r"^author:\s*['\"]?([^'\"\n]+)['\"]?\s*$", text, re.M)
    {
        static const std::regex re(R"(^author:\s*['"]?([^'"\n]+)['"]?\s*$)", std::regex::multiline);
        std::smatch m;
        if (std::regex_search(plugin_yml_text, m, re)) {
            std::string v = m[1].str();
            size_t a = v.find_first_not_of(" \t");
            size_t b = v.find_last_not_of(" \t");
            result.authors = {(a == std::string::npos) ? "" : v.substr(a, b - a + 1)};
        } else {
            // re.search(r"^authors:\s*\[([^\]]*)\]", text, re.M)
            static const std::regex re2(R"(^authors:\s*\[([^\]]*)\])", std::regex::multiline);
            std::smatch m2;
            if (std::regex_search(plugin_yml_text, m2, re2)) {
                std::string inner = m2[1].str();
                // a.strip().strip("'\"") for a in inner.split(",") if a.strip()
                std::stringstream ss(inner);
                std::string item;
                while (std::getline(ss, item, ',')) {
                    size_t a = item.find_first_not_of(" \t");
                    size_t b = item.find_last_not_of(" \t");
                    if (a == std::string::npos) continue;  // a.strip() пусто -> пропускаем (if a.strip())
                    std::string stripped = item.substr(a, b - a + 1);
                    // .strip("'\"") - убираем ведущие/хвостовые кавычки (любые из ' ")
                    size_t s0 = stripped.find_first_not_of("'\"");
                    size_t s1 = stripped.find_last_not_of("'\"");
                    if (s0 == std::string::npos) {
                        result.authors.push_back("");
                    } else {
                        result.authors.push_back(stripped.substr(s0, s1 - s0 + 1));
                    }
                }
            }
        }
    }
    return result;
}

// ---------------- HTTP через curl CLI (см. оговорку в .hpp) ----------------

namespace {

// Возвращает std::nullopt на любой ошибке (таймаут/сеть/ненулевой код
// возврата curl) - зеркалит `except Exception: return None` в оригинале.
std::optional<std::string> http_get(const std::string& url, double timeout_sec, const std::string& accept) {
    // Экранирование url для shell: используем одинарные кавычки, экранируя
    // встречающиеся одинарные кавычки как '\'' - url формируется нами же из
    // urlencoded-компонентов (см. вызовы ниже), так что в норме туда не
    // попадёт ничего опасного, но экранируем в любом случае, а не доверяем.
    std::string escaped;
    for (char c : url) {
        if (c == '\'') escaped += "'\\''";
        else escaped += c;
    }
    std::ostringstream cmd;
    cmd << "curl -s -m " << timeout_sec << " -A 'Mozilla/5.0 (NanoDecompiler-LegitimacyCheck/1.1)' "
        << "-H 'Accept: " << accept << "' '" << escaped << "' 2>/dev/null";
    FILE* pipe = popen(cmd.str().c_str(), "r");
    if (!pipe) return std::nullopt;
    std::string out;
    std::array<char, 4096> buf{};
    size_t nread;
    while ((nread = fread(buf.data(), 1, buf.size(), pipe)) > 0) {
        out.append(buf.data(), nread);
    }
    int rc = pclose(pipe);
    if (rc != 0) return std::nullopt;
    return out;
}

std::optional<JsonValue> http_get_json(const std::string& url, double timeout_sec) {
    auto raw = http_get(url, timeout_sec, "application/json");
    if (!raw.has_value()) return std::nullopt;
    return json_parse(*raw);
}

// Минимальный urlencode (RFC 3986 unreserved characters оставляем как есть,
// остальное - %XX) - аналог urllib.parse.quote с настройками по умолчанию
// (сохраняет "/", как и urllib.parse.quote(safe="/") по умолчанию).
std::string url_quote(const std::string& s, bool safe_slash = false) {
    static const char* hex = "0123456789ABCDEF";
    std::string out;
    for (unsigned char c : s) {
        bool unreserved = std::isalnum(c) || c == '-' || c == '_' || c == '.' || c == '~';
        if (unreserved || (safe_slash && c == '/')) {
            out += static_cast<char>(c);
        } else {
            out += '%';
            out += hex[c >> 4];
            out += hex[c & 0xF];
        }
    }
    return out;
}

}  // namespace

// ---------------- check_github / check_modrinth / check_spigot ----------------

LegitimacySourceResult check_github(const std::string& plugin_name, double timeout_sec) {
    LegitimacySourceResult out;
    if (plugin_name.empty()) return out;
    std::string q = url_quote(plugin_name + " in:name", true);
    auto data = http_get_json("https://api.github.com/search/repositories?q=" + q + "&per_page=5", timeout_sec);
    if (!data.has_value() || !data->is_object() || data->get("items") == nullptr) return out;
    out.checked = true;
    const JsonValue* items = data->get("items");
    if (items && items->is_array()) {
        size_t k = 0;
        for (auto& item : *items->arr_v) {
            if (k >= 5) break;
            const JsonValue* full_name = item.get("full_name");
            const JsonValue* html_url = item.get("html_url");
            if (!full_name || !html_url || !full_name->is_string() || !html_url->is_string()) continue;
            LegitimacyCandidate c;
            c.full_name = full_name->str_v;
            c.url = html_url->str_v;
            const JsonValue* stars = item.get("stargazers_count");
            c.stars = (stars && stars->kind == JsonValue::Kind::Number) ? static_cast<int64_t>(stars->num_v) : 0;
            out.candidates.push_back(std::move(c));
            k += 1;
        }
    }
    out.found = !out.candidates.empty();
    return out;
}

LegitimacySourceResult check_modrinth(const std::string& plugin_name, double timeout_sec) {
    LegitimacySourceResult out;
    if (plugin_name.empty()) return out;
    std::string q = url_quote(plugin_name, true);
    auto data = http_get_json("https://api.modrinth.com/v2/search?query=" + q + "&limit=5", timeout_sec);
    if (!data.has_value() || !data->is_object() || data->get("hits") == nullptr) return out;
    out.checked = true;
    const JsonValue* hits = data->get("hits");
    if (hits && hits->is_array()) {
        size_t k = 0;
        for (auto& item : *hits->arr_v) {
            if (k >= 5) break;
            const JsonValue* title = item.get("title");
            const JsonValue* slug = item.get("slug");
            if (!title || !slug || !title->is_string() || !slug->is_string()) continue;
            LegitimacyCandidate c;
            c.full_name = title->str_v;
            c.url = "https://modrinth.com/plugin/" + slug->str_v;
            const JsonValue* downloads = item.get("downloads");
            c.stars = (downloads && downloads->kind == JsonValue::Kind::Number) ? static_cast<int64_t>(downloads->num_v) : 0;
            out.candidates.push_back(std::move(c));
            k += 1;
        }
    }
    out.found = !out.candidates.empty();
    return out;
}

LegitimacySourceResult check_spigot(const std::string& plugin_name, double timeout_sec) {
    LegitimacySourceResult out;
    if (plugin_name.empty()) return out;
    std::string q = url_quote(plugin_name, true);
    auto data = http_get_json("https://api.spiget.org/v2/search/resources/" + q + "?field=name&size=5", timeout_sec);
    if (!data.has_value() || !data->is_array()) return out;
    out.checked = true;
    size_t k = 0;
    for (auto& item : *data->arr_v) {
        if (k >= 5) break;
        const JsonValue* id = item.get("id");
        if (!id || id->kind != JsonValue::Kind::Number) continue;
        int64_t rid = static_cast<int64_t>(id->num_v);
        LegitimacyCandidate c;
        const JsonValue* name = item.get("name");
        c.full_name = (name && name->is_string()) ? name->str_v : ("resource #" + std::to_string(rid));
        c.url = "https://www.spigotmc.org/resources/" + std::to_string(rid) + "/";
        const JsonValue* downloads = item.get("downloads");
        c.stars = (downloads && downloads->kind == JsonValue::Kind::Number) ? static_cast<int64_t>(downloads->num_v) : 0;
        out.candidates.push_back(std::move(c));
        k += 1;
    }
    out.found = !out.candidates.empty();
    return out;
}

// ---------------- RuSpigot (regex по HTML - офлайн тестируемо отдельно) ----------------

std::vector<std::pair<std::string, std::string>> ruspigot_parse_result_links(const std::string& html) {
    // re.compile(r'<a[^>]+class="[^"]*resource-tile-title[^"]*"[^>]+href="([^"]+)"[^>]*>\s*([^<]+?)\s*</a>', re.I)
    // Порядок групп в Python-регэкспе: (href, title) - возвращаем в том же порядке.
    static const std::regex re(
        R"RE(<a[^>]+class="[^"]*resource-tile-title[^"]*"[^>]+href="([^"]+)"[^>]*>\s*([^<]+?)\s*</a>)RE",
        std::regex::icase);
    std::vector<std::pair<std::string, std::string>> out;
    auto begin = std::sregex_iterator(html.begin(), html.end(), re);
    auto end = std::sregex_iterator();
    for (auto it = begin; it != end; ++it) {
        out.emplace_back((*it)[1].str(), (*it)[2].str());
    }
    return out;
}

LegitimacySourceResult check_ruspigot(const std::string& plugin_name, double timeout_sec) {
    LegitimacySourceResult out;
    if (plugin_name.empty()) return out;
    std::string q = url_quote(plugin_name, true);
    auto html = http_get("https://spigotmc.ru/resources/?q=" + q, timeout_sec, "text/html");
    if (!html.has_value()) return out;
    out.checked = true;
    auto links = ruspigot_parse_result_links(*html);
    size_t k = 0;
    for (auto& [href, title_raw] : links) {
        if (k >= 5) break;
        // title.strip()
        std::string title = title_raw;
        size_t a = title.find_first_not_of(" \t\r\n");
        size_t b = title.find_last_not_of(" \t\r\n");
        title = (a == std::string::npos) ? "" : title.substr(a, b - a + 1);
        if (title.empty()) continue;
        LegitimacyCandidate c;
        c.full_name = title;
        c.url = (href.rfind("http", 0) == 0) ? href : ("https://spigotmc.ru" + href);
        c.stars = 0;
        out.candidates.push_back(std::move(c));
        k += 1;
    }
    out.found = !out.candidates.empty();
    return out;
}

// ---------------- run_legitimacy_check / format_for_console ----------------

LegitimacyCheckResult run_legitimacy_check(const std::string& plugin_name, const std::string& plugin_yml_text) {
    LegitimacyCheckResult result;
    result.plugin_yml_fields = fields_from_plugin_yml(plugin_yml_text);

    // Параллельно, как и в оригинале (threading.Thread) - std::async с
    // политикой launch::async гарантирует реальный отдельный поток.
    auto fut_github = std::async(std::launch::async, [&] { return check_github(plugin_name); });
    auto fut_modrinth = std::async(std::launch::async, [&] { return check_modrinth(plugin_name); });
    auto fut_spigot = std::async(std::launch::async, [&] { return check_spigot(plugin_name); });
    auto fut_ruspigot = std::async(std::launch::async, [&] { return check_ruspigot(plugin_name); });

    auto wait_or_default = [](std::future<LegitimacySourceResult>& f) -> LegitimacySourceResult {
        if (f.wait_for(std::chrono::milliseconds(8000)) == std::future_status::ready) {
            try {
                return f.get();
            } catch (...) {
                return LegitimacySourceResult{};
            }
        }
        return LegitimacySourceResult{};  // общий верхний предел 8с, как и в оригинале
    };
    result.github = wait_or_default(fut_github);
    result.modrinth = wait_or_default(fut_modrinth);
    result.spigot = wait_or_default(fut_spigot);
    result.ruspigot = wait_or_default(fut_ruspigot);
    return result;
}

std::optional<std::string> format_for_console(const LegitimacyCheckResult& result) {
    std::vector<std::string> lines;
    auto& fields = result.plugin_yml_fields;
    if (fields.website.has_value() || !fields.authors.empty()) {
        lines.push_back("[*] Поля в plugin.yml:");
        if (fields.website.has_value()) lines.push_back("    website: " + *fields.website);
        if (!fields.authors.empty()) {
            std::string joined;
            for (size_t i = 0; i < fields.authors.size(); ++i) {
                if (i) joined += ", ";
                joined += fields.authors[i];
            }
            lines.push_back("    author(s): " + joined);
        }
    }
    struct Src { const char* key; const char* label; const LegitimacySourceResult* r; };
    Src sources[] = {
        {"github", "GitHub", &result.github},
        {"modrinth", "Modrinth", &result.modrinth},
        {"spigot", "SpigotMC", &result.spigot},
        {"ruspigot", "RuSpigot", &result.ruspigot},
    };
    for (auto& s : sources) {
        if (!s.r->checked) {
            lines.push_back(std::string("[*] ") + s.label + ": проверка не удалась (нет сети или сервис недоступен)");
        } else if (s.r->found) {
            lines.push_back(std::string("[*] ") + s.label + ": найдены похожие проекты -");
            for (auto& c : s.r->candidates) {
                lines.push_back("    " + c.full_name + " (" + c.url + ")");
            }
        } else {
            lines.push_back(std::string("[*] ") + s.label + ": похожих проектов не найдено");
        }
    }
    if (lines.empty()) return std::nullopt;
    std::string out;
    for (size_t i = 0; i < lines.size(); ++i) {
        if (i) out += "\n";
        out += lines[i];
    }
    return out;
}

}  // namespace nd
