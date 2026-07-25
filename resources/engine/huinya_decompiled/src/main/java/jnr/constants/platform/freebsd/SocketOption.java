// исходный (обфусцированный) внутренний класс: jnr.constants.platform.freebsd.SocketOption
package jnr.constants.platform.freebsd;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.freebsd.SocketOption_StringTable;

public enum SocketOption implements Constant {

    SO_DEBUG(1L),
    SO_ACCEPTCONN(2L),
    SO_REUSEADDR(4L),
    SO_KEEPALIVE(8L),
    SO_DONTROUTE(16L),
    SO_BROADCAST(32L),
    SO_USELOOPBACK(64L),
    SO_LINGER(128L),
    SO_OOBINLINE(256L),
    SO_REUSEPORT(512L),
    SO_TIMESTAMP(1024L),
    SO_ACCEPTFILTER(4096L),
    SO_SNDBUF(4097L),
    SO_RCVBUF(4098L),
    SO_SNDLOWAT(4099L),
    SO_RCVLOWAT(4100L),
    SO_SNDTIMEO(4101L),
    SO_RCVTIMEO(4102L),
    SO_ERROR(4103L),
    SO_TYPE(4104L),
    SO_NOSIGPIPE(2048L),
    SO_LABEL(4105L),
    SO_PEERLABEL(4112L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 4112L;

  private SocketOption(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) SocketOption_StringTable.descriptions.get(this));
    }

  public final int value() {
        return ((int) value);
    }

  public final int intValue() {
        return ((int) value);
    }

  public final long longValue() {
        return value;
    }

  public final boolean defined() {
        return true;
    }

}