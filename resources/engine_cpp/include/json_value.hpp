// json_value.hpp - минимальный JSON-парсер (только чтение) без внешних
// зависимостей - нужен legitimacy_check.cpp для разбора ответов GitHub/
// Modrinth/Spiget API (Python-оригинал использует stdlib `json`). Не
// Python-модуль сам по себе - общая инфраструктура, см. HANDOFF_32.
#pragma once

#include <cstdint>  // БАГ-ФИКС: MinGW/Windows не тянет int64_t транзитивно через другие заголовки, как это молча делает libstdc++ на Linux - см. ошибку сборки Windows-раннера в этой сессии.
#include <map>
#include <memory>
#include <optional>
#include <string>
#include <variant>
#include <vector>

namespace nd {

class JsonValue;
using JsonArray = std::vector<JsonValue>;
// Порядок ключей объекта сохраняется (как Python dict) - вектор пар, а не map.
using JsonObject = std::vector<std::pair<std::string, JsonValue>>;

class JsonValue {
public:
    enum class Kind { Null, Bool, Number, String, Array, Object };
    Kind kind = Kind::Null;
    bool bool_v = false;
    double num_v = 0.0;
    std::string str_v;
    std::shared_ptr<JsonArray> arr_v;
    std::shared_ptr<JsonObject> obj_v;

    bool is_object() const { return kind == Kind::Object; }
    bool is_array() const { return kind == Kind::Array; }
    bool is_string() const { return kind == Kind::String; }

    // Для объекта: значение по ключу, если есть и объект действительно объект.
    const JsonValue* get(const std::string& key) const {
        if (kind != Kind::Object) return nullptr;
        for (auto& [k, v] : *obj_v) {
            if (k == key) return &v;
        }
        return nullptr;
    }
    std::optional<std::string> as_string() const {
        if (kind != Kind::String) return std::nullopt;
        return str_v;
    }
    std::optional<int64_t> as_int() const {
        if (kind != Kind::Number) return std::nullopt;
        return static_cast<int64_t>(num_v);
    }
};

// nullopt на любой ошибке разбора (зеркалит `except Exception: return None`
// вокруг json.loads в str_decrypt.py-подобных местах оригинала).
std::optional<JsonValue> json_parse(const std::string& text);

}  // namespace nd
