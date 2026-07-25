// исходный (обфусцированный) внутренний класс: jnr.constants.platform.darwin.SocketMessage
package jnr.constants.platform.darwin;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.darwin.SocketMessage_StringTable;

public enum SocketMessage implements Constant {

    MSG_DONTWAIT(128L),
    MSG_OOB(1L),
    MSG_PEEK(2L),
    MSG_DONTROUTE(4L),
    MSG_EOR(8L),
    MSG_TRUNC(16L),
    MSG_CTRUNC(32L),
    MSG_WAITALL(64L),
    MSG_EOF(256L),
    MSG_FLUSH(1024L),
    MSG_HOLD(2048L),
    MSG_SEND(4096L),
    MSG_HAVEMORE(8192L),
    MSG_RCVMORE(16384L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 16384L;

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