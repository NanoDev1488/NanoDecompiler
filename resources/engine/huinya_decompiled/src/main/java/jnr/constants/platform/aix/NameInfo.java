// исходный (обфусцированный) внутренний класс: jnr.constants.platform.aix.NameInfo
package jnr.constants.platform.aix;

import jnr.constants.Constant;

public enum NameInfo implements Constant {

    NI_MAXHOST(1025L),
    NI_MAXSERV(32L),
    NI_NOFQDN(1L),
    NI_NUMERICHOST(2L),
    NI_NAMEREQD(4L),
    NI_NUMERICSERV(8L),
    NI_DGRAM(16L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 1025L;

  private NameInfo(long arg2) { // было: <init>
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