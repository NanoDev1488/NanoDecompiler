// исходный (обфусцированный) внутренний класс: jnr.constants.platform.SocketLevel
package jnr.constants.platform;

import jnr.constants.Constant;
import jnr.constants.platform.ConstantResolver;

public enum SocketLevel implements Constant {

    SOL_SOCKET,
    SOL_IP,
    SOL_TCP,
    SOL_UDP,
    SOL_IPV6,
    __UNKNOWN_CONSTANT__;

    // ---- поля ----
  private static final ConstantResolver resolver;

    static {
        resolver = ConstantResolver.getResolver(SocketLevel.class, 20000, 29999);
    }

  private SocketLevel() { // было: <init>
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

  public static SocketLevel valueOf(long arg0) {
        return ((SocketLevel) resolver.valueOf(arg0));
    }

}