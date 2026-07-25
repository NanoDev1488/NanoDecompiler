// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.aarch64.Locale
package jnr.constants.platform.linux.aarch64;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.linux.aarch64.Locale_StringTable;

public enum Locale implements Constant {

    LC_CTYPE(0L),
    LC_NUMERIC(1L),
    LC_TIME(2L),
    LC_COLLATE(3L),
    LC_MONETARY(4L),
    LC_MESSAGES(5L),
    LC_ALL(6L),
    LC_PAPER(7L),
    LC_NAME(8L),
    LC_ADDRESS(9L),
    LC_TELEPHONE(10L),
    LC_MEASUREMENT(11L),
    LC_IDENTIFICATION(12L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 12L;

  private Locale(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) Locale_StringTable.descriptions.get(this));
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