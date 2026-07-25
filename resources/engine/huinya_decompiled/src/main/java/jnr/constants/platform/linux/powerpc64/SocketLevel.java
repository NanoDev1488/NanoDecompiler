// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.powerpc64.SocketLevel
package jnr.constants.platform.linux.powerpc64;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.linux.powerpc64.SocketLevel_StringTable;

public enum SocketLevel implements Constant {

    SOL_SOCKET(1L),
    SOL_IP(0L),
    SOL_TCP(6L),
    SOL_UDP(17L),
    SOL_IPV6(41L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 41L;

  private SocketLevel(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) SocketLevel_StringTable.descriptions.get(this));
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