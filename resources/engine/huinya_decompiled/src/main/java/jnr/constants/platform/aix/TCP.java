// исходный (обфусцированный) внутренний класс: jnr.constants.platform.aix.TCP
package jnr.constants.platform.aix;

import jnr.constants.Constant;

public enum TCP implements Constant {

    TCP_MAX_SACK(4L),
    TCP_MSS(1460L),
    TCP_MAXWIN(65535L),
    TCP_MAXBURST(8L),
    TCP_NODELAY(1L),
    TCP_MAXSEG(2L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 65535L;

  private TCP(long arg2) { // было: <init>
        value = arg2;
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