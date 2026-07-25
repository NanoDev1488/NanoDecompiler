// исходный (обфусцированный) внутренний класс: jnr.constants.platform.freebsd.Pathconf
package jnr.constants.platform.freebsd;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.freebsd.Pathconf_StringTable;

public enum Pathconf implements Constant {

    _PC_FILESIZEBITS(12L),
    _PC_LINK_MAX(1L),
    _PC_MAX_CANON(2L),
    _PC_MAX_INPUT(3L),
    _PC_NAME_MAX(4L),
    _PC_PATH_MAX(5L),
    _PC_PIPE_BUF(6L),
    _PC_ALLOC_SIZE_MIN(10L),
    _PC_REC_INCR_XFER_SIZE(14L),
    _PC_REC_MAX_XFER_SIZE(15L),
    _PC_REC_MIN_XFER_SIZE(16L),
    _PC_REC_XFER_ALIGN(17L),
    _PC_SYMLINK_MAX(18L),
    _PC_CHOWN_RESTRICTED(7L),
    _PC_NO_TRUNC(8L),
    _PC_VDISABLE(9L),
    _PC_ASYNC_IO(53L),
    _PC_PRIO_IO(54L),
    _PC_SYNC_IO(55L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 55L;

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