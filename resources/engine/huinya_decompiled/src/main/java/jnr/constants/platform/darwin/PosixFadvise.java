// исходный (обфусцированный) внутренний класс: jnr.constants.platform.darwin.PosixFadvise
package jnr.constants.platform.darwin;

import jnr.constants.Constant;

public enum PosixFadvise implements Constant {

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 0L;

    static {
        $VALUES = new PosixFadvise[0];
    }

  private PosixFadvise(long arg2) { // было: <init>
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