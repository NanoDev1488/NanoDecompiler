// исходный (обфусцированный) внутренний класс: jnr.constants.platform.windows.Signal.StringTable
package jnr.constants.platform.windows;

import java.util.EnumMap;
import java.util.Map;
import jnr.constants.platform.windows.Signal;

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
        var0.put(Signal.SIGINT, "SIGINT");
        var0.put(Signal.SIGILL, "SIGILL");
        var0.put(Signal.SIGABRT, "SIGABRT");
        var0.put(Signal.SIGFPE, "SIGFPE");
        var0.put(Signal.SIGSEGV, "SIGSEGV");
        var0.put(Signal.SIGTERM, "SIGTERM");
        var0.put(Signal.NSIG, "NSIG");
        return var0;
    }

}