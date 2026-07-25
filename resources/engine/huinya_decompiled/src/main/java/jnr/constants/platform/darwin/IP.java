// исходный (обфусцированный) внутренний класс: jnr.constants.platform.darwin.IP
package jnr.constants.platform.darwin;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.darwin.IP_StringTable;

public enum IP implements Constant {

    IP_OPTIONS(1L),
    IP_HDRINCL(2L),
    IP_TOS(3L),
    IP_TTL(4L),
    IP_RECVOPTS(5L),
    IP_RECVRETOPTS(6L),
    IP_RECVDSTADDR(7L),
    IP_RETOPTS(8L),
    IP_RECVTTL(24L),
    IP_RECVIF(20L),
    IP_PORTRANGE(19L),
    IP_MULTICAST_IF(9L),
    IP_MULTICAST_TTL(10L),
    IP_MULTICAST_LOOP(11L),
    IP_ADD_MEMBERSHIP(12L),
    IP_DROP_MEMBERSHIP(13L),
    IP_DEFAULT_MULTICAST_TTL(1L),
    IP_DEFAULT_MULTICAST_LOOP(1L),
    IP_MAX_MEMBERSHIPS(4095L),
    IP_PKTINFO(26L),
    IP_RECVTOS(27L),
    IP_IPSEC_POLICY(21L),
    IP_UNBLOCK_SOURCE(73L),
    IP_BLOCK_SOURCE(72L),
    IP_ADD_SOURCE_MEMBERSHIP(70L),
    IP_DROP_SOURCE_MEMBERSHIP(71L),
    IP_MSFILTER(74L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 4095L;

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