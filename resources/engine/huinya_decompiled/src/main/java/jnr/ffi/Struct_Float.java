// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.Float
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import jnr.ffi.Struct_NumberField;
import jnr.ffi.Struct_Offset;

public class Struct_Float extends Struct_NumberField {

    // ---- поля ----
  final Struct this$0;

  public Struct_Float(Struct arg0) { // было: <init>
        super(arg0, NativeType.FLOAT);
        this$0 = arg0;
    }

  public Struct_Float(Struct arg0, Struct_Offset arg1) { // было: <init>
        super(arg0, NativeType.FLOAT, arg1);
        this$0 = arg0;
    }

  public final float get() {
        return getMemory().getFloat(offset());
    }

  public final void set(float arg0) {
        getMemory().putFloat(offset(), arg0);
    }

  public void set(Number arg0) {
        getMemory().putFloat(offset(), arg0.floatValue());
    }

  public final int intValue() {
        return ((int) get());
    }

  public final double doubleValue() {
        return ((double) get());
    }

  public final float floatValue() {
        return get();
    }

  public final long longValue() {
        return ((long) get());
    }

  public final String toString() {
        return String.valueOf(get());
    }

}