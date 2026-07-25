// исходный (обфусцированный) внутренний класс: jnr.constants.platform.fake.TCP
package jnr.constants.platform.fake;

import jnr.constants.Constant;

public enum TCP implements Constant {

    TCP_MAX_SACK(1L),
    TCP_MSS(2L),
    TCP_MINMSS(3L),
    TCP_MINMSSOVERLOAD(4L),
    TCP_MAXWIN(5L),
    TCP_MAX_WINSHIFT(6L),
    TCP_MAXBURST(7L),
    TCP_MAXHLEN(8L),
    TCP_MAXOLEN(9L),
    TCP_NODELAY(10L),
    TCP_MAXSEG(11L),
    TCP_NOPUSH(12L),
    TCP_NOOPT(13L),
    TCP_KEEPALIVE(14L),
    TCP_NSTATES(15L),
    TCP_RETRANSHZ(16L),
    TCP_CORK(17L),
    TCP_DEFER_ACCEPT(18L),
    TCP_INFO(19L),
    TCP_KEEPCNT(20L),
    TCP_KEEPIDLE(21L),
    TCP_KEEPINTVL(22L),
    TCP_LINGER2(23L),
    TCP_MD5SIG(24L),
    TCP_QUICKACK(25L),
    TCP_SYNCNT(26L),
    TCP_WINDOW_CLAMP(27L),
    TCP_FASTOPEN(28L),
    TCP_CONGESTION(29L),
    TCP_COOKIE_TRANSACTIONS(30L),
    TCP_QUEUE_SEQ(31L),
    TCP_REPAIR(32L),
    TCP_REPAIR_OPTIONS(33L),
    TCP_REPAIR_QUEUE(34L),
    TCP_THIN_DUPACK(35L),
    TCP_THIN_LINEAR_TIMEOUTS(36L),
    TCP_TIMESTAMP(37L),
    TCP_USER_TIMEOUT(38L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 38L;

  private TCP(long arg2) { // было: <init>
        value = arg2;
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