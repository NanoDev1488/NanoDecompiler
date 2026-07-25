// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.interfaces.DBus.NameAcquired
package org.freedesktop.dbus.interfaces;

import org.freedesktop.dbus.messages.DBusSignal;

public class DBus_NameAcquired extends DBusSignal {

    // ---- поля ----
  public final String name;

  public DBus_NameAcquired(String arg0, String arg1) { // было: <init>
        super(arg0, new Object[]{arg1});
        name = arg1;
    }

}