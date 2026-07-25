// исходный (обфусцированный) внутренний класс: jnr.constants.platform.darwin.Signal.StringTable
package jnr.constants.platform.darwin;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.darwin.Signal;

final class Signal_StringTable {

    // ---- поля ----
  public static final Map descriptions;

    static {
        descriptions = generateTable();
    }

   Signal_StringTable() { // было: <init>
        super();
    }

  public static final Map generateTable() {
        EnumMap var0 = new EnumMap(Signal.class);
        var0.put(Signal.SIGHUP, "SIGHUP");
        var0.put(Signal.SIGINT, "SIGINT");
        var0.put(Signal.SIGQUIT, "SIGQUIT");
        var0.put(Signal.SIGILL, "SIGILL");
        var0.put(Signal.SIGTRAP, "SIGTRAP");
        var0.put(Signal.SIGABRT, "SIGABRT");
        var0.put(Signal.SIGIOT, "SIGIOT");
        var0.put(Signal.SIGBUS, "SIGBUS");
        var0.put(Signal.SIGFPE, "SIGFPE");
        var0.put(Signal.SIGKILL, "SIGKILL");
        var0.put(Signal.SIGUSR1, "SIGUSR1");
        var0.put(Signal.SIGSEGV, "SIGSEGV");
        var0.put(Signal.SIGUSR2, "SIGUSR2");
        var0.put(Signal.SIGPIPE, "SIGPIPE");
        var0.put(Signal.SIGALRM, "SIGALRM");
        var0.put(Signal.SIGTERM, "SIGTERM");
        var0.put(Signal.SIGCHLD, "SIGCHLD");
        var0.put(Signal.SIGCONT, "SIGCONT");
        var0.put(Signal.SIGSTOP, "SIGSTOP");
        var0.put(Signal.SIGTSTP, "SIGTSTP");
        var0.put(Signal.SIGTTIN, "SIGTTIN");
        var0.put(Signal.SIGTTOU, "SIGTTOU");
        var0.put(Signal.SIGURG, "SIGURG");
        var0.put(Signal.SIGXCPU, "SIGXCPU");
        var0.put(Signal.SIGXFSZ, "SIGXFSZ");
        var0.put(Signal.SIGVTALRM, "SIGVTALRM");
        var0.put(Signal.SIGPROF, "SIGPROF");
        var0.put(Signal.SIGWINCH, "SIGWINCH");
        var0.put(Signal.SIGIO, "SIGIO");
        var0.put(Signal.SIGSYS, "SIGSYS");
        var0.put(Signal.NSIG, "NSIG");
        return var0;
    }

}