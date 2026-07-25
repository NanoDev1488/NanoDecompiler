// исходный (обфусцированный) внутренний класс: jnr.constants.platform.windows.Signal
package jnr.constants.platform.windows;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.windows.Signal_StringTable;

public enum Signal implements Constant {

    SIGINT(2L),
    SIGILL(4L),
    SIGABRT(22L),
    SIGFPE(8L),
    SIGSEGV(11L),
    SIGTERM(15L),
    NSIG(23L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 2L;
  public static final long MAX_VALUE = 23L;

  private Signal(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) Signal_StringTable.descriptions.get(this));
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