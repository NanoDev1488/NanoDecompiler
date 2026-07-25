// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.powerpc64.AddressFamily
package jnr.constants.platform.linux.powerpc64;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.linux.powerpc64.AddressFamily_StringTable;

public enum AddressFamily implements Constant {

    AF_UNSPEC(0L),
    AF_LOCAL(1L),
    AF_UNIX(1L),
    AF_INET(2L),
    AF_SNA(22L),
    AF_DECnet(12L),
    AF_APPLETALK(5L),
    AF_ROUTE(16L),
    AF_IPX(4L),
    AF_ISDN(34L),
    AF_INET6(10L),
    AF_AX25(3L),
    AF_MAX(44L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 44L;

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