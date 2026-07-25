// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout.IntegerAlias
package jnr.ffi;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_NumberField;
import jnr.ffi.StructLayout_Offset;
import jnr.ffi.TypeAlias;

public abstract class StructLayout_IntegerAlias extends StructLayout_NumberField {

    // ---- поля ----
  final StructLayout this$0;

  protected StructLayout_IntegerAlias(StructLayout arg0, TypeAlias arg1) { // было: <init>
        super(arg0, arg0.getRuntime().findType(arg1));
        this$0 = arg0;
    }

  protected StructLayout_IntegerAlias(StructLayout arg0, TypeAlias arg1, StructLayout_Offset arg2) { // было: <init>
        super(arg0, arg0.getRuntime().findType(arg1), arg2);
        this$0 = arg0;
    }

  public void set(Pointer arg0, Number arg1) {
        arg0.putInt(type, offset(), arg1.longValue());
    }

  public void set(Pointer arg0, long arg1) {
        arg0.putInt(type, offset(), arg1);
    }

  public final long get(Pointer arg0) {
        return arg0.getInt(type, offset());
    }

  public int intValue(Pointer arg0) {
        return ((int) get(arg0));
    }

  public long longValue(Pointer arg0) {
        return get(arg0);
    }

  public String toString(Pointer arg0) {
        return super.toString(arg0);
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