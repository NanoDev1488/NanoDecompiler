// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout.AbstractBoolean
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_AbstractField;
import jnr.ffi.StructLayout_Offset;

public abstract class StructLayout_AbstractBoolean extends StructLayout_AbstractField {

    // ---- поля ----
  final StructLayout this$0;

  protected StructLayout_AbstractBoolean(StructLayout arg0, NativeType arg1) { // было: <init>
        super(arg0, arg1);
        this$0 = arg0;
    }

  protected StructLayout_AbstractBoolean(StructLayout arg0, NativeType arg1, StructLayout_Offset arg2) { // было: <init>
        super(arg0, arg1, arg2);
        this$0 = arg0;
    }

  public abstract boolean get(Pointer arg0);

  public abstract void set(Pointer arg0, boolean arg1);

  public String toString(Pointer arg0) {
        return Boolean.toString(get(arg0));
    }

}