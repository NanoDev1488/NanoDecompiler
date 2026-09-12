// ast_nodes.hpp - порт resources/engine/ast_nodes.py (v2.0, HANDOFF_29,
// модуль 6). AST-узлы восстановленного Java-кода: выражения (Expr) и
// операторы (Stmt). Печать в текст - в emit.cpp (портирован, см. HANDOFF_42).
//
// Дизайн: в Python это простые классы с мутируемыми полями (в частности,
// stackvm.py в ряде мест переприсваивает `.type` ПОСЛЕ конструирования, и
// _PendingNew мутируется на месте через invokespecial, чтобы все
// dup-копии - тот же объект - синхронно "стали" готовым вызовом). Чтобы
// сохранить эту же ссылочную семантику, узлы здесь - классы с виртуальными
// методами за std::shared_ptr<Expr>/std::shared_ptr<Stmt> (аналог ссылки
// на объект в Python), а не value-типы/variant.
#pragma once

#include <map>
#include <memory>
#include <optional>
#include <string>
#include <vector>

namespace nd {

// ---------------- expressions ----------------

enum class ExprKind {
    Const, Local, This, FieldAccess, ArrayAccess, MethodCall, NewObject,
    NewArray, Cast, InstanceOf, BinOp, UnOp, Ternary, Assign, Raw,
    ClassLiteral, Lambda,
};

class Expr {
public:
    virtual ~Expr() = default;
    ExprKind kind;
    std::string type = "Object";  // мутируемое поле - как в Python

    explicit Expr(ExprKind k) : kind(k) {}

    virtual int prec() const { return 0; }

