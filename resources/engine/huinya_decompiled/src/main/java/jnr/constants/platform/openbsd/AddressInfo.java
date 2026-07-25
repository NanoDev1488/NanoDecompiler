// исходный (обфусцированный) внутренний класс: jnr.constants.platform.openbsd.AddressInfo
package jnr.constants.platform.openbsd;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.openbsd.AddressInfo_StringTable;

public enum AddressInfo implements Constant {

    AI_PASSIVE(1L),
    AI_CANONNAME(2L),
    AI_NUMERICHOST(4L),
    AI_NUMERICSERV(16L),
    AI_MASK(119L),
    AI_ADDRCONFIG(64L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 119L;

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