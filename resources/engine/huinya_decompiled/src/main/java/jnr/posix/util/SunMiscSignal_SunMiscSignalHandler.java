// исходный (обфусцированный) внутренний класс: jnr.posix.util.SunMiscSignal.SunMiscSignalHandler
package jnr.posix.util;

import sun.misc.Signal;
import sun.misc.SignalHandler;

class SunMiscSignal_SunMiscSignalHandler implements SignalHandler {

    // ---- поля ----
  final jnr.posix.SignalHandler handler;

  public SunMiscSignal_SunMiscSignalHandler(jnr.posix.SignalHandler arg0) { // было: <init>
        super();
        handler = arg0;
    }

  public void handle(Signal arg0) {
        handler.handle(arg0.getNumber());
    }

}