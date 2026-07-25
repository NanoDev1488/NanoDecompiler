// исходный (обфусцированный) внутренний класс: jnr.constants.platform.AddressInfo
package jnr.constants.platform;

import jnr.constants.Constant;
import jnr.constants.platform.ConstantResolver;

public enum AddressInfo implements Constant {

    AI_PASSIVE,
    AI_CANONNAME,
    AI_NUMERICHOST,
    AI_NUMERICSERV,
    AI_MASK,
    AI_ALL,
    AI_V4MAPPED_CFG,
    AI_ADDRCONFIG,
    AI_V4MAPPED,
    AI_DEFAULT,
    __UNKNOWN_CONSTANT__;

    // ---- поля ----
  private static final ConstantResolver resolver;

    static {
        resolver = ConstantResolver.getResolver(AddressInfo.class, 20000, 29999);
    }

  private AddressInfo() { // было: <init>
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

  public static AddressInfo valueOf(long arg0) {
        return ((AddressInfo) resolver.valueOf(arg0));
    }

}