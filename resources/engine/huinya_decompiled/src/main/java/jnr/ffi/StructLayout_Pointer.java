// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout.Pointer
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_NumberField;
import jnr.ffi.StructLayout_Offset;
import jnr.ffi.Type;

public class StructLayout_Pointer extends StructLayout_NumberField {

    // ---- поля ----
  final StructLayout this$0;

  public StructLayout_Pointer(StructLayout arg0) { // было: <init>
        super(arg0, NativeType.ADDRESS);
        this$0 = arg0;
    }

  public StructLayout_Pointer(StructLayout arg0, StructLayout_Offset arg1) { // было: <init>
        super(arg0, NativeType.ADDRESS, arg1);
        this$0 = arg0;
    }

  public final Pointer get(Pointer arg0) {
        return arg0.getPointer(offset());
    }

  public final int size() {
        return this$0.getRuntime().findType(NativeType.ADDRESS).size();
    }

  public final void set(Pointer arg0, Pointer arg1) {
        arg0.putPointer(offset(), arg1);
    }

  public void set(Pointer arg0, Number arg1) {
        arg0.putAddress(offset(), arg1.longValue());
    }

  public final int intValue(Pointer arg0) {
        return ((int) arg0.getAddress(offset()));
    }

  public final long longValue(Pointer arg0) {
        return arg0.getAddress(offset());
    }

  public final String toString(Pointer arg0) {
        return get(arg0).toString();
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