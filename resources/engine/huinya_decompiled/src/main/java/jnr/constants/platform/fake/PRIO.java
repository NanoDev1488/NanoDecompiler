// исходный (обфусцированный) внутренний класс: jnr.constants.platform.fake.PRIO
package jnr.constants.platform.fake;

import jnr.constants.Constant;

public enum PRIO implements Constant {

    PRIO_MIN(1L),
    PRIO_PROCESS(2L),
    PRIO_PGRP(3L),
    PRIO_USER(4L),
    PRIO_MAX(5L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 5L;

  private PRIO(long arg2) { // было: <init>
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