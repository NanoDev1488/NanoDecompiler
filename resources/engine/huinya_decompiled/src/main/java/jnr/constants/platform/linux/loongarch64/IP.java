// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.loongarch64.IP
package jnr.constants.platform.linux.loongarch64;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.linux.loongarch64.IP_StringTable;

public enum IP implements Constant {

    IP_OPTIONS(4L),
    IP_HDRINCL(3L),
    IP_TOS(1L),
    IP_TTL(2L),
    IP_RECVOPTS(6L),
    IP_RECVRETOPTS(7L),
    IP_RETOPTS(7L),
    IP_MINTTL(21L),
    IP_RECVTTL(12L),
    IP_MULTICAST_IF(32L),
    IP_MULTICAST_TTL(33L),
    IP_MULTICAST_LOOP(34L),
    IP_ADD_MEMBERSHIP(35L),
    IP_DROP_MEMBERSHIP(36L),
    IP_DEFAULT_MULTICAST_TTL(1L),
    IP_DEFAULT_MULTICAST_LOOP(1L),
    IP_MAX_MEMBERSHIPS(20L),
    IP_ROUTER_ALERT(5L),
    IP_PKTINFO(8L),
    IP_PKTOPTIONS(9L),
    IP_MTU_DISCOVER(10L),
    IP_RECVERR(11L),
    IP_RECVTOS(13L),
    IP_MTU(14L),
    IP_FREEBIND(15L),
    IP_IPSEC_POLICY(16L),
    IP_XFRM_POLICY(17L),
    IP_PASSSEC(18L),
    IP_TRANSPARENT(19L),
    IP_PMTUDISC_DONT(0L),
    IP_PMTUDISC_WANT(1L),
    IP_PMTUDISC_DO(2L),
    IP_UNBLOCK_SOURCE(37L),
    IP_BLOCK_SOURCE(38L),
    IP_ADD_SOURCE_MEMBERSHIP(39L),
    IP_DROP_SOURCE_MEMBERSHIP(40L),
    IP_MSFILTER(41L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 41L;

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