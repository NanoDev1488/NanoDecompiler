// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.ProtocolFamily
package jnr.constants.platform.linux;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.linux.ProtocolFamily_StringTable;

public enum ProtocolFamily implements Constant {

    PF_UNSPEC(0L),
    PF_LOCAL(1L),
    PF_UNIX(1L),
    PF_INET(2L),
    PF_SNA(22L),
    PF_DECnet(12L),
    PF_APPLETALK(5L),
    PF_ROUTE(16L),
    PF_IPX(4L),
    PF_ISDN(34L),
    PF_KEY(15L),
    PF_INET6(10L),
    PF_NETLINK(16L),
    PF_RDS(21L),
    PF_PPPOX(24L),
    PF_LLC(26L),
    PF_IB(27L),
    PF_MPLS(28L),
    PF_CAN(29L),
    PF_TIPC(30L),
    PF_BLUETOOTH(31L),
    PF_ALG(38L),
    PF_VSOCK(40L),
    PF_KCM(41L),
    PF_XDP(44L),
    PF_MAX(45L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 45L;

  private ProtocolFamily(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) ProtocolFamily_StringTable.descriptions.get(this));
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