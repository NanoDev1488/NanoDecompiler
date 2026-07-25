// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.EnumLong
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import jnr.ffi.Struct_EnumField;
import jnr.ffi.util.EnumMapper;

public class Struct_EnumLong extends Struct_EnumField {

    // ---- поля ----
  final Struct this$0;

  public Struct_EnumLong(Struct arg0, Class arg1) { // было: <init>
        super(arg0, NativeType.SLONG, arg1);
        this$0 = arg0;
    }

  public final Enum get() {
        return ((Enum) enumClass.cast(EnumMapper.getInstance(enumClass).valueOf(intValue())));
    }

  public final void set(Enum arg0) {
        getMemory().putNativeLong(offset(), ((long) EnumMapper.getInstance(enumClass).intValue(arg0)));
    }

  public void set(Number arg0) {
        getMemory().putNativeLong(offset(), arg0.longValue());
    }

  public final int intValue() {
        return ((int) longValue());
    }

  public final long longValue() {
        return getMemory().getNativeLong(offset());
    }

  public Object get() {
        return get();
    }

}