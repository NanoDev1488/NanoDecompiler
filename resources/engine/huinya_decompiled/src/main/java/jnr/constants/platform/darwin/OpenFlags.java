// исходный (обфусцированный) внутренний класс: jnr.constants.platform.darwin.OpenFlags
package jnr.constants.platform.darwin;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.darwin.OpenFlags_StringTable;

public enum OpenFlags implements Constant {

    O_RDONLY(0L),
    O_WRONLY(1L),
    O_RDWR(2L),
    O_ACCMODE(3L),
    O_NONBLOCK(4L),
    O_APPEND(8L),
    O_SYNC(128L),
    O_SHLOCK(16L),
    O_EXLOCK(32L),
    O_ASYNC(64L),
    O_FSYNC(128L),
    O_NOFOLLOW(256L),
    O_CREAT(512L),
    O_TRUNC(1024L),
    O_EXCL(2048L),
    O_EVTONLY(32768L),
    O_DIRECTORY(1048576L),
    O_SYMLINK(2097152L),
    O_NOCTTY(131072L),
    O_CLOEXEC(16777216L);

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