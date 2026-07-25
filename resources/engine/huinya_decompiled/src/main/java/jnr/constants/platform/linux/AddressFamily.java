// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.AddressFamily
package jnr.constants.platform.linux;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.linux.AddressFamily_StringTable;

public enum AddressFamily implements Constant {

    AF_UNSPEC(0L),
    AF_LOCAL(1L),
    AF_UNIX(1L),
    AF_INET(2L),
    AF_SNA(22L),
    AF_DECnet(12L),
    AF_APPLETALK(5L),
    AF_ROUTE(16L),
    AF_IPX(4L),
    AF_ISDN(34L),
    AF_INET6(10L),
    AF_AX25(3L),
    AF_KEY(15L),
    AF_NETLINK(16L),
    AF_RDS(21L),
    AF_PPPOX(24L),
    AF_LLC(26L),
    AF_IB(27L),
    AF_MPLS(28L),
    AF_CAN(29L),
    AF_TIPC(30L),
    AF_BLUETOOTH(31L),
    AF_ALG(38L),
    AF_VSOCK(40L),
    AF_KCM(41L),
    AF_XDP(44L),
    AF_MAX(45L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 45L;

  private AddressFamily(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) AddressFamily_StringTable.descriptions.get(this));
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