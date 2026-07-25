// исходный (обфусцированный) внутренний класс: jnr.constants.platform.freebsd.aarch64.SocketMessage
package jnr.constants.platform.freebsd.aarch64;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.freebsd.aarch64.SocketMessage_StringTable;

public enum SocketMessage implements Constant {

    MSG_DONTWAIT(128L),
    MSG_OOB(1L),
    MSG_PEEK(2L),
    MSG_DONTROUTE(4L),
    MSG_EOR(8L),
    MSG_TRUNC(16L),
    MSG_CTRUNC(32L),
    MSG_WAITALL(64L),
    MSG_NOSIGNAL(131072L),
    MSG_EOF(256L),
    MSG_COMPAT(32768L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 131072L;

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