// исходный (обфусцированный) внутренний класс: jnr.constants.platform.solaris.SocketMessage
package jnr.constants.platform.solaris;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.solaris.SocketMessage_StringTable;

public enum SocketMessage implements Constant {

    MSG_DONTWAIT(128L),
    MSG_OOB(1L),
    MSG_PEEK(2L),
    MSG_DONTROUTE(4L),
    MSG_EOR(8L),
    MSG_TRUNC(32L),
    MSG_CTRUNC(16L),
    MSG_WAITALL(64L),
    MSG_NOSIGNAL(512L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 512L;

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