// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout.Boolean
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_AbstractBoolean;
import jnr.ffi.StructLayout_Offset;

public final class StructLayout_Boolean extends StructLayout_AbstractBoolean {

    // ---- поля ----
  final StructLayout this$0;

  protected StructLayout_Boolean(StructLayout arg0) { // было: <init>
        super(arg0, NativeType.SCHAR);
        this$0 = arg0;
    }

  protected StructLayout_Boolean(StructLayout arg0, StructLayout_Offset arg1) { // было: <init>
        super(arg0, NativeType.SCHAR, arg1);
        this$0 = arg0;
    }

  public final boolean get(Pointer arg0) {
        return arg0.getByte(offset()) != 0;
    }

  public final void set(Pointer arg0, boolean arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: aload_0
        //      2: invokevirtual  #13 // jnr.ffi.StructLayout$Boolean.offset:()J
        //      5: iload_2
        //      6: ifeq  13 (offset +7)
        //      9: iconst_1
        //     10: goto  14 (offset +4)
        //     13: iconst_0
        //     14: i2b
        //     15: invokevirtual  #10 // jnr.ffi.Pointer.putByte:(JB)V
        //     18: return
    }

}