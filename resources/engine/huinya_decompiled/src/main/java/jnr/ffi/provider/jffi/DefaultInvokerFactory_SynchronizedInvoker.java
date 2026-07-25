// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.SynchronizedInvoker
package jnr.ffi.provider.jffi;

import jnr.ffi.provider.Invoker;

final class DefaultInvokerFactory_SynchronizedInvoker implements Invoker {

    // ---- поля ----
  private final Invoker invoker;

  public DefaultInvokerFactory_SynchronizedInvoker(Invoker arg0) { // было: <init>
        super();
        invoker = arg0;
    }

  public Object invoke(Object arg0, Object[] arg1) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_1
        //      1: dup
        //      2: astore_3
        //      3: monitorenter
        //      4: aload_0
        //      5: getfield  #5 // jnr.ffi.provider.jffi.DefaultInvokerFactory$SynchronizedInvoker.invoker:Ljnr/ffi/provider/Invoker;
        //      8: aload_1
        //      9: aload_2
        //     10: invokeinterface  #7 // jnr.ffi.provider.Invoker.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;, count 3
        //     15: aload_3
        //     16: monitorexit
        //     17: areturn
        //     18: astore  4
        //     20: aload_3
        //     21: monitorexit
        //     22: aload  4
        //     24: athrow
        //       Exception table:
        //         from 4 to 17 target 18 type any
        //         from 18 to 22 target 18 type any
    }

}