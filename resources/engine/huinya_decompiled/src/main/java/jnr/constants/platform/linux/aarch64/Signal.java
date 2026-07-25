// исходный (обфусцированный) внутренний класс: jnr.constants.platform.linux.aarch64.Signal
package jnr.constants.platform.linux.aarch64;

import java.util.Map;
import jnr.constants.Constant;
import jnr.constants.platform.linux.aarch64.Signal_StringTable;

public enum Signal implements Constant {

    SIGHUP(1L),
    SIGINT(2L),
    SIGQUIT(3L),
    SIGILL(4L),
    SIGTRAP(5L),
    SIGABRT(6L),
    SIGIOT(6L),
    SIGBUS(7L),
    SIGFPE(8L),
    SIGKILL(9L),
    SIGUSR1(10L),
    SIGSEGV(11L),
    SIGUSR2(12L),
    SIGPIPE(13L),
    SIGALRM(14L),
    SIGTERM(15L),
    SIGSTKFLT(16L),
    SIGCLD(17L),
    SIGCHLD(17L),
    SIGCONT(18L),
    SIGSTOP(19L),
    SIGTSTP(20L),
    SIGTTIN(21L),
    SIGTTOU(22L),
    SIGURG(23L),
    SIGXCPU(24L),
    SIGXFSZ(25L),
    SIGVTALRM(26L),
    SIGPROF(27L),
    SIGWINCH(28L),
    SIGPOLL(29L),
    SIGIO(29L),
    SIGPWR(30L),
    SIGSYS(31L),
    SIGRTMIN(34L),
    SIGRTMAX(64L),
    NSIG(65L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 65L;

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