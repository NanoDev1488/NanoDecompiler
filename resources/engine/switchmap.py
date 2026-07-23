# -*- coding: utf-8 -*-
"""
javac компилирует `switch (enumVar) { case CONST: ... }` не напрямую, а через
синтетический вложенный класс вида Outer$1 с полем
`static final int[] $SwitchMap$pkg$EnumType`, которое заполняется в
<clinit> через `array[EnumType.CONST.ordinal()] = N;` (в try/catch
NoSuchFieldError на каждую константу). Сам метод при этом делает
`array[enumVar.ordinal()]` и свитчится по числу N.

Этот модуль находит такие синтетические классы, вручную разбирает их
<clinit> (без полной символической интерпретации - тут это не нужно,
паттерн линейный и жёстко фиксирован) и строит обратную таблицу
N -> имя константы, чтобы движок мог восстановить настоящий
`switch (enumVar) { case CONST: ... }` вместо уродливого индекса по массиву.
"""
from ir import decode_method


def detect_switchmaps(class_files):
    """Возвращает (switchmap_fields, synthetic_class_internals):
    switchmap_fields: dict (owner_class_internal, field_name) -> {
        'enum_owner': enum_class_internal, 'table': {int_value: const_name}
    }
    synthetic_class_internals: set имён классов, которые целиком являются
    этим сгенерированным компилятором артефактом и не должны попадать в
    итоговый вывод .java (в исходнике их никогда не было)."""
    switchmap_fields = {}
    synthetic_classes = set()

    for internal, cf in class_files.items():
        candidate_fields = [f for f in cf.fields
                             if f.descriptor == "[I" and f.name.startswith("$SwitchMap$")]
        if not candidate_fields:
            continue
        clinit = next((m for m in cf.methods if m.name == "<clinit>"), None)
        if clinit is None or clinit.code is None:
            continue
        try:
            instrs, order = decode_method(clinit.code)
        except Exception:
            continue

        for f in candidate_fields:
            table = _extract_table(cf, instrs, order, f.name)
            if table:
                switchmap_fields[(internal, f.name)] = {
                    "enum_owner": table["enum_owner"],
                    "table": table["table"],
                }
        # Класс целиком - синтетический switch-map холдер (не часть исходника),
        # если ВСЕ его поля - это найденные $SwitchMap$ массивы, и единственный
        # метод - <clinit>.
        if len(cf.fields) == len(candidate_fields) and \
                all((internal, f.name) in switchmap_fields for f in candidate_fields) and \
                len([m for m in cf.methods if m.name != "<clinit>"]) == 0:
            synthetic_classes.add(internal)

    return switchmap_fields, synthetic_classes


def _extract_table(cf, instrs, order, field_name):
    """Линейно ищем паттерн:
        getstatic  <field_name>:[I
        getstatic  EnumOwner.CONST:LEnumOwner;
        invokevirtual  ...ordinal:()I
        <push int N>            (bipush/sipush/iconst_N)
        iastore
    Границы try/catch(NoSuchFieldError) не мешают - сканируем сырую
    последовательность инструкций, игнорируя exception table целиком."""
    table = {}
    enum_owner = None
    seq = [instrs[pc] for pc in order]
    n = len(seq)
    i = 0
    while i < n:
        ins = seq[i]
        if ins.mnemonic == "getstatic":
            r = cf.ref_string(ins.cp_index)
            if r and r[1] == field_name and r[2] == "[I":
                # ищем непосредственно следующие 4 инструкции
                if i + 4 < n:
                    g2, ov, push_ins, store = seq[i + 1], seq[i + 2], seq[i + 3], seq[i + 4]
                    if g2.mnemonic == "getstatic" and ov.mnemonic == "invokevirtual" and \
                            store.mnemonic == "iastore":
                        r2 = cf.ref_string(g2.cp_index)
                        rv = cf.ref_string(ov.cp_index)
                        if r2 and rv and rv[1] == "ordinal":
                            owner = r2[0]
                            const_name = r2[1]
                            val = _push_int_value(push_ins)
                            if val is not None:
                                table[val] = const_name
                                enum_owner = owner
                                i += 5
                                continue
        i += 1
    if not table or enum_owner is None:
        return None
    return {"enum_owner": enum_owner, "table": table}


def _push_int_value(ins):
    if ins.mnemonic.startswith("iconst_"):
        v = ins.mnemonic.split("_")[1]
        return -1 if v == "m1" else int(v)
    if ins.mnemonic in ("bipush", "sipush"):
        return ins.ival
    return None
