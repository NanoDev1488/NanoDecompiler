// исходный (обфусцированный) внутренний класс: jnr.constants.platform.windows.IPv6
package jnr.constants.platform.windows;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.windows.IPv6_StringTable;

public enum IPv6 implements Constant {

    IPV6_JOIN_GROUP(12L),
    IPV6_LEAVE_GROUP(13L),
    IPV6_MULTICAST_HOPS(10L),
    IPV6_MULTICAST_IF(9L),
    IPV6_MULTICAST_LOOP(11L),
    IPV6_UNICAST_HOPS(4L),
    IPV6_V6ONLY(27L),
    IPV6_CHECKSUM(26L),
    IPV6_DONTFRAG(14L),
    IPV6_HOPLIMIT(21L),
    IPV6_HOPOPTS(1L),
    IPV6_PKTINFO(19L),
    IPV6_RECVRTHDR(38L),
    IPV6_RECVTCLASS(40L),
    IPV6_RTHDR(32L),
    IPV6_TCLASS(39L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 40L;

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