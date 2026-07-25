// исходный (обфусцированный) внутренний класс: jnr.constants.platform.fake.SocketLevel
package jnr.constants.platform.fake;

import jnr.constants.Constant;

public enum SocketLevel implements Constant {

    SOL_SOCKET(1L),
    SOL_IP(2L),
    SOL_TCP(3L),
    SOL_UDP(4L),
    SOL_IPV6(5L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 5L;

  private SocketLevel(long arg2) { // было: <init>
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