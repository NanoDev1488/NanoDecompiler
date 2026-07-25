// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.Unsigned16
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import jnr.ffi.Struct_NumberField;
import jnr.ffi.Struct_Offset;

public class Struct_Unsigned16 extends Struct_NumberField {

    // ---- поля ----
  final Struct this$0;

  public Struct_Unsigned16(Struct arg0) { // было: <init>
        super(arg0, NativeType.USHORT);
        this$0 = arg0;
    }

  public Struct_Unsigned16(Struct arg0, Struct_Offset arg1) { // было: <init>
        super(arg0, NativeType.USHORT, arg1);
        this$0 = arg0;
    }

  public final int get() {
        short var1 = getMemory().getShort(offset());
        return var1 >= 0 ? var1 : (var1 & 32767) + 32768;
    }

  public final void set(int arg0) {
        getMemory().putShort(offset(), ((short) arg0));
    }

  public void set(Number arg0) {
        getMemory().putShort(offset(), arg0.shortValue());
    }

  public final int intValue() {
        return get();
    }

}