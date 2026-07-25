// исходный (обфусцированный) внутренний класс: jnr.constants.platform.darwin.Multicast
package jnr.constants.platform.darwin;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.darwin.Multicast_StringTable;

public enum Multicast implements Constant {

    MCAST_JOIN_GROUP(80L),
    MCAST_BLOCK_SOURCE(84L),
    MCAST_UNBLOCK_SOURCE(85L),
    MCAST_LEAVE_GROUP(81L),
    MCAST_JOIN_SOURCE_GROUP(82L),
    MCAST_LEAVE_SOURCE_GROUP(83L),
    MCAST_EXCLUDE(2L),
    MCAST_INCLUDE(1L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 85L;

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