    // width: 2 для long/double, иначе 1 (property в Python)
    int width() const { return (type == "long" || type == "double") ? 2 : 1; }
};
using ExprPtr = std::shared_ptr<Expr>;

class Const : public Expr {
public:
    std::string literal;              // готовый Java-литерал строкой, напр. "42", "\"abc\"", "null"
    std::optional<std::string> raw;   // для type=="String" - сырая строка (для str_decrypt.py)
    Const(std::string literal_, std::string type_ = "int", std::optional<std::string> raw_ = std::nullopt)
        : Expr(ExprKind::Const), literal(std::move(literal_)), raw(std::move(raw_)) {
        type = std::move(type_);
    }
    int prec() const override { return 100; }
};

class Local : public Expr {
public:
    std::string name;
    Local(std::string name_, std::string type_ = "Object") : Expr(ExprKind::Local), name(std::move(name_)) {
        type = std::move(type_);
    }
    int prec() const override { return 100; }
};

class This : public Expr {
public:
    This() : Expr(ExprKind::This) { type = "this"; }
    int prec() const override { return 100; }
};

class FieldAccess : public Expr {
public:
    ExprPtr target;  // nullptr - static
    std::string name;
    bool is_static;
    std::optional<std::string> owner;
    FieldAccess(ExprPtr target_, std::string name_, std::string type_ = "Object", bool static_ = false,
                std::optional<std::string> owner_ = std::nullopt)
        : Expr(ExprKind::FieldAccess), target(std::move(target_)), name(std::move(name_)),
          is_static(static_), owner(std::move(owner_)) {
        type = std::move(type_);
    }
    int prec() const override { return 95; }
};

class ArrayAccess : public Expr {
public:
    ExprPtr array;
    ExprPtr index;
    ArrayAccess(ExprPtr array_, ExprPtr index_, std::string type_ = "Object")
        : Expr(ExprKind::ArrayAccess), array(std::move(array_)), index(std::move(index_)) {
        type = std::move(type_);
    }
    int prec() const override { return 95; }
};

class MethodCall : public Expr {
public:
    ExprPtr target;  // nullptr допустим (static, либо цель не задана)
    std::string name;
    std::vector<ExprPtr> args;
    bool is_static;
    std::optional<std::string> owner;
    bool is_ctor;
    bool is_super;
    bool interface;
    MethodCall(ExprPtr target_, std::string name_, std::vector<ExprPtr> args_, std::string type_ = "Object",
               bool static_ = false, std::optional<std::string> owner_ = std::nullopt, bool is_ctor_ = false,
               bool is_super_ = false, bool interface_ = false)
        : Expr(ExprKind::MethodCall), target(std::move(target_)), name(std::move(name_)), args(std::move(args_)),
          is_static(static_), owner(std::move(owner_)), is_ctor(is_ctor_), is_super(is_super_), interface(interface_) {
        type = std::move(type_);
    }
    int prec() const override { return 95; }
};

class NewObject : public Expr {
public:
    std::vector<ExprPtr> args;
    std::optional<std::string> anon_body;  // placeholder - тело анонимного класса пока не моделируем детально
    NewObject(std::string type_, std::vector<ExprPtr> args_, std::optional<std::string> anon_body_ = std::nullopt)
        : Expr(ExprKind::NewObject), args(std::move(args_)), anon_body(std::move(anon_body_)) {
        type = std::move(type_);
    }
    int prec() const override { return 95; }
};

class NewArray : public Expr {
public:
    std::string elem_type;
    std::vector<ExprPtr> dims;  // размеры (снаружи внутрь), элемент может быть nullptr
    // Список элементов инициализатора {a, b, c} - ВАЖНО: список, а не
    // одно значение (emit.py делает `", ".join(emit_expr(v) for v in
    // e.initializer)`). std::nullopt - нет инициализатора (обычный `new T[n]`);
    // пустой vector - есть, но пуст (`new T[]{}`) - это РАЗНЫЕ случаи, как
    // None vs [] в Python.
    std::optional<std::vector<ExprPtr>> initializer;
    NewArray(std::string elem_type_, std::vector<ExprPtr> dims_, std::optional<std::vector<ExprPtr>> initializer_ = std::nullopt)
        : Expr(ExprKind::NewArray), elem_type(std::move(elem_type_)), dims(std::move(dims_)),
          initializer(std::move(initializer_)) {
        std::string suffix;
        for (size_t i = 0; i < dims.size(); ++i) suffix += "[]";
        type = elem_type + suffix;
    }
    int prec() const override { return 95; }
};

class Cast : public Expr {
public:
    ExprPtr expr;
    Cast(std::string type_, ExprPtr expr_) : Expr(ExprKind::Cast), expr(std::move(expr_)) { type = std::move(type_); }
    // HANDOFF_49: 100, а не "естественный" приоритет каста (был 85) - emit.cpp
    // печатает Cast ВСЕГДА уже полностью самообёрнутым в скобки
    // ("((Type) expr)", см. case ExprKind::Cast в emit.cpp) - если оставить
    // тут заниженный приоритет, вызывающий код (paren() в emit.cpp) добавит
    // ЕЩЁ одну пару скобок поверх уже готового результата - двойные скобки
    // портили читаемость почти в каждом декомпилированном каст-выражении
    // (напр. record-equals из ObjectMethods.bootstrap - см. HANDOFF_49).
    int prec() const override { return 100; }
};

class InstanceOf : public Expr {
public:
    ExprPtr expr;
    std::string check_type;
    InstanceOf(ExprPtr expr_, std::string type_) : Expr(ExprKind::InstanceOf), expr(std::move(expr_)), check_type(std::move(type_)) {
        type = "boolean";
    }
    int prec() const override { return 70; }
};

class BinOp : public Expr {
public:
    std::string op;
    ExprPtr left;
    ExprPtr right;
    BinOp(std::string op_, ExprPtr left_, ExprPtr right_, std::string type_ = "int")
        : Expr(ExprKind::BinOp), op(std::move(op_)), left(std::move(left_)), right(std::move(right_)) {
        type = std::move(type_);
    }
    int prec() const override {
        static const std::map<std::string, int> PREC = {
            {"*", 80}, {"/", 80}, {"%", 80},
            {"+", 75}, {"-", 75},
            {"<<", 70}, {">>", 70}, {">>>", 70},
            {"<", 65}, {">", 65}, {"<=", 65}, {">=", 65}, {"instanceof", 65},
            {"==", 60}, {"!=", 60},
            {"&", 55}, {"^", 50}, {"|", 45},
            {"&&", 40}, {"||", 35},
        };
        auto it = PREC.find(op);
        return it != PREC.end() ? it->second : 50;
    }
};

class UnOp : public Expr {
public:
    std::string op;
    ExprPtr expr;
    bool postfix;
    UnOp(std::string op_, ExprPtr expr_, std::string type_ = "int", bool postfix_ = false)
        : Expr(ExprKind::UnOp), op(std::move(op_)), expr(std::move(expr_)), postfix(postfix_) {
        type = std::move(type_);
    }
    int prec() const override { return 85; }
};

class Ternary : public Expr {
public:
    ExprPtr cond;
    ExprPtr tval;
    ExprPtr fval;
    Ternary(ExprPtr cond_, ExprPtr tval_, ExprPtr fval_, std::string type_ = "Object")
        : Expr(ExprKind::Ternary), cond(std::move(cond_)), tval(std::move(tval_)), fval(std::move(fval_)) {
        type = std::move(type_);
    }
    int prec() const override { return 20; }
};

class Assign : public Expr {
public:
    ExprPtr target;
    ExprPtr value;
    std::string op;
    Assign(ExprPtr target_, ExprPtr value_, std::string op_ = "=")
        : Expr(ExprKind::Assign), target(target_), value(std::move(value_)), op(std::move(op_)) {
        type = target ? target->type : "Object";  // getattr(target, "type", "Object")
    }
    int prec() const override { return 10; }
};

// Escape hatch: произвольный уже отформатированный Java-текст (для редких/неподдержанных случаев).
class Raw : public Expr {
public:
    std::string text;
    Raw(std::string text_, std::string type_ = "Object") : Expr(ExprKind::Raw), text(std::move(text_)) {
        type = std::move(type_);
    }
    int prec() const override { return 90; }
};

class ClassLiteral : public Expr {
public:
    std::string type_name;
    explicit ClassLiteral(std::string type_name_) : Expr(ExprKind::ClassLiteral), type_name(std::move(type_name_)) {
        type = "Class";
    }
    int prec() const override { return 95; }
};

class Lambda : public Expr {
public:
    std::vector<ExprPtr> params;         // list[Local]
    ExprPtr body_method_ref;             // MethodCall или NewObject
    Lambda(std::vector<ExprPtr> params_, ExprPtr body_method_ref_, std::string functional_type)
        : Expr(ExprKind::Lambda), params(std::move(params_)), body_method_ref(std::move(body_method_ref_)) {
        type = std::move(functional_type);
    }
    int prec() const override { return 15; }
};

// ---------------- statements ----------------

enum class StmtKind {
    ExprStmt, LocalDecl, ReturnStmt, ThrowStmt, IfStmt, WhileStmt, DoWhileStmt,
    ForStmt, BreakStmt, ContinueStmt, BlockStmt, SwitchStmt, TryStmt, SyncStmt,
    GotoStmt, LabelStmt, RawStmt,
};

class Stmt {
public:
    virtual ~Stmt() = default;
    StmtKind kind;
    explicit Stmt(StmtKind k) : kind(k) {}
};
using StmtPtr = std::shared_ptr<Stmt>;

class ExprStmtNode : public Stmt {
public:
    ExprPtr expr;
    explicit ExprStmtNode(ExprPtr expr_) : Stmt(StmtKind::ExprStmt), expr(std::move(expr_)) {}
};

class LocalDecl : public Stmt {
public:
    std::string type;
    std::string name;
    ExprPtr init;
    bool is_final;
    LocalDecl(std::string type_, std::string name_, ExprPtr init_ = nullptr, bool is_final_ = false)
        : Stmt(StmtKind::LocalDecl), type(std::move(type_)), name(std::move(name_)), init(std::move(init_)),
          is_final(is_final_) {}
};

class ReturnStmt : public Stmt {
public:
    ExprPtr expr;  // может отсутствовать (void return)
    explicit ReturnStmt(ExprPtr expr_ = nullptr) : Stmt(StmtKind::ReturnStmt), expr(std::move(expr_)) {}
};

class ThrowStmt : public Stmt {
public:
    ExprPtr expr;
    explicit ThrowStmt(ExprPtr expr_) : Stmt(StmtKind::ThrowStmt), expr(std::move(expr_)) {}
};

class IfStmt : public Stmt {
public:
    ExprPtr cond;
    std::vector<StmtPtr> then_body;
    std::optional<std::vector<StmtPtr>> else_body;
    IfStmt(ExprPtr cond_, std::vector<StmtPtr> then_body_, std::optional<std::vector<StmtPtr>> else_body_ = std::nullopt)
        : Stmt(StmtKind::IfStmt), cond(std::move(cond_)), then_body(std::move(then_body_)), else_body(std::move(else_body_)) {}
};

class WhileStmt : public Stmt {
public:
    ExprPtr cond;
    std::vector<StmtPtr> body;
    std::optional<std::string> label;
    WhileStmt(ExprPtr cond_, std::vector<StmtPtr> body_, std::optional<std::string> label_ = std::nullopt)
        : Stmt(StmtKind::WhileStmt), cond(std::move(cond_)), body(std::move(body_)), label(std::move(label_)) {}
};

class DoWhileStmt : public Stmt {
public:
    ExprPtr cond;
    std::vector<StmtPtr> body;
    std::optional<std::string> label;
    DoWhileStmt(ExprPtr cond_, std::vector<StmtPtr> body_, std::optional<std::string> label_ = std::nullopt)
        : Stmt(StmtKind::DoWhileStmt), cond(std::move(cond_)), body(std::move(body_)), label(std::move(label_)) {}
};

class ForStmt : public Stmt {
public:
    ExprPtr init;   // ВАЖНО: это Expr (не Stmt!) - emit.py вызывает emit_expr(s.init)
                     // напрямую; обычно Raw("int i = 0") или Assign. Может отсутствовать.
    ExprPtr cond;
    StmtPtr update;  // ВАЖНО: это Stmt (обычно ExprStmtNode) - emit.py делает
                      // emit_expr(s.update.expr), т.е. читает .expr ИЗ update.
                      // Может отсутствовать.
    std::vector<StmtPtr> body;
    std::optional<std::string> label;
    ForStmt(ExprPtr init_, ExprPtr cond_, StmtPtr update_, std::vector<StmtPtr> body_,
            std::optional<std::string> label_ = std::nullopt)
        : Stmt(StmtKind::ForStmt), init(std::move(init_)), cond(std::move(cond_)), update(std::move(update_)),
          body(std::move(body_)), label(std::move(label_)) {}
};

class BreakStmt : public Stmt {
public:
    std::optional<std::string> label;
    explicit BreakStmt(std::optional<std::string> label_ = std::nullopt) : Stmt(StmtKind::BreakStmt), label(std::move(label_)) {}
};

class ContinueStmt : public Stmt {
public:
    std::optional<std::string> label;
    explicit ContinueStmt(std::optional<std::string> label_ = std::nullopt) : Stmt(StmtKind::ContinueStmt), label(std::move(label_)) {}
};

class BlockStmt : public Stmt {
public:
    std::vector<StmtPtr> stmts;
    std::optional<std::string> label;
    explicit BlockStmt(std::vector<StmtPtr> stmts_ = {}, std::optional<std::string> label_ = std::nullopt)
        : Stmt(StmtKind::BlockStmt), stmts(std::move(stmts_)), label(std::move(label_)) {}
};

struct SwitchCase {
    std::vector<std::string> values;  // литералы (обычно int, но представлены строкой как в остальном AST)
    std::vector<StmtPtr> body;
    bool is_default;
};

class SwitchStmt : public Stmt {
public:
    ExprPtr selector;
    std::vector<SwitchCase> cases;
    std::optional<std::string> label;
    SwitchStmt(ExprPtr selector_, std::vector<SwitchCase> cases_, std::optional<std::string> label_ = std::nullopt)
        : Stmt(StmtKind::SwitchStmt), selector(std::move(selector_)), cases(std::move(cases_)), label(std::move(label_)) {}
};

struct CatchClause {
    std::string type;         // может быть "A|B|C" для multi-catch - формат решает emit.cpp
    std::string var_name;
    std::vector<StmtPtr> body;
};

class TryStmt : public Stmt {
public:
    std::vector<StmtPtr> body;
    std::vector<CatchClause> catches;
    std::optional<std::vector<StmtPtr>> finally_body;
    TryStmt(std::vector<StmtPtr> body_, std::vector<CatchClause> catches_,
            std::optional<std::vector<StmtPtr>> finally_body_ = std::nullopt)
        : Stmt(StmtKind::TryStmt), body(std::move(body_)), catches(std::move(catches_)), finally_body(std::move(finally_body_)) {}
};

class SyncStmt : public Stmt {
public:
    ExprPtr expr;
    std::vector<StmtPtr> body;
    SyncStmt(ExprPtr expr_, std::vector<StmtPtr> body_) : Stmt(StmtKind::SyncStmt), expr(std::move(expr_)), body(std::move(body_)) {}
};

// Fallback-выход, когда структуризация не смогла убрать переход.
class GotoStmt : public Stmt {
public:
    std::string label;
    explicit GotoStmt(std::string label_) : Stmt(StmtKind::GotoStmt), label(std::move(label_)) {}
};

class LabelStmt : public Stmt {
public:
    std::string label;
    explicit LabelStmt(std::string label_) : Stmt(StmtKind::LabelStmt), label(std::move(label_)) {}
};

// Escape hatch: произвольная уже готовая строка (напр. комментарий/диагностика).
class RawStmt : public Stmt {
public:
    std::string text;
    explicit RawStmt(std::string text_) : Stmt(StmtKind::RawStmt), text(std::move(text_)) {}
};

}  // namespace nd
