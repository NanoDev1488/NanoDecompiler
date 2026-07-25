// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.BooleanMarshaller
package jnr.ffi.provider.jffi;

import com.kenai.jffi.HeapInvocationBuffer;
import jnr.ffi.provider.InvocationSession;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_Marshaller;

class DefaultInvokerFactory_BooleanMarshaller implements DefaultInvokerFactory_Marshaller {

    // ---- поля ----
  static final DefaultInvokerFactory_Marshaller INSTANCE;

    static {
        INSTANCE = new DefaultInvokerFactory_BooleanMarshaller();
    }

   DefaultInvokerFactory_BooleanMarshaller() { // было: <init>
        super();
    }

  public void marshal(InvocationSession arg0, HeapInvocationBuffer arg1, Object arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_2
        //      1: aload_3
        //      2: checkcast  #2 // java.lang.Boolean
        //      5: invokevirtual  #9 // java.lang.Boolean.booleanValue:()Z
        //      8: ifeq  15 (offset +7)
        //     11: iconst_1
        //     12: goto  16 (offset +4)
        //     15: iconst_0
        //     16: invokevirtual  #8 // com.kenai.jffi.HeapInvocationBuffer.putInt:(I)V
        //     19: return
    }

}