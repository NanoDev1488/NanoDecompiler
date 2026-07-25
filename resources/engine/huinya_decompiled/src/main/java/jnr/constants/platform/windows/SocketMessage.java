// исходный (обфусцированный) внутренний класс: jnr.constants.platform.windows.SocketMessage
package jnr.constants.platform.windows;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.windows.SocketMessage_StringTable;

public enum SocketMessage implements Constant {

    MSG_OOB(1L),
    MSG_PEEK(2L),
    MSG_DONTROUTE(4L),
    MSG_WAITALL(8L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 8L;

  private SocketMessage(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) SocketMessage_StringTable.descriptions.get(this));
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