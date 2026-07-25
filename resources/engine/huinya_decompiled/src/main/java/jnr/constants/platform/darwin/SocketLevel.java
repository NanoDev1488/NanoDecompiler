// исходный (обфусцированный) внутренний класс: jnr.constants.platform.darwin.SocketLevel
package jnr.constants.platform.darwin;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.darwin.SocketLevel_StringTable;

public enum SocketLevel implements Constant {

    SOL_SOCKET(65535L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 65535L;
  public static final long MAX_VALUE = 65535L;

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