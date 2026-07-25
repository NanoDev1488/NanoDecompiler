// исходный (обфусцированный) внутренний класс: jnr.constants.platform.fake.LangInfo
package jnr.constants.platform.fake;

import jnr.constants.Constant;

public enum LangInfo implements Constant {

    CODESET(1L),
    D_T_FMT(2L),
    D_FMT(3L),
    T_FMT(4L),
    DAY_1(5L),
    DAY_2(6L),
    DAY_3(7L),
    DAY_4(8L),
    DAY_5(9L),
    DAY_6(10L),
    DAY_7(11L),
    ABDAY_1(12L),
    ABDAY_2(13L),
    ABDAY_3(14L),
    ABDAY_4(15L),
    ABDAY_5(16L),
    ABDAY_6(17L),
    ABDAY_7(18L),
    MON_1(19L),
    MON_2(20L),
    MON_3(21L),
    MON_4(22L),
    MON_5(23L),
    MON_6(24L),
    MON_7(25L),
    MON_8(26L),
    MON_9(27L),
    MON_10(28L),
    MON_11(29L),
    MON_12(30L),
    ABMON_1(31L),
    ABMON_2(32L),
    ABMON_3(33L),
    ABMON_4(34L),
    ABMON_5(35L),
    ABMON_6(36L),
    ABMON_7(37L),
    ABMON_8(38L),
    ABMON_9(39L),
    ABMON_10(40L),
    ABMON_11(41L),
    ABMON_12(42L),
    RADIXCHAR(43L),
    THOUSEP(44L),
    YESEXPR(45L),
    NOEXPR(46L),
    CRNCYSTR(47L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 47L;

  private LangInfo(long arg2) { // было: <init>
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