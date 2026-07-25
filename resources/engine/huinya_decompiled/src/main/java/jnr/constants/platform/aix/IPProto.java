// исходный (обфусцированный) внутренний класс: jnr.constants.platform.aix.IPProto
package jnr.constants.platform.aix;

import jnr.constants.Constant;

public enum IPProto implements Constant {

    IPPROTO_IP(0L),
    IPPROTO_HOPOPTS(0L),
    IPPROTO_ICMP(1L),
    IPPROTO_IGMP(2L),
    IPPROTO_IPIP(4L),
    IPPROTO_TCP(6L),
    IPPROTO_EGP(8L),
    IPPROTO_PUP(12L),
    IPPROTO_UDP(17L),
    IPPROTO_IDP(22L),
    IPPROTO_TP(29L),
    IPPROTO_IPV6(41L),
    IPPROTO_ROUTING(43L),
    IPPROTO_FRAGMENT(44L),
    IPPROTO_RSVP(46L),
    IPPROTO_GRE(47L),
    IPPROTO_ESP(50L),
    IPPROTO_AH(51L),
    IPPROTO_ICMPV6(58L),
    IPPROTO_NONE(59L),
    IPPROTO_DSTOPTS(60L),
    IPPROTO_SCTP(132L),
    IPPROTO_RAW(255L),
    IPPROTO_MAX(256L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 256L;

  private IPProto(long arg2) { // было: <init>
        value = arg2;
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