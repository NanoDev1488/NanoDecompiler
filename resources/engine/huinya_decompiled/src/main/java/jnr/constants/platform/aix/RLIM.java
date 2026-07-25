// исходный (обфусцированный) внутренний класс: jnr.constants.platform.aix.RLIM
package jnr.constants.platform.aix;

import jnr.constants.Constant;

public enum RLIM implements Constant {

    RLIM_NLIMITS(10L),
    RLIM_INFINITY(9223372036854775807L),
    RLIM_SAVED_MAX(9223372036854775806L),
    RLIM_SAVED_CUR(9223372036854775805L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 10L;
  public static final long MAX_VALUE = 9223372036854775807L;

  private RLIM(long arg2) { // было: <init>
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