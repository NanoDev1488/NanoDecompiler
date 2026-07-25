// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout.ssize_t
package jnr.ffi;

import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_IntegerAlias;
import jnr.ffi.StructLayout_Offset;
import jnr.ffi.TypeAlias;

public final class StructLayout_ssize_t extends StructLayout_IntegerAlias {

    // ---- поля ----
  final StructLayout this$0;

  public StructLayout_ssize_t(StructLayout arg0) { // было: <init>
        super(arg0, TypeAlias.ssize_t);
        this$0 = arg0;
    }

  public StructLayout_ssize_t(StructLayout arg0, StructLayout_Offset arg1) { // было: <init>
        super(arg0, TypeAlias.ssize_t, arg1);
        this$0 = arg0;
    }

}