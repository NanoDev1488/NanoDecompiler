// исходный (обфусцированный) внутренний класс: jnr.constants.platform.dragonflybsd.Signal
package jnr.constants.platform.dragonflybsd;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.dragonflybsd.Signal_StringTable;

public enum Signal implements Constant {

    SIGHUP(1L),
    SIGINT(2L),
    SIGQUIT(3L),
    SIGILL(4L),
    SIGTRAP(5L),
    SIGABRT(6L),
    SIGIOT(6L),
    SIGBUS(10L),
    SIGFPE(8L),
    SIGKILL(9L),
    SIGUSR1(30L),
    SIGSEGV(11L),
    SIGUSR2(31L),
    SIGPIPE(13L),
    SIGALRM(14L),
    SIGTERM(15L),
    SIGCHLD(20L),
    SIGCONT(19L),
    SIGSTOP(17L),
    SIGTSTP(18L),
    SIGTTIN(21L),
    SIGTTOU(22L),
    SIGURG(16L),
    SIGXCPU(24L),
    SIGXFSZ(25L),
    SIGVTALRM(26L),
    SIGPROF(27L),
    SIGWINCH(28L),
    SIGIO(23L),
    SIGSYS(12L),
    NSIG(64L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 64L;

  private Signal(long arg2) { // было: <init>
        value = arg2;
    }

  public final String toString() {
        return ((String) Signal_StringTable.descriptions.get(this));
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