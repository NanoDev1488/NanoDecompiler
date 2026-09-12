// renamer.hpp - порт класса Renamer из resources/engine/main.py (v2.0,
// HANDOFF_40 продолжение). Эвристическая деобфускация имён классов/
// методов/полей/пакетов: мусорные короткие/бессмысленные имена -> читаемые
// (ClassA1, method3, field7, pkg2...), НЕ-обфусцированные имена оставляет
// как есть. Решение "обфусцировано или нет" делает looks_obfuscated()
// (javatypes.hpp) - здесь только генерация новых имён и кэширование
// (internal_name -> new_name), 1:1 с оригиналом.
#pragma once

#include <map>
#include <string>
#include <tuple>
#include <unordered_map>
#include <unordered_set>

#include "stackvm.hpp"  // IRenamer - HANDOFF_42: decompile_method_body берёт
                         // renamer именно через этот интерфейс (const&), см. ниже

namespace nd {

// Реализует IRenamer (stackvm.hpp) - НУЖНО, чтобы decompile_method_body()
// (engine.hpp, принимает const IRenamer&) мог использовать ИМЕННО этот,
// настоящий Renamer, а не только заглушку IdentityRenamer, для которой
// интерфейс изначально проектировался (HANDOFF_36, main.py тогда ещё не
// был перенесён). Публичные методы поэтому const, а не мутируют объект
// напрямую - кэши/счётчики лежат в mutable-полях (семантически это тот же
// ленивый кэш "запомнить придуманное имя", что и в оригинале, просто без
// того, чтобы вызывающий код обязательно держал неconst-ссылку).
class Renamer : public IRenamer {
public:
    // internal_name (a/b/C или a/b/C$1$D) -> новое internal-имя
    // (обфусцированные сегменты заменены, "$" склеен через "_" - каждый
    // .class у нас отдельный top-level файл, "Outer.Inner" не резолвился бы).
    std::string friendly_class(const std::string& internal_name) const override;
    bool class_map_contains(const std::string& internal_name) const override { return class_map_.count(internal_name) != 0; }

    // pkg_internal ("a/b/c") -> новый internal-путь пакета.
    std::string friendly_package(const std::string& pkg_internal) const;

    // IRenamer называет их field_name/method_name - friendly_field/
    // friendly_method оставлены как публичные алиасы для main.cpp
    // (render_class), где это имя ближе к оригинальному
    // `renamer.friendly_method(...)` вызову в main.py.
    std::string method_name(const std::string& owner_internal, const std::string& name, const std::string& desc) const override;
    std::string field_name(const std::string& owner_internal, const std::string& name, const std::string& desc) const override;
    std::string friendly_method(const std::string& owner_internal, const std::string& name, const std::string& desc) const {
        return method_name(owner_internal, name, desc);
    }
    std::string friendly_field(const std::string& owner_internal, const std::string& name, const std::string& desc) const {
        return field_name(owner_internal, name, desc);
    }

    // naming_hints.py (генератор смысловых имён по эвристике использования)
    // НЕ перенесён в этой сессии - не было доступа к оригиналу. Если/когда
    // появится порт, подсказки кладутся сюда ПЕРЕД вызовом friendly_class():
    // internal_name -> предложенное простое имя (последний сегмент, без
    // пакета/Outer$). До тех пор эта карта пустует - поведение как если бы
    // подсказок не было вообще (эквивалент class_name_hints={} в Python),
    // т.е. чисто нумерованные ClassA1/ClassA2/... для всех обфусцированных
    // имён - НЕ баг порта, а отсутствующая (пока) фича апстрима.
    std::map<std::string, std::string> class_name_hints;

    const std::map<std::string, std::string>& class_map() const { return class_map_; }
    const std::map<std::string, std::string>& package_map() const { return package_map_; }
    using MethodKey = std::tuple<std::string, std::string, std::string>;  // (owner, name, desc)
    const std::map<MethodKey, std::string>& method_map() const { return method_map_; }
    const std::map<MethodKey, std::string>& field_map() const { return field_map_; }

private:
    mutable std::map<std::string, std::string> class_map_;
    mutable std::map<MethodKey, std::string> method_map_;
    mutable std::map<MethodKey, std::string> field_map_;
    mutable std::map<std::string, std::string> package_map_;
    mutable int class_ctr_ = 0;
    mutable int method_ctr_ = 0;
    mutable int field_ctr_ = 0;
    mutable int pkg_ctr_ = 0;
    mutable std::unordered_set<std::string> used_hint_names_;
};

}  // namespace nd

