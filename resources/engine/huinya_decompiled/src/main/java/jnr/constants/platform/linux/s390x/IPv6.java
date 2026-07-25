// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.s390x.IPv6
package jnr.constants.platform.linux.s390x;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.linux.s390x.IPv6_StringTable;

public enum IPv6 implements Constant {

    IPV6_JOIN_GROUP(20L),
    IPV6_LEAVE_GROUP(21L),
    IPV6_MULTICAST_HOPS(18L),
    IPV6_MULTICAST_IF(17L),
    IPV6_MULTICAST_LOOP(19L),
    IPV6_UNICAST_HOPS(16L),
    IPV6_V6ONLY(26L),
    IPV6_CHECKSUM(7L),
    IPV6_DONTFRAG(62L),
    IPV6_DSTOPTS(59L),
    IPV6_HOPLIMIT(52L),
    IPV6_HOPOPTS(54L),
    IPV6_NEXTHOP(9L),
    IPV6_PATHMTU(61L),
    IPV6_PKTINFO(50L),
    IPV6_RECVDSTOPTS(58L),
    IPV6_RECVHOPLIMIT(51L),
    IPV6_RECVHOPOPTS(53L),
    IPV6_RECVPKTINFO(49L),
    IPV6_RECVRTHDR(56L),
    IPV6_RECVTCLASS(66L),
    IPV6_RTHDR(57L),
    IPV6_RTHDRDSTOPTS(55L),
    IPV6_RTHDR_TYPE_0(0L),
    IPV6_RECVPATHMTU(60L),
    IPV6_TCLASS(67L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 67L;

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