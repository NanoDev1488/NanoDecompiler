// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.interfaces.Peer
package org.freedesktop.dbus.interfaces;

import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;

@DBusInterfaceName("org.freedesktop.DBus.Peer")
public interface Peer extends DBusInterface {

  public abstract void Ping();

  public abstract String GetMachineId();

}