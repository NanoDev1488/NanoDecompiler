// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.NativeClosureProxy.Factory
package jnr.ffi.provider.jffi;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import jnr.ffi.Runtime;
import jnr.ffi.provider.jffi.NativeClosureProxy;

class NativeClosureProxy_Factory {

    // ---- поля ----
  private final Runtime runtime;
  private final Constructor constructor;
  private final Object[] objectFields;
  private final Method invokeMethod;

   NativeClosureProxy_Factory(Runtime arg0, Constructor arg1, Method arg2, Object[] arg3) { // было: <init>
        super();
        runtime = arg0;
        constructor = arg1;
        invokeMethod = arg2;
        objectFields = arg3;
    }

   NativeClosureProxy newClosureProxy() {
        NativeClosureProxy __stk2;
        try {
            __stk2 = ((NativeClosureProxy) constructor.newInstance(new Object[]{runtime, objectFields}));
        } catch (Throwable var1) {
            throw new RuntimeException(var1);
        }
    }

   Method getInvokeMethod() {
        return invokeMethod;
    }

}