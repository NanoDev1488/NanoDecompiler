// исходный (обфусцированный) внутренний класс: jnr.constants.platform.IP
package jnr.constants.platform;

import jnr.constants.Constant;
import jnr.constants.platform.ConstantResolver;

public enum IP implements Constant {

    IP_OPTIONS,
    IP_HDRINCL,
    IP_TOS,
    IP_TTL,
    IP_RECVOPTS,
    IP_RECVRETOPTS,
    IP_RECVDSTADDR,
    IP_RETOPTS,
    IP_MINTTL,
    IP_DONTFRAG,
    IP_SENDSRCADDR,
    IP_ONESBCAST,
    IP_RECVTTL,
    IP_RECVIF,
    IP_RECVSLLA,
    IP_PORTRANGE,
    IP_MULTICAST_IF,
    IP_MULTICAST_TTL,
    IP_MULTICAST_LOOP,
    IP_ADD_MEMBERSHIP,
    IP_DROP_MEMBERSHIP,
    IP_DEFAULT_MULTICAST_TTL,
    IP_DEFAULT_MULTICAST_LOOP,
    IP_MAX_MEMBERSHIPS,
    IP_ROUTER_ALERT,
    IP_PKTINFO,
    IP_PKTOPTIONS,
    IP_MTU_DISCOVER,
    IP_RECVERR,
    IP_RECVTOS,
    IP_MTU,
    IP_FREEBIND,
    IP_IPSEC_POLICY,
    IP_XFRM_POLICY,
    IP_PASSSEC,
    IP_TRANSPARENT,
    IP_PMTUDISC_DONT,
    IP_PMTUDISC_WANT,
    IP_PMTUDISC_DO,
    IP_UNBLOCK_SOURCE,
    IP_BLOCK_SOURCE,
    IP_ADD_SOURCE_MEMBERSHIP,
    IP_DROP_SOURCE_MEMBERSHIP,
    IP_MSFILTER,
    __UNKNOWN_CONSTANT__;

    // ---- поля ----
  private static final ConstantResolver resolver;

    static {
        resolver = ConstantResolver.getResolver(IP.class, 20000, 29999);
    }

  private IP() { // было: <init>
        // (пустое тело)
    }

  public final int value() {
        return ((int) resolver.longValue(this));
    }

  public final int intValue() {
        return ((int) resolver.longValue(this));
    }

  public final long longValue() {
        return resolver.longValue(this);
    }

  public final String description() {
        return resolver.description(this);
    }

  public final boolean defined() {
        return resolver.defined(this);
    }

  public final String toString() {
        return description();
    }

  public static IP valueOf(long arg0) {
        return ((IP) resolver.valueOf(arg0));
    }

}