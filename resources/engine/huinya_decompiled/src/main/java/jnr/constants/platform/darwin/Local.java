// исходный (обфусцированный) внутренний класс: jnr.constants.platform.darwin.Local
package jnr.constants.platform.darwin;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.darwin.Local_StringTable;

public enum Local implements Constant {

    LOCAL_PEERCRED(1L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 1L;

  private Local(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) Local_StringTable.descriptions.get(this));
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