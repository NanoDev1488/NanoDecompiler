// исходный (обфусцированный) внутренний класс: jnr.constants.platform.solaris.RLIM
package jnr.constants.platform.solaris;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.solaris.RLIM_StringTable;

public enum RLIM implements Constant {

    RLIM_NLIMITS(7L),
    RLIM_INFINITY(-3L),
    RLIM_SAVED_MAX(-2L),
    RLIM_SAVED_CUR(-1L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 7L;
  public static final long MAX_VALUE = -1L;

  private RLIM(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) RLIM_StringTable.descriptions.get(this));
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