// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.Local
package jnr.constants.platform.linux;

import jnr.constants.Constant;

public enum Local implements Constant {

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 0L;

    static {
        $VALUES = new Local[0];
    }

  private Local(long arg2) { // было: <init>
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