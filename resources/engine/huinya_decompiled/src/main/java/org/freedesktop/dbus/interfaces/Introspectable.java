// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.interfaces.Introspectable
package org.freedesktop.dbus.interfaces;

import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;

@DBusInterfaceName("org.freedesktop.DBus.Introspectable")
public interface Introspectable extends DBusInterface {

  public abstract String Introspect();

}