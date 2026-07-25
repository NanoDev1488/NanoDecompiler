// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout.EnumLong
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_EnumField;
import jnr.ffi.StructLayout_Offset;
import jnr.ffi.util.EnumMapper;

public class StructLayout_EnumLong extends StructLayout_EnumField {

    // ---- поля ----
  final StructLayout this$0;

  public StructLayout_EnumLong(StructLayout arg0, Class arg1) { // было: <init>
        super(arg0, NativeType.SLONG, arg1);
        this$0 = arg0;
    }

  public StructLayout_EnumLong(StructLayout arg0, Class arg1, StructLayout_Offset arg2) { // было: <init>
        super(arg0, NativeType.SLONG, arg1, arg2);
        this$0 = arg0;
    }

  public final void set(Pointer arg0, Enum arg1) {
        arg0.putNativeLong(offset(), ((long) enumMapper.intValue(arg1)));
    }

  public void set(Pointer arg0, Number arg1) {
        arg0.putNativeLong(offset(), arg1.longValue());
    }

  public final int intValue(Pointer arg0) {
        return ((int) longValue(arg0));
    }

  public final long longValue(Pointer arg0) {
        return arg0.getNativeLong(offset());
    }

  public Enum get(Pointer arg0) {
        return super.get(arg0);
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