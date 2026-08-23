// renamer.cpp - см. renamer.hpp. 1:1 порт класса Renamer из main.py.
#include "renamer.hpp"

#include <cctype>

#include "javatypes.hpp"

namespace nd {

namespace {

// str.isdigit() Python - непусто и все символы цифры (JVM anon-класс
// сегменты типа "1", "2" - всегда ASCII, доп. юникод-цифры Python
// технически тоже принимает, но для $-сегментов имени класса это не
// встречается на практике - ASCII-проверки достаточно).
bool is_all_digits(const std::string& s) {
    if (s.empty()) return false;
    for (char c : s)
        if (!std::isdigit(static_cast<unsigned char>(c))) return false;
    return true;
}

std::pair<std::string, std::string> rpartition_slash(const std::string& s) {
    // Python str.rpartition("/") -> (before, sep, after); если "/" нет,
    // before == "" (не s!) и after == s. Здесь возвращаем (pkg, simple).
    auto pos = s.find_last_of('/');
    if (pos == std::string::npos) return {"", s};
    return {s.substr(0, pos), s.substr(pos + 1)};
}

std::vector<std::string> split_char(const std::string& s, char sep) {
    std::vector<std::string> out;
    size_t start = 0;
    for (size_t i = 0; i < s.size(); ++i) {
        if (s[i] == sep) {
            out.push_back(s.substr(start, i - start));
            start = i + 1;
        }
    }
    out.push_back(s.substr(start));
    return out;
}

std::string join_char(const std::vector<std::string>& parts, char sep) {
    std::string out;
    for (size_t i = 0; i < parts.size(); ++i) {
        if (i) out += sep;
        out += parts[i];
    }
    return out;
}

}  // namespace

std::string Renamer::friendly_class(const std::string& internal_name) const {
    auto it = class_map_.find(internal_name);
    if (it != class_map_.end()) return it->second;

    auto [pkg, simple] = rpartition_slash(internal_name);
    std::string new_pkg = !pkg.empty() ? friendly_package(pkg) : "";

    // Outer$Inner$1 (вложенные/анонимные классы) - разбираем по сегментам,
    // каждый обрабатываем отдельно: числовым (анонимным) сегментам даём
    // Anon<N>, остальные - через looks_obfuscated.
    std::vector<std::string> parts = split_char(simple, '$');
    std::vector<std::string> new_parts;
    new_parts.reserve(parts.size());
    for (size_t idx = 0; idx < parts.size(); ++idx) {
        const std::string& p = parts[idx];
        if (is_all_digits(p)) {
            new_parts.push_back("Anon" + p);
        } else if (looks_obfuscated(p, "class")) {
            // Подсказка (naming_hints - НЕ перенесён, см. renamer.hpp) -
            // только для последнего сегмента и только если ещё не занята.
            bool is_last = (idx == parts.size() - 1);
            std::string hint;
            bool have_hint = false;
            if (is_last) {
                auto hit = class_name_hints.find(internal_name);
                if (hit != class_name_hints.end()) {
                    have_hint = true;
                    hint = hit->second;
                }
            }
            if (have_hint && !used_hint_names_.count(hint)) {
                used_hint_names_.insert(hint);
                new_parts.push_back(hint);
            } else {
                class_ctr_ += 1;
                new_parts.push_back("ClassA" + std::to_string(class_ctr_));
            }
        } else {
            new_parts.push_back(p);
        }
    }
    std::string new_simple = join_char(new_parts, '_');
    std::string new_internal = !new_pkg.empty() ? (new_pkg + "/" + new_simple) : new_simple;
    class_map_[internal_name] = new_internal;
    return new_internal;
}

std::string Renamer::friendly_package(const std::string& pkg_internal) const {
    auto it = package_map_.find(pkg_internal);
    if (it != package_map_.end()) return it->second;

    std::vector<std::string> parts = split_char(pkg_internal, '/');
    std::vector<std::string> new_parts;
    new_parts.reserve(parts.size());
    for (auto& p : parts) {
        if (looks_obfuscated(p, "package")) {
            pkg_ctr_ += 1;
            new_parts.push_back("pkg" + std::to_string(pkg_ctr_));
        } else {
            new_parts.push_back(p);
        }
    }
    std::string new_pkg = join_char(new_parts, '/');
    package_map_[pkg_internal] = new_pkg;
    return new_pkg;
}

std::string Renamer::method_name(const std::string& owner_internal, const std::string& name, const std::string& desc) const {
    MethodKey key{owner_internal, name, desc};
    auto it = method_map_.find(key);
    if (it != method_map_.end()) return it->second;

    std::string new_name;
    if (looks_obfuscated(name, "method")) {
        method_ctr_ += 1;
        new_name = "method" + std::to_string(method_ctr_);
    } else {
        new_name = name;
    }
    method_map_[key] = new_name;
    return new_name;
}

std::string Renamer::field_name(const std::string& owner_internal, const std::string& name, const std::string& desc) const {
    MethodKey key{owner_internal, name, desc};
    auto it = field_map_.find(key);
    if (it != field_map_.end()) return it->second;

    std::string new_name;
    if (looks_obfuscated(name, "field")) {
        field_ctr_ += 1;
        new_name = "field" + std::to_string(field_ctr_);
    } else {
        new_name = name;
    }
    field_map_[key] = new_name;
    return new_name;
}

}  // namespace nd
