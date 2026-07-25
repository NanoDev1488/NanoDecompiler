// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.s390x.TCP
package jnr.constants.platform.linux.s390x;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.linux.s390x.TCP_StringTable;

public enum TCP implements Constant {

    TCP_MSS(512L),
    TCP_MAXWIN(65535L),
    TCP_MAX_WINSHIFT(14L),
    TCP_NODELAY(1L),
    TCP_MAXSEG(2L),
    TCP_CORK(3L),
    TCP_DEFER_ACCEPT(9L),
    TCP_INFO(11L),
    TCP_KEEPCNT(6L),
    TCP_KEEPIDLE(4L),
    TCP_KEEPINTVL(5L),
    TCP_LINGER2(8L),
    TCP_MD5SIG(14L),
    TCP_QUICKACK(12L),
    TCP_SYNCNT(7L),
    TCP_WINDOW_CLAMP(10L),
    TCP_FASTOPEN(23L),
    TCP_CONGESTION(13L),
    TCP_COOKIE_TRANSACTIONS(15L),
    TCP_QUEUE_SEQ(21L),
    TCP_REPAIR(19L),
    TCP_REPAIR_OPTIONS(22L),
    TCP_REPAIR_QUEUE(20L),
    TCP_THIN_DUPACK(17L),
    TCP_THIN_LINEAR_TIMEOUTS(16L),
    TCP_TIMESTAMP(24L),
    TCP_USER_TIMEOUT(18L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 65535L;

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