// исходный (обфусцированный) внутренний класс: jnr.ffi.Struct.Function
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.Struct_AbstractMember;
import jnr.ffi.provider.ClosureManager;

public final class Struct_Function extends Struct_AbstractMember {

    // ---- поля ----
  private final Class closureClass;
  private Object instance;
  final Struct this$0;

  public Struct_Function(Struct arg0, Class arg1) { // было: <init>
        super(arg0, NativeType.ADDRESS);
        this$0 = arg0;
        closureClass = arg1;
    }

  public final void set(Object arg0) {
        instance = arg0;
        getMemory().putPointer(offset(), this$0.getRuntime().getClosureManager().getClosurePointer(closureClass, arg0));
    }

}