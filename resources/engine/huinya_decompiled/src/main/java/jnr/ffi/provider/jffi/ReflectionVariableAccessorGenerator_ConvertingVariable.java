// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator.ConvertingVariable
package jnr.ffi.provider.jffi;

import jnr.ffi.Variable;
import jnr.ffi.mapper.FromNativeConverter;
import jnr.ffi.mapper.ToNativeConverter;
import jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator_Anon1;

final class ReflectionVariableAccessorGenerator_ConvertingVariable implements Variable {

    // ---- поля ----
  private final Variable variable;
  private final ToNativeConverter toNativeConverter;
  private final FromNativeConverter fromNativeConverter;

  private ReflectionVariableAccessorGenerator_ConvertingVariable(Variable arg0, ToNativeConverter arg1, FromNativeConverter arg2) { // было: <init>
        super();
        variable = arg0;
        toNativeConverter = arg1;
        fromNativeConverter = arg2;
    }

  public Object get() {
        return fromNativeConverter.fromNative(variable.get(), null);
    }

  public void set(Object arg0) {
        variable.set(toNativeConverter.toNative(arg0, null));
    }

   ReflectionVariableAccessorGenerator_ConvertingVariable(Variable arg0, ToNativeConverter arg1, FromNativeConverter arg2, ReflectionVariableAccessorGenerator_Anon1 arg3) { // было: <init>
        this(arg0, arg1, arg2);
    }

}