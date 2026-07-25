// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout.Enum
package jnr.ffi;

import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_Enum32;
import jnr.ffi.StructLayout_Offset;

public class StructLayout_Enum extends StructLayout_Enum32 {

    // ---- поля ----
  final StructLayout this$0;

  public StructLayout_Enum(StructLayout arg0, Class arg1) { // было: <init>
        super(arg0, arg1);
        this$0 = arg0;
    }

  public StructLayout_Enum(StructLayout arg0, Class arg1, StructLayout_Offset arg2) { // было: <init>
        super(arg0, arg1, arg2);
        this$0 = arg0;
    }

}