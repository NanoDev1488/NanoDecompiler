// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.PointerParameterStrategy
package jnr.ffi.provider.jffi;

import com.kenai.jffi.ObjectParameterStrategy_StrategyType;
import com.kenai.jffi.ObjectParameterType;
import jnr.ffi.Pointer;
import jnr.ffi.provider.jffi.ParameterStrategy;

public final class PointerParameterStrategy extends ParameterStrategy {

    // ---- поля ----
  public static final PointerParameterStrategy DIRECT;
  public static final PointerParameterStrategy HEAP;

    static {
        DIRECT = new PointerParameterStrategy(ObjectParameterStrategy_StrategyType.DIRECT);
        HEAP = new PointerParameterStrategy(ObjectParameterStrategy_StrategyType.HEAP);
    }

   PointerParameterStrategy(ObjectParameterStrategy_StrategyType arg0) { // было: <init>
        super(arg0, ObjectParameterType.create(ObjectParameterType.ARRAY, ObjectParameterType.BYTE));
    }

  public long address(Object arg0) {
        return address(((Pointer) arg0));
    }

  public long address(Pointer arg0) {
        return arg0 == null ? 0L : arg0.address();
    }

  public Object object(Object arg0) {
        return (((Pointer) arg0)).array();
    }

  public int offset(Object arg0) {
        return (((Pointer) arg0)).arrayOffset();
    }

  public int length(Object arg0) {
        return (((Pointer) arg0)).arrayLength();
    }

}