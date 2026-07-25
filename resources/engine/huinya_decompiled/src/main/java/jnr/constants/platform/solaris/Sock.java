// исходный (обфусцированный) внутренний класс: jnr.constants.platform.solaris.Sock
package jnr.constants.platform.solaris;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.solaris.Sock_StringTable;

public enum Sock implements Constant {

    SOCK_STREAM(2L),
    SOCK_DGRAM(1L),
    SOCK_RAW(4L),
    SOCK_RDM(5L),
    SOCK_SEQPACKET(6L),
    SOCK_NONBLOCK(1048576L),
    SOCK_CLOEXEC(524288L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 1048576L;

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