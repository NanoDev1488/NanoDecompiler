// исходный (обфусцированный) внутренний класс: jnr.constants.platform.fake.Local
package jnr.constants.platform.fake;

import jnr.constants.Constant;

public enum Local implements Constant {

    LOCAL_PEERCRED(1L),
    LOCAL_CREDS(2L),
    LOCAL_CONNWAIT(3L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 3L;

  private Local(long arg2) { // было: <init>
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