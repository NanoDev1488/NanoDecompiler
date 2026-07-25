// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.base.ConnectionMessageHandler$2
package org.freedesktop.dbus.connections.base;

import java.util.Map;
import org.freedesktop.dbus.DBusAsyncReply;
import org.freedesktop.dbus.DBusCallInfo;
import org.freedesktop.dbus.RemoteInvocationHandler;
import org.freedesktop.dbus.connections.base.ConnectionMessageHandler;
import org.freedesktop.dbus.interfaces.CallbackHandler;
import org.freedesktop.dbus.messages.MethodReturn;
import org.slf4j.Logger;

class ConnectionMessageHandler_Anon2 implements Runnable {

    // ---- поля ----
  final MethodReturn val$_mr;
  final DBusAsyncReply val$fasr;
  final CallbackHandler val$fcbh;
  final ConnectionMessageHandler this$0;

   ConnectionMessageHandler_Anon2(ConnectionMessageHandler arg0, MethodReturn arg1, DBusAsyncReply arg2, CallbackHandler arg3) { // было: <init>
        super();
        this$0 = arg0;
        val$_mr = arg1;
        val$fasr = arg2;
        val$fcbh = arg3;
    }

  public synchronized void run() {
        try {
            this$0.getLogger().trace("Running Callback for {}", val$_mr);
            DBusCallInfo var1 = new DBusCallInfo(val$_mr);
            this$0.getInfoMap().put(Thread.currentThread(), var1);
            Object var2 = RemoteInvocationHandler.convertRV(val$_mr.getParameters(), val$fasr.getMethod(), val$fasr.getConnection());
            val$fcbh.handle(var2);
            this$0.getInfoMap().remove(Thread.currentThread());
        } catch (Exception e1) {
            Throwable var1 = e1;
            this$0.getLogger().debug("Exception while running callback.", var1);
        }
    }

}