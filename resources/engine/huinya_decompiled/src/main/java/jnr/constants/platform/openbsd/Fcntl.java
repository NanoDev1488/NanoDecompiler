// исходный (обфусцированный) внутренний класс: jnr.constants.platform.openbsd.Fcntl
package jnr.constants.platform.openbsd;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.openbsd.Fcntl_StringTable;

public enum Fcntl implements Constant {

    FAPPEND(8L),
    FREAD(1L),
    FWRITE(2L),
    FASYNC(64L),
    FFSYNC(128L),
    FNONBLOCK(4L),
    FNDELAY(4L),
    F_DUPFD(0L),
    F_GETFD(1L),
    F_SETFD(2L),
    F_GETFL(3L),
    F_SETFL(4L),
    F_GETOWN(5L),
    F_SETOWN(6L),
    F_GETLK(7L),
    F_SETLK(8L),
    F_SETLKW(9L),
    F_RDLCK(1L),
    F_UNLCK(2L),
    F_WRLCK(3L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 128L;

  private Fcntl(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) Fcntl_StringTable.descriptions.get(this));
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