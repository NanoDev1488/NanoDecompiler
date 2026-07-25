// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.loongarch64.SocketOption
package jnr.constants.platform.linux.loongarch64;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.linux.loongarch64.SocketOption_StringTable;

public enum SocketOption implements Constant {

    SO_DEBUG(1L),
    SO_ACCEPTCONN(30L),
    SO_REUSEADDR(2L),
    SO_KEEPALIVE(9L),
    SO_DONTROUTE(5L),
    SO_BROADCAST(6L),
    SO_LINGER(13L),
    SO_OOBINLINE(10L),
    SO_REUSEPORT(15L),
    SO_TIMESTAMP(29L),
    SO_SNDBUF(7L),
    SO_RCVBUF(8L),
    SO_SNDLOWAT(19L),
    SO_RCVLOWAT(18L),
    SO_SNDTIMEO(21L),
    SO_RCVTIMEO(20L),
    SO_ERROR(4L),
    SO_TYPE(3L),
    SO_ATTACH_FILTER(26L),
    SO_BINDTODEVICE(25L),
    SO_DETACH_FILTER(27L),
    SO_NO_CHECK(11L),
    SO_PASSCRED(16L),
    SO_PEERCRED(17L),
    SO_PEERNAME(28L),
    SO_PRIORITY(12L),
    SO_SECURITY_AUTHENTICATION(22L),
    SO_SECURITY_ENCRYPTION_NETWORK(24L),
    SO_SECURITY_ENCRYPTION_TRANSPORT(23L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 30L;

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