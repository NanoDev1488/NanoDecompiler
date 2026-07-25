// исходный (обфусцированный) внутренний класс: jnr.constants.platform.freebsd.PosixFadvise
package jnr.constants.platform.freebsd;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.freebsd.PosixFadvise_StringTable;

public enum PosixFadvise implements Constant {

    POSIX_FADV_NORMAL(0L),
    POSIX_FADV_SEQUENTIAL(2L),
    POSIX_FADV_RANDOM(1L),
    POSIX_FADV_NOREUSE(5L),
    POSIX_FADV_WILLNEED(3L),
    POSIX_FADV_DONTNEED(4L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 5L;

  private PosixFadvise(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) PosixFadvise_StringTable.descriptions.get(this));
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