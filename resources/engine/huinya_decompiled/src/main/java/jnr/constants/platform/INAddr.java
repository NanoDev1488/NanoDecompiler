// исходный (обфусцированный) внутренний класс: jnr.constants.platform.INAddr
package jnr.constants.platform;

import jnr.constants.Constant;
import jnr.constants.platform.ConstantResolver;

public enum INAddr implements Constant {

    INADDR_ANY,
    INADDR_BROADCAST,
    INADDR_NONE,
    INADDR_LOOPBACK,
    INADDR_UNSPEC_GROUP,
    INADDR_ALLHOSTS_GROUP,
    INADDR_ALLRTRS_GROUP,
    INADDR_MAX_LOCAL_GROUP,
    __UNKNOWN_CONSTANT__;

    // ---- поля ----
  private static final ConstantResolver resolver;

    static {
        resolver = ConstantResolver.getResolver(INAddr.class, 20000, 29999);
    }

  private INAddr() { // было: <init>
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

  public static INAddr valueOf(long arg0) {
        return ((INAddr) resolver.valueOf(arg0));
    }

}