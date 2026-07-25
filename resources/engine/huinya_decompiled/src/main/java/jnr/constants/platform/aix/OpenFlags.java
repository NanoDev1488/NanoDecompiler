// исходный (обфусцированный) внутренний класс: jnr.constants.platform.aix.OpenFlags
package jnr.constants.platform.aix;

import jnr.constants.Constant;

public enum OpenFlags implements Constant {

    O_RDONLY(0L),
    O_WRONLY(1L),
    O_RDWR(2L),
    O_ACCMODE(3L),
    O_NONBLOCK(4L),
    O_APPEND(8L),
    O_SYNC(16L),
    O_CREAT(256L),
    O_TRUNC(512L),
    O_EXCL(1024L),
    O_DIRECTORY(524288L),
    O_NOCTTY(2048L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 524288L;

  private OpenFlags(long arg2) { // было: <init>
        value = arg2;
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