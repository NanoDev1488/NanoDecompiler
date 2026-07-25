// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout.Unsigned64
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_NumberField;
import jnr.ffi.StructLayout_Offset;

public class StructLayout_Unsigned64 extends StructLayout_NumberField {

    // ---- поля ----
  final StructLayout this$0;

  public StructLayout_Unsigned64(StructLayout arg0) { // было: <init>
        super(arg0, NativeType.ULONGLONG);
        this$0 = arg0;
    }

  public StructLayout_Unsigned64(StructLayout arg0, StructLayout_Offset arg1) { // было: <init>
        super(arg0, NativeType.ULONGLONG, arg1);
        this$0 = arg0;
    }

  public final long get(Pointer arg0) {
        return arg0.getLongLong(offset());
    }

  public final void set(Pointer arg0, long arg1) {
        arg0.putLongLong(offset(), arg1);
    }

  public void set(Pointer arg0, Number arg1) {
        arg0.putLongLong(offset(), arg1.longValue());
    }

  public final int intValue(Pointer arg0) {
        return ((int) get(arg0));
    }

  public final long longValue(Pointer arg0) {
        return get(arg0);
    }

  public final String toString(Pointer arg0) {
        return Long.toString(get(arg0));
    }

  public short shortValue(Pointer arg0) {
        return super.shortValue(arg0);
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