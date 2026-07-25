// исходный (обфусцированный) внутренний класс: jnr.constants.platform.Access
package jnr.constants.platform;

import jnr.constants.Constant;
import jnr.constants.platform.ConstantResolver;

public enum Access implements Constant {

    F_OK,
    X_OK,
    W_OK,
    R_OK,
    __UNKNOWN_CONSTANT__;

    // ---- поля ----
  private static final ConstantResolver resolver;

    static {
        resolver = ConstantResolver.getBitmaskResolver(Access.class);
    }

  private Access() { // было: <init>
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

  public static Access valueOf(long arg0) {
        return ((Access) resolver.valueOf(arg0));
    }

}