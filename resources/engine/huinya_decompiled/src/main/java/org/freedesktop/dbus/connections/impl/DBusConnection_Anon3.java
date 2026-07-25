// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.connections.impl.DBusConnection$3
package org.freedesktop.dbus.connections.impl;

import org.freedesktop.dbus.DBusMatchRule;
import org.freedesktop.dbus.connections.impl.DBusConnection;
import org.freedesktop.dbus.interfaces.DBusSigHandler;

class DBusConnection_Anon3 implements AutoCloseable {

    // ---- поля ----
  final DBusMatchRule val$_rule;
  final DBusSigHandler val$_handler;
  final DBusConnection this$0;

   DBusConnection_Anon3(DBusConnection arg0, DBusMatchRule arg1, DBusSigHandler arg2) { // было: <init>
        super();
        this$0 = arg0;
        val$_rule = arg1;
        val$_handler = arg2;
    }

  public void close() {
        this$0.removeSigHandler(val$_rule, val$_handler);
    }

}