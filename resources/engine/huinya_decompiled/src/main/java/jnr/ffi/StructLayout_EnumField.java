// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout.EnumField
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_NumberField;
import jnr.ffi.StructLayout_Offset;
import jnr.ffi.util.EnumMapper;

public abstract class StructLayout_EnumField extends StructLayout_NumberField {

    // ---- поля ----
  protected final Class enumClass;
  protected final EnumMapper enumMapper;
  final StructLayout this$0;

  public StructLayout_EnumField(StructLayout arg0, NativeType arg1, Class arg2) { // было: <init>
        super(arg0, arg1);
        this$0 = arg0;
        enumClass = arg2;
        enumMapper = EnumMapper.getInstance(arg2);
    }

  public StructLayout_EnumField(StructLayout arg0, NativeType arg1, Class arg2, StructLayout_Offset arg3) { // было: <init>
        super(arg0, arg1, arg3);
        this$0 = arg0;
        enumClass = arg2;
        enumMapper = EnumMapper.getInstance(arg2);
    }

  public Enum get(Pointer arg0) {
        return ((Enum) enumClass.cast(enumMapper.valueOf(intValue(arg0))));
    }

  public final String toString(Pointer arg0) {
        return get(arg0).toString();
    }

}