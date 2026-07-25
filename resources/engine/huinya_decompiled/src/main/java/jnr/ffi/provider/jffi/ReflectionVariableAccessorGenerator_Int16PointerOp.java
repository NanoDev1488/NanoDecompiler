// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator.Int16PointerOp
package jnr.ffi.provider.jffi;

import jnr.ffi.Pointer;
import jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator_PointerOp;

final class ReflectionVariableAccessorGenerator_Int16PointerOp implements ReflectionVariableAccessorGenerator_PointerOp {

    // ---- поля ----
  static final ReflectionVariableAccessorGenerator_PointerOp INSTANCE;

    static {
        INSTANCE = new ReflectionVariableAccessorGenerator_Int16PointerOp();
    }

  private ReflectionVariableAccessorGenerator_Int16PointerOp() { // было: <init>
        super();
    }

  public Number get(Pointer arg0) {
        return Short.valueOf(arg0.getShort(0L));
    }

  public void put(Pointer arg0, Number arg1) {
        arg0.putShort(0L, arg1.shortValue());
    }

  public void put(Pointer arg0, Object arg1) {
        put(arg0, ((Number) arg1));
    }

  public Object get(Pointer arg0) {
        return get(arg0);
    }

}