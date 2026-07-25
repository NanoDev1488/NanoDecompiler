// исходный (обфусцированный) внутренний класс: jnr.constants.platform.windows.ErrnoAddressInfo
package jnr.constants.platform.windows;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.windows.ErrnoAddressInfo_StringTable;

public enum ErrnoAddressInfo implements Constant {

    EAI_AGAIN(11002L),
    EAI_BADFLAGS(10022L),
    EAI_FAIL(11003L),
    EAI_FAMILY(10047L),
    EAI_MEMORY(8L),
    EAI_NODATA(11004L),
    EAI_NONAME(11001L),
    EAI_SERVICE(10109L),
    EAI_SOCKTYPE(10044L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 8L;
  public static final long MAX_VALUE = 11004L;

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