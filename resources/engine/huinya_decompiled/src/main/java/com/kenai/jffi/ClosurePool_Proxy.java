// исходный (обфусцированный) внутренний класс: com.kenai.jffi.ClosurePool.Proxy
package com.kenai.jffi;

import com.kenai.jffi.CallContext;
import com.kenai.jffi.Closure;
import com.kenai.jffi.ClosurePool;
import com.kenai.jffi.DirectClosureBuffer;
import java.lang.reflect.Method;

final class ClosurePool_Proxy {

    // ---- поля ----
  static final Method METHOD;
  final CallContext callContext;
  volatile Closure closure;

    static {
        METHOD = getMethod();
    }

  private static Method getMethod() {
        Method __stk2;
        try {
            __stk2 = ClosurePool_Proxy.class.getDeclaredMethod("invoke", new Class[]{Long.TYPE, Long.TYPE});
        } catch (Throwable var0) {
            throw new RuntimeException(var0);
        }
    }

   ClosurePool_Proxy(CallContext arg0) { // было: <init>
        super();
        closure = ClosurePool.access$000();
        callContext = arg0;
    }

  public void invoke(long arg0, long arg1) {
        closure.invoke(new DirectClosureBuffer(callContext, arg0, arg1));
    }

}