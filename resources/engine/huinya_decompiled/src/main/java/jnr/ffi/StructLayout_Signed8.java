// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout.Signed8
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_NumberField;
import jnr.ffi.StructLayout_Offset;

public class StructLayout_Signed8 extends StructLayout_NumberField {

    // ---- поля ----
  final StructLayout this$0;

  public StructLayout_Signed8(StructLayout arg0) { // было: <init>
        super(arg0, NativeType.SCHAR);
        this$0 = arg0;
    }

  public StructLayout_Signed8(StructLayout arg0, StructLayout_Offset arg1) { // было: <init>
        super(arg0, NativeType.SCHAR, arg1);
        this$0 = arg0;
    }

  public final byte get(Pointer arg0) {
        return arg0.getByte(offset());
    }

  public final void set(Pointer arg0, byte arg1) {
        arg0.putByte(offset(), arg1);
    }

  public void set(Pointer arg0, Number arg1) {
        arg0.putByte(offset(), arg1.byteValue());
    }

  public final byte byteValue(Pointer arg0) {
        return get(arg0);
    }

  public final short shortValue(Pointer arg0) {
        return ((short) get(arg0));
    }

  public final int intValue(Pointer arg0) {
        return get(arg0);
    }

  public String toString(Pointer arg0) {
        return super.toString(arg0);
    }

  public long longValue(Pointer arg0) {
        return super.longValue(arg0);
    }

  public float floatValue(Pointer arg0) {
        return super.floatValue(arg0);
    }

  public double doubleValue(Pointer arg0) {
        return super.doubleValue(arg0);
    }

}