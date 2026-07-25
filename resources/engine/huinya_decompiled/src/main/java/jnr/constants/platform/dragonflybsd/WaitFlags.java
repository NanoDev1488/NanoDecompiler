// исходный (обфусцированный) внутренний класс: jnr.constants.platform.dragonflybsd.WaitFlags
package jnr.constants.platform.dragonflybsd;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.dragonflybsd.WaitFlags_StringTable;

public enum WaitFlags implements Constant {

    WNOHANG(1L),
    WUNTRACED(2L),
    WSTOPPED(2L),
    WEXITED(16L),
    WCONTINUED(4L),
    WNOWAIT(8L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 16L;

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