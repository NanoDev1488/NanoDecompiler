// исходный (обфусцированный) внутренний класс: jnr.ffi.StructLayout.Function
package jnr.ffi;

import jnr.ffi.NativeType;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.StructLayout;
import jnr.ffi.StructLayout_AbstractField;
import jnr.ffi.StructLayout_Offset;
import jnr.ffi.provider.ClosureManager;

public final class StructLayout_Function extends StructLayout_AbstractField {

    // ---- поля ----
  private final Class closureClass;
  private Object instance;
  final StructLayout this$0;

  public StructLayout_Function(StructLayout arg0, Class arg1) { // было: <init>
        super(arg0, NativeType.ADDRESS);
        this$0 = arg0;
        closureClass = arg1;
    }

  public StructLayout_Function(StructLayout arg0, Class arg1, StructLayout_Offset arg2) { // было: <init>
        super(arg0, NativeType.ADDRESS, arg2);
        this$0 = arg0;
        closureClass = arg1;
    }

  public final void set(Pointer arg0, Object arg1) {
        instance = arg1;
        arg0.putPointer(offset(), this$0.getRuntime().getClosureManager().getClosurePointer(closureClass, arg1));
    }

}