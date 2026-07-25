// исходный (обфусцированный) внутренний класс: jnr.constants.platform.openbsd.TCP
package jnr.constants.platform.openbsd;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.openbsd.TCP_StringTable;

public enum TCP implements Constant {

    TCP_MAX_SACK(3L),
    TCP_MSS(512L),
    TCP_MAXWIN(65535L),
    TCP_MAX_WINSHIFT(14L),
    TCP_MAXBURST(4L),
    TCP_NODELAY(1L),
    TCP_MAXSEG(2L),
    TCP_NOPUSH(16L),
    TCP_MD5SIG(4L);

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