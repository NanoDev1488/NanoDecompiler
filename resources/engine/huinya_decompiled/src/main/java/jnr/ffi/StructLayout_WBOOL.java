// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout.WBOOL
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_AbstractBoolean;
import jnr.ffi.StructLayout_Offset;

public final class StructLayout_WBOOL extends StructLayout_AbstractBoolean {

    // ---- поля ----
  final StructLayout this$0;

  protected StructLayout_WBOOL(StructLayout arg0) { // было: <init>
        super(arg0, NativeType.SINT);
        this$0 = arg0;
    }

  protected StructLayout_WBOOL(StructLayout arg0, StructLayout_Offset arg1) { // было: <init>
        super(arg0, NativeType.SINT, arg1);
        this$0 = arg0;
    }

  public final boolean get(Pointer arg0) {
        return arg0.getInt(offset()) != 0;
    }

  public final void set(Pointer arg0, boolean arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: aload_0
        //      2: invokevirtual  #13 // jnr.ffi.StructLayout$WBOOL.offset:()J
        //      5: iload_2
        //      6: ifeq  13 (offset +7)
        //      9: iconst_1
        //     10: goto  14 (offset +4)
        //     13: iconst_0
        //     14: invokevirtual  #10 // jnr.ffi.Pointer.putInt:(JI)V
        //     17: return
    }

}