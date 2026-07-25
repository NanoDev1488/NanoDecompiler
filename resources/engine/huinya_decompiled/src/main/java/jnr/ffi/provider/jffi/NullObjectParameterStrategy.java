// исходный (обфусцированный) внутренний класс: jnr.ffi.provider.jffi.NullObjectParameterStrategy
package jnr.ffi.provider.jffi;

import jnr.ffi.provider.jffi.ParameterStrategy;

public final class NullObjectParameterStrategy extends ParameterStrategy {

    // ---- поля ----
  public static final ParameterStrategy NULL;

    static {
        NULL = new NullObjectParameterStrategy();
    }

  public NullObjectParameterStrategy() { // было: <init>
        super(DIRECT);
    }

  public long address(Object arg0) {
        return 0L;
    }

  public Object object(Object arg0) {
        throw new NullPointerException("null reference");
    }

  public int offset(Object arg0) {
        throw new NullPointerException("null reference");
    }

  public int length(Object arg0) {
        throw new NullPointerException("null reference");
    }

}