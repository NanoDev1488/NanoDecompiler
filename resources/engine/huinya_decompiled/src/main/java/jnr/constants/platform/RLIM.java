// исходный (обфусцированный) внутренний класс: jnr.constants.platform.RLIM
package jnr.constants.platform;

import jnr.constants.Constant;
import jnr.constants.platform.ConstantResolver;

public enum RLIM implements Constant {

    RLIM_NLIMITS,
    RLIM_INFINITY,
    RLIM_SAVED_MAX,
    RLIM_SAVED_CUR,
    __UNKNOWN_CONSTANT__;

    // ---- поля ----
  private static final ConstantResolver resolver;

    static {
        resolver = ConstantResolver.getResolver(RLIM.class, 20000, 29999);
    }

  private RLIM() { // было: <init>
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

  public static RLIM valueOf(long arg0) {
        return ((RLIM) resolver.valueOf(arg0));
    }

}