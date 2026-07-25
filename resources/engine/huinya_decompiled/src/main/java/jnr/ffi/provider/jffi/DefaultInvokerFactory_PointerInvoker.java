// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.PointerInvoker
package jnr.ffi.provider.jffi;

import com.kenai.jffi.Function;
import com.kenai.jffi.HeapInvocationBuffer;
import com.kenai.jffi.Invoker;
import jnr.ffi.Runtime;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_BaseInvoker;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_FunctionInvoker;
import jnr.ffi.provider.jffi.MemoryUtil;

class DefaultInvokerFactory_PointerInvoker extends DefaultInvokerFactory_BaseInvoker {

    // ---- поля ----
  static final DefaultInvokerFactory_FunctionInvoker INSTANCE;

    static {
        INSTANCE = new DefaultInvokerFactory_PointerInvoker();
    }

   DefaultInvokerFactory_PointerInvoker() { // было: <init>
        super();
    }

  public final Object invoke(Runtime arg0, Function arg1, HeapInvocationBuffer arg2) {
        return MemoryUtil.newPointer(arg0, invoker.invokeAddress(arg1, arg2));
    }

}