// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.Float32Marshaller
package jnr.ffi.provider.jffi;

import com.kenai.jffi.HeapInvocationBuffer;
import jnr.ffi.provider.InvocationSession;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_Marshaller;

class DefaultInvokerFactory_Float32Marshaller implements DefaultInvokerFactory_Marshaller {

    // ---- поля ----
  static final DefaultInvokerFactory_Marshaller INSTANCE;

    static {
        INSTANCE = new DefaultInvokerFactory_Float32Marshaller();
    }

   DefaultInvokerFactory_Float32Marshaller() { // было: <init>
        super();
    }

  public void marshal(InvocationSession arg0, HeapInvocationBuffer arg1, Object arg2) {
        arg1.putFloat((((Number) arg2)).floatValue());
    }

}