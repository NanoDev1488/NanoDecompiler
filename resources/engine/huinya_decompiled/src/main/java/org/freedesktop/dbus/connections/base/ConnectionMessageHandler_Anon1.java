// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.base.ConnectionMessageHandler$1
package org.freedesktop.dbus.connections.base;

import java.util.Map;
import org.freedesktop.dbus.DBusCallInfo;
import org.freedesktop.dbus.connections.base.ConnectionMessageHandler;
import org.freedesktop.dbus.interfaces.CallbackHandler;
import org.freedesktop.dbus.messages.Error;
import org.slf4j.Logger;

class ConnectionMessageHandler_Anon1 implements Runnable {

    // ---- поля ----
  final Error val$_err;
  final CallbackHandler val$fcbh;
  final ConnectionMessageHandler this$0;

   ConnectionMessageHandler_Anon1(ConnectionMessageHandler arg0, Error arg1, CallbackHandler arg2) { // было: <init>
        super();
        this$0 = arg0;
        val$_err = arg1;
        val$fcbh = arg2;
    }

  public synchronized void run() {
        try {
            this$0.getLogger().trace("Running Error Callback for {}", val$_err);
            DBusCallInfo var1 = new DBusCallInfo(val$_err);
            this$0.getInfoMap().put(Thread.currentThread(), var1);
            val$fcbh.handleError(val$_err.getException());
            this$0.getInfoMap().remove(Thread.currentThread());
        } catch (Exception e1) {
            Throwable var1 = e1;
            this$0.getLogger().debug("Exception while running error callback.", var1);
        }
    }

}