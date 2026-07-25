// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout.Unsigned8
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_NumberField;
import jnr.ffi.StructLayout_Offset;

public class StructLayout_Unsigned8 extends StructLayout_NumberField {

    // ---- поля ----
  final StructLayout this$0;

  public StructLayout_Unsigned8(StructLayout arg0) { // было: <init>
        super(arg0, NativeType.UCHAR);
        this$0 = arg0;
    }

  public StructLayout_Unsigned8(StructLayout arg0, StructLayout_Offset arg1) { // было: <init>
        super(arg0, NativeType.UCHAR, arg1);
        this$0 = arg0;
    }

  public final short get(Pointer arg0) {
        short var2 = ((short) arg0.getByte(offset()));
        return var2 >= 0 ? var2 : ((short) ((var2 & 127) + 128));
    }

  public final void set(Pointer arg0, short arg1) {
        arg0.putByte(offset(), ((byte) arg1));
    }

  public void set(Pointer arg0, Number arg1) {
        arg0.putByte(offset(), arg1.byteValue());
    }

  public final short shortValue(Pointer arg0) {
        return get(arg0);
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

  public byte byteValue(Pointer arg0) {
        return super.byteValue(arg0);
    }

  public float floatValue(Pointer arg0) {
        return super.floatValue(arg0);
    }

  public double doubleValue(Pointer arg0) {
        return super.doubleValue(arg0);
    }

}