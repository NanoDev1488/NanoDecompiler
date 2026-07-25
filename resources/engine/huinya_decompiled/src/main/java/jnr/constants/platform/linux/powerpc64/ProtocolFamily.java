// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.powerpc64.ProtocolFamily
package jnr.constants.platform.linux.powerpc64;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.linux.powerpc64.ProtocolFamily_StringTable;

public enum ProtocolFamily implements Constant {

    PF_UNSPEC(0L),
    PF_LOCAL(1L),
    PF_UNIX(1L),
    PF_INET(2L),
    PF_SNA(22L),
    PF_DECnet(12L),
    PF_APPLETALK(5L),
    PF_ROUTE(16L),
    PF_IPX(4L),
    PF_ISDN(34L),
    PF_KEY(15L),
    PF_INET6(10L),
    PF_MAX(44L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 44L;

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