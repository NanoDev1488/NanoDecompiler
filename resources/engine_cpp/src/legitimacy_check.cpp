// legitimacy_check.cpp - см. legitimacy_check.hpp (и ОБЯЗАТЕЛЬНО оговорку
// там же про отсутствие живого сетевого тестирования в этой сессии).
#include "legitimacy_check.hpp"

#include <array>
#include <cctype>
#include <cstdio>
#include <filesystem>
#include <fstream>
#include <future>
#include <memory>
#include <random>
#include <regex>
#include <sstream>

#include "json_value.hpp"
#include "sha256.hpp"
#include "toolinstaller.hpp"

namespace fs = std::filesystem;

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

// ---------------- HANDOFF_53: hex-кодирование + скачивание+хэш файла ----------------

namespace {

std::string hex_encode(const std::array<uint8_t, 32>& bytes) {
    static const char* hexd = "0123456789abcdef";
    std::string out;
    out.reserve(64);
    for (uint8_t b : bytes) {
        out += hexd[b >> 4];
        out += hexd[b & 0xF];
    }
    return out;
}

std::string random_hex_local(int n) {
    static std::random_device rd;
    static std::mt19937_64 gen(rd());
    std::uniform_int_distribution<int> dist(0, 15);
    static const char* hexd = "0123456789abcdef";
    std::string out;
    for (int i = 0; i < n; ++i) out += hexd[dist(gen)];
    return out;
}

std::string sha256_of_file(const std::string& path) {
    std::ifstream f(path, std::ios::binary);
    std::vector<uint8_t> data((std::istreambuf_iterator<char>(f)), std::istreambuf_iterator<char>());
    return hex_encode(sha256(data));
}

// Скачивает url во временный файл (лимит через curl --max-filesize) и
// хэширует - используется ТОЛЬКО для GitHub-ассетов (см. спеку - у
// Modrinth хэш и так уже в JSON-ответе, скачивать сам файл не нужно).
// nullopt при любой ошибке (сеть/лимит размера/curl не найден) - как и
// весь остальной сетевой код здесь, тихо деградирует, а не падает.
std::optional<std::string> download_and_sha256(const std::string& url, uint64_t max_bytes, double timeout_sec) {
    std::string tmp = (fs::temp_directory_path() / ("nd_legitimacy_dl_" + random_hex_local(16))).string();
    std::string escaped;
    for (char c : url) {
        if (c == '\'')
            escaped += "'\\''";
        else
            escaped += c;
    }
    std::ostringstream cmd;
    cmd << "curl -sL -m " << timeout_sec << " --max-filesize " << max_bytes << " -A 'Mozilla/5.0 (NanoDecompiler-LegitimacyCheck/1.1)' "
        << "-o '" << tmp << "' '" << escaped << "' 2>/dev/null";
    int rc = std::system(cmd.str().c_str());
    std::error_code ec;
    bool exists = fs::is_regular_file(tmp, ec);
    if (rc != 0 || !exists) {
        fs::remove(tmp, ec);
        return std::nullopt;
    }
    std::string hash = sha256_of_file(tmp);
    fs::remove(tmp, ec);
    return hash;
}

// {plugin_name}/{full_name}/{slug} - буквальная подстановка (НЕ
// шаблонизатор общего назначения - ровно эти три плейсхолдера, см. спеку).
std::string substitute_placeholder(const std::string& tmpl, const std::string& placeholder, const std::string& value) {
    std::string out = tmpl;
    size_t pos;
    while ((pos = out.find(placeholder)) != std::string::npos) out.replace(pos, placeholder.size(), value);
    return out;
}

}  // namespace

// ---------------- HANDOFF_53: мини-язык - имя, конфиг, generic-проверка ----------------

std::optional<SiteKind> parse_site_kind(const std::string& s) {
    if (s == "github_api") return SiteKind::GithubApi;
    if (s == "modrinth_api") return SiteKind::ModrinthApi;
    if (s == "spiget_api") return SiteKind::SpigetApi;
    if (s == "html_search") return SiteKind::HtmlSearch;
    return std::nullopt;
}

std::string clean_plugin_name_for_search(const std::string& raw_name) {
    std::string name = raw_name;
    // .strip()
    {
        size_t a = name.find_first_not_of(" \t\r\n");
        size_t b = name.find_last_not_of(" \t\r\n");
        name = (a == std::string::npos) ? "" : name.substr(a, b - a + 1);
    }
    // Отрезаем хвостовой версионный суффикс: разделитель (пробел/дефис/
    // подчёркивание) + опциональная "v"/"V" + цифры/точки, В КОНЦЕ строки.
    // "MyPlugin-v1.2.3" -> "MyPlugin", "MyPlugin_2" -> "MyPlugin",
    // "MyPluginV2" (без разделителя) - НЕ трогаем (см. спеку - только
    // отделённый разделителем суффикс, иначе рискуем испортить настоящее
    // имя вроде "Zone2" или "Node1").
    static const std::regex version_suffix_re(R"([ _\-]+[vV]?\d[\d.]*$)");
    name = std::regex_replace(name, version_suffix_re, "");
    if (name.size() < 2) return "";
    return name;
}

