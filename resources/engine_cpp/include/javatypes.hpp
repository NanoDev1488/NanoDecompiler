// javatypes.hpp - порт resources/engine/javatypes.py (v2.0, HANDOFF_25,
// модуль 2b). Разбор JVM-дескрипторов типов/сигнатур (generics) и
// эвристика "похоже ли имя на сгенерированное обфускатором".
#pragma once

#include <optional>
#include <string>
#include <unordered_set>
#include <utility>
#include <vector>

namespace nd {

// internal (pkg/Outer$Inner) -> dotted (pkg.Outer.Inner), с сохранением
// числовых сегментов ($1 и т.п.) приклеенными через $.
std::string dotted_from_internal(const std::string& internal);

// Разбор ОДНОГО типа из дескриптора начиная с позиции i; возвращает
// (java-строка типа, позиция после типа). Бросает std::invalid_argument
// на некорректном дескрипторе (зеркалит ValueError в Python).
std::pair<std::string, size_t> parse_one_type(const std::string& desc, size_t i);

std::string field_descriptor_to_java(const std::string& desc);

// returns (return_type, [param_type, ...])
std::pair<std::string, std::vector<std::string>> method_descriptor_to_java(const std::string& desc);

// ---- отложенное разрешение имён типов (mark_type/resolve_type_markers) ----

constexpr char TYPE_MARK_OPEN = '\x01';
constexpr char TYPE_MARK_CLOSE = '\x02';

// dotted может отсутствовать (std::nullopt соответствует Python None) ->
// "Object"; пустая строка возвращается как есть (мимикрирует `dotted == ""`
// в оригинале, который не подставляет "Object" для пустой, а только для None).
std::string mark_type(const std::optional<std::string>& dotted);

std::string resolve_type_markers(const std::string& text, const std::unordered_set<std::string>& losers);

// ---- Signature-атрибут (generics), JVMS 4.7.9.1 ----

// None при неудаче разбора - вызывающий код (main.py/emit.py, будущие
// модули) должен в этом случае просто использовать обычный, не-generic тип.
std::optional<std::string> parse_field_signature(const std::string& sig);

struct MethodSignature {
    std::string type_params;             // "<T>" или ""
    std::vector<std::string> param_types;
    std::string return_type;
};
std::optional<MethodSignature> parse_method_signature(const std::string& sig);

struct ClassSignature {
    std::string type_params;             // "<T>" или ""
    std::string superclass;
    std::vector<std::string> interfaces;
};
std::optional<ClassSignature> parse_class_signature(const std::string& sig);

// ---- эвристики обфускации ----

bool is_safe_local_name(const std::string& name);

// kind: "class" | "method" | "field" | "package"
bool looks_obfuscated(const std::optional<std::string>& name, const std::string& kind = "class");

}  // namespace nd
