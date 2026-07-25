// исходный (обфусцированный) внутренний класс: jnr.constants.platform.fake.Syslog
package jnr.constants.platform.fake;

import jnr.constants.Constant;

public enum Syslog implements Constant {

    LOG_ALERT(1L),
    LOG_AUTH(2L),
    LOG_AUTHPRIV(3L),
    LOG_CONS(4L),
    LOG_CONSOLE(5L),
    LOG_CRIT(6L),
    LOG_CRON(7L),
    LOG_DAEMON(8L),
    LOG_DEBUG(9L),
    LOG_EMERG(10L),
    LOG_ERR(11L),
    LOG_FTP(12L),
    LOG_INFO(13L),
    LOG_KERN(14L),
    LOG_LOCAL0(15L),
    LOG_LOCAL1(16L),
    LOG_LOCAL2(17L),
    LOG_LOCAL3(18L),
    LOG_LOCAL4(19L),
    LOG_LOCAL5(20L),
    LOG_LOCAL6(21L),
    LOG_LOCAL7(22L),
    LOG_LPR(23L),
    LOG_MAIL(24L),
    LOG_NDELAY(25L),
    LOG_NEWS(26L),
    LOG_NOTICE(27L),
    LOG_NOWAIT(28L),
    LOG_NTP(29L),
    LOG_ODELAY(30L),
    LOG_PERROR(31L),
    LOG_PID(32L),
    LOG_SECURITY(33L),
    LOG_SYSLOG(34L),
    LOG_USER(35L),
    LOG_UUCP(36L),
    LOG_WARNING(37L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 37L;

  private Syslog(long arg2) { // было: <init>
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