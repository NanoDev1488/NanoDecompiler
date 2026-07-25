// исходный (обфусцированный) внутренний класс: jnr.constants.platform.darwin.ErrnoAddressInfo
package jnr.constants.platform.darwin;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.darwin.ErrnoAddressInfo_StringTable;

public enum ErrnoAddressInfo implements Constant {

    EAI_ADDRFAMILY(1L),
    EAI_AGAIN(2L),
    EAI_BADFLAGS(3L),
    EAI_FAIL(4L),
    EAI_FAMILY(5L),
    EAI_MEMORY(6L),
    EAI_NODATA(7L),
    EAI_NONAME(8L),
    EAI_OVERFLOW(14L),
    EAI_SERVICE(9L),
    EAI_SOCKTYPE(10L),
    EAI_SYSTEM(11L),
    EAI_BADHINTS(12L),
    EAI_PROTOCOL(13L),
    EAI_MAX(15L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 15L;

  private ErrnoAddressInfo(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) ErrnoAddressInfo_StringTable.descriptions.get(this));
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