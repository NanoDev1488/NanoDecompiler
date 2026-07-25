// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.AbstractBoolean
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Struct;
import jnr.ffi.Struct_AbstractMember;
import jnr.ffi.Struct_Offset;

public abstract class Struct_AbstractBoolean extends Struct_AbstractMember {

    // ---- поля ----
  final Struct this$0;

  protected Struct_AbstractBoolean(Struct arg0, NativeType arg1) { // было: <init>
        super(arg0, arg1);
        this$0 = arg0;
    }

  protected Struct_AbstractBoolean(Struct arg0, NativeType arg1, Struct_Offset arg2) { // было: <init>
        super(arg0, arg1, arg2);
        this$0 = arg0;
    }

  public abstract boolean get();

  public abstract void set(boolean arg0);

  public String toString() {
        return Boolean.toString(get());
    }

}