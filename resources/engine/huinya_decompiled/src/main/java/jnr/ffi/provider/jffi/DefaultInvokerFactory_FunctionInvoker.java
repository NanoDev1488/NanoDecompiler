// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.FunctionInvoker
package jnr.ffi.provider.jffi;

import com.kenai.jffi.Function;
import com.kenai.jffi.HeapInvocationBuffer;
import jnr.ffi.Runtime;

interface DefaultInvokerFactory_FunctionInvoker {

  public abstract Object invoke(Runtime arg0, Function arg1, HeapInvocationBuffer arg2);

}