std::vector<SiteConfig> default_legitimacy_sites_config() {
    return {
        {"GitHub", SiteKind::GithubApi, "github.com", "https://api.github.com/search/repositories?q={plugin_name}+in:name&per_page=5",
         std::optional<std::string>("https://api.github.com/repos/{full_name}/releases/latest")},
        {"Modrinth", SiteKind::ModrinthApi, "modrinth.com", "https://api.modrinth.com/v2/search?query={plugin_name}&limit=5",
         std::optional<std::string>("https://api.modrinth.com/v2/project/{slug}/version")},
        {"SpigotMC", SiteKind::SpigetApi, "spigotmc.org", "https://api.spiget.org/v2/search/resources/{plugin_name}?field=name&size=5",
         std::nullopt},
        {"RuSpigot", SiteKind::HtmlSearch, "spigotmc.ru", "https://spigotmc.ru/resources/?q={plugin_name}", std::nullopt},
    };
}

std::vector<SiteConfig> parse_legitimacy_sites_config(const std::string& json_text) {
    std::vector<SiteConfig> out;
    auto parsed = json_parse(json_text);
    if (!parsed.has_value() || !parsed->is_array()) return out;
    for (auto& item : *parsed->arr_v) {
        if (!item.is_object()) continue;
        const JsonValue* label = item.get("label");
        const JsonValue* kind_s = item.get("kind");
        const JsonValue* site = item.get("site");
        const JsonValue* query = item.get("query");
        if (!label || !label->is_string() || !kind_s || !kind_s->is_string() || !site || !site->is_string() || !query ||
            !query->is_string()) {
            continue;  // невалидная запись - молча пропускаем (см. hpp)
        }
        auto kind = parse_site_kind(kind_s->str_v);
        if (!kind.has_value()) continue;
        SiteConfig cfg;
        cfg.label = label->str_v;
        cfg.kind = *kind;
        cfg.site = site->str_v;
        cfg.query = query->str_v;
        const JsonValue* rq = item.get("release_query");
        if (rq && rq->is_string()) cfg.release_query = rq->str_v;
        out.push_back(std::move(cfg));
    }
    return out;
}

namespace {
// БАГ-ФИКС (HANDOFF_53 -> закрыто): раньше здесь была заглушка
// USER/REPO. Реальный репозиторий проекта - NanoDev1488/NanoDecompiler
// (те же константы GITHUB_OWNER/GITHUB_REPO используются в
// electron/updater.ts для проверки обновлений) - файл legitimacy_sites.json
// лежит в корне репозитория, ветка main. Раньше curl молча получал 404 и
// движок деградировал на default_legitimacy_sites_config() - деградация
// была безопасной, но саму фичу "менять источники без пересборки" никто
// не включал. Теперь URL настоящий.
constexpr const char* LEGITIMACY_SITES_CONFIG_URL = "https://raw.githubusercontent.com/NanoDev1488/NanoDecompiler/main/legitimacy_sites.json";
}  // namespace

std::vector<SiteConfig> load_legitimacy_sites_config() {
    std::string cache_path = (fs::path(get_tools_dir()) / ".." / "legitimacy_sites.json").lexically_normal().string();

    auto fresh = http_get(LEGITIMACY_SITES_CONFIG_URL, 4.0, "application/json");
    if (fresh.has_value()) {
        auto parsed = parse_legitimacy_sites_config(*fresh);
        if (!parsed.empty()) {
            std::error_code ec;
            fs::create_directories(fs::path(cache_path).parent_path(), ec);
            std::ofstream f(cache_path, std::ios::binary);
            f << *fresh;
            return parsed;
        }
    }

    std::error_code ec;
    if (fs::is_regular_file(cache_path, ec)) {
        std::ifstream f(cache_path, std::ios::binary);
        std::string cached((std::istreambuf_iterator<char>(f)), std::istreambuf_iterator<char>());
        auto parsed = parse_legitimacy_sites_config(cached);
        if (!parsed.empty()) return parsed;
    }

    return default_legitimacy_sites_config();
}

// ---------------- HANDOFF_53: универсальная проверка по SiteConfig ----------------

