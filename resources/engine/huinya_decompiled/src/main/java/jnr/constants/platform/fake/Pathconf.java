// исходный (обфусцированный) внутренний класс: jnr.constants.platform.fake.Pathconf
package jnr.constants.platform.fake;

import jnr.constants.Constant;

public enum Pathconf implements Constant {

    _PC_FILESIZEBITS(1L),
    _PC_LINK_MAX(2L),
    _PC_MAX_CANON(3L),
    _PC_MAX_INPUT(4L),
    _PC_NAME_MAX(5L),
    _PC_PATH_MAX(6L),
    _PC_PIPE_BUF(7L),
    _PC_2_SYMLINKS(8L),
    _PC_ALLOC_SIZE_MIN(9L),
    _PC_REC_INCR_XFER_SIZE(10L),
    _PC_REC_MAX_XFER_SIZE(11L),
    _PC_REC_MIN_XFER_SIZE(12L),
    _PC_REC_XFER_ALIGN(13L),
    _PC_SYMLINK_MAX(14L),
    _PC_CHOWN_RESTRICTED(15L),
    _PC_NO_TRUNC(16L),
    _PC_VDISABLE(17L),
    _PC_ASYNC_IO(18L),
    _PC_PRIO_IO(19L),
    _PC_SYNC_IO(20L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 20L;

  private Pathconf(long arg2) { // было: <init>
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