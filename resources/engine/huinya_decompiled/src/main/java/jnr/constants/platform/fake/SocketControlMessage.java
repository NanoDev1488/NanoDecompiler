// исходный (обфусцированный) внутренний класс: jnr.constants.platform.fake.SocketControlMessage
package jnr.constants.platform.fake;

import jnr.constants.Constant;

public enum SocketControlMessage implements Constant {

    SCM_RIGHTS(1L),
    SCM_TIMESTAMP(2L),
    SCM_TIMESTAMPNS(3L),
    SCM_TIMESTAMPING(4L),
    SCM_BINTIME(5L),
    SCM_CREDENTIALS(6L),
    SCM_CREDS(7L),
    SCM_UCRED(8L),
    SCM_WIFI_STATUS(9L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 9L;

  private SocketControlMessage(long arg2) { // было: <init>
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