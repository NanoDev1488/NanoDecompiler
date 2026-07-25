// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.VariableAccessorGenerator.PointerOp
package jnr.ffi.provider.jffi;

import jnr.ffi.Pointer;
import jnr.ffi.provider.jffi.SkinnyMethodAdapter;
import jnr.ffi.provider.jffi.VariableAccessorGenerator_Anon1;

final class VariableAccessorGenerator_PointerOp {

    // ---- поля ----
  private final String getMethodName;
  private final String putMethodName;
  final Class nativeIntClass;

  private VariableAccessorGenerator_PointerOp(String arg0, Class arg1) { // было: <init>
        super();
        getMethodName = new StringBuilder().append("get").append(arg0).toString();
        putMethodName = new StringBuilder().append("put").append(arg0).toString();
        nativeIntClass = arg1;
    }

   void put(SkinnyMethodAdapter arg0) {
        arg0.invokevirtual(Pointer.class, putMethodName, Void.TYPE, new Class[]{Long.TYPE, nativeIntClass});
    }

   void get(SkinnyMethodAdapter arg0) {
        arg0.invokevirtual(Pointer.class, getMethodName, nativeIntClass, new Class[]{Long.TYPE});
    }

   VariableAccessorGenerator_PointerOp(String arg0, Class arg1, VariableAccessorGenerator_Anon1 arg2) { // было: <init>
        this(arg0, arg1);
    }

}