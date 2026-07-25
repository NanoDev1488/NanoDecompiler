// исходный (обфусцированный) внутренний класс: jnr.constants.platform.freebsd.aarch64.Syslog
package jnr.constants.platform.freebsd.aarch64;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.freebsd.aarch64.Syslog_StringTable;

public enum Syslog implements Constant {

    LOG_ALERT(1L),
    LOG_AUTH(32L),
    LOG_AUTHPRIV(80L),
    LOG_CONS(2L),
    LOG_CONSOLE(112L),
    LOG_CRIT(2L),
    LOG_CRON(72L),
    LOG_DAEMON(24L),
    LOG_DEBUG(7L),
    LOG_EMERG(0L),
    LOG_ERR(3L),
    LOG_FTP(88L),
    LOG_INFO(6L),
    LOG_KERN(0L),
    LOG_LOCAL0(128L),
    LOG_LOCAL1(136L),
    LOG_LOCAL2(144L),
    LOG_LOCAL3(152L),
    LOG_LOCAL4(160L),
    LOG_LOCAL5(168L),
    LOG_LOCAL6(176L),
    LOG_LOCAL7(184L),
    LOG_LPR(48L),
    LOG_MAIL(16L),
    LOG_NDELAY(8L),
    LOG_NEWS(56L),
    LOG_NOTICE(5L),
    LOG_NOWAIT(16L),
    LOG_NTP(96L),
    LOG_ODELAY(4L),
    LOG_PERROR(32L),
    LOG_PID(1L),
    LOG_SECURITY(104L),
    LOG_SYSLOG(40L),
    LOG_USER(8L),
    LOG_UUCP(64L),
    LOG_WARNING(4L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 0L;
  public static final long MAX_VALUE = 184L;

  private Syslog(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) Syslog_StringTable.descriptions.get(this));
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