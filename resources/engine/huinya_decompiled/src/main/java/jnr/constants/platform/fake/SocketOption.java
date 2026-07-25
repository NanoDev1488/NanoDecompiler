// исходный (обфусцированный) внутренний класс: jnr.constants.platform.fake.SocketOption
package jnr.constants.platform.fake;

import jnr.constants.Constant;

public enum SocketOption implements Constant {

    SO_DEBUG(1L),
    SO_ACCEPTCONN(2L),
    SO_REUSEADDR(3L),
    SO_KEEPALIVE(4L),
    SO_DONTROUTE(5L),
    SO_BROADCAST(6L),
    SO_USELOOPBACK(7L),
    SO_LINGER(8L),
    SO_OOBINLINE(9L),
    SO_REUSEPORT(10L),
    SO_TIMESTAMP(11L),
    SO_ACCEPTFILTER(12L),
    SO_DONTTRUNC(13L),
    SO_WANTMORE(14L),
    SO_WANTOOBFLAG(15L),
    SO_SNDBUF(16L),
    SO_RCVBUF(17L),
    SO_SNDLOWAT(18L),
    SO_RCVLOWAT(19L),
    SO_SNDTIMEO(20L),
    SO_RCVTIMEO(21L),
    SO_ERROR(22L),
    SO_TYPE(23L),
    SO_NREAD(24L),
    SO_NKE(25L),
    SO_NOSIGPIPE(26L),
    SO_NOADDRERR(27L),
    SO_NWRITE(28L),
    SO_REUSESHAREUID(29L),
    SO_LABEL(30L),
    SO_PEERLABEL(31L),
    SO_ATTACH_FILTER(32L),
    SO_BINDTODEVICE(33L),
    SO_DETACH_FILTER(34L),
    SO_NO_CHECK(35L),
    SO_PASSCRED(36L),
    SO_PEERCRED(37L),
    SO_PEERNAME(38L),
    SO_PRIORITY(39L),
    SO_SNDBUFFORCE(40L),
    SO_RCVBUFFORCE(41L),
    SO_GET_FILTER(42L),
    SO_TIMESTAMPNS(43L),
    SO_RECVUCRED(44L),
    SO_MAC_EXEMPT(45L),
    SO_ALLZONES(46L),
    SO_PEERSEC(47L),
    SO_PASSSEC(48L),
    SO_MARK(49L),
    SO_TIMESTAMPING(50L),
    SO_PROTOCOL(51L),
    SO_DOMAIN(52L),
    SO_RXQ_OVFL(53L),
    SO_WIFI_STATUS(54L),
    SO_PEEK_OFF(55L),
    SO_NOFCS(56L),
    SO_LOCK_FILTER(57L),
    SO_SELECT_ERR_QUEUE(58L),
    SO_BUSY_POLL(59L),
    SO_MAX_PACING_RATE(60L),
    SO_BPF_EXTENSIONS(61L),
    SO_SECURITY_AUTHENTICATION(62L),
    SO_SECURITY_ENCRYPTION_NETWORK(63L),
    SO_SECURITY_ENCRYPTION_TRANSPORT(64L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 64L;

  private SocketOption(long arg2) { // было: <init>
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