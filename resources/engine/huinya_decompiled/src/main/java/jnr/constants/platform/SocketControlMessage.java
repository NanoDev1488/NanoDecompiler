// исходный (обфусцированный) внутренний класс: jnr.constants.platform.SocketControlMessage
package jnr.constants.platform;

import jnr.constants.Constant;
import jnr.constants.platform.ConstantResolver;

public enum SocketControlMessage implements Constant {

    SCM_RIGHTS,
    SCM_TIMESTAMP,
    SCM_TIMESTAMPNS,
    SCM_TIMESTAMPING,
    SCM_BINTIME,
    SCM_CREDENTIALS,
    SCM_CREDS,
    SCM_UCRED,
    SCM_WIFI_STATUS,
    __UNKNOWN_CONSTANT__;

    // ---- поля ----
  private static final ConstantResolver resolver;

    static {
        resolver = ConstantResolver.getResolver(SocketControlMessage.class, 20000, 29999);
    }

  private SocketControlMessage() { // было: <init>
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

  public static SocketControlMessage valueOf(long arg0) {
        return ((SocketControlMessage) resolver.valueOf(arg0));
    }

}