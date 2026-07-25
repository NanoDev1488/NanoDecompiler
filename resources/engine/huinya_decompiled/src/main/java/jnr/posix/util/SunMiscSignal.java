// исходный (обфусцированный) внутренний класс: jnr.posix.util.SunMiscSignal
package jnr.posix.util;

import jnr.constants.platform.Signal;
import jnr.posix.SignalHandler;
import jnr.posix.util.SunMiscSignal_SunMiscSignalHandler;

public class SunMiscSignal {

  public SunMiscSignal() { // было: <init>
        super();
    }

  public static SignalHandler signal(Signal arg0, SignalHandler arg1) {
        sun.misc.Signal var2 = new sun.misc.Signal(arg0.name().substring("SIG".length()));
        sun.misc.SignalHandler var3 = sun.misc.Signal.handle(var2, new SunMiscSignal_SunMiscSignalHandler(arg1));
        if (!(var3 instanceof SunMiscSignal_SunMiscSignalHandler)) {
            return null;
        } else {
            return (((SunMiscSignal_SunMiscSignalHandler) var3)).handler;
        }
    }

}