// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.intptr_t
package jnr.ffi;

import jnr.ffi.Struct;
import jnr.ffi.Struct_IntegerAlias;
import jnr.ffi.Struct_Offset;
import jnr.ffi.TypeAlias;

public final class Struct_intptr_t extends Struct_IntegerAlias {

    // ---- поля ----
  final Struct this$0;

  public Struct_intptr_t(Struct arg0) { // было: <init>
        super(arg0, TypeAlias.intptr_t);
        this$0 = arg0;
    }

  public Struct_intptr_t(Struct arg0, Struct_Offset arg1) { // было: <init>
        super(arg0, TypeAlias.intptr_t, arg1);
        this$0 = arg0;
    }

}