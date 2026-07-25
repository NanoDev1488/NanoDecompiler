// исходный (обфусцированный) внутренний класс: jnr.constants.platform.darwin.Sock
package jnr.constants.platform.darwin;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.darwin.Sock_StringTable;

public enum Sock implements Constant {

    SOCK_STREAM(1L),
    SOCK_DGRAM(2L),
    SOCK_RAW(3L),
    SOCK_RDM(4L),
    SOCK_SEQPACKET(5L),
    SOCK_MAXADDRLEN(255L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 255L;

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