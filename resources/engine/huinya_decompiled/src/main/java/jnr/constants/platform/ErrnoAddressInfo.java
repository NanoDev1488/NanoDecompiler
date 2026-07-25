// исходный (обфусцированный) внутренний класс: jnr.constants.platform.ErrnoAddressInfo
package jnr.constants.platform;

import jnr.constants.Constant;
import jnr.constants.platform.ConstantResolver;

public enum ErrnoAddressInfo implements Constant {

    EAI_ADDRFAMILY,
    EAI_AGAIN,
    EAI_BADFLAGS,
    EAI_FAIL,
    EAI_FAMILY,
    EAI_MEMORY,
    EAI_NODATA,
    EAI_NONAME,
    EAI_OVERFLOW,
    EAI_SERVICE,
    EAI_SOCKTYPE,
    EAI_SYSTEM,
    EAI_BADHINTS,
    EAI_PROTOCOL,
    EAI_MAX,
    __UNKNOWN_CONSTANT__;

    // ---- поля ----
  private static final ConstantResolver resolver;

    static {
        resolver = ConstantResolver.getResolver(ErrnoAddressInfo.class, 20000, 29999);
    }

  private ErrnoAddressInfo() { // было: <init>
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

  public static ErrnoAddressInfo valueOf(long arg0) {
        return ((ErrnoAddressInfo) resolver.valueOf(arg0));
    }

}