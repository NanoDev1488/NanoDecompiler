// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.Unsigned8
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import jnr.ffi.Struct_NumberField;
import jnr.ffi.Struct_Offset;

public class Struct_Unsigned8 extends Struct_NumberField {

    // ---- поля ----
  final Struct this$0;

  public Struct_Unsigned8(Struct arg0) { // было: <init>
        super(arg0, NativeType.UCHAR);
        this$0 = arg0;
    }

  public Struct_Unsigned8(Struct arg0, Struct_Offset arg1) { // было: <init>
        super(arg0, NativeType.UCHAR, arg1);
        this$0 = arg0;
    }

  public final short get() {
        short var1 = ((short) getMemory().getByte(offset()));
        return var1 >= 0 ? var1 : ((short) ((var1 & 127) + 128));
    }

  public final void set(short arg0) {
        getMemory().putByte(offset(), ((byte) arg0));
    }

  public void set(Number arg0) {
        getMemory().putByte(offset(), arg0.byteValue());
    }

  public final short shortValue() {
        return get();
    }

  public final int intValue() {
        return get();
    }

}