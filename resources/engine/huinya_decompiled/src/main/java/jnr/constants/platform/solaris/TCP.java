// исходный (обфусцированный) внутренний класс: jnr.constants.platform.solaris.TCP
package jnr.constants.platform.solaris;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.solaris.TCP_StringTable;

public enum TCP implements Constant {

    TCP_MSS(536L),
    TCP_NODELAY(1L),
    TCP_MAXSEG(2L),
    TCP_KEEPALIVE(8L),
    TCP_CORK(24L),
    TCP_INFO(34L),
    TCP_KEEPCNT(31L),
    TCP_KEEPIDLE(29L),
    TCP_KEEPINTVL(30L),
    TCP_LINGER2(28L),
    TCP_MD5SIG(36L),
    TCP_CONGESTION(35L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 536L;

  private TCP(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) TCP_StringTable.descriptions.get(this));
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