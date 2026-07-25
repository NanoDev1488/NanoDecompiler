// исходный (обфусцированный) внутренний класс: jnr.constants.platform.aix.Shutdown
package jnr.constants.platform.aix;

import jnr.constants.Constant;

public enum Shutdown implements Constant {

    SHUT_RD(0L),
    SHUT_WR(1L),
    SHUT_RDWR(2L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 2L;

  private Shutdown(long arg2) { // было: <init>
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