# -*- coding: utf-8 -*-
"""
Удаление "мусорных" catch-блоков вида `catch (Throwable t) { throw t; }` -
семантически no-op (перехватить и тут же перебросить дальше - как будто
блока не было вообще), частый приём обфускаторов для увеличения числа
try/catch в методе (усложняет структуризацию декомпилятору, не меняя
поведение программы). Портировано с Rust-инструмента пользователя
(catchclean.rs), переписано на Python под наши структуры данных.

БЕЗОПАСНО ПО КОНСТРУКЦИИ: catch-and-rethrow функционально прозрачен -
`try { X } catch (T t) { throw t; }` и просто `X` ведут себя ИДЕНТИЧНО с
точки зрения любого внешнего наблюдателя (то же исключение долетит туда
же). Убираем ТОЛЬКО эту точную последовательность байткода - никаких
эвристик "похоже, что не используется" или подобного.
"""

_ASTORE = 0x3a
_ASTORE_N = {0x4b: 0, 0x4c: 1, 0x4d: 2, 0x4e: 3}
_ALOAD = 0x19
_ALOAD_N = {0x2a: 0, 0x2b: 1, 0x2c: 2, 0x2d: 3}
_ATHROW = 0xbf


def _parse_store_or_load(code, pos, store_opcode, store_n_map, load_opcode, load_n_map, is_store):
    """Возвращает (slot, следующая_позиция) или None, если по этому смещению
    не astore/aload (в зависимости от is_store)."""
    if pos >= len(code):
        return None
    op = code[pos]
    if is_store:
        if op == store_opcode:
            if pos + 1 >= len(code):
                return None
            return code[pos + 1], pos + 2
        if op in store_n_map:
            return store_n_map[op], pos + 1
    else:
        if op == load_opcode:
            if pos + 1 >= len(code):
                return None
            return code[pos + 1], pos + 2
        if op in load_n_map:
            return load_n_map[op], pos + 1
    return None


def is_pure_rethrow_handler(code, handler_pc):
    """True, если байткод, начинающийся ровно с handler_pc, - это ЛИБО
    голый `athrow` (пойманное исключение перебрасывается прямо со стека, не
    сохраняя в переменную), ЛИБО `astore S; aload S; athrow` (сохранили,
    сразу же перечитали, перебросили - то же самое, просто через
    переменную - типичный вид декомпилированного `catch(X e){ throw e; }`)."""
    if handler_pc >= len(code):
        return False
    if code[handler_pc] == _ATHROW:
        return True
    st = _parse_store_or_load(code, handler_pc, _ASTORE, _ASTORE_N, _ALOAD, _ALOAD_N, is_store=True)
    if st is None:
        return False
    slot, pos = st
    ld = _parse_store_or_load(code, pos, _ASTORE, _ASTORE_N, _ALOAD, _ALOAD_N, is_store=False)
    if ld is None:
        return False
    ld_slot, pos2 = ld
    if ld_slot != slot:
        return False
    return pos2 < len(code) and code[pos2] == _ATHROW


def filter_junk_catches(method):
    """Возвращает (новый_список_exceptions, сколько_убрано). Ничего не
    мутирует - вызывающий код (engine.py) сам решает, использовать ли
    отфильтрованный список."""
    if not method.code or not method.exceptions:
        return method.exceptions, 0
    kept = []
    removed = 0
    for entry in method.exceptions:
        if is_pure_rethrow_handler(method.code, entry.handler_pc):
            removed += 1
            continue
        kept.append(entry)
    return kept, removed
