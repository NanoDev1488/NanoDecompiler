// исходный (обфусцированный) внутренний класс: jnr.constants.platform.aix.ProtocolFamily
package jnr.constants.platform.aix;

import jnr.constants.Constant;

public enum ProtocolFamily implements Constant {

    PF_UNSPEC(0L),
    PF_UNIX(1L),
    PF_INET(2L),
    PF_IMPLINK(3L),
    PF_PUP(4L),
    PF_CHAOS(5L),
    PF_NS(6L),
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
    PF_INET6(24L),
    PF_MAX(30L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 30L;

  private ProtocolFamily(long arg2) { // было: <init>
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