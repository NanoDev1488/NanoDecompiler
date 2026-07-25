// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ReflectionLibraryLoader.LazyLoader.VariableAcccessorInvoker
package jnr.ffi.provider.jffi;

import jnr.ffi.Variable;
import jnr.ffi.provider.Invoker;
import jnr.ffi.provider.jffi.ReflectionLibraryLoader_Anon1;

final class ReflectionLibraryLoader_LazyLoader_VariableAcccessorInvoker implements Invoker {

    // ---- поля ----
  private final Variable variable;

  private ReflectionLibraryLoader_LazyLoader_VariableAcccessorInvoker(Variable arg0) { // было: <init>
        super();
        variable = arg0;
    }

  public Object invoke(Object arg0, Object[] arg1) {
        return variable;
    }

   ReflectionLibraryLoader_LazyLoader_VariableAcccessorInvoker(Variable arg0, ReflectionLibraryLoader_Anon1 arg1) { // было: <init>
        this(arg0);
    }

}