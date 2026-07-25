// исходный (обфусцированный) внутренний класс: jnr.constants.platform.fake.RLIMIT
package jnr.constants.platform.fake;

import jnr.constants.Constant;

public enum RLIMIT implements Constant {

    RLIMIT_AS(1L),
    RLIMIT_CORE(2L),
    RLIMIT_CPU(3L),
    RLIMIT_DATA(4L),
    RLIMIT_FSIZE(5L),
    RLIMIT_LOCKS(6L),
    RLIMIT_MEMLOCK(7L),
    RLIMIT_MSGQUEUE(8L),
    RLIMIT_NICE(9L),
    RLIMIT_NLIMITS(10L),
    RLIMIT_NOFILE(11L),
    RLIMIT_NPROC(12L),
    RLIMIT_OFILE(13L),
    RLIMIT_RSS(14L),
    RLIMIT_RTPRIO(15L),
    RLIMIT_RTTIME(16L),
    RLIMIT_SIGPENDING(17L),
    RLIMIT_STACK(18L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 18L;

  private RLIMIT(long arg2) { // было: <init>
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