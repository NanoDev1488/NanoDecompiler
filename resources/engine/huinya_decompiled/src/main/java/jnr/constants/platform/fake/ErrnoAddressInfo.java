// исходный (обфусцированный) внутренний класс: jnr.constants.platform.fake.ErrnoAddressInfo
package jnr.constants.platform.fake;

import jnr.constants.Constant;

public enum ErrnoAddressInfo implements Constant {

    EAI_ADDRFAMILY(1L),
    EAI_AGAIN(2L),
    EAI_BADFLAGS(3L),
    EAI_FAIL(4L),
    EAI_FAMILY(5L),
    EAI_MEMORY(6L),
    EAI_NODATA(7L),
    EAI_NONAME(8L),
    EAI_OVERFLOW(9L),
    EAI_SERVICE(10L),
    EAI_SOCKTYPE(11L),
    EAI_SYSTEM(12L),
    EAI_BADHINTS(13L),
    EAI_PROTOCOL(14L),
    EAI_MAX(15L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 15L;

  private ErrnoAddressInfo(long arg2) { // было: <init>
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