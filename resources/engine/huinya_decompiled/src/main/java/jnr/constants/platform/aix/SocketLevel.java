// исходный (обфусцированный) внутренний класс: jnr.constants.platform.aix.SocketLevel
package jnr.constants.platform.aix;

import jnr.constants.Constant;

public enum SocketLevel implements Constant {

    SOL_SOCKET(65535L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 65535L;
  public static final long MAX_VALUE = 65535L;

  private SocketLevel(long arg2) { // было: <init>
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