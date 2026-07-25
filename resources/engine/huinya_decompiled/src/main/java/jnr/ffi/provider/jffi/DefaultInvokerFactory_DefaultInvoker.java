// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.DefaultInvoker
package jnr.ffi.provider.jffi;

import com.kenai.jffi.Function;
import com.kenai.jffi.HeapInvocationBuffer;
import jnr.ffi.Runtime;
import jnr.ffi.provider.InvocationSession;
import jnr.ffi.provider.Invoker;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_FunctionInvoker;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_Marshaller;
import jnr.ffi.provider.jffi.NativeLibrary;

class DefaultInvokerFactory_DefaultInvoker implements Invoker {

    // ---- поля ----
  protected final Runtime runtime;
  final Function function;
  final DefaultInvokerFactory_FunctionInvoker functionInvoker;
  final DefaultInvokerFactory_Marshaller[] marshallers;
  final NativeLibrary nativeLibrary;

   DefaultInvokerFactory_DefaultInvoker(Runtime arg0, NativeLibrary arg1, Function arg2, DefaultInvokerFactory_FunctionInvoker arg3, DefaultInvokerFactory_Marshaller[] arg4) { // было: <init>
        super();
        runtime = arg0;
        nativeLibrary = arg1;
        function = arg2;
        functionInvoker = arg3;
        marshallers = arg4;
    }

  public final Object invoke(Object arg0, Object[] arg1) {
        InvocationSession var3 = new InvocationSession();
        HeapInvocationBuffer var4 = new HeapInvocationBuffer(function.getCallContext());
        try {
            Object var5;
            if (arg1 == null) {
                var5 = functionInvoker.invoke(runtime, function, var4);
            }
            int var5 = 0;
            while (var5 < arg1.length) {
                marshallers[var5].marshal(var3, var4, arg1[var5]);
                ++var5;
                continue;
            }
            var5 = functionInvoker.invoke(runtime, function, var4);
        } catch (Throwable e2) {
            try {
                while (true) {
                    Throwable var6 = e2;
                }
            } catch (Throwable var6) {
            }
        }
    }

}