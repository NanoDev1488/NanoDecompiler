// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.ToNativeConverterMarshaller$1
package jnr.ffi.provider.jffi;

import jnr.ffi.mapper.ToNativeConverter_PostInvocation;
import jnr.ffi.provider.InvocationSession_PostInvoke;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_ToNativeConverterMarshaller;

class DefaultInvokerFactory_ToNativeConverterMarshaller_Anon1 implements InvocationSession_PostInvoke {

    // ---- поля ----
  final Object val$parameter;
  final Object val$nativeValue;
  final DefaultInvokerFactory_ToNativeConverterMarshaller this$0;

   DefaultInvokerFactory_ToNativeConverterMarshaller_Anon1(DefaultInvokerFactory_ToNativeConverterMarshaller arg0, Object arg1, Object arg2) { // было: <init>
        super();
        this$0 = arg0;
        val$parameter = arg1;
        val$nativeValue = arg2;
    }

  public void postInvoke() {
        (((ToNativeConverter_PostInvocation) DefaultInvokerFactory_ToNativeConverterMarshaller.access$200(this$0))).postInvoke(val$parameter, val$nativeValue, DefaultInvokerFactory_ToNativeConverterMarshaller.access$100(this$0));
    }

}