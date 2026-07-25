// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.EnumField
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Struct;
import jnr.ffi.Struct_NumberField;

public abstract class Struct_EnumField extends Struct_NumberField {

    // ---- поля ----
  protected final Class enumClass;
  final Struct this$0;

  public Struct_EnumField(Struct arg0, NativeType arg1, Class arg2) { // было: <init>
        super(arg0, arg1);
        this$0 = arg0;
        enumClass = arg2;
    }

  public abstract Object get();

  public final String toString() {
        return get().toString();
    }

}