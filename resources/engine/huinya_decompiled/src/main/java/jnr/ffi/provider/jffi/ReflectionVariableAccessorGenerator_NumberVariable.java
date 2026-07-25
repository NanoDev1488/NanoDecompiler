// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator.NumberVariable
package jnr.ffi.provider.jffi;

import jnr.ffi.Pointer;
import jnr.ffi.mapper.DataConverter;
import jnr.ffi.provider.jffi.DefaultInvokerFactory_ResultConverter;
import jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator_AbstractVariable;
import jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator_Anon1;
import jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator_PointerOp;

final class ReflectionVariableAccessorGenerator_NumberVariable extends ReflectionVariableAccessorGenerator_AbstractVariable {

    // ---- поля ----
  private final DataConverter dataConverter;
  private final DefaultInvokerFactory_ResultConverter resultConverter;
  private final ReflectionVariableAccessorGenerator_PointerOp pointerOp;

  private ReflectionVariableAccessorGenerator_NumberVariable(Pointer arg0, ReflectionVariableAccessorGenerator_PointerOp arg1, DataConverter arg2, DefaultInvokerFactory_ResultConverter arg3) { // было: <init>
        super(arg0);
        pointerOp = arg1;
        dataConverter = arg2;
        resultConverter = arg3;
    }

  public Number get() {
        return ((Number) resultConverter.fromNative(((Number) dataConverter.fromNative(((Number) pointerOp.get(memory)), null)), null));
    }

  public void set(Number arg0) {
        pointerOp.put(memory, ((Number) dataConverter.toNative(arg0, null)));
    }

  public void set(Object arg0) {
        set(((Number) arg0));
    }

  public Object get() {
        return get();
    }

   ReflectionVariableAccessorGenerator_NumberVariable(Pointer arg0, ReflectionVariableAccessorGenerator_PointerOp arg1, DataConverter arg2, DefaultInvokerFactory_ResultConverter arg3, ReflectionVariableAccessorGenerator_Anon1 arg4) { // было: <init>
        this(arg0, arg1, arg2, arg3);
    }

}