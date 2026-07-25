// исходный (обфусцированный) внутренний класс: jnr.constants.platform.aix.PosixFadvise
package jnr.constants.platform.aix;

import jnr.constants.Constant;

public enum PosixFadvise implements Constant {

    POSIX_FADV_NORMAL(1L),
    POSIX_FADV_SEQUENTIAL(2L),
    POSIX_FADV_RANDOM(3L),
    POSIX_FADV_NOREUSE(6L),
    POSIX_FADV_WILLNEED(4L),
    POSIX_FADV_DONTNEED(5L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 6L;

  private PosixFadvise(long arg2) { // было: <init>
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