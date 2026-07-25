// исходный (обфусцированный) внутренний класс: jnr.constants.platform.darwin.INet
package jnr.constants.platform.darwin;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.darwin.INet_StringTable;

public enum INet implements Constant {

    INET_ADDRSTRLEN(16L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 16L;
  public static final long MAX_VALUE = 16L;

  private INet(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) INet_StringTable.descriptions.get(this));
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