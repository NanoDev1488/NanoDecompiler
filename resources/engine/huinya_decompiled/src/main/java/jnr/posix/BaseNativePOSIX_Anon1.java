// исходный (обфусцированный) внутренний класс: jnr.posix.BaseNativePOSIX$1
package jnr.posix;

import jnr.posix.BaseNativePOSIX;
import jnr.posix.LibC_LibCSignalHandler;
import jnr.posix.SignalHandler;

class BaseNativePOSIX_Anon1 implements LibC_LibCSignalHandler {

    // ---- поля ----
  final SignalHandler val$handler;
  final BaseNativePOSIX this$0;

   BaseNativePOSIX_Anon1(BaseNativePOSIX arg0, SignalHandler arg1) { // было: <init>
        super();
        this$0 = arg0;
        val$handler = arg1;
    }

  public void signal(int arg0) {
        val$handler.handle(arg0);
    }

}