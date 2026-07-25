// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout.UTF8StringRef
package jnr.ffi;

import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_Offset;
import jnr.ffi.StructLayout_UTFStringRef;

public class StructLayout_UTF8StringRef extends StructLayout_UTFStringRef {

    // ---- поля ----
  final StructLayout this$0;

  public StructLayout_UTF8StringRef(StructLayout arg0, int arg1) { // было: <init>
        super(arg0, arg1, StructLayout.UTF8);
        this$0 = arg0;
    }

  public StructLayout_UTF8StringRef(StructLayout arg0, int arg1, StructLayout_Offset arg2) { // было: <init>
        super(arg0, arg1, StructLayout.UTF8, arg2);
        this$0 = arg0;
    }

  public StructLayout_UTF8StringRef(StructLayout arg0) { // было: <init>
        super(arg0, 2147483647, StructLayout.UTF8);
        this$0 = arg0;
    }

}