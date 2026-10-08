// naming_hints.cpp - см. naming_hints.hpp. 1:1 порт naming_hints.py.
#include <cstdint>  // БАГ-ФИКС: MinGW/Windows не тянет int64_t транзитивно через другие заголовки, как это молча делает libstdc++ на Linux - см. ошибку сборки Windows-раннера в этой сессии.
#include "naming_hints.hpp"

#include <algorithm>
#include <cctype>
#include <set>
#include <sstream>

#include "javatypes.hpp"

namespace nd {

namespace {

// Превращает произвольную строку в валидный Java-идентификатор в
// PascalCase - 'teleport-player' -> 'TeleportPlayer', 'lobby' -> 'Lobby'.
std::optional<std::string> sanitize_identifier(const std::string& raw, const std::string& prefix = "") {
    std::vector<std::string> parts;
    std::string cur;
    for (char c : raw) {
        bool alnum = std::isalnum(static_cast<unsigned char>(c)) != 0;
        if (alnum) {
            cur += c;
        } else if (!cur.empty()) {
            parts.push_back(cur);
            cur.clear();
        }
    }
    if (!cur.empty()) parts.push_back(cur);
    if (parts.empty()) return std::nullopt;

    std::string name;
    for (auto& p : parts) {
        std::string pp = p;
        pp[0] = static_cast<char>(std::toupper(static_cast<unsigned char>(pp[0])));
        name += pp;
    }
    std::string cleaned;
    for (char c : name)
        if (std::isalnum(static_cast<unsigned char>(c)) || c == '_') cleaned += c;
    name = cleaned;
    if (name.empty()) return std::nullopt;
    if (std::isdigit(static_cast<unsigned char>(name[0]))) name = "_" + name;
    return prefix + name;
}

std::string rsplit_last_slash(const std::string& internal) {
    auto pos = internal.find_last_of('/');
    return pos == std::string::npos ? internal : internal.substr(pos + 1);
}

}  // namespace

std::map<std::string, std::string> hints_by_annotation_name(const std::map<std::string, ClassFile>& class_files,
                                                              const LooksObfuscatedFn& looks_obfuscated_fn) {
    std::map<std::string, std::vector<std::pair<std::string, std::string>>> by_annotation_type;
    for (auto& [internal, cf] : class_files) {
        for (auto& ann : cf.annotations) {
            for (auto& [key, val] : ann.args) {
                if (key != "name" || !val || val->kind != AnnotationValue::Kind::Str) continue;
                std::string s = val->str_v;
                size_t b = s.find_first_not_of(" \t\n\r");
                if (b == std::string::npos) continue;
                by_annotation_type[ann.type].emplace_back(internal, s);
            }
        }
    }

    std::map<std::string, std::string> hints;
    for (auto& [ann_type, entries] : by_annotation_type) {
        (void)ann_type;
        if (entries.size() < 3) continue;
        for (auto& [internal, name_val] : entries) {
            std::string simple = rsplit_last_slash(internal);
            if (!looks_obfuscated_fn(simple, "class")) continue;
            auto sanitized = sanitize_identifier(name_val);
            if (sanitized.has_value()) hints[internal] = *sanitized;
        }
    }
    return hints;
}

namespace {

bool contains_ci(const std::string& haystack_lower, const std::string& needle_lower) { return haystack_lower.find(needle_lower) != std::string::npos; }

bool project_uses_brigadier(const std::map<std::string, ClassFile>& class_files) {
    for (auto& [internal, cf] : class_files) {
        (void)internal;
        for (auto& [idx, entry] : cf.pool) {
            (void)idx;
            if (entry.tag != CpTag::Utf8) continue;
            std::string lower = entry.utf8_value;
            std::transform(lower.begin(), lower.end(), lower.begin(), [](unsigned char c) { return std::tolower(c); });
            if (contains_ci(lower, "brigadier")) return true;
        }
    }
    return false;
}

constexpr uint8_t kAload0 = 0x2a;
constexpr uint8_t kLdc = 0x12;
constexpr uint8_t kLdcW = 0x13;
constexpr uint8_t kInvokespecial = 0xb7;

}  // namespace

std::map<std::string, std::string> hints_by_brigadier_super_call(const std::map<std::string, ClassFile>& class_files,
                                                                   const LooksObfuscatedFn& looks_obfuscated_fn) {
    std::map<std::string, std::string> hints;
    if (!project_uses_brigadier(class_files)) return hints;

    for (auto& [internal, cf] : class_files) {
        std::string simple = rsplit_last_slash(internal);
        if (!looks_obfuscated_fn(simple, "class")) continue;
        if (!cf.super_class_name.has_value()) continue;
        const std::string& super_internal = *cf.super_class_name;
        std::string super_simple = rsplit_last_slash(super_internal);
        if (!looks_obfuscated_fn(super_simple, "class")) continue;

        for (auto& m : cf.methods) {
            if (m.name != "<init>" || !m.has_code || m.code.size() < 6) continue;
            const auto& code = m.code;
            if (code[0] != kAload0) continue;
            uint16_t str_idx;
            size_t pos;
            if (code[1] == kLdc) {
                str_idx = code[2];
                pos = 3;
            } else if (code[1] == kLdcW) {
                str_idx = static_cast<uint16_t>((code[2] << 8) | code[3]);
                pos = 4;
            } else {
                continue;
            }
            if (pos + 3 > code.size() || code[pos] != kInvokespecial) continue;
            uint16_t invoke_idx = static_cast<uint16_t>((code[pos + 1] << 8) | code[pos + 2]);
            auto ref = cf.ref_string(invoke_idx);
            if (!ref.has_value() || std::get<0>(*ref) != super_internal || std::get<1>(*ref) != "<init>") continue;
            auto pit = cf.pool.find(str_idx);
            if (pit == cf.pool.end() || pit->second.tag != CpTag::String) continue;
            auto utf8_it = cf.pool.find(pit->second.idx1);
            if (utf8_it == cf.pool.end() || utf8_it->second.tag != CpTag::Utf8) continue;
            std::string cmd_name = utf8_it->second.utf8_value;
            if (cmd_name.empty()) continue;
            auto sanitized = sanitize_identifier(cmd_name, "Command");
            if (sanitized.has_value()) hints[internal] = *sanitized;
            break;
        }
    }
    return hints;
}

BukkitNamingHints hints_by_bukkit_patterns(const std::map<std::string, ClassFile>& class_files,
                                            const std::optional<std::string>& plugin_yml_text,
                                            const LooksObfuscatedFn& looks_obfuscated_fn) {
    BukkitNamingHints out;
    std::set<std::string> used_class_hints;

    // 1. Parse plugin.yml (main class & commands)
    std::string main_class_internal;
    std::vector<std::string> plugin_commands;
    if (plugin_yml_text.has_value()) {
        std::istringstream iss(*plugin_yml_text);
        std::string line;
        bool in_commands = false;
        while (std::getline(iss, line)) {
            size_t first = line.find_first_not_of(" \t\r\n");
            if (first == std::string::npos || line[first] == '#') continue;
            std::string trimmed = line.substr(first);
            if (trimmed.rfind("main:", 0) == 0) {
                std::string val = trimmed.substr(5);
                size_t vf = val.find_first_not_of(" \t\r\n\"'");
                size_t vl = val.find_last_not_of(" \t\r\n\"'");
                if (vf != std::string::npos && vl != std::string::npos) {
                    std::string dotted = val.substr(vf, vl - vf + 1);
                    std::string internal = dotted;
                    for (char& c : internal) if (c == '.') c = '/';
                    main_class_internal = internal;
                }
            } else if (trimmed.rfind("commands:", 0) == 0) {
                in_commands = true;
            } else if (in_commands) {
                if (line[0] != ' ' && line[0] != '\t') {
                    in_commands = false;
                } else {
                    size_t colon = trimmed.find(':');
                    if (colon != std::string::npos) {
                        std::string cmd = trimmed.substr(0, colon);
                        while (!cmd.empty() && (cmd.back() == ' ' || cmd.back() == '\t')) cmd.pop_back();
                        if (!cmd.empty() && cmd != "description" && cmd != "aliases" && cmd != "permission" && cmd != "usage") {
                            plugin_commands.push_back(cmd);
                        }
                    }
                }
            }
        }
    }

    // Main class hint
    if (!main_class_internal.empty() && class_files.count(main_class_internal)) {
        std::string simple = rsplit_last_slash(main_class_internal);
        if (looks_obfuscated_fn(simple, "class")) {
            out.class_hints[main_class_internal] = "PluginMain";
            used_class_hints.insert("PluginMain");
        }
    }

    // 2. Scan class files for listeners, commands, and common Bukkit interfaces
    std::map<std::string, int> listener_base_counts;

    for (const auto& [internal, cf] : class_files) {
        std::string simple = rsplit_last_slash(internal);
        bool class_obf = looks_obfuscated_fn(simple, "class");

        bool implements_listener = false;
        bool implements_command = false;
        bool implements_tab = false;
        bool implements_holder = false;
        bool extends_runnable = false;
        bool implements_config = false;

        if (cf.super_class_name.has_value()) {
            const std::string& sc = *cf.super_class_name;
            if (sc == "org/bukkit/scheduler/BukkitRunnable") extends_runnable = true;
            else if (sc == "org/bukkit/command/Command") implements_command = true;
        }

        for (const auto& iface : cf.interfaces) {
            if (iface == "org/bukkit/event/Listener" ||
                iface == "net/md_5/bungee/api/plugin/Listener" ||
                iface == "net/kyori/event/EventSubscriber") {
                implements_listener = true;
            } else if (iface == "org/bukkit/command/CommandExecutor") {
                implements_command = true;
            } else if (iface == "org/bukkit/command/TabCompleter") {
                implements_tab = true;
            } else if (iface == "org/bukkit/inventory/InventoryHolder") {
                implements_holder = true;
            } else if (iface == "org/bukkit/configuration/serialization/ConfigurationSerializable") {
                implements_config = true;
            }
        }

        // Methods: inspect for @EventHandler
        std::vector<std::string> handled_events;
        std::map<std::string, int> method_name_counts;

        for (const auto& m : cf.methods) {
            bool has_event_ann = false;
            for (const auto& ann : m.annotations) {
                if (ann.type.find("EventHandler;") != std::string::npos ||
                    ann.type.find("Subscribe;") != std::string::npos ||
                    ann.type.find("SubscribeEvent;") != std::string::npos) {
                    has_event_ann = true;
                    break;
                }
            }
            if (!has_event_ann) continue;
            implements_listener = true;

            try {
                auto [ret_type, params] = method_descriptor_to_java(m.descriptor);
                if (!params.empty()) {
                    std::string p0 = params[0];
                    auto last_dot = p0.find_last_of('.');
                    std::string ev_simple = (last_dot == std::string::npos) ? p0 : p0.substr(last_dot + 1);
                    std::string base_ev = ev_simple;
                    if (base_ev.size() > 5 && base_ev.substr(base_ev.size() - 5) == "Event") {
                        base_ev = base_ev.substr(0, base_ev.size() - 5);
                    }
                    handled_events.push_back(base_ev);

                    if (looks_obfuscated_fn(m.name, "method")) {
                        std::string proposed = "on" + base_ev;
                        int cnt = ++method_name_counts[proposed];
                        if (cnt > 1) proposed += std::to_string(cnt);
                        out.method_hints[{internal, m.name, m.descriptor}] = proposed;
                    }
                }
            } catch (...) {}
        }

        if (class_obf && out.class_hints.find(internal) == out.class_hints.end()) {
            if (implements_listener && !handled_events.empty()) {
                int player_cnt = 0, block_cnt = 0, entity_cnt = 0, inv_cnt = 0, srv_cnt = 0;
                for (const auto& ev : handled_events) {
                    if (ev.rfind("Player", 0) == 0 || ev.rfind("AsyncPlayer", 0) == 0) player_cnt++;
                    else if (ev.rfind("Block", 0) == 0) block_cnt++;
                    else if (ev.rfind("Entity", 0) == 0) entity_cnt++;
                    else if (ev.rfind("Inventory", 0) == 0 || ev.rfind("Menu", 0) == 0) inv_cnt++;
                    else if (ev.rfind("Server", 0) == 0) srv_cnt++;
                }
                std::string base_hint = "EventListener";
                if (handled_events.size() == 1) {
                    base_hint = handled_events[0] + "Listener";
                } else if (player_cnt >= (int)handled_events.size() / 2 && player_cnt > 0) {
                    base_hint = "PlayerListener";
                } else if (block_cnt >= (int)handled_events.size() / 2 && block_cnt > 0) {
                    base_hint = "BlockListener";
                } else if (entity_cnt >= (int)handled_events.size() / 2 && entity_cnt > 0) {
                    base_hint = "EntityListener";
                } else if (inv_cnt >= (int)handled_events.size() / 2 && inv_cnt > 0) {
                    base_hint = "InventoryListener";
                } else if (srv_cnt >= (int)handled_events.size() / 2 && srv_cnt > 0) {
                    base_hint = "ServerListener";
                }
                int count = ++listener_base_counts[base_hint];
                std::string final_hint = (count == 1) ? base_hint : (base_hint + std::to_string(count));
                out.class_hints[internal] = final_hint;
                used_class_hints.insert(final_hint);
            } else if (implements_command || implements_tab) {
                // Command executor hint
                std::string matched_cmd = "";
                for (const auto& [idx, cp] : cf.pool) {
                    if (cp.tag == CpTag::Utf8) {
                        for (const auto& pcmd : plugin_commands) {
                            if (cp.utf8_value == pcmd) {
                                matched_cmd = pcmd;
                                break;
                            }
                        }
                    }
                    if (!matched_cmd.empty()) break;
                }
                if (matched_cmd.empty() && plugin_commands.size() == 1) {
                    matched_cmd = plugin_commands[0];
                }
                std::string suffix = implements_tab && !implements_command ? "TabCompleter" : "Command";
                if (!matched_cmd.empty()) {
                    auto sanitized = sanitize_identifier(matched_cmd, "");
                    if (sanitized.has_value()) {
                        std::string ch = *sanitized + suffix;
                        if (!used_class_hints.count(ch)) {
                            out.class_hints[internal] = ch;
                            used_class_hints.insert(ch);
                        }
                    }
                } else {
                    std::string ch = "Plugin" + suffix;
                    int k = 1;
                    while (used_class_hints.count(ch + (k == 1 ? "" : std::to_string(k)))) k++;
                    std::string final_ch = ch + (k == 1 ? "" : std::to_string(k));
                    out.class_hints[internal] = final_ch;
                    used_class_hints.insert(final_ch);
                }
            } else if (extends_runnable) {
                std::string ch = "PluginTask";
                int k = 1;
                while (used_class_hints.count(ch + (k == 1 ? "" : std::to_string(k)))) k++;
                std::string final_ch = ch + (k == 1 ? "" : std::to_string(k));
                out.class_hints[internal] = final_ch;
                used_class_hints.insert(final_ch);
            } else if (implements_holder) {
                std::string ch = "MenuHolder";
                int k = 1;
                while (used_class_hints.count(ch + (k == 1 ? "" : std::to_string(k)))) k++;
                std::string final_ch = ch + (k == 1 ? "" : std::to_string(k));
                out.class_hints[internal] = final_ch;
                used_class_hints.insert(final_ch);
            } else if (implements_config) {
                std::string ch = "ConfigData";
                int k = 1;
                while (used_class_hints.count(ch + (k == 1 ? "" : std::to_string(k)))) k++;
                std::string final_ch = ch + (k == 1 ? "" : std::to_string(k));
                out.class_hints[internal] = final_ch;
                used_class_hints.insert(final_ch);
            }
        }
    }

    return out;
}

}  // namespace nd
