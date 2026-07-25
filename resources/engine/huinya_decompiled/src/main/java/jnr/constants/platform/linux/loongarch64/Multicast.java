// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.loongarch64.Multicast
package jnr.constants.platform.linux.loongarch64;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.linux.loongarch64.Multicast_StringTable;

public enum Multicast implements Constant {

    MCAST_JOIN_GROUP(42L),
    MCAST_BLOCK_SOURCE(43L),
    MCAST_UNBLOCK_SOURCE(44L),
    MCAST_LEAVE_GROUP(45L),
    MCAST_JOIN_SOURCE_GROUP(46L),
    MCAST_LEAVE_SOURCE_GROUP(47L),
    MCAST_MSFILTER(48L),
    MCAST_EXCLUDE(0L),
    MCAST_INCLUDE(1L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 48L;

  private Multicast(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) Multicast_StringTable.descriptions.get(this));
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