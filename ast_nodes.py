# -*- coding: utf-8 -*-
"""
AST-узлы восстановленного Java-кода: выражения (Expr) и операторы (Stmt).
Печать в текст делает emit.py.
"""


_WIDE2_TYPES = ("long", "double")


class Expr:
    type = "Object"

    def prec(self):
        return 0

    @property
    def width(self):
        return 2 if self.type in _WIDE2_TYPES else 1


class Const(Expr):
    def __init__(self, literal, type_="int"):
        self.literal = literal   # уже готовый Java-литерал строкой, напр. "42", "\"abc\"", "null"
        self.type = type_

    def prec(self):
        return 100


class Local(Expr):
    def __init__(self, name, type_="Object"):
        self.name = name
        self.type = type_

    def prec(self):
        return 100


class This(Expr):
    type = "this"
    def prec(self):
        return 100


class FieldAccess(Expr):
    def __init__(self, target, name, type_="Object", static=False, owner=None):
        self.target = target   # Expr or None (static)
        self.name = name
        self.type = type_
        self.static = static
        self.owner = owner

    def prec(self):
        return 95


class ArrayAccess(Expr):
    def __init__(self, array, index, type_="Object"):
        self.array = array
        self.index = index
        self.type = type_

    def prec(self):
        return 95


class MethodCall(Expr):
    def __init__(self, target, name, args, type_="Object", static=False,
                 owner=None, is_ctor=False, is_super=False, interface=False):
        self.target = target
        self.name = name
        self.args = args
        self.type = type_
        self.static = static
        self.owner = owner
        self.is_ctor = is_ctor
        self.is_super = is_super
        self.interface = interface

    def prec(self):
        return 95


class NewObject(Expr):
    def __init__(self, type_, args, anon_body=None):
        self.type = type_
        self.args = args
        self.anon_body = anon_body

    def prec(self):
        return 95


class NewArray(Expr):
    def __init__(self, elem_type, dims, initializer=None):
        self.elem_type = elem_type
        self.dims = dims  # list[Expr] sizes (outer to inner), may contain None
        self.type = elem_type + "[]" * len(dims)
        self.initializer = initializer

    def prec(self):
        return 95


class Cast(Expr):
    def __init__(self, type_, expr):
        self.type = type_
        self.expr = expr

    def prec(self):
        return 85


class InstanceOf(Expr):
    def __init__(self, expr, type_):
        self.expr = expr
        self.check_type = type_
        self.type = "boolean"

    def prec(self):
        return 70


class BinOp(Expr):
    _PREC = {
        "*": 80, "/": 80, "%": 80,
        "+": 75, "-": 75,
        "<<": 70, ">>": 70, ">>>": 70,
        "<": 65, ">": 65, "<=": 65, ">=": 65, "instanceof": 65,
        "==": 60, "!=": 60,
        "&": 55, "^": 50, "|": 45,
        "&&": 40, "||": 35,
    }

    def __init__(self, op, left, right, type_="int"):
        self.op = op
        self.left = left
        self.right = right
        self.type = type_

    def prec(self):
        return self._PREC.get(self.op, 50)


class UnOp(Expr):
    def __init__(self, op, expr, type_="int", postfix=False):
        self.op = op
        self.expr = expr
        self.type = type_
        self.postfix = postfix

    def prec(self):
        return 85


class Ternary(Expr):
    def __init__(self, cond, tval, fval, type_="Object"):
        self.cond = cond
        self.tval = tval
        self.fval = fval
        self.type = type_

    def prec(self):
        return 20


class Assign(Expr):
    def __init__(self, target, value, op="="):
        self.target = target
        self.value = value
        self.op = op
        self.type = getattr(target, "type", "Object")

    def prec(self):
        return 10


class Raw(Expr):
    """Escape hatch: произвольный уже отформатированный Java-текст (для редких/неподдержанных случаев)."""
    def __init__(self, text, type_="Object"):
        self.text = text
        self.type = type_

    def prec(self):
        return 90


class ClassLiteral(Expr):
    def __init__(self, type_name):
        self.type_name = type_name
        self.type = "Class"

    def prec(self):
        return 95


class Lambda(Expr):
    def __init__(self, params, body_method_ref, functional_type):
        self.params = params
        self.body_method_ref = body_method_ref
        self.type = functional_type

    def prec(self):
        return 15


# ---------------- statements ----------------

class Stmt:
    pass


class ExprStmt(Stmt):
    def __init__(self, expr):
        self.expr = expr


class LocalDecl(Stmt):
    def __init__(self, type_, name, init=None, is_final=False):
        self.type = type_
        self.name = name
        self.init = init
        self.is_final = is_final


class ReturnStmt(Stmt):
    def __init__(self, expr=None):
        self.expr = expr


class ThrowStmt(Stmt):
    def __init__(self, expr):
        self.expr = expr


class IfStmt(Stmt):
    def __init__(self, cond, then_body, else_body=None):
        self.cond = cond
        self.then_body = then_body
        self.else_body = else_body


class WhileStmt(Stmt):
    def __init__(self, cond, body, label=None):
        self.cond = cond
        self.body = body
        self.label = label


class DoWhileStmt(Stmt):
    def __init__(self, cond, body, label=None):
        self.cond = cond
        self.body = body
        self.label = label


class ForStmt(Stmt):
    def __init__(self, init, cond, update, body, label=None):
        self.init = init
        self.cond = cond
        self.update = update
        self.body = body
        self.label = label


class BreakStmt(Stmt):
    def __init__(self, label=None):
        self.label = label


class ContinueStmt(Stmt):
    def __init__(self, label=None):
        self.label = label


class BlockStmt(Stmt):
    def __init__(self, stmts=None, label=None):
        self.stmts = stmts or []
        self.label = label


class SwitchCase:
    def __init__(self, values, body, is_default=False):
        self.values = values   # list of literal ints (or [] )
        self.body = body       # list[Stmt]
        self.is_default = is_default


class SwitchStmt(Stmt):
    def __init__(self, selector, cases, label=None):
        self.selector = selector
        self.cases = cases     # list[SwitchCase]
        self.label = label


class TryStmt(Stmt):
    def __init__(self, body, catches, finally_body=None):
        self.body = body
        self.catches = catches   # list[(type, var_name, body)]
        self.finally_body = finally_body


class SyncStmt(Stmt):
    def __init__(self, expr, body):
        self.expr = expr
        self.body = body


class GotoStmt(Stmt):
    """Fallback-выход, когда структуризация не смогла убрать переход."""
    def __init__(self, label):
        self.label = label


class LabelStmt(Stmt):
    def __init__(self, label):
        self.label = label


class RawStmt(Stmt):
    """Escape hatch: произвольная уже готовая строка (напр. комментарий/диагностика)."""
    def __init__(self, text):
        self.text = text
