// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.interfaces.DBusSigHandler
package org.freedesktop.dbus.interfaces;

import org.freedesktop.dbus.messages.DBusSignal;

public interface DBusSigHandler {

  public abstract void handle(DBusSignal arg0);

}