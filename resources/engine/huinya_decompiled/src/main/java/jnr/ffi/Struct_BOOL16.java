// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.BOOL16
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import jnr.ffi.Struct_AbstractBoolean;

public final class Struct_BOOL16 extends Struct_AbstractBoolean {

    // ---- поля ----
  final Struct this$0;

  public Struct_BOOL16(Struct arg0) { // было: <init>
        super(arg0, NativeType.SSHORT);
        this$0 = arg0;
    }

  public final boolean get() {
        return getMemory().getShort(offset()) != 0;
    }

  public final void set(boolean arg0) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_0
        //      1: invokevirtual  #12 // jnr.ffi.Struct$BOOL16.getMemory:()Ljnr/ffi/Pointer;
        //      4: aload_0
        //      5: invokevirtual  #13 // jnr.ffi.Struct$BOOL16.offset:()J
        //      8: iload_1
        //      9: ifeq  16 (offset +7)
        //     12: iconst_1
        //     13: goto  17 (offset +4)
        //     16: iconst_0
        //     17: i2s
        //     18: invokevirtual  #9 // jnr.ffi.Pointer.putShort:(JS)V
        //     21: return
    }

  public String toString() {
        return super.toString();
    }

}