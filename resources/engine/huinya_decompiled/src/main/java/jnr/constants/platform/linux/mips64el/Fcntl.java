// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.mips64el.Fcntl
package jnr.constants.platform.linux.mips64el;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.linux.mips64el.Fcntl_StringTable;

public enum Fcntl implements Constant {

    FAPPEND(8L),
    FASYNC(4096L),
    FFSYNC(16400L),
    FNONBLOCK(128L),
    FNDELAY(128L),
    F_DUPFD(0L),
    F_GETFD(1L),
    F_SETFD(2L),
    F_GETFL(3L),
    F_SETFL(4L),
    F_GETOWN(23L),
    F_SETOWN(24L),
    F_GETLK(14L),
    F_SETLK(6L),
    F_SETLKW(7L),
    F_RDLCK(0L),
    F_UNLCK(2L),
    F_WRLCK(1L),
    F_GETPIPE_SZ(1032L),
    F_SETPIPE_SZ(1031L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 16400L;

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