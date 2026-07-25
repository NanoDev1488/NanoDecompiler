// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.interfaces.Monitoring
package org.freedesktop.dbus.interfaces;

import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.types.UInt32;

@DBusInterfaceName("org.freedesktop.DBus.Monitoring.BecomeMonitor")
public interface Monitoring {

  public abstract void BecomeMonitor(String[] arg0, UInt32 arg1);

}