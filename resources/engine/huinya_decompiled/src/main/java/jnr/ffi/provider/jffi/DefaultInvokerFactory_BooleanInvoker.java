// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.BooleanInvoker
package jnr.ffi.provider.jffi;

import com.kenai.jffi.Function;
import com.kenai.jffi.HeapInvocationBuffer;
import com.kenai.jffi.Invoker;
import jnr.ffi.Runtime;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_BaseInvoker;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_FunctionInvoker;

class DefaultInvokerFactory_BooleanInvoker extends DefaultInvokerFactory_BaseInvoker {

    // ---- поля ----
  static DefaultInvokerFactory_FunctionInvoker INSTANCE;

    static {
        INSTANCE = new DefaultInvokerFactory_BooleanInvoker();
    }

   DefaultInvokerFactory_BooleanInvoker() { // было: <init>
        super();
    }

  public final Object invoke(Runtime arg0, Function arg1, HeapInvocationBuffer arg2) {
        boolean __stk1;
        __stk1 = invoker.invokeInt(arg1, arg2) != 0;
        return Boolean.valueOf(__stk1);
    }

}