// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout.UnsignedLong
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_NumberField;
import jnr.ffi.StructLayout_Offset;
import jnr.ffi.Type;

public class StructLayout_UnsignedLong extends StructLayout_NumberField {

    // ---- поля ----
  final StructLayout this$0;

  public StructLayout_UnsignedLong(StructLayout arg0) { // было: <init>
        super(arg0, NativeType.ULONG);
        this$0 = arg0;
    }

  public StructLayout_UnsignedLong(StructLayout arg0, StructLayout_Offset arg1) { // было: <init>
        super(arg0, NativeType.ULONG, arg1);
        this$0 = arg0;
    }

  public final long get(Pointer arg0) {
        long __stk1;
        long var2 = arg0.getNativeLong(offset());
        __stk1 = this$0.getRuntime().findType(NativeType.SLONG).size() != 4 ? -1L : 4294967295L;
        long var4 = __stk1;
        return var2 >= 0L ? var2 : (var2 & var4) + var4 + 1L;
    }

  public final void set(Pointer arg0, long arg1) {
        arg0.putNativeLong(offset(), arg1);
    }

  public void set(Pointer arg0, Number arg1) {
        arg0.putNativeLong(offset(), arg1.longValue());
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