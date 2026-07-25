// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.BufferMarshaller
package jnr.ffi.provider.jffi;

import com.kenai.jffi.HeapInvocationBuffer;
import com.kenai.jffi.ObjectParameterStrategy;
import com.kenai.jffi.ObjectParameterType_ComponentType;
import java.nio.Buffer;
import java.util.Collection;
import jnr.ffi.provider.InvocationSession;
import jnr.ffi.provider.jffi.AsmRuntime;
import jnr.ffi.provider.jffi.AsmUtil;
import jnr.ffi.provider.jffi.BufferParameterStrategy;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_Marshaller;

class DefaultInvokerFactory_BufferMarshaller implements DefaultInvokerFactory_Marshaller {

    // ---- поля ----
  private final ObjectParameterType_ComponentType componentType;
  private final int flags;

   DefaultInvokerFactory_BufferMarshaller(ObjectParameterType_ComponentType arg0, Collection arg1) { // было: <init>
        super();
        componentType = arg0;
        flags = AsmUtil.getNativeArrayFlags(arg1);
    }

  public final void marshal(InvocationSession arg0, HeapInvocationBuffer arg1, Object arg2) {
        BufferParameterStrategy __stk1;
        __stk1 = componentType == null ? AsmRuntime.pointerParameterStrategy(((Buffer) arg2)) : AsmRuntime.bufferParameterStrategy(((Buffer) arg2), componentType);
        BufferParameterStrategy var4 = __stk1;
        arg1.putObject(arg2, ((ObjectParameterStrategy) var4), flags);
    }

}