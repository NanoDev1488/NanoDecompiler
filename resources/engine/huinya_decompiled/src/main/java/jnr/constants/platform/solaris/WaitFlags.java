// исходный (обфусцированный) внутренний класс: jnr.constants.platform.solaris.WaitFlags
package jnr.constants.platform.solaris;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.solaris.WaitFlags_StringTable;

public enum WaitFlags implements Constant {

    WNOHANG(64L),
    WUNTRACED(4L),
    WSTOPPED(4L),
    WEXITED(1L),
    WCONTINUED(8L),
    WNOWAIT(128L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 128L;

  private WaitFlags(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) WaitFlags_StringTable.descriptions.get(this));
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