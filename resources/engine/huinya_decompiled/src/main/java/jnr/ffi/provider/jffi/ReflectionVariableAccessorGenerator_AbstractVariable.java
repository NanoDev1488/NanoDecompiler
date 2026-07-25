// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator.AbstractVariable
package jnr.ffi.provider.jffi;

import jnr.ffi.Pointer;
import jnr.ffi.Variable;

abstract class ReflectionVariableAccessorGenerator_AbstractVariable implements Variable {

    // ---- поля ----
  protected final Pointer memory;

  protected ReflectionVariableAccessorGenerator_AbstractVariable(Pointer arg0) { // было: <init>
        super();
        memory = arg0;
    }

}