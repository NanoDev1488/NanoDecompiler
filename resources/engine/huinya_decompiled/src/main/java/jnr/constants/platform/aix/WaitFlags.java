// исходный (обфусцированный) внутренний класс: jnr.constants.platform.aix.WaitFlags
package jnr.constants.platform.aix;

import jnr.constants.Constant;

public enum WaitFlags implements Constant {

    WNOHANG(1L),
    WUNTRACED(2L),
    WSTOPPED(64L),
    WEXITED(4L),
    WCONTINUED(16777216L),
    WNOWAIT(16L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 16777216L;

  private WaitFlags(long arg2) { // было: <init>
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