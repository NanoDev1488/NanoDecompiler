// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout.Float
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_NumberField;
import jnr.ffi.StructLayout_Offset;

public class StructLayout_Float extends StructLayout_NumberField {

    // ---- поля ----
  final StructLayout this$0;

  public StructLayout_Float(StructLayout arg0) { // было: <init>
        super(arg0, NativeType.FLOAT);
        this$0 = arg0;
    }

  public StructLayout_Float(StructLayout arg0, StructLayout_Offset arg1) { // было: <init>
        super(arg0, NativeType.FLOAT, arg1);
        this$0 = arg0;
    }

  public final float get(Pointer arg0) {
        return arg0.getFloat(offset());
    }

  public final void set(Pointer arg0, float arg1) {
        arg0.putFloat(offset(), arg1);
    }

  public void set(Pointer arg0, Number arg1) {
        arg0.putFloat(offset(), arg1.floatValue());
    }

  public final int intValue(Pointer arg0) {
        return ((int) get(arg0));
    }

  public final double doubleValue(Pointer arg0) {
        return ((double) get(arg0));
    }

  public final float floatValue(Pointer arg0) {
        return get(arg0);
    }

  public final long longValue(Pointer arg0) {
        return ((long) get(arg0));
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