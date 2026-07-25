// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.mips64el.Sock
package jnr.constants.platform.linux.mips64el;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.linux.mips64el.Sock_StringTable;

public enum Sock implements Constant {

    SOCK_STREAM(2L),
    SOCK_DGRAM(1L),
    SOCK_RAW(3L),
    SOCK_RDM(4L),
    SOCK_SEQPACKET(5L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 5L;

  private Sock(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) Sock_StringTable.descriptions.get(this));
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