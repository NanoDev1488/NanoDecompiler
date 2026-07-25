// исходный (обфусцированный) внутренний класс: jnr.constants.platform.fake.AddressFamily
package jnr.constants.platform.fake;

import jnr.constants.Constant;

public enum AddressFamily implements Constant {

    AF_UNSPEC(1L),
    AF_LOCAL(2L),
    AF_UNIX(3L),
    AF_INET(4L),
    AF_IMPLINK(5L),
    AF_PUP(6L),
    AF_CHAOS(7L),
    AF_NS(8L),
    AF_ISO(9L),
    AF_OSI(10L),
    AF_ECMA(11L),
    AF_DATAKIT(12L),
    AF_CCITT(13L),
    AF_SNA(14L),
    AF_DECnet(15L),
    AF_DLI(16L),
    AF_LAT(17L),
    AF_HYLINK(18L),
    AF_APPLETALK(19L),
    AF_ROUTE(20L),
    AF_LINK(21L),
    pseudo_AF_XTP(22L),
    AF_COIP(23L),
    AF_CNT(24L),
    pseudo_AF_RTIP(25L),
    AF_IPX(26L),
    AF_SIP(27L),
    pseudo_AF_PIP(28L),
    AF_NDRV(29L),
    AF_ISDN(30L),
    AF_E164(31L),
    pseudo_AF_KEY(32L),
    AF_INET6(33L),
    AF_NATM(34L),
    AF_SYSTEM(35L),
    AF_NETBIOS(36L),
    AF_PPP(37L),
    AF_ATM(38L),
    pseudo_AF_HDRCMPLT(39L),
    AF_NETGRAPH(40L),
    AF_AX25(41L),
    AF_KEY(42L),
    AF_NETLINK(43L),
    AF_RDS(44L),
    AF_PPPOX(45L),
    AF_LLC(46L),
    AF_IB(47L),
    AF_MPLS(48L),
    AF_CAN(49L),
    AF_TIPC(50L),
    AF_BLUETOOTH(51L),
    AF_ALG(52L),
    AF_VSOCK(53L),
    AF_KCM(54L),
    AF_XDP(55L),
    AF_MAX(56L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 56L;

  private AddressFamily(long arg2) { // было: <init>
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