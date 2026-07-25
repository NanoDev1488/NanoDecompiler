// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.interfaces.DBus.NameOwnerChanged
package org.freedesktop.dbus.interfaces;

import org.freedesktop.dbus.messages.DBusSignal;

public class DBus_NameOwnerChanged extends DBusSignal {

    // ---- поля ----
  public final String name;
  public final String oldOwner;
  public final String newOwner;

  public DBus_NameOwnerChanged(String arg0, String arg1, String arg2, String arg3) { // было: <init>
        super(arg0, new Object[]{arg1, arg2, arg3});
        name = arg1;
        oldOwner = arg2;
        newOwner = arg3;
    }

}