// исходный (обфусцированный) внутренний класс: jnr.constants.platform.windows.OpenFlags
package jnr.constants.platform.windows;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.windows.OpenFlags_StringTable;

public enum OpenFlags implements Constant {

    O_RDONLY(0L),
    O_WRONLY(1L),
    O_RDWR(2L),
    O_ACCMODE(3L),
    O_APPEND(8L),
    O_CREAT(256L),
    O_TRUNC(512L),
    O_EXCL(1024L),
    O_BINARY(32768L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 32768L;

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