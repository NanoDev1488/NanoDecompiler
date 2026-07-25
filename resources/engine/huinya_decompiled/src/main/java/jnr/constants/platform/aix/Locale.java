// исходный (обфусцированный) внутренний класс: jnr.constants.platform.aix.Locale
package jnr.constants.platform.aix;

import jnr.constants.Constant;

public enum Locale implements Constant {

    LC_CTYPE(1L),
    LC_NUMERIC(3L),
    LC_TIME(4L),
    LC_COLLATE(0L),
    LC_MONETARY(2L),
    LC_MESSAGES(5L),
    LC_ALL(-1L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = -1L;
  public static final long MAX_VALUE = 5L;

  private Locale(long arg2) { // было: <init>
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