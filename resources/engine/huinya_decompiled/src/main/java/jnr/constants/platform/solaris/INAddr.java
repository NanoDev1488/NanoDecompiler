// исходный (обфусцированный) внутренний класс: jnr.constants.platform.solaris.INAddr
package jnr.constants.platform.solaris;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.solaris.INAddr_StringTable;

public enum INAddr implements Constant {

    INADDR_ANY(0L),
    INADDR_BROADCAST(4294967295L),
    INADDR_NONE(4294967295L),
    INADDR_LOOPBACK(2130706433L),
    INADDR_UNSPEC_GROUP(3758096384L),
    INADDR_ALLHOSTS_GROUP(3758096385L),
    INADDR_ALLRTRS_GROUP(3758096386L),
    INADDR_MAX_LOCAL_GROUP(3758096639L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 4294967295L;

  private INAddr(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) INAddr_StringTable.descriptions.get(this));
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