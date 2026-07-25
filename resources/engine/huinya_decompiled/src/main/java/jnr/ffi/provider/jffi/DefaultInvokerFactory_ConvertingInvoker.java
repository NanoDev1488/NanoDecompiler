// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.ConvertingInvoker
package jnr.ffi.provider.jffi;

import com.kenai.jffi.Function;
import com.kenai.jffi.HeapInvocationBuffer;
import jnr.ffi.Runtime;
import jnr.ffi.mapper.FromNativeContext;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_BaseInvoker;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_FunctionInvoker;

class DefaultInvokerFactory_ConvertingInvoker extends DefaultInvokerFactory_BaseInvoker {

    // ---- поля ----
  private final FromNativeConverter fromNativeConverter;
  private final FromNativeContext fromNativeContext;
  private final DefaultInvokerFactory_FunctionInvoker nativeInvoker;

  public DefaultInvokerFactory_ConvertingInvoker(FromNativeConverter arg0, FromNativeContext arg1, DefaultInvokerFactory_FunctionInvoker arg2) { // было: <init>
        super();
        fromNativeConverter = arg0;
        fromNativeContext = arg1;
        nativeInvoker = arg2;
    }

  public final Object invoke(Runtime arg0, Function arg1, HeapInvocationBuffer arg2) {
        return fromNativeConverter.fromNative(nativeInvoker.invoke(arg0, arg1, arg2), fromNativeContext);
    }

}