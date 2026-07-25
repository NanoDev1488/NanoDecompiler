// исходный (обфусцированный) внутренний класс: jnr.constants.platform.fake.IP
package jnr.constants.platform.fake;

import jnr.constants.Constant;

public enum IP implements Constant {

    IP_OPTIONS(1L),
    IP_HDRINCL(2L),
    IP_TOS(3L),
    IP_TTL(4L),
    IP_RECVOPTS(5L),
    IP_RECVRETOPTS(6L),
    IP_RECVDSTADDR(7L),
    IP_RETOPTS(8L),
    IP_MINTTL(9L),
    IP_DONTFRAG(10L),
    IP_SENDSRCADDR(11L),
    IP_ONESBCAST(12L),
    IP_RECVTTL(13L),
    IP_RECVIF(14L),
    IP_RECVSLLA(15L),
    IP_PORTRANGE(16L),
    IP_MULTICAST_IF(17L),
    IP_MULTICAST_TTL(18L),
    IP_MULTICAST_LOOP(19L),
    IP_ADD_MEMBERSHIP(20L),
    IP_DROP_MEMBERSHIP(21L),
    IP_DEFAULT_MULTICAST_TTL(22L),
    IP_DEFAULT_MULTICAST_LOOP(23L),
    IP_MAX_MEMBERSHIPS(24L),
    IP_ROUTER_ALERT(25L),
    IP_PKTINFO(26L),
    IP_PKTOPTIONS(27L),
    IP_MTU_DISCOVER(28L),
    IP_RECVERR(29L),
    IP_RECVTOS(30L),
    IP_MTU(31L),
    IP_FREEBIND(32L),
    IP_IPSEC_POLICY(33L),
    IP_XFRM_POLICY(34L),
    IP_PASSSEC(35L),
    IP_TRANSPARENT(36L),
    IP_PMTUDISC_DONT(37L),
    IP_PMTUDISC_WANT(38L),
    IP_PMTUDISC_DO(39L),
    IP_UNBLOCK_SOURCE(40L),
    IP_BLOCK_SOURCE(41L),
    IP_ADD_SOURCE_MEMBERSHIP(42L),
    IP_DROP_SOURCE_MEMBERSHIP(43L),
    IP_MSFILTER(44L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 44L;

  private IP(long arg2) { // было: <init>
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