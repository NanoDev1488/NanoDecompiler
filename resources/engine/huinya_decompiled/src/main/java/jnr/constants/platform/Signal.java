// исходный (обфусцированный) внутренний класс: jnr.constants.platform.Signal
package jnr.constants.platform;

import jnr.constants.Constant;
import jnr.constants.platform.ConstantResolver;

public enum Signal implements Constant {

    SIGHUP,
    SIGINT,
    SIGQUIT,
    SIGILL,
    SIGTRAP,
    SIGABRT,
    SIGIOT,
    SIGBUS,
    SIGFPE,
    SIGKILL,
    SIGUSR1,
    SIGSEGV,
    SIGUSR2,
    SIGPIPE,
    SIGALRM,
    SIGTERM,
    SIGSTKFLT,
    SIGCLD,
    SIGCHLD,
    SIGCONT,
    SIGSTOP,
    SIGTSTP,
    SIGTTIN,
    SIGTTOU,
    SIGURG,
    SIGXCPU,
    SIGXFSZ,
    SIGVTALRM,
    SIGPROF,
    SIGWINCH,
    SIGPOLL,
    SIGIO,
    SIGPWR,
    SIGSYS,
    SIGUNUSED,
    SIGRTMIN,
    SIGRTMAX,
    NSIG,
    __UNKNOWN_CONSTANT__;

    // ---- поля ----
  private static final ConstantResolver resolver;

    static {
        resolver = ConstantResolver.getResolver(Signal.class, 20000, 29999);
    }

  private Signal() { // было: <init>
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

  public static Signal valueOf(long arg0) {
        return ((Signal) resolver.valueOf(arg0));
    }

}