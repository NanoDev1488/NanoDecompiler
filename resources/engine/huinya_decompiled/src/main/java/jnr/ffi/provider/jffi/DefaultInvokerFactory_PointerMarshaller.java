// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.PointerMarshaller
package jnr.ffi.provider.jffi;

import com.kenai.jffi.HeapInvocationBuffer;
import java.util.Collection;
import jnr.ffi.Pointer;
import jnr.ffi.provider.InvocationSession;
import jnr.ffi.provider.jffi.AsmRuntime;
import jnr.ffi.provider.jffi.AsmUtil;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_Marshaller;

class DefaultInvokerFactory_PointerMarshaller implements DefaultInvokerFactory_Marshaller {

    // ---- поля ----
  private final int flags;

   DefaultInvokerFactory_PointerMarshaller(Collection arg0) { // было: <init>
        super();
        flags = AsmUtil.getNativeArrayFlags(arg0);
    }

  public void marshal(InvocationSession arg0, HeapInvocationBuffer arg1, Object arg2) {
        arg1.putObject(arg2, AsmRuntime.pointerParameterStrategy(((Pointer) arg2)), flags);
    }

}