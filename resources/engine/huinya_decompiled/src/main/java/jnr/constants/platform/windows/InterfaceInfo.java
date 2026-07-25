// исходный (обфусцированный) внутренний класс: jnr.constants.platform.windows.InterfaceInfo
package jnr.constants.platform.windows;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.windows.InterfaceInfo_StringTable;

public enum InterfaceInfo implements Constant {

    IFF_BROADCAST(2L),
    IFF_LOOPBACK(4L),
    IFF_MULTICAST(16L),
    IFF_UP(1L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 16L;

  private InterfaceInfo(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) InterfaceInfo_StringTable.descriptions.get(this));
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