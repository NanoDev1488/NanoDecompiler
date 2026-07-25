// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.LongInvoker
package jnr.ffi.provider.jffi;

import com.kenai.jffi.Function;
import com.kenai.jffi.HeapInvocationBuffer;
import com.kenai.jffi.Invoker;
import jnr.ffi.Runtime;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_BaseInvoker;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_FunctionInvoker;

class DefaultInvokerFactory_LongInvoker extends DefaultInvokerFactory_BaseInvoker {

    // ---- поля ----
  static final DefaultInvokerFactory_FunctionInvoker INSTANCE;

    static {
        INSTANCE = new DefaultInvokerFactory_LongInvoker();
    }

   DefaultInvokerFactory_LongInvoker() { // было: <init>
        super();
    }

  public final Object invoke(Runtime arg0, Function arg1, HeapInvocationBuffer arg2) {
        return Long.valueOf(invoker.invokeLong(arg1, arg2));
    }

}