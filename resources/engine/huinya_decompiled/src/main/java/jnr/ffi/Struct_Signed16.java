// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.Signed16
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import jnr.ffi.Struct_NumberField;
import jnr.ffi.Struct_Offset;

public class Struct_Signed16 extends Struct_NumberField {

    // ---- поля ----
  final Struct this$0;

  public Struct_Signed16(Struct arg0) { // было: <init>
        super(arg0, NativeType.SSHORT);
        this$0 = arg0;
    }

  public Struct_Signed16(Struct arg0, Struct_Offset arg1) { // было: <init>
        super(arg0, NativeType.SSHORT, arg1);
        this$0 = arg0;
    }

  public final short get() {
        return getMemory().getShort(offset());
    }

  public final void set(short arg0) {
        getMemory().putShort(offset(), arg0);
    }

  public void set(Number arg0) {
        getMemory().putShort(offset(), arg0.shortValue());
    }

  public final short shortValue() {
        return get();
    }

  public final int intValue() {
        return get();
    }

}