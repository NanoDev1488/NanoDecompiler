// исходный (обфусцированный) внутренний класс: jnr.constants.platform.fake.UDP
package jnr.constants.platform.fake;

import jnr.constants.Constant;

public enum UDP implements Constant {

    UDP_CORK(1L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 1L;

  private UDP(long arg2) { // было: <init>
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