// исходный (обфусцированный) внутренний класс: jnr.constants.platform.freebsd.aarch64.ProtocolFamily
package jnr.constants.platform.freebsd.aarch64;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.freebsd.aarch64.ProtocolFamily_StringTable;

public enum ProtocolFamily implements Constant {

    PF_UNSPEC(0L),
    PF_LOCAL(1L),
    PF_UNIX(1L),
    PF_INET(2L),
    PF_IMPLINK(3L),
    PF_PUP(4L),
    PF_CHAOS(5L),
    PF_ISO(7L),
    PF_OSI(7L),
    PF_ECMA(8L),
    PF_DATAKIT(9L),
    PF_CCITT(10L),
    PF_SNA(11L),
    PF_DECnet(12L),
    PF_DLI(13L),
    PF_LAT(14L),
    PF_HYLINK(15L),
    PF_APPLETALK(16L),
    PF_ROUTE(17L),
    PF_LINK(18L),
    PF_XTP(19L),
    PF_COIP(20L),
    PF_CNT(21L),
    PF_SIP(24L),
    PF_IPX(23L),
    PF_RTIP(22L),
    PF_PIP(25L),
    PF_ISDN(26L),
    PF_KEY(27L),
    PF_INET6(28L),
    PF_NATM(29L),
    PF_NETBIOS(6L),
    PF_ATM(30L),
    PF_NETGRAPH(32L),
    PF_MAX(43L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 43L;

  private ProtocolFamily(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) ProtocolFamily_StringTable.descriptions.get(this));
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