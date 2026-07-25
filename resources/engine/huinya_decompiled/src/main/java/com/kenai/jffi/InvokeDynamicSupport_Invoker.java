// исходный (обфусцированный) внутренний класс: com.kenai.jffi.InvokeDynamicSupport.Invoker
package com.kenai.jffi;

import java.lang.reflect.Method;

public final class InvokeDynamicSupport_Invoker {

    // ---- поля ----
  private final Method method;
  private final Object methodHandle;

   InvokeDynamicSupport_Invoker(Method arg0, Object arg1) { // было: <init>
        super();
        method = arg0;
        methodHandle = arg1;
    }

  public Object getMethodHandle() {
        return methodHandle;
    }

  public Method getMethod() {
        return method;
    }

}