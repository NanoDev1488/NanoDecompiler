// исходный (обфусцированный) внутренний класс: jnr.constants.platform.solaris.IP
package jnr.constants.platform.solaris;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.solaris.IP_StringTable;

public enum IP implements Constant {

    IP_OPTIONS(1L),
    IP_HDRINCL(2L),
    IP_TOS(3L),
    IP_TTL(4L),
    IP_RECVOPTS(5L),
    IP_RECVRETOPTS(6L),
    IP_RECVDSTADDR(7L),
    IP_RETOPTS(8L),
    IP_DONTFRAG(27L),
    IP_RECVTTL(11L),
    IP_RECVIF(9L),
    IP_RECVSLLA(10L),
    IP_MULTICAST_IF(16L),
    IP_MULTICAST_TTL(17L),
    IP_MULTICAST_LOOP(18L),
    IP_ADD_MEMBERSHIP(19L),
    IP_DROP_MEMBERSHIP(20L),
    IP_DEFAULT_MULTICAST_TTL(1L),
    IP_DEFAULT_MULTICAST_LOOP(1L),
    IP_PKTINFO(26L),
    IP_UNBLOCK_SOURCE(22L),
    IP_BLOCK_SOURCE(21L),
    IP_ADD_SOURCE_MEMBERSHIP(23L),
    IP_DROP_SOURCE_MEMBERSHIP(24L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 27L;

  private IP(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) IP_StringTable.descriptions.get(this));
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