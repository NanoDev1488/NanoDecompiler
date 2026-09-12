// engine.hpp - порт resources/engine/engine.py (v2.0, HANDOFF_39).
// Оркестрация декомпиляции ОДНОГО метода: CFG -> символическое исполнение
// -> межблочное согласование стека -> структуризация -> постобработка AST
// -> печать Java. При любой неуверенности (DecompileAbort/любое другое
// исключение) откатывается на честный дизассемблированный листинг байткода.
#pragma once

#include <cstdint>  // БАГ-ФИКС: MinGW/Windows не тянет int64_t транзитивно через другие заголовки, как это молча делает libstdc++ на Linux - см. ошибку сборки Windows-раннера в этой сессии.
#include <map>
#include <memory>
#include <optional>
#include <string>
#include <vector>

#include "ast_nodes.hpp"
#include "cfg.hpp"
#include "classfile.hpp"
#include "stackvm.hpp"

namespace nd {

struct MethodDecompileResult {
    bool ok = false;
    std::vector<std::string> java_lines;
    std::optional<std::string> reason;
    int n_instructions = 0;
    int n_blocks = 0;
    std::vector<StmtPtr> stmts;  // валиден только если ok
    std::vector<std::string> pre_lines;
    int junk_catches_removed = 0;
    // HANDOFF_42: main.cpp (render_class) нужны имена параметров (должны
    // совпадать с теми, что реально использует ТЕЛО метода - см. коммент в
    // main.py про баг рассинхронизации "arg0" в сигнатуре vs "player" в
    // теле) и импорты, обнаруженные ВНУТРИ тела (напр. типы, встречающиеся
    // только в `new X()`/касте, не в самой сигнатуре) - раньше MethodCtx
    // был недоступен вызывающему коду (создавался и уничтожался целиком
    // внутри decompile_method_body). Валидны только при ok == true (как и
    // result.ctx в оригинале - main.py всегда проверяет result.ok перед
    // обращением к result.ctx).
    std::map<int, LocalInfo> locals;
    OrderedImports imports;
};

// enum_ordinals: internal-имя enum-класса -> список имён констант (в порядке
// объявления). switchmap_tables: (owner_internal, field_name синтетической
// $SwitchMap-таблицы) -> {N: имя_константы} - точная таблица из <clinit>
// синтетического switch-map класса (см. switchmap.hpp, HANDOFF_46 - модуль
// switchmap.py перенесён, вызывающий код (process_jar.cpp) заполняет эти
// контейнеры через detect_switchmaps()).
MethodDecompileResult decompile_method_body(
    const ClassFile& cf, const Method& method, const IRenamer& renamer,
    const std::map<std::string, std::string>& known_internal_by_dotted, const std::string& class_internal,
    int indent = 2, const std::map<std::string, std::vector<std::string>>& enum_ordinals = {},
    const std::map<std::pair<std::string, std::string>, std::map<int64_t, std::string>>& switchmap_tables = {});

// Честный дизассемблированный листинг - используется, когда декомпиляция
// метода не удалась (в самом ЭТОМ методе result.ok уже будет false и это
// вызывается ВНУТРИ decompile_method_body - но render_class.cpp/process_jar.cpp
// тоже вызывают это напрямую в некоторых местах - напр. для <clinit>,
// поэтому экспортируется).
std::vector<std::string> fallback_bytecode_listing(const ClassFile& cf, const Method& method, int indent = 2);

}  // namespace nd
