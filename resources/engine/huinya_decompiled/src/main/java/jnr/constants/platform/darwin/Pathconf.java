// исходный (обфусцированный) внутренний класс: jnr.constants.platform.darwin.Pathconf
package jnr.constants.platform.darwin;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.darwin.Pathconf_StringTable;

public enum Pathconf implements Constant {

    _PC_FILESIZEBITS(18L),
    _PC_LINK_MAX(1L),
    _PC_MAX_CANON(2L),
    _PC_MAX_INPUT(3L),
    _PC_NAME_MAX(4L),
    _PC_PATH_MAX(5L),
    _PC_PIPE_BUF(6L),
    _PC_2_SYMLINKS(15L),
    _PC_ALLOC_SIZE_MIN(16L),
    _PC_REC_INCR_XFER_SIZE(20L),
    _PC_REC_MAX_XFER_SIZE(21L),
    _PC_REC_MIN_XFER_SIZE(22L),
    _PC_REC_XFER_ALIGN(23L),
    _PC_SYMLINK_MAX(24L),
    _PC_CHOWN_RESTRICTED(7L),
    _PC_NO_TRUNC(8L),
    _PC_VDISABLE(9L),
    _PC_ASYNC_IO(17L),
    _PC_PRIO_IO(19L),
    _PC_SYNC_IO(25L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 25L;

  private Pathconf(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) Pathconf_StringTable.descriptions.get(this));
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