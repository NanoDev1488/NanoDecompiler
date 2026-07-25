// исходный (обфусцированный) внутренний класс: jnr.constants.platform.dragonflybsd.SocketControlMessage
package jnr.constants.platform.dragonflybsd;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.dragonflybsd.SocketControlMessage_StringTable;

public enum SocketControlMessage implements Constant {

    SCM_RIGHTS(1L),
    SCM_TIMESTAMP(2L),
    SCM_CREDS(3L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 3L;

  private SocketControlMessage(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) SocketControlMessage_StringTable.descriptions.get(this));
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