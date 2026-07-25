// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator.DoublePointerOp
package jnr.ffi.provider.jffi;

import jnr.ffi.Pointer;
import jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator_PointerOp;

final class ReflectionVariableAccessorGenerator_DoublePointerOp implements ReflectionVariableAccessorGenerator_PointerOp {

    // ---- поля ----
  static final ReflectionVariableAccessorGenerator_PointerOp INSTANCE;

    static {
        INSTANCE = new ReflectionVariableAccessorGenerator_DoublePointerOp();
    }

  private ReflectionVariableAccessorGenerator_DoublePointerOp() { // было: <init>
        super();
    }

  public Number get(Pointer arg0) {
        return Float.valueOf(arg0.getFloat(0L));
    }

  public void put(Pointer arg0, Number arg1) {
        arg0.putFloat(0L, arg1.floatValue());
    }

  public void put(Pointer arg0, Object arg1) {
        put(arg0, ((Number) arg1));
    }

  public Object get(Pointer arg0) {
        return get(arg0);
    }

}