// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator.Int64PointerOp
package jnr.ffi.provider.jffi;

import jnr.ffi.Pointer;
import jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator_PointerOp;

final class ReflectionVariableAccessorGenerator_Int64PointerOp implements ReflectionVariableAccessorGenerator_PointerOp {

    // ---- поля ----
  static final ReflectionVariableAccessorGenerator_PointerOp INSTANCE;

    static {
        INSTANCE = new ReflectionVariableAccessorGenerator_Int64PointerOp();
    }

  private ReflectionVariableAccessorGenerator_Int64PointerOp() { // было: <init>
        super();
    }

  public Number get(Pointer arg0) {
        return Long.valueOf(arg0.getLongLong(0L));
    }

  public void put(Pointer arg0, Number arg1) {
        arg0.putLongLong(0L, arg1.longValue());
    }

  public void put(Pointer arg0, Object arg1) {
        put(arg0, ((Number) arg1));
    }

  public Object get(Pointer arg0) {
        return get(arg0);
    }

}