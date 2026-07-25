// исходный (обфусцированный) внутренний класс: jnr.constants.platform.dragonflybsd.AddressInfo
package jnr.constants.platform.dragonflybsd;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.dragonflybsd.AddressInfo_StringTable;

public enum AddressInfo implements Constant {

    AI_PASSIVE(1L),
    AI_CANONNAME(2L),
    AI_NUMERICHOST(4L),
    AI_NUMERICSERV(8L),
    AI_MASK(1039L),
    AI_ALL(256L),
    AI_V4MAPPED_CFG(512L),
    AI_ADDRCONFIG(1024L),
    AI_V4MAPPED(2048L),
    AI_DEFAULT(1536L);

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