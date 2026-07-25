// исходный (обфусцированный) внутренний класс: jnr.constants.platform.Syslog
package jnr.constants.platform;

import jnr.constants.Constant;
import jnr.constants.platform.ConstantResolver;

public enum Syslog implements Constant {

    LOG_ALERT,
    LOG_AUTH,
    LOG_AUTHPRIV,
    LOG_CONS,
    LOG_CONSOLE,
    LOG_CRIT,
    LOG_CRON,
    LOG_DAEMON,
    LOG_DEBUG,
    LOG_EMERG,
    LOG_ERR,
    LOG_FTP,
    LOG_INFO,
    LOG_KERN,
    LOG_LOCAL0,
    LOG_LOCAL1,
    LOG_LOCAL2,
    LOG_LOCAL3,
    LOG_LOCAL4,
    LOG_LOCAL5,
    LOG_LOCAL6,
    LOG_LOCAL7,
    LOG_LPR,
    LOG_MAIL,
    LOG_NDELAY,
    LOG_NEWS,
    LOG_NOTICE,
    LOG_NOWAIT,
    LOG_NTP,
    LOG_ODELAY,
    LOG_PERROR,
    LOG_PID,
    LOG_SECURITY,
    LOG_SYSLOG,
    LOG_USER,
    LOG_UUCP,
    LOG_WARNING,
    __UNKNOWN_CONSTANT__;

    // ---- поля ----
  private static final ConstantResolver resolver;

    static {
        resolver = ConstantResolver.getResolver(Syslog.class, 20000, 29999);
    }

  private Syslog() { // было: <init>
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

  public static Syslog valueOf(long arg0) {
        return ((Syslog) resolver.valueOf(arg0));
    }

}