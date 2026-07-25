// исходный (обфусцированный) внутренний класс: org.freedesktop.dbus.interfaces.ObjectManager
package org.freedesktop.dbus.interfaces;

import java.util.Map;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.interfaces.DBusInterface;

@DBusInterfaceName("org.freedesktop.DBus.ObjectManager")
public interface ObjectManager extends DBusInterface {

  public abstract Map GetManagedObjects();

}