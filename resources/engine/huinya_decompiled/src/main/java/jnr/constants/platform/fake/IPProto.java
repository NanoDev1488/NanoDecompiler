// исходный (обфусцированный) внутренний класс: jnr.constants.platform.fake.IPProto
package jnr.constants.platform.fake;

import jnr.constants.Constant;

public enum IPProto implements Constant {

    IPPROTO_IP(1L),
    IPPROTO_HOPOPTS(2L),
    IPPROTO_ICMP(3L),
    IPPROTO_IGMP(4L),
    IPPROTO_IPIP(5L),
    IPPROTO_TCP(6L),
    IPPROTO_EGP(7L),
    IPPROTO_PUP(8L),
    IPPROTO_UDP(9L),
    IPPROTO_IDP(10L),
    IPPROTO_TP(11L),
    IPPROTO_IPV6(12L),
    IPPROTO_ROUTING(13L),
    IPPROTO_FRAGMENT(14L),
    IPPROTO_RSVP(15L),
    IPPROTO_GRE(16L),
    IPPROTO_ESP(17L),
    IPPROTO_AH(18L),
    IPPROTO_ICMPV6(19L),
    IPPROTO_NONE(20L),
    IPPROTO_DSTOPTS(21L),
    IPPROTO_MTP(22L),
    IPPROTO_ENCAP(23L),
    IPPROTO_PIM(24L),
    IPPROTO_COMP(25L),
    IPPROTO_SCTP(26L),
    IPPROTO_RAW(27L),
    IPPROTO_MAX(28L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 28L;

  private IPProto(long arg2) { // было: <init>
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