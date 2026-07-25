// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.BaseInvoker
package jnr.ffi.provider.jffi;

import com.kenai.jffi.Invoker;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_FunctionInvoker;

abstract class DefaultInvokerFactory_BaseInvoker implements DefaultInvokerFactory_FunctionInvoker {

    // ---- поля ----
  static Invoker invoker;

    static {
        invoker = Invoker.getInstance();
    }

   DefaultInvokerFactory_BaseInvoker() { // было: <init>
        super();
    }

}