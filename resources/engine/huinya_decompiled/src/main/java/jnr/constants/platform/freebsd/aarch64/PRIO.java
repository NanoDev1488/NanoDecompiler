// исходный (обфусцированный) внутренний класс: jnr.constants.platform.freebsd.aarch64.PRIO
package jnr.constants.platform.freebsd.aarch64;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.freebsd.aarch64.PRIO_StringTable;

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

  public final String toString() {
        return ((String) PRIO_StringTable.descriptions.get(this));
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