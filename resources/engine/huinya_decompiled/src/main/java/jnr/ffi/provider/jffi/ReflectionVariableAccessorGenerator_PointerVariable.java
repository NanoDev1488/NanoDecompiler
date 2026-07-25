// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator.PointerVariable
package jnr.ffi.provider.jffi;

import jnr.ffi.Pointer;
import jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator_AbstractVariable;
import jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator_Anon1;

final class ReflectionVariableAccessorGenerator_PointerVariable extends ReflectionVariableAccessorGenerator_AbstractVariable {

  private ReflectionVariableAccessorGenerator_PointerVariable(Pointer arg0) { // было: <init>
        super(arg0);
    }

  public Pointer get() {
        return memory.getPointer(0L);
    }

  public void set(Pointer arg0) {
        if (arg0 == null) {
            memory.putAddress(0L, 0L);
        } else {
            memory.putPointer(0L, arg0);
        }
    }

  public void set(Object arg0) {
        set(((Pointer) arg0));
    }

  public Object get() {
        return get();
    }

   ReflectionVariableAccessorGenerator_PointerVariable(Pointer arg0, ReflectionVariableAccessorGenerator_Anon1 arg1) { // было: <init>
        this(arg0);
    }

}