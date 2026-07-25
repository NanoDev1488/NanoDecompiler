// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.powerpc64.SocketMessage
package jnr.constants.platform.linux.powerpc64;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.linux.powerpc64.SocketMessage_StringTable;

public enum SocketMessage implements Constant {

    MSG_DONTWAIT(64L),
    MSG_OOB(1L),
    MSG_PEEK(2L),
    MSG_DONTROUTE(4L),
    MSG_EOR(128L),
    MSG_TRUNC(32L),
    MSG_CTRUNC(8L),
    MSG_WAITALL(256L),
    MSG_PROXY(16L),
    MSG_FIN(512L),
    MSG_SYN(1024L),
    MSG_CONFIRM(2048L),
    MSG_RST(4096L),
    MSG_ERRQUEUE(8192L),
    MSG_NOSIGNAL(16384L),
    MSG_MORE(32768L),
    MSG_FASTOPEN(536870912L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 536870912L;

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