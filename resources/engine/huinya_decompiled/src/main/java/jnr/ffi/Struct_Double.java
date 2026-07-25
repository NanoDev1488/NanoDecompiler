// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.Double
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import jnr.ffi.Struct_NumberField;
import jnr.ffi.Struct_Offset;

public final class Struct_Double extends Struct_NumberField {

    // ---- поля ----
  final Struct this$0;

  public Struct_Double(Struct arg0) { // было: <init>
        super(arg0, NativeType.DOUBLE);
        this$0 = arg0;
    }

  public Struct_Double(Struct arg0, Struct_Offset arg1) { // было: <init>
        super(arg0, NativeType.DOUBLE, arg1);
        this$0 = arg0;
    }

  public final double get() {
        return getMemory().getDouble(offset());
    }

  public final void set(double arg0) {
        getMemory().putDouble(offset(), arg0);
    }

  public void set(Number arg0) {
        getMemory().putDouble(offset(), arg0.doubleValue());
    }

  public final int intValue() {
        return ((int) get());
    }

  public final long longValue() {
        return ((long) get());
    }

  public final float floatValue() {
        return ((float) get());
    }

  public final double doubleValue() {
        return get();
    }

  public final String toString() {
        return String.valueOf(get());
    }

}