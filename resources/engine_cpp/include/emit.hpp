// emit.hpp - порт resources/engine/emit.py (v2.0, HANDOFF_34). Печать AST
// (ast_nodes.hpp) в отформатированный Java-текст.
//
// ПРИМЕЧАНИЕ: оригинал импортирует `_MonitorMarker` из stackvm.py - здесь
// объявлен локально как отдельный подкласс Stmt (MonitorMarkerStmt),
// поскольку ast_nodes.hpp его не содержит (это внутренний тип stackvm.py,
// не часть публичной AST-схемы) - stackvm.hpp/.cpp (портирован, HANDOFF_35)
// переиспользует ИМЕННО этот тип (см. `#include "emit.hpp"` в stackvm.hpp).
#pragma once

#include <optional>
#include <string>
#include <vector>

#include "ast_nodes.hpp"

namespace nd {

struct MonitorMarkerStmt : public Stmt {
    std::string kind;  // "enter" | "exit" - зеркалит s.kind в Python _MonitorMarker
    ExprPtr expr;
    MonitorMarkerStmt(std::string kind_, ExprPtr expr_) : Stmt(StmtKind::RawStmt), kind(std::move(kind_)), expr(std::move(expr_)) {}
    // Примечание: используем StmtKind::RawStmt как "базовый" тег для
    // диспетчеризации через dynamic_cast в emit_stmt (см. .cpp) - реальный
    // Python-код различает типы через isinstance, а не через общее поле kind;
    // в C++ здесь это сделано через dynamic_cast<const MonitorMarkerStmt*>
    // ПЕРЕД общим switch по StmtKind, что не требует отдельного значения enum.
};

// Задаёт класс, чьё тело сейчас печатается (см. render_class.cpp - вызывает
// это перед рендерингом каждого класса) - влияет на самоквалификацию
// static-полей/методов.
void set_current_class(const std::optional<std::string>& dotted);

// Задаёт множество имён локальных переменных/параметров текущего метода -
// влияет на то, нужен ли `this.` перед полем.
void set_shadow_context(const std::vector<std::string>& local_names);
void clear_shadow_context();

std::string emit_expr(const ExprPtr& e);
std::vector<std::string> emit_stmt(const StmtPtr& s, int indent);
std::vector<std::string> emit_stmts(const std::vector<StmtPtr>& stmts, int indent);

}  // namespace nd
