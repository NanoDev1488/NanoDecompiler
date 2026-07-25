// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.aarch64.Confstr
package jnr.constants.platform.linux.aarch64;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.linux.aarch64.Confstr_StringTable;

public enum Confstr implements Constant {

    _CS_PATH(0L),
    _CS_POSIX_V7_ILP32_OFF32_CFLAGS(1132L),
    _CS_POSIX_V7_ILP32_OFF32_LDFLAGS(1133L),
    _CS_POSIX_V7_ILP32_OFF32_LIBS(1134L),
    _CS_POSIX_V7_ILP32_OFFBIG_CFLAGS(1136L),
    _CS_POSIX_V7_ILP32_OFFBIG_LDFLAGS(1137L),
    _CS_POSIX_V7_ILP32_OFFBIG_LIBS(1138L),
    _CS_POSIX_V7_LP64_OFF64_CFLAGS(1140L),
    _CS_POSIX_V7_LP64_OFF64_LDFLAGS(1141L),
    _CS_POSIX_V7_LP64_OFF64_LIBS(1142L),
    _CS_POSIX_V7_LPBIG_OFFBIG_CFLAGS(1144L),
    _CS_POSIX_V7_LPBIG_OFFBIG_LDFLAGS(1145L),
    _CS_POSIX_V7_LPBIG_OFFBIG_LIBS(1146L),
    _CS_POSIX_V7_WIDTH_RESTRICTED_ENVS(5L),
    _CS_V7_ENV(1149L),
    _CS_POSIX_V6_ILP32_OFF32_CFLAGS(1116L),
    _CS_POSIX_V6_ILP32_OFF32_LDFLAGS(1117L),
    _CS_POSIX_V6_ILP32_OFF32_LIBS(1118L),
    _CS_POSIX_V6_ILP32_OFFBIG_CFLAGS(1120L),
    _CS_POSIX_V6_ILP32_OFFBIG_LDFLAGS(1121L),
    _CS_POSIX_V6_ILP32_OFFBIG_LIBS(1122L),
    _CS_POSIX_V6_LP64_OFF64_CFLAGS(1124L),
    _CS_POSIX_V6_LP64_OFF64_LDFLAGS(1125L),
    _CS_POSIX_V6_LP64_OFF64_LIBS(1126L),
    _CS_POSIX_V6_LPBIG_OFFBIG_CFLAGS(1128L),
    _CS_POSIX_V6_LPBIG_OFFBIG_LDFLAGS(1129L),
    _CS_POSIX_V6_LPBIG_OFFBIG_LIBS(1130L),
    _CS_POSIX_V6_WIDTH_RESTRICTED_ENVS(1L),
    _CS_V6_ENV(1148L),
    _CS_GNU_LIBC_VERSION(2L),
    _CS_GNU_LIBPTHREAD_VERSION(3L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 1149L;

  private Confstr(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) Confstr_StringTable.descriptions.get(this));
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