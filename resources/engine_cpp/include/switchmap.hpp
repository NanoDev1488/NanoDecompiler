// switchmap.hpp - порт resources/engine/switchmap.py (HANDOFF_46).
//
// javac компилирует `switch (enumVar) { case CONST: ... }` не напрямую, а
// через синтетический вложенный класс вида Outer$1 с полем
// `static final int[] $SwitchMap$pkg$EnumType`, которое заполняется в
// <clinit> через `array[EnumType.CONST.ordinal()] = N;` (в try/catch
// NoSuchFieldError на каждую константу). Сам метод при этом делает
// `array[enumVar.ordinal()]` и свитчится по числу N.
//
// Этот модуль находит такие синтетические классы, вручную разбирает их
// <clinit> (без полной символической интерпретации - паттерн линейный и
// жёстко фиксирован) и строит обратную таблицу N -> имя константы, чтобы
// движок мог восстановить настоящий `switch (enumVar) { case CONST: ... }`
// вместо уродливого индекса по массиву.
#pragma once

#include <cstdint>
#include <map>
#include <set>
#include <string>
#include <utility>

#include "classfile.hpp"

namespace nd {

struct SwitchmapFieldInfo {
    std::string enum_owner;             // internal-имя enum-класса
    std::map<int64_t, std::string> table;  // N -> имя константы
};

struct SwitchmapDetectionResult {
    // (owner_class_internal, field_name) -> {enum_owner, table}
    std::map<std::pair<std::string, std::string>, SwitchmapFieldInfo> switchmap_fields;
    // Классы, которые ЦЕЛИКОМ являются этим сгенерированным компилятором
    // артефактом и не должны попадать в итоговый вывод .java (в исходнике
    // их никогда не было - весь класс это только $SwitchMap$-поля + <clinit>).
    std::set<std::string> synthetic_classes;
};

SwitchmapDetectionResult detect_switchmaps(const std::map<std::string, ClassFile>& class_files);

}  // namespace nd
