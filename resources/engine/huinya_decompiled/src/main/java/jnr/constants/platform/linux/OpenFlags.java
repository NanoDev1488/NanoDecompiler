// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.OpenFlags
package jnr.constants.platform.linux;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.linux.OpenFlags_StringTable;

public enum OpenFlags implements Constant {

    O_RDONLY(0L),
    O_WRONLY(1L),
    O_RDWR(2L),
    O_ACCMODE(3L),
    O_NONBLOCK(2048L),
    O_APPEND(1024L),
    O_SYNC(1052672L),
    O_ASYNC(8192L),
    O_FSYNC(1052672L),
    O_NOFOLLOW(131072L),
    O_CREAT(64L),
    O_TRUNC(512L),
    O_EXCL(128L),
    O_DIRECTORY(65536L),
    O_NOCTTY(256L),
    O_TMPFILE(4259840L),
    O_CLOEXEC(524288L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 4259840L;

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