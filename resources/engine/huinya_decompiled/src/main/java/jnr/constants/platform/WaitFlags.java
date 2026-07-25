// исходный (обфусцированный) внутренний класс: jnr.constants.platform.WaitFlags
package jnr.constants.platform;

import jnr.constants.Constant;
import jnr.constants.platform.ConstantResolver;

public enum WaitFlags implements Constant {

    WNOHANG,
    WUNTRACED,
    WSTOPPED,
    WEXITED,
    WCONTINUED,
    WNOWAIT,
    __UNKNOWN_CONSTANT__;

    // ---- поля ----
  private static final ConstantResolver resolver;

    static {
        resolver = ConstantResolver.getBitmaskResolver(WaitFlags.class);
    }

  private WaitFlags() { // было: <init>
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

  public static WaitFlags valueOf(long arg0) {
        return ((WaitFlags) resolver.valueOf(arg0));
    }

}