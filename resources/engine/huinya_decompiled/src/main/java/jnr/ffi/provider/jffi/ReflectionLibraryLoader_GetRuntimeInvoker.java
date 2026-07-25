// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ReflectionLibraryLoader.GetRuntimeInvoker
package jnr.ffi.provider.jffi;

import jnr.ffi.Runtime;
import jnr.ffi.provider.Invoker;
import jnr.ffi.provider.jffi.ReflectionLibraryLoader_Anon1;

final class ReflectionLibraryLoader_GetRuntimeInvoker implements Invoker {

    // ---- поля ----
  private final Runtime runtime;

  private ReflectionLibraryLoader_GetRuntimeInvoker(Runtime arg0) { // было: <init>
        super();
        runtime = arg0;
    }

  public Object invoke(Object arg0, Object[] arg1) {
        return runtime;
    }

   ReflectionLibraryLoader_GetRuntimeInvoker(Runtime arg0, ReflectionLibraryLoader_Anon1 arg1) { // было: <init>
        this(arg0);
    }

}