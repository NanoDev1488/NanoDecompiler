// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.Int64Marshaller
package jnr.ffi.provider.jffi;

import com.kenai.jffi.HeapInvocationBuffer;
import jnr.ffi.provider.InvocationSession;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_Marshaller;

class DefaultInvokerFactory_Int64Marshaller implements DefaultInvokerFactory_Marshaller {

    // ---- поля ----
  static final DefaultInvokerFactory_Marshaller INSTANCE;

    static {
        INSTANCE = new DefaultInvokerFactory_Int64Marshaller();
    }

   DefaultInvokerFactory_Int64Marshaller() { // было: <init>
        super();
    }

  public void marshal(InvocationSession arg0, HeapInvocationBuffer arg1, Object arg2) {
        arg1.putLong((((Number) arg2)).longValue());
    }

}