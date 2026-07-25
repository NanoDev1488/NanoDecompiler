// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout.Padding
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_AbstractField;
import jnr.ffi.StructLayout_Offset;
import jnr.ffi.Type;

public final class StructLayout_Padding extends StructLayout_AbstractField {

    // ---- поля ----
  final StructLayout this$0;

  public StructLayout_Padding(StructLayout arg0, Type arg1, int arg2) { // было: <init>
        super(arg0, arg1.size() * arg2, arg1.alignment());
        this$0 = arg0;
    }

  public StructLayout_Padding(StructLayout arg0, Type arg1, int arg2, StructLayout_Offset arg3) { // было: <init>
        super(arg0, arg1.size() * arg2, arg1.alignment(), arg3);
        this$0 = arg0;
    }

  public StructLayout_Padding(StructLayout arg0, NativeType arg1, int arg2) { // было: <init>
        this(arg0, arg0.getRuntime().findType(arg1), arg2);
    }

  public StructLayout_Padding(StructLayout arg0, NativeType arg1, int arg2, StructLayout_Offset arg3) { // было: <init>
        this(arg0, arg0.getRuntime().findType(arg1), arg2);
    }

}