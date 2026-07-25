// исходный (обфусцированный) внутренний класс: jnr.constants.platform.freebsd.aarch64.INet6
package jnr.constants.platform.freebsd.aarch64;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.freebsd.aarch64.INet6_StringTable;

public enum INet6 implements Constant {

    INET6_ADDRSTRLEN(46L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 46L;
  public static final long MAX_VALUE = 46L;

  private INet6(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) INet6_StringTable.descriptions.get(this));
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