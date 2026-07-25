// исходный (обфусцированный) внутренний класс: jnr.constants.platform.solaris.IPv6
package jnr.constants.platform.solaris;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.solaris.IPv6_StringTable;

public enum IPv6 implements Constant {

    IPV6_JOIN_GROUP(9L),
    IPV6_LEAVE_GROUP(10L),
    IPV6_MULTICAST_HOPS(7L),
    IPV6_MULTICAST_IF(6L),
    IPV6_MULTICAST_LOOP(8L),
    IPV6_UNICAST_HOPS(5L),
    IPV6_V6ONLY(39L),
    IPV6_CHECKSUM(24L),
    IPV6_DONTFRAG(33L),
    IPV6_DSTOPTS(15L),
    IPV6_HOPLIMIT(12L),
    IPV6_HOPOPTS(14L),
    IPV6_NEXTHOP(13L),
    IPV6_PATHMTU(37L),
    IPV6_PKTINFO(11L),
    IPV6_RECVDSTOPTS(40L),
    IPV6_RECVHOPLIMIT(19L),
    IPV6_RECVHOPOPTS(20L),
    IPV6_RECVPKTINFO(18L),
    IPV6_RECVRTHDR(22L),
    IPV6_RECVTCLASS(25L),
    IPV6_RTHDR(16L),
    IPV6_RTHDRDSTOPTS(17L),
    IPV6_RTHDR_TYPE_0(0L),
    IPV6_RECVPATHMTU(36L),
    IPV6_TCLASS(38L),
    IPV6_USE_MIN_MTU(32L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
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