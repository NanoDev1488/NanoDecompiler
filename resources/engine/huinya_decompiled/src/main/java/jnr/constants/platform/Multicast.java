// исходный (обфусцированный) внутренний класс: jnr.constants.platform.Multicast
package jnr.constants.platform;

import jnr.constants.Constant;
import jnr.constants.platform.ConstantResolver;

public enum Multicast implements Constant {

    MCAST_JOIN_GROUP,
    MCAST_BLOCK_SOURCE,
    MCAST_UNBLOCK_SOURCE,
    MCAST_LEAVE_GROUP,
    MCAST_JOIN_SOURCE_GROUP,
    MCAST_LEAVE_SOURCE_GROUP,
    MCAST_MSFILTER,
    MCAST_EXCLUDE,
    MCAST_INCLUDE,
    __UNKNOWN_CONSTANT__;

    // ---- поля ----
  private static final ConstantResolver resolver;

    static {
        resolver = ConstantResolver.getResolver(Multicast.class, 20000, 29999);
    }

  private Multicast() { // было: <init>
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

  public static Multicast valueOf(long arg0) {
        return ((Multicast) resolver.valueOf(arg0));
    }

}