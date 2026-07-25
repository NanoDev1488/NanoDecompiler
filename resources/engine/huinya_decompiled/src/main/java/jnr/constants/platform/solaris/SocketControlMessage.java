// исходный (обфусцированный) внутренний класс: jnr.constants.platform.solaris.SocketControlMessage
package jnr.constants.platform.solaris;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.solaris.SocketControlMessage_StringTable;

public enum SocketControlMessage implements Constant {

    SCM_RIGHTS(4112L),
    SCM_TIMESTAMP(4115L),
    SCM_UCRED(4114L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 4112L;
  public static final long MAX_VALUE = 4115L;

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