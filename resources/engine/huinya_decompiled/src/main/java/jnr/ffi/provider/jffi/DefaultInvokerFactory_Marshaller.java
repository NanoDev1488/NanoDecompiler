// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.Marshaller
package jnr.ffi.provider.jffi;

import com.kenai.jffi.HeapInvocationBuffer;
import jnr.ffi.provider.InvocationSession;

interface DefaultInvokerFactory_Marshaller {

  public abstract void marshal(InvocationSession arg0, HeapInvocationBuffer arg1, Object arg2);

}