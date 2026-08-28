// render_class.hpp - порт render_class()/format_type_dotted()/
// _format_annotation()/_format_annotation_value()/format_field_constant()
// из resources/engine/main.py (v2.0, HANDOFF_42). Печать ОДНОГО .class ->
// текст .java файла.
//
// ЧЕСТНО ЗАДЕКЛАРИРОВАННЫЕ УПРОЩЕНИЯ ОТНОСИТЕЛЬНО ОРИГИНАЛА (см. подробности
// в render_class.cpp по месту и в HANDOFF_42):
//  1. enum-константы: имена печатаются БЕЗ реконструкции аргументов
//     конструктора из <clinit> (оригинал разбирает `new X(args)` внутри
//     <clinit> и подставляет их в `CONST(args)`, здесь - всегда `CONST`
//     без скобок). Компилируется, только если у enum'а конструктор с нулём
//     "настоящих" (после среза name/ordinal) параметров - иначе получится
//     невалидный Java (нет альтернативы без анализа <clinit>, который не
//     переносился в этой сессии).
//  2. interface со static-полями, требующими <clinit> для инициализации:
//     оригинал пытается разложить простые присваивания на inline-
//     инициализаторы полей; здесь этот путь ВСЕГДА считается "сложным" -
//     печатается предупреждающий комментарий вместо разложения (это ровно
//     тот же fallback-путь, что и в оригинале при неудаче разбора, просто
//     используется чаще).
#pragma once

#include <cstdint>  // БАГ-ФИКС: MinGW/Windows не тянет int64_t транзитивно через другие заголовки, как это молча делает libstdc++ на Linux - см. ошибку сборки Windows-раннера в этой сессии.
#include <map>
#include <string>
#include <vector>

#include "classfile.hpp"
#include "renamer.hpp"
#include "stackvm.hpp"  // OrderedImports
#include "verify.hpp"   // ProjectStats

namespace nd {

// java_type - уже "простой" java-тип (после field_descriptor_to_java, т.е.
// без начального 'L'/';', с "[]" на конце для массивов). Регистрирует
// импорт в all_imports (если не nullptr) и возвращает dotted-имя (без
// разрешения простое/FQN - этим занимается mark_type()/resolve_type_markers()
// на финальном проходе, см. render_class.cpp).
std::string format_type_dotted(const std::string& java_type, const Renamer& renamer,
                                const std::map<std::string, std::string>& known_internal_by_dotted,
                                OrderedImports* all_imports);

// nullopt - "печатать значение сложно/небезопасно" (структура/массив/enum-
// константа) - вызывающий код в этом случае вообще не печатает аргументы
// аннотации, как и оригинал.
std::optional<std::string> format_annotation_value(const AnnotationValue& v);

std::string format_annotation(const Annotation& ann, const Renamer& renamer,
                               const std::map<std::string, std::string>& known_internal_by_dotted,
                               OrderedImports& all_imports);

// entry - сырая ConstantValue-запись из пула констант (Field::constant_value).
// nullopt, если её нет ВООБЩЕ (нет "= ..." после поля), или если тег
// constant pool entry не из ожидаемого набора (не должно случаться для
// валидного ConstantValue-атрибута).
std::optional<std::string> format_field_constant(const ClassFile& cf, const std::optional<CpEntry>& entry,
                                                   const std::string& descriptor);

// Возвращает (текст .java файла, imports этого класса: dotted -> simple, в
// порядке первого обнаружения). switchmap_tables - см. engine.hpp/switchmap.hpp
// (портирован, HANDOFF_46) - process_jar.cpp заполняет реальными таблицами;
// пустая карта по умолчанию здесь - только чтобы render_class() был
// самодостаточным для юнит-тестов/харнессов без полного process_jar.cpp.
std::pair<std::string, OrderedImports> render_class(
    const ClassFile& cf, const Renamer& renamer, const std::map<std::string, std::string>& known_internal_by_dotted,
    ProjectStats& stats, const std::map<std::string, std::vector<std::string>>& enum_ordinals,
    const std::map<std::pair<std::string, std::string>, std::map<int64_t, std::string>>& switchmap_tables = {});

}  // namespace nd
