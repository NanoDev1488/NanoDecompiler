// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.mips64el.RLIMIT
package jnr.constants.platform.linux.mips64el;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.linux.mips64el.RLIMIT_StringTable;

public enum RLIMIT implements Constant {

    RLIMIT_AS(6L),
    RLIMIT_CORE(4L),
    RLIMIT_CPU(0L),
    RLIMIT_DATA(2L),
    RLIMIT_FSIZE(1L),
    RLIMIT_LOCKS(10L),
    RLIMIT_MEMLOCK(9L),
    RLIMIT_MSGQUEUE(12L),
    RLIMIT_NICE(13L),
    RLIMIT_NLIMITS(16L),
    RLIMIT_NOFILE(5L),
    RLIMIT_NPROC(8L),
    RLIMIT_OFILE(5L),
    RLIMIT_RSS(7L),
    RLIMIT_RTPRIO(14L),
    RLIMIT_RTTIME(15L),
    RLIMIT_SIGPENDING(11L),
    RLIMIT_STACK(3L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 16L;

  private RLIMIT(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) RLIMIT_StringTable.descriptions.get(this));
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