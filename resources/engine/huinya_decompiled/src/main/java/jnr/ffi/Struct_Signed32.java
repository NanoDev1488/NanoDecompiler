// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.Signed32
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import jnr.ffi.Struct_NumberField;
import jnr.ffi.Struct_Offset;

public class Struct_Signed32 extends Struct_NumberField {

    // ---- поля ----
  final Struct this$0;

  public Struct_Signed32(Struct arg0) { // было: <init>
        super(arg0, NativeType.SINT);
        this$0 = arg0;
    }

  public Struct_Signed32(Struct arg0, Struct_Offset arg1) { // было: <init>
        super(arg0, NativeType.SINT, arg1);
        this$0 = arg0;
    }

  public final int get() {
        return getMemory().getInt(offset());
    }

  public final void set(int arg0) {
        getMemory().putInt(offset(), arg0);
    }

  public void set(Number arg0) {
        getMemory().putInt(offset(), arg0.intValue());
    }

  public final int intValue() {
        return get();
    }

}