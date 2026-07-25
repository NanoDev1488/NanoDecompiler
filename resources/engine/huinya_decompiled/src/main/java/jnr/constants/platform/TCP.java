// исходный (обфусцированный) внутренний класс: jnr.constants.platform.TCP
package jnr.constants.platform;

import jnr.constants.Constant;
import jnr.constants.platform.ConstantResolver;

public enum TCP implements Constant {

    TCP_MAX_SACK,
    TCP_MSS,
    TCP_MINMSS,
    TCP_MINMSSOVERLOAD,
    TCP_MAXWIN,
    TCP_MAX_WINSHIFT,
    TCP_MAXBURST,
    TCP_MAXHLEN,
    TCP_MAXOLEN,
    TCP_NODELAY,
    TCP_MAXSEG,
    TCP_NOPUSH,
    TCP_NOOPT,
    TCP_KEEPALIVE,
    TCP_NSTATES,
    TCP_RETRANSHZ,
    TCP_CORK,
    TCP_DEFER_ACCEPT,
    TCP_INFO,
    TCP_KEEPCNT,
    TCP_KEEPIDLE,
    TCP_KEEPINTVL,
    TCP_LINGER2,
    TCP_MD5SIG,
    TCP_QUICKACK,
    TCP_SYNCNT,
    TCP_WINDOW_CLAMP,
    TCP_FASTOPEN,
    TCP_CONGESTION,
    TCP_COOKIE_TRANSACTIONS,
    TCP_QUEUE_SEQ,
    TCP_REPAIR,
    TCP_REPAIR_OPTIONS,
    TCP_REPAIR_QUEUE,
    TCP_THIN_DUPACK,
    TCP_THIN_LINEAR_TIMEOUTS,
    TCP_TIMESTAMP,
    TCP_USER_TIMEOUT,
    __UNKNOWN_CONSTANT__;

    // ---- поля ----
  private static final ConstantResolver resolver;

    static {
        resolver = ConstantResolver.getResolver(TCP.class, 20000, 29999);
    }

  private TCP() { // было: <init>
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

  public static TCP valueOf(long arg0) {
        return ((TCP) resolver.valueOf(arg0));
    }

}