// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.mips64el.Signal
package jnr.constants.platform.linux.mips64el;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.linux.mips64el.Signal_StringTable;

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
    SIGUSR1(16L),
    SIGSEGV(11L),
    SIGUSR2(17L),
    SIGPIPE(13L),
    SIGALRM(14L),
    SIGTERM(15L),
    SIGCLD(18L),
    SIGCHLD(18L),
    SIGCONT(25L),
    SIGSTOP(23L),
    SIGTSTP(24L),
    SIGTTIN(26L),
    SIGTTOU(27L),
    SIGURG(21L),
    SIGXCPU(30L),
    SIGXFSZ(31L),
    SIGVTALRM(28L),
    SIGPROF(29L),
    SIGWINCH(20L),
    SIGPOLL(22L),
    SIGIO(22L),
    SIGPWR(19L),
    SIGSYS(12L),
    SIGRTMIN(34L),
    SIGRTMAX(127L),
    NSIG(128L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 128L;

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