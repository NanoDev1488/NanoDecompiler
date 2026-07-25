// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.Signed8
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import jnr.ffi.Struct_NumberField;
import jnr.ffi.Struct_Offset;

public class Struct_Signed8 extends Struct_NumberField {

    // ---- поля ----
  final Struct this$0;

  public Struct_Signed8(Struct arg0) { // было: <init>
        super(arg0, NativeType.SCHAR);
        this$0 = arg0;
    }

  public Struct_Signed8(Struct arg0, Struct_Offset arg1) { // было: <init>
        super(arg0, NativeType.SCHAR, arg1);
        this$0 = arg0;
    }

  public final byte get() {
        return getMemory().getByte(offset());
    }

  public final void set(byte arg0) {
        getMemory().putByte(offset(), arg0);
    }

  public void set(Number arg0) {
        getMemory().putByte(offset(), arg0.byteValue());
    }

  public final byte byteValue() {
        return get();
    }

  public final short shortValue() {
        return ((short) get());
    }

  public final int intValue() {
        return get();
    }

}