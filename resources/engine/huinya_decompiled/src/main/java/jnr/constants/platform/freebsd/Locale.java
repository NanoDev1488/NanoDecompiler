// исходный (обфусцированный) внутренний класс: jnr.constants.platform.freebsd.Locale
package jnr.constants.platform.freebsd;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.freebsd.Locale_StringTable;

public enum Locale implements Constant {

    LC_CTYPE(2L),
    LC_NUMERIC(4L),
    LC_TIME(5L),
    LC_COLLATE(1L),
    LC_MONETARY(3L),
    LC_MESSAGES(6L),
    LC_ALL(0L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 6L;

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