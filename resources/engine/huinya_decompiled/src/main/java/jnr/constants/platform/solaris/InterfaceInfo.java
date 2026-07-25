// исходный (обфусцированный) внутренний класс: jnr.constants.platform.solaris.InterfaceInfo
package jnr.constants.platform.solaris;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.solaris.InterfaceInfo_StringTable;

public enum InterfaceInfo implements Constant {

    IFF_ALLMULTI(512L),
    IFF_BROADCAST(2L),
    IFF_DEBUG(4L),
    IFF_LOOPBACK(8L),
    IFF_MULTICAST(2048L),
    IFF_NOARP(128L),
    IFF_NOTRAILERS(32L),
    IFF_POINTOPOINT(16L),
    IFF_PROMISC(256L),
    IFF_RUNNING(64L),
    IFF_UP(1L),
    IFF_CANTCHANGE(60413060332378L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 60413060332378L;

  private InterfaceInfo(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) InterfaceInfo_StringTable.descriptions.get(this));
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