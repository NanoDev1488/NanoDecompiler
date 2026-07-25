// исходный (обфусцированный) внутренний класс: jnr.constants.platform.fake.Multicast
package jnr.constants.platform.fake;

import jnr.constants.Constant;

public enum Multicast implements Constant {

    MCAST_JOIN_GROUP(1L),
    MCAST_BLOCK_SOURCE(2L),
    MCAST_UNBLOCK_SOURCE(3L),
    MCAST_LEAVE_GROUP(4L),
    MCAST_JOIN_SOURCE_GROUP(5L),
    MCAST_LEAVE_SOURCE_GROUP(6L),
    MCAST_MSFILTER(7L),
    MCAST_EXCLUDE(8L),
    MCAST_INCLUDE(9L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 9L;

  private Multicast(long arg2) { // было: <init>
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