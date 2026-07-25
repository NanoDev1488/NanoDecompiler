// исходный (обфусцированный) внутренний класс: jnr.constants.platform.solaris.AddressInfo
package jnr.constants.platform.solaris;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.solaris.AddressInfo_StringTable;

public enum AddressInfo implements Constant {

    AI_PASSIVE(8L),
    AI_CANONNAME(16L),
    AI_NUMERICHOST(32L),
    AI_NUMERICSERV(64L),
    AI_ALL(2L),
    AI_ADDRCONFIG(4L),
    AI_V4MAPPED(1L),
    AI_DEFAULT(5L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 64L;

  private AddressInfo(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) AddressInfo_StringTable.descriptions.get(this));
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