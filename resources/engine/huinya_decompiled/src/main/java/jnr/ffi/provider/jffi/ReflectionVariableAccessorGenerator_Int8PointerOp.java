// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator.Int8PointerOp
package jnr.ffi.provider.jffi;

import jnr.ffi.Pointer;
import jnr.ffi.provider.jffi.ReflectionVariableAccessorGenerator_PointerOp;

final class ReflectionVariableAccessorGenerator_Int8PointerOp implements ReflectionVariableAccessorGenerator_PointerOp {

    // ---- поля ----
  static final ReflectionVariableAccessorGenerator_PointerOp INSTANCE;

    static {
        INSTANCE = new ReflectionVariableAccessorGenerator_Int8PointerOp();
    }

  private ReflectionVariableAccessorGenerator_Int8PointerOp() { // было: <init>
        super();
    }

  public Number get(Pointer arg0) {
        return Byte.valueOf(arg0.getByte(0L));
    }

  public void put(Pointer arg0, Number arg1) {
        arg0.putByte(0L, arg1.byteValue());
    }

  public void put(Pointer arg0, Object arg1) {
        put(arg0, ((Number) arg1));
    }

  public Object get(Pointer arg0) {
        return get(arg0);
    }

}