LegitimacySourceResult check_site(const SiteConfig& cfg, const std::string& plugin_name, const std::string& jar_sha256_hex,
                                   double timeout_sec) {
    LegitimacySourceResult out;
    if (plugin_name.empty()) return out;
    std::string q = url_quote(plugin_name, true);
    std::string url = substitute_placeholder(cfg.query, "{plugin_name}", q);

    if (cfg.kind == SiteKind::GithubApi) {
        auto data = http_get_json(url, timeout_sec);
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
                if (!jar_sha256_hex.empty() && cfg.release_query.has_value()) {
                    std::string rel_url = substitute_placeholder(*cfg.release_query, "{full_name}", c.full_name);
                    auto rel = http_get_json(rel_url, timeout_sec);
                    if (rel.has_value() && rel->is_object()) {
                        const JsonValue* assets = rel->get("assets");
                        if (assets && assets->is_array() && !assets->arr_v->empty()) {
                            const JsonValue* dl = (*assets->arr_v)[0].get("browser_download_url");
                            if (dl && dl->is_string()) c.sha256_hex = download_and_sha256(dl->str_v, 50ULL * 1024 * 1024, timeout_sec);
                        }
                    }
                }
                out.candidates.push_back(std::move(c));
                k += 1;
            }
        }
        out.found = !out.candidates.empty();
        return out;
    }

    if (cfg.kind == SiteKind::ModrinthApi) {
        auto data = http_get_json(url, timeout_sec);
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
                if (!jar_sha256_hex.empty() && cfg.release_query.has_value()) {
                    // HANDOFF_53: Modrinth отдаёт хэш НАПРЯМУЮ в JSON - скачивать
                    // сам файл не нужно (см. спеку).
                    std::string rel_url = substitute_placeholder(*cfg.release_query, "{slug}", slug->str_v);
                    auto rel = http_get_json(rel_url, timeout_sec);
                    if (rel.has_value() && rel->is_array() && !rel->arr_v->empty()) {
                        const JsonValue& ver = (*rel->arr_v)[0];  // самая свежая версия
                        const JsonValue* files = ver.get("files");
                        if (files && files->is_array() && !files->arr_v->empty()) {
                            const JsonValue* hashes = (*files->arr_v)[0].get("hashes");
                            const JsonValue* sha256_v = hashes ? hashes->get("sha256") : nullptr;
                            if (sha256_v && sha256_v->is_string()) c.sha256_hex = sha256_v->str_v;
                        }
                    }
                }
                out.candidates.push_back(std::move(c));
                k += 1;
            }
        }
        out.found = !out.candidates.empty();
        return out;
    }

    if (cfg.kind == SiteKind::SpigetApi) {
        auto data = http_get_json(url, timeout_sec);
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
            // HANDOFF_53: хэш НЕ сравнивается для этого источника - см. спеку.
            out.candidates.push_back(std::move(c));
            k += 1;
        }
        out.found = !out.candidates.empty();
        return out;
    }

    // SiteKind::HtmlSearch
    auto html = http_get(url, timeout_sec, "text/html");
    if (!html.has_value()) return out;
    out.checked = true;
    auto links = ruspigot_parse_result_links(*html);
    size_t k = 0;
    for (auto& [href, title_raw] : links) {
        if (k >= 5) break;
        std::string title = title_raw;
        size_t a = title.find_first_not_of(" \t\r\n");
        size_t b = title.find_last_not_of(" \t\r\n");
        title = (a == std::string::npos) ? "" : title.substr(a, b - a + 1);
        if (title.empty()) continue;
        LegitimacyCandidate c;
        c.full_name = title;
        c.url = (href.rfind("http", 0) == 0) ? href : ("https://" + cfg.site + href);
        // HANDOFF_53: хэш НЕ сравнивается для этого источника - см. спеку.
        out.candidates.push_back(std::move(c));
        k += 1;
    }
    out.found = !out.candidates.empty();
    return out;
}

// ---------------- check_github / check_modrinth / check_spigot (сохранены для
// обратной совместимости - см. HANDOFF_32; run_legitimacy_check теперь
// использует check_site() выше с конфигом из load_legitimacy_sites_config()) ----------------

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

