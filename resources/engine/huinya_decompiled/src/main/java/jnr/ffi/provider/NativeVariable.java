// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.NativeVariable
package jnr.ffi.provider;

import java.lang.reflect.Method;

public class NativeVariable {

    // ---- поля ----
  private final Method method;

  public NativeVariable(Method arg0) { // было: <init>
        super();
        method = arg0;
    }

  public Method getMethod() {
        return method;
    }

}