// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.ToNativeConverterMarshaller
package jnr.ffi.provider.jffi;

import com.kenai.jffi.HeapInvocationBuffer;
import jnr.ffi.mapper.ToNativeContext;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.mapper.ToNativeConverter_PostInvocation;
import jnr.ffi.provider.InvocationSession;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_Marshaller;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_ToNativeConverterMarshaller_Anon1;

class DefaultInvokerFactory_ToNativeConverterMarshaller implements DefaultInvokerFactory_Marshaller {

    // ---- поля ----
  private final ToNativeConverter converter;
  private final ToNativeContext context;
  private final DefaultInvokerFactory_Marshaller marshaller;
  private final boolean isPostInvokeRequired;

  public DefaultInvokerFactory_ToNativeConverterMarshaller(ToNativeConverter arg0, ToNativeContext arg1, DefaultInvokerFactory_Marshaller arg2) { // было: <init>
        super();
        converter = arg0;
        context = arg1;
        marshaller = arg2;
        isPostInvokeRequired = converter instanceof ToNativeConverter_PostInvocation;
    }

  public void marshal(InvocationSession arg0, HeapInvocationBuffer arg1, Object arg2) {
        Object var4 = converter.toNative(arg2, context);
        marshaller.marshal(arg0, arg1, var4);
        if (!isPostInvokeRequired) {
            arg0.keepAlive(var4);
        } else {
            arg0.addPostInvoke(new DefaultInvokerFactory_ToNativeConverterMarshaller_Anon1(this, arg2, var4));
        }
    }

  static ToNativeContext access$100(DefaultInvokerFactory_ToNativeConverterMarshaller arg0) {
        return arg0.context;
    }

  static ToNativeConverter access$200(DefaultInvokerFactory_ToNativeConverterMarshaller arg0) {
        return arg0.converter;
    }

}