// naming_hints.cpp - см. naming_hints.hpp. 1:1 порт naming_hints.py.
#include <cstdint>  // БАГ-ФИКС: MinGW/Windows не тянет int64_t транзитивно через другие заголовки, как это молча делает libstdc++ на Linux - см. ошибку сборки Windows-раннера в этой сессии.
#include "naming_hints.hpp"

#include <algorithm>
#include <cctype>

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

}  // namespace nd
