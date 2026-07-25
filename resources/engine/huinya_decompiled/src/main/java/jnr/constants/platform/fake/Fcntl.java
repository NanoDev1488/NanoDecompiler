// исходный (обфусцированный) внутренний класс: jnr.constants.platform.fake.Fcntl
package jnr.constants.platform.fake;

import jnr.constants.Constant;

public enum Fcntl implements Constant {

    FAPPEND(0L),
    FREAD(1L),
    FWRITE(2L),
    FASYNC(3L),
    FFSYNC(4L),
    FNONBLOCK(5L),
    FNDELAY(6L),
    F_DUPFD(7L),
    F_GETFD(8L),
    F_SETFD(9L),
    F_GETFL(10L),
    F_SETFL(11L),
    F_GETOWN(12L),
    F_SETOWN(13L),
    F_GETLK(14L),
    F_SETLK(15L),
    F_SETLKW(16L),
    F_CHKCLEAN(17L),
    F_PREALLOCATE(18L),
    F_SETSIZE(19L),
    F_RDADVISE(20L),
    F_RDAHEAD(21L),
    F_READBOOTSTRAP(22L),
    F_WRITEBOOTSTRAP(23L),
    F_NOCACHE(24L),
    F_LOG2PHYS(25L),
    F_GETPATH(26L),
    F_FULLFSYNC(27L),
    F_PATHPKG_CHECK(28L),
    F_FREEZE_FS(29L),
    F_THAW_FS(30L),
    F_GLOBAL_NOCACHE(31L),
    F_ADDSIGS(32L),
    F_MARKDEPENDENCY(33L),
    F_RDLCK(34L),
    F_UNLCK(35L),
    F_WRLCK(36L),
    F_ALLOCATECONTIG(37L),
    F_ALLOCATEALL(38L),
    F_GETPIPE_SZ(39L),
    F_SETPIPE_SZ(40L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 40L;

  private Fcntl(long arg2) { // было: <init>
        value = arg2;
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