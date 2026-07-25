// исходный (обфусцированный) внутренний класс: jnr.constants.platform.PRIO
package jnr.constants.platform;

import jnr.constants.Constant;
import jnr.constants.platform.ConstantResolver;

public enum PRIO implements Constant {

    PRIO_MIN,
    PRIO_PROCESS,
    PRIO_PGRP,
    PRIO_USER,
    PRIO_MAX,
    __UNKNOWN_CONSTANT__;

    // ---- поля ----
  private static final ConstantResolver resolver;

    static {
        resolver = ConstantResolver.getResolver(PRIO.class, 20000, 29999);
    }

  private PRIO() { // было: <init>
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

  public static PRIO valueOf(long arg0) {
        return ((PRIO) resolver.valueOf(arg0));
    }

}