// исходный (обфусцированный) внутренний класс: jnr.constants.platform.fake.RLIM
package jnr.constants.platform.fake;

import jnr.constants.Constant;

public enum RLIM implements Constant {

    RLIM_NLIMITS(1L),
    RLIM_INFINITY(2L),
    RLIM_SAVED_MAX(3L),
    RLIM_SAVED_CUR(4L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 4L;

  private RLIM(long arg2) { // было: <init>
        value = arg2;
    }

  public final int value() {
        return ((int) value);
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