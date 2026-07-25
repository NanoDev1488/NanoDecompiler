// исходный (обфусцированный) внутренний класс: jnr.constants.platform.aix.AddressFamily
package jnr.constants.platform.aix;

import jnr.constants.Constant;

public enum AddressFamily implements Constant {

    AF_UNSPEC(0L),
    AF_UNIX(1L),
    AF_INET(2L),
    AF_IMPLINK(3L),
    AF_PUP(4L),
    AF_CHAOS(5L),
    AF_NS(6L),
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
    AF_INET6(24L),
    AF_MAX(30L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 30L;

  private AddressFamily(long arg2) { // было: <init>
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