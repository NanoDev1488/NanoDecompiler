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
        case PlatformKind::Sponge: return "Sponge";
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
std::optional<std::string> extract_yaml_field(const std::string& text, const std::string& field) {
    std::regex re(R"(^)" + field + R"(:\s*['"]?([^'"\n]+)['"]?\s*$)", std::regex::multiline);
    std::smatch m;
    if (std::regex_search(text, m, re)) {
        std::string v = trim(m[1].str());
        if (!v.empty()) return v;
    }
    return std::nullopt;
}

std::optional<std::string> extract_yaml_name(const std::string& text) {
    return extract_yaml_field(text, "name");
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

// quilt.mod.json (Quilt mod loader) - имя лежит внутри quilt_loader.metadata.name
// или quilt_loader.id, если metadata не задана.
std::optional<std::string> extract_quilt_name(const std::string& text) {
    auto parsed = json_parse(text);
    if (!parsed.has_value() || !parsed->is_object()) return std::nullopt;
    if (const JsonValue* ql = parsed->get("quilt_loader")) {
        if (ql->is_object()) {
            if (const JsonValue* meta = ql->get("metadata")) {
                if (meta->is_object()) {
                    if (const JsonValue* name = meta->get("name")) {
                        if (auto s = name->as_string(); s.has_value() && !s->empty()) return s;
                    }
                }
            }
            if (const JsonValue* id = ql->get("id")) {
                if (auto s = id->as_string(); s.has_value() && !s->empty()) return s;
            }
        }
    }
    return extract_json_field(text, "name");
}

}  // namespace

PlatformInfo detect_platform(const std::vector<std::string>& all_names,
                              const std::function<std::optional<std::string>(const std::string&)>& read_entry) {
    PlatformInfo info;

    auto has = [&](const std::string& path) {
        return std::find(all_names.begin(), all_names.end(), path) != all_names.end();
    };

    std::string vel_path = has("velocity-plugin.json") ? "velocity-plugin.json"
                                                        : (has("META-INF/velocity-plugin.json") ? "META-INF/velocity-plugin.json" : "");
    if (!vel_path.empty()) {
        info.kind = PlatformKind::Velocity;
        info.manifest_path = vel_path;
        if (auto text = read_entry(vel_path)) {
            info.name = extract_json_field(*text, "name");
            if (!info.name.has_value()) info.name = extract_json_field(*text, "id");
            info.version = extract_json_field(*text, "version");
            info.description = extract_json_field(*text, "description");
            info.main_class = extract_json_field(*text, "main");
        }
    } else {
        std::string sponge_path = has("sponge_plugins.json") ? "sponge_plugins.json"
                                                              : (has("META-INF/sponge_plugins.json") ? "META-INF/sponge_plugins.json" : "");
        if (!sponge_path.empty()) {
            info.kind = PlatformKind::Sponge;
            info.manifest_path = sponge_path;
            if (auto text = read_entry(sponge_path)) {
                info.name = extract_json_field(*text, "name");
                if (!info.name.has_value()) info.name = extract_json_field(*text, "id");
                info.version = extract_json_field(*text, "version");
                info.description = extract_json_field(*text, "description");
                info.main_class = extract_json_field(*text, "entrypoint");
                if (!info.main_class.has_value()) info.main_class = extract_json_field(*text, "main");
                auto parsed = json_parse(*text);
                if (parsed && parsed->is_object()) {
                    if (const JsonValue* plugins = parsed->get("plugins")) {
                        if (plugins->is_array() && plugins->arr_v && !plugins->arr_v->empty()) {
                            const JsonValue& p0 = (*plugins->arr_v)[0];
                            if (p0.is_object()) {
                                if (!info.name.has_value()) {
                                    if (const JsonValue* n = p0.get("name")) info.name = n->as_string();
                                    if (!info.name.has_value()) {
                                        if (const JsonValue* id = p0.get("id")) info.name = id->as_string();
                                    }
                                }
                                if (!info.version.has_value()) {
                                    if (const JsonValue* v = p0.get("version")) info.version = v->as_string();
                                }
                                if (!info.main_class.has_value()) {
                                    if (const JsonValue* ep = p0.get("entrypoint")) info.main_class = ep->as_string();
                                }
                            }
                        }
                    }
                }
            }
        } else {
            std::string bungee_path = has("bungee.yml") ? "bungee.yml" : (has("waterfall.yml") ? "waterfall.yml" : "");
            if (!bungee_path.empty()) {
                info.kind = PlatformKind::Bungee;
                info.manifest_path = bungee_path;
                if (auto text = read_entry(bungee_path)) {
                    info.name = extract_yaml_field(*text, "name");
                    info.version = extract_yaml_field(*text, "version");
                    info.description = extract_yaml_field(*text, "description");
                    info.main_class = extract_yaml_field(*text, "main");
                    if (auto a = extract_yaml_field(*text, "author")) info.authors.push_back(*a);
                    if (auto d = extract_yaml_field(*text, "depends")) info.depends.push_back(*d);
                }
            } else {
                std::string paper_path = has("paper-plugin.yml") ? "paper-plugin.yml" : (has("META-INF/paper-plugin.yml") ? "META-INF/paper-plugin.yml" : "");
                if (!paper_path.empty()) {
                    info.kind = PlatformKind::Paper;
                    info.manifest_path = paper_path;
                    if (auto text = read_entry(paper_path)) {
                        info.name = extract_yaml_field(*text, "name");
                        info.version = extract_yaml_field(*text, "version");
                        info.description = extract_yaml_field(*text, "description");
                        info.main_class = extract_yaml_field(*text, "main");
                        if (auto a = extract_yaml_field(*text, "author")) info.authors.push_back(*a);
                    }
                } else if (has("plugin.yml")) {
                    info.kind = PlatformKind::Bukkit;
                    info.manifest_path = "plugin.yml";
                    if (auto text = read_entry("plugin.yml")) {
                        info.name = extract_yaml_field(*text, "name");
                        info.version = extract_yaml_field(*text, "version");
                        info.description = extract_yaml_field(*text, "description");
                        info.main_class = extract_yaml_field(*text, "main");
                        if (auto a = extract_yaml_field(*text, "author")) info.authors.push_back(*a);
                        if (auto d = extract_yaml_field(*text, "depend")) info.depends.push_back(*d);
                        if (auto sd = extract_yaml_field(*text, "softdepend")) info.soft_depends.push_back(*sd);
                    }
                } else {
                    // Моды
                    std::string fabric_path = has("fabric.mod.json") ? "fabric.mod.json"
                                                                     : (has("META-INF/fabric.mod.json") ? "META-INF/fabric.mod.json" : "");
                    if (!fabric_path.empty()) {
                        info.kind = PlatformKind::ModFabric;
                        info.manifest_path = fabric_path;
                        if (auto text = read_entry(fabric_path)) {
                            info.name = extract_json_field(*text, "name");
                            info.version = extract_json_field(*text, "version");
                            info.description = extract_json_field(*text, "description");
                        }
                    } else if (has("quilt.mod.json")) {
                        info.kind = PlatformKind::ModFabric;
                        info.manifest_path = "quilt.mod.json";
                        if (auto text = read_entry("quilt.mod.json")) {
                            info.name = extract_quilt_name(*text);
                            info.version = extract_json_field(*text, "version");
                        }
                    } else if (has("META-INF/mods.toml") || has("META-INF/neoforge.mods.toml")) {
                        info.kind = PlatformKind::ModForge;
                        info.manifest_path = has("META-INF/mods.toml") ? "META-INF/mods.toml" : "META-INF/neoforge.mods.toml";
                        if (auto text = read_entry(info.manifest_path)) {
                            info.name = extract_toml_field(*text, "displayName");
                            info.version = extract_toml_field(*text, "version");
                            info.description = extract_toml_field(*text, "description");
                        }
                    } else if (has("mcmod.info")) {
                        info.kind = PlatformKind::ModForge;
                        info.manifest_path = "mcmod.info";
                        if (auto text = read_entry("mcmod.info")) info.name = extract_mcmod_info_name(*text);
                    } else {
                        info.kind = PlatformKind::Unknown;
                    }
                }
            }
        }
    }

    if (info.main_class.has_value() && !info.main_class->empty()) {
        std::string exp = *info.main_class;
        for (char& c : exp) {
            if (c == '.') c = '/';
        }
        if (exp.size() < 6 || exp.substr(exp.size() - 6) != ".class") {
            exp += ".class";
        }
        bool found = false;
        for (const auto& n : all_names) {
            if (n == exp || (n.size() > exp.size() && n.substr(n.size() - exp.size()) == exp)) {
                found = true;
                break;
            }
        }
        info.main_class_verified = found;
        if (found) {
            info.verification_note = "Точка входа (" + *info.main_class + ") верифицирована в JAR-архиве.";
        } else {
            info.verification_note = "ВНИМАНИЕ: Класс точки входа (" + *info.main_class + ") отсутствует в JAR!";
        }
    }

    return info;
}

}  // namespace nd
