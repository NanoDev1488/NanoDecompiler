// исходный (обфусцированный) внутренний класс: jnr.constants.platform.LangInfo
package jnr.constants.platform;

import jnr.constants.Constant;
import jnr.constants.platform.ConstantResolver;

public enum LangInfo implements Constant {

    CODESET,
    D_T_FMT,
    D_FMT,
    T_FMT,
    DAY_1,
    DAY_2,
    DAY_3,
    DAY_4,
    DAY_5,
    DAY_6,
    DAY_7,
    ABDAY_1,
    ABDAY_2,
    ABDAY_3,
    ABDAY_4,
    ABDAY_5,
    ABDAY_6,
    ABDAY_7,
    MON_1,
    MON_2,
    MON_3,
    MON_4,
    MON_5,
    MON_6,
    MON_7,
    MON_8,
    MON_9,
    MON_10,
    MON_11,
    MON_12,
    ABMON_1,
    ABMON_2,
    ABMON_3,
    ABMON_4,
    ABMON_5,
    ABMON_6,
    ABMON_7,
    ABMON_8,
    ABMON_9,
    ABMON_10,
    ABMON_11,
    ABMON_12,
    RADIXCHAR,
    THOUSEP,
    YESEXPR,
    NOEXPR,
    CRNCYSTR,
    __UNKNOWN_CONSTANT__;

    // ---- поля ----
  private static final ConstantResolver resolver;

    static {
        resolver = ConstantResolver.getResolver(LangInfo.class, 20000, 29999);
    }

  private LangInfo() { // было: <init>
        // (пустое тело)
    }

  public final int value() {
        return ((int) resolver.longValue(this));
    }

  public final int intValue() {
        return ((int) resolver.longValue(this));
    }

  public final long longValue() {
        return resolver.longValue(this);
    }

  public final String description() {
        return resolver.description(this);
    }

  public final boolean defined() {
        return resolver.defined(this);
    }

  public final String toString() {
        return description();
    }

  public static LangInfo valueOf(long arg0) {
        return ((LangInfo) resolver.valueOf(arg0));
    }

}