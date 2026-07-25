// исходный (обфусцированный) внутренний класс: jnr.constants.platform.fake.Signal
package jnr.constants.platform.fake;

import jnr.constants.Constant;

public enum Signal implements Constant {

    SIGHUP(1L),
    SIGINT(2L),
    SIGQUIT(3L),
    SIGILL(4L),
    SIGTRAP(5L),
    SIGABRT(6L),
    SIGIOT(7L),
    SIGBUS(8L),
    SIGFPE(9L),
    SIGKILL(10L),
    SIGUSR1(11L),
    SIGSEGV(12L),
    SIGUSR2(13L),
    SIGPIPE(14L),
    SIGALRM(15L),
    SIGTERM(16L),
    SIGSTKFLT(17L),
    SIGCLD(18L),
    SIGCHLD(19L),
    SIGCONT(20L),
    SIGSTOP(21L),
    SIGTSTP(22L),
    SIGTTIN(23L),
    SIGTTOU(24L),
    SIGURG(25L),
    SIGXCPU(26L),
    SIGXFSZ(27L),
    SIGVTALRM(28L),
    SIGPROF(29L),
    SIGWINCH(30L),
    SIGPOLL(31L),
    SIGIO(32L),
    SIGPWR(33L),
    SIGSYS(34L),
    SIGUNUSED(35L),
    SIGRTMIN(36L),
    SIGRTMAX(37L),
    NSIG(38L);

    // ---- поля ----
  private final long value;
  public static final long MIN_VALUE = 1L;
  public static final long MAX_VALUE = 38L;

  private Signal(long arg2) { // было: <init>
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