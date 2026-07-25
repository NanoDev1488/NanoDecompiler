// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.base.PendingCallbackManager
package org.freedesktop.dbus.connections.base;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.freedesktop.dbus.DBusAsyncReply;
import org.freedesktop.dbus.connections.AbstractConnection;
import org.freedesktop.dbus.interfaces.CallbackHandler;
import org.freedesktop.dbus.messages.MethodCall;

public class PendingCallbackManager {

    // ---- поля ----
  private final Map pendingCallbacks;
  private final Map pendingCallbackReplys;

   PendingCallbackManager() { // было: <init>
        super();
        pendingCallbacks = new ConcurrentHashMap();
        pendingCallbackReplys = new ConcurrentHashMap();
    }

  public synchronized void queueCallback(MethodCall arg0, Method arg1, CallbackHandler arg2, AbstractConnection arg3) {
        pendingCallbacks.put(arg0, arg2);
        pendingCallbackReplys.put(arg0, new DBusAsyncReply(arg0, arg1, arg3));
    }

  public synchronized CallbackHandler removeCallback(MethodCall arg0) {
        pendingCallbackReplys.remove(arg0);
        return ((CallbackHandler) pendingCallbacks.remove(arg0));
    }

  public synchronized CallbackHandler getCallback(MethodCall arg0) {
        return ((CallbackHandler) pendingCallbacks.get(arg0));
    }

  public synchronized DBusAsyncReply getCallbackReply(MethodCall arg0) {
        return ((DBusAsyncReply) pendingCallbackReplys.get(arg0));
    }

}