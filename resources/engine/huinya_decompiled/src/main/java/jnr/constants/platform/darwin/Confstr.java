// исходный (обфусцированный) внутренний класс: jnr.constants.platform.darwin.Confstr
package jnr.constants.platform.darwin;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.darwin.Confstr_StringTable;

public enum Confstr implements Constant {

    _CS_PATH(1L),
    _CS_POSIX_V6_ILP32_OFF32_CFLAGS(2L),
    _CS_POSIX_V6_ILP32_OFF32_LDFLAGS(3L),
    _CS_POSIX_V6_ILP32_OFF32_LIBS(4L),
    _CS_POSIX_V6_ILP32_OFFBIG_CFLAGS(5L),
    _CS_POSIX_V6_ILP32_OFFBIG_LDFLAGS(6L),
    _CS_POSIX_V6_ILP32_OFFBIG_LIBS(7L),
    _CS_POSIX_V6_LP64_OFF64_CFLAGS(8L),
    _CS_POSIX_V6_LP64_OFF64_LDFLAGS(9L),
    _CS_POSIX_V6_LP64_OFF64_LIBS(10L),
    _CS_POSIX_V6_LPBIG_OFFBIG_CFLAGS(11L),
    _CS_POSIX_V6_LPBIG_OFFBIG_LDFLAGS(12L),
    _CS_POSIX_V6_LPBIG_OFFBIG_LIBS(13L),
    _CS_POSIX_V6_WIDTH_RESTRICTED_ENVS(14L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 14L;

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