LegitimacyCheckResult run_legitimacy_check(const std::string& plugin_name, const std::string& plugin_yml_text, const std::string& jar_path) {
    LegitimacyCheckResult result;
    result.plugin_yml_fields = fields_from_plugin_yml(plugin_yml_text);

    // HANDOFF_53: хэш проверяемого jar - для сравнения с хэшами кандидатов
    // (см. спеку). Пусто, если jar_path не передан ИЛИ файл не читается -
    // сравнение хэшей просто не делается (hash_comparison = nullopt),
    // остальная проверка (поиск похожих проектов) продолжается как обычно.
    std::string jar_sha256_hex;
    if (!jar_path.empty()) {
        std::ifstream f(jar_path, std::ios::binary);
        if (f) {
            std::vector<uint8_t> data((std::istreambuf_iterator<char>(f)), std::istreambuf_iterator<char>());
            jar_sha256_hex = hex_encode(sha256(data));
        }
    }

    // HANDOFF_53: очистка имени - ОДИН раз на все источники (см. спеку -
    // результат очистки от источника не зависит). Пустая строка после
    // очистки -> ни один источник не запрашивается вообще (все 4 поля
    // результата останутся checked=false, как и раньше при пустом имени).
    std::string cleaned = clean_plugin_name_for_search(plugin_name);

    // HANDOFF_53: конфиг источников теперь дан данными, не хардкодом (см.
    // load_legitimacy_sites_config) - количество/состав источников МОЖЕТ
    // отличаться от ровно 4 стандартных (пользователь может отредактировать
    // конфиг на GitHub) - но result.github/modrinth/spigot/ruspigot
    // остаются ФИКСИРОВАННЫМИ 4 полями (см. hpp) ради обратной
    // совместимости с form_for_console/stats_json.cpp - сопоставление по
    // cfg.kind; при нескольких записях одного kind в конфиге побеждает
    // последняя (простое детерминированное поведение, не ошибка).
    std::vector<SiteConfig> sites = load_legitimacy_sites_config();

    std::vector<std::future<std::pair<SiteKind, LegitimacySourceResult>>> futures;
    for (auto& cfg : sites) {
        futures.push_back(std::async(std::launch::async, [cfg, cleaned, jar_sha256_hex] {
            return std::make_pair(cfg.kind, check_site(cfg, cleaned, jar_sha256_hex));
        }));
    }

    for (auto& fut : futures) {
        SiteKind kind;
        LegitimacySourceResult r;
        if (fut.wait_for(std::chrono::milliseconds(8000)) == std::future_status::ready) {
            try {
                std::tie(kind, r) = fut.get();
            } catch (...) {
                continue;
            }
        } else {
            continue;  // общий верхний предел 8с на источник, как и в оригинале
        }
        switch (kind) {
            case SiteKind::GithubApi:
                result.github = r;
                break;
            case SiteKind::ModrinthApi:
                result.modrinth = r;
                break;
            case SiteKind::SpigetApi:
                result.spigot = r;
                break;
            case SiteKind::HtmlSearch:
                result.ruspigot = r;
                break;
        }
    }

    // HANDOFF_53: сравнение хэшей - по ВСЕМ кандидатам всех 4 источников,
    // у которых sha256_hex реально получен (см. спеку - сейчас это только
    // GitHub/Modrinth).
    if (!jar_sha256_hex.empty()) {
        HashComparisonResult hc;
        struct Src {
            const char* label;
            const LegitimacySourceResult* r;
        };
        Src srcs[] = {{"GitHub", &result.github}, {"Modrinth", &result.modrinth}, {"SpigotMC", &result.spigot}, {"RuSpigot", &result.ruspigot}};
        for (auto& s : srcs) {
            for (auto& c : s.r->candidates) {
                if (!c.sha256_hex.has_value()) continue;
                bool match = (*c.sha256_hex == jar_sha256_hex);
                (match ? hc.matching : hc.mismatching).push_back(s.label);
            }
        }
        if (!hc.matching.empty() || !hc.mismatching.empty()) result.hash_comparison = std::move(hc);
    }

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
                std::string suffix;
                if (c.sha256_hex.has_value()) suffix = "  [sha256: " + c.sha256_hex->substr(0, 12) + "…]";
                lines.push_back("    " + c.full_name + " (" + c.url + ")" + suffix);
            }
        } else {
            lines.push_back(std::string("[*] ") + s.label + ": похожих проектов не найдено");
        }
    }
    // HANDOFF_53: вердикт по сравнению хэшей (см. спеку) - печатается,
    // только если ХОТЯ БЫ один источник реально дал хэш для сравнения.
    if (result.hash_comparison.has_value()) {
        auto& hc = *result.hash_comparison;
        auto join = [](const std::vector<std::string>& v) {
            std::string s;
            for (size_t i = 0; i < v.size(); ++i) {
                if (i) s += ", ";
                s += v[i];
            }
            return s;
        };
        if (!hc.matching.empty() && hc.mismatching.empty()) {
            lines.push_back("[*] Хэш jar совпадает с найденным на: " + join(hc.matching) + " - похоже на официальную сборку.");
        } else if (hc.matching.empty() && !hc.mismatching.empty()) {
            lines.push_back("[!] ВНИМАНИЕ: хэш jar НЕ совпадает ни с одним найденным источником (" + join(hc.mismatching) +
                             ") - возможна модифицированная/пиратская сборка.");
        } else if (!hc.matching.empty() && !hc.mismatching.empty()) {
            lines.push_back("[!] Хэш jar не соответствует: " + join(hc.mismatching) + ", НО соответствует: " + join(hc.matching) +
                             " - возможно, устаревшая версия или сборка с другого зеркала.");
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
