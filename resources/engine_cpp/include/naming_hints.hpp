// naming_hints.hpp - порт resources/engine/naming_hints.py (HANDOFF_46).
// Подсказки для переименования обфусцированных классов - НЕ угадывание, а
// чтение уже существующих в байткоде данных (строк/аннотаций), которые
// осмысленно называют класс.
//
// 1. by_annotation_name(): если МНОГО разных классов в jar'е несут одну и
//    ту же аннотацию с текстовым аргументом "name" - это, скорее всего,
//    самодельный DI/модульный фреймворк - берём объявленное имя вместо
//    генерического ClassA7.
// 2. by_brigadier_super_call(): специфично для Minecraft-плагинов на
//    Brigadier (командный API Mojang, 1.13+) - командные классы обычно
//    вызывают `super("имякоманды")` в конструкторе.
//
// Оба метода дают {internal_class_name: предложенное_имя} - используется
// Renamer'ом (renamer.hpp) как ПОДСКАЗКА при переименовании через
// Renamer::class_name_hints - если подсказки нет, поведение не меняется.
#pragma once

#include <functional>
#include <map>
#include <string>

#include "classfile.hpp"

namespace nd {

using LooksObfuscatedFn = std::function<bool(const std::string&, const std::string&)>;

std::map<std::string, std::string> hints_by_annotation_name(const std::map<std::string, ClassFile>& class_files,
                                                              const LooksObfuscatedFn& looks_obfuscated_fn);

std::map<std::string, std::string> hints_by_brigadier_super_call(const std::map<std::string, ClassFile>& class_files,
                                                                   const LooksObfuscatedFn& looks_obfuscated_fn);

}  // namespace nd
