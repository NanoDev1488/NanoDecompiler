// исходный (обфусцированный) внутренний класс: jnr.constants.platform.aix.Fcntl
package jnr.constants.platform.aix;

import jnr.constants.Constant;

public enum Fcntl implements Constant {

    F_DUPFD(0L),
    F_GETFD(1L),
    F_SETFD(2L),
    F_GETFL(3L),
    F_SETFL(4L),
    F_GETOWN(8L),
    F_SETOWN(9L),
    F_GETLK(11L),
    F_SETLK(12L),
    F_SETLKW(13L),
    F_RDLCK(1L),
    F_UNLCK(3L),
    F_WRLCK(2L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 13L;

  private Fcntl(long arg2) { // было: <init>
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