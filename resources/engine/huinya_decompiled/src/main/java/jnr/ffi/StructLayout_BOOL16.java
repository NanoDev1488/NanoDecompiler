// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout.BOOL16
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_AbstractBoolean;
import jnr.ffi.StructLayout_Offset;

public final class StructLayout_BOOL16 extends StructLayout_AbstractBoolean {

    // ---- поля ----
  final StructLayout this$0;

  protected StructLayout_BOOL16(StructLayout arg0) { // было: <init>
        super(arg0, NativeType.SSHORT);
        this$0 = arg0;
    }

  protected StructLayout_BOOL16(StructLayout arg0, StructLayout_Offset arg1) { // было: <init>
        super(arg0, NativeType.SSHORT, arg1);
        this$0 = arg0;
    }

  public final boolean get(Pointer arg0) {
        return arg0.getShort(offset()) != 0;
    }

  public final void set(Pointer arg0, boolean arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: aload_0
        //      2: invokevirtual  #14 // jnr.ffi.StructLayout$BOOL16.offset:()J
        //      5: iload_2
        //      6: ifeq  13 (offset +7)
        //      9: iconst_1
        //     10: goto  14 (offset +4)
        //     13: iconst_0
        //     14: i2s
        //     15: invokevirtual  #10 // jnr.ffi.Pointer.putShort:(JS)V
        //     18: return
    }

  public String toString(Pointer arg0) {
        return super.toString(arg0);
    }

}