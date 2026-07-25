// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.Int8Marshaller
package jnr.ffi.provider.jffi;

import com.kenai.jffi.HeapInvocationBuffer;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.provider.InvocationSession;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_Marshaller;

class DefaultInvokerFactory_Int8Marshaller implements DefaultInvokerFactory_Marshaller {

    // ---- поля ----
  private final ToNativeConverter toNativeConverter;

   DefaultInvokerFactory_Int8Marshaller(ToNativeConverter arg0) { // было: <init>
        super();
        toNativeConverter = arg0;
    }

  public void marshal(InvocationSession arg0, HeapInvocationBuffer arg1, Object arg2) {
        arg1.putByte((((Number) toNativeConverter.toNative(((Number) arg2), null))).intValue());
    }

}