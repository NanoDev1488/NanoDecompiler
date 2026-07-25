// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.powerpc64.ErrnoAddressInfo
package jnr.constants.platform.linux.powerpc64;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.linux.powerpc64.ErrnoAddressInfo_StringTable;

public enum ErrnoAddressInfo implements Constant {

    EAI_ADDRFAMILY(-9L),
    EAI_AGAIN(-3L),
    EAI_BADFLAGS(-1L),
    EAI_FAIL(-4L),
    EAI_FAMILY(-6L),
    EAI_MEMORY(-10L),
    EAI_NODATA(-5L),
    EAI_NONAME(-2L),
    EAI_OVERFLOW(-12L),
    EAI_SERVICE(-8L),
    EAI_SOCKTYPE(-7L),
    EAI_SYSTEM(-11L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = -12L;
  public static final long MAX_VALUE = -1L;

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