// исходный (обфусцированный) внутренний класс: jnr.constants.platform.aix.Access
package jnr.constants.platform.aix;

import jnr.constants.Constant;

public enum Access implements Constant {

    F_OK(0L),
    X_OK(1L),
    W_OK(2L),
    R_OK(4L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 4L;

  private Access(long arg2) { // было: <init>
        value = arg2;
    }

  public final int intValue() {
        return ((int) value);
    }

  public final long longValue() {
        return value;
    }

  public final boolean defined() {
        return true;
    }

}