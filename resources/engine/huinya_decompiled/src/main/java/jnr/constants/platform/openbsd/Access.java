// исходный (обфусцированный) внутренний класс: jnr.constants.platform.openbsd.Access
package jnr.constants.platform.openbsd;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.openbsd.Access_StringTable;

public enum Access implements Constant {

    F_OK(0L),
    X_OK(1L),
    W_OK(2L),
    R_OK(4L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 4L;

  private Access(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) Access_StringTable.descriptions.get(this));
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