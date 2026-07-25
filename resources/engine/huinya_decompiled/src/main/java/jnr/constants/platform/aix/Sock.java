// исходный (обфусцированный) внутренний класс: jnr.constants.platform.aix.Sock
package jnr.constants.platform.aix;

import jnr.constants.Constant;

public enum Sock implements Constant {

    SOCK_STREAM(1L),
    SOCK_DGRAM(2L),
    SOCK_RAW(3L),
    SOCK_RDM(4L),
    SOCK_SEQPACKET(5L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 5L;

  private Sock(long arg2) { // было: <init>
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