// исходный (обфусцированный) внутренний класс: jnr.constants.platform.fake.InterfaceInfo
package jnr.constants.platform.fake;

import jnr.constants.Constant;

public enum InterfaceInfo implements Constant {

    IFF_802_1Q_VLAN(1L),
    IFF_ALLMULTI(2L),
    IFF_ALTPHYS(3L),
    IFF_AUTOMEDIA(4L),
    IFF_BONDING(5L),
    IFF_BRIDGE_PORT(6L),
    IFF_BROADCAST(7L),
    IFF_CANTCONFIG(8L),
    IFF_DEBUG(9L),
    IFF_DISABLE_NETPOLL(10L),
    IFF_DONT_BRIDGE(11L),
    IFF_DORMANT(12L),
    IFF_DRV_OACTIVE(13L),
    IFF_DRV_RUNNING(14L),
    IFF_DYING(15L),
    IFF_DYNAMIC(16L),
    IFF_EBRIDGE(17L),
    IFF_ECHO(18L),
    IFF_ISATAP(19L),
    IFF_LINK0(20L),
    IFF_LINK1(21L),
    IFF_LINK2(22L),
    IFF_LIVE_ADDR_CHANGE(23L),
    IFF_LOOPBACK(24L),
    IFF_LOWER_UP(25L),
    IFF_MACVLAN_PORT(26L),
    IFF_MASTER(27L),
    IFF_MASTER_8023AD(28L),
    IFF_MASTER_ALB(29L),
    IFF_MASTER_ARPMON(30L),
    IFF_MONITOR(31L),
    IFF_MULTICAST(32L),
    IFF_NOARP(33L),
    IFF_NOTRAILERS(34L),
    IFF_OACTIVE(35L),
    IFF_OVS_DATAPATH(36L),
    IFF_POINTOPOINT(37L),
    IFF_PORTSEL(38L),
    IFF_PPROMISC(39L),
    IFF_PROMISC(40L),
    IFF_RENAMING(41L),
    IFF_ROUTE(42L),
    IFF_RUNNING(43L),
    IFF_SIMPLEX(44L),
    IFF_SLAVE(45L),
    IFF_SLAVE_INACTIVE(46L),
    IFF_SLAVE_NEEDARP(47L),
    IFF_SMART(48L),
    IFF_STATICARP(49L),
    IFF_SUPP_NOFCS(50L),
    IFF_TEAM_PORT(51L),
    IFF_TX_SKB_SHARING(52L),
    IFF_UNICAST_FLT(53L),
    IFF_UP(54L),
    IFF_WAN_HDLC(55L),
    IFF_XMIT_DST_RELEASE(56L),
    IFF_VOLATILE(57L),
    IFF_CANTCHANGE(58L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 58L;

  private InterfaceInfo(long arg2) { // было: <init>
        value = arg2;
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