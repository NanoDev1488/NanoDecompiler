// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ReflectionLibraryLoader.FunctionNotFoundInvoker
package jnr.ffi.provider.jffi;

import java.lang.reflect.Method;
import jnr.ffi.provider.Invoker;
import jnr.ffi.provider.jffi.ReflectionLibraryLoader_Anon1;

final class ReflectionLibraryLoader_FunctionNotFoundInvoker implements Invoker {

    // ---- поля ----
  private final Method method;
  private final String functionName;

  private ReflectionLibraryLoader_FunctionNotFoundInvoker(Method arg0, String arg1) { // было: <init>
        super();
        method = arg0;
        functionName = arg1;
    }

  public Object invoke(Object arg0, Object[] arg1) {
        throw new UnsatisfiedLinkError(String.format("native method '%s' not found for method %s", new Object[]{functionName, method}));
    }

   ReflectionLibraryLoader_FunctionNotFoundInvoker(Method arg0, String arg1, ReflectionLibraryLoader_Anon1 arg2) { // было: <init>
        this(arg0, arg1);
    }

}