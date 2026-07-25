// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.DefaultInvokerFactory.PrimitiveArrayMarshaller
package jnr.ffi.provider.jffi;

import com.kenai.jffi.HeapInvocationBuffer;
import java.util.Collection;
import jnr.ffi.provider.InvocationSession;
import jnr.ffi.provider.jffi.AsmUtil;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_Marshaller;
import jnr.ffi.provider.jffi.PrimitiveArrayParameterStrategy;

class DefaultInvokerFactory_PrimitiveArrayMarshaller implements DefaultInvokerFactory_Marshaller {

    // ---- поля ----
  private final PrimitiveArrayParameterStrategy strategy;
  private final int flags;

  protected DefaultInvokerFactory_PrimitiveArrayMarshaller(PrimitiveArrayParameterStrategy arg0, Collection arg1) { // было: <init>
        super();
        strategy = arg0;
        flags = AsmUtil.getNativeArrayFlags(arg1);
    }

  public final void marshal(InvocationSession arg0, HeapInvocationBuffer arg1, Object arg2) {
        // -- не удалось безопасно декомпилировать тело метода, показан байткод --
        //      0: aload_2
        //      1: aload_3
        //      2: aload_3
        //      3: ifnull  13 (offset +10)
        //      6: aload_0
        //      7: getfield  #9 // jnr.ffi.provider.jffi.DefaultInvokerFactory$PrimitiveArrayMarshaller.strategy:Ljnr/ffi/provider/jffi/PrimitiveArrayParameterStrategy;
        //     10: goto  16 (offset +6)
        //     13: getstatic  #10 // jnr.ffi.provider.jffi.NullObjectParameterStrategy.NULL:Ljnr/ffi/provider/jffi/ParameterStrategy;
        //     16: aload_0
        //     17: getfield  #8 // jnr.ffi.provider.jffi.DefaultInvokerFactory$PrimitiveArrayMarshaller.flags:I
        //     20: invokevirtual  #11 // com.kenai.jffi.HeapInvocationBuffer.putObject:(Ljava/lang/Object;Lcom/kenai/jffi/ObjectParameterStrategy;I)V
        //     23: return
    }

}