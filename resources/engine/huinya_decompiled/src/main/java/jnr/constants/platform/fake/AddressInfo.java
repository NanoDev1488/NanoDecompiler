// исходный (обфусцированный) внутренний класс: jnr.constants.platform.fake.AddressInfo
package jnr.constants.platform.fake;

import jnr.constants.Constant;

public enum AddressInfo implements Constant {

    AI_PASSIVE(1L),
    AI_CANONNAME(2L),
    AI_NUMERICHOST(3L),
    AI_NUMERICSERV(4L),
    AI_MASK(5L),
    AI_ALL(6L),
    AI_V4MAPPED_CFG(7L),
    AI_ADDRCONFIG(8L),
    AI_V4MAPPED(9L),
    AI_DEFAULT(10L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 10L;

  private AddressInfo(long arg2) { // было: <init>
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