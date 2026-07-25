// исходный (обфусцированный) внутренний класс: jnr.constants.platform.freebsd.aarch64.Syslog.StringTable
package jnr.constants.platform.freebsd.aarch64;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.freebsd.aarch64.Syslog;

final class Syslog_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   Syslog_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(Syslog.class);
        var0.put(Syslog.LOG_ALERT, "LOG_ALERT");
        var0.put(Syslog.LOG_AUTH, "LOG_AUTH");
        var0.put(Syslog.LOG_AUTHPRIV, "LOG_AUTHPRIV");
        var0.put(Syslog.LOG_CONS, "LOG_CONS");
        var0.put(Syslog.LOG_CONSOLE, "LOG_CONSOLE");
        var0.put(Syslog.LOG_CRIT, "LOG_CRIT");
        var0.put(Syslog.LOG_CRON, "LOG_CRON");
        var0.put(Syslog.LOG_DAEMON, "LOG_DAEMON");
        var0.put(Syslog.LOG_DEBUG, "LOG_DEBUG");
        var0.put(Syslog.LOG_EMERG, "LOG_EMERG");
        var0.put(Syslog.LOG_ERR, "LOG_ERR");
        var0.put(Syslog.LOG_FTP, "LOG_FTP");
        var0.put(Syslog.LOG_INFO, "LOG_INFO");
        var0.put(Syslog.LOG_KERN, "LOG_KERN");
        var0.put(Syslog.LOG_LOCAL0, "LOG_LOCAL0");
        var0.put(Syslog.LOG_LOCAL1, "LOG_LOCAL1");
        var0.put(Syslog.LOG_LOCAL2, "LOG_LOCAL2");
        var0.put(Syslog.LOG_LOCAL3, "LOG_LOCAL3");
        var0.put(Syslog.LOG_LOCAL4, "LOG_LOCAL4");
        var0.put(Syslog.LOG_LOCAL5, "LOG_LOCAL5");
        var0.put(Syslog.LOG_LOCAL6, "LOG_LOCAL6");
        var0.put(Syslog.LOG_LOCAL7, "LOG_LOCAL7");
        var0.put(Syslog.LOG_LPR, "LOG_LPR");
        var0.put(Syslog.LOG_MAIL, "LOG_MAIL");
        var0.put(Syslog.LOG_NDELAY, "LOG_NDELAY");
        var0.put(Syslog.LOG_NEWS, "LOG_NEWS");
        var0.put(Syslog.LOG_NOTICE, "LOG_NOTICE");
        var0.put(Syslog.LOG_NOWAIT, "LOG_NOWAIT");
        var0.put(Syslog.LOG_NTP, "LOG_NTP");
        var0.put(Syslog.LOG_ODELAY, "LOG_ODELAY");
        var0.put(Syslog.LOG_PERROR, "LOG_PERROR");
        var0.put(Syslog.LOG_PID, "LOG_PID");
        var0.put(Syslog.LOG_SECURITY, "LOG_SECURITY");
        var0.put(Syslog.LOG_SYSLOG, "LOG_SYSLOG");
        var0.put(Syslog.LOG_USER, "LOG_USER");
        var0.put(Syslog.LOG_UUCP, "LOG_UUCP");
        var0.put(Syslog.LOG_WARNING, "LOG_WARNING");
        return var0;
    }

}