// исходный (обфусцированный) внутренний класс: jnr.constants.platform.NameInfo
package jnr.constants.platform;

import jnr.constants.Constant;
import jnr.constants.platform.ConstantResolver;

public enum NameInfo implements Constant {

    NI_MAXHOST,
    NI_MAXSERV,
    NI_NOFQDN,
    NI_NUMERICHOST,
    NI_NAMEREQD,
    NI_NUMERICSERV,
    NI_DGRAM,
    NI_WITHSCOPEID,
    __UNKNOWN_CONSTANT__;

    // ---- поля ----
  private static final ConstantResolver resolver;

    static {
        resolver = ConstantResolver.getResolver(NameInfo.class, 20000, 29999);
    }

  private NameInfo() { // было: <init>
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

  public static NameInfo valueOf(long arg0) {
        return ((NameInfo) resolver.valueOf(arg0));
    }

}