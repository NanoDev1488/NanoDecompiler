// исходный (обфусцированный) внутренний класс: jnr.constants.platform.fake.Confstr
package jnr.constants.platform.fake;

import jnr.constants.Constant;

public enum Confstr implements Constant {

    _CS_PATH(1L),
    _CS_POSIX_V7_ILP32_OFF32_CFLAGS(2L),
    _CS_POSIX_V7_ILP32_OFF32_LDFLAGS(3L),
    _CS_POSIX_V7_ILP32_OFF32_LIBS(4L),
    _CS_POSIX_V7_ILP32_OFFBIG_CFLAGS(5L),
    _CS_POSIX_V7_ILP32_OFFBIG_LDFLAGS(6L),
    _CS_POSIX_V7_ILP32_OFFBIG_LIBS(7L),
    _CS_POSIX_V7_LP64_OFF64_CFLAGS(8L),
    _CS_POSIX_V7_LP64_OFF64_LDFLAGS(9L),
    _CS_POSIX_V7_LP64_OFF64_LIBS(10L),
    _CS_POSIX_V7_LPBIG_OFFBIG_CFLAGS(11L),
    _CS_POSIX_V7_LPBIG_OFFBIG_LDFLAGS(12L),
    _CS_POSIX_V7_LPBIG_OFFBIG_LIBS(13L),
    _CS_POSIX_V7_WIDTH_RESTRICTED_ENVS(14L),
    _CS_V7_ENV(15L),
    _CS_POSIX_V6_ILP32_OFF32_CFLAGS(16L),
    _CS_POSIX_V6_ILP32_OFF32_LDFLAGS(17L),
    _CS_POSIX_V6_ILP32_OFF32_LIBS(18L),
    _CS_POSIX_V6_ILP32_OFFBIG_CFLAGS(19L),
    _CS_POSIX_V6_ILP32_OFFBIG_LDFLAGS(20L),
    _CS_POSIX_V6_ILP32_OFFBIG_LIBS(21L),
    _CS_POSIX_V6_LP64_OFF64_CFLAGS(22L),
    _CS_POSIX_V6_LP64_OFF64_LDFLAGS(23L),
    _CS_POSIX_V6_LP64_OFF64_LIBS(24L),
    _CS_POSIX_V6_LPBIG_OFFBIG_CFLAGS(25L),
    _CS_POSIX_V6_LPBIG_OFFBIG_LDFLAGS(26L),
    _CS_POSIX_V6_LPBIG_OFFBIG_LIBS(27L),
    _CS_POSIX_V6_WIDTH_RESTRICTED_ENVS(28L),
    _CS_V6_ENV(29L),
    _CS_GNU_LIBC_VERSION(30L),
    _CS_GNU_LIBPTHREAD_VERSION(31L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 31L;

  private Confstr(long arg2) { // было: <init>
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