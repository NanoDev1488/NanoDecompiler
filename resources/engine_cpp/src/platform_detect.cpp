#include "platform_detect.hpp"
#include "json_value.hpp"
#include <algorithm>
#include <regex>

namespace nd {

std::string PlatformInfo::kind_label() const {
    switch (kind) {
        case PlatformKind::Bukkit: return "Bukkit/Spigot";
        case PlatformKind::Paper: return "Paper";
        case PlatformKind::Velocity: return "Velocity";
        case PlatformKind::Bungee: return "BungeeCord";
        case PlatformKind::ModFabric: return "Fabric mod";
        case PlatformKind::ModForge: return "Forge/NeoForge mod";
        default: return "неизвестно";
    }
}

namespace {

std::string trim(const std::string& s) {
    size_t a = s.find_first_not_of(" \t\r\n");
    if (a == std::string::npos) return "";
    size_t b = s.find_last_not_of(" \t\r\n");
    return s.substr(a, b - a + 1);
}

// Общий экстрактор для YAML-подобных манифестов (plugin.yml/bungee.yml) -
// та же грамматика "name: значение" построчно, что уже используется для
// website/author в legitimacy_check.cpp - не дублируем регэксп заново,
// но это отдельный, самодостаточный модуль (platform_detect не должен
// тянуть legitimacy_check.hpp), поэтому здесь свой маленький экстрактор.
std::optional<std::string> extract_yaml_name(const std::string& text) {
    static const std::regex re(R"(^name:\s*['"]?([^'"\n]+)['"]?\s*$)", std::regex::multiline);
    std::smatch m;
    if (std::regex_search(text, m, re)) {
        std::string v = trim(m[1].str());
        if (!v.empty()) return v;
    }
    return std::nullopt;
}

// velocity-plugin.json / fabric.mod.json - настоящий JSON, есть готовый
// парсер (json_value.hpp) - надёжнее регэкспа на случай вложенных полей.
std::optional<std::string> extract_json_field(const std::string& text, const std::string& field) {
    auto parsed = json_parse(text);
    if (!parsed.has_value() || !parsed->is_object()) return std::nullopt;
    const JsonValue* v = parsed->get(field);
    if (v == nullptr) return std::nullopt;
    auto s = v->as_string();
    if (s.has_value() && !s->empty()) return s;
    return std::nullopt;
}

// mods.toml (Forge/NeoForge) - НЕ полноценный TOML-парсер (оверкилл ради
// одного поля из простого key="value" формата) - displayName обычно
// внутри [[mods]] секции, но встречается ОДИН раз почти всегда, ищем
// первое совпадение построчно, как и с YAML-манифестами выше.
std::optional<std::string> extract_toml_field(const std::string& text, const std::string& field) {
    std::regex re("^" + field + R"(\s*=\s*["']([^"'\n]+)["']\s*$)", std::regex::multiline);
    std::smatch m;
    if (std::regex_search(text, m, re)) {
        std::string v = trim(m[1].str());
        if (!v.empty()) return v;
    }
    return std::nullopt;
}

// mcmod.info (легаси Forge 1.7.x-1.12.x) - JSON-массив объектов ИЛИ
// объект с полем "modList" - оба варианта встречаются в реальных модах.
std::optional<std::string> extract_mcmod_info_name(const std::string& text) {
    auto parsed = json_parse(text);
    if (!parsed.has_value()) return std::nullopt;
    // Вариант 1: корень - массив [{"modid":...,"name":...}, ...]
    if (parsed->is_array() && parsed->arr_v && !parsed->arr_v->empty()) {
        const JsonValue& first = (*parsed->arr_v)[0];
        if (first.is_object()) {
            if (const JsonValue* name = first.get("name")) {
                if (auto s = name->as_string(); s.has_value() && !s->empty()) return s;
            }
        }
    }
    // Вариант 2: {"modListVersion":2,"modList":[{"name":...}]}
    if (parsed->is_object()) {
        if (const JsonValue* modList = parsed->get("modList")) {
            if (modList->is_array() && modList->arr_v && !modList->arr_v->empty()) {
                const JsonValue& first = (*modList->arr_v)[0];
                if (first.is_object()) {
                    if (const JsonValue* name = first.get("name")) {
                        if (auto s = name->as_string(); s.has_value() && !s->empty()) return s;
                    }
                }
            }
        }
    }
    return std::nullopt;
}

}  // namespace

PlatformInfo detect_platform(const std::vector<std::string>& all_names,
                              const std::function<std::optional<std::string>(const std::string&)>& read_entry) {
    PlatformInfo info;

    auto has = [&](const std::string& path) {
        return std::find(all_names.begin(), all_names.end(), path) != all_names.end();
    };

    // Порядок проверки важен: моды - первым делом (самое важное - не дать
    // им тихо провалиться в обычную декомпиляцию плагина), затем более
    // специфичные серверные форматы, plugin.yml - как самый общий/частый
    // формат, последним по приоритету среди server-плагинов, НО он же
    // почти всегда СОпутствует paper-plugin.yml для обратной совместимости
    // (современные Paper-плагины часто кладут оба файла) - проверяем
    // paper-plugin.yml раньше, чтобы не занизить более точный тип до
    // просто "Bukkit".

    if (has("fabric.mod.json")) {
        info.kind = PlatformKind::ModFabric;
        info.manifest_path = "fabric.mod.json";
        if (auto text = read_entry("fabric.mod.json")) info.name = extract_json_field(*text, "name");
        return info;
    }
    if (has("META-INF/mods.toml") || has("META-INF/neoforge.mods.toml")) {
        info.kind = PlatformKind::ModForge;
        info.manifest_path = has("META-INF/mods.toml") ? "META-INF/mods.toml" : "META-INF/neoforge.mods.toml";
        if (auto text = read_entry(info.manifest_path)) info.name = extract_toml_field(*text, "displayName");
        return info;
    }
    if (has("mcmod.info")) {
        info.kind = PlatformKind::ModForge;
        info.manifest_path = "mcmod.info";
        if (auto text = read_entry("mcmod.info")) info.name = extract_mcmod_info_name(*text);
        return info;
    }
    if (has("velocity-plugin.json")) {
        info.kind = PlatformKind::Velocity;
        info.manifest_path = "velocity-plugin.json";
        if (auto text = read_entry("velocity-plugin.json")) info.name = extract_json_field(*text, "name");
        return info;
    }
    if (has("bungee.yml")) {
        info.kind = PlatformKind::Bungee;
        info.manifest_path = "bungee.yml";
        if (auto text = read_entry("bungee.yml")) info.name = extract_yaml_name(*text);
        return info;
    }
    if (has("paper-plugin.yml")) {
        info.kind = PlatformKind::Paper;
        info.manifest_path = "paper-plugin.yml";
        if (auto text = read_entry("paper-plugin.yml")) info.name = extract_yaml_name(*text);
        return info;
    }
    if (has("plugin.yml")) {
        info.kind = PlatformKind::Bukkit;
        info.manifest_path = "plugin.yml";
        if (auto text = read_entry("plugin.yml")) info.name = extract_yaml_name(*text);
        return info;
    }

    info.kind = PlatformKind::Unknown;
    return info;
}

}  // namespace nd
