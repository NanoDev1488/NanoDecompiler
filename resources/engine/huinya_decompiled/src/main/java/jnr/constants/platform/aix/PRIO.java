// исходный (обфусцированный) внутренний класс: jnr.constants.platform.aix.PRIO
package jnr.constants.platform.aix;

import jnr.constants.Constant;

public enum PRIO implements Constant {

    PRIO_MIN(-20L),
    PRIO_PROCESS(0L),
    PRIO_PGRP(1L),
    PRIO_USER(2L),
    PRIO_MAX(20L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = -20L;
  public static final long MAX_VALUE = 20L;

  private PRIO(long arg2) { // было: <init>
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