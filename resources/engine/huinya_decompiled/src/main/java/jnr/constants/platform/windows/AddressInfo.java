// исходный (обфусцированный) внутренний класс: jnr.constants.platform.windows.AddressInfo
package jnr.constants.platform.windows;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.windows.AddressInfo_StringTable;

public enum AddressInfo implements Constant {

    AI_PASSIVE(1L),
    AI_CANONNAME(2L),
    AI_NUMERICHOST(4L),
    AI_NUMERICSERV(8L),
    AI_ALL(256L),
    AI_ADDRCONFIG(1024L),
    AI_V4MAPPED(2048L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 2048L;

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