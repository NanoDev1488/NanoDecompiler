// исходный (обфусцированный) внутренний класс: jnr.constants.platform.freebsd.AddressFamily
package jnr.constants.platform.freebsd;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.freebsd.AddressFamily_StringTable;

public enum AddressFamily implements Constant {

    AF_UNSPEC(0L),
    AF_LOCAL(1L),
    AF_UNIX(1L),
    AF_INET(2L),
    AF_IMPLINK(3L),
    AF_PUP(4L),
    AF_CHAOS(5L),
    AF_ISO(7L),
    AF_OSI(7L),
    AF_ECMA(8L),
    AF_DATAKIT(9L),
    AF_CCITT(10L),
    AF_SNA(11L),
    AF_DECnet(12L),
    AF_DLI(13L),
    AF_LAT(14L),
    AF_HYLINK(15L),
    AF_APPLETALK(16L),
    AF_ROUTE(17L),
    AF_LINK(18L),
    pseudo_AF_XTP(19L),
    AF_COIP(20L),
    AF_CNT(21L),
    pseudo_AF_RTIP(22L),
    AF_IPX(23L),
    AF_SIP(24L),
    pseudo_AF_PIP(25L),
    AF_ISDN(26L),
    AF_E164(26L),
    pseudo_AF_KEY(27L),
    AF_INET6(28L),
    AF_NATM(29L),
    AF_NETBIOS(6L),
    AF_ATM(30L),
    pseudo_AF_HDRCMPLT(31L),
    AF_NETGRAPH(32L),
    AF_MAX(43L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 43L;

  private AddressFamily(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) AddressFamily_StringTable.descriptions.get(this));
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