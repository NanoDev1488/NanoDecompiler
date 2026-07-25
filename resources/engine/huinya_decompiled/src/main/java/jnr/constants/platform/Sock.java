// исходный (обфусцированный) внутренний класс: jnr.constants.platform.Sock
package jnr.constants.platform;

import jnr.constants.Constant;
import jnr.constants.platform.ConstantResolver;

public enum Sock implements Constant {

    SOCK_STREAM,
    SOCK_DGRAM,
    SOCK_RAW,
    SOCK_RDM,
    SOCK_SEQPACKET,
    SOCK_NONBLOCK,
    SOCK_CLOEXEC,
    SOCK_MAXADDRLEN,
    __UNKNOWN_CONSTANT__;

    // ---- поля ----
  private static final ConstantResolver resolver;

    static {
        resolver = ConstantResolver.getResolver(Sock.class, 20000, 29999);
    }

  private Sock() { // было: <init>
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

  public static Sock valueOf(long arg0) {
        return ((Sock) resolver.valueOf(arg0));
    }

}