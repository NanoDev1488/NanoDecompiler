// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.impl.DBusConnection$2
package org.freedesktop.dbus.connections.impl;

import org.freedesktop.dbus.connections.impl.DBusConnection;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.interfaces.DBusSigHandler;

class DBusConnection_Anon2 implements AutoCloseable {

    // ---- поля ----
  final Class val$_type;
  final String val$_source;
  final DBusInterface val$_object;
  final DBusSigHandler val$_handler;
  final DBusConnection this$0;

   DBusConnection_Anon2(DBusConnection arg0, Class arg1, String arg2, DBusInterface arg3, DBusSigHandler arg4) { // было: <init>
        super();
        this$0 = arg0;
        val$_type = arg1;
        val$_source = arg2;
        val$_object = arg3;
        val$_handler = arg4;
    }

  public void close() {
        this$0.removeSigHandler(val$_type, val$_source, val$_object, val$_handler);
    }

}