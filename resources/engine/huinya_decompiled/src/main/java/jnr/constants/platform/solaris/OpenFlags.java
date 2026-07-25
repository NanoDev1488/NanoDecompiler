// исходный (обфусцированный) внутренний класс: jnr.constants.platform.solaris.OpenFlags
package jnr.constants.platform.solaris;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.solaris.OpenFlags_StringTable;

public enum OpenFlags implements Constant {

    O_RDONLY(0L),
    O_WRONLY(1L),
    O_RDWR(2L),
    O_ACCMODE(6291459L),
    O_NONBLOCK(128L),
    O_APPEND(8L),
    O_SYNC(16L),
    O_NOFOLLOW(131072L),
    O_CREAT(256L),
    O_TRUNC(512L),
    O_EXCL(1024L),
    O_DIRECTORY(16777216L),
    O_NOCTTY(2048L),
    O_CLOEXEC(8388608L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 16777216L;

  private OpenFlags(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) OpenFlags_StringTable.descriptions.get(this));
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