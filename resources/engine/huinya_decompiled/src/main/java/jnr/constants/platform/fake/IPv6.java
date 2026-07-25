// исходный (обфусцированный) внутренний класс: jnr.constants.platform.fake.IPv6
package jnr.constants.platform.fake;

import jnr.constants.Constant;

public enum IPv6 implements Constant {

    IPV6_JOIN_GROUP(1L),
    IPV6_LEAVE_GROUP(2L),
    IPV6_MULTICAST_HOPS(3L),
    IPV6_MULTICAST_IF(4L),
    IPV6_MULTICAST_LOOP(5L),
    IPV6_UNICAST_HOPS(6L),
    IPV6_V6ONLY(7L),
    IPV6_CHECKSUM(8L),
    IPV6_DONTFRAG(9L),
    IPV6_DSTOPTS(10L),
    IPV6_HOPLIMIT(11L),
    IPV6_HOPOPTS(12L),
    IPV6_NEXTHOP(13L),
    IPV6_PATHMTU(14L),
    IPV6_PKTINFO(15L),
    IPV6_RECVDSTOPTS(16L),
    IPV6_RECVHOPLIMIT(17L),
    IPV6_RECVHOPOPTS(18L),
    IPV6_RECVPKTINFO(19L),
    IPV6_RECVRTHDR(20L),
    IPV6_RECVTCLASS(21L),
    IPV6_RTHDR(22L),
    IPV6_RTHDRDSTOPTS(23L),
    IPV6_RTHDR_TYPE_0(24L),
    IPV6_RECVPATHMTU(25L),
    IPV6_TCLASS(26L),
    IPV6_USE_MIN_MTU(27L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 27L;

  private IPv6(long arg2) { // было: <init>
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