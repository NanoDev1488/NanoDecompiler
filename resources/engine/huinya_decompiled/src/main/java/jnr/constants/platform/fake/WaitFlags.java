// исходный (обфусцированный) внутренний класс: jnr.constants.platform.fake.WaitFlags
package jnr.constants.platform.fake;

import jnr.constants.Constant;

public enum WaitFlags implements Constant {

    WNOHANG(1L),
    WUNTRACED(2L),
    WSTOPPED(4L),
    WEXITED(8L),
    WCONTINUED(16L),
    WNOWAIT(32L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 32L;

  private WaitFlags(long arg2) { // было: <init>
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