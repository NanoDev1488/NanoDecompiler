// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout.Double
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_NumberField;
import jnr.ffi.StructLayout_Offset;

public final class StructLayout_Double extends StructLayout_NumberField {

    // ---- поля ----
  final StructLayout this$0;

  public StructLayout_Double(StructLayout arg0) { // было: <init>
        super(arg0, NativeType.DOUBLE);
        this$0 = arg0;
    }

  public StructLayout_Double(StructLayout arg0, StructLayout_Offset arg1) { // было: <init>
        super(arg0, NativeType.DOUBLE, arg1);
        this$0 = arg0;
    }

  public final double get(Pointer arg0) {
        return arg0.getDouble(offset());
    }

  public final void set(Pointer arg0, double arg1) {
        arg0.putDouble(offset(), arg1);
    }

  public void set(Pointer arg0, Number arg1) {
        arg0.putDouble(offset(), arg1.doubleValue());
    }

  public final int intValue(Pointer arg0) {
        return ((int) get(arg0));
    }

  public final long longValue(Pointer arg0) {
        return ((long) get(arg0));
    }

  public final float floatValue(Pointer arg0) {
        return ((float) get(arg0));
    }

  public final double doubleValue(Pointer arg0) {
        return get(arg0);
    }

  public final String toString(Pointer arg0) {
        return String.valueOf(get(arg0));
    }

  public short shortValue(Pointer arg0) {
        return super.shortValue(arg0);
    }

  public byte byteValue(Pointer arg0) {
        return super.byteValue(arg0);
    }

}