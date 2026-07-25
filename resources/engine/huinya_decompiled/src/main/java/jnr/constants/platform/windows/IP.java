// исходный (обфусцированный) внутренний класс: jnr.constants.platform.windows.IP
package jnr.constants.platform.windows;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.windows.IP_StringTable;

public enum IP implements Constant {

    IP_OPTIONS(1L),
    IP_TOS(8L),
    IP_TTL(7L),
    IP_MULTICAST_IF(2L),
    IP_MULTICAST_TTL(3L),
    IP_MULTICAST_LOOP(4L),
    IP_ADD_MEMBERSHIP(5L),
    IP_DROP_MEMBERSHIP(6L),
    IP_DEFAULT_MULTICAST_TTL(1L),
    IP_DEFAULT_MULTICAST_LOOP(1L),
    IP_MAX_MEMBERSHIPS(20L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 20L;

  private IP(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) IP_StringTable.descriptions.get(this));
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