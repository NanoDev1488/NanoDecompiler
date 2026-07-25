// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.Enum32
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import jnr.ffi.Struct_EnumField;
import jnr.ffi.util.EnumMapper;

public class Struct_Enum32 extends Struct_EnumField {

    // ---- поля ----
  final Struct this$0;

  public Struct_Enum32(Struct arg0, Class arg1) { // было: <init>
        super(arg0, NativeType.SINT, arg1);
        this$0 = arg0;
    }

  public final Enum get() {
        return ((Enum) enumClass.cast(EnumMapper.getInstance(enumClass).valueOf(intValue())));
    }

  public final void set(Enum arg0) {
        getMemory().putInt(offset(), EnumMapper.getInstance(enumClass).intValue(arg0));
    }

  public void set(Number arg0) {
        getMemory().putInt(offset(), arg0.intValue());
    }

  public final int intValue() {
        return getMemory().getInt(offset());
    }

  public Object get() {
        return get();
    }

}