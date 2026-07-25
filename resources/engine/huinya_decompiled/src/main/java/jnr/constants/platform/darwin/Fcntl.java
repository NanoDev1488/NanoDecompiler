// исходный (обфусцированный) внутренний класс: jnr.constants.platform.darwin.Fcntl
package jnr.constants.platform.darwin;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.darwin.Fcntl_StringTable;

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
    F_CHKCLEAN(41L),
    F_PREALLOCATE(42L),
    F_SETSIZE(43L),
    F_RDADVISE(44L),
    F_RDAHEAD(45L),
    F_NOCACHE(48L),
    F_LOG2PHYS(49L),
    F_GETPATH(50L),
    F_FULLFSYNC(51L),
    F_PATHPKG_CHECK(52L),
    F_FREEZE_FS(53L),
    F_THAW_FS(54L),
    F_GLOBAL_NOCACHE(55L),
    F_ADDSIGS(59L),
    F_RDLCK(1L),
    F_UNLCK(2L),
    F_WRLCK(3L),
    F_ALLOCATECONTIG(2L),
    F_ALLOCATEALL(4L);

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