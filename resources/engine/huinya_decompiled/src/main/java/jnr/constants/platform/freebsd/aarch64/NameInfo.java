// исходный (обфусцированный) внутренний класс: jnr.constants.platform.freebsd.aarch64.NameInfo
package jnr.constants.platform.freebsd.aarch64;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.freebsd.aarch64.NameInfo_StringTable;

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

  public final String toString() {
        return ((String) NameInfo_StringTable.descriptions.get(this));
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