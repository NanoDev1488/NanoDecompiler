// исходный (обфусцированный) внутренний класс: jnr.constants.platform.Local
package jnr.constants.platform;

import jnr.constants.Constant;
import jnr.constants.platform.ConstantResolver;

public enum Local implements Constant {

    LOCAL_PEERCRED,
    LOCAL_CREDS,
    LOCAL_CONNWAIT,
    __UNKNOWN_CONSTANT__;

    // ---- поля ----
  private static final ConstantResolver resolver;

    static {
        resolver = ConstantResolver.getResolver(Local.class, 20000, 29999);
    }

  private Local() { // было: <init>
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

  public static Local valueOf(long arg0) {
        return ((Local) resolver.valueOf(arg0));
    }

}