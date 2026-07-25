// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.impl.DBusConnection$1
package org.freedesktop.dbus.connections.impl;

import org.freedesktop.dbus.connections.impl.DBusConnection;
import org.freedesktop.dbus.interfaces.DBusSigHandler;

class DBusConnection_Anon1 implements AutoCloseable {

    // ---- поля ----
  final Class val$_type;
  final String val$_source;
  final DBusSigHandler val$_handler;
  final DBusConnection this$0;

   DBusConnection_Anon1(DBusConnection arg0, Class arg1, String arg2, DBusSigHandler arg3) { // было: <init>
        super();
        this$0 = arg0;
        val$_type = arg1;
        val$_source = arg2;
        val$_handler = arg3;
    }

  public void close() {
        this$0.removeSigHandler(val$_type, val$_source, val$_handler);
    }

}