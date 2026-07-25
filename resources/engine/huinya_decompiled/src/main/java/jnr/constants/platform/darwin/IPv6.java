// исходный (обфусцированный) внутренний класс: jnr.constants.platform.darwin.IPv6
package jnr.constants.platform.darwin;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.darwin.IPv6_StringTable;

public enum IPv6 implements Constant {

    IPV6_JOIN_GROUP(12L),
    IPV6_LEAVE_GROUP(13L),
    IPV6_MULTICAST_HOPS(10L),
    IPV6_MULTICAST_IF(9L),
    IPV6_MULTICAST_LOOP(11L),
    IPV6_UNICAST_HOPS(4L),
    IPV6_V6ONLY(27L),
    IPV6_CHECKSUM(26L),
    IPV6_RECVTCLASS(35L),
    IPV6_RTHDR_TYPE_0(0L),
    IPV6_TCLASS(36L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 36L;

  private IPv6(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) IPv6_StringTable.descriptions.get(this));
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