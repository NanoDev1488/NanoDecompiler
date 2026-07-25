// исходный (обфусцированный) внутренний класс: jnr.constants.platform.SocketMessage
package jnr.constants.platform;

import jnr.constants.Constant;
import jnr.constants.platform.ConstantResolver;

public enum SocketMessage implements Constant {

    MSG_DONTWAIT,
    MSG_OOB,
    MSG_PEEK,
    MSG_DONTROUTE,
    MSG_EOR,
    MSG_TRUNC,
    MSG_CTRUNC,
    MSG_WAITALL,
    MSG_PROXY,
    MSG_FIN,
    MSG_SYN,
    MSG_CONFIRM,
    MSG_RST,
    MSG_ERRQUEUE,
    MSG_NOSIGNAL,
    MSG_MORE,
    MSG_FASTOPEN,
    MSG_EOF,
    MSG_FLUSH,
    MSG_HOLD,
    MSG_SEND,
    MSG_HAVEMORE,
    MSG_RCVMORE,
    MSG_COMPAT,
    __UNKNOWN_CONSTANT__;

    // ---- поля ----
  private static final ConstantResolver resolver;

    static {
        resolver = ConstantResolver.getResolver(SocketMessage.class, 20000, 29999);
    }

  private SocketMessage() { // было: <init>
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

  public static SocketMessage valueOf(long arg0) {
        return ((SocketMessage) resolver.valueOf(arg0));
    }

}