// исходный (обфусцированный) внутренний класс: jnr.constants.platform.freebsd.aarch64.TCP
package jnr.constants.platform.freebsd.aarch64;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.freebsd.aarch64.TCP_StringTable;

public enum TCP implements Constant {

    TCP_MAX_SACK(4L),
    TCP_MSS(536L),
    TCP_MINMSS(216L),
    TCP_MAXWIN(65535L),
    TCP_MAX_WINSHIFT(14L),
    TCP_MAXBURST(4L),
    TCP_MAXHLEN(60L),
    TCP_MAXOLEN(40L),
    TCP_NODELAY(1L),
    TCP_MAXSEG(2L),
    TCP_NOPUSH(4L),
    TCP_NOOPT(8L),
    TCP_INFO(32L),
    TCP_KEEPCNT(1024L),
    TCP_KEEPIDLE(256L),
    TCP_KEEPINTVL(512L),
    TCP_MD5SIG(16L),
    TCP_FASTOPEN(1025L),
    TCP_CONGESTION(64L);

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