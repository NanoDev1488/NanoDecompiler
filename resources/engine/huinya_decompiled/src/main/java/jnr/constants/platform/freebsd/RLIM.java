// исходный (обфусцированный) внутренний класс: jnr.constants.platform.freebsd.RLIM
package jnr.constants.platform.freebsd;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.freebsd.RLIM_StringTable;

public enum RLIM implements Constant {

    RLIM_NLIMITS(15L),
    RLIM_INFINITY(9223372036854775807L),
    RLIM_SAVED_MAX(9223372036854775807L),
    RLIM_SAVED_CUR(9223372036854775807L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 15L;
  public static final long MAX_VALUE = 9223372036854775807L;

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