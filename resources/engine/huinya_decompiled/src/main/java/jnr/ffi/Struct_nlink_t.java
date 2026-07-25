// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.nlink_t
package jnr.ffi;

import jnr.ffi.Struct;
import jnr.ffi.Struct_IntegerAlias;
import jnr.ffi.Struct_Offset;
import jnr.ffi.TypeAlias;

public final class Struct_nlink_t extends Struct_IntegerAlias {

    // ---- поля ----
  final Struct this$0;

  public Struct_nlink_t(Struct arg0) { // было: <init>
        super(arg0, TypeAlias.nlink_t);
        this$0 = arg0;
    }

  public Struct_nlink_t(Struct arg0, Struct_Offset arg1) { // было: <init>
        super(arg0, TypeAlias.nlink_t, arg1);
        this$0 = arg0;
    }

}