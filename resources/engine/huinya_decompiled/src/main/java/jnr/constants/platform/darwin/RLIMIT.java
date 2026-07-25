// исходный (обфусцированный) внутренний класс: jnr.constants.platform.darwin.RLIMIT
package jnr.constants.platform.darwin;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.darwin.RLIMIT_StringTable;

public enum RLIMIT implements Constant {

    RLIMIT_AS(5L),
    RLIMIT_CORE(4L),
    RLIMIT_CPU(0L),
    RLIMIT_DATA(2L),
    RLIMIT_FSIZE(1L),
    RLIMIT_MEMLOCK(6L),
    RLIMIT_NOFILE(8L),
    RLIMIT_NPROC(7L),
    RLIMIT_RSS(5L),
    RLIMIT_STACK(3L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 8L;

  private RLIMIT(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) RLIMIT_StringTable.descriptions.get(this));
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