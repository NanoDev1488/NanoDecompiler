// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.powerpc64.Shutdown
package jnr.constants.platform.linux.powerpc64;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.linux.powerpc64.Shutdown_StringTable;

public enum Shutdown implements Constant {

    SHUT_RD(0L),
    SHUT_WR(1L),
    SHUT_RDWR(2L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 2L;

  private Shutdown(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) Shutdown_StringTable.descriptions.get(this));
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