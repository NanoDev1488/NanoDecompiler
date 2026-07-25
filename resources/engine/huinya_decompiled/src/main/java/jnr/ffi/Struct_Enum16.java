// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.Enum16
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Struct;
import jnr.ffi.Struct_EnumField;
import jnr.ffi.util.EnumMapper;

public class Struct_Enum16 extends Struct_EnumField {

    // ---- поля ----
  final Struct this$0;

  public Struct_Enum16(Struct arg0, Class arg1) { // было: <init>
        super(arg0, NativeType.SSHORT, arg1);
        this$0 = arg0;
    }

  public final Enum get() {
        return ((Enum) enumClass.cast(EnumMapper.getInstance(enumClass).valueOf(intValue())));
    }

  public final void set(Enum arg0) {
        getMemory().putShort(offset(), ((short) EnumMapper.getInstance(enumClass).intValue(arg0)));
    }

  public void set(Number arg0) {
        getMemory().putShort(offset(), arg0.shortValue());
    }

  public final int intValue() {
        return getMemory().getShort(offset());
    }

  public Object get() {
        return get();
    }

}